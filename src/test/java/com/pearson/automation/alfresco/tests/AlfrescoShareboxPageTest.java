package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoShareboxPageTest extends ReusableLibrary {

	private String inCompleteSharingBannerXath = ".//*[contains(@title,'Incomplete external sharing settings')]";
	private String tempXpathForSharedFileDownloadOpt = ".//*[@class='caption']//span[contains(@alt,'CRAFT')]//ancestor::h3//following-sibling::p/a[contains(.,'Download')]";
	private String warningMsgXpath = ".//*[@id='prompt']//*[@class='bd']";
	private String promptLayout = ".//div[@id='prompt']";
	private String emailSuggestionXpath = "//ul[@class='select2-results']/li";
	private String emailInputXpath = "//ul/li/input[@class='select2-input']";
	private String foldernameInShareUI = ".//span[@class='current-folder-container']/h3";
	private String openfolderInShareUI = ".//h3/span[text()='CRAFT']//ancestor::div[@class='caption']//a/span[text()='Open']";
	private String navigationErrorPageXpath = "//div[@class='alert alert-danger']/span";
	private String selectuploadLabelXpath = "//*[@class='bs-component']//div[5]//div[1]//p//span";
	private String selectFilesButtonXpath = "//span[contains(@class,'file-input-content')]//descendant::span[2]";
	private String downloadCurrentFolderXpath = "*//span[contains(@class,'cloud-download')]//parent::h1//parent::div//descendant::div//p//span";
	private String downloadButtonLabelXpath = "*//span[contains(@class,'cloud-download')]//parent::h1//span[2]";
	private String downloadZipButtonXpath = "*//span[contains(@class,'cloud-download')]//parent::h1//parent::div//div//button";
	private String footerTextXpath = "//div[@class='container sharebox-footer']//span";
	private String fileNotAvailableXpath = "//div[@class='well-lg']//span";

	private String uploadButtonLabelXpath = "//span[contains(@class,'cloud-upload')]//parent::h1//parent::div//div//button";
	private String dropFilesLabelXpath = "*//span[contains(@class,'gallery-dropzone')]//span";
	private String viewTypeLabelXpath = "//div[contains(@class,'pull-right')]//button//span[2]";
	private String maxfilesLabelXpath = "//div[@class='alert alert-info']//span";
	private String filemodifyDetails = "//div[@class='caption']//em//span[1]";

	public static ArrayList<String> labelItems = new ArrayList<String>();

	private AlfrescoShareboxPage appShareBoxPg = new AlfrescoShareboxPage(scriptHelper);

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoShareboxPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Verify Sharing properties field values
	// Get all Form Fields Text
	public boolean checkSharingPropertyValues(String expectedProperties, String expectedMessageFieldVal) {
		boolean isDisplayedSharingFieldValues = false;
		try {
			ArrayList<String> sharingPropFieldValList = appShareBoxPg.getSharingPropertyValues();

			String splittedFields[] = expectedProperties.split(";");

			if (splittedFields != null) {
				for (String value : splittedFields) {
					String splitFiledValue[] = value.split(":");

					if (splitFiledValue != null) {

						if (!splitFiledValue[0].equalsIgnoreCase("Sharing expires")) {

							for (String actualSharePropVal : sharingPropFieldValList) {
								if (splitFiledValue[1].equalsIgnoreCase(actualSharePropVal)) {
									isDisplayedSharingFieldValues = true;
									break;
								} else {
									isDisplayedSharingFieldValues = false;
								}
							}

						}

					}

				}
			}

			for (String actualSharePropVal : sharingPropFieldValList) {
				if (expectedMessageFieldVal.equalsIgnoreCase(actualSharePropVal)) {
					isDisplayedSharingFieldValues = true;
					break;
				} else {
					isDisplayedSharingFieldValues = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedSharingFieldValues;
	}

	public void checkEditedSharingPropFieldsWhenClickOnCancel(String expectedProperties,
			String expectedMessageFieldVal) {
		try {
			if (!checkSharingPropertyValues(expectedProperties, expectedMessageFieldVal)) {
				report.updateTestLog("Verify edited Sharing Properties when click on 'Cancel' button",
						"User not able to save the edited values", Status.PASS);
			} else {
				report.updateTestLog("Verify edited Sharing Properties when click on 'Cancel' button",
						"User able to save the edited values", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check invalid due date error message for workflow
	public void checkFieldErrorMsgInSharingPropWindow(String expectedFieldErroVal) {
		try {
			String actualErrorMessageValue = appShareBoxPg.getTextFromErrorMessagePopup();

			if (expectedFieldErroVal.equalsIgnoreCase(actualErrorMessageValue)) {
				report.updateTestLog("Verify the Error Message in 'Sharebox Folder' window",
						"<b>Field error message:</b>" + expectedFieldErroVal
								+ " displayed Successfully when user without enter any data in mandatory fields and click on 'Save' button",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Error Message in 'Sharebox Folder' window",
						"<b>Field error message:</b>" + expectedFieldErroVal
								+ " is failed to display when user without enter any data in mandatory fields and click on 'Save' button",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check user name which is displayed for the corresponding file in the
	// Uploaded by Column.
	public void checkUserNameInUploadedBy(String expectedUserName) {
		try {
			String actualUserName = appShareBoxPg.getUploadedByValue();

			if (expectedUserName.equalsIgnoreCase(actualUserName)) {
				report.updateTestLog(
						"Verify the user name which is displayed for the corresponding file in the Uploaded by Column.",
						"External User email id:" + expectedUserName
								+ " is displayed as the file is uploaded by the External User",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the user name which is displayed for the corresponding file in the Uploaded by Column.",
						"External User email id:" + expectedUserName
								+ " is failed to display as the file is uploaded by the External User",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify external Upload By
	public void verifyExternalUploadByData(String fileName, String mailID) {
		try {

			if (appShareBoxPg.checkExternalUploadByData(fileName, mailID)) {

				report.updateTestLog(
						"Verify the same is getting reflected in alfresco for the file uploaded by external user",
						"Internal User able to see the External User email id for the corresponding file as 'External Upload by: "
								+ mailID + " minutes ago",
						Status.PASS);

			} else {

				report.updateTestLog(
						"Verify the same is getting reflected in alfresco for the file uploaded by external user",
						"Internal User not able to see the External User email id for the corresponding file as 'External Upload by: "
								+ mailID + " minutes ago",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyincompleteBannerisDisplayed(String folderName) {
		boolean flag = false;

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, inCompleteSharingBannerXath);
			if (UIHelper.checkForAnElementbyXpath(driver, inCompleteSharingBannerXath)) {
				UIHelper.highlightElement(driver, inCompleteSharingBannerXath);
				report.updateTestLog("Verify Incomplete External Sharing Banner Is Displayed",
						"Incomplete External Sharing Banner displayed successfully", Status.PASS);
				flag = true;
			} else {
				report.updateTestLog("Verify Incomplete External Sharing Banner Is Displayed",
						"Incomplete External Sharing Banner is not displayed ", Status.FAIL);
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean verifyDownloadBtnisDisplayed(String fileName) {
		boolean flag = false;

		try {
			String finalXpathForSharedFileDownloadOpt = tempXpathForSharedFileDownloadOpt.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSharedFileDownloadOpt);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSharedFileDownloadOpt)) {
				UIHelper.highlightElement(driver, finalXpathForSharedFileDownloadOpt);
				report.updateTestLog("Verify Download Btn Is Displayed for File",
						"Download Btn for File displayed successfully", Status.PASS);
				flag = true;
			} else {
				report.updateTestLog("Verify Download Btn Is Displayed for File", "Download Btn for File NOT displayed",
						Status.FAIL);
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	// Verify mail in French language
	public void verifyRecievedShareboxMailSubInFrench() {
		try {

			if (appShareBoxPg.checkShareboxMailSubInFrench()) {

				report.updateTestLog("Verify recieved sharebox mail", "Sharebox mail recieved in French language",
						Status.PASS);

			} else {
				report.updateTestLog("Verify recieved sharebox mail", "Sharebox mail not recieved in French language",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify mail in French language
	public void verifyRecievedShareboxMailSubInFrench(String inputText) {
		try {

			if (appShareBoxPg.checkShareboxMailSubInFrench(inputText)) {

				report.updateTestLog("Verify recieved sharebox mail", "Sharebox mail recieved in French language",
						Status.PASS);

			} else {
				report.updateTestLog("Verify recieved sharebox mail", "Sharebox mail not recieved in French language",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify recieved sharebox mail", "Sharebox mail not recieved in French language",
					Status.FAIL);
		}
	}

	public void verifyErrorValidationOnDeleteList() {

		String expectedErrorMessage = dataTable.getData("Sharebox", "Message");
		try {
			appShareBoxPg.deletelist();
			UIHelper.waitForVisibilityOfEleByXpath(driver, promptLayout);
			// UIHelper.highlightElement(driver, promptLayout);
			String message = UIHelper.getTextFromWebElement(driver, warningMsgXpath);
			System.out.println();

			if (message.equalsIgnoreCase(expectedErrorMessage)) {
				report.updateTestLog("Verify validation error msg on mail DeleteList",
						"Validation of error msg successfull", Status.PASS);
			} else {
				report.updateTestLog("Verify validation error msg on mail DeleteList",
						"Validation of error msg Unsuccessfull", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify validation error msg on mail DeleteList", "Validation of error msg failed",
					Status.FAIL);
		}
	}

	public void verifySuggestionNotListed(String value) {
		String assertValue = dataTable.getData("Sharebox", "EditMessage");

		if (value == "") {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, emailInputXpath);
				UIHelper.click(driver, emailInputXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, emailSuggestionXpath);
				String text = UIHelper.getTextFromWebElement(driver, emailSuggestionXpath);
				if (text.equalsIgnoreCase(assertValue)) {
					report.updateTestLog("Verify user can't view the suggestion",
							"Suggestion not listed without entering value", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Verify user can't view the suggestion",
						"Suggestion listed without entering value", Status.FAIL);
			}
		} else {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, emailInputXpath);
				UIHelper.sendKeysToAnElementByXpath(driver, emailInputXpath, value);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, emailSuggestionXpath);
				String text = UIHelper.getTextFromWebElement(driver, emailSuggestionXpath);
				if (text.equalsIgnoreCase(assertValue)) {
					report.updateTestLog("Verify user can't view the suggestion",
							"Suggestion not listed with entering value", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Verify user can't view the suggestion", "Suggestion listed with entering value",
						Status.FAIL);
			}

		}
	}

	public String getCurrentFoldernameInSharebox() {
		String folderName = null;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, foldernameInShareUI);
			UIHelper.highlightElement(driver, foldernameInShareUI);
			folderName = UIHelper.getTextFromWebElement(driver, foldernameInShareUI);

			report.updateTestLog("Current Folder Name in Sharebox ", "Current is Folder name is [" + folderName + "] ",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Current Folder Name in Sharebox ", "Get Current FolderName Unsuccessfull ",
					Status.FAIL);
		}

		return folderName;
	}

	// Verify the error page while navigating from folder inside sharebox

	public void verifyFolderNavigationError(String folderName) {

		String actualError = null;
		String expectedError = dataTable.getData("Sharebox", "ErrorMessageValue");

		try {
			UIHelper.waitForPageToLoad(driver);
			String finalfolderopen = openfolderInShareUI.replace("CRAFT", folderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfolderopen);
			UIHelper.highlightElement(driver, finalfolderopen);
			UIHelper.click(driver, finalfolderopen);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, navigationErrorPageXpath);
			UIHelper.highlightElement(driver, navigationErrorPageXpath);
			actualError = UIHelper.getTextFromWebElement(driver, navigationErrorPageXpath);
			if (actualError.contains(expectedError)) {

				report.updateTestLog("Verify Folder Navigation Error ", "Error validation Successfull ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Folder Navigation Error ", "Error validation Unsuccessfull ", Status.FAIL);
		}

	}

	public void verifyFieldLabelTextInShareboxPopup(String fieldname) {

		try {

			UIHelper.waitFor(driver);

			if (fieldname.contains(";")) {
				String splittedNames[] = fieldname.split(";");

				for (String field : splittedNames) {

					if (appShareBoxPg.checkFieldLabelInShareboxPopupWithSemicolonDelimiter(field)) {

						report.updateTestLog("Verify field name", "Field name: " + field + " is present", Status.PASS);

					} else {
						report.updateTestLog("Verify field name", "Field name: " + field + " is not present",
								Status.FAIL);
					}

				}
			} else {

				if (appShareBoxPg.checkFieldLabelInShareboxPopupWithSemicolonDelimiter(fieldname)) {

					report.updateTestLog("Verify field name", "Field name: " + fieldname + " is present", Status.PASS);

				} else {
					report.updateTestLog("Verify field name", "Field name: " + fieldname + " is not present",
							Status.FAIL);
				}
			}

		} catch (Exception e) {
			report.updateTestLog("Verify field name", "Field name" + fieldname + " is not present", Status.FAIL);
		}
	}

	public void verifyHelpTextInShareboxPopup(String fieldname) {

		try {

			UIHelper.waitFor(driver);

			if (fieldname.contains(";")) {
				String splittedNames[] = fieldname.split(";");

				for (String field : splittedNames) {

					if (field.contains("||")) {

						String labelHelpNames[] = field.split("\\|\\|");

						String labelName = labelHelpNames[0];
						String helpTextName = labelHelpNames[1];

						if (appShareBoxPg.checkHelpTextInShareboxPopup(labelName, helpTextName)) {

							report.updateTestLog("Verify Help text",
									"Label name: " + labelName + "Help Text: " + helpTextName + "is present",
									Status.PASS);

						} else {
							report.updateTestLog("Verify Help text",
									"Label name: " + labelName + "Help Text: " + helpTextName + "is not present",
									Status.FAIL);
						}

					}

				}
			} else {

				if (fieldname.contains("||")) {

					String labelHelpNames[] = fieldname.split("\\|\\|");

					String labelName = labelHelpNames[0];
					String helpTextName = labelHelpNames[1];

					if (appShareBoxPg.checkHelpTextInShareboxPopup(labelName, helpTextName)) {

						report.updateTestLog("Verify Help text",
								"Label name: " + labelName + "Help Text: " + helpTextName + "is present", Status.PASS);

					} else {
						report.updateTestLog("Verify Help text",
								"Label name: " + labelName + "Help Text: " + helpTextName + "is not present",
								Status.FAIL);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyNotificationOptions(String notificationOption) {

		ArrayList<String> notficationOptions = appShareBoxPg.getNotificationDropdownValues(notificationOption);
		boolean flag = false;

		try {

			if (notificationOption.contains(",")) {

				String notificationString = "";
				String notificationSplit[] = notificationOption.split(",");

				// for (String notificationString : notificationSplit) {

				for (int i = 0; i < notificationSplit.length; i++) {
					notificationString = notificationSplit[i];
					for (String option : notficationOptions) {
						if (option.equalsIgnoreCase(notificationString)) {
							report.updateTestLog("Verify Notification dropdown option",
									"Notification option: " + notificationString + " is present", Status.PASS);
							flag = true;

							break;
						}
					}
				}

				if (flag == false) {
					report.updateTestLog("Verify Notification dropdown option",
							"Notification option: " + notificationString + " is not present", Status.PASS);
				}

			} else {
				for (String option : notficationOptions) {
					if (option.equalsIgnoreCase(notificationOption)) {
						report.updateTestLog("Verify Notification dropdown option",
								"Notification option: " + notificationOption + "is present", Status.PASS);
						flag = true;

						break;
					}
				}

				if (flag == false) {
					report.updateTestLog("Verify Notification dropdown option",
							"Notification option: " + notificationOption + " is not present", Status.PASS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifySelectFilesUploadLabel(String message) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectuploadLabelXpath);
			UIHelper.highlightElement(driver, selectuploadLabelXpath);
			String selectuploadLabel = UIHelper.getTextFromWebElement(driver, selectuploadLabelXpath);

			if (selectuploadLabel.contains(message)) {
				report.updateTestLog("Verify SelectUpload label text in sharebox page",
						"<b> SelectUpload text: </b>" + message + "present", Status.PASS);
			} else {
				report.updateTestLog("Verify SelectUpload label text in sharebox page",
						"<b> SelectUpload text: </b>" + message + "not present", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify  Selectlabel text in sharebox page",
					"<b> SelectUpload text: </b>" + message + "not present", Status.FAIL);
		}
	}

	public void verifySelectFilesButtonText(String message) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectFilesButtonXpath);
			UIHelper.highlightElement(driver, selectFilesButtonXpath);
			String selectButtonLabel = UIHelper.getTextFromWebElement(driver, selectFilesButtonXpath);

			if (selectButtonLabel.contains(message)) {
				report.updateTestLog("Verify SelectButton text in sharebox page",
						"<b> SelectFilesButton: </b>" + message + "present", Status.PASS);
			} else {
				report.updateTestLog("Verify SelectButton label text in sharebox page",
						"<b> SelectFilesButton: </b>" + message + "not present", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify  SelectButton text in sharebox page",
					"<b> SelectFilesButton: </b>" + message + "not present", Status.FAIL);
		}
	}

	public void verifyDownloadCurrentFolderText(String message) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, downloadCurrentFolderXpath);
			UIHelper.highlightElement(driver, downloadCurrentFolderXpath);
			String downloadCurrentFolderLabel = UIHelper.getTextFromWebElement(driver, downloadCurrentFolderXpath);

			if (downloadCurrentFolderLabel.contains(message)) {
				report.updateTestLog("Verify downloadCurrentFolderLabel text in sharebox page",
						"<b> downloadCurrentFolderLabel: </b>" + message + "present", Status.PASS);
			} else {
				report.updateTestLog("Verify downloadCurrentFolderLabel label text in sharebox page",
						"<b> downloadCurrentFolderLabel: </b>" + message + "not present", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify  downloadCurrentFolderLabel text in sharebox page",
					"<b> downloadCurrentFolderLabel: </b>" + message + "not present", Status.FAIL);
		}
	}

	public ArrayList<String> getExcludeShareboxTranslationText(String message) {

		try {
			if (message.equals("downloadButton")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, downloadButtonLabelXpath);
				UIHelper.highlightElement(driver, downloadButtonLabelXpath);
				labelItems.add(UIHelper.getTextFromWebElement(driver, downloadButtonLabelXpath).trim());
			} else if (message.equals("downloadZipButton")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, downloadZipButtonXpath);
				UIHelper.highlightElement(driver, downloadZipButtonXpath);
				labelItems.add(UIHelper.getTextFromWebElement(driver, downloadZipButtonXpath).trim());
			} else if (message.equals("footerTextLabel")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, footerTextXpath);
				UIHelper.highlightElement(driver, footerTextXpath);
				labelItems.add(UIHelper.getTextFromWebElement(driver, footerTextXpath).trim());
			} else if (message.equals("fileNotAvailableLabel")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, fileNotAvailableXpath);
				UIHelper.highlightElement(driver, fileNotAvailableXpath);
				if (UIHelper.getTextFromWebElement(driver, fileNotAvailableXpath).contains(",")) {
					String spliteStr[] = UIHelper.getTextFromWebElement(driver, fileNotAvailableXpath).split(",");
					labelItems.add(spliteStr[1].trim());
				}

			} else if (message.equals("uploadButtonLabel")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, uploadButtonLabelXpath);
				UIHelper.highlightElement(driver, uploadButtonLabelXpath);
				labelItems.add(UIHelper.getTextFromWebElement(driver, uploadButtonLabelXpath).trim());
			} else if (message.equals("dropFilesLabel")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, dropFilesLabelXpath);
				UIHelper.highlightElement(driver, dropFilesLabelXpath);
				labelItems.add(UIHelper.getTextFromWebElement(driver, dropFilesLabelXpath).trim());
			} else if (message.equals("viewType")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, viewTypeLabelXpath);
				List<WebElement> viewItems = UIHelper.findListOfElementsbyXpath(viewTypeLabelXpath, driver);
				for (WebElement viewLabel : viewItems) {
					UIHelper.highlightElement(driver, viewLabel);
					labelItems.add(viewLabel.getText().trim());
				}
			} else if (message.equals("maxallowedFiles")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, maxfilesLabelXpath);
				UIHelper.highlightElement(driver, maxfilesLabelXpath);
				if (UIHelper.getTextFromWebElement(driver, maxfilesLabelXpath).contains(",")) {
					labelItems.add(UIHelper.getTextFromWebElement(driver, maxfilesLabelXpath).replace(",", "")
							.replaceAll("\\d", "").trim());
					System.out.println(
							UIHelper.getTextFromWebElement(driver, maxfilesLabelXpath).replace(",", "").trim());
				}

			} else if (message.equals("filemodify")) {
				UIHelper.waitForVisibilityOfEleByXpath(driver, filemodifyDetails);
				List<WebElement> fileModifyItems = UIHelper.findListOfElementsbyXpath(filemodifyDetails, driver);
				for (WebElement modifyLabel : fileModifyItems) {
					UIHelper.highlightElement(driver, modifyLabel);
					labelItems.add(modifyLabel.getText().trim());
				}
			}

		} catch (Exception e) {
			report.updateTestLog("verify" + message + "text in External sharebox page",
					"<b> " + message + "not present", Status.FAIL);
		}
		return labelItems;
	}

}
