package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_018 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_15()
	{
		testParameters.setCurrentTestDescription("Verify User can able to start Simple Task workflow from Work flow tab in alfresco"
				+ "<br>Part1: Start 'Simple Task' workflow by assigning 'User1'");
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
		
		UIHelper.waitFor(driver);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToWFStartedTab();
		taskPage.filterWFtasks();
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		wfPageObj.checkWFStatus(message+"_"+dueDate);
	}

	@Override
	public void tearDown() {
		
	}

}
