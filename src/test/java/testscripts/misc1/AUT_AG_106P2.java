package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_106P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_021() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-1130_1333_Verify notification is displayed upon uploading the file with different mimetype chosen for upload new version");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		// String siteNameValue=sitesPage.getCreatedSiteName();

		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] files = dataTable.getData("MyFiles", "FileName").split(",");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] newFiles = dataTable.getData("Document_Details", "FileName").split(",");
		String selectedItemMenuOptVal = dataTable.getData("MyFiles", "RelationshipName");
		String version = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		String updatedVersion = dataTable.getData("MyFiles", "Version");

		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);

		for (String eachFile : files) {
			myFilesTestObj.verifyUploadedFile(eachFile, "");
		}

		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		for (int i = 0; i < files.length; i++) {
			sitesPage.clickOnMoreSetting(files[i]);
			docLibPg.commonMethodForClickOnMoreSettingsOption(files[i], moreSettingsOptionName);
			docLibPg.uploadNewVersionInDocLibPage(newFiles[i], filePath, version, comments);

			docLibPg.uploadNewnotification("Filename", newFiles[i], "new");
			docLibPg.uploadNewVersionButton();

			sitesPage.documentdetails(newFiles[i]);

			if (docDetailsPage.getCurrentVersion().equals(updatedVersion)) {
				report.updateTestLog(
						"Verify the file is updated with next major version", "file " + newFiles[i]
								+ " is updated with next major version " + docDetailsPage.getCurrentVersion(),
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the file is updated with next major version", "file " + newFiles[i]
								+ " is not updated with next major version" + docDetailsPage.getCurrentVersion(),
						Status.FAIL);
			}

			// Revert
			docDetailsPage.revertAnyOlderVersionToCurrentVersion();
			docDetailsPageTest.verifyPreviousVersionIntoLatestVersion();
			if (docDetailsPage.getCurrentVersion().equals("3.0")) {
				report.updateTestLog("Verify the file is reverted with version",
						"file " + newFiles[i] + " is reverted with version " + docDetailsPage.getCurrentVersion(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify the file is reverted with version",
						"file " + newFiles[i] + " is not reverted with version" + docDetailsPage.getCurrentVersion(),
						Status.FAIL);
			}
			sitesPage.enterIntoDocumentLibraryWithoutReport();
		}

	}

	@Override
	public void tearDown() {

	}

}
