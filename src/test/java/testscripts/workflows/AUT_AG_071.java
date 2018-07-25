package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoEditTaskPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
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
public class AUT_AG_071 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void workflows_50() {
		testParameters.setCurrentTestDescription("Verify that assignee can close the task");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
	/*	AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		String file = dataTable.getData("Sites", "FileName");
		sitesPage.documentdetails(file);
		
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Msg = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Msg, DueDate, Priority);
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();

		AlfrescoTasksPage tasksPage = new AlfrescoTasksPage(scriptHelper);
		tasksPage.navigateToMyTasksMenu();
		tasksPage.acceptorRejectWFTask();*/
		
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoTasksPage taskPage = homePage.navigateToTasksTab();
		
		homePage.navigateToTasksTab();
		
		taskPage.navigateToMyWorkFlows();
		
		/*taskPage.cancelCreatedWorkflow();*/
		
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		//String dueDate = dataTable.getData("Workflow", "DueDate");
		String priority = dataTable.getData("Workflow", "Priority");
		String dueDate = new DateUtil().getCurrentDate();
		
		taskPage.startWorkFlowFromTasksPage(taskType, message, dueDate, priority);
		taskPage.selectApprover();
		taskPage.clickStartWorkflowBtn();
		//taskPage.openCreatedOrAssignedTask();
		
		homePage.navigateToTasksTab();
		AlfrescoMyTasksPage myTaskPage = taskPage.navigateToMyTasksMenu();
		//AlfrescoEditTaskPage editTaskPage = myTaskPage.selectTaskFromActiveTasksList();
		taskPage.openCreatedOrAssignedTask(message);
		taskPage.saveTask();
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}