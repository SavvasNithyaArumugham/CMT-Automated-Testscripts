package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
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
public class AUT_AG_054P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_40()
	{
		testParameters.setCurrentTestDescription("To verify assignee name in the task assignment notification email - 'Simple Task' Workflow - Part1");
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
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToTasksTab();
		
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		taskPage.navigateToMyWorkFlows();
		
		//taskPage.cancelCreatedWorkflow();
		
		String userName = dataTable.getData("Workflow", "UserName");
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;
		
		
		taskPage.startWorkFlowFromTasksPage(taskType, Message, strDate, Priority);
		
		taskPage.selectApprover();
		
		taskPage.clickStartWorkflowBtn();	
		
		/*homePage.navigateToTasksTab();
        taskPage.navigateToMyTasksMenu();
       
        taskPage.openCreatedOrAssignedTask(message);
        
        
        taskPage.filterWFtasks();
        AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		wfPageObj.checkWFStatus(Msg);
        taskPage.completeAssignedTask();*/
	
		 
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}