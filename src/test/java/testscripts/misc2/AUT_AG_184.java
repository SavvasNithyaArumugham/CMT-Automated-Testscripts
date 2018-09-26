package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_184 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_079()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to remove site from favorites");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
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
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);

		String sitesFilterOptionVal = dataTable.getData("Home",
				"SitesFilterDropdownValue");
		
		String siteName = dataTable.getData("Sites", "SiteName");
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		
		homePage.navigateToHomePage();
		
		String dashletNme = dataTable.getData("Home", "DashletName");
		if(!homePage.checkDashletInUserDashboard(dashletNme))
		{
			homePage.addDashletInCustomDashBoard();
		}
		
		homePage.filterSitesInMySitesDashlet(sitesFilterOptionVal);
		
		homePageTest.verifySiteInMySitesDashlet(siteName);//Added SiteNo as part of NALS
		
		homePage.removeSiteFromFavorites(siteName);//Added SiteNo as part of NALS
	}

	@Override
	public void tearDown() {
		
	}

}
