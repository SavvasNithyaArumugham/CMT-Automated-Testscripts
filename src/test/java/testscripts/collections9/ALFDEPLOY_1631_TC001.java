package testscripts.collections9;

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
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1631_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Confirm skills and standard CSV is not available on successful export of a new course object");
		
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
	
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");
		File downloadedFile = null;
							
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		// upload course plan
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		UIHelper.waitFor(driver);
			
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
	// Click on Generate Realize Csv for course object
		
		collectionPg.clickOnMoreSetting(folderNames[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], Option[0]);
		collectionPg.clickonrealizebox();
		UIHelper.waitFor(driver);
		sitesPage.enterIntoDocumentLibrary();
		for (String path : filePath1) {
			sitesPage.documentdetails(path);
		}
		 Date date = new Date();
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			sitesPage.documentdetails(currentDate);
			
			ArrayList<String> generatedFileList = new ArrayList<String>();
			generatedFileList = myFiles.getUploadedFileOrFolderTitle();
			System.out.println("List"+generatedFileList);
			UIHelper.waitFor(driver);
				
			if (generatedFileList.contains("Skills to realize.CSV")) {
				report.updateTestLog("Verify skills CSV is not available on successful export of a new course object", "FAIL",
						Status.FAIL);
				
				
			} else {
				report.updateTestLog("Verify skills CSV is not available on successful export of a new course object", "PASS", Status.PASS);
			}
			
			if (generatedFileList.contains("Standard to realize.CSV")) {
				report.updateTestLog("Verify standard CSV is not available on successful export of a new course object", "FAIL",
						Status.FAIL);
				
				
			} else {
				report.updateTestLog("Verify standard CSV is not available on successful export of a new course object", "PASS", Status.PASS);
			}

			UIHelper.pageRefresh(driver);
			
			// CSVfile presence and filename
			String fileName1 = folderNames[2] + "-" + currentDate;
			String filename2 = collectionPg.CSVFilename(fileName1);
			
			new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,
					filename2);
			System.out.println("filename2 : " + filename2); 
			
			sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");

			new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
			downloadedFile = new File(downloadFilePath + "/" + filename2);
			
			sitesPage.enterIntoDocumentLibrary();

			//go to Course plan
			myFiles.openCreatedFolder("Data Imports");
			myFiles.openCreatedFolder("Course Plan");
					
			// upload updated course plan from system download path
					
			collectionPg.uploadUpdatedFileInCollectionSite(downloadFilePath, filename2);
			UIHelper.waitForLong(driver);
			UIHelper.pageRefresh(driver);
		    driver.navigate().refresh();
		    
		    // Go to Courses and navigate to Content Type
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder("Data Imports");
			myFiles.openCreatedFolder("Completed");
			
			UIHelper.waitForPageToLoad(driver);
		
	}
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
