package testscripts.workflows;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_081 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void rworkflows_05() {
		testParameters
				.setCurrentTestDescription("Verify that all tasks will display the corresponding assigned User(s)"
						+" <br> 2. Verify that all tasks will display the corresponding due date"
						+"<br>3. Verify that all tasks will display the corresponding component"
						+"<br>4. Verify that all tasks will display a task description"
						+"<br>5. Verify that assigner is able to track the status of workflow task"
						+"<br>6. Verify that user is able to initiate a workflow for a file and assign it to multiple users"
						+"<br>7. Verify that assigner is able to set priority for a workflow task");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

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
		
		AlfrescoMyTasksPage myTaskPage = new AlfrescoMyTasksPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		
		if(sitesPage.Checkdocument(fileName)){
			sitesPage.documentdetails(fileName);
			
		}else {
			
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			myFiles.createFile(fileDetails);
		}
		
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		String Msg = Message + "_" + strDate;
		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Msg, strDate, Priority);
		String userName = dataTable.getData("Workflow", "UserName");
		taskPage.selectApprover();
		//wfPageObj.selectReviewer();
		//wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();
		UIHelper.waitFor(driver);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToWFStartedTab();
		
		taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Msg);

		AlfrescoTasksPageTest appTasksPgTest = new AlfrescoTasksPageTest(
				scriptHelper);
		appTasksPgTest.veriftTrackWFStatus();
		AlfrescoTasksPageTest taskPageObj = new AlfrescoTasksPageTest(scriptHelper);
		taskPageObj.verifyfileAttachedtoTask(Msg);
		/*AlfrescoTasksPageTest taskPageObj = new AlfrescoTasksPageTest(scriptHelper);
		taskPageObj.verifyAssigneeDisplayedinWorkflow();
	//	taskPageObj.compareWorflowDueDateDetails(DueDate);
		taskPageObj.verifyfileAttachedtoTask();
		taskPageObj.compareWorflowStartedDetailsWithCurrentSystemDetails();*/

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}