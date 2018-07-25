package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_010 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				"Verify on applying Multi Smartlink , multiple content objects are generated successfully when uploading excel spreadsheet template with different type of external URL Links and 'Status' field in downloaded csv file from alfresco is updated with 'Success' message . <br>"
						+ "2. Verify on applying Multi Smartlink , multiple content objects are generated successfully when uploading excel spreadsheet template without providing Image Preview,Image Reference, Alt Tag, Caption Text, Copyright or Credit Text and 'Status' field in downloaded csv file is updated with 'Success' message .<br>"
						+ "3. Verify on applying Multi Smartlink , multiple content objects are generated successfully when uploading excel spreadsheet template without providing Image Preview,Image Reference, Alt Tag, Caption Text, Copyright or Credit Text and 'Status' field in downloaded csv file is updated with 'Success' message . <br>"
						+ "4. Verify on applying Multi Smartlink , smartlink object is not generated when uploading csv spreadsheet template by providing value in image preview field but image is not present in the folder and check 'status' field in processed csv file updated with 'error' message.<br>"
						+ "5. Verify on applying Multi Smartlink , content object is generated when uploading excel spreadsheet template by not providing value in image preview field but image is present in the folder and check 'Status' field in downloaded csv file is updated with 'Success' message .<br>"
						+ "6. Verify the error in csv when image preview field filled with image filename without extension upon creating multi smart link. <br>");

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
		String lists = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		


		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		ArrayList<String> List = new ArrayList<String>();
		List = myFiles.getFileNames(lists);
		
		ArrayList<String> smartLink = new ArrayList<String>();
		smartLink = myFiles.getFileNames(smartlinks);
		
	

		docLibPg.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		sitesPage.documentdetails(fileName);

		docDetailsPageObj.performUnzipDocAction(extractTo);
		sitesPage.enterIntoDocumentLibrary();

		sitesPage.clickOnMoreSetting(csvName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(csvName, moreSettingsOption);

		for (String name : smartLink) {

			sitesPage.documentdetails(name);
			myFilesTestObj.verifyUploadedFile(name, "");
			sitesPage.enterIntoDocumentLibraryWithoutReport();
		}

		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, csvName);
		sitesPage.commonMethodForPerformBrowseOption(csvName, "Download");
		
		
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, csvName);
		downloadedFile = new File(downloadFilePath + "/" + csvName);
		if (downloadedFile.exists() && !csvName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + csvName + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + csvName);
			int iteration = 0;
			for (String csvRow : csvFileRowDataList) {
				if(iteration == 0){
					iteration++;
					continue;
				}

				String splitedRow[] = null;
				splitedRow = csvRow.split(",");
				
			

				if (splitedRow[0].replace("\"", "").contains(List.get(0))
						&& splitedRow[6].replace("\"", "").equals("Success")) {
					report.updateTestLog("Verify Multi smart Link with different type of external URL Links", "Multi smart Link with different type of external URL Links are created with excel upload successfully and status in csv is updated. Status: " + splitedRow[6], Status.PASS);
					continue;
				}
				
				if (splitedRow[0].replace("\"", "").contains(List.get(1))
						&& splitedRow[6].replace("\"", "").equals("Success")) {
					report.updateTestLog("Verify Multi smart Link without providing Image Preview,Image Reference, Alt Tag, Caption Text, Copyright or Credit Text ",
							"Multi smart Link without providing Image Preview,Image Reference, Alt Tag, Caption Text, Copyright or Credit Text  are created with excel upload successfully and status in csv is updated. Status: " + splitedRow[6], Status.PASS);
					continue;
				}
				System.out.println(splitedRow[0].replace("\"", ""));
				if (splitedRow[0].replace("\"", "").contains(List.get(2))
						&& splitedRow[6].replace("\"", "").contains("Error")) {
					report.updateTestLog("Verify Multi smart Link are not created by providing value in image preview field but image is not present in the folder",
							"Multi smart Link  by providing value in image preview field but image is not present in the folder  are not created with excel upload successfully and status in csv is updated. Status: " + splitedRow[6], Status.PASS);
					continue;
				}
				
				if (splitedRow[0].replace("\"", "").contains(List.get(3))
						&& splitedRow[6].replace("\"", "").equals("Success")) {
					report.updateTestLog("Verify Multi smart Link by not providing value in image preview field but image is present in the folder",
							"Multi smart Link  by not providing value in image preview field but image is present in the folder  are created with excel upload successfully and status in csv is updated. Status: " + splitedRow[6], Status.PASS);
					continue;
				}
				
				if (splitedRow[0].replace("\"", "").contains(List.get(5))
						&& splitedRow[6].replace("\"", "").contains("Unsupported format")) {
					report.updateTestLog("Verify Multi smart Link when image preview field filled with image filename without extension",
							"Multi smart Link  when image preview field filled with image filename without extension are not created with excel upload successfully and status in csv is updated. Status: " + splitedRow[6], Status.PASS);
					continue;
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
