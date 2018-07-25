package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoEditTaskPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
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
 * @author Cognizant
 */
public class AUT_AG_023P4 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_16()
	{
		testParameters.setCurrentTestDescription("To check if the coordinator who claimed the task is able to change task status to under review and save changes. Initiator receives email notification once the changes are saved by coordinator.");
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

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{

		String dueDate = new DateUtil().getCurrentDate();		
			
	/*	GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoPearsonMailPage pearsMailPg = new AlfrescoPearsonMailPage(scriptHelper);
		gmailobj.enterCredentials();
		gmailobj.searchWFmessage(keyword);	
		Message = Message+"_"+dueDate;	
		pearsMailPg.clickOnEditTaskLink(Message);
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.switchtab(1);*/
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoWorkflowPage workflowPg = new AlfrescoWorkflowPage(scriptHelper);

		
		String message = dataTable.getData("Workflow", "Message");		
		String status = dataTable.getData("Workflow", "Description");
		
		
		message =message+"_"+dueDate;
		
		homePage.navigateToTasksTab();
		taskPage.navigateToMyTasksMenu();
		taskPage.filterWFtasks();
		
		workflowPg.checkWFStatus(message);
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		AlfrescoWorkflowPage wfPage = new AlfrescoWorkflowPage(scriptHelper);
		
		AlfrescoEditTaskPage editTaskPg = new AlfrescoEditTaskPage(scriptHelper);
		String progressStatusValues = dataTable.getData("Tasks", "Progress");
		String[] statusValues = progressStatusValues.split(",");
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		if(editTaskPg.checkStatusDropDownValues(statusValues)){
			editTaskPg.clickStatusDropDown();
			report.updateTestLog("Verify the Status options in Progress section",
					"Verified successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify the Status options in Progress section",
					"Not able to Verify", Status.FAIL);
		}
		
		UIHelper.waitFor(driver);
		
		if(editTaskPg.checkDefaultStatusDropDownValue(statusValues[0])){
			report.updateTestLog("Verify the Default Status in Progress section",
					"Verified successfully"
					+"</br><b> Verified Default Status : </b>"+statusValues[0], Status.PASS);
		}else{
			report.updateTestLog("Verify the Default Status in Progress section",
					"Not able to Verify", Status.FAIL);
		}
	
		
		String reviewer = dataTable.getData("Workflow", "UserName");
	
	//	UIHelper.pageRefresh(driver);

		taskPage.selectReviewer(reviewer);
		workflowPg.selectWFStatus(status.trim());	
		workflowPg.clickOnSubmitTask();	
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}