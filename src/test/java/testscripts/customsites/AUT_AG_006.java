package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_006 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_006()
	{
		testParameters.setCurrentTestDescription("1. Verify that user can create a component site in a program site"
				+ "<br>2. To verify the GUI of Program Component dashlet (OR) Verify whether Site Dashboard for a Program Component site is rendered properly (OR)"
				+ " Verify whether Program Component dashlet is available by default while creating a Program Component site"
				+ "<br>3. Verify the values available in the 'Contents' dropdown on 'Program Component' dashlet"
				+ "<br>4. Verify the default values in all the fields on 'Program Component' dashlet, when a program component site is created"
				+ "<br>5. Verify that the 'Program Component Title' field display site name as entered on 'Create Site' screen");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(
				scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		
		String siteDetails = dataTable.getData("Sites", "CreateMultipleSites");
		
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails);
		
		String dashletName = dataTable.getData("Sites", "SiteDefaultDashlets");

		if (dashletName.contains(",")) {
			String splittedDashletNames[] = dashletName.split(",");

			if (splittedDashletNames != null && splittedDashletNames.length > 2) {
				if (sitesDashboardPage
						.checkDashletInSiteDashboard(splittedDashletNames[0])
						&& sitesDashboardPage
								.checkDashletInSiteDashboard(splittedDashletNames[1])
						&& sitesDashboardPage
								.checkDashletInSiteDashboard(splittedDashletNames[2])) {
					report.updateTestLog(
							"Verify 'Site Dashboard' page of 'Program Component Site' is rendered properly without any UI distortion",
							"Site Dashboard page of Program Component Site rendered properly without any UI distortion",
							Status.PASS);
				} else {
					
					report.updateTestLog(
							"Verify 'Site Dashboard' page of 'Program Component Site' is rendered properly without any UI distortion",
							"Site Dashboard page of Program Component Site not rendered properly",
							Status.FAIL);
				}
			}
		}
		else
		{
			if (sitesDashboardPage
					.checkDashletInSiteDashboard(dashletName)) {
				report.updateTestLog(
						"Verify 'Site Dashboard' page of 'Program Component Site' is rendered properly without any UI distortion",
						"Site Dashboard page of Program Component Site rendered properly without any UI distortion",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify 'Site Dashboard' page of 'Program Component Site' is rendered properly without any UI distortion",
						"Site Dashboard page of Program Component Site not rendered properly",
						Status.FAIL);
			}
		}
		
		String fieldNames = dataTable.getData("Sites", "SiteDashletFieldNames");
		sitesDashboardPageTest.verifyProgramComponentSiteFields(fieldNames);
		
		String programComponentSiteFieldValue = dataTable.getData("Sites", "SiteDashletFieldValues");
		sitesDashboardPageTest.verifyProgramComponentSiteFieldValues(programComponentSiteFieldValue);
		
		String programComponentSiteFieldDefaultValues = dataTable.getData("Sites", "DashletFieldsDefaultValues");
		sitesDashboardPageTest.verifyProgramComponentSiteDefaultFieldValues(programComponentSiteFieldDefaultValues);
		
		sitesDashboardPageTest.verifyProgramComponentTitleField();
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}