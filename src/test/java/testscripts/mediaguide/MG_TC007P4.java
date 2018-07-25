package testscripts.mediaguide;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaGuidePage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaGuidePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC007P4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify a user can able to add extra Audio files in Audio tab after transcoding all the untranscoded audio files in streaming Audio tab"
						+ "<br>2.Verify a user can able to add extra Video files in Video tab after transcoding all the untranscoded Video files in streaming Video tab."
						+ "<br>3.Verify an alert dialog box is displayed if the Reference ID already exists within ThePlatform in Audio tab of Media Guide Interface."
						+ "<br>4.Verify an alert dialog box is displayed if the Reference ID already exists within ThePlatform in Video tab of Media Guide Interface."
						+ "<br>5.Verify Display dropdown is listed with 4,8,12,16 Results per page in the bottom of Media Guide Interface."
						+ "<br>6.Verify Audio files gets sorted based on the value selected in the Sort By drop down field present in the bottom of Media Guide interface");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMediaGuidePage mediaGuidePg = new AlfrescoMediaGuidePage(scriptHelper);
		AlfrescoMediaGuidePageTest mediaGuidePgTest = new AlfrescoMediaGuidePageTest(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String convertAction[] = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String validInputTitle = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String streamingTabDetails[] = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		String popUpMsg = dataTable.getData("MyFiles", "PopUpMsg");
		String fileDropdown = dataTable.getData("MyFiles", "CreateFileDetails");
		String sortDropdown = dataTable.getData("MyFiles", "AccessToken");
		String sortOptions[] = dataTable.getData("MyFiles", "Sort Options").split(",");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(title);
		for (String uploadFile : fileName) {
			myFiles.uploadFile(filePath, uploadFile);
		}
		docLibPg.navigateToDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);

		for (String mediaFiles1 : convertAction) {
			mediaGuidePg.VerifyTabsInMediaInterface();

			if (mediaFiles1.equals("audio")) {
				mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
				mediaGuidePg.verifyExtraFileAddedAfterTrancoding(mediaFiles1, fileName[0]);

			} else if (mediaFiles1.equals("video")) {
				mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
				mediaGuidePg.verifyExtraFileAddedAfterTrancoding(mediaFiles1, fileName[1]);
			}
		}
		mediaGuidePg.navigateToParticularTab(streamingTabDetails[0]);
		String existsReference = mediaGuidePg.getReferenceIDFromMediaInterface();
		for (String mediaFiles3 : convertAction) {
			mediaGuidePg.navigateToParticularTab(mediaFiles3);
			mediaGuidePg.verifyAlertForExistingReferenceID(mediaFiles3, existsReference, popUpMsg);
		}

		mediaGuidePg.navigateToParticularTab(streamingTabDetails[2]);
		mediaGuidePgTest.getFileDisplayDropDownInMedia(fileDropdown);
		UIHelper.switchtab(0, driver);
		myFiles.openFolder(title);
		for (int fileCount = 0; fileCount < 4; fileCount++) {
			myFiles.uploadFile(filePath, fileName[0]);
		}

		UIHelper.switchtab(1, driver);
		UIHelper.pageRefresh(driver);
		mediaGuidePg.navigateToParticularTab(convertAction[0]);
		mediaGuidePg.clickOnSelectAllCheckbox(convertAction[0]);
		mediaGuidePg.enterValidTitleForUntranscodedMultipleFiles(convertAction[0], validInputTitle);
		mediaGuidePg.navigateToParticularTab(streamingTabDetails[2]);
		mediaGuidePgTest.selectSecondIndexOfFileDisplay(1);
		mediaGuidePgTest.verifyFilesCountMatchesInMediaGuideInterface();
		mediaGuidePg.navigateToParticularTab(convertAction[0]);
		mediaGuidePgTest.getSortByDisplayDropDownInMedia(sortDropdown);
		mediaGuidePgTest.selectMoreDropdown();
		mediaGuidePgTest.verifyFileNameSorted(sortOptions[0], convertAction[0]);
		mediaGuidePgTest.verifyTitleNameSorted(sortOptions[1], convertAction[0]);
	}

	@Override
	public void tearDown() {

	}
}
