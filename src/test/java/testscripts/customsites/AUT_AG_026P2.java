package testscripts.customsites;

import org.testng.annotations.Test;



















import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_026P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC57()
	{
		testParameters.setCurrentTestDescription("To verify that user is able to Accept the invitation to join the site, "
				+ "by clicking on the link provided in email notification");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("GmailUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);

		gmailobj.enterCredentials();
		gmailobj.searchmailwithsiteNameAction();

		gmailobj.verifySiteMember();
			}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}