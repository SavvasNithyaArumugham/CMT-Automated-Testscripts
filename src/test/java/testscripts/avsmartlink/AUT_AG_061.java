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

public class AUT_AG_061 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(	"ALFDEPLOY-3873_3996_Verify the presence of 3PI vendor and Optimized for use on mobile?"
				+ " columns in the multi smart link csv template of 3rd party interactive link"
				+"ALFDEPLOY-4452_Verify downloaded excel spreadsheet template-sl_3rd_party_template.csv from Pearson Google Sheets has Title,External URL Link,Image Preview,Image Ref url,Alt Tag,Caption Text,Copyright or Credit Text,ThreePIVendor,OptimizedForUseOnMobile column along with Status and NodeRef columns"
				+"ALFDEPLOY-4452_Verify error in the status column for the second row is displayed when the Title is given the same name twice in the spreadsheet for which the multi smart link is created ."
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
if(name.equalsIgnoreCase("ThridPartySmartLink1") ){
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
						&&  splitedRow[4].replace("\"", "").equals("Alt Tag 10") && splitedRow[9].replace("\"", "").contains("Error")) {
					report.updateTestLog("Verify error is displayed when the Title is given the same name twice"+"SmartLink: " + splitedRow[0], "Status: " + splitedRow[9], Status.PASS);
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
