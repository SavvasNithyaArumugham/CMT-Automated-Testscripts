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
public class ALFDEPLOY_2767_TC001_P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runALFDEPLOY_2767_TC001_P1() {
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

		// // login
		functionalLibrary.loginAsValidUser(signOnPage);

		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		// upload imagefile
		collectionPg.uploadFileInCollectionSite(filePath, fileName);

		// goto collection UI
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// enter into default course object
		collectionPg.openCollectionObject(collectionObjectName);

		// create collections objects
		collectionPg
				.createBasicCollectionObjectFromCreateMenu(createObjectData);

		levelOneObjectList = collectionPg
				.getFoldersFromRightPanInCollectionUi();

		System.out.println(levelOneObjectList);

		// add Thumbnail for each object
		collectionPg.addThumbnailAndGridThumbnail();
		UIHelper.waitForPageToLoad(driver);

		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.openCollectionObject(collectionObjectName);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		levelOneObjectList = collectionPg
				.getFoldersFromRightPanInCollectionUi();
		UIHelper.waitFor(driver);

		// create collection objects inside created collection object
		for (String objectName : levelOneObjectList) {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			if (!objectName.contains("Asset")) {
				System.out.println(objectName);

				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.openCollectionObject(collectionObjectName);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				collectionPg.openCollectionObject(objectName);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				collectionPg
						.createBasicCollectionObjectFromCreateMenu(createObjectData);
			} else {
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);
			}
		}

		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.openCollectionObject(collectionObjectName);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		levelOneObjectList = collectionPg
				.getFoldersFromRightPanInCollectionUi();
		UIHelper.waitFor(driver);

		System.out.println(levelOneObjectList);

		// Duplicate object A-B
		for (String objectName : levelOneObjectList) {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			collectionPg.clickOnMoreSetting(objectName);
			collectionPg.commonMethodForClickOnMoreSettingsOption(objectName,
					"Duplicate object");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		}

		driver.navigate().refresh();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		// Duplicate all A-B
		for (String objectName : levelOneObjectList) {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			if (!objectName.contains("Asset")) {
				collectionPg.clickOnMoreSetting(objectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						objectName, "Duplicate all");
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		}

		// Duplicate all to... A-B
		for (String objectName : levelOneObjectList) {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			if (!objectName.contains("Asset")) {
				collectionPg.clickOnMoreSetting(objectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						objectName, "Duplicate all to...");
				collectionPg.mapSiteForDuplicateAllTo(siteName);
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		}
		String assetTypes = dataTable.getData("MyFiles", "AssetTypes3");

		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		// add parent reference to duplicated object except course using
		// duplicate all to
		try {
			if (assetTypes.contains(",")) {
				String splittedAssetTypes[] = assetTypes.split(",");
				if (splittedAssetTypes != null) {

					for (String assetTypeStr : splittedAssetTypes) {
						try {
							if (assetTypeStr.contains("-")) {
								String splittedAssetNameAndType[] = assetTypeStr
										.split("-");
								if (splittedAssetNameAndType != null
										&& splittedAssetNameAndType.length > 1) {

									String assetTypeVal = splittedAssetNameAndType[0];
									String assetName = splittedAssetNameAndType[1];
									collectionPg
											.clickOnAssetsSubFoldersFromTreeViewInDocLibPag(
													"Document", assetName,
													assetTypeVal);
									UIHelper.waitFor(driver);
									UIHelper.waitFor(driver);
									collectionPgTest
											.verifyBucketingRules(assetName
													+ "-9");
									UIHelper.waitFor(driver);
									UIHelper.waitFor(driver);
									collectionPg.clickOnMoreSetting(assetName
											+ "-9");
									UIHelper.waitFor(driver);
									collectionPg
											.commonMethodForClickOnMoreSettingsOption(
													assetName + "-9",
													"View Details");
									UIHelper.waitFor(driver);
									collectionPg
											.addReferenceInViewDetailsPageInShareUi(
													"Parent", "Courses/Course");
									UIHelper.waitFor(driver);

									sitesPage.enterIntoDocumentLibrary();

								}

							}

						} catch (Exception e) {
							// TODO: handle exception
						}
					}

					// add parent reference to duplicated course object
					// using duplicate all to

					sitesPage.enterIntoDocumentLibrary();
					myFiles.openCreatedFolder("Courses");
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					collectionPg.clickOnMoreSetting("AutoCourse-9");
					UIHelper.waitFor(driver);
					collectionPg.commonMethodForClickOnMoreSettingsOption(
							"AutoCourse-9", "View Details");
					UIHelper.waitFor(driver);
					collectionPg.addReferenceInViewDetailsPageInShareUi(
							"Parent", "Courses/Course");
					UIHelper.waitFor(driver);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception

		}

		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.openCollectionObject(collectionObjectName);

		levelOneObjectList = collectionPg
				.getFoldersFromRightPanInCollectionUi();
		UIHelper.waitFor(driver);

		System.out.println(levelOneObjectList);

		// duplicate Object B->C
		for (String levelOneObject : levelOneObjectList) {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			if (levelOneObject.contains("-7")) {
				collectionPg.clickOnMoreSetting(levelOneObject);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						levelOneObject, "Duplicate object");

			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		}

		// duplicate all B->C
		for (String levelOneObject : levelOneObjectList) {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			if (levelOneObject.contains("-8")) {

				collectionPg.clickOnMoreSetting(levelOneObject);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						levelOneObject, "Duplicate all");

			}

		}

		// Duplicate all to... B-C
		for (String objectName : levelOneObjectList) {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			if (objectName.contains("-9")) {
				collectionPg.clickOnMoreSetting(objectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						objectName, "Duplicate all to...");
				collectionPg.mapSiteForDuplicateAllTo(siteName);
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		}

		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		// add parent reference to duplicated object except course using
		// duplicate all to B-C
		try {
			if (assetTypes.contains(",")) {
				String splittedAssetTypes[] = assetTypes.split(",");
				if (splittedAssetTypes != null) {

					for (String assetTypeStr : splittedAssetTypes) {
						try {
							if (assetTypeStr.contains("-")) {
								String splittedAssetNameAndType[] = assetTypeStr
										.split("-");
								if (splittedAssetNameAndType != null
										&& splittedAssetNameAndType.length > 1) {

									String assetTypeVal = splittedAssetNameAndType[0];
									String assetName = splittedAssetNameAndType[1];
									collectionPg
											.clickOnAssetsSubFoldersFromTreeViewInDocLibPag(
													"Document", assetName,
													assetTypeVal);
									UIHelper.waitFor(driver);
									UIHelper.waitFor(driver);
									collectionPgTest
											.verifyBucketingRules(assetName
													+ "-9-1");
									UIHelper.waitFor(driver);
									UIHelper.waitFor(driver);
									collectionPg.clickOnMoreSetting(assetName
											+ "-9-1");
									UIHelper.waitFor(driver);
									collectionPg
											.commonMethodForClickOnMoreSettingsOption(
													assetName + "-9-1",
													"View Details");
									UIHelper.waitFor(driver);
									collectionPg
											.addReferenceInViewDetailsPageInShareUi(
													"Parent", "Courses/Course");
									UIHelper.waitFor(driver);

									sitesPage.enterIntoDocumentLibrary();

								}

							}

						} catch (Exception e) {
							// TODO: handle exception
						}
					}

					// add parent reference to duplicated course object
					// using duplicate all to B-C

					sitesPage.enterIntoDocumentLibrary();
					myFiles.openCreatedFolder("Courses");
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					collectionPg.clickOnMoreSetting("AutoCourse-9-1");
					UIHelper.waitFor(driver);
					collectionPg.commonMethodForClickOnMoreSettingsOption(
							"AutoCourse-9", "View Details");
					UIHelper.waitFor(driver);
					collectionPg.addReferenceInViewDetailsPageInShareUi(
							"Parent", "Courses/Course");
					UIHelper.waitFor(driver);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}