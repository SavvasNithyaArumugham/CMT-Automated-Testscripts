package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_171 extends TestCase
{
	
	@Test
	public void MISC2_066()
	{
		testParameters.setCurrentTestDescription("Verify that following links are available in Email Notification.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		gmailobj.enterCredentials();
		gmailobj.searchRecentActivityMail();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}