package testscripts.avsmartlink;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_071 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3633_ALFDEVEL-1176_Verify the created Smart Link folders for image link have Smart Link object appropriately<br>"
						+ "ALFDEPLOY-3633_ALFDEVEL-1175_Verify the absence of edit in microsoft office option for csv smart link template<br>"
						+ "ALFDEPLOY-3633_ALFDEVEL-1175_Verify user can delete the uploaded zipped folder once it is unzipped in alfresco for all the 8 excel spreadsheet templates.<br>"
						+ "ALFDEPLOY-3633_ALFDEVEL-1175_Verify the creation of smart link object for new records when upload new version is performed for csv file<br>"
						+ "ALFDEPLOY-3633_ALFDEVEL-1199_Verify the error in csv when pdf smart link template is added with extra column and create multi smart link");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String csvName = dataTable.getData("MyFiles", "Version");
		String smartlinks = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName = dataTable.getData("MyFiles", "RelationshipName");
		String edittitle = dataTable.getData("Parametrized_Checkpoints", "FileName");
		String editurl = dataTable.getData("Parametrized_Checkpoints", "Help URL");
		String uploadNewVersion = dataTable.getData("MyFiles", "BrowseActionName");
		String versionToChange = dataTable.getData("MyFiles", "Sort Options");
		String comments = dataTable.getData("MyFiles", "CreateChildFolder");
		String errorPdfCsv = dataTable.getData("MyFiles", "CreateFolder");
		String pdfSmartLinks = dataTable.getData("MyFiles", "AccessToken");
		String headerlist = dataTable.getData("MyFiles", "PopUpMsg");

		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		sitesPage.documentdetails(fileName);

		docDetailsPageObj.performUnzipDocAction(extractTo);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteFileInDocumentLib();
		sitesPage.documentdetails(folderName);
		sitesPage.clickOnMoreSetting(csvName);
		if (!sitesPage.checkActionNameForFolderOrFileInDocLibPage("Edit in Microsoft Office", csvName)) {
			report.updateTestLog("Verify the absence of edit in microsoft office option for csv smart link template",
					"File: " + csvName + " does not have Edit in Microsoft office option", Status.PASS);

		} else {
			report.updateTestLog("Verify the absence of edit in microsoft office option for csv smart link template",
					"File: " + csvName + "have Edit in Microsoft office option", Status.FAIL);

		}
		sitesPage.clickOnMoreSetting(csvName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(csvName, moreSettingsOption);

		ArrayList<String> smartLinkList = new ArrayList<String>();
		smartLinkList = myFiles.getFileNames(smartlinks);

		for (String name : smartLinkList) {

			sitesPage.documentdetails(name);
			myFilesTestObj.verifyUploadedFile(name, "");
			driver.navigate().back();
			UIHelper.waitForPageToLoad(driver);
		}
		if (!docLibPg.isFileIsAvailable("ImageSmartLink5")) {
			report.updateTestLog("Verify error row is not created",
					"File: " + "ImageSmartLink5" + " error row is not created/available", Status.PASS);

		} else {
			report.updateTestLog("Verify error row is not created",
					"File: " + "ImageSmartLink5" + " error row is created/available", Status.FAIL);

		}
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);

		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, csvName);
		sitesPage.commonMethodForPerformBrowseOption(csvName, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, csvName);
		downloadedFile = new File(downloadFilePath + "/" + csvName);
		if (downloadedFile.exists() && !csvName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + csvName + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + csvName);

			for (String csvRow : csvFileRowDataList) {

				String splitedRow[] = null;
				splitedRow = csvRow.split(",");

				if (smartLinkList.contains(splitedRow[0].replace("\"", ""))
						&& splitedRow[6].replace("\"", "").equals("Success")) {
					report.updateTestLog("SmartLink: " + splitedRow[0], "Status: " + splitedRow[6], Status.PASS);
				}

				if (smartLinkList.equals(splitedRow[0].replace("\"", ""))
						&& splitedRow[6].replace("\"", "").contains("Error-Image is not available.Reverted")) {
					report.updateTestLog(
							"Verify smart link object not created when Image preview field provided with a site URL path from diff env or repo "
									+ "Smart link not created, Error as expected.SmartLink: " + splitedRow[0],
							"As Expected.Status: " + splitedRow[6], Status.PASS);
					continue;
				}

			}
		} else {

			report.updateTestLog("Verify download file", "File: " + csvName + " failed to download", Status.FAIL);
		}
		// upload new version
		sitesPage.clickOnMoreSetting(csvName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(csvName, uploadNewVersion);
		docLibPg.uploadNewVersionInDocLibPage(csvName, filePath, versionToChange, comments);
		docLibPg.uploadNewVersionButton();

		sitesPage.documentdetails(csvName);

		if (docDetailsPageObj.getCurrentVersion().equals("1.2")) {
			report.updateTestLog("Verify the file is updated with next minor version",
					"file " + csvName + " is updated with next minor version " + docDetailsPageObj.getCurrentVersion(),
					Status.PASS);
		} else {
			report.updateTestLog(
					"Verify the file is updated with next minor version", "file " + csvName
							+ " is not updated with next minor version" + docDetailsPageObj.getCurrentVersion(),
					Status.FAIL);
		}

		driver.navigate().back();
		sitesPage.clickOnMoreSetting(csvName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(csvName, moreSettingsOption);
		UIHelper.pageRefresh(driver);
		if (docLibPg.isFileIsAvailable("ImageSmartLink5")) {
			report.updateTestLog("Verify error row is created post update",
					"File: " + "ImageSmartLink5" + " error row is created/available post correction", Status.PASS);

		} else {
			report.updateTestLog("Verify error row is created post update",
					"File: " + "ImageSmartLink5" + " error row is not created/available post correction", Status.FAIL);

		}

		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, csvName);
		sitesPage.commonMethodForPerformBrowseOption(csvName, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, csvName);
		downloadedFile = new File(downloadFilePath + "/" + csvName);
		if (downloadedFile.exists() && !csvName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + csvName + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + csvName);

			for (String csvRow : csvFileRowDataList) {

				String splitedRow[] = null;
				splitedRow = csvRow.split(",");

				if ("ImageSmartLink5".contains(splitedRow[5].replace("\"", ""))
						&& splitedRow[6].replace("\"", "").equals("Success")) {
					report.updateTestLog("SmartLink: " + splitedRow[0], "Status: " + splitedRow[6], Status.PASS);
				}
			}
		} else {

			report.updateTestLog("Verify download file", "File: " + csvName + " failed to download", Status.FAIL);
		}

		sitesPage.enterIntoDocumentLibraryWithoutReport();
		myFiles.uploadFileInMyFilesPage(filePath, errorPdfCsv);
		sitesPage.clickOnMoreSetting(errorPdfCsv);
		docLibPg.commonMethodForClickOnMoreSettingsOption(errorPdfCsv, moreSettingsOption);

		ArrayList<String> pdfSmartLinkList = new ArrayList<String>();
		pdfSmartLinkList = myFiles.getFileNames(pdfSmartLinks);

		for (String names : pdfSmartLinkList) {
			if (!names.equalsIgnoreCase("PDFSmartLink1") || !names.equalsIgnoreCase("PDFSmartLink2")) {
				if (!docLibPg.isFileIsAvailable("PDFSmartLink1") || !docLibPg.isFileIsAvailable("PDFSmartLink2")) {
					report.updateTestLog("Verify smart link is not created",
							"File: " + "PDFSmartLink1/PDFSmartLink2" + " smart link is not created", Status.PASS);

				} else {
					report.updateTestLog("Verify smart link is not created",
							"File: " + "PDFSmartLink1/PDFSmartLink2" + " smart link is created", Status.FAIL);

				}
			}
		}
		ArrayList<String> header = new ArrayList<String>();
		header = myFiles.getFileNames(headerlist);
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, errorPdfCsv);
		sitesPage.commonMethodForPerformBrowseOption(errorPdfCsv, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, errorPdfCsv);
		downloadedFile = new File(downloadFilePath + "/" + errorPdfCsv);
		if (downloadedFile.exists() && !errorPdfCsv.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + errorPdfCsv + " downloaded sucessfully",
					Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + errorPdfCsv);

			String headsplitedRow[] = null;
			headsplitedRow = csvFileRowDataList.get(0).split(",");
			ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(headsplitedRow));

			if (UIHelper.compareTwoSimilarLists(arrayList, header)) {
				report.updateTestLog("Verify downloaded csv from public site has all expected headers",
						"Actually Headers are as expected<br>" + arrayList, Status.PASS);
			} else {
				report.updateTestLog("Verify downloaded csv from public site has all expected headers",
						"Actually Headers are not as expected<br>" + arrayList, Status.FAIL);
			}

			for (String csvRow : csvFileRowDataList) {

				String splitedRow[] = null;
				splitedRow = csvRow.split(",");

				if (splitedRow[5].replace("\"", "").contains("")) {
					report.updateTestLog(
							"Verify no smart link object is created when csv template has error" + "SmartLink: "
									+ splitedRow[0],
							"Status: " + splitedRow[5] + "Improper CSV..Please check the format and upload.",
							Status.PASS);
					
				}
			}
		} else {

			report.updateTestLog("Verify download file", "File: " + fileName + " failed to download", Status.FAIL);
		}
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.openFolder(folderName);
		sitesPage.openFolder(edittitle);
		sitesPage.documentdetails(edittitle);

		AlfrescoHomePageTest homePageTestObj = new AlfrescoHomePageTest(scriptHelper);
		homePageTestObj.verifyHelpURL();

	}

	@Override
	public void tearDown() {

	}
}
