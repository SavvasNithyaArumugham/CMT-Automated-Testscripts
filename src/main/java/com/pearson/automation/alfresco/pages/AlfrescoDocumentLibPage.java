package com.pearson.automation.alfresco.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

public class AlfrescoDocumentLibPage extends ReusableLibrary {

	private String commonPageTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String pageBodyXpath = ".//*[@id='bd']";
	private String docLibXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']/a";
	//private String uploadBtnXpath = ".//*[@class='file-upload']//span//button[contains(.,'Upload')]";
    private String uploadBtnXpath = ".//*[@class='file-upload']//span/button[contains(@id,'fileUpload-button')]";
	private String selctFilesToUploadBtnXpath = ".//button[contains(@id,'default-file-selection-button-overlay')]";
	private String myFilesTablesXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']";
	private String uploadedFilesTitlesXpath = "//*[@class='yui-dt-data']//h3/span/a";
	private String myFilesLableLinkXpath = ".//*[contains(@id,'yui-gen')]//a[contains(.,'Documents')] | .//*[contains(@id,'yui-gen')]//a[contains(.,'My Files')]";
	private ArrayList<String> searchResultsList = new ArrayList<String>();
	private String searchResultsListXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']//h3//a";
	private String versionListXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']//h3//span[3]";
	private String checkBoxListXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']//input";
	private String deleteDocumentXpath = ".//*[@id='onActionDelete']/a";
	private String deleteBtnXpath = ".//button[contains(text(),'Delete')]";
	private String selectAnItemsButtonXpath = ".//*[@id='template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-selectedItems-button-button']";
	private String downloadZipXpath = ".//*[contains(text(),'Download as Zip')]";
	private String documentPreviewXpath = ".//*[@id='template_x002e_web-preview_x002e_document-details_x0023_default-viewer-pageContainer-1']/div[2]";
	private String fileLockTitleXpath = ".//*[@id='template_x002e_node-header_x002e_document-details_x0023_default']/div/div[1]/span";
	private String editOfflineXpath = ".//a[@class='action-link']/*[contains(text(),'Edit Offline')]";
	private String tempDocOptionsXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::td//div[4]//a[text()='LINK']";
	private String confirmXpath = "//*[@id='prompt_h']";
	private String tempLikeLinkXpath = ".//*[@class='yui-dt-data']/tr/td//h3/span/a[text()='CRAFT']//ancestor::td//span/a[contains(@class,'like-action')]";
	private String tempLikeCounterXpath = ".//*[@class='yui-dt-data']/tr///h3/span/a[text()='CRAFT']//ancestor::td//span/span[contains(@class,'likes-count')]";
	private String likeCountValue;
	private String tempDocRemoveOptionsXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::td//div[4]//a[contains(@class,'LINK')]";
	private String uploadBtXpatheditoffline = ".//button[@id='onBulkCheckinOkButton-button']";
	private String tempXpathForFollowLink = ".//*[@class='yui-dt-data']//h3/span/a[text()='CRAFT']//ancestor::td//span/a[contains(.,'Follow')]";
	private String tempXpathForUnfollowLink = ".//*[@class='yui-dt-data']//h3/span/a[text()='CRAFT']//ancestor::td//span/a[contains(.,'Unfollow')]";
	private boolean isDisplayedFollowLink;

	private String messageEleXpath = ".//*[@id='message']/div";

	private String tempXpathForMoreSettingsOptLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'MORE_OPT')]";
	//	private String tempXpathForMoreSettingsOptLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//div[@class='sharebox-create-share']/a";

	private String selectedItemsMenuOptionXpath = ".//*[contains(@id,'default-selectedItems-menu')]//*[contains(@id,'yui-gen')]/a/span";
//	private String visibleSelectedItemsXpath = ".//*[@id='yui-main']//*[@id='alf-content']//span/span/button[contains(.,'Selected Items') and not(contains(@disabled,'disabled'))]";
	
	private String visibleSelectedItemsXpath = "//div[contains(@class,'selected-items')]//span/button[not(contains(@disabled,'disabled'))]";

	private String tempXpathForMoreHiddenActions = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]//following-sibling::td[1]//*[@class='more-actions hidden']//a/span";
	private boolean isDiaplyedMoreSettingsHiddenValue;

	private String tempXpathForLifecycleTags = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::h3//following-sibling::div[4]//span";
	private ArrayList<String> fileOrFoldeTagsList = new ArrayList<String>();

	private String tempXpathForBrowseActionForFileOrFolder = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//*[contains(@class,'more-actions')]//a/span";
	private ArrayList<String> browseActionsList = new ArrayList<String>();

	private String selectButtonXpath = ".//*[contains(@id,'default-fileSelect-button-button')]";
//	private String slectAllOptionXpath = ".//*[@class='file-select']//*[@class='bd']//ul/li/a/span[contains(.,'All')]";
	private String slectAllOptionXpath=".//*[@class='file-select']//*[@class='bd']//ul/li/a/span[contains(@class,'selectAll')]";
//	private String deleteFolderXpath = ".//*[@id='Share']//*[@id='prompt']//span[contains(@id,'yui-gen')]/span[contains(.,'Delete')]";
	private String deleteFolderXpath = ".//*[@id='Share']//*[@id='prompt']//span[contains(@id,'yui-gen') and (@class='yui-button yui-push-button')]/span";
	private String messageXpath = ".//*[@id='message']/div";
	private String folderCheckOutMsgXpath = ".//*[@id='message']/div//*[text()='cannot check out folders']";

//	private String selectedItemsXpath = ".//*[@id='yui-main']//*[@id='alf-content']//span/span[contains(.,'Selected Items')]";
	private String selectedItemsXpath = ".//*[@id='yui-main']//*[@id='alf-content']//div[contains(@class,'selected-items')]/span/span";
	
	
//	private String deleteXpath = ".//*[contains(@id,'yui-gen')]/a/span[contains(.,'Delete')]";
	private String deleteXpath = ".//*[contains(@id,'yui-gen')]/a/span[@class='onActionDelete']";
    private String calculateSizeInSelectedItemsMenuXpath=".//*[contains(@id,'yui-gen')]/a/span[@class='calculateSizeforFolders']";
	private String spinnerXpath = ".//*[@id='template_x002e_web-preview_x002e_document-details_x0023_default-viewer']/div/div[11]/div";
	private String imagePreviewXpath = ".//*[@id='template_x002e_web-preview_x002e_document-details_x0023_default-previewer-div']/img";

	private String messageText;

	private String linkPopUpHeadLineXpath = "//*[contains(@id,'copyMoveTo-dialog')]//*[@class='hd']";

	private String editPropertiesXpath = "//*[contains(@id,'dialogTitle')]";
	private String editPropertyTitleInputXpath = "//*[contains(@id,'prop_cm_title')]";
	private String editPropertiesSaveButtonXpath = "//button[contains(text(),'Save')]";

	private String libraryDocumentItemsXpath = ".//*[@id='bd']//*[@id='alfresco-documentlibrary']//*[@class='treeview filter']//*[contains(@class,'documentDroppableHighlights')]//table//tr/td[contains(.,'Documents')]//ancestor::table//following-sibling::div//table//tr/td[3]/span";
	private String librarySecXpath = ".//*[@id='alfresco-documentlibrary']//h2[contains(.,'Library')]";
	private String libFolderSubfolderXpath = ".//*[@id='bd']//*[@id='alfresco-documentlibrary']//*[@class='treeview filter']//*[contains(@class,'documentDroppableHighlights')]//table//tr/td[contains(.,'Documents')]//ancestor::table//following-sibling::div//table//tr/td/span[contains(.,'CRAFT')]/ancestor::div[contains(@class,'ygtvitem selected')]/*[@class='ygtvchildren']//table/tbody/tr/td/span[contains(.,'documentLibrary')]";
	private String docLibFilterXpath = ".//*[@id='alf-filters']";

	private String offlineLockXapth = ".//div[contains(text(),'This document is locked by')]";

	private boolean isDisplayedLibDocumentLibVal;

	private ArrayList<String> docLibFoldersList = new ArrayList<String>();
	public String componentFolderName;

	/*** Program Folders ***/
	private String folderXpath = ".//*[@id='parent-doclib-link']/a";
	private String progfolderXpath = ".//*[@id='alf-filters']//span[text()='CRAFT']";
	private String tempXpathForCategoryItem = ".//*[@id='alf-filters']//*[contains(@style,'block')]//span[contains(text(),'CRAFT')]";
	private String categoriesHeadFilterXpath = ".//*[@id='alf-filters']//h2[text()='Categories']";
	private String breadcrumbXpath = ".//*[contains(@class,'crumb documentDroppable')]/span/a[text()='CRAFT']";
	private String shareLinkXpath = ".//*[@class='link-info']//a";

	private String tempXpathForStrtWflwFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Start Workflow')]";

	private String uploadInputField = ".//*[contains(@id,'default-file-selection-button-overlay')]/span/input";
	private String okBtnXpathInUploadPopup = ".//*[contains(@style,'visible')]//*[@class='bd']//*[@class='first-child']//button[text()='OK']";

	private String uploadFileHeaderXpath = ".//*[contains(@id,'default-dialog_c')]//span[contains(.,'Update File')]";
	private String commentsTextAreaXpath = ".//*[contains(@id,'default-dialog_c') and contains(@style,'visible')]//*[contains(@id,'default-description-textarea')]";
	private String uploadFileButtonXpath = ".//*[contains(@class,'file-selection-control')]/span/span[contains(.,'Select files to upload')]";
	private String uploadButtonXpath = ".//*[contains(@style,'visible')]//*[@class='bd']//*[@class='first-child']//button[contains(@id,'default-upload-button-button')]";

	private String tempXpathForDestFolder = ".//*[contains(@id,'default-copyMoveTo-treeview')]//table/tbody/tr/td[3]/span[contains(.,'CRAFT')]";
	//private String btnCopyInCopyPopUpXpath = "//div[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Copy']";
	private String btnCopyInCopyPopUpXpath ="//div[contains(@id,'default-copyMoveTo-dialog_c')]//span[contains(@id,'copyMoveTo-ok')]//button";
	
	private String destinationDailogXpath = ".//*[contains(@id,'default-copyMoveTo-treeview')]";
	private String selectSiteSecXpath = ".//*[contains(@id,'default-copyMoveTo-sitePicker')]";
	private String selectFileToMoveXpath = ".//*[contains(@id,'default-copyMoveTo-sitePicker')]//a[contains(.,'CRAFT')]";
	private String tempXpathForCollIcon = ".//*[contains(@id,'default-copyMoveTo-treeview')]//table/tbody/tr/td[3]/span[contains(.,'CRAFT')]//ancestor::table//following-sibling::div//span[text()='COLL']//ancestor::table//following-sibling::div//span[text()='COLLECTION']//ancestor::table//a";
	private String tempXpathForCollFolderIcon = ".//*[contains(@id,'default-copyMoveTo-treeview')]//table/tbody/tr/td[3]/span[contains(.,'CRAFT')]//ancestor::table//following-sibling::div//span[text()='COLL']";
	private String linkToPanelXpath = ".//*[contains(@id,'_default-copyMoveTo-dialog')]/div[2]";
	private String linkToMyFilesButtonXpath = ".//*[contains(@id,'_default-copyMoveTo-myfiles-button')]";
	private String linkToButtonXpath = ".//*[contains(@id,'_default-copyMoveTo-ok-button')]";

	private String uploadNewVersionWindowXpath = ".//*[@id='bulkCheckinDialogMarkup_c']/div";

	private String tempXpathForUploadedFile = "//*[@class='documents yui-dt']//*[contains(text(),'CRAFT')]";

	//private String btnMoveInMovePopUpXpath = "//div[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Move']";
	private  String btnMoveInMovePopUpXpath="//div[contains(@id,'default-copyMoveTo-dialog_c')]//span[contains(@id,'copyMoveTo-ok')]//button";
	private String tempXpathForSiteSelection = "//div[@class='site']//a/h4[text()='CRAFT']";
	private String btnLinkToInLinkPopUpxpath = "//div[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Link']";
	private String btnUnLinkInUnLinkPopUpxpath = ".//*[@id='onActionUnLinkFolderFiles-unlink-button']";

	private String majorRadioXpath = ".//*[contains(@style,'visible')]//*[contains(@id,'majorVersion-radioButton')]";
	private String minorRadioXpath = ".//*[contains(@style,'visible')]//*[contains(@id,'minorVersion-radioButton')]";
	private String commentsXpath = ".//*[contains(@style,'visible')]//*[contains(@id,'description-textarea')]";
	private String uploadXpath = ".//*[contains(@class,'file-selection-control')]/span/span[contains(.,'Select files to upload')]";
	private String uploadXpathforeditoff = ".//input[@id='bulkCheckInFiles']";
	private String uploadInputTagXpath = ".//*[contains(@id,'default-file-selection-button-overlay')]/span/input";
	private String uploadBtXpath = ".//button[contains(@id,'default-upload-button-button') and text()='Upload']";
	// "//.//*[contains(@style,'visible')]//*[@class='bd']//*[@class='first-child']//button[contains(@id,'default-upload-button-button')]";
	private String linkImgXpath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//td//img[@title='Linked Node']";
	private String editPropertyNameInputXpath = "//*[contains(@id,'prop_cm_name')]";
	private String editPropDropdownFieldValueXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'FIELD_NAME')]/following-sibling::select";

	/*** EPS ***/

	private String tempPublishLinkXpath = ".//*[@class='yui-dt-data']//h3/span/a[text()='CRAFT']//ancestor::tr/td//img[@alt='pearson-one-inst-published']";
	private String tempPublishInProgressLinkXpath = ".//*[@class='yui-dt-data']//h3/span/a[text()='CRAFT']//ancestor::tr/td[2]//img[@alt='pearson-one-inst-publishing']";
	private boolean isDisplayedPublishLink = false;
	private String pubPopchkXPath = ".//*[@id='zipUpload']";
	private String pubBtnXPath = ".//button[text()='Publish']";
	private String batchPubBtnXpath = ".//button[text()='Batch-Publish']";

	private String tempTargetSiteXpath = ".//*[contains(@id,'_default-copyMoveTo-sitePicker')]/div/a/h4[text()='CRAFT']";
	private String copyButtonXpath = ".//button[text()='Copy']";
	private String sitePickerXpath = ".//*[contains(@id,'_default-copyMoveTo-sitePicker')]";

	private String moveButtonXpath = ".//button[text()='Move']";

	private String linkButtonXpath = "//button[contains(@id,'default-copyMoveTo-ok-button')]";
	// ".//button[text()='Link']";
	private String linkIconXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']/tr/td[2]/div/div[1]/img";

	private String secondaryLinkXpath = ".//*[@id='link-function-table']/table/tbody/tr[2]/td[2]/a";

	private String followOptionXpath = ".//*[@id='Follow-document']";
	private String unfollowOptionXpath = ".//*[@id='Unfollow-document']";

	private String okBtnInRepotScreenXpath = ".//*[@type='button' and text()='OK']";

	private String lstOfSortValXPath = ".//div[@class='sort-field']//div[@class='bd']/ul/li";
	private String btnSortOptXPath = ".//button[contains(@id,'sortField-button')]";
	private String srtDirXPath = ".//button[@title='Sort Ascending']";

	// Eps
	//private String delBtnXPath = ".//button[text()='Delete']";
	private String delBtnXPath ="//div[@class='ft']//span[@class='yui-button yui-push-button']//button";
	private String delPopUpXpath = ".//*[@id='prompt']";
	private String confirmPopUpXpath = ".//*[contains(@class,'panel')]//*[Text()='Publish Zip File']";
	private String zipChkBoxXpath = ".//*[@id='prompt']//*[text()='Please select the checkbox to publish as zip itself']";
	private String zipChkXpath = ".//*[@id='zipUpload']";
	private String zipNotExpChkBoxXpath = ".//*[@id='prompt']//input[@type='checkbox']";

	private String tempXpathForOrangeExclamationMark = ".//*[@class='yui-dt-data']//a[text()='CRAFT']//ancestor::tr//*[@class='status']/*[contains(@alt,'pearson-changed')]";

	private String reviewChamgesPopupXpath = ".//*[@id='Share']//*[contains(@class,'ui-module yui-overlay yui-panel')]/*[contains(@class,'hd') and contains(.,'Review Changes')]";
	private String tempXpathForDisplayValInReviewChangesPopup = ".//*[@id='Share']//*[contains(@class,'ui-module yui-overlay yui-panel')]//table/tbody/tr/td[text()='CRAFT']//ancestor::tr/td[1]/b";

	private String statusReportPopupHeaderXpath = ".//*[@id='Share']//*[contains(@class,'yui-panel-container') and contains(@style,'visible')]//*[contains(@class,'hd') and contains(.,'Status Report')]";
	private String publishedDetailsXpathInStatusReportPopup = ".//*[@id='Share']//*[contains(@class,'yui-panel-container') and contains(@style,'visible')]//*[contains(@class,'yui-module yui-overlay yui-panel')]//table/tbody";

	private String tempXpathForFavoriteLink = ".//*[@class='yui-dt-data']/tr/td//h3/span/a[text()='CRAFT']//ancestor::td//span/a[contains(@class,'favourite-action')]";
	private String tempXpathForEnabledFavoriteLink = ".//*[@class='yui-dt-data']/tr/td//h3/span/a[text()='CRAFT']//ancestor::td//span/a[contains(@class,'favourite-action enabled')]";

	private String copyOrMoveToPopupHeaderXpath = ".//*[contains(@id,'default-copyMoveTo-title')]";
	private String firstSiteItemXpathInCopyOrMovePopup = ".//*[contains(@id,'sitePicker')]/div[1]/a/h4";
	private String sitesListXpathInCopyOrMoveToPopup = ".//*[contains(@id,'sitePicker')]//a/h4";
	private String favoriteSitesLinkXpathInCopyOrMoveToPopup = ".//*[contains(@id,'favouritesites-button')]";
	private String allSitesLinkXpathInCopyOrMoveToPopup = ".//*[contains(@id,'site-button')]";
	private String repositoryLinkXpathInCopyOrMoveToPopup = ".//*[contains(@id,'repository-button')]";
	private String loadingImgXpathInRepositorySec = ".//*[@class='ygtvcell ygtvloading']/a";
	private String sitesListXpathInRepositorySec = ".//*[@class='treeview']//table[contains(.,'Sites')]//following-sibling::div//table[contains(@id,'ygtvtable')]/tbody/tr/td[4]/span[contains(@class,'ygtvlabel')]";

	private String secondaryPathXpath = ".//*[contains(@class,'dialog shadow') and contains(@style,'visible')]//div[@id='secondaryLinks']//input";
	private String unLinkBtnXpath = ".//*[contains(@id,'onActionUnLink-unlink') and contains(@style,'visible')]//button[@id='unlinkButton']";
	private String restAllXpath = ".//*[contains(@class,'dialog shadow') and contains(@style,'visible')]//div[@id='restAll' and contains(@style,'block')]//input";
	private String tempXpathForMessage = ".//*[@id='message']/div/span[contains(.,'CRAFT')]";
	private String nextBtnXpath = ".//*[contains(@class,'dialog shadow') and contains(@style,'visible')]//*[@id='nextButton']";
	private String AllLocationSelectXpath = ".//*[@id='all']";

	private String tempXpathForOpenFolderNameLink = "//*[@class='yui-dt-data']/tr/td//h3/span/a[contains(.,'CRAFT')]";
	private String tempXpathForFolderNameLink = "//*[@class='yui-dt-data']/tr/td//h3/span/a[contains(text(),'CRAFT')]";

	private String commonXpathForFirstDoc = "//*[@class='yui-dt-data']/tr[1]/td//h3/span/a";
    private String folderDetailUnderFolderXpath="//*[@class='yui-dt-data']//h3/span/a[@class='filter-change']";
	private String commonXpathForDocItems = "//*[@class='yui-dt-data']/tr/td//h3/span/a";

	private String defaultBreadcumbLink = ".//*[contains(@id,'default-breadcrumb')]";

	private String okBtnXpath = ".//*[@id='Share']//*[@id='prompt']//span[contains(@id,'yui-gen')]//button[contains(.,'OK')]";
	private String cancelXpath = ".//*[@id='Share']//*[@id='prompt']//span[contains(@id,'yui-gen')]//button[contains(.,'Cancel')]";
	private String cancelBtnNewVersionXpath=".//*[contains(@id,'_dnd-upload')]//button[contains(@id,'_default-cancelOk-button') and .='Cancel']";
	private String externalUploadXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']/tr/td/div/div[2]/span";

	private String docLibOptionMenuXapth = ".//button[contains(@id,'_default-options-button-button')]";
	private String tempOptionMenuValueXapth = ".//span[text()='CRAFT']";

	private String tempXpathForSelectItemTypeInDocLib = ".//*[@class='file-select']//*[@class='bd']//ul/li/a/span[contains(.,'CRAFT')]";
	private String tempOfflineEditMsgXapth = "//*[@class='yui-dt-data']//h3/span/a[text()='CRAFT']//ancestor::td//div[contains(text(),'This document is locked by')]";

	private String promptHeaderXpath = ".//*[@id='prompt_h']";
	private String promptMessageValXpath = ".//*[@id='prompt']/*[@class='bd']";

	private String okBtnXpathInFolderSizePopup = ".//*[@class='button-group']//button[contains(text(),'OK')]";

	private String docActionTwisterCloseXpath = ".//*[contains(@class,'document-actions document-details-panel') or contains(@class,'folder-actions folder-details-panel')]/*[contains(@class,'alfresco-twister-closed')]";
	private String docActionTwisterOpenXpath = ".//*[contains(@class,'document-actions document-details-panel') or contains(@class,'folder-actions folder-details-panel')]/*[contains(@class,'alfresco-twister-open')]";
	private String lodingXpathForDocLib = ".//*[@class='yui-dt-message']/tr/td/*[contains(.,'Loading the Document Library')]";

	private String discriptionFieldXpath = ".//textarea[contains(@id,'_prop_cm_description')]";
	private String firstMenuInCreateOptionXapth = ".//span[text()='Folder']";

	private String categoryTagXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']//h3//a[text()='CRAFT']//ancestor::div/div/span/span/a[text()='CATEGORY']";
	private String tempXpathForCategoryItemLink = ".//*[@class='yui-dt-data']//h3/span/a[text()='FILE_NAME']//ancestor::td//span/a[contains(.,'CATEGORY_ITEM')]";

	private String sortFieldBtnXpath = ".//*[contains(@id,'default-sortField-button-button')]";
	private String sortMenuItemBodyXpath = ".//*[contains(@class,'yui-menu-button-menu') and contains(@style,'visible')]/*[contains(@class,'yui-menu-body')]";
	private String tempXpathForSortMenuItem = ".//*[contains(@class,'yui-menu-button-menu') and contains(@style,'visible')]//li/a[contains(.,'CRAFT')]";
	private String tempXpathForSortBtn = ".//button[contains(.,'CRAFT')]";

	private String tempXpathForBreadCrumb = ".//*[contains(@id,'default-navBar')]//a[text()='CRAFT']";
    //private String tempSelectFolderChkboxXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::tr//td[1]//*[@type='checkbox']"; 
	private String unlinkPopupHeaderTitleXpath = ".//*[@id='onActionUnLinkFolderFiles-title']";
	private String tempXpathForCheckBoxInUnlinkPopup = ".//*[contains(@id,'onActionUnLinkFolderFiles-unlink_c') and contains(@style,'visible')]//*[contains(.,'/Sites/SITE_NAME/documentLibrary/FOLDER_NAME')]//preceding-sibling::input";
	private String tempXpathForCheckBoxInSharedOrMyFileUnliknkPopup = ".//*[contains(@id,'onActionUnLinkFolderFiles-unlink_c') and contains(@style,'visible')]//*[contains(.,'/TAB_NAME/FOLDER_NAME')]//preceding-sibling::input";

	private String sharedFilesBtnXpath = ".//*[contains(@id,'default-copyMoveTo-shared-button')]";
	private String sharedFilesXpathInPathSec = ".//*[@class='path']//*[contains(@id,'ygtvlabel') and contains(.,'Shared Files')]";

	private String myFilesBtnXpath = ".//*[contains(@id,'default-copyMoveTo-myfiles-button')]";
	private String myFilesXpathInPathSec = ".//*[@class='path']//*[contains(@id,'ygtvlabel') and contains(.,'My Files')]";

	private String libraryFilterTwisterCloseXpath = ".//*[contains(@class,'alfresco-twister-closed') and contains(.,'Library')]";
	private String libraryFilterTwisterOpenXpath = ".//*[contains(@class,'alfresco-twister-open') and contains(.,'Library')]";
	private String tempXpathForLibraryFilterItem = ".//*[contains(@class,'alfresco-twister-open') and contains(.,'Library')]/following-sibling::*//*[text()='CRAFT']";

	private String documentsFilterTwisterCloseXpath = ".//*[contains(@class,'alfresco-twister-closed') and contains(.,'Documents')]";
	private String documentsFilterTwisterOpenXpath = ".//*[contains(@class,'alfresco-twister-open') and contains(.,'Documents')]";
	private String tempXpathForDocumentsFilterItem = ".//*[contains(@class,'alfresco-twister-open') and contains(.,'Documents')]/following-sibling::*//a[text()='CRAFT']";
	private String userNameFieldXpath = ".//*[@id='UsernameTextBox']";
	private String canceloncopy = "//*[@class='wrapper']//button[text()='Cancel']";
	public String createlinkxpath = "//*[contains(text(),'Create Link')]";
	public String myfilesxpath = "//a[@title='My Files' and contains(.,'My Files')]";
	public String sharedfilesXpath = "//a[@title='Shared Files' and contains(.,'Shared Files')]";
	public String copy2itemsxpath = "//*[text()='Copy 2 items to...']";

	// Added for NALS project
	private String pubBtnXPathtest = "/html/body/div[2]/div[1]/div[2]/table/tbody/tr[2]/td[6]/img";
	private String finalXpathForPublishGreenLink ="/html/body/div[9]/div[1]/div[2]/div[2]/div[2]/div/div/div[1]/div[2]/div[12]/table/tbody[2]/tr/td[3]/div/div/img";

	
	/*** New Upload ***/
	private String uploadnewvalue = ".//div[@class='REPLACE-version element']//span[contains(text(),'CRAFT')]//following-sibling::span";

	private String editIconTagXpath="//*[@class='yui-dt-data']//h3/span//a[contains(text(),'CRAFT')]//ancestor::div[1]//span[@title='Tag']";
	private String editInputTagXpath="//*[@class='yui-dt-data']//h3/span//a[contains(text(),'CRAFT')]//ancestor::div[1]//span/input[@class='yui-ac-input']";
	
	private String editInputListTagXpath = "//*[@class='yui-dt-data']//h3/span//a[contains(text(),'CRAFT')]//ancestor::div[1]//span/input[@class='yui-ac-input']/../div//li[text()='REPLACETAG']";
	
	private String editTagSaveBtnXpath="//*[@class='yui-dt-data']//a[text()='Save']";
	
	private String editNoTagsLabelTagXpath="//*[@class='yui-dt-data']//h3/span//a[contains(text(),'CRAFT')]//ancestor::div[1]//span[@class='faded'][contains(text(),'No Tags')]";
	
	private String copyMoveToDialogButtonsXpath = "//div[@class='bdft']/span[not(contains(@style,\"display: none\"))]//button[contains(@id,'copyMoveTo')]";
	
	private String copyMoveToDialogueButtonAdvSearchXpath="//div[@class='footer']//span[@class='dijitReset dijitInline dijitButtonText'][contains(@id,'alfresco_buttons_AlfButton')]";
	private String selectNoneButtonXpath=".//*[@class='selectNone']";
	private String fileNameXpath=".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']";
	
	private String tempexcludedfolderMsgXapth = "//*[@class='yui-dt-data']//h3/span/a[text()='CRAFT']//ancestor::td//div[contains(text(),'Folder and all its subfolders and content are excluded from ShareBox sharing.')]";
	private String tempexcludedfileMsgXapth = "//*[@class='yui-dt-data']//h3/span/a[text()='CRAFT']//ancestor::td//div[contains(text(),'Document is excluded from ShareBox sharing.')]";
	private String shareFolderTextXpath = ".//*[contains(@id,'yuievtautoid')]//a//ancestor::tr//div[@class='sharebox-create-share']/a/span";
	private String excludeFromShareboxXpath = ".//*[contains(@id,'yuievtautoid')]//a//ancestor::tr//div[@class='sharebox-exclude']/a/span";
	private String removeShareboxExclusionXpath = ".//*[contains(@id,'yuievtautoid')]//a//ancestor::tr//div[@class='sharebox-exclude-remove']/a/span";
	private String excludeFromShareboxInFolderDetailsXpath = "//div[@class='sharebox-exclude']/a/span[contains(text(),'CRAFT')]";
	private String removeShareboxExclusionInFolderDetailsXpath = "//div[@class='sharebox-exclude-remove']/a/span[contains(text(),'CRAFT')]";
	private String viewDetailsXpath = "//div[@class='link-folder-view-details']/a/span";
	
	private String tempSelectFolderChkboxXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::tr//td[1]//*[@type='checkbox']";
	private String selectedFolderORFileItemXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::td";
	private String tempXpathForMoreOptionLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a[@class='show-more']/span";
	
	private String popupXpath = "//div[@id='prompt']";
