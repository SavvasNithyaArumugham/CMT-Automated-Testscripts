package com.pearson.automation.alfresco.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteFinderPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoSiteFinderPageTest extends ReusableLibrary{
	
	private String sitesMenuXpath = ".//*[@id='HEADER_SITES_MENU']/span[text()='Sites']";
	private String mySitesMenuXpath = ".//*[@id='HEADER_SITES_MENU_MY_SITES_text']/a[text()='My Sites']";
	private String headerXpath = ".//*[@id='HEADER_TITLE']";
	private String mySiteXpath = ".//*[@id='HEADER_SITES_MENU_MY_SITES_text']/a";
	private String siteMenuXpath = ".//*[@id='HEADER_SITES_MENU']";
	private String sitesListXpath = ".//*[@class='sticky-wrapper']//*[@class='viewcolumn']//ul/li/p/a";
	private String sitesNameXpath = ".//*[@id='template_x002e_user-sites_x002e_user-sites_x0023_default-sites']/table/tbody[2]/tr/td[2]//div/div/a[text()='CRAFT']";

	public AlfrescoSiteFinderPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author 412766
	 */
	public void verifyDeleteOptionInSiteFinder(String siteName){
		try{
			if(new AlfrescoSiteFinderPage(scriptHelper).isSiteFinderDeleteOptionAvailable()){
				report.updateTestLog("Verify 'Delete' site option in site finder page",
						"Delete option found"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.FAIL);
			}else{
				report.updateTestLog("Verify 'Delete' site option in site finder page",
						"Delete option Not found"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.PASS);
			}
		}catch(Exception e){
			report.updateTestLog("Verify 'Delete' site option in site finder page",
					"Verify 'Delete' site option in site finder page Failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 * @param siteName
	 */
	public void verifyDeleteOptionInSiteManager(String siteName){
		try{
			if(new AlfrescoSiteFinderPage(scriptHelper).isSiteDeleteOptionAvailableForAdmin(siteName)){
				report.updateTestLog("Verify 'Delete Site' option avaialble for ADMIN user",
						"Delete Site option found"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.PASS);
			}else{
				report.updateTestLog("Verify 'Delete Site' option avaialble for ADMIN user",
						"Delete Site option Not found"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.FAIL);
			}
		}catch(Exception e){
			report.updateTestLog("Verify 'Delete Site' option avaialble for ADMIN user",
					"Verify 'Delete Site' option avaialble for ADMIN user Failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 * @param siteName
	 */
	public void verifyManagerAppliedInSiteManager(String siteName){
		try{
			if(new AlfrescoSiteFinderPage(scriptHelper).isSiteManagerApplied()){
				report.updateTestLog("Verify User became a 'Site Manager'",
						"User became 'Site Manager'"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.PASS);
			}else{
				report.updateTestLog("Verify User became a 'Site Manager'",
						"User Not became 'Site Manager'"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.FAIL);
			}
		}catch(Exception e){
			report.updateTestLog("Verify User became a 'Site Manager'",
					"Verify User became a 'Site Manager' Failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 * @param siteName
	 */
	public void verifySiteManagerPageDisplayed(String adminToolsOption){
		try{
			if(new AlfrescoSiteFinderPage(scriptHelper).isPageHeaderDisplayed(adminToolsOption)){
				report.updateTestLog("Verify '"+adminToolsOption+"' page displayed in 'ADMIN Tools'",
						"Page Displayed Successfully"						
								+ "</br><b>Page Name : </b>"
								+ adminToolsOption, Status.PASS);
			}else{
				report.updateTestLog("Verify '"+adminToolsOption+"' page displayed in 'ADMIN Tools'",
						"Page not Displayed"						
								+ "</br><b>Page Name : </b>"
								+ adminToolsOption, Status.FAIL);
			}
		}catch(Exception e){
			report.updateTestLog("Verify '"+adminToolsOption+"' page displayed in 'ADMIN Tools'",
					"Verify '"+adminToolsOption+"' page displayed in 'ADMIN Tools' Failed", Status.FAIL);
		}
	}
	
	// Verify char length of the sites form my sites
	public void verifyCharLenMySitesPage(String siteName) {
		int length = 0;
		try {

			WebElement siteMenuEle = UIHelper.findAnElementbyXpath(driver, siteMenuXpath);
			UIHelper.mouseOveranElement(driver, siteMenuEle);
			UIHelper.highlightElement(driver, siteMenuEle);
			UIHelper.click(driver, siteMenuXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, mySiteXpath);
			UIHelper.click(driver, mySiteXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitesListXpath);
			List<WebElement> siteElementsList = driver.findElements(By
					.xpath(sitesListXpath));
			
			for (WebElement siteEle : siteElementsList) {				

				if (siteEle.getText().equals(siteName)) {
					
					UIHelper.scrollToAnElement(siteEle);
					length = siteEle.getText().length();
					UIHelper.highlightElement(driver, siteEle);
					report.updateTestLog(
							"Verify Site name",
							"Site name displayed with "
									+ length
									+ " characters <br/><b>Expected Site Name:</b> <br/>"
									+ siteName + "<br/><b>Actual Site Name:</b> <br/>"
									+ siteEle.getText(), Status.PASS);

					break;

				} 

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Site name",
					"Failed to load site results", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 * @param siteName
	 */
	public void verifyDeletedSite(String siteName) {
		try{
			if(!(new AlfrescoSitesPage(scriptHelper).siteFinderOption(siteName))){
				report.updateTestLog("Verify ADMIN User able to delete a site",
						"Site Deleted successfully"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.PASS);
			}else{
				report.updateTestLog("Verify ADMIN User able to delete a site",
						"Site Not Deleted"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Verify ADMIN User able to delete a site",
					"Verify ADMIN User able to delete a site Failed", Status.FAIL);
		}
	}
}
