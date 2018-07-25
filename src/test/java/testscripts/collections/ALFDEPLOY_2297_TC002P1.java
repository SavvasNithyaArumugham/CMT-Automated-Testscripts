package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2297_TC002P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_019() {
		testParameters.setCurrentTestDescription("Do not see the action to 'Generate Realize CSVs' on a Course in the right pane of the Collection UI if I do not have permission to create content in the current Site (e.g., Consumer) - Part 1 : Invite user as Consumer");
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
	public void executeTest() 
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSiteMembersPage siteMembPg = new AlfrescoSiteMembersPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		
		
		String siteName = dataTable.getData("Sites", "SiteName");
		homePage.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		
		String site=sitesPage.getCreatedSiteName();
		
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		sitesPage.performInviteUserToSite(site);

		siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);
	/*	boolean isDisplayedSiteMember = siteMembPg.isDisplayedRequiredUserInSiteMembersList(invitedUserName, role);
		
		if(!isDisplayedSiteMember){
			
		}*/
	}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
