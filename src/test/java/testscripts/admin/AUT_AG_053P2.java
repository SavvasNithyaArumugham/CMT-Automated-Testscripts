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
public class AUT_AG_053P2 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_049(){
		testParameters.setCurrentTestDescription("Using share admin tools, Verify Administrator is able to control access from a single admin screen - Apply Visibility");
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
		String siteName = dataTable.getData("Sites", "SiteName");
		
		if(homePage.isAdminToolsAvailable()){
			homePage.navigateToAdminTab();
			String adminToolsOption = dataTable.getData("Admin", "AdminToolsOptions");
			AlfrescoAdminToolsPage adminToolsPage = new AlfrescoAdminToolsPage(scriptHelper);
			adminToolsPage.navigateToAdminToolsOptionsLink(adminToolsOption);
			
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			AlfrescoSiteFinderPage siteFinderPage = new AlfrescoSiteFinderPage(scriptHelper);
			siteFinderPage.moveNextPageInSiteManager();
			UIHelper.waitFor(driver);
			siteFinderPage.moveBackPageInSiteManager();
			siteFinderPage.changeVisibility("Private", siteName);
			UIHelper.waitFor(driver);
			siteFinderPage.changeVisibility("Moderated", siteName);
			UIHelper.waitFor(driver);
			siteFinderPage.changeVisibility("Public", siteName);	
			UIHelper.waitFor(driver);
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
