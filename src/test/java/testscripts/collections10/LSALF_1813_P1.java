package testscripts.collections10;

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
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1813_P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_01_02() {
		testParameters.setCurrentTestDescription("Confirm the CSV will automatically move to the Completed folder, when a valid Print Product CSV with new descriptors is placed into the Data Imports > Course Plan folder.");

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
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		
		

		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);

		// Create site

		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "No");
		//sitesPage.openRecentSite("AutoCollectionTestSite1812_P1_2080250119184429");

		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();

		// go to Course plan
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		//String collectionObjectData = dataTable.getData("MyFiles", "FieldDataForQuickEdit");
				String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
				String filepathfile = dataTable.getData("MyFiles", "uploadpath");
				String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
				String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				String objectName = dataTable.getData("MyFiles", "FileName");
			//	String allProperties = ".//a[contains(text(),'All Properties...')]";
				Date date = new Date();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				File downloadedFile = null;
				ArrayList<String> csvFileRowDataList = null;

				String downloadFilePath = properties.getProperty("DefaultDownloadPath");
				
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
		collectionPg.clickOnMoreSetting("Batch 4 Test Course");
		collectionPg.commonMethodForClickOnMoreSettingsOption("Batch 4 Test Course", "Generate Print Plan CSV");
		collectionPg.clickonprintcsvrealizebox();
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();

		for (String path : filePath) {
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
		if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filenameCSV);
			String downloadedCSVFileANmeWithPath =downloadFilePath + "/" + filename2;
			try {
				//Update the value for Sequenc Object for the Level Incrementing Property
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Inherit from previous (Number, Do no increment)", 2, 15);
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Inherit from previous (Number, Do no increment)", 5, 15);
				//Content Object property value updates
				//Discipline Literature
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Literature", 6, 2);
				//Language Spanish
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Spanish", 6, 3);
				//Grade Numbers PK
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "PK", 6, 4);
				//Title Content object-CSVUpdated
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Content object-CSVUpdated", 6, 10);
				//Product Type iBook
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "iBook", 6, 11);
				//Release Version
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "1", 6, 14);
				//Skills Alike and Different
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Alike and Different", 6, 15);
				//Folio Start - 1
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "1", 6, 17);
				//Folio Prefix - A-
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "A-", 6, 18);
				//Folio Style A - Upper Alpha
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "1 - Arabic", 6, 19);
				//Folio Special  - Forward Interleaf
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Forward Interleaf", 6, 20);
				//TOC Include From - Level 1
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Level 1", 6, 22);
				//TOC Include To - Level 1
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Level 1", 6, 23);
				//Content Object Aggregation Type - Additional Answers
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Additional Answers", 6, 24);
				//Keywords - ABCD
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "ABCD", 6, 25);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sitesPage.enterIntoDocumentLibrary();

			myFiles.openCreatedFolder("Data Imports");
			myFiles.openCreatedFolder("Course Plan");

			collectionPg.uploadUpdatedFileInCollectionSite(downloadFilePath, filename2);

			sitesPage.enterIntoDocumentLibrary();

			myFiles.openCreatedFolder(folderNames[0]);
			myFiles.openCreatedFolder(folderNames[1]);
			collectionPg.clickOnEditCollectionButton();

			collectionPg.openCollectionObject("Batch 4 Test Course");
			
			collectionPg.clickOnMoreSetting("Content object-CSV generated");
			collectionPg.commonMethodForClickOnMoreSettingsOption("Content object-CSV generated",
					"View Details");
			UIHelper.waitFor(driver);
			/*UIHelper.click(driver, allProperties);
			UIHelper.waitFor(driver);
			collectionPgTest.verifyFieldValuesInAllPropPage(collectionObjectData);*/
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[2]/div/div[2]/div[1]/div/span[2]","Literature");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[3]/div/div[2]/div[1]/div/span[2]", "Spanish");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[1]/div[3]/div/span[2]", "Content object-CSVUpdated");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[1]/div[6]/div/span[2]","iBook");
			//collectionPg.verifyObjectsInViewDetails("","Alike and Different");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[7]/div/div[2]/div[3]/div/span[2]","1");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[7]/div/div[2]/div[1]/div/span[2]","A-");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[7]/div/div[2]/div[2]/div/span[2]","1 - Arabic");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[7]/div/div[2]/div[4]/div/span[2]","Forward Interleaf");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[7]/div/div[2]/div[5]/div/span[2]","Level 1");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[7]/div/div[2]/div[6]/div/span[2]","Level 1");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[7]/div/div[2]/div[7]/div/span[2]","Additional Answers");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[1]/div[9]/div/span[2]","ABCD");		
		} else {
			report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
