package testscripts.workflows;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_049P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void workflows_35() {
		testParameters
				.setCurrentTestDescription("Verify that comments given while changing the due date for a Simple review and approveWorkflow task is reflected in mail received by initiator.- Part 1: Start workflow");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");
		/*homePage.navigateToTasksTab();
		taskPage.navigateToMyWorkFlows();
		taskPage.filterWFtasks();
		taskPage.cancelCreatedWorkflow();*/
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteassertValue);		
		sitesPage.documentlib();
		if(sitesPage.Checkdocument(fileName)){
			sitesPage.documentdetails(fileName);
			
		}else {
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			myFiles.createFile(fileDetails);
		}
		sitesPage.documentlib();
		UIHelper.waitFor(driver);
		sitesPage.clickOnMoreSetting(fileName);		
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");		
		String dueDate = new DateUtil().getCurrentDate();
		//message = message+"_"+dueDate;
		String priority = dataTable.getData("Workflow", "Priority");
		String reviwers = dataTable.getData("Workflow", "UserName");
		String approvers = dataTable.getData("Workflow", "ApprovalPercentage");
		String docActionVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		String comment = dataTable.getData("Workflow", "Comment");
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		taskPage.submitWorkFlow(taskType, message, dueDate, priority);
		taskPage.selectApprover(approvers);
		taskPage.selectReviewer(reviwers);	
		taskPage.clickStartWorkflowBtn();
		taskPage.navigateToMyTasksMenu();		
		taskPage.filterWFtasks();
		taskPage.checkTaskAvail(message+"_"+dueDate);
		taskPage.rejectWorkflowApproveTask("WF Rejection");	
		taskPage.navigateToMyTasksMenu();		
		taskPage.filterWFtasks();
		taskPage.checkTaskAvail(message+"_"+dueDate);		
		taskPage.changeDueDate(dueDate);
		taskPage.continueReviewWithComments(comment);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}