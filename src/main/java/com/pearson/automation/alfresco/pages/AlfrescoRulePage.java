package com.pearson.automation.alfresco.pages;

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
public class AlfrescoRulePage extends ReusableLibrary {

	private String tempXpathForManageRuleLink = ".//*[contains(@id,'yuievtautoid')]//a[text()='CRAFT']//ancestor::td[1]//following-sibling::td[1]//*[@id='onActionShowMore']//a[contains(.,'More')]//ancestor::div[1]//following-sibling::div//a/span[contains(.,'Manage Rules')]";
	private String btnInheritRulesForManageRuleXpath = "//button[text()='Inherit Rules']";
	private String txtNoRulesForManageRuleXpath = "//h2[text()='No Rules are defined for this folder']";
	private String lnkCreateRulesForManageRuleXpath = "//a[text()='Create Rules']";
	private String headerNewRuleForManageRuleXpath = "//h1[text()='New Rule']";
	private String btnNewRuleForManageRuleXpath = "//button[text()='New Rule']";
	private String txtboxNameForManageRuleXpath = "//div[contains(@class,'rule-form')]//input[@type='text']";
	private String txtboxDescriptionForManageRuleXpath = "//div[contains(@class,'rule-form')]//textarea[@name='description']";
	private String selectWhenForManageRuleXpath = "//div[text()='When:']/ancestor::div//select[ @class='config-name']";
	private String selectAllCriteriaMetForManageRuleXpath = "//div//label[text()='If all criteria are met:']/ancestor::div/ul//select[ @class='config-name']";
	private String selectIsForAllCriteriaMetInManageRuleXpath = "//select[@title='is']";
	private String selectPerformActionForManageRuleXpath = "//div[text()='Perform Action:']/ancestor::div/ul//select[ @class='config-name']";
	private String selectMimeTypeForPerformActionInManageRuleXpath = "//select[@title='Mimetype']";
	private String btnSelectForPerformActionInManageRuleXpath = ".//*[contains(@id,'default-ruleConfigAction')]//ul/li//button[text()='Select...']";
	private String checkBoxRunAppliesToSubFoldersXpath = "//*[@class='form-field applyToChildren']/input";
	private String selectDestinationPopUpXpath = "//*[contains(@id,'destinationDialog')]//div[@class='site']";
	private String btnOKInSelectDestinationPopUpXpath = "//div[contains(@id,'destinationDialog')]//button[text()='OK']";
	private String btnCreateInManageRuleXpath = "//button[text()='Create']";
	private String btnRunRuleInManageRuleXpath = " //button[text()='Run Rules...']";
	private String tempXpathForCreatedRule = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'alf-id')]//a[contains(.,'CRAFT')]";
	private String tempLinkXpathForCreatedRule = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'alf-id')]//a[contains(.,'CRAFT')]//ancestor::li[1]";
	private String ruleListXpath = ".//*[@class='rules-list'] | //*[contains(@class,'dialog-options')]";
	private String ruleDeleteButtonXpath = ".//*[contains(@id,'default-delete-button-button')]";
	private String tempXpathRunRulesOption = ".//*[@class='sticky-wrapper']//*[@id='bd']//span[@class='first-child']//*[contains(.,'Run Rules...')]//ancestor::span[1]//ancestor::span[1]//following-sibling::div[1]//ul/li/a[text()='CRAFT']";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String bodyXpath = ".//*[@id='bd']";
	private String tempXpathForSiteSelection = "//div[contains(@id,'destinationDialog')]//div[@class='site']//a/h4[text()='CRAFT']";
	private String destinationDailogXpath = ".//*[contains(@id,'default-ruleConfigAction-destinationDialog-treeview')]";
	private String tempXpathForDestFolder = ".//*[contains(@id,'default-ruleConfigAction-destinationDialog-treeview')]//table/tbody/tr/td/span[contains(.,'CRAFT')]";
	private String tempXpathForRuleName = ".//*[contains(@id,'folder-rules')]//*[@class='info']/a[contains(@class,'title') and contains(.,'CRAFT')]";
	private String ruleNameXpathInRightPane = ".//*[@class='rule-details']//h2[contains(@id,'default-title')]";

	private String tempXpathForAddOrPlusButtonInPerformActionSec = ".//*[contains(@id,'default-ruleConfigAction')]//ul/li[CRAFT]//*[@class='actions']//*[contains(@class,'add-config')]//button";
	private String tempXpathForPerformActoinDropdownval = ".//*[contains(@id,'default-ruleConfigAction')]//ul/li[CRAFT]//select[ @class='config-name']";
	private String tempXpathForMimeTypeInPerformAction = ".//*[contains(@id,'default-ruleConfigAction')]//li[CRAFT]//select[@title='Mimetype']";
	private String tempXpathForSelectBtnForsiteFolder = ".//*[contains(@id,'default-ruleConfigAction')]//ul/li[CRAFT]//button[text()='Select...']";

	private String tempXpathForAddOrPlusButtonInCriteriaMetSec = ".//*[contains(@id,'default-ruleConfigIfCondition')]//ul/li[CRAFT]//*[@class='actions']//*[contains(@class,'add-config')]//button";
	private String tempXpathForCriteriaMetDropdownval = ".//*[contains(@id,'default-ruleConfigIfCondition')]//ul/li[CRAFT]//select[ @class='config-name']";
	private String tempXpathForCriterialInCriteriaMet = ".//*[contains(@id,'default-ruleConfigIfCondition')]//li[CRAFT]//select[@title='is']";

	private String runningRuleConfirmMassageXpath = ".//*[@id='message']/*[@class='bd']/*[contains(.,'Running rule')]";
	private String successfulRunRuleConfirmMassageXpath = ".//*[@id='message']/*[@class='bd']/*[contains(.,'Rule(s) successfully run')]";
	private String ruleRunFailurePromptXpath = ".//*[@id='prompt']/*[contains(.,'Could not run rules')]";
	private String okBtnXpathInFailurePrompt = ".//*[@id='prompt']/*[contains(.,'Could not run rules')]/following-sibling::*//button[text()='OK']";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoRulePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Select the folder or file transform to the given document format
	public AlfrescoMyFilesPage manageRuleToConvertDocumentFormat(String folderName, String siteName) {
		try {
			clickOnManageRuleLink(folderName);

			clickCreateRuleORNewRule(siteName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Click on Manage Rule link
	public void clickOnManageRuleLink(String folderName) {
		try {
			String finalXpathForManageRuleLink = tempXpathForManageRuleLink.replace("CRAFT", folderName);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForManageRuleLink);

			WebElement manageRuleEle = UIHelper.findAnElementbyXpath(driver, finalXpathForManageRuleLink);
			UIHelper.highlightElement(driver, manageRuleEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", manageRuleEle);

			// UIHelper.click(driver, finalXpathForManageRuleLink);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnInheritRulesForManageRuleXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create transformation rule
	public void commonMethodForCreateRule(String siteName, String ruleName, String sitesRuleDescription,
			String sitesRuleCriteriaIs, String sitesRuleMimeType, String destinationFolder) {
		try {

			String finalLinkXpathForCreatedRule = tempLinkXpathForCreatedRule.replace("CRAFT", ruleName);
			WebElement bodyEle = UIHelper.findAnElementbyXpath(driver, bodyXpath);

			UIHelper.highlightElement(driver, bodyEle);
			UIHelper.mouseOveranElement(driver, bodyEle);

			if (UIHelper.checkForAnElementbyXpath(driver, btnNewRuleForManageRuleXpath)) {
				Thread.sleep(1000);
				UIHelper.waitForVisibilityOfEleByXpath(driver, btnNewRuleForManageRuleXpath);
				UIHelper.click(driver, btnNewRuleForManageRuleXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, headerNewRuleForManageRuleXpath);

				configureRuleSettings(siteName, ruleName, sitesRuleDescription, sitesRuleCriteriaIs, sitesRuleMimeType,
						destinationFolder);
			} else if (UIHelper.checkForAnElementbyXpath(driver, txtNoRulesForManageRuleXpath)) {

				UIHelper.click(driver, lnkCreateRulesForManageRuleXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, headerNewRuleForManageRuleXpath);

				configureRuleSettings(siteName, ruleName, sitesRuleDescription, sitesRuleCriteriaIs, sitesRuleMimeType,
						destinationFolder);

			}

			clickCreateButton();

			UIHelper.click(driver, finalLinkXpathForCreatedRule);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, ruleNameXpathInRightPane);
			} catch (Exception e) {
			}

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, ruleDeleteButtonXpath);
			} catch (Exception e) {
			}

			UIHelper.waitFor(driver);

			if (checkRuleNameInRulesPage(ruleName)) {
				report.updateTestLog("Create Rule", "User successfully created Rule <br><b>Rule Name</b>" + ruleName,
						Status.DONE);
			} else {
				report.updateTestLog("Create Rule", "User failed to create rule <br><b>Rule Name</b>" + ruleName,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create a rule by Clicking Create Rule/New Rule
	public void clickCreateRuleORNewRule(String siteName) {
		try {
			String sitesRuleName = dataTable.getData("Rule", "RuleName");
			// String finalXpathForCreatedRule =
			// tempXpathForCreatedRule.replace("CRAFT", sitesRuleName);
			String finalLinkXpathForCreatedRule = tempLinkXpathForCreatedRule.replace("CRAFT", sitesRuleName);

			WebElement bodyEle = UIHelper.findAnElementbyXpath(driver, bodyXpath);
			UIHelper.highlightElement(driver, bodyEle);
			UIHelper.mouseOveranElement(driver, bodyEle);

			/*
			 * WebElement ruleListEle = UIHelper.findAnElementbyXpath(driver,
			 * ruleListXpath); UIHelper.highlightElement(driver, ruleListEle);
			 * UIHelper.mouseOveranElement(driver, ruleListEle);
			 */

			// WebElement noRule = driver.findElement(By
			// .xpath(txtNoRulesForManageRuleXpath));

			if (UIHelper.checkForAnElementbyXpath(driver, btnNewRuleForManageRuleXpath)) {
				Thread.sleep(1000);
				UIHelper.waitForVisibilityOfEleByXpath(driver, btnNewRuleForManageRuleXpath);
				UIHelper.click(driver, btnNewRuleForManageRuleXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, headerNewRuleForManageRuleXpath);

				createRule(sitesRuleName);
			} else if (UIHelper
					.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, txtNoRulesForManageRuleXpath))) {

				UIHelper.click(driver, lnkCreateRulesForManageRuleXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, headerNewRuleForManageRuleXpath);

				createRule(sitesRuleName);

			}

			/*
			 * else { UIHelper.click(driver, finalLinkXpathForCreatedRule);
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * ruleDeleteButtonXpath); UIHelper.waitFor(driver); }
			 * 
			 */

			selectDestinationFolderPopUp(siteName);

			clickCreateButton();

			UIHelper.click(driver, finalLinkXpathForCreatedRule);
			UIHelper.waitForVisibilityOfEleByXpath(driver, ruleDeleteButtonXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Create Rule", "User successfully created Rule <br><b>Rule Name</b>" + sitesRuleName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createRule(String sitesRuleName) {
		try {
			UIHelper.sendKeysToAnElementByXpath(driver, txtboxNameForManageRuleXpath, sitesRuleName);

			String sitesRuleDescription = dataTable.getData("Rule", "RuleDescription");
			UIHelper.sendKeysToAnElementByXpath(driver, txtboxDescriptionForManageRuleXpath, sitesRuleDescription);

			String sitesRuleWhen = dataTable.getData("Rule", "RuleWhen");
			UIHelper.selectbyVisibleText(driver, selectWhenForManageRuleXpath, sitesRuleWhen);

			String sitesRuleAllCriteriaMet = dataTable.getData("Rule", "RuleAllCriteriaMet");
			UIHelper.selectbyVisibleText(driver, selectAllCriteriaMetForManageRuleXpath, sitesRuleAllCriteriaMet);
			UIHelper.waitFor(driver);

			String sitesRuleCriteriaIs = dataTable.getData("Rule", "RuleCriteriaIs");
			UIHelper.selectbyVisibleText(driver, selectIsForAllCriteriaMetInManageRuleXpath, sitesRuleCriteriaIs);
			UIHelper.waitFor(driver);

			String sitesRulePerformAction = dataTable.getData("Rule", "RulePerformAction");
			UIHelper.selectbyVisibleText(driver, selectPerformActionForManageRuleXpath, sitesRulePerformAction);
			UIHelper.waitFor(driver);

			String sitesRuleMimeType = dataTable.getData("Rule", "RuleMimeType");
			UIHelper.selectbyVisibleText(driver, selectMimeTypeForPerformActionInManageRuleXpath, sitesRuleMimeType);
			UIHelper.waitForVisibilityOfEleByXpath(driver, checkBoxRunAppliesToSubFoldersXpath);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, checkBoxRunAppliesToSubFoldersXpath));
			UIHelper.waitFor(driver);
			UIHelper.click(driver, btnSelectForPerformActionInManageRuleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectDestinationPopUpXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void configureRuleSettings(String siteName, String sitesRuleName, String sitesRuleDescription,
			String sitesRuleCriteriaIs, String sitesRuleMimeType, String destinationFolder) {
		try {
			UIHelper.sendKeysToAnElementByXpath(driver, txtboxNameForManageRuleXpath, sitesRuleName);

			String sitesRuleWhen = dataTable.getData("Rule", "RuleWhen");
			String sitesRuleAllCriteriaMet = dataTable.getData("Rule", "RuleAllCriteriaMet");
			String sitesRulePerformAction = dataTable.getData("Rule", "RulePerformAction");

			UIHelper.sendKeysToAnElementByXpath(driver, txtboxDescriptionForManageRuleXpath, sitesRuleDescription);
			UIHelper.selectbyVisibleText(driver, selectWhenForManageRuleXpath, sitesRuleWhen);

			UIHelper.selectbyVisibleText(driver, selectAllCriteriaMetForManageRuleXpath, sitesRuleAllCriteriaMet);
			UIHelper.waitFor(driver);
			UIHelper.selectbyVisibleText(driver, selectIsForAllCriteriaMetInManageRuleXpath, sitesRuleCriteriaIs);
			UIHelper.waitFor(driver);
			// selectAllCriteriaMetValues(sitesRuleAllCriteriaMet,
			// sitesRuleCriteriaIs);

			selectPerformActionValues(siteName, sitesRulePerformAction, sitesRuleMimeType, destinationFolder);

			UIHelper.waitForVisibilityOfEleByXpath(driver, checkBoxRunAppliesToSubFoldersXpath);
			UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, checkBoxRunAppliesToSubFoldersXpath));
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select all criteria met values
	public void selectAllCriteriaMetValues(String sitesRuleAllCriteriaMet, String sitesRuleCriteriaIs) {
		try {
			if (sitesRuleCriteriaIs.contains(",")) {
				String splittedRuleCriterials[] = sitesRuleCriteriaIs.split(",");

				if (splittedRuleCriterials != null) {
					int counter = 0;
					for (String ruleCriterialVal : splittedRuleCriterials) {
						String finalXpathForAddOrPlusButtonInCriteriaMetSec = tempXpathForAddOrPlusButtonInCriteriaMetSec
								.replace("CRAFT", "" + counter);
						String finalXpathForCriteriaMetDropdownval = tempXpathForCriteriaMetDropdownval.replace("CRAFT",
								"" + (counter + 1));
						String finalXpathForCriterialInCriteriaMet = tempXpathForCriterialInCriteriaMet.replace("CRAFT",
								"" + (counter + 1));

						if (counter != 0) {
							UIHelper.click(driver, finalXpathForAddOrPlusButtonInCriteriaMetSec);
							UIHelper.waitFor(driver);
							try {
								// UIHelper.waitForVisibilityOfEleByXpath(driver,
								// finalXpathForCriteriaMetDropdownval);
							} catch (Exception e) {
							}
						}

						if (counter == 0) {
							UIHelper.selectbyVisibleText(driver, selectAllCriteriaMetForManageRuleXpath,
									sitesRuleAllCriteriaMet);
							UIHelper.waitFor(driver);
							UIHelper.selectbyVisibleText(driver, selectIsForAllCriteriaMetInManageRuleXpath,
									ruleCriterialVal);
							UIHelper.waitFor(driver);
						} else {
							UIHelper.selectbyVisibleText(driver, finalXpathForCriteriaMetDropdownval,
									sitesRuleAllCriteriaMet);
							UIHelper.waitFor(driver);
							UIHelper.selectbyVisibleText(driver, finalXpathForCriterialInCriteriaMet, ruleCriterialVal);
							UIHelper.waitFor(driver);
						}

						counter++;
					}
				}
			} else {
				UIHelper.selectbyVisibleText(driver, selectAllCriteriaMetForManageRuleXpath, sitesRuleAllCriteriaMet);
				UIHelper.waitFor(driver);
				UIHelper.selectbyVisibleText(driver, selectIsForAllCriteriaMetInManageRuleXpath, sitesRuleCriteriaIs);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select perform action values
	public void selectPerformActionValues(String siteName, String sitesRulePerformAction, String sitesRuleMimeType,
			String destinationFolder) {
		try {
			if (sitesRuleMimeType.contains(",")) {
				String splittedRuleMimeTypes[] = sitesRuleMimeType.split(",");

				if (splittedRuleMimeTypes != null) {
					int counter = 0;
					for (String ruleMimeTypeVal : splittedRuleMimeTypes) {
						String finalXpathForAddOrPlusButtonInPerformActionSec = tempXpathForAddOrPlusButtonInPerformActionSec
								.replace("CRAFT", "" + counter);
						String finalXpathForPerformActoinDropdownval = tempXpathForPerformActoinDropdownval
								.replace("CRAFT", "" + (counter + 1));
						String finalXpathForMimeTypeInPerformAction = tempXpathForMimeTypeInPerformAction
								.replace("CRAFT", "" + (counter + 1));
						String finalXpathForSelectBtnForsiteFolder = tempXpathForSelectBtnForsiteFolder.replace("CRAFT",
								"" + (counter + 1));

						if (counter != 0) {
							UIHelper.click(driver, finalXpathForAddOrPlusButtonInPerformActionSec);
							UIHelper.waitFor(driver);
							try {
								// UIHelper.waitForVisibilityOfEleByXpath(driver,
								// finalXpathForPerformActoinDropdownval);
							} catch (Exception e) {
							}
						}

						if (counter == 0) {
							UIHelper.selectbyVisibleText(driver, selectPerformActionForManageRuleXpath,
									sitesRulePerformAction);
							UIHelper.waitFor(driver);
							UIHelper.selectbyVisibleText(driver, selectMimeTypeForPerformActionInManageRuleXpath,
									ruleMimeTypeVal);

							UIHelper.click(driver, btnSelectForPerformActionInManageRuleXpath);
							UIHelper.waitFor(driver);
							UIHelper.waitForVisibilityOfEleByXpath(driver, selectDestinationPopUpXpath);
							selectDestinationFolderPopUp(siteName, destinationFolder);
						} else {
							UIHelper.selectbyVisibleText(driver, finalXpathForPerformActoinDropdownval,
									sitesRulePerformAction);
							UIHelper.waitFor(driver);
							UIHelper.selectbyVisibleText(driver, finalXpathForMimeTypeInPerformAction, ruleMimeTypeVal);

							UIHelper.click(driver, finalXpathForSelectBtnForsiteFolder);
							UIHelper.waitFor(driver);
							UIHelper.waitForVisibilityOfEleByXpath(driver, selectDestinationPopUpXpath);
							selectDestinationFolderPopUp(siteName, destinationFolder);
						}

						counter++;
					}
				}
			} else {
				UIHelper.selectbyVisibleText(driver, selectPerformActionForManageRuleXpath, sitesRulePerformAction);
				UIHelper.waitFor(driver);
				UIHelper.selectbyVisibleText(driver, selectMimeTypeForPerformActionInManageRuleXpath,
						sitesRuleMimeType);

				UIHelper.click(driver, btnSelectForPerformActionInManageRuleXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, selectDestinationPopUpXpath);

				selectDestinationFolderPopUp(siteName, destinationFolder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select a destination folder
	public void selectDestinationFolderPopUp(String siteName) {
		try {
			String destinationFolder = dataTable.getData("Rule", "DestinationFolder");
			String finalXpathForSiteSelection = tempXpathForSiteSelection.replace("CRAFT", siteName);
			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteSelection);
			} catch (Exception e) {
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			UIHelper.click(driver, finalXpathForSiteSelection);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnOKInSelectDestinationPopUpXpath);
			UIHelper.waitFor(driver);
			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);
			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);

			UIHelper.waitFor(driver);
			Thread.sleep(1000);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnOKInSelectDestinationPopUpXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, btnOKInSelectDestinationPopUpXpath);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select a destination folder
	public void selectDestinationFolderPopUp(String siteName, String destinationFolder) {
		try {
			String finalXpathForSiteSelection = tempXpathForSiteSelection.replace("CRAFT", siteName);
			String finalXpathForDestFolder = tempXpathForDestFolder.replace("CRAFT", destinationFolder);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForSiteSelection);
			} catch (Exception e) {
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			UIHelper.click(driver, finalXpathForSiteSelection);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnOKInSelectDestinationPopUpXpath);
			UIHelper.waitFor(driver);
			WebElement destFolderContainerEle = UIHelper.findAnElementbyXpath(driver, destinationDailogXpath);
			UIHelper.highlightElement(driver, destFolderContainerEle);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForDestFolder);

			WebElement destFolderEle = UIHelper.findAnElementbyXpath(driver, finalXpathForDestFolder);
			UIHelper.highlightElement(driver, destFolderEle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", destFolderEle);

			UIHelper.waitFor(driver);
			Thread.sleep(1000);
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnOKInSelectDestinationPopUpXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, btnOKInSelectDestinationPopUpXpath);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click Create button
	public void clickCreateButton() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, btnCreateInManageRuleXpath);
			UIHelper.click(driver, btnCreateInManageRuleXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click Run Rules button
	public boolean clickRunRuleButton() {
		boolean isRanRuleSuccessfully = false;
		try {
			String runRulesValue = dataTable.getData("Rule", "RunRulesFor");
			String finalXpathRunRulesOption = tempXpathRunRulesOption.replace("CRAFT", runRulesValue);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, ruleNameXpathInRightPane);
			} catch (Exception e) {
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, btnRunRuleInManageRuleXpath);
			UIHelper.click(driver, btnRunRuleInManageRuleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathRunRulesOption);
			UIHelper.highlightElement(driver, finalXpathRunRulesOption);
			UIHelper.click(driver, finalXpathRunRulesOption);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, runningRuleConfirmMassageXpath);
			} catch (Exception e) {
			}

			try {
				try{
					if(UIHelper.checkForAnElementbyXpath(driver, runningRuleConfirmMassageXpath))
					{
						try {
							UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, runningRuleConfirmMassageXpath);
						} catch (Exception e) {
						}
					}
					UIHelper.waitForVisibilityOfEleByXpath(driver, successfulRunRuleConfirmMassageXpath);
				} catch (Exception e) {
				}
				
				if (UIHelper.checkForAnElementbyXpath(driver, successfulRunRuleConfirmMassageXpath)) {
					isRanRuleSuccessfully = true;
					report.updateTestLog("Run Rule For",
							"Sucessfully Ran Rule <br><b>Rule Rule For:</b>" + runRulesValue, Status.PASS);
				} else {
					isRanRuleSuccessfully = false;
					report.updateTestLog("Run Rule For",
							"Failure: could not run rules<br><b>Rule Rule For:</b>" + runRulesValue, Status.FAIL);
				}
			} catch (Exception e) {
				isRanRuleSuccessfully = false;
				report.updateTestLog("Run Rule For",
						"Failure: could not run rules<br><b>Rule Rule For:</b>" + runRulesValue, Status.FAIL);
			}

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageEleXpath);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isRanRuleSuccessfully;
	}

	// Check Rule name
	public boolean checkRuleNameInRulesPage(String ruleName) {
		boolean isDisplayedRuleName = false;
		try {

			String finalXpathForRuleName = tempXpathForRuleName.replace("CRAFT", ruleName);

			try {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// finalXpathForRuleName);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForRuleName)) {
				isDisplayedRuleName = true;
			} else {
				isDisplayedRuleName = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedRuleName;
	}
}