package testscripts.amts;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaTransformPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_085 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_045() {
		testParameters
				.setCurrentTestDescription("Verify old values are retained after clicking on edit profile from the media profile list - Video Transformation Profile.<br>"
						+"ALFDEPLOY-3877_Verify the html content is not displayed in Transformation Profile Summary section of video profile upon editing the existing video profile.Verify alignment upon zooming in,zooming out,minimizing and maximizing the window.<br>"
						+"ALFDEPLOY-3877_Verify htmlcontent is not displayed in TransformationProfileSummary section upon creating video profile with maxchar in mandatory fields&adding all effects.Verify alignment upon zooming in,zooming out,minimizing and maximizing the window<br>");
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
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String videoSettingsData = dataTable.getData("Media_Transform", "VdoSettings");
		
		String testData = dataTable.getData("Media_Transform",
				"verifyVdoSettings");
		
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		
		mediaTransPage.clickOnCreateVideoProfBtn();

		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc, macCode, subAsstCode);
		
		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {

			/*mediaTransPage.clickAddBtn();*/
			mediaTransPage.clickSaveBtn();
				
			mediaTransPageTest.verifyMediaTransformationProfileFile(profName);
			
			mediaTransPage.editProfileFrmMediaTransPg(profName);
			
			if (mediaTransPage.verifyVideoProfDetails(profName, subAsstCode)) {
				report.updateTestLog("Verify Video profile details",
						"Video transformation profile page is displayed with the old values retained", Status.PASS);
			} else {
				report.updateTestLog("Verify Video profile details",
						"Video transformation profile page is failed to display with the old values retained", Status.FAIL);
			}
			
			mediaTransPage.enterVideoSettingsDetails(videoSettingsData);
			
			/*mediaTransPage.clickAddBtn();*/
			mediaTransPage.clickSaveBtn();
			
			mediaTransPage.editProfileFrmMediaTransPg(profName);
			
			if (mediaTransPage.verifyVideoProfDetails(profName, subAsstCode)
					&& mediaTransPage.verifyVideoSettingsDetails(testData)) {

				report.updateTestLog("Verify video profile details",
						"Video profile details displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify video profile details",
						"Video profile details are mismatches", Status.FAIL);
			}
			
		} else {
			report.updateTestLog("Verify Video Profile creation",
					"Failed to create Video profile", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {

	}

}