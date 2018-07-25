package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * 
 * @author Lokesh
 */
public class AUT_AG_038P1 extends TestCase{

private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_28()
	{
		testParameters.setCurrentTestDescription("Verify that reviewer gets mails if no document is attach to the workflow - Part1:Start workflow ");
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
	
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);		
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");		
		String dueDate = new DateUtil().getCurrentDate();		
		//message = message+"_"+dueDate;
		String priority = dataTable.getData("Workflow", "Priority");
		String reviwers = dataTable.getData("Workflow", "UserName");
		String approvers = dataTable.getData("Workflow", "ApprovalPercentage");	
		
		homePage.navigateToTasksTab();
		taskPage.navigateToMyWorkFlows();	
		taskPage.clickStartWorkflowBtn();			
		taskPage.submitWorkFlow(taskType, message, dueDate, priority);
		taskPage.selectApprover(approvers);
		taskPage.selectReviewer(reviwers);	
		taskPage.clickStartWorkflowBtn();		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}

}
