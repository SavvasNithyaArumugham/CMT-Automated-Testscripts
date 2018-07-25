package testscripts.mediaguide;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaGuidePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY - 3819_Verify Media guide option is displayed in more options of a folder <br>"
						+ "ALFDEPLOY - 3819_Verify Media guide option is not displayed in Create and Selected items options of Document library <br>"

						+ "ALFDEPLOY - 3819_Verify Media guide option is displayed in preview page folder details of a folder<br>"

						+ "ALFDEPLOY - 3819_Verify Media guide option is not displayed for files and Zip folders"

		);
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);

		AlfrescoDocumentDetailsPage docDetailPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMediaGuidePageTest mediaGuidePgTest = new AlfrescoMediaGuidePageTest(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		// String title = dataTable.getData("Document_Details", "FilePath");
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);

		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);
		UIHelper.switchtab(0, driver);
		docLibPg.selectAllFilesAndFolders();
		mediaGuidePgTest.verifyMediaGuideOnCreateDropDown(editSL);
		// UIHelper.pageRefresh(driver);
		docLibPg.selectAllFilesAndFolders();
		docLibPg.clickSelectedItemsMenu();
		if (docLibPg.commonMethodToCheckOnSelectedItemsMenuOption(editSL)) {
			report.updateTestLog("Verify Media guide option is not displayed in Selected items options",
					" " + editSL + " is displayed in Selected items options", Status.FAIL);
		} else {
			report.updateTestLog("Verify Media guide option is not displayed in Selected items options",
					" " + editSL + " is not displayed in Selected items options", Status.PASS);
		}

		if (mediaGuidePgTest.verifyUploadedFolder(title)) {
			sitesPage.clickOnViewDetails(title);
			docDetailPg.verifyMediaGuideOptionInPreviewFolder(editSL);
		}
		docDetailPg.backToFolderOrDocumentPage(title);
		UIHelper.pageRefresh(driver);
		for (String uploadFile : fileName) {
			myFiles.uploadFile(filePath, uploadFile);
			sitesPage.clickOnMoreSetting(uploadFile);
			sitePageTest.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(uploadFile, editSL);
		}

	}

	@Override
	public void tearDown() {

	}
}
