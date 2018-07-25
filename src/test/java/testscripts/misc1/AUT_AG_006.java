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
public class AUT_AG_006 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_006() {
		testParameters.setCurrentTestDescription(
				"Verfiy user is able to 'Add Relationship' between folder and a site from Folder Actions Menu <br>"
						+ "ALFDEPLOY-1997_4889_Verify only 250 user sites are listed in relationship document picker upon adding relationship via folder details page when user is member of greater than or equal to 250 sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify the available user sites are listed in relationship document picker upon adding relationship via folder details page when user is member of less than 250 sites");
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

		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);

		String addRelationSite = dataTable.getData("Sites", "SiteToSelect");

		ArrayList<String> mysitesList = homePage.getSitesFromMySitesDashletInHomePage();

		sitesPage.siteFinder(addRelationSite);
		// sitesPage.enterIntoDocumentLibrary();
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTestObj = new AlfrescoSitesPageTest(scriptHelper);
		// docLibPage.deleteAllFilesAndFolders();

		String folderName = dataTable.getData("MyFiles", "Version");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		String sitesCount = dataTable.getData("MyFiles", "ExpectedSitesCount");
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		for (int x = 0; x < 3; x++) {
			switch (x) {
			case 0: {
				homePage.navigateToSitesTab();
				sitesPage.siteFinder(siteNameValue);
				sitesPage.enterIntoDocumentLibrary();
				break;
			}
			case 1: {
				homePage.navigateToMyFilesTab();
				break;
			}
			case 2: {
				homePage.navigateToSharedFilesTab();
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

			// sitesPage.siteFinder(siteNameValue);
			// sitesPage.enterIntoDocumentLibrary();
			// docLibPage.deleteAllFilesAndFolders();

			myFiles.createFolder(folderDetails);
			sitesPage.clickOnViewDetails(folderName);
			docDetailsPage.commonMethodForPerformDocAction(docActionVal);

			sitesPage.selectRelationshipNameAndclickOnAddRelationshipBtnFromMySharedFiles(relationshipName, folderName);
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

			sitesPage.addRelationshipBtwAssetAndSite(relationshipName, folderName, addRelationSite);

			if (docDetailsPage.isRelationshipAddedForSite()) {
				if (addRelationSite.equalsIgnoreCase(docDetailsPage.getRelationshipTableVal()) && (relationshipName
						.equalsIgnoreCase(docDetailsPage.getRelationshipValFromList(relationshipName)))) {
					report.updateTestLog(
							"Verify Relationship Added between folder and site from Folder Actions Menu/folder details page is listed in relationship section",
							"Relationship Added is listed in relationship section successfully"
									+ "</br><b>Folder Name:</b> " + folderName + "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName)
									+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
							Status.PASS);
				} else {
					report.updateTestLog(
							"Verify Relationship Added between folder and site from Folder Actions Menu/folder details page is listed in relationship section",
							"Relationship added is not listed" + "</br><b>Folder Name:</b> " + folderName
									+ "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName)
									+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
							Status.FAIL);
				}
			} else {
				report.updateTestLog(
						"Verify Relationship Added between folder and site from Folder Actions Menu/folder details page is listed in relationship section",
						"Relationship is not Added" + "</br><b>Folder Name:</b> " + folderName
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