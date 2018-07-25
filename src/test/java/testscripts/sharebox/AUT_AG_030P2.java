package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
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
public class AUT_AG_030P2 extends TestCase {

	@Test
	public void runAUT_AG_181P2() {
		testParameters
				.setCurrentTestDescription("verify whether external user is able to view 'File uplaod failed' error message when an uplaod fails via ShareBox -Part2 : Abort an upload");
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
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		for (String folderName : folderNamesList) {
		shareboxPg.navigateToShareboxLinkFromMail(folderName);	
		}
		shareboxPg.abortUploadFileInSharedFolder(filePath, fileName);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}