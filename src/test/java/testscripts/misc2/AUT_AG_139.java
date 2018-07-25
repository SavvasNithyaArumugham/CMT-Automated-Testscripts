package testscripts.misc2;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_139 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_035() {
		testParameters.setCurrentTestDescription("1. Verify user is able to delete a folder created by unzipping any zipped folder from selected items Menu"
				+"<br>2. Verify user is able to delete a folder created by unzipping any zipped folder from Actions Item");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		folderNamesList = myFiles.getFolderNames(folderDetails);

		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();

		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();

		docLibPage.uploadFileInDocumentLibPage();
		
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String zipFileName = dataTable.getData("MyFiles", "FileName");
		String docActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		UIHelper.waitFor(driver);
		docLibPage.openAFile(zipFileName);
		docDetailsPage.commonMethodForPerformDocAction(docActionName);
		
		docDetailsPage.performUnzip(extractTo, zipFileName);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		//Verify user is able to delete a folder created by unzipping any zipped folder from selected items Menu
		for(String folderName:folderNamesList)
		{		
			UIHelper.waitFor(driver);
			docLibPageTest.verifyDeleteForUnZippedFolder(folderName, "selectedItems");
		}

		UIHelper.waitFor(driver);
		docLibPage.openAFile(zipFileName);
		docDetailsPage.commonMethodForPerformDocAction(docActionName);
		
		docDetailsPage.performUnzip(extractTo, zipFileName);
		sitesPage.enterIntoDocumentLibraryWithoutReport();

		//Verify user is able to delete a folder created by unzipping any zipped folder from Actions Item
		for (String folderName : folderNamesList) {
			UIHelper.waitFor(driver);
			docLibPageTest.verifyDeleteForUnZippedFolder(folderName, "actionItem");
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}
