package testscripts.workflows;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_026 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void workflows_18() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to Start Workflow for a Zip Folder from Document action Menu/Hovering/Selected Items Menu in  My Files.");
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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoRepositoryPage repositoryPage = new AlfrescoRepositoryPage(
				scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		homePageObj.navigateToMyFilesTab();

		if (sitesPage.Checkdocument(fileName)) {
			sitesPage.documentdetails(fileName);

		} else {
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			sitesPage.documentdetails(fileName);
		}
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Msg = dataTable.getData("Search", "Query");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		wfPageObj.clickOnDocOptions();
		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Msg, DueDate, Priority);
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();
		homePageObj.navigateToMyTasks();
		UIHelper.waitForPageToLoad(driver);
		taskPage.filterWFtasks();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		wfPageObj.checkWF(Msg);

		// workflow from selected items
		homePageObj.navigateToMyFilesTab();
		UIHelper.waitFor(driver);
		myFiles.commonMethodForSelectingFile(fileName);
		sitesPage.clickOnSelectedItems();
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		String selectedItemMenuOptVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		UIHelper.waitFor(driver);
		String Msg1 = dataTable.getData("Search", "Description");
		wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Msg1, DueDate, Priority);
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();
		homePageObj.navigateToMyTasks();
		UIHelper.waitForPageToLoad(driver);
		taskPage.filterWFtasks();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		wfPageObj.checkWF(Msg1);

		// Start workflow from more options
		homePageObj.navigateToMyFilesTab();
		UIHelper.waitFor(driver);
		AlfrescoDocumentLibPage docLib = new AlfrescoDocumentLibPage(
				scriptHelper);
		String moreSettingsOptName = dataTable.getData("Sites",
				"SelectedItemsMenuOption");

		String Msg2 = dataTable.getData("Workflow", "Message");
		sitesPage.clickOnMoreSetting(fileName);

		docLib.commonMethodForClickOnMoreSettingsOption(fileName,
				moreSettingsOptName);

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
	public void tearDown() {
		// Nothing to do
	}
}