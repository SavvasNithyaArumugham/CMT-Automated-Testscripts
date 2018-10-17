package com.pearson.automation.alfresco.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.RandomPicker;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.RobotUtil;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoDocumentDetailsPage.java - This program contains methods for
 * validating the document details
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoDocumentDetailsPage extends ReusableLibrary {

	private String openedDocHeaderXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//h1";
	private String myFilesFolderLinkXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//a[text()='CRAFT']";
	private String commonPageTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String docHeaderVal;
	private String uploadNewVersionLinkXpath = ".//*[@id='onActionUploadNewVersion']/a/span";
	private String minorChangeXpath = ".//*[@id='template_x002e_dnd-upload_x002e_document-details_x0023_default-minorVersion']";
	private String majorChangeXpath = ".//*[@id='template_x002e_dnd-upload_x002e_document-details_x0023_default-majorVersion']";
	private String majorRadioXpath = ".//*[@id='majorVersion-radioButton']";
	private String minorRadioXpath = ".//*[@id='minorVersion-radioButton']";
	private String commentsTextAreaXpath = ".//*[@id='template_x002e_dnd-upload_x002e_document-details_x0023_default-description-textarea']";
	private String commentsXpath = ".//*[@id='description-textarea']";
	private String uploadFileButtonXpath = ".//*[@id='template_x002e_dnd-upload_x002e_document-details_x0023_default-file-selection-button-overlay']/span/input";
	private String uploadXpath = ".//*[@id='bulkCheckInFiles']";
	private String uploadButtonXpath = ".//*[contains(@style,'visible')]//*[@class='bd']//*[@class='first-child']//button[contains(@id,'default-upload-button-button')]";
	private String uploadBtXpath = ".//*[@id='onBulkCheckinOkButton-button']";
	private String documentDetailsTitleXpath = ".//*[@id='HEADER_TITLE']";
	public static String gobalCurrentVersionNumber = new String();

	private String olderVersionRevertButtonXpath = ".//*[@class='sticky-wrapper']//*[contains(@class,'yui-')]//*[contains(@class,'alfresco-datatable')]//table[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td/div[1]/div[2]/span/a[1]";
	private String revertListSegmentXpath = ".//*[contains(@id,'template_x002e_document-versions_x002e_document-details_x0023_default-body')]";
	private String versionRevertOKButtonXpath = ".//*[@id='alfresco-revertVersion-instance-ok-button-button']";

	private String revertMinorVersionXpath = ".//*[@class='bd']//*[contains(@id,'alfresco-revertVersion-instance')]//*[@class='yui-u']//*[@id='alfresco-revertVersion-instance-minorVersion-radioButton']";
	private String revertMajorVersionXpath = ".//*[@class='bd']//*[contains(@id,'alfresco-revertVersion-instance')]//*[@class='yui-u']//*[@id='alfresco-revertVersion-instance-majorVersion-radioButton']";
	private String revertCommentAreaXpath = ".//*[@class='bd']//*[contains(@id,'alfresco-revertVersion-instance')]//*[@id='alfresco-revertVersion-instance-description-textarea']";

	private String currentVersionNumberXpath = ".//*[@id='template_x002e_document-versions_x002e_document-details_x0023_default-latestVersion']/div[1]/span";

	private String clickMoveToInDocumentDetailsSectionXpath = "//a/span[text()='Move to...']";
	private String manageAspectsLinkXpath = "//a/span[text()='Manage Aspects']";
	private String movePopUpXpath = "//*[contains(@id,'default-copyMoveTo-dialog_c')]//div[@class='site']";
	private String applyChangesBtnXpath = ".//*[contains(@id,'default-aspects-ok-button')]";
	private String cancelButtonXpathInAddAspectsWindow = ".//*[contains(@id,'default-aspects-cancel-button')]";
	private String selectSiteSecXpath = ".//*[contains(@id,'default-copyMoveTo-sitePicker')]";
	private String selectFileToMoveXpath = ".//*[contains(@id,'default-copyMoveTo-sitePicker')]//a[contains(.,'CRAFT')]";
	private String btnMoveInMovePopUpXpath = "//*[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Move']";
	private String btnCopyInCopyPopUpXpath = "//*[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Copy']";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String messageXpathForFilesBeingUnzipped = ".//*[@id='message']/*[@class='bd']/*[contains(.,'Files being unzipped')]";
	private String messageXpathForUnzipSuccessOrNotComplete = ".//*[@id='message']/*[@class='bd']/*[contains(.,'Successfully unzipped') or contains(.,'The unzip operation could not be completed')]";
	private String loadingImage = ".//*[@id='overlay']/a/*[@id='loadingImage']";
	private String openedDocumentContainerLayerXpath = ".//*[@class='textLayer']";

	private String editPropertiesXpath = ".//span[text()='Edit Properties']";
	private String deletedFolderXpath = ".//*[@id='onActionDelete']/a/span";
	private String editPropertyTitleInputXpath = ".//*[@id='template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cm_title']";
	private String editPropertySaveButtonXpath = ".//*[@id='template_x002e_edit-metadata_x002e_edit-metadata_x0023_default-form-submit-button']";
	private String titlePropertyXpath = ".//*[contains(@id,'default-formContainer-form-fields')]//span[contains(.,'Title')]/following-sibling::span";

	private String commentTextXpath = ".//*[@id='template_x002e_document-versions_x002e_document-details_x0023_default-latestVersion']/div[2]/div[2]/div[2]";
	private String lastVersionNumberXpath = ".//*[@class='document-version']";

	private String clickCopyToInDocumentDetailsSectionXpath = "//a/span[text()='Copy to...']";

	private String downloadXpath = ".//*[@class='controls flat-button']/span/span/button[contains(@id,'download')]";

	private String filePropertyNameXpath = ".//*[contains(@id,'default-formContainer-form-fields')]//span[contains(.,'Name')]/following-sibling::span";
	private String filePropertyTitleXpath = ".//*[contains(@id,'default-formContainer-form-fields')]//span[contains(.,'Title')]/following-sibling::span";
	private String filePropertyAuthorXpath = ".//*[@class='viewmode-label' and contains(.,'Author')]//following-sibling::*[@class='viewmode-value']";
	private String filePropertyCreateDateXpath = ".//*[@class='viewmode-label' and contains(.,'Created Date')]//following-sibling::span";
	private String contentViewerXpath = ".//*[contains(@id,'default-viewer-pageContainer-1')]/*[@class='textLayer']";

	private ArrayList<String> propertiesValuesList = new ArrayList<String>();

	private String propertiesListXpath = ".//*[contains(@id,'default-formContainer-form-fields')]/*[@class='set']//*[contains(@class,'viewmode-field') and not(contains(.,'Date')) and not(contains(.,'Size')) and not(contains(.,'Creator')) and not(contains(.,'Image Width')) and not(contains(.,'Image Height')) and not(contains(.,'Exposure Time')) and not(contains(.,'F Number')) and not(contains(.,'Flash Activated')) and not(contains(.,'Focal Length')) and not(contains(.,'ISO Speed')) and not(contains(.,'Flash Activated')) and not(contains(.,'Camera Manufacturer')) and not(contains(.,'Flash Activated')) and not(contains(.,'Camera Model')) and not(contains(.,'Camera Software')) and not(contains(.,'Orientation')) and not(contains(.,'Horizontal Resolution')) and not(contains(.,'Vertical Resolution')) and not(contains(.,'Resolution Unit')) and not(contains(.,'Modifier')) and not(contains(.,'Author')) and not(contains(.,'ISBN'))]";
	private String propertiesListWithCreaterXpath = ".//*[contains(@id,'default-formContainer-form-fields')]/*[@class='set']//*[contains(@class,'viewmode-field') and not(contains(.,'Date')) and not(contains(.,'Size')) and not(contains(.,'Image Width')) and not(contains(.,'Image Height')) and not(contains(.,'Exposure Time')) and not(contains(.,'F Number')) and not(contains(.,'Flash Activated')) and not(contains(.,'Focal Length')) and not(contains(.,'ISO Speed')) and not(contains(.,'Flash Activated')) and not(contains(.,'Camera Manufacturer')) and not(contains(.,'Flash Activated')) and not(contains(.,'Camera Model')) and not(contains(.,'Camera Software')) and not(contains(.,'Orientation')) and not(contains(.,'Horizontal Resolution')) and not(contains(.,'Vertical Resolution')) and not(contains(.,'Resolution Unit')) and not(contains(.,'Modifier')) and not(contains(.,'Author')) and not(contains(.,'ISBN'))]";

	private String buttonStartWorkflowXpath = "//*[@id='onActionAssignWorkflow']/a/span";
	private String pageStartWorkflowHeaderXpath = "//*[@id='HEADER_TITLE']/a";
	private String dropDownWorkflowXpath = "//*[contains(@id,'default-workflow-definition-button-button')]";
	private String selectWokflowSimpleReviewApproveXpath = "//*[@id='yui-gen0']";
	private String wfDueDateXpath = ".//*[@class='date-entry']";
	private String selectWokflowSimpleTaskXpath = "//*[@id='yui-gen1']";
	private String textBoxMessageXpath = ".//*[@class='form-field alf-textarea']//textarea[contains(@id,'default-startWorkflowForm-alf-id1_prop_bpm_workflowDescription')]";
	private String textBoxDateXpath = "//*[contains(@id,'default-startWorkflowForm-alf-id1_prop_bpm_workflowDueDate-cntrl-date')]";
	private String lastWorkFlowCreatedXpath = "//*[contains(@id,'default-body')]/div/div[2]/div[@class='workflow workflow-last']/a";
	private String startWorkflowCompleteXpath = "//*[contains(@id,'default-startWorkflowForm-alf-id1-form-submit-button')]";
	private String userAddedOkXpath = "//*[contains(@id,'default-startWorkflowForm-alf-id1_assoc_bpm_assignees-cntrl-ok-button')]";
	private String userSearchBoxXpath = "//*[contains(@id,'default-startWorkflowForm-alf-id1_assoc_bpm_assignees-cntrl-picker-searchText')]";
	private String userSearchButtonXpath = "//*[contains(@id,'default-startWorkflowForm-alf-id1_assoc_bpm_assignees-cntrl-picker-searchButton-button')]";
	private String textBoxApprovalPercentageXpath = "//*[contains(@id,'default-startWorkflowForm-alf-id1_prop_cwf_requiredApprovePercent')]/input";
	private String addIconXpath = "//*/table/tbody[2]/tr[1]/td[3]/*[@class='yui-dt-liner']/a[@title='Add']";
	private String clickReviewersSelectXpath = "//*[@id='yui-gen8-button']";
	private String selectPriorityXpath = "//*[contains(@id,'default-startWorkflowForm-alf-id1_prop_bpm_workflowPriority')]";

	private String addButtonXpath = "//*[contains(@id,'yui-gen')]/span/button[contains(.,'Add')]";
	private String addFileIcon = "//*/table/tbody[2]/*/td[3]/div/a[@title='Add' and not(contains(@style,'display'))]";
	private String addFileOk = "//button[contains(@id,'assoc_packageItems-cntrl-ok-button')]";

	private String startWorkflowXpath = ".//*[@id='onActionAssignWorkflow']/a/span";

	private String tempXpathForAddAspects = ".//table//tr//td[contains(.,'CRAFT')]//ancestor::tr//td//span";
	private String propertyContainerXpath = ".//*[contains(@id,'default-formContainer-form-fields')]/*[@class='set']";

	private String revertPopMsgXpath = ".//*[@id='message']/div";

	private String unzipDocOptionXpath = ".//*[contains(@id,'default-actionSet')]//a/span[contains(.,'Unzip')]";
	private String optionsBtnXpath = ".//button[contains(.,'Options')]";
	private String viewOptionsListXpath = ".//*[@class='options-select']//*[contains(@class,'menu-body-scrolled')]//li//a//span";
	private String simpleViewBtnXpath = ".//*[@class='view simple']";
	private String detailedViewBtnXpath = ".//*[@class='view detailed']";
	private String optionsContainerXpath = ".//*[@class='options-select']//ul";

	private String tempXpathForPopertyFieldLabel = ".//*[@class='form-container']//label[text()='CRAFT:']";
	private String tempXpathForPopertyFieldValue = ".//*[@class='form-container']//label[text()='CRAFT:']//following-sibling::input";
	private String tempXpathForPopertyTextarea = ".//*[@class='form-container']//label[text()='CRAFT:']//following-sibling::textarea";
	/* Copy To Respository */

	private String tempXpathForDestination = ".//*[@class='mode flat-button']//button[contains(@id,'copyMoveToRepository') and text()='CRAFT' and @aria-checked='true']";
	private String tempXpathForSite = ".//*[contains(@id,'copyMoveToRepository-sitePicker')]//a[@rel='CRAFT']";

	// ".//*[contains(@id,'copyMoveToRepository-sitePicker')]//a//h4[contains(text(),'CRAFT')]";
	private String tempXpathForSiteDocument = ".//*[contains(@id,'copyMoveToRepository-treeview')]//table//td//span[text()='Documents']";
	private String tempXpathForfinalDoc = ".//*[contains(@id,'copyMoveToRepository-treeview')]//table//td//span[text()='CRAFT']";
	private String copybtn = ".//*[contains(@id,'copyMoveToRepository-wrapper')]//span//button[text()='Copy']";
	private String cancelbtn = ".//*[contains(@id,'copyMoveToRepository-wrapper')]//span//button[text()='Cancel']";
	private String copytoRepositorytitle = ".//*[contains(@id,'default-copyMoveToRepository-title')]//span[text()='CRAFT']";
	private String commentWaitXpath="//div[@class='bd']//span[contains(@class,'wait')]";

	// .//*[@id='message']/div
	/*
	 * private String unzipPopupContainerXpath =
	 * ".//*[contains(@class,'unzip-option-container')]"; private String
	 * extractToDestiFolderXpath =
	 * ".//*[contains(@id,'default-form')]/table/tbody/tr[3]/td[1]/input";
	 * private String extractToDocumentsFolderXpath =
	 * ".//*[contains(@id,'default-form')]/table/tbody/tr[3]/td[2]/input";
	 * private String extractToHereXpath =
	 * ".//*[contains(@id,'default-form')]/table/tbody/tr[3]/td[3]/input";
	 * 
	 * private String extractBtnXpath =
	 * ".//*[@class='bdft']//button[contains(.,'Extract')]";
	 * 
	 * private String sourceDailogwrapperXpath =
	 * ".//*[@class='bd']//*[contains(@id,'default-sourceDialog-wrapper')]";
	 * private String allSitesBtnXpath =
	 * ".//*[contains(@id,'default-sourceDialog-site-button')]"; private String
	 * tempXpathForSitePicker =
	 * ".//*[contains(@id,'default-sourceDialog-sitePicker')]//a/h4[contains(.,'CRAFT')]"
	 * ; private String documentsXpathForTargetSite =
	 * ".//*[contains(@id,'default-sourceDialog-treeview')]//table/tbody/tr/td/span[contains(.,'Documents')]"
	 * ; private String destFolderXpathForTargetSite =
	 * ".//*[contains(@id,'default-sourceDialog-treeview')]//table/tbody/tr/td/span[contains(.,'CRAFT')]"
	 * ; private String sourceDailogOkBtn =
	 * ".//*[contains(@id,'default-sourceDialog-ok-button')]";
	 */

	private String editOfflineXpath = "//a/span[text()='Edit Offline']";
	private String cancelEditingXpath = "//a/span[text()='Cancel Editing']";
	private String mediaGuideXpath="//*[@id='onMediaGuideAction']/a";
	private String tempXpathForSiteSelection = "//div[@class='site']//a/h4[text()='CRAFT']";
	private String tempXpathForDestFolder = ".//*[contains(@id,'default-copyMoveTo-treeview')]//table//td//span[contains(text(),'CRAFT')]";
	private String defaultdocument = ".//*[contains(@id,'default-copyMoveTo-treeview')]//table//td//span[contains(text(),'Documents')]";
	private String btnLinkToInLinkPopUpxpath = "//button[contains(@id,'default-copyMoveTo-ok-button')]";
	private String destinationDailogXpath = ".//*[contains(@id,'default-copyMoveTo-treeview')]";

	private String relationshipTableVal = ".//*[@id='relationship-function-table']/tbody/tr/td[3]/a";
	private String relationshipTableRel = ".//*[@id='relationship-function-table']//ancestor::tr//td[1]";

	private String relationshipTablVal, relationshipVal;
	// private String btnLinkToInLinkPopUpXpath =
	// "//div[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Link']";
	private String btnLinkToInLinkPopUpXpath = "//button[contains(@id,'default-copyMoveTo-ok-button')]";

	private String filePropertyModifierXpath = ".//*[@class='form-fields']//*[contains(text(),'Modifier')]//ancestor::div/span[2]";
	private String filePropertyModifiedDateXpath = ".//*[@class='form-fields']//*[contains(text(),'Modified Date')]//ancestor::div/span[2]";

	private String locationsContainerXpath = "//*[@id='global_x002e_view-locations']";
	private String originalLocationXpath = "//*[@id='global_x002e_view-locations']//*[contains(text(),'Original')]//ancestor::tr/td[2]/a";
	private String secondaryLocationXpath = "//*[@id='global_x002e_view-locations']//*[contains(text(),'Secondary')]//ancestor::tr/td[2]/a";

	private String relationsContainerXpath = "//*[@id='global_x002e_view-relationships']";
	private String relationsXpath = "//*[@id='global_x002e_view-relationships']//*[@id='relationshipTable']//table";

	private String docActionOptions = ".//*[@id='bd']//*[@class='doclist']//a/span";
	private ArrayList<String> docOptionValues = new ArrayList<String>();

	private String lifeCycleDropdownXpath = ".//*[contains(@id,'lifecycle-dropdown')]";
	private String okBtnXpath = ".//*[contains(@id,'ok-button')]";
	private String lifeCycleAttributeXpathInPropSec = ".//*[contains(@id,'default-formContainer-form-fields')]//*[@class='set']//span[1][contains(.,'Lifecycle Name')]//following-sibling::span[1]";
	private String propAttributeXpathInPropSec = ".//*[contains(@id,'default-formContainer-form-fields')]//*[@class='set']//*[@class='viewmode-label']/following-sibling::*";
	private ArrayList<String> actualAttributeValuesFromPropSec = new ArrayList<String>();
	private String linkToXpath = " //*[@id='template_x002e_document-actions_x002e_document-details']//*[@id='onActionLinkTo']//*[contains(text(),'Link To..')]";
	private String btnCancelInLinkPopUpXpath = "//div[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Cancel']";
	private String aspectLoadingXpath = ".//*[@class='yui-dt-empty']";
	private String defaultAspectsLeftContainerXpath = ".//*[contains(@id,'default-aspects-left')]";
	private String defaultAspectsRightContainerXpath = ".//*[contains(@id,'default-aspects-right')]";
	private String aspectsListXpath = ".//*[@class='bd']//table//tbody/tr/td//h4";
	private String fileOrFolderPropertiesXpath = ".//*[contains(@id,'default-formContainer-form-fields')]//span[contains(@class,'viewmode-label')]";
	private String fileOrFolderPropertiesValueXpath = ".//*[contains(@id,'default-formContainer-form-fields')]//span[contains(@class,'viewmode-value')]";
	private String fileOrFolderPropertiesSegementXpath = ".//*[contains(@id,'folder-details') or contains(@id,'document-details')]/*[contains(@class,'folder-metadata-header folder-details-panel') or contains(@class,'document-metadata-header document-details-panel')]";
	private String filePropertiesSegementXpath = ".//*[contains(@id,'folder-details') or contains(@id,'document-details')]/*[contains(@class,'folder-metadata-header folder-details-panel') or contains(@class,'document-metadata-header document-details-panel')]";

	private String allPropertiesButtonXpath = ".//*[contains(@id,'editMetadata-button')]";

	private String tempSelectFolderChkboxXpath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//td[1]//input";

	private String linkPopUpContainerXpath = "//*[contains(@id,'default-copyMoveTo-dialog')]";
	private String linkPopUpCrossMarkXpath = "//*[@class='container-close']";
	private String documentLibraryXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']/a";

	private String editPropertiesLableListXpath = ".//*[@class='sticky-wrapper']//*[@class='share-form']//*[contains(@id,'default-form-fields')]//label";
	private String editPropertiesInputXpath = ".//*[@class='sticky-wrapper']//*[@class='share-form']//*[contains(@id,'edit-metadata_x0023_default-form-fields')]//div//div[index]//input[@type='text']";
	private String editPropertiesTextAreaXpath = ".//*[@class='sticky-wrapper']//*[@class='share-form']//*[contains(@id,'edit-metadata_x0023_default-form-fields')]//div//div[index]//textarea";
	private String editPropertiesSaveButtonXpath = ".//*[contains(@id,'form-submit-button')]";
	private String editPropertiesCancelButtonXpath = ".//*[contains(@id,'form-cancel-button')]";

	private String editPropertiesSegementXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']";
	private String emptyFieldPopUpXpath = ".//*[@class='sticky-wrapper']//*[contains(@id,'alf-id')]//*[@class='bd']//*[@class='balloon']//*[contains(text(),'The value cannot be empty')]";

	private String defaultAspectsContainerXpath = ".//*[contains(@id,'default-aspects-left')]";
	private String myFilesTablesXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']";
	private String finalDueDate;

	private String addRelationshipActionXpath = "//*[@class='doclist']//*[@id='addRelationship']/a";

	private String tempDeletRelationshipWithFilenameAndRelationshipXpath="//*[@class='documentLinks'][contains(text(),'FILENAME')]//ancestor::tr/td[text()='CRAFT']//following-sibling::td/img[@id='delete-relationship']";
	private String deleteRelationshipXpath = "//*[@id='viewRelationshipsMarkup']//table//tr/td[4]/img";
	
	private String deleteRelationshipPromptXpath = ".//*[@id='prompt_h']";
	//private String deleteRelationshipYesBtnXpath = ".//*[@id='prompt_c']//button[text()='Yes']";
	private String deleteRelationshipYesBtnXpath=".//*[@id='prompt_c']//*[@class='yui-button yui-push-button']//button";

	private String documentDetailsPropsectXpath = ".//*[contains(@class,'metadata-header') and contains(@class,'details-panel')]";
	//private String propertyHeaderXpathInDocDetails = ".//*[contains(@class,'metadata-header') and contains(@class,'details-panel')]/h2[contains(.,'Properties')]";
	private String propertyHeaderXpathInDocDetails = ".//*[contains(@class,'metadata-header') and contains(@class,'details-panel')]/h2[contains(@class,'thin dark')]";
	private String syncSecXpath = ".//*[contains(@class,'details-panel')]/h2[contains(.,'Sync')]";
	private String modifiedDateXpath = ".//*[contains(@id,'default-formContainer-form-fields')]//span[contains(.,'Modified Date')]";
	private String lastLabelXpathInPropSec = ".//*[contains(@id,'default-formContainer-form-fields')]/div[2]";

	private String documentLikeXpath = ".//*[@id='template_x002e_node-header_x002e_document-details_x0023_default-like']/a";

	/*** Add comment ***/
	private String addCommentXpath = ".//*[contains(@class,'onAddCommentClick')]//button";
	private String noCommentsSecXpath = ".//table/tbody/tr/td[contains(.,'No comments')]";
	private String inputCommentXpath = ".//*[@class='comment-form']//iframe";
	private String iframeCommentsFieldXpath = ".//*[@id='tinymce']/p";
	private String saveCommentXpath = ".//*[contains(@id,'default-add-submit-button')]";
	private String commentXpath = ".//*[contains(@id,'comment-container')]";

	private String deleteCommentXpath = ".//*[contains(@id,'comment-container')]//*[@class='comment-actions']/a[contains(@class,'default delete-comment')]";
	private String commentsContainerXpath = ".//*[contains(@id,'comment-container')]";
	private String locationsXpath = ".//*[@class='document-details-panel']/h2[contains(.,'Locations')]";

	private String deleteButtonXpath = ".//*[@id='Share']//*[@id='prompt']//*[@class='button-group']//span/button[contains(.,'Delete')]";

	private String btnNavFoldrXPath = ".//*[contains(@id,'cntrl-picker-navigator-button')]/div/span[2]";
	private String verNoXPath = ".//*[@id='document-version']";
	private String canEdtXPath = ".//*[@id='bd']//*[@class='doclist']//a/span[text()='Cancel Editing']";

	private String tempXpathForAddFileIcon = "//*[contains(@id,'assoc_packageItems-cntrl-picker-results')]//*[@class='yui-dt-data']/tr/td[contains(.,'CRAFT')]//following-sibling::td[1]/div/a[@title='Add' and not(contains(@style,'display'))]";
	private String tempXpathForFolderAddFileIcon = "//*[contains(@id,'assoc_packageItems-cntrl-picker-results')]/table/tbody[2]/tr/td[2][contains(.,'CRAFT')]//following-sibling::td[1]/div/a[@title='Add']";
	private String tempXpathForSelectItemInCompanyFolder = "//*[contains(@id,'assoc_packageItems-cntrl-picker-results')]//*[@class='yui-dt-data']/tr/td//a[contains(.,'CRAFT')]";
	private String loadingXpathInAddItemsPopup = ".//*[@class='yui-dt-message']//*[contains(@class,'yui-dt-liner') and contains(.,'Loading')]";
	private String titlePropXpath = ".//*[contains(@id,'default-formContainer-form-fields')]/*[@class='set']//*[contains(@class,'viewmode-field')]/span[contains(.,'Title')]/following-sibling::span[1]";

	private String titlePropValue;

	private String wrkFlwMsgXPath = ".//div[@class='set']//div[@class='viewmode-field']/span[text()='Message:']//ancestor::div[1]/span[2]";

	private String uploadField = ".//*[@id='file-selection-button-overlay']/span/input";
	private String uploadInputField = ".//*[contains(@id,'default-file-selection-button-overlay')]/span/input";

	private String cancelButtonManageAspects = ".//*[contains(@id,'_x0023_default-aspects-cancel-button')]";
	private String messageXpath = ".//*[@id='message']/div";

	private String versionHistoryHeadingXpath = ".//*[@class='yui-u']//h2[contains(.,'Version History')]";
	private String olderVersionNoXpathForFile = ".//*[@class='yui-u']//h3[contains(.,'Older Versions')]//following-sibling::div[1]//span[contains(@class,'document-version')]";
	private String lastVersionNoXpathForFile = ".//*[@class='yui-u']//h3[contains(.,'Last Version')]//following-sibling::div[1]//span[contains(@class,'document-version')]";
	private String revertVersionXpathForFile = ".//*[@class='yui-u']//h3[contains(.,'Older Versions')]//following-sibling::div[1]//span/a[contains(@class,'revert')]";

	private String majorRadioXpathForRevertVersion = ".//*[contains(@style,'visible')]//*[contains(@id,'majorVersion-radioButton')]";
	private String minorRadioXpathForRevertVersion = ".//*[contains(@style,'visible')]//*[contains(@id,'minorVersion-radioButton')]";
	private String commentsXpathForRevertVersion = ".//*[contains(@style,'visible')]//*[contains(@id,'description-textarea')]";
	private String okButtonXpathInRevertVersion = ".//*[contains(@style,'visible')]//button[contains(@id,'ok-button')]";

	private String shareLinkXpath = ".//*[contains(@id,'_document-details_x0023_default-quickshare')]/a";
	private String sharePanelXpath = ".//*[contains(@class,'quickshare')]//*[@class='bd']";
	private String googleShareXpath = ".//*[@class='quickshare-linkshare']//span/a[contains(@title,'Google')]";

	private String usertextBoxXpath = ".//span[@class='g-h-f-N']//input[@id='sbdp']";
	private String shareButtonXpath = ".//td[@class='bI']//div[text()='Share']";
	private String gmailUserTextBoxXpath = ".//input[@id='Email']";
	private String gmailNextButtonXpath = ".//input[@id='next']";

	// Click on Edit properties
	private String editPropertiesOpXpath = ".//*[contains(@id,'default-actionSet')]//a[contains(.,'Edit Properties')]";
	private String folderXpath = ".//*[@class='item-name']//a[text()='CRAFT']";
	private String okBtnInAssociatedDocXpath = ".//*[contains(@id,'assoc_wcc_documentRule-cntrl-ok-button')]";
	private String associateDocRuleXpath = ".//*[text()='Associated Document Rule:']//ancestor::div[1]//div//button";

	private String uploadNewVersionProcessPanelXpath = ".//*[@id='template_x002e_dnd-upload_x002e_document-details_x0023_default-dialog']/div[2]";

	private String tempXpathForDocAction = ".//*[contains(@id,'document-details')]//a/span[contains(.,'CRAFT')]";
	private String tempXpathForFolderAction = ".//*[contains(@id,'folder-details')]//a/span[contains(.,'CRAFT')]";

	private String titleInputXapth = ".//*[contains(@id,'_default_prop_cm_title')]";
	private String progCompTitleInputXapth = ".//*[contains(@id,'default_prop_cplg_programComponentTitle')]";

	private String favoriteLink = ".//*[@class='node-header']//span/a[contains(@class,'favourite-action')]";
	private String enabledFavoriteLink = ".//*[@class='node-header']//span/a[contains(@title,'Remove folder from favorites')]";
	private String downloadBtn = ".//*[@class='node-header']//span/a[contains(.,'Download')]";
	private String followOptionLinkXpath = ".//*[@class='node-header']//span/a[contains(.,'Follow')]";
	private String unFollowOptionLinlXpath = ".//*[@class='node-header']//span/a[contains(.,'Unfollow')]";

	private String docActionXpath = ".//*[contains(@id,'default-actionSet')]//span[contains(text(),'CRAFT')]";
	private String cancelIconXpath = ".//*[contains(@class,'dialog shadow') and contains(@style,'visible')]//a";

	private String editProNameInputXpath = ".//input[contains(@id,'_prop_cm_name')]";
	private String inLineEditNameInputXapth = ".//input[contains(@id,'_prop_cm_name')]";

	private String addRelationshipWidgetXpath = ".//*[@id='addNewRelationship']/a";
	private String addRelationshipInDashletXpath = ".//button[@id='addrelationshipssites']";

	private String calculateSizeXpath = ".//*[@id='calculateSize']/a";

	private String inheritPermissionsBtnXpath = ".//button[contains(@id,'_default-inheritedButton-button')]";
	private String yesBtnXpath = ".//button[text()='Yes']";
	private String saveBtnXpath = ".//button[text()='Save']";

	// Select manage pemissions from document details page
	private String buttonManagePermissionsXpath = ".//*[@class='document-manage-granular-permissions']/a/span";

	private String nodePathXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='node-header']/*[@class='node-info']/*[@class='node-path']";

	private String lifecycleFirstOptionXpath = ".//*[contains(@style,'visible')]//*[contains(@id,'lifecycle-dropdown')]/option[1]";

	private String tempdocActionsMenuXapth = ".//span[contains(text(),'CRAFT')]";

	// Changed unzip xpaths
	private String newUnzipContainerXpath = ".//*[contains(@id,'default-copyMoveTo-dialog') and contains(@style,'visible')]";
	private String unzipBtnXpath = ".//*[@class='bd']//button[contains(.,'Unzip')]";
	private String unzipDailogwrapperXpath = ".//*[@class='bd']//*[contains(@id,'default-copyMoveTo-wrapper')]";
	private String tempXpathForSitePicker = ".//*[contains(@id,'sitePicker')]//a/h4[text()='CRAFT']";
	private String documentsXpathForTargetSite = ".//*[contains(@class,'treeview')]//table/tbody/tr/td/span[contains(.,'Documents')]";
	private String destFolderXpathForTargetSite = ".//*[contains(@id,'ygtvcontentel')]/*[text()='CRAFT']";
	private String unzipcollection = ".//*[contains(@id,'ygtvcontentel')]/*[text()='Thumbnails']";

	private String okBtnXpathForBecomeOwner = ".//*[contains(@id,'yui-gen')]//button[contains(.,'OK')]";

	private String myFilesButtonXpath = ".//*[@class='bd']//*[contains(@id,'default-copyMoveTo-myfiles')]//button[contains(.,'My Files')]";
	private String loadXpathInPathSec = ".//*[contains(@class,'ygtvloading')]/a";

	private String sharedFilesButtonXpath = ".//*[contains(@id,'copyMoveTo-shared-button')]";
	private String lodingXpathForDocLib = ".//*[@class='yui-dt-message']/tr/td/*[contains(.,'Loading the Document Library')]";

	private String tempXpathForFieldLabelInEditPropPage = ".//*[contains(@id,'default-form-fields')]//*[contains(@class,'form-field')]/label[contains(.,'CRAFT:')]";

	private String viewModeOfSizeFieldXpath = ".//*[contains(@id,'default-formContainer-form-fields')]//*[@class='form-field']//*[contains(.,'Size:')]/following-sibling::*";
	private String pearsonLogoXpath = ".//*[@id='HEADER_LOGO']//img";

	private String tempXpathForAddedRelationshipLink = ".//*[@id='relationship-function-table']//td/a[contains(.,'CRAFT')]";

	private String folderNavBtnXpathInSelectWindow = ".//*[contains(@id,'default_assoc_wcc_documentRule-cntrl-picker-navigator-button')]";
	private String displayedValOfFolderNavBtn = ".//*[contains(@id,'default_assoc_wcc_documentRule-cntrl-picker-navigator-button')]//*[@class='item-name']";
	private String loadingXpathInSelectFoldOrFileWindow = ".//*[contains(@style,'visible')]//*[contains(@class,'yui-dt-liner') and text()='Loading...']";
	private String tempXpathForNavOptionInSelectFolder = ".//*[@class='bd']//a/*[text()='CRAFT']";
	private String tempXpathForItemUnderCompHome = ".//*[@class='yui-dt-data']//h3/a[text()='CRAFT']";

	private String tempXpathForEditPropFieldVal = ".//*[@class='sticky-wrapper']//*[@class='share-form']//*[contains(@id,'default-form-fields')]//label[text()='CRAFT']/following-sibling::*";
	private String assetGUIDXpath = ".//*[contains(@id,'default-formContainer-form-fields')]//span[contains(.,'Asset GUID')]/following-sibling::span";
	private String sourceIDXpath = ".//*[contains(@id,'default-formContainer-form-fields')]//span[contains(.,'Source ID')]/following-sibling::span";
	private String clickBreadCrumbXpath="//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='node-header']/*[@class='node-info']/*[@class='node-path']//span//a[contains(text(),'Craft')]";
	private String newRelationshipVersionFieldXpath=".//*[@class='yui-panel-container yui-dialog shadow']//div[@id='addRelationship-dialog']//table//div[2]/span";
	private String newRelationshipButtonFieldsXpath=".//div[@class='yui-panel-container yui-dialog shadow']//span/descendant::span";
	private String neRelationShipHeaderXpath=".//*[@class='yui-panel-container yui-dialog shadow']//div[@id='addRelationship-title']";
	
	private String deleteRelationShipBodyFieldXpath=".//*[@id='prompt_c']//div[@class='bd']";
	private String deleteRelationshipButtonXpath=".//*[@id='prompt_c']//div[@class='ft']//button";
	
	AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoDocumentDetailsPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		/*
		 * if(!driver.getTitle().contains("Document Details")) { throw new
		 * IllegalStateException
		 * ("Document Details page expected, but not displayed!"); }
		 */
	}

	public String getDocumentHeaderName() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			UIHelper.highlightElement(driver, openedDocHeaderXpath);
			docHeaderVal = UIHelper.getTextFromWebElement(driver, openedDocHeaderXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docHeaderVal;
	}

	public void navigateDocDetailPgtoMyFilesPage(String folderName) {
		try {
			String finalXpathForMyFileFolder = myFilesFolderLinkXpath.replace("CRAFT", folderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMyFileFolder);
			UIHelper.click(driver, finalXpathForMyFileFolder);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Navigate to Document/Folder to back page
	public void backToFolderOrDocumentPage(String folderName) {
		try {
			String finalXpathForMyFileFolder;
			if (folderName != null && !folderName.isEmpty()) {
				finalXpathForMyFileFolder = myFilesFolderLinkXpath.replace("CRAFT", folderName);
			} else {
				finalXpathForMyFileFolder = myFilesFolderLinkXpath.replace("CRAFT", "Documents");
			}
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMyFileFolder);
			UIHelper.click(driver, finalXpathForMyFileFolder);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unCheckTheSelectedFile(String fileOrFolderName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);

			WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);

			UIHelper.highlightElement(driver, chkboxElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", chkboxElement);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for Upload new Version in Document Details Page
	 * 
	 * @author 412766
	 */
	public void uploadNewVersionFileInDocumentDetailsPage() {

		String fileName = dataTable.getData("Document_Details", "FileName");
		try {
			commonMethodForUploadNewVersionFile(fileName);
		} catch (Exception e) {
			report.updateTestLog("Upload New Version File In DocumentDetails Page",
					"Upload New Version File In DocumentDetails Page Failed" + "<br /><b>File Name :</b> " + fileName,
					Status.FAIL);
		}
	}

	public void commonMethodForUploadNewVersionFile(String fileName) {
		try {
			String filePath = dataTable.getData("Document_Details", "FilePath");
			String versionDetails = dataTable.getData("Document_Details", "Version");
			String comments = dataTable.getData("Document_Details", "Comments");

			filePath = System.getProperty("user.dir") + filePath;

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, uploadNewVersionLinkXpath);
			} catch (Exception e) {
			}

			WebElement uploadNewVersionLinkWebElement = UIHelper.findAnElementbyXpath(driver,
					uploadNewVersionLinkXpath);
			UIHelper.highlightElement(driver, uploadNewVersionLinkWebElement);
			UIHelper.mouseOverandElementdoubleClick(driver, uploadNewVersionLinkWebElement);
			WebElement minorOrMajorChangeWebElement = null;

			if (versionDetails.equals("Minor")) {
				minorOrMajorChangeWebElement = UIHelper.findAnElementbyXpath(driver, minorChangeXpath);

			} else {
				minorOrMajorChangeWebElement = UIHelper.findAnElementbyXpath(driver, majorChangeXpath);
			}

			UIHelper.highlightElement(driver, minorOrMajorChangeWebElement);
			UIHelper.mouseOverandElementdoubleClick(driver, minorOrMajorChangeWebElement);
			gobalCurrentVersionNumber = toGetVersionNumber(minorOrMajorChangeWebElement.getText());

			WebElement commentsTextAreaWebElement = UIHelper.findAnElementbyXpath(driver, commentsTextAreaXpath);
			UIHelper.highlightElement(driver, commentsTextAreaWebElement);
			commentsTextAreaWebElement.sendKeys(comments);

			WebElement uploadFileButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadFileButtonXpath);
			UIHelper.highlightElement(driver, uploadFileButtonWebElement);
			/*
			 * UIHelper.mouseOverandElementdoubleClick(driver,
			 * uploadFileButtonWebElement); UIHelper.waitFor(driver);
			 * RobotUtil.uploadFile(filePath + fileName);
			 */

			WebElement fileInput = driver.findElement(By.xpath(uploadInputField));
			fileInput.sendKeys(filePath + fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadButtonXpath);
			WebElement uploadButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadButtonXpath);
			UIHelper.mouseOverandElementdoubleClick(driver, uploadButtonWebElement);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, uploadNewVersionProcessPanelXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Upload New Version File In DocumentDetails Page",
					"New version File uploded Successfully" + "<br /><b>File Name :</b> " + fileName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for Upload new Version in Document Details Page
	 * 
	 * @author 412766
	 */
	public void uploadNewVersionFileInDocumentDetailsPage(String fileName) {

		// String fileName = dataTable.getData("Document_Details", "FileName");
		try {

			String filePath = dataTable.getData("Document_Details", "FilePath");
			String versionDetails = dataTable.getData("Document_Details", "Version");
			String comments = dataTable.getData("Document_Details", "Comments");

			filePath = System.getProperty("user.dir") + filePath;

			/*
			 * WebElement uploadNewVersionLinkWebElement = UIHelper
			 * .findAnElementbyXpath(driver, uploadNewVersionLinkXpath);
			 * UIHelper.highlightElement(driver,
			 * uploadNewVersionLinkWebElement);
			 * UIHelper.mouseOverandElementdoubleClick(driver,
			 * uploadNewVersionLinkWebElement);
			 */
			WebElement minorOrMajorChangeWebElement = null;

			if (versionDetails.equals("Minor")) {
				minorOrMajorChangeWebElement = UIHelper.findAnElementbyXpath(driver, minorRadioXpath);

			} else {
				minorOrMajorChangeWebElement = UIHelper.findAnElementbyXpath(driver, majorRadioXpath);
			}

			UIHelper.highlightElement(driver, minorOrMajorChangeWebElement);
			UIHelper.mouseOverandElementdoubleClick(driver, minorOrMajorChangeWebElement);
			/*
			 * gobalCurrentVersionNumber =
			 * toGetVersionNumber(minorOrMajorChangeWebElement .getText());
			 */
			String version = dataTable.getData("MyFiles", "Version");
			gobalCurrentVersionNumber = version;

			WebElement commentsTextAreaWebElement = UIHelper.findAnElementbyXpath(driver, commentsXpath);
			UIHelper.highlightElement(driver, commentsTextAreaWebElement);
			commentsTextAreaWebElement.sendKeys(comments);

			WebElement uploadFileButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadXpath);
			UIHelper.highlightElement(driver, uploadFileButtonWebElement);
			/*
			 * UIHelper.mouseOverandElementdoubleClick(driver,
			 * uploadFileButtonWebElement); UIHelper.waitFor(driver);
			 * RobotUtil.uploadFile(filePath + fileName);
			 */

			WebElement fileInput = driver.findElement(By.xpath(uploadXpath));
			fileInput.sendKeys(filePath + fileName);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtXpath);
			} catch (Exception e) {

			}
			UIHelper.waitFor(driver);

			WebElement uploadButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadBtXpath);
			UIHelper.mouseOverandElementdoubleClick(driver, uploadButtonWebElement);

			try {
				UIHelper.waitForVisibilityOfEleByXpathForUpload(driver, messageEleXpath);
			} catch (Exception e) {

			}
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Upload New Version File In DocumentDetails Page",
					"New version File uploded Successfully" + "<br /><b>File Name :</b> " + fileName, Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Upload New Version File In DocumentDetails Page",
					"Upload New Version File In DocumentDetails Page Failed" + "<br /><b>File Name :</b> " + fileName,
					Status.FAIL);
		}
	}

	private String toGetVersionNumber(String versionNumberString) {

		String versionNumber = versionNumberString.substring(15, versionNumberString.length() - 1);

		return versionNumber;

	}

	/**
	 * @author 412766
	 */
	public void revertAnyOlderVersionToCurrentVersion() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, revertListSegmentXpath);
			UIHelper.highlightElement(driver, revertListSegmentXpath);
			UIHelper.click(driver, revertListSegmentXpath);

			List<WebElement> olderVersionRevertButtonWebElement = UIHelper
					.findListOfElementsbyXpath(olderVersionRevertButtonXpath, driver);
			ArrayList<String> preVersionList = new AlfrescoMyFilesPage(scriptHelper).getPreviousVersionOrderedList();
			int randomIndex = RandomPicker.getRandomList(olderVersionRevertButtonWebElement);

			int randomIndexes = olderVersionRevertButtonWebElement.size();
			WebElement randomSelectedWebElement = olderVersionRevertButtonWebElement.get(randomIndexes - 1);
			UIHelper.waitForAnElement(driver, randomSelectedWebElement);
			UIHelper.highlightElement(driver, randomSelectedWebElement);
			randomSelectedWebElement.click();

			UIHelper.waitForPageToLoad(driver);
			gobalCurrentVersionNumber = getCurrentVersionNumber(currentVersionNumberXpath);

			selectMajorOrMinorVersion(revertMinorVersionXpath, revertMajorVersionXpath, revertCommentAreaXpath);

			UIHelper.highlightElement(driver, versionRevertOKButtonXpath);
			UIHelper.click(driver, versionRevertOKButtonXpath);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, revertPopMsgXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
            UIHelper.pageRefresh(driver);
            
			report.updateTestLog("Revert older version to current version", "Older version reverted "
					+ "<br /><b>Version Reverted :</b> " + preVersionList.get(randomIndexes - 1), Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Revert older version to current version",
					"Revert older version to current version Failed", Status.FAIL);
		}
	}

	private void selectMajorOrMinorVersion(String minorVersionXpath, String majorVersionXpath,
			String commentAreaXpath) {

		String versionDetails = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		WebElement minorOrMajorChangeWebElement = null;

		if (versionDetails.equals("Minor")) {
			UIHelper.waitForVisibilityOfEleByXpath(driver, minorVersionXpath);
			minorOrMajorChangeWebElement = UIHelper.findAnElementbyXpath(driver, minorVersionXpath);

		} else {
			UIHelper.waitForVisibilityOfEleByXpath(driver, majorVersionXpath);
			minorOrMajorChangeWebElement = UIHelper.findAnElementbyXpath(driver, majorVersionXpath);
		}

		UIHelper.highlightElement(driver, minorOrMajorChangeWebElement);
		UIHelper.mouseOverandElementdoubleClick(driver, minorOrMajorChangeWebElement);

		WebElement commentsTextAreaWebElement = UIHelper.findAnElementbyXpath(driver, commentAreaXpath);
		UIHelper.highlightElement(driver, commentsTextAreaWebElement);
		commentsTextAreaWebElement.sendKeys(comments);
	}

	private String getCurrentVersionNumber(String currentVersionXPath) {

		UIHelper.findAnElementbyXpath(driver, currentVersionXPath);
		return UIHelper.findAnElementbyXpath(driver, currentVersionXPath).getText();
	}

	// Select any document details - Eg- Move to
	public void clickMoveToDocAction() {
		try {
			driver.navigate().refresh();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// openedDocumentContainerLayerXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, clickMoveToInDocumentDetailsSectionXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			UIHelper.click(driver, clickMoveToInDocumentDetailsSectionXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, movePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on Document Action option:'Move to...'",
					"User able to click the Document Action option:'Move to...'", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Perform Manage Aspects action
	public void performManageAspectsDocAction() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, manageAspectsLinkXpath);
			UIHelper.click(driver, manageAspectsLinkXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, defaultAspectsContainerXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, applyChangesBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on Document Action option:'Manage Aspects'",
					"User able to click the Document Action option:'Manage Aspects'", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickCancelInManageAspects() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelButtonManageAspects);
			UIHelper.highlightElement(driver, cancelButtonManageAspects);
			UIHelper.click(driver, cancelButtonManageAspects);
			UIHelper.waitFor(driver);
		} catch (Exception e) {

		}
	}

	/**
	 * @author 412766
	 */
	public void performEditPropertiesDocAction() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesXpath);
			UIHelper.highlightElement(driver, editPropertiesXpath);
			UIHelper.click(driver, editPropertiesXpath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void performDeleteFolderDocAction() {
		try {
			UIHelper.highlightElement(driver, deletedFolderXpath);
			UIHelper.click(driver, deletedFolderXpath);

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for add aspects and apply changes to file
	 * 
	 * @author 412766
	 */
	public void addAspectsAndApllyChangesToAFile() {
		try {
			String aspectsName = dataTable.getData("Document_Details", "AspectName");
			addOrDeleteAspect(aspectsName);
			report.updateTestLog("Apply Aspects for a file/folder",
					"Aspect Applied successfully" + "<br><b>Aspect Name : </b>" + aspectsName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for add aspects and apply changes to file
	 * 
	 * @author 412766
	 */
	public void addAspectsAndApllyChangesToAFile(String aspectsName) {
		try {
			addOrDeleteAspect(aspectsName);
			report.updateTestLog("Apply Aspects for a file/folder",
					"Aspect Applied successfully" + "<br><b>Aspect Name : </b>" + aspectsName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void deleteAspectsAndApllyChangesToAFile() {
		try {
			String aspectsName = dataTable.getData("Document_Details", "AspectName");
			commonMethodForDeleteAspects(aspectsName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param aspectsName
	 */
	public void commonMethodForDeleteAspects(String aspectsName) {
		try {
			addOrDeleteAspect(aspectsName);
			report.updateTestLog("Delete Aspects for a file/folder",
					"Aspect Deleted successfully" + "<br><b>Aspect Name : </b>" + aspectsName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param aspectsName
	 */
	private void addOrDeleteAspect(String aspectsName) {

		StringTokenizer token = new StringTokenizer(aspectsName, ",");
		while (token.hasMoreElements()) {
			String aspect = token.nextElement().toString().trim();
			String finalXpathForAddAspects = tempXpathForAddAspects.replace("CRAFT", aspect);
			UIHelper.click(driver, finalXpathForAddAspects);
			UIHelper.waitFor(driver);
		}

		UIHelper.waitForVisibilityOfEleByXpath(driver, applyChangesBtnXpath);
		UIHelper.click(driver, applyChangesBtnXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
		UIHelper.waitFor(driver);
	}

	/**
	 * @author 412766
	 * @param aspectName
	 * @return
	 */
	public boolean isAspectsAvailable(String aspectName) {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, defaultAspectsLeftContainerXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, aspectLoadingXpath);
			UIHelper.highlightElement(driver, defaultAspectsLeftContainerXpath);
			UIHelper.highlightElement(driver, defaultAspectsRightContainerXpath);
			List<WebElement> aspectsList = UIHelper.findListOfElementsbyXpath(aspectsListXpath, driver);
			// String aspectName = dataTable.getData("Document_Details",
			// "AspectName");
			for (WebElement webElement : aspectsList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					if (webElement.getText().equalsIgnoreCase(aspectName)) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.highlightElement(driver, webElement);
						UIHelper.waitFor(driver);
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Perform Unzip doc action
	public void performUnzipDocAction(String extractTo) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, unzipDocOptionXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UIHelper.waitFor(driver);
			UIHelper.click(driver, unzipDocOptionXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click On 'Unzip to..' option", "User able to click on 'Unzip to..' option ",
					Status.DONE);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, newUnzipContainerXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, unzipBtnXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, unzipBtnXpath)) {
				report.updateTestLog("Verify 'Unzip to..' popup", "'Unzip to..' popup opened successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Unzip to..' popup", "'Unzip to..' popup opened successfully",
						Status.FAIL);
			}
			performExtractToOperation(extractTo);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			} catch (Exception e) {
			}

			String messageVal = "";
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpathForUnzipSuccessOrNotComplete);
				messageVal = UIHelper.getTextFromWebElement(driver, messageXpathForUnzipSuccessOrNotComplete);
			} catch (Exception e) {
			}
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			if (messageVal.contains("Successfully unzipped")) {
				report.updateTestLog("Perform Unzip", "File unzipped successfully", Status.DONE);
			} else if (messageVal.contains("The unzip operation could not be completed")) {
				report.updateTestLog("Perform Unzip",
						"User is not able to perform 'Unzip to' in a folder where already the Unzip operation is performed",
						Status.DONE);
			} else {
				report.updateTestLog("Perform Unzip", "File failed to unzip", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void performUnzip(String extractTo, String fileName) {
		try {

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, newUnzipContainerXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, unzipBtnXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, unzipBtnXpath)) {
				report.updateTestLog("Verify 'Unzip to..' popup", "'Unzip to..' popup opened successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Unzip to..' popup", "'Unzip to..' popup opened successfully",
						Status.FAIL);
			}
			performExtractToOperation(extractTo);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			report.updateTestLog("Perform Unzip",
					"File extracted successfully" + "<br /><b>Extracted File Name : </b>" + fileName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param extractTo
	 */
	public void performUnzipForPopUpMsg(String extractTo) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, unzipDocOptionXpath);
			UIHelper.click(driver, unzipDocOptionXpath);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, newUnzipContainerXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, unzipBtnXpath);
			UIHelper.waitFor(driver);
			performExtractToOperation(extractTo);
			report.updateTestLog("Perform Unzip", "File extracted successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void performExtractToOperation(String extractTo) {
		try {
			if (extractTo.equalsIgnoreCase("DestinationSite")) {
				String siteName = dataTable.getData("Sites", "TargetSiteName");
				commonMethodForSelectSiteFolder(siteName, "No");
			} else if (extractTo.equalsIgnoreCase("DocumentsFolder")) {

				String siteName = dataTable.getData("Sites", "SiteName");
				commonMethodForSelectSiteFolder(siteName, "Yes");

			} else if (extractTo.equalsIgnoreCase("Here")) {

				String siteName = dataTable.getData("Sites", "SiteName");
				commonMethodForSelectSiteFolder(siteName, "No");
			} else if (extractTo.equalsIgnoreCase("DestinationSiteFolder")) {
				String siteName = dataTable.getData("Sites", "TargetSiteName");
				commonMethodForSelectSiteFolder(siteName, "Yes");
			} else if (extractTo.equalsIgnoreCase("MyFiles-Here")) {
				commonMethodForSelectMyFilesFolder("No");
			} else if (extractTo.equalsIgnoreCase("MyFiles-DocumentsFolder")) {
				commonMethodForSelectMyFilesFolder("Yes");
			}

			else if (extractTo.equalsIgnoreCase("SharedFiles-Here")) {
				commonMethodForSelectSharedFilesFolder("No");
			}

			else if (extractTo.equalsIgnoreCase("SharedFiles-DocumentsFolder")) {
				commonMethodForSelectSharedFilesFolder("Yes");
			}

		} catch (Exception e) {

		}
	}

	// Select My files folder
	public void commonMethodForSelectMyFilesFolder(String isReqToselctDocFolder) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, unzipDailogwrapperXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, unzipBtnXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesButtonXpath);
			UIHelper.click(driver, myFilesButtonXpath);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadXpathInPathSec);
			UIHelper.waitFor(driver);

			if (isReqToselctDocFolder.equalsIgnoreCase("Yes")) {
				String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
				ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);

				for (String folderName : folderNamesList) {
					String finalDestFolderXpathForTargetSite = destFolderXpathForTargetSite.replace("CRAFT",
							folderName);
					UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalDestFolderXpathForTargetSite);
					UIHelper.click(driver, finalDestFolderXpathForTargetSite);
					UIHelper.waitFor(driver);
				}
			}

			UIHelper.waitFor(driver);

			UIHelper.click(driver, unzipBtnXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commonMethodForSelectSiteFolder(String siteName, String isReqToselctDocFolder) {
		try {
			String finalXpathForSitePicker = tempXpathForSitePicker.replace("CRAFT", siteName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, unzipDailogwrapperXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalXpathForSitePicker);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSitePicker)) {
				UIHelper.click(driver, finalXpathForSitePicker);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
			} else {
				try {
					UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalXpathForSitePicker);
				} catch (Exception e) {
				}
				UIHelper.click(driver, finalXpathForSitePicker);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
			}

			if (isReqToselctDocFolder.equalsIgnoreCase("Yes")) {
				String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
				ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);

				for (String folderName : folderNamesList) {
					String finalDestFolderXpathForTargetSite = destFolderXpathForTargetSite.replace("CRAFT",
							folderName);
					UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalDestFolderXpathForTargetSite);
					UIHelper.click(driver, finalDestFolderXpathForTargetSite);
					UIHelper.waitFor(driver);
				}
			} else {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, documentsXpathForTargetSite);
			}

			UIHelper.waitFor(driver);

			UIHelper.click(driver, unzipBtnXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Perform Edit Offline doc action
	public void performEditOfflineDocAction() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editOfflineXpath);
			UIHelper.click(driver, editOfflineXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Perform Edit Offline", "User able to click on <b>Documennt Action:</b> Edit Offline",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// perform Document Action: Cancel Editing
	public void performCancelEditingDocAction() {
		try {
			driver.navigate().refresh();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, cancelEditingXpath)) {
				UIHelper.click(driver, cancelEditingXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickCopyToDocAction() {
		try {
			driver.navigate().refresh();
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocumentContainerLayerXpath);
			} catch (Exception e) {
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, clickCopyToInDocumentDetailsSectionXpath);
			} catch (Exception e) {
			}
			UIHelper.click(driver, clickCopyToInDocumentDetailsSectionXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, movePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnCopyInCopyPopUpXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Perform 'Copy To'", "User able to click on <b>Document Action:</b> Copy To",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select a file from popup to move
	public void selectFileInMovePopUp() {
		try {
			String targetSiteName = dataTable.getData("Sites", "TargetSiteName");

			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", targetSiteName);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, movePopUpXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, btnMoveInMovePopUpXpath);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, selectSiteSecXpath);

			try {

				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalSelectFileToMoveXpath);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, finalSelectFileToMoveXpath);
			UIHelper.click(driver, finalSelectFileToMoveXpath);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitFor(driver);
			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);
			UIHelper.highlightElement(driver, destFolderContainerEle);

			String destinationFolder = dataTable.getData("Sites", "TargetFolder");
			if (destinationFolder != null && !destinationFolder.isEmpty()) {
				String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);

				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

				WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
				UIHelper.highlightElement(driver, destFolderEle);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", destFolderEle);
			}

			// UIHelper.waitFor(driver);
			// UIHelper.waitFor(driver);
			report.updateTestLog("Verify Document Action: Move To", "Move file to " + "Target Site = " + targetSiteName,
					Status.PASS);
			UIHelper.click(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			// UIHelper.waitForPageToLoad(driver);
			// UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Document Action: Move To", "Move file to action failed", Status.FAIL);
		}
	}

	// Select a file from popup to move
	public void selectFileInMovePopUp(String sourceFileOrFolderName, String targetSiteName, String destinationFolder) {
		try {
			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", targetSiteName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, movePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnMoveInMovePopUpXpath);

			UIHelper.highlightElement(driver, selectSiteSecXpath);
			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalSelectFileToMoveXpath);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, finalSelectFileToMoveXpath);
			UIHelper.click(driver, finalSelectFileToMoveXpath);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitFor(driver);
			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);
			UIHelper.highlightElement(driver, destFolderContainerEle);

			if (destinationFolder != null && !destinationFolder.isEmpty()) {
				String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);

				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

				WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
				UIHelper.highlightElement(driver, destFolderEle);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", destFolderEle);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Verify Document Action: Move To",
					sourceFileOrFolderName + " moved to " + destinationFolder + " in Site = " + targetSiteName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select a file from popup to move
	public void selectFileInCopyPopUp() {
		try {
			String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", targetSiteName);

			UIHelper.highlightElement(driver, selectSiteSecXpath);
			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalSelectFileToMoveXpath);
			} catch (Exception e) {

			}

			UIHelper.highlightElement(driver, finalSelectFileToMoveXpath);
			UIHelper.click(driver, finalSelectFileToMoveXpath);
			// UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, destinationDailogXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, defaultdocument);
			UIHelper.waitFor(driver);
			report.updateTestLog("Verify Document Action: Copy To", "Copy file to " + "Target Site = " + targetSiteName,
					Status.PASS);
			UIHelper.click(driver, btnCopyInCopyPopUpXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, btnCopyInCopyPopUpXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Document Action: Copy To", "Copy file to action failed", Status.FAIL);
		}
	}

	// Select folder to copy in copy popup
	public void selectFolderToCopyInCopyPopUp(String sourceFileOrFolderName, String targetSiteName,
			String destinationFolder) {
		try {
			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);

			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", targetSiteName);

			UIHelper.highlightElement(driver, selectSiteSecXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalSelectFileToMoveXpath);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, finalSelectFileToMoveXpath);
			UIHelper.click(driver, finalSelectFileToMoveXpath);
			UIHelper.waitFor(driver);

			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);

			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnCopyInCopyPopUpXpath);
			UIHelper.click(driver, btnCopyInCopyPopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Verify Document Action: Copy To",
					sourceFileOrFolderName + " copied to " + destinationFolder + " in Site = " + targetSiteName,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select folder to copy in copy popup
	public void selectFolderToCopyInCopyPopUp(String destinationFolder) {
		try {
			String targetSiteName = dataTable.getData("Sites", "TargetSiteName");

			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);

			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", targetSiteName);

			UIHelper.highlightElement(driver, selectSiteSecXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalSelectFileToMoveXpath);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, finalSelectFileToMoveXpath);
			UIHelper.click(driver, finalSelectFileToMoveXpath);
			UIHelper.waitFor(driver);

			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);

			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnCopyInCopyPopUpXpath);
			report.updateTestLog("Verify Document Action: Copy To",
					"Copy file to " + "Folder Name = " + destinationFolder, Status.PASS);
			UIHelper.click(driver, btnCopyInCopyPopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Verify Document Action: Copy To",
					"Copy file to " + "Folder Name = " + destinationFolder, Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void editFileProperties() {
		try {
			UIHelper.waitFor(driver);
			Thread.sleep(2000);
			UIHelper.highlightElement(driver, editPropertiesXpath);
			UIHelper.click(driver, editPropertiesXpath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			String editPropertyTitleValue = dataTable.getData("Document_Details", "Title");

			UIHelper.highlightElement(driver, editPropertyTitleInputXpath);
			UIHelper.findAnElementbyXpath(driver, editPropertyTitleInputXpath).clear();
			UIHelper.findAnElementbyXpath(driver, editPropertyTitleInputXpath).sendKeys(editPropertyTitleValue);

			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesSaveButtonXpath);
			UIHelper.highlightElement(driver, editPropertiesSaveButtonXpath);
			UIHelper.click(driver, editPropertiesSaveButtonXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			report.updateTestLog("Edit file properties in DocumentDetails Page",
					"File property edited Successfully" + "<br /><b>Edited Value : </b>" + editPropertyTitleValue,
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Edit file properties in DocumentDetails Page",
					"Edit file properties in DocumentDetails Page Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isPreviousVersionIntoLatestVersion() {

		boolean flag = true;
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.findAnElementbyXpath(driver, currentVersionNumberXpath);
			String latestVersion = UIHelper.findAnElementbyXpath(driver, currentVersionNumberXpath).getText();

			double previousVersion = Double.parseDouble(AlfrescoDocumentDetailsPage.gobalCurrentVersionNumber);
			double currentVersion = Double.parseDouble(latestVersion);

			if (currentVersion > previousVersion) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			report.updateTestLog("Previous version selected as latest version", e.getMessage(), Status.FAIL);
		}

		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public String getCurrentVersion() {
		String latestVersion = UIHelper.findAnElementbyXpath(driver, currentVersionNumberXpath).getText();
		return latestVersion;
	}

	/**
	 * @author 412766
	 */
	public void highlightCurrentVersion() {
		UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, currentVersionNumberXpath));
		UIHelper.highlightElement(driver, currentVersionNumberXpath);
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isVersionByCommentedFreeText() {
		boolean flag = true;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentTextXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, commentTextXpath));
			UIHelper.highlightElement(driver, commentTextXpath);
			String commentText = UIHelper.findAnElementbyXpath(driver, commentTextXpath).getText();

			String comments = dataTable.getData("Document_Details", "Comments");

			if (commentText.contains(comments)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			report.updateTestLog("Identify the names added via free text",
					"Identify the names added via free text Failed", Status.FAIL);
		}

		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public String getCommentedTextValue() {
		String commentedText = UIHelper.findAnElementbyXpath(driver, commentTextXpath).getText();
		return commentedText;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isUploadedNewVersionFile() {
		boolean flag = true;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, lastVersionNumberXpath);
			UIHelper.highlightElement(driver, lastVersionNumberXpath);
			WebElement currentFileVersionWebElement = UIHelper.findAnElementbyXpath(driver, lastVersionNumberXpath);

			if (currentFileVersionWebElement.getText()
					.contains(AlfrescoDocumentDetailsPage.gobalCurrentVersionNumber)) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Uploaded New Version File", "Verify Uploaded New Version File Failed",
					Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isPreviousVersionMetaDataRetained() {
		boolean flag = true;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, titlePropertyXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, titlePropertyXpath));
			UIHelper.highlightElement(driver, titlePropertyXpath);
			String currentVersionTitleValue = UIHelper.findAnElementbyXpath(driver, titlePropertyXpath).getText();
			String editPropertyTitleValue = dataTable.getData("Document_Details", "Title");

			if (editPropertyTitleValue.equals(currentVersionTitleValue)) {
				flag = true;
			} else {
				flag = false;
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify previous version metadata retained in new version", e.getMessage(),
					Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public String getTitlePropertyValue() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, titlePropertyXpath);
		String currentVersionTitleValue = UIHelper.findAnElementbyXpath(driver, titlePropertyXpath).getText();
		return currentVersionTitleValue;
	}

	/**
	 * @author 412766
	 */
	public void downloadFileInDocumentDetailsPage() {
		try {

			UIHelper.highlightElement(driver, downloadXpath);
			UIHelper.click(driver, downloadXpath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			File downloadFile = new File(downloadedFilePath + "/" + fileName);
			long start_time = System.currentTimeMillis();
			long wait_time = 200000;
			long end_time = start_time + wait_time;
			while (System.currentTimeMillis() < end_time) {
				if (downloadFile.exists()) {
					break;
				}
			}

			if (!downloadFile.exists()) {
				report.updateTestLog("To download a file", "To download a file Failed", Status.FAIL);
			} else {
				report.updateTestLog("To download a file", "File Downloaded" + "<br /><b>File Name : </b>" + fileName,
						Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadFilefromactionmenu() {
		try {
			// UIHelper.highlightElement(driver, downloadXpath);
			// UIHelper.click(driver, downloadXpath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			File downloadFile = new File(downloadedFilePath + "/" + fileName);

			long start_time = System.currentTimeMillis();
			long wait_time = 200000;
			long end_time = start_time + wait_time;
			while (System.currentTimeMillis() < end_time) {
				if (downloadFile.exists()) {
					break;
				}
			}

			if (!downloadFile.exists()) {
				report.updateTestLog("To download a file", "To download a file Failed", Status.FAIL);
			} else {
				report.updateTestLog("To download a file", "File Downloaded" + "<br /><b>File Name : </b>" + fileName,
						Status.DONE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadLockedFileInDocumentDetailsPage() {
		try {
			boolean isDownloadedFile = false;
			UIHelper.highlightElement(driver, downloadXpath);
			UIHelper.click(driver, downloadXpath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
			String fileName = dataTable.getData("MyFiles", "FileName");

			String finalFileName;
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0] + " (Working Copy)";
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName + " (Working Copy).txt";
			}

			File downloadFile = new File(downloadedFilePath + "/" + finalFileName);

			long start_time = System.currentTimeMillis();
			long wait_time = 200000;
			long end_time = start_time + wait_time;
			while (System.currentTimeMillis() < end_time) {
				if (downloadFile.exists()) {
					isDownloadedFile = true;
					break;
				} else {
					isDownloadedFile = false;
				}
			}

			if (isDownloadedFile) {
				report.updateTestLog("To download a file", "File Downloaded" + "<br /><b>File Name : </b>" + fileName,
						Status.DONE);
			} else {
				report.updateTestLog("To download a file", "To download a file Failed", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param isZipFile
	 */
	public void deleteFileInDownloadedPath(boolean isZipFile) {

		String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
		String downloadedFileName = dataTable.getData("MyFiles", "FileName");
		String finalPath = "";
		if (isZipFile) {
			finalPath = downloadedFilePath + "/" + downloadedFileName + ".zip";
		} else {
			finalPath = downloadedFilePath + "/" + downloadedFileName + ".txt";
		}
		File downloadedFile = new File(finalPath);
		if (downloadedFile.exists()) {
			downloadedFile.delete();
		}
	}

	public void deleteLockedFileInDownloadedPath(boolean isZipFile) {

		String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
		String downloadedFileName = dataTable.getData("MyFiles", "FileName");

		String finalPath = "";
		if (isZipFile) {
			String finalFileName;
			if (downloadedFileName.contains(".")) {
				finalFileName = downloadedFileName;
			} else {
				finalFileName = downloadedFileName + ".zip";
			}

			finalPath = downloadedFilePath + "/" + finalFileName;
		} else {
			String finalFileName;
			if (downloadedFileName.contains(".")) {
				finalFileName = downloadedFileName;
			} else {
				finalFileName = downloadedFileName + ".txt";
			}

			finalPath = downloadedFilePath + "/" + finalFileName;
		}
		File downloadedFile = new File(finalPath);
		if (downloadedFile.exists()) {
			downloadedFile.delete();
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isFilePreviewed() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, contentViewerXpath);
			UIHelper.highlightElement(driver, contentViewerXpath);
			if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, contentViewerXpath))) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			report.updateTestLog("Verify preview of the file", e.getMessage(), Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isFilePropertiesViewed() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertyNameXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, filePropertyNameXpath));
			UIHelper.highlightElement(driver, filePropertyNameXpath);
			String fileName = UIHelper.findAnElementbyXpath(driver, filePropertyNameXpath).getText();

			UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertyAuthorXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, filePropertyAuthorXpath));
			UIHelper.highlightElement(driver, filePropertyAuthorXpath);
			String authorName = UIHelper.findAnElementbyXpath(driver, filePropertyAuthorXpath).getText();

			UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertyCreateDateXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, filePropertyCreateDateXpath));
			UIHelper.highlightElement(driver, filePropertyCreateDateXpath);
			String createdDate = UIHelper.findAnElementbyXpath(driver, filePropertyCreateDateXpath).getText();

			if (fileName.length() > 0 && authorName.length() > 0 && createdDate.length() > 0) {
				flag = true;
			} else {
				flag = false;
			}

			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Verify properties of the file", e.getMessage(), Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public ArrayList<String> getFileProperties() {

		ArrayList<String> filePropertiesList = new ArrayList<String>();
		String fileName = UIHelper.findAnElementbyXpath(driver, filePropertyNameXpath).getText();
		String authorName = UIHelper.findAnElementbyXpath(driver, filePropertyAuthorXpath).getText();
		String createdDate = UIHelper.findAnElementbyXpath(driver, filePropertyCreateDateXpath).getText();
		filePropertiesList.add(fileName);
		filePropertiesList.add(authorName);
		filePropertiesList.add(createdDate);

		return filePropertiesList;
	}

	// Get Properties from Document Details Page
	public ArrayList<String> getPropertiesFromDocDetailsPage() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, documentDetailsPropsectXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, propertyHeaderXpathInDocDetails);

			WebElement documentDetailsPropsectEle = UIHelper.findAnElementbyXpath(driver, documentDetailsPropsectXpath);
			UIHelper.highlightElement(driver, documentDetailsPropsectEle);

			WebElement propertyHeaderEleInDocDetails = UIHelper.findAnElementbyXpath(driver,
					propertyHeaderXpathInDocDetails);
			UIHelper.highlightElement(driver, propertyHeaderEleInDocDetails);

			WebElement modifiedDateEle = UIHelper.findAnElementbyXpath(driver, modifiedDateXpath);
			UIHelper.scrollToAnElement(modifiedDateEle);
			UIHelper.highlightElement(driver, modifiedDateEle);

			List<WebElement> propertiesValuesListEle = UIHelper.findListOfElementsbyXpath(propertiesListXpath, driver);

			for (WebElement propertyEle : propertiesValuesListEle) {
				propertiesValuesList.add(propertyEle.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertiesValuesList;
	}

	// Get Properties from Document Details Page
	public String getTitleFromPropertiesSecInDocDetailsPage() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, documentDetailsPropsectXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, propertyHeaderXpathInDocDetails);

			WebElement documentDetailsPropsectEle = UIHelper.findAnElementbyXpath(driver, documentDetailsPropsectXpath);
			UIHelper.highlightElement(driver, documentDetailsPropsectEle);

			WebElement propertyHeaderEleInDocDetails = UIHelper.findAnElementbyXpath(driver,
					propertyHeaderXpathInDocDetails);
			UIHelper.highlightElement(driver, propertyHeaderEleInDocDetails);

			if (UIHelper.checkForAnElementbyXpath(driver, titlePropXpath)) {
				WebElement titlePropEle = UIHelper.findAnElementbyXpath(driver, titlePropXpath);
				UIHelper.scrollToAnElement(titlePropEle);
				UIHelper.highlightElement(driver, titlePropEle);

				titlePropValue = titlePropEle.getText().trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return titlePropValue;
	}

	// Get Properties from Document Details Page
	public ArrayList<String> getPropertiesWithCreatorFromDocDetailsPage() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, documentDetailsPropsectXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, propertyHeaderXpathInDocDetails);

			WebElement documentDetailsPropsectEle = UIHelper.findAnElementbyXpath(driver, documentDetailsPropsectXpath);
			UIHelper.highlightElement(driver, documentDetailsPropsectEle);

			WebElement propertyHeaderEleInDocDetails = UIHelper.findAnElementbyXpath(driver,
					propertyHeaderXpathInDocDetails);
			UIHelper.highlightElement(driver, propertyHeaderEleInDocDetails);

			WebElement modifiedDateEle = UIHelper.findAnElementbyXpath(driver, modifiedDateXpath);
			UIHelper.scrollToAnElement(modifiedDateEle);
			UIHelper.highlightElement(driver, modifiedDateEle);

			List<WebElement> propertiesValuesListEle = UIHelper
					.findListOfElementsbyXpath(propertiesListWithCreaterXpath, driver);

			for (WebElement propertyEle : propertiesValuesListEle) {
				UIHelper.highlightElement(driver, propertyEle);
				propertiesValuesList.add(propertyEle.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertiesValuesList;
	}

	public void selectStartWorkflow() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, buttonStartWorkflowXpath);
			driver.findElement(By.xpath(buttonStartWorkflowXpath)).click();

			UIHelper.waitForPageToLoad(driver);

			if (driver.getTitle().contains("Start Workflow")) {
				report.updateTestLog("Start Workflow for File", "Start Workflow page opened Successfully", Status.DONE);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectWorkflowOption() {
		try {
			UIHelper.waitForPageToLoad(driver);

			driver.findElement(By.xpath(dropDownWorkflowXpath)).click();
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectWokflowSimpleReviewApproveXpath);

			String workflowOption = dataTable.getData("Workflow", "Tasktype");

			if (workflowOption.equalsIgnoreCase("Simple Review and Approve")) {
				driver.findElement(By.xpath(selectWokflowSimpleReviewApproveXpath)).click();
			} else if (workflowOption.equalsIgnoreCase("Simple Task")) {
				driver.findElement(By.xpath(selectWokflowSimpleTaskXpath)).click();
			}

			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterSimpleReviewApproveForm() {
		try {
			UIHelper.waitForPageToLoad(driver);

			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String messageBoxText = message + "_" + currentDate;

			UIHelper.waitForVisibilityOfEleByXpath(driver, textBoxMessageXpath);

			WebElement textAreaEle = UIHelper.findAnElementbyXpath(driver, textBoxMessageXpath);
			UIHelper.highlightElement(driver, textAreaEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", textAreaEle);

			textAreaEle.clear();
			textAreaEle.sendKeys(messageBoxText);

			/*
			 * DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); Date
			 * todaysDate = new Date(); String todaysDateString =
			 * dateFormat.format(todaysDate);
			 */
			String dueDate = dataTable.getData("Workflow", "DueDate");

			if (dueDate.contains("'")) {
				finalDueDate = dueDate.replace("'", "");
			} else {
				finalDueDate = dueDate;
			}

			// driver.findElement(By.xpath(textBoxDateXpath)).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, wfDueDateXpath, finalDueDate);
			WebElement dueDateEle = UIHelper.findAnElementbyXpath(driver, wfDueDateXpath);
			if (UIHelper.checkForAnWebElement(dueDateEle)) {
				dueDateEle.sendKeys(Keys.TAB);
			}
			UIHelper.waitFor(driver);

			String priorityValue = dataTable.getData("Workflow", "Priority");

			UIHelper.selectbyVisibleText(driver, selectPriorityXpath, priorityValue);

			String approvalPercentageValue = dataTable.getData("Workflow", "ApprovalPercentage");

			/*
			 * driver.findElement(By.xpath(textBoxApprovalPercentageXpath))
			 * .clear();
			 */
			UIHelper.sendKeysToAnElementByXpath(driver, textBoxApprovalPercentageXpath, approvalPercentageValue);

			String userNames = dataTable.getData("Workflow", "UserName");
			String userNameValues[] = userNames.split(",");

			for (String userName : userNameValues) {
				UIHelper.click(driver, clickReviewersSelectXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, userSearchBoxXpath);
				driver.findElement(By.xpath(userSearchBoxXpath)).clear();
				UIHelper.waitFor(driver);
				UIHelper.sendKeysToAnElementByXpath(driver, userSearchBoxXpath, userName);
				UIHelper.click(driver, userSearchButtonXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, addIconXpath);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, addIconXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, userAddedOkXpath);
				UIHelper.click(driver, userAddedOkXpath);
				UIHelper.waitFor(driver);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sumbitWorkflow() {
		try {
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String messageBoxText = message + "_" + currentDate;

			UIHelper.click(driver, startWorkflowCompleteXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Submit workflow", "<b>Workflow:</b> " + messageBoxText + " submitted successfully",
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnAddItemsButton() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addButtonXpath);
			UIHelper.click(driver, addButtonXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add items for workflow
	public void addMoreItem(String fileName) {
		try {
			String finalXpathForAddFileIcon = tempXpathForAddFileIcon.replace("CRAFT", fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForAddFileIcon);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAddFileIcon)) {
				clickOnAddIcon(finalXpathForAddFileIcon);
			} else {
				String addItemsFrom = dataTable.getData("Workflow", "AddItemsFrom");

				if (addItemsFrom.equalsIgnoreCase("Sites")) {
					openItemInAddItemPopup(addItemsFrom);

					String siteName = dataTable.getData("Sites", "SiteName");

					openItemInAddItemPopup(siteName);

					openItemInAddItemPopup("documentLibrary");

					if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAddFileIcon)) {
						clickOnAddIcon(finalXpathForAddFileIcon);
					}
				}
			}

			report.updateTestLog("Add Item for workflow",
					"<b>File:</b> " + fileName + " added successfully to Items List", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Click Add Icon
	public void clickOnAddIcon(String addItemXpath) {
		try {
			WebElement addIconEle = driver.findElement(By.xpath(addItemXpath));

			UIHelper.highlightElement(driver, addIconEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", addIconEle);

			// driver.findElements(By.xpath(addFileIcon)).get(0).click();
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Open Or Select item in Add Items Popup window
	public void openItemInAddItemPopup(String itemName) {
		try {
			String openItemXpath = tempXpathForSelectItemInCompanyFolder.replace("CRAFT", itemName);

			WebElement openItemEle = driver.findElement(By.xpath(openItemXpath));
			UIHelper.scrollToAnElement(openItemEle);
			UIHelper.highlightElement(driver, openItemEle);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", openItemEle);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInAddItemsPopup);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInAddItemsPopup);

			report.updateTestLog("Open Item", "User able to open <b>Item:</b> " + itemName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Folders in Add Items Section
	public void addMoreItemForFolder(String folderName) {
		try {
			String finalXpathForAddFileIcon = tempXpathForFolderAddFileIcon.replace("CRAFT", folderName);
			WebElement addIconEle = driver.findElement(By.xpath(finalXpathForAddFileIcon));

			UIHelper.highlightElement(driver, addIconEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", addIconEle);

			// driver.findElements(By.xpath(addFileIcon)).get(0).click();
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Item for workflow",
					"<b>Folder:</b> " + folderName + " added successfully to Items List", Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOnAddItemsOkButton() {
		try {
			UIHelper.click(driver, addFileOk);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startworkflow() {
		try {
			UIHelper.waitForPageToLoad(driver);
			WebElement startWorkflowObj = UIHelper.findAnElementbyXpath(driver, startWorkflowXpath);
			UIHelper.mouseOverandElementdoubleClick(driver, startWorkflowObj);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Start Workflow", "Workflow started", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Startworkflow", e.getMessage(), Status.FAIL);
		}

	}

	// To Check Move Link Available or not in Doucument details page
	public void tryToFindMoveLink() {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, clickMoveToInDocumentDetailsSectionXpath)) {
				report.updateTestLog("Verify 'Move to..' option", "'Move to..' option found<br/>", Status.FAIL);
			} else {
				report.updateTestLog("Verify 'Move to..' option",
						"'Move to..' option not displayed<br/><b>Expected Result:</b>'Move to..' option should not found"
								+ "<br><b>Actual Result:</b>'Move to..' option not found",
						Status.PASS);

			}

		} catch (Exception e) {
			report.updateTestLog("Move File", e.getMessage(), Status.FAIL);
		}

	}

	// Get Property Values
	public String getPropertyContainerContent() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, propertyContainerXpath);
		UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, propertyContainerXpath));

		return UIHelper.getTextFromWebElement(driver, propertyContainerXpath);
	}

	// Upload new version
	public void uploadNewVersion() {
		try {
			// String filePath = dataTable.getData("Document_Details",
			// "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String filePath = dataTable.getData("MyFiles", "FilePath");
			filePath = System.getProperty("user.dir") + filePath;
			WebElement uploadNewVersionLinkWebElement = UIHelper.findAnElementbyXpath(driver,
					uploadNewVersionLinkXpath);
			UIHelper.highlightElement(driver, uploadNewVersionLinkWebElement);
			UIHelper.mouseOverandElementdoubleClick(driver, uploadNewVersionLinkWebElement);
			WebElement uploadFileButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadFileButtonXpath);
			UIHelper.highlightElement(driver, uploadFileButtonWebElement);

			// UIHelper.mouseOverandElementdoubleClick(driver,
			// uploadFileButtonWebElement); UIHelper.waitFor(driver);
			// RobotUtil.uploadFile(fileName);

			WebElement fileInput = driver.findElement(By.xpath(uploadInputField));
			fileInput.sendKeys(filePath + fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadButtonXpath);
			WebElement uploadButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadButtonXpath);
			UIHelper.mouseOverandElementdoubleClick(driver, uploadButtonWebElement);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Upload New Version File In DocumentDetails Page",
					"New version File uploded Successfully", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Upload New Version File In DocumentDetails Page", e.getMessage(), Status.FAIL);
		}
	}

	// To get Relationship Table data Value
	public String getRelationshipTableVal() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipTableVal);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, relationshipTableVal));
			UIHelper.highlightElement(driver, relationshipTableVal);
			relationshipTablVal = UIHelper.getTextFromWebElement(driver, relationshipTableVal);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return relationshipTablVal;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public String getRelationshipTableValFromList(String fileOrSiteName) {
		List<WebElement> listRealtionship = new ArrayList<WebElement>();
		relationshipTablVal = "";
		try {
			UIHelper.waitFor(driver);
			listRealtionship = UIHelper.findListOfElementsbyXpath(relationshipTableVal, driver);
			for (WebElement webElement : listRealtionship) {
				if (webElement.getText().equalsIgnoreCase(fileOrSiteName)) {
					UIHelper.scrollToAnElement(webElement);
					UIHelper.highlightElement(driver, webElement);
					relationshipTablVal = webElement.getText();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return relationshipTablVal;
	}

	
	public String getRelationshipValueFromList(String fileorsiteName) {
		List<WebElement> listRealtionship = new ArrayList<WebElement>();
		relationshipTablVal = "";
		try {
			UIHelper.waitFor(driver);
			listRealtionship = UIHelper.findListOfElementsbyXpath(relationshipTableVal, driver);
			for (WebElement webElement : listRealtionship) {
				if (webElement.getText().toLowerCase().contains(fileorsiteName.toLowerCase())) {
					UIHelper.scrollToAnElement(webElement);
					UIHelper.highlightElement(driver, webElement);
					relationshipTablVal = webElement.getText();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return relationshipTablVal;
	}
	
	
	
	// Get the relationship name from the relationship section
	public String getRelationshipValFromList(String relationshipName) {
		List<WebElement> listRealtionship = new ArrayList<WebElement>();
		relationshipVal = "";
		try {
			UIHelper.waitFor(driver);
			listRealtionship = UIHelper.findListOfElementsbyXpath(relationshipTableRel, driver);
			for (WebElement webElement : listRealtionship) {
				if (webElement.getText().equalsIgnoreCase(relationshipName)) {
					UIHelper.scrollToAnElement(webElement);
					UIHelper.highlightElement(driver, webElement);
					relationshipVal = webElement.getText();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return relationshipVal;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean openAndVerifyRelationshipSite() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipTableVal);
			UIHelper.click(driver, relationshipTableVal);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentDetailsTitleXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, documentDetailsTitleXpath))) {
				UIHelper.highlightElement(driver, documentDetailsTitleXpath);
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
	public boolean isPopUpDisplayedInRelationshipFile() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipTableVal);
			UIHelper.highlightElement(driver, relationshipTableVal);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, relationshipTableVal));
			UIHelper.click(driver, relationshipTableVal);
			String popMsgXpath = ".//*[text()='This document is now located in site reg-test-site. We will redirect you to this site shortly']";
			if (UIHelper.checkForAnElementbyXpath(driver, popMsgXpath)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Comments by Sai Kiran : To select Folder in Link Pop Up Window
	private String allSiteButtonXpath = "//button [text()='All Sites']";

	public void selectFolderInLinkPopUp(String siteName) {
		try {
			String destinationFolder = dataTable.getData("Sites", "TargetFolder");
			String finalXpathForSiteSelection = tempXpathForSiteSelection.replace("CRAFT", siteName);
			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
			// finalXpathForSiteSelection);
			UIHelper.click(driver, allSiteButtonXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalXpathForSiteSelection);
			UIHelper.click(driver, finalXpathForSiteSelection);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// btnLinkToInLinkPopUpxpath);
			UIHelper.waitFor(driver);
			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);
			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);

			UIHelper.waitFor(driver);

			report.updateTestLog("Select Folder",
					"Folder selected successfully:</br><b>Folder Name:</b>" + destinationFolder, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Select Folder", "Select Folder Failed", Status.FAIL);
		}

	}

	// Tp Click on Link Button in Link PopUp
	public void clickLinkBtnInLinkPopUp() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnLinkToInLinkPopUpXpath);
			String buttonName = UIHelper.findAnElementbyXpath(driver, btnLinkToInLinkPopUpXpath).getText();
			UIHelper.click(driver, btnLinkToInLinkPopUpXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on Link Button",
					"Folder Linked sucessfully,</br><b>Button Clicked:</b>" + buttonName, Status.PASS);
			UIHelper.pageRefresh(driver);
		} catch (Exception e) {
			report.updateTestLog("Click on Link Button", "Link to folder failed", Status.FAIL);
		}

	}

	// Tp Click on Link Button in Link PopUp
	public String getMessageWhileLinking() {
		String linkMessage = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnLinkToInLinkPopUpXpath);
			String buttonName = UIHelper.findAnElementbyXpath(driver, btnLinkToInLinkPopUpXpath).getText();
			UIHelper.click(driver, btnLinkToInLinkPopUpXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			linkMessage = UIHelper.getTextFromWebElement(driver, messageXpath);
			report.updateTestLog("Click on Link Button",
					" Link Button clicked sucessfully,</br><b>Button Clicked:</b>" + buttonName, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Click on Link Button", "Link to folder failed", Status.FAIL);
		}
		return linkMessage;

	}

	public ArrayList<String> getPropertiesOfFile(String fileName) {
		try {
			propertiesValuesList.clear();
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, propertyContainerXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, propertyContainerXpath));

			UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertyNameXpath);
			UIHelper.highlightElement(driver, filePropertyNameXpath);

			propertiesValuesList.add(UIHelper.findAnElementbyXpath(driver, filePropertyNameXpath).getText());

			UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertyTitleXpath);
			UIHelper.highlightElement(driver, filePropertyTitleXpath);

			propertiesValuesList.add(UIHelper.findAnElementbyXpath(driver, filePropertyTitleXpath).getText());

			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, filePropertyCreateDateXpath));

			UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertyAuthorXpath);
			UIHelper.highlightElement(driver, filePropertyAuthorXpath);
			propertiesValuesList.add(UIHelper.findAnElementbyXpath(driver, filePropertyAuthorXpath).getText());

			UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertyCreateDateXpath);
			UIHelper.highlightElement(driver, filePropertyCreateDateXpath);
			propertiesValuesList.add(UIHelper.findAnElementbyXpath(driver, filePropertyCreateDateXpath).getText());

			UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertyModifierXpath);
			UIHelper.highlightElement(driver, filePropertyModifierXpath);
			propertiesValuesList.add(UIHelper.findAnElementbyXpath(driver, filePropertyModifierXpath).getText());

			UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertyModifiedDateXpath);
			UIHelper.highlightElement(driver, filePropertyModifiedDateXpath);

			propertiesValuesList.add(UIHelper.findAnElementbyXpath(driver, filePropertyModifiedDateXpath).getText());
			report.updateTestLog("Properties of File", "Acquired File Properties", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Properties of File", "Unable to Acquire File Properties", Status.FAIL);
		}
		return propertiesValuesList;
	}

	public void comparePropertiesOfTwoFiles(ArrayList<String> sourceFilePropertiesList,
			ArrayList<String> targetFilePropertiesList) {
		try {
			boolean flag = false;
			flag = UIHelper.compareTwoDiffSizeOfLists(sourceFilePropertiesList, targetFilePropertiesList);
			if (flag) {
				report.updateTestLog("Properties/Locations/Relations of a File",
						"</br><b>Expected Result</b>:File Properties/Relations/Locations of Original "
								+ "file reflected for Target File </br><b>Actual Result:</b>File Properties/Relations/Locations of Original "
								+ "file reflected for Target File ",
						Status.PASS);
			} else {
				report.updateTestLog("Properties/Locations/Relations of a File",
						"</br><b>Expected Result</b>:File Properties/Relations/Locations of Original "
								+ "file reflected for Target File </br><b>Actual Result:</b>File Properties/Relations/Locations of Original "
								+ "file reflected for Target File ",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Properties/Locations/Relations of a File",
					"</br><b>Expected Result</b>:File Properties/Relations/Locations of Original "
							+ "file reflected for Target File </br><b>Actual Result:</b>File Properties/Relations/Locations of Original "
							+ "file reflected for Target File ",
					Status.FAIL);
		}

	}

	public ArrayList<String> getLocations(String fileName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, locationsContainerXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, locationsContainerXpath));
			UIHelper.highlightElement(driver, locationsContainerXpath);

			propertiesValuesList.clear();
			UIHelper.waitForVisibilityOfEleByXpath(driver, originalLocationXpath);
			UIHelper.highlightElement(driver, originalLocationXpath);

			propertiesValuesList.add(UIHelper.findAnElementbyXpath(driver, originalLocationXpath).getText());

			UIHelper.waitForVisibilityOfEleByXpath(driver, secondaryLocationXpath);
			UIHelper.highlightElement(driver, secondaryLocationXpath);
			propertiesValuesList.add(UIHelper.findAnElementbyXpath(driver, secondaryLocationXpath).getText());

			report.updateTestLog("Locations of File", "Acquired File Locations for: " + fileName, Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Locations of File", "Unable to Acquire File Locations for " + fileName, Status.FAIL);
		}
		return propertiesValuesList;
	}

	public ArrayList<String> getRelations(String fileName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationsContainerXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, relationsContainerXpath));
			UIHelper.highlightElement(driver, relationsContainerXpath);
			propertiesValuesList.clear();
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationsXpath);
			UIHelper.highlightElement(driver, relationsXpath);

			propertiesValuesList.add(UIHelper.findAnElementbyXpath(driver, relationsXpath).getText());
			Thread.sleep(1000);
			report.updateTestLog("Relations of File", "Acquired File Relations", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Relations of File", "Unable to Acquire File Relations", Status.FAIL);
		}
		return propertiesValuesList;
	}

	public ArrayList<String> getDocActionOptions() {
		try {

			List<WebElement> selectedItemsMenuList = UIHelper.findListOfElementsbyXpath(docActionOptions, driver);

			for (WebElement selectedItemOptEle : selectedItemsMenuList) {
				UIHelper.scrollToAnElement(selectedItemOptEle);

				docOptionValues.add(selectedItemOptEle.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return docOptionValues;
	}

	// Common method for perform Dcoumnet action
	public void commonMethodForPerformDocAction(String docActionName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			List<WebElement> docActionItemsList = UIHelper.findListOfElementsbyXpath(docActionOptions, driver);

			for (WebElement docActionEle : docActionItemsList) {
				if (docActionEle.getText().contains(docActionName)) {
					docActionEle.click();
					UIHelper.waitForPageToLoad(driver);
					report.updateTestLog("Click on document action: " + docActionName,
							"User able to click the document action: " + docActionName, Status.PASS);
					break;
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Click on document action: " + docActionName,
					"User unable to click the document action: " + docActionName, Status.FAIL);
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param docActionName
	 */
	public void clickDocAction(String docActionName) {
		try {
			List<WebElement> docActionItemsList = UIHelper.findListOfElementsbyXpath(docActionOptions, driver);

			for (WebElement docActionEle : docActionItemsList) {
				if (docActionEle.getText().contains(docActionName)) {
					UIHelper.highlightElement(driver, docActionEle);
					UIHelper.clickanElement(docActionEle);

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isCalculateSizeDisabled() {
		boolean flag = true;
		try {
			if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, calculateSizeXpath))) {
				UIHelper.highlightElement(driver, calculateSizeXpath);
				flag = false;
			} else {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Change Lifecycle state
	public void changeLifeCycleSate(String lifeCycleDropdownVal) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, lifeCycleDropdownXpath);
			UIHelper.highlightElement(driver, lifeCycleDropdownXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, lifecycleFirstOptionXpath);
			UIHelper.selectbyVisibleText(driver, lifeCycleDropdownXpath, lifeCycleDropdownVal);
			report.updateTestLog("Verify LifeCycle state is present",
					"LifeCycle state is present:</b>" + lifeCycleDropdownVal, Status.PASS);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpath);
			WebElement okBtnEle = UIHelper.findAnElementbyXpath(driver, okBtnXpath);
			UIHelper.highlightElement(driver, okBtnEle);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", okBtnEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			UIHelper.waitFor(driver);

			report.updateTestLog("Change LifeCycle state",
					"LifeCycle state changed successfully:</b>" + lifeCycleDropdownVal, Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Property Section Attribute values
	public ArrayList<String> getPropertyAttributeValues() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentDetailsPropsectXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, propertyHeaderXpathInDocDetails);
			UIHelper.waitFor(driver);
			WebElement documentDetailsPropsectEle = UIHelper.findAnElementbyXpath(driver, documentDetailsPropsectXpath);
			UIHelper.highlightElement(driver, documentDetailsPropsectEle);

			WebElement propertyHeaderEleInDocDetails = UIHelper.findAnElementbyXpath(driver,
					propertyHeaderXpathInDocDetails);
			UIHelper.scrollToAnElement(propertyHeaderEleInDocDetails);
			UIHelper.highlightElement(driver, propertyHeaderEleInDocDetails);

			if (UIHelper.checkForAnElementbyXpath(driver, modifiedDateXpath)) {
				WebElement modifiedDateEle = UIHelper.findAnElementbyXpath(driver, modifiedDateXpath);
				UIHelper.scrollToAnElement(modifiedDateEle);
				// UIHelper.highlightElement(driver, modifiedDateEle);
			}

			if (UIHelper.checkForAnElementbyXpath(driver, lastLabelXpathInPropSec)) {
				WebElement lastLabelEleInPropSec = UIHelper.findAnElementbyXpath(driver, lastLabelXpathInPropSec);
				UIHelper.scrollToAnElement(lastLabelEleInPropSec);
			}

			if (UIHelper.checkForAnElementbyXpath(driver, syncSecXpath)) {
				WebElement syncSecEle = UIHelper.findAnElementbyXpath(driver, syncSecXpath);
				UIHelper.scrollToAnElement(syncSecEle);
			}

			List<WebElement> propertyattributesList = UIHelper.findListOfElementsbyXpath(propAttributeXpathInPropSec,
					driver);

			for (WebElement ele : propertyattributesList) {
				actualAttributeValuesFromPropSec.add(ele.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actualAttributeValuesFromPropSec;
	}

	// To Click on LinkTo In docuement Preview Page
	public void clickonLinkToInPreviewPage(String fileName) {

		try {
			driver.navigate().refresh();
			UIHelper.highlightElement(driver, linkToXpath);
			UIHelper.click(driver, linkToXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click Link To", "Sucessfully Clicked on Link To", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Click Link To", e.getMessage(), Status.FAIL);
		}

	}

	// To Click on Cancel button in Link PopUp
	public void clickCancelBtnInLinkPopUp() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnCancelInLinkPopUpXpath);
			UIHelper.click(driver, btnCancelInLinkPopUpXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click Cancel", "Cancelled sucessfully", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Click Cancel", "Cancel Button failed to click", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param propertyName
	 * @return
	 */
	public boolean isDocumentPropertyAvailable(String propertyName) {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, fileOrFolderPropertiesSegementXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, fileOrFolderPropertiesSegementXpath));
			UIHelper.highlightElement(driver, fileOrFolderPropertiesSegementXpath);
			UIHelper.mouseOveranElement(driver,
					UIHelper.findAnElementbyXpath(driver, fileOrFolderPropertiesSegementXpath));
			List<WebElement> filePropertiesList = UIHelper.findListOfElementsbyXpath(fileOrFolderPropertiesXpath,
					driver);
			System.out.println("FILE PROPERTY LIST : " + filePropertiesList.size());
			for (WebElement webElement : filePropertiesList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					String tempString = webElement.getText().trim();
					if (tempString.contains(" (None)")) {
						tempString = tempString.replace(" (None)", "").trim();
					}
					if (propertyName.equals("ISBN 13:*")) {
						propertyName = propertyName.replace("*", "");
					}
					if (tempString.equalsIgnoreCase(propertyName.trim())) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.highlightElement(driver, webElement);
						flag = true;
						break;
					} else {
						flag = false;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param propertyName
	 * @param propertyValue
	 * @return boolean
	 */
	public boolean isDocumentPropertyAvailable(String propertyName, String propertyValue) {
		boolean flag = false;
		try {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, fileOrFolderPropertiesSegementXpath);
			} catch (Exception e) {

			}
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, fileOrFolderPropertiesSegementXpath));
			UIHelper.highlightElement(driver, fileOrFolderPropertiesSegementXpath);
			UIHelper.mouseOveranElement(driver,
					UIHelper.findAnElementbyXpath(driver, fileOrFolderPropertiesSegementXpath));

			List<WebElement> filePropertiesList = UIHelper.findListOfElementsbyXpath(fileOrFolderPropertiesXpath,
					driver);
			List<WebElement> filePropertiesValueList = UIHelper
					.findListOfElementsbyXpath(fileOrFolderPropertiesValueXpath, driver);
			int index = 0;
			for (WebElement webElement : filePropertiesList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					String tempString = webElement.getText().trim();

					if (tempString.contains(" (None)")) {
						tempString = tempString.replace(" (None)", "").trim();
					}
					if (propertyName.equals("ISBN 13:*")) {
						propertyName = propertyName.replace("*", "");
					}

					try {
						if (propertyName.equalsIgnoreCase("Publication Date:")
								&& tempString.equalsIgnoreCase("Publication Date:")) {
							propertyValue = ""
									+ new DateUtil().convertDateToCustomizedFormat("EEE dd MMM YYYY", propertyValue);
						}
					} catch (Exception e) {
					}
					if (tempString.equalsIgnoreCase(propertyName.trim())
							&& filePropertiesValueList.get(index).getText().trim().contains(propertyValue.trim())) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.highlightElement(driver, webElement);
						UIHelper.highlightElement(driver, filePropertiesValueList.get(index));
						flag = true;
						break;
					} else {
						flag = false;
					}
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
	 * @param propertyName
	 * @return
	 */
	public boolean isFilePropertyAvailable(String propertyName, String propertyValue) {
		boolean flag = false;
		try {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, filePropertiesSegementXpath);
			} catch (Exception e) {

			}

			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, filePropertiesSegementXpath));
			UIHelper.highlightElement(driver, filePropertiesSegementXpath);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, filePropertiesSegementXpath));
			List<WebElement> filePropertiesList = UIHelper.findListOfElementsbyXpath(fileOrFolderPropertiesXpath,
					driver);
			List<WebElement> filePropertiesValueList = UIHelper
					.findListOfElementsbyXpath(fileOrFolderPropertiesValueXpath, driver);
			int index = 0;
			for (WebElement webElement : filePropertiesList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					String tempString = webElement.getText().trim();
					if (tempString.contains(" (None)")) {
						tempString = tempString.replace(" (None)", "").trim();
					}
					if (propertyName.equals("ISBN 13:*")) {
						propertyName = propertyName.replace("*", "");
					}
					if (tempString.equalsIgnoreCase("Publication Date:")
							&& tempString.equalsIgnoreCase("Publication Date:")) {
						propertyValue = ""
								+ new DateUtil().convertDateToCustomizedFormat("EEE dd MMM YYYY", propertyValue);
					}
					if (tempString.equalsIgnoreCase(propertyName.trim())
							&& filePropertiesValueList.get(index).getText().trim().contains(propertyValue.trim())) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.highlightElement(driver, webElement);
						UIHelper.highlightElement(driver, filePropertiesValueList.get(index));
						flag = true;
						break;
					} else {
						flag = false;
					}
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
	 */
	public void clickAllProperties() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, allPropertiesButtonXpath);
			UIHelper.highlightElement(driver, allPropertiesButtonXpath);
			UIHelper.click(driver, allPropertiesButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param propertyName
	 * @return
	 */
	public boolean isAllEditPropertiesAvailable(String propertyName) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesSegementXpath);
			UIHelper.highlightElement(driver, editPropertiesSegementXpath);
			List<WebElement> editPropertiesLabelList = UIHelper.findListOfElementsbyXpath(editPropertiesLableListXpath,
					driver);
			for (WebElement webElement : editPropertiesLabelList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					if (webElement.getText().trim().equalsIgnoreCase(propertyName.trim())) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.highlightElement(driver, webElement);
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param propertyName
	 * @return
	 */
	public boolean isNonMandatoryFieldInEditProperties(String propertyName) {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesSegementXpath);
			UIHelper.highlightElement(driver, editPropertiesSegementXpath);
			List<WebElement> editPropertiesLabelList = UIHelper.findListOfElementsbyXpath(editPropertiesLableListXpath,
					driver);
			for (WebElement webElement : editPropertiesLabelList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					if (webElement.getText().trim().equalsIgnoreCase(propertyName.trim())
							&& !(webElement.getText().trim().contains("*"))) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.highlightElement(driver, webElement);
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// To click Cross Mark on Link PopUp
	public void clickCrossMark() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, linkPopUpContainerXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, linkPopUpCrossMarkXpath);
			UIHelper.click(driver, linkPopUpCrossMarkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);

			report.updateTestLog("Click Cross Mark", "Closed sucessfully", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Click Cross Mark", "Cross Mark Button failed to click", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void enterDataAndSaveIntoEditProperties() {
		try {
			ArrayList<String> dataList = enterEditPropertiesData();
			clickSaveInEditProperties();
			/*
			 * report.updateTestLog("Enter and save data into Edit Properties",
			 * "Data entered and saved successfully" +
			 * "<br><b>Data Entered : </b>" + dataList.toString(), Status.PASS);
			 */
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public ArrayList<String> enterEditPropertiesData() {
		String docProperties = dataTable.getData("Document_Details", "DocPropertyValues");
		ArrayList<String> dataList = commonMethodForEnterEditPropertiesData(docProperties);

		return dataList;
	}

	/**
	 * @author 412766
	 * @param docProperties
	 * @return ArrayList<String>
	 */
	public ArrayList<String> commonMethodForEnterEditPropertiesData(String docProperties) {
		ArrayList<String> dataList = new ArrayList<String>();
		try {
			StringTokenizer token = new StringTokenizer(docProperties, ",");
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
					if (title.contains("5*letter code:")) {
						title = title.replace("*", "-");
					}
				}

				List<WebElement> editPropertiesLabelList = UIHelper
						.findListOfElementsbyXpath(editPropertiesLableListXpath, driver);

				int index = 1;
				for (WebElement webElement : editPropertiesLabelList) {
					if (UIHelper.isWebElementDisplayed(webElement)) {
						if (webElement.getText().trim().equalsIgnoreCase(title)) {
							UIHelper.scrollToAnElement(webElement);
							UIHelper.highlightElement(driver, webElement);
							String finalInputBoxXpath = "";
							if (dataType.equalsIgnoreCase("d:text") || dataType.equalsIgnoreCase("d:int")) {
								finalInputBoxXpath = editPropertiesInputXpath.replace("index", String.valueOf(index));
							} else if (dataType.equalsIgnoreCase("d:mltext")) {
								finalInputBoxXpath = editPropertiesTextAreaXpath.replace("index",
										String.valueOf(index));
							}
							UIHelper.highlightElement(driver, finalInputBoxXpath);
							UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath).clear();
							UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath).sendKeys(value);
							break;
						}
					}
					index++;
				}
			}

			report.updateTestLog("Enter data for 'Edit Properties'",
					"User entered data:" + dataList + " for 'Edit Properties'", Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	// Get Edit Prop Value
	public ArrayList<String> getEditPropFieldValue(String docProperties) {
		ArrayList<String> dataList = new ArrayList<String>();
		try {
			StringTokenizer token = new StringTokenizer(docProperties, ",");
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
					dataList.add(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataList;
	}

	/**
	 * @author 412766
	 */
	public void clickSaveInEditProperties() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesSaveButtonXpath);
			UIHelper.highlightElement(driver, editPropertiesSaveButtonXpath);
			UIHelper.click(driver, editPropertiesSaveButtonXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			report.updateTestLog("Click on 'Save' button in 'Edit Properties' page",
					"User clicked the 'Save' button in 'Edit Properties' page", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickCancelInEditProperties() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesCancelButtonXpath);
			UIHelper.highlightElement(driver, editPropertiesCancelButtonXpath);
			UIHelper.click(driver, editPropertiesCancelButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void mouseOverclickSaveInEditProperties() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesSaveButtonXpath);
		UIHelper.highlightElement(driver, editPropertiesSaveButtonXpath);
		UIHelper.mouseOverandclickanElement(driver,
				UIHelper.findAnElementbyXpath(driver, editPropertiesSaveButtonXpath));
		UIHelper.mouseOverandElementdoubleClick(driver,
				UIHelper.findAnElementbyXpath(driver, editPropertiesSaveButtonXpath));

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);

		report.updateTestLog("Click on 'Save' button in 'Edit Properties'",
				"User clicked 'Save' button for save the entered data in 'Edit Properties' page", Status.DONE);
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isEmptyFieldPopUpDisplayed() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, emptyFieldPopUpXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, emptyFieldPopUpXpath))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, emptyFieldPopUpXpath));
				UIHelper.highlightElement(driver, emptyFieldPopUpXpath);
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

	// Comments by Sai Kiran , Function to Navigate to Original File Location
	// after clicking in Origianl Locations Link
	public void navigateToOriginalFileLocation() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, locationsContainerXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, locationsContainerXpath));
			UIHelper.highlightElement(driver, locationsContainerXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, originalLocationXpath);
			String location = UIHelper.findAnElementbyXpath(driver, originalLocationXpath).getText();
			UIHelper.highlightElement(driver, originalLocationXpath);
			UIHelper.click(driver, originalLocationXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Original File Location", "Clicked on Original File Location" + location, Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Original File Location", e.getMessage(), Status.DONE);
		}

	}

	// Comments by Sai Kiran , Function to Navigate to Secondary File Location
	// after clicking in Secondary Locations Link
	public void navigateToSecondaryFileLocation() {

		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, locationsContainerXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, locationsContainerXpath));
			UIHelper.highlightElement(driver, locationsContainerXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, secondaryLocationXpath);
			String location = UIHelper.findAnElementbyXpath(driver, originalLocationXpath).getText();
			UIHelper.highlightElement(driver, secondaryLocationXpath);
			UIHelper.click(driver, secondaryLocationXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Secodary File Location", "Clicked on secondary File Location" + location,
					Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Secondary File Location", e.getMessage(), Status.FAIL);
		}

	}

	// To add or Delete Aspects
	public void addOrDeleteAspectInManageAspect() {

		String aspectsName = dataTable.getData("Document_Details", "AspectName");
		if (aspectsName.contains(",")) {
			StringTokenizer token = new StringTokenizer(aspectsName, ",");
			while (token.hasMoreElements()) {
				String aspect = token.nextElement().toString().trim();
				String finalXpathForAddAspects = tempXpathForAddAspects.replace("CRAFT", aspect);
				report.updateTestLog("Add the Aspect",
						"<b>AspectValue: </b>'" + aspect + "' has been added successfully", Status.PASS);
				UIHelper.click(driver, finalXpathForAddAspects);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

			}
		} else {
			String finalXpathForAddAspects = tempXpathForAddAspects.replace("CRAFT", aspectsName);
			report.updateTestLog("Add the Aspect",
					"<b>AspectValue: </b>'" + aspectsName + "' has been added successfully", Status.PASS);
			UIHelper.click(driver, finalXpathForAddAspects);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		}

		UIHelper.waitForVisibilityOfEleByXpath(driver, applyChangesBtnXpath);

		UIHelper.click(driver, applyChangesBtnXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
		UIHelper.waitFor(driver);
	}

	// To find documentaction
	public boolean isDocumentActionAvilable(String documentAction) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, addRelationshipActionXpath))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, addRelationshipActionXpath));
				UIHelper.highlightElement(driver, addRelationshipActionXpath);
				UIHelper.waitFor(driver);
				flag = true;
			} else
				flag = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// To delete Relationship data
	public void deleteRelationshipData(String fileName) {

		try {
			String actualRelationshipFileName = getRelationshipTableVal();

			if (actualRelationshipFileName.contains(fileName)) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteRelationshipXpath);
				UIHelper.highlightElement(driver, deleteRelationshipXpath);
				UIHelper.click(driver, deleteRelationshipXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteRelationshipPromptXpath);
				UIHelper.highlightElement(driver, deleteRelationshipPromptXpath);
				UIHelper.highlightElement(driver, deleteRelationshipYesBtnXpath);
				UIHelper.click(driver, deleteRelationshipYesBtnXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Delete Relationship data for file",
						"Deleted relationship data to successfully "
								+ "</br><b>Expected Result:</b>Should delete sucessfully "
								+ "</br><b>Actual Result:</b>Deleted Sucessfully ",
						Status.PASS);
			} else {
				report.updateTestLog("Delete Relationship data for file",
						"User unable to delete relationship data to a file"
								+ "</br><b>Expected Result:</b>Should delete sucessfully"
								+ "</br><b>Actual Result:</b>Failed to delete ",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Delete Relationship data for a file", "Delete Relationship data for a file Failed",
					Status.FAIL);
			e.getMessage();
		}
	}

	
	// To click on deleteRelationship with filename its related and the relationship type
	public void clickOnDeleteRelationship(String filename,String relationShip) {
		try {
			UIHelper.waitFor(driver);
			String deletRelationshipWithFilenameAndRelationshipXpath=tempDeletRelationshipWithFilenameAndRelationshipXpath.replace("FILENAME", filename).replace("CRAFT", relationShip);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deletRelationshipWithFilenameAndRelationshipXpath);
			UIHelper.highlightElement(driver, deletRelationshipWithFilenameAndRelationshipXpath);
			UIHelper.click(driver, deletRelationshipWithFilenameAndRelationshipXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteRelationshipPromptXpath);
			report.updateTestLog("Click on Delete Relationship",
					"User clicked on Delete Relationship successfully", Status.DONE);
		}catch(Exception e) {
			report.updateTestLog("Click on Delete Relationship",
					"User clicked on Delete Relationship failed", Status.FAIL);
		}
	}
	
	public void clickOnYesButtonInDeleteRelationship() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteRelationshipYesBtnXpath);
			UIHelper.highlightElement(driver, deleteRelationshipYesBtnXpath);
			UIHelper.click(driver, deleteRelationshipYesBtnXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on Yes in Delete Relationship",
					"User clicked on Yes in Delete Relationship successfully", Status.DONE);
		}catch(Exception e){
			report.updateTestLog("Click on Yes in Delete Relationship",
					"User clicked on Yes in Delete Relationship failed", Status.FAIL);
		}
	}
	/**
	 * @author 412766
	 * @param fileName
	 */
	public void likeADocument(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLikeXpath);
			UIHelper.highlightElement(driver, documentLikeXpath);
			UIHelper.click(driver, documentLikeXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click 'Like' a Document option",
					"User clicked 'Like' option successfully" + "</br><b>Document Name: </b>" + fileName, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Like a Document", "Like a Document Failed", Status.DONE);
		}
	}

	// Add comment to a selected document
	public void Addcomment(String fileName) {
		try {
			System.out.println("1");
			UIHelper.waitForVisibilityOfEleByXpath(driver, addCommentXpath);
			System.out.println("2");
			WebElement addCommentEle = UIHelper.findAnElementbyXpath(driver, addCommentXpath);
			System.out.println("3");
			UIHelper.highlightElement(driver, addCommentEle);
			UIHelper.scrollToAnElement(addCommentEle);

			try {
				deleteCommentsForDoc();
				System.out.println("4");
			} catch (Exception e) {

			}
			System.out.println("5");
			UIHelper.click(driver, addCommentXpath);
			UIHelper.waitFor(driver);
			System.out.println("6");
			WebElement noCommentsSecEle = UIHelper.findAnElementbyXpath(driver, noCommentsSecXpath);
			System.out.println("7");
			UIHelper.scrollToAnElement(noCommentsSecEle);
			System.out.println("8");
			String comment = dataTable.getData("Document_Details", "Comments");
			System.out.println("11");
			driver.switchTo().frame(driver.findElement(By.xpath(inputCommentXpath)));
			System.out.println("12");
			UIHelper.waitFor(driver);

			WebElement iframeCommentsFieldEle = UIHelper.findAnElementbyXpath(driver, iframeCommentsFieldXpath);
			System.out.println("13");
			UIHelper.highlightElement(driver, iframeCommentsFieldEle);
			UIHelper.scrollToAnElement(iframeCommentsFieldEle);
			UIHelper.mouseOverandclickanElement(driver, iframeCommentsFieldEle);
			System.out.println("14");
			RobotUtil.pasteDataIntoInputField(comment);
			System.out.println("15");
			driver.switchTo().defaultContent();
			UIHelper.waitFor(driver);
			System.out.println("16");
			UIHelper.click(driver, saveCommentXpath);
			System.out.println("17");
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, commentWaitXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentXpath);
			if (UIHelper.findAnElementbyXpath(driver, commentXpath).isDisplayed()) {
				report.updateTestLog("Add Comment", "Comment added to file successfully", Status.PASS);

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Add Comment", "Comment added to file failed", Status.FAIL);
		}
	}

	public void deleteCommentsForDoc() {
		try {
			UIHelper.waitFor(driver);

			WebElement locationsEle = UIHelper.findAnElementbyXpath(driver, locationsXpath);
			UIHelper.scrollToAnElement(locationsEle);

			List<WebElement> deleteCommentListEle = UIHelper.findListOfElementsbyXpath(deleteCommentXpath, driver);
			for (WebElement ele : deleteCommentListEle) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", ele);

				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteButtonXpath);
				UIHelper.click(driver, deleteButtonXpath);

				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void capturePageBeforeStrtWF() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, buttonStartWorkflowXpath);

			if (UIHelper.findAnElementbyXpath(driver, buttonStartWorkflowXpath).isDisplayed()) {
				report.updateTestLog("Page before click starting the Work flow", "Page Captured Successfully",
						Status.PASS);

			}

		} catch (Exception e) {
			report.updateTestLog("Capture Page before click starting the Work flow",
					"There is no Start wrkflow Option to proceed", Status.FAIL);
			e.printStackTrace();
		}
	}

	public void verifyLastPageNav() {
		try {
			UIHelper.waitForPageToLoad(driver);
			String messageBoxText = dataTable.getData("Workflow", "Message");
			String userNames = dataTable.getData("Workflow", "UserName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, lastWorkFlowCreatedXpath);
			String actualResult = UIHelper.getTextFromWebElement(driver, lastWorkFlowCreatedXpath);
			if (messageBoxText.equalsIgnoreCase(actualResult) || actualResult.contains(messageBoxText)) {

				report.updateTestLog("Verify the workflow initiation and it is navigating to the last visited Page",
						"User able to initiate workflow and navigated successfully to the last visited Page Successful"
								+ "<br /><b>Expected Workflow Name:</b> " + messageBoxText
								+ ", <br /><b>Actual Workflow Name:</b> " + actualResult,
						Status.PASS);

			} else {
				report.updateTestLog("Verify the workflow initiation and it is navigating to the last visited Page",
						"User unable to initiate workflow and NOT navigated successfully to the last visited Page Successful"
								+ "<br /><b>Expected Workflow Name:</b> " + messageBoxText
								+ ", <br /><b>Actual Workflow Name:</b> " + actualResult,
						Status.FAIL);
			}

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addFilesNotFrmHmePg() {

		UIHelper.waitForPageToLoad(driver);

		UIHelper.click(driver, addButtonXpath);
		// add first available file
		UIHelper.waitFor(driver);
		;
		String navFldrText = "";

		navFldrText = UIHelper.findAnElementbyXpath(driver, btnNavFoldrXPath).getText();
		UIHelper.highlightElement(driver, btnNavFoldrXPath);

		if (navFldrText.contains("Home")) {
			report.updateTestLog("Verify that the file path to add file is displayed not from Home folder Path. ",
					"File Path displayed from Home folder Path", Status.FAIL);
		} else {
			report.updateTestLog("Verify that the file path to add file is displayed not from Home folder Path. ",
					"File Path not displayed from Home and from the file in the Items field", Status.PASS);

		}

		UIHelper.waitFor(driver);

	}

	public String retFileVersionNo() {
		String strVerNo;

		UIHelper.waitForPageToLoad(driver);
		UIHelper.highlightElement(driver, verNoXPath);
		strVerNo = UIHelper.findAnElementbyXpath(driver, verNoXPath).getText();

		UIHelper.waitFor(driver);
		return strVerNo;

	}

	public void clickCancelEditing() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, canEdtXPath);
			UIHelper.highlightElement(driver, canEdtXPath);
			UIHelper.click(driver, canEdtXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on document action: Cancel Editing",
					"User able to click the document action:Cancel Editing and file is back from Edit Offline ",
					Status.PASS);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		}

		catch (Exception e) {
			report.updateTestLog("Click on document action: Cancel Editing",
					"User unable to click the document action:Cancel Editing ", Status.FAIL);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			e.printStackTrace();
		}
	}

	// To Navigate to Properties of a file in document details page
	public void navigateToPropertiesofFile() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, propertyContainerXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, propertyContainerXpath));
			UIHelper.highlightElement(driver, propertyContainerXpath);

			report.updateTestLog("Properties of a file", "Properties of file is listed sucessfully", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Properties of a file", "Unable to navigate to properties", Status.FAIL);
		}
	}

	// Perform Edit Offline doc action for Folder
	public void performEditOfflineDocActionforFolder() {
		try {
			driver.navigate().refresh();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editOfflineXpath);
			UIHelper.click(driver, editOfflineXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			RobotUtil.pressEnter();
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			report.updateTestLog("Perform Edit Offline",
					"User able to click on <b>Documennt Action:</b> Edit Offline for the folder", Status.FAIL);

		} catch (Exception e) {
			report.updateTestLog("Perform Edit Offline for the folder",
					"User unable to perform Edit Offline for the folder ", Status.PASS);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			e.printStackTrace();
		}
	}

	// To Select a folder in link PopUp
	public void selectAFolderInLinkPopUp(String siteName, String destinationFolder) {
		try {

			String finalXpathForSiteSelection = tempXpathForSiteSelection.replace("CRAFT", siteName);
			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteSelection);
			UIHelper.click(driver, finalXpathForSiteSelection);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnLinkToInLinkPopUpxpath);
			UIHelper.waitFor(driver);
			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);
			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);

			UIHelper.waitFor(driver);

			report.updateTestLog("Select Folder",
					"Folder selected successfully:</br><b>Folder Name:</b>" + destinationFolder, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Select Folder", "Select Folder Failed", Status.FAIL);
		}

	}

	public void verifyCreatedWorkflow() {
		try {
			UIHelper.waitForPageToLoad(driver);
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String messageBoxText = message + "_" + currentDate;

			String userNames = dataTable.getData("Workflow", "UserName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, lastWorkFlowCreatedXpath);
			String actualResult = UIHelper.getTextFromWebElement(driver, lastWorkFlowCreatedXpath);
			if (messageBoxText.equalsIgnoreCase(actualResult) || actualResult.contains(messageBoxText)) {

				report.updateTestLog("Verify the workflow creation",
						"User able to initiate workflow for a file and assign it to users:" + userNames + " Successful"
								+ "<br /><b>Expected Workflow Name:</b> " + messageBoxText
								+ ", <br /><b>Actual Workflow Name:</b> " + actualResult,
						Status.PASS);

			} else {
				report.updateTestLog("Verify the workflow creation",
						"User not able to initiate workflow for a file and assign it to users:" + userNames
								+ "Successful" + "<br /><b>Expected Workflow Name:</b> " + messageBoxText
								+ ", <br /><b>Actual Workflow Name:</b> " + actualResult,
						Status.FAIL);
			}

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify Created Work flow for the folder
	public void verifyCreatedWorkflowforFolder() {

		try {
			UIHelper.waitForPageToLoad(driver);
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String messageBoxText = message + "_" + currentDate;
			String userNames = dataTable.getData("Workflow", "UserName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, wrkFlwMsgXPath);
			String actualResult = UIHelper.getTextFromWebElement(driver, wrkFlwMsgXPath);
			if (messageBoxText.equalsIgnoreCase(actualResult) || actualResult.contains(messageBoxText)) {

				report.updateTestLog(
						"Verify the workflow initiation for a folder  Folder action Menu in My Files/Shared Files and Repository.",
						"User able to initiate workflow for a folder :" + userNames + " Successful"
								+ "<br /><b>Expected Workflow Name:</b> " + messageBoxText
								+ ", <br /><b>Actual Workflow Name:</b> " + actualResult,
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify the workflow initiation for a folder  Folder action Menu in My Files/Shared Files and Repository.",
						"User unable to initiate workflow for a folder :" + userNames + " Successful"
								+ "<br /><b>Expected Workflow Name:</b> " + messageBoxText
								+ ", <br /><b>Actual Workflow Name:</b> " + actualResult,
						Status.FAIL);
			}

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// select file in move
	public void selectFileInMove(String targetSiteName) {
		try {

			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", targetSiteName);
			UIHelper.click(driver, finalSelectFileToMoveXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitFor(driver);
			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);
			UIHelper.highlightElement(driver, destFolderContainerEle);

			String destinationFolder = dataTable.getData("Sites", "TargetFolder");
			if (destinationFolder != null && !destinationFolder.isEmpty()) {
				String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);

				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

				WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
				UIHelper.highlightElement(driver, destFolderEle);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", destFolderEle);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Verify Document Action: Move To", "Move file to " + "Target Site = " + targetSiteName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDisplayedVersionHistoryForFile() {
		boolean isDisplayedVerHist = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, versionHistoryHeadingXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, versionHistoryHeadingXpath)) {
				isDisplayedVerHist = true;
				WebElement versionHistoryHeadingEle = UIHelper.findAnElementbyXpath(driver, versionHistoryHeadingXpath);
				UIHelper.scrollToAnElement(versionHistoryHeadingEle);
				UIHelper.highlightElement(driver, versionHistoryHeadingEle);

			} else {
				isDisplayedVerHist = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedVerHist;
	}

	public String getOlderVesrionNumber() {
		String olderVersionNo = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, olderVersionNoXpathForFile);
			if (UIHelper.checkForAnElementbyXpath(driver, olderVersionNoXpathForFile)) {
				olderVersionNo = UIHelper.getTextFromWebElement(driver, olderVersionNoXpathForFile);
				WebElement olderVersionNoEleForFile = UIHelper.findAnElementbyXpath(driver, olderVersionNoXpathForFile);
				UIHelper.scrollToAnElement(olderVersionNoEleForFile);
				UIHelper.highlightElement(driver, olderVersionNoEleForFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return olderVersionNo;
	}

	public void performReverVersionForFile() {
		try {
			UIHelper.waitForPageToLoad(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, revertVersionXpathForFile)) {

				WebElement revertVersionEleForFile = UIHelper.findAnElementbyXpath(driver, revertVersionXpathForFile);
				UIHelper.scrollToAnElement(revertVersionEleForFile);
				UIHelper.highlightElement(driver, revertVersionEleForFile);

				UIHelper.click(driver, revertVersionXpathForFile);

				String versionDetails = dataTable.getData("Document_Details", "Version");

				if (versionDetails.contains("Minor") || versionDetails.equalsIgnoreCase("Minor")) {
					UIHelper.waitForVisibilityOfEleByXpath(driver, minorRadioXpathForRevertVersion);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, minorRadioXpathForRevertVersion);
				} else if (versionDetails.contains("Major") || versionDetails.equalsIgnoreCase("Major")) {
					UIHelper.waitForVisibilityOfEleByXpath(driver, majorRadioXpathForRevertVersion);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, majorRadioXpathForRevertVersion);
				}

				UIHelper.sendKeysToAnElementByXpath(driver, commentsXpathForRevertVersion,
						"Test Comments for Revert Version");

				UIHelper.click(driver, okButtonXpathInRevertVersion);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, revertPopMsgXpath);

				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Revert older version to current version",
						"Older version reverted " + "<br />Version reverted successfully for file", Status.DONE);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLastVesrionNumber() {
		String lastVersionNo = "";
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, lastVersionNoXpathForFile);
			if (UIHelper.checkForAnElementbyXpath(driver, lastVersionNoXpathForFile)) {
				lastVersionNo = UIHelper.getTextFromWebElement(driver, lastVersionNoXpathForFile);

				WebElement lastVersionNoEleForFile = UIHelper.findAnElementbyXpath(driver, lastVersionNoXpathForFile);
				UIHelper.scrollToAnElement(lastVersionNoEleForFile);
				UIHelper.highlightElement(driver, lastVersionNoEleForFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lastVersionNo;
	}

	// Get Parent Window ID
	public String getParentWindowDriverID() {
		String parentWindowDriverID = "";
		try {
			parentWindowDriverID = driver.getWindowHandle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return parentWindowDriverID;
	}

	// Check Child Window ID
	public boolean isDisplayedChildWindowDriverID(String parentWindowDriverID) {
		boolean isDisplayedChildWindow = false;
		try {
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equalsIgnoreCase(parentWindowDriverID)) {
					isDisplayedChildWindow = true;
				} else {
					isDisplayedChildWindow = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedChildWindow;
	}

	/**
	 * @author 412766
	 * @param fileNAme
	 */
	public void clickGoogleShare(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareLinkXpath);
			UIHelper.highlightElement(driver, shareLinkXpath);
			UIHelper.click(driver, shareLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sharePanelXpath);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, sharePanelXpath));
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, sharePanelXpath));
			if (UIHelper.checkForAnElementbyXpath(driver, googleShareXpath)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, googleShareXpath));
				report.updateTestLog("To click Google plus option",
						"Clicked successfully" + "<br /><b>Shared File Name : </b>" + fileName, Status.PASS);
				UIHelper.click(driver, googleShareXpath);
				UIHelper.waitFor(driver);
				new AlfrescoHomePage(scriptHelper).switchtab(1);
				UIHelper.waitForPageToLoad(driver);
			} else {
				report.updateTestLog("To click Google plus option",
						"Not able to Click" + "<br /><b>Shared File Name : </b>" + fileName, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("To click Google plus option", "To click Google share option Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @param sharedPersonEmailId
	 */
	public void shareADocInGooglePlus(String fileName, String sharedPersonEmailId, String emailId) {

		// This method has to be updated with Google share page

		try {
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, gmailUserTextBoxXpath)) {
				UIHelper.highlightElement(driver, gmailUserTextBoxXpath);
				UIHelper.sendKeysToAnElementByXpath(driver, gmailUserTextBoxXpath, emailId);
				UIHelper.highlightElement(driver, gmailNextButtonXpath);
				UIHelper.click(driver, gmailNextButtonXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				report.updateTestLog("To share a document through Google Share doc",
						"Shared successfully" + "<br /><b>Shared File Name : </b>" + fileName
								+ "<br /><b>Shared Person Email : </b>" + sharedPersonEmailId,
						Status.PASS);
				UIHelper.click(driver, shareButtonXpath);
				UIHelper.waitFor(driver);

			}

			/*
			 * UIHelper.waitFor(driver);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, usertextBoxXpath);
			 * UIHelper.highlightElement(driver, usertextBoxXpath);
			 * UIHelper.click(driver, usertextBoxXpath);
			 * UIHelper.waitFor(driver);
			 * 
			 * addEmailId(); UIHelper.waitFor(driver); UIHelper.waitFor(driver);
			 * if (UIHelper.checkForAnElementbyXpath(driver, shareButtonXpath))
			 * { UIHelper.highlightElement(driver, shareButtonXpath);
			 * UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
			 * shareButtonXpath)); report.
			 * updateTestLog("To share a document through Google Share doc",
			 * "Shared successfully" + "<br /><b>Shared File Name : </b>" +
			 * fileName + "<br /><b>Shared Person Email : </b>" +
			 * sharedPersonEmailId, Status.PASS); UIHelper.click(driver,
			 * shareButtonXpath); UIHelper.waitFor(driver); }
			 */ else {
				report.updateTestLog("To share a document through Google Share doc",
						"Document not able to share" + "<br /><b>Shared File Name : </b>" + fileName
								+ "<br /><b>Shared Person Email : </b>" + sharedPersonEmailId,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("To share a document through Google Share doc",
					"To share a document through Google Share doc Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void addEmailId() {
		try {
			UIHelper.click(driver, ".//div[@class='g-h-f-ci g-h-f-Au']/div[2]");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@id,'I')]")));
			UIHelper.waitForVisibilityOfEleByXpath(driver, ".//*[@id=':t']/div[1]/div[1]/img");
			UIHelper.click(driver, ".//*[@id=':t']/div[1]/div[1]/img");
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, ".//*[@class='pg-tg-ug pg-vg-ug']//*[@id='picker:ap:0']");
			UIHelper.click(driver, ".//*[@class='pg-tg-ug pg-vg-ug']//*[@id='picker:ap:0']");
			driver.switchTo().defaultContent();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	// Click Cancel Button in 'Aspects' popup
	public void clickCancelBtnInAspectsPopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelButtonXpathInAddAspectsWindow);
			if (UIHelper.checkForAnElementbyXpath(driver, cancelButtonXpathInAddAspectsWindow)) {
				UIHelper.click(driver, cancelButtonXpathInAddAspectsWindow);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Edit properties

	public void clickOnEditProperties() {

		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesOpXpath);
			UIHelper.click(driver, editPropertiesOpXpath);

			report.updateTestLog("Click on Edit properties", "Edit properties option clicked successfully",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Edit properties", "Failed to click on Edit properties option", Status.FAIL);

		}

	}

	// Attach top notch file in Associated document rule
	public void attachTopNotchFileInAssociatedDocRule(String siteName, String folder, String file) {

		try {
			String finalFileAddXpath = tempXpathForAddAspects.replace("CRAFT", file);

			String folderNavBtnVal = "";
			try {
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInSelectFoldOrFileWindow);
				UIHelper.waitForVisibilityOfEleByXpath(driver, folderNavBtnXpathInSelectWindow);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, folderNavBtnXpathInSelectWindow)) {
				folderNavBtnVal = UIHelper.getTextFromWebElement(driver, displayedValOfFolderNavBtn);

				if (folderNavBtnVal.contains("Company Home")) {
					navigateToCompHomeItemInSelectWindow(siteName, folder, finalFileAddXpath);
				} else {
					UIHelper.click(driver, folderNavBtnXpathInSelectWindow);

					String finalXpathForNavOptionInSelectFolder = tempXpathForNavOptionInSelectFolder.replace("CRAFT",
							"Company Home");
					try {
						UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForNavOptionInSelectFolder);
					} catch (Exception e) {
					}

					UIHelper.click(driver, finalXpathForNavOptionInSelectFolder);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInSelectFoldOrFileWindow);

					navigateToCompHomeItemInSelectWindow(siteName, folder, finalFileAddXpath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Attach Top Notch file", "Failed to Attach Top Notch file", Status.FAIL);

		}

	}

	public void navigateToCompHomeItemInSelectWindow(String siteName, String folder, String finalFileAddXpath) {
		try {
			clickOnItemInLeftPaneInSelectWindow("Sites");
			clickOnItemInLeftPaneInSelectWindow(siteName);

			clickOnItemInLeftPaneInSelectWindow("documentLibrary");

			clickOnItemInLeftPaneInSelectWindow(folder);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileAddXpath);
			UIHelper.highlightElement(driver, finalFileAddXpath);
			UIHelper.click(driver, finalFileAddXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnInAssociatedDocXpath);
			UIHelper.highlightElement(driver, okBtnInAssociatedDocXpath);
			UIHelper.click(driver, okBtnInAssociatedDocXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, okBtnInAssociatedDocXpath);

			report.updateTestLog("Attach Top Notch file", "Top notch file attached successfully", Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnItemInLeftPaneInSelectWindow(String itemName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInSelectFoldOrFileWindow);

			String itemXpath = tempXpathForNavOptionInSelectFolder.replace("CRAFT", itemName);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, itemXpath);
			} catch (Exception e) {
			}

			UIHelper.click(driver, itemXpath);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInSelectFoldOrFileWindow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Associate document rule :
	public boolean checkAssociateDocRuleAvail() {
		boolean flag = false;
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, associateDocRuleXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, associateDocRuleXpath)) {
				flag = true;
				UIHelper.highlightElement(driver, associateDocRuleXpath);
				report.updateTestLog("Check Associate Document rule available",
						"Associate document rule option is available", Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check Associate Document rule available",
					"Associate document rule option is not available", Status.FAIL);
			flag = false;
		}
		return flag;
	}

	// Check Associate document rule :
	public void clickOnAssociateDocRuleAvail() {

		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, associateDocRuleXpath);
			UIHelper.highlightElement(driver, associateDocRuleXpath);
			UIHelper.click(driver, associateDocRuleXpath);

			report.updateTestLog("Click on Associate Document rule",
					"Clicked on Associate document rule option successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Associate Document rule",
					"Failed to click on Associate document rule option", Status.FAIL);

		}

	}

	// Check Document Action Name
	public boolean checkDocumentActionName(String expectedDocActionName) {
		boolean isDiaplayedDocActionName = false;
		try {
			String finalXpathForDocAction = tempXpathForDocAction.replace("CRAFT", expectedDocActionName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDocAction);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForDocAction)) {
				WebElement docActionEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDocAction);
				UIHelper.scrollToAnElement(docActionEle);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, docActionEle);
				isDiaplayedDocActionName = true;
			} else {
				isDiaplayedDocActionName = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDiaplayedDocActionName;
	}

	/**
	 * @author 412766
	 * @param expectedFolderActionName
	 * @return boolean
	 */
	public boolean checkFolderActionName(String expectedFolderActionName) {
		boolean isDiaplayedDocActionName = false;
		try {
			String finalXpathForDocAction = tempXpathForFolderAction.replace("CRAFT", expectedFolderActionName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDocAction);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForDocAction)) {
				WebElement docActionEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDocAction);
				UIHelper.scrollToAnElement(docActionEle);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, docActionEle);
				isDiaplayedDocActionName = true;
			} else {
				isDiaplayedDocActionName = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDiaplayedDocActionName;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isEPMDatamartRecordMapped(String title) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, titleInputXapth);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, titleInputXapth));
			String titleValue = UIHelper.findAnElementbyXpath(driver, titleInputXapth).getAttribute("value");

			UIHelper.highlightElement(driver, progCompTitleInputXapth);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, progCompTitleInputXapth));
			String progCompTitleValue = UIHelper.getTextFromWebElement(driver, progCompTitleInputXapth);

			if (title.equalsIgnoreCase(titleValue) && title.equalsIgnoreCase(progCompTitleValue)
					&& titleValue.equalsIgnoreCase(progCompTitleValue)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Click the Favorite link for selected filename
	public void clickOnFavoriteLinkInDocDetailsPg(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, favoriteLink);
			WebElement favoriteLinkEle = UIHelper.findAnElementbyXpath(driver, favoriteLink);
			UIHelper.highlightElement(driver, favoriteLinkEle);
			favoriteLinkEle.click();
			UIHelper.waitForVisibilityOfEleByXpath(driver, enabledFavoriteLink);

			report.updateTestLog("Click on 'Favorite' option",
					"Clicked the <b>'Favorite'</b> option using <br/><b>File Name:</b> " + fileName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// Check the presence of download button in document details page
		public boolean checkDownloadBtnInDocDetailsPg(String fileName) {
			boolean flag = false;
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, downloadBtn);
				WebElement downloadEle = UIHelper.findAnElementbyXpath(driver, downloadBtn);
				UIHelper.highlightElement(driver, downloadEle);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
	// Check follow option in Document Details Page
	public boolean checkFollowOptionDocDetailPg() {
		boolean isDisplayedFollowOption;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, followOptionLinkXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, followOptionLinkXpath)) {
				isDisplayedFollowOption = true;
			} else {
				isDisplayedFollowOption = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isDisplayedFollowOption = false;
		}

		return isDisplayedFollowOption;
	}

	// Click on Follow link in Document Details Page
	public void clickOnFollowOptionInDocDetailsPg() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, followOptionLinkXpath)) {
				UIHelper.click(driver, followOptionLinkXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, unFollowOptionLinlXpath);

				report.updateTestLog("Click on 'Follow' option", "Clicked the <b>'Follow'</b> link option",
						Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Unfollow option in Document Details Page
	public boolean checkUnfollowOptionDocDetailPg() {
		boolean isDisplayedUnfollowOption = false;
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, unFollowOptionLinlXpath)) {
				isDisplayedUnfollowOption = true;
			} else {
				isDisplayedUnfollowOption = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedUnfollowOption;
	}

	// Click on Follow link in Document Details Page
	public void clickOnUnfollowOptionInDocDetailsPg() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, unFollowOptionLinlXpath)) {
				UIHelper.click(driver, unFollowOptionLinlXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, followOptionLinkXpath);

				report.updateTestLog("Click on 'Unfollow' option", "Clicked the <b>'Unfollow'</b> link option",
						Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Follow link in Document Details Page
	public void hOverOnUnfollowAndClickXIconInDocDetailsPg() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, unFollowOptionLinlXpath)) {
				UIHelper.click(driver, unFollowOptionLinlXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, followOptionLinkXpath);

				report.updateTestLog("Hover on 'Unfollo' and Click on 'X' Icon", "Clicked the <b>'X'</b> Icon",
						Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Option from Document Details Page
	public void clickOptionFromDocActionsPg(String Option) {
		try {

			String finalDocActionXpath = docActionXpath.replace("CRAFT", Option);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalDocActionXpath);
			UIHelper.highlightElement(driver, finalDocActionXpath);
			UIHelper.click(driver, finalDocActionXpath);

			report.updateTestLog("Click option from Document details page", "Clicked the option " + Option,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click option from Document details page", "Failed to click the option " + Option,
					Status.FAIL);
		}
	}

	// Click on cancel option from Document Details Page Review
	// changes,Publication history,status report
	public void clickOnCancelOptionInPopup() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelIconXpath);
			UIHelper.highlightElement(driver, cancelIconXpath);
			UIHelper.click(driver, cancelIconXpath);

			report.updateTestLog("Click on Cancel option from the Pop up", "Clicked the cancel option", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Cancel option from the Pop up", "Failed to Click the cancel option",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isPropertyNameEnabled() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editProNameInputXpath);
			UIHelper.highlightElement(driver, editProNameInputXpath);

			if (UIHelper.findAnElementbyXpath(driver, editProNameInputXpath).isEnabled()) {
				flag = true;
			} else {
				flag = false;
			}

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isInLineEditNameEnabled() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, inLineEditNameInputXapth);
			UIHelper.highlightElement(driver, inLineEditNameInputXapth);

			if (UIHelper.findAnElementbyXpath(driver, inLineEditNameInputXapth).isEnabled()) {
				flag = true;
			} else {
				flag = false;
			}

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isRelationshipAddedForSite() {
		boolean flag = false;

		String xpathRelationshipAdded = ".//table[@id='relationship-function-table']";
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, xpathRelationshipAdded);
			if (UIHelper.findAnElementbyXpath(driver, xpathRelationshipAdded).getText()
					.contains("There aren't any relationships defined yet")) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, xpathRelationshipAdded));
				UIHelper.highlightElement(driver, xpathRelationshipAdded);
				flag = false;
			} else {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, xpathRelationshipAdded));
				UIHelper.highlightElement(driver, xpathRelationshipAdded);
				flag = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Click on added relationship link in document details page
	public void clickOnAddedRelationshipLink(String sourceVisibletxt, String destVisibleText) {
		String finalXpathForAddedRelationshipLink1 = tempXpathForAddedRelationshipLink.replace("CRAFT",
				sourceVisibletxt);
		String finalXpathForAddedRelationshipLink2 = tempXpathForAddedRelationshipLink.replace("CRAFT",
				destVisibleText);
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAddedRelationshipLink1)) {
				UIHelper.highlightElement(driver, finalXpathForAddedRelationshipLink1);
				UIHelper.click(driver, finalXpathForAddedRelationshipLink1);
				report.updateTestLog("Click on Relationship link", "Clicked the Relationship link", Status.DONE);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAddedRelationshipLink2)) {
					UIHelper.highlightElement(driver, finalXpathForAddedRelationshipLink2);
					report.updateTestLog("Verify user navigate to specified location in added relation",
							"User successfully navigated to the specified location where the relationship is associated for this file",
							Status.PASS);
				} else {
					report.updateTestLog("Verify user navigate to specified location in added relation",
							"User failed to navigate to the specified location where the relationship is associated for this file",
							Status.PASS);
				}

			} else {
				report.updateTestLog("Click on Relationship link", "Required relationship link is not found",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickAddRelationshipWidget() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addRelationshipWidgetXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, addRelationshipWidgetXpath))) {
				UIHelper.highlightElement(driver, addRelationshipWidgetXpath);
				UIHelper.click(driver, addRelationshipWidgetXpath);
				report.updateTestLog("Click 'Add Relationship' Widget", "Clicked Successfully", Status.PASS);
				UIHelper.waitFor(driver);
			} else {
				report.updateTestLog("Click 'Add Relationship' Widget", "Widget Not Clicked", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Comments by Moshin : To select Folder in Link Pop Up Window
	public void selectFolderInLinkPopUp(String siteName, String destinationFolder) {
		try {
			/*
			 * String destinationFolder = dataTable.getData("Sites",
			 * "TargetFolder");
			 */
			String finalXpathForSiteSelection = tempXpathForSiteSelection.replace("CRAFT", siteName);
			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteSelection);
			UIHelper.click(driver, finalXpathForSiteSelection);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnLinkToInLinkPopUpxpath);
			UIHelper.waitFor(driver);
			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);
			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);

			UIHelper.waitFor(driver);

			report.updateTestLog("Select Folder",
					"Folder selected successfully:</br><b>Folder Name:</b>" + destinationFolder, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Select Folder", "Select Folder Failed", Status.FAIL);
		}

	}

	/**
	 * @author 412766
	 */
	public void clickAddRelationshipInDashlet() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addRelationshipInDashletXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, addRelationshipInDashletXpath))) {
				UIHelper.highlightElement(driver, addRelationshipInDashletXpath);
				UIHelper.click(driver, addRelationshipInDashletXpath);
				report.updateTestLog("Click 'Add Relationship' option In Dashlet", "Clicked Successfully", Status.PASS);
				UIHelper.waitFor(driver);
			} else {
				report.updateTestLog("Click 'Add Relationship' option In Dashlet", "Not able to Click", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void removeInheritPermissions() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, inheritPermissionsBtnXpath);
			UIHelper.highlightElement(driver, inheritPermissionsBtnXpath);
			UIHelper.click(driver, inheritPermissionsBtnXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, yesBtnXpath);
			UIHelper.highlightElement(driver, yesBtnXpath);
			UIHelper.click(driver, yesBtnXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, saveBtnXpath);
			UIHelper.highlightElement(driver, saveBtnXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, saveBtnXpath))) {
				report.updateTestLog("Remove 'Inherit Permissions'", "Permission removed succesfully", Status.PASS);
			}
			UIHelper.click(driver, saveBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectManagePermissions() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, buttonManagePermissionsXpath);

			report.updateTestLog("Click on Manage permissions", "Clicked on Manage permissions Successfully",
					Status.DONE);
			driver.findElement(By.xpath(buttonManagePermissionsXpath)).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Breadcumb link
	public void checkBreadCumbLinkXpath(String folderName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, nodePathXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, nodePathXpath)
					&& UIHelper.getTextFromWebElement(driver, nodePathXpath).trim().contains(folderName)) {
				report.updateTestLog("Verify Breadcumb Link",
						"Breadcumb Link displayed successfully using folder: " + folderName, Status.PASS);
			} else {
				report.updateTestLog("Verify Breadcumb Link",
						"Breadcumb Link failed to display using folder: " + folderName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param menuName
	 * @return
	 */
	public boolean isDocActionMenuAvailable(String menuName) {
		boolean flag = false;
		try {
			String finalDocActionsMenuXapth = tempdocActionsMenuXapth.replace("CRAFT", menuName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalDocActionsMenuXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalDocActionsMenuXapth))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalDocActionsMenuXapth));
				UIHelper.highlightElement(driver, finalDocActionsMenuXapth);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Click on OK button in Become Owner Popup
	public void clickOnOkBtnInBecomeOwnerPopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForBecomeOwner);

			UIHelper.highlightElement(driver, okBtnXpathForBecomeOwner);
			UIHelper.click(driver, okBtnXpathForBecomeOwner);

			UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			String popupMessageVal = UIHelper.getTextFromWebElement(driver, messageXpath);
			System.out.println(popupMessageVal);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);

			report.updateTestLog("Verify Popup message for Become Owner",
					"Displayed Popup messages as '" + popupMessageVal + "'", Status.PASS);
			// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			// messageXpath);
			UIHelper.pageRefresh(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Popup message for Become Owner", "Displayed Popup messages failed",
					Status.FAIL);
		}
	}

	// select view options
	public void selectDocumentLibView(String viewOption) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, optionsBtnXpath);
			UIHelper.click(driver, optionsBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, simpleViewBtnXpath);
			if (viewOption.contains("Simple View")) {
				UIHelper.highlightElement(driver, simpleViewBtnXpath);
				UIHelper.click(driver, simpleViewBtnXpath);
			} else if (viewOption.contains("Detailed View")) {

				UIHelper.highlightElement(driver, detailedViewBtnXpath);
				UIHelper.click(driver, detailedViewBtnXpath);

			}
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Select document library view",
					"Document library " + viewOption + " view selected successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select document library view", "Failed to select " + viewOption, Status.FAIL);
		}
	}

	// Select shared files folder
	public void commonMethodForSelectSharedFilesFolder(String isReqToselctDocFolder) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, unzipDailogwrapperXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, unzipBtnXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, sharedFilesButtonXpath);
			UIHelper.click(driver, sharedFilesButtonXpath);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadXpathInPathSec);
			UIHelper.waitFor(driver);

			if (isReqToselctDocFolder.equalsIgnoreCase("Yes")) {
				String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
				ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);

				for (String folderName : folderNamesList) {
					String finalDestFolderXpathForTargetSite = destFolderXpathForTargetSite.replace("CRAFT",
							folderName);
					UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalDestFolderXpathForTargetSite);
					UIHelper.click(driver, finalDestFolderXpathForTargetSite);
					UIHelper.waitFor(driver);
				}
			}

			UIHelper.waitFor(driver);

			UIHelper.click(driver, unzipBtnXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check mandatory field in 'Edit Properties' Page
	public boolean checkMandatoryFieldInEditPropPage(String fieldLabelName) {
		boolean isDisplayedFieldAsMandatoryField = false;
		try {
			String finalXpathForFieldLabelInEditPropPage = tempXpathForFieldLabelInEditPropPage.replace("CRAFT",
					fieldLabelName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForFieldLabelInEditPropPage)) {
				String fieldName = UIHelper.getTextFromWebElement(driver, finalXpathForFieldLabelInEditPropPage);
				if (fieldName.contains("*")) {
					isDisplayedFieldAsMandatoryField = true;
				} else {
					isDisplayedFieldAsMandatoryField = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFieldAsMandatoryField;
	}

	// Check Folder size
	public String getFolderSizeFromDocDetailsPage() {
		String promptMessageVal = "";
		try {
			try {
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, viewModeOfSizeFieldXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (UIHelper.checkForAnElementbyXpath(driver, viewModeOfSizeFieldXpath)) {
				WebElement viewModeOfSizeFieldEle = UIHelper.findAnElementbyXpath(driver, viewModeOfSizeFieldXpath);
				UIHelper.scrollToAnElement(viewModeOfSizeFieldEle);
				UIHelper.highlightElement(driver, viewModeOfSizeFieldEle);

				promptMessageVal = UIHelper.getTextFromWebElement(driver, viewModeOfSizeFieldXpath).trim();
			} else {
				promptMessageVal = "Size Not get displayed for selected folders";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return promptMessageVal;
	}

	// Get Value of a specific label in edit properties page
	public String getValueInEditPropertiesInputBox(String lableName) {
		String uRNValue = "";
		try {
			String finalFieldLabelXpath = tempXpathForPopertyFieldLabel.replace("CRAFT", lableName);
			String finalFieldInputXpath = tempXpathForPopertyFieldValue.replace("CRAFT", lableName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalFieldInputXpath);
			UIHelper.highlightElement(driver, finalFieldLabelXpath);

			/*
			 * String value1 = (String)((JavascriptExecutor) driver)
			 * .executeScript(
			 * "return document.getElementById('template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cp_"
			 * +lableName+"').value");
			 */

			uRNValue = driver.findElement(By.xpath(finalFieldInputXpath)).getAttribute("value");
			report.updateTestLog("Get " + lableName + " value", lableName + "Value is " + uRNValue, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Get " + lableName + " value", "Failed to get value", Status.FAIL);
		}
		return uRNValue;
	}

	// check specific label in edit properties page enabled
	public void editInEditPropertiesInputBox(String lableName) {
		try {
			String finalFieldLabelXpath = tempXpathForPopertyFieldLabel.replace("CRAFT", lableName);
			String finalFieldInputXpath = tempXpathForPopertyFieldValue.replace("CRAFT", lableName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFieldLabelXpath);
			UIHelper.highlightElement(driver, finalFieldLabelXpath);
			if (!UIHelper.findAnElementbyXpath(driver, finalFieldInputXpath).isEnabled()) {
				UIHelper.highlightElement(driver, finalFieldInputXpath);
				report.updateTestLog("Check " + lableName + " is enabled to edit", lableName + "is not enabled to edit",
						Status.PASS);
			} else {
				report.updateTestLog("Check " + lableName + " is enabled to edit", lableName + "is enabled to edit",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check " + lableName + " is enabled to edit", lableName + "Failed to display",
					Status.FAIL);
		}
	}

	// Check Pearson Logo
	public boolean checkPearsonLogo() {
		boolean isDisplayedPearsonLogo = false;
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, pearsonLogoXpath)) {
				isDisplayedPearsonLogo = true;
			} else {
				isDisplayedPearsonLogo = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedPearsonLogo;
	}

	// Copy to repository
	public void copytoRepository(String Destination, String Site, String Folder) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			String finalXpathForDestination = tempXpathForDestination.replace("CRAFT", Destination);
			String finalXpathForSite = tempXpathForSite.replace("CRAFT", Site.toLowerCase());
			String finalXpathForfinalDoc = tempXpathForfinalDoc.replace("CRAFT", Folder);
			Thread.sleep(3000);
			// String finalXpathFortitle =
			// copytoRepositorytitle.replace("CRAFT", Site);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// finalXpathFortitle);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, cancelbtn);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSite);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// finalXpathForDestination);
			/*
			 * UIHelper.highlightElement(driver, finalXpathForDestination);
			 * UIHelper.click(driver, finalXpathForDestination);
			 */

			UIHelper.highlightElement(driver, finalXpathForSite);
			UIHelper.click(driver, finalXpathForSite);

			UIHelper.waitForVisibilityOfEleByXpath(driver, tempXpathForSiteDocument);
			UIHelper.highlightElement(driver, tempXpathForSiteDocument);
			UIHelper.click(driver, tempXpathForSiteDocument);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForfinalDoc);
			UIHelper.highlightElement(driver, finalXpathForfinalDoc);
			UIHelper.click(driver, finalXpathForfinalDoc);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, copybtn);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Verify Copy to Repository", "Copy to Repository to  " + "Target Site = " + Site,
					Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Copy to Repository", "Copy to Repository failed", Status.FAIL);
		}
	}

	// Check Field values in Edit Prop page
	public boolean checkFieldValueInEditPropPage(String fieldsData) {
		boolean isDisplayedFieldVal = false;
		try {
			if (fieldsData.contains(";")) {
				String splittedFieldData[] = fieldsData.split(";");
				if (splittedFieldData != null) {
					for (String fieldData : splittedFieldData) {
						isDisplayedFieldVal = subMethodForCheckFieldVal(fieldData);
					}
				}
			} else {
				isDisplayedFieldVal = subMethodForCheckFieldVal(fieldsData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFieldVal;
	}

	public boolean subMethodForCheckFieldVal(String fieldData) {
		boolean isDisplayedFieldVal = false;
		try {
			if (fieldData.contains("-")) {
				String splittedFieldAndVal[] = fieldData.split("-");

				if (splittedFieldAndVal != null & splittedFieldAndVal.length > 1) {
					String finalXpathForEditPropFieldVal = tempXpathForEditPropFieldVal.replace("CRAFT",
							splittedFieldAndVal[0]);

					UIHelper.highlightElement(driver, finalXpathForEditPropFieldVal);
					
					String fieldActualVal = UIHelper.getTextFromWebElement(driver, finalXpathForEditPropFieldVal);
					if(fieldActualVal.isEmpty())
					{
						fieldActualVal=UIHelper.getAttributeFromWebElement(driver, finalXpathForEditPropFieldVal, "value");
					}
					if (fieldActualVal.contains(splittedFieldAndVal[1])
							|| fieldActualVal.equalsIgnoreCase(splittedFieldAndVal[1])) {
						isDisplayedFieldVal = true;
					} else {
						isDisplayedFieldVal = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFieldVal;
	}

	// Copy to repository
	public void copytoRepositoryRepo(String Destination, String Reponame, String folder) {
		try {

			String finalXpathForDestination = tempXpathForDestination.replace("CRAFT", Destination);
			String finalXpathForSite = tempXpathForSite.replace("CRAFT", Reponame);
			// String finalXpathFortitle =
			// copytoRepositorytitle.replace("CRAFT", Site);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// copytoRepositorytitle);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, cancelbtn);
			Thread.sleep(5000);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalXpathForDestination);
			UIHelper.highlightElement(driver, finalXpathForDestination);
			UIHelper.click(driver, finalXpathForDestination);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSite);
			UIHelper.highlightElement(driver, finalXpathForSite);
			UIHelper.click(driver, finalXpathForSite);

			UIHelper.waitForVisibilityOfEleByXpath(driver, tempXpathForSiteDocument);
			UIHelper.highlightElement(driver, tempXpathForSiteDocument);
			UIHelper.click(driver, tempXpathForSiteDocument);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, copybtn);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			// report.updateTestLog("Verify Copy to Repository", "Copy to
			// Repository to " + "Target Site = " + Site,
			// Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Copy to Repository", "Copy to Repository failed", Status.FAIL);
		}
	}

	public void samesiteFolderLink(String siteName, String destinationFolder) {
		try {

			String finalXpathForSiteSelection = tempXpathForSiteSelection.replace("CRAFT", siteName);
			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalXpathForSiteSelection);
			UIHelper.click(driver, finalXpathForSiteSelection);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalXpathForSiteSelection);
			UIHelper.click(driver, finalXpathForSiteSelection);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// btnLinkToInLinkPopUpxpath);
			UIHelper.waitFor(driver);
			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);
			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);

			UIHelper.waitFor(driver);

			report.updateTestLog("Select Folder",
					"Folder selected successfully:</br><b>Folder Name:</b>" + destinationFolder, Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Select Folder", "Select Folder Failed", Status.FAIL);
		}

	}

	// Perform Unzip doc action
	public void performUnzipDocActionCollection(String extractTo) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, unzipDocOptionXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UIHelper.waitFor(driver);
			UIHelper.click(driver, unzipDocOptionXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click On 'Unzip to..' option", "User able to click on 'Unzip to..' option ",
					Status.DONE);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, newUnzipContainerXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, unzipBtnXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, unzipBtnXpath)) {
				report.updateTestLog("Verify 'Unzip to..' popup", "'Unzip to..' popup opened successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Unzip to..' popup", "'Unzip to..' popup opened successfully",
						Status.FAIL);
			}
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, unzipcollection);

			UIHelper.click(driver, unzipBtnXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			} catch (Exception e) {
			}

			String messageVal = "";
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpathForUnzipSuccessOrNotComplete);
				messageVal = UIHelper.getTextFromWebElement(driver, messageXpathForUnzipSuccessOrNotComplete);
			} catch (Exception e) {
			}
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			if (messageVal.contains("Successfully unzipped")) {
				report.updateTestLog("Perform Unzip", "File unzipped successfully", Status.DONE);
			} else if (messageVal.contains("The unzip operation could not be completed")) {
				report.updateTestLog("Perform Unzip",
						"User is not able to perform 'Unzip to' in a folder where already the Unzip operation is performed",
						Status.DONE);
			} else {
				report.updateTestLog("Perform Unzip", "File failed to unzip", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> commonMethodForEnterEditPropertiesDatanew(String docProperties) {
		ArrayList<String> dataList = new ArrayList<String>();
		try {
			StringTokenizer token = new StringTokenizer(docProperties, ";");
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
					if (title.contains("5*letter code:")) {
						title = title.replace("*", "-");
					}
				}

				List<WebElement> editPropertiesLabelList = UIHelper
						.findListOfElementsbyXpath(editPropertiesLableListXpath, driver);

				int index = 1;
				for (WebElement webElement : editPropertiesLabelList) {
					if (UIHelper.isWebElementDisplayed(webElement)) {
						if (webElement.getText().trim().equalsIgnoreCase(title)) {
							UIHelper.scrollToAnElement(webElement);
							UIHelper.highlightElement(driver, webElement);
							String finalInputBoxXpath = "";
							if (dataType.equalsIgnoreCase("d:text") || dataType.equalsIgnoreCase("d:int")) {
								finalInputBoxXpath = editPropertiesInputXpath.replace("index", String.valueOf(index));
							} else if (dataType.equalsIgnoreCase("d:mltext")) {
								finalInputBoxXpath = editPropertiesTextAreaXpath.replace("index",
										String.valueOf(index));
							}
							UIHelper.highlightElement(driver, finalInputBoxXpath);
							UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath).clear();
							UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath).sendKeys(value);
							break;
						}
					}
					index++;
				}
			}

			report.updateTestLog("Enter data for 'Edit Properties'",
					"User entered data:" + dataList + " for 'Edit Properties'", Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	// check specific label in edit properties page enabled
	public void editInEditPropertiesTextarea(String lableName, String value) {
		try {
			String finalFieldLabelXpath = tempXpathForPopertyFieldLabel.replace("CRAFT", lableName);
			String finalFieldInputXpath = tempXpathForPopertyTextarea.replace("CRAFT", lableName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFieldInputXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalFieldInputXpath));
			UIHelper.highlightElement(driver, finalFieldInputXpath);
			UIHelper.click(driver, finalFieldInputXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, finalFieldInputXpath, value);

			report.updateTestLog("Enter Link Properties", lableName + " : " + value, Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// check specific label in edit properties page enabled
	public void editInEditPropertiesInputBox(String lableName, String value) {
		try {
			String finalFieldLabelXpath = tempXpathForPopertyFieldLabel.replace("CRAFT", lableName);
			String finalFieldInputXpath = tempXpathForPopertyFieldValue.replace("CRAFT", lableName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFieldLabelXpath);
			UIHelper.highlightElement(driver, finalFieldLabelXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, finalFieldInputXpath)) {
				UIHelper.highlightElement(driver, finalFieldInputXpath);
				UIHelper.sendKeysToAnElementByXpath(driver, finalFieldInputXpath, value);

				report.updateTestLog("Update " + lableName, lableName + "is updated with Value " + value, Status.PASS);
			} else {
				report.updateTestLog("Check " + lableName + " is enabled to edit", lableName + "is not enabled to edit",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check " + lableName + " is enabled to edit", lableName + "is not enabled to edit",
					Status.FAIL);
		}
	}

	// list of lifecycle dropdown values
	public void lifeCycleDropdown(String lifeCycleDropdown) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, lifeCycleDropdownXpath);
			UIHelper.highlightElement(driver, lifeCycleDropdownXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, lifeCycleDropdownXpath);
			List<WebElement> statesList = UIHelper.findListOfElementsbyXpath(lifeCycleDropdownXpath, driver);
			for (WebElement states : statesList) {
				report.updateTestLog(
						"Verify List of LifeCycle states present in Attach/Promote/Demote Lifecycle dropdown",
						"List of LifeCycle states present in Attach/Promote/Demote Lifecycle dropdown are: <br>"
								+ states.getText(),
						Status.PASS);

			}
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// verify Media Guide option is displaying in Preview of folder
	public void verifyMediaGuideOptionInPreviewFolder(String lableName) {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, mediaGuideXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, mediaGuideXpath));
				UIHelper.highlightElement(driver, mediaGuideXpath);
				report.updateTestLog("Click MediaGuide Option in Preview of Folder",
						" " + lableName + " Option is displayed in Preview of Folder", Status.PASS);
			} else {
				report.updateTestLog("Click MediaGuide Option in Preview of Folder ",
						" " + lableName + " Option is not displayed in Preview of Folder", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * @author 690809
	 * @return Get SourceID PropertyValue
	 */
	public String getSourceIDPropertyValue() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, sourceIDXpath);
		return UIHelper.findAnElementbyXpath(driver, sourceIDXpath).getText();

	}

	/**
	 * @author 690809
	 * @return Get assetGUID PropertyValue
	 */
	public String getAssetGUIDPropertyValue() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, assetGUIDXpath);
		return UIHelper.findAnElementbyXpath(driver, assetGUIDXpath).getText();

	}
	public void clickOnBreadCrumbLink(String folderName)
	{
		try
		{
		String finalBreadCrumbPath=clickBreadCrumbXpath.replace("Craft", folderName);
		UIHelper.highlightElement(driver, finalBreadCrumbPath);
		UIHelper.click(driver, finalBreadCrumbPath);
		UIHelper.waitForPageToLoad(driver);
		report.updateTestLog("Click on 'Breadcrumb' link Folder",
				"Clicked the 'Breadcrumb' using Folder: " + folderName, Status.DONE);
		}catch(Exception e)
		{
			e.printStackTrace();
			report.updateTestLog("Click on 'Breadcrumb' link Folder",
					"Failed to Click the 'Breadcrumb' using Folder: " + folderName, Status.FAIL);
		}
	}
	// Change Lifecycle state
		public String changeLifeCycleSateWithMessage(String lifeCycleDropdownVal) {
			try {
				String expConfMessage="";
				UIHelper.waitForVisibilityOfEleByXpath(driver, lifeCycleDropdownXpath);
				UIHelper.highlightElement(driver, lifeCycleDropdownXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, lifecycleFirstOptionXpath);
				UIHelper.selectbyVisibleText(driver, lifeCycleDropdownXpath, lifeCycleDropdownVal);
				report.updateTestLog("Verify LifeCycle state is present",
						"LifeCycle state is present:</b>" + lifeCycleDropdownVal, Status.PASS);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpath);
				WebElement okBtnEle = UIHelper.findAnElementbyXpath(driver, okBtnXpath);
				UIHelper.highlightElement(driver, okBtnEle);

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", okBtnEle);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				expConfMessage=UIHelper.getTextFromWebElement(driver, messageEleXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				

				report.updateTestLog("Change LifeCycle state",
						"LifeCycle state changed successfully:</b>" + lifeCycleDropdownVal, Status.PASS);
				return expConfMessage;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		//To get the fields present in the New Relationship layout
		public ArrayList<String> getNewRelationShipFieldValues(){
			ArrayList<String> value = new ArrayList<String>();
			
			try {
				UIHelper.waitFor(driver);
				UIHelper.findAndAddElementsToaList(driver, newRelationshipButtonFieldsXpath, value);
				value.add(UIHelper.getTextFromWebElement(driver, neRelationShipHeaderXpath).trim());
				value.add(UIHelper.getTextFromWebElement(driver, newRelationshipVersionFieldXpath).split(":")[0].trim());
				
				Set<String> hs = new HashSet<>();
				hs.addAll(value);
				value.clear();
				value.addAll(hs);
				
				report.updateTestLog("Retrieve New Relationship Prompt fields "," New Relationship prompt fields retrieved", Status.DONE);
			}catch (Exception e) {
				report.updateTestLog("Retrieve New Relationship Prompt fields "," New Relationship prompt fields retrieve failed", Status.FAIL);
			}
			return value;
		}
		
		//To get the fields present in the Delete Relationship layout
		public ArrayList<String> getDeleteRelationShipFieldValues(){
			ArrayList<String> value = new ArrayList<String>();
			try {
				UIHelper.waitFor(driver);
				UIHelper.findAndAddElementsToaList(driver, deleteRelationshipButtonXpath, value);
				value.add(UIHelper.getTextFromWebElement(driver, deleteRelationshipPromptXpath).trim());
				value.add(UIHelper.getTextFromWebElement(driver, deleteRelationShipBodyFieldXpath).split("'/")[0].trim());
				Set<String> hs = new HashSet<>();
				hs.addAll(value);
				value.clear();
				value.addAll(hs);
				report.updateTestLog("Retrieve Delete Relationship Prompt fields "," Delete Relationship prompt fields retrieved", Status.DONE);
			}catch(Exception e) {
				report.updateTestLog("Retrieve Delete Relationship Prompt fields "," Delete Relationship prompt fields retrieve failed", Status.FAIL);
			}
			return value;
		}
}
