package com.pearson.automation.alfresco.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
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
public class AlfrescoAVTPage extends ReusableLibrary {

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoAVTPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	private String createButtomXpath = ".//button[contains(text(),'Create..')]";
	public String relatedFilesTabXpath = ".//a[text()='Related File']";
	public String disabledrelatedXpath = ".//a[text()='Related File' and @disabled='']";
	private String priveTabXpath = ".//a[text()='Preview']";
	private String embeddingURL = ".//*[@id='react-app']/div/div[1]/div/div[1]/div[2]/div/div[2]//*[contains(text(),'Embedding URL')]";
	private String standAloneURL = ".//*[@id='react-app']/div/div[1]/div/div[1]/div[2]/div/div[2]//*[contains(text(),'Stand-Alone URL')]";
	private String citeURL = ".//*[@id='react-app']/div/div[1]/div/div[1]/div[2]/div/div[2]//*[contains(text(),'CITE URL')]";
	private String Link = "/following-sibling::a";
	private String deleteVideoInUploadFilesTabXpath = ".//*[@id='SingleFileUploadForm']/div[2]/div[2]/div/span";
	private String deleteVideoInUploadFilesPromptBoxXpath = ".//*[contains(text(),'Are you sure you want to delete the media file?')]";
	private String yesButtonXpathForDeleteVideo = ".//*[contains(text(),'Are you sure you want to delete the media file?')]/following-sibling::div//button[text()='Yes']";
	private String PopuptitleXpath = ".//*[@id='popup_title']";
	private String deleteSuccessMessage = ".//*[contains(text(),'Successfully deleted the Source file')]";
	private String deleteSuccessMessageOkButton = ".//*[contains(text(),'Successfully deleted the Source file')]/following-sibling::div//button[text()='OK']";
	private String urllink = ".//div[@class='url_info']//p[contains(text(),'CRAFT')]/following-sibling::a";
	private String RelationshipTableXpath = ".//*[@id='relationship-function-table']";
	private String relatedFilesLinkXpath = ".//*[@id='relationship-function-table']/tbody/tr/td[3]/a";
	private String deleteCaptionsXpath = ".//div[@class='form-field fleft ']/label[contains(text(),'Caption')]/following-sibling::div/div/div/span";
	private String deleteCaptionPromptBoxXpath = ".//*[contains(text(),'Are you sure you want to delete the caption?')]";
	private String yesButtonXpathForCaption = ".//*[contains(text(),'Are you sure you want to delete the caption?')]/following-sibling::div//button[text()='Yes']";
	private String tempSelectFolderChkboxXpath = "//*[contains(@class,'filename')]//*[text()='CRAFT']//ancestor::tr//td//*[@type='checkbox']";
	private String selectedFolderORFileItemXpath = "//*[@class='filename']//*[text()='CRAFT']//ancestor::tr//td[contains(@class,'yui-dt-col-fileName')]";
	private String tempXpathForMoreOptionLink = ".//*[contains(@id,'yuievtautoid')]//*[text()='CRAFT']//ancestor::tr//a/span[contains(.,'More')]";
	private String uploadedFilesTitlesXpath = "//*[@class='yui-dt-data']//h3/*[contains(@id,'alf-id')]";
	private String streamingMediaHeaderXpath = ".//span[text()='Create Streaming Media']";
	public String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/FolderNames.txt";
	private String mediaXpath = "//*[@name='media']";
	private String replaceButtonXpath = "//*[contains(@class,'uploadSingleFile')]//div[contains(@class,'right-action-container')]//span[@class='icon-upload']";
	private String editStreamingMediaXpath = ".//span[contains(text(),'Edit Streaming Media')]";
	private String replaceFileUploadInputFieldXpath = ".//input[contains(@name,'source-icon-upload')]";
	private String replaceSelectedFileXpath = "//div[@class='right-action-container form-field']/input[@type='text' and @class='small-textbox1 fleft']";
	private String fileUploadTypesXpath = ".//div[contains(@class,'accordion-section')]//span";
	private String uploadTabButtonsXpath = ".//*[contains(@class,'form-container')]//div[@class='fright']//input[@type='submit' or @type='button']";
	private String singleFileTabFieldsXpath = ".//*[contains(@class,'clear-space')]//a[contains(@name,'upload')]";
	private String batchFileXpath = ".//div[contains(@class,'accordion-section')]//span[contains(text(),'Batch File Upload')]";
	private String nolabelBatchFileXpath = "//div[@class='accordion-content clear-space open']//h3[text()='Batch File Upload']";
	private String errorTranscodeXpath = ".//span[contains(text(),'Error in Source file Transcoding')] ";
	private String sourceTrancodeFailXpath = ".//p[contains(text(),'Import of Source file is in progress')]";

	public ArrayList<String> filesTypes = new ArrayList<String>();
	public ArrayList<String> availableFieldsList1 = new ArrayList<String>();

