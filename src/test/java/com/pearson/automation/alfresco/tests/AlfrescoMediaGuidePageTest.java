package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;

import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaGuidePage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoMediaGuidePageTest extends ReusableLibrary {

	AlfrescoMediaGuidePage mediaTransPageObj = new AlfrescoMediaGuidePage(scriptHelper);
	AlfrescoMyFilesPageTest myFilePageTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
	AlfrescoMyFilesPage myFilePageObj = new AlfrescoMyFilesPage(scriptHelper);
	AlfrescoDocumentLibPage docLibPageObj = new AlfrescoDocumentLibPage(scriptHelper);
	AlfrescoDocumentDetailsPage docDetailsObj = new AlfrescoDocumentDetailsPage(scriptHelper);

	ArrayList<String> createdMenuItems = new ArrayList<String>();

	private String TitleColumnName[];
	private ArrayList<String> colItems = new ArrayList<String>();
	private ArrayList<String> actColItems = new ArrayList<String>();

	private ArrayList<String> unTrancodedItems = new ArrayList<String>();
	private ArrayList<String> uploadedItems = new ArrayList<String>();
	private ArrayList<String> uploadedMediaItems = new ArrayList<String>();
	private ArrayList<String> jsonItems = new ArrayList<String>();
	private ArrayList<String> notJsonItems = new ArrayList<String>();
	private String trancodedItem;

	private String toggleDropdown = "//*[contains(@class,'dropdown-menu')]/li/a";
	private ArrayList<String> toggleItems = new ArrayList<String>();
	private String fileDisplayDropdownXpath = ".//select[@class='displayddn']";
	private String sortByDropDownXpath = ".//select[2]";
	private String sortButtonXpath = "//*[@id='av-media-guide']/descendant::div[@class='row'][last()]//div[@class='form-inline']//button[contains(@class,'btn btn-xs')]";
	private String successImgIcons = "//table[@class='table table-bordered']/tbody/tr/td[3]/span[@title='Success']";
	private String inProgressImgIcons = "//table[@class='table table-bordered']/tbody/tr/td[3]/span[@title='In Progress']";

	public AlfrescoMediaGuidePageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Check uploaded folder
	public boolean verifyUploadedFolder(String folderName) {
		try {
			myFilePageTestObj.getUploadedFileDetails(folderName);
			report.updateTestLog("Verify Uploaded Folder ", " " + folderName + " is available", Status.PASS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Uploaded Folder ", " " + folderName + " is not available", Status.FAIL);
		}
		return false;
	}

	// Check MediaGuide Options Not displayed in "Create" Options,On Click
	// Create "Button"
	public boolean verifyMediaGuideOnCreateDropDown(String searchText) {
		try {
			myFilePageObj.clickOnCreateButton();
			createdMenuItems = myFilePageObj.getCreateMenuItems();
			for (String menuItem : createdMenuItems) {
				if (!menuItem.equals(searchText)) {
					report.updateTestLog("Verify MediaGuideOption in Create DropdownOptions",
							" " + searchText + " is not displayed in Create Option", Status.PASS);
					return true;
				} else {
					report.updateTestLog("Verify MediaGuideOption in Create DropdownOptions",
							" " + searchText + " is displayed in Create Option", Status.FAIL);

				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify MediaGuideOption in Create DropdownOptions",
					" " + searchText + " is displayed in Create Option", Status.FAIL);
			return false;
		}
		return false;

	}

	// verifyToggleDropdown is displayed
	public void verifyToggleDropdown(String listItem) {
		try {
			UIHelper.findAndAddElementsToaList(driver, toggleDropdown, toggleItems);
			if (toggleItems.contains(listItem)) {
				report.updateTestLog("Verify ToggleDropDown is displayed",
						" " + "ToggleButton with" + " " + listItem + " " + "view" + "should be displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify ToggleDropDown is displayed",
						" " + "ToggleButton with" + " " + listItem + " " + "view" + "should not be displayed",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify ToggleDropDown is displayed",
					" " + "ToggleButton with" + " " + listItem + " " + "view" + "should not be displayed", Status.FAIL);
		}
	}

	// verifyColumnName are displayed in ListView
	public void verifyColumnNameListView(String colName, String convertAction) {
		try {
			actColItems = mediaTransPageObj.verifyListViewColumnsInUntrancodedMedia();

			if (colName.contains(",")) {
				TitleColumnName = colName.split(",");
			}
			if (TitleColumnName != null & TitleColumnName.length > 0) {
				for (String expValue : TitleColumnName) {
					colItems.add(expValue);
				}

			}
			if (UIHelper.compareTwoSimilarLists(colItems, actColItems)) {
				report.updateTestLog("Verify ColumnName in 'List View' for " + convertAction + " Tab",
						"ColumnName:" + colName + " are successfully displayed in " + convertAction + " Tab" + colItems,
						Status.PASS);
			} else {
				report.updateTestLog("Verify ColumnName in 'List View' for " + convertAction + "Tab",
						"ColumnName:" + colName + " are failed to display in " + convertAction + "Tab" + colItems,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify ColumnName in 'List View' for " + convertAction + "Tab",
					"ColumnName:" + colName + " are failed to display in " + convertAction + "Tab" + colItems,
					Status.FAIL);
		}

	}

	// method to verifyUntrancodedFilesInFolder
	public void verifyUntrancodedFilesInFolder(String folderName, String convertAction) {
		try {
			unTrancodedItems = mediaTransPageObj.getListOfUnTrancodedFiles();
			UIHelper.switchtab(0, driver);
			myFilePageObj.openFolder(folderName);
			uploadedItems = myFilePageObj.getUploadedFileOrFolderTitle();
			if (uploadedItems.containsAll(unTrancodedItems)) {
				report.updateTestLog(
						"Verify only untranscoded audio files within the particular folder are displayed in "
								+ convertAction + " Tab",
						"FileName:" + unTrancodedItems + " are successfully displayed in " + convertAction + " Tab"
								+ uploadedItems,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify only untranscoded audio files within the particular folder are displayed in  "
								+ convertAction + "Tab",
						"FileName:" + unTrancodedItems + " are failed to display in " + convertAction + "Tab"
								+ uploadedItems,
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify only untranscoded audio files within the particular folder are displayed in  "
							+ convertAction + "Tab",
					"FileName:" + unTrancodedItems + " are failed to display in " + convertAction + "Tab"
							+ uploadedItems,
					Status.FAIL);
		}

	}

	public ArrayList<String> getTrancodedJsonFilesInFolder(String folderName) {
		try {

			myFilePageObj.openFolder(folderName);
			uploadedItems = myFilePageObj.getUploadedFileOrFolderTitle();
			for (String item : uploadedItems) {
				if (item.endsWith(".json")) {

					jsonItems.add(item);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonItems;
	}

	// method to verify TrancodedJsonFiles with in particular Folder
	public void verifyTrancodedJsonFilesInFolder(ArrayList<String> actual, String folderName) {
		try {
			ArrayList<String> expectJsonItems = getTrancodedJsonFilesInFolder(folderName);
			if (UIHelper.compareTwoSimilarLists(actual, expectJsonItems)) {
				report.updateTestLog(
						"Verify JSON files are created at the same location as the original audio & Video files and named with the same filename but with the .json extension after transcoding the audio & Video file.",
						"<b> TrancodedItem is: </b><br/>" + expectJsonItems + " successfully displayed in "
								+ "<b> FolderName : " + folderName + " ",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify JSON files are created at the same location as the original audio & Video files and named with the same filename but with the .json extension after transcoding the audio & Video file.",
						"<b> TrancodedItem is: </b><br/>" + expectJsonItems + " are failed to  displayed in "
								+ "<b> FolderName : " + folderName + " ",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify JSON files are created at the same location as the original audio & Video files and named with the same filename but with the .json extension after transcoding the audio & Video file.",
					"<b> TrancodedItem  are failed to  displayed in " + "<b> FolderName : " + folderName + " ",
					Status.FAIL);
		}

	}

	public String verifyTrancodedFilesDisplayedInStreamingMedia(String convertAction) {
		trancodedItem = mediaTransPageObj.getTranscodedFiles();
		return trancodedItem;
	}

	// method to add related files in streaming video

	public void verifyRelatedFilesAddedInStreamingVideo(String tobeupload[], String filePath, String fileName[],
			String folderName) {
		try {
			mediaTransPageObj.uploadRelatedFilesInStreamVideo(tobeupload, filePath, fileName);
			if (docLibPageObj.isNavigateFolderInDocLibrary(folderName)) {
				report.updateTestLog(
						"Verify a user can able to add related Files in Edit Streaming media Page of Video File",
						" " + "User can able to add related Files in Edit Streaming media Page of Video File",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify a user can able to add related Files in Edit Streaming media Page of Video File",
						" " + "User can't able to add related Files in Edit Streaming media Page of Video File",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify a user can able to add related Files in Edit Streaming media Page of Video File",
					" " + "User can't able to add related Files in Edit Streaming media Page of Video File",
					Status.FAIL);
		}
	}
	// Verify Files found Count matches with Number of files available in
	// Selected Tab

	public void verifyFilesCountMatchesInMediaGuideInterface() {
		try {
			if (mediaTransPageObj.getListOfUnTrancodedFiles().size() == mediaTransPageObj.getNumberOfFilesDisplayed()) {
				report.updateTestLog("Verify Files found Count matches with Number of files available in Selected Tab",
						" " + "Files found Count  is matched with Number of files available in Selected Tab",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Files found Count matches with Number of files available in Selected Tab",
						" " + "Files found Count  is not matched with Number of files available in Selected Tab",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Files found Count matches with Number of files available in Selected Tab",
					" " + "Files found Count  is not matched with Number of files available in Selected Tab",
					Status.FAIL);

		}
	}

	public void getFileDisplayDropDownInMedia(String fileDisplay) {
		try {
			ArrayList<String> fileDisplayList = new ArrayList<String>();
			fileDisplayList = myFilePageObj.getFileNames(fileDisplay);

			ArrayList<String> Values = new ArrayList<String>();
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, fileDisplayDropdownXpath), Values);
			System.out.println("dropdown list are: " + Values);
			if (UIHelper.compareTwoSimilarLists(fileDisplayList, Values)) {
				report.updateTestLog(
						"Verify Display dropdown is listed with 4,8,12,16 Results per page in the bottom of Media Guide Interface",
						" " + "Display dropdown is successfully listed with 4,8,12,16 Results per page in the bottom of Media Guide Interface",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Display dropdown is listed with 4,8,12,16 Results per page in the bottom of Media Guide Interface",
						" " + "Display dropdown failed to listed with 4,8,12,16 Results per page in the bottom of Media Guide Interface",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify Display dropdown is listed with 4,8,12,16 Results per page in the bottom of Media Guide Interface",
					" " + "Display dropdown failed to listed with 4,8,12,16 Results per page in the bottom of Media Guide Interface",
					Status.FAIL);
		}
	}

	public void selectSecondIndexOfFileDisplay(int index) {
		try {
			UIHelper.selectIntergerValue(driver, fileDisplayDropdownXpath, index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Sort Display dropdown is listed with
	// FileName,Title,CreationDate,ModificationDate
	public void getSortByDisplayDropDownInMedia(String fileDisplay) {
		try {
			ArrayList<String> sortByDisplayList = new ArrayList<String>();
			sortByDisplayList = myFilePageObj.getFileNames(fileDisplay);

			ArrayList<String> Values1 = new ArrayList<String>();
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, sortByDropDownXpath), Values1);
			System.out.println("Sort Dropdown list are: " + Values1);
			if (UIHelper.compareTwoSimilarLists(sortByDisplayList, Values1)) {
				report.updateTestLog(
						"Verify Sort Display dropdown is listed with FileName,Title,CreationDate,ModificationDate",
						" " + "Sort Display dropdown is successfully listed with " + Values1
								+ " options in the bottom of Media Guide Interface",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Sort Display dropdown is listed with FileName,Title,CreationDate,ModificationDate",
						" " + "Sort Display dropdown failed to display dropdown with " + Values1
								+ " options in the bottom of Media Guide Interface",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify Sort Display dropdown is listed with FileName,Title,CreationDate,ModificationDate",
					" " + "Sort Display dropdown failed to display dropdown with "
							+ " options in the bottom of Media Guide Interface",
					Status.FAIL);
		}
	}

	public void selectMoreDropdown() {
		try {
			if (mediaTransPageObj.getNumberOfFilesDisplayed() <= 4) {
				selectSecondIndexOfFileDisplay(0);
			} else if ((mediaTransPageObj.getNumberOfFilesDisplayed() > 4)
					&& (mediaTransPageObj.getNumberOfFilesDisplayed() <= 8)) {
				selectSecondIndexOfFileDisplay(1);
			} else if ((mediaTransPageObj.getNumberOfFilesDisplayed() > 8)
					&& (mediaTransPageObj.getNumberOfFilesDisplayed() <= 12)) {
				selectSecondIndexOfFileDisplay(2);
			} else if ((mediaTransPageObj.getNumberOfFilesDisplayed() > 12)
					&& (mediaTransPageObj.getNumberOfFilesDisplayed() <= 16)) {
				selectSecondIndexOfFileDisplay(3);
			} else if ((mediaTransPageObj.getNumberOfFilesDisplayed() > 16)
					&& (mediaTransPageObj.getNumberOfFilesDisplayed() <= 20)) {
				selectSecondIndexOfFileDisplay(4);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify FileName is Sorted
	public void verifyFileNameSorted(String sortByValue, String fileType) {
		try {
			UIHelper.clearList(mediaTransPageObj.getListOfUnTrancodedFiles());
			UIHelper.selectbyVisibleText(driver, sortByDropDownXpath, sortByValue);
			if (UIHelper.isSortedList(mediaTransPageObj.getListOfUnTrancodedFiles())) {
				report.updateTestLog(
						"Verify FileName is Sorted in" + " " + fileType + " Tab after selecting," + sortByValue
								+ "from dropdown options",
						"FileName is Sorted Successfully in: " + fileType + " Tab", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify FileName is Sorted in" + " " + fileType + " Tab after selecting," + sortByValue
								+ "from dropdown options",
						"FileName is failed to Sort in: " + fileType + " Tab", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify FileName is Sorted in" + " " + fileType + " Tab after selecting," + sortByValue
							+ "from dropdown options",
					"FileName is failed to Sort in: " + fileType + " Tab", Status.FAIL);
		}
	}

	// verifyTitleName is Sorted
	public void verifyTitleNameSorted(String sortByValue, String fileType) {

		try {
			UIHelper.selectbyVisibleText(driver, sortByDropDownXpath, sortByValue);
			if (UIHelper.isSortedList(mediaTransPageObj.getTitleNameForSort())) {
				report.updateTestLog(
						"Verify TitleName is Sorted in" + " " + fileType + " Tab after selecting," + sortByValue
								+ "from dropdown options",
						"TitleName is Sorted Successfully in: " + fileType + " Tab", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify TitleName is Sorted in" + " " + fileType + " Tab after selecting," + sortByValue
								+ "from dropdown options",
						"TitleName is failed to Sort in: " + fileType + " Tab", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify TitleName is Sorted in" + " " + fileType + " Tab after selecting," + sortByValue
							+ "from dropdown options",
					"TitleName is failed to Sort in: " + fileType + " Tab", Status.FAIL);
		}
	}

	// verifyStatusFilterInLeftPanel
	public void verifyStatusFilterInLeftPanel(String filterBy, String fileType) {
		try {
			ArrayList<String> statusFilterList = new ArrayList<String>();
			statusFilterList = myFilePageObj.getFileNames(filterBy);
			ArrayList<String> Values1 = new ArrayList<String>();
			Values1 = mediaTransPageObj.getStatusFilterInLeftPanel();

			if (UIHelper.compareTwoSimilarLists(statusFilterList, Values1)) {
				report.updateTestLog(
						"Verify user is able to filter the " + fileType
								+ " in Listview using Status Filter available in the left panel" + "<b>:List Items are,"
								+ Values1 + "",
						"User should be able to filter " + fileType + " in Listview using Status Filter", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify user is able to filter the " + fileType
								+ " in Listview using Status Filter available in the left panel" + "<b>:List Items are,"
								+ Values1 + "",
						"User not able to filter " + fileType + " in Listview using Status Filter", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify user is able to filter the " + fileType
							+ " in Listview using Status Filter available in the left panel",
					"User not able to filter " + fileType + " in Listview using Status Filter", Status.FAIL);
		}
	}

	// verifyInprogressIcon is displayed
	public void verifyInprogressIcon(String convertAction) {
		try {
			if (UIHelper.chkForThisElementList(UIHelper.findListOfElementsbyXpath(inProgressImgIcons, driver))) {
				report.updateTestLog(
						"Verify user is able to filter the " + convertAction + " in Listview using Status Filter"
								+ "<b>:In Progress</b>",
						" " + convertAction + " with" + "<b>:In Progress</b>"
								+ " status should be displayed after applying status filter ",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify user is able to filter the " + convertAction + " in Listview using Status Filter"
								+ "<b>:In Progress</b>",
						" " + convertAction + " with" + "<b>:In Progress</b>"
								+ " status failed to displayed after applying status filter ",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify user is able to filter the " + convertAction + " in Listview using Status Filter"
							+ "<b>:In Progress</b>",
					" " + convertAction + " with" + "<b>:In Progress</b>"
							+ " status failed to displayed after applying status filter ",
					Status.FAIL);
		}
	}

	// verifySuccessIcon is displayed
	public void verifySuccessIcon(String convertAction) {
		try {
			if (UIHelper.chkForThisElementList(UIHelper.findListOfElementsbyXpath(successImgIcons, driver))) {
				report.updateTestLog(
						"Verify user is able to filter the " + convertAction + " in Listview using Status Filter"
								+ "<b>:Success Icon</b>",
						" " + convertAction + " with" + "<b>:Success Icon</b>"
								+ " status should be displayed after applying status filter ",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify user is able to filter the " + convertAction + " in Listview using Status Filter"
								+ "<b>:Success Icon</b>",
						" " + convertAction + " with" + "<b>:Success Icon</b>"
								+ " status failed to displayed after applying status filter ",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify user is able to filter the " + convertAction + " in Listview using Status Filter"
							+ "<b>:Success Icon</b>",
					" " + convertAction + " with" + "<b>:Success Icon</b>"
							+ " status failed to displayed after applying status filter ",
					Status.FAIL);
		}
	}

	// verifyContentAssetApplied is applied for media files
	public void verifyContentAssetApplied(String aspectName, String convertAction) {
		try {
			docDetailsObj.performManageAspectsDocAction();
			if (docDetailsObj.isAspectsAvailable(aspectName)) {
				report.updateTestLog("Verify Content Asset Aspects Applied in " + convertAction + " Tab",
						"Content Asset Aspects Applied Successfully in " + convertAction + " Tab" + "<b>:" + aspectName
								+ "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Content Asset Aspects Applied in " + convertAction + " Tab",
						"Content Asset Aspects failed to display in " + convertAction + " Tab" + "<b>:" + aspectName
								+ "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Content Asset Aspects Applied in " + convertAction + " Tab",
					"Content Asset Aspects failed to display in " + convertAction + " Tab" + "<b>:" + aspectName
							+ "</b>",
					Status.FAIL);
		}

	}

	// verifyAssetGUID is present in doc preview
	public void verifyAssetGUID(String refIDAttribute, String convertAction) {
		try {

			String assetID = docDetailsObj.getAssetGUIDPropertyValue();
			if (refIDAttribute.equals(assetID)) {
				report.updateTestLog(
						"Verify Reference ID value updated in " + convertAction
								+ " Tab is reflected in Asset GUID label of Document Preview Screen",
						"Reference ID Value " + "<b> :" + refIDAttribute + "</b>" + " updated in " + convertAction
								+ " Tab is successfully reflected in Asset GUID label of Document Preview Screen"
								+ "<b>:" + assetID + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Reference ID Value updated in " + convertAction
								+ " Tab is reflected in Asset GUID label of Document Preview Screen",
						"Reference ID Value " + "<b> :" + refIDAttribute + "</b>" + " updated in " + convertAction
								+ " Tab is failed to reflected in Asset GUID label of Document Preview Screen" + "<b>:"
								+ assetID + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify Reference ID Value updated in " + convertAction
							+ " Tab is reflected in Asset GUID label of Document Preview Screen",
					"Reference ID Value  updated in " + convertAction
							+ " Tab is failed to reflected in Asset GUID label of Document Preview Screen",
					Status.FAIL);
		}
	}

	// verifySourceID is present in doc preview
	public void verifySourceID(String titleAttribute, String convertAction) {
		try {

			String srcID = docDetailsObj.getSourceIDPropertyValue();
			if (titleAttribute.equals(srcID)) {
				report.updateTestLog(
						"Verify Title Value updated in " + convertAction
								+ " Tab is reflected in SourceID label of Document Preview Screen",
						"Title Value " + "<b> :" + titleAttribute + "</b>" + " updated in " + convertAction
								+ " Tab is successfully reflected in SourceID label of Document Preview Screen" + "<b>:"
								+ srcID + "</b>",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify Title Value updated in " + convertAction
								+ " Tab is reflected in SourceID label of Document Preview Screen",
						"Title Value " + "<b> :" + titleAttribute + "</b>" + " updated in " + convertAction
								+ " Tab is failed to reflected in SourceID label of Document Preview Screen" + "<b>:"
								+ srcID + "</b>",
						Status.FAIL);

			}
		} catch (Exception e) {

			report.updateTestLog(
					"Verify Title Value updated in " + convertAction
							+ " Tab is reflected in SourceID label of Document Preview Screen",
					"Title Value  updated in " + convertAction
							+ " Tab is failed to reflected in SourceID label of Document Preview Screen",
					Status.FAIL);
			e.printStackTrace();

		}

	}

	public ArrayList<String> getAllFilesInMediaFolder(String folderName) {
		try {

			myFilePageObj.openFolder(folderName);
			uploadedMediaItems = myFilePageObj.getUploadedFileOrFolderTitle();
			for (String item : uploadedMediaItems) {
				if ((item.endsWith(".mp3")) || (item.endsWith(".mp4"))) {
					notJsonItems.add(item.trim());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notJsonItems;
	}

	// verifySuccessStatusIcons after success trancoding
	public void verifySuccessStatusIcons(String convertAction) {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, successImgIcons)) {
				report.updateTestLog(
						"Verify user can transcode " + convertAction + " files by clicking Media Guide option",
						"User should able to transcode " + convertAction + " files Successfully", Status.PASS);
			} else

				report.updateTestLog(
						"Verify user can transcode " + convertAction + " files by clicking Media Guide option",
						"User failed to transcode " + convertAction + " files", Status.FAIL);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify user can transcode " + convertAction + " files by clicking Media Guide option",
					"User failed to transcode " + convertAction + " files", Status.FAIL);
		}

	}

}
