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
public class AUT_AG_103 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_102() {
		
		testParameters.setCurrentTestDescription
		("ALFDEPLOY-3739_Verify the name and description label for saved search was aligned properly in basic search result page.<br>"+
		"ALFDEPLOY-3739_Verify the error remains between name and description text field once the basic search result page gets loaded.<br>"+
		"ALFDEPLOY-3739_Verify the error remains between name and description text field once the advance search result page gets loaded.<br>"+
		"ALFDEPLOY-3739_Verify the error text gets removed after providing Valid name in the name field alone of saved search page for private search.<br>"+
		"ALFDEPLOY-3739_Verify the error text gets removed after providing Valid name in the name field alone of saved search page for public search.<br>"+
		"ALFDEPLOY-3739_Verify the error text gets removed after providing Valid name in the name field alone of saved search page for private search.<br>"+
		"ALFDEPLOY-3739_Verfiy the is a mandatory field symbol(*) was placed at name label for public search.<br>"+
		"ALFDEPLOY-3739_Verify save button got disabled when user fail to provide mandatory values.<br>"+
		"ALFDEPLOY-3739_Verify save button got disabled when user provide improper name for the query to be saved.<br>");
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
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(
				scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");

		homePage.navigateToMyFilesTab();

		if (sitesPage.Checkdocument(fileName)) {
		} else {
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}

		homePage.navigateToAdvSearch();
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		appSearchPgTest.verifyFileNameSearchResults();
		
		if(appSearchPgTest.verifysavesearchlabel("Name") &&
				appSearchPgTest.verifysavesearchlabel("*") &&
				appSearchPgTest.verifysavesearchlabel("Description") && 
			appSearchPgTest.verifysavesearchlabel("Enter a valid name")){
			
			report.updateTestLog("Verify save search query labels",
					"save search query labels are as expected", Status.PASS);
			
		}else{
			report.updateTestLog("Verify save search query labels",
					"save search query labels are not as expected", Status.FAIL);
		}
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		
		//appSearchPgTest.saveSearchWithoutName();
		
		UIHelper.findAnElementbyXpath(driver, appSearchPg.saveSearchNameXpath).clear();
		UIHelper.findAnElementbyXpath(driver, appSearchPg.saveSearchDescXpath).clear();
		UIHelper.sendKeysToAnElementByXpath(driver, appSearchPg.saveSearchNameXpath, "TestX1");
		UIHelper.sendKeysToAnElementByXpath(driver, appSearchPg.saveSearchDescXpath, "TestX1");
		
		if(!appSearchPgTest.verifysavesearchlabel("Enter a valid name")){
			report.updateTestLog("Verify error text gets removed after providing Valid name",
					" error text gets removed after providing Valid name in the name field alone of saved search page", Status.PASS);
		}else{
			report.updateTestLog("Verify error text gets removed after providing Valid name",
					" error text is not  removed after providing Valid name in the name field alone of saved search page", Status.FAIL);
		}
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		
		UIHelper.findAnElementbyXpath(driver, appSearchPg.saveSearchNameXpath).clear();
		UIHelper.findAnElementbyXpath(driver, appSearchPg.saveSearchDescXpath).clear();
		UIHelper.sendKeysToAnElementByXpath(driver, appSearchPg.saveSearchNameXpath, "**");
		UIHelper.sendKeysToAnElementByXpath(driver, appSearchPg.saveSearchDescXpath, "TestX1");
		
		if(appSearchPgTest.verifysavesearchlabel("Enter a valid name")){
			report.updateTestLog("Verify error text is displayed after providing inValid name",
					" error text  is displayed after providing inValid name in the name field alone of saved search page", Status.PASS);
		}else{
			report.updateTestLog("Verify error text gets removed after providing inValid name",
					" error text  is not displayed after providing inValid name in the name field alone of saved search page", Status.FAIL);
		}
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		
		
		UIHelper.findAnElementbyXpath(driver, appSearchPg.saveSearchNameXpath).clear();
		UIHelper.findAnElementbyXpath(driver, appSearchPg.saveSearchDescXpath).clear();

		UIHelper.sendKeysToAnElementByXpath(driver, appSearchPg.saveSearchDescXpath, "TestX1");
		UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.saveSearchBtn);
		if(UIHelper.checkForAnElementbyXpath(driver, appSearchPg.saveSearchBtn)){
			UIHelper.highlightElement(driver, appSearchPg.saveSearchBtn);
			report.updateTestLog("Verify save button got disabled when user fail to provide mandatory values",
					"save button got disabled when user fail to provide mandatory values", Status.PASS);
		}else{
			report.updateTestLog("Verify save button got disabled when user fail to provide mandatory values",
					"save button not got disabled when user fail to provide mandatory values", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}