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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_144 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_072() {
		testParameters
				.setCurrentTestDescription("1. To verify the user is trying to create the available target folder via relative path for video file transformation"
						+ "<br>2. To verify the user is trying to create the deleted target folder with the same name via relative path for image file transformation"
						+ "<br>3. Validate the selection of target folder via relative path for video file transformation"
						+ "<br>4. Validate the selection of target folder which is a multiple level subfolder on root with source via relative path for video file transformation");
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
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");

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
		
		String fileNameBased1 = "", fileNameBased2 = "";
		if(fileNmeBased.contains(";"))
		{
			String splittedFNBValues[] = fileNmeBased.split(";");
			if(splittedFNBValues!=null && splittedFNBValues.length>1)
			{
				fileNameBased1 = splittedFNBValues[0];
				fileNameBased2 = splittedFNBValues[1];
			}
			else
			{
				fileNameBased1 = "No";
				fileNameBased2 = "Yes";
			}
			
		}
		
		String watchFldrBased1 = "", watchFldrBased2 = "";
		if(watchFldr.contains(";"))
		{
			String splittedWFBValues[] = watchFldr.split(";");
			if(splittedWFBValues!=null && splittedWFBValues.length>1)
			{
				watchFldrBased1 = splittedWFBValues[0];
				watchFldrBased2 = splittedWFBValues[1];
			}
			else
			{
				watchFldrBased1 = "Yes";
				watchFldrBased2 = "Yes";
			}
			
		}

		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateVideoProfBtn();
		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc,
				macCode, subAsstCode);
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Video profile details",
					"Video Profile details displayed successfully", Status.PASS);	
			
			mediaTransPage.clickSaveBtn();

		} else {
			report.updateTestLog("Verify Video Profile creation",
					"Failed to create Video profile", Status.FAIL);
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
		
		sitesPage.enterIntoDocumentLibrary();
						
		for(int index=1;index<=4;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		sitesPage.clickOnMoreSetting(parentFolderName);
		docLibPge.commonMethodForClickOnMoreSettingsOption(parentFolderName,
				docActionVal);
		mediaTransPage.selectFileNmeBaseRadio(fileNameBased1);
		mediaTransPage.watchFldrRadio(watchFldrBased1);
		mediaTransPage.addTargetFolderViaRelativeAndTargSelectionWindow(profName, parentFolderName, watchFolderName, preTxt,
				sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		for(int index=1;index<=5;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		myFiles.uploadFile(filePath, fileNme);
		
		sitesPage.enterIntoDocumentLibrary();
		
		for(int index=1;index<=4;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		myFilesTest.verifyCreatedFolder(watchFolderName);
		
		myFiles.openCreatedFolder(watchFolderName);
		
		String[] file = fileNme.split(Pattern.quote("."));
		String fileName = file[0] + "_" + subAsstCode + "_" +preTxt+ "."+ file[1];

		if (mediaTransPage.isTransferredFileIsAvailable(fileName)) {
			report.updateTestLog(
					"Verify video profile applied Successfully to the file:"
							+ fileName, "Video profile applied successfully.",
					Status.PASS);

		} else {
			report.updateTestLog(
					"Verify video profile applied Successfully to the file:"
							+ fileName, "Video profile is not applied."
							+ "<br><b>File Name : </br>", Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		for(int index=1;index<=4;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		myFiles.deleteCreatedFolder(watchFolderName);
		
		myFiles.openCreatedFolder(parentFolderName);
		myFiles.commonMethodToDeleteFile(fileNme);
		
		sitesPage.enterIntoDocumentLibrary();
		
		for(int index=1;index<=4;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		sitesPage.clickOnMoreSetting(parentFolderName);
		docLibPge.commonMethodForClickOnMoreSettingsOption(parentFolderName,
				docActionVal);
		mediaTransPage.selectFileNmeBaseRadio(fileNameBased2);
		mediaTransPage.watchFldrRadio(watchFldrBased2);
		mediaTransPage.addTargetFolderViaRelativeAndTargSelectionWindow(profName, parentFolderName, watchFolderName, preTxt,
				sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		for(int index=1;index<=5;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		myFiles.uploadFile(filePath, fileNme);
		
		sitesPage.enterIntoDocumentLibrary();
		
		for(int index=1;index<=4;index++)
		{
			myFiles.openCreatedFolder("AutoTest"+index);
		}
		
		myFilesTest.verifyCreatedFolder(watchFolderName);
		
		myFiles.openCreatedFolder(watchFolderName);
		
		if (mediaTransPage.isTransferredFileIsAvailable(fileName)) {
			report.updateTestLog(
					"Verify video profile applied Successfully to the file:"
							+ fileName, "Video profile applied successfully.",
					Status.PASS);

		} else {
			report.updateTestLog(
					"Verify video profile applied Successfully to the file:"
							+ fileName, "Video profile is not applied."
							+ "<br><b>File Name : </br>", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {

	}

}
