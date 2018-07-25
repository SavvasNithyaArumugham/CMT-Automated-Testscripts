package testscripts.misc5;

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
public class AUT_AG_415P2 extends TestCase {

	@Test
	public void navigateTenFoldersSharebox2() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3526_Verify the externanl ShareBox navigation after external user uploads"
						+ "file inside the 4 level subfolder - Part2");
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
		String[] shareFolderNameList=dataTable.getData("MyFiles", "CreateFileDetails").split(",");
		int folderLevelAssert= shareFolderNameList.length;
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String filePath=dataTable.getData("MyFiles", "FilePath");
		String uploadFilename=dataTable.getData("MyFiles", "FileName");
		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);	
		
		for(int i=0;i<folderLevelAssert;i++) {
			shareboxPg.navigateShareUI(shareFolderNameList[i]);
			UIHelper.waitForPageToLoad(driver);
			
		}
		shareboxPg.uploadFileInSharedFolder(filePath,uploadFilename);
		shareboxPg.navigateBackInShareUI(shareFolderNameList[folderLevelAssert-1]);
		
						
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}