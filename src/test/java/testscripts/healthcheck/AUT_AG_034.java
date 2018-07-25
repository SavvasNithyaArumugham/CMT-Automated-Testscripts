package testscripts.healthcheck;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_034 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_132()
	{
		testParameters.setCurrentTestDescription("Verify user is unable to delete a folder if it has a file that is checked out for editing purpose");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();
		
		for(String folderName:folderNamesList)
		{
			myFiles.releaseLockOnFilesUnderFolder(folderName);
		}
		
		myFiles.deleteCreatedFolder(folderDetails);
		
		myFiles.createFolder(folderDetails);
		
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			myFiles.openCreatedFolder(folderName);
			
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

			ArrayList<String> createdFileNames = myFilesTestObj
					.getCreatedFileNames(fileDetails);

			for (String fileName : createdFileNames) {

				myFiles.createFile(fileDetails);

				docDetailsPageTest.verifyCreatedFile(fileName, "");
				
				docDetailsPageObj.performEditOfflineDocAction();

				/*docDetailsPageObj.backToFolderOrDocumentPage("");*/
				sitesPage.enterIntoDocumentLibrary();
			}
			
			myFiles.deleteCreatedFolder(folderDetails);
			
			myFilesTestObj.verifyDeletedFolderForLockedFiles(folderDetails);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}