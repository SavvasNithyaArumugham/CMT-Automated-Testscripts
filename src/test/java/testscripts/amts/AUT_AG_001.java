package testscripts.amts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.server.handler.CaptureScreenshot;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_078() {
		testParameters
				.setCurrentTestDescription("Verify user is able to created image profile with sub asset code containing dash in the middle and verify its transformed file by applying the profile <br>"+
		"ALFDEPLOY-4427_Verify the target output filename does not cut off period present in source file when image transformation is applied for folder containing image files with period<br>"+
						"[1]ALFDEPLOY-4758_Verify the relationship between source and target is displayed in relationship section of target image file preview on applying file based transformation to image file when file same as target already exists or uploaded in target location");
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

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String imageType = dataTable.getData("Media_Transform",
				"FileFormatType");
		String filePathForUploadFile = dataTable.getData("Media_Transform",
				"filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform",
				"fileName");

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String rename = dataTable.getData("MyFiles", "RelationshipName");
		String transfile = dataTable.getData("MyFiles", "BrowseActionName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String version = dataTable.getData("MyFiles", "Sort Options");
		if (imageType.equalsIgnoreCase("jpeg")) {
			imageType = "jpg";
		}
		String fileName = transfile + "_" + subAsstCode + "-" + preTxt + "."
				+ imageType;
		
		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.pageRefresh(driver);
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.commonMethodForEnterImageProfDetails(profName, profDesc,
				macCode, subAsstCode, filePathForUploadFile, fileNameForUpload);
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Image profile details",
					"Image Profile details displayed successfully", Status.PASS);
			mediaTransPage.selectImgFileFormatType(imageType);
			mediaTransPage.clickSaveBtnImgProf();

		} else {
			report.updateTestLog("Verify Image Profile creation",
					"Failed to create Image profile", Status.FAIL);
		}

	
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(folderName);
		myFiles.uploadFile(filePath, fileNme);
		myFiles.uploadFile(filePath, fileName);
		
		sitesPage.documentdetails(fileName);
		if(!docDetailsPage.isRelationshipAddedForSite()){
			report.updateTestLog("Verify Relationship exists",
					"No relation ship exists as expected"
							+ "</br><b>File Name:</b> " + fileName,Status.PASS);
		}else{
			report.updateTestLog("Verify Relationship exists",
					"relation ship exists which is not expected"
							+ "</br><b>File Name:</b> " + fileName,Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		myFiles.rename(fileNme, rename);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
				docActionVal);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName,
				preTxt);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		/*String[] fileNames = fileNme.split(",");
		for (String filename : fileNames) {
			String[] file = filename.split(Pattern.quote("."));*/
			/*if (imageType.equalsIgnoreCase("jpeg")) {
				imageType = "jpg";
			}
			String fileName = transfile + "_" + subAsstCode + "-" + preTxt + "."
					+ imageType;*/

			if (mediaTransPage.isTransferredFileIsAvailable(fileName)) {
				//mediaTransPage.checkVersionOfTransformedFile(version);
				sitesPage.documentdetails(fileName);
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
			//Modified as part of NALS
			//sitesPage.documentdetails(fileName);
			docDetailsPageTest.verifyAddedRelationshipData(rename);

		}
	//}

	@Override
	public void tearDown() {

	}

}
