package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

/**
 * FlightFinderPage class
 * 
 * @author Cognizant
 */
public class AlfrescoHomePage extends ReusableLibrary {
	public String searchmodifierXpath = ".//*[@name='prop_cm_modifier']";
	private String homeTabLinkXpath = ".//*[@id='HEADER_HOME_text']/a";
	private String actualHomeTabValue;
	public String commonPageTitle = ".//*[@id='HEADER_TITLE']/span";
	//public String tempUserOption = ".//a[text()='CRAFT']";
	public String tempUserOption = ".//tr[@title='CRAFT']";
	public String tempUserOpt = ".//a[@title='CRAFT']";
	private String myFilesTabLinkXpath = ".//*[@id='HEADER_MY_FILES_text']/a";
	private String sharedFilesTabLinkXpath = ".//*[@id='HEADER_SHARED_FILES']/span/a";
	private String repositoryTabLinkXpath = ".//*[@id='HEADER_REPOSITORY_text']/a";
	/*Changed for release 1.9.3*/
	private String commonPageTitleXpath = ".//*[@id='HEADER_TITLE']//span";
	private String siteUserMenuXpath = ".//*[@id='HEADER_USER_MENU_POPUP']";
	private String siteUserMenuHelpXpath = ".//*[@id='HEADER_USER_MENU_HELP_text']";
	private String docLibLoadingMsgXpath = ".//*[@id='message']/div";
	private String adminTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String mySitesXapth = ".//*[@id='HEADER_SITES_MENU_MY_SITES_text']/a";
	private String menuOptXpath = ".//*[@aria-label='CRAFT ']";
	private String messageEleXpath = ".//*[@id='message']/div";

	private String siteMenuXpath = ".//*[@id='HEADER_SITES_MENU']";
	private String creatSiteXpath = ".//*[@id='HEADER_SITES_MENU_CREATE_SITE_text']";

	private String taskMenuXpath = ".//*[@id='HEADER_TASKS_text']";

	private String taskMyTasksXpath = ".//*[@id='HEADER_MY_TASKS_text']/a";

	private String mySiteXpath = ".//*[@class='sticky-wrapper']//*[@class='yui-gd grid columnSize2']//table[@id='yuievtautoid-0']//*[@class='yui-dt-data']";

	private String searchFieldXpath = ".//*[@id='HEADER_SEARCHBOX_FORM_FIELD']";
	private String searchImageIconXpath = ".//*[@id='HEADER_SEARCH_BOX_DROPDOWN_MENU']/img";
	private String advancedSearchXpath = ".//*[@id='HEADER_SEARCH_BOX_ADVANCED_SEARCH_text']/a";
	private String searchBox = ".//*[@id='HEADER_SEARCHBOX_FORM_FIELD']";

	private String cmpltdTaskinMyTaskDashlet = ".//span[3]/a[contains(text(),'Completed Tasks')]";

	private String viewCmpltdTaskXPath = ".//*[@title='View Task']";
	private String drpDwnFltrdTaskDispXPath = ".//*[@title='Edit Task']";

	private String createSiteLnk = "//a[contains(@id,'default-createSite-button')]";
	private String createSitePopUp = "//button[@id='alfresco-ppc-createSite-instance-cancel-button-button']";

	private String userMenuXpath = ".//*[@id='HEADER_USER_MENU_POPUP_text']";
	private String userProfileXpath = ".//*[@id='HEADER_USER_MENU_PROFILE_text']/a";
	public String attachmentXpath = "//*[contains(@class,'dashlet my-tasks')]//tbody[contains(@class,'yui-dt-data')]//tr//td[2]//h3//a[text()='CRAFT']//ancestor::tr//h4[1]/span";
	private String dueDateXpathForTasks = ".//*[contains(@class,'dashlet my-tasks')]//span[@class='task-delayed']";
	private String taskNameXpathInHomeDashlet = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-tasks')]//table[contains(@id,'yuievtautoid')]//tbody[contains(@class,'yui-dt-data')]//tr//td[2]//h3/a";
	private String taskLinksXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-tasks')]//table[contains(@id,'yuievtautoid')]//tbody[contains(@class,'yui-dt-data')]//tr//td[2]//h3/a";
	private String taskFilterDropdownValXpath = "//a[contains(.,'CRAFT')]";
	private String taskFilterDropdownXpath = ".//*[@class='title' and text()='My Tasks']//following::div//*[contains(@id,'default-filters-button')]";
	private String firstTaskInMyTasksDashlet = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-tasks')]//table[contains(@id,'yuievtautoid')]//tbody[contains(@class,'yui-dt-data')]//tr[1]//td[2]//h3";
	private String tasksContainerInMyTasksDashlet = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-tasks')]//*[@class='body scrollableList']";
	private String subTaskContainerXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-tasks')]//*[@class='body scrollableList']/div";
	private String titlenameindocPreviewXpath = "//*[@class='node-header']//img[@title]";
	private boolean isDisplayedDueDateForAllTasks;
	private boolean isDisplayedMyTask;

	public String isBulkDashletAvailable = "//div[contains(@class,'bulk-job-status')]";
	private String bulkgetJobsBtn = "//div[contains(@class,'bulk-job-status')]//button[text()='Get Jobs']";
	private String bulkDurationbutton = ".//*[contains(@id,'default-job-duration-button-button')]";
	private String bulkDuration30Days = ".//div[contains(@class,'bulk-job-status')]//*[@class='first-of-type']//a[text()='Last 30 Days']";
	private String bulkDurations = ".//div[contains(@class,'bulk-job-status')]//*[@class='first-of-type']//a[text()='CRAFT']";
	private String bulkstatusbutton = ".//*[contains(@id,'default-job-status-button-button')]";
	private String bulkRowCount = ".//*[@class='search-bar']";
	private String bulkTypeButton = ".//*[contains(@id,'default-job-type-button-button')]";
	private String bulkDownloadTypeJobs = ".//div[contains(@style, 'visibility: visible')]//a[text()='Download']";
	private String bulkUploadTypeJobs = ".//div[contains(@style, 'visibility: visible')]//a[text()='Upload']";

	private String bulkLoadingGifXpath = ".//*[contains(@id,'default-search-bar')]/img";
	private String bulkIDData = ".//div[contains(@class,'bulk-job-status')]//table//tbody[2]/tr/td[1]/div";
	private String bulkTypeData = ".//div[contains(@class,'bulk-job-status')]//table//tbody[2]/tr/td[2]/div";
	private String bulkStartDate = ".//div[contains(@class,'bulk-job-status')]//table//tbody[2]/tr/td[3]/div";
	private String bulkCompletionDate = ".//div[contains(@class,'bulk-job-status')]//table//tbody[2]/tr/td[4]/div";
	private String bulkCommentsdata = ".//div[contains(@class,'bulk-job-status')]//table//tbody[2]/tr/td[5]/div";
	private String bulkStatusdata = ".//div[contains(@class,'bulk-job-status')]//table//tbody[2]/tr/td[6]/div";
	private String bulkSizedata = ".//div[contains(@class,'bulk-job-status')]//table//tbody[2]/tr/td[7]/div";
	private String bulkActiondata = ".//div[contains(@class,'bulk-job-status')]//table//tbody[2]/tr/td[8]/div";
	private String bulkActionbutton = ".//div[contains(@class,'bulk-job-status')]//table//tbody[2]/tr/td[8]//button";
	private String bulkStatusButton = ".//*[contains(@id,'default-job-status-button-button')]";
	private String bulkFailedJobs = ".//div[contains(@style, 'visibility: visible')]//a[text()='Failed']";
	private String sitenamecheckXpath = ".//img[@class='validationInProgress']";

	private Map<String, ArrayList<String>> bulkJobValues = new LinkedHashMap<String, ArrayList<String>>();

	public ArrayList<String> bulkIDList = new ArrayList<String>();
	public ArrayList<String> bulkTypeList = new ArrayList<String>();
	public ArrayList<String> bulkStartDateList = new ArrayList<String>();
	public ArrayList<String> bulkCompletionDateList = new ArrayList<String>();
	public ArrayList<String> bulkCommentList = new ArrayList<String>();
	public ArrayList<String> bulkStatusList = new ArrayList<String>();
	public ArrayList<String> bulkSizeList = new ArrayList<String>();
	public ArrayList<String> bulkActionList = new ArrayList<String>();

	/** Add Dashlet ***/
	private String tomoveDashColXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[contains(@id,'column-ul-CRAFT')][@class='usedList']";
	private String drpdwnValXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[text()='My Tasks']//following-sibling::div[1]//*[contains(@id,'yui-gen')]//*[@class='bd']/ul/li/a[contains(.,'CRAFT')]";

	private String customDashBtnXPath = ".//*[@id='HEADER_CUSTOMIZE_USER_DASHBOARD']/img";
	private String addDashletBtnXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//button[contains(.,'Add Dashlets')]";
	private String sitesettingbtn = ".//*[@id='HEADER_SITE_CONFIGURATION_DROPDOWN']/span[2]";
	private String editsiteoverlay = ".//*[@id='EDIT_SITE_DIALOG_title']";
	private String edittitle = ".//*[@name='title']";
	private String editokbtn = ".//*[@id='EDIT_SITE_DIALOG_OK_label']";
	private String sitesettingopt = ".//*[@aria-labelledby='HEADER_SITE_CONFIGURATION_DROPDOWN_text']//*[text()='CRAFT']";
	private String okBtnDashXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//button[contains(.,'OK')]";

	private String addDashValXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[@class='availableList']/li[contains(.,'CRAFT')]";

