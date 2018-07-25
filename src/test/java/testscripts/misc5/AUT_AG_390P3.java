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
public class AUT_AG_390P3 extends TestCase
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		String folder = dataTable.getData("MyFiles", "Version");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String folder2 = dataTable.getData("MyFiles", "RelationshipName");
		String action = dataTable.getData("MyFiles", "MoreSettingsOption");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String files = dataTable.getData("MyFiles", "Sort Options");
	
		functionalLibrary.loginAsValidUser(signOnPage);
		
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
	
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.deleteCreatedFolder(folder);
		
		
		if(!docLibPge.isFileIsAvailable(folder)){
			
			report.updateTestLog("Verify Manager can delete the own folder containing someone else content which is locked by some other user",
					"User able to delete a folder which has the locked files by other user. Folder Name : " +folder, Status.PASS);
			
		}else{
			
			report.updateTestLog("Verify Manager can delete the own folder containing someone else content which is locked by some other user",
					"User unable to delete a folder which has the locked files by other user Folder Name : " +folder, Status.FAIL);
		}
		
		myFiles.deleteCreatedFolder(folder1);
		
		if(!docLibPge.isFileIsAvailable(folder1)){
			
			report.updateTestLog("Verify Manager can delete the own folder containing someone else content which is locked by some other user",
					"User able to delete a folder which has the locked files by other user. Folder Name : " +folder1, Status.PASS);
			
		}else{
			
			report.updateTestLog("Verify Manager can delete the own folder containing someone else content which is locked by some other user",
					"User unable to delete a folder which has the locked files by other user. Folder Name : " +folder1, Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folder2);
		
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
		
		myFiles.deleteCreatedFolder(folder2);
		
		if(!docLibPge.isFileIsAvailable(folder2)){
			
			report.updateTestLog("Verify Manager can delete the own folder containing someone else content which is locked by some other user",
					"User able to delete a folder which has the locked files by other user. Folder Name : " +folder1, Status.PASS);
			
		}else{
			
			report.updateTestLog("Verify Manager can delete the own folder containing someone else content which is locked by some other user",
					"User unable to delete a folder which has the locked files by other user. Folder Name : " +folder1, Status.FAIL);
		}

		
		/*sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		docLibPge.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		
		sitesPage.documentdetails(folder);
		
		myFiles.uploadFileInMyFilesPage(filePath, files);
		
		sitesPage.documentdetails(fileName);
		
		
		docDetailsPageObj.Addcomment(fileName);
		
	
	
		docDetailsPageTest.Verifycomment();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		myFiles.deleteCreatedFolder(folder);
		
		if(!docLibPge.isFileIsAvailable(folder)){
			
			report.updateTestLog("Verify Manager can delete the own folder containing someone else content which is locked by some other user",
					"User able to delete a folder which has the locked files by other user. Folder Name : " +folder, Status.PASS);
			
		}else{
			
			report.updateTestLog("Verify Manager can delete the own folder containing someone else content which is locked by some other user",
					"User unable to delete a folder which has the locked files by other user. Folder Name : " +folder, Status.FAIL);
		}
		
	sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		myFiles.createFolder(folderDetails);
		
		sitesPage.documentdetails(folder);
		
		myFiles.uploadFileInMyFilesPage(filePath, files);
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.Verifycomment();
	*/
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}