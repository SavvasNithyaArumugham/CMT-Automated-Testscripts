package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoPearsonMailPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_328P2 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc4_027()
	{
		testParameters.setCurrentTestDescription("To check if internal users can be added to moderated site by sitemanager<br> - Part2: Navigate to the configuerd user Email ID and check Mail");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("GmailUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUserforPearsonMail(signOnPage);
		
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		AlfrescoPearsonMailPageTest pearsonMailTestObj = new AlfrescoPearsonMailPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		String siteName=sitesPage.getCreatedSiteName();
		pearsonMailObj.commonMethodForSearchWorkflowMail("Invite/Add User to Site ", siteName,
				siteName);
		
		pearsonMailObj.openMail(siteName);
		
		pearsonMailTestObj.verifyGotoSiteLinkMailBody(siteName);
	}

	@Override
	public void tearDown() {
		
	}

}
