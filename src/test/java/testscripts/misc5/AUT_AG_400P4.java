package testscripts.misc5;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
public class AUT_AG_400P4 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_037() {
		testParameters.setCurrentTestDescription(
				"Verify delete option is hidden for Collaborator or Contributor under More or Document actions for other users folder containing someone elses content");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String title = dataTable.getData("MyFiles", "Version");
		String siteName = dataTable.getData("Sites", "SiteName");
		String moreOptions = dataTable.getData("MyFiles", "MoreSettingsOption");
		//login as collabrator
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		sitePageTest.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(title, moreOptions);
		//create folder,file  for next Precond TC
		myFiles.createFolder(folderDetails);
		myFiles.openFolder(title);
		myFiles.uploadFile(filePath, fileName);

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}