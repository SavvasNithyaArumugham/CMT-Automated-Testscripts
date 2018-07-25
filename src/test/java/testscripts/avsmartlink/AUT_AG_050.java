package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.io.File;
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
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_050 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription("1.Verify user can download excel spreadsheet template-sl_tablelink_template.csv available in public site for table Smart Link type"
				+"<br>2.Verify downloaded excel spreadsheet template-sl_tablelink_template.csv from public site has Title,External URL Link,Image Preview,Image Ref url,Alt Tag,Caption Text,Copyright or Credit Text column along with Status, NodeRef"
				+"<br>3.Verify new smart links are created in folders based on the spreadsheet values on selecting Create Multi Smart Link option for the valid sl_tablelink_template.csv file"
				+"<br>4.Verify the created Smart Link folders for Table link have Smart Link object appropriately"
				+"<br>5.Verify error in the status column for the second row is displayed when the Title is given the same name twice in the spreadsheet for which the multi smart link is created"
				+"<br>6.Verify user can directly upload the spreadsheet into Alfresco and is able to create multi smart link for the valid rows"
				+"<br>7.Verify smart link object is not created when image preview field is filled with multiple image previews"
				+"<br>8.Verify if Images are already in Alfresco, then a site URL path is provided in the Image Preview field of spreadsheet"
				+"<br>9.Verify smart link object not created when Image preview field provided with a site URL path from diff env or repo and check the status column in the csv updated with Error - Image is not available.Reverted"
				+"<br>10.Verify user can provide image preview filename field as filename.png when we have preview images in the same location where we have the spreadsheet"
				+"<br>11.Verify user can download excel spreadsheet template-sl_3rd_party_template.csv available in Pearson Google Sheets for 3rd party interactive Smart Link type"
				+"ALFDEPLOY-3995_Verify the error in csv when table smart link template is added with extra column and create multi smart link"
				);
		
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
		String sitetarget = dataTable.getData("Sites", "TargetSiteName");
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String image = dataTable.getData("MyFiles", "Version");
		String image2 = dataTable.getData("MyFiles", "Version");
		String smartlinks = dataTable.getData("MyFiles", "CreateFileDetails");
		String headerlist = dataTable.getData("MyFiles", "RelationshipName");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		

		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		
		ArrayList<String> smartLinkList = new ArrayList<String>();
		smartLinkList = myFiles.getFileNames(smartlinks);
		
		ArrayList<String> header = new ArrayList<String>();
		header = myFiles.getFileNames(headerlist);

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		functionalLibrary.loginAsValidUser(signOnPage);
	/*	
		sitesPage.siteFinder(sitetarget);
		
		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, image2);*/

		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFiles.uploadFileInMyFilesPage(filePath, image);

		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption);

		

		for (String name : smartLinkList) {
if(name.equalsIgnoreCase("TableLink1") || name.equalsIgnoreCase("TableLink2") ){
	sitesPage.documentdetails(name);
	myFilesTestObj.verifyUploadedFile(name, "");
	sitesPage.enterIntoDocumentLibraryWithoutReport();
	}
			
		}

		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, fileName);
		sitesPage.commonMethodForPerformBrowseOption(fileName, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, fileName);
		downloadedFile = new File(downloadFilePath + "/" + fileName);
		if (downloadedFile.exists() && !fileName.equalsIgnoreCase("File Not Found")) {
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

	}

	@Override
	public void tearDown() {

	}
}
