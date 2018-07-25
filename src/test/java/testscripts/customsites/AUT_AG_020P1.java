package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_020P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_005()
	{
		testParameters.setCurrentTestDescription("Verify that Site admin is able to See the Site Members and change their permissions"
				+ "<br> Part1: Invite User to Site if not a Member");
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
		
		
			homePage.navigateToSitesTab();
			sitesPage.createSite(siteNameValue, "Yes");
			String siteName = sitesPage.getCreatedSiteName();	
			AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(scriptHelper);
		
	
		
		String invitedUserName = dataTable.getData("Sites",
				"InviteUserName");
		String role = dataTable.getData("Sites", "Role");
		
		sitesPage.performInviteUserToSite(siteName);
		
		AlfrescoSiteMembersPageTest siteMembPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		siteMembPgTest.verifySiteMemebrs(siteName, invitedUserName, role);
		
	
		String requiredPermission = dataTable.getData("Sites", "FileName");
		
		sitesPage.siteFinder(siteName);
		sitesPage.siteMembersPage();

		sitesPage.changeMemberPermission("ALF01",role,requiredPermission);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}