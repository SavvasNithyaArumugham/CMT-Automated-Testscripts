package testscripts.sharebox;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_040P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters
				.setCurrentTestDescription("[1]ALFDEPLOY-4582_Verify user is able to perform Edit external sharing action via Folder details page for the shared folder.<br>"+
		"[1]ALFDEPLOY-4582_Verify user is able to perform stop external sharing action via Folder details page for the shared folder.<br>"+
		"[1]ALFDEPLOY-4582_Verify user is able to share the folder to external user via Folder details page.<br>"+
		"[1]ALFDEPLOY-4582_Verify user is able to share the subfolder to external user via Folder details page when the root folder is already shared."+
		"[1]ALFDEPLOY-4582_Verify user is able to view the updated details once the details are updated via Edit external sharing option form view details page.<br>");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

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
		String file2 = dataTable.getData("MyFiles", "CreateFolder");
		
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("E, MMM dd, yyyy");
		String dates = sf.format(date);
		
		String finalshareBoxmaildatexpath = gmailobj.shareBoxmaildatexpath.replace("CRAFT", dates);
	
		message = message + " " + folder2;
		message4 = message4 + " " + file;
		message2 = message2 + " " + file;
		String message6 = message2 + " " + file2;

		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		
		String activity1 ="A new folder (AutoTest5) has been added to a shared folder";
		
		String actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity1) && UIHelper.checkForAnElementbyXpath(driver, finalshareBoxmaildatexpath)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
		
		gmailobj.searchWFmessage(message4);
		
		String activity4 ="A new file (TC79.txt) has been uploaded to a shared folder";
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity4) && UIHelper.checkForAnElementbyXpath(driver, finalshareBoxmaildatexpath)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
		
		gmailobj.searchWFmessage(message2);
		
		String activity2 ="A file (TC79.txt) has been updated in a shared folder";
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity2) && UIHelper.checkForAnElementbyXpath(driver, finalshareBoxmaildatexpath)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
		
		gmailobj.searchWFmessage(message3);
		
		String activity3 ="A new file (TC79.txt) has been uploaded to a shared folder";
		
		actualactivity = UIHelper.findAnElementbyXpath(driver, gmailobj.shareBoxactivityxpath).getText();
		
		if(actualactivity.contains(activity3) && UIHelper.checkForAnElementbyXpath(driver, finalshareBoxmaildatexpath)) {
			
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was recieved successfully.<b> Mail Content <b> <br> " + actualactivity, Status.PASS);
			
		}else {
			report.updateTestLog("Verify the configured subject displayed in Activity email ",
					"Mail was not received .<b> Mail Content <b> <br> " + actualactivity, Status.FAIL);
		}
	
		/*
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
	*/
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}