//	private String okButtonXpath = "//span[@class='yui-button yui-push-button default']//button";
	private String okButtonXpath = "//div[@id='prompt']//span[@class='yui-button yui-push-button default']/span/button";
	private String cancelButtonXpath = "//span[@class='yui-button yui-push-button']//button[contains(text(),'CRAFT')]";
	private String popupHeaderXpath = "//div[@id='prompt_h'][contains(text(),'CRAFT')]";
	private String popupTextXpath = "//div[@id='prompt']/div[@class='bd'][contains(text(),'')]";
	
	private String loadingMessage = "//div[@class='bd']/span[@class='message'][contains(text(),'CRAFT')]";
	private String folderExcludeMessageXpath = "//div[@class='info-banner']";
	private String tempCalculatedSizeInDocLibXpath="//*[@class='filename']//a[text()='CRAFT']//ancestor::td//div[@class='detail']/span[@class='item'][2]";
	private String loadingMessageTextXpath = "//div[@class='bd']/span[@class='message']/span";
	



	private String copyToRepoLayoutHeaderXpath="//*[@class='yui-panel-container yui-dialog shadow']//div[contains(@id,'default-copyMoveToRepository-title')]";
	private String tempSelectRepositryXpath="//*[contains(@id,'copyMoveToRepository-treeview')]//span[@class='ygtvlabel' and contains(text(),'CRAFT')]";
	private String tempSelectSiteOrFolderUnderSelectedRepositryXpath="//*[contains(@id,'copyMoveToRepository-treeview')]//div[@class='ygtvitem selected']//span[@class='ygtvlabel' and text()='CRAFT']";
	private String copyButtonInCopyToRepoPromptXpath="//*[@class='yui-panel-container yui-dialog shadow']//button[contains(@id,'copyMoveToRepository-ok-button')]";
	
	public AlfrescoDocumentLibPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	/*
	 * Comments by - Sai Kiran Function - To navigate to Document Library
	 */

	public AlfrescoDocumentLibPage navigateToDocumentLibrary() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, docLibXpath);
			UIHelper.click(driver, docLibXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);

		} catch (Exception e) {

		}
		return new AlfrescoDocumentLibPage(scriptHelper);

	}

	/*
	 * Comments by - Sai Kiran Function - To Upload File in Document Library
	 * Page
	 */
	public AlfrescoDocumentLibPage uploadFileInDocumentLibPage() {
		try {
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String finalFilePath = System.getProperty("user.dir") + filePath;

			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			WebElement uploadBtnEle = driver.findElement(By.xpath(uploadBtnXpath));
			if (UIHelper.checkForAnWebElement(uploadBtnEle)) {
				UIHelper.highlightElement(driver, uploadBtnEle);
				driver.findElement(By.xpath(uploadBtnXpath)).click();
				UIHelper.waitForVisibilityOfEleByXpath(driver, selctFilesToUploadBtnXpath);
				WebElement uploadInputFieldEle = driver.findElement(By.xpath(selctFilesToUploadBtnXpath));
				UIHelper.highlightElement(driver, uploadInputFieldEle);

				WebElement fileInput = driver.findElement(By.xpath(uploadInputField));
				fileInput.sendKeys(finalFilePath + fileName);

				/*
				 * try{
				 * UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
				 * okBtnXpathInUploadPopup); } catch(Exception e) {
				 * e.printStackTrace(); }
				 * 
				 * if(UIHelper.checkForAnElementbyXpath(driver,
				 * okBtnXpathInUploadPopup)) { UIHelper.click(driver,
				 * okBtnXpathInUploadPopup); }
				 */

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Upload File",
						"Upload file using -" + "<b>FilePath:</b>" + filePath + ", " + "<b>FileName:</b>" + fileName,
						Status.DONE);
			}
		} catch (Exception e) {
		}

		return new AlfrescoDocumentLibPage(scriptHelper);
	}

	/*
	 * Comments by - Sai Kiran, Function - To return list of documents in
	 * library
	 */
	public ArrayList<String> getSearchResultsTitle() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
			List<WebElement> searchResultsListEle = null;
			searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));

			for (WebElement ele : searchResultsListEle) {
				searchResultsList.add(ele.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchResultsList;
	}

	/*
	 * Comments by - Sai Kiran, Function - To delete document from Document
	 * Library
	 */

	public AlfrescoDocumentLibPage deleteFileInDocumentLib() {
		try {

			String fileName = dataTable.getData("MyFiles", "FileName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.clickanElement(ele);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, deleteDocumentXpath);
					UIHelper.click(driver, deleteDocumentXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, deleteBtnXpath);
					UIHelper.click(driver, deleteBtnXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);

					report.updateTestLog("Delete Document", "Document deleted sucessfully", Status.DONE);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoDocumentLibPage(scriptHelper);
	}

	/**
	 * Method to check the file is available or not
	 * 
	 * @param fileName
	 * @return boolean
	 * @author 412766
	 */
	public boolean checkFileIsAvailable(String fileName) {

		boolean flag = isFileIsAvailable(fileName);
		if (flag) {
			report.updateTestLog("Check file is available", "File available" + "<br /><b>File Name : </b>" + fileName,
					Status.PASS);
		} else {
			report.updateTestLog("Check file is available",
					"File not available" + "<br /><b>File Name : </b>" + fileName, Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @return
	 */
	public boolean isFileIsAvailable(String fileName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.scrollToAnElement(ele);
					flag = true;
					break;

				}
			}
		} catch (Exception e) {
			report.updateTestLog("Check file is available", "Check file is available Failed", Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @return
	 */
	public boolean isExternalUploadTagAvailable(String textToVerify) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(externalUploadXpath));
			for (WebElement ele : searchResultsListEle) {
				if (ele.getText().contains(textToVerify)) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.scrollToAnElement(ele);
					flag = true;
					break;

				}
			}
		} catch (Exception e) {
			report.updateTestLog("Check External Upload Tag is available",
					"Check External Upload Tag is available Failed", Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @return
	 */
	public boolean isLinkIconAvailable(String fileName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, linkIconXpath))) {
						UIHelper.highlightElement(driver, linkIconXpath);
						flag = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Check Linked icon is available", "Check Linked icon is available Failed",
					Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @return
	 */
	public void checkVersionAvailable(String fileName, String message) {
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			List<WebElement> versionListEle = driver.findElements(By.xpath(versionListXpath));
			int index = 0;
			String expectedVersion = dataTable.getData("MyFiles", "Version");
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.waitFor(driver);
					UIHelper.waitForAnElement(driver, versionListEle.get(index));
					UIHelper.mouseOveranElement(driver, versionListEle.get(index));
					String actualVersion = versionListEle.get(index).getText();
					if (actualVersion.contains(expectedVersion)) {
						UIHelper.highlightElement(driver, versionListEle.get(index));
						report.updateTestLog(message,
								"Version not incremented" + "<br /><b>File Name : </b>" + fileName
										+ "<br /><b>Expected Version Number : </b>" + expectedVersion
										+ "<br /><b>Actual Version Number : </b>" + actualVersion.toString(),
								Status.PASS);
					} else {
						report.updateTestLog(message,
								"Version incremented" + "<br /><b>File Name : </b>" + fileName
										+ "<br /><b>Expected Version Number : </b>" + expectedVersion
										+ "<br /><b>Actual Version Number : </b>" + actualVersion.toString(),
								Status.FAIL);
					}

				}
				index++;
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog(message, "Verify the file version Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @param message
	 */
	public void checkVersionIncremented(String fileName, String message) {
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			List<WebElement> versionListEle = driver.findElements(By.xpath(versionListXpath));
			int index = 0;
			String expectedVersion = dataTable.getData("MyFiles", "Version");
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.waitFor(driver);
					UIHelper.waitForAnElement(driver, versionListEle.get(index));
					UIHelper.mouseOveranElement(driver, versionListEle.get(index));
					String actualVersion = versionListEle.get(index).getText();
					if (actualVersion.equals(expectedVersion)) {
						UIHelper.highlightElement(driver, versionListEle.get(index));
						report.updateTestLog(message,
								"Version incremented" + "<br /><b>File Name : </b>" + fileName
										+ "<br /><b>Expected Version Number : </b>" + expectedVersion
										+ "<br /><b>Actual Version Number : </b>" + actualVersion.toString(),
								Status.PASS);
					} else {
						report.updateTestLog(message,
								"Version not incremented" + "<br /><b>File Name : </b>" + fileName
										+ "<br /><b>Expected Version Number : </b>" + expectedVersion
										+ "<br /><b>Actual Version Number : </b>" + actualVersion.toString(),
								Status.FAIL);
					}

				}
				index++;
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog(message, "Verify the file version Failed", Status.FAIL);
		}
	}

	/**
	 * Method to open a file in document library page
	 * 
	 * @param fileName
	 * @author 412766
	 */
	public void openAFile(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * searchResultsListXpath);
			 */
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.clickanElement(ele);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);

					if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
						UIHelper.click(driver, docActionTwisterCloseXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
						UIHelper.waitFor(driver);
					}

					report.updateTestLog("Open a file in Document Library",
							"File opened successfully" + "<br /><b>File Name :</b> " + fileName, Status.DONE);
					/*
					 * if (isDocumentPreviewed() || isImagePreviewed()) {
					 * report.updateTestLog("Open a file in Document Library",
					 * "File opened successfully" + "<br /><b>File Name :</b> "
					 * + fileName, Status.DONE); } else {
					 * report.updateTestLog("Open a file in Document Library",
					 * "File not opened" + "<br /><b>File Name :</b> " +
					 * fileName, Status.FAIL); }
					 */
					break;
				}
			}

		} catch (Exception e) {
			report.updateTestLog("Open a '<b>" + fileName + "</b>' file in Document Library",
					"Open a '<b>" + fileName + "</b>' file in Document Library Failed", Status.FAIL);
		}
	}

	/**
	 * Method to check the file is locked or not
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isFileLocked() {
		boolean flag = false;
		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, fileLockTitleXpath))) {
				UIHelper.highlightElement(driver, fileLockTitleXpath);
				UIHelper.waitFor(driver);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
		}
		return flag;
	}

	/**
	 * Method to lock a file by clicking offline Edit option
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean lockFile(String fileName) {
		boolean flag = false;
		try {
			editOffline(fileName);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, fileLockTitleXpath))) {
				report.updateTestLog("To lock a File",
						"File locked successfully" + "<br /><b>File Name :</b> " + fileName, Status.PASS);
			} else {
				report.updateTestLog("To lock a File", "File lock Failed" + "<br /><b>File Name :</b> " + fileName,
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("To lock a File", "To lock a File Failed", Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void editOffline(String fileName) {

		UIHelper.waitForVisibilityOfEleByXpath(driver, editOfflineXpath);
		UIHelper.highlightElement(driver, editOfflineXpath);
		UIHelper.click(driver, editOfflineXpath);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, fileLockTitleXpath);
		UIHelper.highlightElement(driver, fileLockTitleXpath);
		UIHelper.waitFor(driver);

		if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, fileLockTitleXpath))) {
			report.updateTestLog("Apply 'Edit-Offline' to a File",
					"Applied successfully" + "<br /><b>File Name :</b> " + fileName, Status.PASS);
		} else {
			report.updateTestLog("Apply 'Edit-Offline' to a File",
					"Not Applied" + "<br /><b>File Name :</b> " + fileName, Status.FAIL);
		}
	}

	/**
	 * Method to download a file or folder as ZipFile
	 * 
	 * @return AlfrescoDocumentLibPage
	 * @author 412766
	 */
	public AlfrescoDocumentLibPage downloadAsZipInDocumentLib(String fileName) {
		try {

			if (fileName == null) {
				fileName = dataTable.getData("MyFiles", "FileName");
			}
			String filePath = Settings.getInstance().getProperty("DefaultDownloadPath");
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			List<WebElement> checkBoxListEle = driver.findElements(By.xpath(checkBoxListXpath));
			int index = 0;
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					// UIHelper.mouseOveranElement(driver, ele);

					UIHelper.highlightElement(driver, checkBoxListEle.get(index));
					UIHelper.clickanElement(checkBoxListEle.get(index));

					UIHelper.waitForVisibilityOfEleByXpath(driver, selectAnItemsButtonXpath);
					UIHelper.click(driver, selectAnItemsButtonXpath);

					UIHelper.waitForVisibilityOfEleByXpath(driver, downloadZipXpath);
					UIHelper.click(driver, downloadZipXpath);

					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
					UIHelper.waitFor(driver);
					File zipFile = new File(filePath + "/" + fileName + ".zip");
					long start_time = System.currentTimeMillis();
					long wait_time = 200000;
					long end_time = start_time + wait_time;
					while (System.currentTimeMillis() < end_time) {
						if (zipFile.exists()) {
							report.updateTestLog("Download Zip file", "Zip File downloaded sucessfully", Status.DONE);
							break;
						}
					}
					if (!zipFile.exists()) {
						report.updateTestLog("To download a file", "To download a file Failed", Status.FAIL);
					}
					break;
				}

				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Download Zip file", e.getMessage(), Status.DONE);
		}
		return new AlfrescoDocumentLibPage(scriptHelper);
	}

	/**
	 * Method to check the given folder available or not.
	 * 
	 * @param folderName
	 * @return boolean
	 * @author 412766
	 * 
	 */
	public boolean isNavigateFolderInDocLibrary(String folderName) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (folderName.equals(ele.getText())) {
					flag = true;
					UIHelper.highlightElement(driver, ele);
					UIHelper.clickanElement(ele);
					UIHelper.waitFor(driver);
					break;
				} else {
					flag = false;
				}
			}
		} catch (Exception e) {

		}
		return flag;
	}

	/**
	 * Method to check the document previewed or not in document preview page
	 * 
	 * @param fileName
	 * @author 412766
	 */
	public boolean isDocumentPreviewed() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentPreviewXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, documentPreviewXpath))) {
				UIHelper.highlightElement(driver, documentPreviewXpath);
				flag = true;
			} else {
				flag = false;
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Click the like for selected filename
	public void clickOnFavoriteLink(String fileName) {
		try {
			highlightTheDocLibSection(fileName);
			String finalXpathForFavoriteLink = tempXpathForFavoriteLink.replace("CRAFT", fileName);

			String finalXpathForEnabledFavoriteLink = tempXpathForEnabledFavoriteLink.replace("CRAFT", fileName);

			WebElement favoriteLinkEle = UIHelper.findAnElementbyXpath(driver, finalXpathForFavoriteLink);
			UIHelper.highlightElement(driver, favoriteLinkEle);
			favoriteLinkEle.click();
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForEnabledFavoriteLink);

			report.updateTestLog("Click on 'Favorite' option",
					"Clicked the <b>'Favorite'</b> option using <br/><b>File Name:</b> " + fileName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Click the like for selected filename
	public void clickLikeLink(String fileName) {
		try {
			highlightTheDocLibSection(fileName);
			String finalLikeEleXpath = tempLikeLinkXpath.replace("CRAFT", fileName);
			WebElement likeLinkEle = UIHelper.findAnElementbyXpath(driver, finalLikeEleXpath);
			UIHelper.highlightElement(driver, likeLinkEle);
			likeLinkEle.click();

			report.updateTestLog("Click on 'Like' option",
					"Clicked the <b>'Like'</b> link using <br/><b>File Name:</b> " + fileName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Get the like count
	public String getLikeCount(String fileName) {
		try {
			highlightTheDocLibSection(fileName);
			String finalCountEleForLikeXpath = tempLikeCounterXpath.replace("CRAFT", fileName);
			WebElement likeCountEle = UIHelper.findAnElementbyXpath(driver, finalCountEleForLikeXpath);
			likeCountValue = likeCountEle.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return likeCountValue;
	}

	public boolean isDisplayedFollowLink(String fileName) {
		try {

			highlightTheDocLibSection(fileName);
			String finalXpathForFollowLink = tempXpathForFollowLink.replace("CRAFT", fileName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForFollowLink)) {
				UIHelper.highlightElement(driver, finalXpathForFollowLink);
				isDisplayedFollowLink = true;
			} else {
				isDisplayedFollowLink = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFollowLink;
	}

	public void highlightTheDocLibSection(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);

			/*
			 * WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver,
			 * pageBodyXpath); UIHelper.highlightElement(driver, pageBodyEle);
			 * UIHelper.mouseOveranElement(driver, pageBodyEle);
			 * 
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * myFilesTablesXpath); WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileName)) {
					UIHelper.highlightElement(driver, ele);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Click on Follow option
	public void clickOnFollowOption(String fileName) {
		try {
			try {
				highlightTheDocLibSection(fileName);
			} catch (Exception e) {
			}
			String finalXpathForFollowLink = tempXpathForFollowLink.replace("CRAFT", fileName);
			String finalXpathForUnfollowLink = tempXpathForUnfollowLink.replace("CRAFT", fileName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForFollowLink)) {
				UIHelper.click(driver, finalXpathForFollowLink);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForUnfollowLink);

				UIHelper.waitFor(driver);

				report.updateTestLog("Click on 'Follow' option",
						"Clicked the <b>'Follow'</b> link using <br/><b>File Name:</b> " + fileName, Status.DONE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Unfollow option
	public boolean isDisplayedUnfollowLink(String fileName) {
		try {
			highlightTheDocLibSection(fileName);
			String finalXpathForFollowLink = tempXpathForUnfollowLink.replace("CRAFT", fileName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForFollowLink)) {
				UIHelper.highlightElement(driver, finalXpathForFollowLink);
				isDisplayedFollowLink = true;
			} else {
				isDisplayedFollowLink = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFollowLink;
	}

	/**
	 * @author 412766
	 */
	public void mouseOverOnUnFollowedOption(String fileName) {
		try {
			String finalXpathForFollowLink = tempXpathForUnfollowLink.replace("CRAFT", fileName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForFollowLink)) {
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, finalXpathForFollowLink));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Unfollow otpion
	public void clickOnUnfollowOption(String fileName) {
		try {
			highlightTheDocLibSection(fileName);
			String finalXpathForFollowLink = tempXpathForFollowLink.replace("CRAFT", fileName);
			String finalXpathForUnfollowLink = tempXpathForUnfollowLink.replace("CRAFT", fileName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForUnfollowLink)) {
				UIHelper.click(driver, finalXpathForUnfollowLink);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFollowLink);

				UIHelper.waitFor(driver);

				report.updateTestLog("Click on 'Un Follow' option",
						"Clicked the <b>'Un Follow'</b> action using <br/><b>File Name:</b> " + fileName, Status.DONE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyEditOffline() {

		try {
			UIHelper.highlightElement(driver, editOfflineXpath);
			UIHelper.click(driver, editOfflineXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Test that user is able to edit a file", "Sucessfully locked for edit", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Test that user is able to edit a file", e.getMessage(), Status.FAIL);
		}

	}

	public void commonMethodForClickOnMoreSettingsOption(String fileOrfolderName, String moreSettingsOptName) {
		try {
			String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink.replace("CRAFT", fileOrfolderName)
					.replace("MORE_OPT", moreSettingsOptName);
			
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * finalXpathForCopyToFolderLink);
			 */

			WebElement copyToElement = UIHelper.findAnElementbyXpath(driver, finalXpathForCopyToFolderLink);
		
			UIHelper.highlightElement(driver, copyToElement);
			UIHelper.scrollToAnElement(copyToElement);
			report.updateTestLog("Click on " + moreSettingsOptName, "User able to click the " + moreSettingsOptName,
					Status.PASS);
			JavascriptExecutor executor = (JavascriptExecutor) driver;

			executor.executeScript("arguments[0].click();", copyToElement);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			report.updateTestLog("Click on " + moreSettingsOptName, "User unable to click the " + moreSettingsOptName,
					Status.FAIL);
		}
	}

	public void negativeCommonMethodForClickOnMoreSettingsOption(String fileOrfolderName, String moreSettingsOptName) {
		String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink.replace("CRAFT", fileOrfolderName)
				.replace("MORE_OPT", moreSettingsOptName);

		/*
		 * UIHelper.waitForVisibilityOfEleByXpath(driver,
		 * finalXpathForCopyToFolderLink);
		 */

		WebElement copyToElement = UIHelper.findAnElementbyXpath(driver, finalXpathForCopyToFolderLink);
		UIHelper.highlightElement(driver, copyToElement);
		UIHelper.scrollToAnElement(copyToElement);
		report.updateTestLog("Click on " + moreSettingsOptName, "User able to click the " + moreSettingsOptName,
				Status.PASS);
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		executor.executeScript("arguments[0].click();", copyToElement);
		UIHelper.waitForPageToLoad(driver);

	}

	// common method for click on 'Selected Items' Option link
	public void commonMethodForClickOnSelectedItemsMenuOption(String selectItemMenuOptName) {
		try {

			List<WebElement> selectedItemsMenuList = UIHelper.findListOfElementsbyXpath(selectedItemsMenuOptionXpath,
					driver);

			for (WebElement selectedItemOptEle : selectedItemsMenuList) {
				if (selectedItemOptEle.getText().equalsIgnoreCase(selectItemMenuOptName)
						|| selectedItemOptEle.getText().contains(selectItemMenuOptName)) {

					UIHelper.highlightElement(driver, selectedItemOptEle);

					report.updateTestLog("Click on " + selectItemMenuOptName,
							"User able to perform the " + selectItemMenuOptName, Status.PASS);
					selectedItemOptEle.click();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyFolderCheckOutMessage(String selectItemMenuOptName) {
		try {
			List<WebElement> selectedItemsMenuList = UIHelper.findListOfElementsbyXpath(selectedItemsMenuOptionXpath,
					driver);

			for (WebElement selectedItemOptEle : selectedItemsMenuList) {
				if (selectedItemOptEle.getText().equalsIgnoreCase(selectItemMenuOptName)
						|| selectedItemOptEle.getText().contains(selectItemMenuOptName)) {

					selectedItemOptEle.click();
					Thread.sleep(1000);
					if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, folderCheckOutMsgXpath))) {
						report.updateTestLog("Verify Message for Folder Check out",
								"Folder Check out message displayed successfully", Status.PASS);
					} else {
						report.updateTestLog("Verify Message for Folder Check out",
								"Folder Check out message not displayed", Status.FAIL);
					}
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Message for Folder Check out", "Verify Message for Folder Check out Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method for Upload new Version in Document Library Page
	 * 
	 */
	public void uploadNewVersionFileInDocLibPage(String fileName, String filePath, String comments) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commentsTextAreaXpath);
			WebElement commentsTextAreaWebElement = UIHelper.findAnElementbyXpath(driver, commentsTextAreaXpath);
			UIHelper.highlightElement(driver, commentsTextAreaWebElement);
			commentsTextAreaWebElement.sendKeys(comments);

			WebElement uploadFileButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadFileButtonXpath);
			UIHelper.highlightElement(driver, uploadFileButtonWebElement);

			WebElement fileInput = driver.findElement(By.xpath(uploadInputField));
			fileInput.sendKeys(filePath + "\\" + fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadButtonXpath);
			UIHelper.click(driver, uploadButtonXpath);
			UIHelper.click(driver, uploadButtonXpath);
			// WebElement uploadButtonWebElement =
			// UIHelper.findAnElementbyXpath(driver, uploadButtonXpath);
			// UIHelper.mouseOverandclickanElement(driver,
			// uploadButtonWebElement);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, uploadButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Upload New Version File In Document Library Page",
					"New version File uploded Successfully" + "<br /><b>File Name :</b> " + fileName, Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Upload New Version File In Document Library Page",
					"Upload New Version File In DocumentDetails Page Failed" + "<br /><b>File Name :</b> " + fileName,
					Status.FAIL);
		}
	}

	/**
	 * Method for Upload new Version in Document Details Page
	 * 
	 * @author Duvvuru Naveen
	 */
	public void uploadNewVersionFileInDocLibPage(String fileName, String filePath, String versionChangeTo,
			String comments) {

		try {
			String finalFilePath = System.getProperty("user.dir") + filePath;

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFileHeaderXpath);

			if (versionChangeTo.equals("Minor")) {
				WebElement minorRadioEle = UIHelper.findAnElementbyXpath(driver, minorRadioXpath);
				UIHelper.highlightElement(driver, minorRadioEle);
				UIHelper.mouseOverandclickanElement(driver, minorRadioEle);

			} else {
				WebElement majorRadioEle = UIHelper.findAnElementbyXpath(driver, majorRadioXpath);
				UIHelper.highlightElement(driver, majorRadioEle);
				UIHelper.mouseOverandclickanElement(driver, majorRadioEle);
			}

			WebElement commentsTextAreaWebElement = UIHelper.findAnElementbyXpath(driver, commentsXpath);
			UIHelper.highlightElement(driver, commentsTextAreaWebElement);
			commentsTextAreaWebElement.sendKeys(comments);

			WebElement uploadFileButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadXpath);
			UIHelper.highlightElement(driver, uploadFileButtonWebElement);

			WebElement uploadInputEle = UIHelper.findAnElementbyXpath(driver, uploadInputTagXpath);
			uploadInputEle.sendKeys(finalFilePath + fileName);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtXpath);
			WebElement uploadButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadBtXpath);
			// UIHelper.click(driver, uploadBtXpath);
			// UIHelper.mouseOverandElementdoubleClick(driver,
			// uploadButtonWebElement);
			UIHelper.javascriptClick(driver, uploadButtonWebElement);

			report.updateTestLog("Upload New Version File In Document Library Page",
					"New version File uploded Successfully" + "<br /><b>File Name :</b> " + fileName, Status.PASS);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, uploadButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Upload New Version File In Document Library Page",
					"Upload New Version File In Document Library Page Failed" + "<br /><b>File Name :</b> " + fileName,
					Status.FAIL);
		}
	}

	public boolean getMoreHiddenActions(String folderOrFileName, String expectedValue) {
		try {
			String finalXpathForMoreHiddenActions = tempXpathForMoreHiddenActions.replace("CRAFT", folderOrFileName);
			List<WebElement> moreHiddenEleList = UIHelper.findListOfElementsbyXpath(finalXpathForMoreHiddenActions,
					driver);

			for (WebElement ele : moreHiddenEleList) {
				if (ele.getText().trim().contains(expectedValue)) {
					isDiaplyedMoreSettingsHiddenValue = true;
					break;
				} else {
					isDiaplyedMoreSettingsHiddenValue = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDiaplyedMoreSettingsHiddenValue;
	}

	public ArrayList<String> getLifecycleTags(String fileOrFolderName) {
		try {
			String finalXpathForLifecycleTags = tempXpathForLifecycleTags.replace("CRAFT", fileOrFolderName);
			List<WebElement> lifecycleTagsList = UIHelper.findListOfElementsbyXpath(finalXpathForLifecycleTags, driver);

			for (WebElement ele : lifecycleTagsList) {
				fileOrFoldeTagsList.add(ele.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileOrFoldeTagsList;
	}

	public ArrayList<String> getBrowseActionOptions(String fileOrFolderName) {
		try {
			String finalXpathForBrowseActionForFileOrFolder = tempXpathForBrowseActionForFileOrFolder.replace("CRAFT",
					fileOrFolderName);
			List<WebElement> browseActionsEleList = UIHelper
					.findListOfElementsbyXpath(finalXpathForBrowseActionForFileOrFolder, driver);

			for (WebElement browseActionEle : browseActionsEleList) {
				browseActionsList.add(browseActionEle.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return browseActionsList;
	}

	// Method for Delete All Folder&Files
	public void deleteAllFilesAndFolders() {
		try {
			deleteLockedFile();
			try {
				selectAllFilesAndFolders();
			} catch (Exception e) {
				e.printStackTrace();
			}
			performDeleteFromSelectedItemsMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void deleteLockedFile() {
		boolean isDisplayedLockedFile = false;
		try {
			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			ArrayList<String> fileNames = new ArrayList<String>();
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				fileNames.add(ele.getText());
			}
			for (String fileName : fileNames) {

				String finalOfflineEditMsgXapth = tempOfflineEditMsgXapth.replace("CRAFT", fileName);
				if (UIHelper.checkForAnElementbyXpath(driver, finalOfflineEditMsgXapth)) {
					AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
					sitesPage.clickOnMoreSetting(fileName);
					sitesPage.commonMethodForClickOnMoreOptionLink(fileName, "Cancel Editing");
					UIHelper.waitFor(driver);

					isDisplayedLockedFile = true;
				}
			}

			if (isDisplayedLockedFile) {
				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);

				UIHelper.waitFor(driver);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select all folders & files
	public void selectAllFilesAndFolders() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectButtonXpath);
			UIHelper.click(driver, selectButtonXpath);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, slectAllOptionXpath);
			} catch (Exception e) {
			}
			UIHelper.click(driver, slectAllOptionXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Select all 'Documents and Folders'", "User selected 'Documents and Folders'",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// deselect all files and folder
	   public void deselectAllFilesAndFolders(){
				try{
					UIHelper.waitForVisibilityOfEleByXpath(driver, selectButtonXpath);
					UIHelper.click(driver, selectButtonXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, selectNoneButtonXpath);
					UIHelper.highlightElement(driver, selectNoneButtonXpath);
					UIHelper.click(driver, selectNoneButtonXpath);
					UIHelper.waitFor(driver);
					report.updateTestLog("Deselect all 'Documents and Folders'", " Deselect all 'Documents and Folders' successfull",
							Status.DONE);
					
				}catch(Exception e){
					report.updateTestLog("Deselect all 'Documents and Folders'", " Deselect all 'Documents and Folders' Unsuccessfull",
							Status.FAIL);
				}
			}

	// Select items (folders/files) in Document Library
	public void selectDocumentLibItems(String docLibItemType) {
		try {
			String finalXpathForSelectItemTypeInDocLib = tempXpathForSelectItemTypeInDocLib.replace("CRAFT",
					docLibItemType);

			UIHelper.waitForVisibilityOfEleByXpath(driver, selectButtonXpath);
			UIHelper.click(driver, selectButtonXpath);
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * finalXpathForSelectItemTypeInDocLib);
			 */
			UIHelper.waitFor(driver);
			UIHelper.click(driver, finalXpathForSelectItemTypeInDocLib);
			UIHelper.waitFor(driver);

			report.updateTestLog("Select item:'" + docLibItemType + "' in Document Library",
					"User selected item:'" + docLibItemType + "' in Document Library", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Perform all folders & files deletion
	public void performDeleteFromSelectedItemsMenu() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, visibleSelectedItemsXpath)) {
				UIHelper.click(driver, selectedItemsXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteXpath);
				UIHelper.click(driver, deleteXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteFolderXpath);
				UIHelper.click(driver, deleteFolderXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);

				driver.navigate().refresh();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Delete a all files and folder", "User deleted All Files/Folders", Status.DONE);
			} else {
				report.updateTestLog("Delete a all files and folder", "User deleted All Files/Folders", Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click Delete Button In Delete Document Popup
	public void clickDeleteBtnInDeleteDocumentPopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteFolderXpath);
			UIHelper.click(driver, deleteFolderXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Delete a Document", "User deleted document", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickDeleteConformationButton() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteFolderXpath);
			UIHelper.click(driver, deleteFolderXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);

			driver.navigate().refresh();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void performAllDelete() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, visibleSelectedItemsXpath)) {
				UIHelper.click(driver, selectedItemsXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteXpath);
				UIHelper.click(driver, deleteXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteFolderXpath);
				UIHelper.click(driver, deleteFolderXpath);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Method to check the image previewed or not in Image preview page
	 * 
	 * @param fileName
	 * @author 412766
	 */
	public boolean isImagePreviewed() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, imagePreviewXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, imagePreviewXpath))) {
				UIHelper.highlightElement(driver, imagePreviewXpath);
				flag = true;
			} else {
				flag = false;
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String getTheMessageText() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			messageText = UIHelper.getTextFromWebElement(driver, messageEleXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return messageText;
	}


	public String getTheLoadingMessageText(String message) {
		try {
			String loadingMessageFinal = loadingMessage.replace("CRAFT", message);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, loadingMessageFinal);
			messageText = UIHelper.getTextFromWebElement(driver, loadingMessageFinal);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return messageText;
	}

	
	public String getTheFolderMessageText() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, folderExcludeMessageXpath);
			messageText = UIHelper.getTextFromWebElement(driver, folderExcludeMessageXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return messageText;
	}
	
	// To Get count from Link To PopUp Head Line
	public int getCountFromLinkPopUp() {
		int countSelected = 0;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, linkPopUpHeadLineXpath);
			String headLineText = UIHelper.getTextFromWebElement(driver, linkPopUpHeadLineXpath);

			String splitText[] = headLineText.split("\\s");
			countSelected = Integer.parseInt(splitText[1]);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return countSelected;
	}

	// To edit file Properties
	public void editFileProperties() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesXpath);
			UIHelper.highlightElement(driver, editPropertiesXpath);

			String editPropertyTitleValue = dataTable.getData("Document_Details", "Title");

			UIHelper.highlightElement(driver, editPropertyTitleInputXpath);
			UIHelper.findAnElementbyXpath(driver, editPropertyTitleInputXpath).clear();
			UIHelper.findAnElementbyXpath(driver, editPropertyTitleInputXpath).sendKeys(editPropertyTitleValue);

			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesSaveButtonXpath);
			UIHelper.highlightElement(driver, editPropertiesSaveButtonXpath);
			UIHelper.click(driver, editPropertiesSaveButtonXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, docLibXpath);

			report.updateTestLog("Edit file properties", "File edited Successfully", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Edit file properties", e.getMessage(), Status.FAIL);
		}
	}

	// Get the document library folders
	public ArrayList<String> getDocLibFolderDetails() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);
			WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver, pageBodyXpath);
			UIHelper.highlightElement(driver, pageBodyEle);
			UIHelper.mouseOveranElement(driver, pageBodyEle);

			WebElement docLibFilterEle = UIHelper.findAnElementbyXpath(driver, docLibFilterXpath);
			UIHelper.highlightElement(driver, docLibFilterEle);
			UIHelper.mouseOveranElement(driver, docLibFilterEle);

			if (UIHelper.checkForAnElementbyXpath(driver, libraryFilterTwisterCloseXpath)) {
				UIHelper.click(driver, libraryFilterTwisterCloseXpath);
				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, libraryFilterTwisterOpenXpath);
				} catch (Exception e) {

				}
				UIHelper.waitFor(driver);
			}

			List<WebElement> libraryDocumentItemsEle = UIHelper.findListOfElementsbyXpath(libraryDocumentItemsXpath,
					driver);
			for (WebElement ele : libraryDocumentItemsEle) {
				UIHelper.highlightElement(driver, ele);
				docLibFoldersList.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return docLibFoldersList;
	}

	public boolean isDisplayedLibraryFolderSubFolder(ArrayList<String> siteNamesList) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			for (String siteName : siteNamesList) {
				List<WebElement> libraryDocumentItemsEle = UIHelper.findListOfElementsbyXpath(libraryDocumentItemsXpath,
						driver);
				for (WebElement ele : libraryDocumentItemsEle) {
					if (ele.getText().trim().contains(siteName)) {
						UIHelper.highlightElement(driver, ele);
						executor.executeScript("arguments[0].click();", ele);
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);
						String finalLibFolderSubfolderXpath = libFolderSubfolderXpath.replace("CRAFT", siteName);
						// UIHelper.waitForVisibilityOfEleByXpath(driver,
						// finalLibFolderSubfolderXpath);
						UIHelper.highlightElement(driver,
								UIHelper.findAnElementbyXpath(driver, finalLibFolderSubfolderXpath));
						isDisplayedLibDocumentLibVal = UIHelper.checkForAnElementbyXpath(driver,
								finalLibFolderSubfolderXpath);
						componentFolderName = UIHelper.getTextFromWebElement(driver, finalLibFolderSubfolderXpath);
						break;
					} else {
						isDisplayedLibDocumentLibVal = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedLibDocumentLibVal;
	}

	public AlfrescoDocumentLibPage deleteFileInDocumentLibrary() {
		try {

			String fileName = dataTable.getData("MyFiles", "FileName");
			commonMethodForDeleteFileInDocumentLibrary(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoDocumentLibPage(scriptHelper);

	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void commonMethodForDeleteFileInDocumentLibrary(String fileName) {
		UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
		List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
		for (WebElement ele : searchResultsListEle) {
			if (fileName.equals(ele.getText())) {
				UIHelper.clickanElement(ele);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteDocumentXpath);
				UIHelper.click(driver, deleteDocumentXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteBtnXpath);
				UIHelper.click(driver, deleteBtnXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);

				break;
			}
		}
	}

	// Click whether program folder are available in Doc lib of Program
	// Component
	public void clickProgramFolder(String SiteName) {

		try {
			String folder = UIHelper.findAnElementbyXpath(driver, folderXpath).getText();
			if (UIHelper.findAnElementbyXpath(driver, folderXpath).getText().contains(SiteName)) {
				UIHelper.highlightElement(driver, folderXpath);
				UIHelper.click(driver, folderXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Click Program foler",
						"Click program folder in program library section of Component site."

								+ "<br /><b> Folder Name :</b>" + folder,
						Status.DONE);
			} else {
				report.updateTestLog("Click Program foler", "Folder Not found", Status.FAIL);
			}

		} catch (Exception e) {

			report.updateTestLog("Click Program foler", "Folder Not found", Status.FAIL);
		}

	}

	// Click program Component related folder are available in Doc lib
	public void clickProgramCompFolder(String SiteName) {
		UIHelper.waitFor(driver);
		String finalfolderXpath = progfolderXpath.replace("CRAFT", SiteName);
		try {

			if (UIHelper.findAnElementbyXpath(driver, finalfolderXpath).getText().contains(SiteName)) {
				UIHelper.highlightElement(driver, finalfolderXpath);
				UIHelper.mouseOverandElementdoubleClick(driver,
						UIHelper.findAnElementbyXpath(driver, finalfolderXpath));
				UIHelper.waitForPageToLoad(driver);
				String finalDocLibXpath = breadcrumbXpath.replace("CRAFT", SiteName);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalDocLibXpath);
				report.updateTestLog("Click Program Component foler",
						"Click program Comp folder in program library section of Program site."
								+ "<br /><b> Expected Folder Name : </b>" + SiteName
								+ "<br /><b> Actual Folder Name :</b>"
								+ UIHelper.findAnElementbyXpath(driver, finalfolderXpath).getText(),
						Status.DONE);
			} else {
				report.updateTestLog("Click Program Component foler",
						"Click program Comp folder in program library section of Program site."
								+ "<br /><b> Expected Folder Name : </b>" + SiteName
								+ "<br /><b> Actual Folder Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver, finalfolderXpath).getText(),
						Status.FAIL);
			}

		} catch (Exception e) {

			report.updateTestLog("Click Program Component foler",
					"Click program Comp folder in program library section of Program site."
							+ "<br /><b> Expected Folder Name : </b>" + SiteName + "<br /><b> Actual Folder Name : </b>"
							+ UIHelper.findAnElementbyXpath(driver, finalfolderXpath).getText(),
					Status.FAIL);
		}

	}

	// Click program Component related folder are available in Doc lib
	public void filterCategory(String category) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			String finalfolderXpath = tempXpathForCategoryItem.replace("CRAFT", category);

			if (UIHelper.checkForAnElementbyXpath(driver, finalfolderXpath)) {
				commonMethodForClickingCategoryItem(finalfolderXpath, category);
			} else if (UIHelper.checkForAnElementbyXpath(driver, categoriesHeadFilterXpath)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, categoriesHeadFilterXpath));
				UIHelper.mouseOverandclickanElement(driver,
						UIHelper.findAnElementbyXpath(driver, categoriesHeadFilterXpath));
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalfolderXpath);
				UIHelper.waitFor(driver);
				Thread.sleep(2000);

				if (UIHelper.checkForAnElementbyXpath(driver, finalfolderXpath)) {
					commonMethodForClickingCategoryItem(finalfolderXpath, category);
				}
			} else {
				report.updateTestLog("Click on Category", "Click on Category Failed. Category Not found", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Category", "Click on Category Failed. Category Not found", Status.FAIL);
		}

	}

	public void commonMethodForClickingCategoryItem(String finalfolderXpath, String category) {
		try {
			UIHelper.highlightElement(driver, finalfolderXpath);
			UIHelper.mouseOverandElementdoubleClick(driver, UIHelper.findAnElementbyXpath(driver, finalfolderXpath));
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on Category",
					"Click on Category successfully." + "<br /><b> Category Name : </b>" + category, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Check download successfully from SelectedOptions or More Options
	public void downloadAsZipInDocumentLibrary(String fileorFolderName) {
		try {
			String filePath = Settings.getInstance().getProperty("DefaultDownloadPath");
			File zipFile = new File(filePath + "/" + fileorFolderName + ".zip");
			long start_time = System.currentTimeMillis();
			long wait_time = 200000;
			long end_time = start_time + wait_time;
			while (System.currentTimeMillis() < end_time) {
				if (zipFile.exists()) {
					report.updateTestLog("Download Zip file", "Zip File downloaded sucessfully", Status.DONE);
					break;
				}
			}
			if (!zipFile.exists()) {
				report.updateTestLog("To download a file", "To download a file Failed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Download Zip file", "Failed to download", Status.DONE);
			e.getMessage();
		}
	}

	// Copy paste Share link in new browser
	public void shareLink() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareLinkXpath);
			String URL = UIHelper.getTextFromWebElement(driver, shareLinkXpath);
			UIHelper.waitFor(driver);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			UIHelper.waitFor(driver);
			driver.get(URL);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			// driver.switchTo().window(tabs.get(1));
			report.updateTestLog("Use Share URL", "Share URL copy Pasted into the new browser", Status.DONE);

		} catch (Exception e) {
			// e.printStackTrace();
			report.updateTestLog("Use Share URL", "Share URL copy Pasted into the new browser failed", Status.FAIL);
			e.getMessage();
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isNotDeleteMessageDisplayed() {
		boolean flag = false;
		try {
			selectAllFilesAndFolders();
			performAllDelete();
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, messageXpath))) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {

		}
		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isDeleteMessageDisplayed(String folderOrFileName, String deleteType) {
		boolean flag = false;
		try {
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

			if (deleteType.equalsIgnoreCase("hovering")) {
				sitesPage.clickOnMoreSetting(folderOrFileName);
				String docActionVal = dataTable.getData("MyFiles", "MoreSettingsOption");
				docLibPg.commonMethodForClickOnMoreSettingsOption(folderOrFileName, docActionVal);
			} else if (deleteType.equalsIgnoreCase("actionItem")) {
				sitesPage.clickOnViewDetails(folderOrFileName);
				docDetailsPageObj.performDeleteFolderDocAction();
			} else if (deleteType.equalsIgnoreCase("selectedItems")) {
				myFiles.selectMultipleFilesOrFolders(folderOrFileName);
				sitesPage.clickOnSelectedItems();
				String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
				docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
			} else if (deleteType.equalsIgnoreCase("DocActionForFile")) {
				docDetailsPageObj.commonMethodForPerformDocAction("Delete Document");
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteFolderXpath);
			UIHelper.click(driver, deleteFolderXpath);
			if (deleteType.equalsIgnoreCase("hovering") || deleteType.equalsIgnoreCase("selectedItems")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
				if (UIHelper.checkForAnElementbyXpath(driver, messageXpath)) {
					flag = true;
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
				} else {
					flag = false;
				}
			} else {
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				flag = true;
			}

		} catch (Exception e) {

		}
		return flag;
	}

	// click Selected Items Button
	public void clickSelectedItemsMenu() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, visibleSelectedItemsXpath)) {
				UIHelper.click(driver, selectedItemsXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteXpath);
				report.updateTestLog("Click on Selected Items Menu", "Selected Items List displayed successfully",
						Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// click on Start Workflow Link on Hovering
	public void clickStrtWF(String folderName) {
		try {
			String finalXpathForStrtWFFolderLink = tempXpathForStrtWflwFolderLink.replace("CRAFT", folderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForStrtWFFolderLink);

			WebElement managePermElement = UIHelper.findAnElementbyXpath(driver, finalXpathForStrtWFFolderLink);
			UIHelper.highlightElement(driver, managePermElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", managePermElement);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select all folders & files
	public void selectAllItems(String fileOrFolder) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, selectButtonXpath);
			UIHelper.click(driver, selectButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, slectAllOptionXpath);
			UIHelper.click(driver, slectAllOptionXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Select " + fileOrFolder, fileOrFolder + " selected successfully", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To click on All document options
	public void clickOnDocOptions(String fileName, String link) {
		try {
			highlightTheDocLibSection(fileName);
			String finalDocumentOptionsXpath = tempDocOptionsXpath.replace("CRAFT", fileName).replace("LINK", link);

			if (UIHelper.checkForAnElementbyXpath(driver, finalDocumentOptionsXpath)) {
				System.out.println("1");
				UIHelper.click(driver, finalDocumentOptionsXpath);
				System.out.println("2");
				UIHelper.waitFor(driver);
				System.out.println("3");
				report.updateTestLog("Click on" + link + "option",
						"Clicked the <b>" + link + "</b> action for <br/><b>File Name:</b> " + fileName, Status.DONE);
				System.out.println("4");
			}

			else {
				System.out.println("5");
				report.updateTestLog("Click on" + link + "option",
						"Unable to Click the <b>" + link + "</b> action for <br/><b>File Name:</b> " + fileName,
						Status.FAIL);
				System.out.println("6");
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on" + link + "option",
					"Unable to Click the <b>" + link + "</b> action for <br/><b>File Name:</b> " + fileName,
					Status.FAIL);
		}

	}

	// Select folder to copy in copy popup
	public void performCopyToOperation(String siteName, String destinationFolder) {
		try {

			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);

			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", siteName);

			try {
				UIHelper.highlightElement(driver, selectSiteSecXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectFileToMoveXpath);
				UIHelper.highlightElement(driver, finalSelectFileToMoveXpath);
				UIHelper.click(driver, finalSelectFileToMoveXpath);
				UIHelper.waitFor(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}

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
			report.updateTestLog("Peform 'Copy To'",
					"File successfully copied to " + "Folder Name = " + destinationFolder, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select a file from popup to move
	public void performMoveToOperation(String siteName, String folderName) {
		try {
			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", folderName);

			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", siteName);

			try {
				UIHelper.highlightElement(driver, selectSiteSecXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectFileToMoveXpath);
				UIHelper.highlightElement(driver, finalSelectFileToMoveXpath);
				UIHelper.click(driver, finalSelectFileToMoveXpath);
				UIHelper.waitFor(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}

			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);

			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Peform 'Move To'", "File successfully moved to " + "Folder Name = " + folderName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Comments To select Folder in Link Pop Up Window
	public void performLinkToOperation(String targetSiteOrMyOrSharedFiles, String folderName) {
		try {
			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", folderName);

			if (targetSiteOrMyOrSharedFiles.equalsIgnoreCase("Shared Files")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, sharedFilesBtnXpath);
				UIHelper.click(driver, sharedFilesBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, sharedFilesXpathInPathSec);
				UIHelper.waitFor(driver);
			}
			if (targetSiteOrMyOrSharedFiles.equalsIgnoreCase("My Files")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesBtnXpath);
				UIHelper.click(driver, myFilesBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesXpathInPathSec);
				UIHelper.waitFor(driver);
			} else {
				String finalXpathForSiteSelection = tempXpathForSiteSelection.replace("CRAFT",
						targetSiteOrMyOrSharedFiles);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteSelection);
				UIHelper.click(driver, finalXpathForSiteSelection);
			}

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
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnLinkToInLinkPopUpxpath);
			UIHelper.click(driver, btnLinkToInLinkPopUpxpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Peform 'Link To' operation",
					"File successfully linked to " + "Folder Name = " + folderName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Perform 'UnLink' operation
	public void performUnLinkOperation(String siteOrMyOrSharedFiles, String folderName) {
		try {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, unlinkPopupHeaderTitleXpath);
			} catch (Exception e) {

			}

			if (siteOrMyOrSharedFiles.equalsIgnoreCase("Shared") || siteOrMyOrSharedFiles.contains("User Homes")) {
				String finalXpathForCheckBoxInSharedOrMyFileUnliknkPopup = tempXpathForCheckBoxInSharedOrMyFileUnliknkPopup
						.replace("TAB_NAME", siteOrMyOrSharedFiles).replace("FOLDER_NAME", folderName);

				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCheckBoxInSharedOrMyFileUnliknkPopup);

				UIHelper.click(driver, finalXpathForCheckBoxInSharedOrMyFileUnliknkPopup);
			} else {
				String finalXpathForCheckBoxInUnlinkPopup = tempXpathForCheckBoxInUnlinkPopup
						.replace("SITE_NAME", siteOrMyOrSharedFiles.toLowerCase()).replace("FOLDER_NAME", folderName);

				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCheckBoxInUnlinkPopup);

				UIHelper.click(driver, finalXpathForCheckBoxInUnlinkPopup);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnUnLinkInUnLinkPopUpxpath);
			UIHelper.click(driver, btnUnLinkInUnLinkPopUpxpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			report.updateTestLog("Peform 'UnLink' operation",
					"File successfully linked to " + "Folder Name = " + folderName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author 412766
	 */
	public void linkFileToMyFiles(String fileName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, linkToPanelXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, linkToMyFilesButtonXpath);
			UIHelper.highlightElement(driver, linkToMyFilesButtonXpath);
			UIHelper.click(driver, linkToMyFilesButtonXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, linkToButtonXpath);
			UIHelper.highlightElement(driver, linkToButtonXpath);
			UIHelper.click(driver, linkToButtonXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Link a file in 'My Files'",
					"File Linked successfully" + "<br /><b> File Name : </b>" + fileName, Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Link a file in 'My Files'", "Link a file in 'My Files' Failed", Status.PASS);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @param targetSiteName
	 */
	public void copyFileIntoAnotherSite(String fileName, String targetSiteName) {
		try {
			UIHelper.waitFor(driver);
			String finalTargetSite = tempTargetSiteXpath.replace("CRAFT", targetSiteName);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, linkToPanelXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, sitePickerXpath);
			} catch (Exception e) {

			}

			UIHelper.highlightElement(driver, sitePickerXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalTargetSite);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, finalTargetSite);
			UIHelper.click(driver, finalTargetSite);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, copyButtonXpath);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, copyButtonXpath);
			UIHelper.click(driver, copyButtonXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, messageEleXpath)) {
				report.updateTestLog("Copy a file to the another Site",
						"File Copied successfully" + "<br /><b> File Name : </b>" + fileName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.PASS);
			} else {
				report.updateTestLog("Copy a file to the another Site", "File not Copied" + "<br /><b> File Name : </b>"
						+ fileName + "<br /><b> Destination Site Name : </b>" + targetSiteName, Status.FAIL);
			}

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Copy a file to the another Site", "Copy a file to the another Site Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @param targetSiteName
	 */
	public void moveFileIntoAnotherSite(String fileName, String targetSiteName) {
		try {
			UIHelper.waitFor(driver);
			String finalTargetSite = tempTargetSiteXpath.replace("CRAFT", targetSiteName);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, linkToPanelXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, sitePickerXpath);
			} catch (Exception e) {

			}

			UIHelper.highlightElement(driver, sitePickerXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalTargetSite);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, finalTargetSite);
			UIHelper.click(driver, finalTargetSite);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, moveButtonXpath);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, moveButtonXpath);
			UIHelper.click(driver, moveButtonXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			} catch (Exception e) {
			}

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, messageEleXpath))) {
				report.updateTestLog("Move a file to the another Site",
						"File Moved successfully" + "<br /><b> File Name : </b>" + fileName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.PASS);
			} else {
				report.updateTestLog("Move a file to the another Site", "File not Moved" + "<br /><b> File Name : </b>"
						+ fileName + "<br /><b> Destination Site Name : </b>" + targetSiteName, Status.FAIL);
			}

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Move a file to the another Site", "Move a file to the another Site Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileOrFolderName
	 * @param targetSiteName
	 */
	public void linkFileIntoAnotherSite(String fileOrFolderName, String targetSiteName) {
		try {
			UIHelper.waitFor(driver);
			String finalTargetSite = tempTargetSiteXpath.replace("CRAFT", targetSiteName);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, linkToPanelXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, sitePickerXpath);
			} catch (Exception e) {

			}

			UIHelper.highlightElement(driver, sitePickerXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalTargetSite);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, finalTargetSite);
			UIHelper.click(driver, finalTargetSite);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, linkButtonXpath);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, linkButtonXpath);
			UIHelper.click(driver, linkButtonXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			} catch (Exception e) {
			}

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, messageEleXpath))) {
				report.updateTestLog("Link a File / Folder to the another Site",
						"File / Folder Linked successfully" + "<br /><b> File / Folder Name: </b>" + fileOrFolderName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.PASS);
			} else {
				report.updateTestLog("Link a File / Folder to the another Site",
						"File / Folder not Linked" + "<br /><b> File Name : </b>" + fileOrFolderName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.FAIL);
			}

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Link a File / Folder to the another Site",
					"Link a File / Folder to the another Site Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isUploadWindowDisplayed() {
		boolean flag = false;
		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, uploadNewVersionWindowXpath))) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {

		}

		return flag;
	}

	// Verify on All document options
	public void verifyDocOptions(String fileName, String link) {
		try {
			highlightTheDocLibSection(fileName);
			String finalDocumentOptionsXpath = tempDocOptionsXpath.replace("CRAFT", fileName).replace("LINK", link);

			if (UIHelper.checkForAnElementbyXpath(driver, finalDocumentOptionsXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalDocumentOptionsXpath));
				UIHelper.highlightElement(driver, finalDocumentOptionsXpath);
				// UIHelper.click(driver, finalDocumentOptionsXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify" + link + "option", link + " is displayed for File Name: " + fileName,
						Status.PASS);

			}

			else {
				report.updateTestLog("Verify" + link + "option", link + " is not displayed for File Name: " + fileName,
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify" + link + "option", link + " is not displayed for File Name: " + fileName,
					Status.FAIL);
		}

	}

	// Verify on All folder options
	public void verifyFolderOptions(String Folder, String link) {
		try {
			highlightTheDocLibSection(Folder);
			String finalDocumentOptionsXpath = tempDocOptionsXpath.replace("CRAFT", Folder).replace("LINK", link);

			if (!UIHelper.checkForAnElementbyXpath(driver, finalDocumentOptionsXpath)) {
				// UIHelper.click(driver, finalDocumentOptionsXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify" + link + "option", link + "Link is not displayed for Folder : " + Folder,
						Status.PASS);

			}

			else {
				report.updateTestLog("Verify" + link + "option", link + "Link is displayed for Folder : " + Folder,
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify" + link + "option", link + "Link is displayed for Folder : " + Folder,
					Status.FAIL);
		}

	}

	public AlfrescoDocumentLibPage ClickOnDownloadAsZipForMessage(String fileName) {
		try {

			if (fileName == null) {
				fileName = dataTable.getData("MyFiles", "FileName");
			}
			String filePath = Settings.getInstance().getProperty("DefaultDownloadPath");
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			List<WebElement> checkBoxListEle = driver.findElements(By.xpath(checkBoxListXpath));
			int index = 0;
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					// UIHelper.mouseOveranElement(driver, ele);

					UIHelper.highlightElement(driver, checkBoxListEle.get(index));
					UIHelper.clickanElement(checkBoxListEle.get(index));

					UIHelper.waitForVisibilityOfEleByXpath(driver, selectAnItemsButtonXpath);
					UIHelper.click(driver, selectAnItemsButtonXpath);

					UIHelper.waitForVisibilityOfEleByXpath(driver, downloadZipXpath);
					UIHelper.click(driver, downloadZipXpath);

				}

				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Download Zip file", e.getMessage(), Status.DONE);
		}
		return new AlfrescoDocumentLibPage(scriptHelper);
	}

	// To Verify Link Image is displayed for a Linked File
	public boolean verifyLinkIcon(String fileOrFolderName) {
		boolean flag = false;
		try {
			String finalImageLinkXpath = linkImgXpath.replace("CRAFT", fileOrFolderName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalImageLinkXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, finalImageLinkXpath)) {
				UIHelper.highlightElement(driver, finalImageLinkXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Check Link Icon", "Link Icon is present for linked file :" + fileOrFolderName,
						Status.PASS);
				flag = true;
			} else {
				report.updateTestLog("Check Link Icon", "Link Icon is not present for linked file :" + fileOrFolderName,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check Link Icon", "Link Icon is not present for linked file :" + fileOrFolderName,
					Status.FAIL);
		}

		return flag;
	}

	// Click Publish Option
	public boolean clickPublishPopup() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, pubPopchkXPath);
			UIHelper.click(driver, pubPopchkXPath);
		//	UIHelper.waitForVisibilityOfEleByXpath(driver, pubBtnXPath);
			UIHelper.click(driver, pubBtnXPathtest);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedPublishLink;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean clickBatchPublishPopup() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, batchPubBtnXpath);
			UIHelper.highlightElement(driver, batchPubBtnXpath);
			UIHelper.click(driver, batchPubBtnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedPublishLink;
	}

	// Check Publish option
	public boolean isDisplayedPublishedTick(String fileName) {
		try {
			highlightTheDocLibSection(fileName);
			String finalXpathForPublishLink = tempPublishLinkXpath.replace("CRAFT", fileName);
			System.out.println(finalXpathForPublishLink);
			// Thread.sleep(400000);
			UIHelper.pageRefresh(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, tempPublishInProgressLinkXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForPublishLink);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForPublishLink)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpathForPublishLink));
				UIHelper.highlightElement(driver, finalXpathForPublishLink);
				isDisplayedPublishLink = true;
			} else {
				isDisplayedPublishLink = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedPublishLink;
	}

	

	
	// Check Published Tick for File Or Folder
	public boolean checkPublishedTick(String fileName) {
		boolean isDisplayedPublishedTickForFileOrFolder = false;
		try {
			highlightTheDocLibSection(fileName);
			String finalXpathForPublishLink = tempPublishLinkXpath.replace("CRAFT", fileName);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, tempPublishInProgressLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForPublishLink);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForPublishLink)) {
				UIHelper.highlightElement(driver, finalXpathForPublishLink);
				isDisplayedPublishedTickForFileOrFolder = true;
			} else {
				isDisplayedPublishedTickForFileOrFolder = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedPublishedTickForFileOrFolder;
	}

	// Rename a file
	public void documentRename(String fileName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesXpath);
			UIHelper.highlightElement(driver, editPropertiesXpath);

			UIHelper.highlightElement(driver, editPropertyNameInputXpath);
			UIHelper.findAnElementbyXpath(driver, editPropertyNameInputXpath).clear();
			UIHelper.findAnElementbyXpath(driver, editPropertyNameInputXpath).sendKeys(fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesSaveButtonXpath);
			UIHelper.highlightElement(driver, editPropertiesSaveButtonXpath);
			UIHelper.click(driver, editPropertiesSaveButtonXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, docLibXpath);

			report.updateTestLog("Edit file Name", "File Name edited Successfully " + "Renamed as : " + fileName,
					Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Edit file Name", e.getMessage(), Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void clickOnSecondaryLink(String siteName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			// String secondaryLinkXpath =
			// tempSecondaryLinkXpath.replace("CRAFT", siteName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, secondaryLinkXpath);
			UIHelper.highlightElement(driver, secondaryLinkXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, secondaryLinkXpath));
			UIHelper.click(driver, secondaryLinkXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Navigate to Secondary Link location",
					"Navigated successfully" + "<br /><b> Secondary Link Site Name : </b>" + siteName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void clickFollowOption(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, followOptionXpath);
			UIHelper.highlightElement(driver, followOptionXpath);
			UIHelper.click(driver, followOptionXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, unfollowOptionXpath);
			UIHelper.highlightElement(driver, unfollowOptionXpath);

			report.updateTestLog("Click Follow option",
					"Clicked successfully" + "<br /><b> File Name : </b>" + fileName, Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @return
	 */
	public boolean isFollowLinkSelected() {
		boolean flag = false;
		try {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, unfollowOptionXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UIHelper.highlightElement(driver, unfollowOptionXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, unfollowOptionXpath));

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, unfollowOptionXpath))) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Click on OK button in report gen screen

	public void clickOKBtnInRepoScreen() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnInRepotScreenXpath);
			UIHelper.click(driver, okBtnInRepotScreenXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isXMLTransFileIsAvailable(String XMLfileName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (ele.getText().contains(XMLfileName)) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.scrollToAnElement(ele);
					flag = true;
					break;

				}
			}
		} catch (Exception e) {
			report.updateTestLog("Check file is available", "Check file is available Failed", Status.FAIL);
		}
		return flag;
	}

	// To Select sort options in Document Library
	public void slctSortOptFromDocLib(String sortVal) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, btnSortOptXPath);
			UIHelper.click(driver, btnSortOptXPath);

			List<WebElement> lstSrtOption = driver.findElements(By.xpath(lstOfSortValXPath));

			for (WebElement ele : lstSrtOption) {
				if (ele.getText().equalsIgnoreCase(sortVal)) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.clickanElement(ele);
					report.updateTestLog("Select the sorting option from the dropdown",
							ele.getText() + " Option selected successfully", Status.PASS);
					break;
				}
			}

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		}

		catch (Exception e) {
			report.updateTestLog("Select the sorting option from the dropdown",
					sortVal + " Option NOT selected successfully", Status.FAIL);
			e.printStackTrace();

		}

	}

	public void clickSortDirectBtn() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, srtDirXPath);

			if (driver.findElement(By.xpath(srtDirXPath)).isDisplayed()) {
				UIHelper.click(driver, srtDirXPath);
				System.out.println("sort asc clicked");

			}

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}

	// Click Delete Option
	public boolean clickDeletePopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, delPopUpXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, delBtnXPath);
			UIHelper.click(driver, delBtnXPath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedPublishLink;

	}

	// Check confirmation pop up for zip file displayed
	public boolean checkConfirmationPopupForZip() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, zipChkBoxXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, zipChkBoxXpath)) {
				flag = true;
				System.out.println(flag);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, zipChkBoxXpath);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	// click confirmation pop up for zip file
	public boolean clickZipNotExpandChkBox() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, zipChkBoxXpath);
			UIHelper.highlightElement(driver, zipChkBoxXpath);
			UIHelper.click(driver, zipNotExpChkBoxXpath);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	// Check Orange exclamation mark for published file or folder (After file
	// status changes)
	public boolean isDisplayedOrangeExclamationMarkForPublishedFolder(String fileOrFolderName) {
		boolean isDisplayedOrangeExclamationMark = false;
		try {
			String finalXpathForOrangeExclamationMark = tempXpathForOrangeExclamationMark.replace("CRAFT",
					fileOrFolderName);

			if (UIHelper
					.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalXpathForOrangeExclamationMark))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpathForOrangeExclamationMark));
				UIHelper.highlightElement(driver, finalXpathForOrangeExclamationMark);
				isDisplayedOrangeExclamationMark = true;
			} else {
				isDisplayedOrangeExclamationMark = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedOrangeExclamationMark;
	}

	// Select publish as zip itself
	public void selectPublishZipAsItself() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, zipChkBoxXpath);
			UIHelper.highlightElement(driver, zipChkBoxXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, zipChkXpath);
			UIHelper.highlightElement(driver, zipChkXpath);
			UIHelper.click(driver, zipChkXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on select publish zip as itself  checkbox",
					"Select publish zip as itself  checkbox selected", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on select publish zip as itself  checkbox",
					"Select publish zip as itself  checkbox is not selected", Status.FAIL);
		}

	}

	// Check Changed FileDetails in Review Changes Popup Menu
	public boolean checkModeifiedFileDetailsInReviewChangesPopup(String fileName) {
		boolean isDisplayedModifiedFileName = false;
		try {
			String finalXpathForDisplayValInReviewChangesPopup = tempXpathForDisplayValInReviewChangesPopup
					.replace("CRAFT", fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, reviewChamgesPopupXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDisplayValInReviewChangesPopup);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForDisplayValInReviewChangesPopup)) {
				isDisplayedModifiedFileName = true;
			} else {
				isDisplayedModifiedFileName = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedModifiedFileName;
	}

	// Get Text from Status Report Popup
	public String getTextFromStatusReportPopup() {
		String statusReportValue = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, statusReportPopupHeaderXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, publishedDetailsXpathInStatusReportPopup);

			if (UIHelper.checkForAnElementbyXpath(driver, publishedDetailsXpathInStatusReportPopup)) {
				statusReportValue = UIHelper.getTextFromWebElement(driver, publishedDetailsXpathInStatusReportPopup);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusReportValue;
	}

	// Click Publish Option
	public boolean clickPublishPopupOpt(String check) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, pubPopchkXPath);
			if (check.equalsIgnoreCase("check")) {
				UIHelper.click(driver, pubPopchkXPath);
			}
			UIHelper.waitForVisibilityOfEleByXpath(driver, pubBtnXPath);
			Thread.sleep(4000);
			UIHelper.click(driver, pubBtnXPath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedPublishLink;
	}

	// Get Recent Sites from Copy To or Move To Popup
	public ArrayList<String> getRecentSitesFromCopyOrMoveToPopup() {
		ArrayList<String> recentSitesList = new ArrayList<String>();
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, copyOrMoveToPopupHeaderXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, firstSiteItemXpathInCopyOrMovePopup);
			UIHelper.waitFor(driver);

			List<WebElement> recentSitesListEle = UIHelper.findListOfElementsbyXpath(sitesListXpathInCopyOrMoveToPopup,
					driver);

			for (WebElement ele : recentSitesListEle) {
				recentSitesList.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return recentSitesList;
	}

	// Get Favorite Sites from Copy To or Move To Popup
	public ArrayList<String> getFavoriteSitesFromCopyOrMoveToPopup() {
		ArrayList<String> favoriteSitesList = new ArrayList<String>();
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, favoriteSitesLinkXpathInCopyOrMoveToPopup);
			UIHelper.click(driver, favoriteSitesLinkXpathInCopyOrMoveToPopup);
			UIHelper.waitForVisibilityOfEleByXpath(driver, firstSiteItemXpathInCopyOrMovePopup);
			UIHelper.waitFor(driver);

			List<WebElement> favoriteSitesListEle = UIHelper
					.findListOfElementsbyXpath(sitesListXpathInCopyOrMoveToPopup, driver);

			for (WebElement ele : favoriteSitesListEle) {
				favoriteSitesList.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return favoriteSitesList;
	}

	// Get All Sites from Copy To or Move To Popup
	public ArrayList<String> getAllSitesFromCopyOrMoveToPopup() {
		ArrayList<String> allSitesList = new ArrayList<String>();
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, allSitesLinkXpathInCopyOrMoveToPopup);
			UIHelper.click(driver, allSitesLinkXpathInCopyOrMoveToPopup);
			UIHelper.waitForVisibilityOfEleByXpath(driver, firstSiteItemXpathInCopyOrMovePopup);
			UIHelper.waitFor(driver);

			List<WebElement> allSitesListEle = UIHelper.findListOfElementsbyXpath(sitesListXpathInCopyOrMoveToPopup,
					driver);

			for (WebElement ele : allSitesListEle) {
				allSitesList.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return allSitesList;
	}

	// Get Repository Sites (Enabled Sites only) from Copy To or Move To Popup
	public ArrayList<String> getEnabledRepositorySitesFromCopyOrMoveToPopup() {
		ArrayList<String> repositorySitesList = new ArrayList<String>();
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, repositoryLinkXpathInCopyOrMoveToPopup);
			UIHelper.click(driver, repositoryLinkXpathInCopyOrMoveToPopup);
			UIHelper.waitForVisibilityOfEleByXpath(driver, loadingImgXpathInRepositorySec);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingImgXpathInRepositorySec);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			List<WebElement> allSitesListEle = UIHelper.findListOfElementsbyXpath(sitesListXpathInRepositorySec,
					driver);

			for (WebElement ele : allSitesListEle) {
				repositorySitesList.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return repositorySitesList;
	}

	// Compare Repository Sites with Mysites
	public boolean compareRepositorySitesWithMysites(ArrayList<String> repositorySitesList,
			ArrayList<String> mySitesList) {

		boolean isEqualTwolists = false;

		try {
			Set<String> hs1 = new HashSet<>();
			hs1.addAll(repositorySitesList);
			repositorySitesList.clear();
			repositorySitesList.addAll(hs1);

			Set<String> hs2 = new HashSet<>();
			hs2.addAll(mySitesList);
			mySitesList.clear();
			mySitesList.addAll(hs2);

			Collections.sort(repositorySitesList);
			Collections.sort(mySitesList);

			if (mySitesList != null && repositorySitesList != null) {
				for (String val1 : repositorySitesList) {
					for (String val2 : mySitesList) {
						String finalVal1 = val1;
						String finalVal2 = val2.replace("_", "").replace(" ", "-").toLowerCase();
						if (finalVal2.trim().equalsIgnoreCase(finalVal1.trim())
								|| finalVal2.trim().contains(finalVal1.trim())
								|| finalVal1.trim().contains(finalVal2.trim())) {
							isEqualTwolists = true;
							break;
						} else {
							isEqualTwolists = false;
						}
					}
				}
			} else {
				isEqualTwolists = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isEqualTwolists;
	}

	// common method to check on 'Selected Items' Option link
	public void checkSelectedItemsMenuOption(String selectItemMenuOptName) {
		try {

			List<WebElement> selectedItemsMenuList = UIHelper.findListOfElementsbyXpath(selectedItemsMenuOptionXpath,
					driver);

			for (WebElement selectedItemOptEle : selectedItemsMenuList) {
				if (selectedItemOptEle.getText().equalsIgnoreCase(selectItemMenuOptName)
						|| selectedItemOptEle.getText().contains(selectItemMenuOptName)) {

					UIHelper.highlightElement(driver, selectedItemOptEle);

					report.updateTestLog("Check Selected itmes option available " + selectItemMenuOptName,
							"selected items option availble " + selectItemMenuOptName, Status.PASS);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to Unlink single location
	public void unlinkSingleLocation() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, secondaryPathXpath);
			UIHelper.highlightElement(driver, secondaryPathXpath);
			UIHelper.click(driver, secondaryPathXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, unLinkBtnXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, restAllXpath)) {
				UIHelper.highlightElement(driver, unLinkBtnXpath);
				UIHelper.click(driver, restAllXpath);
			}

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, unLinkBtnXpath);
			 */
			UIHelper.highlightElement(driver, unLinkBtnXpath);
			UIHelper.click(driver, unLinkBtnXpath);

			report.updateTestLog("Unlink location of a linked file", "Loccation unlinked from the linked file",
					Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Unlink location of a linked file", "Failed to unlink", Status.FAIL);
		}
	}

	// method to Unlink all location
	public void unlinkAllLocation() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, AllLocationSelectXpath);
			UIHelper.highlightElement(driver, AllLocationSelectXpath);
			UIHelper.click(driver, AllLocationSelectXpath);
			UIHelper.selectbyVisibleText(driver, AllLocationSelectXpath, "All");
			UIHelper.waitForVisibilityOfEleByXpath(driver, unLinkBtnXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, restAllXpath)) {
				UIHelper.highlightElement(driver, unLinkBtnXpath);
				UIHelper.click(driver, restAllXpath);
			}

			UIHelper.highlightElement(driver, unLinkBtnXpath);
			UIHelper.click(driver, unLinkBtnXpath);
			report.updateTestLog("Unlink All locations of a linked file",
					"All Loccations unlinked from the linked file", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Unlink All locations of a linked file", "Failed to unlink", Status.FAIL);
		}
	}

	// method to verify warning message for unlink
	public String verifyWarningMsg(String expectedMessageVal) {
		try {

			if (expectedMessageVal != null && !expectedMessageVal.isEmpty()) {
				String finalXpathForMessage = tempXpathForMessage.replace("CRAFT", expectedMessageVal);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMessage);
				messageText = UIHelper.getTextFromWebElement(driver, finalXpathForMessage);
				report.updateTestLog("Verify Warning message", "Warning message dispalyed successfully", Status.PASS);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, finalXpathForMessage);

			}

		} catch (Exception e) {
			e.printStackTrace();

			report.updateTestLog("Verify Warning message", "Failed to display warning message", Status.FAIL);

		}
		return expectedMessageVal;
	}

	// method to Unlink single location using next button
	public void unlinkSingleLocationUsingNextBtn() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, secondaryPathXpath);

			UIHelper.highlightElement(driver, secondaryPathXpath);
			UIHelper.click(driver, secondaryPathXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, unLinkBtnXpath);

			while (UIHelper.checkForAnElementbyXpath(driver, nextBtnXpath)) {

				UIHelper.highlightElement(driver, unLinkBtnXpath);
				UIHelper.click(driver, unLinkBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, secondaryPathXpath);
				UIHelper.highlightElement(driver, secondaryPathXpath);
				UIHelper.click(driver, secondaryPathXpath);

			}

			UIHelper.highlightElement(driver, unLinkBtnXpath);
			UIHelper.click(driver, unLinkBtnXpath);

			UIHelper.highlightElement(driver, unLinkBtnXpath);
			UIHelper.click(driver, unLinkBtnXpath);

			report.updateTestLog("Unlink location of a linked file", "Location unlinked from the linked file",
					Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Unlink location of a linked file", "Failed to unlink", Status.FAIL);
		}
	}

	// method to Unlink all location using Next Btn
	public void unlinkAllLocationUsingNextBtn() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, AllLocationSelectXpath);

			UIHelper.highlightElement(driver, AllLocationSelectXpath);
			UIHelper.click(driver, AllLocationSelectXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, unLinkBtnXpath);

			UIHelper.selectbyVisibleText(driver, AllLocationSelectXpath, "All");

			while (UIHelper.checkForAnElementbyXpath(driver, nextBtnXpath)) {

				UIHelper.highlightElement(driver, unLinkBtnXpath);
				UIHelper.click(driver, unLinkBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, AllLocationSelectXpath);
				UIHelper.highlightElement(driver, AllLocationSelectXpath);
				UIHelper.click(driver, AllLocationSelectXpath);

				UIHelper.waitForVisibilityOfEleByXpath(driver, unLinkBtnXpath);

				UIHelper.selectbyVisibleText(driver, AllLocationSelectXpath, "All");

			}

			UIHelper.highlightElement(driver, unLinkBtnXpath);
			UIHelper.click(driver, unLinkBtnXpath);

			report.updateTestLog("Unlink All locations of a linked file", "All Locations unlinked from the linked file",
					Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Unlink All locations of a linked file", "Failed to unlink", Status.FAIL);
		}
	}

	// Navigate to folders
	public void naviageToFolder(String openFolder, String expectedFolder) {
		try {
			String finalXpathForOpenFolderNameLink = tempXpathForOpenFolderNameLink.replace("CRAFT", openFolder);
			String finalXpathForFolderNameLink = tempXpathForFolderNameLink.replace("CRAFT", expectedFolder);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForOpenFolderNameLink);
			UIHelper.click(driver, finalXpathForOpenFolderNameLink);

			UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForFolderNameLink);
			report.updateTestLog("Navigate to folder", "Navigated to Folder:" + expectedFolder, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Current folder item
	public String getFolderNameFromCurrentDirectory() {
		String folderItemName = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonXpathForFirstDoc);
			folderItemName = UIHelper.getTextFromWebElement(driver, commonXpathForFirstDoc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return folderItemName;
	}

	// Get Current folder items
	public ArrayList<String> getFolderDetailsFromCurrentDirectory() {
		ArrayList<String> folderItemsList = new ArrayList<String>();
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonXpathForFirstDoc);
			List<WebElement> folderItemsListEle = UIHelper.findListOfElementsbyXpath(commonXpathForDocItems, driver);

			for (WebElement ele : folderItemsListEle) {
				folderItemsList.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return folderItemsList;
	}

	// Get folder details under Current folder
		public ArrayList<String> getFolderDetails() {
			ArrayList<String> folderItemsList = new ArrayList<String>();
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, folderDetailUnderFolderXpath);
				List<WebElement> folderItemsListEle = UIHelper.findListOfElementsbyXpath(folderDetailUnderFolderXpath, driver);

				for (WebElement ele : folderItemsListEle) {
					folderItemsList.add(ele.getText().trim());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return folderItemsList;
		}
	
	// Get Default Breadcumb link val
	public String getDefaultBreadcumbLinkVal() {
		String defaultBreadcumbLinkVal = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, defaultBreadcumbLink);

			defaultBreadcumbLinkVal = UIHelper.getTextFromWebElement(driver, defaultBreadcumbLink);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return defaultBreadcumbLinkVal;
	}

	// Click on Ok Button in Popup Window
	public void clickOnOkBtnInPopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpath);
			UIHelper.click(driver, okBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			report.updateTestLog("Click on 'OK' button in Popup", "User clicked 'Ok' button successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Confirmation window
	public void verifyConfirmationWindow() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpath);
			// UIHelper.click(driver, okBtnXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, confirmXpath)) {
				UIHelper.highlightElement(driver, confirmXpath);
				report.updateTestLog("Verify Confirmation Window", "Confirmation Window displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify Confirmation Window", "Confirmation Window not displayed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Confirmation Window", "Confirmation Window not displayed", Status.FAIL);
		}
	}

	// Click on Cancel Button in Popup Window
	public void clickOnOkcancelBtnInPopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelXpath);
			UIHelper.click(driver, cancelXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			report.updateTestLog("Click Cancel in Popup", "Click Cancel in Popup successful", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click Cancel in Popup", "Click Cancel in Popup failed", Status.FAIL);
		}
	}
	// Click on Cancel Button in Upload new version
			public void clickOnCancelBtnInUploadNewVersion() {
				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, cancelBtnNewVersionXpath);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, cancelBtnNewVersionXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);


				report.updateTestLog("Click Cancel in Upload new version Popup", "Click Cancel in upload new version pop up successful", Status.DONE);
			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog("Click Cancel in Upload new version Popup", "Click Cancel in Upload new version Popup failed", Status.FAIL);
			}
			}
	// method to check option available in more options
	public boolean commonMethodToCheckMoreSettingsOption(String fileOrfolderName, String moreSettingsOptName) {
		boolean flag = false;
		try {
			String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink.replace("CRAFT", fileOrfolderName)
					.replace("MORE_OPT", moreSettingsOptName);
			WebElement copyToElement = UIHelper.findAnElementbyXpath(driver, finalXpathForCopyToFolderLink);
			if (copyToElement.isDisplayed()) {
				UIHelper.highlightElement(driver, copyToElement);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;

		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param viewType
	 */
	public void selectDocLibViewType(String viewType) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, docLibOptionMenuXapth);
			UIHelper.highlightElement(driver, docLibOptionMenuXapth);
			UIHelper.click(driver, docLibOptionMenuXapth);

			UIHelper.waitFor(driver);

			String optionMenuValueXapth = tempOptionMenuValueXapth.replace("CRAFT", viewType);
			UIHelper.waitForVisibilityOfEleByXpath(driver, optionMenuValueXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, optionMenuValueXapth))) {
				UIHelper.highlightElement(driver, optionMenuValueXapth);
				report.updateTestLog("Select Document library view type",
						"Selected successful" + "<br /><b> View Type : </b>" + viewType, Status.PASS);
				UIHelper.click(driver, optionMenuValueXapth);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			} else {
				report.updateTestLog("Select Document library view type",
						"Not Selected" + "<br /><b> View Type : </b>" + viewType, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Document library view type", "Select Document library view type Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public boolean isOfflineLockMsgDisplayed() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, offlineLockXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, offlineLockXapth))) {
				UIHelper.highlightElement(driver, offlineLockXapth);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify the user is able to lock a file",
					"Verify the user is able to lock a file Failed", Status.FAIL);
		}
		return flag;
	}

	// common method for check on 'Selected Items' Option link
	public boolean commonMethodToCheckOnSelectedItemsMenuOption(String selectItemMenuOptName) {
		boolean flag = false;
		try {

			List<WebElement> selectedItemsMenuList = UIHelper.findListOfElementsbyXpath(selectedItemsMenuOptionXpath,
					driver);

			for (WebElement selectedItemOptEle : selectedItemsMenuList) {
				if (selectedItemOptEle.getText().equalsIgnoreCase(selectItemMenuOptName)
						|| selectedItemOptEle.getText().contains(selectItemMenuOptName)) {
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

	// To unclick on All document options
	// (Unlike, Remove document from favorites,Comment on this document, Share
	// document, Unfollow-action enabled)
	public void UnclickOnDocOptions(String fileName, String param) {
		try {
			highlightTheDocLibSection(fileName);
			String finalDocumentOptionsXpath = tempDocRemoveOptionsXpath.replace("CRAFT", fileName).replace("LINK",
					param);

			if (UIHelper.checkForAnElementbyXpath(driver, finalDocumentOptionsXpath)) {
				UIHelper.click(driver, finalDocumentOptionsXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Unclick on" + param + "option",
						"Unclicked the <b>" + param + "</b> action for <br/><b>File Name:</b> " + fileName,
						Status.DONE);
			} else {
				report.updateTestLog("Unclick on" + param + "option",
						"Unable to Unclick the <b>" + param + "</b> action for <br/><b>File Name:</b> " + fileName,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Unclick on" + param + "option",
					"Unable to Unclick the <b>" + param + "</b> action for <br/><b>File Name:</b> " + fileName,
					Status.FAIL);
		}
	}

	// Select folder to copy in copy popup and check Collection obj icon
	public void performCopyToIconVerify(String siteName, String destinationFolder, String collFolder, String objName) {
		try {

			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);

			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", siteName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectFileToMoveXpath);
			UIHelper.click(driver, finalSelectFileToMoveXpath);
			UIHelper.waitFor(driver);

			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);

			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);

			String finalXpathForCollFolderIcon = tempXpathForCollFolderIcon.replace("CRAFT", destinationFolder)
					.replace("COLL", collFolder);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCollFolderIcon);

			WebElement destCollFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForCollFolderIcon);
			UIHelper.highlightElement(driver, destFolderEle);
			executor.executeScript("arguments[0].click();", destCollFolderEle);

			String finalXpathForCollIcon = tempXpathForCollIcon.replace("CRAFT", destinationFolder)
					.replace("COLL", collFolder).replace("COLLECTION", objName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCollIcon);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForCollIcon)) {
				WebElement destCollFolderIconEle = UIHelper.findAnElementbyXpath(driver, finalXpathForCollIcon);
				UIHelper.highlightElement(driver, destCollFolderIconEle);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify custom icons in the picker in Copy to popup",
						"Custom icons in the picker in Copy to popup is displayed as expected", Status.PASS);
			} else {
				report.updateTestLog("Verify custom icons in the picker in Copy to popup",
						"Custom icons in the picker in Copy to popup is not displayed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify custom icons in the picker in Copy to popup",
					"Custom icons in the picker in Copy to popup is not displayed", Status.FAIL);
		}
	}

	// Check Folder size
	public String getFolderSize() {
		String promptMessageVal = "";
		try {
			try {
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, promptHeaderXpath);
				// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				// messageEleXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, promptMessageValXpath);
				// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				// messageEleXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (UIHelper.getTextFromWebElement(driver, promptMessageValXpath)
					.contains("Size of the selected folders")) {
				promptMessageVal = UIHelper.getTextFromWebElement(driver, promptMessageValXpath);
			} else {
				promptMessageVal = "Size Not get displayed for selected folders";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return promptMessageVal;
	}

	// Click on 'Ok' button in 'Folder Size' popup
	public void clickOnOkbuttonInFolderSizePopup() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathInFolderSizePopup);
			if (UIHelper.checkForAnElementbyXpath(driver, okBtnXpathInFolderSizePopup)) {
				System.out.println("Entered");
				UIHelper.click(driver, okBtnXpathInFolderSizePopup);
				UIHelper.waitFor(driver);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public boolean isDiscriptionFieldAvailable() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, discriptionFieldXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, discriptionFieldXpath))) {
				UIHelper.highlightElement(driver, discriptionFieldXpath);
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
	public boolean isCreateFolderAsFirstMenu() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, firstMenuInCreateOptionXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, firstMenuInCreateOptionXapth))) {
				UIHelper.highlightElement(driver, firstMenuInCreateOptionXapth);
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
	public boolean isCategoryTagAvailable(String fileOrFolderName, String categoryType) {
		boolean flag = false;
		try {
			String finalXpath = categoryTagXpath.replace("CRAFT", fileOrFolderName).replace("CATEGORY", categoryType);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalXpath))) {
				UIHelper.highlightElement(driver, finalXpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Check Category Item link for content in Doc Lib Page
	public boolean isDisplayedCategoryItemLinkInDocLibPag(String fileOrFolderName, String categoryItem) {
		boolean isDisplayedCategoryItem = false;
		try {

			highlightTheDocLibSection(fileOrFolderName);
			String finalXpathForCategoryItemLink = tempXpathForCategoryItemLink.replace("FILE_NAME", fileOrFolderName)
					.replace("CATEGORY_ITEM", categoryItem);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForCategoryItemLink)) {
				UIHelper.highlightElement(driver, finalXpathForCategoryItemLink);
				isDisplayedCategoryItem = true;
			} else {
				isDisplayedCategoryItem = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedCategoryItem;
	}

	// Sort the file/folder items in document library page
	public void sortDocLibByGivenOption(String sortOtion) {
		try {
			String finalXpathForSortMenuItem = tempXpathForSortMenuItem.replace("CRAFT", sortOtion);
			String finalXpathForSortBtn = tempXpathForSortBtn.replace("CRAFT", sortOtion);

			if (UIHelper.checkForAnElementbyXpath(driver, sortFieldBtnXpath)) {
				UIHelper.click(driver, sortFieldBtnXpath);
				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, sortMenuItemBodyXpath);
				} catch (Exception e) {
					e.printStackTrace();
				}

				WebElement finalXpathForSortMenuItemEle = null;

				try {
					finalXpathForSortMenuItemEle = UIHelper.findAnElementbyXpath(driver, finalXpathForSortMenuItem);
				} catch (Exception e) {
					e.printStackTrace();
				}

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", finalXpathForSortMenuItemEle);

				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
				UIHelper.waitFor(driver);

				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSortBtn)) {
					report.updateTestLog("Verify sort in 'Document Library' page",
							"User sorted the 'Document Library'page successfully using sort option:" + sortOtion,
							Status.PASS);
				} else {
					report.updateTestLog("Verify sort in 'Document Library' page",
							"User failed to sort the 'Document Library'page using sort option:" + sortOtion,
							Status.FAIL);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on BreadCrumb link using particular folder name link in Document
	// Library page
	public void clickOnBreadCrumbLinkDocLibPg(String folderName) {
		try {
			String finalXpathForBreadCrumb = tempXpathForBreadCrumb.replace("CRAFT", folderName);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForBreadCrumb);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForBreadCrumb)) {
				UIHelper.highlightElement(driver, finalXpathForBreadCrumb);
				UIHelper.click(driver, finalXpathForBreadCrumb);

				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);

				Thread.sleep(2000);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);

				if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
					UIHelper.click(driver, docActionTwisterCloseXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
					UIHelper.waitFor(driver);
				}

				report.updateTestLog("Click on 'Breadcrumb' link",
						"Clicked the 'Breadcrumb' using Folder: " + folderName, Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Site menu bar item
	public void clickOnDocOrLibraryFilterItem(String filterOption, String filterItem, String minutes,
			String negOrPoscase) {
		try {

			String finalXpathForDocumentsFilterItem = tempXpathForDocumentsFilterItem.replace("CRAFT", filterItem);
			String finalXpathForLibraryFilterItem = tempXpathForLibraryFilterItem.replace("CRAFT", filterItem);

			UIHelper.waitForPageToLoad(driver);
			try {
				if (filterOption.equalsIgnoreCase("Documents")) {
					if (UIHelper.checkForAnElementbyXpath(driver, documentsFilterTwisterCloseXpath)) {
						UIHelper.click(driver, documentsFilterTwisterCloseXpath);
						UIHelper.waitFor(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, documentsFilterTwisterOpenXpath);
					}

					UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDocumentsFilterItem);
				} else {
					if (UIHelper.checkForAnElementbyXpath(driver, libraryFilterTwisterCloseXpath)) {
						UIHelper.click(driver, libraryFilterTwisterCloseXpath);
						UIHelper.waitFor(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, libraryFilterTwisterOpenXpath);
					}

					UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForLibraryFilterItem);
				}

			} catch (Exception e) {
			}

			if (filterOption.equalsIgnoreCase("Documents")) {
				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForDocumentsFilterItem)) {
					UIHelper.click(driver, finalXpathForDocumentsFilterItem);

					report.updateTestLog("Click on '" + filterItem + "'",
							"User clicked the '" + filterItem + "' @ " + minutes + " time", Status.DONE);

					report.updateTestLog("Verify the current time",
							"Current Time: " + new DateUtil().getCurrentDateAndTime(), Status.DONE);

					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);

					if (negOrPoscase.equalsIgnoreCase("Negative")) {
						if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForDocumentsFilterItem)) {
							report.updateTestLog("Verify applied '" + filterItem,
									"Filter:'" + filterItem + "' applied successfully", Status.PASS);
						} else {
							report.updateTestLog("Verify applied '" + filterItem,
									"Filter:'" + filterItem + "' failed to apply", Status.FAIL);
						}
					} else if (negOrPoscase.equalsIgnoreCase("Positive")) {
						if (UIHelper.checkForAnElementbyXpath(driver, userNameFieldXpath)) {
							report.updateTestLog("Verify login page", "Login page displayed successfully", Status.PASS);
						} else {
							report.updateTestLog("Verify login page", "Failed to display login page", Status.FAIL);
						}
					}
				}
			} else {
				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForLibraryFilterItem)) {
					UIHelper.click(driver, finalXpathForLibraryFilterItem);

					report.updateTestLog("Click on '" + filterItem + "'",
							"User clicked the '" + filterItem + "' @ " + minutes + " time", Status.DONE);

					report.updateTestLog("Verify the current time",
							"Current Time: " + new DateUtil().getCurrentDateAndTime(), Status.DONE);

					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);

					if (negOrPoscase.equalsIgnoreCase("Negative")) {
						if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForLibraryFilterItem)) {
							report.updateTestLog("Verify applied '" + filterItem,
									"Filter:'" + filterItem + "' applied successfully", Status.PASS);
						} else {
							report.updateTestLog("Verify applied '" + filterItem,
									"Filter:'" + filterItem + "' failed to apply", Status.FAIL);
						}
					} else if (negOrPoscase.equalsIgnoreCase("Positive")) {
						if (UIHelper.checkForAnElementbyXpath(driver, userNameFieldXpath)) {
							report.updateTestLog("Verify login page", "Login page displayed successfully", Status.PASS);
						} else {
							report.updateTestLog("Verify login page", "Failed to display login page", Status.FAIL);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void uploadNewVersionInDocLibPage(String fileName, String filePath, String versionChangeTo,
			String comments) {

		try {
			String finalFilePath = System.getProperty("user.dir") + filePath;

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFileHeaderXpath);

			if (versionChangeTo.equals("Minor")) {
				WebElement minorRadioEle = UIHelper.findAnElementbyXpath(driver, minorRadioXpath);
				UIHelper.highlightElement(driver, minorRadioEle);
				UIHelper.mouseOverandclickanElement(driver, minorRadioEle);

			} else {
				WebElement majorRadioEle = UIHelper.findAnElementbyXpath(driver, majorRadioXpath);
				UIHelper.highlightElement(driver, majorRadioEle);
				UIHelper.mouseOverandclickanElement(driver, majorRadioEle);
			}

			WebElement commentsTextAreaWebElement = UIHelper.findAnElementbyXpath(driver, commentsXpath);
			UIHelper.highlightElement(driver, commentsTextAreaWebElement);
			commentsTextAreaWebElement.sendKeys(comments);

			WebElement uploadFileButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadXpath);
			UIHelper.highlightElement(driver, uploadFileButtonWebElement);

			WebElement uploadInputEle = UIHelper.findAnElementbyXpath(driver, uploadInputTagXpath);
			uploadInputEle.sendKeys(finalFilePath + fileName);

			/*
			 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			 * uploadButtonXpath); UIHelper.waitForPageToLoad(driver);
			 * UIHelper.waitForPageToLoad(driver);
			 */
			report.updateTestLog("Upload New Version File In Document Library Page",
					"New version File uploded Successfully" + "<br /><b>File Name :</b> " + fileName, Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Upload New Version File In Document Library Page",
					"Upload New Version File In Document Library Page Failed" + "<br /><b>File Name :</b> " + fileName,
					Status.FAIL);
		}
	}

	public void uploadNewVersionButton() {

		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtXpath);
			WebElement uploadButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadBtXpath);
			UIHelper.mouseOverandElementdoubleClick(driver, uploadButtonWebElement);
			UIHelper.click(driver, uploadBtXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, uploadButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {

		}
	}

	public void uploadNewnotification(String field, String value, String type) {

		try {

			String finalfiledetails = uploadnewvalue.replace("CRAFT", field).replace("REPLACE", type);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfiledetails);
			UIHelper.highlightElement(driver, finalfiledetails);

			String newvalue = UIHelper.getTextFromWebElement(driver, finalfiledetails);

			if (newvalue.equalsIgnoreCase(value)) {

				report.updateTestLog("Upload New Version " + type + "file details",
						"New file details are displayed" + "<br /><b>field :</b> " + newvalue, Status.PASS);
			} else {
				report.updateTestLog("Upload New Version " + type + "file details",
						"New file details are not displayed", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Upload New Version " + type + "file details", "New file details are not displayed",
					Status.FAIL);
		}
	}
	public boolean uploadNewnotificationPresence(String field, String value, String type) {

		boolean flag=false;
		try {

			String finalfiledetails = uploadnewvalue.replace("CRAFT", field).replace("REPLACE", type);

			//UIHelper.waitForVisibilityOfEleByXpath(driver, finalfiledetails);
			//UIHelper.highlightElement(driver, finalfiledetails);
			if(!UIHelper.checkForAnElementbyXpath(driver, finalfiledetails))
			{

				report.updateTestLog("Verify Upload New Version notification is displayed",
						"Upload New Version notification is not displayed", Status.PASS);
				flag = true;
			} else {
				report.updateTestLog("Verify Upload New Version notification is displayed",
						"Upload New Version notification is displayed", Status.FAIL);
			}		

		} catch (Exception e) {
			report.updateTestLog("Verify Upload New Version notification is displayed", "Upload New Version notification is displayed",
					Status.FAIL);
		}
		return flag;
	}
	
	public boolean verifyLinkIconisnotpresent(String fileOrFolderName) {
		boolean flag = false;
		try {
			String finalImageLinkXpath = linkImgXpath.replace("CRAFT", fileOrFolderName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalImageLinkXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, finalImageLinkXpath)) {
				UIHelper.highlightElement(driver, finalImageLinkXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Check Link Icon", "Link Icon is present for linked file :" + fileOrFolderName,
						Status.PASS);
				flag = true;
			} else {
				// report.updateTestLog("Check Link Icon",
				// "Link Icon is not present for linked file :"
				// + fileOrFolderName, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// report.updateTestLog("Check Link Icon",
			// "Link Icon is not present for linked file :"
			// + fileOrFolderName, Status.FAIL);
		}

		return flag;
	}

	public void createlinkpresence(String folderName, String folderDetails, String[] fileName, String filePath,
			String options) {
		try {
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);

			Boolean flag2 = myFiles.isFileOrFolderDisplayed(folderName);
			if (flag2) {
				myFiles.openCreatedFolder(folderName);
				/*
				 * myFiles.deleteCreatedFolder(folderDetails);
				 * myFiles.createFolder(folderDetails);
				 * myFiles.openCreatedFolder(folderName);
				 */
			} else {
				myFiles.createFolder(folderDetails);
				myFiles.openCreatedFolder(folderName);
			}

			UIHelper.waitFor(driver);

			docLibPage.deleteAllFilesAndFolders();
			// myFiles.openCreatedFolder(folderName);
			for (String NameofFile : fileName) {

				myFiles.uploadFileInMyFilesPage(filePath, NameofFile);

			}

			docLibPage.selectAllFilesAndFolders();
			sitesPage.clickOnSelectedItems();
			UIHelper.waitForPageToLoad(driver);
			sitesPage.selectItemFromSelectedItemsMenuOption(options);
			UIHelper.waitForVisibilityOfEleByXpath(driver, copy2itemsxpath);
			try {
				Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, createlinkxpath));
				report.updateTestLog("Create Link option presence:", "Create Link option is presented ", Status.FAIL);
			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog("Create Link option presence:", "Create Link option is not presented ",
						Status.PASS);
				UIHelper.click(driver, canceloncopy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void checkForClickOnMoreSettingsOption(String fileOrfolderName, String moreSettingsOptName) {

		try {
			String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink.replace("CRAFT", fileOrfolderName)
					.replace("MORE_OPT", moreSettingsOptName);

			WebElement copyToElement = UIHelper.findAnElementbyXpath(driver, finalXpathForCopyToFolderLink);
			UIHelper.highlightElement(driver, copyToElement);
			UIHelper.scrollToAnElement(copyToElement);
			report.updateTestLog("Click on " + moreSettingsOptName, "User able to click the " + moreSettingsOptName,
					Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Click on " + moreSettingsOptName, "User unable to click the " + moreSettingsOptName,
					Status.PASS);
		}
	}
	
	public void deletefolderwithMoreactions() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteBtnXpath);
			UIHelper.click(driver, deleteBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);

			report.updateTestLog("Delete Document", "Document deleted sucessfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	public void addTagContent(String folderOrFileName, String tagName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);


			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			System.out.println("No Of elements 1 : " + uploadedFileOrFolderTitleEleList.size());
			/*List<WebElement> editFileOrFolderEleList = UIHelper.findListOfElementsbyXpath(editTagXpath, driver);
			System.out.println("No Of elements 2 : " + editFileOrFolderEleList.size());
			*/
			

//			int index = 0;
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(folderOrFileName)) {
					
					String finalEditNoTagsLabelTagXpath = editNoTagsLabelTagXpath.replace("CRAFT", folderOrFileName);
					String finalEditIconTagXpath = editIconTagXpath.replace("CRAFT", folderOrFileName);
					String finalEditInputTagXpath = editInputTagXpath.replace("CRAFT", folderOrFileName);
					
					String finalInputTaglistXpath = editInputListTagXpath.replace("CRAFT", folderOrFileName).replace("REPLACETAG", tagName);
					
					
					UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, finalEditNoTagsLabelTagXpath));
					UIHelper.waitFor(driver);
