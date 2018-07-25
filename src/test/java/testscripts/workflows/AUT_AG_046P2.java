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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 */
public class AUT_AG_046P2 extends TestCase{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_32()
	{
		testParameters.setCurrentTestDescription("Verify Approvers and Reviewers recieves the Workflow that are assigned to them. - Verify Approver MyTask dashlet");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.HtmlUnit);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}
	
	@Override
	public void executeTest() {
		// TODO Auto-generated method stub
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		
		String dashletNme = dataTable.getData("Home", "DashletName");
		if(!homePage.checkDashletInUserDashboard(dashletNme))
		{
			homePage.addDashletInCustomDashBoard();
		}
		
		homePageTest.verifyTaskNotification();
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		taskPage.navigateToMyTasksMenu();
		
		taskPage.clickOnViewTaskInMyTasksPage();
		
		taskPage.clickOnWorkflowDetailsLink();
		
		String generalInfoLabelName = dataTable.getData("Workflow", "GeneralInfoLabelNames");
		AlfrescoTasksPageTest taskPgTest = new AlfrescoTasksPageTest(scriptHelper);
		taskPgTest.verifyGeneralInfoLabelInWorkFlowDetails(generalInfoLabelName);
		
	}

	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
