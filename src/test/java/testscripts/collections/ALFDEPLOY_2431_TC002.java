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

public class ALFDEPLOY_2431_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_051() {
		testParameters
				.setCurrentTestDescription("When generating a course CSV containing any objects where the File Type is not 'Sequence' and the 'Player Target' is blank, can see the following error is reported: 'Player Target' must not be BLANK");
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

		// Populate the ""File Type"" with some other option that is not
		// ""Sequence"" and the ""Player Target"" field should be left blank

		// Navigate to the Course

		// Hover over the course and from the right side menu select ""Generate
		// Realize CSV""

		// Navigate to the Realize CSV (documents > data exchange > course >
		// date >)

		// Open the Realize CSV document

		// Check that the following error is reported: ""Player Target"" must
		// not be BLANK
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
