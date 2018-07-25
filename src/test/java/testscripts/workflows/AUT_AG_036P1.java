package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Lokesh
 */
public class AUT_AG_036P1 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_26()
	{
		testParameters.setCurrentTestDescription("Verify the template for General info in Worfkow details page of Simple Workflow"
				+"2. Verify that the text in Message field is same as the text given in description field by initiator while initiating Simple Worklfow."
				+"3. Verfiy that no field is displayed more than once in General info in Simple Task Workflow Details Page."
				+ "4. Verify that Due date in Workflow summary and General info is same in Workflow Detail page for Simple Task Workflow."
				+"5. Verify that Priority in Workflow summary and General info is same in Workflow Detail page for Simple Task Workflow."
				+"6. Verify that Status in Workflow summary and General info is same in Workflow Details page for Simple Task Workflow."
				+"7. Verify that Locale field in not there on Workflow Details page for Simple Task Workflow.");
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
		AlfrescoTasksPage taskPage = homePage.navigateToTasksTab();
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyTasksPage myTaskPage = new AlfrescoMyTasksPage(scriptHelper);
		
		homePage.navigateToSitesTab();		
		String siteName = dataTable.getData("Sites", "SiteName").trim();		
		sitesPage.openSiteFromRecentSites(siteName);
		docLibPage.navigateToDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");	
		if(!docLibPage.isFileIsAvailable(fileName)){
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}
		
		sitesPage.clickOnMoreSetting(fileName);	
		String docActionVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
		
		String userName = dataTable.getData("Workflow", "UserName");
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;
		

		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		wfPageObj.worflowInput(taskType, Msg, DueDate, Priority);

		wfPageObj.selectReviewer();
		
		taskPage.clickStartWorkflowBtn();
	}

	@Override
	public void tearDown() {
		
	}

}
