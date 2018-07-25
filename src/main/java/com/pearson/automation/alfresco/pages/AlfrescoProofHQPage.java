package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoProofHQPage extends ReusableLibrary {

	private String gotoProofBtnXpath = ".//table//tr/td/a[contains(.,'proof')]";
	private String alsoDeleteProofFromProofHQChkBoxXpath = ".//*[contains(@id,'default-removeProof_proofHqDeleteProofFromProofHQ-entry')]";
	private String okBtnXpathInRemoveProofHQPopup = ".//*[contains(@id,'default-removeProof-form-submit-button')]";
	private String messageEleXpath = ".//*[@id='message']/div";
	
	//configuration ProofHQ
	
	private String emailXpath = ".//*[contains(@id,'default-email-address')]";
	private String tokenXpath = ".//*[contains(@id,'default-authtoken')]";
	private String actcheckXpath = ".//*[contains(@id,'default-active')]";
	private String btnOKXpath = ".//*[contains(@id,'default-button-ok-button')]";
	
	public String mailsharelink = ".//div[3]/a/span[text()='proof']";

	public AlfrescoProofHQPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Navigate to shared folder through mail link
	public void navigateToProofHQPage(String fileOrFolderName) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, gotoProofBtnXpath);
			UIHelper.click(driver, gotoProofBtnXpath);
			UIHelper.waitFor(driver);
			ArrayList<String> tabs2 = new ArrayList<String>(
					driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			Thread.sleep(25000);

			if (driver.getTitle().trim().contains(fileOrFolderName)) {
				report.updateTestLog("Navigate to ProofHQ page",
						"Navigated to ProofHQ page successfully <br><b>ProofHQ page title:</b>"
								+ fileOrFolderName, Status.PASS);
			} else {
				report.updateTestLog("Navigate to ProofHQ page",
						"Failed to navigate to ProofHQ page<br><b>ProofHQ page title:</b>"
								+ fileOrFolderName, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Navigate to ProofHQ page",
					"Failed to navigate to ProofHQ page", Status.FAIL);
		}

	}

	// Click on 'Ok' button in Remove from ProofHQ popup
	public void clickOnOkBtnInRemoveFromProofHQPopup(
			String isReqToChkAlsoDeleteProof) {
		try {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						okBtnXpathInRemoveProofHQPopup);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (UIHelper.checkForAnElementbyXpath(driver,
					okBtnXpathInRemoveProofHQPopup)) {
				if (isReqToChkAlsoDeleteProof.equalsIgnoreCase("Yes")) {
					UIHelper.click(driver,
							alsoDeleteProofFromProofHQChkBoxXpath);
					UIHelper.highlightElement(driver,
							okBtnXpathInRemoveProofHQPopup);
					UIHelper.click(driver, okBtnXpathInRemoveProofHQPopup);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageEleXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageEleXpath);
					UIHelper.waitFor(driver);
				} else {
					UIHelper.highlightElement(driver,
							okBtnXpathInRemoveProofHQPopup);
					UIHelper.click(driver, okBtnXpathInRemoveProofHQPopup);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageEleXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageEleXpath);
					UIHelper.waitFor(driver);
				}

				report.updateTestLog(
						"Click on 'Ok' button in Remove from ProofHQ",
						"User able to click the 'Ok' button", Status.PASS);
			} else {
				report.updateTestLog(
						"Click on 'Ok' button in Remove from ProofHQ",
						"User failed to click on 'Ok' button", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Configuration Of Proof HQ account for User
	public void configProofHQ(String email, String Token) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, emailXpath);
			UIHelper.findAnElementbyXpath(driver, emailXpath).clear();
			UIHelper.highlightElement(driver, emailXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, emailXpath, email);
			UIHelper.highlightElement(driver, tokenXpath);
			UIHelper.findAnElementbyXpath(driver, tokenXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, tokenXpath, Token);
			UIHelper.highlightElement(driver, actcheckXpath);
			
			if(UIHelper.findAnElementbyXpath(driver, actcheckXpath).isSelected()){
				
				
				
			}else{
				UIHelper.waitFor(driver);
				UIHelper.click(driver, actcheckXpath);
				UIHelper.waitFor(driver);
			}
			//UIHelper.findAnElementbyXpath(driver, actcheckXpath).clear();
			
			UIHelper.highlightElement(driver, btnOKXpath);
			UIHelper.click(driver, btnOKXpath);
			report.updateTestLog("ProofHQ page Configuration",
					"successful configuration of  ProofHQ of user", Status.PASS);
	//		UIHelper.waitForVisibilityOfEleByXpath(driver, messageEleXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					messageEleXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					messageEleXpath);
			UIHelper.waitFor(driver);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("ProofHQ page Configuration",
					"Failed to configure ProofHQ of user", Status.FAIL);
		}

	}
}
