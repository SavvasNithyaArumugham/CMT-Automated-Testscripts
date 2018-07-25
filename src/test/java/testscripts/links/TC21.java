package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;

import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC21 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_013()
	{
		testParameters.setCurrentTestDescription("1). Verify that there should be no Change password option present in My profile.<br>"
				+ "2). Verify that there should be no Change pasword option present under User Name dropdown.<br>"
				+"3).Verify that no Welcome Dashboard is visible to the User after login.");
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
		AlfrescoHomePageTest homePageTstObj = new AlfrescoHomePageTest(
				scriptHelper);

		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		homePageTstObj.verifyWelcomeDashBrdNotDisp();
		homePageTstObj.verifyChngPwdNotDispInMyProfile();

		// "2). Verify that there should be no Change pasword option present
		// under User Name dropdown.

		homePageTstObj.verifyChngPwdNotDisp();
		
		// Verify that no Welcome Dashboard is visible to the User after login.
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}