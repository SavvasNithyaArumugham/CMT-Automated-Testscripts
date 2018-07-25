package testscripts.search;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_111 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_110() {
		testParameters
				.setCurrentTestDescription("Verify that  users is able to see only  his own Saved Searches queries  on the My searches in the Saved Searches");
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
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);		
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);		
		String type = dataTable.getData("Search", "Type");
		String Name = dataTable.getData("Search", "SavedSearchName");		
		homePage.customizeSiteDashboard();
		if (sitesDashboardPageTest.verifyDashletAvailableInDashboard()) {
		} else {
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		homePage.navigateToHomePage();
		homePage.searchSavedSearchType(type);
		if (appSearchPg.checkUserAvail(Name)) {
			report.updateTestLog("Check saved searches in my searches",
					"All saved searches created by user : "+Name, Status.PASS);
		} else {
			report.updateTestLog("Check saved searches in my searches",
					"Saved searches of other user is displayed in my searches",
					Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}