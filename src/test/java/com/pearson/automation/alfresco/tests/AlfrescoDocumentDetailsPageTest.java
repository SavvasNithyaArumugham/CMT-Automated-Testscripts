package com.pearson.automation.alfresco.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoDocumentDetailsPageTest.java - This program contains verification
 * methods for Document details page
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoDocumentDetailsPageTest extends ReusableLibrary {

	private AlfrescoDocumentDetailsPage appDocDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
	private String uploadedFilesName;
	private ArrayList<String> propertiesValuesList = new ArrayList<String>();
	private ArrayList<String> expectedPropValuesList = new ArrayList<String>();

	private String loadingImage = ".//*[@id='overlay']/a/*[@id='loadingImage']";
	private String openedDocumentContainerLayerXpath = ".//*[@class='textLayer']";
	private String openedDocHeaderXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//h1";
	private ArrayList<String> docOptionValues = new ArrayList<String>();

	// Web resource
	public String commoncreatexpath = ".//*[@class='create-content']//li//span[text()='CRAFT']";
	private String fileIconXapth = ".//img[@title='CRAFT']";

	private ArrayList<String> actualAttributeValuesFromPropSec = new ArrayList<String>();

	private String splittedPropertyAttributeValues[];

	private boolean isDisplayedDocAction;

	/*** Edit offline ***/
	private String editOfflineXpath = "//a/span[text()='Edit Offline']";
	private String messageXpath = ".//*[@id='message']//span";

	/*** Add comment ***/
	private String commentorXpath = ".//*[@class='info']/a";
	private String commenttextXpath = ".//*[@class='comment-content']";
	private String commentXpath = ".//*[contains(@id,'comment-container')]";

	/*** Preview Download Button ***/
	public String nodeHeaderdXpath = ".//*[@class='node-header']";
	public String aspectcancelbtn = ".//*[contains(@id,'default-aspects-cancel-button')]";
	public String downloadbutton = ".//div[@class='node-action']//a[text()='Download']";

	/*** ShareBox "This Field is mandatory Banner" ***/
	public String shareboxIncompleteFieldBannerXath = ".//*[contains(@title,'This field is mandatory but a value has not been provided')]";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoDocumentDetailsPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Verify file open
	public void verifyUploadedFileIsOpened(String fileName, String fileAppendVal) {
		try {
			String finalFileName = "";
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0] + fileAppendVal;
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}

			String uploadedFilesName = appDocDetailsPg.getDocumentHeaderName();

			String finalActualResultValue = uploadedFilesName.replace("1.0", "");

			if (uploadedFilesName.contains(finalFileName)) {
				report.updateTestLog("Verify user able to open uploaded file",
						"Uploaded file: " + finalFileName + " opened Successfully." + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + finalActualResultValue + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify user able to open uploaded file",
						"Uploaded file: " + finalFileName + " failed to open" + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + finalActualResultValue + "",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get uploaded file name from My Files Page
	public void verifyCreatedFile(String fileName, String fileAppendVal) {
		try {
			String finalFileName = "";
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0] + fileAppendVal;
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}

			uploadedFilesName = appDocDetailsPg.getDocumentHeaderName();

			String finalActualResultValue = uploadedFilesName.replace("1.0", "");

			if (uploadedFilesName.contains(finalFileName)) {
				report.updateTestLog("Verify created file",
						"File: " + finalFileName + " created Successfully." + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + finalActualResultValue + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify created file",
						"File: " + finalFileName + " failed to create" + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + finalActualResultValue + "",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify uploaded file version number
	public void verifyUploadedFileVersionNumber(String fileName, String fileAppendVal) {
		try {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0] + fileAppendVal;
			String finalFileName = part1 + "." + splitVal[1];
			String versionNo = dataTable.getData("MyFiles", "Version");
			String finalVersionNo = versionNo.replace("'", "");

			uploadedFilesName = appDocDetailsPg.getDocumentHeaderName();

			String actualVersionNumber = uploadedFilesName.replace(finalFileName, "");

			if (uploadedFilesName.contains(finalFileName) && uploadedFilesName.contains(finalVersionNo)) {
				report.updateTestLog("Verify uploaded file version",
						"Correct version number:" + finalVersionNo + " is displayed for uploaded file: " + finalFileName
								+ "<br /><b>Expected Result:</b> " + finalVersionNo + ", <br /><b>Actual Result:</b> "
								+ actualVersionNumber,
						Status.PASS);
			} else {
				report.updateTestLog("Verify uploaded file version",
						"Wrong version number is displayed for uploaded file: " + finalFileName
								+ "<br /><b>Expected Result:</b> " + finalVersionNo + ", <br /><b>Actual Result:</b> "
								+ actualVersionNumber,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyUploadedNewVersionFile() {

		try {
			String actualVersionNumber = new AlfrescoDocumentDetailsPage(scriptHelper).getCurrentVersion();
			String expectedVersionNumber = AlfrescoDocumentDetailsPage.gobalCurrentVersionNumber;
			if (expectedVersionNumber.trim().isEmpty()) {
				expectedVersionNumber = dataTable.getData("MyFiles", "Version");
			}
			if (new AlfrescoDocumentDetailsPage(scriptHelper).isUploadedNewVersionFile()) {
				report.updateTestLog("Verify the version incremented",
						"Version incremented successfully" + "<br /><b>Expected Version Number : </b>"
								+ expectedVersionNumber + "<br /><b>Actual Version Number : </b>"
								+ actualVersionNumber.toString(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify the version incremented",
						"Version not incremented" + "<br /><b>Expected Version Number : </b>" + expectedVersionNumber
								+ "<br /><b>Actual Version Number : </b>" + actualVersionNumber.toString(),
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify the version incremented", "Verify the version incremented Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyEditPropertyVersion(String fileName) {
		try {
			String actualVersionNumber = new AlfrescoDocumentDetailsPage(scriptHelper).getCurrentVersion();
			new AlfrescoDocumentDetailsPage(scriptHelper).highlightCurrentVersion();
			String expectedVersionNumber = dataTable.getData("MyFiles", "Version");
			if (actualVersionNumber.equals(expectedVersionNumber)) {
				report.updateTestLog("Verify the version in Version History",
						"Version not incremented" + "<br /><b>File Name : </b>" + fileName
								+ "<br /><b>Expected Version Number : </b>" + expectedVersionNumber
								+ "<br /><b>Actual Version Number : </b>" + actualVersionNumber.toString(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify the version in Version History",
						"Version incremented" + "<br /><b>File Name : </b>" + fileName
								+ "<br /><b>Expected Version Number : </b>" + expectedVersionNumber
								+ "<br /><b>Actual Version Number : </b>" + actualVersionNumber.toString(),
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the version in Version History",
					"Verify the version in Version History Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyVersionForNewVersionUpload(String fileName, String message) {
		try {
			String actualVersionNumber = new AlfrescoDocumentDetailsPage(scriptHelper).getCurrentVersion();
			new AlfrescoDocumentDetailsPage(scriptHelper).highlightCurrentVersion();
			String expectedVersionNumber = dataTable.getData("MyFiles", "Version");
			if (actualVersionNumber.equals(expectedVersionNumber)) {
				report.updateTestLog(message,
						"Version got incremented" + "<br /><b>File Name : </b>" + fileName
								+ "<br /><b>Expected Version Number : </b>" + expectedVersionNumber
								+ "<br /><b>Actual Version Number : </b>" + actualVersionNumber.toString(),
						Status.PASS);
			} else {
				report.updateTestLog(message,
						"Version not incremented" + "<br /><b>File Name : </b>" + fileName
								+ "<br /><b>Expected Version Number : </b>" + expectedVersionNumber
								+ "<br /><b>Actual Version Number : </b>" + actualVersionNumber.toString(),
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the version in Version History",
					"Verify the version in Version History Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyForDuplicateFiles(ArrayList<String> fileList) {
		try {
			boolean flagFile = false;
			boolean flagDuplicateFile = false;
			ArrayList<String> duplicateFileList = new ArrayList<String>();
			for (String fileName : fileList) {
				if (new AlfrescoDocumentLibPage(scriptHelper).isFileIsAvailable(fileName)) {
					flagFile = true;
				} else {
					flagFile = false;
					break;
				}

				String duplicateFileName = fileName.replace(".", ".");
				System.out.println(duplicateFileName);
				duplicateFileList.add(duplicateFileName);
				if (duplicateFileList.size()>1) {
					flagDuplicateFile = true;
				} else {
					flagDuplicateFile = false;
				}
			}
			if (flagFile) {
				report.updateTestLog("Verify the Files Uploaded as New Version",
						"Files verifyed successfully" + "<br /><b>File Names : </b>" + fileList.toString(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Files Uploaded as New Version",
						"File not available" + "<br /><b>File Names : </b>" + fileList.toString(), Status.FAIL);
			}

			if (flagDuplicateFile) {
				report.updateTestLog("Verify the Duplicate Files",
						"Dulpicate File Found" + "<br /><b>Duplicate File Names : </b>" + duplicateFileList.toString(),
						Status.FAIL);
			} else {
				report.updateTestLog("Verify the Duplicate Files", "Dulpicate File Not Found"
						+ "<br /><b>Duplicate File Names : </b>" + duplicateFileList.toString(), Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the Duplicate Files", "Verify the Duplicate Files Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyUploadWindowVisibility() {
		try {
			if (!(new AlfrescoDocumentLibPage(scriptHelper).isUploadWindowDisplayed())) {
				report.updateTestLog("Verify the Upload Window should be closed automatically",
						"Upload Window closed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the Upload Window should be closed automatically",
						"Upload Window closed successfully", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the Upload Window should be closed automatically",
					"Verify the Upload Window should be closed automatically Failed", Status.FAIL);
		}
	}

	/**
	 * Method for retain version
	 * 
	 * @author 412766
	 */
	public void verifyPreviousVersionsRetained() {

		try {
			String currentVersion = new AlfrescoDocumentDetailsPage(scriptHelper).getCurrentVersion();
			ArrayList<String> preVersionList = null;
			if (new AlfrescoMyFilesPage(scriptHelper).isPreviousVersionsRetained()) {
				preVersionList = new AlfrescoMyFilesPage(scriptHelper).getPreviousVersionOrderedList();
				report.updateTestLog("Verify all earlier versions will be retained",
						"Earlier versions retained successfully" + "<br /><b>Current Version Number : </b>"
								+ currentVersion + "<br /><b>Previous Version Number List : </b>"
								+ preVersionList.toString(),
						Status.PASS);
			} else {
				preVersionList = new AlfrescoMyFilesPage(scriptHelper).getPreviousVersionOrderedList();
				report.updateTestLog("Verify all earlier versions will be retained",
						"Earlier versions not retained" + "<br /><b>Current Version : </b>" + currentVersion
								+ "<br /><b>Previous Version List : </b>" + preVersionList.toString(),
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify all earlier versions will be retained",
					"Verify all earlier versions will be retained Failed", Status.FAIL);
		}
	}

	/**
	 * Method for verify Previous Version to a file
	 * 
	 * @author 412766
	 */
	public void verifyPreviousVersionInOrderedList() {

		try {
			ArrayList<String> preVersionList = null;
			if (new AlfrescoMyFilesPage(scriptHelper).isPreviousVersionInOrderedList()) {
				preVersionList = new AlfrescoMyFilesPage(scriptHelper).getPreviousVersionOrderedList();
				report.updateTestLog("Verify all earlier versions in ordered list",
						"Previous versions verified successfully" + "<br /><b>Previous versions : </b>"
								+ preVersionList.toString(),
						Status.PASS);
			} else {
				preVersionList = new AlfrescoMyFilesPage(scriptHelper).getPreviousVersionOrderedList();
				report.updateTestLog("Verify all earlier versions in ordered list", "Previous versions are not in order"
						+ "<br /><b>Previous versions : </b>" + preVersionList.toString(), Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify all earlier versions in ordered list",
					"Verify all earlier versions in ordered list Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyPreviousVersionIntoLatestVersion() {
		try {

			String currentVersion = new AlfrescoDocumentDetailsPage(scriptHelper).getCurrentVersion();
			String previousVersion = AlfrescoDocumentDetailsPage.gobalCurrentVersionNumber;
			if (new AlfrescoDocumentDetailsPage(scriptHelper).isPreviousVersionIntoLatestVersion()) {
				report.updateTestLog("Verify Previous version selected as latest version",
						"Previous version selected successfully" + "<br /><b>Current Version Number : </b>"
								+ currentVersion + "<br /><b>Previous Version Number: </b>" + previousVersion,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Previous version selected as latest version",
						"Previous version not selected successfully" + "<br /><b>Current Version Number : </b>"
								+ currentVersion + "<br /><b>Previous Version Number: </b>" + previousVersion,
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Previous version selected as latest version",
					"Verify Previous version selected as latest version Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyCheckedInPersonAndDate() {
		try {
			ArrayList<String> eachVersionNameList = null;
			ArrayList<String> eachVersionDateList = null;
			if (new AlfrescoMyFilesPage(scriptHelper).isCheckedInPersonAndDate()) {
				eachVersionNameList = new AlfrescoMyFilesPage(scriptHelper).getEachVersionCheckInNames();
				eachVersionDateList = new AlfrescoMyFilesPage(scriptHelper).getEachVersionCheckInDate();
				report.updateTestLog("Verify to view Checked-In Name and Date",
						"Name and Date viewed successfully" + "<br /><b>Version Check-In Names: </b>"
								+ eachVersionNameList.toString() + "<br /><b>Version Dates: </b>"
								+ eachVersionDateList.toString(),
						Status.PASS);
			} else {
				eachVersionNameList = new AlfrescoMyFilesPage(scriptHelper).getEachVersionCheckInNames();
				eachVersionDateList = new AlfrescoMyFilesPage(scriptHelper).getEachVersionCheckInDate();
				report.updateTestLog("Verify to view Checked-In name and date",
						"Name and Date not viewed" + "<br /><b>Version Check-In Names: </b>"
								+ eachVersionNameList.toString() + "<br /><b>Version Dates: </b>"
								+ eachVersionDateList.toString(),
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify to view CheckedIn name and date",
					"Verify to view Checked-In name and date Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyVersionByCommentedFreeText() {
		try {
			String expected = dataTable.getData("Document_Details", "Comments");
			String actual = new AlfrescoDocumentDetailsPage(scriptHelper).getCommentedTextValue();
			if (actual.contains(expected)) {
				actual = expected;
			}
			if (new AlfrescoDocumentDetailsPage(scriptHelper).isVersionByCommentedFreeText()) {
				report.updateTestLog(
						"Verify the Name added via free text", "Name Verified Successfully"
								+ "<br /><b>Expected Value: </b>" + expected + "<br /><b>Actual Value: </b>" + actual,
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Name added via free text", "Name not Verified"
						+ "<br /><b>Expected Value: </b>" + expected + "<br /><b>Actual Value: </b>" + actual,
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify the Name added via free text", "Verify the Name added via free text Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void isPreviousVersionMetaDataRetained() {
		try {
			String editedValue = dataTable.getData("Document_Details", "Title");
			String retainedValue = new AlfrescoDocumentDetailsPage(scriptHelper).getTitlePropertyValue();
			if (new AlfrescoDocumentDetailsPage(scriptHelper).isPreviousVersionMetaDataRetained()) {
				report.updateTestLog("Verify previous version metadata retained in new version",
						"Meatdata retained successfully" + "<br /><b>Expected Value: </b>" + editedValue
								+ "<br /><b>Actual Value: </b>" + retainedValue,
						Status.PASS);
			} else {
				report.updateTestLog("Verify previous version metadata retained in new version",
						"Meatdata not retained successfully" + "<br /><b>Expected Value: </b>" + editedValue
								+ "<br /><b>Actual Value: </b>" + retainedValue,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify previous version metadata retained in new version",
					"Verify previous version metadata retained in new version Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param isZipFile
	 * @param downloadedFileName
	 */
	public void verifyDownloadedFile(boolean isZipFile, String downloadedFileName) {
		try {
			String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
			if (downloadedFileName == null) {
				downloadedFileName = dataTable.getData("MyFiles", "FileName");
			}
			String finalPath = "";
			if (isZipFile) {
				finalPath = downloadedFilePath + "/" + downloadedFileName + ".zip";
			} else {
				finalPath = downloadedFilePath + "/" + downloadedFileName;
			}
			File downloadedFile = new File(finalPath);
			if (downloadedFile.exists()) {
				report.updateTestLog("Verify the downloaded a file",
						"File downloded successfully" + "<br /><b>Download Path : </b>" + finalPath, Status.PASS);
			} else {
				report.updateTestLog("Verify the downloaded a file",
						"File not downloded" + "<br /><b>File Name : </b>" + downloadedFileName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the downloaded a file", "Verify the downloaded a file Failed", Status.FAIL);
		}
	}

	public void verifyLockedDownloadedFile(boolean isZipFile) {
		try {
			String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
			String downloadedFileName = dataTable.getData("MyFiles", "FileName");
			String finalPath = "";
			if (isZipFile) {
				finalPath = downloadedFilePath + "/" + downloadedFileName + ".zip";
			} else {
				String finalFileName;
				if (downloadedFileName.contains(".")) {
					String splitVal[] = downloadedFileName.split(Pattern.quote("."));
					String part1 = splitVal[0] + " (Working Copy)";
					finalFileName = part1 + "." + splitVal[1];
				} else {
					finalFileName = downloadedFileName + " (Working Copy).txt";
				}

				finalPath = downloadedFilePath + "/" + finalFileName;
			}
			File downloadedFile = new File(finalPath);
			if (downloadedFile.exists()) {
				System.out.println("File downloaded Successfully");
				report.updateTestLog("Verify that user is able to download a file or Zip file",
						"File downloded successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify that user is able to download a file or Zip file", "File not downloded",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify that user is able to download a file", e.getMessage(), Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyFilePreviewed() {
		try {
			String fileName = dataTable.getData("MyFiles", "FileName");
			if (new AlfrescoDocumentDetailsPage(scriptHelper).isFilePreviewed()) {
				report.updateTestLog("Verify the file preview",
						"File previewed successfully" + "<br /><b>File Name : </b>" + fileName, Status.PASS);
			} else {
				report.updateTestLog("Verify to file preview",
						"File not previewed" + "<br /><b>File Name : </b>" + fileName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the file preview", "Verify the file preview Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyFilePropertiesViewed() {
		try {
			ArrayList<String> filePropertiesList = null;
			if (new AlfrescoDocumentDetailsPage(scriptHelper).isFilePropertiesViewed()) {
				filePropertiesList = new AlfrescoDocumentDetailsPage(scriptHelper).getFileProperties();
				report.updateTestLog("Verify to view file properties", "File properties verified successfully"
						+ "<br /><b>Verified Properties : </b>" + filePropertiesList.toString(), Status.PASS);
			} else {
				filePropertiesList = new AlfrescoDocumentDetailsPage(scriptHelper).getFileProperties();
				report.updateTestLog("Verify to view file properties", "File properties not verified"
						+ "<br /><b>Verified Properties : </b>" + filePropertiesList.toString(), Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify to view file properties", "Verify to view file properties Failed",
					Status.FAIL);
		}
	}

	// Verify uploaded file property values in Document Details Page
	public void verifyXMPMetaaDataAttributesInPropertySec(String propertyValues) {
		try {

			propertiesValuesList = appDocDetailsPg.getPropertiesFromDocDetailsPage();

			String splittedPropertyValues[] = propertyValues.split(";");

			for (String expPropValue : splittedPropertyValues) {
				expectedPropValuesList.add(expPropValue);
			}
			
			if (UIHelper.compareTwoDiffSizeOfLists(expectedPropValuesList, propertiesValuesList)) {
				report.updateTestLog("Verify XMP metadata attributes in Property Section",
						"XMP metadata attributes are displayed in Property Section"
								+ "<br/><b>XMP Metadata Attribute Values:</b>" + propertyValues,
						Status.PASS);
			} else {
				report.updateTestLog("Verify XMP metadata attributes in Property Section",
						"XMP metadata attributes are failed to displayed in Property Section"
								+ "<br/><b>XMP Metadata Attribute Values:</b>" + propertyValues,
						Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException(
				 * "Verify XMP metadata attributes in Property Section",
				 * "XMP metadata attributes are failed to displayed in Property Section"
				 * );
				 */
			}
		} catch (Exception e) {
		}
	}

	// Verify uploaded file property values in Document Details Page
	public void verifyEditedPropertiesForFile(String propertyValue) {
		try {

			String actualEditedPropertyValue = appDocDetailsPg.getTitleFromPropertiesSecInDocDetailsPage();

			if (actualEditedPropertyValue.contains(propertyValue)
					|| actualEditedPropertyValue.equalsIgnoreCase(propertyValue)) {
				report.updateTestLog("Verify edited file property value in DocumentDetails Page",
						"Edited file property value is displayed Successfully in 'Property' Section"
								+ "<br /><b>Expected Edited Value : </b>" + propertyValue
								+ "<br /><b>Actual Edited Value : </b>" + actualEditedPropertyValue,
						Status.PASS);
			} else {
				report.updateTestLog("Verify edited file property value in 'Property' Section in DocumentDetails Page",
						"Edited file property value is failed to display in 'Property' Section"
								+ "<br /><b>Expected Edited Value : </b>" + propertyValue
								+ "<br /><b>Actual Edited Value : </b>" + actualEditedPropertyValue,
						Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException(
				 * "Verify XMP metadata attributes in Property Section",
				 * "XMP metadata attributes are failed to displayed in Property Section"
				 * );
				 */
			}
		} catch (Exception e) {
		}
	}

	// Verify uploaded file property values in Document Details Page
	public void verifyXMPMetaaDataAttributesInPropertySecWithCreator(String propertyValues) {
		try {

			propertiesValuesList = appDocDetailsPg.getPropertiesWithCreatorFromDocDetailsPage();

			String splittedPropertyValues[] = propertyValues.split(",");

			for (String expPropValue : splittedPropertyValues) {
				expectedPropValuesList.add(expPropValue);
			}
System.out.println(propertiesValuesList);
			if (UIHelper.compareTwoSimilarLists(propertiesValuesList,expectedPropValuesList)) {
				report.updateTestLog("Verify XMP metadata attributes in Property Section",
						"XMP metadata attributes are displayed in Property Section"
								+ "<br/><b>XMP Metadata Attribute Values:</b>" + propertyValues,
						Status.PASS);
			} else {
				report.updateTestLog("Verify XMP metadata attributes in Property Section",
						"XMP metadata attributes are failed to displayed in Property Section"
								+ "<br/><b>XMP Metadata Attribute Values:</b>" + propertyValues,
						Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException(
				 * "Verify XMP metadata attributes in Property Section",
				 * "XMP metadata attributes are failed to displayed in Property Section"
				 * );
				 */
			}
		} catch (Exception e) {
		}
	}

	public void verifyAddedXMPMetaData() {
		try {
			String apectName = dataTable.getData("Document_Details", "AspectName");
			String actualAspectName = appDocDetailsPg.getPropertyContainerContent();

			if (actualAspectName.contains(apectName)) {
				report.updateTestLog("Verify XMP meta data for a file in search results",
						"File with XMP Metadata is searchable" + "<br /><b>Expected Result:</b> " + apectName
								+ ", <br /><b>Actual Result:</b> " + actualAspectName + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify XMP meta data for a file in search results",
						"File with XMP Metadata is failed search" + "<br /><b>Expected Result:</b> " + apectName
								+ ", <br /><b>Actual Result:</b> " + actualAspectName + "",
						Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException(
				 * "Verify XMP meta data for a file in search results",
				 * "File with XMP Metadata is failed search");
				 */
			}
		} catch (Exception e) {
		}
	}

	// Verify uploaded version
	public void verifyUploadedVersion(String firstfileName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, loadingImage);
			// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			// loadingImage);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocHeaderXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, openedDocumentContainerLayerXpath);
			UIHelper.waitForPageToLoad(driver);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			String versionNo = dataTable.getData("MyFiles", "Version");

			uploadedFilesName = appDocDetailsPg.getDocumentHeaderName();
			if (uploadedFilesName.contains(firstfileName) && uploadedFilesName.contains(versionNo)) {
				report.updateTestLog("Verify uploaded file version",
						"Correct version number is displayed for uploaded file", Status.PASS);
			} else {
				report.updateTestLog("Verify uploaded file version",
						"Wrong version number is displayed for uploaded file", Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify Document Options
	public void verifyDocAction(String docActionName) {
		try {
			docOptionValues = appDocDetailsPg.getDocActionOptions();

			for (String actualVal : docOptionValues) {
				if (actualVal.contains(docActionName)) {
					isDisplayedDocAction = true;
					break;
				} else {
					isDisplayedDocAction = false;
				}
			}

			if (isDisplayedDocAction) {
				report.updateTestLog(
						"Verify the '" + docActionName + "' Option for any file or folder from  Document Detail Page",
						"User able to view the '" + docActionName
								+ "' Option for any file or folder from  Document Detail Page",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the '" + docActionName + "' Option for any file or folder from  Document Detail Page",
						"User unable to view the '" + docActionName
								+ "' Option for any file or folder from  Document Detail Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify Document Options
	public void verifyDocActionForNegativeCase(String docActionName) {
		try {
			docOptionValues = appDocDetailsPg.getDocActionOptions();

			for (String actualVal : docOptionValues) {
				if (actualVal.contains(docActionName)) {
					isDisplayedDocAction = true;
					break;
				} else {
					isDisplayedDocAction = false;
				}
			}

			if (!isDisplayedDocAction) {

				report.updateTestLog(
						"Verify the '" + docActionName + "' Option for any files or folder from  Document Detail Page",
						"User unable to view the '" + docActionName
								+ "' Option for any files or folder from  Document Detail Page",
						Status.PASS);

			} else {

				report.updateTestLog(
						"Verify the '" + docActionName + "' Option for any files or folder from  Document Detail Page",
						"User able to view the '" + docActionName
								+ "' Option for any files  from  Document Detail Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify uploaded file property values in Document Details Page
	public void verifyAttributesInPropertySec(String propertyValues, String attributesFor) {
		try {
			actualAttributeValuesFromPropSec = appDocDetailsPg.getPropertyAttributeValues();

			if (propertyValues.contains(";")) {
				splittedPropertyAttributeValues = propertyValues.split(";");

			} else if (propertyValues.contains(",")) {
				splittedPropertyAttributeValues = propertyValues.split(",");
			} else {
				expectedPropValuesList.add(propertyValues);
			}

			if (splittedPropertyAttributeValues != null & splittedPropertyAttributeValues.length > 0) {
				for (String expPropValue : splittedPropertyAttributeValues) {
					expectedPropValuesList.add(expPropValue);
				}
			}

			if (UIHelper.compareTwoDiffSizeOfLists(expectedPropValuesList, actualAttributeValuesFromPropSec)) {
				report.updateTestLog("Verify " + attributesFor + " attributes in Property Section",
						attributesFor + "attributes are displayed in Property Section", Status.PASS);
			} else {
				report.updateTestLog("Verify " + attributesFor + " attributes in Property Section",
						attributesFor + "attributes are failed to displayed in Property Section", Status.FAIL);
			}
			
			System.out.println(expectedPropValuesList);
			System.out.println(actualAttributeValuesFromPropSec);

			expectedPropValuesList.clear();
			actualAttributeValuesFromPropSec.clear();

		} catch (Exception e) {
			report.updateTestLog("Verify " + attributesFor + " attributes in Property Section",
					attributesFor + "attributes are failed to displayed in Property Section", Status.FAIL);
		}
	
	}

	// Verify uploaded file property values in Document Details Page
	public void verifyAttributesInPropertySecForNegativeCase(String propertyValues, String attributesFor) {
		try {
			actualAttributeValuesFromPropSec = appDocDetailsPg.getPropertyAttributeValues();

			if (propertyValues.contains(";")) {
				splittedPropertyAttributeValues = propertyValues.split(";");

			} else if (propertyValues.contains(",")) {
				splittedPropertyAttributeValues = propertyValues.split(",");
			} else {
				expectedPropValuesList.add(propertyValues);
			}

			if (splittedPropertyAttributeValues != null & splittedPropertyAttributeValues.length > 0) {
				for (String expPropValue : splittedPropertyAttributeValues) {
					expectedPropValuesList.add(expPropValue);
				}
			}

			if (!UIHelper.compareTwoDiffSizeOfLists(expectedPropValuesList, actualAttributeValuesFromPropSec)) {
				report.updateTestLog("Verify " + attributesFor + " attributes in Property Section",
						attributesFor + " attributes are not displayed in Property Section", Status.PASS);
			} else {
				report.updateTestLog("Verify " + attributesFor + " attributes in Property Section",
						attributesFor + " attributes are displayed in Property Section", Status.FAIL);
			}

			expectedPropValuesList.clear();
			actualAttributeValuesFromPropSec.clear();

		} catch (Exception e) {
		}
	}

	// Verify uploaded file property values in Document Details Page
	public void verifyAttributesInPropertySec(String propertyValues) {
		try {
			actualAttributeValuesFromPropSec = appDocDetailsPg.getPropertyAttributeValues();

			if (propertyValues.contains(";")) {
				splittedPropertyAttributeValues = propertyValues.split(";");

			} else if (propertyValues.contains(",")) {
				splittedPropertyAttributeValues = propertyValues.split(",");
			} else {
				expectedPropValuesList.add(propertyValues);
			}

			if (splittedPropertyAttributeValues != null & splittedPropertyAttributeValues.length > 0) {
				for (String expPropValue : splittedPropertyAttributeValues) {
					expectedPropValuesList.add(expPropValue);
				}
			}

			if (UIHelper.compareTwoDiffSizeOfLists(expectedPropValuesList, actualAttributeValuesFromPropSec)) {
				report.updateTestLog("Verify properties field values in Property Section",
						propertyValues + " are displayed in Property Section", Status.PASS);
			} else {
				report.updateTestLog("Verify properties field values in Property Section",
						propertyValues + " are failed to displayed in Property Section", Status.FAIL);
			}

			expectedPropValuesList.clear();
			actualAttributeValuesFromPropSec.clear();

		} catch (Exception e) {
		}
	}

	// Verify uploaded file property values in Document Details Page
	public void verifyAttributesInPropertySecForNegativeCase(String propertyValues) {
		try {
			actualAttributeValuesFromPropSec = appDocDetailsPg.getPropertyAttributeValues();

			if (propertyValues.contains(";")) {
				splittedPropertyAttributeValues = propertyValues.split(";");

			} else if (propertyValues.contains(",")) {
				splittedPropertyAttributeValues = propertyValues.split(",");
			} else {
				expectedPropValuesList.add(propertyValues);
			}

			if (splittedPropertyAttributeValues != null & splittedPropertyAttributeValues.length > 0) {
				for (String expPropValue : splittedPropertyAttributeValues) {
					expectedPropValuesList.add(expPropValue);
				}
			}

			if (!UIHelper.compareTwoDiffSizeOfLists(expectedPropValuesList, actualAttributeValuesFromPropSec)) {
				report.updateTestLog("Verify properties field values in  Property Section",
						propertyValues + " are not displayed in Property Section", Status.PASS);
			} else {
				report.updateTestLog("Verify properties field values in  Property Section",
						propertyValues + " are displayed in Property Section", Status.FAIL);
			}

			expectedPropValuesList.clear();
			actualAttributeValuesFromPropSec.clear();

		} catch (Exception e) {
		}
	}

	public void verifyAddedRelationshipData(String fileName) {

		try {
			String actualRelationshipFileName = appDocDetailsPg.getRelationshipTableVal();

			if (actualRelationshipFileName.contains(fileName)) {
				report.updateTestLog("Verify Added Relationship data for a file",
						"User added relationship data to a file '" + fileName + "' successfully "
								+ "</br><b>Expected Result:</b> " + fileName + "</br><b>Actual Result:</b> "
								+ actualRelationshipFileName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Added Relationship data for a file",
						"User not added relationship data to a file '" + fileName + "'" + "</br><b>Expected Result:</b>"
								+ fileName + "</br><b>Actual Result:</b> " + actualRelationshipFileName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Added Relationship data for a file",
					"Verify Added Relationship data for a file Failed", Status.FAIL);
		}
	}

	/**
	 * 
	 * @param fileName
	 */
	public void verifyListOfRelationshipData(String fileName) {

		try {
			String actualRelationshipFileName = appDocDetailsPg.getRelationshipTableValFromList(fileName);

			if (actualRelationshipFileName.contains(fileName)) {
				report.updateTestLog("Verify Added Relationship data for a file",
						"User added relationship data to a file '" + fileName + "' successfully "
								+ "</br><b>Expected Result:</b> " + fileName + "</br><b>Actual Result:</b> "
								+ actualRelationshipFileName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Added Relationship data for a file",
						"User not added relationship data to a file '" + fileName + "'" + "</br><b>Expected Result:</b>"
								+ fileName + "</br><b>Actual Result:</b> " + actualRelationshipFileName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Added Relationship data for a file",
					"Verify Added Relationship data for a file Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void verifyPopUpMsgBwtRelationshipFile(String fileName, String siteName) {

		try {
			if (new AlfrescoDocumentDetailsPage(scriptHelper).isPopUpDisplayedInRelationshipFile()) {
				report.updateTestLog("Verify PopUp Displayed", "PopUp displayed successfully"
						+ "</br><b>File Name:</b> " + fileName + "</br><b>Site Name:</b> " + siteName, Status.FAIL);
			} else {
				report.updateTestLog("Verify PopUp Displayed", "PopUp not displayed" + "</br><b>File Name:</b> "
						+ fileName + "</br><b>Site Name:</b> " + siteName, Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify PopUp Displayed", "Verify PopUp Displayed Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyAspectsAvailable() {
		try {
			String aspectNames = dataTable.getData("Document_Details", "AspectName");

			if (aspectNames.contains(",")) {
				String splittedAspectNames[] = aspectNames.split(",");

				for (String aspectName : splittedAspectNames) {
					verifyAspect(aspectName);
				}
			} else {
				verifyAspect(aspectNames);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyAspect(String aspectName) {
		try {
			if (appDocDetailsPg.isAspectsAvailable(aspectName)) {
				report.updateTestLog("Verify the Aspect availble in 'Manage Aspects'",
						"'" + aspectName + "' Aspects avaialble successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the Aspect availble in 'Manage Aspects'",
						"'" + aspectName + "' Aspects not avaialble", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyAspectsAvailable(String aspectName) {
		try {
			if (appDocDetailsPg.isAspectsAvailable(aspectName)) {
				report.updateTestLog("Verify the Aspect availble in 'Manage Aspects'",
						"'" + aspectName + "' Aspects avaialble successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the Aspect availble in 'Manage Aspects'",
						"'" + aspectName + "' Aspects not avaialble", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the Aspect availble in 'Manage Aspects'",
					"Verify the Aspect availble in 'Manage Aspects' Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyListOfAspectsAvailable() {
		try {
			ArrayList<String> aspectAvailableList = new ArrayList<String>();
			ArrayList<String> aspectNotAvailableList = new ArrayList<String>();
			String aspectNames = dataTable.getData("Document_Details", "AspectName");
			StringTokenizer token = new StringTokenizer(aspectNames, ",");
			boolean flag = false;
			while (token.hasMoreElements()) {
				String aspectName = token.nextToken();
				if (appDocDetailsPg.isAspectsAvailable(aspectName)) {
					aspectAvailableList.add(aspectName);
					flag = true;
				} else {
					aspectNotAvailableList.add(aspectName);
					flag = false;
					break;
				}
			}
			if (flag) {
				report.updateTestLog("Verify All Aspect available in 'Manage Aspects'",
						"All Aspects available successfully" + "<br><b> Aspects Verified</b> : "
								+ aspectAvailableList.toString(),
						Status.PASS);

				UIHelper.waitForVisibilityOfEleByXpath(driver, aspectcancelbtn);
				UIHelper.click(driver, aspectcancelbtn);

			} else {
				report.updateTestLog(
						"Verify All Aspect availble in 'Manage Aspects'", "All Aspects not avaialble"
								+ "<br><b> Aspect Not Available</b> : " + aspectNotAvailableList.toString(),
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify All Aspect available in 'Manage Aspects'",
					"Verify All Aspect available in 'Manage Aspects Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyListOfAspectsAvailable(String aspectNames) {
		try {
			ArrayList<String> aspectAvailableList = new ArrayList<String>();
			ArrayList<String> aspectNotAvailableList = new ArrayList<String>();

			StringTokenizer token = new StringTokenizer(aspectNames, ",");
			boolean flag = false;
			while (token.hasMoreElements()) {
				String aspectName = token.nextToken();
				if (appDocDetailsPg.isAspectsAvailable(aspectName)) {
					aspectAvailableList.add(aspectName);
					flag = true;
				} else {
					aspectNotAvailableList.add(aspectName);
					flag = false;
					break;
				}
			}
			if (flag) {
				report.updateTestLog("Verify All Aspect available in 'Manage Aspects'",
						"All Aspects available successfully" + "<br><b> Aspects Verified</b> : "
								+ aspectAvailableList.toString(),
						Status.PASS);

				UIHelper.waitForVisibilityOfEleByXpath(driver, aspectcancelbtn);
				UIHelper.click(driver, aspectcancelbtn);

			} else {
				report.updateTestLog(
						"Verify All Aspect availble in 'Manage Aspects'", "All Aspects not avaialble"
								+ "<br><b> Aspect Not Available</b> : " + aspectNotAvailableList.toString(),
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify All Aspect available in 'Manage Aspects'",
					"Verify All Aspect available in 'Manage Aspects Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyAppliedAspects() {
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		try {
			if (appDocDetailsPg.isAspectsAvailable(aspectName)) {
				report.updateTestLog("Verify the '" + aspectName + "' Aspect availble in 'Manage Aspects'",
						"'" + aspectName + "' Aspect verified successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the '" + aspectName + "' Aspect availble in 'Manage Aspects'",
						"'" + aspectName + "' Aspect not verified", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the '" + aspectName + "' Aspects availble in 'Manage Aspects'",
					"Verify the '" + aspectName + "' Aspects availble in 'Manage Aspects' Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyAppliedAspects(String aspectName) {
		try {
			if (appDocDetailsPg.isAspectsAvailable(aspectName)) {
				report.updateTestLog("Verify the '" + aspectName + "' Aspect availble in 'Manage Aspects'",
						"'" + aspectName + "' Aspect verified successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the '" + aspectName + "' Aspect availble in 'Manage Aspects'",
						"'" + aspectName + "' Aspect not verified", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the '" + aspectName + "' Aspects availble in 'Manage Aspects'",
					"Verify the '" + aspectName + "' Aspects availble in 'Manage Aspects' Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyAttributeInDocProperties() {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		try {
			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isDocumentPropertyAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);
				} else {
					docPropertyFailList.add(docProperty);
				}
			}

			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the Document Properties for '" + aspectName + "' aspect",
						"<br>" + docPropertyFailList.toString() + " <br>properties are not available", Status.FAIL);
			} else {
				report.updateTestLog("Verify the Document Properties for '" + aspectName + "' aspect",
						"<br><b>Expected Result:</b>All properties should be matched/available"
								+ "<br><b>Actual Result</b>:All properties matched/available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify the Document Properties for " + aspectName + "' aspect",
					"Verify the Document Properties  for " + aspectName + "' aspect Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyAttributeAndValuesInDocProperties() {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		try {
			String docPropertiesAndValues = dataTable.getData("Document_Details", "DocPropertyValues");
			StringTokenizer token = new StringTokenizer(docPropertiesAndValues, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				String[] docValues = docProperty.split("-");
				/*
				 * if (appDocDetailsPg.isDocumentPropertyAvailable(docValues[0].
				 * trim(), docValues[2].trim()) ||
				 * appDocDetailsPg.isFilePropertyAvailable(docValues[0].trim(),
				 * docValues[2].trim())) {
				 */
				if (appDocDetailsPg.isDocumentPropertyAvailable(docValues[0].trim(), docValues[2].trim())) {
					docPropertyPassList.add(docProperty);
				} else {
					docPropertyFailList.add(docProperty);
				}
			}

			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the Property and value for " + aspectName + "' aspect",
						"<br>" + docPropertyFailList.toString() + " <br>properties are not available", Status.FAIL);
			} else {
				report.updateTestLog("Verify the Property and value for " + aspectName + "' aspect",
						"<br><b>Expected Result:</b>All properties should be matched/available"
								+ "<br><b>Actual Result</b>:All properties matched/available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify the Property and value for '" + aspectName + "' aspect",
					"Verify the Property and value for '" + aspectName + "' aspect Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyDocEditProperties() {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		try {
			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isDocumentPropertyAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);
				} else {
					docPropertyFailList.add(docProperty);
				}
			}

			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the Edit Properties of the content",
						"<br>" + docPropertyFailList.toString() + " <br>properties are not available", Status.FAIL);
			} else {
				report.updateTestLog("Verify the Edit Properties of the content",
						"<br><b>Expected Result:</b>All properties should be matched/available"
								+ "<br><b>Actual Result</b>:All properties matched/available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify the Edit Properties of the content",
					"Verify the Edit Properties of the content Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyAllEditProperties() {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		try {
			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isAllEditPropertiesAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);

				} else {
					docPropertyFailList.add(docProperty);
				}
			}
			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the All Edit Properties for " + aspectName + " aspect",
						"<br>" + docPropertyFailList.toString() + " <br>properties are not available", Status.FAIL);
			} else {
				report.updateTestLog("Verify All Edit Properties for " + aspectName + " aspect",
						"<br><b>Expected Result:</b>All properties should be matched/available"
								+ "<br><b>Actual Result</b>:All properties matched/available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify the All Edit Properties", "Verify the All Edit Properties Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyFieldsInEditProperties(String docProperties) {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		try {

			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isAllEditPropertiesAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);

				} else {
					docPropertyFailList.add(docProperty);
				}
			}
			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the fields in Edit Properties",
						"<br>" + docPropertyFailList.toString() + " <br>properties are not available", Status.FAIL);
			} else {
				report.updateTestLog("Verify the fields in Edit Properties",
						"<br><b>Expected Result:</b>All properties should be matched/available"
								+ "<br><b>Actual Result</b>:All properties matched/available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyFieldsInEditPropertiesForNegativeCase(String docProperties) {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		try {

			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isAllEditPropertiesAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);

				} else {
					docPropertyFailList.add(docProperty);
				}
			}
			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the fields in Edit Properties",
						"<br>" + docPropertyFailList.toString() + " <br>properties are not available", Status.PASS);
			} else {
				report.updateTestLog("Verify the fields in Edit Properties",
						"<br><b>Expected Result:</b>All properties available"
								+ "<br><b>Actual Result</b>:All properties available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// verify field values in Edit Properties page
	public void verifyFieldValuesInEditPropPage(String fieldsData) {
		try {

			if (appDocDetailsPg.checkFieldValueInEditPropPage(fieldsData)) {
				report.updateTestLog("Verify the field values in 'Edit Properties' page",
						"Fields Data:" + fieldsData + " displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the field values in 'Edit Properties' page",
						"Fields Data:" + fieldsData + " failed to display", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyRenamedField(String renamedField, String renamedWith) {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		try {
			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isAllEditPropertiesAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);

				} else {
					docPropertyFailList.add(docProperty);
				}
			}
			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify " + renamedField + " Field is renamed with " + renamedWith,
						"<b>" + renamedField + "</b> property is not renamed", Status.FAIL);
			} else {
				report.updateTestLog("Verify " + renamedField + " Field is renamed with " + renamedWith,
						"<b>" + renamedField + "</b> property is renamed succesfully", Status.PASS);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify " + renamedField + " Field is renamed with " + renamedWith,
					"Verify " + renamedField + " Field is renamed with " + renamedWith + " Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyRemovedAspectsEditProperties() {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		try {
			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (!appDocDetailsPg.isAllEditPropertiesAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);
				} else {
					docPropertyFailList.add(docProperty);
				}
			}

			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the Edit Property for Removed " + aspectName + "' aspect",
						"<br>" + docPropertyFailList.toString() + " <br>properties are available", Status.FAIL);
			} else {
				report.updateTestLog("Verify the Edit Property for Removed " + aspectName + "' aspect",
						"<br><b>Expected Result:</b>All properties should not be available"
								+ "<br><b>Actual Result</b>:All properties not available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify the Edit Properties for Removed '" + aspectName + "' aspect",
					"Verify the Edit Properties for Removed '" + aspectName + "' aspect Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyMandatoryAttributeInEditProperties() {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		try {
			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isAllEditPropertiesAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);
				} else {
					docPropertyFailList.add(docProperty);
				}
			}

			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the Mandatory attribute for " + aspectName + "' aspect",
						"<br>" + docPropertyFailList.toString() + " <br>properties are not available", Status.FAIL);
			} else {
				report.updateTestLog("Verify the Mandatory attribute for " + aspectName + "' aspect",
						"<br><b>Expected Result:</b>All properties should be matched/available"
								+ "<br><b>Actual Result</b>:All properties matched/available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify the Mandatory attribute for '" + aspectName + "' aspect",
					"Verify the Mandatory attribute for '" + aspectName + "' aspect Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyMandatoryAttributeInDocProperties() {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		try {
			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isDocumentPropertyAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);
				} else {
					docPropertyFailList.add(docProperty);
				}
			}

			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the Mandatory attribute for " + aspectName + "' aspect",
						"<br>" + docPropertyFailList.toString() + " <br>properties are not available", Status.FAIL);
			} else {
				report.updateTestLog("Verify the Mandatory attribute for " + aspectName + "' aspect",
						"<br><b>Expected Result:</b>All properties should be matched/available"
								+ "<br><b>Actual Result</b>:All properties matched/available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify the Mandatory attribute for '" + aspectName + "' aspect",
					"Verify the Mandatory attribute for '" + aspectName + "' aspect Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyMandatoryFieldEmptyPopUp() {
		try {
			if (appDocDetailsPg.isEmptyFieldPopUpDisplayed()) {
				report.updateTestLog("Verify the Mandatory field Empty PopUp displayed", "Popup Displayed succesfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Mandatory field Empty PopUp displayed", "Popup not Displayed",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the Mandatory field Empty PopUp displayed", e.getMessage(), Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyNonMandatoryFieldInEditProperties() {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		String docProperties = dataTable.getData("Document_Details", "NonMandatoryDocPropertiesField");
		try {
			StringTokenizer token = new StringTokenizer(docProperties, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isNonMandatoryFieldInEditProperties(docProperty)) {
					docPropertyPassList.add(docProperty);
				} else {
					docPropertyFailList.add(docProperty);
				}
			}
			if (docPropertyFailList.size() > 0) {
				report.updateTestLog(
						"Verify the " + docProperties + " is a Non Mandatory attribute by adding those aspects "
								+ aspectName,
						"<br>" + docPropertyFailList.toString() + " <br>is a Mandatory attribute", Status.FAIL);
			} else {
				report.updateTestLog(
						"Verify the " + docProperties + " is a Non Mandatory attribute by adding those aspects "
								+ aspectName,
						"<br><b>Expected Result:</b>Properties should be non Mandatory fields"
								+ "<br><b>Actual Result</b>:Properties are non Mandatory fields<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the " + docProperties + " is a Non Mandatory attribute by adding those aspects "
							+ aspectName,
					"Verify the " + docProperties + " is a Non Mandatory attribute by adding those aspects "
							+ aspectName + " Failed",
					Status.FAIL);
		}
	}

	// To verify Original breadcrumbs/Locations
	public void verifyOriginalLocation(String originalLocation) {
		try {
			String siteName = dataTable.getData("Sites", "SiteName");
			String actualSiteName = siteName.toLowerCase();
			String actualLocation = "/Sites/" + actualSiteName + "/documentLibrary";

			if (originalLocation.equalsIgnoreCase(actualLocation)) {
				report.updateTestLog("Original Location",
						"Original Locations are matching.<br /><b>Expected Result:</b> " + originalLocation
								+ ", <br /><b>Actual Result:</b> " + actualLocation,
						Status.PASS);
			} else {
				report.updateTestLog("Original Location",
						"Original Locations are not matching.<br /><b>Expected Result:</b> " + originalLocation
								+ ", <br /><b>Actual Result:</b> " + actualLocation,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Original Location", e.getMessage(), Status.FAIL);
		}
	}

	// To verify Secondary/Linked breadcrumbs/Locations
	public void verifySecondaryLocation(String linkedLocation) {
		try {
			ArrayList<String> folderNamesList = new ArrayList<String>();
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			String siteName = dataTable.getData("Sites", "SiteName");

			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			folderNamesList = myFiles.getFolderNames(folderDetails);
			String folderName = folderNamesList.get(0);

			String actualSiteName = siteName.toLowerCase();
			String actualLocation = "/Sites/" + actualSiteName + "/documentLibrary/" + folderName;
			if (linkedLocation.equalsIgnoreCase(actualLocation)) {
				report.updateTestLog("Linked Location", "Linked Locations are matching.<br /><b>Expected Result:</b> "
						+ linkedLocation + ", <br /><b>Actual Result:</b> " + actualLocation, Status.PASS);
			} else {
				report.updateTestLog("Linked Location",
						"Linked Locations are not matching.<br /><b>Expected Result:</b> " + linkedLocation
								+ ", <br /><b>Actual Result:</b> " + actualLocation,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Linked Locations", e.getMessage(), Status.FAIL);
		}
	}

	// To Verify Document Action Available or not
	public void verifyDocumentActionsOption(String documentAction) {
		try {
			UIHelper.waitFor(driver);
			driver.navigate().refresh();
			if (new AlfrescoDocumentDetailsPage(scriptHelper).isDocumentActionAvilable(documentAction)) {
				report.updateTestLog("Check for Document Action",
						"Document Actions" + "<br><b>Expected Result:</b> Action " + documentAction
								+ "Should be available" + "<br><b>Actual Result:</b>" + documentAction + " Found",
						Status.PASS);
			} else {
				report.updateTestLog("Check for Document Action",
						"Document Actions" + "<br><b>Expected Result:</b> Action " + documentAction + " not Found"
								+ "<br><b>Actual Result:</b>" + documentAction + " not Found",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Check for Document Action", e.getMessage(), Status.FAIL);
		}

	}

	public void Verifycomment() {
		try {

			if (UIHelper.findAnElementbyXpath(driver, commentXpath).isDisplayed()) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, commentXpath));
				UIHelper.highlightElement(driver, commentXpath);
				UIHelper.highlightElement(driver, commentorXpath);
				UIHelper.highlightElement(driver, commenttextXpath);
				report.updateTestLog("Verify Comment",
						"Comment added to file successfully" + "<br /><b> Commentor Name : </b>"
								+ UIHelper.findAnElementbyXpath(driver, commentorXpath).getText()
								+ "<br /><b> Comment: </b>"
								+ UIHelper.findAnElementbyXpath(driver, commenttextXpath).getText(),
						Status.PASS);

			}

		} catch (Exception e) {
			report.updateTestLog("Verify Comment", "No comments added to the document", Status.FAIL);
			e.getMessage();
		}
	}

	// Verify file open
	public void verifyUploadedFileIsPreviewd(String fileName, String fileAppendVal) {
		try {
			String finalFileName = "";
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0] + fileAppendVal;
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}

			uploadedFilesName = appDocDetailsPg.getDocumentHeaderName();

			String finalActualResultValue = uploadedFilesName.replace("1.0", "");

			if (uploadedFilesName.contains(finalFileName)) {
				report.updateTestLog("Verify user able to preview the file",
						"File: " + finalFileName + " previewed successfully." + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + finalActualResultValue + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify user able to preview the file",
						"File: " + finalFileName + " failed to preview" + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + finalActualResultValue + "",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify file is viewable
	public void verifyFileIsViewable(String fileName, String fileAppendVal) {
		try {
			String finalFileName = "";
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0] + fileAppendVal;
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}

			uploadedFilesName = appDocDetailsPg.getDocumentHeaderName();

			String finalActualResultValue = uploadedFilesName.replace("1.0", "");

			if (uploadedFilesName.contains(finalFileName)) {
				report.updateTestLog("Verify user able to view the file",
						"File: " + finalFileName + " viewed successfully." + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + finalActualResultValue + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify user able to view the file",
						"File: " + finalFileName + " failed to view" + "<br /><b>Expected Result:</b> " + finalFileName
								+ ", <br /><b>Actual Result:</b> " + finalActualResultValue + "",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify message of editoffline
	public void verifyEditoffMessage() {
		try {
			/*
			 * UIHelper.waitForPageToLoad(driver);
			 * UIHelper.highlightElement(driver, editOfflineXpath);
			 * UIHelper.click(driver, editOfflineXpath);
			 */

			UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			String msg = UIHelper.findAnElementbyXpath(driver, messageXpath).getText();
			UIHelper.highlightElement(driver, messageXpath);
			report.updateTestLog("Verify Message",
					"Message displayed successfully." + "<br /><b>Actual Message:</b> " + msg, Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify Message", "Message displayed failed.", Status.FAIL);
		}
	}

	// Verify message of editoffline
	public void verifyNoElementPresent(String Xpath, String text) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, Xpath);
			UIHelper.highlightElement(driver, Xpath);
			if (!UIHelper.findAnElementbyXpath(driver, Xpath).getText().contains(text)) {
				report.updateTestLog("Verify" + text + "Element available",
						text + " Element is not displayed in the page", Status.PASS);
			} else {
				report.updateTestLog("Verify " + text + "Element available",
						"Verify " + text + "Element available in the page", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify " + text + "Element available",
					"Verify " + text + "Element available in the page", Status.FAIL);
		}
	}

	// Verify Version History Header in Document Details Page
	public void verifyVersionHistoryHeader() {
		try {
			if (appDocDetailsPg.isDisplayedVersionHistoryForFile()) {
				report.updateTestLog("Verify the 'Version History' Header in Document Details Page",
						"'Version History' displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify the 'Version History' Header in Document Details Page",
						"'Version History' failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Older Version for file in Document Details Page
	public void verifyOlderVersionForFile(String expectedVersionNo) {
		try {
			String actualVersionNo = appDocDetailsPg.getOlderVesrionNumber();

			if (actualVersionNo.contains(expectedVersionNo)) {
				report.updateTestLog("Verify the 'Older Version' for file in Document Details Page",
						"'Older Version' displayed successfully" + "<br /><b>Expected Result:</b> " + expectedVersionNo
								+ ", <br /><b>Actual Result:</b> " + actualVersionNo + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the 'Older Version' for file in Document Details Page",
						"'Older Version' failed to display" + "<br /><b>Expected Result:</b> " + expectedVersionNo
								+ ", <br /><b>Actual Result:</b> " + actualVersionNo + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Reverted version for file in Document Details Page
	public void verifyRevertedVersionNoForFile(String expectedRevertedVersionNoForFile) {
		try {
			String lastVersion = appDocDetailsPg.getLastVesrionNumber();

			if (lastVersion == null) {
				lastVersion = "Version not changed";
			}

			if (lastVersion.contains(expectedRevertedVersionNoForFile)) {
				report.updateTestLog("Verify Reverted version for file in Document Details Page",
						"Version reverted successfully for file" + "<br /><b>Expected Result:</b> "
								+ expectedRevertedVersionNoForFile + ", <br /><b>Actual Result:</b> " + lastVersion
								+ "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Reverted version for file in Document Details Page",
						"Version failed to revert for file" + "<br /><b>Expected Result:</b> "
								+ expectedRevertedVersionNoForFile + ", <br /><b>Actual Result:</b> " + lastVersion
								+ "",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyFileinDownloadedZipFile(String filepath, String zipFileName, String fileNameToFind) {
		try {
			FileUtil file = new FileUtil();
			boolean isFileFound = file.readContentsOfZipFile(filepath, zipFileName, fileNameToFind);

			if (isFileFound) {
				report.updateTestLog("Verify File", "File Found in Zip File <br> <b>Expected Result:</b> FileName:"
						+ fileNameToFind + "<br><b> Actual Result:</b> FileName:" + fileNameToFind, Status.PASS);
			} else {
				report.updateTestLog("Verify File", "File Not Found", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify File", "File Not Found", Status.FAIL);
		}

	}
	//Verify the presence of file in downloaded zip file
		public boolean checkFileinDownloadedZipFile(String filepath, String zipFileName, String fileNameToFind) {
			boolean flag=false;
			try {
				FileUtil file = new FileUtil();
				boolean isFileFound = file.readContentsOfZipFile(filepath, zipFileName, fileNameToFind);

				if (isFileFound) {
					flag=true;
				} else {
					flag=false;
				}

			} catch (Exception e) {
				report.updateTestLog("Verify File", "File Not Found", Status.FAIL);
			}
			return flag;

		}
	public void validateMessage(String actualMessage, String expectedMessage) {
		try {
			if (actualMessage.equalsIgnoreCase(expectedMessage)) {
				report.updateTestLog("Message Validation", "Message received as expected<br>" + actualMessage,
						Status.PASS);
			} else {
				report.updateTestLog("Message Validation", "Message not received as expected<br>" + actualMessage,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Message Validation", "Message not received as expected", Status.FAIL);
		}

	}

	// Verify Browser View of Document after performing Edit Offline from
	// Document tDetails Page
	public void verifyBrowserViewOfDocAfterEditOffline(String parentWindowDriverID) {
		try {
			boolean isDisplayedChildWindow = appDocDetailsPg.isDisplayedChildWindowDriverID(parentWindowDriverID);

			if (!isDisplayedChildWindow) {
				report.updateTestLog(
						"Verify Browser View of Document when performing 'Edit Offline' in Document Details Page",
						"Document is not visible on the browser when clicked on the option 'Edit offline'",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Browser View of Document when performing 'Edit Offline' in Document Details Page",
						"Document is visible on the browser when clicked on the option 'Edit offline'", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void verifyNullProperties(String fileName, ArrayList<String> properties) {

		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		boolean flag = false;
		for (String property : properties) {
			if (docDetailsPage.isFilePropertyAvailable(property, "(None)")) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag) {
			report.updateTestLog("Verify File Properties are Null",
					"Verified successfully" + "<br /><b> File Verified : </b>" + fileName
							+ "<br /><b> Properties Verified : </b>" + properties.toString(),
					Status.PASS);
		} else {
			report.updateTestLog("Verify File Properties are Null",
					"Verification Failed" + "<br /><b> File Verified : </b>" + fileName
							+ "<br /><b> Properties Verified : </b>" + properties.toString(),
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 */
	public void verifyAddedProperties(String fileName, ArrayList<String> properties,
			ArrayList<String> propertiesValue) {

		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		boolean flag = false;
		int index = 0;
		for (String property : properties) {
			if (docDetailsPage.isFilePropertyAvailable(property, propertiesValue.get(index))) {
				flag = true;
			} else {
				flag = false;
				break;
			}
			index++;
		}

		if (flag) {
			report.updateTestLog("Verify File Properties are Added",
					"Verified successfully" + "<br /><b> File Verified : </b>" + fileName
							+ "<br /><b> Properties Verified : </b>" + properties.toString()
							+ "<br /><b> Properties Value : </b>" + propertiesValue.toString(),
					Status.PASS);
		} else {
			report.updateTestLog("Verify File Properties are Added",
					"Verification Failed" + "<br /><b> File Verified : </b>" + fileName
							+ "<br /><b> Properties Verified : </b>" + properties.toString()
							+ "<br /><b> Properties Value : </b>" + propertiesValue.toString(),
					Status.FAIL);
		}
	}

	// Verify message of editoffline
	public void verifyElementPresent(String Xpath, String text) {
		try {
			String finalcommoncreatexpath = Xpath.replace("CRAFT", text);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalcommoncreatexpath);
			UIHelper.highlightElement(driver, finalcommoncreatexpath);
			if (UIHelper.findAnElementbyXpath(driver, finalcommoncreatexpath).getText().contains(text)) {
				report.updateTestLog("Verify" + text + "Element available", text + " Element available in the page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + text + "Element available",
						"Verify " + text + "Element is not displayed in the page", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify " + text + "Element available",
					"Verify " + text + "Element is not displayed in the page", Status.FAIL);
		}
	}

	// Check Document Action Name
	public void verifyDocumentActionName(String expectedDocumentActionName) {
		try {
			boolean isDiaplayedDocActionName = appDocDetailsPg.checkDocumentActionName(expectedDocumentActionName);

			if (isDiaplayedDocActionName) {
				report.updateTestLog("Verify " + expectedDocumentActionName + " option",
						"User able to see '" + expectedDocumentActionName + "' option in Document Details Page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + expectedDocumentActionName + " option",
						"User unable to see '" + expectedDocumentActionName + "' option in Document Details Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify " + expectedDocumentActionName + " option",
					"User unable to see '" + expectedDocumentActionName + "' option in Document Details Page",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyEPMDatamartRecordIsMapped(String title) {
		try {
			if (new AlfrescoDocumentDetailsPage(scriptHelper).isEPMDatamartRecordMapped(title)) {
				report.updateTestLog("Verify EPM datamart record is mapped to 'Title' and 'Program Component Title'",
						"Record Mapped Successfully" + "<br /><b> Title Verified : </b>" + title, Status.PASS);
			} else {
				report.updateTestLog("Verify EPM datamart record is mapped to 'Title' and 'Program Component Title'",
						"Record not Mapped" + "<br /><b> Title Verified : </b>" + title, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify EPM datamart record is mapped to 'Title' and 'Program Component Title'",
					"Verify EPM datamart record is mapped to 'Title' and 'Program Component Title' Failed",
					Status.FAIL);
		}
	}

	// Check Follow link for folder
	public void verifyFollowLinkForFolderInDocDetailsPg(String folderName) {
		try {
			if (appDocDetailsPg.checkFollowOptionDocDetailPg()) {
				report.updateTestLog("Verify 'Follow' option for folders in Document Details Page",
						"User able to view the <b>'Follow'</b> link option for folder:<b>" + folderName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Follow' option for folders in Document Details Page",
						"User not able to view the <b>'Follow'</b> link option for folder: <b>" + folderName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify 'Follow' option for folders in Document Details Page",
					"User not able to view the <b>'Follow'</b> link option for folder: <b>" + folderName + "</b>",
					Status.FAIL);
		}
	}

	// Check Unfollow link for file
	public void verifyGreenTickSignInDocDetailsPg(String fileOrFolderName) {
		try {
			if (appDocDetailsPg.checkUnfollowOptionDocDetailPg()) {
				report.updateTestLog(
						"Verify green coloured 'tick' sign after clicking on Follow option for files in Document Details Page",
						"User able to view the green coloured <b>'tick'</b> sign after clicking on Follow option for file or folder:<b>"
								+ fileOrFolderName + "</b>",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify green coloured 'tick' sign after clicking on Follow option for files in Document Details Page",
						"User not able to view the green coloured <b>'tick'</b> sign after clicking on Follow option for file or folder:<b>"
								+ fileOrFolderName + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify green coloured 'tick' sign after clicking on Follow option for files in Document Details Page",
					"User not able to view the green coloured <b>'tick'</b> sign after clicking on Follow option for file or folder:<b>"
							+ fileOrFolderName + "</b>",
					Status.FAIL);
		}
	}

	// Check Unfollow link for file
	public void verifyUnfollowLinkInDocDetailsPg(String fileOrFolderName) {
		try {
			if (appDocDetailsPg.checkUnfollowOptionDocDetailPg()) {
				report.updateTestLog("Verify 'Unfollow' option for folders in Document Details Page",
						"User able to view the <b>'Unfollow'</b> link option for folder:<b>" + fileOrFolderName
								+ "</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Unfollow' option for folders in Document Details Page",
						"User not able to view the <b>'Unfollow'</b> link option for folder: <b>" + fileOrFolderName
								+ "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify 'Unfollow' option for folders in Document Details Page",
					"User not able to view the <b>'Unfollow'</b> link option for folder: <b>" + fileOrFolderName
							+ "</b>",
					Status.FAIL);
		}
	}

	// Check Follow link for folder
	public void verifyFollowedDocInDocDetailsPg(String folderName) {
		try {
			if (appDocDetailsPg.checkUnfollowOptionDocDetailPg()) {
				report.updateTestLog("Verify 'Followed' document in Document Details Page",
						"User <b>'Followed'</b> the document:<b>" + folderName + "</b> in Document Details Page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Followed' document in Document Details Page",
						"User <b>'Unfollowed'</b> the document:<b>" + folderName + "</b> in Document Details Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify 'Followed' document in Document Details Page",
					"User <b>'Unfollowed'</b> the document:<b>" + folderName + "</b> in Document Details Page",
					Status.FAIL);
		}
	}

	// Check Follow link for folder
	public void verifyUnfollowedDocInDocDetailsPg(String folderName) {
		try {
			if (appDocDetailsPg.checkFollowOptionDocDetailPg()) {
				report.updateTestLog("Verify 'Unfollowed' document in Document Details Page",
						"User <b>'Unfollowed'</b> the document:<b>" + folderName + "</b> in Document Details Page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Unfollowed' document in Document Details Page",
						"User <b>'Followed'</b> the document:<b>" + folderName + "</b> in Document Details Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify document property values in Document Details Page
	public void verifyPropertiesInDocDetailPage(String propertyValues) {
		try {

			propertiesValuesList = appDocDetailsPg.getPropertiesFromDocDetailsPage();

			String splittedPropertyValues[] = propertyValues.split(";");
			
			System.out.println(propertiesValuesList);
			

			for (String expPropValue : splittedPropertyValues) {
				expectedPropValuesList.add(expPropValue);
				
			}
			System.out.println(expectedPropValuesList);
			if (UIHelper.compareTwoDiffSizeOfLists(expectedPropValuesList, propertiesValuesList)) {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are successfully displayed in Property Section" + "<br/><b>Property Values:</b>"
								+ propertyValues,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are failed to display in Property Section" + "<br/><b>Property Values:</b>"
								+ propertyValues,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are failed to display in Property Section" + "<br/><b>Property Values:</b>"
							+ propertyValues,
					Status.FAIL);

		}
	}

	// Verify Mandatory Field in Edit Properties Page
	public void verifyMandatoryFieldInEditPropPage(String fieldLabelName) {
		try {
			boolean isDisplayedFieldAsMandatoryField = appDocDetailsPg
					.checkMandatoryFieldInEditPropPage(fieldLabelName);

			if (isDisplayedFieldAsMandatoryField) {
				report.updateTestLog("Verify the Mandatory Field in Edit Properties Page",
						"Field:" + fieldLabelName + " is mandatory field", Status.PASS);
			} else {
				report.updateTestLog("Verify the Mandatory Field in Edit Properties Page",
						"Field:" + fieldLabelName + " is not a mandatory field", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the Mandatory Field in Edit Properties Page",
					"Field:" + fieldLabelName + " is not a mandatory field", Status.FAIL);
		}
	}

	// Verify Mandatory Field in Edit Properties Page for negative case
	public void verifyMandatoryFieldInEditPropPageForNegativeCase(String fieldLabelName) {
		try {
			boolean isDisplayedFieldAsMandatoryField = appDocDetailsPg
					.checkMandatoryFieldInEditPropPage(fieldLabelName);

			if (!isDisplayedFieldAsMandatoryField) {

				report.updateTestLog("Verify the Mandatory Field in Edit Properties Page",
						"Field:" + fieldLabelName + " is not a mandatory field", Status.PASS);
			} else {
				report.updateTestLog("Verify the Mandatory Field in Edit Properties Page",
						"Field:" + fieldLabelName + " is mandatory field", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the Mandatory Field in Edit Properties Page",
					"Field:" + fieldLabelName + " is mandatory field", Status.FAIL);
		}
	}

	// verify folder size in Document Details Page
	public void verifyFolderSizeInDocDetailsPage(String folderName) {
		try {
			String folderSizeVal = appDocDetailsPg.getFolderSizeFromDocDetailsPage();

			if (!folderSizeVal.equalsIgnoreCase("Size Not get displayed for selected folders")) {
				report.updateTestLog("Verify size of the selected folder in 'View Details' Page",
						"Size calculated individually for folder:" + folderName + " and size get displayed as '"
								+ folderSizeVal + "' in 'View Details' Page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify size of the selected folder in 'View Details' Page",
						"Size not calculated individually for folder:" + folderName
								+ " and size not get displayed in 'View Details' Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify size of the selected folder in 'View Details' Page",
					"Size not calculated individually for folder:" + folderName
							+ " and size not get displayed in 'View Details' Page",
					Status.FAIL);
		}
	}

	// Check Follow link for folder
	public void verifyPearsonLogoInDocDetailsPg(String fileName) {
		try {
			if (appDocDetailsPg.checkPearsonLogo()) {
				report.updateTestLog("Verify 'Pearson Logo' in Document Details Page",
						"<b>'Pearson Logo'</b> is displayed successfully in Document Details Page when open file: "
								+ fileName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Pearson Logo' in Document Details Page",
						"<b>'Pearson Logo'</b> is failed to display in Document Details Page when open file: "
								+ fileName,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify 'Pearson Logo' in Document Details Page",
					"<b>'Pearson Logo'</b> is failed to display in Document Details Page when open file: "
							+ fileName,
					Status.FAIL);
		}
	}

	public boolean verifyShareBoxIncompleteBannerisDisplayed(String folderName) {
		boolean flag = false;

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxIncompleteFieldBannerXath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, shareboxIncompleteFieldBannerXath));
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, shareboxIncompleteFieldBannerXath)) {

				UIHelper.highlightElement(driver, shareboxIncompleteFieldBannerXath);
				report.updateTestLog("Verify Incomplete External Sharing Banner Is Displayed",
						"Incomplete External Sharing Banner displayed successfully", Status.PASS);
				flag = true;
			} else {
				report.updateTestLog("Verify Incomplete External Sharing Banner Is Displayed",
						"Incomplete External Sharing Banner is not displayed ", Status.FAIL);
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			report.updateTestLog("Verify Incomplete External Sharing Banner Is Displayed",
					"Incomplete External Sharing Banner is not displayed ", Status.FAIL);
			flag = false;
		}
		return flag;
	}

	
	/**
	 * @author 317085
	 */
	public void verifyAttributeInDocPropertiesnew(String docProperties) {
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		try {
			
			StringTokenizer token = new StringTokenizer(docProperties, ";");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				if (appDocDetailsPg.isDocumentPropertyAvailable(docProperty)) {
					docPropertyPassList.add(docProperty);
				} else {
					docPropertyFailList.add(docProperty);
				}
			}

			if (docPropertyFailList.size() > 0) {
				report.updateTestLog("Verify the Document Properties for '" + aspectName + "' aspect",
						"<br>" + docPropertyFailList.toString() + " <br>properties are not available", Status.FAIL);
			} else {
				report.updateTestLog("Verify the Document Properties for '" + aspectName + "' aspect",
						"<br><b>Expected Result:</b>All properties should be matched/available"
								+ "<br><b>Actual Result</b>:All properties matched/available<br>"
								+ "Properties Verified are:<br>" + docPropertyPassList.toString(),
						Status.PASS);
			}

			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify the Document Properties for " + aspectName + "' aspect",
					"Verify the Document Properties  for " + aspectName + "' aspect Failed", Status.FAIL);
		}
	}

	
	// Verify message of editoffline
	public boolean verifyElementPresentreturn(String Xpath, String text) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalcommoncreatexpath = Xpath.replace("CRAFT", text);
			//UIHelper.waitForVisibilityOfEleByXpath(driver, finalcommoncreatexpath);
			//UIHelper.highlightElement(driver, finalcommoncreatexpath);
			if (UIHelper.checkForAnElementbyXpath(driver, finalcommoncreatexpath)) {
				return true;
			} else {
			
				return false;
			}

		} catch (Exception e) {
			report.updateTestLog("Verify " + text + "Element available",
					"Verify " + text + "Element is not displayed in the page", Status.FAIL);
			return false;
			
		}
	}
	
	
	public void verifyRelationShipFields(ArrayList<String> expVal) {
		try {
		ArrayList<String> actualVal=appDocDetailsPg.getNewRelationShipFieldValues();
		if(!actualVal.isEmpty()  && (actualVal.size()==expVal.size())) {
			   Collections.sort(actualVal);	
			   Collections.sort(expVal);
			   if(actualVal.equals(expVal)) {
				   report.updateTestLog("Verify New relationship fields", "New relationship field present as expected <br><b>Expected</b>" +expVal+"<br><b>Actual</b>"+actualVal,
							Status.PASS);
			   }else {
				   report.updateTestLog("Verify New relationship fields", "New relationship field present as not same as expected <br><b>Expected</b>" +expVal+"<br><b>Actual</b>"+actualVal,
							Status.FAIL); 
			   }
			}else {
				report.updateTestLog("Verify New relationship fields", "New relationship field present as not same as expected <br><b>Expected</b>" +expVal+"<br><b>Actual</b>"+actualVal,
						Status.FAIL);
			}
		}catch(Exception e) {
			report.updateTestLog("Verify New relationship fields", "New relationship fields verification failed",Status.FAIL); 
		}
	}
	
	public void verifyDeleteRelationShipFields(ArrayList<String> expVal) {
		try {
		ArrayList<String> actualVal=appDocDetailsPg.getDeleteRelationShipFieldValues();	
		if(!actualVal.isEmpty()  && (actualVal.size()==expVal.size())) {
			   Collections.sort(actualVal);	
			   Collections.sort(expVal);
			   if(actualVal.equals(expVal)) {
				   report.updateTestLog("Verify Delete relationship fields", "Delete relationship field present as expected <br><b>Expected</b>" +expVal+"<br><b>Actual</b>"+actualVal,
							Status.PASS);
			   }else {
				   report.updateTestLog("Verify Delete relationship fields", "Delete relationship field present as not same as expected <br><b>Expected</b>" +expVal+"<br><b>Actual</b>"+actualVal,
							Status.FAIL); 
			   }
			}else {
				   report.updateTestLog("Verify Delete relationship fields", "Delete relationship field present as not same as expected <br><b>Expected</b>" +expVal+"<br><b>Actual</b>"+actualVal,
							Status.FAIL); 
			   }	
		}catch(Exception e) {
			report.updateTestLog("Verify Delete relationship fields", "Delete relationship fields verification failed",Status.FAIL); 
		}
	}
	
	
}
