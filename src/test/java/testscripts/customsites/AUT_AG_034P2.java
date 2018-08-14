package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * @author 516188
 */
public class AUT_AG_034P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_018()
	{
		testParameters.setCurrentTestDescription("1. Verify the Request to join button should be visible for the moderate site"
				+ "<br>2. To verify that the site manager receives an email notification, when user requests to to Join a site"
				+ "<br>3. To verify that Site Manager is able to approve the request to Join the site"
				+ "<br>4. To verify that the user should get approval mail notification email after the site manager approves the request to join the site"
				+ "<br>5. The format of the email received to the site manager should be in the following manner:"
				+ "<br> Hi Manager name,"
				+ "<br> User, User name, has requested to join the sitename site."
				+ "<br> Click this link to view the request:"
				+ "<br> Request URL. Sample: http://gwipqa.pearson.com/share/pa"
				+ "<br> Part2: Another User Checks Join Moderate Site Button");
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
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		//taskPage.acceptInvitationTask();
		
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinderOption(siteName);
		sitesPage.requestToJoinSiteButton();
		sitesPage.requestToJoinSite();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}