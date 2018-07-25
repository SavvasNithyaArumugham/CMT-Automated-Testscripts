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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_034 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC1003()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is able to continue task even the sum of total characters in comment is more than 4000."
				+"2. Verify that assignee is able to reject task with providing comments between 0 and 4,000 characters.(multiple word)"
				+"3. Verify that initiator is able to continue with review and approve task for rejected task by providing comments between 0 and 4,000 characters.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
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
		AlfrescoTasksPage taskPage = homePage.navigateToTasksTab();
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyTasksPage myTaskPage = new AlfrescoMyTasksPage(scriptHelper);
		
		/*taskPage.navigateToMyWorkFlows();		
		taskPage.cancelCreatedWorkflow();*/
		
		homePage.navigateToSitesTab();		
		String siteName = dataTable.getData("Sites", "SiteName").trim();		
		sitesPage.openSiteFromRecentSites(siteName);
		docLibPage.navigateToDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");	
		if(!docLibPage.isFileIsAvailable(fileName)){
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}
		
		sitesPage.clickOnMoreSetting(fileName);			
		sitesPage.clickOnMoreOptionLink(fileName);	
		
		String userName = dataTable.getData("Workflow", "UserName");
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;
		
		//String userNames[] = userName.split(",");
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		wfPageObj.worflowInput(taskType, Msg, DueDate, Priority);
		
		myTaskPage.selectApprover(userName);
		
		/*if(userNames!=null && userNames.length>1)
		{
			myTaskPage.selectApprover(userNames[0]);
		//	myTaskPage.selectApprover(userNames[2]);
			myTaskPage.selectReviewer(userNames[1]);
		}*/
		taskPage.clickStartWorkflowBtn();
		
      //  String comment = dataTable.getData("Workflow", "Comment");     
        homePage.navigateToTasksTab();
        taskPage.navigateToMyTasksMenu();     
        
     /*   taskPage.filterWFtasks();
        taskPage.openWorkFlowTask();
        //taskPage.openAssignedWorkFlowFromMyTasks();
        taskPage.contRevApprveWorkflowTask();*/
		taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Msg);

		String reviewerComment = dataTable.getData("Workflow", "Comment");
	     taskPage.rejectWorkflowApproveTask(reviewerComment);
	         
	     homePage.navigateToTasksTab();
	      taskPage.navigateToMyTasksMenu();
	      taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Msg);
		
		String reviewerComments = dataTable.getData("Tasks",
				"GeneralInfoLabelNames");
		String status = dataTable.getData("Tasks", "Status");

		taskPage.performActionOnTaskFromMyTasksPage("Continue Review and Approve", reviewerComments, status);
		
		
		 homePage.navigateToTasksTab();
	     taskPage.navigateToMyTasksMenu();
	     taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Msg);
		
		
		String stat = dataTable.getData("Tasks", "ReviewerComment");
		taskPage.performActionOnTaskFromMyTasksPage("Approve", reviewerComment, stat);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}