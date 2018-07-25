package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Status;
import com.pearson.framework.Settings;

import java.io.File;

/**
 * Functional Components class
 * 
 * @author Cognizant
 */
public class AlfrescoSitesDashboardPage extends ReusableLibrary {
	private String pageTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String siteMenuXpath = ".//*[@id='HEADER_SITES_MENU']";
	private String creatSiteXpath = ".//*[@id='HEADER_SITES_MENU_CREATE_SITE_text']";
	private String addDashletBtnXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//button[contains(.,'Add Dashlets')]";
	private String xpathForprogramComponentDashletFieldsList = ".//*[@id='prgmComp-site-details-tablediv']/table/tbody/tr/td[1] | .//*[@id='prgmComp-site-details-tablediv']/table/tbody/tr/td[3]";
	private String isbn10FieldLabelXpath = ".//*[@id='prgmComp-site-details-tablediv']/table/tbody/tr/td[contains(@class,'ISBN13')]";
	private String commonXpathForProgramComponentFieldValXpath = ".//*[@id='prgmComp-site-details-tablediv']/table/tbody/tr/td[contains(.,'CRAFT')]/following-sibling::td[1]/input";
	private String isbn13FieldValXpath = ".//*[@id='prgmComp-site-details-tablediv']/table/tbody/tr/td[contains(.,'ISBN13')]/following-sibling::td[1]/input[1]";
	private String isbn10FieldValXpath = ".//*[@id='prgmComp-site-details-tablediv']/table/tbody/tr/td[contains(.,'ISBN13')]/following-sibling::td[1]/input[2]";
	private String xpathForProgramSiteDashletFields = ".//*[@id='prgm-site-details-tablediv']/table/tbody";
	private String updateDetailsBtnXpathForProgramComponentSite = ".//*[@id='update-programComp-details-button']";
	private String tempXpathForCopyRightField = ".//*[@id='prgmComp-site-details-tablediv']/table/tbody/tr/td[contains(.,'CRAFT')]//following-sibling::td[1]/input";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String programComponentsiteDetailsXpath = ".//*[contains(@id,'default-programComp-details-list')]";
	private String programsiteDetailsXpath = ".//*[contains(@id,'default-program-details-list')]";

