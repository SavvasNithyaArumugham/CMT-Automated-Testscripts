package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


public class AUT_AG_044P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_139P2()
	{
		testParameters.setCurrentTestDescription("Verify that initiator is able to continue with review and approve task for rejected task by providing comments - 'Simple Review and Approve' Workflow(Multiple Assignees)  <br> Part1: Create and complete a Task");
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
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		/*homePage.navigateToTasksTab();
		
		taskPage.navigateToMyWorkFlows();
		
		taskPage.cancelCreatedWorkflow();*/
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		
		sitesPage.documentdetails(fileName);
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.click(driver, sitesPage.Workflowicon);
		
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		//String dueDate = dataTable.getData("Workflow", "DueDate");
		String priority = dataTable.getData("Workflow", "Priority");
		String reviwers = dataTable.getData("Workflow", "ApprovalPercentage");
		String dueDate = new DateUtil().getCurrentDate();
		
		taskPage.submitWorkFlow(taskType, message, dueDate, priority);
		
		taskPage.selectApprover();
		taskPage.selectReviewer(reviwers);
		
		taskPage.clickStartWorkflowBtn();	
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}
	
}
