package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_051 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				"1.ALFDEPLOY-3995_Verify user can rename the downloaded excel spreadsheet template-sl_tablelink_template.csv with any dummy name prefixed by sl_table<br>"
				+"2. ALFDEPLOY-3995_Verify user can populate excel spreadsheet template-sl_tablelink_template.csv with text field values"		);
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
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);	

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String csvfile = dataTable.getData("MyFiles", "CreateFileDetails");
		String action = dataTable.getData("MyFiles", "BrowseActionName");
		String smartlink = dataTable.getData("MyFiles", "BrowseActionName");
		
		File downloadedFile = null;
		/*ArrayList<String> csvFileRowDataList = null;
		
		ArrayList<String> smartLinkList = new ArrayList<String>();
		smartLinkList = myFiles.getFileNames(smartlinks);
		
		ArrayList<String> header = new ArrayList<String>();
		header = myFiles.getFileNames(headerlist);*/

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		
		String uploadFilePath = properties.getProperty("DefaultUploadPath");
		
		
		
		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + csvfile;

		functionalLibrary.loginAsValidUser(signOnPage);
		
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName, moreSettingsOption);

		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, fileName);
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, csvfile);
		sitesPage.commonMethodForPerformBrowseOption(fileName, action);

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, fileName);
		downloadedFile = new File(downloadFilePath + "/" + fileName);
		
		
		if (downloadedFile.exists() && !fileName.equalsIgnoreCase("File Not Found")) {
		new FileUtil().renamefile(downloadFilePath,fileName,csvfile);
		
		}
	
		try {
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, smartlink, 1, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myFiles.commonMethodForUploadFiles(uploadFilePath, csvfile);
		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption);
		
	
		sitesPage.documentdetails(smartlink);
		myFilesTestObj.verifyUploadedFile(smartlink, "");
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		
		
	/*	if (downloadedFile.exists() && !fileName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + fileName + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + fileName);
			
			String headsplitedRow[] = null;
			headsplitedRow = csvFileRowDataList.get(0).split(",");
			ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(headsplitedRow));
			
			if(UIHelper.compareTwoSimilarLists(arrayList,header)){
				report.updateTestLog("Verify downloaded csv from public site has all expected headers", "Actually Headers are as expected<br>" + arrayList, Status.PASS);
			}else{
				report.updateTestLog("Verify downloaded csv from public site has all expected headers", "Actually Headers are not as expected<br>" + arrayList, Status.FAIL);
			}
	
			

			for (String csvRow : csvFileRowDataList) {

				String splitedRow[] = null;
				splitedRow = csvRow.split(",");

				if (smartLinkList.contains(splitedRow[0].replace("\"", ""))
						&&  splitedRow[4].replace("\"", "").equals("Alt Tag 10") && splitedRow[7].replace("\"", "").contains("Error")) {
					report.updateTestLog("Verify error is displayed when the Title is given the same name twice"+"SmartLink: " + splitedRow[0], "Status: " + splitedRow[7], Status.PASS);
					continue;
				}
				
				if ("TableLink5".equals(splitedRow[0].replace("\"", "")) && splitedRow[7].replace("\"", "").contains("Error")) {
					report.updateTestLog("Verify smart link is not created when image preview field is with multiple image"+"SmartLink: " + splitedRow[0], "Status: " + splitedRow[7], Status.PASS);
					continue;
				}
				
				if ("TableLink6".equals(splitedRow[0].replace("\"", "")) && splitedRow[7].replace("\"", "").contains("Success")) {
					report.updateTestLog("Verify if Images are already in Alfresco, then a site URL path is provided in the Image Preview field "+"SmartLink: " + splitedRow[0], "As Expected.Status: " + splitedRow[7], Status.PASS);
					continue;
				}
				
				if ("TableLink7".equals(splitedRow[0].replace("\"", "")) && splitedRow[7].replace("\"", "").contains("Error")) {
					report.updateTestLog("Verify smart link object not created when Image preview field provided with a site URL path from diff env or repo "+"Smart link not created, Error as expected.SmartLink: " + splitedRow[0], "As Expected.Status: " + splitedRow[7], Status.PASS);
					continue;
				}
				
				if ("TableLink8".equals(splitedRow[0].replace("\"", "")) && splitedRow[7].replace("\"", "").contains("Success")) {
					report.updateTestLog("Verify user can provide image preview filename field when we have preview images in different site "+"Smart link created, working as expected as expected.SmartLink: " + splitedRow[0], "As Expected.Status: " + splitedRow[7], Status.PASS);
					continue;
				}
			}
		} else {

			report.updateTestLog("Verify download file", "File: " + fileName + " failed to download", Status.FAIL);
		}
*/
	}

	@Override
	public void tearDown() {

	}
}
