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

public class AUT_AG_015 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_015()	{
		testParameters.setCurrentTestDescription("Test the User is not able to find 'Program Component Site' Option from Type Dropdown for Collobarative Site");
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
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		homePage.navigateToSitesTab();
		homePage.navigeteToCreateSiteMenu();
		
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		sitePageTest.verifyProgramComponentSiteOptionFromCollobSite();
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