//					UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, finalEditIconTagXpath));
					UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, finalEditIconTagXpath));
					UIHelper.waitFor(driver);
					UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, finalEditInputTagXpath));
					UIHelper.sendKeysToAnElementByXpath(driver, finalEditInputTagXpath, tagName);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalInputTaglistXpath);
					UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, finalInputTaglistXpath));
					UIHelper.waitFor(driver);
					
					
					UIHelper.highlightElement(driver, editTagSaveBtnXpath);
					UIHelper.click(driver, editTagSaveBtnXpath);
					UIHelper.waitFor(driver);
					
					report.updateTestLog("Tag Value for file/Folder", "Tag added successfully"
							+ "<br /><b>Resource Name :</b> " + folderOrFileName + "<br /><b>Resource :</b> " + tagName,
							Status.PASS);
					break;
				}
//				index++;
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Tag Value for file/Folder", "Tag add failed"
					+ "<br /><b>Resource Name :</b> " + folderOrFileName + "<br /><b>Resource :</b> " + tagName,
					Status.FAIL);
		}
	}
	
	// Verify the required button not available in the screen
	public void isRequiredButtonNotAvailable(String name,String location){
		
		
		try {
			boolean flag=false;
			List<WebElement> searchResultsListEle;
			UIHelper.waitFor(driver);
			
			if(location.equalsIgnoreCase("AdvSearch")) {
			 searchResultsListEle = driver.findElements(By.xpath(copyMoveToDialogueButtonAdvSearchXpath));
			 System.out.println(searchResultsListEle);
			}else
			{
			 searchResultsListEle = driver.findElements(By.xpath(copyMoveToDialogButtonsXpath));
			 System.out.println(searchResultsListEle);
			}
			
			for (WebElement ele : searchResultsListEle) {
				if (name.equals(ele.getText())) {
					flag=true;
					break;
					}
				}
			if(flag) {
				report.updateTestLog("Check Button is available", "Button Present"
						+ "<br /><b>Button Name :</b> "  +name ,
						Status.FAIL);
				
			}else {
				report.updateTestLog("Check Button is available", "Button not Present"
						+ "<br /><b>Button Name :</b> "  +name ,
						Status.PASS);
			}
			
		} catch (Exception e) {
			report.updateTestLog("Check Button is available", "Check Button available Failed",
					Status.FAIL);
		}
	
	}
	
	// Filter based on the link present under the Documents list 
	public void filterDocuments(String filterName) {
		try {
			UIHelper.waitFor(driver);
			String finalXpathForDocumentsFilterItem = tempXpathForDocumentsFilterItem.replace("CRAFT", filterName);
			if(UIHelper.checkForAnElementbyXpath(driver,documentsFilterTwisterCloseXpath)){
				UIHelper.highlightElement(driver,documentsFilterTwisterCloseXpath);
				UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, documentsFilterTwisterCloseXpath));
				commonMethodForClickingDocumentsItem(finalXpathForDocumentsFilterItem, filterName);
			}else if(UIHelper.checkForAnElementbyXpath(driver,documentsFilterTwisterOpenXpath)) {
				commonMethodForClickingDocumentsItem(finalXpathForDocumentsFilterItem, filterName);
			}else {
				report.updateTestLog("Click on Documents", "Click on Documents Failed. Documents Not found", Status.FAIL);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Documents", "Click on Documents Failed. Documents Not found", Status.FAIL);
		}

	}
    
	// Method to click on the filter link present below documents list
	public void commonMethodForClickingDocumentsItem(String finalDocumentFilter, String filterName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, finalDocumentFilter);
			UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, finalDocumentFilter));
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on Documents",
					"Click on Documents filter link successfully." + "<br /><b> Document filter Name : </b>" + filterName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method for Upload new Version in Document Details Page from selected items for edited offline file
	 */
	public void uploadNewVersionFileforeditOfflinefile(String fileName, String filePath, String versionChangeTo,
			String comments) {

		try {
			String finalFilePath = System.getProperty("user.dir") + filePath;

			UIHelper.waitForVisibilityOfEleByXpath(driver, minorRadioXpath);

			if (versionChangeTo.equals("Minor")) {
				WebElement minorRadioEle = UIHelper.findAnElementbyXpath(driver, minorRadioXpath);
				UIHelper.highlightElement(driver, minorRadioEle);
				UIHelper.mouseOverandclickanElement(driver, minorRadioEle);

			} else {
				WebElement majorRadioEle = UIHelper.findAnElementbyXpath(driver, majorRadioXpath);
				UIHelper.highlightElement(driver, majorRadioEle);
				UIHelper.mouseOverandclickanElement(driver, majorRadioEle);
			}

			WebElement commentsTextAreaWebElement = UIHelper.findAnElementbyXpath(driver, commentsXpath);
			UIHelper.highlightElement(driver, commentsTextAreaWebElement);
			commentsTextAreaWebElement.sendKeys(comments);

			WebElement uploadFileButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadXpath);
			UIHelper.highlightElement(driver, uploadFileButtonWebElement);

			WebElement uploadInputEle = UIHelper.findAnElementbyXpath(driver, uploadXpathforeditoff);
			uploadInputEle.sendKeys(finalFilePath + fileName);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtXpatheditoffline);
			WebElement uploadButtonWebElement = UIHelper.findAnElementbyXpath(driver, uploadBtXpatheditoffline);
			// UIHelper.click(driver, uploadBtXpath);
			// UIHelper.mouseOverandElementdoubleClick(driver,
			// uploadButtonWebElement);
			UIHelper.javascriptClick(driver, uploadButtonWebElement);

			report.updateTestLog("Upload New Version File In Document Library Page",
					"New version File uploded Successfully" + "<br /><b>File Name :</b> " + fileName, Status.PASS);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, uploadButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Upload New Version File In Document Library Page",
					"Upload New Version File In Document Library Page Failed" + "<br /><b>File Name :</b> " + fileName,
					Status.FAIL);
		}
	}
	
	// Check select Items menu is enabled in doc Library
	  public boolean isSelectItemsMenuEnabled(){
				boolean flag=false;
					if(UIHelper.checkForAnElementbyXpath(driver, visibleSelectedItemsXpath)){
						flag=true;
					}
				return flag;
			}
			
		// move to particular file or folder in doc lib	
	  public void scrollToFileorFolder(String fileorFolder){
			       String fileorFolderName=	fileNameXpath.replace("CRAFT", fileorFolder);
			       try{
			    	   UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, fileorFolderName));
			    	   //UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, fileorFolderName));
			    	   report.updateTestLog("Scroll to File or Folder",
								"Scroll to File or Folder successfully.", Status.DONE);
			       }catch(Exception e){
			    	   e.getMessage();
			    	   report.updateTestLog("Scroll to File or Folder",
								"Scroll to File or Folder Unsuccessfull.", Status.FAIL);
			       }
			}	
	  
	  // Click on check box for a particular file or folder
	  public void clickOnCheckBoxForFileorFolderInDocLib(String fileOrFolderName) {
		  try {
			  String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			  UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectFolderChkboxXpath);
			  UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath));
			  UIHelper.waitFor(driver);
			  report.updateTestLog("Click on CheckBox for a File/Folder", "Click on check box successfull ", Status.DONE);
		  }catch(Exception e) {
			  report.updateTestLog("Click on CheckBox for a File/Folder", "Click on check box failed ", Status.FAIL);
		  }
	  }
	  
	  // Click on Calculate Size from Selected Items Menu
	  public void clickOnCalculateSizeInSelectedItemsMenu() {
		  try {
				if (UIHelper.checkForAnElementbyXpath(driver, visibleSelectedItemsXpath)) {
					UIHelper.click(driver, selectedItemsXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, calculateSizeInSelectedItemsMenuXpath);
					UIHelper.highlightElement(driver, calculateSizeInSelectedItemsMenuXpath);
					UIHelper.click(driver, calculateSizeInSelectedItemsMenuXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, promptHeaderXpath);
					UIHelper.waitFor(driver);
					report.updateTestLog("Click on Calculate Size from selected Items menu", "Calculate Size click is successfull", Status.DONE);
				} else {
					report.updateTestLog("Click on Calculate Size from selected Items menu", "Selected Items Menu is disabled", Status.FAIL);
				}
			} catch (Exception e) {
				report.updateTestLog("Click on Calculate Size from selected Items menu", "Calculate Size click Unsuccessfull", Status.FAIL);
			}
		}


