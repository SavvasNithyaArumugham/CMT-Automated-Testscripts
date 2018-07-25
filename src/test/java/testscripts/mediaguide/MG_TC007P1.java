package testscripts.mediaguide;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
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

public class MG_TC007P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify List View of Audio tabs are displayed with Name,Title and Reference ID columns in which Name column is non editable and Title, Reference ID columns are editable.<br>"
						+ "2.Verify List View of Video tabs are displayed with Name,Title and Reference ID columns in which Name column is non editable and Title, Reference ID columns are editable.<br>"
						+ "3.Verify only untranscoded audio files within the particular folder are displayed in Audio tab of Media Guide Interface.<br>"
						+ "4.Verify only untranscoded video files within the particular folder are displayed in the Video tab of Media Guide Interface.<br>"
						+ "5.Verify the number of 'Files Found' is displayed in the bottom, based on the files available in the selected tab of Media Guide Interface.");
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
		String validInputTitle[] = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb").split(",");
		String validReferenceID[] = dataTable.getData("MyFiles", "StatusReportValue").split(",");
		String editTabDetails[] = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		String columnName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");

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
		mediaGuidePg.VerifyTabsInMediaInterface();
		mediaGuidePgTest.verifyFilesCountMatchesInMediaGuideInterface();

		for (String mediaFiles1 : convertAction) {

			for (int i1 = 0; i1 < validInputTitle.length; i1++) {
				if (mediaFiles1.equals("audio")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
					mediaGuidePg.enterValidReferenceID(mediaFiles1, validReferenceID[i1]);
					mediaGuidePg.enterValidTitleForUntranscodedFiles(mediaFiles1, validInputTitle[i1]);

				} else if (mediaFiles1.equals("video")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
					mediaGuidePg.enterValidReferenceID(mediaFiles1, validReferenceID[1]);
					mediaGuidePg.enterValidTitleForUntranscodedFiles(mediaFiles1, validInputTitle[1]);

				}
				break;
			}
		}

		mediaGuidePg.verifyListView();
		for (String mediaFiles2 : convertAction) {

			mediaGuidePg.navigateToParticularTab(mediaFiles2);
			mediaGuidePgTest.verifyColumnNameListView(columnName, mediaFiles2);
			for (int field = 0; field < editTabDetails.length; field++) {
				mediaGuidePg.verifyEditActionForUnTranscodedListView(mediaFiles2, editTabDetails[field],
						validInputTitle[field]);
			}
		}
		mediaGuidePg.verifyGridView();

		for (String mediaFiles3 : convertAction) {
			mediaGuidePg.navigateToParticularTab(mediaFiles3);
			mediaGuidePgTest.verifyUntrancodedFilesInFolder(title, mediaFiles3);
		}

	}

	@Override
	public void tearDown() {

	}
}
