package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
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
public class AUT_AG_038P2 extends TestCase {

	@Test
	public void sharebox_037() {
		testParameters
				.setCurrentTestDescription("1. Verify Edit Sharing properties screen is update with new Email notification field as Language"
						+ "<br>2. Verify email template for notification mail, mail triggerred while uploading via SharBox, mail triggerred while uploading via Alfresco and aggregated notification mail"
						+ "<br>Part2: Verify received notification email for shared folder from Alfresco and Sharebox screen");
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
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String recievedMaiSub = dataTable.getData("Sharebox", "EditMessage");
		
		String recievedMailFromAlfresco="", recievedMailFromSharebox="";
		if(recievedMaiSub.contains(","))
		{
			String splittedMailSub[] = recievedMaiSub.split(",");
			if(splittedMailSub!=null&splittedMailSub.length>1)
			{
				recievedMailFromAlfresco=splittedMailSub[0];
				recievedMailFromSharebox=splittedMailSub[1];
			}
			else
			{
				recievedMailFromAlfresco="ShareBox - Nouveau fichier disponible";
				recievedMailFromSharebox="ShareBox - Nouveau fichier importé";
			}
		}
		
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoShareboxPageTest shareboxPgTest = new AlfrescoShareboxPageTest(scriptHelper);
		gmailobj.enterCredentials();
		
		gmailobj.searchWFmessage(recievedMailFromAlfresco);
		shareboxPgTest.verifyRecievedShareboxMailSubInFrench(recievedMailFromAlfresco);
		
		gmailobj.searchWFmessage(message);
		
		shareboxPgTest.verifyRecievedShareboxMailSubInFrench();
		
		String parentDrive = driver.getWindowHandle();
		
		shareboxPg.navigateToShareboxLinkFromMail(folderName);
		
		shareboxPg.uploadFileInSharedFolder(filePath, fileName);
		
		driver.close();
		try {
		driver.switchTo().window(parentDrive);
		driver.switchTo().defaultContent();
		} catch(Exception e) {
			
		}
		gmailobj.searchWFmessage(recievedMailFromSharebox);
		//UIHelper.pageRefresh(driver);
		shareboxPgTest.verifyRecievedShareboxMailSubInFrench(recievedMailFromSharebox);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}