public void verifyShareFolderExternallyText(String message) {
			String text="";
			try {
				
				UIHelper.waitForVisibilityOfEleByXpath(driver, shareFolderTextXpath);
				UIHelper.highlightElement(driver, shareFolderTextXpath);
				text=UIHelper.getTextFromWebElement(driver, shareFolderTextXpath);
				if(text.equalsIgnoreCase(message)) {
					report.updateTestLog("Verify Share folder externally more option text", "Share folder externally: " +message+ " is present",
							Status.PASS);
					} else {
						report.updateTestLog("Verify Share folder externally more option text", "Share folder externally: " +message+ " is not present",
								Status.FAIL);
					}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			report.updateTestLog("Verify Share folder externally more option text", "Share folder externally: " +message+ " is not present",
					Status.FAIL);
		}
			
	}
	  
	  
	  public void verifyExcludeFromShareboxText(String message) {
			String text="";
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, excludeFromShareboxXpath);
				UIHelper.highlightElement(driver, excludeFromShareboxXpath);
				text=UIHelper.getTextFromWebElement(driver, excludeFromShareboxXpath);
				if(text.equalsIgnoreCase(message)) {
					report.updateTestLog("Verify Exclude from sharebox more option text", "Exclude from sharebox: " +message+ " is present",
							Status.PASS);
					} else {
						report.updateTestLog("Verify Exclude from sharebox more option text", "Exclude from sharebox: " +message+ " is not present",
								Status.FAIL);
					}
			
		}catch(Exception e) {
			report.updateTestLog("Verify Exclude from sharebox more option text", "Exclude from sharebox: " +message+ " is not present",
					Status.FAIL);
		}
			
	}
	  
	  
	  public void verifyExcludeFromShareboxTextNotPresent(String message) {
			String text="";
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, excludeFromShareboxXpath);
				UIHelper.highlightElement(driver, excludeFromShareboxXpath);
				text=UIHelper.getTextFromWebElement(driver, excludeFromShareboxXpath);
				if(text.equalsIgnoreCase(message)) {
					report.updateTestLog("Verify Exclude from sharebox more option text", "Exclude from sharebox: " +message+ " is present",
							Status.FAIL);
					} else {
						report.updateTestLog("Verify Exclude from sharebox more option text", "Exclude from sharebox: " +message+ " is not present",
								Status.PASS);
					}
			
		}catch(Exception e) {
			report.updateTestLog("Verify Exclude from sharebox more option text", "Exclude from sharebox: " +message+ " is not present",
					Status.PASS);
		}
			
	}
	  
	  
	  public void verifyRemoveShareboxExclusionText(String message) {
			String text="";
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, removeShareboxExclusionXpath);
				UIHelper.highlightElement(driver, removeShareboxExclusionXpath);
				text=UIHelper.getTextFromWebElement(driver, removeShareboxExclusionXpath);
				if(text.equalsIgnoreCase(message)) {
					report.updateTestLog("Verify Remove Share box exclusion more option text", "Remove Share box exclusion: " +message+ " is present",
							Status.PASS);
					} else {
						report.updateTestLog("Verify Remove Share box exclusion more option text", "Remove Share box exclusion: " +message+ " is not present",
								Status.FAIL);
					}
			
		}catch(Exception e) {
			report.updateTestLog("Verify Remove Share box exclusion more option text", "Remove Share box exclusion: " +message+ " is not present",
					Status.FAIL);
		}
			
	}
	  
	  public void verifyExcludeFromShareboxTextInFolderDetailsPage(String message) {
		  
		  String excludeFromShareboxInFolderDetailsXpathFinal = excludeFromShareboxInFolderDetailsXpath.replace("CRAFT", message);
		  
			String text="";
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, excludeFromShareboxInFolderDetailsXpathFinal);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, excludeFromShareboxInFolderDetailsXpathFinal));
				UIHelper.highlightElement(driver, excludeFromShareboxInFolderDetailsXpathFinal);
				text=UIHelper.getTextFromWebElement(driver, excludeFromShareboxInFolderDetailsXpathFinal);
				if(text.equalsIgnoreCase(message)) {
					report.updateTestLog("Verify Exclude from sharebox more option text", "Exclude from sharebox: " +message+ " is present",
							Status.PASS);
					} else {
						report.updateTestLog("Verify Exclude from sharebox more option text", "Exclude from sharebox: " +message+ " is not present",
								Status.FAIL);
					}
			
		}catch(Exception e) {
			report.updateTestLog("Verify Exclude from sharebox more option text", "Exclude from sharebox: " +message+ " is not present",
					Status.FAIL);
		}
			
	}
	  
