package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_016 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_040() {
		testParameters
				.setCurrentTestDescription("1.ALFDEPLOY-4417_Verify the URL will be added in the Generated realize CSV after the Updated Asset once it was added as child for the content object.");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);

		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		String moreSettingsOptionName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		String moreSettingsOptionName1 = dataTable.getData("MyFiles",
				"MoreSettingsOption2");
		String link = dataTable.getData("MyFiles", "Subfolders1");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails")
				.split("/");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;

		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);

		// Create site
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteassertValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();

		siteName = siteName.toLowerCase();

		// upload Thumbnails files
		sitesPage.enterIntoDocumentLibrary();

		myFiles.openCreatedFolder("Assets");
		myFiles.openCreatedFolder("Thumbnails");

		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		sitesPage.documentdetails(fileName);

		docDetailsPageObj.editFileProperties();
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// enter into default course object
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);

		// create collections objects
		String createObjectData = dataTable.getData("MyFiles",
				"CollectionObjectBasicData");
		collectionPg
				.createBasicCollectionObjectFromCreateMenu(createObjectData);

		// verify Grid Thumbnail to link: ,Thumbnail to link: for content,
		// composite, learning bundel, sequence
		ArrayList<String> listOfObjects = new ArrayList<String>();
		listOfObjects = collectionPg.getFoldersFromRightPanInCollectionUi();

		for (String listOfObjectsString : listOfObjects) {
			if (listOfObjectsString.contains("AutoContentObj")
					|| listOfObjectsString.contains("AutoCompositeObj")
					|| listOfObjectsString.contains("AutoSeqObj")
					|| listOfObjectsString.contains("AutoLearningBundle")) {
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						listOfObjectsString, "Edit Properties");
				UIHelper.waitFor(driver);

				if (listOfObjectsString.contains("AutoContentObj")) {
					collectionPg.enterCollectionObjectA2LData(
							"Asset(s) to link:", fileName);
				}

				/*
				 * collectionPg.enterCollectionObjectA2LData(
				 * "Grid Thumbnail to link:", link);
				 * collectionPg.enterCollectionObjectA2LData
				 * ("Thumbnail to link:", link);
				 */

				UIHelper.waitFor(driver);

				collectionPg.clickOnSaveBtn();
			}
		}

		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		// Click auto link assets on parent course
		driver.navigate().back();
		UIHelper.waitForPageToLoad(driver);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(
				collectionObjectName, moreSettingsOptionName);

		// Click on Generate Realize Csv for course object
		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(
				collectionObjectName, moreSettingsOptionName1);
		collectionPg.clickonrealizebox();
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();

		for (String path : filePath1) {
			sitesPage.documentdetails(path);
		}

		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(currentDate);

		// CSVfile presence and filename
		String fileName1 = collectionObjectName + "-" + currentDate;
		String filename2 = collectionPg.CSVFilename(fileName1);

		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,
				filename2);

		sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");

		new FileUtil()
				.waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);
		if (downloadedFile.exists()
				&& !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2
					+ " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil()
					.readLinesOfDataFromCSVFile(downloadFilePath + "/"
							+ filename2);
			boolean flag = false;
			String value = null;
			for (String csvRow : csvFileRowDataList) {

				String splitedRow[] = null;
				splitedRow = csvRow.split(",");

				value = splitedRow[46].replace("\"", "");

				if (value != null && value.contains("https")) {
					flag = true;

				}

				else {
					flag = false;

				}

			}

			if (flag) {
				report.updateTestLog("Verify URL ios available", "URL: "
						+ value, Status.PASS);

			} else {
				report.updateTestLog("Verify URL is available",
						"URL is not available", Status.FAIL);
			}

		}

		

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