	/*** Remove Dashlet ****/
	private String remvDashColumXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[contains(@id,'CRAFTSys')][@class='usedList']/li[contains(.,'CRAFTLite')]";
	private String remvdDashletHomePgXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(text(),'CRAFT')]";

	private String movetoDashXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[@class='availableList']";

	private boolean remvdDashVerFlag;
	private String addedDashletHomePgXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(text(),'CRAFT')]";
	private String movetoRemvDashXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[@class='availableList']";

	private String siteFinderXapth = ".//*[@id='HEADER_SITES_MENU_SITE_FINDER_text']/a";
	private String overDueTaskNameXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='body scrollableList']//*[@class='yui-dt-data']//tr//td[2]//div//h3//a";
	private String overDueTaskDateXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='body scrollableList']//*[@class='yui-dt-data']//tr//td[2]//div//h4[2]/span";
	private String myTasksegmentXpath = ".//*[@class='sticky-wrapper']//*[@class='body scrollableList']//*[@class='alfresco-datatable yui-dt']";

	// private String taskMyWFTaskXpath =
	// ".//*[@id='HEADER_MY_WORKFLOWS_text']";
	private String taskMyWFTaskXpath = ".//*[@id='HEADER_MY_WORKFLOWS_text']/a";
	private String adminPageXpath = ".//*[@id='HEADER_ADMIN_CONSOLE_text']";

	/*** Bulkjob Sort ***/
	private String sortStartDateXpath = ".//*[@class='yui-dt-liner']//a[contains(.,'Start date')]";
	private String sortCompDateXpath = "..//*[@id='yui-dt162-th-completeDate-liner']//a";
	/*** Instant Site Search ***/
	private String instantSiteResultXpath = ".//*[@class='alf-livesearch right']";
	private String instantSiteSeacrhXpath = ".//*[contains(@class,'alf-live-search-sites-title')]//following::div//*[@class='alf-livesearch-item']//a[text()='CRAFT']";
	public int openTasksSize;
	public int favoriteSitesCount;

	private String creatSiteXPath = ".//a[contains(@id,'createSite')]";
	public int tasksDueDateCount;

	private String myActivitiesDashletHeaderXpath = ".//*[@id='bd']//*[contains(@id,'yui-gen')]/*[contains(.,'My Activities')]";
	private String todayTitleXpathInMyactivitiesDashlet = ".//*[@id='bd']//*[contains(@id,'yui-gen')]/*[contains(.,'My Activities')]//following-sibling::div[contains(@id,'default-activityList')]//span[contains(.,'Today')]";

	private String firstThreeActivitiesElXpath = ".//*[contains(@id,'default-activityList')]//div[2][@class='activity']//*[@class='content']/span[1] | .//*[contains(@id,'default-activityList')]//div[3][@class='activity']//*[@class='content']/span[1] | .//*[contains(@id,'default-activityList')]//div[4][@class='activity']//*[@class='content']/span[1]";

	private String myActivityPanelXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[contains(@id,'_default-activityList')]";
	private String myActivityListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[contains(@id,'_default-activityList')]/div/div/span[1]";
	private String myActivityDropDownXpath = ".//*[contains(@id,'_dashboard_x0023_default-user-button')]";
			//.//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='dashlet activities resizable yui-resize']//*[contains(@id,'_dashboard_x0023_default-user-button')]";
	private String myActivitytimeDropDownXpath = ".//*[contains(@id,'_dashboard_x0023_default-range-button')]";
	String myActivityDropDownValueTempXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[@class='bd']//ul//li//a[contains(.,'CRAFT')]";
	String myActivityDropDownValuetodayTempXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[@class='bd']//ul//li//a[contains(.,'today')]";
	private String mySitesPanelXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='body scrollableList yui-dt']";
	private String mySitesListXapth = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='body scrollableList yui-dt']//table//tbody[2]//tr//td[2]//div//h3//a";
	private String startWorkFlowXpath = ".//*[contains(@id,'yui-gen')]//span/a[contains(.,'Start Workflow')]";
	private String createSiteLinkXpath = ".//*[contains(@id,'yui-gen')]//span/a[contains(.,'Create Site')]";
	private String sitecontentDashletHeaderXpath = "//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(text(),'Site Content')]";

	private String repositoryMenuLinkXpath = ".//*[@id='HEADER_REPOSITORY_text']/a";
	private String tempXpathForDueDateInMyTasksDashlet = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-tasks')]//table[contains(@id,'yuievtautoid')]//tbody[contains(@class,'yui-dt-data')]//tr//td[2]//h3[contains(.,'CRAFT')]//following-sibling::h4[2]";

	private String myTaskRemoteSiteLoadingXpath = ".//*[@class='title' and text()='My Tasks']";

	private long startTime;
	private long endTime;
	private long differenceTime;

	private ArrayList<String> myActivitiesList = new ArrayList<String>();
	private boolean isDisplayedBlueTint;
	private boolean isDisplayedReqTaskInMyTasksDashlet;

	private String custSiteListArrowXpath = ".//*[@id='HEADER_SITE_CONFIGURATION_DROPDOWN']/span[2]";
	private String custDashboardXpath = ".//*[@id='HEADER_CUSTOMIZE_SITE_DASHBOARD_text']/a";
	private String dashletHeaderXpath = ".//*[@id='bd']/div/div/div/div/div/div[contains(text(),'CRAFT')]//ancestor::div[1]";
	private String mySearchesXpath = ".//*[contains(@class,'dashlet saved-searches')]//div[2]//span//input[1]";
	private String allSearchesXpath = ".//*[contains(@class,'dashlet saved-searches')]//div[2]//span//input[2]";
	public String savedSearchlistXpath = ".//*[@id='saved-searches-body']/table//span/a";
	public String savedSearchDelXpath = ".//*[@id='saved-searches-body']/table//span/a[text()='CRAFT']//ancestor::table//td[3]/a";
	public String savedSearchConXpath = ".//*[@class='button-group']/span//button[text()='Delete']";
	public String myFilesListXpath = ".//*[contains(@id,'default-sites')]/table//h3/a";
	public String loadingXpath = ".//*[@id='saved-searches-body']//span[contains(.,'Loading')]";

	private String moreSrchXPath = ".//*[@id='link-to-detailedPage']";
	private String allSrchXPath = ".//*[@id='allSearches']/a/span";
	private String lstAllSrchXPath = ".//td[contains(@class,'searchDisplayName')]/div/a";
	public String savedSearchDesclistXpath = ".//*[@id='saved-searches-body']/table//tr//td[2]";

	private String myTaskPagesXpath = ".//*[@id='bd']//*[@id='yui-main']//*[@class='yui-u']//a[contains(@class,'yui-pg-page')]";
	private String nextPageXpath = "//a[@title='Next Page']";

	private String messageXpath = ".//*[@id='message']/div";

	private String mySitesDashletFilterDropdownXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-sites')]//span/span/button";
	private String mySitesDashletFilterDropdownValXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-sites')]//*[@class='bd']/ul/li/a[contains(.,'CRAFT')]";
	private String myDocumentsDashletFilterDropdownXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-documents')]//span/span/button[contains(@id,'default-filters-button')]";
	private String myDocumentsDashletFilterDropdownValXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-documents')]//*[@class='bd']/ul/li/a[contains(.,'CRAFT')]";
	private String documentsContainerXpathInMyDocumentsDashlet = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-documents')]//*[contains(@class,'body scrollableList')]";
	private String firstDocumentItemXpathInMyDocumentsDashletForAll = ".//*[contains(@class,'dashlet my-documents')]//table//tr[1]/td[2]//h3/a";

	private String sitesContainerXpathInMySitesDashlet = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet my-sites')]//*[contains(@class,'body scrollableList')]";
	private String firstSiteItemXpathInMySitesDashletForAll = ".//*[contains(@class,'dashlet my-sites')]//table//tr[1]/td//a[contains(@title,'Add site to favorites')]";
	private String firstFavoriteSiteXpathInMySitesDashlet = ".//*[contains(@class,'dashlet my-sites')]//table//tr[1]/td//a[contains(@title,'Remove site from favorites')]";
	private String listOfFavoriteSitesXpath = ".//*[contains(@class,'dashlet my-sites')]//table//tr/td//a[contains(@title,'Remove site from favorites')]";
	private String listOfsitesHeaderLinkXpath = ".//*[contains(@class,'dashlet my-sites')]//table//tr/td//h3/a";

	// PCS dashlet value list
	public String pcsValListXpath = ".//*[@id='folderListTable-pcs']/tbody//tr//td//span";

	private String unFavoriteSitesXpathInMySitesDashlet = ".//*[contains(@class,'dashlet my-sites')]//table//tr/td//a[contains(@title,'Add site to favorites')]";
	private String tempXpathForSiteUnFavoriteLink = ".//*[contains(@class,'dashlet my-sites')]//table//tr/td//h3[contains(.,'CRAFT')]//ancestor::td//a[contains(@title,'Add site to favorites')]";
	private String tempXpathForSiteFavoriteLink = ".//*[contains(@class,'dashlet my-sites')]//table//tr/td//h3[contains(.,'CRAFT')]//ancestor::td//a[contains(@title,'Remove site from favorites')]";
	private String mySitesCurrentDrodownValXpath = ".//button[contains(@id,'default-type-button')]";
	private String tempXpathForSelectedSiteNextSiteItem = ".//*[contains(@class,'dashlet my-sites')]//table//tr//a[contains(.,'CRAFT')]//ancestor::tr//following-sibling::tr[1]/td//h3/a";
	private String favouriteSiteXpathFromMySitesDashlet = ".//*[contains(@class,'dashlet my-sites')]//table//tr/td//a[contains(@title,'Remove site from favorites')]";
	private String unfavouriteSiteXpathFromMySiteDashlet = ".//*[contains(@class,'dashlet my-sites')]//table//tr/td//a[contains(@title,'Add site to favorites')]";
	public int allSitesCount;

	// Word to xml
	private String linkXpath = ".//table//tr//td[text()='CRAFT']//ancestor::tr//td//a";
	private String successXpath = ".//p[contains(text(),'succeeded')]";
	private String failedXpath = ".//p[contains(text(),'failed')]";

	private String tempXpathForDashletInHomePage = ".//*[contains(@id,'yui-gen')]/*[text()='CRAFT']";
	private String listOfMyDocumentsXpath = ".//*[contains(@class,'dashlet my-documents')]//table//tr/td[2]//h3/a";
	private String tempXpathForDocumentItem = ".//*[contains(@class,'dashlet my-documents')]//table//tr/td[2]//h3/a[contains(.,'CRAFT')]";

	private String contentEditingHeaderXpath = ".//*[contains(@id,'_default-my-docs-dashlet')]//div[contains(text(),'Content I')]";
	private String contentEditingListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='dashlet resizable yui-resize']//*[contains(@id,'_default-document-template')]//h4/following-sibling::*";
	private String sitecontentListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='dashlet docsummary resizable yui-resize']//*[contains(@id,'_default-documents')]//h3/a";
	private String contentEditingFileNameListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='dashlet resizable yui-resize']//*[contains(@id,'_default-document-template')]//h4/a";
	// My tasks dashlet
	private String editTaskIconXpath = ".//*[@class='yui-dt-data']//tr//td[3]//a[1]";
	private String viewTaskIconXpath = ".//*[@class='yui-dt-data']//tr//td[3]//a[2]";
	private String myTasksLabelXpath = ".//*[@class='title' and contains(.,'My Tasks')]";
	private String taskListXpath = ".//*[@class='title' and contains(.,'My Tasks')]//ancestor::div[1]//table//tr[1]//td[2]//h3//a";

	private String currentPageNatorXpath = ".//*[contains(@id,'default-paginator')]/span[@class='yui-pg-current']";
	private String nextPageLinkXpath = ".//*[contains(@id,'default-paginator')]/a[contains(@title,'Next Page')]";
	private String myTaskDashletPageCountMessage = "START - END of TOTAL_TASKS";
	private String tempXpathForPageNatorMsg = ".//*[contains(@id,'default-paginator')]/span[contains(@class,'yui-pg-current') and contains(.,'CRAFT')]";

	private String lodingXpathForDocLib = ".//*[@class='yui-dt-message']/tr/td/*[contains(.,'Loading the Document Library')]";
	private String loadingXpathInSiteMenu = ".//*[contains(@class,'alf-dropdown-menu')]/*[@class='dijitReset']/tr/td[contains(.,'Loading')]";

	//private String uploadBtnXpath = ".//*[@class='file-upload']//span//button[contains(.,'Upload')]";
	private String uploadBtnXpath =".//*[@class='file-upload']//span[contains(@id,'fileUpload-button')]//button";

	private String loadingXpathInMySitesDashlet = ".//*[contains(@id,'default-sites')]//*[@class='yui-dt-message']//*[contains(@class,'yui-dt-liner') and contains(.,'Loading')]";

	//private String userDropDownXpath = ".//*[@id='HEADER_USER_MENU_POPUP']/span[@class='alf-menu-arrow']";
	private String userDropDownXpath = ".//*[@id='HEADER_USER_MENU_POPUP']/span[@class='alfresco-menus-AlfMenuBarPopup__arrow']";
	private String alfrescoForumMenuXpath = ".//*[@id='HEADER_USER_FAQS_text']/a[text()='Alfresco Forum']";
	private String trainingMenuXapth = ".//*[@id='TRAINING_text']/a[text()='Training']";

	private String tempXpathForMenuHeaderName = ".//*[@id='HEADER_APP_MENU_BAR']//a[contains(.,'CRAFT')]";

	private String userNameFieldXpath = ".//*[@id='UsernameTextBox']";
	
	public String dashletWFXpath = ".//div[text()='My Tasks']//following-sibling::div[1][@class='toolbar flat-button']//*[@class='align-right yui-button-align']//a";
	public String searchCountXpath = ".//*[@id='FCTSRCH_RESULTS_COUNT_LABEL']";
	//public String searchCountXpath = ".//*[@id='FCTSRCH_RESULTS_COUNT_LABEL']//b";
	public String searchingXpath = ".//*[@class='info data-loading-more']";
	
	
	public String livesearchblock = ".//div[@class='alf-livesearch-context']";
	public String liveRepsearch = ".//div[@data-dojo-attach-point='contextRepositoryNode']";
	public String livesitesearch = ".//div[@data-dojo-attach-point='contextSiteNode']";
	public String liveresultblock = ".//div[@data-dojo-attach-point='containerNodeDocs']";
	
	public String liveresult = ".//div[@data-dojo-attach-point='containerNodeDocs']/div[1]//a[@class='alf-livesearch-item__name']";
	public String liveressite = ".//div[@data-dojo-attach-point='containerNodeDocs']/div[1]//a[@class='alf-livesearch-item__name']//../span[1]/a";
	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	

	public AlfrescoHomePage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		/*
		 * if (!driver.getTitle().contains("User Dashboard")) { throw new
		 * IllegalStateException(
		 * "User Dashboard page expected, but not displayed!"); }
		 */
	}

	
	// Navigate to Tasks page
	public AlfrescoTasksPage navigateToHomePage() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);
			} catch (Exception e) {
			}
			if (UIHelper.checkForAnElementbyXpath(driver, homeTabLinkXpath)) {
				UIHelper.click(driver, homeTabLinkXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
				report.updateTestLog("Navigate To 'Home' Page",
						"Navigated to 'Home' Page successfully" + "<br /><b> Page Title : </b>" + "Home", Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoTasksPage(scriptHelper);
	}

	// Navigate to My Files page
	public AlfrescoMyFilesPage navigateToMyFilesTab() {
		try {
			driver.findElement(By.xpath(myFilesTabLinkXpath)).click();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			//UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);

			report.updateTestLog("Navigate To My Files Page",
					"Navigated to My Files Page successfully" + "<br /><b> Page Title : </b>" + "My Files",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To My Files Page", "Failed to load Document Library", Status.FAIL);
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	/**
	 * @author 412766
	 * @return
	 */
	public AlfrescoMyFilesPage navigateToRepositoryTab() {
		try {
			driver.findElement(By.xpath(repositoryTabLinkXpath)).click();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			report.updateTestLog("Navigate To Repository Page",
					"Navigated to Repository Page successfully" + "<br /><b> Page Title : </b>" + "Repository",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To Repository Page", "Failed to load Document Library", Status.FAIL);
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Navigate to Shared Files page
	public AlfrescoMyFilesPage navigateToSharedFilesTab() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, sharedFilesTabLinkXpath);
			UIHelper.highlightElement(driver, sharedFilesTabLinkXpath);
			UIHelper.click(driver, sharedFilesTabLinkXpath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, docLibLoadingMsgXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);

			report.updateTestLog("Navigate To Shared Files Page",
					"Navigated to Shared Files Page successfully" + "<br /><b> Page Title : </b>" + "Shared Files",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To Shared Files Page", "Failed to load Document Library", Status.FAIL);
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	/**
	 * @author 412766
	 * @return
	 */
	public AlfrescoMyFilesPage navigateToMySitesPage() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySitesXapth);
			UIHelper.highlightElement(driver, mySitesXapth);
			UIHelper.click(driver, mySitesXapth);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			report.updateTestLog("Navigate To My Sites Page",
					"Navigated to My Sites page successfully" + "<br /><b> Page Title : </b>" + "User Sites List",
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Navigate to Sites page
	public AlfrescoSitesPage navigateToSitesTab() {
		try {
			WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
			UIHelper.mouseOveranElement(driver, siteMenuEle);
			UIHelper.highlightElement(driver, siteMenuEle);
			UIHelper.click(driver, siteMenuXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInSiteMenu);
			UIHelper.waitForVisibilityOfEleByXpath(driver, creatSiteXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Navigate To Sites Menu Page",
					"Navigated to Sites Menu Page successfully" + "<br /><b> Page Title : </b>" + "Sites Menu",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoSitesPage(scriptHelper);
	}

	/**
	 * @author 412766
	 */
	public void navigeteToCreateSiteMenu() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, creatSiteXpath);
			UIHelper.click(driver, creatSiteXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Navigate To <b>'Create Site'</b> Page",
					"Navigated to <b>'Create Site'</b> Page successfully" + "<br /><b> Page Title : </b>"
							+ "Create Site",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To <b>'Create Site'</b> Page",
					"Navigate To <b>'Create Site'</b> Page Failed", Status.FAIL);
		}
	}

	// Navigate to Tasks page
	public AlfrescoTasksPage navigateToTasksTab() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.click(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			report.updateTestLog("Navigate To Tasks Menu Page",
					"Navigated to Tasks Menu Page successfully" + "<br /><b> Page Title : </b>" + "Tasks Menu",
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoTasksPage(scriptHelper);
	}

	// Navigate to Tasks page
	public AlfrescoTasksPage navigateToMyTasks() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.click(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
			UIHelper.click(driver, taskMyTasksXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Navigate To Tasks Menu Page",
					"Navigated to Tasks Menu Page successfully" + "<br /><b> Page Title : </b>" + "Tasks Menu",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To Tasks Menu Page", "Navigated to Tasks Menu Page Failed", Status.FAIL);
		}

		return new AlfrescoTasksPage(scriptHelper);
	}

	// Navigate to Tasks page
	public AlfrescoTasksPage navigateToUserMenuTab() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, userMenuXpath);
			UIHelper.click(driver, userMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, userProfileXpath);

			report.updateTestLog("Navigate To User Menu Page",
					"Navigated to User Menu Page successfully" + "<br /><b> Page Title : </b>" + "User Menu",
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoTasksPage(scriptHelper);
	}

	public String getHomeTabValue() {
		try {
			actualHomeTabValue = driver.findElement(By.xpath(homeTabLinkXpath)).getText();
		} catch (Exception e) {

		}
		return actualHomeTabValue;
	}

	/**
	 * Method to select a site in dashboard at My Sites segment
	 * 
	 * @return AlfrescoSitesPage
	 * @author 412766
	 */
	public AlfrescoSitesPage navigateToMySitePageUsingHomeTab() {

		try {

			String siteName = dataTable.getData("Sites", "SiteToSelect");
			String siteNameLinkXPath = "//*[contains(text(),'" + siteName + "')]";
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteNameLinkXPath);
			UIHelper.highlightElement(driver, siteNameLinkXPath);
			UIHelper.click(driver, siteNameLinkXPath);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Navigate To My Sites Page",
					"Navigated to My Sites Page successfully" + "<br /><b> Page Title : </b>" + "My Sites",
					Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Navigate To Site page", e.getMessage(), Status.FAIL);
		}

		return new AlfrescoSitesPage(scriptHelper);
	}

	// Navigate to Advanced Search page
	public AlfrescoSearchPage navigateToAdvSearch() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, searchFieldXpath);
			UIHelper.click(driver, searchImageIconXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, advancedSearchXpath);
			UIHelper.click(driver, advancedSearchXpath);
			UIHelper.waitForPageToLoad(driver);
			if (UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath).isDisplayed()) {
				report.updateTestLog("Navigate To Advanced Search Page",
						"Navigated to Advanced Search Page successfully" + "<br /><b> Page Title : </b>"
								+ "Advanced Search",
						Status.DONE);
			} else {
				report.updateTestLog("Navigate To Advanced Search Page", "Navigated to Advanced Search Page failed",
						Status.FAIL);

			}
		} catch (Exception e) {
			report.updateTestLog("Navigate To Advanced Search Page", "Navigated to Advanced Search Page failed",
					Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Navigate to Repository Page
	public void navigateToRepositoryPage() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, repositoryMenuLinkXpath);
			UIHelper.click(driver, repositoryMenuLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, docLibLoadingMsgXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			report.updateTestLog("Navigate to Repository Page", "User successfully navigated to repository page",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To Repository Page", "Failed to load Document Library", Status.FAIL);
			e.printStackTrace();
		}
	}

	// Navigate to Advanced Search page
	public void alfrescoSearch(String searchQuery) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchBox);
			UIHelper.sendKeysToAnElementByXpath(driver, searchBox, searchQuery);
			UIHelper.findAnElementbyXpath(driver, searchBox).sendKeys(Keys.ENTER);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		//	UIHelper.waitForVisibilityOfEleByXpath(driver, searchingXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchingXpath);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
					searchCountXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Perform Simple Search",
					"Search preformed successfully" + "<br /><b> Search Query : </b>" + searchQuery, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Perform Simple Search", "Search preformed failed", Status.FAIL);
		}
	}

	public boolean ClickCmpltdTaskinMyTaskDashlet() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cmpltdTaskinMyTaskDashlet);
			UIHelper.click(driver, cmpltdTaskinMyTaskDashlet);
			// UIHelper.waitForPageToLoad(driver);
			Thread.sleep(8000);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, viewCmpltdTaskXPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return UIHelper.findAnElementbyXpath(driver, viewCmpltdTaskXPath).isDisplayed();
	}

	public boolean selectValInMyTaskDashletDrpDwn() {
		String drpDownVal = dataTable.getData("Home", "TasksFilterDropdownValue");
		String drpdwnBtnXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[text()='My Tasks']//following-sibling::div[1]//span/span/button[contains(.,'Active Tasks')]";
		String drpdwnValXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[text()='My Tasks']//following-sibling::div[1]//*[contains(@id,'yui-gen')]//*[@class='bd']/ul/li/a[contains(.,'"
				+ drpDownVal + "')]";

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, drpdwnBtnXPath);
			UIHelper.click(driver, drpdwnBtnXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, drpdwnValXPath);
			UIHelper.click(driver, drpdwnValXPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return UIHelper.findAnElementbyXpath(driver, drpDwnFltrdTaskDispXPath).isDisplayed();

	}

	// Click on Create Site Link in home page using 'My Sites' Dashlet
	public void clickOnCreateSiteLinkFromHomePage() {
		try {
			UIHelper.click(driver, createSiteLnk);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createSitePopUp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check tasks due date in My Tasks Dashlet
	public AlfrescoHomePage selectTaskFilterDropdownValInMyTasksDashlet() {
		try {
		//	UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, myTaskRemoteSiteLoadingXpath);

			selectFilterValFromDropdownInMyTasksDashlet();

			WebElement tasksContainerEle = UIHelper.findAnElementbyXpath(driver, tasksContainerInMyTasksDashlet);
			UIHelper.highlightElement(driver, tasksContainerEle);
			UIHelper.mouseOveranElement(driver, tasksContainerEle);
			UIHelper.waitFor(driver);

			WebElement subTasksContainerEle = UIHelper.findAnElementbyXpath(driver, subTaskContainerXpath);
			UIHelper.highlightElement(driver, subTasksContainerEle);
			UIHelper.mouseOveranElement(driver, subTasksContainerEle);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoHomePage(scriptHelper);
	}

	public void selectFilterValFromDropdownInMyTasksDashlet() {
		try {
			String tasksFilterVal = dataTable.getData("Home", "TasksFilterDropdownValue");
			String finalTaskFilterDropdownValXpath = taskFilterDropdownValXpath.replace("CRAFT", tasksFilterVal);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskFilterDropdownXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, taskFilterDropdownXpath));
			UIHelper.highlightElement(driver, taskFilterDropdownXpath);
			UIHelper.click(driver, taskFilterDropdownXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalTaskFilterDropdownValXpath);
			UIHelper.click(driver, finalTaskFilterDropdownValXpath);
			UIHelper.waitFor(driver);
		//	UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, myTaskRemoteSiteLoadingXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Filter the Tasks in 'My Tasks' Dashlet",
					"Tasks filtered successfully with" + tasksFilterVal + " in 'My Tasks' Dashlet", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Filter the Tasks in 'My Tasks' Dashlet",
					"Tasks filter failed My Tasks Dashlet", Status.FAIL);
			e.printStackTrace();
		}
	}

	// Open Task from 'My Tasks' Dashlet
	public void openTaskFromMyDashlet() {
		selectTaskFilterDropdownValInMyTasksDashlet();
		String taskName = dataTable.getData("Workflow", "Message");
		String currentDate = new DateUtil().getCurrentDate();

		String finalTaskName = taskName + "_" + currentDate;
		boolean isDispTaskInMyTasksDashlet = checkTaskInMyTasksDashlet(finalTaskName);

		if (isDispTaskInMyTasksDashlet) {
			clickTaskInMyDashlet();
		}
	}

	// Common method for open Task from 'My Tasks' Dashlet
	public void commonMethodForOpenTaskFromMyDashlet(String taskName) {
		selectTaskFilterDropdownValInMyTasksDashlet();
		boolean isDispTaskInMyTasksDashlet = checkTaskInMyTasksDashlet(taskName);

		if (isDispTaskInMyTasksDashlet) {
			clickOnTaskInMyDashlet(taskName);
		}

	}

	// Click on required task in 'My Tasks' Dashlet
	public void clickTaskInMyDashlet() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String finalTaskName = taskName + "_" + currentDate;

			List<WebElement> listOfTasksDueDates = driver.findElements(By.xpath(taskLinksXpath));
			for (WebElement ele : listOfTasksDueDates) {
				if (ele.getText().equalsIgnoreCase(finalTaskName) || ele.getText().contains(finalTaskName)) {
					UIHelper.highlightElement(driver, ele);
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);

					report.updateTestLog("Open Task from 'My Tasks' Dashlet",
							"<b>Task:</b>" + finalTaskName + " opened successfully from 'My Tasks' Dashlet",
							Status.DONE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Task in 'My Tasks' Dashlet
	public boolean commonMethodForCheckTaskInMyTasksDashlet(String taskName) {

		boolean isDispTaskInMyTasksDashlet = false;
		try {
			selectTaskFilterDropdownValInMyTasksDashlet();
			isDispTaskInMyTasksDashlet = checkTaskInMyTasksDashlet(taskName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDispTaskInMyTasksDashlet;
	}

	// Get count of Tasks from My tasks dashlet
	public int getCountOfTasksFromMyTasksDashlet() {
		int pageItems = 0;
		try {
			String taskCountMessage = UIHelper.getTextFromWebElement(driver, currentPageNatorXpath);

			if (taskCountMessage.contains("of")) {
				String splitTaskCountMsg[] = taskCountMessage.split("of");

				if (splitTaskCountMsg != null && splitTaskCountMsg.length > 1) {
					pageItems = Integer.parseInt(splitTaskCountMsg[1].trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageItems;
	}

	// Check Task is Created/Assigned
	public boolean checkTaskInMyTasksDashlet(String taskName) {
		boolean isDisplayedTask = false;
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			isDisplayedTask = checkTask(taskName);

			if (isDisplayedTask == false) {

				String taskCountMessage = UIHelper.getTextFromWebElement(driver, currentPageNatorXpath);

				int pageCount = 0;
				int pageItems = 0;
				if (taskCountMessage.contains("of")) {
					String splitTaskCountMsg[] = taskCountMessage.split("of");

					if (splitTaskCountMsg != null && splitTaskCountMsg.length > 1) {
						pageItems = Integer.parseInt(splitTaskCountMsg[1].trim());
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

				isDisplayedTask = checkTaskInAllPagesInMyTaskDashlet(pageCount, taskName);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedTask;
	}

	public boolean checkTaskInAllPagesInMyTaskDashlet(int pageCount, String taskName) {
		boolean isDisplayedTask = false;
		try {
			for (int index = 1; index <= pageCount; index++) {
				int startVal = (index * 50) + 1;
				int tempEndVal = ((index + 1) * 50);
				String displayedTasksCountMessage = UIHelper.getTextFromWebElement(driver, currentPageNatorXpath);
				int noOfTasks = 0;
				if (displayedTasksCountMessage.contains("of")) {
					String splittedTaskCountMsg[] = displayedTasksCountMessage.split("of");

					if (splittedTaskCountMsg != null && splittedTaskCountMsg.length > 1) {
						noOfTasks = Integer.parseInt(splittedTaskCountMsg[1].trim());
					}
				}

				int endVal = 0;
				if (tempEndVal < noOfTasks) {
					endVal = tempEndVal;
				} else {
					endVal = noOfTasks;
				}

				String finalPageCountMessage = myTaskDashletPageCountMessage.replace("START", "" + startVal)
						.replace("END", "" + endVal).replace("TOTAL_TASKS", "" + noOfTasks);

				String finalXpathForPageNatorMsg = tempXpathForPageNatorMsg.replace("CRAFT", finalPageCountMessage);

				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, nextPageLinkXpath));
				UIHelper.click(driver, nextPageLinkXpath);

				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, finalXpathForPageNatorMsg);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				isDisplayedTask = checkTask(taskName);
				if (isDisplayedTask) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedTask;
	}

	// Check Task is Created/Displayed
	public boolean checkTask(String taskName) {
		boolean isDisplayedTask = false;
		try {
			isDisplayedTask = verifyTaskInMyTasksDashlet(taskName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedTask;
	}

	// Check Task in My Tasks Dashlet
	public boolean verifyTaskInMyTasksDashlet(String taskName) {
		try {
			List<WebElement> listOfTasksDueDates = driver.findElements(By.xpath(taskLinksXpath));
			for (WebElement ele : listOfTasksDueDates) {
				if (ele.getText().equalsIgnoreCase(taskName) || ele.getText().contains(taskName)) {
					isDisplayedReqTaskInMyTasksDashlet = true;
					UIHelper.highlightElement(driver, ele);
					UIHelper.scrollToAnElement(ele);

					UIHelper.waitFor(driver);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedReqTaskInMyTasksDashlet;
	}

	public void clickOnTaskInMyDashlet(String taskName) {
		try {
			List<WebElement> listOfTasksDueDates = driver.findElements(By.xpath(taskLinksXpath));
			for (WebElement ele : listOfTasksDueDates) {
				if (ele.getText().equalsIgnoreCase(taskName) || ele.getText().contains(taskName)) {
					UIHelper.highlightElement(driver, ele);
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);

					report.updateTestLog("Open Task from 'My Tasks' Dashlet",
							"<b>Task:</b>" + taskName + " opened successfully from 'My Tasks' Dashlet", Status.DONE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDisplayedDueDateForAllTasksInMyTasksDashlet() {
		try {
			List<WebElement> listOfTasksDueDates = driver.findElements(By.xpath(dueDateXpathForTasks));
			tasksDueDateCount = listOfTasksDueDates.size();
			for (WebElement ele : listOfTasksDueDates) {
				if (UIHelper.checkForAnWebElement(ele)) {
					UIHelper.highlightElement(driver, ele);
					isDisplayedDueDateForAllTasks = true;
				} else {
					isDisplayedDueDateForAllTasks = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedDueDateForAllTasks;
	}

	// Filter with test data in My Sites Dashlet
	public void filterSitesInMySitesDashlet(String filterOptionVal) {
		try {

			String finalTaskFilterDropdownValXpath = mySitesDashletFilterDropdownValXpath.replace("CRAFT",
					filterOptionVal);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInMySitesDashlet);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, mySitesDashletFilterDropdownXpath);
			} catch (Exception e) {

			}
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, mySitesDashletFilterDropdownXpath));
			UIHelper.highlightElement(driver, mySitesDashletFilterDropdownXpath);
			UIHelper.click(driver, mySitesDashletFilterDropdownXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalTaskFilterDropdownValXpath);
			UIHelper.click(driver, finalTaskFilterDropdownValXpath);

			if (filterOptionVal.equalsIgnoreCase("All")) {
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
			} else {
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, firstFavoriteSiteXpathInMySitesDashlet);
			}

			WebElement sitesContainerEle = UIHelper.findAnElementbyXpath(driver, sitesContainerXpathInMySitesDashlet);
			UIHelper.highlightElement(driver, sitesContainerEle);
			UIHelper.mouseOveranElement(driver, sitesContainerEle);
			UIHelper.waitFor(driver);

			report.updateTestLog("Filter the Sites in 'My Sites' Dashlet",
					"Sites filtered successfully with " + filterOptionVal + " in 'My Sites' Dashlet", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Task Due date in 'My Tasks' Dashlet in User Dashboard Page
	public boolean isDisplayedDueDateForTaskInMyTasksDashlet(String taskName) {
		try {
			String finalXpathForDueDateInMyTasksDashlet = tempXpathForDueDateInMyTasksDashlet.replace("CRAFT",
					taskName);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForDueDateInMyTasksDashlet)) {
				isDisplayedDueDateForAllTasks = true;
				WebElement taskDueDateEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDueDateInMyTasksDashlet);
				UIHelper.highlightElement(driver, taskDueDateEle);
				UIHelper.scrollToAnElement(taskDueDateEle);
			} else {
				isDisplayedDueDateForAllTasks = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedDueDateForAllTasks;
	}

	// Verify AND Search page
	public void alfrescoANDSearch() {
		try {
			UIHelper.waitForPageToLoad(driver);
			String searchANDQuery = dataTable.getData("Search", "Query");
			UIHelper.sendKeysToAnElementByXpath(driver, searchBox, searchANDQuery);
			UIHelper.findAnElementbyXpath(driver, searchBox).sendKeys(Keys.ENTER);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
		}
	}

	// Verify OR Search page
	public void alfrescoORSearch() {
		try {
			UIHelper.waitForPageToLoad(driver);
			String searchORQuery = dataTable.getData("Search", "Result");
			UIHelper.sendKeysToAnElementByXpath(driver, searchBox, searchORQuery);
			UIHelper.findAnElementbyXpath(driver, searchBox).sendKeys(Keys.ENTER);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
		}
	}

	// Check if any bulk jobs are available for the user
	public void isBulkJobpresent() {
		try {
			if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, isBulkDashletAvailable))) {
				UIHelper.click(driver, bulkDurationbutton);
				UIHelper.click(driver, bulkDuration30Days);
				UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
				UIHelper.click(driver, bulkgetJobsBtn);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, bulkIDData);
				if (UIHelper.findAnElementbyXpath(driver, bulkIDData).isDisplayed()) {
					report.updateTestLog("Get Bulk Job for 30 Days",
							"Get Bulk Job for 30 Days Job from Dashlet successfully", Status.DONE);
				} else {
					report.updateTestLog("Get Bulk Job for 30 Days", "Get Bulk Job for 30 Days Job from Dashlet Failed",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Get Bulk Job for 30 Days", "Get Bulk Job for 30 Days Job from Dashlet Failed",
					Status.FAIL);
		}
	}

	// Get the list of Bulk jobs data from dashlet
	public void getBulkJobDataList() {
		try {
			UIHelper.findandAddElementsToaListforBulk(driver, bulkIDData, bulkIDList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkTypeData, bulkTypeList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkStartDate, bulkStartDateList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkCompletionDate, bulkCompletionDateList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkCommentsdata, bulkCommentList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkStatusdata, bulkStatusList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkSizedata, bulkSizeList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkActiondata, bulkActionList);
		} catch (Exception e) {
		}
	}

	// Get file/folder name from Search result and put in into a Map with
	// details
	public Map<String, ArrayList<String>> getBulkjobValues() {
		try {

			UIHelper.findandAddElementsToaListforBulk(driver, bulkIDData, bulkIDList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkTypeData, bulkTypeList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkStartDate, bulkStartDateList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkCompletionDate, bulkCompletionDateList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkCommentsdata, bulkCommentList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkStatusdata, bulkStatusList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkSizedata, bulkSizeList);
			UIHelper.findandAddElementsToaListforBulk(driver, bulkActiondata, bulkActionList);

			bulkJobValues.put("JobId", bulkIDList);
			bulkJobValues.put("JobType", bulkTypeList);
			bulkJobValues.put("SatrtDate", bulkStartDateList);
			bulkJobValues.put("Completion", bulkCompletionDateList);
			bulkJobValues.put("Comment", bulkCommentList);
			bulkJobValues.put("Status", bulkStatusList);
			bulkJobValues.put("Size", bulkSizeList);
			bulkJobValues.put("Action", bulkActionList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bulkJobValues;
	}

	// Get the list of ALL Bulk Download jobs data from dashlet
	public void getBulkDownloadJobfromDashlet() {
		try {
			UIHelper.click(driver, bulkTypeButton);
			UIHelper.click(driver, bulkDownloadTypeJobs);
			UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
			UIHelper.click(driver, bulkgetJobsBtn);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, bulkLoadingGifXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, bulkIDData)) {
				report.updateTestLog("Get Bulk Download Job",
						"Get Bulk Download Job from Dashlet successfully" + "<br /><b> Job Type : </b>" + "DOWNLOAD",
						Status.DONE);
			} else {
				report.updateTestLog("Get Bulk Download Job", "Get Bulk Download Job from Dashlet Failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Get Bulk Download Job", "Get Bulk Download Job from Dashlet Failed", Status.FAIL);
		}
	}

	// Get the list of ALL Bulk Upload jobs data from dashlet
	public void getBulkUploadJobfromDashlet() {
		try {
			UIHelper.click(driver, bulkTypeButton);
			UIHelper.click(driver, bulkUploadTypeJobs);
			UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
			UIHelper.click(driver, bulkgetJobsBtn);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, bulkLoadingGifXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, bulkIDData)) {
				report.updateTestLog("Get Bulk Upload Job",
						"Get Bulk Upload Job from Dashlet successfully" + "<br /><b> Job Type : </b>" + "UPLOAD",
						Status.DONE);
			} else {
				report.updateTestLog("Get Bulk Upload Job", "Get Bulk Upload Job from Dashlet Failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Get Bulk Upload Job", "Get Bulk Upload Job from Dashlet Failed", Status.FAIL);
		}
	}

	// Customize Site Dashlet
	public void customizeSiteDashboard() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, customDashBtnXPath);
			UIHelper.click(driver, customDashBtnXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addDashletBtnXPath);
			UIHelper.highlightElement(driver, addDashletBtnXPath);
			UIHelper.click(driver, addDashletBtnXPath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Dashlet
	public boolean addDashletInCustomDashBoard() {
		
		

		String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
		String finalAddedDashletHomePgXPath = addedDashletHomePgXPath.replace("CRAFT", dashletNmetoAdd);

		String colNoofAddDashlet = dataTable.getData("Home", "ColumnNoofAddDashlet");
		String finalColNoofAddDashlet = colNoofAddDashlet.replace("'", "");

		int colNoofAddDashletInt = Integer.valueOf(finalColNoofAddDashlet).intValue();

		String finalTomoveDashColXPath = tomoveDashColXPath.replace("CRAFT", "" + colNoofAddDashletInt);

		String finalAddDashValXpath = addDashValXpath.replace("CRAFT", dashletNmetoAdd);

		try {

			UIHelper.click(driver, homeTabLinkXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, customDashBtnXPath);
			UIHelper.click(driver, customDashBtnXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, addDashletBtnXPath);
			UIHelper.click(driver, addDashletBtnXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalAddDashValXpath);

			UIHelper.dragAndDrop(driver, finalAddDashValXpath, finalTomoveDashColXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnDashXPath);
			UIHelper.click(driver, okBtnDashXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalAddedDashletHomePgXPath);

			report.updateTestLog(
					"Add the Dashlet -  " + dashletNmetoAdd + " from Column No " + colNoofAddDashletInt
							+ "using  Custom Dashboard Functionality.Verify the dashlet has been Added successfully",
					"Dashlet has been Added successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return UIHelper.checkForAnElementbyXpath(driver, finalAddedDashletHomePgXPath);

	}

	// Remove Dashlet
	public boolean removeDashletInCustomDashBoard() {

		try {
			String dashletNmetoremove = dataTable.getData("Home", "DashletName");
			String colNoofremvDashlet = dataTable.getData("Home", "ColumnNoofremvDashlet");

			String finalRemvDashColumXPath = (remvDashColumXPath.replace("CRAFTSys", colNoofremvDashlet).toString())
					.replace("CRAFTLite", dashletNmetoremove);
			System.out.println("" + finalRemvDashColumXPath);

			String dashletNmetoAdd = dataTable.getData("Home", "DashletName");

			UIHelper.click(driver, homeTabLinkXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, customDashBtnXPath);
			UIHelper.click(driver, customDashBtnXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, addDashletBtnXPath);
			UIHelper.click(driver, addDashletBtnXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalRemvDashColumXPath);

			UIHelper.dragAndDrop(driver, finalRemvDashColumXPath, movetoRemvDashXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnDashXPath);
			UIHelper.click(driver, okBtnDashXPath);

			report.updateTestLog(
					"Remove the Dashlet -  " + dashletNmetoremove + " from Column No " + colNoofremvDashlet
							+ "using  Custom Dashboard Functionality.Verify the dashlet has been removed successfully",
					"Dashlet has been removed successfully", Status.DONE);

			UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return UIHelper.checkForAnElementbyXpath(driver, homeTabLinkXpath);

	}

	/**
	 * Method to navigate site finder page
	 * 
	 * @return AlfrescoSitesPage
	 * @author 412766
	 */
	public AlfrescoSitesPage navigateToSiteFinder() {
		try {
			WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteFinderXapth);
			UIHelper.mouseOveranElement(driver, siteMenuEle);
			UIHelper.highlightElement(driver, siteMenuEle);
			UIHelper.click(driver, siteFinderXapth);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			report.updateTestLog("Navigate To Sites Finder Tab",
					"Navigated to Sites Finder Page successfully" + "<br /><b> Page Title : </b>" + "Site Finder",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To Sites Finder Tab", "Navigate To Sites Finder Tab Failed", Status.FAIL);
		}

		return new AlfrescoSitesPage(scriptHelper);
	}

	// Navigate to WFStart page
	public void navigateToWFStartedTab() {
		try {

			UIHelper.click(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyWFTaskXpath);
			UIHelper.click(driver, taskMyWFTaskXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Navigate To Workflow I've Started Page",
					"Navigate To Workflow I'va Started Page successfully", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To Workflow I've Started Page",
					"Navigate To Workflow I've Started Page Failed", Status.FAIL);
		}

	}

	// Navigate to My Files page
	public void navigateToAdminTab() {
		try {
			driver.findElement(By.xpath(adminPageXpath)).click();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			if (UIHelper.findAnElementbyXpath(driver, adminTitleXpath).isDisplayed()) {
				report.updateTestLog("Navigate to Admin Tools",
						"Navigation to Admin Tools Page successfully " + "<br /><b>Page Title : </b>"
								+ UIHelper.findAnElementbyXpath(driver, adminTitleXpath).getText(),
						Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Navigate To Admin Tab", "Navigated to Admin Page failed", Status.FAIL);
		}

	}

	/**
	 * Method to check the 'Admin Tools' Tab available or Not
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isAdminToolsAvailable() {
		boolean flag = false;
		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, adminPageXpath))) {
				UIHelper.highlightElement(driver, adminPageXpath);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Get the list of ALL Bulk Failed jobs data from dashlet
	public void getBulkFailedJobfromDashlet() {
		try {
			UIHelper.click(driver, bulkStatusButton);
			UIHelper.click(driver, bulkFailedJobs);
			UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
			UIHelper.click(driver, bulkgetJobsBtn);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, bulkIDData).isDisplayed()) {
				List<WebElement> bulkActionButtonList = UIHelper.findListOfElementsbyXpath(bulkActionbutton, driver);
				bulkActionButtonList.get(0).click();

				UIHelper.waitForPageToLoad(driver);

				report.updateTestLog("Get Bulk Failed Job",
						"Get Bulk Faield Job from Dashlet and check for errors successfully"
								+ "<br /><b> Jod Status : </b>" + "Failed",
						Status.DONE);
			} else {
				report.updateTestLog("Get Bulk Failed Job",
						"Get Bulk Faield Job from Dashlet and check for errors failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Get Bulk Failed Job", "Get Bulk Faield Job from Dashlet and check for errors failed",
					Status.FAIL);
		}
	}

	// Get the list of ALL Bulk Failed jobs data from dashlet
	public void getBulkFailedfromDashlet() {
		try {
			UIHelper.click(driver, bulkStatusButton);
			UIHelper.click(driver, bulkFailedJobs);
			UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
			UIHelper.click(driver, bulkgetJobsBtn);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, bulkIDData).isDisplayed()) {
				report.updateTestLog("Get Bulk Failed Job",
						"Get Bulk Faield Job from Dashlet successfully" + "<br /><b> Jod Status : </b>" + "Failed",
						Status.DONE);
			} else {
				report.updateTestLog("Get Bulk Failed Job", "Get Bulk Faield Job from Dashlet failed", Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Get Bulk Failed Job", "Get Bulk Faield Job from Dashlet failed", Status.FAIL);
		}
	}

	// Click on user Menu and Click Help
	public void clickonUserMenu() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.click(driver, siteUserMenuXpath);
			UIHelper.click(driver, siteUserMenuHelpXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Navigate to Help", "Navigate to Help failed", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate to Help", "Navigate to Help failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean checkOverDueTaskNotification() {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			selectFilterValFromDropdownInMyTasksDashlet();
			UIHelper.waitForVisibilityOfEleByXpath(driver, myTasksegmentXpath);
			UIHelper.highlightElement(driver, myTasksegmentXpath);
			UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, myTasksegmentXpath));
			List<WebElement> overDueTaskNameList = UIHelper.findListOfElementsbyXpath(overDueTaskNameXpath, driver);
			List<WebElement> overDueTaskDateList = UIHelper.findListOfElementsbyXpath(overDueTaskDateXpath, driver);
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String finalTaskName = taskName + "_" + currentDate;

			for (int index = 0; index < overDueTaskNameList.size(); index++) {
				if (overDueTaskNameList.get(index).getText().equals(finalTaskName)) {
					UIHelper.scrollToAnElement(overDueTaskNameList.get(index));
					UIHelper.highlightElement(driver, overDueTaskNameList.get(index));
					UIHelper.highlightElement(driver, overDueTaskDateList.get(index));
					String dueDate = overDueTaskDateList.get(index).getAttribute("class");
					if (dueDate.equals("task-delayed")) {
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
	 * @return
	 */
	public boolean checkTaskNotification() {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			selectFilterValFromDropdownInMyTasksDashlet();
			UIHelper.waitForVisibilityOfEleByXpath(driver, myTasksegmentXpath);
			UIHelper.highlightElement(driver, myTasksegmentXpath);
			UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, myTasksegmentXpath));
			List<WebElement> overDueTaskNameList = UIHelper.findListOfElementsbyXpath(overDueTaskNameXpath, driver);
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String finalTaskName = taskName + "_" + currentDate;

			for (int index = 0; index < overDueTaskNameList.size(); index++) {
				if (overDueTaskNameList.get(index).getText().equals(finalTaskName)) {
					UIHelper.highlightElement(driver, overDueTaskNameList.get(index));
					UIHelper.scrollToAnElement(overDueTaskNameList.get(index));
					flag = true;
					break;
				} else {
					flag = false;
				}
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
	public void checkActiveTaskInMyTaskDashlet() {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			selectFilterValFromDropdownInMyTasksDashlet();
			UIHelper.waitForVisibilityOfEleByXpath(driver, myTasksegmentXpath);
			UIHelper.highlightElement(driver, myTasksegmentXpath);
			UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, myTasksegmentXpath));
			List<WebElement> myTaskNameList = UIHelper.findListOfElementsbyXpath(overDueTaskNameXpath, driver);

			if (myTaskNameList.size() > 0) {
				report.updateTestLog("Verify user able to filter 'Active Task' in MyTask Dashlet",
						"User able to filtered successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify user able to filter 'Active Task' in MyTask Dashlet",
						"User not able to filtered", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify user able to filter 'Active Task' in MyTask Dashlet",
					"Verify user able to filter 'Active Task' in MyTask Dashlet Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public void checkCompletedTaskInMyTaskDashlet() {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			selectFilterValFromDropdownInMyTasksDashlet();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myTasksegmentXpath);
			UIHelper.highlightElement(driver, myTasksegmentXpath);
			UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, myTasksegmentXpath));
			List<WebElement> myTaskNameList = UIHelper.findListOfElementsbyXpath(overDueTaskNameXpath, driver);

			if (myTaskNameList.size() > 0) {
				report.updateTestLog("Verify user able to filter 'Completed Task' in MyTask Dashlet",
						"User able to filtered successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify user able to filter 'Completed Task' in MyTask Dashlet",
						"User not able to filtered", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify user able to filter 'Completed Task' in MyTask Dashlet",
					"Verify user able to filter 'Completed Task' in MyTask Dashlet Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param activityDid
	 * @param activityName
	 * @param siteName
	 * @return
	 */
	public boolean isUserActivityDisplayedInMyActivityDashlet(String activityDid, String activityName,
			String siteName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			String activityType = dataTable.getData("MyActivities", "MyActivityDropDownValue");
			UIHelper.waitForVisibilityOfEleByXpath(driver, myActivityDropDownXpath);
			UIHelper.highlightElement(driver, myActivityDropDownXpath);
			UIHelper.click(driver, myActivityDropDownXpath);

			UIHelper.waitForPageToLoad(driver);
			String finalXpath = myActivityDropDownValueTempXpath.replace("CRAFT", activityType);
			UIHelper.highlightElement(driver, finalXpath);
			UIHelper.click(driver, finalXpath);

			UIHelper.highlightElement(driver, myActivitytimeDropDownXpath);
			UIHelper.click(driver, myActivitytimeDropDownXpath);

			UIHelper.waitForPageToLoad(driver);
			// String finalXpath =
			// myActivityDropDownValueTempXpath.replace("CRAFT", activityType);
			UIHelper.highlightElement(driver, myActivityDropDownValuetodayTempXpath);
			UIHelper.click(driver, myActivityDropDownValuetodayTempXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myActivityPanelXpath);
			UIHelper.highlightElement(driver, myActivityPanelXpath);

			List<WebElement> myActivityList = UIHelper.findListOfElementsbyXpath(myActivityListXpath, driver);
			for (WebElement webElement : myActivityList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					String myActivityText = webElement.getText();
				
					if (myActivityText.contains(activityDid) && myActivityText.contains(activityName)
							&& myActivityText.contains(siteName)) {
						UIHelper.highlightElement(driver, webElement);
						flag = true;
						break;
					} else {
						flag = false;
					}
					// UIHelper.waitFor(driver);
				}
			}
		} catch (Exception e) {
			
			/*report.updateTestLog("sdfaf  Verify that a record is displayed in the 'My Activity' dashlet",
					"Verify that a record is displayed in the 'My Activity' dashlet Failed", Status.FAIL);*/
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param activityDid
	 * @param activityName
	 * @param siteName
	 * @return
	 */
	public boolean isUserActivityDisplayedInMyActivityDashlet(String activityDid, String activityName, String siteName,
			String activityType) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myActivityDropDownXpath);
			UIHelper.highlightElement(driver, myActivityDropDownXpath);
			UIHelper.click(driver, myActivityDropDownXpath);

			UIHelper.waitForPageToLoad(driver);
			String finalXpath = myActivityDropDownValueTempXpath.replace("CRAFT", activityType);
			UIHelper.highlightElement(driver, finalXpath);
			UIHelper.click(driver, finalXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myActivityPanelXpath);
			UIHelper.highlightElement(driver, myActivityPanelXpath);

			List<WebElement> myActivityList = UIHelper.findListOfElementsbyXpath(myActivityListXpath, driver);
			for (WebElement webElement : myActivityList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					String myActivityText = webElement.getText();
					if (myActivityText.contains(activityDid) && myActivityText.contains(activityName)
							&& myActivityText.contains(siteName)) {
						UIHelper.highlightElement(driver, webElement);
						flag = true;
						break;
					} else {
						flag = false;
					}
					// UIHelper.waitFor(driver);
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Verify that a record is displayed in the 'My Activity' dashlet Failed", Status.FAIL);
		}
		return flag;
	}

	// Navigate to 'User My Profile'
	public AlfrescoHomePage navigateToUserMyProfileTab() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, userMenuXpath);
			UIHelper.click(driver, userMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, userProfileXpath);
			UIHelper.click(driver, userProfileXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Navigate To User Tab", "Navigated to User Profile Page successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoHomePage(scriptHelper);
	}

	// Get the list jobs sorted by date from dashlet
	public void getBulkJobSortedfromDashlet() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, sortStartDateXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, sortStartDateXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, bulkIDData).isDisplayed()) {
				report.updateTestLog("Get Bulk Job Sortred",
						"Get Bulk Job Sorted from Dashlet successfully" + "<br /><b> Sorted by : </b>" + "Start Date",
						Status.DONE);
			} else {
				report.updateTestLog("Get Bulk Job Sortred", "Get Bulk Job Sorted from Dashlet Failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Get Bulk Job Sortred", "Get Bulk Job Sorted from Dashlet Failed", Status.FAIL);
		}
	}

	// Click 'Create Site' Link from 'My Sites' Dashlet
	public void clickCreateSiteFromDashlet() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, creatSiteXPath);
			UIHelper.click(driver, creatSiteXPath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click Create Site Link from My site Dashlet", "Create Site Link Clicked successfully",
					Status.DONE);
		} catch (Exception e) {
		}
	}

	// Check tasks in 'My Tasks' Dashlet in Home Page
	public boolean isDisplayedTasksInHomePage() {
		try {
			List<WebElement> listOfTasksDueDates = driver.findElements(By.xpath(dueDateXpathForTasks));
			openTasksSize = listOfTasksDueDates.size();
			for (WebElement ele : listOfTasksDueDates) {
				if (UIHelper.checkForAnWebElement(ele)) {
					isDisplayedDueDateForAllTasks = true;
				} else {
					isDisplayedDueDateForAllTasks = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedDueDateForAllTasks;
	}

	// Check created task in home page
	public boolean isDisplayedMyTaskInHomePage(String taskName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			List<WebElement> listOfTasksDueDates = driver.findElements(By.xpath(taskNameXpathInHomeDashlet));
			for (WebElement ele : listOfTasksDueDates) {
				if (ele.getText().trim().contains(taskName)) {
					isDisplayedMyTask = true;
					break;
				} else {
					isDisplayedMyTask = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedMyTask;
	}

	// Select sites from simple search result
	public void instantSiteSearch() {

		try {
			UIHelper.waitForPageToLoad(driver);
			String searchQuery = dataTable.getData("Search", "Query");
			UIHelper.sendKeysToAnElementByXpath(driver, searchBox, searchQuery);
			UIHelper.waitForVisibilityOfEleByXpath(driver, instantSiteResultXpath);
			String siteName = dataTable.getData("Sites", "SiteName");
			String finalSiteSeacrhXpath = instantSiteSeacrhXpath.replace("CRAFT", siteName);
			if (UIHelper.findAnElementbyXpath(driver, finalSiteSeacrhXpath).isDisplayed()) {
				UIHelper.click(driver, finalSiteSeacrhXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Instant Site Search",
						"Instant site Search successfully" + "Searched Site Name" + searchQuery, Status.DONE);
			} else {
				report.updateTestLog("Instant Site Search", "Instant site Search failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Instant Site Search", "Instant site Search failed", Status.FAIL);
		}
	}

	// Return True if Admin tools tab is present for Site_Admin user
	public boolean retAdminTabPresence() {

		try {
			UIHelper.findAnElementbyXpath(driver, adminPageXpath);
			return true;

		} catch (Exception e) {
			return false;

		}

	}

	// Open New tab and check URL
	public void openNewTab(String currentURL) {
		try {
			// String currentURL = driver.getCurrentUrl();
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		
			((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
			//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			UIHelper.waitFor(driver);
			driver.get(currentURL);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Open New Tab",
					"Load Bookmark URL in New tab" + "<br /><b>Bookmark URL : </b>" + currentURL, Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Open New Tab", "Load Bookmark URL in New tab failed", Status.FAIL);

		}

	}

	// Note time to load data in My Activities Dashlet
	public void verifyTimeDetailsToLoadDataInMyActivitiesDashlet() {
		try {
			driver.findElement(By.xpath(myFilesTabLinkXpath)).click();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			UIHelper.click(driver, homeTabLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myActivitiesDashletHeaderXpath);
			startTime = System.currentTimeMillis();
			UIHelper.waitForVisibilityOfEleByXpath(driver, todayTitleXpathInMyactivitiesDashlet);

			if (UIHelper.checkForAnElementbyXpath(driver, todayTitleXpathInMyactivitiesDashlet)) {
				endTime = System.currentTimeMillis();
				long tempDifferenceTime = endTime - startTime;

				differenceTime = tempDifferenceTime / 10;

				report.updateTestLog("verify the time taken to load the data in 'My Activities' dashlet",
						"'My Activities' Dashlet data loaded " + differenceTime + " Seconds in 'Home' Page"
								+ "<br /><b>Time taken to load data in 'My Activities' Dashlet : </b>" + differenceTime
								+ " Seconds",
						Status.PASS);
			} else {
				report.updateTestLog("verify the time taken to load the data in 'My Activities' dashlet",
						"'My Activities' Dashlet data failed to display in 'Home' Page", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("verify the time taken to load the data in 'My Activities' dashlet",
					"'My Activities' Dashlet data failed to display in 'Home' Page", Status.FAIL);
			e.printStackTrace();
		}
	}

	// Check Task Details in My Tasks Dashlet
	public boolean isDisplayedTaskInMyTasksDashlet(String taskName) {
		try {
			List<WebElement> listOfTasksDueDates = driver.findElements(By.xpath(taskLinksXpath));
			for (WebElement ele : listOfTasksDueDates) {
				if (UIHelper.checkForAnWebElement(ele)) {
					isDisplayedMyTask = true;
				} else {
					isDisplayedMyTask = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedMyTask;
	}

	// Get first three activities from 'My Activities' Dashlet
	public ArrayList<String> getFirstThreeActivities() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, myActivitiesDashletHeaderXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, myActivitiesDashletHeaderXpath)) {

				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, myActivitiesDashletHeaderXpath));

				List<WebElement> firstThreeActivitiesEleList = UIHelper
						.findListOfElementsbyXpath(firstThreeActivitiesElXpath, driver);
				for (WebElement ele : firstThreeActivitiesEleList) {
					myActivitiesList.add(ele.getText().trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return myActivitiesList;
	}

	/**
	 * @author 412766
	 * @param url
	 */
	public void openURLWithNewTab(String url) {
		try {
			UIHelper.waitFor(driver);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			UIHelper.waitFor(driver);
			driver.get(url);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Open URL with New Tab",
					"Page opened successfully" + "<br><b> URL opened : </b>" + url, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Open New tab and check URL
	public void openGmailTab() {
		try {

			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {

				UIHelper.waitFor(driver);
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				UIHelper.waitFor(driver);
				driver.get(Settings.getInstance().getProperty("GmailUrl"));
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Open New Gmail Tab", "Load Gmail in New tab", Status.DONE);
			} else if (os.contains("mac")) {

				UIHelper.waitFor(driver);
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				UIHelper.waitFor(driver);
				driver.get(Settings.getInstance().getProperty("GmailUrl"));
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Open New Gmail Tab", "Load Gmail in New tab", Status.DONE);

			} else {
				report.updateTestLog("Open New Gmail Tab", "Load Gmail URL in New tab failed", Status.FAIL);
			}

			/*
			 * UIHelper.waitFor(driver);
			 * driver.findElement(By.cssSelector("body")).sendKeys( Keys.CONTROL
			 * + "t"); ArrayList<String> tabs = new ArrayList<String>(
			 * driver.getWindowHandles());
			 * driver.switchTo().window(tabs.get(1)); UIHelper.waitFor(driver);
			 * driver.get(Settings.getInstance().getProperty("GmailUrl"));
			 * UIHelper.waitForPageToLoad(driver); UIHelper.waitFor(driver);
			 * 
			 * report.updateTestLog("Open New Gmail Tab",
			 * "Load Gmail in New tab", Status.DONE);
			 */

		} catch (Exception e) {
			report.updateTestLog("Open New Gmail Tab", "Load Gmail URL in New tab failed", Status.FAIL);
		}

	}

	// Navigate to Tasks page
	public AlfrescoTasksPage navigateToTasksTabNoReport() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMenuXpath);
			UIHelper.click(driver, taskMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, taskMyTasksXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoTasksPage(scriptHelper);
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isDeleteOptionInMySiteDashlet(String siteName) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInMySitesDashlet);
			// selectTaskInMyTasksDashlet();
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySitesPanelXpath);
			UIHelper.highlightElement(driver, mySitesPanelXpath);
			
			//UIHelper.mouseOverandclickanElement(driver, UIHelper.findAnElementbyXpath(driver, mySitesPanelXpath));
			List<WebElement> siteNameNameList = UIHelper.findListOfElementsbyXpath(mySitesListXapth, driver);
			for (WebElement webElement : siteNameNameList) {
				UIHelper.scrollToAnElement(webElement);
				if (webElement.getText().equals(siteName)) {
					UIHelper.highlightElement(driver, webElement);
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Check Blue Tints in Home page
	public boolean checkBlueTints() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, startWorkFlowXpath)) {
				WebElement ele = UIHelper.findAnElementbyXpath(driver, startWorkFlowXpath);
				String getColourObj = ele.getAttribute("class");

				if (getColourObj.equalsIgnoreCase("theme-color-1") || getColourObj.contains("theme-color-1")) {
					isDisplayedBlueTint = true;
				} else {
					isDisplayedBlueTint = false;
				}
			} else if (UIHelper.checkForAnElementbyXpath(driver, createSiteLinkXpath)) {
				WebElement createSiteLinkEle = UIHelper.findAnElementbyXpath(driver, createSiteLinkXpath);
				String getColourObjForCreateSiteLink = createSiteLinkEle.getAttribute("class");

				if (getColourObjForCreateSiteLink.equalsIgnoreCase("theme-color-1")
						|| getColourObjForCreateSiteLink.contains("theme-color-1")) {
					isDisplayedBlueTint = true;
				} else {
					isDisplayedBlueTint = false;
				}
			} else {
				isDisplayedBlueTint = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedBlueTint;
	}

	// Switch tab to recent tab
	public void switchtab(int tab) {
		try {

			UIHelper.waitFor(driver);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tab));
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

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

				report.updateTestLog("Login", "Enter login credentials: " + "Username = " + userName, Status.DONE);
			}

			report.updateTestLog("Navigated to link page", "Navigate to link page successfully", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Navigated to link page", "Navigate to link page failed", Status.FAIL);
		}

	}

	// Customize Site Dashlet
	public void customizeDashboardFrmSiteDashboard() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, custSiteListArrowXpath);
			UIHelper.click(driver, custSiteListArrowXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, custDashboardXpath);
			UIHelper.highlightElement(driver, custDashboardXpath);
			UIHelper.click(driver, custDashboardXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addDashletBtnXPath);
			UIHelper.highlightElement(driver, addDashletBtnXPath);
			UIHelper.click(driver, addDashletBtnXPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check and select Dashlet
	public AlfrescoHomePage checkDashletInHomePage() {
		try {
			selectFilterValFromDropdownInMyTasksDashlet();

			WebElement tasksContainerEle = UIHelper.findAnElementbyXpath(driver, tasksContainerInMyTasksDashlet);
			UIHelper.highlightElement(driver, tasksContainerEle);
			UIHelper.mouseOveranElement(driver, tasksContainerEle);
			UIHelper.waitFor(driver);

			WebElement subTasksContainerEle = UIHelper.findAnElementbyXpath(driver, subTaskContainerXpath);
			UIHelper.highlightElement(driver, subTasksContainerEle);
			UIHelper.mouseOveranElement(driver, subTasksContainerEle);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoHomePage(scriptHelper);
	}

	// Select dashlet
	public AlfrescoHomePage moveDashletfrmHomePage(String header) {
		try {

			String finalDashletHeaderXpath = dashletHeaderXpath.replace("CRAFT", header);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalDashletHeaderXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalDashletHeaderXpath));
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, finalDashletHeaderXpath));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoHomePage(scriptHelper);
	}

	// Select My searches or All searches
	public AlfrescoHomePage searchSavedSearchType(String type) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySearchesXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, mySearchesXpath));
			if (type.equalsIgnoreCase("All Searches")) {
				UIHelper.click(driver, allSearchesXpath);
				report.updateTestLog("Select All searches", "All searches seleted successfully", Status.DONE);
			} else {
				UIHelper.click(driver, mySearchesXpath);
				report.updateTestLog("Select My searches", "My searches seleted successfully", Status.DONE);
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select My searches/All searches", "Failed to select My searches/All searches",
					Status.FAIL);
		}

		return new AlfrescoHomePage(scriptHelper);
	}

	// select value from dashlet
	public AlfrescoHomePage selectValueFrmDashletList(String listXpath, String value) {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, listXpath);
			List<WebElement> listOfValues = driver.findElements(By.xpath(listXpath));

			for (WebElement values : listOfValues) {
				UIHelper.scrollToAnElement(values);
				if (values.getText().equalsIgnoreCase(value)) {
					UIHelper.highlightElement(driver, values);
					UIHelper.clickanElement(values);
					report.updateTestLog("Select given value from dashlet",
							"Given value selected successfully" + "<br><b> Slected value : </b>" + values.getText(),
							Status.DONE);
					break;
				} /*
					 * else {
					 * report.updateTestLog("Select given value from dashlet",
					 * "Given value is not selected", Status.FAIL); }
					 */

				UIHelper.waitForPageToLoad(driver);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select given value from dashlet", "Given value is not selected", Status.FAIL);
		}

		return new AlfrescoHomePage(scriptHelper);
	}

	// Delete List values from Saved searches
	public AlfrescoHomePage deleteValueFrmDashletList(String listXpath, String value) {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
			List<WebElement> listOfValues = driver.findElements(By.xpath(listXpath));

			for (WebElement values : listOfValues) {
				UIHelper.scrollToAnElement(values);
				if (values.getText().equalsIgnoreCase(value)) {

					UIHelper.highlightElement(driver, savedSearchDelXpath);
					String finalDelXpath = savedSearchDelXpath.replace("CRAFT", values.getText());
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalDelXpath);
					UIHelper.findAnElementbyXpath(driver, finalDelXpath).click();
					UIHelper.waitForVisibilityOfEleByXpath(driver, savedSearchConXpath);
					UIHelper.findAnElementbyXpath(driver, savedSearchConXpath).click();
					UIHelper.waitFor(driver);
					
					report.updateTestLog("Delete existing Saved search",
							"Existing saved search deleted successfully </br><b>Saved Search:</b>" + value,
							Status.PASS);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,messageEleXpath );
					
					break;
				}
				UIHelper.waitForPageToLoad(driver);

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Delete existing Saved search", "Existing saved search is not deleted", Status.FAIL);
		}

		return new AlfrescoHomePage(scriptHelper);
	}

	// Select List values from All searches
	public AlfrescoHomePage selectValFrmAllSearches(String srchQryVal) {

		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, moreSrchXPath);
			UIHelper.click(driver, moreSrchXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, allSrchXPath);
			UIHelper.click(driver, allSrchXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, lstAllSrchXPath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(myTaskPagesXpath, driver);

			int noOfPages = noOfPagesEle.size();

			for (int index = 0; index <= noOfPages; index++) {
				List<WebElement> listOfValues = driver.findElements(By.xpath(lstAllSrchXPath));
				for (WebElement values : listOfValues) {

					if (values.getText().equalsIgnoreCase(srchQryVal)) {
						values.click();
						report.updateTestLog("Open Existing Saved search from All Searches",
								"Existing saved search " + values.getText() + " opened successfully", Status.PASS);
						UIHelper.waitForPageToLoad(driver);
						index = noOfPages + 1;
						break;

					}

				}

				UIHelper.click(driver, nextPageXpath);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return new AlfrescoHomePage(scriptHelper);
	}

	// Check favorite sites in 'My Sites' Dashlet in Home Page
	public boolean isDisplayedFavoriteSitesInHomePage() {
		boolean isDisplayedFavoriteSites = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, listOfFavoriteSitesXpath);
			UIHelper.highlightElement(driver, listOfFavoriteSitesXpath);
			List<WebElement> listOfFavoriteSites = driver.findElements(By.xpath(listOfFavoriteSitesXpath));
			favoriteSitesCount = listOfFavoriteSites.size();
			for (WebElement ele : listOfFavoriteSites) {
				if (UIHelper.checkForAnWebElement(ele)) {
					isDisplayedFavoriteSites = true;
				} else {
					isDisplayedFavoriteSites = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFavoriteSites;
	}

	// Check favorite sites in 'My Sites' Dashlet in Home Page
	public boolean isDisplayedAllSitesInHomePage() {
		boolean isDisplayedAllSites = false;
		try {
			List<WebElement> listOfAllSites = driver.findElements(By.xpath(listOfsitesHeaderLinkXpath));
			allSitesCount = listOfAllSites.size();
			for (WebElement ele : listOfAllSites) {
				if (UIHelper.checkForAnWebElement(ele)) {
					isDisplayedAllSites = true;
				} else {
					isDisplayedAllSites = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedAllSites;
	}

	// Get sites from 'My Sites' Dashlet in Home Page
	public ArrayList<String> getSitesFromMySitesDashletInHomePage() {
		ArrayList<String> sitesList = new ArrayList<String>();
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, listOfsitesHeaderLinkXpath);
			List<WebElement> listOfSites = driver.findElements(By.xpath(listOfsitesHeaderLinkXpath));
			for (WebElement ele : listOfSites) {
				sitesList.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sitesList;
	}

	// Check site in 'My Sites' Dashlet
	public boolean checkSiteInMySitesDashlet(String siteName) {
		boolean isDisplayedSiteInMySitesDashlet = false;
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInMySitesDashlet);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpathInMySitesDashlet);
			List<WebElement> listOfSites = driver.findElements(By.xpath(listOfsitesHeaderLinkXpath));
			for (WebElement ele : listOfSites) {
				if (ele.getText().trim().equalsIgnoreCase(siteName)) {
					isDisplayedSiteInMySitesDashlet = true;
					break;
				} else {
					isDisplayedSiteInMySitesDashlet = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedSiteInMySitesDashlet;
	}

	// Click on site item in 'My Sites' Dashlet
	public void clickOnSiteItemInMYSitesDashlet(String siteName) {
		try {
			List<WebElement> listOfSites = driver.findElements(By.xpath(listOfsitesHeaderLinkXpath));
			for (WebElement ele : listOfSites) {
				if (ele.getText().trim().contains(siteName)) {
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

					report.updateTestLog("Click on Site in 'My Sites' dashlet",
							"User clikced on site using site name: " + siteName, Status.DONE);

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Make site as favorites from 'MySite' dashlet
	public void makeSiteAsFavorite(String siteName) {
		boolean isChangedSiteToFavorite = false;
		boolean isDisplayedSite = false;
		try {
			List<WebElement> listOfSites = driver.findElements(By.xpath(listOfsitesHeaderLinkXpath));

			for (WebElement ele : listOfSites) {
				String actualSiteName = ele.getText().trim();
				if (actualSiteName.contains(siteName)) {
					isDisplayedSite = true;
					UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					UIHelper.waitFor(driver);

					isChangedSiteToFavorite = changeSiteToFavoriteSite(siteName, actualSiteName);

					break;
				} else {
					isDisplayedSite = false;
				}
			}

			if (!isDisplayedSite) {
				report.updateTestLog("Make Site as 'Favorite' Site in 'My Sites' Dashlet",
						"Site: " + siteName + " not found", Status.DONE);
			}

			if (isChangedSiteToFavorite) {
				report.updateTestLog("Verify Favorite Site in 'My Sites' dashlet",
						"Site: " + siteName + " displayed as favorite site", Status.PASS);
			} else {
				report.updateTestLog("Verify Favorite Site in 'My Sites' dashlet",
						"Site: " + siteName + " failed to display as favorite site", Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Make site as favorites from 'MySite' dashlet
	public void makeSiteAsFavoriteWithoutReport(String siteName) {
		try {
			List<WebElement> listOfSites = driver.findElements(By.xpath(listOfsitesHeaderLinkXpath));

			for (WebElement ele : listOfSites) {
				String actualSiteName = ele.getText().trim();
				if (actualSiteName.contains(siteName)) {
					UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					UIHelper.waitFor(driver);

					changeSiteToFavoriteSite(siteName, actualSiteName);

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Change Site to Favorite and check site changed to favorite site
	public boolean changeSiteToFavoriteSite(String expectedSiteName, String actualSiteName) {
		boolean isChangedSiteToFavoriteSite = false;

		try {
			String finalXpathForSiteUnFavoriteLink = tempXpathForSiteUnFavoriteLink.replace("CRAFT", actualSiteName);
			String finalXpathForSiteFavoriteLink = tempXpathForSiteFavoriteLink.replace("CRAFT", actualSiteName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSiteUnFavoriteLink)) {
				isChangedSiteToFavoriteSite = makeFavouriteSiteInMySites(finalXpathForSiteUnFavoriteLink,
						finalXpathForSiteFavoriteLink, expectedSiteName);
			} else {
				WebElement favoriteSiteIconEle = UIHelper.findAnElementbyXpath(driver, finalXpathForSiteFavoriteLink);
				UIHelper.scrollToAnElement(favoriteSiteIconEle);
				UIHelper.highlightElement(driver, favoriteSiteIconEle);
				UIHelper.waitFor(driver);

				UIHelper.clickanElement(favoriteSiteIconEle);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteUnFavoriteLink);
				UIHelper.waitFor(driver);

				isChangedSiteToFavoriteSite = makeFavouriteSiteInMySites(finalXpathForSiteUnFavoriteLink,
						finalXpathForSiteFavoriteLink, expectedSiteName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isChangedSiteToFavoriteSite;
	}

	// MakeFavourite Site
	public boolean makeFavouriteSiteInMySites(String finalXpathForSiteUnFavoriteLink,
			String finalXpathForSiteFavoriteLink, String expectedSiteName) {
		boolean isChangedSiteToFavoriteSite = false;
		try {
			UIHelper.click(driver, finalXpathForSiteUnFavoriteLink);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteFavoriteLink);
			UIHelper.waitFor(driver);

			report.updateTestLog("Make Site as 'Favorite' Site in 'My Sites' Dashlet",
					"Changed Site as 'Favorite' site using site name: " + expectedSiteName, Status.DONE);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSiteFavoriteLink)) {

				WebElement favoriteSiteIconEle = UIHelper.findAnElementbyXpath(driver, finalXpathForSiteFavoriteLink);
				UIHelper.scrollToAnElement(favoriteSiteIconEle);
				UIHelper.highlightElement(driver, favoriteSiteIconEle);
				UIHelper.waitFor(driver);
				isChangedSiteToFavoriteSite = true;
			} else {
				isChangedSiteToFavoriteSite = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isChangedSiteToFavoriteSite;
	}

	// Make site as favorites from 'MySite' dashlet
	public boolean checkFavoriteSite(String siteName) {
		boolean isDisplayedFavoriteSite = false;
		boolean isDisplayedSite = false;
		try {
			List<WebElement> listOfSites = driver.findElements(By.xpath(listOfsitesHeaderLinkXpath));

			for (WebElement ele : listOfSites) {
				String actualSiteName = ele.getText().trim();
				if (actualSiteName.contains(siteName)) {
					isDisplayedSite = true;

					String finalXpathForSiteFavoriteLink = tempXpathForSiteFavoriteLink.replace("CRAFT",
							actualSiteName);

					String finalXpathForSelectedSiteNextSiteItem = tempXpathForSelectedSiteNextSiteItem.replace("CRAFT",
							actualSiteName);

					WebElement finalEleForSelectedSiteNextSiteItem = UIHelper.findAnElementbyXpath(driver,
							finalXpathForSelectedSiteNextSiteItem);

					UIHelper.scrollToAnElement(finalEleForSelectedSiteNextSiteItem);

					if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSiteFavoriteLink)) {

						WebElement favoriteSiteIconEle = UIHelper.findAnElementbyXpath(driver,
								finalXpathForSiteFavoriteLink);
						UIHelper.mouseOveranElement(driver, favoriteSiteIconEle);
						isDisplayedFavoriteSite = true;
					} else {
						isDisplayedFavoriteSite = false;
					}

					break;
				} else {
					isDisplayedSite = false;
				}
			}

			if (!isDisplayedSite) {
				report.updateTestLog("Make Site as 'Favorite' Site in 'My Sites' Dashlet",
						"Site: " + siteName + " not found", Status.DONE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFavoriteSite;
	}

	// Make site as favorites from 'MySite' dashlet
	public void removeSiteFromFavorites(String siteName) {
		boolean isRemovedSiteFromFavorites = false;
		boolean isDisplayedSite = false;
		try {
			List<WebElement> listOfSites = driver.findElements(By.xpath(listOfsitesHeaderLinkXpath));

			for (WebElement ele : listOfSites) {
				String actualSiteName = ele.getText().trim();
				if (actualSiteName.contains(siteName)) {
					isDisplayedSite = true;
					UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					UIHelper.waitFor(driver);

					isRemovedSiteFromFavorites = changeSiteToUnFavoriteSite(siteName, actualSiteName);

					break;
				} else {
					isDisplayedSite = false;
				}
			}

			if (!isDisplayedSite) {
				report.updateTestLog("Remove Site from 'Favorite' Site in 'My Sites' Dashlet",
						"Site: " + siteName + " not found", Status.DONE);
			}

			if (isRemovedSiteFromFavorites) {
				report.updateTestLog("Verify Remove Site  from 'Favorites' in 'My Sites' dashlet",
						"Site: " + siteName + " removed successfully from favorite sites", Status.PASS);
			} else {
				report.updateTestLog("Verify Remove Site  from 'Favorites' in 'My Sites' dashlet",
						"Site: " + siteName + " failed to remove from favorite sites", Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Change Site to Favorite and check site changed to favorite site
	public boolean changeSiteToUnFavoriteSite(String expectedSiteName, String actualSiteName) {
		boolean isChangedSiteToUnFavoriteSite = false;

		try {
			String finalXpathForSiteUnFavoriteLink = tempXpathForSiteUnFavoriteLink.replace("CRAFT", actualSiteName);
			String finalXpathForSiteFavoriteLink = tempXpathForSiteFavoriteLink.replace("CRAFT", actualSiteName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSiteUnFavoriteLink)) {

				WebElement unFavoriteSiteIconEle = UIHelper.findAnElementbyXpath(driver,
						finalXpathForSiteUnFavoriteLink);
				UIHelper.scrollToAnElement(unFavoriteSiteIconEle);
				UIHelper.highlightElement(driver, unFavoriteSiteIconEle);
				UIHelper.waitFor(driver);

				report.updateTestLog("Remove Site from 'Favorite' Site in 'My Sites' Dashlet",
						"Site: " + expectedSiteName + " removed from 'Favorite' sites", Status.DONE);

				isChangedSiteToUnFavoriteSite = true;
				;
			} else {

				isChangedSiteToUnFavoriteSite = makeUnFavouriteSiteInMySites(finalXpathForSiteUnFavoriteLink,
						finalXpathForSiteFavoriteLink, expectedSiteName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isChangedSiteToUnFavoriteSite;
	}

	// MakeFavourite Site
	public boolean makeUnFavouriteSiteInMySites(String finalXpathForSiteUnFavoriteLink,
			String finalXpathForSiteFavoriteLink, String expectedSiteName) {
		boolean isChangedSiteToUnFavoriteSite = false;
		try {
			UIHelper.click(driver, finalXpathForSiteFavoriteLink);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteUnFavoriteLink);
			UIHelper.waitFor(driver);

			report.updateTestLog("Remove Site from 'Favorite' Site in 'My Sites' Dashlet",
					"Site: " + expectedSiteName + " removed from 'Favorite' sites", Status.DONE);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSiteUnFavoriteLink)) {

				WebElement unFavoriteSiteIconEle = UIHelper.findAnElementbyXpath(driver,
						finalXpathForSiteUnFavoriteLink);
				UIHelper.scrollToAnElement(unFavoriteSiteIconEle);
				UIHelper.highlightElement(driver, unFavoriteSiteIconEle);
				UIHelper.waitFor(driver);
				isChangedSiteToUnFavoriteSite = true;
			} else {
				isChangedSiteToUnFavoriteSite = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isChangedSiteToUnFavoriteSite;
	}

	// word to xml conversion report-success
	public void selectFileLinkFrmSuccessReport(String fileName) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, successXpath);
			UIHelper.highlightElement(driver, successXpath);
			String finalLinkXpath = linkXpath.replace("CRAFT", fileName);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, finalLinkXpath);
			UIHelper.highlightElement(driver, finalLinkXpath);
			UIHelper.click(driver, finalLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Select xml link from report",
					"Xml link selected successfully" + "<br/><b>File Name :</b>" + fileName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select xml link from report",
					"Failed to select Xml link" + "<br/><b>File Name :</b>" + fileName, Status.FAIL);
		}

	}

	// word to xml conversion report-Failure
	public void selectFileLinkFrmFailedReport(String fileName) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, successXpath);
			UIHelper.highlightElement(driver, failedXpath);
			String finalLinkXpath = linkXpath.replace("CRAFT", fileName);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, finalLinkXpath);
			UIHelper.highlightElement(driver, finalLinkXpath);
			report.updateTestLog("Select xml link from Failed report",
					"Xml link selected successfully" + "<br/><b>File Name :</b>" + fileName, Status.PASS);

			UIHelper.click(driver, finalLinkXpath);
			UIHelper.waitForPageToLoad(driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select xml link from Failed report",
					"Failed to select Xml link" + "<br/><b>File Name :</b>" + fileName, Status.FAIL);
		}

	}

	// Click on user Menu and Click Help
	public void UserMenucommonMethod(String Option) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.click(driver, siteUserMenuXpath);

			String finaltempUserOption = tempUserOption.replace("CRAFT", Option);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// finaltempUserOption);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, finaltempUserOption);
			report.updateTestLog("Navigate to " + Option, "Navigate to " + Option + " Passed", Status.PASS);
			UIHelper.click(driver, finaltempUserOption);
			UIHelper.waitFor(driver);
			// UIHelper.waitForPageToLoad(driver);
			//UIHelper.highlightElement(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitle);

		} catch (Exception e) {
			report.updateTestLog("Navigate to " + Option, "Navigate to " + Option + " failed", Status.FAIL);
		}
	}

	// Filter with test data in My Documents Dashlet
	public void filterMyDocumentsDashletData(String filterOptionVal) {
		try {

			String finalDocumentsFilterDropdownValXpath = myDocumentsDashletFilterDropdownValXpath.replace("CRAFT",
					filterOptionVal);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myDocumentsDashletFilterDropdownXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, myDocumentsDashletFilterDropdownXpath));
			UIHelper.highlightElement(driver, myDocumentsDashletFilterDropdownXpath);
			UIHelper.click(driver, myDocumentsDashletFilterDropdownXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalDocumentsFilterDropdownValXpath);
			UIHelper.click(driver, finalDocumentsFilterDropdownValXpath);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, firstDocumentItemXpathInMyDocumentsDashletForAll);

			UIHelper.waitFor(driver);

			WebElement myDocumentsContainerEle = UIHelper.findAnElementbyXpath(driver,
					documentsContainerXpathInMyDocumentsDashlet);
			UIHelper.highlightElement(driver, myDocumentsContainerEle);
			UIHelper.mouseOveranElement(driver, myDocumentsContainerEle);
			UIHelper.waitFor(driver);

			report.updateTestLog("Filter the documents in 'My Documents' Dashlet",
					"Documents filtered successfully with" + filterOptionVal + " in 'My Documents' Dashlet",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Wait for expected document in My Documents Dashlet
	public void waitForFilteredItemInMyDocumentsDashlet(String documentName) {
		try {
			String finalXpathForDocumentItem = tempXpathForDocumentItem.replace("CRAFT", documentName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDocumentItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check favorite document in 'My Documents' Dashlet in Home Page
	public boolean isDisplayedFavoriteDocumentInHomePage(String documentName) {
		boolean isDisplayedDocument = false;
		try {
			List<WebElement> listOfDocuments = driver.findElements(By.xpath(listOfMyDocumentsXpath));
			for (WebElement ele : listOfDocuments) {
				if (ele.getText().trim().contains(documentName)) {
					isDisplayedDocument = true;
					break;
				} else {
					isDisplayedDocument = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedDocument;
	}

	// Check Dashlet is displayed in Home Page
	public boolean checkExpectedDashletInHomePage(String dashletName) {
		boolean isDisplayedDashletInHomePage = false;
		try {
			String finalXpathForDashletInHomePage = tempXpathForDashletInHomePage.replace("CRAFT", dashletName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForDashletInHomePage)) {
				isDisplayedDashletInHomePage = true;
			} else {
				isDisplayedDashletInHomePage = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedDashletInHomePage;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isContentEditingDashletAdded() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, contentEditingHeaderXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, contentEditingHeaderXpath))) {
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
	 * @param activityDid
	 * @param editedFileName
	 * @param siteName
	 * @return
	 */
	public boolean isContentEditingDisplayedInContentEditingDashlet(String activityDid, String editedFileName,
			String siteName, String timeInfo) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, contentEditingHeaderXpath);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, contentEditingHeaderXpath)) {
				UIHelper.highlightElement(driver, contentEditingHeaderXpath);

				List<WebElement> contentEditingFileNameList = UIHelper
						.findListOfElementsbyXpath(contentEditingFileNameListXpath, driver);
				List<WebElement> contentEditingList = UIHelper.findListOfElementsbyXpath(contentEditingListXpath,
						driver);

				int index = 0;
				for (WebElement webElement : contentEditingFileNameList) {
					if (UIHelper.isWebElementDisplayed(webElement)) {
						try {
							UIHelper.scrollToAnElement(webElement);
						} catch (Exception e) {
						}
						String contentEditFileNameText = webElement.getText();

						String contentEdiText = "";
						try {
							contentEdiText = contentEditingList.get(index).getText();
						} catch (Exception e) {
						}
						if (contentEditFileNameText.contains(editedFileName) 
								&& contentEdiText.contains(siteName)) {
							flag = true;
							UIHelper.highlightElement(driver, webElement);
							UIHelper.highlightElement(driver, contentEditingList.get(index));
							UIHelper.scrollToAnElement(contentEditingList.get(index));

							break;
						} else {
							flag = false;
						}
					}
					index++;
				}

			} else {
				report.updateTestLog("Verify 'Content I'm Editing' Dashlet",
						"<b>Dashlet:</b>" + "Content I'm Editing" + " failed to display in User Dashboard",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * report.
			 * updateTestLog("Verify that a record is displayed in the 'Content I'm Editing' dashlet"
			 * ,
			 * "Verify that a record is displayed in the 'Content I'm Editing' dashlet Failed"
			 * , Status.FAIL);
			 */
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param activityDid
	 * @return
	 */
	public boolean isUserActivityDisplayedInContentEditingDashlet(String activityDid) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, contentEditingHeaderXpath);
			UIHelper.highlightElement(driver, contentEditingHeaderXpath);

			List<WebElement> contentEditingList = UIHelper.findListOfElementsbyXpath(contentEditingListXpath, driver);

			for (WebElement webElement : contentEditingList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					String contentEdiText = webElement.getText();
					System.out.println("Content VALUE : " + contentEdiText);
					if (contentEdiText.contains(activityDid)) {
						UIHelper.highlightElement(driver, webElement);
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify that User Activity not displayed in the 'Content I'm Editing' dashlet",
					"Verify that User Activity not displayed in the 'Content I'm Editing' dashlet Failed", Status.FAIL);
		}
		return flag;
	}

	// Check Site Dashlets in Site Dashboard Page
	public boolean checkDashletInUserDashboard(String dashletName) {
		boolean isDisplayedSiteDashlet = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitFor(driver);
			String finalDashletName = addedDashletHomePgXPath.replace("CRAFT", dashletName);
			if (UIHelper.checkForAnElementbyXpath(driver, finalDashletName)) {
				isDisplayedSiteDashlet = true;
			} else {
				isDisplayedSiteDashlet = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedSiteDashlet;
	}

	// Check edit task and view task icon available
	public void checkEditAndViewTaskIconAvail() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myTasksLabelXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitforPresenceOfAllElements(driver, taskListXpath);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, taskListXpath));
			if (UIHelper.checkForAnElementbyXpath(driver, editTaskIconXpath)
					&& UIHelper.checkForAnElementbyXpath(driver, viewTaskIconXpath)) {

				UIHelper.highlightElement(driver, editTaskIconXpath);
				UIHelper.highlightElement(driver, viewTaskIconXpath);

				report.updateTestLog("Verify Edit task and view task icon displayed",
						"Edit task and view task icon displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Edit task and view task icon displayed",
						"Edit task and view task icon are not displayed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Edit task and view task icon displayed",
					"Failed to display Edit task and view task icon", Status.FAIL);
		}
	}

	// Check title of the page with params
	public void validatepagetitle(String title) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String ActualTitle = UIHelper.findAnElementbyXpath(driver, commonPageTitle).getAttribute("title");
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitle);
			UIHelper.highlightElement(driver, commonPageTitle);
			UIHelper.waitFor(driver);
			if (ActualTitle.equalsIgnoreCase(title)) {
				report.updateTestLog("Verify Page title", ActualTitle + "Page has been displayed as expected.",
						Status.PASS);

			} else {
				report.updateTestLog("Verify Page title",
						ActualTitle + "Page has been displayed instead of " + title + "page.", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Page title", "Expected Page was not displayed.", Status.FAIL);
		}
	}

	/**
	 * Method to check the Menu option not available
	 * 
	 * @author 317085
	 */
	public void isMenuOptionAvail(String menu) {

		try {
			String finalmenuOptXpath = menuOptXpath.replace("CRAFT", menu);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// finalmenuOptXpath);
			if (!UIHelper.checkForAnElementbyXpath(driver, finalmenuOptXpath)) {
				// UIHelper.highlightElement(driver, adminPageXpath);

				report.updateTestLog("Verify " + menu + " Menu Option",
						"Expected" + menu + " Menu Page was not displayed.", Status.PASS);

			} else {

				report.updateTestLog("Verify " + menu + " Menu Option", "Expected" + menu + " Menu Page was displayed.",
						Status.FAIL);
			}

		} catch (Exception e) {

			e.printStackTrace();
			report.updateTestLog("Verify " + menu + " Menu Option", "Expected" + menu + " Menu Page was displayed.",
					Status.FAIL);
		}

	}

	/**
	 * @author 412766
	 */
	public void clickUserNameDropDown() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, userDropDownXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, userDropDownXpath))) {
				UIHelper.highlightElement(driver, userDropDownXpath);
				UIHelper.click(driver, userDropDownXpath);
				report.updateTestLog("Click UserName Dropdown Menu", "Clicked successfully", Status.PASS);
			} else {
				report.updateTestLog("Click UserName Dropdown Menu", "Not able to Click", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickAlfrescoForumMenu() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, alfrescoForumMenuXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, alfrescoForumMenuXpath))) {
				UIHelper.highlightElement(driver, alfrescoForumMenuXpath);
				report.updateTestLog("Click 'Alfresco Forum' Menu", "Clicked successfully", Status.PASS);
				UIHelper.click(driver, alfrescoForumMenuXpath);
			} else {
				report.updateTestLog("Click 'Alfresco Forum' Menu", "Not able to Click", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickTrainingMenu() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, trainingMenuXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, trainingMenuXapth))) {
				UIHelper.highlightElement(driver, trainingMenuXapth);
				report.updateTestLog("Click 'Training' Menu", "Clicked successfully", Status.PASS);
				UIHelper.click(driver, trainingMenuXapth);
			} else {
				report.updateTestLog("Click 'Training' Menu", "Not able to Click", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Navigate to Tasks page
	public void clickOnApplicationMenuHeader(String menuHeaderName, String minutes, String negOrPoscase) {
		try {

			String finalXpathForMenuHeaderName = tempXpathForMenuHeaderName.replace("CRAFT", menuHeaderName);

			UIHelper.waitForPageToLoad(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMenuHeaderName);
			} catch (Exception e) {
				
			}
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForMenuHeaderName)) {
				UIHelper.click(driver, finalXpathForMenuHeaderName);

				report.updateTestLog("Click on '" + menuHeaderName + "'",
						"User clicked the '" + menuHeaderName + "' @ " + minutes + " time", Status.DONE);

				report.updateTestLog("Verify the current time",
						"Current Time: " + new DateUtil().getCurrentDateAndTime(), Status.DONE);

				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);

				if (negOrPoscase.equalsIgnoreCase("Negative")) {
					if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForMenuHeaderName)) {
						report.updateTestLog("Navigate To '" + menuHeaderName + "' Page", "Navigated to '"
								+ menuHeaderName + "' Page successfully" + "<br /><b> Page Title : </b>" + "Home",
								Status.PASS);
					} else {
						report.updateTestLog("Navigate To '" + menuHeaderName + "' Page",
								"Failed to navigate to '" + menuHeaderName + "' Page", Status.FAIL);
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
	
	// common method to edit site dashboard ::Customize Site Dashlet
	public void commoncustDd(String option) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitesettingbtn);
			UIHelper.click(driver, sitesettingbtn);
			String finalsitesettingopt = sitesettingopt
					.replace("CRAFT", option);
		//	UIHelper.waitForVisibilityOfEleByXpath(driver, sitesettingbtn);
			//UIHelper.click(driver, sitesettingbtn);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalsitesettingopt);
			UIHelper.highlightElement(driver, finalsitesettingopt);
			UIHelper.click(driver, finalsitesettingopt);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Verify site setting", "Custom site " + option
					+ "option clicked", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify site setting", "Custom site " + option
					+ "option not clicked", Status.FAIL);
		}
	}

	// common method to edit site dashboard ::Customize Site Dashlet update later when required
	public void editsite(String value) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editsiteoverlay);
			UIHelper.waitForVisibilityOfEleByXpath(driver, edittitle);
			UIHelper.findAnElementbyXpath(driver, edittitle).clear();
			UIHelper.highlightElement(driver, edittitle);
			UIHelper.sendKeysToAnElementByXpath(driver, edittitle, value);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, sitenamecheckXpath);
			UIHelper.highlightElement(driver, editokbtn);
			UIHelper.click(driver, editokbtn);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			if (UIHelper.getTextFromWebElement(driver, commonPageTitleXpath)
					.equalsIgnoreCase(value)) {
				report.updateTestLog("Update site details",
						"Site details has been updated " + value, Status.PASS);

			} else {
				report.updateTestLog("Update site details",
						"Site details has not been updated " + value,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Update site details",
					"Site details has not been updated " + value, Status.FAIL);
		}
	}
	
	
	// intellisense testing
	public void IntelliSensesearch(String searchQuery) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.sendKeysToAnElementByXpath(driver, searchBox, searchQuery);
			UIHelper.waitForVisibilityOfEleByXpath(driver, livesearchblock);
			UIHelper.highlightElement(driver, liveresultblock);

			report.updateTestLog("Perform IntelliSense Simple Search",
					"IntelliSense Search preformed successfully"
							+ "<br /><b> Search Query : </b>" + searchQuery,
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Perform Simple Search",
					"Search preformed failed", Status.FAIL);
		}
	}

	// intellisense search all content option
	public void searchallcontent(String searchQuery, String site) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, liveRepsearch);
			UIHelper.highlightElement(driver, liveRepsearch);
			UIHelper.click(driver, liveRepsearch);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, liveresult);

			if (UIHelper.getTextFromWebElement(driver, liveresult)
					.contains(searchQuery)
					) {
				UIHelper.highlightElement(driver, liveresult);
				UIHelper.highlightElement(driver, liveressite);
				report.updateTestLog(
						"Perform IntelliSense Simple Search in all content",
						"IntelliSense Simple Search in all content successfully"
								+ "<br /><b> Search Query : </b>" + searchQuery
								+ "<b> Search site : </b>" 
								+ UIHelper.getTextFromWebElement(driver, liveressite), Status.PASS);

			} else {
				report.updateTestLog(
						"Perform IntelliSense Simple Search in all content",
						"Search preformed failed", Status.FAIL);

			}
		} catch (Exception e) {
			report.updateTestLog(
					"Perform IntelliSense Simple Search in all content",
					"Search preformed failed", Status.FAIL);
		}
	}

	// intellisense search all site testing
	public void searchsitecontent(String searchQuery, String site) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, livesitesearch);
			UIHelper.highlightElement(driver, livesitesearch);
			UIHelper.click(driver, livesitesearch);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, liveresult);
			if (UIHelper.getTextFromWebElement(driver, liveresult)
					.contains(searchQuery)
					&& UIHelper.getTextFromWebElement(driver, liveressite)
							.equalsIgnoreCase(site)) {
				UIHelper.highlightElement(driver, liveresult);
				UIHelper.highlightElement(driver, liveressite);
				report.updateTestLog(
						"Perform IntelliSense Simple Search in site content",
						"IntelliSense Simple Search in site content successfully"
								+ "<br /><b> Search Query : </b>" + searchQuery
								+ "<b> Search site : </b>" + site, Status.PASS);
			} else {
				report.updateTestLog(
						"Perform IntelliSense Simple Search in site content",
						"Search preformed failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Perform IntelliSense Simple Search in site content",
					"Search preformed failed", Status.FAIL);
		}
	}
	
	
	public boolean isUserActivityDisplayedSiteContentDashlet(String activityDid) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitecontentDashletHeaderXpath);
			UIHelper.highlightElement(driver, sitecontentDashletHeaderXpath);

			List<WebElement> contentEditingList = UIHelper.findListOfElementsbyXpath(sitecontentListXpath, driver);

			for (WebElement webElement : contentEditingList) {
				System.out.println(webElement);
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					String contentEdiText = webElement.getText();
					
					//System.out.println("Content VALUE : " + contentEdiText);
					if (contentEdiText.contains(activityDid)) {
						UIHelper.highlightElement(driver, webElement);
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify that User Activity not displayed in the 'Recently Modified' dashlet",
					"Verify that Site Content Activity not displayed in the 'recently Modified' dashlet Failed", Status.FAIL);
		}
		return flag;
	}
	
	public boolean ClickActivityDisplayedinSiteContentDashlet(String activityDid) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitecontentDashletHeaderXpath);
			UIHelper.highlightElement(driver, sitecontentDashletHeaderXpath);

			List<WebElement> contentEditingList = UIHelper.findListOfElementsbyXpath(sitecontentListXpath, driver);

			for (WebElement webElement : contentEditingList) {
				//System.out.println(webElement);
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					String contentEdiText = webElement.getText();
					
					//System.out.println("Content VALUE : " + contentEdiText);
					if (contentEdiText.contains(activityDid)) {
						UIHelper.highlightElement(driver, webElement);
						UIHelper.clickanElement(webElement);
						String title = driver.findElement(By.xpath(titlenameindocPreviewXpath)).getAttribute("title");
						if(title.contains(activityDid)){
							report.updateTestLog("verify navigation of smart link object",
									"Table smart link "+activityDid+"object is created in site activities and  navigation of smart link object", Status.PASS);
						
						}else{
							report.updateTestLog("verify navigation of smart link object",
									"Table smart link "+activityDid+"object is not created in site activities and navigation of smart link object", Status.FAIL);
						
						}
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify that User Activity not displayed in the 'Recently Modified' dashlet",
					"Verify that Site Content Activity not displayed in the 'recently Modified' dashlet Failed", Status.FAIL);
		}
		return flag;
	}
	
	
	// Check if any bulk jobs are available for the user
		public void isBulkJobpresent(String period, String jobType, String jobstatus) {
			try {
				
				String finalbulkDurations = bulkDurations.replace("CRAFT", period);
				String finalbulkjobtype = bulkDurations.replace("CRAFT", jobType);
				String finalbulkstatus = bulkDurations.replace("CRAFT", jobstatus);
				if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, isBulkDashletAvailable))) {
					UIHelper.click(driver, bulkDurationbutton);
					UIHelper.click(driver, finalbulkDurations);
					
					UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
					UIHelper.click(driver, bulkTypeButton);
					UIHelper.click(driver, finalbulkjobtype);
					UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
					UIHelper.click(driver, bulkstatusbutton);
					UIHelper.click(driver, finalbulkstatus);
					UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
					UIHelper.click(driver, bulkgetJobsBtn);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, bulkIDData);
					if (UIHelper.findAnElementbyXpath(driver, bulkIDData).isDisplayed()) {
						report.updateTestLog("Get Bulk Job",
								"Get Bulk Job for from Dashlet successfully", Status.PASS);
						if(jobstatus.equals("Failed")) {
							
							List<WebElement> bulkActionButtonList = UIHelper.findListOfElementsbyXpath(bulkActionbutton, driver);
							bulkActionButtonList.get(0).click();

							UIHelper.waitForPageToLoad(driver);

							report.updateTestLog("Get Bulk Failed Job",
									"Get Bulk Faield Job from Dashlet and check for errors successfully",
									Status.PASS);
							
						}
						
					} else {
						report.updateTestLog("Get Bulk Job ", "Get Bulk Job from Dashlet Failed",
								Status.FAIL);
					}
				}
			} catch (Exception e) {
				report.updateTestLog("Get Bulk Job ", "Get Bulk Job from Dashlet Failed",
						Status.FAIL);
			}
		}
		
		
		public boolean isBulkJobpresentAsExpected(String period, String jobType, String jobstatus,String jobID) {
			boolean flag=false;
			try {
				
				String finalbulkDurations = bulkDurations.replace("CRAFT", period);
				String finalbulkjobtype = bulkDurations.replace("CRAFT", jobType);
				String finalbulkstatus = bulkDurations.replace("CRAFT", jobstatus);
				if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, isBulkDashletAvailable))) {
					UIHelper.click(driver, bulkDurationbutton);
					UIHelper.click(driver, finalbulkDurations);
					
					UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
					UIHelper.click(driver, bulkTypeButton);
					UIHelper.click(driver, finalbulkjobtype);
					UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
					UIHelper.click(driver, bulkstatusbutton);
					UIHelper.click(driver, finalbulkstatus);
					UIHelper.waitForVisibilityOfEleByXpath(driver, bulkRowCount);
					UIHelper.click(driver, bulkgetJobsBtn);
					UIHelper.waitFor(driver);
					//UIHelper.waitForVisibilityOfEleByXpath(driver, bulkIDData);
					if (UIHelper.getTextFromWebElement(driver, bulkIDData).contains(jobID)) {
						flag=true;							
						}
				} 
				
			} catch (Exception e) {
				report.updateTestLog("Get Bulk Job ", "Get Bulk Job from Dashlet Failed",
						Status.FAIL);
			}
			
			return flag;
		}
		
		// To get the user option value
		public String getUserOptionValue(String option) {
			String value="";
			try {
				String finalUserOption=tempUserOption.replace("CRAFT", option);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalUserOption);
				value=UIHelper.getTextFromWebElement(driver, finalUserOption);
				report.updateTestLog("Get User Option value <b>"+option+"</b> under User profile","Get User Option value successfull",
						Status.PASS);
			}catch(Exception e) {
				report.updateTestLog("Get User Option value <b>"+option+"</b> under User profile","Get User Option value failed",
						Status.FAIL);
			}
			
			return value;
		}
		

}
