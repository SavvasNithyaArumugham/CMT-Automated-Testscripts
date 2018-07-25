package testscripts.misc3;

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
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Moshin
 */
public class AUT_AG_257 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_041() {
		testParameters
				.setCurrentTestDescription("Verify that user should be able to perform operations (Start Workflow,attach Lifecycle,Link,Change Lifecycle state,etc)");
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
			
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			
			AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
			String targetSiteName=dataTable.getData("Sites", "TargetSiteName");
			
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			String siteassertValue = dataTable.getData("Sites", "SiteName");
			sitesPage.siteFinder(siteassertValue);
			sitesPage.documentlib();
			
			String webName = dataTable.getData("Parametrized_Checkpoints", "FileName");
			String webURL = dataTable.getData("Parametrized_Checkpoints", "Help URL");
			String type = dataTable.getData("Parametrized_Checkpoints", "FilePath");
			
	
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			try{
				docLibPg.deleteAllFilesAndFolders();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			myFiles.createcontenttype(type);
			myFiles.createWebResource(webName, webURL);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
			String taskType = dataTable.getData("Workflow", "Tasktype");
			String Message = dataTable.getData("Workflow", "Message");
			String DueDate = dataTable.getData("Workflow", "DueDate");
			String Priority = dataTable.getData("Workflow", "Priority");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = sdf.format(date);
			
			String Msg = Message + "_" + strDate;
			
			sitesPage.clickOnMoreSetting(webName);

			AlfrescoDocumentLibPage docLib = new AlfrescoDocumentLibPage(
					scriptHelper);
			AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
			String moreSettingsOptName = dataTable.getData("Sites",
					"FileName");
			docLib.commonMethodForClickOnMoreSettingsOption(webName,
					moreSettingsOptName);

			wfPageObj.startWorkflow();
			wfPageObj.worflowInput(taskType, Msg, DueDate, Priority);
			wfPageObj.selectReviewer();
			wfPageObj.clickstartWorkflowBtn();
			
			homePageObj.navigateToWFStartedTab();
			taskPage.filterWFtasks();
			wfPageObj.checkWF(Msg);
			
			sitesPage.siteFinder(siteassertValue);
			sitesPage.documentlib();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			sitesPage.clickOnMoreSetting(webName);
			
			AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
			sitePgTestObj.verifyAttachLifecycleForFileOrFolder(webName);
		
			String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");

			docLibPg.commonMethodForClickOnMoreSettingsOption(webName, docActionVal);
		
			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
			String changedLifecycleStateActions = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
			sitesPage.clickOnMoreSetting(webName);
			docLibPageTest.verifyBrowseAction(changedLifecycleStateActions, webName);
			
			sitesPage.documentlib();
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			String folder = dataTable.getData("MyFiles", "FileName");
			myFiles.createFolder(folderDetails);
			String browseOption = dataTable.getData("MyFiles", "BrowseActionName");
			sitesPage.commonMethodForPerformBrowseOption(
					webName, browseOption);	
			
			docDetailsPageObj.selectFolderInLinkPopUp(targetSiteName);
			docDetailsPageObj.clickLinkBtnInLinkPopUp();
			
			sitesPage.documentdetails(folder);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
			myFilesTestObj.verifyIsFileAvilabile(webName,"");
			
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}