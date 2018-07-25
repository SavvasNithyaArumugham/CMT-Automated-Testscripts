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


public class AUT_AG_037 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_020()
	{
		testParameters.setCurrentTestDescription("Verify that Coordinator role is added as role for user between a Manager and Collaborator role (OR)"
				+ "<br> Verify that site manager able to invite users to join site as site Coordinator (OR)"
				+ "<br> Verify that user able to 'Accept/Reject' invitation to join site with coordinator role");
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
		sitesPage.createSite(siteNameValue, "Yes");
		
		AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(scriptHelper);
		siteTestObj.verifyCreatedSite();
		
		sitesPage.inviteUserForSite();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}