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

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_113 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_009(){
		testParameters.setCurrentTestDescription("Verify user is not able to view wiki pages information in 'Content I'm editing' dashlet of users dashboard");
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
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		if(!sitesPage.isCustomizeSiteMenuAvailable()){
			AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
			sitesDashboardPage.navigateToCustomizeSite();
			sitesDashboardPage.addCustomizeSiteMenus();
		}
		
		sitesPage.navigateToCustomizeSiteMenu();		
		AlfrescoMyActivitiesPage myActivityPage = new AlfrescoMyActivitiesPage(scriptHelper);
		myActivityPage.createWikiPage();
		
		homePage.navigateToHomePage();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		if(!homePage.isContentEditingDashletAdded()){
			homePage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		homePageTest.verifyUserActivityDisplayedInContentEditingDashlet("Wiki Page Created");
	}

	@Override
	public void tearDown() {
		
	}
}
