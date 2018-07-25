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

public class AUT_AG_031P3 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_23()
	{
		testParameters.setCurrentTestDescription("Verify the outcome value displayed in workflow task detail when approver approves/rejects the task<br> - Part 3: Verify Outcome filed ");
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
		
		//taskPage.navigateToMyTasksMenu();
		taskPage.navigateToMyWorkFlows();
		

		String Message = dataTable.getData("Workflow", "Message");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		
		String Msg = Message + "_" + strDate;
		taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Msg);
		String field = dataTable.getData("Tasks",
				"PreviousCommentsFiled");
		String value = dataTable.getData("Tasks",
				"PreviousCommentsValue");
		AlfrescoWorkflowPageTest appWorkflowPgTest = new AlfrescoWorkflowPageTest(
				scriptHelper);
		
		appWorkflowPgTest.verifyPreviousComments(field, value);
		
		//taskPage.verifyRejectOutcome();

		
	
	}
	@Override
	public void tearDown() {
		
	}

}
