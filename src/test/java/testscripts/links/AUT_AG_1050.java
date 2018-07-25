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

public class AUT_AG_1050 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LINKS_053() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to Unlink single File linked at any one location.");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		//homePageObj.navigateToSitesTab();

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		UIHelper.waitFor(driver);		
		
		sitesPage.siteFinder(sourceSiteName);
		//sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		docLibPg.deleteAllFilesAndFolders();
		//myFiles.deleteCreatedFolder(folderDetails);

		myFiles.createFolder(folderDetails);

		myFiles.deleteUploadedFile(fileName);

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		myFiles.openUploadedOrCreatedFile(fileName, "");

		docDetailsPageObj.clickonLinkToInPreviewPage(fileName);

		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();

		sitesPage.enterIntoDocumentLibrary();

		folderNamesList = myFiles.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
		}

		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		myFilesTestObj.verifyUploadedFile(fileName, "");

		sitesPage.documentlib();
		UIHelper.waitFor(driver);
		AlfrescoRepositoryPage repositoryPage = new AlfrescoRepositoryPage(
				scriptHelper);
		repositoryPage.commonMethodToSelectFileInRepository(fileName);
		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		docLibPg.unlinkSingleLocation();
		sitesPage.documentlib();
		if (sitesPage.Checkdocument(fileName)) {
			report.updateTestLog("Verify original file available",
					"Original file is available", Status.PASS);
		} else {
			report.updateTestLog("Verify original file available",
					"Original file is not available", Status.FAIL);
		}
		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
		}

		if (sitesPage.Checkdocument(fileName)) {
			report.updateTestLog("Verify Linked file available",
					"Linked file is available", Status.FAIL);
		} else {
			report.updateTestLog("Verify Linked file available",
					"Linked file is not available", Status.PASS);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}