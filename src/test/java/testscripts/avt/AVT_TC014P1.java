package testscripts.avt;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC014P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_02() {
		testParameters
				.setCurrentTestDescription("Verify user(Manager/Coordinator/Collaborator/Contributor) is able to view Streaming media option under create when user is member of Media Developers group <br>"
						+ "Verify user(Manager/Coordinator/Collaborator/Contributor) is able to view Streaming media option for JSON file created by self when user is member of Media Developers group <br>"
						+ "Verify user(Manager/Coordinator/Collaborator/Contributor) is not able to view Streaming media option under create when user is not member of Media Developers group<br>"
						+ "Verify user(Manager/Coordinator/Collaborator) is able to view Streaming media option for JSON file created by others when user is not member of Media Developers group <br>"
						+ "Part 1 : create Site and invite User and assign role");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
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

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		functionalLibrary.loginAsValidUser(signOnPage);

		String siteName = dataTable.getData("Sites", "SiteName");
		String userName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		
		homePage.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		String newsiteName = sitesPage.getCreatedSiteName();
		sitesPage.performInviteUserToSite(newsiteName);
		
		siteMemPgTest.verifySiteMemebrs(newsiteName, userName, roleName);

		sitesPage.enterIntoDocumentLibrary();
	}

	@Override
	public void tearDown() {

	}
}
