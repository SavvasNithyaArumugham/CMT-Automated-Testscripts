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
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_031 extends TestCase {

	private FunctionalLibrary functionalLibrary;

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
	


		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String objectName = dataTable.getData("MyFiles", "Subfolders1");
	
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		String errorfile = dataTable.getData("MyFiles", "Subfolders2");
		
		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);

		// Create site
		
		/*	homePageObj.navigateToSitesTab();
		   sitesPage.createSite(siteNameValue,"Yes");*/
		 

		String site = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(site);

		
/*	 // Go to collection UI 
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
		  driver.navigate().refresh();*/
	
		 sitesPage.enterIntoDocumentLibraryWithoutReport();
		  
		  //go to course object 1 
	
		  sitesPage.documentdetails("Data Imports");
		  sitesPage.documentdetails("Completed");
		  sitesPage.documentdetails(currentDate.substring(0, 4));
		  sitesPage.documentdetails(currentDate.substring(5, 7));
		  sitesPage.documentdetails(currentDate.substring(8, 10));
		  sitesPage.clickOnMoreSetting(fileName);
		  collectionPg.commonMethodForClickOnMoreSettingsOption(objectName,
				  moreSettingsOption);
		  new FileUtil().waitUptoAllFilesDownloadComplete(downloadFilePath,errorfile);

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			homePageObj.openNewTab(downloadFilePath + "\\" + errorfile);
			
			collectionPg.errormessage("Content Category","Missing column from CSV node: Content Category");
			collectionPg.errormessage("Call Out","Error persisting node... The value is not an allowed value: Call Out");
		 

		
	} 


	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

}
