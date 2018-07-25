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
public class AUT_AG_137 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_AUT_137() {
		testParameters.setCurrentTestDescription("Validate the JSON response for work and manifestation URN generated if user edits metadata on a watched content folder");
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
		AlfrescoDocumentLibPageTest docLibTestPage = new AlfrescoDocumentLibPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoAdminToolsPage adminPg = new AlfrescoAdminToolsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String editPropLableNames = dataTable.getData("MyFiles", "TagName");
		String valueInEditPropInputBox = dataTable.getData("MyFiles",
				"EditTagName");

		String categoryItem = dataTable.getData("MyFiles",
				"ContentCategoryItem");
		
		String urnLabelName = "", workURNLabelName = "";
		if(editPropLableNames.contains(","))
		{
			String splittedEditPropLabels[] = editPropLableNames.split(",");
			urnLabelName = splittedEditPropLabels[0];
			workURNLabelName = splittedEditPropLabels[1];
		}
		
		String expectedURNValue = "", expectedWorkURNValue = "";
		if(valueInEditPropInputBox.contains(","))
		{
			String splittedEditPropLabels[] = valueInEditPropInputBox.split(",");
			expectedURNValue = splittedEditPropLabels[0];
			expectedWorkURNValue = splittedEditPropLabels[1];
		}
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		String folderName="";
		if(folderNamesList!=null && folderNamesList.size()>1)
		{
			folderName = folderNamesList.get(0);
		}
		
		String actualURNUrl="", actualWorkURL="";
		
		sitesPage.clickOnMoreSetting(folderName);
		if (sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0])) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
					moreSetOptions[0]);
			UIHelper.waitFor(driver);
			
			docLibTestPage.verifyContentCategoryItemInDocLibPag(folderName, categoryItem);
			
			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.clickOnMoreLinkOptions(folderName, moreSetOptions[1]);
			docDetailsPage.clickAllProperties();
			
			actualURNUrl = docDetailsPage
					.getValueInEditPropertiesInputBox(urnLabelName);
			actualWorkURL = docDetailsPage
					.getValueInEditPropertiesInputBox(workURNLabelName);

			if (actualURNUrl.contains(expectedURNValue)) {

				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualURNUrl + " displayed successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualURNUrl + " displayed", Status.FAIL);
			}

			if (actualWorkURL.contains(expectedWorkURNValue)) {

				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualWorkURL + " displayed successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualWorkURL + " displayed", Status.FAIL);
			}
			
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Not able to Verify", Status.FAIL);
		}

		String urnUrl = myFiles.getMarkLogicManifestUrl(actualURNUrl);
		String workURNUrl = myFiles.getMarkLogicManifestUrl(actualWorkURL);

		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnViewDetails(folderName);
		String markLogicUrl = myFiles.getMarkLogicUrl();

		homePage.navigateToAdminTab();
		adminPg.navigateToNodeBrowserTab();
		adminPg.searchNodeBrowserResults(markLogicUrl);
		adminPg.navigateToNodeBrowserDetailsPg(folderName);

		AlfrescoMarklogicPageTest marklogic = new AlfrescoMarklogicPageTest(
				scriptHelper);
		Response auth = marklogic.verifyLoginAuth();

		String token = CC_API_Helper.retriveValuefromJson("$..tokenId", auth
				.getBody().asString());
		
		Response urnAnswer = CC_API_Helper.getManifestationReturnresponse(token,
				urnUrl);
		marklogic.verifygetManifestationResponse(urnAnswer);
		marklogic.verifyallresponseValue(urnAnswer);
		
		Response workURNAnswer = CC_API_Helper.getManifestationReturnresponse(token,
				workURNUrl);
		marklogic.verifygetManifestationResponse(workURNAnswer);
		marklogic.verifyallresponseValue(workURNAnswer);

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}