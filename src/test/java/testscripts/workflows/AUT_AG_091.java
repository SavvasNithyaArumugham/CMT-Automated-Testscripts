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
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_091 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_63()
	{
		testParameters.setCurrentTestDescription("1.Verify that asset name and task name both are visible in workflow task display in my task dashlet."
				+"<br>2. Verify that asset name and task name both are visible in workflow task display in my task window.");
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
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		if(sitesPage.Checkdocument(fileName)){
			sitesPage.documentdetails(fileName);
			
		}else {
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			myFiles.createFile(fileDetails);
		}
			
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
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		taskPage.selectApprover();
		//wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);	
		homePageObj.navigateToHomePage();
		homePageObj.selectTaskFilterDropdownValInMyTasksDashlet();
		
		AlfrescoHomePageTest homePgTestObj = new AlfrescoHomePageTest(scriptHelper);
		homePgTestObj.verifyTaskandAttachment(Msg);		
		
		homePageObj.navigateToHomePage();
		taskPage.navigateToMyTasksMenu();	
		taskPage.filterWFtasks();	
		wfPageObj.checkWFandAttachment(Msg);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}