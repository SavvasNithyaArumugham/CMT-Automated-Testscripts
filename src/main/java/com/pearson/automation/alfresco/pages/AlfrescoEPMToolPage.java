package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * Functional Components class
 * 
 * @author Cognizant
 */
public class AlfrescoEPMToolPage extends ReusableLibrary {
	private String findBtnXpath = ".//*[@id='find-button']";
	private String findBtnXpathInFindTitlesPage = ".//*[@class='find-button']//*[contains(@value,'Find')]";

	private String listTitlesWithFieldXpath = ".//*[contains(@id,'epmNormalFind_txtCriteria')]";
	private String searchTypeOptionXpath = "//*[@class='searchTypeOptions']//label[contains(text(),'CRAFT')]//preceding-sibling::input[contains(@name,'SearchType')]";

	private String tempXpathForShortTitle = ".//table[contains(@class,'tblSearchResult')]//tr/td[5][contains(.,'CRAFT')]//ancestor::tr//td[1]";
	private String biblioLinkXpath = ".//table/tbody/tr/td/a[contains(.,'Biblio')]";
	private String historyLinkXpath = ".//table/tbody/tr/td/a[contains(.,'History')]";
	private String biblioFieldLabelsXpath = ".//*[@id='biblio-container']//*[@class='row']/label[not(contains(.,'Bled'))]";
	private String biblioFieldValuesXpath = ".//*[@id='biblio-container']//*[@class='row']/input[not(contains(@id,'txtPrice'))]";
	
	private String publicationDateFieldXpathInHistorySec = ".//*[@id='history-container']//label[contains(.,'Publication Date')]";
	private String historyFieldLabelsXpath = ".//*[@id='history-container']//*[@class='row']/label[contains(.,'Publication Date') or contains(.,'Copyright Year')]";
	private String historyFieldValuesXpath = ".//*[@id='history-container']//*[@class='row']/label[contains(.,'Publication Date') or contains(.,'Copyright Year')]//following-sibling::input";
	
	private String exitBtnXpath = ".//*[contains(@id,'StatusBar_btnExit')]";
	
