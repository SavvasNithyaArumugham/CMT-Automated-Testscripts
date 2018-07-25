package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoWorkflowPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_035P2 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_26()
	{
		testParameters.setCurrentTestDescription("1. Verify the template for General Info in Worfkow details page of Simple Review and Approve Workflow."
				 +"<br>2. Verify that the text in message field is same as the text given in description field by initiator while initiating Simple Review and Approve Workflow."
				+"<br>3. Verify that no field is displayed more than once in General info in Simple Review and Approve Workflow Details Page."
				 + "<br>4. Verify that Due date in Workflow summary and General info is same in Workflow Detail page for Simple Review and Approve Workflow."
				+"<br>5. Verify that Priority in Workflow summary and General info is same in Workflow Detail page for Simple Review and Approve Workflow."
				 +"<br>6. Verify that Status in Workflow summary and General info is same in Workflow Details page for Simple Review and Approve Workflow ."
				+"<br>7. Verify that Locale field is not displayed in Workflow Details page for Simple Review and Approve Workflow"
				 +"<br>8.Verify the template for the mail received by assignee for Simple Review and approve Workflow ."
				+"<br>9. Verify that assignee get email notification once due date is changed for any task."
		+"<br>10. Verify that detail Field in workflow detail page for Simple review and approve workflow."
				+"<br>11.Verify that text of value Field in workflow detail page for Simple review and approve workflow."
				+ "<br>Part2: Verify the template for General info of Worfkow details page");
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
		
		AlfrescoTasksPageTest taskPgTest = new AlfrescoTasksPageTest(scriptHelper);
		AlfrescoWorkflowPageTest appWorkflowPgTest = new AlfrescoWorkflowPageTest(
				scriptHelper);
				
		homePage.navigateToTasksTab();
		
		taskPage.navigateToMyTasksMenu();
		
		taskPage.clickOnViewTaskInMyTasksPage();
		
		taskPage.clickOnWorkflowDetailsLink();
		
		appWorkflowPgTest.verifyAlfrescoLogoAndWorkflowDetailsInWorkFlowDetails();
		
		appWorkflowPgTest.verifyTaskAndWorkflowDetailsInWorkFlowDetails();
		

		
		
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		String currentDate = new DateUtil().getCurrentDate();
		String finalTaskName = message+"_"+currentDate;
		String priority = dataTable.getData("Workflow", "Priority");
		String startedBy = dataTable.getData("Workflow", "StartedBy");
		String description = dataTable.getData("Workflow", "Description");
		
		//Check General Info Details
		appWorkflowPgTest.verifyFieldsHeaderTitleInWorkFlowDetails("General Info");
		appWorkflowPgTest.verifyFieldLabelAndValInWorkFlowDetails("Workflow Type",taskType);
		appWorkflowPgTest.verifyFieldLabelAndValInWorkFlowDetails("Description",description);
		appWorkflowPgTest.verifyFieldLabelAndValInWorkFlowDetails("Started by",startedBy);
		appWorkflowPgTest.verifyFieldLabelAndValInWorkFlowDetails("Priority",priority);
		appWorkflowPgTest.verifyFieldLabelAndValInWorkFlowDetails("Message",finalTaskName);
		
		String generalInfoLabelNames = dataTable.getData("Workflow", "GeneralInfoLabelNames");
		String splitGeneralInfoLabels[] = generalInfoLabelNames.split(",");
		for(String generalInfoLabelName:splitGeneralInfoLabels)
		{
			taskPgTest.verifyFieldLabelInWorkFlowDetails(generalInfoLabelName);
		}
		
		//Check More Info Details
		appWorkflowPgTest.verifyFieldsHeaderTitleInWorkFlowDetails("More Info");
		appWorkflowPgTest.verifyFieldLabelAndValInWorkFlowDetails("Send Email Notification","Yes");
		
		//Check Items Details
		taskPgTest.verifyFieldLabelInWorkFlowDetails("Items");
		
		appWorkflowPgTest.verifyMessageFieldValInWorkFlowDetails("Message",finalTaskName);
		
		String generalInfoLabelNamesDue = dataTable.getData("Workflow", "GeneralInfoLabelNames");
		String generalInfoLabelNamesPrior = dataTable.getData("Workflow", "Comment");
		String generalInfoLabelNamesLocale = dataTable.getData("Workflow", "Description");
		String generalInfoLabelNamesstatus = dataTable.getData("Workflow", "HistoryFieldName");
		
		appWorkflowPgTest.verifyFieldValInWorkFlowSumAndGeneralInfo(generalInfoLabelNamesDue);
		appWorkflowPgTest.verifyFieldValInWorkFlowSumAndGeneralInfo(generalInfoLabelNamesPrior);
	//	appWorkflowPgTest.verifyFieldValInWorkFlowSumAndGeneralInfo(generalInfoLabelNamesLocale);
		appWorkflowPgTest.verifyFieldValInWorkFlowSumAndGeneralInfo(generalInfoLabelNamesstatus);
		
		AlfrescoWorkflowPage workFlowPage = new AlfrescoWorkflowPage(scriptHelper);
		if(workFlowPage.checkDetailsFormatInWF(message, "cwf:sumbitParallelReviewTask")){
			report.updateTestLog("Verify Details Field format",
					"Format verified successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify Details Field format",
					"Invalid Format", Status.FAIL);
		}
		
		String historyFieldName = dataTable.getData("Workflow", "DoWorkflowFrom");
		
		appWorkflowPgTest.verifyHistoryFieldValueInWorkFlowDetails(historyFieldName, taskType);
		

	}

	@Override
	public void tearDown() {
		
	}

}
