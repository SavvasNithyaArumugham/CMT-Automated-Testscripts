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
import java.util.List;
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
import com.pearson.automation.alfresco.tests.AlfrescoAVSmartLinkPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1909_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Enhance File report to include 'Active Preview URL' Links and  'File Creation Date' in the existing report");
		
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

	/* (non-Javadoc)
	 * @see com.pearson.automation.utils.TestCase#executeTest()
	 */
	/* (non-Javadoc)
	 * @see com.pearson.automation.utils.TestCase#executeTest()
	 */
	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String Option = dataTable.getData("MyFiles", "MoreSettingsOption");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
		String filename1 = dataTable.getData("MyFiles", "Subfolders1");
		String activePreviewURLFieldName = dataTable.getData("MyFiles", "Subfolders2");
		String fileFieldname = dataTable.getData("MyFiles", "Subfolders3");
		String fileFieldname1 = dataTable.getData("MyFiles", "Version");	
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		CSVUtil csvUtil=new CSVUtil();
		
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
	//	sitesPage.openSiteFromRecentSites("autorefercollectionsite202060319153518");
		
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		
		// Create a folder and Upload image file in created folder 
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.createFolder(folderDetails);
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("AutoTest");
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);
		sitesPage.enterIntoDocumentLibrary();
				
		// Click on Generate Report To CSV and check for 'Active Preview URL' Links and  'File Creation Date' in report
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,filename1);
				
		collectionPg.clickOnMoreSetting("AutoTest");
		collectionPg.commonMethodForClickOnMoreSettingsOption("AutoTest", Option);
		
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename1);
		downloadedFile = new File(downloadFilePath + "/" +filename1 );
		
		if (downloadedFile.exists()
				&& !filename1.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename1
					+ " downloaded sucessfully", Status.PASS);
		}
		
		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename1;
		
		List<String> data=null;
		try {
			data=csvUtil.readLinesOfDataFromCSVFile(downloadedCSVFileANmeWithPath);
			if(data.get(0).contains(activePreviewURLFieldName)) {
				report.updateTestLog("Verify field present in header of CSV", " CSV has the "+activePreviewURLFieldName+" field in the header",
						Status.PASS);
			}else {
				report.updateTestLog("Verify field present in header of CSV", " CSV doesn't have the "+activePreviewURLFieldName+" field in the header",
						Status.FAIL);
			}
		}catch(Exception e) {
			report.updateTestLog("Verify field present in header of CSV", "CSV header check failed",
					Status.FAIL);
		}
		
		try {
			data=csvUtil.readLinesOfDataFromCSVFile(downloadedCSVFileANmeWithPath);
			if(data.get(0).contains(fileFieldname)) {
				report.updateTestLog("Verify field present in header of CSV", " CSV has the "+fileFieldname+" field in the header",
						Status.PASS);
			}
			else if(data.get(0).contains(fileFieldname1))
			{
				report.updateTestLog("Verify field present in header of CSV", " CSV has the "+fileFieldname1+" field in the header",
						Status.PASS);
			}
		else {
				report.updateTestLog("Verify field present in header of CSV", " CSV doesn't have the "+fileFieldname+" field in the header",
						Status.FAIL);
			}
		}catch(Exception e) {
			report.updateTestLog("Verify field present in header of CSV", "CSV header check failed",
					Status.FAIL);
		}
		
		
	}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
