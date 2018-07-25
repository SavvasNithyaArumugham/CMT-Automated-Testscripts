package testscripts.avt;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC020P4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_001() {
		testParameters.setCurrentTestDescription("1.Assigning Consumer role to ALF02 and ALF01"
				+ "<br> 2. Assigning Contributor role to ALF03(not member of Media Developer Group)");

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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// String siteName = dataTable.getData("Sites", "SiteName");
		String userName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");

		homePageObj.navigateToSitesTab();
		String newsiteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(newsiteName);
		sitesPage.performInviteUserToSite(newsiteName);
		siteMemPgTest.verifySiteMemebrs(newsiteName, userName, roleName);
		sitesPage.enterIntoDocumentLibrary();
	}

	@Override
	public void tearDown() {

	}
}
