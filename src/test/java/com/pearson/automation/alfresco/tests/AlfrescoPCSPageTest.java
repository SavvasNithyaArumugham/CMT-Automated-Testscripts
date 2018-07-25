package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;

import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoPCSPageTest extends ReusableLibrary {

	private String fldrXPath = ".//div[@id='pcsDashlet']//table[@id='folderListTable-pcs']//*[text()='CRAFT']";
	private String fleXPath = ".//div[@id='pcsDashlet']//table[@id='folderListTable-pcs']//*[text()='CRAFT']";
	public String dashletname = ".//div[@id='pcsDashlet']//div[@class='title']";
	public String foldername = ".//div[@id='pcsDashlet']//div[@class='dataTables_scrollHead']//th[1]";
	public String programname = ".//div[@id='pcsDashlet']//div[@class='dataTables_scrollHead']//th[2]";
	public String isbnname = ".//div[@id='pcsDashlet']//div[@class='dataTables_scrollHead']//th[3]";
	public String fileXPath = ".//div[@id='pcsDashlet']//table[@id='folderListTable-pcs']";
	public String filechkXPath = ".//div[@id='pcsDashlet']//table//input[@title='CRAFT']";
	public String breadcrumb = ".//*[@id='breadCrumb-pcs']";
	public String breadcrumblist = ".//*[@id='breadCrumb-pcs']//li//span";
	private ArrayList<String> pcsbreadcrumbList = new ArrayList<String>();
	public String pcslogo = ".//*[@id='tabs-pcs']//img[contains(@src,'pcs_Logo.png')]";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String promptmsg = ".//*[@id='prompt']/div[2]";
	private String promptstate = ".//*[@id='prompt_h']";
	private String promptbutton = ".//*[@id='prompt']//button[text()='CRAFT']";
	private String folderList = ".//*[@id='folderListTable-pcs']//tr//a";
	private ArrayList<String> folderpcsList = new ArrayList<String>();
	private String isbnvalue = ".//div[@id='pcsDashlet']//table[@id='folderListTable-pcs']//*[text()='CRAFT']//ancestor::tr//td[3]";
	private String keywordvalue = ".//div[@id='pcsDashlet']//table[@id='folderListTable-pcs']//*[text()='CRAFT']//ancestor::tr//td[2]";
	

	public AlfrescoPCSPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	AlfrescoPCSPage pcsPage = new AlfrescoPCSPage(scriptHelper);


	// Verify PCS Dashlet File/Folder Displayed
	public void verifyFileFldrDisp(String fldrNme) {
		try {
		String fldrXPathFinal = fldrXPath.replace("CRAFT", fldrNme);
		UIHelper.waitForVisibilityOfEleByXpath(driver, fldrXPathFinal);	
	
			if (UIHelper.findAnElementbyXpath(driver, fldrXPathFinal)
					.isDisplayed()
					) {
				UIHelper.highlightElement(driver, fldrXPathFinal);
				report.updateTestLog(
						"Verify whether the files created in the document library of the archive site is displayed in the 'Database Search - PCS' dash-let of the archive review site dashboard",
						"Created Files and folders are displayed successfully ",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify whether the files created in the document library of the archive site is displayed in the 'Database Search - PCS' dash-let of the archive review site dashboard",
						"Created Files and folders are  NOT displayed successfully ",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify whether the files created in the document library of the archive site is displayed in the 'Database Search - PCS' dash-let of the archive review site dashboard",
					"Created Files and folders are  NOT displayed successfully ",
					Status.FAIL);
		}
	}

	// Verify PCS Dashlet breadcrumb list Displayed
	public void verifyPCSBreadcrumb(String folder) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, dashletname);
			UIHelper.findandAddElementsToaList(driver, breadcrumblist,
					pcsbreadcrumbList);
			if (pcsbreadcrumbList.contains(folder)) {

				report.updateTestLog(
						"Verify view the breadcrumbs in PCS dashlet",
						"Breadcrumbs in PCS dashlet contains parent folders. <br><b> Parent Folders : <\b>"
								+ pcsbreadcrumbList, Status.PASS);
			} else {
				report.updateTestLog(
						"Verify view the breadcrumbs in PCS dashlet",
						"Breadcrumbs display in PCS dashlet contains parent folders failed",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify view the breadcrumbs in PCS dashlet",
					"Breadcrumbs display in PCS dashlet contains parent folders failed",
					Status.FAIL);
		}
	}

	// Verify PCS Dashlet success msg for MEtadata attachment to file / Folder
	public void verifypromptMsg(String buttonname) {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					messageEleXpath);
			 UIHelper.waitForPageToLoad(driver);
			 UIHelper.waitFor(driver);
			String finalbutton = promptbutton.replace("CRAFT", buttonname);
			UIHelper.waitForVisibilityOfEleByXpath(driver, promptmsg);

			if (UIHelper.checkForAnElementbyXpath(driver, finalbutton)) {
				UIHelper.highlightElement(driver, finalbutton);
				report.updateTestLog(
						"Verify prompt msg",
						"Search result is attached to data. <br><b> Message : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										promptmsg).getText() +"<br><b> Status : </b>" 
								+ UIHelper.findAnElementbyXpath(driver,
										promptstate).getText(), Status.PASS);

				UIHelper.click(driver, finalbutton);
			} else {
				report.updateTestLog("Verify prompt msg",
						"Search result is not attached to data.", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify prompt msg",
					"Search result is not attached to data.", Status.FAIL);
		}
	}
	
	// Verify PCS Dashlet success msg for MEtadata attachment to file / Folder
	public void verifyupdatedPCSValues(String file, String isbn) {
		try {
			String finalisbnvalue = isbnvalue.replace("CRAFT", file);
			String finalkeywordvalue = keywordvalue.replace("CRAFT", file);

			// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			// messageEleXpath);
			UIHelper.waitForPageToLoad(driver);
			// UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finalisbnvalue);

			if (UIHelper.getTextFromWebElement(driver, finalisbnvalue).equals(
					isbn)) {

				UIHelper.highlightElement(driver, finalisbnvalue);
				UIHelper.highlightElement(driver, finalkeywordvalue);
				report.updateTestLog(
						"Verify Updated Metadata in PCS Dashlet",
						"ISBN is updated for the file. <br><b> ISBN : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										finalisbnvalue).getText()
								+ "<br><b> Title : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										finalkeywordvalue).getText(),
						Status.PASS);

			} else {
				report.updateTestLog("Verify Updated Metadata in PCS Dashlet",
						"ISBN is not updated for the file.", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Updated Metadata in PCS Dashlet",
					"ISBN is not updated for the file.", Status.FAIL);
		}
	}

	// Verify PCS Dashlet success msg for MEtadata attachment to file / Folder
	public void verifydashletSort() {
		try {
			folderpcsList.clear();
			// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
			// messageEleXpath);
			UIHelper.waitForPageToLoad(driver);
			// UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, foldername);
			UIHelper.highlightElement(driver, foldername);
			UIHelper.click(driver, foldername);
			if (UIHelper.checkForAnElementbyXpath(driver, folderList)) {
				UIHelper.highlightElement(driver, folderList);
				UIHelper.findandAddElementsToaList(driver, folderList,
						folderpcsList);

				report.updateTestLog(
						"Verify Updated Metadata in PCS Dashlet",
						"ISBN is updated for the file. <br><b> Sort : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										foldername).getAttribute("aria-sort")
								+ "<br><b> Folders List : </b>" + folderpcsList,
						Status.PASS);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Updated Metadata in PCS Dashlet",
					"ISBN is not updated for the file.", Status.FAIL);
		}
	}

	// Verify PCS site date and URL of teh metadata update in alfresco
	public void verifypcssite(String date, String site) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pcsPage.pcsURL);
			if (UIHelper.findAnElementbyXpath(driver, pcsPage.datevalue)
					.getAttribute("value").equals(date)
					//&& UIHelper.findAnElementbyXpath(driver, pcsPage.pcsURL).getAttribute("value")
					) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, pcsPage.pcsURL));
				UIHelper.highlightElement(driver, pcsPage.datevalue);
				UIHelper.highlightElement(driver, pcsPage.pcsURL);

				report.updateTestLog(
						"Verify date and url sent to PCS Site",
						"Date and url sent to PCS Site verified. <br><b> Date : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										pcsPage.datevalue)
										.getAttribute("value")
								+ "<br><b> Object URL : </b>"
								+ UIHelper.findAnElementbyXpath(driver,
										pcsPage.pcsURL).getAttribute("value"),
						Status.PASS);
			}else{
				report.updateTestLog("Verify date and url sent to PCS Site",
						"Verify date and url sent to PCS Site failed.", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify date and url sent to PCS Site",
					"Verify date and url sent to PCS Site failed.", Status.FAIL);
		}
	}
	
	// Verify PCS site record fetched for a ISBN
	public void verifypcsrecord() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					pcsPage.pcsnoofrecord);
			if (UIHelper
					.checkForAnElementbyXpath(driver, pcsPage.pcsnoofrecord)) {
				UIHelper.highlightElement(driver, pcsPage.pcsnoofrecord);

				report.updateTestLog(
						"Verify No of record in PCS Site",
						"No of records retrieved in PCS site."
								+ UIHelper.findAnElementbyXpath(driver,
										pcsPage.pcsnoofrecord).getText(),
						Status.PASS);
			}else{
				report.updateTestLog("Verify No of record in PCS Site",
						"Retrieval of record in PCS site failed.", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify No of record in PCS Site",
					"Retrieval of record in PCS site failed.", Status.FAIL);
		}
	}
	

	// Verify PCS site date and URL for pls data of teh metadata update in alfresco
		public void verifyplssite(String date, String site) {
			try {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, pcsPage.plsURL);
				if (UIHelper.findAnElementbyXpath(driver, pcsPage.plsdatevalue)
						.getAttribute("value").equals(date)
						//&& UIHelper.findAnElementbyXpath(driver, pcsPage.pcsURL).getAttribute("value")
						) {
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, pcsPage.plsURL));
					UIHelper.highlightElement(driver, pcsPage.plsdatevalue);
					UIHelper.highlightElement(driver, pcsPage.plsURL);

					report.updateTestLog(
							"Verify date and url sent to PCS Site",
							"Date and url sent to PCS Site verified. <br><b> Date : </b>"
									+ UIHelper.findAnElementbyXpath(driver,
											pcsPage.plsdatevalue)
											.getAttribute("value")
									+ "<br><b> Object URL : </b>"
									+ UIHelper.findAnElementbyXpath(driver,
											pcsPage.plsURL).getAttribute("value"),
							Status.PASS);
				}else{
					report.updateTestLog("Verify date and url sent to PCS Site",
							"Verify date and url sent to PCS Site failed.", Status.FAIL);
				}

			} catch (Exception e) {
				report.updateTestLog("Verify date and url sent to PCS Site",
						"Verify date and url sent to PCS Site failed.", Status.FAIL);
			}
		}
		
		
		// Verify PCS site date and URL of teh metadata update in alfresco
		public void verifycustompcssite(String date, String site) {
			try {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, pcsPage.custompcsURL);
				if (UIHelper.findAnElementbyXpath(driver, pcsPage.customdatevalue)
						.getAttribute("value").equals(date)
						//&& UIHelper.findAnElementbyXpath(driver, pcsPage.pcsURL).getAttribute("value")
						) {
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, pcsPage.custompcsURL));
					UIHelper.highlightElement(driver, pcsPage.customdatevalue);
					UIHelper.highlightElement(driver, pcsPage.custompcsURL);

					report.updateTestLog(
							"Verify date and url sent to PCS Site",
							"Date and url sent to PCS Site verified. <br><b> Date : </b>"
									+ UIHelper.findAnElementbyXpath(driver,
											pcsPage.customdatevalue)
											.getAttribute("value")
									+ "<br><b> Object URL : </b>"
									+ UIHelper.findAnElementbyXpath(driver,
											pcsPage.custompcsURL).getAttribute("value"),
							Status.PASS);
				}else{
					report.updateTestLog("Verify date and url sent to PCS Site",
							"Verify date and url sent to PCS Site failed.", Status.FAIL);
				}

			} catch (Exception e) {
				report.updateTestLog("Verify date and url sent to PCS Site",
						"Verify date and url sent to PCS Site failed.", Status.FAIL);
			}
		}
}

