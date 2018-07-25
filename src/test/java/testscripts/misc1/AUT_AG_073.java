package testscripts.misc1;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
public class AUT_AG_073 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_063() {
		testParameters
				.setCurrentTestDescription("Verify the folder size gets displayed in th popup by selecting multiple folders inside a folder under document library page and click on calculate size from selected items menu");
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

		homePageObj.navigateToSitesTab();

		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteNameValue);

		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String parentFolderDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		myFiles.createFolder(parentFolderDetails);
		myFilesTestObj.verifyCreatedFolder(parentFolderDetails);
		
		String nestedfolderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		ArrayList<String> ParentfolderNamesList = myFiles
				.getFolderNames(parentFolderDetails);
		ArrayList<String> nestedfolderNamesList = myFiles
				.getFolderNames(nestedfolderDetails);
		
		for (String folderName : ParentfolderNamesList) {
			
			myFiles.openCreatedFolder(folderName);
			
			myFiles.createFolder(nestedfolderDetails);
			myFilesTestObj.verifyCreatedFolder(nestedfolderDetails);
			
			for (String childfolderName : nestedfolderNamesList) {
				
				myFiles.openCreatedFolder(childfolderName);
			
				String filePath = dataTable.getData("MyFiles", "FilePath");
				String fileName = dataTable.getData("MyFiles", "FileName");
				myFiles.uploadFileInMyFilesPage(filePath, fileName);
				
				myFilesTestObj.verifyUploadedMultipleFiles(fileName);
				
				myFiles.backToFolderOrDocumentPage(folderName);
			}
			
			docLibPg.selectDocumentLibItems("Folders");
			
			sitesPage.clickOnSelectedItems();
			
			String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
			sitePgTestObj.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
			
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
			
			docLibPgTestObj.verifyFolderSize();
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}