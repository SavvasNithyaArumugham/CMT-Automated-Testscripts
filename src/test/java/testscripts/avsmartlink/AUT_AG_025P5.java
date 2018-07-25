package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_025P5 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_025() {
		testParameters.setCurrentTestDescription(

				" <br>ALFDEPLOY-4120_To verify Edit smartlink  option is getting display for smartlink- 3RD PARTY INTERACTIVE files."
						+ "<br>ALFDEPLOY-4120_To verify Edit smartlink  option is not getting display for files other than "
						+ "smartlink file."
						+ "<br>ALFDEPLOY-4120_To verify user is able to submit successfully after making changes in smartlink "
						+ "properties and the version of the smartlink changes after every edit in Edit of smartlink-3RD PARTY INTERACTIVE."
						+ "<br>ALFDEPLOY-4120_To verify on clicking Cancel button , changes made by the user will revert back to "
						+ "original state in Edit smartlink-3RD PARTY INTERACTIVE file."
						+ "ALFDEPLOY-4116_To verify user is getting edit Smartlink option via More for 3rd party interactive type smart link object."
						+ "ALFDEPLOY-4117_To verify for 3rd party interactive smart link object on clicking Edit Smart link via More option , "
						+ "user is getting only 3rd party interactive form pop up displaying exsisting field values.<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to create smartlink successfully on entering all special characters in Image Reference URL, Image Alt Text, Caption Text, Copyright or Credit Text fields for 3rd party interactive smartlink<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to edit smartlink successfully on entering backslash in Image Reference URL, Image Alt Text, Caption Text, Copyright or Credit Text fields for 3rd party interactive smartlink");
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

		try {

			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

			String filePath = dataTable.getData("MyFiles", "FilePath");
			String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
			String siteName = dataTable.getData("Sites", "SiteName");
			String type = dataTable.getData("Home", "DashletName");
			String title = dataTable.getData("Document_Details", "FilePath");
			String extURLLink = dataTable.getData("Document_Details", "FileName");
			String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
			String[] smartlinktype = dataTable.getData("MyFiles", "CreateFolder").split(",");
			String typeofsmart = "";

			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitForLong(driver);
			// Navigate into sites
			sitesPage.navigateToSitesTab();
			sitesPage.siteFinder(targetSiteName);
			sitesPage.enterIntoDocumentLibrary();
			docLibPg.deleteAllFilesAndFolders();

			// Upload the file in the targeted site:
			collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
			UIHelper.waitForPageToLoad(driver);

			// navigated back to source site
			sitesPage.navigateToSitesTab();
			sitesPage.siteFinder(siteName);
			sitesPage.enterIntoDocumentLibrary();
			docLibPg.deleteAllFilesAndFolders();

			// Upload:

			for (String link : smartlinktype) {

				if (link.equalsIgnoreCase("imageExternalLink")) {
					typeofsmart = "Image External Link";
				} else if (link.equalsIgnoreCase("videoExternalLink")) {
					typeofsmart = "Video External Link";
				} else if (link.equalsIgnoreCase("audioExternalLink")) {
					typeofsmart = "Audio External Link";
				} else if (link.equalsIgnoreCase("externalWebsiteLink")) {
					typeofsmart = "External Website Link";
				} else if (link.equalsIgnoreCase("thirdPartyInteractiveLink")) {
					typeofsmart = "3rd Party Interactive Link";
				} else if (link.equalsIgnoreCase("metrodigiLink")) {
					typeofsmart = "Metrodigi Link";
				} else if (link.equalsIgnoreCase("tableLink")) {
					typeofsmart = "Table Link";
				}

				myFiles.createcontenttype(type);
				avsmart.clickSmartLinkType(typeofsmart, link);
				avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "cred" + "it", "",
						System.getProperty("user.dir") + filePath + fileName[0]);
				avsmart.EnteringImageref(link, title);
				avsmart.submitbutton(link, title);
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.0", title);

				// External URL link updation
				report.updateTestLog("Edit Smartlink Updation:", "<b>External URL Link Update", Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.editsmarttypedatainduvidual(link, "extURLLink", properties.getProperty("ApplicationUrl"));
				avsmart.submitbutton(link, title);
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.1", title);

				// External Image Preview updation
				report.updateTestLog("Edit Smartlink Updation:", "<b>Image Preview Update", Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.ClearbuttonforAllSmartlink(link, title);
				avsmart.editsmarttypedatainduvidual(link, "imgpreview",
						System.getProperty("user.dir") + filePath + fileName[1]);
				avsmart.submitbutton(link, title);
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.2", title);

				// External Image txt updation
				report.updateTestLog("Edit Smartlink Updation:", "<b>Image Text Update", Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.editsmarttypedatainduvidual(link, "imgtext", "Image Text Updated");
				avsmart.submitbutton(link, title);
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.3", title);

				// External caption updation
				report.updateTestLog("Edit Smartlink Updation:", "<b>Caption Update", Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.editsmarttypedatainduvidual(link, "caption", "CaptionUpdated");
				avsmart.submitbutton(link, title);
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.4", title);

				// External credit updation
				report.updateTestLog("Edit Smartlink Updation:", "<b>Credit Update", Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.editsmarttypedatainduvidual(link, "credit", "CreditUpdated");
				avsmart.submitbutton(link, title);
				// UIHelper.waitForLong(driver);
				// .waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.5", title);

				// cancel edit edit smartlink for no version update
				report.updateTestLog("Edit Smartlink Updation:", "<b>Clicking on Cancel, No Version Update",
						Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.cancelButtonOnEditSmartlink(link, title);
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.5", title);

				// Title updation
				report.updateTestLog("Edit Smartlink Updation:", "<b>Title Update", Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.editsmarttypedatainduvidual(link, "title", "Changed");
				avsmart.submitbutton(link, "Changed");
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.6", "Changed");

				// Check whether edit Smartlink is not appearing for other than
				// smartlink
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnMoreSetting("Changed");
				boolean flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption("Changed", "Edit SmartLink");
				if (flag) {
					report.updateTestLog("Edit Smartlink:", "Edit Smartlink is presented for " + "<b>Changed",
							Status.PASS);
				} else {
					report.updateTestLog("Edit Smartlink:", "Edit Smartlink is not presented for " + "<b>Changed",
							Status.FAIL);

				}
				collectionPg.clickOnMoreSetting(fileName[0]);
				boolean flag1 = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName[0], "Edit SmartLink");
				if (flag1) {
					report.updateTestLog("Edit Smartlink:", "Edit Smartlink is presented for " + title, Status.FAIL);
				} else {
					report.updateTestLog("Edit Smartlink:", "Edit Smartlink is not presented for " + title,
							Status.PASS);

				}
			}

			// backslash
			sitesPage.enterIntoDocumentLibrary();
			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType("3rd Party Interactive Link", "thirdPartyInteractiveLink");
			avsmart.entersmarttypedata("thirdPartyInteractiveLink", "3rdPartySLBackslash", extURLLink,
					"~`!@#$%^&*()_+-={}|[]\\:;'\"<>,.?/", "~`!@#$%^&*()_+-={}|[]\"\\:;'<>,.?/",
					"~`!@#$%^&*()_+-={}|\"[]\\:;'<>,.?/", "", System.getProperty("user.dir") + filePath + fileName[0]);
			avsmart.subcancelbtn("thirdPartyInteractiveLink", "Submit");

			myFilesTestObj.verifyUploadedFile("3rdPartySLBackslash", "");

			collectionPg.clickOnMoreSetting("3rdPartySLBackslash");
			collectionPg.commonMethodForClickOnMoreSettingsOption("3rdPartySLBackslash", "Edit SmartLink");
			avsmart.editsmarttypedatainduvidual("thirdPartyInteractiveLink", "imgrefpath", "\\\'");
			avsmart.editsmarttypedatainduvidual("thirdPartyInteractiveLink", "imgtext", "\\\"");
			avsmart.editsmarttypedatainduvidual("thirdPartyInteractiveLink", "caption", "\\n");
			avsmart.editsmarttypedatainduvidual("thirdPartyInteractiveLink", "credit", "\\r");
			avsmart.subcancelbtn("thirdPartyInteractiveLink", "Submit");

			collectionPg.clickOnMoreSetting("3rdPartySLBackslash");
			collectionPg.commonMethodForClickOnMoreSettingsOption("3rdPartySLBackslash", "Edit SmartLink");
			avsmart.EditSmartlinkFilePathValidation("thirdPartyInteractiveLink", "3rdPartySLBackslash", siteName,
					fileName[0]);
			avsmart.cancelButtonOnEditSmartlink("thirdPartyInteractiveLink", "3rdPartySLBackslash");

		} catch (Exception e) {
			report.updateTestLog("AUT_AG_025P5 status:", "<br>AUT_AG_025P5 Testcase is Failed", Status.FAIL);

		}

	}

	@Override
	public void tearDown() {

	}
}
