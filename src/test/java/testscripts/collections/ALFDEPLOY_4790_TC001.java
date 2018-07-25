package testscripts.collections;

import org.openqa.selenium.By;
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

public class ALFDEPLOY_4790_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_028() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4790_Confirm Align Skills command"
						+"<br>ALFDEPLOY-4790_Alignment tool window and CMT connection"
						+"<br>ALFDEPLOY-4790_Create alingment"
						+"<br>ALFDEPLOY-4790_Confirm alignment"
						+"<br>ALFDEPLOY-4790_Remove alignment"
						+"<br>ALFDEPLOY-4790_Export alignment");

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
		String[] moreSettingsOptionName = dataTable.getData("MyFiles","MoreSettingsOption").split(",");
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
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
	    driver.navigate().refresh();
	    
		// Go to Courses and navigate to Content Type
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		
		//Create Skill Alignment and confirm in View Details 
		collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[2]);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting(folderNames[3]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[3],moreSettingsOptionName[0]);
		UIHelper.waitForPageToLoad(driver);
		collectionPg.createAlignment(3,folderNames[3],"Social Studies");

		
		//Remove Skill Alignment and Confirm in View Details
		driver.navigate().back();
		UIHelper.waitForLong(driver);
		
		collectionPg.clickOnMoreSetting(folderNames[3]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[3],moreSettingsOptionName[0]);
		collectionPg.removeAlignment(2,folderNames[3]);
	
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitForPageToLoad(driver);
		
		// Click on Generate Realize Csv for course object
				collectionPg.clickOnMoreSetting(folderNames[2]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], moreSettingsOptionName[1]);
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
				
				new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,filename2);

				sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");

				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				downloadedFile = new File(downloadFilePath + "/" + filename2);
				if (downloadedFile.exists()
						&& !filename2.equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: " + filename2
							+ " downloaded sucessfully", Status.PASS);
				}
		/*		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
				try {
					if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"SS.2",2,40))
						{
						report.updateTestLog(" In resulting successful export, confirm that the remaining aligned Skill codes are present in the Skills column (Column AO) ",
								"the remaining aligned Skill codes are present in the Skills column (Column AO)", Status.PASS);
						}
					else
					{
						report.updateTestLog(" In resulting successful export, confirm that the remaining aligned Skill codes are present in the Skills column (Column AO) ",
								"the remaining aligned Skill codes are not present in the Skills column (Column AO)", Status.PASS);
					}
					
				} catch (IOException e) {
				
					e.printStackTrace();
				}*/
		} 



	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
