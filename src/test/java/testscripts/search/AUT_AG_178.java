package testscripts.search;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
public class AUT_AG_178 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_037() {
		testParameters.setCurrentTestDescription(
				"1.Verify relevant search results are returned when filename containing 255 characters with extension is searched via basic or advanced search."
						+ "<br>2.Verify the presence of Alfresco Vanilla default dropdown beside X - Results found label upon performing basic search for file or folder."
						+ "<br>3.Verify Alfresco Vanilla default dropdown is disabled when none of the files or folders are selected from search results."
						+ "<br>4.Verify the absence of Pearson customized default dropdown beside gear icon upon performing advanced search for file or folder."
						+ "<br>5.Verify Alfresco Vanilla default dropdown is enabled upon selecting files or folders from search results.");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);

		String siteassertValue = dataTable.getData("Sites", "SiteName");
        String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
        functionalLibrary.loginAsValidUser(signOnPage);
        sitesPage.siteFinder(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		myFiles.createFile(fileDetails);
		homePageObj.navigateToMyTasks();
		searchObj.performSearch();
		searchTestObj.verifyUploadedFileInSearchResults();
		searchObj.performAdvancedSearch();
		searchTestObj.verifyUploadedFileInSearchResults();
		searchTestObj.verifyDropDownBesidesResult();
		searchTestObj.verifySelectedItemDisabled();
		searchTestObj.verifyAbsenceDropdownBesideGearIcon();
		searchObj.selValFrmSerchResultDrpDow("All");
		searchTestObj.verifySelectedItemEnabled();

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}