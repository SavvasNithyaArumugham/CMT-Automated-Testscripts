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
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC004 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify user is able to navigate to preview page of Audio by double-clicking the thumbnail or by clicking the portrait icon in the media guide interface. <br>"
						+ "2.Verify user is able to navigate to preview page of video by double-clicking the thumbnail or by clicking the portrait icon in the media guide interface.<br>"
						+ "3.Verify the user is switched to the All tab of the Media Guide Interface on selecting untranscoded audio and video files and on click of Convert Streaming Media button.<br>"
						+ "4.Verify Media guide option is displayed for MP folders containing both audio and video files");
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
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String convertAction[] = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String validInputTitle[] = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb").split(",");
		String tabDetails = dataTable.getData("MyFiles", "SelectDropdownValue");
		String verifyConfirmMessage = dataTable.getData("MyFiles", "PopUpMsg");
		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);

		myFiles.openFolder(title);
		for (String uploadFile : fileName) {
			myFiles.uploadFile(filePath, uploadFile);
		}
		docLibPg.navigateToDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);

		for (String mediaFiles : convertAction) {
			mediaGuidePg.VerifyTabsInMediaInterface();
			mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles);
			mediaGuidePg.doubleClickThumbnail();
			mediaGuidePg.verifyPreviewPageNavigated(mediaFiles);
			UIHelper.closeCurrentWindow(driver);
			UIHelper.switchtab(1, driver);

			mediaGuidePg.clickOnPortraitIcon();
			mediaGuidePg.verifyPreviewPageNavigated(mediaFiles);
			UIHelper.closeCurrentWindow(driver);
			UIHelper.switchtab(1, driver);
		}
		UIHelper.pageRefresh(driver);
		for (String mediaFiles1 : convertAction) {

			for (int i1 = 0; i1 < validInputTitle.length; i1++) {
				if (mediaFiles1.equals("audio")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
					mediaGuidePg.enterValidTitleForUntranscodedFiles(mediaFiles1, validInputTitle[i1]);
				} else if (mediaFiles1.equals("video")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
					mediaGuidePg.enterValidTitleForUntranscodedFiles(mediaFiles1, validInputTitle[1]);
				}
				break;
			}
		}
		mediaGuidePg.clickConvertToStreamMedia();
		mediaGuidePg.verifyValidationConversionMessage(verifyConfirmMessage);
		mediaGuidePg.verifyUserNavigatedToParticularTab(tabDetails);
	}

	@Override
	public void tearDown() {

	}
}