	private String biblioDataFilePath = "src/test/resources/AppTestData/TestOutput/BiblioData.txt";
	private String epmDashletXPath =".//*[@id='epmDashlet']";
	private String epmArchivesXPath =".//*[@name='dbSearch-epm']//span[contains(.,'Archives')]";
	private String epmSearchXPath =".//*[@id='lblEpmSearch-epm']";
	
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoEPMToolPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	//Perform EPM search in EPM tool
	public void performSearchInEPMTool(String searchContent, String property)
	{
		try
		{
			UIHelper.waitForVisibilityOfEleByXpath(driver, findBtnXpath);
			
			
			UIHelper.click(driver, findBtnXpath);
			UIHelper.waitFor(driver);
			String parentWindowID = driver.getWindowHandle();
		
			for(String window:driver.getWindowHandles())
			{
				if(!window.equalsIgnoreCase(parentWindowID))
				{
					driver.switchTo().window(window);
				}
			}
			
			UIHelper.waitForVisibilityOfEleByXpath(driver,findBtnXpathInFindTitlesPage);
			String finalRadioBtnXpath=searchTypeOptionXpath.replace("CRAFT", property);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalRadioBtnXpath);
			UIHelper.click(driver, finalRadioBtnXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, listTitlesWithFieldXpath, searchContent);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, findBtnXpathInFindTitlesPage);
			Thread.sleep(6000);
			report.updateTestLog("Perform EPM Search in 'EPM Application'",
					"Search Records displayed Sucessfully using "+
			"<br> List Titles With:"+searchContent+"<br> Search Type: "+property, Status.DONE);
			driver.switchTo().window(parentWindowID);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Click on Title in EPM
	public void clickOnTitleInEPMTool(String isbnValue)
	{
		try
		{
			String finalXpathForShortTitle = tempXpathForShortTitle.replace("CRAFT", isbnValue);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForShortTitle);
			
			if(UIHelper.checkForAnElementbyXpath(driver, finalXpathForShortTitle))
			{
				WebElement ShortTitleEle = UIHelper.findAnElementbyXpath(driver, finalXpathForShortTitle);
				UIHelper.scrollToAnElement(ShortTitleEle);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, ShortTitleEle);
				UIHelper.mouseOverandElementdoubleClick(driver, ShortTitleEle);
				UIHelper.waitFor(driver);
				report.updateTestLog("Open EPM Search Result Item in 'EPM Application'",
						"Search Records opened Sucessfully using "+
				"<br> ISBN:"+isbnValue, Status.DONE);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the Biblio Data from EPM and store it into
	// Text File
	public boolean compareAlfrescoBiblioDataWithEPMBiblio() {
		boolean isDisplayedAlfrescoEPMFieldsInEPMTool = false;
		try {
			
			String parentWindowID = driver.getWindowHandle();
			for(String window:driver.getWindowHandles())
			{
				if(!window.equalsIgnoreCase(parentWindowID))
				{
					driver.switchTo().window(window);
				}
			}
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, biblioLinkXpath);
			UIHelper.waitFor(driver);
			
			//Getting the Biblio Field Values
			ArrayList<String> epmToolFieldsData = new ArrayList<String>();
			ArrayList<String> alfrescoEPMFieldsData = new FileUtil().readListOFDataFromFile(biblioDataFilePath);
			
			List<WebElement> biblioFieldTitlesListEle = UIHelper
					.findListOfElementsbyXpath(biblioFieldLabelsXpath, driver);
			List<WebElement> biblioFieldValuesListEle = UIHelper
					.findListOfElementsbyXpath(biblioFieldValuesXpath, driver);

			ArrayList<String> biblioFieldTitlesList = new ArrayList<String>();
			ArrayList<String> biblioFieldValuesList = new ArrayList<String>();

			for (WebElement fieldTitleEle : biblioFieldTitlesListEle) {
				biblioFieldTitlesList.add(fieldTitleEle.getText().trim());
			}

			for (WebElement fieldValueEle : biblioFieldValuesListEle) {
				biblioFieldValuesList.add(""
						+ fieldValueEle.getAttribute("value").trim());
			}

			if (biblioFieldTitlesList != null && biblioFieldValuesList != null
					&& biblioFieldTitlesList.size() == biblioFieldValuesList.size()) {
				for (int index = 0; index < biblioFieldTitlesList.size(); index++) {
					epmToolFieldsData.add(biblioFieldTitlesList.get(index)
					+ ":" + biblioFieldValuesList.get(index));
				}
			}
			
			if(UIHelper.checkForAnElementbyXpath(driver, historyLinkXpath))
			{
				UIHelper.click(driver, historyLinkXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, publicationDateFieldXpathInHistorySec);
				UIHelper.waitFor(driver);
			}
			
			//Getting the History Field Values
			List<WebElement> historyFieldTitlesListEle = UIHelper
					.findListOfElementsbyXpath(historyFieldLabelsXpath, driver);
			List<WebElement> historyFieldValuesListEle = UIHelper
					.findListOfElementsbyXpath(historyFieldValuesXpath, driver);
			
			ArrayList<String> historyFieldTitlesList = new ArrayList<String>();
			ArrayList<String> historyFieldValuesList = new ArrayList<String>();

			for (WebElement fieldTitleEle : historyFieldTitlesListEle) {
				historyFieldTitlesList.add(fieldTitleEle.getText().trim());
			}

			for (WebElement fieldValueEle : historyFieldValuesListEle) {
				historyFieldValuesList.add(""
						+ fieldValueEle.getAttribute("value").trim());
			}

			if (historyFieldTitlesList != null && historyFieldValuesList != null
					&& historyFieldTitlesList.size() == historyFieldValuesList.size()) {
				for (int index = 0; index < historyFieldTitlesList.size(); index++) {
					epmToolFieldsData.add(historyFieldTitlesList.get(index)
					+ ":" + historyFieldValuesList.get(index));
				}
			}
			
			isDisplayedAlfrescoEPMFieldsInEPMTool = UIHelper.compareTwoDiffSizeOfLists(alfrescoEPMFieldsData, epmToolFieldsData);
			
			if (isDisplayedAlfrescoEPMFieldsInEPMTool) {
				report.updateTestLog(
						"Verify EPM application field details in Alfresco EPM Search Result",
						"EPM application field details are displayed successfully in Alfresco EPM Search Result", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify EPM application field details in Alfresco EPM Search Result",
						"EPM application field details are failed to display in Alfresco EPM Search Result", Status.FAIL);
			}
			
			UIHelper.click(driver, exitBtnXpath);
			UIHelper.waitFor(driver);
			
			for(String window:driver.getWindowHandles())
			{
				if(window.equalsIgnoreCase(parentWindowID))
				{
					driver.switchTo().window(window);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isDisplayedAlfrescoEPMFieldsInEPMTool;
	}

	public boolean verifyEPMDashletInSiteDashBoard(){
		boolean isEPMDahletavailable = false;
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, epmDashletXPath);
			if(UIHelper.checkForAnElementbyXpath(driver, epmDashletXPath)&&
					UIHelper.checkForAnElementbyXpath(driver, epmArchivesXPath)&&
					UIHelper.checkForAnElementbyXpath(driver, epmSearchXPath)){
				UIHelper.highlightElement(driver, epmDashletXPath);
				UIHelper.highlightElement(driver, epmArchivesXPath);
				UIHelper.highlightElement(driver, epmSearchXPath);
				isEPMDahletavailable = true;
			}
			else{
				isEPMDahletavailable = false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return isEPMDahletavailable;
	}
}