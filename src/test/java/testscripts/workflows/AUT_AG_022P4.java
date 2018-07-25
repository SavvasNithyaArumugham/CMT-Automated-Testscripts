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

public class AUT_AG_022P4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTCAUT_AG_115P2() {
		
		testParameters
		.setCurrentTestDescription("1. To check if the coordinator is able to release the task that has been claimed by him/her without changing status of the task."
				+"<br><b> Part 2: Release to pool </b>");
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
		
		String dueDate = new DateUtil().getCurrentDate();
		message =message+"_"+dueDate;
		
		homePage.navigateToTasksTab();
		taskPage.navigateToMyTasksMenu();
		taskPage.filterWFtasks();
		
		workflowPg.checkWFStatus(message);
		workflowPg.clickClaimButton();
		workflowPg.enterComments("Release task");
		//UIHelper.pageRefresh(driver);
		workflowPg.clickOnReleaseToPool();	
		
		homePage.navigateToTasksTab();
		taskPage.navigateToMyTasksMenu();
		taskPage.filterWFtasks();
		
		workflowPg.checkWFStatus(message);
		
		if(workflowPg.isClaimButtonVisible()){
			report.updateTestLog("Verify Release Task",
					"Task released by clicking Release to Pool button", Status.PASS);
		}else{
			report.updateTestLog("Verify Release Task",
					"Task not released by clicking Release to Pool button", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {

	}

}
