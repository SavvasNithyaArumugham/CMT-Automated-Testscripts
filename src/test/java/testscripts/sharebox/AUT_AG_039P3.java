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
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_039P3 extends TestCase {

	@Test
	public void sharebox_011() {
		testParameters
				.setCurrentTestDescription("Upload of files into Shared folder and sub folder by external User");
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
		String folderName = dataTable.getData("MyFiles", "Version");
		String folder2 = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String file2 = dataTable.getData("MyFiles", "RelationshipName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);
		shareboxPg.uploadFileInSharedFolder(filePath, fileName);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		shareboxPg.navigateShareUI(folder2);
		shareboxPg.uploadFileInSharedFolder(filePath, file2);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}