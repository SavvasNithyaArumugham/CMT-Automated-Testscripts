package testscripts.search;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_146 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_145() {
		testParameters
				.setCurrentTestDescription("Verify that 'Saved Search' dashlet is not available under Add Dashlet for Non-Admin user on customise user dashlet page.");
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
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		String dashletName = dataTable.getData("Home", "DashletName");
		homePage.customizeSiteDashboard();
		boolean flag = sitesDashboardPageTest.verifyDashletAvailable();
		if (flag == false) {
			report.updateTestLog("Verify Dashlet", "Dashlet is not available"
					+ "<br/><b>Dashlet: </b>" + dashletName, Status.PASS);
		} else {
			report.updateTestLog("Verify Dashlet", "Dashlet is available"
					+ "<br/><b>Dashlet: </b>" + dashletName, Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}