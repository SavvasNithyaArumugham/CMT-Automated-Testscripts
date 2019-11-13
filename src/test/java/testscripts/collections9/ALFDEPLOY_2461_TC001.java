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

public class ALFDEPLOY_2461_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription(" Job Queue titles for non-JSON jobs link to their status files - Generate Realize CSVs");
		
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
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		
		String jobQueueDashletAvailableCheck = ".//div[@class='dashlet jobs resizable yui-resize']//div[1]";
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
		sitesPage.siteFinder("jobqueuecollectionstestsite");			
			
			// go to Course plan and upload course
				sitesPage.enterIntoDocumentLibrary();				
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
				// Click on Generate Realize Csv for course object
				
				collectionPg.clickOnMoreSetting(folderNames[2]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], "Generate Realize CSVs");
				collectionPg.clickonrealizebox();
		
				// Check Generate Realize Csv job status in the user dash board
				
				homePageObj.navigateToHomePage();
				
				// Check import job status in the user dash board
							
				UIHelper.waitFor(driver);
				
				//String loadingDocumentsXpath = ".//*[@class='yui-dt-data']//a[@title='Navigate to manifest']";
	String loadingDocumentsXpath = ".//*[@class='yui-dt-data']//a[@title='Navigate to manifest' and contains(.,'Collection to Realize')]";
				UIHelper.click(driver, loadingDocumentsXpath);
				
				UIHelper.waitFor(driver);
					
				String realizeLinkXpath = ".//*[@class='node-path']//span[11]//a [contains(text(),'Realize Export')]";
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, realizeLinkXpath )))
				{
					UIHelper.highlightElement(driver, realizeLinkXpath);
					UIHelper.waitFor(driver);
					report.updateTestLog("Job Queue UI", "Job Queue UI pass", Status.PASS);
				} else {
					report.updateTestLog("Job Queue UI", "Job Queue UI pass", Status.FAIL);
				}
				
				String docDetailsXpath = ".//*[@class='node-path']//span[13]//a [contains(text(),'Name Overalap 2')]";
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, docDetailsXpath )))
				{
					UIHelper.highlightElement(driver, docDetailsXpath);
					UIHelper.waitFor(driver);
					report.updateTestLog("Job Queue UI", "Job Queue UI doc pass", Status.PASS);
				} else {
					report.updateTestLog("Job Queue UI", "Job Queue UI doc pass", Status.FAIL);
				}
				
				
	}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
