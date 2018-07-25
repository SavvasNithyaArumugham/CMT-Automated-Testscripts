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

public class AUT_AG_124 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_020() {
		testParameters.setCurrentTestDescription(
				"1. To verify that a record Is displayed in the 'My Activity' dashlet, when a user creates an Event in Calendar"
						+ "<br>2. To verify that a record Is displayed in the 'My Activity' dashlet, when a user updates an Event in Calendar"
						+ "<br>3. To verify that a record Is displayed in the 'My Activity' dashlet, when a user deletes an Event in Calendar");
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
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		// TODO Auto-generated method stub
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoMyActivitiesPage myActivityPage = new AlfrescoMyActivitiesPage(scriptHelper);
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String activityName = dataTable.getData("MyActivities", "TitleName");
		String menuToNavigate = dataTable.getData("Sites", "CustomizeSiteMenuToNavigate");
		String activityType = dataTable.getData("MyActivities", "MyActivityDropDownValue");
		String activitiesDid = dataTable.getData("MyActivities", "ActivityDid");

		String activityDid1 = "", activityDid2 = "", activityDid3 = "";
		if (activitiesDid.contains(",")) {
			String splittedActivitiesDid[] = activitiesDid.split(",");
			if (splittedActivitiesDid != null && splittedActivitiesDid.length > 2) {
				activityDid1 = splittedActivitiesDid[0];
				activityDid2 = splittedActivitiesDid[1];
				activityDid3 = splittedActivitiesDid[2];

			} else {
				activityDid1 = "created calendar event";
				activityDid2 = "updated calendar event";
				activityDid3 = "deleted calendar event";
			}
		} else {
			activityDid1 = "created calendar event";
			activityDid2 = "updated calendar event";
			activityDid3 = "deleted calendar event";
		}
		
		String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		if(!homePage.checkExpectedDashletInHomePage(dashletNmetoAdd)){
			homePage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}

		//To verify that a record Is displayed in the 'My Activity' dashlet, when a user creates an Event in Calendar
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);

		if (!sitesPage.isCustomizeSiteMenuAvailable()) {
			sitesDashboardPage.navigateToCustomizeSite();
			sitesDashboardPage.addCustomizeSiteMenus();
		}

		sitesPage.navigateToCustomizeSiteMenu();
		myActivityPage.createCalendarEvent();

		homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);

		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid1, siteNameValue,
				menuToNavigate, activityType);

		// To verify that a record Is displayed in the 'My Activity' dashlet,
		// when a user updates an Event in Calendar
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.navigateToCustomizeSiteMenu();

		myActivityPage.editCalendarEvent();

		homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);

		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid2, siteNameValue,
				menuToNavigate, activityType);

		// To verify that a record Is displayed in the 'My Activity' dashlet,
		// when a user deletes an Event in Calendar
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.navigateToCustomizeSiteMenu();
		
		myActivityPage.deleteCalendarEvent();
		
		homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid3, siteNameValue,
				menuToNavigate, activityType);
		
	}

	@Override
	public void tearDown() {

	}
}
