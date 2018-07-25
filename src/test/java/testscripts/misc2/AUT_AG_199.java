package testscripts.misc2;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_199 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_092()
	{
		testParameters.setCurrentTestDescription("1. Verify that user should not be able to view Follow option for folders in document library"
				+ "<br>2. Verify that user should be able to view Follow option for files in document library"
				+ "<br>3. Verify that user should be able to view 'tick' sign before Follow option"
				+ "<br>4. Verify that user should be able to view green coloured 'tick' sign after clicking on Follow option");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
		}
		
		//1. Verify that user should not be able to view Follow option for folders in document library
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
        myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		docLibPg.selectDocLibViewType("Detailed View");
		folderNamesList = myFiles.getFolderNames(folderDetails);
		for(String folderName:folderNamesList)
		{
			docLibPgTest.verifyFollowLinkForFolder(folderName);
		}
		
		//2. Verify that user should be able to view Follow option for files in document library
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		ArrayList<String> createdFileNames = myFilesTestObj
				.getCreatedFileNames(fileDetails);

		for (String fileName : createdFileNames) {

			myFiles.createFile(fileDetails);
			docDetailsPageTest.verifyCreatedFile(fileName, "");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			docLibPgTest.verifyFollowLinkForFile(fileName);
			
			//3. Verify that user should be able to view 'tick' sign before Follow option
			docLibPgTest.verifyTickSignBeforeFollowOptionForFile(fileName);
			
			//4. Verify that user should be able to view green coloured 'tick' sign after clicking on Follow option
			docLibPg.clickOnFollowOption(fileName);
			docLibPgTest.verifyUnfollowLinkForFile(fileName);
		}
		
		//
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}