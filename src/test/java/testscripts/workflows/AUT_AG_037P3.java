package testscripts.workflows;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
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
public class AUT_AG_037P3 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_27()
	{
		testParameters.setCurrentTestDescription("Verify that reviewer don't get task in My Task dashlet or My Task page");
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
		AlfrescoTasksPage taskPage = homePage.navigateToTasksTab();
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyTasksPage myTaskPage = new AlfrescoMyTasksPage(scriptHelper);
		
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "- " + strDate;
		/*taskPage.navigateToMyWorkFlows();		
		taskPage.cancelCreatedWorkflow();*/
		/*
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
		
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		String userName = dataTable.getData("Workflow", "UserName");
		String dueDate = new DateUtil().getCurrentDate();
		String priority = dataTable.getData("Workflow", "Priority");
		
		String userNames[] = userName.split(",");
 		
		taskPage.submitWorkFlow(taskType, message, dueDate, priority);
		if(userNames!=null && userNames.length>1)
		{
			myTaskPage.selectApprover(userNames[0]);
			myTaskPage.selectReviewer(userNames[1]);
		}
		taskPage.clickStartWorkflowBtn();*/
		
		//homePage.navigateToHomePage();
		UIHelper.waitFor(driver);
		
		String dashletNme = dataTable.getData("Home", "DashletName");
		if(!homePage.checkDashletInUserDashboard(dashletNme))
		{
			homePage.addDashletInCustomDashBoard();
		}

		boolean taskInMyTaskDashlet = homePage.commonMethodForCheckTaskInMyTasksDashlet(Msg);
		if(!taskInMyTaskDashlet){
			report.updateTestLog(
					"Verify the Task displayed in 'My Task' Dashlet for Reviewer",
					"Task not displayed", Status.PASS);
		}else{
			report.updateTestLog(
					"Verify the Task displayed in 'My Task' Dashlet for Reviewer",
					"Task displayed", Status.FAIL);
		}
		
		taskPage.navigateToMyTasksMenu();
		taskPage.filterWFtasks();
		boolean taskInMyTaskPage = taskPage.checkTaskInMyTasksPage(Msg);
		if(!taskInMyTaskPage){
			report.updateTestLog(
					"Verify the Task displayed in 'My Task' Page for Reviewer",
					"Task not displayed", Status.PASS);
		}else{
			report.updateTestLog(
					"Verify the Task displayed in 'My Task' Page for Reviewer",
					"Task displayed", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
