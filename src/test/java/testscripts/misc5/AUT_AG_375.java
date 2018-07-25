package testscripts.misc5;


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
public class AUT_AG_375 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTC128() {
		testParameters
				.setCurrentTestDescription("");
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
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPge.deleteAllFilesAndFolders();
		
		
		String fileName = dataTable.getData("MyFiles", "FileName");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		sitesPage.documentdetails(fileName);
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Message");
		//String Message = dataTable.getData("Workflow", "Message");
		String wfDetails = dataTable.getData("Workflow", "UserName");
	/*	Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);*/
	//	String Msg = Message + "_" + strDate;
		wfPageObj.startWorkflow();
		wfPageObj.selectWorkFlow(taskType);
		wfPageObj.Updateallcontent(wfDetails);
		wfPageObj.clickstartWorkflowBtn();
		
		/*
		wfPageObj.worflowInput(taskType, Msg, strDate, Priority);
		
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
		AlfrescoTasksPageTest taskPageObj = new AlfrescoTasksPageTest(scriptHelper);
		taskPageObj.verifyAssigneeDisplayedinWorkflow();
	//	taskPageObj.compareWorflowDueDateDetails(DueDate);
		taskPageObj.verifyfileAttachedtoTask();
		taskPageObj.compareWorflowStartedDetailsWithCurrentSystemDetails();
*/
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}