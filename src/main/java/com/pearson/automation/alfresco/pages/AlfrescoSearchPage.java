package com.pearson.automation.alfresco.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.RobotUtil;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * Functional Components class
 * 
 * @author Cognizant
 */
public class AlfrescoSearchPage extends ReusableLibrary {

	public String searchFieldXpath = ".//*[@id='HEADER_SEARCHBOX_FORM_FIELD']";
	private String searchResultsContainerXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]";
	private String searchButtonXpath = ".//*[contains(@id,'uniqName')]//span[contains(.,'Search')]";
	public String searchResultsListXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a";

	public String searchImageIconXpath = ".//*[@id='HEADER_SEARCH_BOX_DROPDOWN_MENU']/img";
	public String advancedSearchXpath = ".//*[@id='HEADER_SEARCH_BOX_ADVANCED_SEARCH_text']/a";
	public String fullTextXpath = ".//*[@id='default-keywords-text']";
	//public String searchBtnXpath = ".//*[@id='bd']//*[@id='page_x002e_search_x002e_advsearch']//*[contains(@id,'default-search-button-2')]//span[contains(.,'Search')]/button";
	public String searchBtnXpath = ".//*[@id='bd']//*[@id='page_x002e_search_x002e_advsearch']//*[contains(@id,'default-search-button-2')]//span/button";
	public String searchmodifierXpath = ".//div[@class='share-form']//*[@name='prop_cm_modifier']";
	public String searchNameXpath = ".//div[@class='share-form']//*[@name='prop_cm_name']";
	private String loadingApsectsXpath = ".//*[@id='aspect-search-panel']//*[contains(.,'Loading Aspects')]";
	private String additionalPropDropdownXpath = ".//*[@id='aspects-dropdown-0']";
	public String searchTitleXpath = ".//div[@class='share-form']//*[@name='prop_cm_title']";
	public String searchDescXpath = ".//div[@class='share-form']//*[@name='prop_cm_description']";
	public String searchAssetXpath = ".//div[@class='share-form']//*[@name='prop_mimetype']";
	public String fromDateXpath = ".//*[@id='page_x002e_search_x002e_advsearch_x0023_default_0_prop_cm_modified-cntrl-date-from']";
	private String searchListNames = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a";
	public String tempBreadcrumblabel = ".//span[@class='label']/a[contains(text(),'CRAFT')]";
	public String tempsearchresultclick = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a//*[contains(text(),'CRAFT')]";
			//*[@class='nameAndTitleCell']//a//span[@class='value']";
	public String tempFileNameNames = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a//*[text()='CRAFT']";
	private String tempFiletitleXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a//*[text()='CRAFT']//ancestor::tr//td//span[@id='FCTSRCH_SEARCH_RESULT_TITLE']//span[@class='value']";
	private String tempFileDescXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a//*[text()='CRAFT']//ancestor::tr//td//span[@id='FCTSRCH_SEARCH_RESULT_DESCRIPTION']//span[@class='value']";
	private String tempFileLastModifiedXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a//*[text()='CRAFT']//ancestor::tr//td//span[@id='FCTSRCH_SEARCH_RESULT_DATE']//span[@class='value']";
	//private String tempFileModifierXpath = "//*[@class='nameAndTitleCell']//a/span[text()='CRAFT']//ancestor::td//*[@class='dateCell']//*[@class='value']";
	private String tempFileModifierXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a//*[text()='CRAFT']//ancestor::tr//td//span[@id='FCTSRCH_SEARCH_RESULT_DATE']//span[@class='value']";
	
//	private String tempFileSizeXpath = "//*[@class='nameAndTitleCell']//a/span[text()='CRAFT']//ancestor::td//*[@class='sizeCell']//*[@class='value']";
	private String tempFileSizeXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a//*[text()='CRAFT']//ancestor::tr//td//span[@id='FCTSRCH_SEARCH_RESULT_SIZE']//span[@class='value']";
	private String tempFilePathXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a//*[text()='CRAFT']//ancestor::tr//td//span[@id='FCTSRCH_SEARCH_RESULT_PATH']//span[@class='value']";
	//private String tempFileSiteXpath = "//*[@class='nameAndTitleCell']//a/span[text()='CRAFT']//ancestor::td//*[@class='siteCell']//*[@class='value']";
	private String tempFileSiteXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a//*[text()='CRAFT']//ancestor::tr//td//span[@id='FCTSRCH_SEARCH_RESULT_SITE']//span[@class='value']";
	private String tempNameXpath = "//*[@class='nameAndTitleCell']//a//span[text()='CRAFT']";

	
	private String searchListNamesNew = "//*[@class='nameAndTitleCell']//a//span[@class='value']";
	public String tempFileNameNamesNew = "//*[@class='nameAndTitleCell']//a//span//mark[text()='CRAFT']";
	private String tempFiletitleXpathNew = "//*[@class='nameAndTitleCell']//a//span//mark[text()='CRAFT']//ancestor::td//*[@class='nameAndTitleCell']/span[2]//*[@class='value']";
	private String tempFileDescXpathNew = "//*[@class='nameAndTitleCell']//a//span//mark[text()='CRAFT']//ancestor::td//*[@class='descriptionCell']//*[@class='value']";
	private String tempFileLastModifiedXpathNew = "//*[@class='nameAndTitleCell']//a//span//mark[text()='CRAFT']//ancestor::td//*[@class='dateCell']//*[@class='value']/span";
	//private String tempFileModifierXpath = "//*[@class='nameAndTitleCell']//a/span[text()='CRAFT']//ancestor::td//*[@class='dateCell']//*[@class='value']";
	private String tempFileModifierXpathNew = "//*[@class='nameAndTitleCell']//a//span//mark[text()='CRAFT']//ancestor::td//*[@class='dateCell']//*[@class='alfresco-renderers-DateLink']//*[@class='value']";
	
//	private String tempFileSizeXpath = "//*[@class='nameAndTitleCell']//a/span[text()='CRAFT']//ancestor::td//*[@class='sizeCell']//*[@class='value']";
	private String tempFileSizeXpathNew = "//*[@class='nameAndTitleCell']//a//span//mark[text()='CRAFT']//ancestor::td//*[@class='dateCell']//*[@id='FCTSRCH_SEARCH_RESULT_SIZE']//*[@class='value']";
	private String tempFilePathXpathNew = "//*[@class='nameAndTitleCell']//a//span//mark[text()='CRAFT']//ancestor::td//*[@class='pathCell']//*[@class='value']";
	//private String tempFileSiteXpath = "//*[@class='nameAndTitleCell']//a/span[text()='CRAFT']//ancestor::td//*[@class='siteCell']//*[@class='value']";
	private String tempFileSiteXpathNew = "//*[@class='nameAndTitleCell']//a//span//mark[text()='CRAFT']//ancestor::td//*[@class='dateCell']//*[@id='FCTSRCH_SEARCH_RESULT_SITE']//*[@class='value']";
	private String tempNameXpathNew = "//*[@class='nameAndTitleCell']//a//span//mark[text()='CRAFT']";

	
	
	private String tempMetadataValueXpath = "//div[contains(@class,'metadata')]//*[@class='viewmode-label'][contains(text(),'CRAFT')]//ancestor::div/*[contains(@class,'viewmode-value')]";
	public String AspectPropDropDownXpath = ".//*[@id='aspect-properties-dropdown-0']";
	public String AspectPropDropDown1Xpath = ".//*[@id='aspect-properties-dropdown-1']";
	public String AspectDropDownXpath = ".//*[@id='aspects-dropdown-0']";
	public String AspectDropDown1Xpath = ".//*[@id='aspects-dropdown-1']";
	public String AspectValueXpath = ".//*[@id='aspect-search-text-0']";
	public String AspectValue1Xpath = ".//*[@id='aspect-search-text-1']";
	public String addAspectXpath = ".//*[@id='add-row-button-0']";
	public String aspectPanelXpath = ".//*[@id='aspect-search-panel']//*[contains(.,'Loading Aspects')]";
	public String tempBreadCrumbForFile=".//div[@class='node-info']//h1[contains(text(),'CRAFT')]";

	private ArrayList<String> searchResultsList = new ArrayList<String>();
	private ArrayList<String> searchResultsname = new ArrayList<String>();
	public ArrayList<String> searchNames = new ArrayList<String>();
	public ArrayList<String> titleName = new ArrayList<String>();
	public ArrayList<String> descvalue = new ArrayList<String>();
	private ArrayList<String> lastModified = new ArrayList<String>();
	public ArrayList<String> modifier = new ArrayList<String>();
	public ArrayList<String> filesize = new ArrayList<String>();
	public ArrayList<String> location = new ArrayList<String>();
	public ArrayList<String> siteName = new ArrayList<String>();
	private Map<String, ArrayList<String>> searchValues = new LinkedHashMap<String, ArrayList<String>>();

	private String loadingImage = ".//*[@id='overlay']/a/*[@id='loadingImage']";
	private String openedDocumentContainerLayerXpath = ".//*[@class='textLayer']";
	private String openedDocHeaderXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//h1";

	private String searchThumbnailListXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[2]//a/img";

	public String relevanceXpath = ".//*[@id='FCTSRCH_SORT_MENU_text']";
	private String nameSortXpath = ".//*[@title='Sort by Name']";

	/*** Actions menu ***/
	public String tempactionMouseXpath = "//*[@class='nameAndTitleCell']//a/span/mark[text()='CRAFT']//ancestor::tr//td[@class='actionsCell']";
//	private String tempactionMenuXpath = "//*[@class='nameAndTitleCell']//a/span[text()='CRAFT']//ancestor::tr//td[4]//*[@role='menuitem']";
	public String tempactionMenuXpath = "//*[@class='nameAndTitleCell']//a/span/mark[text()='CRAFT']//ancestor::tr//td[@class='actionsCell']//div[@id='FCTSRCH_SEARCH_RESULT_ACTIONS']";
