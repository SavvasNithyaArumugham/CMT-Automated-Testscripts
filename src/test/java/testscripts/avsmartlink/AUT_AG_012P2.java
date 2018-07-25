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

public class AUT_AG_012P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription(
				"1.Verify Collaborator is able to create 'MD Pop up' smart link object in document library"
						+ "<br>2.Verify the creation of 'MD Pop up' smart link object when only mandatory fields 'Title' and 'External URL Link' are given"
						+ "<br>3.Verify user is able to create 'MD Pop up' smart link object inside sub folder of document library"
						+ "<br>4. Verify the absence of Edit offline for 'MD Pop up' smart link object"
						+ "<br> 5. Verify the absence of upload new version for 'MD Pop up' smart link object<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to edit smartlink successfully on entering backslash in Image Reference URL, Caption Text, Copyright or Credit Text fields for MD Pop up smartlink");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");

		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOption1 = dataTable.getData("MyFiles", "RelationshipName");
		String moreSettingsOption2 = dataTable.getData("MyFiles", "BrowseActionName");

		functionalLibrary.loginAsValidUser(signOnPage);

		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		myFiles.createFolder(folderDetails);

		sitesPage.documentdetails(fileName);

		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("MD Pop Up ", "mdPopUp");

		avsmart.entersmarttypedata("mdPopUp", title, extURLLink, "", "", "", "", "");

		avsmart.subcancelbtn("mdPopUp", "Submit");

		try {
			Thread.sleep(2000);
		} catch (Exception e) {

		}
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(fileName);

		sitesPage.documentdetails(title);

		myFilesTestObj.verifyUploadedFile(title, "");

		sitesPage.clickOnMoreSetting(title);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(title, moreSettingsOption);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(title, moreSettingsOption2);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(title, moreSettingsOption1);

		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		searchObj.performSearch();

		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
		searchTestObj.verifyFileNameSearchResults();

		searchObj.performAdvancedSearch();
		searchTestObj.verifyFileNameSearchResults();

		sitesPage.siteFinder(siteName);
		// Edit smartlink with backslash
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(fileName);
		sitesPage.documentdetails(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
		avsmart.editsmarttypedatainduvidual("mdPopUp", "title", "MDPopUpBackslash");
		avsmart.editsmarttypedatainduvidual("mdPopUp", "extURLLink", "https://www.pearson.com");
		avsmart.editsmarttypedatainduvidual("mdPopUp", "imgrefpath", "\\\"");
		avsmart.editsmarttypedatainduvidual("mdPopUp", "caption", "\\\'");
		avsmart.editsmarttypedatainduvidual("mdPopUp", "credit", "\"\"");
		avsmart.submitbutton("mdPopUp", "MDPopUpBackslash");

		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		collectionPg.clickOnMoreSetting("MDPopUpBackslash");
		collectionPg.commonMethodForClickOnMoreSettingsOption("MDPopUpBackslash", "Edit SmartLink");
		avsmart.cancelButtonOnEditSmartlink("mdPopUp", "MDPopUpBackslash");

	}

	@Override
	public void tearDown() {

	}
}
