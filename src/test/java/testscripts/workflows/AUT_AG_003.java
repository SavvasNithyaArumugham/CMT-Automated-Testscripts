package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_003 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_03()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is able to initiate a workflow for multiple file"
				+"<br> 2. Verify that user is able to initiate a workflow for multiple files and assign it to multiple users");
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
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		UIHelper.waitFor(driver);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String file = dataTable.getData("Sites", "FileName");
		
		if(sitesPage.Checkdocument(fileName)){
			
		}else {
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		}
		
		if(sitesPage.Checkdocument(file)){
			
		}else {
			myFiles.uploadFileInMyFilesPage(filePath, file);
		
			}
	
	myFiles.commonMethodForSelectingFiles(fileName);
	myFiles.commonMethodForSelectingFiles(file);

	sitesPage.clickOnSelectedItems();
	String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
	
	AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
	docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;
		String userName = dataTable.getData("Workflow", "UserName");
		String userNames[] = userName.split(",");
		wfPageObj.worflowInput(taskType, Msg, DueDate, Priority);
		
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		taskPage.selectApprover();
	
		
		wfPageObj.clickstartWorkflowBtn();
		
		
			
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