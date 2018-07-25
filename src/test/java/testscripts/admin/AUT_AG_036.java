package testscripts.admin;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteFinderPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteFinderPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_036 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_034(){
		testParameters.setCurrentTestDescription("Verify that admin user is able to delete the testing site from site manager page in admin tools");
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
		String siteName = dataTable.getData("Sites", "SiteName");
		siteName = generateUniqueSiteName(siteName);
		
		if(homePage.isAdminToolsAvailable()){
			homePage.navigateToSitesTab();
			sitesPage.createSite(siteName, "No");
			doDeleteSite(siteName);			
			AlfrescoSiteFinderPageTest siteFinderPageTest = new AlfrescoSiteFinderPageTest(scriptHelper);
			siteFinderPageTest.verifyDeletedSite(siteName);
		}else{
			report.updateTestLog("Admin Tools Option not available",
					"User dont have Admin rights", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}
	
	private void doDeleteSite(String siteName){
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToAdminTab();
		String adminToolsOption = dataTable.getData("Admin", "AdminToolsOptions");
		AlfrescoAdminToolsPage adminToolsPage = new AlfrescoAdminToolsPage(scriptHelper);
		adminToolsPage.navigateToAdminToolsOptionsLink(adminToolsOption);
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);		
		
		AlfrescoSiteFinderPage siteFinderPage = new AlfrescoSiteFinderPage(scriptHelper);
		siteFinderPage.deleteSiteInSiteManager(siteName);
	}
	
	private String generateUniqueSiteName(String siteName){
		
		String uniqueSiteName = "";
		
		String currentDateValue = DateUtil.getCurrentDateAndTime();
		uniqueSiteName = siteName+ currentDateValue.replace(" ", "")
						.replace("/", "_").replace(":", "_");
		return uniqueSiteName;
	}

}
