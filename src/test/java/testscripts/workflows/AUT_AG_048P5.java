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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 */
public class AUT_AG_048P5 extends TestCase{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_34()
	{
		testParameters.setCurrentTestDescription("Verify Approvers and Reviewers recieves the Workflow that are assigned to them. - Verify Approver MyTask dashlet");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.HtmlUnit);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
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
		
	//	taskPage.openCreatedOrAssignedTask();
		
		String message = dataTable.getData("Workflow", "Message");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String Msg = message + "_" + strDate;
		
		taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Msg);
		
		String reviewerComment = dataTable.getData("Tasks",
				"ReviewerComment");
		String status = dataTable.getData("Tasks", "TaskName");

		taskPage.performActionOnTaskFromMyTasksPage("Continue Review and Approve", reviewerComment, status);
	}

	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
