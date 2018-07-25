package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_027 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_19()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to Start Workflow for a Zip Folder from Document action Menu/Hovering/Selected Items Menu in  Shared Files.");
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
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Search", "Query");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder = dataTable.getData("MyFiles", "Version");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "- " + strDate;
		
		//start workflow
		homePageObj.navigateToSharedFilesTab();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		
		if(sitesPage.Checkdocument(folder)){			
			sitesPage.documentdetails(folder);
				
			}else {				
				myFiles.createFolder(folderDetails);
				sitesPage.documentdetails(folder);		
			}	
	
		
		if(sitesPage.Checkdocument(fileName)){			
			
			sitesPage.documentdetails(fileName);
				
			}else {				
				myFiles.uploadFileInMyFilesPage(filePath, fileName);
				sitesPage.documentdetails(fileName);		
			}
				
		wfPageObj.clickOnDocOptions();
		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Message, DueDate, Priority);
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();	
		UIHelper.waitFor(driver);		
		homePageObj.navigateToWFStartedTab();		
		taskPage.filterWFtasks();
		UIHelper.waitForPageToLoad(driver);
		wfPageObj.checkWF(Message);	
		
		
		
		//Selected items
		
		homePageObj.navigateToSharedFilesTab();
		UIHelper.waitForPageToLoad(driver);		
		sitesPage.documentdetails(folder);
		String filter = dataTable.getData("Search", "Actions");
		myFiles.commonMethodForSelectingFiles(fileName);
		sitesPage.clickOnSelectedItems();
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		String Message1 = dataTable.getData("Search", "Description");
		/*String Msg1 = Message1 + "- " + strDate;*/
		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Message1, DueDate, Priority);
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();		
		UIHelper.waitFor(driver);	
		homePageObj.navigateToWFStartedTab();
		taskPage.filterWFtasks();
		UIHelper.waitForPageToLoad(driver);
		wfPageObj.checkWF(Message1);	
		
		
		//More options
		homePageObj.navigateToSharedFilesTab();
		UIHelper.waitForPageToLoad(driver);
		sitesPage.documentdetails(folder);	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AlfrescoDocumentLibPage docLib = new AlfrescoDocumentLibPage(scriptHelper);
		String moreSettingsOptName = dataTable.getData("Sites", "SelectedItemsMenuOption");				
		sitesPage.clickOnMoreSetting(fileName);
		String Message2 = dataTable.getData("Workflow", "Message");
		docLib.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptName);
		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Message2, DueDate, Priority);
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();	
		UIHelper.waitFor(driver);		
		homePageObj.navigateToWFStartedTab();
		taskPage.filterWFtasks();
		UIHelper.waitForPageToLoad(driver);
		wfPageObj.checkWF(Message2);	
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}