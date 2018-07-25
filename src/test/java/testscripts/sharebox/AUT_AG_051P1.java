package testscripts.sharebox;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_051P1 extends TestCase {

	@Test
	public void sharebox_015() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3638_Verify user can download a root folder contains 2 level subfolder and each folders has some files in it-Part 2:Verify the external user mailbox");
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
		String[] folderName = dataTable.getData("MyFiles", "CreateFolder").split(",");
		String[] files = dataTable.getData("MyFiles", "FileName").split(",");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String downloadPath = properties.getProperty("DefaultDownloadPath");
		String sharedFolder = dataTable.getData("Sharebox", "ShareboxFieldsData");
		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(sharedFolder);
		String zipFileName=folderName[0]+".zip";
		shareboxPg.performDownloadZipInShareboxPage(zipFileName,downloadPath);
		
		for(String name:files)
		{
		if (docDetailsPageTest.checkFileinDownloadedZipFile(downloadPath, zipFileName, name)) {
			report.updateTestLog("Verify file/folder present in shared folder is available in downloaded zip file",
					"file/folder present in shared folder is available in downloaded zip file" + "<br><b> Actual Result:</b> FileName:"
							+ name,
					Status.PASS);
		} else {

			report.updateTestLog("Verify file/folder present in shared folder is available in downloaded zip file",
					"file/folder is missing in shared folder is available in downloaded zip file <br> <b>Expected Result:</b> FileName:"
							+ name + "<br><b> Actual Result:</b> FileName:" + name,
					Status.FAIL);
		}
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}