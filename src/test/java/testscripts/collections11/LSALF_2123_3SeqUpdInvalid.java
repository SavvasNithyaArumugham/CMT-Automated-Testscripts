package testscripts.collections11;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

public class LSALF_2123_3SeqUpdInvalid extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_07() {
		testParameters
		.setCurrentTestDescription("Confirm invalid values produce error on import (space after pipe)");
		
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
				
	
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
	
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");					
		sitesPage.openSiteFromRecentSites(siteNameValue);
			
				sitesPage.enterIntoDocumentLibrary();
				String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
				String filepathfile = dataTable.getData("MyFiles", "uploadpath");
				String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
				String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				String objectName = dataTable.getData("MyFiles", "FileName");
				String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
				Date date = new Date();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				File downloadedFile = null;
				ArrayList<String> csvFileRowDataList = null;
				String invalidValue = dataTable.getData("MyFiles", "BrowseActionName");
				String downloadFilePath = properties.getProperty("DefaultDownloadPath");
				String CourseXpath = "//*[@class='filename']//*[contains(text(),'Course Object 1')]";
				String errorfile = dataTable.getData("MyFiles", "Subfolders1");
				
				// go to Course plan
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
				// Click on Generate Realize Csv for course object
				collectionPg.clickOnBrowseActionsInCollectionUI(objectName, browseActionName);
				collectionPg.clickonrealizebox();
				// Navigate to Document library
				sitesPage.enterIntoDocumentLibrary();

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
					
					String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
					
					
/************************************Modify Genres in CSV and update***********************************/			
					
					try {
						CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, invalidValue ,1 ,78 );
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					report.updateTestLog("Verify library value is updated", "File: " + filename2
							+ " updated sucessfully", Status.PASS);
					sitesPage.enterIntoDocumentLibrary();


					myFiles.openCreatedFolder("Data Imports");
					myFiles.openCreatedFolder("Course Plan");
					collectionPg.uploadUpdatedFileInCollectionSite(downloadFilePath, filename2);
					UIHelper.waitForLong(driver);
					UIHelper.pageRefresh(driver);
					UIHelper.waitForLong(driver);
					UIHelper.pageRefresh(driver);
				    driver.navigate().refresh();
				    
					sitesPage.enterIntoDocumentLibrary();
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder("Data Imports");
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder("Completed");
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(currentDate.substring(0, 4));
					myFiles.openCreatedFolder(currentDate.substring(5, 7));
					myFiles.openCreatedFolder(currentDate.substring(8, 10));
					UIHelper.waitFor(driver);
					collectionPg.clickOnMoreSetting(filename2);
					UIHelper.waitFor(driver);
					
					collectionPg.commonMethodForClickOnMoreSettingsOption(filename2, "View Error Report");
					UIHelper.waitFor(driver);					
					new FileUtil().waitUptoAllFilesDownloadComplete(downloadFilePath,errorfile);

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					homePageObj.openNewTab(downloadFilePath + "\\" + errorfile);	
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					collectionPg.errormessagecontains("Found 1 integrity violations:","Invalid property value");
					
					
				} else {

					report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
				}
				
				
				
				
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
