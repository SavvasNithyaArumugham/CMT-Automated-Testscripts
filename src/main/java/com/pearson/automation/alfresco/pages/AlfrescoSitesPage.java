package com.pearson.automation.alfresco.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * Functional Components class
 * 
 * @author Cognizant
 */
public class AlfrescoSitesPage extends ReusableLibrary {

	// Alfresco 5.2
	private String createSitePopupXPathnew = ".//*[@id='CREATE_SITE_DIALOG']";
	private String siteNameFieldXpathnew = ".//*[@id='CREATE_SITE_FIELD_TITLE']//input[@name='title']";
	private String siteDescFieldXpathnew = ".//*[@id='CREATE_SITE_FIELD_DESCRIPTION']//textarea[@name='description']";
	private String siteTypeDropdownXpathnew = ".//*[@name='sitePreset']";
	private String sitevisilibilityXpathnew = ".//*[@id='CREATE_SITE_FIELD_VISIBILITY_CONTROL']//input[@value='CRAFT']";
	private String sitecreateokbtnhnew = ".//*[@id='CREATE_SITE_DIALOG_OK']";
	private String sitenamecheckXpath = ".//img[@class='validationInProgress']";

	private String sitedropdowntable = ".//*[@id='CREATE_SITE_FIELD_PRESET_CONTROL_menu']";
	private String sitedropdownArrow = "//table[contains(@class,'ArrowButton')]//td[contains(@class,'ArrowButton')]/input";
	private String sitetypevalue = ".//*[@id='CREATE_SITE_FIELD_PRESET_CONTROL_menu']//td[text()='CRAFT']";

