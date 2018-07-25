package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoMyTasksPage extends ReusableLibrary {

	private String listOfTaskFilesSegmentXpath = ".//*[@class='sticky-wrapper']//*[@id='alfresco-myworkflows']//*[@id='alf-content']";
	private String listOfTaskTitleXpath = ".//*[@class='sticky-wrapper']//*[@id='alfresco-myworkflows']//*[@id='alf-content']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td[2]//h3/a";
	private String editTaskTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String myTasksHeaderXpath = ".//*[@id='HEADER_TITLE']";

	private String taskMyTasksXpath = ".//*[@id='HEADER_MY_TASKS_text']/a";
	private String myTaskSectionXpath = ".//*[@id='alf-content']//table[contains(@id,'yuievtautoid')]";

	private String activeTaskNameXpath = "//*[@title='Edit Task']";
	private String editTaskPageheader = "//*[@id='HEADER_TITLE']";

	private String myTaskFileVersionXpath = ".//*[@id='document-version']";
	private String myTaskLastVersionXpath = ".//*[@id='template_x002e_document-versions_x002e_document-details_x0023_default-latestVersion']/div[1]/span";

	private ArrayList<String> activeTaskNameList = new ArrayList<String>();

	private String acceptBtnXpath = ".//*[contains(@id,'accept-button')]";

	private String dueDateXpathForTasks = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-tasks')]//table[contains(@id,'yuievtautoid')]//tbody[contains(@class,'yui-dt-data')]//tr//td[2]//h4[2]";

	private boolean isDisplayedDueDateForAllTasks;

	private String resultTaskList = ".//table/tbody[contains(@class,'data')]/tr/td[2]//h3/a";
	private String tempwftaskXpath = ".//table/tbody[contains(@class,'data')]/tr/td[2]//h3/a[text()='CRAFT']";
	public ArrayList<String> wfList = new ArrayList<String>();

	private String activeTaskHeaderXpath = ".//h2[contains(text(),'Active Tasks')]";
	private String completedTaskHeaderXpath = ".//h2[contains(text(),'Completed Tasks')]";
	private String loadingXpath = ".//*[contains(text(),'Loading...')]";
	private String completedMenuXpath = "//a[contains(text(),'Completed')]";
	private String overDueTaskHeaderXpath = ".//h2[contains(text(),'Overdue Tasks')]";
	private String HighPriorityTaskHeaderXpath = ".//h2[contains(text(),'High Priority Tasks')]";
	private String overDueMenuXpath = "//a[contains(text(),'Overdue')]";
	private String highPriorityMenuXpath = "//a[contains(text(),'High')]";

	private String wfReviewerXapth = ".//*[contains(text(),'Reviewers')]//following-sibling::div//button[text()='Select']";
	private String wfApproverXapth = ".//*[contains(text(),'Approvers')]//following-sibling::div//button[text()='Select']";
	private String wfAssignToXapth = ".//*[contains(text(),'Assignees')]//following-sibling::div//button[text()='Select']";
	private String wfReviewerSearchXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//*[@class='picker-search-input']";
	private String wfReviwerSearchbuttonXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//*[@class='search-button']//button";
	private String wfReviewerResultXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//table//td[2]//h3";
	private String wfReviewerAddXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//table//td[3]//a[@title='Add']";
	private String wfReviewerOKXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//*[@class='first-child']/button[text()='OK']";
	private String startWFbuttonXpath = ".//*[@class='first-child']/button[text()='Start Workflow']";
	private String wfReviewSearchXpath = ".//*[contains(@id,'assigneesCC-cntrl-picker-body')]//*[@class='picker-search-input']";
	private String wfReviwSearchbuttonXpath = ".//*[contains(@id,'assoc_cwf_assigneesCC-cntrl')]//*[@class='search-button']//button";
	private String wfReviewResultXpath = ".//*[contains(@id,'assoc_cwf_assigneesCC-cntrl')]//table//td[2]//h3";
	private String wfReviewAddXpath = ".//*[contains(@id,'assoc_cwf_assigneesCC-cntrl')]//table//td[3]//a";
	private String wfReviewOKXpath = ".//*[contains(@id,'assoc_cwf_assigneesCC-cntrl')]//*[@class='first-child']/button[text()='OK']";
	private String wfFolderXpath=".//*[contains(@id,'default_assoc_packageItems-cntrl-currentValueDisplay')]//tr//h3//a[contains(text(),'Craft')]";

	public AlfrescoMyTasksPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	/**
	 * @author 412766
	 * @return
	 */
	public AlfrescoEditTaskPage selectTaskFromActiveTasksList() {

		try {

			String taskName = dataTable.getData("Tasks", "TaskName");
			String taskLinkXPath = ".//*[contains(text(),'" + taskName + "')]";

			UIHelper.waitForVisibilityOfEleByXpath(driver, taskLinkXPath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					taskLinkXPath));
			UIHelper.highlightElement(driver, taskLinkXPath);
			UIHelper.click(driver, taskLinkXPath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Select Task from active Task page",
					"Task selected Successfully" + "<br /><b> Task Name : </b>"
							+ taskName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Task from active Task page",
					"Select Task from active Task page Failed", Status.FAIL);
		}

		return new AlfrescoEditTaskPage(scriptHelper);
	}

	/**
	 * @author 412766
	 * @param taskName
	 * @return
	 */
	public AlfrescoEditTaskPage selectTaskFromActiveTasksList(String taskName) {

		try {

			String taskLinkXPath = ".//*[contains(text(),'" + taskName + "')]";

			UIHelper.waitForVisibilityOfEleByXpath(driver, taskLinkXPath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					taskLinkXPath));
			UIHelper.highlightElement(driver, taskLinkXPath);
			UIHelper.click(driver, taskLinkXPath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Select Task from active Task page",
					"Task selected Successfully" + "<br /><b> Task Name : </b>"
							+ taskName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Task from active Task page",
					"Select Task from active Task page Failed", Status.FAIL);
		}

		return new AlfrescoEditTaskPage(scriptHelper);
	}

	/**
	 * @author 412766
	 */
	public void acceptTask() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, editTaskPageheader);
			UIHelper.highlightElement(driver, acceptBtnXpath);
			UIHelper.click(driver, acceptBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Accept Task", "Task Accepted Successfully",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Accept Task", "Accept Task Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method to verify the last version listed top of the version list
	 * 
	 * @author 412766
	 */
	public boolean isUploadedFileWithLastVersion() {

		boolean flag = true;
		try {

			WebElement myTaskFileVersionWebElement = UIHelper
					.findAnElementbyXpath(driver, myTaskFileVersionXpath);
			WebElement myTaskLastVersionWebElement = UIHelper
					.findAnElementbyXpath(driver, myTaskLastVersionXpath);

			if (myTaskFileVersionWebElement.getText().equals(
					myTaskLastVersionWebElement.getText())) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isActiveTaskAvailable() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					activeTaskHeaderXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, activeTaskHeaderXpath))) {
				UIHelper.highlightElement(driver, activeTaskHeaderXpath);
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

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isCompletedTaskAvailable() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					completedTaskHeaderXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, completedTaskHeaderXpath))) {
				UIHelper.highlightElement(driver, completedTaskHeaderXpath);
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

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isOverDueTaskAvailable() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					overDueTaskHeaderXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, overDueTaskHeaderXpath))) {
				UIHelper.highlightElement(driver, overDueTaskHeaderXpath);
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

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isHighPriorityTaskAvailable() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					HighPriorityTaskHeaderXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, HighPriorityTaskHeaderXpath))) {
				UIHelper.highlightElement(driver, HighPriorityTaskHeaderXpath);
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

	/**
	 * @author 412766
	 */
	public void selectCompletedTaskMenu() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, completedMenuXpath);
			UIHelper.highlightElement(driver, completedMenuXpath);
			UIHelper.click(driver, completedMenuXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void selectOverDueTaskMenu() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, overDueMenuXpath);
			UIHelper.highlightElement(driver, overDueMenuXpath);
			UIHelper.click(driver, overDueMenuXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void selectHighPriorityTaskMenu() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					highPriorityMenuXpath);
			UIHelper.highlightElement(driver, highPriorityMenuXpath);
			UIHelper.click(driver, highPriorityMenuXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select reviewer details for simply task
	public void selectApprover(String userName) {

		if (userName.contains(",")) {
			String userNames[] = userName.split(",");
			selectMultipleApprovers(userNames);
		} else {
			selectSingleApprover(userName);
		}

	}

	public void selectMultipleApprovers(String[] userNames) {
		try {
			for (String userName : userNames) {
				UIHelper.click(driver, wfApproverXapth);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						wfReviewerSearchXpath);
				driver.findElement(By.xpath(wfReviewerSearchXpath)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver,
						wfReviewerSearchXpath, userName);
				UIHelper.click(driver, wfReviwerSearchbuttonXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						wfReviewerResultXpath);
				UIHelper.waitFor(driver);
				if (UIHelper
						.findAnElementbyXpath(driver, wfReviewerResultXpath)
						.getText().contains(userName)) {
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							wfReviewerResultXpath);
					UIHelper.click(driver, wfReviewerAddXpath);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, wfReviewerOKXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);

					report.updateTestLog("Select Workflow Approver",
							"Username = " + userName, Status.PASS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectSingleApprover(String userName) {
		UIHelper.click(driver, wfApproverXapth);
		UIHelper.waitForVisibilityOfEleByXpath(driver, wfReviewerSearchXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, wfReviewerSearchXpath,
				userName);
		UIHelper.click(driver, wfReviwerSearchbuttonXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, wfReviewerResultXpath);
		UIHelper.waitFor(driver);
		if (UIHelper.findAnElementbyXpath(driver, wfReviewerResultXpath)
				.getText().contains(userName)) {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					wfReviewerAddXpath);
			UIHelper.click(driver, wfReviewerAddXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, wfReviewerOKXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Select Workflow Approver", "Username = "
					+ userName, Status.PASS);
		}
	}

	/**
	 * @author 412766
	 * @param userName
	 */
	public void selectSingleAssignee(String userName) {
		UIHelper.click(driver, wfAssignToXapth);
		UIHelper.waitForVisibilityOfEleByXpath(driver, wfReviewerSearchXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, wfReviewerSearchXpath,
				userName);
		UIHelper.click(driver, wfReviwerSearchbuttonXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, wfReviewerResultXpath);
		UIHelper.waitFor(driver);
		if (UIHelper.findAnElementbyXpath(driver, wfReviewerResultXpath)
				.getText().contains(userName)) {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					wfReviewerResultXpath);
			UIHelper.click(driver, wfReviewerAddXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, wfReviewerOKXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Select Workflow Assignee", "Username = "
					+ userName, Status.PASS);
		}
	}
	
	// Select reviewer details for simply task
	public void selectReviewer(String userName) {

		if (userName.contains(",")) {
			String userNames[] = userName.split(",");
			selectMultipleReviewer(userNames);
		} else {
			selectSingleReviewerUser(userName);
		}

	}

	public void selectMultipleReviewer(String[] userNames) {
		try {
			for (String userName : userNames) {
				UIHelper.click(driver, wfReviewerXapth);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						wfReviewSearchXpath);
				driver.findElement(By.xpath(wfReviewSearchXpath)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver,
						wfReviewSearchXpath, userName);
				UIHelper.click(driver, wfReviwSearchbuttonXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						wfReviewResultXpath);
				UIHelper.waitFor(driver);
				if (UIHelper.findAnElementbyXpath(driver, wfReviewResultXpath)
						.getText().contains(userName)) {
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							wfReviewResultXpath);
					UIHelper.click(driver, wfReviewAddXpath);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, wfReviewOKXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);

					report.updateTestLog("Select Workflow Reviewer",
							"Username = " + userName, Status.PASS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectSingleReviewerUser(String userName) {
		UIHelper.click(driver, wfReviewerXapth);
		UIHelper.waitForVisibilityOfEleByXpath(driver, wfReviewSearchXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, wfReviewSearchXpath,
				userName);
		UIHelper.click(driver, wfReviwSearchbuttonXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, wfReviewResultXpath);
		UIHelper.waitFor(driver);
		if (UIHelper.findAnElementbyXpath(driver, wfReviewResultXpath)
				.getText().contains(userName)) {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, wfReviewResultXpath);
			UIHelper.click(driver, wfReviewAddXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, wfReviewOKXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Select Workflow Reviewer", "Username = "
					+ userName, Status.PASS);
		}
	}
	public void clickOnWFFolderLink(String folderName)
	{
		try
		{
			String finalFolderLink=wfFolderXpath.replace("Craft",folderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFolderLink);
			UIHelper.highlightElement(driver, finalFolderLink);
			UIHelper.click(driver, finalFolderLink);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Click on 'Folder' in Workflow Assigned Items",
					"Click on 'Folder'in Workflow Assigned Items: " + folderName, Status.DONE);
		}
		catch(Exception e)
		
		{ 
			e.printStackTrace();
			report.updateTestLog("Click on 'Folder' in Workflow Assigned Items",
					"Failed to Click on 'Folder'in Workflow Assigned Items: " + folderName, Status.FAIL);
		}
	}

}
