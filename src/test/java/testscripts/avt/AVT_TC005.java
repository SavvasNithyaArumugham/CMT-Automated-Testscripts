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

public class AVT_TC005 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_005() {
		testParameters.setCurrentTestDescription("14	Verify user is able to upload multiple files for caption field in Related files section"
				+ "21	Verify the user is able to upload the .mp4 video file in alfresco using streaming media by uploading files for Related files tab fields and verify the JSON creation post- upload"
				+ "22	Verify the user is able to upload the .m4v video file in alfresco using streaming media by uploading files for Related files tab fields and verify the JSON creation post upload"
				+ "23	Verify the user is able to upload the .mov video file in alfresco using streaming media by uploading files for Related files tab fields and verify the JSON creation post upload"
				+ "26	Verify the content of the streaming folder contains the appropriate Related files" 
				+ "31	Verify preview tab is available after successful upload of the video file"
						+ "" + "" + "" + "");

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

		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// From the site Type dropdown select 'Collaboration Site '.
		homePageObj.navigateToSitesTab();
		String siteName = sitesPage.getCreatedSiteName();
		// String siteName = "AutoAVTRandomSite50517074206";
		sitesPage.openSiteFromRecentSites(siteName);

		// enter document library
		
		
//		upload video files and verify supported format for MOV file with related files
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
		alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt",	"Spanish");
		alfrescoAVTPage.addCaptionsFile(filePath, "stat13t_sl_10_03_es.srt", "Spanish");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTT.vtt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTTS.vtt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();

		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		
//		upload video files and verify supported format for MP4 file with related files
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MP4.mp4");
		String mp4Title=alfrescoAVTPage.inputTitle("MP4");
		alfrescoAVTPage.inputReferenceIDwithoutRandomNumber(mp4Title);
		alfrescoAVTPage.inputDescription(mp4Title);
		alfrescoAVTPage.inpitKeyword(mp4Title);
		alfrescoAVTPage.inputPlayListID(mp4Title);
		alfrescoAVTPage.verifyRelatedFilesTabFields();		
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "GIF.gif");
		alfrescoAVTPage.uploadAndVerifyValidChapteringXML(filePath, "ChapteringXML.xml");
		alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt",	"Spanish");
		alfrescoAVTPage.addCaptionsFile(filePath, "stat13t_sl_10_03_es.srt", "Spanish");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTT.vtt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTTS.vtt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		
		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		
//		upload video files and verify supported format for M4V file with related files
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "M4V.m4v");
		String m4vTitle=alfrescoAVTPage.inputTitle("M4V");
		alfrescoAVTPage.inputReferenceIDwithoutRandomNumber(m4vTitle);
		alfrescoAVTPage.inputDescription(m4vTitle);
		alfrescoAVTPage.inpitKeyword(m4vTitle);
		alfrescoAVTPage.inputPlayListID(m4vTitle);
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "JPG.jpg");
		alfrescoAVTPage.uploadAndVerifyValidChapteringXML(filePath, "ChapteringXML.xml");
		alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt",	"Spanish");
		alfrescoAVTPage.addCaptionsFile(filePath, "stat13t_sl_10_03_es.srt", "Spanish");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTT.vtt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTTS.vtt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		
		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(movTitle);
		ArrayList<String> listOfFilesForMOV = new ArrayList<String>();
		listOfFilesForMOV=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFieldsAfterTranscoding();
		alfrescoAVTPage.verifyPreviewTab();

		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(mp4Title);
		ArrayList<String> listOfFilesForMP4 = new ArrayList<String>();
		listOfFilesForMP4=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFieldsAfterTranscoding();
		alfrescoAVTPage.verifyPreviewTab();

		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(m4vTitle);
		ArrayList<String> listOfFilesForM4V = new ArrayList<String>();
		listOfFilesForM4V=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFieldsAfterTranscoding();
		alfrescoAVTPage.verifyPreviewTab();


	}

	@Override
	public void tearDown() {

	}
}
