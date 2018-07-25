package com.pearson.automation.alfresco.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

public class AlfrescoShareboxPage extends ReusableLibrary {

	private String commonPageTitleXpath = ".//*[@id='HEADER_TITLE']";

	private String messageEleXpath = ".//*[@id='message']/div";

	private String okBtnXpatha = ".//*[@id='Share']//*[@id='prompt']//span[contains(@id,'yui-gen')]//button[contains(.,'OK')]";
	private String shareFolderHeaderXpath = ".//*[@id='Share']//*[text()='Sharebox Folder']";
	// To enter sharing details
	private String propertiesInputXpath = ".//*[@class='form-fields']//label[contains(.,'CRAFT')]//following-sibling::input[contains(@type,'text')]";
	// private String propertiesInputXpath =
	// ".//*[@class='form-fields']//label//following-sibling::input[@name='prop_sharebox_description']";
	public String messageTextXpath = ".//*[@name='prop_sharebox_shareMessage']";
	public String languageDropdownXpath = ".//*[@name='prop_sharebox_notificationLanguage']";
	public String selectNotificationXpath = ".//*[@name='prop_sharebox_notificationFrequency']";
	public String languageUsedInNotifcLabelXpath = ".//*[text()='Language used in notifications:']";
	public String sharingPopupXpath = ".//*[@class='form-fields']";
	public String saveXpath = ".//*[contains(@class,'submit-button')]//button[contains(.,'Save')]";
	public String saveBtnXpath = ".//*[contains(@class,'submit-button')]//button[contains(.,'OK')]";
	private String notifyChkBoxXpath = ".//*[@class='set-bordered-panel-body']//*[@class='form-field']//*[contains(.,'CRAFT')]//ancestor::div[1]//*[@class='formsCheckBox']";
	private String documentLibraryXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']/a";

	// private String startuploadbutton = ".//span[text()='Start Upload']";
	private String startuploadbutton = "//h1/span[@class='fa fa-cloud-upload']/../following-sibling::div//button[@class='btn btn-primary']";

	private String tempXpathForNotificationField = ".//label[contains(.,'CRAFT')]";
	private String removeEmailIdXpath = ".//*[contains(@id,'prop_sharebox_sharedWith')]/ul/li[1]/a";
	private String selectResultXpathForEmail = ".//*[contains(@id,'select2-result-label')]/span";
	public String cancelBtnXpath = "//button[contains(@id,'form-cancel-button')]";
	private String formFieldsXpath = ".//*[contains(@id,'form-fields')]";
	public String sharingPropFieldValuesXpath = ".//*[contains(@class,'form-field')]/input[contains(@value,'') and not(contains(@type,'checkbox')) and not(contains(@type,'hidden')) and not(contains(@title,'Sharing expires'))]";
	private String sharedWithXpath = ".//*[contains(@class,'form-field')]//ul/li[1]/div";
	public String sharedWithQueTooltipXpath = ".//*[contains(@class,'form-field')]//following-sibling::span[@class='help-icon']//img[contains(@id,'sharebox_sharedWith-help-icon')]";
	public String sharedWithQueTooltipMsgXpath = ".//*[contains(@class,'form-field')]//following-sibling::div[contains(@id,'sharebox_sharedWith-help')]";
	public String sharedMesageFieldXpath = ".//*[contains(@class,'form-field')]/textarea";
	private String errorMessagePopupXpath = ".//*[contains(@id,'alf-id')]//*[@class='text']/*[contains(.,'The value cannot be empty.')]";
	private String shareboxLinkXpath = ".//*[@class='adn ads']//a[contains(.,'sharebox')]";
	private String shareboxHeaderXpath = "//*[@class='page-header']//span[2]";
	public String shareBoxtitleXpath = "//a[@class='navbar-brand']/span[1]";
	public String shareBoxiconXpath = ".//h3[@class='filename']//a[text()='CRAFT']//ancestor::tr//td//img[@title='Folder shared externally']";

	private String uploadFileDropZoneContainerXpath = ".//*[contains(@class,'uploader-dropzone-container')]";
	private String invalidLinkMsgXpathInShareboxPage = ".//*[@class='container']/*[contains(.,'Oops, looks like you have visited an invalid link.')]";
	private String uploadInputXpath = ".//*[@class='react-fine-uploader-file-input']";
	private String tempXpathForUploadedFile = ".//*[contains(@class,'uploader-filename') and text()='CRAFT']";

	// private String uploadBtnXpath = ".//*[@class='btn btn-primary' and
	// contains(.,'Upload')]";
	private String uploadBtnXpath = "//div[@class='fine-uploader-dropzone-container react-fine-uploader-gallery-dropzone']/../following-sibling::div/button";

	private String filesNameListXpath = "//*[@class='thumbnail-block-container']//*[@class='thumbnail-title']";
	private String uploadedByXpath = "//*[@class='thumbnail-block-container']//span[contains(.,'Uploaded By')]/following-sibling::span";
	private String uploadProgressSexXpath = ".//*[@id='uploadprogress']";
	private String tempXpathForUploadedFileUnderFolder = "//*[@class='thumbnail-block-container']//*[contains(@class,'thumbnail-title') and contains(.,'CRAFT')]";
	private String filesLoadingXpath = "//*[@class='well-lg']//*[@class='progress progress-striped active']";
	private String emptyFileMsgXpath = ".//*[@id='files']//*[@class='text-danger']";

	private String tempXpathForSharedFileDownloadOpt = ".//span[contains(text(),'CRAFT')]//ancestor::div[@class='caption']//a";
	// "//.//*[@class='caption']//span[contains(@alt,'CRAFT')]//ancestor::h3//following-sibling::p/a[contains(.,'Download')]";
	// "//.//*[@class='caption']//span[contains(@alt,'CRAFT')]//ancestor::h3//following-sibling::p/a[contains(.,'Download')]";
	// private String abortBtnXpath =
	// ".//*[@id='files']//button[contains(.,'Cancel')]";
	private String abortBtnXpath = ".//*[contains(@class,'uploader-dropzone-container')]//button[contains(@class,'cancel')]";
	// private String abortBtnXpath =
	// ".//*[@id='files']//button[contains(.,'Cancel')]";
	private String tempXpathForFailedToUploadMsg = ".//*[@id='files']//span[contains(.,'CRAFT')]//following-sibling::span";
	public String wronglinkXpath = ".//div[@data-localize='header.messages.wronglink']";
	private String warningMsgXpath = ".//*[@id='prompt']//*[@class='bd']";
	private String fileNamesInSelectFilesSec = ".//*[@class='react-fine-uploader-gallery-file-footer']//span[contains(@class,'filename')]";
	private String fileSizeInSelectFilesSec = ".//*[@class='react-fine-uploader-gallery-file-footer']//span[contains(@class,'filesize')]";

	private String oKBtnXpath = ".//*[@class='button-group']//button[contains(.,'CRAFT')]";
	private String promptbutton = ".//*[@id='prompt']//button[contains(.,'CRAFT')]";
	private String sharingBannerXpath = ".//*[@class='toolbar no-check-bg']";
	private String shareIconXpath = ".//*[@class='filename']//span[contains(.,'CRAFT')]//ancestor::tr//*[@class='status']//*[@title='Folder shared externally']";
	private String sharedFolderXpath = ".//*[@class='filename']//span[contains(.,'CRAFT')]";
	private String duplicateFileWarningMsgXpath = ".//*[@class='text-danger']";
	private String userNameXpath = ".//*[@class='nav navbar-nav navbar-right']//a";
	private String shareBoxMsgXpath = ".//*[contains(@class,'alert-danger') and contains(.,'CRAFT')]";
	private String openfolderShareUI = ".//h3/span[text()='CRAFT']//ancestor::div[@class='caption']//a/span[text()='Open']";
	private String foldernameShareUI = ".//span[@class='current-folder-container']/h3";

	private String tempXpathExternalUploadBy = "//*[@class='yui-dt-data']//h3[contains(.,'CRAFT')]//following-sibling::*/span[contains(.,'MAIL_ID') and contains(.,'External upload by')]";

