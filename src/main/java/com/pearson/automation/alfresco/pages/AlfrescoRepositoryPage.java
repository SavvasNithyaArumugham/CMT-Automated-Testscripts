package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * Functional Components class
 * 
 * @author Cognizant
 */
public class AlfrescoRepositoryPage extends ReusableLibrary {
	private String repositoryMenuLinkXpath = ".//*[@id='HEADER_REPOSITORY_text']/a";
	private String commonPageTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String pageBodyXpath = ".//*[@id='bd']";
	private String repositoryFilesTablesXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']";
	private String repositoryfileLableLinkXpath = ".//*[@id='yui-gen151']/a";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String uploadedFilesInRepoTitlesXpath = ".//*[@class='sticky-wrapper']//*[@id='alf-content']//*[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td//h3//span//a";
	private String tempSelectFolderChkboxXpath = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::tr//td[1]//input";

	// Xpaths for Program Site Edit Properties
	private String commonXpathForEditPropFields = ".//label[contains(.,'CRAFT')]//following-sibling::textarea | .//label[contains(.,'CRAFT')]//following-sibling::input";
	private String saveBtnXpathInEditProp = ".//button[contains(@id,'form-submit-button')]";

	private String lodingXpathForDocLib = ".//*[@class='yui-dt-message']/tr/td/*[contains(.,'Loading the Document Library')]";

	private String splittedFieldValues[];

	private String uploadBtnXpath = ".//*[@class='file-upload']//span//button[contains(.,'Upload')]";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoRepositoryPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void navigateToRepositoryPage() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					repositoryMenuLinkXpath);
			UIHelper.click(driver, repositoryMenuLinkXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					lodingXpathForDocLib);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					lodingXpathForDocLib);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			report.updateTestLog("Navigate to Repository Page",
					"User successfully navigated to repository page",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate To Repository Page",
					"Failed to load Document Library", Status.FAIL);
			e.printStackTrace();
		}
	}

	public void enterDataInEditPropForProgramSite(String fieldDetails) {
		try {
			if (fieldDetails.contains(",")) {
				splittedFieldValues = fieldDetails.split(",");
				if (splittedFieldValues != null
						& splittedFieldValues.length > 0) {
					for (String expPropValue : splittedFieldValues) {
						if (expPropValue.contains(":")) {
							String splittedExpPropValue[] = expPropValue
									.split(":");
							if (splittedExpPropValue != null
									&& splittedExpPropValue.length > 1) {

								if (splittedExpPropValue[0]
										.equalsIgnoreCase("Y")) {
									String finalCommonXpathForEditPropFields = commonXpathForEditPropFields
											.replace("CRAFT",
													splittedExpPropValue[1]);
									Thread.sleep(1000);
									/*
									 * UIHelper.waitForVisibilityOfEleByXpath(
									 * driver,
									 * finalCommonXpathForEditPropFields);
									 */
									UIHelper.sendKeysToAnElementByXpath(driver,
											finalCommonXpathForEditPropFields,
											splittedExpPropValue[2]);
									Thread.sleep(1000);
								}
							}
						}
					}
				}
			}

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					saveBtnXpathInEditProp);
			UIHelper.click(driver, saveBtnXpathInEditProp);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					messageEleXpath);

			report.updateTestLog("Perform 'Edit Properties' for Program Site",
					"User successfully edited the properties of Program site"
							+ "<br/><b>Edited Field Details:</b>"
							+ fieldDetails, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to select a file in repository
	public AlfrescoRepositoryPage commonMethodToSelectFileInRepository(
			String fileName) {

		try {

			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath
					.replace("CRAFT", fileName);

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * repositoryfileLableLinkXpath);
			 */
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					repositoryFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By
					.xpath(repositoryFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesInRepoTitlesXpath,
							driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileName)) {
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(
							driver, finalSelectFolderChkboxXpath);
					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();",
							chkboxElement);

					UIHelper.waitFor(driver);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoRepositoryPage(scriptHelper);
	}

}