package testscripts.marklogic;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Naresh Kumar Salla
 */
public class AUT_AG_110 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_110() {
		testParameters
				.setCurrentTestDescription("Verify the user who is member of 'Metadata Synchronization Controller' group is able to view either 'Add to watched content' or 'Remove from Watched content items' on folders at a time in view details page of folder");
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
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();

		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		sitesPage.clickOnMoreSetting(folderName);
		sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
				moreSetOptions[0]);
		docDetailsPg.commonMethodForPerformDocAction(moreSetOptions[1]);
		sitesPage.enterIntoDocumentLibrary();

		if (docLibPage.isCategoryTagAvailable(folderName, "Watched Content")) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect applied for empty folder",
					"Applied Successfully", Status.PASS);
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect applied for empty folder", "Not able to Apply",
					Status.FAIL);
		}

		sitesPage.clickOnMoreSetting(folderName);
		sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
				moreSetOptions[0]);
		docDetailsPg.commonMethodForPerformDocAction(moreSetOptions[2]);
		sitesPage.enterIntoDocumentLibrary();

		if (docLibPage.isCategoryTagAvailable(folderName, "Watched Content")) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect applied for empty folder",
					"Aspect is not removed", Status.FAIL);
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect applied for empty folder", "Aspect is removed",
					Status.PASS);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}