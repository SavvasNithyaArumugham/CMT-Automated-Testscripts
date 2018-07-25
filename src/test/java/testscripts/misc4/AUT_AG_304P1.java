package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyActivitiesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_304P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_004() {
		testParameters
				.setCurrentTestDescription("Verify that Reviewer user can view wiki page details - Part 1: Create a site and add reviwer");
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
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		// TODO Auto-generated method stub
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteNameValue);
		sitesPage.siteMembersPage();
		sitesPage.inviteUserForSite();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		if (!sitesPage.isCustomizeSiteMenuAvailable()) {
			AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(
					scriptHelper);
			sitesDashboardPage.navigateToCustomizeSite();
			sitesDashboardPage.addCustomizeSiteMenus();
		}

		sitesPage.navigateToCustomizeSiteMenu();
		AlfrescoMyActivitiesPage myActivityPage = new AlfrescoMyActivitiesPage(
				scriptHelper);
		myActivityPage.createWikiPage();
	}

	@Override
	public void tearDown() {

	}
}
