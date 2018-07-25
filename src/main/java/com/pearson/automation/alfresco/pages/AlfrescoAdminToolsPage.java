package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pearson.automation.utils.DateUtil;
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
public class AlfrescoAdminToolsPage extends ReusableLibrary {

	private String groupMgmtXpath = ".//*[@title='Group Management']";
	private String groupBrowseBtnXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-browse-button-button']";
	private String groupBrowseBtnAsGrpAdminXpath = ".//*[@id='page_x002e_ctool_x002e_group-admin-console_x0023_default-browse-button-button']";
	private String addgroupXpath = ".//*[@title='New Group']";
	private String groupIdentifierXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-shortname']";
	private String groupIdentifierAsGrpAdminXpath = ".//*[@id='page_x002e_ctool_x002e_group-admin-console_x0023_default-create-shortname']";
	private String groupNameXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-displayname']";
	private String groupNameAsGrpAdminXpath = ".//*[@id='page_x002e_ctool_x002e_group-admin-console_x0023_default-create-displayname']";
	private String createGroupBtn = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-creategroup-ok-button-button']";
	private String createGroupAsGrpAdminBtn = ".//*[@id='page_x002e_ctool_x002e_group-admin-console_x0023_default-creategroup-ok-button-button']";
	private String groupListXpath = ".//*[@class='yui-carousel-element']//li//a";
	private String groupListXpathUserAdmin = ".//*[@class='yui-carousel-element']//li//a/span[1]";
	public String tempeditgpXpath = ".//*[@class='yui-carousel-element']//li//a/span[text()='CRAFT']";
	private String addUserIconXpath = ".//*[@title='Add User']";
	private String peopleFinderXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-peoplefinder-search-text']";
	public ArrayList<String> groupList = new ArrayList<String>();
	private String grouptitleXpath = ".//*[@class='title']//label[text()='Groups']";
	private String adminToolsTitleXpath = ".//*[@id='HEADER_TITLE']";

	/*** Search User ***/
	private String searchUserXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-text']";
	private String searchUserBtnXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button']";
	private String searchUserResXpath = ".//*[@class='search-main']//*[@class='yui-dt-data']//td[3]//div";
	private String userPageXpath = ".//*[@title='User Management']";

	/*** Search Group ***/
	private String searchButtonXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-peoplefinder-search-button-button']";
	private String groupFinderXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-text']";
	private String groupFinderGrpAdminXpath = ".//*[@id='page_x002e_ctool_x002e_group-admin-console_x0023_default-search-text']";
	private String searchGroupBtnXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button']";
	private String searchGroupBtnAsGrpAdminXpath = ".//*[@id='page_x002e_ctool_x002e_group-admin-console_x0023_default-search-button-button']";
	private String displayNameXpath = ".//*[@class='yui-dt-data']/tr[1]/td[2]/div";

	/*** Delete Group ***/
	private String deleteGpIconXpath = ".//*[@class='yui-dt-data']/tr[1]/td[3]/div/a[@class='delete']";
	private String deleteAlertMsgXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-deletegroupdialog_c']";
	private String deleteAlertBtnXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-remove-button-button']";
	private String addButtonXpath = ".//button[text()='Add ']";
	private String groupDOMXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search']";
	private String groupResultXpath = ".//*[text()='CRAFT']";

	/*** Edit Group ***/
	private String editGpIconXpath = ".//*[@class='yui-dt-data']/tr[1]/td[3]/div/a[@class='update']";
	private String UpdateGpXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-displayname']";
	private String saveBtnXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-updategroup-save-button-button']";

	/*** Add Sub Group ***/
	private String addSubGpIconXpath = ".//*[@class='groups-addgroup-button']";
	private String subGpFinderXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-groupfinder-search-text']";
	private String subGpBtnXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-groupfinder-group-search-button-button']";
	private String subGpListXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-groupfinder-results']//table//tr//td[2]//h3";
	private String subGpXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-groupfinder-results']//table//tr//td[2]//h3[text()='CRAFT']";
	private String addSubGpBtnXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-groupfinder-results']//table//tr//td[2]//h3[text()='CRAFT']//ancestor::tr//button";
	public ArrayList<String> subgroupList = new ArrayList<String>();

	/*** Create Subgroup ***/
	private String createSubGpIconXpath = ".//*[@title='New Subgroup']";

	/*** Transformation Server ***/
	private String transServerXpath = ".//*[@id='page_x002e_tools_x002e_admin-console_x0023_default-body']/ul[2]/li/span/a";
	private String transServerURLXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-undefined-alf-id0-form-fields']//a";
	private String transtitleXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-view']/h1";

	/*** Tag Manager ***/
	private String tagMgnXpath = ".//*[@title='Tag Manager']";
	private String tagSearchXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-text']";
	private String tagSearchBtnXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button']";
	private String tagNameListXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-tags']//tr//td[1]//a";
	private String addedtagXpath = ".//*[contains(@id,'prop_cm_taggable-cntrl-currentValueDisplay')]//div";
	private String tagMgnDOMXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-tags']";
	private String saveTagXpath = ".//*[contains(@id,'form-submit-button')]";
	/*** Edit Tag ***/
	private String tempTagEditXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-tags']//tr//td[1]//a//b[text()='CRAFT']//ancestor::tr//a[@title='Edit tag']";
	private String edittagNameXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-edit-tag-name']";
	private String edittagBtnXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-edit-tag-ok-button']";
	private String edittagMsgXpath = ".//*[@class='message']";
	public String tempTagXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-tags']//tr//td[1]//a//b[text()='CRAFT']";

	/*** Delete Tag ***/
	private String tempTagDeleteXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-tags']//tr//td[1]//a//b[text()='CRAFT']//ancestor::tr//a[@title='Delete tag']";
	private String deleteTagNameXpath = ".//*[@id='prompt_c']//button[text()='Delete']";

	/*** Create Tag ***/
	private String editPropertyXpath = ".//*[@title='Edit Properties']";
	private String selectTagXpath = ".//*[contains(@id,'prop_cm_taggable-cntrl-itemGroupActions')]//button";
	private String AddTagTextXpath = ".//*[@class='create-new-input']";
	private String AddTagIconXpath = ".//*[contains(@id,'prop_cm_taggable-cntrl-picker-results')]//h3[text()='CRAFT']//ancestor::tr//a";
	private String AddTagBtnXpath = ".//*[contains(@id,'prop_cm_taggable-cntrl-ok-button')]";
	private String AddTagXpath = ".//*[@class='createNewIcon']";
	private String Loading = ".//tbody[@class='yui-dt-message']//*[text()='Loading...']";

	/*** Create Category ***/
	private String categoryXpath = ".//*[@title='Category Manager']";
	private String categorytitleXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-list']/div[1]/div";
	private String categoryRootXpath = ".//*[contains(@id,'default-category-manager')]//span[text()='Category Root']";
	private String mainPageXpath = ".//*[@id='yui-main']";
	private String bodyPageXpath = ".//*[@id='bd']";
	private String addCatCancelXpath = ".//*[contains(@id,'default_prop_cm_categories-cntrl-cancel')]//button";
	private String categoryDOMXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-category-manager']";
	private String categoryAddIconXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-category-manager']//span[text()='Category Root']//*[@title='Add Category']";
	private String categoryAddXpath = ".//*[@id='userInput_c']//input";
	private String categoryAddBtnXpath = ".//*[@id='userInput_c']//button[text()='OK']";
	public String categoryNameXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-category-manager']//span[text()='CRAFT']";
	private String selectCategoryXpath = ".//*[contains(@id,'default_prop_cm_categories-cntrl-itemGroupActions')]//button";
	private String addCatokXpath = ".//*[@id='template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cm_categories-cntrl-ok-button']";
	private String addCategoryXpath = "//*[contains(@id,'default_prop_cm_categories-cntrl-picker-results')]//td[2]//h3//a[contains(text(),'CRAFT')]//ancestor::tr//td[3]//a";
	private String categoryResXpath = ".//*[contains(@id,'default_prop_cm_categories-cntrl-picker-results')]";

	/*** Edit Category ***/
	private String categoryEditIconXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-category-manager']//span[text()='CRAFT']//*[@title='Edit Category']";
	private String tempCategoryEditXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-category-manager']//span[text()='CRAFT']//ancestor::td//input";
	private String tempCategorySaveXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-category-manager']//span[text()='CRAFT']//ancestor::td//a[1]";
	/*** Delete Category ***/
	private String categoryDeleteIconXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-category-manager']//span[text()='CRAFT']//*[@title='Delete Category']";
	private String categoryDeleteXpath = ".//*[@id='prompt_c']//button[text()='Delete']";

	private String adminOptionsLinkCommmonXpath = ".//*[contains(text(),'AlfrescoAdmin')]";

	private String linkRelationShipHeaderXpath = ".//*[@id='link-head']/b";

	private String newRelationshipButtonXpath = ".//*[@id='link-head']/input";

	private String relationshipSegmentXpath = ".//*[@class='sticky-wrapper']//*[@id='doc3']//*[@id='bd']//*[contains(@id,'page_x002e_ctool_x002e_admin-console')]//*[@id='relationship']";
	private String newRelationshipEditButtonListXpath = ".//*[@class='sticky-wrapper']//*[@id='doc3']//*[@id='bd']//*[contains(@id,'page_x002e_ctool_x002e_admin-console')]//*[@id='relationship']//tr//td[2]//input";

	// private String newRelationShipHeaderXpath = ".//*[contains(text(),'New Relationship')]";
	private String newRelationShipHeaderXpath ="//*[@class='yui-panel-container yui-dialog shadow']//div[@class='hd']";
	//private String newRelationshipBiDirectionalXpath = ".//*[contains(text(),'Bi-directional')]";
	private String newRelationshipBiDirectionalXpath = "//*[@class='yui-panel-container yui-dialog shadow']//*[contains(@for,'type-bidirectional')]";
	//private String newRelationshipLableXpath = ".//*[contains(text(),'Label')]";
	private String newRelationshipLableXpath = "//*[@class='yui-panel-container yui-dialog shadow']//div[contains(@id,'bidirectional-section')]//span[@class='crud-label']";
	//private String newRelationshipParentChildXpath = ".//*[contains(text(),'Parent/Child')]";
	private String newRelationshipParentChildXpath = "//*[@class='yui-panel-container yui-dialog shadow']//*[contains(@for,'type-parentchild')]";
	//private String newRelationshipSourceXpath = ".//*[contains(text(),'Source')]";
	private String newRelationshipSourceXpath = "//*[@class='yui-panel-container yui-dialog shadow']//div[contains(@id,'parentchild-section')]/div[2]/span[@class='crud-label']";
	//private String newRelationshipTargetXpath = ".//*[contains(text(),'Target')]";
	private String newRelationshipTargetXpath = "//*[@class='yui-panel-container yui-dialog shadow']//div[contains(@id,'parentchild-section')]/div[4]/span[@class='crud-label']";
	//private String newRelationshipSaveXpath = ".//*[contains(text(),'Save')]";
	private String newRelationshipSaveXpath ="//*[@class='yui-panel-container yui-dialog shadow']//span[contains(@id,'save-button')]//button";
	//private String newRelationshipCancelXpath = ".//*[contains(text(),'Cancel')]";
	public String newRelationshipCancelXpath = "//*[@class='yui-panel-container yui-dialog shadow']//span[contains(@id,'cancel-button')]//button";
    private String newRelationShipGeneralXpath="//*[@class='yui-panel-container yui-dialog shadow']//*[@class='header-bar']/span";
	private String newRelationShipTypeXpath="//*[@class='yui-panel-container yui-dialog shadow']//*[@class='field-row']/span";
    private String newRelationShipBidirectionalRadioButtonXpath="//*[@class='yui-panel-container yui-dialog shadow']//div[contains(@id,'bidirectional-section')]//input[@type='radio']";
    private String newRelationShipParentChildRadioButtonXpath="//*[@class='yui-panel-container yui-dialog shadow']//div[contains(@id,'parentchild-section')]//input[@type='radio']";
	private String newRelationshipLableInputXpath = ".//*[@id='newLabel']";
	private String newRelationshipSourceInputXpath = ".//*[@id='newSource']";
	private String newRelationshipTargetInputXpath = ".//*[@id='newTarget']";
     
