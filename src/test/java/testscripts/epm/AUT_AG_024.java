package testscripts.epm;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoEPMToolPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_024 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTCAUT_AG_024() {
		testParameters
				.setCurrentTestDescription("To check if the user does not see the EPM dashlet by default when the user creates a normal site; Upon customization, EPM dashlet should be available to the user.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
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

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);		
		AlfrescoEPMToolPage epmToolsPg = new AlfrescoEPMToolPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(
				scriptHelper);	
		
		homePage.navigateToSitesTab();

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");

		AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(
				scriptHelper);
		siteTestObj.verifyCreatedSite();
		
		
		if (epmToolsPg.verifyEPMDashletInSiteDashBoard()) {
			report.updateTestLog("Verify EPM Dashlet is added by default",
					"EPM dashlet added by default", Status.FAIL);
		} else {
			report.updateTestLog("Verify EPM Dashlet is added by default",
					"EPM dashlet is not available by default", Status.PASS);
		}
		
		sitesDashboardPage.customizeSiteDashboard();		
		sitesDashboardPage.addDashletInCustomDashBoard();
		UIHelper.waitForPageToLoad(driver);
		if (epmToolsPg.verifyEPMDashletInSiteDashBoard()) {
			report.updateTestLog("Verify EPM Dashlet is added by default",
					"EPM dashlet added by default", Status.PASS);
		} else {
			report.updateTestLog("Verify EPM Dashlet is added by default",
					"EPM dashlet is not available", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}