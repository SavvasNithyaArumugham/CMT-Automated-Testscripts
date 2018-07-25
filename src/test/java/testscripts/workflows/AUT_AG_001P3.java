package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_001P3 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_01()
	{
		testParameters
		.setCurrentTestDescription("1. Verify that user is able to initiate a workflow for a file"
				+"<br>2. Verify that when a task is marked Accepted/Complete by any assignee then it will be marked completed for all assingees.");

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
		AlfrescoTasksPageTest taskPageTest = new AlfrescoTasksPageTest(scriptHelper);
		String Message = dataTable.getData("Workflow", "Message");

		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;
		
		AlfrescoTasksPageTest taskPgTest = new AlfrescoTasksPageTest(scriptHelper);
				
		homePage.navigateToTasksTab();
		
		taskPage.navigateToMyWorkFlows();
		taskPage.filterWFtasks();
		 taskPage.openCreatedOrAssignedTask(Msg);
	        
	       taskPageTest.verifyStatusOfWorkFlow("Workflow is Complete");
	/*	//taskPage.navigateToMyTasksMenu();
	        homePage.navigateToTasksTab();
			
			taskPage.navigateToMyWorkFlows();
		taskPage.clickOnCompletedTasks();
		
		 taskPage.openCreatedOrAssignedTask(Msg);
		
		taskPgTest.verifyCompletedTasksStatus();*/
	}

	@Override
	public void tearDown() {
		
	}

}
