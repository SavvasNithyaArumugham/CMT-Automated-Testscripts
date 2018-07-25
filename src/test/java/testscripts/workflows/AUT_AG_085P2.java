package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_085P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_58()
	{
		testParameters.setCurrentTestDescription("1.Verify that user is able to add folders in start workflow page by clicking on Add button"
				+"<br>2.Verify that Assignee is getting email notification when initiator start workflow for folders"
				+"<br>3.Verify that Assignee is able to download the folder by the link provided in email notification"
+"<br>4.Verify that user is able to view the workflow in My Task Dashlet of the assignee when initiator assign the workflow started for folders");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		String Message = dataTable.getData("Workflow", "Message");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		String Msg = Message + "_" + strDate;
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		String initialFolder = dataTable.getData("Sites", "FileName");
		//gmailobj.openGmail();
		gmailobj.enterCredentials();
		gmailobj.searchWFmessage(Msg);
		//gmailobj.searchmailwithWFmessage(Msg);
		
		
		if (UIHelper.findAnElementbyXpath(driver, gmailobj.fileURLXpath).isDisplayed()) {
			UIHelper.highlightElement(driver, gmailobj.fileURLXpath);
			UIHelper.findAnElementbyXpath(driver, gmailobj.fileURLXpath).click();
			report.updateTestLog("Verify File URL in Pearson mail",
					"Mail received successfully" + "<br /><b>File URL : </b>"
							+ UIHelper.findAnElementbyXpath(driver, gmailobj.fileURLXpath).getText(),
					Status.PASS);
		} else {
			report.updateTestLog("Verify File URL in Pearson mail", "Mail not received successfully",
					Status.FAIL);
		}
	//	gmailobj.searchFileURLLink(Msg);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		try{
		Thread.sleep(10000);
		}catch(Exception e){
			
		}
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.switchtab(1);
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		try{
		Thread.sleep(10000);
	
		String title = ".//*[@title='CRAFT']//following::h1";
		String finaltitle = title.replace("CRAFT", initialFolder);
		UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finaltitle);
		if (UIHelper.findAnElementbyXpath(driver, finaltitle).isDisplayed()) {
			UIHelper.highlightElement(driver, finaltitle);
			
			report.updateTestLog("Verify Navigation from WF Mail",
					"Navigation successful from WF Link " + "<br /><b>Folder : </b>"
							+ UIHelper.findAnElementbyXpath(driver,finaltitle).getText(),
					Status.PASS);
		} else {
			report.updateTestLog("Verify Navigation from WF Mail",
					"Navigation failed from WF Link ",
					Status.FAIL);
		}
	
		}catch(Exception e){
			
		}
		
		
		
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}