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
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_070 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3724_Verify the creation of new content folder when Title field in MD Pop up Link type is provided with special characters, numbers, alphanumeric and with space prefixed.<br>"
						+ "ALFDEPLOY-3724_Verify the creation of smart link object when maximum characters are entered in Title field for MD Pop up Linktype.<br>"
						+ "ALFDEPLOY-3724_Verify the navigation of user to document library upon clicking Cancel button during MD Pop up smart link object creation<br>"
						+ "ALFDEPLOY-3724_To verify user is able to perform advanced search of MD Pop up smart link object<br>"
						+ "ALFDEPLOY-3724_Verify MD Pop up smart link object and new content folder is created in site activities and verify navigation of smart link object<br>"
						+ "ALFDEPLOY-3724_Verify smart link object is not created when invalid char star or blank values are given in Title field during MD Pop up smart link object creation and check error message Title value contains illegal Characters is displayed<br>"
						+ "ALFDEPLOY-3724_Verify the user is able to create MD Pop up link when any other external URL link is given<br>"
						+ "ALFDEPLOY-3724_To Verify user is not able to download MD Pop up smart link object from document library as the download option will not be available to the user.<br>"
						+ "ALFDEPLOY-3724_Verify MD Pop up smart link object is hidden in Sharebox UI when smart link folder is shared externally.Part 1:Share smart link object folder externally<br>"
						+ "ALFDEPLOY-3724_Verify smart link object is hidden when MD Pop up smart link object is downloaded upon clicking download as zip via selected items");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);

		String type = dataTable.getData("Home", "DashletName");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		String data = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String headerType = dataTable.getData("MyFiles", "Version");
		String siteName = dataTable.getData("Sites", "SiteName");
		String slType = dataTable.getData("MyFiles", "CreateFolder");
		String error = dataTable.getData("Document_Details", "DocPropertyValues");
		String errorTitle = dataTable.getData("Document_Details", "AspectName");
		String imgRefUrl = dataTable.getData("Document_Details", "Title");
		String slSiteActivities = dataTable.getData("Search", "FileName");
		String extURLLink1 = dataTable.getData("Document_Details", "Comments");
		String extUrlTitle = dataTable.getData("MyFiles", "RelationshipName");
		String extUrlTitleUpdated = dataTable.getData("MyFiles", "BrowseActionName");

		String properties1 = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		if (data.contains(";")) {
			String splittedFileDetails[] = data.split(";");
			for (String nxtval : splittedFileDetails) {
				String splittedsmartDetails[] = nxtval.split(",");

				String smarttitle = splittedsmartDetails[0];
				String smartname = splittedsmartDetails[1];
				String smarttype = splittedsmartDetails[2];

				myFiles.createcontenttype(type);

				avsmart.clickSmartLinkType(headerType, smartname);

				avsmart.entersmarttypedata(smarttype, smarttitle, extURLLink, imgRefUrl, "caption", "1996", "", "");
				avsmart.submitbutton(smarttype, smarttitle);

				myFilesTestObj.verifyUploadedFile(smarttitle, "");

				sitesPage.enterIntoDocumentLibrary();

				if (sitesPage.documentAvailable(smarttitle)) {
					report.updateTestLog("Verify the creation of new content folder",
							"New content folder is created successfully as expected <br>" + smarttitle, Status.PASS);

				} else {
					report.updateTestLog("Verify the creation of new content folder",
							"New folder created with smart link title name not created as expected.<br> " + smarttitle,
							Status.FAIL);
				}

			}

			// verfying in site content:
			sitesPage.enterIntoSiteDashboard();
			AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
			if (!sitesDashboardPg.isSiteContentDashletAdded()) {
				sitesDashboardPg.customizeSiteDashboard();
				sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
			}

			sitesDashboardPg.selectSiteContentInSiteActivityDashlet();
			Boolean flag = homePageObj.isUserActivityDisplayedSiteContentDashlet(slSiteActivities);
			if (flag) {
				report.updateTestLog(
						"Verify Table smart link object and new content folder is created in site activities",
						"Table smart link" + slSiteActivities
								+ " object and new content folder is created in site activities",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Table smart link object and new content folder is not created in site activities",
						"Table smart link" + slSiteActivities
								+ " object and new content folder is not created in site activities",
						Status.FAIL);
			}

			homePageObj.ClickActivityDisplayedinSiteContentDashlet(slSiteActivities);
			// Verify download button in preview page
			if (!docDetailsPg.checkDownloadBtnInDocDetailsPg(slSiteActivities)) {
				report.updateTestLog("Verify download option is not displayed for smart link object in preview screen",
						"Verify download option is not displayed for smart link object in preview screen<br/><b>File Name:</b> "
								+ slSiteActivities,
						Status.PASS);
			} else {
				report.updateTestLog("Verify download option is not displayed for smart link object in preview screen",
						"Verify download option is displayed for smart link object in preview screen<br/><b>File Name:</b> "
								+ slSiteActivities,
						Status.FAIL);
			}

			// Smartlink with error
			sitesPage.enterIntoDocumentLibrary();
			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType(headerType, slType);
			// error for space prefixed title
			avsmart.entersmarttypedata(slType, " MDPopup12spaceprefixed", extURLLink, "", "", "", "", "");
			avsmart.error(error, slType);
			// error for blank title
			avsmart.entersmarttypedata(slType, "", extURLLink, "", "", "", "", "");
			avsmart.error(errorTitle, slType);
			// error for invalid char title
			avsmart.entersmarttypedata(slType, "*<>?/:|", extURLLink, "", "", "", "", "");
			avsmart.error(error, slType);
			avsmart.subcancelbtn(slType, "Cancel");
			sitesPage.InDocumentLibrary("Document Library", "Selected");

			// Advanced search of smart link object
			homePageObj.navigateToAdvSearch();
			appSearchPg.inputFileNameAdvSearch();
			appSearchPg.clickSearch();

			appSearchPgTest.verifySearchResults();
			appSearchPgTest.verifyFileNameSearchResults();

			// Other external URL
			sitesPage.enterIntoDocumentLibrary();

			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType(headerType, slType);
			avsmart.entersmarttypedata(slType, extUrlTitle, extURLLink1, imgRefUrl, "caption", "2018", "", "");
			avsmart.submitbutton(slType, extUrlTitle);
			myFilesTestObj.verifyUploadedFile(extUrlTitle, "");

			sitesPage.clickOnEditProperties(extUrlTitle);
			avsmart.editsmartlink(extUrlTitleUpdated, extURLLink1);
			UIHelper.pageRefresh(driver);
			myFilesTestObj.verifyUploadedFile(extUrlTitleUpdated, "");
			sitesPage.enterIntoDocumentLibrary();

			sitesPage.clickOnMoreSetting(extUrlTitle);
			sitesPage.clickOnSelectedItems();
			sitesPage.selectItemFromSelectedItemsMenuOption("Download as Zip");

			String zipFileName = extUrlTitle + ".zip";
			FileUtil file = new FileUtil();
			String downloadPath = properties.getProperty("DefaultDownloadPath");

			file.deleteIfFileExistsInDownloadPath(downloadPath, zipFileName);

			AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);

			docLibPage.downloadAsZipInDocumentLib(extUrlTitle);

			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
			docDetailsPageTest.verifyDownloadedFile(true, extUrlTitle);
			if (!docDetailsPageTest.checkFileinDownloadedZipFile(downloadPath, zipFileName, extUrlTitleUpdated)) {
				report.updateTestLog("Verify smart link object in downloaded zip File",
						"smart link object Not Found in downloaded zip File" + "<br><b> Actual Result:</b> FileName:"
								+ extUrlTitleUpdated,
						Status.PASS);
			} else {

				report.updateTestLog("Verify smart link object in downloaded zip File",
						"smart link object found in downloaded zip File <br> <b>Expected Result:</b> FileName:"
								+ extUrlTitleUpdated + "<br><b> Actual Result:</b> FileName:" + extUrlTitleUpdated,
						Status.FAIL);
			}

			// sharebox
			sitesPage.clickOnMoreSetting(extUrlTitle);
			docLibPg.commonMethodForClickOnMoreSettingsOption(extUrlTitle, moreSettingsOpt);
			shareboxPg.commonMethodToEnterSharingProperties(properties1, message, notification, notifyDetails);
			shareboxPg.clickOnSaveBtnInSharingPopup();
			sitesPage.openFolder(extUrlTitle);
			// Verify download button in doc. lib. page
			if (!docLibPg.commonMethodToCheckMoreSettingsOption(extUrlTitleUpdated, "Download")) {
				report.updateTestLog("Verify download option is not displayed for smart link object in doc lib page",
						"Verify download option is not displayed for smart link object in doc lib page <br/><b>File Name:</b> "
								+ extUrlTitleUpdated,
						Status.PASS);
			} else {
				report.updateTestLog("Verify download option is not displayed for smart link object in doc lib page",
						"Verify download option is displayed for smart link object in doc lib page <br/><b>File Name:</b> "
								+ extUrlTitleUpdated,
						Status.FAIL);
			}

			sitesPage.documentdetails(extUrlTitleUpdated);

			AlfrescoHomePageTest homePageTestObj = new AlfrescoHomePageTest(scriptHelper);
			homePageTestObj.verifyHelpURL();
		}

	}

	@Override
	public void tearDown() {

	}
}
