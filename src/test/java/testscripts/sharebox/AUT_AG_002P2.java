package testscripts.sharebox;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_002P2 extends TestCase {

	@Test
	public void sharebox_002() {
		testParameters
				.setCurrentTestDescription("1. Verify that an email is triggered to the external user, once the internal user share the folder externally"
						+ "<br>2. Verify the notification email contains the link to the shared folder"
						+ "<br>3. Verify user is able to access the shared folder using the link provided in the notification email"
						+ "<br>4. Verify user is able to access the shared folder and view the shared folder content using the link provided in the notification email"
						+ "<br>5. Verify the user receives the notification email immediately, if the Notifications is set as 'immediately' while sharing the Folder"
						+ "<br>6. Verify the Sharing is not removed , if the user clicks on 'Cancel' button after selecting the 'Stop Sharing Externally' option"
						+ "<br>Part2: Verify shared folder link in received notification email and access the same");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

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
		String fileName = dataTable.getData("MyFiles", "FileName");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(fileName);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}