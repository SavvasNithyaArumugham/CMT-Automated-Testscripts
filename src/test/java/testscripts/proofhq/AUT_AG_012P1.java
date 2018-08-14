package testscripts.proofhq;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;

import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;

import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_012P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_012P1() {
		testParameters
				.setCurrentTestDescription("Share the Proof with Someone Else option NOT available.<br> Part 1: create Site and ivite user to site");

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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		AlfrescoSiteMembersPage siteMemPg = new AlfrescoSiteMembersPage(
				scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);

		homePage.navigateToSitesTab();

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String userName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");

		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.performInviteUserToSite(sourceSiteName);
		
		siteMemPgTest.verifySiteMemebrs(sourceSiteName, userName, roleName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.uploadFile(filePath, fileName);

		myFilesTestObj.verifyUploadedFile(fileName, "");

	/*	

		boolean isDisplayedSiteMember = siteMemPg
				.isDisplayedRequiredUserInSiteMembersList(userName, roleName);

		if (!isDisplayedSiteMember) {
			sitesPage.performInviteUserToSite(sourceSiteName);

		}*/

		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}