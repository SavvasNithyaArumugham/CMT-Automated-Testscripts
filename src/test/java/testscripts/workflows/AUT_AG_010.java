package testscripts.workflows;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_010 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_08()
	{
		testParameters.setCurrentTestDescription("Check whether user is able to give 1000 characters in workflow 'message' field");
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
		AlfrescoTasksPageTest taskPageTest = new AlfrescoTasksPageTest(scriptHelper);
		
		/*homePage.navigateToTasksTab();
		
		taskPage.navigateToMyWorkFlows();
		
		taskPage.cancelCreatedWorkflow();*/
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
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
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.createFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {

			sitesPage.clickOnMoreSetting(folderName);
		
			String taskType = dataTable.getData("Workflow", "Tasktype");
			String message = dataTable.getData("Workflow", "Message");
			//String dueDate = dataTable.getData("Workflow", "DueDate");
			String priority = dataTable.getData("Workflow", "Priority");
			String dueDate = new DateUtil().getCurrentDate();
			
			String docActionVal = dataTable.getData("MyFiles", "MoreSettingsOption");
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, docActionVal);
		
			taskPage.submitWorkFlow(taskType, message, dueDate, priority);
			
			taskPage.selectApprover();
			
			taskPage.clickStartWorkflowBtn();
			
			myFilesTestObj.verifyUserNavigatedTodFolderLocation(folderName);
			
			homePage.navigateToTasksTab();
			
			taskPage.navigateToMyWorkFlows();
			
			taskPageTest.verifyCreatedWorkflowForGivenMsgFieldCharacters(message+"_"+dueDate);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
