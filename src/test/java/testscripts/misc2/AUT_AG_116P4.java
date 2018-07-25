package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_116P4 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_012()
	{
		testParameters.setCurrentTestDescription("Verify Memberships Records is displayed on selecting Membership value in the Dropdown on the Site Activites Dashlet - Check Site Activities Dashlet");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites("AutoCreateRandomSite93200218065700");
		
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		if(!sitesDashboardPg.isSiteActivityDashletAdded()){
			sitesDashboardPg.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		sitesDashboardPg.selectMyActivitiesInSiteActivityDashlet();
		sitesDashboardPg.selectMyActivitiesCategory("memberships");
		sitesDashboardPg.selectMyActivitiesDateRange("today");
		
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		String activityDid = dataTable.getData("MyActivities", "ActivityDid");
		String activityName = dataTable.getData("MyActivities", "TitleName");
		
		String[] userNames = activityName.split(",");
		
		sitesDashboardPg.verifyActivitiesDisplayedInSiteActivityDashlet(userNames[0], activityDid, siteName);
		sitesDashboardPg.verifyActivitiesDisplayedInSiteActivityDashlet(userNames[1], activityDid, siteName);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}