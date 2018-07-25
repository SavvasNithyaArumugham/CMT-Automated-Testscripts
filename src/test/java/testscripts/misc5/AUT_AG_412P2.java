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
public class AUT_AG_412P2 extends TestCase {

	@Test
	public void navigateTenFoldersSharebox2() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3498_Verify user cannot navigate to 10thth deep folder via External \"\r\n" + 
						"				+ \"sharebox UI (When the Alfresco.global.properties set to 7 - Part2 )");
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
		int errorfolderLevelBeforeValue= shareFolderNameList.length-1;
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);	
		
		
		for(int i=0;i<errorfolderLevelBeforeValue;i++) {
			shareboxPg.navigateShareUI(shareFolderNameList[i]);
			UIHelper.waitForPageToLoad(driver);
			
		}
		
		AlfrescoShareboxPageTest shareboxPgTest= new AlfrescoShareboxPageTest(scriptHelper);
		shareboxPgTest.verifyFolderNavigationError(shareFolderNameList[errorfolderLevelBeforeValue]);
				
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}