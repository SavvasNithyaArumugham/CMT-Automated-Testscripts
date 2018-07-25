package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
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
public class AlfrescoTasksPage extends ReusableLibrary {

	private String tasksSectionXpath = ".//*[@class='sticky-wrapper']";
	private String pageTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String commonTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String taskMenuXpath = ".//*[@id='HEADER_TASKS_text']";
	private String taskMyTasksXpath = ".//*[@id='HEADER_MY_TASKS_text']/a";
	private String myTaskSectionXpath = ".//*[@id='alf-content']//table[contains(@id,'yuievtautoid')]";
	private String commentBoxXpath = ".//textarea[contains(@id,'_bpm_comment')]";
	private String commentBoxXpath2 = ".//textarea[contains(@title,'Comment')]";
	private String acceptBtnXpath = ".//*[contains(@id,'accept-button')]";
	private String approveBtnXpath = ".//button[contains(text(),'Approve')]";
	private String rejectBtnXpathInEditTaskPg = ".//button[contains(text(),'Approve')]";
	private String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
	private String activeTasksXpath = ".//a[contains(text(),'Active')]";
	private String activeTasksListXpath = ".//*[contains(@class,'yui-dt-data')]//a[contains(@class,'theme-color-1')]";
	private String statusLabelXpath = ".//label[contains(text(),'Status')]";
	private String statusSelectBoxXpath = ".//select[@name='prop_bpm_status']";
	private String taskDoneBtnXpath = ".//button[contains(text(),'Task Done')]";

	private String myTasksMenuLinkXpath = ".//*[@id='HEADER_MY_TASKS_text']/a";
	private String myTasksHeaderXpath = ".//*[@id='HEADER_TITLE']";

	private String filterWFXpath = ".//*[@id='alf-filters']//a[text()='CRAFT']";
	private String filterfolder = ".//div[@class='treeview filter']//td//span[text()='CRAFT']";
	private String twister = ".//h2[@class='alfresco-twister alfresco-twister-closed' and text()='Library']";
	private String twisteropen = ".//h2[@class='alfresco-twister alfresco-twister-open' and text()='Library']";
	
	public String filterList = ".//ul[@class='filterLink']//li//a";

	private String dueDateLblXpath = ".//*[@id='alf-content']//*[contains(@class,'due')]";
	private String dueDateValXPath = ".//*[@id='alf-content']//div[@class='due']//span[contains(text(),'20')]";
	private boolean isDisplayedDueDateVal = false;
	private String duedtVal;

	private String resultTaskList = ".//table/tbody[contains(@class,'data')]/tr/td[2]//h3/a";
	private String tempwftaskXpath = ".//table/tbody[contains(@class,'data')]/tr/td[2]//h3/a[text()='CRAFT']";
	public ArrayList<String> wfList = new ArrayList<String>();

	private String descValforTask;
	private String descValTaskXPath = ".//*[text()='Message:']/ancestor::div[1]/span[2]";
	private String componentValTaskXpath = ".//*[@id='alf-content']//div[@class='attached']//span[contains(text(),'gif')]";
	private String componentValforTask;

	private String compltdFltrXPath = ".//*[@id='alf-filters']//a[@rel='completed']";

	private String viewTaskXPath = ".//*[contains(@title,'Task')]";

	private String[] duedteFilters = { "today", "tomorrow", "next7Days",
			"overdue", "noDate" };
	private boolean duedteFilterValdisplayed = false;
	private String dueFltrdTaskDispXPath = ".//*[@title='Edit Task']";
	private int i = 0;

	boolean assgineeforTaskisDisplayed = false;
	private String myWorkFlowContainerXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']";
	private String myWorkFlowTableXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']//table";
	private String viewWrkFlowXPath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']//table//tr[1]//*[@class='workflow-view-link']";
	private String taskMyWrkFlwXpath = ".//*[@id='HEADER_MY_WORKFLOWS_text']/a";
	private String viewWrkFlwXPath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']//a[@title='View History']";
	private String assignedToFldXPath = ".//*[@id='yui-dt0-th-owner-liner']/span[contains(text(),'Assigned To')]";
	private String assignedToValXPath = ".//div[@class='current-tasks']//*[@class='yui-dt-data']/tr[1]/td[2]/div[1]/a";
	private String assignedToVal;
	private boolean isDisplayedAssignedToVal = false;

	private String assignFltrXPath = ".//*[@id='alf-filters']//span[@class='assignee']//a[@rel='me']";
	private String lstTasksMeFlrtXPath = ".//*[@title='Edit Task']";