	public String revertXpath = ".//a[@title='Revert']";
	private String pageTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String siteMenuXpath = ".//*[@id='HEADER_SITES_MENU_text']";
	// ".//*[@id='HEADER_SITES_MENU']";
	private String lodingImgIconXpath = ".//*[contains(@id,'alfresco_header_AlfMenuItem')]//*[contains(@class,'dijitMenuItemIcon alf-loading-icon')]";
	private String sitesArrowMenuXpath = ".//*[@id='HEADER_SITES_MENU']/span[2]";
	private String creatSiteXpath = ".//*[@id='HEADER_SITES_MENU_CREATE_SITE_text']";
	// private String createSitePopupXPath =
	// ".//*[contains(@id,'createSite-instance-dialog_h')]";
	private String createSitePopupXPath = ".//*[@id='CREATE_SITE_DIALOG']";
	// private String siteNameFieldXpath =
	// ".//*[contains(@id,'createSite-instance-title')]";
	private String siteNameFieldXpath = ".//div[@id='CREATE_SITE_FIELD_TITLE']//input[@name='title']";
	// private String siteDescFieldXpath =
	// ".//*[contains(@id,'createSite-instance-description')]";
	private String siteDescFieldXpath = ".//div[@id='CREATE_SITE_FIELD_DESCRIPTION']//textarea[@name='description']";
	// private String siteTypeDropdownXpath =
	// ".//*[contains(@id,'createSite-instance-sitePreset')]";
	private String siteTypeDropdownXpath = ".//table[@id='CREATE_SITE_FIELD_PRESET_CONTROL']//*[@role='option']";
	private String tempsiteTypeDropdownXpath = ".//div[@id='CREATE_SITE_FIELD_PRESET_CONTROL_dropdown']//table[contains(@id,'CREATE_SITE_FIELD_PRESET_CONTROL')]//td[contains(@class,'MenuItemLabel')]";
	private String siteRegionDropdownXpath = ".//*[contains(@id,'createSite-instance-location')]";
	private String tempXpathForSiteVisibility = "//*[contains(@id,'createSite-instance-form')]//label[contains(.,'CRAFT')]//ancestor::div[1]/input";
// revert after EDMS 	private String editOfflineXpath = ".//*[@id='onActionCustomEditOffline']/a/span";
	private String editOfflineXpath = ".//*[@id='onActionEditOffline']/a/span";
	private String okButtonXpath = ".//*[contains(@id,'createSite-instance-ok-button-button')]";
	// private String cancelButtonXpathInCreateSiteDailog =
	// ".//*[contains(@id,'createSite-instance-cancel-button-button')]";
	private String cancelButtonXpathInCreateSiteDailog = ".//*[@id='CREATE_SITE_DIALOG_CANCEL_label']";
	private String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
	private String testOutputFilePath2 = "src/test/resources/AppTestData/TestOutput/SiteDetailsCopy.txt";
	private String testOutputMultipleFilePath = "src/test/resources/AppTestData/TestOutput/MultipleSiteDetails.txt";
	private String mySiteFilesLinkXpath = ".//*[@id='HEADER_SITES_MENU_MY_SITES_text']/a";
	private String sitesSectionXpath = ".//*[@class='sticky-wrapper']";
	private String createdSitesContainerXpath = ".//*[@id='template_x002e_user-sites_x002e_user-sites_x0023_default-body']/div/ul";
	private String tempXpathForSitLinkInMySites = ".//*[contains(@id,'HEADER_SITES_MENU_RECENT')]/a[contains(.,'CRAFT')]";
	private String inviteLinkXpath = ".//*[@class='sticky-wrapper']//a[contains(.,'Add Users') and not(contains(.,'Pending Invites'))]";
	private String searchTextFieldXpath = ".//*[@class='sticky-wrapper']//*[@class='search-text']/input";
//	private String searchButtonXpath = ".//*[@class='sticky-wrapper']//button[contains(.,'Search')]";
	private String searchButtonadduser = "//button[contains(@id,'add-user') and contains(@id,'search-button')]";
	private String searchButtonXpath = ".//div[@class='search-button']//span/button";
	private String tempAddBtnXpath = ".//*[contains(@id,'yuievtautoid')]//tbody/tr/td[contains(.,'CRAFT')]//following-sibling::td//button[contains(.,'Select')]";
	private String tempXpathForUserResultsXpath = ".//*[contains(@id,'yuievtautoid')]//tbody/tr/td[contains(.,'CRAFT')]";
	private String selectRolesToDropdownXpath = ".//*[@class='sticky-wrapper']//button[contains(.,'Set all roles to')]";
	private String tempXpathForSelectRolesToDropdown = ".//*[@class='sticky-wrapper']//*[contains(@id,'default-invitationBar') and contains(@style,'visible')]//li/a[contains(.,'CRAFT')]";
	private String addUserToSiteButtonXpath = ".//*[@class='sticky-wrapper']//button[contains(.,'Add Users')]";
	private String createdSitesListXpathFromMySites = ".//*[@class='sticky-wrapper']//*[@class='viewcolumn']//ul/li/p/a";
	private String searchingXpath = ".//*[@id='message']/div/span";
	private String htmlBodyXpath = ".//*[@id='bd']";
	private String searchResultsXpath = ".//*[contains(@class,'results yui-dt')]";
	private String invitedContainerXpath = ".//*[@class='invitationlistwrapper']";
	private String selectRoleContainerXpathForInvitedUser = ".//*[@class='invitationlist']";
	private ArrayList<String> createdSitesNameList = new ArrayList<String>();
	private String popupMesaageForInvitedUsers = ".//*[@id='message']/div";
	private String confirmCreatedSiteMsgXpath = ".//*[@id='message']/div/span";
	private String documentLibraryXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']/a";
	private String siteDashboardXpath = ".//*[@id='HEADER_SITE_DASHBOARD_text']/a";
//	private String uploadBtnXpath = ".//*[@class='file-upload']//span//button[contains(.,'Upload')]";
	private String uploadBtnXpath = ".//*[@class='file-upload']//span//button";
	private String myDocumentsLableLinkXpath = ".//*[contains(@id,'yui-gen')]//a[contains(.,'Documents')] | .//*[contains(@id,'yui-gen')]//a[contains(.,'My Files')]";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String prooferrmsg = ".//*[@class='message' and contains(text(),'error')]";
	private String createFolderLinkXpath = ".//*[contains(@id,'yui-gen')]/a[contains(.,'Create a folder')]";
	private String allDocumentsLinkXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//span/a[contains(.,'All Documents')]";
	private String lockBannerXpath = ".//*[@id='template_x002e_node-header_x002e_document-details_x0023_default']/div/div[1]/span";
	private String tempXpathForBrowseAction = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'BROWSE_OPTION')]";
	// private String selectedFolderORFileItemXpath =
	// ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]";
	private String selectedFolderORFileItemXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::td";
	// private String tempXpathForMoreOptionLink =
	// ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'More')]";
	private String tempXpathForMoreOptionLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a[@class='show-more']/span";
	private String fileORFolderPropertOtionXpath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'PROPNAME')]";
	//private String tempXpathForViewDetailsLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'View Details')]";
	private String tempXpathForViewDetailsLink =".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//div[@class='link-folder-view-details']//span";
	//private String tempXpathForEditProperties = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Edit Properties')]";
	private String tempXpathForEditProperties =".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//div[@class='document-edit-properties']//span";
	private String cancelBtnXpathInEditProp = ".//*[contains(@id,'default-editDetails-alf-id43-form-cancel-button')]";
	private String tempXpathForDeleteFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Delete Folder')]";
	private String tempXpathForMoveToFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Move to')]";
	private String tempXpathForAttachLifecycle = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Attach Lifecycle')]";
	private String tempXpathForAddRelationship = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Add Relationship')]";
	private String tempXpathForMoreSettings = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'MOREOPT')]";
	private String tempXpathForCopyToFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Copy to')]";
	private String tempXpathForManageAspects = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Manage Aspects')]";
	public String copylink = ".//*[@class='link-info']/input";
	// private String tempSelectFolderChkboxXpath =
	// ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//td[1]//input";
	private String tempSelectFolderChkboxXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::tr//td[1]//*[@type='checkbox']";
	private String movePopUpXpath = "//*[contains(@id,'default-copyMoveTo-dialog_c')]//div[@class='site']";
	private String selectFileToMoveXpath = "//*[contains(@id,'default-copyMoveTo-dialog_c')]//div[@class='site']//a/h4[contains(.,'CRAFT')]";
	private String btnMoveInMovePopUpXpath = "//*[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Move']";
	private String btnCopyInCopyPopUpXpath = "//*[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Copy']";

	private String myFilesLableLinkXpath = ".//*[contains(@id,'yui-gen')]//a[contains(.,'Documents')] | .//*[contains(@id,'yui-gen')]//a[contains(.,'Files')]";
	private String myFilesTablesXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']";
	private String uploadedFilesTitlesXpath = "//*[@class='yui-dt-data']//h3/span/a";

	private String documentLibXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']";
	protected String tempCheckModXpath = ".//*[@class='sitename']/a[text()='CRAFT']//ancestor::tr/td[2]//span";

	private String FilesAndFolderListXpath = "//*[@class='filename']//a";
	protected ArrayList<String> filesAndFoldersList = new ArrayList<String>();
	protected String tempSelectedFileXpath = "//*[@class='filename']//a[text()='CRAFT']";
	private String cancelEditXpath = ".//*[@id='onActionCancelEditing']/a";
	protected String tempIsLockXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::tr/td//*[contains(@class,'info-banner') and contains(.,'locked')]";
	private String findSiteXpath = ".//*[@id='HEADER_SITES_MENU_SITE_FINDER_text']/a";
	private String siteFindTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String searchBoxXpath = ".//*[@class='search-term']";
	private String siteNameListXpath = ".//*[@class='sitename']/a";
	protected ArrayList<String> siteResultList = new ArrayList<String>();
	private String tempsiteresultXpath = ".//*[@class='sitename']/a[text()='CRAFT']";
	private String siteTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String searchingXapth = ".//*[text()='Searching...']";
	protected String tempFileXpath = "//*[@class='filename']//a[text()='CRAFT']";
	protected String tempCollFileXpath = "//*[@class='filename']//a[contains(text(),'CRAFT')]";

	private String btnAddUserGroupInManagePermissionXpath = "//div[contains(@id,'manage-permissions')]//div[@class='add-user-group']";
	private String txtboxSearchAddUserGroupInManagePermissionXpath = "//input[contains(@id,'manage-permissions') and @type='text']";
	private String btnSearchAddUserGroupInManagePermissionXpath = "//button[text()='Search']";
	private String searchResultsAddUserGroupInManagePermissionXpath = "//div[contains(@id,'authorityFinder-results')]";
	private String btnAddInAddUserGroupInManagePermissionXpath = "//div[contains(@id,'authorityFinder-results')]//tr/td//h3[contains(.,'CRAFT')]/../../following-sibling::td//button";
	private String btnRoleContributorAddUserGroupInManagePermissionXpath = "//div[contains(@id,'default-directPermissions')]//tbody[2]//td[3]//button";
	private String btnMenuRoleContributorAddUserGroupInManagePermissionXpath = "//div[contains(@id,'default-directPermissions')]//td/div[text()='site_alfresco-sanity_SiteCollaborator']/../following-sibling::td//div[contains(@class,'button-menu')]//li/a[text()='Site Manager']";
	private String tempXpathForSelectUserRole = "//div[contains(@id,'default-directPermissions')]//*[contains(@id,'yui-rec')]/td[3]//span//ul/li/a[contains(.,'CRAFT')]";
	private String btnSaveInManagePermissionXpath = "//button[text()='Save']";
	private String tempXpathForManagePermissionFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Manage Permissions')]";
	private String searchLoadingXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//tbody/tr/td//*[contains(.,'Searching')]";

	private String userRoleDetailsFilePath = "src/test/resources/AppTestData/Sites/UserRoleDetails.txt";

	private String tempRecentSitesXpath = ".//*[contains(@id,'HEADER_SITES_MENU_RECENT')]/a[text()='CRAFT']";

	private String recentSitesList = ".//*[contains(@id,'HEADER_SITES_MENU_RECENT')]/a";

	private String siteSearchInputXpath = ".//*[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-term']";
	private String siteSearchButtonXpath = ".//*[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-button-button']";

	private String finalName;

	private boolean isDisplayedRecentSite;

	private String docTextLayerXpath = ".//*[@class='textLayer']";

	private String publicSiteJoinBtnXpath = ".//*[@class='sitename']/a[text()='CRAFT']//ancestor::tr/td[3]//button[text()='Join']";

	private String siteMembersXpath = ".//*[@id='HEADER_SITE_MEMBERS_text']/a";
	private String searchBtnXpath = ".//button[contains(text(),'Search')]";

	private String memberRoleBtnXpath = "//tbody[@class='yui-dt-data']/tr/td[3]/div/span//button";
	private String adduser = ".//a[@href='add-users']";
	private boolean isDiaplayedSite;

	private String xpathForManageAspectsLeftPaneInPoup = ".//*[@class='bd']//*[contains(@id,'default-aspects-left')]";
	private String xpathForManageAspectsRightPaneInPoup = ".//*[@class='bd']//*[contains(@id,'default-aspects-right')]";
	private String applyChangesBtnXpath = "//div[contains(@id,'default-aspects-dialog')]//button[text()='Apply changes']";
	private String cancelBtnXpath = "//div[contains(@id,'default-aspects-dialog')]//button[text()='Cancel']";

	private String selectedAspectsList = ".//*[@class='bd']//*[contains(@id,'default-aspects-right')]//table//*[@class='yui-dt-data']/tr/td[2]//h4";

	private String tempXpathForLinkToFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Link To')]";
	private String btnLinkToInLinkPopUpxpath = "//div[contains(@id,'default-copyMoveTo-dialog_c')]//button[text()='Link']";

	private ArrayList<String> selectedAspectsListVal = new ArrayList<String>();
	private String allLinksBtnXpath = "//*[@id='prompt']//button[contains(text(),'All Links')]";
	private boolean isDisplayedAttachLifecycle;
	private boolean isDisplayedMoreSettingsOption;
	private String createRelationBtnXpath = ".//*[@id='addRelationship-create-button']";
	private String relationshipDropdownXpath = ".//*[contains(@id,'addRelationship-dialog_c') and contains(@style,'visible') ]//select[@id='dropdown']";
	private String addRelationshipBtnXpath = ".//*[contains(@id,'addRelationship-dialog_c') and contains(@style,'visible') ]//button[@id='addRelationship-Select-Record']";
	private String tempXpathForselctFolder = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td[2]//h3/a[contains(.,'CRAFT')]";
	private String tempXpathForFile = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td//*[text()='CRAFT']//ancestor::td[1]//following-sibling::td//a[contains(@title,'Add')]";
	private String tempXpathForSite = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td//*[text()='CRAFT']//ancestor::td[1]//following-sibling::td//a[contains(@title,'Add')]";
	private String temprlnXapthForFF = ".//*[@id='relationship-docPicker-cntrl-picker-left']//tr//*[text()='CRAFT']//ancestor::tr//td[3]//a";
	private String loadingXpath = ".//*[@class='yui-dt-message']//tr[1]/td[contains(.,'Loading')]";
	private String okBtnXpathForRelationshipPopup = ".//*[@id='relationship-docPicker-cntrl-ok-button']";
	private String cancelButtonForRelationshipPopup="//*[@id='addRelationship-cancel']//button";
	private String tempXpathForDeleteDocumentLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Delete Document')]";
	private String promptBoxXpath = "//*[@id='prompt']";
	private String thisFileBtnXpath = "//*[@id='prompt']//button[contains(text(),'This File')]";
	//private String selectedItemsXpath = ".//*[@id='yui-main']//*[@id='alf-content']//span/span[contains(.,'Selected Items')]";
	private String selectedItemsXpath ="//div[contains(@class,'selected-items')]//span/button[contains(@id,'selectedItems')]";
	AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
	AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	private boolean isDisplayedAddRelationship;

	private String selectedItemsMenuXpath = ".//*[contains(@id,'default-selectedItems-menu')]//*[@class='bd']";

	private String selectedItemsMenuOptionXpath = ".//*[contains(@id,'default-selectedItems-menu')]//*[contains(@id,'yui-gen')]/a/span";

	private ArrayList<String> selectedItemsMenuValues = new ArrayList<String>();

	private String tempXpathForAttachLifecycleFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Attach Lifecycle')]";
	private String lstLifeCycleDrpDwnXPath = ".//*[@id='attach-lifecycle-dropdown']";
	private String tempXpathForLifeCycAttaLinkInFldr = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]/div/div/span[contains(.,'Lite')]";
	private String lfeCycDrpDwnXPath = ".//*[@id='attach-lifecycle-dropdown']";
	private String btnAttachLifeCycleXPath = ".//*[@id='clickAttach-ok-button']";
	private String tempXpathForDetachLifecycleFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Detach Lifecycle')]";

	private String tempXPathFolderViewDetails = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'View Details')]";
	private String promteLfeCycleXPath = ".//*[@id='clickPromote']/a";
	private String actPrmteValXPath = ".//div[@class='set']/div[5]/div/span[2]";
	private String promteLfeCycleMoreXPath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Promote Lifecycle')]";

	private String prmtLifDrpDwnXPath = ".//div[@class='dialog-body']//select[@id='promote-lifecycle-dropdown']";
	//private String promoteOKbutton = ".//div[@class='bdft']//button[text()='Promote']";
	private String promoteOKbutton = ".//div[@class='bdft']//span[contains(@id,'Promote-ok')]//button";
	private String promoteCancelButton=".//*[contains(@id,'Promote-cancel')]//button";

	private String demoteLfeCycleXPath = ".//*[@id='clickDemote']/a";
	private String demtLifDrpDwnXPath = ".//*[@id='demote-lifecycle-dropdown']";
	private String demoteOKbutton = ".//*[@id='clickDemote-ok-button']";
	private String demoteCancelButton=".//*[contains(@id,'Demote-cancel')]//button";

	private String demoteLfeCycleMoreXPath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Demote Lifecycle')]";
	private String selectedOption;
	private String selectedItemFromMenuOptionXpath = ".//*[contains(@id,'default-selectedItems-menu')]//*[contains(@id,'yui-gen')]/a/span[contains(text(),'CRAFT')]";

	private String[] splittedVisibleOption;

	private String clkDetachLfeCycLnkXPath = ".//*[@id='clickDetach']/a/span";

	private String xpathForCreateSiteLabels = ".//label[contains(text(),'CRAFT')]";
	private String xpathForUserDashboard = ".//*[contains(@id,'yui-gen')]/*[contains(.,'My Sites')]";
	private String userDashCreateSiteXpath = ".//*[contains(@id,'default-createSite-button')]";

	private String xpathForCreateSiteTypeValues = ".//*[contains(@id,'createSite-instance-sitePreset')]//option";
	private String pageBodyXpath = ".//*[@id='bd']";

	private String addRelationWidXPath = ".//*[@id='addNewRelationship']/a";
	private String tempXpathForMoreOptionAddRel = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'MORE_OPT')]";
	private String relationshipImageXpath = ".//*[@id='relationship-function-table']//img[@style='max-width:50px; max-height:50px; width:auto; height:auto;']";

	private String pagesXpath = ".//*[contains(@id,'default-doclistBarBottom')]//span[contains(@class,'yui-pg-pages')]/a";
	private String pagesBoxXpath = ".//*[contains(@id,'default-doclistBarBottom')]//span[contains(@class,'yui-pg-pages')]";
	private String pageNosXpath = ".//*[contains(@id,'default-doclistBarBottom')]//*[contains(@id,'default-paginatorBottom')]/span[contains(@class,'yui-pg-current')]";

	private String tempXpathForPages = ".//*[contains(@id,'default-doclistBarBottom')]//span[contains(@class,'yui-pg-pages')]/a[contains(.,'CRAFT')]";
	private String nextPageLink = ".//*[contains(@id,'next-link')]";

	private String customizeSiteDiscussionsMenuXpath = ".//*[@id='HEADER_SITE_DISCUSSIONS-TOPICLIST_text']/a";
	private String customizeSiteWikiPageMenuXpath = ".//*[@id='HEADER_SITE_WIKI-PAGE_text']/a";
	private String customizeSiteBlogMenuXpath = ".//*[@id='HEADER_SITE_BLOG-POSTLIST_text']/a";
	private String customizeSiteCalendarMenuXpath = ".//*[@id='HEADER_SITE_CALENDAR_text']/a";
	private String customizeSiteMoreOptionMenuXpath = ".//*[@id='HEADER_SITE_MORE_PAGES']";

	private String siteInSiteFinder = "//*[@class='sitename']/a";
	private String requestToJoinBtn = "//button[contains(.,'Join')]";
	private String sitefinderoption = ".//*[@class='sitename']/a[text()='CRAFT']//ancestor::tr//td[3]//button[contains(text(),'REPLACE')]";

	private String sortButtonXpath = ".//*[contains(@id,'default-sortField-button-button')]";
	private String modifierXpath = ".//*[@class='sort-field']//ul/li/a[contains(.,'Modifier')]";
	private String createSiteArchiveOptXpath = ".//*[contains(@id,'createSite-instance-isArchive')]";

	private String tempFileListXpath = ".//*[@class='yui-dt-data']/tr/td//h3/span/a";

	private String messageXpath = ".//*[@id='message']/div";

	ArrayList<String> listOfCreateSitTypeValues = new ArrayList<String>();

	private ArrayList<String> listOfCreateSitFieldLabels = new ArrayList<String>();

	private boolean isDisplayedSiteNameInSFResults;

	private String fileLockBanner = "//*[@class='info-banner']";

	private String tempXpathForStrtWflwFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//a/span[contains(.,'Start Workflow')]";
	private boolean isDisplayedBrowseOption;

	private String mySiteXPath = ".//*[@id='HEADER_SITES_MENU_MY_SITES_text']/a";
	private String mySitePgXPath = ".//*[@class='header-bar'][text()='Sites']";
	private String btnDisableSiteFeedXPath = ".//button[@title='Disable Activity Feeds and Email Notifications for site CRAFT'][@name='disable']";
	private String btnEnableSiteFeedXPath = ".//button[@title='Enable Activity Feeds and Email Notifications for site CRAFT'][@name='enable']";

	public int mySitesCount;
	// EPS
	private String publishedURLXpath = ".//*[@class='viewmode-field']";
	private String publishedURLValXpath = ".//*[@name='prop_sys_node-uuid'][2]";

	private String tempUserRolesDropDownXpath = ".//*[@class='sticky-wrapper']//button[contains(.,'CRAFT')]";
	private String tempUserRoleXpath = ".//*[@class='sticky-wrapper']//li/a[contains(.,'CRAFT')]";

	private String currentPageNatorXpath = ".//*[contains(@id,'default-paginator')]/span[@class='yui-pg-current']";
	private String nextPageLinkXpath = ".//*[contains(@id,'default-paginator')]/a[contains(@title,'Next Page')]";
	private String docLibPageCountMessage = "START - END of TOTAL_ITEMS";
	private String tempXpathForPageNatorMsg = ".//*[contains(@id,'default-paginator')]/span[contains(@class,'yui-pg-current') and contains(.,'CRAFT')]";

	// Verify site member
	private String siteMembersListXpath = ".//*[contains(@class,'results')]//table//tr//a[contains(.,'USER')]//ancestor::td//following-sibling::td//*[contains(@class,'overflow') and contains(.,'ROLE')]";

	private String tempXpathForAddedUser = ".//*[@class='body added-users-list-body']//table//*[@class='yui-dt-data']/tr/td//h3[contains(.,'CRAFT')]";
	private String lodingXpathForDocLib = ".//*[@class='yui-dt-message']/tr/td/*[contains(.,'Loading the Document Library')]";
	private String loadingXpathInSiteMenu = ".//*[contains(@class,'alf-dropdown-menu')]/*[@class='dijitReset']/tr/td[contains(.,'Loading')]";
	public String pageheader = ".//*[@class='node-info']/h1";
	private String tempXpathForSelectRole = ".//*[@class='sticky-wrapper']//td[contains(.,'CRAFT')]//following-sibling::*//button[contains(.,'Select Role')]";
	private String tempXpathForRoleDropdownOption = ".//*[@class='sticky-wrapper']//*[contains(@id,'default-inviteelist')]//*[contains(@style,'visibility: visible')]//li/a[contains(.,'CRAFT')]";

	private String calendarBtnXpath = ".//*[@id='HEADER_SITE_CALENDAR_text']";
	private String addEventBtnXpath = ".//*[@type='button' and contains(.,'Add Event')]";
	private String newPageBtnXpath = ".//*[@type='button' and contains(.,'New Page')]";
	private String newTopicBtnXpath = ".//*[@type='button' and contains(.,'New Topic')]";
	private String newPostBtnXpath = ".//*[@type='button' and contains(.,'New Post')]";
	private String newLinkBtnXpath = ".//*[@type='button' and contains(.,'New Link')]";
	private String wikiBtnXpath = ".//*[@id='HEADER_SITE_WIKI-PAGE_text']";
	private String discussionBtnXpath = ".//*[@id='HEADER_SITE_DISCUSSIONS-TOPICLIST_text']";
	private String blogBtnXpath = ".//*[@id='HEADER_SITE_BLOG-POSTLIST_text']";
	private String LinkBtnXpath = ".//*[@id='HEADER_SITE_LINKS_text']";

	private String tempXpathForMoreOptSection = ".//*[@class='filename']//ancestor::tr//*[contains(@class,'col-actions')]";
	private String tempXpathForMoreOpt = ".//*[@class='filename' and contains(.,'CRAFT')]//ancestor::td//following-sibling::td//*[@class='action-set detailed']";
	private String tempXpathForDownloadFrmVersionHistory = ".//*[@class='document-version' and contains(.,'CRAFT')]//ancestor::tr//*[@class='download']";
	private String versionHistoryXpath = ".//*[contains(@class,'document-versions') and contains(.,'Version History')]";
	private String filePreviewXpath = ".//*[@class='web-preview']";
	private String managerRoleBtnXpath = ".//*[contains(@class,'button-menu')]//li//a[contains(.,'Manager')]";
	private String RoleBtnXpath = ".//*[contains(@class,'button-menu')]//li//a[contains(.,'CRAFT')]";

	private String optionValXpath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//*[contains(@class,'more-actions')]//a/span";
	List<String> alloptionlist = new ArrayList<String>();
	ArrayList<String> moreOptionsList = new ArrayList<String>();
	ArrayList<String> moreOptions = new ArrayList<String>();
	private String tempXpathForRoleBtn = ".//*[contains(@class,'menu-button')]//button";
	private String tempRoleBtnXpath = ".//*[contains(@id,'default-directPermissions')]//td[contains(@class,'displayName')]//div[contains(text(),'REPLACE')]//ancestor::tr//td//button";
	public String Workflowicon = ".//*[@title='Start Workflow']";
	private String tempXpathForMoreOptions = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//*[contains(@class,'more-actions')]";
	private String tempChangeToXpath = ".//*[contains(@id,'roleSelector')]//li//a[text()='CRAFT']";
	private String memberuser = ".//h3/a[contains(text(),'CRAFT')]//ancestor::tr//td[contains(@headers,'role')]//button";
	private String removeCurrentSiteFromFavouriteXpath = ".//*[@id='HEADER_SITES_MENU_REMOVE_FAVOURITE_text']";

	private String listOfSitesXpath = "//*[@class='yui-dt-data']/tr/td//h3/a";
	private String listOfSitesIconXPath = "//*[@class='yui-dt-data']/tr/td//img[contains(@src,'site-32.png')][1]";
	private String listOfFileFolderXpath = "//*[@class='yui-dt-data']/tr//img[contains(@src,'file-32.png') or contains(@src,'folder-32.png')]";

	private String docActionTwisterCloseXpath = ".//*[contains(@class,'document-actions document-details-panel') or contains(@class,'folder-actions folder-details-panel')]/*[contains(@class,'alfresco-twister-closed')]";
	private String docActionTwisterOpenXpath = ".//*[contains(@class,'document-actions document-details-panel') or contains(@class,'folder-actions folder-details-panel')]/*[contains(@class,'alfresco-twister-open')]";

	private String createProofHQPopupHeaderXpath = ".//*[contains(@id,'default-createProof-form-container_h')]";
	private String proofHQRecipientsFieldXpath = ".//*[contains(@id,'default-createProof-form-container') and contains(@style,'visible')]//*[contains(@id,'default-createProof_proofhqCreateProofRecipients')]/ul";
	private String proofhqRecepientsXpath = ".//*[contains(@id,'default-createProof-form-container') and contains(@style,'visible')]//*[contains(@id,'default-createProof_proofhqCreateProofRecipients')]//label[contains(.,'Recipients')]//following-sibling::input[contains(@type,'text')]";
	private String selectResultXpathForEmail = ".//*[contains(@id,'select2-result-label')]/span";
	private String proofhqAcesspolicyDropdownXpath = ".//*[contains(@id,'default-createProof-form-container') and contains(@style,'visible')]//*[contains(@id,'default-createProof_proofhqCreateAccessPolicy')]";
	private String proofhqSubmitButton = "//button[contains(@id,'default-createProof-form-submit-button')]";
	private String proofHQStatusIndicatorXpath = ".//*[@class='status']/*[contains(@title,'This file is available on ProofHQ')]";
	private String siteDashboardLinkXpath = ".//*[@id='HEADER_SITE_DASHBOARD']";
	private String tempXpathForSiteNavigationMenuBar = ".//*[@id='HEADER_NAVIGATION_MENU_BAR']//a[contains(.,'CRAFT')]";
	private String userNameFieldXpath = ".//*[@id='UsernameTextBox']";
	public String moreoptionXpath = "//*[contains(text(),'CRAFT')]/following::td[4]//span[contains(text(),'More..')]";
	public String docLibHeader = ".//*[@id='HEADER_SITE_CRAFT' and contains(@class,'_SELECT')]";
	private String siteConfigurationOptionDropdownXpath = "//*[@id='HEADER_SITE_CONFIGURATION_DROPDOWN']";
	private String siteConfigurationOptionsListXpath = ".//*[@id='HEADER_SITE_CONFIGURATION_DROPDOWN_dropdown']//table//tr";
	private String inheritedButtonXpath = ".//span[contains(@id,'inheritedButton')]//button[contains(@id,'inheritedButton')]";
	private String turnOffPermissionXpath = ".//div[contains(text(),'Turn off Permission')]";
	private String clickYesOnTurnoffXpath = ".//div[contains(@class,'simple-dialog shadow')]//button[text()='Yes']";
	private String tempXpathForlifeCycleLabelXPath = "//div[@id='clickAttach-dialog']//div[@id='clickAttach-title']";
	private String tempXpathForlifeCycleSelectdrpdwnLabelXpath = "//div[@id='clickAttach-dialog']//div[@class='yui-g']//label";
	private String btnCancelLifeCycleXPath = ".//*[contains(@id,'cancel-button')]";
	
	private String tempXpathForPromLifeCycLabel=".//div[@id='clickPromote-dialog']//div[@id='clickPromote-title']";
	private String tempXpathForPromoteLables=".//div[@id='clickPromote-dialog']//label";
	
	private String tempXpathForDemtLifeCycLabel=".//div[@id='clickDemote-dialog']//div[@id='clickDemote-title']";
	private String tempXpathForDemtLabels=".//div[@id='clickDemote-dialog']//label";
	
	private String navigatorButtonInSelectPromptForNewRelationshipXpath=".//*[@id='relationship-docPicker-cntrl-picker-navigator-button']";
	private String tempNavigatorItemNameXpath=".//*[@id='relationship-docPicker-cntrl-picker-navigatorItems']//*[text()='CRAFT']";
	private String tempLoadingtext=".//*[@id='relationship-docPicker-cntrl-picker-left']//*[contains(@class,'yui-dt-liner') and contains(text(),'CRAFT')]";
	private String tempSiteorFolderToSelect=".//*[@id='relationship-docPicker-cntrl-picker-left']//a[text()='CRAFT']";
	private String tempPlusButtonInSelectPromptXpath=".//*[@id='relationship-docPicker-cntrl-picker-left']//*[text()='CRAFT']//ancestor::td//following-sibling::td//a";
    
	

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoSitesPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Navigate to Sites page
	public AlfrescoSitesPage navigateToSitesTab() {
		try {
			WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
			UIHelper.mouseOveranElement(driver, siteMenuEle);
			UIHelper.highlightElement(driver, siteMenuEle);
			UIHelper.click(driver, siteMenuXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInSiteMenu);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingImgIconXpath);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, creatSiteXpath);
			} catch (Exception e) {
			}
			UIHelper.waitFor(driver);

			/*
			 * report.updateTestLog("Navigate To Sites Menu Page",
			 * "Navigated to Sites Menu Page successfully" +
			 * "<br /><b> Page Title : </b>" + "Sites Menu", Status.DONE);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoSitesPage(scriptHelper);
	}

	// Get Recent Sites
	public ArrayList<String> getRecentSites() {
		ArrayList<String> recentSiteNamesList = new ArrayList<String>();
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteFilesLinkXpath);
			UIHelper.waitFor(driver);
			List<WebElement> recentSiteElementsList = driver.findElements(By.xpath(recentSitesList));

			for (WebElement recentSiteEle : recentSiteElementsList) {
				recentSiteNamesList.add(recentSiteEle.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recentSiteNamesList;

	}

	// 1. Open Site from Recently Opend Sites List OR
	// If site not found in Recent List, perform 'Site Finder' and open site OR
	// If Site not found using 'Site Finder', create new site
	public AlfrescoSitesPage openSiteFromRecentSites(String siteName) {
		try {
			homePageObj.navigateToSitesTab();
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteFilesLinkXpath);
			UIHelper.waitFor(driver);
			List<WebElement> recentSiteElementsList = driver.findElements(By.xpath(recentSitesList));

			for (WebElement recentSiteEle : recentSiteElementsList) {
				if (recentSiteEle.getText().equalsIgnoreCase(siteName)) {
					recentSiteEle.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.pageRefresh(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
					UIHelper.waitForPageToLoad(driver);
					isDisplayedRecentSite = true;

					report.updateTestLog("Enter into Site", "Enter into site using <br/><b>Site Name:</b> " + siteName,
							Status.DONE);

					break;
				} else {
					isDisplayedRecentSite = false;
				}
			}

			if (!isDisplayedRecentSite) {
				isDisplayedRecentSite = checkGivenSiteUsingSiteFinder(siteName);
			}

			if (!isDisplayedRecentSite) {
				navigateToSitesTab();
				createSite(siteName, "No");
				/*
				 * navigateToSitesTab(); openRecentSite(siteName);
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoSitesPage(scriptHelper);
	}

	public boolean checkGivenSiteIsCreatedOrNot(String siteName) {
		boolean isDiaplayedSiteName = false;
		try {
			navigateToSitesTab();
			enterIntoMySitesPage();

			List<WebElement> createdSitesEleList = driver.findElements(By.xpath(createdSitesListXpathFromMySites));

			for (WebElement ele : createdSitesEleList) {
				if (ele.getText().equalsIgnoreCase(siteName)) {
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);

					isDiaplayedSiteName = true;

					report.updateTestLog("Enter into Site", "Enter into site using <br/><b>Site Name:</b> " + siteName,
							Status.DONE);
					break;
				} else {
					isDiaplayedSiteName = false;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDiaplayedSiteName;
	}

	public boolean checkGivenSiteUsingSiteFinder(String siteName) {
		boolean isDiaplayedOrAvailableSite = false;
		try {
			isDiaplayedOrAvailableSite = isDisplayedSiteNameInSiteFinderResults(siteName);

			if (isDiaplayedOrAvailableSite) {
				UIHelper.waitFor(driver);
				String finalsiteresultXpath = tempsiteresultXpath.replace("CRAFT", siteName);
				WebElement finalSiteResultEle = UIHelper.findAnElementbyXpath(driver, finalsiteresultXpath);
				UIHelper.highlightElement(driver, finalSiteResultEle);
				/*
				 * UIHelper.iterateListandhighlightele(driver, siteResultList,
				 * siteName, finalsiteresultXpath);
				 */
				UIHelper.click(driver, finalsiteresultXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, siteTitleXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Enter into Site", "Enter into site using <br/><b>Site Name:</b> " + siteName,
						Status.DONE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDiaplayedOrAvailableSite;
	}

	public void openRecentSite(String siteName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteFilesLinkXpath);
			UIHelper.waitFor(driver);
			List<WebElement> recentSiteElementsList = driver.findElements(By.xpath(recentSitesList));

			for (WebElement recentSiteEle : recentSiteElementsList) {
				if (recentSiteEle.getText().equalsIgnoreCase(siteName)) {
					recentSiteEle.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);

					report.updateTestLog("Enter into Site", "Enter into site using <br/><b>Site Name:</b> " + siteName,
							Status.DONE);
				}
			}
		} catch (Exception e) {
		}
	}

	// Create a Site
	public void enterSiteDetails(String siteName, String isReqWithTimeStamp) {
		try {
			String siteDesc;
			if (isReqWithTimeStamp.equalsIgnoreCase("Yes")) {

				siteDesc = "Cr3at3Rand0m$it3";

			} else {
				siteDesc = dataTable.getData("Sites", "SiteDescription");
			}

			String siteType = dataTable.getData("Sites", "SiteType");
			String siteVisibleOption = dataTable.getData("Sites", "SiteVisibilityFor");
			UIHelper.waitForVisibilityOfEleByXpath(driver, creatSiteXpath);
			WebElement createSiteLinkEle = UIHelper.findAnElementbyXpath(driver, creatSiteXpath);
			UIHelper.highlightElement(driver, createSiteLinkEle);

			if (UIHelper.checkForAnWebElement(createSiteLinkEle)) {

				DateFormat dateFormat = new SimpleDateFormat("dMMyyHHmmss");
				Date date = new Date();
				String random = dateFormat.format(date);

				if (isReqWithTimeStamp.equalsIgnoreCase("Yes")) {
					finalName = siteName + random;

				} else {
					finalName = siteName;
				}
				new FileUtil().writeTextToFile(finalName, testOutputFilePath);

				UIHelper.click(driver, creatSiteXpath);
				/*
				 * UIHelper.waitForVisibilityOfEleByXpath(driver,
				 * createSitePopupXPath);
				 * UIHelper.waitForVisibilityOfEleByXpath(driver,
				 * siteNameFieldXpath);
				 * UIHelper.sendKeysToAnElementByXpath(driver,
				 * siteNameFieldXpath, finalName);
				 * UIHelper.sendKeysToAnElementByXpath(driver,
				 * siteDescFieldXpath, siteDesc);
				 * 
				 * UIHelper.waitFor(driver);
				 */

				UIHelper.waitForVisibilityOfEleByXpath(driver, createSitePopupXPathnew);
				UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameFieldXpathnew);
				UIHelper.sendKeysToAnElementByXpath(driver, siteNameFieldXpathnew, finalName);
				UIHelper.sendKeysToAnElementByXpath(driver, siteDescFieldXpathnew, siteDesc);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, sitenamecheckXpath);

				UIHelper.waitFor(driver);

				splittedVisibleOption = siteVisibleOption.split(";");

				if (splittedVisibleOption[0].toUpperCase().equalsIgnoreCase("Public")) {

				} else {
					String finalXpathForSiteVisibility = sitevisilibilityXpathnew.replace("CRAFT",
							splittedVisibleOption[0].toUpperCase());
					UIHelper.highlightElement(driver, ".//*[@id='CREATE_SITE_FIELD_VISIBILITY_CONTROL']");
					// UIHelper.waitForVisibilityOfEleByXpath(driver,
					// finalXpathForSiteVisibility);
					UIHelper.highlightElement(driver, finalXpathForSiteVisibility);

					UIHelper.click(driver, finalXpathForSiteVisibility);
					/*
					 * UIHelper.mouseOverandElementdoubleClick(driver,UIHelper.
					 * findAnElementbyXpath(driver, finalXpathForSiteVisibility)
					 * ); JavascriptExecutor executor = (JavascriptExecutor)
					 * driver; executor.executeScript("arguments[0].click();",
					 * finalXpathForSiteVisibility);
					 */
					UIHelper.waitFor(driver);
				}

				/*
				 * String finalsitevisilibilityXpathnew =
				 * sitevisilibilityXpathnew.replace("CRAFT", visibility);
				 * UIHelper.highlightElement(driver,
				 * finalsitevisilibilityXpathnew); UIHelper.click(driver,
				 * finalsitevisilibilityXpathnew);
				 */
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, sitenamecheckXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				WebElement typDropdownEle = UIHelper.findAnElementbyXpath(driver, siteTypeDropdownXpath);
				UIHelper.highlightElement(driver, typDropdownEle);
				UIHelper.click(driver, siteTypeDropdownXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, sitedropdowntable);
				String finalsitetypevalue = sitetypevalue.replace("CRAFT", siteType);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalsitetypevalue);
				// UIHelper.selectbyVisibleText(driver, siteTypeDropdownXpath,
				// siteType);
				UIHelper.highlightElement(driver, finalsitetypevalue);
				UIHelper.click(driver, finalsitetypevalue);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, sitecreateokbtnhnew);
				UIHelper.click(driver, sitecreateokbtnhnew);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, confirmCreatedSiteMsgXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, sitecreateokbtnhnew);

				/*
				 * WebElement typDropdownEle =
				 * UIHelper.findAnElementbyXpath(driver, siteTypeDropdownXpath);
				 * UIHelper.highlightElement(driver, typDropdownEle);
				 * UIHelper.click(driver, siteTypeDropdownXpath); if
				 * (siteType.equalsIgnoreCase("Program Component Site")) {
				 * UIHelper.waitFor(driver); UIHelper.waitFor(driver);
				 * UIHelper.waitFor(driver); UIHelper.waitFor(driver); } else {
				 * UIHelper.waitFor(driver); }
				 * UIHelper.selectbyVisibleText(driver, siteTypeDropdownXpath,
				 * siteType);
				 * 
				 * selectSiteVisibileOptAndSubmitSiteDetails(siteVisibleOption,
				 * finalName);
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectSiteVisibileOptAndSubmitSiteDetails(String siteVisibleOption, String finalName) {
		try {
			if (siteVisibleOption.contains(";") && siteVisibleOption.contains("Public")) {
				splittedVisibleOption = siteVisibleOption.split(";");
				String finalXpathForSiteVisibility = tempXpathForSiteVisibility.replace("CRAFT",
						splittedVisibleOption[0]);
				UIHelper.click(driver, finalXpathForSiteVisibility);

				if (splittedVisibleOption != null && splittedVisibleOption.length > 1
						&& splittedVisibleOption[1].contains(":") && splittedVisibleOption[1].contains("Yes")) {
					String storeVisibleOptions[] = splittedVisibleOption[1].split(":");
					String moderatedCheckboxXpathForSiteVisibility = tempXpathForSiteVisibility.replace("CRAFT",
							storeVisibleOptions[1]);
					UIHelper.click(driver, moderatedCheckboxXpathForSiteVisibility);
				}
			} else {
				String finalXpathForSiteVisibility = tempXpathForSiteVisibility.replace("CRAFT", siteVisibleOption);
				UIHelper.click(driver, finalXpathForSiteVisibility);
			}

			selectRegionInCreateSiteWindow();

			UIHelper.click(driver, okButtonXpath);
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * confirmCreatedSiteMsgXpath);
			 */
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, confirmCreatedSiteMsgXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, confirmCreatedSiteMsgXpath);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, siteMenuXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Create Site", "Created site using " + "<br/><b>SiteName:</b>" + finalName,
					Status.DONE);

			splittedVisibleOption = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createMultipleSitesWithTimeStamp(String siteDetails) {
		try {
			commonMethodForCreateMultipleSites(siteDetails, "Yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createMultipleSitesWithoutTimeStamp(String siteDetails) {
		try {
			commonMethodForCreateMultipleSites(siteDetails, "No");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commonMethodForCreateMultipleSites(String createSiteDetails, String isReqWithTimeStamp) {
		try {
			if (createSiteDetails.contains(";")) {
				String splittedSiteDetailas[] = createSiteDetails.split(";");
				for (String siteValues : splittedSiteDetailas) {
					String splittedSiteValues[] = siteValues.split(",");

					String siteName = splittedSiteValues[0].replace("Name-", "");
					String siteDesc = splittedSiteValues[1].replace("Description-", "");
					String siteType = splittedSiteValues[2].replace("Type-", "");
					String siteVisibleOption = splittedSiteValues[3].replace("Visibility-", "");

					createMultipleSites(isReqWithTimeStamp, siteName, siteDesc, siteType, siteVisibleOption);
				}
			} else {
				String splittedSiteValues[] = createSiteDetails.split(",");

				String siteName = splittedSiteValues[0].replace("Name-", "");
				String siteDesc = splittedSiteValues[1].replace("Description-", "");
				String siteType = splittedSiteValues[2].replace("Type-", "");
				String siteVisibleOption = splittedSiteValues[3].replace("Visibility-", "");

				createMultipleSites(isReqWithTimeStamp, siteName, siteDesc, siteType, siteVisibleOption);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createMultipleSites(String isReqWithTimeStamp, String siteName, String siteDesc, String siteType,
			String siteVisibleOption) {
		try {

			if (isReqWithTimeStamp.equalsIgnoreCase("Yes")) {

				navigateToSitesTab();

				WebElement createSiteLinkEle = UIHelper.findAnElementbyXpath(driver, creatSiteXpath);

				if (UIHelper.checkForAnWebElement(createSiteLinkEle)) {

					UIHelper.highlightElement(driver, createSiteLinkEle);
					fillCreateSiteFields(isReqWithTimeStamp, siteName, siteDesc, siteType, siteVisibleOption);

				}
			} else {
				boolean isDisplayedRecentSiteNameVal = false;
				navigateToSitesTab();

				UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteFilesLinkXpath);
				UIHelper.waitFor(driver);
				List<WebElement> recentSiteElementsList = driver.findElements(By.xpath(recentSitesList));

				for (WebElement recentSiteEle : recentSiteElementsList) {
					if (recentSiteEle.getText().equalsIgnoreCase(siteName)) {
						recentSiteEle.click();
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.pageRefresh(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
						UIHelper.waitForPageToLoad(driver);
						isDisplayedRecentSiteNameVal = true;

						report.updateTestLog("Enter into Site",
								"Enter into site using <br/><b>Site Name:</b> " + siteName, Status.DONE);

						break;
					} else {
						isDisplayedRecentSiteNameVal = false;
					}
				}

				if (!isDisplayedRecentSiteNameVal) {
					isDisplayedRecentSiteNameVal = checkGivenSiteUsingSiteFinder(siteName);
				}

				if (!isDisplayedRecentSiteNameVal) {

					try {
						driver.navigate().back();
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitFor(driver);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, siteDashboardLinkXpath);
					} catch (Exception e) {
					}

					navigateToSitesTab();

					WebElement createSiteLinkEle = UIHelper.findAnElementbyXpath(driver, creatSiteXpath);

					if (UIHelper.checkForAnWebElement(createSiteLinkEle)) {

						UIHelper.highlightElement(driver, createSiteLinkEle);
						fillCreateSiteFields(isReqWithTimeStamp, siteName, siteDesc, siteType, siteVisibleOption);

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillCreateSiteFields(String isReqWithTimeStamp, String siteName, String siteDesc, String siteType,
			String siteVisibleOption) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dMMyyHHmmss");
			Date date = new Date();
			String random = dateFormat.format(date);

			if (isReqWithTimeStamp.equalsIgnoreCase("Yes")) {
				finalName = siteName + random;

			} else {
				finalName = siteName;
			}

			new FileUtil().writeTextToFile(finalName, testOutputFilePath);
			new FileUtil().appendTextToFile(finalName, testOutputMultipleFilePath);

			UIHelper.click(driver, creatSiteXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createSitePopupXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameFieldXpath);

			report.updateTestLog("Click on 'Create Site' Link Option",
					"Clicked the 'Create Site' link option from Sites dropdown on menu bar and Create Site Popup window displayed",
					Status.DONE);

			UIHelper.sendKeysToAnElementByXpath(driver, siteNameFieldXpath, finalName);
			UIHelper.sendKeysToAnElementByXpath(driver, siteDescFieldXpath, siteDesc);
			UIHelper.waitFor(driver);
			WebElement typDropdownEle = UIHelper.findAnElementbyXpath(driver, siteTypeDropdownXpath);
			UIHelper.highlightElement(driver, typDropdownEle);
			UIHelper.click(driver, siteTypeDropdownXpath);

			if (siteType.equalsIgnoreCase("Program Component Site")) {
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
			} else {
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
			}

			UIHelper.selectbyVisibleText(driver, siteTypeDropdownXpath, siteType);

			selectSiteVisibilityOptionAndSubmitSiteDetails(siteVisibleOption, finalName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectSiteVisibilityOptionAndSubmitSiteDetails(String siteVisibleOption, String finalName) {
		try {
			if (siteVisibleOption.contains("_") && siteVisibleOption.contains("Public")) {
				splittedVisibleOption = siteVisibleOption.split("_");
				String finalXpathForSiteVisibility = tempXpathForSiteVisibility.replace("CRAFT",
						splittedVisibleOption[0]);
				UIHelper.click(driver, finalXpathForSiteVisibility);

				if (splittedVisibleOption != null && splittedVisibleOption.length > 1
						&& splittedVisibleOption[1].contains(":") && splittedVisibleOption[1].contains("Yes")) {
					String storeVisibleOptions[] = splittedVisibleOption[1].split(":");
					String moderatedCheckboxXpathForSiteVisibility = tempXpathForSiteVisibility.replace("CRAFT",
							storeVisibleOptions[1]);
					UIHelper.click(driver, moderatedCheckboxXpathForSiteVisibility);
				}
			} else {
				String finalXpathForSiteVisibility = tempXpathForSiteVisibility.replace("CRAFT", siteVisibleOption);
				UIHelper.click(driver, finalXpathForSiteVisibility);
			}

			selectRegionInCreateSiteWindow();

			UIHelper.click(driver, okButtonXpath);
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * confirmCreatedSiteMsgXpath);
			 */
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, confirmCreatedSiteMsgXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, siteMenuXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Create Site", "Created site using " + "<br/><b>SiteName:</b>" + finalName,
					Status.DONE);

			splittedVisibleOption = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select Region
	public void selectRegionInCreateSiteWindow() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, siteRegionDropdownXpath)) {
				String appURL = properties.getProperty("ApplicationUrl");
				String finalAppURL = "";
				if (appURL.contains("https://")) {
					finalAppURL = appURL.replace("https://", "");
				} else if (appURL.contains("http://")) {
					finalAppURL = appURL.replace("http://", "");
				}
				String splittedAppURL[] = finalAppURL.split(Pattern.quote("."));

				if (splittedAppURL != null) {

					if (splittedAppURL[0].contains("usppe")) {
						/*
						 * String selectDropdownVal = "" +
						 * splittedAppURL[0].charAt(0) +
						 * splittedAppURL[0].charAt(1) + " " +
						 * splittedAppURL[0].charAt(2) +
						 * splittedAppURL[0].charAt(3) +
						 * splittedAppURL[0].charAt(4) + " " +
						 * splittedAppURL[0].charAt(5) +
						 * splittedAppURL[0].charAt(6) +
						 * splittedAppURL[0].charAt(7);
						 * 
						 * String finalRegionDropdownVal = selectDropdownVal
						 * .toUpperCase();
						 */

						UIHelper.selectbyVisibleText(driver, siteRegionDropdownXpath, "US PPE WIP");
						UIHelper.waitFor(driver);
					} else if (splittedAppURL[0].contains("ukppe")) {
						UIHelper.selectbyVisibleText(driver, siteRegionDropdownXpath, "Europe PPE WIP");
					} else if (splittedAppURL[0].contains("apppe")) {
						UIHelper.selectbyVisibleText(driver, siteRegionDropdownXpath, "SG PPE WIP");
					} else if (splittedAppURL[0].contains("qa")) {
						UIHelper.selectbyVisibleText(driver, siteRegionDropdownXpath, "Europe");
					}

					if (splittedAppURL[0].contains("arc")) {
						UIHelper.waitFor(driver);
						if (UIHelper.checkForAnElementbyXpath(driver, createSiteArchiveOptXpath)) {
							UIHelper.click(driver, createSiteArchiveOptXpath);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Site
	public AlfrescoSitesPage createSite(String siteName, String isReqWithTimeStamp) {
		enterSiteDetails(siteName, isReqWithTimeStamp);

		return new AlfrescoSitesPage(scriptHelper);
	}

	// Click on Cancel button in 'Create Site' instance dailog
	public void clickOnCancelBtnInCreateSiteDailog() {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, sitenamecheckXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelButtonXpathInCreateSiteDailog);
			if (UIHelper.checkForAnElementbyXpath(driver, cancelButtonXpathInCreateSiteDailog)) {
				UIHelper.highlightElement(driver, cancelButtonXpathInCreateSiteDailog);
				UIHelper.mouseOverandElementdoubleClick(driver,
						UIHelper.findAnElementbyXpath(driver, cancelButtonXpathInCreateSiteDailog));
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, cancelButtonXpathInCreateSiteDailog);

				report.updateTestLog("Click on 'Cancel' button in 'Create Site' instance dailog",
						"User able to click on 'Cancel' button in 'Create Site' instance dailog", Status.DONE);
			} else {
				report.updateTestLog("Click on 'Cancel' button in 'Create Site' instance dailog",
						"User not able to click on 'Cancel' button in 'Create Site' instance dailog", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on 'Cancel' button in 'Create Site' instance dailog",
					"User not able to click on 'Cancel' button in 'Create Site' instance dailog", Status.FAIL);
		}
	}

	public AlfrescoSitesPage enterIntoMySitesPage() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteFilesLinkXpath);
			UIHelper.click(driver, mySiteFilesLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitesSectionXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createdSitesContainerXpath);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, sitesSectionXpath));
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, createdSitesContainerXpath));
			UIHelper.waitFor(driver);

			report.updateTestLog("Navigate To My Sites Page",
					"Navigated to My Sites Page successfully" + "<br /><b> Page Title : </b>" + "My Sites",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSitesPage(scriptHelper);
	}

	// Check sites in 'My Sites'
	public boolean isDisplayedSitesInMySitesPage() {
		boolean isDisplayedSites = false;
		try {
			List<WebElement> listOfSites = driver.findElements(By.xpath(createdSitesListXpathFromMySites));
			mySitesCount = listOfSites.size();
			for (WebElement ele : listOfSites) {
				if (UIHelper.checkForAnWebElement(ele)) {
					isDisplayedSites = true;
				} else {
					isDisplayedSites = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedSites;
	}

	public AlfrescoSitesPage openSite() {
		try {
			String siteName = dataTable.getData("Sites", "SiteName");

			List<WebElement> createdSitesEleList = driver.findElements(By.xpath(createdSitesListXpathFromMySites));

			for (WebElement ele : createdSitesEleList) {

				if (ele.getText().equalsIgnoreCase(siteName)) {
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
				}
			}

			report.updateTestLog("Enter into Site", "Enter into site using: " + "Site Name = " + siteName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSitesPage(scriptHelper);
	}

	public AlfrescoSitesPage openTargetSite() {
		try {
			String siteName = dataTable.getData("Sites", "TargetSiteName");

			List<WebElement> createdSitesEleList = driver.findElements(By.xpath(createdSitesListXpathFromMySites));

			for (WebElement ele : createdSitesEleList) {

				if (ele.getText().equalsIgnoreCase(siteName)) {
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
				}
			}

			report.updateTestLog("Enter into Target Site",
					"Enter into target site using: " + "Target Site Name = " + siteName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSitesPage(scriptHelper);
	}

	/**
	 * Method for enter into Site's 'Document Library' Page
	 * 
	 * @author 412766
	 * @return
	 */
	public AlfrescoSitesPage enterIntoDocumentLibrary() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
			UIHelper.click(driver, documentLibraryXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);

			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			report.updateTestLog(
					"Navigate to Document Library", "Document Library loaded successfully"
							+ "<br /><b> Page Title : </b>" + UIHelper.getTextFromWebElement(driver, pageTitleXpath),
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate to Document Library", "Failed to load Document Library", Status.FAIL);
			e.printStackTrace();
		}
		return new AlfrescoSitesPage(scriptHelper);
	}

	// J-Method to check user is in document library
	public boolean InDocumentLibrary(String menu, String selection) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);

			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			String doclib = docLibHeader.replace("CRAFT", menu.toUpperCase().replace(" ", "")).replace("_SELECT",
					selection);
			String title = UIHelper.getTextFromWebElement(driver, doclib);
			if ((!selection.isEmpty()) && UIHelper.checkForAnElementbyXpath(driver, doclib)) {
				report.updateTestLog("Verify user is in " + title,
						"User is in " + title + " page and it is selected " + "<br /><b> Page Title : </b>" + title,
						Status.PASS);
				flag = true;
			} else {
				report.updateTestLog("Verify user is in " + title, "User is not in " + title
						+ " page and it is not selected" + "<br /><b> Page Title : </b>" + title, Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify user is in Document Library", "User is not in Document Library", Status.FAIL);
			e.printStackTrace();
		}
		return flag;
	}

	public void waitForInvisiblityOfMsgInDocLib() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public void enterIntoSiteDashboard() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.click(driver, siteDashboardXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog(
					"Navigate to Site Dashboard", "Navigate to Site Dashboard successfully"
							+ "<br /><b> Page Title : </b>" + UIHelper.getTextFromWebElement(driver, pageTitleXpath),
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Enter into document library (No report )
	public AlfrescoSitesPage enterIntoDocumentLibraryWithoutReport() {
		try {
			UIHelper.click(driver, documentLibraryXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);

		} catch (Exception e) {
			report.updateTestLog("Navigate to Document Library", "Failed to load Document Library", Status.FAIL);
			e.printStackTrace();
		}
		return new AlfrescoSitesPage(scriptHelper);
	}

	// Get site name from My Sites Page
	public ArrayList<String> getSitesFromMySites(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteFilesLinkXpath);
			UIHelper.click(driver, mySiteFilesLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitesSectionXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createdSitesContainerXpath);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, sitesSectionXpath));
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, createdSitesContainerXpath));
			UIHelper.waitFor(driver);

			report.updateTestLog("Navigate To My Sites Page",
					"Navigated to My Sites Page successfully" + "<br /><b> Page Title : </b>" + "My Sites",
					Status.DONE);

			List<WebElement> createdSitesEleList = driver.findElements(By.xpath(createdSitesListXpathFromMySites));

			for (WebElement ele : createdSitesEleList) {
				createdSitesNameList.add(ele.getText());
				if (ele.getText().trim().equalsIgnoreCase(fileName)) {
					UIHelper.scrollToAnElement(ele);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createdSitesNameList;
	}

	// Invite user for created sites
	public AlfrescoSitesPage inviteUserForSite() {
		try {

			String siteName = new FileUtil().readDataFromFile(testOutputFilePath);

			/*
			 * String finalsiteresultXpath =
			 * tempsiteresultXpath.replace("CRAFT", siteName);
			 * UIHelper.iterateListandhighlightele(driver, siteResultList,
			 * siteName, finalsiteresultXpath); UIHelper.click(driver,
			 * finalsiteresultXpath);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, siteTitleXpath);
			 */

			performInviteUserToSite(siteName);

		} catch (Exception e) {
		}

		return new AlfrescoSitesPage(scriptHelper);
	}

	/**
	 * @author 412766
	 */
	public void verifyUserRoleIsAvailable(String invitedUserName, String userRole, String dropDownType) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, searchTextFieldXpath);

			UIHelper.sendKeysToAnElementByXpath(driver, searchTextFieldXpath, invitedUserName);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, searchButtonadduser);
			UIHelper.waitFor(driver);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, htmlBodyXpath));
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, searchResultsXpath));
			UIHelper.waitFor(driver);
			String finalAddBtnXpath = tempAddBtnXpath.replace("CRAFT", invitedUserName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalAddBtnXpath);
			if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, finalAddBtnXpath))) {
				UIHelper.click(driver, finalAddBtnXpath);

				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, invitedContainerXpath));
				UIHelper.mouseOveranElement(driver,
						UIHelper.findAnElementbyXpath(driver, selectRoleContainerXpathForInvitedUser));
				String userRolesDropDownXpath = tempUserRolesDropDownXpath.replace("CRAFT", dropDownType);
				UIHelper.waitForVisibilityOfEleByXpath(driver, userRolesDropDownXpath);
				UIHelper.highlightElement(driver, userRolesDropDownXpath);
				UIHelper.click(driver, userRolesDropDownXpath);

				String finalContributorXpath = tempXpathForSelectRolesToDropdown.replace("CRAFT", userRole);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalContributorXpath);

				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalContributorXpath))) {
					UIHelper.highlightElement(driver, finalContributorXpath);

					report.updateTestLog("Verify the User Role is visible",
							"User Role visibled successfully" + "</br><b>User Role Verified:</b> " + userRole,
							Status.PASS);

					UIHelper.click(driver, finalContributorXpath);
					UIHelper.waitFor(driver);
				} else {
					report.updateTestLog("Verify the User Role is visible",
							"User Role not visible" + "</br><b>User Role Verified:</b> " + userRole, Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickInviteUser() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, addUserToSiteButtonXpath);
			UIHelper.click(driver, addUserToSiteButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupMesaageForInvitedUsers);
			report.updateTestLog("Invite the user", "User Invited successfully", Status.PASS);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickInviteLink() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, inviteLinkXpath);
			UIHelper.click(driver, inviteLinkXpath);
			UIHelper.waitForPageToLoad(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void performInviteUserToSite(String siteName) {
		try {
			String invitedUserName = dataTable.getData("Sites", "InviteUserName");
			String role = dataTable.getData("Sites", "Role");

			clickOnAddUserBtn();

			StringTokenizer userNameToken = new StringTokenizer(invitedUserName, ",");
			StringTokenizer roleToken = new StringTokenizer(role, ",");

			while (userNameToken.hasMoreElements()) {
				String userName = userNameToken.nextElement().toString();
				String userRole = roleToken.nextElement().toString();

				String finalXpathForAddedUser = tempXpathForAddedUser.replace("CRAFT", userName);

				UIHelper.sendKeysToAnElementByXpath(driver, searchTextFieldXpath, userName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, searchButtonadduser);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, htmlBodyXpath));
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, searchResultsXpath));
				UIHelper.waitFor(driver);
				String finalAddBtnXpath = tempAddBtnXpath.replace("CRAFT", userName);
				UIHelper.waitFor(driver);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// finalAddBtnXpath);
				if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, finalAddBtnXpath))) {
					UIHelper.click(driver, finalAddBtnXpath);
					UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, invitedContainerXpath));
					UIHelper.mouseOveranElement(driver,
							UIHelper.findAnElementbyXpath(driver, selectRoleContainerXpathForInvitedUser));
					UIHelper.waitForVisibilityOfEleByXpath(driver, selectRolesToDropdownXpath);
					UIHelper.click(driver, selectRolesToDropdownXpath);

					String finalContributorXpath = tempXpathForSelectRolesToDropdown.replace("CRAFT", userRole);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalContributorXpath);
					UIHelper.click(driver, finalContributorXpath);
					UIHelper.waitFor(driver);

					UIHelper.click(driver, addUserToSiteButtonXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, popupMesaageForInvitedUsers);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);

					UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForAddedUser);

					if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAddedUser)) {
						report.updateTestLog("Invite User to Site",
								"Invite User to site using: SiteName = " + siteName + ", " + "Invited User Name = "
										+ userName + ", " + "Role = " + userRole + "<br/><b>Expected Result:</b>Invite:"
										+ userName + "<br><b>Actual Result:</b>Invite:" + userName,
								Status.PASS);
					} else {
						report.updateTestLog("Invite User to Site", "Failed to invite User to a site", Status.FAIL);
					}
				} else {
					report.updateTestLog("Invite User to Site", "User:" + userName + " is already Member of Site",
							Status.PASS);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on 'Add Users' Button
	public void clickOnAddUserBtn() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, inviteLinkXpath);
			UIHelper.click(driver, inviteLinkXpath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, searchTextFieldXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// perform empty user search
	public void performEmptySearch() {
		try {
			String userName = dataTable.getData("Sites", "InviteUserName");

			UIHelper.sendKeysToAnElementByXpath(driver, searchTextFieldXpath, userName);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, searchButtonadduser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check invite external user to site
	public void inviteExternalUserToSite(String siteName) {
		try {
			String invitedUserName = dataTable.getData("Sites", "InviteUserName");
			String role = dataTable.getData("Sites", "Role");

			clickOnAddUserBtn();

			StringTokenizer userNameToken = new StringTokenizer(invitedUserName, ",");
			StringTokenizer roleToken = new StringTokenizer(role, ",");

			while (userNameToken.hasMoreElements()) {
				String userName = userNameToken.nextElement().toString();
				String userRole = roleToken.nextElement().toString();

				UIHelper.sendKeysToAnElementByXpath(driver, searchTextFieldXpath, userName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, searchButtonadduser);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, htmlBodyXpath));
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, searchResultsXpath));
				UIHelper.waitFor(driver);
				String finalAddBtnXpath = tempXpathForUserResultsXpath.replace("CRAFT", userRole);
				UIHelper.waitFor(driver);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// finalAddBtnXpath);

				if (UIHelper.checkForAnElementbyXpath(driver, finalAddBtnXpath)) {
					report.updateTestLog("Invite/Add User to Site",
							"User not able to add External Users to site members" + "<br/><b>Expected Result:</b>"
									+ userRole + "<br><b>Actual Result:</b>" + userRole,
							Status.PASS);
				} else {

					report.updateTestLog("Invite/Add User to Site",
							"Invite/Add User to site using: SiteName = " + siteName + ", "
									+ "User able to add External Users to site members"
									+ "<br/><b>Expected Result:</b>Invite:" + userName
									+ "<br><b>Actual Result:</b>Invite:" + userName,
							Status.FAIL);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add User and set roles parallely to join site
	public void setRolesParallelyToUsersInIviteUseToSte(String siteName) {
		try {
			String invitedUserName = dataTable.getData("Sites", "InviteUserName");
			String role = dataTable.getData("Sites", "Role");

			clickOnAddUserBtn();

			StringTokenizer userNameToken = new StringTokenizer(invitedUserName, ",");

			while (userNameToken.hasMoreElements()) {
				String userName = userNameToken.nextElement().toString();

				UIHelper.sendKeysToAnElementByXpath(driver, searchTextFieldXpath, userName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, searchButtonadduser);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, htmlBodyXpath));
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, searchResultsXpath));
				UIHelper.waitFor(driver);
				String finalAddBtnXpath = tempAddBtnXpath.replace("CRAFT", userName);
				UIHelper.waitFor(driver);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// finalAddBtnXpath);
				if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, finalAddBtnXpath))) {
					UIHelper.click(driver, finalAddBtnXpath);
					UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, invitedContainerXpath));
					UIHelper.mouseOveranElement(driver,
							UIHelper.findAnElementbyXpath(driver, selectRoleContainerXpathForInvitedUser));
				} else {
					report.updateTestLog("Invite User to Site", " User is already Member of Site", Status.PASS);
				}

			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, selectRolesToDropdownXpath);
			UIHelper.click(driver, selectRolesToDropdownXpath);

			String finalContributorXpath = tempXpathForSelectRolesToDropdown.replace("CRAFT", role);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalContributorXpath);
			UIHelper.click(driver, finalContributorXpath);
			UIHelper.waitFor(driver);

			UIHelper.click(driver, addUserToSiteButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupMesaageForInvitedUsers);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);

			StringTokenizer userNameTokenValues = new StringTokenizer(invitedUserName, ",");

			while (userNameTokenValues.hasMoreElements()) {
				String userName = userNameTokenValues.nextElement().toString();

				String finalXpathForAddedUser = tempXpathForAddedUser.replace("CRAFT", userName);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForAddedUser);

				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAddedUser)) {
					report.updateTestLog("Invite User to Site",
							"Invite User to site using: SiteName = " + siteName + ", " + "Invited User Name = "
									+ userName + ", " + "Role = " + role + "<br/><b>Expected Result:</b>Invite:"
									+ userName + "<br><b>Actual Result:</b>Invite:" + userName,
							Status.PASS);
				} else {
					report.updateTestLog("Invite User to Site", "Failed to invite User to a site", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add User and set roles individuall by using 'Select Role' dropdown to
	// join site
	public void setRolesIndividuallyToUsersInIviteUseToSte(String siteName) {
		try {
			String invitedUserName = dataTable.getData("Sites", "InviteUserName");
			String role = dataTable.getData("Sites", "Role");

			clickOnAddUserBtn();

			StringTokenizer userNameToken = new StringTokenizer(invitedUserName, ",");

			while (userNameToken.hasMoreElements()) {
				String userName = userNameToken.nextElement().toString();

				UIHelper.sendKeysToAnElementByXpath(driver, searchTextFieldXpath, userName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, searchButtonXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, htmlBodyXpath));
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, searchResultsXpath));
				UIHelper.waitFor(driver);
				String finalAddBtnXpath = tempAddBtnXpath.replace("CRAFT", userName);
				UIHelper.waitFor(driver);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// finalAddBtnXpath);
				if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, finalAddBtnXpath))) {
					UIHelper.click(driver, finalAddBtnXpath);
					UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, invitedContainerXpath));
					UIHelper.mouseOveranElement(driver,
							UIHelper.findAnElementbyXpath(driver, selectRoleContainerXpathForInvitedUser));
				} else {
					report.updateTestLog("Invite User to Site", " User is already Member of Site", Status.PASS);
				}

			}

			StringTokenizer userNameTokenSValue2 = new StringTokenizer(invitedUserName, ",");
			StringTokenizer roleToken = new StringTokenizer(role, ",");
			while (roleToken.hasMoreElements()) {
				String userRole = roleToken.nextElement().toString();
				String userName = userNameTokenSValue2.nextElement().toString();

				String finalXpathForSelectRole = tempXpathForSelectRole.replace("CRAFT", userName);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSelectRole);
				UIHelper.click(driver, finalXpathForSelectRole);

				String finalRoleOptionXpath = tempXpathForRoleDropdownOption.replace("CRAFT", userRole);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalRoleOptionXpath);
				UIHelper.click(driver, finalRoleOptionXpath);
				UIHelper.waitFor(driver);
			}

			UIHelper.click(driver, addUserToSiteButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupMesaageForInvitedUsers);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupMesaageForInvitedUsers);

			StringTokenizer userNameTokenValues = new StringTokenizer(invitedUserName, ",");

			while (userNameTokenValues.hasMoreElements()) {
				String userName = userNameTokenValues.nextElement().toString();

				String finalXpathForAddedUser = tempXpathForAddedUser.replace("CRAFT", userName);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForAddedUser);

				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAddedUser)) {
					report.updateTestLog("Invite User to Site",
							"Invite User to site using: SiteName = " + siteName + ", " + "Invited User Name = "
									+ userName + ", " + "Role = " + role + "<br/><b>Expected Result:</b>Invite:"
									+ userName + "<br><b>Actual Result:</b>Invite:" + userName,
							Status.PASS);
				} else {
					report.updateTestLog("Invite User to Site", "Failed to invite User to a site", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSiteToUploadFile() {
		try {
			String siteName = dataTable.getData("Sites", "SiteName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteFilesLinkXpath);
			UIHelper.click(driver, mySiteFilesLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitesSectionXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createdSitesContainerXpath);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, sitesSectionXpath));
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, createdSitesContainerXpath));
			UIHelper.waitFor(driver);
			List<WebElement> createdSitesEleList = driver.findElements(By.xpath(createdSitesListXpathFromMySites));
			for (WebElement actualSite : createdSitesEleList) {
				if (siteName.equals(actualSite.getText())) {
					UIHelper.clickanElement(actualSite);
					UIHelper.waitForPageToLoad(driver);
					break;
				}

			}
		} catch (Exception e) {

		}

	}

	public AlfrescoDocumentDetailsPage selectContentFromSite() {
		try {
			String contentName = dataTable.getData("MyFiles", "FileName");

			String contentNameLinkXPath = "//*[contains(text(),'" + contentName + "')]";
			UIHelper.waitForVisibilityOfEleByXpath(driver, contentNameLinkXPath);
			UIHelper.highlightElement(driver, contentNameLinkXPath);
			UIHelper.click(driver, contentNameLinkXPath);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("To Select Content in Site Page", "Content selected successfully", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("To Select Content in Site Page ", e.getMessage(), Status.FAIL);
		}

		return new AlfrescoDocumentDetailsPage(scriptHelper);
	}

	/**
	 * @author 412766
	 * @param fileOrFolderName
	 */
	public void openFolder(String folderName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalFileXpath = tempFileXpath.replace("CRAFT", folderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileXpath);
			UIHelper.click(driver, finalFileXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);

			report.updateTestLog("Navigate to Folder",
					"Folder Navigated successfully" + "<br /><b> Folder Name :</b>" + folderName, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate to Folder", "Navigate to Folder Failed", Status.FAIL);
		}
	}

	public AlfrescoSitesPage backFromFolderToMySiteDocuments() {
		try {

			UIHelper.click(driver, myDocumentsLableLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSitesPage(scriptHelper);
	}

	public boolean isDisplayedCreateFolderLink() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, createFolderLinkXpath);
		return UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, createFolderLinkXpath));
	}

	// Click All Documents Link
	public void clickAllDocumentsLink() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, allDocumentsLinkXpath);
		UIHelper.click(driver, allDocumentsLinkXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
		UIHelper.waitFor(driver);
	}

	// Move Folder from one site to another site
	public AlfrescoMyFilesPage moveFolderFromOneSiteToAnother() {
		try {
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

			if (folderDetails.contains(";")) {
				String splittedFolderDetailas[] = folderDetails.split(";");
				for (String folderValues : splittedFolderDetailas) {
					String splittedFolderValues[] = folderValues.split(",");

					String folderName = splittedFolderValues[0].replace("FolderName:", "");

					clickOnMoreSetting(folderName);

					clickOnMoveToLink(folderName);

					selectFileInMovePopUp();

				}
			} else {
				String splittedFolderValues[] = folderDetails.split(",");

				String folderName = splittedFolderValues[0].replace("FolderName:", "");

				clickOnMoreSetting(folderName);

				clickOnMoveToLink(folderName);

				selectFileInMovePopUp();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Perform Manage Aspects on Folder
	public AlfrescoMyFilesPage performManageAspectsOnFolder() {
		try {
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

			if (folderDetails.contains(";")) {
				String splittedFolderDetailas[] = folderDetails.split(";");
				for (String folderValues : splittedFolderDetailas) {
					String splittedFolderValues[] = folderValues.split(",");

					String folderName = splittedFolderValues[0].replace("FolderName:", "");

					clickOnMoreSetting(folderName);

					clickOnManageAspectsLink(folderName);
				}
			} else {
				String splittedFolderValues[] = folderDetails.split(",");

				String folderName = splittedFolderValues[0].replace("FolderName:", "");

				clickOnMoreSetting(folderName);

				clickOnManageAspectsLink(folderName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Get the selected aspects values
	public ArrayList<String> getSelectedAspects() {
		try {
			List<WebElement> selectedAspectsListEle = UIHelper.findListOfElementsbyXpath(selectedAspectsList, driver);

			for (WebElement ele : selectedAspectsListEle) {
				selectedAspectsListVal.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return selectedAspectsListVal;
	}

	// Click on 'More Settings' option link for file or Folder
	public void clickOnMoreSetting(String fileOrFolderName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForMoreOptionLink = tempXpathForMoreOptionLink.replace("CRAFT", fileOrFolderName);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			/*
			 * WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);

					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement moreSettingsEle = UIHelper.findAnElementbyXpath(driver, finalXpathForMoreOptionLink);
					UIHelper.highlightElement(driver, moreSettingsEle);
					executor.executeScript("arguments[0].click();", moreSettingsEle);
					UIHelper.waitFor(driver);
					report.updateTestLog("Click on 'More Settings' Link Option",
							"User successfully clicked the <b> 'More Settings'</b> Option using <b>" + fileOrFolderName
									+ "</b>",
							Status.DONE);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check Task is Created/Assigned
	public boolean checkFileOrFolderInDocLibOrMyFile(String fileOrFolderName) {
		boolean isDisplayedfileOrFolder = false;
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			isDisplayedfileOrFolder = checkFileOrFolder(fileOrFolderName);

			if (isDisplayedfileOrFolder == false) {

				String itemsCountMessage = UIHelper.getTextFromWebElement(driver, currentPageNatorXpath);

				int pageCount = 0;
				int pageItems = 0;
				if (itemsCountMessage.contains("of")) {
					String splitItemCountMsg[] = itemsCountMessage.split("of");

					if (splitItemCountMsg != null && splitItemCountMsg.length > 1) {
						pageItems = Integer.parseInt(splitItemCountMsg[1].trim());
						pageCount = pageItems / 50;
						String pageCountVal = "" + (pageItems / 50);
						if (pageCountVal.contains(".")) {
							String splitVal[] = pageCountVal.split(Pattern.quote("."));
							if (splitVal != null && splitVal.length > 1) {
								int decVal = Integer.parseInt(splitVal[1]);
								if (decVal > 0) {
									pageCount = pageCount + 1;
								}
							}
						}
					}
				}

				isDisplayedfileOrFolder = checkFileOrFolderInAllPages(pageCount, fileOrFolderName);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedfileOrFolder;
	}

	public boolean checkFileOrFolderInAllPages(int pageCount, String fileOrFolderName) {
		boolean isDisplayedfileOrFolder = false;
		try {
			for (int index = 1; index <= pageCount; index++) {

				int startVal = (index * 50) + 1;
				int tempEndVal = ((index + 1) * 50);
				String displayedItemsCountMessage = UIHelper.getTextFromWebElement(driver, currentPageNatorXpath);
				int noOfItems = 0;
				if (displayedItemsCountMessage.contains("of")) {
					String splittedItemsCountMsg[] = displayedItemsCountMessage.split("of");

					if (splittedItemsCountMsg != null && splittedItemsCountMsg.length > 1) {
						noOfItems = Integer.parseInt(splittedItemsCountMsg[1].trim());
					}
				}

				int endVal = 0;
				if (tempEndVal < noOfItems) {
					endVal = tempEndVal;
				} else {
					endVal = noOfItems;
				}

				String finalPageCountMessage = docLibPageCountMessage.replace("START", "" + startVal)
						.replace("END", "" + endVal).replace("TOTAL_ITEMS", "" + noOfItems);

				String finalXpathForPageNatorMsg = tempXpathForPageNatorMsg.replace("CRAFT", finalPageCountMessage);

				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, nextPageLinkXpath));
				UIHelper.click(driver, nextPageLinkXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalXpathForPageNatorMsg);

				UIHelper.waitFor(driver);

				isDisplayedfileOrFolder = checkFileOrFolder(fileOrFolderName);
				if (isDisplayedfileOrFolder) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedfileOrFolder;
	}

	public boolean checkFileOrFolder(String fileOrFolderName) {
		boolean isDisplayedfileOrFolder = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By.xpath(myFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {

					isDisplayedfileOrFolder = true;
					break;
				}
			}
		} catch (StaleElementReferenceException e) {
			checkFileOrFolder(fileOrFolderName);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedfileOrFolder;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isCustomizeSiteMenuAvailable() {
		boolean flag = false;
		try {
			String menuToNavigate = dataTable.getData("Sites", "CustomizeSiteMenuToNavigate");
			switch (menuToNavigate) {
			case "Discussions":
				flag = checkForCustomizeSiteMenu(customizeSiteDiscussionsMenuXpath);
				break;
			case "Wiki":
				flag = checkForCustomizeSiteMenu(customizeSiteWikiPageMenuXpath);
				break;
			case "Blog":
				flag = checkForCustomizeSiteMenu(customizeSiteBlogMenuXpath);
				break;
			case "Calendar":
				flag = checkForCustomizeSiteMenu(customizeSiteCalendarMenuXpath);
				break;
			case "Links":
				flag = checkForCustomizeSiteMenu(LinkBtnXpath);
				break;

			default:
				break;
			}
		} catch (Exception e) {

		}

		return flag;
	}

	/**
	 * @author 412766
	 * @param menuXpath
	 * @return
	 */
	private boolean checkForCustomizeSiteMenu(String menuXpath) {
		boolean flag = false;
		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, menuXpath))) {
				flag = true;
			} else if (UIHelper
					.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, customizeSiteMoreOptionMenuXpath))) {
				UIHelper.click(driver, customizeSiteMoreOptionMenuXpath);
				UIHelper.waitFor(driver);
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, menuXpath))) {
					flag = true;
				}
			}
		} catch (Exception e) {

		}

		return flag;
	}

	/**
	 * @author 412766
	 */
	public void navigateToCustomizeSiteMenu() {
		try {
			UIHelper.waitFor(driver);
			String menuToNavigate = dataTable.getData("Sites", "CustomizeSiteMenuToNavigate");
			switch (menuToNavigate) {
			case "Discussions":
				clickOnCustomizeSiteMenu(customizeSiteDiscussionsMenuXpath, menuToNavigate);
				break;
			case "Wiki":
				clickOnCustomizeSiteMenu(customizeSiteWikiPageMenuXpath, menuToNavigate);
				break;
			case "Blog":
				clickOnCustomizeSiteMenu(customizeSiteBlogMenuXpath, menuToNavigate);
				break;
			case "Calendar":
				clickOnCustomizeSiteMenu(customizeSiteCalendarMenuXpath, menuToNavigate);
				break;
			case "Links":
				clickOnCustomizeSiteMenu(LinkBtnXpath, menuToNavigate);
				break;

			default:
				break;
			}

		} catch (Exception e) {
			report.updateTestLog("Navigate to 'Customize Site' Menu", "Navigate to 'Customize Site' Menu Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param menuXpath
	 */
	private void clickOnCustomizeSiteMenu(String menuXpath, String menuToNavigate) {
		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, menuXpath))) {
				UIHelper.highlightElement(driver, menuXpath);
				UIHelper.click(driver, menuXpath);
				report.updateTestLog("Navigate to 'Customize Site' Menu",
						"Navigated Successfully" + "<br/><b> Menu Name : </b>" + menuToNavigate, Status.DONE);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
			} else if (UIHelper
					.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, customizeSiteMoreOptionMenuXpath))) {
				UIHelper.click(driver, customizeSiteMoreOptionMenuXpath);
				UIHelper.waitFor(driver);
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, menuXpath))) {
					UIHelper.highlightElement(driver, menuXpath);
					UIHelper.click(driver, menuXpath);
					report.updateTestLog("Navigate to 'Customize Site' Menu",
							"Navigated Successfully" + "<br/><b> Menu Name : </b>" + menuToNavigate, Status.DONE);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
				} else {
					report.updateTestLog("Navigate to 'Customize Site' Menu",
							"Not able to Navigate" + "<br/><b> Menu Name : </b>" + menuToNavigate, Status.FAIL);
				}
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Navigate to 'Customize Site' Menu", "Navigate to 'Customize Site' Menu Failed",
					Status.FAIL);
		}
	}

	// Check Action Name for File or folder
	public boolean checkActionNameForFolderOrFileInDocLibPage(String actionName, String fileOrFolderName) {
		boolean isDisplayedViewdetailsOption = false;
		try {
			/*
			 * String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath
			 * .replace("CRAFT", fileOrFolderName);
			 */
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForViewDetailsOptionLink = fileORFolderPropertOtionXpath.replace("CRAFT", fileOrFolderName)
					.replace("PROPNAME", actionName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By.xpath(myFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);
					/*
					 * WebElement chkboxElement = UIHelper.findAnElementbyXpath(
					 * driver, finalSelectFolderChkboxXpath);
					 * UIHelper.highlightElement(driver, chkboxElement);
					 * JavascriptExecutor executor = (JavascriptExecutor)
					 * driver; executor.executeScript("arguments[0].click();",
					 * chkboxElement);
					 */
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement viewDetailsEle = UIHelper.findAnElementbyXpath(driver,
							finalXpathForViewDetailsOptionLink);
					UIHelper.highlightElement(driver, viewDetailsEle);
					isDisplayedViewdetailsOption = true;
					/*
					 * UIHelper.waitFor(driver); UIHelper.waitFor(driver);
					 * UIHelper.waitForPageToLoad(driver);
					 * 
					 * if (UIHelper.checkForAnElementbyXpath(driver,
					 * docActionTwisterCloseXpath)) { UIHelper.click(driver,
					 * docActionTwisterCloseXpath);
					 * UIHelper.waitForVisibilityOfEleByXpath(driver,
					 * docActionTwisterOpenXpath); UIHelper.waitFor(driver); }
					 */
					break;
				} else {
					isDisplayedViewdetailsOption = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedViewdetailsOption;
	}

	// Click on ViewDetails Option for File or Folder
	public AlfrescoMyFilesPage clickOnViewDetails(String fileOrFolderName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForViewDetailsOptionLink = tempXpathForViewDetailsLink.replace("CRAFT", fileOrFolderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			/*
			 * WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement viewDetailsEle = UIHelper.findAnElementbyXpath(driver,
							finalXpathForViewDetailsOptionLink);
					UIHelper.highlightElement(driver, viewDetailsEle);
					executor.executeScript("arguments[0].click();", viewDetailsEle);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);

					Thread.sleep(2000);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);

					if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
						UIHelper.click(driver, docActionTwisterCloseXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
						UIHelper.waitFor(driver);
					}

					report.updateTestLog("Click on 'View Details'",
							"Clicked the 'View Details' option using Folder: " + fileOrFolderName, Status.DONE);

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Common method for perform browse action for file or folder
	public AlfrescoMyFilesPage commonMethodForPerformBrowseOption(String fileOrFolderName, String browseOption) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForBrowseActionOptionLink = tempXpathForBrowseAction.replace("CRAFT", fileOrFolderName)
					.replace("BROWSE_OPTION", browseOption);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, pageTitleXpath);
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
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle)
			 */;
			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.scrollToAnElement(folderEle);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement browseActionEle = UIHelper.findAnElementbyXpath(driver,
							finalXpathForBrowseActionOptionLink);
					UIHelper.highlightElement(driver, browseActionEle);
					executor.executeScript("arguments[0].click();", browseActionEle);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public boolean checkMoreOptions(String fileOrFolderName) {
		boolean isDisplayedMoreOptions = false;
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForMoreOptions = tempXpathForMoreOptions.replace("CRAFT", fileOrFolderName);
			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.scrollToAnElement(folderEle);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement browseActionEle = UIHelper.findAnElementbyXpath(driver, finalXpathForMoreOptions);

					if (UIHelper.checkForAnWebElement(browseActionEle)) {
						isDisplayedMoreOptions = true;
						UIHelper.highlightElement(driver, browseActionEle);
						UIHelper.waitFor(driver);
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedMoreOptions;
	}

	public boolean checkFileOrFolderBrowseOption(String fileOrFolderName, String browseOption) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForBrowseActionOptionLink = tempXpathForBrowseAction.replace("CRAFT", fileOrFolderName)
					.replace("BROWSE_OPTION", browseOption);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, pageTitleXpath);
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
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.scrollToAnElement(folderEle);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement browseActionEle = UIHelper.findAnElementbyXpath(driver,
							finalXpathForBrowseActionOptionLink);

					if (UIHelper.checkForAnWebElement(browseActionEle)) {
						isDisplayedBrowseOption = true;
						UIHelper.highlightElement(driver, browseActionEle);
						UIHelper.waitFor(driver);
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedBrowseOption;
	}

	public void sortResults() {
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, sortButtonXpath)) {
				UIHelper.click(driver, sortButtonXpath);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, modifierXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AlfrescoMyFilesPage checkSiteFolderUnderRepositoryPage(String fileOrFolderName) {

		try {
			boolean isPerformedBrowseAction;

			isPerformedBrowseAction = checkSiteFolder(fileOrFolderName);

			if (isPerformedBrowseAction == false) {
				List<WebElement> pagesList = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);
				if (pagesList.size() > 0) {
					String pageNumber = UIHelper.getTextFromWebElement(driver, pageNosXpath).trim();

					String pageValues[] = pageNumber.split("of");
					int pageCount = 0;
					if (pageValues != null && pageValues.length > 1) {
						int pageItems = Integer.parseInt(pageValues[1].trim());
						pageCount = pageItems / 50;
						String pageCountVal = "" + (pageItems / 50);
						if (pageCountVal.contains(".")) {
							String splitVal[] = pageCountVal.split(Pattern.quote("."));
							if (splitVal != null && splitVal.length > 1) {
								int decVal = Integer.parseInt(splitVal[1]);
								if (decVal > 0) {
									pageCount = pageCount + 1;
								}
							}
						}

					}

					for (int index = 2; index <= pageCount; index++) {
						String finalXpathForPages = tempXpathForPages.replace("CRAFT", "" + index);

						UIHelper.waitForVisibilityOfEleByXpath(driver, pagesBoxXpath);
						UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, pagesBoxXpath));
						UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, pagesBoxXpath));
						UIHelper.waitFor(driver);

						if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForPages)) {
							UIHelper.click(driver, nextPageLink);
							Thread.sleep(1000);
							UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
							UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
							UIHelper.waitFor(driver);
							UIHelper.waitFor(driver);
						}

						isPerformedBrowseAction = checkSiteFolder(fileOrFolderName);
						if (isPerformedBrowseAction) {
							report.updateTestLog("Verify Site Folder", "Site Folder: " + fileOrFolderName
									+ " is displayed successfully in Repository Page", Status.PASS);
							break;
						} else {
							report.updateTestLog("Verify Site Folder",
									"Site Folder: " + fileOrFolderName + " is failed to display in Repository Page",
									Status.FAIL);
						}
					}
				}

			} else {
				report.updateTestLog("Verify Site Folder",
						"Site Folder: " + fileOrFolderName + " is displayed successfully in Repository Page",
						Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public boolean checkSiteFolder(String fileOrFolderName) {
		boolean isPerformedBrowseAction = false;
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, pageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);

			/*
			 * WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver,
			 * pageBodyXpath); UIHelper.highlightElement(driver, pageBodyEle);
			 * UIHelper.mouseOveranElement(driver, pageBodyEle);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * myFilesTablesXpath); WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */
			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.scrollToAnElement(folderEle);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					isPerformedBrowseAction = true;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPerformedBrowseAction;
	}

	public void clickOnBrowseAction(String fileOrFolderName, String browseOption) {
		try {
			String finalXpathForBrowseActionOptionLink = tempXpathForBrowseAction.replace("CRAFT", fileOrFolderName)
					.replace("BROWSE_OPTION", browseOption);
			JavascriptExecutor executor = (JavascriptExecutor) driver;

			WebElement browseActionEle = UIHelper.findAnElementbyXpath(driver, finalXpathForBrowseActionOptionLink);
			UIHelper.highlightElement(driver, browseActionEle);
			executor.executeScript("arguments[0].click();", browseActionEle);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on '" + browseOption + "' Link",
					"User clicked '" + browseOption + "' link in browse actions ", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AlfrescoMyFilesPage clickOnEditProperties(String fileOrFolderName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForEditPropertiesLink = tempXpathForEditProperties.replace("CRAFT", fileOrFolderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			/*
			 * WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.scrollToAnElement(chkboxElement);
					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement moreSettingsEle = UIHelper.findAnElementbyXpath(driver, finalXpathForEditPropertiesLink);
					UIHelper.highlightElement(driver, moreSettingsEle);
					executor.executeScript("arguments[0].click();", moreSettingsEle);
					UIHelper.waitFor(driver);

					report.updateTestLog("Click on 'Edit Properties' Link",
							"User clicked 'Edit Properties' link in browse actions ", Status.DONE);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Click on Cancel button in Edit prop popup
	public void clickOnCancelBtnInEditPropWindow() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, cancelBtnXpathInEditProp)) {
				UIHelper.highlightElement(driver, cancelBtnXpathInEditProp);
				UIHelper.click(driver, cancelBtnXpathInEditProp);
				UIHelper.waitFor(driver);

				report.updateTestLog("Click on 'Cancel' button in 'Edit Properties' popup",
						"User clicked 'Cancel' button in 'Edit Properties' popup", Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on File or Folder properties link
	public AlfrescoMyFilesPage clickOnFileORFolderPropertiesOption(String fileOrFolderName, String propertyName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForFileORFolderPropOptionLink = fileORFolderPropertOtionXpath
					.replace("CRAFT", fileOrFolderName).replace("PROPNAME", propertyName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			/*
			 * WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement fileOrFolderPropEle = UIHelper.findAnElementbyXpath(driver,
							finalXpathForFileORFolderPropOptionLink);
					UIHelper.highlightElement(driver, fileOrFolderPropEle);
					executor.executeScript("arguments[0].click();", fileOrFolderPropEle);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);

					report.updateTestLog("Click on File or Folder settings: " + propertyName,
							"User able to click the file or folder property: " + propertyName, Status.DONE);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public void clickOnMoveToLink(String folderName) {
		try {
			String finalXpathForMoveToFolderLink = tempXpathForMoveToFolderLink.replace("CRAFT", folderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMoveToFolderLink);

			WebElement moveToElement = UIHelper.findAnElementbyXpath(driver, finalXpathForMoveToFolderLink);
			UIHelper.highlightElement(driver, moveToElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", moveToElement);

			UIHelper.click(driver, finalXpathForMoveToFolderLink);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, movePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnMoveInMovePopUpXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on 'Move to' Option",
					"User successfully clicked the <b> 'Move to' for " + folderName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnMoreOptionLink(String fileOrFolderName) {
		try {
			String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");

			commonMethodForClickOnMoreOptionLink(fileOrFolderName, moreSettingsOption);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param fileOrFolderName
	 * @param moreSettingsOption
	 */
	public void commonMethodForClickOnMoreOptionLink(String fileOrFolderName, String moreSettingsOption) {
		try {
			String finalXpathForMoreOptionLink = tempXpathForMoreSettings.replace("CRAFT", fileOrFolderName)
					.replace("MOREOPT", moreSettingsOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMoreOptionLink);

			WebElement moreOptElement = UIHelper.findAnElementbyXpath(driver, finalXpathForMoreOptionLink);
			UIHelper.highlightElement(driver, moreOptElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", moreOptElement);

			UIHelper.click(driver, finalXpathForMoreOptionLink);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on '" + moreSettingsOption + "' using 'More Settings' Options",
					"User successfully clicked the <b> '" + moreSettingsOption
							+ "</b> ' using 'More Settings' Option for " + fileOrFolderName,
					Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void commonMethodForClickOnMoreOptionLinkWithoutWait(String fileOrFolderName, String moreSettingsOption) {
		try {
			String finalXpathForMoreOptionLink = tempXpathForMoreSettings.replace("CRAFT", fileOrFolderName)
					.replace("MOREOPT", moreSettingsOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMoreOptionLink);

			WebElement moreOptElement = UIHelper.findAnElementbyXpath(driver, finalXpathForMoreOptionLink);
			UIHelper.highlightElement(driver, moreOptElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", moreOptElement);

			UIHelper.click(driver, finalXpathForMoreOptionLink);
		    report.updateTestLog("Click on '" + moreSettingsOption + "' using 'More Settings' Options",
					"User successfully clicked the <b> '" + moreSettingsOption
							+ "</b> ' using 'More Settings' Option for " + fileOrFolderName,
					Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String addReferenceDropDownOptionsXpath = ".//select[contains(@id,'dropdown')]//*[text()='CRAFT']";
	private String addReferenceCreateButtonXpath = ".//button[text()='Create ']";

	public void addRelationship(String relationshipName, String fileName) {
		try {
			String finalXpathForFile = temprlnXapthForFF.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);

			UIHelper.click(driver, addReferenceDropDownOptionsXpath.replace("CRAFT", relationshipName));
			UIHelper.waitFor(driver);
			UIHelper.click(driver, addRelationshipBtnXpath);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// addReferenceCreateButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);
			UIHelper.click(driver, finalXpathForFile);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, okBtnXpathForRelationshipPopup);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
			UIHelper.click(driver, createRelationBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			report.updateTestLog("Apply Relationship for a file/folder",
					"Relationship Applied successfully" + "<br><b>Relationship Name : </b>" + relationshipName,
					Status.DONE);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// Add Relationship to verify the loading message
	public void addRelationshipForMessageCheck(String relationshipName, String fileName) {
		try {
			String finalXpathForFile = temprlnXapthForFF.replace("CRAFT", fileName);
			clickOnAddRelationshipButtonInNewRelationShip(relationshipName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);
			UIHelper.click(driver, finalXpathForFile);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, okBtnXpathForRelationshipPopup);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
			UIHelper.click(driver, createRelationBtnXpath);
			report.updateTestLog("Add relation to verify the loading message",
					"Add relation click successfull" + "<br><b>Relationship Name : </b>" + relationshipName,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Add relation to verify the loading message",
					"Add relation click  failed",
					Status.FAIL);
		}
	}
	
	// Click on Add relationship button in New Relationship Pop Up
	public void clickOnAddRelationshipButtonInNewRelationShip(String relationshipName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, addReferenceDropDownOptionsXpath.replace("CRAFT", relationshipName));
			UIHelper.waitFor(driver);
			UIHelper.click(driver, addRelationshipBtnXpath);
			report.updateTestLog("Click on Add Relationship Button in New Relationship prompt",
					"Click on Add Relationship Button in New Relationship prompt successfull",
					Status.DONE);
		}catch(Exception e) {
			report.updateTestLog("Click on Add Relationship Button in New Relationship prompt",
					"Click on Add Relationship Button in New Relationship prompt failed",
					Status.FAIL);
		}
	}
	
	// Click on cancel button in New Relationship Pop Up
    public void clickOnCancelButtonInNewRelationship() {
    	try {
    		UIHelper.waitForVisibilityOfEleByXpath(driver, cancelButtonForRelationshipPopup);
    		UIHelper.click(driver, cancelButtonForRelationshipPopup);
    		report.updateTestLog("Click on cancel in New Relationship prompt",
					"Click on Cancel Button in New Relationship prompt successfull",
					Status.DONE);
    		
    	}catch(Exception e) {
    		report.updateTestLog("Click on cancel in New Relationship prompt",
					"Click on Cancel Button in New Relationship prompt failed",
					Status.FAIL);
    	}
    }
    
 // Click on create button in New Relationship Pop Up
    public void clickOnCreateButtonInNewRelationship() {
    	try {
    		UIHelper.waitFor(driver);
    		UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
			UIHelper.click(driver, createRelationBtnXpath);
    		report.updateTestLog("Click on create in New Relationship prompt",
					"Click on create Button in New Relationship prompt successfull",
					Status.DONE);
    		
    	}catch(Exception e) {
    		report.updateTestLog("Click on create in New Relationship prompt",
					"Click on create Button in New Relationship prompt failed",
					Status.FAIL);
    	}
    }
    
    
    
    // click on the navigator button in the select layout while adding new relationship
    public void clickOnNavigatorItemInSelectPrompt(String navigatorItemName) {
    	String finalNavigatorItemNameXpath=tempNavigatorItemNameXpath.replace("CRAFT", navigatorItemName);
    	try {
    		UIHelper.waitForVisibilityOfEleByXpath(driver, navigatorButtonInSelectPromptForNewRelationshipXpath);
    		UIHelper.click(driver, navigatorButtonInSelectPromptForNewRelationshipXpath);
    		UIHelper.waitFor(driver);
    		UIHelper.waitForVisibilityOfEleByXpath(driver, finalNavigatorItemNameXpath);
    		UIHelper.highlightElement(driver, finalNavigatorItemNameXpath);
    		UIHelper.click(driver, finalNavigatorItemNameXpath);
    		report.updateTestLog("Click on navigator item in select prompt for New Relationship",
					"Click on navigator item in select prompt for New Relationship successfull",
					Status.DONE);
    	}catch(Exception e){
    		report.updateTestLog("Click on navigator item in select prompt for New Relationship",
					"Click on navigator item in select prompt for New Relationship failed",
					Status.FAIL);
    	}
    	
    }
    
    // Navigate into the site or folder in the left panel of select layout while adding new relationship
    public void navigateInsideLeftPanelofSelectPrompt(String siteOrFolderName,String loadingText) {
    	String finalLoadingText=tempLoadingtext.replace("CRAFT", loadingText);
    	String finalSelectItem=tempSiteorFolderToSelect.replace("CRAFT", siteOrFolderName);
    	try {
    		UIHelper.waitFor(driver);
    		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, finalLoadingText);
    		UIHelper.waitFor(driver);
    		UIHelper.highlightElement(driver, finalSelectItem);
    		UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, finalSelectItem));
    		report.updateTestLog("Navigate inside in select prompt for New Relationship",
					"Navigate inside in select prompt for New Relationship successfull",
					Status.DONE);
    	}catch(Exception e){
    		report.updateTestLog("Navigate inside in select prompt for New Relationship",
					"Navigate inside in select prompt for New Relationship failed",
					Status.FAIL);
    	}
    	
    }
    
    // Click on the plus sign corresponding to the site/folder in the left panel of select layout while adding new relationship
    public void selectFromLeftPanelofSelectPrompt(String siteOrFolderName,String loadingText) {
    	String finalLoadingText=tempLoadingtext.replace("CRAFT", loadingText);
    	String finalSelectItem=tempPlusButtonInSelectPromptXpath.replace("CRAFT", siteOrFolderName);
    	try {
    		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, finalLoadingText);
    		UIHelper.waitFor(driver);
    		UIHelper.highlightElement(driver, finalSelectItem);
    		UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, finalSelectItem));
    		report.updateTestLog("Add inside select prompt for New Relationship",
					"Add inside in select prompt for New Relationship successfull",
					Status.DONE);
    	}catch(Exception e){
    		report.updateTestLog("Add inside in select prompt for New Relationship",
					"Add inside in select prompt for New Relationship failed",
					Status.FAIL);
    	}
    	
    }
    
    // To click on the Ok button in Select layout while adding new relationship
    public void  clickOnOkButtonInSelectPromptForNewRelationship() {
    	try {
    		UIHelper.waitFor(driver);
    		UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);
    		UIHelper.highlightElement(driver, okBtnXpathForRelationshipPopup);
    		UIHelper.click(driver, okBtnXpathForRelationshipPopup);
    		report.updateTestLog("Click on Ok button in select prompt for New Relationship",
					"Click on Ok button in select prompt for New Relationship successfull",
					Status.DONE);
    	}catch(Exception e) {
    		report.updateTestLog("Click on Ok button in select prompt for New Relationship",
					"Click on Ok button in select prompt for New Relationship failed",
					Status.FAIL);
    	}
    	
    }
    
	/**
	 * @author 412766
	 * @param relationshipName
	 * @param fileName
	 */
	public void addRelationshipInDifferentSite(String relationshipName, String fileName, String siteName) {
		try {
			String finalXpathForFile = tempXpathForFile.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath))
				UIHelper.highlightElement(driver, relationshipDropdownXpath);
			{
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, addRelationshipBtnXpath);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);

				String documentLibXpath = ".//*[@id='relationship-docPicker-cntrl-picker-navigator-button']";
				String siteXpath = ".//*[text()='Sites']";
				String loadingXpath = ".//*[contains(@class,'yui-dt-liner') and text()='Loading...']";
				String siteNameXpath = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td//h3/a[text()='CRAFT']";
				String docLibButtonXpath = ".//*[text()='documentLibrary']//following::td//a[@title='Add']";

				String finalsiteNameXpath = siteNameXpath.replace("CRAFT", siteName);

				UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
				UIHelper.highlightElement(driver, documentLibXpath);
				UIHelper.click(driver, documentLibXpath);

				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, siteXpath);
				UIHelper.click(driver, siteXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalsiteNameXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalsiteNameXpath));
				UIHelper.highlightElement(driver, finalsiteNameXpath);
				UIHelper.click(driver, finalsiteNameXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, docLibButtonXpath);
				UIHelper.highlightElement(driver, docLibButtonXpath);
				UIHelper.click(driver, docLibButtonXpath);

				/*
				 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				 * loadingXpath); UIHelper.waitForVisibilityOfEleByXpath(driver,
				 * finalXpathForFile); UIHelper.highlightElement(driver,
				 * finalXpathForFile); UIHelper.click(driver,
				 * finalXpathForFile);
				 */

				UIHelper.waitFor(driver);
				UIHelper.click(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
				UIHelper.click(driver, createRelationBtnXpath);

				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				report.updateTestLog("Apply Relationship for a file/folder in differnt site",
						"Relationship Applied successfully" + "<br><b>Relationship Name : </b>" + relationshipName
								+ "<br><b>File Name : </b>" + fileName + "<br><b>Site Name : </b>" + siteName,
						Status.PASS);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Relationship for a file/folder in other site",
					"Apply Relationship for a file/folder in other site Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param relationshipName
	 * @param fileOrFolderName
	 * @param siteName
	 */
	public void addRelationshipBtwAssetAndSite(String relationshipName, String fileOrFolderName, String siteName) {
		try {
			String finalXpathForSite = tempXpathForSite.replace("CRAFT", siteName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath)) {
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);

				String documentLibXpath = ".//*[@id='relationship-docPicker-cntrl-picker-navigator-button']";
				String siteXpath = ".//*[text()='Sites']";
				String loadingXpath = ".//*[contains(@class,'yui-dt-liner') and text()='Loading...']";
				String siteNameXpath = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td//h3/a[text()='"
						+ siteName + "']";

				UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, documentLibXpath);
				UIHelper.click(driver, documentLibXpath);
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, siteXpath);
				UIHelper.highlightElement(driver, siteXpath);
				UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, siteXpath));

				// UIHelper.javascriptClick(driver,
				// UIHelper.findAnElementbyXpath(driver, siteXpath));
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameXpath);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, siteNameXpath));
					UIHelper.highlightElement(driver, siteNameXpath);
				} catch (Exception e) {

				}

				UIHelper.highlightElement(driver, finalXpathForSite);
				UIHelper.click(driver, finalXpathForSite);

				UIHelper.waitFor(driver);
				UIHelper.click(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
				UIHelper.click(driver, createRelationBtnXpath);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				} catch (Exception e) {

				}
				report.updateTestLog("Apply Relationship for a file/folder in differnt site",
						"Relationship Applied successfully" + "<br><b>Relationship Name : </b>" + relationshipName
								+ "<br><b>File/Folder Name : </b>" + fileOrFolderName + "<br><b>Site Name : </b>"
								+ siteName,
						Status.PASS);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Relationship for a file/folder in other site",
					"Apply Relationship for a file/folder in other site Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param relationshipName
	 * @param fileOrFolderName
	 * @param siteName
	 */
	public void addRelationshipBtwAssetAndSiteFolder(String relationshipName, String siteDestFileOrFolderName,
			String siteName) {
		try {
			String finalXpathForSiteFolder = tempXpathForSite.replace("CRAFT", siteDestFileOrFolderName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath)) {
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);

				String documentLibXpath = ".//*[@id='relationship-docPicker-cntrl-picker-navigator-button']";
				String siteXpath = ".//*[text()='Sites']";
				String loadingXpath = ".//*[contains(@class,'yui-dt-liner') and text()='Loading...']";
				String siteNameXpath = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td//h3/a[text()='"
						+ siteName + "']";
				String siteDocLibXpath = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td//h3/a[text()='documentLibrary']";

				UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
				UIHelper.highlightElement(driver, documentLibXpath);
				UIHelper.click(driver, documentLibXpath);

				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, siteXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, siteXpath));
				UIHelper.click(driver, siteXpath);

				Thread.sleep(2000);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameXpath);
				} catch (Exception e) {
				}
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, siteNameXpath));
				UIHelper.highlightElement(driver, siteNameXpath);
				UIHelper.click(driver, siteNameXpath);

				Thread.sleep(1000);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, siteDocLibXpath);
				} catch (Exception e) {
				}

				UIHelper.highlightElement(driver, siteDocLibXpath);
				UIHelper.click(driver, siteDocLibXpath);

				Thread.sleep(1000);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteFolder);
				} catch (Exception e) {
				}

				UIHelper.highlightElement(driver, finalXpathForSiteFolder);
				UIHelper.click(driver, finalXpathForSiteFolder);

				UIHelper.waitFor(driver);
				UIHelper.click(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
				UIHelper.click(driver, createRelationBtnXpath);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				} catch (Exception e) {
				}

				report.updateTestLog("Apply Relationship for a file/folder in differnt site",
						"Relationship Applied successfully" + "<br><b>Relationship Name : </b>" + relationshipName
								+ "<br><b>File/Folder Name : </b>" + siteDestFileOrFolderName
								+ "<br><b>Site Name : </b>" + siteName,
						Status.PASS);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Relationship for a file/folder in other site",
					"Apply Relationship for a file/folder in other site Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param relationshipName
	 * @param fileOrFolderName
	 * @param siteName
	 */
	public void selectRelationshipNameAndclickOnAddRelationshipBtn(String relationshipName, String fileOrFolderName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath)) {
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);

				String documentLibXpath = ".//*[@id='relationship-docPicker-cntrl-picker-navigator-button']";
				String siteXpath = ".//*[text()='Sites']";
				String loadingXpath = ".//*[contains(@class,'yui-dt-liner') and text()='Loading...']";

				UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
				UIHelper.highlightElement(driver, documentLibXpath);
				UIHelper.click(driver, documentLibXpath);

				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, siteXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, siteXpath));
				UIHelper.click(driver, siteXpath);

				Thread.sleep(1000);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);

				UIHelper.waitFor(driver);

				report.updateTestLog("Select add relationship name from dropdown and click on Add Relationship Button",
						"User able to select relationship name from dropdown and clicked 'Add Relationship' Button"
								+ "<br><b>Relationship Name : </b>" + relationshipName
								+ "<br><b>File/Folder Name : </b>" + fileOrFolderName,
						Status.PASS);

			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select add relationship name from dropdown and click on Add Relationship Button",
					"User not able to select relationship name from dropdown and clicked 'Add Relationship' Button"
							+ "<br><b>Relationship Name : </b>" + relationshipName + "<br><b>File/Folder Name : </b>"
							+ fileOrFolderName,
					Status.FAIL);
		}
	}

	// Get Site names list count from Select window (for Add new relationship)
	public int getSiteNamesCountFormSelectWindow() {
		int sitesIconCount = 0, sitesCount = 0, fileFolderCount = 0;
		String attribute = dataTable.getData("MyFiles", "CreateFileDetails");
		try {
			if (UIHelper.chkForThisElementList(UIHelper.findListOfElementsbyXpath(listOfFileFolderXpath, driver))) {
				List<WebElement> listOfFileFolderEles = UIHelper.findListOfElementsbyXpath(listOfFileFolderXpath,
						driver);
				fileFolderCount = listOfFileFolderEles.size();
				for (int x = 0; x < fileFolderCount; x++) {
					String names1 = UIHelper.getAttributeFromWebElement(driver, listOfFileFolderXpath, attribute);
					report.updateTestLog("Verify files and folder are listed in relationship picker window",
							"Files and folder are listed in relationship picker window"
									+ "</br><b>File/folder Name:</b> " + names1,
							Status.FAIL);
				}

			} else {
				report.updateTestLog("Verify files and folder are listed in relationship picker window",
						"Files and folder are not listed in relationship picker window", Status.PASS);

			}
			UIHelper.highlightElement(driver, listOfSitesIconXPath);
			List<WebElement> listOfSiteIconEles = UIHelper.findListOfElementsbyXpath(listOfSitesIconXPath, driver);
			sitesIconCount = listOfSiteIconEles.size();
			UIHelper.highlightElement(driver, listOfSitesXpath);
			List<WebElement> listOfSiteEles = UIHelper.findListOfElementsbyXpath(listOfSitesXpath, driver);
			sitesCount = listOfSiteEles.size();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sitesIconCount;
	}

	// Get sites from 'Relationship picker'
	public ArrayList<String> getSitesFromRelationshipPicker() {
		ArrayList<String> sitesList = new ArrayList<String>();
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, listOfSitesIconXPath);
			UIHelper.highlightElement(driver, listOfSitesIconXPath);
			UIHelper.highlightElement(driver, listOfSitesXpath);
			List<WebElement> listOfSites = driver.findElements(By.xpath(listOfSitesXpath));
			for (WebElement ele : listOfSites) {
				sitesList.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sitesList;
	}

	public void clickOnManageAspectsLink(String folderName) {
		try {
			String finalXpathForManageAspects = tempXpathForManageAspects.replace("CRAFT", folderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForManageAspects);

			WebElement manageAspectsElement = UIHelper.findAnElementbyXpath(driver, finalXpathForManageAspects);
			UIHelper.highlightElement(driver, manageAspectsElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", manageAspectsElement);

			UIHelper.click(driver, finalXpathForManageAspects);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, xpathForManageAspectsLeftPaneInPoup);
			UIHelper.waitForVisibilityOfEleByXpath(driver, xpathForManageAspectsRightPaneInPoup);
			UIHelper.waitForVisibilityOfEleByXpath(driver, applyChangesBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on 'Manage Aspects' Link Option",
					"User able to click the 'Manage Aspects' link option for Folder: " + folderName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// For my files & shared files
	public void selectRelationshipNameAndclickOnAddRelationshipBtnFromMySharedFiles(String relationshipName,
			String fileOrFolderName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath)) {
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);

				String documentLibXpath = ".//*[@id='relationship-docPicker-cntrl-picker-navigator-button']";
				String companyHomesXpath = ".//*[text()='Company Home']";
				String siteXpath = ".//*[text()='Sites']";
				String loadingXpath = ".//*[contains(@class,'yui-dt-liner') and text()='Loading...']";

				UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
				UIHelper.highlightElement(driver, documentLibXpath);
				UIHelper.click(driver, documentLibXpath);

				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, companyHomesXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, companyHomesXpath));
				UIHelper.click(driver, companyHomesXpath);

				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, siteXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, siteXpath));
				UIHelper.click(driver, siteXpath);

				Thread.sleep(1000);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);

				UIHelper.waitFor(driver);

				report.updateTestLog("Select add relationship name from dropdown and click on Add Relationship Button",
						"User able to select relationship name from dropdown and clicked 'Add Relationship' Button"
								+ "<br><b>Relationship Name : </b>" + relationshipName
								+ "<br><b>File/Folder Name : </b>" + fileOrFolderName,
						Status.PASS);

			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select add relationship name from dropdown and click on Add Relationship Button",
					"User not able to select relationship name from dropdown and clicked 'Add Relationship' Button"
							+ "<br><b>Relationship Name : </b>" + relationshipName + "<br><b>File/Folder Name : </b>"
							+ fileOrFolderName,
					Status.FAIL);
		}
	}

	public void clickOnCancelBtnInManageAspectsPopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelBtnXpath);
			UIHelper.click(driver, cancelBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select a file from popup to move
	public void selectFileInMovePopUp() {
		try {
			String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", targetSiteName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalSelectFileToMoveXpath);
			} catch (Exception e) {
			}

			UIHelper.click(driver, finalSelectFileToMoveXpath);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, btnMoveInMovePopUpXpath);
			} catch (Exception e) {

			}
			UIHelper.waitFor(driver);
			UIHelper.click(driver, btnMoveInMovePopUpXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			} catch (Exception e) {

			}

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Verify 'Move To'", "Move file to " + "Target Site = " + targetSiteName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Copy Folder from one site to another site
	public AlfrescoMyFilesPage copyFolderFromOneSiteToAnother() {
		try {
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

			if (folderDetails.contains(";")) {
				String splittedFolderDetailas[] = folderDetails.split(";");
				for (String folderValues : splittedFolderDetailas) {
					String splittedFolderValues[] = folderValues.split(",");

					String folderName = splittedFolderValues[0].replace("FolderName:", "");

					clickOnMoreSetting(folderName);

					clickOnCopyToLink(folderName);

					selectFileInCopyPopUp();

				}
			} else {
				String splittedFolderValues[] = folderDetails.split(",");

				String folderName = splittedFolderValues[0].replace("FolderName:", "");

				clickOnMoreSetting(folderName);

				clickOnCopyToLink(folderName);

				selectFileInCopyPopUp();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public void clickOnCopyToLink(String folderName) {
		try {
			String finalXpathForCopyToFolderLink = tempXpathForCopyToFolderLink.replace("CRAFT", folderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCopyToFolderLink);

			WebElement copyToElement = UIHelper.findAnElementbyXpath(driver, finalXpathForCopyToFolderLink);
			UIHelper.highlightElement(driver, copyToElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", copyToElement);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, movePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnCopyInCopyPopUpXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select a file from popup to move
	public void selectFileInCopyPopUp() {
		try {
			String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
			String finalSelectFileToMoveXpath = selectFileToMoveXpath.replace("CRAFT", targetSiteName);

			try {
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalSelectFileToMoveXpath);
			} catch (Exception e) {
			}

			UIHelper.click(driver, finalSelectFileToMoveXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, btnCopyInCopyPopUpXpath);
			} catch (Exception e) {
			}

			UIHelper.waitFor(driver);
			UIHelper.click(driver, btnCopyInCopyPopUpXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			} catch (Exception e) {
			}

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Verify 'Copy To'", "Copy file to " + "Target Site = " + targetSiteName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Navigate to Document Library
	public void documentlib() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
			UIHelper.click(driver, documentLibXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);

			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			// UIHelper.waitFor(driver);

			report.updateTestLog("Navigate to Document Library", "Document Library loaded successfully", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate to Document Library", "Failed to load Document Library", Status.FAIL);
		}
	}

	// Method for find site using 'Site Finder'
	public void siteFinder(String siteName) {
		UIHelper.waitForVisibilityOfEleByXpath(driver, siteMenuXpath);
		WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
		UIHelper.mouseOveranElement(driver, siteMenuEle);
		UIHelper.highlightElement(driver, siteMenuEle);
		UIHelper.click(driver, siteMenuXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, findSiteXpath);
		UIHelper.click(driver, findSiteXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, searchBoxXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, searchBoxXpath, siteName);
		UIHelper.click(driver, searchButtonXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchingXpath);
		UIHelper.findandAddElementsToaList(driver, siteNameListXpath, siteResultList);
		if (siteResultList.size() > 0 && siteResultList.contains(siteName)) {
			String finalsiteresultXpath = tempsiteresultXpath.replace("CRAFT", siteName);
			UIHelper.iterateListandhighlightele(driver, siteResultList, siteName, finalsiteresultXpath);
			UIHelper.click(driver, finalsiteresultXpath);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, documentLibraryXpath);

			report.updateTestLog("Find Input Site", "Given site is located." + "<br /><b>SiteName :</b> " + siteName,
					Status.DONE);

		} else {
			// homePageObj.navigateToSitesTab();
			openSiteFromRecentSites(siteName);
		}

	}

	// Search Site and return true or false
	public Boolean siteFinderOption(String siteName) {
		try {
			WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
			UIHelper.mouseOveranElement(driver, siteMenuEle);
			UIHelper.highlightElement(driver, siteMenuEle);
			UIHelper.click(driver, siteMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, findSiteXpath);
			UIHelper.click(driver, findSiteXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchBoxXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, searchBoxXpath, siteName);
			UIHelper.click(driver, searchButtonXpath);
			UIHelper.waitFor(driver);
			UIHelper.findandAddElementsToaList(driver, siteNameListXpath, siteResultList);
			String finalsiteresultXpath = tempsiteresultXpath.replace("CRAFT", siteName);
			if (siteResultList.size() > 0
					&& UIHelper.findAnElementbyXpath(driver, finalsiteresultXpath).isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// Cancel the check out of file locked by user by Admin user.
	public void cancelcheck() {

		UIHelper.waitForPageToLoad(driver);
		UIHelper.findandAddElementsToaList(driver, FilesAndFolderListXpath, filesAndFoldersList);
		String file = dataTable.getData("Sites", "FileName");
		String finalsiteresultXpath = tempSelectedFileXpath.replace("CRAFT", file);
		UIHelper.iterateListandhighlightele(driver, filesAndFoldersList, file, finalsiteresultXpath);
		UIHelper.waitFor(driver);
		UIHelper.click(driver, finalsiteresultXpath);

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.click(driver, cancelEditXpath);
		UIHelper.waitForPageToLoad(driver);
		driver.navigate().back();
		report.updateTestLog("Given locked file is unlocked", "FileName = " + file, Status.DONE);
	}

	public void getModeratedSite(String siteName) {
		WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
		UIHelper.mouseOveranElement(driver, siteMenuEle);
		UIHelper.highlightElement(driver, siteMenuEle);
		UIHelper.click(driver, siteMenuXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, findSiteXpath);
		UIHelper.click(driver, findSiteXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, searchBoxXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, searchBoxXpath, siteName);
		UIHelper.click(driver, searchButtonXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameListXpath);
		UIHelper.findandAddElementsToaList(driver, siteNameListXpath, siteResultList);
		if (siteResultList.contains(siteName)) {
			String finalsiteresultXpath = tempsiteresultXpath.replace("CRAFT", siteName);
			String finalCheckModXpath = tempCheckModXpath.replace("CRAFT", siteName);
			String siteType = dataTable.getData("Sites", "SiteType");
			if (UIHelper.findAnElementbyXpath(driver, finalCheckModXpath).getText().equalsIgnoreCase(siteType)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, finalsiteresultXpath));
				UIHelper.click(driver, finalsiteresultXpath);

				report.updateTestLog("Find Site", "Given site is found via Site Finder: " + "SiteName = " + siteName,
						Status.DONE);
			} else {
				report.updateTestLog("Moderated Site", "Given site is not Moderated Site", Status.FAIL);
			}
		} else {
			report.updateTestLog("Moderated Site", "Given site is not Moderated Site", Status.FAIL);
		}

	}

	public void loginModeratedSite() {
		UIHelper.waitForPageToLoad(driver);
		if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, documentLibXpath)) == true) {
			documentlib();
		}
	}

	// Click on the select document to Navigate to Document Details Package
	public void documentdetails(String file) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalFileXpath = tempFileXpath.replace("CRAFT", file);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, finalFileXpath)) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileXpath);
				report.updateTestLog("Verify Document/Folder is present",
						"Document/Folder is present" + "<br /><b> File/Folder Name :</b>" + file, Status.PASS);
				UIHelper.click(driver, finalFileXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
					UIHelper.click(driver, docActionTwisterCloseXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
					UIHelper.waitFor(driver);
				}

				report.updateTestLog("Open Document",
						"Document details page displayed using" + "<br /><b> File Name :</b>" + file, Status.DONE);
			} else {
				report.updateTestLog("Verify Document/Folder is present",
						"Document/Folder is not present" + "<br /><b> File/Folder Name :</b>" + file, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Open Document",
					"Document Details Page not found" + "<br /><b> File Name :</b>" + file, Status.FAIL);
		}
	}

	// Click on the select document to Navigate to Document Details Package
	public Boolean Checkdocument(String file) {
		try {

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			// Thread.sleep(4000);
			String finalFileXpath = tempFileXpath.replace("CRAFT", file);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, finalFileXpath)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// Manage Permission from one site to another site
	public AlfrescoMyFilesPage manageUserPermForFileMoveInOneSiteToAnother() {
		try {
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

			if (folderDetails.contains(";")) {
				String splittedFolderDetailas[] = folderDetails.split(";");
				for (String folderValues : splittedFolderDetailas) {
					String splittedFolderValues[] = folderValues.split(",");

					String folderName = splittedFolderValues[0].replace("FolderName:", "");

					clickOnMoreSetting(folderName);

					clickOnManagePermissionLink(folderName);

				}
			} else {
				String splittedFolderValues[] = folderDetails.split(",");

				String folderName = splittedFolderValues[0].replace("FolderName:", "");

				clickOnMoreSetting(folderName);

				clickOnManagePermissionLink(folderName);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public void editOffline() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, editOfflineXpath);
			UIHelper.highlightElement(driver, editOfflineXpath);
			UIHelper.click(driver, editOfflineXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, lockBannerXpath);
			// String downloadedFilePath =
			// Settings.getInstance().getProperty("DefaultDownloadPath");
			// String fileName = dataTable.getData("MyFiles", "FileName");
			// File downloadFile = new File(downloadedFilePath + "/" + fileName
			// +
			// " (Working Copy)");
			// while (true) {
			if (UIHelper.findAnElementbyXpath(driver, lockBannerXpath).isDisplayed()) {

				report.updateTestLog("Edit offline", "File Checkout for Edit offline successfully", Status.DONE);
			} else {
				report.updateTestLog("Edit offline", "Edit offline failed", Status.FAIL);
			}
		} catch (Exception e) {

			e.printStackTrace();
			report.updateTestLog("Edit offline", "Edit offline failed", Status.FAIL);
		}

	}

	// Manage Permission on a file
	public AlfrescoMyFilesPage managePermissionOnFile() {

		try {
			String fileName = dataTable.getData("MyFiles", "FileName");

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					clickOnMoreSetting(fileNameVal);

					clickOnManagePermissionLink(fileNameVal);
				}
			} else {
				clickOnMoreSetting(fileName);

				clickOnManagePermissionLink(fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Click on Manage Permission link
	public void clickOnManagePermissionLink(String folderName) {
		try {
			String finalXpathForManagePermisssionFolderLink = tempXpathForManagePermissionFolderLink.replace("CRAFT",
					folderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForManagePermisssionFolderLink);

			WebElement managePermElement = UIHelper.findAnElementbyXpath(driver,
					finalXpathForManagePermisssionFolderLink);
			UIHelper.highlightElement(driver, managePermElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", managePermElement);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnAddUserGroupInManagePermissionXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on Manage Permission Link Option",
					"User able to click the Manage Permission Link Option for " + "Folder = " + folderName,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Add/User group Button
	public void clickOnAddUserGroupButton() {
		try {
			String userName = dataTable.getData("Sites", "InviteUserName");
			UIHelper.click(driver, btnAddUserGroupInManagePermissionXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, txtboxSearchAddUserGroupInManagePermissionXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, txtboxSearchAddUserGroupInManagePermissionXpath, userName);

			UIHelper.click(driver, btnSearchAddUserGroupInManagePermissionXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchLoadingXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchLoadingXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsAddUserGroupInManagePermissionXpath);

			String finalUserFromSearchResultsXpath = btnAddInAddUserGroupInManagePermissionXpath.replace("CRAFT",
					userName);

			WebElement clickOnElement = UIHelper.findAnElementbyXpath(driver, finalUserFromSearchResultsXpath);
			UIHelper.highlightElement(driver, clickOnElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", clickOnElement);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on 'Add User Group' button", "User able to click the 'Add User Group' button",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on 'Add User Group' button",
					"User not able to click the 'Add User Group' button", Status.FAIL);

		}
	}

	public void clickOnUserRoleBtn() {
		try {
			WebElement userRoleEle = UIHelper.findAnElementbyXpath(driver,
					btnRoleContributorAddUserGroupInManagePermissionXpath);
			UIHelper.highlightElement(driver, userRoleEle);
			UIHelper.mouseOveranElement(driver, userRoleEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", userRoleEle);

			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mangePermission(String roleName) {
		try {
			String finalXpathForSelectUserRole = tempXpathForSelectUserRole.replace("CRAFT", roleName);
			WebElement userRoleEle = UIHelper.findAnElementbyXpath(driver,
					btnRoleContributorAddUserGroupInManagePermissionXpath);
			UIHelper.highlightElement(driver, userRoleEle);
			UIHelper.mouseOveranElement(driver, userRoleEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", userRoleEle);
			UIHelper.mouseOverandclickanElement(driver, userRoleEle);
			UIHelper.waitFor(driver);
			WebElement roleSelectionEle = UIHelper.findAnElementbyXpath(driver, finalXpathForSelectUserRole);
			executor.executeScript("arguments[0].click();", roleSelectionEle);
			UIHelper.waitFor(driver);

			report.updateTestLog("Change the Permission", "User able to change the permission as " + roleName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getChangedRoleName() {
		WebElement userRoleEle = UIHelper.findAnElementbyXpath(driver,
				btnRoleContributorAddUserGroupInManagePermissionXpath);
		UIHelper.highlightElement(driver, userRoleEle);
		return userRoleEle.getText();
	}

	public void writeUserRoleDetails() {
		try {
			new FileUtil().writeTextToFile(
					UIHelper.getTextFromWebElement(driver, btnRoleContributorAddUserGroupInManagePermissionXpath),
					userRoleDetailsFilePath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSaveButtonInManagePermission() {
		try {
			WebElement savePermission = UIHelper.findAnElementbyXpath(driver, btnSaveInManagePermissionXpath);
			UIHelper.highlightElement(driver, savePermission);
			UIHelper.mouseOveranElement(driver, savePermission);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", savePermission);
			report.updateTestLog("Save manage permission changes", "Manage permissions saved successfully",
					Status.PASS);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Save manage permission changes", "Failed to save Manage permissions", Status.FAIL);
		}
	}

	public String getUSerRoleDetailsFromManagePermission() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, btnRoleContributorAddUserGroupInManagePermissionXpath);
		return UIHelper.getTextFromWebElement(driver, btnRoleContributorAddUserGroupInManagePermissionXpath);
	}

	/**
	 * Method to search and find a site by providing site name and select that
	 * site to get in.
	 * 
	 * @return AlfrescoDocumentLibPage
	 * @author 412766
	 */
	public AlfrescoDocumentLibPage findAndSelectSite() {
		try {

			String siteName = dataTable.getData("Sites", "SiteName");

			UIHelper.waitForVisibilityOfEleByXpath(driver, siteSearchInputXpath);
			UIHelper.highlightElement(driver, siteSearchInputXpath);
			UIHelper.findAnElementbyXpath(driver, siteSearchInputXpath).sendKeys(siteName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, siteSearchButtonXpath);
			UIHelper.highlightElement(driver, siteSearchButtonXpath);
			UIHelper.click(driver, siteSearchButtonXpath);
			UIHelper.waitFor(driver);

			String siteNameLinkXPath = "//*[contains(text(),'" + siteName + "')]";
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameLinkXPath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, siteNameLinkXPath))) {
				UIHelper.highlightElement(driver, siteNameLinkXPath);
				UIHelper.click(driver, siteNameLinkXPath);
				UIHelper.waitForPageToLoad(driver);
				report.updateTestLog("To find and select a Site",
						"Site selected successfully" + "<br /><b> Site Name : </b>" + siteName, Status.PASS);
			} else {
				report.updateTestLog("To find and select a Site",
						"Site not found" + "<br /><b> Site Name : </b>" + siteName, Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("To find and select a Site", "To find and select a Site Failed", Status.FAIL);
		}
		return new AlfrescoDocumentLibPage(scriptHelper);
	}

	// Is selected file locked.
	public void isFileLocked(String file) {
		UIHelper.waitForPageToLoad(driver);
		UIHelper.findandAddElementsToaList(driver, FilesAndFolderListXpath, filesAndFoldersList);
		String finalsiteresultXpath = tempSelectedFileXpath.replace("CRAFT", file);
		UIHelper.literateListandhighlightele(driver, filesAndFoldersList, file, finalsiteresultXpath);
		String finalIsLockXpath = tempIsLockXpath.replace("CRAFT", file);
		UIHelper.waitFor(driver);
		// UIHelper.waitFor(driver);
		if (UIHelper.findAnElementbyXpath(driver, finalIsLockXpath).isDisplayed()) {
			report.updateTestLog("File Locked", "Selected file = " + file + "Locked", Status.PASS);
		} else {
			report.updateTestLog("File Locked", "Given file is not Locked", Status.FAIL);
		}
	}

	// Check file in Doc lib
	public boolean checkFileInDocLib(String file) {
		boolean isDisplayedFile = false;
		try {
			String finalsiteresultXpath = tempSelectedFileXpath.replace("CRAFT", file);
			if (UIHelper.checkForAnElementbyXpath(driver, finalsiteresultXpath)) {
				isDisplayedFile = true;
			} else {
				isDisplayedFile = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFile;
	}

	// Is selected file locked.
	public boolean checkFileIsLocked(String file) {
		boolean isDisplayedLockedFile = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.findandAddElementsToaList(driver, FilesAndFolderListXpath, filesAndFoldersList);
			String finalsiteresultXpath = tempSelectedFileXpath.replace("CRAFT", file);
			UIHelper.literateListandhighlightele(driver, filesAndFoldersList, file, finalsiteresultXpath);
			String finalIsLockXpath = tempIsLockXpath.replace("CRAFT", file);
			UIHelper.waitFor(driver);
			// UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, finalIsLockXpath)) {
				isDisplayedLockedFile = true;
			} else {
				isDisplayedLockedFile = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedLockedFile;

	}

	public void verifyLockedBanner(String file) {
		UIHelper.waitForPageToLoad(driver);
		UIHelper.findandAddElementsToaList(driver, FilesAndFolderListXpath, filesAndFoldersList);
		String finalsiteresultXpath = tempSelectedFileXpath.replace("CRAFT", file);
		UIHelper.literateListandhighlightele(driver, filesAndFoldersList, file, finalsiteresultXpath);
		String finalIsLockXpath = fileLockBanner.replace("CRAFT", file);
		UIHelper.waitFor(driver);
		// UIHelper.waitFor(driver);
		if (UIHelper.findAnElementbyXpath(driver, finalIsLockXpath).isDisplayed()) {
			report.updateTestLog("File Locked", " " + file + "Locked Sucessfully", Status.PASS);
		} else {
			report.updateTestLog("File Locked", "Given file is not Locked", Status.FAIL);
		}
	}

	// Cancel the check out of file locked by user by Admin user.
	public void cancelCheck(String file) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, docTextLayerXpath);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, cancelEditXpath));
			UIHelper.waitFor(driver);
			UIHelper.click(driver, cancelEditXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, pageTitleXpath));

			report.updateTestLog("Given locked file is unlocked", "FileName = " + file, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findPublicSite(String siteName) {
		WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
		UIHelper.mouseOveranElement(driver, siteMenuEle);
		UIHelper.highlightElement(driver, siteMenuEle);
		UIHelper.click(driver, siteMenuXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, findSiteXpath);
		UIHelper.click(driver, findSiteXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, searchBoxXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, searchBoxXpath, siteName);
		UIHelper.click(driver, searchButtonXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameListXpath);
		UIHelper.findandAddElementsToaList(driver, siteNameListXpath, siteResultList);

		if (siteResultList.contains(siteName)) {
			String finalsiteresultXpath = tempsiteresultXpath.replace("CRAFT", siteName);
			String finalPublicSiteJoinBtnXpath = publicSiteJoinBtnXpath.replace("CRAFT", siteName);
			if (UIHelper.findAnElementbyXpath(driver, finalPublicSiteJoinBtnXpath).isDisplayed()) {
				UIHelper.iterateListandhighlightele(driver, siteResultList, siteName, finalsiteresultXpath);
				UIHelper.click(driver, finalsiteresultXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, siteTitleXpath);

				report.updateTestLog("Find Site", "SiteName = " + siteName, Status.DONE);
			} else {
				report.updateTestLog("Find Public Site operation", "Given site is not Public", Status.FAIL);
			}

		} else {
			report.updateTestLog("Find Public Site operation", "Given site is not Public", Status.FAIL);
		}

	}

	public AlfrescoSitesPage enterIntoSiteMembers() {

		try {
			UIHelper.click(driver, siteMembersXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchBtnXpath);

			report.updateTestLog("Navigate to Document Library", "Enter into Document Library section", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSitesPage(scriptHelper);

	}

	public AlfrescoSitesPage openSite(String siteName) {
		try {

			List<WebElement> createdSitesEleList = driver.findElements(By.xpath(createdSitesListXpathFromMySites));

			for (WebElement ele : createdSitesEleList) {

				if (ele.getText().equalsIgnoreCase(siteName)) {
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
				}
			}

			report.updateTestLog("Enter into Site", "Enter into site using: " + "Site Name = " + siteName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSitesPage(scriptHelper);
	}

	// Goto Site Member Page
	public void siteMembersPage() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteMembersXpath);
			UIHelper.highlightElement(driver, siteMembersXpath);
			UIHelper.click(driver, siteMembersXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchBtnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void changeMemberPermission(String user, String currentPermission, String requiredPermission) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, adduser);
			UIHelper.waitFor(driver);
			String finalmemberuser = memberuser.replace("CRAFT", user);
			// String selectedPermission =
			// UIHelper.getTextFromWebElement(driver, memberRoleBtnXpath);
			String finaltempChangeToXpath = tempChangeToXpath.replace("CRAFT", requiredPermission);
			UIHelper.click(driver, finalmemberuser);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finaltempChangeToXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, finaltempChangeToXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForPageToLoad(driver);

			if (UIHelper.getTextFromWebElement(driver, finalmemberuser).contains(requiredPermission)) {
				report.updateTestLog("perform Change Member Permission  ",
						"Successfully performed change Member Permission to " + requiredPermission, Status.PASS);
			} else {
				report.updateTestLog("Change Member Permission", "Member Permission change failed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Change Member Permission", "Member Permission change failed", Status.FAIL);
		}
	}

	// Method for change Member Permission
	public void changeMemberPermission() {
		int findLoop = 0;
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, memberRoleBtnXpath);
			UIHelper.click(driver, memberRoleBtnXpath);
			String presentPermission = UIHelper.getTextFromWebElement(driver, memberRoleBtnXpath);
			String splited[] = presentPermission.split("\\s");
			String actualPresentPermission = splited[0];
			String permission1 = "Collaborator";
			String permission2 = "Contributor";
			if (actualPresentPermission.equalsIgnoreCase("Collaborator")) {
				String changePermission = "Contributor";
				String finalContributorXpath = tempXpathForSelectRolesToDropdown.replace("CRAFT", changePermission);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalContributorXpath);
				UIHelper.click(driver, finalContributorXpath);
				findLoop = 1;
			}

			if (actualPresentPermission.equalsIgnoreCase("Contributor")) {
				String changePermission = "Collaborator";
				String finalContributorXpath = tempXpathForSelectRolesToDropdown.replace("CRAFT", changePermission);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalContributorXpath);
				UIHelper.click(driver, finalContributorXpath);
				findLoop = 2;
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			if (findLoop == 1)
				report.updateTestLog("Change Member Permission",
						"Member Permission changed sucessfully From:" + actualPresentPermission + ""
								+ "<br /><b>Expected Result:</b> " + permission2 + ", <br /><b>Actual Result:</b> "
								+ permission2,
						Status.PASS);
			else if (findLoop == 2)
				report.updateTestLog("Change Member Permission",
						"Member Permission changed sucessfully From:" + actualPresentPermission + ""
								+ "<br /><b>Expected Result:</b> " + permission1 + ", <br /><b>Actual Result:</b> "
								+ permission1,
						Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Change Member Permission", "Member Permission change failed", Status.FAIL);
		}

	}

	public void clickOnLinkToLink(String fileNameorFolderName) {
		try {
			String finalXpathForLinkToFolderLink = tempXpathForLinkToFolderLink.replace("CRAFT", fileNameorFolderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForLinkToFolderLink);

			WebElement linkToElement = UIHelper.findAnElementbyXpath(driver, finalXpathForLinkToFolderLink);
			UIHelper.highlightElement(driver, linkToElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", linkToElement);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, movePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnLinkToInLinkPopUpxpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on LinkTo", "Link TO clicked sucessfully", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click on LinkTo", "Link TO failed", Status.FAIL);
		}

	}

	public boolean checkAttachLifecycleOption(String fileOrFolderName) {
		try {
			String finalXpathForAttachLifecycle = tempXpathForAttachLifecycle.replace("CRAFT", fileOrFolderName);
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * finalXpathForAttachLifecycle);
			 */
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAttachLifecycle)) {
				isDisplayedAttachLifecycle = true;
			} else {
				isDisplayedAttachLifecycle = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedAttachLifecycle;
	}

	public boolean checkMoreSettingsOption(String fileOrFolderName, String optionName) {
		try {
			String finalXpathForMoreSettings = tempXpathForMoreSettings.replace("CRAFT", fileOrFolderName)
					.replace("MOREOPT", optionName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, finalXpathForMoreSettings);

			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// finalXpathForMoreSettings);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForMoreSettings)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpathForMoreSettings));
				UIHelper.highlightElement(driver, finalXpathForMoreSettings);
				isDisplayedMoreSettingsOption = true;
			} else {
				isDisplayedMoreSettingsOption = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedMoreSettingsOption;
	}

	public boolean checkAddRelationshipOption(String fileOrFolderName) {
		try {
			String finalXpathForAttachLifecycle = tempXpathForAddRelationship.replace("CRAFT", fileOrFolderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForAttachLifecycle);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForAttachLifecycle)) {
				isDisplayedAddRelationship = true;
			} else {
				isDisplayedAddRelationship = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedAddRelationship;
	}

	public void clickOnDeleteDocumentLink(String fileNameorFolderName) {
		try {
			String finalXpathForDeleteDocumentLink = tempXpathForDeleteDocumentLink.replace("CRAFT",
					fileNameorFolderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDeleteDocumentLink);

			WebElement deleteDocumentElement = UIHelper.findAnElementbyXpath(driver, finalXpathForDeleteDocumentLink);
			UIHelper.highlightElement(driver, deleteDocumentElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", deleteDocumentElement);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, promptBoxXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, thisFileBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on DeleteDocument", "Delete Document clicked sucessfully", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click on DeleteDocument", "Unable to Click DeleteDocument", Status.FAIL);
		}

	}

	// To Click on This File Button
	public void clickOnDeleteThisFile() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, promptBoxXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, thisFileBtnXpath);
			String buttonName = UIHelper.findAnElementbyXpath(driver, thisFileBtnXpath).getText();
			UIHelper.click(driver, thisFileBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on 'This File' Button", "Clicked successfully on:" + buttonName, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click on 'This File' Button", "Unable to Click 'This File' Button", Status.FAIL);
		}

	}

	// Method for click on 'Selected Items' button
	public void clickOnSelectedItems() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsXpath);
			UIHelper.click(driver, selectedItemsXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsMenuXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getSelectedItemMenuOption() {
		try {
			List<WebElement> selectedItemsMenuList = UIHelper.findListOfElementsbyXpath(selectedItemsMenuOptionXpath,
					driver);

			for (WebElement selectedItemOptEle : selectedItemsMenuList) {
				selectedItemsMenuValues.add(selectedItemOptEle.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return selectedItemsMenuValues;
	}

	// Attach Life cycle to file or Folder
	public AlfrescoMyFilesPage attachLifeCycle(String lfeCycNme) {
		String fileName = dataTable.getData("MyFiles", "FileName");

		try {

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					clickOnMoreSetting(fileNameVal);
					clickAttachLifeCycle(fileNameVal);
					WebElement select = UIHelper.findAnElementbyXpath(driver, lfeCycDrpDwnXPath);
					Select dropDown = new Select(select);
					dropDown.selectByVisibleText(lfeCycNme);
					UIHelper.click(driver, btnAttachLifeCycleXPath);
				}
			} else {
				clickOnMoreSetting(fileName);
				clickAttachLifeCycle(fileName);
				Select dropdown = new Select(UIHelper.findAnElementbyXpath(driver, lfeCycDrpDwnXPath));
				dropdown.selectByVisibleText(lfeCycNme);

				UIHelper.click(driver, btnAttachLifeCycleXPath);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// click on attach life cycle Link
	public void clickAttachLifeCycle(String folderName) {
		try {
			String finalXpathForAttachLifecycleFolderLink = tempXpathForAttachLifecycleFolderLink.replace("CRAFT",
					folderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForAttachLifecycleFolderLink);

			WebElement managePermElement = UIHelper.findAnElementbyXpath(driver,
					finalXpathForAttachLifecycleFolderLink);
			UIHelper.highlightElement(driver, managePermElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", managePermElement);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, lstLifeCycleDrpDwnXPath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Life cycle Attached to the Folder
	public boolean verifyAttachedLifeCycle(String folderName, String lfeCycleNme) {

		boolean isLifeCycleAttached = false;
		try {
			String finalXpathForLifeCycAttaLinkInFldr1 = tempXpathForLifeCycAttaLinkInFldr.replace("CRAFT", folderName);
			String finalXpathForLifeCycAttaLinkInFldr = finalXpathForLifeCycAttaLinkInFldr1.replace("Lite",
					lfeCycleNme);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForLifeCycAttaLinkInFldr);
			isLifeCycleAttached = UIHelper.findAnElementbyXpath(driver, finalXpathForLifeCycAttaLinkInFldr)
					.isDisplayed();
			UIHelper.waitFor(driver);
			report.updateTestLog("Verify that life Cycle " + lfeCycleNme + " is attached successfully to the folder -> "
					+ folderName, "Life Cycle attached successfully to the folder ", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify that life Cycle " + lfeCycleNme + " is attached successfully to the folder -> "
					+ folderName, "Life Cycle NOT attached successfully to the folder ", Status.FAIL);
			e.printStackTrace();
		}

		return isLifeCycleAttached;
	}

	// Detach Life cycle from the folder
	public AlfrescoMyFilesPage clickDetachLifeCycle() {

		UIHelper.waitForVisibilityOfEleByXpath(driver, clkDetachLfeCycLnkXPath);
		UIHelper.click(driver, clkDetachLfeCycLnkXPath);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Click View Details
	public void clickViewDetails() {
		String fileName = dataTable.getData("Sites", "FileName");

		String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileName);
		String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT", fileName);
		String finalXPathViewDetXPath = tempXPathFolderViewDetails.replace("CRAFT", fileName);

		UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
		/*
		 * WebElement myFilesTableEle =
		 * driver.findElement(By.xpath(myFilesTablesXpath));
		 * UIHelper.highlightElement(driver, myFilesTableEle);
		 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
		 */

		List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper.findListOfElementsbyXpath(uploadedFilesTitlesXpath,
				driver);

		for (WebElement ele : uploadedFileOrFolderTitleEleList) {
			if (ele.getText().equalsIgnoreCase(fileName)) {
				UIHelper.highlightElement(driver, ele);
				WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
				UIHelper.highlightElement(driver, chkboxElement);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", chkboxElement);
				UIHelper.waitFor(driver);

				WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
				UIHelper.highlightElement(driver, folderEle);
				UIHelper.mouseOveranElement(driver, folderEle);

				WebElement viewDetsEle = UIHelper.findAnElementbyXpath(driver, finalXPathViewDetXPath);
				UIHelper.highlightElement(driver, viewDetsEle);
				executor.executeScript("arguments[0].click();", viewDetsEle);
				UIHelper.waitFor(driver);
				report.updateTestLog("Click on 'View Details' option",
						"Clicked on 'View Details' option for " + fileName + " sucessfully", Status.DONE);
				break;
			}
		}
	}

	// click Promote Link
	public void choosePromoteLfeCycle(String toPromteVal) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, promteLfeCycleXPath);
			UIHelper.click(driver, promteLfeCycleXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prmtLifDrpDwnXPath);

			WebElement select = UIHelper.findAnElementbyXpath(driver, prmtLifDrpDwnXPath);
			Select dropDown = new Select(select);
			dropDown.selectByValue(toPromteVal);
			UIHelper.click(driver, promoteOKbutton);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Return actual promote Life Cycle Value displayed
	public String returnActStateVal() {
		String valActualPromteVal = "";

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, actPrmteValXPath);
			valActualPromteVal = UIHelper.findAnElementbyXpath(driver, actPrmteValXPath).getText();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return valActualPromteVal;
	}

	public void clickAndChoosePrmteFromMore(String toPromteVal) {

		String fileName = dataTable.getData("MyFiles", "FileName");
		String finalMorePromteXPath = promteLfeCycleMoreXPath.replace("CRAFT", fileName);
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalMorePromteXPath);
			UIHelper.click(driver, finalMorePromteXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prmtLifDrpDwnXPath);

			WebElement select = UIHelper.findAnElementbyXpath(driver, prmtLifDrpDwnXPath);
			Select dropDown = new Select(select);
			dropDown.selectByValue(toPromteVal);
			UIHelper.click(driver, promoteOKbutton);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickAndChooseDemoteFromMore(String toDemoteVal) {

		String fileName = dataTable.getData("MyFiles", "FileName");
		String finalMoreDemoteXPath = demoteLfeCycleMoreXPath.replace("CRAFT", fileName);
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalMoreDemoteXPath);
			UIHelper.click(driver, finalMoreDemoteXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, demtLifDrpDwnXPath);

			WebElement select = UIHelper.findAnElementbyXpath(driver, demtLifDrpDwnXPath);
			Select dropDown = new Select(select);
			dropDown.selectByValue(toDemoteVal);
			UIHelper.click(driver, demoteOKbutton);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void chooseDemoteLfeCycle(String toDemoteVal) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, demoteLfeCycleXPath);
			UIHelper.click(driver, demoteLfeCycleXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, demtLifDrpDwnXPath);

			WebElement select = UIHelper.findAnElementbyXpath(driver, demtLifDrpDwnXPath);
			Select dropDown = new Select(select);
			dropDown.selectByVisibleText(toDemoteVal);
			UIHelper.click(driver, demoteOKbutton);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To select an Item from Select Items Dropdown
	public void verifySelectedItemsMenuOption(String selectedItemMenuOptVal) {
		try {
			selectedOption = selectedItemFromMenuOptionXpath.replace("CRAFT", selectedItemMenuOptVal);
			System.out.println(selectedOption);

			selectedItemsMenuValues = getSelectedItemMenuOption();

			for (String actualVal : selectedItemsMenuValues) {
				System.out.println(actualVal);
				if (actualVal.equalsIgnoreCase(selectedItemMenuOptVal)) {
					UIHelper.click(driver, selectedOption);
					report.updateTestLog("Click on Link To", "Clicked on Link to sucessfully", Status.PASS);

				}

			}
		} catch (Exception e) {

			report.updateTestLog("Click on Link To", "Failed to click on Link to ", Status.FAIL);
			e.printStackTrace();
		}

	}

	// Create site from MySiteDashlet
	public void mySiteDashletCreatSiteLink(String siteName, String isReqWithTimeStamp) {
		try {
			String siteDesc = dataTable.getData("Sites", "SiteDescription");
			String siteType = dataTable.getData("Sites", "SiteType");
			String siteVisibleOption = dataTable.getData("Sites", "SiteVisibilityFor");

			String currentDateValue = DateUtil.getCurrentDateAndTime();

			if (isReqWithTimeStamp.equalsIgnoreCase("Yes")) {
				finalName = siteName + currentDateValue.replace(" ", "").replace("/", "_").replace(":", "_");
			} else {
				finalName = siteName;
			}

			new FileUtil().writeTextToFile(finalName, testOutputFilePath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, createSitePopupXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, siteNameFieldXpath, finalName);
			UIHelper.sendKeysToAnElementByXpath(driver, siteDescFieldXpath, siteDesc);
			UIHelper.selectValueFromDropdown(driver, siteTypeDropdownXpath, siteType);

			selectSiteVisibileOptAndSubmitSiteDetails(siteVisibleOption, finalName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To click on Options in More Link

	public AlfrescoMyFilesPage clickOnMoreLinkOptions(String fileOrFolderName, String propertyName) {
		try {

			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForFileORFolderPropOptionLink = fileORFolderPropertOtionXpath
					.replace("CRAFT", fileOrFolderName).replace("PROPNAME", propertyName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			/*
			 * WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);

					JavascriptExecutor executor = (JavascriptExecutor) driver;

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement fileOrFolderPropEle = UIHelper.findAnElementbyXpath(driver,
							finalXpathForFileORFolderPropOptionLink);
					UIHelper.highlightElement(driver, fileOrFolderPropEle);
					executor.executeScript("arguments[0].click();", fileOrFolderPropEle);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);

					report.updateTestLog("Click on File or Folder settings:</br><b>Option Name</b>: " + propertyName,
							"User able to click the file or folder property: " + propertyName, Status.PASS);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// To select an Item from Select Items Dropdown
	public void selectItemFromSelectedItemsMenuOption(String selectedItemMenuOptVal) {
		try {
			selectedOption = selectedItemFromMenuOptionXpath.replace("CRAFT", selectedItemMenuOptVal);

			selectedItemsMenuValues = getSelectedItemMenuOption();
			System.out.println(selectedItemMenuOptVal);
			for (String actualVal : selectedItemsMenuValues) {
				if (actualVal.contains(selectedItemMenuOptVal)) {
					UIHelper.click(driver, selectedOption);
					report.updateTestLog("Click on Selected Item",
							"Clicked on " + selectedItemMenuOptVal + " sucessfully", Status.PASS);
				}
			}
		} catch (Exception e) {

			report.updateTestLog("Click on selected Item", "Failed to click on Selected Item ", Status.FAIL);
			e.printStackTrace();
		}

	}

	public void clickOnDeleteAllLinks() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, promptBoxXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, allLinksBtnXpath);
			UIHelper.click(driver, allLinksBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on 'All Links' Button", "Clicked successfully", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Click on 'All Links' Button", "Unable to Click 'All Links' Button", Status.FAIL);
		}

	}

	// Click on Create Site
	public void clickOnCreateSite() {

		try {

			WebElement createSiteLinkEle = UIHelper.findAnElementbyXpath(driver, creatSiteXpath);
			UIHelper.highlightElement(driver, createSiteLinkEle);

			if (UIHelper.checkForAnWebElement(createSiteLinkEle)) {
				UIHelper.click(driver, creatSiteXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, createSitePopupXPath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameFieldXpath);
				UIHelper.waitFor(driver);

				report.updateTestLog("Click on 'Create Site' link",
						"User clicked on 'Create Site' link and 'Create Site' popup opened successfully", Status.DONE);
			} else {
				report.updateTestLog("Click on 'Create Site' link", "User failed to clickon 'Create Site' link",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on 'Create Site' link", "User failed to clickon 'Create Site' link",
					Status.FAIL);
		}
	}

	public ArrayList<String> getActualCreateSiteLabels() {
		try {

			List<WebElement> lableEleList = UIHelper.findListOfElementsbyXpath(xpathForCreateSiteLabels, driver);

			for (WebElement ele : lableEleList) {
				listOfCreateSitFieldLabels.add(ele.getText());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfCreateSitFieldLabels;

	}

	public boolean isDisplayedCreateSite() {
		return UIHelper.checkForAnElementbyXpath(driver, createSitePopupXPath);
	}

	public void clickOnUserDashboardCreateSite() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, xpathForUserDashboard);
			WebElement userDashCreateSiteEle = UIHelper.findAnElementbyXpath(driver, userDashCreateSiteXpath);
			UIHelper.highlightElement(driver, userDashCreateSiteEle);

			UIHelper.click(driver, userDashCreateSiteXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameFieldXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Navigate To Create site Window form User Dashboard",
					"Navigated to Create site Window form User Dashboard successfully", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Navigate To Create site Window form User Dashboard",
					"Unable to navigate to Create site Window form User Dashboard", Status.FAIL);

		}

	}

	public ArrayList<String> getActualCreateSiteTypeValues() {
		try {

			WebElement typDropdownEle = UIHelper.findAnElementbyXpath(driver, siteTypeDropdownXpath);
			UIHelper.highlightElement(driver, typDropdownEle);
			UIHelper.click(driver, siteTypeDropdownXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			List<WebElement> typeValues = UIHelper.findListOfElementsbyXpath(siteTypeDropdownXpath, driver);

			for (WebElement ele : typeValues) {
				listOfCreateSitTypeValues.add(ele.getText().trim());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(listOfCreateSitTypeValues);
		return listOfCreateSitTypeValues;

	}

	// To Find Option in More Link Options

	public boolean findOptionInMoreLinkOptions(String fileOrFolderName, String propertyName) {
		boolean flag = false;
		try {

			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForFileORFolderPropOptionLink = fileORFolderPropertOtionXpath
					.replace("CRAFT", fileOrFolderName).replace("PROPNAME", propertyName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			/*
			 * WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement fileOrFolderPropEle = UIHelper.findAnElementbyXpath(driver,
							finalXpathForFileORFolderPropOptionLink);
					if (UIHelper.isWebElementDisplayed(fileOrFolderPropEle)) {
						UIHelper.highlightElement(driver, fileOrFolderPropEle);
						flag = true;
					} else {
						flag = false;
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Refresh on Site Dashboard page
	public void refreshSitesPage() {
		try {
			driver.navigate().refresh();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteMenuXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param siteType
	 * @return
	 */
	public boolean isSiteTypeAvailable(String siteType) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitedropdownArrow);
			UIHelper.highlightElement(driver, sitedropdownArrow);
			UIHelper.click(driver, sitedropdownArrow);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, tempsiteTypeDropdownXpath);
			List<WebElement> typDropdownElems = UIHelper.findListOfElementsbyXpath(tempsiteTypeDropdownXpath, driver);
			for (WebElement siteTypeValue : typDropdownElems) {
				if (siteTypeValue.getText().equals(siteType)) {
					UIHelper.highlightElement(driver, siteTypeValue);
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public ArrayList<String> getSiteTypeValues() {
		ArrayList<String> typeValuesList = new ArrayList<String>();
		try {
			UIHelper.waitFor(driver);
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, siteTypeDropdownXpath),
					typeValuesList);
		} catch (Exception e) {

		}
		return typeValuesList;
	}

	// Add Relationship from Relationship Widget
	public void addRelationshipFromWidget(String relationshipName, String fileName) {
		try {
			String finalXpathForFile = tempXpathForFile.replace("CRAFT", fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addRelationWidXPath);
			UIHelper.click(driver, addRelationWidXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath)) {
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);
				UIHelper.click(driver, finalXpathForFile);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
				UIHelper.click(driver, createRelationBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Relationship from Relationship Browse Options
	public void addRelationshipFromBrowseOpt(String relationshipName, String fileName) {
		try {
			String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");

			String finalXpathForFile = tempXpathForFile.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath)) {
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);
				UIHelper.click(driver, finalXpathForFile);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
				UIHelper.click(driver, createRelationBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Added Relationship data for a file",
						"User added relationship data to a file '" + fileName + "' successfully "
								+ "</br><b>Expected Result:</b> " + fileName + "</br><b>Actual Result:</b> " + fileName,
						Status.PASS);

			}
		} catch (Exception e) {
			report.updateTestLog("Verify Added Relationship data for a file",
					"User added relationship data to a file '" + fileName + "' successfully "
							+ "</br><b>Expected Result:</b> " + fileName + "</br><b>Actual Result:</b> " + fileName,
					Status.FAIL);
		}
	}

	public AlfrescoMyFilesPage clickOnMoreSettingBrw(String fileOrFolderName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForMoreOptionLink = tempXpathForMoreOptionLink.replace("CRAFT", fileOrFolderName);
			String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
			String finalXpathAddRel = tempXpathForMoreOptionAddRel.replace("CRAFT", fileOrFolderName)
					.replace("MORE_OPT", docActionVal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			/*
			 * WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);

					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement moreSettingsEle = UIHelper.findAnElementbyXpath(driver, finalXpathForMoreOptionLink);
					UIHelper.highlightElement(driver, moreSettingsEle);
					executor.executeScript("arguments[0].click();", moreSettingsEle);
					UIHelper.waitFor(driver);

					WebElement addRelEle = UIHelper.findAnElementbyXpath(driver, finalXpathAddRel);
					UIHelper.highlightElement(driver, addRelEle);
					executor.executeScript("arguments[0].click();", addRelEle);
					UIHelper.waitFor(driver);

					report.updateTestLog("Click on 'More Settings' Link Option",
							"User successfully clicked the <b> 'More Settings'</b> Link Option", Status.DONE);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Check created site in Site Finder Results
	public boolean isDisplayedSiteNameInSiteFinderResults(String siteName) {

		try {
			WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
			UIHelper.mouseOveranElement(driver, siteMenuEle);
			UIHelper.highlightElement(driver, siteMenuEle);
			UIHelper.click(driver, siteMenuXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInSiteMenu);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingImgIconXpath);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, findSiteXpath);
			} catch (Exception e) {
			}
			report.updateTestLog("Click on Sites Menu",
					"User able to click on Sites Menu" + "<br /><b> Page Title : </b>" + "Sites Menu", Status.DONE);

			UIHelper.click(driver, findSiteXpath);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, searchBoxXpath);
			} catch (Exception e) {
			}
			report.updateTestLog("Click on 'Site Finder'", "User able to click on 'Site Finder' option in Sites Menu"
					+ "<br /><b> Page Title : </b>" + "Site Finder", Status.DONE);

			UIHelper.sendKeysToAnElementByXpath(driver, searchBoxXpath, siteName);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, searchButtonXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchingXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on 'Search Button'", "User able to click on 'Search Button'", Status.DONE);

			UIHelper.findandAddElementsToaList(driver, siteNameListXpath, siteResultList);
			if (siteResultList.size() > 0 && siteResultList.contains(siteName)) {
				isDisplayedSiteNameInSFResults = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedSiteNameInSFResults;
	}

	// To get Site Name that was created
	public String getCreatedSiteName() {
		String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e) {
		}

		return siteName;
	}

	// To get Site Name that was created copy
	public String getCreatedSiteName2() {
		String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath2);
		} catch (Exception e) {
		}

		return siteName;
	}

	// TO Check Folder of file existence
	public Boolean CheckFolderOrFile(String file) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalFileXpath = tempFileXpath.replace("CRAFT", file);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileXpath);
			if (UIHelper.findAnElementbyXpath(driver, finalFileXpath).isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// To Send Join request for a site
	public void requestToJoinSite() {
		try {
			String siteName = new FileUtil().readDataFromFile(testOutputFilePath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, requestToJoinBtn);
			UIHelper.click(driver, requestToJoinBtn);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Request to Join Site",
					"Successfully sent Join Request for <br>" + "<b>Site Name :</b>" + siteName, Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Request to Join Site", "Failed to send Join Request for", Status.FAIL);
		}

	}

	// To write SiteName to File
	public void writeSiteNameToFile(String siteNameValue) {

		try {
			new FileUtil().writeTextToFile(siteNameValue, testOutputFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To write SiteName to File
	public void writeSiteNameToFile2(String siteNameValue) {

		try {
			new FileUtil().writeTextToFile(siteNameValue, testOutputFilePath2);
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
			UIHelper.waitForVisibilityOfEleByXpath(driver, lstLifeCycleDrpDwnXPath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify document available
	public boolean documentAvailable(String file) {

		boolean flag = false;
		try {

			List<WebElement> listOfValues = driver.findElements(By.xpath(tempFileListXpath));

			for (WebElement values : listOfValues) {
				UIHelper.scrollToAnElement(values);
				if (values.getText().equalsIgnoreCase(file)) {
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// Click on MySite Link

	public void clickMySiteLink() {

		UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteXPath);

		UIHelper.click(driver, mySiteXPath);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, mySitePgXPath);

	}

	// majeed
	// Verify Site has Enable Feed Option
	public void enableOrDisableFeedForSite(String SiteNme) {

		try {
			boolean flag = false;
			String btnDisableSiteFeedXPathFinal = btnDisableSiteFeedXPath.replace("CRAFT", SiteNme);
			String btnEnableSiteFeedXpathFinal = btnEnableSiteFeedXPath.replace("CRAFT", SiteNme);
			System.out.println("" + btnDisableSiteFeedXPathFinal);
			System.out.println("" + btnEnableSiteFeedXpathFinal);

			if (UIHelper.checkForAnElementbyXpath(driver, btnEnableSiteFeedXpathFinal)) {

				flag = true;

			}

			if (flag) {
				UIHelper.click(driver, btnEnableSiteFeedXpathFinal);
				UIHelper.highlightElement(driver, btnEnableSiteFeedXpathFinal);
				report.updateTestLog("Verify the User can Enable Activity Feeds in the My Site Page",
						"User successfully Enabled Activity Feeds for the Site - " + SiteNme, Status.DONE);
			}

			else {
				report.updateTestLog("Verify the User can Enable Activity Feeds in the My Site Page",
						"User successfully Enabled Activity Feeds by default for the Site - " + SiteNme, Status.DONE);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get recent sites
	public ArrayList<String> getSitesFromRecentSites() {

		navigateToSitesTab();

		ArrayList<String> sitesList = new ArrayList<String>();
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteFilesLinkXpath);
			UIHelper.waitFor(driver);
			List<WebElement> recentSiteElementsList = driver.findElements(By.xpath(recentSitesList));

			for (WebElement recentSiteEle : recentSiteElementsList) {
				sitesList.add(recentSiteEle.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sitesList;
	}

	// Get Page Title Text
	public String getSitePageTitle() {
		String pageTitleVal = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, pageTitleXpath)) {
				pageTitleVal = UIHelper.getTextFromWebElement(driver, pageTitleXpath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageTitleVal;
	}

	// Check published URL field in edit properties pop up
	public void checkPublishedURLfield() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, publishedURLXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, publishedURLXpath)) {
				report.updateTestLog("Verify Published URL field displayed",
						"Published URL field displayed successfully", Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Published URL field displayed", "Failed to display Published URL field",
					Status.FAIL);
		}
	}

	// Check published URL field in edit properties pop up
	public void checkPublishedURL(String format, String fileName) {
		try {

			String result = getStringFromClipboard(publishedURLValXpath);

			String urlFormat[] = format.split(";");
			if (result != null) {
				for (String urlform : urlFormat) {
					if (!urlform.isEmpty()) {
						if (result.contains(urlform)) {
							report.updateTestLog("Verify Format in publish URL", "Format matches with publish URL",
									Status.PASS);
							if (result.contains(fileName)) {
								report.updateTestLog("Verify filename in publish URL",
										"filename matches with publish URL", Status.PASS);

							} else {
								report.updateTestLog("Verify filename in publish URL",
										"File Name mismatches in publish URL", Status.FAIL);
							}

						} else {
							report.updateTestLog("Verify Format in publish URL", "format mismatches in publish URL",
									Status.FAIL);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Format and filename in publish URL",
					"Failed. format and File Name mismatches ", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param xPath
	 * @return
	 * @throws AWTException
	 * @throws UnsupportedFlavorException
	 * @throws IOException
	 */
	public String getStringFromClipboard(String xPath) {
		String result = "";

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, xPath);
			UIHelper.highlightElement(driver, xPath);
			UIHelper.mouseOverandElementdoubleClick(driver, UIHelper.findAnElementbyXpath(driver, xPath));
			UIHelper.waitFor(driver);

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_A);
			UIHelper.waitFor(driver);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_C);

			Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
			result = (String) c.getData(DataFlavor.stringFlavor);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public String getPublishedURL() {
		String publishedURL = "";
		try {
			publishedURL = getStringFromClipboard(publishedURLValXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publishedURL;
	}

	// Check not published URL field present for folder in edit properties pop
	// up
	public void checkFolderPublishURLfield() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (!UIHelper.checkForAnElementbyXpath(driver, publishedURLXpath)) {
				report.updateTestLog("Verify Published URL field", "Published URL field not displayed for folder.",
						Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Published URL field", "Published URL field displayed for folder.",
					Status.FAIL);
		}
	}

	// To check Join request Button for a site
	public void requestToJoinSiteButton() {
		try {
			String siteName = new FileUtil().readDataFromFile(testOutputFilePath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteInSiteFinder);
			UIHelper.waitForVisibilityOfEleByXpath(driver, requestToJoinBtn);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, requestToJoinBtn)))
				report.updateTestLog("Request to Join Site Button",
						"Button displayed Sucessfully  <br>" + "<b>Site Name :</b>" + siteName, Status.PASS);

			else
				report.updateTestLog("Request to Join Site Button",
						"Button failed to display <br>" + "<b>Site Name :</b>" + siteName, Status.FAIL);

		} catch (Exception e) {
			report.updateTestLog("Request to Join Site", "Failed to send Join Request for", Status.FAIL);
		}

	}

	public void verifySiteMember(String user, String role) {
		try {
			String finalSiteMemberListXpath = siteMembersListXpath.replace("USER", user).replace("ROLE", role);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalSiteMemberListXpath);
			UIHelper.highlightElement(driver, finalSiteMemberListXpath);

			report.updateTestLog("Verify user and role",
					"User <b>" + user + "</b> is member with role <b>" + role + "</b>", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify user and role",
					"User <b>" + user + "</b> is not a member with role <b>" + role + "</b>", Status.FAIL);
		}

	}

	// Click on 'Calendar' Button
	public void clickOnCalendarBtn() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, calendarBtnXpath);
			UIHelper.click(driver, calendarBtnXpath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, addEventBtnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on 'Wiki' Button
	public void clickOnWikiBtn() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, wikiBtnXpath);
			UIHelper.click(driver, wikiBtnXpath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, newPageBtnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on 'Discussion' Button
	public void clickOnDiscussionBtn() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, discussionBtnXpath);
			UIHelper.click(driver, discussionBtnXpath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, newTopicBtnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on 'Blog' Button
	public void clickOnBlogBtn() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, blogBtnXpath);
			UIHelper.click(driver, blogBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, newPostBtnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on 'Link' Button
	public void clickOnLinkBtn() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, LinkBtnXpath);
			UIHelper.click(driver, LinkBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, newLinkBtnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTestOutputFilePath() {
		return testOutputFilePath;
	}

	public void setTestOutputFilePath(String testOutputFilePath) {
		this.testOutputFilePath = testOutputFilePath;
	}

	// Check site member method
	public boolean checkSiteMember(String user, String role) {
		boolean flag = false;
		try {
			String finalSiteMemberListXpath = siteMembersListXpath.replace("USER", user).replace("ROLE", role);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalSiteMemberListXpath);
			UIHelper.highlightElement(driver, finalSiteMemberListXpath);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;

	}

	// Check more option avail
	public boolean checkMoreOptionsAvil(String fileOrFolderName) {
		boolean flag = false;
		try {

			String finalXpathForMoreOptionLink = tempXpathForMoreOpt.replace("CRAFT", fileOrFolderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, tempXpathForMoreOptSection);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, tempXpathForMoreOptSection));
			UIHelper.highlightElement(driver, tempXpathForMoreOptSection);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMoreOptionLink);
			if (UIHelper.findAnElementbyXpath(driver, finalXpathForMoreOptionLink).isDisplayed()) {
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, finalXpathForMoreOptionLink));
				UIHelper.highlightElement(driver, finalXpathForMoreOptionLink);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}

	// Check all more option avail
	public boolean checkAllMoreOptionsAvil(String fileOrFolderName, String options) {
		boolean flag = false;
		try {
			String[] allOptions = options.split(",");

			alloptionlist = Arrays.asList(allOptions);
			String finalOptionValXpath = optionValXpath.replace("CRAFT", fileOrFolderName);
			List<WebElement> optionsValList = UIHelper.findListOfElementsbyXpath(finalOptionValXpath, driver);

			for (WebElement option : optionsValList) {
				moreOptions.add(option.getText());
			}

			Collections.sort(alloptionlist);
			moreOptionsList.addAll(alloptionlist);
			Collections.sort(moreOptions);

			if (UIHelper.compareTwoDiffSizeOfLists(moreOptionsList, moreOptions)) {
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

	// Check download option avail in Version history
	public boolean checkDownloadOptAvailInVersionHistory(String version) {
		boolean flag = false;
		try {

			String finalXpathForDownloadLink = tempXpathForDownloadFrmVersionHistory.replace("CRAFT", version);

			UIHelper.waitForVisibilityOfEleByXpath(driver, versionHistoryXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, versionHistoryXpath));

			if (UIHelper.findAnElementbyXpath(driver, finalXpathForDownloadLink).isDisplayed()) {
				UIHelper.highlightElement(driver, finalXpathForDownloadLink);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}

	// Check file preview is avail
	public boolean checkFilePreview() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, filePreviewXpath);

			if (UIHelper.findAnElementbyXpath(driver, filePreviewXpath).isDisplayed()) {
				UIHelper.highlightElement(driver, filePreviewXpath);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}

	public void clickOnUserRoleManager() {
		try {
			UIHelper.findAnElementbyXpath(driver, tempXpathForRoleBtn).click();
			WebElement userRoleEle = UIHelper.findAnElementbyXpath(driver, managerRoleBtnXpath);
			UIHelper.highlightElement(driver, userRoleEle);
			UIHelper.mouseOveranElement(driver, userRoleEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", userRoleEle);

			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click View Details
	public void clickDetailOpt(String file) {

		String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", file);
		UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
		/*
		 * WebElement myFilesTableEle =
		 * driver.findElement(By.xpath(myFilesTablesXpath));
		 * UIHelper.highlightElement(driver, myFilesTableEle);
		 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
		 */

		WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);

		List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper.findListOfElementsbyXpath(uploadedFilesTitlesXpath,
				driver);

		for (WebElement ele : uploadedFileOrFolderTitleEleList) {
			if (ele.getText().equalsIgnoreCase(file)) {
				UIHelper.highlightElement(driver, ele);
				UIHelper.highlightElement(driver, chkboxElement);

				if (chkboxElement.isEnabled()) {
					UIHelper.highlightElement(driver, chkboxElement);
					report.updateTestLog("Verify Locate File ", "Locate file working successfully", Status.PASS);
				} else {
					report.updateTestLog("Verify Locate File ", "Locate file failed", Status.PASS);
				}

				break;
			}
		}
	}

	// Perform remove current site from favourite siteSites page
	public void removeCurrentSiteFromFavourites() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, removeCurrentSiteFromFavouriteXpath);
			WebElement removeCurrentSiteFromFavouriteEle = UIHelper.findAnElementbyXpath(driver,
					removeCurrentSiteFromFavouriteXpath);
			UIHelper.mouseOveranElement(driver, removeCurrentSiteFromFavouriteEle);
			UIHelper.highlightElement(driver, removeCurrentSiteFromFavouriteEle);
			UIHelper.click(driver, removeCurrentSiteFromFavouriteXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on 'Remove current site from Favorites' from Site menu",
					"User able to click 'Remove current site from Favorites'", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on the select document to Navigate to Document Details Package for
	// colloection
	public void documentdetailsColl(String file) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalFileXpath = tempCollFileXpath.replace("CRAFT", file);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, finalFileXpath)) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileXpath);
				UIHelper.click(driver, finalFileXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
					UIHelper.click(driver, docActionTwisterCloseXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
					UIHelper.waitFor(driver);
				}

				report.updateTestLog("Open Document",
						"Document details page displayed using" + "<br /><b> File Name :</b>" + file, Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Open Document", "Document Details Page not found", Status.FAIL);
		}
	}

	// Method for find site using 'Site Finder'

	private String skillsAndStandardsXpath = "//*[contains(@class,'yui-dt-data')]//*[contains(@class,'yui-dt-rec')]//a[1]";

	public void siteFinderSkillsAndStandards(String siteName) {
		WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
		UIHelper.mouseOveranElement(driver, siteMenuEle);
		UIHelper.highlightElement(driver, siteMenuEle);
		UIHelper.click(driver, siteMenuXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, findSiteXpath);
		UIHelper.click(driver, findSiteXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, searchBoxXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, searchBoxXpath, siteName);
		UIHelper.click(driver, searchButtonXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchingXpath);
		if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, skillsAndStandardsXpath))) {
			UIHelper.click(driver, skillsAndStandardsXpath);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, documentLibraryXpath);

			report.updateTestLog("Find Input Site", "Given site is located." + "<br /><b>SiteName :</b> " + siteName,
					Status.PASS);

		} else {
			homePageObj.navigateToSitesTab();
			// openSiteFromRecentSites(siteName);
			report.updateTestLog("Find Input Site",
					"Given site not found or too many site found." + "<br /><b>SiteName :</b> " + siteName,
					Status.FAIL);
		}

	}

	// TO add recipients and access policy in proofhq
	public void addProofHQ(String recepients, String policy, String fileOrFolderName) {
		try {
			UIHelper.waitFor(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, createProofHQPopupHeaderXpath);
			} catch (Exception e) {
			}

			/*
			 * UIHelper.highlightElement(driver, proofHQRecipientsFieldXpath);
			 * UIHelper.click(driver, proofHQRecipientsFieldXpath);
			 * UIHelper.waitFor(driver);
			 */

			UIHelper.highlightElement(driver, proofhqRecepientsXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, proofhqRecepientsXpath, recepients);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, selectResultXpathForEmail);
			UIHelper.waitFor(driver);

			UIHelper.selectbyVisibleText(driver, proofhqAcesspolicyDropdownXpath, policy);
			UIHelper.waitFor(driver);

			UIHelper.click(driver, proofhqSubmitButton);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, proofHQStatusIndicatorXpath)) {
				report.updateTestLog(
						"Create Proof in ProofHQ for a " + fileOrFolderName, "Proof created successfully"
								+ "<br><b>Recepients Name : </b>" + recepients + "<br><b>policy Name : </b>" + policy,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Create Proof in ProofHQ for a " + fileOrFolderName, "Proof failed to create"
								+ "<br><b>Recepients Name : </b>" + recepients + "<br><b>policy Name : </b>" + policy,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Site menu bar item
	public void clickOnSiteNavMenuBarItem(String menuBarItem, String minutes, String negOrPoscase) {
		try {

			String finalXpathForSiteNavigationMenuBar = tempXpathForSiteNavigationMenuBar.replace("CRAFT", menuBarItem);

			UIHelper.waitForPageToLoad(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteNavigationMenuBar);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSiteNavigationMenuBar)) {
				UIHelper.click(driver, finalXpathForSiteNavigationMenuBar);

				report.updateTestLog("Click on '" + menuBarItem + "'",
						"User clicked the '" + menuBarItem + "' @ " + minutes + " time", Status.DONE);

				report.updateTestLog("Verify the current time",
						"Current Time: " + new DateUtil().getCurrentDateAndTime(), Status.DONE);

				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);

				if (negOrPoscase.equalsIgnoreCase("Negative")) {
					if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSiteNavigationMenuBar)) {
						report.updateTestLog("Navigate To '" + menuBarItem + "' Page", "Navigated to '" + menuBarItem
								+ "' Page successfully" + "<br /><b> Page Title : </b>" + "Home", Status.PASS);
					} else {
						report.updateTestLog("Navigate To '" + menuBarItem + "' Page",
								"Failed to navigate to '" + menuBarItem + "' Page", Status.FAIL);
					}
				} else if (negOrPoscase.equalsIgnoreCase("Positive")) {
					if (UIHelper.checkForAnElementbyXpath(driver, userNameFieldXpath)) {
						report.updateTestLog("Verify login page", "Login page displayed successfully", Status.PASS);
					} else {
						report.updateTestLog("Verify login page", "Failed to display login page", Status.FAIL);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TO add recipients and access policy in proofhq
	public void addProofHQNegative(String recepients, String policy, String Role) {
		try {
			UIHelper.waitFor(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, createProofHQPopupHeaderXpath);
			} catch (Exception e) {
			}

			UIHelper.highlightElement(driver, proofhqRecepientsXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, proofhqRecepientsXpath, recepients);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, selectResultXpathForEmail);
			UIHelper.waitFor(driver);

			UIHelper.selectbyVisibleText(driver, proofhqAcesspolicyDropdownXpath, policy);
			UIHelper.waitFor(driver);

			UIHelper.click(driver, proofhqSubmitButton);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prooferrmsg);
			String error = UIHelper.getTextFromWebElement(driver, prooferrmsg);

			if (error.contains("error")) {

				report.updateTestLog("Verfiy warning message displayed for " + Role + " role ",
						Role + " role should not be able to create a proof via alfresco. Error : " + error,
						Status.PASS);

			} else {
				report.updateTestLog("Verfiy warning message displayed for " + Role + " role",
						Role + " role should not be able to create a proof via alfresco", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verfiy warning message displayed for " + Role + " role",
					Role + " role should not be able to create a proof via alfresco", Status.FAIL);
		}
	}

	/**
	 * @author 317085
	 * @param relationshipName
	 * @param fileOrFolderName
	 * @param siteName
	 */
	public void addRelationshiptositefolder(String relationshipName, String siteDestFileOrFolderName, String siteName) {
		try {
			String finalXpathForSiteFolder = temprlnXapthForFF.replace("CRAFT", siteDestFileOrFolderName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath)) {
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);

				/*
				 * UIHelper.waitForVisibilityOfEleByXpath(driver,
				 * documentLibXpath); UIHelper.highlightElement(driver,
				 * documentLibXpath); UIHelper.click(driver, documentLibXpath);
				 */

				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteFolder);
				UIHelper.highlightElement(driver, finalXpathForSiteFolder);
				UIHelper.click(driver, finalXpathForSiteFolder);

				UIHelper.waitFor(driver);
				UIHelper.click(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
				UIHelper.click(driver, createRelationBtnXpath);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				} catch (Exception e) {
				}

				report.updateTestLog("Apply Relationship for a file/folder in differnt site",
						"Relationship Applied successfully" + "<br><b>Relationship Name : </b>" + relationshipName
								+ "<br><b>File/Folder Name : </b>" + siteDestFileOrFolderName
								+ "<br><b>Site Name : </b>" + siteName,
						Status.PASS);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Relationship for a file/folder in other site",
					"Apply Relationship for a file/folder in other site Failed", Status.FAIL);
		}
	}

	// Click on Add/User group Button with paramter
	public void clickOnAddUserGroupButton(String userName) {
		try {
			// String userName = dataTable.getData("Sites", "InviteUserName");
			UIHelper.click(driver, btnAddUserGroupInManagePermissionXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, txtboxSearchAddUserGroupInManagePermissionXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, txtboxSearchAddUserGroupInManagePermissionXpath, userName);

			UIHelper.click(driver, btnSearchAddUserGroupInManagePermissionXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchLoadingXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchLoadingXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsAddUserGroupInManagePermissionXpath);

			String finalUserFromSearchResultsXpath = btnAddInAddUserGroupInManagePermissionXpath.replace("CRAFT",
					userName);

			WebElement clickOnElement = UIHelper.findAnElementbyXpath(driver, finalUserFromSearchResultsXpath);
			UIHelper.highlightElement(driver, clickOnElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", clickOnElement);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on 'Add User Group' button", "User able to click the 'Add User Group' button",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on 'Add User Group' button",
					"User not able to click the 'Add User Group' button", Status.FAIL);

		}
	}

	// User permission role setting
	public void clickOnUserRole(String role, String userName) {
		try {

			String finalRoleBtnXpath = RoleBtnXpath.replace("CRAFT", role);
			String finalRoleBtn = tempRoleBtnXpath.replace("REPLACE", userName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalRoleBtn);
			UIHelper.findAnElementbyXpath(driver, finalRoleBtn).click();
			WebElement userRoleEle = UIHelper.findAnElementbyXpath(driver, finalRoleBtnXpath);
			UIHelper.highlightElement(driver, userRoleEle);
			UIHelper.mouseOveranElement(driver, userRoleEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", userRoleEle);

			UIHelper.waitFor(driver);

			report.updateTestLog("Select User Role", role + " User role have been selected", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select User Role", role + " User role selection failed", Status.FAIL);

		}
	}

	// To Send Join request for a site
	public void sitefinderoption(String value, String site) {
		try {
			String finalsiteresultXpath = tempsiteresultXpath.replace("CRAFT", site);
			String finalsitefinderoption = sitefinderoption.replace("CRAFT", site).replace("REPLACE", value);

			UIHelper.waitForVisibilityOfEleByXpath(driver, searchBoxXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, searchBoxXpath, site);
			Thread.sleep(2000);
			UIHelper.click(driver, searchButtonXpath);
			Thread.sleep(2000);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			siteResultList.clear();
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchingXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalsiteresultXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, finalsiteresultXpath)) {
				UIHelper.highlightElement(driver, finalsiteresultXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.click(driver, finalsitefinderoption);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Click " + value + "button for site",
						"Click " + value + "button for site successfully", Status.PASS);
			} else {
				report.updateTestLog("Click " + value + "button for site", "Click " + value + "button for site failed",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Click " + value + "button for site", "Click " + value + "button for site failed",
					Status.FAIL);
		}

	}

	public void addRelationshipInDifSiteinsidefoler(String relationshipName, String fileName, String siteName) {
		try {
			String finalXpathForFile = tempXpathForFile.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath))
				UIHelper.highlightElement(driver, relationshipDropdownXpath);
			{
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, addRelationshipBtnXpath);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);

				String documentLibXpath = ".//*[@id='relationship-docPicker-cntrl-picker-navigator-button']";
				String siteXpath = ".//*[text()='Sites']";
				String loadingXpath = ".//*[contains(@class,'yui-dt-liner') and text()='Loading...']";
				String siteNameXpath = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td//h3/a[text()='CRAFT']";
				String docLibButtonXpath = ".//*[text()='documentLibrary']";
				// String docLibButtonXpath =
				// ".//*[text()='documentLibrary']//following::td//a[@title='Add']";

				String finalsiteNameXpath = siteNameXpath.replace("CRAFT", siteName);

				UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
				UIHelper.highlightElement(driver, documentLibXpath);
				UIHelper.click(driver, documentLibXpath);

				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, siteXpath);
				UIHelper.click(driver, siteXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalsiteNameXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalsiteNameXpath));
				UIHelper.highlightElement(driver, finalsiteNameXpath);
				UIHelper.click(driver, finalsiteNameXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, docLibButtonXpath);
				UIHelper.highlightElement(driver, docLibButtonXpath);
				UIHelper.click(driver, docLibButtonXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);
				UIHelper.highlightElement(driver, finalXpathForFile);
				UIHelper.click(driver, finalXpathForFile);

				UIHelper.waitFor(driver);
				UIHelper.click(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
				UIHelper.click(driver, createRelationBtnXpath);

				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				report.updateTestLog("Apply Relationship for a file/folder in differnt site",
						"Relationship Applied successfully" + "<br><b>Relationship Name : </b>" + relationshipName
								+ "<br><b>File Name : </b>" + fileName + "<br><b>Site Name : </b>" + siteName,
						Status.PASS);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Relationship for a file/folder in other site",
					"Apply Relationship for a file/folder in other site Failed", Status.FAIL);
		}
	}

	public void addRelationshipInMyfiles(String relationshipName, String fileName, String siteName, String Username) {
		try {
			String finalXpathForFile = tempXpathForFile.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath))
				UIHelper.highlightElement(driver, relationshipDropdownXpath);
			{
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, addRelationshipBtnXpath);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);

				String documentLibXpath = ".//*[@id='relationship-docPicker-cntrl-picker-navigator-button']";
				String siteXpath = ".//*[text()='Sites']";
				String CompanyXpath = ".//*[text()='Company Home']";
				String loadingXpath = ".//*[contains(@class,'yui-dt-liner') and text()='Loading...']";
				String siteNameXpath = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td//h3/a[text()='CRAFT']";
				String docLibButtonXpath = ".//*[text()='documentLibrary']";
				// String docLibButtonXpath =
				// ".//*[text()='documentLibrary']//following::td//a[@title='Add']";
				String useridxpath = ".//*[@class='yui-dt-data']//*[text()='" + Username + "']";
				String UserhomesXpath = ".//*[text()='User Homes']";
				String finalsiteNameXpath = siteNameXpath.replace("CRAFT", siteName);

				UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
				UIHelper.highlightElement(driver, documentLibXpath);
				UIHelper.click(driver, documentLibXpath);

				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, CompanyXpath);
				UIHelper.click(driver, CompanyXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, UserhomesXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, UserhomesXpath));
				UIHelper.highlightElement(driver, UserhomesXpath);
				UIHelper.click(driver, UserhomesXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, useridxpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, useridxpath));
				UIHelper.highlightElement(driver, useridxpath);
				UIHelper.click(driver, useridxpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);
				UIHelper.highlightElement(driver, finalXpathForFile);
				UIHelper.click(driver, finalXpathForFile);

				UIHelper.waitFor(driver);
				UIHelper.click(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
				UIHelper.click(driver, createRelationBtnXpath);

				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				report.updateTestLog("Apply Relationship for a file/folder in differnt site",
						"Relationship Applied successfully" + "<br><b>Relationship Name : </b>" + relationshipName
								+ "<br><b>File Name : </b>" + fileName + "<br><b>Site Name : </b>" + siteName,
						Status.PASS);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Relationship for a file/folder in other site",
					"Apply Relationship for a file/folder in other site Failed", Status.FAIL);
		}
	}

	public void addRelationshipInSharedfiles(String relationshipName, String fileName, String siteName) {
		try {
			String finalXpathForFile = tempXpathForFile.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipDropdownXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, relationshipDropdownXpath))
				UIHelper.highlightElement(driver, relationshipDropdownXpath);
			{
				UIHelper.selectbyVisibleText(driver, relationshipDropdownXpath, relationshipName);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, addRelationshipBtnXpath);
				UIHelper.click(driver, addRelationshipBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);

				String documentLibXpath = ".//*[@id='relationship-docPicker-cntrl-picker-navigator-button']";
				String siteXpath = ".//*[text()='Sites']";
				String CompanyXpath = ".//*[text()='Company Home']";
				String loadingXpath = ".//*[contains(@class,'yui-dt-liner') and text()='Loading...']";
				String siteNameXpath = ".//*[@id='relationship-docPicker-cntrl-picker-left']//table//tbody//tr/td//h3/a[text()='CRAFT']";
				String docLibButtonXpath = ".//*[text()='documentLibrary']";
				// String docLibButtonXpath =
				// ".//*[text()='documentLibrary']//following::td//a[@title='Add']";

				String SharedXpath = ".//*[text()='Shared']";

				String finalsiteNameXpath = siteNameXpath.replace("CRAFT", siteName);

				UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
				UIHelper.highlightElement(driver, documentLibXpath);
				UIHelper.click(driver, documentLibXpath);

				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, CompanyXpath);
				UIHelper.click(driver, CompanyXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, SharedXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, SharedXpath));
				UIHelper.highlightElement(driver, SharedXpath);
				UIHelper.click(driver, SharedXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForFile);
				UIHelper.highlightElement(driver, finalXpathForFile);
				UIHelper.click(driver, finalXpathForFile);

				UIHelper.waitFor(driver);
				UIHelper.click(driver, okBtnXpathForRelationshipPopup);
				UIHelper.waitFor(driver);

				UIHelper.waitForVisibilityOfEleByXpath(driver, createRelationBtnXpath);
				UIHelper.click(driver, createRelationBtnXpath);

				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				report.updateTestLog("Apply Relationship for a file/folder in differnt site",
						"Relationship Applied successfully" + "<br><b>Relationship Name : </b>" + relationshipName
								+ "<br><b>File Name : </b>" + fileName + "<br><b>Site Name : </b>" + siteName,
						Status.PASS);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Relationship for a file/folder in other site",
					"Apply Relationship for a file/folder in other site Failed", Status.FAIL);
		}
	}

	public void isrelationshipimagedisplayed(String filename) {

		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, relationshipImageXpath))) {
				UIHelper.highlightElement(driver, relationshipImageXpath);
				report.updateTestLog("<b>verify Relationship Image Size",
						"<b>Relationship Image" + filename + " is not Narrow", Status.PASS);
			} else {
				report.updateTestLog("<b>verify Relationship Image Size",
						"<b>Relationship Image" + filename + "  is  Narrow", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify document name and return
	public String documentname(String file) {

		String value = null;
		try {

			List<WebElement> listOfValues = driver.findElements(By.xpath(tempFileListXpath));

			for (WebElement values : listOfValues) {
				UIHelper.scrollToAnElement(values);
				if (values.getText().contains(file)) {
					value = values.getText();
					break;
				} else {
					value = null;
				}
			}
		} catch (Exception e) {
			value = null;
		}
		return value;
	}

	public void checkConfigurationOptionNotAvailable(String configName) {

		boolean isConfigurationNameAvailable = false;

		UIHelper.waitForVisibilityOfEleByXpath(driver, siteConfigurationOptionDropdownXpath);
		UIHelper.highlightElement(driver, siteConfigurationOptionDropdownXpath);
		UIHelper.click(driver, siteConfigurationOptionDropdownXpath);
		UIHelper.waitFor(driver);

		ArrayList<String> configurationList = new ArrayList<String>();
		String attributeValue = dataTable.getData("MyFiles", "BrowseActionName");

		UIHelper.findAndAddElementsAttributeValueToaList(driver, siteConfigurationOptionsListXpath, configurationList,
				attributeValue);

		for (String name : configurationList) {
			if (name.equalsIgnoreCase(configName)) {
				isConfigurationNameAvailable = true;
				break;
			}

		}

		if (!isConfigurationNameAvailable) {
			report.updateTestLog("Site Configuration Name in  'Site Page",
					"Site Configuration Name not present" + "<br /><b> Configuration Name : </b>" + configName
							+ "<br /><b> Page Title : </b>" + "Site DashBoard",
					Status.PASS);

		} else {
			report.updateTestLog("Site Configuration Name in  'Site Page",
					"Site Configuration Name present" + "<br /><b> Configuration Name : </b>" + configName
							+ "<br /><b> Page Title : </b>" + "Site DashBoard",
					Status.FAIL);

		}

	}

	public void turnOffInheritPermission() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.highlightElement(driver, inheritedButtonXpath);
			UIHelper.click(driver, inheritedButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, turnOffPermissionXpath);
			UIHelper.highlightElement(driver, clickYesOnTurnoffXpath);
			UIHelper.click(driver, clickYesOnTurnoffXpath);
			report.updateTestLog("Click TurnOff Inherit permission", "TurnOff Inherit permission is clicked",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click TurnOff Inherit permission", "TurnOff Inherit permission is not clicked",
					Status.FAIL);
		}
	}

	public ArrayList<String> getLifeCycleLabelText() {
		ArrayList<String> lifeCyclelabeItems = new ArrayList<String>();
		try {
			lifeCyclelabeItems.add(UIHelper.getTextFromWebElement(driver, tempXpathForlifeCycleLabelXPath));
			lifeCyclelabeItems.add(UIHelper.getTextFromWebElement(driver, tempXpathForlifeCycleSelectdrpdwnLabelXpath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lifeCyclelabeItems;
	}

	public ArrayList<String> getButtonNameInLifeCycle() {
		ArrayList<String> lifeCyclebuttonItems = new ArrayList<String>();
		try {
			lifeCyclebuttonItems.add(UIHelper.getTextFromWebElement(driver, btnAttachLifeCycleXPath));
			lifeCyclebuttonItems.add(UIHelper.getTextFromWebElement(driver, btnCancelLifeCycleXPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lifeCyclebuttonItems;
	}
	public ArrayList<String> getPromteLifeCycleLabelText() {
		ArrayList<String> promtlifeCyclelabeItems = new ArrayList<String>();
		try {
			promtlifeCyclelabeItems.add(UIHelper.getTextFromWebElement(driver, tempXpathForPromLifeCycLabel));
			List<WebElement> promLabelItems= UIHelper.findListOfElementsbyXpath(tempXpathForPromoteLables, driver);
			for (WebElement webElem : promLabelItems) {
				promtlifeCyclelabeItems.add(webElem.getText().trim());
			}
			
		} catch (Exception e) {                                               
			e.printStackTrace();
		}
		return promtlifeCyclelabeItems;
	}

	public ArrayList<String> getPromteButtonNameInLifeCycle() {
		ArrayList<String> promtlifeCyclebuttonItems = new ArrayList<String>();
		try {
			promtlifeCyclebuttonItems.add(UIHelper.getTextFromWebElement(driver, promoteOKbutton ));
			promtlifeCyclebuttonItems.add(UIHelper.getTextFromWebElement(driver, promoteCancelButton));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promtlifeCyclebuttonItems;
	}
	public ArrayList<String> getDemteLifeCycleLabelText() {
		ArrayList<String> demotelifeCyclelabeItems = new ArrayList<String>();
		try {
			
			demotelifeCyclelabeItems.add(UIHelper.getTextFromWebElement(driver, tempXpathForDemtLifeCycLabel));
			List<WebElement> promLabelItems= UIHelper.findListOfElementsbyXpath(tempXpathForDemtLabels, driver);
			for (WebElement webElem : promLabelItems) {
				demotelifeCyclelabeItems.add(webElem.getText().trim());
			}
			
		} catch (Exception e) {                                               
			e.printStackTrace();
		}
		return demotelifeCyclelabeItems;
	}
	
	public ArrayList<String> getDemoteButtonNameInLifeCycle() {
		ArrayList<String> demotlifeCyclebuttonItems = new ArrayList<String>();
		try {
			demotlifeCyclebuttonItems.add(UIHelper.getTextFromWebElement(driver, demoteOKbutton ));
			demotlifeCyclebuttonItems.add(UIHelper.getTextFromWebElement(driver, demoteCancelButton));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return demotlifeCyclebuttonItems;
	}

}
