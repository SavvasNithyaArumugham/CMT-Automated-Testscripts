package testscripts.amts;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaTransformPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_128 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_066() {
		testParameters
				.setCurrentTestDescription("Verify the version of the file is not incremented even after applying transformations for a transformed file from the document library <br>" +
		"ALFDEPLOY-4109_Verify the target image file is updated with new version and content when target image file resides in the target location upon applying image transformation for a file");
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
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String imageType = dataTable.getData("Media_Transform", "OutPutFormat");		
		String filePathForUploadFile = dataTable.getData("Media_Transform", "filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform", "fileName");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");	
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();

		mediaTransPage.commonMethodForEnterImageProfDetails(profName,
				profDesc, macCode, subAsstCode,
				filePathForUploadFile, fileNameForUpload);
		
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Image profile details",
					"Image Profile details displayed successfully", Status.PASS);
			mediaTransPage.selectImgTransType(imageType);
			mediaTransPage.clickAddBtnImgProf();
			mediaTransPage.clickSaveBtnImgProf();

		} else {
			report.updateTestLog("Verify Image Profile creation",
					"Failed to create Image profile", Status.FAIL);
		}
		
	

		String siteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String prefTxt = dataTable.getData("Media_Transform", "Preference");		
		String file = dataTable.getData("MyFiles", "CreateFolder");
		
		String transFileName= file+"_"+subAsstCode+"-"+prefTxt+"."+imageType;
		String version = dataTable.getData("MyFiles", "Version");
		String version1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOptName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		myFiles.uploadFile(filePath, fileName);		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptName);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName, prefTxt);		
		docLibPg.navigateToDocumentLibrary();	
		UIHelper.pageRefresh(driver);
		if(mediaTransPage.isTransferredFileIsAvailable(transFileName)){
			report.updateTestLog("Verify tranferred  file is available",
					"File is available", Status.PASS);
		}
		sitesPage.clickOnMoreSetting(transFileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(transFileName, moreSettingsOptName);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName, prefTxt);		
		sitesPage.enterIntoDocumentLibrary();

		
		sitesPage.documentdetails(transFileName);

		mediaTransPage.checkVersionOfTransformedFile(version);
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptName);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName, prefTxt);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(transFileName);

		mediaTransPage.checkVersionOfTransformedFile(version1);
		
		
	}

	@Override
	public void tearDown() {

	}

}