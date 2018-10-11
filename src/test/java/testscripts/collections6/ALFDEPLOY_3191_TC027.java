package testscripts.collections6;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3191_TC027 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_028() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3191_027_Export modified Course"
						+"<br>ALFDEPLOY-3191_028_Confirm all Leveled Reader properties are exporting properly");

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

		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String moreSettingsOptionName = dataTable.getData("MyFiles","MoreSettingsOption");
		String link = dataTable.getData("MyFiles", "Subfolders1");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");
		String[] smartlink = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String Metadata[] = dataTable.getData("MyFiles", "FieldDataForAllProperties").split(";");
		String[] fieldData = dataTable.getData("MyFiles", "FieldDataForQuickEdit").split(";");
		String allProperties1 = ".//a[contains(text(),'All Properties...')]";
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		
		File downloadedFile = null;
		//ArrayList<String> csvFileRowDataList = null;
	
		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);
		UIHelper.waitForLong(driver);
		// Create site
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteassertValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();

		siteName = siteName.toLowerCase();

		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();

		//go to Course plan
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		// upload course plan
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
	    driver.navigate().refresh();
	    
		// Go to Courses and navigate to Content Type
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetailsColl("Courses");
		sitesPage.documentdetailsColl(folderNames[2]);
		collectionPg.clickOnEditCollectionButton();
			
							
		// Edit and save Property values in Edit All Properties form
		
		collectionPg.clickOnMouseOverMenu(folderNames[3],"Edit Properties");
		UIHelper.click(driver, allProperties1);
		collectionPg.confirmPropertyinImportAndSave(folderNames[3],Metadata[0],fieldData[0]);
		collectionPg.clickOnMouseOverMenu(folderNames[4],"Edit Properties");
		UIHelper.click(driver, allProperties1);
		collectionPg.confirmPropertyinImportAndSave(folderNames[4],Metadata[1],fieldData[1]);
		collectionPg.clickOnMouseOverMenu(folderNames[5],"Edit Properties");
		UIHelper.click(driver, allProperties1);
		collectionPg.confirmPropertyinImportAndSave(folderNames[5],Metadata[2],fieldData[2]);
		collectionPg.clickOnMouseOverMenu(folderNames[6],"Edit Properties");
		UIHelper.click(driver, allProperties1);
		collectionPg.confirmPropertyinImportAndSave(folderNames[6],Metadata[3],fieldData[3]);
		collectionPg.clickOnMouseOverMenu(folderNames[7],"Edit Properties");
		UIHelper.click(driver, allProperties1);
		collectionPg.confirmPropertyinImportAndSave(folderNames[7],Metadata[4],fieldData[4]);
		collectionPg.clickOnMouseOverMenu(folderNames[8],"Edit Properties");
		UIHelper.click(driver, allProperties1);
		collectionPg.confirmPropertyinImportAndSave(folderNames[8],Metadata[5],fieldData[5]);
	   
		sitesPage.enterIntoDocumentLibrary();
	    sitesPage.documentdetailsColl(folderNames[0]);
		sitesPage.documentdetailsColl(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitForPageToLoad(driver);

		/***********************************Export modified Course*****************************/
		// Click on Generate Realize Csv for course object
		collectionPg.clickOnMoreSetting(folderNames[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], moreSettingsOptionName);
		collectionPg.clickonrealizebox();
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();

		for (String path : filePath1) {
			sitesPage.documentdetails(path);
		}

		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(currentDate);

		// CSVfile presence and filename
		String fileName1 = folderNames[2] + "-" + currentDate;
		String filename2 = collectionPg.CSVFilename(fileName1);

		System.out.println("filename1 : " + fileName1); 
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,
				filename2);

		sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");

		new FileUtil()
			.waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);
		if (downloadedFile.exists()
				&& !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2
					+ " downloaded sucessfully", Status.PASS);
		}
		
		/***************************Confirm all Leveled Reader properties are exporting properly*********************/
		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
		try {
			if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"500",2,69))
				{
				report.updateTestLog("Confirm all Leveled Reader properties are exporting properly ",
						"Column BR (Lexile) value for 'Leveled Reader Test - Valid Lexile' is 500", Status.PASS);
				}
			if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"100",3,70))
			{
			report.updateTestLog("Confirm all Guided Reader properties are exporting properly ",
					"Column BS (Guided Reader) value for 'Leveled Reader Test - Guided Reading' is 100", Status.PASS);
			}
			if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"100",5,72))
			{
			report.updateTestLog("Confirm all Reading Maturity Metric properties are exporting properly ",
					"Column BU (Reading Maturity Metric) value for 'Leveled Reader Test - Reading Maturity Metric' is 100", Status.PASS);
			}
			if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"100",6,73))
			{
			report.updateTestLog("Confirm all Quantile properties are exporting properly ",
					"Column BV (Quantile) value for 'Leveled Reader Test - Quantile' is 100", Status.PASS);
			}
			if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"500",10,78))
			{
			report.updateTestLog("Confirm all ISBN properties are exporting properly ",
					"Column BV (ISBN) value for 'Leveled Reader Test - ISBN' is 500", Status.PASS);
			}
			if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"Lorum Ipsum",11,79))
			{
			report.updateTestLog("Confirm all Author properties are exporting properly ",
					"Column BV (Author) value for 'Leveled Reader Test - Author' is Lorum Ipsum", Status.PASS);
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		} 



	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
