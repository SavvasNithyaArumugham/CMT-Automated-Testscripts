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
public class AUT_AG_036P2 extends TestCase {

	@Test
	public void sharebox_035() {
		testParameters
				.setCurrentTestDescription("Verify the 'A file already exists with the same name' error message once the file is upload successfully - Part2: Click on Browse button for upload same file again and check the meesage");
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
		String warningMsg = dataTable.getData("Sharebox", "ErrorMessageValue");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);
		
		shareboxPg.uploadFileInSharedFolder(filePath, fileName);
		
		shareboxPg.verifyWarningMsgOnUploadingDuplicateFile(filePath, fileName, warningMsg);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}