	private String downloadZipBtnXpath = ".//button//span[text()='Download ZIP']";

	// private String tempXpathForLabelNameInShareboxPopup =
	// ".//*[text()='CRAFT']";
	private String tempXpathForLabelNameInShareboxPopup = ".//*[contains(text(),'CRAFT')]";

	private String shareboxMailSubInFrench = ".//h2[contains(.,'Inviter au dossier partagé')]";
	private String tempShareboxMailSubInFrench = ".//h2[contains(.,'CRAFT')]";
	public String disableokbutton = ".//button[contains(@id,'form-submit-button')]";

	public String deleteemaillt = ".//button[contains(@title,'Delete list')]";
	public String nocontentmsg = ".//div[@class='docListInstructions']//div[@class='docListInstructionTitle']";

	public String foldershareedittitle = ".//h3[@class='filename']//a[text()='CRAFT']//ancestor::td//span[@title='Sharebox_DescEdit']";

	public String emailInputXpath = "//*[contains(@id,'prop_sharebox_sharedWith')]/ul/li[index]/input[@class='select2-input']";
	// public String sendEnterXpath="//ul[@class='select2-choices']";
	public String matchEmailListXpath = "//span[@class='select2-match']";
	public String enteredEmailListXpath = "//li[@class='select2-search-choice']/div";
	public String okOrCancelXpathButton = ".//*[@id='prompt']//button[contains(text(),'CRAFT')]";
	private String upButtonNavigateBackXpath = "//h3[text()='CRAFT']//ancestor::span[@class='current-folder-container']//a/span[contains(text(),'Up')]";

	private String filesLabelTextXpath = "//div[@class='col-lg-8 col-xs-6']/h1/span";
	private String uploadLabelTextXpath = "//h1/span[@class='fa fa-cloud-upload']/following-sibling::span";

	private String helpImageXpath = ".//*[contains(text(),'CRAFT')]/../span/img";
	private String helpTextXpath = ".//*[contains(text(),'CRAFT')]/../div[@class='help-text']";

	public String tempexshareboxfileorfilexpath = ".//div[contains(@class,'caption')]//span[contains(.,'CRAFT')]";
	private String popupXpath = ".//div[@id='prompt']";
	private String okButtonXpath = ".//div[@id='prompt']//span[@class='yui-button yui-push-button default']//button";
	private String cancelButtonXpath = ".//div[@id='prompt']//span[@class='yui-button yui-push-button']//button";
	
	private String languageSwitchXpath=".//div[@class='container sharebox-footer']//span//a//span[contains(text(),'CRAFT')]";