//	private String actionSubitemXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULT_ACTIONS')]//*[@class='alf-menu-groups']//tr//td[2][text()='CRAFT']";
	
	//public String actionSubitemXpath = ".//*[@id='FCTSRCH_SEARCH_RESULT_ACTIONS_DROPDOWN']//td[text()='CRAFT']";
	
	public String actionSubitemXpath = ".//*[@id='FCTSRCH_SEARCH_RESULT_ACTIONS_DROPDOWN']//td[contains(text(),'CRAFT')]";
	public String actionSubitemXpathNew = ".//*[@id='FCTSRCH_SEARCH_RESULT_ACTIONS_DROPDOWN']//td[contains(text(),'CRAFT')]";
	public String actionloadingXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULT_ACTIONS')]//span[text()='Loading...']";
	private String deleteActionXpath = ".//*[@id='ALF_DELETE_CONTENT_DIALOG']//span[text()='Delete']";
	private String moveCopyActionXpath = ".//div[@id='FCTSRCH_SEARCH_RESULT_ACTIONS_MENU_dropdown']//tr[contains(@aria-label,'CRAFT')]";
		//*[@class='alf-menu-groups']//tr//td[text()[contains(.,'CRAFT')]]";
	//.//*[contains(@id,'FCTSRCH_SEARCH_RESULT_ACTIONS')]//*[@class='alf-menu-groups']//tr//td[2][text()='CRAFT']
	private String targetFolderActionXpath = ".//*[@class='dijitTreeLabel'][text()='My Files']";
	private String MyfilesXpath = ".//*[@id='ALF_COPY_MOVE_DIALOG']//*[@aria-label='My Files ']";
	private String moveCopyBtnXpath = ".//*[@id='ALF_COPY_MOVE_DIALOG']/div[2]/div[2]/span[1]/span";
	private String breadcrumbXpath = ".//*[contains(@class,'crumb documentDroppable')]/span/a[text()='CRAFT']";
	protected String tempFileXpath = "//*[@class='filename']//a[text()='CRAFT']";
	private String tempmoreMenuDocXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::tr/td//*[@id='onActionShowMore']/a";
	private String moreDeleteXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::tr/td//*[@id='onActionDelete']//span";
	//private String confirmDeleteXpath = ".//*[@id='prompt']//button[text()='Delete']";
	private String confirmDeleteXpath=".//*[@id='prompt']//span[@class='yui-button yui-push-button']//button";
	public String viewDocXpath = "//*[@class='nameAndTitleCell']//a//*[text()='CRAFT']//ancestor::tr//td[2]";
	public String viewDocXpathNew = "//*[@class='nameAndTitleCell']//a/span//mark[text()='CRAFT']//ancestor::tr//td[2]";
	
	private String docpropTwisterCloseXpath = ".//*[contains(@class,'document-metadata-header document-details-panel')]/*[contains(@class,'alfresco-twister-closed')]";
	private String docpropTwisterOpenXpath = ".//*[contains(@class,'document-metadata-header document-details-panel')]/*[contains(@class,'alfresco-twister-open')]";

	/*** View Menu ***/
	public String viewBtnXpath = ".//*[@id='FCTSRCH_VIEWS_MENU']";
	private String galleryViewXpath = ".//*[@id='FCTSRCH_VIEWS_MENU_dropdown']//td[3][text()='Gallery View']";
	private String detailedViewXpath = ".//*[@id='FCTSRCH_VIEWS_MENU_dropdown']//td[3][text()='Detailed View']";
	private String galleryResultXpath = ".//*[@id='FCTSRCH_SEARCH_RESULTS_LIST']//*[@widgetid='FCTSRCH_GALLERY_VIEW']";

	/*** Result UI ***/
	public String searchScopeDBXpath = ".//*[@id='FCTSRCH_SCOPE_SELECTION_MENU']";
	public String searchManagerXpath = ".//*[@id='FCTSRCH_CONFIG_PAGE_LINK']";
	public String searchFilterXpath = ".//*[@id='FCTSRCH_SEARCH_FACET_LIST']";
	public String searchCountXpath = ".//*[@id='FCTSRCH_RESULTS_COUNT_LABEL']";
	//public String searchCountXpath = ".//*[@id='FCTSRCH_RESULTS_COUNT_LABEL']//b";
	public String searchingXpath = ".//*[@class='info data-loading-more']";
	public String searchResultXpath = ".//*[@id='FCTSRCH_SEARCH_RESULTS_LIST']";
	public String searchFiltersXpath = ".//*[@id='FCTSRCH_SEARCH_FACET_LIST']//h3[text()='CRAFT']";
	public String selectFiltersXpath = ".//*[@id='FCTSRCH_SEARCH_FACET_LIST']//*[contains(@id,'FILTER')]//*[@class='details']//span[contains(text(),'CRAFT')]";
	public String selectFilterValXpath = ".//*[@id='FCTSRCH_SEARCH_FACET_LIST']//*[contains(@id,'FILTER')]//*[@class='details']//span[contains(text(),'CRAFT')]//ancestor::span//*[@class='hits']";
	private String noofhits;
	public String copybuttonxpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULT_ACTIONS') and text()='Copy to...']";
	/*** Additional Aspect Panel UI ***/
	public String aspectPanelUIXpath = ".//*[@id='aspect-search-panel']";

	/*** Select Search Scope ***/
	private String selectScopeXpath = ".//*[@id='page_x002e_search_x002e_advsearch_x0023_default-selected-form-button-button']//ancestor::span/div//li/span[text()='CRAFT']";
	public String selectAdvScopeXpath = ".//*[@id='page_x002e_search_x002e_advsearch_x0023_default-selected-form-button-button']";
	public String selectResultlocXpath = ".//*[@id='FCTSRCH_SCOPE_SELECTION_MENU']";
	public String selectRepolocXpath = ".//*[@id='FCTSRCH_SET_REPO_SCOPE_text']";
	public String selectSiteslocXpath = ".//*[@id='FCTSRCH_SET_ALL_SITES_SCOPE_text']";
	/*** Tag Search ***/
	private String selectTagXpath = ".//*[contains(@id,'prop_cm_taggable-cntrl-picker-results')]//tr[2]//h3//following::td//a";
	private String selectTagBtnXpath = ".//*[contains(@id,'prop_cm_taggable-cntrl-itemGroupActions')]//button";
	private String TagOKBtnXpath = ".//*[contains(@id,'prop_cm_taggable-cntrl-ok-button')]";
	private String selectedTagXpath = ".//*[contains(@id,'prop_cm_taggable-cntrl-currentValueDisplay')]//div";

	/*** create new filter ***/
	public String createnewfilterxpath = ".//*[@id='CREATE_FACET_BUTTON_label']";
	private String filterID = ".//*[@name='filterID']";
	private String filtername = ".//*[@name='displayName']";
	private String tempDelIconXpath = ".//*[@id='SEARCH_CONFIG_FACET_LIST']//tr//span[text()='Filterbytest']//ancestor::tr//td[9]//img";
	private String cnfsave = "//span[contains(@class,'dijitButtonText') and contains(.,'Save')]";
	private String filterby = ".//*[@id='FCTSRCH_filter_creator']//li[1]//span//span[2]";
	private String resultby = ".//*[@id='FCTSRCH_RESULTS_COUNT_LABEL']/b";
	private String accept = "//span[@class='dijit dijitReset dijitInline alfresco-buttons-AlfButton dijitButton' and text() = 'Yes']";
	private String tableheader = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW']//th//span";
	private String arrow = ".//*[@id='SEARCH_CONFIG_REORDER_ITEM_4']/span[1]/img";
	private String searchby = ".//*[@id='HEADER_SEARCHBOX_FORM_FIELD']";
	private String tempDelbuttonxpath = ".//*[@id='SEARCH_CONFIG_FACET_LIST']//tr//span[text()='Filterbycreate']//ancestor::tr//td[9]//img";
	private String alertconfirm = ".//*[@class='dijitReset dijitInline dijitButtonText' and text() = 'Yes']";
	private String filternamelist = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW_ITEMS']//td[3]";
	private String filternamewait = ".//div[@id='FORM_FILTER_ID']//img[contains(@class,'validationInProgress')]";
	private String filtersave = ".//div[@class='footer']//span[contains(@class,'confirmationButton')]";
	private String filterproperty = ".//input[@id='FORM_FACET_QNAME_CONTROL']";

	private String searchmanager = ".//*[@id='FCTSRCH_CONFIG_PAGE_LINK_text']/a";
	public String filterHeaderID = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW']//th[2]";
	public String filterHeaderProp = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW']//th[4]";
	public String filterHeaderType = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW']//th[5]";
	public String filterHeaderResult = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW']//th[6]";
	public String filterHeaderfilter = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW']//th[7]";
	public String filterHeaderfilterAvail = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW']//th[8]";
	public String filterHeadername = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW']//th[3]";

	/*** Aspect DP ***/
	private String clickAspectOpt = ".//*[contains(@id,'aspects-dropdown')]/option[contains(.,'CRAFT')]";

	/*** Saved Search ***/
	public String rightGadgetXpath = ".//*[@class='alfresco-forms-Form save-search']//form";
	public String saveSearchNameXpath = ".//*[@class='alfresco-forms-Form save-search']//form//div//*[@class='control-row']//*[@name='save_search_name']";
	public String saveSearchDescXpath = ".//*[@class='alfresco-forms-Form save-search']//form//div//*[@class='control-row']//*[@name='save_search_desc']";
	public String saveSearchVisiblitycXpath = ".//*[@class='dijitReset dijitCheckBoxInput' and @value='CRAFT']";
	public String saveSearchBtnXpath = ".//*[@id='uniqName_12_0']";
	public String saveSearchBtn = ".//*[@aria-labelledby='uniqName_12_0_label' and @disabled='true']";
	public String saveSearchVisibTypeXpath = ".//*[contains(@id,'alfresco_forms_controls_RadioButtons') and @class='radio-buttons']";
	public String saveSrcNameXpath = ".//*[contains(@class,'alfresco-forms-controls-BaseFormControl wipe nameField')]";
	public String saveSrcDescXpath = ".//*[contains(@class,'alfresco-forms-controls-BaseFormControl wipe descField')]";
	public String saveSrcprivateXpath = ".//*[@class='dijitReset dijitCheckBoxInput' and @value='private']";
	public String tempChkBoxXpathForSearchResultsItem = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a[contains(.,'CRAFT')]//ancestor::tr//td[1]/span";
	private String tempXpathForSearchResultsItem = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr//td[3]//a[contains(.,'CRAFT')]";
	//private String selectItemsMenuXpath = ".//*[@id='FCTSRCH_SEARCH_LIST_MENU_BAR']//*[contains(@aria-disabled,'false')]/span[contains(.,'Selected items')]";
	private String selectItemsMenuXpath = ".//*[@id='SELECTED_ITEMS_MENU']";
	private String tempXpathForSelectItems = ".//*[contains(@style,'visible')]/*[contains(@class,'alf-menu-groups') and contains(@style,'visible')]//td[text()='CRAFT']";
	// Bulk Download Popup Xapths
	private String bulkDownloadHeaderXpathInPopup = ".//*[contains(@id,'uniqName')]//span[contains(.,'Bulk Download')]";
	private String commentsFieldXpathInBulkDownloadPopup = ".//*[@id='EXPORT-commentTextField']";
	private String okBtnXpathInBulkDownloadPopup = ".//*[contains(@id,'uniqName')]//span[text()='Ok']";
	private String bchlocationDropdown = ".//*[@id='BCHLocationField']";
	private String bchlocationDropdownvalue = ".//*[@id='BCHLocationField']//option[2]";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String tempXpathForMessage = ".//*[@id='message']/div[contains(.,'CRAFT')]";
	private String messageText;
	// All Aspects Template
	private String allAspectsTemplatePopupHeaderXpath = ".//*[contains(@id,'uniqName')]//span[contains(.,'Export Metadata')]";
	private String selectAllBtnXpath = ".//*[contains(@id,'uniqName')]//span[text()='Select All']";
	private String pickedAspectFirstItemXpath = ".//*[@id='CUSTOM_SEARCH_PICKEDITEMS_ITEMS']/tr[1]";
	private String downloadBtnXpath = ".//*[contains(@id,'uniqName')]//span[text()='Download']";
	// Saved Search
	public String searchQueryTxtXpath = ".//*[@class='alfresco-forms-Form save-search']";
	public String savedSearchTypesXpath = ".//*[contains(@class,'dashlet saved-searches')]//div[2]//span";
	//public String selectOptionXpath = ".//*[@id='FCTSRCH_SEARCH_LIST_MENU_BAR']//div[contains(@id,'AlfSelectDocumentListItems')]";
	public String selectOptionXpath = ".//*[@id='SELECTED_ITEMS_MENU_text']";
	private String selectLinkXPath = ".//div[@id='SELECTED_LIST_ITEMS']//span[contains(@class,'arrow')]";
	private String slctListValsXPath = ".//tbody[@class='dijitReset']/tr[contains(@id,'alfresco_menus_AlfMenuItem')]/td[2]";
	private List<WebElement> lstVals;
	private List<String> lstValsTxt;
	private List<WebElement> lstValsSlctDrpdwn;
	private String slctFilesCheckedXPath = ".//*[contains(@class,'alfresco-renderers-Selector alfresco-lists-ItemSelectionMixin--selected')]";
	private String slctFilesUnCheckedXPath = ".//span[@class='alfresco-renderers-Selector']";
	private String dataLodingMoreXpathForSearch = ".//*[@id='FCTSRCH_SEARCH_RESULTS_LIST']//*[contains(@class,'data-loading-more') and contains(.,'Searching')]";
	private String searchResultItemXpath = ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr[1]//td[3]//a[contains(.,'CRAFT')]";
	private String saveSrchBtnXPath = ".//*[@class='buttons']//span[text()='Save']";
	private String NameSrchQry;
	public String testOutputFilePathSveSrch = "src/test/resources/AppTestData/TestOutput/SaveSrchnme.txt";
	// Create Filter Xpaths
	private String createNewFilterPopupHeaderXpath = ".//*[contains(@class,'dijitDialogTitleBar')]//span[contains(.,'Create New Filter')]";
	private String createdSearchFilterHeadings = ".//*[contains(@id,'FCTSRCH')]//h3";
	private String loadingXpath = "//*[@id='SEARCH_CONFIG_FACET_LIST']/*[contains(.,'Loading...')]";
	private String messageLoadXpath = ".//*[@id='SEARCH_CONFIG_FACET_LIST']/*[contains(.,'Please wait while the view is rendered')]";
	private String cnfMessageForCreateFilterXpath = ".//*[contains(@id,'uniqName')]//span[contains(.,'Operation Completed Successfully')]";
	private String cnfMessageForDeleteteFilterXpath = ".//*[contains(@id,'uniqName')]//span[contains(.,'Successfully deleted')]";
	private String tempXpathForFirstFilterItem = ".//*[contains(@id,'FCTSRCH')]//*[contains(@class,'label alfresco-layout-Twister--open') and contains(.,'CRAFT')]//following-sibling::ul/li[1]/span[contains(@class,'details')]/span[1]";
	private String tempXpathForFirstFilterItemTickMark = ".//*[contains(@id,'FCTSRCH')]//*[contains(@class,'label alfresco-layout-Twister--open') and contains(.,'CRAFT')]//following-sibling::ul/li[1]/span[contains(@class,'status')]/span[not(contains(@class,'hidden'))]/img";
	// Delete Filter Xpaths
	private String tempXpathForDeleteFilter = ".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW_ROW']//td[contains(.,'CRAFT')]//following-sibling::td[7]/span";
	private String confirmYesOrNoPopupHeaderXpath = ".//*[@class='dijitDialogTitleBar']/*[contains(.,'Confirm Deletion')]";
	private String deleteYesBtnXpath = ".//*[@class='dijitDialogPaneContent']//span[contains(@class,'dijitButtonNode') and contains(.,'Yes')]";

	// private String dataLodingMoreXpathForSearch =
	// ".//*[@id='FCTSRCH_SEARCH_RESULTS_LIST']//*[contains(@class,'data-loading-more') and contains(.,'Searching')]";
	// private String searchResultItemXpath =
	// ".//*[contains(@id,'FCTSRCH_SEARCH_RESULTS_LIST')]//table//tbody//tr[1]//td[3]//a[contains(.,'CRAFT')]";
	private String overWriteBtnXpath = ".//*[@class='ft']//*[@class='button-group']//button[contains(text(),'Overwrite')]";
	private String renameBtnXpath = ".//*[@class='button-group']//button[Text()='Rename']";
	private String moreSrchXPath = ".//*[@id='link-to-detailedPage']";
	private String allSrchXPath = ".//*[@id='allSearches']/a/span";
	private String lstAllSrchXPath = ".//td[contains(@class,'searchDisplayName')]/div/a";
	private String userListXPath = ".//td[contains(@class,'searchCreator')]/div";
	// private String saveSrchBtnXPath =
	// ".//*[@class='buttons']//span[text()='Save']";
	// public String testOutputFilePathSveSrch =
	// "src/test/resources/AppTestData/TestOutput/SaveSrchnme.txt";
	private String nextBtnXpath = ".//*[@id='paginator-container']//span[contains(Text(),'next')]";
	private String modifiedDateXpath = ".//*[@class='yui-dt-first yui-dt-last']//th[5]//span/a";
	// private String modifiedDateXpath = ".//*[@class='yui-dt-first yui-dt-last']//th//span/a[contains(Text(),'Modified')]"
	private String valXpath = ".//td[contains(@class,'searchDisplayName')]/div/a[contains(Text(),'CRAFT')]";
	private String iIconXPath = ".//span[@title='Click to show more information about CRAFT']";
	private String iIConDilgXPath = ".//span[text()='CRAFT'][@role='heading']";
	private String srtDrpXPath = ".//*[@id='FCTSRCH_SORT_MENU']/span[2]";
	private String modFDrpXPath = ".//td[text()='Modified date']";
	private String srchEleAjaxXPath = ".//*[@id='FCTSRCH_SEARCH_RESULTS_LIST']/div[7]";
	// Verify fileter values
	private String filterTypeXpath = ".//*[@id='FCTSRCH_SEARCH_FACET_LIST']//div[1]//h3[contains(.,'CRAFT')]";
	private String showMoreXpath = ".//*[@id='FCTSRCH_SEARCH_FACET_LIST']//div[1]//h3[contains(.,'CRAFT')]//ancestor::div[2]//li//span[contains(.,'Show More')]";
	private String filterValListXpath = ".//*[@id='FCTSRCH_SEARCH_FACET_LIST']//div[1]//h3[contains(.,'CRAFT')]//ancestor::div[2]//li//span[2]//span[1]";
	// public String NameSrchQry;
	//Added for NALS test
