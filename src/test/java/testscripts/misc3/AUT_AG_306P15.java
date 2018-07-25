package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoPearsonMailPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_306P15 extends TestCase {

	@Test
	public void sharebox_003() {
		testParameters
				.setCurrentTestDescription("Part2:Verify the user not receives the activity email immediately, if the internal user link file into other site  when the Notifications is set as 'immediately'");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("GmailUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {

		String message = dataTable.getData("Sharebox", "Message");
		

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoPearsonMailPageTest pearsonMailTestObj=new AlfrescoPearsonMailPageTest(scriptHelper);
		AlfrescoPearsonMailPage pearsonMailObj=new AlfrescoPearsonMailPage(scriptHelper);
		gmailobj.enterCredentials();	
		pearsonMailObj.searchNoEmailReceived(message);
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}