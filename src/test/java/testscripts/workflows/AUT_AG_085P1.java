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
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_085P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_58()
	{
		testParameters.setCurrentTestDescription("1.Verify that user is able to add folders in start workflow page by clicking on Add button"
				+"<br>2.Verify that Assignee is getting email notification when initiator start workflow for folders"
				+"<br>3.Verify that Assignee is able to download the folder by the link provided in email notification"
+"<br>4.Verify that user is able to view the workflow in My Task Dashlet of the assignee when initiator assign the workflow started for folders");
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
		
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
	/*	homePage.navigateToTasksTab();	
		taskPage.navigateToMyWorkFlows();
		taskPage.cancelCreatedWorkflow();*/
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String initialFolder = dataTable.getData("Sites", "FileName");
		
		if(sitesPage.Checkdocument(initialFolder)){
			
			
		}else {
			myFiles.createFolder(folderDetails);
			
		}
		
		if(sitesPage.Checkdocument(fileName)){
			sitesPage.documentdetails(fileName);
				
		}else {
			
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			myFiles.createFile(fileDetails);
			
		}
		
		/*AlfrescoDocumentLibPage docLib = new AlfrescoDocumentLibPage(scriptHelper);
		String moreSettingsOptName = dataTable.getData("Sites", "SelectedItemsMenuOption");
		

		sitesPage.clickOnMoreSetting(initialFolder);
		
		docLib.commonMethodForClickOnMoreSettingsOption(initialFolder, moreSettingsOptName);*/
		
		AlfrescoDocumentDetailsPage myDocPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;
		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Msg, DueDate, Priority);
		
		myDocPage.clickOnAddItemsButton();
		
		myDocPage.addMoreItemForFolder(initialFolder);
		
		myDocPage.clickOnAddItemsOkButton();
		
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToMyTasks();
		
		taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Msg);
		
		AlfrescoMyTasksPageTest taskPageTest = new AlfrescoMyTasksPageTest(scriptHelper);
		taskPageTest.verifyAssetinWFTask(initialFolder, Msg);	
		
	
		homePageObj.navigateToHomePage();
		String dashletNme = dataTable.getData("Home", "DashletName");
		if(!homePage.checkDashletInUserDashboard(dashletNme))
		{
			homePage.addDashletInCustomDashBoard();
		}
		
		homePageObj.selectTaskFilterDropdownValInMyTasksDashlet();
		
		AlfrescoHomePageTest homePgTestObj = new AlfrescoHomePageTest(scriptHelper);
		homePgTestObj.verifyOpenTasksInHomePageDashlet(Msg);
		
		

	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}