package com.pearson.automation.alfresco.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * 
 * @author 412766
 * 
 */
public class AlfrescoMySitesPage extends ReusableLibrary {

	private String mySitesListPanelXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'_user-sites_x002e_user-sites_x0023_default-body')]/div";
	private String mySitesListXapth = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'_user-sites_x002e_user-sites_x0023_default-body')]//li/p/a";
	private String activityFeedButtonListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'_user-sites_x002e_user-sites_x0023_default-body')]//li/div//span//span//button";
	private String trashCanXpath = "//*[@class='members-bar-links']//a[contains(text(),'Trashcan')]";
	private String tempconfigMenuXpath = "//*[@class='members-bar-links']//a[contains(text(),'CRAFT')]";

	// date of last file in trash can
	private String trashCanNextBtnXpath = ".//*[contains(@id,'more-button-button')]";
	private String trashCanPrevBtnXpath = ".//*[contains(@id,'less-button-button')]";
	private String trashCanNoItemXpath = ".//*div[contains(.,'No items exist')]";
	private String trashCanListXpath = ".//*[@class='content']//*[@class='yui-dt-data']//tr//td[3]//div//div[2]";
	private String trashCanDateListXpath = ".//*[contains(@class,'col-description')]//div//div[2]";

	private String siteFinderListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='finder-wrapper']//tbody//tr//td[2]//div//h3/a";
	private String siteJoinListButtonXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='finder-wrapper']//tbody//tr//td[3]//div//span//button[text()='Join']";


	public AlfrescoMySitesPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isMySitesListAvaialable(List<String> siteNames) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					mySitesListPanelXpath);
			UIHelper.highlightElement(driver, mySitesListPanelXpath);

			List<WebElement> mySitesList = UIHelper.findListOfElementsbyXpath(
					mySitesListXapth, driver);
			for (WebElement webElement : mySitesList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					UIHelper.highlightElement(driver, webElement);
					siteNames.add(webElement.getText()
							.replace("Disable Activity Feeds", "").trim());
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param siteName
	 */
	public void disableActivityFeeds(String siteName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					mySitesListPanelXpath);
			UIHelper.highlightElement(driver, mySitesListPanelXpath);

			List<WebElement> mySitesList = UIHelper.findListOfElementsbyXpath(
					mySitesListXapth, driver);
			List<WebElement> activityFeedList = UIHelper
					.findListOfElementsbyXpath(activityFeedButtonListXpath,
							driver);
			int index = 0;
			for (WebElement webElement : mySitesList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					if (webElement.getText().contains(siteName)) {
						UIHelper.highlightElement(driver, webElement);
						if (activityFeedList.get(index).getText()
								.contains("Disable Activity Feeds")) {
							UIHelper.clickanElement(activityFeedList.get(index));
							UIHelper.waitFor(driver);
							UIHelper.highlightElement(driver,
									activityFeedList.get(index));
						} else {
							UIHelper.highlightElement(driver,
									activityFeedList.get(index));
						}
						UIHelper.waitFor(driver);
						flag = true;
						break;
					}
				}

				index++;
			}

			if (flag) {
				report.updateTestLog("Disable 'Activity Feeds' in My Sites",
						"Disabled successfully" + "<br /><b> Site Name : </b>"
								+ siteName, Status.PASS);
			} else {
				report.updateTestLog("Disable 'Activity Feeds' in My Sites",
						"Disabled Failed" + "<br /><b> Site Name : </b>"
								+ siteName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Disable 'Activity Feeds' in My Sites",
					"Disable 'Activity Feeds' in My Sites Failed", Status.FAIL);
		}
	}

	public void navigateToTrashCan() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, trashCanXpath);
			UIHelper.click(driver, trashCanXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {

		}

	}

	public String getDateOfLastContent() {
		String dateText = null;
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, trashCanListXpath);

			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					trashCanNextBtnXpath));

			while (UIHelper.findAnElementbyXpath(driver, trashCanNextBtnXpath)
					.isEnabled()) {
				UIHelper.highlightElement(driver, trashCanNextBtnXpath);
				UIHelper.click(driver, trashCanNextBtnXpath);
				/* UIHelper.waitForPageToLoad(driver); */
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				/* System.out.println("PASS"); */

			}
			List<WebElement> List = UIHelper.findListOfElementsbyXpath(
					trashCanDateListXpath, driver);
			System.out.println(List.size());
			for (WebElement element : List) {
				dateText = element.getText();

			}
			return dateText;
		} catch (Exception e) {
			e.printStackTrace();
			return dateText;
		}

	}

	/**
	 * @author 412766
	 */
	public void joinSiteBySiteFinder(String siteNameToCheck){
		try{
			boolean flag = false;
			List<WebElement> siteNameList = UIHelper.findListOfElementsbyXpath(siteFinderListXpath, driver);
			List<WebElement> siteJoinButtonList = UIHelper.findListOfElementsbyXpath(siteJoinListButtonXpath, driver);
			
			int index = 0;
			for (WebElement webElement : siteNameList) {
				if(webElement.getText().equalsIgnoreCase(siteNameToCheck)){
					UIHelper.highlightElement(driver, webElement);
					UIHelper.highlightElement(driver, siteJoinButtonList.get(index));
					if(UIHelper.isWebElementDisplayed(webElement)){
						flag = true;
						UIHelper.waitFor(driver);
						UIHelper.clickanElement(siteJoinButtonList.get(index));
						UIHelper.waitFor(driver);
						break;
					}else{
						flag = false;
					}
				}
				index++;
			}
			
			if(flag){
				report.updateTestLog("Verify user joined the site by site finder",
						"User joined successfully" + "<br /><b> Site Name : </b>"
								+ siteNameToCheck, Status.PASS);
			}else{
					report.updateTestLog("Verify user joined the site by site finder",
							"User not joined" + "<br /><b> Site Name : </b>"
									+ siteNameToCheck, Status.FAIL);
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void navigatetoconfigMenu(String menuheader) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalconfigMenuXpath = tempconfigMenuXpath.replace("CRAFT", menuheader);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalconfigMenuXpath);
			UIHelper.highlightElement(driver, finalconfigMenuXpath);
			UIHelper.click(driver, finalconfigMenuXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			
			report.updateTestLog("Navigate to " + menuheader, "Navigate to " + menuheader + " passed", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Navigate to " + menuheader, "Navigate to " + menuheader + " failed", Status.FAIL);
		}

	}

}
