package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * @author 516188
 */
public class AUT_AG_035P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_035P2()
	{
		testParameters.setCurrentTestDescription("1. To verify that Site Manager is able to Reject the request to Join the moderate site"
				+ "<br> 2. To verify that the user should get rejection mail notification email after the site manager rejects the request to join the site"
				+ "<br> Part2: Another User request to Join Moderate Site");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinderOption(siteName);
		sitesPage.requestToJoinSite();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}