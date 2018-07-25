package com.pearson.automation.alfresco.pages;

import java.util.List;

import javax.servlet.jsp.tagext.TagInfo;

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
public class AlfrescoDocPreviewPage extends ReusableLibrary{
	
	private String linkLocationDetails = ".//*[@id='link-function-table']//b[text()='CRAFT']//ancestor::tr//td[2]/a";
	private String tagLinkListXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']//h3//a[text()='CRAFT']//ancestor::td//div[3]//span/a";
	private String tagTwisterXpath = ".//h2[contains(@class,'thin dark alfresco-twister')]//ancestor::div//h2[contains(.,'CRAFT')]";
	private String tagDocActionListXpath = ".//h2[contains(@class,'thin dark alfresco-twister')]//ancestor::div//h2[contains(.,'Tags')]//following-sibling::div//span";
	
	private String selectButtonXpath = ".//button[text()='Select']";
	private String loadingXpath = ".//div[text()='Loading...']";
	private String createNewTagInputXapth = ".//input[@class='create-new-input']";
	private String removeTagIconXapth = ".//*[@class='yui-dt-data']//td//h3[text()='CRAFT']//ancestor::tr//*[@class='removeIcon']";
	private String createTagIconXapth = ".//span[@class='createNewIcon']";
	private String okButonXpath = ".//button[text()='OK']";
	private String saveTagButton = ".//button[text()='Save']";
	private String docLibLoadingPopUp = ".//*[@id='message']//div//span[contains(text(),'Loading the Document Library')]";
	
	private String tempPreviewDelRelXpath = ".//table[@id='relationship-function-table']//td/a[contains(text(),'CRAFT')]//ancestor::tr//img[@id='delete-relationship']";

	public AlfrescoDocPreviewPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author 412766
	 * @param folderName
	 * @return boolean
	 */
	public void isLinkedLocationURLAvailable(String link){
	
		try{
			String finalXpath = linkLocationDetails.replace("CRAFT", link);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpath);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalXpath))){
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpath));
				UIHelper.highlightElement(driver, finalXpath);
				
				report.updateTestLog("Verify "+ link +" URL ",
						link +" Clicked successfully" 
				+UIHelper.getTextFromWebElement(driver, finalXpath), Status.PASS);
				
			}else{
				report.updateTestLog("Verify "+ link +" URL",
						link +" Clicked failed " 
				+UIHelper.getTextFromWebElement(driver, finalXpath), Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Verify "+ link +" URL ",
					link +" Clicked failed" 
			, Status.FAIL);
		}
		
	}
	
	/**
	 * @author 317085
	 * @param link
	 */
	public void clickLinkedLocationURL(String link){
		try{
			String finalXpath = linkLocationDetails.replace("CRAFT", link);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpath);
			if(UIHelper.checkForAnElementbyXpath(driver, finalXpath)){
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpath));
				UIHelper.highlightElement(driver, finalXpath);
				report.updateTestLog("Click "+ link +" URL ",
						link +" Clicked successfully" 
				+UIHelper.getTextFromWebElement(driver, finalXpath), Status.PASS);
				UIHelper.click(driver, finalXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
			}else{
				report.updateTestLog("Click Linked Location URL",
						"Not able to Click", Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Click Linked Location URL",
					"Not able to Click", Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 * @param fileName
	 * @param tagName
	 * @return boolean
	 */
	public boolean isTagCreated(String fileName, String tagName, boolean isMultiTag){
		boolean flag = false;
		try{
			String finalXpath = tagLinkListXpath.replace("CRAFT", fileName);
			if(isMultiTag){
				String[] tagNames = tagName.split(",");
				int index = 0;
				List<WebElement> tagList = UIHelper.findListOfElementsbyXpath(finalXpath, driver);
				for (WebElement webElement : tagList) {
					if(checkTag(webElement.toString(), tagNames[index])){
						flag = true;
						index++;
					}else{
						flag = false;
						break;
					}
				}
			}else{
				if(checkTag(finalXpath, tagName)){
					flag = true;
				}else{
					flag = false;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * @author 412766
	 * @param finalXpath
	 * @param tagName
	 * @return
	 */
	private boolean checkTag(String finalXpath, String tagName){
		boolean flag = false;
		UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpath);
		if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalXpath))){
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpath));
			UIHelper.highlightElement(driver, finalXpath);
			String actualTagName = UIHelper.getTextFromWebElement(driver, finalXpath);
			if(tagName.equalsIgnoreCase(actualTagName)){
				flag = true;
			}else{
				flag = false;
			}
		}else{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @author 412766
	 * @param fileName
	 */
	public void clickTagLink(String fileName, boolean isMultiTag){
		try{
			String finalXpath = tagLinkListXpath.replace("CRAFT", fileName);
			if(isMultiTag){
				List<WebElement> tagList = UIHelper.findListOfElementsbyXpath(finalXpath, driver);
				finalXpath = tagList.get(0).toString();
			}
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpath);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalXpath))){
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXpath));
				UIHelper.highlightElement(driver, finalXpath);
				report.updateTestLog("Click a TAG Link",
						"Clicked successfully", Status.PASS);
				UIHelper.click(driver, finalXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
			}else{
				report.updateTestLog("Click a TAG Link",
						"Not able to Click", Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 412766
	 */
	public void openTagTwister(String option){
		try{
			String finalXapth = tagTwisterXpath.replace("CRAFT", option);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXapth);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalXapth))){
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalXapth));
				UIHelper.highlightElement(driver, finalXapth);
				UIHelper.waitFor(driver);
				report.updateTestLog("Click a TAG Twister",
						"Clicked successfully", Status.PASS);
				UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver, finalXapth));
				UIHelper.waitFor(driver);
			}else{
				report.updateTestLog("Click a TAG Twister",
						"Not able to Click", Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 412766
	 * @param tagName
	 * @param editTagName
	 */
	public void editTag(String tagName, String editTagName){
		try{
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectButtonXpath);
			UIHelper.highlightElement(driver, selectButtonXpath);
			UIHelper.click(driver, selectButtonXpath);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loadingXpath);
			
			String finalXpath = removeTagIconXapth.replace("CRAFT", tagName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpath);
			UIHelper.highlightElement(driver, finalXpath);
			UIHelper.click(driver, finalXpath);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, createNewTagInputXapth);
			UIHelper.highlightElement(driver, createNewTagInputXapth);
			UIHelper.sendKeysToAnElementByXpath(driver, createNewTagInputXapth, editTagName);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, createTagIconXapth);
			UIHelper.highlightElement(driver, createTagIconXapth);
			UIHelper.click(driver, createTagIconXapth);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, okButonXpath);
			UIHelper.highlightElement(driver, okButonXpath);
			UIHelper.click(driver, okButonXpath);
			UIHelper.waitFor(driver);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, saveTagButton);
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, saveTagButton))){
				UIHelper.highlightElement(driver, saveTagButton);
				report.updateTestLog("Edit TAG through Edit Properties",
						"Edited successfully", Status.PASS);
				UIHelper.click(driver, saveTagButton);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, docLibLoadingPopUp);
			}else{
				report.updateTestLog("Edit TAG through Edit Properties",
						"Not able to Edit TAG", Status.FAIL);
			}
			
			UIHelper.waitFor(driver);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param tagName
	 * @param isMultiTag
	 * @return boolean
	 */
	public boolean checkTagInDocActions(String tagName, boolean isMultiTag){
		boolean flag = false;
		try{
			if(isMultiTag){
				String[] tagNames = tagName.split(",");
				int index = 0;
				List<WebElement> tagList = UIHelper.findListOfElementsbyXpath(tagDocActionListXpath, driver);
				for (WebElement webElement : tagList) {
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, webElement.toString()));
					UIHelper.highlightElement(driver, webElement.toString());
					if(webElement.getText().equalsIgnoreCase(tagNames[index])){
						flag = true;
						index++;
					}else{
						flag = false;
						break;
					}
				}
			}else{
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, tagDocActionListXpath));
				UIHelper.highlightElement(driver, tagDocActionListXpath);
				if(UIHelper.getTextFromWebElement(driver, tagDocActionListXpath).equalsIgnoreCase(tagName)){
					flag = true;
				}else{
					flag = false;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * @author 412766
	 * @param relationshipFileName
	 * @return
	 */
	public boolean isDeleteRelatioshipAvailableInPreview(String relationshipFileName) {
		boolean flag = false;
		try {
			String finalPreviewDelRelXapthXapth = tempPreviewDelRelXpath.replace(
					"CRAFT", relationshipFileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalPreviewDelRelXapthXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, finalPreviewDelRelXapthXapth))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalPreviewDelRelXapthXapth));
				UIHelper.highlightElement(driver, finalPreviewDelRelXapthXapth);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
