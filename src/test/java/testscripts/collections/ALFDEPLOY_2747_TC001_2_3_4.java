package testscripts.collections;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author ASHOK
 */
public class ALFDEPLOY_2747_TC001_2_3_4 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_025() {
		testParameters.setCurrentTestDescription(
				"1)The Content Type and File Type property value of 'Sequence' has a capital 'S'.     2)The Media Type property value of 'Lesson' has a capital 'L'.     3}The Course Objects Media Type value of 'Tier' has a capital 'T'.     4}The Player Target property value of 'realize' has a lower case 'r'.");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTest = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		// login
		functionalLibrary.loginAsValidUser(signOnPage);

		// create random site
		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		

		// enter collection UI
		sitesPage.enterIntoDocumentLibrary();
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// enter into default course object
		
		String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);

		// create collections objects
	
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
		collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);

		// navigate back to program in collection UI
	
		driver.navigate().back();
		UIHelper.waitForPageToLoad(driver);
		collectionPg.clickOnEditCollectionButton();

		
		// click generate realize CSV on course and CMT content CSV
		
		
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptionName2 = dataTable.getData("MyFiles", "MoreSettingsOption2");
		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionName);
		UIHelper.waitFor(driver);
		collectionPg.clickonrealizebox();
		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionName2);
		

		// navigate to generated csv file location and download
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Data Exports");
		myFiles.openCreatedFolder("Realize Export");
		myFiles.openCreatedFolder("Course");
		myFiles.openCreatedFolder(myFiles.getUploadedFileOrFolderTitle().get(0));

		// check download on generated realize csv file
		boolean downloadFlag = false;
		boolean downloadFlag2 = false;

		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		ArrayList<String> csvFileRowDataList2 = null;

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		ArrayList<String> generatedFileList = new ArrayList<String>();
		generatedFileList = myFiles.getUploadedFileOrFolderTitle();
		String downloadedCourseFileName = "";

		for (String file : generatedFileList) {

			if (file.contains("Course")) {
				downloadedCourseFileName = file;
				new FileUtil().deleteFileWithContainsTxtInDownloadPath(downloadFilePath, "Course");
				sitesPage.commonMethodForPerformBrowseOption(file, "Download");
				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, file);
				downloadedFile = new File(downloadFilePath + "/" + file);
				if (downloadedFile.exists() && !file.equalsIgnoreCase("File Not Found")) {

					report.updateTestLog("Verify download file", "File: " + file + " downloaded sucessfully",
							Status.PASS);
					downloadFlag = true;
					csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + file);
				} else {

					report.updateTestLog("Verify download file", "File: " + file + " failed to download", Status.FAIL);
				}
			} else {

				System.out.println(file + "Not expected File");
			}

			if (file.contains("Standards")) {
				new FileUtil().deleteFileWithContainsTxtInDownloadPath(downloadFilePath, "Standards");
				sitesPage.commonMethodForPerformBrowseOption(file, "Download");
				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, file);
				downloadedFile = new File(downloadFilePath + "/" + file);
				if (downloadedFile.exists() && !file.equalsIgnoreCase("File Not Found")) {

					report.updateTestLog("Verify download file", "File: " + file + " downloaded sucessfully",
							Status.PASS);
					downloadFlag2 = true;
					csvFileRowDataList2 = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + file);

				} else {

					report.updateTestLog("Verify download file", "File: " + file + " failed to download", Status.FAIL);
				}
			} else {

				System.out.println(file + "Not expected File");
			}
		}

		if (downloadFlag) {

			for (String csvRow : csvFileRowDataList) {
				String splitedRow[] = null;
				splitedRow = csvRow.replace("\"", "").replace("", " ").split(",");		
				
				if (splitedRow[0].replace(" ", "").contains("true") && splitedRow[13].contains(" ")) {
					report.updateTestLog("Root: " + splitedRow[0].replace(" ", ""), "PD: " + splitedRow[13],
							Status.PASS);
				} else if (splitedRow[0].replace(" ", "").contains("true") && !splitedRow[13].contains(" ")) {
					report.updateTestLog("Root: " + splitedRow[0].replace(" ", ""), "PD: " + splitedRow[13],
							Status.FAIL);
				}
				if (splitedRow[22].replace(" ", "").contains("Sequence") && splitedRow[22].charAt(1) == 'S') {
					report.updateTestLog("Content Type: " + splitedRow[22].replace(" ", ""),
							"Has " + splitedRow[22].charAt(1) + " as first letter", Status.PASS);
				} else if (splitedRow[22].replace(" ", "").contains("Sequence") && splitedRow[22].charAt(1) != 'S') {
					report.updateTestLog("Content Type: " + splitedRow[22].replace(" ", ""),
							"Has " + splitedRow[22].charAt(1) + " as first letter", Status.FAIL);
				}
				if (splitedRow[23].replace(" ", "").contains("Sequence") && splitedRow[23].charAt(1) == 'S') {
					report.updateTestLog("File Type: " + splitedRow[23].replace(" ", ""),
							"Has " + splitedRow[23].charAt(1) + " as first letter", Status.PASS);
				} else if (splitedRow[23].replace(" ", "").contains("Sequence") && splitedRow[23].charAt(1) != 'S') {
					report.updateTestLog("File Type: " + splitedRow[23].replace(" ", ""),
							"Has " + splitedRow[23].charAt(1) + " as first letter", Status.FAIL);
				}
				if (splitedRow[24].replace(" ", "").contains("Lesson") && splitedRow[24].charAt(1) == 'L') {
					report.updateTestLog("Media Type: " + splitedRow[24].replace(" ", ""),
							"Has " + splitedRow[24].charAt(1) + " as first letter", Status.PASS);
				} else if (splitedRow[24].replace(" ", "").contains("Lesson") && splitedRow[24].charAt(1) != 'L') {
					report.updateTestLog("Media Type: " + splitedRow[24].replace(" ", ""),
							"Has " + splitedRow[24].charAt(1) + " as first letter", Status.FAIL);
				}
				if (splitedRow[24].replace(" ", "").contains("Tier") && splitedRow[24].charAt(1) == 'T') {
					report.updateTestLog("Media Type: " + splitedRow[24].replace(" ", ""),
							"Has " + splitedRow[24].charAt(1) + " as first letter", Status.PASS);
				} else if (splitedRow[24].replace(" ", "").contains("Tier") && splitedRow[24].charAt(1) != 'T') {
					report.updateTestLog("Media Type: " + splitedRow[24].replace(" ", ""),
							"Has " + splitedRow[24].charAt(1) + " as first letter", Status.FAIL);
				}
				if (splitedRow[80].replace(" ", "").contains("realize") && splitedRow[80].charAt(1) == 'r') {
					report.updateTestLog("player Target: " + splitedRow[80].replace(" ", ""),
							"Has " + splitedRow[80].charAt(1) + " as first letter", Status.PASS);
				} else if (splitedRow[80].replace(" ", "").contains("realize") && splitedRow[80].charAt(1) != 'r') {
					report.updateTestLog("player Target: " + splitedRow[80].replace(" ", ""),
							"Has " + splitedRow[80].charAt(1) + " as first letter", Status.FAIL);
				}
				if (splitedRow[6].replace(" ", "").contains("Social Studies")
						&& splitedRow[9].replace(" ", "").contains("Humanities")) {
					report.updateTestLog("Pearsonbu: " + splitedRow[9].replace(" ", ""),
							"for Discipline : Social Studies", Status.PASS);
				} else if (splitedRow[6].replace(" ", "").contains("Social Studies")
						&& !splitedRow[9].replace(" ", "").contains("Humanities")) {
					report.updateTestLog("Pearsonbu: " + splitedRow[9].replace(" ", ""),
							"for Discipline : Social Studies", Status.FAIL);
				}

				if (splitedRow[0].replace(" ", "").contains("Root")) {
					//Modified the splitedRow[] as part of NALS
					if (splitedRow[88].replace(" ", "").trim().equalsIgnoreCase("Asset(s)tolink")) {
						report.updateTestLog("Verify 'Asset(s) to link' column from the CSV",
								"'Asset(s) to link' column displayed successfully", Status.PASS);
					} else {
						report.updateTestLog("Verify 'Asset(s) to link' column from the CSV",
								"'Asset(s) to link' column failed to display in CSV file", Status.FAIL);
					}
					if (splitedRow[89].replace(" ", "").trim().equalsIgnoreCase("Thumbnailtolink")) {
						report.updateTestLog("Verify 'Thumbnail to link' column from the CSV",
								"'Thumbnail to link' column displayed successfully", Status.PASS);
					} else {
						report.updateTestLog("Verify 'Thumbnail to link' column from the CSV",
								"'Thumbnail to link' column failed to display in CSV file", Status.FAIL);
					}
					if (splitedRow[90].replace(" ", "").trim().equalsIgnoreCase("GridThumbnailtolink")) {
						report.updateTestLog("Verify 'Grid Thumbnail to link' column from the CSV",
								"'Grid Thumbnail to link' column displayed successfully", Status.PASS);
					} else {
						report.updateTestLog("Verify 'Grid Thumbnail to link' column from the CSV",
								"'Grid Thumbnail to link' column failed to display in CSV file", Status.FAIL);
					}
					
				}
			}

		}

		if (downloadFlag2) {
			for (String csvRow : csvFileRowDataList2) {
				String splitedRow[] = null;
				splitedRow = csvRow.replace("\"", "").replace("", " ").split(",");

				if (splitedRow[2].replace(" ", "").contains("Parent")
						&& splitedRow[3].replace(" ", "").contains("state_code")
						&& splitedRow[4].replace(" ", "").contains("statedescription")
						&& splitedRow[5].replace(" ", "").contains("Subject")) {
					report.updateTestLog(
							"Between " + splitedRow[2].replace(" ", "") + " and " + splitedRow[5].replace(" ", ""),
							" the standard csv have " + splitedRow[3].replace(" ", "") + " and "
									+ splitedRow[4].replace(" ", ""),
							Status.PASS);
					break;
				} else if (!splitedRow[2].replace(" ", "").contains("Parent") && !splitedRow[3].contains("state_code")
						&& !splitedRow[4].contains("state description") && !splitedRow[5].contains("Subject")) {
					report.updateTestLog(
							"Between " + splitedRow[2].replace(" ", "") + " and " + splitedRow[5].replace(" ", ""),
							" the standard csv have " + splitedRow[3].replace(" ", "") + " and "
									+ splitedRow[4].replace(" ", ""),
							Status.PASS);
				}

			}
		}

		// Uploading doc file for Asset(s) to link
		sitesPage.enterIntoDocumentLibrary();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		collectionPg.uploadFileInCollectionSite(filePath, fileName);

		// Uploading image file for thumbnail link
		String thumbImgFilePath = dataTable.getData("Document_Details", "FilePath");
		String thumbImgFileName = dataTable.getData("Document_Details", "FileName");
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Assets");
		myFiles.openCreatedFolder("Thumbnails");
		myFiles.uploadFile(thumbImgFilePath, thumbImgFileName);

		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + downloadedCourseFileName;
		try {
			//Modified 88,89,90 from 87,88,89 as part of NALS
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, fileName, 5, 88);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, fileName, 7, 88);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, thumbImgFileName, 5, 89);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, thumbImgFileName, 7, 89);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, thumbImgFileName, 5, 90);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, thumbImgFileName, 7, 90);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sitesPage.enterIntoDocumentLibrary();

		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		myFiles.uploadFile(downloadFilePath + "/", downloadedCourseFileName);

		sitesPage.enterIntoDocumentLibrary();

		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		collectionPg.openCollectionObject(collectionObjectName);

		String collectionObjectData = dataTable.getData("MyFiles", "FieldDataForQuickEdit");
		String collectionObjectNames = dataTable.getData("MyFiles", "CollectionObjectAdditionalData");

		if (collectionObjectNames.contains(",")) {
			String splittedCollObjNames[] = collectionObjectNames.split(",");
			if (splittedCollObjNames != null) {
				for (String collObjName : splittedCollObjNames) {
					collectionPg.clickOnBrowseActionsInCollectionUI(collObjName, "Edit Properties");
					collectionPgTest.verifyFieldValuesInEditPropPage(collectionObjectData);
					collectionPg.clickOnCancelBtnInEditPropPage();
				}
			}
		} else {
			collectionPg.clickOnBrowseActionsInCollectionUI(collectionObjectNames, "Edit Properties");
			collectionPgTest.verifyFieldValuesInEditPropPage(collectionObjectData);
			collectionPg.clickOnCancelBtnInEditPropPage();
		}

		collectionPg.clickOnLeftSideParentFolderFromTreeView(folderNames[1]);
		String moreSettingsOptionNameForCourse = dataTable.getData("MyFiles", "MoreSettingsOption3");

		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionNameForCourse);

		collectionPg.waitForCollectionObjInCollViewPage();

		collectionPg.openCollectionObject(collectionObjectName);

		if (collectionObjectNames.contains(",")) {
			String splittedCollObjNames[] = collectionObjectNames.split(",");
			if (splittedCollObjNames != null) {
				for (String collObjName : splittedCollObjNames) {
					collectionPg.clickOnBrowseActionsInCollectionUI(collObjName, "View Details");
					collectionPgTest
							.verifyCollectionObjchildreferencesInViewDetailsPg(fileName + "," + thumbImgFileName);
					collectionPg.backToCollectionObjPage();
				}
			}
		} else {
			collectionPg.clickOnBrowseActionsInCollectionUI(collectionObjectNames, "View Details");
			collectionPgTest.verifyCollectionObjchildreferencesInViewDetailsPg(fileName + "," + thumbImgFileName);
			collectionPg.backToCollectionObjPage();
		}

		if (collectionObjectNames.contains(",")) {
			String splittedCollObjNames[] = collectionObjectNames.split(",");
			if (splittedCollObjNames != null) {
				for (String collObjName : splittedCollObjNames) {
					collectionPg.clickOnLeftSideFolderFromTreeView(collObjName);
					collectionPgTest.verifyFile(fileName, "'Auto asseted'");
				}
			}
		} else {
			collectionPg.clickOnLeftSideFolderFromTreeView(collectionObjectNames);
			myFilesTest.verifyFileInDocLibPage(fileName, "'Auto asseted'");
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}