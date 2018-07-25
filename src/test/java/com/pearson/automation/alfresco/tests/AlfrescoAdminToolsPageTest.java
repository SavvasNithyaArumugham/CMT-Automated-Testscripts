package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoAdminToolsPageTest extends ReusableLibrary {

	private AlfrescoAdminToolsPage appAdminToolsPg = new AlfrescoAdminToolsPage(
			scriptHelper);

	/*** Group Object ***/
	private String groupMgmtXpath = ".//*[@title='Group Management']";
	private String groupDOMXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search']";
	private String groupDOMXpathUserAdmin = ".//*[@id='page_x002e_ctool_x002e_group-admin-console_x0023_default']";
	private String displayNameXpath = ".//*[@class='yui-dt-data']/tr[1]/td[2]/div";
	private String userXpath = ".//*[@class='yui-carousel-content']//li[2]//a//span[contains(text(),'CRAFT')]";
	private String searchUserResXpath = ".//*[@class='search-main']//*[@class='yui-dt-data']//td[3]//div";
	private String subGpXpath = ".//*[@class='yui-carousel-content']//li[2]//a//span[text()='CRAFT']";
	private String groupBrowseBtnAsGrpAdminXpath = ".//*[@id='page_x002e_ctool_x002e_group-admin-console_x0023_default-browse-button-button']";
	private String groupFinderGrpAdminXpath = ".//*[@id='page_x002e_ctool_x002e_group-admin-console_x0023_default-search-text']";
	private String groupListXpath = ".//*[@class='yui-carousel-element']//li//a";
	public String tempeditgpXpath = ".//*[@class='yui-carousel-element']//li//a/span[text()='CRAFT']";
	private String grpUserXpath = ".//*[@id='yui-gen45']/div[2]/a/span[contains(text(),'CRAFT')]";
	private String grpUserColumnXpath = ".//*[@class='yui-carousel-element']//li[2]/div[1]/span";
	private String addUserIconXpath = ".//*[@title='Add User']";
	private String delUserIconXpath = ".//*[@id='yui-gen45']/div[2]/a/span[contains(text(),'CRAFT')]//ancestor::a/span[2]";
	private String adminOptionsXpath = ".//*[@id='alf-filters']";
	private String adminListXpath = ".//*[contains(@id,'admin-console_x0023_default-body')]/ul/li/span";
	public ArrayList<String> groupList = new ArrayList<String>();
	public ArrayList<String> userList = new ArrayList<String>();
	public ArrayList<String> optionList = new ArrayList<String>();
	/*** Delete Group ***/
	private String displayResultXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-bar-text']";

	/*** Transformation Server ***/
	private String transServerURLXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-undefined-alf-id0-form-fields']//a";

	/*** Tag Manager Obj ***/
	private String tagMgnDOMXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-tags']";
	private String edittagMsgXpath = ".//*[@class='message']";
	private String addedtagXpath = ".//*[@id='template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cm_taggable-cntrl-currentValueDisplay']/div";
	public String Result = "";

	/*** Category Manager ***/
	private String categoryDOMXpath = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default']";
	private String categoryAddDOMXpath = ".//*[@id='template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-editDetails-alf-id50_prop_cm_categories-cntrl-picker-results']";
	private String categoryMsgXpath = ".//*[@id='message']/div";
	public static String actualValue;
	
	/*** Users column ***/
	private String UsersXpath = ".//*[@class='yui-carousel-content']//li[2]//a//span[contains(text(),'CRAFT')]";
	private String usrProfileXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-view-title']";
	public ArrayList<String> usrList = new ArrayList<String>();
	private String searchTxtAsUserAdmin = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-search-text']";
	private String searchTxtInUsers = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-text']";
	private String searchBtnAsUserAdmin = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-search-button-button']";
	private String searchBtnInUsers = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button']";
	private String newuserAsUserAdmin = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-newuser-button-button']";
	private String newuserAsAdmin = ".//*[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search']/div[2]";
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
	private String finalMailXPath = ".//*[@id='page_x002e_ctool_x002e_user-admin-console_x0023_default-view']/div[2]/div[5]/span[2]";


	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoAdminToolsPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void VerifyNewGroupandUser() {
		try {
			String GroupName = dataTable.getData("Admin", "GroupName");
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");
			String newUser = dataTable.getData("Admin", "Adduser");
			String ActualGpName = appAdminToolsPg.tempeditgpXpath.replace(
					"CRAFT", GroupName);
			String finalUserXpath = userXpath.replace("CRAFT", newUser);
			WebElement groupDom = UIHelper.findAnElementbyXpath(driver,
					groupDOMXpath);
			if (groupDom.getText().contains(GroupName)
					&& groupDom.getText().contains(gpIdentifier)
					&& groupDom.getText().contains(newUser)) {
				report.updateTestLog(
						"Verify New Group and User Add",
						"Verify New Group and User Add successfully"
								+ "<br/><b>Actual Sub Gp : </b> "
								+ UIHelper.findAnElementbyXpath(driver,
										ActualGpName).getText()
								+ "<br /><b> Actual User details: </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										finalUserXpath).getText(), Status.PASS);
			} else {
				report.updateTestLog("Verify New Group and User Add",
						"Verify New Group and User Add Failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify New Group and User Add",
					"Verify New Group and User Add Failed", Status.FAIL);
		}
	}

	public void VerifyCreateSubgroup() {

		String subGpName = dataTable.getData("Admin", "SubGroup");
		String subGpIdentifer = dataTable.getData("Admin", "SubGpIdentifier");
		WebElement groupDom = UIHelper.findAnElementbyXpath(driver,
				groupDOMXpath);
		String ActualGpName = appAdminToolsPg.tempeditgpXpath.replace("CRAFT",
				subGpName);
		if (groupDom.getText().contains(subGpName)
				&& groupDom.getText().contains(subGpIdentifer)) {
			report.updateTestLog(
					"Verify New Sub Add",
					"Verify New Sub Added successfully"
							+ "<br/><b>Excpected Sub Gp : </b> "
							+ subGpName
							+ "<br/><b>Actual Sub Gp : </b> "
							+ UIHelper.findAnElementbyXpath(driver,
									ActualGpName).getText(), Status.PASS);
		} else {
			report.updateTestLog("Verify New Sub Add",
					"Verify New Sub Added Failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 */
	public void VerifyCreateSubgroupUserAdmin() {

		String subGpName = dataTable.getData("Admin", "SubGroup");
		String subGpIdentifer = dataTable.getData("Admin", "SubGpIdentifier");
		WebElement groupDom = UIHelper.findAnElementbyXpath(driver,
				groupDOMXpathUserAdmin);
		String ActualGpName = appAdminToolsPg.tempeditgpXpath.replace("CRAFT",
				subGpName);
		if (groupDom.getText().contains(subGpName)
				&& groupDom.getText().contains(subGpIdentifer)) {
			report.updateTestLog(
					"Verify New Sub Group added",
					"New Sub Group Added successfully"
							+ "<br/><b>Excpected Sub Gp : </b> "
							+ subGpName
							+ "<br/><b>Actual Sub Gp : </b> "
							+ UIHelper.findAnElementbyXpath(driver,
									ActualGpName).getText(), Status.PASS);
		} else {
			report.updateTestLog("Verify New Sub Group added",
					"New Sub Group added Failed", Status.FAIL);
		}
	}

	public void VerifyTransformationServer() {
		try {
			if (UIHelper.findAnElementbyXpath(driver, transServerURLXpath)
					.isDisplayed()) {
				report.updateTestLog(
						"View Transformation server details ",
						"View Transformation server details Successful."
								+ "<br /><b> Server URL : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										transServerURLXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("View Transformation server details ",
						"View Transformation server details Failed.",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("View Transformation server details ",
					"View Transformation server details Failed.", Status.FAIL);
		}
	}

	public void VerifyDeleteSubgroup() {
		String ResultName = UIHelper.findAnElementbyXpath(driver,
				displayResultXpath).getText();

		if (ResultName.contains("No results")) {
			report.updateTestLog("Verify Delete Group",
					"Delete Group successfully.", Status.PASS);
		} else {
			report.updateTestLog("Verify Delete Group", "Delete Group Failed.",
					Status.FAIL);
		}
	}

	public void VerifyAddSubGroup() {
		String GroupName = dataTable.getData("Admin", "GroupName");
		String subGp = dataTable.getData("Admin", "SubGroup");
		String result = subGpXpath.replace("CRAFT", subGp);
		WebElement groupDom = UIHelper.findAnElementbyXpath(driver,
				groupDOMXpath);
		if (groupDom.getText().contains(GroupName)
				&& groupDom.getText().contains(subGp)) {
			report.updateTestLog("Verify Add Sub Group",
					"Verify Add Sub Group successfully"
							+ "<br /><b>Expected SubGp :</b>"
							+ subGp
							+ "<br /><b>Actual Sub Gp : </b>"
							+ UIHelper.findAnElementbyXpath(driver, result)
									.getText(), Status.PASS);
		} else {
			report.updateTestLog("Verify Add Sub Group",
					"Verify Add Sub Group Failed.", Status.FAIL);
		}
	}

	public void VerifyNewUser() {
		String newUser = dataTable.getData("Admin", "Adduser");
		WebElement groupDom = UIHelper.findAnElementbyXpath(driver,
				groupDOMXpath);
		String finalUserXpath = userXpath.replace("CRAFT", newUser);
		if (groupDom.getText().contains(newUser)) {
			report.updateTestLog("Verify New  User Add",
					"Verify New User Add successfully"
							+ "<br /><b> Expected User : </b>"
							+ newUser
							+ "<br /><b> Actual User details: </b>"
							+ UIHelper.findAnElementbyXpath(driver, finalUserXpath)
									.getText(), Status.PASS);
		} else {
			report.updateTestLog("Verify New  User Add",
					"Verify New User Add Failed", Status.FAIL);
		}
	}

	public void VerifyNewGroup() {
		String groupName = dataTable.getData("Admin", "GroupName");
		try {

			WebElement groupDom = UIHelper.findAnElementbyXpath(driver,
					groupDOMXpath);
			String ActualGpName = appAdminToolsPg.tempeditgpXpath.replace(
					"CRAFT", groupName);
			if (groupDom.getText().contains(groupName)) {
				report.updateTestLog(
						"Verify New Group",
						"Verify New Group successfully"
								+ "<br /><b> Expected Group Name : </b>"
								+ groupName
								+ "<br /><b> Actual Group Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										ActualGpName).getText(), Status.PASS);
			} else {
				report.updateTestLog("Verify New Group",
						"Verify New Group Failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify New Group", "Verify New Group Failed",
					Status.FAIL);
		}
	}

	public void VerifySearchGroup() {
		String groupName = dataTable.getData("Admin", "GroupName");
		try {

			WebElement groupDom = UIHelper.findAnElementbyXpath(driver,
					groupDOMXpath);
			if (groupDom.getText().contains(groupName)) {
				report.updateTestLog(
						"Verify search Group",
						"Verify search Group successfully"
								+ "<br /><b> Expected Group Name : </b>"
								+ groupName
								+ "<br /><b> Actual Group Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										displayNameXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify search Group",
						"Verify search Group Failed.Group not found",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify search Group",
					"Verify search Group Failed.Group not found", Status.FAIL);
		}
	}

	public void VerifyUser() {
		String userName = dataTable.getData("Admin", "Adduser");

		if (UIHelper.findAnElementbyXpath(driver, searchUserResXpath).getText()
				.contains(userName)) {
			report.updateTestLog(
					"Verify New Group",
					"Verify New Group successfully"
							+ "<br /><b> Expected Group Name : </b>"
							+ userName
							+ "<br /><b> Actual Group Name : </b>"
							+ UIHelper.findAnElementbyXpath(driver,
									searchUserResXpath).getText(), Status.PASS);
		} else {
			report.updateTestLog("Verify New Group", "Verify New Group Failed",
					Status.FAIL);
		}
	}

	public void VerifyEditGroup() {
		String EditGpName = dataTable.getData("Admin", "EditGroupName");
		WebElement groupDom = UIHelper.findAnElementbyXpath(driver,
				groupDOMXpath);
		if (groupDom.getText().contains(EditGpName)) {
			report.updateTestLog(
					"Verify Edit Group",
					"Verify Edit Group successfully"
							+ "<br /><b> Expected Group Name : <b>"
							+ EditGpName
							+ "<br /><b>Actual Group Name : <b>"
							+ UIHelper.findAnElementbyXpath(driver,
									displayNameXpath).getText(), Status.PASS);
		} else {
			report.updateTestLog("Verify Edit Group",
					"Verify Edit Group Failed", Status.FAIL);
		}
	}

	public void VerifyEditTag() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, edittagMsgXpath);
			String tagName = dataTable.getData("Admin", "EditTagName");
			Result = UIHelper.findAnElementbyXpath(driver, edittagMsgXpath)
					.getText();
			if (Result.contains("Tag updated")) {
				report.updateTestLog("Verify Edit Tag",
						"Verify Edit Tag successfully"
								+ "<br /><b> Actual Tag Name </b>" + "tc38",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Edit Tag",
						"Verify Edit Tag Failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Edit Tag", "Verify Edit Tag Failed",
					Status.FAIL);
		}
	}

	public void VerifySearchTag() {
		try {
			String tagName = dataTable.getData("Admin", "TagName");
			String finalTagEditXpath = appAdminToolsPg.tempTagXpath.replace(
					"CRAFT", tagName);
			if (UIHelper.findAnElementbyXpath(driver, tagMgnDOMXpath).getText()
					.contains(tagName)) {
				report.updateTestLog(
						"Verify Search Tag",
						"Verify search Tag successfully"
								+ "<br /><b> Actual Tag Name </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										finalTagEditXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Search Tag",
						"Verify search Tag Failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Create Tag", "Create Tag failed.",
					Status.FAIL);
		}
	}

	public void VerifyDeleteTag() {
		String tagName = dataTable.getData("Admin", "TagName");
		UIHelper.waitFor(driver);
		if (UIHelper.findAnElementbyXpath(driver, tagMgnDOMXpath).isDisplayed()) {
			report.updateTestLog("Verify Delete Tag",
					"Verify Delete Tag Failed", Status.FAIL);
		} else {

			report.updateTestLog("Verify Delete Tag",
					"Verify Delete Tag successfully"
							+ "<br /><b> Delete Tag : </b>" + tagName,
					Status.PASS);
		}
	}

	public void VerifyCreateTag() {
		try {
			String tagName = dataTable.getData("Admin", "TagName");
			if (UIHelper.findAnElementbyXpath(driver, addedtagXpath).getText()
					.contains(tagName)) {
				report.updateTestLog(
						"Verify Create Tag",
						"Verify Create Tag successfully"
								+ "<br /><b> Expected Tag Name : </b>"
								+ tagName
								+ "<br /><b> Actual Tag Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										addedtagXpath).getText(), Status.PASS);
			} else {
				report.updateTestLog("Verify Create Tag",
						"Verify Create Tag Failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Create Tag", "Create Tag failed.",
					Status.FAIL);
		}
	}

	public void VerifyEditCategory() {
		try {
			String editCatName = dataTable.getData("Admin", "EditCategoryName");
			String finalCategoryNameXpath = appAdminToolsPg.categoryNameXpath
					.replace("CRAFT", editCatName);
			if (UIHelper.findAnElementbyXpath(driver, categoryDOMXpath)
					.getText().contains(editCatName)) {
				report.updateTestLog(
						"Verify Edit Category",
						"Verify Edit Category successfully"
								+ "<br /><b> Expected Category : </b>"
								+ editCatName
								+ "<br /><b> Actual Category : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										finalCategoryNameXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Edit Category",
						"Verify Edit Category Failed.Category not found",
						Status.FAIL);
			}
		} catch (Exception e) {

			report.updateTestLog("Verify Edit Category",
					"Verify Edit Category Failed.Category not found",
					Status.FAIL);
		}
	}

	public void VerifyAddCategory() {
		try {
			String categoryName = dataTable.getData("Admin", "CategoryName");
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, categoryMsgXpath);
			// String Result = UIHelper.findAnElementbyXpath(driver,
			// categoryMsgXpath).getText();
			String editCatName = dataTable.getData("Admin", "EditCategoryName");
			String finalCategoryNameXpath = appAdminToolsPg.categoryNameXpath
					.replace("CRAFT", categoryName);
			// if (Result.contains("Successfully"))
			if (UIHelper.findAnElementbyXpath(driver, finalCategoryNameXpath)
					.getText().contains(editCatName)) {
				report.updateTestLog(
						"Verify Add Category",
						"Verify Add Category successfully"
								+ "<br /><b> Expected Category : </b>"
								+ categoryName
								+ "<br /><b> Actual Category : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										finalCategoryNameXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Add Category",
						"Verify Add Category Failed.Category not found",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Add Category",
					"Verify Add Category Failed.Category not found",
					Status.FAIL);
		}
	}

	public void VerifyDeleteCategory() {

		String categoryName = dataTable.getData("Admin", "CategoryName");
		UIHelper.waitFor(driver);
		if (UIHelper.findAnElementbyXpath(driver, categoryDOMXpath).getText()
				.contains(categoryName)) {
			report.updateTestLog("Verify Delete Category",
					"Verify Delete Category Failed", Status.FAIL);
		} else {

			report.updateTestLog("Verify Delete Category",
					"Verify Delete Category successfully", Status.PASS);
		}
	}

	/**
	 * Method to verify the 'Admin Tools' options menu available or not
	 * 
	 * @param optionsLinkName
	 *            - It contains Menu option link name
	 * @author 412766
	 */
	public void verifyAdminToolsOptionsLink(String optionsLinkName) {
		try {
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isAdminOtionsLinkAvailable(optionsLinkName)) {
				report.updateTestLog("Verify the '" + optionsLinkName
						+ "' option under Admin tools", "'" + optionsLinkName
						+ "' option verifyed successfully."
						+ "<br /><b>Expected Result:</b> " + optionsLinkName
						+ ", <br /><b>Actual Result:</b> "
						+ AlfrescoAdminToolsPage.getActualValue() + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the '" + optionsLinkName
						+ "' option under Admin tools", "'" + optionsLinkName
						+ "' option not available."
						+ "<br /><b>Expected Result:</b> " + optionsLinkName
						+ ", <br /><b>Actual Result:</b> "
						+ AlfrescoAdminToolsPage.getActualValue() + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the '" + optionsLinkName
					+ "' option under Admin tools", "Verify the '"
					+ optionsLinkName + "' option Failed", Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Admin Tools' Tab available or not
	 * 
	 * @author 412766
	 */
	public void verifyAdminTools() {
		try {
			if (new AlfrescoHomePage(scriptHelper).isAdminToolsAvailable()) {
				report.updateTestLog(
						"Verify the 'Admin Tools' option for Non-Admin user",
						"'Admin Tools' option available for Non-Admin user."
								+ "<br /><b>Expected Result:</b>No Admin Tools option, "
								+ "<br /><b>Actual Result:</b>Admin Tools option available",
						Status.FAIL);
			} else {
				report.updateTestLog(
						"Verify the 'Admin Tools' option for Non-Admin user",
						"'Admin Tools' option not available for Non-Admin user."
								+ "<br /><b>Expected Result:</b>No Admin Tools option, "
								+ "<br /><b>Actual Result:</b>No Admin Tools option",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the 'Admin Tools' option for Non-Admin user",
					"Verify the 'Admin Tools' option Failed", Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Link Relationship' header displayed in 'Link
	 * Relationship' menu or not.
	 * 
	 * @author 412766
	 */
	public void verifyLinkRelationshipHeader() {
		try {
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isLinkRelationshipHeaderDisplayed()) {
				report.updateTestLog(
						"Verify the Link Relationship Header",
						"Link Relationship Header verifyed successfully"
								+ ""
								+ "<br /><b>Expected Result:</b> Link Relationship, <br /><b>Actual Result:</b> "
								+ AlfrescoAdminToolsPage.getActualValue() + "",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the Link Relationship Header",
						"Link Relationship Header not available"
								+ ""
								+ "<br /><b>Expected Result:</b> Link Relationship, <br /><b>Actual Result:</b> "
								+ AlfrescoAdminToolsPage.getActualValue() + "",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify the Link Relationship Header",
					"Verify the Link Relationship Header Failed", Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Relationship' Button displayed in 'Link
	 * Relationship' menu or not.
	 * 
	 * @author 412766
	 */
	public void verifyNewRelationshipButton() {
		try {
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isRelationShipButtonDisplayed()) {
				report.updateTestLog(
						"Verify the New Relationship Button",
						"New Relationship Button verifyed successfully"
								+ ""
								+ "<br /><b>Expected Result:</b> New Relationship, <br /><b>Actual Result:</b> New Relationship Button",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the New Relationship Button",
						"New Relationship Button not available"
								+ ""
								+ "<br /><b>Expected Result:</b> New Relationship Button, <br /><b>Actual Result:</b> New Relationship Button not displayed",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the New Relationship Button",
					"Verify the New Relationship Button Failed", Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Edit' button List displayed in 'Link Relationship'
	 * menu or not.
	 * 
	 * @author 412766
	 */
	public void verifyEditButonList() {
		try {
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isEditButtonsDisplayed()) {
				report.updateTestLog(
						"Verify the Edit Button List in Link Relationship",
						"Edit Button List verifyed successfully"
								+ ""
								+ "<br /><b>Expected Result:</b>Edit Button, <br /><b>Actual Result:</b> Edit Button",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the Edit Button List in Link Relationship",
						"Edit Button List not available"
								+ ""
								+ "<br /><b>Expected Result:</b>Edit, <br /><b>Actual Result:</b> Edit Button not displayed",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the Edit Button List in Link Relationship",
					"Verify the Edit Button List in Link Relationship Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method to verify the All Elements displayed in the 'New Relationship'
	 * panel.
	 * 
	 * @author 412766
	 */
	public void verifyAllElementsDisplayedinNewRelationshipPanel() {
		try {
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isAllElementsDisplayedInNewRelationshipPanel()) {
				report.updateTestLog(
						"Verify the Elements displayed in New Relationship Panel",
						"All Elements Displayed successfully"
								+ ""
								+ "<br /><b>Expected Result:</b>To display All Elements, <br /><b>Actual Result:</b> All Elements Displayed",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the Elements displayed in New Relationship Panel",
						"All Elements not Displayed"
								+ ""
								+ "<br /><b>Expected Result:</b>To display All Elements, <br /><b>Actual Result:</b> Elements not Displayed",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the Elements displayed in New Relationship Panel",
					"Verify the Elements displayed in New Relationship Panel Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Bi-directional' Association created or not in 'New
	 * Relationship' menu.
	 * 
	 * @author 412766
	 */
	public void verifyBiDirectionalAssociationCreated() {
		try {
			String lable = dataTable.getData("Admin", "Bi-Directional_Label");
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isRelationShipCreated()) {
				report.updateTestLog(
						"Verify the New Bi-Directional Association created",
						"New Bi-Directional Association verified successfully"
								+ "" + "<br /><b>Expected Result:</b>" + lable
								+ ", <br /><b>Actual Result:</b> " + lable + "",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the New Bi-Directional Association created",
						"New Bi-Directional Association not verified" + ""
								+ "<br /><b>Expected Result:</b>" + lable
								+ ", <br /><b>Actual Result:</b> Not created",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the New Bi-Directional Association created",
					"Verify the New Bi-Directional Association created Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Parent/Child' Association created or not in 'New
	 * Relationship' menu.
	 * 
	 * @author 412766
	 */
	public void verifyParentChildAssociationCreated() {
		try {
			String source = dataTable.getData("Admin", "Parent/Child_Source");
			String target = dataTable.getData("Admin", "Parent/Child_Target");
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isRelationShipCreated()) {
				report.updateTestLog(
						"Verify the New Parent/Child Association created",
						"Created successfully", Status.PASS);

				report.updateTestLog(
						"Verify the New Parent/Child Association created",
						"New Parent/Child Association verified successfully"
								+ "" + "<br /><b>Expected Result:</b>" + source
								+ " and " + target
								+ ", <br /><b>Actual Result:</b> " + source
								+ " and " + target + "", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the New Parent/Child Association created",
						"New Parent/Child Association not verified" + ""
								+ "<br /><b>Expected Result:</b>" + source
								+ " and " + target
								+ ", <br /><b>Actual Result:</b> Not created",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the New Parent/Child Association created",
					"Verify the New Parent/Child Association created Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method to verify the data provided in the fields of 'New Relationship'
	 * menu.
	 * 
	 * @author 412766
	 */
	public void verifyEmptyDataInNewRelationship() {
		try {
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.checkEmptyDataInNewRelationship()) {
				report.updateTestLog(
						"Verify New Bi-drectional or "
								+ "parent child Association not created without providing data",
						"<br><b>Expected Result:</b>Without Data New Relationship should not be created"
								+ "<br><b>Actual Result:</b> New Relationship is not created",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify New Bi-drectional or "
								+ "parent child Association not created without providing data",
						"<br><b>Expected Result:</b>Without Data New Relationship should not be created"
								+ "<br><b>Actual Result:</b> New Relationship is created",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify that New Bi-drectional or "
							+ "parent child Association not created without providing data",
					"Verify that New Bi-drectional or "
							+ "parent child Association created without providing data Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Bi-directional' Association updated or not.
	 * 
	 * @author 412766
	 */
	public void verifyBiDirectionalAssociationUpdated() {
		try {
			String lable = dataTable.getData("Admin",
					"Bi-Directional_Label_Update");
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isRelationShipCreated()) {
				report.updateTestLog(
						"Verify the Bi-Directional Association updated",
						"Updated Bi-Directional Association verified successfully"
								+ "" + "<br /><b>Expected Result:</b>" + lable
								+ ", <br /><b>Actual Result:</b> " + lable + "",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the Bi-Directional Association updated",
						"Updated Bi-Directional Association not verified" + ""
								+ "<br /><b>Expected Result:</b>" + lable
								+ ", <br /><b>Actual Result:</b> Not updated",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the Bi-Directional Association updated",
					"Verify the Bi-Directional Association updated Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Parent/Child' Association updated or not.
	 * 
	 * @author 412766
	 */
	public void verifyParentChildAssociationUpdated() {
		try {
			String source = dataTable.getData("Admin",
					"Parent/Child_Source_Update");
			String target = dataTable.getData("Admin",
					"Parent/Child_Target_Update");
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isRelationShipCreated()) {
				report.updateTestLog(
						"Verify the Parent/Child Association updated",
						"Updated Parent/Child Association verified successfully"
								+ "" + "<br /><b>Expected Result:</b>" + source
								+ " and " + target
								+ ", <br /><b>Actual Result:</b> " + source
								+ " and " + target + "", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the Parent/Child Association updated",
						"Updated Parent/Child Association not verified" + ""
								+ "<br /><b>Expected Result:</b>" + source
								+ " and " + target
								+ ", <br /><b>Actual Result:</b> Not updated",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the Parent/Child Association updated",
					"Verify the Parent/Child Association updated Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Application Tool' menu loaded by default in 'Admin
	 * Tools' page.
	 * 
	 * @author 412766
	 */
	public void verifyApplicationToolLoadedByDefault() {
		try {
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isApplicationToolHeaderDisplayed()) {
				report.updateTestLog(
						"Verify Application option selected by Default",
						"Application option Selected by Default successfully"
								+ "<br /><b>Expected Result:</b>To disply Application option by default, <br /><b>Actual Result:</b> Application option selected default",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Application option selected by Default",
						"Application option not Selected by Default"
								+ "<br /><b>Expected Result:</b>To select Application option by default, <br /><b>Actual Result:</b> Application option not selected default",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify Application option selected by Default",
					"Verify Application option selected by Default Failed",
					Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Admin Tools' options available or not
	 * 
	 * @author 412766
	 */
	public void verifyAdminToolsOptionsAvailable() {
		try {
			if (new AlfrescoAdminToolsPage(scriptHelper)
					.isAdminToolsOptionsAvailable()) {
				report.updateTestLog(
						"Verify Admin Tools options available",
						"All Admin tools Options available"
								+ "<br /><b>Expected Result:</b>All Admin tools options, <br /><b>Actual Result:</b>All Admin tools options available",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Admin Tools options available",
						"All Admin tools Options not available"
								+ "<br /><b>Expected Result:</b>All Admin tools options, <br /><b>Actual Result:</b>All Admin tools options not available",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Admin Tools options available",
					"Verify Admin Tools options available Failed", Status.FAIL);
		}
	}

	/**
	 * Method to verify the 'Theme' applied or not in 'Admin Tools' menu.
	 * 
	 * @author 412766
	 */
	public void verifyThemeApplied() {
		try {
			String themeName = dataTable.getData("Admin", "Theme_Name");
			if (new AlfrescoAdminToolsPage(scriptHelper).isThemeApplied()) {
				report.updateTestLog("Verify the applied Theme", "'"
						+ themeName + ", Theme verified successfully"
						+ "<br /><b>Expected Result:</b>" + themeName
						+ ", <br /><b>Actual Result:</b>" + themeName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify the applied Theme", "'"
						+ themeName + ", Theme not verified"
						+ "<br /><b>Expected Result:</b>" + themeName
						+ ", <br /><b>Actual Result:</b> Theme Not Applied",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the applied Theme",
					"Verify the applied Theme Failed", Status.FAIL);
		}
	}

	// To Verify UserName is in CAPS or not
	public void VerifyUserNameInCaps(String expectedUserName) {
		String actualUserName = UIHelper.findAnElementbyXpath(driver,
				searchUserResXpath).getText();
		UIHelper.highlightElement(driver, searchUserResXpath);
		if (actualUserName.contains(expectedUserName)) {
			boolean flag = false;
			for (int i = 0; i < actualUserName.length(); i++) {
				char c = actualUserName.charAt(i);
				if (Character.isUpperCase(c))
					flag = true;

			}
			if (flag) {
				report.updateTestLog("Verify UserName",
						"UserName is in UPPERCASE"
								+ "<br /><b> Expected User Name : </b>"
								+ expectedUserName
								+ "<br /><b> Actual User Name : </b>"
								+ actualUserName, Status.PASS);
			} else {
				report.updateTestLog("Verify UserName",
						"UserName is Not in UPPERCASE"
								+ "<br /><b> Expected User Name : </b>"
								+ expectedUserName
								+ "<br /><b> Actual User Name : </b>"
								+ actualUserName, Status.PASS);
			}
		} else {
			report.updateTestLog("Verify UserName", "Verify User Name Failed",
					Status.FAIL);
		}
	}

	// Verify new group
	public void VerifyNewGroupCreated(String Group) {
		try {
			UIHelper.click(driver, groupMgmtXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					groupBrowseBtnAsGrpAdminXpath);
			UIHelper.highlightElement(driver, groupFinderGrpAdminXpath);
			UIHelper.sendKeysToAnElementByXpath(driver,
					groupFinderGrpAdminXpath, Group);
			UIHelper.click(driver, groupBrowseBtnAsGrpAdminXpath);
			UIHelper.waitFor(driver);
			String gpIdentifier = dataTable.getData("Admin", "GpIdentifier");

			if (UIHelper.findAnElementbyXpath(driver, groupListXpath).getText()
					.contains(Group)) {

				UIHelper.highlightElement(driver,
						UIHelper.findAnElementbyXpath(driver, groupListXpath));

				report.updateTestLog("Browsed Group",
						"New Group found successfully."
								+ "<br /><b> Expected Group Name : </b>"
								+ Group + "<br /><b> Actual Group Name : </b>"
								+ Group + " (" + gpIdentifier + ")",
						Status.PASS);

			} else {
				report.updateTestLog("Browsed  Group", "New Group not found."
						+ "<br /><b>Group Name : </b>" + Group, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Browsed  Group", "Browsed Group not found."
					+ "<br /><b>Group Name : </b>" + Group, Status.FAIL);
		}
	}

	// login as Group admin- Is Add user icon available in groups tab
	public void isadUserIconNotAvailAsGrpAdmin() {
		try {
			UIHelper.findandAddElementsToaList(driver, groupListXpath,
					groupList);
			String GroupName = dataTable.getData("Admin", "GroupName");
			String newUser = dataTable.getData("Admin", "Adduser");
			for (String group : groupList) {
				if (group.contains(GroupName)) {
					String finaleditgpXpath = tempeditgpXpath.replace("CRAFT",
							GroupName);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							finaleditgpXpath);
					UIHelper.click(driver, finaleditgpXpath);
					UIHelper.waitFor(driver);

					UIHelper.findandAddElementsToaList(driver,
							grpUserColumnXpath, userList);
					for (String element : userList) {
						if (element.contains(addUserIconXpath))
						{
							report.updateTestLog("Verify add user icon",
									"Add user icon is available"
											+ "<br /><b>Group Name : <b>"
											+ GroupName + "<br /><b>User : <b>"
											+ newUser, Status.FAIL);
						} else {
							report.updateTestLog("Verify add user icon",
									"Add user icon is not available"
											+ "<br /><b>Group Name : <b>"
											+ GroupName + "<br /><b>User : <b>"
											+ newUser, Status.PASS);
						}
					}
				}
			}

		} catch (Exception e) {
			report.updateTestLog("Verify add user icon",
					"Add user icon is available", Status.FAIL);
		}

	}

	// login as Group admin- Is Delete user icon available in groups tab
	public void isDelUserIconNotAvailAsGrpAdmin() {
		try {
			UIHelper.findandAddElementsToaList(driver, groupListXpath,
					groupList);
			String GroupName = dataTable.getData("Admin", "GroupName");
			String newUser = dataTable.getData("Admin", "Adduser");
			for (String group : groupList) {
				if (group.contains(GroupName)) {
					String finaleditgpXpath = tempeditgpXpath.replace("CRAFT",
							GroupName);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							finaleditgpXpath);
					UIHelper.click(driver, finaleditgpXpath);
					UIHelper.waitFor(driver);

					String finalGrpUserXpath = grpUserXpath.replace("CRAFT",
							newUser);
					String finalDelUserXpath = delUserIconXpath.replace(
							"CRAFT", newUser);

					UIHelper.click(driver, finalGrpUserXpath);
					UIHelper.highlightElement(driver, finalGrpUserXpath);
					UIHelper.waitFor(driver);

					if (UIHelper.isWebElementDisplayed(UIHelper
							.findAnElementbyXpath(driver, finalDelUserXpath))) {
						report.updateTestLog(
								"Verify Delete user option for Group_Admin",
								"Delete user icon is available"
										+ "<br /><b>Group Name : <b>"
										+ GroupName + "<br /><b>User : <b>"
										+ newUser, Status.FAIL);
					} else {
						report.updateTestLog(
								"Verify delete user option for Group_Admin",
								"Delete user icon is not available"
										+ "<br /><b>Group Name : <b>"
										+ GroupName + "<br /><b>User : <b>"
										+ newUser, Status.PASS);

					}
				}
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Delete user option for Group_Admin",
					"Verify Delete user option for Group_Admin Failed",
					Status.FAIL);
		}

	}

	// Navigate group page as group admin
	public void isUserMgmtAvailable() {

		try {
			UIHelper.highlightElement(driver, adminOptionsXpath);
			UIHelper.findandAddElementsToaList(driver, adminListXpath,
					optionList);
			String GroupName = dataTable.getData("Admin", "GroupName");
			for (String option : optionList) {
				if (option.contains(GroupName)) {
					report.updateTestLog(
							"Verify User management to create a new user",
							"User management is available", Status.FAIL);
				} else {

					report.updateTestLog(
							"Verify User management to create a new user",
							"User management is not available", Status.PASS);
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Delete user option for Group_Admin",
					"Verify Delete user option for Group_Admin Failed",
					Status.FAIL);
		}

	}
	
	// Navigate Users page as USER admin
		public void verifyUserMgmtAvailable() {

			try {
				UIHelper.highlightElement(driver, adminOptionsXpath);
				UIHelper.findandAddElementsToaList(driver, adminListXpath,
						optionList);
				String GroupName = dataTable.getData("Admin", "GroupName");
				for (String option : optionList) {
					if (option.contains(GroupName)) {
						report.updateTestLog("Verify User management",
								"User management is available", Status.PASS);

					} else {

						report.updateTestLog("Verify User management",
								"User management is not available", Status.FAIL);
					}
				}
			} catch (Exception e) {
				report.updateTestLog("Verify User management",
						"Verify USER_Admin Failed", Status.FAIL);
			}

		}

		// Verify created user as USER admin
		public void verifyCreatedUser(String User) {

			try {

				UIHelper.waitForVisibilityOfEleByXpath(driver, searchTxtAsUserAdmin);
				UIHelper.highlightElement(driver, searchTxtAsUserAdmin);
				UIHelper.findAnElementbyXpath(driver, searchTxtAsUserAdmin).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, searchTxtAsUserAdmin,
						User);
				UIHelper.click(driver, searchBtnAsUserAdmin);
				UIHelper.waitForVisibilityOfEleByXpath(driver, userListXPath);
				UIHelper.findandAddElementsToaList(driver, userListXPath, usrList);
				for (String usr : usrList) {
					if (usr.contains(usr)) {
						UIHelper.waitFor(driver);
						UIHelper.highlightElement(driver, usr);
						UIHelper.waitFor(driver);
						report.updateTestLog("Verify Created User",
								"Created User successfully"
										+ "<br /><b> User : </b>" + usr,
								Status.PASS);
					} else {

						report.updateTestLog("Verify Created User",
								"User is not availble" + "<br /><b> User : </b>"
										+ User, Status.FAIL);
					}
				}

			} catch (Exception e) {
				report.updateTestLog("Verify Created User",
						"Failed to create new user", Status.FAIL);
			}

		}

		public void verifyDeletedUser(String User) {

			try {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, searchTxtInUsers);
				UIHelper.findAnElementbyXpath(driver, searchTxtInUsers).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, searchTxtInUsers, User);
				UIHelper.click(driver, searchBtnInUsers);
				/* System.out.println(usrList.size()); */

				if (UIHelper.checkForAnElementbyXpath(driver, userListXPath)) {

					UIHelper.findandAddElementsToaList(driver, userListXPath,
							usrList);
					for (String usr : usrList) {
						if (usr.contains(usr)) {
							report.updateTestLog("Verify Deleted User",
									"User is not Deleted" + "<br /><b> User : </b>"
											+ usr, Status.FAIL);
						} else {
							report.updateTestLog("Verify Deleted User",
									"User is deleted" + "<br /><b> User : </b>"
											+ usr, Status.PASS);
						}
					}

				} else {

					report.updateTestLog("Verify Deleted User", "User is deleted"
							+ "<br /><b> User : </b>" + User, Status.PASS);
				}

			} catch (Exception e) {
				report.updateTestLog("Verify Deleted User",
						"Failed to Delete user", Status.FAIL);
			}

		}

		// Verify Edited user as USER admin
		public void verifyEditedUser() {

			try {

				UIHelper.waitFor(driver);
				String fName = dataTable.getData("UserName", "EditFirstName");
				String lName = dataTable.getData("UserName", "EditLastName");
				String eMail = dataTable.getData("UserName", "EditEmail");
				 String finalName = fName +" "+ lName; 
				 System.out.println("final Name : "+finalName);
				
				UIHelper.waitForVisibilityOfEleByXpath(driver, usrProfileXPath);
				if ((UIHelper.findAnElementbyXpath(
						driver, usrProfileXPath).getText().contains(finalName))) {
					
					UIHelper.highlightElement(driver, usrProfileXPath);
					UIHelper.highlightElement(driver, finalMailXPath);
					report.updateTestLog("Verify Edited User",
							"User updated successfully"
									+ "<br /><b> User name: </b>" + finalName,
							Status.PASS);
				}
				else {

					report.updateTestLog("Verify Edited User",
							"User is not updated" + "<br /><b> User Name: </b>"
									+ finalName, Status.FAIL);
				}

			} catch (Exception e) {
				report.updateTestLog("Verify Edited User", "Failed to update user",
						Status.FAIL);
			}

		}

	public void verifyNewRelationshipFields(ArrayList<String> expFields) {
		try {
		ArrayList<String> actualFields=appAdminToolsPg.getNewRelationshipPanelFields();
		if(actualFields.size()==expFields.size()) {
		   Collections.sort(expFields);	
		   Collections.sort(actualFields);
		   if(actualFields.equals(expFields)) {
			   report.updateTestLog("Verify New relationship fields", "New relationship field present as expected <br><b>Expected</b>" +expFields+"<br><b>Expected</b>Actual</b>"+actualFields,
						Status.PASS);
		   }else {
			   report.updateTestLog("Verify New relationship fields", "New relationship field present as not same as expected <br><b>Expected</b>" +expFields+"<br><b>Expected</b>Actual</b>"+actualFields,
						Status.FAIL); 
		   }
		}
		}catch(Exception e) {
			report.updateTestLog("Verify New relationship fields", "New relationship fields verification failed",Status.FAIL); 
		}
	}
	
	public void verifyEditRelationshipFields(ArrayList<String> expFields,String type1,String type2) {
		try {
		
		appAdminToolsPg.selectEditForAnyRelationType(type1);	
		ArrayList<String> actualFields=appAdminToolsPg.getNewRelationshipPanelFields();
		UIHelper.click(driver, appAdminToolsPg.newRelationshipCancelXpath);
		appAdminToolsPg.selectEditForAnyRelationType(type2);
		ArrayList<String> temp2actualField=appAdminToolsPg.getNewRelationshipPanelFields();
		actualFields.addAll(temp2actualField);
		
		Set<String> hs = new HashSet<>();
		hs.addAll(actualFields);
		actualFields.clear();
		actualFields.addAll(hs);
		
		if(actualFields.size()==expFields.size()) {
		   Collections.sort(expFields);	
		   Collections.sort(actualFields);
		   if(actualFields.equals(expFields)) {
			   report.updateTestLog("Verify Edit relationship fields", "Edit relationship field present as expected <br><b>Expected</b>" +expFields+"<br><b>Actual</b>"+actualFields,
						Status.PASS);
		   }else {
			   report.updateTestLog("Verify Edit relationship fields", "Edit relationship field present as not same as expected <br><b>Expected</b>" +expFields+"<br><b>Actual</b>"+actualFields,
						Status.FAIL); 
		   }
		}
		}catch(Exception e) {
			report.updateTestLog("Verify Edit relationship fields", "Edit relationship fields verification failed",Status.FAIL); 
		}
	}
	
	

}
