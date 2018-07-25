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

public class AUT_AG_781 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_006() {
		testParameters
				.setCurrentTestDescription("To Verify whether system allow the user to set resize rules by entering percentage value.<br>"
						+"ALFDEPLOY-3877_Verify the html content is not displayed in Transformation Profile Summary section of image profile upon editing the existing image profile.Verify alignment upon zooming in,zooming out,minimizing and maximizing the window.<br>");
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

		String percent = dataTable.getData("Media_Transform", "VdoSettings");
		String type = dataTable.getData("Media_Transform", "filePath");
		String testData = dataTable.getData("Media_Transform",
				"verifyVdoSettings");

		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		
		/*mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.enterImageProfDetailsWithUniqueNameAndSubAsst(profName,
				subAsstCode);
		
		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {
			
			report.updateTestLog("Verify Image profile created",
					"Video Image created successfully."
							+ "<br><b>Image Profile Name : </br>" + profName,
					Status.PASS);
			mediaTransPage.selectImgFileFormatType(type);

			mediaTransPage.addImgResizeByPercent(percent);
			UIHelper.waitFor(driver);
			
			if ( mediaTransPage.verifyVideoSettingsDetails(testData)) {
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Image profile details",
						"Details displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Image profile details",
						"Details mismatches", Status.FAIL);
			}
			
			mediaTransPage.clickSaveBtnImgProf();
			UIHelper.waitFor(driver);
		}
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);*/
		
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

			mediaTransPage.addImgResizeByPercent(percent);
			UIHelper.waitFor(driver);

			mediaTransPage.clickSaveBtnImgProf();
			UIHelper.waitFor(driver);
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
			report.updateTestLog("Verify image profile created",
					"Failed to create image profile", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {

	}

}