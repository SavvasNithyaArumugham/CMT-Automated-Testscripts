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
public class AUT_AG_144 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_144() {
		testParameters.setCurrentTestDescription("Validate JSON response if user copies a document from one watched content folder to another watched content folder");
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
		String sourceFolderName = dataTable.getData("MyFiles", "Version");
		String destinationFolderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String lableName = dataTable.getData("MyFiles", "TagName");
		String worklableName = dataTable.getData("MyFiles", "ExpectedSitesCount");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String categoryItem = dataTable.getData("MyFiles", "ContentCategoryItem");

		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		
		String baseURN="";
		String workbaseURN="";
		sitesPage.clickOnMoreSetting(sourceFolderName);
		if (sitesPage.checkMoreSettingsOption(sourceFolderName, moreSetOptions[0])) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
			sitesPage.commonMethodForClickOnMoreOptionLink(sourceFolderName,
					moreSetOptions[0]);
			UIHelper.waitFor(driver);
			docLibTestPage.verifyContentCategoryItemInDocLibPag(sourceFolderName, categoryItem);
			
			sitesPage.enterIntoDocumentLibrary();
			sitesPage.clickOnMoreSetting(destinationFolderName);
			sitesPage.commonMethodForClickOnMoreOptionLink(destinationFolderName,
					moreSetOptions[0]);
			UIHelper.waitFor(driver);
			docLibTestPage.verifyContentCategoryItemInDocLibPag(destinationFolderName, categoryItem);
			
			sitesPage.enterIntoDocumentLibrary();
			
			myFiles.openCreatedFolder(sourceFolderName);
			myFiles.uploadFile(filePath, fileName);
			
			docLibTestPage.verifyContentCategoryItemInDocLibPag(fileName, categoryItem);
			
			sitesPage.clickOnEditProperties(fileName);
			docDetailsPage.clickAllProperties();
			
			baseURN = docDetailsPage
					.getValueInEditPropertiesInputBox(lableName);
			
			workbaseURN = docDetailsPage
					.getValueInEditPropertiesInputBox(worklableName);
	
			String url = myFiles.getMarkLogicManifestUrl(baseURN);
			String workurl = myFiles.getMarkLogicManifestUrl(workbaseURN);

			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(sourceFolderName);
			myFiles.openAFile(fileName);
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
			
			ArrayList<ArrayList<String>> finalManifestationResponse = adminPg.verifyJSONResponse("FILE", "manifestation", baseURN, UIManifestationTagMap, mapManifest);
			
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
			
			ArrayList<ArrayList<String>> finalWorkResponse = adminPg.verifyJSONResponse("FILE", "work", workbaseURN, UIWorkTagMap, mapWork);
			
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
			myFiles.openCreatedFolder(sourceFolderName);
			
			/*myFiles.openAFile(fileName);
			docDetailsPage.clickCopyToDocAction();*/
			sitesPage.clickOnMoreSetting(fileName);
			sitesPage.commonMethodForClickOnMoreOptionLink(fileName,
					moreSetOptions[1]);
			UIHelper.waitFor(driver);
			docDetailsPage.selectFolderToCopyInCopyPopUp(fileName,
					siteNameValue, destinationFolderName);
			
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(destinationFolderName);
			docLibTestPage.verifyContentCategoryItemInDocLibPag(fileName, categoryItem);
			
			sitesPage.clickOnEditProperties(fileName);
			docDetailsPage.clickAllProperties();
			
			baseURN = docDetailsPage
					.getValueInEditPropertiesInputBox(lableName);
			
			workbaseURN = docDetailsPage
					.getValueInEditPropertiesInputBox(worklableName);
	
			url = myFiles.getMarkLogicManifestUrl(baseURN);
			workurl = myFiles.getMarkLogicManifestUrl(workbaseURN);

			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(destinationFolderName);
			myFiles.openAFile(fileName);
			markLogicUrl = myFiles.getMarkLogicUrl();
			
			homePage.navigateToAdminTab();
			adminPg.navigateToNodeBrowserTab();
			adminPg.searchNodeBrowserResults(markLogicUrl);
			adminPg.navigateToNodeBrowserDetailsPg(fileName);
			
			Response authAfterDelete = marklogic.verifyLoginAuth();

			token = CC_API_Helper.retriveValuefromJson("$..tokenId", authAfterDelete
					.getBody().asString());
			Response manifestationRespAfterDelete = CC_API_Helper.getManifestationReturnresponse(token,
					url);
			Response workRespAfterDelete = CC_API_Helper.getManifestationReturnresponse(token,
					workurl);
			System.out.println("TOKEN AFTER COPIED : " + token);
			
			Map<String, Object> mapManifestAfterDelete = marklogic.verifyallresponseValue(manifestationRespAfterDelete);
			Map<String, Object> mapWorkAfterDelete = marklogic.verifyallresponseValue(workRespAfterDelete);
			
			System.out.println("MANIFEST RESPONSE AFTER COPIED : " + mapManifestAfterDelete);
			System.out.println("WORK RESPONSE AFTER COPIED : " + mapWorkAfterDelete);
			
			Map<String, String> UIManifestationTagMapAfterCopied = adminPg.getAllValuesFromNodeBrowser(tagForManifestation);
			Map<String, String> UIWorkTagMapAfterCopied = adminPg.getAllValuesFromNodeBrowser(tagForWork);
			
			ArrayList<ArrayList<String>> finalManifestationResponseAfterDelete = adminPg.verifyJSONResponse("FILE", "manifestation", baseURN, UIManifestationTagMapAfterCopied, mapManifestAfterDelete);
			ArrayList<ArrayList<String>> finalWorkResponseAfterDelete = adminPg.verifyJSONResponse("FILE", "work", workbaseURN, UIWorkTagMapAfterCopied, mapWorkAfterDelete);
			
			if(finalManifestationResponseAfterDelete.get(1).size()==0){
				report.updateTestLog("Verify the Manifestation JSON response After copied a file to another folder",
						"Values verified successfully"
						+ "</br> <b> Verified Tag Details : </b>" +finalManifestationResponseAfterDelete.get(0).toString(), Status.PASS);
			}else{
				report.updateTestLog("Verify the Manifestation JSON response After copied a file to another folder",
						"Values not matched"
						+"</br> <b> Verified Tag Details : </b>" +finalManifestationResponseAfterDelete.get(0).toString()
						+"</br> <b> Failed Tag Details : </b>" +finalManifestationResponseAfterDelete.get(1).toString(), Status.FAIL);
			}
			
			if(finalWorkResponseAfterDelete.get(1).size()==0){
				report.updateTestLog("Verify the Work JSON response After copied a file to another folder",
						"Values verified successfully"
						+"</br> <b> Verified Tag Details : </b>" +finalWorkResponseAfterDelete.get(0).toString(), Status.PASS);
			}else{
				report.updateTestLog("Verify the JSON response After copied a file to another folder",
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