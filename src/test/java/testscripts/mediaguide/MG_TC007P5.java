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

public class MG_TC007P5 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify Video files gets sorted based on the value selected in the 'Sort By' drop down field present in the bottom of Media Guide interface");
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
		String fileName = dataTable.getData("MyFiles", "FileName");
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String convertAction = dataTable.getData("MyFiles", "BrowseActionName");
		String validInputTitle = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String sortDropdown = dataTable.getData("MyFiles", "AccessToken");
		String sortOptions[] = dataTable.getData("MyFiles", "Sort Options").split(",");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(title);
		for (int fileCount = 0; fileCount < 3; fileCount++) {
			myFiles.uploadFile(filePath, fileName);
		}
		docLibPg.navigateToDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);
		mediaGuidePg.VerifyTabsInMediaInterface();
		mediaGuidePg.navigateToParticularTab(convertAction);
		mediaGuidePg.clickOnSelectAllCheckbox(convertAction);
		mediaGuidePg.enterValidTitleForUntranscodedMultipleFiles(convertAction, validInputTitle);
		mediaGuidePgTest.getSortByDisplayDropDownInMedia(sortDropdown);
		mediaGuidePgTest.selectMoreDropdown();
		mediaGuidePgTest.verifyFileNameSorted(sortOptions[0], convertAction);
		mediaGuidePgTest.verifyTitleNameSorted(sortOptions[1], convertAction);
	}

	@Override
	public void tearDown() {

	}
}
