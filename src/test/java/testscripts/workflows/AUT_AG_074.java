package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_074 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_53()
	{
		testParameters.setCurrentTestDescription("Verify that task assigner is able to see when a comment has been viewed by an assignee");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		
		
		homePage.navigateToTasksTab();
		
		taskPage.navigateToMyWorkFlows();
		
	
		
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		//String dueDate = dataTable.getData("Workflow", "DueDate");
		String priority = dataTable.getData("Workflow", "Priority");
		
		String dueDate = new DateUtil().getCurrentDate();
		taskPage.startWorkFlowFromTasksPage(taskType, message, dueDate, priority);
		
		taskPage.selectApprover();
		
		taskPage.clickStartWorkflowBtn();
		
		homePage.navigateToTasksTab();
		
		taskPage.navigateToMyTasksMenu();
		
		taskPage.openCreatedOrAssignedTask();
		
		taskPage.editTaskFromMyTasksPage();
		
		
		homePage.navigateToHomePage();
		
		String dashletNme = dataTable.getData("Home", "DashletName");
		if(!homePage.checkDashletInUserDashboard(dashletNme))
		{
			homePage.addDashletInCustomDashBoard();
		}
		
		homePage.openTaskFromMyDashlet();
		
		AlfrescoTasksPageTest taskPgTest = new AlfrescoTasksPageTest(scriptHelper);
		taskPgTest.verifyTasksReviewComments();
	}

	@Override
	public void tearDown() {
		
	}

}
