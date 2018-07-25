package testscripts.amts;

import java.util.ArrayList;
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

public class AUT_AG_809 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_024() {
		testParameters
				.setCurrentTestDescription("To Verify that system doesn’t allow the user to create a new profile if the Profile Name entered matches with the one that already exists.");
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
		AlfrescoHomePageTest homePgTest = new AlfrescoHomePageTest(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		String listXpath = mediaTransPage.valListXpath;
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateVideoProfBtn();
		mediaTransPage.enterVideoProfDetailsWithUniqueName();
		mediaTransPage.clickOnAddImageProfBtn();
		try {
			String profName = new FileUtil()
					.readDataFromFile(mediaTransPage.testOutputFilePathVdoProf);

			if (mediaTransPage.clickOnAddVideoProfBtn()) {
				report.updateTestLog("Verify video profile created",
						"Video profile created successfully."
								+ "<br><b>Video Profile Name : </br>"
								+ profName, Status.PASS);
			} else {
				report.updateTestLog("Verify vidoe profile created",
						"Failed to create Video profile", Status.FAIL);
			}
			mediaTransPage.navigateToMediaTransPage();
			mediaTransPage.clickOnCreateVideoProfBtn();
			mediaTransPage.enterVideoProfDetails(profName);
			if(mediaTransPage.checkDuplicateMessageOnAddDplicateVideoProf())
			{
				report.updateTestLog("Verify duplicate Video profile",
						"Warining message displayed successfully", Status.PASS);
				
			}else{
				report.updateTestLog("Verify duplicate Video profile",
						"Duplicate profile created.", Status.FAIL);
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}

}
