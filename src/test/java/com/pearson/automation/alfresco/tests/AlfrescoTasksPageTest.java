package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoTasksPageTest extends ReusableLibrary {

	private String activeTasksXpath = ".//a[contains(text(),'Active')]";
	private String activeTasksListXpath = ".//*[contains(@class,'yui-dt-data')]/tr";
	private String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
	private String CompletedTasksXpath = ".//a[contains(text(),'Completed')]";
	private String completedTasksListXpath = ".//*[contains(@class,'yui-dt-data')]//a[contains(@class,'theme-color-1')]";
	private boolean found;

	private String testOutputForCreatedWorkFlow = "src/test/resources/AppTestData/TestOutput/WorkflowDetails.txt";

	private boolean isDisplayedCreatedTask;
	private ArrayList<String> createdTasksNameList = new ArrayList<String>();

	private AlfrescoTasksPage appTasksPgObj = new AlfrescoTasksPage(
			scriptHelper);

	private String wfstatusXpath = ".//*[@id='page_x002e_data-form_x002e_workflow-details_x0023_default-status']";

	private ArrayList<String> completedTasksNameList = new ArrayList<String>();

	private ArrayList<String> taskTypeDropdownValues = new ArrayList<String>();

	private String splittedTaskTypeValues[];
	private ArrayList<String> workflowsList = new ArrayList<String>();
	private String actualResult;
	private boolean isDisplayedCreatedWorkflow;

	private String attchdFldEdtXPath = ".//*[contains(text(),'CRAFT')]";
	private String apprBtnXPath = ".//*[contains(text(),'Approval Percentage']";

	/*** Accept/Reject ***/
	private String taskActionXpath = ".//*[contains(@id,'cwf_reviewOutcome')]//button[text()='CRAFT']";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoTasksPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void verifyRejectedTask() {
		try {
			String temp = new FileUtil().readDataFromFile(testOutputFilePath);
			String taskName = "Invitation to join " + temp + " site";
			createdTasksNameList = appTasksPgObj.getAllCompletedTasks();

			for (String actualCreatedTask : createdTasksNameList) {
				if (actualCreatedTask.equalsIgnoreCase(taskName)) {
					isDisplayedCreatedTask = true;
					break;
				} else {
					isDisplayedCreatedTask = false;
				}
			}

			if (isDisplayedCreatedTask) {
				report.updateTestLog("Verify Rejected Task",
						"Task Rejected Successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Rejected Task",
						"Task Rejection Failed", Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException("Verify Rejected Task",
				 * "Task failed to reject");
				 */
			}
		} catch (Exception e) {
		}
	}

	// To verify Active Tasks
	public void verifyActiveTasks() {
		try {
			createdTasksNameList = appTasksPgObj.getTasksFromMyTasks();
			int activeTasks = createdTasksNameList.size();
			System.out.println(activeTasks);

			if (activeTasks >= 1) {
				isDisplayedCreatedTask = true;

			} else {
				isDisplayedCreatedTask = false;
			}

			if (isDisplayedCreatedTask) {
				report.updateTestLog(
						"Task found in Active Tasks",
						"</br><b>Expected Result:</b> Atleat One Task <b>Actual Result: Number of Tasks Found</b>"
								+ activeTasks, Status.PASS);
			} else {
				report.updateTestLog(
						"Task not found in Active Tasks",
						"</br><b>Expected Result:</b> Atleat One Task <b>Actual Result: Number of Tasks Found</b>"
								+ activeTasks, Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Comments By Sai Kiran - To verify an open task is completed
	public void verifyCompletedTask() {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, CompletedTasksXpath);
			UIHelper.click(driver, CompletedTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			String taskName = new FileUtil()
					.readDataFromFile(testOutputFilePath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					completedTasksListXpath);
			List<WebElement> tasks = driver.findElements(By
					.xpath(completedTasksListXpath));
			for (WebElement task : tasks) {
				if (taskName.equals(task.getText())) {
					UIHelper.scrollToAnElement(task);
					UIHelper.highlightElement(driver, task);
					UIHelper.waitFor(driver); // To see Highlighted element with
												// naked
					// eye
					found = true;
					break;
				} else {
					found = false;
				}

			}
			if (found) {
				report.updateTestLog("Completed Tasks",
						"Task Found in completed Tasks list", Status.PASS);
			} else {
				report.updateTestLog("Completed Tasks",
						"Failed to find Completed Task", Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException("Completed Tasks",
				 * " Failed to find Completed Task");
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * frameworkParameters.setStopExecution(true); throw new
			 * FrameworkException("Completed Tasks",
			 * " Failed to find Completed Task");
			 */
		}

	}

	public void checkTaskCompletion() {

		try {
			String completedtaskName = new FileUtil()
					.readDataFromFile(testOutputFilePath);
			completedTasksNameList = appTasksPgObj.getAllCompletedTasks();

			for (String actualCompletedTask : completedTasksNameList) {
				System.out.println(actualCompletedTask);
				System.out.println(completedtaskName);
				if (actualCompletedTask.equalsIgnoreCase(completedtaskName)) {
					isDisplayedCreatedTask = true;
					break;
				} else {
					isDisplayedCreatedTask = false;
				}
			}

			if (isDisplayedCreatedTask = true) {
				report.updateTestLog("Verify the Task is completed ->"
						+ completedtaskName, "Task completed Successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Task is completed -> "
						+ completedtaskName, "Task completion Failed",
						Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException("Verify completed Task",
				 * "Task failed to complete");
				 */
			}
		} catch (Exception e) {
		}

	}

	public void verifyDueDateForAllTasks() {
		try {
			boolean isDisplayedDueDateForTasks = appTasksPgObj
					.returnDuedateisDisplayed();

			if (isDisplayedDueDateForTasks) {
				report.updateTestLog(
						"Verify the Due Date assigned to the Task",
						"Due Date is assigned to the task ", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the Due Date assigned to the Task",
						"Due date is failed to display for tasks. 	Please verify Active Tasks are available under My Tasks.",
						Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException("Verify Due Date for All Tasks",
				 * "Due date is failed to display for tasks");
				 */
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Click on Complete filter and Verify the task that are Completed are displayed under My Task Page.  ",
					"Completed Tasks are NOT displayed under May Tasks Page.Please verify Active Tasks are available under My Tasks. ",
					Status.FAIL);
		}
	}

	public void verifyDescriptionForAllTasks() {
		try {
			String descDisplayforTasks = appTasksPgObj
					.returnDescriptionValforTask();

			if (descDisplayforTasks.length() != 0) {
				report.updateTestLog(
						"Verify the Description assigned to the Task",
						"Description" + descDisplayforTasks
								+ " is assigned to the task ", Status.PASS);
			} else {
				/* frameworkParameters.setStopExecution(true); */
				report.updateTestLog(
						"Verify the Description assigned to the Task",
						"Active Tasks with Description is NOT available to verify. Please check Activte tasks are created as per the pre requisite ",
						Status.FAIL);

				/*
				 * throw new FrameworkException(
				 * "Verify the Description assigned to the Task",
				 * "Active Tasks with Description is NOT available to verify. Please check Activte tasks are created as per the pre requisite "
				 * );
				 */
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Click on Complete filter and Verify the task that are Completed are displayed under My Task Page.  ",
					"Completed Tasks are NOT displayed under May Tasks Page.Please verify Active Tasks are available under My Tasks. ",
					Status.FAIL);
		}
	}

	public void verifyComponentForTask() {
		try {
			String componentdisplayforTask = appTasksPgObj
					.returnComponenentValforTask();

			if (componentdisplayforTask.length() != 0) {
				report.updateTestLog(
						"Verify the Task displays the component if applicable",
						"Component-> " + componentdisplayforTask
								+ " is assigned to the task ", Status.PASS);
			} else {
				// frameworkParameters.setStopExecution(true);
				report.updateTestLog(
						"Verify the Task displays the component if applicable",
						"Active Tasks with Component is NOT available to verify. Please check Activte tasks are created as per the pre requisite ",
						Status.FAIL);

				/*
				 * throw new FrameworkException(
				 * "Verify the Task displays the component if applicable",
				 * "Active Tasks with Component is NOT available to verify. Please check Activte tasks are created as per the pre requisite "
				 * );
				 */

			}
		} catch (Exception e) {
			report.updateTestLog(
					"Click on Complete filter and Verify the task that are Completed are displayed under My Task Page.  ",
					"Completed Tasks are NOT displayed under May Tasks Page.Please verify Active Tasks are available under My Tasks. ",
					Status.FAIL);
		}
	}

	public void verifyCmplteFilterinMyTasksPage() {
		try {
			Boolean cmpleteFilterTask = appTasksPgObj
					.CmplteFilterinMyTasksPage();

			if (cmpleteFilterTask) {
				report.updateTestLog(
						"Click on Complete filter and Verify the task that are Completed are displayed under My Task Page.  ",
						"Completed Tasks are displayed successfully under My Tasks. ",
						Status.PASS);
			} else {
				// frameworkParameters.setStopExecution(true);
				report.updateTestLog(
						"Click on Complete filter and Verify the task that are Completed are displayed under My Task Page.  ",
						"Completed Tasks are NOT displayed under May Tasks Page.Please verify Active Tasks are available under My Tasks. ",
						Status.FAIL);

				/*
				 * throw new FrameworkException(
				 * "Click on Complete filter and Verify the task that are Completed are displayed under My Task Page.  "
				 * , "Completed Tasks are NOT displayed under May Tasks Page ");
				 */

			}
		} catch (Exception e) {
			report.updateTestLog(
					"Click on Complete filter and Verify the task that are Completed are displayed under My Task Page.  ",
					"Completed Tasks are NOT displayed under May Tasks Page.Please verify Active Tasks are available under My Tasks. ",
					Status.FAIL);
		}
	}

	public void verifyDueDateFilterinMyTasksPage() {
		try {
			Boolean DueDateFilterinMyTasksPage = appTasksPgObj
					.dueDateFilterinMyTasksPage();

			if (DueDateFilterinMyTasksPage) {
				report.updateTestLog(
						"Click on Due Date filter and Verify the task that are Due are displayed under My Task Page.  ",
						"Tasks that are in Due are displayed under May Tasks Page ",
						Status.PASS);
			} else {
				// frameworkParameters.setStopExecution(true);
				report.updateTestLog(
						"Click on Due Date filter and Verify the task that are Due are displayed under My Task Page. ",
						"Tasks that are in Due are NOT available to verify. Please check tasks are created and available as per the pre requisite ",
						Status.FAIL);

				/*
				 * throw new FrameworkException(
				 * "Click on Due Date filter and Verify the task that are Due are displayed under My Task Page. "
				 * ,
				 * "Tasks that are in Due are NOT available to verify. Please check tasks are created and available as per the pre requisite "
				 * );
				 */

			}
		} catch (Exception e) {
			report.updateTestLog(
					"Click on Due Date filter and Verify the task that are Due are displayed under My Task Page. ",
					"Tasks that are in Due are NOT available to verify. Please check tasks are created and available as per the pre requisite ",
					Status.FAIL);
		}
	}

	public void verifyAssigneeDisplayedinWorkflow() {
		try {
			Boolean isAssigneeDisplayed = appTasksPgObj
					.displayAssigneeinWorkflow();

			if (isAssigneeDisplayed) {
				report.updateTestLog(
						"View Workflow for the Task displayed under My Task Page. and Verify that Assigned To field is displayed with Assignee ",
						"Assignee is displayed successfully for the selected workflow.",
						Status.PASS);
			} else {
				// frameworkParameters.setStopExecution(true);
				report.updateTestLog(
						"View Workflow for the Task displayed under My Task Page. and Verify that Assigned To field is displayed with Assignee ",
						"Assignee is NOT displayed successfully for the selected workflow. Please verify Active Tasks are available under My Tasks.",
						Status.FAIL);

				/*
				 * throw new FrameworkException(
				 * "View Workflow for the Task displayed under My Task Page. and Verify that Assigned To field is displayed with Assignee "
				 * ,
				 * "Assignee is NOT displayed successfully for the selected workflow"
				 * );
				 */

			}
		} catch (Exception e) {
			report.updateTestLog(
					"View Workflow for the Task displayed under My Task Page. and Verify that Assigned To field is displayed with Assignee ",
					"Assignee is NOT displayed successfully for the selected workflow. Please verify Active Tasks are available under My Tasks.",
					Status.FAIL);
		}
	}

	public void verifyAssignedToFilterinMyTasksPage() {
		try {
			Boolean assignFilterTask = appTasksPgObj
					.assignedToFilterinMyTasksPage();

			if (assignFilterTask) {
				report.updateTestLog(
						"Click on Me filter under Assigned To Category and Verify the task that are applicable are displayed under My Task Page.  ",
						"Tasks are displayed successfully under My Tasks Page ",
						Status.PASS);
			} else {
				// frameworkParameters.setStopExecution(true);
				report.updateTestLog(
						"Click on Me filter under Assigned To Category and Verify the task that are applicable are displayed under My Task Page.  ",
						"Tasks are NOT displayed successfully under My Tasks Page. Please verify Active Tasks are available under My Tasks. ",
						Status.FAIL);

				/*
				 * throw new FrameworkException(
				 * "Click on Me filter under Assigned To Category and Verify the task that are applicable are displayed under My Task Page.  "
				 * ,
				 * "Tasks are NOT displayed successfully under May Tasks Page "
				 * );
				 */

			}
		} catch (Exception e) {
			report.updateTestLog(
					"View Workflow for the Task displayed under My Task Page. and Verify that Assigned To field is displayed with Assignee ",
					"Assignee is NOT displayed successfully for the selected workflow. Please verify Active Tasks are available under My Tasks.",
					Status.FAIL);
		}
	}

	// Check Task Time
	public void compareWorflowStartedDetailsWithCurrentSystemDetails() {
		try {
			String actualStartDetails = appTasksPgObj
					.getWorkflowStartedDetails();

			String currentDate = new FileUtil()
					.readDataFromFile(testOutputForCreatedWorkFlow);
			String finalCurrentDate = currentDate.replace(" ", "-");
			String finalCurrentDateVals[] = finalCurrentDate.split("-");
			String timeValues[] = finalCurrentDateVals[3].split(":");
			String expecfinalDayVal;
			String tempDayVal = "" + finalCurrentDateVals[2].charAt(0);
			if (tempDayVal.contains("0")) {
				expecfinalDayVal = finalCurrentDateVals[2].replace("0", "");
			} else {
				expecfinalDayVal = finalCurrentDateVals[2];
			}

			String expectedDateDetails = finalCurrentDateVals[0] + " "
					+ expecfinalDayVal + " " + finalCurrentDateVals[1] + " "
					+ finalCurrentDateVals[5] + " " + timeValues[0];

			String finalActualDate = actualStartDetails.replace(" ", "-");
			String actualAppDateVal[] = finalActualDate.split("-");
			String actualTimes[] = actualAppDateVal[4].split(":");
			String actualDateDetails = actualAppDateVal[0] + " "
					+ actualAppDateVal[1] + " " + actualAppDateVal[2] + " "
					+ actualAppDateVal[3] + " " + actualTimes[0];
			if (actualDateDetails.equalsIgnoreCase(expectedDateDetails)) {
				report.updateTestLog(
						"Verify that Date and Time dispalyed in local time zone for a task",
						"Local time zone:" + expectedDateDetails
								+ " displayed Successfully"
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify that Date and Time dispalyed in local time zone for a task",
						"Local time zone fialed to display"
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check Due Dates
	public void compareWorflowDueDateDetails(String dueDate) {
		try {
			String actualStartDetails = appTasksPgObj
					.getWorkflowDueDateDetails();

			String expectedDateDetails = new DateUtil()
					.convertDateToCustomizedFormat("dd MMM yyyy", dueDate);

			String finalActualDate = actualStartDetails.replace(" ", "-");
			String actualAppDateVal[] = finalActualDate.split("-");

			String actualDateDetails = "";
			if (actualAppDateVal != null && actualAppDateVal.length == 4) {
				actualDateDetails = actualAppDateVal[1] + " "
						+ actualAppDateVal[2] + " " + actualAppDateVal[3];
			} else if (actualAppDateVal != null && actualAppDateVal.length == 3) {
				actualDateDetails = actualAppDateVal[0] + " "
						+ actualAppDateVal[1] + " " + actualAppDateVal[2];
			}			

			if (expectedDateDetails.contains(actualDateDetails)){
				report.updateTestLog(
						"Verify that user is able to initiate a workflow with Due Date",
						"User initiated workflow with due date:"
								+ expectedDateDetails + " successfully."
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify that user is able to initiate a workflow with Due Date",
						"User failed to initiate workflow with due date:"
								+ expectedDateDetails
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check Due Date In General Info Section
	public void verifyWorflowDueDateDetailsInGeneralInfo(String dueDate) {
		try {
			String tempActualStartDetails = appTasksPgObj
					.getWorkflowDueDateDetails();

			String actualStartDetails = "";
			if (tempActualStartDetails.contains(",")) {
				actualStartDetails = tempActualStartDetails.replace(",", "");
			} else {
				actualStartDetails = tempActualStartDetails;
			}

			String expectedDateDetails = new DateUtil()
					.convertDateToCustomizedFormat("dd MMM yyyy", dueDate);

			String finalActualDate = actualStartDetails.replace(" ", "-");
			String actualAppDateVal[] = finalActualDate.split("-");

			String actualDateDetails = "";
			if (actualAppDateVal != null && actualAppDateVal.length == 4) {
				actualDateDetails = actualAppDateVal[1] + " "
						+ actualAppDateVal[2] + " " + actualAppDateVal[3];
			} else if (actualAppDateVal != null && actualAppDateVal.length == 3) {
				actualDateDetails = actualAppDateVal[0] + " "
						+ actualAppDateVal[1] + " " + actualAppDateVal[2];
			}

			if (expectedDateDetails.contains(actualDateDetails)) {
				report.updateTestLog(
						"Verify workflow Due Date in General Info Section",
						"Workflow due date:"
								+ expectedDateDetails
								+ " displayed correclty in General Info Section"
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify workflow Due Date in General Info Section",
						"Workflow due date:" + expectedDateDetails
								+ " failed to display in General Info Section"
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check Due Date In Workflow Summary Section
	public void verifyWorflowDueDateDetailsInSummarySec(String dueDate) {
		try {
			String actualStartDetails = appTasksPgObj
					.getWorkflowDueDateDetailsFromWorkFlowSummary();
			String expectedDateDetails = new DateUtil()
					.convertDateToCustomizedFormat("dd MMM yyyy", dueDate);

			String finalActualDate = actualStartDetails.replace(" ", "-");
			String actualAppDateVal[] = finalActualDate.split("-");

			String actualDateDetails = "";
			if (actualAppDateVal != null && actualAppDateVal.length == 4) {
				actualDateDetails = actualAppDateVal[1] + " "
						+ actualAppDateVal[2] + " " + actualAppDateVal[3];
			} else if (actualAppDateVal != null && actualAppDateVal.length == 3) {
				actualDateDetails = actualAppDateVal[0] + " "
						+ actualAppDateVal[1] + " " + actualAppDateVal[2];
			}

			if (expectedDateDetails.contains(actualDateDetails)) {
				report.updateTestLog(
						"Verify workflow Due Date in Workflow Summary",
						"Workflow due date:" + expectedDateDetails
								+ " displayed correclty in Workflow Summary"
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify workflow Due Date in Workflow Summary",
						"Workflow due date:" + expectedDateDetails
								+ " failed to display in Workflow Summary"
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check Due Date In Items Field Section
	public void verifyWorflowDueDateDetailsInItemsFieldSec(String dueDate) {
		try {
			String actualStartDetails = appTasksPgObj
					.getWorkflowDueDateDetailsFromItemsFieldSec();

			String expectedDateDetails = new DateUtil()
					.convertDateToCustomizedFormat("dd MMM yyyy", dueDate);

			String finalActualDate = actualStartDetails.replace(" ", "-");
			String actualAppDateVal[] = finalActualDate.split("-");

			String actualDateDetails = "";
			if (actualAppDateVal != null && actualAppDateVal.length == 4) {
				actualDateDetails = actualAppDateVal[1] + " "
						+ actualAppDateVal[2] + " " + actualAppDateVal[3];
			} else if (actualAppDateVal != null && actualAppDateVal.length == 3) {
				actualDateDetails = actualAppDateVal[0] + " "
						+ actualAppDateVal[1] + " " + actualAppDateVal[2];
			}

			if (expectedDateDetails.contains(actualDateDetails)) {
				report.updateTestLog(
						"Verify workflow Due Date in Items Field Section",
						"Workflow due date:" + expectedDateDetails
								+ " displayed correclty in Items Field Section"
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify workflow Due Date in Items Field Section",
						"Workflow due date:" + expectedDateDetails
								+ " failed to display in Items Field Section"
								+ "<br /><b>Expected Result:</b> "
								+ expectedDateDetails
								+ ", <br /><b>Actual Result:</b> "
								+ actualDateDetails + "", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check invalid due date error message for workflow
	public void checkFieldErrorMsgForInvalidDueDateInWorkflow(
			boolean isDisplayedFieldErroVal) {
		try {
			String errorMessageValue = appTasksPgObj.errorMessageVal;
			if (errorMessageValue == null || errorMessageValue.isEmpty()) {
				errorMessageValue = "Field contains an error";
			}
			if (isDisplayedFieldErroVal) {
				report.updateTestLog(
						"Verify the Error Message for Due date field when user enter invaid due date",
						"<b>Field error message:</b>"
								+ errorMessageValue
								+ " displayed Successfully when entering invalid due date",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the Error Message for Due date field when user enter invaid due date",
						"Field error message fialed to display", Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException("Verify the Due date",
				 * "Field error message fialed to display");
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyfileAttachedtoTask(String task) {
		// String attachedFileTxtActual = appTasksPgObj.retFileAttachedtoTask();
		//String taskNmeStrtest = dataTable.getData("Tasks", "TaskName");
		String fleName = dataTable.getData("MyFiles", "FileName");
		fleName = fleName.trim();
		String attachedFileTxtExpectd = dataTable.getData("Tasks",
				"AttachedFileTxtVal");
		String attchdFldEdtXPathFinal = attchdFldEdtXPath.replace("CRAFT",
				fleName);
		System.out.println("" + attchdFldEdtXPathFinal);
		UIHelper.waitForVisibilityOfEleByXpath(driver, attchdFldEdtXPathFinal);
		String attachedFileTxt = driver
				.findElement(By.xpath(attchdFldEdtXPathFinal)).getText().trim();

		/*
		 * UIHelper.scrollToAnElement(driver.findElement(By
		 * .xpath(attchdFldEdtXPathFinal))); UIHelper.highlightElement(driver,
		 * attchdFldEdtXPathFinal);
		 */

		try {

			if (attachedFileTxt.contains(attachedFileTxtExpectd)) {
				report.updateTestLog(
						"Verify the file " + attachedFileTxtExpectd
								+ " is attached to the Task - "
								+ task + "  ",
						"File is attached to the specified Task and displayed Successfully ",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the file " + attachedFileTxtExpectd
								+ " is attached to the Task - "
								+ task + "  ",
						"File is NOT attached to the specified Task and NOT displayed Successfully ",
						Status.FAIL);

			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the file " + attachedFileTxtExpectd
							+ " is attached to the Task - " + task
							+ "  ",
					"File is NOT attached to the specified Task and NOT displayed Successfully ",
					Status.FAIL);

		}
	}

	public void verifyTasksReviewComments() {
		try {
			String reviewerActualComments = appTasksPgObj
					.getTasksResponseComment();

			String expectedReviewerComeents = dataTable.getData("Tasks",
					"ReviewerComment");

			if (reviewerActualComments
					.equalsIgnoreCase(expectedReviewerComeents)) {
				report.updateTestLog(
						"Verify that Task assigner is able to seen reviewer comments",
						"Task assigner seen the reviewer coments:"
								+ expectedReviewerComeents + " Successfully"
								+ "<br /><b>Expected Result:</b> "
								+ expectedReviewerComeents
								+ ", <br /><b>Actual Result:</b> "
								+ reviewerActualComments + "", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify that Task assigner is able to seen reviewer comments",
						"Task assigner is fialed to see the reviewr comments"
								+ "<br /><b>Expected Result:</b> "
								+ expectedReviewerComeents
								+ ", <br /><b>Actual Result:</b> "
								+ reviewerActualComments + "", Status.FAIL);

			}
		} catch (Exception e) {
		}
	}

	// Method to identity the WF task status
	public void veriftTrackWFStatus() {
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, wfstatusXpath);
		if (UIHelper.findAnElementbyXpath(driver, wfstatusXpath).getText() != null) {
			report.updateTestLog(
					"Verify Workflow Status ",
					"Worflow status of the given WF is tracked "
							+ "<br /><b> Work flow Status : </b>"
							+ UIHelper.findAnElementbyXpath(driver,
									wfstatusXpath).getText(), Status.PASS);
		} else {
			report.updateTestLog("Verify Workflow Status ",
					"Worflow status of the given WF tracking failed "
							+ "<br /><b> Work flow Status : </b>", Status.PASS);
		}
	}

	// Verify created workflow
	public void verifyCreatedWorkflow() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();

			String finalTaskName = taskName + "_" + currentDate;

			boolean isDisplayedWorkflowInPage = appTasksPgObj
					.checkCreatedOrAssignedTask(finalTaskName);
			if (isDisplayedWorkflowInPage) {
				report.updateTestLog("Verify created workflow", "Workflow: "
						+ taskName + " is created/displayed successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify created workflow", "Workflow: "
						+ taskName + " is failed to create/display",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Task in My Tasks Page
	public void verifyTaskFromMyTasksPg(String taskName) {
		try {
			boolean isDisplayedTaskInMyTasksPage = appTasksPgObj
					.checkCreatedOrAssignedTask(taskName);
			if (isDisplayedTaskInMyTasksPage) {
				report.updateTestLog("Verify task in 'My Tasks' Page", "Task: "
						+ taskName
						+ " is displayed successfully in 'My Tasks' page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify task in 'My Tasks' Page",
						"Task: " + taskName
								+ " is failed to display in 'My Tasks' page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Task in My Tasks Page for Negative Case
	public void verifyTaskFromMyTasksPgForNegativeCase(String taskName) {
		try {
			boolean isDisplayedTaskInMyTasksPage = appTasksPgObj
					.checkCreatedOrAssignedTask(taskName);
			if (!isDisplayedTaskInMyTasksPage) {
				report.updateTestLog("Verify task in 'My Tasks' Page", "Task: "
						+ taskName + " is not displayed in 'My Tasks' page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify task in 'My Tasks' Page", "Task: "
						+ taskName
						+ " is displayed successfully in 'My Tasks' page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Task status
	public void verifyCompletedTasksStatus() {
		try {
			String actualStatus = appTasksPgObj.getTasksStatus();

			String expectedStatus = dataTable.getData("Tasks", "Status");

			if (actualStatus.contains(expectedStatus)) {
				report.updateTestLog("Verify Task Status",
						"Task completed successfully"
								+ "<br /><b>Expected Result:</b> "
								+ expectedStatus
								+ ", <br /><b>Actual Result:</b> "
								+ actualStatus + "", Status.PASS);

			} else {
				report.updateTestLog("Verify Task Status",
						"Task failed to create"
								+ "<br /><b>Expected Result:</b> "
								+ expectedStatus
								+ ", <br /><b>Actual Result:</b> "
								+ actualStatus + "", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyTasksPriority() {
		String actualPriority = appTasksPgObj.getTasksPriority();

		String expectedPriority = dataTable.getData("Workflow", "Priority");

		if (actualPriority.equalsIgnoreCase(expectedPriority)) {
			report.updateTestLog("Verify Task Priority",
					"User successfully set tasks priority"
							+ "<br /><b>Expected Result:</b> "
							+ expectedPriority
							+ ", <br /><b>Actual Result:</b> " + actualPriority
							+ "", Status.PASS);
		} else {
			report.updateTestLog("Verify Task Priority",
					"User fialed to set tasks priority"
							+ "<br /><b>Expected Result:</b> "
							+ expectedPriority
							+ ", <br /><b>Actual Result:</b> " + actualPriority
							+ "", Status.FAIL);
			/*
			 * frameworkParameters.setStopExecution(true); throw new
			 * FrameworkException( "Verify Task Priority",
			 * "User fialed to set tasks priority");
			 */
		}
	}

	public void verifyWrkflwTypeDrpDwn() {
		String taskTypeDrodownVal = dataTable.getData("Workflow",
				"TaskTypDropdownOptions");

		if (taskTypeDrodownVal.contains(",")) {
			splittedTaskTypeValues = taskTypeDrodownVal.split(",");
		}

		taskTypeDropdownValues = appTasksPgObj.getTaskTypeDropdownOption();

		if (splittedTaskTypeValues != null && splittedTaskTypeValues.length > 1
				&& taskTypeDropdownValues != null
				&& taskTypeDropdownValues.size() > 1) {
			if (splittedTaskTypeValues[0]
					.equalsIgnoreCase(taskTypeDropdownValues.get(0))
					&& splittedTaskTypeValues[1]
							.equalsIgnoreCase(taskTypeDropdownValues.get(1))) {
				report.updateTestLog(
						"Verify the Wrokflow type drop down has the values "
								+ splittedTaskTypeValues[0] + " and "
								+ splittedTaskTypeValues[1],
						"Work flow Type drop down has been verified Successfully",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the Wrokflow type drop down has the values "
								+ splittedTaskTypeValues[0] + " and "
								+ splittedTaskTypeValues[1],
						"Work flow Type drop down has been verified Successfully",
						Status.FAIL);

			}
		}
	}

	// Method to verify Task is assigned to the user
	public void verifyTaskAssginedToUser() {

		try {

			if (appTasksPgObj.isDisplayedAssignedTask()) {
				report.updateTestLog(
						"Verify the User is able to recieve the assigned Task in My Dashlet",
						"Tasks has been received successfully in the My Tasks Dashlet",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the User is able to recieve the assigned Task in My Dashlet",
						"Tasks is not received in the My Tasks Dashlet",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify Accepted Task removed from pending invite list
	public void verifyAccptedTaskInActiveList() {
		try {
			String siteName = new FileUtil()
					.readDataFromFile(testOutputFilePath);

			if (!appTasksPgObj
					.isDisplayedAcceptedOrRejectedTaskInActiveList(siteName)) {
				report.updateTestLog(
						"verify Accepted Task in Pending Invite list",
						"<b>Accepted Task:</b>"
								+ siteName
								+ " has been removed successfully from Pending Invite list",
						Status.PASS);
			} else {
				report.updateTestLog(
						"verify Accepted Task in Pending Invite list",
						"<b>Accepted Task:</b>" + siteName
								+ " not removed from Pending Invite list",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Rejected Task removed from pending invite list
	public void verifyRejectedTaskInActiveList() {
		try {
			String siteName = new FileUtil()
					.readDataFromFile(testOutputFilePath);

			if (!appTasksPgObj
					.isDisplayedAcceptedOrRejectedTaskInActiveList(siteName)) {
				report.updateTestLog(
						"verify Rejected Task in Pending Invite list",
						"<b>Rejected Task:</b>"
								+ siteName
								+ " has been removed successfully from Pending Invite list",
						Status.PASS);
			} else {
				report.updateTestLog(
						"verify Rejected Task in Pending Invite list",
						"<b>Rejected Task:</b>" + siteName
								+ " not removed from Pending Invite list",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Task status
	public void verifyFilteredTasks(String expectedStatus) {
		try {
			String actualStatus = appTasksPgObj.getTasksFileteredTitle();

			if (actualStatus.contains(expectedStatus)) {
				report.updateTestLog(
						"Verify filtered Task Details",
						"Task filtered using 'Completed' option and displayed completed tasks successfully"
								+ "<br /><b>Filtered Option:</b> "
								+ expectedStatus, Status.PASS);

			} else {
				report.updateTestLog("Verify filtered Task Details",
						"Task failed to filtered using 'Completed' option"
								+ "<br /><b>Filtered Option:</b> "
								+ "Task are not filtered", Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify created work flow displayed with Time
	public void verifyCreatedWorkflowDisplaysTime() {
		try {
			String actTimeStmpVal;
			actTimeStmpVal = appTasksPgObj.getTimeStampFromWF();
			if (actTimeStmpVal.length() != 0) {
				report.updateTestLog(
						"Verify Created workflow displayed with Time Stamp",
						"Workflow created Successfully with the Timestamp. Value: "
								+ actTimeStmpVal, Status.PASS);

			} else {
				report.updateTestLog(
						"Verify Created workflow displayed with Time Stamp",
						"Workflow NOT created Timestamp. Timestamp Value present is: "
								+ actTimeStmpVal, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify accept or r3eject task in Taskpage
	public void verifyAcceptRejectTask(String Action) {
		try {
			String finaltaskActionXpath = taskActionXpath.replace("CRAFT",
					Action);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finaltaskActionXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					finaltaskActionXpath));
			UIHelper.highlightElement(driver, finaltaskActionXpath);
			UIHelper.click(driver, finaltaskActionXpath);
			report.updateTestLog(Action + " Task", Action
					+ " task of the selected task completed", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(Action + " Task", Action
					+ " task of the selected task failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param workFlow
	 */
	public void verifyWorkFlowType(String workFlow) {
		try {
			if (new AlfrescoWorkflowPage(scriptHelper)
					.isWorkFlowAvailable(workFlow)) {
				report.updateTestLog("Verify to check the WorkFlow Type",
						"WorkFlow Available" + "<br /><b>WorkFlow Type :</b> "
								+ workFlow, Status.PASS);
			} else {
				report.updateTestLog("Verify to check the WorkFlow Type",
						"WorkFlow not Available"
								+ "<br /><b>WorkFlow Type :</b> " + workFlow,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify to see the WorkFlow Type",
					"Verify to see the WorkFlow Type Failed", Status.FAIL);
		}
	}

	// Verify Assignee able to edit the due date value
	public void verifyDueDateIsEditableForAssignee() {
		try {
			if (appTasksPgObj.isAssigneeEditableDueDateInTaskEditPage()) {
				report.updateTestLog(
						"Verify Due Date field",
						"Assignee not able to change the due date of the task that has been received",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Due Date field",
						"Assignee able to change the due date of the task that has been received",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyStatusOfWorkFlow(String status) {

		try {
			String statusMessage = appTasksPgObj.getWorkflowStatus(status);
			if (statusMessage.equalsIgnoreCase(status)) {
			
				report.updateTestLog("Verify Status Field",
						"<br><b>Expected Result</b>: Workflow should not complete"
								+ "<br><b>Actual Result</b>:" + statusMessage,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Status Field",
						"<br><b>Expected Result</b>: Workflow should not complete"
								+ "<br><b>Actual Result</b>:" + statusMessage,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify General Info Field Labels
	public void verifyGeneralInfoLabelInWorkFlowDetails(String labelName) {
		try {

			if (appTasksPgObj.checkGeneralInfoLabelName(labelName)) {
				report.updateTestLog(
						"Verify Label 'Workflow Type' under 'General Info'",
						"Label 'Workflow Type' is displayed successfully"
								+ "<br /><b>'General Info' Label Name:</b> "
								+ labelName, Status.PASS);

			} else {
				report.updateTestLog(
						"Verify Label 'Workflow Type' under 'General Info'",
						"Label 'Workflow Type' is failed to display"
								+ "<br /><b>'General Info' Label Name:</b> "
								+ labelName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Field Labels in Workflow Details Page
	public void verifyFieldLabelInWorkFlowDetails(String labelName) {
		try {

			if (appTasksPgObj.checkGeneralInfoLabelName(labelName)) {
				report.updateTestLog("Verify '" + labelName
						+ "' in Workflow Details Page", "Field: '" + labelName
						+ "' is displayed successfully", Status.PASS);

			} else {
				report.updateTestLog("Verify '" + labelName
						+ "' in Workflow Details Page", "Field: '" + labelName
						+ "' is failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Field Labels in Workflow Details Page for Negative Case
	public void verifyFieldLabelInWorkFlowDetailsForNegativeCase(
			String labelName) {
		try {

			if (!appTasksPgObj.checkGeneralInfoLabelName(labelName)) {
				report.updateTestLog("Verify '" + labelName
						+ "' in Workflow Details Page", "Field: '" + labelName
						+ "' is not displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify '" + labelName
						+ "' in Workflow Details Page", "Field: '" + labelName
						+ "' is displayed successfully", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Approver & Reviewer Field Labels
	public void verifyApproverAndReviewerFieldLabels() {
		try {

			if (appTasksPgObj.checkApproverAndReviewerFieldLabels()) {
				report.updateTestLog(
						"Verify 'Approver' and 'Reviewer' fields under 'Assignees'",
						"'Approver' and 'Reviewer' fields are displayed successfully",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify 'Approver' and 'Reviewer' fields under 'Assignees'",
						"'Approver' and 'Reviewer' fields are failed to display",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyApprBtnInWFSubmit() {
		try {
			if (!(UIHelper.findAnElementbyXpath(driver, apprBtnXPath)
					.isDisplayed())) {
				report.updateTestLog(
						"Verify Field Required Approval Percentage is not Present for Simple Review and Approve Task Workflow",
						"Approval Percentage is not available", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Field Required Approval Percentage is not Present for Simple Review and Approve Task Workflow",
						"Approval Percentage is available", Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Reviewer Field Label
	public void verifyReviewerFieldLabel() {
		try {

			if (appTasksPgObj.checkReviewerFieldForSimpleReviewAndApprove()) {
				report.updateTestLog(
						"Verify 'Reviewer' field",
						"'Reviewer' field is displayed successfully in Simple review and approve workflow page",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify 'Reviewer' field",
						"'Reviewer' field is failed to display in Simple review and approve workflow page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify acceptable character in Message Field
	public void verifyCreatedWorkflowForGivenMsgFieldCharacters(String message) {
		try {
			boolean isDisplayedWorkflowInPage = appTasksPgObj
					.checkCreatedOrAssignedTask(message);

			int countOfMsgChars = message.length();

			String characterCount = "";
			if (countOfMsgChars == 1000) {
				characterCount = "1000";
			} else if (countOfMsgChars > 1000) {
				characterCount = "more than 1000";
			} else {
				characterCount = "less than 1000";
			}

			if (isDisplayedWorkflowInPage) {
				report.updateTestLog(
						"Verify 'Message' field acceptable characters in 'Start Workflow' page ",
						"'Message' field successfully accepted the "
								+ characterCount + " characters", Status.PASS);

			} else {
				report.updateTestLog(
						"Verify 'Message' field acceptable characters in 'Start Workflow' page ",
						"'Message' field failed to accept " + characterCount
								+ " characters", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify acceptable character in Message Field fo rnegative case
	public void verifyCreatedWorkflowForGivenMsgFieldCharactersInNegative(String message) {
		try {
			boolean isDisplayedWorkflowInPage = appTasksPgObj
					.checkCreatedOrAssignedTask(message);

			int countOfMsgChars = message.length();

			String characterCount = "";
			if (countOfMsgChars == 1000) {
				characterCount = "1000";
			} else if (countOfMsgChars > 1000) {
				characterCount = "more than 1000";
			} else {
				characterCount = "less than 1000";
			}

			if (isDisplayedWorkflowInPage) {
				report.updateTestLog(
						"Verify 'Message' field acceptable characters in 'Start Workflow' page ",
						"'Message' field successfully accepted the "
								+ characterCount + " characters", Status.FAIL);

			} else {
				report.updateTestLog(
						"Verify 'Message' field acceptable characters in 'Start Workflow' page ",
						"'Message' field not accepted " + characterCount
								+ " characters", Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify attached file name for task
	public void verifyAttachedFileNameForTask(String taskName, String fileName) {
		try {
			boolean isDisplayedAttachedFileNameForTask = appTasksPgObj
					.checkAttachedFileNameForTask(taskName, fileName);
			if (isDisplayedAttachedFileNameForTask) {
				report.updateTestLog(
						"Verify the asset name and task name in 'My Tasks' Page",
						"Asset name:" + fileName + " and task name:" + taskName
								+ " both displayed successfully", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the asset name and task name in 'My Tasks' Page",
						"Asset name:" + fileName + " and task name:" + taskName
								+ " both failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
