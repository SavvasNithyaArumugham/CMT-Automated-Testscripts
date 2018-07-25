package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_047 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_047()
	{
		testParameters.setCurrentTestDescription("Verify that site manager is able to customize site dashboard for Program site with 'Four columns'");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		String dashletName = dataTable.getData("Home", "DashletName");
		String colNoDetailsForAddDashlet = dataTable.getData("Home",
				"ColumnNoofAddDashlet");
		
		sitesDashboardPageTest.verifyRemovingOfMultipleDashlets(dashletName);
		
		sitesDashboardPage.navigateTocustomizeSiteDashboardPage();
		
		String layoutOption = dataTable.getData("Home", "LayoutOption");
		
		String layoutPositionVal = dataTable.getData("Home", "LayoutPositionValues");
		
		sitesDashboardPage.changeLayout(layoutOption, layoutPositionVal);
		
		sitesDashboardPage.clickOnOkBtnInCustomizeDashboardPage();
		
		sitesDashboardPageTest.verifyAddingOfMultipleDashlets(dashletName, colNoDetailsForAddDashlet);
		
		sitesDashboardPageTest.verifyPlacingOfDashletsInSiteDashboard(layoutOption);
	}

	@Override
	public void tearDown() {
		
	}

}