public void verifyRemoveShareboxExclusionTextInFolderDetailsPage(String message) {
		  
		  String removeShareboxExclusionInFolderDetailsXpathFinal = removeShareboxExclusionInFolderDetailsXpath.replace("CRAFT", message);
		  
			String text="";
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, removeShareboxExclusionInFolderDetailsXpathFinal);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, removeShareboxExclusionInFolderDetailsXpathFinal));
				UIHelper.highlightElement(driver, removeShareboxExclusionInFolderDetailsXpathFinal);
				text=UIHelper.getTextFromWebElement(driver, removeShareboxExclusionInFolderDetailsXpathFinal);
				if(text.equalsIgnoreCase(message)) {
					report.updateTestLog("Verify Remove Share box exclusion more option text", "Remove Share box exclusion: " +message+ " is present",
							Status.PASS);
					} else {
						report.updateTestLog("Verify Remove Share box exclusion more option text", "Remove Share box exclusion: " +message+ " is not present",
								Status.FAIL);
					}
			
		}catch(Exception e) {
			report.updateTestLog("Verify Remove Share box exclusion more option text", "Remove Share box exclusion: " +message+ " is not present",
					Status.FAIL);
		}
			
	}
	  
	  
	public void clickOnExcludeFromShareboxInFolderDetailsPage(String message){
		
		String excludeFromShareboxInFolderDetailsXpathFinal = excludeFromShareboxInFolderDetailsXpath.replace("CRAFT", message);
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, excludeFromShareboxInFolderDetailsXpathFinal);
			UIHelper.highlightElement(driver, excludeFromShareboxInFolderDetailsXpathFinal);
			UIHelper.click(driver, excludeFromShareboxInFolderDetailsXpathFinal);
			report.updateTestLog("Click on Exclude from Sharebox",
					"Click on Exclude from Sharebox successfully.", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click on Exclude from Sharebox",
					"Click on Exclude from Sharebox Unsuccessfull.", Status.FAIL);
		}
	}
	  
	
	public void clickOnRemoveShareboxExclusionInFolderDetailsPage(String message){
		
		String removeShareboxExclusionInFolderDetailsXpathFinal = removeShareboxExclusionInFolderDetailsXpath.replace("CRAFT", message);
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, removeShareboxExclusionInFolderDetailsXpathFinal);
			UIHelper.highlightElement(driver, removeShareboxExclusionInFolderDetailsXpathFinal);
			UIHelper.click(driver, removeShareboxExclusionInFolderDetailsXpathFinal);
			report.updateTestLog("Click on Remove Sharebox exclusion",
					"Click on Remove Sharebox exclusion successfully.", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click on Remove Sharebox exclusion",
					"Click on Remove Sharebox exclusion Unsuccessfull.", Status.FAIL);
		}
	}
	
	
	public void clickOnExcludeFromSharebox(String message){
		
		String excludeFromShareboxXpathFinal = excludeFromShareboxXpath.replace("CRAFT", message);
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, excludeFromShareboxXpathFinal);
			UIHelper.highlightElement(driver, excludeFromShareboxXpathFinal);
