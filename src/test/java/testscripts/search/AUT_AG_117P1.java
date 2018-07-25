package testscripts.search;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
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
public class AUT_AG_117P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_116() {
		testParameters
				.setCurrentTestDescription("Verify that the Users is able to  edit a Public Saved Searches query created by other user and  run it again and save as new Saved Searches query- Part 1");
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
		
		/*Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    System.out.println(""+sdf.format(cal.getTime()));*/    
	    
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);		
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(
				scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");
		String type = dataTable.getData("Search", "Type");
		
		
		homePage.customizeSiteDashboard();
		if (sitesDashboardPageTest.verifyDashletAvailableInDashboard()) {
		} else {	
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
	//	AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		homePage.navigateToSitesTab();
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(
				scriptHelper);
		sitesPage.createSite(siteNameValue, "Yes");
	//	sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.performInviteUserToSite(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		
	    myFiles.commonMethodForCreateFile("Plain Text", fileName, fileName, fileName, fileName);
		
		homePage.navigateToAdvSearch();
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		
		appSearchPgTest.verifyFileNameSearchResults();
		String searchQryname = appSearchPg.saveSearchwithUniqueNme();
		System.out.println("Pl value"+searchQryname);
		homePage.navigateToHomePage();
		homePage.searchSavedSearchType(type);
		
		appSearchPg.selectValFrmDetailedSearches(searchQryname);	
		
		//int randNo=Math.round(a)
		//comments
		//homePage.selectValFrmAllSearches("uspublic");
		
		//homePage.selectValFrmAllSearches(searchQryname);
		//homePage.selectValueFrmDashletList(listXpath, Name);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		appSearchPg.clickSearch();
		appSearchPgTest.verifyFileNameSearchResults();
		System.out.println("Pl value"+searchQryname);
		

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}