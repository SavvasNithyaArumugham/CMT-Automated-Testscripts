package testscripts.collections8;

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

public class LSALF_1704_P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_01_02() {
		testParameters
				.setCurrentTestDescription("Bulk Skills Alignment via CSV Import: "
						+ "\"Confirm valid/invalid skills are/are not aligned on updating a course with error via Data import.\r\n" + 
						"(with skills populated in course CSV)\"");
		
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
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
				
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
		String objectName = dataTable.getData("MyFiles", "FileName");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption2").split(";");
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
	
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");					
		//sitesPage.createSite(siteNameValue, "Yes");
		sitesPage.openSiteFromRecentSites(siteNameValue);
				// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				for (String path : filePath) {
					sitesPage.documentdetails(path);
				}
				
				// Navigate to generated csv file path and check whether
				sitesPage.documentdetails(currentDate);
				
				// CSVfile presence and filename
				String fileName1 = objectName + "-" + currentDate;	
				String filenameCSV= mediaTransPage.RetreiveFilename(fileName1);
				String filename2= collectionPg.CSVFilename(fileName1);
				collectionPg.clickOnMoreSetting(filename2);
				collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"Download");
				
				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				downloadedFile = new File(downloadFilePath + "/" + filename2);
				if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
					csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filenameCSV);
					
/************************************Modify Skills in CSV and update***********************************/			
					String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
					try {
						CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "MTH.100" ,2 ,40 );
						CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "MTH.101|MTH.102|MTH.103" ,3 ,40 );
						CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "MTH.1153|MTH.103|MTH.4046" ,4 ,40 );
						CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "MTH.2133 |MTH.100|MTH.2233_1-1|AGA18_NAG1\\MTH\\MTH.302" ,5 ,40 );						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					report.updateTestLog("Verify Skills value is updated", "File: " + filename2
							+ " updated sucessfully", Status.PASS);
					
					// Import the updated CSV File
					
					// Go to collection UI
					sitesPage.enterIntoDocumentLibrary();
					UIHelper.waitFor(driver);
					//go to Course plan
					myFiles.openCreatedFolder("Data Imports");
					myFiles.openCreatedFolder("Course Plan");
							
					// upload course plan
					
					collectionPg.uploadUpdatedFileInCollectionSite(downloadFilePath, filename2);
					UIHelper.waitForLong(driver);
					UIHelper.pageRefresh(driver);
				    driver.navigate().refresh();

				    sitesPage.enterIntoDocumentLibrary();
				    UIHelper.waitFor(driver);
				    myFiles.openCreatedFolder("Data Imports");
					myFiles.openCreatedFolder("Completed");
					myFiles.openCreatedFolder(currentDate.substring(0, 4));
					myFiles.openCreatedFolder(currentDate.substring(5, 7));
					myFiles.openCreatedFolder(currentDate.substring(8, 10));
				
					//Case 3 : Verify error report to check results 
					String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
					String errorfile = dataTable.getData("MyFiles", "Subfolders1");
					collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"View Error Report");
					UIHelper.waitFor(driver);
					new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath,errorfile);		
					UIHelper.waitFor(driver);		
					homePageObj.openNewTab(fileDownloadPath + "\\" + errorfile);	
					UIHelper.waitFor(driver);
					if(UIHelper.chkForThisStringinUI(driver, "Skill with statement code MTH.100 already exists with a different CMT content ID (Intentionally Incorrect)"))
					{
						report.updateTestLog("Verify Error Report","Skill with statement code MTH.100 already exists with a different CMT content ID (Intentionally Incorrect)", Status.PASS);			
					}else {
						report.updateTestLog("Verify Error Report","Skill with statement code MTH.100 already exists with a different CMT content ID (Intentionally Incorrect)", Status.FAIL);
					}
					UIHelper.waitFor(driver);
					if(UIHelper.chkForThisStringinUI(driver, "Error: Skill with statement code MTH.103 already exists with a different CMT content ID (Intentionally Incorrect)"))		{
						report.updateTestLog("Verify Error Report","Error: Skill with statement code MTH.103 already exists with a different CMT content ID (Intentionally Incorrect)", Status.PASS);
					}else {
						report.updateTestLog("Verify Error Report","Error: Skill with statement code MTH.103 already exists with a different CMT content ID (Intentionally Incorrect)", Status.FAIL);
					}
					UIHelper.waitFor(driver);
					if(UIHelper.chkForThisStringinUI(driver, "Error: Skill with statement code MTH.101 already exists with a different CMT content ID (Intentionally Incorrect)"))		{
						report.updateTestLog("Verify Error Report","Error: Skill with statement code MTH.101 already exists with a different CMT content ID (Intentionally Incorrect)", Status.PASS);
					}else {
						report.updateTestLog("Verify Error Report","Error: Skill with statement code MTH.101 already exists with a different CMT content ID (Intentionally Incorrect)", Status.FAIL);
					}
					UIHelper.waitFor(driver);
					if(UIHelper.chkForThisStringinUI(driver, "Error: Skill with statement code MTH.102 already exists with a different CMT content ID (Intentionally Incorrect)"))		{
						report.updateTestLog("Verify Error Report","Error: Skill with statement code MTH.102 already exists with a different CMT content ID (Intentionally Incorrect)", Status.PASS);
					}else {
						report.updateTestLog("Verify Error Report","Error: Skill with statement code MTH.102 already exists with a different CMT content ID (Intentionally Incorrect)", Status.FAIL);
					}
					UIHelper.waitFor(driver);
					if(UIHelper.chkForThisStringinUI(driver, "AGA18_NAG1\\MTH\\MTH.302 was not aligned"))		{
						report.updateTestLog("Verify Error Report","AGA18_NAG1\\MTH\\MTH.302 was not aligned", Status.PASS);
					}else {
						report.updateTestLog("Verify Error Report","AGA18_NAG1\\MTH\\MTH.302 was not aligned", Status.FAIL);
					}
					UIHelper.waitFor(driver);
					if(UIHelper.chkForThisStringinUI(driver, " MTH.2233_1-1 was not aligned"))		{
						report.updateTestLog("Verify Error Report"," MTH.2233_1-1 was not aligned", Status.PASS);
					}else {
						report.updateTestLog("Verify Error Report"," MTH.2233_1-1 was not aligned", Status.FAIL);
					}
					UIHelper.waitFor(driver);
					if(UIHelper.chkForThisStringinUI(driver, "MTH.4046 was not aligned"))		{
						report.updateTestLog("Verify Error Report","MTH.4046 was not aligned", Status.PASS);
					}else {
						report.updateTestLog("Verify Error Report","MTH.4046 was not aligned", Status.FAIL);
					}
					UIHelper.waitFor(driver);
					/*if(UIHelper.chkForThisStringinUI(driver, "MTH.2133 was not aligned"))		{
						report.updateTestLog("Verify Error Report","MTH.2133 was not aligned", Status.PASS);
					}else {
						report.updateTestLog("Verify Error Report","MTH.2133 was not aligned", Status.FAIL);
					}
					UIHelper.waitFor(driver);*/
				} else {

					report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
				}		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
