package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2825_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_071() {
		testParameters
				.setCurrentTestDescription("As a user importing a Realize CSV I want to identify assets that already exist in Alfresco before downloading them from documentum so that I do not create unnecessary duplicates");
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
		
		// Log in Pearson Schools project
				AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
				functionalLibrary.loginAsValidUser(signOnPage);

		// Create Collections Site

		// Navigate to Collections Site

		// Import legacy course

		// Navigate to each content object that has a referal to the asset
		// mentioned in the preconditions

		// Check content object has references to the asset mentioned in the
		// preconditions

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
