package testscripts.avsmartlink;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.Random;
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

public class AUT_AG_28 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription(
				"1.ALFDEPLOY-3996_Verify the creation of 3rd party interactive smartlink object without image reference Url via Create smartlink "
						+ "2.ALFDEPLOY-3996_Verify the creation of MD pop up smartlink object without image reference Url via Create smartlink"
						+ "ALFDEPLOY-3996_Verify the creation of metrodigi smartlink object without image reference Url via Create smartlink"
						+ "ALFDEPLOY-3996_Verify the creation of video external smartlink object without image reference Url via Create smartlink<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to create smartlink successfully on entering backslash in Image Reference URL, Image Alt Text, Caption Text, Copyright or Credit Text fields for Video External link.<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to edit smartlink successfully on entering all special characters in Image Reference URL, Image Alt Text, Caption Text, Copyright or Credit Text fields for Video smartlink.");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoSearchPage alfrescoSearchPage = new AlfrescoSearchPage(scriptHelper);

		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");

		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		// String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String[] smartlinktype = dataTable.getData("MyFiles", "CreateFolder").split(",");
		String typeofsmart = "";

		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);

		/*
		 * Create site homePageObj.navigateToSitesTab(); String siteNameValue =
		 * dataTable.getData("Sites", "SiteName");
		 * sitesPage.siteFinder(siteNameValue);
		 * sitesPage.enterIntoDocumentLibrary();
		 */

		// Site finder
		homePageObj.navigateToSitesTab();
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		// Go to document library

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForPageToLoad(driver);

		// Navigate to Smart link Page

		for (String type1 : smartlinktype) {
			System.out.println(type1);
			Random rand = new Random();

			int n = rand.nextInt(50) + 1;

			if (type1.equalsIgnoreCase("thirdPartyInteractiveLink")) {
				typeofsmart = "3rd Party Interactive Link";
			}

			else if (type1.equalsIgnoreCase("metrodigiLink")) {
				typeofsmart = "Metrodigi Link";
			} else if (type1.equalsIgnoreCase("videoExternalLink")) {
				typeofsmart = "Video External Link";
			} else if (type1.equalsIgnoreCase("mdPopUp")) {
				typeofsmart = "MD Pop Up ";
			}

			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType(typeofsmart, type1);

			avsmart.entersmarttypedata(type1, title + n, extURLLink, "", "", "", "", "");
			// avsmart.entersmarttypedata(type1, title+n, extURLLink, "",
			// "Caption", "credit","","");
			avsmart.subcancelbtn(type1, "Submit");
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Check whether" + "type" + "Smart link is created without image reference Url",
					"Status: " + "" + "Smartlink is created without image reference Url", Status.PASS);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			sitesPage.enterIntoDocumentLibrary();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		}

		// backslash
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("Video External Link", "videoExternalLink");
		avsmart.entersmarttypedata("videoExternalLink", "VideoSmartLinkBackslash", extURLLink, "\\t", "\\n", "\\r", "",
				System.getProperty("user.dir") + filePath + fileName[0]);
		avsmart.subcancelbtn("videoExternalLink", "Submit");

		myFilesTestObj.verifyUploadedFile("VideoSmartLinkBackslash", "");

		collectionPg.clickOnMoreSetting("VideoSmartLinkBackslash");
		collectionPg.commonMethodForClickOnMoreSettingsOption("VideoSmartLinkBackslash", "Edit SmartLink");
		avsmart.editsmarttypedatainduvidual("videoExternalLink", "imgrefpath", "\\");
		avsmart.subcancelbtn("videoExternalLink", "Submit");

		// Edit smart link with spl characters filled
		collectionPg.clickOnMoreSetting("VideoSmartLinkBackslash");
		collectionPg.commonMethodForClickOnMoreSettingsOption("VideoSmartLinkBackslash", "Edit SmartLink");
		avsmart.editsmarttypedatainduvidual("videoExternalLink", "imgtext", "~`!@#$%^&*()_+-={}|\"[]\\:;'<>,.?/");
		avsmart.editsmarttypedatainduvidual("videoExternalLink", "caption", "~`!@#$%^&*()_+-={}|[]\\:;'\"<>,.?/");
		avsmart.editsmarttypedatainduvidual("videoExternalLink", "credit", "~`!@#$%^&*()_+-={}|[]\"\\:;'<>,.?/");
		avsmart.editsmarttypedatainduvidual("videoExternalLink", "imgrefpath", "~`!@#$%^&*()_+-={}|\"[]\\:;'<>,.?/");
		avsmart.subcancelbtn("videoExternalLink", "Submit");

		collectionPg.clickOnMoreSetting("VideoSmartLinkBackslash");
		collectionPg.commonMethodForClickOnMoreSettingsOption("VideoSmartLinkBackslash", "Edit SmartLink");
		avsmart.EditSmartlinkFilePathValidation("videoExternalLink", "VideoSmartLinkBackslash", siteassertValue,
				fileName[0]);
		avsmart.cancelButtonOnEditSmartlink("videoExternalLink", "VideoSmartLinkBackslash");

	}

	@Override
	public void tearDown() {

	}
}
