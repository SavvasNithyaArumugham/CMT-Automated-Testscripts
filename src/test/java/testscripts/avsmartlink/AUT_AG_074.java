package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.io.File;
import java.util.ArrayList;

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

public class AUT_AG_074 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3633_ALFDEVEL-1176_Verify the created Smart Link folders for external website link have Smart Link object appropriately.<br>"
						+ "ALFDEPLOY-3633_ALFDEVEL-1175_Verify the absence of edit in microsoft office option for csv smart link template<br>"
						+ "ALFDEPLOY-3633_ALFDEVEL-1175_Verify user can delete the uploaded zipped folder once it is unzipped in alfresco for all the 8 excel spreadsheet templates.");
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
			}
		} else {

			report.updateTestLog("Verify download file", "File: " + csvName + " failed to download", Status.FAIL);
		}

		sitesPage.openFolder(edittitle);
		sitesPage.documentdetails(edittitle);

		AlfrescoHomePageTest homePageTestObj = new AlfrescoHomePageTest(scriptHelper);
		homePageTestObj.verifyHelpURL();

	}

	@Override
	public void tearDown() {

	}
}
