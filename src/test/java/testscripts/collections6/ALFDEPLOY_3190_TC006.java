package testscripts.collections6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class ALFDEPLOY_3190_TC006 extends TestCase {

	private FunctionalLibrary functionalLibrary;
	@Test
	public void COLLECTIONS_009() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3190_06_Export new course"
						+"<br>ALFDEPLOY-3190_07_Confirm export of new metadata"
						+"<br>ALFDEPLOY-3190_08_Confirm ability to update new metadata"
						+"<br>ALFDEPLOY-3190_09_Confirm updated metadata");

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
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");
		String[] smartlink = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String selctFilesToUploadBtnXpath = "//*[contains(@id,'default-file-selection-button-overlay')]/span//*[contains(.,'Select files to upload')]";
		String uploadInputField = ".//*[contains(@id,'default-file-selection-button-overlay')]/span/input";
		String messageXpath = ".//*[@id='message']/div";
		File downloadedFile = null;
	
		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);
		UIHelper.waitForLong(driver);
		
		/*String siteName = sitesPage.getCreatedSiteName();				
		sitesPage.openSiteFromRecentSites(siteName);*/
		
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

		/*************************************Export new course********************************/
		// Click on Generate Realize Csv for course object
		collectionPg.clickOnMoreSetting(folderNames[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(
				folderNames[2], moreSettingsOptionName);
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
		/**********************Confirm ability to update new metadata********************/
		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
		
		//filePath1[2]+"-" + currentDate;

		try {
			//CSVUtil.updateCSV(System.getProperty("user.dir")+filePath+"/ALFDEPLOY-3190-Updated.csv", smartlink[0],25 ,9 );		
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, smartlink[0],9 ,9 );
			report.updateTestLog("Verify change 'Discipline Test 2 - World Languages' Discipline value of 'World Languages' to 'Music'", "File: " + filename2
					+ " updated sucessfully", Status.PASS);
			
			//CSVUtil.updateCSV(System.getProperty("user.dir")+filePath+"/ALFDEPLOY-3190-Updated.csv", smartlink[1],5 , 22);
			
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, smartlink[1],5 , 22);
			report.updateTestLog( "In 'Content Type Test 1 - Lesson' change Content Type value from 'Lesson' to 'Map'", "File: " + filename2
					+ " updated sucessfully", Status.PASS);
		
			//CSVUtil.updateCSV(System.getProperty("user.dir")+filePath+"/ALFDEPLOY-3190-Updated.csv", smartlink[2],16 , 24);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, smartlink[2],7 , 24);
			report.updateTestLog( "In 'Media Type Test 3 - Professional Development Center' change Media Type value from 'Professional Development Center' to 'Desmos'", "File: " + filename2
					+ " updated sucessfully", Status.PASS);
			
			//CSVUtil.updateCSV(System.getProperty("user.dir")+filePath+"/ALFDEPLOY-3190-Updated.csv", smartlink[3],26 ,76 );
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, smartlink[3],10 ,76 );
			report.updateTestLog( "In 'Genre Test 1 - Animal Fantasy' change Genres value from 'Animal Fantasy' to 'Diary/Journal'", "File: " + filename2
					+ " updated sucessfully", Status.PASS);
			
			//CSVUtil.updateCSV(System.getProperty("user.dir")+filePath+"/ALFDEPLOY-3190-Updated.csv", smartlink[4],1 ,92);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, smartlink[4],1 ,92);
			report.updateTestLog( "Change any blank State Code value from blank to 'NA'", "File: " + filename2
					+ " updated sucessfully", Status.PASS);
			
			//CSVUtil.updateCSV(System.getProperty("user.dir")+filePath+"/ALFDEPLOY-3190-Updated.csv", smartlink[5], 35 ,84);
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, smartlink[5], 12 ,84);
			report.updateTestLog( "In 'Recommendable No Test 1' change Recommendable value from 'No' to 'Yes'", "File: " + filename2
					+ " updated sucessfully", Status.PASS);
			
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*********************************Confirm updated metadata**********************/
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
		sitesPage.documentdetailsColl(folderNames[0]);
		sitesPage.documentdetailsColl(folderNames[1]);
		
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitForPageToLoad(driver);
		collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[2]);
		collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[3]);
		collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[4]);
		collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[5]);
		UIHelper.waitForLong(driver);
		collectionPg.clickOnMouseOverMenu("Content Type Test 1 - Lesson", "View Details");
		UIHelper.waitForPageToLoad(driver);
		collectionPg.verifyMetadataValues("Content Type",smartlink[1]);   
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
