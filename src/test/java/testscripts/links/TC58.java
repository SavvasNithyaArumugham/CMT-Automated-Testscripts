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

public class TC58 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LINKS_058() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to Unlink a Folder Which contains independent linked Files/Folder.");
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

		String folderName = dataTable.getData("MyFiles", "Version");
		String folderName1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoRepositoryPage repositoryPage = new AlfrescoRepositoryPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		myFiles.openFolder(folderName);
		myFiles.deleteUploadedFile(fileName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName);
		repositoryPage.commonMethodToSelectFileInRepository(fileName);
		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOpt = dataTable.getData("Sites",
				"FilePropertyName");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOpt);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName, folderName1);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		sitesPage.enterIntoDocumentLibrary();
		repositoryPage.commonMethodToSelectFileInRepository(folderName);
		sitesPage.clickOnSelectedItems();
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOpt);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName, folderName1);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		sitesPage.enterIntoDocumentLibrary();
		repositoryPage.commonMethodToSelectFileInRepository(folderName);
		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		docLibPg.unlinkSingleLocationUsingNextBtn();
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName1);
		if (!sitesPage.Checkdocument(folderName)
				&& sitesPage.Checkdocument(fileName)) {
			report.updateTestLog("Verify Linked folder and file",
					"Linked folder is not available <b>" + folderName+"</b>"
							+ "<br>Linked file is available <b>" + fileName+"</b>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify Linked folder and file",
					"Failed to display unlinked file/folder " + folderName
							+ "," + fileName, Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}