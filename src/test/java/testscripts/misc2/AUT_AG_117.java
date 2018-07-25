package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_117 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_013() {
		testParameters.setCurrentTestDescription(
				"1. Check whether the sites are appearing in the my site dashlet after unfavorite. When it is in all option"
						+ "<br>2. Check whether the sites are disappearing in the my site dashlet after unfavorite. When it is in my favorite option"
						+ "<br>3. Check whether the sites are appearing in the my site dashlet if it is unfavorite in the sites dropdown"
						+ "<br>4. Check whether the sites are appearing in the my site dashlet if it is unfavorite in the sites dropdown");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		AlfrescoSitesDashboardPageTest siteDashboardPgTest = new AlfrescoSitesDashboardPageTest(scriptHelper);

		String sitesFilterOptionVal = dataTable.getData("Home", "SitesFilterDropdownValue");
		String siteName = dataTable.getData("Sites", "SiteName");

		String sitesFilterOptionVal1 = "", sitesFilterOptionVal2 = "";
		if (sitesFilterOptionVal.contains(",")) {
			String splittedFilterOptions[] = sitesFilterOptionVal.split(",");
			if (splittedFilterOptions != null && splittedFilterOptions.length > 1) {
				sitesFilterOptionVal1 = splittedFilterOptions[0];
				sitesFilterOptionVal2 = splittedFilterOptions[1];
			} else {
				sitesFilterOptionVal1 = "All";
				sitesFilterOptionVal2 = "My Favorites";
			}
		} else {
			sitesFilterOptionVal1 = "All";
			sitesFilterOptionVal2 = "My Favorites";
		}

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		homePage.navigateToSitesTab();
		
	
		sitesPage.createSite(siteName, "Yes");
		
		String site=sitesPage.getCreatedSiteName();
		
		
		sitesPage.openSiteFromRecentSites(site);

		homePage.navigateToHomePage();

		String dashletNme = dataTable.getData("Home", "DashletName");
		if (!homePage.checkDashletInUserDashboard(dashletNme)) {
			homePage.addDashletInCustomDashBoard();
		}

		// Check whether the sites are appearing in the my site dashlet after
		// unfavorite. When it is in all option
		homePage.filterSitesInMySitesDashlet(sitesFilterOptionVal1);
		homePageTest.verifySiteInMySitesDashlet(site);
		homePage.removeSiteFromFavorites(site);
		homePage.navigateToMyFilesTab();
		homePage.navigateToHomePage();
		homePageTest.verifySiteInMySitesDashlet(site);
		homePage.makeSiteAsFavoriteWithoutReport(site);

	// Check whether the sites are disappearing in the my site dashlet after
		// unfavorite. When it is in my favorite option
		homePage.filterSitesInMySitesDashlet(sitesFilterOptionVal2);
		homePageTest.verifySiteInMySitesDashlet(site);
		homePage.removeSiteFromFavorites(site);
		homePage.navigateToMyFilesTab();
		homePage.navigateToHomePage();
		homePageTest.verifySiteInMySitesDashletForNegativeCase(site);
		homePage.filterSitesInMySitesDashlet(sitesFilterOptionVal1);
		homePage.makeSiteAsFavoriteWithoutReport(site);

			// Check whether the sites are appearing in the my site dashlet if it is
		// unfavorite in the sites dropdown
		homePage.filterSitesInMySitesDashlet(sitesFilterOptionVal1);
		homePageTest.verifySiteInMySitesDashlet(site);
		homePage.clickOnSiteItemInMYSitesDashlet(site);
		siteDashboardPgTest.verifySiteDashboardPageIsOpenedFrmMysites();
		homePage.navigateToSitesTab();
		sitesPage.removeCurrentSiteFromFavourites();
		homePage.navigateToHomePage();
		homePageTest.verifySiteInMySitesDashlet(site);
		homePage.makeSiteAsFavoriteWithoutReport(site);

		// Check whether the sites are appearing in the my site dashlet if it is
		// unfavorite in the sites dropdown
		homePage.filterSitesInMySitesDashlet(sitesFilterOptionVal2);
		homePageTest.verifySiteInMySitesDashlet(site);
		homePage.clickOnSiteItemInMYSitesDashlet(site);
		siteDashboardPgTest.verifySiteDashboardPageIsOpenedFrmMysites();
		homePage.navigateToSitesTab();
		sitesPage.removeCurrentSiteFromFavourites();
		homePage.navigateToHomePage();
		homePageTest.verifySiteInMySitesDashletForNegativeCase(site);
		homePage.filterSitesInMySitesDashlet(sitesFilterOptionVal1);
		homePage.makeSiteAsFavoriteWithoutReport(site);
	}

	@Override
	public void tearDown() {

	}

}
