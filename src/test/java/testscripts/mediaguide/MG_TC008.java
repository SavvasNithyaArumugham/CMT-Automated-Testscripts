package testscripts.mediaguide;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaGuidePage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaGuidePageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC008 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify a user can transcode Audio files in the external shared folder by clicking media guide option in the more options."
						+ "<br> 2.Verify a user can transcode video files in the external shared folder by clicking media guide option in the more options.");
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
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String sharefolder = dataTable.getData("MyFiles", "CreateFileDetails");
		String convertAction[] = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String validInputTitle[] = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb").split(",");
		String validReferenceID[] = dataTable.getData("MyFiles", "StatusReportValue").split(",");
		String tabDetails[] = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		String verifyConfirmMessage = dataTable.getData("MyFiles", "PopUpMsg");

		String shareProperties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");

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
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, sharefolder);
		shareboxPg.commonMethodToEnterSharingProperties(shareProperties, message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		docLibPg.navigateToDocumentLibrary();
		mediaGuidePg.checkShareFolder(title);
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);
		for (String mediaFiles1 : convertAction) {
			mediaGuidePg.VerifyTabsInMediaInterface();
			for (int i1 = 0; i1 < validInputTitle.length; i1++) {
				if (mediaFiles1.equals("audio")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
					mediaGuidePg.enterValidReferenceID(mediaFiles1, validReferenceID[i1]);
					mediaGuidePg.trancodingMediaFiles(mediaFiles1, validInputTitle[i1], verifyConfirmMessage);
					mediaGuidePg.verifyUserNavigatedToParticularTab(tabDetails[0]);

				} else if (mediaFiles1.equals("video")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
					mediaGuidePg.enterValidReferenceID(mediaFiles1, validReferenceID[1]);
					mediaGuidePg.trancodingMediaFiles(mediaFiles1, validInputTitle[1], verifyConfirmMessage);
					mediaGuidePg.verifyUserNavigatedToParticularTab(tabDetails[1]);
				}
				break;
			}
		}
		for (String mediaFiles5 : tabDetails) {
			mediaGuidePg.waitUntillTrancodeComplete(mediaFiles5);
			mediaGuidePgTest.verifySuccessStatusIcons(mediaFiles5);
		}
	}

	@Override
	public void tearDown() {

	}
}
