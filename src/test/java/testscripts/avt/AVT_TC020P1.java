package testscripts.avt;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC020P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_001() {
		testParameters.setCurrentTestDescription("1.To Verify UI for Replace Icon."
				+ "<br>2. Verify user is able to cancel the details entered for Upload file tab on clicking Cancel button in Create Streaming media screen-upload file tab."
				+ "<br>3.Verify streaming folder is created even if only title field is provided without uploading the source audio file "
				+ "and also verify user is able to upload the audio file through Edit streaming screen"
				+ "<br> Message in preview tab"
				+ "<br>3.Verify streaming media option is available for JSON file available in the created streaming media folder."
				+ "<br>4.Verify the content of the streaming folder contains the appropriate files and the JSON file on uploading audio type file in Create Streaming Media screen-Upload file tab."
				+ "<br> 5.Verify user is able to Replace source audio file with .pdf , .jpeg and .doc files");

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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String unSupportFile[] = dataTable.getData("MyFiles", "CreateFileDetails").split(",");

		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// From the site Type dropdown select 'Collaboration Site '.
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		// Navigate to document library
		sitesPage.enterIntoDocumentLibrary();

		// click on streaming media
		alfrescoAVTPage.clickOnStreamingMedia();

		// verify upload files tab's fields
		alfrescoAVTPage.verifyUploadFilesTabFields();

		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MOV.mov");
		alfrescoAVTPage.verifyReplaceIconNegativeBeforeTranscoding();
		alfrescoAVTPage.clickOnCancle();
		UIHelper.waitFor(driver);

		if (siteName.equals(sitesPage.getSitePageTitle())) {
			report.updateTestLog("Verify user navigated to Document library screen after clicking Cancel Button",
					"user navigated to Document library screen successfully", Status.PASS);
		} else {
			report.updateTestLog("Verify user navigated to Document library screen after clicking Cancel Button",
					"user failed to navigated to Document library screen", Status.FAIL);
		}

		// click on streaming media
		alfrescoAVTPage.clickOnStreamingMedia();
		String givenTitle = alfrescoAVTPage.inputTitle("MOV");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnMetaDataSubmittedPromptBox();

		docLibPg.navigateToDocumentLibrary();
		ArrayList<String> FilesAndFoldersListFromDocumentLibrary = alfrescoAVTPage
				.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.verifyStreamingMediaFolderNameAndTitle(givenTitle, FilesAndFoldersListFromDocumentLibrary);
		myFiles.openFolder(givenTitle);

		sitesPage.clickOnMoreSetting(givenTitle + "." + "json");
		sitesPageTestObj.verifyMoreSettingsOptionForFileOrFolderItem(givenTitle + "." + "json", "Streaming Media");
		alfrescoAVTPage.commonMethodForClickOnMoreSettingsOption(givenTitle + "." + "json", "Streaming Media");

		alfrescoAVTPage.verifyeditStreamingPage();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, fileName);
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyPreviewTab();
		alfrescoAVTPage.clickUploadFilesTabFields();
		alfrescoAVTPage.verifySuccessTrancodeConfirmation();
		// Replacing .jpeg,.pdf , .doc file verification
		alfrescoAVTPage.verifyReplaceIcon();
		for (String inapp : unSupportFile) {
			alfrescoAVTPage.uploadFileInReplaceIconNegative(filePath, inapp);
		}

	}

	@Override
	public void tearDown() {

	}
}
