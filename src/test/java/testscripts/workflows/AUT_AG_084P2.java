package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_084P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_57()
	{
		testParameters.setCurrentTestDescription("1.Verify the Due date for the Worklfow in My Tasks dashlet of the user dashboard. "
				+"<br>2. Verify the Due date of the Workflow from Workflows I've Started Page"
				+"<br>3.Verify all Due Date fields on the Workflow Details Page"
				+ "Part2 - Verify that Due Date is displayed with the task in My Tasks Dashlet");
	
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
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
		String Message = dataTable.getData("Workflow", "Message");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.selectTaskFilterDropdownValInMyTasksDashlet();
		
		AlfrescoHomePageTest homePgTestObj = new AlfrescoHomePageTest(scriptHelper);
		homePgTestObj.verifyTaskDueDateInMyTaskDashlet(Msg);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		homePage.navigateToTasksTab();
		
		taskPage.navigateToMyWorkFlows();
		
		taskPage.filterWFtasks();
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		wfPageObj.checkWFStatus(Msg);
		
		//taskPage.openCreatedOrAssignedTask();
		
		AlfrescoTasksPageTest taskPgTest = new AlfrescoTasksPageTest(scriptHelper);
		taskPgTest.compareWorflowDueDateDetails(Msg);
		
		taskPgTest.verifyWorflowDueDateDetailsInGeneralInfo(Msg);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}