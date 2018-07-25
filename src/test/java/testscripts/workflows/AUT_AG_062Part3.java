package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_062Part3 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_44()
	{
		testParameters.setCurrentTestDescription("To verify comment detail in the notification email, on task re-assignment of the rejected task(by Assignee2) - 'Simple Review and Approve' Workflow(Multiple Assignees) <br> - Part 3: Login with Initiater and Verify that user is able to click on 'Continue Review and Approve' button  task");
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
		
		taskPage.navigateToMyTasksMenu();
		
		taskPage.openCreatedOrAssignedTask();
		
		String reviewerComment = dataTable.getData("Tasks",
				"ReviewerComment");
		String status = dataTable.getData("Tasks", "Status");

		taskPage.performActionOnTaskFromMyTasksPage("Continue Review and Approve", reviewerComment, status);
	}

	@Override
	public void tearDown() {
		
	}

}
