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
public class AUT_AG_021P2 extends TestCase {

	@Test
	public void sharebox_021() {
		testParameters
				.setCurrentTestDescription("Verify user is not getting any email notification, when the “Notify Sharer” and “Notify Shared with” checkboxes are unchecked in the Sharebox folder window - Part2: Verify mail");
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
		String folderName = dataTable.getData("MyFiles", "FileName");
		String fileName = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		String mailUrl = properties.getProperty("GmailUrl");
		System.out.println(mailUrl);
		gmailobj.searchWFmessage(message);			
		shareboxPg.navigateToShareboxLinkFromMail(folderName);
		shareboxPg.uploadFileInSharedFolder(filePath,fileName);	
		driver.navigate().to(mailUrl);
						
		if (!gmailobj.checkMailCount(message,1)) {
			
			report.updateTestLog("Verify Sharebox mail received",
					"Sharebox mail is not received", Status.PASS);
		} else {
			report.updateTestLog("Verify Sharebox mail received",
					"Sharebox mail is received", Status.FAIL);

		}	

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}