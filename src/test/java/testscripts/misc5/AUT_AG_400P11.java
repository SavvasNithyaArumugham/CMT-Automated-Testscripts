package testscripts.misc5;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with valid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_400P11 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_037() {
		testParameters.setCurrentTestDescription(
				"Verify Archive Review-PCS option is hidden under type dropdown in Create Site window for user who is the member of SITE_ADMIN group navigates to Create Site window.");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		String dropDownvalue = dataTable.getData("Sites", "CreateSiteTypeValues");
		homePageObj.navigateToSitesTab();
		sitesPage.clickOnCreateSite();
		if (!sitesPage.isSiteTypeAvailable(dropDownvalue))

		{
			report.updateTestLog(
					"Verify Archive Review-PCS option is hidden under type dropdown in Create Site window  from Sites header",
					"" + dropDownvalue + " Option is hidden under type dropdown", Status.PASS);
		} else {
			report.updateTestLog(
					"Verify Archive Review-PCS option is hidden under type dropdown in Create Site window  from Sites header",
					"" + dropDownvalue + " Option is not hidden under type dropdown", Status.FAIL);
		}
		sitesPage.clickOnCancelBtnInCreateSiteDailog();
		UIHelper.pageRefresh(driver);
		sitesPage.clickOnUserDashboardCreateSite();
		if (!sitesPage.isSiteTypeAvailable(dropDownvalue))

		{
			report.updateTestLog(
					"Verify Archive Review-PCS option is hidden under  type dropdown in Create Site window  from My Sites Dashlet",
					"" + dropDownvalue + " Option is hidden under type dropdown", Status.PASS);
		} else {
			report.updateTestLog(
					"Verify Archive Review-PCS option is hidden under type dropdown in Create Site window  from My Sites Dashlet",
					"" + dropDownvalue + " Option is not hidden under type dropdown", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}