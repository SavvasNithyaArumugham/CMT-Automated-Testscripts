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

public class TC57 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LINKS_057() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to Unlink multiple Folder linked at more than one location.");
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
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		UIHelper.waitFor(driver);		
		
		sitesPage.siteFinder(sourceSiteName);
		//homePageObj.navigateToSitesTab();
		//sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.deleteAllFilesAndFolders();
		//myFiles.deleteCreatedFolder(folderDetails);
		AlfrescoRepositoryPage repositoryPage = new AlfrescoRepositoryPage(
				scriptHelper);
	

		myFiles.createFolder(folderDetails);
		ArrayList<String> folderNamesList = new ArrayList<String>();
		folderNamesList = myFiles.getFolderNames(folderDetails);
		String[] folder = folderName.split(",");
		String[] targetFolder = folderName1.split(",");

		
		for (String tName : targetFolder) {
			sitesPage.enterIntoDocumentLibrary();			
			for (String name : folder) {
				repositoryPage.commonMethodToSelectFileInRepository(name);
			}
			sitesPage.clickOnSelectedItems();
			String selectedItemMenuOpt = dataTable.getData("Sites",
					"FilePropertyName");
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOpt);
			docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName, tName);
			docDetailsPageObj.clickLinkBtnInLinkPopUp();
		}

		sitesPage.enterIntoDocumentLibrary();

		for (String name : folder) {
			repositoryPage.commonMethodToSelectFileInRepository(name);
		}

		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		docLibPg.unlinkAllLocationUsingNextBtn();

		sitesPage.clickOnSelectedItems();
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		docLibPg.unlinkAllLocationUsingNextBtn();
		
		for (String name : folderNamesList) {
			if (sitesPage.Checkdocument(name)) {
				report.updateTestLog("Verify original folder available",
						"Original folder is available " + name, Status.PASS);
			} else {
				report.updateTestLog("Verify original file available",
						"Original file is not available " + name, Status.FAIL);
			}
		}

		for (String tName : targetFolder) {
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openFolder(tName);
			for (String name : folder) {
				if (sitesPage.Checkdocument(name)) {
					report.updateTestLog("Verify Linked folder available",
							"Linked folder is available " + name, Status.FAIL);
				} else {
					report.updateTestLog("Verify Linked folder available",
							"Linked folder is not available " + name,
							Status.PASS);
				}
			}
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}