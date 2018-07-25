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

public class AUT_AG_025P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_025() {
		testParameters.setCurrentTestDescription(

				" <br>ALFDEPLOY-4124_To verify Edit smartlink  option is getting display for smartlink- pdf files."
						+ "<br>ALFDEPLOY-4124_To verify Edit smartlink  option is not getting display for files other than "
						+ "smartlink file."
						+ "<br>ALFDEPLOY-4124_To verify user is able to submit successfully after making changes in smartlink "
						+ "properties and the version of the smartlink changes after every edit in Edit of smartlink-pdf."
						+ "<br>ALFDEPLOY-4124_To verify on clicking Cancel button , changes made by the user will revert back to "
						+ "original state in Edit smartlink-pdf file."
						+ "ALFDEPLOY-4224_Verify on click of Edit smartlink for PDF object, the Edit smartlink form should be expanded."
						+ "ALFDEPLOY-4116_To verify user is getting edit Smartlink option via More for Pdf type smart link object."
						+ "ALFDEPLOY-4117_To verify for Pdf smart link object on clicking Edit Smart link via More option , "
						+ "user is getting only Pdf form pop up displaying exsisting field values.<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to edit smartlink successfully on entering backslash in Caption Text, Copyright or Credit Text fields for PDF links.<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to edit smartlink successfully on entering backslash  in Image Reference URL, Image Alt Text, Caption Text, Copyright or Credit Text fields for Table smartlink"

		);
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
				} else if (link.equalsIgnoreCase("pdfLink")) {
					typeofsmart = "PDF Link";
				}

				myFiles.createcontenttype(type);
				avsmart.clickSmartLinkType(typeofsmart, link);
				avsmart.entersmarttypedata(link, title, extURLLink, "", "Caption", "cred" + "", "", "");
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

				// External caption updation
				report.updateTestLog("Edit Smartlink Updation:", "<b>Caption Update", Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.editsmarttypedatainduvidual(link, "caption", "CaptionUpdated");
				avsmart.submitbutton(link, title);
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.2", title);

				// External credit updation
				report.updateTestLog("Edit Smartlink Updation:", "<b>Credit Update", Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.editsmarttypedatainduvidual(link, "credit", "CreditUpdated");
				avsmart.submitbutton(link, title);
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.3", title);

				// cancel edit edit smartlink for no version update
				report.updateTestLog("Edit Smartlink Updation:", "<b>Clicking on Cancel, No Version Update",
						Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.cancelButtonOnEditSmartlink(link, title);
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.3", title);

				// Title updation
				report.updateTestLog("Edit Smartlink Updation:", "<b>Title Update", Status.DONE);
				collectionPg.clickOnMoreSetting(title);
				collectionPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
				avsmart.editsmarttypedatainduvidual(link, "title", "Changed");
				avsmart.submitbutton(link, "Changed");
				// UIHelper.waitForLong(driver);
				// UIHelper.waitForPageToLoad(driver);
				avsmart.VersionCheckdocument("1.4", "Changed");

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

			// Edit smartlink with backslash for table
			collectionPg.clickOnMoreSetting("Changed");
			collectionPg.commonMethodForClickOnMoreSettingsOption("Changed", "Edit SmartLink");
			avsmart.editsmarttypedatainduvidual("tableLink", "title", "TableBackslashUpdated");
			avsmart.editsmarttypedatainduvidual("tableLink", "extURLLink", "https://www.pearson.com");
			avsmart.editsmarttypedatainduvidual("tableLink", "imgrefpath", "\\n");
			avsmart.editsmarttypedatainduvidual("tableLink", "imgtext", "\\r");
			avsmart.editsmarttypedatainduvidual("tableLink", "caption", "\\t");
			avsmart.editsmarttypedatainduvidual("tableLink", "credit", "\\b");

			avsmart.submitbutton("tableLink", "TableBackslashUpdated");
			avsmart.VersionCheckdocument("1.5", "TableBackslashUpdated");

			collectionPg.clickOnMoreSetting("TableBackslashUpdated");
			collectionPg.commonMethodForClickOnMoreSettingsOption("TableBackslashUpdated", "Edit SmartLink");
			avsmart.cancelButtonOnEditSmartlink("tableLink", "TableBackslashUpdated");

			// Create & Edit PDF smartlink with backslash
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
			sitesPage.enterIntoDocumentLibrary();
			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType("PDF Link", "pdfLink");
			avsmart.entersmarttypedata("pdfLink", "PDFBackslash", extURLLink, "", "~`!@#$%^&*()_+-={}|[]\"\\:;'<>,.?/",
					"~`!@#$%^&*()_+-={}|\"[]\\:;'<>,.?/", "", "");
			avsmart.subcancelbtn("pdfLink", "Submit");

			myFilesTestObj.verifyUploadedFile("PDFBackslash", "");

			collectionPg.clickOnMoreSetting("PDFBackslash");
			collectionPg.commonMethodForClickOnMoreSettingsOption("PDFBackslash", "Edit SmartLink");
			avsmart.editsmarttypedatainduvidual("pdfLink", "title", "PDFBackslashUpdated");
			avsmart.editsmarttypedatainduvidual("pdfLink", "extURLLink", "https://www.google.co.in");
			avsmart.editsmarttypedatainduvidual("pdfLink", "caption", "\"\"");
			avsmart.editsmarttypedatainduvidual("pdfLink", "credit", "\\");

			avsmart.submitbutton("pdfLink", "PDFBackslashUpdated");
			avsmart.VersionCheckdocument("1.1", "PDFBackslashUpdated");

			collectionPg.clickOnMoreSetting("PDFBackslashUpdated");
			collectionPg.commonMethodForClickOnMoreSettingsOption("PDFBackslashUpdated", "Edit SmartLink");
			avsmart.cancelButtonOnEditSmartlink("pdfLink", "PDFBackslashUpdated");

		} catch (Exception e) {
			report.updateTestLog("AUT_AG_025 status:", "<br>AUT_AG_025 Testcase is Failed", Status.FAIL);

		}

	}

	@Override
	public void tearDown() {

	}
}
