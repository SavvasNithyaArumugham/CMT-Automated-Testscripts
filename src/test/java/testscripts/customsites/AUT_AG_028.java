package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteFinderPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_028 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_012(){
		testParameters.setCurrentTestDescription("Verify user with Site Manager role has no permission to delete site through site finder");
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
		AlfrescoSiteFinderPageTest siteFinderPageTest = new AlfrescoSiteFinderPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		if(sitesPage.siteFinderOption(siteName)){
			UIHelper.waitFor(driver);
			siteFinderPageTest.verifyDeleteOptionInSiteFinder(siteName);
		}else{
			AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
			homePage.navigateToSitesTab();
			sitesPage.createSite(siteName, "No");
			sitesPage.siteFinderOption(siteName);
			UIHelper.waitFor(driver);
			siteFinderPageTest.verifyDeleteOptionInSiteFinder(siteName);
		}
		
	}

	@Override
	public void tearDown() {
		
	}
}
