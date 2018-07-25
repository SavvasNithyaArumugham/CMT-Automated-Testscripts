package com.pearson.automation.alfresco.pages;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

public class AlfrescoPCSPage extends ReusableLibrary {

	private String fldrXPath = ".//div[@id='pcsDashlet']//table[@id='folderListTable-pcs']//*[text()='CRAFT']";
	private String fleXPath = ".//div[@id='pcsDashlet']//table[@id='folderListTable-pcs']//*[text()='CRAFT']";
	public String dashletname = ".//div[@id='pcsDashlet']//div[@class='title']";
	public String foldername = ".//div[@id='pcsDashlet']//div[@class='dataTables_scrollHead']//th[1]";
	public String programname = ".//div[@id='pcsDashlet']//div[@class='dataTables_scrollHead']//th[2]";
	public String isbnname = ".//div[@id='pcsDashlet']//div[@class='dataTables_scrollHead']//th[3]";
	public String fileXPath = ".//div[@id='pcsDashlet']//table[@id='folderListTable-pcs']";
	public String filechkXPath = ".//div[@id='pcsDashlet']//table//input[@title='CRAFT']";
	private String messageEleXpath = ".//*[@id='message']/div";
	private String searchfield = ".//*[@id='txtFind-pcs']";
	
	public String  isbnIDXpath= ".//*[@id='findHeader-pcs']/span";
	public String  buttonXpath = ".//*[@id='findHeader-pcs']/button[text()='CRAFT']";
	
	public String  labelXpath = ".//*[@id='tblSearchResults-pcs']//span[text()='CRAFT']";
	public String  labellistXpath = ".//section[@class='odd']//span";
	
	public String  custlabelXpath = ".//*[@class='plsTblSearchResults-pcs table']//span[text()='CRAFT']";
	public String  custlabellistXpath = ".//*[@class='plsTblSearchResults-pcs table']//input[@id='CRAFT']";
	
	public String  editmetadata =".//*[@class='set']//label[text()='CRAFT:']//following-sibling::input";
	public String  pcsvalues =".//*[@id='allContainerDiv']/table//td[contains(text(),'CRAFT')]//following-sibling::td[1]";
	public String  pcsvalues1 =".//*[@id='allContainerDiv']/table//td[contains(text(),'CRAFT')]//following-sibling::td//input";
	public String  pcscopyright =".//*[@id='allContainerDiv']/table//td[contains(text(),'Copyright')]//following-sibling::td//input";
	public String  pcsISBN10 =".//*[@id='allContainerDiv']/table//td[text()='ISBN-10:']//following-sibling::td//input";
	public String  pcsISBN13 =".//*[@id='allContainerDiv']/table//td[contains(text(),'ISBN-13')]//following-sibling::td//input";
	

	
	//PLS
	public String  plscopyright =".//*[@id='allContainerDiv']/table//td[contains(text(),'Copyright')]//following-sibling::td//input[1]";
	public String  plsISBN10 =".//*[@id='allContainerDiv']/table//td[text()='ISBN-10:']//following-sibling::td//input[2]";
	public String  plsISBN13 =".//*[@id='allContainerDiv']/table//td[contains(text(),'ISBN-13')]//following-sibling::td//input[2]";

	
	public String  custompcsvalues =".//tbody/tr/td[text()='CRAFT']//following-sibling::td/input";
	public String  custompcsselect =".//tbody/tr/td[text()='CRAFT']//following-sibling::td/select/option[@selected='selected']";
	
	public String  editmetadatatext =".//*[@class='set']//label[text()='CRAFT:']//following-sibling::textarea";
	public String labelvalueXpath = ".//*[@id='CRAFT']";
	public String newlabelvalueXpath = ".//div[@class='ptgTblSearchResults-pcs table']//*[@id='CRAFT']";
	public String chooseRecord = ".//*[@id='btnChoose-pcs']";
	public String searchExit = ".//*[@id='searchResultHeader-pcs']/div[2]/button";
	public String searchimgslot = ".//*[@id='tblSearchResults-pcs']//img";
	public String editvalue = ".//*[@name='CRAFT']";
	
