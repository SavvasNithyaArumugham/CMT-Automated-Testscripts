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
public class AUT_AG_039P5 extends TestCase {

	@Test
	public void sharebox_011() {
		testParameters
				.setCurrentTestDescription("[1]ALFDEPLOY-5270-Veify the configured subject displayed in Activity email when alfresco user updated a file into shared folder with new version.<br>"+
		"[1]ALFDEPLOY-5270-Veify the configured subject displayed in Activity email when alfresco user upload OR create a folder inside the shared folder.<br>"+
						"[1]ALFDEPLOY-5270-Veify the configured subject displayed in Activity email when alfresco user upload a file into shared folder.<br>"+
		"[1]ALFDEPLOY-5270-Veify the configured subject displayed in Activity email when external user upload a file into shared folder via External sharebox UI<br>"+
						"[1]ALFDEPLOY-5270-Veify the configured subject displayed in notification email on sharing a folder to external user<br>."+
		"[1]ALFDEPLOY-5271-Veify the configured title displayed in External sharebox UI.<br>"+
						"[1]ALFDEPLOY-4768-Verify external sharebox user receiving email notification when a file is updated with same file inside a shared folder with new version.");
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
		
		String message = dataTable.getData("Sharebox", "Message");
		String message2 = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message3 = dataTable.getData("Sharebox", "EditShareboxFieldsData");
		String message4 = dataTable.getData("Sharebox", "Notify_Sharer");
		String message5 = dataTable.getData("Sharebox", "Notifications");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folder2 = dataTable.getData("MyFiles", "CreateFileDetails");
		String file = dataTable.getData("MyFiles", "FileName");
	
		message = message + " " + folder2;
		message2 = message2 + " " + file;

		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		
		String activity1 ="A new folder (AutoTest1) has been added to a shared folder";
		
		String actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity1)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
		
		gmailobj.searchWFmessage(message2);
		
		String activity2 ="A new file (AlfrescoAutoDemoForTest.docx) has been uploaded to a shared folder";
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity2)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
		
		gmailobj.searchWFmessage(message3);
		
		String activity3 ="A new file has been uploaded to a folder shared by you - AutoTest";
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity3)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
		
		gmailobj.searchWFmessage(message4);
		
		String activity4 ="A file (AlfrescoAutoDemoForTest.docx) has been updated in a shared folder";
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity4)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
	
		gmailobj.searchWFmessage(message5);
		shareboxPg.navigateToShareboxLinkFromMail(folderName);
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, shareboxPg.shareBoxtitleXpath).getAttribute("span");
		
		if(actualactivity.contains("Pearson ShearBox")) {
			
			report.updateTestLog("Verify the configured title displayed in ShareBox UI ",
					"Title as expected.<b> Title <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured title displayed in ShareBox UI ",
					"Title not as expected.<b> Title <b> <br> " + actualactivity, Status.FAIL);
			
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}