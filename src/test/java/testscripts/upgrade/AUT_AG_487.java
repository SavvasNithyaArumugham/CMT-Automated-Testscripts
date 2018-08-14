package testscripts.upgrade;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_487 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_487(){
		testParameters.setCurrentTestDescription("Verify 'The Site Contributor Breakdown' dashlet provides all the info on what all content is being added to the site");
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
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		
		String dashletNmetoAddTest = dataTable.getData("Home", "DashletName");
		
		if(new AlfrescoSitesDashboardPage(scriptHelper).isSiteContributorBreakdownTitleAvailable()){
			report.updateTestLog("Verify Dashlet", "Dashlet verified succesfully"
					+ "<br/><b>Dashlet Name : </b>"
					+ dashletNmetoAddTest,
					Status.PASS);
		}else{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}