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
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_011 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_011() {
		testParameters.setCurrentTestDescription(
				"Verfiy users is able to 'add relationship' between files and site a on Preview Page<br>"
						+ "ALFDEPLOY-1997_4889_Verify only 250 user sites are listed in relationship document picker upon adding relationship via file preview page when user is member of greater than or equal to 250 sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify only 250 user sites are listed in relationship document picker upon adding relationship via file preview page-relationship section when user is member of greater than or equal to 250 sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify the available user sites are listed in relationship document picker upon adding relationship via file preview page when user is member of less than 250 sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify the available user sites are listed in relationship document picker upon adding relationship via file preview page-relationship section when user is member of less than 250 sites");
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

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String addRelationSite1 = dataTable.getData("Sites", "SiteToSelect");
		String addRelationSite2 = dataTable.getData("Sites", "TargetSiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String sitesCount = dataTable.getData("MyFiles", "ExpectedSitesCount");
		String[] relationshipName = dataTable.getData("MyFiles", "RelationshipName").split(",");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		ArrayList<String> mysitesList = homePage.getSitesFromMySitesDashletInHomePage();

		sitesPage.openSiteFromRecentSites(addRelationSite1);
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(addRelationSite2);

		// sitesPage.enterIntoDocumentLibrary();

		// docLibPage.deleteAllFilesAndFolders();
		for (int x = 0; x < 3; x++) {
			switch (x) {
			case 0: {
				homePage.navigateToSharedFilesTab();
				break;
			}
			case 1: {
				homePage.navigateToMyFilesTab();
				break;
			}
			case 2: {
				homePage.navigateToSitesTab();
				sitesPage.openSiteFromRecentSites(siteNameValue);
				sitesPage.enterIntoDocumentLibrary();
				break;
			}
			default: {
				System.out.println("Exit");
				break;
			}

			}
			if (sitesPage.documentAvailable(folderName)) {
				sitesPage.openFolder(folderName);
				docLibPage.deleteAllFilesAndFolders();
			} else {
				myFiles.createFolder(folderDetails);
				sitesPage.openFolder(folderName);
			}
			// homePage.navigateToSitesTab();
			// sitesPage.openSiteFromRecentSites(siteNameValue);
			// sitesPage.enterIntoDocumentLibrary();
			// docLibPage.deleteAllFilesAndFolders();

			myFiles.uploadFile(filePath, fileName);
			myFiles.openAFile(fileName);
			docDetailsPage.commonMethodForPerformDocAction(docActionVal);

			sitesPage.selectRelationshipNameAndclickOnAddRelationshipBtnFromMySharedFiles(relationshipName[0], fileName);
			int expectedSitesCount = Integer.parseInt(sitesCount.replace("'", ""));
			sitesPageTestObj.verifySiteNamesInSelectWindowForAddRelationship(expectedSitesCount);
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

			sitesPage.addRelationshipBtwAssetAndSite(relationshipName[0], fileName, addRelationSite1);

			if (docDetailsPage.isRelationshipAddedForSite()) {
				if (addRelationSite1.equalsIgnoreCase(docDetailsPage.getRelationshipTableVal()) && (relationshipName[0]
						.equalsIgnoreCase(docDetailsPage.getRelationshipValFromList(relationshipName[0])))) {
					report.updateTestLog(
							"Verify Relationship Added between file and site from file Preview Page is listed in relationship section",
							"Relationship Added is listed in relationship section successfully"
									+ "</br><b>File Name:</b> " + fileName + "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName[0])
									+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
							Status.PASS);
				} else {
					report.updateTestLog(
							"Verify Relationship Added between file and site from file Preview Page is listed in relationship section",
							"Relationship added is not listed" + "</br><b>File Name:</b> " + fileName
									+ "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName[0])
									+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
							Status.FAIL);
				}
			} else {
				report.updateTestLog(
						"Verify Relationship Added between file and site from file Preview Page is listed in relationship section",
						"Relationship is not Added" + "</br><b>File Name:</b> " + fileName
								+ "</br><b>Relationship Name:</b> "
								+ docDetailsPage.getRelationshipValFromList(relationshipName[0])
								+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
						Status.FAIL);
			}

			docDetailsPage.clickAddRelationshipWidget();

			sitesPage.selectRelationshipNameAndclickOnAddRelationshipBtnFromMySharedFiles(relationshipName[1], fileName);
			int expectedSitesCount1 = Integer.parseInt(sitesCount.replace("'", ""));
			sitesPageTestObj.verifySiteNamesInSelectWindowForAddRelationship(expectedSitesCount1);

			ArrayList<String> relationshipPickerSitesList1 = sitesPage.getSitesFromRelationshipPicker();

			int matchingCount1 = 0, nonMatchingCount1 = 0;
			for (int i = 0; i < relationshipPickerSitesList1.size(); i++) {
				if (relationshipPickerSitesList1.contains(mysitesList.get(i))) {
					matchingCount1++;
				} else {
					nonMatchingCount1++;
				}

			}
			report.updateTestLog("Verify sitesList in relationship picker matches with my sites list",
					"SitesList in relationship picker matches with my sites list are " + matchingCount1
							+ " and not matches with my sites list are " + nonMatchingCount1,
					Status.PASS);

			sitesPage.addRelationshipBtwAssetAndSite(relationshipName[1], fileName, addRelationSite2);

			if (docDetailsPage.isRelationshipAddedForSite()) {
				if (addRelationSite2.equalsIgnoreCase(docDetailsPage.getRelationshipTableValFromList(addRelationSite2))
						&& (relationshipName[1]
								.equalsIgnoreCase(docDetailsPage.getRelationshipValFromList(relationshipName[1])))) {
					report.updateTestLog(
							"Verify Relationship Added between file and site from file Preview Page is listed in relationship section",
							"Relationship Added is listed in relationship section successfully"
									+ "</br><b>File Name:</b> " + fileName + "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName[1])
									+ "</br><b>Site Name:</b> "
									+ docDetailsPage.getRelationshipTableValFromList(addRelationSite2),
							Status.PASS);
				} else {
					report.updateTestLog(
							"Verify Relationship Added between file and site from file Preview Page is listed in relationship section",
							"Relationship added is not listed" + "</br><b>File Name:</b> " + fileName
									+ "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName[1])
									+ "</br><b>Site Name:</b> "
									+ docDetailsPage.getRelationshipTableValFromList(addRelationSite2),
							Status.FAIL);
				}
			} else {
				report.updateTestLog(
						"Verify Relationship Added between file and site from file Preview Page is listed in relationship section",
						"Relationship is not Added" + "</br><b>File Name:</b> " + fileName
								+ "</br><b>Relationship Name:</b> "
								+ docDetailsPage.getRelationshipValFromList(relationshipName[1])
								+ "</br><b>Site Name:</b> "
								+ docDetailsPage.getRelationshipTableValFromList(addRelationSite2),
						Status.FAIL);
			}
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}