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

public class AUT_AG_783 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_008() {
		testParameters
				.setCurrentTestDescription("To Verify whether system allows the user to set crop rules, by entering the starting XY coordinates and setting crop-to width and height, with ability to lock aspect ratio.");
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

		String type = dataTable.getData("Media_Transform", "filePath");
		String crop = dataTable.getData("Media_Transform", "fileName");

		String testData = dataTable.getData("Media_Transform",
				"verifyVdoSettings");
		String height = dataTable.getData("Media_Transform", "height");
		String width = dataTable.getData("Media_Transform", "width");

		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.enterImageProfDetailsWithUniqueNameAndSubAsst(profName,
				subAsstCode);

		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {

			report.updateTestLog("Verify Image profile created",
					"Video Image created successfully."
							+ "<br><b>Image Profile Name : </br>" + profName,
					Status.PASS);
			mediaTransPage.selectImgFileFormatType(type);
			mediaTransPage.addImgCropDetails(crop, height, width);
			mediaTransPage.clickSaveBtnImgProf();
			mediaTransPage.navigateToMediaTransPage();
			//mediaTransPage.navigateToProfilePage(profName);
			mediaTransPage.editProfileFrmMediaTransPg(profName);
			if (mediaTransPage.verifyVideoProfDetails(profName, subAsstCode)
					&& mediaTransPage.verifyVideoSettingsDetails(testData)) {
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Image profile details",
						"Details displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Image profile details",
						"Details mismatches", Status.FAIL);
			}

		} else {
			report.updateTestLog("Verify Image profile created",
					"Failed to create Image profile", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {

	}

}