//			UIHelper.click(driver, excludeFromShareboxInFolderDetailsXpathFinal);
			UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, excludeFromShareboxXpathFinal));
			report.updateTestLog("Click on Exclude from Sharebox",
					"Click on Exclude from Sharebox successfully.", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click on Exclude from Sharebox",
					"Click on Exclude from Sharebox Unsuccessfull.", Status.FAIL);
		}
	}
	
	public void clickOnRemoveShareboxExclusion(String message){
		
		String removeShareboxExclusionXpathFinal = removeShareboxExclusionXpath.replace("CRAFT", message);
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, removeShareboxExclusionXpathFinal);
			UIHelper.highlightElement(driver, removeShareboxExclusionXpathFinal);
//			UIHelper.click(driver, excludeFromShareboxInFolderDetailsXpathFinal);
			UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, removeShareboxExclusionXpathFinal));
			report.updateTestLog("Click on Remove Sharebox exclusion",
					"Click on Remove Sharebox exclusion successfully.", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click on Remove Sharebox exclusion",
					"Click on Remove Sharebox exclusion Unsuccessfull.", Status.FAIL);
		}
	}
	
	public void verifyExcludeShareboxPopupContents(String cancelButtonText, String popupHeaderText, String popupText){
		
		
		try {
			String cancelButtonXpathFinal = cancelButtonXpath.replace("CRAFT", cancelButtonText);
			String popupHeaderXpathFinal = popupHeaderXpath.replace("CRAFT", popupHeaderText);
			String popupTextXpathFinal = popupTextXpath.replace("CRAFT", popupText);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupXpath);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupHeaderXpathFinal);
			UIHelper.highlightElement(driver, popupHeaderXpathFinal);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupTextXpathFinal);
			UIHelper.highlightElement(driver, popupTextXpathFinal);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpath);
			UIHelper.highlightElement(driver, okBtnXpath);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelButtonXpathFinal);
			UIHelper.highlightElement(driver, cancelButtonXpathFinal);
			
			report.updateTestLog("Verify exclude from sharebox popup contents and buttons",
					"Exclude from sharebox popup contents and buttons displayed successfully", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Verify exclude from sharebox popup contents and buttons",
					"Exclude from sharebox popup contents and buttons are not displayed", Status.FAIL);
		}
		
	}
	
