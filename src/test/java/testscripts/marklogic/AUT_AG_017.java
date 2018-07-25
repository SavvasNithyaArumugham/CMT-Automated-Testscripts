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
 * @author Lokesh
 */
public class AUT_AG_017 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MARKLOGIC_017() {
		testParameters.setCurrentTestDescription("Verify user is able to apply resource container aspect to 2nd aor 3rd level of subfolder containing multiple files and folders");
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
	AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(
			scriptHelper);
	AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
	AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
			scriptHelper);

	String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
	String folderDetails1 = dataTable.getData("MyFiles", "AccessToken");
	String folderDetails2 = dataTable.getData("MyFiles", "PopUpMsg");
	String folderName = dataTable.getData("MyFiles", "Version");
	String folderName1 = dataTable.getData("MyFiles", "BrowseActionName");
	String folderName2 = dataTable.getData("MyFiles", "StatusReportValue");
	
	String aspect = dataTable.getData("MyFiles", "RelationshipName");
	String aspect1 = dataTable.getData("MyFiles", "Sort Options");
	String filePath = dataTable.getData("MyFiles", "FilePath");
	String fileName = dataTable.getData("MyFiles", "FileName");
	String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
	String[] moreSetOptions = moreSetOpt.split(",");
	String siteNameValue = dataTable.getData("Sites", "SiteName");
	String labelName = dataTable.getData("MyFiles", "TagName");

	sitesPage.siteFinder(siteNameValue);
	sitesPage.enterIntoDocumentLibrary();
	
	docLibPage.deleteAllFilesAndFolders();
	myFiles.createFolder(folderDetails);
	sitesPage.documentdetails(folderName);
	myFiles.createFolder(folderDetails1);
	sitesPage.documentdetails(folderName1);
	myFiles.createFolder(folderDetails2);
	sitesPage.documentdetails(folderName2);
	myFiles.uploadFile(filePath, fileName);
	sitesPage.documentdetails(fileName);

	sitesPage.enterIntoDocumentLibrary();
	sitesPage.documentdetails(folderName);
	sitesPage.documentdetails(folderName1);
	sitesPage.clickOnMoreSetting(folderName2);

	docLibPg.commonMethodForClickOnMoreSettingsOption(folderName2,
				moreSetOptions[0]);
	UIHelper.waitForPageToLoad(driver);
	UIHelper.waitFor(driver);
	UIHelper.waitFor(driver);
	
	
/*	sitesPage.enterIntoDocumentLibrary();
	sitesPage.documentdetails(folderName);
	sitesPage.documentdetails(folderName1);*/
	sitesPage.documentdetails(folderName2);
	UIHelper.waitForPageToLoad(driver);
	UIHelper.waitFor(driver);
	
	sitesPage.clickOnEditProperties(fileName);
	docDetailsPage.clickAllProperties();
	
	String baseUrl = docDetailsPage
			.getValueInEditPropertiesInputBox(labelName);
	
	sitesPage.enterIntoDocumentLibrary();
	sitesPage.documentdetails(folderName);
	sitesPage.documentdetails(folderName1);
	sitesPage.documentdetails(folderName2);
	sitesPage.documentdetails(fileName);
	String markLogicUrlfile = myFiles.getMarkLogicUrl();
	
	
	

	sitesPage.enterIntoDocumentLibrary();
	sitesPage.documentdetails(folderName);
	sitesPage.documentdetails(folderName1);
	sitesPage.clickOnViewDetails(folderName2);
	String markLogicUrl = myFiles.getMarkLogicUrl();

	
/*AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
	
	docDetailsPageObj.clickCopyToDocAction();
	docDetailsPageObj.selectFolderToCopyInCopyPopUp(folderName1);*/
	
	
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
	
	if (baseUrl.contains(adminPg.getValueInNodeBrowserDetailsPg("workURN"))) {

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