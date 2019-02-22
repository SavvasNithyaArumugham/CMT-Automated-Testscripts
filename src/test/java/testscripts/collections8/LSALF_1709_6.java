package testscripts.collections8;

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

public class LSALF_1709_6 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_06() {
		testParameters
				.setCurrentTestDescription("Confirm CSV update of Genres property with multiple pipe-separated values");
		
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
		String filepathfile = dataTable.getData("MyFiles", "uploadpath");
		String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectName = dataTable.getData("MyFiles", "FileName");
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		String updatedGenres = dataTable.getData("MyFiles", "BrowseActionName");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String CourseXpath = "//*[@class='filename']//*[contains(text(),'Course Object 1')]";
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
	
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");					
		sitesPage.createSite(siteNameValue, "Yes");

				// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();

				// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

				// upload course plan
				collectionPg.uploadFileInCollectionSite(filepathfile, filepathfilename);

				// verify import process progress
				collectionPg.verifyImportProcessProgress(filepathfilename);				
				sitesPage.enterIntoDocumentLibrary();
				
				
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
					/***************************Confirm Genres Leveled Reader properties are exporting properly*********************/
					String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
					try {
						if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"Fantasy|Fiction",2,77))
							{
							report.updateTestLog("Confirm Leveled Reader property Genres is exporting properly ",
									"Column BZ (Genres) value for 'Leveled Reader Test - Valid Genres' is Fantasy|Fiction", Status.PASS);
							}else {

								report.updateTestLog("Confirm Leveled Reader property Genres is exporting properly ",
										"Column BZ (Genres) value for 'Leveled Reader Test - Valid Genres' is not Fantasy|Fiction", Status.FAIL);
							}
					} catch (IOException e) {
					
						e.printStackTrace();
					}
					
/************************************Modify Genres in CSV and update***********************************/			
					
					try {
						CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, updatedGenres ,2 ,77 );
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					report.updateTestLog("Verify Genres value is updated", "File: " + filename2
							+ " updated sucessfully", Status.PASS);
					
					// Import the updated CSV File
					
					// Go to collection UI
					sitesPage.enterIntoDocumentLibrary();

					//go to Course plan
					myFiles.openCreatedFolder("Data Imports");
					myFiles.openCreatedFolder("Course Plan");
							
					// upload course plan
					
					collectionPg.uploadUpdatedFileInCollectionSite(downloadFilePath, filename2);
					UIHelper.waitForLong(driver);
					UIHelper.pageRefresh(driver);
					UIHelper.waitForLong(driver);
					UIHelper.pageRefresh(driver);
				    driver.navigate().refresh();
				    
				    
				 // Go to Courses and navigate to Content Type
					sitesPage.enterIntoDocumentLibrary();
					// go to Course plan
					myFiles.openCreatedFolder(folderNames[0]);
					myFiles.openCreatedFolder(folderNames[1]);
					collectionPg.clickOnEditCollectionButton();
					UIHelper.click(driver, CourseXpath);
					UIHelper.waitForPageToLoad(driver);
					collectionPg.clickOnMoreSetting("Content Object 1");
					UIHelper.waitFor(driver);
					collectionPg.commonMethodForClickOnMoreSettingsOption("Content Object 1","View Details");
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValueINviewDetails("Genres:", "Nonfiction,Mystery");	
					UIHelper.waitFor(driver);

					
					
				} else {

					report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
				}
				
				
				
				
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
