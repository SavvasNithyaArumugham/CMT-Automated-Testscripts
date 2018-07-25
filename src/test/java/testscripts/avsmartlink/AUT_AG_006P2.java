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
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_006P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription(
				"Verify whether coordinator is able to create smart link object(image external link) in document library. Part 2 - Cordinator create a Smart link file<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to create smartlink successfully on entering backslash  in Image Alt Text, Caption Text, Copyright or Credit Text fields for Image External link.-Part 2:Create smartlink with backslash<br>"
						+ "R2.0.2_ALFDEPLOY-5570_Verify Unwanted cross mark is not displayed on selecting unsupported file in Select pop up window of Create Smartlink for Repository, Shared File and My Files.<br>"
						+ "R2.0.2_ALFDEPLOY-5570_Verify error message Unsupported file format is displayed on selecting unsupported file in Select pop up window of Create Smartlink for Repository, Shared File and My Files<br>"
						+ "R2.0.2_ALFDEPLOY-5570_Verify error message Unsupported file format is displayed on selecting unsupported file name which consists of percentage symbol in Select pop up window of Create Smartlink for Repository, Shared File and My Files<br>"
						+ "R2.0.2_ALFDEPLOY-5570_Verify Unwanted cross mark is not displayed on selecting unsupported file in Select pop up window of Edit Smartlink for Repository, Shared File and My Files.<br>"
						+ "R2.0.2_ALFDEPLOY-5570_Verify error message Unsupported file format is displayed on selecting unsupported file in Select pop up window of Edit Smartlink for Repository, Shared File and My Files<br>"
						+ "R2.0.2_ALFDEPLOY-5570_Verify error message Unsupported file format is displayed on selecting unsupported file name which consists of percentage symbol in Select pop up window of Edit Smartlink for Repository, Shared File and My Files<br>+"
						+ "R2.0.2_ALFDEPLOY-6082_Verify if user is able to change the Optimized for use on Mobile radio button to No on selecting the Image Preview file within alfresco repository<br>"
						+ "R2.0.2_ALFDEPLOY-6082_Verify if user is able to change the Optimized for use on Mobile radio button to No on uploading the Image Preview file when the previous selection of radio button was Yes while creating a new 3rd party interactive smartlink.<br>"
						+ "R2.0.2_ALFDEPLOY-6082_Verify if user is able to change the Optimized for use on Mobile radio button to No on uploading the Image Preview file when the previous selection of radio button was Yes while editing a new 3rd party interactive smartlink.<br>"
						+ "R2.0.2_ALFDEPLOY-6083_Verify 3PI vendor drop down list is not getting duplicated on changing the option in optimized for use on mobile radio button while creating a new 3rd party interactive smartlink<br>"
						+ "R2.0.2_ALFDEPLOY-6083_Verify 3PI vendor drop down list is not getting duplicated on uploading image preview file while creating a new 3rd party interactive smartlink.<br>"
						+ "R2.0.2_ALFDEPLOY-6083_Verify 3PI vendor drop down list is not getting duplicated on selecting image preview file within alfresco repository while creating a new 3rd party interactive smartlink.<br>"
						+ "R2.0.2_ALFDEPLOY-6083_Verify 3PI vendor drop down list is not getting duplicated on changing the option in optimized for use on mobile radio button while editing a 3rd party interactive smartlink.<br>"
						+ "R2.0.2_ALFDEPLOY-6083_Verify 3PI vendor drop down list is not getting duplicated on uploading image preview file while editing 3rd party interactive smartlink.<br>"
						+ "R2.0.2_ALFDEPLOY-6083_Verify 3PI vendor drop down list is not getting duplicated on selecting image preview file within alfresco repository while editing 3rd party interactive smartlink.");

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
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		String unsupportedFile = dataTable.getData("MyFiles", "CreateFolder");
		String[] unsupportedFiles = dataTable.getData("MyFiles", "CreateFolder").split(",");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String file = dataTable.getData("MyFiles", "RelationshipName");
		functionalLibrary.loginAsValidUser(signOnPage);

		homePage.navigateToSharedFilesTab();

		for (int i = 0; i < unsupportedFiles.length; i++) {
			if (docLibPg.isFileIsAvailable(unsupportedFiles[i])) {
				myFiles.deleteUploadedFile(unsupportedFiles[i]);
				myFiles.uploadFile(filePath, unsupportedFiles[i]);
				myFilesTestObj.verifyUploadedFile(unsupportedFiles[i], "");
			} else {
				myFiles.uploadFile(filePath, unsupportedFiles[i]);
				myFilesTestObj.verifyUploadedFile(unsupportedFiles[i], "");
			}

		}
		homePage.navigateToMyFilesTab();
		for (int i = 0; i < unsupportedFiles.length; i++) {
			if (docLibPg.isFileIsAvailable(unsupportedFiles[i])) {
				myFiles.deleteUploadedFile(unsupportedFiles[i]);
				myFiles.uploadFile(filePath, unsupportedFiles[i]);
				myFilesTestObj.verifyUploadedFile(unsupportedFiles[i], "");
			} else {
				myFiles.uploadFile(filePath, unsupportedFiles[i]);
				myFilesTestObj.verifyUploadedFile(unsupportedFiles[i], "");
			}
		}

		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();

		myFiles.uploadFile(filePath, unsupportedFile);
		myFilesTestObj.verifyUploadedFile(unsupportedFiles[0], "");
		myFilesTestObj.verifyUploadedFile(unsupportedFiles[1], "");

		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("Image External Link", "imageExternalLink");
		avsmart.entersmarttypedata("imageExternalLink", title, extURLLink, "", "", "", "",
				System.getProperty("user.dir") + filePath + fileName);
		avsmart.subcancelbtn("imageExternalLink", "Submit");
		myFilesTestObj.verifyUploadedFile(title, "");

		// backslash
		sitesPage.enterIntoDocumentLibrary();

		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("Image External Link", "imageExternalLink");
		avsmart.entersmarttypedata("imageExternalLink", "ImageSmartLinkBackslash", extURLLink, "\"\"", "\\'", "\\\"",
				"", "");
		// Selecting unsupported file
		avsmart.selectimagefromSelectreportory("imageExternalLink", siteName, unsupportedFiles[0]);
		avsmart.errormsg(unsupportedFiles[0]);
		avsmart.clearfunction(unsupportedFiles[0]);
		avsmart.selectimagefromSelectSharedMyFiles("imageExternalLink", "My Files", unsupportedFiles[0]);
		avsmart.errormsg(unsupportedFiles[0]);
		avsmart.clearfunction(unsupportedFiles[0]);
		avsmart.selectimagefromSelectSharedMyFiles("imageExternalLink", "Shared Files", unsupportedFiles[0]);
		avsmart.errormsg(unsupportedFiles[0]);
		avsmart.clearfunction(unsupportedFiles[0]);
		// Selecting unsupported percentage file
		avsmart.selectimagefromSelectreportory("imageExternalLink", siteName, unsupportedFiles[1]);
		avsmart.errormsg(unsupportedFiles[1]);
		avsmart.clearfunction(unsupportedFiles[1]);
		avsmart.selectimagefromSelectSharedMyFiles("imageExternalLink", "My Files", unsupportedFiles[1]);
		avsmart.errormsg(unsupportedFiles[1]);
		avsmart.clearfunction(unsupportedFiles[1]);
		avsmart.selectimagefromSelectSharedMyFiles("imageExternalLink", "Shared Files", unsupportedFiles[1]);
		avsmart.errormsg(unsupportedFiles[1]);
		avsmart.subcancelbtn("imageExternalLink", "Submit");

		myFilesTestObj.verifyUploadedFile("ImageSmartLinkBackslash", "");

		// Edit smartlink
		collectionPg.clickOnMoreSetting("ImageSmartLinkBackslash");
		collectionPg.commonMethodForClickOnMoreSettingsOption("ImageSmartLinkBackslash", moreSettingsOption);
		// Selecting unsupported file
		avsmart.selectimagefromSelectreportory("imageExternalLink", siteName, unsupportedFiles[0]);
		avsmart.errormsg(unsupportedFiles[0]);
		avsmart.clearfunction(unsupportedFiles[0]);
		avsmart.selectimagefromSelectSharedMyFiles("imageExternalLink", "My Files", unsupportedFiles[0]);
		avsmart.errormsg(unsupportedFiles[0]);
		avsmart.clearfunction(unsupportedFiles[0]);
		avsmart.selectimagefromSelectSharedMyFiles("imageExternalLink", "Shared Files", unsupportedFiles[0]);
		avsmart.errormsg(unsupportedFiles[0]);
		avsmart.clearfunction(unsupportedFiles[0]);
		// Selecting unsupported percentage file
		avsmart.selectimagefromSelectreportory("imageExternalLink", siteName, unsupportedFiles[1]);
		avsmart.errormsg(unsupportedFiles[1]);
		avsmart.clearfunction(unsupportedFiles[1]);
		avsmart.selectimagefromSelectSharedMyFiles("imageExternalLink", "My Files", unsupportedFiles[1]);
		avsmart.errormsg(unsupportedFiles[1]);
		avsmart.clearfunction(unsupportedFiles[1]);
		avsmart.selectimagefromSelectSharedMyFiles("imageExternalLink", "Shared Files", unsupportedFiles[1]);
		avsmart.errormsg(unsupportedFiles[1]);
		avsmart.subcancelbtn("imageExternalLink", "Submit");

		collectionPg.clickOnMoreSetting("ImageSmartLinkBackslash");
		collectionPg.commonMethodForClickOnMoreSettingsOption("ImageSmartLinkBackslash", moreSettingsOption);
		avsmart.cancelButtonOnEditSmartlink("imageExternalLink", "ImageSmartLinkBackslash");

		// Selecting file from repository for 3rd party
		sitesPage.enterIntoDocumentLibrary();
		myFiles.uploadFile(filePath, file);
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("3rd Party Interactive Link", "thirdPartyInteractiveLink");
		if (avsmart.optimizedSelection()) {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is defaulted to Yes", Status.PASS);
		} else {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is not defaulted to Yes", Status.FAIL);
		}
		avsmart.selectimagefromSelectreportory("thirdPartyInteractiveLink", siteName, file);
		avsmart.toggleOptimizedSelection("No");
		avsmart.cancelButtonOnEditSmartlink("thirdPartyInteractiveLink", "3rdpartyoptimized");

		// uploading file for 3rd party
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("3rd Party Interactive Link", "thirdPartyInteractiveLink");
		if (avsmart.optimizedSelection()) {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is defaulted to Yes", Status.PASS);
		} else {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is not defaulted to Yes", Status.FAIL);
		}
		avsmart.editsmarttypedatainduvidual("thirdPartyInteractiveLink", "imgpreview",
				System.getProperty("user.dir") + filePath + file);
		avsmart.toggleOptimizedSelection("No");
		avsmart.cancelButtonOnEditSmartlink("thirdPartyInteractiveLink", "3rdpartyoptimized");

		// edit smartlink-3rd party-change optimized radio button
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("3rd Party Interactive Link", "thirdPartyInteractiveLink");
		if (avsmart.optimizedSelection()) {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is defaulted to Yes", Status.PASS);
		} else {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is not defaulted to Yes", Status.FAIL);
		}
		avsmart.entersmarttypedata("thirdPartyInteractiveLink", "3rdpartyoptimized", extURLLink, "\"\"", "\\'", "\\\"",
				"", "");
		avsmart.subcancelbtn("thirdPartyInteractiveLink", "Submit");

		collectionPg.clickOnMoreSetting("3rdpartyoptimized");
		collectionPg.commonMethodForClickOnMoreSettingsOption("3rdpartyoptimized", moreSettingsOption);
		if (avsmart.optimizedSelection()) {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is defaulted to Yes", Status.PASS);
		} else {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is not defaulted to Yes", Status.FAIL);

		}
		avsmart.editsmarttypedatainduvidual("thirdPartyInteractiveLink", "imgpreview",
				System.getProperty("user.dir") + filePath + file);
		avsmart.toggleOptimizedSelection("No");
		// avsmart.subcancelbtn("thirdPartyInteractiveLink", "Submit");
		avsmart.cancelButtonOnEditSmartlink("thirdPartyInteractiveLink", "3rdpartyoptimized");

		// 3PI vendor dropdown list does not get duplicated upon changing
		// optimized to No
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("3rd Party Interactive Link", "thirdPartyInteractiveLink");
		if (avsmart.optimizedSelection()) {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is defaulted to Yes", Status.PASS);
		} else {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is not defaulted to Yes", Status.FAIL);

		}
		avsmart.toggleOptimizedSelection("No");
		avsmart.threePIVendorDropdown();

		// 3PI vendor dropdown list does not get duplicated upon uploading image
		// preview
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("3rd Party Interactive Link", "thirdPartyInteractiveLink");
		if (avsmart.optimizedSelection()) {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is defaulted to Yes", Status.PASS);
		} else {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is not defaulted to Yes", Status.FAIL);

		}
		avsmart.editsmarttypedatainduvidual("thirdPartyInteractiveLink", "imgpreview",
				System.getProperty("user.dir") + filePath + file);
		avsmart.threePIVendorDropdown();

		// 3PI vendor dropdown list does not get duplicated upon uploading image
		// preview
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("3rd Party Interactive Link", "thirdPartyInteractiveLink");
		if (avsmart.optimizedSelection()) {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is defaulted to Yes", Status.PASS);
		} else {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is not defaulted to Yes", Status.FAIL);

		}
		avsmart.selectimagefromSelectreportory("thirdPartyInteractiveLink", siteName, file);
		avsmart.threePIVendorDropdown();

		// 3PI vendor dropdown list does not get duplicated when editing
		// optimized dropdown
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails("3rdpartyoptimized");
		collectionPg.clickOnMoreSetting("3rdpartyoptimized");
		collectionPg.commonMethodForClickOnMoreSettingsOption("3rdpartyoptimized", moreSettingsOption);
		if (avsmart.optimizedSelection()) {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is defaulted to Yes", Status.PASS);
		} else {
			report.updateTestLog("Verify Optimized for use on Mobile radio button in UI is default to Yes",
					"Optimized for use on Mobile radio button in UI is not defaulted to Yes", Status.FAIL);

		}
		avsmart.toggleOptimizedSelection("No");
		avsmart.threePIVendorDropdown();
		avsmart.cancelButtonOnEditSmartlink("thirdPartyInteractiveLink", "3rdpartyoptimized");

		// 3PI vendor dropdown list does not get duplicated when editing
		// -uploading image preview
		collectionPg.clickOnMoreSetting("3rdpartyoptimized");
		collectionPg.commonMethodForClickOnMoreSettingsOption("3rdpartyoptimized", moreSettingsOption);
		avsmart.editsmarttypedatainduvidual("thirdPartyInteractiveLink", "imgpreview",
				System.getProperty("user.dir") + filePath + fileName);
		avsmart.threePIVendorDropdown();
		avsmart.cancelButtonOnEditSmartlink("thirdPartyInteractiveLink", "3rdpartyoptimized");

		// 3PI vendor dropdown list does not get duplicated when editing
		// -selecting image preview
		collectionPg.clickOnMoreSetting("3rdpartyoptimized");
		collectionPg.commonMethodForClickOnMoreSettingsOption("3rdpartyoptimized", moreSettingsOption);
		avsmart.selectimagefromSelectreportory("thirdPartyInteractiveLink", siteName, file);
		avsmart.threePIVendorDropdown();
		avsmart.cancelButtonOnEditSmartlink("thirdPartyInteractiveLink", "3rdpartyoptimized");

	}

	@Override
	public void tearDown() {

	}
}
