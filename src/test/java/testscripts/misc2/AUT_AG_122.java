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

public class AUT_AG_122 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_018(){
		testParameters.setCurrentTestDescription("1. To verify that a record Is displayed in the 'My Activity' dashlet, when a user creates a WiKi page"
				+ "<br>2. To verify that a record Is displayed in the 'My Activity' dashlet, when a user renames a WiKi page"
				+ "<br>3. To verify that a record Is displayed in the 'My Activity' dashlet, when a user deletes a WiKi page");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
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
		
		String activityDid1 = "", activityDid2 = "", activityDid3 = "";
		if (activitiesDid.contains(",")) {
			String splittedActivitiesDid[] = activitiesDid.split(",");
			if (splittedActivitiesDid != null && splittedActivitiesDid.length > 2) {
				activityDid1 = splittedActivitiesDid[0];
				activityDid2 = splittedActivitiesDid[1];
				activityDid3 = splittedActivitiesDid[2];
				
			} else {
				activityDid1 = "created wiki page";
				activityDid2 = "renamed wiki page";
				activityDid3 = "deleted wiki page";
			}
		} else {
			activityDid1 = "created wiki page";
			activityDid2 = "renamed wiki page";
			activityDid3 = "deleted wiki page";
		}
		
		String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		if(!homePage.checkExpectedDashletInHomePage(dashletNmetoAdd)){
			homePage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		//To verify that a record Is displayed in the 'My Activity' dashlet, when a user creates a WiKi page
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		if(!sitesPage.isCustomizeSiteMenuAvailable()){
			sitesDashboardPage.navigateToCustomizeSite();
			sitesDashboardPage.addCustomizeSiteMenus();
		}
		
		sitesPage.navigateToCustomizeSiteMenu();		
		myActivityPage.createWikiPage();
		
		homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid1, siteNameValue, menuToNavigate, activityType);
		
		//To verify that a record Is displayed in the 'My Activity' dashlet, when a user renames a WiKi page
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.navigateToCustomizeSiteMenu();
		
		myActivityPage.renameWikiPage();
		
		homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid2, siteNameValue, menuToNavigate, activityType);
		
		//To verify that a record Is displayed in the 'My Activity' dashlet, when a user deletes a WiKi page
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.navigateToCustomizeSiteMenu();
		
		myActivityPage.deleteWikiPage();
		
		homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid3, siteNameValue, menuToNavigate, activityType);
		
	}

	@Override
	public void tearDown() {
		
	}
}
