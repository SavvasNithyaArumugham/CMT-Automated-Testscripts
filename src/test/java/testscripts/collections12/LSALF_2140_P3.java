package testscripts.collections12;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_2140_P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_01_02() {
		testParameters.setCurrentTestDescription("CMT Standards Correlation Reports");

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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);
		String cmtforsinglegrade = dataTable.getData("MyFiles", "MoreSettingsOption2");
		String[] skills = dataTable.getData("MyFiles", "MoreSettingsOption3").split(",");
		
		String[] realizepath = dataTable.getData("MyFiles", "Subfolders1").split("/");
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectName = dataTable.getData("MyFiles", "FileName");
		String moreOptions = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;


		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();

		// go to Course plan
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();		
		
		// Click on Generate Realize Csv for course object
		collectionPg.clickOnBrowseActionsInCollectionUI(objectName, "Generate Realize CSVs");
		collectionPg.clickonrealizebox();
		
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();

		for (String path : realizepath) {
			sitesPage.documentdetails(path);
		}

		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(currentDate);

		// CSVfile presence and filename
		String fileName1 = objectName + "-" + currentDate;
		String filenameCSV = mediaTransPage.RetreiveFilename(fileName1);
		String filename2 = collectionPg.CSVFilename(fileName1);
		collectionPg.clickOnMoreSetting(filename2);
		collectionPg.commonMethodForClickOnMoreSettingsOption(filename2, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);		
		
		report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
		csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filenameCSV);
		String downloadedCSVFileANmeWithPath =downloadFilePath + "/" + filename2;
		try {
			
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[0], 8, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[1], 9, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[2], 10, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[3], 11, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[4], 12, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[5], 13, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[6], 14, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[7], 15, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[8], 16, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[9], 17, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[10], 18, 41);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, skills[11], 19, 41);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sitesPage.enterIntoDocumentLibrary();

		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		//myFiles.uploadFile(downloadFilePath + "/", filename2);
		collectionPg.uploadUpdatedFileInCollectionSite(downloadFilePath, filename2);
		
		UIHelper.waitFor(driver);
		
				
		sitesPage.enterIntoDocumentLibrary();	

		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// Click on Generate Standards Report for course object
				collectionPg.clickOnMoreSetting(objectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(objectName,moreOptions);
				
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();

		for (String path : filePath) {
			sitesPage.documentdetails(path);
		}

		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(currentDate);

		// CSVfile presence and filename
		String fileName2 = "Standards Correlation Report-"+objectName + "-" + currentDate;
		String filename2CSV = mediaTransPage.RetreiveFilename(fileName2);
		String filename22 = collectionPg.CSVFilename(fileName2);
		
		collectionPg.clickOnMoreSetting(filename22);
		collectionPg.commonMethodForClickOnMoreSettingsOption(filename22, "Download");
				
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename22);
		downloadedFile = new File(downloadFilePath + "/" + filename22);		
		csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filename2CSV);
		report.updateTestLog("Verify updated download file", "File: " + filename22 + " downloaded sucessfully", Status.PASS);
		try {
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath,skills[0], 8, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[1], 9, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[2], 10, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[3], 11, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[4], 12, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[5], 13, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[6], 14, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[7], 15, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[8], 16, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[9], 17, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[10], 18, 10);
		CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath, skills[11], 19, 10);
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		

		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
