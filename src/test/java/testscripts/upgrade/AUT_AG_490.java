package testscripts.upgrade;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_490 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_490(){
		testParameters.setCurrentTestDescription("Verify user is able to disable activity feeds for the sites that user is a member of from 'My Sites' option under Tab 'Sites'");
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
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
//		
//		if(!sitesPage.siteFinderOption(siteName)){
//			homePage.navigateToSitesTab();
//			sitesPage.createSite(siteName, "No");
//		}
		
		homePage.navigateToSitesTab();
		homePage.navigateToMySitesPage();
		
		AlfrescoMySitesPage mySitesPage = new AlfrescoMySitesPage(scriptHelper);
		mySitesPage.disableActivityFeeds(siteName);
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
