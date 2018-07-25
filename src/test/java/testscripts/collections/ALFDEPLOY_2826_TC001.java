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

public class ALFDEPLOY_2826_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_072() {
		testParameters
				.setCurrentTestDescription("As an Editor I want thumbnails to be automatically imported when I upload a Course Plan CSV file so that I have all content relating to an object");
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

		// Navigate to an object, which has 2xthumbnail populated in the CSV,
		// and check in the relationship viewer the "has thumbnail" association
		// to the associated thumbnail

		// Navigate to each object, which has 2xgrid thumbnail populated in the
		// CSV, and check in the relationship viewer the "has grid thumbnail"
		// association to the associated thumbnail

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
