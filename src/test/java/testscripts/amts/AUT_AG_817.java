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

public class AUT_AG_817 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_30() {
		testParameters
				.setCurrentTestDescription("To Verify whether the previously given information are getting retained in the profile summary section of video profile");
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
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateVideoProfBtn();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		profName = profName + sdf.format(cal.getTime());
		profName = profName.replaceAll(":", "");
		subAsstCode = subAsstCode + sdf.format(cal.getTime());
		subAsstCode = subAsstCode.replaceAll(":", "");
		mediaTransPage.enterVideoProfDetailsWithUniqueNameAndSubAsst(profName,
				subAsstCode);

		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify video profile created",
					"Video profile created successfully."
							+ "<br><b>Video Profile Name : </br>" + profName,
					Status.PASS);
			//mediaTransPage.clickAddBtn();
			mediaTransPage.clickSaveBtn();
			UIHelper.waitFor(driver);
			mediaTransPage.navigateToMediaTransPage();
			mediaTransPage.editProfileFrmMediaTransPg(profName);
			if (mediaTransPage.verifyVideoProfDetails(profName, subAsstCode)) {
				report.updateTestLog("Verify vidoe profile details",
						"Details displayed successfully", Status.PASS);
			}
			else{
				report.updateTestLog("Verify vidoe profile details",
						"Details mismatches", Status.FAIL);
			}

		} else {
			report.updateTestLog("Verify vidoe profile created",
					"Failed to create Video profile", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {

	}

}