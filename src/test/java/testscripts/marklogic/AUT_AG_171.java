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
public class AUT_AG_171 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_171() {
		testParameters
				.setCurrentTestDescription("Verify the user who is member of 'Metadata Synchronization Controller' group is able to view the Add Watched content option for Assets folder under collection site");
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
		AlfrescoDocumentDetailsPage docPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();

		myFiles.createFolder(folderDetails);

		sitesPage.clickOnMoreSetting(folderName);
		if (sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0])) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
					moreSetOptions[0]);
			UIHelper.waitFor(driver);
			if (docLibPage
					.isCategoryTagAvailable(folderName, "Watched Content")) {
				report.updateTestLog("Verify '" + moreSetOptions[0]
						+ "' aspect applied for empty folder",
						"Applied Successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify '" + moreSetOptions[0]
						+ "' aspect applied for empty folder",
						"Not able to Apply", Status.FAIL);
			}
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Not able to Verify", Status.FAIL);
		}

		sitesPage.clickOnMoreSetting(folderName);
		if (sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[1])) {
			report.updateTestLog("Verify '" + moreSetOptions[1]
					+ "' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
					moreSetOptions[1]);
			UIHelper.waitFor(driver);
			if (!docLibPage.isCategoryTagAvailable(folderName,
					"Watched Content")) {
				report.updateTestLog("Verify '" + moreSetOptions[1]
						+ "' aspect removed for empty folder",
						"Removed Successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify '" + moreSetOptions[1]
						+ "' aspect removed for empty folder",
						"Not able to Remove", Status.FAIL);
			}
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[1]
					+ "' aspect applied for empty folder", "Not able to Apply",
					Status.FAIL);
		}

		sitesPage.clickOnMoreSetting(folderName);
		if ((sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0]))
				&& !(sitesPage.checkMoreSettingsOption(folderName,
						moreSetOptions[1]))) {
			report.updateTestLog(
					"Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in more options",
					"'" + moreSetOptions[0]
							+ "' option only displyed Successfully"
							+ "</br> <b> Option Verified : </b>"
							+ moreSetOptions[0], Status.PASS);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
					moreSetOptions[0]);
			UIHelper.waitFor(driver);
		} else {
			report.updateTestLog(
					"Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in more options",
					"Verification failed", Status.FAIL);
		}

		UIHelper.waitFor(driver);
		sitesPage.clickOnMoreSetting(folderName);
		if ((sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[1]))
				&& !(sitesPage.checkMoreSettingsOption(folderName,
						moreSetOptions[0]))) {
			report.updateTestLog(
					"Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in more options",
					"'" + moreSetOptions[1]
							+ "' option only displyed Successfully"
							+ "</br> <b> Option Verified : </b>"
							+ moreSetOptions[1], Status.PASS);
		} else {
			report.updateTestLog(
					"Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in more options",
					"Verification failed", Status.FAIL);
		}

		sitesPage.clickOnViewDetails(folderName);
		if ((docPage.checkFolderActionName(moreSetOptions[1]))
				&& !(docPage.checkFolderActionName(moreSetOptions[0]))) {
			report.updateTestLog(
					"Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in Folder Actions",
					"'" + moreSetOptions[1]
							+ "' option only displyed Successfully"
							+ "</br> <b> Option Verified : </b>"
							+ moreSetOptions[1], Status.PASS);
			docPage.clickDocAction(moreSetOptions[1]);

		} else {
			report.updateTestLog(
					"Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in Folder Actions",
					"Verification failed", Status.FAIL);
		}

		if ((docPage.checkFolderActionName(moreSetOptions[0]))
				&& !(docPage.checkFolderActionName(moreSetOptions[1]))) {
			report.updateTestLog(
					"Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in Folder Actions",
					"'" + moreSetOptions[0]
							+ "' option only displyed Successfully"
							+ "</br> <b> Option Verified : </b>"
							+ moreSetOptions[0], Status.PASS);

		} else {
			report.updateTestLog(
					"Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in Folder Actions",
					"Verification failed", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}