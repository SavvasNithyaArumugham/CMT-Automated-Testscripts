package testscripts.customsites;


import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_002 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_002()
	{
		testParameters.setCurrentTestDescription("1. Verify the values available in the 'Type' dropdown on the 'Create Site' screen, when opened from User Dashboard"
				+"<br>2. Verify the values available in the 'Type' dropdown on the 'Create Site' screen, when opened from any collaboration site");
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
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		homePage.navigateToSitesTab();
		
		//sitesPage.clickOnUserDashboardCreateSite();	
		
		sitesPage.clickOnCreateSite();
		
		AlfrescoSitesPageTest sitesPageTest = new AlfrescoSitesPageTest(scriptHelper);		
	//				
		sitesPageTest.verifynewCreateSiteTypeValues();
		sitesPageTest.verifyCreateSitePageIsDisplayed();
		//sitesPage.clickOnCancelBtnInCreateSiteDailog();
		
		/*homePage.navigateToSitesTab();
		
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteName);
		
		homePage.navigateToSitesTab();
		
		sitesPage.clickOnCreateSite();
		sitesPageTest.verifyCreateSitePageIsDisplayed();
		sitesPageTest.verifyCreateSiteTypeValues();*/
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}