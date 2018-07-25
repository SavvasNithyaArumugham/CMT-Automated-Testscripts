package testscripts.movetocopyto;

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
 * 
 * @author Cognizant
 */
public class AUT_AG_001 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void movetocopyto_01() {
		testParameters.setCurrentTestDescription("1. Verify that user is able to move files to a different site"
				+ "<br>2. Verify that user is able to move folders to a different site"
				+ "<br>3. Verify that user is able to move a folder (Containing multiple files and folders) to a different site"
				+ "<br>4. Verify that user is able to perform move content from one site to other in same instance");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		// Verify that user is able to move files to a different site
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		String finalFileNameVal = "";
		String splittedFileNames[] = null;
		if (fileName.contains(",")) {
			splittedFileNames = fileName.split(",");

			if (splittedFileNames != null && splittedFileNames.length > 1) {
				finalFileNameVal = splittedFileNames[0];
			}
		} else {
			finalFileNameVal = fileName;
		}

		myFiles.uploadFileInMyFilesPage(filePath, finalFileNameVal);
		myFilesTestObj.verifyUploadedFile(finalFileNameVal, "");

		myFiles.openUploadedOrCreatedFile(finalFileNameVal, "");
		docDetailsPageTest.verifyUploadedFileIsOpened(finalFileNameVal, "");

		docDetailsPageObj.clickMoveToDocAction();
		docDetailsPageObj.selectFileInMovePopUp();

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();

		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
			myFilesTestObj.verifyMovedFileInTargetSite(finalFileNameVal, "");
		}

		// Verify that user is able to move folders to a different site
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);

		//myFiles.navigateToMyFilesContainer();
		sitesPage.moveFolderFromOneSiteToAnother();

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		myFilesTestObj.verifyMovedFolder(folderDetails);

		// Verify that user is able to move a folder (Containing multiple files
		// and folders) to a different site
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);

		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
			myFiles.createFolder(folderDetails);
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		}

		//myFiles.navigatTeMyFilesContainer();
		sitesPage.enterIntoDocumentLibrary();

		sitesPage.moveFolderFromOneSiteToAnother();

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		myFilesTestObj.verifyMovedFolder(folderDetails);

		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
			myFilesTestObj.verifyMovedFolder(folderDetails);

			if (splittedFileNames != null && splittedFileNames.length > 1) {
				for (String fileNameValue : splittedFileNames) {
					myFilesTestObj.verifyMovedFileInTargetSite(fileNameValue, "");
				}
			} else {
				myFilesTestObj.verifyMovedFileInTargetSite(finalFileNameVal, "");
			}
		}

		// Verify that user is able to perform move content from one site to
		// other in same instance from more settings option
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, finalFileNameVal);
		myFilesTestObj.verifyUploadedFile(finalFileNameVal, "");

		sitesPage.clickOnMoreSetting(finalFileNameVal);
		sitesPage.clickOnMoreOptionLink(finalFileNameVal);

		docLibPage.moveFileIntoAnotherSite(finalFileNameVal, targetSiteName);

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();

		docLibPgTest.verifyMovedFilesInDestinationSite(finalFileNameVal, targetSiteName);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}