package testscripts.marklogic;

import java.util.ArrayList;

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
public class AUT_AG_123 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_AUT_133() {
		testParameters.setCurrentTestDescription("Validate the JSON response for work and manifestation URN generated when user links a document into watched content folder from normal folder");
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoAdminToolsPage adminPg = new AlfrescoAdminToolsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoDocumentLibPageTest docLibTestPage = new AlfrescoDocumentLibPageTest(
				scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String lableName = dataTable.getData("MyFiles", "TagName");
		String valueInEditPropInputBox = dataTable.getData("MyFiles",
				"EditTagName");
		String worklableName = dataTable.getData("MyFiles", "ExpectedSitesCount");
		String folderDetails1 = dataTable.getData("MyFiles", "RelationshipName");
		String folderName1 = dataTable.getData("MyFiles", "BrowseActionName");
	

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String categoryItem = dataTable.getData("MyFiles", "ContentCategoryItem");

		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		myFiles.createFolder(folderDetails1);
		
		String baseUrl="";
		String workbaseUrl="";
		sitesPage.clickOnMoreSetting(folderName);
		if (sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0])) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
					moreSetOptions[0]);
			UIHelper.waitFor(driver);
			
			sitesPage.enterIntoDocumentLibrary();
			sitesPage.documentdetails(folderName1);
			myFiles.uploadFile(filePath, fileName);
		//	sitesPage.documentdetails(fileName);
			
			AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
			
			sitesPage.clickOnMoreSetting(fileName);
			sitesPage.commonMethodForClickOnMoreOptionLink(fileName,
					moreSetOptions[2]);
			docLibPage.performLinkToOperation(siteNameValue, folderName1);
		/*	docDetailsPageObj.clickMoveToDocAction();
			docDetailsPageObj.selectFileInMovePopUp();*/
			
			sitesPage.enterIntoDocumentLibrary();
			sitesPage.documentdetails(folderName);

			
			sitesPage.clickOnEditProperties(folderName);
			docDetailsPage.clickAllProperties();
			
			baseUrl = docDetailsPage
					.getValueInEditPropertiesInputBox(lableName);
			
			workbaseUrl = docDetailsPage
					.getValueInEditPropertiesInputBox(worklableName);
			
			if (baseUrl.contains(valueInEditPropInputBox)) {

				report.updateTestLog("Verify " + lableName + " value",
						"URN value:"+baseUrl+" displayed successfully", Status.PASS);

			} else {
				report.updateTestLog("Verify " + lableName + " value",
						"URN value:"+baseUrl+" displayed",
						Status.FAIL);
			}
			
			System.out.println(baseUrl);
			String url = myFiles.getMarkLogicManifestUrl(baseUrl);
			String workurl = myFiles.getMarkLogicManifestUrl(workbaseUrl);

			sitesPage.enterIntoDocumentLibrary();
			sitesPage.documentdetails(folderName);
			sitesPage.documentdetails(fileName);
			//sitesPage.clickOnViewDetails(folderName);
			String markLogicUrl = myFiles.getMarkLogicUrl();

			homePage.navigateToAdminTab();
			adminPg.navigateToNodeBrowserTab();
			adminPg.searchNodeBrowserResults(markLogicUrl);
			adminPg.navigateToNodeBrowserDetailsPg(fileName);

			AlfrescoMarklogicPageTest marklogic = new AlfrescoMarklogicPageTest(
					scriptHelper);
			Response auth = marklogic.verifyLoginAuth();

			String token = CC_API_Helper.retriveValuefromJson("$..tokenId", auth
					.getBody().asString());
			Response Answer = CC_API_Helper.getManifestationReturnresponse(token,
					url);
			Response workResp = CC_API_Helper.getManifestationReturnresponse(token,
					workurl);
			marklogic.verifygetManifestationResponse(Answer);
			System.out.println(CC_API_Helper.retriveValuefromJson("$..filename", Answer
					.getBody().asString()));
			marklogic.verifyresponseValue(Answer, "filename",
					adminPg.getValueInNodeBrowserDetailsPg("name"));
			marklogic.verifyallresponseValue(Answer);
			marklogic.verifyallresponseValue(workResp);
			
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Not able to Verify", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}