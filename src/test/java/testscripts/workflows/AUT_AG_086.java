package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_086 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void workflows_59() {
		testParameters
				.setCurrentTestDescription("Verify that Assignee is able to upload new version of the file if task is reject by Assigner");
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
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoTasksPage tasksPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSearchPage searchPage = new AlfrescoSearchPage(scriptHelper);
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage=new AlfrescoDocumentLibPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		//String dueDate = dataTable.getData("Workflow", "DueDate");
		String priority = dataTable.getData("Workflow", "Priority");
		String dueDate = new DateUtil().getCurrentDate();
		
		/*homePage.navigateToTasksTab();
		tasksPage.navigateToMyWorkFlows();
		
		tasksPage.cancelCreatedWorkflow();*/
		
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		if (sitesPage.Checkdocument(fileName)) {
			searchPage.deletedocument(fileName);
		}
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		sitesPage.documentdetails(fileName);

		wfPageObj.startWorkflow();
		tasksPage.submitWorkFlow(taskType, message, dueDate, priority);
		tasksPage.selectApprover();
		tasksPage.clickStartWorkflowBtn();
		
		String createdWorkflowName= message+"_"+dueDate;
		
		tasksPage.navigateToMyTasksMenu();
		tasksPage.openCreatedOrAssignedTask(createdWorkflowName);
		tasksPage.rejectWorkflowApproveTask();

		tasksPage.navigateToMyTasksMenu();
		tasksPage.openCreatedOrAssignedTask(createdWorkflowName);
		tasksPage.selectViewMoreActionsLink();
		
		String newDocfileName = dataTable.getData("Document_Details", "FileName");
		String newDocfilePath = dataTable.getData("Document_Details", "FilePath");
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		String actionName=dataTable.getData("Document_Details", "DocumentActionName");
		docDetailsPage.commonMethodForPerformDocAction(actionName);
		docLibPage.uploadNewVersionFileInDocLibPage(newDocfileName, newDocfilePath, versionDetails, comments);
		
		//docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		docDetailsPageTest.verifyUploadedNewVersionFile();
		tasksPage.navigateToMyTasksMenu();
		tasksPage.openCreatedOrAssignedTask(createdWorkflowName);
		tasksPage.contRevApprveWorkflowTask();
		tasksPage.navigateToMyTasksMenu();

		if (tasksPage.checkCreatedOrAssignedTask(createdWorkflowName)) {
			report.updateTestLog("Verify task is assigned to assignee",
					" Task is assigned to assignee sucessfully", Status.PASS);
		} else {
			report.updateTestLog("Verify task is assigned to assignee",
					"Failed to assign task", Status.FAIL);

		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}