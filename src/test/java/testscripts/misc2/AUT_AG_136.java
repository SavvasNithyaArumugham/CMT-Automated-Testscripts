package testscripts.misc2;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_136 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_032() {
		testParameters.setCurrentTestDescription(
				"1. Verify user is able to delete a folder successfully if it has has no file that is checked out for editing purpose."
				+ "<br>2. Verify user is able to delete a folder successfully through delete option from selected items drop down menu"
				+ "<br>3. Verify user is able to delete a folder successfully through delete option from selected items drop down menu"
				+ "<br>4. Verify user is able to delete a empty folder successfully through delete option from selected items drop down menu");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(
				scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "FileName");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPage.deleteAllFilesAndFolders();

		//Verify user is able to delete a folder successfully if it has has no file that is checked out for editing purpose
		if (sitesPage.Checkdocument(folderName)) {
			appSearchPg.deletedocument(folderName);
		}

		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);

		myFiles.openCreatedFolder(folderName);
		myFiles.createFile(fileDetails);
		
		//Verify user is able to delete a folder successfully through delete option from selected items drop down menu
		sitesPage.enterIntoDocumentLibrary();

		myFiles.deleteCreatedFolder(folderDetails);
		myFilesTestObj.verifyDeletedFolder(folderDetails);
		
		if (sitesPage.Checkdocument(folderName)) {
			appSearchPg.deletedocument(folderName);
		}

		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		myFiles.deleteCreatedFolder(folderDetails);
		myFilesTestObj.verifyDeletedFolder(folderDetails);
		
		//Verify user is able to delete a folder successfully through delete option from selected items drop down menu
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		for(String folderNameVal:folderNamesList)
		{
			myFiles.openCreatedFolder(folderNameVal);
			
			myFiles.createFile(fileDetails);
			UIHelper.waitFor(driver);
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			docLibPageTest.verifyUnLockedFilesOrFolderByDeleting(folderName, "selectedItems");
		}

		//Verify user is able to delete a empty folder successfully through delete option from selected items drop down menu
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createFolder(folderDetails);
		for(String folderNameVal:folderNamesList)
		{
			UIHelper.waitFor(driver);
			docLibPageTest.verifyDeleteEmptyFolder(folderNameVal, "selectedItems");
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}