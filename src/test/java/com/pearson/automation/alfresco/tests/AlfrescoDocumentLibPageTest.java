package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;

import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoDocumentLibPageTest extends ReusableLibrary {

	private ArrayList<String> searchResultsList = new ArrayList<String>();
	private boolean isDeleted;
	private boolean isDiaplyedMoreSettingsHiddenValue;
	private String lifeCycleDropdownXpath = ".//*[contains(@id,'lifecycle-dropdown')]";

	private ArrayList<String> fileOrFoldeTagsList = new ArrayList<String>();

	private ArrayList<String> expectedTagsList = new ArrayList<String>();

	private AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
	private AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
	private AlfrescoMyFilesPage myFilePageObj = new AlfrescoMyFilesPage(scriptHelper);

	private String splittedLifecycleTags[];

	private boolean isDisplayedBrowseAction;

	/*** Program Folders ***/
	private String breadcrumbXpath = ".//*[contains(@class,'crumb documentDroppable')]/span/a[text()='CRAFT']";
	private String docBreadcrumbXpath = ".//*[contains(@class,'crumb documentDroppable')]//a";
	private String folderXpath = ".//*[@id='parent-doclib-link']/a";
	private String progfolderXpath = ".//*[@id='alf-filters']//span[text()='CRAFT']";
	private String documentLibXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']";
	private String docFavoriteEnabledXpath = "//table//tr//td[4]//*[@class='filename']//a[text()='CRAFT']//ancestor::td//div[4]//*[@class='favourite-action enabled']";
	private String doclikeEnabledXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::td//div[4]//a[contains(@class,'LINK')]";

	/*** Category filter ***/
	private String categoryXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::tr//td[contains(@class,'fileName')]//a[contains(text(),'REPLACE')]";

	private ArrayList<String> browseActionsList = new ArrayList<String>();
	private ArrayList<String> expectedSiteNamesList = new ArrayList<String>();
	private ArrayList<String> actualDocLibFoldersList = new ArrayList<String>();

	/*** Header Bar ***/
	private String headerBarOptXpath = ".//*[contains(@id,'default-headerBar')]//button[contains(text(),'CRAFT')]";
	private String headerBarXpath = ".//*[contains(@id,'default-headerBar')]";

	private boolean isDisplayedLibDocumentLibVal;
	private String deletePopupXPath = ".//*[@id='prompt']/div[2]";
	private String popupXPath = ".//*[@id='prompt']//div[@class='bd']";
	private String popupbuttonXPath = ".//*[@id='prompt']//button[text()='CRAFT']";

	private String uploadedFilesTitlesXpath = "//*[@class='yui-dt-data']//h3/span/a[1]";
	private String uploadedFilesTitlesXpath1 = "//*[@class='yui-dt-data']//h3/span/a[text()='CRAFT']";
	private String stsRprtXPath = ".//*[@id='onActionCallStatusReportWS']/a/span";
	private String pubHistXPath = ".//*[@id='onActionCallPublishHistoryWS']/a/span";

	private String popUpMsgXapth = ".//*[@id='message']/div/span";
	private String fileIconXapth = ".//img[@title='CRAFT']";

	private String tempXpathForShareFolderJustNow = ".//*[contains(@id,'yuievtautoid')]//a[contains(text(),'CRAFT')]/ancestor::h3/following-sibling::div[1]/span/span";
	private String tempDocumentLibBannerInfoXpath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//div[@class='info-banner' and contains(text(),'MSGNAME')]";
	private String documentFilterLabelXpath = "//div[@class='nav-bar flat-button theme-bg-2']/div[@class='description hideable DocListFilter TagFilter']";
	private String tempXpathForlifeCycleLabelXPath = "//div[@id='clickAttach-dialog']//div[@id='clickAttach-title']";
	private String tempXpathForlifeCycleSelectdrpdwnLabelXpath = "//div[@id='clickAttach-dialog']//div[@class='yui-g']//label";
	private String prmtLifDrpDwnXPath = ".//div[@class='dialog-body']//select[@id='promote-lifecycle-dropdown']";
	private String demtLifDrpDwnXPath = ".//*[@id='demote-lifecycle-dropdown']";

	public AlfrescoDocumentLibPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// To Verify Documented deleted sucessfully.
	public AlfrescoDocumentLibPageTest verifyDocumentDeleted() {
		try {
			String fileName = dataTable.getData("MyFiles", "FileName");

			searchResultsList = docLibPage.getSearchResultsTitle();

			for (String actualSearchResult : searchResultsList) {
				if (!actualSearchResult.equalsIgnoreCase(fileName)) {

					isDeleted = true;
					break;
				} else {
					isDeleted = false;
				}
			}

			if (isDeleted) {
				report.updateTestLog("Verify Deleted File", "File Not available in Library, So Deleted sucessfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Deleted File", "File Failed to Delete", Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException("Delete File", "File Failed to Delete");
				 */
			}
		} catch (Exception e) {
		}

		return new AlfrescoDocumentLibPageTest(scriptHelper);
	}

	/**
	 * Method to verify the uploaded folder structure retained or not, by
	 * navigate the parent to child folder level.
	 * 
	 * @param folderNameList
	 *            - Folder name list(from parent to child)
	 * @author 412766
	 */
	public void verifyUploadedFolderStructure(List<String> folderNameList) {
		boolean flag = false;
		try {
			for (String folderName : folderNameList) {
				if (new AlfrescoDocumentLibPage(scriptHelper).isNavigateFolderInDocLibrary(folderName)) {
					flag = true;

				} else {
					flag = false;
					break;
				}
			}

			if (flag) {
				report.updateTestLog("Verify the uploded folder structure",
						"Uploaded folder structure availble in the destination location"
								+ "<br /><b>Uploded Folders : </b>" + folderNameList.toString(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify the folder structure",
						"Uploaded folder structure not availble in the destination location"
								+ "<br /><b>Uploded Folders : </b>" + folderNameList.toString(),
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the uploded structure", "Verify the uploded structure Failed", Status.FAIL);
		}
	}

	/**
	 * Method to verify the document previewed or not
	 * 
	 * @param fileName
	 * @author 412766
	 */
	public void verifyDocumentPreviewed(String fileName) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).isDocumentPreviewed()) {
				report.updateTestLog("Check for document preview",
						"Document previewed successfully" + "<br /><b>File Name : </b>" + fileName, Status.PASS);
			} else {
				report.updateTestLog("Check for document preview",
						"Document not previewed" + "<br /><b>File Name : </b>" + fileName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Check for document preview", "Check for document preview Failed", Status.FAIL);
		}
	}

	/**
	 * Method to verify the document is available to the site member
	 * 
	 * @param fileName
	 * @author 412766
	 */
	public void verifyDocumentAvailableForSiteMember(String fileName) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).checkFileIsAvailable(fileName)) {
				report.updateTestLog("Check for document view by site member",
						" File viewed successfully" + "<br /><b>File Name : </b>" + fileName, Status.PASS);
			} else {
				report.updateTestLog("Check for document view by site member",
						" File not viewed" + "<br /><b>File Name : </b>" + fileName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Check for document view by site member",
					"Check for document view by site member Failed", Status.FAIL);
		}
	}

	// Check Follow link for folder
	public void verifyFollowLinkForFolder(String folderName) {
		try {
			if (!docLibPage.isDisplayedFollowLink(folderName)) {
				report.updateTestLog("Verify 'Follow' option for folders in document library",
						"User not able to view the <b>'Follow'</b> link option for folder:<b>" + folderName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Follow' option for folders in document library",
						"User able to view the <b>'Follow'</b> link option for folder: <b>" + folderName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Follow link for file
	public void verifyFollowLinkForFile(String fileName) {
		try {
			if (docLibPage.isDisplayedFollowLink(fileName)) {
				report.updateTestLog("Verify 'Follow' option for files in document library",
						"User able to view the <b>'Follow'</b> link option for file:<b>" + fileName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Follow' option for files in document library",
						"User not able to view the <b>'Follow'</b> link option for file:<b>" + fileName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Unfollow link for file
	public void verifyUnfollowLinkForFile(String fileName) {
		try {
			if (docLibPage.isDisplayedUnfollowLink(fileName)) {
				report.updateTestLog(
						"Verify green coloured 'tick' sign after clicking on Follow option for files in document library",
						"User able to view the green coloured <b>'tick'</b> sign after clicking on Follow option for file:<b>"
								+ fileName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify green coloured 'tick' sign after clicking on Follow option for files in document library",
						"User not able to view the green coloured <b>'tick'</b> sign after clicking on Follow option for file:<b>"
								+ fileName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void verifyFollowedOptionInLinkedFiles(String fileName) {
		try {
			if (docLibPage.isDisplayedUnfollowLink(fileName)) {
				report.updateTestLog("Verify Followed option in Linked files ",
						"Follow option verified succesfully" + "<br /><b> File Name : </b>" + fileName, Status.PASS);
			} else {
				report.updateTestLog("Verify Followed option in Linked files ",
						"Follow option not verified" + "<br /><b> File Name : </b>" + fileName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Followed option in Linked files ",
					"Verify Followed option in Linked files Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void verifyCopiedFilesInDestinationSite(String fileName, String targetSiteName) {
		try {
			if (docLibPage.isFileIsAvailable(fileName)) {
				report.updateTestLog("Verify copied files in Destination Site",
						"File verified succesfully" + "<br /><b> File Name : </b>" + fileName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify copied files in Destination Site",
						"File not copied in destination site" + "<br /><b> File Name : </b>" + fileName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify copied files in Destination Site",
					"Verify copied files in Destination Site Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void verifyMovedFilesInDestinationSite(String fileName, String targetSiteName) {
		try {
			if (docLibPage.isFileIsAvailable(fileName)) {
				report.updateTestLog("Verify moved files in Destination Site",
						"File verified succesfully" + "<br /><b> File Name : </b>" + fileName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify moved files in Destination Site",
						"File not moved in destination site" + "<br /><b> File Name : </b>" + fileName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Linked files in Destination Site",
					"Verify Linked files in Destination Site Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void verifyLinkedFilesInDestinationSite(String fileName, String targetSiteName) {
		try {
			if (docLibPage.isFileIsAvailable(fileName)) {
				report.updateTestLog("Verify Linked files in Destination Site",
						"File verified succesfully" + "<br /><b> File Name : </b>" + fileName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Linked files in Destination Site",
						"File not linked in destination site" + "<br /><b> File Name : </b>" + fileName
								+ "<br /><b> Destination Site Name : </b>" + targetSiteName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify moved files in Destination Site",
					"Verify moved files in Destination Site Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void verifyLinkedFile(String fileName, String siteName, String folderName) {
		try {
			if (docLibPage.isFileIsAvailable(fileName)) {
				report.updateTestLog("Verify Linked file",
						"File:" + fileName + " linked succesfully to folder:" + folderName + " using site:" + siteName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Linked file", "File:" + fileName + " failed to link succesfully to folder:"
						+ folderName + " using site:" + siteName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Linked file", "File:" + fileName + " failed to link succesfully to folder:"
					+ folderName + " using site:" + siteName, Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void verifyXOptionInFollowedFiles(String fileName) {
		try {
			if (docLibPage.isDisplayedUnfollowLink(fileName)) {
				docLibPage.mouseOverOnUnFollowedOption(fileName);
				report.updateTestLog("Verify 'X' option in followed files",
						"'X' option verified succesfully" + "<br /><b> File Name : </b>" + fileName, Status.PASS);
			} else {
				report.updateTestLog("Verify 'X' option in followed files",
						"'X' option not verified" + "<br /><b> File Name : </b>" + fileName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify 'X' option in followed files", "Verify 'X' option in followed files Failed",
					Status.FAIL);
		}
	}

	// Check Follow link for file
	public void verifyTickSignBeforeFollowOptionForFile(String fileName) {
		try {
			if (docLibPage.isDisplayedFollowLink(fileName)) {
				report.updateTestLog("Verify 'tick' sign before Follow option for files in document library",
						"User able to view the <b>'tick'</b> sign before Follow option for file:<b>" + fileName
								+ "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'tick' sign before Follow option for files in document library",
						"User not able to view the <b>'tick'</b> sign before Follow option for file:<b>" + fileName
								+ "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyAttachLifecycleStateForLifecycleAttachFiles(String fileOrFolderName, String docActionVal) {
		try {

			isDiaplyedMoreSettingsHiddenValue = docLibPage.getMoreHiddenActions(fileOrFolderName, docActionVal);

			if (!isDiaplyedMoreSettingsHiddenValue) {
				report.updateTestLog("Verify 'Attach LifeCycle State'",
						"User is not able to attach lifecycle state for already lifecycle attached files/folder:<b>"
								+ fileOrFolderName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Attach LifeCycle State'",
						"User is not able to attach lifecycle state for already lifecycle attached files/folder:<b>"
								+ fileOrFolderName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Verify uploaded file property values in Document Details Page
	public void verifyLifecycleTags(String lifecycleValues, String attributesFor, String fileOrFolderName) {
		try {
			fileOrFoldeTagsList = docLibPage.getLifecycleTags(fileOrFolderName);

			if (lifecycleValues.contains(";")) {
				splittedLifecycleTags = lifecycleValues.split(";");

			} else if (lifecycleValues.contains(",")) {
				splittedLifecycleTags = lifecycleValues.split(",");
			} else {
				expectedTagsList.add(lifecycleValues);
			}

			if (splittedLifecycleTags != null & splittedLifecycleTags.length > 0) {
				for (String expPropValue : splittedLifecycleTags) {
					expectedTagsList.add(expPropValue);
				}
			}

			if (UIHelper.compareTwoDiffSizeOfLists(expectedTagsList, fileOrFoldeTagsList)) {
				report.updateTestLog("Verify " + attributesFor + " tagss in Document Library section", attributesFor
						+ "tags are displayed in Document Library section for file:<b>" + fileOrFolderName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + attributesFor + " tagss in Document Library section",
						attributesFor + "attributes are failed to displayed in Document Library section for file:<b>"
								+ fileOrFolderName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	public void verifyBrowseAction(String browseActionName, String fileOrFolderName) {
		try {
			browseActionsList = docLibPage.getBrowseActionOptions(fileOrFolderName);

			for (String actualVal : browseActionsList) {
				if (actualVal.contains(browseActionName)) {
					isDisplayedBrowseAction = true;
					break;
				} else {
					isDisplayedBrowseAction = false;
				}
			}

			if (isDisplayedBrowseAction) {
				report.updateTestLog(
						"Verify the '" + browseActionName + "' Option for any files/folder from Browse actions",
						"User able to view the '" + browseActionName + "' Option for any files/folder:<b>"
								+ fileOrFolderName + "</b>" + " from Browse actions",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the '" + browseActionName + "' Option for any files/folder from Browse actions",
						"User unable to view the '" + browseActionName + "' Option for any files/folder:<b>"
								+ fileOrFolderName + "</b>" + " from Browse actions",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to verify the image previewed or not
	 * 
	 * @param fileName
	 * @author 412766
	 */
	public void verifyImagePreviewed(String fileName) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).isImagePreviewed()) {
				report.updateTestLog("Check for image preview",
						fileName + " File previewed successfully" + "<br /><b>File Name : </b>" + fileName,
						Status.PASS);
			} else {
				report.updateTestLog("Check for image preview",
						fileName + " File not previewed" + "<br /><b>File Name : </b>" + fileName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Check for image preview", e.getMessage(), Status.FAIL);
		}
	}

	public void verifyConfirmationDailogMessage(String expectedMessageVal) {
		try {

			String actualMessageVal = docLibPage.getTheMessageText();

			if (actualMessageVal != null && actualMessageVal.equalsIgnoreCase(expectedMessageVal)) {
				report.updateTestLog("Verify Confirmation Message Dailog Text",
						"User able to see the confirmation mesaage as: " + expectedMessageVal, Status.PASS);
			} else {
				report.updateTestLog("Verify Confirmation Message Dailog Text",
						"User not able to see the confirmation mesaage as: " + expectedMessageVal, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Confirmation Message Dailog Text",
					"User not able to see the confirmation message as: ", Status.FAIL);
		}
	}

	public void verifyLoadingMessage(String expectedMessageVal) {
		try {

			String actualMessageVal = docLibPage.getTheLoadingMessageText(expectedMessageVal);

			System.out.println("Message" + actualMessageVal);

			if (actualMessageVal != null && actualMessageVal.contains(expectedMessageVal)) {
				report.updateTestLog("Verify Confirmation Message Dailog Text",
						"User able to see the confirmation mesaage as: " + expectedMessageVal, Status.PASS);

			} else {
				report.updateTestLog("Verify Confirmation Message Dailog Text",
						"User not able to see the confirmation mesaage as: " + expectedMessageVal, Status.FAIL);
			}
		} catch (Exception e) {
			System.out.println("Verify Confirmation Message Dailog Text"
					+ "User able to see the confirmation mesaage as: " + expectedMessageVal + ", Status Exception");
			e.printStackTrace();
		}
	}

	public void verifyFolderMessage(String expectedMessageVal) {
		try {

			String actualMessageVal = docLibPage.getTheFolderMessageText();

			if (actualMessageVal != null && actualMessageVal.equalsIgnoreCase(expectedMessageVal)) {
				report.updateTestLog("Verify Confirmation Message Dailog Text",
						"User able to see the confirmation mesaage as: " + expectedMessageVal, Status.PASS);

			} else {
				report.updateTestLog("Verify Confirmation Message Dailog Text",
						"User not able to see the confirmation mesaage as: " + expectedMessageVal, Status.FAIL);
			}
		} catch (Exception e) {
			System.out.println("Verify Confirmation Message Dailog Text"
					+ "User able to see the confirmation mesaage as: " + expectedMessageVal + ", Status Exception");
			e.printStackTrace();
		}
	}

	// To Check count of selected files matches in document library and Link To
	// PopUp
	public void verifyCountOfSelectedFromLinkPopUp(int selectedCount, int countFromPopUp) {

		try {
			if (selectedCount == countFromPopUp) {
				report.updateTestLog("Number of Items", "Number of Items Selected are:" + selectedCount, Status.PASS);
			} else {
				report.updateTestLog("Number of Items", "Number of Items Selected mismatches", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To Verify Option in More Link Options
	public void verifyOptionInMoreLinkOptions(String fileName, String propertyName) {
		try {
			if (new AlfrescoSitesPage(scriptHelper).findOptionInMoreLinkOptions(fileName, propertyName)) {
				report.updateTestLog("Verify" + propertyName + "Option",
						"<br/><b>Expected Result:</b>" + propertyName + "Option should be not available <br/>"
								+ "<b>Actual Result</b>" + propertyName + "Option is  available",
						Status.FAIL);
			} else {
				report.updateTestLog("Verify" + propertyName + "Option",
						"<br/><b>Expected Result:</b>" + propertyName + "Option should be not available <br/>"
								+ "<b>Actual Result</b>" + propertyName + "Option is not available",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Option", e.getMessage(), Status.FAIL);
		}
	}

	// Check Library folder structure in Document Library Page
	public void verifyLibraryFoldersForSiteInDocLibPage(String createSiteDetails1, String createSiteDetails2) {
		try {

			expectedSiteNamesList.add(createSiteDetails1);
			expectedSiteNamesList.add(createSiteDetails2);

			actualDocLibFoldersList = docLibPage.getDocLibFolderDetails();

			isDisplayedLibDocumentLibVal = docLibPage.isDisplayedLibraryFolderSubFolder(expectedSiteNamesList);

			if (UIHelper.compareTwoSimilarLists(actualDocLibFoldersList, expectedSiteNamesList)) {
				report.updateTestLog(
						"Verify component sites folder structure for Profram site in 'Document Library' section",
						"<b>Component sites:</b>" + expectedSiteNamesList
								+ " are displayed as subfolder folder under document library of the program site"
								+ "<br /><b>Expected Component Site folder Names:</b> " + expectedSiteNamesList
								+ ", <br /><b>Actual Component Site folder Names:</b> " + actualDocLibFoldersList,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify component sites folder structure for Profram site in 'Document Library' section",
						"<b>Component sites:</b>" + expectedSiteNamesList
								+ " are failed to display as subfolder folder under document library of the program site"
								+ "<br /><b>Expected Component Site folder Names:</b> " + expectedSiteNamesList
								+ ", <br /><b>Actual Component Site folder Names:</b> " + actualDocLibFoldersList,
						Status.FAIL);
			}

			if (isDisplayedLibDocumentLibVal) {
				report.updateTestLog("Verify component sites sub folder for Profram site in 'Document Library' section",
						"<br/><b>Component folders:</b>" + expectedSiteNamesList
								+ " successfully have a sub folder: 'document library'"
								+ "<br /><b>Expected Component Site sub folder Names:</b> [documentLibrary, documentLibrary]"
								+ ", <br /><b>Actual Component Site sub folder Names:</b> ["
								+ docLibPage.componentFolderName + "," + docLibPage.componentFolderName + "]",
						Status.PASS);
			} else {
				report.updateTestLog("Verify component sites sub folder for Profram site in 'Document Library' section",
						"<br/><b>Component folders:</b>" + expectedSiteNamesList
								+ " failed to have a sub folder: 'document library'"
								+ "<br /><b>Expected Component Site sub folder Names:</b> " + expectedSiteNamesList
								+ ", [documentLibrary, documentLibrary]"
								+ ", <br /><b>Actual Component Site sub folder Names:</b> " + actualDocLibFoldersList
								+ ", [" + docLibPage.componentFolderName + "," + docLibPage.componentFolderName + "]",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the site name from expected site details(from excel)
	public ArrayList<String> getSiteNamesFromCreateSiteDetails(String createSiteDetails) {
		try {
			if (expectedSiteNamesList != null) {
				expectedSiteNamesList.clear();
			}

			if (createSiteDetails.contains(";")) {
				String splittedSiteDetailas[] = createSiteDetails.split(";");
				for (String siteValues : splittedSiteDetailas) {
					String splittedSiteValues[] = siteValues.split(",");

					String siteName = splittedSiteValues[0].replace("Name-", "");

					expectedSiteNamesList.add(siteName);

				}
			} else {
				String splittedSiteValues[] = createSiteDetails.split(",");

				String siteName = splittedSiteValues[0].replace("Name-", "");
				expectedSiteNamesList.add(siteName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return expectedSiteNamesList;
	}

	/**
	 * @author 412766
	 */
	public void verifyFileUplodedInComponentSite(String fileName) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).isFileIsAvailable(fileName)) {
				report.updateTestLog(
						"Verify the file Uploded in 'Component Site', that should be available in 'Program Site'",
						"File Available in 'Program Site'" + "<br/> <b>Expected Result : </b> File Name - " + fileName
								+ "<br/> <b>Actual Result : </b> File Name - " + fileName,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the file Uploded in 'Component Site', that should be available in 'Program Site'",
						"File Not Available in 'Program Site'" + "<br/> <b>Expected Result : </b> File Name - "
								+ fileName + "<br/> <b>Actual Result : </b> File Not Found",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the file Uploded in 'Component Site', that should be available in 'Program Site'",
					"Verify the file Uploded in 'Component Site', that should be available in 'Program Site'  Failed",
					Status.FAIL);
		}
	}

	// Check whether program related folder are available in Doc lib
	public void verifyProgramLibFolder(String SiteName) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, folderXpath);
			if (UIHelper.findAnElementbyXpath(driver, folderXpath).getText().contains(SiteName)) {
				UIHelper.highlightElement(driver, folderXpath);

				report.updateTestLog("Verify Program Library Section",
						"Verify program folder in program library section of Component site:"
								+ "<br /><b> Expected Folder Name : </b>" + SiteName
								+ "<br /><b> Actual Folder Name :</b>"
								+ UIHelper.findAnElementbyXpath(driver, folderXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Library Section",
						"Verify program folder in program library section of Component site:"
								+ "<br /><b> Expected Folder Name : </b>" + SiteName
								+ "<br /><b> Actual Folder Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver, folderXpath).getText(),
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Program Library Section",
					"Verify program folder in program library section of Component site:"
							+ "<br /><b> Expected Folder Name : </b>" + SiteName + "<br /><b> Actual Folder Name : </b>"
							+ UIHelper.findAnElementbyXpath(driver, folderXpath).getText(),
					Status.FAIL);
		}

	}

	// Check whether program Component related folder are available in Doc lib
	public void verifyProgramFolder(String SiteName) {
		String finalfolderXpath = progfolderXpath.replace("CRAFT", SiteName);
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfolderXpath);
			if (UIHelper.findAnElementbyXpath(driver, finalfolderXpath).getText().contains(SiteName)) {
				UIHelper.highlightElement(driver, finalfolderXpath);

				report.updateTestLog("Verify Program Folder",
						"Verify folder in Document library section." + "<br /><b> Expected Folder Name : </b>"
								+ SiteName + "<br /><b> Actual Folder Name :</b>"
								+ UIHelper.findAnElementbyXpath(driver, finalfolderXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Library Section",
						"Verify folder in Document library section." + "<br /><b> Expected Folder Name : </b>"
								+ SiteName + "<br /><b> Actual Folder Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver, finalfolderXpath).getText(),
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Program Library Section",
					"Verify program folder in program library section of Component site:"
							+ "<br /><b> Expected Folder Name : </b>" + SiteName + "<br /><b> Actual Folder Name : </b>"
							+ UIHelper.findAnElementbyXpath(driver, finalfolderXpath).getText(),
					Status.FAIL);
		}

	}

	// Check whether program Component document lib
	public void verifyProgramCompDocLib(String SiteName) {
		String finalDocLibXpath = breadcrumbXpath.replace("CRAFT", SiteName);
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalDocLibXpath);
			if (UIHelper.findAnElementbyXpath(driver, finalDocLibXpath).getText().contains(SiteName)) {
				UIHelper.highlightElement(driver, finalDocLibXpath);

				report.updateTestLog("Verify Program Component Doc Library",
						"Verify folder in Document library section." + "<br /><b> Expected Site Doc Lib : </b>"
								+ SiteName + "<br /><b> Actual Site Doc Lib : </b>"
								+ UIHelper.findAnElementbyXpath(driver, finalDocLibXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Component Doc Library",
						"Verify folder in Document library section." + "<br /><b> Expected Site Doc Lib : </b>"
								+ SiteName + "<br /><b> Actual Site Doc Lib : </b>"
								+ UIHelper.findAnElementbyXpath(driver, finalDocLibXpath).getText(),
						Status.FAIL);
			}

		} catch (Exception e) {

			report.updateTestLog("Verify Program Component Doc Library",
					"Verify folder in Document library section." + "<br /><b> Expected Site Doc Lib : </b>" + SiteName
							+ "<br /><b> Actual Site Doc Lib : </b>"
							+ UIHelper.findAnElementbyXpath(driver, finalDocLibXpath).getText(),
					Status.FAIL);
		}

	}

	// Check whether program Component document lib
	public void verifyProgramDocLib(String SiteName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, docBreadcrumbXpath);
			if (UIHelper.findAnElementbyXpath(driver, docBreadcrumbXpath).isDisplayed()) {
				UIHelper.highlightElement(driver, docBreadcrumbXpath);

				report.updateTestLog("Verify Site Doc Library",
						"Verify site Document library section." + "<br /><b> Site Name : </b>" + SiteName
								+ "<br /><b> Page Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver, docBreadcrumbXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Doc Library",
						"Verify Program site Document library section." + "<br /><b> Site Name : </b>" + SiteName
								+ "<br /><b> Page Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver, docBreadcrumbXpath).getText(),
						Status.FAIL);
			}

		} catch (Exception e) {

			report.updateTestLog("Verify Program Doc Library",
					"Verify Program site Document library section." + "<br /><b> Site Name : </b>" + SiteName
							+ "<br /><b> Page Name : </b>"
							+ UIHelper.findAnElementbyXpath(driver, docBreadcrumbXpath).getText(),
					Status.FAIL);
		}

	}

	// Navigate to Document Library
	public void VerifyDocLibAccess() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Navigate to Document Library", "Navigation to Document Library page successfully",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate to Document Library", "Navigation to Document Library page failed",
					Status.FAIL);
		}
	}

	// Verify Document Category
	public void VerifyDocCategory() {
		try {
			String fileName = dataTable.getData("MyFiles", "FileName");
			String categoryName = dataTable.getData("Admin", "CategoryName");
			String finalcategoryXpath = categoryXpath.replace("CRAFT", fileName).replace("REPLACE", categoryName);
			UIHelper.waitForPageToLoad(driver);
			// Thread.sleep(2000);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalcategoryXpath);
			UIHelper.highlightElement(driver, finalcategoryXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Verify category filter", "Category filter in Document Library page successfully",
					Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Verify catefory filter", "Category filter in Document Library page failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyLockedFilesOrFolderByDeleting(String folderOrFileName) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).isNotDeleteMessageDisplayed()) {
				Thread.sleep(1000);
				report.updateTestLog("Verify delete a folder if it has a file that is checked out for editing purpose",
						"User unable to delete a folder which have the locked files"
								+ "<br /><b> Folder/File Name : </b>" + folderOrFileName,
						Status.PASS);
			} else {
				Thread.sleep(1000);
				report.updateTestLog("Verify delete a folder if it has a file that is checked out for editing purpose",
						"User able to delete a folder which have the locked files" + "<br /><b> Folder/File Name : </b>"
								+ folderOrFileName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify delete a folder if it has a file that is checked out for editing purpose",
					"Verify delete a folder if it has a file that is checked out for editing purpose Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyUnLockedFilesOrFolderByDeleting(String folderOrFileName, String deleteType) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).isDeleteMessageDisplayed(folderOrFileName, deleteType)) {
				Thread.sleep(1000);
				report.updateTestLog(
						"Verify delete a folder if it has a no file that is checked out for editing purpose",
						"User able to delete a folder which not have the locked files"
								+ "<br /><b> Folder/File Name : </b>" + folderOrFileName,
						Status.PASS);
			} else {
				Thread.sleep(1000);
				report.updateTestLog(
						"Verify delete a folder if it has a no file that is checked out for editing purpose",
						"User unable to delete a folder which not have the locked files"
								+ "<br /><b> Folder/File Name : </b>" + folderOrFileName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify delete a folder if it has a no file that is checked out for editing purpose",
					"Verify delete a folder if it has a no file that is checked out for editing purpose Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyDeleteEmptyFolder(String folderOrFileName, String deleteType) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).isDeleteMessageDisplayed(folderOrFileName, deleteType)) {
				Thread.sleep(1000);
				report.updateTestLog("Verify delete a empty folder if it has no file in edit offline mode",
						"User able to delete a empty folder" + "<br /><b> Folder Name : </b>" + folderOrFileName,
						Status.PASS);
			} else {
				Thread.sleep(1000);
				report.updateTestLog("Verify delete a empty folder if it has no file in edit offline mode",
						"User unable to delete a empty folder" + "<br /><b> Folder Name : </b>" + folderOrFileName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify delete a empty folder if it has no file in edit offline mode",
					"Verify delete a empty folder if it has no file in edit offline mode Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyDeleteFileByNavigatingFolder(String fileName, String deleteType) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).isDeleteMessageDisplayed(fileName, deleteType)) {
				Thread.sleep(1000);
				report.updateTestLog("Verify delete a file by navigating mutiple folders",
						"User able to delete a file" + "<br /><b> File Name : </b>" + fileName, Status.PASS);
			} else {
				Thread.sleep(1000);
				report.updateTestLog("Verify delete a file by navigating mutiple folders",
						"User unable to delete a file" + "<br /><b> File Name : </b>" + fileName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify delete a file by navigating mutiple folders",
					"Verify delete a file by navigating mutiple folders", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyDeleteFolderWithAspectsApplied(String folderOrFileName, String deleteType) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).isDeleteMessageDisplayed(folderOrFileName, deleteType)) {
				Thread.sleep(1000);
				report.updateTestLog("Verify to delete a folder with applied Aspects",
						"User able to delete a folder" + "<br /><b> Folder Name : </b>" + folderOrFileName,
						Status.PASS);
			} else {
				Thread.sleep(1000);
				report.updateTestLog("Verify to delete a folder with applied Aspects",
						"User unable to delete a folder" + "<br /><b> Folder Name : </b>" + folderOrFileName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify to delete a folder with applied Aspects",
					"Verify to delete a folder with applied Aspects Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyDeleteForUnZippedFolder(String folderOrFileName, String deleteType) {
		try {
			if (new AlfrescoDocumentLibPage(scriptHelper).isDeleteMessageDisplayed(folderOrFileName, deleteType)) {
				Thread.sleep(1000);
				report.updateTestLog("Verify to delete a UnZipped folder",
						"User able to delete a UnZipped folder" + "<br /><b> Folder Name : </b>" + folderOrFileName,
						Status.PASS);
			} else {
				Thread.sleep(1000);
				report.updateTestLog("Verify to delete a UnZipped folder",
						"User unable to delete a UnZipped folder" + "<br /><b> Folder Name : </b>" + folderOrFileName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify to delete a UnZipped folder", "Verify to delete a UnZipped folder Failed",
					Status.FAIL);
		}
	}

	// Verify the Favorite document
	public void verifyFavoptionselected() {
		try {
			String fileName = dataTable.getData("MyFiles", "FileName");
			String finalDocFavEnabled = docFavoriteEnabledXpath.replace("CRAFT", fileName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.checkForAnElementbyXpath(driver, finalDocFavEnabled);
			UIHelper.highlightElement(driver, finalDocFavEnabled);
			UIHelper.waitFor(driver);
			report.updateTestLog("Verify Favorite option enabled",
					"Favorite option enabled in Document Library page successfully", Status.PASS);
		}

		catch (Exception e) {
			report.updateTestLog("Verify catefory filter", "Category filter in Document Library page failed",
					Status.FAIL);
		}
	}

	// Verify the Favorite document
	public void verifyoptioninHeaderbar(String linkName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalheaderBarOpt = headerBarOptXpath.replace("CRAFT", linkName);
			UIHelper.highlightElement(driver, headerBarXpath);
			if (!UIHelper.checkForAnElementbyXpath(driver, finalheaderBarOpt)) {
				report.updateTestLog("Verify" + linkName + "option", linkName + " option not available in the page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify" + linkName + "option", linkName + " option available in the page",
						Status.FAIL);
			}
		}

		catch (Exception e) {
			report.updateTestLog("Verify" + linkName + "option", linkName + " option available in the page",
					Status.FAIL);
		}
	}

	// Check whether browse option 'NOT' have specified option
	public void verifyBrowseActionopt(String browseActionName, String fileOrFolderName) {
		try {
			browseActionsList = docLibPage.getBrowseActionOptions(fileOrFolderName);

			for (String actualVal : browseActionsList) {
				if (!actualVal.contains(browseActionName)) {
					isDisplayedBrowseAction = true;
					break;
				} else {
					isDisplayedBrowseAction = false;
				}
			}

			if (isDisplayedBrowseAction) {
				report.updateTestLog("Verify " + browseActionName + " option",
						browseActionName + " option not available in the more option of file", Status.PASS);
			} else {
				report.updateTestLog("Verify " + browseActionName + " option",
						browseActionName + " option available in the more option of file", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify " + browseActionName + " option",
					browseActionName + " option available in the more option of file", Status.FAIL);
		}
	}

	// Verify the Delete Pop up
	public void verifyDelPopUp() {
		try {
			String fileName = "Are you sure you want to delete 'AlfrescoAutoDemoForTest.docx'?";

			if (UIHelper.findAnElementbyXpath(driver, deletePopupXPath).getText().trim()
					.equalsIgnoreCase(fileName.trim())) {
				report.updateTestLog("Verify Delete Pop up Window",
						"Delete Pop up window displayed Successfully and no duplicate file name displayed",
						Status.PASS);
			}

		}

		catch (Exception e) {
			report.updateTestLog("Verify Delete Pop up Window",
					"Delete Pop up window displayed Successfully and file name displayed wrongly"
							+ UIHelper.findAnElementbyXpath(driver, deletePopupXPath).getText().trim(),
					Status.FAIL);
		}
	}

	// Check File is Published
	public void verifyFilePublished(String fileName) {
		try {
			if (docLibPage.isDisplayedPublishedTick(fileName)) {
				report.updateTestLog("Verify green coloured 'tick' sign after publishing files in document library",
						"User able to view the green coloured <b>'tick'</b> sign after publishing file:<b>" + fileName
								+ "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify green coloured 'tick' sign after publishing files in document library",
						"User NOT able to view the green coloured <b>'tick'</b> sign after publishing file:<b>"
								+ fileName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param folderName
	 */
	public void verifyFolderPublished(String folderName) {
		try {
			if (docLibPage.isDisplayedPublishedTick(folderName)) {
				report.updateTestLog("Verify green coloured 'tick' sign after publishing folder in document library",
						"User able to view the green coloured <b>'tick'</b> sign after publishing folder:<b>"
								+ folderName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify green coloured 'tick' sign after publishing folder in document library",
						"User NOT able to view the green coloured <b>'tick'</b> sign after publishing folder:<b>"
								+ folderName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check File is Published
	public void verifyFilePublishedNotCopied(String fileName) {
		try {
			if (!docLibPage.isDisplayedPublishedTick(fileName)) {
				report.updateTestLog(
						"Verify green coloured 'tick' not present in the Copied folder sign after publishing files in document library",
						"User NOT able to view the green coloured <b>'tick'</b> sign after Copying file:<b>" + fileName
								+ "</b>",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify green coloured 'tick' not present in the Copied folder sign after publishing files in document library",
						"User  able to view the green coloured <b>'tick'</b> sign after Copying file:<b>" + fileName
								+ "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyFileInSecondaryLinkFolder(ArrayList<String> fileNames) {
		try {
			boolean flag = false;
			AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
			for (String fileName : fileNames) {
				if (docLibPage.isFileIsAvailable(fileName)) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}

			if (flag) {
				report.updateTestLog("Verify Files in Secondary Link Folder",
						"Files verified Successfully" + "<br/><b>Files verified : </b> " + fileNames, Status.PASS);
			} else {
				report.updateTestLog("Verify Files in Secondary Link Folder",
						"Files not verified" + "<br/><b>Files verified : </b> " + fileNames, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyFileSortedAsLfeCycle(String fileName) {
		try {
			// System.out.println("i
			// verify"+uploadedFilesTitlesXpath1+fileName[0]+fileName[1]+fileName[2]);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			/*
			 * UIHelper.waitFor(driver);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * uploadedFilesTitlesXpath1);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * uploadedFilesTitlesXpath2);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * uploadedFilesTitlesXpath3);
			 */

			/*
			 * System.out.println("after wait verify"); String file1 =
			 * UIHelper.getTextFromWebElement(driver,
			 * uploadedFilesTitlesXpath1); String file2 =
			 * UIHelper.getTextFromWebElement(driver,
			 * uploadedFilesTitlesXpath2); String file3 =
			 * UIHelper.getTextFromWebElement(driver,
			 * uploadedFilesTitlesXpath3);
			 * 
			 * System.out.println(file1+"file 1"+fileName[0]);
			 * System.out.println(file2+"file 1"+fileName[1]);
			 * System.out.println(file3+"file 1"+fileName[2]);
			 */
			// String fileXPath1 = uploadedFilesTitlesXpath1.replace("CRAFT",
			// fileName);
			/*
			 * String fileXPath2 = uploadedFilesTitlesXpath1.replace("CRAFT",
			 * fileName[1]); String fileXPath3 =
			 * uploadedFilesTitlesXpath1.replace("CRAFT", fileName[2]);
			 */

			if (UIHelper.findAnElementbyXpath(driver, uploadedFilesTitlesXpath).getText().trim()
					.equalsIgnoreCase(fileName)) {
				report.updateTestLog(
						"Verify whether user is able to sort assets based on life cycle status in document library",
						"Folders are sorted successfuly", Status.PASS);

			}

			/*
			 * if (file1.equalsIgnoreCase(fileName[0]) &&
			 * file2.equalsIgnoreCase(fileName[1])) { report.updateTestLog(
			 * "Verify whether user is able to sort assets based on life cycle status in document library"
			 * , "Folders are sorted successfuly", Status.PASS); }
			 */
		} catch (Exception e) {
			report.updateTestLog(
					"Verify whether user is able to sort assets based on life cycle status in document library",
					"Folders are NOT sorted successfuly", Status.FAIL);
			e.printStackTrace();
		}
	}

	// Check File is Published
	public void verifyFilePublishedInNonPubSite(String fileName) {
		try {
			if (!docLibPage.isDisplayedPublishedTick(fileName)) {
				report.updateTestLog(
						"Verify green coloured 'tick' not present in the folder sign after publishing files in document library",
						"User NOT able to view the green coloured <b>'tick'</b> sign that is published in Non publishing Site:<b>"
								+ fileName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify green coloured 'tick' not present in the folder sign after publishing files in document library",
						"User able to view the green coloured <b>'tick'</b> sign that is published in Non publishing Site:<b>"
								+ fileName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check File is Published
	public void verifyUnPublishFldrDocActions() {
		// UIHelper.waitForVisibilityOfEleByXpath(driver, stsRprtXPath);
		// UIHelper.waitForVisibilityOfEleByXpath(driver, pubHistXPath);
		try {
			if (!(UIHelper.findAnElementbyXpath(driver, stsRprtXPath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, pubHistXPath).isDisplayed())) {
				report.updateTestLog("Verify the Un Published Folder Doc Actions Menus",
						"Un published folder does not have  Status Report, Publication History and Review History Options as expected",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Un Published Folder Doc Actions Menus",
						"Un published folder have  Status Report, Publication History and Review History Options as expected",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check File is Published
	public void verifyOrangeExclamationMarkForPublishedFileOrFolder(String fileOrFolderName) {
		try {
			if (docLibPage.isDisplayedOrangeExclamationMarkForPublishedFolder(fileOrFolderName)) {
				report.updateTestLog(
						"Verify orange coloured 'exclamation' indicator after publishing file/folder and changing the file/folder status in document library",
						"User able to view the orange coloured <b>'exclamation'</b> indicator for the  File/Folder:<b>"
								+ fileOrFolderName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify orange coloured 'exclamation' indicator after publishing file/folder and changing the file/folder status in document library",
						"User not able to view the orange coloured <b>'exclamation'</b> indicator for the  File/Folder:<b>"
								+ fileOrFolderName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Published Object in Review Changes Popup
	public void verifyPublishedObjectInReviewChangesPopup(String fileName) {
		try {
			if (docLibPage.checkModeifiedFileDetailsInReviewChangesPopup(fileName)) {
				report.updateTestLog("Verify published object in Review Changes Popup",
						"Published object: <b>'" + fileName + "'</b> is displayed successfully in Review Changes Popup",
						Status.PASS);
			} else {
				report.updateTestLog("Verify published object in Review Changes Popup",
						"Published object: <b>'" + fileName + "'</b> is failed to display in Review Changes Popup",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Status Report Values in Status Report Popup
	public void verifyPublishedFileDetailsInStatusReportPopup(String siteVal, String fileNameVal,
			String publishedDateVal, String publishedStatus) {
		try {
			boolean isDisplayedPublishedFileDetails = false;
			String statusReportValueFromApp = docLibPage.getTextFromStatusReportPopup();

			if (statusReportValueFromApp.contains(siteVal) && statusReportValueFromApp.contains(fileNameVal)
					&& statusReportValueFromApp.contains(publishedDateVal)
					&& statusReportValueFromApp.contains(publishedStatus)) {
				isDisplayedPublishedFileDetails = true;
			} else {
				isDisplayedPublishedFileDetails = false;
			}

			if (isDisplayedPublishedFileDetails) {
				report.updateTestLog("Verify published object in Status Report Popup",
						"Published object details are displayed successfully in Status Report Popup"
								+ "<br/><b>Status Report Popup Values:</b>" + "<br/>Site: " + siteVal
								+ "<br/>Alfresco path for root item: " + fileNameVal + "<br/>Publish Date: "
								+ publishedDateVal + "<br/>Last Publish Status: " + publishedStatus,
						Status.PASS);
			} else {
				report.updateTestLog("Verify published object in Status Report Popup",
						"Published object details are failed to display in Status Report Popup"
								+ "<br/><b>Status Report Popup Values:</b>" + "<br/>Site: " + siteVal
								+ "<br/>Alfresco path for root item: " + fileNameVal + "<br/>Publish Date: "
								+ publishedDateVal + "<br/>Last Publish Status: " + publishedStatus,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Status Report Values in Status Report Popup
	public void verifyPublishedFileDetailsInStatusReportPopup(String message) {
		try {
			boolean isDisplayedPublishedFileDetails = false;
			String statusReportValueFromApp = docLibPage.getTextFromStatusReportPopup();

			if (statusReportValueFromApp.contains(message)) {
				isDisplayedPublishedFileDetails = true;
			} else {
				isDisplayedPublishedFileDetails = false;
			}

			if (isDisplayedPublishedFileDetails) {
				report.updateTestLog("Verify published object in Status Report Popup",
						"Message: " + message + " is displayed successfully in Status Report Popup", Status.PASS);
			} else {
				report.updateTestLog("Verify published object in Status Report Popup",
						"Message: " + message + " is failed to displayed in Status Report Popup", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param popUpMsg
	 * @return
	 */
	public boolean isPopUpMsgDisplayed(String popUpMsg) {
		boolean flag = false;
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, popUpMsgXapth);
			String actualPopUpMsg = UIHelper.getTextFromWebElement(driver, popUpMsgXapth);
			System.out.println(actualPopUpMsg);
			System.out.println(popUpMsg);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popUpMsgXapth);
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, popUpMsgXapth);
			 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			 * popUpMsgXapth);
			 */
			if (actualPopUpMsg.contains(popUpMsg)) {
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
	 * 
	 * @return
	 */
	public boolean isUnzipPopUpDispayed() {
		boolean flag = false;
		try {
			Thread.sleep(1000);
			String xpath = ".//*[@id='message']/div/span[text()='The unzip operation could not be completed']";
			UIHelper.waitForVisibilityOfEleByXpath(driver, xpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, xpath))) {
				UIHelper.highlightElement(driver, xpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Check User Recent sites in Copy To Or Move To Popup
	public void verifyRecentSitesInCopyToOrMoveToPopup(ArrayList<String> expectedSitesList, String performedOperation) {
		try {
			ArrayList<String> actualRecentSitesList = docLibPage.getRecentSitesFromCopyOrMoveToPopup();

			boolean isDisplayedOnlyUSerSitesInRecentSites = UIHelper.compareTwoDiffSizeOfLists(actualRecentSitesList,
					expectedSitesList);

			if (isDisplayedOnlyUSerSitesInRecentSites) {
				report.updateTestLog("Verify 'Recent Sites' in " + performedOperation + " Popup",
						"User not able to see sites in pop up in which user is not a member", Status.PASS);
			} else {
				report.updateTestLog("Verify 'Recent Sites' in " + performedOperation + " Popup",
						"User able to see sites in pop up in which user is not a member", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check User Favorite sites in Copy To Or Move To Popup
	public void verifyFavoriteSitesInCopyToOrMoveToPopup(ArrayList<String> expectedSitesList,
			String performedOperation) {
		try {

			ArrayList<String> actualFavoriteSitesList = docLibPage.getFavoriteSitesFromCopyOrMoveToPopup();

			boolean isDisplayedOnlyUSerSitesInFavoriteSites = UIHelper.compareTwoDiffSizeOfLists(expectedSitesList,
					actualFavoriteSitesList);

			if (isDisplayedOnlyUSerSitesInFavoriteSites) {
				report.updateTestLog("Verify 'Favorite Sites' in " + performedOperation + " Popup",
						"User not able to see sites in pop up in which user is not a member", Status.PASS);
			} else {
				report.updateTestLog("Verify 'Favorite Sites' in " + performedOperation + " Popup",
						"User able to see sites in pop up in which user is not a member", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check All sites in Copy To Or Move To Popup
	public void verifyAllSitesInCopyToOrMoveToPopup(ArrayList<String> expectedSitesList, String performedOperation) {
		try {
			ArrayList<String> actualAllSitesList = docLibPage.getAllSitesFromCopyOrMoveToPopup();

			boolean isDisplayedOnlyUSerSitesInAllSites = UIHelper.compareTwoDiffSizeOfLists(expectedSitesList,
					actualAllSitesList);

			if (isDisplayedOnlyUSerSitesInAllSites) {
				report.updateTestLog("Verify 'All Sites' in " + performedOperation + " Popup",
						"User not able to see sites in pop up in which user is not a member", Status.PASS);
			} else {
				report.updateTestLog("Verify 'All Sites' in " + performedOperation + " Popup",
						"User able to see sites in pop up in which user is not a member", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Enabled Repository Sites in Copy To Or Move To Popup
	public void verifyEnabledRepositorySitesInCopyToOrMoveToPopup(ArrayList<String> expectedSitesList,
			String performedOperation) {
		try {
			ArrayList<String> actualRepositorySitesList = docLibPage.getEnabledRepositorySitesFromCopyOrMoveToPopup();

			boolean isDisplayedOnlyUSerSitesInRepositorySites = docLibPage
					.compareRepositorySitesWithMysites(expectedSitesList, actualRepositorySitesList);

			if (isDisplayedOnlyUSerSitesInRepositorySites) {
				report.updateTestLog("Verify 'Repository Sites' in " + performedOperation + " Popup",
						"User not able to see sites in pop up in which user is not a member", Status.PASS);
			} else {
				report.updateTestLog("Verify 'Repository Sites' in " + performedOperation + " Popup",
						"User able to see sites in pop up in which user is not a member", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Default Breadcumb Link Value
	public void verifyDefaultBreadcumbLinkValue(String folderName) {
		try {
			String actualBreadcumbVal = docLibPage.getDefaultBreadcumbLinkVal();

			if (actualBreadcumbVal.contains(folderName)) {
				report.updateTestLog("Verify Default Breadcumb Link Value",
						"Folder Name: " + folderName + " displayed successfully in Default Breadcumb link",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Default Breadcumb Link Value",
						"Folder Name: " + folderName + " failed to display in Default Breadcumb link", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify the like document
	public void verifyNooptionselected(String fileName, String Link) {
		try {
			// String fileName = dataTable.getData("MyFiles", "FileName");
			String finalDoclikeEnabled = doclikeEnabledXpath.replace("CRAFT", fileName).replace("LINK", Link);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalDoclikeEnabled);
			if (UIHelper.checkForAnElementbyXpath(driver, finalDoclikeEnabled)) {
				UIHelper.highlightElement(driver, finalDoclikeEnabled);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify" + Link + "option not enabled",
						Link + " option not enabled in Document Library page successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify" + Link + "option not enabled",
						Link + " option not enabled in Document Library page failed", Status.FAIL);
			}
		}

		catch (Exception e) {
			report.updateTestLog("Verify" + Link + "option not enabled",
					Link + " option not enabled in Document Library page failed", Status.FAIL);
		}
	}

	// Verify the like document
	public void verifyoptionselected(String fileName, String Link) {
		try {
			// String fileName = dataTable.getData("MyFiles", "FileName");
			String finalDoclikeEnabled = doclikeEnabledXpath.replace("CRAFT", fileName).replace("LINK", Link);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalDoclikeEnabled);
			if (UIHelper.checkForAnElementbyXpath(driver, finalDoclikeEnabled)) {
				UIHelper.highlightElement(driver, finalDoclikeEnabled);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify" + Link + "option enabled",
						Link + " option enabled in Document Library page successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify" + Link + "option not enabled",
						Link + " option enabled in Document Library page failed", Status.FAIL);
			}
		}

		catch (Exception e) {
			report.updateTestLog("Verify" + Link + "option not enabled",
					Link + " option not enabled in Document Library page failed", Status.FAIL);
		}
	}

	// Verify the like document
	public void verifyFileIcon(String fileName, String fileIcon) {
		try {

			String finalFileIconXapth = fileIconXapth.replace("CRAFT", fileName);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileIconXapth);
			UIHelper.highlightElement(driver, finalFileIconXapth);
			String fileIconName = UIHelper.findAnElementbyXpath(driver, finalFileIconXapth).getAttribute("src");

			if (fileIconName.contains(fileIcon)) {
				UIHelper.highlightElement(driver, finalFileIconXapth);
				report.updateTestLog("Verify Web resource icon",
						"Web resource icon displayed successfully <br><b> File Icon :</b>" + fileIcon, Status.PASS);

			} else {
				report.updateTestLog("Verify Web resource icon",
						"Web resource icon is not displayed successfully <br><b> File Icon :</b>" + fileIcon,
						Status.FAIL);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Web resource icon", "Web resource icon is not displayed", Status.FAIL);
		}
	}

	// verify folder size
	public void verifyFolderSize() {
		try {
			String folderSizeVal = docLibPage.getFolderSize();

			if (!folderSizeVal.equalsIgnoreCase("Size Not get displayed for selected folders")) {
				report.updateTestLog("Verify size of the selected folders",
						"Size calculated working as expected. Message : '" + folderSizeVal, Status.PASS);
			} else {
				report.updateTestLog("Verify size of the selected folders",
						"Size calculation failed. Error message :'" + folderSizeVal, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify size of the selected folders", "Size calculation failed", Status.FAIL);
		}
	}

	// Check content category in Doc Lib
	public void verifyContentCategoryItemInDocLibPag(String fileOrFolderName, String categoryItem) {
		try {
			if (docLibPage.isDisplayedCategoryItemLinkInDocLibPag(fileOrFolderName, categoryItem)) {
				report.updateTestLog("Verify '" + categoryItem + "' option for folders in document library",
						"User able to view the <b>'" + categoryItem + "'</b> link option for <b>" + fileOrFolderName
								+ "</b>",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify '" + categoryItem + "' option for " + fileOrFolderName + " in document library",
						"User not able to view the <b>'" + categoryItem + "'</b> link option for <b>" + fileOrFolderName
								+ "</b>",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Just now is displayed in alfresco after uploading a file in
	// external share box

	public void verifyJustNowIsDisplayed(String FileorFolderName) {
		try {
			String finalXpathForShareFolderJustNow = tempXpathForShareFolderJustNow.replace("CRAFT", FileorFolderName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForShareFolderJustNow);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForShareFolderJustNow)) {
				UIHelper.highlightElement(driver, finalXpathForShareFolderJustNow);
				report.updateTestLog("Verify Just now is displayed present next to filename",
						"file Name: " + FileorFolderName + " Just now is displayed present next to filename",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Just now is displayed present next to filename",
						"file Name: " + FileorFolderName + " failed to display Just now present next to filename",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify the pop up msg
	public void verifyPopUp(String msg, String button) {
		try {
			String finalpopupbuttonXPath = popupbuttonXPath.replace("CRAFT", button);
			UIHelper.waitForVisibilityOfEleByXpath(driver, popupXPath);
			String actual = UIHelper.findAnElementbyXpath(driver, popupXPath).getText().trim();
			if (actual.contains(msg)) {
				report.updateTestLog("Verify Pop up Window",
						"Pop up window displayed Successfully. <br><b>Pop up Msg </b> :" + actual, Status.PASS);
			} else {
				report.updateTestLog("Verify Pop up Window", "Pop up window not displayed. Pup up msg" + actual,
						Status.FAIL);
			}
			UIHelper.click(driver, finalpopupbuttonXPath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, popupXPath);
			UIHelper.waitForPageToLoad(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify Pop up Window", "Pop up window not displayed ", Status.FAIL);//Modified as part of NALS
		}
	}

	// verify the label content of filter under Document list

	public void verifyDocumentFilterLabel() {
		String expectedLabel = dataTable.getData("Search", "Query");
		String actualLabel = null;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentFilterLabelXpath);
			UIHelper.highlightElement(driver, documentFilterLabelXpath);
			actualLabel = UIHelper.getTextFromWebElement(driver, documentFilterLabelXpath);
			if (actualLabel.equalsIgnoreCase(expectedLabel)) {
				report.updateTestLog("Verify Label content Under Doocument List Filter",
						"Label displayed as expected." + "<br /><b> Label Name : </b>" + actualLabel, Status.PASS);
			} else {
				report.updateTestLog("Verify Label content Under Doocument List Filter",
						"Label not displayed as expected." + "<br /><b> Actual label Name:  </b>" + actualLabel
								+ "<br /><b> Expected label Name:  </b>" + expectedLabel,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Label content Under Doocument List Filter", "Label not displayed",
					Status.FAIL);
		}
	}

	// verify Lifecycle Dropdown
	public void getLifeCycleDropDown(String dropDown, String lang) {
		try {
			ArrayList<String> lifeCycleDisplayList = new ArrayList<String>();
			lifeCycleDisplayList = myFilePageObj.getFileNames(dropDown);

			ArrayList<String> lifeCycleValuesList = new ArrayList<String>();
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, lifeCycleDropdownXpath),
					lifeCycleValuesList);
			System.out.println("attach option are " + lifeCycleValuesList);
			if (UIHelper.compareTwoSimilarLists(lifeCycleDisplayList, lifeCycleValuesList)) {
				report.updateTestLog("Verify LifeCycle dropdown is Listed in " + lang + "",
						"LifeCycle dropdown is successfully listed in " + lang + "", Status.PASS);
			} else {
				report.updateTestLog("Verify LifeCycle dropdown is Listed in " + lang + "",
						"LifeCycle dropdown is successfully listed in " + lang + "", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify LifeCycle dropdown is Listed in " + lang + "",
					"LifeCycle dropdown is successfully listed in " + lang + "", Status.FAIL);
		}
	}

	// verify Lifecycle Dropdown
	public void getPromoteLifeCycleDropDown(String dropDown, String lang) {
		try {
			ArrayList<String> prmtlifeCycleDisplayList = new ArrayList<String>();
			prmtlifeCycleDisplayList = myFilePageObj.getFileNames(dropDown);

			ArrayList<String> prmtlifeCycleValuesList = new ArrayList<String>();
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, prmtLifDrpDwnXPath),
					prmtlifeCycleValuesList);
			System.out.println("promote option are " + prmtlifeCycleValuesList);
			if (UIHelper.compareTwoSimilarLists(prmtlifeCycleDisplayList, prmtlifeCycleValuesList)) {
				report.updateTestLog("Verify PromoteLifeCycle dropdown is Listed in " + lang + "",
						"PromoteLifeCycle dropdown is successfully listed in " + lang + "", Status.PASS);
			} else {
				report.updateTestLog("Verify PromoteLifeCycle dropdown is Listed in " + lang + "",
						"PromoteLifeCycle dropdown is successfully listed in " + lang + "", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify PromoteLifeCycle dropdown is Listed in " + lang + "",
					"PromoteLifeCycle dropdown is successfully listed in " + lang + "", Status.FAIL);
		}
	}

	// verify Lifecycle Dropdown
	public void getDemoteLifeCycleDropDown(String dropDown, String lang) {
		try {
			ArrayList<String> demotelifeCycleDisplayList = new ArrayList<String>();
			demotelifeCycleDisplayList = myFilePageObj.getFileNames(dropDown);

			ArrayList<String> demotelifeCycleValuesList = new ArrayList<String>();
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, demtLifDrpDwnXPath),
					demotelifeCycleValuesList);
			System.out.println("demote option are " + demotelifeCycleValuesList);
			if (UIHelper.compareTwoSimilarLists(demotelifeCycleDisplayList, demotelifeCycleValuesList)) {
				report.updateTestLog("Verify DemoteLifeCycle dropdown is Listed in " + lang + "",
						"DemoteLifeCycle dropdown is successfully listed in " + lang + "", Status.PASS);
			} else {
				report.updateTestLog("Verify DemoteLifeCycle dropdown is Listed in " + lang + "",
						"DemoteLifeCycle dropdown is successfully listed in " + lang + "", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify DemoteLifeCycle dropdown is Listed in " + lang + "",
					"DemoteLifeCycle dropdown is successfully listed in " + lang + "", Status.FAIL);
		}
	}

	public void verifyDialogConfirmationMessage(String expectedMessageVal, String actualMessageVal) {
		try {

			if (actualMessageVal != null && actualMessageVal.equalsIgnoreCase(expectedMessageVal)) {
				report.updateTestLog("Verify Confirmation Message Dialog Text",
						"User able to see the confirmation mesaage as: " + expectedMessageVal, Status.PASS);
			} else {
				report.updateTestLog("Verify Confirmation Message Dialog Text",
						"User not able to see the confirmation mesaage as: " + expectedMessageVal, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Confirmation Message Dialog Text",
					"User not able to see the confirmation message as: ", Status.FAIL);
		}
	}

	public void verifyBannerMessageInDocLib(String folderOrFileName, String expectedMsg) {

		String finalBannerXpath = tempDocumentLibBannerInfoXpath.replace("CRAFT", folderOrFileName).replace("MSGNAME",
				expectedMsg);
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, finalBannerXpath)) {
				UIHelper.highlightElement(driver, finalBannerXpath);
				String name = UIHelper.getTextFromWebElement(driver, finalBannerXpath);
				report.updateTestLog("Verify Banner message displayed",
						"Banner Message displayed." + "<br /><b> Banner Name : </b>" + name, Status.PASS);
			} else {
				report.updateTestLog("Verify Banner message displayed", "Banner Message not displayed.", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Banner message displayed", "Banner Message not present.", Status.FAIL);
		}

	}

	// Verify the calculated size in document library
	public void verifyCalculationSizeInDocLib(String folderName, String expectedValue) {
		try {
			String actualValue = docLibPage.getCalculatedSizeFromDocLib(folderName);
			if (!actualValue.isEmpty() && actualValue.contains(expectedValue)) {
				report.updateTestLog("Verify Calculation Size in document Library for folder",
						"Calculation Size for folder same as expected  <br><b>Expected:</b>" + expectedValue
								+ "<br><b>Actual:</b>" + actualValue,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Calculation Size in document Library for folder",
						"Calculation Size for folder not same as expected  <br><b>Expected:</b>" + expectedValue
								+ "<br><b>Actual:</b>" + actualValue,
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Calculation Size in document Library for folder",
					"Calculation Size for folder verification failed", Status.FAIL);
		}
	}
	
	// Check File is Published with green tick -Added for NALS project
	public void verifyFilePublishedGreenTick(String fileName) {
	try {
	if (docLibPage.isDisplayedPublishedGreenTick(fileName)) {
	report.updateTestLog("Verify green coloured 'tick' sign after publishing files in document library",
	"User able to view the green coloured <b>'tick'</b> sign after publishing file:<b>" + fileName
	+ "</b>",
	Status.PASS);
	} else {
	report.updateTestLog("Verify green coloured 'tick' sign after publishing files in document library",
	"User NOT able to view the green coloured <b>'tick'</b> sign after publishing file:<b>"
	+ fileName + "</b>",
	Status.FAIL);
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
}