package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoWorkflowPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_032P2 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_24()
	{
		testParameters.setCurrentTestDescription("1.Verify that assignee is able to change the Priority for the Simple workflow task"
				+"<br>2.Verify that once assignee change the priority of the task,the updated priority is not visible to assigner for Review and Approve Workflow."
				+"<br>3.Verify that default priority visible to assignee is Medium for Review and Approve Workflow task"
				+"<br>4.Verify user able to click on 'Please select a workflow drop down and abel to select the required workflow from the drop down"
				+"<br>5.Verify that user is able to see options in the Assignees and 'Required Approval Percentage' option should not be present"
				+ "<br>Part2: Verify assignee is able to change the Priority");
		
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
		AlfrescoWorkflowPage appWorkflowPg = new AlfrescoWorkflowPage(
				scriptHelper);
		AlfrescoWorkflowPageTest appWorkflowPgTest = new AlfrescoWorkflowPageTest(
				scriptHelper);
				
		homePage.navigateToTasksTab();
		
		taskPage.navigateToMyTasksMenu();
		
		String Message = dataTable.getData("Workflow", "Message");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		
		String Msg = Message + "_" + strDate;
		taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Msg);
		
		String priority = dataTable.getData("Workflow", "Priority");
		
		appWorkflowPg.changePriorityInEditTaskPg(priority);
		
		appWorkflowPg.clickOnSaveBtnInEditTaskPg();
		
		/*taskPage.openCreatedOrAssignedTask();
		
		appWorkflowPgTest.checkChangedTaskPriority(priority);*/
	}

	@Override
	public void tearDown() {
		
	}

}