	public AlfrescoShareboxPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Click on Ok Button in Popup Window
	public void enterShareboxDetailsAndSave() {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareFolderHeaderXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Enter Sharing properties

	public void enterPropertiesInSharingWindow(String properties) {
		try {

			// Date date = new Date();
			Date date = new Date(new Date().getTime() + 86400000);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = sdf.format(date);

			UIHelper.waitForVisibilityOfEleByXpath(driver, sharingPopupXpath);

			String splittedFields[] = properties.split(";");

			if (splittedFields != null) {
				for (String value : splittedFields) {
					String splitFiledValue[] = value.split(":");

					if (splitFiledValue != null && splitFiledValue.length > 1) {

						if ((splitFiledValue[0].equalsIgnoreCase("Shared with")) || (splitFiledValue[0]
								.equalsIgnoreCase("Geteilt mit (E-Mail-Adressen der eingeladenen externen Nutzer)"))) {
							String finalFieldValueXpath = propertiesInputXpath.replace("CRAFT", splitFiledValue[0]);
							UIHelper.waitForVisibilityOfEleByXpath(driver, finalFieldValueXpath);
							if (UIHelper.checkForAnElementbyXpath(driver, removeEmailIdXpath)) {
								UIHelper.click(driver, removeEmailIdXpath);
							}

							UIHelper.highlightElement(driver, finalFieldValueXpath);
							if ((splitFiledValue[0].equals("Sharing expires"))
									|| (splitFiledValue[0].equals("Ablaufzeitpunkt der Einladung"))) {
								UIHelper.sendKeysToAnElementByXpath(driver, finalFieldValueXpath, strDate);
							} else {
								UIHelper.sendKeysToAnElementByXpath(driver, finalFieldValueXpath, splitFiledValue[1]);
							}
							UIHelper.waitFor(driver);
							UIHelper.click(driver, selectResultXpathForEmail);
							UIHelper.waitFor(driver);
						} else {
							String finalFieldValueXpath = propertiesInputXpath.replace("CRAFT", splitFiledValue[0]);

							UIHelper.highlightElement(driver, finalFieldValueXpath);
							if ((splitFiledValue[0].equals("Sharing expires"))
									|| (splitFiledValue[0].equals("Ablaufzeitpunkt der Einladung"))) {
								UIHelper.sendKeysToAnElementByXpath(driver, finalFieldValueXpath, strDate);
							} else {
								UIHelper.sendKeysToAnElementByXpath(driver, finalFieldValueXpath, splitFiledValue[1]);
							}
						}
					}

				}

				report.updateTestLog("Enter sharing properties", "Sharing properties entered successfully.",
						Status.PASS);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter sharing properties", "Failed to enter sharing properties", Status.FAIL);
		}

	}

	// Enter message in sharing properties window
	public void enterMessageInSharingPropWindow(String message) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageTextXpath);
			UIHelper.highlightElement(driver, messageTextXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, messageTextXpath, message);

			report.updateTestLog("Enter message in sharing properties window",
					"Message entered successfully.<br><b>Message :</b>" + message, Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter message in sharing properties window",
					"Failed to enter message in sharing properties window", Status.FAIL);
		}
	}

	// Select Language in sharing properties window
	public void selectLanguage(String languageOpt) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, languageDropdownXpath);
			UIHelper.highlightElement(driver, languageDropdownXpath);

			UIHelper.click(driver, languageDropdownXpath);
			UIHelper.waitFor(driver);

			UIHelper.selectbyVisibleText(driver, languageDropdownXpath, languageOpt);

			report.updateTestLog("Select language in sharing properties window",
					"Language Selected successfully.<br><b>Language :</b>" + languageOpt, Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select language in sharing properties window",
					"Failed to select language in sharing properties window", Status.FAIL);
		}
	}

	// Select Notifications in sharing properties window
	public void selectNotificationsDropdown(String notification) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectNotificationXpath);
			UIHelper.highlightElement(driver, selectNotificationXpath);

			UIHelper.click(driver, selectNotificationXpath);
			UIHelper.waitFor(driver);

			UIHelper.selectbyVisibleText(driver, selectNotificationXpath, notification);

			report.updateTestLog("Select notification in sharing properties window",
					"Notification Selected successfully.<br><b>Notification :</b>" + notification, Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select notification in sharing properties window",
					"Failed to select Notification in sharing properties window", Status.FAIL);
		}
	}

	// Select Notify details in sharing properties window
	public void selectNotifyCheckBoxes(String notifyDetails) {
		try {

			String splittedFields[] = notifyDetails.split(",");

			if (splittedFields != null) {
				for (String value : splittedFields) {
					String splitFiledValue[] = value.split(":");

					if (splitFiledValue != null) {
						String finalFieldValueXpath = notifyChkBoxXpath.replace("CRAFT", splitFiledValue[0]);

						String finalXpathForNotificationField = tempXpathForNotificationField.replace("CRAFT",
								splitFiledValue[0]);

						if (UIHelper.findAnElementbyXpath(driver, finalFieldValueXpath).isSelected()) {
							if (splitFiledValue[1].equalsIgnoreCase("No")) {
								UIHelper.highlightElement(driver,
										UIHelper.findAnElementbyXpath(driver, finalXpathForNotificationField));
								JavascriptExecutor executor = (JavascriptExecutor) driver;
								executor.executeScript("arguments[0].click();",
										UIHelper.findAnElementbyXpath(driver, finalXpathForNotificationField));
							}
						} else {
							if (splitFiledValue[1].equalsIgnoreCase("Yes")) {
								UIHelper.highlightElement(driver,
										UIHelper.findAnElementbyXpath(driver, finalXpathForNotificationField));
								JavascriptExecutor executor = (JavascriptExecutor) driver;
								executor.executeScript("arguments[0].click();",
										UIHelper.findAnElementbyXpath(driver, finalXpathForNotificationField));
							}
						}
					}

				}

				report.updateTestLog("Select Notify details", "Notify details selected successfully.", Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Notify details", "Failed to select Notify details", Status.FAIL);
		}
	}

	// Common method to enter details in sharing properties
	public void commonMethodToEnterSharingProperties(String properties, String message, String notification,
			String notifyDetails) {
		try {
			enterPropertiesInSharingWindow(properties);
			enterMessageInSharingPropWindow(message);
			selectNotificationsDropdown(notification);
			selectNotifyCheckBoxes(notifyDetails);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter Sharing properties in Sharing Window", "Failed to ented Sharing properties",
					Status.FAIL);
		}

	}

	// Common method to enter details in sharing properties
	public void commonMethodToEnterSharingProperties(String properties, String message, String languageOpt,
			String notification, String notifyDetails) {
		try {
			enterPropertiesInSharingWindow(properties);
			enterMessageInSharingPropWindow(message);
			selectLanguage(languageOpt);
			selectNotificationsDropdown(notification);
			selectNotifyCheckBoxes(notifyDetails);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter Sharing properties in Sharing Window", "Failed to ented Sharing properties",
					Status.FAIL);
		}

	}

	// Click on save Button in Popup Window
	public void clickOnSaveBtnInSharingPopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, saveBtnXpath);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, saveBtnXpath));
			UIHelper.click(driver, saveBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, saveBtnXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on [OK] button", "Clicked on [OK] button successfully.", Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on [OK] button", "Failed to clicked [OK] button", Status.FAIL);
		}
	}

	// Click on save Button in Popup Window
	public void clickOneditsaveBtnInSharingPopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, saveXpath);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, saveXpath));
			UIHelper.click(driver, saveXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on [SAVE] button", "Clicked on [SAVE] button successfully.", Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on [SAVE] button", "Failed to clicked [SAVE] button", Status.FAIL);
		}
	}

	// Click on Cancel Button in Popup Window
	public void clickOnCancelBtnInSharingPopup() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelBtnXpath);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, cancelBtnXpath));

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", UIHelper.findAnElementbyXpath(driver, cancelBtnXpath));

			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on [Cancel] button", "Clicked on [Cancel] button successfully.", Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on [Cancel] button", "Failed to clicked [Cancel] button", Status.FAIL);
		}
	}

	// Get all Form Fields Text
	public ArrayList<String> getSharingPropertyValues() {
		ArrayList<String> sharingPropFieldValList = new ArrayList<String>();
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, saveBtnXpath);

			List<WebElement> propFieldListEle = UIHelper.findListOfElementsbyXpath(sharingPropFieldValuesXpath, driver);

			String messageFieldVal = UIHelper.getTextFromWebElement(driver, sharedMesageFieldXpath);
			String mailFieldValXpath = UIHelper.getTextFromWebElement(driver, sharedWithXpath);

			for (WebElement ele : propFieldListEle) {
				sharingPropFieldValList.add(ele.getAttribute("value").trim());
			}

			sharingPropFieldValList.add(messageFieldVal.trim());
			sharingPropFieldValList.add(mailFieldValXpath.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sharingPropFieldValList;
	}

	// Get the text from Error Message Popup
	public String getTextFromErrorMessagePopup() {
		String fieldErrorMessageVal = "";
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, saveXpath);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, saveXpath));
			UIHelper.click(driver, saveXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, errorMessagePopupXpath);
			WebElement fieldErrorEle = UIHelper.findAnElementbyXpath(driver, errorMessagePopupXpath);
			if (UIHelper.checkForAnWebElement(fieldErrorEle)) {
				fieldErrorMessageVal = fieldErrorEle.getText();
				report.updateTestLog("Verify error msg",
						"error msg displayed successfully <br><b>Errormsg :</b>" + fieldErrorMessageVal, Status.PASS);
			} else {
				report.updateTestLog("Verify error msg", "error msg not displayed .", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify error msg", "error msg not displayed .", Status.FAIL);
			e.printStackTrace();
		}

		return fieldErrorMessageVal;
	}

	// Navigate to shared folder through mail link
	public void navigateToShareboxLinkFromMail(String folderName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxLinkXpath);
			UIHelper.click(driver, shareboxLinkXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, filesLoadingXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, filesLoadingXpath);
			/* try { */
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxHeaderXpath);

			/*
			 * } catch (Exception e) { }
			 */

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			if (UIHelper.getTextFromWebElement(driver, shareboxHeaderXpath).contains(folderName)) {
				UIHelper.highlightElement(driver, shareboxHeaderXpath);
				report.updateTestLog("Navigate to shared folder",
						"Navigated to shared folder successfully <br><b>Shared folder Name :</b>" + folderName,
						Status.PASS);
			} else {
				report.updateTestLog("Navigate to shared folder",
						"Failed to navigate to shared folder<br><b>Shared folder Name :</b>" + folderName, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Navigate to shared folder", "Failed to navigate to shared folder", Status.FAIL);
		}

	}

	public void switchToGmailTab() {
		try {
			List<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			if (tabs2.size() == 2) {
				driver.switchTo().window(tabs2.get(0));
				UIHelper.waitFor(driver);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Navigate to shared folder through mail link
	public void navigateToShareboxLinkFromMailInNegativeCase() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxLinkXpath);
			UIHelper.click(driver, shareboxLinkXpath);
			UIHelper.waitFor(driver);
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, filesLoadingXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, invalidLinkMsgXpathInShareboxPage);
			} catch (Exception e) {

			}
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, invalidLinkMsgXpathInShareboxPage)) {
				UIHelper.highlightElement(driver, invalidLinkMsgXpathInShareboxPage);
				report.updateTestLog(
						"Navigated to Sharebox for Alfresco page successfully by clicking on the link provided in the notification mail",
						"Navigated to Sharebox for Alfresco page successfully and User able to see the error message as 'Oops, looks like you have visited an invalid link.'",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Navigate to shared folder by clicking on the link provided in the notification mail",
						"Navigated to Sharebox for Alfresco page successfully and User failed to see the error message as 'Oops, looks like you have visited an invalid link.'",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Navigate to shared folder by clicking on the link provided in the notification mail",
					"Navigated to Sharebox for Alfresco page successfully and User failed to see the error message as 'Oops, looks like you have visited an invalid link.'",
					Status.FAIL);
		}

	}

	// Upload a file in shared folder
	public void commonMethodForUploadFiles(String filePath, String fileName) {
		try {
			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);
			String finalXpathForUploadedFileUnderFolder = tempXpathForUploadedFileUnderFolder.replace("CRAFT",
					fileName);
			// UIHelper.pageRefresh(driver);
			// UIHelper.waitFor(driver);
			// UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, startuploadbutton);
				UIHelper.click(driver, startuploadbutton);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFileDropZoneContainerXpath);
				UIHelper.highlightElement(driver, uploadFileDropZoneContainerXpath);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// uploadInputXpath);
			} catch (Exception e) {
			}

			// if (UIHelper.checkForAnElementbyXpath(driver, uploadInputXpath))
			// {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, uploadInputXpath);
			WebElement fileInput = driver.findElement(By.xpath(uploadInputXpath));
			fileInput.sendKeys(filePath + fileName);

			try {
				UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
			} catch (Exception e) {
			}
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			report.updateTestLog("upload File chosen in Shared Folder", "Chosen file to upload "
					+ filesNameInSelectFiles() + " " + "With file size" + " " + filesSizesInSelectFiles(), Status.PASS);
			UIHelper.click(driver, uploadBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, filesLoadingXpath);
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForUploadedFileUnderFolder);
			} catch (Exception e) {
			}
			List<WebElement> fileNameList = new ArrayList<WebElement>();
			fileNameList = UIHelper.findListOfElementsbyXpath(filesNameListXpath, driver);
			for (WebElement file : fileNameList) {
				if (file.getText().contains(fileName)) {
					UIHelper.highlightElement(driver, file);
					report.updateTestLog("Upload File", "Upload file using -" + "<b>FilePath:</b>" + filePath + ", "
							+ "<b>FileName:</b>" + file.getText(), Status.PASS);

				}
			}

			// }
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Upload File", "Failed to Upload file", Status.FAIL);

		}
	}

	// Verify files in shared folder
	public boolean verifyFilesInExternalSharebox(String fileName) {
		boolean flag = false;
		try {

			List<WebElement> fileNameList = new ArrayList<WebElement>();
			fileNameList = UIHelper.findListOfElementsbyXpath(filesNameListXpath, driver);
			for (WebElement file : fileNameList) {
				if (file.getText().contains(fileName)) {
					UIHelper.highlightElement(driver, file);
					/*
					 * report.
					 * updateTestLog("Verify files in external sharebox UI",
					 * "File is present in external sharebox UI" +
					 * "<b>FileName:</b>" + file.getText(), Status.PASS);
					 */
					flag = true;
				}
			}

			// }
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify files in external sharebox UI", "Failed to display file", Status.FAIL);

		}
		return flag;
	}

	// upload mutiple files in shared folder
	public void uploadFileInSharedFolder(String filePath, String fileName) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			} else {
				finalFilePath = System.getProperty("user.dir") + filePath;
			}

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					commonMethodForUploadFiles(finalFilePath, fileNameVal);
				}
			} else {
				commonMethodForUploadFiles(finalFilePath, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Abort upload file in shared folder
	public void abortUploadFileInSharedFolder(String filePath, String fileName) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			} else {
				finalFilePath = System.getProperty("user.dir") + filePath;
			}

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					commonMethodForAbortUploadFiles(finalFilePath, fileNameVal);
				}
			} else {
				commonMethodForAbortUploadFiles(finalFilePath, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Abort Upload a file in shared folder
	public void commonMethodForAbortUploadFiles(String filePath, String fileName) {
		try {
			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, startuploadbutton);
				UIHelper.click(driver, startuploadbutton);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFileDropZoneContainerXpath);
				UIHelper.highlightElement(driver, uploadFileDropZoneContainerXpath);

			} catch (Exception e) {

			}
			UIHelper.waitFor(driver);

			WebElement uploadBtnEle = driver.findElement(By.xpath(uploadInputXpath));
			uploadBtnEle.sendKeys(filePath + fileName);

			UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
			UIHelper.waitForVisibilityOfElementLocated(driver, fileNamesInSelectFilesSec);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, abortBtnXpath);

			if (UIHelper.checkForAnElementbyXpath(driver, abortBtnXpath)) {
				UIHelper.click(driver, abortBtnXpath);

				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, finalXpathForUploadedFile);
				if (!UIHelper.checkForAnElementbyXpath(driver, finalXpathForUploadedFile)) {
					report.updateTestLog("Abort upload File in Shared Folder", "Aborted uploading of file "
							+ filesNameInSelectFiles() + " " + "With file size" + " " + filesSizesInSelectFiles(),
							Status.PASS);
				} else {
					report.updateTestLog("Abort upload File in Shared Folder",
							"Failed to abort uploading of file " + fileName, Status.FAIL);
				}
			}

			List<WebElement> fileNameList = null;

			try {
				fileNameList = UIHelper.findListOfElementsbyXpath(filesNameListXpath, driver);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (fileNameList != null && fileNameList.size() != 0) {
				for (WebElement file : fileNameList) {
					if (!(file.getText().contains(fileName))) {

						report.updateTestLog("Check uploaded file in 'Files' Section",
								"File aborted and not displayed in 'Files' Section", Status.PASS);

					} else {
						report.updateTestLog("Check uploaded file in 'Files' Section",
								"File Uploaded and displayed successfully 'Files' Section", Status.FAIL);
					}
				}
			} else {
				report.updateTestLog("Check uploaded file in 'Files' Section",
						"File aborted and not displayed in 'Files' Section", Status.PASS);
			}
			/* } */
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * report.updateTestLog("Abort upload File in Shared Folder",
			 * "Failed to abort uploading of file", Status.FAIL);
			 */
		}
	}

	public String filesNameInSelectFiles() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFileDropZoneContainerXpath);
		UIHelper.waitForVisibilityOfElementLocated(driver, fileNamesInSelectFilesSec);
		String filenames = UIHelper.getTextFromWebElement(driver, fileNamesInSelectFilesSec);
		return filenames;
	}

	public String filesSizesInSelectFiles() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFileDropZoneContainerXpath);
		UIHelper.waitForVisibilityOfElementLocated(driver, fileNamesInSelectFilesSec);
		String fileSizes = UIHelper.getTextFromWebElement(driver, fileSizeInSelectFilesSec);
		return fileSizes;
	}

	// upload mutiple files in shared folder
	public void uploadSameFileAfterAbortOperation(String filePath, String fileName) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			} else {
				finalFilePath = System.getProperty("user.dir") + filePath;
			}

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					performUploadProcessWithouRefresh(finalFilePath, fileNameVal);
				}
			} else {
				performUploadProcessWithouRefresh(finalFilePath, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Upload a file in shared folder
	public void performUploadProcessWithouRefresh(String filePath, String fileName) {
		try {
			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);
			String finalXpathForFailedToUploadMsg = tempXpathForFailedToUploadMsg.replace("CRAFT", fileName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadInputXpath);
			WebElement uploadBtnEle = driver.findElement(By.xpath(uploadInputXpath));

			if (UIHelper.checkForAnWebElement(uploadBtnEle)) {
				UIHelper.highlightElement(driver, uploadBtnEle);
				WebElement fileInput = driver.findElement(By.xpath(uploadInputXpath));
				fileInput.sendKeys(filePath + fileName);

				UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);

				UIHelper.click(driver, uploadBtnXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, filesLoadingXpath);
				UIHelper.waitFor(driver);
				List<WebElement> fileNameList = new ArrayList<WebElement>();
				fileNameList = UIHelper.findListOfElementsbyXpath(filesNameListXpath, driver);
				boolean isUploadedFile = false;
				for (WebElement file : fileNameList) {
					if (file.getText().trim().contains(fileName)) {
						UIHelper.highlightElement(driver, file);

						isUploadedFile = true;

					} else {
						isUploadedFile = false;
					}
				}

				boolean isDisplayedFailedToErrorMsg = UIHelper.checkForAnElementbyXpath(driver,
						finalXpathForFailedToUploadMsg);

				if (isUploadedFile && !isDisplayedFailedToErrorMsg) {
					report.updateTestLog("Upload File",
							"Upload file using -" + "<b>FilePath:</b>" + filePath + ", " + "<b>FileName:</b>" + fileName
									+ " and the previous file upload failed error message get refreshed and not be displayed",
							Status.PASS);
				} else {
					report.updateTestLog("Upload File",
							"Failed to Upload file/'Upload failed' error message not get refreshed", Status.FAIL);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Upload File", "Failed to Upload file", Status.FAIL);

		}
	}

	// Upload a o bytes file and check upload button is disabled
	public void checkUploadBtnDisabledForEmptyFile(String filePath, String fileName) {
		try {
			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);
			UIHelper.pageRefresh(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			UIHelper.highlightElement(driver, uploadBtnXpath);
			UIHelper.click(driver, uploadBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			WebElement uploadBtnEle = driver.findElement(By.xpath(uploadInputXpath));

			if (UIHelper.checkForAnElementbyXpath(driver, uploadBtnXpath)) {

				// UIHelper.highlightElement(driver, uploadBtnEle);
				WebElement fileInput = driver.findElement(By.xpath(uploadInputXpath));
				fileInput.sendKeys(filePath + fileName);
				UIHelper.highlightElement(driver, uploadBtnXpath);
				UIHelper.click(driver, uploadBtnXpath);

				/*
				 * UIHelper.waitForVisibilityOfElementLocated(driver,
				 * finalXpathForUploadedFile);
				 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				 * messageEleXpath); UIHelper.waitFor(driver);
				 * UIHelper.waitForVisibilityOfEleByXpath(driver,
				 * uploadBtnXpath);
				 */
				if (UIHelper.isDisplayedAlertMessage(driver)) {

					// UIHelper.highlightElement(driver, uploadBtnXpath);
					// UIHelper.highlightElement(driver, emptyFileMsgXpath);
					report.updateTestLog("Verify zero upload", "Cannot upload zero size file, Alert msg is shown",
							Status.PASS);
				} else {
					/*
					 * report.updateTestLog("Check [UPLOAD] button disabled",
					 * "[UPLOAD] button is enabled for Empty file",
					 * Status.FAIL);
					 */

					report.updateTestLog("Verify zero upload", "Verify upload zero size file failed", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * report.updateTestLog("Check [UPLOAD] button disabled",
			 * "[UPLOAD] button not displayed", Status.FAIL);
			 */

			report.updateTestLog("Verify zero upload", "Verify upload zero size file failed", Status.FAIL);

		}
	}

	// upload empty file in shared folder
	public AlfrescoShareboxPage uploadEmptyFileInSharedFolder(String filePath, String fileName) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			} else {
				finalFilePath = System.getProperty("user.dir") + filePath;
			}

			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					commonMethodToUpload0ByteFile(finalFilePath, fileNameVal);
					// checkUploadBtnDisabledForEmptyFile(finalFilePath,
					// fileNameVal);
				}
			} else {
				// checkUploadBtnDisabledForEmptyFile(finalFilePath, fileName);
				commonMethodToUpload0ByteFile(finalFilePath, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoShareboxPage(scriptHelper);
	}

	// Upload 0 byte file and validate
	public void commonMethodToUpload0ByteFile(String filePath, String fileName) {
		try {
			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);
			// String finalXpathForUploadedFileUnderFolder =
			// tempXpathForUploadedFileUnderFolder.replace("CRAFT",fileName);
			String errorMsg = dataTable.getData("Sharebox", "EditShareboxFieldsData");
			// UIHelper.pageRefresh(driver);
			// UIHelper.waitFor(driver);
			// UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, startuploadbutton);
				UIHelper.click(driver, startuploadbutton);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, uploadFileDropZoneContainerXpath);
				UIHelper.highlightElement(driver, uploadFileDropZoneContainerXpath);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// uploadInputXpath);
			} catch (Exception e) {
			}

			// if (UIHelper.checkForAnElementbyXpath(driver, uploadInputXpath))
			// {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, uploadInputXpath);
			WebElement fileInput = driver.findElement(By.xpath(uploadInputXpath));
			fileInput.sendKeys(filePath + fileName);

			/* try { */
			UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
			report.updateTestLog("upload File chosen in Shared Folder", "Chosen file to upload"
					+ filesNameInSelectFiles() + " " + "With file size" + " " + filesSizesInSelectFiles(), Status.PASS);
			/*
			 * } catch (Exception e) { }
			 */
			UIHelper.waitFor(driver);
			UIHelper.click(driver, uploadBtnXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, uploadFileDropZoneContainerXpath);
			UIHelper.waitFor(driver);
			// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			// messageEleXpath);
			// UIHelper.click(driver, uploadBtnXpath);

			// WebDriverWait wait = new WebDriverWait(driver,
			// HIGHLIGHTERINTMAX);
			// wait.until(ExpectedConditions.alertIsPresent());
			// Alert alert = driver.switchTo().alert();
			String zeroByteMsg = driver.switchTo().alert().getText();
			report.updateTestLog("Verify the alert message", "Alert message displayed is " + zeroByteMsg, Status.DONE);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			String validateError = fileName + " " + errorMsg;
			if (zeroByteMsg.equals(validateError)) {
				report.updateTestLog("Verify the alert message matches",
						"Alert message matches " + zeroByteMsg.equals(validateError), Status.PASS);
			} else {
				report.updateTestLog("Verify the alert message matches", "Alert message does not match", Status.FAIL);
			}

			/*
			 * } catch (Exception e) {
			 * 
			 * 
			 * }
			 */
			/*
			 * UIHelper.waitForPageToLoad(driver); UIHelper.waitFor(driver);
			 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			 * filesLoadingXpath); UIHelper.waitFor(driver);
			 */
			/*
			 * try { UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * finalXpathForUploadedFileUnderFolder); } catch (Exception e) { }
			 * List<WebElement> fileNameList = new ArrayList<WebElement>();
			 * fileNameList =
			 * UIHelper.findListOfElementsbyXpath(filesNameListXpath, driver);
			 * for (WebElement file : fileNameList) { if
			 * (!file.getText().contains(fileName)) {
			 * UIHelper.highlightElement(driver, file);
			 * report.updateTestLog("Upload File",
			 * "File is not uploaded using -" + "<b>FilePath:</b>" + filePath +
			 * ", " + "<b>FileName:</b>", Status.PASS);
			 * 
			 * } }
			 * 
			 * }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Upload File", "file is uploaded", Status.FAIL);

		}
	}

	// Download shared files
	public void downloadSharedFilesUsingExternalMailLink(String fileName, String fileDownloadPath) {
		try {
			String finalXpathForSharedFileDownloadOpt = tempXpathForSharedFileDownloadOpt.replace("CRAFT", fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSharedFileDownloadOpt);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForSharedFileDownloadOpt)) {
				UIHelper.highlightElement(driver, finalXpathForSharedFileDownloadOpt);
				new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath, fileName);

				UIHelper.click(driver, finalXpathForSharedFileDownloadOpt);

				new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, fileName);

				File zipFile = new File(fileDownloadPath + "\\" + fileName);
				if (zipFile.exists()) {
					report.updateTestLog("Check Downloaded File", "File: " + fileName + " downloaded successfully",
							Status.PASS);
				} else {
					report.updateTestLog("Check Downloaded File", "File: " + fileName + " failed to download",
							Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check Downloaded File", "File: " + fileName + " failed to download", Status.FAIL);
		}
	}

	// Download Zip
	public void performDownloadZipInShareboxPage(String fileName, String fileDownloadPath) {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, downloadZipBtnXpath)) {
				UIHelper.highlightElement(driver, downloadZipBtnXpath);
				new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath, fileName);

				UIHelper.click(driver, downloadZipBtnXpath);

				new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, fileName);

				File zipFile = new File(fileDownloadPath + "\\" + fileName);
				if (zipFile.exists()) {
					report.updateTestLog("Verify downloaded dile using 'Download ZIP' option",
							"File: " + fileName + " downloaded successfully", Status.PASS);
				} else {
					report.updateTestLog("Verify downloaded dile using 'Download ZIP' option",
							"File: " + fileName + " failed to download", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify downloaded dile using 'Download ZIP' option",
					"File: " + fileName + " failed to download", Status.FAIL);
		}
	}

	// temporarily added this method - need to remove
	public boolean performDownloadZipInShareboxPageAndReturnBoolean(String fileName, String fileDownloadPath) {

		boolean flag = false;

		try {
			if (UIHelper.checkForAnElementbyXpath(driver, downloadZipBtnXpath)) {
				UIHelper.highlightElement(driver, downloadZipBtnXpath);
				new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath, fileName);

				UIHelper.click(driver, downloadZipBtnXpath);

				new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, fileName);

				File zipFile = new File(fileDownloadPath + "\\" + fileName);
				if (zipFile.exists()) {

					flag = true;

				} else {

					flag = false;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify file not downloadable using 'Download ZIP' option",
					"File: " + fileName + " failed to download", Status.PASS);
		}
		return flag;
	}

	// Navigate to shared folder through mail link
	public void ShareboxLinkerrorcaseFromMail(String folderName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxLinkXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, shareboxLinkXpath)) {
				UIHelper.highlightElement(driver, shareboxLinkXpath);
				report.updateTestLog("Share Folder Link", " Share folder link displayed", Status.PASS);
			} else {
				report.updateTestLog("Share Folder Link", " Share folder link not displayed", Status.FAIL);
			}

			UIHelper.click(driver, shareboxLinkXpath);
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, filesLoadingXpath);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Navigate to shared folder", "Failed to navigate to shared folder", Status.FAIL);
		}

	}

	// verify warning message displayed when trying to share a folder within a
	// shared folder
	public void verifyWarningMsgOnSharingFolderWithinSharedFolder(String message) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, oKBtnXpath);

			if (UIHelper.getTextFromWebElement(driver, warningMsgXpath).equalsIgnoreCase(message)) {
				UIHelper.highlightElement(driver, warningMsgXpath);
				report.updateTestLog("Verify Warning message when sharing a sub folder within a shared folder",
						"Warning message displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Warning message when sharing a sub folder within a shared folder",
						"Warning message is not matched", Status.FAIL);
			}

			UIHelper.click(driver, oKBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sharingBannerXpath);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Warning message when sharing a sub folder within a shared folder",
					"Failed to display warning message", Status.FAIL);
		}
	}

	// check share icon available for the shared folder in document library
	public boolean checkShareIconAvailable(String folderName) {
		boolean flag = false;
		try {
			String fianlSharedFolderXpath = sharedFolderXpath.replace("CRAFT", folderName);
			String fianlSshareIconXpath = shareIconXpath.replace("CRAFT", folderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, fianlSharedFolderXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, fianlSshareIconXpath)) {
				UIHelper.highlightElement(driver, fianlSshareIconXpath);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	// Verify warning message when uploading duplicate file
	public void verifyWarningMsgOnUploadingDuplicateFile(String filePath, String fileName, String message) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			} else {
				finalFilePath = System.getProperty("user.dir") + filePath;
			}

			String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, uploadInputXpath);
			 * WebElement uploadBtnEle =
			 * driver.findElement(By.xpath(uploadInputXpath));
			 * 
			 * if (UIHelper.checkForAnWebElement(uploadBtnEle)) {
			 */
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, uploadInputXpath);
			WebElement fileInput = driver.findElement(By.xpath(uploadInputXpath));
			fileInput.sendKeys(finalFilePath + fileName);

			try {
				UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
				
			} catch (Exception e) {
			}
				
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			
			UIHelper.click(driver, uploadBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			/*
			 * Robot r = new Robot(); r.keyPress(KeyEvent.VK_ENTER);
			 * r.keyRelease(KeyEvent.VK_ENTER);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * duplicateFileWarningMsgXpath);
			 */

			/*
			 * new WebDriverWait(driver, 60)
			 * .ignoring(NoAlertPresentException.class)
			 * .until(ExpectedConditions.alertIsPresent());
			 */
			//UIHelper.waitFor(driver);

			String expectedAlertMsg = message.replace("FILE_NAME", fileName);

			
			boolean isDisplayedAlertMsg = false;
			try {
				System.out.println("switch to alert");
				Alert alert = driver.switchTo().alert();
				
				// String alertMessageVal = alert.getText();
				alert.accept();
				isDisplayedAlertMsg = true;
			} catch (UnhandledAlertException  e) {
				isDisplayedAlertMsg = false;
			}
			
			driver.switchTo().defaultContent();
			UIHelper.waitFor(driver);

			if (isDisplayedAlertMsg) {
				report.updateTestLog("Verify alert message when uploading a duplicate file",
						"Alert message:<b>" + expectedAlertMsg + "</b> displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify alert message when uploading a duplicate file",
						"Alert message:<b>" + expectedAlertMsg + "</b> not displayed", Status.FAIL);
			}

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * duplicateFileWarningMsgXpath);
			 * 
			 * if (UIHelper.getTextFromWebElement(driver,
			 * duplicateFileWarningMsgXpath).equalsIgnoreCase(message)) {
			 * UIHelper.highlightElement(driver, duplicateFileWarningMsgXpath);
			 * report.
			 * updateTestLog("Verify Warning message when uploading a duplicate file"
			 * , "Warning message displayed successfully", Status.PASS); } else
			 * { report.
			 * updateTestLog("Verify Warning message when uploading a duplicate file"
			 * , "Warning message is not matched", Status.FAIL); }
			 */

			// }
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Upload File", "Failed to Upload file", Status.FAIL);

		}
	}

	// check message displayed in share box window
	public void checkMessageDisplayed(String message) {
		try {
			UIHelper.click(driver, userNameXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareBoxMsgXpath.replace("CRAFT", message));
			if (UIHelper.checkForAnElementbyXpath(driver, shareBoxMsgXpath.replace("CRAFT", message))) {
				UIHelper.highlightElement(driver, shareBoxMsgXpath.replace("CRAFT", message));
				report.updateTestLog("Verify Sharebox Warning message", "Warning message displayed", Status.PASS);
			} else {
				report.updateTestLog("Verify Sharebox Warning message", "Warning message is not displayed",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Sharebox Warning message", "Failed to display warning message", Status.FAIL);
		}

	}

	// Get the uploaded by value from sharebox page
	public String getUploadedByValue() {
		String uploadedByVal = "";
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, uploadedByXpath)) {
				UIHelper.highlightElement(driver, uploadedByXpath);
				uploadedByVal = UIHelper.getTextFromWebElement(driver, uploadedByXpath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return uploadedByVal;
	}

	// Check External upload by data
	public boolean checkExternalUploadByData(String fileName, String mailID) {
		boolean isDisplayedExternalUploadBy = false;
		try {
			String finalXpathExternalUploadBy = tempXpathExternalUploadBy.replace("CRAFT", fileName).replace("MAIL_ID",
					mailID);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathExternalUploadBy)) {
				isDisplayedExternalUploadBy = true;
			} else {
				isDisplayedExternalUploadBy = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedExternalUploadBy;
	}

	// Check field label in sharebox popup
	public boolean checkFieldLabelInShareboxPopup(String fieldLabelNames) {
		boolean isDisplayedFieldLabe = false;
		try {
			if (fieldLabelNames.contains(",")) {
				String splittedLabelNames[] = fieldLabelNames.split(",");

				if (splittedLabelNames != null) {
					for (String labelName : splittedLabelNames) {
						String finalXpathForLabelNameInShareboxPopup = tempXpathForLabelNameInShareboxPopup
								.replace("CRAFT", labelName);

						if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForLabelNameInShareboxPopup)) {
							isDisplayedFieldLabe = true;
						} else {
							isDisplayedFieldLabe = false;
						}
					}
				}
			} else {
				String finalXpathForLabelNameInShareboxPopup = tempXpathForLabelNameInShareboxPopup.replace("CRAFT",
						fieldLabelNames);

				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForLabelNameInShareboxPopup)) {
					isDisplayedFieldLabe = true;
				} else {
					isDisplayedFieldLabe = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFieldLabe;
	}

	public boolean checkFieldLabelInShareboxPopupWithSemicolonDelimiter(String fieldLabelNames) {
		boolean isDisplayedFieldLabe = false;
		try {
			if (fieldLabelNames.contains(";")) {
				String splittedLabelNames[] = fieldLabelNames.split(";");

				if (splittedLabelNames != null) {
					for (String labelName : splittedLabelNames) {
						String finalXpathForLabelNameInShareboxPopup = tempXpathForLabelNameInShareboxPopup
								.replace("CRAFT", labelName);
						UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForLabelNameInShareboxPopup);
						if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForLabelNameInShareboxPopup)) {
							UIHelper.highlightElement(driver, finalXpathForLabelNameInShareboxPopup);
							isDisplayedFieldLabe = true;
						} else {
							isDisplayedFieldLabe = false;
						}
					}
				}
			} else {
				String finalXpathForLabelNameInShareboxPopup = tempXpathForLabelNameInShareboxPopup.replace("CRAFT",
						fieldLabelNames);

				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForLabelNameInShareboxPopup);
				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForLabelNameInShareboxPopup)) {
					UIHelper.highlightElement(driver, finalXpathForLabelNameInShareboxPopup);
					isDisplayedFieldLabe = true;
				} else {
					isDisplayedFieldLabe = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedFieldLabe;
	}

	public boolean checkHelpTextInShareboxPopup(String labelName, String helpTextName) {
		boolean isDisplayedFieldLabe = false;
		try {

			String finalXpathForHelpImageInShareboxPopup = helpImageXpath.replace("CRAFT", labelName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForHelpImageInShareboxPopup);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForHelpImageInShareboxPopup)) {
				UIHelper.highlightElement(driver, finalXpathForHelpImageInShareboxPopup);
				UIHelper.click(driver, finalXpathForHelpImageInShareboxPopup);

				UIHelper.waitFor(driver);

				String finalXpathForHelpTextInShareboxPopup = helpTextXpath.replace("CRAFT", helpTextName);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForHelpTextInShareboxPopup);

				if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForHelpTextInShareboxPopup)) {
					// UIHelper.highlightElement(driver,
					// finalXpathForHelpTextInShareboxPopup);
					UIHelper.scrollToAnElement(
							UIHelper.findAnElementbyXpath(driver, finalXpathForHelpTextInShareboxPopup));
					UIHelper.waitFor(driver);
					String helpText = UIHelper.getTextFromWebElement(driver, finalXpathForHelpTextInShareboxPopup);

					if (helpText.contains(helpTextName)) {
						isDisplayedFieldLabe = true;
					} else {
						isDisplayedFieldLabe = false;
					}

				} else {
					isDisplayedFieldLabe = false;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
			e.printStackTrace();
		}

		return isDisplayedFieldLabe;
	}

	// Check Language options
	public boolean checkLanguageDropdownOptions(String expectedOptions) {
		boolean isDisplayedAllLangOpt = false;
		try {
			ArrayList<String> expectedLanguageOptionsList = new ArrayList<String>();

			if (expectedOptions.contains(",")) {
				String splittedLangOpt[] = expectedOptions.split(",");

				if (splittedLangOpt != null) {
					for (String langOpt : splittedLangOpt) {
						expectedLanguageOptionsList.add(langOpt);
					}
				}
			}

			ArrayList<String> actualLanguageOptionsList = new ArrayList<String>();

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, languageDropdownXpath);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, languageDropdownXpath)) {
				UIHelper.highlightElement(driver, languageDropdownXpath);
				UIHelper.click(driver, languageDropdownXpath);
				UIHelper.waitFor(driver);
				UIHelper.getAllSelectedOptions(driver, languageDropdownXpath, actualLanguageOptionsList);
			}

			isDisplayedAllLangOpt = UIHelper.compareTwoDiffSizeOfLists(actualLanguageOptionsList,
					expectedLanguageOptionsList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedAllLangOpt;
	}

	// Check mail subject in French
	public boolean checkShareboxMailSubInFrench() {
		boolean isDisplayedMailSubInFrench = false;
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, shareboxMailSubInFrench)) {
				isDisplayedMailSubInFrench = true;
			} else {
				isDisplayedMailSubInFrench = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedMailSubInFrench;
	}

	// Check mail subject in French
	public boolean checkShareboxMailSubInFrench(String inputText) {

		boolean isDisplayedMailSubInFrench = false;
		try {
			String finalShareboxMailSubInFrench = tempShareboxMailSubInFrench.replace("CRAFT", inputText);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// finalShareboxMailSubInFrench);

			if (UIHelper.checkForAnElementbyXpath(driver, finalShareboxMailSubInFrench)) {
				isDisplayedMailSubInFrench = true;
			} else {
				isDisplayedMailSubInFrench = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedMailSubInFrench;
	}

	// delete mail list button
	public void deletelist() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteemaillt);
			if (UIHelper.checkForAnElementbyXpath(driver, deleteemaillt)) {
				UIHelper.highlightElement(driver, deleteemaillt);
				UIHelper.click(driver, deleteemaillt);
				report.updateTestLog("Click Delete email button", "Delete email list button successfully", Status.DONE);
			} else {
				report.updateTestLog("Click Delete email button", "Delete email list button Unsuccessfully",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click Delete email button", "Delete email list button Unsuccessfully", Status.FAIL);
		}
	}

	// verify warning message displayed when
	public void promptconfirm(String message) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalpromptbutton = promptbutton.replace("CRAFT", message);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpromptbutton);
			UIHelper.click(driver, finalpromptbutton);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, finalpromptbutton);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// navigate in Sharebox UI
	public void navigateShareUI(String folder) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalfolderopen = openfolderShareUI.replace("CRAFT", folder);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfolderopen);
			UIHelper.highlightElement(driver, finalfolderopen);
			UIHelper.click(driver, finalfolderopen);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, foldernameShareUI);
			if (UIHelper.findAnElementbyXpath(driver, foldernameShareUI).getText().equals(folder)) {
				report.updateTestLog("Click folder Open in Share UI", "Click folder Open in Share UI successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Click folder Open in Share UI", "Click folder Open in Share UI Unsuccessfully",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click folder Open in Share UI", "Click folder Open in Share UI Unsuccessfully",
					Status.FAIL);

		}
	}

	// navigate in Sharebox UI
	public boolean isfoldershared(String folder) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalshareicon = shareBoxiconXpath.replace("CRAFT", folder);
			UIHelper.waitFor(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, finalshareicon);
			if (UIHelper.checkForAnElementbyXpath(driver, finalshareicon)) {
				UIHelper.highlightElement(driver, finalshareicon);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify share Icon is displayed", "Share icon assertion failed", Status.FAIL);
			return false;

		}
	}

	// update date value alone in share
	public void shareDate(String value, String ifneeded) {
		Date date = new Date(new Date().getTime() - 86400000);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);

		String finalFieldValueXpath = propertiesInputXpath.replace("CRAFT", "Sharing expires");

		UIHelper.highlightElement(driver, finalFieldValueXpath);
		if (UIHelper.checkForAnElementbyXpath(driver, finalFieldValueXpath)) {
			UIHelper.sendKeysToAnElementByXpath(driver, finalFieldValueXpath, strDate);
		}
	}

	public void enterEmailAddress(String mailIds) {

		int count = 1;
		String[] tempMailid = new String[20];
		String mailId = null;

		try {
			if (mailIds.contains(";")) {
				tempMailid = mailIds.split(";");
				for (String value : tempMailid) {
					String finalEmailInputXpath = emailInputXpath.replace("index", String.valueOf(count));
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalEmailInputXpath);
					UIHelper.highlightElement(driver, finalEmailInputXpath);
					UIHelper.sendKeysToAnElementByXpath(driver, finalEmailInputXpath, value);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, matchEmailListXpath);
					if (UIHelper.findAnElementbyXpath(driver, matchEmailListXpath).getText().equals(value)) {
						UIHelper.waitForVisibilityOfEleByXpath(driver, matchEmailListXpath);
						UIHelper.click(driver, matchEmailListXpath);
					}
					UIHelper.waitFor(driver);
					count++;
				}
				report.updateTestLog("Verify Email Address entered in ShareBox PopUp menu",
						"Email Address entered successfully", Status.PASS);
			} else {
				mailId = mailIds;
				String finalEmailInputXpath = emailInputXpath.replace("index", String.valueOf(count));
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalEmailInputXpath);
				UIHelper.highlightElement(driver, finalEmailInputXpath);
				UIHelper.sendKeysToAnElementByXpath(driver, finalEmailInputXpath, mailId);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, matchEmailListXpath);
				if (UIHelper.findAnElementbyXpath(driver, matchEmailListXpath).getText().equals(mailId)) {
					UIHelper.waitForVisibilityOfEleByXpath(driver, matchEmailListXpath);
					UIHelper.click(driver, matchEmailListXpath);
				}
			}
			report.updateTestLog("Verify Email Address entered in ShareBox PopUp menu",
					"Email Address entered successfully", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Verify Email Address entered in ShareBox PopUp menu",
					"Email Address value not entered", Status.FAIL);
		}

	}

	public void clickOkorCancelInPropmpt(String msg) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalpromptConfirmbutton = okOrCancelXpathButton.replace("CRAFT", msg);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpromptConfirmbutton);
			UIHelper.highlightElement(driver, finalpromptConfirmbutton);
			UIHelper.click(driver, finalpromptConfirmbutton);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, finalpromptConfirmbutton);
			report.updateTestLog("Click on [" + msg + "] button",
					"Clicked on [" + msg + "] button successfully in Sharing PopUp", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on [" + msg + "] button",
					"Clicked on [" + msg + "] button Unsuccessfull in Sharing PopUp", Status.FAIL);
		}

	}

	// To navigate back in Sharebox UI
	public void navigateBackInShareUI(String folder) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalfolderUpButton = upButtonNavigateBackXpath.replace("CRAFT", folder);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfolderUpButton);
			UIHelper.highlightElement(driver, finalfolderUpButton);
			UIHelper.click(driver, finalfolderUpButton);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			String finalOpenfolderButton = openfolderShareUI.replace("CRAFT", folder);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalOpenfolderButton);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalOpenfolderButton))) {
				report.updateTestLog("Click on Up  for navigate back in Share UI",
						"Navigate back in Share UI successfull", Status.PASS);
			} else {
				report.updateTestLog("Click on Up for navigate back in Share UI",
						"Navigate back in Share UI Unsuccessfull", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Up for navigate back in Share UI", "Navigate back in share UI Unsuccessfull",
					Status.FAIL);

		}
	}

	public void verifyFilesLabelText(String message) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, filesLabelTextXpath);
			UIHelper.highlightElement(driver, filesLabelTextXpath);
			String filesLabel = UIHelper.getTextFromWebElement(driver, filesLabelTextXpath);

			if (filesLabel.contains(message)) {
				report.updateTestLog("Verify Files label text in sharebox page",
						"<b> Files text: </b>" + message + "present", Status.PASS);
			} else {
				report.updateTestLog("Verify Files label text in sharebox page",
						"<b> Files text: </b>" + message + "not present", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Files label text in sharebox page",
					"<b> Files text: </b>" + message + "not present", Status.FAIL);
		}
	}

	public void verifyUploadLabelText(String message) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadLabelTextXpath);
			UIHelper.highlightElement(driver, uploadLabelTextXpath);
			String uploadLabel = UIHelper.getTextFromWebElement(driver, uploadLabelTextXpath);

			if (uploadLabel.contains(message)) {
				report.updateTestLog("Verify Upload label text in sharebox page",
						"<b> Upload text: </b>" + message + "present", Status.PASS);
			} else {
				report.updateTestLog("Verify Upload label text in sharebox page",
						"<b> Upload text: </b>" + message + "not present", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Upload label text in sharebox page",
					"<b> Upload text: </b>" + message + "not present", Status.FAIL);
		}
	}

	public ArrayList<String> getNotificationDropdownValues(String notificationOption) {

		ArrayList<String> notificationOptionsList = new ArrayList<String>();

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectNotificationXpath);
			UIHelper.highlightElement(driver, selectNotificationXpath);
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, selectNotificationXpath),
					notificationOptionsList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificationOptionsList;

	}

	public void verifyfolderOrfiledisplayedinExShareboxUINegative(String folderOrfilename) {
		UIHelper.waitForVisibilityOfEleByXpath(driver, ".//div[contains(@class,'caption')]");
		String finalFieldValueXpath = tempexshareboxfileorfilexpath.replace("CRAFT", folderOrfilename);
		try {
			if (!UIHelper.checkForAnElementbyXpath(driver, finalFieldValueXpath)) {
				report.updateTestLog("verify excluded " + folderOrfilename,
						"Excluded " + folderOrfilename + " is not displayed", Status.PASS);
			} else {
				UIHelper.highlightElement(driver, finalFieldValueXpath);
				report.updateTestLog("verify excluded " + folderOrfilename,
						"Excluded " + folderOrfilename + " is displayed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("verify excluded " + folderOrfilename,
					"Excluded " + folderOrfilename + " is displayed", Status.FAIL);
			e.printStackTrace();
		}

	}

	public void verifyfolderOrfiledisplayedinExShareboxUI(String folderOrfilename) {
		UIHelper.waitForVisibilityOfEleByXpath(driver, ".//div[contains(@class,'caption')]");
		String finalFieldValueXpath = tempexshareboxfileorfilexpath.replace("CRAFT", folderOrfilename);
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, finalFieldValueXpath)) {
				UIHelper.highlightElement(driver, finalFieldValueXpath);
				report.updateTestLog("verify excluded " + folderOrfilename,
						"Excluded " + folderOrfilename + " is displayed", Status.PASS);
			} else {
				report.updateTestLog("verify excluded " + folderOrfilename,
						"Excluded " + folderOrfilename + " is not displayed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("verify excluded " + folderOrfilename,
					"Excluded " + folderOrfilename + " is not displayed", Status.FAIL);
			e.printStackTrace();
		}
	}

	public void clickUploadInExternalShareBox() {
		try {
			UIHelper.findAnElementbyXpath(driver, startuploadbutton).click();
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Verify warning message when uploading duplicate file
		public void verifyWarningAlertMsgOnUploadingDuplicateFile(String filePath, String fileName, String message) {
			try {
				String finalFilePath;
				if (filePath.contains("Automation/Alfresco")) {
					finalFilePath = filePath;
				} else {
					finalFilePath = System.getProperty("user.dir") + filePath;
				}

				String finalXpathForUploadedFile = tempXpathForUploadedFile.replace("CRAFT", fileName);

				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, uploadInputXpath);
				WebElement fileInput = driver.findElement(By.xpath(uploadInputXpath));
				fileInput.sendKeys(finalFilePath + fileName);

				try {
					UIHelper.waitForVisibilityOfElementLocated(driver, finalXpathForUploadedFile);
					
				} catch (Exception e) {
				}
					
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
				
				UIHelper.click(driver, uploadBtnXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				
				String expectedAlertMsg = message.replace("FILE_NAME", fileName);
				String alertMessageVal = null;
				
				boolean isDisplayedAlertMsg = false;
				try {
					System.out.println("switch to alert");
					Alert alert = driver.switchTo().alert();
					alertMessageVal = alert.getText();
					alert.accept();
					isDisplayedAlertMsg = true;
				} catch (UnhandledAlertException  e) {
					isDisplayedAlertMsg = false;
				}
				
				driver.switchTo().defaultContent();
				UIHelper.waitFor(driver);

				if (isDisplayedAlertMsg) {
					
					if(expectedAlertMsg.equals(alertMessageVal))
					{
					report.updateTestLog("Verify alert message when uploading a duplicate file",
							"Alert message:<b>" + expectedAlertMsg + "</b> displayed successfully", Status.PASS);
				 } else {
					report.updateTestLog("Verify alert message when uploading a duplicate file",
							"Alert message:<b>" + expectedAlertMsg + "</b> not displayed", Status.FAIL);
				}

				
				}
			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog("Upload File", "Failed to Upload file", Status.FAIL);

			}
		}
public void clickLanguageInExternalSharebox(String language)
{
	try
	{
		String finalXpathForLanguageSwitch = languageSwitchXpath.replace("CRAFT", language);
		UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForLanguageSwitch);
		UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpathForLanguageSwitch));
		UIHelper.waitFor(driver);
		UIHelper.highlightElement(driver, finalXpathForLanguageSwitch);
		UIHelper.click(driver, finalXpathForLanguageSwitch);
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		report.updateTestLog("Click Language Switcher in ExternalShare UI", "Language:<b>" +language+ "</b> is switched", Status.PASS);	
		
	}
	catch(Exception e)
	{
		report.updateTestLog("Click Language Switcher in ExternalShare UI", "Language:<b>" +language+"</b> is  not switched", Status.FAIL);	
	}
}
}
