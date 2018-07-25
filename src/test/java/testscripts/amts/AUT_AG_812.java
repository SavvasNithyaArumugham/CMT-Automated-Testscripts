package testscripts.amts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

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

public class AUT_AG_812 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_027() {
		testParameters
				.setCurrentTestDescription("To Verify whether system user is able to set Video Profile by selecting different transformation rules.");
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
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);		
		mediaTransPage.clickOnCreateVideoProfBtn();
		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc, macCode, subAsstCode);		
		mediaTransPage.clickOnAddImageProfBtn();
		String testData = dataTable.getData("Media_Transform", "VdoSettings");

		if (mediaTransPage.checkUiInTransRulesPage(testData)) {

			report.updateTestLog(
					"Verify UI in Transformation rules page",
					"All UI elements displayed with default values successfully",
					Status.PASS);
			UIHelper.waitFor(driver);

		} else {
			report.updateTestLog("Verify UI in Transformation rules page",
					"Failed in UI verification", Status.FAIL);
		}

		mediaTransPage.clickSaveBtn();
		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.editProfileFrmMediaTransPg(profName);
		if (mediaTransPage.verifyVideoProfDetails(profName, subAsstCode)) {
			report.updateTestLog("Verify video profile details",
					"Details displayed successfully", Status.PASS);
		} else {
			report.updateTestLog("Verify video profile details",
					"Details mismatches", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {

	}

}
