package testscripts.collections1;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2241_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_017() {
		testParameters.setCurrentTestDescription(
				"1. As a user, I want to create direct children Files when I upload files within a Composite Object, so that these objects can be created as direct primary children rather than references"
						+ "<br> 2. Within the Collections UI, can select a Composite Object in the left pane and click 'Upload' button to select one or more files");

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

		// Login into Application
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);

		homePageObj.navigateToSitesTab();

		// Create a new collection site
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();

		sitesPage.openSiteFromRecentSites(siteName);

		// Enter into Document library
		sitesPage.enterIntoDocumentLibrary();

		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		// Navigate to Programs>Program object
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPgTest.verifyEditCollectionOption();

		// Navigate to Collection UI
		collectionPg.clickOnEditCollectionButton();

		// Create new collection objects
		collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);

		ArrayList<String> collectionObjNamesAndTypeList = collectionPgTest
				.getCreateCollectionObjectNamesWithObjectType(createObjectData);

		// Enter into Document library
		sitesPage.enterIntoDocumentLibrary();

		for (String collectionObjectNameAndType : collectionObjNamesAndTypeList) {

			if (collectionObjectNameAndType.contains("-")) {
				String splittedCollectionObjectNameAndType[] = collectionObjectNameAndType.split("-");

				if (splittedCollectionObjectNameAndType != null && splittedCollectionObjectNameAndType.length > 1) {
					String collectionObjectType = splittedCollectionObjectNameAndType[0];
					String collectionObjectName = splittedCollectionObjectNameAndType[1];

					collectionPg.clickOnCollectionObjectFromTreeViewInDocLibPag(collectionObjectType,
							collectionObjectName);

					// Navigate to created collections object
					myFiles.openCreatedFolder(collectionObjectName);

					// Upload asset/files inside collections object
					myFiles.uploadFileInMyFilesPage(filePath, fileName);

					if (fileName.contains(",")) {
						String splittedFileNames[] = fileName.split(",");
						for (String fileNameVal : splittedFileNames) {

							myFiles.openUploadedOrCreatedFile(fileNameVal, "");
							collectionPgTest.verifyRelationshipsForUpldaedFilesOfCompObjInDetailsPageByShareUI(
									collectionObjectType, collectionObjectName);

						}
					} else {
						myFiles.openUploadedOrCreatedFile(fileName, "");
						collectionPgTest.verifyRelationshipsForUpldaedFilesOfCompObjInDetailsPageByShareUI(
								collectionObjectType, collectionObjectName);
					}
				}
			}
		}

		sitesPage.enterIntoDocumentLibrary();

		String assetTypes = dataTable.getData("MyFiles", "AssetTypes");

		// Validate uploaded file is available in asset folders
		collectionPg.clickOnFolderInLeftsideTreeView("Asset");

		if (assetTypes.contains(",")) {
			String splittedAssetTypes[] = assetTypes.split(",");
			if (splittedAssetTypes != null) {
				for (String assetType : splittedAssetTypes) {

					collectionPgTest.verifyBucketingRulesForAssetTypeInNegativeCase(assetType);
				}
			}
		} else {
			collectionPgTest.verifyBucketingRulesForAssetTypeInNegativeCase(assetTypes);
		}

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
