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
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_099 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_098() {
		testParameters
				.setCurrentTestDescription("Verify that  All searches and My searches is empty once it is added to User's Dashboard");
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
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);	
		
		String listXpath = homePage.savedSearchlistXpath;
		
		String dashletName = dataTable.getData("Home", "DashletName");
		if(homePage.checkDashletInUserDashboard(dashletName))
		{
			homePage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyRemovedDashletInCustomDashBrd();
		}
		
		homePage.customizeSiteDashboard();
		sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		
		homePage.navigateToHomePage();
		if(!homePageTest.isDashletempty(listXpath)){
			report.updateTestLog(
					"Verify values in dashlet",
					"Dashlet is not empty",
					Status.FAIL);
		}
		else{
			report.updateTestLog(
					"Verify values in dashlet",
					"Dashlet is empty",
					Status.PASS);
			
		}
		

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}