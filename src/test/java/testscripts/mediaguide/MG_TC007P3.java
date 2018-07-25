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

public class MG_TC007P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify a user can able to add related files (thumbnail files,Caption files and XML files ) in the Edit streaming media page after double clicking transcoded Audio file in streaming Audio page.<br>"
						+ "2.Verify a user can able to add related files (thumbnail files,Caption files and XML files ) in the Edit streaming media page after double clicking transcoded Video file in streaming Video page.<br>"
						+ "3.Verify the default tab of Media Guide Interface shows the Video tab with all the untranscoded video files within that particular folder location.");
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
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String tabDetails[] = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		String relativeUploadFiles[] = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb").split(",");

		functionalLibrary.loginAsValidUser(signOnPage);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);
		mediaGuidePg.VerifyTabsInMediaInterface();
		mediaGuidePg.verifyDefaultTab();

		for (String mediaFiles2 : tabDetails) {

			mediaGuidePg.navigateToParticularTab(mediaFiles2);
			mediaGuidePg.doubleClickThumbnail();
			if (mediaFiles2.equals("Streaming Audio"))
				mediaGuidePg.verifyRelatedFilesInStreamAudio();
			else if (mediaFiles2.equals("Streaming Video")) {
				mediaGuidePgTest.verifyRelatedFilesAddedInStreamingVideo(relativeUploadFiles, filePath, fileName,
						title);
			}
			UIHelper.closeCurrentWindow(driver);
			UIHelper.switchtab(1, driver);

		}
	}

	@Override
	public void tearDown() {

	}
}
