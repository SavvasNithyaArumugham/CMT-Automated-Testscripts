package com.pearson.automation.alfresco.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoMyFilesPage extends ReusableLibrary {

	private String commonPageTitleXpath = ".//*[@id='HEADER_TITLE']";
	//private String uploadBtnXpath = ".//*[@class='file-upload']//span//button[contains(.,'Upload')]";
	private String uploadBtnXpath = ".//*[@class='file-upload']//span//button";
	//private String selctFilesToUploadBtnXpath = "//*[contains(@id,'default-file-selection-button-overlay')]/span//*[contains(.,'Select files to upload')]";
	private String selctFilesToUploadBtnXpath ="//*[contains(@id,'default-file-selection-button-overlay')]/span//button[@type='button']";
	
	private String myFilesTablesXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']";
	private String myFilesTableContainerXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']//table[contains(@id,'yuievtautoid')]";
	private String myFilesLableLinkXpath = ".//*[contains(@id,'yui-gen')]//a[contains(.,'Documents')] | .//*[contains(@id,'yui-gen')]//a[contains(.,'Files')]";
	private String uploadedFilesTitlesXpath = "//*[@class='yui-dt-data']//h3/span/a";
	
	private String CoursesTitlesXpath = "//*[@class='yui-dt-liner']//h3/span"; //added by 211311

//	public String createLinkXpath = ".//*[@id='bd']//span[contains(@id,'createContent-button')]//span/*[contains(.,'Create')]";
	public String createLinkXpath = ".//*[@id='bd']//span[contains(@id,'createContent-button')]//span/button";
	private String createContentContainerXpath = ".//*[contains(@id,'default-createContent-menu')]/*[@class='bd']";
	//private String folderLinkXpath = ".//*[contains(@id,'yui-gen')]/a/span[text()='Folder']";
	private String folderLinkXpath = ".//div[contains(@id,'default-createContent-menu')]/div[@class='bd']/ul[@class='first-of-type']/li[1]"; 
	public String disablecreate = ".//div[@class='create-content']//button";
	private String createMenuItemsXpath = ".//*[contains(@id,'default-createContent-menu')]//*[contains(@id,'yui-gen')]/a[@class='yuimenuitemlabel']/span";

	private String folderNameFieldXpath = ".//*[contains(@id,'prop_cm_name')]";
	private String titleXpath = ".//*[contains(@id,'prop_cm_title')]";
	private String descriptionXpath = ".//*[contains(@id,'prop_cm_description')]";
	private String saveButton = ".//*[contains(@id,'form-submit-button')]";

	private String deleteFolderXpath = ".//*[@id='Share']//*[@id='prompt']//span[contains(@id,'yui-gen')]/span[contains(.,'Delete')]";
	private String messageXpath = ".//*[@id='message']/div";
	private String tempSelectFolderChkboxXpath = ".//*[contains(@class,'yui-dt-data')]//a[text()='CRAFT']//ancestor::tr//td[1]//input";

	private String selectedItemsXpath = ".//*[@id='yui-main']//*[@id='alf-content']//span/span[contains(.,'Selected Items')]";
	private String deleteXpath = ".//*[contains(@id,'yui-gen')]/a/span[contains(.,'Delete')]";
	private String openedDocHeaderXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//h1";
	private String tempFolderChkboxXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::tr//td[1]//input";

	private String listOfFilesSegmentXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']";
	private String uploadedFileOrFolderTitleXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td//h3/span/a";
	private String documentDetailsTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String lodingXpathForDocLib = ".//*[@class='yui-dt-message']/tr/td/*[contains(.,'Loading the Document Library')]";
	private String loadingImage = ".//*[@id='overlay']/a/*[@id='loadingImage']";
	public String openedDocumentContainerLayerXpath = ".//*[@class='textLayer']";

	private String olderVersionsNumberXpath = ".//*[@class='sticky-wrapper']//*[contains(@class,'yui-')]//*[contains(@class,'alfresco-datatable')]//table[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td/div[1]/div[1]/span";
	private String versionListSegmentXpath = ".//*[contains(@id,'template_x002e_document-versions_x002e_document-details_x0023_default-body')]";

	private String checkedInPersonNameXpath = ".//*[@class='sticky-wrapper']//*[contains(@class,'yui-')]//*[contains(@class,'alfresco-datatable')]//table[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td/div[1]/div[2]/div[2]/div[2]/a";
	private String checkedInDateXpath = ".//*[@class='sticky-wrapper']//*[contains(@class,'yui-')]//*[contains(@class,'alfresco-datatable')]//table[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td/div[1]/div[2]/div[2]/div[2]/span";
	private String lastVersionNumberXpath = ".//*[@id='document-version']";

	private String tempXpathForMoreOptionLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]//following-sibling::td[1]//*[@id='onActionShowMore']//a[contains(.,'More')]/span";
	private String tempXpathForDeleteFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]//following-sibling::td[1]//*[@id='onActionShowMore']//a[contains(.,'More')]//ancestor::div[1]//following-sibling::div//a/span[contains(.,'Delete Folder')]";
	private String tempXpathForMoveToFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]//following-sibling::td[1]//*[@id='onActionShowMore']//a[contains(.,'More')]//ancestor::div[1]//following-sibling::div//a/span[contains(.,'Move to')]";

	private String selectWFXpath = ".//*[@class='onActionAssignWorkflow']";
	private String filenameXpath = ".//*[@class='filename']//a";
	public ArrayList<String> fileFolderNames = new ArrayList<String>();

	private String slctCatXPath = ".//div[contains(@id,'categories-cntrl-itemGroupActions')]//button[contains(text(),'Select')]";
	private String firstCatgryAddXPath = ".//div[contains(@id,'categories-cntrl-picker-left')]//tbody[@class='yui-dt-data']/tr[1]//td[3]//a";
	private String okBtnaddCategryXPath = ".//*[@class='bdft']//button[text()='OK']";
	private String saveBtnAddCatXPath = ".//button[text()='Save']";
	private String categoryAddedVerifyXPath = ".//div[contains(@id,'categories-cntrl-currentValueDisplay')]//div";
	private boolean isCtgryDisplydFlag;
	private ArrayList<String> filesNamesList = new ArrayList<String>();
	private String edtPrpLinkXPath = ".//span[contains(.,'Edit Properties')]";
	private String catgryLblXPath = ".//*/label[contains(.,'Categories')]";
	private String cntrlRightBoxXPath = ".//div[contains(@id,'categories-cntrl-picker-right')]";

	private String firstCatgryRemvXPath = ".//div[contains(@id,'categories-cntrl-picker-right')]//tbody[@class='yui-dt-data']/tr[1]//td[3]//a/span";

	private String createFileLinkXpath = ".//*[contains(@id,'yui-gen')]/a/span[contains(.,'CRAFT')]";
	private String fileNameFieldXpath = ".//*[contains(@id,'default_prop_cm_name')]";
	private String fileContentFieldXpath = ".//*[contains(@id,'default_prop_cm_content')]//textarea";
	private String fileTitleFieldXpath = ".//*[contains(@id,'default_prop_cm_title')]";
	private String fileDescFieldXpath = ".//*[contains(@id,'default_prop_cm_description')]";
	private String createBtnXpath = ".//*[contains(@id,'default-form-submit-button')]";
	private String myFilesFolderLinkXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//a[text()='CRAFT']";
	private String lockedFilesListXpath = ".//*[@class='yui-dt-data']//*[contains(@class,'info-banner') and contains(.,'locked')]//following-sibling::h3/span/a";
	private String tempXpathForLocekdFile = ".//*[@class='yui-dt-data']//*[contains(@class,'info-banner') and contains(.,'locked')]//following-sibling::h3/span/a[text()='CRAFT']";

	private String cancelEditingXpath = "//a/span[text()='Cancel Editing']";
	private String selectLinkToXpath = ".//*[@class='onActionLinkTo']";

	private String pageBodyXpath = ".//*[@id='bd']";

	private String selectdItemsLstXPath = ".//*[@id='yui-main']//*[@id='alf-content']//div[contains(@class,'selected-items')]/div//ul/li/a";

	private String selectdItemsXPath = ".//button[contains(@id,'selectedItems')]";
	private String linkToDocActXPath = ".//*[@id='onActionLinkTo']/a/span";
	private String checkBoxXpath = "//*[@name='fileChecked']";

	private String tempXpathForAttachLifecycleFolderLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]//following-sibling::td[1]//*[@id='onActionShowMore']//a[contains(.,'More')]//ancestor::div[1]//following-sibling::div//a/span[contains(.,'Attach Lifecycle')]";

	private String edtPropXPath = ".//*[@class='doclist']//a/span[text()='Edit Properties']";

	private String selectAnItemsButtonXpath = ".//*[contains(@id,'default-selectedItems-button-button')]";
	private String downloadZipXpath = ".//*[contains(@id,'default-selectedItems-menu')]//*[contains(@id,'yui-gen')]/a/span[contains(.,'Download as Zip')]";
	private String searchResultsListXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']//h3//a";
	protected String tempFileXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::tr";

	private String tempXpathForBrowseAction = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]//following-sibling::td[1]//a[contains(.,'BROWSE_OPTION')]/span";
	private String selectedFolderORFileItemXpath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]";

	private String uploadInputField = ".//*[contains(@id,'default-file-selection-button-overlay')]/span/input";
	private String okBtnXpathInUploadPopup = ".//*[contains(@style,'visible')]//*[@class='bd']//*[@class='first-child']//button[text()='OK']";
	private String tempXpathForUploadedFile = ".//*[contains(@id,'default-documents')]//*[@class='yui-dt-data']//h3//*[text()='CRAFT']";

	private String htmlFontXpath = ".//*[@id='mceu_2']/button";
	private String htmlImageXpath = ".//*[@id='mceu_14']/button";
	private String htmlBodyXpath = ".//*[@id='mceu_18']//*[@id='mceu_18-body']//div[3]//iframe";

	private String tempXpathForCreateFileFieldLabel = ".//*[contains(@id,'default-form-fields')]//*[@class='form-field']/label[contains(.,'CRAFT')]";

	// Web resource
	public String commoncreatexpath = ".//*[@class='create-content']//li//span[text()='CRAFT']";
	public String webresourcename = ".//*[@name='prop_cm_name']";
	public String webresourceURL = ".//*[@name='prop_wrm_webResourceUrl']";
	private String webresourcefailure = ".//*[@id='prompt_h']";
	private String webresfailurebutton = ".//*[@id='prompt']//button";
	public String webthumbnail = ".//*[@class='yui-dt-data']//a[text()='CRAFT']//ancestor::tr//*[@class='thumbnail']//img";
	public String webthumbnailcheck = ".//*[@class='yui-dt-data']//a[text()='CRAFT']//ancestor::tr//*[@class='thumbnail']";
	public String webeditsave = ".//*[contains(@id,'submit-button')]";

	private String renameListXapth = "//*[@class='yui-dt-data']//h3//span[@class='insitu-edit']";
	private String renameTextXpath = ".//*[@name='prop_cm_name']";
	private String renameSaveButton = ".//a[text()='Save']";
	private String renameCancelButton = ".//a[text()='Cancel']";
	private String renameMsgPopUpXapth = ".//div[text()='Could not rename file. Another file may exist with the same name.']";

	// Web resource
	private String webReSrcXpath = ".//*[contains(@id,'yui-gen')]/a/span[text()='Web Resource']";
	private String webReSrcNameFieldXpath = ".//*[contains(@id,'default_prop_cm_name')]";
	private String webReSrcURLFieldXpath = ".//*[@name='prop_wrm_webResourceUrl']";
	private String webReSrcSaveXpath = ".//*[contains(@id,'default-form-submit-button')]";
	private String invalidURLWarningXpath = ".//*[@class='balloon']/div[contains(.,'Invalid')]";

	private String pagesXpath = ".//*[@class='yui-pg-pages']//a[contains(@class,'yui-pg-page')]";
	private String nextPageXpath = ".//*[@title='Next Page']";
	private String waitXpath = ".//*[@class='wait']";

	private String docActionTwisterCloseXpath = ".//*[contains(@class,'document-actions document-details-panel') or contains(@class,'folder-actions folder-details-panel')]/*[contains(@class,'alfresco-twister-closed')]";
	private String docActionTwisterOpenXpath = ".//*[contains(@class,'document-actions document-details-panel') or contains(@class,'folder-actions folder-details-panel')]/*[contains(@class,'alfresco-twister-open')]";

	private String editPropertiesFormXpath = ".//*[@class='form-fields']";
	private String editPropertiesLabelListXpath = ".//*[@class='form-fields']//label";
	ArrayList<String> editpropertiesList = new ArrayList<String>();
	ArrayList<String> editpropertiesExpectedList = new ArrayList<String>();
	private String cancelEditPropBtnXpath = ".//*[contains(@id,'form-cancel-button')]";

	private String docLibXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']/a";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoMyFilesPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		/*
		 * if(!driver.getTitle().contains("My Files")) { throw new
		 * IllegalStateException("My Files page expected, but not displayed!");
		 * }
		 */
	}

	// Upload a file in My Files Page from C drive upload path
	public AlfrescoMyFilesPage uploadFileInMyFiles(String fileName) {
		try {
			String uploadPath = Settings.getInstance().getProperty("DefaultUploadPath");

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					commonMethodForUploadMultipleFiles(uploadPath, fileNameVal);
				}
			} else {
				commonMethodForUploadMultipleFiles(uploadPath, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Upload a file in My Files Page
	public AlfrescoMyFilesPage uploadFileInMyFilesPage(String filePath, String fileName) {
		try {
		String finalFilePath;
		if (filePath.contains("Automation/Alfresco")) {
		finalFilePath = filePath;
		}
		else if (filePath.contains("Automation/Alfresco")) {
		finalFilePath = filePath;
		} 
		else {
		finalFilePath = System.getProperty("user.dir") + filePath;
		}
		if (fileName.contains(",")) {
		String splittedFileNames[] = fileName.split(",");
		for (String fileNameVal : splittedFileNames) {
		commonMethodForUploadMultipleFiles(finalFilePath, fileNameVal);
		}
		} else {
		commonMethodForUploadMultipleFiles(finalFilePath, fileName);
		}

		} catch (Exception e) {
		e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
		}

	// Upload a Large file in My Files Page
	public AlfrescoMyFilesPage uploadLargeFiles(String filePath, String fileName) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			}
			else {
				finalFilePath = System.getProperty("user.dir") + filePath;
			}
			// String finalFilePath = System.getProperty("user.dir") + filePath;
			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {

					commonMethodForUploadLargeFile(finalFilePath, fileNameVal);
				}
			} else {
				commonMethodForUploadLargeFile(finalFilePath, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public void commonMethodForUploadMultipleFiles(String filePath, String fileName) {
		try {
			
			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			WebElement uploadBtnEle = driver.findElement(By.xpath(uploadBtnXpath));

			if (UIHelper.checkForAnWebElement(uploadBtnEle)) {
				
				UIHelper.highlightElement(driver, uploadBtnEle);
				
				driver.findElement(By.xpath(uploadBtnXpath)).click();
				
				UIHelper.waitForVisibilityOfEleByXpath(driver, selctFilesToUploadBtnXpath);
				
				WebElement uploadInputFieldEle = driver.findElement(By.xpath(selctFilesToUploadBtnXpath));
				
				UIHelper.highlightElement(driver, uploadInputFieldEle);
				
				WebElement fileInput = driver.findElement(By.xpath(uploadInputField));
				
				fileInput.sendKeys(filePath + fileName);
				
				/*
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				System.out.println("14");
				UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
				System.out.println("15");
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				System.out.println("16");
				UIHelper.waitForPageToLoad(driver);
				System.out.println("17");
				UIHelper.waitFor(driver);
				System.out.println("18");*/
				report.updateTestLog("Upload File",
						"Upload file using -" + "<b>FilePath:</b>" + filePath + ", " + "<b>FileName:</b>" + fileName,
						Status.DONE);
				System.out.println("19");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Common method for uplaod large file
	public void commonMethodForUploadLargeFile(String filePath, String fileName) {
		try {
			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			WebElement uploadBtnEle = driver.findElement(By.xpath(uploadBtnXpath));

			if (UIHelper.checkForAnWebElement(uploadBtnEle)) {
				UIHelper.highlightElement(driver, uploadBtnEle);
				driver.findElement(By.xpath(uploadBtnXpath)).click();
				UIHelper.waitForVisibilityOfEleByXpath(driver, selctFilesToUploadBtnXpath);
				WebElement uploadInputFieldEle = driver.findElement(By.xpath(selctFilesToUploadBtnXpath));
				UIHelper.highlightElement(driver, uploadInputFieldEle);

				WebElement fileInput = driver.findElement(By.xpath(uploadInputField));
				fileInput.sendKeys(filePath + fileName);

				/*
				 * try{
				 * UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
				 * okBtnXpathInUploadPopup); } catch(Exception e) {
				 * e.printStackTrace(); }
				 * 
				 * if(UIHelper.checkForAnElementbyXpath(driver,
				 * okBtnXpathInUploadPopup)) { UIHelper.click(driver,
				 * okBtnXpathInUploadPopup); }
				 */

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Upload File",
						"Upload file using -" + "<b>FilePath:</b>" + filePath + ", " + "<b>FileName:</b>" + fileName,
						Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Upload a file in My Files Page
	public AlfrescoMyFilesPage uploadFile(String filePath, String fileName) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation\\Alfresco")) {
				finalFilePath = filePath;
			}
			else if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			} 
			else {
				finalFilePath = System.getProperty("user.dir") + filePath;
			}

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					commonMethodForUploadFiles(finalFilePath, fileNameVal);
				}
			} else {
				commonMethodForUploadFiles(finalFilePath, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public void commonMethodForUploadFiles(String filePath, String fileName) {
		try {
			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			WebElement uploadBtnEle = driver.findElement(By.xpath(uploadBtnXpath));

			if (UIHelper.checkForAnWebElement(uploadBtnEle)) {
				UIHelper.highlightElement(driver, uploadBtnEle);
				driver.findElement(By.xpath(uploadBtnXpath)).click();
				UIHelper.waitForVisibilityOfEleByXpath(driver, selctFilesToUploadBtnXpath);
				WebElement uploadInputFieldEle = driver.findElement(By.xpath(selctFilesToUploadBtnXpath));
				UIHelper.highlightElement(driver, uploadInputFieldEle);

				WebElement fileInput = driver.findElement(By.xpath(uploadInputField));
				fileInput.sendKeys(filePath + fileName);

				/*
				 * try{
				 * UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
				 * okBtnXpathInUploadPopup); } catch(Exception e) {
				 * e.printStackTrace(); }
				 * 
				 * if(UIHelper.checkForAnElementbyXpath(driver,
				 * okBtnXpathInUploadPopup)) { UIHelper.click(driver,
				 * okBtnXpathInUploadPopup); }
				 */

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Upload File",
						"Upload file using -" + "<b>FilePath:</b>" + filePath + ", " + "<b>FileName:</b>" + fileName,
						Status.DONE);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get uploaded file/folder name from My Files Page
	public ArrayList<String> getUploadedFileOrFolderTitle() {

		ArrayList<String> uploadedFilesNameList = new ArrayList<String>();

		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);
			 * 
			 * WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver,
			 * pageBodyXpath); UIHelper.highlightElement(driver, pageBodyEle);
			 * UIHelper.mouseOveranElement(driver, pageBodyEle);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * myFilesTablesXpath); WebElement myFilesTableEle =
			 * driver.findElement(By .xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadedFilesTitlesXpath);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				uploadedFilesNameList.add(ele.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadedFilesNameList;
	}

	// Get File Or Folder full name from Document Library Page
	public String getFileOrFolderNameFromDocLib(String partialFileName) {
		String fullFileOrFolderName = "";
		try {
			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				String actualFileOrFolderName = ele.getText().trim();
				if (actualFileOrFolderName.contains(partialFileName)) {
					fullFileOrFolderName = actualFileOrFolderName;
					break;
				} else {
					fullFileOrFolderName = "File Not Found";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fullFileOrFolderName;
	}

	// Release lock on file
	public void releaseLockOnFile(String folderName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			/*
			 * WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */
			UIHelper.waitFor(driver);
			List<WebElement> uploadedFileOrFolderTitleEleList = driver.findElements(By.xpath(lockedFilesListXpath));
			if (uploadedFileOrFolderTitleEleList != null && uploadedFileOrFolderTitleEleList.size() > 0) {
				for (WebElement ele : uploadedFileOrFolderTitleEleList) {
					UIHelper.highlightElement(driver, ele);
					String lockedFileName = ele.getText().trim();
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", ele);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
					try {
						UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocumentContainerLayerXpath);
					} catch (Exception e) {
					}

					UIHelper.waitFor(driver);

					if (UIHelper.checkForAnElementbyXpath(driver, cancelEditingXpath)) {
						UIHelper.click(driver, cancelEditingXpath);
						UIHelper.waitFor(driver);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
						UIHelper.waitFor(driver);

						report.updateTestLog("Rlease the lock on File",
								"User release the lock successfully on File: " + lockedFileName, Status.DONE);

						String finalXpathForMyFileFolder = myFilesFolderLinkXpath.replace("CRAFT", folderName);

						try {
							UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMyFileFolder);
						} catch (Exception e) {
						}

						UIHelper.click(driver, finalXpathForMyFileFolder);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);
					}

				}
			}

		} catch (StaleElementReferenceException e) {
			releaseLockOnFile(folderName);
		}
	}

	// Navigate to My Files section
	public AlfrescoMyFilesPage navigateToMyFilesContainer() {
		try {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesLableLinkXpath);
			} catch (Exception e) {
			}

			UIHelper.click(driver, myFilesLableLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			} catch (Exception e) {
			}
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Create Folder
	public AlfrescoMyFilesPage createFolder(String folderDetails) {
		try {
			if (folderDetails.contains(";")) {
				String splittedFolderDetailas[] = folderDetails.split(";");
				for (String folderValues : splittedFolderDetailas) {
					String splittedFolderValues[] = folderValues.split(",");

					String folderName = splittedFolderValues[0].replace("FolderName:", "");
					String folderTitle = splittedFolderValues[1].replace("Title:", "");
					String folderDescription = splittedFolderValues[2].replace("Description:", "");

					commonMethodForCreateFolder(folderName, folderTitle, folderDescription);
				}
			} else {
				String splittedFolderValues[] = folderDetails.split(",");

				String folderName = splittedFolderValues[0].replace("FolderName:", "");
				String folderTitle = splittedFolderValues[1].replace("Title:", "");
				String folderDescription = splittedFolderValues[2].replace("Description:", "");

				commonMethodForCreateFolder(folderName, folderTitle, folderDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Create Plain Text File
	public AlfrescoMyFilesPage createFile(String fileDetails) {
		try {
			if (fileDetails.contains(";")) {
				String splittedFileDetailas[] = fileDetails.split(";");
				for (String fileValues : splittedFileDetailas) {
					String splittedFileValues[] = fileValues.split(",");

					String fileType = splittedFileValues[0].replace("FileType:", "");

					String fileName = splittedFileValues[1].replace("FileName:", "");
					String fileContent = splittedFileValues[2].replace("FileContent:", "");
					String fileTitle = splittedFileValues[3].replace("FileTitle:", "");
					String fileDescription = splittedFileValues[4].replace("FileDesc:", "");

					commonMethodForCreateFile(fileType, fileName, fileContent, fileTitle, fileDescription);

					backToFolderOrDocumentPage();
				}
			} else {

				String splittedFileDetailas[] = fileDetails.split(",");

				String fileType = splittedFileDetailas[0].replace("FileType:", "");

				String fileName = splittedFileDetailas[1].replace("FileName:", "");
				String fileContent = splittedFileDetailas[2].replace("FileContent:", "");
				String fileTitle = splittedFileDetailas[3].replace("FileTitle:", "");
				String fileDescription = splittedFileDetailas[4].replace("FileDesc:", "");

				commonMethodForCreateFile(fileType, fileName, fileContent, fileTitle, fileDescription);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public void backToFolderOrDocumentPage() {
		try {

			String finalXpathForMyFileFolder = myFilesFolderLinkXpath.replace("CRAFT", "Documents");

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMyFileFolder);
			UIHelper.click(driver, finalXpathForMyFileFolder);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void backToFolderOrDocumentPage(String folderOrFileName) {
		try {

			String finalXpathForMyFileFolder = myFilesFolderLinkXpath.replace("CRAFT", folderOrFileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForMyFileFolder);
			UIHelper.click(driver, finalXpathForMyFileFolder);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on 'Breadcumb Link",
					"Clicked the breadcumb link using Folder:" + folderOrFileName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getFolderNames(String folderDetails) {
		ArrayList<String> folderNamesList = new ArrayList<String>();
		try {

			if (folderDetails.contains(";")) {
				String splittedFolderDetailas[] = folderDetails.split(";");
				for (String folderValues : splittedFolderDetailas) {
					if (folderValues.contains(",")) {
						String splittedFolderValues[] = folderValues.split(",");

						String folderName = splittedFolderValues[0].replace("FolderName:", "");
						folderNamesList.add(folderName);
					} else {
						folderNamesList.add(folderValues);
					}
				}
			} else {
				if (folderDetails.contains(",")) {
					String splittedFolderValues[] = folderDetails.split(",");

					String folderName = splittedFolderValues[0].replace("FolderName:", "");
					folderNamesList.add(folderName);
				} else {
					folderNamesList.add(folderDetails);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return folderNamesList;
	}

	// Click on Create Button
	public void clickOnCreateButton() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			UIHelper.click(driver, createLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createContentContainerXpath);
			WebElement createDailogEle = UIHelper.findAnElementbyXpath(driver, createContentContainerXpath);
			UIHelper.highlightElement(driver, createDailogEle);
			UIHelper.mouseOveranElement(driver, createDailogEle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Create Menu Items
	public ArrayList<String> getCreateMenuItems() {
		ArrayList<String> createMenuItemsList = new ArrayList<String>();
		try {
			List<WebElement> createMenuItemsEle = UIHelper.findListOfElementsbyXpath(createMenuItemsXpath, driver);

			for (WebElement ele : createMenuItemsEle) {
				createMenuItemsList.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createMenuItemsList;
	}

	// Create Folder
	public void commonMethodForCreateFolder(String folderName, String folderTitle, String folderDescription) {
		try {
			clickOnCreateButton();
			UIHelper.waitForVisibilityOfEleByXpath(driver, folderLinkXpath);
			WebElement folderLinkEle = UIHelper.findAnElementbyXpath(driver, folderLinkXpath);
			UIHelper.highlightElement(driver, folderLinkEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", folderLinkEle);
			UIHelper.click(driver, folderLinkXpath);
			enterDataForCreateObject(folderName, folderTitle, folderDescription);
			clickOnSaveBtnForSubmitCreateObjectData();

			report.updateTestLog("Create Folder",
					"Create Folder using: " + "<br/><b>FolderName:</b>" + folderName + ", " + "<br/><b>FolderTitle:</b>"
							+ folderTitle + ", " + "<br/><b>FolderDescription:</b>" + folderDescription,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Enter Data in fields
	public void enterDataForCreateObject(String name, String title, String description) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, folderNameFieldXpath);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, folderNameFieldXpath, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath, description);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Save Button
	public void clickOnSaveBtnForSubmitCreateObjectData() {
		try {
			UIHelper.click(driver, saveButton);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commonMethodForCreateFile(String fileType, String fileName, String fileContent, String fileTitle,
			String fileDescription) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			UIHelper.click(driver, createLinkXpath);

			String finalXpathForCreateFile = createFileLinkXpath.replace("CRAFT", fileType);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCreateFile);
			UIHelper.click(driver, finalXpathForCreateFile);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, fileNameFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, fileNameFieldXpath, fileName);

			UIHelper.click(driver, fileContentFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, fileContentFieldXpath, fileContent);
			UIHelper.sendKeysToAnElementByXpath(driver, fileTitleFieldXpath, fileTitle);
			UIHelper.sendKeysToAnElementByXpath(driver, fileDescFieldXpath, fileDescription);
			UIHelper.click(driver, createBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Create File",
					"Created File using: " + " <br /><b>FileName : </b>" + fileName + "<br /><b>FileContent : </b>"
							+ fileContent + " <br /><b>FileTitle : </b>" + fileTitle
							+ " <br /><b> FolderDescription : </b>" + fileDescription,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Create Dropdown Items
	public void commonMethodForClickOnCreateDropdownItems(String fileDetails) {
		try {
			if (fileDetails.contains(";")) {
				String splittedFileDetailas[] = fileDetails.split(";");
				for (String fileValues : splittedFileDetailas) {
					String splittedFileValues[] = fileValues.split(",");

					String fileType = splittedFileValues[0].replace("FileType:", "");

					createFileFromCreateDropDown(fileType);
				}
			} else {

				String splittedFileDetailas[] = fileDetails.split(",");

				String fileType = splittedFileDetailas[0].replace("FileType:", "");

				createFileFromCreateDropDown(fileType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create File from Create Dropdown
	public void createFileFromCreateDropDown(String fileType) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			UIHelper.click(driver, createLinkXpath);

			String finalXpathForCreateFile = createFileLinkXpath.replace("CRAFT", fileType);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCreateFile);
			UIHelper.click(driver, finalXpathForCreateFile);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, fileNameFieldXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void openFileCreator(String fileType) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			UIHelper.click(driver, createLinkXpath);

			String finalXpathForCreateFile = createFileLinkXpath.replace("CRAFT", fileType);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCreateFile);
			UIHelper.click(driver, finalXpathForCreateFile);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, fileNameFieldXpath);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, fileNameFieldXpath))) {
				report.updateTestLog("Open " + fileType + " File Creator", "Opened successfully", Status.DONE);
			} else {
				report.updateTestLog("Open " + fileType + " File Creator", "Open Failed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Open " + fileType + " File Creator", "Open " + fileType + " File Creator Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isHTMLComponentsAvailable() {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, htmlBodyXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, htmlBodyXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, htmlFontXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, htmlImageXpath))) {
				UIHelper.highlightElement(driver, htmlBodyXpath);
				UIHelper.highlightElement(driver, htmlFontXpath);
				UIHelper.highlightElement(driver, htmlImageXpath);
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
	 * @param folderName
	 * @return
	 */
	public AlfrescoMyFilesPage openCreatedFolder(String folderName) {
		try {
			boolean isOpenedFolder = false;
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);

			/*
			 * WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver,
			 * pageBodyXpath); UIHelper.highlightElement(driver, pageBodyEle);
			 * UIHelper.mouseOveranElement(driver, pageBodyEle);
			 */

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * myFilesTablesXpath); WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(folderName)) {
					ele.click();
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
					UIHelper.waitFor(driver);
					isOpenedFolder = true;
					break;
				} else {
					isOpenedFolder = false;
				}
			}
			UIHelper.waitFor(driver);

			if (isOpenedFolder) {
				report.updateTestLog("Open a Folder", "Folder opened" + "<br /><b>Folder Name : </b>" + folderName,
						Status.PASS);
			} else {
				report.updateTestLog("Open a Folder",
						"Folder failed to open" + "<br /><b>Folder Name : </b>" + folderName, Status.FAIL);
			}

		} catch (Exception e) {
			System.out.println("error"+e.getMessage());
			
		
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Open Folder
	public AlfrescoMyFilesPage openFolder(String folderName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
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
				if (ele.getText().equalsIgnoreCase(folderName)) {
					ele.click();
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
					break;
				}
			}
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on Folder", "Click on Folder using: " + "FolderName = " + folderName,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public AlfrescoMyFilesPage releaseLockOnFilesUnderFolder(String folderName) {
		try {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			} catch (Exception e) {
			}

			/*
			 * WebElement myFilesTableEle =
			 * driver.findElement(By.xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(folderName)) {
					ele.click();
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);

					releaseLockOnFile(folderName);

					navigateToMyFilesContainer();

					break;
				}
			}
			UIHelper.waitFor(driver);

			report.updateTestLog("Rlease the lock on Folder",
					"Release the lock on Folder using: " + "FolderName = " + folderName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Delete Folder
	public AlfrescoMyFilesPage deleteCreatedFolder(String folderDetails) {
		try {

			if (folderDetails.contains(";")) {
				String splittedFolderDetailas[] = folderDetails.split(";");
				for (String folderValues : splittedFolderDetailas) {
					String splittedFolderValues[] = folderValues.split(",");

					String folderName = splittedFolderValues[0].replace("FolderName:", "");

					commonMethodFordeleteFolder(folderName);
				}
			} else {
				if (folderDetails.contains(",")) {
					String splittedFolderValues[] = folderDetails.split(",");

					String folderName = splittedFolderValues[0].replace("FolderName:", "");

					commonMethodFordeleteFolder(folderName);
				} else {
					String folderName = folderDetails.replace("FolderName:", "");

					commonMethodFordeleteFolder(folderName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public AlfrescoMyFilesPage commonMethodFordeleteFolder(String folderName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", folderName);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
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
				if (ele.getText().equalsIgnoreCase(folderName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsXpath);
					UIHelper.click(driver, selectedItemsXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, deleteXpath);
					UIHelper.click(driver, deleteXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, deleteFolderXpath);
					UIHelper.click(driver, deleteFolderXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
					report.updateTestLog("Delete a Folder", "Deleted folder: <br/><b>FolderName:</b>" + folderName,
							Status.DONE);

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// To delete Multiple Files
	public AlfrescoMyFilesPage deleteUploadedFile(String fileNames) {
		try {

			if (fileNames.contains(",")) {
				String splittedFileName[] = fileNames.split(",");
				for (String fileName : splittedFileName) {

					commonMethodToDeleteFile(fileName);
				}
			} else {
				commonMethodToDeleteFile(fileNames);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// To delete Single file
	public AlfrescoMyFilesPage commonMethodToDeleteFile(String fileName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileName);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
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
				if (ele.getText().equalsIgnoreCase(fileName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);

					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsXpath);
					UIHelper.click(driver, selectedItemsXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, deleteXpath);
					UIHelper.click(driver, deleteXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, deleteFolderXpath);
					UIHelper.click(driver, deleteFolderXpath);
					report.updateTestLog("Delete a File", "Delete file using " + "<br/><b>FileName:</b>" + fileName,
							Status.PASS);
					UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);

					UIHelper.waitFor(driver);

					

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// To select Multiple Files
	public AlfrescoMyFilesPage selectMultipleFilesOrFolders(String fileNames) {
		try {

			if (fileNames.contains(",")) {
				String splittedFileName[] = fileNames.split(",");
				for (String fileName : splittedFileName) {

					commonMethodToSelectFile(fileName);
				}
			} else {

				commonMethodToSelectFile(fileNames);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// To select a single file
	public AlfrescoMyFilesPage commonMethodToSelectFile(String fileName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileName);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
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
				if (ele.getText().equalsIgnoreCase(fileName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					break;
				}
			}

			report.updateTestLog("Select a File", "Selected file using " + "<br/><b>FileName:</b>" + fileName,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public AlfrescoMyFilesPage openUploadedOrCreatedFile(String fileName, String fileAppendVal) {
		try {
			String finalFileName;
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0] + fileAppendVal;
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, lodingXpathForDocLib);

			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
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
				String atFileName = ele.getText();
				if (finalFileName.equalsIgnoreCase(atFileName)) {
					UIHelper.highlightElement(driver, ele);
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForPageToLoad(driver);
					/*
					 * UIHelper.waitForVisibilityOfEleByXpath(driver,
					 * loadingImage);
					 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					 * loadingImage);
					 */
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
					/*
					 * UIHelper.waitForVisibilityOfEleByXpath(driver,
					 * openedDocumentContainerLayerXpath);
					 */
					Thread.sleep(2000);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);

					if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
						UIHelper.click(driver, docActionTwisterCloseXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
						UIHelper.waitFor(driver);
					}

					report.updateTestLog("Open a File", "File opened using " + "<br/><b>FileName:</b>" + finalFileName,
							Status.DONE);

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public AlfrescoDocumentDetailsPage selectFileToUpdateInMyFilesPage(String fileToUpdate) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, listOfFilesSegmentXpath);
			WebElement listOfFilesSegmentWebElement = UIHelper.findAnElementbyXpath(driver, listOfFilesSegmentXpath);
			UIHelper.mouseOverandclickanElement(driver, listOfFilesSegmentWebElement);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFileOrFolderTitleXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileToUpdate)) {
					UIHelper.mouseOveranElement(driver, ele);
					UIHelper.highlightElement(driver, ele);
					UIHelper.mouseOverandElementdoubleClick(driver, ele);
					UIHelper.waitForVisibilityOfEleByXpath(driver, documentDetailsTitleXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
						UIHelper.click(driver, docActionTwisterCloseXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
						UIHelper.waitFor(driver);
					}
					break;
				}
			}

			report.updateTestLog("Select File To Update In MyFiles Page", "Select My files page Successfully",
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select File To Update In MyFiles Page", e.getMessage(), Status.FAIL);
		}

		return new AlfrescoDocumentDetailsPage(scriptHelper);
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isPreviousVersionInOrderedList() {

		boolean versionCheckflag = true;
		try {
			toHighlightVersionSegment();

			List<WebElement> olderVersionsNumberWebElement = UIHelper
					.findListOfElementsbyXpath(olderVersionsNumberXpath, driver);

			for (int index = olderVersionsNumberWebElement.size() - 1; index > 0; index--) {

				double olderVersion = Double.parseDouble(olderVersionsNumberWebElement.get(index).getText());
				double previousVersion = Double.parseDouble(olderVersionsNumberWebElement.get(index - 1).getText());

				if (olderVersion < previousVersion) {
					versionCheckflag = true;
				} else {
					versionCheckflag = false;
					break;
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify all earlier versions in ordered list",
					"Verify all earlier versions in ordered list Failed", Status.FAIL);
		}

		return versionCheckflag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public ArrayList<String> getPreviousVersionOrderedList() {
		ArrayList<String> preVersionlist = new ArrayList<String>();
		List<WebElement> olderVersionsNumberWebElement = UIHelper.findListOfElementsbyXpath(olderVersionsNumberXpath,
				driver);
		for (WebElement webElement : olderVersionsNumberWebElement) {
			preVersionlist.add(webElement.getText());
		}
		return preVersionlist;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isCheckedInPersonAndDate() {

		boolean checkedInNameAndDateflag = true;
		try {
			toHighlightVersionSegment();

			List<WebElement> checkedInNameWebElement = UIHelper.findListOfElementsbyXpath(checkedInPersonNameXpath,
					driver);
			List<WebElement> checkedInDateWebElement = UIHelper.findListOfElementsbyXpath(checkedInDateXpath, driver);

			for (int index = 0; index < checkedInNameWebElement.size(); index++) {

				UIHelper.highlightElement(driver, checkedInNameWebElement.get(index));
				UIHelper.highlightElement(driver, checkedInDateWebElement.get(index));
				String name = checkedInNameWebElement.get(index).getText();
				String date = checkedInDateWebElement.get(index).getText();

				if (name.length() > 0 && date.length() > 0) {
					checkedInNameAndDateflag = true;
				} else {
					checkedInNameAndDateflag = false;
					break;
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify to view CheckedIn name and date",
					"Verify to view CheckedIn name and date Failed", Status.FAIL);
		}

		return checkedInNameAndDateflag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public ArrayList<String> getEachVersionCheckInNames() {
		ArrayList<String> eachVersionNameslist = new ArrayList<String>();
		List<WebElement> eachVersionNameslistWebElement = UIHelper.findListOfElementsbyXpath(checkedInPersonNameXpath,
				driver);
		for (WebElement webElement : eachVersionNameslistWebElement) {
			eachVersionNameslist.add(webElement.getText());
		}
		return eachVersionNameslist;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public ArrayList<String> getEachVersionCheckInDate() {
		ArrayList<String> eachVersionDatelist = new ArrayList<String>();
		List<WebElement> eachVersionDatelistWebElement = UIHelper.findListOfElementsbyXpath(checkedInDateXpath, driver);
		for (WebElement webElement : eachVersionDatelistWebElement) {
			eachVersionDatelist.add(webElement.getText());
		}
		return eachVersionDatelist;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isPreviousVersionsRetained() {

		boolean versionCheckflag = true;
		try {
			toHighlightVersionSegment();

			WebElement currentFileVersionWebElement = UIHelper.findAnElementbyXpath(driver, lastVersionNumberXpath);
			double currentVersionNumberDouble = Double.parseDouble(currentFileVersionWebElement.getText());

			List<WebElement> olderVersionsNumberWebElement = UIHelper
					.findListOfElementsbyXpath(olderVersionsNumberXpath, driver);

			for (WebElement webElement : olderVersionsNumberWebElement) {

				double olderVersionNumberInt = Double.parseDouble(webElement.getText());
				if (olderVersionNumberInt < currentVersionNumberDouble) {
					versionCheckflag = true;
				} else {
					versionCheckflag = false;
					break;
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify all earlier versions will be retained",
					"Verify all earlier versions will be retained Failed", Status.FAIL);
		}

		return versionCheckflag;
	}

	private void toHighlightVersionSegment() {
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, versionListSegmentXpath);
		UIHelper.highlightElement(driver, versionListSegmentXpath);
		UIHelper.click(driver, versionListSegmentXpath);
	}

	public AlfrescoMyFilesPage openFile(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTableContainerXpath);
			/*
			 * WebElement myFilesTableEle =
			 * UIHelper.findAnElementbyXpath(driver, myFilesTablesXpath);
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle); WebElement
			 * myFilesTableContainerEle = UIHelper.findAnElementbyXpath(driver,
			 * myFilesTableContainerXpath); UIHelper.highlightElement(driver,
			 * myFilesTableContainerEle); UIHelper.mouseOveranElement(driver,
			 * myFilesTableContainerEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				String atFileName = ele.getText();
				if (fileName.equalsIgnoreCase(atFileName)) {
					UIHelper.highlightElement(driver, ele);
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForPageToLoad(driver);
					/*
					 * UIHelper.waitForVisibilityOfEleByXpath(driver,
					 * loadingImage);
					 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					 * loadingImage);
					 */
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocumentContainerLayerXpath);

					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);

					if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
						UIHelper.click(driver, docActionTwisterCloseXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
						UIHelper.waitFor(driver);
					}

					report.updateTestLog("Open a File", "File opened using " + "<br/><b>FileName:</b>" + fileName,
							Status.DONE);

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Open Edit Properties for the folder
	public AlfrescoMyFilesPage openEdtPropofFileorFldr(String folderName) {
		try {

			boolean ctrgFlag = false;
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By.xpath(myFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(folderName)) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.mouseOveranElement(driver, ele);
					UIHelper.click(driver, edtPrpLinkXPath);

					UIHelper.waitForVisibilityOfEleByXpath(driver, catgryLblXPath);
					report.updateTestLog("Open the Edit Properties of the Folder -> " + folderName,
							"Edit Properties of the Folder opened Successfully ", Status.DONE);
					ctrgFlag = driver.findElement(By.xpath(catgryLblXPath)).isDisplayed();
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					break;
				}

			}
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Open the Edit Properties of the Folder -> " + folderName,
					"Folder is not opened Successfully and the Category is not added. Please verfiy the folder is available",
					Status.FAIL);
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public void commonMethodForSelectingWFFiles() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, filenameXpath);
			UIHelper.findAndAddElementsToaList(driver, filenameXpath, fileFolderNames);
			UIHelper.waitFor(driver);
			String file = dataTable.getData("Sites", "FileName");
			String file1 = dataTable.getData("Sites", "FileName1");
			for (String ele : fileFolderNames) {
				if (ele.equalsIgnoreCase(file) || ele.equalsIgnoreCase(file1)) {
					String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", ele);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", chkboxElement);
				}
			}
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsXpath);
			UIHelper.click(driver, selectedItemsXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, selectWFXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("select Multifiles for WF",
					"Files selected for WF" + "<br /><b> Selected Files : </b>" + file + ", " + file1, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("select Multifiles for WF", "Files selection for WF failed", Status.FAIL);
		}
	}

	public boolean addCattoAssetfromEditPropPage() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, edtPropXPath);
			UIHelper.click(driver, edtPropXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, slctCatXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, slctCatXPath);
			UIHelper.click(driver, slctCatXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, firstCatgryAddXPath);
			UIHelper.click(driver, firstCatgryAddXPath);
			UIHelper.click(driver, okBtnaddCategryXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, slctCatXPath);
			isCtgryDisplydFlag = UIHelper.findAnElementbyXpath(driver, categoryAddedVerifyXPath).isDisplayed();
			report.updateTestLog("Try adding assests to Category",
					"Category -> " + UIHelper.findAnElementbyXpath(driver, categoryAddedVerifyXPath).getText()
							+ " has been added successfully ",
					Status.DONE);
			UIHelper.click(driver, saveBtnAddCatXPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isCtgryDisplydFlag;
	}

	public boolean remvCattoAssetfromEditPropPage() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, slctCatXPath);
			UIHelper.click(driver, slctCatXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, cntrlRightBoxXPath);
			UIHelper.highlightElement(driver, cntrlRightBoxXPath);
			UIHelper.click(driver, cntrlRightBoxXPath);

			UIHelper.click(driver, firstCatgryRemvXPath);
			UIHelper.click(driver, okBtnaddCategryXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, slctCatXPath);
			isCtgryDisplydFlag = UIHelper.findAnElementbyXpath(driver, categoryAddedVerifyXPath).isDisplayed();
			UIHelper.click(driver, saveBtnAddCatXPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isCtgryDisplydFlag;
	}

	public AlfrescoMyFilesPage methodToSelectMultipleFiles(String fileName) {
		try {

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					commonMethodForSelectingFile(fileNameVal);
				}
			} else {
				commonMethodForSelectingFile(fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public AlfrescoMyFilesPage commonMethodForSelectingFile(String fileOrFolderName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileOrFolderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By.xpath(myFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);

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
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	public void selectingMultipleLinkTo() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsXpath);
			UIHelper.click(driver, selectedItemsXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, selectLinkToXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Multiple Files selected", "Files selected sucessfully", Status.DONE);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To select LinkTo option from dropdown
	public void selectDropDownLinkTo() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsXpath);
			UIHelper.click(driver, selectedItemsXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectLinkToXpath);
			UIHelper.highlightElement(driver, selectLinkToXpath);
			UIHelper.click(driver, selectLinkToXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog(" Files selected", "Files selected sucessfully to Link", Status.DONE);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Selected Item Drop down do not have Promote Demote Options
	public boolean verifySelectedItemDontHavePromoteDemote() {
		boolean isPromoteDemoteDisplayed = false;

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsXpath);
			UIHelper.click(driver, selectedItemsXpath);
			UIHelper.waitFor(driver);

			List<WebElement> selectdItemsList = UIHelper.findListOfElementsbyXpath(selectdItemsLstXPath, driver);

			for (WebElement ele : selectdItemsList) {
				if (ele.getText().contains("Promote Lifecycle") || ele.getText().contains("Demote Lifecycle")) {

					isPromoteDemoteDisplayed = true;
				} else {
					isPromoteDemoteDisplayed = false;
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return isPromoteDemoteDisplayed;
	}

	public boolean retSelectedItemHaveLinkToOpt(String folderName) {
		boolean isLinkToDisplayed = false;

		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", folderName);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);

			WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver, pageBodyXpath);
			UIHelper.highlightElement(driver, pageBodyEle);
			UIHelper.mouseOveranElement(driver, pageBodyEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By.xpath(myFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(folderName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsXpath);
					UIHelper.click(driver, selectedItemsXpath);

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<WebElement> selectdItemsList = UIHelper.findListOfElementsbyXpath(selectdItemsLstXPath, driver);

		for (WebElement ele : selectdItemsList) {
			if (ele.getText().contains("Link To")) {

				isLinkToDisplayed = true;
				break;
			} else {
			}
		}

		UIHelper.waitFor(driver);
		return isLinkToDisplayed;
	}

	public boolean retDocActionMenuHaveLinkToOpt(String folderName) {

		UIHelper.waitForVisibilityOfEleByXpath(driver, linkToDocActXPath);
		return UIHelper.findAnElementbyXpath(driver, linkToDocActXPath).isDisplayed();
	}

	// To Count Number of Files selected
	public int countNoOfFilesSelected() {
		int count = 0;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, checkBoxXpath);
			List<WebElement> checkBoxList = UIHelper.findListOfElementsbyXpath(checkBoxXpath, driver);
			for (WebElement ele : checkBoxList) {
				if (ele.isSelected()) {
					count++;
				}
			}
			if (count == 0) {
				report.updateTestLog("File/Folder selected", "No File of Folder Selected", Status.FAIL);
			} else {
				report.updateTestLog("File/Folder selected", count + " File/Folder are Selected", Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

	public boolean retLifeCycleDisplayed() {

		String folderNme = dataTable.getData("Sites", "FileName");

		String finalXpathForAttachLifecycleFolderLink = tempXpathForAttachLifecycleFolderLink.replace("CRAFT",
				folderNme);

		UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForAttachLifecycleFolderLink);
		UIHelper.findAnElementbyXpath(driver, finalXpathForAttachLifecycleFolderLink).isDisplayed();
		return UIHelper.findAnElementbyXpath(driver, finalXpathForAttachLifecycleFolderLink).isDisplayed();

	}

	public void highlightFileOrFolder(String finalFileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);

			WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver, pageBodyXpath);
			UIHelper.highlightElement(driver, pageBodyEle);
			UIHelper.mouseOveranElement(driver, pageBodyEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By.xpath(myFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				String atFileName = ele.getText();
				if (finalFileName.equalsIgnoreCase(atFileName)) {
					UIHelper.highlightElement(driver, ele);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To get FileNames from DataSheet
	public ArrayList<String> getFileNames(String fileNames) {
		
		ArrayList<String> filesList = new ArrayList<String>();
		try {
			/*filesList.clear();*/

			/*if (filesNamesList != null || filesNamesList.size() != 0) {
				
			}
*/
			if (fileNames.contains(",")) {
				String splittedFileDetails[] = fileNames.split(",");
				for (String fileValues : splittedFileDetails) {
					filesList.add(fileValues);
				}
			} else {
				filesList.add(fileNames);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filesList;
	}

	// TO open file after it is edited
	public AlfrescoMyFilesPage openEditedFile(String fileName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);

			WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver, pageBodyXpath);
			UIHelper.highlightElement(driver, pageBodyEle);
			UIHelper.mouseOveranElement(driver, pageBodyEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By.xpath(myFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileName)) {
					ele.click();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
						UIHelper.click(driver, docActionTwisterCloseXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
						UIHelper.waitFor(driver);
					}
					break;
				}
			}
			UIHelper.waitFor(driver);

			report.updateTestLog("Navigate to File",
					"File Opened successfully" + "<br /><b>File Name : </b>" + fileName, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Download File
	public void downloadFile(String fileName) {
		try {
			highlightDocLibPageSec();
			performDownloadFile(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DownloadFialAsZip
	public void downloadFileAsZip(String fileName) {
		try {
			highlightDocLibPageSec();

			downloadAsZipFromSelectedItems(fileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void highlightDocLibPageSec() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);

			WebElement pageBodyEle = UIHelper.findAnElementbyXpath(driver, pageBodyXpath);
			UIHelper.highlightElement(driver, pageBodyEle);
			UIHelper.mouseOveranElement(driver, pageBodyEle);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By.xpath(myFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadAsZipFromSelectedItems(String fileName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileName);

			String filePath = Settings.getInstance().getProperty("DefaultDownloadPath");

			JavascriptExecutor executor = (JavascriptExecutor) driver;

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					executor.executeScript("arguments[0].click();", chkboxElement);
					UIHelper.waitFor(driver);

					UIHelper.waitForVisibilityOfEleByXpath(driver, selectAnItemsButtonXpath);
					UIHelper.click(driver, selectAnItemsButtonXpath);

					UIHelper.waitForVisibilityOfEleByXpath(driver, downloadZipXpath);
					UIHelper.click(driver, downloadZipXpath);

					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
					UIHelper.waitFor(driver);
					File zipFile = new File(filePath + "/" + fileName + ".zip");
					while (true) {
						if (zipFile.exists()) {
							break;
						}
					}

					report.updateTestLog("Download Zip file", "Zip File downloaded sucessfully", Status.DONE);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void performDownloadFile(String fileName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath.replace("CRAFT", fileName);

			String filePath = Settings.getInstance().getProperty("DefaultDownloadPath");

			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath.replace("CRAFT", fileName);

			String finalXpathForBrowseActionOptionLink = tempXpathForBrowseAction.replace("CRAFT", fileName)
					.replace("BROWSE_OPTION", "Download");

			JavascriptExecutor executor = (JavascriptExecutor) driver;

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
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
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);

					File zipFile = new File(filePath + "/" + fileName);
					while (true) {
						if (zipFile.exists()) {
							break;
						}
					}

					report.updateTestLog("Download file", "File:" + fileName + " downloaded sucessfully", Status.DONE);
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
	public void selectStartWorkFlow() {

		UIHelper.waitForVisibilityOfEleByXpath(driver, selectedItemsXpath);
		UIHelper.highlightElement(driver, selectedItemsXpath);
		UIHelper.click(driver, selectedItemsXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, selectWFXpath);
		UIHelper.highlightElement(driver, selectWFXpath);
		UIHelper.click(driver, selectWFXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
	}

	public ArrayList<String> ListFolderNames(String folderDetails) {
		ArrayList<String> folderNamesList = new ArrayList<String>();
		try {

			if (folderDetails.contains(",")) {
				String splittedFolderDetailas[] = folderDetails.split(",");
				for (String folderValues : splittedFolderDetailas) {
					folderNamesList.add(folderValues);
				}
			} else {
				folderNamesList.add(folderDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return folderNamesList;
	}

	// To select workflow file
	public void commonMethodForSelectingFiles(String fileName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, filenameXpath);
			 * UIHelper.findAndAddElementsToaList(driver, filenameXpath,
			 * fileFolderNames); UIHelper.waitFor(driver);
			 */
			// String file = dataTable.getData("Sites", "FileName");

			/*
			 * for (String ele : fileFolderNames) { if
			 * (ele.equalsIgnoreCase(fileName)) {
			 */
			String finalSelectFolderChkboxXpath = tempFolderChkboxXpath.replace("CRAFT", fileName);
			UIHelper.waitFor(driver);
			/*
			 * WebElement chkboxElement = UIHelper.findAnElementbyXpath( driver,
			 * finalSelectFolderChkboxXpath);
			 */
			// chkboxElement.click();
			String finalFileXpath = tempFileXpath.replace("CRAFT", fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalFileXpath));
			UIHelper.highlightElement(driver, finalFileXpath);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, finalSelectFolderChkboxXpath);
			UIHelper.findAnElementbyXpath(driver, finalSelectFolderChkboxXpath).click();
			/*
			 * JavascriptExecutor executor = (JavascriptExecutor) driver;
			 * executor.executeScript("arguments[0].click();", chkboxElement);
			 */

			/*
			 * } }
			 */
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * selectedItemsXpath); UIHelper.click(driver, selectedItemsXpath);
			 * UIHelper.waitFor(driver); String selectOption =
			 * dataTable.getData("Sites", "SelectedItemsMenuOption"); String
			 * finalWorkFlowXpath = selectoptionXpath .replace("CRAFT",
			 * selectOption); UIHelper.click(driver, finalWorkFlowXpath);
			 * UIHelper.waitFor(driver);
			 */
			report.updateTestLog("select files ",
					"Files selected for WF" + "<br /><b> Selected Files : </b>" + fileName, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("select Multifiles for WF", "Files selection for WF failed", Status.FAIL);
		}
	}

	// Create Plain Text File
	public AlfrescoMyFilesPage performInlineEditToAlfrescoFile(String fileDetails, String reqFileName) {
		try {
			if (fileDetails.contains(";")) {
				String splittedFileDetailas[] = fileDetails.split(";");
				for (String fileValues : splittedFileDetailas) {

					if (fileValues.contains(reqFileName)) {
						String splittedFileValues[] = fileValues.split(",");

						String fileType = splittedFileValues[0].replace("FileType:", "");

						String fileName = splittedFileValues[1].replace("FileName:", "");
						String fileContent = splittedFileValues[2].replace("FileContent:", "");
						String fileTitle = splittedFileValues[3].replace("FileTitle:", "");
						String fileDescription = splittedFileValues[4].replace("FileDesc:", "");

						commonMethodForInLineEditToAlfrescoFile(fileType, fileName, fileContent, fileTitle,
								fileDescription);
					}
				}
			} else {

				String splittedFileDetailas[] = fileDetails.split(",");

				String fileType = splittedFileDetailas[0].replace("FileType:", "");

				String fileName = splittedFileDetailas[1].replace("FileName:", "");
				String fileContent = splittedFileDetailas[2].replace("FileContent:", "");
				String fileTitle = splittedFileDetailas[3].replace("FileTitle:", "");
				String fileDescription = splittedFileDetailas[4].replace("FileDesc:", "");

				commonMethodForInLineEditToAlfrescoFile(fileType, fileName, fileContent, fileTitle, fileDescription);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Inline Edit
	public void commonMethodForInLineEditToAlfrescoFile(String fileType, String fileName, String fileContent,
			String fileTitle, String fileDescription) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, fileNameFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, fileNameFieldXpath, fileName);

			UIHelper.click(driver, fileContentFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, fileContentFieldXpath, fileContent);
			UIHelper.sendKeysToAnElementByXpath(driver, fileTitleFieldXpath, fileTitle);
			UIHelper.sendKeysToAnElementByXpath(driver, fileDescFieldXpath, fileDescription);
			UIHelper.click(driver, createBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Perform Inline Edit",
					"Edited content using: " + " <br /><b>FileName : </b>" + fileName + "<br /><b>FileContent : </b>"
							+ fileContent + " <br /><b>FileTitle : </b>" + fileTitle
							+ " <br /><b> FolderDescription : </b>" + fileDescription,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to open a file in MyFiles page
	 * 
	 * @param fileName
	 * @author 412766
	 */
	public void openAFile(String fileName) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.clickanElement(ele);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
						UIHelper.click(driver, docActionTwisterCloseXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
						UIHelper.waitFor(driver);
					}
					report.updateTestLog("Open a file",
							"File opened successfully" + "<br /><b>File Name :</b> " + fileName, Status.DONE);
					break;
				}
			}

		} catch (Exception e) {
			report.updateTestLog("Open a '<b>" + fileName + "</b>' file in MyFiles",
					"Open a '<b>" + fileName + "</b>' file in MyFiles Failed", Status.FAIL);
		}
	}

	/**
	 * Method to open a file in Shared Files page
	 * 
	 * @param fileName
	 * @author 412766
	 */
	public void openAFileInSharedFiles(String fileName) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchResultsListXpath);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.clickanElement(ele);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					if (UIHelper.checkForAnElementbyXpath(driver, docActionTwisterCloseXpath)) {
						UIHelper.click(driver, docActionTwisterCloseXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, docActionTwisterOpenXpath);
						UIHelper.waitFor(driver);
					}
					report.updateTestLog("Open a file in Shared Files",
							"File opened successfully" + "<br /><b>File Name :</b> " + fileName, Status.DONE);
					break;
				}
			}

		} catch (Exception e) {
			report.updateTestLog("Open a '<b>" + fileName + "</b>' file in Shared Files",
					"Open a '<b>" + fileName + "</b>' file in Shared Files Failed", Status.FAIL);
		}
	}

	// Check Create File Field Label
	public boolean checkFieldLabelInCreateFile(String fieldLabelName) {
		boolean isDisplayedFieldLabel = false;
		try {
			String finalXpathForCreateFileFieldLabel = tempXpathForCreateFileFieldLabel.replace("CRAFT",
					fieldLabelName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForCreateFileFieldLabel)) {
				isDisplayedFieldLabel = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFieldLabel;
	}

	public void createcontenttype(String Option) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			UIHelper.click(driver, createLinkXpath);
			String finalcommoncreatexpath = commoncreatexpath.replace("CRAFT", Option);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalcommoncreatexpath);
			
			if(UIHelper.checkForAnElementbyXpath(driver, finalcommoncreatexpath)){
			UIHelper.highlightElement(driver, finalcommoncreatexpath);
			
			UIHelper.click(driver, finalcommoncreatexpath);
			
			report.updateTestLog("Click " +Option,Option+ " clicked successfully",
					Status.DONE);
			}else{
				report.updateTestLog("Click " +Option, Option+ " click failed",
						Status.FAIL);
			}
			
		} catch (Exception e) {
			report.updateTestLog("Click " +Option, Option+ " click failed",
					Status.FAIL);
		}
	}

	public void createWebResource(String name, String URL) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, webresourcename);
			UIHelper.sendKeysToAnElementByXpath(driver, webresourcename, name);
			UIHelper.sendKeysToAnElementByXpath(driver, webresourceURL, URL);
			UIHelper.highlightElement(driver, createBtnXpath);
			// UIHelper.click(driver, createBtnXpath);

			WebElement createBtn = UIHelper.findAnElementbyXpath(driver, createBtnXpath);
			UIHelper.highlightElement(driver, createBtn);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", createBtn);

			/*
			 * UIHelper.waitFor(driver); UIHelper.click(driver, createBtnXpath);
			 */
			/*
			 * UIHelper.mouseOverandElementdoubleClick(driver,
			 * UIHelper.findAnElementbyXpath(driver, webresfailurebutton));
			 * UIHelper.mouseOverandElementdoubleClick(driver,
			 * UIHelper.findAnElementbyXpath(driver, webresfailurebutton));
			 */
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			if (UIHelper.getTextFromWebElement(driver, webresourcefailure).contains("Failure")) {
				UIHelper.click(driver, webresfailurebutton);
				report.updateTestLog("Create Web resource", "Web resource creation failed", Status.FAIL);
			} else {

				report.updateTestLog("Create Web resource", "Web resource created successfully"
						+ "<br /><b>Web Resource Name :</b> " + name + "<br /><b>Web Resource URL:</b> " + URL,
						Status.DONE);
			}

		} catch (Exception e) {
			report.updateTestLog("Create Web resource", "Web resource creation failed", Status.FAIL);

		}
	}

	public void editWebResource(String name, String URL) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, webresourcename);
			UIHelper.findAnElementbyXpath(driver, webresourcename).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, webresourcename, name);
			UIHelper.findAnElementbyXpath(driver, webresourceURL).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, webresourceURL, URL);
			// UIHelper.highlightElement(driver, webeditsave);
			UIHelper.waitForPageToLoad(driver);
			/*
			 * UIHelper.mouseOverandElementdoubleClick(driver,
			 * UIHelper.findAnElementbyXpath(driver, webeditsave));
			 */

			WebElement saveBtn = UIHelper.findAnElementbyXpath(driver, webeditsave);
			UIHelper.highlightElement(driver, saveBtn);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", saveBtn);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Edit Web resource", "Web resource edited successfully"
					+ "<br /><b>Web Resource Name :</b> " + name + "<br /><b>Web Resource URL:</b> " + URL,
					Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Edit Web resource", "Web resource edit failed", Status.FAIL);

		}
	}

	/**
	 * @author 412766
	 * @param folderName
	 */
	public void rename(String folderOrFileName, String reName) {
		try {
			/*UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);*/

			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);


			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
		//	System.out.println("No Of elements 1 : " + uploadedFileOrFolderTitleEleList.size());
			List<WebElement> editFileOrFolderEleList = UIHelper.findListOfElementsbyXpath(renameListXapth, driver);
		//	System.out.println("No Of elements 2 : " + editFileOrFolderEleList.size());

			int index = 0;
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(folderOrFileName)) {
					// UIHelper.highlightElement(driver, ele);
					// UIHelper.scrollToAnElement(ele);
					UIHelper.mouseOveranElement(driver, ele);

					UIHelper.waitFor(driver);

					// UIHelper.highlightElement(driver,
					// editFileOrFolderEleList.get(index));
					// editFileOrFolderEleList.get(index).click();
					UIHelper.clickanElement(editFileOrFolderEleList.get(index));

					UIHelper.waitFor(driver);
					UIHelper.highlightElement(driver, renameTextXpath);
					UIHelper.clearInputField(driver, renameTextXpath);
					UIHelper.sendKeysToAnElementByXpath(driver, renameTextXpath, reName);

					UIHelper.highlightElement(driver, renameSaveButton);
					UIHelper.click(driver, renameSaveButton);

					UIHelper.waitFor(driver);
					
					report.updateTestLog("Rename file/Folder", "Rename successfully"
							+ "<br /><b>Resource Name :</b> " + folderOrFileName + "<br /><b>Resource :</b> " + reName,
							Status.PASS);
					break;
				}
				index++;
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Rename file/Folder", "Rename failed"
					+ "<br /><b>Resource Name :</b> " + folderOrFileName + "<br /><b>Resource :</b> " + reName,
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isRenamePopUpDisplayed() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, renameMsgPopUpXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, renameMsgPopUpXapth))) {
				UIHelper.highlightElement(driver, renameMsgPopUpXapth);
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
	public void cancelRename() {
		UIHelper.highlightElement(driver, renameCancelButton);
		UIHelper.click(driver, renameCancelButton);
		UIHelper.waitFor(driver);
	}

	// Verify Warning message displayed
	public void verifyWarningMsgInvalidURL(String WebResouceName, String URL) {
		try {

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			UIHelper.click(driver, createLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createContentContainerXpath);
			WebElement createDailogEle = UIHelper.findAnElementbyXpath(driver, createContentContainerXpath);
			UIHelper.highlightElement(driver, createDailogEle);
			UIHelper.mouseOveranElement(driver, createDailogEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, webReSrcXpath);
			WebElement folderLinkEle = UIHelper.findAnElementbyXpath(driver, webReSrcXpath);
			UIHelper.highlightElement(driver, folderLinkEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", folderLinkEle);
			UIHelper.click(driver, webReSrcXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, webReSrcNameFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, webReSrcNameFieldXpath, WebResouceName);
			UIHelper.sendKeysToAnElementByXpath(driver, webReSrcURLFieldXpath, URL);

			UIHelper.waitForVisibilityOfEleByXpath(driver, invalidURLWarningXpath);
			UIHelper.highlightElement(driver, invalidURLWarningXpath);
			report.updateTestLog("Verify Invalid URL warning message", "Invalid URL warning message displayed",
					Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Invalid URL warning message", "Invalid URL warning message is not displayed",
					Status.FAIL);
		}
	}

	// Perform Rename to file or folder
	public void performRenameToFolderOrFile(String folderOrFileName, String reName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
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
			List<WebElement> editFileOrFolderEleList = UIHelper.findListOfElementsbyXpath(renameListXapth, driver);

			int index = 0;
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(folderOrFileName)) {
					UIHelper.mouseOveranElement(driver, ele);

					UIHelper.waitFor(driver);
					UIHelper.clickanElement(editFileOrFolderEleList.get(index));

					UIHelper.waitFor(driver);
					UIHelper.highlightElement(driver, renameTextXpath);
					UIHelper.sendKeysToAnElementByXpath(driver, renameTextXpath, reName);

					UIHelper.highlightElement(driver, renameSaveButton);
					UIHelper.click(driver, renameSaveButton);

					UIHelper.waitFor(driver);

					report.updateTestLog("Perform Rename to " + folderOrFileName,
							folderOrFileName + " renamed with reName", Status.DONE);

					break;
				}
				index++;
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// navigate to the page where file or folder is available
	public void navigateToFileOrFolderPg(String folderOrFileName) {
		boolean isFileOrFolderDisplayed = false;
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);
			UIHelper.waitForPageToLoad(driver);

			isFileOrFolderDisplayed = isFileOrFolderDisplayed(folderOrFileName);

			if (isFileOrFolderDisplayed == false) {
				List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);
				int noOfPages = noOfPagesEle.size();
				for (int index = 0; index < noOfPages; index++) {
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);

					isFileOrFolderDisplayed = isFileOrFolderDisplayed(folderOrFileName);
					if (isFileOrFolderDisplayed) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// check file or fodler
	public boolean isFileOrFolderDisplayed(String folderOrFileName) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, waitXpath);
			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(folderOrFileName)) {
					UIHelper.waitFor(driver);
					UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					flag = true;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// check file properties fields
	public void checkEditPropertiesFields(String fieldNames) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropertiesFormXpath);
			UIHelper.highlightElement(driver, editPropertiesFormXpath);
			List<WebElement> editPropertiesLabel = UIHelper.findListOfElementsbyXpath(editPropertiesLabelListXpath,
					driver);
			for (WebElement label : editPropertiesLabel) {
				UIHelper.highlightElement(driver, label);
				editpropertiesList.add(label.getText());
			}

			String[] expectedValues = fieldNames.split(",");

			for (String value : expectedValues) {
				editpropertiesExpectedList.add(value);
			}

			if (UIHelper.compareTwoDiffSizeOfLists(editpropertiesList, editpropertiesExpectedList)) {

				report.updateTestLog("Verify Actual edit properties label names with expected label names",
						"Actual label names matches with expected label names<br><b>Expected values : </b>"
								+ editpropertiesExpectedList + "<br><b>Actual values : </b>" + editpropertiesList,
						Status.PASS);

			} else {
				report.updateTestLog("Verify Actual edit properties label names with expected label names",
						"Actual label names not matches with expected label names<br><b>Expected values : </b>"
								+ editpropertiesExpectedList + "<br><b>Actual values : </b>" + editpropertiesList,
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Cancel Edit properties screen
	public void clickOnCancelEditPropertiesScreen() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelEditPropBtnXpath);
			UIHelper.highlightElement(driver, cancelEditPropBtnXpath);
			UIHelper.click(driver, cancelEditPropBtnXpath);
			report.updateTestLog("Click on [CANCEL] button in edit properties Screen",
					"Clicked on [CANCEL] button succesfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on [CANCEL] button in edit properties Screen",
					"Failed to click on [CANCEL] button", Status.FAIL);
		}
	}

	// Get current URL to work on Marklogic
	public String getMarkLogicUrl() {
		String url = "";
		try {
			UIHelper.waitForPageToLoad(driver);
			String currentUrl = driver.getCurrentUrl();
			String[] baseUrl = currentUrl.split("=");
			String[] finalUrl = baseUrl[1].split("&");
			url = finalUrl[0];
			if (url != null && !url.isEmpty()) {
				report.updateTestLog("Get URL for perform noderef search", "URL:" + url + " retrieved successfully",
						Status.DONE);
			} else {
				report.updateTestLog("Get URL for perform noderef search", "URL:" + url + " failed to retrieve",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return url;
	}

	// Get current URL to work on Marklogic
	public String getMarkLogicManifestUrl(String baseUrl) {
		String url = "";
		try {

			url = baseUrl.replace("urn:pearson:", "").replace(":", "/");

			if (url != null && !url.isEmpty()) {
				report.updateTestLog("Get Manifestation URL", "Manifestation URL:" + url + " retrieved successfully",
						Status.DONE);
			} else {
				report.updateTestLog("Get Manifestation URL", "Manifestation URL:" + url + " failed to retrieve",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	// Wait for file unlock
	public void waitForFileUnlock(String fileName) {
		try {
			String finalXpathForLocekdFile = tempXpathForLocekdFile.replace("CRAFT", fileName);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, finalXpathForLocekdFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on documents
	public void clickOnDocumentItem(String fileOrFolderName, String menuBarItem, String minutes) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTableContainerXpath);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				String atFileName = ele.getText();
				if (fileOrFolderName.equalsIgnoreCase(atFileName)) {
					UIHelper.highlightElement(driver, ele);
					ele.click();

					report.updateTestLog("Click on '" + menuBarItem + "'",
							"User clicked the '" + menuBarItem + "' @ " + minutes + " time", Status.DONE);

					report.updateTestLog("Verify the current time",
							"Current Time: " + new DateUtil().getCurrentDateAndTime(), Status.DONE);

					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);

					if (UIHelper.checkForAnElementbyXpath(driver, docLibXpath)) {
						report.updateTestLog("Open document", "Document:" + menuBarItem + " opened successfully",
								Status.PASS);
					} else {
						report.updateTestLog("Open document", "Failed to open document:" + menuBarItem + " Page",
								Status.FAIL);
					}

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
