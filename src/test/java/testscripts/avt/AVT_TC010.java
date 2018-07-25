package testscripts.avt;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC010 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_010() {
		testParameters
				.setCurrentTestDescription( "46		Alfresco to create necessary objects to upload files and metadata.Create relationships between the JSON and the related files"
						+ "42	Verify user is able to delete the uploaded video file via Edit streaming media screen and check still the streaming media folder and JSON file exist in alfresco"
						+ "");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");


		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

// 		From the site Type dropdown select 'Collaboration Site '.
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		
//		create streaming media
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MOV.mov");
		String movTitle=alfrescoAVTPage.inputTitle("MOV");
		alfrescoAVTPage.inputReferenceIDwithoutRandomNumber(movTitle);
		alfrescoAVTPage.inputDescription(movTitle);
		alfrescoAVTPage.inpitKeyword(movTitle);
		alfrescoAVTPage.inputPlayListID(movTitle);
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "PNG.png");
		alfrescoAVTPage.uploadAndVerifyValidChapteringXML(filePath, "ChapteringXML.xml");
		alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt",	"English");
		alfrescoAVTPage.addCaptionsFile(filePath, "stat13t_sl_10_03_es.srt", "Spanish");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTT.vtt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTTS.vtt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();

		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		
//		verify relationship between json and streaming media objects		
		myFiles.openCreatedFolder(movTitle+".json");
		alfrescoAVTPage.verifyRelatedFilesToTheJson();
		driver.navigate().back();
		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		ArrayList<String> beforeDeleteVideo = new ArrayList<String>();
		ArrayList<String> afterDeleteVideo = new ArrayList<String>();
		beforeDeleteVideo=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.deleteVideoInStreamingPageAndVerifyStreamingContentInStreamingFolder();
		afterDeleteVideo=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		report.updateTestLog("Before delete video on upload files Tab " +beforeDeleteVideo, "After delete video on upload files Tab "+afterDeleteVideo,
				Status.DONE);
	}

	@Override
	public void tearDown() {

	}
}
