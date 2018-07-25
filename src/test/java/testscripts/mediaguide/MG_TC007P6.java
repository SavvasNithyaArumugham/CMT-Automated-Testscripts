package testscripts.mediaguide;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaGuidePage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaGuidePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC007P6 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify Streaming Audio files gets sorted based on the value selected in the 'Sort By' drop down field present in the bottom of Media Guide interface."
						+ "<br>2.Verify Streaming Video files gets sorted based on the value selected in the 'Sort By' drop down field present in the bottom of Media Guide interface."
						+ "<br>3.Verify user is able to filter the Streaming videos in Listview using Status Filter available in the left panel."
						+ "<br>4.Verify user is able to filter the Streaming Audios in Listview using Status Filter available in the left panel."
						+ "<br>5.Verify Status Filter for the Streaming Videos are displayed with Success, In progress."
						+ "<br>6.Verify Status Filter for the Streaming Audios are displayed with Success, In progress.");
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

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMediaGuidePage mediaGuidePg = new AlfrescoMediaGuidePage(scriptHelper);
		AlfrescoMediaGuidePageTest mediaGuidePgTest = new AlfrescoMediaGuidePageTest(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String convertAction[] = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String validInputTitle = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String tabDetails[] = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		String verifyConfirmMessage = dataTable.getData("MyFiles", "PopUpMsg");
		String statusFilterDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String sortDropdown = dataTable.getData("MyFiles", "AccessToken");
		String sortOptions[] = dataTable.getData("MyFiles", "Sort Options").split(",");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);
		mediaGuidePg.VerifyTabsInMediaInterface();
		for (String mediaFiles2 : tabDetails) {

			mediaGuidePg.navigateToParticularTab(mediaFiles2);
			mediaGuidePgTest.verifyStatusFilterInLeftPanel(statusFilterDetails, mediaFiles2);

		}

		for (String mediaFiles1 : convertAction) {
			mediaGuidePg.VerifyTabsInMediaInterface();
			if (mediaFiles1.equals("audio")) {

				mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
				mediaGuidePg.trancodingMediaFiles(mediaFiles1, validInputTitle, verifyConfirmMessage);
				mediaGuidePg.verifyUserNavigatedToParticularTab(tabDetails[0]);
				// mediaGuidePg.waitUntillInProgressIcon(tabDetails[0]);

				mediaGuidePg.verifyListView();
				mediaGuidePg.clickStatusFilterLeftPanel();
				mediaGuidePg.clickInProgressFilterCheckbox();
				mediaGuidePgTest.verifyInprogressIcon(tabDetails[0]);

			} else if (mediaFiles1.equals("video")) {
				mediaGuidePg.verifyGridView();
				mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
				mediaGuidePg.trancodingMediaFiles(mediaFiles1, validInputTitle, verifyConfirmMessage);
				mediaGuidePg.verifyUserNavigatedToParticularTab(tabDetails[1]);
				// mediaGuidePg.waitUntillInProgressIcon(tabDetails[1]);
				mediaGuidePg.verifyListView();
				mediaGuidePg.clickStatusFilterLeftPanel();
				mediaGuidePg.clickInProgressFilterCheckbox();
				mediaGuidePgTest.verifyInprogressIcon(tabDetails[1]);

			}

		}
		UIHelper.pageRefresh(driver);
		mediaGuidePg.verifyListView();
		for (String mediaFiles5 : tabDetails) {
			mediaGuidePg.navigateToParticularTab(mediaFiles5);
			mediaGuidePg.clickStatusFilterLeftPanel();
			mediaGuidePg.clickSuccessFilterCheckbox();
			mediaGuidePgTest.verifySuccessIcon(mediaFiles5);
			UIHelper.waitFor(driver);
		}

		mediaGuidePg.verifyGridView();
		for (String mediaFiles6 : tabDetails) {
			mediaGuidePgTest.getSortByDisplayDropDownInMedia(sortDropdown);
			mediaGuidePgTest.verifyFileNameSorted(sortOptions[0], mediaFiles6);
			mediaGuidePgTest.verifyTitleNameSorted(sortOptions[1], mediaFiles6);
		}

	}

	@Override
	public void tearDown() {

	}
}
