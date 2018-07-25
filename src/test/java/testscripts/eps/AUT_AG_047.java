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
public class AUT_AG_047 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_045()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify user can able to publish content as Zip in one instituiton and expanding in another institution."
				+"Verify there is a check box to publish the content either as a Zip or by expanding them."						
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
				String folderDetails = dataTable.getData("MyFiles", "CreateFolder");	
				String folderName = dataTable.getData("MyFiles", "Version");
				String Instution[] = dataTable.getData("MyFiles", "RelationshipName").split(";");
				// Script Started: 
				
				functionalLibrary.loginAsValidUser(signOnPage);	
				UIHelper.waitFor(driver);
				sitesPage.siteFinder(sourceSiteName);
				sitesPage.enterIntoDocumentLibrary();
		/*		docLibPg.deleteAllFilesAndFolders();
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();*/
				myFiles.createFolder(folderDetails);
				myFiles.openCreatedFolder(folderName);
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	
			
				UIHelper.waitFor(driver);
				
				//Publish the content
				
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				UIHelper.click(driver, epsPg.zipuploadxpath);
				epsPg.Publish(Instution[0], fileName[0],"one");		
				collectionPg.clickOnMoreSetting(fileName[0]);
				epsPg.publishbutton(fileName[0],Option[0]);
				epsPg.Publish(Instution[1], fileName[0],"two");
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				
				epsPg.clickonurl("Publication", Instution[1], "Phoenix ELA", "First Read Guide");
				epsPg.ClickOnURLZipItself("Publication", Instution[0]);
				UIHelper.click(driver, epsPg.batchPublishcancelbutton);
				epsPg.DeleteFolderandFile();
				UIHelper.pageRefresh(driver);
				epsPg.DeleteFolderandFile();
				
				
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_47 Status", "EPS 47 Testcases has been failed", Status.FAIL);	
			}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}