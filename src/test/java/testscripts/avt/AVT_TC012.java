package testscripts.avt;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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

public class AVT_TC012 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_12() {
		testParameters
				.setCurrentTestDescription("48	Verify the newly added caption file is placed in the streaming media folder if user add new caption file via JSON after moving the source video file and thumbnail from streaming media folder to any other folder"
						+ "50	Verify the newly added caption file is placed in the streaming media folder if user add new caption file via JSON after moving the entire streaming media folder content to any other folder");

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
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
//		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");


		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

// 		From the site Type dropdown select 'Collaboration Site '.
		//homePageObj.navigateToSitesTab();
//		sitesPage.createSite(sourceSiteName, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);

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
		alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt",	"English");
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
		alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt",	"English");
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
		alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt",	"English");
		alfrescoAVTPage.addCaptionsFile(filePath, "stat13t_sl_10_03_es.srt", "Spanish");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTT.vtt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTTS.vtt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		
		UIHelper.waitFor(driver);UIHelper.waitFor(driver);		
		
//		move source video and thumbnail and verify streaming
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createFolder("FolderName:Move"+movTitle+"ContentHere,Title:Test,Description:TestDemo");
		myFiles.openCreatedFolder(movTitle);
		ArrayList<String> listOfFilesForMOV = new ArrayList<String>();
		listOfFilesForMOV=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnMoreSetting("PNG.png");
		alfrescoAVTPage.commonMethodForClickOnMoreSettingsOption("PNG.png",	"Move to...");
		docDetailsPageObj.selectFileInMovePopUp("PNG.png",siteName,"Move"+movTitle+"ContentHere");
		alfrescoAVTPage.clickOnMoreSetting("MOV.mov");
		alfrescoAVTPage.commonMethodForClickOnMoreSettingsOption("MOV.mov",	"Move to...");
		docDetailsPageObj.selectFileInMovePopUp("MOV.mov",siteName,"Move"+movTitle+"ContentHere");
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFieldsAfterTranscoding();
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT1.srt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT2.srt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		UIHelper.waitFor(driver);
		listOfFilesForMOV=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		UIHelper.waitFor(driver);
		if(listOfFilesForMOV.contains("SRT1.srt") && listOfFilesForMOV.contains("SRT2.srt")){
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Placed in streaming folder insted new destination folder after movied sourde video and thumbnail to new destination folder", Status.PASS);	
		}else{
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Not Placed in streaming folder insted new destination folder after movied sourde video and thumbnail to new destination folder", Status.FAIL);
		}

//		move source video and thumbnail and verify streaming
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createFolder("FolderName:Move"+mp4Title+"ContentHere,Title:Test,Description:TestDemo");
		myFiles.openCreatedFolder(mp4Title);
		ArrayList<String> listOfFilesForMP4 = new ArrayList<String>();
		listOfFilesForMP4=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnMoreSetting("GIF.gif");
		alfrescoAVTPage.commonMethodForClickOnMoreSettingsOption("GIF.gif",	"Move to...");
		docDetailsPageObj.selectFileInMovePopUp("GIF.gif",siteName,"Move"+mp4Title+"ContentHere");
		alfrescoAVTPage.clickOnMoreSetting("MP4.mp4");
		alfrescoAVTPage.commonMethodForClickOnMoreSettingsOption("MP4.mp4",	"Move to...");
		docDetailsPageObj.selectFileInMovePopUp("MP4.mp4",siteName,"Move"+mp4Title+"ContentHere");
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFieldsAfterTranscoding();
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT1.srt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT2.srt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		UIHelper.waitFor(driver);
		listOfFilesForMP4=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		UIHelper.waitFor(driver);
		if(listOfFilesForMP4.contains("SRT1.srt") && listOfFilesForMP4.contains("SRT2.srt")){
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Placed in streaming folder insted new destination folder after movied sourde video and thumbnail to new destination folder", Status.PASS);	
		}
		else{
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Not Placed in streaming folder insted new destination folder after movied sourde video and thumbnail to new destination folder", Status.FAIL);
		}
		
//		move source video and thumbnail and verify streaming
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createFolder("FolderName:Move"+m4vTitle+"ContentHere,Title:Test,Description:TestDemo");
		myFiles.openCreatedFolder(m4vTitle);
		ArrayList<String> listOfFilesForM4V = new ArrayList<String>();
		listOfFilesForM4V=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnMoreSetting("JPG.jpg");
		alfrescoAVTPage.commonMethodForClickOnMoreSettingsOption("JPG.jpg",	"Move to...");
		docDetailsPageObj.selectFileInMovePopUp("JPG.jpg",siteName,"Move"+m4vTitle+"ContentHere");
		alfrescoAVTPage.clickOnMoreSetting("M4V.m4v");
		alfrescoAVTPage.commonMethodForClickOnMoreSettingsOption("M4V.m4v",	"Move to...");
		docDetailsPageObj.selectFileInMovePopUp("M4V.m4v",siteName,"Move"+m4vTitle+"ContentHere");
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFieldsAfterTranscoding();
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT1.srt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT2.srt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		UIHelper.waitFor(driver);
		listOfFilesForM4V=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		UIHelper.waitFor(driver);
		if(listOfFilesForM4V.contains("SRT1.srt") && listOfFilesForM4V.contains("SRT2.srt")){
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Placed in streaming folder insted new destination folder after movied sourde video and thumbnail to new destination folderr", Status.PASS);	
		}else{
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Not Placed in streaming folder insted new destination folder after movied sourde video and thumbnail to new destination folder", Status.FAIL);
		}
	
//		move all content include json and verify streaming 
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(movTitle);
		docLibPg.selectAllFilesAndFolders();
		docLibPg.clickSelectedItemsMenu();
		docLibPg.checkSelectedItemsMenuOption("Move to...");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption("Move to...");
		docLibPg.performMoveToOperation(siteName, "Move"+movTitle+"ContentHere");
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Move"+movTitle+"ContentHere");
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFieldsAfterTranscoding();
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT1.srt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT2.srt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(movTitle);
		UIHelper.waitFor(driver);
		listOfFilesForMOV=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		UIHelper.waitFor(driver);
		if(listOfFilesForMOV.contains("SRT1.srt") && listOfFilesForMOV.contains("SRT2.srt")){
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Placed in streaming folder insted new destination folder after movied all content to new destination folder", Status.PASS);	
		}else{
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Not Placed in streaming folder insted new destination folder after movied all content to new destination folder", Status.FAIL);
		}
		
//		move all content include json and verify streaming
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(mp4Title);
		docLibPg.selectAllFilesAndFolders();
		docLibPg.clickSelectedItemsMenu();
		docLibPg.checkSelectedItemsMenuOption("Move to...");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption("Move to...");
		docLibPg.performMoveToOperation(siteName, "Move"+mp4Title+"ContentHere");
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Move"+mp4Title+"ContentHere");
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFieldsAfterTranscoding();
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT1.srt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT2.srt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(movTitle);
		UIHelper.waitFor(driver);
		listOfFilesForMP4=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		UIHelper.waitFor(driver);
		if(listOfFilesForMP4.contains("SRT1.srt") && listOfFilesForMP4.contains("SRT2.srt")){
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Placed in streaming folder insted new destination folder after movied all content to new destination folder", Status.PASS);	
		}
		else{
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Not Placed in streaming folder insted new destination folder after movied all content to new destination folder", Status.FAIL);
		}
		
//		move all content include json and verify streaming 
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(m4vTitle);
		docLibPg.selectAllFilesAndFolders();
		docLibPg.clickSelectedItemsMenu();
		docLibPg.checkSelectedItemsMenuOption("Move to...");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption("Move to...");
		docLibPg.performMoveToOperation(siteName, "Move"+m4vTitle+"ContentHere");
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Move"+m4vTitle+"ContentHere");
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyRelatedFilesTabFieldsAfterTranscoding();
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT1.srt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "SRT2.srt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(movTitle);
		UIHelper.waitFor(driver);
		listOfFilesForMOV=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		UIHelper.waitFor(driver);
		if(listOfFilesForMOV.contains("SRT1.srt") && listOfFilesForMOV.contains("SRT2.srt")){
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Placed in streaming folder insted new destination folder after movied all content to new destination folder", Status.PASS);	
		}else{
			report.updateTestLog("newly added caption files SRT1.srt and SRT2.srt are  ", 
					 "Not Placed in streaming folder insted new destination folder after movied all content to new destination folder", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {

	}
}
