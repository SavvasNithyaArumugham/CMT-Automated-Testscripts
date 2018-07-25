package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_285P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_064() {
		testParameters
				.setCurrentTestDescription("1. Verify that user with the role as 'Coordinator' is able to perform opeartions. - Performing operations<br>"
						+"2. Verify that user with Coordinator role have permissions to Move content created by another user<br>"
						+"3. Verify that user with Coordinator role have permissions to Delete content created by another user<br>"
						+"4. Verify that user with Coordinator role have permissions to Renamecontent created by another user<br>"
						+"5. Verify that Site Coordinator is able to perform Manage Permissions.");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);

		String siteName = sitesPage.getCreatedSiteName();
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderName = dataTable.getData("MyFiles", "CreateFolder");

		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		// move
		sitesPage.documentdetails(fileName);
		docDetailsPageObj.clickMoveToDocAction();
		docDetailsPageObj.selectFileInMove(siteName);
		// Manage permissions

		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnManagePermissionLink(fileName);
		sitesPage.clickOnAddUserGroupButton("SSO3");
		sitesPage.clickOnUserRole("Coordinator", "SSO3");
		
	/*	docDetailsPage.removeInheritPermissions();
		sitesPage.clickOnAddUserGroupButton();
		sitesPage.clickOnUserRoleBtn();
		sitesPage.writeUserRoleDetails();*/
		sitesPage.clickSaveButtonInManagePermission();
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		
		String renameForFile = dataTable.getData("MyFiles", "AccessToken");
		myFiles.performRenameToFolderOrFile(fileName, renameForFile);
		
		myFilesTestObj.validateRenamedFile(renameForFile);
		
		docLibPg.deleteAllFilesAndFolders();
/*		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);*/
		if (sitesPage.Checkdocument(renameForFile)) {

			report.updateTestLog("Verify document deleted",
					"Document is not deleted" + "<br /><b> Document : </b>"
							+ fileName, Status.FAIL);

		} else {
			report.updateTestLog("Verify document deleted",
					"Document deleted successfully"
							+ "<br /><b> Document : </b>" + fileName,
					Status.PASS);

		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}