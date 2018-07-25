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
import com.pearson.automation.alfresco.pages.AlfrescoEditTaskPage;
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

public class AUT_AG_022P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTCAUT_AG_114P2() {
		testParameters
		.setCurrentTestDescription("<br>1. To Check if the coordinators are able to see the 'Claim' button in the workflow task page. "
				+ "	The task page opens when the users click on the link provided in the email."
				+"<br>2. To check if the workflow task gets 'Locked' once a particular user claims the task."
				+"<br>3. To check if the coordinator who claimed the task is able to see the following items under status(progress section) :"
				+"<br>4.To check if coordinator can reassign task to other external users once it is claimed."
				+"<br>5. To check if the coordinator is able to release the task that has been claimed by him/her without changing status of the task."
				+"<br><b> Part 2: Claim task and submit </b>");
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
		
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoWorkflowPage workflowPg = new AlfrescoWorkflowPage(scriptHelper);

		
		String message = dataTable.getData("Workflow", "Message");
		
		String reviewer = dataTable.getData("Workflow", "UserName");
		String status = dataTable.getData("Workflow", "Description");
		
		String dueDate = new DateUtil().getCurrentDate();
		message =message+"_"+dueDate;
		
		homePage.navigateToTasksTab();
		taskPage.navigateToMyTasksMenu();
		taskPage.filterWFtasks();
		
	//	taskPage.filterWFtasks();
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		wfPageObj.checkWFStatus(message);
		workflowPg.clickClaimButton();
	//	UIHelper.pageRefresh(driver);
		taskPage.selectReviewer(reviewer);
		workflowPg.selectWFStatus(status.trim());	
		workflowPg.clickOnSubmitTask();	
		
		AlfrescoEditTaskPage editTaskPg = new AlfrescoEditTaskPage(scriptHelper);
		String progressStatusValues = dataTable.getData("Tasks", "Progress");
		String[] statusValues = progressStatusValues.split(",");
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		if(editTaskPg.checkStatusDropDownValues(statusValues)){
			editTaskPg.clickStatusDropDown();
			report.updateTestLog("Verify the Status options in Progress section",
					"Verified successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify the Status options in Progress section",
					"Not able to Verify", Status.FAIL);
		}
		
		UIHelper.waitFor(driver);
		
		if(editTaskPg.checkDefaultStatusDropDownValue(statusValues[0])){
			report.updateTestLog("Verify the Default Status in Progress section",
					"Verified successfully"
					+"</br><b> Verified Default Status : </b>"+statusValues[0], Status.PASS);
		}else{
			report.updateTestLog("Verify the Default Status in Progress section",
					"Not able to Verify", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {

	}

}
