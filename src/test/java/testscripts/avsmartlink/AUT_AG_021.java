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

public class AUT_AG_021 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				"1.Verify user can add Image, Audio, Video, External Website, 3rd Party Interactive, Metrodigi, MD Pop up, PDF links in CSV all at once and user is able to create separate smart link object for each types via multi smart link<br>"
				+"2. Validate the smart link is updated correctly when Title,external URL,JSON string fields are edited via edit properties for Audio link.");
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

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String csvName = dataTable.getData("MyFiles", "Version");
		String smartlinks = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName = dataTable.getData("MyFiles", "RelationshipName");
		
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
		sitesPage.documentdetails(folderName);
		sitesPage.clickOnMoreSetting(csvName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(csvName, moreSettingsOption);

		ArrayList<String> smartLinkList = new ArrayList<String>();
		smartLinkList = myFiles.getFileNames(smartlinks);

		for (String name : smartLinkList) {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			sitesPage.documentdetails(name);
			myFilesTestObj.verifyUploadedFile(name, "");
			driver.navigate().back();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
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

				if (smartLinkList.contains(splitedRow[0].replace("\"", ""))
						&& splitedRow[7].replace("\"", "").equals("Success")) {
					report.updateTestLog("SmartLink: " + splitedRow[0], "Status: " + splitedRow[7], Status.PASS);
				}
			}
		} else {
			report.updateTestLog("Verify download file", "File: " + csvName + " failed to download", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {

	}
}
