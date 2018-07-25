package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_164 extends TestCase
{
	@Test
	public void MISC2_060()
	{
		testParameters.setCurrentTestDescription("Verify that link in the email notification for the recent activities should be in the correct format and navigated to the user notification page when clicked.");
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
								properties.getProperty("GmailUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		gmailobj.enterCredentials();
		String Msg = dataTable.getData("Workflow", "Message");
		gmailobj.searchAndOpenMail(Msg);
	/*	gmailobj.recentActivityMailURL();
		gmailobj.verifyRecActURL();*/
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}