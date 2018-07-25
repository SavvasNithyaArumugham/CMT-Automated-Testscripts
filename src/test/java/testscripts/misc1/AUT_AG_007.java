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
public class AUT_AG_007 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_007() {
		testParameters.setCurrentTestDescription(
				"Verfiy user is able to 'Add Relationship' for between folders and site a from Browse Actions Menu<br>"
						+ "ALFDEPLOY-1997_4889_Verify the available user sites are listed in relationship document picker upon adding relationship via My files when user is member of less than 250 sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify the available user sites are listed in relationship document picker upon adding relationship via Shared files when user is member of less than 250 sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify the available user sites are listed in relationship document picker upon adding relationship via document library when user is member of less than 250 sites");
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

		String addRelationSite = dataTable.getData("Sites", "SiteToSelect");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String sitesCount = dataTable.getData("MyFiles", "ExpectedSitesCount");
		
		ArrayList<String> mysitesList=homePage.getSitesFromMySitesDashletInHomePage();
		
		sitesPage.siteFinder(addRelationSite);

		for (int x = 0; x < 3; x++) {
			switch (x) {
			case 0: {
				homePage.navigateToSitesTab();
				sitesPage.siteFinder(siteNameValue);
				sitesPage.enterIntoDocumentLibrary();

				break;
			}
			case 1: {
				homePage.navigateToSharedFilesTab();
				break;
			}
			case 2: {
				homePage.navigateToMyFilesTab();
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

			// sitesPage.enterIntoDocumentLibrary();

			// docLibPage.deleteAllFilesAndFolders();

			// docLibPage.deleteAllFilesAndFolders();
			myFiles.createFolder(folderDetails);
			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.clickOnMoreOptionLink(folderName);

			String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
			sitesPage.selectRelationshipNameAndclickOnAddRelationshipBtnFromMySharedFiles(relationshipName, folderName);

			int expectedSitesCount = Integer.parseInt(sitesCount.replace("'", ""));
			sitesPageTestObj.verifySiteNamesInSelectWindowForAddRelationship(expectedSitesCount);
			
			ArrayList<String> relationshipPickerSitesList=sitesPage.getSitesFromRelationshipPicker();

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

			sitesPage.clickOnViewDetails(folderName);
			if (docDetailsPage.isRelationshipAddedForSite()) {
				if (addRelationSite.equalsIgnoreCase(docDetailsPage.getRelationshipTableVal()) && (relationshipName
						.equalsIgnoreCase(docDetailsPage.getRelationshipValFromList(relationshipName)))) {
					report.updateTestLog(
							"Verify Relationship Added between folder and site from Browse Actions Menu is listed in relationship section",
							"Relationship Added is listed in relationship section successfully"
									+ "</br><b>Folder Name:</b> " + folderName + "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName)
									+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
							Status.PASS);
				} else {
					report.updateTestLog(
							"Verify Relationship Added between folder and site from Browse Actions Menu is listed in relationship section",
							"Relationship added is not listed" + "</br><b>Folder Name:</b> " + folderName
									+ "</br><b>Relationship Name:</b> "
									+ docDetailsPage.getRelationshipValFromList(relationshipName)
									+ "</br><b>Site Name:</b> " + docDetailsPage.getRelationshipTableVal(),
							Status.FAIL);
				}
			} else {
				report.updateTestLog(
						"Verify Relationship Added between folder and site from Browse Actions Menu is listed in relationship section",
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