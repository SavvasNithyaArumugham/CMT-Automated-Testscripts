package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyActivitiesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_309P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_009() {
		testParameters
				.setCurrentTestDescription("Verify that Reviewer user can able to leave from the site. Part 3: Verify user in site menbers");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		// TODO Auto-generated method stub
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String testOutputFilePath = sitesPage.getTestOutputFilePath();
		String siteNameValue;
		try {
			siteNameValue = new FileUtil().readDataFromFile(testOutputFilePath);
			sitesPage.siteFinder(siteNameValue.trim());
			sitesPage.siteMembersPage();
			String UserName = dataTable.getData("Sites", "InviteUserName");
			String role = dataTable.getData("Sites", "Role");
			if (!sitesPage.checkSiteMember(UserName, role)) {
				report.updateTestLog("Check User in site members page",
						"User is not a member of the site", Status.PASS);
			} else {
				report.updateTestLog("Check User in site members page",
						"User is a member of the site", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}
}
