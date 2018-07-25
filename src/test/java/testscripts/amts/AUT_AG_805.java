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

public class AUT_AG_805 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_020() {
		testParameters
				.setCurrentTestDescription("To Verify whether the user is able to Edit the already created Transformation image profile.");
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
		String imageType = dataTable.getData("Media_Transform",
				"FileFormatType");
		String filePathForUploadFile = dataTable.getData("Media_Transform",
				"filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform",
				"fileName");		
		String type = dataTable.getData("Media_Transform", "filePath");
		String type1 = dataTable.getData("Media_Transform", "VdoSettings");
		String testData = dataTable.getData("Media_Transform",
				"verifyVdoSettings");
		

		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);		
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.commonMethodForEnterImageProfDetails(profName,
				profDesc, macCode, subAsstCode, filePathForUploadFile,
				fileNameForUpload);

		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {

			report.updateTestLog("Verify Image profile created",
					"Image profile created successfully."
							+ "<br><b>Image Profile Name : </br>" + profName,
					Status.PASS);
			mediaTransPage.selectImgTransType(type);
			mediaTransPage.clickAddBtnImgProf();
			mediaTransPage.clickSaveBtnImgProf();
			UIHelper.waitFor(driver);
			mediaTransPage.navigateToMediaTransPage();
			//add search method			
			mediaTransPage.searchMediaProfile(profName);
			mediaTransPage.editProfileFrmMediaTransPg(profName);
			mediaTransPage.selectImgTransType(type1);
			mediaTransPage.clickAddBtnImgProf();
			mediaTransPage.clickSaveBtnImgProf();	
			UIHelper.waitFor(driver);
			mediaTransPage.navigateToMediaTransPage();
			//add search method			
			mediaTransPage.searchMediaProfile(profName);
			mediaTransPage.editProfileFrmMediaTransPg(profName);			
			if (mediaTransPage.verifyVideoProfDetails(profName, subAsstCode)
					&& mediaTransPage.verifyVideoSettingsDetails(testData)) {			
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