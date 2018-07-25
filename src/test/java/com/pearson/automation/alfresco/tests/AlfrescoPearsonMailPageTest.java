package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;

import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoPearsonMailPageTest extends ReusableLibrary {

	private AlfrescoPearsonMailPage gmailObj = new AlfrescoPearsonMailPage(scriptHelper);

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoPearsonMailPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Check Mail Subject Details
	public void verifyMailSubjectDetails(String workflowName, String dueDate, String mailSubText) {
		try {
			String expectedDueDate = dueDate;
			String tempActualDueDate = gmailObj.openAssignedTaskMailAndGetDueDate(workflowName, dueDate, mailSubText);
			;
			String actualDueDate;
			if (tempActualDueDate == null || tempActualDueDate.isEmpty()) {
				actualDueDate = "Due Date is not found";
			} else {
				actualDueDate = tempActualDueDate;
			}

			if (actualDueDate.contains(expectedDueDate)) {
				report.updateTestLog("Verify Due date for Workflow in Email",
						"Submitted Workflow:" + workflowName + " due date:" + expectedDueDate
								+ " displayed Successfully in Email" + "<br /><b>Expected Result:</b> "
								+ expectedDueDate + "<br /><b>Actual Result:</b> " + actualDueDate + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Due date for Workflow in Email",
						"Submitted Workflow:" + workflowName + " due date:" + expectedDueDate
								+ " failed to display in Email" + "<br /><b>Expected Result:</b> " + expectedDueDate
								+ "<br /><b>Actual Result:</b> " + actualDueDate + "",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param workflowName
	 */
	public void verifyTimeStampDetails(String workflowName) {
		try {
			String actualTimeStamp = gmailObj.openMailAndCheckTimeStamp();
			if (actualTimeStamp == null || actualTimeStamp.isEmpty()) {
				actualTimeStamp = "Due Date is not found";
				report.updateTestLog("Verify Time Stamp for Recent Activity Email",
						"Time Stamp not Displayed" + "<br /><b> Submitted Workflow : </b>" + workflowName
								+ "<br /><b> Time Stamp :" + actualTimeStamp,
						Status.FAIL);
			} else {
				report.updateTestLog("Verify Time Stamp for Recent Activity Email",
						"Time Stamp Displayed Successfully" + "<br /><b> Submitted Workflow : </b>" + workflowName
								+ "<br /><b> Time Stamp :" + actualTimeStamp,
						Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify BulkDownload Mail Body Text
	public void verifyMailBodyText(String performedAction, String expectedMessage) {
		try {
			String actualMessage = gmailObj.getTextFromBulkDownloadMailBody();
			if (!actualMessage.contains(expectedMessage)) {
				report.updateTestLog("Verify review link in " + performedAction + " Mail Body",
						"'" + expectedMessage + "' is not Displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify review link in " + performedAction + " Mail Body",
						"'" + expectedMessage + "' is Displayed", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify BulkDownload Mail Body Text
	public void verifyMailBodyContentText(String performedAction, String expectedMessage) {
		try {
			String actualMessage = gmailObj.getTextFromBulkDownloadMailBody();
			if (expectedMessage.contains(",")) {
				String splittedStr[] = expectedMessage.split(",");
			   if ( (actualMessage.contains(splittedStr[0])) || (actualMessage.contains(splittedStr[1])) ) {
						report.updateTestLog("Verify " + performedAction +" displayed in Mail Body",
								"'" + performedAction + "' is Displayed", Status.PASS);
					} else {
						report.updateTestLog("Verify"  +performedAction +" is not displayed in Mail Body",
								"'" + performedAction + "' is not Displayed", Status.FAIL);
					
				}
			} else {
				if (actualMessage.contains(expectedMessage)) {
					report.updateTestLog("Verify" + expectedMessage + " in " + performedAction + " Mail Body",
							"'" + expectedMessage + "' is Displayed", Status.PASS);
				} else {
					report.updateTestLog("Verify review link in " + performedAction + " Mail Body",
							"'" + expectedMessage + "' is not Displayed", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyCommentDetails(String comment) {

		try {

			String actualComment = gmailObj.getCommentDetails(comment);

			if (actualComment.contains(comment)) {
				report.updateTestLog("Verify Comment details",
						"Comment Details Verified Sucessfully <br><b>Actual Result</b>" + actualComment
								+ "<br><b> Expected Result</b>" + comment,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Comment details",
						"Comment Details Verified Sucessfully<br><b>Actual Comment</b>" + actualComment
								+ "<br> <b>Expected Comment</b>" + comment,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check Email for workflow when perform start workflow
	public void verifyWorkflowMail(String performedOperation, String taskName, String mailSubText) {
		try {

			boolean isReceivedMail = gmailObj.checReceivedUserMail(performedOperation, taskName, mailSubText);

			if (isReceivedMail) {
				report.updateTestLog("Verify the received Reviewer mail subject when perform " + performedOperation,
						"Reviewer successfully notified with mail and subject of the mail is-'Review Only' when perform "
								+ performedOperation,
						Status.PASS);
			} else {

				report.updateTestLog("Verify the received Reviewer mail subject when perform " + performedOperation,
						"Reviewer failed to notify with mail subject as 'Review Only' when perform "
								+ performedOperation,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check 'Go to Site' link in Mail Body
	public void verifyGotoSiteLinkMailBody(String siteName) {
		try {

			boolean isDisplayeGotSiteLink = gmailObj.checkSiteLinkInMail(siteName);

			if (isDisplayeGotSiteLink) {
				report.updateTestLog("Verify the 'Go to Site' link in mail body",
						"User able to see 'Go to " + siteName + "' link successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the 'Go to Site' link in mail body",
						"User unable to see 'Go to " + siteName + "' link", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check mail subject and content for Archive pooled task
	public void verifyMailSubAndBodyForRevAppSubTask(String expectedResult) {
		try {

			ArrayList<String> archivePoolTaskDataList = gmailObj.checkArchivePooledTaskMailSubAndContent();
			boolean isDisplayedMailSubAndConetentAsExpected = false;

			for (String archivePoolTaskData : archivePoolTaskDataList) {
				if (archivePoolTaskData.contains(expectedResult)) {
					isDisplayedMailSubAndConetentAsExpected = true;
				} else {
					isDisplayedMailSubAndConetentAsExpected = false;
				}
			}

			if (isDisplayedMailSubAndConetentAsExpected) {
				report.updateTestLog("Verify the mail subject and body for 'Archive Pooled Task'",
						"User able to see 'Archive Pooled Task " + expectedResult
								+ "' successfully in mail subject and body",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the mail subject and body for 'Archive Pooled Task'",
						"User not able to see 'Archive Pooled Task " + expectedResult + "' in mail subject and body",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
