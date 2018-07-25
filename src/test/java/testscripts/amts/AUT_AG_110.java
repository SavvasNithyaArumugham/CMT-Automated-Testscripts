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

public class AUT_AG_110 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_065() {
		testParameters
				.setCurrentTestDescription("Verify that the user gets a duplicate error message");
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
		mediaTransPage.clickOnCreateVideoProfBtn();		
		mediaTransPage.commonMethodToEnterVideoProfDetails(profName, subAsstCode, profDesc, macCode);

		if (mediaTransPage.clickOnAddVideoProfBtn()) {

			report.updateTestLog("Verify video profile created",
					"Video profile created successfully."
							+ "<br><b>Video Profile Name : </br>" + profName,
					Status.PASS);
			/*mediaTransPage.clickAddBtn();
			mediaTransPage.clickSaveBtn();*/
			
		} else {
			report.updateTestLog("Verify vidoe profile created",
					"Failed to create Video profile", Status.FAIL);
		}
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateVideoProfBtn();		
		mediaTransPage.commonMethodToEnterVideoProfDetails(profName, subAsstCode, profDesc, macCode);
		if(mediaTransPage.checkDuplicateMessageOnAddDplicateVideoProf()){
			report.updateTestLog("Verify warning message for duplicate profile",
					"Warning message displayed successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify warning message for duplicate profile",
					"Warning message is not displayed", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {

	}

}