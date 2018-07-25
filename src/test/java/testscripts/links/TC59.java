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

public class TC59 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LINKS_059() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to Unlink multiple Folder/Files linked at one/more than one location.");
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

		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folderName1 = dataTable.getData("MyFiles", "CreateFileDetails");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoRepositoryPage repositoryPage = new AlfrescoRepositoryPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		UIHelper.waitFor(driver);
		sitesPage.siteFinder(sourceSiteName);
		//homePageObj.navigateToSitesTab();
		//sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		//AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.deleteAllFilesAndFolders();
	//	myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		myFiles.deleteUploadedFile(fileName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);		
		String[] targetFolder = folderName1.split(",");
		
		for (String name : targetFolder) {
			sitesPage.enterIntoDocumentLibrary();
			repositoryPage.commonMethodToSelectFileInRepository(fileName);
			repositoryPage.commonMethodToSelectFileInRepository(folderName);			
			sitesPage.clickOnSelectedItems();
			String selectedItemMenuOpt = dataTable.getData("Sites",
					"FilePropertyName");
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOpt);
			docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName, name);
			docDetailsPageObj.clickLinkBtnInLinkPopUp();
		}

		sitesPage.enterIntoDocumentLibrary();
		repositoryPage.commonMethodToSelectFileInRepository(fileName);
		repositoryPage.commonMethodToSelectFileInRepository(folderName);

		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		docLibPg.unlinkAllLocationUsingNextBtn();

		for (String name : targetFolder) {
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openFolder(name);

			if (sitesPage.Checkdocument(fileName)
					&& sitesPage.Checkdocument(folderName)) {
				report.updateTestLog("Verify Linked folder and file available",
						"Linked folder is available <b>" + folderName
								+ "</b><br>" + "Linked file is available <b>"
								+ fileName + "</b>", Status.FAIL);
			} else {
				report.updateTestLog("Verify Linked folder and file available",
						"Linked folder is not available <b>" + folderName
								+ "</b><br>"
								+ "Linked file is not available <b>" + fileName
								+ "</b>", Status.PASS);
			}

		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}