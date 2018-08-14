package testscripts.workflows;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.server.handler.CaptureScreenshot;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_020P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTCAUT_AG_109P1() {
		testParameters
				.setCurrentTestDescription("1. To check if a member of the media hub alfresco group is able to see the Media HUB request workflow when user starts a workflow in Alfresco brazil"+
						"<br>2. To check if the Initiator receives email notification for MH Workflow once initiator starts workflow. "
						+"<br>3. To check if the Media HUB request can be assigned to a group of users(known as coordinators). Note : Only Media hub group members can be assigned workflow tasks for this type of workflow. Also kindly note that the text box against the reviewer label is us"
						+"<br>4. To check if the initiator is able to see the created workflow task Under : Tasks > workflows I have started."
						+"<br><b> Part 1: Create Workflow </b>");
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
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		String priority = dataTable.getData("Workflow", "Priority");
		String reviewer = dataTable.getData("Workflow", "UserName");
		String dueDate = new DateUtil().getCurrentDate();

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.uploadFile(filePath, fileNme);
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, docActionVal);
		taskPage.submitWorkFlow(taskType, message, dueDate, priority);
		taskPage.selectReviewer(reviewer);
		taskPage.clickStartWorkflowBtn();
	
		
		homePage.navigateToTasksTab();
		taskPage.navigateToMyWorkFlows();
		taskPage.filterWFtasks();		
		if(taskPage.checkCreatedOrAssignedTask()){
			report.updateTestLog("Verify created task",
					"Task created successfully"
							+ "<br /><b> Task Name : </b>"+message,
					Status.PASS);
		}
		else{
			report.updateTestLog("Verify created task",
					"Task is not created"
							+ "<br /><b> Task Name : </b>"+message,
					Status.FAIL);
		}
		/*	AlfrescoHomePageTest homePgTestObj = new AlfrescoHomePageTest(scriptHelper);
		String dashletNme = dataTable.getData("Home", "DashletName");
		homePage.navigateToHomePage();
		if(!homePage.checkDashletInUserDashboard(dashletNme))
		{
			homePage.addDashletInCustomDashBoard();
		}
		
		homePage.selectTaskFilterDropdownValInMyTasksDashlet();			
		homePgTestObj.verifyOpenTasksInHomePageDashlet(message);	
		homePage.clickOnTaskInMyDashlet(message);
	
		if(taskPage.checkFileInWorkFlowAttachment(fileNme)){
			report.updateTestLog("Verify File in Attachments",
					"File is displayed in attachments"
							+ "<br /><b> File Name : </b>"+fileNme,
					Status.PASS);
		}
		else{
			report.updateTestLog("Verify File in Attachments",
					"File is not dispalyed in attachments"
							+ "<br /><b> File Name : </b>"+fileNme,
					Status.FAIL);
			
		}*/

	}

	@Override
	public void tearDown() {

	}

}