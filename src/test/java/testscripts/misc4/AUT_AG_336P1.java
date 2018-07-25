package testscripts.misc4;

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
public class AUT_AG_336P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_491P1()
	{
		testParameters.setCurrentTestDescription("Verify user is not getting activity feeds for any action performed for the site after disabling the feeds - Create Site and Invite user"
				+"Verify user is able to disable activity feeds for the sites that user is a member of from 'My Sites' option under Tab 'Sites'");
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		functionalLibrary.loginAsValidUser(signOnPage);
		
	
		homePage.navigateToSitesTab();		
		sitesPage.createSite(siteNameValue, "Yes");
		
		siteTestObj.verifyCreatedSite();
		sitesPage.inviteUserForSite();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}