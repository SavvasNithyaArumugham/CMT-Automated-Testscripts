package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_277 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_059(){
		testParameters.setCurrentTestDescription("1.Verify that user is able to disable activity feed for sites <br>"
				+"2.Verify that user is able to navigate to site dashboard when click on site name from MySite Dashlet<br>"
				+"3.Verify that user is able to make site as favorites from Mysite dashlet");
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
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		homePage.navigateToSitesTab();
		
		String site = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(site, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		
		homePage.navigateToSitesTab();
		homePage.navigateToMySitesPage();
		
		AlfrescoMySitesPage mySitesPage = new AlfrescoMySitesPage(scriptHelper);
		mySitesPage.disableActivityFeeds(siteName);
		
homePage.navigateToHomePage();
String sitesFilterOptionVal = dataTable.getData("Home",
		"SitesFilterDropdownValue");
		String dashletNme = dataTable.getData("Home", "DashletName");
		if(!homePage.checkDashletInUserDashboard(dashletNme))
		{
			homePage.addDashletInCustomDashBoard();
		}
		
		homePage.filterSitesInMySitesDashlet(sitesFilterOptionVal);
		
		homePage.makeSiteAsFavorite(siteName);
		
		homePageTest.verifySiteInMySitesDashlet(siteName);
		
		homePage.clickOnSiteItemInMYSitesDashlet(siteName);
		
		AlfrescoSitesDashboardPageTest siteDashboardPgTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		siteDashboardPgTest.verifySiteDashboardPageIsOpenedFrmMysites();

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
