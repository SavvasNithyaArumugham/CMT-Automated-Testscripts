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
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1791_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("1. Verify partial update of an child object when the 1st level parent reference is removed from the CSV <br>"
						+"2.Verify partial update of an child object when the 2nd level parent reference is removed from the CSV"
					+"<br>3.Verify partial update of an child object when the 3nd level parent reference is removed from the CSV");
		
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
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String input = dataTable.getData("MyFiles", "BrowseActionName");
		String input1 = dataTable.getData("MyFiles", "Sort Options");
		String input2 = dataTable.getData("MyFiles", "AccessToken");
		File downloadedFile = null;
		
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		// go to Course plan
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		// upload course plan
		collectionPg.uploadFileInCollectionSite(filePath, fileName);

		// verify import process progress
		collectionPg.verifyImportProcessProgress(fileName);

		// verify uploaded object in the collection view
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
			
		// Click on Generate Realize Csv for course object
		
		collectionPg.clickOnMoreSetting(folderNames[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], Option[0]);
		collectionPg.clickonrealizebox();
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();
				
		for (String path : filePath1) {
			sitesPage.documentdetails(path);
		}
	
		// Navigate to generated csv file path 
		
		  Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		sitesPage.documentdetails(currentDate);

		// CSVfile presence and filename
		String fileName1 = folderNames[2] + "-" + currentDate;
		String filename2 = collectionPg.CSVFilename(fileName1);
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,
				filename2);
		System.out.println("filename2 : " + filename2); 
		
		sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);
		
		
		if (downloadedFile.exists()
				&& !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2
					+ " downloaded sucessfully", Status.PASS);
		}

		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
		try {
			
			for(int j=2;j<12;j+=4) {

				for(int i=0;i<90;i++) {
					CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, input ,j ,i );

				}
		
		CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, input1 ,4 ,86 );
		CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, input1 ,8 ,86 );
		
		CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, input2 ,4 ,90 );
		CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, input2 ,8 ,90 );
		
		report.updateTestLog("value updated for PlayerTarget", "File: " + filename2
				+ " updated sucessfully", Status.PASS);
			
			}
		}catch (IOException e) {
			report.updateTestLog("Value for PlayerTarget not updated ",
					"Discussion Prompt exported incorrectly ", Status.FAIL);
			e.printStackTrace();
		}
		
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
		
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		UIHelper.waitForPageToLoad(driver);
	
		
		collectionPg.clickOnEditCollectionButton();
		
		UIHelper.waitForPageToLoad(driver);
		
		collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[2]);
		collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[3]);
				
		collectionPg.clickOnMouseOverMenu("ASKD_asd 2", "View Details");
		UIHelper.waitForPageToLoad(driver);
		collectionPg.verifyMetadataValues("Book ID",input1);
		collectionPg.verifyMetadataValues("Thumbnail to link",input2);
				
		collectionPg.clickOnMouseOverMenu("ASKD_asd 2-1", "View Details");
		UIHelper.waitForPageToLoad(driver);
		collectionPg.verifyMetadataValues("Book ID",input1);
		collectionPg.verifyMetadataValues("Thumbnail to link",input2);
		
	}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