	private String newRelationshipUpdateLableInputXpath = ".//*[@id='label']";
	private String newRelationshipUpdateSourceInputXpath = ".//*[@id='source']";
	private String newRelationshipUpdateTargetInputXpath = ".//*[@id='target']";

	private String newRelationshipListXpath = ".//*[@class='sticky-wrapper']//*[@id='doc3']//*[@id='bd']//*[contains(@id,'page_x002e_ctool_x002e_admin-console')]//*[@id='relationship']//tr//td[1]";

	private String popUpMsgXpath = ".//*[@id='message']/div";

	private String applicationToolHeaderXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-options']/div[1]";

	private String adminToolsOptionsSegmentXpath = ".//*[@id='alf-filters']";
	private String adminToolsOptionsListXpath = ".//*[@class='sticky-wrapper']//*[@id='doc3']//*[@id='bd']//*[@id='alfresco-console']//*[@id='alf-filters']//*[contains(@id,'page_x002e_tools_x002e_admin-console')]//li//span";

	private String themeDropDownXpath = ".//*[@id='console-options-theme-menu']";
	private String applyThemeButtonXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-apply-button-button']";
	private String themeSegmentXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-options-form']";
	private static String actualValueInAdminToolsPage = "null";

	private String editRelationShipHeaderXpath = ".//*[contains(text(),'Edit Relationship')]";
	private String editRelationShipGeneralXpath = "//*[@class='yui-panel-container yui-dialog shadow']//*[@class='header-bar']/span";
	//private String editRelationShipGeneralXpath = ".//*[contains(text(),'General:')]";
	private String editRelationShipXpath = ".//*[contains(text(),'Edit')]";
	

	/*** User management ***/
	private String adminOptionsXpath = ".//*[@id='alf-filters']";
	private String adminListXpath = ".//*[contains(@id,'admin-console_x0023_default-body')]/ul/li/span";
	public ArrayList<String> optionList = new ArrayList<String>();
	public ArrayList<String> usrList = new ArrayList<String>();
	private String searchTxtAsUserAdmin = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-search-text']";
	private String searchTxtInUsers = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-text']";
	private String searchBtnAsUserAdmin = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-search-button-button']";
	private String searchBtnInUsers = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button']";
	private String newuserAsUserAdmin = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-newuser-button-button']";
	private String newuserAsAdmin = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-newuser-button-button']";
	private String userListXPath = ".//*[@class='yui-dt-data']//tr//td[2]/div/a";
	private String editUserXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-edituser-button-button']";
	private String deleteUserXPath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-deleteuser-button-button']";
	private String delConfirmXPath = ".//*[@id='yui-gen0-button']";
	private String firstNameXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-create-firstname']";
	private String lastNameXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-create-lastname']";
	private String emailXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-create-email']";
	private String userNameXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-create-username']";
	private String passXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-create-password']";
	private String confirmPassXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-create-verifypassword']";
	private String createBtnXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-createuser-ok-button-button']";
	private String firstNameXPathAdmin = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-firstname']";
	private String lastNameXPathAdmin = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-lastname']";
	private String emailXPathAdmin = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-email']";
	private String userNameXPathAdmin = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-username']";
	private String passXPathAdmin = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-password']";
	private String confirmPassXPathAdmin = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-verifypassword']";
	private String createBtnXPathAdmin = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-createuser-ok-button-button']";
	private String usrProfileXPath = ".//*[@class='title']/span[contains(text(),CRAFT)]";
	private String editUsrXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-edituser-button-button']";
	private String editFNameXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-update-firstname']";
	private String editLNameXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-update-lastname']";
	private String editMailXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-update-email']";
	private String editSaveXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-updateuser-save-button-button']";
	public String titleXpath = ".//*[@class='title']//label";
	private String nodeBrowserXpath = ".//*[@class='tool tools-link']//a[contains(.,'Node')]";
	private String nodeBrowserSearchBoxXpath = ".//*[contains(@id,'admin-console')]//textarea";
	private String nodeRefBtnXpath = ".//*[contains(@id,'default-lang-menu-button-button')]";
	private String nodeRefOptionXpath = ".//*[contains(@class,'yui-button-menu yui-menu-button-menu visible')]//ul//li[contains(.,'noderef')]";
	private String nodeRefSearchBtnXpath = ".//*[contains(@id,'_default-search-button-button')]";
	private String nodeRefNameXpath = ".//*[@class='results yui-dt']//tr//td[contains(@class,'name')]//a[contains(.,'CRAFT')]";
	private String nodeRefHeaderXpath = ".//*[contains(@id,'default-view')]//span[text()='cm:CRAFT']";
	private String nodeRefValueXpath = ".//*[contains(@class,'node-properties')]//table//tr//td[contains(.,'CRAFT')]//ancestor::tr//td[contains(@class,'values')]//div//div";
	private String nodeRefAspectsXpath = ".//*[@class='node-aspects list']";
	
	private String tempXpathForChildNameLinkUnderChildren = ".//*[@class='yui-dt-data']//tr//td//a[contains(.,'CRAFT')]";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoAdminToolsPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Search group page
	public void searchGroup(String Group) {
		try {
			UIHelper.click(driver, groupMgmtXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnXpath);
			UIHelper.highlightElement(driver, groupFinderXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, groupFinderXpath, Group);
			UIHelper.click(driver, searchGroupBtnXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, groupDOMXpath).getText().contains(Group)) {
				report.updateTestLog("Search Group",
						"Searched Group found successfully." + "<br /><b> Group Name : </b>" + Group, Status.DONE);
			} else {
				report.updateTestLog("Search  Group",
						"Searched Group not found." + "<br /><b> Group Name : </b>" + Group, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Search  Group", "Searched Group not found." + "<br /><b> Group Name : </b>" + Group,
					Status.FAIL);
		}
	}

	// Search User
	public void searchUser(String User) {
		try {
			UIHelper.click(driver, userPageXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchUserXpath);
			UIHelper.highlightElement(driver, searchUserXpath);
			UIHelper.findAnElementbyXpath(driver, searchUserXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, searchUserXpath, User);
			UIHelper.click(driver, searchUserBtnXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, searchUserResXpath).getText().contains(User)) {
				report.updateTestLog("Search User",
						"Searched User found successfully." + "<br /><b> User Name : </b>" + User, Status.DONE);
			} else {
				report.updateTestLog("Search  User", "Searched User not found." + "<br /><b> User Name : </b>" + User,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Search  User", "Searched User not found." + "<br /><b> User Name : </b>" + User,
					Status.FAIL);
		}
	}

