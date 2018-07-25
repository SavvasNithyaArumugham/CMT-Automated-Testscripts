package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoEditTaskPage extends ReusableLibrary {

	private String viewMoreActionsLinkXpath = ".//*[@class='view_more_actions']";

	private String editTaskTitleXpath = ".//*[@id='HEADER_TITLE']";

	private String editTaskButtonXpath = ".//*[@id='page_x002e_data-actions_x002e_task-details_x0023_default-edit-button']";
	private String taskDoneButtonXpath = ".//*[@id='page_x002e_data-form_x002e_task-edit_x0023_default_prop_transitions-Next-button']";
	private String tempFileNameXpath = ".//*[text()='CRAFT']";

	/*** Check Life cycle in Edit Prop ***/
	public String lifecycleNameXpath = ".//*[@name='prop_lm_lifecycleName']";
	public String lifecycleStateXpath = ".//*[@name='prop_lm_lifecycleState']";

	public String titleTxtAreaXpath = ".//textarea[contains(@id,'_default_prop_cm_content')]";
	public String saveButtonXpath = ".//button[text()='Save']";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String tempUpdatedMsgXapth = ".//div[contains(text(),'CRAFT')]";
	private String htmlTxtAreaXapth = ".//*[@id='tinymce']/p";

	private String viewMenuXapth = ".//button[@id='mceu_23-open']//span[text()='View']";
	private String sourceCodeOptionXapth = ".//span[text()='Source code']";
	private String sourceCodeLayoutXapth = ".//textarea[contains(@class,'mce-abs-layout-item')]";
	private String buttonOKXpath = ".//button[text()='Ok']";

	private String statusDropDownXpath = ".//select[contains(@id,'default_prop_pnwf_status')]";
	private String submitTaskButtonXpath = ".//button[text()='Submit Task']";
	private String rejectTaskButtonXpath = ".//button[text()='Reject']";

	public AlfrescoEditTaskPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	/**
	 * Method to select the ViewMoreActions Link
	 * 
	 * @author 412766
	 */
	public void selectViewMoreActionsLink() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, viewMoreActionsLinkXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, viewMoreActionsLinkXpath));
			UIHelper.highlightElement(driver, viewMoreActionsLinkXpath);
			UIHelper.click(driver, viewMoreActionsLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("To select view more action link", "Link selected successfully", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("To select view more action link", e.getMessage(), Status.FAIL);
		}
	}

	/**
	 * Method to click the Edit task button in Task Details page.
	 * 
	 * @author 412766
	 */
	public void selectEditTaskButton() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, editTaskButtonXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, editTaskButtonXpath));
			UIHelper.highlightElement(driver, editTaskButtonXpath);
			UIHelper.click(driver, editTaskButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("To click Edit Task option", "Edit Task clicked successfully", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("To click Edit Task option", "To click Edit Task option Failed", Status.FAIL);
		}
	}

	/**
	 * Method to click the Task Done button in Edit Task page.
	 * 
	 * @author 412766
	 */
	public void selectTaskDoneButton() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskDoneButtonXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, taskDoneButtonXpath));
			UIHelper.highlightElement(driver, taskDoneButtonXpath);
			UIHelper.click(driver, taskDoneButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("To click Task Done option", "Task Done successfully", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("To click Task Done option", "To click Task Done option Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileNameList
	 */
	public void verifyItemInEditTask(ArrayList<String> fileNameList) {
		boolean flag = false;
		try {
			for (String fileName : fileNameList) {
				String finalXpath = tempFileNameXpath.replace("CRAFT", fileName);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpath);
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalXpath))) {
					UIHelper.highlightElement(driver, finalXpath);
					flag = true;
				} else {
					flag = false;
					break;
				}
			}

			if (flag) {
				report.updateTestLog("Verify the linked Files available in Edit Task",
						"All Files verified successfully" + "<br /><b>Files verified :</b> " + fileNameList.toString(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify linked Files in Edit Task",
						"All Files not available" + "<br /><b>File verified :</b> " + fileNameList.toString(),
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify linked Files in Edit Task", "Verify linked Files in Edit Task Failed",
					Status.FAIL);
		}

	}

	/**
	 * @author 317085 Method to check whether the field is editable or not
	 */
	public void verifyEditable(String Xpath) {

		try {

			UIHelper.waitForPageToLoad(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, Xpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, Xpath));
				UIHelper.highlightElement(driver, Xpath);
				String value = UIHelper.findAnElementbyXpath(driver, Xpath).getAttribute("disabled");
				if (value.equalsIgnoreCase("true")) {
					report.updateTestLog("Verify Editable",
							"Verify element is editable successfully." + "<br><b> Field Value </b> :"
									+ UIHelper.findAnElementbyXpath(driver, Xpath).getAttribute("value"),
							Status.PASS);
				} else {
					report.updateTestLog("Verify Editable",
							"Verify element is editable failed." + "<br><b> Field Value </b> :"
									+ UIHelper.findAnElementbyXpath(driver, Xpath).getAttribute("value"),
							Status.FAIL);
				}

			} else {
				report.updateTestLog("Verify Editable", "Element not found", Status.FAIL);

			}
		} catch (Exception e) {
			report.updateTestLog("Verify Editable", "Element not found", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param value
	 */
	public void doEditInAlfresco(String value, String fileName, String fileType) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			boolean flag = false;
			if (fileType.equalsIgnoreCase("html")) {
				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, viewMenuXapth);
				} catch (Exception e) {

				}
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, viewMenuXapth))) {
					UIHelper.highlightElement(driver, viewMenuXapth);
					UIHelper.click(driver, viewMenuXapth);
					UIHelper.waitFor(driver);
					UIHelper.highlightElement(driver, sourceCodeOptionXapth);
					UIHelper.click(driver, sourceCodeOptionXapth);
					UIHelper.waitFor(driver);
					try {
						UIHelper.waitForVisibilityOfEleByXpath(driver, sourceCodeLayoutXapth);
					} catch (Exception e) {

					}
					UIHelper.highlightElement(driver, sourceCodeLayoutXapth);
					UIHelper.sendKeysToAnElementByXpath(driver, sourceCodeLayoutXapth, value);
					UIHelper.waitFor(driver);
					UIHelper.highlightElement(driver, buttonOKXpath);
					UIHelper.click(driver, buttonOKXpath);
					flag = true;
				} else {
					flag = false;
				}
			} else {
				UIHelper.waitForVisibilityOfEleByXpath(driver, titleTxtAreaXpath);
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, titleTxtAreaXpath))) {
					UIHelper.highlightElement(driver, titleTxtAreaXpath);
					UIHelper.sendKeysToAnElementByXpath(driver, titleTxtAreaXpath, value);
					flag = true;
				} else {
					flag = false;
				}
			}

			if (flag) {
				report.updateTestLog(
						"Check user is able to Edit file uisng 'Edit in Alfresco' option", "Edited successfully."
								+ "<br><b> File Edited </b> :" + fileName + "<br><b> Edited Value </b> :" + value,
						Status.PASS);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, saveButtonXpath);
				UIHelper.click(driver, saveButtonXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			} else {
				report.updateTestLog("Check user is able to Edit file uisng 'Edit in Alfresco' option",
						"Failed to Edit." + "<br><b> File Edited </b> :" + fileName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check user is able to Edit file uisng 'Edit in Alfresco' option",
					"Check user failed to edit file uisng 'Edit in Alfresco' option", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param toCheck
	 * @param fileName
	 */
	public void checkEditedValue(String toCheck, String fileName) {
		try {
			tempUpdatedMsgXapth = tempUpdatedMsgXapth.replace("CRAFT", toCheck);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, tempUpdatedMsgXapth);

			if (UIHelper.checkForAnElementbyXpath(driver, tempUpdatedMsgXapth)) {
				UIHelper.highlightElement(driver, tempUpdatedMsgXapth);
				report.updateTestLog("Verify the Edited file",
						"File Edited successfully." + "<br><b> File Verified </b> :" + fileName, Status.PASS);
			} else {
				report.updateTestLog("Verify the Edited file",
						"File Not Edited." + "<br><b> File Verified </b> :" + fileName, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify the Edited file", "Verify the Edited file Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param statusValues
	 * @return
	 */
	public boolean checkStatusDropDownValues(String[] statusValues) {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, statusDropDownXpath);
			ArrayList<String> getAllOptions = new ArrayList<String>();
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, statusDropDownXpath), getAllOptions);

			int index = 0;
			for (String actualValue : getAllOptions) {
				if (actualValue.trim().equalsIgnoreCase(statusValues[index])) {
					flag = true;
				} else {
					flag = false;
					break;
				}
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param defaultStatus
	 * @return
	 */
	public boolean checkDefaultStatusDropDownValue(String defaultStatus) {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, statusDropDownXpath);
			ArrayList<String> getAllOptions = new ArrayList<String>();
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, statusDropDownXpath), getAllOptions);
			if (getAllOptions.get(0).equalsIgnoreCase(defaultStatus)) {
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
	public void clickStatusDropDown() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, statusDropDownXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, statusDropDownXpath))) {
				UIHelper.click(driver, statusDropDownXpath);
				report.updateTestLog("Click Status Dropdown", "Clicked successfully.", Status.PASS);
			} else {
				report.updateTestLog("Click Status Dropdown", "Not able to Click", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param statusValue
	 */
	public void selectStatusDropDownValue(String statusValue) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, statusDropDownXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, statusDropDownXpath))) {
				UIHelper.selectDropdownList(driver, UIHelper.findAnElementbyXpath(driver, statusDropDownXpath),
						statusValue);
				report.updateTestLog("Select Status Dropdown value",
						"Value selected successfully." + "</br> <b> Status Selected : " + statusValue, Status.PASS);
			} else {
				report.updateTestLog("Select Status Dropdown value", "Not able to select value", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickSubmitTaskButton() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, submitTaskButtonXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, submitTaskButtonXpath))) {
				UIHelper.click(driver, submitTaskButtonXpath);
				report.updateTestLog("Click Submit Task Button", "Clicked successfully.", Status.PASS);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
			} else {
				report.updateTestLog("Click Submit Task Button", "Not able to Click", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickRejectTaskButton() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, rejectTaskButtonXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, rejectTaskButtonXpath))) {
				UIHelper.click(driver, rejectTaskButtonXpath);
				report.updateTestLog("Click Reject Task Button", "Clicked successfully.", Status.PASS);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
			} else {
				report.updateTestLog("Click Reject Task Button", "Not able to Click", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
