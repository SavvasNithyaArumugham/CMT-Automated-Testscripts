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

public class AUT_AG_099 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_054() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to delete an existing profile - Video Profile Deletion");
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
		
		if (mediaTransPage.checkProfileNameInMeadiTransProfilePage(profName)) {
			mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		}
		
		mediaTransPage.clickOnCreateVideoProfBtn();

		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc, macCode, subAsstCode);
		
		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {

			mediaTransPage.enterVideoSettingsDetails(videoSettingsData);
			
			/*mediaTransPage.clickAddBtn();*/
			mediaTransPage.clickSaveBtn();
			
			mediaTransPageTest.verifyMediaTransformationProfileFile(profName);
			
		} else {
			report.updateTestLog("Verify Video Profile creation",
					"Failed to create Video profile", Status.FAIL);
		}
		
		if (mediaTransPage.checkProfileNameInMeadiTransProfilePage(profName)) {
			mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		}
	}

	@Override
	public void tearDown() {

	}

}