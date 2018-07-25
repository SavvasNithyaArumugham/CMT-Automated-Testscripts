package testscripts.avt;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC020P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_001() {
		testParameters.setCurrentTestDescription(
				"1.Verify user is able to Replace source audio file having special character in the file name.");

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

		try {
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");

			String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/FolderNames.txt";
			String givenTitle = new FileUtil()
					.readDataFromFile(System.getProperty("user.dir") + "/" + testOutputFilePath);
			// Login alf cms
			functionalLibrary.loginAsValidUser(signOnPage);

			// From the site Type dropdown select 'Collaboration Site '.
			homePageObj.navigateToSitesTab();

			String siteName = sitesPage.getCreatedSiteName();
			sitesPage.openSiteFromRecentSites(siteName);

			// Navigate to document library
			sitesPage.enterIntoDocumentLibrary();

			myFiles.openFolder(givenTitle);
			alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
			alfrescoAVTPage.verifyeditStreamingPage();
			alfrescoAVTPage.clickUploadFilesTabFields();
			alfrescoAVTPage.verifyReplaceIcon();
			alfrescoAVTPage.uploadFileInReplaceIcon(filePath, fileName);
			alfrescoAVTPage.clickOnSaveAndUpload();
			alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
			alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
			alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
			alfrescoAVTPage.verifyPreviewTab();
			alfrescoAVTPage.clickUploadFilesTabFields();
			alfrescoAVTPage.verifySuccessTrancodeConfirmation();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void tearDown() {

	}
}
