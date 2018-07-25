package testscripts.avt;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC022 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_001() {
		testParameters.setCurrentTestDescription(
				"1.Verify user is able to delete the uploaded source audio file via Edit streaming media screen and check still the streaming media folder and JSON file exist in Document Lib");

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
			AlfrescoMyFilesPage myFilePageObj = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			AlfrescoDocumentLibPageTest docLibTestPg = new AlfrescoDocumentLibPageTest(scriptHelper);
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

			// click on streaming media
			myFilePageObj.openFolder(givenTitle);
			alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
			alfrescoAVTPage.clickUploadFilesTabFields();
			alfrescoAVTPage.deleteAudioInStreamingPageAndVerifyStreamingContentInStreamingFolder();
			docLibPg.navigateToDocumentLibrary();
			myFilePageObj.openFolder(givenTitle);
			docLibTestPg.verifyDocumentDeleted();
			alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
			alfrescoAVTPage.verifyeditStreamingPage();
			alfrescoAVTPage.verifyAudioFileNotAvailableInUploadTab();
			alfrescoAVTPage.verifyPreviewTabNegative();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void tearDown() {

	}
}
