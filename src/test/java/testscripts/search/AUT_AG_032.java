package testscripts.search;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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
public class AUT_AG_032 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_031() {
		testParameters
				.setCurrentTestDescription("Verify the aspects fields is entered with values on rerunning the Saved Search Query for Advance Search");
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
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
		
		if(sitesPage.Checkdocument(fileName)){
			
		}else {
			
		
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			
			myFiles.createFile(fileDetails);
			docDetailsPage.performManageAspectsDocAction();
			docDetailsPage.addAspectsAndApllyChangesToAFile();
			docDetailsPage.performEditPropertiesDocAction();
			docDetailsPage.enterDataAndSaveIntoEditProperties();
			
		}

		//first save search
		homePage.navigateToAdvSearch();
		UIHelper.sendKeysToAnElementByXpath(driver, appSearchPg.fullTextXpath, fileName);
	//	appSearchPg.inputKeywordSearch();
		appSearchPg.inputAspectAdvSearch();
		appSearchPg.clickAddAspectBtn();
		appSearchPg.inputMultiAspectAdvSearch();
		appSearchPg.clickSearch();		
	
		appSearchPg.saveSearch();	
			
		homePage.navigateToHomePage();
		homePage.searchSavedSearchType(type);
		appSearchPg.selectValFrmDetailedSearches(Name);	
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				appSearchPg.aspectPanelXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.AspectValueXpath);
		String AspectProp = dataTable.getData("Search", "AspectProp");
		String Aspect1Prop = dataTable.getData("Search", "AspectProp1");
		if(UIHelper.findAnElementbyXpath(driver, appSearchPg.fullTextXpath).getAttribute("value").equals(fileName)){
			UIHelper.highlightElement(driver, appSearchPg.fullTextXpath);
			UIHelper.highlightElement(driver, appSearchPg.AspectValueXpath);
			report.updateTestLog("Verify the Full Text field for Saved Search Query",
					"Full Text field on rerunning the Saved Search Query is displayed. <br><b> Search Query  :</b> " 
			+UIHelper.findAnElementbyXpath(driver, appSearchPg.fullTextXpath).getAttribute("value")
			+ "<br /><b>Aspect Properties 1 :</b>" +AspectProp 	+ "<br /><b>Aspect Properties 2:</b>"
					+ Aspect1Prop, Status.PASS);
			
		}else{
			report.updateTestLog("Verify the Full Text field for Saved Search Query",
					"Full Text field on rerunning the Saved Search Query is displayed", Status.FAIL);
		}
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}