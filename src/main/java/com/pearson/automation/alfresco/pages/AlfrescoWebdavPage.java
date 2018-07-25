package com.pearson.automation.alfresco.pages;

import java.io.File;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

public class AlfrescoWebdavPage extends ReusableLibrary {

	private String listingTableXpath = "//*[@class='listingTable']";
	private String sitesMenuLinkXpath = "//*[@class='listingTable']//a[contains(text(),'Sites')]";
	private String siteXpath = "//*[@class='listingTable']//tr//td/a[contains(.,'CRAFT')]";
	private String documentLibraryXpath = "//*[@class='listingTable']//a[contains(.,'documentLibrary')]";
	private String fileOrfolderXpath = "//*[@class='listingTable']//a[contains(.,'CRAFT')]";

	public AlfrescoWebdavPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// To find Linked file in Webdav Page
	public void navigateToSite() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, listingTableXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitesMenuLinkXpath);
			UIHelper.highlightElement(driver, sitesMenuLinkXpath);
			UIHelper.click(driver, sitesMenuLinkXpath);
			UIHelper.waitForPageToLoad(driver);
			String siteName = dataTable.getData("Sites", "SiteName")
					.toLowerCase();
			String siteNameElement = siteXpath.replace("CRAFT", siteName);
//			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
//					siteNameElement));
			UIHelper.highlightElement(driver, siteNameElement);
			UIHelper.click(driver, siteNameElement);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Navigate to Site",
					"Navigated to Site Page Sucessfully<br>"
							+ "<b>Site Name:</b>" + siteName, Status.DONE);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Navigate to Site",
					"User unable to navigate to Site", Status.FAIL);
			e.getMessage();
		}

	}
	public void navigateToSite(String siteName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, listingTableXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitesMenuLinkXpath);
			UIHelper.highlightElement(driver, sitesMenuLinkXpath);
			UIHelper.click(driver, sitesMenuLinkXpath);
			UIHelper.waitForPageToLoad(driver);
//			String siteName = dataTable.getData("Sites", "SiteName")
//					.toLowerCase();
			String siteNameElement = siteXpath.replace("CRAFT", siteName);
//			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
//					siteNameElement));
			UIHelper.highlightElement(driver, siteNameElement);
			UIHelper.click(driver, siteNameElement);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Navigate to Site",
					"Navigated to Site Page Sucessfully<br>"
							+ "<b>Site Name:</b>" + siteName, Status.DONE);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Navigate to Site",
					"User unable to navigate to Site", Status.FAIL);
			e.getMessage();
		}

	}
	public void navigateToDocumentLibrary() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, listingTableXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
			UIHelper.highlightElement(driver, documentLibraryXpath);
			UIHelper.click(driver, documentLibraryXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Navigate to Document Library",
					"Navigated to Document Library page Sucessfully<br>"
							+ "<b>Page Name:</b> Document Library", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Navigate to Document Library",
					"User Unable to navigate to document library", Status.FAIL);
			e.getMessage();
		}

	}

	public void navigateToFolder(String folderName) {
		try {
			String folderNameVal = fileOrfolderXpath.replace("CRAFT",
					folderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, folderNameVal);
			UIHelper.highlightElement(driver, folderNameVal);
			UIHelper.click(driver, folderNameVal);
			UIHelper.waitForPageToLoad(driver);

			report.updateTestLog("Navigate to Folder",
					"Navigated to Folder Sucessfully<br>"
							+ "<b>Folder Name:</b>" + folderName, Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Navigate to Folder",
					"User Unable to Navigate to Folder", Status.FAIL);
			e.getMessage();
		}

	}

	public void findFileOrFolder(String fileName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			String fileOrfolderNameVal = fileOrfolderXpath.replace("CRAFT",
					fileName);
			if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(
					driver, fileOrfolderNameVal))) {
				UIHelper.highlightElement(driver, fileOrfolderNameVal);
				flag = true;
			} else
				flag = false;
			if (flag) {
				report.updateTestLog("Find File or Folder",
						"File/Folder found<br>"
								+ "<b>Expected:</b>File/Folder Name:"
								+ fileName + "<br><b>Actual Result:</b>"
								+ fileName, Status.PASS);
			} else {
				report.updateTestLog("Find File or Folder",
						"File/Folder not found<br>"
								+ "<b>Expected:</b>File/Folder Name:"
								+ fileName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Find file or Folder",
					"File/Folder not found", Status.FAIL);
			e.getMessage();
		}

	}

	public void downloadFile(String actualFileorFolder) {
		try {
			String actualFileorFolderXpath = fileOrfolderXpath.replace("CRAFT",
					actualFileorFolder);
			UIHelper.highlightElement(driver, actualFileorFolderXpath);
			UIHelper.click(driver, actualFileorFolderXpath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String downloadedFilePath = Settings.getInstance().getProperty(
					"DefaultDownloadPath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			File downloadFile = new File(downloadedFilePath + "/" + fileName);
			while (true) {
				if (downloadFile.exists()) {
					break;
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Download a file", "Failed to download",
					Status.FAIL);
			e.getMessage();
		}
	}

}
