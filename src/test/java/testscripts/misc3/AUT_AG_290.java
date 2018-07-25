package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_290 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_065()
	{
		testParameters.setCurrentTestDescription("Verify that site coordinator role name is visible as 'Coordinator' to all below locations:"
													+"1. Invite user to join site."
													+"2. Set all role to: 'In user invitation window'");
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
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		
		String invitedUserName = dataTable.getData("Sites",	"InviteUserName");
		String role = dataTable.getData("Sites", "Role");
		String dropDownType = dataTable.getData("Sites", "BrowseActionName");
		
		String[] userNameToken = invitedUserName.split(",");
		String[] dropDownTypeToken = dropDownType.split(",");
		
		sitesPage.clickInviteLink();
		sitesPage.verifyUserRoleIsAvailable(userNameToken[0], role, dropDownTypeToken[0]);
		sitesPage.verifyUserRoleIsAvailable(userNameToken[1], role, dropDownTypeToken[1]);
		
		sitesPage.clickInviteUser();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}