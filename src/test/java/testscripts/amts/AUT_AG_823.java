package testscripts.amts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_823 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_004() {
		testParameters
				.setCurrentTestDescription(
						
				"ALFDEPLOY-4252_Verify user is able to increase by 10s and decrease by 10s the "
				+ "contrast slider in contrast accordion"
						
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
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {	
		
		
	try {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		
		String docActionVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String filePathForUploadFile = dataTable.getData("Media_Transform",
				"filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform",
				"fileName");
		
		UIHelper.waitFor(driver);
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.pageRefresh(driver);
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.commonMethodForEnterImageProfDetails(profName, profDesc,
				macCode, subAsstCode, filePathForUploadFile, fileNameForUpload);
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Image profile details",
					"Image Profile details displayed successfully", Status.PASS);
			
			mediaTransPage.selectImgFileFormatType("jpeg");
			//mediaTransPage.clickAddBtn();
			mediaTransPage.clickprofiletypeopen("Brightness");
			mediaTransPage.sliderbarcheck("brightness_silder","30");			
			mediaTransPage.addbuttoninImage("Brightness");
			mediaTransPage.sliderbarcheck("brightness_silder","1");
			mediaTransPage.addbuttoninImage("Brightness");
			mediaTransPage.clickprofiletypeopen("Contrast");
			mediaTransPage.sliderbarcheck("contrast_silder","30");
			mediaTransPage.addbuttoninImage("Contrast");
			mediaTransPage.sliderbarcheck("contrast_silder","1");
			mediaTransPage.addbuttoninImage("Contrast");
			
			
			mediaTransPage.clickOnPreviewButton();
			mediaTransPage.clickSaveBtnImgProf();
			
			mediaTransPage.editProfileFrmMediaTransPg(profName);
			mediaTransPage.clickprofiletypeopen("Brightness");
			mediaTransPage.sliderbarcheck("brightness_silder","30");
			mediaTransPage.addbuttoninImage("Brightness");
			mediaTransPage.sliderbarcheck("brightness_silder","1");
			mediaTransPage.addbuttoninImage("Brightness");
			mediaTransPage.clickprofiletypeopen("Contrast");
			mediaTransPage.sliderbarcheck("contrast_silder","30");
			mediaTransPage.addbuttoninImage("Contrast");
			mediaTransPage.sliderbarcheck("contrast_silder","1");
			mediaTransPage.addbuttoninImage("Contrast");
			//mediaTransPage.selectImgFileFormatType("jpeg");
			mediaTransPage.clickOnPreviewButton();
			mediaTransPage.clickSaveBtnImgProf();
								

		} else {
			report.updateTestLog("Verify Image profile details",
					"Image Profile creation failed", Status.FAIL);
		}
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg("PNGIMAGE1");
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.commonMethodForEnterImageProfDetails("PNGIMAGE1", profDesc,
				"123SD", "1CGOOD", filePathForUploadFile, fileNameForUpload);
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Image profile details",
					"Image Profile details displayed successfully", Status.PASS);
			
			mediaTransPage.selectImgFileFormatType("png");
			//mediaTransPage.clickAddBtn();
			mediaTransPage.clickprofiletypeopen("Brightness");
			mediaTransPage.sliderbarcheck("brightness_silder","30");
			mediaTransPage.addbuttoninImage("Brightness");
			mediaTransPage.sliderbarcheck("brightness_silder","1");
			mediaTransPage.addbuttoninImage("Brightness");
			mediaTransPage.clickprofiletypeopen("Contrast");
			mediaTransPage.sliderbarcheck("contrast_silder","30");
			mediaTransPage.addbuttoninImage("Contrast");
			mediaTransPage.sliderbarcheck("contrast_silder","1");
			mediaTransPage.addbuttoninImage("Contrast");
			
			mediaTransPage.clickOnPreviewButton();
			mediaTransPage.clickSaveBtnImgProf();
			mediaTransPage.editProfileFrmMediaTransPg("PNGIMAGE1");
			mediaTransPage.clickprofiletypeopen("Brightness");
			mediaTransPage.sliderbarcheck("brightness_silder","30");
			mediaTransPage.addbuttoninImage("Brightness");
			mediaTransPage.sliderbarcheck("brightness_silder","1");
			mediaTransPage.addbuttoninImage("Brightness");
			mediaTransPage.clickprofiletypeopen("Contrast");
			mediaTransPage.sliderbarcheck("contrast_silder","30");
			mediaTransPage.addbuttoninImage("Contrast");
			mediaTransPage.sliderbarcheck("contrast_silder","1");
			mediaTransPage.addbuttoninImage("Contrast");
			//mediaTransPage.selectImgFileFormatType("png");
			mediaTransPage.clickOnPreviewButton();
			mediaTransPage.clickSaveBtnImgProf();
								

		} else {
			report.updateTestLog("Verify Image profile details",
					"Image Profile creation failed", Status.FAIL);
		}
		
		// Tiff to Jpg
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();				
		myFiles.uploadFile(filePath, fileNme);
		
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme,
				docActionVal);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(
				profName, "out");
		sitesPage.enterIntoDocumentLibrary();	
		String[] file = fileNme.split(Pattern.quote("."));
		
		String fileName = file[0] + "_" + subAsstCode + "-" + "out"
				+ "." + "jpg";

		UIHelper.pageRefresh(driver);
		//UIHelper.waitFor(driver);
		if (mediaTransPage.isTransferredFileIsAvailable(fileName)) {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ fileName,
					"Image profile applied successfully.", Status.PASS);

		} else {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ fileName, "Image profile is not applied."
							+ "<br><b>File  Name : </br>", Status.FAIL);
		}
		
		
		// Tiff to Png
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();				
		myFiles.uploadFile(filePath, fileNme);
		
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme,
				docActionVal);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(
				"PNGIMAGE1", "out");
		sitesPage.enterIntoDocumentLibrary();	
		String[] files = fileNme.split(Pattern.quote("."));
		
		String fileNames = files[0] + "_" + "1CGOOD" + "-" + "out"
				+ "." + "png";
		UIHelper.pageRefresh(driver);
		//UIHelper.waitFor(driver);
		if (mediaTransPage.isTransferredFileIsAvailable(fileNames)) {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ fileNames,
					"Image profile applied successfully.", Status.PASS);

		} else {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ fileNames, "Image profile is not applied."
							+ "<br><b>File  Name : </br>", Status.FAIL);
		}
		
		// Folder:
		
		UIHelper.waitFor(driver);
		
		String ParentFolderName = dataTable.getData("MyFiles", "Version");
		String TargetFolderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String[] folderDetails = dataTable.getData("MyFiles", "CreateFolder").split(";");
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails[0]);
		myFiles.createFolder(folderDetails[1]);
		myFiles.openCreatedFolder(ParentFolderName);
		
		// Apply transformation for PNGIMAGE tiff to png
		myFiles.uploadFile(filePath, fileNme);		
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme,
				docActionVal);
		mediaTransPage.applyTransformationToTargetFolder("PNGIMAGE1", fileNme, TargetFolderName, "out", sourceSiteName);	
		
		// Apply transformation for Imgpro tiff to jpg
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(ParentFolderName);
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme,
				docActionVal);
		mediaTransPage.applyTransformationToTargetFolder(profName, fileNme, TargetFolderName, "out", sourceSiteName);	
		UIHelper.pageRefresh(driver);
		// Check whether the files are converted:
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(TargetFolderName);
		String[] files1 = fileNme.split(Pattern.quote("."));
		
		String fileNames1 = files1[0] + "_" + "1CGOOD" + "-" + "out"
				+ "." + "png";
		String fileNames2 = files1[0] + "_" + subAsstCode + "-" + "out"
				+ "." + "jpg";
		UIHelper.pageRefresh(driver);
		if (mediaTransPage.isTransferredFileIsAvailable(fileNames1)) {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the Folder "
							+ fileNames1,
					"Image profile applied successfully.", Status.PASS);

		} else {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the Folder "
							+ fileNames1, "Image profile is not applied."
							+ "<br><b>File  Name : </br>", Status.FAIL);
		}
		UIHelper.pageRefresh(driver);
		if (mediaTransPage.isTransferredFileIsAvailable(fileNames2)) {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the Folder "
							+ fileNames2,
					"Image profile applied successfully.", Status.PASS);

		} else {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the Folder "
							+ fileNames2, "Image profile is not applied."
							+ "<br><b>File  Name : </br>", Status.FAIL);
		}
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		report.updateTestLog("Verify Image profile details",
				"Image Profile creation failed", Status.FAIL);
	}
	
	}

	@Override
	public void tearDown() {

	}

}