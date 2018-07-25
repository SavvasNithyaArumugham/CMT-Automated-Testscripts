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
public class AUT_AG_008P2 extends TestCase {

	@Test
	public void sharebox_008() {
		testParameters.setCurrentTestDescription(
				"Verify newly uploaded file in the Shared Folder by the external user is reflected in the alfresco site"
						+ "<br>Part2: Click on Browse button and upload file using shared external mail<br>"+
						"ALFDEPLOY-4405_Verify user is able to select both Zero and Non Zero byte file via External Sharebox UI from Upload section.");

		// + "<br>2. Verify the external user is able to cancel the upload of
		// the new file into the Shared folder by clicking on the 'Abort'
		// button"
		// + "<br>Part2: a) Click on Browse button for upload file and click
		// 'Abort' button"

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("GmailUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		String message = dataTable.getData("Sharebox", "Message");
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);

		// shareboxPg.abortUploadFileInSharedFolder(filePath, fileName);

		shareboxPg.uploadFileInSharedFolder(filePath, fileName[0]);
		shareboxPg.uploadEmptyFileInSharedFolder(filePath,fileName[1]);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}