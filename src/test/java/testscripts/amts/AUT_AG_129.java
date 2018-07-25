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

public class AUT_AG_129 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_067() {
		testParameters
				.setCurrentTestDescription("Verify the version of the file is not incremented even after applying transformations for a transformed file based on file name");
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
		AlfrescoMediaTransformPageTest mediaTransPageTest = new AlfrescoMediaTransformPageTest(
				scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
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
		String fleNmeBas = dataTable.getData("Media_Transform", "BasedOnFile");	
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
		
		homePageObj.navigateToSitesTab();

		String siteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String prefTxt = dataTable.getData("Media_Transform", "Preference");		
		String file = dataTable.getData("MyFiles", "CreateFolder");
		String renamedFile= file+"_"+subAsstCode+"_"+".jpeg";	
		String fileToTransform= file+"_"+subAsstCode+"_";	
		String transFileName= fileToTransform+"_"+subAsstCode+"-"+prefTxt+"."+imageType;
		String version = dataTable.getData("MyFiles", "Version");
		String moreSettingsOptName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		myFiles.uploadFile(filePath, fileName);	
		sitesPage.clickOnEditProperties(fileName);	
		docLibPg.documentRename(renamedFile);
		sitesPage.clickOnMoreSetting(renamedFile);
		docLibPg.commonMethodForClickOnMoreSettingsOption(renamedFile, moreSettingsOptName);
		mediaTransPage.selectFileNmeBaseRadio(fleNmeBas);
		mediaTransPage.selectProfileBasedOnFileName(prefTxt);		
		docLibPg.navigateToDocumentLibrary();	
		UIHelper.pageRefresh(driver);
		if(mediaTransPage.isTransferredFileIsAvailable(transFileName)){
			report.updateTestLog("Verify tranferred  file is available",
					"File is available", Status.PASS);
		}
		sitesPage.clickOnMoreSetting(transFileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(transFileName, moreSettingsOptName);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName, prefTxt);		
		docLibPg.navigateToDocumentLibrary();
		docLibPg.openAFile(transFileName);
		mediaTransPage.checkVersionOfTransformedFile(version);
	}

	@Override
	public void tearDown() {

	}

}