public void verifyRemoveShareboxExclusionPopupContents(String cancelButtonText, String popupHeaderText, String popupText){
		
		
		try {
			String cancelButtonXpathFinal = cancelButtonXpath.replace("CRAFT", cancelButtonText);
			String popupHeaderXpathFinal = popupHeaderXpath.replace("CRAFT", popupHeaderText);
			String popupTextXpathFinal = popupTextXpath.replace("CRAFT", popupText);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupXpath);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupHeaderXpathFinal);
			UIHelper.highlightElement(driver, popupHeaderXpathFinal);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupTextXpathFinal);
			UIHelper.highlightElement(driver, popupTextXpathFinal);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpath);
			UIHelper.highlightElement(driver, okBtnXpath);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelButtonXpathFinal);
			UIHelper.highlightElement(driver, cancelButtonXpathFinal);
			
			report.updateTestLog("Verify exclude from sharebox popup contents and buttons",
					"Exclude from sharebox popup contents and buttons displayed successfully", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Verify exclude from sharebox popup contents and buttons",
					"Exclude from sharebox popup contents and buttons are not displayed", Status.FAIL);
		}
		
	}
	
	public void clickOnCancelButtonInPopup(String cancelButtonText){
	
	try {
		String cancelButtonXpathFinal = cancelButtonXpath.replace("CRAFT", cancelButtonText);
		
		UIHelper.waitForVisibilityOfEleByXpath(driver, cancelButtonXpathFinal);
		UIHelper.highlightElement(driver, cancelButtonXpathFinal);
		UIHelper.click(driver, cancelButtonXpathFinal);
		report.updateTestLog("Click on Cancel Button in Popup",
				"Click on Cancel Button successfull.", Status.DONE);
	} catch (Exception e) {
		report.updateTestLog("Click on Cancel Button in Popup",
				"Click on Cancel Button Unsuccessfull.", Status.FAIL);
	}
}
	
	
	public void clickOnOkButtonInPopup(){
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpath);
			UIHelper.highlightElement(driver, okBtnXpath);
