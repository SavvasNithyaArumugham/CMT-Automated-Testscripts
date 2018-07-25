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
public class AUT_AG_034P4 extends TestCase {

	@Test
	public void sharebox_034() {
		testParameters
				.setCurrentTestDescription("1. Verify whether the Unzip option is displayed in alfresco for the zip file which is uploaded via sharebox by External User"
				+ "<br>2. Verify whether the user can share the folder externally which is unzipped form the Zip file - Part4: Click on Browse button and upload file using shared external mail");
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
		String foldershare = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(foldershare);
		
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		
		String uploadedFilesInShareboxFolder = dataTable.getData("Sharebox", "UploadedFilesInShareboxFolder");
		
		if (uploadedFilesInShareboxFolder.contains(",")) {
			String splittedFileNames[] = uploadedFilesInShareboxFolder.split(",");
			for (String fileNameVal : splittedFileNames) {
				shareboxPg.downloadSharedFilesUsingExternalMailLink(fileNameVal, fileDownloadPath);
			}
		} else {
			shareboxPg.downloadSharedFilesUsingExternalMailLink(uploadedFilesInShareboxFolder, fileDownloadPath);
		}
		
		shareboxPg.uploadFileInSharedFolder(filePath, fileName);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}