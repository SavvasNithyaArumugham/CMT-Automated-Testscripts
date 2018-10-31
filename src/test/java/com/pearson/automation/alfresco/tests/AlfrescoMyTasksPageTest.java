package com.pearson.automation.alfresco.tests;

import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

public class AlfrescoMyTasksPageTest extends ReusableLibrary {
	
	private String myTaskFileVersionXpath = ".//*[@id='document-version']";
	private String myTaskLastVersionXpath = ".//*[@id='template_x002e_document-versions_x002e_document-details_x0023_default-latestVersion']/div[1]/span";
	private String assetSectionXpath = ".//*[contains(@id,'default_assoc_packageItems-cntrl-currentValueDisplay')]";
	private String assetViewMoreXpath = ".//*[contains(@id,'default_assoc_packageItems-cntrl-currentValueDisplay')]//td[2]//h3//a[text()='CRAFT']//ancestor::tr//td[3]//div[1]/a";
	//Modified as part of NALS
	//private String assetXpath = ".//*[contains(@id,'default_assoc_packageItems-cntrl-currentValueDisplay')]//td[2]//h3//a[text()='CRAFT']";
	private String assetXpath = ".//*[contains(@id,'default-WorkflowForm-alf-id1_assoc_packageItems-cntrl')]//td[2]//h3//a[text()='CRAFT']";
	
	
	public AlfrescoMyTasksPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	/**Method for verify Uploaded file Version
	 * @author 412766
	 * @param fileName
	 */
	public void verifyUploadedFileWithLastVersion(String fileName){
		try{
			WebElement myTaskFileVersionWebElement = UIHelper.findAnElementbyXpath(driver, myTaskFileVersionXpath);
			WebElement myTaskLastVersionWebElement = UIHelper.findAnElementbyXpath(driver, myTaskLastVersionXpath);
			
			if(myTaskFileVersionWebElement.getText().equals(myTaskLastVersionWebElement.getText())){
				report.updateTestLog("Verify Uploaded File("+fileName+") with Last Version", "Last version updated successfully"
						+ "<br /><b>Expected Result:</b>"+myTaskFileVersionWebElement.getText()
						+", <br /><b>Actual Result:</b>"+myTaskLastVersionWebElement.getText(), Status.PASS);
			}else{
				report.updateTestLog("Verify Uploaded File("+fileName+") with Last Version", "Last version not update successfully"
						+ "<br /><b>Expected Result:</b>"+myTaskFileVersionWebElement.getText()
						+", <br /><b>Actual Result:</b>"+myTaskLastVersionWebElement.getText(), Status.PASS);
			}
		}catch(Exception e){
			report.updateTestLog("Verify Uploaded File with Last Version", "Verify Uploaded File with Last Version Failed", Status.FAIL);
		}
	}
	
	public void verifyAssetinWFTask(String fileName, String Msg){
		try{
			//Modified as part of NALS
			
			String finalassetXpath = assetXpath
					.replace("CRAFT",fileName );
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalassetXpath);
			UIHelper.mouseOveranElement(driver, UIHelper.findAnElementbyXpath(driver, finalassetXpath));
			
			/*String finalassetViewMoreXpath = assetViewMoreXpath
					.replace("CRAFT",fileName );
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalassetViewMoreXpath);
			UIHelper.highlightElement(driver, finalassetViewMoreXpath);*/
			report.updateTestLog("Verify Asset in WF task", "Verify Asset in WF task Suessfully"
					+ "<br /><b> Asset Name : </b> " 
					+fileName, Status.PASS);
			UIHelper.click(driver, finalassetXpath);
			
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
		}catch(Exception e){
			report.updateTestLog("Verify Asset in WF task", "Verify Asset in WF task failed.Asset Not found"
					, Status.FAIL);
		}
		}
	
	/**
	 * @author 412766
	 */
	public void verifyActiveTaskSelectedByDefault(){
		try{
			if(new AlfrescoMyTasksPage(scriptHelper).isActiveTaskAvailable()){
				report.updateTestLog("Verify the 'Active Task' selected by default",
						"'Active Task' selected by Default successfully",
						Status.PASS);
			}else{
				report.updateTestLog("Verify the 'Active Task' selected by default",
						"'Active Task' not selected by Default",
						Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Verify the 'Active Task' selected by default",
					"Verify the 'Active Task' selected by default Failed",
					Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 */
	public void verifyCompletedTaskIsSelected(){
		try{
			if(new AlfrescoMyTasksPage(scriptHelper).isCompletedTaskAvailable()){
				report.updateTestLog("Verify the 'Completed Task' has been selected",
						"'Completed Task' selected successfully",
						Status.PASS);
			}else{
				report.updateTestLog("Verify the 'Completed Task' has been selected",
						"'Completed Task' not selected",
						Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Verify the 'Completed Task' has been selected",
					"Verify the 'Completed Task' has been selected Failed",
					Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 */
	public void verifyOverDueTaskIsSelected(){
		try{
			if(new AlfrescoMyTasksPage(scriptHelper).isOverDueTaskAvailable()){
				report.updateTestLog("Verify the 'Over Due Task' has been selected",
						"Over Due Task' selected successfully",
						Status.PASS);
			}else{
				report.updateTestLog("Verify the 'Over Due Task' has been selected",
						"'Over Due Task' not selected",
						Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Verify the 'Over Due Task' has been selected",
					"Verify the 'Over Due Task' has been selected Failed",
					Status.FAIL);
		}
	}
	
	/**
	 * @author 412766
	 */
	public void verifyHighPriorityTaskIsSelected(){
		try{
			if(new AlfrescoMyTasksPage(scriptHelper).isHighPriorityTaskAvailable()){
				report.updateTestLog("Verify the 'High Priority Task' has been selected",
						"'High Priority Task' selected successfully",
						Status.PASS);
			}else{
				report.updateTestLog("Verify the 'Over Due Task' has been selected",
						"'High Priority Task' not selected",
						Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Verify the 'High Priority Task' has been selected",
					"Verify the 'High Priority Task' has been selected Failed",
					Status.FAIL);
		}
	}
}
