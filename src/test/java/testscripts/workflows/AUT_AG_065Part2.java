package testscripts.workflows;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
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

public class AUT_AG_065Part2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void workflows_47() {
		testParameters
				.setCurrentTestDescription("To verify reviewer is not able to edit task - 'Simple Task' Workflow(Single Assignee\\Reviewer - Part2");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);

		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);

		String message = dataTable.getData("Workflow", "Message");

		homePage.navigateToTasksTab();
		taskPage.navigateToMyTasksMenu();
		taskPage.editCreatedOrAssignedTask(message);

		// taskPage.openCreatedOrAssignedTask(message);

		/*
		 * AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		 * signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		 * cancelWorkFlow(); doWorkFlow();
		 */
	}

	private void doWorkFlow() {

		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		//String dueDate = dataTable.getData("Workflow", "DueDate");
		String priority = dataTable.getData("Workflow", "Priority");
		String dueDate = new DateUtil().getCurrentDate();
		System.out.println("" + dueDate);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToTasksTab();
		taskPage.navigateToMyWorkFlows();
		taskPage.clickStartWorkflowBtn();
		taskPage.submitWorkFlow(taskType, message, dueDate, priority);
		taskPage.selectApprover();
		taskPage.clickStartWorkflowBtn();

		UIHelper.waitFor(driver);

		homePage.navigateToTasksTab();
		taskPage.navigateToMyWorkFlows();

		//taskPage.filterWFtasks();
		AlfrescoWorkflowPage workFlowPage = new AlfrescoWorkflowPage(
				scriptHelper);
		workFlowPage.checkWF(message+"_"+dueDate);
	}

	private void cancelWorkFlow() {

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		homePage.navigateToTasksTab();
		taskPage.navigateToMyWorkFlows();
		//taskPage.filterWFtasks();
		taskPage.cancelCreatedWorkflow();
	}

	@Override
	public void tearDown() {

	}

}
