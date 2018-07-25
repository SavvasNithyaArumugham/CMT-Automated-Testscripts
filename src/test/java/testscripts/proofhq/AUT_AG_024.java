package testscripts.proofhq;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_024 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void proofhq_12() {
		testParameters
				.setCurrentTestDescription("Verifying the alfresco download feature for proof related files/folders is working properly");

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

		homePage.navigateToSitesTab();

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOption = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		String browseOption = dataTable.getData("MyFiles", "BrowseActionName");

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

			sitesPage.clickOnMoreSetting(fileName);
			sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(
					fileName, moreSettingsOption);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,
					moreSettingsOption);

			String recepients = dataTable.getData("MyFiles", "Recepients");
			String policy = dataTable.getData("MyFiles", "ProofHQPolicy");
			sitesPage.addProofHQ(recepients, policy, fileName);

			String fileDownloadPath = properties
					.getProperty("DefaultDownloadPath");

			String finalFileName;
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0];
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}

			new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,
					finalFileName);

			sitesPage.commonMethodForPerformBrowseOption(finalFileName,
					browseOption);

			new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath,
					finalFileName);

			File downloadedFile = new File(fileDownloadPath + "/"
					+ finalFileName);
			if (downloadedFile.exists()) {
				report.updateTestLog("Verify download 'Proof HQ' file",
						"'Proof HQ'File: " + finalFileName
								+ " downloaded sucessfully", Status.PASS);
			} else {
				report.updateTestLog("Verify download 'Proof HQ' file",
						"'Proof HQ'File: " + finalFileName
								+ " failed to download", Status.FAIL);
			}
		}
	}

	@Override
	public void tearDown() {

	}

}
