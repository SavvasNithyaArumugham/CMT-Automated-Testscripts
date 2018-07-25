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

public class ALFDEPLOY_2836_TC004 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_080() {
		testParameters
				.setCurrentTestDescription("As a user, when I copy an Composite Object, Asset or Content Ojbect, I want the system to automatically create or retain an association from the original source to the copy, so that I can maintain the traceability of original ancestry of all copied assets.");
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

		// Login as a user that is member of Collection Course Planner group.

		// Navigate to Collections UI.

		// "Select ""Duplicate Object"" action on a Composite object (lets name
		// it object A).
		// (to make ""Duplicate Object"" action visible, hover over the object
		// and from the menu in the right side select ""More"")

		// Navigate to object's A details page.

		// Select again "Duplicate Object" action on object A

		// Navigate to object's A details page

		// Repeat steps #3-#6 for "Duplicate All" and "Duplicate All To..."
		// actions.

		// Repeat steps #3-#7 for Content Object and steps #3-#6 for Asset.

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
