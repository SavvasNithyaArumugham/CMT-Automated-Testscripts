package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC6_P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_003()
	{
		testParameters.setCurrentTestDescription("Verify user is able to Link multiple files using  'Link to ' functionality  in drop down under Selected Items tab in Document Library");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentLibPage doclib = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPage docLib = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);

		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String file = dataTable.getData("MyFiles", "Version");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");


		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		doclib.deleteAllFilesAndFolders();
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		
		//myFiles.deleteUploadedFile(fileName);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFiles.methodToSelectMultipleFiles(fileName);
		sitesPage.clickOnSelectedItems();
		myFiles.selectDropDownLinkTo();
		
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		folderNamesList = myFiles.getFolderNames(folderDetails);
		sitesPage.documentdetails(folderNamesList.get(0));
		myFilesTestObj.verifyMultipleFilesAvailable(fileName);
		doclib.verifyLinkIcon(file);
		
		String moreSettingsOptName = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		String Msg2 = dataTable.getData("Workflow", "Message");
		sitesPage.clickOnMoreSetting(file);

		docLib.commonMethodForClickOnMoreSettingsOption(file,
				"Start Workflow");

		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Msg2, DueDate, Priority);
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();

		homePageObj.navigateToMyTasks();
		UIHelper.waitForPageToLoad(driver);
		taskPage.filterWFtasks();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		wfPageObj.checkWF(Msg2);
						
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}