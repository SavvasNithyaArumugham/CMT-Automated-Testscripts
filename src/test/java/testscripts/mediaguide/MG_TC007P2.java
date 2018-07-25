package testscripts.mediaguide;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
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

public class MG_TC007P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify List View of Streaming Audio tabs are displayed with Status, Name ,Title and Reference ID columns in which no columns are editable+"
						+ "<br>2.Verify List View of Streaming Video tabs are displayed with Status, Name ,Title and Reference ID columns in which no columns are editable."
						+ "<br>3.Verify only transcoded audio files within the particular folder are displayed in Streaming Audio tab of Media Guide Interface."
						+ "<br>4.Verify JSON files are created at the same location as the original audio files and named with the same filename as the original audio file but with the .json extension after transcoding the audio file."
						+ "<br>5.Verify JSON files are created at the same location as the original video files and named with the same filename as the original video file but with the .json extension after transcoding the video file."
						+ "<br>6.Verify transcoded Audio files are displayed successfully in Streaming Audio tabs respectively after transcoding is complete and leave the files where they were uploaded from Alfresco along with json file."
						+ "<br>7.Verify transcoded Video files are displayed successfully in Streaming Video tabs respectively after transcoding is complete and leave the files where they were uploaded from Alfresco along with json file");
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
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String convertAction[] = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String validInputTitle[] = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb").split(",");
		String validReferenceID[] = dataTable.getData("MyFiles", "StatusReportValue").split(",");
		String tabDetails[] = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		String verifyConfirmMessage = dataTable.getData("MyFiles", "PopUpMsg");
		String columnName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		String editTabDetails[] = dataTable.getData("MyFiles", "AccessToken").split(",");
		String jsonFileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		ArrayList<String> jsonFiles = new ArrayList<String>();

		functionalLibrary.loginAsValidUser(signOnPage);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);
		for (String mediaFiles1 : convertAction) {
			mediaGuidePg.VerifyTabsInMediaInterface();
			for (int i1 = 0; i1 < validInputTitle.length; i1++) {
				if (mediaFiles1.equals("audio")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
					mediaGuidePg.enterValidReferenceID(mediaFiles1, validReferenceID[1]);
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
		mediaGuidePg.verifyListView();

		for (String mediaFiles2 : tabDetails) {

			mediaGuidePg.navigateToParticularTab(mediaFiles2);
			mediaGuidePgTest.verifyColumnNameListView(columnName, mediaFiles2);
			for (int field = 0; field < editTabDetails.length; field++) {
				mediaGuidePg.verifyEditActionForTranscodedListView(mediaFiles2, editTabDetails[field],
						validInputTitle[field]);
			}
		}
		mediaGuidePg.verifyGridView();
		UIHelper.switchtab(0, driver);
		jsonFiles = myFiles.getFileNames(jsonFileDetails);
		mediaGuidePgTest.verifyTrancodedJsonFilesInFolder(jsonFiles, title);
		UIHelper.switchtab(1, driver);
		for (String mediaFiles4 : tabDetails) {

			mediaGuidePg.navigateToParticularTab(mediaFiles4);
			String expectedfileName = mediaGuidePgTest.verifyTrancodedFilesDisplayedInStreamingMedia(mediaFiles4);
			switch (mediaFiles4) {

			case "Streaming Audio":
				if (jsonFiles.get(0).replace(".json", ".mp3").equals(expectedfileName)) {
					report.updateTestLog(
							"Verify only transcoded files within the particular folder are displayed in " + mediaFiles4
									+ " Tab",
							"<b> TrancodedItem is: </b><br/>" + expectedfileName + "  successfully displayed in "
									+ mediaFiles4 + "Tab" + "<br/>",
							Status.PASS);
				} else {
					report.updateTestLog(
							"Verify only transcoded files within the particular folder are displayed in " + mediaFiles4
									+ " Tab",
							"<b> TrancodedItem is: </b><br/>" + expectedfileName + "  failed to displayed in "
									+ mediaFiles4 + "Tab" + "<br/>",
							Status.FAIL);
				}
				break;

			case "Streaming Video":
				if (jsonFiles.get(1).replace(".json", ".mp4").equals(expectedfileName)) {
					report.updateTestLog(
							"Verify only transcoded files within the particular folder are displayed in " + mediaFiles4
									+ " Tab",
							"<b> TrancodedItem is: </b><br/>" + expectedfileName + "  successfully displayed in "
									+ mediaFiles4 + "Tab" + "<br/>",
							Status.PASS);
				} else {
					report.updateTestLog(
							"Verify only transcoded files within the particular folder are displayed in " + mediaFiles4
									+ " Tab",
							"<b> TrancodedItem is: </b><br/>" + expectedfileName + "  failed to displayed in "
									+ mediaFiles4 + "Tab" + "<br/>",
							Status.FAIL);
				}
				break;
			}
		}
		for (String mediaFiles5 : tabDetails) {
			mediaGuidePg.waitUntillTrancodeComplete(mediaFiles5);
		}
	}

	@Override
	public void tearDown() {

	}
}
