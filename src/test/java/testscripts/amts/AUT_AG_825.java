package testscripts.amts;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_825 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_821()
	{
		testParameters.setCurrentTestDescription(
		"[1]ALFDEPLOY-4758_5266_Verify the relationship between source and target is displayed in relationship section of target audio file preview on applying transformation to audio file when file same as target already exists or uploaded in target location<br>"+
		"[1]ALFDEPLOY-4758_5266_Verify the relationship between source and target is displayed in relationship section of target image file preview on applying transformation to image file when file same as target already exists or uploaded in target location<br>"+
		"[1]ALFDEPLOY-4758_5266_Verify the relationship between source and target is displayed in relationship section of target video file preview on applying transformation to video file when file same as target already exists or uploaded in target location<br>"+
		"[1]ALFDEPLOY-4758_Verify relationship between source and target is displayed in relationship section of target audio file preview on applying transformation to folder containing audio files when file same as target already exists or uploaded in target loc<br>"+
		"[1]ALFDEPLOY-4758_Verify relationship between source and target is displayed in relationship section of target image file preview on applying transformation to folder containing image files when file same as target already exists or uploaded in target loc<br>"+
		"[1]ALFDEPLOY-4758_Verify relationship between source and target is displayed in relationship section of target video file preview on applying transformation to folder containing video files when file same as target already exists or uploaded in target loc"
		
				);
		
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileimg = dataTable.getData("MyFiles", "RelationshipName");
		String filevid = dataTable.getData("MyFiles", "Sort Options");

		String files = dataTable.getData("MyFiles", "BrowseActionName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String ParentFolderName = dataTable.getData("MyFiles", "Version");
		String desinationfolder = dataTable.getData("MyFiles", "CreateFileDetails");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String imageType = dataTable.getData("Media_Transform", "OutPutFormat");
		String options = dataTable.getData("MyFiles", "MoreSettingsOption");
		String ProfiledetailsMp3[] = dataTable.getData("Media_Transform", "verifyVdoSettings").split(";");
		String watchFldr = dataTable.getData("Media_Transform", "WatchFolder");
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String vidprofName = dataTable.getData("Media_Transform", "filePath");
		/*
		 * String subAsstCode = dataTable.getData("Media_Transform",
		 * "subAsstCode");
		 * 
		 * String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		 * String macCode = dataTable.getData("Media_Transform", "macCode");
		 */

		String finalFileaudio = "AlfrescoWAVFile_01CA-out.mp3";
		String finalFilevideo = "Alfresco3GPFile_VdoSubAsst-out.mp4";
		String finalFileimg = "AlfrescoTesting_JPEG_IMGSub-out.jpg";
		UIHelper.waitFor(driver);
		// Creation of the MP3 Audio Profile

		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(ProfiledetailsMp3[0]);
		mediaTransPage.clickOnCreateaudioProfBtn();
		mediaTransPage.enterAudioProfileDetails(ProfiledetailsMp3[0], ProfiledetailsMp3[1], ProfiledetailsMp3[2],
				ProfiledetailsMp3[3]);
		mediaTransPage.SubmitAddTransformationrules(options);
		System.out.println(ProfiledetailsMp3[4]);
		mediaTransPage.enteraudioSettingsDetails(ProfiledetailsMp3[4]);
		UIHelper.click(driver, mediaTransPage.vdoSaveBtnXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTransPage.OkbuttonXpath);
		UIHelper.click(driver, mediaTransPage.OkbuttonXpath);
		UIHelper.waitFor(driver);

		sitesPage.navigateToSitesTab();
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(desinationfolder);
		myFiles.uploadFile(filePath, finalFileaudio);
		myFiles.uploadFile(filePath, finalFilevideo);
		myFiles.uploadFile(filePath, finalFileimg);

		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(ParentFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(ParentFolderName, docActionVal);
		mediaTransPage.watchFldrRadio(watchFldr);
		/*
		 * mediaTransPage.selectProfileFrmListAndApplyTransformation(
		 * ProfiledetailsMp3[0], preTxt);
		 */

		mediaTransPage.applyTransformationToTargetFolder(ProfiledetailsMp3[0], ParentFolderName, desinationfolder,
				preTxt, sourceSiteName);
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitFor(driver);

		sitesPage.clickOnMoreSetting(ParentFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(ParentFolderName, docActionVal);
		mediaTransPage.watchFldrRadio(watchFldr);
		mediaTransPage.applyTransformationToTargetFolder(profName, ParentFolderName, desinationfolder, preTxt,
				sourceSiteName);
		
		sitesPage.clickOnMoreSetting(ParentFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(ParentFolderName, docActionVal);
		mediaTransPage.watchFldrRadio(watchFldr);
		mediaTransPage.applyTransformationToTargetFolder(vidprofName, ParentFolderName, desinationfolder, preTxt,
				sourceSiteName);

		sitesPage.documentdetails(ParentFolderName);
		myFiles.uploadFile(filePath, files);

		sitesPage.documentdetails(desinationfolder);

		if (docLibPg.isFileIsAvailable(finalFileaudio)) {
			report.updateTestLog("Verify the Transformed file with sub assert code",
					"File verified successfully" + "<br><b> Transformed File Name : </b>" + finalFileaudio,
					Status.PASS);
		} else {
			report.updateTestLog("Verify the Transformed file with sub assert code",
					"File not verified" + "<br><b> Transformed File Name : </b>" + finalFileaudio, Status.FAIL);
		}

		sitesPage.documentdetails(finalFileaudio);
		docDetailsPageTest.verifyAddedRelationshipData(fileName);
		driver.navigate().back();
		UIHelper.waitForPageToLoad(driver);
		// sitesPage.documentdetails(desinationfolder);

		if (docLibPg.isFileIsAvailable(finalFileimg)) {
			report.updateTestLog("Verify the Transformed file with sub assert code",
					"File verified successfully" + "<br><b> Transformed File Name : </b>" + finalFileimg, Status.PASS);
		} else {
			report.updateTestLog("Verify the Transformed file with sub assert code",
					"File not verified" + "<br><b> Transformed File Name : </b>" + finalFileimg, Status.FAIL);
		}

		sitesPage.documentdetails(finalFileimg);
		docDetailsPageTest.verifyAddedRelationshipData(fileimg);

		driver.navigate().back();
		UIHelper.waitForPageToLoad(driver);

		if (docLibPg.isFileIsAvailable(finalFilevideo)) {
			report.updateTestLog("Verify the Transformed file with sub assert code",
					"File verified successfully" + "<br><b> Transformed File Name : </b>" + finalFilevideo, Status.PASS);
		} else {
			report.updateTestLog("Verify the Transformed file with sub assert code",
					"File not verified" + "<br><b> Transformed File Name : </b>" + finalFilevideo, Status.FAIL);
		}

		sitesPage.documentdetails(finalFilevideo);
		docDetailsPageTest.verifyAddedRelationshipData(filevid);

	}

	@Override
	public void tearDown() {
		
	}

}
