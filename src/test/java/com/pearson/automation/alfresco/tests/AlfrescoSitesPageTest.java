package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoSiteFinderPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.FileUtil;
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
public class AlfrescoSitesPageTest extends ReusableLibrary {

	private AlfrescoSitesPage appSitesPg = new AlfrescoSitesPage(scriptHelper);
	private AlfrescoSiteFinderPage siteFinderPage =new AlfrescoSiteFinderPage(scriptHelper);
	private ArrayList<String> createdSitesNameList = new ArrayList<String>();
	private boolean isDisplayedCreatedSite;
	private String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
	private boolean isDocumentLibraryDisplayed;
	private String documentLibXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']";
	private String editPropXpath = ".//*[@id='template_x002e_document-metadata_x002e_document-details_x0023_default']";
	private String userRoleDetailsFilePath = "src/test/resources/AppTestData/Sites/UserRoleDetails.txt";
	private String titleBarXpath = ".//*[@id='HEADER_TITLE_BAR']/header/div";
	private String titleXpath = ".//*[@id='HEADER_TITLE_BAR']/header/div";
	private String cancelbuttonXpath = ".//button[text()='Cancel']";
	
	public String disableduploadbtn = ".//button[contains(@id,'fileUpload-button') and @disabled='disabled']";

	private boolean isFileLocked;
	private boolean isUploadDisplayed;

	private String editOnlineXpath = ".//*[@id='onActionEditOnlineAos']";
	private String uploadVersionXpath = ".//*[@id='onActionUploadNewVersion']";
	private String editOfflineXpath = "//a/span[text()='Edit Offline']";
	private String siteTypeDpXpath = ".//table[@id='CREATE_SITE_FIELD_PRESET_CONTROL']//*[@role='option']";
	private String siteTypeDropdownXpath = ".//*[@id='CREATE_SITE_FIELD_PRESET_CONTROL_menu']//td[@class='dijitReset dijitMenuItemLabel']";

	private boolean isFileUnLocked;

	private boolean isDocumentMetaDataDisplayed;

	private boolean isDisplayedAspectInSelectedList;

	private boolean isDisplayedAttachLifecycle;

	private boolean isDisplayedMoreSettingsOption;

	private boolean isDisplayedAddRelationship;

	private String messageXpath = ".//*[@id='message']/div";

	private ArrayList<String> selectedItemsMenuValues = new ArrayList<String>();

	private boolean isDisplayedSelectedItemMenuOption;

	private ArrayList<String> siteNamesList = new ArrayList<String>();

	private ArrayList<String> expectedListOfCreateSitFieldLabels = new ArrayList<String>();
	private ArrayList<String> listOfCreateSitFieldLabels = new ArrayList<String>();
	private String splittedCreateSiteLabelNames[];

	private ArrayList<String> expectedListOfCreateSitTypeValues = new ArrayList<String>();
	private ArrayList<String> actuallistOfCreateSitTypeValues = new ArrayList<String>();

	private String createdSiteName;

	private boolean isDisplayedSiteNameInSFResults;

	private String inviteLinkXpath = ".//*[@class='sticky-wrapper']//a[contains(.,'Invite') and not(contains(.,'Pending Invites'))] | .//*[@class='sticky-wrapper']//a[contains(.,'Invite People')]";
	private String searchTextFieldXpath = ".//*[@class='sticky-wrapper']//*[@class='search-text']/input";
	private String searchButtonXpath = ".//*[@class='sticky-wrapper']//*[@class='search-button']//*[contains(.,'Search')]";
	private String tempAddBtnXpath = ".//*[@class='sticky-wrapper']//*[contains(@id,'yuievtautoid')]//tbody/tr//a[contains(.,'CRAFT')]//ancestor::tr/td//span/span[contains(.,'Add')]";
	private String contributorXpath = ".//*[@class='sticky-wrapper']//button[contains(.,'Select Role')]";
	private String tempContributorXpath = ".//*[@class='sticky-wrapper']//li/a[contains(.,'CRAFT')]";
	private String inviteButtonXpath = ".//*[@class='sticky-wrapper']//button[contains(.,'Invite')]";
	private String htmlBodyXpath = ".//*[@id='bd']";
	private String searchResultsXpath = ".//*[@id='template_x002e_people-finder_x002e_invite_x0023_default-results']";
	private String invitedContainerXpath = ".//*[@id='template_x002e_invitationlist_x002e_invite_x0023_default-invitationlistwrapper']";
	private String selectRoleContainerXpathForInvitedUser = ".//*[@id='template_x002e_invitationlist_x002e_invite_x0023_default-inviteelist']";

	private String siteHeaderName = "//*[@id='HEADER_TITLE']";

	private String btnDisableSiteFeedXPath = ".//button[@title='Disable Activity Feeds and Email Notifications for site CRAFT'][@name='disable']";
	private String btnEnableSiteFeedXPath = ".//button[@title='Enable Activity Feeds and Email Notifications for site CRAFT'][@name='enable']";
	private String inheritOffXpath = "//div[@class='inherited inherited-off']";
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoSitesPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Verify Created Site
	public void verifyCreatedSite() {
		try {
			String siteName = new FileUtil().readDataFromFile(testOutputFilePath);

			String isDisplayedSiteNameInHeader = UIHelper.getTextFromWebElement(driver, siteHeaderName);

			if (isDisplayedSiteNameInHeader.equalsIgnoreCase(siteName)) {
				report.updateTestLog("Verify Created Site", "Created Site:<b>" + siteName
						+ "</b> displayed Successfully</br><b>Created Site Name:</b>" + siteName, Status.PASS);
			} else {
				report.updateTestLog("Verify Created Site", "Failed to display created site", Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check if the user has access to asset in the moderated site
	public void verifyModeratedSiteAccess() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, documentLibXpath))) {
				UIHelper.highlightElement(driver, documentLibXpath);

				isDocumentLibraryDisplayed = true;
			} else {
				isDocumentLibraryDisplayed = false;
			}
			if (isDocumentLibraryDisplayed) {
				report.updateTestLog("Verify Access Site to Moderated Site", "User has access to Site Assets",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Access Site to Moderated Site", "User doesn't has access to Site Assets",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Access Site to Moderated Site", "User doesn't has access to Site Assets",
					Status.FAIL);
		}
	}

