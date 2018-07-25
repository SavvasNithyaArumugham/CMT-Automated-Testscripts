package com.pearson.automation.alfresco.pages;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoMaintenanceActivitesPage extends ReusableLibrary {

	private String siteMenuXpath = ".//*[@id='HEADER_SITES_MENU']";
	private String findSiteXpath = ".//*[@id='HEADER_SITES_MENU_SITE_FINDER_text']";
	private String searchBoxXpath = ".//*[@class='search-term']";
	private String siteNameListXpath = ".//*[@class='sitename']/a";
	protected ArrayList<String> siteResultList = new ArrayList<String>();
	private String searchButtonXpath = ".//*[@class='sticky-wrapper']//*[@class='search-button']//*[contains(.,'Search')]";
	private String searchingXpath = ".//*[@id='message']/div/span";
	private String deleteSiteFirstPrompt = "//div[@id='prompt']";
	private String deleteSiteYesButtonXpath = "//div[@id='prompt']//*[contains(button,'Yes')]";
	private String promptDeleteButtonXpath = "//div[@id='prompt']//button[contains(text(),'Delete')]";
	private String siteNamesFilePath = "src/test/resources/AppTestData/TestOutput/SitesList.txt";
	private String messageXpath = ".//*[@id='message']/div";
	private String siteCouldNotDeletedMsgXpath = ".//*[@id='message']/*[@class='bd']/*[text()='Site could not be deleted']";
	private String specificsiteNamesFilePath = "src/test/resources/AppTestData/TestOutput/SpecificitesList.txt";
	private String[] linesOfDataInTxtFile;

	private String deleteButtonXpath = "//*[@class='results yui-dt']//tr[CRAFT]//button[text()='Delete']";
	private String trashSearchBoxXpath = "//*[@class='search-text toolbar-widget']/input";
	private String trashSearchButtonXpath = "//button[contains(text(),'Search')]";
	private String trashDeleteButtonXpath = "//table[contains(@id,'yuievtautoid')]//tbody[2]//tr[CRAFT]/td[4]//button[contains(text(),'Delete')]";
	private String trashSitesCheckBoxXpath = "//table[contains(@id,'yuievtautoid')]//tbody[2]//tr/td[1]//input[@type='checkbox']";
	private String trashSitesTableRowsXpath = "//table[contains(@id,'yuievtautoid')]//tbody[@class='yui-dt-data']//tr";
	private String trashSiteslistXpath = "//table[contains(@id,'yuievtautoid')]//tbody[2]//tr/td[3]/div/div[1][@class='name']";
	private String trashAllDeleteButtonsXpath = "//table[contains(@id,'yuievtautoid')]//tbody[2]//tr/td[4]//button[contains(text(),'Delete')]";
	private String okButtonXpath = "//div[@id='prompt']//button[contains(text(),'OK')]";
	private String tableFirstRowDeleteBtnXpth = "//table[contains(@id,'yuievtautoid')]//tbody[@class='yui-dt-data']//tr[1]/td//button[contains(.,'Delete')]";
	private String deleteConfirmMsgXpath = ".//*[@id='message']//span[contains(.,'Successfully deleted')]";
	private String DeletedSiteFilePath = "C:/DeleteSitesFolder/DeletedSites.txt";
	private String nonDeletedSiteFilePath = "C:/DeleteSitesFolder/nonDeletedSites.txt";
	private String fetchdesc = ".//a[text()='CRAFT']//following::div[1][@class='sitedescription']";
	
	

	public String errorpage = ".//*[@id='Share']//b[text()='Error Message:']";
	public AlfrescoMaintenanceActivitesPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void deleteSitesFromSiteFinder() {

		try {

			linesOfDataInTxtFile = new FileUtil()
					.readSetOFDataFromFile(siteNamesFilePath);
			int numberOfLines = linesOfDataInTxtFile.length;
			
			
			for (int i = 0; i < numberOfLines; i++) {
				System.out.println(linesOfDataInTxtFile[i]);
				SiteNameInSiteFinderResults(linesOfDataInTxtFile[i]);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// Check created site in Site Finder Results
	public void SiteNameInSiteFinderResults(String siteName) {

		try {	
			
			File dir = new File("C:/DeleteSitesFolder");
			dir.mkdir();
			
			DateFormat df = new SimpleDateFormat("dd/MM/yy");
			Date dateobj = new Date();				
			String deletedSitesName= "DeletedSites"+df.format(dateobj);
			deletedSitesName =deletedSitesName.replace("/", "");
			String finalDeletedSiteFilePath = DeletedSiteFilePath.replace("DeletedSites", deletedSitesName);
			String finalNonDeletedSiteFilePath = nonDeletedSiteFilePath.replace("DeletedSites", deletedSitesName);
			System.out.println(finalDeletedSiteFilePath+"\n"+finalNonDeletedSiteFilePath);
			UIHelper.waitFor(driver);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchBoxXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, searchBoxXpath,
					siteName);
			Thread.sleep(2000);
			UIHelper.click(driver, searchButtonXpath);
			Thread.sleep(2000);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			siteResultList.clear();
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, searchingXpath);
			UIHelper.findandAddElementsToaList(driver, siteNameListXpath,
					siteResultList);
			System.out.println(siteResultList.size());
			Thread.sleep(10000);
			if (siteResultList.size() > 0) {
				System.out.println(siteResultList.size());
				int i = 1;
				for (String sites : siteResultList) {
					String toString = Integer.toString(i);
					
					String finalfetchdesc = fetchdesc.replace(
							"CRAFT", sites);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalfetchdesc);
					if(UIHelper.getTextFromWebElement(driver, finalfetchdesc).equals(siteName)) {
					String findalDeleteButtonXpath = deleteButtonXpath.replace(
							"CRAFT", toString);
					WebElement deleteButton = driver.findElement(By
							.xpath(findalDeleteButtonXpath));
					UIHelper.highlightElement(driver, deleteButton);
					UIHelper.clickanElement(deleteButton);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							deleteSiteFirstPrompt);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							promptDeleteButtonXpath);
					UIHelper.click(driver, promptDeleteButtonXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							deleteSiteYesButtonXpath);
					UIHelper.click(driver, deleteSiteYesButtonXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
					Thread.sleep(2500);
					String getMessage = UIHelper.getTextFromWebElement(driver,
							messageXpath);
					if(getMessage.equalsIgnoreCase("Site is being deleted"))
					{
						Thread.sleep(600);
						getMessage = UIHelper.getTextFromWebElement(driver,
								messageXpath);
						
						if(getMessage.equalsIgnoreCase("Site is being deleted"))
						{
							Thread.sleep(400);
							getMessage = UIHelper.getTextFromWebElement(driver,
									messageXpath);
							
							if(getMessage.equalsIgnoreCase("Site is being deleted"))
							{
								Thread.sleep(400);
								getMessage = UIHelper.getTextFromWebElement(driver,
										messageXpath);
							}
						}
					}
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					System.out.println(getMessage);
					if (getMessage
							.equalsIgnoreCase("Site could not be deleted")) {
						new FileUtil().appendTextToFile(sites+"\n", finalNonDeletedSiteFilePath);						
						System.out.println(i);
						i++;						
						
					}
					else{
						new FileUtil().appendTextToFile(sites+"\n", finalDeletedSiteFilePath);						
					}
					if (i > siteResultList.size()) {
						break;
					}

				}

			
				}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteSitesFromTrash() {

		try {

			linesOfDataInTxtFile = new FileUtil()
					.readSetOFDataFromFile(siteNamesFilePath);
			int numberOfLines = linesOfDataInTxtFile.length;

			for (int i = 0; i < numberOfLines; i++) {
				System.out.println(linesOfDataInTxtFile[i]);
				searchSiteNameInTrashResults(linesOfDataInTxtFile[i]);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void searchSiteNameInTrashResults(String siteName) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, trashSearchBoxXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, trashSearchBoxXpath,
					siteName);
			Thread.sleep(1000);
			UIHelper.click(driver, trashSearchButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					tableFirstRowDeleteBtnXpth);
			UIHelper.waitFor(driver);
			List<WebElement> getArrayMembers = driver.findElements(By
					.xpath(trashSitesTableRowsXpath));

			System.out.println(getArrayMembers.size());
			if (getArrayMembers.size() > 0) {
				int i = 1;
				for (WebElement sites : getArrayMembers) {
					String toString = Integer.toString(i);
					String findalDeleteButtonXpath = trashDeleteButtonXpath
							.replace("CRAFT", toString);
					WebElement deleteButton = driver.findElement(By
							.xpath(findalDeleteButtonXpath));
					UIHelper.clickanElement(deleteButton);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							deleteSiteFirstPrompt);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							okButtonXpath);
					boolean isDisplayed = UIHelper.isWebElementDisplayed(driver
							.findElement(By.xpath(okButtonXpath)));
					UIHelper.click(driver, okButtonXpath);
					// UIHelper.waitForVisibilityOfEleByXpath(driver,
					// deleteConfirmMsgXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					if (isDisplayed) {
						List<WebElement> getPresentNumberofSites = driver
								.findElements(By
										.xpath(trashSitesTableRowsXpath));
						// i++;

						if (getPresentNumberofSites.size() == 0) {
							break;
						}

					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Delete site with parameter
	public void deleteSpecSitesFromSiteFinder() {

		try {

			linesOfDataInTxtFile = new FileUtil()
					.readSetOFDataFromFile(specificsiteNamesFilePath);
			int numberOfLines = linesOfDataInTxtFile.length;

			for (int i = 0; i < numberOfLines; i++) {
				System.out.println(linesOfDataInTxtFile[i]);
				SiteNameInSiteFinderResults(linesOfDataInTxtFile[i]);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
