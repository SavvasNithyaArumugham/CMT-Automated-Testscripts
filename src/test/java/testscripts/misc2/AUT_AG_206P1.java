package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_206P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_039() {
		testParameters.setCurrentTestDescription(
				"To verify ,for user who is not in site owner group,Sharebox user group, Repository Manager group is not getting below options under More options"
						+ "<br>Manage Permission" + "<br>Share Folder Externally" + "<br>Copy to Repository"
						+ "<br>Publish" + "<br>Transformation Profile");

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
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folder = dataTable.getData("MyFiles", "CreateFolder");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		sitesPage.createSite(sourceSiteName, "Yes");
		String siteName = sitesPage.getCreatedSiteName();

		sitesPage.siteFinder(siteName);

		String userName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");

		sitesPage.performInviteUserToSite(siteName);

		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		siteMemPgTest.verifySiteMemebrs(siteName, userName, roleName);

		sitesPage.enterIntoDocumentLibrary();

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		// Checking for folder
		myFiles.createFolder(folder);
		UIHelper.waitForPageToLoad(driver);
		// Checking for file
		myFiles.uploadFileInMyFilesPage(filePath, fileName);

	}

	@Override
	public void tearDown() {

	}

}