	// Check if the user has access to asset in the moderated site
	public void verifyModeratedSiteNonAccess() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, titleXpath).isDisplayed()) {
				UIHelper.highlightElement(driver, titleXpath);

				isDocumentLibraryDisplayed = true;
			} else {
				isDocumentLibraryDisplayed = false;
			}
			if (isDocumentLibraryDisplayed) {

				report.updateTestLog("Verify Access Site to Moderated Site", "Non Member doesn't have Site Assets",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Access Site to Moderated Site", "Non Member have Site Assets",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Access Site to Moderated Site", "Non Member site asset verification failed",
					Status.FAIL);
		}
	}

	// Validate that when user moves content from one site to another then
	// permission on that content should overwrite with those of the new site
	public void verifyMovedFolderUserRoleInTargetSite() {
		try {

			String actualValue = appSitesPg.getUSerRoleDetailsFromManagePermission();
			String expectedValue = new FileUtil().readDataFromFile(userRoleDetailsFilePath);

			if (actualValue.equalsIgnoreCase(expectedValue)) {
				report.updateTestLog("Verify User Role for Moved Folder in Target Site",
						"Both the source and target sites user roles are similar", Status.PASS);
			} else {
				report.updateTestLog("Verify User Role for Moved Folder in Target Site",
						"Both the source and target sites user roles are not similar", Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException(
				 * "Verify User Role for Moved Folder in Target Site",
				 * "Both the source and target sites user roles are not similar"
				 * );
				 */
			}
		} catch (Exception e) {
		}
	}

	// Validate that when user moves content from one site to another then
	// permission on that content should overwrite with those of the new site
	public void verifyCopiedFolderUserRoleInTargetSite() {
		try {

			String actualValue = appSitesPg.getUSerRoleDetailsFromManagePermission();
			String expectedValue = new FileUtil().readDataFromFile(userRoleDetailsFilePath);

			if (actualValue.equalsIgnoreCase(expectedValue)) {
				report.updateTestLog("Verify User Role for Copied Folder in Target Site",
						"Both the source and target sites user roles are similar", Status.PASS);
			} else {
				report.updateTestLog("Verify User Role for Copied Folder in Target Site",
						"Both the source and target sites user roles are not similar", Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException(
				 * "Verify User Role for Copied Folder in Target Site",
				 * "Both the source and target sites user roles are not similar"
				 * );
				 */
			}
		} catch (Exception e) {
		}
	}

	// Verify user content creator is able to manage permission on a folder
	public void verifyManagePermissionsOnFolder(String roleName) {
		try {

			String actualValue = appSitesPg.getChangedRoleName();

			if (actualValue.equalsIgnoreCase(roleName)) {
				report.updateTestLog("Verify Changed permission on a folder",
						"Content creator successfully changed the permission as '" + roleName + "' on a folder"
								+ "<br /><b>Expected Result:</b> " + roleName + ", <br /><b>Actual Result:</b> "
								+ actualValue + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Changed permission on a folder",
						"Content creator failed to change the permission as '" + roleName + "' on a folder"
								+ "<br /><b>Expected Result:</b> " + roleName + ", <br /><b>Actual Result:</b> "
								+ actualValue + "",
						Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException( "Verify manage permission on a folder",
				 * "Content creator failed to change permissionon on a folder");
				 */
			}
		} catch (Exception e) {
		}
	}

	// Verify user content creator is able to manage permission on a file
	public void verifyManagePermissionsOnFile(String roleName) {
		try {

			String actualValue = appSitesPg.getChangedRoleName();

			if (actualValue.contains(roleName)) {
				report.updateTestLog("Verify Changed permission on a file",
						"Content creator successfully changed the permission as '" + roleName + "' on a file"
								+ "<br /><b>Expected Result:</b> " + roleName + ", <br /><b>Actual Result:</b> "
								+ actualValue + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Changed permission on a file",
						"Content creator failed to change the permission as '" + roleName + "' on a file"
								+ "<br /><b>Expected Result:</b> " + roleName + ", <br /><b>Actual Result:</b> "
								+ actualValue + "",
						Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException( "Verify manage permission on a file",
				 * "Content creator failed to change permissionon on a file");
				 */
			}
		} catch (Exception e) {
		}
	}

	// Check if the user has access to asset in the moderated site
	public void verifyFilelockisAvailable(String file) {
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, editOnlineXpath)) {
				isFileLocked = false;
			} else {
				isFileLocked = true;
			}
			if (isFileLocked) {
				report.updateTestLog("Verify Cancel Check out Operation", "Admin User Can Cancel a locked file",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Cancel Check out Operation", "Admin User Cancel a locked file failed",
						Status.PASS);
			}
		} catch (Exception e) {
		}
	}

	// Check if the user has access to asset in the moderated site
	public void verifyFilelock(String file) {
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, editOnlineXpath)) {
				isFileLocked = false;
			} else {
				isFileLocked = true;
			}
			if (isFileLocked) {
				report.updateTestLog("Verify file Lock", "File is Locked", Status.PASS);
			} else {
				report.updateTestLog("Verify file Lock", "File is not locked", Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Check if the user can upload a new version of a locked file by another
	// user.
	public void verifyFileUploadOFLockedFile(String file) {
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, uploadVersionXpath)) {
				isUploadDisplayed = false;
			} else {
				isUploadDisplayed = true;
			}
			if (isUploadDisplayed) {
				report.updateTestLog("Verify Locked file Upload Operation",
						"Locked file Upload Operation is not possible", Status.PASS);
			} else {
				report.updateTestLog("Verify Locked file Upload Operation", "Locked file Upload Operation is possible",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check if the user has access to asset in the moderated site
	public void verifyPublicSiteAccess() {
		try {

			if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, documentLibXpath))) {
				isDocumentLibraryDisplayed = true;
			} else {
				isDocumentLibraryDisplayed = false;
			}
			if (isDocumentLibraryDisplayed) {
				report.updateTestLog("Verify Access Site to Public Site", "User has access to Site Assets",
						Status.PASS);
			} else {

				report.updateTestLog("Verify Access Site to Public Site", "User has no access to Site Assets",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Check if the user has access to asset in the moderated site
	public void verifyDocumentMetaDataExits() {
		try {

			if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, editPropXpath))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, editPropXpath));
				UIHelper.highlightElement(driver, editPropXpath);
				isDocumentMetaDataDisplayed = true;
			} else {
				isDocumentMetaDataDisplayed = false;
			}
			if (isDocumentMetaDataDisplayed) {
				report.updateTestLog("Verify Document Metadata Exits", "Document Metadata Exits", Status.PASS);
			} else {
				report.updateTestLog("Verify Document Metadata Exits", "Document Metadata doesnt Exits", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Document Metadata Exits", "Document Metadata doesnt Exits", Status.FAIL);
		}
	}

	// Check if the user has access to asset in the site
	public void verifyFileisAvailable(String file) {
		try {
			driver.navigate().refresh();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, editOfflineXpath)) {
				isFileUnLocked = true;
			} else {
				isFileUnLocked = false;
			}
			if (isFileUnLocked) {
				report.updateTestLog("Verify User Access an unlocked file", "User Can Access an unlocked file",
						Status.PASS);
			} else {
				report.updateTestLog("Verify User Access an unlocked file", "User Cannot Access an unlocked file",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Verify default manage aspects on a Folder
	public void verifyNotAppliedDefaultManageAspectsOnFolder(String aspectName) {
		try {

			ArrayList<String> selectedAspectsListVal = appSitesPg.getSelectedAspects();

			String actualResult = "";
			for (String actualAspectVal : selectedAspectsListVal) {
				if (actualAspectVal.equalsIgnoreCase(aspectName)) {
					isDisplayedAspectInSelectedList = true;
					actualResult = actualAspectVal;
					break;
				} else {
					isDisplayedAspectInSelectedList = false;
					actualResult = "Aspect not applied as default one";
				}
			}

			if (!isDisplayedAspectInSelectedList) {
				report.updateTestLog("Verify " + aspectName + " on a newly created folder",
						"" + aspectName + " is not applied as default aspect to newly created folder"
								+ "<br /><b>Expected Result:</b> " + aspectName
								+ "Aspect should not apply as default one" + ", <br /><b>Actual Result:</b> "
								+ aspectName + " " + actualResult + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + aspectName + " on a newly created folder",
						"" + aspectName + " is applied as default aspect to newly created folder"
								+ "<br /><b>Expected Result:</b> " + aspectName
								+ "Aspect should not apply as default one" + ", <br /><b>Actual Result:</b> "
								+ aspectName + " " + actualResult + "",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Verify default manage aspects on a Folder
	public void verifyNotAppliedDefaultManageAspectsOnFile(String aspectName) {
		try {

			ArrayList<String> selectedAspectsListVal = appSitesPg.getSelectedAspects();

			String actualResult = "";
			for (String actualAspectVal : selectedAspectsListVal) {
				if (actualAspectVal.equalsIgnoreCase(aspectName)) {
					isDisplayedAspectInSelectedList = true;
					actualResult = actualAspectVal;
					break;
				} else {
					isDisplayedAspectInSelectedList = false;
					actualResult = "Aspect not applied as default one";
				}
			}

			if (!isDisplayedAspectInSelectedList) {
				report.updateTestLog("Verify " + aspectName + " on a newly created file",
						"" + aspectName + " is not applied as default aspect to newly created file"
								+ "<br /><b>Expected Result:</b> " + aspectName
								+ "Aspect should not apply as default one" + ", <br /><b>Actual Result:</b> "
								+ aspectName + " " + actualResult + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + aspectName + " on a newly created file",
						"" + aspectName + " is applied as default aspect to newly created file"
								+ "<br /><b>Expected Result:</b> " + aspectName
								+ "Aspect should not apply as default one" + ", <br /><b>Actual Result:</b> "
								+ aspectName + " " + actualResult + "",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Verify default manage aspects on a Folder
	public void verifyAppliedDefaultManageAspectsOnFolder(String aspectName) {
		try {

			ArrayList<String> selectedAspectsListVal = appSitesPg.getSelectedAspects();

			String actualResult = "";
			for (String actualAspectVal : selectedAspectsListVal) {
				if (actualAspectVal.equalsIgnoreCase(aspectName)) {
					isDisplayedAspectInSelectedList = true;
					actualResult = actualAspectVal;
					break;
				} else {
					isDisplayedAspectInSelectedList = false;
					actualResult = "Aspect Not Found";
				}
			}

			if (isDisplayedAspectInSelectedList) {
				report.updateTestLog("Verify " + aspectName + " on a newly created folder",
						"" + aspectName + " is applied as default aspect to newly created folder"
								+ "<br /><b>Expected Result:</b> " + aspectName
								+ ", <br /><b>Actual Result:</b> " + actualResult + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify " + aspectName + " on a newly created folder",
						"" + aspectName + " is not applied as default aspect to newly created folder"
								+ "<br /><b>Expected Result:</b> " + aspectName
								+ ", <br /><b>Actual Result:</b> " + actualResult + "",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Verify default manage aspects on a file
	public void verifyAppliedDefaultManageAspectsOnFile(String aspectName) {
		try {

			ArrayList<String> selectedAspectsListVal = appSitesPg.getSelectedAspects();

			String actualResult = "";
			for (String actualAspectVal : selectedAspectsListVal) {
				if (actualAspectVal.equalsIgnoreCase(aspectName)) {
					isDisplayedAspectInSelectedList = true;
					actualResult = actualAspectVal;
					break;
				} else {
					isDisplayedAspectInSelectedList = false;
					actualResult = "Aspect Not Found";
				}
			}

			if (isDisplayedAspectInSelectedList) {
				report.updateTestLog("Verify " + aspectName + " on a file",
						"" + aspectName + " is applied as default aspect to file" + "<br /><b>Expected Result:</b> "
								+ aspectName + ", <br /><b>Actual Result:</b> " + actualResult + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify " + aspectName + " on a file",
						"" + aspectName + " is not applied as default aspect to file" + "<br /><b>Expected Result:</b> "
								+ aspectName + ", <br /><b>Actual Result:</b> " + actualResult + "",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Verify Attached Lifecycle Option for File or Folder
	public void verifyAttachLifecycleForFileOrFolder(String fileOrFolderName) {
		try {
			isDisplayedAttachLifecycle = appSitesPg.checkAttachLifecycleOption(fileOrFolderName);

			if (isDisplayedAttachLifecycle) {
				report.updateTestLog("Verify Attach Lifecycle option",
						"User able to see 'Attach Lifecycle' option by hovering mouse on '" + fileOrFolderName + "'",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Attach Lifecycle option",
						"User unable to see 'Attach Lifecycle' option by hovering mouse on '" + fileOrFolderName + "'",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Verify Attached Lifecycle Option for sub File or Folder when performed
	// the attach lifecycle to parent folder
	public void verifyAttachLifecycleForSubFileOrFolder(String fileOrFolderName) {
		try {
			boolean isDisplayedAttachLifecycleForSubFolder = appSitesPg.checkAttachLifecycleOption(fileOrFolderName);

			if (isDisplayedAttachLifecycleForSubFolder) {

				report.updateTestLog(
						"Verify that if user attaches lifecycle state for any folder does not reflect on sub folder",
						"Subfolder doesn't reflect Parent folder lifecycle and its verified successfuly ", Status.PASS);

			} else {

				report.updateTestLog(
						"Verify that if user attaches lifecycle state for any folder does not reflect on sub folder",
						"Subfolder reflects Parent folder lifecycle and its verified successfuly ", Status.FAIL);

			}
		} catch (Exception e) {
		}
	}

	public void verifyMoreSettingsOptionForFileOrFolder(String fileOrFolderName) {
		try {
			String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
			isDisplayedMoreSettingsOption = appSitesPg.checkMoreSettingsOption(fileOrFolderName, moreSettingsOption);

			if (isDisplayedMoreSettingsOption) {
				report.updateTestLog("Verify " + moreSettingsOption + " option",
						"User able to see '" + moreSettingsOption + "' option by hovering mouse on any file/folder",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + moreSettingsOption + " option",
						"User unable to see '" + moreSettingsOption + "' option by hovering mouse on any file/folder",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	public void verifyMoreSettingsOptionForFileOrFolderItem(String fileOrFolderName, String moreSettingsOptionName) {
		try {
			isDisplayedMoreSettingsOption = appSitesPg.checkMoreSettingsOption(fileOrFolderName,
					moreSettingsOptionName);

			if (isDisplayedMoreSettingsOption) {
				report.updateTestLog("Verify " + moreSettingsOptionName + " option", "User able to see '"
						+ moreSettingsOptionName + "' option by hovering mouse on any file/folder:" + fileOrFolderName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + moreSettingsOptionName + " option", "User unable to see '"
						+ moreSettingsOptionName + "' option by hovering mouse on any file/folder:" + fileOrFolderName,
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	public void verifyManageAspectsOptionIsAvaible(String fileOrFolderName, String userRole) {
		try {
			String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
			isDisplayedMoreSettingsOption = appSitesPg.checkMoreSettingsOption(fileOrFolderName, moreSettingsOption);

			if (isDisplayedMoreSettingsOption) {
				report.updateTestLog("Verify " + moreSettingsOption + " option", "User (" + userRole + ") able to see '"
						+ moreSettingsOption + "' option by hovering mouse on any file/folder", Status.FAIL);
			} else {
				report.updateTestLog("Verify " + moreSettingsOption + " option", "User (" + userRole
						+ ") unable to see '" + moreSettingsOption + "' option by hovering mouse on any file/folder",
						Status.PASS);
			}
		} catch (Exception e) {
		}
	}

	// Method for verify Added Relationship for file or folder
	public void verifyAddRelationshipForFileOrFolder(String fileOrFolderName) {
		try {
			isDisplayedAddRelationship = appSitesPg.checkAddRelationshipOption(fileOrFolderName);

			if (isDisplayedAddRelationship) {
				report.updateTestLog("Verify Add Relationship option",
						"User able to see 'Add Relationship' option by hovering mouse on folder " + fileOrFolderName
								+ "</br><b>Expected Result:</b>Relationship option not available"
								+ "</br><b>Actual Result:<b> Relationship option available ",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Add Relationship option",
						"User unable to see 'Add Relationship' option by hovering mouse on folder " + fileOrFolderName
								+ "</br><b>Expected Result:</b>Relationship option not available"
								+ "</br><b>Actual Result:<b> Relationship option not available ",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Add Relationship option", "Verify Add Relationship option Failed",
					Status.FAIL);
		}
	}

	public void verifyLinkingFailed(String linkMessage) {
		try {
			// UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			// String linkMessage = UIHelper.getTextFromWebElement(driver,
			// messageXpath);
			// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			// messageXpath);
			System.out.println(linkMessage);
			if (linkMessage.equalsIgnoreCase("The linking could not be completed")) {
				report.updateTestLog("Link a File",
						"Unable to Link a File</br><b>Expected Result:</b> Linking should fail"
								+ "</br><b>Actual Result:<b> Linking failed",
						Status.PASS);
			} else {
				report.updateTestLog("Link a File", "" + linkMessage + "", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Link File", e.getMessage(), Status.FAIL);
		}
	}

	public void verifyLinkingFailed() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			String linkMessage = UIHelper.getTextFromWebElement(driver, messageXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			System.out.println(linkMessage);
			if (linkMessage.equalsIgnoreCase("The linking could not be completed")) {
				report.updateTestLog("Link a File",
						"Unable to Link a File</br><b>Expected Result:</b> Linking should fail"
								+ "</br><b>Actual Result:<b> Linking failed",
						Status.PASS);
			} else {
				report.updateTestLog("Link a File", "" + linkMessage + "", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Link File", e.getMessage(), Status.FAIL);
		}
	}

	// Verify 'Selected Items' Menu options
	public void verifySelectedItemsMenuOption(String selectedItemMenuOptVal) {
		try {
			selectedItemsMenuValues = appSitesPg.getSelectedItemMenuOption();
			String actualValue = "";
			for (String actualVal : selectedItemsMenuValues) {
				if (actualVal.contains(selectedItemMenuOptVal)) {
					isDisplayedSelectedItemMenuOption = true;
					actualValue = actualVal;
					break;
				} else {
					actualValue = "Option not found in 'Selected Items' menu";
					isDisplayedSelectedItemMenuOption = false;
				}
			}

			if (isDisplayedSelectedItemMenuOption) {
				report.updateTestLog(
						"Verify the " + selectedItemMenuOptVal
								+ " Option for any files or folder from Selected Items dropdown",
						"User is able to see the 'Selected Items' Dropdown Option:" + selectedItemMenuOptVal
								+ "<br><b>Expected Result:</b>" + selectedItemMenuOptVal + " <br><b>Actual Result:</b>"
								+ actualValue,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the " + selectedItemMenuOptVal
								+ " Option for any files or folder from Selected Items dropdown",
						"User is not able to see the 'Selected Items' Dropdown  Option:" + selectedItemMenuOptVal
								+ "<br><b>Expected Result:</b>" + selectedItemMenuOptVal + " <br><b>Actual Result:</b>"
								+ actualValue,
						Status.FAIL);
			}
		} catch (Exception e) {
		}

	}

	// Check Selected Items Dropdown Option name
	public boolean checkSelectedItemsDropdownOption(String selectedItemMenuOptVal) {
		boolean isDisplayedSelectedItemDropdwonOption = false;
		try {
			selectedItemsMenuValues = appSitesPg.getSelectedItemMenuOption();
			String actualValue = "";
			for (String actualVal : selectedItemsMenuValues) {
				if (actualVal.contains(selectedItemMenuOptVal)) {
					isDisplayedSelectedItemDropdwonOption = true;
					break;
				} else {
					isDisplayedSelectedItemDropdwonOption = false;
				}
			}
		} catch (Exception e) {
		}

		return isDisplayedSelectedItemDropdwonOption;
	}

	public void verifySelectedItemsMenuOptionForNegativeCase(String selectedItemMenuOptVal) {
		try {
			selectedItemsMenuValues = appSitesPg.getSelectedItemMenuOption();
			String actualValue = "";
			for (String actualVal : selectedItemsMenuValues) {
				if (actualVal.contains(selectedItemMenuOptVal)) {
					isDisplayedSelectedItemMenuOption = true;
					actualValue = actualVal;
					break;
				} else {
					actualValue = "Option not found in 'Selected Items' Dropdown";
					isDisplayedSelectedItemMenuOption = false;
				}
			}

			if (!isDisplayedSelectedItemMenuOption) {
				report.updateTestLog(
						"Verify the " + selectedItemMenuOptVal
								+ "Option for any files or folder from Selected Items dropdown",
						"User is not able to see the 'Selected Items' Dropdown Option:" + selectedItemMenuOptVal
								+ "<br><b>Expected Result:</b>" + selectedItemMenuOptVal + " <br><b>Actual Result:</b>"
								+ actualValue,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the " + selectedItemMenuOptVal
								+ "Option for any files or folder from Selected Items dropdown",
						"User is able to see the 'Selected Items' Dropdown Option:" + selectedItemMenuOptVal
								+ "<br><b>Expected Result:</b>" + selectedItemMenuOptVal + " <br><b>Actual Result:</b>"
								+ actualValue,
						Status.FAIL);
			}

			isDisplayedSelectedItemMenuOption = false;
		} catch (Exception e) {
		}

	}

	// Verify Browse Action option name for negativeCase
	public void verifyBrowseActionNameForFileOrFolderInNegativeCase(String fileOrFolderName, String browseOption) {
		try {
			boolean isDisplayedBrowseActionName = appSitesPg.checkFileOrFolderBrowseOption(fileOrFolderName,
					browseOption);

			if (!isDisplayedBrowseActionName) {
				report.updateTestLog(
						"Verify the " + browseOption + "Option for any files or folder from Browse Actions",
						"User is not able to see the 'Browse Action' name:" + "<b>" + browseOption + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the " + browseOption + "Option for any files or folder from Browse Actions",
						"User is able to see the 'Browse Actions' name:" + "<b>" + browseOption + "</b>", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify Browse Action option name for negativeCase
	public void verifyMoreOptionsForFileOrFolder(String fileOrFolderName) {
		try {
			boolean isDisplayedMoreOptions = appSitesPg.checkMoreOptions(fileOrFolderName);

			if (isDisplayedMoreOptions) {
				report.updateTestLog("Verify the More Options for any files or folder",
						"User is able to see the 'More' options:", Status.PASS);
			} else {
				report.updateTestLog("Verify the More Options for any files or folder",
						"User is not able to see the 'More' options:", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Sanity - Verify Life cycle is Attached and Promote Demote not available
	// in Selected Items Drop down
	public void verifyLifeCycleAttached() {
		boolean blnFlagTwo = false;
		String lifeCycleNme = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String folderNme = dataTable.getData("MyFiles", "FileName");

		try {
			blnFlagTwo = appSitesPg.verifyAttachedLifeCycle(folderNme, lifeCycleNme);

			if (blnFlagTwo) {

				report.updateTestLog("Verify attach Lifecycle Functionality",
						"LifeCycle has been attached Successfully to the folder", Status.PASS);

			} else {

				report.updateTestLog("Verify attach Lifecycle Functionality",
						"LifeCycle has NOT been attached Successfully to the folder", Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyLifeCycleDetached() {
		boolean blnFlagOne = false;

		String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
		String folderNme = dataTable.getData("MyFiles", "FileName");

		try {
			blnFlagOne = appSitesPg.verifyAttachedLifeCycle(folderNme, attachLifeCycleDropdownVal);

			if (!blnFlagOne) {

				report.updateTestLog("Verify user is able to Detach Lifecycle for folder",
						"Lifecycle Detached Successfully ", Status.PASS);

			} else {

				report.updateTestLog("Verify user is able to Detach Lifecycle for folder",
						"Lifecycle NOT Detached Successfully ", Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> getSiteNames(String siteDetails) {
		try {

			if (siteNamesList != null || siteNamesList.size() != 0) {
				siteNamesList.clear();
			}

			if (siteDetails.contains(";")) {
				String splittedSiteDetailas[] = siteDetails.split(";");
				for (String siteValues : splittedSiteDetailas) {
					String splittedSiteValues[] = siteValues.split(",");

					String siteName = splittedSiteValues[0].replace("Name-", "");
					siteNamesList.add(siteName);
				}
			} else {
				String splittedSiteValues[] = siteDetails.split(",");

				String siteName = splittedSiteValues[0].replace("Name-", "");
				siteNamesList.add(siteName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return siteNamesList;
	}

	public void verifyCreateSitePageIsDisplayed() {
		try {

			if (appSitesPg.isDisplayedCreateSite()) {
				report.updateTestLog("Verify Create Site Option", "User able to see 'Create Site'", Status.PASS);
			} else {
				report.updateTestLog("Verify Create Site Option", "User not able to see 'Create Site'", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyCreateSitePageLabels() {
		try {

			String expectedCreateSiteLabelValues = dataTable.getData("Sites", "CreateSiteLabelNames");

			if (expectedCreateSiteLabelValues.contains(",")) {
				splittedCreateSiteLabelNames = expectedCreateSiteLabelValues.split(",");
			} else if (expectedCreateSiteLabelValues.contains(";")) {
				splittedCreateSiteLabelNames = expectedCreateSiteLabelValues.split(";");
			} else {
				expectedListOfCreateSitFieldLabels.add(expectedCreateSiteLabelValues);
			}

			if (splittedCreateSiteLabelNames != null && splittedCreateSiteLabelNames.length > 1) {

				for (String fieldLabel : splittedCreateSiteLabelNames) {

					expectedListOfCreateSitFieldLabels.add(fieldLabel);
				}
			}

			System.out.println(expectedListOfCreateSitFieldLabels);

			listOfCreateSitFieldLabels = appSitesPg.getActualCreateSiteLabels();

			System.out.println(listOfCreateSitFieldLabels);

			boolean isEqualTwolists = false;

			isEqualTwolists = UIHelper.compareTwoSimilarLists(expectedListOfCreateSitFieldLabels,
					listOfCreateSitFieldLabels);
			;

			if (isEqualTwolists) {

				report.updateTestLog("Verify if all GUI values of Create site is displayed",
						"All GUI values of Create site is displayed ", Status.PASS);

			} else {

				report.updateTestLog("Verify if all GUI values of Create site is displayed",
						"All GUI values of Create site is not displayed ", Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify Create Site Type dropdown Values
	public void verifyCreateSiteTypeValues() {
		try {

			String expectedCreateSiteTypeValues = dataTable.getData("Sites", "CreateSiteTypeValues");

			if (expectedCreateSiteTypeValues.contains(",")) {
				splittedCreateSiteLabelNames = expectedCreateSiteTypeValues.split(",");
			} else if (expectedCreateSiteTypeValues.contains(";")) {
				splittedCreateSiteLabelNames = expectedCreateSiteTypeValues.split(";");
			} else {
				expectedListOfCreateSitTypeValues.add(expectedCreateSiteTypeValues);
			}

			if (splittedCreateSiteLabelNames != null && splittedCreateSiteLabelNames.length > 1) {

				for (String fieldLabel : splittedCreateSiteLabelNames) {

					expectedListOfCreateSitTypeValues.add(fieldLabel);

				}
			}

			actuallistOfCreateSitTypeValues = appSitesPg.getActualCreateSiteTypeValues();

			boolean isEqualTwolists = false;

			isEqualTwolists = UIHelper.compareTwoSimilarLists(expectedListOfCreateSitTypeValues,
					actuallistOfCreateSitTypeValues);

			if (isEqualTwolists) {

				report.updateTestLog("Verify 'Type' dropdown values from Create Site popup window",
						"Create Site's <b>Type dropdown values</b>:" + expectedListOfCreateSitTypeValues
								+ " are displayed ",
						Status.PASS);

			} else {
				report.updateTestLog("Verify 'Type' dropdown values from Create Site popup window",
						"Create Site's <b>Type dropdown values</b>:" + expectedListOfCreateSitTypeValues
								+ " are failed to display ",
						Status.FAIL);

			}

			UIHelper.highlightElement(driver, cancelbuttonXpath);
			UIHelper.click(driver, cancelbuttonXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author 412766
	 */
	public void verifyProgramComponentSiteOptionFromCollobSite() {
		try {
			String siteType = dataTable.getData("Sites", "CreateSiteTypeValues");
			ArrayList<String> typeValuesList = new AlfrescoSitesPage(scriptHelper).getSiteTypeValues();
			if (!(new AlfrescoSitesPage(scriptHelper).isSiteTypeAvailable(siteType))) {
				report.updateTestLog(
						"Verify the User is not able to find 'Program Component Site' Option in Collobarative Site",
						"<b>" + siteType + "</b> Type Not displyed" + "</br><b>Site Types :</b>"
								+ typeValuesList.toString(),
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the User is not able to find 'Program Component Site' Option in Collobarative Site",
						"<b>" + siteType + "</b> Type displyed" + "</br><b>Site Types :</b>"
								+ typeValuesList.toString(),
						Status.FAIL);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the User is not able to find 'Program Component Site' Option in Collobarative Site",
					"Verify the User is not able to find 'Program Component Site' Option in Collobarative Site Failed",
					Status.FAIL);
		}
	}

	// check given site type in Create Site-->Type dropdown
	public void verifySiteTypeInCreateSiteTypeDropdown(String siteType) {
		try {
			if (appSitesPg.isSiteTypeAvailable(siteType)) {
				report.updateTestLog("Verify the " + siteType + " Option in Create Site-->Type dropdown",
						"<b>" + siteType + "</b> option displyed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the " + siteType + " Option in Create Site-->Type dropdown",
						"<b>" + siteType + "</b> option failed to display", Status.FAIL);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Created Site Names
	public String getCreatedSiteName() {
		try {
			createdSiteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createdSiteName;
	}

	/**
	 * @author 412766
	 * @param siteName
	 */
	public void verifyPrivateSite(String siteName) {
		try {
			if (!(new AlfrescoSitesPage(scriptHelper).siteFinderOption(siteName))) {
				report.updateTestLog(
						"Verify the User is not able to access 'Component Site' which has Private visibility",
						"Site Not Found" + "</br><b>Site Name :</b> " + siteName, Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the User is not able to access 'Component Site' which has Private visibility",
						"Site Found" + "</br><b>Site Name :</b> " + siteName, Status.FAIL);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify the User is not able to access 'Component Site' which has Private visibility",
					"Verify the User is not able to access 'Component Site' which has Private visibility Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param siteName
	 */
	public void verifyEditPropertiesOfRootFolder(String fileOrFolderName) {
		try {
			String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
			isDisplayedMoreSettingsOption = appSitesPg.checkMoreSettingsOption(fileOrFolderName, moreSettingsOption);

			if (!isDisplayedMoreSettingsOption) {
				report.updateTestLog(
						"Verify the User should not have 'Edit Properties' option for root folder named with Component Site",
						"User not having 'Edit Properties' option for root folder named with Component Site"
								+ "</br><b>Folder Name :</b> " + fileOrFolderName,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the User should not have Edit option for root folder named with Component Site",
						"User having 'Edit Properties' option for root folder named with Component Site"
								+ "</br><b>Folder Name :</b> " + fileOrFolderName,
						Status.FAIL);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the User should not have Edit option for root folder named with Component Site",
					"Verify the User should not have Edit option for root folder named with Component Site Failed",
					Status.FAIL);
		}
	}

	// To check coordinator role
	public void toCheckRoleInInviteUser() {
		try {
			String invitedUserName = dataTable.getData("Sites", "InviteUserName");
			String role = dataTable.getData("Sites", "Role");

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, inviteLinkXpath);
			UIHelper.click(driver, inviteLinkXpath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, searchTextFieldXpath);

			StringTokenizer userNameToken = new StringTokenizer(invitedUserName, ",");
			StringTokenizer roleToken = new StringTokenizer(role, ",");

			while (userNameToken.hasMoreElements()) {
				String userName = userNameToken.nextElement().toString();
				String userRole = roleToken.nextElement().toString();

				UIHelper.sendKeysToAnElementByXpath(driver, searchTextFieldXpath, userName);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, searchButtonXpath);
				UIHelper.waitFor(driver);
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, htmlBodyXpath));
				UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, searchResultsXpath));
				UIHelper.waitFor(driver);
				String finalAddBtnXpath = tempAddBtnXpath.replace("CRAFT", userName);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalAddBtnXpath);
				if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, finalAddBtnXpath))) {
					UIHelper.click(driver, finalAddBtnXpath);
					UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, invitedContainerXpath));
					UIHelper.mouseOveranElement(driver,
							UIHelper.findAnElementbyXpath(driver, selectRoleContainerXpathForInvitedUser));
					UIHelper.waitForVisibilityOfEleByXpath(driver, contributorXpath);
					UIHelper.click(driver, contributorXpath);

					String finalContributorXpath = tempContributorXpath.replace("CRAFT", userRole);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalContributorXpath);
					if (UIHelper.checkForAnElementbyXpath(driver, finalContributorXpath)) {
						UIHelper.highlightElement(driver, finalContributorXpath);

						report.updateTestLog("Verify user is able to view " + userRole + " role",
								"User is able to view " + userRole + " role", Status.PASS);

					}

				} else {
					report.updateTestLog("Verify user is able to view " + userRole + " role",
							"User is unable to view " + userRole + " role", Status.FAIL);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Site has Enable Feed Option
	public void verifyEnbleFeedForSite(String SiteNme) {
		try {
			String btnDisableSiteFeedXPathFinal = btnDisableSiteFeedXPath.replace("CRAFT", SiteNme);
			String btnEnableSiteFeedXpathFinal = btnEnableSiteFeedXPath.replace("CRAFT", SiteNme);
			boolean flag = false;
			System.out.println("" + btnDisableSiteFeedXPathFinal);
			System.out.println("" + btnEnableSiteFeedXpathFinal);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, btnDisableSiteFeedXPathFinal))) {
				flag = true;
				UIHelper.highlightElement(driver, btnDisableSiteFeedXPathFinal);
			}

			else {
				UIHelper.waitForVisibilityOfEleByXpath(driver, btnEnableSiteFeedXpathFinal);

				flag = UIHelper
						.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, btnDisableSiteFeedXPathFinal));
				UIHelper.highlightElement(driver, btnEnableSiteFeedXpathFinal);
				UIHelper.click(driver, btnEnableSiteFeedXpathFinal);
			}

			if (flag) {
				report.updateTestLog("Verify the User can Enable Activity Feeds in the My Site Page",
						"User successfully Enabled Activity Feeds for the Site - " + SiteNme, Status.PASS);
			} else {
				report.updateTestLog("Verify the User can Enable Activity Feeds in the My Site Page",
						"User Not able to  Enable the Activity Feeds for the Site - " + SiteNme, Status.FAIL);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify the User can Enable Activity Feeds in the My Site Page",
					"User Not able to  Enable the Activity Feeds for the Site - " + SiteNme, Status.FAIL);
		}
	}

	// Verify Sites in 'My Sites' Page
	public void verifySitesInMySitesPage() {

		try {
			if (appSitesPg.isDisplayedSitesInMySitesPage()) {
				report.updateTestLog("Verify user is able to view all sites in 'Mysite' page",
						"User viewed all sites successfully in 'Mysite' page" + "<br /><b>Number of My Sites: "
								+ appSitesPg.mySitesCount,
						Status.PASS);
			} else {
				report.updateTestLog("Verify user is able to view all sites in 'Mysite' page",
						"User failed to view sites in 'Mysite' page" + "<br /><b>Number of My Sites: "
								+ appSitesPg.mySitesCount,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Sites in 'Site Finder' results
	public void verifySiteInSiteFinderResults(String expectedSiteName) {

		try {

			if (appSitesPg.isDisplayedSiteNameInSiteFinderResults(expectedSiteName)) {
				report.updateTestLog("Verify site in 'Site Finder' results",
						"Site: " + expectedSiteName + " is displayed successfully in 'Site Finder' results",
						Status.PASS);
			} else {
				report.updateTestLog("Verify site in 'Site Finder' Results",
						"Site: " + expectedSiteName + " is failed to display in 'Site Finder' results", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To Validate Option is not avilable in More Options
	public void verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(String fileOrFolderName,
			String moreSettingsOptionName) {
		try {
			isDisplayedMoreSettingsOption = appSitesPg.checkMoreSettingsOption(fileOrFolderName,
					moreSettingsOptionName);

			if (!isDisplayedMoreSettingsOption) {
				report.updateTestLog(
						"Verify " + moreSettingsOptionName + " option", "User is not able to see '"
								+ moreSettingsOptionName + "' option by hovering mouse on any file/folder",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + moreSettingsOptionName + " option",
						"User able to see '" + moreSettingsOptionName + "' option by hovering mouse on any file/folder",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Check the Properties of the Site are updated
	public void verifyProgComponentSiteProp(String siteName) {
		try {

			if (appSitesPg.getSitePageTitle().contains(siteName)
					|| appSitesPg.getSitePageTitle().equalsIgnoreCase(siteName)) {
				report.updateTestLog("Verify updated properties of the Program Component Site",
						"Changes are reflected back in Component site:<b>" + siteName, Status.PASS);
			} else {
				report.updateTestLog("Verify updated properties of the Program Component Site",
						"Changes are not reflected back in Component site:<b>" + siteName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Action Name for File or folder
	public void verifyActionNameForFileOrFolder(String actionName, String fileOrFolderName) {
		try {
			boolean isDisplayedActionNameAction = appSitesPg.checkActionNameForFolderOrFileInDocLibPage(actionName,
					fileOrFolderName);

			if (isDisplayedActionNameAction) {
				report.updateTestLog("Verify the '" + actionName + "' Option for any files/folder",
						"User able to view the '" + actionName + "' Option for any files/folder:<b>" + fileOrFolderName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify the '" + actionName + "' Option for any files/folder",
						"User unable to view the '" + actionName + "' Option for any files/folder:<b>"
								+ fileOrFolderName,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check whether count of sites name are listed in select window
	public void verifySiteNamesInSelectWindowForAddRelationship(int expectedSitesCount) {
		try {
			int actualSitesCount = appSitesPg.getSiteNamesCountFormSelectWindow();

			if (actualSitesCount <= expectedSitesCount && !(actualSitesCount > expectedSitesCount)) {
				report.updateTestLog("verify whether " + expectedSitesCount + " sites name are listed in select window",
						"User able to view the '" + actualSitesCount + "' sites names in select window", Status.PASS);
			} else {
				report.updateTestLog("verify whether " + expectedSitesCount + " sites name are listed in select window",
						"User able to view the '" + actualSitesCount + "' sites names in select window", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Verify Create Site Type dropdown Values
	public void verifynewCreateSiteTypeValues() {
		try {

			String expectedCreateSiteTypeValues = dataTable.getData("Sites",
					"CreateSiteTypeValues");

			if (expectedCreateSiteTypeValues.contains(",")) {
				splittedCreateSiteLabelNames = expectedCreateSiteTypeValues
						.split(",");
			} else if (expectedCreateSiteTypeValues.contains(";")) {
				splittedCreateSiteLabelNames = expectedCreateSiteTypeValues
						.split(";");
			} else {
				expectedListOfCreateSitTypeValues
						.add(expectedCreateSiteTypeValues);
			}

			if (splittedCreateSiteLabelNames != null
					&& splittedCreateSiteLabelNames.length > 1) {

				for (String fieldLabel : splittedCreateSiteLabelNames) {

					expectedListOfCreateSitTypeValues.add(fieldLabel);

				}
			}
			WebElement typDropdownEle = UIHelper.findAnElementbyXpath(driver, siteTypeDpXpath);
			UIHelper.highlightElement(driver, typDropdownEle);
			UIHelper.click(driver, siteTypeDropdownXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			List<WebElement> typeValues = UIHelper.findListOfElementsbyXpath(siteTypeDropdownXpath, driver);

			for (WebElement ele : typeValues) {
				actuallistOfCreateSitTypeValues.add(ele.getText().trim());

			boolean isEqualTwolists = false;

			isEqualTwolists = UIHelper.compareTwoSimilarLists(
					expectedListOfCreateSitTypeValues,
					actuallistOfCreateSitTypeValues);

			if (isEqualTwolists) {

				report.updateTestLog(
						"Verify 'Type' dropdown values from Create Site popup window",
						"Create Site's <b>Type dropdown values</b>:"
								+ expectedListOfCreateSitTypeValues
								+ " are displayed ", Status.PASS);

			} else {
				report.updateTestLog(
						"Verify 'Type' dropdown values from Create Site popup window",
						"Create Site's <b>Type dropdown values</b>:"
								+ expectedListOfCreateSitTypeValues
								+ " are failed to display ", Status.FAIL);

			}

			UIHelper.highlightElement(driver, cancelbuttonXpath);
			UIHelper.click(driver, cancelbuttonXpath);

		} 
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void isDeleteButtonPresentInSiteFinder(String siteName,String role)
	{
		
		if(appSitesPg.siteFinderOption(siteName)) {
			
			if(role.equalsIgnoreCase("Manager")) {
				
				if(siteFinderPage.isSiteFinderDeleteOptionAvailableForSite(siteName)) {
					report.updateTestLog("Delete Button visible 'Site finder Page",
							"Delete button visible successfully" + "<br /><b> Page Title : </b>" + "SiteFinder", Status.PASS);
				}
				else{
					report.updateTestLog("Delete Button visible 'Site finder Page",
							"Delete button not visible" + "<br /><b> Page Title : </b>" + "SiteFinder", Status.FAIL);
				}
			}else {
				
				if(siteFinderPage.isSiteFinderDeleteOptionAvailableForSite(siteName)) {
					report.updateTestLog("Delete Button visible 'Site finder Page",
							"Delete button visible" + "<br /><b> Page Title : </b>" + "SiteFinder", Status.FAIL);
				}
				else{
					report.updateTestLog("Delete Button visible 'Site finder Page",
							"Delete button not visible" + "<br /><b> Page Title : </b>" + "SiteFinder", Status.PASS);
				}
			}
		
		}
		else {
			report.updateTestLog("Site finder Search",
					"Site finder search unsuccessfull" + "<br /><b> Page Title : </b>" + "SiteFinder", Status.FAIL);
		}
		
	}
	public void verifyPermissionTurnOff() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, inheritOffXpath)) {
				report.updateTestLog("Verify Turn Off permission is applied",
						"Turn Off inheritpermission applied Successfully ", Status.PASS);
			} else {
				report.updateTestLog("Verify Turn Off permission is applied",
						"Turn Off inheritpermission failed to apply", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Turn Off permission is applied", "Turn Off inheritpermission failed to apply",
					Status.FAIL);
		}
	}
	

}
