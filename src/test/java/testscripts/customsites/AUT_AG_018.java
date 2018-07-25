package testscripts.customsites;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_018 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_003()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is able to create site and all members of site are able to access those sites <br> Part1: Create a site and invete user to site"
				+"<br>2. Verify that admin user is able to invite user (Internal/External Users) to site"
				+"<br>3. Verify that user is able to send site URL to other user");
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
		//homePage.navigateToSitesTab();
		//siteTestObj.verifyCreatedSite();
		
		//homePage.navigateToSitesTab();
		
	//	sitesPage.inviteUserForSite();
		
		String siteName=sitesPage.getCreatedSiteName();
		
	//	sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.performInviteUserToSite(siteName);
		
		String invitedUserName = dataTable.getData("Sites",
				"InviteUserName");
		String role = dataTable.getData("Sites", "Role");
		
		AlfrescoSiteMembersPageTest siteMembPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		siteMembPgTest.verifySiteMemebrs(siteName, invitedUserName, role);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}