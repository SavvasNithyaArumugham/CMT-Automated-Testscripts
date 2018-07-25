package testscripts.healthcheck;

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

public class AUT_AG_044P6 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTCAUT_AG_120P3() {
		testParameters
		.setCurrentTestDescription("1.To check if the coordinator is able to reject the workflow task that is under review."
				+"<br>2.To check if the initiator receives an email once the task is rejected by coordinator."
				+"<br>3.To check if the task reverts to open state(claimable/unlocked) once the task is rejected by coordinator."
				+"<br>4.To check if the initiator is able to resubmit the rejected workflow task to coordinator by clicking on Continue Review and Approve button."
				+"<br><b> Part 4. initiator complete validation");
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
		
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoWorkflowPage workflowPg = new AlfrescoWorkflowPage(scriptHelper);

		
		String message = dataTable.getData("Workflow", "Message");		
		String reviewer = dataTable.getData("Workflow", "UserName");
		String comments = dataTable.getData("Workflow", "Comment");
		String status = dataTable.getData("Workflow", "Description");
		
		String dueDate = new DateUtil().getCurrentDate();
		message =message+"_"+dueDate;
		
		homePage.navigateToTasksTab();
		taskPage.navigateToMyTasksMenu();
		taskPage.filterWFtasks();
		workflowPg.checkWFStatus(message);
		//taskPage.selectReviewer(reviewer);	
		workflowPg.selectWFStatus(status.trim());	
		workflowPg.enterComments("Validation complete");
		workflowPg.clickOnSubmitTask();			

	}

	@Override
	public void tearDown() {

	}

}
