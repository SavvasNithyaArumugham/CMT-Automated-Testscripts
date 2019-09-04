package com.pearson.automation.alfresco.pages;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class AlfrescoEPSPage extends ReusableLibrary {

	// private String batchPublishPrompt="//*[contains(text(),'Batch
	// Publish')]";
	private String batchPublishPrompt = "//*[@id='prompt']";
	// private String batchPublishPromptFilesList="//*[contains(text(),'Batch
	// Publish')]//following-sibling::div//*[@class='toolbar-file-list']";
	private String batchPublishPromptFilesList = "//*[@id='prompt']//div[@class='toolbar-file-list']";
	public String batchPublishButton = "//button[text()='Batch-Publish']";

	public String publishinstitute = "//table[@class='spaces']/tbody//td[2]/b[text()='CRAFT']";
	public String publishactionbut = "//table[@class='spaces']/tbody//td[2]/b[text()='CRAFT']//ancestor::tr//img[@id='pubActionButton']";

	private String publishedHistoryXapth = ".//*[contains(@class,'yui-overlay yui-panel')]/div/form/div/table/tbody";
	private String pubHisCloseButtonXapth = ".//input[@value='Close']";
	public String sitenotconfiguredxpath = "//*[contains(text(),'Site is not configured for publication')]";
	public String sitenotcongiCloseXpath = "//button[@type='button' and contains(text(),'Close')]";
	private String epspublishopwindowXpath = "//*[contains(text(),'EPS Publishing Status')]";
	public String publishbutton = "//*[contains(text(),'CRAFT')]/following::img[1][@title='publish']";
	public String publishinprogressXpath = "//*[@title='indicators.pearson.publishing']";
	private String indicationprogressXpath = "(//*[normalize-space()='CRAFT']//preceding::img[@title='indicators.pearson.published'])[last()]";
	public String deletedocumentXpath = "//button[contains(text(),'Delete')]";
	private String ifyoupublishXpath = ".//*[text()='if you publish']";
	public String ifyoupublishDropdownxpath = ".//select[@id='pubFromCombo']";
	private String toXpath = ".//*[text()='to']";
	public String todropdownXpath = ".//select[@id='instNameCombo']";
	private String thethedeliveryXpath = ".//*[text()='then the Delivery URI will be']";
	public String deliveryURLXpath = "//*[@id='deliveryUrl']";
	private String documentsXpath = "//div[@class='documents yui-dt']//tbody[@class='yui-dt-data']";
	public String GetTypeofFileXpath = "//div[@class='documents yui-dt']//tbody[@class='yui-dt-data']/tr[CRAFT]/td[contains(@class,'thumbnail')]//span";
	public String GetFileNameXpath = "//div[@class='documents yui-dt']//tbody[@class='yui-dt-data']/tr[CRAFT]/td[contains(@class,'fileName')]//span[2]/a";
	public String batchPublishInstXpath = "//select[@id='institutionCombo']";
	public String batchPublishtheSelectedOptionsXpath = "//*[text()='Publish the selected objects to : ']";
	public String batchPublishFileNames = "//*[@class='toolbar-file-list']";
	public String batchPublishSelectedFilezipfiles = "//*[text()='Selected files contains Zip files. Please select the checkbox to publish those as zip itself.']";
	public String batchPublishcancelbutton = "//*[@id='prompt']//button[text()='Cancel']";
	public String batchPublishdocumentsXpath = "//*[@class='documents yui-dt']";
	public String playerhtmlXpath = "//*[contains(text(),'CRAFT/player.html')]";
	public String zipXpath = "//*[contains(text(),'CRAFT')]";
	public String zipuploadxpath = "//input[@id='zipUpload']";
	public String publicationurldynamic = "//table[@class='spaces']//tr[2]/td[2]/b[contains(text(),'CRAFT')]/following::td[2]//font";
	public String batchqueuedXpath = "//*[contains(text(),'Batch queued successfully for publishing')]";
	public String foldernameofdoc = "//a[contains(text(),'CRAFT') and @class='filter-change']";
	public String URLLinkXpath = "//*[@class='spaces']//*[contains(text(),'CRAFT')]/following::a[TYPE]";
	public String filecontentXpath = "//*[contains(text(),'CRAFT')]";
	public String filealreadypublished = "//*[contains(text(),'This file is within the folder')]";
	public String fileisavilable = "//div[@class='documents yui-dt']//a[contains(text(),'CRAFT')]";
	public String publishrownumber = "//table[@class='spaces']/tbody/tr[CRAFT]/td[6]/*[@id='pubActionButton']";
	public String epsreviewchangesXpath = "//*[text()='CRAFT']";
	public String closexpath = "//*[@value='Close']";
	public String notfoundxpath = "//*[contains(text(),'Not Found')]";
	public String cantdeletxpath = "//*[contains(text(),'Please delete the published children first, and then delete this folder')]";
	public String filepublishinprogress = "//h3[@class='filename']//a[contains(.,'CRAFT')]//ancestor::tr//div[@class='status']/img[@title='indicators.pearson.publishing']";
	public String publishbox = "//table[@class='spaces']/tbody";
	public String publishhistoryrows = ".//div[contains(@style,'overflow-y:')]//tr[CRAFT]/td";
	// indicators.pearson.published,indicators.pearson.publishing,pearson-two-inst-change-updated,pearson-two-inst-published
	public String filepublishstatus = "//h3[@class='filename']//a[contains(.,'CRAFT')]//ancestor::tr//div[@class='status']/img[contains(@title,'REPLACE') and contains(@alt,'EPS')]";
	public String cancelButtonXpath=".//span[contains(@class,'push-button default')]//button";
	public String deleteButtonXpath="//div[@class='ft']//span[@class='yui-button yui-push-button']//button";
	public AlfrescoEPSPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public boolean isFilesListMatched(String fileName) {
		boolean matched = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, batchPublishPrompt);
			UIHelper.waitForVisibilityOfEleByXpath(driver, batchPublishPromptFilesList);
			String listOfFiles = UIHelper.getTextFromWebElement(driver, batchPublishPromptFilesList);
			String splitStr[] = listOfFiles.trim().split("\\s+");

			for (String fileNameVal : splitStr) {
				System.out.println(fileNameVal);
			}

			String splittedFileNames[] = fileName.split(",");

			for (String fileNameVal : splittedFileNames) {
				System.out.println(fileNameVal);
			}

			Arrays.sort(splitStr);
			Arrays.sort(splittedFileNames);

			if (Arrays.equals(splitStr, splittedFileNames)) {
				matched = true;
			}
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return matched;
	}

	// Click Batch Publish Option
	public void clickBatchPublishButton() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, batchPublishPrompt);
			UIHelper.click(driver, batchPublishButton);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click Batch Publish", "Sucessfully clicked on Batch Publish button", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Click Batch Publish", "Unable to click on Batch Publish button", Status.FAIL);
		}

	}

	// Click Batch Publish Option with out wait
	public void clickBatchPublishButtonWithoutWait() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, batchPublishPrompt);
			UIHelper.click(driver, batchPublishButton);
			report.updateTestLog("Click Batch Publish", "Sucessfully clicked on Batch Publish button", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Click Batch Publish", "Unable to click on Batch Publish button", Status.FAIL);
		}

	}

	/**
	 * @author 412766
	 * @return boolean
	 */
	public boolean isPublishedHistoryDisplayed() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, publishedHistoryXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, publishedHistoryXapth))) {
				UIHelper.highlightElement(driver, publishedHistoryXapth);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 */
	public void clickPubHisCloseButton() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pubHisCloseButtonXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, pubHisCloseButtonXapth))) {
				UIHelper.highlightElement(driver, pubHisCloseButtonXapth);
				report.updateTestLog("Click Close Button", "Closed Sucessfully", Status.PASS);
				UIHelper.click(driver, pubHisCloseButtonXapth);
			} else {
				report.updateTestLog("Click Close Button", "Not able to Close", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 656287
	 */
	public void Sitenotconfiguredandclose() {

		try {
			UIHelper.waitForAnElement(driver, UIHelper.findAnElementbyXpath(driver, sitenotconfiguredxpath));
			Boolean flag = UIHelper
					.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, sitenotconfiguredxpath));
			if (flag) {
				report.updateTestLog("Site not configured Message:",
						"<b> 'Site is not configured for publishing' is displayed", Status.PASS);
				// UIHelper.waitForAnElement(driver,
				// UIHelper.findAnElementbyXpath(driver,
				// sitenotcongiCloseXpath));
				try {
					Boolean flag1 = UIHelper
							.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, sitenotcongiCloseXpath));
					UIHelper.click(driver, sitenotcongiCloseXpath);
				} catch (Exception e) {
					UIHelper.click(driver, batchPublishcancelbutton);
				}

			} else {
				report.updateTestLog("Site not configured Message:",
						"<b> 'Site is not configured for publishing' is not displayed", Status.FAIL);

			}

		} catch (Exception e) {

		}

	}

	public void Publish(String Institution, String folderfileName, String PublishTimes) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, "//table[@class='spaces']/tbody");
			String finalpublishactionbut = publishactionbut.replace("CRAFT", Institution);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpublishactionbut);
			UIHelper.highlightElement(driver, finalpublishactionbut);
			UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, finalpublishactionbut));
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			//
			// String actionbutton = publishbutton.replace("CRAFT",
			// Institution);
			// UIHelper.javascriptClick(driver,
			// UIHelper.findAnElementbyXpath(driver, actionbutton));

			/*
			 * WebElement table =
			 * driver.findElement(By.xpath("//table[@class='spaces']/tbody"));
			 * List<WebElement> tr = table.findElements(By.tagName("tr")); int
			 * Sizeofrow = tr.size(); System.out.println(Sizeofrow); for(int
			 * i=Sizeofrow;i>1;i--){ String row=Integer.toString(i); String
			 * publish = publishrownumber.replace("CRAFT", row); Boolean flag
			 * =UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
			 * driver, publish)); if(flag){ UIHelper.javascriptClick(driver,
			 * UIHelper.findAnElementbyXpath(driver, publish)); i=0; }
			 * 
			 * }
			 */

			try {
				for (int i = 0; i < 10; i++) {

					UIHelper.pageRefresh(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					// UIHelper.waitForLong(driver);

					if (!UIHelper
							.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, publishinprogressXpath))) {
						break;
					}

				}
			} catch (Exception e) {
				String publishedicon = indicationprogressXpath.replace("CRAFT", folderfileName);
				String publishtimes = driver.findElement(By.xpath(publishedicon)).getAttribute("alt");
				String[] times = publishtimes.split("-");
				// System.out.println(times[1]);

				Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, publishedicon));
				if (flag && times[1].equalsIgnoreCase(PublishTimes)) {
					UIHelper.highlightElement(driver, publishedicon);
					report.updateTestLog("Publish Status",
							"<b> File: " + folderfileName + " is published and it is published " + times[1] + " times",
							Status.PASS);

				} else {
					report.updateTestLog("Publish Status", "<b> File: " + folderfileName
							+ " is not published or publishing times " + times[1] + " is incorrect", Status.FAIL);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void orangepublish(String folderfileName, String PublishTimes) {
		try {
			String publishedicon = indicationprogressXpath.replace("CRAFT", folderfileName);
			String publishtimes = driver.findElement(By.xpath(publishedicon)).getAttribute("alt");
			String[] times = publishtimes.split("-");
			// System.out.println(times[1]);

			Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, publishedicon));
			/*
			 * if(flag && times[1].equalsIgnoreCase(PublishTimes)){
			 * report.updateTestLog("Publish Status",
			 * "<b> File: "+folderfileName+" is published and it is published "
			 * +times[1]+" times", Status.PASS);
			 * 
			 * }else{ report.updateTestLog("Publish Status", "<b> File: "
			 * +folderfileName+" is not published or publishing times "+times[1]
			 * +" is incorrect", Status.FAIL); }
			 */

			if (times[3].equalsIgnoreCase("change")) {
				report.updateTestLog("Publish Status", "<b> File: " + folderfileName + " is modified or updated "
						+ times[1] + " times and it is in organe color symbol", Status.PASS);

			} else {
				report.updateTestLog("Publish Status", "<b> File: " + folderfileName + " is not modified or updated  "
						+ times[1] + " times and it is incorrect", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Publish Status",
					"<b> File: " + folderfileName + " is not modified or updated and it is incorrect", Status.FAIL);
		}
	}

	public void publishbutton(String filename, String moreoptn) {
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String finalXpathForCopyToFolderLink = collectionPg.tempXpathForpublishOptLink.replace("CRAFT", filename)
				.replace("MORE_OPT", moreoptn);
		
		UIHelper.waitFor(driver);
		WebElement moreSettingsOptElement = UIHelper.findAnElementbyXpath(driver, finalXpathForCopyToFolderLink);
		UIHelper.highlightElement(driver, moreSettingsOptElement);
	
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", moreSettingsOptElement);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, collectionPg.messageXpath);
		UIHelper.waitForPageToLoad(driver);
	}

	public void batchpublish(String folderfileName, String PublishTimes) {
		try {

			try {
				do {

					UIHelper.pageRefresh(driver);
					UIHelper.waitForLong(driver);

				} while (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, publishinprogressXpath)));
			} catch (Exception e) {
				String publishedicon = indicationprogressXpath.replace("CRAFT", folderfileName);
				String publishtimes = driver.findElement(By.xpath(publishedicon)).getAttribute("alt");
				String[] times = publishtimes.split("-");
				// System.out.println(times[1]);
				Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, publishedicon));

				if (flag && times[1].equalsIgnoreCase(PublishTimes)) {
					UIHelper.highlightElement(driver, publishedicon);
					report.updateTestLog("Publish Status",
							"<b> File: " + folderfileName + " is published and it is published " + times[1] + " times",
							Status.PASS);

				} else {
					report.updateTestLog("Publish Status", "<b> File: " + folderfileName
							+ " is not published or publishing times " + times[1] + " is incorrect", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PublishingoptionsValidation(String ifyoupublish, String to, ArrayList<String> li) {
		AlfrescoSitesPage sites = new AlfrescoSitesPage(scriptHelper);

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, ifyoupublishXpath);
			UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, ifyoupublishXpath));
			UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, toXpath));
			UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, thethedeliveryXpath));
			Boolean flag = UIHelper
					.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, ifyoupublishDropdownxpath));
			Boolean flag1 = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, todropdownXpath));
			if (flag && flag1) {
				report.updateTestLog("Dropdown Field", "'If you publish' and 'to' are presented in the dropdown fields",
						Status.PASS);
			} else {
				report.updateTestLog("Dropdown Field",
						"'If you publish' and 'to' are not presented in the dropdown fields", Status.FAIL);
			}

			UIHelper.selectbyVisibleText(driver, ifyoupublishDropdownxpath, ifyoupublish);
			ArrayList<String> Values = new ArrayList<String>();
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, ifyoupublishDropdownxpath), Values);
			int count = 0;

			for (String lil : li) {
				for (String Values1 : Values) {
					if (lil.contentEquals(Values1)) {
						count++;
						if (count == 1) {
							report.updateTestLog("<b>If you publish Dropdown Field",
									Values1 + " dropdown value is presented", Status.PASS);
						} else {
							report.updateTestLog("<b>If you publish Dropdown Field",
									Values1 + " dropdown value is not presented", Status.FAIL);
						}
						count = 0;
					}
				}
			}

			UIHelper.selectbyVisibleText(driver, todropdownXpath, to);
			sites.getStringFromClipboard(deliveryURLXpath);
			String url = sites.getStringFromClipboard(deliveryURLXpath);
			ArrayList<String> lil = new ArrayList<String>();
			url = url.replace("http://", "http:/");
			String[] b1 = url.split("/");
			for (String b1b : b1) {
				if (!b1b.matches(".*\\d+.*")) {
					lil.add(b1b);
					lil.add("/");
				}
			}

			String URLLink = "";
			String url1 = null;
			String a = "";

			if (to.equalsIgnoreCase("School Content")) {
				// URLLink="[http:, /, us-school-stg.pearsoned.com, /, school,
				// /, "+ifyoupublish+", /]";
				for (String filename : li) {
					url1 = ", " + filename + ", /";
					a = a.concat(url1);
					URLLink = "[http:, /, us-school-stg.pearsoned.com, /, school, /" + a + "]";
				}
			} else if (to.equalsIgnoreCase("Alfresco CDN")) {
				// URLLink="[http:, /, content.stg-openclass.com, /, eps, /,
				// cdn, /, alfesrco, /, "+ifyoupublish+", /]";

				for (String filename : li) {
					url1 = ", " + filename + ", /";
					a = a.concat(url1);
					URLLink = "[http:, /, content.stg-openclass.com, /, eps, /, cdn, /, alfesrco, /" + a + "]";
				}
			}

			String Actual = lil.toString();
			// System.out.println(URLLink);
			// System.out.println(Actual);
			if (Actual.contains(URLLink)) {
				report.updateTestLog("<b>Publishing Options URL:", "<b>" + lil + "url is generated", Status.PASS);
			} else {
				report.updateTestLog("<b>Publishing Options URL:", "<b>" + lil + "url is not generated as" + URLLink,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DeleteFolderandFile() {

		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);

		try {

			WebElement tbody = driver.findElement(By.xpath(documentsXpath));
			List<WebElement> tr = tbody.findElements(By.tagName("tr"));
			int rowcount = tr.size();

			for (int i = 1; i <= rowcount; i++) {
				// div[@class='documents
				// yui-dt']//tbody[@class='yui-dt-data']/tr[1]/td[contains(@class,'fileName')]//span[2]/a
				String num = Integer.toString(i);
				String filetype = GetTypeofFileXpath.replace("CRAFT", num);

				String filetypename = driver.findElement(By.xpath(filetype)).getAttribute("class");
				String filename = GetFileNameXpath.replace("CRAFT", num);
				String name = UIHelper.getTextFromWebElement(driver, filename);

				if (filetypename.contains("folder")) {
					collectionPg.clickOnMoreSetting(name);
					collectionPg.commonMethodForClickOnMoreSettingsOption(name, "Delete Folder");
					UIHelper.click(driver, deletedocumentXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					// UIHelper.waitForVisibilityOfEleByXpath(driver,
					// cantdeletxpath);
					try {
						collectionPg.clickOnMoreSetting(name);
						new AlfrescoMyFilesPage(scriptHelper).openFolder(name);
						DeleteFolderandFile();
						new AlfrescoSitesPage(scriptHelper).enterIntoDocumentLibrary();
					} catch (Exception e) {

					}

				} else {
					collectionPg.clickOnMoreSetting(name);
					collectionPg.commonMethodForClickOnMoreSettingsOption(name, "Delete Document");
					UIHelper.click(driver, deletedocumentXpath);
					UIHelper.waitForPageToLoad(driver);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickonurl(String PublicationOREPS, String Institution, String title, String contentinfile) {
       try {
		String url = "";
		if (PublicationOREPS.equalsIgnoreCase("Publication")) {
			url = "1";
		} else if (PublicationOREPS.equalsIgnoreCase("EPS")) {
			url = "2";
		}
		String fileline = filecontentXpath.replace("CRAFT", contentinfile);
		String urllink = URLLinkXpath.replace("CRAFT", Institution).replace("TYPE", url);
		// System.out.println(urllink);
		UIHelper.highlightElement(driver, urllink);
		UIHelper.click(driver, urllink);
		UIHelper.waitFor(driver);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String titleactual = driver.getTitle();
		// System.out.println(titleactual);
		if (titleactual.equalsIgnoreCase(title)) {
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, fileline);
			boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, fileline));
			if (flag) {
				report.updateTestLog(PublicationOREPS + "URL:",
						"<b>User can be able to see the published Content as like a web application.", Status.PASS);
				driver.close();
				driver.switchTo().window(tabs.get(0));
			} else {
				report.updateTestLog(PublicationOREPS + "URL:",
						"<b>User is not able to see the published Content as like a web application.", Status.FAIL);
			}
		} else {
			report.updateTestLog(PublicationOREPS + "URL:", "<b>URL Link is not working as expected.", Status.FAIL);

		}
       }catch(Exception e) {
    	   report.updateTestLog(PublicationOREPS + "URL:", "<b>URL Link is not working as expected.", Status.FAIL);
       }
	}

	public void ClickOnURLZipItself(String PublicationOREPS, String Institution) {

		String url = "";
		if (PublicationOREPS.equalsIgnoreCase("Publication")) {
			url = "1";
		} else if (PublicationOREPS.equalsIgnoreCase("EPS")) {
			url = "2";
		}

		String urllink = URLLinkXpath.replace("CRAFT", Institution).replace("TYPE", url);
		// System.out.println(urllink);
		UIHelper.highlightElement(driver, urllink);
		UIHelper.click(driver, urllink);
		UIHelper.waitFor(driver);

		String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		File downloadFile = new File(downloadedFilePath + "/" + fileName);
		long start_time = System.currentTimeMillis();
		long wait_time = 200000;
		long end_time = start_time + wait_time;
		while (System.currentTimeMillis() < end_time) {
			if (downloadFile.exists()) {
				break;
			}
		}

		if (!downloadFile.exists()) {
			report.updateTestLog("To download a file", "To download a file Failed", Status.FAIL);
		} else {
			report.updateTestLog("To download a file", "File Downloaded" + "<br /><b>File Name : </b>" + fileName,
					Status.DONE);
		}

	}

	public void reviewchanges(String changes, String filename) {

		try {

			String Changestype = epsreviewchangesXpath.replace("CRAFT", changes);
			String name = filecontentXpath.replace("CRAFT", filename);

			String type = UIHelper.getTextFromWebElement(driver, Changestype);
			String name1 = UIHelper.getTextFromWebElement(driver, name);

			Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, name));
			Boolean flag1 = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, Changestype));

			if (flag && flag1) {
				report.updateTestLog("File is " + type, "<b>File " + name1 + " is " + type, Status.PASS);
				UIHelper.click(driver, closexpath);

			}

		} catch (Exception e) {

		}
	}

	public String getPublishingURL(String PublicationOREPS, String Institution) {

		String urloffile = "";

		try {

			String url = "";
			if (PublicationOREPS.equalsIgnoreCase("Publication")) {
				url = "1";
			} else if (PublicationOREPS.equalsIgnoreCase("EPS")) {
				url = "2";
			}

			String urllink = URLLinkXpath.replace("CRAFT", Institution).replace("TYPE", url);
			urloffile = UIHelper.getTextFromWebElement(driver, urllink);

			/* System.out.println(urloffile); */
			UIHelper.highlightElement(driver, urllink);
			// UIHelper.click(driver, urllink);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return urloffile;
	}

	public void openurlintab(String PublicationOREPS, String Institution) {

		try {

			// String url=driver.getCurrentUrl();
			String url = getPublishingURL(PublicationOREPS, Institution);
			/*
			 * ArrayList<String> tabs = new
			 * ArrayList<String>(driver.getWindowHandles());
			 * driver.switchTo().window(tabs.get(1)); String
			 * url=driver.getTitle();
			 */
			/* System.out.println(url); */
			url = url.replace("/player.html", ".zip");
			new AlfrescoHomePage(scriptHelper).openNewTab(url);
			/* System.out.println(url); */
			UIHelper.waitFor(driver);

			new AlfrescoDocumentDetailsPage(scriptHelper).deleteFileInDownloadedPath(false);

			String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			File downloadFile = new File(downloadedFilePath + "/" + fileName);
			long start_time = System.currentTimeMillis();
			long wait_time = 200000;
			long end_time = start_time + wait_time;
			while (System.currentTimeMillis() < end_time) {
				if (downloadFile.exists()) {
					break;
				}
			}

			if (!downloadFile.exists()) {
				report.updateTestLog("To download a file", "To download a file Failed", Status.FAIL);
			} else {
				report.updateTestLog("To download a file", "File Downloaded" + "<br /><b>File Name : </b>" + fileName,
						Status.DONE);
			}

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(0));

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/*
	 * public void EPSPublishDeleteFolderFiles(){
	 * 
	 * try{
	 * 
	 * WebElement tbody = driver.findElement(By.xpath(documentsXpath));
	 * List<WebElement> tr = tbody.findElements(By.tagName("tr")); int rowcount
	 * = tr.size();
	 * 
	 * 
	 * for(int i=1;i<=rowcount;i++){
	 * 
	 * String num = Integer.toString(i); String filetype =
	 * GetTypeofFileXpath.replace("CRAFT", num); //System.out.println(filetype);
	 * String filetypename =
	 * driver.findElement(By.xpath(filetype)).getAttribute("class"); String
	 * filename = GetFileNameXpath.replace("CRAFT", num); String name=
	 * UIHelper.getTextFromWebElement(driver, filename);
	 * //System.out.println(name); if(filetypename.contains("folder")){ int
	 * rowcount1=1; new
	 * AlfrescoCollectionsPage(scriptHelper).clickOnMoreSetting(name); new
	 * AlfrescoCollectionsPage(scriptHelper).
	 * commonMethodForClickOnMoreSettingsOption(name,"Delete Folder");
	 * UIHelper.click(driver, deletedocumentXpath);
	 * 
	 * while(rowcount1>0){
	 * 
	 * 
	 * new AlfrescoMyFilesPage(scriptHelper).openFolder(name);
	 * DeleteFolderandFile();
	 * 
	 * WebElement tbody1 = driver.findElement(By.xpath(documentsXpath));
	 * List<WebElement> tr1 = tbody1.findElements(By.tagName("tr")); rowcount1 =
	 * tr1.size();
	 * 
	 * }
	 * 
	 * 
	 * }else{ new
	 * AlfrescoCollectionsPage(scriptHelper).clickOnMoreSetting(name); new
	 * AlfrescoCollectionsPage(scriptHelper).
	 * commonMethodForClickOnMoreSettingsOption(name,"Delete Document");
	 * UIHelper.click(driver, deletedocumentXpath);
	 * UIHelper.waitForPageToLoad(driver); } }
	 * 
	 * 
	 * 
	 * }catch(Exception e){ e.printStackTrace(); }
	 * 
	 * }
	 */

	public void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}

	public void batchpublishurlzip(String fileName, String option) {
		batchpublish(fileName, "one");
		new AlfrescoCollectionsPage(scriptHelper).clickOnMoreSetting(fileName);
		new AlfrescoCollectionsPage(scriptHelper).commonMethodForClickOnMoreSettingsOption(fileName, option);
		/* System.out.println(fileName); */

		String player = zipXpath.replace("CRAFT", fileName);

		Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, player));
		/* System.out.println(flag); */
		String url = UIHelper.getTextFromWebElement(driver, player);
		/* System.out.println(url); */
		if (flag) {
			report.updateTestLog("Publication URL:", "Publication url is ended as zip as:" + url, Status.PASS);
		} else {
			report.updateTestLog("Publication URL:", "Publication url is not ended as zip as:" + url, Status.FAIL);
		}
		UIHelper.click(driver, batchPublishcancelbutton);
		UIHelper.pageRefresh(driver);
		DeleteFolderandFile();
		UIHelper.pageRefresh(driver);
		DeleteFolderandFile();
		UIHelper.pageRefresh(driver);
		DeleteFolderandFile();

	}

	public void batchpublishurlplayer(String fileName, String option) {
		batchpublish(fileName, "one");
		new AlfrescoCollectionsPage(scriptHelper).clickOnMoreSetting(fileName);
		new AlfrescoCollectionsPage(scriptHelper).commonMethodForClickOnMoreSettingsOption(fileName, option);
		System.out.println(fileName);
		String Name = fileName.replace(".zip", "");
		System.out.println(Name);
		String player = playerhtmlXpath.replace("CRAFT", Name);

		Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, player));
		System.out.println(flag);
		String url = UIHelper.getTextFromWebElement(driver, player);
		System.out.println(url);
		if (flag) {
			report.updateTestLog("Publication URL:", "Publication url is ended as player html as:" + url, Status.PASS);
		} else {
			report.updateTestLog("Publication URL:", "Publication url is not ended as player html as:" + url,
					Status.FAIL);
		}

		UIHelper.click(driver, batchPublishcancelbutton);
	}

	// Verify Publish icon for the given file
	public void verifypublishicon(String fileName, String option, String institute) {
		try {
			String filestatus = filepublishstatus.replace("CRAFT", fileName).replace("REPLACE", option).replace("EPS",
					institute);

			UIHelper.waitForVisibilityOfEleByXpath(driver, filestatus);
			if (UIHelper.checkForAnElementbyXpath(driver, filestatus)) {
				String instutionpublished = UIHelper.findAnElementbyXpath(driver, filestatus).getAttribute("alt");
				String pubstate = UIHelper.findAnElementbyXpath(driver, filestatus).getAttribute("Title");
				UIHelper.highlightElement(driver, filestatus);
				report.updateTestLog("Verify publish status for file/folder",
						"Publishing of file/Folder " + fileName + "is successfully. <br> Publish State : " + pubstate,
						Status.PASS);
			} else {
				report.updateTestLog("Verify publish status for file/folder",
						"Publishing of file/Folder " + fileName + "failed", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			report.updateTestLog("Verify publish status for file/folder",
					"Publishing of file/Folder " + fileName + "failed", Status.FAIL);
			e.printStackTrace();
		}
	}

	// publish history details
	public ArrayList<String> publishhistory(String rownum) {
		ArrayList<String> publisyhistory = new ArrayList<String>();
		String rows = publishhistoryrows.replace("CRAFT", rownum);
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, publishbox);
			List<WebElement> historyrows = UIHelper.findListOfElementsbyXpath(rows, driver);

			for (WebElement row : historyrows) {

				publisyhistory.add(row.getText());

			}

			return publisyhistory;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			report.updateTestLog("Verify publish  history", "Failed to retreive publish history", Status.FAIL);

			e.printStackTrace();
			return null;
		}

	}

	public void PublishWithoutWait(String Institution) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, "//table[@class='spaces']/tbody");
			String finalpublishactionbut = publishactionbut.replace("CRAFT", Institution);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpublishactionbut);
			UIHelper.highlightElement(driver, finalpublishactionbut);
			UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, finalpublishactionbut));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitUntilPublishContent(String folderfileName, String PublishTimes) {
		try {
			for (int i = 0; i < 10; i++) {

				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				// UIHelper.waitForLong(driver);

				if (!UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, publishinprogressXpath))) {
					break;
				}

			}
		} catch (Exception e) {
			String publishedicon = indicationprogressXpath.replace("CRAFT", folderfileName);
			String publishtimes = driver.findElement(By.xpath(publishedicon)).getAttribute("alt");
			String[] times = publishtimes.split("-");
			// System.out.println(times[1]);

			Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, publishedicon));
			if (flag && times[1].equalsIgnoreCase(PublishTimes)) {
				UIHelper.highlightElement(driver, publishedicon);
				report.updateTestLog("Publish Status",
						"<b> File: " + folderfileName + " is published and it is published " + times[1] + " times",
						Status.PASS);

			} else {
				report.updateTestLog("Publish Status", "<b> File: " + folderfileName
						+ " is not published or publishing times " + times[1] + " is incorrect", Status.FAIL);
			}

		}

	}
	public void clickCancelOnPublishScreen()
	{
		try
		{
			UIHelper.click(driver, cancelButtonXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("click on Cancel in Publish Popup", "Cancel is Clicked", Status.PASS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			report.updateTestLog("click on Cancel in Publish Popup", "Cancel is not Clicked", Status.FAIL);
		}
	}
	public void clickDeleteOnPublishScreen()
	{
		try
		{
			UIHelper.click(driver, deleteButtonXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("click on Delete in DeletePublishFiles Popup", "Delete is Clicked", Status.PASS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			report.updateTestLog("click on Cancel in DeletePublishFiles Popup", "Delete is not Clicked", Status.FAIL);
		}
	}
	
	//Added for NALS
	public void checkEPSID(String fileNameVal) {
		UIHelper.waitFor(driver);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String EPSURL,EPSID=null;
		collectionPg.clickOnMoreSetting(fileNameVal);	
		collectionPg.commonMethodForClickOnMoreSettingsOption(fileNameVal,"Publish");
		UIHelper.waitFor(driver);
		String EPSURL1 = (UIHelper.findAnElementbyXpath(driver, "//*[@id='prompt']/div[2]/table/tbody/tr[2]/td[5]/u/a").getText()).substring(68,106);	
		       collectionPg.commonMethodForClickOnMoreSettingsOption(fileNameVal,"Edit Properties");
		       String EPSURL2 = (UIHelper.findAnElementbyXpath(driver, "//*[contains(@id,'prop_cm_description')]").getText()).substring(8,47);
		       driver.findElement(By.xpath("//div[@class='yui-module yui-overlay yui-panel']/a")).click();
		        UIHelper.waitFor(driver); 
		        
		    if(EPSURL1.trim().equalsIgnoreCase(EPSURL2.trim())){
		       report.updateTestLog("EPS ID match check",  "EPS ID matchs" + fileNameVal,Status.PASS);
		    }
		    else
		    {
		       report.updateTestLog("EPS ID match check",  "EPS ID does not match" +fileNameVal ,Status.FAIL);
		    }


		}


}
