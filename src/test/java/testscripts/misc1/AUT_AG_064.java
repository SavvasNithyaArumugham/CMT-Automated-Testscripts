package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_064 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_054() {
		testParameters
				.setCurrentTestDescription("Verify the user who does not belongs to 'Folder calculation group' is not getting 'Calculate Size' field in selected items menu if we select folders alone in search results page");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
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

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		myFiles.createFolder(folderDetails);

		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		searchObj.performSearch();
		
		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
		searchTestObj.verifyFolderInSearchResults();
		
		searchObj.clickOnSelectBtnAndSelectOptionFromSearchResultPage();
		
		searchObj.clickOnSelectedItemsDropdownInSearchResultsPage();
		
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		searchTestObj.verifySelectedItemsMenuOptionForNegativeCase(selectedItemMenuOptVal);
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}