	// Search group page
	public Boolean searchGpAndDelete(String Group) {
		try {
			UIHelper.click(driver, groupMgmtXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnXpath);
			UIHelper.highlightElement(driver, groupFinderXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, groupFinderXpath, Group);
			UIHelper.click(driver, searchGroupBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.findAnElementbyXpath(driver, groupFinderXpath).clear();
			if (UIHelper.findAnElementbyXpath(driver, groupDOMXpath).getText().contains(Group)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			return false;

		}
	}

	// Browse group page
	public void BrowseGroup(String Group) {
		try {
			UIHelper.click(driver, groupMgmtXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnXpath);
			UIHelper.highlightElement(driver, groupFinderXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, groupFinderXpath, Group);
			UIHelper.click(driver, groupBrowseBtnXpath);
			UIHelper.waitFor(driver);
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");

			if (UIHelper.findAnElementbyXpath(driver, groupListXpath).getText().contains(Group)) {
				report.updateTestLog("Browsed Group",
						"Browsed Group found successfully." + "<br /><b> Expected Group Name : </b>" + Group
								+ "<br /><b> Actual Group Name : </b>" + Group + " (" + gpIdentifier + ")",
						Status.PASS);

			} else {
				report.updateTestLog("Browsed  Group",
						"Browsed Group not found." + "<br /><b>Group Name : </b>" + Group, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Browsed  Group", "Browsed Group not found." + "<br /><b>Group Name : </b>" + Group,
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param Group
	 */
	public void browseGroupInUserAdmin(String Group) {
		try {
			UIHelper.click(driver, groupMgmtXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnAsGrpAdminXpath);
			UIHelper.highlightElement(driver, groupFinderGrpAdminXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, groupFinderGrpAdminXpath, Group);
			UIHelper.click(driver, groupBrowseBtnAsGrpAdminXpath);
			UIHelper.waitFor(driver);
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");

			if (UIHelper.findAnElementbyXpath(driver, groupListXpathUserAdmin).getText().contains(Group)) {
				report.updateTestLog("Browsed Group",
						"Browsed Group found successfully." + "<br /><b> Expected Group Name : </b>" + Group
								+ "<br /><b> Actual Group Name : </b>" + Group + " (" + gpIdentifier + ")",
						Status.DONE);

			} else {
				report.updateTestLog("Browsed  Group",
						"Browsed Group not found." + "<br /><b>Group Name : </b>" + Group, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Browsed  Group", "Browsed Group not found." + "<br /><b>Group Name : </b>" + Group,
					Status.FAIL);
		}
	}

	// Edit group page
	public void editGroup() {
		try {
			String editGpName = dataTable.getData("Admin", "EditGroupName");
			String GpName = dataTable.getData("Admin", "GroupName");
			UIHelper.highlightElement(driver, editGpIconXpath);
			UIHelper.click(driver, editGpIconXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, UpdateGpXpath);
			UIHelper.findAnElementbyXpath(driver, UpdateGpXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, UpdateGpXpath, editGpName);
			UIHelper.click(driver, saveBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, displayNameXpath).getText().contains(editGpName)) {
				report.updateTestLog("Edit Group",
						"Group Edited successfully." + "<br /><b> Group Name Before Edit : <b>" + GpName
								+ "<br /><b>Actual Group Name after Edit : <b>"
								+ UIHelper.findAnElementbyXpath(driver, displayNameXpath).getText(),
						Status.DONE);
			} else {
				report.updateTestLog("Edit Group", " Edit Group failed . Group not found:", Status.FAIL);
			}
		} catch (Exception e) {

			report.updateTestLog("Edit Group", " Edit Group failed . Group not found:", Status.FAIL);
		}
	}

	// Delete group page
	public void deleteGroup(String Group) {

		try {

			if (UIHelper.findAnElementbyXpath(driver, deleteGpIconXpath).isDisplayed()) {
				UIHelper.highlightElement(driver, deleteGpIconXpath);
				UIHelper.click(driver, deleteGpIconXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteAlertMsgXpath);
				UIHelper.highlightElement(driver, deleteAlertBtnXpath);
				UIHelper.click(driver, deleteAlertBtnXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Delete  Group",
						"Group Deleted successfully." + "<br /><b> Group Name : </b>" + Group, Status.DONE);
			} else {
				report.updateTestLog("Delete  Group",
						"Group Deleted Failed,Group Not Found." + "<br /><b> Group Name : </b>" + Group, Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Delete  Group",
					"Group Deleted Failed,Group Not Found." + "<br /><b> Group Name : </b>" + Group, Status.FAIL);
		}
	}

	// Transformation page
	public void transformationServer() {
		try {
			UIHelper.click(driver, transServerXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, transServerURLXpath);
			if (UIHelper.findAnElementbyXpath(driver, transServerURLXpath).isDisplayed()) {
				report.updateTestLog("Navigate to Transformation Page ",
						"Navigate to Transformation Page Successful." + "<br /><b> Page Title : </b>"
								+ UIHelper.findAnElementbyXpath(driver, transtitleXpath).getText(),
						Status.DONE);
			} else {
				report.updateTestLog("Navigate to Transformation Page ", "Navigate to Transformation Page Failed.",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Navigate to Transformation Page ", "Navigate to Transformation Page Failed.",
					Status.FAIL);
		}
	}

	// Navigate group page
	public void groupMgmt() {
		try {
			UIHelper.click(driver, groupMgmtXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnXpath);
			if (UIHelper.findAnElementbyXpath(driver, grouptitleXpath).isDisplayed()) {
				report.updateTestLog("Navigate to Group Link",
						"Navigation to Groups page successfully." + "<br /><b>Page title : </b>"
								+ UIHelper.findAnElementbyXpath(driver, grouptitleXpath).getText(),
						Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Navigate to Group Link", "Navigation to Groups page failed.", Status.DONE);
		}
	}

	// Add Subgroup page
	public void createSubGroup() {
		try {
			UIHelper.findandAddElementsToaList(driver, groupListXpath, groupList);
			String GroupName = dataTable.getData("Admin", "GroupName");
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");
			for (String group : groupList) {
				if (group.contains(GroupName)) {
					String finaleditgpXpath = tempeditgpXpath.replace("CRAFT",
							GroupName + " " + "(" + gpIdentifier + ")");
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finaleditgpXpath);
					UIHelper.click(driver, finaleditgpXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, createSubGpIconXpath);
					UIHelper.click(driver, createSubGpIconXpath);
					UIHelper.waitForPageToLoad(driver);
					String subGpName = dataTable.getData("Admin", "SubGroup");
					String subGpIdentifer = dataTable.getData("Admin", "SubGpIdentifier");
					UIHelper.sendKeysToAnElementByXpath(driver, groupIdentifierXpath, subGpIdentifer);
					UIHelper.sendKeysToAnElementByXpath(driver, groupNameXpath, subGpName);
					UIHelper.click(driver, createGroupBtn);
					UIHelper.waitFor(driver);
					report.updateTestLog("Create New Sub Group",
							"Create New Sub Group successfully. SubGroup Name :" + subGpIdentifer, Status.DONE);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void createSubGroupInUserAdmin() {
		try {
			UIHelper.findandAddElementsToaList(driver, groupListXpath, groupList);
			System.out.println("GROUP LIST : " + groupList.toString());
			String GroupName = dataTable.getData("Admin", "GroupName");
			// String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");
			for (String group : groupList) {
				if (group.contains(GroupName)) {
					System.out.println("GROUP NAME : " + group);
					String finaleditgpXpath = tempeditgpXpath.replace("CRAFT", GroupName);
					System.out.println("FINAL EDIT XPATH : " + finaleditgpXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finaleditgpXpath);
					UIHelper.click(driver, finaleditgpXpath);
					// UIHelper.mouseOverandclickanElement(driver,
					// UIHelper.findAnElementbyXpath(driver, finaleditgpXpath));
					UIHelper.waitForVisibilityOfEleByXpath(driver, createSubGpIconXpath);
					UIHelper.click(driver, createSubGpIconXpath);
					UIHelper.waitForPageToLoad(driver);
					String subGpName = dataTable.getData("Admin", "SubGroup");
					String subGpIdentifer = dataTable.getData("Admin", "SubGpIdentifier");
					UIHelper.sendKeysToAnElementByXpath(driver, groupIdentifierAsGrpAdminXpath, subGpIdentifer);
					UIHelper.sendKeysToAnElementByXpath(driver, groupNameAsGrpAdminXpath, subGpName);
					UIHelper.click(driver, createGroupAsGrpAdminBtn);
					UIHelper.waitFor(driver);
					report.updateTestLog("Create New Sub Group",
							"Create New Sub Group successfully. SubGroup Name :" + subGpIdentifer, Status.DONE);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add group page
	public void createGroup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnXpath);
			UIHelper.click(driver, groupBrowseBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addgroupXpath);
			UIHelper.click(driver, addgroupXpath);
			UIHelper.waitForPageToLoad(driver);
			String groupName = dataTable.getData("Admin", "GroupName");
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");
			UIHelper.sendKeysToAnElementByXpath(driver, groupIdentifierXpath, groupName);
			UIHelper.sendKeysToAnElementByXpath(driver, groupNameXpath, gpIdentifier);
			UIHelper.click(driver, createGroupBtn);
			String ActualGpName = tempeditgpXpath.replace("CRAFT", groupName);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, groupListXpath).isDisplayed()) {
				report.updateTestLog("Create New Group",
						"Create New Group successfully." + "<br /><b>Group Name : </b>" + groupName, Status.PASS);
			} else {
				report.updateTestLog("Create New Group", "Create New Group Failed. Group not create :", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Create New Group", "Create New Group Failed. Group not create :", Status.FAIL);
		}
	}

	// Add users to the new group page
	public void addUserToNewGroup() {
		try {
			UIHelper.findandAddElementsToaList(driver, groupListXpath, groupList);
			String GroupName = dataTable.getData("Admin", "GroupName");
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");
			String newUser = dataTable.getData("Admin", "Adduser");
			for (String group : groupList) {
				if (group.contains(GroupName)) {
					String finaleditgpXpath = tempeditgpXpath.replace("CRAFT", group);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finaleditgpXpath);
					UIHelper.click(driver, finaleditgpXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, addUserIconXpath);
					UIHelper.click(driver, addUserIconXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, peopleFinderXpath);
					UIHelper.sendKeysToAnElementByXpath(driver, peopleFinderXpath, newUser);
					UIHelper.waitForVisibilityOfEleByXpath(driver, searchButtonXpath);
					UIHelper.click(driver, searchButtonXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, addButtonXpath);
					UIHelper.click(driver, addButtonXpath);
					UIHelper.waitFor(driver);
				} else {
					report.updateTestLog("Add User to New Group", "Add User to New Group failed.Group not found ",
							Status.FAIL);

				}
				break;
			}
			report.updateTestLog("Add User to New Group",
					"Add User to New Group successfully." + "<br /><b> Add User : </b>" + newUser, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Add User to New Group", "Add User to New Group failed. ", Status.FAIL);

		}

	}

	// Add users to the new group page
	public void addUserToNewGroupwithDiffIden() {
		try {
			UIHelper.findandAddElementsToaList(driver, groupListXpath, groupList);
			String GroupName = dataTable.getData("Admin", "GroupName");
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");
			String newUser = dataTable.getData("Admin", "Adduser");
			for (String group : groupList) {
				if (group.contains(GroupName)) {
					String finaleditgpXpath = tempeditgpXpath.replace("CRAFT", GroupName + " (" + gpIdentifier + ")");
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finaleditgpXpath);
					UIHelper.click(driver, finaleditgpXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, addUserIconXpath);
					UIHelper.click(driver, addUserIconXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, peopleFinderXpath);
					UIHelper.sendKeysToAnElementByXpath(driver, peopleFinderXpath, newUser);
					UIHelper.waitForVisibilityOfEleByXpath(driver, searchButtonXpath);
					UIHelper.click(driver, searchButtonXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, addButtonXpath);
					UIHelper.click(driver, addButtonXpath);
					UIHelper.waitFor(driver);
				}
				break;
			}
			report.updateTestLog("Add User to New Group", "Add User to New Group successfully."
					+ "<br /><b>Group Name : <b>" + GroupName + "<br /><b>Add User : <b>" + newUser, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Add User to New Group", "Add User to New Group failed. Group not found", Status.FAIL);
		}

	}

	// Add sub group to th new group page
	public void addSubGpToGroup() {
		try {
			UIHelper.findandAddElementsToaList(driver, groupListXpath, groupList);
			String GroupName = dataTable.getData("Admin", "GroupName");
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");
			String subGp = dataTable.getData("Admin", "SubGroup");
			for (String group : groupList) {
				if (group.contains(GroupName)) {
					String finaleditgpXpath = tempeditgpXpath.replace("CRAFT",
							GroupName + " " + "(" + gpIdentifier + ")");
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finaleditgpXpath);
					UIHelper.click(driver, finaleditgpXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, addSubGpIconXpath);
					UIHelper.click(driver, addSubGpIconXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, subGpFinderXpath);
					UIHelper.sendKeysToAnElementByXpath(driver, subGpFinderXpath, subGp);
					UIHelper.waitForVisibilityOfEleByXpath(driver, subGpBtnXpath);
					UIHelper.click(driver, subGpBtnXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, subGpListXpath);
					UIHelper.findandAddElementsToaList(driver, subGpListXpath, subgroupList);
					for (String subgroup : subgroupList) {
						if (subgroup.contains(subGp)) {
							String finaladdSubGpBtnXpath = addSubGpBtnXpath.replace("CRAFT", subGp);
							UIHelper.waitFor(driver);
							UIHelper.waitForVisibilityOfEleByXpath(driver, finaladdSubGpBtnXpath);
							UIHelper.click(driver, finaladdSubGpBtnXpath);
							UIHelper.waitFor(driver);
						} else {
							report.updateTestLog("Add Sub Group to Group",
									"Add Sub Group to  Group failed.SubGroup not found", Status.FAIL);
						}
						break;
					}
				}
				break;
			}
			report.updateTestLog("Add Sub Group to  Group",
					"Add Sub Group to  Group successfully." + "<br /><b>Add Sub Group : </b>" + subGp, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Add Sub Group to  Group", "Add Sub Group to  Group failed.Group not found",
					Status.FAIL);
		}

	}

	// Navigate Tag Manager
	public void tagMgmt() {
		try {
			UIHelper.click(driver, tagMgnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, tagSearchXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Search Tag Manager
	public void searchTagMgmt() {
		try {
			String tagName = dataTable.getData("Admin", "TagName");
			UIHelper.highlightElement(driver, tagSearchXpath);
			UIHelper.findAnElementbyXpath(driver, tagSearchXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, tagSearchXpath, tagName);
			UIHelper.click(driver, tagSearchBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, tagNameListXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, tagNameListXpath).getText().contains(tagName)) {
				report.updateTestLog("Search Tag",
						"Searched Tag found successfully." + "<br /><b>Search Tag Name : </b>" + tagName, Status.DONE);
			} else {
				report.updateTestLog("Search  Tag", "Searched Tag not found.", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Search  Tag", "Searched Tag not found.", Status.FAIL);
		}
	}

	// Search Tag and delete Manager
	public Boolean searchTagAndDelete(String tagName) {
		try {
			UIHelper.highlightElement(driver, tagSearchXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, tagSearchXpath, tagName);
			UIHelper.click(driver, tagSearchBtnXpath);
			UIHelper.waitFor(driver);
			String finalTagEditXpath = tempTagXpath.replace("CRAFT", tagName);
			if (UIHelper.findAnElementbyXpath(driver, finalTagEditXpath).getText().contains(tagName)) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// Edit Tag Manager
	public void editTagMgmt() {
		try {
			UIHelper.findandAddElementsToaList(driver, tagNameListXpath, groupList);
			String tagName = dataTable.getData("Admin", "TagName");
			String editTag = dataTable.getData("Admin", "EditTagName");
			for (String tag : groupList) {
				if (tag.contains(tagName)) {
					String finalTagEditXpath = tempTagEditXpath.replace("CRAFT", tag);
					UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, finalTagEditXpath));
					UIHelper.click(driver, finalTagEditXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, edittagNameXpath);
					UIHelper.findAnElementbyXpath(driver, edittagNameXpath).clear();
					UIHelper.sendKeysToAnElementByXpath(driver, edittagNameXpath, editTag);
					UIHelper.click(driver, edittagBtnXpath);

					report.updateTestLog("Edit Tag",
							"Edit Tag found successfully." + "<br /><b> Edited Tag Name : </b>" + editTag, Status.DONE);
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Edit Tag", "Edit Tag found failed.Tag not found", Status.FAIL);
		}
	}

	// Delete Tag Manager
	public void deleteTagMgmt() {
		try {
			UIHelper.findandAddElementsToaList(driver, tagNameListXpath, groupList);
			String tagName = dataTable.getData("Admin", "TagName");
			for (String tag : groupList) {
				if (tag.contains(tagName)) {
					String finalTagDeleteXpath = tempTagDeleteXpath.replace("CRAFT", tag);
					UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, finalTagDeleteXpath));
					UIHelper.click(driver, finalTagDeleteXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, deleteTagNameXpath);
					UIHelper.click(driver, deleteTagNameXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					report.updateTestLog("Delete Tag", "Delete Tag successfully." + "<br /><b> Tag Name :" + tagName,
							Status.DONE);
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Delete Tag", "Delete Tag failed.", Status.FAIL);
		}
	}

	// Create Tag Manager
	public void createTagMgmt() {
		String tagName = dataTable.getData("Admin", "TagName");
		try {
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// editPropertyXpath);
			// UIHelper.click(driver, editPropertyXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectTagXpath);
			UIHelper.click(driver, selectTagXpath);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, Loading);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, AddTagTextXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, AddTagTextXpath, tagName);
			UIHelper.waitFor(driver);
			String finalAddTagIconXpath = AddTagIconXpath.replace("CRAFT", tagName);
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// finalAddTagIconXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, finalAddTagIconXpath)) {
				UIHelper.click(driver, finalAddTagIconXpath);
				UIHelper.waitFor(driver);
			} else {
				System.out.println("In");
				UIHelper.waitForVisibilityOfEleByXpath(driver, AddTagXpath);
				UIHelper.click(driver, AddTagXpath);
			}
			System.out.println("Insf");
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// finalAddTagIconXpath);

			UIHelper.click(driver, AddTagBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addedtagXpath);
			if (UIHelper.findAnElementbyXpath(driver, addedtagXpath).getText().contains(tagName)) {
				System.out.println("Insfsdsd");
				report.updateTestLog("Create Tag", "Create Tag successfully." + "<br /><b> Tag Name : </b>" + tagName,
						Status.DONE);
			} else {
				System.out.println("Insfsdfgsdrgsdgfsd");
				report.updateTestLog("Create Tag", "Create Tag failed.", Status.FAIL);
			}

		} catch (Exception e) {
			System.out.println("Insfsdfgsdrg");
			report.updateTestLog("Create Tag", "Create Tag failed.", Status.FAIL);
		}
	}

	// Navigate Category Manager
	public void CategoryMgmt() {
		try {
			UIHelper.click(driver, categoryXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, categoryDOMXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, categorytitleXpath).isDisplayed()) {
				report.updateTestLog("Navigate to Category Mgmt",
						"Successfully navigate to Category Mgmt" + "<br /><b> Page Title : </b>"
								+ UIHelper.findAnElementbyXpath(driver, categorytitleXpath).getText(),
						Status.DONE);
			} else {
				report.updateTestLog("Navigate to Category Mgmt", "Navigate to Category Mgmt failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Navigate to Category Mgmt", "Navigate to Category Mgmt failed", Status.FAIL);
		}
	}

	// Check New Category Manager
	public void checkNewCatMgmt() {
		try {
			String categoryName = dataTable.getData("Admin", "CategoryName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertyXpath);
			UIHelper.click(driver, editPropertyXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.click(driver, selectCategoryXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, addCatCancelXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Check New Category",
					"New category displayed successfully." + "<br /><b>Tag Name : </b>" + categoryName, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Check New Category", "New category failed to display. Category Not found",
					Status.FAIL);
		}
	}

	// Check New Category Manager
	public void addNewCatMgmt() {
		try {
			String categoryName = dataTable.getData("Admin", "CategoryName");
			/*
			 * UIHelper.waitForPageToLoad(driver); UIHelper.waitFor(driver);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * editPropertyXpath); UIHelper.click(driver, editPropertyXpath);
			 */
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, selectCategoryXpath);

			String finaladdCategoryXpath = addCategoryXpath.replace("CRAFT", categoryName);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finaladdCategoryXpath);
			UIHelper.click(driver, finaladdCategoryXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, addCatokXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Add Category",
					"Category added successfully." + "<br /><b>Tag Name : </b>" + categoryName, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Add Category", "Category failed to add.Category Not found", Status.FAIL);
		}
	}

	// Add Category Manager
	public void addCategoryMgmt() {
		try {
			String categoryName = dataTable.getData("Admin", "CategoryName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, bodyPageXpath);
			WebElement bodyPageEle = UIHelper.findAnElementbyXpath(driver, bodyPageXpath);
			UIHelper.highlightElement(driver, bodyPageEle);
			UIHelper.mouseOveranElement(driver, bodyPageEle);

			WebElement mainPageEle = UIHelper.findAnElementbyXpath(driver, mainPageXpath);
			UIHelper.highlightElement(driver, mainPageEle);
			UIHelper.mouseOveranElement(driver, mainPageEle);

			WebElement categoryRootEle = UIHelper.findAnElementbyXpath(driver, categoryRootXpath);

			if (categoryRootEle.isDisplayed()) {
				UIHelper.highlightElement(driver, categoryRootEle);
				UIHelper.mouseOveranElement(driver, categoryRootEle);

				UIHelper.waitFor(driver);
				UIHelper.click(driver, categoryAddIconXpath);
				UIHelper.waitFor(driver);
				UIHelper.findAnElementbyXpath(driver, categoryAddXpath).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, categoryAddXpath, categoryName);
				UIHelper.click(driver, categoryAddBtnXpath);

				report.updateTestLog("Add Category", "Category added successfully. Category Name :" + categoryName,
						Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Add Category", "Category failed to add. Root Category Not found", Status.FAIL);
		}
	}

	// Edit Category Manager
	public void editCategoryMgmt() {
		try {
			String categoryName = dataTable.getData("Admin", "CategoryName");
			String editCatName = dataTable.getData("Admin", "EditCategoryName");
			String finalCategoryNameXpath = categoryNameXpath.replace("CRAFT", categoryName);
			if (UIHelper.findAnElementbyXpath(driver, finalCategoryNameXpath).isDisplayed()) {
				String finalCategoryEditIconXpath = categoryEditIconXpath.replace("CRAFT", categoryName);
				String finalCategoryEditXpath = tempCategoryEditXpath.replace("CRAFT", categoryName);
				String finalCategorySaveXpath = tempCategorySaveXpath.replace("CRAFT", categoryName);
				UIHelper.mouseOverandElementdoubleClick(driver,
						UIHelper.findAnElementbyXpath(driver, finalCategoryNameXpath));
				UIHelper.waitFor(driver);
				UIHelper.click(driver, finalCategoryEditIconXpath);
				UIHelper.waitFor(driver);
				UIHelper.findAnElementbyXpath(driver, finalCategoryEditXpath).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, finalCategoryEditXpath, editCatName);
				UIHelper.click(driver, finalCategorySaveXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Edit Category",
						"Edit Category successfully." + "<br /><b>Category Name : </b>" + categoryName, Status.DONE);
			} else {
				report.updateTestLog("Edit Category", "Edit Category Failed, Category Not found.", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Edit Category", "Edit Category Failed, Category Not found.", Status.FAIL);
		}
	}

	// Delete Category Manager
	public void deleteCategoryMgmt() {
		try {

			String categoryName = dataTable.getData("Admin", "CategoryName");
			String finalCategoryNameXpath = categoryNameXpath.replace("CRAFT", categoryName);
			if (UIHelper.findAnElementbyXpath(driver, finalCategoryNameXpath).isDisplayed()) {
				String finalCategoryDelIconXpath = categoryDeleteIconXpath.replace("CRAFT", categoryName);
				UIHelper.mouseOverandElementdoubleClick(driver,
						UIHelper.findAnElementbyXpath(driver, finalCategoryNameXpath));
				UIHelper.waitFor(driver);
				UIHelper.click(driver, finalCategoryDelIconXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, categoryDeleteXpath);
				UIHelper.click(driver, categoryDeleteXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Delete Category",
						"Delete Category successfully.<br /><b> Category Name : </b>" + categoryName, Status.DONE);
			} else {
				report.updateTestLog("Delete Category", "Delete Category successfully,Category Not found.",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Delete Category", "Delete Category failed,Category Not found.", Status.FAIL);
		}
	}

	// search Category Manager
	public boolean searchCategoryMgmt() {
		try {

			String categoryName = dataTable.getData("Admin", "CategoryName");
			String finalCategoryNameXpath = categoryNameXpath.replace("CRAFT", categoryName);
			if (UIHelper.findAnElementbyXpath(driver, finalCategoryNameXpath).isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to check the 'Admin Tools' options menu available or not
	 * 
	 * @param optionsLinkName
	 *            - It contains Menu option link name
	 * @return boolean
	 * @author 412766
	 */
	public boolean isAdminOtionsLinkAvailable(String optionsLinkName) {
		boolean flag = false;
		try {
			adminOptionsLinkCommmonXpath = adminOptionsLinkCommmonXpath.replace("AlfrescoAdmin", optionsLinkName);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, adminOptionsLinkCommmonXpath))) {
				UIHelper.highlightElement(driver, adminOptionsLinkCommmonXpath);
				actualValueInAdminToolsPage = UIHelper.getTextFromWebElement(driver, adminOptionsLinkCommmonXpath);
				UIHelper.waitFor(driver);
				flag = true;
			} else {
				actualValueInAdminToolsPage = "No option available.";
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Method to navigate the 'Admin Tools' options Menu Link
	 * 
	 * @param optionsLinkName
	 *            - It contains the Menu link option name.
	 * @author 412766
	 */
	public void navigateToAdminToolsOptionsLink(String optionsLinkName) {
		try {
			adminOptionsLinkCommmonXpath = adminOptionsLinkCommmonXpath.replace("AlfrescoAdmin", optionsLinkName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, adminOptionsLinkCommmonXpath);
			UIHelper.highlightElement(driver, adminOptionsLinkCommmonXpath);
			UIHelper.click(driver, adminOptionsLinkCommmonXpath);
			UIHelper.waitForPageToLoad(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, adminToolsTitleXpath))) {
				report.updateTestLog("Navigate to '" + optionsLinkName + "' option in Admin Tools",
						"Navigate to '" + optionsLinkName + "' option Successfully ", Status.DONE);
			} else {
				report.updateTestLog("Navigate to '" + optionsLinkName + "' option in Admin Tools",
						"Navigate to '" + optionsLinkName + "' option Failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Navigate to '" + optionsLinkName + "' option in Admin Tools",
					"Navigate to '" + optionsLinkName + "' option Failed", Status.FAIL);
		}
	}

	/**
	 * Method to check the 'Link Relationship' header displayed in 'Link
	 * Relationship' menu or not.
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isLinkRelationshipHeaderDisplayed() {
		boolean flag = false;
		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, linkRelationShipHeaderXpath))) {
				UIHelper.highlightElement(driver, linkRelationShipHeaderXpath);
				actualValueInAdminToolsPage = UIHelper.getTextFromWebElement(driver, linkRelationShipHeaderXpath);
				UIHelper.waitFor(driver);
				flag = true;
			} else {
				actualValueInAdminToolsPage = "Link Relationship Header not displayed.";
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Method to check the 'Relationship' Button displayed in 'Link
	 * Relationship' menu or not.
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isRelationShipButtonDisplayed() {
		boolean flag = false;
		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationshipButtonXpath))) {
				UIHelper.highlightElement(driver, newRelationshipButtonXpath);
				//actualValueInAdminToolsPage = UIHelper.getTextFromWebElement(driver, newRelationshipButtonXpath);
				actualValueInAdminToolsPage = UIHelper.getAttributeFromWebElement(driver, newRelationshipButtonXpath, "value");
				UIHelper.waitFor(driver);
				flag = true;
			} else {
				actualValueInAdminToolsPage = "New Relationship Button not displayed.";
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Method to check the 'Edit' button List displayed in 'Link Relationship'
	 * menu or not.
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isEditButtonsDisplayed() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipSegmentXpath);
			UIHelper.highlightElement(driver, relationshipSegmentXpath);
			UIHelper.waitFor(driver);
			List<WebElement> editListXpath = UIHelper.findListOfElementsbyXpath(newRelationshipEditButtonListXpath,
					driver);
			for (WebElement webElement : editListXpath) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.highlightElement(driver, webElement);
					actualValueInAdminToolsPage = webElement.getText();
					flag = true;
				} else {
					actualValueInAdminToolsPage = "Edit Button not displayed.";
					flag = false;
					break;
				}
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Method to navigate 'New Relationship' option in 'Link Relationship' menu.
	 * 
	 * @author 412766
	 */
	public void navigateToNewRelationship() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, newRelationshipButtonXpath);
			UIHelper.highlightElement(driver, newRelationshipButtonXpath);
			UIHelper.click(driver, newRelationshipButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationShipHeaderXpath))) {
				UIHelper.highlightElement(driver, newRelationShipHeaderXpath);
				report.updateTestLog("Navigate to New Relationship",
						"Navigated to New Relationship Successfully" + "<br /><b>Panel Title : </b>"
								+ UIHelper.findAnElementbyXpath(driver, newRelationShipHeaderXpath).getText(),
						Status.DONE);
			} else {
				report.updateTestLog("Navigate to New Relationship", "Navigated to New Relationship Failed",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to check the All Elements displayed in the 'New Relationship'
	 * panel.
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isAllElementsDisplayedInNewRelationshipPanel() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationShipHeaderXpath))
					&& UIHelper.isWebElementDisplayed(
							UIHelper.findAnElementbyXpath(driver, newRelationshipBiDirectionalXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationshipLableXpath))
					&& UIHelper.isWebElementDisplayed(
							UIHelper.findAnElementbyXpath(driver, newRelationshipParentChildXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationshipSourceXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationshipTargetXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath))
					&& UIHelper
							.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationshipCancelXpath))) {

				UIHelper.highlightElement(driver, newRelationShipHeaderXpath);
				UIHelper.highlightElement(driver, newRelationshipBiDirectionalXpath);
				UIHelper.highlightElement(driver, newRelationshipLableXpath);
				UIHelper.highlightElement(driver, newRelationshipParentChildXpath);
				UIHelper.highlightElement(driver, newRelationshipSourceXpath);
				UIHelper.highlightElement(driver, newRelationshipTargetXpath);
				UIHelper.highlightElement(driver, newRelationshipSaveXpath);
				UIHelper.highlightElement(driver, newRelationshipCancelXpath);
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
	 * Method to create a 'Bi-directional Relationship' option in 'New
	 * Relationship' menu.
	 * 
	 * @author 412766
	 */
	public void createBiDirectionalRelationship() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, newRelationShipHeaderXpath);
			UIHelper.highlightElement(driver, newRelationshipBiDirectionalXpath);
			UIHelper.highlightElement(driver, newRelationshipLableInputXpath);
			String lable = dataTable.getData("Admin", "Bi-Directional_Label");
			UIHelper.findAnElementbyXpath(driver, newRelationshipLableInputXpath).sendKeys(lable);
			UIHelper.highlightElement(driver, newRelationshipSaveXpath);
			// UIHelper.waitFor(driver);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath));
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("To create Bi-directional Relationship",
					"'" + lable + "' Bi-directional Relationship created successfully", Status.DONE);
		} catch (Exception e) {

		}
	}

	/**
	 * Method to check the 'Bi-directional' or 'Parent/Child' Association
	 * updated or not.
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isRelationShipCreated() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipSegmentXpath);
			UIHelper.highlightElement(driver, relationshipSegmentXpath);
			UIHelper.waitFor(driver);
			List<WebElement> linkRelationshipListXpath = UIHelper.findListOfElementsbyXpath(newRelationshipListXpath,
					driver);
			String lable = dataTable.getData("Admin", "Bi-Directional_Label");
			String source = dataTable.getData("Admin", "Parent/Child_Source");
			String target = dataTable.getData("Admin", "Parent/Child_Target");
			String relationshipType = dataTable.getData("Admin", "Relationship_Type");
			for (WebElement webElement : linkRelationshipListXpath) {

				if (relationshipType.equalsIgnoreCase("Bi-directional")) {
					if (webElement.getText().contains(lable) && webElement.getText().contains(relationshipType)) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.highlightElement(driver, webElement);
						UIHelper.waitFor(driver);
						flag = true;
						break;
					} else {
						flag = false;
					}
				} else {
					if (webElement.getText().contains(source) && webElement.getText().contains(target)
							&& webElement.getText().contains(relationshipType)) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.waitFor(driver);
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Method to create a 'Parent/Child Relationship' option in 'New
	 * Relationship' menu.
	 * 
	 * @author 412766
	 */
	public void createParentChildRelationship() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, newRelationShipHeaderXpath);
			// UIHelper.highlightElement(driver,
			// newRelationshipParentChildInputXpath);
			UIHelper.highlightElement(driver, newRelationshipParentChildXpath);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipParentChildXpath));

			String source = dataTable.getData("Admin", "Parent/Child_Source");
			String target = dataTable.getData("Admin", "Parent/Child_Target");

			UIHelper.highlightElement(driver, newRelationshipSourceInputXpath);
			UIHelper.findAnElementbyXpath(driver, newRelationshipSourceInputXpath).sendKeys(source);
			UIHelper.highlightElement(driver, newRelationshipTargetInputXpath);
			UIHelper.findAnElementbyXpath(driver, newRelationshipTargetInputXpath).sendKeys(target);

			UIHelper.highlightElement(driver, newRelationshipSaveXpath);
			// UIHelper.waitFor(driver);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath));
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("To create Parent Child Relationship",
					"'" + source + "' and '" + target + "' for Parent/Child Relationship created successfully",
					Status.DONE);
		} catch (Exception e) {

		}
	}

	/**
	 * Method to check the data provided in the fields of 'New Relationship'
	 * menu.
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean checkEmptyDataInNewRelationship() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, newRelationShipHeaderXpath);
			UIHelper.highlightElement(driver, newRelationshipBiDirectionalXpath);
			UIHelper.highlightElement(driver, newRelationshipLableInputXpath);

			UIHelper.highlightElement(driver, newRelationshipSaveXpath);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath));
			// UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popUpMsgXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, popUpMsgXpath))) {
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popUpMsgXpath);
				UIHelper.highlightElement(driver, popUpMsgXpath);
				flag = true;
				UIHelper.waitFor(driver);
			} else {
				flag = false;
			}

			UIHelper.waitFor(driver);

			UIHelper.highlightElement(driver, newRelationshipCancelXpath);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipCancelXpath));

			UIHelper.waitForPageToLoad(driver);

			AlfrescoAdminToolsPage adminToolsPage = new AlfrescoAdminToolsPage(scriptHelper);
			adminToolsPage.navigateToNewRelationship();

			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, newRelationShipHeaderXpath);
			UIHelper.highlightElement(driver, newRelationshipParentChildXpath);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipParentChildXpath));
			UIHelper.highlightElement(driver, newRelationshipSourceInputXpath);
			UIHelper.highlightElement(driver, newRelationshipTargetInputXpath);

			UIHelper.highlightElement(driver, newRelationshipSaveXpath);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath));
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, popUpMsgXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, popUpMsgXpath))) {
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popUpMsgXpath);
				UIHelper.highlightElement(driver, popUpMsgXpath);
				flag = true;
				UIHelper.waitFor(driver);
			} else {
				flag = false;
			}

			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Method to select the particular relationship option in the Table Menu
	 * List.
	 * 
	 * @author 412766
	 */
	public void selectToUpdateRelationship() {
		try {
			UIHelper.waitFor(driver);
			// UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipSegmentXpath);
			UIHelper.highlightElement(driver, relationshipSegmentXpath);
			UIHelper.waitFor(driver);
			List<WebElement> linkRelationshipListXpath = UIHelper.findListOfElementsbyXpath(newRelationshipListXpath,
					driver);
			List<WebElement> linkRelationshipEditButtonListXpath = UIHelper
					.findListOfElementsbyXpath(newRelationshipEditButtonListXpath, driver);
			String lable = dataTable.getData("Admin", "Bi-Directional_Label");
			String source = dataTable.getData("Admin", "Parent/Child_Source");
			String target = dataTable.getData("Admin", "Parent/Child_Target");
			String relationshipType = dataTable.getData("Admin", "Relationship_Type");
			int index = 0;
			for (WebElement webElement : linkRelationshipListXpath) {
				UIHelper.highlightElement(driver, webElement);

				if (relationshipType.equalsIgnoreCase("Bi-directional")) {
					if (webElement.getText().contains(lable) && webElement.getText().contains(relationshipType)) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.waitFor(driver);
						UIHelper.highlightElement(driver, linkRelationshipEditButtonListXpath.get(index));
						UIHelper.clickanElement(linkRelationshipEditButtonListXpath.get(index));
						UIHelper.waitFor(driver);
						report.updateTestLog("Select to update Bi-directional Relationship",
								"Bi-directional Relationship Selected successfully", Status.PASS);
						break;
					}
				} else {
					if (webElement.getText().contains(source) && webElement.getText().contains(target)
							&& webElement.getText().contains(relationshipType)) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.waitFor(driver);
						UIHelper.highlightElement(driver, linkRelationshipEditButtonListXpath.get(index));
						UIHelper.clickanElement(linkRelationshipEditButtonListXpath.get(index));
						UIHelper.waitFor(driver);
						report.updateTestLog("Select to update Parent Child Relationship",
								"Parent Child Relationship Selected successfully", Status.PASS);
						break;
					}
				}
				index++;
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to update a 'Bi-directional Relationship' option in 'New
	 * Relationship' menu.
	 * 
	 * @author 412766
	 */
	public void updateBiDirectionalRelationship() {
		try {
			UIHelper.waitFor(driver);

			UIHelper.highlightElement(driver, newRelationshipBiDirectionalXpath);
			UIHelper.highlightElement(driver, newRelationshipUpdateLableInputXpath);
			String lable = dataTable.getData("Admin", "Bi-Directional_Label_Update");
			UIHelper.findAnElementbyXpath(driver, newRelationshipUpdateLableInputXpath).clear();
			UIHelper.findAnElementbyXpath(driver, newRelationshipUpdateLableInputXpath).sendKeys(lable);
			UIHelper.highlightElement(driver, newRelationshipSaveXpath);
			// UIHelper.waitFor(driver);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath));
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("To update Bi-directional Relationship",
					"'" + lable + "' Bi-directional Relationship updated successfully", Status.DONE);
		} catch (Exception e) {

		}
	}

	/**
	 * Method to update a 'Parent/Child Relationship' option in 'New
	 * Relationship' menu.
	 * 
	 * @author 412766
	 */
	public void updateParentChildRelationship() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, newRelationshipParentChildXpath);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipParentChildXpath));

			String source = dataTable.getData("Admin", "Parent/Child_Source_Update");
			String target = dataTable.getData("Admin", "Parent/Child_Target_Update");

			UIHelper.highlightElement(driver, newRelationshipUpdateSourceInputXpath);
			UIHelper.findAnElementbyXpath(driver, newRelationshipUpdateSourceInputXpath).clear();
			UIHelper.findAnElementbyXpath(driver, newRelationshipUpdateSourceInputXpath).sendKeys(source);
			UIHelper.highlightElement(driver, newRelationshipUpdateTargetInputXpath);
			UIHelper.findAnElementbyXpath(driver, newRelationshipUpdateTargetInputXpath).clear();
			UIHelper.findAnElementbyXpath(driver, newRelationshipUpdateTargetInputXpath).sendKeys(target);

			UIHelper.highlightElement(driver, newRelationshipSaveXpath);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath));
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("To update Parent Child Relationship",
					"'" + source + "' and '" + target + "' for Parent Child Relationship updated successfully",
					Status.DONE);
		} catch (Exception e) {

		}
	}

	/**
	 * Method to check the 'Application Tool' menu loaded by default in 'Admin
	 * Tools' page.
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isApplicationToolHeaderDisplayed() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, applicationToolHeaderXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, applicationToolHeaderXpath))) {
				UIHelper.highlightElement(driver, applicationToolHeaderXpath);
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
	 * Method to check the 'Admin Tools' options available or not
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isAdminToolsOptionsAvailable() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, adminToolsOptionsSegmentXpath);
			UIHelper.highlightElement(driver, adminToolsOptionsSegmentXpath);

			List<WebElement> adminToolsOptionList = UIHelper.findListOfElementsbyXpath(adminToolsOptionsListXpath,
					driver);
			for (WebElement webElement : adminToolsOptionList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.highlightElement(driver, webElement);
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Method to Apply the 'Theme' in 'Admin Tools' menu.
	 * 
	 * @author 412766
	 */
	public void applyTheme() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, themeSegmentXpath);
			UIHelper.highlightElement(driver, themeSegmentXpath);
			UIHelper.highlightElement(driver, themeDropDownXpath);
			String themeName = dataTable.getData("Admin", "Theme_Name");
			UIHelper.selectDropdownList(driver, UIHelper.findAnElementbyXpath(driver, themeDropDownXpath), themeName);
			UIHelper.highlightElement(driver, applyThemeButtonXpath);
			// UIHelper.waitFor(driver);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, applyThemeButtonXpath));
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, themeSegmentXpath);
			UIHelper.highlightElement(driver, themeSegmentXpath);
			UIHelper.highlightElement(driver, themeDropDownXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("To Apply selected Theme", "'" + themeName + "' Theme Applied successfully",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to check the 'Theme' applied or not in 'Admin Tools' menu.
	 * 
	 * @return boolean
	 * @author 412766
	 */
	public boolean isThemeApplied() {
		boolean flag = false;
		try {
			Select clickThis = new Select(UIHelper.findAnElementbyXpath(driver, themeDropDownXpath));
			String appliedTheme = clickThis.getFirstSelectedOption().getText();
			String themeName = dataTable.getData("Admin", "Theme_Name");
			if (appliedTheme.equalsIgnoreCase(themeName)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Save tag
	public void saveTag() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.highlightElement(driver, saveTagXpath);
			UIHelper.click(driver, saveTagXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getActualValue() {
		return actualValueInAdminToolsPage;
	}

	// Method to verify the Edit UI of 'Bi-directional Relationship' and
	// Parent/Child page
	public void VerifyEditUIInLinkRelationship() {
		try {
			UIHelper.waitFor(driver);

			UIHelper.highlightElement(driver, editRelationShipGeneralXpath);

			String relationshipType = dataTable.getData("Admin", "Relationship_Type");

			if (relationshipType.equalsIgnoreCase("Bi-directional")) {
				UIHelper.highlightElement(driver, editRelationShipXpath);
				UIHelper.highlightElement(driver, newRelationshipBiDirectionalXpath);
				UIHelper.highlightElement(driver, newRelationshipUpdateLableInputXpath);
				UIHelper.waitFor(driver);
			} else {
				UIHelper.highlightElement(driver, editRelationShipHeaderXpath);
				UIHelper.highlightElement(driver, newRelationshipParentChildXpath);
				UIHelper.highlightElement(driver, newRelationshipUpdateLableInputXpath);
				UIHelper.highlightElement(driver, newRelationshipUpdateSourceInputXpath);
				UIHelper.highlightElement(driver, newRelationshipUpdateTargetInputXpath);
				UIHelper.waitFor(driver);
			}
			UIHelper.highlightElement(driver, newRelationshipSaveXpath);
			UIHelper.highlightElement(driver, newRelationshipCancelXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Edit UI of Bi-directional and Parent/Child Relationship",
					"UI of Edit Relationship dialog Verified successfully", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Edit UI of Bi-directional and Parent/Child Relationship",
					"UI of Edit Relationship dialog verification failed", Status.FAIL);
			e.getMessage();
		}
	}

	// Add users to the Existing group page
	public void addUserToExistingGroupwithoutfIden() {
		try {
			UIHelper.findandAddElementsToaList(driver, groupListXpath, groupList);
			String GroupName = dataTable.getData("Admin", "GroupName");
			String newUser = dataTable.getData("Admin", "Adduser");
			for (String group : groupList) {
				if (group.contains(GroupName)) {
					String finaleditgpXpath = tempeditgpXpath.replace("CRAFT", GroupName);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finaleditgpXpath);
					UIHelper.click(driver, finaleditgpXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, addUserIconXpath);
					WebElement groupDom = UIHelper.findAnElementbyXpath(driver, groupDOMXpath);
					if (groupDom.getText().contains(newUser)) {
						report.updateTestLog("Add User to New Group", "User already exixts"
								+ "<br /><b>Group Name : <b>" + GroupName + "<br /><b>Add User : <b>" + newUser,
								Status.DONE);
						/*
						 * String flag = "exist"; new
						 * FileUtil().writeTextToFile(flag, testOutputFilePath);
						 */

						break;

					} else {
						UIHelper.click(driver, addUserIconXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, peopleFinderXpath);
						UIHelper.sendKeysToAnElementByXpath(driver, peopleFinderXpath, newUser);
						UIHelper.waitForVisibilityOfEleByXpath(driver, searchButtonXpath);
						UIHelper.click(driver, searchButtonXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, addButtonXpath);
						UIHelper.click(driver, addButtonXpath);
						UIHelper.waitFor(driver);

						report.updateTestLog(
								"Add User to New Group", "Add User to New Group successfully."
										+ "<br /><b>Group Name : <b>" + GroupName + "<br /><b>Add User : <b>" + newUser,
								Status.DONE);
					}
				}
				break;
			}

		} catch (Exception e) {
			report.updateTestLog("Add User to New Group", "Add User to New Group failed. Group not found", Status.FAIL);
		}

	}

	// Search group page As Group Admin
	public Boolean searchGpAndDeleteAsGroupAdmin(String Group) {
		try {
			UIHelper.click(driver, groupMgmtXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnAsGrpAdminXpath);
			UIHelper.highlightElement(driver, groupFinderGrpAdminXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, groupFinderGrpAdminXpath, Group);
			UIHelper.click(driver, searchGroupBtnAsGrpAdminXpath);
			UIHelper.waitFor(driver);
			UIHelper.findAnElementbyXpath(driver, groupFinderGrpAdminXpath).clear();
			if (UIHelper.findAnElementbyXpath(driver, groupDOMXpath).getText().contains(Group)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			return false;

		}
	}

	/**
	 * @author 412766
	 * @param Group
	 * @return
	 */
	public Boolean searchGroupASGroupAdmin(String Group) {
		try {
			UIHelper.click(driver, groupMgmtXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnAsGrpAdminXpath);
			UIHelper.highlightElement(driver, groupFinderGrpAdminXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, groupFinderGrpAdminXpath, Group);
			UIHelper.click(driver, searchGroupBtnAsGrpAdminXpath);
			UIHelper.waitFor(driver);
			UIHelper.findAnElementbyXpath(driver, groupFinderGrpAdminXpath).clear();
			String finalSearchResult = groupResultXpath.replace("CRAFT", Group);

			if (UIHelper.findAnElementbyXpath(driver, finalSearchResult).getText().contains(Group)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			return false;

		}
	}

	// Add group page As Group admin
	public void createGroupAsGrpAdmin() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnAsGrpAdminXpath);
			UIHelper.click(driver, groupBrowseBtnAsGrpAdminXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addgroupXpath);
			UIHelper.click(driver, addgroupXpath);
			UIHelper.waitForPageToLoad(driver);

			String groupName = dataTable.getData("Admin", "GroupName");
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");

			UIHelper.sendKeysToAnElementByXpath(driver, groupIdentifierAsGrpAdminXpath, groupName);
			UIHelper.sendKeysToAnElementByXpath(driver, groupNameAsGrpAdminXpath, gpIdentifier);
			UIHelper.click(driver, createGroupAsGrpAdminBtn);
			String ActualGpName = tempeditgpXpath.replace("CRAFT", groupName);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, groupListXpath).isDisplayed()) {
				report.updateTestLog("Create New Group",
						"Create New Group successfully." + "<br /><b> Expected Group Name : </b>" + groupName
								+ "<br /><b> Actual Group Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver, ActualGpName).getText(),
						Status.DONE);
			} else {
				report.updateTestLog("Create New Group", "Create New Group Failed. Group not create :", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Create New Group", "Create New Group Failed. Group not create :", Status.FAIL);
		}
	}

	// Browse group page As Group Admin
	public void BrowseGroupAsGrpAdmin(String Group) {
		try {
			UIHelper.click(driver, groupMgmtXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnAsGrpAdminXpath);
			UIHelper.highlightElement(driver, groupFinderGrpAdminXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, groupFinderGrpAdminXpath, Group);
			UIHelper.click(driver, groupBrowseBtnAsGrpAdminXpath);
			UIHelper.waitFor(driver);
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");

			if (UIHelper.findAnElementbyXpath(driver, groupListXpath).getText().contains(Group)) {
				report.updateTestLog("Browsed Group",
						"Browsed Group found successfully." + "<br /><b> Expected Group Name : </b>" + Group
								+ "<br /><b> Actual Group Name : </b>" + Group + " (" + gpIdentifier + ")",
						Status.DONE);

			} else {
				report.updateTestLog("Browsed  Group",
						"Browsed Group not found." + "<br /><b>Group Name : </b>" + Group, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Browsed  Group", "Browsed Group not found." + "<br /><b>Group Name : </b>" + Group,
					Status.FAIL);
		}
	}

	// Add a new group as Admin
	public void createNewGroupAsAdmin() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, groupBrowseBtnXpath);
			UIHelper.click(driver, groupBrowseBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addgroupXpath);
			UIHelper.click(driver, addgroupXpath);
			UIHelper.waitForPageToLoad(driver);

			String groupName = dataTable.getData("Admin", "SubGroup");
			String gpIdentifier = dataTable.getData("Admin", "SubGpIdentifier");

			UIHelper.sendKeysToAnElementByXpath(driver, groupIdentifierXpath, groupName);
			UIHelper.sendKeysToAnElementByXpath(driver, groupNameXpath, gpIdentifier);
			UIHelper.click(driver, createGroupBtn);
			String ActualGpName = tempeditgpXpath.replace("CRAFT", groupName);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, groupListXpath).isDisplayed()) {
				report.updateTestLog("Create New Group",
						"Create New Group successfully." + "<br /><b> Expected Group Name : </b>" + groupName
								+ "<br /><b> Actual Group Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver, ActualGpName).getText(),
						Status.DONE);
			} else {
				report.updateTestLog("Create New Group", "Create New Group Failed. Group not create :", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Create New Group", "Create New Group Failed. Group not create :", Status.FAIL);
		}
	}

	// Search User
	public boolean searchUserInUsers(String User) {
		try {

			UIHelper.click(driver, userPageXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchTxtInUsers);
			UIHelper.highlightElement(driver, searchTxtInUsers);
			UIHelper.findAnElementbyXpath(driver, searchTxtInUsers).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, searchTxtInUsers, User);
			UIHelper.click(driver, searchBtnInUsers);
			UIHelper.waitForVisibilityOfEleByXpath(driver, userListXPath);
			UIHelper.findandAddElementsToaList(driver, userListXPath, usrList);
			for (String usr : usrList) {
				if (usr.contains(User)) {
					return true;
				} else {
					return false;
				}
			}

		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Search User User admin
	public boolean searchUserAsUserAdmin(String User) {
		try {
			UIHelper.click(driver, userPageXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchTxtAsUserAdmin);
			UIHelper.highlightElement(driver, searchTxtAsUserAdmin);
			UIHelper.findAnElementbyXpath(driver, searchTxtAsUserAdmin).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, searchTxtAsUserAdmin, User);
			UIHelper.click(driver, searchBtnAsUserAdmin);
			String finalUserListXPath = userListXPath.replace("CRAFT", User);
			if (UIHelper.findAnElementbyXpath(driver, finalUserListXPath).getText().contains(User)) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// Delete User- User admin
	public void deleteUser(String User) {
		try {
			String finalUserListXPath = userListXPath.replace("CRAFT", User);
			UIHelper.findandAddElementsToaList(driver, finalUserListXPath, usrList);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalUserListXPath);

			UIHelper.click(driver, finalUserListXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteUserXPath);
			UIHelper.click(driver, deleteUserXPath);
			UIHelper.waitForAnElement(driver, UIHelper.findAnElementbyXpath(driver, delConfirmXPath));
			UIHelper.click(driver, delConfirmXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, newuserAsAdmin);
			report.updateTestLog("Verify Delete user", "User deleted successfully" + "<br /><b> User : </b>" + User,
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Verify Delete user", "Failed to Delete user", Status.FAIL);
		}
	}

	// Create User -User admin
	public void createUserAsUserAdmin() {
		try {

			UIHelper.click(driver, newuserAsUserAdmin);
			String fName = dataTable.getData("UserName", "FirstName");
			String lName = dataTable.getData("UserName", "LastName");
			String eMail = dataTable.getData("UserName", "Email");
			String uName = dataTable.getData("UserName", "Username");
			String pass = dataTable.getData("UserName", "Password");
			String cPass = dataTable.getData("UserName", "VerifyPassword");

			UIHelper.sendKeysToAnElementByXpath(driver, firstNameXPath, fName);
			UIHelper.sendKeysToAnElementByXpath(driver, lastNameXPath, lName);
			UIHelper.sendKeysToAnElementByXpath(driver, emailXPath, eMail);
			UIHelper.sendKeysToAnElementByXpath(driver, userNameXPath, uName);
			UIHelper.sendKeysToAnElementByXpath(driver, passXPath, pass);
			UIHelper.sendKeysToAnElementByXpath(driver, confirmPassXPath, cPass);
			UIHelper.click(driver, createBtnXPath);

			report.updateTestLog("Click on Create user", "Create user button clicked" + "<br /><b> User : </b>" + uName,
					Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Click on Create user", "Failed to create new user", Status.FAIL);
		}
	}

	// Create User -Admin
	public void createUser() {
		try {

			UIHelper.click(driver, newuserAsAdmin);
			String fName = dataTable.getData("UserName", "FirstName");
			String lName = dataTable.getData("UserName", "LastName");
			String eMail = dataTable.getData("UserName", "Email");
			String uName = dataTable.getData("UserName", "Username");
			String pass = dataTable.getData("UserName", "Password");
			String cPass = dataTable.getData("UserName", "VerifyPassword");

			UIHelper.sendKeysToAnElementByXpath(driver, firstNameXPathAdmin, fName);
			UIHelper.sendKeysToAnElementByXpath(driver, lastNameXPathAdmin, lName);
			UIHelper.sendKeysToAnElementByXpath(driver, emailXPathAdmin, eMail);
			UIHelper.sendKeysToAnElementByXpath(driver, userNameXPathAdmin, uName);
			UIHelper.sendKeysToAnElementByXpath(driver, passXPathAdmin, pass);
			UIHelper.sendKeysToAnElementByXpath(driver, confirmPassXPathAdmin, cPass);
			UIHelper.click(driver, createBtnXPathAdmin);

			report.updateTestLog("Click on Create user", "Create user button clicked" + "<br /><b> User : </b>" + uName,
					Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Click on Create user", "Failed to create new user", Status.FAIL);
		}
	}

	// Edit User -User admin
	public void editUserAsUserAdmin() {
		try {

			String fName = dataTable.getData("UserName", "EditFirstName");
			String lName = dataTable.getData("UserName", "EditLastName");
			String eMail = dataTable.getData("UserName", "EditEmail");
			String uName = dataTable.getData("UserName", "Username");

			String finalUserListXPath = userListXPath.replace("CRAFT", uName);
			UIHelper.findandAddElementsToaList(driver, finalUserListXPath, usrList);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalUserListXPath);
			UIHelper.click(driver, finalUserListXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editUsrXPath);
			UIHelper.click(driver, editUsrXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editFNameXPath);
			UIHelper.findAnElementbyXpath(driver, editFNameXPath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, editFNameXPath, fName);
			UIHelper.findAnElementbyXpath(driver, editLNameXPath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, editLNameXPath, lName);
			UIHelper.findAnElementbyXpath(driver, editMailXPath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, editMailXPath, eMail);

			UIHelper.click(driver, editSaveXPath);

			report.updateTestLog("Click on Save Edit user", "Save button clicked" + "<br /><b> User : </b>" + uName,
					Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Click on Save Edit user", "Failed to save edit user", Status.FAIL);
		}
	}

	// Navigate Users page
	public void adminOptionsMgmt() {
		try {

			UIHelper.highlightElement(driver, adminOptionsXpath);
			UIHelper.findandAddElementsToaList(driver, adminListXpath, optionList);
			String GroupName = dataTable.getData("Admin", "GroupName");
			for (String option : optionList) {
				if (option.equalsIgnoreCase(GroupName)) {
					UIHelper.click(driver, option);
					report.updateTestLog("Verify " + GroupName + " management", GroupName + " management is available",
							Status.DONE);

				} else {
					report.updateTestLog("Verify " + GroupName + " management",
							GroupName + "management is not available", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify admin options", "Failed to selectadmin option", Status.FAIL);
		}
	}

	// Navigate to node browser tab
	public void navigateToNodeBrowserTab() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, nodeBrowserXpath);
			UIHelper.highlightElement(driver, nodeBrowserXpath);
			report.updateTestLog("Click on Node browser option", "Node browser option clicked successfully",
					Status.DONE);
			UIHelper.click(driver, nodeBrowserXpath);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Node browser option", "Failed to click Node browser", Status.FAIL);
		}
	}

	// Node browser search option
	public void searchNodeBrowserResults(String url) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, nodeBrowserSearchBoxXpath);
			UIHelper.highlightElement(driver, nodeBrowserSearchBoxXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, nodeBrowserSearchBoxXpath, url);
			UIHelper.highlightElement(driver, nodeRefBtnXpath);
			UIHelper.click(driver, nodeRefBtnXpath);
			UIHelper.highlightElement(driver, nodeRefOptionXpath);
			UIHelper.click(driver, nodeRefOptionXpath);
			UIHelper.click(driver, nodeRefSearchBtnXpath);
			report.updateTestLog("Search with '" + url + "' in Node browser",
					"Node browser results displayed successfully", Status.DONE);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Search with '" + url + "' in Node browser", "Failed to display search results",
					Status.FAIL);
		}
	}

	// Navigate to node browser metadata details page
	public void navigateToNodeBrowserDetailsPg(String folderOrFileName) {
		try {

			UIHelper.waitForPageToLoad(driver);
			String finalNodeRefNameXpath = nodeRefNameXpath.replace("CRAFT", folderOrFileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalNodeRefNameXpath);
			UIHelper.highlightElement(driver, finalNodeRefNameXpath);
			UIHelper.click(driver, finalNodeRefNameXpath);

			String finalNodeRefHeaderXpath = nodeRefHeaderXpath.replace("CRAFT", folderOrFileName);
			UIHelper.waitForPageToLoad(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalNodeRefHeaderXpath);
			} catch (Exception e) {

			}
			UIHelper.highlightElement(driver, finalNodeRefHeaderXpath);

			report.updateTestLog("Navigate to node browser details page",
					"Node browser details page displayed successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Navigate to node browser details page", "Failed to display Node browser details page",
					Status.FAIL);
		}
	}

	// Click on child name
	public void clickOnChildNameLinkUnderChildren(String childName) {
		try {

			UIHelper.waitForPageToLoad(driver);
			String finalNodeRefNameXpath = tempXpathForChildNameLinkUnderChildren.replace("CRAFT", childName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalNodeRefNameXpath);
			UIHelper.highlightElement(driver, finalNodeRefNameXpath);
			UIHelper.click(driver, finalNodeRefNameXpath);

			String finalNodeRefHeaderXpath = nodeRefHeaderXpath.replace("CRAFT", childName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalNodeRefHeaderXpath);
			UIHelper.highlightElement(driver, finalNodeRefHeaderXpath);

			report.updateTestLog("Click the '" + childName + "' under Childern",
					"Low rendition content details are displayed", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click the '" + childName + "' under Childern",
					"Failed to display Low rendition content details", Status.FAIL);
		}
	}

	// Click on Property Name
	public void clickOnPropertyNameValueLink(String propertyName) {
		try {

			UIHelper.waitForPageToLoad(driver);
			String finalNodeRefNameXpath = tempXpathForChildNameLinkUnderChildren.replace("CRAFT", propertyName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalNodeRefNameXpath);
			UIHelper.highlightElement(driver, finalNodeRefNameXpath);
			UIHelper.click(driver, finalNodeRefNameXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Click the '" + propertyName + "' under Property", "Image file displayed",
					Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click the '" + propertyName + "' under Property", "Failed to display the image file",
					Status.FAIL);
		}
	}

	// get value of a particular label in node browser details page
	public String getValueInNodeBrowserDetailsPg(String labelName) {
		String value = "";
		try {

			UIHelper.waitForPageToLoad(driver);
			String finalNodeRefValXpath = nodeRefValueXpath.replace("CRAFT", labelName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalNodeRefValXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalNodeRefValXpath));
			UIHelper.highlightElement(driver, finalNodeRefValXpath);
			value = UIHelper.getTextFromWebElement(driver, finalNodeRefValXpath);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return value;
	}

	// Check aspects in node browser details page
	public void verifyAspectsInNodeBrowserPg(String aspects) {
		try {

			if (aspects.contains(",")) {

				String[] aspectsArray = aspects.split(",");

				for (String aspect : aspectsArray) {
					if (checkAspectAvailInNodeBrowserPg(aspect)) {
						report.updateTestLog("Verify added aspect " + aspect, aspect + " is added for the folder/file",
								Status.PASS);
					} else {
						report.updateTestLog("Verify added aspect " + aspect,
								aspect + " is not added for the folder/file", Status.FAIL);
					}
				}

			} else {
				String aspect = aspects;
				if (checkAspectAvailInNodeBrowserPg(aspect)) {
					report.updateTestLog("Verify added aspect " + aspect, aspect + " is added for the folder/file",
							Status.PASS);
				} else {
					report.updateTestLog("Verify added aspect " + aspect, aspect + " is not added for the folder/file",
							Status.FAIL);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify added aspect ", "Failed to display aspects", Status.FAIL);
		}
	}

	public boolean checkAspectAvailInNodeBrowserPg(String aspect) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			List<WebElement> aspectsList = driver.findElements(By.xpath(nodeRefAspectsXpath));

			for (WebElement element : aspectsList) {
				if (element.getText().contains(aspect)) {
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

	/**
	 * @author 412766
	 * @param tagDetails
	 * @return Map<String, String>
	 */
	public Map<String, String> getAllValuesFromNodeBrowser(String tagDetails) {
		Map<String, String> UITagMap = new HashMap<String, String>();
		try {
			String[] tags = tagDetails.split(",");
			for (String string : tags) {
				String[] tag = string.split("-");
				UITagMap.put(tag[0], getValueInNodeBrowserDetailsPg(tag[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return UITagMap;
	}

	/**
	 * @author 412766
	 * @param folderOrFile
	 * @param manifestationOrWork
	 * @param baseOrWorkURN
	 * @param uiDetailsMap
	 * @param manifestOrWorkDetailsMap
	 * @return ArrayList<ArrayList<String>>
	 */
	public ArrayList<ArrayList<String>> verifyJSONResponse(String folderOrFile, String manifestationOrWork,
			String baseOrWorkURN, Map<String, String> uiDetailsMap, Map<String, Object> manifestOrWorkDetailsMap) {

		ArrayList<ArrayList<String>> finalResult = new ArrayList<ArrayList<String>>();
		try {
			ArrayList<String> tagPassedList = new ArrayList<String>();
			ArrayList<String> tagFailedList = new ArrayList<String>();

			for (String key : manifestOrWorkDetailsMap.keySet()) {

				if (key.equalsIgnoreCase("isDefinedBy")) {
					String tempIsDefinedBy = "https://data.pearson.com/TYPE/URN/metadata";
					if (manifestationOrWork.equalsIgnoreCase("manifestation")) {
						baseOrWorkURN = baseOrWorkURN.replace("urn:pearson:manifestation:", "").trim();
					} else {
						baseOrWorkURN = baseOrWorkURN.replace("urn:pearson:work:", "").trim();
					}
					String finalIsDefinedBy = tempIsDefinedBy.replace("TYPE", manifestationOrWork).replace("URN",
							baseOrWorkURN);
					System.out.println("IS DEFINE BY URL : " + manifestOrWorkDetailsMap.get(key).toString());
					System.out.println("IS DEFINE BY URL CREATED : " + finalIsDefinedBy);
					if ((manifestOrWorkDetailsMap.get(key).toString()).equalsIgnoreCase(finalIsDefinedBy)) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}

				} else if (key.equalsIgnoreCase("format")) {
					if (uiDetailsMap.get(key).contains(manifestOrWorkDetailsMap.get(key).toString())) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}

				} else if (key.equalsIgnoreCase("dateModified")) {
					String jsonResDate = manifestOrWorkDetailsMap.get(key).toString().replace("T", " ");
					String uiDateFormat = "" + new DateUtil().convertDateToCustomizedFormat("yyyy-MM-dd HH:mm:ss",
							jsonResDate, "dd MMM YYYY HH:mm:ss");
					if (uiDetailsMap.get(key).contains(uiDateFormat)) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}
				} else if (key.equalsIgnoreCase("type")) {
					String tempType = "";
					String finalType = "";
					if (folderOrFile.equalsIgnoreCase("FILE")) {
						tempType = "[CreativeWork, Thing, TYPE]";
						finalType = tempType.replace("TYPE", manifestationOrWork);
					} else {
						tempType = "[CreativeWork, Thing, Container, TYPE]";
						finalType = tempType.replace("TYPE", manifestationOrWork);
					}

					if ((manifestOrWorkDetailsMap.get(key).toString()).equalsIgnoreCase(finalType)) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}

				} else if (key.equalsIgnoreCase("exampleOfWork")) {
					if (manifestOrWorkDetailsMap.get(key).toString().contains(uiDetailsMap.get(key))) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}

				} else if (key.equalsIgnoreCase("isPartOf")) {

				} else if (key.equalsIgnoreCase("@context")) {

				} else if (key.equalsIgnoreCase("uuid")) {

				} else if (key.equalsIgnoreCase("dateCreated")) {
					String jsonResDate = manifestOrWorkDetailsMap.get(key).toString().replace("T", " ");
					String uiDateFormat = "" + new DateUtil().convertDateToCustomizedFormat("yyyy-MM-dd HH:mm:ss",
							jsonResDate, "dd MMM YYYY HH:mm:ss");
					if (uiDetailsMap.get(key).contains(uiDateFormat)) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}
				} else if (key.equalsIgnoreCase("filename")) {
					if (uiDetailsMap.get(key).equals(manifestOrWorkDetailsMap.get(key).toString())) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}

				} else if (key.equalsIgnoreCase("contentSize")) {
					if (uiDetailsMap.get(key).contains(manifestOrWorkDetailsMap.get(key).toString())) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}

				} else if (key.equalsIgnoreCase("name")) {

				} else if (key.equalsIgnoreCase("id")) {
					if (uiDetailsMap.get(key).contains(manifestOrWorkDetailsMap.get(key).toString())) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}

				} else if (key.equalsIgnoreCase("identifiedBy")) {

				} else if (key.equalsIgnoreCase("sameAs")) {

				} else if (key.equalsIgnoreCase("workExample")) {
					if (manifestOrWorkDetailsMap.get(key).toString().contains(uiDetailsMap.get(key))) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}

				} else if (key.equalsIgnoreCase("thumbnailUrl")) {

				} else if (key.equalsIgnoreCase("status")) {
					if (manifestOrWorkDetailsMap.get(key).toString().contains(uiDetailsMap.get(key))) {
						tagPassedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					} else {
						tagFailedList.add(key + " = " + manifestOrWorkDetailsMap.get(key).toString());
					}
				}
			}

			finalResult.add(tagPassedList);
			finalResult.add(tagFailedList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResult;
	}
	
	public ArrayList<String> getNewRelationshipPanelFields(){
		ArrayList<String> fieldValues = new ArrayList<String>();
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.findListOfElementsbyXpath(newRelationShipHeaderXpath,driver ).size()>0) {
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationShipHeaderXpath).trim());
			}
			if (UIHelper.findListOfElementsbyXpath(newRelationShipGeneralXpath,driver).size()>0){
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationShipGeneralXpath).replace(":", "").trim());
			}		
			if (UIHelper.findListOfElementsbyXpath(newRelationShipTypeXpath, driver).size()>0){
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationShipTypeXpath).replace(":", "").trim());
			}
			if (UIHelper.findListOfElementsbyXpath(newRelationshipBiDirectionalXpath, driver).size()>0){
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationshipBiDirectionalXpath).trim());
			}
			if (UIHelper.findListOfElementsbyXpath(newRelationshipLableXpath, driver).size()>0) {
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationshipLableXpath).replace(":", "").trim());
			}
			if (UIHelper.findListOfElementsbyXpath(newRelationshipParentChildXpath, driver).size()>0){
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationshipParentChildXpath).trim());
			}		
			if (UIHelper.findListOfElementsbyXpath(newRelationshipSourceXpath, driver).size()>0){
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationshipSourceXpath).replace(":", "").trim());
			}
			if (UIHelper.findListOfElementsbyXpath(newRelationshipTargetXpath, driver).size()>0){
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationshipTargetXpath).replace(":", "").trim());
			}
			/*if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath))){
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationshipSaveXpath).trim());
			}
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, newRelationshipCancelXpath))){
				fieldValues.add(UIHelper.getTextFromWebElement(driver, newRelationshipCancelXpath).trim());
			}	*/
			
			report.updateTestLog("Retrieve New Relationship Prompt fields "," New Relationship prompt fields retrieved", Status.DONE);
		}catch (Exception e) {
			report.updateTestLog("Retrieve New Relationship Prompt fields "," New Relationship prompt field retrieval failed", Status.FAIL);
		}
		
		return fieldValues;
	}
	
	
	//Select edit for any created relationship with type
	public void selectEditForAnyRelationType(String type) {
		try {
			boolean flag=false;
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, relationshipSegmentXpath);
			UIHelper.highlightElement(driver, relationshipSegmentXpath);
			UIHelper.waitFor(driver);
			List<WebElement> linkRelationshipListXpath = UIHelper.findListOfElementsbyXpath(newRelationshipListXpath,
					driver);
			List<WebElement> linkRelationshipEditButtonListXpath = UIHelper
					.findListOfElementsbyXpath(newRelationshipEditButtonListXpath, driver);
			int index = -1;
			for (WebElement webElement : linkRelationshipListXpath) {
				UIHelper.highlightElement(driver, webElement);
				index++;
					if (webElement.getText().contains(type)) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.waitFor(driver);
						UIHelper.highlightElement(driver, linkRelationshipEditButtonListXpath.get(index));
						UIHelper.clickanElement(linkRelationshipEditButtonListXpath.get(index));
						UIHelper.waitFor(driver);
						flag=true;
						break;
		    }
			
		}if(flag) {
			report.updateTestLog("Click edit on " +type +" Relationship",
					"Relationship clicked successfully", Status.DONE);
		}else {
			report.updateTestLog("Click edit on "+type +" Relationship",
					"Relationship clicked Unsuccessfull", Status.FAIL);
		}
		
	}catch(Exception e) {
		report.updateTestLog("Click edit on "+type +" Relationship",
				"Relationship click failed", Status.FAIL);
	}
		}
	
	
	// Create relationship to check the error 
	public void createRleationshipForErrorCheck(String type) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,newRelationShipHeaderXpath);
			if(type.contains("Bi-directional")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, newRelationShipBidirectionalRadioButtonXpath);
				UIHelper.highlightElement(driver, newRelationShipBidirectionalRadioButtonXpath);
				UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, newRelationShipBidirectionalRadioButtonXpath));
				UIHelper.highlightElement(driver, newRelationshipLableInputXpath);
				String lable = dataTable.getData("Admin", "Bi-Directional_Label");
				UIHelper.findAnElementbyXpath(driver, newRelationshipLableInputXpath).sendKeys(lable);
				UIHelper.highlightElement(driver, newRelationshipSaveXpath);
				UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath));
				report.updateTestLog("Create "+type+" Relation ship for error check",
						"create "+type+" Relation ship for error check done", Status.DONE);
			}else if(type.contains("Parent/Child")){
				String source = dataTable.getData("Admin", "Parent/Child_Source");
				String target = dataTable.getData("Admin", "Parent/Child_Target");

				UIHelper.waitForVisibilityOfEleByXpath(driver, newRelationShipParentChildRadioButtonXpath);
				UIHelper.highlightElement(driver, newRelationShipParentChildRadioButtonXpath);
				UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, newRelationShipParentChildRadioButtonXpath));
				UIHelper.highlightElement(driver, newRelationshipSourceInputXpath);
				UIHelper.findAnElementbyXpath(driver, newRelationshipSourceInputXpath).sendKeys(source);
				UIHelper.highlightElement(driver, newRelationshipTargetInputXpath);
				UIHelper.findAnElementbyXpath(driver, newRelationshipTargetInputXpath).sendKeys(target);
				UIHelper.highlightElement(driver, newRelationshipSaveXpath);
				UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, newRelationshipSaveXpath));
				report.updateTestLog("Create "+type+" Relation ship for error check",
						"create "+type+" Relation ship for error check done", Status.DONE);
			}else {
				report.updateTestLog("Create "+type+" Relation ship for error check",
						"Relation ship type mismatched", Status.FAIL);
			}
		}catch(Exception e) {
			report.updateTestLog("Create "+type+" Relation ship for error check",
					"create "+type+" Relation ship for error check failed", Status.FAIL);
		}
	}
}
