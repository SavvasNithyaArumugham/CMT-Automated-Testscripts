package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoSiteFinderPage extends ReusableLibrary {
	
	private String deleteOptionXpath = ".//*[contains(text(),'Delete')]";
	private String deletionSiteButtonXpath="//a[text()='CRAFT']//ancestor::tr//td//span/button[text()='Delete']";
	private String siteManagerPanelXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[contains(@id,'admin-console_x0023_default')]//*[@id='DOCLIB_DOCUMENT_LIST']";
	private String tempSiteManagerActionXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='DOCLIB_DOCUMENT_LIST']//*[@class='info rendered-view']//div//table//tbody//tr//td//span[text()='CRAFT']//ancestor::td[1]//following-sibling::td[4]//div[contains(@id,'alfresco_menus_AlfMenuBarPopup')]";
	private String deleteSiteOptionXpath = ".//*[@id='Share']//div[@class='dijitPopup Popup']//*[contains(text(),'Delete Site')]";
	private String becameSiteManagerXpath = ".//*[@id='Share']//div[@class='dijitPopup Popup']//*[contains(text(),'Become Site Manager')]";
	private String siteLoadingXpath = ".//*[@id='DOCLIB_DOCUMENT_LIST']/div[4]";
	private String deleteConfirmOKXpath = ".//*[@id='ALF_SITE_SERVICE_DIALOG']/div[2]/div[2]/span[1]/span";
	private String adminToolsTitleXpath = ".//*[@id='HEADER_TITLE']";
	
	private String backXpath = ".//span[text()='< Back']";
	private String nextXpath = ".//span[text()='Next >']";
	private String tempVisibilityDropDownXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='DOCLIB_DOCUMENT_LIST']//*[@class='info rendered-view']//div//table//tbody//tr//td//span[text()='CRAFT']//ancestor::td[1]//following-sibling::td[2]//div[contains(@class,'alfresco-renderers-PublishingDropDownMenu')]//div[4]//div[2]//table//tbody//tr";
	
	private String popumMenuXpath = ".//div[contains(@class,'dijitPopup dijitMenuPopup') and contains(@style,'visible')]";
	private String tempVisibilityTypeXpath = ".//div[contains(@class,'dijitPopup dijitMenuPopup') and contains(@style,'visible')]/table/tbody/tr//td[contains(.,'CRAFT')]";
	private String tempVisibilityValueXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='DOCLIB_DOCUMENT_LIST']//*[@class='info rendered-view']//div//table//tbody//tr//td//span[text()='CRAFT']//ancestor::td[1]//following-sibling::td[2]//div[contains(@class,'alfresco-renderers-PublishingDropDownMenu')]//div[4]//div[2]//table//tbody//tr//span";
	
	private String tempVisibiltyInput = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='DOCLIB_DOCUMENT_LIST']//*[@class='info rendered-view']//div//table//tbody//tr//td//span[text()='CRAFT']//ancestor::td[1]//following-sibling::td[2]//div[contains(@class,'alfresco-renderers-PublishingDropDownMenu')]//div[4]//div[2]//table//tbody//tr//td[1]//div[2]//input";
	
	
	private String siteManagerRowXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//table//tbody//tr[2]";
	private String siteManagerLableXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//table//tbody//tr[2]//td[3]//div//div";
	private String removeButtonXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//table//tbody//tr[2]//td[4]//div//span//span//span//button";
	private String alertMessageXpath = ".//*[@id='message']/div";
	private String deleteMessagePopUpXpath="//div[@class='underlay']";
	private String deleteButtonInPopUpXpath="//span[@class='yui-button yui-push-button alf-primary-button']//button[contains(text(),'Delete')]";
    private String yesButtonInDeleteSitePopUpXpath="//span/button[contains(text(),'Yes')]";
	private String deleteMessageXpath=".//*[@id='message']/div";
	
	public AlfrescoSiteFinderPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isSiteFinderDeleteOptionAvailable(){
		boolean flag = false;
		try{
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, deleteOptionXpath))){
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			
		}
		return flag;
	}
	
	
	// Is Delete button visible for particular siteName
	public boolean isSiteFinderDeleteOptionAvailableForSite(String siteName)
	{
		boolean flag=false;
		
		String finalDeletionSiteButtonXpath=deletionSiteButtonXpath.replace("CRAFT",siteName);
				
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalDeletionSiteButtonXpath);
			UIHelper.highlightElement(driver, finalDeletionSiteButtonXpath);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalDeletionSiteButtonXpath))){
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
		}
		
		return flag;
		
	}
	
	// Click on delete button in sitefinderPage for particular site
	public void deleteSiteInSiteFinder(String siteName) {
		try {

			String finalDeletionSiteButtonXpath=deletionSiteButtonXpath.replace("CRAFT",siteName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalDeletionSiteButtonXpath);
			UIHelper.highlightElement(driver, finalDeletionSiteButtonXpath);
			UIHelper.click(driver, finalDeletionSiteButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteMessagePopUpXpath);
			UIHelper.highlightElement(driver, deleteMessagePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,deleteButtonInPopUpXpath);
			UIHelper.highlightElement(driver, deleteButtonInPopUpXpath);
			UIHelper.click(driver, deleteButtonInPopUpXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteMessagePopUpXpath);
			UIHelper.highlightElement(driver, deleteMessagePopUpXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,yesButtonInDeleteSitePopUpXpath);
			UIHelper.highlightElement(driver, yesButtonInDeleteSitePopUpXpath);
			UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, yesButtonInDeleteSitePopUpXpath));
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, deleteMessageXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Site Delete in Site Finder Page", "Site Deleted successfully", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Site Delete in Site Finder Page", "Site Deletion Unsuccessfull", Status.FAIL);
		}

	}
	
	/**
	 * @author 412766
	 * @param siteName
	 * @return
	 */
	public boolean isSiteDeleteOptionAvailableForAdmin(String siteName){
		boolean flag = false;
		try{
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, siteLoadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteManagerPanelXpath);
			UIHelper.highlightElement(driver, siteManagerPanelXpath);
			String actionXpath = tempSiteManagerActionXpath.replace("CRAFT", siteName);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, actionXpath))){
				UIHelper.highlightElement(driver, actionXpath);
				UIHelper.click(driver, actionXpath);
				UIHelper.waitFor(driver);
				//UIHelper.waitForVisibilityOfEleByXpath(driver, deleteSiteOptionXpath);
				UIHelper.highlightElement(driver, deleteSiteOptionXpath);
				UIHelper.waitFor(driver);
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			
		}
		return flag;
	}
	
	/**
	 * @author 412766
	 */
	public void moveNextPageInSiteManager(){
		try{
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, siteLoadingXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, nextXpath);
			if(UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, nextXpath))){
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, nextXpath));
				UIHelper.highlightElement(driver, nextXpath);
				UIHelper.click(driver, nextXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, siteLoadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, siteManagerPanelXpath);
				if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, siteManagerPanelXpath))){
					UIHelper.highlightElement(driver, siteManagerPanelXpath);
					report.updateTestLog("Navigate to next page in 'Site Manager'",
							"Navigated Successfully", Status.PASS);
				}else{
					report.updateTestLog("Navigate to next page in 'Site Manager'",
							"Not able to Navigate", Status.FAIL);
				}
			}else{
				report.updateTestLog("Navigate to next page in 'Site Manager'",
						"Next option Not enabled", Status.DONE);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Navigate to next page in 'Site Manager'",
					"Navigate to next page in 'Site Manager' Failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 */
	public void moveBackPageInSiteManager(){
		try{
			
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, siteLoadingXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, backXpath);
			if(UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, backXpath))){
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, backXpath));
				UIHelper.highlightElement(driver, backXpath);
				UIHelper.click(driver, backXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, siteLoadingXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, siteManagerPanelXpath);
				if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, siteManagerPanelXpath))){
					UIHelper.highlightElement(driver, siteManagerPanelXpath);
					report.updateTestLog("Navigate to back page in 'Site Manager'",
							"Navigated Successfully", Status.PASS);
				}else{
					report.updateTestLog("Navigate to back page in 'Site Manager'",
							"Not able to Navigate", Status.FAIL);
				}
			}else{
				report.updateTestLog("Navigate to back page in 'Site Manager'",
						"Back option Not enabled", Status.DONE);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Navigate to back page in 'Site Manager'",
					"Navigate to back page in 'Site Manager' Failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 */
	public void changeVisibility(String visibilityType, String siteName){
		try{
			String finalVisibilityXpath = tempVisibilityDropDownXpath.replace("CRAFT", siteName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalVisibilityXpath);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalVisibilityXpath))){
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalVisibilityXpath));
				UIHelper.highlightElement(driver, finalVisibilityXpath);
				UIHelper.click(driver, finalVisibilityXpath);
				
				UIHelper.waitForVisibilityOfEleByXpath(driver, popumMenuXpath);
								
				UIHelper.waitFor(driver);
				String finalVisibilityTypeXpath = tempVisibilityTypeXpath.replace("CRAFT", visibilityType);
				WebElement typEle = UIHelper.findAnElementbyXpath(driver, finalVisibilityTypeXpath);
				UIHelper.highlightElement(driver, typEle);
				UIHelper.click(driver, finalVisibilityTypeXpath);
				
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
				String finalVisibilityValueXpath = tempVisibilityValueXpath.replace("CRAFT", siteName);
				if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalVisibilityValueXpath))){
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalVisibilityValueXpath));
					UIHelper.highlightElement(driver, finalVisibilityValueXpath);
					String visibilityValue = UIHelper.getTextFromWebElement(driver, finalVisibilityValueXpath);
					if(visibilityValue.equalsIgnoreCase(visibilityType)){						
						report.updateTestLog("Change Visibility in 'Site Manager'",
								"Visibility changed Successfully"											
										+ "</br><b>Site Name : </b>"
										+ siteName
										+ "</br><b>Visibility Type : </b>"
										+ visibilityType, Status.PASS);
						
					}else{
						report.updateTestLog("Change Visibility in 'Site Manager'",
								"Visibility not changed"											
										+ "</br><b>Site Name : </b>"
										+ siteName
										+ "</br><b>Visibility Type : </b>"
										+ visibilityType, Status.FAIL);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Change Visibility in 'Site Manager'",
					"Change Visibility in 'Site Manager' Failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 * @param siteName
	 */
	public void deleteSiteInSiteManager(String siteName){
		try{
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, siteLoadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteManagerPanelXpath);
			UIHelper.highlightElement(driver, siteManagerPanelXpath);
			String actionXpath = tempSiteManagerActionXpath.replace("CRAFT", siteName);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, actionXpath))){
				UIHelper.highlightElement(driver, actionXpath);
				UIHelper.click(driver, actionXpath);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, deleteSiteOptionXpath);
				UIHelper.click(driver, deleteSiteOptionXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, deleteConfirmOKXpath);
				if(UIHelper.checkForAnElementbyXpath(driver, deleteConfirmOKXpath)){
					UIHelper.highlightElement(driver, deleteConfirmOKXpath);
					report.updateTestLog("Delete a Site form 'Site Manager'",
							"Site Deleted Successfully"						
									+ "</br><b>Site Name : </b>"
									+ siteName, Status.PASS);
					UIHelper.click(driver, deleteConfirmOKXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
				}else{
					report.updateTestLog("Delete a Site form 'Site Manager'",
							"Site Not Deleted"						
									+ "</br><b>Site Name : </b>"
									+ siteName, Status.FAIL);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Delete a Site form 'Site Manager'",
					"Delete a Site form 'Site Manager' Failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 * @param siteName
	 */
	public void applyToBecomeSiteManager(String siteName){
		try{
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, siteLoadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteManagerPanelXpath);
			UIHelper.highlightElement(driver, siteManagerPanelXpath);
			String actionXpath = tempSiteManagerActionXpath.replace("CRAFT", siteName);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, actionXpath))){
				UIHelper.highlightElement(driver, actionXpath);
				UIHelper.click(driver, actionXpath);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, becameSiteManagerXpath);
				UIHelper.click(driver, becameSiteManagerXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("To Apply 'Site Manager'",
						"'Site Manager' applied Successfully"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.PASS);
				
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, siteLoadingXpath);
			}else{
				report.updateTestLog("To Apply 'Site Manager'",
						"'Site Manager' Not applied"						
								+ "</br><b>Site Name : </b>"
								+ siteName, Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("To Apply 'Site Manager'",
					"To Apply 'Site Manager' Failed", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 * @return
	 */
	public boolean isPageHeaderDisplayed(String headerName) {
		boolean flag = false;
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, siteLoadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteManagerPanelXpath);
			UIHelper.highlightElement(driver, siteManagerPanelXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, adminToolsTitleXpath);
			if (UIHelper.getTextFromWebElement(driver, adminToolsTitleXpath).equalsIgnoreCase(headerName)) {
				UIHelper.highlightElement(driver, adminToolsTitleXpath);
				UIHelper.waitFor(driver);
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
	 * @return
	 */
	public boolean isSiteManagerApplied(){
		boolean flag = false;
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteManagerRowXpath);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, siteManagerRowXpath))){
				UIHelper.highlightElement(driver, siteManagerRowXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, siteManagerLableXpath);
				if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, siteManagerLableXpath))){
					UIHelper.highlightElement(driver, siteManagerLableXpath);
					String value = UIHelper.getTextFromWebElement(driver, siteManagerLableXpath).trim();
					System.out.println("MANAGER VALUE " + value);
					if(value.equalsIgnoreCase("Manager")){
						flag = true;
					}else{
						flag = false;
					}
				}
				else{
					flag = false;
				}
			}else{
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * @author 412766
	 */
	public void removeSiteManager(){
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, removeButtonXpath);
			UIHelper.highlightElement(driver, removeButtonXpath);
			UIHelper.click(driver, removeButtonXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, alertMessageXpath);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
