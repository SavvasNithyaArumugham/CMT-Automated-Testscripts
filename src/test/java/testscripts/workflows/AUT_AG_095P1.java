package testscripts.workflows;

import java.util.ArrayList;
import java.util.StringTokenizer;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_095P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void workflows_64() {
		testParameters.setCurrentTestDescription(
				"1.Verify TurnOff Permission is enabled on selecting Manage Permission Option for Top level folder."
						+ "<br>2.Invite Reviewer to Site");
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
		AlfrescoSitesPageTest sitePagTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		String userName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String title = dataTable.getData("MyFiles", "MoreSettingsOption");

		sitesPage.siteFinder(siteName);

		String newsiteName = sitesPage.getCreatedSiteName();
		sitesPage.performInviteUserToSite(newsiteName);
		siteMemPgTest.verifySiteMemebrs(newsiteName, userName, roleName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();

		ArrayList<String> allFolderNamesList = new ArrayList<String>();

		StringTokenizer token = new StringTokenizer(folderDetails, ";");
		while (token.hasMoreElements()) {
			String folderdetails = token.nextElement().toString();
			myFiles.createFolder(folderdetails);
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = myFiles.getFolderNames(folderdetails);
			for (String folderName : folderNamesList) {
				myFiles.openCreatedFolder(folderName);
				allFolderNamesList.add(folderName);
			}
		}
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(0));
		sitesPage.commonMethodForClickOnMoreOptionLink(allFolderNamesList.get(0), title);
		sitesPage.turnOffInheritPermission();
		sitePagTestObj.verifyPermissionTurnOff();
		sitesPage.clickSaveButtonInManagePermission();

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}