	private String myworkFlowMenuOptionXpath = ".//*[@id='HEADER_MY_WORKFLOWS_text']/a";
	private String myWorkFlowSecXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']";
	private String myWorkflowListXpath = ".//*[@id='bd']//*[@id='yui-main']/*[@id='alf-content']/*[contains(@id,'my-workflows') and contains(@id,'list')]";
	private String myWorkflowsOrMyTasksXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']//*[contains(@id,'default-workflows')] | .//*[@class='sticky-wrapper']//*[@id='alf-content']//*[contains(@id,'default-tasks')]";
	private String myWorkFlowTableContainerXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']//table[contains(@id,'yuievtautoid')]";
	private String tasksTitlesXpath = ".//*[@class='yui-dt-data']//h3//a";
	private String taskslstXpathvalue = ".//*[@class='yui-dt-data']//h3/a";
	private String workflowStartedDetailsXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='share-form']//span[contains(.,'Started:')]//following-sibling::span[1]";
	private String workflowDueDateDetailsXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='share-form']//span[contains(.,'Due:')]//following-sibling::span[1]";
	private String dueDateXpathFormWorkflowSummarySec = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='share-form']//*[@class='summary-icons']//*[contains(@class,'due')]";
	private String workflowDueDateDetailsXpathFromItemsFieldSec = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='share-form']//span[contains(.,'Workflow Due Date:')]//following-sibling::span[1]";
	private String workFlowDetailsContainerXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'form-container')]";

	private String defaultTaskFilterXpath = ".//*[contains(@id,'default-filterTitle')]";
	private String myTasksTableXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']/div[2][contains(@id,'my-tasks')]";
	private String tasksTableXpath = ".//*[@class='sticky-wrapper']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']";
	private String taskLinkOnMyTasks = ".//*[@class='sticky-wrapper']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td[2]//h3/a[text()='CRAFT']";
	private String firstRowOfTasksTableXpath = ".//*[@class='sticky-wrapper']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr[1]/td[2]";
	private String loadingXpath = ".//*[@class='sticky-wrapper']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-message']/tr[1]/td[contains(.,'Loading')]";

	private String startWorkFlowButtonXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default-startWorkflow-button-button')]";
	private String workflowDropdownListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default-workflow-definition-button-button')]";
	private String tempWorkFlowDropDownValXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default-workflow-definition-menu')]//ul//li[contains(.,'CRAFT')]";
	private String workflowMessageXpath = ".//*[@name='prop_bpm_workflowDescription']";
	private String wfDueDateXpath = ".//*[@class='date-entry']";
	private String dueDateFieldXpathForErrorVal = ".//*[contains(@id,'prop_bpm_workflowDueDate-cntrl-date')]";
	private String wfPriority = ".//*[@name='prop_bpm_workflowPriority']";

	private String wfAssignerXapth = ".//*[@class='form-field']/label[contains(.,'Assign To')]//following-sibling::div//*[@class='first-child']/button[text()='Select']";

	private String wfApproverXapth = ".//*[@class='form-field']/label[contains(.,'Assign To') or contains(.,'Approver')]//following-sibling::div//*[@class='first-child']/button[text()='Select']";
	// private String wfApproverSearchXpath =
	// ".//*[contains(@id,'cntrl-picker_c') and not(contains(@style,'hidden'))]//*[@class='search-input']";
	private String wfApproverSearchXpath = ".//*[contains(@id,'cntrl-picker_c') and not(contains(@style,'hidden'))]//*[@class='picker-search-input']";
	private String wfApproverSearchbuttonXpath = ".//*[contains(@id,'cntrl-picker_c') and not(contains(@style,'hidden'))]//*[@class='search-button']//button";
	private String wfApproverResultXpath = ".//*[contains(@id,'cntrl-picker_c') and not(contains(@style,'hidden'))]//table//td[2]//h3";
	private String wfApproverAddXpath = ".//*[contains(@id,'cntrl-picker_c') and not(contains(@style,'hidden'))]//table//td[3]//a";
	private String wfApproverOKXpath = ".//*[contains(@id,'cntrl-picker_c') and not(contains(@style,'hidden'))]//*[@class='first-child']/button[text()='OK']";

	private String wfReviewerXapth = ".//*[@class='form-field']/label[contains(.,'Reviewer')]//following-sibling::div//*[@class='first-child']/button[text()='Select']";
	private String wfReviewerSearchXpath = ".//*[contains(@id,'cntrl-picker_c') and not(contains(@style,'hidden'))]//*[@class='picker-search-input']";
	private String wfReviwerSearchbuttonXpath = ".//*[contains(@id,'cntrl-picker_c') and not(contains(@style,'hidden'))]//*[@class='search-button']//button";
	private String wfReviewerResultXpath = ".//*[contains(@id,'cntrl-picker_c')]//table//td[2]//h3";
	private String wfReviewerAddXpath = ".//*[contains(@id,'cntrl-picker_c')]//table//td[3]//a";
	private String wfReviewerOKXpath = ".//*[contains(@id,'cntrl-picker_c') and not(contains(@style,'hidden'))]//*[@class='first-child']/button[text()='OK']";

	private String startWFbuttonXpath = ".//*[@class='first-child']/button[text()='Start Workflow']";

	private String testOutputForCreatedWorkFlow = "src/test/resources/AppTestData/TestOutput/WorkflowDetails.txt";

	private String deleteFolderXpath = ".//*[@id='Share']//*[@id='prompt']//span[contains(@id,'yui-gen')]/span[contains(.,'Delete')]";
	private String messageXpath = ".//*[@id='message']/div";
	private String tempTaskTableRowXpath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr[1]";

	private String selectedItemsXpath = ".//*[@id='yui-main']//*[@id='alf-content']//span/span[contains(.,'Selected Items')]";
	private String deleteXpath = ".//*[contains(@id,'yui-gen')]/a/span[contains(.,'Delete')]";

	private String tempXpathForCancelTaskLink = ".//*[@class='sticky-wrapper']//*[@id='bd']//table//*[@class='yui-dt-data']//tr//td[contains(.,'CRAFT')]//following-sibling::td//a[contains(.,'Cancel Workflow')]";
	private String yesButtonXpathInCancelWorkflow = ".//*[@id='Share']//*[@id='prompt']//button[contains(.,'Yes')]";
	private String messageDivXpath = ".//*[@id='message']/div";

	private String fieldErrorMsgXpath = ".//*[contains(text(),'Field contains an error.')]";
	private boolean isDisplayedFieldErrorVal;

	private String attachedFileTxt = "";
	private String attchdFldEdtXPath = ".//a[contains(text(),'TC102.txt')]";

	private String tasksEditButtonXpath = ".//*[@class='sticky-wrapper']//*[contains(@id,'default-edit-button')]";
	private String editTaskReviewCommentsTAreaXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@title='Comment']";
	private String taskStatusDropdownXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_bpm_status')]";

	private String approveButtonXpathInReviewTaskPage = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_cwf_reviewOutcome-Approve')]/span[contains(.,'Approve')] | //*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_transitions-Next')]/span[contains(.,'Task Done')]";
	private String rejectButtonXpathInReviewTaskPage = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_cwf_reviewOutcome-Reject')]/span[contains(.,'Reject')] | //*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_transitions-Next')]/span[contains(.,'Task Done')]";
	private String tempXpathForApproveOrRejectTaskButton = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_cwf_reviewOutcome-CRAFT')]/span[contains(.,'CRAFT')] | //*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_transitions-Next')]/span[contains(.,'Task Done')]";

	private String completedTasksLinkXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//ul/li/span/a[contains(.,'Completed')]";

	private String completedTasksTabXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//h2[contains(.,'Completed')]";

	private String responseCommentXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//span[contains(.,'Comment')]//following-sibling::span[1]";
	private String statusXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default-general-form-section')]/following-sibling::div[@class='set']//span[contains(.,'Status')]//following-sibling::span[1]";
	private String priorityXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default-general-form-section')]//span[contains(.,'Priority')]//following-sibling::span[1]";
	private String workFlowDetailsLinkXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default-body')]//span[contains(.,'Workflow Details')]";
	private String deleteWorkFlowBtnXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default-delete')]/span/span[contains(.,'Delete Workflow')]";
	private String deleteCompWorkFlowConfirmMsgYesXpath = ".//*[@id='Share']//*[@id='prompt']//*[@class='button-group']//span/button[contains(.,'Yes')]";

	private String finalDueDate;

	private String activeTask = ".//h2[contains(text(),'Active Tasks')]";

	private String rejectBtnXpath = ".//*[contains(@id,'reject-button')]";

	private String CompletedTasksXpath = ".//a[contains(text(),'Completed')]";
	private String completedTasksListXpath = ".//*[contains(@class,'yui-dt-data')]//a[contains(@class,'theme-color-1')]";
	private boolean found;

	private String workflowDetailsXpath = ".//a[contains(text(), 'Workflow Details')]";

	private String assignedToMeXpath = ".//*[@class='assignee']//a[contains(text(),'Me')]";

	private String viewMoreActionsLinkXpath = ".//*[@class='view_more_actions']";

	private String editTaskTitleXpath = ".//*[@id='HEADER_TITLE']";

	private String taskDoneXpath = ".//button[contains(text(), 'Task Done')]";

	private String createdTasksContainerXpath = ".//*[@id='template_x002e_list_x002e_my-tasks_x0023_default-tasks']";
	private ArrayList<String> createdTasksNameList = new ArrayList<String>();

	private String taskNameXpath = ".//*[@class='yui-dt-data']//h3//a";
	private String tempTaskNameXpath = ".//*[@class='yui-dt-data']//h3//a[text()='CRAFT']";

	private String wFtaskDoneBtnXpath = ".//*[contains(@id,'default_prop_transitions-Next-button')]";

	private boolean isDisplayedWorkflow;

	private WebElement taskLinkEle;

	private String createdTasksListXpathFromMyTasks = ".//*[@class='sticky-wrapper']//*[@id='alfresco-myworkflows']//*[@id='alf-content']//*[@class='yui-dt-data']/tr/td[2]//h3/a";
	private String assignedTasksListXpath = ".//*[@class='sticky-wrapper']//*[@id='alfresco-myworkflows']//*[@id='alf-content']//*[@class='yui-dt-data']/tr/td[2]//h3/a";

	private ArrayList<String> completedTasksNameList = new ArrayList<String>();

	private String taskTypeXpath = ".//*[@class='sticky-wrapper']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td[2]//h3/a[text()='CRAFT']//ancestor::td/div/div/div[5]/span[text()='Review Asset']";

	private String reviewAssetTaskXpath = ".//*[@class='sticky-wrapper']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']//span[text()='Review Asset']//preceding::h3[1]/a[text()='CRAFT']";

	private String rejectButtonXpath = ".//button[contains(.,'Reject')]";
	private String timeStampHisXPath = ".//*[@class='workflow-history']//tbody[@class='yui-dt-data']/tr[1]/td[3]";

	private String lstWrkTypesBtnXPath = ".//*[@class='first-child']/button[contains(text(),'Please select a workflow')]";
	private String lstWrkTypesSimplTaskXPath = ".//div[@class='bd']/ul[@class='first-of-type']/li/span[text()='Simple Task']";
	private String lstWrkTypesSimplRevApprTaskXPath = ".//div[@class='bd']/ul[@class='first-of-type']/li/span[text()='Simple Review and Approve']";
	private String taskLinksXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-tasks')]//table[contains(@id,'yuievtautoid')]//tbody[contains(@class,'yui-dt-data')]//tr//td[2]//h3/a";

	private String contRevBtnXPath = ".//button[contains(.,'Continue Review and Approve')]";
	private String tasksFilteredTitleXpath = ".//*[contains(@id,'default-filterTitle')]";

	private ArrayList<String> taskTypeDropdownValues = new ArrayList<String>();

	private boolean isDispTask;

	private String pageBodyXpath = ".//*[@id='bd']";

	private String overDueTaskXPath = ".//*[@id='alf-filters']//a[@rel='noDate']";
	private ArrayList<String> workflowsList = new ArrayList<String>();
	public String errorMessageVal;

	private boolean isDisplayedAcceptedOrRejectedTask;

	private String activeWorkflowXpath = "//*[@class='workflows']/*[contains(.,'Active')]";
	private String completedWorkflowXpath = "//*[@class='workflows']/*[contains(.,'Completed')]";
	private String activeWorkflowHeadlineXpath = "//*[@class='workflow-list']//h2[contains(.,'Active Workflows')]";
	private String completedWorkflowHeadlineXpath = "//*[@class='workflow-list']//h2[contains(.,'Completed Workflows')]";

	private String saveBtnXPath = ".//button[contains(@id,'submit')]";

	private String taskslistName = "//*[@class='yui-dt-data']//h3/a";
	public ArrayList<String> tasksList = new ArrayList<String>();
	private String temptasklist = "//a[contains(text(),'CRAFT')]";

	private String myTaskPagesXpath = ".//*[@id='bd']//*[@id='yui-main']//*[@class='yui-u']//a[contains(@class,'yui-pg-page')]";
	private String nextPageXpath = "//a[@title='Next Page']";
	private String taskTableXpath = ".//*[@class='sticky-wrapper']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']";

	private String synchronizingRemoteTasksMsgXpath = ".//*[contains(@id,'my-tasks')]//h2[contains(.,'(Synchronizing Remote Tasks List)')]";
	private String tempEditTaskFileXpath = ".//*[@class='yui-dt-data']//*[contains(text(),'CRAFT')]";

	private String approvalPercentageFieldXpath = ".//input[contains(@id,'requiredApprovePercent') and contains(@type,'text')]";

	private String tempXpathForPerformActionOnTask = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'default_prop_cwf_reviewOutcome-CRAFT')]/span[contains(.,'CRAFT')]";
	private String continueReviewAndApproveBtnXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'continueAfterEdit-Next')]/span[contains(.,'Continue Review and Approve')]";

	private String claimBtnXpath = ".//button[contains(.,'Claim')]";

	private String viewModeFieldXpathForDueDate = ".//*[contains(@id,'default-form-fields')]//span[contains(.,'Due:')]//following-sibling::span";

	private String rejectOutcomeXpath = "//*[@class='workflow-reject-comment']//td[4]/div[contains(text(),'Rejected')]";

	private String workflowStatusXpath = "//div[@class='set']//*[contains(text(),'CRAFT')]";

	private String tempXpathForViewTask = ".//*[contains(@class,'yui-dt-data')]/tr/td//a[contains(text(),'CRAFT')]//ancestor::td//following-sibling::td//span[contains(.,'View Task')]";
	private String viewProcessDiagButtonXpath = ".//button[contains(text(), 'View Process Diagram')]";

	private String tempXpathForGeneralInfoLabelName = ".//*[@class='form-field']//span[contains(.,'CRAFT')]";
	private String approverFieldLabelXpath = ".//*[@class='form-field']/label[contains(.,'Approvers')]";
	private String reviewerFieldLabelXpath = ".//*[@class='form-field']/label[contains(.,'Reviewers')]";

	private String taskWithoutDueDateXpath = ".//h2[text()='Tasks Without a Due Date']";

	private String edtTaskLinkXPath = ".//*[@class='yui-dt-data']//h3//a[text()='CRAFT']//ancestor::h3/ancestor::div[@class='yui-dt-liner']//ancestor::tr//td[@class='yui-dt0-col-name yui-dt-col-name yui-dt-last']//div[@class='task-edit-link']";

	private String startWorkflowBtn = ".//*[contains(@id,'default-startWorkflow-button-button')]";
	private String loadingXpathInWorkflowPage = ".//*[@class='yui-dt-message']//td[contains(.,'Loading')]";
	private String documentLibXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']";

	private String filterDueTodayXpath = ".//*[@class='filter']//*[@class='due']/a[contains(.,'Today')]";

	//private String tempXpathForAttachedFileNameInTasksPage = "//*[@class='yui-dt-data']//h3/a[text()='CRAFT']//ancestor::h3//following-sibling::*[@class='attached']";
	private String tempXpathForAttachedFileNameInTasksPage = "//*[@class='yui-dt-data']//span[text()='CRAFT']//ancestor::div//following-sibling::*[@class='attached']";
	private String tempXpathForRemoveItemInEditTaskPage = ".//*[@class='yui-dt-data']/tr/td//h3/a[contains(.,'CRAFT')]//ancestor::td//following-sibling::td//a[contains(@title,'Remove')]";

	private String saveAndCloseBtnXpath = ".//*[contains(@id,'default-form-submit-button')]";

	private String reAssignBtnXpath = ".//*[contains(@id,'default-reassign-button')]";
	private String searchUserFieldInSelectAsigneePopup = ".//*[contains(@id,'default-peopleFinder-search-text')]";
	private String searchBtnInSelectAsigneePopup = ".//*[contains(@id,'default-peopleFinder-search-button-button')]";
	private String tempSearchResultXpathInSelectAsigneePopup = ".//*[@class='yui-dt-data']//h3/a[contains(.,'CRAFT')]";
	private String tempXpathForSelectSearchedUser = ".//*[@class='yui-dt-data']//h3/a[contains(.,'CRAFT')]//ancestor::td//following-sibling::*//button";
	private String noItemsFoundXpath = ".//*[contains(@id,'groupAssignee-cntrl-picker-results')]//table//td[contains(.,'No items found')]";
	private String attachedFileListXpath = ".//*[contains(@id,'currentValueDisplay')]//table//tr//td[contains(@class,'col-name')]//a";

	private String revAndAppArchSubmissionProcessInputXpath = ".//label[contains(.,'FIELD_LABEL')]//ancestor::label//following-sibling::input";
	private String revAndAppArchSubmissionProcessTextAreaXpath = ".//label[contains(.,'TEXT_AREA_LABEL')]/following-sibling::textarea";
	private String revAndAppArchSubmissionProcessDropDownXpath = ".//label[contains(.,'DROPDOWN_FIELD_LABEL')]/following-sibling::select";
	private String revAndAppArchSubmissionProcessChkBoxXpath = ".//label[contains(.,'CHKBOX_LABEL')]/preceding-sibling::input[1]";

	// private String loadingXpathInWorkflowPage =
	// ".//*[@class='yui-dt-message']//td[contains(.,'Loading')]";

	/**
	 * 
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoTasksPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public AlfrescoTasksPage acceptTask() {
		try {
			String taskName = new FileUtil()
					.readDataFromFile(testOutputFilePath);
			String taskLinkXPath = ".//*[contains(text(),'" + taskName + "')]";

			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.mouseOveranElement(driver,
					UIHelper.findAnElementbyXpath(driver, myTaskSectionXpath));
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskLinkXPath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					taskLinkXPath));
			UIHelper.click(driver, taskLinkXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys("test");
			UIHelper.click(driver, acceptBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("AcceptTask", "Task Accepted Successfully",
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoTasksPage(scriptHelper);
	}

	public void acceptSiteInvitation(String siteName) {
		try {

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys("test");
			UIHelper.click(driver, acceptBtnXpath);
			report.updateTestLog("Accept Task",
					"Task Accepted Successfully <br/><b>Accepted Task:</b>"
							+ siteName, Status.PASS);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Approve invited task
	public void acceptInvitedTask() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (UIHelper.checkForAnElementbyXpath(driver, acceptBtnXpath)) {
				driver.findElement(By.xpath(commentBoxXpath)).sendKeys("test");
				UIHelper.click(driver, acceptBtnXpath);
				report.updateTestLog("Accept task in edit task page",
						"Task accepted successfully", Status.DONE);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
			} else {
				report.updateTestLog("Accept task in edit task page",
						"Task failed to accept", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Reject invited task
	public void rejectInvitedTask() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (UIHelper.checkForAnElementbyXpath(driver,
					rejectBtnXpathInEditTaskPg)) {
				driver.findElement(By.xpath(commentBoxXpath)).sendKeys("test");
				UIHelper.click(driver, rejectBtnXpathInEditTaskPg);
				report.updateTestLog("Reject task in edit task page",
						"Task rejected successfully", Status.DONE);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
			} else {
				report.updateTestLog("Reject task in edit task page",
						"Task failed to reject", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Comments By Sai Kiran, Function To accept Task under Mytasks
	public void acceptInvitationTask() {
		try {
			String taskName = new FileUtil()
					.readDataFromFile(testOutputFilePath);
			acceptInvitation(taskName);

		} catch (Exception e) {
		}

	}

	/**
	 * @author 412766
	 */
	public void acceptInvitation(String taskName) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.click(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			String taskLinkXPath = ".//*[contains(text(),'" + taskName + "')]";
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskLinkXPath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					taskLinkXPath));
			UIHelper.click(driver, taskLinkXPath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys("test");
			UIHelper.click(driver, acceptBtnXpath);
			report.updateTestLog("Accept Task",
					"Task Accepted Successfully <br/><b>Accepted Task:</b>"
							+ taskName, Status.PASS);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Comments By Sai Kiran, Function To accept Task under Mytasks
	public void rejectInvitationTask() {
		try {
			String taskName = new FileUtil()
					.readDataFromFile(testOutputFilePath);
			rejectInvitation(taskName);

		} catch (Exception e) {
		}

	}

	/**
	 * @author 412766
	 */
	public void rejectInvitation(String taskName) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.click(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			String taskLinkXPath = ".//*[contains(text(),'" + taskName + "')]";
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskLinkXPath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					taskLinkXPath));
			UIHelper.click(driver, taskLinkXPath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys("test");
			UIHelper.click(driver, rejectBtnXpath);
			report.updateTestLog("Reject Task",
					"Task Rejected Successfully <br/><b>Rejected Task:</b>"
							+ taskName, Status.PASS);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Perform Acceptance on Task
	public void performAcceptOnTask(String taskName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys("test");
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, acceptBtnXpath));
			UIHelper.click(driver, acceptBtnXpath);
			report.updateTestLog("Accept Task",
					"Task Accepted Successfully <br/><b>Accepted Task:</b>"
							+ taskName, Status.PASS);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Perform Rejection on Task
	public void performRejectOnTask(String taskName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys("test");
			UIHelper.click(driver, rejectBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Reject Task",
					"Task Rejected Successfully <br/><b>Rejected Task:</b>"
							+ taskName, Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDisplayedAcceptedOrRejectedTaskInActiveList(String taskName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.click(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String taskLinkXPath = ".//*[contains(text(),'" + taskName + "')]";
			if (UIHelper.checkForAnElementbyXpath(driver, taskLinkXPath)) {
				isDisplayedAcceptedOrRejectedTask = true;
			} else {
				isDisplayedAcceptedOrRejectedTask = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedAcceptedOrRejectedTask;
	}

	// Function- To Navigate to Active Tasks
	public AlfrescoTasksPage checkactiveTasks() {
		try {
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, activeTasksXpath);
			UIHelper.click(driver, activeTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Active Tasks",
					"Active Tasks opened sucessfully<br>"
							+ "<b>Tasks Page:</b>Active Tasks", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoTasksPage(scriptHelper);

	}

	// Function-To Change status of active task to
	// complete
	public AlfrescoTasksPage completeTask() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, activeTasksListXpath);
			List<WebElement> activeTasks = driver.findElements(By
					.xpath(activeTasksListXpath));
			if (activeTasks.size() > 0) {
				new FileUtil().writeTextToFile(activeTasks.get(0).getText(),
						testOutputFilePath);
				activeTasks.get(0).click();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				boolean isAcceptBtnDisplayed = UIHelper
						.checkForAnElementbyXpath(driver, acceptBtnXpath);
				boolean isStatusLabelDisplayed = UIHelper
						.checkForAnElementbyXpath(driver, statusLabelXpath);
				if (isStatusLabelDisplayed) {
					Select statusSelectBox = new Select(
							UIHelper.findAnElementbyXpath(driver,
									statusSelectBoxXpath));
					statusSelectBox.selectByValue("Completed");
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							commentBoxXpath2);
					UIHelper.sendKeysToAnElementByXpath(driver,
							commentBoxXpath2, "testing");
					boolean isTaskBtnDisplayed = UIHelper
							.checkForAnElementbyXpath(driver, taskDoneBtnXpath);
					if (isTaskBtnDisplayed)
						UIHelper.click(driver, taskDoneBtnXpath);
					boolean isApproveBtnDisplayed = UIHelper
							.checkForAnElementbyXpath(driver, approveBtnXpath);
					if (isApproveBtnDisplayed)
						UIHelper.click(driver, approveBtnXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					report.updateTestLog("Complete Task",
							"Task completed sucessfully", Status.DONE);
				} else if (isAcceptBtnDisplayed) {
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							commentBoxXpath);
					driver.findElement(By.xpath(commentBoxXpath)).sendKeys(
							"test");
					UIHelper.click(driver, acceptBtnXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					report.updateTestLog("Complete Task",
							"Task completed sucessfully", Status.DONE);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoTasksPage(scriptHelper);
	}

	// Navigate to My tasks menu
	public AlfrescoMyTasksPage navigateToMyTasksMenu() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.click(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.highlightElement(driver, myTasksMenuLinkXpath);
			String myTasksTitle = UIHelper.findAnElementbyXpath(driver,
					myTasksMenuLinkXpath).getText();
			UIHelper.click(driver, myTasksMenuLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					synchronizingRemoteTasksMsgXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingXpathInWorkflowPage);
			UIHelper.waitFor(driver);
			report.updateTestLog("Navigate To My Tasks Menu",
					"Navigated to <br/><b> My Tasks Page:</b>" + myTasksTitle,
					Status.DONE);
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	// Navigate to My tasks page
	public AlfrescoMyTasksPage navigateToMyTasksPage() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.highlightElement(driver, myTasksMenuLinkXpath);
			String myTasksTitle = UIHelper.findAnElementbyXpath(driver,
					myTasksMenuLinkXpath).getText();
			UIHelper.click(driver, myTasksMenuLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					synchronizingRemoteTasksMsgXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingXpathInWorkflowPage);
			UIHelper.waitFor(driver);
			report.updateTestLog("Navigate To My Tasks Page",
					"Navigated to <br/><b> My Tasks Page:</b>" + myTasksTitle,
					Status.DONE);
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	// Navigate to Workflows I'Ve Started
	public AlfrescoMyTasksPage navigateToMyWorkFlows() {
		try {

			UIHelper.highlightElement(driver, myworkFlowMenuOptionXpath);
			UIHelper.click(driver, myworkFlowMenuOptionXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					synchronizingRemoteTasksMsgXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingXpathInWorkflowPage);
			UIHelper.waitFor(driver);
			report.updateTestLog(
					"Navigate To Workflows I'Ve Started",
					"Navigated to Workflows <br><b>Page:</b> WorkFlows I have Started",
					Status.DONE);
		} catch (Exception e) {
			// e.printStackTrace();
			report.updateTestLog("Navigate To Workflows I'Ve Started",
					e.getMessage(), Status.FAIL);
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	// Open task
	public void openCreatedOrAssignedTask() {
		try {
			boolean isDisplayedTask = checkCreatedOrAssignedTask();

			String pageTitle = UIHelper.getTextFromWebElement(driver,
					pageTitleXpath);

			if (pageTitle.contains("Workflows I've Started") && isDisplayedTask) {
				openTaskInMyWorkflows();
			} else if (pageTitle.contains("My Tasks") && isDisplayedTask) {
				openTask();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on View Task option in My Task Page
	public void clickOnViewTaskInMyTasksPage() {
		try {
			boolean isDisplayedTask = checkCreatedOrAssignedTask();

			if (isDisplayedTask) {
				clickOnViewTaskOption();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Task is Created/Assigned
	public boolean checkCreatedOrAssignedTask() {
		boolean isDisplayedWorkflowInPage = false;
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String finalTaskName = taskName + "_" + currentDate;

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			isDisplayedWorkflowInPage = checkTask(finalTaskName);

			if (isDisplayedWorkflowInPage == false) {
				List<WebElement> noOfPagesEle = UIHelper
						.findListOfElementsbyXpath(myTaskPagesXpath, driver);
				int noOfPages = noOfPagesEle.size();
				for (int index = 0; index < noOfPages; index++) {
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					isDisplayedWorkflowInPage = checkTask(finalTaskName);
					if (isDisplayedWorkflowInPage) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedWorkflowInPage;
	}

	// Check Task is Created/Assigned
	public boolean checkCreatedOrAssignedTask(String taskName) {
		boolean isDisplayedWorkflowInPage = false;
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			isDisplayedWorkflowInPage = checkTask(taskName);

			if (isDisplayedWorkflowInPage == false) {
				List<WebElement> noOfPagesEle = UIHelper
						.findListOfElementsbyXpath(myTaskPagesXpath, driver);
				int noOfPages = noOfPagesEle.size();
				for (int index = 0; index < noOfPages; index++) {
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					isDisplayedWorkflowInPage = checkTask(taskName);
					if (isDisplayedWorkflowInPage) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedWorkflowInPage;
	}

	// Check Task is Created/Displayed
	public boolean checkTask(String taskName) {
		boolean isDisplayedTask = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);
			WebElement pageBodyEle = driver
					.findElement(By.xpath(pageBodyXpath));
			UIHelper.highlightElement(driver, pageBodyEle);
			UIHelper.mouseOveranElement(driver, pageBodyEle);

			UIHelper.waitFor(driver);

			String pageTitle = UIHelper.getTextFromWebElement(driver,
					pageTitleXpath);
			if (pageTitle.contains("Workflows I've Started")) {
				isDisplayedTask = checkTaskInMyWorkFlows(taskName);
			} else if (pageTitle.contains("My Tasks")) {
				isDisplayedTask = checkTaskInMyTasksPage(taskName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedTask;
	}

	// Check Task in My Tasks Page
	public boolean checkTaskInMyTasksPage(String taskName) {
		boolean isDisplayedTaskInMyTasksPg = false;
		try {

			if (UIHelper.checkForAnElementbyXpath(driver, filterDueTodayXpath)) {
				UIHelper.click(driver, filterDueTodayXpath);

				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				/*
				 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				 * synchronizingRemoteTasksMsgXpath);
				 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				 * loadingXpathInWorkflowPage);
				 */
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.findandAddElementsToaList(driver, taskslistName,
						tasksList);
				for (String result : tasksList) {
					if (result.contains(taskName)) {
						String finaltaskXpath = temptasklist.replace("CRAFT",
								result);
						WebElement taskEle = UIHelper.findAnElementbyXpath(
								driver, finaltaskXpath);
						UIHelper.highlightElement(driver, taskEle);
						UIHelper.scrollToAnElement(taskEle);
						UIHelper.waitFor(driver);
						isDisplayedTaskInMyTasksPg = true;

						break;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedTaskInMyTasksPg;
	}

	// Check Task in Workflows I've Started Page
	public boolean checkTaskInMyWorkFlows(String taskName) {
		boolean isDisplayedTaskInMyWorkflowsPg = false;
		try {
			/*
			 * WebElement myWorkFlowTableContainerEle = driver.findElement(By
			 * .xpath(myWorkFlowSecXpath)); UIHelper.highlightElement(driver,
			 * myWorkFlowTableContainerEle); UIHelper.mouseOveranElement(driver,
			 * myWorkFlowTableContainerEle);
			 * 
			 * WebElement myWorkFlowsOrTasksContainerEle = driver.findElement(By
			 * .xpath(myWorkflowsOrMyTasksXpath));
			 * UIHelper.highlightElement(driver,
			 * myWorkFlowsOrTasksContainerEle);
			 * UIHelper.mouseOveranElement(driver,
			 * myWorkFlowsOrTasksContainerEle);
			 */

			if (UIHelper.checkForAnElementbyXpath(driver, filterDueTodayXpath)) {
				// UIHelper.click(driver, filterDueTodayXpath);

				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				/*
				 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				 * synchronizingRemoteTasksMsgXpath);
				 */
				// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				// loadingXpathInWorkflowPage);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				List<WebElement> uploadedFileOrFolderTitleEleList = driver
						.findElements(By.xpath(tasksTitlesXpath));
				for (WebElement ele : uploadedFileOrFolderTitleEleList) {
					if (ele.getText().equalsIgnoreCase(taskName)) {
						UIHelper.highlightElement(driver, ele);

						try {
							UIHelper.scrollToAnElement(ele);
						} catch (Exception e) {
							e.printStackTrace();
						}
						UIHelper.waitFor(driver);

						isDisplayedTaskInMyWorkflowsPg = true;
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedTaskInMyWorkflowsPg;
	}

	// Open Task from My Tasks Page
	public void openTask() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String finalTaskName = taskName + "_" + currentDate;

			String finaltaskXpath = temptasklist
					.replace("CRAFT", finalTaskName);

			UIHelper.findAnElementbyXpath(driver, finaltaskXpath).click();
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Open workflow", "Opened workflow using: "
					+ finalTaskName, Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Open Task from My Tasks Page
	public void clickOnViewTaskOption() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");

			String finaltaskXpath = temptasklist.replace("CRAFT", taskName);
			String finalXpathForViewTask = tempXpathForViewTask.replace(
					"CRAFT", taskName);

			WebElement reqTaskEle = UIHelper.findAnElementbyXpath(driver,
					finaltaskXpath);
			UIHelper.mouseOveranElement(driver, reqTaskEle);

			WebElement viewTaskEle = UIHelper.findAnElementbyXpath(driver,
					finalXpathForViewTask);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", viewTaskEle);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog(
					"Click on 'View Task' Option for Assigned Task",
					"Task Details page opened using: " + taskName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Workflow Details Link
	public void clickOnWorkflowDetailsLink() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, workflowDetailsXpath);

			UIHelper.click(driver, workflowDetailsXpath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					viewProcessDiagButtonXpath);

			report.updateTestLog("Click on Workflow Details Link",
					"Opened Workflow Details", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Open Task from My Tasks Page
	public void openSiteTask(String taskName) {
		try {

			String finaltaskXpath = temptasklist.replace("CRAFT", taskName);

			UIHelper.findAnElementbyXpath(driver, finaltaskXpath).click();
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Open workflow", "Opened workflow using: "
					+ taskName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Open Task from Workflows I've Started Page
	public void openTaskInMyWorkflows() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String finalTaskName = taskName + "_" + currentDate;

			JavascriptExecutor executor = (JavascriptExecutor) driver;

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(tasksTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(finalTaskName)) {
					UIHelper.highlightElement(driver, ele);
					executor.executeScript("arguments[0].click();", ele);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);

					report.updateTestLog("Open workflow",
							"Open workflow using: " + taskName, Status.PASS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Open workflow form created workflows
	public AlfrescoMyTasksPage openAssignedWorkFlowFromMyTasks() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			JavascriptExecutor executor = (JavascriptExecutor) driver;

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);

			WebElement myWorkFlowTableContainerEle = driver.findElement(By
					.xpath(myWorkFlowSecXpath));
			UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
			executor.executeScript("arguments[0].click();",
					myWorkFlowTableContainerEle);
			UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);
			UIHelper.waitFor(driver);

			WebElement defaultTaskFilterEle = driver.findElement(By
					.xpath(myTasksTableXpath));
			UIHelper.highlightElement(driver, defaultTaskFilterEle);
			UIHelper.mouseOveranElement(driver, defaultTaskFilterEle);

			String finalTaskLinkOnMyTasksXpath = taskLinkOnMyTasks.replace(
					"CRAFT", taskName);
			try {
				taskLinkEle = driver.findElement(By
						.xpath(finalTaskLinkOnMyTasksXpath));
			} catch (Exception e) {
				e.printStackTrace();
			}

			executor.executeScript("arguments[0].click();", taskLinkEle);

			report.updateTestLog("Open Assigned workflow",
					"Open assigned workflow using: " + taskName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoMyTasksPage(scriptHelper);
	}

	// Check created workflow
	public boolean isDisplayedCreatedWorkFlow() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");

			UIHelper.waitForVisibilityOfEleByXpath(driver, myWorkFlowSecXpath);
			WebElement myWorkFlowTableContainerEle = driver.findElement(By
					.xpath(myWorkFlowSecXpath));
			UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
			UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);
			UIHelper.waitFor(driver);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(tasksTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(taskName)) {
					isDisplayedWorkflow = true;
					break;
				} else {
					isDisplayedWorkflow = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedWorkflow;
	}

	// Get the workflow names
	public ArrayList<String> getWorkflows() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);

			WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver,
					pageBodyXpath);
			UIHelper.highlightElement(driver, pageBodyEle);
			UIHelper.mouseOveranElement(driver, pageBodyEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myWorkFlowSecXpath);
			WebElement myWorkFlowTableContainerEle = driver.findElement(By
					.xpath(myWorkFlowSecXpath));
			UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
			UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);

			WebElement myWorkflowListEle = driver.findElement(By
					.xpath(myWorkflowListXpath));
			UIHelper.highlightElement(driver, myWorkflowListEle);
			UIHelper.mouseOveranElement(driver, myWorkflowListEle);

			UIHelper.waitFor(driver);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(tasksTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				workflowsList.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workflowsList;
	}

	// Get Workflow Started details
	public String getWorkflowStartedDetails() {
		UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver,
				workFlowDetailsContainerXpath));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", UIHelper
				.findAnElementbyXpath(driver, workFlowDetailsContainerXpath));
		UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
				driver, workFlowDetailsContainerXpath));
		UIHelper.highlightElement(driver, workflowStartedDetailsXpath);
		UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
				driver, workflowStartedDetailsXpath));
		return UIHelper.getTextFromWebElement(driver,
				workflowStartedDetailsXpath);
	}

	// Get Workflow Started details
	public String getWorkflowDueDateDetails() {
		UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver,
				workFlowDetailsContainerXpath));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", UIHelper
				.findAnElementbyXpath(driver, workFlowDetailsContainerXpath));
		UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
				driver, workFlowDetailsContainerXpath));

		UIHelper.highlightElement(driver, workflowDueDateDetailsXpath);
		UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
				driver, workflowDueDateDetailsXpath));
		return UIHelper.getTextFromWebElement(driver,
				workflowDueDateDetailsXpath);
	}

	// Get Workflow Started details from WorkFlow summary
	public String getWorkflowDueDateDetailsFromWorkFlowSummary() {
		UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver,
				workFlowDetailsContainerXpath));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", UIHelper
				.findAnElementbyXpath(driver, workFlowDetailsContainerXpath));
		UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
				driver, workFlowDetailsContainerXpath));

		UIHelper.highlightElement(driver, dueDateXpathFormWorkflowSummarySec);
		UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
				driver, dueDateXpathFormWorkflowSummarySec));
		return UIHelper.getTextFromWebElement(driver,
				dueDateXpathFormWorkflowSummarySec).replace("Due on ", "");
	}

	// Get Workflow Started details from Item Field Sec
	public String getWorkflowDueDateDetailsFromItemsFieldSec() {
		UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver,
				workFlowDetailsContainerXpath));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", UIHelper
				.findAnElementbyXpath(driver, workFlowDetailsContainerXpath));
		UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
				driver, workFlowDetailsContainerXpath));

		UIHelper.highlightElement(driver,
				workflowDueDateDetailsXpathFromItemsFieldSec);
		UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
				driver, workflowDueDateDetailsXpathFromItemsFieldSec));
		return UIHelper.getTextFromWebElement(driver,
				workflowDueDateDetailsXpathFromItemsFieldSec);
	}

	// To verify due date is assigned to the Task under My tasks
	public boolean returnDuedateisDisplayed() {
		try {

			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, dueDateLblXpath);
			duedtVal = driver.findElement(By.xpath(dueDateValXPath)).getText();
			if (duedtVal.length() != 0) {
				isDisplayedDueDateVal = true;
			}

			else {
				isDisplayedDueDateVal = false;
			}

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedDueDateVal;
	}

	// To verify Description is assigned to the Task under My tasks
	public String returnDescriptionValforTask() {
		try {

			/*
			 * UIHelper.click(driver, taskMyTasksXpath);
			 * UIHelper.waitForPageToLoad(driver); UIHelper.waitFor(driver);
			 */
			UIHelper.waitForVisibilityOfEleByXpath(driver, descValTaskXPath);

			descValforTask = driver.findElement(By.xpath(descValTaskXPath))
					.getText();

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return descValforTask;
	}

	// To verify Task displays corresponding component if available under My
	// tasks
	public String returnComponenentValforTask() {
		try {

			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					componentValTaskXpath);

			componentValforTask = driver.findElement(
					By.xpath(componentValTaskXpath)).getText();

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return componentValforTask;
	}

	// To verify Task displays corresponding component if available under My
	// tasks
	public boolean CmplteFilterinMyTasksPage() {
		try {

			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, compltdFltrXPath);
			UIHelper.click(driver, compltdFltrXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, viewTaskXPath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return driver.findElement(By.xpath(viewTaskXPath)).isDisplayed();
	}

	// To verify Task displays due Date available under My
	// tasks
	public boolean dueDateFilterinMyTasksPage() {

		try {

			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			/**** Code to check only overdue tasks */
			UIHelper.waitForVisibilityOfEleByXpath(driver, overDueTaskXPath);
			UIHelper.click(driver, overDueTaskXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					dueFltrdTaskDispXPath);
			if (driver.findElement(By.xpath(dueFltrdTaskDispXPath))
					.isDisplayed()) {

				duedteFilterValdisplayed = true;
			}

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog(
					"Click on Due Date filter and Verify the task that are Due are displayed under My Task Page. ",
					"Tasks that are in Due are NOT available to verify. Please check tasks are created and available as per the pre requisite ",
					Status.FAIL);
			e.printStackTrace();
		}

		return duedteFilterValdisplayed;
	}

	// To verify Task displays the assignee details in the Assigned To field in
	// Work flow details under My Tasks Page
	public boolean displayAssigneeinWorkflow() {
		try {

			UIHelper.click(driver, taskMyWrkFlwXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, viewWrkFlwXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, assignedToFldXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, assignedToValXPath);
			UIHelper.highlightElement(driver, assignedToValXPath);
			assignedToVal = UIHelper.findAnElementbyXpath(driver,
					assignedToValXPath).getText();

			if (assignedToVal.length() != 0) {
				isDisplayedAssignedToVal = true;
			}

			else {
				isDisplayedAssignedToVal = false;
			}

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedAssignedToVal;
	}

	// To verify Tasks are displayed when Me filter is selected in 'Assigned To'
	// category under My tasks page
	public boolean assignedToFilterinMyTasksPage() {
		try {

			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, assignFltrXPath);
			UIHelper.click(driver, assignFltrXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, lstTasksMeFlrtXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return driver.findElement(By.xpath(lstTasksMeFlrtXPath)).isDisplayed();
	}

	// Start workflow from tasks page
	public AlfrescoMyTasksPage startWorkFlowFromTasksPage(String taskType,
			String message, String dueDate, String priority) {
		try {
			WebElement myWorkFlowTableContainerEle = driver.findElement(By
					.xpath(myWorkFlowSecXpath));
			UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
			UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					startWorkFlowButtonXpath);
			UIHelper.highlightElement(driver, startWorkFlowButtonXpath);
			UIHelper.click(driver, startWorkFlowButtonXpath);

			submitWorkFlow(taskType, message, dueDate, priority);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	public AlfrescoMyTasksPage startWorkFlowFromTasksPage() {
		try {
			WebElement myWorkFlowTableContainerEle = driver.findElement(By
					.xpath(myWorkFlowSecXpath));
			UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
			UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					startWorkFlowButtonXpath);
			UIHelper.highlightElement(driver, startWorkFlowButtonXpath);
			UIHelper.click(driver, startWorkFlowButtonXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	// Select Task Type dropdown value
	public void selectTaskType(String taskType) {
		try {
			String finalTaskTypeValueXpath = tempWorkFlowDropDownValXpath
					.replace("CRAFT", taskType);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					workflowDropdownListXpath);
			UIHelper.click(driver, workflowDropdownListXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalTaskTypeValueXpath);
			UIHelper.click(driver, finalTaskTypeValueXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, workflowMessageXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void submitWorkFlow(String taskType, String message, String dueDate,
			String priority) {
		try {
			String finalTaskTypeValueXpath = tempWorkFlowDropDownValXpath
					.replace("CRAFT", taskType);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					workflowDropdownListXpath);
			UIHelper.click(driver, workflowDropdownListXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalTaskTypeValueXpath);
			UIHelper.click(driver, finalTaskTypeValueXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, workflowMessageXpath);

			if (dueDate.contains("'")) {
				finalDueDate = dueDate.replace("'", "");
			} else {
				finalDueDate = dueDate;
			}

			UIHelper.sendKeysToAnElementByXpath(driver, workflowMessageXpath,
					message + "_" + finalDueDate);
			UIHelper.sendKeysToAnElementByXpath(driver, wfDueDateXpath,
					finalDueDate);
			WebElement dueDateEle = UIHelper.findAnElementbyXpath(driver,
					wfDueDateXpath);
			if (UIHelper.checkForAnWebElement(dueDateEle)) {
				dueDateEle.sendKeys(Keys.TAB);
			}
			UIHelper.waitFor(driver);

			if (priority != null && !priority.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, wfPriority, priority);
			}

			report.updateTestLog("Input Workflow parameters", "WFType: "
					+ taskType + ", " + "<br>Message: " + message + "_"
					+ finalDueDate + ", " + "Due Date: " + finalDueDate + ", "
					+ "<br>Priority: " + priority, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Start workflow from tasks page
	public boolean getInvalidDueDateFieldErrorMsg(String taskType,
			String message, String dueDate) {
		try {
			WebElement myWorkFlowTableContainerEle = driver.findElement(By
					.xpath(myWorkFlowSecXpath));
			UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
			UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);

			String finalTaskTypeValueXpath = tempWorkFlowDropDownValXpath
					.replace("CRAFT", taskType);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					startWorkFlowButtonXpath);
			UIHelper.highlightElement(driver, startWorkFlowButtonXpath);
			UIHelper.click(driver, startWorkFlowButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					workflowDropdownListXpath);
			UIHelper.click(driver, workflowDropdownListXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalTaskTypeValueXpath);
			UIHelper.click(driver, finalTaskTypeValueXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, workflowMessageXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, workflowMessageXpath,
					message);
			UIHelper.sendKeysToAnElementByXpath(driver, wfDueDateXpath, dueDate);
			UIHelper.waitForVisibilityOfEleByXpath(driver, fieldErrorMsgXpath);

			UIHelper.waitFor(driver);

			UIHelper.highlightElement(driver, fieldErrorMsgXpath);
			WebElement fieldErrorEle = UIHelper.findAnElementbyXpath(driver,
					fieldErrorMsgXpath);

			if (UIHelper.checkForAnWebElement(fieldErrorEle)) {
				isDisplayedFieldErrorVal = true;
				errorMessageVal = fieldErrorEle.getText();
			} else {
				isDisplayedFieldErrorVal = false;
			}

			report.updateTestLog("Input invalid Due date for Workflow",
					"WFType = " + taskType + ", " + "Message = " + message
							+ ", " + "Invalid Due Date = " + dueDate,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFieldErrorVal;
	}

	/**
	 * @author 412766
	 * @param userName
	 */
	public void selectAssigner(String userName) {
		UIHelper.click(driver, wfAssignerXapth);
		UIHelper.waitForVisibilityOfEleByXpath(driver, wfReviewerSearchXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, wfReviewerSearchXpath,
				userName);
		UIHelper.click(driver, wfReviwerSearchbuttonXpath);
		UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
				wfReviewerResultXpath);
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

			report.updateTestLog("Select Workflow Approver", "Username = "
					+ userName, Status.PASS);
		}
	}

	// Select reviewer details for simply task
	public void selectReviewer() {

		String userName = dataTable.getData("Workflow", "UserName");

		if (userName.contains(",")) {
			String userNames[] = userName.split(",");
			selectMultipleReviewers(userNames);
		} else {
			selectSingleReviewer(userName);
		}

	}

	public void selectSingleReviewer(String userName) {
		try {
			UIHelper.click(driver, wfReviewerXapth);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					wfReviewerSearchXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, wfReviewerSearchXpath,
					userName);
			UIHelper.click(driver, wfReviwerSearchbuttonXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
					wfReviewerResultXpath);
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

				report.updateTestLog("Select Workflow Reviewer", "Username = "
						+ userName, Status.PASS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Select Workflow Reviewer", "Username = "
					+ userName + "Failed", Status.FAIL);
		}
	}

	public void selectMultipleReviewers(String[] userNames) {
		try {
			for (String userName : userNames) {
				UIHelper.click(driver, wfReviewerXapth);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						wfReviewerSearchXpath);
				driver.findElement(By.xpath(wfReviewerSearchXpath)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver,
						wfReviewerSearchXpath, userName);
				UIHelper.click(driver, wfReviwerSearchbuttonXpath);
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
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

					report.updateTestLog("Select Workflow Reviewer",
							"Username = " + userName, Status.PASS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Workflow Reviewer",
					"Username addition Failed", Status.FAIL);
		}
	}

	// Select reviewer details for simply task
	public void selectApprover() {

		String userName = dataTable.getData("Workflow", "UserName");

		if (userName.contains(",")) {
			String userNames[] = userName.split(",");
			selectMultipleApprovers(userNames);
		} else {
			selectSingleApprover(userName);
		}

	}

	public void selectSingleApprover(String userName) {
		try {
			UIHelper.click(driver, wfApproverXapth);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					wfApproverSearchXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, wfApproverSearchXpath,
					userName);
			UIHelper.click(driver, wfApproverSearchbuttonXpath);

			// String finalwfApproverResultXpath =
			// wfApproverResultXpath.replace("CRAFT", userName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
					wfApproverAddXpath);

			if (UIHelper.findAnElementbyXpath(driver, wfApproverResultXpath)
					.getText().contains(userName)) {
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						wfApproverAddXpath);
				UIHelper.click(driver, wfApproverAddXpath);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, wfApproverOKXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);

				report.updateTestLog("Select Workflow Approver", "Username = "
						+ userName, Status.PASS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Select Workflow Approver", "Username = "
					+ userName + "failed", Status.FAIL);
		}
	}

	public void selectMultipleApprovers(String[] userNames) {
		try {
			for (String userName : userNames) {
				UIHelper.click(driver, wfApproverXapth);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						wfApproverSearchXpath);
				driver.findElement(By.xpath(wfApproverSearchXpath)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver,
						wfApproverSearchXpath, userName);
				UIHelper.click(driver, wfApproverSearchbuttonXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
						wfApproverAddXpath);
				if (UIHelper
						.findAnElementbyXpath(driver, wfApproverResultXpath)
						.getText().contains(userName)) {
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							wfApproverAddXpath);
					UIHelper.click(driver, wfApproverAddXpath);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, wfApproverOKXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);

					report.updateTestLog("Select Workflow Approver",
							"Username = " + userName, Status.PASS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Workflow Approver",
					"Username addition Failed", Status.PASS);
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

	// Select reviewer details for simply task
	public void selectReviewer(String userName) {

		if (userName.contains(",")) {
			String userNames[] = userName.split(",");
			selectMultipleReviewers(userNames);
		} else {
			selectSingleReviewer(userName);
		}

	}

	// Enter Approval Percentage
	public void inputApprovalPercentage(String approvalPercentageVal) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					approvalPercentageFieldXpath);
			if (UIHelper.checkForAnElementbyXpath(driver,
					approvalPercentageFieldXpath)) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						approvalPercentageFieldXpath, approvalPercentageVal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click start work flow button
	public void clickStartWorkflowBtn() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, startWFbuttonXpath);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, startWFbuttonXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					startWFbuttonXpath));
			report.updateTestLog(
					"Click on 'Start Workflow' Button and submit workflow",
					"Clicked the 'Start Workflow' button and submitted the workflow",
					Status.PASS);
			UIHelper.click(driver, startWFbuttonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			String currentDate = new DateUtil().getDayOfWeekAndDate();
			new FileUtil().writeTextToFile(currentDate,
					testOutputForCreatedWorkFlow);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			// UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
			// documentLibXpath);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Click on 'Start Workflow' Button and submit workflow",
					"failed to Click the 'Start Workflow' button and submitted the workflow",
					Status.FAIL);
		}
	}

	// Cancel Created Workflow
	public AlfrescoMyTasksPage cancelCreatedWorkflow() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();

			if (taskName.contains(";")) {
				String splittedTaskDetailas[] = taskName.split(";");
				for (String taskValue : splittedTaskDetailas) {
					commonMethodForCancelTask(taskValue + "_" + currentDate);
				}
			} else {
				commonMethodForCancelTask(taskName + "_" + currentDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	public AlfrescoMyTasksPage commonMethodForCancelTask(String taskName) {
		try {

			boolean isDisplayedReqTask = checkCreatedOrAssignedTask(taskName);
			if (isDisplayedReqTask) {
				String finalXpathForCancelTaskLink = tempXpathForCancelTaskLink
						.replace("CRAFT", taskName);

				WebElement myWorkFlowTableContainerEle = driver.findElement(By
						.xpath(myWorkFlowSecXpath));
				UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
				UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);
				UIHelper.waitFor(driver);

				List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
						.findListOfElementsbyXpath(tasksTitlesXpath, driver);

				for (WebElement ele : uploadedFileOrFolderTitleEleList) {
					if (ele.getText().equalsIgnoreCase(taskName)) {
						UIHelper.highlightElement(driver, ele);
						UIHelper.mouseOveranElement(driver, ele);
						UIHelper.waitFor(driver);
						WebElement calceTaskEle = UIHelper
								.findAnElementbyXpath(driver,
										finalXpathForCancelTaskLink);
						UIHelper.highlightElement(driver, calceTaskEle);
						JavascriptExecutor executor = (JavascriptExecutor) driver;
						executor.executeScript("arguments[0].click();",
								calceTaskEle);
						UIHelper.waitFor(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								yesButtonXpathInCancelWorkflow);
						UIHelper.click(driver, yesButtonXpathInCancelWorkflow);
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								messageDivXpath);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
								messageDivXpath);
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);

						report.updateTestLog("Cancel a Task",
								"Cancel a task using: "
										+ "<br><b>TaskName:</b> " + taskName,
								Status.DONE);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	// Cancel Created Workflow
	public AlfrescoMyTasksPage cancelCreatedWorkflowNoReport() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();

			if (taskName.contains(";")) {
				String splittedTaskDetailas[] = taskName.split(";");
				for (String taskValue : splittedTaskDetailas) {
					commonMethodForCancelTaskNoReport(taskValue + "_"
							+ currentDate);
				}
			} else {
				commonMethodForCancelTaskNoReport(taskName + "_" + currentDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	public AlfrescoMyTasksPage commonMethodForCancelTaskNoReport(String taskName) {
		try {

			boolean isDisplayedReqTask = checkCreatedOrAssignedTask();
			if (isDisplayedReqTask) {
				String finalXpathForCancelTaskLink = tempXpathForCancelTaskLink
						.replace("CRAFT", taskName);

				WebElement myWorkFlowTableContainerEle = driver.findElement(By
						.xpath(myWorkFlowSecXpath));
				UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
				UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);
				UIHelper.waitFor(driver);

				List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
						.findListOfElementsbyXpath(tasksTitlesXpath, driver);

				for (WebElement ele : uploadedFileOrFolderTitleEleList) {
					if (ele.getText().equalsIgnoreCase(taskName)) {
						UIHelper.highlightElement(driver, ele);
						UIHelper.mouseOveranElement(driver, ele);
						UIHelper.waitFor(driver);
						WebElement calceTaskEle = UIHelper
								.findAnElementbyXpath(driver,
										finalXpathForCancelTaskLink);
						UIHelper.highlightElement(driver, calceTaskEle);
						JavascriptExecutor executor = (JavascriptExecutor) driver;
						executor.executeScript("arguments[0].click();",
								calceTaskEle);
						UIHelper.waitFor(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								yesButtonXpathInCancelWorkflow);
						UIHelper.click(driver, yesButtonXpathInCancelWorkflow);
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								messageDivXpath);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
								messageDivXpath);
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);

						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	// View File is Attached to the File
	public String retFileAttachedtoTask() {
		try {
			String taskNmeStr = dataTable.getData("Tasks", "TaskName");
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myWorkFlowSecXpath);

			WebElement myWorkFlowTableContainerEle = driver.findElement(By
					.xpath(myWorkFlowSecXpath));
			UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
			UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskslstXpathvalue);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(taskslstXpathvalue, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(taskNmeStr)) {
					UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", ele);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							attchdFldEdtXPath);
					attachedFileTxt = driver.findElement(
							By.xpath(attchdFldEdtXPath)).getText();
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
				}
			}

			report.updateTestLog("Open workflow", "Open workflow using: "
					+ taskNmeStr, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return attachedFileTxt;
	}

	// Edit task and add comment and approve task
	public void editTaskFromMyTasksPage() {
		try {
			String reviewerComment = dataTable.getData("Tasks",
					"ReviewerComment");

			String status = dataTable.getData("Tasks", "Status");

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * tasksEditButtonXpath); WebElement editBtnEle =
			 * UIHelper.findAnElementbyXpath(driver, tasksEditButtonXpath);
			 * UIHelper.highlightElement(driver, editBtnEle);
			 * UIHelper.clickanElement(editBtnEle);
			 * 
			 * UIHelper.waitForPageToLoad(driver);
			 */

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					taskStatusDropdownXpath);

			if (status != null && !status.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, taskStatusDropdownXpath,
						status);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					editTaskReviewCommentsTAreaXpath);

			WebElement commentTAreaEle = UIHelper.findAnElementbyXpath(driver,
					editTaskReviewCommentsTAreaXpath);
			UIHelper.highlightElement(driver, commentTAreaEle);

			commentTAreaEle.sendKeys(reviewerComment);
			UIHelper.waitFor(driver);

			WebElement approveOrTaskDoneBtnEle = UIHelper.findAnElementbyXpath(
					driver, approveButtonXpathInReviewTaskPage);
			UIHelper.highlightElement(driver, approveOrTaskDoneBtnEle);

			UIHelper.clickanElement(approveOrTaskDoneBtnEle);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog(
					"Verify Assigned Task",
					"Reviewed the assigned task and approved/completed the task by adding comments: "
							+ reviewerComment, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param approveOrReject
	 */
	public void commonMethodForAcceptOrRejectTask(String approveOrReject) {
		try {
			String reviewerComment = dataTable.getData("Tasks",
					"ReviewerComment");
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String finalTaskName = taskName + "_" + currentDate;
			String status = dataTable.getData("Tasks", "Status");

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					taskStatusDropdownXpath);

			if (status != null && !status.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, taskStatusDropdownXpath,
						status);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					editTaskReviewCommentsTAreaXpath);

			WebElement commentTAreaEle = UIHelper.findAnElementbyXpath(driver,
					editTaskReviewCommentsTAreaXpath);
			UIHelper.highlightElement(driver, commentTAreaEle);

			commentTAreaEle.sendKeys(reviewerComment);
			UIHelper.waitFor(driver);

			String finalXpath = tempXpathForApproveOrRejectTaskButton.replace(
					"CRAFT", approveOrReject);
			WebElement approveOrTaskDoneBtnEle = UIHelper.findAnElementbyXpath(
					driver, finalXpath);
			UIHelper.highlightElement(driver, approveOrTaskDoneBtnEle);

			if (UIHelper.checkForAnWebElement(approveOrTaskDoneBtnEle)) {
				UIHelper.highlightElement(driver, finalXpath);
				report.updateTestLog(
						approveOrReject + " a Task",
						"User able to " + approveOrReject
								+ " a Task successfully"
								+ "<br /><b>Tasks Name : </b> " + finalTaskName,
						Status.PASS);
				UIHelper.clickanElement(approveOrTaskDoneBtnEle);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
			} else {
				report.updateTestLog(
						approveOrReject + " a Task",
						"User not able to " + approveOrReject + " a Task"
								+ "<br /><b>Tasks Name : </b> " + finalTaskName,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(approveOrReject + " a Task", approveOrReject
					+ " a Task Failed", Status.FAIL);
		}
	}

	// Click on completed tasks link
	public void clickOnCompletedTasks() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					completedTasksLinkXpath);
			UIHelper.click(driver, completedTasksLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					completedTasksTabXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on 'Completed' filter for Tasks",
					"List of Completed status Tasks displyed sucessfully<br>"
							+ "<b>Tasks Page:</b>Completed Tasks", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the Respons comment tasks value
	public String getTasksResponseComment() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, responseCommentXpath);
		return UIHelper.getTextFromWebElement(driver, responseCommentXpath);
	}

	// Get the tasks status value
	public String getTasksStatus() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, statusXpath);
		return UIHelper.getTextFromWebElement(driver, statusXpath);
	}

	// Get tasks filter title
	public String getTasksFileteredTitle() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, tasksFilteredTitleXpath);
		return UIHelper.getTextFromWebElement(driver, tasksFilteredTitleXpath);
	}

	// Get the tasks priority value
	public String getTasksPriority() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, priorityXpath);
		return UIHelper.getTextFromWebElement(driver, priorityXpath);
	}

	// Delete completed Task
	public void deleteCompletedTask() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					workFlowDetailsLinkXpath);
			UIHelper.click(driver, workFlowDetailsLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					deleteWorkFlowBtnXpath);
			UIHelper.click(driver, deleteWorkFlowBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					deleteCompWorkFlowConfirmMsgYesXpath);
			UIHelper.click(driver, deleteCompWorkFlowConfirmMsgYesXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageDivXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					messageDivXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {

		}
	}

	// Delete completed Task
	public void deleteCompletedTaskFromWorkflowStartPage() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					deleteWorkFlowBtnXpath);
			UIHelper.click(driver, deleteWorkFlowBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					deleteCompWorkFlowConfirmMsgYesXpath);
			UIHelper.click(driver, deleteCompWorkFlowConfirmMsgYesXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageDivXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					messageDivXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {

		}
	}

	public AlfrescoTasksPage rejectTask() {

		try {
			String temp = new FileUtil().readDataFromFile(testOutputFilePath);
			String taskName = "Invitation to join " + temp + " site";
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, activeTask);
			UIHelper.waitForVisibilityOfEleByXpath(driver, activeTasksListXpath);
			List<WebElement> tasks = driver.findElements(By
					.xpath(activeTasksListXpath));
			for (WebElement task : tasks) {
				if (taskName.equals(task.getText())) {
					UIHelper.scrollToAnElement(task);
					UIHelper.highlightElement(driver, task);
					UIHelper.clickanElement(task);
					UIHelper.waitFor(driver);
					found = true;
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							commentBoxXpath);
					driver.findElement(By.xpath(commentBoxXpath)).sendKeys(
							"test");
					report.updateTestLog(
							"Add the Comments to the Task and Reject Task-> "
									+ taskName,
							"Comments-> test added Successfully", Status.PASS);
					UIHelper.click(driver, rejectBtnXpath);

					report.updateTestLog("Reject the Task -> " + taskName,
							"Task Rejected Successfully", Status.PASS);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					break;
				}
			}

		} catch (Exception e) {
			report.updateTestLog(
					"Find the Task and Reject",
					"Failed to Reject Task.Please verify Active is created and avaialble to Reject.",
					Status.FAIL);

		}

		return new AlfrescoTasksPage(scriptHelper);
	}

	public void openCompletedTask() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, CompletedTasksXpath);
			UIHelper.click(driver, CompletedTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			String temp = new FileUtil().readDataFromFile(testOutputFilePath);
			String taskName = "Invitation to join " + temp + " site";
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
					UIHelper.clickanElement(task);
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
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException("Completed Tasks",
				 * " Failed to find Completed Task");
				 */
				report.updateTestLog("Completed Tasks",
						"Failed to find Completed Task", Status.FAIL);
			}

		} catch (Exception e) {
			/*
			 * frameworkParameters.setStopExecution(true); throw new
			 * FrameworkException("Completed Tasks",
			 * " Failed to find Completed Task");
			 */
			report.updateTestLog("Completed Tasks",
					"Failed to find Completed Task", Status.FAIL);

		}
	}

	public void navigateToWorkflowDetails() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, workflowDetailsXpath);
			UIHelper.click(driver, workflowDetailsXpath);
			report.updateTestLog("navigate to wrokflow deatils",
					"navigated to workflow details", Status.DONE);
		} catch (Exception e) {

		}

	}

	public void openTaskByUnassignedUser() {
		try {
			UIHelper.waitForPageToLoad(driver);
			String temp = new FileUtil().readDataFromFile(testOutputFilePath);
			String taskName = "Invitation to join " + temp + " site";
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, activeTasksXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, activeTasksListXpath);
			List<WebElement> tasks = driver.findElements(By
					.xpath(activeTasksListXpath));
			for (WebElement task : tasks) {
				if (taskName.equals(task.getText())) {
					UIHelper.scrollToAnElement(task);
					UIHelper.highlightElement(driver, task);
					UIHelper.clickanElement(task);
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
				report.updateTestLog(
						"Verify the Unassigned user is not able to open the Task ->"
								+ taskName, "Task Found in the Tasks list",
						Status.FAIL);
			} else {
				report.updateTestLog(
						"Verify the Unassigned user is not able to open the Task ->"
								+ taskName,
						" User not able to find the Task in My Tasks list ",
						Status.PASS);
			}

		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void openAssignedTask() {
		try {

			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, assignedToMeXpath);
			UIHelper.click(driver, assignedToMeXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> assignedTasks = driver.findElements(By
					.xpath(assignedTasksListXpath));
			String assignedTaskTiltle = assignedTasks.get(0).getText();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			new FileUtil().writeTextToFile(assignedTaskTiltle,
					testOutputFilePath);
			assignedTasks.get(0).click();
			UIHelper.waitFor(driver);
			report.updateTestLog("Open the Assigned Task->"
					+ assignedTaskTiltle, "Assigned Task Opened sucessfully",
					Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Open the Assigned Task",
					"Opening Assigned Task Failed. Task Not found", Status.FAIL);
		}

	}

	public void selectViewMoreActionsLink() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					viewMoreActionsLinkXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					viewMoreActionsLinkXpath));
			UIHelper.click(driver, viewMoreActionsLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("To select view more action link",
					"Link selected successfully", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("To select view more action link",
					e.getMessage(), Status.FAIL);
		}
	}

	public void completeEditedTask() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editTaskTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskDoneXpath);
			UIHelper.highlightElement(driver,
					driver.findElement(By.xpath(taskDoneXpath)));
			UIHelper.waitFor(driver);
			UIHelper.click(driver, taskDoneXpath);
		} catch (Exception e) {
		}
	}

	// To get Tasks from My Tasks{
	public ArrayList<String> getTasksFromMyTasks() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingXpathInWorkflowPage);
			UIHelper.waitForVisibilityOfEleByXpath(driver, tasksSectionXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingXpathInWorkflowPage);
			UIHelper.waitFor(driver);
			List<WebElement> createdTasksEleList = driver.findElements(By
					.xpath(createdTasksListXpathFromMyTasks));

			for (WebElement ele : createdTasksEleList) {
				createdTasksNameList.add(ele.getText());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return createdTasksNameList;
	}

	public void acceptorRejectWFTask(String Msg) {

		try {
			String finalTaskNameXpath = tempTaskNameXpath.replace("CRAFT", Msg);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalTaskNameXpath);
			UIHelper.findandAddElementsToaList(driver, taskNameXpath,
					createdTasksNameList);
			// String Msg = dataTable.getData("Workflow", "Message");

			for (String WFtask : createdTasksNameList) {

				if (WFtask.contains(Msg)) {

					UIHelper.highlightElement(driver, finalTaskNameXpath);
					UIHelper.click(driver, finalTaskNameXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					break;
				}
			}
			UIHelper.highlightElement(driver, wFtaskDoneBtnXpath);
			UIHelper.click(driver, wFtaskDoneBtnXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Open Assigned Task",
					"Assigned Task Opened sucessfully", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Open Assigned Task",
					"Opening Assigned Task Failed", Status.FAIL);
		}

	}

	public ArrayList<String> getAllCompletedTasks() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, CompletedTasksXpath);
			UIHelper.click(driver, CompletedTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					completedTasksListXpath);
			List<WebElement> completedTasks = driver.findElements(By
					.xpath(completedTasksListXpath));
			System.out.println(completedTasks.size());
			for (WebElement task : completedTasks) {
				completedTasksNameList.add(task.getText());
				System.out.println(task.getText());
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return completedTasksNameList;
	}

	// to complete the assigned task
	public void completeAssignedTask() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editTaskTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskDoneBtnXpath);
			UIHelper.highlightElement(driver,
					driver.findElement(By.xpath(taskDoneBtnXpath)));
			Thread.sleep(1000);
			if (UIHelper.findAnElementbyXpath(driver, taskDoneBtnXpath)
					.isDisplayed()) {
				report.updateTestLog(
						"Verify Accept/Task Done button presence and Click",
						"Accept/Task Done button is Visible and Clicked successfully",
						Status.PASS);
				UIHelper.click(driver, taskDoneBtnXpath);
			}

		} catch (Exception e) {
			report.updateTestLog(
					"Verify Accept/Task Done button presence and Click",
					"Accept/Task Done button is NOT available to Click",
					Status.FAIL);
			// UIHelper.click(driver, taskDoneBtnXpath);

		}
	}

	// to complete the assigned task with comments
	public void completeAssignedTaskWithComments(String comment) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editTaskTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskDoneBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys(comment);
			UIHelper.highlightElement(driver,
					driver.findElement(By.xpath(taskDoneBtnXpath)));
			Thread.sleep(1000);
			if (UIHelper.findAnElementbyXpath(driver, taskDoneBtnXpath)
					.isDisplayed()) {
				report.updateTestLog(
						"Verify Accept/Task Done button presence and Click",
						"Accept/Task Done button is Visible and Clicked successfully",
						Status.PASS);
				UIHelper.click(driver, taskDoneBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
			}

		} catch (Exception e) {
			report.updateTestLog(
					"Verify Accept/Task Done button presence and Click",
					"Accept/Task Done button is NOT available to Click",
					Status.FAIL);
			// UIHelper.click(driver, taskDoneBtnXpath);

		}
	}

	public AlfrescoMyTasksPage openAssignedWorkflow() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			UIHelper.waitFor(driver);
			JavascriptExecutor executor = (JavascriptExecutor) driver;

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);

			WebElement myWorkFlowTableContainerEle = driver.findElement(By
					.xpath(myWorkFlowSecXpath));
			UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
			executor.executeScript("arguments[0].click();",
					myWorkFlowTableContainerEle);
			UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);
			UIHelper.waitFor(driver);

			WebElement defaultTaskFilterEle = driver.findElement(By
					.xpath(myTasksTableXpath));
			UIHelper.highlightElement(driver, defaultTaskFilterEle);
			UIHelper.mouseOveranElement(driver, defaultTaskFilterEle);

			String finalTaskLinkOnMyTasksXpath = reviewAssetTaskXpath.replace(
					"CRAFT", taskName);
			String finalTaskTypeXpath = taskTypeXpath
					.replace("CRAFT", taskName);

			String taskNametitle = UIHelper.getTextFromWebElement(driver,
					finalTaskLinkOnMyTasksXpath);
			UIHelper.highlightElement(driver, taskNametitle);
			String taskType = UIHelper.getTextFromWebElement(driver,
					finalTaskTypeXpath);

			try {
				if (taskNametitle.equalsIgnoreCase(taskName)
						&& taskType.equalsIgnoreCase("Review Asset"))
					UIHelper.click(driver, finalTaskLinkOnMyTasksXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			report.updateTestLog("Open Assigned workflow",
					"Assigned workflow :<br><b>Task Name</b> " + taskName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoMyTasksPage(scriptHelper);
	}

	// Reject Task
	public void rejectWorkflowApproveTask() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys("Rejected");
			UIHelper.waitForVisibilityOfEleByXpath(driver, rejectButtonXpath);
			UIHelper.highlightElement(driver, rejectButtonXpath);
			String rejectButton = UIHelper.findAnElementbyXpath(driver,
					rejectButtonXpath).getText();
			report.updateTestLog("Reject Task",
					" Task Rejected sucessfully<br/><b>Expected Result:</b>"
							+ rejectButton + "<br/><b>Actual Result</b>"
							+ rejectButton, Status.PASS);
			UIHelper.click(driver, rejectButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			/*
			 * UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
			 * startWorkflowBtn);
			 */

		} catch (Exception e) {
			report.updateTestLog("Reject Task", "Reject Task Failed",
					Status.FAIL);
		}

	}

	// Reject Task with Comments
	public void rejectWorkflowApproveTask(String comment) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys(comment);
			UIHelper.waitForVisibilityOfEleByXpath(driver, rejectButtonXpath);
			UIHelper.highlightElement(driver, rejectButtonXpath);
			String rejectButton = UIHelper.findAnElementbyXpath(driver,
					rejectButtonXpath).getText();
			report.updateTestLog("Reject Task",
					" Task Rejected sucessfully<br/><b>Expected Result:</b>"
							+ rejectButton + "<br/><b>Actual Result</b>"
							+ rejectButton, Status.PASS);
			UIHelper.click(driver, rejectButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Reject Task", "Reject Task Failed",
					Status.FAIL);
		}

	}

	public void approveTask() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys("Approved");
			UIHelper.waitForVisibilityOfEleByXpath(driver, approveBtnXpath);
			UIHelper.highlightElement(driver, approveBtnXpath);
			String approveBtn = UIHelper.findAnElementbyXpath(driver,
					approveBtnXpath).getText();
			report.updateTestLog("Approve Task",
					" Task Approved sucessfully<br/><b>Expected Result:</b>"
							+ approveBtn + "<br/><b>Actual Result</b>"
							+ approveBtn, Status.PASS);
			UIHelper.click(driver, approveBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Approve Task", "Approve Task Failed",
					Status.FAIL);
		}

	}

	// To approve a task with comments
	public void approveTaskWithComments(String comment) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys(comment);
			UIHelper.waitForVisibilityOfEleByXpath(driver, approveBtnXpath);
			UIHelper.highlightElement(driver, approveBtnXpath);
			String approveBtn = UIHelper.findAnElementbyXpath(driver,
					approveBtnXpath).getText();
			report.updateTestLog("Approve Task",
					" Task Approved sucessfully<br/><b>Expected Result:</b>"
							+ approveBtn + "<br/><b>Actual Result</b>"
							+ approveBtn, Status.PASS);
			UIHelper.click(driver, approveBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Approve Task", "Approve Task Failed",
					Status.FAIL);
		}

	}

	public ArrayList<String> getTaskTypeDropdownOption() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, lstWrkTypesBtnXPath);
			UIHelper.click(driver, lstWrkTypesBtnXPath);

			taskTypeDropdownValues.add(UIHelper.findAnElementbyXpath(driver,
					lstWrkTypesSimplTaskXPath).getText());
			taskTypeDropdownValues.add(UIHelper.findAnElementbyXpath(driver,
					lstWrkTypesSimplRevApprTaskXPath).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return taskTypeDropdownValues;
	}

	public boolean isDisplayedAssignedTask() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			List<WebElement> listOfTasksDueDates = driver.findElements(By
					.xpath(taskLinksXpath));
			for (WebElement ele : listOfTasksDueDates) {
				if (ele.getText()
						.equalsIgnoreCase(taskName + "_" + currentDate)) {
					UIHelper.highlightElement(driver, ele);
					isDispTask = true;
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					break;
				} else {
					isDispTask = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDispTask;
	}

	public void contRevApprveWorkflowTask() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys("test");
			UIHelper.waitForVisibilityOfEleByXpath(driver, contRevBtnXPath);

			if (UIHelper.checkForAnElementbyXpath(driver, contRevBtnXPath)) {
				UIHelper.highlightElement(driver, contRevBtnXPath);
				UIHelper.click(driver, contRevBtnXPath);
				report.updateTestLog(
						"Click on 'Continue Review and Approve Task'",
						" Task 'Continue Review and Approve Task' displayed sucessfully",
						Status.PASS);
				UIHelper.waitForPageToLoad(driver);
			} else {
				report.updateTestLog(
						"Click on 'Continue Review and Approve Task'",
						" Task 'Continue Review and Approve Task' not displayed",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Click on 'Continue Review and Approve Task'",
					"Click on 'Continue Review and Approve Task' Failed",
					Status.FAIL);
		}

	}

	public void openTaskInMyTaskPge() {
		String taskName = dataTable.getData("Workflow", "Message");
		String currentDate = new DateUtil().getCurrentDate();
		String finalTaskName = taskName + "_" + currentDate;
		System.out.println("" + finalTaskName);
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, tasksSectionXpath);
			UIHelper.waitFor(driver);
			List<WebElement> createdTasksEleList = driver.findElements(By
					.xpath(createdTasksListXpathFromMyTasks));

			for (WebElement ele : createdTasksEleList) {
				createdTasksNameList.add(ele.getText());
				System.out.println(ele.getText());
			}

			for (WebElement ele : createdTasksEleList) {
				if (ele.getText().equalsIgnoreCase(finalTaskName)) {
					ele.click();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Navigate to Active Workflows
	public void navigateToActiveWorkFlows() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, activeWorkflowXpath);
			UIHelper.click(driver, activeWorkflowXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					activeWorkflowHeadlineXpath);
			UIHelper.highlightElement(driver, activeWorkflowHeadlineXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog(
					"Active Workflows",
					"Navigate to Active Workflows sucessfully <br><b>Expected Result:</b> Active WorkFlows"
							+ "<br><b>Actual Result:</b> Active Workflows Page",
					Status.PASS);
		} catch (Exception e) {
			report.updateTestLog(
					"Active Workflows",
					"Unable to Active Workflows <br><b>Expected Result:</b> Active WorkFlows",
					Status.FAIL);
		}

	}

	// To Navigate to Completed Workflows
	public void navigateToCompletedWorkFlows() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					completedWorkflowXpath);
			UIHelper.click(driver, completedWorkflowXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					completedWorkflowHeadlineXpath);
			UIHelper.highlightElement(driver, completedWorkflowHeadlineXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog(
					"Completed Workflows",
					"Navigate to Completed Workflows sucessfully <br><b>Expected Result:</b> Completed WorkFlows"
							+ "<br><b>Actual Result:</b> Completed Workflows Page",
					Status.PASS);
		} catch (Exception e) {
			report.updateTestLog(
					"Completed Workflows",
					"Unable to Completed Workflows <br><b>Expected Result:</b> Completed WorkFlows",
					Status.FAIL);
		}

	}

	// Navigate to Workflows I'Ve Started
	public AlfrescoMyTasksPage navigateToMyWorkFlowsNoReport() {
		try {

			UIHelper.highlightElement(driver, myworkFlowMenuOptionXpath);
			UIHelper.click(driver, myworkFlowMenuOptionXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return new AlfrescoMyTasksPage(scriptHelper);
	}

	// Filter work flow task
	public void filterWFtasks() {
		try {
			String filter = dataTable.getData("Tasks", "Status");
			String finalfilterXpath = filterWFXpath.replace("CRAFT", filter);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfilterXpath);
			UIHelper.click(driver, finalfilterXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingXpathInWorkflowPage);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			/*
			 * report.updateTestLog( "Active Workflows",
			 * "Navigate to Active Workflows sucessfully <br><b>Expected Result:</b> Active WorkFlows"
			 * + "<br><b>Actual Result:</b> Active Workflows Page",
			 * Status.PASS);
			 */
		} catch (Exception e) {
			/*
			 * report.updateTestLog( "Active Workflows",
			 * "Unable to Active Workflows <br><b>Expected Result:</b> Active WorkFlows"
			 * , Status.FAIL);
			 */
		}

	}

	/**
	 * @author 412766
	 */
	public void waitForTaskWithoutDueDateHeader() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					taskWithoutDueDateXpath);
			UIHelper.highlightElement(driver, taskWithoutDueDateXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openWorkFlowTask() {
		try {
			UIHelper.waitforPresenceOfAllElements(driver, taskNameXpath);
			UIHelper.findandAddElementsToaList(driver, taskNameXpath,
					createdTasksNameList);
			String Msg = dataTable.getData("Workflow", "Message");
			for (String WFtask : createdTasksNameList) {
				if (WFtask.contains(Msg)) {
					String finalTaskNameXpath = tempTaskNameXpath.replace(
							"CRAFT", WFtask);
					UIHelper.highlightElement(driver, finalTaskNameXpath);
					UIHelper.click(driver, finalTaskNameXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					break;
				}
			}
		} catch (Exception e) {

		}

	}

	public void taskDoneWFTask() {

		try {
			UIHelper.waitforPresenceOfAllElements(driver, taskNameXpath);
			UIHelper.findandAddElementsToaList(driver, taskNameXpath,
					createdTasksNameList);
			String Msg = dataTable.getData("Workflow", "Message");
			for (String WFtask : createdTasksNameList) {
				if (WFtask.contains(Msg)) {
					String finalTaskNameXpath = tempTaskNameXpath.replace(
							"CRAFT", WFtask);
					UIHelper.highlightElement(driver, finalTaskNameXpath);
					UIHelper.click(driver, finalTaskNameXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					break;
				}
			}
			UIHelper.highlightElement(driver, wFtaskDoneBtnXpath);
			UIHelper.click(driver, wFtaskDoneBtnXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Open Assigned Task and Approve",
					"Assigned Task Opened and approved sucessfully",
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Open Assigned Task",
					"Opening Assigned Task Failed", Status.FAIL);
		}

	}

	// Return the Time Stamp
	public String getTimeStampFromWF() {
		String timeStmpVal = "";
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, timeStampHisXPath);

			WebElement timeStmpEle = UIHelper.findAnElementbyXpath(driver,
					timeStampHisXPath);
			UIHelper.highlightElement(driver, timeStmpEle);
			UIHelper.mouseOveranElement(driver, timeStmpEle);
			timeStmpVal = UIHelper.findAnElementbyXpath(driver,
					timeStampHisXPath).getText();

			UIHelper.waitForVisibilityOfEleByXpath(driver, myWorkFlowSecXpath);
			WebElement myWorkFlowTableContainerEle = driver.findElement(By
					.xpath(myWorkFlowSecXpath));
			UIHelper.highlightElement(driver, myWorkFlowTableContainerEle);
			UIHelper.mouseOveranElement(driver, myWorkFlowTableContainerEle);

			WebElement myWorkflowListEle = driver.findElement(By
					.xpath(myWorkflowListXpath));
			UIHelper.highlightElement(driver, myWorkflowListEle);
			UIHelper.mouseOveranElement(driver, myWorkflowListEle);

			UIHelper.waitFor(driver);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(tasksTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				workflowsList.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeStmpVal;
	}

	// Edit task and add comment and approve task
	public void saveTaskFromMyTasksPage() {
		try {
			String reviewerComment = dataTable.getData("Tasks",
					"ReviewerComment");

			String status = dataTable.getData("Tasks", "Status");

			UIHelper.waitforPresenceOfAllElements(driver, taskNameXpath);
			UIHelper.findandAddElementsToaList(driver, taskNameXpath,
					createdTasksNameList);
			String Msg = dataTable.getData("Workflow", "Message");
			for (String WFtask : createdTasksNameList) {
				if (WFtask.contains(Msg)) {
					String finalTaskNameXpath = tempTaskNameXpath.replace(
							"CRAFT", WFtask);
					UIHelper.highlightElement(driver, finalTaskNameXpath);
					UIHelper.click(driver, finalTaskNameXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					break;
				}
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					taskStatusDropdownXpath);

			if (status != null && !status.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, taskStatusDropdownXpath,
						status);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					editTaskReviewCommentsTAreaXpath);

			WebElement commentTAreaEle = UIHelper.findAnElementbyXpath(driver,
					editTaskReviewCommentsTAreaXpath);
			UIHelper.highlightElement(driver, commentTAreaEle);

			commentTAreaEle.sendKeys(reviewerComment);
			UIHelper.waitFor(driver);

			WebElement saveBtnEle = UIHelper.findAnElementbyXpath(driver,
					saveBtnXPath);
			UIHelper.highlightElement(driver, saveBtnEle);
			report.updateTestLog("Save and Close the  Assigned Task",
					"Save the assigned task and closed the task by adding comments: "
							+ reviewerComment, Status.PASS);

			UIHelper.clickanElement(saveBtnEle);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Save and Close the  Assigned Task",
					"Unable to edit the task ", Status.FAIL);
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param filter
	 */
	public void filterSharedFiles(String filter) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalfilterXpath = filterWFXpath.replace("CRAFT", filter);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfilterXpath);
			UIHelper.highlightElement(driver, finalfilterXpath);
			UIHelper.click(driver, finalfilterXpath);
			UIHelper.waitForPageToLoad(driver);
			Thread.sleep(2000);
		} catch (Exception e) {
		}
	}

	// Open task
	public void openCreatedOrAssignedTask(String taskName) {
		try {
			boolean isDisplayedTask = checkCreatedOrAssignedTask(taskName);

			String pageTitle = UIHelper.getTextFromWebElement(driver,
					pageTitleXpath);
			System.out.println(pageTitle);
			if (pageTitle.contains("Workflows I've Started") && isDisplayedTask) {
				openTaskInMyWorkflows();
			} else if (pageTitle.contains("My Tasks") && isDisplayedTask) {
				openSiteTask(taskName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Filter work flow task
	public void filter(String filter) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalfilterXpath = filterWFXpath.replace("CRAFT", filter);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfilterXpath);
			UIHelper.highlightElement(driver, finalfilterXpath);
			UIHelper.click(driver, finalfilterXpath);
			UIHelper.waitForPageToLoad(driver);
			Thread.sleep(2000);
			
			  report.updateTestLog( "Filter Applied",
			  "Filter of filers successfully <br><b>Filter Name: </b> " +filter,
			  Status.PASS);
			 
		} catch (Exception e) {
			report.updateTestLog( "Filter Applied",
					  "Filter Applied failed <br><b>Filter Name: </b> " +filter,
					  Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileOrFolderName
	 */
	public void checkForEditTaskFile(String fileOrFolderName) {
		try {
			String finalXpath = tempEditTaskFileXpath.replace("CRAFT",
					fileOrFolderName);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpath)) {
				UIHelper.highlightElement(driver, finalXpath);
				report.updateTestLog("Verify File/Folder in 'Edit Task'",
						"File/Folder Verified successfully"
								+ "<br /><b> Fiole/Folder Name : </b>"
								+ fileOrFolderName, Status.PASS);
			} else {
				report.updateTestLog("Verify File/Folder in 'Edit Task'",
						"File/Folder not Verified"
								+ "<br /><b> Fiole/Folder Name : </b>"
								+ fileOrFolderName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify File/Folder in 'Edit Task'",
					"Verify File/Folder in 'Edit Task' Failed", Status.FAIL);
		}
	}

	// Delete created workflow 50 per page
	public void check() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(
					myTaskPagesXpath, driver);

			if (noOfPagesEle != null && noOfPagesEle.size() > 1) {
				cancelTasks();

				int noOfPages = noOfPagesEle.size();

				for (int index = 0; index < noOfPages; index++) {
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					cancelTasks();
				}

				cancelTasks();
			} else {
				cancelTasks();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancelTasks() {
		try {
			UIHelper.findandAddElementsToaList(driver, resultTaskList, wfList);
			for (String result : wfList) {
				String finalwftaskXpath = tempwftaskXpath.replace("CRAFT",
						result);
				try {
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalwftaskXpath));
				} catch (Exception e) {
				}

				try {
					UIHelper.highlightElement(driver, finalwftaskXpath);
				} catch (Exception e) {
				}
				String finalXpathForCancelTaskLink = tempXpathForCancelTaskLink
						.replace("CRAFT", result);
				UIHelper.waitFor(driver);
				try {
					WebElement calceTaskEle = UIHelper.findAnElementbyXpath(
							driver, finalXpathForCancelTaskLink);
					UIHelper.highlightElement(driver, calceTaskEle);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();",
							calceTaskEle);
				} catch (Exception e) {
				}
				UIHelper.waitFor(driver);
				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							yesButtonXpathInCancelWorkflow);
				} catch (Exception e) {
				}

				try {
					UIHelper.click(driver, yesButtonXpathInCancelWorkflow);
				} catch (Exception e) {
				}

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							messageDivXpath);
				} catch (Exception e) {
				}
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageDivXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

			}
			wfList.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Approve task by adding comments
	public void performActionOnTaskFromMyTasksPage(
			String performedActionOnTask, String reviewerComment, String status) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					taskStatusDropdownXpath);

			if (status != null && !status.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, taskStatusDropdownXpath,
						status);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					editTaskReviewCommentsTAreaXpath);

			WebElement commentTAreaEle = UIHelper.findAnElementbyXpath(driver,
					editTaskReviewCommentsTAreaXpath);
			UIHelper.highlightElement(driver, commentTAreaEle);

			commentTAreaEle.sendKeys(reviewerComment);
			UIHelper.waitFor(driver);

			String finalXpathForPerformActionOnTask = tempXpathForPerformActionOnTask
					.replace("CRAFT", performedActionOnTask);

			if (performedActionOnTask.equalsIgnoreCase("Approve")) {
				WebElement performActionEleOnTask = UIHelper
						.findAnElementbyXpath(driver,
								finalXpathForPerformActionOnTask);
				UIHelper.scrollToAnElement(performActionEleOnTask);
				UIHelper.highlightElement(driver, performActionEleOnTask);
				UIHelper.clickanElement(performActionEleOnTask);

				report.updateTestLog("Verify Assigned Task",
						"Reviewed the assigned task and approved the task by adding comments: "
								+ reviewerComment, Status.PASS);
			} else if (performedActionOnTask.equalsIgnoreCase("Reject")) {
				WebElement performActionEleOnTask = UIHelper
						.findAnElementbyXpath(driver,
								finalXpathForPerformActionOnTask);
				UIHelper.scrollToAnElement(performActionEleOnTask);
				UIHelper.highlightElement(driver, performActionEleOnTask);
				UIHelper.clickanElement(performActionEleOnTask);

				report.updateTestLog("Verify Assigned Task",
						"Reviewed the assigned task and rejected the task by adding comments: "
								+ reviewerComment, Status.PASS);
			} else if (performedActionOnTask
					.equalsIgnoreCase("Continue Review and Approve")) {
				WebElement performActionEleOnTask = UIHelper
						.findAnElementbyXpath(driver,
								continueReviewAndApproveBtnXpath);
				UIHelper.scrollToAnElement(performActionEleOnTask);
				UIHelper.highlightElement(driver, performActionEleOnTask);
				UIHelper.clickanElement(performActionEleOnTask);

				report.updateTestLog(
						"Verify Rejected Task",
						"Reviewed the rejected task and performed 'Continue Review and Approve' to the task by adding comments: "
								+ reviewerComment, Status.PASS);
			}
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Edit task and add comment and approve task
	public void saveTask() {
		try {
			String reviewerComment = dataTable.getData("Tasks",
					"ReviewerComment");

			String status = dataTable.getData("Tasks", "Status");

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					taskStatusDropdownXpath);

			if (status != null && !status.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, taskStatusDropdownXpath,
						status);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					editTaskReviewCommentsTAreaXpath);

			WebElement commentTAreaEle = UIHelper.findAnElementbyXpath(driver,
					editTaskReviewCommentsTAreaXpath);
			UIHelper.highlightElement(driver, commentTAreaEle);

			commentTAreaEle.sendKeys(reviewerComment);
			UIHelper.waitFor(driver);

			WebElement saveBtnEle = UIHelper.findAnElementbyXpath(driver,
					saveBtnXPath);
			UIHelper.highlightElement(driver, saveBtnEle);
			report.updateTestLog("Save and Close the  Assigned Task",
					"Save the assigned task and closed the task by adding comments: "
							+ reviewerComment, Status.PASS);

			UIHelper.clickanElement(saveBtnEle);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Save and Close the  Assigned Task",
					"Unable to edit the task ", Status.FAIL);
			e.printStackTrace();
		}
	}

	// To claim the Task
	public void claimTask() {
		try {
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, claimBtnXpath);
			} catch (Exception e) {
			}
			UIHelper.highlightElement(driver, claimBtnXpath);
			UIHelper.click(driver, claimBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check Assignee is able to editable Due date or not
	public boolean isAssigneeEditableDueDateInTaskEditPage() {
		boolean isEditableDueDate = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					viewModeFieldXpathForDueDate);
			if (UIHelper.checkForAnElementbyXpath(driver,
					viewModeFieldXpathForDueDate)) {
				WebElement dueDateValEle = UIHelper.findAnElementbyXpath(
						driver, viewModeFieldXpathForDueDate);
				UIHelper.highlightElement(driver, dueDateValEle);
				String attributeVal = UIHelper.findAnElementbyXpath(driver,
						viewModeFieldXpathForDueDate).getAttribute("class");

				if (attributeVal.contains("viewmode")) {
					isEditableDueDate = true;
				} else {
					isEditableDueDate = false;
				}
			} else {
				isEditableDueDate = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isEditableDueDate;
	}

	public void verifyRejectOutcome() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, rejectOutcomeXpath);
			UIHelper.highlightElement(driver, rejectOutcomeXpath);
			String outcomeMessage = UIHelper.getTextFromWebElement(driver,
					rejectOutcomeXpath);
			if (outcomeMessage.equalsIgnoreCase("Rejected")) {
				report.updateTestLog("Reject Task",
						"Task is rejected by other asignee ", Status.PASS);
			} else {
				report.updateTestLog("Reject Task", "Task is not rejected ",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getWorkflowStatus(String status) {
		String finalworkflowStatusXpath = tempXpathForGeneralInfoLabelName
				.replace("CRAFT", status);

		UIHelper.waitForVisibilityOfEleByXpath(driver, finalworkflowStatusXpath);
		UIHelper.highlightElement(driver, finalworkflowStatusXpath);
		return UIHelper.getTextFromWebElement(driver, finalworkflowStatusXpath);

	}

	// Check General Info Label Name
	public boolean checkGeneralInfoLabelName(String labelName) {
		boolean isDisplayedGeneralInfoLabel = false;
		try {
			String finalXpathForGeneralInfoLabelName = tempXpathForGeneralInfoLabelName
					.replace("CRAFT", labelName);
			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForGeneralInfoLabelName)) {
				isDisplayedGeneralInfoLabel = true;
			} else {
				isDisplayedGeneralInfoLabel = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedGeneralInfoLabel;
	}

	// Check Approver & Reviewer Fields
	public boolean checkApproverAndReviewerFieldLabels() {
		boolean isDisplayedApproverAndReviewerFields = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					approverFieldLabelXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					reviewerFieldLabelXpath);

			if (UIHelper.checkForAnElementbyXpath(driver,
					approverFieldLabelXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							reviewerFieldLabelXpath)) {
				isDisplayedApproverAndReviewerFields = true;
			} else {
				isDisplayedApproverAndReviewerFields = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedApproverAndReviewerFields;
	}

	public boolean checkTaskAvail(String Msg) {
		boolean flag = false;
		try {
			String finalTaskNameXpath = tempTaskNameXpath.replace("CRAFT", Msg);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalTaskNameXpath);
			UIHelper.findandAddElementsToaList(driver, taskNameXpath,
					createdTasksNameList);
			for (String WFtask : createdTasksNameList) {

				if (WFtask.contains(Msg)) {
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalTaskNameXpath));
					UIHelper.highlightElement(driver, finalTaskNameXpath);
					UIHelper.click(driver, finalTaskNameXpath);
					flag = true;
					break;
				}

			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}

	public void clickTaskDoneWithComments(String reviewerComment) {

		try {
			WebElement commentTAreaEle = UIHelper.findAnElementbyXpath(driver,
					editTaskReviewCommentsTAreaXpath);
			UIHelper.highlightElement(driver, commentTAreaEle);

			commentTAreaEle.sendKeys(reviewerComment);
			UIHelper.waitFor(driver);

			WebElement approveOrTaskDoneBtnEle = UIHelper.findAnElementbyXpath(
					driver, approveButtonXpathInReviewTaskPage);
			UIHelper.highlightElement(driver, approveOrTaskDoneBtnEle);

			UIHelper.clickanElement(approveOrTaskDoneBtnEle);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog(
					"Verify Assigned Task",
					"Reviewed the assigned task and approved/completed the task by adding comments: "
							+ reviewerComment, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Assigned Task",
					"Failed to approve/complete the task by adding comments: "
							+ reviewerComment, Status.FAIL);
		}

	}

	private String wFStatusXpath = ".//*[@class='status']";

	public void checkWFStaus(String status) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, wFStatusXpath);
			UIHelper.highlightElement(driver, wFStatusXpath);
			if (UIHelper.getTextFromWebElement(driver, wFStatusXpath).equals(
					status)) {
				report.updateTestLog(
						"Verify Workflow status",
						"Workflow status is "
								+ UIHelper.getTextFromWebElement(driver,
										wFStatusXpath), Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Workflow status",
					"FAILED. Workflow status is not matched", Status.FAIL);

		}

	}

	public void changeDueDate(String dueDate) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, wfDueDateXpath);
			if (dueDate.contains("'")) {
				finalDueDate = dueDate.replace("'", "");
			} else {
				finalDueDate = dueDate;
			}
			UIHelper.sendKeysToAnElementByXpath(driver, wfDueDateXpath,
					finalDueDate);
			WebElement dueDateEle = UIHelper.findAnElementbyXpath(driver,
					wfDueDateXpath);
			if (UIHelper.checkForAnWebElement(dueDateEle)) {
				dueDateEle.sendKeys(Keys.TAB);
			}

			report.updateTestLog("Change due date of workflow",
					"Due date changed for the workflow", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To Continue review a task with comments
	public void continueReviewWithComments(String comment) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commentBoxXpath);
			driver.findElement(By.xpath(commentBoxXpath)).sendKeys(comment);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					continueReviewAndApproveBtnXpath);
			UIHelper.highlightElement(driver, continueReviewAndApproveBtnXpath);
			report.updateTestLog("Save Task", " Task Saved sucessfully",
					Status.PASS);
			UIHelper.click(driver, continueReviewAndApproveBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Save Task", "Save Task Failed", Status.FAIL);
		}

	}

	// Edit task
	public void editCreatedOrAssignedTask(String taskName) {
		try {
			boolean isDisplayedTask = checkCreatedOrAssignedTask(taskName);

			String pageTitle = UIHelper.getTextFromWebElement(driver,
					pageTitleXpath);
			System.out.println(pageTitle);
			if (isDisplayedTask) {
				editTaskInMyWorkflows();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Edit task
	public void editTaskInMyWorkflows() {

		String taskName = dataTable.getData("Workflow", "Message");
		String finalEdtTaskLinkXPath = edtTaskLinkXPath.replace("CRAFT",
				taskName);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		if (!(UIHelper.findAnElementbyXpath(driver, finalEdtTaskLinkXPath)
				.isDisplayed())) {
			report.updateTestLog(
					"Verify the Edit Taks Link displayed",
					"Edit Task Link NOT displayed hence the reviewer cannot edit or approve or reject taks-> "
							+ taskName, Status.PASS);
		}

	}

	public boolean checkReviewerFieldForSimpleReviewAndApprove() {
		boolean isDisplayedReviewerField = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					reviewerFieldLabelXpath);
			if (UIHelper.checkForAnElementbyXpath(driver,
					reviewerFieldLabelXpath)) {
				isDisplayedReviewerField = true;
			} else {
				isDisplayedReviewerField = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedReviewerField;
	}

	// Check attached file name for task
	public boolean checkAttachedFileNameForTask(String taskName, String fileName) {
		boolean isDisplayedAttachedFileNameForTask = false;
		try {
			String finalXpathForAttachedFileNameInTasksPage = tempXpathForAttachedFileNameInTasksPage
					.replace("CRAFT", fileName);

			boolean isDisplayedWorkflowInPage = checkCreatedOrAssignedTask(taskName);

			if (isDisplayedWorkflowInPage) {
				if (UIHelper.checkForAnElementbyXpath(driver,
						finalXpathForAttachedFileNameInTasksPage)) {
					isDisplayedAttachedFileNameForTask = true;
				} else {
					isDisplayedAttachedFileNameForTask = false;
				}
			} else {
				isDisplayedAttachedFileNameForTask = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedAttachedFileNameForTask;
	}

	// Remove added asset from edit tas page
	public void removeAssetFromEditTaskPage(String assetName) {
		try {
			String finalXpathForRemoveItemInEditTaskPage = tempXpathForRemoveItemInEditTaskPage
					.replace("CRAFT", assetName);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalXpathForRemoveItemInEditTaskPage);

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForRemoveItemInEditTaskPage)) {
				UIHelper.click(driver, finalXpathForRemoveItemInEditTaskPage);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						finalXpathForRemoveItemInEditTaskPage);

				if (!UIHelper.checkForAnElementbyXpath(driver,
						finalXpathForRemoveItemInEditTaskPage)) {
					report.updateTestLog("Remove asset from edit task page",
							"Asset: " + assetName + " removed successfully",
							Status.PASS);
				} else {
					report.updateTestLog("Remove asset from edit task page",
							"Asset: " + assetName + " removed successfully",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Save and Close button in edited task page
	public void clickOnSaveAndCloseBtn() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, saveAndCloseBtnXpath)) {
				UIHelper.click(driver, saveAndCloseBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
						startWorkFlowButtonXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog(
						"Click on 'Save and Close' Button",
						"Clicked the 'Save and Close' button and saved the workflow",
						Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Perform Reassign
	public void performReAssignForTask(String assignee) {
		try {
			String finalSearchResultXpathInSelectAsigneePopup = tempSearchResultXpathInSelectAsigneePopup
					.replace("CRAFT", assignee);
			String finalXpathForSelectSearchedUser = tempXpathForSelectSearchedUser
					.replace("CRAFT", assignee);

			UIHelper.waitForVisibilityOfEleByXpath(driver, reAssignBtnXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, reAssignBtnXpath)) {
				UIHelper.click(driver, reAssignBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						searchUserFieldInSelectAsigneePopup);

				UIHelper.sendKeysToAnElementByXpath(driver,
						searchUserFieldInSelectAsigneePopup, assignee);

				UIHelper.click(driver, searchBtnInSelectAsigneePopup);

				UIHelper.waitForVisibilityOfEleByXpath(driver,
						finalSearchResultXpathInSelectAsigneePopup);
				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver,
						finalXpathForSelectSearchedUser);
				if (UIHelper.checkForAnElementbyXpath(driver,
						finalXpathForSelectSearchedUser)) {
					UIHelper.click(driver, finalXpathForSelectSearchedUser);

					report.updateTestLog("Click on 'Select",
							"User ressigned task with user:" + assignee,
							Status.PASS);

					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
				} else {
					report.updateTestLog(
							"Perform Reassign",
							"User failed to ressign task with user:" + assignee,
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Perform Reassign",
					"User failed to ressign task with user:" + assignee,
					Status.FAIL);
			e.printStackTrace();
		}
	}

	// check file in Attached list in workflow
	public boolean checkFileInWorkFlowAttachment(String fileName) {
		boolean flag = false;
		try {
			List<WebElement> fileNameList = UIHelper.findListOfElementsbyXpath(
					attachedFileListXpath, driver);

			for (WebElement ele : fileNameList) {
				if (ele.getText().contains(fileName)) {
					UIHelper.highlightElement(driver, ele);
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;

	}

	public void checkNoItemsMsgInReviewerSearchList(String userName) {
		try {
			UIHelper.click(driver, wfReviewerXapth);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					wfReviewerSearchXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, wfReviewerSearchXpath,
					userName);
			UIHelper.click(driver, wfReviwerSearchbuttonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, noItemsFoundXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, noItemsFoundXpath)) {
				UIHelper.highlightElement(driver, noItemsFoundXpath);
				report.updateTestLog(
						"Check 'No items found' message displayed",
						"'No Items found' message displayed successfully for <br><b>User : </b>"
								+ userName, Status.PASS);
			} else {
				report.updateTestLog(
						"Check 'No items found' message displayed",
						"'No Items found' message is not displayed for <br><b>User : </b>"
								+ userName, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();

			report.updateTestLog("Check 'No items found' message displayed",
					"'No Items found' message is not displayed", Status.FAIL);
		}
	}

	// check Claim button available
	public void checkClaimButton() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, claimBtnXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, claimBtnXpath)) {
				UIHelper.highlightElement(driver, claimBtnXpath);
				report.updateTestLog("Check [CLAIM] button available",
						"[CLAIM] button is available", Status.PASS);
			} else {
				report.updateTestLog("Check [CLAIM] button available",
						"[CLAIM] button is not available", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();

			report.updateTestLog("Check [CLAIM] button available",
					"[CLAIM] button is not available", Status.FAIL);
		}
	}

	// Check Task in My Tasks Page
	public boolean checkTaskInMyTasksPageByFilter(String taskName) {
		boolean isDisplayedTaskInMyTasksPg = false;
		try {
			filterWFtasks();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			/*
			 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			 * synchronizingRemoteTasksMsgXpath);
			 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			 * loadingXpathInWorkflowPage);
			 */
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.findandAddElementsToaList(driver, taskslistName, tasksList);
			for (String result : tasksList) {
				if (result.contains(taskName)) {
					String finaltaskXpath = temptasklist.replace("CRAFT",
							result);
					WebElement taskEle = UIHelper.findAnElementbyXpath(driver,
							finaltaskXpath);
					UIHelper.highlightElement(driver, taskEle);
					UIHelper.scrollToAnElement(taskEle);
					UIHelper.waitFor(driver);
					isDisplayedTaskInMyTasksPg = true;

					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedTaskInMyTasksPg;
	}

	// Check Task in Workflows I've Started Page
	public boolean checkTaskInMyWorkFlowsByFilter(String taskName) {
		boolean isDisplayedTaskInMyWorkflowsPg = false;
		try {
			/*
			 * WebElement myWorkFlowTableContainerEle = driver.findElement(By
			 * .xpath(myWorkFlowSecXpath)); UIHelper.highlightElement(driver,
			 * myWorkFlowTableContainerEle); UIHelper.mouseOveranElement(driver,
			 * myWorkFlowTableContainerEle);
			 * 
			 * WebElement myWorkFlowsOrTasksContainerEle = driver.findElement(By
			 * .xpath(myWorkflowsOrMyTasksXpath));
			 * UIHelper.highlightElement(driver,
			 * myWorkFlowsOrTasksContainerEle);
			 * UIHelper.mouseOveranElement(driver,
			 * myWorkFlowsOrTasksContainerEle);
			 */

			filterWFtasks();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			/*
			 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			 * synchronizingRemoteTasksMsgXpath);
			 */
			// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			// loadingXpathInWorkflowPage);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			List<WebElement> uploadedFileOrFolderTitleEleList = driver
					.findElements(By.xpath(tasksTitlesXpath));
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(taskName)) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.scrollToAnElement(ele);
					UIHelper.waitFor(driver);

					isDisplayedTaskInMyWorkflowsPg = true;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedTaskInMyWorkflowsPg;
	}

	// Check Task is Created/Assigned
	public boolean checkCreatedOrAssignedTaskByFilter(String taskName) {
		boolean isDisplayedWorkflowInPage = false;
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			isDisplayedWorkflowInPage = checkTaskByFilter(taskName);

			if (isDisplayedWorkflowInPage == false) {
				List<WebElement> noOfPagesEle = UIHelper
						.findListOfElementsbyXpath(myTaskPagesXpath, driver);
				int noOfPages = noOfPagesEle.size();
				for (int index = 0; index < noOfPages; index++) {
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					isDisplayedWorkflowInPage = checkTaskByFilter(taskName);
					if (isDisplayedWorkflowInPage) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedWorkflowInPage;
	}

	// Check Task is Created/Displayed
	public boolean checkTaskByFilter(String taskName) {
		boolean isDisplayedTask = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);
			WebElement pageBodyEle = driver
					.findElement(By.xpath(pageBodyXpath));
			UIHelper.highlightElement(driver, pageBodyEle);
			UIHelper.mouseOveranElement(driver, pageBodyEle);

			UIHelper.waitFor(driver);

			String pageTitle = UIHelper.getTextFromWebElement(driver,
					pageTitleXpath);
			if (pageTitle.contains("Workflows I've Started")) {
				isDisplayedTask = checkTaskInMyWorkFlowsByFilter(taskName);
			} else if (pageTitle.contains("My Tasks")) {
				isDisplayedTask = checkTaskInMyTasksPageByFilter(taskName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedTask;
	}

	// Enter field data for Revie And Approve Submission Process
	public void enterFieldDataForRevAndAppArchSubmissionProcess(
			String fieldDetails) {

		ArrayList<String> dataList = new ArrayList<String>();
		try {
			StringTokenizer token = new StringTokenizer(fieldDetails, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				String title = "";
				String dataType = "";
				String value = "";
				StringTokenizer subToken = new StringTokenizer(docProperty, "-");
				while (subToken.hasMoreElements()) {
					title = subToken.nextToken().trim();
					dataType = subToken.nextToken().trim();
					value = subToken.nextToken().trim();
					dataList.add(title + " " + value);
				}

				String finalInputBoxXpath = "";

				if (dataType.equalsIgnoreCase("d:text")
						|| dataType.equalsIgnoreCase("d:int")) {
					finalInputBoxXpath = revAndAppArchSubmissionProcessInputXpath
							.replace("FIELD_LABEL", title);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalInputBoxXpath));
					UIHelper.highlightElement(driver, finalInputBoxXpath);
					UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath)
							.clear();
					UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath)
							.sendKeys(value);
				} else if (dataType.equalsIgnoreCase("d:mltext")) {
					finalInputBoxXpath = revAndAppArchSubmissionProcessTextAreaXpath
							.replace("TEXT_AREA_LABEL", title);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalInputBoxXpath));
					UIHelper.highlightElement(driver, finalInputBoxXpath);
					UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath)
							.clear();
					UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath)
							.sendKeys(value);
				} else if (dataType.equalsIgnoreCase("d:dropdown")) {
					finalInputBoxXpath = revAndAppArchSubmissionProcessDropDownXpath
							.replace("DROPDOWN_FIELD_LABEL", title);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalInputBoxXpath));
					UIHelper.highlightElement(driver, finalInputBoxXpath);
					UIHelper.selectbyVisibleText(driver, finalInputBoxXpath,
							value);
				} else if (dataType.equalsIgnoreCase("d:chkbox")) {
					finalInputBoxXpath = revAndAppArchSubmissionProcessChkBoxXpath
							.replace("CHKBOX_LABEL", title);

					if (value.equalsIgnoreCase("Yes")) {
						UIHelper.click(driver, finalInputBoxXpath);
					}

				}
			}
			report.updateTestLog(
					"Enter data for 'Review And Approve Archive Submission Process'",
					"User entered data:"
							+ dataList
							+ " for 'Review And Approve Archive Submission Process'",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Filter work flow task
	public void filterfolderview(String filter) {
		try {
			
			
			if(UIHelper.checkForAnElementbyXpath(driver, twister)){
				
				UIHelper.click(driver, twister);
				UIHelper.waitForVisibilityOfEleByXpath(driver, twisteropen);
				
			}
			String finalfilterXpath = filterfolder.replace("CRAFT", filter);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfilterXpath);
			UIHelper.click(driver, finalfilterXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingXpathInWorkflowPage);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		
			report.updateTestLog(
					"Select folder from left pan",
					filter +" Folder selected from left pan",
					Status.DONE);

		} catch (Exception e) {
			report.updateTestLog(
					"Select folder from left pan",
					filter +" folder not availble for selection",
					Status.FAIL);
		}

	}
}
