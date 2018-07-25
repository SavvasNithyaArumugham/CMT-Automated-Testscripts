package com.pearson.automation.alfresco.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Status;

/**
 * Functional Components class
 * 
 * @author Cognizant
 */
public class AlfrescoWorkflowPage extends ReusableLibrary {
	private String tempassignWorkflowXpath = ".//*[@id='onActionAssignWorkflow']/a";
	private String tempDocOptionsXpath = ".//*[contains(@id,'default-heading') and contains(text(),'Document Actions')]";
	private String tempDocListXpath = ".//*[@class='doclist']";
	private String workflowDropdownListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default-workflow-definition-button-button')]";
	private String tempWorkFlowDropDownValXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default-workflow-definition-menu')]//*[@class='bd']/ul/li[contains(.,'CRAFT')]";
	private String tempWFDropDownValXpath = ".//*[contains(@id,'default-workflow-definition-menu')]//li//span[1][contains(text(),'CRAFT')]";
	private String workflowDropDownXpath = ".//*[@class='first-child']/button";
	private String wfTextareXpath = ".//*[@name='prop_bpm_workflowDescription']";
	private String wfDueDateXpath = ".//*[@class='date-entry']";
	private String wfPriority = ".//*[@name='prop_bpm_workflowPriority']";
	private String simpleTaskXpath = ".//*[@class='first-of-type']/li[2]";
	private String wfReviewerXapth = ".//*[@class='first-child']/button[text()='Select']";
	private String wfReviewerSearchXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//input[@class='picker-search-input']";
	//private String wfReviewerSearchXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//*[@class='search-input']";
	private String wfReviwerSearchbuttonXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//*[@class='search-button']//button";
	private String wfReviewerResultXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//table//td[2]//h3";
	private String wfReviewerAddXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//table//td[3]//a";
	private String wfReviewerOKXpath = ".//*[contains(@id,'assoc_bpm_assignees')]//*[@class='first-child']/button[text()='OK']";
	private String startWFbuttonXpath = ".//*[@class='first-child']/button[text()='Start Workflow']";

	private String pageTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String workflowCommentsXpath = ".//*[contains(@class,'task-comment')]";
	private String enteredComments;
	private String actionsIconXpath = ".//*[@class='task-edit']";
	private String taskMenuXpath = ".//*[@id='HEADER_TASKS_text']";
	private String taskMyTasksXpath = ".//*[@id='HEADER_MY_TASKS_text']/a";

	private String resultTaskList = ".//table/tbody[contains(@class,'data')]/tr/td[2]//h3/a";
	private String tempwftaskXpath = ".//table/tbody[contains(@class,'data')]/tr/td[2]//h3/a[text()='CRAFT']";
	private String tempattachmentXpath = ".//*[@class='yui-dt-data']//h3//a[text()='CRAFT']//ancestor::tr//*[@class='attached']/span";

	private String alfrescoLogoXpath = ".//*[@id='HEADER_LOGO']//img";
	private String workflowDetailsXpath = ".//*[@class='links']/*[contains(text(), 'Workflow Details')]";
	private String taskDetailsXpath = ".//*[@class='links']/*[contains(text(), 'Task Details')]";
	private String detailsHeaderXpath = ".//*[@class='share-form']//h1[contains(.,'Details:')]";
	private String workflowSummaryHeaderXpath = ".//*[contains(@id,'default-summary-form-section')]/h3[contains(.,'Workflow Summary')]";
	private String tempXpathForFieldHeaderTitle = ".//*[@class='form-fields']//*[@class='set']/*[contains(@class,'set-title') and contains(.,'CRAFT')]";
	private String tempXpathForFieldLabelAndVal = ".//*[@class='form-field']//span[contains(.,'FIELD_LBEL_NAME')]//following-sibling::span[contains(.,'FIELD_VALUE')]";

	private String generalInfoFieldLabelsListXpath = ".//*[contains(@id,'general-form-section')]//*[@class='form-field']//span[contains(@class,'viewmode-label')]";

	private String tempFieldXpathInWFSummarySec = ".//*[contains(@id,'default-CRAFTSummary')]";
	private String tempXpathForFieldVal = ".//*[@class='form-field']//span[contains(.,'FIELD_LBEL_NAME')]//following-sibling::span";

