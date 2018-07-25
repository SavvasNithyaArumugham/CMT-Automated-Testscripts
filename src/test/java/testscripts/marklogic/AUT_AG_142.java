package testscripts.marklogic;

import java.util.ArrayList;
import java.util.Map;

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
public class AUT_AG_142 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_142() {
		testParameters.setCurrentTestDescription("Validate the JSON response for work and manifestation URN generated if user deletes a folder from watched content folder");
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
		String worklableName = dataTable.getData("MyFiles", "ExpectedSitesCount");

		//String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderToDeleteDT = dataTable.getData("MyFiles", "FileName");
		String folderToDelete = dataTable.getData("MyFiles", "CreateFileDetails");
		
		String categoryItem = dataTable.getData("MyFiles", "ContentCategoryItem");

		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		
		String baseURN="";
		String workbaseURN="";
		sitesPage.clickOnMoreSetting(folderName);
		if (sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0])) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
					moreSetOptions[0]);
			UIHelper.waitFor(driver);
			
			docLibTestPage.verifyContentCategoryItemInDocLibPag(folderName, categoryItem);
			
			myFiles.openCreatedFolder(folderName);
			myFiles.createFolder(folderToDeleteDT);
			
			docLibTestPage.verifyContentCategoryItemInDocLibPag(folderToDelete, categoryItem);
			
			sitesPage.clickOnEditProperties(folderToDelete);
			docDetailsPage.clickAllProperties();
			
			baseURN = docDetailsPage
					.getValueInEditPropertiesInputBox(lableName);
			
			workbaseURN = docDetailsPage
					.getValueInEditPropertiesInputBox(worklableName);
	
			String url = myFiles.getMarkLogicManifestUrl(baseURN);
			String workurl = myFiles.getMarkLogicManifestUrl(workbaseURN);

			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(folderName);
			sitesPage.clickOnViewDetails(folderToDelete);
			String markLogicUrl = myFiles.getMarkLogicUrl();

			homePage.navigateToAdminTab();
			adminPg.navigateToNodeBrowserTab();
			adminPg.searchNodeBrowserResults(markLogicUrl);
			adminPg.navigateToNodeBrowserDetailsPg(folderToDelete);

			AlfrescoMarklogicPageTest marklogic = new AlfrescoMarklogicPageTest(
					scriptHelper);
			Response auth = marklogic.verifyLoginAuth();

			String token = CC_API_Helper.retriveValuefromJson("$..tokenId", auth
					.getBody().asString());
			Response manifestationResp = CC_API_Helper.getManifestationReturnresponse(token,
					url);
			Response workResp = CC_API_Helper.getManifestationReturnresponse(token,
					workurl);
			System.out.println("TOKEN BEFORE DELETE : " + token);
			
			Map<String, Object> mapManifest = marklogic.verifyallresponseValue(manifestationResp);
			Map<String, Object> mapWork = marklogic.verifyallresponseValue(workResp);
			
			System.out.println("MANIFEST RESPONSE : " + mapManifest);
			System.out.println("WORK RESPONSE : " + mapWork);
			
			String tagForManifestation = dataTable.getData("MyFiles", "TagForManifestation");
			Map<String, String> UIManifestationTagMap = adminPg.getAllValuesFromNodeBrowser(tagForManifestation);
			
			String tagForWork = dataTable.getData("MyFiles", "TagForWork");
			Map<String, String> UIWorkTagMap = adminPg.getAllValuesFromNodeBrowser(tagForWork);
			
			ArrayList<ArrayList<String>> finalManifestationResponse = adminPg.verifyJSONResponse("FOLDER", "manifestation", baseURN, UIManifestationTagMap, mapManifest);
			
			if(finalManifestationResponse.get(1).size()==0){
				report.updateTestLog("Verify the JSON response for Manifestation URN generated",
						"Values verified in Node Browser successfully"
						+"</br> <b> Verified Tag Details : </b>" +finalManifestationResponse.get(0).toString(), Status.PASS);
			}else{
				report.updateTestLog("Verify the JSON response for Manifestation URN generated",
						"Values not matched in Node Browser"
						+"</br> <b> Verified Tag Details : </b>" +finalManifestationResponse.get(0).toString()
						+"</br> <b> Failed Tag Details : </b>" +finalManifestationResponse.get(1).toString(), Status.FAIL);
			}
			
			ArrayList<ArrayList<String>> finalWorkResponse = adminPg.verifyJSONResponse("FOLDER", "work", workbaseURN, UIWorkTagMap, mapWork);
			
			if(finalWorkResponse.get(1).size()==0){
				report.updateTestLog("Verify the JSON response for Work URN generated",
						"Values verified in Node Browser successfully"
						+"</br> <b> Verified Tag Details : </b>" +finalWorkResponse.get(0).toString(), Status.PASS);
			}else{
				report.updateTestLog("Verify the JSON response for Work URN generated",
						"Values not matched in Node Browser"
						+"</br> <b> Verified Tag Details : </b>" +finalWorkResponse.get(0).toString()
						+"</br> <b> Failed Tag Details : </b>" +finalWorkResponse.get(1).toString(), Status.FAIL);
			}
			
			homePage.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(siteNameValue);
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(folderName);
			myFiles.deleteCreatedFolder(folderToDelete);
			
			homePage.navigateToAdminTab();
			adminPg.navigateToNodeBrowserTab();
			adminPg.searchNodeBrowserResults(markLogicUrl);
			
			Response authAfterDelete = marklogic.verifyLoginAuth();

			token = CC_API_Helper.retriveValuefromJson("$..tokenId", authAfterDelete
					.getBody().asString());
			Response manifestationRespAfterDelete = CC_API_Helper.getManifestationReturnresponse(token,
					url);
			Response workRespAfterDelete = CC_API_Helper.getManifestationReturnresponse(token,
					workurl);
			System.out.println("TOKEN AFTER DELETE : " + token);
			
			Map<String, Object> mapManifestAfterDelete = marklogic.verifyallresponseValue(manifestationRespAfterDelete);
			Map<String, Object> mapWorkAfterDelete = marklogic.verifyallresponseValue(workRespAfterDelete);
			
			System.out.println("MANIFEST RESPONSE AFTER DELETE : " + mapManifestAfterDelete);
			System.out.println("WORK RESPONSE AFTER DELETE : " + mapWorkAfterDelete);
			
			UIManifestationTagMap.put("status", "https://schema.pearson.com/ns/status/unavailable");
			UIWorkTagMap.put("status", "https://schema.pearson.com/ns/status/unavailable");
			
			ArrayList<ArrayList<String>> finalManifestationResponseAfterDelete = adminPg.verifyJSONResponse("FOLDER", "manifestation", baseURN, UIManifestationTagMap, mapManifestAfterDelete);
			ArrayList<ArrayList<String>> finalWorkResponseAfterDelete = adminPg.verifyJSONResponse("FOLDER", "work", workbaseURN, UIWorkTagMap, mapWorkAfterDelete);
			
			if(finalManifestationResponseAfterDelete.get(1).size()==0){
				report.updateTestLog("Verify the Manifestation JSON response After delete a Folder",
						"Values verified successfully"
						+ "</br> <b> Verified Tag Details : </b>" +finalManifestationResponseAfterDelete.get(0).toString(), Status.PASS);
			}else{
				report.updateTestLog("Verify the Manifestation JSON response After delete a Folder",
						"Values not matched"
						+"</br> <b> Verified Tag Details : </b>" +finalManifestationResponseAfterDelete.get(0).toString()
						+"</br> <b> Failed Tag Details : </b>" +finalManifestationResponseAfterDelete.get(1).toString(), Status.FAIL);
			}
			
			if(finalWorkResponseAfterDelete.get(1).size()==0){
				report.updateTestLog("Verify the Work JSON response After delete a Folder",
						"Values verified successfully"
						+"</br> <b> Verified Tag Details : </b>" +finalWorkResponseAfterDelete.get(0).toString(), Status.PASS);
			}else{
				report.updateTestLog("Verify the JSON response After delete a Folder",
						"Values not matched "
						+"</br> <b> Verified Tag Details : </b>" +finalWorkResponseAfterDelete.get(0).toString()
						+"</br> <b> Failed Tag Details : </b>" +finalWorkResponseAfterDelete.get(1).toString(), Status.FAIL);
			}
			
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