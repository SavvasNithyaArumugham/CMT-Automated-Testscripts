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
public class AUT_AG_007P2 extends TestCase {

	@Test
	public void sharebox_007() {
		testParameters
				.setCurrentTestDescription("1. Verify user is able to download the shared folder as a zip file by clicking on 'Download as Zip' button"
				+ "<br>2. Verify user is able to download the shared folder by clicking on 'Download ZIP' button- Check the 'Download ZIP' option in shared folder using shared external mail"
				+ "<br>3. Verify user is able to download the individual files inside the shared folder by clicking on 'Download' button displayed below each file - Check the 'Download' option in shared folder using shared external mail");
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
		String fileName = dataTable.getData("MyFiles", "FileName");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);
		
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		
		if (fileName.contains(",")) {
			String splittedFileNames[] = fileName.split(",");
			for (String fileNameVal : splittedFileNames) {
				shareboxPg.downloadSharedFilesUsingExternalMailLink(fileNameVal, fileDownloadPath);
			}
		} else {
			shareboxPg.downloadSharedFilesUsingExternalMailLink(fileName, fileDownloadPath);
		}
		
		shareboxPg.performDownloadZipInShareboxPage(folderName+".zip", fileDownloadPath);
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}