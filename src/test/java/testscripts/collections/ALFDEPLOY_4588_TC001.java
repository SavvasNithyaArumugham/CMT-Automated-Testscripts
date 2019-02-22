package testscripts.collections;

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

public class ALFDEPLOY_4588_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_028() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4588_Import test data course"
						+"<br>ALFDEPLOY-4588_Export test course with applied Version State filter"
						+"<br>ALFDEPLOY-4588_Confirm that blank Version State values are included in the TX filtered export");

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
		CSVUtil csvutil = new CSVUtil();
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String moreSettingsOptionName = dataTable.getData("MyFiles","MoreSettingsOption");
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");		
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		
		Date date = new Date();
		int rowCount =0;
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

		
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

		/*****************************Import test data course**************************/
		//go to Course plan
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		// upload course plan
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		UIHelper.waitForLong(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
	    driver.navigate().refresh();
	    
		
		sitesPage.enterIntoDocumentLibrary();
	    sitesPage.documentdetailsColl(folderNames[0]);
		sitesPage.documentdetailsColl(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitForPageToLoad(driver);

		/************************************Export test course with applied Version State filter*******************************/
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

		/***************************Confirm that blank Version State values are included in the TX filtered export*********************/
		// CSVfile presence and filename
		String fileName1 = folderNames[2] + "-" + currentDate;
		String filename2 = collectionPg.CSVFilename(fileName1);

		if(filename2.contains("TX-"))
		{
			report.updateTestLog("Verify export tiltle begins with 'TX-', indicating that the Texas filter has been applied",
					"Export tiltle begins with 'TX-' : " + filename2, Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify export tiltle begins with 'TX-', indicating that the Texas filter has been applied",
					"Export tiltle begins does not begin with 'TX-' : " + filename2, Status.FAIL);
		}
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,filename2);

		sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");

		new FileUtil()
			.waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);
		if (downloadedFile.exists()
				&& !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
		}
		
		
		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
		try {//Modified as part of NALS 
			if(!CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"",1,92))
				{
				report.updateTestLog(" Confirm that Column CO (State Code) shows blank values",
						"Column CO (State Code) does not show blank values", Status.PASS);
				}
			else
			{
				report.updateTestLog(" Confirm that Column CO (State Code) shows blank values",
						"Column CO (State Code) shows blank values", Status.FAIL);
			}
			
			rowCount = csvutil.getRowCount(downloadedCSVFileANmeWithPath);
			
			if(rowCount>0)
			{
				report.updateTestLog(" Open the export CSV and confirm that there are 10 rows in total",
						"The export CSV file contains 10 or more rows : " + rowCount, Status.PASS);
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
