package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoCollectionsPage.java - This program contains methods for Collections
 * UI functionalities
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoCollectionsPageTest extends ReusableLibrary {

	private AlfrescoCollectionsPage appCollectionsPg = new AlfrescoCollectionsPage(scriptHelper);
	String createLinkXpath = appCollectionsPg.createLinkXpath;
	private String collectionRelationFilePathXpath = ".//*[@class='relationships-list-container-table']//*[@class='relationships-element-type-table']//div[contains(.,'RELATIONSHIP')]//following-sibling::*//a";
	private String collectionDOMXpath = ".//*[@class='relationships-list-container-table']";

	private AlfrescoMyFilesPage appMyFilesPg = new AlfrescoMyFilesPage(scriptHelper);

	private boolean isDisplayedUploadedFile;

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoCollectionsPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Get uploaded file/folder name from My Files Page
	public void verifyEditCollectionOption() {
		try {
			boolean isDisplayedEditCollectionOption = appCollectionsPg.checkEditCollectionOptionInCollectionPage();

			if (isDisplayedEditCollectionOption) {
				report.updateTestLog("Verify 'Edit Collection' Option",
						"'Edit Collection' option displayed Successfully in Collections UI screen", Status.PASS);
			} else {
				report.updateTestLog("Verify 'Edit Collection' Option",
						"'Edit Collection' option fialed to display in Collections UI screen", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Create Menu Items
	public void verifyCreateMenuItems(String expectedCreateMenuItems) {
		ArrayList<String> expectedCreateMenuItemsList = new ArrayList<String>();
		try {
			if (expectedCreateMenuItems.contains(",")) {
				String expectedCreateMenuValues[] = expectedCreateMenuItems.split(",");

				for (String createMenuItem : expectedCreateMenuValues) {
					expectedCreateMenuItemsList.add(createMenuItem);
				}
			}

			boolean isDisplayedIconsForCreateMenuItems = appCollectionsPg
					.checkCreateMenuItemsIcons(expectedCreateMenuItemsList);

			ArrayList<String> actualCreateMenuItemsList = appCollectionsPg.getCreateMenuItems();

			if (UIHelper.compareTwoDiffSizeOfLists(actualCreateMenuItemsList, expectedCreateMenuItemsList)
					&& isDisplayedIconsForCreateMenuItems) {
				report.updateTestLog("Verify 'Create' menu items",
						"'Create' menu items are displayed Successfully with image icons"
								+ "<br /><b>Expected Result:</b> " + expectedCreateMenuItemsList
								+ ", <br /><b>Actual Result:</b> " + actualCreateMenuItemsList + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Create' menu items",
						"'Create' menu items are fialed to display with image icons in 'Create' Menu list"
								+ "<br /><b>Expected Result:</b> " + expectedCreateMenuItemsList
								+ ", <br /><b>Actual Result:</b> " + actualCreateMenuItemsList + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Create Menu Items
	public void verifyCreateMenuItemsForNegativeCase(String expectedCreateMenuItems) {
		ArrayList<String> expectedCreateMenuItemsList = new ArrayList<String>();
		try {
			if (expectedCreateMenuItems.contains(",")) {
				String expectedCreateMenuValues[] = expectedCreateMenuItems.split(",");

				for (String createMenuItem : expectedCreateMenuValues) {
					expectedCreateMenuItemsList.add(createMenuItem);
				}
			}

			ArrayList<String> actualCreateMenuItemsList = appCollectionsPg.getCreateMenuItems();

			if (!UIHelper.compareTwoDiffSizeOfLists(actualCreateMenuItemsList, expectedCreateMenuItemsList)) {
				report.updateTestLog("Verify 'Create' menu items",
						expectedCreateMenuItemsList + " are NOT present in the 'Create' menu", Status.PASS);
			} else {
				report.updateTestLog("Verify 'Create' menu items",
						expectedCreateMenuItemsList + " are present in the 'Create' menu", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Aspect added
	public void verifyAddedAspect(String aspectName) {
		try {

			boolean isDisplayedAspect = appCollectionsPg.isAspectsAdded(aspectName);

			if (isDisplayedAspect) {
				report.updateTestLog("Verify Aspect added to the folder/file",
						"<b>" + aspectName + "</b> added to the folder/file", Status.PASS);
			} else {
				report.updateTestLog("Verify Aspect added to the folder/file",
						"<b>" + aspectName + "</b> is not added to the folder/file", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Create Collection object names from excel
	public ArrayList<String> getCreateCollectionObjectNames(String createObjectData) {
		ArrayList<String> collectionObjNamesList = new ArrayList<String>();
		try {
			if (createObjectData.contains(";")) {
				String splittedObjectData[] = createObjectData.split(";");
				for (String objectValues : splittedObjectData) {
					String splittedObjectValues[] = objectValues.split(",");

					if (splittedObjectValues != null) {
						String objectType = splittedObjectValues[1].replace("Name:", "");

						collectionObjNamesList.add(objectType);
					}
				}
			} else {

				String splittedObjectData[] = createObjectData.split(",");

				if (splittedObjectData != null) {
					String objectType = splittedObjectData[1].replace("Name:", "");

					collectionObjNamesList.add(objectType);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return collectionObjNamesList;
	}

	// Get Create Collection object names with Object type from excel
	public ArrayList<String> getCreateCollectionObjectNamesWithObjectType(String createObjectData) {
		ArrayList<String> collectionObjNamesList = new ArrayList<String>();
		try {
			if (createObjectData.contains(";")) {
				String splittedObjectData[] = createObjectData.split(";");
				for (String objectValues : splittedObjectData) {
					String splittedObjectValues[] = objectValues.split(",");

					if (splittedObjectValues != null) {
						
						String objectType = splittedObjectValues[0].replace("ObjectType:", "");
						
						String objectName = splittedObjectValues[1].replace("Name:", "");
						collectionObjNamesList.add(objectType + "-" + objectName);
					
						
					}
				}
			} else {

				String splittedObjectData[] = createObjectData.split(",");

				if (splittedObjectData != null) {
					String objectType = splittedObjectData[0].replace("ObjectType:", "");
					String objectName = splittedObjectData[1].replace("Name:", "");

					collectionObjNamesList.add(objectType + "-" + objectName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return collectionObjNamesList;
	}

	// Verify Create Menu Items
	public void verifyImageIconForCreatedObjects(String createdObjectName, String objectType) {
		try {

			boolean isDisplayedImageIconsForCreatedObjects = appCollectionsPg
					.checkImageIconsForCreatedObjects(createdObjectName, objectType);

			if (isDisplayedImageIconsForCreatedObjects) {
				report.updateTestLog("Verify Image Icon for objects in List View", objectType
						+ " object image icon is displayed Successfully for created object:" + createdObjectName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Image Icon for objects in List View",
						objectType + " object image icon is failed to display for created object:" + createdObjectName,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Create Menu Items
	public void verifyImageIconForLeftSideTreeObjectsInCollectionUI(String createdObjectName, String objectType) {
		try {

			boolean isDisplayedImageIconsForCreatedObjects = appCollectionsPg
					.checkImageIconsForLeftSideTreeObjectsInCollectionUI(createdObjectName, objectType);

			if (isDisplayedImageIconsForCreatedObjects) {
				report.updateTestLog("Verify Image Icon for Created objects in Left Tree in Collections UI", objectType
						+ " object image icon is displayed Successfully for created object:" + createdObjectName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Image Icon for Created objects in Left Tree in Collections UI",
						objectType + "object image icon is failed to display for created object:" + createdObjectName,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Field Label name and value from excel sheet
	public ArrayList<String> getrCollectionObjectData(String collectionObjectData) {
		ArrayList<String> dataList = new ArrayList<String>();
		try {
			StringTokenizer token = new StringTokenizer(collectionObjectData, ",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				String title = "";
				String dataType = "";
				String value = "";
				StringTokenizer subToken = new StringTokenizer(docProperty, "-");
				while (subToken.hasMoreElements()) {
					title = subToken.nextToken().trim();
					dataType = subToken.nextToken().trim();
					value = subToken.nextToken().trim();
					dataList.add(title + " " + value);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	// Verify updated data in Edit properties page
	public void verifyFieldValuesInEditPropPage(String collectionObjectData) {
		try {

			ArrayList<String> dataList = getrCollectionObjectData(collectionObjectData);

			boolean isDisplayedEntereFieldData = appCollectionsPg
					.checkCollectionObjectDataInEditPropPg(collectionObjectData);

			if (isDisplayedEntereFieldData) {
				report.updateTestLog("Verify field values in 'Edit Properties' Page",
						"field Values:" + dataList + " are displayed Successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify field values in 'Edit Properties' Page",
						"field Values:" + dataList + " are failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify updated data in Edit properties page
	public void verifyFieldValuesInEditPropPageForNegativeCase(String collectionObjectData) {
		try {

			ArrayList<String> dataList = getrCollectionObjectData(collectionObjectData);

			boolean isDisplayedEntereFieldData = appCollectionsPg
					.checkCollectionObjectDataInEditPropPg(collectionObjectData);

			if (!isDisplayedEntereFieldData) {
				report.updateTestLog("Verify field values in 'Edit Properties' Page when perform 'Cancel'",
						"Entered field Values:" + dataList + " are not saved in Properties Page", Status.PASS);
			} else {
				report.updateTestLog("Verify field values in 'Edit Properties' Page when perform 'Cancel'",
						"Entered field Values:" + dataList + " are saved in Properties Page", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify updated data in Edit properties page
	public void verifyFieldLabelNamesInEditPropPage(String editPropFieldLabelNames) {
		try {
			ArrayList<String> expectedFieldLabelNamesListFromEditProp = new ArrayList<String>();

			StringTokenizer token = new StringTokenizer(editPropFieldLabelNames, ",");
			while (token.hasMoreElements()) {
				String filedLabel = token.nextToken();
				expectedFieldLabelNamesListFromEditProp.add(filedLabel);
			}

			ArrayList<String> actualFieldLabelNamesListFromEditProp = appCollectionsPg
					.checkCollectionObjectFieldLabelsInEditPropPg();

			if (UIHelper.compareTwoDiffSizeOfLists(actualFieldLabelNamesListFromEditProp,
					expectedFieldLabelNamesListFromEditProp)) {
				report.updateTestLog("Verify field label names in 'Edit Properties' Page",
						"User able to see all field label names in 'Edit Properties' Page" + "Expected Result: "
								+ expectedFieldLabelNamesListFromEditProp + "Actual Result: "
								+ actualFieldLabelNamesListFromEditProp,
						Status.PASS);
			} else {
				report.updateTestLog("Verify field label names in 'Edit Properties' Page",
						"Failed to display allfield label names in 'Edit Properties' Page" + "Expected Result: "
								+ expectedFieldLabelNamesListFromEditProp + "Actual Result: "
								+ actualFieldLabelNamesListFromEditProp,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Accepted Types values
	public void verifyAcceptedTypesValues(String acceptedTypeValues) {
		try {

			boolean isDisplayedAcceptedTypes = appCollectionsPg.isAcceptedTypeValuesDisplayed(acceptedTypeValues);

			if (isDisplayedAcceptedTypes) {
				report.updateTestLog("Verify Accpeted Types values in Edit properties",
						"All accepted types values displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Accpeted Types values in Edit properties",
						"Accepted types values is not displayed", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check on Create Button
	public void checkCreateButton() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, createLinkXpath)) {
				UIHelper.highlightElement(driver, createLinkXpath);
				UIHelper.click(driver, createLinkXpath);
				report.updateTestLog("Verify Create button displayed", "Create button displayed successfully",
						Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Create button displayed", "Failed to display Create button", Status.FAIL);
		}
	}

	// Verify collection relationship file path
	public void verifyCollectionRelationFilePath(String relationship, String filePath) {
		try {

			boolean isDisplayedFilePath = appCollectionsPg.isCollectionRelationshipPathDisplayed(relationship,
					filePath);

			if (isDisplayedFilePath) {
				report.updateTestLog("Verify Collection relationship filepath in details page",
						"Collection relationship file path displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Collection relationship filepath in details page",
						"Failed to display Collection relationship file path", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify file upload
	public void verifyBucketingRules(String fileName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String actualResult = "";
			ArrayList<String> uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(fileName)) {
					isDisplayedUploadedFile = true;
					actualResult = actualUploadedFileName;
					break;
				} else {
					isDisplayedUploadedFile = false;
					actualResult = "File Not Found";
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify bucketing rules",
						"Uploaded File: " + fileName + " was automatically bucketed (moved) per the 'Bucketing Rules'"
								+ "<br /><b>Expected Result:</b> " + fileName + ", <br /><b>Actual Result:</b> "
								+ actualResult + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify bucketing rules",
						"Uploaded File: " + fileName + " was failed to bucketed (moved) per the 'Bucketing Rules'"
								+ "<br /><b>Expected Result:</b> " + fileName + ", <br /><b>Actual Result:</b> "
								+ actualResult + "",
						Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
		}
	}

	// rename file upload

	String nameFieldXpath = ".//input[contains(@name,'prop_cm_name')]";
	String titleFieldXpath = ".//textarea[contains(@name,'prop_cm_title')]";

	public void verifyBucketingRulesAndRename(String fileName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String actualResult = "";
			String updatedName = "";
			ArrayList<String> uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			for (String actualUploadedFileName : uploadedFilesNameList) {
				UIHelper.waitForPageToLoad(driver);

				if (actualUploadedFileName.contains(fileName)) {
					actualResult = actualUploadedFileName;

					appCollectionsPg.clickOnMoreSetting(actualUploadedFileName);
					appCollectionsPg.commonMethodForClickOnMoreSettingsOption(actualUploadedFileName,
							"Edit Properties");
					UIHelper.waitForVisibilityOfEleByXpath(driver, nameFieldXpath);

					UIHelper.click(driver, nameFieldXpath);
					String name = UIHelper.findAnElementbyXpath(driver, nameFieldXpath).getAttribute("value");
					updatedName = name.replace('A', 'B');
					UIHelper.sendKeysToAnElementByXpath(driver, nameFieldXpath, updatedName);

					UIHelper.click(driver, titleFieldXpath);
					String title = "this is a Name changed asset";
					UIHelper.sendKeysToAnElementByXpath(driver, titleFieldXpath, title);

					appCollectionsPg.clickOnSaveBtn();

					report.updateTestLog("the file name " + actualResult, " has been changed to " + updatedName,
							Status.PASS);

					break;
				} else {
					actualResult = "File Not Found";
					report.updateTestLog("the file name change  " + actualResult,
							" has been failed to changed  " + updatedName, Status.PASS);

				}
			}
			uploadedFilesNameList.clear();
		} catch (Exception e) {
			report.updateTestLog("exception occured in ",
					" AlfrescoCollectionsPageTest > verifyBucketingRulesAndRename(String fileName) > line No.532 ",
					Status.PASS);
		}
	}

	// Verify file upload
	public void verifyBucketingRulesInNegativeCase(String fileName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			ArrayList<String> uploadedFilesNameList = appMyFilesPg.getUploadedFileOrFolderTitle();

			for (String actualUploadedFileName : uploadedFilesNameList) {
				if (actualUploadedFileName.contains(fileName)) {
					isDisplayedUploadedFile = true;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (!isDisplayedUploadedFile) {
				report.updateTestLog("Verify bucketing rules",
						"Uploaded File: " + fileName + " is not moved within the Asset folder (sub-folders)",
						Status.PASS);

			} else {
				report.updateTestLog("Verify bucketing rules",
						"Uploaded File: " + fileName + " is moved within the Asset folder (sub-folders)", Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
		}
	}

	// Get child reference values from excel sheet
	public ArrayList<String> getChildReferencesFromExcel(String collectionObjectType, String assetTypes) {
		ArrayList<String> childReferenceValuesFromExcel = new ArrayList<String>();
		try {
			if (assetTypes.contains(",")) {
				String splittedAssetTypes[] = assetTypes.split(",");
				if (splittedAssetTypes != null) {
					for (String assetType : splittedAssetTypes) {

						if (assetType.contains("-")) {
							String splittedAssetNameAndType[] = assetType.split("-");

							if (splittedAssetNameAndType != null && splittedAssetNameAndType.length > 1) {
								String assetTypeVal = splittedAssetNameAndType[0];
								String assetName = splittedAssetNameAndType[1];

								String firstChar = "" + assetName.charAt(0);
								String finalFirstCharOfUploadedFileName = firstChar.toLowerCase();

								String chilReferenceValue = "/" + collectionObjectType + "/" + assetTypeVal + "/"
										+ finalFirstCharOfUploadedFileName + "/" + assetName;
								childReferenceValuesFromExcel.add(chilReferenceValue);
							}
						}
					}
				}
			} else {

				if (assetTypes.contains("-")) {
					String splittedAssetNameAndType[] = assetTypes.split("-");

					if (splittedAssetNameAndType != null && splittedAssetNameAndType.length > 1) {
						String assetTypeVal = splittedAssetNameAndType[0];
						String assetName = splittedAssetNameAndType[1];

						String firstChar = "" + assetName.charAt(0);
						String finalFirstCharOfUploadedFileName = firstChar.toLowerCase();

						String chilReferenceValue = "/" + collectionObjectType + "/" + assetTypeVal + "/"
								+ finalFirstCharOfUploadedFileName + "/" + assetName;
						childReferenceValuesFromExcel.add(chilReferenceValue);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return childReferenceValuesFromExcel;
	}

	// Verify Child references in folder details Page
	public void verifyChildReferencesForUploadedFiles(String collectionObjectType, String assetTypes) {
		try {
			ArrayList<String> expectedChildReferencesFromExcel = getChildReferencesFromExcel(collectionObjectType,
					assetTypes);
			
			expectedChildReferencesFromExcel.add("/Courses/Course");
			ArrayList<String> actualChildReferencesList = appCollectionsPg.getChildreferencesFromDocDetailsPage();

			if (UIHelper.compareTwoDiffSizeOfLists(actualChildReferencesList, expectedChildReferencesFromExcel)) {
				report.updateTestLog(
						"Verify the 'Child Reference' association to the the uploaded file is present in the relationship viewer",
						"User able to see the 'Child References' for the uploaded files in Folder details Page"
								+ "<br /><b>Expected Result:</b> " + expectedChildReferencesFromExcel
								+ ", <br /><b>Actual Result:</b> " + actualChildReferencesList + "",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the 'Child Reference' association to the the uploaded file is present in the relationship viewer",
						"User not able to see the 'Child References' for the uploaded files in Folder details Page"
								+ "<br /><b>Expected Result:</b> " + expectedChildReferencesFromExcel
								+ ", <br /><b>Actual Result:</b> " + actualChildReferencesList + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param collectionObjectData
	 */
	public void verifyFieldValuesInAllPropPage(String collectionObjectData) {
		try {

			ArrayList<String> dataList = getrCollectionObjectData(collectionObjectData);

			boolean isDisplayedEntereFieldData = appCollectionsPg
					.checkCollectionObjectDataInEditPropPg(collectionObjectData);

			if (isDisplayedEntereFieldData) {
				report.updateTestLog("Verify field values in 'All Properties' Page",
						"field Values:" + dataList + " are displayed Successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify field values in 'All Properties' Page",
						"field Values:" + dataList + " are failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify field values in 'All Properties' Page",
					"Verify field values in 'All Properties' Page Failed", Status.FAIL);
		}
	}

	// Verify bucketing rule for asset type in Doc lib page
	public void verifyBucketingRulesForAssetType(String assetType) {
		try {
			if (assetType.contains("-")) {
				String splittedAssetNameAndType[] = assetType.split("-");

				if (splittedAssetNameAndType != null && splittedAssetNameAndType.length > 1) {
					String assetTypeVal = splittedAssetNameAndType[0];
					String assetName = splittedAssetNameAndType[1];

					if (assetName.contains(":")) {
						String splittedAssetNames[] = assetName.split(":");

						if (splittedAssetNames != null) {
							for (String assetNameVal : splittedAssetNames) {
								appCollectionsPg.clickOnAssetsSubFoldersFromTreeViewInDocLibPag("Asset", assetNameVal,
										assetTypeVal);

								verifyBucketingRules(assetNameVal);
							}
						}

					} else {
						appCollectionsPg.clickOnAssetsSubFoldersFromTreeViewInDocLibPag("Asset", assetName,
								assetTypeVal);

						verifyBucketingRules(assetName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify bucketing rule for asset type in Doc lib page and rename the same
	// to verify change in
	// folderlocation..............................................
	public void renameBucketedFiles(String assetType) {
		try {
			if (assetType.contains("-")) {
				String splittedAssetNameAndType[] = assetType.split("-");

				if (splittedAssetNameAndType != null && splittedAssetNameAndType.length > 1) {
					String assetTypeVal = splittedAssetNameAndType[0];
					String assetName = splittedAssetNameAndType[1];

					if (assetName.contains(":")) {
						String splittedAssetNames[] = assetName.split(":");

						if (splittedAssetNames != null) {
							for (String assetNameVal : splittedAssetNames) {
								UIHelper.waitForPageToLoad(driver);

								appCollectionsPg.clickOnAssetsSubFoldersFromTreeViewInDocLibPag("Asset", assetNameVal,
										assetTypeVal);

								UIHelper.waitForPageToLoad(driver);
								verifyBucketingRulesAndRename(assetNameVal);
							}
						}

					} else {
						UIHelper.waitForPageToLoad(driver);

						appCollectionsPg.clickOnAssetsSubFoldersFromTreeViewInDocLibPag("Asset", assetName,
								assetTypeVal);
						UIHelper.waitForPageToLoad(driver);

						verifyBucketingRulesAndRename(assetName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// .........................................

	// Verify bucketing rule for asset type in Doc lib page - Negative Case
	public void verifyBucketingRulesForAssetTypeInNegativeCase(String assetType) {
		try {
			if (assetType.contains("-")) {
				String splittedAssetNameAndType[] = assetType.split("-");

				if (splittedAssetNameAndType != null && splittedAssetNameAndType.length > 1) {
					String assetTypeVal = splittedAssetNameAndType[0];
					String assetName = splittedAssetNameAndType[1];

					if (assetName.contains(":")) {
						String splittedAssetNames[] = assetName.split(":");

						if (splittedAssetNames != null) {
							for (String assetNameVal : splittedAssetNames) {
								appCollectionsPg.clickOnAssetsSubFoldersFromTreeViewInDocLibPag("Asset", assetNameVal,
										assetTypeVal);

								verifyBucketingRulesInNegativeCase(assetNameVal);
							}
						}

					} else {
						appCollectionsPg.clickOnAssetsSubFoldersFromTreeViewInDocLibPag("Asset", assetName,
								assetTypeVal);

						verifyBucketingRulesInNegativeCase(assetName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the Asset Type based on File name
	public String getAssetTypeBasedOnFileName(String assetTypes, String fileName) {
		String expectedAssetTypeVal = "";
		try {
			if (assetTypes.contains(",")) {
				String splittedAssetTypes[] = assetTypes.split(",");
				if (splittedAssetTypes != null) {
					for (String assetType : splittedAssetTypes) {

						if (assetType.contains(fileName)) {
							expectedAssetTypeVal = subMethodForGetAssetTypeBasedOnGivenFile(assetType);
							break;
						}
					}
				}
			} else {
				expectedAssetTypeVal = subMethodForGetAssetTypeBasedOnGivenFile(assetTypes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return expectedAssetTypeVal;
	}

	// Sub method for get AssetTypeBased on given file
	public String subMethodForGetAssetTypeBasedOnGivenFile(String assetType) {
		String expectedAssetTypeVal = "";
		try {
			if (assetType.contains("-")) {
				String splittedAssetNameAndType[] = assetType.split("-");

				if (splittedAssetNameAndType != null && splittedAssetNameAndType.length > 1) {
					String assetTypeVal = splittedAssetNameAndType[0];
					expectedAssetTypeVal = assetTypeVal;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return expectedAssetTypeVal;
	}

	// Verify Relationships (Parent and contained by references) from
	// Collections UI Page
	public void verifyRelationshipsForUpldaedFilesInDetailsPage(String collectionObjectType, String assetTypes,
			String folderName, String fileName) {
		try {
			String expectedAssetTypeVal = getAssetTypeBasedOnFileName(assetTypes, fileName);
			String firstChar = "" + fileName.charAt(0);
			String finalFirstChar = firstChar.toLowerCase();

			String expectedParentReference = "/" + folderName;
			String expectedIsContainedBy = "/" + collectionObjectType + "/" + expectedAssetTypeVal + "/"
					+ finalFirstChar;

			String actualParentReference = appCollectionsPg.getParenReferenceInfoFromDetailsPage();
			String actualIsContainedBy = appCollectionsPg.getIsContainedByInfoFromDetailsPage();

			if (actualIsContainedBy.equalsIgnoreCase(expectedIsContainedBy)) {

				report.updateTestLog("Verify the 'Is Contained By' reference to a Bucket Folder in relationships",
						"User able to see the 'Is Contained By' for the uploaded files in Folder details Page"
								+ "<br /><b>Expected Result:</b> " + expectedIsContainedBy
								+ ", <br /><b>Actual Result:</b> " + actualIsContainedBy + "",
						Status.PASS);
			} else {

				report.updateTestLog("Verify the 'Is Contained By' reference to a Bucket Folder in relationships",
						"User not able to see the 'Is Contained By' for the uploaded files in Folder details Page"
								+ "<br /><b>Expected Result:</b> " + expectedIsContainedBy
								+ ", <br /><b>Actual Result:</b> " + actualIsContainedBy + "",
						Status.FAIL);
			}

			if (actualParentReference.equalsIgnoreCase(expectedParentReference)) {

				report.updateTestLog("Verify the 'Parent Reference' to a Bucket Folder in relationships",
						"User able to see the 'Parent Reference' for the uploaded files in Folder details Page"
								+ "<br /><b>Expected Result:</b> " + expectedParentReference
								+ ", <br /><b>Actual Result:</b> " + actualParentReference + "",
						Status.PASS);
			} else {

				report.updateTestLog("Verify the 'Parent Reference' reference to a Bucket Folder in relationships",
						"User not able to see the 'Parent Reference' for the uploaded files in Folder details Page"
								+ "<br /><b>Expected Result:</b> " + expectedParentReference
								+ ", <br /><b>Actual Result:</b> " + actualParentReference + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Create Menu Items
	public void verifyFolderNamesUnderCollectionObject(String createdObjectName, String objectType) {
		try {

			boolean isDisplayedFolderNameInLowerCaseCharacter = appCollectionsPg
					.checkCreatedObjectNamesFromTreeViewInDocLibPag(createdObjectName, objectType);

			if (isDisplayedFolderNameInLowerCaseCharacter) {
				report.updateTestLog(
						"Verify folder:" + createdObjectName
								+ " from within the object are written with lower case characters",
						createdObjectName + " is displayed with lower case character under collection object",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify folder:" + createdObjectName
								+ " from within the object are written with lower case characters",
						createdObjectName + " is failed to display with lower case character under collection object",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Relationships (Parent and contained by references) for uploaded
	// files in Composite Object from
	// Share UI Page
	public void verifyRelationshipsForUpldaedFilesOfCompObjInDetailsPageByShareUI(String collectionObjectType,
			String fileName) {
		try {
			String firstChar = "" + fileName.charAt(0);
			String finalFirstChar = firstChar.toLowerCase();

			String expectedIsContainedBy = "/" + collectionObjectType + "s/" + finalFirstChar + "/" + fileName;

			boolean isDisplayedParentReference = appCollectionsPg.checkParenReferenceInfoFromDetailsPage();
			String actualIsContainedBy = appCollectionsPg.getIsContainedByInfoFromDetailsPage();

			if (actualIsContainedBy.equalsIgnoreCase(expectedIsContainedBy)) {

				report.updateTestLog("Verify the 'Is Contained By' reference in relationships",
						"User able to see the 'Is Contained By' for the uploaded files in 'Document Details' Page"
								+ "<br /><b>Expected Result:</b> " + expectedIsContainedBy
								+ ", <br /><b>Actual Result:</b> " + actualIsContainedBy + "",
						Status.PASS);
			} else {

				report.updateTestLog("Verify the 'Is Contained By' reference in relationships",
						"User not able to see the 'Is Contained By' for the uploaded files in 'Document Details' Page"
								+ "<br /><b>Expected Result:</b> " + expectedIsContainedBy
								+ ", <br /><b>Actual Result:</b> " + actualIsContainedBy + "",
						Status.FAIL);
			}

			if (!isDisplayedParentReference) {

				report.updateTestLog("Verify the 'Parent Reference' in relationships",
						"Uploaded file not have the 'Parent Reference' in 'Document Details' Page", Status.PASS);
			} else {

				report.updateTestLog("Verify the 'Parent Reference' reference in relationships",
						"Uploaded file successfully have the 'Parent Reference' in 'Document Details' Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify collection relationship file path for indiviual object
	public void verifyContainedBy(String relationship, String ObjType, String ObjName) {
		try {
			String finalCollectionRelationFilePathXpath = collectionRelationFilePathXpath.replace("RELATIONSHIP",
					relationship);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, collectionDOMXpath);
			System.out.println(
					UIHelper.findAnElementbyXpath(driver, finalCollectionRelationFilePathXpath).getAttribute("title"));
			System.out.println("asfgasdgasg       /" + ObjType + "s/" + ObjName.charAt(0));
			if (UIHelper.findAnElementbyXpath(driver, finalCollectionRelationFilePathXpath).getAttribute("title")
					.equalsIgnoreCase("/" + ObjType + "s/" + ObjName.toLowerCase().charAt(0))
					&& !ObjType.equalsIgnoreCase("Asset")) {
				UIHelper.highlightElement(driver, finalCollectionRelationFilePathXpath);

				report.updateTestLog("Verify Collection Contained By in details page",
						"Collection Contained By file path displayed successfully" + "<br /><b>Expected Result:</b> "
								+ ObjType + "/" + ObjName.toLowerCase().charAt(0) + ", <br /><b>Actual Result:</b> "
								+ UIHelper.findAnElementbyXpath(driver, finalCollectionRelationFilePathXpath)
										.getAttribute("title"),
						Status.PASS);
			} else if (ObjType.equalsIgnoreCase("Asset")
					&& UIHelper.findAnElementbyXpath(driver, finalCollectionRelationFilePathXpath).getAttribute("title")
							.equalsIgnoreCase("/" + ObjType + "s/Other/" + ObjName.toLowerCase().charAt(0))) {
				UIHelper.highlightElement(driver, finalCollectionRelationFilePathXpath);
				report.updateTestLog("Verify Collection Contained By in details page",
						"Collection Contained By file path displayed successfully" + "<br /><b>Expected Result:</b> "
								+ "/" + ObjType + "s/Other/" + ObjName.toLowerCase().charAt(0)
								+ ", <br /><b>Actual Result:</b> "
								+ UIHelper.findAnElementbyXpath(driver, finalCollectionRelationFilePathXpath)
										.getAttribute("title"),
						Status.PASS);

			} else {
				report.updateTestLog("Verify Collection Contained By in details page",
						"Failed to display Collection relationship file path", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Collection Contained By in details page",
					"Failed to display Collection relationship file path", Status.FAIL);
		}
	}

	// Verify collection relationship file path in file preview
	public void verifyCollectionRelationFilePathInPreview(String relationship, String filePath) {
		try {

			boolean isDisplayedFilePath = appCollectionsPg.isCollectionRelationshipPathDisplayed(relationship,
					filePath);

			if (isDisplayedFilePath) {
				report.updateTestLog("Verify Collection relationship filepath in details page",
						"Collection relationship file path displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Collection relationship filepath in details page",
						"Failed to display Collection relationship file path", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Collection object child references in view details page
	public void verifyCollectionObjchildreferencesInViewDetailsPg(String expChildRefValues) {
		boolean isDisplayedChildRefVal = false;
		try {
			ArrayList<String> actualChildRefValuesList = appCollectionsPg.getChildRefFromCollObjVieDetailsPg();

			if (expChildRefValues.contains(",")) {
				String splittedChildRefVals[] = expChildRefValues.split(",");

				for (String expectChildRefVal : splittedChildRefVals) {
					for (String actualChildRefVal : actualChildRefValuesList) {
						if (actualChildRefVal.contains(expectChildRefVal)) {
							isDisplayedChildRefVal = true;
							break;
						} else {
							isDisplayedChildRefVal = false;
						}
					}
				}
			} else {
				for (String actualChildRefVal : actualChildRefValuesList) {
					if (actualChildRefVal.contains(expChildRefValues)) {
						isDisplayedChildRefVal = true;
						break;
					} else {
						isDisplayedChildRefVal = false;
					}
				}
			}

			if (isDisplayedChildRefVal) {
				report.updateTestLog("Verify 'Child References' in view details page",
						"Child References:" + expChildRefValues + "displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify 'Child References' in view details page",
						"Child References:" + expChildRefValues + " are failed to display", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To verify file/Folder Located or not
	public void verifyFile(String fileName, String performedAction) {
		try {
			ArrayList<String> uploadedFilesNameList = appCollectionsPg.getUploadedFileOrFolderTitle();
			String actualName = "File Not Found";
			for (String actualFileName : uploadedFilesNameList) {
				if (actualFileName.contains(fileName)) {
					isDisplayedUploadedFile = true;
					actualName = actualFileName;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Verify "+performedAction+" file",
						performedAction+" File: " + fileName + " is displayed successfully<br/><b>Expected Result:</b>" + fileName
								+ "<br/><b>Actual Result:</b>" + actualName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify "+performedAction+" file",
						performedAction+" File: " + fileName
								+ " is failed to display<br/><b>Expected Result:</b>"
								+ fileName + "<br/><b>Actual Result:</b>" + actualName,
						Status.FAIL);
			}

			uploadedFilesNameList.clear();
			isDisplayedUploadedFile = false;
		} catch (Exception e) {
			report.updateTestLog("Find File", e.getMessage(), Status.FAIL);
		}
	}
}
