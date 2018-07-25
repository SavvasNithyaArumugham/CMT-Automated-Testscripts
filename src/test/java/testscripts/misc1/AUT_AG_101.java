package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_101 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_091()
	{
		testParameters.setCurrentTestDescription("Verify that the user is able to add Bulk Job status Dashlet on the User 's Dashboard");
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
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		
		String dashletName = dataTable.getData("Home", "DashletName");
		if(homePage.checkDashletInUserDashboard(dashletName))
		{
			homePage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyRemovedDashletInCustomDashBrd();
		}
		
		homePage.customizeSiteDashboard();
		sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}