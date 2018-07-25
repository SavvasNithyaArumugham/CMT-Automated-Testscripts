package testscripts.marklogic;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMarklogicPageTest;
import com.pearson.automation.utils.CC_API_Helper;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Naresh Kumar Salla
 */
public class AUT_AG_008 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MARKLOGIC_008() {
		testParameters.setCurrentTestDescription("Verify if no action occurs when user retriggers the Add ResourceContainer Aspect action to RC folder when aspect is not applied properly to its children");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoAdminToolsPage adminPg = new AlfrescoAdminToolsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String aspect = dataTable.getData("MyFiles", "RelationshipName");
		String aspect1 = dataTable.getData("MyFiles", "BrowseActionName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String labelName = dataTable.getData("MyFiles", "TagName");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
	
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(folderName);
		myFiles.uploadFile(filePath, fileName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(folderName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
				moreSetOptions[0]);

		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnViewDetails(folderName);

		String markLogicUrl = myFiles.getMarkLogicUrl();
	
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPage.clickAllProperties();
		
		String baseUrl = docDetailsPage
				.getValueInEditPropertiesInputBox(labelName);
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		sitesPage.documentdetails(fileName);
		String markLogicUrlfile = myFiles.getMarkLogicUrl();

		homePage.navigateToAdminTab();
		adminPg.navigateToNodeBrowserTab();
		adminPg.searchNodeBrowserResults(markLogicUrl);
		adminPg.navigateToNodeBrowserDetailsPg(folderName);
		AlfrescoMarklogicPageTest marklogic = new AlfrescoMarklogicPageTest(
				scriptHelper);
		marklogic.verifyResourceaspect(aspect);
		
		homePage.navigateToAdminTab();
		adminPg.navigateToNodeBrowserTab();
		adminPg.searchNodeBrowserResults(markLogicUrlfile);
		adminPg.navigateToNodeBrowserDetailsPg(fileName);
		marklogic.verifyResourceaspect(aspect1);
		adminPg.getValueInNodeBrowserDetailsPg("workURN");
		
		//System.out.println(adminPg.getValueInNodeBrowserDetailsPg("workURN"));
		
		if (baseUrl.contains(adminPg.getValueInNodeBrowserDetailsPg("workURN"))) {

			report.updateTestLog("Verify " + labelName + " value",
					"URN value:"+baseUrl+" displayed successfully", Status.PASS);

		} else {
			report.updateTestLog("Verify " + labelName + " value",
					"URN value:"+baseUrl+" displayed",
					Status.FAIL);
		}

		
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(folderName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
				moreSetOptions[0]);

		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnViewDetails(folderName);

		String markLogicUrl1 = myFiles.getMarkLogicUrl();
	
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPage.clickAllProperties();
		
		String baseUrl1 = docDetailsPage
				.getValueInEditPropertiesInputBox(labelName);
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		sitesPage.documentdetails(fileName);
		String markLogicUrlfile1 = myFiles.getMarkLogicUrl();

		homePage.navigateToAdminTab();
		adminPg.navigateToNodeBrowserTab();
		adminPg.searchNodeBrowserResults(markLogicUrl1);
		adminPg.navigateToNodeBrowserDetailsPg(folderName);
		marklogic.verifyResourceaspect(aspect);
		
		homePage.navigateToAdminTab();
		adminPg.navigateToNodeBrowserTab();
		adminPg.searchNodeBrowserResults(markLogicUrlfile1);
		adminPg.navigateToNodeBrowserDetailsPg(fileName);
		marklogic.verifyResourceaspect(aspect1);
		adminPg.getValueInNodeBrowserDetailsPg("workURN");
		
		if (baseUrl1.contains(adminPg.getValueInNodeBrowserDetailsPg("workURN"))) {

			report.updateTestLog("Verify " + labelName + " value",
					"URN value:"+baseUrl+" displayed successfully", Status.PASS);

		} else {
			report.updateTestLog("Verify " + labelName + " value",
					"URN value:"+baseUrl+" displayed",
					Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}