//			UIHelper.click(driver, okButtonXpath);
			UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, okBtnXpath));
			report.updateTestLog("Click on OK Button in Popup",
					"Click on OK Button successfull.", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click on OK Button in Popup",
					"Click on OK Button Unsuccessfull.", Status.FAIL);
		}
	}

       
	// Get Prompt Header message
		  public String getPromptHeaderMessage() {
			  String promptHeaderVal="";
			  try {
				  UIHelper.waitForVisibilityOfEleByXpath(driver, promptHeaderXpath);
				  UIHelper.highlightElement(driver, promptHeaderXpath);
				  promptHeaderVal=UIHelper.getTextFromWebElement(driver, promptHeaderXpath);
			  }catch(Exception e) {
				  report.updateTestLog("Get Prompt Header message", "Prompt message header value check failed", Status.FAIL);
			  }
			  return promptHeaderVal;
		  }
		  
		// Get Prompt Body message
		  public String getPromptbodyMessage() {
			  String promptBodyContent="";
			  try {
				  UIHelper.waitForVisibilityOfEleByXpath(driver, promptMessageValXpath);
				  UIHelper.highlightElement(driver, promptMessageValXpath);
				  promptBodyContent=UIHelper.getTextFromWebElement(driver, promptMessageValXpath);
			  }catch(Exception e) {
				  report.updateTestLog("Get Prompt Header message", "Prompt message header value check failed", Status.FAIL);
			  }
			  return promptBodyContent;
		  }	  
		  
		// Get calculated size value from Document Library
		  public String getCalculatedSizeFromDocLib(String folderName) {
			 String calculatedSize="";
			
			 try {
				 String finalCalculatedSizeXpath=tempCalculatedSizeInDocLibXpath.replace("CRAFT", folderName);
				 UIHelper.waitForVisibilityOfEleByXpath(driver, finalCalculatedSizeXpath);
				 UIHelper.highlightElement(driver, finalCalculatedSizeXpath);
				 calculatedSize=UIHelper.getTextFromWebElement(driver, finalCalculatedSizeXpath).trim();
			 }catch(Exception e){
				 report.updateTestLog("Get Calculated size in Document Library", "Calculated Size in document library failed", Status.FAIL);
			 }
			  return calculatedSize;
		  }
		//getting details of the excluded text for folder
			public void verifyexcludedfoldertextavailble(String folderName) {
				String finaexcludedfolderMsgXapth = tempexcludedfolderMsgXapth.replace("CRAFT", folderName);
				String exludedfoldertxt = UIHelper.getTextFromWebElement(driver, finaexcludedfolderMsgXapth);
				try {
					
					
					if (exludedfoldertxt.contentEquals("Folder and all its subfolders and content are excluded from ShareBox sharing.")) {
						UIHelper.highlightElement(driver, promptMessageValXpath);
						report.updateTestLog("Verify excluded folder text" , "Text is displayed", Status.PASS);
					} else {
						report.updateTestLog("Verify excluded folder text", "Text is not displayed", Status.FAIL);
					}

				} catch (Exception e) {
					e.printStackTrace();
					report.updateTestLog("Verify excluded folder text", "Text is not displayed", Status.FAIL);
				}
			}
			public void verifyexcludedfoldertextNOTavailbleNegative(String folderName) {
				String finaexcludedfolderMsgXapth = tempexcludedfolderMsgXapth.replace("CRAFT", folderName);
				try {				
					if (!UIHelper.checkForAnElementbyXpath(driver, finaexcludedfolderMsgXapth)) {
						report.updateTestLog("Verify excluded folder text" , "Text is not displayed", Status.PASS);
					} else {
						report.updateTestLog("Verify excluded folder text", "Text is displayed", Status.FAIL);
					}

				} catch (Exception e) {
					e.printStackTrace();
					report.updateTestLog("Verify excluded folder text", "Text is displayed", Status.FAIL);
				}
			}
			
			
			public void verifyexcludedfiletextavailble(String fileName) {
				String finaexcludedfileMsgXapth = tempexcludedfileMsgXapth.replace("CRAFT", fileName);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finaexcludedfileMsgXapth);
				String excludedfiletxt = UIHelper.getTextFromWebElement(driver, finaexcludedfileMsgXapth);
				try {
					
					
					if (excludedfiletxt.contentEquals("Document is excluded from ShareBox sharing.")) {
						UIHelper.highlightElement(driver, finaexcludedfileMsgXapth);
						report.updateTestLog("Verify excluded file text" , "Text is displayed", Status.PASS);
					} else {
						report.updateTestLog("Verify excluded file text", "Text is not displayed", Status.FAIL);
					}

				} catch (Exception e) {
					e.printStackTrace();
					report.updateTestLog("Verify excluded folder text", "Text is not displayed", Status.FAIL);
				}
				
			}
			public void verifyexcludedfiletexNOTtavailbleNegative(String fileName) {
				String finaexcludedfileMsgXapth = tempexcludedfileMsgXapth.replace("CRAFT", fileName);
				try {				
					if (!UIHelper.checkForAnElementbyXpath(driver, finaexcludedfileMsgXapth)) 
					{
						report.updateTestLog("Verify excluded file text" , "Text is not displayed", Status.PASS);
					} else {
						report.updateTestLog("Verify excluded file text", "Text is displayed", Status.FAIL);
					}

				} catch (Exception e) {
					e.printStackTrace();
					report.updateTestLog("Verify excluded folder text", "Text is displayed", Status.FAIL);
				}
				
			}
			// getting details of the Popup Window
			public void verifytextavailbleinExcludeconfirmationwindow() {
				UIHelper.waitForVisibilityOfEleByXpath(driver, promptHeaderXpath);
				UIHelper.highlightElement(driver, promptHeaderXpath);
				String heardertext = UIHelper.getTextFromWebElement(driver, promptHeaderXpath);
				String messagetext = UIHelper.getTextFromWebElement(driver, promptMessageValXpath);
				try {
					
					if (heardertext.contentEquals("Exclude from ShareBox sharing")) 
					{
						
						
						report.updateTestLog("Verify exclude popup header text" , "Text is displayed", Status.PASS);
					} 
					else if (heardertext.contentEquals("Remove ShareBox exclusion"))
					
					{
						report.updateTestLog("Verify remove exclusion popup header text", "Text is displayed", Status.PASS);
					}
					else 
					{
						
						report.updateTestLog("Verify popup header text", "Text is not displayed", Status.FAIL);
					}
				}
					catch (Exception e) {
					e.printStackTrace();
					report.updateTestLog("Verify exclude popup header text", "Text is not displayed", Status.FAIL);
				}
				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, promptMessageValXpath);
					if (messagetext.contentEquals("Document or folder will be excluded from Sharebox sharing. External users will not have access.")) 
					{
						UIHelper.highlightElement(driver, promptMessageValXpath);
						report.updateTestLog("Verify exclusion popup message text" , "Text is displayed", Status.PASS);
					} else if (messagetext.contentEquals("Document or folder will be accessible to external users again")) {
						report.updateTestLog("Verify remove exclusion popup message text" , "Text is displayed", Status.PASS);
					}
					else 
					{
						report.updateTestLog("Verify message text", "Text is not displayed", Status.FAIL);
					}

				} catch (Exception e) {
					e.printStackTrace();
					report.updateTestLog("Verify message text", "Text is not displayed", Status.FAIL);
				}
			}
			
			// select drop down from all properties page
			  public void editpropertyDpdown(String title, String value) {
				String finalFieldLabelWithValueXpath = editPropDropdownFieldValueXpath
							.replace("FIELD_NAME", title);
				 try {

					 UIHelper.waitForVisibilityOfEleByXpath(driver, finalFieldLabelWithValueXpath);
					 UIHelper.selectbyVisibleText(driver, finalFieldLabelWithValueXpath, value);
					 report.updateTestLog("Select Edit property Drop down", "Property :" + title + "Value : "+ value , Status.PASS);
					
				 }catch(Exception e){
					 report.updateTestLog("Select Edit property Drop down", "Property :" + title + "Value : "+ value +"Failed", Status.FAIL);
				 }
				 
			  }
			  
				
				// Click on reposity link in Copy to repository Popup
				public void clickOnRepositoryButtonInCopyToRepoPopUp() {
					try {
						UIHelper.waitFor(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, copyToRepoLayoutHeaderXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, repositoryLinkXpathInCopyOrMoveToPopup);
						UIHelper.highlightElement(driver, repositoryLinkXpathInCopyOrMoveToPopup);
						UIHelper.click(driver, repositoryLinkXpathInCopyOrMoveToPopup);
						report.updateTestLog("Click on Repositry button in Copy To Repo Prompt", "Click on Repositry button successfull", Status.DONE);
					}catch(Exception e) {
						report.updateTestLog("Click on Repositry button in Copy To Repo Prompt", "Click on Repositry button failed", Status.FAIL);
					}
				}
				
				// Select the repositry to copy in the Copy to Repo popUp
				public void selectRepositryUnderPathInCopyToRepoPopUp(String repositryName) {
					String finalSelectRepositryXpath=tempSelectRepositryXpath.replace("CRAFT", repositryName);
					try {
						UIHelper.waitFor(driver);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingImgXpathInRepositorySec);
						UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectRepositryXpath);
						UIHelper.click(driver, finalSelectRepositryXpath);
						report.updateTestLog("Select the repositry in Copy To repo prompt", "Selection of repo successfull", Status.DONE);
					}catch(Exception e) {
						report.updateTestLog("Select the repositry in Copy To repo prompt", "Selection of repo failed", Status.FAIL);
					}
					
				}
				
				// Select Site or folder under the selected repositry in Copy to repo popUp
				public void selectSiteOrFolderUnderSelectedRepositryInCopyToRepoPopUp(String siteOrFolderName) {
					String finalSelectSiteOrFolderUnderSelectedRepositryXpath=tempSelectSiteOrFolderUnderSelectedRepositryXpath.replace("CRAFT", siteOrFolderName);
					try {
						UIHelper.waitFor(driver);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingImgXpathInRepositorySec);
						UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectSiteOrFolderUnderSelectedRepositryXpath);
						UIHelper.click(driver, finalSelectSiteOrFolderUnderSelectedRepositryXpath);
						report.updateTestLog("Select site or folder under selected repositry in Copy To repo prompt", "Selection successfull", Status.DONE);
					}catch(Exception e) {
						report.updateTestLog("Select site or folder under selected repositry in Copy To repo prompt", "Selection failed", Status.FAIL);
					}
					
				}
				
				// Click on copy button in Copy To repo prompt
				public void clickOnCopyButtonInCopyToRepoPopUp() {
					try {
						UIHelper.waitFor(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, copyButtonInCopyToRepoPromptXpath);
						UIHelper.highlightElement(driver, copyButtonInCopyToRepoPromptXpath);
						UIHelper.click(driver, copyButtonInCopyToRepoPromptXpath);
						report.updateTestLog("Click on Copy button in Copy To Repo Prompt", "Click on Repositry button successfull", Status.DONE);
					}catch(Exception e) {
						report.updateTestLog("Click on Copy button in Copy To Repo Prompt", "Click on Repositry button failed", Status.FAIL);
					}
				}
				
				// Get the loading text
				public  String getLoadingText() {
					try {
						UIHelper.waitForVisibilityOfEleByXpath(driver, loadingMessageTextXpath);
						messageText = UIHelper.getTextFromWebElement(driver, loadingMessageTextXpath);
					} catch (Exception e) {
						e.printStackTrace();
					}

					return messageText;
					
				}
				
				// Added for NALS project
				public boolean isDisplayedPublishedGreenTick(String fileName) {
				try {
				highlightTheDocLibSection(fileName);
				String finalXpathForPublishGreenLink1 = finalXpathForPublishGreenLink;

				// Thread.sleep(400000);
				UIHelper.pageRefresh(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, tempPublishInProgressLinkXpath);

				try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForPublishGreenLink1);
				} catch (Exception e) {
				}

				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForPublishGreenLink1)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpathForPublishGreenLink1));
				UIHelper.highlightElement(driver, finalXpathForPublishGreenLink1);
				isDisplayedPublishLink = true;
				} else {
				isDisplayedPublishLink = false;
				}
				} catch (Exception e) {
				e.printStackTrace();
				}

				return isDisplayedPublishLink;
				}
				
}
