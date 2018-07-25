package testscripts.collections;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
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
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.FrameworkParameters;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_032 extends TestCase {

	private FunctionalLibrary functionalLibrary;
	//FrameworkParameters frameworkParameters = new FrameworkParameters();

	@Test
	public void COLLECTIONS_060() {
		testParameters.setCurrentTestDescription("TC-V_2.3.7.6-01 - Import test data set <br>"+
	"TC-5200-01 - Export Course to generate Index Code <br>" +
				"TC-5306-01 - Confirm Content Category property population via CSV import <br> " +
	"TC-5306-03 - Confirm Content Category property export<br>" +
				"TC-5306-04 - Confirm Content Category property update<br>"+
	"TC-5306-05 - Confirm Content Category property delete <br>");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		
		String name = System.getProperty("suite.fileName");
		System.out.println(name);
		
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);


		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String objectName = dataTable.getData("MyFiles", "Subfolders1");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String generateCSVoption = dataTable.getData("MyFiles", "MoreSettingsOption2");
		String[] csvpath = dataTable.getData("MyFiles", "CreateFolder").split("/");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String collectionsobj = dataTable.getData("MyFiles", "CreateFileDetails");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		File downloadedFile = null;
		String errorfile = dataTable.getData("MyFiles", "Subfolders2");
		
		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);

		// Create site
		
			homePageObj.navigateToSitesTab();
		   sitesPage.createSite(siteNameValue,"Yes");
		 

		/*String site = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(site);
*/
		
	 // Go to collection UI 
		sitesPage.enterIntoDocumentLibrary();
		 //go to Course plan 
		sitesPage.documentdetails("Data Imports");
		 sitesPage.documentdetails("Course Plan");
		 
		  // upload course plan
		 
		  collectionPg.uploadFileInCollectionSite(filePath, fileName);
		  UIHelper.waitForLong(driver);
		   UIHelper.waitForLong(driver);
		  UIHelper.pageRefresh(driver);
		   UIHelper.waitForLong(driver);
		  UIHelper.pageRefresh(driver); 
		  driver.navigate().refresh();
	
		 sitesPage.enterIntoDocumentLibraryWithoutReport();
		 
		 sitesPage.documentdetails(folderNames[0]);
		 sitesPage.documentdetails(folderNames[1]);
		 collectionPg.clickOnEditCollectionButton();
	
		  // Click on Generate Realize Csv for course object
		  collectionPg.clickOnMoreSetting(objectName);
		  collectionPg.commonMethodForClickOnMoreSettingsOption(objectName,
		  generateCSVoption); 
		  collectionPg.clickonrealizebox();
		  
		  UIHelper.waitForPageToLoad(driver);
		  UIHelper.waitFor(driver);
	
	// Navigate to Document library
	sitesPage.enterIntoDocumentLibraryWithoutReport();

	for (String path : csvpath) {
		sitesPage.documentdetails(path);
	}

	// Navigate to generated csv file path and check whether
	sitesPage.documentdetails(objectName);
	sitesPage.documentdetails(currentDate);

	String csvName = sitesPage.documentname(objectName);
	new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, csvName);
	
	String uploadPath = downloadFilePath + csvName;
	
	collectionPg.clickOnBrowseActionsInCollectionUI(csvName, "Download");

	new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, csvName);
	downloadedFile = new File(downloadFilePath + "/" + csvName);

	if (downloadedFile.exists() && !csvName.equalsIgnoreCase("File Not Found")) {
		report.updateTestLog("Verify download file", "File: " + csvName + " downloaded sucessfully", Status.PASS);
	}
	
	UIHelper.waitFor(driver);
	UIHelper.waitFor(driver);
	try {
		CSVUtil.updateCSV(uploadPath, "*100", 3, 1);
		CSVUtil.updateCSV(uploadPath, "*31", 5, 1);
		CSVUtil.updateCSV(uploadPath, "*30", 7, 1);
		CSVUtil.updateCSV(uploadPath, "*40", 8, 1);
		CSVUtil.updateCSV(uploadPath, "*50", 9, 1);
		CSVUtil.updateCSV(uploadPath, "*60", 10, 1);
		CSVUtil.updateCSV(uploadPath, "*70", 11, 1);
		CSVUtil.updateCSV(uploadPath, "*80", 12, 1);
		CSVUtil.updateCSV(uploadPath, "*90", 13, 1);
		CSVUtil.updateCSV(uploadPath, "*10", 14, 1);

	} catch (IOException e) {
		// TODO Auto-generated catch block 
		e.printStackTrace();
	}
		 
	 sitesPage.enterIntoDocumentLibraryWithoutReport();
	 
	 //go to Course plan 
	sitesPage.documentdetails("Data Imports");
	sitesPage.documentdetails("Course Plan");
		 
	// upload course plan
		 
	collectionPg.commonMethodForUploadMultipleFiles(downloadFilePath,"/" + csvName);
	UIHelper.waitForLong(driver);
	UIHelper.waitForLong(driver);
	UIHelper.pageRefresh(driver);
	UIHelper.waitForLong(driver);
	UIHelper.pageRefresh(driver);
	driver.navigate().refresh();
	
	sitesPage.enterIntoDocumentLibraryWithoutReport();

	sitesPage.documentdetails(folderNames[0]);
	sitesPage.documentdetails(folderNames[1]);
	collectionPg.clickOnEditCollectionButton();
	
	collectionPg.openCollectionObject(objectName);
	collectionPg.openCollectionObject("Lesson 1");
	ArrayList<String> listOfObjects = new ArrayList<String>();
	
	listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
	
	
	if((listOfObjects.get(0).equalsIgnoreCase("Content J")) && (listOfObjects.get(1).equalsIgnoreCase("Content C"))
			&&(listOfObjects.get(2).equalsIgnoreCase("Content B")) &&(listOfObjects.get(3).equalsIgnoreCase("Content D"))
			&&(listOfObjects.get(4).equalsIgnoreCase("Content E"))) {
		
		report.updateTestLog("Verify resequencing in Colloctions",
				"Re sequencing is as expected " + "<br/><b>Object list:</b>"
						+ listOfObjects,
				Status.PASS);
		
		
	}else {
		report.updateTestLog("Verify resequencing in Colloctions",
				"Re sequencing is not done as expected " + "<br/><b>Object list:</b>"
						+ listOfObjects,
				Status.PASS);
	}
	
	
		
	} 


	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

}
