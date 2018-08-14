package testscripts.misc5;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_388 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTC128() {
		testParameters
				.setCurrentTestDescription("Verify Content Fulfillment workflow can start be start from selected items");
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
		
		AlfrescoMyTasksPage myTaskPage = new AlfrescoMyTasksPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		String userName = dataTable.getData("General_Data", "Username");
		
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPge.deleteAllFilesAndFolders();
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String file = dataTable.getData("MyFiles", "Version");
		String file2 = dataTable.getData("MyFiles", "CreateFolder");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFiles.commonMethodForSelectingFiles(file2);
		myFiles.commonMethodForSelectingFiles(file);

		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Message");
		String wfDetails = dataTable.getData("Workflow", "UserName");
		wfPageObj.startWorkflow();
		wfPageObj.selectWorkFlow(taskType);
		wfPageObj.Updateallcontent(wfDetails);
		wfPageObj.contentfulstartWFBtnSelect();
	
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}