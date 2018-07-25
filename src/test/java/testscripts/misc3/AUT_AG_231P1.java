package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyActivitiesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_231P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_021() {
		testParameters
				.setCurrentTestDescription("Validate the user able to manage permissions for a folder. Part 1: Create site and invite a member as reviewer");
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
		AlfrescoDocumentDetailsPage docLibPg = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileDetails = dataTable.getData("MyFiles",
				"CreateFileDetails");
		
		sitesPage.siteFinder(siteNameValue);
	
		
		sitesPage.performInviteUserToSite(siteNameValue);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		if (sitesPage.Checkdocument(fileName)) {
			sitesPage.documentdetails(fileName);

		} else {
			myFiles.createFile(fileDetails);
		}

		docLibPg.selectManagePermissions();

		sitesPage.clickOnAddUserGroupButton();

		sitesPage.clickOnUserRoleManager();

		sitesPage.writeUserRoleDetails();

		sitesPage.clickSaveButtonInManagePermission();		
	/*	sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.siteMembersPage();
		sitesPage.inviteUserForSite();*/

	}

	@Override
	public void tearDown() {

	}
}
