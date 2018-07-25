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

public class AVT_TC003 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_003() {
		testParameters
				.setCurrentTestDescription("45	Verify streaming folder is created even if only title field is provided without uploading the source video file and also verify user is able to upload the video file through Edit streaming screen<br>"
						+ "46	Verify user is able to update the editable metadata fields and check save and close button works in any one of the tabs in streaming media screen<br>"
						+ "43	Verify that indication bar is needed to keep the user appraised of the progress of the upload.Default to 1% in the progress bar upon uploading a file<br>"
						+ "27	Verify that the JSON file is also created within the streaming folder<br>"
						+ "25	Verify Streaming media folder is created in alfresco for the streaming package named with the user supplied title during upload<br>"
						+ "30	Verify streaming media option is available for JSON file available in the streaming media folder<br>");

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
//		String siteName = "AutoAVTRandomSite50517074206";
		sitesPage.openSiteFromRecentSites(siteName);


		
		
//		input title only
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		String givenTitle=alfrescoAVTPage.inputTitle("onlyTitle");
		alfrescoAVTPage.clickOnSaveAndUpload();		
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnMetaDataSubmittedPromptBox();
		sitesPage.enterIntoDocumentLibrary();
		ArrayList<String> FilesAndFoldersListFromDocumentLibrary= alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.verifyStreamingMediaFolderNameAndTitle(givenTitle,FilesAndFoldersListFromDocumentLibrary);
		
//		upload new video after meta data submit completes
		myFiles.openCreatedFolder(givenTitle);
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();//verify streaming media in availble json file 
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MOV.mov");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		FilesAndFoldersListFromDocumentLibrary= alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.verifyStreamingMediaFolderNameAndTitle("MOV.mov",FilesAndFoldersListFromDocumentLibrary);

		
//		update editable fields in streaming media
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.inputDescription(givenTitle);
		alfrescoAVTPage.inpitKeyword(givenTitle);
		alfrescoAVTPage.inputPlayListID(givenTitle);
		alfrescoAVTPage.clickOnSaveAndUpload();


	}

	@Override
	public void tearDown() {

	}
}
