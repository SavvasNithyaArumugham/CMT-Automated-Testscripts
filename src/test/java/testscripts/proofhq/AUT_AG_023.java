package testscripts.proofhq;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_023 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void proofhq_11() {
		testParameters
				.setCurrentTestDescription("Verifying the alfresco edit offline feature for proof related files/folders is working properly");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);

		homePage.navigateToSitesTab();

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOption = dataTable.getData("MyFiles",
				"MoreSettingsOption");

		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {

		}

		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
			myFiles.uploadFile(filePath, fileName);
			myFilesTestObj.verifyUploadedFile(fileName, "");

			String moreSetOption1 = "", moreSetOption2 = "", moreSetOption3 = "";
			if (moreSettingsOption.contains(",")) {
				String splittedMoreSetOptions[] = moreSettingsOption.split(",");

				if (splittedMoreSetOptions != null
						&& splittedMoreSetOptions.length > 1) {
					moreSetOption1 = splittedMoreSetOptions[0];
					moreSetOption2 = splittedMoreSetOptions[1];
					moreSetOption3 = splittedMoreSetOptions[2];
				} else {
					moreSetOption1 = "Create Proof in ProofHQ";
					moreSetOption2 = "Edit Offline";
					moreSetOption3 = "Upload New Version";
				}
			} else {
				moreSetOption1 = "Create Proof in ProofHQ";
				moreSetOption2 = "Edit Offline";
				moreSetOption3 = "Upload New Version";
			}

			sitesPage.clickOnMoreSetting(fileName);
			sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(
					fileName, moreSetOption1);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,
					moreSetOption1);

			String recepients = dataTable.getData("MyFiles", "Recepients");
			String policy = dataTable.getData("MyFiles", "ProofHQPolicy");
			sitesPage.addProofHQ(recepients, policy, fileName);
			
			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			String fileNameUploadNewVersion = dataTable.getData("Document_Details", "FileName");
			String filePathUploadNewVersion = dataTable.getData("Document_Details", "FilePath");
			String versionDetails = dataTable.getData("Document_Details",
					"Version");
			String comments = dataTable.getData("Document_Details", "Comments");
			
			String finalFileName;
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0];
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}
			
			sitesPage.clickOnMoreSetting(fileName);
			new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,
					finalFileName);

			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSetOption2);
			
			new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, finalFileName);
			
			sitesPage.clickOnMoreSetting(fileName);
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSetOption3);
			
			docLibPg.uploadNewVersionFileInDocLibPage(fileNameUploadNewVersion,
					filePathUploadNewVersion, versionDetails, comments);
			
			myFiles.openUploadedOrCreatedFile(fileNameUploadNewVersion, "");

			docDetailsPageTest.verifyVersionHistoryHeader();
			
			String expectedOldVersionNoForFile = dataTable.getData("MyFiles", "Version");
			docDetailsPageTest.verifyOlderVersionForFile(expectedOldVersionNoForFile.replace("'", ""));
			
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPg.performReverVersionForFile();
			
			String expectedRevertedVersionNoForFile = dataTable.getData("Document_Details", "RevertedFileVersionNo");
			docDetailsPageTest.verifyRevertedVersionNoForFile(expectedRevertedVersionNoForFile);
			
		}
	}

	@Override
	public void tearDown() {

	}

}
