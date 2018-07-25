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

public class AUT_AG_123 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_019(){
		testParameters.setCurrentTestDescription("1. To verify that a record Is displayed in the 'My Activity' dashlet, when a user created a Blog"
				+ "<br>2. To verify that a record Is displayed in the 'My Activity' dashlet, when a user deletes a Blog");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
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
				activityDid1 = "created blog post";
				activityDid2 = "deleted blog post";
			}
		} else {
			activityDid1 = "created blog post";
			activityDid2 = "deleted blog post";
		}
		
		String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		if(!homePage.checkExpectedDashletInHomePage(dashletNmetoAdd)){
			homePage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		if(!sitesPage.isCustomizeSiteMenuAvailable()){
			AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
			sitesDashboardPage.navigateToCustomizeSite();
			sitesDashboardPage.addCustomizeSiteMenus();
		}
		
		//To verify that a record Is displayed in the 'My Activity' dashlet, when a user created a Blog
		sitesPage.navigateToCustomizeSiteMenu();		
		myActivityPage.createBlog();
		
		homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid1, siteNameValue, menuToNavigate, activityType);
		
		//To verify that a record Is displayed in the 'My Activity' dashlet, when a user deletes a Blog
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.navigateToCustomizeSiteMenu();
		
		myActivityPage.deleteBlogPost();
		
		homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid2, siteNameValue, menuToNavigate, activityType);
	}

	@Override
	public void tearDown() {
		
	}
}
