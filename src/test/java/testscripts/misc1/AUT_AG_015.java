package testscripts.misc1;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_015 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_015() {
		testParameters.setCurrentTestDescription(
				"Verify more than 250 site names are present Select Window in document library<br>"
						+ "ALFDEPLOY-1997_4889_Verify only 250 user sites are listed in relationship document picker upon adding relationship via document library when user is member of greater than or equal to 250 sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify only 250 user sites are listed in relationship document picker upon adding relationship via My files when user is member of greater than or equal to 250 sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify only 250 user sites are listed in relationship document picker upon adding relationship via Shared files when user is member of greater than or equal to 250 sites");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String moreSettingsOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		String sitesCount = dataTable.getData("MyFiles", "ExpectedSitesCount");

		String folderName = dataTable.getData("MyFiles", "Version");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		ArrayList<String> mysitesList = homePageObj.getSitesFromMySitesDashletInHomePage();

		// String addRelationSite = dataTable.getData("Sites", "SiteToSelect");
		for (int x = 0; x < 3; x++) {
			switch (x) {
			case 0: {

				homePageObj.navigateToSitesTab();
				sitesPage.openSiteFromRecentSites(siteName);
				sitesPage.enterIntoDocumentLibrary();
				break;
			}
			case 1: {
				homePageObj.navigateToMyFilesTab();
				break;
			}
			case 2: {
				homePageObj.navigateToSharedFilesTab();
				break;
			}
			default: {
				System.out.println("Exit");
				break;
			}

			}
			if (sitesPage.documentAvailable(folderName)) {
				sitesPage.openFolder(folderName);
				docLibPg.deleteAllFilesAndFolders();
			} else {
				myFiles.createFolder(folderDetails);
				sitesPage.openFolder(folderName);
			}
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			myFilesTestObj.verifyUploadedFile(fileName, "");
			sitesPage.clickOnMoreSetting(fileName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOpt);
			sitesPage.selectRelationshipNameAndclickOnAddRelationshipBtnFromMySharedFiles(relationshipName, fileName);

			int expectedSitesCount = Integer.parseInt(sitesCount.replace("'", ""));
			sitesPageTestObj.verifySiteNamesInSelectWindowForAddRelationship(expectedSitesCount);
			/*
			 * myFiles.backToFolderOrDocumentPage(); UIHelper.waitFor(driver);
			 * sitesPage.enterIntoDocumentLibrary();
			 * 
			 * myFiles.openAFile(fileName);
			 * docDetailsPage.commonMethodForPerformDocAction(moreSettingsOpt);
			 */
			ArrayList<String> relationshipPickerSitesList = sitesPage.getSitesFromRelationshipPicker();

			int matchingCount = 0, nonMatchingCount = 0;
			for (int i = 0; i < relationshipPickerSitesList.size(); i++) {
				if (relationshipPickerSitesList.contains(mysitesList.get(i))) {
					matchingCount++;
				} else {
					nonMatchingCount++;
				}

			}
			report.updateTestLog("Verify sitesList in relationship picker matches with my sites list",
					"SitesList in relationship picker matches with my sites list are " + matchingCount
							+ " and not matches with my sites list are " + nonMatchingCount,
					Status.PASS);

			sitesPage.addRelationshipBtwAssetAndSite(relationshipName, fileName, siteName);
			myFiles.openAFile(fileName);
			// docDetailsPage.clickAddRelationshipWidget();

			if (docDetailsPage.isRelationshipAddedForSite()) {
				if (siteName.equalsIgnoreCase(docDetailsPage.getRelationshipTableVal()) && (relationshipName
						.equalsIgnoreCase(docDetailsPage.getRelationshipValFromList(relationshipName)))) {
					report.updateTestLog(
							"Relationship Added between File and Site from more option is listed in relationship section",
							"Relationship Added is listed in relationship section successfully"
									+ "</br><b>File Name:</b> " + fileName + "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName)
									+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
							Status.PASS);
				} else {
					report.updateTestLog(
							"Relationship Added between File and Site from more option is listed in relationship section",
							"Relationship added is not listed" + "</br><b>File Name:</b> " + fileName
									+ "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName)
									+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
							Status.FAIL);
				}
			} else {
				report.updateTestLog(
						"Relationship Added between File and Site from more option is listed in relationship section",
						"Relationship is not Added" + "</br><b>File Name:</b> " + fileName
								+ "</br><b>Relationship Name:</b> "
								+ docDetailsPage.getRelationshipValFromList(relationshipName)
								+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
						Status.FAIL);
			}
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}