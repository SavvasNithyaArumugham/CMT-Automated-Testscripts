package testscripts.search;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_144 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_143() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to perform search using saved queries in Saved Searches dashlet.");
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

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);		
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(
				scriptHelper);
		
		String fileName = dataTable.getData("MyFiles", "FileName");		
		String type = dataTable.getData("Search", "Type");
		String Name = dataTable.getData("Search", "SavedSearchName");
		String filePath = dataTable.getData("MyFiles", "FilePath");		
		String listXpath = homePage.savedSearchlistXpath;
		
		homePage.customizeSiteDashboard();
		if (sitesDashboardPageTest.verifyDashletAvailableInDashboard()) {
		} else {	
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		homePage.navigateToHomePage();
		homePage.searchSavedSearchType(type);
		UIHelper.waitForPageToLoad(driver);
		homePage.deleteValueFrmDashletList(listXpath, Name);
		homePage.navigateToMyFilesTab();		
		
		if (sitesPage.Checkdocument(fileName)) {
			
		}
		else{
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}
		
		homePage.navigateToAdvSearch();
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		
		appSearchPgTest.verifyFileNameSearchResults();
		appSearchPg.saveSearch();
		
		homePage.navigateToHomePage();
		homePage.searchSavedSearchType(type);
		homePage.selectValueFrmDashletList(listXpath, Name);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		appSearchPgTest.verifyFileNameSearchResults();
		

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}