package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Verify that user is able to view all open tasks
 * 
 * @author Cognizant
 */
public class AUT_AG_002 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void workflows_02() {
		testParameters
				.setCurrentTestDescription("1. Verify that user is able to view all open tasks"
						+"<br> 2. Verify that user is able to initiate a workflow and select a Due Date"
						+"<br> 3. Verify that Date and Time dispalyed in local time zone, for a task");
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
		
		homePage.navigateToTasksTab();
		
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		taskPage.navigateToMyWorkFlows();

		String userName = dataTable.getData("Workflow", "UserName");
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		
		String Msg = Message + "_" + strDate;
		taskPage.startWorkFlowFromTasksPage(taskType, Message, strDate, Priority);
		taskPage.selectApprover();	
		taskPage.clickStartWorkflowBtn();
		
		taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Message + "_" + strDate);
		
		AlfrescoTasksPageTest taskPageObj = new AlfrescoTasksPageTest(scriptHelper);
		taskPageObj.verifyAssigneeDisplayedinWorkflow();
		taskPageObj.compareWorflowDueDateDetails(strDate);
		taskPageObj.compareWorflowStartedDetailsWithCurrentSystemDetails();
		

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}