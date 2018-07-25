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
 * @author Lokesh
 *
 */
public class AUT_AG_043P2 extends TestCase {

	@Test
	public void sharebox_011() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4768-Verify external sharebox user receiving email notification when a file is updated with same file inside a shared folder with new version.<br>"+
		"ALFDEPLOY-4768-Verify external sharebox user receiving email notification when a file is updated with different file inside a shared folder with new version.<br>"+
						"ALFDEPLOY-4768-Verify external sharebox user receiving email notification when a files are updated with same file inside a shared folder with new version via selected items menu.<br>"+
		"ALFDEPLOY-4768-Verify external sharebox user receiving email notification when a file are updated with different file inside a shared folder with new version via document details page.<br>"+
						"ALFDEPLOY-4768-Verify external sharebox user receiving email notification when a folder is updated by renaming a foldername inside a shared folder.."+
		"ALFDEPLOY-4768-Verify external sharebox user receiving email notification when a file is updated by renaming a filename inside a shared folder.<br>");
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
		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);

		gmailobj.enterCredentials();	
		
		
		String activity1 ="A file (TC68.txt) has been updated in a shared folder - AUT_AG_043";
		gmailobj.searchWFmessage(activity1);
		
		String actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity1)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
		
		
		
		String activity2 ="A file (TC78.txt) has been updated in a shared folder - AUT_AG_043";
		gmailobj.searchWFmessage(activity2);
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity2)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
		
		
		
		String activity3 ="A new folder (ShareRenamedFile) has been addedto a shared - AUT_AG_043";
		gmailobj.searchWFmessage(activity3);
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity3)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
		
		
		
		String activity4 ="A new folder (ShareRenamedFolderc) has been added in a shared folder - AUT_AG_043";
		gmailobj.searchWFmessage(activity4);
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity4)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
	
	
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}