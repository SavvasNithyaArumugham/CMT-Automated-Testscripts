package testscripts.release184;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author ASHOK
 */
public class ALFDEPLOY_2767_TC001_P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runALFDEPLOY_2767_TC001_P2() {
		testParameters
				.setCurrentTestDescription("Within the collection UI, can see a 'duplicate all' action on any Course, Sequence Object, Container, Learning Bundle, Composite Object and Content Object");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(
				scriptHelper);

		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		String createObjectData = dataTable.getData("MyFiles",
				"CollectionObjectBasicData");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		ArrayList<String> levelOneObjectList = new ArrayList<>();
		ArrayList<String> originalObjectList = new ArrayList<>();
		ArrayList<String> duplicateObjectList_A_B = new ArrayList<>();
		ArrayList<String> duplicateAllObjectList_A_B = new ArrayList<>();
		ArrayList<String> duplicateAllToObjectList_A_B = new ArrayList<>();
		ArrayList<String> duplicateObjectList_B_C = new ArrayList<>();
		ArrayList<String> duplicateAllObjectList_B_C = new ArrayList<>();
		ArrayList<String> duplicateAllToObjectList_B_C = new ArrayList<>();

		// // login
		functionalLibrary.loginAsValidUser(signOnPage);

		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		// sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		// goto collection UI
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// enter into default course object
		collectionPg.openCollectionObject(collectionObjectName);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		levelOneObjectList = collectionPg
				.getFoldersFromRightPanInCollectionUi();

		for (String string : levelOneObjectList) {
			if (!string.contains("-")) {
				originalObjectList.add(string);

			}

		}

		for (String string : levelOneObjectList) {
			if (string.contains("-7") && !string.contains("-7-1")) {
				duplicateObjectList_A_B.add(string);

			}

		}

		for (String string : levelOneObjectList) {
			if (string.contains("-8") && !string.contains("-8-1")) {
				duplicateAllObjectList_A_B.add(string);

			}

		}

		for (String string : levelOneObjectList) {
			if (string.contains("-9") && !string.contains("-9-1")) {
				duplicateAllToObjectList_A_B.add(string);

			}
		}

		for (String string : levelOneObjectList) {
			if (string.contains("-7-1")) {
				duplicateObjectList_B_C.add(string);

			}

		}

		for (String string : levelOneObjectList) {
			if (string.contains("-8-1")) {
				duplicateAllObjectList_B_C.add(string);

			}

		}

		for (String string : levelOneObjectList) {
			if (string.contains("-9-1")) {
				duplicateAllToObjectList_B_C.add(string);

			}
		}

		System.out.println(levelOneObjectList);
		System.out.println(originalObjectList);
		System.out.println(duplicateObjectList_A_B);
		System.out.println(duplicateAllObjectList_A_B);
		System.out.println(duplicateAllToObjectList_A_B);
		System.out.println(duplicateObjectList_B_C);
		System.out.println(duplicateAllObjectList_B_C);
		System.out.println(duplicateAllToObjectList_B_C);

		// verify tumbnail for duplicateObjectList_A_B

		for (String string : duplicateObjectList_A_B) {

			if (!string.contains("Asset") && !string.contains("Course")
					&& !string.contains("Container")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Thumbnail");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Grid Thumbnail");
			}

		}

		// verify tumbnail for duplicateAllObjectList_A_B

		for (String string : duplicateAllObjectList_A_B) {

			if (!string.contains("Asset") && !string.contains("Course")
					&& !string.contains("Container")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Thumbnail");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Grid Thumbnail");
			}

		}

		// verify tumbnail for duplicateAllToObjectList_A_B

		for (String string : duplicateAllToObjectList_A_B) {

			if (!string.contains("Asset") && !string.contains("Course")
					&& !string.contains("Container")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Thumbnail");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Grid Thumbnail");
			}

		}

		// verify Was originally duplicated from and Was duplicated from for
		// duplicateObjectList_A_B

		for (String string : duplicateObjectList_A_B) {

			if (!string.contains("Learning") && !string.contains("Course")
					&& !string.contains("Container") && !string.contains("Seq")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was originally duplicated from");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was duplicated from");
			}

		}

		// verify Was originally duplicated from and Was duplicated from for
		// duplicateAllObjectList_A_B

		for (String string : duplicateAllObjectList_A_B) {

			if (!string.contains("Asset") && !string.contains("Container")
					&& !string.contains("Course") && !string.contains("Seq")
					&& !string.contains("Learning")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was originally duplicated from");

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was duplicated from");
			}

		}

		// verify Was originally duplicated from and Was duplicated from for
		// duplicateAllToObjectList_A_B

		for (String string : duplicateAllToObjectList_A_B) {

			if (!string.contains("Asset") && !string.contains("Container")
					&& !string.contains("Course") && !string.contains("Seq")
					&& !string.contains("Learning")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was originally duplicated from");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was duplicated from");
			}

		}

		// verify Was originally duplicated from and Was duplicated from for
		// duplicateObjectList_A_B is not populated

		for (String string : originalObjectList) {

			if (string.contains("Course") || string.contains("Container")
					|| string.contains("Learning")) {

				collectionPg
						.verifyRelationshipViewerOnObjectsInCollectionUiNegative(
								string, "outgoing",
								"Was originally duplicated from");
				collectionPg
						.verifyRelationshipViewerOnObjectsInCollectionUiNegative(
								string, "outgoing", "Was duplicated from");
			}

		}

		// verify "Was duplicated from" and "Has duplicates" for content and
		// composite object

		for (String string : duplicateObjectList_A_B) {

			if (!string.contains("Asset") && !string.contains("Container")
					&& !string.contains("Course") && !string.contains("Seq")
					&& !string.contains("Learning")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was duplicated from");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "incoming", "Has duplicates");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was originally duplicated from");

			}

		}

		for (String string : duplicateAllObjectList_A_B) {

			if (!string.contains("Asset") && !string.contains("Container")
					&& !string.contains("Course") && !string.contains("Seq")
					&& !string.contains("Learning")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was duplicated from");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "incoming", "Has duplicates");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was originally duplicated from");

			}

		}

		for (String string : duplicateAllToObjectList_A_B) {

			if (!string.contains("Asset") && !string.contains("Container")
					&& !string.contains("Course") && !string.contains("Seq")
					&& !string.contains("Learning")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was duplicated from");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "incoming", "Has duplicates");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "outgoing", "Was originally duplicated from");

			}

		}

		// verify "has duplicate" and "has original duplicate"
		for (String string : originalObjectList) {

			if (!string.contains("Asset") && !string.contains("Container")
					&& !string.contains("Course") && !string.contains("Seq")
					&& !string.contains("Learning")) {

				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "incoming", "Has duplicates");
				collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
						string, "incoming", "Has original duplicates");

			}

		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}