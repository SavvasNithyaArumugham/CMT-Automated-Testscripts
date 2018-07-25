package testscripts.marklogic;

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
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMarklogicPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
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
public class AUT_AG_018 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MARKLOGIC_018() {
		testParameters.setCurrentTestDescription("Verify if failure response message is returned for creation of workURN for valid document node present in document library/normal folder-when user not a member of Resource Container Manager group<br>"
				+"Verify if success response message is returned for creation of workURN for valid resource document node present in document library/RC folder<br>"
				+"Verify if success response message is returned for creation of workURN for valid document node present in document library/normal folder<br>"
				+"Verify if failure response message is returned for creation of workURN for invalid document node<br>"
				+"Verify if failure response message is returned for creation of workURN for normal folder present in document library/My files/Shared files<br>"
				+"Verify if failure response message is returned for creation of workURN for normal folder present in document library/My files/Shared files-when user not a member of Resource Container Manager group<br>"
				+"Verify if failure response message is returned for creation of workURN for RC folder present in document library/My files/Shared files<br>"+
				"ALFDEPLOY-3517_Verify the search results for work URN(created via POST request) present in My files or Shared files or document library containing colon escaped with backslash via basic search <br>" +
				"ALFDEPLOY-3517_Verify the search results for work URN(created via POST request) present in My files or Shared files or document library containing colon escaped with backslash via advanced search");
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


	AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(
			scriptHelper);
	AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(
			scriptHelper);
	AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
			scriptHelper);
	AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
	AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
	AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
	
	String filePath = dataTable.getData("MyFiles", "FilePath");
	String fileName = dataTable.getData("MyFiles", "FileName");
	String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
	String siteNameValue = dataTable.getData("Sites", "SiteName");
	String baseUrl="";
	String lableName = dataTable.getData("MyFiles", "TagName");
	String valueInEditPropInputBox = dataTable.getData("MyFiles",
			"EditTagName");
	String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
	String folderName = dataTable.getData("MyFiles", "Version");
	String Aspect = dataTable.getData("Search", "Aspect");
	String AspectProp = dataTable.getData("Search", "AspectProp");


	sitesPage.siteFinder(siteNameValue);
	sitesPage.enterIntoDocumentLibrary();
	
	docLibPage.deleteAllFilesAndFolders();
	myFiles.uploadFile(filePath, fileName);
	sitesPage.documentdetails(fileName);
	String markLogicUrlfile = myFiles.getMarkLogicUrl().replace("workspace://SpacesStore/", "");

	AlfrescoMarklogicPageTest marklogic = new AlfrescoMarklogicPageTest(
			scriptHelper);
	Response Answer =CC_API_Helper.getNegativeManifestationresponse(markLogicUrlfile);
	Map<String, Object> c1response;
	c1response = marklogic.verifyallresponsenewValue(Answer);
	
	sitesPage.enterIntoDocumentLibrary();
	sitesPage.clickOnEditProperties(fileName);
	docDetailsPage.clickAllProperties();
	
	baseUrl = docDetailsPage
			.getValueInEditPropertiesInputBox(lableName);
	
	if (baseUrl.equals(c1response.get(valueInEditPropInputBox).toString())) {

		report.updateTestLog("Verify " + lableName + " value",
				"URN value:"+baseUrl+" displayed successfully", Status.PASS);

	} else {
		report.updateTestLog("Verify " + lableName + " value",
				"URN value:"+baseUrl+" displayed",
				Status.FAIL);
	}
	
	
	searchObj.commonMethodForPerformSimpleSearch(baseUrl);
	
	
	appSearchPgTest.verifyNoSearchResults();
	
	
	homePageObj.navigateToAdvSearch();
	
	searchObj.clickShowMore();
	searchObj.inputAspectAdvSearchparam(Aspect,AspectProp,baseUrl);
	searchObj.clickSearch();
	
	appSearchPgTest.commonMethodForVerifySearchResults(fileName);	
	
	Response Answer1 =CC_API_Helper.getManifestationresponse(markLogicUrlfile);
	Map<String, Object> c1responseagain;
	c1responseagain = marklogic.verifyallresponsenewValue(Answer1);
	
	sitesPage.enterIntoDocumentLibrary();
	sitesPage.clickOnEditProperties(fileName);
	docDetailsPage.clickAllProperties();
	
	baseUrl = docDetailsPage
			.getValueInEditPropertiesInputBox(lableName);
	

	
	if (baseUrl.equals(c1response.get(valueInEditPropInputBox).toString())) {

		report.updateTestLog("Verify " + lableName + " value",
				"URN value:"+baseUrl+" displayed successfully", Status.PASS);

	} else {
		report.updateTestLog("Verify " + lableName + " value",
				"URN value:"+baseUrl+" displayed",
				Status.FAIL);
	}
	
	//For invalid document node
	Response negresponse =CC_API_Helper.getManifestationresponse("abcd");
	marklogic.verifynegativeresponsenewValue(negresponse);
	
	
	sitesPage.enterIntoDocumentLibrary();
	docLibPage.deleteAllFilesAndFolders();
	myFiles.createFolder(folderDetails);
	
	sitesPage.clickOnViewDetails(folderName);
	String markLogicUrlfolder = myFiles.getMarkLogicUrl().replace("workspace://SpacesStore/", "");
	Response negfolderresponse =CC_API_Helper.getManifestationresponse(markLogicUrlfolder);
	marklogic.verifynegativeresponsenewValue(negfolderresponse);
	
	
	
	Response folderresponse =CC_API_Helper.getNegativeManifestationresponse(markLogicUrlfolder);
	marklogic.verifynegativeresponsenewValue(folderresponse);
	
	
	sitesPage.enterIntoDocumentLibrary();
	sitesPage.clickOnMoreSetting(folderName);
	docLibPage.commonMethodForClickOnMoreSettingsOption(folderName, moreSetOpt);
	
	sitesPage.clickOnViewDetails(folderName);
	Response rcfolderresp =CC_API_Helper.getNegativeManifestationresponse(markLogicUrlfolder);
	marklogic.verifynegativeresponsenewValue(rcfolderresp);

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}