package testscripts.misc1;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
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
public class AUT_AG_099 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_089() {
		testParameters
				.setCurrentTestDescription("Verify that user can view all bulk download jobs on 'Job Status' dashlet");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		// testParameters.setBrowser(Browser.Chrome);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);

		if (!UIHelper.checkForAnElementbyXpath(driver, homePageObj.isBulkDashletAvailable)) {
			homePageObj.customizeSiteDashboard();

			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
			homePageObj.navigateToHomePage();
		}
		WebElement isBulkDashletAvailable = UIHelper.findAnElementbyXpath(driver,
				"//div[contains(@class,'bulk-job-status')]");
		UIHelper.scrollToAnElement(isBulkDashletAvailable);
		homePageObj.isBulkJobpresent();
		UIHelper.waitFor(driver);
		homePageObj.getBulkDownloadJobfromDashlet();
		UIHelper.waitFor(driver);
		// homePageObj.getBulkjobValues();

		AlfrescoHomePageTest appHomePgTest = new AlfrescoHomePageTest(scriptHelper);
		appHomePgTest.verifyBulkDownloadJobsFromDashlet();

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}