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

public class ALFDEPLOY_1706_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("1.Confirm \"Collections Job Queue\" dashlet appears on user's homepage"+
		"Check Import and Generate Realize Csv job Status in \"Collections Job Queue\" dashlet ");
		
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
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String	csvFileName[] = dataTable.getData("MyFiles", "Sort Options").split(",");
		String  jobType[]= dataTable.getData("MyFiles", "StatusReportValue").split(",");
		String userName = dataTable.getData("MyFiles", "AccessToken");
		String reqSiteName = dataTable.getData("MyFiles", "PopUpMsg");
		String serverName = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String progress[]  = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);
		
		
		String jobQueueDashletAvailableCheck = ".//*[contains(@id,'ALF-CMS-3_x007e_dashboard')]//*[contains(@class,'dashlet jobs resizable yui-resize')]";
		if (UIHelper.checkForAnElementbyXpath(driver,jobQueueDashletAvailableCheck))
	{
		UIHelper.highlightElement(driver, jobQueueDashletAvailableCheck);
		report.updateTestLog("Check if Collections Job Queue dashlet appears on user's homepage",
				"Collections Job Queue dashlet appears on user's homepage", Status.PASS);	
	}
	else
	{
		report.updateTestLog("Check if Collections Job Queue dashlet appears on user's homepage",
				"Collections Job Queue dashlet failed to appears on user's homepage ", Status.FAIL);	
	}

			
		// goto site > document lib
		homePageObj.navigateToSitesTab();
		//sitesPage.siteFinder(siteNameValue);
		
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		
		sitesPage.openSiteFromRecentSites(siteName);
				sitesPage.enterIntoDocumentLibrary();

				// go to Course plan and upload course
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				homePageObj.navigateToHomePage();
				UIHelper.waitFor(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitFor(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitFor(driver);
																								
				// Generate Realize Csv
				 sitesPage.openSiteFromRecentSites(siteName);
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
				// Click on Generate Realize Csv for course object
				
				collectionPg.clickOnMoreSetting(folderNames[2]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], Option[0]);
				collectionPg.clickonrealizebox();
		
				// Check Generate Realize Csv job status in the user dash board
				
				homePageObj.navigateToHomePage();
				UIHelper.pageRefresh(driver);
				UIHelper.waitFor(driver);
				collectionPg.isGenerateRealizeStatusDisplayed(csvFileName[0],userName,reqSiteName,jobType[0],serverName,progress[0]);
				UIHelper.pageRefresh(driver);
				collectionPg.isGenerateRealizeStatusDisplayed(csvFileName[0],userName,reqSiteName,jobType[0],serverName,progress[0]);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
						
	}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
