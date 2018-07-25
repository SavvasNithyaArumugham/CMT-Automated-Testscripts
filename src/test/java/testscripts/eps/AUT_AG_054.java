package testscripts.eps;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_054 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_051()
	{
		testParameters.setCurrentTestDescription(
				
				"ALFDEPLOY-3910_Verify user not able to publish a "
				+ "level3 sco zip file in player.html url format"
				+"[1]ALFDEPLOY-3910_Verify user not able to publish a normal zip file "
				+ "in player.html url format"
						
				);	
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);			
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		try{
		//Value Declaration
				String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
				AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
				String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
				String filePath = dataTable.getData("MyFiles", "FilePath");	
				String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
				String folderDetails[] = dataTable.getData("MyFiles", "CreateFolder").split(";");	
				String folderName[] = dataTable.getData("MyFiles", "Version").split(",");
				String Instution[] = dataTable.getData("MyFiles", "RelationshipName").split(";");
				// Script Started: 
				
				functionalLibrary.loginAsValidUser(signOnPage);	
				UIHelper.waitFor(driver);
				sitesPage.siteFinder(sourceSiteName);
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
				
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				epsPg.Publish(Instution[0], fileName[0],"one");		
				
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				
				String url1 =epsPg.getPublishingURL("Publication", Instution[0]);
				if(!url1.contains("/player.html")){
					report.updateTestLog("Publication URL is ", "Publication URL doesnot "
							+ "have Player.html as displayed as "+url1, Status.PASS);	
				}else{
					report.updateTestLog("Publication URL is ", "Publication URL does"
							+ "have Player.html as displayed as "+url1, Status.FAIL);
				}
				
				
				UIHelper.click(driver, epsPg.batchPublishcancelbutton);
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				myFiles.createFolder(folderDetails[0]);
				myFiles.openCreatedFolder(folderName[0]);
				
				myFiles.createFolder(folderDetails[1]);
				myFiles.openCreatedFolder(folderName[1]);
				
				myFiles.createFolder(folderDetails[2]);
				myFiles.openCreatedFolder(folderName[2]);
				
				myFiles.createFolder(folderDetails[3]);
				myFiles.openCreatedFolder(folderName[3]);
				
				
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	
			
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				epsPg.Publish(Instution[0], fileName[0],"one");		
				
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				
				String url =epsPg.getPublishingURL("Publication", Instution[0]);
				if(!url.contains("/player.html")){
					report.updateTestLog("Publication URL is ", "Publication URL doesnot "
							+ "have Player.html as displayed as "+url, Status.PASS);	
				}else{
					report.updateTestLog("Publication URL is ", "Publication URL does"
							+ "have Player.html as displayed as "+url, Status.FAIL);
				}
				
				
				UIHelper.click(driver, epsPg.batchPublishcancelbutton);
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();	
				epsPg.DeleteFolderandFile();
				
				
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_54 Status", "EPS 54 Testcases has been failed", Status.FAIL);	
			}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}