package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
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
public class AUT_AG_410P2 extends TestCase {

	@Test
	public void navigateSevenFoldersSharebox2() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3498_Verify user can navigtae to 7th folder inside a subfolder via External sharebox - Part2 Verify in ShareBox");
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
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);	
		
		for(int i=0;i<folderLevelAssert;i++) {
			shareboxPg.navigateShareUI(shareFolderNameList[i]);
			UIHelper.waitForPageToLoad(driver);
			
		}
		
		AlfrescoShareboxPageTest shareboxPgTest= new AlfrescoShareboxPageTest(scriptHelper);
		
		if((shareboxPgTest.getCurrentFoldernameInSharebox()).equalsIgnoreCase(shareFolderNameList[folderLevelAssert-1])) {
			report.updateTestLog("Verify User can navigate to desired folder", "User navigated to the desired folder",
					Status.PASS);
			
		}else {
			report.updateTestLog("Verify User can navigate to desired folder", "User not navigated to the desired folder",
					Status.FAIL);
			
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}