package testscripts.misc4;

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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_376 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_031()
	{
		testParameters.setCurrentTestDescription("Validate user icon for" 
				+"1. Simple workflow while assigning user"
				+"2. Simple review and approve workflow while assigning user"
				+"3. Review and approve Archive submission workflow while assigning user");
		
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
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		ArrayList<String> createdFileNames = myFilesTestObj
				.getCreatedFileNames(fileDetails);

		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		//String dueDate = dataTable.getData("Workflow", "DueDate");
		String priority = dataTable.getData("Workflow", "Priority");
		
		String splitTaskType[] = taskType.split(",");
		String splitMessage[] = message.split(",");
		
		String dueDate = new DateUtil().getCurrentDate();
		
		String docActionVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		String revAndAppSubmProcessFieldDetails = dataTable.getData("Workflow", "RevAndAppSubProcFieldData");
		
		for (String fileName : createdFileNames) {
			myFiles.deleteUploadedFile(fileName);

			myFiles.createFile(fileDetails);
			
			docDetailsPageTest.verifyCreatedFile(fileName, "");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
		
			sitesPage.clickOnMoreSetting(fileName);
		
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
			taskPage.submitWorkFlow(splitTaskType[0], splitMessage[0], dueDate, priority);
			
			String userName = dataTable.getData("Workflow", "UserName");

			if (userName.contains(",")) {
				String userNames[] = userName.split(",");
				taskPage.selectMultipleApprovers(userNames);
			} else {
				taskPage.selectSingleApprover(userName);
			}
			
			taskPage.clickStartWorkflowBtn();
			
			sitesPage.clickOnMoreSetting(fileName);
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
			taskPage.submitWorkFlow(splitTaskType[1], splitMessage[1], dueDate, priority);
			
			if (userName.contains(",")) {
				String userNames[] = userName.split(",");
				taskPage.selectMultipleApprovers(userNames);
			} else {
				taskPage.selectSingleApprover(userName);
			}
			
			String reviewers = dataTable.getData("Workflow", "Reviewer");

			if (reviewers.contains(",")) {
				String reviewerNames[] = reviewers.split(",");
				taskPage.selectMultipleReviewers(reviewerNames);
			} else {
				taskPage.selectSingleReviewer(reviewers);
			}
			
			taskPage.clickStartWorkflowBtn();
			
			sitesPage.clickOnMoreSetting(fileName);
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
			taskPage.enterFieldDataForRevAndAppArchSubmissionProcess(revAndAppSubmProcessFieldDetails);
			
			if (userName.contains(",")) {
				String userNames[] = userName.split(",");
				taskPage.selectMultipleApprovers(userNames);
			} else {
				taskPage.selectSingleApprover(userName);
			}
			
			taskPage.clickStartWorkflowBtn();
		}
	}

	@Override
	public void tearDown() {
		
	}

}
