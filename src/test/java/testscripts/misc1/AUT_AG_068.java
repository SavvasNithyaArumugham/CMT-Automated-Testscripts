package testscripts.misc1;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_068 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_058() {
		testParameters
				.setCurrentTestDescription("Verify the folder size gets calculated individually for each folders for all selected folders if we select multiple folders under document library page and click on 'Calculate total size' from selected items menu");
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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTestObj = new AlfrescoDocumentLibPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);

		homePageObj.navigateToSitesTab();

		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteNameValue);

		sitesPage.enterIntoDocumentLibrary();

		/*try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		myFiles.createFolder(folderDetails);

		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);
		for (String folderName : folderNamesList) {
			
			myFiles.openCreatedFolder(folderName);
			
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			myFilesTestObj.verifyUploadedMultipleFiles(fileName);
			
			sitesPage.enterIntoDocumentLibrary();
		}*/
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);
		
		docLibPg.selectDocumentLibItems("Folders");
		
		sitesPage.clickOnSelectedItems();
		
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
	
		sitePgTestObj.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
		
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		docLibPgTestObj.verifyFolderSize();
		
		docLibPg.clickOnOkbuttonInFolderSizePopup();
		
		for (String folderName : folderNamesList) {
			
			sitesPage.clickOnViewDetails(folderName);
			
			docDetailsPageTest.verifyFolderSizeInDocDetailsPage(folderName);
			
			sitesPage.enterIntoDocumentLibrary();
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}