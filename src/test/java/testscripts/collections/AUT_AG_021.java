package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
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
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_021 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_040() {
		testParameters
				.setCurrentTestDescription(
		 "1. ALFDEPLOY-3815_Verify the right file gets mapped after running Asset to auto linking script for Grid Thumbnail to link in Content object."
		+"<br>2. ALFDEPLOY-3815_Verify the right file gets mapped after running Asset to auto linking script for Thumbnail to link in Content object."
		+"<br>3. ALFDEPLOY-3815_Verify the right file gets mapped after running Asset to auto linking script for Asset to link in Content object."
		+"<br>4. ALFDEPLOY-3815_Verify the right file gets mapped after running Asset to auto linking script for Grid Thumbnail to link in learning bundles object."
		+"<br>5. ALFDEPLOY-3815_Verify the right file gets mapped after running Asset to auto linking script for Thumbnail to link in learning bundle object.");
		
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);

		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		String moreSettingsOptionName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		String link = dataTable.getData("MyFiles", "Subfolders1");

		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);

		// Create site
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteassertValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();

		siteName = siteName.toLowerCase();

		// upload Thumbnails files
		sitesPage.enterIntoDocumentLibrary();

		myFiles.openCreatedFolder("Assets");
		myFiles.openCreatedFolder("Thumbnails");
	
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		sitesPage.documentdetails(fileName);
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");

		docDetailsPageObj.performUnzipDocActionCollection(extractTo);

		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// enter into default course object
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);

		// create collections objects
		String createObjectData = dataTable.getData("MyFiles",
				"CollectionObjectBasicData");
		collectionPg
				.createBasicCollectionObjectFromCreateMenu(createObjectData);

		// verify Grid Thumbnail to link: ,Thumbnail to link: for content,
		// composite, learning bundel, sequence
		ArrayList<String> listOfObjects = new ArrayList<String>();
		listOfObjects = collectionPg.getFoldersFromRightPanInCollectionUi();

		for (String listOfObjectsString : listOfObjects) {
			if (listOfObjectsString.contains("AutoContentObj")
					|| listOfObjectsString.contains("AutoCompositeObj")
					|| listOfObjectsString.contains("AutoSeqObj")
					|| listOfObjectsString.contains("AutoLearningBundle")) {
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						listOfObjectsString, "Edit Properties");
				UIHelper.waitFor(driver);

				if (listOfObjectsString.contains("AutoContentObj")) {
					collectionPg.enterCollectionObjectA2LData(
							"Asset(s) to link:", link);
				}

				collectionPg.enterCollectionObjectA2LData(
						"Grid Thumbnail to link:", link);
				collectionPg.enterCollectionObjectA2LData("Thumbnail to link:",
						link);

				UIHelper.waitFor(driver);

				collectionPg.clickOnSaveBtn();
			}
		}

		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		// Click auto link assets on parent course
		driver.navigate().back();
		UIHelper.waitForPageToLoad(driver);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(
				collectionObjectName, moreSettingsOptionName);

		// enter into default course object
		collectionPg.openCollectionObject(collectionObjectName);

		// Verify respective grid thumbnail, thumbnail references were added
		for (String listOfObjectsString : listOfObjects) {
			if (listOfObjectsString.contains("AutoContentObj")
					|| listOfObjectsString.contains("AutoCompositeObj")
					|| listOfObjectsString.contains("AutoSeqObj")
					|| listOfObjectsString.contains("AutoLearningBundle")) {

				if (listOfObjectsString.contains("AutoContentObj")) {
					collectionPg.verifyListOfReferenveValue(
							listOfObjectsString, "outgoing",
							"Child References", link);
				}
				collectionPg.verifyListOfReferenveValue(listOfObjectsString,
						"outgoing", "Grid Thumbnail", link);
				collectionPg.verifyListOfReferenveValue(listOfObjectsString,
						"outgoing", "Thumbnail", link);

			}
		}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
