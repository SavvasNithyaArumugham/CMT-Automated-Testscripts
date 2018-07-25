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

public class AVT_TC007 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_007() {
		testParameters.setCurrentTestDescription("18	Verify the user is able to upload the mp4 video file in alfresco using streaming media without uploading files for Related files tab fields and verify the JSON creation post -upload"
				+ "19	Verify the user is able to upload the m4v video file in alfresco using streaming media without uploading files for Related files tab fields and verify the JSON creation post -upload"
				+ "20	Verify the user is able to upload the .mov video file in alfresco using streaming media without uploading files for Related files tab fields and verify the JSON creation post- upload"
				+ "35	Verify user is displayed with a message in the upload files tab when the transcoding of media file is in progress or completed" + "" + "" + "");

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
		
		
//		upload video files and verify supported format for MOV file without related files
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MOV.mov");
		String movTitle=alfrescoAVTPage.inputTitle("MOV");
		alfrescoAVTPage.inputReferenceID(movTitle);
		alfrescoAVTPage.inputDescription(movTitle);
		alfrescoAVTPage.inpitKeyword(movTitle);
		alfrescoAVTPage.inputPlayListID(movTitle);
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();

		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		
//		upload video files and verify supported format for MP4 file without related files
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MP4.mp4");
		String mp4Title=alfrescoAVTPage.inputTitle("MP4");
		alfrescoAVTPage.inputReferenceID(mp4Title);
		alfrescoAVTPage.inputDescription(mp4Title);
		alfrescoAVTPage.inpitKeyword(mp4Title);
		alfrescoAVTPage.inputPlayListID(mp4Title);
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		
		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		
//		upload video files and verify supported format for M4V file without related files
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "M4V.m4v");
		String m4vTitle=alfrescoAVTPage.inputTitle("M4V");
		alfrescoAVTPage.inputReferenceID(m4vTitle);
		alfrescoAVTPage.inputDescription(m4vTitle);
		alfrescoAVTPage.inpitKeyword(m4vTitle);
		alfrescoAVTPage.inputPlayListID(m4vTitle);
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		
		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(m4vTitle);
		ArrayList<String> listOfFilesForM4V = new ArrayList<String>();
		listOfFilesForM4V=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();//withouterify Transcoding in progress in upload files tab
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.verifyPreviewTab();
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(movTitle);
		ArrayList<String> listOfFilesForMOV = new ArrayList<String>();
		listOfFilesForMOV=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.verifyPreviewTab();

		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(mp4Title);
		ArrayList<String> listOfFilesForMP4 = new ArrayList<String>();
		listOfFilesForMP4=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.verifyPreviewTab();




	}

	@Override
	public void tearDown() {

	}
}