	private String priorityDropDownXpathInEditTaskPg = ".//*[contains(@id,'prop_bpm_priority')]";
	private String selectedPriorityValXpath = ".//*[contains(@id,'prop_bpm_priority')]/option[contains(@selected,'selected')]";
	private String saveAndCloseBtnXpathInEditTaskPg = ".//*[contains(@id,'default-form-submit-button')]";
	private String loadingXpath = ".//*[@class='yui-dt-message']//td/*[contains(.,'Loading...')]";

	private String tempXpathForHistoryField = ".//*[contains(@id,'default-workflowHistory-form-section')]//table/thead[contains(.,'FIELD_NAME')]//ancestor::table//tbody/tr/td/*[contains(@class,'yui-dt-liner') and contains(.,'FIELD_VALUE')]";
	
	private String msgPopUpXpath = ".//*[@id='message']/div";
	private String tempTypeXpath = ".//a[text()='CRAFT']";
	private String shortListXpath = ".//button[contains(@id,'_default-sortField-button-button')]";
	
	private String detailsWFXpath = ".//div[contains(@class,'workflow-details-header')]/h1/span";
	
	private String claimButtonXapth = ".//button[text()='Claim']";
	private String selectStatusXpath = ".//*[@name='prop_pnwf_status']";
	private String submitTaskXpath = ".//*[@class='form-field suggested-actions']//span//button[contains(.,'Submit')]";
	private String releaseToPoolXpath = ".//*[@class='form-field suggested-actions']//span//button[contains(.,'Release')]";
	private String rejectTaskXpath = ".//*[@class='form-field suggested-actions']//span//button[contains(.,'Reject')]";
	private String WFButtonXapth = "//*[@class='form-buttons']//span//button[contains(.,'CRAFT')]";
	
	private String contentfullvaluestxt = ".//div[@class='form-field']//label[contains(text(),'CRAFT')]//following-sibling::input";
	public String contentfulllabel = ".//div[@class='form-field']//label[contains(text(),'CRAFT')]";
	public String contentfulllabelDp = ".//div[@class='form-field']//label[contains(text(),'CRAFT')]//following-sibling::select";
	private String checkbox = ".//*[@name='prop_cfwf_requestRush']//following::input[@type='checkbox']";
	public String contenterror = ".//*[@id='prompt']//div[@class='bd']";
	public String documentname = ".//h1[contains(text(),'CRAFT')]";
	public ArrayList<String> wfList = new ArrayList<String>();

	public String workFlowSummarySecFieldVal;
	public String generalInfoSecFieldVal;
	
	private String tempXpathPreviousComments=".//*[@class='recent-task form-element-border']//div//span[contains(text(),'CRAFT')]//following-sibling::span[contains(text(),'VALUE')]";
	
	private AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoWorkflowPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Assign work flow for selected file from Document page
	public void startWorkflow() {
		try {
			String file = dataTable.getData("MyFiles", "FileName");
			UIHelper.waitForPageToLoad(driver);
			UIHelper.click(driver, tempassignWorkflowXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Initate Worflow", "Workflow initated"
					+ "<br /><b> File Name : </b> " + file, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Initate Worflow", "Workflow initate failed",
					Status.FAIL);
		}
	}

