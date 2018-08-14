package testscripts.sharebox;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_026P2 extends TestCase {

	@Test
	public void sharebox_026() {
		testParameters
				.setCurrentTestDescription("Verify whether internal user is able to share folder to multiple external users -Part2 : Open the sharebox from mail");
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
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
	
		gmailobj.enterCredentials();
		gmailobj.searchWFmessage("ShareBox - New folder available in shared folder");
		driver.navigate().back();
		
		gmailobj.searchWFmessage("ShareBox - New file available in shared folder");
		driver.navigate().back();
		
		
		gmailobj.searchWFmessage(message);			
		shareboxPg.navigateToShareboxLinkFromMail(folderName);	
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		shareboxPg.performDownloadZipInShareboxPage(folderName+".zip", fileDownloadPath);
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}