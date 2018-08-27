package testscripts.sanity.usppewip;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_033 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_131() {
		testParameters.setCurrentTestDescription(
				"1. Verify that user is able to Delete a file (OR) Verify that user is able to delete content from the site"
						+ "<br>2. Verify that user is able to Delete a folder in site"
						+ "<br>3. Verify that user is able to Delete a folder containing multiple files and folders");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		homePageObj.navigateToSitesTab();

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		docLibPage.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName, "");

		myFiles.deleteUploadedFile(fileName);

		//1. Verify that user is able to Delete a file (OR) Verify that user is able to delete content from the site
		if (!(docLibPage.isFileIsAvailable(fileName))) {
			report.updateTestLog("Check file is Deleted",
					"File deleted successfully" + "<br /><b>File Name : </b>" + fileName, Status.PASS);
		} else {
			report.updateTestLog("Check file is Deleted", "File not deleted" + "<br /><b>File Name : </b>" + fileName,
					Status.FAIL);
		}

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		//Verify that user is able to Delete a folder in site
		myFiles.deleteCreatedFolder(folderDetails);
		myFilesTestObj.verifyDeletedFolder(folderDetails);

		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);

		//Verify that user is able to Delete a folder containing multiple files and folders
		String fileNames = dataTable.getData("MyFiles", "CreateFileDetails");
		String childFolderDetails = dataTable.getData("MyFiles", "CreateChildFolder");
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
			myFiles.createFolder(childFolderDetails);
			myFiles.uploadFileInMyFilesPage(filePath, fileNames);
			myFilesTestObj.verifyUploadedMultipleFiles(fileNames);
		}

		sitesPage.backFromFolderToMySiteDocuments();
		myFiles.deleteCreatedFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolderIsDeleted(folderDetails);

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}