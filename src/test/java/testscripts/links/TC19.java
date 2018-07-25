package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC19 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void TestTC18() {
		testParameters
				.setCurrentTestDescription("1). Verify that user belongs to Group site_admin group able to see Create Site Option on the My Sites Dashlet"
						+ "2). Verify that user belongs to Group site_admin group able to see Create Site Option  from Site Menu Bar");
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoHomePageTest homePageTstObj = new AlfrescoHomePageTest(
				scriptHelper);

		String dashletNme = dataTable.getData("Home", "DashletName");

		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		// homePage.navigateToSitesTab();

		// homePage.navigateToHomePage();
		if (!homePage.checkDashletInUserDashboard(dashletNme)) {
			homePage.addDashletInCustomDashBoard();
		}

		homePageTstObj.verifyCreatSiteLinkDisplyed();

		// homePage.removeDashletInCustomDashBoard();

		// 2). Verify that user belongs to Group site_admin group able to see
		// Create Site Option from Site Menu Bar");

		homePage.navigateToSitesTab();
		homePageTstObj.verifyCreatSiteLinkDisplyedSiteTab();

	}

	@Override
	public void tearDown() {

	}
}
