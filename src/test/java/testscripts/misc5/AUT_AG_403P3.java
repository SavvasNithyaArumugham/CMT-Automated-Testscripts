package testscripts.misc5;

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
 * Test for login with valid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_403P3 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC5_03736P3() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3736_Verify Delete button is not visible to user B who is not in Site Delete group and not a member of "
				+ "site abc when searched site via site finder");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTest = new AlfrescoSitesPageTest(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		String configName=dataTable.getData("MyActivities", "Tag");
		String role = dataTable.getData("MyActivities", "ActivityDid");
		sitesPageTest.isDeleteButtonPresentInSiteFinder(siteName, role);
		sitesPage.siteFinder(siteName);
		sitesPage.checkConfigurationOptionNotAvailable(configName);
		
		}
		
		
	@Override
	public void tearDown() {
		// Nothing to do
	}
}