package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoEPSPageTest extends ReusableLibrary {

	private String filepublishstatus = "//*[@class='filename']//a[text()='FILE']//ancestor::tr//td[2]//*[contains(@alt,'pearson-CRAFT')]";
	private String filepublishload = "//*[@class='filename']//a[text()='FILE']//ancestor::tr//td[2]//*[contains(@alt,'pearson-publishing')]";
	private String publishstatus = "//*[@class='yui-dt-data']//td[2]//*[contains(@alt,'pearson-CRAFT')]";
	private String fileListXpath = "//*[@class='filename']//a";
	public ArrayList<String> fileFolderNames = new ArrayList<String>();
	private boolean isPublishLinkStatusDisplayed = false;
	private String tempPromptFooterButton = "//*[@id='prompt']//span[contains(@class,'yui-button yui-push-button')]//button[contains(text(),'CRAFT')]";
	private String publishPopUpXpath = "//div[@id='prompt_c']//*[text()='CRAFT']";
	public String editNamePropertiesXpath = "//input[@type='text' and contains(@id,'name')]";
	public String siteNotConfigureTextXpath = "//div[@id='prompt'][1]//div[@class='bd']";
	public ArrayList<String> PublishpopUpItems = new ArrayList<String>();

	public AlfrescoEPSPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	AlfrescoEPSPage epsPage = new AlfrescoEPSPage(scriptHelper);

	public void verifyFilesListedOnBatchPublish(String fileName) {

		try {
			boolean isListMatched = epsPage.isFilesListMatched(fileName);
			if (isListMatched) {
				report.updateTestLog("Batch Publish Files List", "Files Listed as per selected", Status.PASS);
			} else {
				report.updateTestLog("Batch Publish Files List", "Files are not Listed as per selected", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean verifyFilePublishStatus(String status) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.findAndAddElementsToaList(driver, fileListXpath, fileFolderNames);
			UIHelper.waitFor(driver);
			System.out.println(fileFolderNames);
			for (String file : fileFolderNames) {
				System.out.println(file);
				String finalFilePublishStatus = filepublishstatus.replace("FILE", file).replace("CRAFT", status);

				if (UIHelper.checkForAnElementbyXpath(driver, finalFilePublishStatus)) {
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalFilePublishStatus));
					UIHelper.highlightElement(driver, finalFilePublishStatus);
					report.updateTestLog("Verify " + status + " Icon",
							"Publish Status <b>" + status + "</b> for <br/><b>File Name:</b> " + file, Status.PASS);
					isPublishLinkStatusDisplayed = true;
					break;
				} else {
					isPublishLinkStatusDisplayed = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify" + status + "Icon", "Publish Status <b>" + status + "</b> not displayed ",
					Status.FAIL);
		}
		return isPublishLinkStatusDisplayed;
	}

	public void verifyFilePublish(String filename, String status) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalFilePublishStatus = filepublishstatus.replace("FILE", filename).replace("CRAFT", status);
			String finalFilePublishload = filepublishload.replace("FILE", filename).replace("CRAFT", status);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, finalFilePublishload);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFilePublishStatus);
			if (UIHelper.checkForAnElementbyXpath(driver, finalFilePublishStatus)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalFilePublishStatus));
				UIHelper.highlightElement(driver, finalFilePublishStatus);
				report.updateTestLog("Verify " + status + " Icon",
						"Publish Status <b>" + status + "</b> for <br/><b>File Name:</b> " + filename, Status.PASS);

			} else {
				report.updateTestLog("Verify " + status + " Icon",
						"No Publish Status <b>" + status + "</b> for <br/><b>File Name:</b> " + filename, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify" + status + "Icon", "Publish Status <b>" + status + "</b> not displayed ",
					Status.FAIL);
		}

	}

	// verify the presence of button in prompt footer
	public void verifyPromptFooterButton(String value) {
		try {
			String finalButtonValue = tempPromptFooterButton.replace("CRAFT", value);
			if (UIHelper.checkForAnElementbyXpath(driver, finalButtonValue)) {
				UIHelper.highlightElement(driver, finalButtonValue);
				report.updateTestLog("Verify " + value + " button in prompt Footer",
						value + " button present in prompt footer", Status.PASS);
			} else {
				report.updateTestLog("Verify " + value + " button in prompt Footer",
						value + " button not present in prompt footer", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify " + value + " button in prompt Footer", " Button verification failed",
					Status.FAIL);
		}
	}

	public ArrayList<String> getPublishPopUpTableItems(String fieldName) {
		try {
			UIHelper.waitFor(driver);
			String finalfieldName = publishPopUpXpath.replace("CRAFT", fieldName);

			WebElement popUptable = UIHelper.findAnElementbyXpath(driver, finalfieldName);
			if (UIHelper.checkForAnElementbyXpath(driver, finalfieldName)) {
				UIHelper.highlightElement(driver, popUptable);
				if (popUptable.getText().trim().contains("?")) {
					String splittedString[];
					splittedString = popUptable.getText().trim().split("\n");
					PublishpopUpItems.add(splittedString[0]);
				} else
					PublishpopUpItems.add(popUptable.getText().trim());
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return PublishpopUpItems;

	}

	public void verifyPublishPopUpTable(ArrayList<String> list1, ArrayList<String> list2) {
		try {

			if (UIHelper.compareTwoSimilarLists(list1, list2)) {
				report.updateTestLog("verify the field Name for publish pop up screen ",
						"" + list1 + " fieldName is successfully displayed in Publish Popup Screen ", Status.PASS);
			} else {
				report.updateTestLog("verify the field Name for publish pop up screen ",
						"" + list1 + " fieldName is  failed to displayed in Publish Popup Screen ", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("verify the field Name for publish pop up screen ",
					"" + list1 + " fieldName is failed to displayed in Publish Popup Screen ", Status.FAIL);
		}

	}

	public String verifyPublishedFileEditable() {
		String expectedNotEditMsg = null;
		try {
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, editNamePropertiesXpath));
			expectedNotEditMsg = UIHelper.getAttributeFromWebElement(driver, editNamePropertiesXpath, "title").trim();
			System.out.println("Editable " + expectedNotEditMsg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expectedNotEditMsg;
	}

	public String verifySiteNotConfigure(String expMsg) {
		String actSitenotConfigured = null;
		try {
			UIHelper.waitForAnElement(driver, UIHelper.findAnElementbyXpath(driver, siteNotConfigureTextXpath));
			Boolean flag = UIHelper
					.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, siteNotConfigureTextXpath));
			if (flag) {
				actSitenotConfigured = UIHelper.getTextFromWebElement(driver, siteNotConfigureTextXpath);
				if (actSitenotConfigured.equals(expMsg)) {
					report.updateTestLog("Site not configured Message:",
							"<b> 'Site is not configured for publishing' is displayed", Status.PASS);
				} else {
					report.updateTestLog("Site not configured Message:",
							"<b> 'Site is not configured for publishing' is not displayed", Status.FAIL);

				}

			}
		} catch (Exception e) {
			report.updateTestLog("Site not configured Message:",
					"<b> 'Site is not configured for publishing' is not displayed", Status.FAIL);
			e.printStackTrace();

		}
		return actSitenotConfigured;

	}
}
