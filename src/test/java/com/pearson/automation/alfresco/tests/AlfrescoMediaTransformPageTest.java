package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;

import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.framework.Status;

public class AlfrescoMediaTransformPageTest extends ReusableLibrary {

	AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);

	public AlfrescoMediaTransformPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Check Transformed Profile
	public void verifyTransformedFile(String preTxt, String fileName) {
		try {
			if (mediaTransPage.isTransferredFileIsAvailable(preTxt)) {
				report.updateTestLog("Verify Transformed File ", "Transformed File available", Status.PASS);

			}

			else {
				report.updateTestLog("Verify Transformed File ", "Transformed File is not available", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Profile in Media Transformation Profile Page
	public void verifyMediaTransformationProfileFile(String profileName) {
		try {
			if (mediaTransPage.checkProfileNameInMeadiTransProfilePage(profileName)) {
				report.updateTestLog("Verify Profile Name",
						"Profile: " + profileName + " is displayed successfully in 'Media Transformation Profile' Page",
						Status.PASS);

			}

			else {
				report.updateTestLog("Verify Profile Name",
						"Profile: " + profileName + " is failed to display in 'Media Transformation Profile' Page",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Profile in Media Transformation Profile Page
	public void verifyImagePreview() {
		try {
			if (mediaTransPage.checkImagePerview()) {
				report.updateTestLog("Verify Previewer Image ", "Image previewed sucessfully", Status.PASS);

			}

			else {
				report.updateTestLog("Verify Previewer Image ", "Image preview Failed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check create profile buttons on the top of created profile list
	public void verifyCreateProfileButton(String buttonLabelName) {
		try {
			if (mediaTransPage.checkCreateProfileButton(buttonLabelName)) {
				report.updateTestLog("Verify 'Create Profile Button'",
						"User able to view the 'Create Profile Button: " + buttonLabelName
								+ "' on the top of created profile list in 'Media Transformation Profile' Page",
						Status.PASS);

			} else {
				report.updateTestLog("Verify 'Create Profile Button'",
						"User unable to view the 'Create Profile Button: " + buttonLabelName
								+ "' on the top of created profile list in 'Media Transformation Profile' Page",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check added field values in profile summary section
	public void verifyAddedFieldValueInProfileSummary(String profileFieldName, String profileFieldValue) {
		try {
			if (mediaTransPage.getTextFromProfileSummary(profileFieldName).contains(profileFieldValue)
					|| profileFieldValue.contains(mediaTransPage.getTextFromProfileSummary(profileFieldName))) {
				report.updateTestLog(
						"Verify added profile data in 'Transformation Profile Summary'", profileFieldName + " value:"
								+ profileFieldValue + " displayed successfully in 'Transformation Profile Summary'",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify added profile data in 'Transformation Profile Summary'", profileFieldName + " value:"
								+ profileFieldValue + " failed to display in 'Transformation Profile Summary'",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check added field values in profile summary section
	public void verifyAddedCropFieldValueInProfileSummary(String type, String height, String width) {
		try {
			boolean isDisplayedCropFieldsInProfileSummary = false;
			ArrayList<String> profileSummaryValList = mediaTransPage.getListFromProfileSummary("Crop");

			for (String actualCropVal : profileSummaryValList) {
				if (actualCropVal.contains(type) || actualCropVal.contains(height) || actualCropVal.contains(width)) {
					isDisplayedCropFieldsInProfileSummary = true;
					break;
				} else {
					isDisplayedCropFieldsInProfileSummary = false;
				}
			}

			if (isDisplayedCropFieldsInProfileSummary) {
				report.updateTestLog(
						"Verify added profile data in 'Transformation Profile Summary'", "Added Crop Field values:"
								+ profileSummaryValList + " displayed successfully in 'Transformation Profile Summary'",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify added profile data in 'Transformation Profile Summary'", "Added Crop Field values:"
								+ profileSummaryValList + " failed to display in 'Transformation Profile Summary'",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check added field values in profile summary section
	public void verifyAddedAbsoluteResizeFieldValuesInProfileSummary(String height, String width) {
		try {
			boolean isDisplayedCropFieldsInProfileSummary = false;
			ArrayList<String> profileSummaryValList = mediaTransPage.getListFromProfileSummary("Resize");

			for (String actualCropVal : profileSummaryValList) {
				if (actualCropVal.contains(height) || actualCropVal.contains(width)) {
					isDisplayedCropFieldsInProfileSummary = true;
					break;
				} else {
					isDisplayedCropFieldsInProfileSummary = false;
				}
			}

			if (isDisplayedCropFieldsInProfileSummary) {
				report.updateTestLog("Verify added profile data in 'Transformation Profile Summary'",
						"Added Absolute Resize Field values:" + profileSummaryValList
								+ " displayed successfully in 'Transformation Profile Summary'",
						Status.PASS);

			} else {
				report.updateTestLog("Verify added profile data in 'Transformation Profile Summary'",
						"Added Absolute Resize  Field values:" + profileSummaryValList
								+ " failed to display in 'Transformation Profile Summary'",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check removed field values in profile summary section
	public void verifyRemovedFieldValueInProfileSummary(String profileFieldName) {
		try {
			if (!mediaTransPage.checkProfileSummaryFieldVal(profileFieldName)) {
				report.updateTestLog("Verify removed profile data in 'Transformation Profile Summary'",
						profileFieldName + " removed successfully from 'Transformation Profile Summary'", Status.PASS);

			} else {
				report.updateTestLog("Verify removed profile data in 'Transformation Profile Summary'",
						"'" + profileFieldName + "' displayed successfully in 'Transformation Profile Summary'",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check removed rotate field value in profile summary section
	public void verifyRemovedRotateFieldValueInProfileSummary(String profileFieldName) {
		try {
			if (mediaTransPage.checkProfileSummaryFieldVal(profileFieldName)) {
				report.updateTestLog("Verify removed profile data in 'Transformation Profile Summary'",
						profileFieldName + " removed successfully from 'Transformation Profile Summary'", Status.PASS);

			} else {
				report.updateTestLog("Verify removed profile data in 'Transformation Profile Summary'",
						"'" + profileFieldName + "' displayed successfully in 'Transformation Profile Summary'",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add image profiel data and verify in Transformation Profile Summary
	// container
	// Remove image profile data and verify in Transformation Profile Summary
	// container
	public void addAndRemoveTransformationProfilePageDataForImage(String transformationProfWizardValues) {
		try {
			if (transformationProfWizardValues.contains(";")) {
				String splittedTransformationProfWizardValues[] = transformationProfWizardValues.split(";");

				if (splittedTransformationProfWizardValues != null) {
					for (String containerDetails : splittedTransformationProfWizardValues) {
						if (containerDetails.contains("-")) {
							String splitForContainer[] = containerDetails.split("-");

							ArrayList<String> fieldValuesList = new ArrayList<String>();
							if (splitForContainer != null && splitForContainer.length > 1) {
								if (splitForContainer[1].contains(",")) {
									String splitFields[] = splitForContainer[1].split(",");

									if (splitFields != null) {
										for (String fieldPlusValue : splitFields) {
											if (fieldPlusValue.contains(":")) {
												String splitFieldValues[] = fieldPlusValue.split(":");
												fieldValuesList.add(splitFieldValues[0]);
												fieldValuesList.add(splitFieldValues[1]);
											}
										}
									}
								} else {
									if (splitForContainer[1].contains(":")) {
										String splitFieldValues[] = splitForContainer[1].split(":");
										fieldValuesList.add(splitFieldValues[0]);
										fieldValuesList.add(splitFieldValues[1]);
									}
								}
							}

							subMethodForAddandRemoveProfileData(splitForContainer[0], fieldValuesList);

							fieldValuesList.clear();
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Sub Method for Add image profiel data and verify in Transformation
	// Profile Summary container &
	// Remove image profile data and verify in Transformation Profile Summary
	// container
	public void subMethodForAddandRemoveProfileData(String transProfContainerName, ArrayList<String> fieldValuesList) {
		try {
			if (transProfContainerName.equalsIgnoreCase("Resize")) {

				if (fieldValuesList.get(0).contains("Percent")) {
					mediaTransPage.addImgResizeByPercent(fieldValuesList.get(1));
					verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				} else {
					mediaTransPage.addImgResizeBySize(fieldValuesList.get(1), fieldValuesList.get(3));
					verifyAddedAbsoluteResizeFieldValuesInProfileSummary(fieldValuesList.get(1),
							fieldValuesList.get(3));
				}

				mediaTransPage.clickOnRemoveBtnInResizeContainer();

				verifyRemovedFieldValueInProfileSummary(fieldValuesList.get(0));

				/*
				 * mediaTransPage.addImgResizeByPercent(fieldValuesList.get(1));
				 * verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0),
				 * fieldValuesList.get(1));
				 * mediaTransPage.clickOnRemoveBtnInResizeContainer();
				 */

				verifyRemovedFieldValueInProfileSummary(fieldValuesList.get(0));
			} else if (transProfContainerName.equalsIgnoreCase("Rotate")) {
				mediaTransPage.addRotateAngleForImg(fieldValuesList.get(1));

				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				mediaTransPage.clickOnRemoveBtnInRotateContainer();

				verifyRemovedRotateFieldValueInProfileSummary(fieldValuesList.get(0));
			} else if (transProfContainerName.equalsIgnoreCase("Crop")) {
				mediaTransPage.addImgCropDetails(fieldValuesList.get(1), fieldValuesList.get(3),
						fieldValuesList.get(5));

				verifyAddedCropFieldValueInProfileSummary(fieldValuesList.get(1), fieldValuesList.get(3),
						fieldValuesList.get(5));
				mediaTransPage.clickOnRemoveBtnInCropContainer();

				verifyRemovedFieldValueInProfileSummary(transProfContainerName);
			} else if (transProfContainerName.equalsIgnoreCase("Color")) {
				mediaTransPage.addColorForImg(fieldValuesList.get(1));
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				mediaTransPage.clickOnRemoveBtnInColorContainer();

				verifyRemovedFieldValueInProfileSummary(fieldValuesList.get(0));
			} else if (transProfContainerName.equalsIgnoreCase("ColorMode")) {
				mediaTransPage.addColorModeForImg(fieldValuesList.get(1));
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				mediaTransPage.clickOnRemoveBtnInColorModeContainer();

				verifyRemovedFieldValueInProfileSummary(fieldValuesList.get(0));
			} else if (transProfContainerName.equalsIgnoreCase("Grayscale")) {
				mediaTransPage.addGrayscaleForImg();
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				mediaTransPage.clickOnRemoveBtnInGrayscaleContainer();

				verifyRemovedFieldValueInProfileSummary(fieldValuesList.get(0));
			} else if (transProfContainerName.equalsIgnoreCase("WaterMark")) {
				mediaTransPage.addWaterMarkForImg();
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				mediaTransPage.clickOnRemoveBtnInWaterMarkContainer();

				verifyRemovedFieldValueInProfileSummary(fieldValuesList.get(0));
			} else if (transProfContainerName.equalsIgnoreCase("Flip")) {
				mediaTransPage.addFlipForImg(fieldValuesList.get(1));
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				mediaTransPage.clickOnRemoveBtnInFlipContainer();

				verifyRemovedFieldValueInProfileSummary(fieldValuesList.get(0));
			} else if (transProfContainerName.equalsIgnoreCase("Compression")) {
				mediaTransPage.addCompressionForImg();
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				mediaTransPage.clickOnRemoveBtnInCompressionContainer();

				verifyRemovedFieldValueInProfileSummary(fieldValuesList.get(0));
			} else if (transProfContainerName.equalsIgnoreCase("Resolution")) {
				mediaTransPage.addResoultionForImg(fieldValuesList.get(1));

				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				mediaTransPage.clickOnRemoveBtnInResoultionContainer();

				verifyRemovedFieldValueInProfileSummary(fieldValuesList.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add image profiel data and verify in Transformation Profile Summary
	// container
	public void addTransformationProfilePageDataForImage(String transformationProfWizardValues) {
		try {
			if (transformationProfWizardValues.contains(";")) {
				String splittedTransformationProfWizardValues[] = transformationProfWizardValues.split(";");

				if (splittedTransformationProfWizardValues != null) {
					for (String containerDetails : splittedTransformationProfWizardValues) {
						if (containerDetails.contains("-")) {
							String splitForContainer[] = containerDetails.split("-");

							ArrayList<String> fieldValuesList = new ArrayList<String>();
							if (splitForContainer != null && splitForContainer.length > 1) {
								if (splitForContainer[1].contains(",")) {
									String splitFields[] = splitForContainer[1].split(",");

									if (splitFields != null) {
										for (String fieldPlusValue : splitFields) {
											if (fieldPlusValue.contains(":")) {
												String splitFieldValues[] = fieldPlusValue.split(":");
												fieldValuesList.add(splitFieldValues[0]);
												fieldValuesList.add(splitFieldValues[1]);
											}
										}
									}
								} else {
									if (splitForContainer[1].contains(":")) {
										String splitFieldValues[] = splitForContainer[1].split(":");
										fieldValuesList.add(splitFieldValues[0]);
										fieldValuesList.add(splitFieldValues[1]);
									}
								}
							}

							subMethodForAddandRemoveProfileData(splitForContainer[0], fieldValuesList);

							fieldValuesList.clear();
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Sub Method for Add image profile data and verify in Transformation
	// Profile Summary container &
	public void subMethodForAddProfileData(String transProfContainerName, ArrayList<String> fieldValuesList) {
		try {
			if (transProfContainerName.equalsIgnoreCase("Resize")) {
				if (fieldValuesList.get(0).contains("Percent")) {
					mediaTransPage.addImgResizeByPercent(fieldValuesList.get(1));
					verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
				} else {
					mediaTransPage.addImgResizeBySize(fieldValuesList.get(1), fieldValuesList.get(3));
					verifyAddedAbsoluteResizeFieldValuesInProfileSummary(fieldValuesList.get(1),
							fieldValuesList.get(3));
				}

			} else if (transProfContainerName.equalsIgnoreCase("Rotate")) {
				mediaTransPage.addRotateAngleForImg(fieldValuesList.get(1));
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
			} else if (transProfContainerName.equalsIgnoreCase("Crop")) {
				mediaTransPage.addImgCropDetails(fieldValuesList.get(1), fieldValuesList.get(3),
						fieldValuesList.get(5));
				verifyAddedCropFieldValueInProfileSummary(fieldValuesList.get(1), fieldValuesList.get(3),
						fieldValuesList.get(5));
			} else if (transProfContainerName.equalsIgnoreCase("Color")) {
				mediaTransPage.addColorForImg(fieldValuesList.get(1));
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
			} else if (transProfContainerName.equalsIgnoreCase("ColorMode")) {
				mediaTransPage.addColorModeForImg(fieldValuesList.get(1));
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
			} else if (transProfContainerName.equalsIgnoreCase("Grayscale")) {
				mediaTransPage.addGrayscaleForImg();
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
			} else if (transProfContainerName.equalsIgnoreCase("WaterMark")) {
				mediaTransPage.addWaterMarkForImg();
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
			} else if (transProfContainerName.equalsIgnoreCase("Flip")) {
				mediaTransPage.addFlipForImg(fieldValuesList.get(1));
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
			} else if (transProfContainerName.equalsIgnoreCase("Compression")) {
				mediaTransPage.addCompressionForImg();
				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
			} else if (transProfContainerName.equalsIgnoreCase("Resolution")) {
				mediaTransPage.addResoultionForImg(fieldValuesList.get(1));

				verifyAddedFieldValueInProfileSummary(fieldValuesList.get(0), fieldValuesList.get(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Preview button in Media Transformation Profile Page
	public void verifyPreviewBtn() {
		try {
			if (mediaTransPage.checkPreviewBtn()) {
				report.updateTestLog("Verify Preview button", "Preview button displayed sucessfully", Status.PASS);

			}

			else {
				report.updateTestLog("Verify Previewer Image ", "Preview button failed to display", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Media watch folders for applied transform profiles
	public void verifyMediaWatchFolders(String folderName) {
		try {
			if (mediaTransPage.checkMediaWatchFolderInDocLibPg(folderName)) {
				report.updateTestLog("Verify watch folder feature",
						"Watch folder feature applied and watch folder icon displayed sucessfully for " + folderName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify watch folder feature",
						"Watch folder feature failed to applied for " + folderName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
