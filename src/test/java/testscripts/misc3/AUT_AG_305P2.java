package testscripts.misc3;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_305P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_006() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-2065_Verify Reviewer is unable to rename the file created by others through Edit Properties.<br>" + 
						"ALFDEPLOY-2065_Verify Reviewer is unable to rename the folder created by others through Edit Properties.<br>");
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
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoHomePageTest appHomePgTest = new AlfrescoHomePageTest(scriptHelper);
		
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");	
		String selectedItemMenuOptVal1 = dataTable.getData("Sites", "CreateSiteTypeValues");
		String siteName = dataTable.getData("Sites", "SiteName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String comments = dataTable.getData("Search", "CommentsForBulkDownload");
		String expectedMessageVal = dataTable.getData("Sites", "ExpectedConfirmationMessage");
		
		/*if (!UIHelper.checkForAnElementbyXpath(driver, homePageObj.isBulkDashletAvailable)) {
			homePageObj.customizeSiteDashboard();

			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
			homePageObj.navigateToHomePage();
		}
		
		searchObj.performSearch();
		
		searchTestObj.verifyUploadedFileInSearchResults();
		searchObj.commonMethodForPerformSelectedItemsOperation(fileName, selectedItemMenuOptVal);
		searchObj.performBulkOrMetadataTemplateDownload(comments+" via normal search");
		
		String actualMessageVal = searchObj
				.getTheMessageText(expectedMessageVal);
		
		System.out.println(actualMessageVal);
		
		new FileUtil().clearFile("src/test/resources/AppTestData/TestOutput/ConfMessages.txt");
		searchTestObj.verifyConfirmationDailogMessage(expectedMessageVal);*/
		
	/*	homePageObj.navigateToHomePage();
		
		homePageObj.isBulkJobpresent();
		homePageObj.getBulkFailedJobfromDashlet();
		
		//homePageObj.getBulkjobValues();
		
		
		appHomePgTest.verifyBulkFailedJobsErrorsFromDashlet();
		
		if (UIHelper.findAnElementbyXpath(driver, appHomePgTest.isMessagelongXpath).getText().contains("User is not a member of the Site")) {
			report.updateTestLog("Verify Bulk Failed Job ",
					"Bulk failed job displays Error Msg." + "<br /><b> Error Msg : </b>"
							+ UIHelper.findAnElementbyXpath(driver, appHomePgTest.isMessagelongXpath).getText(),
					Status.PASS);
		} else {
			report.updateTestLog("Verify Bulk Failed Job ",
					"Bulk failed job displays Error Msg failed. Error Msg not found", Status.FAIL);
		}
		*/
	
		
/*		searchObj.performSearch();
	
		searchTestObj.verifyUploadedFileInSearchResults();
		searchObj.commonMethodForPerformSelectedItemsOperation(fileName, selectedItemMenuOptVal1);
		searchObj.performAllAspectsTemplateDownload(comments+" via normal search");
		
		new FileUtil().clearFile("src/test/resources/AppTestData/TestOutput/ConfMessages.txt");
		searchTestObj.verifyConfirmationDailogMessage(expectedMessageVal);*/
		
		homePageObj.navigateToHomePage();                                                                                                                                    
		
		homePageObj.isBulkJobpresent("Last 24 Hours", "Template Download","Failed" );
	//	homePageObj.getBulkFailedJobfromDashlet();
		
		//homePageObj.getBulkjobValues();
		
		appHomePgTest.verifyBulkFailedJobsErrorsFromDashlet();
		
		if (UIHelper.findAnElementbyXpath(driver, appHomePgTest.isMessagelongXpath).getText().contains("User is not a member of the Site")) {
			report.updateTestLog("Verify Bulk Job",
					"Bulk job displays Error Msg."+"<br /><b> Job Type : </b>" + "Template Download"
			+ "<br /><b> Error Msg : </b>"
							+ UIHelper.findAnElementbyXpath(driver, appHomePgTest.isMessagelongXpath).getText(),
					Status.PASS);
		} else {
			report.updateTestLog("Verify Bulk Failed Job ",
					"Bulk failed job displays Error Msg failed. Error Msg not found", Status.FAIL);
		}
		
		UIHelper.switchtab(0,driver);
		

		
		homePageObj.isBulkJobpresent("Last 24 Hours", "Download","Failed");
		//	homePageObj.getBulkFailedJobfromDashlet();
			
			//homePageObj.getBulkjobValues();
			
			appHomePgTest.verifyBulkFailedJobsErrorsFromDashlet();
			
			if (UIHelper.findAnElementbyXpath(driver, appHomePgTest.isMessagelongXpath).getText().contains("User is not a member of the Site")) {
				report.updateTestLog("Verify Bulk Failed Job ",
						"Bulk failed job displays Error Msg." +"<br /><b> Job Type : </b>" + "Download"
				+ "<br /><b> Error Msg : </b>"
								+ UIHelper.findAnElementbyXpath(driver, appHomePgTest.isMessagelongXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Bulk Failed Job ",
						"Bulk failed job displays Error Msg failed. Error Msg not found", Status.FAIL);
			}
		
		/*sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		searchObj.performAdvancedSearch();
		searchTestObj.verifyUploadedFileInSearchResults();
		
		searchObj.commonMethodForPerformSelectedItemsOperation(fileName, selectedItemMenuOptVal);
		
		searchObj.performBulkOrMetadataTemplateDownload(comments+" via advanced search");
		
		searchTestObj.verifyConfirmationDailogMessage(expectedMessageVal);*/
	
	}
	
	@Override
	public void tearDown() {

	}

}