	public String pcsvaluelist = ".//*[@id='tblSearchResults-pcs']//span[text()='CRAFT']//ancestor::section//section[@class='even']//input";
	public String pcssitelist = ".//input[contains(@name,'CRAFT')]";
	public String searchlisttab = ".//*[@id='tabs-pcs']//li[2]";
	public String Returnvalue;
	
	
	public String pcssiteIsbn = ".//*[@id='searchISBN']";
	public String pcssiteOK = ".//*[@name='listButton']";
	public String primaryTab = ".//*[@id='mydroplinemenu']//li//span[text()='CRAFT']";
	public String secondaryTab = ".//*[@class='verticalTabsContainer']//li//span[text()='CRAFT']";
	public String customdatevalue = ".//*[@id='filesToPal']";
	public String custompcsURL = ".//*[@name='palObjectId']";
	public String datevalue = ".//*[@id='filesToPAL']";
	public String pcsURL = ".//*[@name='palObjectID']";
	public String plsdatevalue = ".//*[@id='alfrescoDate']";
	public String plsURL = ".//*[@name='palObjectId']";
	public String pcsAlfrescoButton = ".//*[@value='view alfresco']";
	public String pcsnoofrecord = ".//*[@id='navigation']/li[6]";
	public String pcsdashletlist = ".//section[@class='even']//input";
	public String pcseditPage = ".//*[@class='form-manager']/h1[contains(text(),'CRAFT')]";
	
	public String pcsEditLabel = ".//label[contains(text(),'CRAFT')]";
	public String pcsEditValue = ".//label[contains(text(),'CRAFT')]//following-sibling::input";
	public String pcsCancelButton = ".//*[@id='btnCancel']";

	public AlfrescoPCSPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void openFoldersInPCSDashlet(String folderName, String deepitem) {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, dashletname);
			String finalFolderlistXpath = fldrXPath
					.replace("CRAFT", folderName);
			String finaldeepitemXpath = fldrXPath
					.replace("CRAFT", folderName);
			UIHelper.click(driver, finalFolderlistXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					messageEleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finaldeepitemXpath);
			if(UIHelper.checkForAnElementbyXpath(driver, finaldeepitemXpath)){
			
			report.updateTestLog("Navigate to Folder",
							"Navigated to Folder" + folderName, Status.PASS);
			}else{
				report.updateTestLog("Navigate to Folder",
						"Navigated to Folder" + folderName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkiteminPCSDashlet(String folderName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, dashletname);
			String finalRadioButtonXpath = filechkXPath
					.replace("CRAFT", folderName);
			UIHelper.click(driver, finalRadioButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchfield);
			if(UIHelper.checkForAnElementbyXpath(driver, searchfield)){
				UIHelper.highlightElement(driver, searchfield);
				report.updateTestLog("Select File/Folder", "File/Folder selected:"
						+ folderName, Status.PASS);
			}else{
				report.updateTestLog("Select File/Folder", "File/Folder failed:"
						+ folderName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchisbnPCS(String value) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, searchfield);
			if (UIHelper.checkForAnElementbyXpath(driver, searchfield)) {
				UIHelper.highlightElement(driver, searchfield);
			UIHelper.sendKeysToAnElementByXpath(driver, searchfield, value);

				report.updateTestLog("ISBN/Pear ID search",
						"ISBN/Pear ID value entered for search. <br><b> Value : </b> " +value,
						Status.PASS);
			} else {
				report.updateTestLog("ISBN/Pear ID search",
						"ISBN/Pear ID search failed",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("ISBN/Pear ID search",
					"ISBN/Pear ID search failed",
					Status.FAIL);
		}
	}
	
	public void clickbutton(String buttonName) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalbuttonXpath = buttonXpath.replace("CRAFT", buttonName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalbuttonXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, finalbuttonXpath)) {
				UIHelper.highlightElement(driver, searchfield);
				UIHelper.click(driver, finalbuttonXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, dashletname);

				report.updateTestLog("Click " + buttonName + " button",
						"Click " + buttonName + " button sussefully",
						Status.PASS);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageEleXpath);
			} else {
				report.updateTestLog("Click " + buttonName + " button",
						"Click " + buttonName + " button failed", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click " + buttonName + " button", "Click "
					+ buttonName + " button failed", Status.FAIL);
		}
	}
	
