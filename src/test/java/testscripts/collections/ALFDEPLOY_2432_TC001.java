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

public class ALFDEPLOY_2432_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_052() {
		testParameters
				.setCurrentTestDescription("When generating a course CSV containing any objects where 'Teacher Only' is 'True' and 'Hide From Student' is 'False' can see that the following error is reported: 'Hide From Student' must not be 'False'");
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
		// Navigate to collections UI

		// Navigate to each object

		// Select ""Edit Properties"" from the right side menu

		// Populate the ""Teacher Only"" with ""True"" and ""Hide From Student""
		// with ""False

		// Navigate to the Course

		// Hover over the course and from the right side menu select ""Generate
		// Realize CSV""

		// Navigate to the Realize CSV (documents > data exchange > course >
		// date >)

		// Open the Realize CSV document

		// Check that the following error is reported: ""Hide From Student""
		// must not be ""False""
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
