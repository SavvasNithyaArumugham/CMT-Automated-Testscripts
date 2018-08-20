package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_119P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_015()
	{
		testParameters.setCurrentTestDescription("To verify that a record Is displayed in the 'My Activity' dashlet, when a user leaves the site - <br> Part 1: Create Site and invite user to site");
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
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		//Modified as part of NALS
		//sitesPage.createSite(siteNameValue, "No");
		sitesPage.openSiteFromRecentSites(siteNameValue);
		//Modified as part of NALS
		sitesPage.performInviteUserToSite(siteNameValue);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
