package testscripts.misc5;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_390P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P2()
	{
		testParameters.setCurrentTestDescription("Verify if sitemanager is able to become owner of multiple files or folders created by other users<br> - Part2:Check the presence of 'Become owner' option");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);	
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folder = dataTable.getData("MyFiles", "Version");
		String folder1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String folder2 = dataTable.getData("MyFiles", "RelationshipName");
		String action = dataTable.getData("MyFiles", "MoreSettingsOption");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOption = dataTable.getData("MyFiles", "BrowseActionName");
		String folder3 = dataTable.getData("MyFiles", "CreateChildFolder");
		
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(folder);
		
		sitesPage.clickOnMoreSetting(fileName);

		docLibPge.commonMethodForClickOnMoreSettingsOption(fileName, action);
		
		if(docLibPge.isOfflineLockMsgDisplayed()){
			report.updateTestLog(
					"Verify the user is able to lock a file",
					"File Locked successfully."
							+ "<br><b> Locked File Name </b> :"
							+ fileName, Status.PASS);
		}else{
			report.updateTestLog(
					"Verify the user is able to lock a file",
					"File not Locked."
							+ "<br><b> File Name </b> :"
							+ fileName, Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.clickOnMoreSetting(folder);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName, moreSettingsOption);
		
		sitesPage.clickOnMoreSetting(folder3);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName, moreSettingsOption);
		
		sitesPage.documentdetails(folder1);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		
		sitesPage.clickOnMoreSetting(fileName);

		docLibPge.commonMethodForClickOnMoreSettingsOption(fileName, action);
		
		if(docLibPge.isOfflineLockMsgDisplayed()){
			report.updateTestLog(
					"Verify the user is able to lock a file",
					"File Locked successfully."
							+ "<br><b> Locked File Name </b> :"
							+ fileName, Status.PASS);
		}else{
			report.updateTestLog(
					"Verify the user is able to lock a file",
					"File not Locked."
							+ "<br><b> File Name </b> :"
							+ fileName, Status.FAIL);
		}
	
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folder2);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}