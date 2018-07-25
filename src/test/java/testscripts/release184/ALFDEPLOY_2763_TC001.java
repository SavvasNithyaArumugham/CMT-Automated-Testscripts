package testscripts.release184;

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
public class ALFDEPLOY_2763_TC001 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runALFDEPLOY_2763_TC001() {
		testParameters
				.setCurrentTestDescription("1)Can change the name of a previously uploaded asset, so that the first letter of the name is different and 1) can see it automatically move to the letter bucket which matches the new name, 2)Verify that Changing the name of a previously uploaded for multiple asset in same folder, so that the first letter of the name is different and 1) can see it automatically move to the letter bucket which matches the new name 3)Verify that Changing the name of a created content object (Course, content object, composite object, container, sequence object or learning bundle) in same folder, so that the first letter of the name is different and 1) can see it automatically move to the letter bucket which matches the new name");

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
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		String createObjectData = dataTable.getData("MyFiles",
				"CollectionObjectBasicData");
		String assetTypes = dataTable.getData("MyFiles", "AssetTypes");
		String assetTypes2 = dataTable.getData("MyFiles", "AssetTypes2");
		String assetTypes3 = dataTable.getData("MyFiles", "AssetTypes3");
		String assetTypes4 = dataTable.getData("MyFiles", "AssetTypes4");

		// login
		functionalLibrary.loginAsValidUser(signOnPage);

		// goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		// goto site > document lib >Programs > Program object
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// upload multiple files in share ui
		collectionPg.uploadFileInCollectionSite(filePath, fileName);

		// create collection objects in collection ui
		collectionPg.clickOnEditCollectionButton();
		collectionPg.openCollectionObject(collectionObjectName);
		collectionPg
				.createBasicCollectionObjectFromCreateMenu(createObjectData);

		// verify child reference of the folder where filed are uploaded
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		sitesPage.clickOnViewDetails(folderNames[1]);
		collectionPgTest.verifyChildReferencesForUploadedFiles("Assets",
				assetTypes);

