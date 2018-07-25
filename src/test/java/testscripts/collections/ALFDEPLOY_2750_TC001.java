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

public class ALFDEPLOY_2750_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_057() {
		testParameters
				.setCurrentTestDescription("As a user I want the \"PearsonBU\" column in the Realize CSV export to be automatically populated from the \"Discipline\" metadata so that I can import it to Realize");
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
		// Login as a user that is a member of Collection Course Planner group.

		// Navigate to Collection UI.

		// Select ""Generate Realize CSV's"" action on course.

		// Navigate to Documents>Data Exports>Realize Export folder and download
		// the CSV.

		// Open the CSV.

		// See that for all other discipline values, the ""Pearsonbu"" column is
		// directly populated with value of the *Discipline* metadata.

		// Navigate to Document Library > Data Imports

		// Import document

		// Navigate to Document Library > Data Imports > Completed folder

		// Hover over CSV and select ""more"" option

		// Click on the ""view error report"" action

		// Check error report

		// Navigate to each object with errors and see that it is created

		// Check that default values are put in the fields that contained errors

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
