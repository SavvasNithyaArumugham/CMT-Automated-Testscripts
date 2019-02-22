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

public class LSALF_1813_13_P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_01_02() {
		testParameters.setCurrentTestDescription("Confirm that In the Print Product CSV I can remove one or more parent nodes in order to effect a partial CSV update. I can modify some property value, and import the new CSV and once completed I can view the CSV in the completed folder and confirm that no unexpected errors are reported in the partial update.");

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
		
		String collectionObjectData = dataTable.getData("MyFiles", "FieldDataForQuickEdit");
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
		String filepathfile = dataTable.getData("MyFiles", "uploadpath");
		String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectName = dataTable.getData("MyFiles", "FileName");
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		String input = dataTable.getData("MyFiles", "BrowseActionName");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);

		// Create site

		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		//sitesPage.openRecentSite("AutoCollectionTestSite1812_P1_2080250119184429");

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
		collectionPg.clickOnMoreSetting("Batch 4 Test Course");
		collectionPg.commonMethodForClickOnMoreSettingsOption("Batch 4 Test Course", "Generate Print Plan CSV");
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
				
/*				for(int j=7;j<9;j++) {

					for(int i=0;i<31;i++) {
					CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, input ,j ,i );
					}
				}*/
				//Display Sequence change
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "*10", 2, 1);
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "*30", 5, 1);
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "*20",6 , 1);
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "*40", 7, 1);
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "*50", 8, 1);
				//Content Object property value updates
				//Discipline Literature
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Literature", 6, 2);
				//Language Spanish
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Spanish", 6, 3);
				//Title Content object-CSVUpdated
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Content object-CSVPartialUpdated", 6, 10);
				//Keywords - ABCD
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "1234", 6, 24);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sitesPage.enterIntoDocumentLibrary();

			myFiles.openCreatedFolder("Data Imports");
			myFiles.openCreatedFolder("Course Plan");

			myFiles.uploadFile(downloadFilePath + "/", filename2);

			UIHelper.waitFor(driver);
			
			sitesPage.enterIntoDocumentLibrary();

			myFiles.openCreatedFolder(folderNames[0]);
			myFiles.openCreatedFolder(folderNames[1]);
			collectionPg.clickOnEditCollectionButton();

			collectionPg.openCollectionObject("Batch 4 Test Course");
			
			collectionPg.clickOnMoreSetting("Content object-CSV generated");
			collectionPg.commonMethodForClickOnMoreSettingsOption("Content object-CSV generated",
					"View Details");
			UIHelper.waitFor(driver);
			
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[2]/div/div[2]/div[1]/div/span[2]","Literature");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[3]/div/div[2]/div[1]/div/span[2]", "Spanish");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[1]/div[3]/div/span[2]", "Content object-CSVPartialUpdated");
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[1]/div[9]/div/span[2]","1234");
		} else {
			report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