	public String labelvaluereturned(String labelName, String textId) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, chooseRecord);
			String finallabelNameXpath = labelXpath.replace("CRAFT", labelName);
			String finallabelValueXpath = labelvalueXpath.replace("CRAFT",
					textId);
			if (UIHelper.checkForAnElementbyXpath(driver, finallabelNameXpath)) {
				UIHelper.highlightElement(driver, finallabelNameXpath);
				UIHelper.highlightElement(driver, finallabelValueXpath);
				Returnvalue = UIHelper.findAnElementbyXpath(driver,
						finallabelValueXpath).getAttribute("Title");
				report.updateTestLog(
						"Get Label value",
						"Value for "
								+ labelName
								+ " is retrieved sussefully. <br><b> Returnvalue : </b>"
								+ Returnvalue, Status.DONE);
			} else {
				report.updateTestLog("Get Label value", "Value for "
						+ labelName + " is not retrieved.", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Get Label value", "Value for " + labelName
					+ " is not retrieved.", Status.FAIL);
		}
		return Returnvalue;
	}
	
	// Common method to navigate in PCS site after isbn search
	public void pcsSitetabNavigate(String primarytab, String secondarytab,
			String fieldValue) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pcssiteIsbn);
			if (UIHelper.checkForAnElementbyXpath(driver, pcssiteIsbn)) {
				UIHelper.highlightElement(driver, pcssiteIsbn);
				UIHelper.sendKeysToAnElementByXpath(driver, pcssiteIsbn,
						fieldValue);
				UIHelper.click(driver, pcssiteOK);
				// String finalprimaryTab = primaryTab.replace("CRAFT",
				// primarytab);
				// String finalsecondaryTab = secondaryTab.replace("CRAFT",
				// secondarytab);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// finalprimaryTab);
				// UIHelper.click(driver, finalprimaryTab);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// finalsecondaryTab);
				// UIHelper.click(driver, finalsecondaryTab);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Search PCS site",
						"PCS site search for the provided value: </b>"
								+ fieldValue, Status.DONE);
			} else {

				report.updateTestLog("Search PCS site",
						"PCS site search for the provided value failed",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Search PCS site",
					"PCS site search for the provided value failed",
					Status.FAIL);
		}
	}

	// Common method to navigate in PCS site after isbn search
	public void pcsSitetab(String primarytab, String secondarytab,
			String fieldValue) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pcssiteIsbn);
			if (UIHelper.checkForAnElementbyXpath(driver, pcssiteIsbn)) {
				UIHelper.highlightElement(driver, pcssiteIsbn);
				UIHelper.sendKeysToAnElementByXpath(driver, pcssiteIsbn,
						fieldValue);
				UIHelper.click(driver, pcssiteOK);
				String finalprimaryTab = primaryTab
						.replace("CRAFT", primarytab);
				String finalsecondaryTab = secondaryTab.replace("CRAFT",
						secondarytab);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalprimaryTab);
				
				UIHelper.click(driver, finalprimaryTab);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						finalsecondaryTab);
		/*		UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);*/
				UIHelper.click(driver, finalsecondaryTab);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Search PCS site",
						"PCS site search for the provided value: </b>"
								+ fieldValue, Status.PASS);
			} else {

				report.updateTestLog("Search PCS site",
						"PCS site search for the provided value failed",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Search PCS site",
					"PCS site search for the provided value failed",
					Status.FAIL);
		}
	}
	
	
	//custom return of PCS dashlet
	public String custompcslabelreturn(String labelName, String textId) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, chooseRecord);
			String finallabelNameXpath = custlabelXpath.replace("CRAFT", labelName);
			String finallabelValueXpath = custlabellistXpath.replace("CRAFT",
					textId);
			if (UIHelper.checkForAnElementbyXpath(driver, finallabelNameXpath)) {
				UIHelper.highlightElement(driver, finallabelNameXpath);
				UIHelper.highlightElement(driver, finallabelValueXpath);
				Returnvalue = UIHelper.findAnElementbyXpath(driver,
						finallabelValueXpath).getAttribute("Title");
				report.updateTestLog(
						"Get Label value",
						"Value for "
								+ labelName
								+ " is retrieved sussefully. <br><b> Returnvalue : </b>"
								+ Returnvalue, Status.DONE);
			} else {
				report.updateTestLog("Get Label value", "Value for "
						+ labelName + " is not retrieved.", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Get Label value", "Value for " + labelName
					+ " is not retrieved.", Status.FAIL);
		}
		return Returnvalue;
	}
	
}
