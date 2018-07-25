package testscripts.translation;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_394P4 extends TestCase {

	@Test
	public void translation_3465_14() {
		testParameters.setCurrentTestDescription("ALFDEPLOY-3465-Verify email template for notification mail, "
				+ "mail triggered while uploading via SharBox, mail triggered while uploading via Alfresco "
				+ "and aggregated notification mail are translated to German - Part 4");
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
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		String fileName = dataTable.getData("MyFiles", "FileName");
		String shareboxMailContent = dataTable.getData("Sharebox", "EditMessage");
		String shareboxMailContentFinal = shareboxMailContent.replace("uploadedFileName", fileName);
		gmailobj.verifyGmailContent(shareboxMailContentFinal);
		
	}
	
	

	@Override
	public void tearDown() {
		// Nothing to do
	}
	
	
	
	
	
}
