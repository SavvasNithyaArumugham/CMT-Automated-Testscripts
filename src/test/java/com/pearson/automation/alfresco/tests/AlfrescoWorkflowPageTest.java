package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoWorkflowPageTest extends ReusableLibrary {

	private AlfrescoWorkflowPage appWorkflowPg = new AlfrescoWorkflowPage(
			scriptHelper);

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoWorkflowPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Verify Field Header Titles in Workflow Details Page
	public void verifyAlfrescoLogoAndWorkflowDetailsInWorkFlowDetails() {
		try {

			if (appWorkflowPg.checkAlfrescoLogoAndWorkflowDetailsHeader()) {
				report.updateTestLog(
						"Verify Pearson Logo|Workflow Details",
						"Pearson Logo|Workflow Details are displayed successfully in Workflow Details Page",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify Pearson Logo|Workflow Details",
						"Pearson Logo|Workflow Details are failed to display in Workflow Details Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Field Header Titles in Workflow Details Page
	public void verifyTaskAndWorkflowDetailsInWorkFlowDetails() {
		try {

			if (appWorkflowPg.checkTaskDetails()) {
				report.updateTestLog(
						"Verify Details:, Task Details|Workflow Details & Workflow Summary in a box",
						"Details:, Task Details|Workflow Details & Workflow Summary in a box are displayed successfully in Workflow Details Page",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify Details:, Task Details, Workflow Details & Workflow Summary",
						"Details:, Task Details|Workflow Details & Workflow Summary in a box are failed to display in Workflow Details Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Field Header Titles in Workflow Details Page
	public void verifyFieldsHeaderTitleInWorkFlowDetails(String fieldHeaderName) {
		try {

			if (appWorkflowPg.checkFieldHeaderTitleNames(fieldHeaderName)) {
				report.updateTestLog("Verify '" + fieldHeaderName
						+ "' in Workflow Details Page",
						"Fields Header Title: '" + fieldHeaderName
								+ "' is displayed successfully", Status.PASS);

			} else {
				report.updateTestLog("Verify '" + fieldHeaderName
						+ "' in Workflow Details Page",
						"Fields Header Title: '" + fieldHeaderName
								+ "' is failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify General Info Field Labels and Values
	public void verifyFieldLabelAndValInWorkFlowDetails(String labelName,
			String fieldVal) {
		try {

			if (appWorkflowPg.checkFieldLabelAndValue(labelName, fieldVal)) {
				report.updateTestLog("Verify '" + labelName
						+ "' in Workflow Details Page", "Field: '" + labelName
						+ "' and Field Value: '" + fieldVal
						+ "' displayed successfully", Status.PASS);

			} else {
				report.updateTestLog("Verify '" + labelName
						+ "' in Workflow Details Page", "Field: '" + labelName
						+ "' and Field Value: '" + fieldVal
						+ "' are failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Field Labels and Values in WorkFlow Details Page
	public void verifyMessageFieldValInWorkFlowDetails(String labelName,
			String fieldVal) {
		try {

			if (appWorkflowPg.checkFieldLabelAndValue(labelName, fieldVal)) {
				report.updateTestLog(
						"Verify '" + labelName
								+ "' field value in Workflow Details Page",
						"Text in'"
								+ labelName
								+ "' Field is same as the text given in description by initiator"
								+ "<br /><b>Message Field Value:</b> "
								+ fieldVal, Status.PASS);

			} else {
				report.updateTestLog(
						"Verify '" + labelName
								+ "' field value in Workflow Details Page",
						"Text in'"
								+ labelName
								+ "' Field is not same as the text given in description by initiator"
								+ "<br /><b>Message Field Value:</b> "
								+ fieldVal, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify General Info Field Labels in WorkFlow Details Page
	public void verifyGeneralInfoFieldInWorkFlowDetails(
			String expectedFieldlabelNames) {
		try {
			ArrayList<String> expeGeneralInfoFieldsList = new ArrayList<String>();
			if (expectedFieldlabelNames.contains(",")) {
				String splittedExpectedFieldlabelNames[] = expectedFieldlabelNames
						.split(",");

				if (splittedExpectedFieldlabelNames != null) {
					for (String genInfoFieldName : splittedExpectedFieldlabelNames) {
						expeGeneralInfoFieldsList.add(genInfoFieldName);
					}
				}
			}

			ArrayList<String> actualGeneralInfoFieldsList = appWorkflowPg
					.getGeneralInfoFieldLabels();

			boolean isDisplayedGenInfoFieldLabels = UIHelper
					.compareTwoDiffSizeOfLists(expeGeneralInfoFieldsList,
							actualGeneralInfoFieldsList);

			ArrayList<String> finalGeneralInfoFieldsList = new ArrayList<String>();
			Set<String> hs1 = new HashSet<>();
			hs1.addAll(actualGeneralInfoFieldsList);
			finalGeneralInfoFieldsList.addAll(hs1);

			boolean isDisplayedDuplicateFieldNames = false;
			if (actualGeneralInfoFieldsList.size() == finalGeneralInfoFieldsList
					.size()) {
				isDisplayedDuplicateFieldNames = true;
			} else {
				isDisplayedDuplicateFieldNames = false;
			}

			if (isDisplayedGenInfoFieldLabels && isDisplayedDuplicateFieldNames) {
				report.updateTestLog(
						"Verify 'General Info' field Labels in Workflow Details Page",
						"No field is displayed more than once in General info"
								+ "<br /><b>General Info Field Labels:</b> "
								+ actualGeneralInfoFieldsList, Status.PASS);

			} else {
				report.updateTestLog(
						"Verify 'General Info' field Labels in Workflow Details Page",
						"Fields are displayed more than once in General info"
								+ "<br /><b>General Info Field Labels:</b> "
								+ actualGeneralInfoFieldsList, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Field value is dame in Workflow summary and General info for
	// Simple Task
	public void verifyFieldValInWorkFlowSumAndGeneralInfo(String fieldLabelName) {
		try {

			if (appWorkflowPg
					.checkFieldValueWorkflowAndGeneralInfoSec(fieldLabelName)) {
				report.updateTestLog(
						"Verify '"
								+ fieldLabelName
								+ "' field value in Workflow Summary and General Info",
						"'"
								+ fieldLabelName
								+ "' field value in Workflow summary and General info is same in Workflow Detail page for Simple Task Workflow"
								+ "<br />" + fieldLabelName
								+ " Field Value from WorkFlow Summary: <b>"
								+ appWorkflowPg.workFlowSummarySecFieldVal
								+ "</b>" + "<br />" + fieldLabelName
								+ " Field Value from General Info: <b>"
								+ appWorkflowPg.generalInfoSecFieldVal + "</b>",
						Status.PASS);

			} else {

				report.updateTestLog(
						"Verify '"
								+ fieldLabelName
								+ "' field value in Workflow Summary and General Info",
						"'"
								+ fieldLabelName
								+ "' field value in Workflow summary and General info is not same in Workflow Detail page for Simple Task Workflow"
								+ "<br />" + fieldLabelName
								+ " Field Value from WorkFlow Summary: <b>"
								+ appWorkflowPg.workFlowSummarySecFieldVal
								+ "</b>" + "<br />" + fieldLabelName
								+ " Field Value from General Info: <b>"
								+ appWorkflowPg.generalInfoSecFieldVal + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check changed priority for Task
	public void checkChangedTaskPriority(String priorityVal) {
		try {
			String selectedPriorityVal = appWorkflowPg
					.getChangedPriorityFromEditTaskPg();

			if (selectedPriorityVal.equalsIgnoreCase(priorityVal)) {
				report.updateTestLog(
						"Verify assignee is able to change the Priority",
						"Asignee changed the priority successfully for task"
								+ "<br /><b> Priority: </b>" + priorityVal,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify assignee is able to change the Priority",
						"Asignee failed to change priority for task"
								+ "<br /><b> Priority : </b>" + priorityVal,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Field Labels and Values in WorkFlow Details Page
	public void verifyPriorityFieldLabelAndValInWorkFlowDetails(
			String labelName, String fieldVal) {
		try {

			if (appWorkflowPg.checkFieldLabelAndValue(labelName, fieldVal)) {
				report.updateTestLog(
						"Verify '" + labelName + "' in Workflow Details Page",
						"The '"
								+ labelName
								+ "' of the task is same as set by assigner while initiating the workflow"
								+ "<br /><b> Priority : </b>" + fieldVal,
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify '" + labelName + "' in Workflow Details Page",
						"The '"
								+ labelName
								+ "' of the task is not same as set by assigner while initiating the workflow",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check changed priority for Task
	public void checkDefaultTaskPriority(String priorityVal) {
		try {
			String selectedPriorityVal = appWorkflowPg
					.getChangedPriorityFromEditTaskPg();

			if (selectedPriorityVal.equalsIgnoreCase(priorityVal)) {
				report.updateTestLog("Verify Priority visible to Assignee",
						"Priority visible to Assignee is" + priorityVal,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Priority visible to Assignee",
						"Assignee is able to view the default Priority:"
								+ priorityVal, Status.FAIL);
				report.updateTestLog(
						"Verify assignee is able to change the Priority",
						"Assignee is not able to view the default Priority:"
								+ priorityVal, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Verify History Field Value in WorkFlow Details Page
	public void verifyHistoryFieldValueInWorkFlowDetails(String labelName,
			String fieldVal) {
		try {

			if (appWorkflowPg.checkHistoryFieldValue(labelName, fieldVal)) {
				
				report.updateTestLog("Verify History Field '" + labelName
						+ "' value in Workflow Details Page", "History Field " + labelName
						+ " Value:"+fieldVal+" is displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify History Field '" + labelName
						+ "' value in Workflow Details Page", "History Field " + labelName
						+ " Value:"+fieldVal+" is failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Verify History Field Value in WorkFlow Details Page
	public void verifyPreviousComments(String labelName,
			String fieldVal) {
		try {

			if (appWorkflowPg.checkPreviousComments(labelName, fieldVal)) {
				
				report.updateTestLog("Verify Previous Comments Outcome Field '" + labelName
						+ "' value in Workflow Details Page", "Outcome Field " + labelName
						+ " Value:"+fieldVal+" is displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Previous Comments Outcome Field '" + labelName
						+ "' value in Workflow Details Page", "Outcome Field " + labelName
						+ " Value:"+fieldVal+" is failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
