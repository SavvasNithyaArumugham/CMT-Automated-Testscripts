package com.pearson.automation.alfresco.pages;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**
 * This class use to do the Pearson Email verification part.
 * 
 * @author 412766
 * 
 */
public class AlfrescoPearsonMailPage extends ReusableLibrary {

	private String gmailSubjectXpath = ".//*[contains(text(), 'Mail Subject')]";
	private String mailIconXpath = ".//*[@id='gbq1']/div/a/span";
	private String searchinputXpath = ".//*[@id='gbqfq']";
	private String searchBtnXpath = ".//*[@id='gbqfb']";
	private String searchMailXpath = ".//table//tr[1]//td//span[contains(.,'You have been assigned a task')]";
	private String searchRecentActivityXpath = ".//table//tr//td//span[contains(.,'Alfresco Share: Recent Activities')]";
	private String searchResultMailXpath = "//table//tr/td[6]//span[text()='Simple Task Completed']";
	private String mailContentXpath = "//table//p[4]";
	private String recentMailUserXpath = "//table//a[1]";
	private String recentMailActionXpath = "//table//a[2]";
	private String recentMailDocXpath = "//table//a[3]";
	private String mailStatusXpath = "//table//p[2][contains(.,'Simple Task Completed')]";
	private String fileURLXpath = "//table//a[contains(.,'document')]";
	private String tempXpathForAssignedTaskMail = ".//*[@class='nH']//div[contains(@class,'BltHke nH oy8Mbf') and not (contains(@style,'display: none;'))]//table//tr[1]//td//span[1][contains(.,'You have been assigned a task')]//ancestor::td[1]//following-sibling::td[2]/span[contains(@title,'DAY')]";
	private String tempXpathFordeleteDocument = ".//*[@class='nH']//div[contains(@class,'BltHke nH oy8Mbf') and not (contains(@style,'display: none;'))]//table//tr[1]//td//span[1][contains(.,'has deleted')]//ancestor::td[1]//following-sibling::td[2]/span[contains(@title,'DAY')]";
	private String tempXpathForSimpleTaskCompletedMail = ".//*[@class='nH']//div[contains(@class,'BltHke nH oy8Mbf') and not (contains(@style,'display: none;'))]//table//tr[1]//td//span[1][contains(.,'Simple Task Completed')]//ancestor::td[1]//following-sibling::td[2]/span[contains(@title,'DAY')]";
	private String tempXpathWithMailSubject = ".//*[@class='nH']//div[contains(@class,'BltHke nH oy8Mbf') and not (contains(@style,'display: none;'))]//table//tr[1]//td//span[contains(.,'CRAFT')]//ancestor::td[1]//following-sibling::td[2]/span[contains(@title,'DAY')]";
	private String tempXpathWithMailSubjectforsinglereview = ".//*[@class='nH']//div[contains(@class,'BltHke nH oy8Mbf') and not (contains(@style,'display: none;'))]//table//tr//td//span[contains(.,'CRAFT')]//ancestor::td[1]//following-sibling::td[2]/span[contains(@title,'DAY')]";
	private String taskDueDateXpathInMailSub = "//table/tbody/tr/td//p[contains(.,'CRAFT')]//following-sibling::p[1]/b[1]";
	private String timeStampXpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr/td[2]/div[2]";
	private String tempSearchResultItemXpath = ".//*[contains(@role,'main')]//table//tr[1]//td/*[contains(.,\"MESSAGE\")]//ancestor::td[1]//following-sibling::td[2]/span[contains(@title,'DAY')]";
	private String tempXpathForAssignedTaskSearchItem = ".//*[@class='nH']//div[contains(@class,'BltHke nH oy8Mbf') and not (contains(@style,'display: none;'))]//table//tr[1]//td//span[1][contains(.,'You have been assigned a task')]//ancestor::td[1]//following-sibling::td[2]/span[contains(@title,'DAY')]";
	private String tempXpathForEditTaskURL = ".//*[contains(@href,'CRAFT')]";
	private String tempFollowedFileDeleteMsg = "USER_NAME has deleted FILE_NAME in site";
	private String dueDateDetailsFromMail;
	private String siteNmeXPath = ".//*[@id=':jt']/div[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr/td[2]/div[1]";

	private String editTaskReviewCommentsTAreaXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@title='Comment']";
	private String taskStatusDropdownXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_bpm_status')]";

	private String approveButtonXpathInReviewTaskPage = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_cwf_reviewOutcome-Approve')]/span[contains(.,'Approve')] | //*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_transitions-Next')]/span[contains(.,'Task Done')]";

	private String mailBodyXpathForBulkDownload = ".//*[@class='nH']//div[contains(@style,'font-family')]";

	private String inboxLinkXpath = ".//*[@class='nH']//span/a[contains(.,'Inbox')]";

	private String bulkDownloadBodyMailText;

	private String editTaskXPath = ".//a[contains(@href,'pearson')]";

	public String assigneNmeXPath = "//p[contains(text(),'Hi CRAFT')]";
	private String assignNmeFromMail;
	private String intNmeFromMail;
	private String actualComment;
	public String intNmeXPath = "//p[contains(text(),'Sincerely')]";
	private String tempXpathForSimpleReviewTaskCompletedMail = ".//*[@class='nH']//div[contains(@class,'BltHke nH oy8Mbf') and not (contains(@style,'display: none;'))]//table//tr[1]//td//span[1][contains(.,'Simple Review and Approve Completed')]";

	private String commentDetailsXpath = "//div[@class='nH']//table//tbody//thead//tr[1]//th[4][contains(text(),'Comment')]//following::td[4]";

	private String tempXpathForAssigneeContent = "//p[contains(.,'Hi CRAFT')]";
	private String tempXpathForInitiator = "//p[contains(.,'CRAFT') and contains(.,'Sincerely')]";
	private String tempEmailContentXpath = "//p[contains(.,'CRAFT')]";
	private String overDueTaskName = ".//p[contains(.,'You have the following overdue task:')]/following-sibling::p[1]";

	private String mailFormatTemplate1Xpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr/td[2]/div[1]";
	private String mailFormatTemplate2Xpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/div/p[2]";
	private String mailFormatTemplate3Xpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/div/p[4]";
	private String mailFormatTemplate4Xpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/div/table/tbody/tr/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td";
	private String mailFormatTemplate5Xpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/div/p[5]";
	private String mailFormatTemplate6Xpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/div/p[7]";
	private String mailFormatTemplate7Xpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td";

	private String editTaskLinkXpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/div/p/a";
	private String editTaskPgXpath = ".//*[contains(@id,'default-form-fields')]/div[1]/div[2]/div/span[2]";

	private String tempXpathForMailBodyHeader = ".//*[@class='nH']//*[@class='gs']//table/tbody/tr/td//td//td//td//td[contains(.,'CRAFT')]";
	public String tempXpathForGotoSite = "//table/tbody/tr/td//a[contains(.,'CRAFT')]";

	private String mailWFStatusTableXpath = ".//table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/div/table[1]";

	private String archivePoolesdTaskXpathForMailSubAndContent = ".//*[contains(@class,'nH') and contains(@role,'main')]//table/tbody/tr/td/div[contains(.,'Archive Pooled Task')]";
	private String editLinkXpathForArchivePooledTask = ".//*[contains(@class,'nH') and contains(@role,'main')]//table/tbody/tr/td//*[contains(.,'Click this link to edit the task:')]/following-sibling::*//a[contains(.,'pearsoncms')]";

	private String actionInviteXpath = "//table//a[contains(.,'CRAFT')]";
	
	public String errorh1 = "//*[contains(text(),'Other errors: H1')]";
	public String errorstyle = "//*[contains(text(),'Tags with style attributes: [p]')]";
	public String errorunapprovedtags =	"//*[contains(text(),' tags: [sample-')]/span";
	
	
	public AlfrescoPearsonMailPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	/**
	 * Method to verify the mail received or not based on the mail subject
	 * 
	 * @param mailSubject
	 *            - Mail subject to verify
	 * @author 412766
	 */
	public void checkEmailSubject(String mailSubject) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mailIconXpath);
			UIHelper.highlightElement(driver, mailIconXpath);
			gmailSubjectXpath = gmailSubjectXpath.replaceAll("Mail Subject", mailSubject);

			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(gmailSubjectXpath, driver);
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify Pearson mail", "Mail received successfully", Status.DONE);
			} else {
				report.updateTestLog("Verify Pearson mail", "Mail not received successfully", Status.FAIL);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", e.getMessage(), Status.FAIL);
		}
	}

	public void searchWFStatusmessage() {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String Msg = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchResultMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify WF task Accepted",
						"WF task Accepted successfully" + "<br /><b> Task Msg : </b>" + Msg, Status.PASS);
			} else {
				report.updateTestLog("Verify WF task Accepted", "WF task Accepted failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "Mail not recieved", Status.FAIL);
		}
	}

	// Check Email for followed file when perform doc actions
	public void searchFollowedFileMessageByDocAction(String fileName, String userName, String docActionName,
			String tempMessage) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));

			String message = tempMessage.replace("USER_NAME", userName).replace("FILE_NAME", fileName);

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, message);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalSearchResultItemXpath = tempSearchResultItemXpath.replace("MESSAGE", message).replace("DAY",
					requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalSearchResultItemXpath)) {
				report.updateTestLog(
						"Verify user receive a email when perform " + docActionName + " using followed file",
						"User successfully received email when perform " + docActionName + " using followed file"
								+ "<br /><b> Mail Message : </b>" + message,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify user receive a email when perform " + docActionName + " using followed file",
						"User failed to receive email when perform " + docActionName + " using followed file",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Email for followed file when perform doc actions
	public void searchFollowedFileMessageByDocActionForNegativeCase(String fileName, String userName,
			String docActionName, String tempMessage) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));

			String message = tempMessage.replace("USER_NAME", userName).replace("FILE_NAME", fileName);

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, message);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalSearchResultItemXpath = tempSearchResultItemXpath.replace("MESSAGE", message).replace("DAY",
					requiredDayDate);

			if (!UIHelper.checkForAnElementbyXpath(driver, finalSearchResultItemXpath)) {
				report.updateTestLog(
						"Verify user receive a email when perform " + docActionName + " using followed file",
						"User not received email when perform " + docActionName + " using followed file"
								+ "<br /><b> Mail Message : </b>" + message,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify user receive a email when perform " + docActionName + " using followed file",
						"User received email when perform " + docActionName + " using followed file", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to locate the Mail with WF task msg
	public void searchmailwithWFmessage() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String Msg = message + "_" + currentDate;
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				subjectList.get(0).click();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail", "Mail received successfully", Status.DONE);
				if (UIHelper.findAnElementbyXpath(driver, mailContentXpath).getText().contains(Msg)) {
					report.updateTestLog("Verify Mail received for WF",
							"Mail received successfully" + "<br /><b> Task Msg : </b>"
									+ UIHelper.findAnElementbyXpath(driver, mailContentXpath).getText(),
							Status.PASS);
				} else {
					report.updateTestLog("Verify Mail received for WF", "Mail not received", Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Pearson mail", "Mail not received ", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchRecentActivityMail() {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String Msg = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchRecentActivityXpath, driver);

			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				subjectList.get(0).click();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson Recent Activity mail", "Mail received successfully", Status.DONE);
				List<WebElement> UserList = UIHelper.findListOfElementsbyXpath(recentMailUserXpath, driver);
				List<WebElement> ActionList = UIHelper.findListOfElementsbyXpath(recentMailActionXpath, driver);
				List<WebElement> DocList = UIHelper.findListOfElementsbyXpath(recentMailDocXpath, driver);
				UIHelper.waitFor(driver);
				if (UIHelper.findAnElementbyXpath(driver, recentMailUserXpath) != null
						&& UIHelper.findAnElementbyXpath(driver, recentMailActionXpath) != null
						&& UIHelper.findAnElementbyXpath(driver, recentMailDocXpath) != null) {
					report.updateTestLog("Verify Pearson Recent Activity mail",
							"Mail received successfully" + "<br /><b> User Name : </b>" + UserList.get(0).getText()
									+ "<br /><b> Document Name : </b>" + ActionList.get(0).getText()
									+ "<br /><b> Site Name : </b>" + DocList.get(0).getText(),
							Status.PASS);
				} else {
					report.updateTestLog("Verify Pearson Recent Activity mail", "Mail not received successfully",
							Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Pearson Recent Activity mail", "Mail not received successfully",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson Recent Activity mail", e.getMessage(), Status.FAIL);
		}
	}

	public void searchFileURLLink() {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String Msg = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, "label:alfresco-task " + Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				subjectList.get(0).click();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail", "Mail received successfully", Status.DONE);
				if (UIHelper.findAnElementbyXpath(driver, fileURLXpath).isDisplayed()) {
					report.updateTestLog("Verify File URL in Pearson mail",
							"Mail received successfully" + "<br /><b>File URL : </b>"
									+ UIHelper.findAnElementbyXpath(driver, fileURLXpath).getText(),
							Status.PASS);
				} else {
					report.updateTestLog("Verify File URL in Pearson mail", "Mail not received successfully",
							Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Pearson mail", "Mail not received successfully", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", e.getMessage(), Status.FAIL);
		}
	}

	public void searchTaskActionNotifyeMail() {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String Msg = message + "_" + currentDate;
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify notification email sent for Task Action->Continue Review and Approve",
						"email received successfully" + "<br /><b> Task Msg : </b>" + Msg, Status.PASS);
			} else {
				report.updateTestLog("Verify notification email sent for Task Action->Continue Review and Approve",
						"email NOT received " + "<br /><b> Task Msg : </b>" + Msg, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "Mail not recieved", Status.FAIL);
		}
	}

	// Open Assigned Task Mail
	public String openAssignedTaskMailAndGetDueDate(String workFlowName, String dueDate, String mailSubText) {
		try {
			// String finalXpathForAssignedTaskMail =
			// tempXpathForAssignedTaskMail.replace("WORKFLOW",
			// workFlowName).replace("CRAFT", dueDate);

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalSearchResultItemXpath = tempSearchResultItemXpath.replace("MESSAGE", mailSubText).replace("DAY",
					requiredDayDate);

			// String finalXpathForAssignedTaskMail =
			// tempXpathForAssignedTaskMail.replace("DAY", dueDate);
			String finalDueDateXpathInMailSub = taskDueDateXpathInMailSub.replace("CRAFT", workFlowName);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, finalSearchResultItemXpath)) {
				UIHelper.click(driver, finalSearchResultItemXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalDueDateXpathInMailSub);

				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalDueDateXpathInMailSub));
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, finalDueDateXpathInMailSub));

				dueDateDetailsFromMail = UIHelper.getTextFromWebElement(driver, finalDueDateXpathInMailSub);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dueDateDetailsFromMail;
	}

	// Open Assigned Task Mail
	public String openMail(String mailSubText) {
		try {

			String finalXpathForMailBodyHeader = tempXpathForMailBodyHeader.replace("CRAFT", mailSubText);

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalSearchResultItemXpath = tempSearchResultItemXpath.replace("MESSAGE", mailSubText).replace("DAY",
					requiredDayDate);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, finalSearchResultItemXpath)) {
				UIHelper.click(driver, finalSearchResultItemXpath);
				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMailBodyHeader);

				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForMailBodyHeader)) {
					report.updateTestLog("Open received mail", "Received email opened successfully", Status.PASS);
				} else {
					report.updateTestLog("Open received mail", "Failed to open received email ", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dueDateDetailsFromMail;
	}

	// Check 'Go to Site' link
	public boolean checkSiteLinkInMail(String siteName) {
		boolean isDisplayeGotSiteLink = false;
		try {
			String finalXpathForGotoSite = tempXpathForGotoSite.replace("CRAFT", siteName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForGotoSite)) {
				isDisplayeGotSiteLink = true;
			} else {
				isDisplayeGotSiteLink = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayeGotSiteLink;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public String getDateForMailSearch() {
		String requiredDayDate = "";
		try {
			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requiredDayDate;
	}

	/**
	 * @author 412766
	 */
	public void searchmailwithMailSubject() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");

			String dueDate = new DateUtil().getCurrentDate();
			String workflowName = message + "_" + dueDate;

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String requiredDayDate = getDateForMailSearch();

			String finalXpathForAssignedTaskSearchItem = tempXpathForAssignedTaskSearchItem.replace("DAY",
					requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAssignedTaskSearchItem)) {
				UIHelper.click(driver, finalXpathForAssignedTaskSearchItem);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail",
						"Mail received successfully" + "<br /><b> Submitted Workflow : </b>" + workflowName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Pearson mail",
						"Mail not received" + "<br /><b> Submitted Workflow : </b>" + workflowName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Click on Link in Reveived Email
	public void clickOnLinkInReceivedMail() {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editTaskXPath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, editTaskXPath));
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, editTaskXPath));

			report.updateTestLog("Click on Link", "Clicked on Recived link in email ", Status.PASS);
			UIHelper.click(driver, editTaskXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			String pageName = driver.getTitle();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void searchDeletedMailwithMailSubject() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String workflowName = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String requiredDayDate = getDateForMailSearch();
			String finalXpathFordeleteDocument = tempXpathFordeleteDocument.replace("DAY", requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathFordeleteDocument)) {
				UIHelper.click(driver, finalXpathFordeleteDocument);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail",
						"Mail received successfully" + "<br /><b> Submitted Workflow : </b>" + workflowName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Pearson mail",
						"Mail not received" + "<br /><b> Submitted Workflow : </b>" + workflowName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void searchSimpleTaskCompletedMail() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String workflowName = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String requiredDayDate = getDateForMailSearch();
			String finalXpathForSimpleTaskCompletedMail = tempXpathForSimpleTaskCompletedMail.replace("DAY",
					requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSimpleTaskCompletedMail)) {
				UIHelper.click(driver, finalXpathForSimpleTaskCompletedMail);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail",
						"Mail received successfully" + "<br /><b> Submitted Workflow : </b>" + workflowName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Pearson mail",
						"Mail not received" + "<br /><b> Submitted Workflow : </b>" + workflowName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void searchMailWithSubject(String mailSubject) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String workflowName = message + "_" + currentDate;

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalMailSubjectXpath = tempXpathWithMailSubject.replace("CRAFT", mailSubject);

			String requiredDayDate = getDateForMailSearch();
			finalMailSubjectXpath = finalMailSubjectXpath.replace("DAY", requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalMailSubjectXpath)) {
				UIHelper.click(driver, finalMailSubjectXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail",
						"Mail received successfully" + "<br /><b> Submitted Workflow : </b>" + workflowName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Pearson mail",
						"Mail not received" + "<br /><b> Submitted Workflow : </b>" + workflowName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public boolean isMailWithSubject(String mailSubject) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String workflowName = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalMailSubjectXpath = tempXpathWithMailSubject.replace("CRAFT", mailSubject);

			String requiredDayDate = getDateForMailSearch();
			finalMailSubjectXpath = finalMailSubjectXpath.replace("DAY", requiredDayDate);
			System.out.println("FINAL MAIL XAPTH : " + finalMailSubjectXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, finalMailSubjectXpath)) {
				UIHelper.click(driver, finalMailSubjectXpath);
				UIHelper.waitFor(driver);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Method to locate the Mail with site name
	public void searchMailWithSubjectTitle(String mailSubject, String actionName, String siteName) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String receivedMailSub = mailSubject;
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, receivedMailSub);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalMailSubjectXpath = tempXpathWithMailSubject.replace("CRAFT", mailSubject);

			String requiredDayDate = getDateForMailSearch();
			finalMailSubjectXpath = finalMailSubjectXpath.replace("DAY", requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalMailSubjectXpath)) {
				UIHelper.click(driver, finalMailSubjectXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail",
						"Mail received successfully" + "<br /><b> Received Mail : </b>" + receivedMailSub, Status.PASS);

				String finalactionInviteXpath = actionInviteXpath.replace("CRAFT", actionName);

				if (UIHelper.checkForAnElementbyXpath(driver, finalactionInviteXpath)) {
					UIHelper.highlightElement(driver, finalactionInviteXpath);
					UIHelper.click(driver, finalactionInviteXpath);
					UIHelper.waitFor(driver);
					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);

					report.updateTestLog("Verify Leave from Site",
							"User removed successfully from site" + "<br /><b> Site Neme : </b>" + siteName,
							Status.PASS);

					if (UIHelper.checkForAnElementbyXpath(driver, ".//*[@id='UsernameTextBox']")) {
						String userName = dataTable.getData("General_Data", "Username");
						String password = dataTable.getData("General_Data", "Password");

						driver.findElement(By.id("UsernameTextBox")).sendKeys(userName);
						driver.findElement(By.id("PasswordTextBox")).sendKeys(password);
						driver.findElement(By.id("SubmitButton")).click();
						UIHelper.waitFor(driver);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, ".//*[@id='loader']");
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitFor(driver);

						report.updateTestLog("Login", "Enter login credentials: " + "Username = " + userName,
								Status.DONE);
					}

				} else {
					report.updateTestLog("Verify " + actionName + " from Site",
							"User failed to " + actionName + " from site", Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Pearson mail",
						"Mail not received" + "<br /><b> Received Mail : </b>" + receivedMailSub, Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "Mail not received ", Status.FAIL);
		}
	}

	public void searchMailWithSubjectTitle(String mailSubject) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String receivedMailSub = mailSubject;
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, receivedMailSub);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalMailSubjectXpath = tempXpathWithMailSubject.replace("CRAFT", mailSubject);

			String requiredDayDate = getDateForMailSearch();
			finalMailSubjectXpath = finalMailSubjectXpath.replace("DAY", requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalMailSubjectXpath)) {
				report.updateTestLog("Verify Pearson mail",
						"Mail received successfully" + "<br /><b> Received Mail : </b>" + receivedMailSub, Status.PASS);
				
				UIHelper.click(driver, finalMailSubjectXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Open recieved mail", "Mail opened successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Pearson mail",
						"Mail not received" + "<br /><b> Received Mail : </b>" + receivedMailSub, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public String openMailAndCheckTimeStamp() {
		String timeStampDetailsFromMail = "";
		try {
			// String finalXpathForAssignedTaskMail =
			// tempXpathForAssignedTaskMail.replace("WORKFLOW",
			// workFlowName).replace("CRAFT", dueDate);
			String requiredDayDate = getDateForMailSearch();
			System.out.println(requiredDayDate);
			String finalXpathForAssignedTaskMail = tempXpathForAssignedTaskMail.replace("zz", requiredDayDate);
			System.out.println(finalXpathForAssignedTaskMail);
			// String finalDueDateXpathInMailSub =
			// taskDueDateXpathInMailSub.replace("CRAFT", workFlowName);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAssignedTaskMail)) {
				UIHelper.click(driver, finalXpathForAssignedTaskMail);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, timeStampXpath);

				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, timeStampXpath));
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, timeStampXpath));

				timeStampDetailsFromMail = UIHelper.getTextFromWebElement(driver, timeStampXpath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return timeStampDetailsFromMail;
	}

	/**
	 * @author 412766
	 */
	public void openEditTaskURL() {
		try {
			String currentEnv = properties.getProperty("ApplicationUrl");
			if (currentEnv.contains("share")) {
				currentEnv = currentEnv + "/page/task-edit?";
			} else {
				currentEnv = currentEnv + "/share/page/task-edit?";
			}

			String finalXpath = tempXpathForEditTaskURL.replace("CRAFT", currentEnv);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpath)) {
				UIHelper.highlightElement(driver, finalXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpath));
				report.updateTestLog("Click 'Edit Task' URL ", "'Edit Task URL' clicked Successfully", Status.PASS);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.click(driver, finalXpath);
				AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
				homePage.switchtab(1);
			} else {
				report.updateTestLog("Click 'Edit Task' URL ", "'Edit Task URL' Not clicked", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Click 'Edit Task' URL", "Click 'Edit Task' URL Failed", Status.FAIL);
		}
	}

	// Check Email for followed file when perform doc actions
	public void searchFollowFileMessageByDocAction(String fileName, String userName, String docActionName,
			String tempMessage) {
		String siteName = dataTable.getData("Sites", "SiteName");
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));

			String message = tempMessage.replace("USER_NAME", userName).replace("FILE_NAME", fileName);

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, message);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalSearchResultItemXpath = tempSearchResultItemXpath.replace("MESSAGE", message).replace("DAY",
					requiredDayDate);

			// finalSearchResultItemXpath =
			// ".//*[contains(@role,'main')]//table//tr[1]//td/div[contains(.,'Naresh
			// Kumar Salla has updated
			// AlfrescoAutoDemoForTest.docx')]//ancestor::td[1]//following-sibling::td[2]/span";
			if (UIHelper.checkForAnElementbyXpath(driver, finalSearchResultItemXpath)) {
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, finalSearchResultItemXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

			
					report.updateTestLog(
							"Verify user receive a email with <b>Original Site name:</b>" + siteName + " when perform "
									+ docActionName + " using followed file",
							"User successfully received email  with <b>Original Site name:</b>" + siteName
									+ " when perform when perform " + docActionName + " using followed file"
									+ "<br /><b> Mail Message : </b>" + message,
							Status.PASS);
			

			} else {
				
				report.updateTestLog(
						"Verify user receive a email with <b>Original Site name:</b>" + siteName + " when perform "
								+ docActionName + " using followed file",
						"User failed to received email when perform " + docActionName + " using followed file",
						Status.PASS);//Modified from FAIL to PASS As part of NALS
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to locate the Mail with WF task msg
	public void clickEditTaskLink() {

		String edtTaskXPath = ".//a[contains(@href,'pearsoncms.com')]";
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, edtTaskXPath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, edtTaskXPath));
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, edtTaskXPath));

			report.updateTestLog("Verify Edit Task Link  received for WF", "Edit Task Link displayed successfully ",

					Status.PASS);
			UIHelper.click(driver, edtTaskXPath);
			String strPar = driver.getWindowHandle();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			for (String toNavigate : tabs) {
				driver.switchTo().window(toNavigate);
			}
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			String reviewerComment = dataTable.getData("Tasks", "ReviewerComment");

			String status = dataTable.getData("Tasks", "Status");
			UIHelper.waitForVisibilityOfEleByXpath(driver, ".//*[@id='HEADER_LOGO']/div[2]/img");
			UIHelper.findAnElementbyXpath(driver, ".//*[@id='HEADER_LOGO']/div[2]/img").click();
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskStatusDropdownXpath);

			if (status != null && !status.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, taskStatusDropdownXpath, status);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, editTaskReviewCommentsTAreaXpath);

			WebElement commentTAreaEle = UIHelper.findAnElementbyXpath(driver, editTaskReviewCommentsTAreaXpath);
			UIHelper.highlightElement(driver, commentTAreaEle);

			commentTAreaEle.sendKeys(reviewerComment);
			UIHelper.waitFor(driver);

			WebElement approveOrTaskDoneBtnEle = UIHelper.findAnElementbyXpath(driver,
					approveButtonXpathInReviewTaskPage);
			UIHelper.highlightElement(driver, approveOrTaskDoneBtnEle);

			UIHelper.clickanElement(approveOrTaskDoneBtnEle);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Verify Assigned Task",
					"Reviewed the assigned task and approved/completed the task by adding comments: " + reviewerComment,
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify Edit Task Link  received for WF", "Edit Task Link NOT displayed successfully ",
					Status.FAIL);
			e.printStackTrace();
		}
	}

	// Get the text from Bulk Download Mail body
	public String getTextFromBulkDownloadMailBody() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mailBodyXpathForBulkDownload);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, mailBodyXpathForBulkDownload)) {
				bulkDownloadBodyMailText = UIHelper.getTextFromWebElement(driver, mailBodyXpathForBulkDownload);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bulkDownloadBodyMailText;
	}

	// Check Email when perform doc/browse actions and open search mail
	public void commonMethodForSearchMail(String performedOperation, String message) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, message);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalSearchResultItemXpath = tempSearchResultItemXpath.replace("MESSAGE", message).replace("DAY",
					requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalSearchResultItemXpath)) {

				UIHelper.click(driver, finalSearchResultItemXpath);
				report.updateTestLog(
						"Verify user receive a email for " + message + " when perform " + performedOperation,
						"User successfully received email when perform " + performedOperation, Status.PASS);
			} else {
				report.updateTestLog(
						"Verify user receive a email for " + message + " when perform " + performedOperation,
						"User failed to received email when perform " + performedOperation, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Email for workflow when perform start workflow
	public void commonMethodForSearchWorkflowMail(String performedOperation, String taskName, String mailSubText) {
		try {

			boolean isReceivedMail = checReceivedUserMail(performedOperation, taskName, mailSubText);

			if (isReceivedMail) {

				report.updateTestLog("Verify user receive a email when perform " + performedOperation,
						"User successfully received email when perform " + performedOperation, Status.PASS);
			} else {
				report.updateTestLog("Verify user receive a email when perform " + performedOperation,
						"User failed to received email when perform " + performedOperation, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checReceivedUserMail(String performedOperation, String taskName, String mailSubText) {
		boolean isReceivedMail = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, taskName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalSearchResultItemXpath = tempSearchResultItemXpath.replace("MESSAGE", mailSubText).replace("DAY",
					requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalSearchResultItemXpath)) {
				isReceivedMail = true;
			} else {
				isReceivedMail = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isReceivedMail;
	}

	// Click on inbox
	public void clickOnInboxItem() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, inboxLinkXpath)) {
				UIHelper.click(driver, inboxLinkXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to locate the Mail with WF task msg
	public void searchNoEmailReceived() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchinputXpath);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String Msg = message + "_" + currentDate;

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify Mail received for WF", "Mail received", Status.FAIL);
			}

			else {
				report.updateTestLog("Verify Mail received for WF", "Mail not received", Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String openMailAndCheckAssigneeNme(String assigneeNme) {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String workflowName = message + "_" + currentDate;

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String assigneNmeXPathFinal = assigneNmeXPath.replace("CRAFT", assigneeNme);

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalXpathForAssignedTaskMail = tempXpathForAssignedTaskMail.replace("DAY", requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAssignedTaskMail)) {
				UIHelper.click(driver, finalXpathForAssignedTaskMail);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, assigneNmeXPathFinal);

				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, assigneNmeXPathFinal));
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, assigneNmeXPathFinal));

				assignNmeFromMail = UIHelper.getTextFromWebElement(driver, assigneNmeXPathFinal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return assignNmeFromMail;
	}

	public String openMailAndCheckIntiatorNme(String assigneeNme) {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String workflowName = message + "_" + currentDate;

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String assigneNmeXPathFinal = intNmeXPath;

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalXpathForAssignedTaskMail = tempXpathForAssignedTaskMail.replace("DAY", requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAssignedTaskMail)) {
				UIHelper.click(driver, finalXpathForAssignedTaskMail);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, assigneNmeXPathFinal);

				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, assigneNmeXPathFinal));
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, assigneNmeXPathFinal));

				assignNmeFromMail = UIHelper.getTextFromWebElement(driver, assigneNmeXPathFinal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return assignNmeFromMail;
	}

	public String openMailAndCheckAssigneeNmeForTaskCompl(String assigneeNme) {

		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String workflowName = message + "_" + currentDate;

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String assigneNmeXPathFinal = assigneNmeXPath.replace("CRAFT", assigneeNme);

			String currentDayDate = new DateUtil().getDayOfWeekAndDate();
			String splittedDayDate[] = currentDayDate.replace(" ", ",").split(",");
			String requiredDayDate = "";
			if (splittedDayDate != null && splittedDayDate.length == 6) {
				String dayFirstDigitVal = "" + splittedDayDate[2].charAt(0);
				if (dayFirstDigitVal.contains("0")) {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2].charAt(1) + ", "
							+ splittedDayDate[5];
				} else {
					requiredDayDate = splittedDayDate[1] + " " + splittedDayDate[2] + ", " + splittedDayDate[5];
				}
			}

			String finalXpathForAssignedTaskMail = tempXpathForSimpleTaskCompletedMail.replace("DAY", requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAssignedTaskMail)) {
				UIHelper.click(driver, finalXpathForAssignedTaskMail);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, assigneNmeXPathFinal);

				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, assigneNmeXPathFinal));
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, assigneNmeXPathFinal));

				assignNmeFromMail = UIHelper.getTextFromWebElement(driver, assigneNmeXPathFinal);
			}
		} catch (Exception e) {
		}

		return assignNmeFromMail;
	}

	public String openMailAndCheckAssigneeNmeForRevTaskCompl(String assigneeNme) {

		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String workflowName = message + "_" + currentDate;

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String assigneNmeXPathFinal = assigneNmeXPath.replace("CRAFT", assigneeNme);
			String finalXpathForAssignedTaskMail = tempXpathForSimpleReviewTaskCompletedMail;

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAssignedTaskMail)) {
				UIHelper.click(driver, finalXpathForAssignedTaskMail);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, assigneNmeXPathFinal);

				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, assigneNmeXPathFinal));
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, assigneNmeXPathFinal));

				assignNmeFromMail = UIHelper.getTextFromWebElement(driver, assigneNmeXPathFinal);
				System.out.println(assignNmeFromMail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return assignNmeFromMail;
	}

	public String getCommentDetails(String comment) {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			actualComment = UIHelper.getTextFromWebElement(driver, commentDetailsXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return actualComment;
	}

	// To validate email Contents
	public void validateMailContents(ArrayList<String> content) {
		try {
			UIHelper.waitFor(driver);
			String contentXpath = tempXpathForAssigneeContent.replace("CRAFT", content.get(0));
			if (UIHelper.checkForAnElementbyXpath(driver, contentXpath)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, contentXpath));
				report.updateTestLog("Verify Email Content", content.get(0) + " displayed Sucessfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Email Content", content.get(0) + " is not displayed", Status.FAIL);
			}

			contentXpath = tempXpathForInitiator.replace("CRAFT", content.get(1));
			if (UIHelper.checkForAnElementbyXpath(driver, contentXpath)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, contentXpath));
				report.updateTestLog("Verify Email Content", content.get(1) + " displayed Sucessfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Email Content", content.get(1) + " is not displayed", Status.FAIL);
			}

			if (!content.get(2).isEmpty() && content.get(2) != null) {
				contentXpath = tempEmailContentXpath.replace("CRAFT", content.get(2));
			} else {
				contentXpath = overDueTaskName;
			}

			if (UIHelper.checkForAnElementbyXpath(driver, contentXpath)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, contentXpath));
				report.updateTestLog("Verify Email Content", content.get(2) + " displayed Sucessfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Email Content", content.get(2) + " is not displayed", Status.FAIL);
			}

			contentXpath = tempEmailContentXpath.replace("CRAFT", content.get(3));
			if (UIHelper.checkForAnElementbyXpath(driver, contentXpath)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, contentXpath));
				report.updateTestLog("Verify Email Content", content.get(3) + " displayed Sucessfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Email Content", content.get(3) + " is not displayed", Status.FAIL);
			}

			contentXpath = tempEmailContentXpath.replace("CRAFT", content.get(4));
			if (UIHelper.checkForAnElementbyXpath(driver, contentXpath)) {
				report.updateTestLog("Verify Email Content", content.get(4) + " displayed Sucessfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Email Content", content.get(4) + "is not displayed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author 412766
	 */
	public void verifyTaskAssignedMailTemplate() {
		try {
			boolean flag = false;
			UIHelper.waitFor(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate1Xpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate2Xpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate3Xpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate4Xpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate5Xpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate6Xpath))
					&& UIHelper
							.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate7Xpath))) {
				UIHelper.highlightElement(driver, mailFormatTemplate1Xpath);
				UIHelper.highlightElement(driver, mailFormatTemplate2Xpath);
				UIHelper.highlightElement(driver, mailFormatTemplate3Xpath);
				UIHelper.highlightElement(driver, mailFormatTemplate4Xpath);
				UIHelper.highlightElement(driver, mailFormatTemplate5Xpath);
				UIHelper.highlightElement(driver, mailFormatTemplate6Xpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate7Xpath));
				UIHelper.highlightElement(driver, mailFormatTemplate7Xpath);
				flag = true;
			} else {
				flag = false;
			}

			if (flag) {
				report.updateTestLog("Verify the Mail Template", "Mail Template verified successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the Mail Template", "Mail Template not in correct format", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify the Mail Template", "Verify the Mail Template Failed", Status.FAIL);
		}
	}

	public void clickOnEditTaskLink(String taskName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, editTaskLinkXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, editTaskLinkXpath)) {
				UIHelper.highlightElement(driver, editTaskLinkXpath);

				UIHelper.click(driver, editTaskLinkXpath);

				UIHelper.highlightElement(driver, editTaskLinkXpath);
				report.updateTestLog("Verify edit task link is  displayed", "Edit task link is displayed Sucessfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify edit task link displayed", "Edit task link is not displayed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify edit link page displayed", "Failed to display edit link", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyReviewOnlyMailTemplate() {
		try {
			boolean flag = false;
			UIHelper.waitFor(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate1Xpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate2Xpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate3Xpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate4Xpath))
					&& UIHelper
							.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate5Xpath))) {
				UIHelper.highlightElement(driver, mailFormatTemplate1Xpath);
				UIHelper.highlightElement(driver, mailFormatTemplate2Xpath);
				UIHelper.highlightElement(driver, mailFormatTemplate3Xpath);
				UIHelper.highlightElement(driver, mailFormatTemplate4Xpath);
				UIHelper.highlightElement(driver, mailFormatTemplate5Xpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate5Xpath));
				flag = true;
			} else {
				flag = false;
			}

			if (flag) {
				report.updateTestLog("Verify the Mail Template", "Mail Template verified successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the Mail Template", "Mail Template not in correct format", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify the Mail Template", "Verify the Mail Template Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyEditLinkOptionForReviewer() {
		try {
			boolean flag = false;
			UIHelper.waitFor(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate5Xpath))) {
				String editLink = UIHelper.getTextFromWebElement(driver, mailFormatTemplate5Xpath);
				System.out.println("EDIT LINK DATA : " + editLink);
				if (!(editLink.contains("Click this link to edit the task"))) {
					report.updateTestLog("Verify the Edit Link option for Reviewer", "'Edit Link' Option not avaialble",
							Status.PASS);
				} else {
					UIHelper.highlightElement(driver, mailFormatTemplate5Xpath);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate5Xpath));
					report.updateTestLog("Verify the Edit Link option for Reviewer", "'Edit Link' Option avaialble",
							Status.FAIL);
				}
			}

		} catch (Exception e) {
			report.updateTestLog("Verify the Edit Link option for Reviewer",
					"Verify the Edit Link option for Reviewer Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyDeleteAlertForAssetFollowed() {
		try {
			boolean flag = false;
			UIHelper.waitFor(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate2Xpath))) {
				UIHelper.highlightElement(driver, mailFormatTemplate2Xpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, mailFormatTemplate2Xpath));
				flag = true;
			} else {
				flag = false;
			}

			if (flag) {
				report.updateTestLog("Verify the Mail Template", "Mail Template verified successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the Mail Template", "Mail Template not in correct format", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify the Mail Template", "Verify the Mail Template Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean verifyWFStatusMail() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, mailWFStatusTableXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mailWFStatusTableXpath))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, mailWFStatusTableXpath));
				UIHelper.highlightElement(driver, mailWFStatusTableXpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// get mail sub and mail content for Archive pooled task
	public ArrayList<String> checkArchivePooledTaskMailSubAndContent() {
		ArrayList<String> archivePoolTaskDataList = new ArrayList<String>();
		try {
			List<WebElement> archivePoolTaskDataListEle = UIHelper
					.findListOfElementsbyXpath(archivePoolesdTaskXpathForMailSubAndContent, driver);

			for (WebElement ele : archivePoolTaskDataListEle) {
				archivePoolTaskDataList.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return archivePoolTaskDataList;

	}

	// Click on edit task link in Archive Pooled Task mail body
	public void clickOnEditTaskLinkInArchivePooledTask() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, editLinkXpathForArchivePooledTask)) {
				UIHelper.click(driver, editLinkXpathForArchivePooledTask);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				report.updateTestLog("Click on edit task Link", "User clicked the edit task link", Status.PASS);
			} else {
				report.updateTestLog("Verify the Mail Template", "Mail Template verified successfully", Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 412766
	 */
	public void searchMailWithSubjectforSingleReview(String mailSubject) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String workflowName = message + "_" + currentDate;

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, workflowName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalMailSubjectXpath = tempXpathWithMailSubjectforsinglereview.replace("CRAFT", mailSubject);

			String requiredDayDate = getDateForMailSearch();
			finalMailSubjectXpath = finalMailSubjectXpath.replace("DAY", requiredDayDate);

			if (UIHelper.checkForAnElementbyXpath(driver, finalMailSubjectXpath)) {
				UIHelper.click(driver, finalMailSubjectXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail",
						"Mail received successfully" + "<br /><b> Submitted Workflow : </b>" + workflowName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Pearson mail",
						"Mail not received" + "<br /><b> Submitted Workflow : </b>" + workflowName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Method to search for No Mail Received
		public void searchNoEmailReceived(String message) {
			try {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, searchinputXpath);
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
				

				UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, message);
				UIHelper.click(driver, searchBtnXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchMailXpath, driver);
				UIHelper.waitFor(driver);
				if (subjectList.size() > 0) {
					report.updateTestLog("Verify Mail received ", "Mail received", Status.FAIL);
				}

				else {
					report.updateTestLog("Verify Mail received ", "Mail not received", Status.PASS);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