	// Click on Create button on document library
	public void clickOnCreate() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, createButtomXpath);
			UIHelper.highlightElement(driver, createButtomXpath);
			UIHelper.click(driver, createButtomXpath);
			report.updateTestLog("clicked on", UIHelper.getTextFromWebElement(driver, createButtomXpath).trim(),
					Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("unable to click on", UIHelper.getTextFromWebElement(driver, createButtomXpath).trim(),
					Status.FAIL);
		}
	}

	private String createMenuItemsXpath = ".//div[contains(@class,'yuimenu yui-module yui-overlay yui-button-menu yui-menu-button-menu visible')]//div[contains(@class,'bd')]//ul[1]//a[contains(@class,'yuimenuitemlabel')]";

	public void verifyStreamingMediaInCreate() {
		clickOnCreate();
		UIHelper.waitFor(driver);
		try {
			List<WebElement> createMenuItemsElementList = UIHelper.findListOfElementsbyXpath(createMenuItemsXpath,
					driver);
			ArrayList<String> createMenuItemsArrayList = new ArrayList<String>();
			for (WebElement webElement : createMenuItemsElementList) {
				createMenuItemsArrayList.add(webElement.getText().trim());
			}
			if (createMenuItemsArrayList.contains("Streaming Media")) {
				report.updateTestLog("Expected: Streaming Media",
						"Existing Menu " + createMenuItemsArrayList + "in which Streaming Media is Exist ",
						Status.PASS);

			} else {
				report.updateTestLog("Expected: Streaming Media",
						"Existing Menu " + createMenuItemsArrayList + "in which Streaming Media is not  Exist ",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyStreamingMediaInCreateNegative() {
		clickOnCreate();
		UIHelper.waitFor(driver);
		try {
			List<WebElement> createMenuItemsElementList = UIHelper.findListOfElementsbyXpath(createMenuItemsXpath,
					driver);
			ArrayList<String> createMenuItemsArrayList = new ArrayList<String>();
			for (WebElement webElement : createMenuItemsElementList) {
				createMenuItemsArrayList.add(webElement.getText().trim());
			}
			if (createMenuItemsArrayList.contains("Streaming Media")) {
				report.updateTestLog("Expected: Streaming Media",
						"Existing Menu " + createMenuItemsArrayList + "in which Streaming Media is Exist ",
						Status.PASS);

			} else {
				report.updateTestLog("Expected: Streaming Media",
						"Existing Menu " + createMenuItemsArrayList + "in which Streaming Media is not  Exist ",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOnStreamingMedia() {
		WebElement temp = null;
		driver.navigate().refresh();
		clickOnCreate();
		UIHelper.waitFor(driver);
		try {
			List<WebElement> createMenuItemsElementList = UIHelper.findListOfElementsbyXpath(createMenuItemsXpath,
					driver);
			ArrayList<String> createMenuItemsArrayList = new ArrayList<String>();
			for (WebElement webElement : createMenuItemsElementList) {
				createMenuItemsArrayList.add(webElement.getText().trim());

				if (webElement.getText().trim().contains("Streaming Media")) {
					temp = webElement;
				}
			}
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, temp);
			/*
			 * if (createMenuItemsArrayList.contains("Streaming Media")) {
			 * report.updateTestLog("Expected: Streaming Media",
			 * "Existing Menu " + createMenuItemsArrayList +
			 * "in which Streaming Media is Exist ", Status.PASS); } else {
			 * report.updateTestLog("Expected: Streaming Media",
			 * "Existing Menu " + createMenuItemsArrayList +
			 * "in which Streaming Media is not  Exist ", Status.FAIL); }
			 */
			UIHelper.clickanElement(temp);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, streamingMediaHeaderXpath);
			UIHelper.highlightElement(driver, streamingMediaHeaderXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, streamingMediaHeaderXpath))) {
				report.updateTestLog("Expected: Streaming Media Page header ",
						"loaded Header is " + UIHelper.getTextFromWebElement(driver, streamingMediaHeaderXpath),
						Status.PASS);

			} else {
				report.updateTestLog("Expected: Streaming Media Page header ",
						" Header is not expected " + UIHelper.getTextFromWebElement(driver, streamingMediaHeaderXpath),
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(" EXCEPTION", " while trying to create Streaming Media ", Status.FAIL);
		}
	}

	// click calcle on streaming media page
	private String cancleStreamingMediaXpath = ".//input[@value='Cancel']";
	private String docLibPageXpath = "//*[contains(@class,'documents ')]//*";

	public void clickOnCancle() {

		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, cancleStreamingMediaXpath);
			UIHelper.highlightElement(driver, cancleStreamingMediaXpath);
			UIHelper.click(driver, cancleStreamingMediaXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Clicked  ", " Cancel on Streaming media Page", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("EXCEPTION  ", " unable to click on Cancel  Streaming media Page", Status.FAIL);
		}

	}

	private String availableFieldsXpath = ".//*[contains(@class,'form-container')]//label";
	private String uploadFilesTabXpath = ".//a[text()='Upload File']";

	public void verifyUploadFilesTabFields() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFilesTabXpath);
		UIHelper.highlightElement(driver, uploadFilesTabXpath);
		UIHelper.click(driver, uploadFilesTabXpath);

		List<WebElement> availableFieldsElement = UIHelper.findListOfElementsbyXpath(availableFieldsXpath, driver);
		ArrayList<String> availableFieldsList = new ArrayList<String>();
		ArrayList<String> availableFieldsListtemp = new ArrayList<String>();
		String[] expectedList = { "Choose File", "*Title", "Description", "Keywords", "Playlist ID", "Reference ID",
				"Profile" };
		for (String string : expectedList) {
			availableFieldsListtemp.add(string);
		}

		UIHelper.waitFor(driver);

		for (WebElement webElement : availableFieldsElement) {
			UIHelper.highlightElement(driver, webElement);

			availableFieldsList.add(webElement.getText().trim());

		}

		if (availableFieldsList.containsAll(availableFieldsListtemp)) {
			report.updateTestLog("Expected:  " + availableFieldsListtemp, "Actual: " + availableFieldsList,
					Status.PASS);
		} else {
			report.updateTestLog("Expected:  " + availableFieldsListtemp, "Actual: " + availableFieldsList,
					Status.FAIL);
		}

	}

	public void verifyRelatedFilesTabFields() {

		UIHelper.waitForVisibilityOfEleByXpath(driver, relatedFilesTabXpath);
		UIHelper.highlightElement(driver, relatedFilesTabXpath);
		UIHelper.click(driver, relatedFilesTabXpath);

		List<WebElement> availableFieldsElement = UIHelper.findListOfElementsbyXpath(availableFieldsXpath, driver);
		ArrayList<String> availableFieldsList = new ArrayList<String>();
		ArrayList<String> availableFieldsListtemp = new ArrayList<String>();
		String[] expectedList = { "Thumbnail Image", "Choose File", "Chaptering XML File", "Choose File", "Caption",
				"Choose File" };
		for (String string : expectedList) {
			availableFieldsListtemp.add(string);
		}

		UIHelper.waitFor(driver);

		for (WebElement webElement : availableFieldsElement) {
			UIHelper.highlightElement(driver, webElement);
			availableFieldsList.add(webElement.getText().trim());

		}
		if (availableFieldsList.containsAll(availableFieldsListtemp)) {
			report.updateTestLog("Expected:  " + availableFieldsListtemp, "Actual: " + availableFieldsList,
					Status.PASS);
		} else {
			report.updateTestLog("Expected:  " + availableFieldsListtemp, "Actual: " + availableFieldsList,
					Status.FAIL);
		}

	}

	private String videoFileUploadInputFieldXpath = ".//input[contains(@name,'uploadFile')]";

	private String selectedFileXpath = ".//p[contains(@class,'selectedFileName')]";
	private String unsupportWarningMessageXpath = ".//span[contains(text(),'Unsupported format')]";

	public void uploadFileInUploadFilesTab(String filePath, String fileName) {

		try {

			WebElement fileInput = driver.findElement(By.xpath(videoFileUploadInputFieldXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectedFileXpath);
			UIHelper.highlightElement(driver, selectedFileXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, selectedFileXpath))) {
				String selectedFileName = UIHelper.findAnElementbyXpath(driver, selectedFileXpath).getText().trim();
				if (selectedFileName.contains(fileName) || fileName.contains(".mp4") || fileName.contains(".m4v")
						|| fileName.contains(".mov")) {
					if (!UIHelper.checkForAnElementbyXpath(driver, unsupportWarningMessageXpath)) {
						report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
								"Actual upload file: " + selectedFileName, Status.PASS);
					} else {
						report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
								"Actual upload file: " + selectedFileName + " " + UIHelper
										.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
								Status.FAIL);
					}

				} else {
					report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
							"Actual upload file: " + selectedFileName + " " + UIHelper
									.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
							Status.FAIL);
				}

			} else {
				report.updateTestLog("Expected upload file: NOT FOUD ", "Actual upload file: ", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("upload action failed", "upload action failed ", Status.FAIL);
		}

	}

	public void uploadFileInUploadFilesTabNegative(String filePath, String fileName) {

		try {

			WebElement fileInput = driver.findElement(By.xpath(videoFileUploadInputFieldXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectedFileXpath);
			UIHelper.highlightElement(driver, selectedFileXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, selectedFileXpath))) {
				String selectedFileName = UIHelper.findAnElementbyXpath(driver, selectedFileXpath).getText().trim();
				if (selectedFileName.contains(fileName)) {
					if (UIHelper.checkForAnElementbyXpath(driver, unsupportWarningMessageXpath)) {
						UIHelper.highlightElement(driver, unsupportWarningMessageXpath);

						report.updateTestLog("Expected upload file with inappropriate format:  " + fileName,
								"Actual upload file: " + selectedFileName + " " + UIHelper
										.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
								Status.PASS);
					} else {
						report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
								"Actual upload file: " + selectedFileName + " " + UIHelper
										.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
								Status.FAIL);

					}

				} else {
					report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
							"Actual upload file: " + selectedFileName + " " + UIHelper
									.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
							Status.FAIL);
				}

			} else {
				report.updateTestLog("Expected upload file: NOT FOUD ", "Actual upload file: ", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("upload action failed", "upload action failed ", Status.FAIL);
		}

	}

	private String saveAndUploadXpath = ".//input[contains(@value,'Save and Upload')]";

	public void clickOnSaveAndUpload() {

		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, saveAndUploadXpath);
			UIHelper.highlightElement(driver, saveAndUploadXpath);
			UIHelper.click(driver, saveAndUploadXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Clicked  ", " saveAndUpload on Streaming media Page", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("EXCEPTION  ", " unable to click on Cancle  Streaming media Page", Status.FAIL);
		}

	}

	private String requiredFieldErrorMessageForTitleXpath = ".//span[contains(text(),'Title is a required')]";

	public void verifyErrorMessageForEmptyTitle() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, requiredFieldErrorMessageForTitleXpath)) {
				UIHelper.highlightElement(driver, requiredFieldErrorMessageForTitleXpath);
				report.updateTestLog(
						"Error Message displayed Successfully  " + UIHelper
								.findAnElementbyXpath(driver, requiredFieldErrorMessageForTitleXpath).getText().trim(),
						"while clicking on save and upload with out title ", Status.PASS);
			} else {
				report.updateTestLog(
						"Error Message not displayed   " + UIHelper
								.findAnElementbyXpath(driver, requiredFieldErrorMessageForTitleXpath).getText().trim(),
						"while clicking on save and upload with out title ", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String TitleFieldXpath = ".//*[@id='title']";

	public String inputTitle(String Title) {
		int random = (int) (Math.random() * 5000 + 1);
		Title += random;
		try {
			UIHelper.highlightElement(driver, TitleFieldXpath);
			UIHelper.click(driver, TitleFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, TitleFieldXpath, Title);
			report.updateTestLog("Input  ", "Title:" + Title, Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("unable to locate Input  ", "Title:" + Title, Status.FAIL);
		}
		new FileUtil().writeTextToFile(Title, testOutputFilePath);
		return Title;

	}

	private String referenceIDXpath = ".//*[@id='referenceId']";

	public String inputReferenceID(String referenceID) {
		int random = (int) (Math.random() * 5000 + 1);
		referenceID += random;
		try {
			UIHelper.highlightElement(driver, referenceIDXpath);
			UIHelper.click(driver, referenceIDXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, referenceIDXpath, referenceID);
			report.updateTestLog("Input  ", "Reference ID:" + referenceID, Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("unable to locate Input  ", "Reference ID:" + referenceID, Status.FAIL);
		}
		return referenceID;

	}

	private String requiredFieldErrorMessageForReferenceIdXpath = ".//span[contains(text(),'Your Reference ID includes invalid characters')]";

	public void verifyErrorMessageForInvalideReference() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, requiredFieldErrorMessageForReferenceIdXpath)) {
				UIHelper.highlightElement(driver, requiredFieldErrorMessageForReferenceIdXpath);
				report.updateTestLog(
						"Error Message displayed Successfully  "
								+ UIHelper.findAnElementbyXpath(driver, requiredFieldErrorMessageForReferenceIdXpath)
										.getText().trim(),
						"while clicking on save and upload with invalide reference ID ", Status.PASS);
			} else {
				report.updateTestLog(
						"Error Message not displayed   "
								+ UIHelper.findAnElementbyXpath(driver, requiredFieldErrorMessageForReferenceIdXpath)
										.getText().trim(),
						"while clicking on save and upload with invalide reference ID ", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String streamingUploadProgressBoxXpath = ".//h1[text()='Uploading']";
	private String transcodingInProgressOrmetaDataSubmittedPromptXpath = ".//*[contains(@class,'yui-module yui-overlay yui-panel')]";
	private String transcodingInProgressOrmetaDataSubmittedPromptOkButtonXpath = ".//button[text()='OK']";

	public void verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, streamingUploadProgressBoxXpath);
		if (UIHelper.checkForAnElementbyXpath(driver, streamingUploadProgressBoxXpath)) {
			UIHelper.highlightElement(driver, streamingUploadProgressBoxXpath);

			report.updateTestLog("upload progress box displayed successfuly",
					UIHelper.findAnElementbyXpath(driver, streamingUploadProgressBoxXpath).getText().trim(),
					Status.PASS);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, streamingUploadProgressBoxXpath);
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
					transcodingInProgressOrmetaDataSubmittedPromptXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, transcodingInProgressOrmetaDataSubmittedPromptXpath)) {
				UIHelper.highlightElement(driver, transcodingInProgressOrmetaDataSubmittedPromptOkButtonXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("transcoding in progress prompt box displayed successfuly",
						UIHelper.findAnElementbyXpath(driver,
								transcodingInProgressOrmetaDataSubmittedPromptOkButtonXpath).getText().trim(),
						Status.PASS);
				UIHelper.click(driver, transcodingInProgressOrmetaDataSubmittedPromptOkButtonXpath);

			} else {
				report.updateTestLog("transcoding in progress prompt box is not displayed ",
						UIHelper.findAnElementbyXpath(driver,
								transcodingInProgressOrmetaDataSubmittedPromptOkButtonXpath).getText().trim(),
						Status.FAIL);

			}

		} else {
			report.updateTestLog("upload progress box is not displayed ",
					UIHelper.findAnElementbyXpath(driver, streamingUploadProgressBoxXpath).getText().trim(),
					Status.FAIL);

		}
	}

	public void verifyStreamingUploadProgressAndClickOkOnMetaDataSubmittedPromptBox() {

		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, streamingUploadProgressBoxXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, transcodingInProgressOrmetaDataSubmittedPromptXpath);
		if (UIHelper.checkForAnElementbyXpath(driver, transcodingInProgressOrmetaDataSubmittedPromptXpath)) {
			UIHelper.highlightElement(driver, transcodingInProgressOrmetaDataSubmittedPromptOkButtonXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("transcoding in progress prompt box displayed successfuly",
					UIHelper.findAnElementbyXpath(driver, transcodingInProgressOrmetaDataSubmittedPromptOkButtonXpath)
							.getText().trim(),
					Status.PASS);
			UIHelper.click(driver, transcodingInProgressOrmetaDataSubmittedPromptOkButtonXpath);

		} else {
			report.updateTestLog("transcoding in progress prompt box is not displayed ",
					UIHelper.findAnElementbyXpath(driver, transcodingInProgressOrmetaDataSubmittedPromptOkButtonXpath)
							.getText().trim(),
					Status.FAIL);

		}

	}

	public String inputReferenceIDwithoutRandomNumber(String previousReferenceId) {

		try {
			UIHelper.highlightElement(driver, referenceIDXpath);
			UIHelper.click(driver, referenceIDXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, referenceIDXpath, previousReferenceId);
			report.updateTestLog("Input  ", "*Title:" + previousReferenceId, Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("unable to locate Input  ", "*Title:" + previousReferenceId, Status.FAIL);
		}
		return previousReferenceId;

	}

	private String popUpBoxForDuplicateReferenceID = ".//*[contains(text(),'This Reference ID already exists')]";

	public void verifyPopUpMessageForDuplicateReferenceID() {
		UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, popUpBoxForDuplicateReferenceID);
		if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, popUpBoxForDuplicateReferenceID))) {
			UIHelper.highlightElement(driver, popUpBoxForDuplicateReferenceID);

			report.updateTestLog("Message  ",
					"*displayed:"
							+ UIHelper.findAnElementbyXpath(driver, popUpBoxForDuplicateReferenceID).getText().trim(),
					Status.PASS);
		} else {
			report.updateTestLog("Message  ",
					"*displayed:"
							+ UIHelper.findAnElementbyXpath(driver, popUpBoxForDuplicateReferenceID).getText().trim(),
					Status.FAIL);
		}
	}

	private String rightFoldersListFromShareUiXpath = "//*[contains(@class,'documents ')]//*[contains(@class,'yui-dt-data')]//tr[contains(@class,'yui-dt-rec')]//td//h3//a";

	public ArrayList<String> getFilesAndFoldersListFromDocumentLibrary() {

		ArrayList<String> foldersListFromRightPanInShareUi = new ArrayList<String>();
		StringBuffer listOfFolders = new StringBuffer();
		int i = 1;
		try {
			List<WebElement> foldersFromRightInShareUiEle = UIHelper
					.findListOfElementsbyXpath(rightFoldersListFromShareUiXpath, driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, rightFoldersListFromShareUiXpath);
			UIHelper.highlightElement(driver, rightFoldersListFromShareUiXpath);

			for (WebElement ele : foldersFromRightInShareUiEle) {
				foldersListFromRightPanInShareUi.add(ele.getText().trim());
			}

			for (String folderName : foldersListFromRightPanInShareUi) {
				listOfFolders.append(i++ + "." + folderName + ",\n");
			}

			report.updateTestLog("Verify file/folders from share UI ",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1) + "are Available by default",
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify File/folders from share UI",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1) + "are Available by default",
					Status.FAIL);
			e.printStackTrace();
		}
		return foldersListFromRightPanInShareUi;

	}

	public void verifyStreamingMediaFolderNameAndTitle(String givenTitle,
			ArrayList<String> filesAndFoldersListFromDocumentLibrary) {
		if (filesAndFoldersListFromDocumentLibrary.contains(givenTitle)) {

			report.updateTestLog("Expected:" + givenTitle, "Actual existence of expected content: "
					+ filesAndFoldersListFromDocumentLibrary.contains(givenTitle), Status.PASS);
		} else {
			report.updateTestLog(" Expected :" + givenTitle, "   Actual existence of expected content: "
					+ filesAndFoldersListFromDocumentLibrary.contains(givenTitle), Status.FAIL);

		}
	}

	public void verifyStreamingMediaFolderNameAndTitleNegative(String givenTitle,
			ArrayList<String> filesAndFoldersListFromDocumentLibrary) {
		if (filesAndFoldersListFromDocumentLibrary.contains(givenTitle)) {

			report.updateTestLog("Expected:" + givenTitle, "Actual existence of expected content: "
					+ filesAndFoldersListFromDocumentLibrary.contains(givenTitle), Status.FAIL);
		} else {
			report.updateTestLog(" Expected :" + givenTitle, "   Actual existence of expected content: "
					+ filesAndFoldersListFromDocumentLibrary.contains(givenTitle), Status.PASS);

		}
	}

	public void clickOnStreamingMediaOnJsonFile() {
		ArrayList<String> filesAndFoldersListFromDocumentLibrary = getFilesAndFoldersListFromDocumentLibrary();
		for (String file : filesAndFoldersListFromDocumentLibrary) {
			if (file.contains(".json")) {
				clickOnMoreSetting(file);
				commonMethodForClickOnMoreSettingsOption(file, "Streaming Media");

			}

		}

	}

	private String tempXpathForMoreSettingsOptLink = ".//*[contains(@id,'yuievtautoid')]//*[text()='CRAFT']//ancestor::tr//a/span[contains(.,'MORE_OPT')]";

	public void clickMoreSettingsOptionOnly(String fileOrfolderName, String moreSettingsOptName) {
		try {
			String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink.replace("CRAFT", fileOrfolderName)
					.replace("MORE_OPT", moreSettingsOptName);
			UIHelper.waitFor(driver);
			WebElement moreSettingsOptElement = UIHelper.findAnElementbyXpath(driver, finalXpathForCopyToFolderLink);
			UIHelper.highlightElement(driver, moreSettingsOptElement);
			report.updateTestLog("Click on " + moreSettingsOptName,
					"User able to click the " + moreSettingsOptName + " for file " + fileOrfolderName, Status.PASS);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", moreSettingsOptElement);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on " + moreSettingsOptName,
					"User not able to click the " + moreSettingsOptName + " for file " + fileOrfolderName, Status.FAIL);
		}
	}

	private String messageXpath = ".//*[@id='message']/div";

	public void commonMethodForClickOnMoreSettingsOption(String fileOrfolderName, String moreSettingsOptName) {
		try {
			clickMoreSettingsOptionOnly(fileOrfolderName, moreSettingsOptName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnMoreSetting(String fileOrFolderName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT",
					fileOrFolderName);
			String finalXpathForMoreOptionLink = tempXpathForMoreOptionLink.replace("CRAFT", fileOrFolderName);

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * myFilesTablesXpath); WebElement myFilesTableEle =
			 * driver.findElement(By .xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					// UIHelper.scrollToAnElement(ele);
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
							"User successfully clicked the <b> 'More Settings'</b> Option using " + fileOrFolderName,
							Status.DONE);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String descriptionFieldXpath = ".//textarea[contains(@id,'description')]";

	public String inputDescription(String description) {
		description += "Description";

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, descriptionFieldXpath);
			UIHelper.highlightElement(driver, descriptionFieldXpath);
			UIHelper.click(driver, descriptionFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, descriptionFieldXpath, description);
			report.updateTestLog("Input  ", "Description:" + description, Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("unable to locate Input  ", "Description:" + description, Status.FAIL);
		}
		return description;

	}

	private String playListFieldXpath = ".//input[contains(@id,'playlist')]";

	public String inputPlayListID(String platListID) {
		platListID += "PlatListID";

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, playListFieldXpath);

			UIHelper.highlightElement(driver, playListFieldXpath);
			UIHelper.click(driver, playListFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, playListFieldXpath, platListID);
			report.updateTestLog("Input  ", "platListID:" + platListID, Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("unable to locate Input  ", "platListID:" + platListID, Status.FAIL);
		}
		return platListID;

	}

	private String keywordFieldXpath = ".//textarea[contains(@id,'tags')]";

	public String inpitKeyword(String keyword) {
		keyword += "Keyword";

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, keywordFieldXpath);

			UIHelper.highlightElement(driver, keywordFieldXpath);
			UIHelper.click(driver, keywordFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, keywordFieldXpath, keyword);
			report.updateTestLog("Input  ", "keyword:" + keyword, Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("unable to locate Input  ", "keyword:" + keyword, Status.FAIL);
		}
		return keyword;

	}

	private String transcodingInProgressXpath = ".//p[text()='Please wait. Transcoding is in progress.']";

	public void waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab() {
		int count = 0;
		try {

			do {
				if (UIHelper.checkForAnElementbyXpath(driver, transcodingInProgressXpath)) {
					UIHelper.highlightElement(driver, transcodingInProgressXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForLong(driver);
					driver.navigate().refresh();
					count++;
				} else {
					UIHelper.click(driver, uploadFilesTabXpath);
					break;
				}
			} while (count != 10);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String thumbnailChoseButtonInputXpath = ".//*[contains(text(),'Thumbnail Image')]/following-sibling::div//input[@id='thumbnailImage']";
	private String thumbnailImageXpath = ".//*[@id='SingleFileUploadForm']/div[2]/div[2]/div[2]/div[1]";

	public void uploadAndVerifyValidThumbnail(String filePath, String thumbnail) {

		try {

			WebElement fileInput = driver.findElement(By.xpath(thumbnailChoseButtonInputXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + thumbnail);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, thumbnailImageXpath);
			UIHelper.highlightElement(driver, thumbnailImageXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, unsupportWarningMessageXpath)) {
				report.updateTestLog("Thumbnail  ", thumbnail + " is unsupported format", Status.FAIL);

			} else {
				report.updateTestLog("Thumbnail  ", thumbnail + " is supported format", Status.PASS);

			}

		} catch (Exception e) {
			UIHelper.highlightElement(driver, unsupportWarningMessageXpath);
			report.updateTestLog("Thumbnail  ", thumbnail + " is unsupported format", Status.FAIL);

		}

	}

	public void uploadAndVerifyValidThumbnailNegative(String filePath, String thumbnail) {

		try {

			WebElement fileInput = driver.findElement(By.xpath(thumbnailChoseButtonInputXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + thumbnail);
			UIHelper.waitForVisibilityOfEleByXpath(driver, thumbnailImageXpath);
			UIHelper.highlightElement(driver, thumbnailImageXpath);
			if (!UIHelper.checkForAnElementbyXpath(driver, unsupportWarningMessageXpath)) {

				report.updateTestLog("Thumbnail  ", thumbnail + " is supported format", Status.FAIL);

			} else {
				report.updateTestLog("Thumbnail  ", thumbnail + " is unsupported format", Status.PASS);

			}

		} catch (Exception e) {
			UIHelper.highlightElement(driver, unsupportWarningMessageXpath);
			report.updateTestLog("Thumbnail  ", thumbnail + " is unsupported format", Status.PASS);

		}
	}

	private String removeThumbnailXpath = ".//*[contains(text(),'Thumbnail Image')]/following-sibling::div[2]";
	private String deleteThumbnailPromptBoxXpath = ".//*[contains(text(),'Are you sure you want to delete the thumbnail?')]";
	private String yesButtonXpathForThumbnail = ".//*[contains(text(),'Are you sure you want to delete the thumbnail?')]/following-sibling::div//button[text()='Yes']";

	public void clickOnRemoveThumbnail() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, removeThumbnailXpath);
			UIHelper.highlightElement(driver, removeThumbnailXpath);
			UIHelper.click(driver, removeThumbnailXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteThumbnailPromptBoxXpath);
			UIHelper.highlightElement(driver, deleteThumbnailPromptBoxXpath);
			UIHelper.click(driver, yesButtonXpathForThumbnail);
			report.updateTestLog("click on Remove Thumbnail  ", "Success", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("click on Remove Thumbnail  ", "Success", Status.FAIL);

		}

	}

	private String chapterChoseButtonInputXpath = ".//*[contains(text(),'Chaptering XML File')]/following-sibling::div//input[@id='chapteringFile']";

	public void uploadAndVerifyValidChapteringXML(String filePath, String ChapteringXML) {

		try {

			WebElement fileInput = driver.findElement(By.xpath(chapterChoseButtonInputXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + ChapteringXML);

			if (UIHelper.checkForAnElementbyXpath(driver, unsupportWarningMessageXpath)) {

				report.updateTestLog("Chapter XML  ", ChapteringXML + " is unsupported format", Status.FAIL);

			} else {
				report.updateTestLog("Chapter XML  ", ChapteringXML + " is supported format", Status.PASS);

			}

		} catch (Exception e) {
			UIHelper.highlightElement(driver, unsupportWarningMessageXpath);
			report.updateTestLog("Chapter XML  ", ChapteringXML + " is supported format", Status.PASS);

		}

	}

	private String removeChapterXpath = ".//*[@id='SingleFileUploadForm']/div[2]/div[2]/div[4]/div/span";
	private String deleteChapterPromptBoxXpath = ".//*[contains(text(),'Are you sure you want to delete the chaptering XML?')]";
	private String yesButtonXpathForChapter = ".//*[contains(text(),'Are you sure you want to delete the chaptering XML?')]/following-sibling::div//button[text()='Yes']";

	public void clickOnRemoveChapter() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, removeChapterXpath);
			UIHelper.highlightElement(driver, removeChapterXpath);
			UIHelper.click(driver, removeChapterXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteChapterPromptBoxXpath);
			UIHelper.highlightElement(driver, deleteChapterPromptBoxXpath);
			UIHelper.click(driver, yesButtonXpathForChapter);
			report.updateTestLog("click on Remove Thumbnail  ", "Success", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("click on Remove Thumbnail  ", "Success", Status.FAIL);

		}

	}

	public void uploadAndVerifyValidChapteringXMLNegative(String filePath, String ChapteringXML) {

		try {

			WebElement fileInput = driver.findElement(By.xpath(chapterChoseButtonInputXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + ChapteringXML);

			if (!UIHelper.checkForAnElementbyXpath(driver, unsupportWarningMessageXpath)) {

				report.updateTestLog("Chapter XML  ", ChapteringXML + " is supported format", Status.FAIL);

			} else {
				report.updateTestLog("Chapter XML  ", ChapteringXML + " is unsupported format", Status.PASS);

			}

		} catch (Exception e) {
			UIHelper.highlightElement(driver, unsupportWarningMessageXpath);
			report.updateTestLog("Chapter XML  ", ChapteringXML + " is unsupported format", Status.PASS);

		}

	}

	private String choseCaptionInputXpath = ".//*[@id='SingleFileUploadForm']/div[2]/div[3]/div[2]/div/input";
	private String captionFileXpath = ".//*[@id='SingleFileUploadForm']/div[2]/div[3]/div[2]/div/div[ROW]/input";
	private String captionFileXpathTemp;
	private String languageDropDownXpathTemp = ".//*[@id='captionFileTypeROW']";
	private String languageDropDownXpath;
	private String unsupportCaptionFileXpath = ".//*[contains(text(),'Unsupported format. Click Help for details on supported formats')]";

	public void uploadCaptionsFile(String filePath, String captionFile, String Language) {
		try {
			WebElement fileInput = driver.findElement(By.xpath(choseCaptionInputXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + captionFile);

			captionFileXpathTemp = captionFileXpath.replace("ROW", "1");
			UIHelper.waitForVisibilityOfEleByXpath(driver, captionFileXpathTemp);
			UIHelper.highlightElement(driver, captionFileXpathTemp);

			languageDropDownXpath = languageDropDownXpathTemp.replace("ROW", "1");

			UIHelper.waitFor(driver);
			WebElement languageDropdownEle = UIHelper.findAnElementbyXpath(driver, languageDropDownXpath);
			UIHelper.highlightElement(driver, languageDropdownEle);
			UIHelper.click(driver, languageDropDownXpath);
			UIHelper.selectbyVisibleText(driver, languageDropDownXpath, Language);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, languageDropDownXpath);
			report.updateTestLog(
					"Caption file: "
							+ UIHelper.findAnElementbyXpath(driver, captionFileXpathTemp).getAttribute("value").trim(),
					"is uploaded successfully", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog(
					"Caption file: " + UIHelper.findAnElementbyXpath(driver, captionFileXpathTemp).getText().trim(),
					"upload is was not success", Status.FAIL);

		}

	}

	private String addFileButtonXpath = ".//label[text()='Add file']";
	private String existingListOfCaptionFilesXpath = ".//*[@id='SingleFileUploadForm']//input[@class='small-textbox caption']";
	private String addFileInputXpath = ".//label[contains(text(),'Add file')]//following-sibling::input";

	public void addCaptionsFile(String filePath, String captionFile, String Language) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, addFileButtonXpath);
			UIHelper.highlightElement(driver, addFileButtonXpath);
			int ROW;
			List<WebElement> existingListOfCaptionFiles = UIHelper
					.findListOfElementsbyXpath(existingListOfCaptionFilesXpath, driver);
			ROW = existingListOfCaptionFiles.size() + 1;
			languageDropDownXpath = languageDropDownXpathTemp.replace("ROW", "" + ROW);

			// for (WebElement webElement :existingListOfCaptionFiles) {
			// System.out.println("I am here");
			// System.out.println(webElement.getAttribute("value").trim());
			// }
			WebElement fileInput = driver.findElement(By.xpath(addFileInputXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + captionFile);

			UIHelper.waitFor(driver);
			WebElement languageDropdownEle = UIHelper.findAnElementbyXpath(driver, languageDropDownXpath);
			UIHelper.highlightElement(driver, languageDropdownEle);
			UIHelper.click(driver, languageDropDownXpath);
			UIHelper.selectbyVisibleText(driver, languageDropDownXpath, Language);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, languageDropDownXpath);
			report.updateTestLog("Caption file: " + captionFile, "is added successfully", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Caption file: " + captionFile, "is failed to add", Status.FAIL);

		}

	}

	public void verifyCompatibilityOfCaptionFile(String captionFile) {
		try {
			if (!UIHelper.checkForAnElementbyXpath(driver, unsupportCaptionFileXpath)) {
				UIHelper.highlightElement(driver, unsupportCaptionFileXpath);
				report.updateTestLog("Caption file: " + captionFile, "is Supported format", Status.PASS);
			} else {
				report.updateTestLog("Caption file: " + captionFile, "is NOT Supported format", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyCompatibilityOfCaptionFileNegative(String captionFile) {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, unsupportCaptionFileXpath)) {
				UIHelper.highlightElement(driver, unsupportCaptionFileXpath);

				report.updateTestLog("Caption file: " + captionFile, "is NOT Supported format", Status.PASS);
			} else {
				report.updateTestLog("Caption file: " + captionFile, "is  Supported format", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyPreviewTab() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, priveTabXpath);
			UIHelper.highlightElement(driver, priveTabXpath);
			UIHelper.click(driver, priveTabXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, embeddingURL)
					&& UIHelper.checkForAnElementbyXpath(driver, standAloneURL)
					&& UIHelper.checkForAnElementbyXpath(driver, citeURL)) {

				UIHelper.highlightElement(driver, embeddingURL);
				UIHelper.highlightElement(driver, standAloneURL);
				UIHelper.highlightElement(driver, citeURL);
				UIHelper.highlightElement(driver, embeddingURL + Link);
				UIHelper.highlightElement(driver, standAloneURL + Link);
				UIHelper.highlightElement(driver, citeURL + Link);
				report.updateTestLog(
						"Preview Tab displayed successfully with "
								+ UIHelper.findAnElementbyXpath(driver, embeddingURL).getText().trim() + " and "
								+ UIHelper.findAnElementbyXpath(driver, standAloneURL).getText().trim() + " and "
								+ UIHelper.findAnElementbyXpath(driver, citeURL).getText().trim(),
						"and the respective URL Values are displayed as bellow "
								+ UIHelper.findAnElementbyXpath(driver, embeddingURL + Link).getText().trim() + " and "
								+ UIHelper.findAnElementbyXpath(driver, standAloneURL + Link).getText().trim() + " and "
								+ UIHelper.findAnElementbyXpath(driver, citeURL + Link).getText().trim(),
						Status.PASS);

			} else {
				report.updateTestLog("No URL are visible", "", Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Preview failed to load ", "", Status.FAIL);
		}

	}

	public void verifyRelatedFilesTabFieldsAfterTranscoding() {

		UIHelper.waitForVisibilityOfEleByXpath(driver, relatedFilesTabXpath);
		UIHelper.highlightElement(driver, relatedFilesTabXpath);
		UIHelper.click(driver, relatedFilesTabXpath);

		List<WebElement> availableFieldsElement = UIHelper.findListOfElementsbyXpath(availableFieldsXpath, driver);
		ArrayList<String> availableFieldsList = new ArrayList<String>();
		ArrayList<String> availableFieldsListtemp = new ArrayList<String>();
		String[] expectedList = { "Thumbnail Image", "Chaptering XML File", "Caption", "Add file" };
		for (String string : expectedList) {
			availableFieldsListtemp.add(string);
		}

		UIHelper.waitFor(driver);

		for (WebElement webElement : availableFieldsElement) {
			UIHelper.highlightElement(driver, webElement);
			if (!webElement.getText().trim().equals("")) {
				availableFieldsList.add(webElement.getText().trim());
			}

		}
		if (availableFieldsList.equals(availableFieldsListtemp)) {
			report.updateTestLog("Expected:  " + availableFieldsListtemp, "Actual: " + availableFieldsList,
					Status.PASS);
		} else {
			report.updateTestLog("Expected:  " + availableFieldsListtemp, "Actual: " + availableFieldsList,
					Status.FAIL);
		}

	}

	public void verifyAutopulatedReferenceID(String movTitle) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFilesTabXpath);
			UIHelper.highlightElement(driver, uploadFilesTabXpath);
			UIHelper.click(driver, uploadFilesTabXpath);

			UIHelper.highlightElement(driver, referenceIDXpath);

			String existingReferenceID = getStringFromClipboard(referenceIDXpath);
			if (existingReferenceID.equals(movTitle)) {
				report.updateTestLog("Expected Auto populated:  " + movTitle,
						"Actual Auto populated: " + existingReferenceID, Status.PASS);
			} else {
				report.updateTestLog("Expected Auto populated:  " + movTitle,
						"Actual Auto populated: " + existingReferenceID, Status.FAIL);
			}

		} catch (Exception e) {

			e.printStackTrace();
			report.updateTestLog("Exception ", "unable to perform verifyReferenceIDEqualsToGivenTitle ", Status.FAIL);

		}

	}

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

	public String getStringFromClipboard(WebElement element) {
		String result = "";

		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.mouseOverandElementdoubleClick(driver, element);
			UIHelper.waitFor(driver);
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

	private String uploadFilesFieldsInputFieldXpath = ".//*[contains(@class,'form-container')]//label/following-sibling::input | //*[contains(@class,'form-container')]//label/following-sibling::textarea | //*[contains(@class,'form-container')]//label/following-sibling::select";
	private String title = "";

	public void validateReadonlyFieldsInUploadFiles() {
		title = TitleFieldXpath + "/preceding-sibling::label";
		try {
			WebElement webElement = UIHelper.findAnElementbyXpath(driver, TitleFieldXpath);

			if (isAttribtuePresent(webElement, "readonly")) {
				UIHelper.waitFor(driver);
				webElement.click();
				UIHelper.waitFor(driver);

				report.updateTestLog(UIHelper.findAnElementbyXpath(driver, title).getText().trim() + " has the value "
						+ getStringFromClipboard(webElement), "is read only", Status.PASS);

			} else {
				UIHelper.waitFor(driver);

				webElement.click();
				UIHelper.waitFor(driver);

				report.updateTestLog("The selected Web element  " + getStringFromClipboard(webElement),
						"is not read only", Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("unable to locate the elements ", "in upload files tab", Status.FAIL);

		}

	}

	private boolean isAttribtuePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	private String deleteThumbnailXpath = ".//*[contains(text(),'Thumbnail Image')]/following-sibling::div[2]//span[contains(@title,'Remove')]";

	public void deleteThumbnail() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, relatedFilesTabXpath);
			UIHelper.highlightElement(driver, relatedFilesTabXpath);
			UIHelper.click(driver, relatedFilesTabXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteThumbnailXpath);
			UIHelper.highlightElement(driver, deleteThumbnailXpath);
			UIHelper.click(driver, deleteThumbnailXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteThumbnailPromptBoxXpath);
			UIHelper.highlightElement(driver, deleteThumbnailPromptBoxXpath);
			UIHelper.click(driver, yesButtonXpathForThumbnail);

			report.updateTestLog("successfully deleted ", "Thumbnail", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(" unable to delete ", "Thumbnail", Status.FAIL);

		}

	}

	private String deleteChapteringXml = ".//div[@class='form-field fleft ']/label[contains(text(),'Chaptering XML File')]/following-sibling::div/span";

	public void deleteChaptering() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, relatedFilesTabXpath);
			UIHelper.highlightElement(driver, relatedFilesTabXpath);
			UIHelper.click(driver, relatedFilesTabXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteChapteringXml);
			UIHelper.highlightElement(driver, deleteChapteringXml);
			UIHelper.click(driver, deleteChapteringXml);

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteChapterPromptBoxXpath);
			UIHelper.highlightElement(driver, deleteChapterPromptBoxXpath);
			UIHelper.click(driver, yesButtonXpathForChapter);

			report.updateTestLog("successfully deleted ", "Chapter XML", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(" unable to delete ", "Chapter XML", Status.FAIL);

		}

	}

	public void deleteCaption() {

		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, relatedFilesTabXpath);
			UIHelper.highlightElement(driver, relatedFilesTabXpath);
			UIHelper.click(driver, relatedFilesTabXpath);

			UIHelper.waitFor(driver);

			List<WebElement> listOfDeleteButtons = UIHelper.findListOfElementsbyXpath(deleteCaptionsXpath, driver);

			UIHelper.waitFor(driver);

			for (WebElement webElement : listOfDeleteButtons) {

				UIHelper.waitFor(driver);

				UIHelper.highlightElement(driver, webElement);
				UIHelper.clickanElement(webElement);

				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteCaptionPromptBoxXpath);
				UIHelper.highlightElement(driver, deleteCaptionPromptBoxXpath);
				UIHelper.click(driver, yesButtonXpathForCaption);

				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("successfully deleted ", "Caption", Status.PASS);

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(" unable to delete ", "Caption", Status.FAIL);

		}
	}

	public void verifyRelatedFilesToTheJson() {

		try {
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, RelationshipTableXpath);
			UIHelper.highlightElement(driver, RelationshipTableXpath);
			ArrayList<String> relatedFilesLinkVal = new ArrayList<String>();

			UIHelper.waitFor(driver);
			List<WebElement> relatedFilesLink = UIHelper.findListOfElementsbyXpath(relatedFilesLinkXpath, driver);
			UIHelper.waitFor(driver);

			for (WebElement webElement : relatedFilesLink) {
				UIHelper.waitFor(driver);

				relatedFilesLinkVal.add(webElement.getText().trim());

			}

			report.updateTestLog("json Files has the following linked files to it", "" + relatedFilesLinkVal,
					Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(" unable to get the related files to the json  ", "Exception occured", Status.FAIL);

		}

	}

	public void deleteVideoInStreamingPageAndVerifyStreamingContentInStreamingFolder() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFilesTabXpath);
			UIHelper.highlightElement(driver, uploadFilesTabXpath);
			UIHelper.click(driver, uploadFilesTabXpath);

			UIHelper.highlightElement(driver, deleteVideoInUploadFilesTabXpath);
			UIHelper.click(driver, deleteVideoInUploadFilesTabXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteVideoInUploadFilesPromptBoxXpath);
			UIHelper.highlightElement(driver, deleteVideoInUploadFilesPromptBoxXpath);

			UIHelper.click(driver, yesButtonXpathForDeleteVideo);

			UIHelper.waitForVisibilityOfEleByXpath(driver, PopuptitleXpath);
			UIHelper.highlightElement(driver, PopuptitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, PopuptitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteSuccessMessage);
			UIHelper.click(driver, deleteSuccessMessageOkButton);

			report.updateTestLog("Delete video on upload files Tab ", "Success", Status.PASS);

			clickOnCancle();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {

			e.printStackTrace();
			report.updateTestLog("Delete video on upload files Tab ", "Exception", Status.FAIL);
		}

	}

	public void clickonURL(String type) {

		try {

			String finalurl = urllink.replace("CRAFT", type);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalurl);
			if (UIHelper.checkForAnElementbyXpath(driver, finalurl)) {
				UIHelper.highlightElement(driver, finalurl);
				UIHelper.click(driver, finalurl);
				UIHelper.waitFor(driver);
				report.updateTestLog("Click " + type + "URL", type + "clicked successfully", Status.PASS);
			} else {
				report.updateTestLog("Click " + type + "URL", type + "clicked failed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click " + type + "URL", type + "clicked failed", Status.FAIL);
		}

	}

	public String FormatofURL(String type, String urlpart, String extension, String mp4, String mp3) {

		String URL = "";

		String finalurl = urllink.replace("CRAFT", type);
		UIHelper.waitForVisibilityOfEleByXpath(driver, finalurl);
		if (UIHelper.checkForAnElementbyXpath(driver, finalurl)) {
			UIHelper.highlightElement(driver, finalurl);
			URL = UIHelper.getTextFromWebElement(driver, finalurl);
			String spliturl[] = URL.split("_");
			String firstSplit[] = spliturl[1].split("\\?");
			String Actualurl = spliturl[0] + firstSplit[0].subSequence(0, 23) + firstSplit[1];
			System.out.println(Actualurl);

			if (extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("mov")
					|| extension.equalsIgnoreCase("m4v")) {

				if (Actualurl.equalsIgnoreCase(mp4)) {
					report.updateTestLog("Check the URL Format: ",
							"URL format is " + URL + " and it is in correct Format", Status.PASS);
				} else {
					report.updateTestLog("Check the URL Format: ",
							"URL format is " + URL + " and it is in correct Format", Status.FAIL);
				}
			} else if (extension.equalsIgnoreCase("mp3")) {

				if (Actualurl.equalsIgnoreCase(mp3)) {
					report.updateTestLog("Check the URL Format: ",
							"URL format is " + URL + " and it is in correct Format", Status.PASS);
				} else {
					report.updateTestLog("Check the URL Format: ",
							"URL format is " + URL + " and it is in correct Format", Status.FAIL);
				}

			} else {
				report.updateTestLog("Check the URL Format: ", "URL format is " + URL + " and it is in correct Format",
						Status.FAIL);
			}

		}

		return URL;
	}

	public void verifyReplaceIconNegativeBeforeTranscoding() {
		try {
			if (!UIHelper.checkForAnElementbyXpath(driver, replaceButtonXpath)) {
				report.updateTestLog("Verify Replace Icon before trancoding",
						"Replace Icon should not present before trancoding", Status.PASS);
			} else {
				report.updateTestLog("Verify Replace Icon before trancoding",
						"Replace Icon is present before trancoding", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Replace Icon before trancoding", "Replace Icon is present before trancoding",
					Status.FAIL);
			e.printStackTrace();
		}
	}

	public void verifyReplaceIcon() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, replaceButtonXpath)) {
				UIHelper.highlightElement(driver, replaceButtonXpath);
				report.updateTestLog("Verify Replace Icon after trancoding",
						"Replace Icon should present after trancoding", Status.PASS);
			} else {
				report.updateTestLog("Verify Replace Icon after trancoding",
						"Replace Icon should not present after trancoding", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Replace Icon after trancoding",
					"Replace Icon should not present after trancoding", Status.FAIL);
			e.printStackTrace();
		}
	}

	public void verifyeditStreamingPage() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, editStreamingMediaXpath)) {
				report.updateTestLog("Verify page Navigated to Edit Streaming media",
						"Edit Streaming media Navigated Successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify page Navigated to Edit Streaming media",
						"Edit Streaming media Page failed to navigate", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify page Navigated to Edit Streaming media",
					"Edit Streaming media Page failed to navigate", Status.FAIL);
			e.printStackTrace();

		}
	}

	public void clickUploadFilesTabFields() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFilesTabXpath);
			UIHelper.highlightElement(driver, uploadFilesTabXpath);
			UIHelper.click(driver, uploadFilesTabXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e)

		{
			e.printStackTrace();
		}

	}

	public void uploadFileInReplaceIconNegative(String filePath, String fileName) {

		try {

			WebElement fileInput = driver.findElement(By.xpath(replaceFileUploadInputFieldXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, replaceSelectedFileXpath);
			UIHelper.highlightElement(driver, replaceSelectedFileXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, replaceSelectedFileXpath))) {
				String selectedFileName = UIHelper.findAnElementbyXpath(driver, replaceSelectedFileXpath)
						.getAttribute("value").trim();
				if (selectedFileName.contains(fileName)) {
					if (UIHelper.checkForAnElementbyXpath(driver, unsupportWarningMessageXpath)) {
						UIHelper.highlightElement(driver, unsupportWarningMessageXpath);

						report.updateTestLog("Expected upload file with inappropriate format:  " + fileName,
								"Actual upload file: " + selectedFileName + " " + UIHelper
										.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
								Status.PASS);
					} else {
						report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
								"Actual upload file: " + selectedFileName + " " + UIHelper
										.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
								Status.FAIL);

					}

				} else {
					report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
							"Actual upload file: " + selectedFileName + " " + UIHelper
									.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
							Status.FAIL);
				}

			} else {
				report.updateTestLog("Expected upload file: NOT FOUD ", "Actual upload file: ", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("upload action failed", "upload action failed ", Status.FAIL);
		}

	}

	public void uploadFileInReplaceIcon(String filePath, String fileName) {

		try {

			WebElement fileInput = driver.findElement(By.xpath(replaceFileUploadInputFieldXpath));

			fileInput.sendKeys(System.getProperty("user.dir") + filePath + fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, replaceSelectedFileXpath);
			UIHelper.highlightElement(driver, replaceSelectedFileXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, replaceSelectedFileXpath))) {
				String selectedFileName = UIHelper.findAnElementbyXpath(driver, replaceSelectedFileXpath)
						.getAttribute("value").trim();
				if (selectedFileName.contains(fileName)) {
					if (!UIHelper.checkForAnElementbyXpath(driver, unsupportWarningMessageXpath)) {

						report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
								"Actual upload file: " + selectedFileName + "", Status.PASS);
					} else {
						report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
								"Actual upload file: " + selectedFileName + " " + UIHelper
										.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
								Status.FAIL);

					}

				} else {
					report.updateTestLog("Expected upload file with appropriate format:  " + fileName,
							"Actual upload file: " + selectedFileName + " " + UIHelper
									.findAnElementbyXpath(driver, unsupportWarningMessageXpath).getText().trim(),
							Status.FAIL);
				}

			} else {
				report.updateTestLog("Expected upload file: NOT FOUD ", "Actual upload file: ", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("upload action failed", "upload action failed ", Status.FAIL);
		}

	}

	public ArrayList<String> getTypeOffileOptionUpload() {
		try {
			UIHelper.findAndAddElementsToaList(driver, fileUploadTypesXpath, filesTypes);
			for (String s1 : filesTypes) {
				UIHelper.highlightElement(driver, s1);
			}
		} catch (Exception E) {
			E.printStackTrace();
		}
		return filesTypes;
	}

	public void verifyFileOptionAvailable(ArrayList<String> expected) {
		try {
			ArrayList<String> actFileType = getTypeOffileOptionUpload();
			if (UIHelper.compareTwoSimilarLists(actFileType, expected)) {
				report.updateTestLog("verify User able to see file type options for upload ",
						"User successfully able to see file type options " + "<br><b>:File Type Options are,"
								+ actFileType + "",
						Status.PASS);
			} else {
				report.updateTestLog("verify User able to see file type options for upload ",
						"User can't able to see file type options" + "<b>:File Type Options are," + actFileType + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("verify User should able to see file type options for upload ",
					"User can't able to see file type options", Status.FAIL);
			e.printStackTrace();
		}
	}

	public ArrayList<String> getUploadedTabFields() {
		try {
			List<WebElement> availableFieldsElement = UIHelper.findListOfElementsbyXpath(availableFieldsXpath, driver);
			List<WebElement> availableButtonElement = UIHelper.findListOfElementsbyXpath(uploadTabButtonsXpath, driver);

			for (WebElement webElement : availableFieldsElement) {
				UIHelper.highlightElement(driver, webElement);
				availableFieldsList1.add(webElement.getText().trim());
			}
			for (WebElement webElement : availableButtonElement) {
				UIHelper.scrollToAnElement(webElement);
				UIHelper.highlightElement(driver, webElement);
				availableFieldsList1.add(webElement.getAttribute("value").trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return availableFieldsList1;

	}

	public void verifyUploadedTabFields(ArrayList<String> expLabelList) {
		try {

			ArrayList<String> availableLabel = getUploadedTabFields();
			if (availableLabel.containsAll(expLabelList)) {
				report.updateTestLog(
						"Verify the fields of Upload File tab in Create Streaming media screen <br><b>Expected fields are "
								+ expLabelList + "",
						"Uploaded Tab fields are displayed successfully" + "<br><b> : Available Label in upload Tab"
								+ availableLabel + "",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the fields of Upload File tab in Create Streaming media screen  <br><b>Expected fields are "
								+ expLabelList + "",
						"Uploaded Tab fields are failed to display" + "<br><b> : Available Label in upload Tab"
								+ availableLabel + "",
						Status.FAIL);

			}

		} catch (Exception e) {
			report.updateTestLog(
					"Verify the fields of Upload File tab in Create Streaming media screen  <br><b>Expected fields are "
							+ expLabelList + "",
					"Uploaded Tab fields are failed to display" + "<br><b> : Available Label in upload Tab",
					Status.FAIL);

			e.printStackTrace();
		}
	}

	public void verifySingleFileTabFields(ArrayList<String> expList) {
		try {
			ArrayList<String> singleFileTabFields = new ArrayList<String>();
			List<WebElement> availableFieldsElement = UIHelper.findListOfElementsbyXpath(singleFileTabFieldsXpath,
					driver);
			for (WebElement webElement1 : availableFieldsElement) {
				UIHelper.highlightElement(driver, webElement1);
				singleFileTabFields.add(webElement1.getText().trim());
			}
			if (UIHelper.compareTwoSimilarLists(singleFileTabFields, expList)) {
				report.updateTestLog(
						"Verify User can able to view the" + "<b>:" + expList + "</b> tabs under single file upload",
						"User can able to view tab <br> <b>:" + singleFileTabFields
								+ "</b>  Successfully under single file upload ",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify User can able to view the" + "<b>:" + expList + "</b> tabs under single file upload",
						"User failed to view tab <br> <b>:" + singleFileTabFields + " </b> under single file upload ",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify User can able to view the" + "<b>:" + expList + "</b> tabs under single file upload",
					"User failed to view tab <br> <b>:" + "</b>  under single file upload ", Status.FAIL);
		}
	}

	public void verifyBatchFileUpload() {
		try {
			UIHelper.highlightElement(driver, batchFileXpath);
			UIHelper.click(driver, batchFileXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, nolabelBatchFileXpath)) {
				report.updateTestLog("Verify no field in Batch File Upload", "Batch File does not contains any fields",
						Status.PASS);
			} else {
				report.updateTestLog("Verify no field in Batch File Upload", "Batch File contains fields", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify no field in Batch File Upload", "Batch File contains fields", Status.FAIL);
		}
	}

	public void verifyCreateButtonDisabled() {
		try {
			if (UIHelper.findAnElementbyXpath(driver, createButtomXpath).getAttribute("disabled").equals("true")) {
				report.updateTestLog("Verify user(Consumer) not able to view Create Button",
						"Create Button is disabled for Consumer role in document library", Status.PASS);
			} else {
				report.updateTestLog("Verify user(Consumer) not able to view Create Button",
						"Create Button is not disabled for Consumer role in document library", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify user(Consumer) not able to view Create Button",
					"Create Button is not disabled for Consumer role in document library", Status.FAIL);
			e.printStackTrace();
		}
	}

	public void verifySuccessTrancodeConfirmation() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, errorTranscodeXpath)
					|| UIHelper.checkForAnElementbyXpath(driver, sourceTrancodeFailXpath)) {
				report.updateTestLog("Verify file is Trancoded", "import file is failed to Transcode", Status.FAIL);
			} else {
				report.updateTestLog("Verify file is Trancoded", "import file is successfully Transcoded", Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify file is Trancoded", "import file is successfully Transcoded", Status.PASS);
		}
	}

	public void deleteAudioInStreamingPageAndVerifyStreamingContentInStreamingFolder() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFilesTabXpath);
			UIHelper.highlightElement(driver, uploadFilesTabXpath);
			UIHelper.click(driver, uploadFilesTabXpath);

			UIHelper.highlightElement(driver, deleteVideoInUploadFilesTabXpath);
			UIHelper.click(driver, deleteVideoInUploadFilesTabXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteVideoInUploadFilesPromptBoxXpath);
			UIHelper.highlightElement(driver, deleteVideoInUploadFilesPromptBoxXpath);

			UIHelper.click(driver, yesButtonXpathForDeleteVideo);

			UIHelper.waitForVisibilityOfEleByXpath(driver, PopuptitleXpath);
			UIHelper.highlightElement(driver, PopuptitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, PopuptitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteSuccessMessage);
			UIHelper.click(driver, deleteSuccessMessageOkButton);

			report.updateTestLog("Delete Audio on upload files Tab ", "Success", Status.PASS);

			clickOnCancle();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {

			e.printStackTrace();
			report.updateTestLog("Delete Audio on upload files Tab ", "Exception", Status.FAIL);
		}

	}

	public void verifyAudioFileNotAvailableInUploadTab() {
		try {
			UIHelper.highlightElement(driver, selectedFileXpath);
			if (UIHelper.findAnElementbyXpath(driver, selectedFileXpath).getText().trim().equals("")) {
				report.updateTestLog("Verify Audio File is Deleted", "Audio File is Successfully Deleted", Status.PASS);
			} else {
				report.updateTestLog("Verify Audio File is Deleted", "Audio File is not Deleted", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Audio File is Deleted", "Audio File is not Deleted", Status.FAIL);
		}
	}

	public void verifyPreviewTabNegative() {
		try {
			if (!UIHelper.checkForAnElementbyXpath(driver, priveTabXpath))

			{
				report.updateTestLog("Verify Preview Tab", "Preview Tab should not be displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify Preview Tab", "Preview Tab is displayed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Preview Tab", "Preview Tab is displayed", Status.FAIL);
			e.printStackTrace();
		}
	}
	public void verifyRelatedTabForAudioFiles()
	{
		try
		{
		String cssDisabledcolor = UIHelper.findAnElementbyXpath(driver, relatedFilesTabXpath).getCssValue("color");
		String hex = Color.fromString(cssDisabledcolor).asHex();
		
		if (hex.equals("#a7a7a7")) {
			report.updateTestLog("Verify related tab is disabled ",
					"Related file tab is disabled for audio", Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify related tab is disabled ",
					"Related file tab is not disabled for audio", Status.FAIL);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			report.updateTestLog("Verify related tab is disabled ",
					"Related file tab is not disabled for audio", Status.FAIL);
		}
	}

}
