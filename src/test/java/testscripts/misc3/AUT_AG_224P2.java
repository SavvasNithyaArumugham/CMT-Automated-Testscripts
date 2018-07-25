package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
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
public class AUT_AG_224P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_014()
	{
		testParameters.setCurrentTestDescription("Verify that when a site is set up as moderated then all users can see the site"
				+ " but only members can access the assets");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.Chrome);
		
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
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		sitesPage.getModeratedSite(siteassertValue);
	
		AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(scriptHelper);
		siteTestObj.verifyModeratedSiteNonAccess();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}