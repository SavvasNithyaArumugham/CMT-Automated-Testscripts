package testscripts.release184;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
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
public class AUT_AG_063P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_063P2() {
		testParameters
				.setCurrentTestDescription("Verify newly uploaded file in the Shared Folder by the external user is reflected in the alfresco site - Part2: Click on Browse button and upload file using shared external mail");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
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
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoShareboxPageTest shareboxPgTest = new AlfrescoShareboxPageTest(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);
		
		
		
		String mailId = dataTable.getData("Sharebox", "ExternalMailID");
		shareboxPg.uploadFileInSharedFolder(filePath, fileName);
		
		shareboxPgTest.checkUserNameInUploadedBy(mailId);
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}