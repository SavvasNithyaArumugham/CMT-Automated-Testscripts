package testscripts.workflows;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_090P1 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_62()
	{
		testParameters.setCurrentTestDescription("Verify the outcome value displayed in workflow task detail when reviewer approves/rejects the task<br> - Part 1: Verify that user is able to initiate a workflow and select reviewer");
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
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		
		/*homePage.navigateToTasksTab();
		
		taskPage.navigateToMyWorkFlows();
		
		taskPage.cancelCreatedWorkflow();*/
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		ArrayList<String> createdFileNames = myFilesTestObj
				.getCreatedFileNames(fileDetails);

		for (String fileName : createdFileNames) {

			myFiles.createFile(fileDetails);
			
			docDetailsPageTest.verifyCreatedFile(fileName, "");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
		
			sitesPage.clickOnMoreSetting(fileName);
		
			String taskType = dataTable.getData("Workflow", "Tasktype");
			String message = dataTable.getData("Workflow", "Message");
			//String dueDate = dataTable.getData("Workflow", "DueDate");
			String priority = dataTable.getData("Workflow", "Priority");
			String approvalPercentageVal = dataTable.getData("Workflow", "ApprovalPercentage");
			String dueDate = new DateUtil().getCurrentDate();
			
			String docActionVal = dataTable.getData("MyFiles", "MoreSettingsOption");
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
			taskPage.submitWorkFlow(taskType, message, dueDate, priority);
			
			taskPage.selectApprover();
			
			taskPage.inputApprovalPercentage(approvalPercentageVal.replace("'", ""));
			
			taskPage.clickStartWorkflowBtn();
		}
		
	}

	@Override
	public void tearDown() {
		
	}

}
