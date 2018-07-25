package testscripts.amts;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaTransformPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_132 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_060() {
		testParameters
				.setCurrentTestDescription("To verify the choosing of target folder via relative path and target folder selection window throws error for video/image file transformation"
						+ "<br>2. Validate the selection of target folder via relative path for image file transformation"
						+ "<br>3. Validate the selection of target folder which is a subfolder on root with source via relative path for image file transformation");
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
		AlfrescoMyFilesPageTest myFilesTest = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		AlfrescoMediaTransformPageTest mediaTransPageTest = new AlfrescoMediaTransformPageTest(
				scriptHelper);
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String imageType = dataTable.getData("Media_Transform",
				"FileFormatType");
		String creditLineOpt = dataTable.getData("Media_Transform",
				"CreditLine");
		String filePathForUploadFile = dataTable.getData("Media_Transform",
				"filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform",
				"fileName");

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String deepFolderDetails = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String parentFolderName = dataTable.getData("MyFiles", "Version");
		String watchFolderName = dataTable.getData("MyFiles",
				"CreateFileDetails");
		
		String docActionVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");		
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String fileNmeBased = dataTable.getData("Media_Transform",
				"BasedOnFile");
		String watchFldr = dataTable.getData("Media_Transform", "WatchFolder");

		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.commonMethodForEnterImageProfDetails(profName, profDesc,
				macCode, subAsstCode, filePathForUploadFile, fileNameForUpload);
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Image profile details",
					"Image Profile details displayed successfully", Status.PASS);
			mediaTransPage.selectImgFileFormatType(imageType);
			mediaTransPage.addCreditLineForImg(creditLineOpt);
			mediaTransPageTest.verifyPreviewBtn();
			mediaTransPage.clickSaveBtnImgProf();

		} else {
			report.updateTestLog("Verify Image Profile creation",
					"Failed to create Image profile", Status.FAIL);
		}

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();	
		
		myFiles.createFolder(folderDetails);
		
		if (deepFolderDetails.contains(";")) {
			String splittedFolderDetailas[] = deepFolderDetails.split(";");
			for (String folderValues : splittedFolderDetailas) {
				String splittedFolderValues[] = folderValues.split(",");

				String folderName = splittedFolderValues[0].replace("FolderName:", "");
				String folderTitle = splittedFolderValues[1].replace("Title:", "");
				String folderDescription = splittedFolderValues[2].replace("Description:", "");

				for(int index=1;index<=5;index++)
				{
					myFiles.commonMethodForCreateFolder(folderName+index, folderTitle+index, folderDescription+index);
					myFiles.openCreatedFolder(folderName+index);
				}
			}
		} else {
			String splittedFolderValues[] = deepFolderDetails.split(",");

			String folderName = splittedFolderValues[0].replace("FolderName:", "");
			String folderTitle = splittedFolderValues[1].replace("Title:", "");
			String folderDescription = splittedFolderValues[2].replace("Description:", "");

			for(int index=1;index<=5;index++)
			{
				myFiles.commonMethodForCreateFolder(folderName+index, folderTitle+index, folderDescription+index);
				myFiles.openCreatedFolder(folderName+index);
			}
		}
		
		myFiles.uploadFile(filePath, fileNme);
		
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
						
		for(int index=1;index<=4;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		sitesPage.clickOnMoreSetting(parentFolderName);
		docLibPge.commonMethodForClickOnMoreSettingsOption(parentFolderName,
				docActionVal);
		mediaTransPage.selectFileNmeBaseRadio(fileNmeBased);
		mediaTransPage.watchFldrRadio(watchFldr);
		mediaTransPage.addTargetFolderViaRelativeAndTargSelectionWindow(profName, parentFolderName, watchFolderName, preTxt,
				sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		for(int index=1;index<=4;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		myFilesTest.verifyCreatedFolder(watchFolderName);
		
		myFiles.openCreatedFolder(watchFolderName);
		
		String[] file = fileNme.split(Pattern.quote("."));
		if (imageType.equalsIgnoreCase("jpeg")) {
			imageType = "jpg";
		}
		String fileName = file[0] + "_" + subAsstCode + "-" + preTxt + "."
				+ imageType;

		if (mediaTransPage.isTransferredFileIsAvailable(fileName)) {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ fileName, "Image profile applied successfully.",
					Status.PASS);

		} else {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ fileName, "Image profile is not applied."
							+ "<br><b>File  Name : </br>", Status.FAIL);
		}

		sitesPage.enterIntoDocumentLibrary();
		
		for(int index=1;index<=4;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		myFiles.deleteCreatedFolder(watchFolderName);
		myFiles.openCreatedFolder(parentFolderName);
		
		String fileNme2 = dataTable.getData("Document_Details", "FileName");
		String filePath2 = dataTable.getData("Document_Details", "FilePath");
		
		myFiles.uploadFile(filePath2, fileNme2);
		
		sitesPage.enterIntoDocumentLibrary();
		
		for(int index=1;index<=4;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		myFilesTest.verifyCreatedFolder(watchFolderName);
		
		myFiles.openCreatedFolder(watchFolderName);
		
		String[] file2 = fileNme2.split(Pattern.quote("."));
		if (imageType.equalsIgnoreCase("jpeg")) {
			imageType = "jpg";
		}
		String transFileName2 = file2[0] + "_" + subAsstCode + "-" + preTxt + "."
				+ imageType;
		
		if (mediaTransPage.isTransferredFileIsAvailable(transFileName2)) {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ transFileName2, "Image profile applied successfully.",
					Status.PASS);
		} else {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ transFileName2, "Image profile is not applied."
							+ "<br><b>File  Name : </br>", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {

	}

}
