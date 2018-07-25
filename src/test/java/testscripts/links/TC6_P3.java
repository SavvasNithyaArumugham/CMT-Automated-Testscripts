package testscripts.links;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEditTaskPage;
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

public class TC6_P3 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_003()
	{
		testParameters.setCurrentTestDescription("TC002_1-3 Link Content");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoEditTaskPage editTaskPage = new AlfrescoEditTaskPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage myDocPage = new AlfrescoDocumentDetailsPage(scriptHelper);


		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileNameToSelect = dataTable.getData("MyFiles", "Version");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String dashletNme = dataTable.getData("Home", "DashletName");

		ArrayList<String>  folderNamesList = myFiles.getFolderNames(folderDetails);

	

		functionalLibrary.loginAsValidUser(signOnPage);
		
		
		
		homePageObj.navigateToSitesTab();		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);		
		myFiles.createFile(fileDetails);
		
		myFiles.methodToSelectMultipleFiles(fileNameToSelect);
		
		myFiles.selectDropDownLinkTo();
		
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
	
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		
		sitesPage.documentdetails(folderNamesList.get(0));
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.selectAllItems("File");
		sitesPage.clickOnSelectedItems();
		
		String selectedItemMenuOptVal = dataTable.getData("MyFiles", "RelationshipName");
		//AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		
		//sitePgTestObj.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
	
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
		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Msg, strDate, Priority);
		String userName = dataTable.getData("Workflow", "UserName");
		taskPage.selectApprover();
		wfPageObj.clickstartWorkflowBtn();
		UIHelper.waitFor(driver);
		homePageObj.navigateToWFStartedTab();
		
		taskPage.filterWFtasks();
		wfPageObj.checkWFStatus(Msg);
						
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