//	private String slectBtnXpathInSearchResultsPage = ".//*[@id='SELECTED_LIST_ITEMS']//span[2]";
	private String slectBtnXpathInSearchResultsPage = ".//*[@id='SELECTED_LIST_ITEMS']//*[@role='presentation']";
	
	private String tempXpathForSelectOption = ".//*[contains(@id,'alfresco_documentlibrary_AlfSelectDocumentListItems') and contains(@style,'visible')]//*[contains(@id,'alfresco_menus_AlfMenuItem') and contains(@title,'CRAFT')]//*[text()='CRAFT']";
	private String selectedItemsMenuOptionXpath = ".//*[contains(@id,'CUSTOM_SELECTED_MENU_dropdown') and contains(@style,'visible')]/*[contains(@class,'alf-menu-groups') and contains(@style,'visible')]//*[contains(@class,'dijitMenuItemLabel')]";
	private String selecteditemsoptionsXpath  = ".//*[@id='SELECTED_ITEMS_ACTIONS_GROUP']//tbody/tr";

	private String lookForFieldValueInAdvancedSearch = ".//*[@class='search']//*[contains(.,'Look for')]//*[contains(@class,'first-child') and contains(.,'Content')]";
	private String tempXpathForLookForFieldVal = ".//*[contains(@id,'default-selected-form-list')]//*[@class='bd']//li/*[contains(@class,'orm-type-name') and contains(text(),'CRAFT')]";
	private String actionsoptionsxpath  = "//*[@id='FCTSRCH_SEARCH_RESULT_ACTIONS_DROPDOWN']//tr";
	// Added for NALS project
	   	
	private String selectCalculateSizeInMenuXpath ="//*[@id='SELECTED_ITEMS_ACTIONS_GROUP'] // tr[8]";	
	
	AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoSearchPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public AlfrescoSearchPage performSearch() {
		String fileName = dataTable.getData("Search", "FullText");
		try {

			commonMethodForPerformSimpleSearch(fileName);

		} catch (Exception e) {
			report.updateTestLog("Perform Search",
					"Search using uploaded file: " + "FileName = " + fileName,
					Status.FAIL);
			e.printStackTrace();
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	public void commonMethodForPerformSimpleSearch(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchFieldXpath);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, searchFieldXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, searchFieldXpath,
					fileName);
			UIHelper.waitFor(driver);
			UIHelper.findAnElementbyXpath(driver, searchFieldXpath).sendKeys(
					Keys.ENTER);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					dataLodingMoreXpathForSearch);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					searchResultsContainerXpath);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
					driver, searchResultsContainerXpath));

			report.updateTestLog("Perform Search",
					"Search using uploaded file: " + "FileName = " + fileName,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AlfrescoSearchPage performAdvancedSearch() {
		try {
			String fileName = dataTable.getData("Search", "FullText");
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchFieldXpath);
			UIHelper.click(driver, searchImageIconXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, advancedSearchXpath);
			UIHelper.click(driver, advancedSearchXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingApsectsXpath);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, fullTextXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, fullTextXpath, fileName);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					dataLodingMoreXpathForSearch);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					searchResultsContainerXpath);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(
					driver, searchResultsContainerXpath));

			report.updateTestLog("Perform Advanced Search",
					"Search using uploaded file: " + "FileName = " + fileName,
					Status.DONE);
		} catch (Exception e) {

		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Get uploaded file/folder name from My Files Page
	public ArrayList<String> getSearchResultsTitle() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			List<WebElement> searchResultsListEle;
		
			searchResultsListEle = driver.findElements(By
					.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				searchResultsList.add(ele.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return searchResultsList;
	}

	public void openSearchResult() {
		try {
			String fileName = dataTable.getData("MyFiles", "FileName");
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By
					.xpath(searchResultsListXpath));

			for (WebElement ele : searchResultsListEle) {
				if (ele.getText().equalsIgnoreCase(fileName)) {
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, loadingImage);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							loadingImage);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							openedDocHeaderXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							openedDocumentContainerLayerXpath);

					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get file/folder name from Search result and put in into a Map with
	// details
	public Map<String, ArrayList<String>> getSearchListValues() {
		try {
			
			searchNames = getSearchResultsTitle();
		
			UIHelper.findandAddElementsToaListforMap(driver, searchNames,
					tempFiletitleXpath, titleName);
			UIHelper.findandAddElementsToaListforMap(driver, searchNames,
					tempFileDescXpath, descvalue);
			UIHelper.findandAddElementsToaListforMap(driver, searchNames,
					tempFileLastModifiedXpath, lastModified);
			UIHelper.findandAddElementsToaListforMap(driver, searchNames,
					tempFileModifierXpath, modifier);
			UIHelper.findandAddElementsToaListforMap(driver, searchNames,
					tempFileSizeXpath, filesize);
			UIHelper.findandAddElementsToaListforMap(driver, searchNames,
					tempFilePathXpath, location);
			UIHelper.findandAddElementsToaListforMap(driver, searchNames,
					tempFileSiteXpath, siteName);

			searchValues.put("Names", searchNames);
			searchValues.put("Title", titleName);
			searchValues.put("Desc", descvalue);
			searchValues.put("LastModified", lastModified);
			searchValues.put("Modifier", modifier);
			searchValues.put("Size", filesize);
			searchValues.put("Path", location);
			searchValues.put("Site", siteName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchValues;
	}

	// Advanced search with User Name
	public AlfrescoSearchPage searchWithUserName() {
		try {

			String SearchUser = dataTable.getData("Search", "Query");
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchmodifierXpath);
			if (UIHelper.findAnElementbyXpath(driver, searchmodifierXpath)
					.isDisplayed()) {
				UIHelper.findAnElementbyXpath(driver, searchmodifierXpath)
						.sendKeys(SearchUser);
				UIHelper.waitFor(driver);
				report.updateTestLog("Perform Advanced Search",
						"Search using User Name "
								+ "<br /><b> User Name Query : </b>"
								+ SearchUser, Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Perform Advanced Search",
					"Search using User Name failed.No search result found",
					Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Perform File Name search
	public AlfrescoSearchPage fileNamesearch() {
		try {
			UIHelper.waitFor(driver);
			String searchQuery = dataTable.getData("Search", "Query");
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, searchNameXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, searchNameXpath,
					searchQuery);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, searchBtnXpath));
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Perform Search", "Search using fileName "
					+ "Query = " + searchQuery, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// input File Name in Advanced search
	public AlfrescoSearchPage inputFileNameAdvSearch() {
		String searchQuery = dataTable.getData("Search", "FileName");
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchNameXpath);
			if (UIHelper.findAnElementbyXpath(driver, searchNameXpath)
					.isDisplayed()) {
				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, searchNameXpath));
				UIHelper.sendKeysToAnElementByXpath(driver, searchNameXpath,
						searchQuery);
				report.updateTestLog("Input File Name",
						"Search using fileName "
								+ " <br /><b>FileName Query: </b>"
								+ searchQuery, Status.DONE);
			}

		} catch (Exception e) {
			report.updateTestLog("Input File Name",
					"Search using fileName failed", Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// input keyword Name in Advanced search
	public AlfrescoSearchPage inputKeywordSearch() {
		String searchQuery = dataTable.getData("Search", "Query");
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, fullTextXpath)
					.isDisplayed()) {
				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, fullTextXpath));
				UIHelper.sendKeysToAnElementByXpath(driver, fullTextXpath,
						searchQuery);

				report.updateTestLog("Input keyword Name",
						"Search using keyword " + "<br /><b>Query : </b>"
								+ searchQuery, Status.DONE);
			}

		} catch (Exception e) {
			report.updateTestLog("Input keyword Name",
					"Search using keyword failed", Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Select search Scope Name in Advanced search
	public AlfrescoSearchPage searchScope() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectAdvScopeXpath);
			UIHelper.click(driver, selectAdvScopeXpath);
			UIHelper.waitFor(driver);
			String searchscope = dataTable.getData("Search", "Actions");
			String finalselectscopeXpath = selectScopeXpath.replace("CRAFT",
					searchscope);
			UIHelper.click(driver, finalselectscopeXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Select Search Scope", "Search scope selected"
					+ searchscope, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// input Title in Advanced search
	public AlfrescoSearchPage inputTitleAdvSearch() {
		try {
			UIHelper.waitFor(driver);
			String searchTitle = dataTable.getData("Search", "Title");
			if (UIHelper.findAnElementbyXpath(driver, searchTitleXpath)
					.isDisplayed()) {
				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, searchTitleXpath));
				UIHelper.sendKeysToAnElementByXpath(driver, searchTitleXpath,
						searchTitle);

				report.updateTestLog("Input Title ",
						"Search using Title completed "
								+ "<br /><b>Title Query : </b>" + searchTitle,
						Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Input Title ", "Search using Title failed ",
					Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// input Desc in Advanced search
	public AlfrescoSearchPage inputDescAdvSearch() {
		try {
			UIHelper.waitFor(driver);
			String searchDesc = dataTable.getData("Search", "Description");
			if (UIHelper.findAnElementbyXpath(driver, searchAssetXpath)
					.isDisplayed()) {
				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, searchDescXpath));
				UIHelper.sendKeysToAnElementByXpath(driver, searchDescXpath,
						searchDesc);

				report.updateTestLog("Input Desc ", "Search using Desc "
						+ " <br /><b>Desc Query : </b>" + searchDesc,
						Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Input Desc ", "Search using Desc failed",
					Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Input Asset in Advanced search
	public AlfrescoSearchPage inputAssetAdvSearch() {
		try {
			String Asset = dataTable.getData("Search", "AssetType");
			if (UIHelper.findAnElementbyXpath(driver, searchAssetXpath)
					.isDisplayed()) {
				UIHelper.highlightElement(driver,

				UIHelper.findAnElementbyXpath(driver, searchAssetXpath));
				Select selectBox = new Select(driver.findElement(By
						.xpath(searchAssetXpath)));
				selectBox.selectByVisibleText(Asset);
				report.updateTestLog("Input Asset Name",
						"Search using Asset Name successfully"
								+ "<br /><b> Asset type Query : </b>" + Asset,
						Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Input Asset Name",
					"Search using Asset Name failed", Status.FAIL);
		}

		return new AlfrescoSearchPage(scriptHelper);
	}

	// Click search button in Advanced search
	public AlfrescoSearchPage clickSearch() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchBtnXpath);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, searchBtnXpath));
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		//	UIHelper.waitForVisibilityOfEleByXpath(driver, searchingXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchingXpath);
			UIHelper.waitforPresenceOfAllElements(driver, searchCountXpath);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
					searchCountXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			

			if (UIHelper.checkForAnElementbyXpath(driver, searchCountXpath)) {
				report.updateTestLog(
						"Perform Adv Search",
						"Adv Search Performed"
								+ "<br /><b>No of search result found :</b> "
								+ UIHelper.findAnElementbyXpath(driver,
										searchCountXpath).getText(),
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Perform Adv Search",
					"Adv Search results not found", Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Click delete button in is search result
	public AlfrescoSearchPage clickDeleteinActionMenu() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteActionXpath);
			UIHelper.findAnElementbyXpath(driver, deleteActionXpath);
			UIHelper.click(driver, deleteActionXpath);
			/*
			 * UIHelper.waitForPageToLoad(driver); UIHelper.waitFor(driver);
			 */
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Move or copy in search result
	public AlfrescoSearchPage moveorCopyinActionMenu() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, MyfilesXpath);
			UIHelper.click(driver, MyfilesXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, targetFolderActionXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, moveCopyBtnXpath);

		} catch (Exception e) {
			// e.printStackTrace();
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Get Metadata value search
	public String getMetadata(String FileName, String Metadata) {
		String MetadataValue = "";
		try {
			String Resultfile = tempFileNameNames.replace("CRAFT", FileName);
			UIHelper.click(driver, Resultfile);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, docpropTwisterCloseXpath)) {
				UIHelper.click(driver, docpropTwisterCloseXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, docpropTwisterOpenXpath);
				UIHelper.waitFor(driver);
			}
			String FinalMetadataValueXpath = tempMetadataValueXpath.replace(
					"CRAFT", Metadata);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					FinalMetadataValueXpath));
			UIHelper.highlightElement(driver, FinalMetadataValueXpath);
			MetadataValue = UIHelper.findAnElementbyXpath(driver,
					FinalMetadataValueXpath).getText();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return MetadataValue;
	}

	// Input Aspect in Advanced search
	public AlfrescoSearchPage inputAspectAdvSearch() {
		String Aspect = null;
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					aspectPanelXpath);
			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver,
					AspectDropDownXpath);
			
/*			if(properties.getProperty("ApplicationUrl").contains("pearsoncms")) {*/
				 Aspect = dataTable.getData("Search", "Aspect");
		/*	}else {
				 Aspect = dataTable.getData("Search", "AWSAspect");
			}*/
			
			String AspectProp = dataTable.getData("Search", "AspectProp");
			String Aspectvalue = dataTable.getData("Search", "Query");
			Select selectBox = new Select(dropdownEle);
			selectBox.selectByVisibleText(Aspect);
			Select selectpropBox = new Select(driver.findElement(By
					.xpath(AspectPropDropDownXpath)));
			selectpropBox.selectByVisibleText(AspectProp);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, AspectValueXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, AspectValueXpath,
					Aspectvalue);
			if (UIHelper.findAnElementbyXpath(driver, AspectPropDropDownXpath)
					.isEnabled()) {
				report.updateTestLog("Input Aspect Name",
						"Search using Aspect Name "
								+ "<br /><b>Aspect Name :</b>" + Aspect
								+ "<br /><b>Aspect Properties :</b>"
								+ AspectProp + "<br /><b>Aspect Value :</b>"
								+ Aspectvalue, Status.DONE);
			} else {
				report.updateTestLog("Input Aspect Name",
						"Input aspect value in Adv search Failed ", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Input Aspect Name",
					"Input aspect value in Adv search Failed ", Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Input Aspect in Advanced search
	public void inputAspectAdvSearch(String aspect, String aspectProp,
			String aspectvalue) {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					aspectPanelXpath);
			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver,
					AspectDropDownXpath);

			Select selectBox = new Select(dropdownEle);
			selectBox.selectByVisibleText(aspect);
			Select selectpropBox = new Select(driver.findElement(By
					.xpath(AspectPropDropDownXpath)));
			selectpropBox.selectByVisibleText(aspectProp);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, AspectValueXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, AspectValueXpath,
					aspectvalue);
			if (UIHelper.findAnElementbyXpath(driver, AspectPropDropDownXpath)
					.isEnabled()) {
				report.updateTestLog("Input Aspect Name",
						"Search using Aspect Name "
								+ "<br /><b>Aspect Name :</b>" + aspect
								+ "<br /><b>Aspect Properties :</b>"
								+ aspectProp + "<br /><b>Aspect Value :</b>"
								+ aspectvalue, Status.DONE);
			} else {
				report.updateTestLog("Input Aspect Name",
						"Input aspect value in Adv search Failed ", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Input Aspect Name",
					"Input aspect value in Adv search Failed ", Status.FAIL);
		}
	}

	// Input Aspect2 in Advanced search
	public AlfrescoSearchPage inputMultiAspectAdvSearch() {
		String Aspect,Aspect1 =null;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, AspectDropDown1Xpath);
			
			
			if(properties.getProperty("ApplicationUrl").contains("pearsoncms")) {
				 Aspect = dataTable.getData("Search", "Aspect");
			}
				
			else {
				 Aspect = dataTable.getData("Search", "AWSAspect");
			}
			
			
			if(properties.getProperty("ApplicationUrl").contains("pearsoncms")) {
				 Aspect1 = dataTable.getData("Search", "Aspect1");
			}else {
				 Aspect1 = dataTable.getData("Search", "AWSAspect1");
			}
			
			
			String Aspect1Prop = dataTable.getData("Search", "AspectProp1");
			String Aspectvalue = dataTable.getData("Search", "Query");
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, AspectDropDown1Xpath));
			Select selectBox = new Select(driver.findElement(By
					.xpath(AspectDropDown1Xpath)));
			selectBox.selectByVisibleText(Aspect);
			Select selectpropBox = new Select(driver.findElement(By
					.xpath(AspectPropDropDown1Xpath)));
			selectpropBox.selectByVisibleText(Aspect1Prop);
			UIHelper.sendKeysToAnElementByXpath(driver, AspectValue1Xpath,
					Aspectvalue);
			if (UIHelper.findAnElementbyXpath(driver, AspectValue1Xpath)
					.isEnabled()) {
				report.updateTestLog("Input Multi Aspect Name",
						"Search using Aspect Name "
								+ "<br /><b>Aspect Name :</b>" + Aspect
								+ "<br /><b>Aspect Properties :</b>"
								+ Aspect1Prop + "<br /><b>Aspect Value :</b>"
								+ Aspectvalue, Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Input Multi Aspect Name",
					"Search using Aspect Name Failed ", Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Click add aspect button in Advanced search
	public AlfrescoSearchPage clickAddAspectBtn() {
		try {
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, addAspectXpath));
			UIHelper.click(driver, addAspectXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSearchPage(scriptHelper);
	}


	// Click action in search result.
	public AlfrescoSearchPage performActionBtn() {
		try {
			getSearchListValues();
			UIHelper.waitFor(driver);
			String fileName = dataTable.getData("Search", "FileName");
			String action = dataTable.getData("Search", "Actions");
			if (searchNames.size() > 0) {
				for (String fileResult : searchNames) {
					if (fileResult.equalsIgnoreCase(fileName)) {
						String Resultfile = tempactionMouseXpath.replace(
								"CRAFT", fileResult);
						UIHelper.mouseOveranElement(driver, UIHelper
								.findAnElementbyXpath(driver, Resultfile));
						String finalactionMenuXpath = tempactionMenuXpath
								.replace("CRAFT", fileResult);
						UIHelper.findAnElementbyXpath(driver,
								finalactionMenuXpath).click();
						UIHelper.waitFor(driver);
						String finalactionSubitemXpath = actionSubitemXpath
								.replace("CRAFT", action);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, actionloadingXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, finalactionSubitemXpath);
						
						UIHelper.click(driver, finalactionSubitemXpath);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitFor(driver);

						report.updateTestLog("Verify " +action +" search result",
								
								"Seacrh result is retireved and " + action
										+ " is preformed", Status.PASS);
					}
					break;
				}

			} else {
				report.updateTestLog("Verify search result",
						"Seacrh result is empty", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify search result",
					"Seacrh result is empty", Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Click Move Copy action in search result.
	public AlfrescoSearchPage performMoveCopyActionBtn() {
		try {
			getSearchListValues();
			UIHelper.waitFor(driver);
			String fileName = dataTable.getData("Search", "FileName");
			String action = dataTable.getData("Search", "Actions");
			if (searchNames.size() > 0) {
				for (String fileResult : searchNames) {
					if (fileResult.equalsIgnoreCase(fileName)) {
						String Resultfile = tempactionMouseXpath.replace(
								"CRAFT", fileResult);
						UIHelper.mouseOveranElement(driver, UIHelper
								.findAnElementbyXpath(driver, Resultfile));
						String finalactionMenuXpath = tempactionMenuXpath
								.replace("CRAFT", fileResult);
						UIHelper.findAnElementbyXpath(driver,
								finalactionMenuXpath).click();
						UIHelper.waitFor(driver);
						String finalactionSubitemXpath = actionSubitemXpathNew
								.replace("CRAFT", action);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, actionloadingXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, finalactionSubitemXpath);
						
						UIHelper.click(driver, finalactionSubitemXpath);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitFor(driver);

						report.updateTestLog("Verify " +action +" search result",
								
								"Seacrh result is retireved and " + action
										+ " is preformed", Status.PASS);
					}
					break;
				}

			} else {
				report.updateTestLog("Verify search result",
						"Seacrh result is empty", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Perform Copy/Move action",
					"Perform Copy/Move action failed", Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	/**
	 * Method to check the thumbnail display in search result, and return true
	 * if it is displayed.
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isThumbnailDisplayed() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchThumbnailListEle = driver.findElements(By
					.xpath(searchThumbnailListXpath));
			for (WebElement webElement : searchThumbnailListEle) {
				UIHelper.highlightElement(driver, webElement);
				if (UIHelper.isWebElementDisplayed(webElement)) {
					String src = webElement.getAttribute("src");
					if (src.contains("thumbnails")) {
						flag = true;
					} else {
						flag = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Get the result of search sorted
	public void sortingOfResult() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, relevanceXpath);
			UIHelper.click(driver, relevanceXpath);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, nameSortXpath);
			UIHelper.click(driver, nameSortXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			getSearchListValues();
			UIHelper.waitFor(driver);
			if (searchNames.size() > 0) {
				report.updateTestLog("Sort search result",
						"Search result sorted by Name", Status.DONE);
			} else {
				report.updateTestLog("Sort search result",
						"Search result not sorted", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Sort search result",
					"Search result not sorted", Status.FAIL);
		}

	}

	// select search result view
	public void selectGalleryView() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.click(driver, viewBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, galleryViewXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, galleryResultXpath)
					.isDisplayed()) {
				report.updateTestLog("Gallery View",
						"Seacrh result displayed in Gallery View", Status.DONE);
			} else {
				report.updateTestLog("Gallery View",
						"Seacrh result displayed in Gallery View", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Gallery View",
					"Seacrh result displayed in Gallery View", Status.FAIL);
		}

	}

	// select search result view
	public void selectdetailedView() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.click(driver, viewBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, detailedViewXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			getSearchListValues();
			UIHelper.waitFor(driver);
			if (searchNames.size() > 0) {
				report.updateTestLog("Detailed View",
						"Seacrh result displayed in Detailed View", Status.DONE);
			} else {
				report.updateTestLog("Detailed View",
						"Seacrh result not displayed in Detailed View",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Detailed View",
					"Seacrh result not displayed in Detailed View", Status.FAIL);
		}

	}

	// Select tag in Advanced search
	public AlfrescoSearchPage tagSearch() {
		try {
			UIHelper.waitFor(driver);
			String searchTag = dataTable.getData("Search", "Query");
			/*String finalselectTagXpath = selectTagXpath.replace("CRAFT",
					searchTag);*/
			UIHelper.click(driver, selectTagBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, selectTagXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, TagOKBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			if (UIHelper.findAnElementbyXpath(driver, selectedTagXpath)
					.isDisplayed()) {

				report.updateTestLog("Tag seleted", "Search using " + "Tag = "
						+ searchTag, Status.DONE);
			} else {
				report.updateTestLog("Tag seleted", "Search using " + "Tag = "
						+ searchTag, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Blank space followed by numbers Name in Advanced search
	public AlfrescoSearchPage blankSpacesSearch() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, fullTextXpath));
			UIHelper.click(driver, fullTextXpath);
			RobotUtil.pressSpaces();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Blank search follwed by query",
					"Blank search follwed by query successfully", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Blank search follwed by query",
					"Blank search follwed by query failed", Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// input Start Date in Advanced search
	public AlfrescoSearchPage inputModifiedStartDate(String Value) {
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, fromDateXpath)
					.isDisplayed()) {
				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, fromDateXpath));
				UIHelper.sendKeysToAnElementByXpath(driver, fromDateXpath,
						Value);
				report.updateTestLog("Input Start Date",
						"Search using Start Date " + "Query = " + Value,
						Status.DONE);
			} else {

				frameworkParameters.setStopExecution(true);
				report.updateTestLog("Input Start Date",
						"Search using Start Date " + "Query = " + Value,
						Status.FAIL);
			}
		} catch (Exception e) {
			frameworkParameters.setStopExecution(true);
			report.updateTestLog("Input Start Date", "Search using Start Date "
					+ "Query = " + Value, Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Get Folder Metadata value search
	public String getFolderMetadata(String FileName, String Metadata) {
		String MetadataValue = "";
		try {
			String resultfile = tempFileNameNames.replace("CRAFT", FileName);
			UIHelper.click(driver, resultfile);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String viewDetails = breadcrumbXpath.replace("CRAFT", FileName);
			UIHelper.click(driver, viewDetails);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String FinalMetadataValueXpath = tempMetadataValueXpath.replace(
					"CRAFT", Metadata);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					FinalMetadataValueXpath));
			UIHelper.highlightElement(driver, FinalMetadataValueXpath);
			MetadataValue = UIHelper.findAnElementbyXpath(driver,
					FinalMetadataValueXpath).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MetadataValue;
	}

	// Filter data
	public void searchUsingFilter() {
		try {
			String filter = dataTable.getData("Search", "Actions");
			System.out.println(filter);
			UIHelper.waitFor(driver);
			String type = dataTable.getData("Search", "Result");
			System.out.println(type);
			WebElement Filtername = driver
					.findElement(By
							.xpath("//div[@class='alfresco-layout-Twister alfresco-documentlibrary-AlfDocumentFilters separated']//h3[contains(text(),'"
									+ filter + "')]"));
			Filtername.click();
			Filtername.click();
			WebElement Filtervalue = driver.findElement(By
					.xpath("//span[@class='filterLabel' and text() = '" + type
							+ "']"));
			Filtervalue.click();
			report.updateTestLog("Input Filter",
					"Search using filter and type " + "type=+type"
							+ "filter = " + filter, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyResult() {
		String filternametresult = driver.findElement(By.xpath(filterby))
				.getText();
		UIHelper.waitFor(driver);
		String searchresult = driver.findElement(By.xpath(resultby)).getText();
		if (filternametresult.equals(searchresult)) {
			report.updateTestLog(
					"Verify that the  number of search results populated ",
					"Number of search results populated is tracked "
							+ "<br /><b> filternametresult : </b>"
							+ "<br /><b> searchresult : </b>"
							+ UIHelper.findAnElementbyXpath(driver, filterby)
									.getText(), Status.PASS);

		} else {

			report.updateTestLog(
					"Verify that the number of search results populated ",
					"Failed to display search results"
							+ "<br /><b> filternametresult : </b>"
							+ "<br /><b> searchresult : </b>"
							+ UIHelper.findAnElementbyXpath(driver, filterby)
									.getText(), Status.FAIL);
		}
	}

	public void searchManager() {
		UIHelper.click(driver, searchmanager);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		report.updateTestLog("SearchManager",
				"searchmanager clicked sucessfully ", Status.DONE);
	}

	public void createNewFilter() {
		try {
			UIHelper.click(driver, createnewfilterxpath);
			UIHelper.waitFor(driver);
			String Query = dataTable.getData("Search", "Query");
			String actions = dataTable.getData("Search", "Actions");
			UIHelper.sendKeysToAnElementByXpath(driver, filterID, Query);
			UIHelper.sendKeysToAnElementByXpath(driver, filtername, actions);
			UIHelper.waitFor(driver);
			driver.findElement(By.xpath(cnfsave)).click();
			UIHelper.waitFor(driver);
			String str = driver.findElement(By.xpath(filtername)).getAttribute(
					"value");
			ArrayList<String> filterNameList = new ArrayList<String>();
			UIHelper.findandAddElementsToaListforBulk(driver,
					".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW_ITEMS']//td[3]",
					filterNameList);
			if (filterNameList.contains(str)) {
				report.updateTestLog(
						"Verify  the  CreateNewFilter ",
						"Verify the NewFilter is Created "
								+ "<br /><b> filterNameList : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										filternamelist).getText(), Status.PASS);

			} else {
				report.updateTestLog(
						"Verify  the  CreateNewFilter ",
						"Verify the NewFilter is Created "
								+ "<br /><b> filterNameList : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										filternamelist).getText(), Status.FAIL);

			}
			String searchQuery = dataTable.getData("Search", "FileName");
			driver.findElement(By.xpath(searchby)).sendKeys(searchQuery,
					Keys.ENTER);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			WebElement filtersearch = driver
					.findElement(By
							.xpath("//div[@class='alfresco-layout-Twister alfresco-documentlibrary-AlfDocumentFilters separated']//h3[contains(text(),'"
									+ str + "')]"));
			if (filtersearch.isDisplayed()) {
				report.updateTestLog("Create Filter",
						"Created Filter is dispalyed Successfully ",
						Status.DONE);

			} else {
				report.updateTestLog("Create Filter",
						"Created Filter is dispalyed Successfully ",
						Status.FAIL);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyDeleteImg() {
		try {
			UIHelper.click(driver, createnewfilterxpath);
			UIHelper.waitFor(driver);
			String Query = dataTable.getData("Search", "Query");
			String actions = dataTable.getData("Search", "Actions");
			UIHelper.sendKeysToAnElementByXpath(driver, filterID, Query);
			UIHelper.sendKeysToAnElementByXpath(driver, filtername, actions);
			UIHelper.waitFor(driver);
			driver.findElement(By.xpath(cnfsave)).click();
			String filname = driver.findElement(By.xpath(filtername))
					.getAttribute("value");
			System.out.println(filname);
			UIHelper.waitFor(driver);
			String searchQuery = dataTable.getData("Search", "FileName");
			driver.findElement(By.xpath(searchby)).sendKeys(searchQuery,
					Keys.ENTER);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			WebElement filtersearch = driver
					.findElement(By
							.xpath("//div[@class='alfresco-layout-Twister alfresco-documentlibrary-AlfDocumentFilters separated']//h3[contains(text(),'"
									+ filname + "')]"));
			if (filtersearch.isDisplayed()) {
				report.updateTestLog("Created Filter",
						"Created Filter is dispalyed Successfully ",
						Status.DONE);

			} else {
				report.updateTestLog("Create Filter",
						"Created Filter is dispalyed Successfully ",
						Status.FAIL);
			}

			searchManager();
			UIHelper.waitFor(driver);
			ArrayList<String> bulkIDList = new ArrayList<String>();
			UIHelper.findandAddElementsToaListforBulk(driver, "filternamelist",
					bulkIDList);
			System.out.println(bulkIDList);
			if (bulkIDList.contains(filname)) {
				report.updateTestLog(
						"Verify  the  CreateNewFilter ",
						"Verify the NewFilter is Created "
								+ "<br /><b> filname : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										filternamelist).getText(), Status.PASS);

			} else {
				report.updateTestLog(
						"Verify  the  CreateNewFilter ",
						"Verify the NewFilter is Created "
								+ "<br /><b> filname : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										filternamelist).getText(), Status.FAIL);

			}

			String filtername = dataTable.getData("Search", "Query");
			String finalDelIconXpath = tempDelIconXpath.replace("Filterbytest",
					filtername);

			if (UIHelper.findAnElementbyXpath(driver, finalDelIconXpath)
					.isDisplayed()) {
				report.updateTestLog(
						"Verify  the  Delete Option ",
						"Verify the Delete Option is Displayed "
								+ "<br /><b> filtername : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										finalDelIconXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify  the  Delete Option ",
						"Verify the Delete Option is Displayed "
								+ "<br /><b> filtername : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										finalDelIconXpath).getText(),
						Status.FAIL);

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyDelete() {
		try {
			UIHelper.click(driver, createnewfilterxpath);
			UIHelper.waitFor(driver);
			String Query = dataTable.getData("Search", "Query");
			String actions = dataTable.getData("Search", "Actions");
			UIHelper.sendKeysToAnElementByXpath(driver, filterID, Query);
			UIHelper.sendKeysToAnElementByXpath(driver, filtername, actions);
			UIHelper.waitFor(driver);
			driver.findElement(By.xpath(cnfsave)).click();
			String filname = driver.findElement(By.xpath(filtername))
					.getAttribute("value");
			UIHelper.waitFor(driver);
			String searchQuery = dataTable.getData("Search", "FileName");
			driver.findElement(By.xpath(searchby)).sendKeys(searchQuery,
					Keys.ENTER);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			WebElement filtersearch = driver
					.findElement(By
							.xpath("//div[@class='alfresco-layout-Twister alfresco-documentlibrary-AlfDocumentFilters separated']//h3[contains(text(),'"
									+ filname + "')]"));
			if (filtersearch.isDisplayed()) {
				report.updateTestLog("Create Filter",
						"Created Filter is dispalyed Successfully ",
						Status.DONE);

			} else {
				report.updateTestLog("Create Filter",
						"Created Filter is dispalyed Successfully ",
						Status.FAIL);
			}

			searchManager();
			UIHelper.waitFor(driver);
			ArrayList<String> bulkIDList = new ArrayList<String>();
			UIHelper.findandAddElementsToaListforBulk(driver, filternamelist,
					bulkIDList);
			if (bulkIDList.contains(filname)) {
				report.updateTestLog("Verify  the  CreateNewFilter",
						"Verify the NewFilter is Created " + "Filtername = "
								+ filname, Status.DONE);

			} else {
				report.updateTestLog("Verify  the  CreateNewFilter",
						"Verify the NewFilter is Created " + "Filtername = "
								+ filname, Status.FAIL);

			}
			String filtername = dataTable.getData("Search", "Query");
			String finalDelbuttonxpath = tempDelbuttonxpath.replace(
					"Filterbycreate", filtername);
			if (UIHelper.findAnElementbyXpath(driver, tempDelbuttonxpath)
					.isDisplayed()) {
				UIHelper.findAnElementbyXpath(driver, finalDelbuttonxpath)
						.click();
				UIHelper.waitFor(driver);
				UIHelper.findAnElementbyXpath(driver, alertconfirm).click();
				UIHelper.waitFor(driver);
				ArrayList<String> bulkIDLists = new ArrayList<String>();
				UIHelper.findandAddElementsToaListforBulk(
						driver,
						".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW_ITEMS']//td[3]",
						bulkIDList);
				UIHelper.waitFor(driver);
				if (bulkIDLists.contains(filname)) {
					report.updateTestLog("Verify Deleted CreateNewFilter",
							"Verify the NewFilter is Deleted "
									+ "Filtername = " + filname, Status.FAIL);

				} else {
					report.updateTestLog("Verify Deleted CreateNewFilter",
							"Verify the NewFilter is Deleted "
									+ "Filtername = " + filname, Status.DONE);

				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void VerifyAppletHeader() {
		try {
			UIHelper.waitFor(driver);
			ArrayList<String> bulkIDList = new ArrayList<String>();
			ArrayList<String> filterNameList = new ArrayList<String>();
			UIHelper.findandAddElementsToaListforBulk(driver, tableheader,
					bulkIDList);
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			ArrayList<String> folderNamesList = myFiles
					.getFolderNames(folderDetails);
			UIHelper.findandAddElementsToaListforBulk(driver,
					".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW_ITEMS']//td[2]",
					filterNameList);
			if (bulkIDList.contains("Filter ID")) {
				report.updateTestLog(
						"Verify  the  Table Header ",
						"Table Header is Displayed is tracked "
								+ "<br /><b> filternametresult : </b>"
								+ "<br /><b> searchresult : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										folderDetails).getText(), Status.PASS);

			}

			else {
				report.updateTestLog(
						"Verify  the  Table Header ",
						"Table Header is Displayed is tracked "
								+ "<br /><b> filternametresult : </b>"
								+ "<br /><b> searchresult : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										folderDetails).getText(), Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sorttable() {
		UIHelper.click(driver, createnewfilterxpath);
		UIHelper.waitFor(driver);
		String Query = dataTable.getData("Search", "Query");
		String actions = dataTable.getData("Search", "Actions");
		UIHelper.sendKeysToAnElementByXpath(driver, filterID, Query);
		UIHelper.sendKeysToAnElementByXpath(driver, filtername, actions);
		UIHelper.waitFor(driver);
		driver.findElement(By.xpath(cnfsave)).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		ArrayList<String> filterNameList = new ArrayList<String>();
		ArrayList<String> filterNameList1 = new ArrayList<String>();
		UIHelper.highlightElement(
				driver,
				".//*[@id='SEARCH_CONFIG_FACET_LIST']//tr//span[text()='filter_creator']//ancestor::tr//td[1]//*[contains(@src,'move-down.png')]");
		UIHelper.click(
				driver,
				".//*[@id='SEARCH_CONFIG_FACET_LIST']//tr//span[text()='filter_creator']//ancestor::tr//td[1]//*[contains(@src,'move-down.png')]");
		UIHelper.waitForVisibilityOfEleByXpath(
				driver,
				".//*[@id='SEARCH_CONFIG_FACET_LIST']//tr//span[text()='filter_creator']//ancestor::tr//td[1]//*[contains(@src,'move-up.png')]");

		UIHelper.findandAddElementsToaListforBulk(driver,
				".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW_ITEMS']//td[2]",
				filterNameList1);
		UIHelper.waitFor(driver);

		UIHelper.waitForVisibilityOfEleByXpath(
				driver,
				".//*[@id='SEARCH_CONFIG_FACET_LIST']//tr//span[text()='filter_creator']//ancestor::tr//td[1]//*[contains(@src,'move-up.png')]");

		if (filterNameList.indexOf("aaa") != filterNameList1
				.indexOf("aaafilter")) {
			// System.out.println("pass");
		} else {
			// System.out.println("fail");
		}

		UIHelper.waitFor(driver);
	}

	// Click on the select document to delete it
	public void deletedocument(String file) {
		try {
			UIHelper.waitFor(driver);
			Thread.sleep(2000);
			String finalFileXpath = tempFileXpath.replace("CRAFT", file);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					finalFileXpath));
			UIHelper.mouseOveranElement(driver,
					UIHelper.findAnElementbyXpath(driver, finalFileXpath));
			String finalactionMenuXpath = tempmoreMenuDocXpath.replace("CRAFT",
					file);
			UIHelper.findAnElementbyXpath(driver, finalactionMenuXpath).click();
			UIHelper.waitFor(driver);
			String finalmoreDeleteXpath = moreDeleteXpath
					.replace("CRAFT", file);
			UIHelper.click(driver, finalmoreDeleteXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, confirmDeleteXpath);
			UIHelper.click(driver, confirmDeleteXpath);
			Thread.sleep(4000);
			UIHelper.waitFor(driver);

			report.updateTestLog("Delete Document", "Delete Existing Document "
					+ "<br /><b> Doc Name : </b>" + file, Status.DONE);
		}

		catch (Exception e) {

		}
	}

	// Click Show more Aspect in Advanced search
	public void aspectvalueShowMore() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingApsectsXpath);

			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver,
					AspectDropDownXpath);
			String Aspect = dataTable.getData("Search", "Aspect");
			String finalAspectDP = clickAspectOpt.replace("CRAFT", Aspect);
			/*
			 * Select selectBox = new Select(dropdownEle);
			 * selectBox.selectByVisibleText(Aspect);
			 */
			dropdownEle.click();
			Thread.sleep(1000);
			// UIHelper.highlightElement(driver, finalAspectDP);
			UIHelper.click(driver, finalAspectDP);

			/*
			 * UIHelper.mouseOverandclickanElement(driver,
			 * UIHelper.findAnElementbyXpath(driver, finalAspectDP));
			 */
			// UIHelper.findAnElementbyXpath(driver, finalAspectDP).click();

			dropdownEle.click();
			Thread.sleep(2000);

			report.updateTestLog("Verify Aspect Dropdown",
					"Aspect Dropdown expands on click successfully",
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify Aspect Dropdown",
					"Aspect Dropdown expands on click successfully",
					Status.FAIL);
		}

	}

	// saved search functionality
	public void saveSearch() {
		try {

			String Name = dataTable.getData("Search", "SavedSearchName");
			String Desc = dataTable.getData("Search", "SavedSearchDesc");
			String visiblity = dataTable.getData("Search", "Visibility");
			String finalSaveSrcVisiblityXpath = saveSearchVisiblitycXpath
					.replace("CRAFT", visiblity);
			UIHelper.waitForVisibilityOfEleByXpath(driver, rightGadgetXpath);
			UIHelper.findAnElementbyXpath(driver, saveSearchNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, saveSearchNameXpath,
					Name);
			UIHelper.findAnElementbyXpath(driver, saveSearchDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, saveSearchDescXpath,
					Desc);

			UIHelper.findAnElementbyXpath(driver, finalSaveSrcVisiblityXpath)
					.click();
			UIHelper.findAnElementbyXpath(driver, saveSearchBtnXpath).click();

		/*	UIHelper.waitForPageToLoad(driver);*/
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			report.updateTestLog("Save the search query",
					"Search query saved successfully"
							+ "<br><b>Saved Search Name : </br>" + Name,
					Status.PASS);

		/*	UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Select all folders & files
	public void clickOnSelectBtnAndSelectOptionFromSearchResultPage(
			String selecMenuOption) {
		try {
			String finalXpathForSelectOption = tempXpathForSelectOption.replace("CRAFT", selecMenuOption);
			UIHelper.waitForVisibilityOfEleByXpath(driver,slectBtnXpathInSearchResultsPage);
			UIHelper.click(driver, slectBtnXpathInSearchResultsPage);
			UIHelper.waitForVisibilityOfEleByXpath(driver,finalXpathForSelectOption);
			UIHelper.click(driver, finalXpathForSelectOption);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	*/
	// Added for NALS project - Select all in search results page
	public void clickOnSelectBtnAndSelectOptionFromSearchResultPage()
	{
		try {
			
			UIHelper.waitForVisibilityOfEleByXpath(driver,slectBtnXpathInSearchResultsPage);
			UIHelper.click(driver, slectBtnXpathInSearchResultsPage);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Start Select Items operation from Search Results page
	public void clickOnSelectedItemsDropdownInSearchResultsPage() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectItemsMenuXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, selectItemsMenuXpath)) {
				UIHelper.click(driver, selectItemsMenuXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// check Select Items from Search Results page
	public ArrayList<String> getSelectedItemMenuOption() {
		ArrayList<String> selectedItemsMenuValues = new ArrayList<String>();
		try {
			List<WebElement> selectedItemsMenuList = UIHelper
					.findListOfElementsbyXpath(selectedItemsMenuOptionXpath,
							driver);

			for (WebElement selectedItemOptEle : selectedItemsMenuList) {
				selectedItemsMenuValues.add(selectedItemOptEle.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return selectedItemsMenuValues;
	}

	// Start Select Items operation from Search Results page
	public void commonMethodForPerformSelectedItemsOperation(String fileName,
			String selectedItemsOptionName) {
		try {
			String finalChkBoxXpathForSearchResultsItem = tempChkBoxXpathForSearchResultsItem
					.replace("CRAFT", fileName);
			String finalXpathForSelectItems = tempXpathForSelectItems.replace(
					"CRAFT", selectedItemsOptionName);

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalChkBoxXpathForSearchResultsItem)) {
				UIHelper.click(driver, finalChkBoxXpathForSearchResultsItem);

				UIHelper.waitForVisibilityOfEleByXpath(driver,
						selectItemsMenuXpath);

				if (UIHelper.checkForAnElementbyXpath(driver,
						selectItemsMenuXpath)) {
					UIHelper.click(driver, selectItemsMenuXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							finalXpathForSelectItems);

					UIHelper.click(driver, finalXpathForSelectItems);
					
					report.updateTestLog("Click on " + selectedItemsOptionName,
							"User able to click the 'Selected Items' option:" + selectedItemsOptionName,
							Status.DONE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Added for NALS project 
	
	public void clickOnCalculateSizeOptionInDropDown()
	{
	try {
		UIHelper.waitForVisibilityOfEleByXpath(driver, selectCalculateSizeInMenuXpath);

		if (UIHelper.checkForAnElementbyXpath(driver, selectCalculateSizeInMenuXpath)) {
			UIHelper.click(driver, selectCalculateSizeInMenuXpath);
			UIHelper.waitFor(driver);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

	
	// Start Select Items operation from Search Results page
	public void commonMethodForPerformSelectedItemsOperation(
			String selectItemMenuOptName) {
		try {
			boolean isDisplayedExpecteOption = false;
			List<WebElement> selectedItemsMenuList = UIHelper
					.findListOfElementsbyXpath(selectedItemsMenuOptionXpath,
							driver);

			for (WebElement selectedItemOptEle : selectedItemsMenuList) {
				if (selectedItemOptEle.getText().equalsIgnoreCase(
						selectItemMenuOptName)
						|| selectedItemOptEle.getText().contains(
								selectItemMenuOptName)) {
					isDisplayedExpecteOption = true;
					UIHelper.highlightElement(driver, selectedItemOptEle);

					selectedItemOptEle.click();
					UIHelper.waitForPageToLoad(driver);
					break;

				} else {
					isDisplayedExpecteOption = false;
				}
			}

			if (isDisplayedExpecteOption) {
				report.updateTestLog("Click on " + selectItemMenuOptName,
						"User able to perform the " + selectItemMenuOptName,
						Status.DONE);
			} else {
				report.updateTestLog(
						"Click on " + selectItemMenuOptName,
						"User not able to perform the " + selectItemMenuOptName,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Start Select Items operation from Search Results page
	public void openFileOrFolderFromSearchResultsPage(String fileName) {
		try {
			String finalXpathForSearchResultsItem = tempXpathForSearchResultsItem
					.replace("CRAFT", fileName);

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForSearchResultsItem)) {
				UIHelper.click(driver, finalXpathForSearchResultsItem);

				UIHelper.waitForPageToLoad(driver);

				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageEleXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog(
						"Click on File/Folder in Search Results Page",
						"User clicked the File/Folder:" + fileName, Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Perform Bulk Or Metadata Template Download operation
	public void performBulkOrMetadataTemplateDownload(String comments) {
		try {
			String place = "";
			String appURL = properties.getProperty("ApplicationUrl");
			if(appURL.contains("appp"))
		  {   
					place = "Singapore";
			 }
			else
			{
				place = dataTable.getData("Search", "Result");
			}
			
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					bulkDownloadHeaderXpathInPopup);

			if (UIHelper.checkForAnElementbyXpath(driver,
					bulkDownloadHeaderXpathInPopup)) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						commentsFieldXpathInBulkDownloadPopup, comments);
				UIHelper.highlightElement(driver, bchlocationDropdown);
				UIHelper.click(driver, bchlocationDropdown);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, bchlocationDropdownvalue);
			//	UIHelper.selectbyVisibleText(driver, bchlocationDropdown, place.trim());
				/*String finalbchlocationDpdvalue = bchlocationDropdownvalue
						.replace("CRAFT", place);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						finalbchlocationDpdvalue);
				UIHelper.highlightElement(driver, finalbchlocationDpdvalue);
				UIHelper.click(driver, finalbchlocationDpdvalue);*/
				UIHelper.highlightElement(driver, okBtnXpathInBulkDownloadPopup);
				UIHelper.click(driver, okBtnXpathInBulkDownloadPopup);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Perform All Aspects Template Download operation
	public void performAllAspectsTemplateDownload(String comments) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					allAspectsTemplatePopupHeaderXpath);

			if (UIHelper.checkForAnElementbyXpath(driver,
					allAspectsTemplatePopupHeaderXpath)) {

				UIHelper.click(driver, selectAllBtnXpath);

				UIHelper.waitForVisibilityOfEleByXpath(driver,
						pickedAspectFirstItemXpath);
				UIHelper.waitFor(driver);

				UIHelper.sendKeysToAnElementByXpath(driver,
						commentsFieldXpathInBulkDownloadPopup, comments);

				UIHelper.highlightElement(driver, bchlocationDropdown);
				String finalbchlocationDpdvalue = bchlocationDropdownvalue
						.replace("CRAFT", "Slough");
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						finalbchlocationDpdvalue);
				UIHelper.highlightElement(driver, finalbchlocationDpdvalue);
				UIHelper.click(driver, finalbchlocationDpdvalue);
				UIHelper.highlightElement(driver, okBtnXpathInBulkDownloadPopup);

				UIHelper.click(driver, downloadBtnXpath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the message from confirmation box
	public String getTheMessageText(String expectedMessageVal) {
		try {
			if (expectedMessageVal != null && !expectedMessageVal.isEmpty()) {
				String finalXpathForMessage = tempXpathForMessage.replace(
						"CRAFT", expectedMessageVal);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						finalXpathForMessage);
				UIHelper.waitForPageToLoad(driver);
				report.updateTestLog("Verify Messge Box",
						"Message box is displayed", Status.PASS);
				messageText = UIHelper.getTextFromWebElement(driver,
						finalXpathForMessage);
			} else {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				UIHelper.waitForPageToLoad(driver);
				report.updateTestLog("Verify Messge Box",
						"Message box is displayed", Status.PASS);
				messageText = UIHelper.getTextFromWebElement(driver,
						messageEleXpath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return messageText;
	}

	// Select the dropdown value from the Select Option search results page
	public void selValFrmSerchResultDrpDow(String fltrVal) {

		try {
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectLinkXPath);

			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, selectLinkXPath));
			UIHelper.click(driver, selectLinkXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, slctListValsXPath);
			lstValsSlctDrpdwn = driver
					.findElements(By.xpath(slctListValsXPath));
			System.out.println("" + lstValsSlctDrpdwn.size());

			for (WebElement availVal : lstValsSlctDrpdwn) {

				if (availVal.getText().trim().equalsIgnoreCase(fltrVal)) {
					availVal.click();
					UIHelper.waitForPageToLoad(driver);
					report.updateTestLog("Select the dropdown -> " + fltrVal
							+ " Value from Select Dropdown", "Dropdown Value "
							+ fltrVal + " selected successfully", Status.PASS);
					break;
				}

			}

		} catch (Exception e) {
			report.updateTestLog("Select the dropdown -> " + fltrVal
					+ " Value from Select Dropdown", "Dropdown Value "
					+ fltrVal + " NOT selected successfully", Status.FAIL);
		}

	}

	// Return no of files selected after choosing the Valu from Select drop down
	public int retCntFilesChkd() {

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitforPresenceOfAllElements(driver, slctFilesCheckedXPath);
	//	UIHelper.waitForVisibilityOfEleByXpath(driver, slctFilesCheckedXPath);
		List<WebElement> lstFilesChkd = driver.findElements(By
				.xpath(slctFilesCheckedXPath));
		return lstFilesChkd.size();

	}

	// Return no of files selected after choosing the Valu from Select drop down
	public int retCntFilesUnChkd() {

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitforPresenceOfAllElements(driver, slctFilesUnCheckedXPath);
	//	UIHelper.waitForVisibilityOfEleByXpath(driver, slctFilesCheckedXPath);
		List<WebElement> lstFilesChkd = driver.findElements(By
				.xpath(slctFilesUnCheckedXPath));
		return lstFilesChkd.size();

	}

	// Open document from Search Results page
	public void commonMethodForOpenSearchResultsItem(String fileName) {
		try {
			String finalSearchResultItemXpath = searchResultItemXpath.replace(
					"CRAFT", fileName);

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalSearchResultItemXpath)) {
				UIHelper.click(driver, finalSearchResultItemXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						openedDocHeaderXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						openedDocumentContainerLayerXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * // saved search functionality public String saveSearchwithUniqueNme() {
	 * try { Calendar cal = Calendar.getInstance(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("HH:mm:ss"); System.out.println("" +
	 * sdf.format(cal.getTime()));
	 * 
	 * NameSrchQry = dataTable.getData("Search", "SavedSearchName"); NameSrchQry
	 * = NameSrchQry + sdf.format(cal.getTime());
	 * 
	 * NameSrchQry = NameSrchQry.replaceAll(":", "@"); new
	 * FileUtil().writeTextToFile(NameSrchQry, testOutputFilePathSveSrch);
	 * 
	 * String Desc = dataTable.getData("Search", "SavedSearchDesc"); String
	 * visiblity = dataTable.getData("Search", "Visibility"); String
	 * finalSaveSrcVisiblityXpath = saveSearchVisiblitycXpath .replace("CRAFT",
	 * visiblity); UIHelper.waitForVisibilityOfEleByXpath(driver,
	 * rightGadgetXpath); UIHelper.findAnElementbyXpath(driver,
	 * saveSearchNameXpath).clear(); UIHelper.sendKeysToAnElementByXpath(driver,
	 * saveSearchNameXpath, NameSrchQry); UIHelper.findAnElementbyXpath(driver,
	 * saveSearchDescXpath).clear(); UIHelper.sendKeysToAnElementByXpath(driver,
	 * saveSearchDescXpath, Desc); report.updateTestLog("Save the search query",
	 * "Search query saved successfully", Status.DONE);
	 * 
	 * UIHelper.findAnElementbyXpath(driver, finalSaveSrcVisiblityXpath)
	 * .click(); UIHelper.findAnElementbyXpath(driver,
	 * saveSrchBtnXPath).click();
	 * 
	 * UIHelper.waitForPageToLoad(driver); UIHelper.waitFor(driver);
	 * UIHelper.waitFor(driver); UIHelper.waitFor(driver);
	 * UIHelper.waitFor(driver); UIHelper.waitFor(driver);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return NameSrchQry;
	 * 
	 * }
	 */

	// Click On Search Manager
	public void clickOnSearchManager() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, searchmanager)) {
				WebElement searchmanagerEle = UIHelper.findAnElementbyXpath(
						driver, searchmanager);
				UIHelper.highlightElement(driver, searchmanagerEle);

				UIHelper.click(driver, searchmanager);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						createnewfilterxpath);

				report.updateTestLog("Click on Search Manager",
						"Page displays all search filters already created",
						Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create New Filter
	public void createNewFilter(String createfilterDetails) {
		try {
			if (createfilterDetails.contains(",")) {
				String splittedFiltereDetailas[] = createfilterDetails
						.split(",");

				String filterIDVal = splittedFiltereDetailas[0].replace(
						"FilterID:", "");

				String filterNameVal = splittedFiltereDetailas[1].replace(
						"FilterName:", "");
				
				String filterPropertyVal = splittedFiltereDetailas[2].replace(
						"FilterProperty:", "");
				UIHelper.waitForVisibilityOfEleByXpath(driver, createnewfilterxpath);
				UIHelper.click(driver, createnewfilterxpath);
				
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						createNewFilterPopupHeaderXpath);

				UIHelper.sendKeysToAnElementByXpath(driver, filterID, filterIDVal);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, filternamewait);
				UIHelper.sendKeysToAnElementByXpath(driver, filtername,
						filterNameVal);
				UIHelper.sendKeysToAnElementByXpath(driver, filterproperty,
						filterPropertyVal);

				UIHelper.waitFor(driver);

				UIHelper.click(driver, filtersave);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						loadingXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageLoadXpath);

				ArrayList<String> filterid = new ArrayList<String>();
				UIHelper.findandAddElementsToaListforBulk(
						driver,
						".//td[contains(@id,'SEARCH_CONFIG_FILTER_ID_CELL_ITEM')]//span[@class='value']",
						filterid);
				if (filterid.contains(filterIDVal)) {
					report.updateTestLog(
							"Verify the create filter is exists in Search Manager Page",
							"Create Filter is already exists in Search Manager Page"
									+ "<br /><b> Filter Name: </b>"
									+ filterNameVal, Status.DONE);

				} else {
					createFilter(filterIDVal, filterNameVal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createFilter(String filterIDVal, String filterNameVal) {
		try {
			UIHelper.click(driver, createnewfilterxpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					createNewFilterPopupHeaderXpath);

			UIHelper.sendKeysToAnElementByXpath(driver, filterID, filterIDVal);
			UIHelper.sendKeysToAnElementByXpath(driver, filtername,
					filterNameVal);

			UIHelper.waitFor(driver);

			UIHelper.click(driver, cnfsave);
			UIHelper.waitForPageToLoad(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						cnfMessageForCreateFilterXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						loadingXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageLoadXpath);
			} catch (Exception e) {

			}

			UIHelper.waitFor(driver);

			ArrayList<String> filterNameList = new ArrayList<String>();
			UIHelper.findandAddElementsToaListforBulk(driver,
					".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW_ITEMS']//td[3]",
					filterNameList);
			if (filterNameList.contains(filterNameVal)) {
				report.updateTestLog(
						"Verify the Newly created filter in Search Manager Page",
						"Newly created filter is displayed successfully in Search Manager Page"
								+ "<br /><b> Filter Name: </b>" + filterNameVal,
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify the Newly created filter in Search Manager Page",
						"Newly created filter is failed to display in Search Manager Page"
								+ "<br /><b> Filter Name: </b>" + filterNameVal,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the filter Headings
	public ArrayList<String> getFilterHeadings() {
		ArrayList<String> filterHeadingNamesList = new ArrayList<String>();

		try {
			List<WebElement> filterHeadingsEle = UIHelper
					.findListOfElementsbyXpath(createdSearchFilterHeadings,
							driver);

			for (WebElement ele : filterHeadingsEle) {
				filterHeadingNamesList.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filterHeadingNamesList;
	}

	// Filter the search results with filter
	public void filterSearchResults(String createfilterDetails) {
		try {
			if (createfilterDetails.contains(",")) {
				String splittedFiltereDetailas[] = createfilterDetails
						.split(",");

				String filterNameVal = splittedFiltereDetailas[1].replace(
						"FilterName:", "");

				String finalXpathForFirstFilterItem = tempXpathForFirstFilterItem
						.replace("CRAFT", filterNameVal);
				String finalXpathForFirstFilterItemTickMark = tempXpathForFirstFilterItemTickMark
						.replace("CRAFT", filterNameVal);

				if (UIHelper.checkForAnElementbyXpath(driver,
						finalXpathForFirstFilterItem)) {
					UIHelper.click(driver, finalXpathForFirstFilterItem);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							finalXpathForFirstFilterItemTickMark);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);

					report.updateTestLog("Apply Filter", "Filter:"
							+ filterNameVal
							+ " applied successfully to the search results"
							+ "<br /><b> Applied Filter Name: </b>"
							+ filterNameVal, Status.DONE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete Filter
	public void deleteCreatedFileter(String filterName) {
		try {
			String finalXpathForDeleteFilter = tempXpathForDeleteFilter
					.replace("CRAFT", filterName);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForDeleteFilter)) {
				commonMethodForDeleteFilter(filterName,
						finalXpathForDeleteFilter);
			} else {
				String filterID = dataTable.getData("Search", "type");
				createFilter(filterID, filterName);
				commonMethodForDeleteFilter(filterName,
						finalXpathForDeleteFilter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commonMethodForDeleteFilter(String filterName,
			String finalXpathForDeleteFilter) {
		try {
			UIHelper.click(driver, finalXpathForDeleteFilter);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					confirmYesOrNoPopupHeaderXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteYesBtnXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, deleteYesBtnXpath)) {
				UIHelper.click(driver, deleteYesBtnXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						cnfMessageForDeleteteFilterXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						loadingXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageLoadXpath);

				UIHelper.waitFor(driver);

				ArrayList<String> filterNameList = new ArrayList<String>();
				UIHelper.findandAddElementsToaListforBulk(
						driver,
						".//*[@id='SEARCH_CONFIG_FACET_LIST_VIEW_ITEMS']//td[3]",
						filterNameList);
				if (!filterNameList.contains(filterName)) {
					report.updateTestLog(
							"Verify the newly created filter deletion",
							"Newly created filter:" + filterName
									+ " deleted successfully"
									+ "<br /><b> Deleted Filter Name: </b>"
									+ filterName, Status.PASS);

				} else {
					report.updateTestLog(
							"Verify the newly created filter deletion",
							"Newly created filter:" + filterName
									+ " failed to delete", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select List values from All searches
	public void selectValFrmDetailedSearches(String srchQryVal) {

		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, moreSrchXPath);
			UIHelper.click(driver, moreSrchXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, modifiedDateXpath);
			UIHelper.highlightElement(driver, modifiedDateXpath);
			UIHelper.click(driver, modifiedDateXpath);
			UIHelper.click(driver, modifiedDateXpath);

			List<WebElement> listOfValues = driver.findElements(By
					.xpath(lstAllSrchXPath));
			System.out.println("" + listOfValues.size());
			UIHelper.waitFor(driver);
			for (WebElement values : listOfValues) {

				System.out.println("" + values.getText());
				if (values.getText().equalsIgnoreCase(srchQryVal)) {

					values.click();
					report.updateTestLog(
							"open Existing Saved search from All Searches",
							"Existing saved search opened successfully",
							Status.PASS);
					UIHelper.waitForPageToLoad(driver);
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"open Existing Saved search from All Searches",
					"Existing saved search NOT opened successfully",
					Status.FAIL);
		}
	}

	// saved search functionality
	public String saveSearchwithUniqueNme() {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println("" + sdf.format(cal.getTime()));

			NameSrchQry = dataTable.getData("Search", "SavedSearchName");
			NameSrchQry = NameSrchQry + sdf.format(cal.getTime());

			NameSrchQry = NameSrchQry.replaceAll(":", "@");
			new FileUtil().writeTextToFile(NameSrchQry,
					testOutputFilePathSveSrch);

			String Desc = dataTable.getData("Search", "SavedSearchDesc");
			String visiblity = dataTable.getData("Search", "Visibility");
			String finalSaveSrcVisiblityXpath = saveSearchVisiblitycXpath
					.replace("CRAFT", visiblity);
			UIHelper.waitForVisibilityOfEleByXpath(driver, rightGadgetXpath);
			UIHelper.findAnElementbyXpath(driver, saveSearchNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, saveSearchNameXpath,
					NameSrchQry);
			UIHelper.findAnElementbyXpath(driver, saveSearchDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, saveSearchDescXpath,
					Desc);
			report.updateTestLog("Save the search query",
					"Search query saved successfully."
							+ "<br>Saved search name :</br>" + NameSrchQry,
					Status.DONE);

			UIHelper.findAnElementbyXpath(driver, finalSaveSrcVisiblityXpath)
					.click();
			UIHelper.findAnElementbyXpath(driver, saveSrchBtnXPath).click();

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return NameSrchQry;

	}

	// Select List values from All searches
	public boolean checkSavedSearchAvail(String srchQryVal) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, moreSrchXPath);
			UIHelper.click(driver, moreSrchXPath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, modifiedDateXpath);
			UIHelper.highlightElement(driver, modifiedDateXpath);
			UIHelper.click(driver, modifiedDateXpath);
			UIHelper.click(driver, modifiedDateXpath);

			List<WebElement> listOfValues = driver.findElements(By
					.xpath(lstAllSrchXPath));
			System.out.println("" + listOfValues.size());
			UIHelper.waitFor(driver);
			for (WebElement values : listOfValues) {

				System.out.println("" + values.getText());
				if (values.getText().equalsIgnoreCase(srchQryVal)) {

					flag = true;

				}

				UIHelper.waitForPageToLoad(driver);

			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	// Select List values from All searches
	public boolean checkUserAvail(String Name) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, moreSrchXPath);
			UIHelper.click(driver, moreSrchXPath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, modifiedDateXpath);

			List<WebElement> listOfValues = driver.findElements(By
					.xpath(userListXPath));
			System.out.println("" + listOfValues.size());
			UIHelper.waitFor(driver);
			for (WebElement values : listOfValues) {
				System.out.println("" + values.getText());
				if (values.getText().equalsIgnoreCase(Name)) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	// saved search functionality
	public String overWriteSavedSearch() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, overWriteBtnXpath);
			UIHelper.click(driver, overWriteBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on Overwrite button",
					"Overwrite button clicked successfully.", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Overwrite button",
					"Failed to click Overwrite button", Status.FAIL);
		}
		return NameSrchQry;

	}

	// click on Info Icon for File
	public boolean clickInfoIconForFile(String fileNme) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			String iIconXPathFinal = iIconXPath.replace("CRAFT", fileNme);
			String iIConDilgXPathFinal = iIConDilgXPath.replace("CRAFT",
					fileNme);
			UIHelper.waitForVisibilityOfEleByXpath(driver, iIconXPathFinal);
			UIHelper.highlightElement(driver, iIconXPathFinal);
			UIHelper.click(driver, iIconXPathFinal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, iIConDilgXPathFinal);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, iIConDilgXPathFinal))) {
				report.updateTestLog("Verify i - Icon ",
						"i - Information Icon displayed successfully for the file "
								+ fileNme
								+ " with related Actions and details.",
						Status.PASS);
			} else {
				report.updateTestLog("Verify i - Icon ",
						"i - Information Icon NOT displayed successfully for the file "
								+ fileNme
								+ " with related Actions and details.",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	// Select search result Scope Name in Advanced search
	public void searchResultScope(String Xpath, String name) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectResultlocXpath);
			UIHelper.click(driver, selectResultlocXpath);
			UIHelper.waitFor(driver);
			// String searchscope = dataTable.getData("Search", "Actions");
			/*
			 * String finalselectscopeXpath = selectScopeXpath.replace("CRAFT",
			 * searchscope);
			 */
			UIHelper.highlightElement(driver, Xpath);
			UIHelper.click(driver, Xpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Select Search Result Location",
					"Search result location selected " + name, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Select filter for searched results in results page
	public String selectfilters(String filter, String value, String type) {
		try {
			String finalsearchFiltersXpath = searchFiltersXpath.replace(
					"CRAFT", type);
			String finalselectFiltersXpath = selectFiltersXpath.replace(
					"FILTER", filter).replace("CRAFT", value);
			String finalselectFilterValXpath = selectFilterValXpath.replace(
					"FILTER", filter).replace("CRAFT", value);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalsearchFiltersXpath);
			
			noofhits = UIHelper.findAnElementbyXpath(driver,
					finalselectFilterValXpath).getText();
			UIHelper.click(driver, finalselectFiltersXpath);
			UIHelper.waitFor(driver);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					finalselectFiltersXpath));
			UIHelper.highlightElement(driver, finalsearchFiltersXpath);
			UIHelper.highlightElement(driver, finalselectFiltersXpath);
			UIHelper.highlightElement(driver, finalselectFilterValXpath);
			report.updateTestLog("Select Search Filter",
					"Search filter selected" + "<br><b>Filter Name :</b>"
							+ value + "<br><b>No of Hits for Filter :</b>"
							+ noofhits, Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Search Filter",
					"Search filter selected" + "<br><b>Filter Name :</b>"
							+ value + "<br><b>No of Hits for Filter :</b>"
							+ noofhits, Status.FAIL);
		}
		return noofhits;
	}

	// saved search functionality
	public void saveSearchParam(String Name) {
		try {

			// String Name = dataTable.getData("Search", "SavedSearchName");
			String Desc = dataTable.getData("Search", "SavedSearchDesc");
			String visiblity = dataTable.getData("Search", "Visibility");
			String finalSaveSrcVisiblityXpath = saveSearchVisiblitycXpath
					.replace("CRAFT", visiblity);
			UIHelper.waitForVisibilityOfEleByXpath(driver, rightGadgetXpath);
			UIHelper.findAnElementbyXpath(driver, saveSearchNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, saveSearchNameXpath,
					Name);
			UIHelper.findAnElementbyXpath(driver, saveSearchDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, saveSearchDescXpath,
					Desc);
			report.updateTestLog("Save the search query",
					"Search query saved successfully"
							+ "<br><b>Saved Search Name : </br>" + Name,
					Status.DONE);

			UIHelper.findAnElementbyXpath(driver, finalSaveSrcVisiblityXpath)
					.click();
			UIHelper.findAnElementbyXpath(driver, saveSearchBtnXpath).click();

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify save search button is disabled
	public void savebutton(String Name, String Desc, String visiblity) {
		try {

			String finalSaveSrcVisiblityXpath = saveSearchVisiblitycXpath
					.replace("CRAFT", visiblity);
			UIHelper.waitForVisibilityOfEleByXpath(driver, rightGadgetXpath);
			UIHelper.findAnElementbyXpath(driver, saveSearchNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, saveSearchNameXpath,
					Name);
			UIHelper.findAnElementbyXpath(driver, saveSearchDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, saveSearchDescXpath,
					Desc);

			String condition = UIHelper.findAnElementbyXpath(driver,
					saveSearchBtnXpath).getAttribute("disabled");
			if (condition.equalsIgnoreCase("true")) {
				UIHelper.highlightElement(driver, saveSearchBtnXpath);
				report.updateTestLog("Verify Save search Disable",
						"Save Search button disable", Status.PASS);
			} else {
				report.updateTestLog("Verify Save search Disable",
						"Save Search button not disable", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Save search Disable",
					"Verify Save Search button failed", Status.FAIL);
		}
	}

	// Input Aspect in Advanced search parameterised
	public AlfrescoSearchPage inputAspectAdvSearchParam(String Aspect,
			String AspectProp, String Aspectvalue) {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					aspectPanelXpath);
			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver,
					AspectDropDownXpath);
			/*
			 * String Aspect = dataTable.getData("Search", "Aspect"); String
			 * AspectProp = dataTable.getData("Search", "AspectProp"); String
			 * Aspectvalue = dataTable.getData("Search", "Query");
			 */
			Select selectBox = new Select(dropdownEle);
			selectBox.selectByVisibleText(Aspect);
			Select selectpropBox = new Select(driver.findElement(By
					.xpath(AspectPropDropDownXpath)));
			selectpropBox.selectByVisibleText(AspectProp);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, AspectValueXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, AspectValueXpath,
					Aspectvalue);
			if (UIHelper.findAnElementbyXpath(driver, AspectPropDropDownXpath)
					.isEnabled()) {
				report.updateTestLog("Input Aspect Name",
						"Search using Aspect Name "
								+ "<br /><b>Aspect Name :</b>" + Aspect
								+ "<br /><b>Aspect Properties :</b>"
								+ AspectProp + "<br /><b>Aspect Value :</b>"
								+ Aspectvalue, Status.DONE);
			} else {
				report.updateTestLog("Input Aspect Name",
						"Input aspect value in Adv search Failed ", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Input Aspect Name",
					"Input aspect value in Adv search Failed ", Status.FAIL);
		}
		return new AlfrescoSearchPage(scriptHelper);
	}

	// Click Show more Aspect in Advanced search
	public void clickShowMore() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingApsectsXpath);

			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver,
					AspectDropDownXpath);
			String Aspect = dataTable.getData("Search", "Aspect1");
			String finalAspectDP = clickAspectOpt.replace("CRAFT", Aspect);
			/*
			 * Select selectBox = new Select(dropdownEle);
			 * selectBox.selectByVisibleText(Aspect);
			 */
			dropdownEle.click();
			Thread.sleep(1000);
			// UIHelper.highlightElement(driver, finalAspectDP);
			UIHelper.click(driver, finalAspectDP);

			/*
			 * UIHelper.mouseOverandclickanElement(driver,
			 * UIHelper.findAnElementbyXpath(driver, finalAspectDP));
			 */
			// UIHelper.findAnElementbyXpath(driver, finalAspectDP).click();

			dropdownEle.click();
			Thread.sleep(2000);

			report.updateTestLog("Verify Aspect Dropdown",
					"Aspect Dropdown expands on click successfully",
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify Aspect Dropdown",
					"Aspect Dropdown expands on click successfully",
					Status.FAIL);
		}

	}

	public void sortByModifiedDate() {

		UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, srtDrpXPath);
		UIHelper.findAnElementbyXpath(driver, srtDrpXPath).click();
		UIHelper.waitForVisibilityOfEleByXpath(driver, modFDrpXPath);
		UIHelper.findAnElementbyXpath(driver, modFDrpXPath).click();

		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, srchEleAjaxXPath);
		/*
		 * UIHelper.waitFor(driver); UIHelper.waitFor(driver);
		 * UIHelper.waitFor(driver);
		 */
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

	}

	// Verify fileter values
	public boolean verifyFilterValues(String filter, String value) {
		boolean flag = false;
		try {

			String finalselectFiltersXpath = filterTypeXpath.replace("CRAFT",
					filter);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalselectFiltersXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					finalselectFiltersXpath));
			UIHelper.highlightElement(driver, finalselectFiltersXpath);
			String finalShowMoreXpath = showMoreXpath.replace("CRAFT", filter);
			if (UIHelper.checkForAnElementbyXpath(driver, finalShowMoreXpath)) {
				UIHelper.click(driver, finalShowMoreXpath);
			}
			String finalFilterValListXpath = filterValListXpath.replace(
					"CRAFT", filter);

			List<WebElement> listOfValues = driver.findElements(By
					.xpath(finalFilterValListXpath));
			for (WebElement element : listOfValues) {
				if (element.getText().contains(value)) {
					flag = true;
				} else {
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Check Folder size
	public String getMessageForFileOnceClickedCalculateSize() {
		String promptMessageVal = "";
		try {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (UIHelper.checkForAnElementbyXpath(driver, messageEleXpath)) {
				promptMessageVal = UIHelper.getTextFromWebElement(driver,
						messageEleXpath);
			} else {
				promptMessageVal = "'Please select folder only' not get displayed for selected file";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return promptMessageVal;
	}

	// Get Metadata value search for collection
	public String getMetadata(String Metadata) {
		String MetadataValue = "";
		try {
			// String Resultfile = tempFileNameNames.replace("CRAFT", FileName);
			// UIHelper.click(driver, Resultfile);
			UIHelper.waitForPageToLoad(driver);
			// UIHelper.waitFor(driver);
			String FinalMetadataValueXpath = tempMetadataValueXpath.replace(
					"CRAFT", Metadata);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					FinalMetadataValueXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					FinalMetadataValueXpath));
			UIHelper.highlightElement(driver, FinalMetadataValueXpath);
			MetadataValue = UIHelper.findAnElementbyXpath(driver,
					FinalMetadataValueXpath).getText();
			System.out.println(MetadataValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return MetadataValue;
	}

	// Check Look for field value in 'Advanced Search' Page
	public boolean checkLookForFieldValue() {
		boolean isDisplayedLookForFieldValue = false;

		try {
			if (UIHelper.checkForAnElementbyXpath(driver,
					lookForFieldValueInAdvancedSearch)) {
				isDisplayedLookForFieldValue = false;
			} else {
				isDisplayedLookForFieldValue = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedLookForFieldValue;
	}

	// Select 'Look for' option
	public void selectLookForFieldOption(String optionName) {
		try {
			String finalXpathForLookForFieldVal = tempXpathForLookForFieldVal
					.replace("CRAFT", optionName);

			if (UIHelper.checkForAnElementbyXpath(driver,
					lookForFieldValueInAdvancedSearch)) {
				UIHelper.click(driver, lookForFieldValueInAdvancedSearch);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							finalXpathForLookForFieldVal);
				} catch (Exception e) {
					e.printStackTrace();
				}

				UIHelper.click(driver, finalXpathForLookForFieldVal);
				UIHelper.waitFor(driver);

				report.updateTestLog("Select 'Look for' field option",
						"'Look for' field option:" + optionName
								+ " selected successfully", Status.DONE);
			} else if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForLookForFieldVal)) {
				report.updateTestLog("Select 'Look for' field option",
						"'Look for' field option:" + optionName
								+ " selected successfully", Status.DONE);
			} else {
				report.updateTestLog("Select 'Look for' field option",
						"Failed to select 'Look for' field option:"
								+ optionName, Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Get Metadata value search
		public void getMetavalue(String metadata, String expectedval) {
			String MetadataValue = "";
			try {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, docpropTwisterCloseXpath)) {
					UIHelper.click(driver, docpropTwisterCloseXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, docpropTwisterOpenXpath);
					UIHelper.waitFor(driver);
				}
				String FinalMetadataValueXpath = tempMetadataValueXpath.replace(
						"CRAFT", metadata);
				UIHelper.waitForVisibilityOfEleByXpath(driver, FinalMetadataValueXpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
						FinalMetadataValueXpath));
				UIHelper.highlightElement(driver, FinalMetadataValueXpath);
				MetadataValue = UIHelper.findAnElementbyXpath(driver,
						FinalMetadataValueXpath).getText();
			if(MetadataValue.equalsIgnoreCase(expectedval)){
				report.updateTestLog("Verify " + metadata + " attributes in Property Section",
						metadata + "attributes are displayed in Property Section. Value : " +MetadataValue, Status.PASS);
			}else{
				report.updateTestLog("Verify " + metadata + " attributes in Property Section",
						metadata + "attributes are not displayed in Property Section. Value : " +MetadataValue, Status.FAIL);
			}
			} catch (Exception e) {
				// e.printStackTrace();
				report.updateTestLog("Verify " + metadata + " attributes in Property Section",
						metadata + "attributes are not displayed in Property Section.", Status.FAIL);
			}
		}
		
		// Get file/folder name from Search result and put in into a Map with
		// details
		public Map<String, ArrayList<String>> getSearchListValuesNew() {
			try {

			/*	UIHelper.findandAddElementsToaListforMap(driver, searchListNamesNew,
						tempNameXpathNew, searchNames);
				UIHelper.findandAddElementsToaListforMap(driver, searchListNamesNew,
						tempFiletitleXpathNew, titleName);
				UIHelper.findandAddElementsToaListforMap(driver, searchListNamesNew,
						tempFileDescXpathNew, descvalue);
				UIHelper.findandAddElementsToaListforMap(driver, searchListNamesNew,
						tempFileLastModifiedXpathNew, lastModified);
				UIHelper.findandAddElementsToaListforMap(driver, searchListNamesNew,
						tempFileModifierXpathNew, modifier);
				UIHelper.findandAddElementsToaListforMap(driver, searchListNamesNew,
						tempFileSizeXpathNew, filesize);
				UIHelper.findandAddElementsToaListforMap(driver, searchListNamesNew,
						tempFilePathXpathNew, location);
				UIHelper.findandAddElementsToaListforMap(driver, searchListNamesNew,
						tempFileSiteXpathNew, siteName);*/

				searchValues.put("Names", searchNames);
				searchValues.put("Title", titleName);
				searchValues.put("Desc", descvalue);
				searchValues.put("LastModified", lastModified);
				searchValues.put("Modifier", modifier);
				searchValues.put("Size", filesize);
				searchValues.put("Path", location);
				searchValues.put("Site", siteName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return searchValues;
		}
		
		
		// Get Metadata value search
		public String getMetadataNew(String FileName, String Metadata) {
			String MetadataValue = "";
			try {
				String Resultfile = tempFileNameNamesNew.replace("CRAFT", FileName);
				UIHelper.click(driver, Resultfile);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, docpropTwisterCloseXpath)) {
					UIHelper.click(driver, docpropTwisterCloseXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, docpropTwisterOpenXpath);
					UIHelper.waitFor(driver);
				}
				String FinalMetadataValueXpath = tempMetadataValueXpath.replace(
						"CRAFT", Metadata);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
						FinalMetadataValueXpath));
				UIHelper.highlightElement(driver, FinalMetadataValueXpath);
				MetadataValue = UIHelper.findAnElementbyXpath(driver,
						FinalMetadataValueXpath).getText();
			} catch (Exception e) {
				// e.printStackTrace();
			}
			return MetadataValue;
		}

		

	// Start Select Items operation from Search Results page
	public void commonMethodForsearchselectedoption(String selectedItemsOptionName) {
		try {
			String finalXpathForSelectItems = tempXpathForSelectItems.replace("CRAFT", selectedItemsOptionName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, selectItemsMenuXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, selectItemsMenuXpath)) {
				UIHelper.click(driver, selectItemsMenuXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSelectItems);

				UIHelper.click(driver, finalXpathForSelectItems);

				report.updateTestLog("Click on " + selectedItemsOptionName,
						"User able to click the 'Selected Items' option:" + selectedItemsOptionName, Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on " + selectedItemsOptionName,
					"User unable to click the 'Selected Items' option:" + selectedItemsOptionName, Status.FAIL);
		}
	}
	
	

	// Start Select Items operation from Search Results page
	public void commonMethodtoselectsearchcheck(String fileName)
			 {
		try {
			String finalChkBoxXpathForSearchResultsItem = tempChkBoxXpathForSearchResultsItem
					.replace("CRAFT", fileName);
			

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalChkBoxXpathForSearchResultsItem)) {
				UIHelper.click(driver, finalChkBoxXpathForSearchResultsItem);

				UIHelper.waitForVisibilityOfEleByXpath(driver,
						selectItemsMenuXpath);

			
					report.updateTestLog("Click on " ,
							"User able to click the check box of "+fileName ,
							Status.DONE);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// input File Name in Advanced search
		public AlfrescoSearchPage inputFileNameAdvSearchparam(String searchQuery) {
			
			try {
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, searchNameXpath);
				if (UIHelper.findAnElementbyXpath(driver, searchNameXpath)
						.isDisplayed()) {
					UIHelper.highlightElement(driver,
							UIHelper.findAnElementbyXpath(driver, searchNameXpath));
					UIHelper.sendKeysToAnElementByXpath(driver, searchNameXpath,
							searchQuery);
					report.updateTestLog("Input File Name",
							"Search using fileName "
									+ " <br /><b>FileName Query: </b>"
									+ searchQuery, Status.DONE);
				}

			} catch (Exception e) {
				report.updateTestLog("Input File Name",
						"Search using fileName failed", Status.FAIL);
			}
			return new AlfrescoSearchPage(scriptHelper);
		}
		
		// input Title in Advanced search
		public AlfrescoSearchPage inputTitleAdvSearch(String searchTitle) {
			try {
				UIHelper.waitFor(driver);
				
				if (UIHelper.findAnElementbyXpath(driver, searchTitleXpath)
						.isDisplayed()) {
					UIHelper.highlightElement(driver,
							UIHelper.findAnElementbyXpath(driver, searchTitleXpath));
					UIHelper.sendKeysToAnElementByXpath(driver, searchTitleXpath,
							searchTitle);

					report.updateTestLog("Input Title ",
							"Search using Title completed "
									+ "<br /><b>Title Query : </b>" + searchTitle,
							Status.DONE);
				}
			} catch (Exception e) {
				report.updateTestLog("Input Title ", "Search using Title failed ",
						Status.FAIL);
			}
			return new AlfrescoSearchPage(scriptHelper);
		}

		// input Desc in Advanced search
		public AlfrescoSearchPage inputDescAdvSearch(String searchDesc) {
			try {
				UIHelper.waitFor(driver);
			
				if (UIHelper.findAnElementbyXpath(driver, searchAssetXpath)
						.isDisplayed()) {
					UIHelper.highlightElement(driver,
							UIHelper.findAnElementbyXpath(driver, searchDescXpath));
					UIHelper.sendKeysToAnElementByXpath(driver, searchDescXpath,
							searchDesc);

					report.updateTestLog("Input Desc ", "Search using Desc "
							+ " <br /><b>Desc Query : </b>" + searchDesc,
							Status.DONE);
				}
			} catch (Exception e) {
				report.updateTestLog("Input Desc ", "Search using Desc failed",
						Status.FAIL);
			}
			return new AlfrescoSearchPage(scriptHelper);
		}
		
		// input keyword Name in Advanced search
		public AlfrescoSearchPage inputKeywordSearch(String searchQuery) {
		
			try {
				UIHelper.waitFor(driver);
				if (UIHelper.findAnElementbyXpath(driver, fullTextXpath)
						.isDisplayed()) {
					UIHelper.highlightElement(driver,
							UIHelper.findAnElementbyXpath(driver, fullTextXpath));
					UIHelper.sendKeysToAnElementByXpath(driver, fullTextXpath,
							searchQuery);

					report.updateTestLog("Input keyword Name",
							"Search using keyword " + "<br /><b>Query : </b>"
									+ searchQuery, Status.DONE);
				}

			} catch (Exception e) {
				report.updateTestLog("Input keyword Name",
						"Search using keyword failed", Status.FAIL);
			}
			return new AlfrescoSearchPage(scriptHelper);
		}
		
		
		public AlfrescoSearchPage inputAspectAdvSearchparam(String Aspect, String AspectProp, String Aspectvalue ) {
			try {
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						aspectPanelXpath);
				WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver,
						AspectDropDownXpath);
				/*String Aspect = dataTable.getData("Search", "Aspect");
				String AspectProp = dataTable.getData("Search", "AspectProp");
				String Aspectvalue = dataTable.getData("Search", "Query");*/
				Select selectBox = new Select(dropdownEle);
				selectBox.selectByVisibleText(Aspect);
				Select selectpropBox = new Select(driver.findElement(By
						.xpath(AspectPropDropDownXpath)));
				selectpropBox.selectByVisibleText(AspectProp);
				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, AspectValueXpath));
				UIHelper.sendKeysToAnElementByXpath(driver, AspectValueXpath,
						Aspectvalue);
				if (UIHelper.findAnElementbyXpath(driver, AspectPropDropDownXpath)
						.isEnabled()) {
					report.updateTestLog("Input Aspect Name",
							"Search using Aspect Name "
									+ "<br /><b>Aspect Name :</b>" + Aspect
									+ "<br /><b>Aspect Properties :</b>"
									+ AspectProp + "<br /><b>Aspect Value :</b>"
									+ Aspectvalue, Status.DONE);
				} else {
					report.updateTestLog("Input Aspect Name",
							"Input aspect value in Adv search Failed ", Status.FAIL);
				}

			} catch (Exception e) {
				report.updateTestLog("Input Aspect Name",
						"Input aspect value in Adv search Failed ", Status.FAIL);
			}
			return new AlfrescoSearchPage(scriptHelper);
		}
		
		// Select all folders & files
		public void clickOnSelectitemsfromSearchResultPage() {
			try {
				
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						slectBtnXpathInSearchResultsPage);
				UIHelper.click(driver, slectBtnXpathInSearchResultsPage);
				//selecteditemsoptionsXpath
				/*UIHelper.click(driver, finalXpathForSelectOption);
				UIHelper.waitFor(driver);*/
				
				//*[@id='FCTSRCH_SEARCH_RESULT_ACTIONS_DROPDOWN']//tr
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public ArrayList<String> getSelectedItemsoptions() {
			ArrayList<String> selectedItemsMenuValues = new ArrayList<String>();
			try {
				List<WebElement> selectedItemsMenuList = UIHelper
						.findListOfElementsbyXpath(selecteditemsoptionsXpath,
								driver);

				for (WebElement selectedItemOptEle : selectedItemsMenuList) {
					selectedItemsMenuValues.add(selectedItemOptEle.getAttribute("title"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return selectedItemsMenuValues;
		}
		
		public ArrayList<String> getactionsoptions(String filename) {
			
			String Resultfile = tempactionMouseXpath.replace(
					"CRAFT", filename);	
			//System.out.println(Resultfile);
			UIHelper.waitForVisibilityOfEleByXpath(driver, Resultfile);
			//UIHelper.mouseOveranElement(driver, UIHelper
			//		.findAnElementbyXpath(driver, Resultfile));
			String finalactionMenuXpath = tempactionMenuXpath
					.replace("CRAFT", filename);
			UIHelper.findAnElementbyXpath(driver,
					finalactionMenuXpath).click();
			UIHelper.waitFor(driver);
			
			ArrayList<String> selectedItemsMenuValues = new ArrayList<String>();
			try {
				List<WebElement> selectedItemsMenuList = UIHelper
						.findListOfElementsbyXpath(actionsoptionsxpath,
								driver);

				for (WebElement selectedItemOptEle : selectedItemsMenuList) {
					selectedItemsMenuValues.add(selectedItemOptEle.getAttribute("title"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return selectedItemsMenuValues;
		}
		
		//Added for NALS Alfresco for Custom Search automation
		

		// Click search button in Advanced search
		public AlfrescoSearchPage clickCustomSearch() {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, searchBtnXpath);
				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, searchBtnXpath));
				UIHelper.click(driver, searchBtnXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
			//	UIHelper.waitForVisibilityOfEleByXpath(driver, searchingXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchingXpath);
				UIHelper.waitforPresenceOfAllElements(driver, searchCountXpath);
				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
						searchCountXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				

				if (UIHelper.checkForAnElementbyXpath(driver, searchCountXpath)) {
					report.updateTestLog(
							"Perform Custom Search",
							"Custom Search Performed"
									+ "<br /><b>No of search result found :</b> "
									+ UIHelper.findAnElementbyXpath(driver,
											searchCountXpath).getText(),
							Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Perform Custom Search",
						"Custom Search results not found", Status.FAIL);
			}
			return new AlfrescoSearchPage(scriptHelper);
		}
		
		//customSearchWithCourse
		public AlfrescoSearchPage customSearchWithCourse(String siteName,String courseName) {
			try {
				//Click the browse button to choose the site
				driver.findElement(By.id("template_x002e_customsearch_x002e_customsearchprofile_x0023_default-selectDestination-button-button")).click();
				UIHelper.waitFor(driver);
				
				//Choose the site from the picker window
				UIHelper.waitFor(driver);
				List<WebElement> siteResultsListEle = driver.findElements(By.xpath(".//div[@class='site-picker']//h4"));
				for (WebElement ele : siteResultsListEle) {
					if (ele.getText().contains(siteName)) {
						UIHelper.highlightElement(driver, ele);
						UIHelper.scrollToAnElement(ele);
						ele.click();
						break;
					}
				}
				UIHelper.waitFor(driver);
				//Click OK button
				driver.findElement(By.id("template_x002e_customsearch_x002e_customsearchprofile_x0023_default-destinationDialog-ok-button")).click();				
				UIHelper.waitFor(driver);
				//Choose the objectType
				Select objType = new Select(driver.findElement(By.id("types-dropdown-0")));
				objType.selectByVisibleText("Course");
				UIHelper.waitFor(driver);
				//Choose the objectType1
				Select objType1 = new Select(driver.findElement(By.id("types-properties-dropdown-0")));
				objType1.selectByVisibleText("Version Country");
				UIHelper.waitFor(driver);
				//Choose the objectType2
				Select objType2 = new Select(driver.findElement(By.id("select-conditions-0")));
				objType2.selectByVisibleText("CONTAINS");
				UIHelper.waitFor(driver);
				//Choose the objectType3
				UIHelper.findAnElementbyXpath(driver, "//*[@id=\"type-search-text-0\"]").sendKeys("USA");
				UIHelper.waitFor(driver);
				//Click the Search button
				driver.findElement(By.id("template_x002e_customsearch_x002e_customsearchprofile_x0023_default-button-submit-button")).click();
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				//Verify the Results
				boolean flag = false;
				List<WebElement> searchResultsListEle = driver.findElements(By
						.xpath(".//*[@class='contentlist yui-dt']//tbody[@class='yui-dt-data']/tr//a"));
				for (WebElement ele : searchResultsListEle) {					
					if (ele.getText().contains(courseName)) {
						UIHelper.waitFor(driver);						
						UIHelper.highlightElement(driver, ele);
						UIHelper.waitFor(driver);
						flag = true;
						report.updateTestLog("Custom Search Page","Results verified successfully",Status.DONE);
						break;
					}
				}
				if(!flag) {
					report.updateTestLog("Custom Search Page", "Results verification failed",Status.FAIL);
				}
				
			} catch (Exception e) {
				report.updateTestLog("Perform Custom Search",
						"Search using Course.No search result found",
						Status.FAIL);
			}
			return new AlfrescoSearchPage(scriptHelper);
		}
		
		
		//customSearchWithContentObject
		public AlfrescoSearchPage customSearchWithObjectType(String siteName,String courseName,String objectType0,String objectType1,String objectType2) {
			try {
				//Click the browse button to choose the site
				driver.findElement(By.id("template_x002e_customsearch_x002e_customsearchprofile_x0023_default-selectDestination-button-button")).click();
				UIHelper.waitFor(driver);
				
				//Choose the site from the picker window
				UIHelper.waitFor(driver);
				List<WebElement> siteResultsListEle = driver.findElements(By.xpath(".//div[@class='site-picker']//h4"));
				for (WebElement ele : siteResultsListEle) {
					if (ele.getText().contains(siteName)) {
						UIHelper.highlightElement(driver, ele);
						UIHelper.scrollToAnElement(ele);
						ele.click();
						break;
					}
				}
				UIHelper.waitFor(driver);
				
				//Choose the path from the picker window
				UIHelper.waitFor(driver);
				List<WebElement> pathResultsListEle = driver.findElements(By.xpath(".//*[contains(@id,'ygtvtablee')]//span"));
				for (WebElement ele : pathResultsListEle) {
					if (ele.getText().contains(objectType0)) {
						UIHelper.highlightElement(driver, ele);
						UIHelper.scrollToAnElement(ele);
						ele.click();
						break;
					}
				}
				UIHelper.waitFor(driver);
				
				//Click OK button
				driver.findElement(By.id("template_x002e_customsearch_x002e_customsearchprofile_x0023_default-destinationDialog-ok-button")).click();				
				UIHelper.waitFor(driver);
				
				//Choose the objectType
				Select objType = new Select(driver.findElement(By.id("types-dropdown-0")));
				objType.selectByVisibleText(objectType1);
				UIHelper.waitFor(driver);
				//Choose the objectType1
				Select objType1 = new Select(driver.findElement(By.id("types-properties-dropdown-0")));
				objType1.selectByVisibleText("Version Country");
				UIHelper.waitFor(driver);
				//Choose the objectType2
				Select objType2 = new Select(driver.findElement(By.id("select-conditions-0")));
				objType2.selectByVisibleText("CONTAINS");
				UIHelper.waitFor(driver);
				//Choose the objectType3
				UIHelper.findAnElementbyXpath(driver, "//*[@id=\"type-search-text-0\"]").sendKeys("USA");
				UIHelper.waitFor(driver);
				//Click the Search button
				driver.findElement(By.id("template_x002e_customsearch_x002e_customsearchprofile_x0023_default-button-submit-button")).click();
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				//Verify the Results
				boolean flag = false;
				List<WebElement> searchResultsListEle = driver.findElements(By
						.xpath(".//*[@class='contentlist yui-dt']//tbody[@class='yui-dt-data']/tr//a"));
				for (WebElement ele : searchResultsListEle) {					
					if (ele.getText().contains(objectType1)) {
						UIHelper.waitFor(driver);						
						UIHelper.highlightElement(driver, ele);
						UIHelper.waitFor(driver);
						flag = true;
						report.updateTestLog("Custom Search Page","Results verified successfully for "+objectType0,Status.DONE);
						break;
					}
				}
				if(!flag) {
					report.updateTestLog("Custom Search Page", "Results verification failed for "+objectType0,Status.FAIL);
				}
				
			} catch (Exception e) {
				report.updateTestLog("Perform Custom Search",
						"Search using "+ objectType0 +". No search result found",
						Status.FAIL);
			}
			return new AlfrescoSearchPage(scriptHelper);
		}
		
		
		
		
		
		
		
		
		
		
}
