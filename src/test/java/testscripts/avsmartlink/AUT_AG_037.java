package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_037 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription(
				"1.Verify user is able to edit Title and External URL for Table smart link object via edit properties and verify the correct values are populated in the content folder"
						+ "<br>2.To Verify the absence of upload new version for Table smart link object"
						+ "<br>3.To Verify the absence of Edit offline for Table smart link object"
						+ "<br>4.To verify user is able to basic search the Table smart link object"
						+ "<br>5.To verify user is able to perform advanced search of Table smart link object"
						+ "<br>6.Verify smartlink object is not created when Title field is left blank in the Table link type and check the error message Title is a required field. Please fill in a Title for your smart link is displayed"
						+ "<br>7.Verify smartlink object not created for already existing SL object &check error msg Smart link with same name already exists. Use some other name is displayed for Table link type when folder name already exists in document library"
						+ "<br>R2.0.2_ALFDEPLOY-5735_Verify Creator, Modifier and Smartlink search filters are displayed in Search result page."
						+ "<br>R2.0.2_ALFDEPLOY-5735_Verify user is able to filter the smart links using SmartLink Search"
						+ "<br>R2.0.2_ALFDEPLOY-5735_Verify user is able to filter the smart links using Creator, Modifier and SmartLink Search filters.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");
		String type = dataTable.getData("Home", "DashletName");

		String siteName = dataTable.getData("Sites", "SiteName");
		String moreSettingsOption1 = dataTable.getData("MyFiles", "RelationshipName");
		String Type = dataTable.getData("MyFiles", "Version");
		String Name = dataTable.getData("MyFiles", "CreateFolder");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		String edittitle = dataTable.getData("Parametrized_Checkpoints", "FileName");
		String editurl = dataTable.getData("Parametrized_Checkpoints", "Help URL");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		myFiles.createcontenttype(type);

		avsmart.clickSmartLinkType(Type, Name);

		avsmart.entersmarttypedata(Name, title, extURLLink, "Images", "", "", "", "");

		avsmart.submitbutton(Name, title);

		myFilesTestObj.verifyUploadedFile(title, "");

		sitesPage.clickOnMoreSetting(fileName);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(title, moreSettingsOption);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(title, moreSettingsOption1);

		sitesPage.clickOnEditProperties(title);

		avsmart.editsmartlink(edittitle, editurl);

		homePageObj.alfrescoSearch(edittitle);

		appSearchPgTest.verifySearchResults();
		appSearchPgTest.verifyFileNameSearchResults();
		// R2.0.2_Verify the filter & filter by smartlinks
		appSearchPg.verifyFilterValues("Creator", "ALF02");
		appSearchPg.verifyFilterValues("Modifier", "ALF02");
		appSearchPg.verifyFilterValues("SmartLink Search", "SmartLinks");
		appSearchPg.selectfilters("smartLink_search", "SmartLinks", "SmartLink Search");
		appSearchPgTest.verifyFileNameSearchResults();
		// Filter by creator,modifier
		homePageObj.alfrescoSearch(edittitle);

		appSearchPgTest.verifySearchResults();
		appSearchPg.selectfilters("creator", "ALF02", "Creator");
		appSearchPgTest.verifyFileNameSearchResults();

		homePageObj.alfrescoSearch(edittitle);

		appSearchPgTest.verifySearchResults();
		appSearchPg.selectfilters("modifier", "ALF02", "Modifier");
		appSearchPgTest.verifyFileNameSearchResults();

		homePageObj.navigateToAdvSearch();

		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();

		appSearchPgTest.verifySearchResults();
		appSearchPgTest.verifyFileNameSearchResults();

		sitesPage.enterIntoDocumentLibrary();

		myFiles.createcontenttype(type);

		avsmart.clickSmartLinkType(Type, Name);

		avsmart.entersmarttypedata(Name, "", extURLLink, "Images", "", "", "", "");

		avsmart.error("Title is a required field", Name);

		/*
		 * sitesPage.enterIntoDocumentLibrary();
		 * 
		 * myFiles.createcontenttype(type);
		 * 
		 * avsmart.clickSmartLinkType(Type, Name);
		 * 
		 * avsmart.entersmarttypedata( Name, title, extURLLink, "Images", "",
		 * "", "", "");
		 * 
		 * 
		 * try { String finalsubmit = avsmart.submitbtn.replace("CRAFT", Name);
		 * UIHelper.highlightElement(driver, finalsubmit);
		 * UIHelper.click(driver, finalsubmit);
		 * UIHelper.waitForVisibilityOfEleByXpath(driver, avsmart.messageXpath);
		 * String getMessage = UIHelper.getTextFromWebElement(driver,
		 * avsmart.messageXpath); System.out.println(getMessage);
		 * 
		 * if(getMessage.
		 * equalsIgnoreCase("This action is not possible in My Files")){
		 * 
		 * report.updateTestLog(" Verify creation of Smart link",
		 * "Smart Link cannot be created in my files. <br><b>Message : <b> "
		 * +getMessage, Status.PASS); }else{
		 * report.updateTestLog(" Verify creation of Smart link",
		 * "Smart Link can be created in my files which is not execpted",
		 * Status.FAIL); } } catch (Exception e) { // TODO Auto-generated catch
		 * block e.printStackTrace();
		 * report.updateTestLog(" Verify creation of Smart link",
		 * "Smart Link can be created in my files which is not execpted",
		 * Status.FAIL); }
		 */

	}
	// }

	@Override
	public void tearDown() {

	}
}