	// Click on Document actions bar
	public void clickOnDocOptions() {
		try {

			UIHelper.waitForPageToLoad(driver);
			String Style = UIHelper.findAnElementbyXpath(driver,
					tempDocListXpath).getAttribute("style");

			if (Style.contains("none")) {
				System.out.println("Style : " + Style);
				UIHelper.click(driver, tempDocOptionsXpath);
				UIHelper.waitFor(driver);
			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Input Work flow parameters
	public void worflowInput(String taskType, String Msg, String DueDate,
			String Priority) {
		try {
			UIHelper.waitForPageToLoad(driver);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = sdf.format(date);
			String finalTaskTypeValueXpath = tempWorkFlowDropDownValXpath
					.replace("CRAFT", taskType);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					workflowDropdownListXpath);
			UIHelper.click(driver, workflowDropdownListXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalTaskTypeValueXpath);
			UIHelper.click(driver, finalTaskTypeValueXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, wfTextareXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, wfTextareXpath, Msg);
			UIHelper.sendKeysToAnElementByXpath(driver, wfDueDateXpath, strDate);
			UIHelper.selectbyVisibleText(driver, wfPriority, Priority);
			UIHelper.selectDropdownList(driver,
					UIHelper.findAnElementbyXpath(driver, wfPriority), Priority);
			report.updateTestLog("Input Workflow parameters",
					" <br /><b>WFType : </b> " + taskType
							+ "<br /><b>Message : </b>" + Msg
							+ " <br /><b>Due Date : </b>" + strDate
							+ " <br /><b>Priority : </b>" + Priority,
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Input Worflow Task Details",
					"Worflow Task Details input failed", Status.FAIL);
		}
	}

	// Select reviewer details for simply task
	public void selectReviewer() {
		try {
			UIHelper.click(driver, wfReviewerXapth);
			String userNames = dataTable.getData("Workflow", "UserName");
			String userNameValues[] = userNames.split(",");
			
			for (String userName : userNameValues) {
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						wfReviewerSearchXpath);
				UIHelper.sendKeysToAnElementByXpath(driver, wfReviewerSearchXpath,
						userName);
				UIHelper.click(driver, wfReviwerSearchbuttonXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						wfReviewerResultXpath);
				UIHelper.waitFor(driver);
				if (UIHelper.findAnElementbyXpath(driver, wfReviewerResultXpath)
						.getText().contains(userName)) {
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							wfReviewerResultXpath);
					UIHelper.click(driver, wfReviewerAddXpath);
					

					report.updateTestLog("Select Workflow Reviewer",
							"Reviewer selection Successful"
									+ "<br /><b> Reviewer : </b>" + userName,
							Status.DONE);
				} else {

					report.updateTestLog("Select Workflow Reviewer",
							"Reviewer selection failed", Status.FAIL);
				}
			}
			
			UIHelper.waitFor(driver);
			UIHelper.click(driver, wfReviewerOKXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
	
		} catch (Exception e) {
			report.updateTestLog("Input Worflow Task Details",
					"Worflow Task Details input failed", Status.FAIL);
		}
	}

	// Click start work flow button
	public void clickstartWorkflowBtn() {
		
		try {
			report.updateTestLog("Click on Start workflow",
					"Clicked on Start workflow successfully", Status.PASS);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, startWFbuttonXpath));
			UIHelper.highlightElement(driver, startWFbuttonXpath);
			UIHelper.click(driver, startWFbuttonXpath);
		//	Thread.sleep(5000);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, startWFbuttonXpath);
			UIHelper.waitForPageToLoad(driver);
			

		
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
/*			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// report.updateTestLog("Simple Workflow Initiated", "", Status.DONE);
	}

	public String getComments() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					workflowCommentsXpath);
			enteredComments = UIHelper.findAnElementbyXpath(driver,
					workflowCommentsXpath).getText();
			report.updateTestLog("comments",
					"comments entered while rejecting is : " + enteredComments,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enteredComments;
	}

	public void navigateToEditTaskPage() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, actionsIconXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, actionsIconXpath);
			report.updateTestLog("Edit Task Page",
					"Navigated to edit task page ", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Edit Task Page",
					" Failed to Navigate to edit task page ", Status.FAIL);
		}
	}

	public AlfrescoWorkflowPage navigateToTasksTab() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.click(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);

			report.updateTestLog("Navigate To Tasks Tab",
					"Navigated to Tasks Page successfully", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To Tasks Tab",
					"Navigate to Tasks Page failed", Status.FAIL);
		}

		return new AlfrescoWorkflowPage(scriptHelper);
	}

	// Select reviewer details for simply task
	public void checkWFStatus() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String finalwftaskXpath = tempwftaskXpath
					.replace("CRAFT", taskName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalwftaskXpath);
			UIHelper.findandAddElementsToaList(driver, resultTaskList, wfList);

			for (String result : wfList) {
				if (result.contains(taskName)) {

					UIHelper.findAnElementbyXpath(driver, finalwftaskXpath)
							.click();
					report.updateTestLog("Check WF Task",
							"Check WF task is available"
									+ "<br /><b> WF Task : </b>" + taskName,
							Status.PASS);
					break;
				}

			}
		} catch (Exception e) {
			report.updateTestLog("Check WF Task",
					"Check WF task is failed. WF task not found", Status.FAIL);
		}
	}

	// Select reviewer details for simply task
	public void checkWFStatus(String taskName) {
		try {
			// String taskName = dataTable.getData("Workflow", "Message");
			String finalwftaskXpath = tempwftaskXpath
					.replace("CRAFT", taskName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalwftaskXpath);
			UIHelper.findandAddElementsToaList(driver, resultTaskList, wfList);

			for (String result : wfList) {
				if (result.contains(taskName)) {

					UIHelper.findAnElementbyXpath(driver, finalwftaskXpath)
							.click();
					report.updateTestLog("Check WF Task",
							"Check WF task is available"
									+ "<br /><b> WF Task : </b>" + taskName,
							Status.PASS);
					break;
				}

			}
		} catch (Exception e) {
			report.updateTestLog("Check WF Task",
					"Check WF task is failed. WF task not found", Status.FAIL);
		}
	}

	// Select reviewer details for simply task
	public void checkWF(String Message) {
		try {
			String finalwftaskXpath = tempwftaskXpath.replace("CRAFT", Message);
			boolean isDisplayedTask = taskPage.checkCreatedOrAssignedTask(Message);
			if(isDisplayedTask)
			{
				report.updateTestLog("Check WF Task",
						"Check WF task is available"
								+ "<br /><b> WF Task : </b>" + Message,
						Status.PASS);
				UIHelper.click(driver, finalwftaskXpath);
			}
			else
			{
				report.updateTestLog("Check WF Task",
						"Check WF task is failed. WF task not found", Status.FAIL);
			}
			/*String finalwftaskXpath = tempwftaskXpath.replace("CRAFT", Message);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalwftaskXpath);
			UIHelper.findandAddElementsToaList(driver, resultTaskList, wfList);
			System.out.println(wfList);
			for (String result : wfList) {

				if (result.contains(Message)) {
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalwftaskXpath));
					UIHelper.highlightElement(driver, finalwftaskXpath);
					UIHelper.waitFor(driver);
					report.updateTestLog("Check WF Task",
							"Check WF task is available"
									+ "<br /><b> WF Task : </b>" + Message,
							Status.PASS);
					UIHelper.click(driver, finalwftaskXpath);
					break;
				}
			}*/
		} catch (Exception e) {
			report.updateTestLog("Check WF Task",
					"Check WF task is failed. WF task not found", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param workFlow
	 * @return
	 */
	public boolean isWorkFlowAvailable(String workFlow) {
		boolean flag = false;
		try {
			String finalWorkFlowXpath = tempWorkFlowDropDownValXpath.replace(
					"CRAFT", workFlow);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalWorkFlowXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, finalWorkFlowXpath)) {
				UIHelper.highlightElement(driver, finalWorkFlowXpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			return false;
		}

		return flag;
	}

	/**
	 * @author 412766
	 */
	public void clickWorkFlowDropDown() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					workflowDropdownListXpath);
			UIHelper.highlightElement(driver, workflowDropdownListXpath);
			UIHelper.click(driver, workflowDropdownListXpath);
		} catch (Exception e) {

		}
	}

	/**
	 * @author 412766
	 * @param workFlow
	 */
	public void selectWorkFlow(String workFlow) {
		try {
			
			
			
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					workflowDropdownListXpath);
			UIHelper.click(driver, workflowDropdownListXpath);
		
			String finalWorkFlowXpath = tempWFDropDownValXpath.replace(
					"CRAFT", workFlow);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalWorkFlowXpath);
		//	if (UIHelper.checkForAnElementbyXpath(driver, finalWorkFlowXpath)) {
				UIHelper.highlightElement(driver, finalWorkFlowXpath);
				UIHelper.click(driver, finalWorkFlowXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify to select the WorkFlow Type",
						"WorkFlow Selected successfully"
								+ "<br /><b>WorkFlow Type :</b> " + workFlow,
						Status.PASS);
			/*} else {
				report.updateTestLog("Verify to select the WorkFlow Type",
						"WorkFlow not Selected"
								+ "<br /><b>WorkFlow Type :</b> " + workFlow,
						Status.FAIL);
			}
*/
		} catch (Exception e) {
			report.updateTestLog("Verify to select the WorkFlow Type",
					"Verify to select the WorkFlow Type Failed", Status.FAIL);
		}
	}

	// Select reviewer details for simply task
	public void checkWFandAttachment(String Message) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalwftaskXpath = tempwftaskXpath.replace("CRAFT", Message);
			String finaltempattachmentXpath = tempattachmentXpath.replace(
					"CRAFT", Message);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalwftaskXpath);
			UIHelper.findandAddElementsToaList(driver, resultTaskList, wfList);
			System.out.println(wfList);
			for (String result : wfList) {

				if (result.contains(Message)) {
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalwftaskXpath));
					UIHelper.highlightElement(driver, finalwftaskXpath);
					UIHelper.highlightElement(driver, finaltempattachmentXpath);
					UIHelper.waitFor(driver);
					report.updateTestLog(
							"Check WF Task",
							"Check WF task is available"
									+ "<br /><b> WF Task : </b>"
									+ Message
									+ "<br /><b> Attachment : </b>"
									+ UIHelper.findAnElementbyXpath(driver,
											finaltempattachmentXpath).getText(),
							Status.PASS);

					break;
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Check WF Task",
					"Check WF task is failed. WF task not found", Status.FAIL);
		}
	}

	// Check Alfresco Logo and Workflow Details Main Header
	public boolean checkAlfrescoLogoAndWorkflowDetailsHeader() {
		boolean isDisplayedLogoAndWorkFlowHeader = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, alfrescoLogoXpath)
					&& UIHelper
							.checkForAnElementbyXpath(driver, pageTitleXpath)) {
				isDisplayedLogoAndWorkFlowHeader = true;
			} else {
				isDisplayedLogoAndWorkFlowHeader = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedLogoAndWorkFlowHeader;
	}

	// Check Task Details
	public boolean checkTaskDetails() {
		boolean isDisplayedTaskDetails = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, detailsHeaderXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, detailsHeaderXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							workflowDetailsXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							taskDetailsXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							workflowSummaryHeaderXpath)) {
				isDisplayedTaskDetails = true;
			} else {
				isDisplayedTaskDetails = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedTaskDetails;
	}

	// Check Field Header Names
	public boolean checkFieldHeaderTitleNames(String fieldHeaderName) {
		boolean isDisplayedFieldHeaderTitle = false;
		try {
			String finalXpathForFieldHeaderTitle = tempXpathForFieldHeaderTitle
					.replace("CRAFT", fieldHeaderName);
			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForFieldHeaderTitle)) {
				isDisplayedFieldHeaderTitle = true;
			} else {
				isDisplayedFieldHeaderTitle = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFieldHeaderTitle;
	}

	// Check Field Name and Field Values
	public boolean checkFieldLabelAndValue(String fieldName, String fieldVal) {
		boolean isDisplayedFieldHeaderTitle = false;
		try {
			String finalXpathForFieldLabelAndVal = tempXpathForFieldLabelAndVal
					.replace("FIELD_LBEL_NAME", fieldName).replace(
							"FIELD_VALUE", fieldVal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFieldLabelAndVal);
			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForFieldLabelAndVal)) {

				UIHelper.highlightElement(driver, UIHelper
						.findAnElementbyXpath(driver,
								finalXpathForFieldLabelAndVal));

				isDisplayedFieldHeaderTitle = true;
			} else {
				isDisplayedFieldHeaderTitle = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFieldHeaderTitle;
	}

	// Check General Info Field Labels and get count of field labels
	public ArrayList<String> getGeneralInfoFieldLabels() {
		ArrayList<String> generalInfoFieldsList = new ArrayList<String>();
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);

			List<WebElement> generalInfoLabelsList = UIHelper
					.findListOfElementsbyXpath(generalInfoFieldLabelsListXpath,
							driver);
			for (WebElement ele : generalInfoLabelsList) {
				generalInfoFieldsList.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return generalInfoFieldsList;
	}

	// Check Field Value in Workflow summary & General Info section
	public boolean checkFieldValueWorkflowAndGeneralInfoSec(
			String fieldLabelName) {
		boolean isEqualTwoSectionsFieldVal = false;
		try {
			String finalFieldXpathInWFSummarySec = tempFieldXpathInWFSummarySec
					.replace("CRAFT", fieldLabelName.toLowerCase());
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalFieldXpathInWFSummarySec);
			String finalXpathForFieldVal = tempXpathForFieldVal.replace(
					"FIELD_LBEL_NAME", fieldLabelName);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalXpathForFieldVal);

			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(
					driver, finalFieldXpathInWFSummarySec));
			workFlowSummarySecFieldVal = UIHelper.getTextFromWebElement(driver,
					finalFieldXpathInWFSummarySec).trim();

			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(
					driver, finalXpathForFieldVal));
			generalInfoSecFieldVal = UIHelper.getTextFromWebElement(driver,
					finalXpathForFieldVal).trim();

			if (workFlowSummarySecFieldVal.contains(generalInfoSecFieldVal)) {
				isEqualTwoSectionsFieldVal = true;
			} else {
				isEqualTwoSectionsFieldVal = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isEqualTwoSectionsFieldVal;
	}

	// Change Priority and Save the task details
	public void changePriorityInEditTaskPg(String priorityVal) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					priorityDropDownXpathInEditTaskPg);
			if (UIHelper.checkForAnElementbyXpath(driver,
					priorityDropDownXpathInEditTaskPg)) {
				UIHelper.highlightElement(driver, UIHelper
						.findAnElementbyXpath(driver,
								priorityDropDownXpathInEditTaskPg));
				UIHelper.selectbyVisibleText(driver,
						priorityDropDownXpathInEditTaskPg, priorityVal);
				UIHelper.waitFor(driver);
				report.updateTestLog(
						"Change the priority of the workflow task",
						"Priority field is editable and changed the priority of the task using"
								+ "<br /><b> Priority : </b>" + priorityVal,
						Status.DONE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Change Priority and Save the task details
	public String getChangedPriorityFromEditTaskPg() {
		String selectedPriorityVal = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					priorityDropDownXpathInEditTaskPg);

			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(
					driver, priorityDropDownXpathInEditTaskPg));

			selectedPriorityVal = UIHelper.getTextFromWebElement(driver,
					selectedPriorityValXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return selectedPriorityVal;
	}

	// Click on Save button and save the edited task details
	public void clickOnSaveBtnInEditTaskPg() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					saveAndCloseBtnXpathInEditTaskPg);
			if (UIHelper.checkForAnElementbyXpath(driver,
					saveAndCloseBtnXpathInEditTaskPg)) {
				UIHelper.highlightElement(driver, UIHelper
						.findAnElementbyXpath(driver,
								saveAndCloseBtnXpathInEditTaskPg));
				UIHelper.click(driver, saveAndCloseBtnXpathInEditTaskPg);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						loadingXpath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Field Name and Field Values
	public boolean checkHistoryFieldValue(String fieldName, String fieldVal) {
		boolean isDisplayedHistoryFieldVal = false;
		try {
			String finalXpathForHistoryField = tempXpathForHistoryField
					.replace("FIELD_NAME", fieldName).replace("FIELD_VALUE",
							fieldVal);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalXpathForHistoryField);
			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForHistoryField)) {

				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalXpathForHistoryField));
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver,
								finalXpathForHistoryField));

				isDisplayedHistoryFieldVal = true;
			} else {
				isDisplayedHistoryFieldVal = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedHistoryFieldVal;
	}
	
	
	// Check Previous Comments Field Name and Field Values
		public boolean checkPreviousComments(String fieldName, String fieldVal) {
			boolean isDisplayedHistoryFieldVal = false;
			try {
				String finalXpathForHistoryField = tempXpathPreviousComments
						.replace("CRAFT", fieldName).replace("VALUE",
								fieldVal);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						finalXpathForHistoryField);
				if (UIHelper.checkForAnElementbyXpath(driver,
						finalXpathForHistoryField)) {

					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalXpathForHistoryField));
					UIHelper.waitFor(driver);
					UIHelper.highlightElement(driver,
							UIHelper.findAnElementbyXpath(driver,
									finalXpathForHistoryField));

					isDisplayedHistoryFieldVal = true;
				} else {
					isDisplayedHistoryFieldVal = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return isDisplayedHistoryFieldVal;
		}

	/**
	 * @author 412766
	 * @param type
	 */
	public void selectDocumentViewType(String type){
		try{
			String typeXpath = tempTypeXpath.replace("CRAFT", type);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, shortListXpath);
			UIHelper.highlightElement(driver, shortListXpath);
			UIHelper.click(driver, shortListXpath);
			
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, typeXpath);
			UIHelper.click(driver, typeXpath);
			
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgPopUpXpath);
			
			UIHelper.waitFor(driver);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 412766
	 * @return
	 */
	public boolean checkDetailsFormatInWF(String WFName, String valueNotContains){
		boolean flag = false;
		try{
			//detailsWFXpath
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, detailsWFXpath);
			UIHelper.highlightElement(driver, detailsWFXpath);
			String detailsValue = UIHelper.getTextFromWebElement(driver, detailsWFXpath);
			if(detailsValue.contains(WFName) && !(detailsValue.contains(valueNotContains))){
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * @author 412766
	 * @return
	 */
	public boolean isClaimButtonVisible(){
		boolean flag = false;
		try{
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, claimButtonXapth))){
				UIHelper.highlightElement(driver, claimButtonXapth);
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * @author 412766
	 */
	public void clickClaimButton(){
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, claimButtonXapth);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, claimButtonXapth))){
				UIHelper.highlightElement(driver, claimButtonXapth);
				report.updateTestLog("Click the Claim Button",
						"Clicked successfully", Status.PASS);
				UIHelper.click(driver, claimButtonXapth);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgPopUpXpath);
				
				UIHelper.waitFor(driver);
				//UIHelper.waitForPageToLoad(driver);
			}else{
				report.updateTestLog("Click the Claim Button",
						"Not able to click", Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Click the Claim Button",
					"Not able to click", Status.FAIL);
		}
	}
	
	/**
	 * @author 391543
	 */
	public void selectWFStatus(String status) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectStatusXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, selectStatusXpath))) {
				UIHelper.highlightElement(driver, selectStatusXpath);
				WebElement selectWFStatus = UIHelper.findAnElementbyXpath(
						driver, selectStatusXpath);
				Select selectStatus = new Select(selectWFStatus);
				UIHelper.click(driver, selectStatusXpath);
				selectStatus.selectByVisibleText(status);
				report.updateTestLog("Select Status of the workflow",
						"Status selected successfully<br><b>Status</b>"
								+ status, Status.PASS);

			} else {
				report.updateTestLog("Select Status of the workflow",
						"Failed to select status<br><b>Status</b>"
								+ status, Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Status of the workflow",
					"Failed to select status<br><b>Status</b>"
							+ status, Status.FAIL);

		}
	}
	
	/**
	 * @author 391543
	 */
	public void clickOnSubmitTask() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, submitTaskXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, submitTaskXpath));
			UIHelper.highlightElement(driver, submitTaskXpath);
			report.updateTestLog("Click the [SUBMIT TASK] Button",
						"[SUBMIT TASK] Button Clicked successfully", Status.PASS);
				UIHelper.click(driver, submitTaskXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
					
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click the [SUBMIT TASK] Button",
					"Not able to click [SUBMIT TASK] Button", Status.FAIL);
		}
	}
	
	/**
	 * @author 391543
	 */
	public void clickOnReleaseToPool() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, releaseToPoolXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, releaseToPoolXpath));
			UIHelper.highlightElement(driver, releaseToPoolXpath);
			UIHelper.click(driver, releaseToPoolXpath);
			report.updateTestLog("Click the [RELEASE TO POOL] Button",
						"[RELEASE TO POOL] Button Clicked successfully", Status.PASS);
				
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
					
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click the [RELEASE TO POOL] Button",
					"Not able to click [RELEASE TO POOL] Button", Status.FAIL);
		}
	}
	private String commonPageTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String commentsBoxXpath =".//*[@title='Comment']";
	
	/**
	 * @author 391543
	 */
	public void enterComments(String comments) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentsBoxXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, commentsBoxXpath));
			UIHelper.highlightElement(driver, commentsBoxXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, commentsBoxXpath, comments);
			report.updateTestLog("Enter comments in Comments filed",
						"Comments eneted in the comments field successfully", Status.DONE);
					
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter comments in Comments filed",
					"Failed to enter Comments in the comments field", Status.FAIL);
		}
	}
	
	/**
	 * @author 391543
	 */
	public void clickOnRejectTask() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, rejectTaskXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, rejectTaskXpath));
			UIHelper.highlightElement(driver, rejectTaskXpath);
			report.updateTestLog("Click the [REJECT] Button",
						"[REJECT] Button Clicked successfully", Status.PASS);
				UIHelper.click(driver, rejectTaskXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
				
					
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click the [REJECT] Button",
					"Not able to click [REJECT] Button", Status.FAIL);
		}
	}

	
	/**
	 * @author 317085
	 */
	public void contentfulfillvaluestxt(String label, String values) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalcontentvalues = contentfullvaluestxt.replace("CRAFT", label);
			String finalcontentlabel = contentfulllabel.replace("CRAFT", label);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalcontentlabel);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalcontentlabel));
			UIHelper.highlightElement(driver, finalcontentlabel);
			UIHelper.highlightElement(driver, finalcontentvalues);
			UIHelper.sendKeysToAnElementByXpath(driver, finalcontentvalues, values);
			report.updateTestLog("Update "+label+" values",
					label+	"has bee updated as " +values, Status.PASS);
						
					
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Update "+label+" values",
					label+	"has bee updated as " +values + " failed", Status.FAIL);
		}
	}
	
	
	/**
	 * @author 317085
	 */
	public void contentfulfillvaluesDp(String label, String values) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalcontentvalues = contentfulllabelDp.replace("CRAFT", label);
			String finalcontentlabel = contentfulllabel.replace("CRAFT", label);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalcontentlabel);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalcontentlabel));
			UIHelper.highlightElement(driver, finalcontentlabel);
			UIHelper.selectbyVisibleText(driver, finalcontentvalues, values);
			report.updateTestLog("Update "+label+" values",
					label+	"has bee updated as " +values, Status.PASS);
						
					
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Update "+label+" values",
					label+	"has bee updated as" +values + " failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 317085
	 */
	public void Updateallcontent(String data) {
		try {
			if (data.contains(";")) {
				String splittedFolderDetailas[] = data.split(";");
				for (String values : splittedFolderDetailas) {
					String splittedFolderValues[] = values.split("-");
					
					String finalcontentlabel = contentfulllabelDp.replace("CRAFT", splittedFolderValues[0]);
					if (splittedFolderValues != null && splittedFolderValues.length > 1 &&
							UIHelper.checkForAnElementbyXpath(driver, finalcontentlabel)) {
					 
					contentfulfillvaluesDp(splittedFolderValues[0], splittedFolderValues[1]);
				}else{
					contentfulfillvaluestxt(splittedFolderValues[0], splittedFolderValues[1]);
				}
		} 
			}
			
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, checkbox));
			UIHelper.highlightElement(driver, checkbox);
			UIHelper.click(driver, checkbox);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	// Click start work flow button
	public void contentfulstartWFBtn(String filename) {

		try {
			report.updateTestLog("Click on Start workflow",
					"Clicked on Start workflow successfully", Status.PASS);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					startWFbuttonXpath));
			UIHelper.highlightElement(driver, startWFbuttonXpath);
			UIHelper.click(driver, startWFbuttonXpath);
			Thread.sleep(5000);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, contenterror)) {
				UIHelper.highlightElement(driver, contenterror);
				report.updateTestLog(
						"Start workflow",
						"Start workflow Failed. Error Msg :"
								+ UIHelper.findAnElementbyXpath(driver,
										contenterror).getText(), Status.FAIL);
			} else {
				report.updateTestLog("Start workflow",
						"Start workflow successfully", Status.PASS);
			}
			String finaldocumentname = documentname.replace("CRAFT", filename);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finaldocumentname);
			UIHelper.highlightElement(driver, finaldocumentname);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Start workflow issue",
					"Start workflow failed", Status.FAIL);
		}
	}
	
	// Click start work flow button for content fulfilment from selected items
		public void contentfulstartWFBtnSelect() {

			try {
				report.updateTestLog("Click on Start workflow",
						"Clicked on Start workflow successfully", Status.PASS);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
						startWFbuttonXpath));
				UIHelper.highlightElement(driver, startWFbuttonXpath);
				UIHelper.click(driver, startWFbuttonXpath);
				Thread.sleep(5000);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, contenterror)) {
					UIHelper.highlightElement(driver, contenterror);
					report.updateTestLog(
							"Start workflow",
							"Start workflow Failed. Error Msg :"
									+ UIHelper.findAnElementbyXpath(driver,
											contenterror).getText(), Status.FAIL);
				} else {
					report.updateTestLog("Start workflow",
							"Start workflow successfully", Status.PASS);
				}
				/*String finaldocumentname = documentname.replace("CRAFT", filename);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finaldocumentname);
				UIHelper.highlightElement(driver, finaldocumentname);*/

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				report.updateTestLog("Start workflow issue",
						"Start workflow failed", Status.FAIL);
			}
		}
		
		/**
		 * @author 317085
		 */
		public void clickWFactionbtn(String action) {
			try {
				
				String finalWFButtonXapth = WFButtonXapth.replace("CRAFT", action);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalWFButtonXapth);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalWFButtonXapth));
				UIHelper.highlightElement(driver, finalWFButtonXapth);
				report.updateTestLog("Click the " +action+" Button",
						action +"Button Clicked successfully", Status.PASS);
					UIHelper.click(driver, finalWFButtonXapth);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
						
			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog("Click the " +action+" Button",
						"Not able to click Button", Status.FAIL);
			}
		}
}
	



