package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyActivitiesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_121 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_017(){
		testParameters.setCurrentTestDescription("1. To verify that a record Is displayed in the 'My Activity' dashlet, when a user Starts a discussion"
				+ "<br>2. To verify that a record Is displayed in the 'My Activity' dashlet, when a user Deletes a discussion");
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
		
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoMyActivitiesPage myActivityPage = new AlfrescoMyActivitiesPage(scriptHelper);
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String activityName = dataTable
				.getData("MyActivities", "TitleName");
		String menuToNavigate = dataTable.getData("Sites",
				"CustomizeSiteMenuToNavigate");
		String activityType = dataTable.getData("MyActivities", "MyActivityDropDownValue");
		String activitiesDid = dataTable.getData("MyActivities",
				"ActivityDid");
		
		String activityDid1 = "", activityDid2 = "";
		if (activitiesDid.contains(",")) {
			String splittedActivitiesDid[] = activitiesDid.split(",");
			if (splittedActivitiesDid != null && splittedActivitiesDid.length > 1) {
				activityDid1 = splittedActivitiesDid[0];
				activityDid2 = splittedActivitiesDid[1];
				
			} else {
				activityDid1 = "started discussion";
				activityDid2 = "deleted discussion";
			}
		} else {
			activityDid1 = "started discussion";
			activityDid2 = "deleted discussion";
		}
		
		String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		if(!homePage.checkExpectedDashletInHomePage(dashletNmetoAdd)){
			homePage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		

		sitesPage.siteFinder(siteNameValue);
		
		if(!sitesPage.isCustomizeSiteMenuAvailable()){
			sitesDashboardPage.navigateToCustomizeSite();
			sitesDashboardPage.addCustomizeSiteMenus();
		}
		
		sitesPage.navigateToCustomizeSiteMenu();		
		
		myActivityPage.startNewDiscussion();
		
		/*homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);*/
		
		
		
		//To verify that a record Is displayed in the 'My Activity' dashlet, when a user Deletes a discussion
	/*	homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);*/
		
		sitesPage.navigateToCustomizeSiteMenu();
		
		myActivityPage.deleteDiscussion();
		
		homePage.navigateToHomePage();
		
		sitesDashboardPg.selectMyActivitiesCategory("all items");
		sitesDashboardPg.selectMyActivitiesDateRange("today");
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid1, siteNameValue, menuToNavigate, activityType);
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid2, siteNameValue, menuToNavigate, activityType);
	}

	@Override
	public void tearDown() {
		
	}
}
