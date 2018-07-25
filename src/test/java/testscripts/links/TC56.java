package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC56 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LINKS_056() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to Unlink multiple Folder linked at one location.");
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

		String folderName1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String folderName = dataTable.getData("MyFiles", "FileName");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.deleteCreatedFolder(folderDetails);
		AlfrescoRepositoryPage repositoryPage = new AlfrescoRepositoryPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);

		myFiles.createFolder(folderDetails);

		String[] folder = folderName1.split(",");

		for (String name : folder) {
			repositoryPage.commonMethodToSelectFileInRepository(name);
		}
		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOpt = dataTable.getData("Sites",
				"FilePropertyName");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOpt);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName, folderName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();

		sitesPage.enterIntoDocumentLibrary();

		for (String name : folder) {
			repositoryPage.commonMethodToSelectFileInRepository(name);
		}

		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		docLibPg.unlinkSingleLocation();

		for (String name : folder) {
			if (sitesPage.Checkdocument(name)) {
				report.updateTestLog("Verify original folder available",
						"Original folder is available " + name, Status.PASS);
			} else {
				report.updateTestLog("Verify original file available",
						"Original file is not available " + name, Status.FAIL);
			}
		}
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName);
		for (String name : folder) {
			if (sitesPage.Checkdocument(name)) {
				report.updateTestLog("Verify Linked folder available",
						"Linked folder is available " + name, Status.FAIL);
			} else {
				report.updateTestLog("Verify Linked folder available",
						"Linked folder is not available " + name, Status.PASS);
			}
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}