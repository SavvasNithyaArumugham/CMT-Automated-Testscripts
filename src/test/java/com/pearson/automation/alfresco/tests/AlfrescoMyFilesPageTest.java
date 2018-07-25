package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoMyFilesPageTest extends ReusableLibrary {

	private AlfrescoMyFilesPage appMyFilesPg = new AlfrescoMyFilesPage(scriptHelper);
	private ArrayList<String> uploadedFilesNameList = new ArrayList<String>();
	private boolean isDisplayedUploadedFile;

	private boolean isDisplayedCreatedFolder;

	private ArrayList<String> createdFileNames = new ArrayList<String>();

	private ArrayList<String> uploadedFileNames = new ArrayList<String>();

	private String linkImgXpath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//td[3]//img";

	private String uploadedFilesTitlesXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td[5]//h3/span/a";
	private String actualFolderName;
	private String actualFileName;
	protected String tempFileXpath = "//*[@class='filename']//a[text()='CRAFT']";

	protected String primaryLocXpath = ".//*[@id='link-function-table']//tr[1]//td[2]//a";
	protected String secondaryLocXpath = ".//*[@id='link-function-table']//tr[2]//td[2]//a";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoMyFilesPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Verify file upload
	public void verifyUploadedFile(String fileName, String fileAppendVal) {
		String finalFileName =null;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (fileName.contains(".")) {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0] + fileAppendVal;
			 finalFileName = part1 + "." + splitVal[1];
			}else{
				 finalFileName = fileName;
			}
		
			String actualResult = "";
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(finalFileName)) {
					isDisplayedUploadedFile = true;
					actualResult = actualUploadedFileName;
					break;
				} else {
					isDisplayedUploadedFile = false;
					actualResult = "File Not Found";
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify Uploaded/created File",
						"File: " + finalFileName + " Uploaded/created Successfully." + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + actualResult + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify Uploaded/created File",
						"File: " + finalFileName + " failed to upload." + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + actualResult + "",
						Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
			/*report.updateTestLog("Verify File",
					"File failed to upload.",Status.FAIL);*/
		}
	}

	// Verify Unzipped Folder details
	public void verifyUnzippedFolder(String folderDetails) {
		try {
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = appMyFilesPg.getFolderNames(folderDetails);
			for (String folderName : folderNamesList) {
				isDisplayedCreatedFolder = validateCreatedFolders(folderName);
				String actualResultVal = getCreatedFolder(folderName);
				String finalActualResult;
				if (actualResultVal == null) {
					finalActualResult = "Unzipped Folder Not Found";
				} else {
					finalActualResult = actualResultVal;
				}

				if (isDisplayedCreatedFolder) {
					report.updateTestLog("Verify Unzipped Folders",
							"Zip file:" + folderName + ".zip extracted Successfully" + "<br /><b>Expected Result:</b> "
									+ folderName + ", <br /><b>Actual Result:</b> " + finalActualResult + "",
							Status.PASS);
				} else {
					report.updateTestLog("Verify Unzipped Folders",
							"Zip file:" + folderName + ".zip failed to unzip" + "<br /><b>Expected Result:</b> "
									+ folderName + ", <br /><b>Actual Result:</b> " + finalActualResult + "",
							Status.FAIL);
				}
			}

			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Uploaded file (when upload a same file again)
	public void verifyUploadedOfSameFileAgain(String fileName, String fileAppendVal) {
		try {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0] + fileAppendVal;
			String finalFileName = part1 + "." + splitVal[1];
			appMyFilesPg.highlightFileOrFolder(finalFileName);
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			String actualResult = "";
			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(finalFileName)) {
					isDisplayedUploadedFile = true;
					actualResult = actualUploadedFileName;
					break;
				} else {
					isDisplayedUploadedFile = false;
					actualResult = "File not found";
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify that user can upload the same file again in a folder",
						"New file gets created with hash increament (File: " + finalFileName + ") Successfully."
								+ "<br /><b>Expected Result:</b> " + finalFileName + ", <br /><b>Actual Result:</b> "
								+ actualResult + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify that user can upload the same file again in a folder",
						"New File (" + finalFileName + ") fialed to upload" + "<br /><b>Expected Result:</b> "
								+ finalFileName + ", <br /><b>Actual Result:</b> " + actualResult + "",
						Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify moved file in target site
	public void verifyMovedFileInTargetSite(String fileName, String fileAppendVal) {
		try {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0] + fileAppendVal;
			String finalFileName = part1 + "." + splitVal[1];

			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(finalFileName)) {
					isDisplayedUploadedFile = true;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify moved File", "File:" + finalFileName + " moved Successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify moved File", "File:" + finalFileName + " fialed to move", Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
		}
	}

	// Verify copied file in target site
	public void verifyCopieddFileInTargetSite(String fileName, String fileAppendVal) {
		try {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0] + fileAppendVal;
			String finalFileName = part1 + "." + splitVal[1];

			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(finalFileName)) {
					isDisplayedUploadedFile = true;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify copied File", "File:" + finalFileName + " copied Successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify copied File", "File:" + finalFileName + " fialed to copy", Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
		}
	}

	// Get uploaded file details
	public ArrayList<String> getUploadedFileDetails(String fileNames) {
		try {
			if (fileNames.contains(",")) {
				String splittedFileNames[] = fileNames.split(",");
				for (String fileNameVal : splittedFileNames) {
					uploadedFileNames.add(fileNameVal);
				}
			} else {
				uploadedFileNames.add(fileNames);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return uploadedFileNames;
	}

	// Verify uploaded multiple files
	public void verifyUploadedMultipleFiles(String fileName) {
		try {
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();
			String actualFileNameValue = "";

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					isDisplayedUploadedFile = commonMethodForValidateUploadedFile(fileNameVal, uploadedFilesNameList);
					actualFileNameValue = getUploadedFileName(fileNameVal, uploadedFilesNameList);
					validateUploadedFile(fileNameVal, actualFileNameValue);
				}
			} else {
				isDisplayedUploadedFile = commonMethodForValidateUploadedFile(fileName, uploadedFilesNameList);
				actualFileNameValue = getUploadedFileName(fileName, uploadedFilesNameList);
				validateUploadedFile(fileName, actualFileNameValue);
			}

			/*
			 * if(isDisplayedUploadedFile) {
			 * report.updateTestLog("Verify Uploaded Files", "Files: " +
			 * fileName + " uploaded Successfully." +
			 * "<br /><b>Expected Result:</b> " + fileName +
			 * ", <br /><b>Actual Result:</b> " + actualFileNameValue + "",
			 * Status.PASS); } else {
			 * report.updateTestLog("Verify Uploaded Files", "Files: " +
			 * fileName + " failed to upload." +
			 * "<br /><b>Expected Result:</b> " + fileName +
			 * ", <br /><b>Actual Result:</b> " + actualFileNameValue + "",
			 * Status.FAIL); }
			 */
			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Common method for validate upload file
	public void validateUploadedFile(String expectedFileName, String actualFileNameVal) {
		try {
			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify Uploaded File",
						"Uploaded File: " + expectedFileName + " displayed Successfully."
								+ "<br /><b>Expected Result:</b> " + expectedFileName + ", <br /><b>Actual Result:</b> "
								+ actualFileNameVal + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Uploaded File",
						"Uploaded File: " + expectedFileName + " failed to display." + "<br /><b>Expected Result:</b> "
								+ expectedFileName + ", <br /><b>Actual Result:</b> " + actualFileNameVal + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Common method for validate renamed file
	public void validateRenamedFile(String expectedFileName) {
		try {
			String actualFileNameVal = getUploadedFileName(expectedFileName, uploadedFilesNameList);
			ArrayList<String> uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			boolean isDisplayedRenamedFile = commonMethodForValidateUploadedFile(expectedFileName,
					uploadedFilesNameList);

			if (isDisplayedRenamedFile) {
				report.updateTestLog("Verify Renamed File",
						"Renamed File: " + expectedFileName + " displayed Successfully."
								+ "<br /><b>Expected Result:</b> " + expectedFileName + ", <br /><b>Actual Result:</b> "
								+ actualFileNameVal + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Renamed File",
						"Renamed File: " + expectedFileName + " failed to display." + "<br /><b>Expected Result:</b> "
								+ expectedFileName + ", <br /><b>Actual Result:</b> " + actualFileNameVal + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Common method file validate uploaded file and return String value
	public String getUploadedFileName(String uploadedFileName, ArrayList<String> uploadedFilesNamesList) {
		for (String actualUploadedFileName : uploadedFilesNamesList) {
			if (actualUploadedFileName.contains(uploadedFileName)) {
				actualFileName = actualUploadedFileName;
				break;
			} else {
				actualFileName = "File Not Found";
			}
		}
		return actualFileName;
	}

	// Verify transformed file names in Folder
	public void verifyTransformedFiles(String fileName) {
		try {
			ArrayList<String> expectedTransFileNameList = new ArrayList<String>();
			ArrayList<String> actualTransFileNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					expectedTransFileNameList.add(fileNameVal);
				}
			} else {
				expectedTransFileNameList.add(fileName);
			}

			if (UIHelper.compareTwoDiffSizeOfLists(expectedTransFileNameList, actualTransFileNameList)) {
				report.updateTestLog("Verify Transformed Files",
						"Files transformed Successfully<br><b>Expected Result:</b>" + expectedTransFileNameList + ""
								+ "<br><b>Actual Result:</b>" + actualTransFileNameList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Transformed Files", "Files fialed to transform<br><b>Expected Result:</b>"
						+ expectedTransFileNameList + "" + "<br><b>Actual Result:</b>" + actualTransFileNameList,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean commonMethodForValidateUploadedFile(String uploadedFileName,
			ArrayList<String> uploadedFilesNamesList) {
		for (String actualUploadedFileName : uploadedFilesNamesList) {
			if (actualUploadedFileName.contains(uploadedFileName)) {
				isDisplayedUploadedFile = true;
				break;
			} else {
				isDisplayedUploadedFile = false;
			}
		}

		return isDisplayedUploadedFile;
	}

	public String getCreatedFolder(String folderName) {
		String actualFolderNameVal ="";
		try {
			ArrayList<String> createdFolderList = appMyFilesPg.getUploadedFileOrFolderTitle();

			if (createdFolderList != null & createdFolderList.size() > 0) {
				for (String actualCreatedFolderName : createdFolderList) {
					if (actualCreatedFolderName.equalsIgnoreCase(folderName)) {
						actualFolderNameVal = actualCreatedFolderName;
						break;
					} else {
						actualFolderNameVal = "Created folder not found";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actualFolderNameVal;

	}

	// Verify Created folder
	public void verifyCreatedFolder(String targetFolderDetails) {
		try {
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = appMyFilesPg.getFolderNames(targetFolderDetails);

			for (String folderName : folderNamesList) {
				isDisplayedCreatedFolder = validateCreatedFolders(folderName);
				String actualResultVal = getCreatedFolder(folderName);
				String finalActualResult;
				if (actualResultVal == null) {
					finalActualResult = "Folder Not Found";
				} else {
					finalActualResult = actualResultVal;
				}

				if (isDisplayedCreatedFolder) {
					report.updateTestLog("Verify Created Folders",
							"Folder: " + folderName + " created Successfully." + "<br /><b>Expected Result:</b> "
									+ folderName + ", <br /><b>Actual Result:</b> " + finalActualResult + "",
							Status.PASS);
				} else {
					report.updateTestLog("Verify Created Folders",
							"Folder: " + folderName + " failed to create" + "<br /><b>Expected Result:</b> "
									+ folderName + ", <br /><b>Actual Result:</b> " + finalActualResult + "",
							Status.FAIL);
				}
			}

			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Created folder
	public void verifyLinkedFolder(String folderName) {
		try {
			isDisplayedCreatedFolder = validateCreatedFolders(folderName);
			String actualResultVal = getCreatedFolder(folderName);
			String finalActualResult;
			if (actualResultVal == null) {
				finalActualResult = "Folder Not Found";
			} else {
				finalActualResult = actualResultVal;
			}

			if (isDisplayedCreatedFolder) {
				report.updateTestLog("Verify the linked Folder",
						"User able to see the linked folder:" + folderName + " in the document library"
								+ "<br /><b>Expected Result:</b> " + folderName + ", <br /><b>Actual Result:</b> "
								+ finalActualResult + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the linked Folder",
						"User not able to see the linked folder:" + folderName + " in the document library"
								+ "<br /><b>Expected Result:</b> " + folderName + ", <br /><b>Actual Result:</b> "
								+ finalActualResult + "",
						Status.FAIL);
			}

			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Created folder
	public void verifyUpdatedFolder(String folderName) {
		boolean isDisplayedUpdatedFolder = false;
		try {

			isDisplayedUpdatedFolder = validateCreatedFolders(folderName);
			String actualResultVal = getCreatedFolder(folderName);
			String finalActualResult;
			if (actualResultVal == null) {
				finalActualResult = "Folder Not Found";
			} else {
				finalActualResult = actualResultVal;
			}

			if (isDisplayedUpdatedFolder) {
				report.updateTestLog("Verify Updated Folder",
						"Folder: " + folderName + " updated Successfully." + "<br /><b>Expected Result:</b> "
								+ folderName + ", <br /><b>Actual Result:</b> " + finalActualResult + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Updated Folders",
						"Folder: " + folderName + " failed to update" + "<br /><b>Expected Result:</b> " + folderName
								+ ", <br /><b>Actual Result:</b> " + finalActualResult + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Created folder
	public void verifyUserNavigatedTodFolderLocation(String folderNameVal) {
		try {
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = appMyFilesPg.getFolderNames(folderNameVal);

			for (String folderName : folderNamesList) {
				isDisplayedCreatedFolder = validateCreatedFolders(folderName);
				String actualResultVal = getCreatedFolder(folderName);
				String finalActualResult;
				if (actualResultVal == null) {
					finalActualResult = "Folder Not Found";
				} else {
					finalActualResult = actualResultVal;
				}

				if (isDisplayedCreatedFolder) {
					report.updateTestLog("Verify User navigation to Folder location",
							"User navigated to Folder: " + folderName + " location Successfully."
									+ "<br /><b>Expected Result:</b> " + folderName + ", <br /><b>Actual Result:</b> "
									+ finalActualResult + "",
							Status.PASS);
				} else {
					report.updateTestLog("Verify User navigation to Folder location",
							"User failed to navigate to Folder: " + folderName + " location"
									+ "<br /><b>Expected Result:</b> " + folderName + ", <br /><b>Actual Result:</b> "
									+ finalActualResult + "",
							Status.FAIL);
				}
			}

			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Deleted folder
	public void verifyDeletedFolderForLockedFiles(String folderDetails) {
		try {
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = appMyFilesPg.getFolderNames(folderDetails);

			for (String folderName : folderNamesList) {
				isDisplayedCreatedFolder = validateCreatedFolders(folderName);
			}

			if (isDisplayedCreatedFolder) {
				report.updateTestLog("Verify delete a folder if it has a file that is checked out for editing purpose",
						"User unable to delete a folder which have the locked files", Status.PASS);
			} else {
				report.updateTestLog("Verify delete a folder if it has a file that is checked out for editing purpose",
						"User able to delete a folder which have the locked files", Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException("Verify Created Folders",
				 * "Folders fialed to create");
				 */
			}

			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify moved folder
	public void verifyMovedFolder(String folderDetails) {
		boolean isDisplayedFolderName = false;
		try {
			ArrayList<String> folderNamesList = appMyFilesPg.getFolderNames(folderDetails);

			for (String folderName : folderNamesList) {
				isDisplayedFolderName = validateCreatedFolders(folderName);
				String actualResultVal = getCreatedFolder(folderName);
				if (isDisplayedFolderName) {
					report.updateTestLog("Verify Moved Folder",
							"Folder: " + folderName + " moved Successfully." + "<br /><b>Expected Result:</b> "
									+ folderName + ", <br /><b>Actual Result:</b> " + actualResultVal + "",
							Status.PASS);
				} else {
					report.updateTestLog("Verify Moved Folder",
							"Folder: " + folderName + " failed to move" + "<br /><b>Expected Result:</b> " + folderName
									+ ", <br /><b>Actual Result:</b> " + actualResultVal + "",
							Status.PASS);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify copied folder
	public void verifyCopiedFolder(String folderDetails) {
		try {
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = appMyFilesPg.getFolderNames(folderDetails);

			for (String folderName : folderNamesList) {
				isDisplayedCreatedFolder = validateCreatedFolders(folderName);
				String actualResultVal = getCreatedFolder(folderName);
				if (isDisplayedCreatedFolder) {
					report.updateTestLog("Verify Copied Folder",
							"Folder: " + folderName + " Copied Successfully." + "<br /><b>Expected Result:</b> "
									+ folderName + ", <br /><b>Actual Result:</b> " + actualResultVal + "",
							Status.PASS);
				} else {
					report.updateTestLog("Verify Copied Folder",
							"Folder: " + folderName + " failed to copy" + "<br /><b>Expected Result:</b> " + folderName
									+ ", <br /><b>Actual Result:</b> " + actualResultVal + "",
							Status.PASS);
				}
			}

			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify deleted folder
	public void verifyDeletedFolder(String folderDetails) {
		try {
			boolean isDeletedCreatedFolder;
			ArrayList<String> folderNamesList = appMyFilesPg.getFolderNames(folderDetails);

			for (String folderName : folderNamesList) {
				isDeletedCreatedFolder = validateCreatedFolders(folderName);
				if (!isDeletedCreatedFolder) {
					report.updateTestLog("Verify Deleted Folder",
							"<b>Folder:</b> " + folderName + " deleted Successfully.", Status.PASS);
				} else {
					report.updateTestLog("Verify Deleted Folder", "<b>Folder:</b> " + folderName + " failed to delete",
							Status.FAIL);
				}
			}

			isDeletedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Deleted folder
	public void verifyDeletedFolderNegativeCase(String folderDetails) {
		try {
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = appMyFilesPg.getFolderNames(folderDetails);

			for (String folderName : folderNamesList) {
				isDisplayedCreatedFolder = validateCreatedFolders(folderName);

				if (isDisplayedCreatedFolder) {
					report.updateTestLog("Verify Deleted Folder", "User unable to delete <b>Folder:</b> " + folderName,
							Status.PASS);
				} else {

					report.updateTestLog("Verify Deleted Folder", "User able to delete <b>Folder:</b> " + folderName,
							Status.FAIL);
				}
			}
			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify deleted folder
	public void verifyFolderDeletion(String folderName) {
		try {
			boolean isDeletedCreatedFolder;
			isDeletedCreatedFolder = validateCreatedFolders(folderName);
			if (!isDeletedCreatedFolder) {
				report.updateTestLog("Verify Deleted Folder", "<b>Folder:</b> " + folderName + " deleted Successfully.",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Deleted Folder", "<b>Folder:</b> " + folderName + " failed to delete",
						Status.FAIL);
			}

			isDeletedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Deleted folder
	public void verifyFolderDeletionForNegativeCase(String folderName) {
		try {
			isDisplayedCreatedFolder = validateCreatedFolders(folderName);

			if (isDisplayedCreatedFolder) {
				report.updateTestLog("Verify Deleted Folder", "User unable to delete <b>Folder:</b> " + folderName,
						Status.PASS);
			} else {

				report.updateTestLog("Verify Deleted Folder", "User able to delete <b>Folder:</b> " + folderName,
						Status.FAIL);
			}
			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Deleted file
	public void verifyDeletedFile(String fileName) {
		try {
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(fileName)) {
					isDisplayedUploadedFile = true;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (isDisplayedCreatedFolder) {
				report.updateTestLog("Verify File Deletion", "User able to delete <b>File:</b> " + fileName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify File Deletion", "User unable to delete <b>File:</b> " + fileName,
						Status.FAIL);

			}
			uploadedFilesNameList.clear();
			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Deleted file
	public void verifyDeletedFileNegativeCase(String fileName) {
		try {
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(fileName)) {
					isDisplayedUploadedFile = true;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (isDisplayedCreatedFolder) {
				report.updateTestLog("Verify File deletion", "User unable to delete <b>File:</b> " + fileName,
						Status.PASS);
			} else {

				report.updateTestLog("Verify File deletion", "User able to delete <b>File:</b> " + fileName,
						Status.FAIL);
			}
			uploadedFilesNameList.clear();
			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify deleted folder
	public void verifyCreatedFolderIsDeleted(String folderDetails) {
		try {
			boolean isDeletedCreatedFolder;
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = appMyFilesPg.getFolderNames(folderDetails);

			for (String folderName : folderNamesList) {
				isDeletedCreatedFolder = validateCreatedFolders(folderName);
				if (!isDeletedCreatedFolder) {
					report.updateTestLog("Verify Deleted Folder",
							"<b>Folder:</b> " + folderName + " deleted Successfully.", Status.PASS);
				} else {
					report.updateTestLog("Verify Deleted Folder", "<b>Folder:</b> " + folderName + " failed to delete",
							Status.FAIL);
				}
			}

			isDeletedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Document deletion
	public void verifyDocumentDeletion(String documentName) {
		try {
			boolean isDeletedCreatedFolder;

			isDeletedCreatedFolder = validateCreatedFolders(documentName);
			if (!isDeletedCreatedFolder) {
				report.updateTestLog("Verify Deleted Document",
						"<b>Document:</b> " + documentName + " deleted Successfully.", Status.PASS);
			} else {
				report.updateTestLog("Verify Deleted Document",
						"<b>Document:</b> " + documentName + " failed to delete", Status.FAIL);
			}

			isDeletedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean validateCreatedFolders(String folderName) {

		boolean isDisplayedCreatedFolderName = false;
		try {
			ArrayList<String> createdFolderList = appMyFilesPg.getUploadedFileOrFolderTitle();

			if (createdFolderList != null & createdFolderList.size() > 0) {
				for (String actualCreatedFolderName : createdFolderList) {
					if (actualCreatedFolderName.equalsIgnoreCase(folderName)) {
						isDisplayedCreatedFolderName = true;
						break;
					} else {
						isDisplayedCreatedFolderName = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedCreatedFolderName;

	}

	public void verifyAssetAddtoCatgry() {
		try {

			boolean assertVal = false;
			assertVal = appMyFilesPg.addCattoAssetfromEditPropPage();

			if (assertVal) {
				report.updateTestLog("Try adding assests to Category under my files Tab",
						"Category ->  has been added successfully ", Status.PASS);
			} else {
				// frameworkParameters.setStopExecution(true);
				report.updateTestLog("Try adding assests to Category under my files Tab",
						"Category ->  has NOT been added successfully ", Status.FAIL);

				/*
				 * throw new FrameworkException(
				 * "Try adding assests to Category under my files Tab",
				 * "Category ->  has been added successfully ");
				 */

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Create Plain Text File
	public ArrayList<String> getCreatedFileNames(String fileDetails) {
		try {
			if (fileDetails.contains(";")) {
				String splittedFileDetailas[] = fileDetails.split(";");
				for (String fileValues : splittedFileDetailas) {
					String splittedFileValues[] = fileValues.split(",");

					String fileName = splittedFileValues[1].replace("FileName:", "");
					createdFileNames.add(fileName);
				}
			} else {

				String splittedFileDetailas[] = fileDetails.split(",");

				String fileName = splittedFileDetailas[1].replace("FileName:", "");
				createdFileNames.add(fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createdFileNames;
	}

	// To Verify Link Image is displayed for a Linked File
	public void verifyLinkImage(String fileOrFolderName) {
		try {
			String finalImageLinkXpath = linkImgXpath.replace("CRAFT", fileOrFolderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalImageLinkXpath);
			

		//	for (WebElement ele : uploadedFileOrFolderTitleEleList) {
			if (UIHelper.checkForAnElementbyXpath(driver, finalImageLinkXpath)) {
					UIHelper.highlightElement(driver, finalImageLinkXpath);
					
					UIHelper.waitFor(driver);
					report.updateTestLog("Link a File", "Link Image displayed successfully for" + fileOrFolderName,
							Status.PASS);
					
				}else {
					report.updateTestLog("Link a File", "Link Image is not displayed  for" + fileOrFolderName,
							Status.FAIL);
				}

			
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Link a File", "Link Image not displayed ", Status.FAIL);
		}

	}

	public void verifyDeletedFiles(String fileName) {
		try {
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					isDisplayedUploadedFile = commonMethodForValidateUploadedFile(fileNameVal, uploadedFilesNameList);
				}
			} else {
				isDisplayedUploadedFile = commonMethodForValidateUploadedFile(fileName, uploadedFilesNameList);
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify Deleted Files", "Files failed to delete", Status.FAIL);
			} else {
				report.updateTestLog("Verify Deleted Files", "Files deleted Sucessfully</br><b>Expected Result:</b>"
						+ fileName + "</br><b>Actual Result:</b>" + fileName, Status.PASS);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Life cycle is Attached and Promote Demote not available
	// in Selected Items Drop down
	public void verifyLifeCyclePromoteAndDemoteOptions() {
		boolean blnFlagOne = false;
		try {

			blnFlagOne = appMyFilesPg.verifySelectedItemDontHavePromoteDemote();
			if (!blnFlagOne) {

				report.updateTestLog(
						"Verify Options like Promote and demote are not available on the selected Items Dropdown on attaching lifecycle state to any folder and files",
						"Options verified Successfully ", Status.PASS);

			} else {

				report.updateTestLog(
						"Verify Options like Promote and demote are not available on the selected Items Dropdown on attaching lifecycle state to any folder and files",
						"Options NOT verified Successfully ", Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyLifeCycleState(String expPrmtVal, String actualPrmtVal) {
		try {

			if (expPrmtVal.equalsIgnoreCase(actualPrmtVal)) {

				report.updateTestLog("Verify user is able to promote LifeCycle", "Lifecycle promoted successfully ",
						Status.PASS);

			} else {

				report.updateTestLog("Verify user is able to promote LifeCycle", "Lifecycle promoted successfully ",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyAttachLifeCycleDisplayed() {

		boolean blnFlg = false;

		blnFlg = appMyFilesPg.retLifeCycleDisplayed();
		if (blnFlg) {

			report.updateTestLog(
					"Verify that if user attaches lifecycle state for any folder does not reflect on sub folder",
					"Subfolder doesn't reflect Parent folder lifecycle and its verified successfuly ", Status.PASS);

		} else {

			report.updateTestLog(
					"Verify that if user attaches lifecycle state for any folder does not reflect on sub folder",
					"Subfolder reflects Parent folder lifecycle and its verified successfuly ", Status.FAIL);

		}
	}

	// Verify the Link To Option displayed under Selected Items List for folder
	public void verifySelectedItemHaveLinkToOpt() {
		try {
			boolean blnFlagLinkTo = false;
			String folderNme = dataTable.getData("MyFiles", "FileName");

			blnFlagLinkTo = appMyFilesPg.retSelectedItemHaveLinkToOpt(folderNme);

			if (blnFlagLinkTo) {

				report.updateTestLog("Verify user is able to find Link to option from selected items dropdown.",
						"Link To Option displayed successfully under Selected Items List ", Status.PASS);

			} else {

				report.updateTestLog("Verify user is able to find Link to option from selected items dropdown.",
						"Link To Option NOT displayed successfully under Selected Items List ", Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify the Link To Option displayed under Document Action
	// for folder
	public void verifyDocActionMenuHaveLinkToOpt() {
		try {
			boolean blnFlagLinkTo = false;
			String folderNme = dataTable.getData("MyFiles", "FileName");

			blnFlagLinkTo = appMyFilesPg.retDocActionMenuHaveLinkToOpt(folderNme);

			if (blnFlagLinkTo) {

				report.updateTestLog("Verify user is able to find Link to option from selected items dropdown.",
						"Link To Option displayed successfully under Selected Items List ", Status.PASS);

			} else {

				report.updateTestLog("Verify user is able to find Link to option from selected items dropdown.",
						"Link To Option NOT displayed successfully under Selected Items List ", Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To verify filename before and after linking operation
	public void verifyFileNames(String fileName, String fileAppendVal) {
		try {
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();
			String actualResult = "";
			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(fileName)) {
					isDisplayedUploadedFile = true;
					actualResult = actualUploadedFileName;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify File Name", "Filenames are same.<br /><b>Expected Result:</b> " + fileName
						+ ", <br /><b>Actual Result:</b> " + actualResult, Status.PASS);
			} else {
				report.updateTestLog("Verify File Name", "Filenames are Not same.<br /><b>Expected Result:</b> "
						+ fileName + ", <br /><b>Actual Result:</b> " + actualResult, Status.FAIL);

			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
		}
	}

	// To verify file/Folder Located or not
	public void verifyIsFileAvilabile(String fileName, String fileAppendVal) {
		try {
			String finalFileName;
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0] + fileAppendVal;
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();
			String actualName = "File Not Found";
			for (String actualFileName : uploadedFilesNameList) {
				if (actualFileName.contains(finalFileName)) {
					isDisplayedUploadedFile = true;
					actualName = actualFileName;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Find File/Folder",
						"File/Folder: " + finalFileName + " Available<br/><b>Expected Result:</b>" + fileName
								+ "<br/><b>Actual Result:</b>" + actualName,
						Status.PASS);
			} else {
				report.updateTestLog("Find File/Folder",
						"File/Folder: " + finalFileName + " not Available<br/><b>Expected Result:</b>" + fileName
								+ "<br/><b>Actual Result:</b>" + actualName,
						Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
			report.updateTestLog("Find File", e.getMessage(), Status.FAIL);
		}
	}

	// To Verify Multiple Files
	public AlfrescoMyFilesPage verifyMultipleFilesAvailable(String fileNames) {
		try {

			if (fileNames.contains(",")) {
				String splittedFileName[] = fileNames.split(",");
				for (String fileName : splittedFileName) {

					verifyIsFileAvilabile(fileName, "");
				}
			} else {
				verifyIsFileAvilabile(fileNames, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Validate Component Site files by accessing through Program Site
	public void validateFilesUndersideComponentSiteInDocLib(String fileName) {
		try {
			String actualResult = "";
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(fileName)) {
					isDisplayedUploadedFile = true;
					actualResult = actualUploadedFileName;
					break;
				} else {
					isDisplayedUploadedFile = false;
					actualResult = "File Not Found";
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog(
						"Verify the users of the Program Site is able to access all contents inside folder named with the component's sitename in its Document Library",
						"Users of the Program Site is accessed all contents:" + fileName
								+ " inside folder named with the component's sitename in its Document Library"
								+ "<br /><b>Expected Result:</b> " + fileName + ", <br /><b>Actual Result:</b> "
								+ actualResult + "",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify the users of the Program Site is able to access all contents inside folder named with the component's sitename in its Document Library",
						"Users of the Program Site is failed to accessed all contents:" + fileName
								+ " inside folder named with the component's sitename in its Document Library"
								+ "<br /><b>Expected Result:</b> " + fileName + ", <br /><b>Actual Result:</b> "
								+ actualResult + "",
						Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
		}
	}

	// Verify Program Component Site folder as sub folder under Program Site
	// -->Document Library
	public void verifyProgramComponentSiteFolderUnderProgramSiteDocLibPage(String targetFolderDetails) {
		try {
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = appMyFilesPg.getFolderNames(targetFolderDetails);

			for (String folderName : folderNamesList) {
				isDisplayedCreatedFolder = validateCreatedFolders(folderName);
				String actualResultVal = getCreatedFolder(folderName);
				String finalActualResult;
				if (actualResultVal == null) {
					finalActualResult = "Folder Not Found";
				} else {
					finalActualResult = actualResultVal;
				}

				if (isDisplayedCreatedFolder) {
					report.updateTestLog("Verify the Program's Document Library",
							"Entire Document Library of the Component Site:" + folderName
									+ " appears is visible as a subfolder within Documents Folder"
									+ "<br /><b>Expected Result:</b> " + folderName + ", <br /><b>Actual Result:</b> "
									+ finalActualResult + "",
							Status.PASS);
				} else {
					report.updateTestLog("Verify Created Folders",
							"Folder: " + folderName + " failed to create" + "<br /><b>Expected Result:</b> "
									+ folderName + ", <br /><b>Actual Result:</b> " + finalActualResult + "",
							Status.FAIL);
				}
			}

			isDisplayedCreatedFolder = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param folderName
	 */
	public void verifyComponentSiteNameFolderInProgramSite(String folderName) {
		try {
			if (validateCreatedFolders(folderName)) {
				report.updateTestLog(
						"Verify the user is able to access the 'Component Site' "
								+ "named Folder in 'Program Site document Library",
						"User able to access 'Component Site' Folder" + "<br /><b>Expected Result : </b> Folder Name - "
								+ folderName + ",<br /><b>Actual Result : </b> Folder Name - " + folderName + "",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the user is able to access the 'Component Site' "
								+ "named Folder in 'Program Site document Library",
						"User not able to access 'Component Site' Folder"
								+ "<br /><b>Expected Result : </b> Folder Name - " + folderName
								+ ",<br /><b>Actual Result : </b> Folder Name - " + folderName + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the user is able to access the 'Component Site' "
							+ "named Folder in 'Program Site document Library",
					"Verify the user is able to access the 'Component Site' "
							+ "named Folder in 'Program Site document Library Failed",
					Status.FAIL);
		}
	}

	// Click on the select document to Navigate to Document Details Package
	public void Checkdocument(String file) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalFileXpath = tempFileXpath.replace("CRAFT", file);
			if (UIHelper.checkForAnElementbyXpath(driver, finalFileXpath)) {
				UIHelper.highlightElement(driver, finalFileXpath);
				report.updateTestLog("Document Name", "Document is available" + "<br /><b> Doc Name :</b>" + file,
						Status.PASS);
			} else {
				report.updateTestLog("Document Name", "Document not found", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Document Name", "Document not found", Status.FAIL);
		}
	}

	// To verify link file location details
	public void verifyLinkLoc(String fileName) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, primaryLocXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, primaryLocXpath));
				UIHelper.highlightElement(driver, primaryLocXpath);
				UIHelper.highlightElement(driver, secondaryLocXpath);
				report.updateTestLog("Verify File Location",
						"Linked file is available." + "<br /><b>Orginial Location : </b> "
								+ UIHelper.findAnElementbyXpath(driver, primaryLocXpath).getText()
								+ ", <br /><b>Secondary Location : </b> "
								+ UIHelper.findAnElementbyXpath(driver, secondaryLocXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify File Name", "Filenames are Not same.<br /><b>Expected Result:</b> "
						+ fileName + ", <br /><b>Actual Result:</b> ", Status.FAIL);
			}

		} catch (Exception e) {
		}
	}

	public void verifyLinkImageDisplayed(String fileOrFolderName) {
		try {
			AlfrescoDocumentLibPage docLib = new AlfrescoDocumentLibPage(scriptHelper);
			boolean isDisplayed = docLib.verifyLinkIconisnotpresent(fileOrFolderName);

			if (!isDisplayed) {
				report.updateTestLog("Verify Image Icon", "Link Icon is not displayed", Status.PASS);
			}

			else
				report.updateTestLog("Verify Image Icon", "Link Icon is displayed", Status.FAIL);
		} catch (Exception e) {
			report.updateTestLog("Verify Image Icon", "Behaviour is not as expected", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyHTMLComponents() {
		try {
			if (new AlfrescoMyFilesPage(scriptHelper).isHTMLComponentsAvailable()) {
				report.updateTestLog("Verify HTML Editor components",
						"Components verified successfully"
								+ "<br /><b>Componets Verified : </b> TinyMCE4 Editor, Font, Attaching File",
						Status.PASS);
			} else {
				report.updateTestLog("Verify HTML Editor components",
						"Components not available"
								+ "<br /><b>Componets Verified : </b> TinyMCE4 Editor, Font, Attaching File",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify HTML Editor components", "Verify HTML Editor components Failed", Status.FAIL);
		}
	}

	// Check Create File's Field Label
	public void verifyCreateFileFieldLabel(String fieldLabelName) {
		try {

			if (appMyFilesPg.checkFieldLabelInCreateFile(fieldLabelName)) {
				report.updateTestLog("Verify the " + fieldLabelName + " in create file page",
						fieldLabelName + " Label is displayed above the " + fieldLabelName + "field", Status.PASS);
			} else {
				report.updateTestLog("Verify the " + fieldLabelName + " in create file page",
						fieldLabelName + " Label is displayed above the " + fieldLabelName + "field", Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Renamed File
	public void verifyRenamedFile(String fileName) {
		try {
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			isDisplayedUploadedFile = commonMethodForValidateUploadedFile(fileName, uploadedFilesNameList);

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify Renamed File", "Files renamed Successfully<br><b>Expected Result:</b>"
						+ fileName + "" + "<br><b>Actual Result:</b>" + fileName, Status.PASS);
			} else {
				report.updateTestLog("Verify Renamed Files", "Files fialed to rename", Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify folder
	public void verifyFolder(String folderName) {
		try {

			isDisplayedCreatedFolder = validateCreatedFolders(folderName);
			String actualResultVal = getCreatedFolder(folderName);
			String finalActualResult;
			if (actualResultVal == null) {
				finalActualResult = "Folder Not Found";
			} else {
				finalActualResult = actualResultVal;
			}

			if (isDisplayedCreatedFolder) {
				report.updateTestLog("Verify Folder",
						"Folder: " + folderName + " displayed Successfully." + "<br /><b>Expected Result:</b> "
								+ folderName + ", <br /><b>Actual Result:</b> " + finalActualResult + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Folder",
						"Folder: " + folderName + " failed to display" + "<br /><b>Expected Result:</b> " + folderName
								+ ", <br /><b>Actual Result:</b> " + finalActualResult + "",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Created folder
	public void verifyPredefinedFolder(String targetFolderDetails) {
		try {
			ArrayList<String> expectedFolderNameList = new ArrayList<String>();
			expectedFolderNameList = appMyFilesPg.getFolderNames(targetFolderDetails);

			ArrayList<String> actualFolderNameList = new ArrayList<String>();
			actualFolderNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			if (UIHelper.compareTwoDiffSizeOfLists(actualFolderNameList, expectedFolderNameList)) {
				report.updateTestLog("Verify Pre-defined Folders",
						"Folder: " + expectedFolderNameList + " are displayed Successfully."
								+ "<br /><b>Expected Result:</b> " + expectedFolderNameList
								+ ", <br /><b>Actual Result:</b> " + actualFolderNameList + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Pre-defined Folders",
						"Folder: " + expectedFolderNameList + " are failed to display"
								+ "<br /><b>Expected Result:</b> " + expectedFolderNameList
								+ ", <br /><b>Actual Result:</b> " + actualFolderNameList + "",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Created folder
	public void verifyPredefinedFolderForNegativeCase(String targetFolderDetails) {
		try {
			ArrayList<String> expectedFolderNameList = new ArrayList<String>();
			expectedFolderNameList = appMyFilesPg.getFolderNames(targetFolderDetails);

			ArrayList<String> actualFolderNameList = new ArrayList<String>();
			actualFolderNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			if ((UIHelper.compareTwoDiffSizeOfLists(actualFolderNameList, expectedFolderNameList))) {
				report.updateTestLog("Verify Pre-defined Folders",
						"Folder: " + expectedFolderNameList + " are displayed Successfully."
								+ "<br /><b>Expected Result:</b> " + expectedFolderNameList
								+ ", <br /><b>Actual Result:</b> " + actualFolderNameList + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Pre-defined Folders",
						"Folder: " + expectedFolderNameList + " are failed to display"
								+ "<br /><b>Expected Result:</b> " + expectedFolderNameList
								+ ", <br /><b>Actual Result:</b> " + actualFolderNameList + "",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To verify file/Folder Located or not
	public void verifyFileInDocLibPage(String fileName, String fileAppendVal) {
		try {
			String finalFileName;
			if (fileName.contains(".")) {
				String splitVal[] = fileName.split(Pattern.quote("."));
				String part1 = splitVal[0] + fileAppendVal;
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}
			uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();
			String actualName = "File Not Found";
			for (String actualFileName : uploadedFilesNameList) {
				if (actualFileName.contains(finalFileName)) {
					isDisplayedUploadedFile = true;
					actualName = actualFileName;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify file in 'Document Library' page",
						"File: " + finalFileName
								+ " is displayed successfully in 'Document Library' page<br/><b>Expected Result:</b>"
								+ fileName + "<br/><b>Actual Result:</b>" + actualName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify file in 'Document Library' page",
						"File: " + finalFileName
								+ " is failed to display in 'Document Library' page<br/><b>Expected Result:</b>"
								+ fileName + "<br/><b>Actual Result:</b>" + actualName,
						Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
			report.updateTestLog("Find File", e.getMessage(), Status.FAIL);
		}
	}
}
