package testscripts.admin;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteFinderPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteFinderPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_054P2 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_050(){
		testParameters.setCurrentTestDescription("Using share admin tools,Verify Administrator is able to become a site manager from a single admin screen - Apply To become Manager");
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
		AlfrescoSiteFinderPage siteFinderPage = new AlfrescoSiteFinderPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoSiteMembers();
		if(siteFinderPage.isSiteManagerApplied()){
			System.out.println("INSIDE IF");
			siteFinderPage.removeSiteManager();
		}
		
		if(homePage.isAdminToolsAvailable()){
			homePage.navigateToAdminTab();
			String adminToolsOption = dataTable.getData("Admin", "AdminToolsOptions");
			AlfrescoAdminToolsPage adminToolsPage = new AlfrescoAdminToolsPage(scriptHelper);
			adminToolsPage.navigateToAdminToolsOptionsLink(adminToolsOption);
			
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			siteFinderPage.moveNextPageInSiteManager();
			UIHelper.waitFor(driver);
			siteFinderPage.moveBackPageInSiteManager();
			UIHelper.waitFor(driver);
			
			siteFinderPage.applyToBecomeSiteManager(siteName);
			
			UIHelper.waitFor(driver);
			
			homePage.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(siteName);
			sitesPage.enterIntoSiteMembers();			
			
			AlfrescoSiteFinderPageTest siteFinderPageTest = new AlfrescoSiteFinderPageTest(scriptHelper);
			siteFinderPageTest.verifyManagerAppliedInSiteManager(siteName);
			
		}else{
			report.updateTestLog("Admin Tools Option not available",
					"User dont have Admin rights", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
