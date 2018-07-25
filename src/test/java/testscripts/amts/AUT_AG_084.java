package testscripts.amts;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaTransformPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_084 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_044() {
		testParameters
				.setCurrentTestDescription("Verify old values are retained after clicking on edit profile from the media profile list");
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
		
		mediaTransPage.navigateToMediaTransPage();
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String imageType = dataTable.getData("Media_Transform", "OutPutFormat");
		String imageTypeForEdit = dataTable.getData("Media_Transform", "OutPutFormatForEditProfile");
		String filePathForUploadFile = dataTable.getData("Media_Transform", "filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform", "fileName");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		  UIHelper.pageRefresh(driver);;
		mediaTransPage.clickOnCreateImageProfBtn();
      
		mediaTransPage.commonMethodForEnterImageProfDetails(profName,
				profDesc, macCode, subAsstCode,
				filePathForUploadFile, fileNameForUpload);
				
		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {

			mediaTransPage.selectImgTransType(imageType);
			mediaTransPage.clickAddBtnImgProf();
			
			mediaTransPage.clickOnPreviewButton();
			mediaTransPageTest.verifyImagePreview();
			
			mediaTransPage.clickSaveBtnImgProf();
			
			mediaTransPageTest.verifyMediaTransformationProfileFile(profName);
			
			mediaTransPage.editProfileFrmMediaTransPg(profName);			
			if (mediaTransPage.verifyVideoProfDetails(profName, subAsstCode)
					&& mediaTransPage.verifyVideoSettingsDetails("targetFileFormat:image/"+imageType)) {			
				report.updateTestLog("Verify Image profile details",
						"Image transformation profile page is displayed with the old values retained", Status.PASS);
			} else {
				report.updateTestLog("Verify Image profile details",
						"Image transformation profile page is failed to display with the old values retained", Status.FAIL);
			}
			
			mediaTransPage.selectImgTransType(imageTypeForEdit);
			mediaTransPage.clickAddBtnImgProf();
			
			mediaTransPage.clickOnPreviewButton();
			mediaTransPageTest.verifyImagePreview();
			
			mediaTransPage.clickSaveBtnImgProf();	
					
			mediaTransPage.editProfileFrmMediaTransPg(profName);			
			if (mediaTransPage.verifyVideoProfDetails(profName, subAsstCode)
					&& mediaTransPage.verifyVideoSettingsDetails("targetFileFormat:image/"+imageTypeForEdit)) {			
				report.updateTestLog("Verify Image profile details",
						"Image Profile details displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Image profile details",
						"Image Profile details mismatches", Status.FAIL);
			}

		} else {
			report.updateTestLog("Verify Image Profile creation",
					"Failed to create Image profile", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {

	}

}