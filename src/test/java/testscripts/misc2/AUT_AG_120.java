package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_120 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_016(){
		testParameters.setCurrentTestDescription("1. To verify that a record Is displayed in the 'My Activity' dashlet, when a document is added in the site"
				+ "<br>2. To verify that a record Is displayed in the 'My Activity' dashlet, when a user like any content on the site"
				+ "<br>3. To verify that a record Is displayed in the 'My Activity' dashlet, when a document is deleted from the site");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		// TODO Auto-generated method stub
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String activityName = dataTable
				.getData("MyActivities", "TitleName");
		String menuToNavigate = dataTable.getData("Sites",
				"CustomizeSiteMenuToNavigate");
		String activityType = dataTable.getData("MyActivities", "MyActivityDropDownValue");
		String activitiesDid = dataTable.getData("MyActivities",
				"ActivityDid");
		
		String activityDid1 = "", activityDid2 = "", activityDid3 = "";
		if (activitiesDid.contains(",")) {
			String splittedActivitiesDid[] = activitiesDid.split(",");
			if (splittedActivitiesDid != null && splittedActivitiesDid.length > 2) {
				activityDid1 = splittedActivitiesDid[0];
				activityDid2 = splittedActivitiesDid[1];
				activityDid3 = splittedActivitiesDid[2];
				
			} else {
				activityDid1 = "created document";
				activityDid2 = "liked document";
				activityDid3 = "deleted document";
			}
		} else {
			activityDid1 = "created document";
			activityDid2 = "liked document";
			activityDid3 = "deleted document";
		}
		
		String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
		if(!homePage.checkExpectedDashletInHomePage(dashletNmetoAdd)){
			homePage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		//To verify that a record Is displayed in the 'My Activity' dashlet, when a document is added in the site

		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
	
		docLibPage.deleteAllFilesAndFolders();
	
		

			myFiles.createFile(fileDetails);
			docDetailsPage.likeADocument(fileName);

		
		homePage.navigateToHomePage();
		
		
		sitesDashboardPg.selectMyActivitiesInSiteActivityDashlet();
		sitesDashboardPg.selectMyActivitiesCategory("all items");
		sitesDashboardPg.selectMyActivitiesDateRange("today");
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid1, siteNameValue, menuToNavigate, activityType);
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid2, siteNameValue, menuToNavigate, activityType);
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid3, siteNameValue, menuToNavigate, activityType);
		
	
		
		
	}

	@Override
	public void tearDown() {
		
	}
}