	private String siteConfigBtnXpath = ".//*[@id='HEADER_SITE_CONFIGURATION_DROPDOWN']/img";
	private String customizedDashboardXpath = ".//*[@id='HEADER_CUSTOMIZE_SITE_DASHBOARD_text']/a";
	private String changeLayoutXpath = ".//*[contains(@id,'default-change-button-button')]";
	private String selectNewLayoutMessageXpath = ".//*[contains(@id,'default-layouts-div')]/h2[contains(.,'Select New Layout')]";
	private String tempSelectBtnXpathForNewLayout = ".//*[contains(@id,'default-layouts-div')]//ul/li//b[contains(.,'CRAFT')]//ancestor::li//button[contains(.,'Select')]";
	private String tempXpathForSelectBtnInLayoutPage = ".//*[contains(@id,'default-layouts-div')]//ul/li/*[contains(.,'CRAFT') and contains(.,'LAYOUT_VALUE')]//ancestor::li//button[contains(.,'Select')]";
	private String addDashletButtonXpath = ".//*[contains(@id,'default-addDashlets-button-button')]";
	private String programSiteDashletText;
	private String addedDashletHomePgXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'title') and contains(text(),'CRAFT')]";
	private String toMoVeDashColItemsListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[contains(@id,'column-ul-CRAFT')][@class='usedList']/li/a/ancestor::li[1]";
	private String tomoveDashColXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[contains(@id,'column-ul-CRAFT')][@class='usedList']/li[SIZE]";
	private String tomoveAddDashColXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[contains(@id,'column-ul-CRAFT')][@class='usedList']";
	private String tempXpathRemoveDashlet = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//*[@class='column']//ul/li[contains(.,'CRAFT')]";
	private String tempXpathRemoveDashletWithText = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[contains(@id,'column-ul-1')]//*[text()='CRAFT']//ancestor::li";
	private String toMoveUserDashColXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[contains(@id,'column-ul-CRAFT')][@class='usedList']";
	private String addDashValXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[@class='availableList']//*[text()='CRAFT']//ancestor::li";
	private String addDashValXpathUsingContains = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[@class='availableList']/li[contains(.,'CRAFT')]";
	private String okBtnDashXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//button[contains(.,'OK')]";
	private String homeTabLinkXpath = ".//*[@id='HEADER_HOME_text']/a";
	private String customizeDashletXpath = ".//*[@class='customise-dashlets']";
	private String programSiteFieldsListXpath = ".//*[@id='prgm-site-details-tablediv']/table/tbody/tr/td//span/button | .//*[@id='prgm-site-details-tablediv']/table/tbody/tr/td[1] | .//*[@id='prgm-site-details-tablediv']/table/tbody/tr/td[3]";
	private String siteComponentNavigatorXpath = ".//*[@id='navigator-body']/table[1]/tbody/tr/td[2]/a";
	private String tempXpathForProgramSiteProp = ".//*[@id='prgm-site-details-tablediv']/table/tbody/tr/td[contains(.,'CRAFT')]//following-sibling::td[1]/input";
	private String tempXpathForReadOnlyFieldInProgramSite = ".//*[@id='prgm-site-details-tablediv']/table/tbody/tr/td[contains(.,'Program Name')]//following-sibling::td[1]/input[contains(@readonly,'')]";
	private String tempSelectDropValXpathForProgram = ".//*[@id='select-CRAFT-button']";
	private String tempXpathForDivisionDropdownItems = ".//*[@id='prgm-site-details-tablediv']/table//tbody//tr/td//*[contains(@class,'yui-menu-button-menu visible')]//ul/li/a[contains(.,'CRAFT')]";
	private String updateProgramSitePropValBtnXpath = ".//*[@id='update-program-details-button']";
	private String contentsDropdownBtnXpath = ".//*[@id='select-CRAFT-button']";
	private String contentsDropdownItemsXpath = ".//*[@id='prgmComp-site-details-tablediv']/table/tbody/tr/td[contains(.,'CRAFT')]/following-sibling::td[contains(@class,'field')]//*[@class='bd']/ul/li/a";
	private String programComponentDropDownContainerXpath = ".//*[@id='prgmComp-site-details-tablediv']/table/tbody//*[@class='bd']";

	private String componentNavigatorDashletXpath = ".//*[@id='bd']//*[contains(@class,'dashlet component-navigator')]";
	private String componentNavigatorContentsXpath = ".//*[@id='navigator-body']/table/tbody/tr/td/a";
	private String progComponentSiteChildItemsXpath = ".//*[@id='navigator-body']/table/tbody/tr/td/a[contains(.,'CRAFT')]//ancestor::table[1]//following-sibling::table[2]/tbody/tr/td[2]/a";

	private String updateDetailsXpath = ".//*[@id='update-program-details-button']";
	private String xpathForProgramSiteFieldLabels = ".//*[@id='prgm-site-details-tablediv']/table/tbody/tr/td";

	private ArrayList<String> programComponentDashletList = new ArrayList<String>();
	private ArrayList<String> programDashletList = new ArrayList<String>();
	private String pageTitleTextVal;
	private String siteLinkName;
	private boolean isDisplayedReadonlyFieldInProgSite;
	private ArrayList<String> contentDropdownList = new ArrayList<String>();
	private ArrayList<String> progComponentFieldsValuesActualList = new ArrayList<String>();
	private ArrayList<String> progComponentFieldDefaultValuesList = new ArrayList<String>();
	private String contentValues = "";
	private boolean isDisplayedProgSiteAsParentOne;

	private ArrayList<String> componentNavigatorSitesListValues = new ArrayList<String>();

	private String programDashletAllDropDownsXpath = ".//*[@id='prgm-site-details-tablediv']/table//tbody//tr/td//*[contains(@class,'yui-menu-button-menu visible')]//ul/li/a";
	private String dropdownMenuListContainerXpath = ".//*[contains(@class,'yui-menu-button-menu visible')]/*[@class='bd']";
	private String alfrescoGlobalImgXpath = ".//*[contains(@id,'global')]/div/span/a/img";

	private String customizedSiteTitleXpath = ".//*[@id='alf-hd']/h1";
	private String customizedSiteXpath = ".//*[@id='HEADER_CUSTOMIZE_SITE_text']/a";
	private String customizedSiteDiscussionsXpath = ".//*[@id='template_x002e_customise-pages_x002e_customise-site_x0023_default-page-discussions-topiclist']/img";
	private String customizedSiteCurrentSitePagesPanelXpath = ".//*[@id='template_x002e_customise-pages_x002e_customise-site_x0023_default-currentPages-ul']";
	private String customizedSiteWikiPageXpath = ".//*[@id='template_x002e_customise-pages_x002e_customise-site_x0023_default-page-wiki-page']/img";
	private String customizedSiteBlogXpath = ".//*[@id='template_x002e_customise-pages_x002e_customise-site_x0023_default-page-blog-postlist']/img";
	private String customizedSiteCalendarXpath = ".//*[@id='template_x002e_customise-pages_x002e_customise-site_x0023_default-page-calendar']/img";
	private String customizedSiteOkButtonXpath = ".//*[@id='template_x002e_customise-pages_x002e_customise-site_x0023_default-save-button-button']";
	private String customizedSiteSaveConfigAlertXpath = ".//*[@id='message']/div";
	private String customizedSiteLinksXpath = ".//*[@id='template_x002e_customise-pages_x002e_customise-site_x0023_default-page-links']/img";

	private String leaveSiteMenuXpath = ".//*[@id='HEADER_LEAVE_SITE_text']";
	private String leaveSiteOkButtonXpath = ".//*[@id='ALF_SITE_SERVICE_DIALOG']/div[2]/div[2]/span[1]/span";
	private String tempDashletTitle = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='dashlet site-details']/*[contains(@class,'title') and contains(.,'CRAFT')]";
	private String appLogoXpath = ".//*[@id='HEADER_LOGO']//img";
	private String allMembersXpath = ".//*[contains(@id,'yui-gen')]//span/a[contains(.,'All Members')]";

	private String movetoRemvDashXPath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='customise-dashlets']//ul[@class='availableList']";
	private String customizeDashboardHeaderXpath = ".//*[@id='alf-hd']/h1";

	private ArrayList<String> actualDropDownValuesList = new ArrayList<String>();
	private boolean isDisplayedProgramDaslet;
	private boolean isDisplayedAppLogo;
	private boolean isDisplayedBlueTint;

	private String dashletListXPath = ".//*[@class='availableList']/li/span";
	private String dashletListFullXPath = ".//*[@class='availableList']";
	private ArrayList<String> availableDashletList = new ArrayList<String>();
	private String dashletValues = ".//*[@class='used']//div//div//ul//span";
	private String dragDropValue = ".//*[@class='used']//div//div//ul//span[contains(text(),'CRAFT')]//ancestor::li";

	private String siteFileTypeBreakDownTitleXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(text(),'Site File Type Breakdown')]";
	private String siteContributorBreakdownTitleXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(text(),'Site Contributor Breakdown')]";
	private String siteDashboardXpath = ".//*[@id='HEADER_SITE_DASHBOARD_text']/a";
	private String epmDashletFoldersXpath = "//*[contains(@id,'folderListTable-epm_wrapper')]//table//tbody/tr//a";
	private String epmDashletFileOrFolderRadioBtnXpath = "//*[contains(@id,'folderListTable-epm_wrapper')]//table//tbody/tr//a[contains(text(),'CRAFT')]//preceding-sibling::input";
	private String epmDashletFileXpath = "//*[contains(@id,'folderListTable-epm_wrapper')]//table//tbody/tr//span";
	private String epmDashletXpath = "//*[@id='epmDashlet']";
	public String epmSearchFindBtnXpath = "//button[@id='btnFind-epm']";
	public String epmSearchInputBoxXpath = "//*[@id='stdSearchForm-epm']//input[@id='txtListTitle-epm']";
	private String epmSearchRadioBtnXpath = "//*[@id='stdSearchForm-epm']//span[contains(text(),'CRAFT')]//preceding-sibling::input[@name='btnAnywhere-epm']";
	private String epmSearchChooseThisRecordBtnXpath = "//button[contains(text(),'Choose This Record')]";
	private String epmPromptBoxHeadlineXpath = "//*[@id='prompt_h']";
	private String epmPromptProceedBtnxpath = "//button[contains(text(),'Proceed')]";
	private String epmPromptCancelBtnxpath = "//button[contains(text(),'Cancel')]";
	private String epmSearchNextBtnXpath = "//button[@id='btnNext-epm']";
	private String epmSearchPreviousBtnXpath = "//button[@id='btnPrev-epm']";
	private String epmPromptOKXpath = "//button[contains(text(),'Ok')]";

	private String epmSearchTitleXpath = "//*[@id='epmDashlet']//*[@id='lblEpmSearch-epm']";
	private String epmFindTabXpath = "//a[@name='tabFind-epm']";
	private String epmSearchResultsTabXpath = "//a[contains(text(),'Search Results')]";
	private String epmSearchExitBtnXpath = "//button[contains(text(),'Exit') and contains(@id,'btnExit-epm')]";
	private String epmSearchRecordXpath = "//*[@id='biblioTable-epm']";
	public String epmSearchEmptyBtnXpath = "//button[contains(text(),'Empty')]";
	boolean isDisplayed;
	private String epmSearchRecordResultContains = "//*[@id='biblioTable-epm']//*[contains(text(),'Alfresco')]//parent::div//following-sibling::div//input[contains(@title,'CRAFT')]";
	// Database Searc - EPM Dashlet
	private String allBiblioFieldTitlesXpath = ".//*[@id='biblioTable-epm']/*[@class='row']//span";
	private String biblioFieldTitlesXpath = ".//*[@id='biblioTable-epm']/*[@class='row']//span[not(contains(.,'Product ID')) and not(contains(.,'Edition')) and not(contains(.,'Originator')) and not(contains(.,'Country of Publication'))]";

	private String allBiblioFieldValuesXpath = ".//*[@id='biblioTable-epm']/*[@class='row']//input[1]";
	private String biblioFieldValuesXpath = ".//*[@id='biblioTable-epm']/*[@class='row']//input[1][not(contains(@id,'ProductId')) and not(contains(@id,'Edition')) and not(contains(@id,'Originator')) and not(contains(@id,'CountryPublication'))]";
	private String folderFullTitleValXpath = ".//*[@id='folderListTable-epm']/tbody/tr/td[2]";
	private String folderListIsbnValXpath = ".//*[@id='folderListTable-epm']/tbody/tr/td[3]";

	private String folderISBNValStoreFilePath = "src/test/resources/AppTestData/TestOutput/FolderISBNData.txt";
	private String biblioDataFilePath = "src/test/resources/AppTestData/TestOutput/BiblioData.txt";

	private String okButtonPopUpXpath = ".//button[text()='Ok']";
	private String popUpPanelXapth = ".//div[@id='prompt']/div[2]";
	private String listTitleInputXpath = ".//input[@id='txtListTitle-epm']";
	public String fromDateXpath = ".//input[@id='fromDate-epm']";
	public String toDateXpath = ".//input[@id='toDate-epm']";
	private String findTabXpath = ".//a[text()='Find']";
	private String epmSearchResultPanel = ".//div[@id='resultOption-epm']/div[2]/div";
	public String fieldXpath = ".//*[@id='stdSearchForm-epm']//*[contains(@value,'CRAFT')]";

	private String disabledSearcResultsXpath = ".//*[contains(@class,'ui-tabs-nav')]//li/a[contains(.,'Search Results')]//ancestor::li[contains(@class,'disabled')]";
	private String searchResultsTabXpath = ".//*[contains(@class,'ui-tabs-nav')]//li/a[contains(.,'Search Results')]";
	private String findTabSecXpath = ".//*[contains(@class,'ui-tabs-nav')]//li/a[contains(.,'Find')]";
	private String preButtonXpath = ".//*[@id='btnPrev-epm']";
	private String nextButtonXpath = ".//*[@id='btnNext-epm']";
	private String chooseRecordButtonXpath = ".//*[@id='btnChooseRecord-epm']";
	private String exitButtonXpath = ".//*[@id='btnClose-epm']";
	private String epmSearchFieldXpath = ".//*[@id='biblioTable-epm']/div/div/span[text()='CRAFT']";

	 //private String siteRelationshipDashletHeaderXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(text(),'Site-Relationships')]";
	private String siteRelationshipDashletHeaderXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(@class,'dashlet site Relationships')]//div[@class='title']";
	
	//private String siteRelationshipDashletAddRelationshipBtn=".//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(text(),'Site-Relationships')]//following-sibling::button[@id='addrelationshipssites']";
	private String siteRelationshipDashletAddRelationshipBtn="//*[contains(@class,'dashlet site Relationships')]//button[@id='addrelationshipssites']";
	
	private String xpathDashlet = ".//li[contains(@class,'usedDashlet')]//span[contains(text(),'Site Relationships')]";
	private String xpathTrashCan = ".//span[contains(@id,'_default-trashcan-img')]";

	private String tempXpathForSiteDashletsByColumn = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'columnCRAFT dcolumn')]//*[@class='title']";
	private String cancelBtnInCustomizeDashboardPage = ".//*[@class='customise-dashlets']//*[contains(@id,'default-cancel-button-button')]";

	private String tempXpathForCurrentLayout = ".//*[contains(@id,'default-currentLayoutDescription-span')]/b[contains(.,'CRAFT')]";

	private String tempSortByXpath = "//th[contains(text(),'CRAFT')]";
	private String epmFolderNames = "//*[contains(@id,'folderListTable-epm_wrapper')]//table//tbody/tr//a";
	private ArrayList<String> sortList = new ArrayList<String>();

	private String siteActivitiesDashletHeaderXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(text(),'Site Activities')]";
	private String sitecontentDashletHeaderXpath = "//*[@class='sticky-wrapper']//*[@id='bd']//div[contains(text(),'Site Content')]";
	private String myActivityDropDownInSiteDashboardXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[contains(@id,'_dashboard_x0023_default-user-button')]";
	private String SitecontentDropdown = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet docsummary')]//*[contains(@id,'_dashboard_x0023_default-filters-button')]";
	private String myActivityDropDownValueInSiteDashboardXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[@class='bd']//ul//li//a[contains(.,'CRAFT')]";
	private String SitecontentDropdownValues = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet docsummary')]//*[@class='bd']//ul//li//a[contains(.,'CRAFT')]";
	private String myActivitySiteDashboardPanelXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[contains(@id,'_default-activityList')]";
	private String myActivitySiteDashboardListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[contains(@id,'_default-activityList')]/div/div/span[1]";
	private String deleteButtonXpath = ".//button[text()='Delete']";
	private String dateRangeDropdownXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[contains(@id,'default-range-button')]";
	private String dateValueXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[@class='bd']//ul//li//a[contains(.,'CRAFT')]";
	private String activitiesDropdownXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[contains(@id,'_dashboard_x0023_default-activities-button')]";
	private String activitiesValueXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@class,'dashlet activities')]//*[@class='bd']//ul//li//a[contains(.,'CRAFT')]";

	private String asyncDashletTableHeadingsXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='async-status-dashlet']//*[@id='accordion']//table/thead/tr/td/b";
	private String tempXpathForMessageStatus = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='async-status-dashlet']//*[@id='accordion']//table/tbody/tr/td[2][contains(.,'CRAFT')]//following-sibling::td[1]";
	private String folderLableInfoXpath = ".//*[@id='folderListTable_info']";

	private String moreInfoXpath = ".//*[@id='async-status-dashlet']//a[contains(.,'More Info')]";
	private String detailedAsyncSatusForEPSHeaderXpath = ".//*[@id='async-status-dashlet']//*[contains(.,'Detailed Async Status for EPS and MediaTransform')]";

	private String paginatorXapth = ".//*[@id='folderListTable_paginate']";
	private String paginationNextBtnXpath = ".//*[@id='folderListTable_next']";
	private String paginateBtnCurreItemXpath = ".//*[@id='folderListTable_paginate']/span/a[contains(@class,'paginate_button current')]";
	private String paginationPreviousBtnXpath = ".//*[@id='folderListTable_previous']";
	private String paginationPreviousBtnDisabledXpath = ".//*[contains(@id,'folderListTable_previous') and contains(@class,'previous disabled')]";

	private String asyncDetailsPageTableHeaderNamesXpath = ".//*[@id='folderListTable']/thead/tr/td";
	private String firstRowValXpathInAsyncStatusDashlet = ".//*[@id='folderListTable']/tbody/tr[1]";

	private String tempHeaderVisibilityXpath = ".//div[@id='HEADER_TITLE_VISIBILITY']//span[contains(text(),'CRAFT')]";

	private String firstThreeActivitiesElXpath = ".//*[contains(@id,'default-activityList')]//*[@class='activity']//*[@class='content']/span[1] | .//*[contains(@id,'default-activityList')]//*[@class='activity']//*[@class='content']/span[1] | .//*[contains(@id,'default-activityList')]//*[@class='activity']//*[@class='content']/span[1]";

	private ArrayList<String> siteActivitiesList = new ArrayList<String>();
	
	private String tempHelpIconXpath="//*[@class='title' and text()='CRAFT']//following-sibling::div//*[@class='titleBarActionIcon help']";
	private String helpTextXpath="//*[@class='yui-module yui-overlay' and contains(@style,'visibility: visible')]//div[@class='text']";
	private String deleteRelationshipXpath = "//*[@id='viewRelationshipsMarkup']//table//tr/td[4]/img";
	AlfrescoDocumentDetailsPage docDetailsPg=new AlfrescoDocumentDetailsPage(scriptHelper);
	

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoSitesDashboardPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Navigate to Sites page
	public AlfrescoSitesDashboardPage navigateToSitesTab() {
		try {
			WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
			UIHelper.mouseOveranElement(driver, siteMenuEle);
			UIHelper.highlightElement(driver, siteMenuEle);
			UIHelper.click(driver, siteMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, creatSiteXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoSitesDashboardPage(scriptHelper);
	}

	// Navigate To Site Dashboard Page

	public void navigateToSiteDashboard() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteDashboardXpath);
			UIHelper.click(driver, siteDashboardXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Navigate to Site Dashboard Page", "Navigate to Site Dashboard Page sucessfully",
					Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Navigate to Site Dashboard Page", "Navigate to Site Dashboard Page Failed",
					Status.FAIL);
		}
	}

	// Get the Page Head Title text
	public String getPageHeadTitleText() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			pageTitleTextVal = UIHelper.getTextFromWebElement(driver, pageTitleXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageTitleTextVal;
	}

	// Get the Program component site Dashlet details
	public ArrayList<String> getProgramComponentDashletDetails() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, programComponentsiteDetailsXpath);

			WebElement programComponentsiteDetailsEle = UIHelper.findAnElementbyXpath(driver,
					programComponentsiteDetailsXpath);

			UIHelper.highlightElement(driver, programComponentsiteDetailsEle);

			List<WebElement> programcomponentListEle = UIHelper
					.findListOfElementsbyXpath(xpathForprogramComponentDashletFieldsList, driver);
			for (WebElement ele : programcomponentListEle) {
				programComponentDashletList.add(ele.getText().trim());
			}

			if (UIHelper.checkForAnElementbyXpath(driver, isbn10FieldLabelXpath)) {
				programComponentDashletList.add("ISBN10");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return programComponentDashletList;
	}

	// Get the Program site Dashlet details
	public String getProgramDashletDetails() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, programsiteDetailsXpath);

			WebElement programSiteDetailsEle = UIHelper.findAnElementbyXpath(driver, programsiteDetailsXpath);

			UIHelper.scrollToAnElement(programSiteDetailsEle);
			UIHelper.highlightElement(driver, programSiteDetailsEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, xpathForProgramSiteDashletFields);
			programSiteDashletText = UIHelper.getTextFromWebElement(driver, xpathForProgramSiteDashletFields).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return programSiteDashletText;
	}

	// Get the Program site Dashlet details
	public ArrayList<String> getProgramDashletFieldsDetails() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, programsiteDetailsXpath);

			WebElement programSiteDetailsEle = UIHelper.findAnElementbyXpath(driver, programsiteDetailsXpath);

			UIHelper.scrollToAnElement(programSiteDetailsEle);
			UIHelper.highlightElement(driver, programSiteDetailsEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, xpathForProgramSiteDashletFields);

			programSiteDashletText = UIHelper.getTextFromWebElement(driver, xpathForProgramSiteDashletFields);

			List<WebElement> programSiteFieldsListEle = UIHelper.findListOfElementsbyXpath(programSiteFieldsListXpath,
					driver);
			for (WebElement ele : programSiteFieldsListEle) {
				String fieldValue = ele.getText().trim();
				if (!fieldValue.isEmpty()) {
					programDashletList.add(fieldValue);
				}
			}

			if (programSiteDashletText.contains("Division")) {
				programDashletList.add("Division");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return programDashletList;
	}

	// Get the Program site Dashlet details
	public ArrayList<String> getProgramDashletFieldDefaultValues() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, programsiteDetailsXpath);

			WebElement programSiteDetailsEle = UIHelper.findAnElementbyXpath(driver, programsiteDetailsXpath);

			UIHelper.scrollToAnElement(programSiteDetailsEle);
			UIHelper.highlightElement(driver, programSiteDetailsEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, xpathForProgramSiteDashletFields);

			programSiteDashletText = UIHelper.getTextFromWebElement(driver, xpathForProgramSiteDashletFields);

			List<WebElement> programSiteFieldsListEle = UIHelper.findListOfElementsbyXpath(programSiteFieldsListXpath,
					driver);
			for (WebElement ele : programSiteFieldsListEle) {
				String fieldValue = ele.getText().trim();
				if (!fieldValue.isEmpty()) {
					programDashletList.add(fieldValue + ":");
				}
			}

			if (programSiteDashletText.contains("Division")) {
				programDashletList.add("Division:");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return programDashletList;
	}

	// Update any field in Program Component site dashlet
	public void updateProgramComponentSiteFieldData(String programComponentSiteFieldValue) {
		try {
			if (programComponentSiteFieldValue.contains(":")) {

				String splittedFieldValues[] = programComponentSiteFieldValue.split(":");

				if (splittedFieldValues != null && splittedFieldValues.length > 1) {
					String finalXpathForCopyRightField = tempXpathForCopyRightField.replace("CRAFT",
							splittedFieldValues[0]);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCopyRightField);
					UIHelper.sendKeysToAnElementByXpath(driver, finalXpathForCopyRightField, splittedFieldValues[1]);
					UIHelper.highlightElement(driver, updateDetailsBtnXpathForProgramComponentSite);
					UIHelper.click(driver, updateDetailsBtnXpathForProgramComponentSite);
					UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);

					report.updateTestLog(
							"Enter the data for '" + splittedFieldValues[0]
									+ "' field in Program Component Site Dashlet",
							"Entered the data as '" + splittedFieldValues[1] + "' in " + splittedFieldValues[0]
									+ " field in Program Component Site Dashlet",
							Status.DONE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the Content dropdown items from Program Component List
	public ArrayList<String> getContentDropdownValuesFromProgramComponent(String dropdownName) {
		try {
			String finalContentsDropdownItemsXpath = contentsDropdownItemsXpath.replace("CRAFT", dropdownName);
			String convertedDropdownValToLower = dropdownName.toLowerCase();
			String finalContentsDropdownBtnXpath = contentsDropdownBtnXpath.replace("CRAFT",
					convertedDropdownValToLower);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalContentsDropdownBtnXpath);
			UIHelper.click(driver, finalContentsDropdownBtnXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, programComponentDropDownContainerXpath);

			List<WebElement> contentDropdownListEle = UIHelper
					.findListOfElementsbyXpath(finalContentsDropdownItemsXpath, driver);
			for (WebElement ele : contentDropdownListEle) {
				contentDropdownList.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return contentDropdownList;
	}

	// Customize Site Dashlet
	public void customizeSiteDashboard() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteConfigBtnXpath);
			UIHelper.click(driver, siteConfigBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, customizedDashboardXpath);
			UIHelper.click(driver, customizedDashboardXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addDashletButtonXpath);
			UIHelper.highlightElement(driver, addDashletButtonXpath);
			UIHelper.click(driver, addDashletButtonXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void toLeaveASite(String siteName) {

		UIHelper.waitForVisibilityOfEleByXpath(driver, siteConfigBtnXpath);
		UIHelper.highlightElement(driver, siteConfigBtnXpath);
		UIHelper.click(driver, siteConfigBtnXpath);

		UIHelper.waitForVisibilityOfEleByXpath(driver, leaveSiteMenuXpath);
		UIHelper.highlightElement(driver, leaveSiteMenuXpath);
		UIHelper.waitFor(driver);
		UIHelper.click(driver, leaveSiteMenuXpath);

		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, leaveSiteOkButtonXpath);
		UIHelper.highlightElement(driver, leaveSiteOkButtonXpath);
		UIHelper.click(driver, leaveSiteOkButtonXpath);

		report.updateTestLog("To leave a Site",
				"User leaved site Successfully" + "<br/><b> Site Name : </b>" + siteName, Status.PASS);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, customizedSiteSaveConfigAlertXpath);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
	}

	/**
	 * @author 412766
	 */
	public void navigateToCustomizeSite() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteConfigBtnXpath);
			UIHelper.highlightElement(driver, siteConfigBtnXpath);
			UIHelper.click(driver, siteConfigBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, customizedSiteXpath);
			UIHelper.highlightElement(driver, customizedSiteXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, customizedSiteXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, customizedSiteTitleXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, customizedSiteTitleXpath))) {
				UIHelper.highlightElement(driver, customizedSiteTitleXpath);
				report.updateTestLog("Navigate to Customize Site Page",
						"Navigated Successfully" + "<br/><b> Page Title : </b>"
								+ UIHelper.getTextFromWebElement(driver, customizedSiteTitleXpath),
						Status.DONE);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Navigate to Customize Site Page", "Navigate to Customize Site Page Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void addCustomizeSiteMenus() {
		try {
			UIHelper.waitFor(driver);
			String menuNames = dataTable.getData("Sites", "CustomizeSiteMenu");
			StringTokenizer token = new StringTokenizer(menuNames, ",");
			while (token.hasMoreElements()) {
				String menuName = token.nextElement().toString();

				switch (menuName) {
				case "Discussions":
					dragAndDropCustomizeSiteMenu(customizedSiteDiscussionsXpath,
							customizedSiteCurrentSitePagesPanelXpath, menuName);
					break;
				case "Wiki":
					dragAndDropCustomizeSiteMenu(customizedSiteWikiPageXpath, customizedSiteCurrentSitePagesPanelXpath,
							menuName);
					break;
				case "Blog":
					dragAndDropCustomizeSiteMenu(customizedSiteBlogXpath, customizedSiteCurrentSitePagesPanelXpath,
							menuName);
					break;
				case "Calendar":
					dragAndDropCustomizeSiteMenu(customizedSiteCalendarXpath, customizedSiteCurrentSitePagesPanelXpath,
							menuName);
					break;
				case "Links":
					dragAndDropCustomizeSiteMenu(customizedSiteLinksXpath, customizedSiteCurrentSitePagesPanelXpath,
							menuName);
					break;

				default:
					break;
				}
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, customizedSiteOkButtonXpath);
			UIHelper.highlightElement(driver, customizedSiteOkButtonXpath);
			UIHelper.click(driver, customizedSiteOkButtonXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, customizedSiteSaveConfigAlertXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("To add 'Customize Site' Menu", "To add 'Customize Site' Menu Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param draggedElementXpath
	 * @param droppedElementXpath
	 * @param menuName
	 */
	private void dragAndDropCustomizeSiteMenu(String draggedElementXpath, String droppedElementXpath, String menuName) {

		if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, draggedElementXpath))) {
			UIHelper.dragAndDrop(driver, draggedElementXpath, droppedElementXpath);
			report.updateTestLog("To add 'Customize Site' Menu",
					"Menu Added Successfully" + "<br/><b> Menu Name : </b>" + menuName, Status.DONE);
		} else {
			report.updateTestLog("To add 'Customize Site' Menu",
					"Menu not Added - Reason : It is already added / Menu not available", Status.FAIL);
		}
		UIHelper.waitFor(driver);
	}

	// Add Dashlet
	public boolean addDashletInCustomDashBoard() {
		
		
		String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
	//	String finalAddedDashletHomePgXPath = addedDashletHomePgXPath.replace("CRAFT", dashletNmetoAdd);

		try {
			String customizeSiteHeaderText = "";
			if (UIHelper.checkForAnElementbyXpath(driver, customizeDashboardHeaderXpath)) {
				customizeSiteHeaderText = UIHelper.getTextFromWebElement(driver, customizeDashboardHeaderXpath);
			}
			
			if (UIHelper.checkForAnElementbyXpath(driver, addDashletBtnXPath)) {
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, addDashletBtnXPath);
			UIHelper.highlightElement(driver, addDashletBtnXPath);
			UIHelper.click(driver, addDashletBtnXPath);
			UIHelper.waitFor(driver);
			}


			String colNoofAddDashlet = dataTable.getData("Home", "ColumnNoofAddDashlet");

			String finalColNoofAddDashlet = colNoofAddDashlet.replace("'", "");

			int colNoofAddDashletInt = Integer.valueOf(finalColNoofAddDashlet).intValue();

			String finalTomoveDashColXPathForUserDashboard = toMoveUserDashColXpath.replace("CRAFT",
					"" + colNoofAddDashletInt);

			String finalMoveTotDashColItemsXpath = toMoVeDashColItemsListXpath.replace("CRAFT",
					"" + colNoofAddDashletInt);
			String finalTomoveDashColXPath = "";
			if (UIHelper.checkForAnElementbyXpath(driver, finalMoveTotDashColItemsXpath)) {
				List<WebElement> listOfElements = UIHelper.findListOfElementsbyXpath(finalMoveTotDashColItemsXpath,
						driver);
				int countOfMoveToItems = listOfElements.size();

				finalTomoveDashColXPath = tomoveDashColXPath.replace("CRAFT", "" + colNoofAddDashletInt).replace("SIZE",
						"" + countOfMoveToItems);
			} else {
				finalTomoveDashColXPath = tomoveAddDashColXPath.replace("CRAFT", "" + colNoofAddDashletInt);
			}

			if (dashletNmetoAdd.equalsIgnoreCase("Site Contributor Breakdown")) {
				dashletNmetoAdd = dashletNmetoAdd.replace("Breakdown", "Breakdo...");
			}

			String finalAddDashValXpath = "";
			if (dashletNmetoAdd.equalsIgnoreCase("Program")) {
				finalAddDashValXpath = addDashValXpath.replace("CRAFT", dashletNmetoAdd);
			} else {
				finalAddDashValXpath = addDashValXpathUsingContains.replace("CRAFT", dashletNmetoAdd);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, customizeDashletXpath);

			WebElement customizeDashletEle = UIHelper.findAnElementbyXpath(driver, customizeDashletXpath);
			UIHelper.highlightElement(driver, customizeDashletEle);
			UIHelper.mouseOveranElement(driver, customizeDashletEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalAddDashValXpath);

			WebElement finalAddDashValEle = UIHelper.findAnElementbyXpath(driver, finalAddDashValXpath);
			UIHelper.highlightElement(driver, finalAddDashValEle);
			UIHelper.scrollToAnElement(finalAddDashValEle);
			

			WebElement finalTomoveDashColEle = UIHelper.findAnElementbyXpath(driver, finalTomoveDashColXPath);
			UIHelper.mouseOveranElement(driver, finalTomoveDashColEle);
			UIHelper.highlightElement(driver, finalTomoveDashColEle);
			UIHelper.scrollToAnElement(finalTomoveDashColEle);

			UIHelper.waitFor(driver);

			if (customizeSiteHeaderText.contains("Site")) {
				UIHelper.dragAndDrop(driver, finalAddDashValXpath, finalTomoveDashColXPath);
			} else if (customizeSiteHeaderText.contains("User")) {
				UIHelper.dragAndDrop(driver, finalAddDashValXpath, finalTomoveDashColXPathForUserDashboard);
			} else {
				UIHelper.dragAndDrop(driver, finalAddDashValXpath, finalTomoveDashColXPath);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnDashXPath);
			UIHelper.click(driver, okBtnDashXPath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);

		/*	try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalAddedDashletHomePgXPath);
			} catch (Exception e) {
			}*/

			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
				WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

				UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
				UIHelper.waitFor(driver);
			}
		//	UIHelper.highlightElement(driver, finalAddedDashletHomePgXPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath);
	}

	// Remove Dashlet
	public boolean removeDashletFromCustomDashBoard() {

		String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
		String finalAddedDashletHomePgXPath = addedDashletHomePgXPath.replace("CRAFT", dashletNmetoAdd);

		try {

			String finalToRemoveDashColXPath = tempXpathRemoveDashlet.replace("CRAFT",
					dashletNmetoAdd.replace("-", " "));

			UIHelper.waitForVisibilityOfEleByXpath(driver, customizeDashletXpath);

			WebElement customizeDashletEle = UIHelper.findAnElementbyXpath(driver, customizeDashletXpath);
			UIHelper.highlightElement(driver, customizeDashletEle);
			UIHelper.mouseOveranElement(driver, customizeDashletEle);

			WebElement finalToRemoveDashColEle = UIHelper.findAnElementbyXpath(driver, finalToRemoveDashColXPath);
			UIHelper.highlightElement(driver, finalToRemoveDashColEle);
			UIHelper.scrollToAnElement(finalToRemoveDashColEle);

			UIHelper.waitFor(driver);
			UIHelper.dragAndDrop(driver, finalToRemoveDashColXPath, movetoRemvDashXPath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnDashXPath);
			UIHelper.click(driver, okBtnDashXPath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
				WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

				UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
				UIHelper.waitFor(driver);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return UIHelper.checkForAnElementbyXpath(driver, finalAddedDashletHomePgXPath);
	}

	// Method for add Dashlet to Site Dashboard
	public boolean commonMethodForAddDashletInCustomDashBoard(String dashletNmetoAdd, String colNoofAddDashlet) {

		String finalAddedDashletHomePgXPath = addedDashletHomePgXPath.replace("CRAFT", dashletNmetoAdd);

		try {

			String customizeSiteHeaderText = "";
			if (UIHelper.checkForAnElementbyXpath(driver, customizeDashboardHeaderXpath)) {
				customizeSiteHeaderText = UIHelper.getTextFromWebElement(driver, customizeDashboardHeaderXpath);
			}

			String finalColNoofAddDashlet = colNoofAddDashlet.replace("'", "");

			int colNoofAddDashletInt = Integer.valueOf(finalColNoofAddDashlet).intValue();

			String finalTomoveDashColXPathForUserDashboard = toMoveUserDashColXpath.replace("CRAFT",
					"" + colNoofAddDashletInt);

			String finalMoveTotDashColItemsXpath = toMoVeDashColItemsListXpath.replace("CRAFT",
					"" + colNoofAddDashletInt);
			UIHelper.waitFor(driver);

			String finalTomoveDashColXPath = "";
			if (UIHelper.checkForAnElementbyXpath(driver, finalMoveTotDashColItemsXpath)) {
				List<WebElement> listOfElements = UIHelper.findListOfElementsbyXpath(finalMoveTotDashColItemsXpath,
						driver);
				int countOfMoveToItems = listOfElements.size();

				finalTomoveDashColXPath = tomoveDashColXPath.replace("CRAFT", "" + colNoofAddDashletInt).replace("SIZE",
						"" + countOfMoveToItems);
			} else {
				finalTomoveDashColXPath = tomoveAddDashColXPath.replace("CRAFT", "" + colNoofAddDashletInt);
			}

			if (dashletNmetoAdd.equalsIgnoreCase("Site Contributor Breakdown")) {
				dashletNmetoAdd = dashletNmetoAdd.replace("Breakdown", "Breakdo...");
			}

			String finalAddDashValXpath = "";
			if (dashletNmetoAdd.equalsIgnoreCase("Program")) {
				finalAddDashValXpath = addDashValXpath.replace("CRAFT", dashletNmetoAdd);
			} else {
				finalAddDashValXpath = addDashValXpathUsingContains.replace("CRAFT", dashletNmetoAdd.replace("-", " "));
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, customizeDashletXpath);

			WebElement customizeDashletEle = UIHelper.findAnElementbyXpath(driver, customizeDashletXpath);
			UIHelper.highlightElement(driver, customizeDashletEle);
			UIHelper.mouseOveranElement(driver, customizeDashletEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalAddDashValXpath);

			WebElement finalAddDashValEle = UIHelper.findAnElementbyXpath(driver, finalAddDashValXpath);
			UIHelper.highlightElement(driver, finalAddDashValEle);
			UIHelper.scrollToAnElement(finalAddDashValEle);

			WebElement finalTomoveDashColEle = UIHelper.findAnElementbyXpath(driver, finalTomoveDashColXPath);
			UIHelper.highlightElement(driver, finalTomoveDashColEle);
			UIHelper.scrollToAnElement(finalTomoveDashColEle);

			UIHelper.waitFor(driver);

			if (customizeSiteHeaderText.contains("Site")) {
				UIHelper.dragAndDrop(driver, finalAddDashValXpath, finalTomoveDashColXPath);
			} else if (customizeSiteHeaderText.contains("User")) {
				UIHelper.dragAndDrop(driver, finalAddDashValXpath, finalTomoveDashColXPathForUserDashboard);
			} else {
				UIHelper.dragAndDrop(driver, finalAddDashValXpath, finalTomoveDashColXPath);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnDashXPath);
			UIHelper.click(driver, okBtnDashXPath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalAddedDashletHomePgXPath);

			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
				WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

				UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
				UIHelper.waitFor(driver);
			}
			UIHelper.highlightElement(driver, finalAddedDashletHomePgXPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return UIHelper.checkForAnElementbyXpath(driver, finalAddedDashletHomePgXPath);
	}

	// Click Ok button in Customize Dashboard Page
	public void clickOnOkBtnInCustomizeDashboardPage() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnDashXPath);
			UIHelper.click(driver, okBtnDashXPath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
				WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

				UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Remove Dashlet
	public boolean commonMethodForRemoveDashletFromCustomDashBoard(String dashletNmetoAdd) {

		String finalAddedDashletHomePgXPath = addedDashletHomePgXPath.replace("CRAFT", dashletNmetoAdd);

		try {

			String finalToRemoveDashColXPath = tempXpathRemoveDashlet.replace("DASHLET_NAME",
					dashletNmetoAdd.replace("-", " "));

			UIHelper.waitForVisibilityOfEleByXpath(driver, customizeDashletXpath);

			WebElement customizeDashletEle = UIHelper.findAnElementbyXpath(driver, customizeDashletXpath);
			UIHelper.highlightElement(driver, customizeDashletEle);
			UIHelper.mouseOveranElement(driver, customizeDashletEle);

			WebElement finalToRemoveDashColEle = UIHelper.findAnElementbyXpath(driver, finalToRemoveDashColXPath);
			UIHelper.highlightElement(driver, finalToRemoveDashColEle);
			UIHelper.scrollToAnElement(finalToRemoveDashColEle);

			UIHelper.waitFor(driver);
			UIHelper.dragAndDrop(driver, finalToRemoveDashColXPath, movetoRemvDashXPath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnDashXPath);
			UIHelper.click(driver, okBtnDashXPath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
				WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

				UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
				UIHelper.waitFor(driver);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return UIHelper.checkForAnElementbyXpath(driver, finalAddedDashletHomePgXPath);
	}

	/**
	 * @author 412766
	 */
	public void removeCustomeSiteDashlet() {
		UIHelper.waitFor(driver);
		if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, xpathDashlet))) {
			UIHelper.dragAndDrop(driver, xpathDashlet, xpathTrashCan);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnDashXPath);
			UIHelper.click(driver, okBtnDashXPath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);
		}
	}

	// Get the Program Site link from Component Navigator Dashlet
	public String getSiteLinkFormComponentNavigatorDashlet() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteComponentNavigatorXpath);
			siteLinkName = UIHelper.getTextFromWebElement(driver, siteComponentNavigatorXpath).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return siteLinkName;

	}

	// Update Program Site properties
	public void enterDataInProgramSiteProperties(String fieldDetails) {
		try {
			String splittedFieldValues[];

			if (fieldDetails.contains(",")) {
				splittedFieldValues = fieldDetails.split(",");
				if (splittedFieldValues != null & splittedFieldValues.length > 0) {
					for (String expPropValue : splittedFieldValues) {
						commonMethodForEnterFieldsDataInProgramSite(expPropValue);
					}
				}
			} else {
				commonMethodForEnterFieldsDataInProgramSite(fieldDetails);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, updateProgramSitePropValBtnXpath);
			UIHelper.click(driver, updateProgramSitePropValBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			report.updateTestLog("Perform 'Update Properties' for Program Site",
					"User successfully updated the properties of Program site", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commonMethodForEnterFieldsDataInProgramSite(String fieldDetails) {
		try {
			if (fieldDetails.contains(":")) {
				String splittedExpPropValue[] = fieldDetails.split(":");
				if (splittedExpPropValue != null && splittedExpPropValue.length > 1) {

					if (splittedExpPropValue[0].contains("TextField")) {
						String finalCommonXpathForEditPropFields = tempXpathForProgramSiteProp.replace("CRAFT",
								splittedExpPropValue[1]);
						UIHelper.waitForVisibilityOfEleByXpath(driver, finalCommonXpathForEditPropFields);
						UIHelper.sendKeysToAnElementByXpath(driver, finalCommonXpathForEditPropFields,
								splittedExpPropValue[2]);
					} else if (splittedExpPropValue[0].contains("Dropdown")) {

						String finalDropdownFieldName = splittedExpPropValue[1].toLowerCase();
						String finalSelectDropValXpathForProgram = tempSelectDropValXpathForProgram.replace("CRAFT",
								finalDropdownFieldName);

						String finalXpathForDivisionDropdownItems = tempXpathForDivisionDropdownItems.replace("CRAFT",
								splittedExpPropValue[2]);

						UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectDropValXpathForProgram);
						UIHelper.click(driver, finalSelectDropValXpathForProgram);

						UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDivisionDropdownItems);
						UIHelper.click(driver, finalXpathForDivisionDropdownItems);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDisplayedReadOnlyField(String programSiteFieldName, String programSiteFieldValue) {
		try {
			String finalXpathForReadOnlyFieldInProgramSite = tempXpathForReadOnlyFieldInProgramSite.replace("CRAFT",
					programSiteFieldName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForReadOnlyFieldInProgramSite);
			isDisplayedReadonlyFieldInProgSite = UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForReadOnlyFieldInProgramSite);
			WebElement readOnlyFieldEle = UIHelper.findAnElementbyXpath(driver,
					finalXpathForReadOnlyFieldInProgramSite);
			UIHelper.scrollToAnElement(readOnlyFieldEle);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedReadonlyFieldInProgSite;
	}

	// Get the all program component default field values
	public ArrayList<String> getProgramComponentFieldsDefaultValues(String progComponentFieldDetails) {
		try {

			if (progComponentFieldDetails.contains(";")) {
				String splittedAllFieldDetails[] = progComponentFieldDetails.split(";");

				for (String fieldDetails : splittedAllFieldDetails) {
					addDataToActualList(fieldDetails);
				}
			} else {
				addDataToActualList(progComponentFieldDetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return progComponentFieldsValuesActualList;
	}

	// Add program component site data to Actual List
	public void addDataToActualList(String fieldDetails) {
		try {
			if (fieldDetails.contains(":")) {
				String splittedFieldDetails[] = fieldDetails.split(":");

				if (splittedFieldDetails != null) {
					if (splittedFieldDetails[0].equalsIgnoreCase("ISBN13")) {
						String isbn13FieldValue = splittedFieldDetails[0] + ":"
								+ UIHelper.getTextFromWebElement(driver, isbn13FieldValXpath).trim();
						progComponentFieldsValuesActualList.add(isbn13FieldValue);
					} else if (splittedFieldDetails[0].equalsIgnoreCase("ISBN10")) {
						String isbn10FieldValue = splittedFieldDetails[0] + ":"
								+ UIHelper.getTextFromWebElement(driver, isbn10FieldValXpath).trim();
						progComponentFieldsValuesActualList.add(isbn10FieldValue);
					} else if (splittedFieldDetails[0].equalsIgnoreCase("Contents")) {
						String finalContentsDropdownItemsXpath = contentsDropdownItemsXpath.replace("CRAFT",
								splittedFieldDetails[0]);
						String convertedDropdownValToLower = splittedFieldDetails[0].toLowerCase();
						String finalContentsDropdownBtnXpath = contentsDropdownBtnXpath.replace("CRAFT",
								convertedDropdownValToLower);

						UIHelper.waitForVisibilityOfEleByXpath(driver, finalContentsDropdownBtnXpath);
						UIHelper.click(driver, finalContentsDropdownBtnXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, programComponentDropDownContainerXpath);
						UIHelper.waitFor(driver);

						List<WebElement> contentDropdownListEle = UIHelper
								.findListOfElementsbyXpath(finalContentsDropdownItemsXpath, driver);
						int listSize = contentDropdownListEle.size();
						for (int index = 0; index < listSize; index++) {
							if ((listSize - 1) != index) {
								contentValues = contentValues + contentDropdownListEle.get(index).getText().trim()
										+ ",";
							} else {
								contentValues = contentValues + contentDropdownListEle.get(index).getText().trim();
							}
						}

						String finalContentVal = splittedFieldDetails[0] + ":" + contentValues;
						progComponentFieldsValuesActualList.add(finalContentVal);
					} else if (splittedFieldDetails[0].equalsIgnoreCase("Program Component Title")) {
						String finalCommonXpathForProgramComponentFieldValXpath = commonXpathForProgramComponentFieldValXpath
								.replace("CRAFT", splittedFieldDetails[0]);
						String programComponentTitleField = UIHelper
								.getTextFromWebElement(driver, finalCommonXpathForProgramComponentFieldValXpath).trim();

						String finalProgramSiteFieldValue = "";
						if (programComponentTitleField == null | programComponentTitleField.isEmpty()) {
							programComponentTitleField = UIHelper.getTextFromWebElement(driver, pageTitleXpath).trim();
							int lastIndex = splittedFieldDetails[0].length();
							finalProgramSiteFieldValue = splittedFieldDetails[0] + ":"
									+ programComponentTitleField.substring(0, lastIndex + 5);
						} else {
							finalProgramSiteFieldValue = splittedFieldDetails[0] + ":" + programComponentTitleField;
						}

						progComponentFieldsValuesActualList.add(finalProgramSiteFieldValue);
					} else {
						String finalCommonXpathForProgramComponentFieldValXpath = commonXpathForProgramComponentFieldValXpath
								.replace("CRAFT", splittedFieldDetails[0]);
						String programComponentSiteField = splittedFieldDetails[0] + ":" + UIHelper
								.getTextFromWebElement(driver, finalCommonXpathForProgramComponentFieldValXpath).trim();
						progComponentFieldsValuesActualList.add(programComponentSiteField);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the all program component default field values
	public ArrayList<String> getAllProgramComponentFieldDefaultValues(String progComponentFieldDetails) {
		try {

			if (progComponentFieldDetails.contains(";")) {
				String splittedAllFieldDetails[] = progComponentFieldDetails.split(";");

				for (String fieldDetails : splittedAllFieldDetails) {
					getProgFieldDefaultDataAndAddToActualList(fieldDetails);
				}
			} else {
				getProgFieldDefaultDataAndAddToActualList(progComponentFieldDetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return progComponentFieldDefaultValuesList;
	}

	// Add program component site data to Actual List
	public void getProgFieldDefaultDataAndAddToActualList(String fieldDetails) {
		try {
			if (fieldDetails.contains(":")) {
				String splittedFieldDetails[] = fieldDetails.split(":");

				if (splittedFieldDetails != null) {
					if (splittedFieldDetails[0].equalsIgnoreCase("ISBN13")) {
						String isbn13FieldValue = splittedFieldDetails[0] + ":"
								+ UIHelper.getTextFromWebElement(driver, isbn13FieldValXpath).trim();
						progComponentFieldDefaultValuesList.add(isbn13FieldValue);
					} else if (splittedFieldDetails[0].equalsIgnoreCase("ISBN10")) {
						String isbn10FieldValue = splittedFieldDetails[0] + ":"
								+ UIHelper.getTextFromWebElement(driver, isbn10FieldValXpath).trim();
						progComponentFieldDefaultValuesList.add(isbn10FieldValue);
					} else if (splittedFieldDetails[0].equalsIgnoreCase("Contents")) {
						String convertedDropdownValToLower = splittedFieldDetails[0].toLowerCase();
						String finalContentsDropdownBtnXpath = contentsDropdownBtnXpath.replace("CRAFT",
								convertedDropdownValToLower);

						UIHelper.waitForVisibilityOfEleByXpath(driver, finalContentsDropdownBtnXpath);

						String finalContentVal = splittedFieldDetails[0] + ":"
								+ UIHelper.getTextFromWebElement(driver, finalContentsDropdownBtnXpath).trim();
						progComponentFieldDefaultValuesList.add(finalContentVal);
					} else if (splittedFieldDetails[0].equalsIgnoreCase("Program Component Title")) {
						String finalCommonXpathForProgramComponentFieldValXpath = commonXpathForProgramComponentFieldValXpath
								.replace("CRAFT", splittedFieldDetails[0]);
						String programComponentTitleField = UIHelper
								.getTextFromWebElement(driver, finalCommonXpathForProgramComponentFieldValXpath).trim();

						String finalProgramSiteFieldValue = "";
						if (programComponentTitleField == null | programComponentTitleField.isEmpty()) {
							programComponentTitleField = UIHelper.getTextFromWebElement(driver, pageTitleXpath).trim();
							int lastIndex = splittedFieldDetails[0].length();
							finalProgramSiteFieldValue = splittedFieldDetails[0] + ":"
									+ programComponentTitleField.substring(0, lastIndex + 5);
						} else {
							finalProgramSiteFieldValue = splittedFieldDetails[0] + ":" + programComponentTitleField;
						}

						progComponentFieldDefaultValuesList.add(finalProgramSiteFieldValue);
					} else {
						String finalCommonXpathForProgramComponentFieldValXpath = commonXpathForProgramComponentFieldValXpath
								.replace("CRAFT", splittedFieldDetails[0]);
						String programComponentSiteField = splittedFieldDetails[0] + ":" + UIHelper
								.getTextFromWebElement(driver, finalCommonXpathForProgramComponentFieldValXpath).trim();
						progComponentFieldDefaultValuesList.add(programComponentSiteField);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getComponentNavigatorSites() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, componentNavigatorDashletXpath);

			WebElement componentNavigatorDashletEle = UIHelper.findAnElementbyXpath(driver,
					componentNavigatorDashletXpath);
			UIHelper.highlightElement(driver, componentNavigatorDashletEle);
			UIHelper.mouseOveranElement(driver, componentNavigatorDashletEle);

			List<WebElement> componentNavigatorContentsListEle = UIHelper
					.findListOfElementsbyXpath(componentNavigatorContentsXpath, driver);
			for (WebElement ele : componentNavigatorContentsListEle) {
				componentNavigatorSitesListValues.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return componentNavigatorSitesListValues;
	}

	public boolean isDisplayedProgSiteAsParentToComponentSites(String progSiteName) {
		try {
			String finalProgComponentSiteChildItemsXpath = progComponentSiteChildItemsXpath.replace("CRAFT",
					progSiteName);

			List<WebElement> progComponentSitesList = UIHelper
					.findListOfElementsbyXpath(finalProgComponentSiteChildItemsXpath, driver);

			for (WebElement ele : progComponentSitesList) {
				if (UIHelper.checkForAnWebElement(ele)) {
					isDisplayedProgSiteAsParentOne = true;
					break;
				} else {
					isDisplayedProgSiteAsParentOne = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedProgSiteAsParentOne;
	}

	public void clickOnSiteInComponentNavigatorDashlet(String siteName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, componentNavigatorDashletXpath);

			WebElement componentNavigatorDashletEle = UIHelper.findAnElementbyXpath(driver,
					componentNavigatorDashletXpath);
			UIHelper.highlightElement(driver, componentNavigatorDashletEle);
			UIHelper.mouseOveranElement(driver, componentNavigatorDashletEle);

			List<WebElement> componentNavigatorContentsListEle = UIHelper
					.findListOfElementsbyXpath(componentNavigatorContentsXpath, driver);
			for (WebElement ele : componentNavigatorContentsListEle) {
				if (ele.getText().trim().contains(siteName) || ele.getText().trim().equalsIgnoreCase(siteName)) {
					UIHelper.highlightElement(driver, ele);
					ele.click();
					UIHelper.waitForPageToLoad(driver);
				}
			}

			report.updateTestLog("Click on site link",
					"User successfully clicked the site link:" + siteName + " in 'Component Navigator' Dashlet",
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the Program site Dashlet details
	public ArrayList<String> getProgramDashletFieldDetails() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, programsiteDetailsXpath);

			WebElement programSiteDetailsEle = UIHelper.findAnElementbyXpath(driver, programsiteDetailsXpath);

			UIHelper.scrollToAnElement(programSiteDetailsEle);
			UIHelper.highlightElement(driver, programSiteDetailsEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, xpathForProgramSiteDashletFields);
			List<WebElement> programSiteDashletLabels = UIHelper
					.findListOfElementsbyXpath(xpathForProgramSiteFieldLabels, driver);
			for (WebElement ele : programSiteDashletLabels) {
				String fieldValue = ele.getText().trim();
				if (!fieldValue.isEmpty()) {
					programDashletList.add(fieldValue);
				}
			}

			// programSiteDashletText = UIHelper.getTextFromWebElement(driver,
			// xpathForProgramSiteFieldLabels).trim();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return programDashletList;
	}

	public ArrayList<String> getactualDropdDownFieldsAndValuesInProgramDashlet(String fieldDetails) {
		try {
			UIHelper.waitFor(driver);
			WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

			UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
			UIHelper.waitFor(driver);

			if (fieldDetails.contains(";")) {
				String splittedFieldNames[] = fieldDetails.split(";");

				for (String fieldValues : splittedFieldNames) {
					commonMethodForAddValuesExpectedList(fieldValues);
				}
			} else {
				commonMethodForAddValuesExpectedList(fieldDetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return actualDropDownValuesList;
	}

	public void addProgramSiteDropdownValuesToActualList(String dropdownFieldName) {
		try {
			String finalDropdownFieldName;
			if (dropdownFieldName.contains(" ")) {
				String tempDropDownval = dropdownFieldName.replace(" ", ",");
				String splittedFieldName[] = tempDropDownval.split(",");
				String firstName = splittedFieldName[0].toLowerCase();
				finalDropdownFieldName = firstName + splittedFieldName[1];
			} else {
				finalDropdownFieldName = dropdownFieldName.toLowerCase();
			}
			String finalSelectDropValXpathForProgram = tempSelectDropValXpathForProgram.replace("CRAFT",
					finalDropdownFieldName);

			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver, finalSelectDropValXpathForProgram);
			UIHelper.highlightElement(driver, dropdownEle);

			UIHelper.waitFor(driver);

			UIHelper.clickanElement(dropdownEle);
			UIHelper.waitFor(driver);

			List<WebElement> programDropDownElementsList = UIHelper
					.findListOfElementsbyXpath(programDashletAllDropDownsXpath, driver);

			int listSize = programDropDownElementsList.size();
			String dropdownValues = "";
			for (WebElement ele : programDropDownElementsList) {

				String innerText = ""
						+ ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText", ele);
				dropdownValues = dropdownValues + innerText + ",";
				// dropdownValues = dropdownValues + ele.getText().trim() + ",";
			}

			UIHelper.clickanElement(dropdownEle);
			String finalDropdownValues = dropdownFieldName + ":" + dropdownValues;

			actualDropDownValuesList.add(finalDropdownValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commonMethodForAddValuesExpectedList(String fieldDetail) {
		try {
			if (fieldDetail.contains(":")) {
				String splittedFieldValues[] = fieldDetail.split(":");
				if (splittedFieldValues != null) {
					addProgramSiteDropdownValuesToActualList(splittedFieldValues[0]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkProgramDashletAsDefaultOneForProgramSite(String dashletNme) {
		try {
			String finalDashletTitle = tempDashletTitle.replace("CRAFT", dashletNme);
			if (UIHelper.checkForAnElementbyXpath(driver, finalDashletTitle)) {
				isDisplayedProgramDaslet = true;
			} else {
				isDisplayedProgramDaslet = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedProgramDaslet;
	}

	public boolean checkApplicationLogo() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, appLogoXpath)) {
				isDisplayedAppLogo = true;
			} else {
				isDisplayedAppLogo = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedAppLogo;
	}

	public boolean checkBlueTints() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, allMembersXpath)) {
				WebElement ele = UIHelper.findAnElementbyXpath(driver, allMembersXpath);
				String getColourObj = ele.getAttribute("class");

				if (getColourObj.equalsIgnoreCase("theme-color-1") || getColourObj.contains("theme-color-1")) {
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

	// Remove Dashlet
	public void removeDashletIn() {

		String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
		String finalAddedDashletHomePgXPath = addedDashletHomePgXPath.replace("CRAFT", dashletNmetoAdd);

		try {

			String dashletName = dataTable.getData("Home", "DashletName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, dashletValues);

			UIHelper.findandAddElementsToaList(driver, dashletValues, availableDashletList);

			for (String dashlet : availableDashletList) {
				System.out.println("Result: " + dashlet);
				if (dashlet.equalsIgnoreCase(dashletName)) {

					String dragDropValues = dragDropValue.replace("CRAFT", dashlet);
					UIHelper.highlightElement(driver, dragDropValues);
					UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, dragDropValues));
					UIHelper.waitFor(driver);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, dragDropValues));
					UIHelper.waitFor(driver);
					UIHelper.highlightElement(driver, dashlet);
					UIHelper.waitForVisibilityOfEleByXpath(driver, movetoRemvDashXPath);
					UIHelper.dragAndDrop(driver, dragDropValues, movetoRemvDashXPath);

				}
			}
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnDashXPath);
			UIHelper.click(driver, okBtnDashXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isSiteFileTypeBreakdownTitleAvailable() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, siteFileTypeBreakDownTitleXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, siteFileTypeBreakDownTitleXpath));
				UIHelper.highlightElement(driver, siteFileTypeBreakDownTitleXpath);

				UIHelper.waitFor(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
					WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

					UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
					UIHelper.waitFor(driver);
				}
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
	public boolean isSiteContributorBreakdownTitleAvailable() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, siteContributorBreakdownTitleXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, siteContributorBreakdownTitleXpath));
				UIHelper.highlightElement(driver, siteContributorBreakdownTitleXpath);

				UIHelper.waitFor(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
					WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

					UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
					UIHelper.waitFor(driver);
				}
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Check Site Dashboard
	public boolean checkSiteDashboard() {
		boolean isDiaplayedSiteDashboard = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteDashboardXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, siteDashboardXpath)) {
				isDiaplayedSiteDashboard = true;
			} else {
				isDiaplayedSiteDashboard = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDiaplayedSiteDashboard;

	}

	public void openFoldersInEPMDashlet(String folderName) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmDashletXpath);
			WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver, epmDashletXpath);
			UIHelper.highlightElement(driver, pageBodyEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(epmDashletFoldersXpath, driver);
			System.out.println(uploadedFileOrFolderTitleEleList.size());
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				System.out.println(ele.getText());
				if (ele.getText().equalsIgnoreCase(folderName)) {
					ele.click();
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					UIHelper.waitFor(driver);
					report.updateTestLog("Navigate to Folder", "Navigated to Folder" + folderName, Status.PASS);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getUploadedFileOrFolderTitle() {
		ArrayList<String> uploadedFilesNameList = new ArrayList<String>();

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmDashletXpath);
			WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver, epmDashletXpath);
			UIHelper.highlightElement(driver, pageBodyEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper.findListOfElementsbyXpath(epmDashletFileXpath,
					driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				System.out.println(ele.getText());
				uploadedFilesNameList.add(ele.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadedFilesNameList;
	}

	// Check Folder in EPM Dashlet
	public void selectFoldersInEPMDashlet(String folderName) {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmDashletXpath);
			WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver, epmDashletXpath);
			UIHelper.highlightElement(driver, pageBodyEle);
			String finalRadioButtonXpath = epmDashletFileOrFolderRadioBtnXpath.replace("CRAFT", folderName);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalRadioButtonXpath);
			UIHelper.click(driver, finalRadioButtonXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchFindBtnXpath);
			report.updateTestLog("Select File/Folder", "File/Folder selected:" + folderName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Search with EPM content
	public void searchEPM(String searchContent, String property) {
		try {
			enterSearchCriTeria(searchContent, property);
			clickOnChooseThisRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterSearchCriTeria(String searchContent, String property) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchFindBtnXpath);
			String finalRadioBtnXpath = epmSearchRadioBtnXpath.replace("CRAFT", property);
			System.out.println(finalRadioBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalRadioBtnXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, epmSearchInputBoxXpath, searchContent);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, finalRadioBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchFindBtnXpath);
			UIHelper.click(driver, epmSearchFindBtnXpath);
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, epmSearchChooseThisRecordBtnXpath);
			report.updateTestLog("Search Results", "Search Records displayed Sucessfully", Status.DONE);

		} catch (Exception e) {

			report.updateTestLog("Search Results", "Search Results failed to Display", Status.FAIL);
		}
	}

	// To Enter search Criteria
	public void enterEPMSearchCriteria(String searchContent, String property) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchFindBtnXpath);
			String finalRadioBtnXpath = epmSearchRadioBtnXpath.replace("CRAFT", property);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalRadioBtnXpath);
			UIHelper.click(driver, finalRadioBtnXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, epmSearchInputBoxXpath, searchContent);
			report.updateTestLog("Enter Search Criteria", "Search Criteria entered Sucessfully", Status.DONE);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on 'Choose This Record' button
	public void checkWarningMessageWhenclickOnChooseThisRecord(String folderName) {
		try {
			UIHelper.click(driver, epmSearchChooseThisRecordBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmPromptBoxHeadlineXpath);
			String statusMessage = UIHelper.getTextFromWebElement(driver, epmPromptBoxHeadlineXpath);
			if (statusMessage.equalsIgnoreCase("Warning")) {
				report.updateTestLog("Verify warning message",
						"Warning message 'This record has already been associated with the archive(s): '" + folderName
								+ " displayed sucessfully",
						Status.PASS);
				UIHelper.click(driver, epmPromptCancelBtnxpath);
				UIHelper.waitFor(driver);

			} else if (statusMessage.equalsIgnoreCase("Success")) {
				report.updateTestLog("Verify warning message",
						"Warning message 'This record has already been associated with the archive(s): '" + folderName
								+ " failed to display",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on 'Choose This Record' button
	public void clickOnProceedBtnInWarningMessagePopup(String folderName) {
		try {
			UIHelper.click(driver, epmSearchChooseThisRecordBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmPromptBoxHeadlineXpath);
			String statusMessage = UIHelper.getTextFromWebElement(driver, epmPromptBoxHeadlineXpath);
			if (statusMessage.equalsIgnoreCase("Warning")) {
				report.updateTestLog("Verify warning message",
						"Warning message 'This record has already been associated with the archive(s): '" + folderName
								+ " displayed sucessfully",
						Status.PASS);
				UIHelper.click(driver, epmPromptProceedBtnxpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, epmPromptOKXpath);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, epmPromptOKXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, epmDashletXpath);
				report.updateTestLog("Select a Record", "Record selected sucessfully", Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Click On Choose This Record button
	public void clickOnChooseThisRecord() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, epmSearchChooseThisRecordBtnXpath);
			UIHelper.click(driver, epmSearchChooseThisRecordBtnXpath);
			UIHelper.waitFor(driver);
			while (true) {
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, epmPromptBoxHeadlineXpath);
				String statusMessage = UIHelper.getTextFromWebElement(driver, epmPromptBoxHeadlineXpath);
				if (statusMessage.equalsIgnoreCase("Warning")) {
					UIHelper.click(driver, epmPromptCancelBtnxpath);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, epmSearchNextBtnXpath);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, epmSearchChooseThisRecordBtnXpath);

				} else if (statusMessage.equalsIgnoreCase("Success")) {
					UIHelper.waitFor(driver);
					UIHelper.click(driver, epmPromptOKXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, epmDashletXpath);
					report.updateTestLog("Select a Record", "Record selected sucessfully", Status.DONE);

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select a Record", "Unable to select a record", Status.FAIL);
		}
	}

	// To sort EPM library
	public ArrayList<String> sortEPMDocumentLibrary(String sortby) {
		try {

			String actualSortBy = tempSortByXpath.replace("CRAFT", sortby);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, actualSortBy);
			} catch (Exception e) {
			}

			if (!sortby.equalsIgnoreCase("Folder Name")) {
				UIHelper.click(driver, actualSortBy);
				UIHelper.waitFor(driver);
			}

			sortList.clear();
			UIHelper.findandAddElementsToaList(driver, epmFolderNames, sortList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortList;
	}

	// To Validate EMPSerach Panel
	public void EPMSearchPanel(String searchContent, String property) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchFindBtnXpath);
			String finalRadioBtnXpath = epmSearchRadioBtnXpath.replace("CRAFT", property);
			System.out.println(finalRadioBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalRadioBtnXpath);
			UIHelper.click(driver, finalRadioBtnXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, epmSearchInputBoxXpath, searchContent);
			UIHelper.waitFor(driver);

			validateFindTab();
			validateEPMSearchTitle();
			clickOnEPMFindButton();

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchChooseThisRecordBtnXpath);

			validateSearchResultsTab();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Validate Search Results Tab is Present or not
	private void validateSearchResultsTab() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchResultsTabXpath);
			String searchTab = UIHelper.getTextFromWebElement(driver, epmSearchResultsTabXpath);
			if (searchTab.equalsIgnoreCase("Search Results")) {
				report.updateTestLog("Search Results Tab",
						"Search Results Tab displayed Sucessfully, Tab Name:" + searchTab, Status.DONE);
			} else {
				report.updateTestLog("Search Results Tab", "Search Results Tab is not displayed:" + searchTab,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To Validate Find Tab is Present or not
	private void validateFindTab() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmFindTabXpath);
			String findTab = UIHelper.getTextFromWebElement(driver, epmFindTabXpath);
			if (findTab.equalsIgnoreCase("Find")) {
				report.updateTestLog("Find Tab", "Find Tab displayed Sucessfully,Tab Name:" + findTab, Status.PASS);
			} else {
				report.updateTestLog("Find Tab", "Find Tab is not displayed:" + findTab, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To Click on Find Tab
	public void clickOnFindTab() {

		try {
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, epmFindTabXpath);
			} catch (Exception e) {
			}
			String findTab = UIHelper.getTextFromWebElement(driver, epmFindTabXpath);
			if (findTab.equalsIgnoreCase("Find")) {
				UIHelper.click(driver, epmFindTabXpath);
				report.updateTestLog("Find Tab", "Find Tab Clicked Sucessfully,Tab Name:" + findTab, Status.PASS);
			} else {
				report.updateTestLog("Find Tab", "Find Tab is not able to Click:" + findTab, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To Validate EPM Serch Panel
	private void validateEPMSearchTitle() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchTitleXpath);
			String title = UIHelper.getTextFromWebElement(driver, epmSearchTitleXpath);
			if (title.equalsIgnoreCase("EPM Search - Standard Options")) {
				report.updateTestLog("EPM Search Title", "EPM Search Title is as per Expected:" + title, Status.DONE);
			} else {
				report.updateTestLog("EPM Search Title", "EPM Search Title is as not as Expected:" + title,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To Click on Find Button
	public void clickOnEPMFindButton() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchFindBtnXpath);
			UIHelper.click(driver, epmSearchFindBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchChooseThisRecordBtnXpath);
			report.updateTestLog("Find Button", "Find Button Clicked sucessfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Click on Exit Button
	public void clickOnEPMSearchExit() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchExitBtnXpath);
			UIHelper.click(driver, epmSearchExitBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmDashletXpath);
			report.updateTestLog("Exit Button", "Exit Button Clicked sucessfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Click on Next Button in EPM Search
	public void clickOnEPMNextButton() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchNextBtnXpath);
			UIHelper.click(driver, epmSearchNextBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchRecordXpath);
			report.updateTestLog("Next Button", "Next Button Clicked sucessfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Click on Previous Button in EPM Search
	public void clickOnEPMPreviousButton() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchPreviousBtnXpath);
			UIHelper.click(driver, epmSearchPreviousBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchRecordXpath);
			report.updateTestLog("Previous Button", "Previous Button Clicked sucessfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Click on Empty Button in EPM Search
	public void clickOnEPMSearchEmpty() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchEmptyBtnXpath);
			UIHelper.click(driver, epmSearchEmptyBtnXpath);
			report.updateTestLog("Empty Button", "Empty Button Clicked sucessfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To Valdiate Empty Button cleared entered values
	public void validateEmptyButton() {
		try {
			UIHelper.waitFor(driver);
			String isCleared = UIHelper.getTextFromWebElement(driver, epmSearchInputBoxXpath);

			if (isCleared.isEmpty()) {
				report.updateTestLog("Empty Button", "Empty Button Cleared the values sucessfully", Status.PASS);
			} else {
				report.updateTestLog("Empty Button", "Empty Button not Cleared the values", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean getEPMSearchResults(String searchContent, String property) {
		isDisplayed = false;
		try {
		/*	String epmRecord = epmSearchRecordResultContains.replace("CRAFT", searchContent);
			System.out.println(epmRecord);*/
			String finalempRecord = epmSearchRecordResultContains.replace("Alfresco", property).replace("CRAFT", searchContent);
			System.out.println(finalempRecord);

			WebElement element = UIHelper.findAnElementbyXpath(driver, finalempRecord);
			String testVal = element.getAttribute("title");
			System.out.println(testVal);
			if (testVal.contains(searchContent)) {
				isDisplayed = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayed;

	}

	// Get the Biblio Data from Database Search - EPM Dashlet and store it into
	// Text File
	public void getBiblioDataAndStoreIntoTextFile() {
		try {
			List<WebElement> biblioFieldTitlesListEle = UIHelper.findListOfElementsbyXpath(biblioFieldTitlesXpath,
					driver);
			List<WebElement> biblioFieldValuesListEle = UIHelper.findListOfElementsbyXpath(biblioFieldValuesXpath,
					driver);

			ArrayList<String> fieldTitlesList = new ArrayList<String>();
			ArrayList<String> fieldValuesList = new ArrayList<String>();

			for (WebElement fieldTitleEle : biblioFieldTitlesListEle) {
				fieldTitlesList.add(fieldTitleEle.getText().trim());
			}

			for (WebElement fieldValueEle : biblioFieldValuesListEle) {
				fieldValuesList.add("" + fieldValueEle.getAttribute("title").trim());
			}

			if (fieldTitlesList != null && fieldValuesList != null
					&& fieldTitlesList.size() == fieldValuesList.size()) {
				for (int index = 0; index < fieldTitlesList.size(); index++) {
					new FileUtil().appendTextToFile(fieldTitlesList.get(index) + ":" + fieldValuesList.get(index),
							biblioDataFilePath);
				}
			}

			report.updateTestLog("Store Biblio Data into Text File", "Biblio Data stored sucessfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public boolean checkPreButtonAvailable() {
		boolean flag = false;
		try {
			String preButtonXpath = ".//*[@id='btnPrev-epm']";
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, preButtonXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, preButtonXpath))) {
				UIHelper.highlightElement(driver, preButtonXpath);
				flag = true;
				report.updateTestLog("Check for '<' button", "'<' button available", Status.PASS);
			} else {
				flag = false;
				report.updateTestLog("Check for '<' button", "'<' button not available", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check for '<' button", "Check for '<' button Failed", Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 */
	public boolean checkNextButtonAvailable() {
		boolean flag = false;
		try {
			String nextButtonXpath = ".//*[@id='btnNext-epm']";
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, nextButtonXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, nextButtonXpath))) {
				UIHelper.highlightElement(driver, nextButtonXpath);
				flag = true;
				report.updateTestLog("Check for '>' button", "'>' button available", Status.PASS);
			} else {
				flag = false;
				report.updateTestLog("Check for '>' button", "'>' button not available", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check for '>' button", "Check for '>' button Failed", Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 */
	public boolean chooseRecordButtonAvailable() {
		boolean flag = false;
		try {
			String chooseRecordButtonXpath = ".//*[@id='btnChooseRecord-epm']";
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, chooseRecordButtonXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, chooseRecordButtonXpath))) {
				UIHelper.highlightElement(driver, chooseRecordButtonXpath);
				flag = true;
				report.updateTestLog("Check for 'Choose This Record' button", "'Choose This Record' button available",
						Status.PASS);
			} else {
				flag = false;
				report.updateTestLog("Check for 'Choose This Record' button",
						"'Choose This Record' button not available", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check for 'Choose This Record' button",
					"Check for 'Choose This Record' button Failed", Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 */
	public boolean exitButtonAvailable() {
		boolean flag = false;
		try {
			String exitButtonXpath = ".//*[@id='btnClose-epm']";
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, exitButtonXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, exitButtonXpath))) {
				UIHelper.highlightElement(driver, exitButtonXpath);
				flag = true;
				report.updateTestLog("Check for 'Exit' button", "'Exit' button available", Status.PASS);
			} else {
				flag = false;
				report.updateTestLog("Check for 'Exit' button", "'Exit' button not available", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check for 'Exit' button", "Check for 'Exit' button Failed", Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 */
	public boolean isEPMSearchFieldsAvailable(String fieldName) {
		boolean flag = false;
		try {
			String epmSearchFieldXpath = ".//*[@id='biblioTable-epm']/div/div/span[text()='CRAFT']";
			epmSearchFieldXpath = epmSearchFieldXpath.replace("CRAFT", fieldName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchFieldXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epmSearchFieldXpath))) {
				UIHelper.highlightElement(driver, epmSearchFieldXpath);
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
	public void clickOnFindButton() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchFindBtnXpath);
			UIHelper.highlightElement(driver, epmSearchFindBtnXpath);
			UIHelper.click(driver, epmSearchFindBtnXpath);
			report.updateTestLog("Click 'Find' Button", "'Find' Button clicked", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickOKButtonInPopMsg() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okButtonPopUpXpath);
			UIHelper.highlightElement(driver, okButtonPopUpXpath);
			UIHelper.click(driver, okButtonPopUpXpath);
			report.updateTestLog("Click 'Ok' Button", "'Ok' Button clicked", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickFindTab() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, findTabXpath);
			UIHelper.highlightElement(driver, findTabXpath);
			UIHelper.click(driver, findTabXpath);
			report.updateTestLog("Click 'Find' Tab", "'Find' Tab clicked", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param popUpMsg
	 */
	public void verifyEPMPopUpMsg(String popUpMsg) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popUpPanelXapth);
			UIHelper.highlightElement(driver, popUpPanelXapth);
			UIHelper.highlightElement(driver, okButtonPopUpXpath);
			String actualMsg = UIHelper.getTextFromWebElement(driver, popUpPanelXapth).trim();
			System.out.println("ACTUAL POPUP MSG : " + actualMsg);
			System.out.println("Expected MSG : " + popUpMsg);
			if (popUpMsg.equalsIgnoreCase(actualMsg)) {
				report.updateTestLog("Verify PopUp Message", "PopUp Message verified successfully"
						+ "<br/><b>Expected Message : </b>" + popUpMsg + "<br/><b>Actual Message : </b>" + actualMsg,
						Status.PASS);
			} else {
				report.updateTestLog("Verify PopUp Message", "PopUp Message not verified"
						+ "<br/><b>Expected Message : </b>" + popUpMsg + "<br/><b>Actual Message : </b>" + actualMsg,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify PopUp Message", "Verify PopUp Message Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param listTitleData
	 */
	public void enterListTitle(String listTitleData) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, listTitleInputXpath);
			UIHelper.highlightElement(driver, listTitleInputXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, listTitleInputXpath, listTitleData);
			report.updateTestLog("Enter 'List Title' data",
					"Data entered successfully" + "<br/><b>Data Entered : </b>" + listTitleData, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param fromDate
	 */
	public void enterFromDate(String fromDate) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, fromDateXpath);
			UIHelper.highlightElement(driver, fromDateXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, fromDateXpath, fromDate);
			report.updateTestLog("Enter 'From Date'",
					"'From Date' entered successfully" + "<br/><b>Date Entered : </b>" + fromDate, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clearFromDate() {
		driver.findElement(By.xpath(fromDateXpath)).clear();
	}

	/**
	 * @author 412766
	 * @param toDate
	 */
	public void enterToDate(String toDate) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, toDateXpath);
			UIHelper.highlightElement(driver, toDateXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, toDateXpath, toDate);
			report.updateTestLog("Enter 'To Date'",
					"'To Date' entered successfully" + "<br/><b>Date Entered : </b>" + toDate, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyEPMSearchResultsDisplayed() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchResultPanel);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epmSearchResultPanel))) {
				UIHelper.highlightElement(driver, epmSearchResultPanel);
				report.updateTestLog("Verify EPM search results", "Results Displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify EPM search results", "Results Not Displayed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify EPM search results", "Verify EPM search results Failed", Status.FAIL);
		}
	}

	// Get Folder ISBN Value from Database Search - EPM Dashlet and store into
	// Text File
	public void getFolderISBNValAndStoreIntoTxtFile() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, folderListIsbnValXpath);
			String folderISBNValue = UIHelper.getTextFromWebElement(driver, folderListIsbnValXpath).trim();
			new FileUtil().writeTextToFile(folderISBNValue, folderISBNValStoreFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Archived Folder ISBN Value from Database Search - EPM Dashlet
	public String getFolderISBNValFromDataBaseSearchEPMDashlet() {
		String folderISBNValue = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, folderListIsbnValXpath);
			folderISBNValue = UIHelper.getTextFromWebElement(driver, folderListIsbnValXpath).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return folderISBNValue;
	}

	// Get Archived Folder Full Title from Database Search - EPM Dashlet
	public String getFolderFullTitleValueFromDataBaseSearchEPMDashlet() {
		String folderFullTitleValue = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, folderFullTitleValXpath);
			folderFullTitleValue = UIHelper.getTextFromWebElement(driver, folderFullTitleValXpath).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return folderFullTitleValue;
	}

	public boolean isDisabledSearchResultsTabInDatabaseSearchEPM() {
		boolean isDisplayedSearchResultsTab = false;
		try {

			if (UIHelper.checkForAnElementbyXpath(driver, disabledSearcResultsXpath)) {
				isDisplayedSearchResultsTab = true;
			} else {
				isDisplayedSearchResultsTab = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedSearchResultsTab;
	}

	// Click on 'Find' Tab in Database Search - EPM
	public void clickOnFindTabInDatabaseSearchEPM() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, findTabSecXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, findTabSecXpath)) {
				UIHelper.click(driver, findTabSecXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchFindBtnXpath);
				if (UIHelper.checkForAnElementbyXpath(driver, epmSearchFindBtnXpath)) {
					report.updateTestLog("Click on 'Find' Tab", "'Find' Tab details displayed successfully",
							Status.PASS);
				} else {
					report.updateTestLog("Click on 'Find' Tab", "'Find' Tab details failed to display", Status.FAIL);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on 'Search Results' Tab in Database Search - EPM
	public void clickOnSearchResultsTabInDatabaseSearchEPM() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsTabXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, searchResultsTabXpath)) {
				UIHelper.click(driver, searchResultsTabXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, epmSearchChooseThisRecordBtnXpath);
				if (UIHelper.checkForAnElementbyXpath(driver, epmSearchChooseThisRecordBtnXpath)) {
					report.updateTestLog("Click on 'Search Results' Tab",
							"'Search Results' Tab details displayed successfully", Status.PASS);
				} else {
					report.updateTestLog("Click on 'Search Results' Tab",
							"'Search Results' Tab details failed to display", Status.FAIL);
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
	public boolean isSiteRelationshipDashletAdded() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteRelationshipDashletHeaderXpath);
			if (UIHelper
					.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, siteRelationshipDashletHeaderXpath))) {
				UIHelper.highlightElement(driver, siteRelationshipDashletHeaderXpath);
				UIHelper.waitFor(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
					WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

					UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
					UIHelper.waitFor(driver);
				}
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//Add relationship from site relationship dashlet
		public boolean clickAddRelationshipFromSiteRelationshipDashlet() {
			boolean flag = false;
			
					try {
						UIHelper.waitFor(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, siteRelationshipDashletAddRelationshipBtn);
						if (UIHelper.isWebElementDisplayed(
								UIHelper.findAnElementbyXpath(driver, siteRelationshipDashletAddRelationshipBtn))) {
							UIHelper.highlightElement(driver, siteRelationshipDashletAddRelationshipBtn);
							UIHelper.click(driver, siteRelationshipDashletAddRelationshipBtn);
							UIHelper.waitFor(driver);
							report.updateTestLog("Click 'Add Relationship' in Site Dashboard", "Clicked Successfully", Status.PASS);
							UIHelper.waitFor(driver);					
							flag = true;
						} else {
							report.updateTestLog("Click 'Add Relationship' in Site Dashboard", "Add Relationship Not Clicked", Status.FAIL);
							flag=false;
						}
		}
	      
				 catch (Exception e) {
						e.printStackTrace();
					}

			return flag;
		}
	// Customize Site Dashlet
	public void navigateTocustomizeSiteDashboardPage() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteConfigBtnXpath);
			UIHelper.click(driver, siteConfigBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, customizedDashboardXpath);
			UIHelper.click(driver, customizedDashboardXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addDashletButtonXpath);

			report.updateTestLog("Navigate to Custom Site Dashboard Page",
					"Navigate to Custom Site Dashboard Page sucessfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Change Layout
	public void changeLayout(String layoutType, String layoutPositionVal) {
		try {
			String finalSelectBtnXpathForNewLayout = "";
			if (layoutPositionVal != null && !layoutPositionVal.isEmpty()) {
				finalSelectBtnXpathForNewLayout = tempXpathForSelectBtnInLayoutPage.replace("CRAFT", layoutType)
						.replace("LAYOUT_VALUE", layoutPositionVal);
			} else {
				if (layoutType.equalsIgnoreCase("Two")) {
					finalSelectBtnXpathForNewLayout = tempXpathForSelectBtnInLayoutPage.replace("CRAFT", layoutType)
							.replace("LAYOUT_VALUE", "columns: narrow left, wide right");
				} else {
					finalSelectBtnXpathForNewLayout = tempXpathForSelectBtnInLayoutPage.replace("CRAFT", layoutType)
							.replace("LAYOUT_VALUE", "columns");
				}
			}

			String finalXpathForCurrentLayout = tempXpathForCurrentLayout.replace("CRAFT", layoutType);

			UIHelper.waitForVisibilityOfEleByXpath(driver, changeLayoutXpath);

			if (!UIHelper.checkForAnElementbyXpath(driver, finalXpathForCurrentLayout)) {
				if (UIHelper.checkForAnElementbyXpath(driver, changeLayoutXpath)) {
					UIHelper.click(driver, changeLayoutXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, selectNewLayoutMessageXpath);
					UIHelper.waitFor(driver);

					UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectBtnXpathForNewLayout);
					if (UIHelper.checkForAnElementbyXpath(driver, finalSelectBtnXpathForNewLayout)) {
						UIHelper.click(driver, finalSelectBtnXpathForNewLayout);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, addDashletButtonXpath);
						UIHelper.waitFor(driver);
					}
				}

				report.updateTestLog("Change Layout of Site Dashboard Page",
						"Site Dashboard Page Layout changed sucessfully using Layout Option: " + layoutType + " "
								+ layoutPositionVal,
						Status.DONE);
			} else {
				report.updateTestLog("Change Layout of Site Dashboard Page", "Expected Layout:" + layoutType + " "
						+ layoutPositionVal + " is already available in Site Dashboard Page", Status.DONE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Add Dashlet Btn
	public void clickOnAddDashletBtn() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, addDashletButtonXpath);
			UIHelper.highlightElement(driver, addDashletButtonXpath);
			UIHelper.click(driver, addDashletButtonXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Dashlet Names by column from Add Dashlet Page
	public ArrayList<String> getDashletByColumnInAddDashletPage(String colNoOfDashletInt) {
		ArrayList<String> dashletColumnValues = new ArrayList<String>();
		try {
			String finalMoveTotDashColItemsXpath = toMoVeDashColItemsListXpath.replace("CRAFT", colNoOfDashletInt);
			UIHelper.waitFor(driver);
			List<WebElement> listOfElements = UIHelper.findListOfElementsbyXpath(finalMoveTotDashColItemsXpath, driver);

			for (WebElement ele : listOfElements) {
				dashletColumnValues.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dashletColumnValues;
	}

	// Click on Cance button in Customize Dashboard Page
	public void clickOnCancelBtnInCustomizeDasboardPg() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelBtnInCustomizeDashboardPage);
			if (UIHelper.checkForAnElementbyXpath(driver, cancelBtnInCustomizeDashboardPage)) {
				UIHelper.click(driver, cancelBtnInCustomizeDashboardPage);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, homeTabLinkXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Dashlet Names by column from Site Dashboard Page
	public ArrayList<String> getDashletByColumnInSiteDashboardPage(String colNoOfDashletInt) {
		ArrayList<String> dashletColumnValues = new ArrayList<String>();
		try {
			String finalMoveTotDashColItemsXpath = tempXpathForSiteDashletsByColumn.replace("CRAFT", colNoOfDashletInt);
			UIHelper.waitFor(driver);
			List<WebElement> listOfElements = UIHelper.findListOfElementsbyXpath(finalMoveTotDashColItemsXpath, driver);

			for (WebElement ele : listOfElements) {
				dashletColumnValues.add(ele.getText().trim().replace("-", " "));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dashletColumnValues;
	}

	// Check Site Dashlets in Site Dashboard Page
	public boolean checkDashletInSiteDashboard(String dashletName) {
		boolean isDisplayedSiteDashlet = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteDashboardXpath);
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

	/**
	 * @author 412766
	 */
	public void selectMyActivitiesInSiteActivityDashlet() {
		try {
			UIHelper.waitFor(driver);
			String activityType = dataTable.getData("MyActivities", "MyActivityDropDownValue");
			UIHelper.waitForVisibilityOfEleByXpath(driver, myActivityDropDownInSiteDashboardXpath);
			UIHelper.highlightElement(driver, myActivityDropDownInSiteDashboardXpath);
			UIHelper.click(driver, myActivityDropDownInSiteDashboardXpath);

			UIHelper.waitForPageToLoad(driver);
			String finalXpath = myActivityDropDownValueInSiteDashboardXpath.replace("CRAFT", activityType);
			UIHelper.highlightElement(driver, finalXpath);
			UIHelper.click(driver, finalXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void selectMyActivitiesDateRange(String dateRange) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, dateRangeDropdownXpath);
			UIHelper.highlightElement(driver, dateRangeDropdownXpath);
			UIHelper.click(driver, dateRangeDropdownXpath);

			UIHelper.waitForPageToLoad(driver);
			String finalXpath = dateValueXpath.replace("CRAFT", dateRange);
			UIHelper.highlightElement(driver, finalXpath);
			UIHelper.click(driver, finalXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void selectMyActivitiesCategory(String activity) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, activitiesDropdownXpath);
			UIHelper.highlightElement(driver, activitiesDropdownXpath);
			UIHelper.click(driver, activitiesDropdownXpath);

			UIHelper.waitForPageToLoad(driver);
			String finalXpath = activitiesValueXpath.replace("CRAFT", activity);
			UIHelper.highlightElement(driver, finalXpath);
			UIHelper.click(driver, finalXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param noOfActiviesToCheck
	 * @return
	 */
	public boolean isMyActivitiesDisplayedInSiteActivityDashlet(int noOfActiviesToCheck) {
		boolean flag = false;
		try {

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myActivitySiteDashboardPanelXpath);
			UIHelper.highlightElement(driver, myActivitySiteDashboardPanelXpath);

			List<WebElement> myActivityList = UIHelper.findListOfElementsbyXpath(myActivitySiteDashboardListXpath,
					driver);
			System.out.println("MY ACTIVITY LIST SIZE : " + myActivityList.size());

			if (myActivityList.size() >= noOfActiviesToCheck) {
				for (WebElement webElement : myActivityList) {
					UIHelper.scrollToAnElement(webElement);
					UIHelper.highlightElement(driver, webElement);
				}
				flag = true;
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
	public boolean isSiteActivityDashletAdded() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteActivitiesDashletHeaderXpath);
			if (UIHelper
					.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, siteActivitiesDashletHeaderXpath))) {
				UIHelper.highlightElement(driver, siteActivitiesDashletHeaderXpath);
				UIHelper.waitFor(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
					WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

					UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
					UIHelper.waitFor(driver);
				}
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
	public int toGetNoOfSiteActivitiesInSiteActivityDashlet() {
		int noOfActivities = 0;
		try {
			UIHelper.waitFor(driver);
			List<WebElement> myActivityList = UIHelper.findListOfElementsbyXpath(myActivitySiteDashboardListXpath,
					driver);
			noOfActivities = myActivityList.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noOfActivities;
	}

	/**
	 * @author 412766
	 */
	public void clickDeleteButton() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, deleteButtonXpath);
			UIHelper.click(driver, deleteButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param userName
	 * @param activityDid
	 * @param siteName
	 * @return
	 */
	public void verifyActivitiesDisplayedInSiteActivityDashlet(String userName, String activityDid, String siteName) {
		boolean flag = false;
		try {

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myActivitySiteDashboardPanelXpath);
			UIHelper.highlightElement(driver, myActivitySiteDashboardPanelXpath);

			List<WebElement> myActivityList = UIHelper.findListOfElementsbyXpath(myActivitySiteDashboardListXpath,
					driver);
			System.out.println("MY ACTIVITY LIST SIZE : " + myActivityList.size());

			for (WebElement webElement : myActivityList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					String myActivityText = webElement.getText();
					System.out.println("MY ACTIVITY VALUE : " + myActivityText);
					if (myActivityText.contains(userName) && myActivityText.contains(activityDid)
							&& myActivityText.contains(siteName)) {
						UIHelper.highlightElement(driver, webElement);
						flag = true;
						break;
					} else {

						flag = false;
					}
				}
			}

			if (flag) {
				report.updateTestLog("Verify Membership record displayed in 'Site Activitied Dashlet'",
						"Records displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Membership record displayed in 'Site Activitied Dashlet'",
						"Records not displayed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the Async Dashlet Hearder Names
	public ArrayList<String> getAsyncDashletHeaderNames() {
		ArrayList<String> asyncDashletHeaderNamesList = new ArrayList<String>();
		try {
			List<WebElement> asyncDashletTableHeadingsListEle = UIHelper
					.findListOfElementsbyXpath(asyncDashletTableHeadingsXpath, driver);

			for (WebElement ele : asyncDashletTableHeadingsListEle) {
				UIHelper.highlightElement(driver, ele);
				asyncDashletHeaderNamesList.add(ele.getText().trim());
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return asyncDashletHeaderNamesList;
	}

	// Get the Queued status values for published file
	public ArrayList<String> getPublishedFileQueuedStatusFromAsyncDashlet(String itemName) {
		ArrayList<String> queuedStatusList = new ArrayList<String>();
		try {
			String finalXpathForMessageStatus = tempXpathForMessageStatus.replace("CRAFT", itemName);

			List<WebElement> queuedStatusListEle = UIHelper.findListOfElementsbyXpath(finalXpathForMessageStatus,
					driver);

			for (WebElement ele : queuedStatusListEle) {
				UIHelper.highlightElement(driver, ele);
				queuedStatusList.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return queuedStatusList;
	}

	// Check User able to see only the first 10 status of the process
	public boolean checkEntriesInAsyncStatusForEPSDashlet() {
		boolean isDisplayedFirstTenEntries = false;
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, folderLableInfoXpath)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, folderLableInfoXpath));
				isDisplayedFirstTenEntries = true;
			} else {
				isDisplayedFirstTenEntries = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFirstTenEntries;
	}

	// Click on More Info Link under Async Status for EPS Dashlet
	public void clickOnMoreInfoLinkUnderAsyncDashlet() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, moreInfoXpath)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, moreInfoXpath));
				UIHelper.click(driver, moreInfoXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, detailedAsyncSatusForEPSHeaderXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check User able to see the page navigation
	public boolean checkPageNavigation() {
		boolean isDisplayedPageNavigation = false;
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, paginatorXapth)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, paginatorXapth));
				isDisplayedPageNavigation = true;
			} else {
				isDisplayedPageNavigation = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedPageNavigation;
	}

	// Check User able to navigate to different pages
	public boolean checkUserNavigationToDifferentPages() {
		boolean isUserNavigatedToDifferentPages = false;
		try {
			boolean isDisplayedNextPage = false;
			boolean isDisplayedPreviousPage = false;

			if (UIHelper.checkForAnElementbyXpath(driver, paginationNextBtnXpath)) {
				UIHelper.click(driver, paginationNextBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, paginateBtnCurreItemXpath);
				UIHelper.waitFor(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, paginateBtnCurreItemXpath)) {
					isDisplayedNextPage = true;

					if (UIHelper.checkForAnElementbyXpath(driver, paginationPreviousBtnXpath)) {
						UIHelper.click(driver, paginationPreviousBtnXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, paginationPreviousBtnDisabledXpath);
						UIHelper.waitFor(driver);
						if (UIHelper.checkForAnElementbyXpath(driver, paginationPreviousBtnDisabledXpath)) {
							isDisplayedPreviousPage = true;
						} else {
							isDisplayedPreviousPage = false;
						}
					}
				} else {
					isDisplayedNextPage = false;
				}
			}

			if (isDisplayedNextPage && isDisplayedPreviousPage) {
				isUserNavigatedToDifferentPages = true;
			} else {
				isUserNavigatedToDifferentPages = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isUserNavigatedToDifferentPages;
	}

	// Perform Sort in Async Status for EPS and MediaTransform
	public boolean performSortOperationForAsyncStatusHeaders(String sortBy) {
		boolean isPerformedSortOperation = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, firstRowValXpathInAsyncStatusDashlet);
			UIHelper.waitFor(driver);
			String getFirstrowValue = UIHelper.getTextFromWebElement(driver, firstRowValXpathInAsyncStatusDashlet);
			List<WebElement> asyncDashletHeaderNamesListEle = UIHelper
					.findListOfElementsbyXpath(asyncDetailsPageTableHeaderNamesXpath, driver);

			for (WebElement ele : asyncDashletHeaderNamesListEle) {
				if (ele.getText().trim().equalsIgnoreCase(sortBy)) {
					UIHelper.highlightElement(driver, ele);
					ele.click();
					UIHelper.waitFor(driver);
					ele.click();
					UIHelper.waitForVisibilityOfEleByXpath(driver, firstRowValXpathInAsyncStatusDashlet);
					String getUpdatedFirstrowValue = UIHelper.getTextFromWebElement(driver,
							firstRowValXpathInAsyncStatusDashlet);
					if (!getUpdatedFirstrowValue.equalsIgnoreCase(getFirstrowValue)
							|| getUpdatedFirstrowValue.equalsIgnoreCase(getFirstrowValue)) {
						isPerformedSortOperation = true;
					} else {
						isPerformedSortOperation = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPerformedSortOperation;
	}

	/**
	 * @author 412766
	 * @param visibilityType
	 * @return
	 */
	public boolean isHeaderVisibilityAvailable(String visibilityType) {
		boolean flag = false;
		try {
			String finalHeaderVisibilityXpath = tempHeaderVisibilityXpath.replace("CRAFT", visibilityType);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalHeaderVisibilityXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalHeaderVisibilityXpath))) {
				UIHelper.highlightElement(driver, finalHeaderVisibilityXpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify the site visibility in Site Dashboard",
					"Verify the site visibility in Site Dashboard Failed", Status.FAIL);
		}
		return flag;
	}

	// Get first three activities from 'My Activities' Dashlet
	public ArrayList<String> getFirstThreeActivities() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteActivitiesDashletHeaderXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, siteActivitiesDashletHeaderXpath)) {

				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, siteActivitiesDashletHeaderXpath));

				List<WebElement> firstThreeActivitiesEleList = UIHelper
						.findListOfElementsbyXpath(firstThreeActivitiesElXpath, driver);
				for (WebElement ele : firstThreeActivitiesEleList) {
					siteActivitiesList.add(ele.getText().trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return siteActivitiesList;
	}

	// Check 'Update Details' button in Program Dashlet
	public boolean checkUpdateDetailsBtnInProgramDashlet() {
		boolean isDisplayedUpdateDetailsBtn = false;
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, updateDetailsXpath)) {
				isDisplayedUpdateDetailsBtn = true;
			} else {
				isDisplayedUpdateDetailsBtn = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedUpdateDetailsBtn;
	}

	// Check UI Of Database Search EPM Dashlet
	public void checkUIOfDatabaseSearchEPMDashlet(ArrayList<String> inputList) {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, epmSearchFindBtnXpath)
					&& UIHelper.checkForAnElementbyXpath(driver, epmSearchInputBoxXpath)
					&& UIHelper.checkForAnElementbyXpath(driver, epmSearchEmptyBtnXpath)
					&& UIHelper.checkForAnElementbyXpath(driver, toDateXpath)
					&& UIHelper.checkForAnElementbyXpath(driver, fromDateXpath)) {
				UIHelper.highlightElement(driver, fromDateXpath);
				UIHelper.highlightElement(driver, toDateXpath);
				UIHelper.highlightElement(driver, epmSearchEmptyBtnXpath);
				UIHelper.highlightElement(driver, epmSearchInputBoxXpath);
				UIHelper.highlightElement(driver, epmSearchFindBtnXpath);

				for (String field : inputList) {
					String finalfieldXpath = fieldXpath.replace("CRAFT", field);
					if (UIHelper.checkForAnElementbyXpath(driver, finalfieldXpath)) {
						UIHelper.highlightElement(driver, finalfieldXpath);
					} else {
						report.updateTestLog("Verify UI of 'Database Search - EPM'",
								"Verify UI of 'Database Search - EPM'" + " failed", Status.FAIL);
					}

				}
				report.updateTestLog("Verify UI of 'Database Search - EPM'",
						"Verify UI of 'Database Search - EPM'" + " successfully", Status.PASS);

			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void selectSiteContentInSiteActivityDashlet() {
		try {
			UIHelper.waitFor(driver);
			String activityType = dataTable.getData("MyActivities", "MyActivityDropDownValue");
			UIHelper.waitForVisibilityOfEleByXpath(driver, SitecontentDropdown);
			UIHelper.highlightElement(driver, SitecontentDropdown);
			UIHelper.click(driver, SitecontentDropdown);

			UIHelper.waitForPageToLoad(driver);
			String finalXpath = SitecontentDropdownValues.replace("CRAFT", activityType);
			UIHelper.highlightElement(driver, finalXpath);
			UIHelper.click(driver, finalXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean isSiteContentDashletAdded() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitecontentDashletHeaderXpath);
			if (UIHelper
					.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, sitecontentDashletHeaderXpath))) {
				UIHelper.highlightElement(driver, sitecontentDashletHeaderXpath);
				UIHelper.waitFor(driver);
				if (UIHelper.checkForAnElementbyXpath(driver, alfrescoGlobalImgXpath)) {
					WebElement alfrescoGlobalImgEle = UIHelper.findAnElementbyXpath(driver, alfrescoGlobalImgXpath);

					UIHelper.scrollToAnElement(alfrescoGlobalImgEle);
					UIHelper.waitFor(driver);
				}
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// Click on help icon in the dashlet passing the dashlet name
	public void clickOnHelpIconInDashlet(String dashletName) {
		String finalHelpIconXpath=tempHelpIconXpath.replace("CRAFT", dashletName);
		try {
			UIHelper.waitFor(driver);
			UIHelper.javascriptScrollToAnElement(driver, UIHelper.findAnElementbyXpath(driver, finalHelpIconXpath));
			UIHelper.highlightElement(driver, finalHelpIconXpath);
			UIHelper.click(driver, finalHelpIconXpath);
			report.updateTestLog("Click on Help button in Dashlet","Click on Help button in Dashlet successfull",Status.DONE);
		}catch(Exception e) {
			report.updateTestLog("Click on Help button in Dashlet","Click on Help button in Dashlet failed",Status.FAIL);
		}
		
		
	}
	
	// Get help content passing the dashlet name
	public String getHelpContent(String dashletName) {
		String content="";
		try {
			clickOnHelpIconInDashlet(dashletName);
			UIHelper.waitFor(driver);
			content=UIHelper.getTextFromWebElement(driver, helpTextXpath);
			report.updateTestLog("Get Help message","Help message retrieved successfully",Status.DONE);
		}catch(Exception e) {
			report.updateTestLog("Get Help message","Help message retrieval failed",Status.FAIL);
		}
		System.out.println(content);		
		return content;		
	}
	
	
	// Delete all the relationship created for that site from relationship dashlet
	public void deleteAllRelationship() {
		List<WebElement> listRealtionship = new ArrayList<WebElement>();
		try {
			UIHelper.waitFor(driver);
			listRealtionship = UIHelper.findListOfElementsbyXpath(deleteRelationshipXpath, driver);
			for (WebElement webElement : listRealtionship) {
				UIHelper.highlightElement(driver, webElement);
				UIHelper.clickanElement(webElement);
				UIHelper.waitFor(driver);
				docDetailsPg.clickOnYesButtonInDeleteRelationship();
				UIHelper.waitFor(driver);
				}
			report.updateTestLog("Delete relationship in site Dashlet","Delete relationship successfull",Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Delete relationship in site Dashlet","Delete relationship failed",Status.FAIL);
		}
	}
	
}
