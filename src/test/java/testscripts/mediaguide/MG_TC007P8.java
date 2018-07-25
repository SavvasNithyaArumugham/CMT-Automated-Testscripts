package testscripts.mediaguide;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import java.util.ArrayList;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaGuidePage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaGuidePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC007P8 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify Transcoded Audio/Video, Untranscoded Audio/Video files within the particular folder are displayed in ALL tab of Media Guide Interface.");
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
		String tabDetails[] = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		ArrayList<String> actualFiles = new ArrayList<String>();
		ArrayList<String> expectedFiles = new ArrayList<String>();

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		actualFiles = mediaGuidePgTest.getAllFilesInMediaFolder(title);
		docLibPg.navigateToDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);
		mediaGuidePg.VerifyTabsInMediaInterface();
		mediaGuidePg.navigateToParticularTab(tabDetails[2]);
		mediaGuidePgTest.selectMoreDropdown();
		expectedFiles = mediaGuidePg.getListOfUnTrancodedFiles();
		try {
			if (UIHelper.compareTwoSimilarLists(actualFiles, expectedFiles)) {
				report.updateTestLog(
						"Verify Transcoded Audio/Video,Untranscoded Audio/Video files within the particular folder : <b>"
								+ title + " </b>  should be displayed in ALL tab",
						"Transcoded & UnTranscoded Audio/Video files with in folder :<b>" + title
								+ " </b>  is  successfully displayed in ALL Tab",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Transcoded Audio/Video,Untranscoded Audio/Video files within the particular folder : <b>"
								+ title + " </b>  should be displayed in ALL tab",
						"Transcoded & UnTranscoded Audio/Video files with in folder :<b>" + title
								+ " </b>  is  failed to displayed in ALL Tab",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify Transcoded Audio/Video,Untranscoded Audio/Video files within the particular folder : <b>"
							+ title + " </b>  should be displayed in ALL tab",
					"Transcoded & UnTranscoded Audio/Video files with in folder :<b>" + title
							+ " </b>  is  failed to displayed in ALL Tab",
					Status.FAIL);
		}

	}

	@Override
	public void tearDown() {

	}
}
