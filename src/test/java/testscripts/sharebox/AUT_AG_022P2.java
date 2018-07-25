package testscripts.sharebox;

import org.testng.annotations.Test;

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
public class AUT_AG_022P2 extends TestCase {

	@Test
	public void sharebox_022() {
		testParameters.setCurrentTestDescription(
				"Verify the external user is not able to upload 0 byte/empty file into the Shared Folder - Part2: Upload empty file<br>"
						+ "ALFDEPLOY-4405_Verify user is not able to upload Zero byte file via External Sharebox UI.<br>"
						+ "ALFDEPLOY-3642_Verify the error message while user uploading Zero byte files with different formats (.txt,.doc,.xlsx,.bmp,.pptx) in external sharebox UI");
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
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String errorMsg=".//div[@id='root']/div//span";
		String invalidError="Oops, looks like you have visited an invalid link";
		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);
		for (String files : fileName) {
			shareboxPg.uploadEmptyFileInSharedFolder(filePath, files);
		}
		if(!UIHelper.getTextFromWebElement(driver, errorMsg).contains(invalidError))
		{
			report.updateTestLog("Verify invalid link error message is not displayed upon uploading zero byte files",
					"Invalid link error message <br><b>Message :</b>"+invalidError +" is not displayed upon uploading zero byte files", Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify invalid link error message is not displayed upon uploading zero byte files",
					"Invalid link error message <br><b>Message :</b>"+invalidError +" is displayed upon uploading zero byte files", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}