		// verify bucketing rules by navigating respective asset folders
		try {
			sitesPage.enterIntoDocumentLibrary();
			collectionPg.clickOnFolderInLeftsideTreeView("Asset");
			UIHelper.waitForPageToLoad(driver);
			if (assetTypes.contains(",")) {
				String splittedAssetTypes[] = assetTypes.split(",");
				if (splittedAssetTypes != null) {
					for (String assetType : splittedAssetTypes) {
						UIHelper.waitForPageToLoad(driver);
						collectionPgTest
								.verifyBucketingRulesForAssetType(assetType);
					}
				}
			} else {
				UIHelper.waitForPageToLoad(driver);
				collectionPgTest.verifyBucketingRulesForAssetType(assetTypes);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Exception occured while veryfind bucketing rules in ALFDEPLOY_2763_TC001 : line No:116 ");
			driver.quit();
		}

		// rename auto bucketed files
		try {
			sitesPage.enterIntoDocumentLibrary();
			collectionPg.clickOnFolderInLeftsideTreeView("Asset");
			UIHelper.waitForPageToLoad(driver);
			if (assetTypes.contains(",")) {
				String splittedAssetTypes[] = assetTypes.split(",");
				if (splittedAssetTypes != null) {
					for (String assetType : splittedAssetTypes) {
						UIHelper.waitForPageToLoad(driver);
						collectionPgTest.renameBucketedFiles(assetType);

					}
				}
			} else {
				UIHelper.waitForPageToLoad(driver);
				collectionPgTest.renameBucketedFiles(assetTypes);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Exception occured while renaming auto buckted folder in ALFDEPLOY_2763_TC001 : line No:140 ");
			driver.quit();
		}

		// verify bucketing rules by navigating respective asset folders after
		// renaming the files
		try {
			sitesPage.enterIntoDocumentLibrary();
			collectionPg.clickOnFolderInLeftsideTreeView("Asset");
			UIHelper.waitForPageToLoad(driver);
			if (assetTypes2.contains(",")) {
				String splittedAssetTypes[] = assetTypes2.split(",");
				if (splittedAssetTypes != null) {
					for (String assetType : splittedAssetTypes) {
						UIHelper.waitForPageToLoad(driver);
						collectionPgTest
								.verifyBucketingRulesForAssetType(assetType);
					}
				}
			} else {
				UIHelper.waitForPageToLoad(driver);
				collectionPgTest.verifyBucketingRulesForAssetType(assetTypes2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Exception occured while veryfind bucketing rules in ALFDEPLOY_2763_TC001 : line No:168 ");
			driver.quit();
		}

		// verify child reference of the folder where filed are uploaded after
		// rename
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		sitesPage.clickOnViewDetails(folderNames[1]);
		collectionPgTest.verifyChildReferencesForUploadedFiles("Assets",
				assetTypes2);

		// verify child reference of the folder where collection object are
		// created
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Courses");
		sitesPage.clickOnViewDetails("Course");
		collectionPgTest.verifyChildReferencesForUploadedFiles(
				"Composite Object", "Composite Objects-AutoCompositeObj");
		collectionPgTest.verifyChildReferencesForUploadedFiles("Containers-",
				"Containers-AutoContainer");
		collectionPgTest.verifyChildReferencesForUploadedFiles(
				"Content Objects", "Content Objects-AutoContentObj");
		collectionPgTest.verifyChildReferencesForUploadedFiles(
				"Learning Bundles", "Learning Bundles-AutoLearningBundle");
		collectionPgTest.verifyChildReferencesForUploadedFiles(
				"Sequence Objects", "Sequence Objects-AutoSeqObj");
		collectionPgTest.verifyChildReferencesForUploadedFiles("Assets",
				"Other-AutoAsset");

		// rename auto bucketed collection objects
		try {
			sitesPage.enterIntoDocumentLibrary();
			UIHelper.waitForPageToLoad(driver);
			if (assetTypes3.contains(",")) {
				String splittedAssetTypes[] = assetTypes3.split(",");
				if (splittedAssetTypes != null) {
					for (String assetType : splittedAssetTypes) {
						UIHelper.waitForPageToLoad(driver);
						try {
							if (assetType.contains("-")) {
								String splittedAssetNameAndType[] = assetType
										.split("-");

								if (splittedAssetNameAndType != null
										&& splittedAssetNameAndType.length > 1) {
									String assetTypeVal = splittedAssetNameAndType[0];
									String assetName = splittedAssetNameAndType[1];

									if (assetName.contains(":")) {
										String splittedAssetNames[] = assetName
												.split(":");

										if (splittedAssetNames != null) {
											for (String assetNameVal : splittedAssetNames) {
												UIHelper.waitForPageToLoad(driver);

												collectionPg
														.clickOnAssetsSubFoldersFromTreeViewInDocLibPag(
																"Document",
																assetNameVal,
																assetTypeVal);

												UIHelper.waitForPageToLoad(driver);
												collectionPgTest
														.verifyBucketingRulesAndRename(assetNameVal);
											}
										}

									} else {
										UIHelper.waitForPageToLoad(driver);

										collectionPg
												.clickOnAssetsSubFoldersFromTreeViewInDocLibPag(
														"Document", assetName,
														assetTypeVal);
										UIHelper.waitForPageToLoad(driver);

										collectionPgTest
												.verifyBucketingRulesAndRename(assetName);
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						UIHelper.waitForPageToLoad(driver);

					}
				}
			} else {
				UIHelper.waitForPageToLoad(driver);
				collectionPgTest.renameBucketedFiles(assetTypes3);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Exception occured while renaming auto buckted folder in ALFDEPLOY_2763_TC001 : line No:140 ");
			driver.quit();
		}

		// verify child reference of the folder where collection object are
		// created
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Courses");
		sitesPage.clickOnViewDetails("Course");
		collectionPgTest.verifyChildReferencesForUploadedFiles(
				"Composite Object", "Composite Objects-ButoCompositeObj");
		collectionPgTest.verifyChildReferencesForUploadedFiles("Containers-",
				"Containers-ButoContainer");
		collectionPgTest.verifyChildReferencesForUploadedFiles(
				"Content Objects", "Content Objects-ButoContentObj");
		collectionPgTest.verifyChildReferencesForUploadedFiles(
				"Learning Bundles", "Learning Bundles-ButoLearningBundle");
		collectionPgTest.verifyChildReferencesForUploadedFiles(
				"Sequence Objects", "Sequence Objects-ButoSeqObj");
		collectionPgTest.verifyChildReferencesForUploadedFiles("Assets",
				"Other-ButoAsset");

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}