package testscripts.misc5;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteFinderPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with valid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_404 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC5_03736P4() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3736_Verify user(Manager/Co-ordinator) is taken to error screen upon deleting the site via site"
				+ " finder as the site which is set as home page is deleted");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoSiteFinderPage siteFinderPage=new AlfrescoSiteFinderPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesDashboardPageTest siteDashboardPageTest =new AlfrescoSitesDashboardPageTest(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		String changeDashboardHome=dataTable.getData("Home", "Status");
		String role=dataTable.getData("MyActivities", "ActivityDid");
		String actualHome=dataTable.getData("Home","TasksFilterDropdownValue");
		String dashBoardName=dataTable.getData("Home","DashBoardName");
		sitesPage.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		homePage.UserMenucommonMethod(changeDashboardHome);
		homePage.navigateToHomePage();
		String createdSiteName=	sitesPage.getCreatedSiteName();
		sitesPageTest.isDeleteButtonPresentInSiteFinder(createdSiteName, role);
		try {
		siteFinderPage.deleteSiteInSiteFinder(createdSiteName);
		homePage.navigateToHomePage();
		siteDashboardPageTest.verifyErrorAfterDeleteSiteInDashboard();
		}finally {
		siteDashboardPageTest.clickOnBackToDashBoard();
		siteDashboardPageTest.verifyUserNavigatedToProgOrComponentSite(dashBoardName);
		homePage.UserMenucommonMethod(actualHome);
		}
		
		}
		
		
	@Override
	public void tearDown() {
		// Nothing to do
	}
}
