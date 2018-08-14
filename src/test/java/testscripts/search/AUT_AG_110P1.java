package testscripts.search;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_110P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_109() {
		testParameters
				.setCurrentTestDescription("Verify that  users is able not  to view  Saved Searches queries created by another user with Private visiblity- Save a private seacrh");
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
		AlfrescoHomePageTest homePgTest = new AlfrescoHomePageTest(
				scriptHelper);
		
		String fileName = dataTable.getData("MyFiles", "FileName");		
		String type = dataTable.getData("Search", "Type");
		String Name = dataTable.getData("Search", "SavedSearchName");
		String Desc = dataTable.getData("Search", "SavedSearchDesc");
		String filePath = dataTable.getData("MyFiles", "FilePath");	
		
		String listXpath = homePage.savedSearchlistXpath;
		String descXpath=homePage.savedSearchDesclistXpath;
		
		homePage.customizeSiteDashboard();
		if (sitesDashboardPageTest.verifyDashletAvailableInDashboard()) {
		} else {	
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		homePage.navigateToHomePage();
		homePage.searchSavedSearchType(type);
		homePage.deleteValueFrmDashletList(listXpath, Name);
		homePage.navigateToMyFilesTab();		
		
		if (sitesPage.Checkdocument(fileName)) {			
		}
		else{
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}
		//first save search
		homePage.navigateToAdvSearch();
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();		
		appSearchPgTest.verifyFileNameSearchResults();
		appSearchPg.saveSearchwithUniqueNme();		
		UIHelper.waitFor(driver);
		String srchQryVal;
		try {
			srchQryVal = new FileUtil()
					.readDataFromFile(appSearchPg.testOutputFilePathSveSrch);
			System.out.println(srchQryVal);
			homePage.navigateToHomePage();
			homePage.searchSavedSearchType(type);
			appSearchPg.selectValFrmDetailedSearches(srchQryVal);	
			UIHelper.waitFor(driver);
			appSearchPg.clickSearch();
			appSearchPgTest.verifyFileNameSearchResults();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}