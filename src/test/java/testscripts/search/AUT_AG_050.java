package testscripts.search;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRulePage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
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

public class AUT_AG_050 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_049() {
		testParameters
				.setCurrentTestDescription("Verify that user is able search files with search filters created using Search Manager");
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

	/*	AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToAdvSearch();
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.inputFileNameAdvSearch();
		UIHelper.waitFor(driver);
		appSearchPg.clickSearch();
		UIHelper.waitFor(driver);
		appSearchPg.searchManager();
		appSearchPg.verifyDeleteImg();*/
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		myFiles.createFile(fileDetails);
		
		docDetailsPageObj.backToFolderOrDocumentPage("");
		
		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		searchObj.performSearch();
		
		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
		searchTestObj.verifyUploadedFileInSearchResults();

		searchObj.clickOnSearchManager();
		
		String createfilterDetails = dataTable.getData("Search", "CreateFilterDetails");
		searchObj.createNewFilter(createfilterDetails);
		
		searchObj.performSearch();
		
		searchTestObj.verifyCreatedFilter(createfilterDetails);
		
		searchObj.filterSearchResults(createfilterDetails);
		
		searchTestObj.verifyFileInSearchResultsForAppliedFilter();
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}
