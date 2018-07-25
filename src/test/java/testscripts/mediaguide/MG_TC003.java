package testscripts.mediaguide;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaGuidePage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaGuidePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC003 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify Video, Streaming Video, Audio, Streaming Audio and All tabs are displayed in the Media Guide Interface. <br>"
						+ "2.Verify user is able to select Audio files using checkbox or Select All button to transcode the streaming medias. <br>"
						+ "3.Verify user is able to select Video files using checkbox or Select All button to transcode streaming medias. <br>"
						+ "4.Verify View toggle button is displayed with Listview and Gridview at right side of Media Guide Interface. <br>"
						+ "5.Verify on clicking 'Convert to Streaming Media' button, error message is displayed when user provides value with unsupported characters other than alphanumeric/spaces/underscore for the Title field in audio tab of Media Guide Interface.<br>"
						+ "6.Verify on clicking 'Convert to Streaming Media' button, error message is displayed when user provides value with unsupported characters other than alphanumeric/spaces/underscore for the Title field in Video tab of Media Guide Interface.<br>"
						+ "7.Verify on clicking 'Convert to Streaming Media' button, error message is displayed when user provides value with unsupported characters other than alphanumeric/spaces/underscore for the Reference ID field in Video tab of Media Guide Interface.<br>"
						+ "8.Verify on clicking 'Convert to Streaming Media' button, error message is displayed when user provides value with unsupported characters other than alphanumeric/spaces/underscore for the Reference ID field in Audio tab of Media Guide Interface.<br>"
						+ "9.Verify on clicking 'Convert to Streaming Media' button, warning message is displayed if user does not provide the value for Title field in Video tab of Media Guide Interface.<br>"
						+ "10.Verify on clicking 'Convert to Streaming Media' button, warning message is displayed if user does not provide the value for Title field in audio tab of Media Guide Interface.<br>"
						+ "11.Verify Reference id is auto populated with Title in Streaming Audio tab after transcoding, if user does not provide any value for Reference id field in Audio tab of Media Guide Interface.<br>"
						+ "12.Verify Reference id is auto populated with Title in Streaming Video tab after transcoding, if user does not provide any value for the Reference id field in Video tab of Media Guide Interface.<br>"
						+ "13.Verify user is able to switch between list View or Grid view using View toggle.<br>");
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
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String convertAction[] = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String toggleItemsInMedia[] = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		String verifyErrorMessage[] = dataTable.getData("MyFiles", "PopUpMsg").split(",");
		String titleValidation = dataTable.getData("MyFiles", "AccessToken");
		String validInputTitle[] = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb").split(",");

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
			mediaGuidePg.clickOnSelectAllCheckbox(mediaFiles);

		}
		mediaGuidePg.verifyToggleButton();
		for (String toggleItemInMedia : toggleItemsInMedia) {
			mediaGuidePgTest.verifyToggleDropdown(toggleItemInMedia);
		}
		for (String mediaFiles1 : convertAction) {
			mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
			mediaGuidePg.verifyErrorValidationInTitle(mediaFiles1, titleValidation, verifyErrorMessage[0]);
			mediaGuidePg.verifyErrorValidationInReferenceID(mediaFiles1, titleValidation, verifyErrorMessage[0]);
			mediaGuidePg.verifyBlankErrorValidationInTitle(mediaFiles1, verifyErrorMessage[0]);
		}
		UIHelper.pageRefresh(driver);

		for (String mediaFiles2 : convertAction) {

			for (int i1 = 0; i1 < validInputTitle.length; i1++) {
				if (mediaFiles2.equals("audio")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles2);
					mediaGuidePg.verifyReferenceIDisAutoPopulated(mediaFiles2, validInputTitle[i1],
							verifyErrorMessage[1], fileName[i1]);

				} else if (mediaFiles2.equals("video")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles2);
					mediaGuidePg.verifyReferenceIDisAutoPopulated(mediaFiles2, validInputTitle[1],
							verifyErrorMessage[1], fileName[1]);

				}
				break;

			}
		}
		mediaGuidePg.verifyListView();
		mediaGuidePg.verifyGridView();

	}

	@Override
	public void tearDown() {

	}
}
