package testscripts.customsites;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteFinderPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_033 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void CUSTOMSITES_017() {
		testParameters
				.setCurrentTestDescription("1. Verify that the Full site names is displayed into 'My Sites' Page when user create sites using 17 character length site names"
						+ "<br>2. Verify that the Full site names is displayed into 'My Sites' Page when user  create sites using 25 character length site names"
						+ "<br>3. Verify that the Full site names is displayed into “My Sites” Page when user  create sites using 50 character length site names");
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
		
		AlfrescoSiteFinderPageTest siteFindTest = new AlfrescoSiteFinderPageTest(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		
		String siteName1 = "", siteName2 = "", siteName3 = "";
		if (siteName.contains(",")) {
			String splittedSiteNamess[] = siteName.split(",");

			if (splittedSiteNamess != null
					&& splittedSiteNamess.length > 1) {
				siteName1 = splittedSiteNamess[0];
				siteName2 = splittedSiteNamess[1];
				siteName3 = splittedSiteNamess[2];
			}
		}
		
		homePage.navigateToSitesTab();		
		sitesPage.openSiteFromRecentSites(siteName1);
		
		siteFindTest.verifyCharLenMySitesPage(siteName1);

		homePage.navigateToSitesTab();		
		sitesPage.openSiteFromRecentSites(siteName2);		
		siteFindTest.verifyCharLenMySitesPage(siteName2);
		
		homePage.navigateToSitesTab();		
		sitesPage.openSiteFromRecentSites(siteName3);		
		siteFindTest.verifyCharLenMySitesPage(siteName3);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}