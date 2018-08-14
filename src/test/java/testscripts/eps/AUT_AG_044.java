package testscripts.eps;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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
public class AUT_AG_044 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_042()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify user not able to delete the folder if it was published as content."
				
						
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
				String Instution = dataTable.getData("MyFiles", "RelationshipName");
				// Script Started: 
				
				functionalLibrary.loginAsValidUser(signOnPage);	
				
				sitesPage.siteFinder(sourceSiteName);
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				myFiles.createFolder(folderDetails);
				myFiles.openCreatedFolder(folderName);
				collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
			
				UIHelper.waitFor(driver);
				
				//Publish the content
			
				collectionPg.clickOnMoreSetting(fileName[1]);	
				
				epsPg.publishbutton(fileName[1],Option[0]);
				epsPg.Publish(Instution, fileName[1],"one");
				
				sitesPage.enterIntoDocumentLibrary();
				myFiles.deleteCreatedFolder(folderDetails);
				sitesPage.enterIntoDocumentLibraryWithoutReport();
				
				UIHelper.waitFor(driver);
				
				
				Boolean flag1 =myFiles.isFileOrFolderDisplayed(folderName);
				if(flag1){
					report.updateTestLog("<b>Delete the content if published:", "User cant deleted the folder, if the "+fileName[1]+" was Published", Status.PASS);				
					myFiles.openCreatedFolder(folderName);
					epsPg.DeleteFolderandFile();
					epsPg.DeleteFolderandFile();
				}else{
					report.updateTestLog("<b>Delete the content if published:", "User deleted the folder if the "+fileName[1]+" was Published from the content", Status.FAIL);				
					
				}
				
				
												
			
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_44 Status", "EPS 44 Testcases has been failed", Status.FAIL);	
			}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}