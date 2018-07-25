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
import com.pearson.framework.selenium.Browser;


public class AUT_AG_060Part2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_42()
	{
		testParameters.setCurrentTestDescription("To verify comment detail in the task re-assignment notification email - 'Simple Review and Approve' Workflow (Single Assignee) <br> Part1: Reassign the Task with comments");
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
	
		String message = dataTable.getData("Workflow", "Message");
        taskPage.navigateToMyTasksMenu();
       
        taskPage.openCreatedOrAssignedTask(message);
        String comment = dataTable.getData("Workflow", "Comment");
        
        taskPage.rejectWorkflowApproveTask(comment);
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}
	
}
