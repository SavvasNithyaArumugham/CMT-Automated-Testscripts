package com.pearson.automation.alfresco.pages;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * Functional Components class
 * 
 * @author Cognizant
 */

public class AlfrescoAVSmartLinkPage extends ReusableLibrary {

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoAVSmartLinkPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	private String smartlinktype = ".//*[contains(@class,'accordion-section')]//span[text()='CRAFT']";
	private String smarttitle = ".//*[@id='titleCRAFT']";
	private String smartexttitle = ".//*[@id='externalUrlLinkCRAFT']";
	private String smartuploadimg = ".//input[@name='uploadFileCRAFT']";
	private String imgAltxpath = ".//*[@id='imageAltTextCRAFT']";
	private String captionxpath = ".//*[@id='captionTextCRAFT']";
	private String credittxtxpath = ".//*[@id='copyRightOrCreditTextCRAFT']";
	private String imgrefxpath = ".//*[@id='imageRefURLCRAFT']";
	public String cancelsubxpath = ".//form[@id='CRAFT']//*[@value='REPLACE']";
	private String smartuploadbtn = ".//label[@for='uploadFileCRAFT']";
	public String submitbtn = "//form[@id='CRAFT']//input[@value='Submit']";
	public String jsonString = "//*[contains(text(),'JSON')]";
	public String copycredit = ".//*[@id='copyRightOrCreditTextCRAFT']//..//label";
	public String imgrefxpathcheck = ".//*[@id='imageRefURLCRAFT']//..//label";

	private String uploadedxpath = ".//p[@class='selectedFileNameCRAFT']";
	private String selectxpath = ".//*[@id='select-CRAFT']";
	private String selectedxpath = "//*[@id='selectTextCRAFT']";

	public String messageXpath = ".//*[@id='message']/div/span";
	public String sample1 ="//*[@id='CRAFT']//td[(text()= 'Optimized for use on Mobile?')]";
    public String sample2 = "//*[@id='CRAFT']//td[(text()= '3PI Vendor')]";	
    public String heightXpath=".//input[@id='threePiMetroHeightCRAFT']";
    public String widthXpath=".//input[@id='threePiMetroWidthCRAFT']";
    public String labelCheckXpath="//form[@id='CRAFT']//*[contains(text(),'LABELNAME')]";
    
	// edit smart link

	public String edittitle = ".//*[@name='prop_cm_name']";
	public String editurl = ".//*[@name='prop_avs_url']";
	public String editsave = ".//button[text()='Save']";

	public String clearbtn = ".//*[@id='clear-imageExternalLink']";
	//public String errormsg = ".//p[@class='error-msg']//span[text()='Unsupported format.']";
	public String errormsg = ".//p[@class='select-error-msg']";
	public String errorxpath = ".//p[@class='error-msg disableREPLACE']//span[contains(text(),'CRAFT')]";
	public String cancelonEditproperties = "//*[contains(text(),'Cancel')][last()]";
	// 4134
	public String thirdpartydropdownXpath = "//table[@class='thirdpartytable']//select";
	// private String targetsitexpath = "//*[contains(text(),'CRAFT')]";
	private String targetsitexpath = "//*[@title='CRAFT']";
	private String recentsitexpath = "//*[contains(text(),'Recent Sites')]";
	private String myFilesSharedFilesxpath = "//*[contains(text(),'CRAFT')]";
	private String respostorysitexpath = "//*[contains(text(),'Repository')]";
	private String selectfilexpath = "//*[@class='alfresco-renderers-PublishAction__image']";
	private String versionxpath = ".//*[@class='filename']//*[text()='CRAFT']//following::span[1]";
	private String cleanbuttonxpath = ".//*[@id='clear-CRAFT']";
	private String smartlinkxpath = "//*[@class='filename']//a[contains(text(),'CRAFT')]";
	private String fileclearedverifyxpath = ".//*[@id='uploadFileCRAFT']//following::div[@class='alignment']/p";
	public String uploaderrormsg = "//*[@id='CRAFT']//*[contains(text(),'Unsupported format.')]";
	public String selecterrormsg = "//*[@id='selectErrorCRAFT']";
	private String dynamicSelectImagexpath = "//*[contains(text(),'CRAFT')]//following::td[1]//img[@class='alfresco-renderers-PublishAction__image']";
	private String documentlib = "//*[text()='Documents']//following:: span[1][@data-dojo-attach-point='expandoNode']";
	private String filenameofselect = "//*[@class='dijitTreeLabel' and contains(text(),'CRAFT')]";
	
	public void clickSmartLinkType(String type, String name) {
		try {
			String finaltitle = smarttitle.replace("CRAFT", name);
			UIHelper.waitForPageToLoad(driver);

			String finalsmartlinktype = smartlinktype.replace("CRAFT", type);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalsmartlinktype);  

			if (UIHelper.checkForAnElementbyXpath(driver, finalsmartlinktype)) {
				UIHelper.highlightElement(driver, finalsmartlinktype);
				UIHelper.clickanElement(UIHelper.findAnElementbyXpath(driver,
						finalsmartlinktype));
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finaltitle);
				UIHelper.waitForPageToLoad(driver);
				report.updateTestLog("Click on " + type, "Click on " + type
						+ " successfully", Status.PASS);

			} else {
				report.updateTestLog("Click on " + type, "Click on " + type
						+ " failed", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Click on " + type, "Click on " + type
					+ " failed", Status.FAIL);

		}
	}

	public void subcancelbtn(String name, String btn) {
		try {
			String cancelbtn = cancelsubxpath.replace("CRAFT", name).replace(
					"REPLACE", btn);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelbtn);

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			 * String getMessage = UIHelper.getTextFromWebElement(driver,
			 * messageXpath);
			 */

			if (UIHelper.checkForAnElementbyXpath(driver, cancelbtn)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, cancelbtn));
				UIHelper.highlightElement(driver, cancelbtn);
				UIHelper.click(driver, cancelbtn);
				report.updateTestLog(" Verify creation of Smart link is cancelled", name
						+ " Smart Link creation is cancelled sucessfully. ", Status.PASS);
			} else {
				report.updateTestLog(" Verify creation of Smart link is cancelled",
						"Smart Link created successfully", Status.FAIL);
			}
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, cancelbtn);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog(" Verify creation of Smart link is cancelled",
					"Smart Link created successfully", Status.FAIL);
		}

	}

	public void entersmarttypedata(String name, String title,
			String extURLLink, String imgalttxt, String caption, String credit,
			String imgpreview, String imgRef) {

		String finaltitle = smarttitle.replace("CRAFT", name);
		String finalextURLLink = smartexttitle.replace("CRAFT", name);
		String finalimgalttxt = imgAltxpath.replace("CRAFT", name);
		String finalcaption = captionxpath.replace("CRAFT", name);
		String finalcredit = credittxtxpath.replace("CRAFT", name);

		String finalimgpreview = smartuploadimg.replace("CRAFT", name);
		String finalimgrefxpath = imgrefxpath.replace("CRAFT", name);
		String finalimguploadxpath = uploadedxpath.replace("CRAFT", name);
		String finalimguploadbtn = smartuploadbtn.replace("CRAFT", name);

		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finaltitle);
			if (!finaltitle.isEmpty()) {
				UIHelper.highlightElement(driver, finaltitle);
				UIHelper.sendKeysToAnElementByXpath(driver, finaltitle, title);
			}

			if (!extURLLink.isEmpty()) {
				UIHelper.highlightElement(driver, finalextURLLink);
				UIHelper.sendKeysToAnElementByXpath(driver, finalextURLLink,
						extURLLink);
			}

			if (!caption.isEmpty()) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalcaption));
				UIHelper.highlightElement(driver, finalcaption);
				UIHelper.sendKeysToAnElementByXpath(driver, finalcaption,
						caption);
			}

			if (!credit.isEmpty()) {
				UIHelper.highlightElement(driver, finalcredit);
				UIHelper.sendKeysToAnElementByXpath(driver, finalcredit, credit);
			}

			if (UIHelper.checkForAnElementbyXpath(driver, finalimgrefxpath)
					&& !imgalttxt.isEmpty()) {
				UIHelper.highlightElement(driver, finalimgrefxpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalimgrefxpath));
				UIHelper.sendKeysToAnElementByXpath(driver, finalimgrefxpath,
						imgalttxt);

			}
			
			if (!imgalttxt.isEmpty()) {
				UIHelper.highlightElement(driver, finalimgalttxt);
				UIHelper.sendKeysToAnElementByXpath(driver, finalimgalttxt,
						imgalttxt);
			}
			
			

			if (UIHelper.checkForAnElementbyXpath(driver, finalimguploadbtn)
					&& !imgRef.isEmpty()) {

				/*UIHelper.highlightElement(driver, finalimgalttxt);
				UIHelper.sendKeysToAnElementByXpath(driver, finalimgalttxt,
						imgalttxt);*/

				UIHelper.highlightElement(driver, finalimguploadbtn);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalimguploadbtn));

				WebElement fileInput = driver.findElement(By
						.xpath(finalimgpreview));

				fileInput.sendKeys(imgRef);
				// Thread.sleep(2000);
				UIHelper.waitFor(driver);
				// UIHelper.waitForVisibilityOfEleByXpath(driver,
				// finalimguploadxpath);

				if (UIHelper.checkForAnElementbyXpath(driver,
						finalimguploadxpath)) {
					UIHelper.highlightElement(driver, finalimguploadxpath);
					report.updateTestLog(
							"Upload File",
							"File uploaded successfully. <br><b> File : <b>"
									+ UIHelper.findAnElementbyXpath(driver,
											finalimguploadxpath).getText(),
							Status.PASS);

				} else {
					report.updateTestLog("Upload File",
							"File uploaded failed.", Status.FAIL);
				}

				report.updateTestLog("Enter smart link Data",
						"smart link data entered successfully.", Status.PASS);
			} else {
				/*
				 * report.updateTestLog( "Enter smart link Data",
				 * "smart link data failed.", Status.FAIL);
				 */
			}

		} catch (Exception e) {
			report.updateTestLog("Enter smart link Data",
					"smart link data failed.", Status.FAIL);

		}
	}

	//Enter data for thirdPartyInteractiveLink
			public void enterDataInThirdPartyInteractiveOrMetrodigiLink(String name, String title,
					String extURLLink,String height,String width,String imgRef, String imgalttxt, String caption, String credit,
					String imgpreview,String PIVendor){
				String finaltitle = smarttitle.replace("CRAFT", name);
				String finalextURLLink = smartexttitle.replace("CRAFT", name);
				String finalHeightXpath=heightXpath.replace("CRAFT", name);
				String finalWidthXpath=widthXpath.replace("CRAFT", name);
				String finalimgrefxpath = imgrefxpath.replace("CRAFT", name);
				String finalimgalttxt = imgAltxpath.replace("CRAFT", name);
				String finalcaption = captionxpath.replace("CRAFT", name);
				String finalcredit = credittxtxpath.replace("CRAFT", name);

				String finalimgpreview = smartuploadimg.replace("CRAFT", name);
				String finalimguploadxpath = uploadedxpath.replace("CRAFT", name);
				String finalimguploadbtn = smartuploadbtn.replace("CRAFT", name);
                
				try{
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finaltitle);
					if (!title.isEmpty()) {
						UIHelper.highlightElement(driver, finaltitle);
						UIHelper.sendKeysToAnElementByXpath(driver, finaltitle, title);
					}

					if (!extURLLink.isEmpty()) {
						UIHelper.highlightElement(driver, finalextURLLink);
						UIHelper.sendKeysToAnElementByXpath(driver, finalextURLLink,
								extURLLink);
					}
					
					if (!height.isEmpty()) {
						UIHelper.highlightElement(driver, finalHeightXpath);
						UIHelper.sendKeysToAnElementByXpath(driver, finalHeightXpath, height);
					}

					if (!width.isEmpty()) {
						UIHelper.highlightElement(driver, finalWidthXpath);
						UIHelper.sendKeysToAnElementByXpath(driver, finalWidthXpath,width);
					}
					
					if (!caption.isEmpty()) {
						UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
								driver, finalcaption));
						UIHelper.highlightElement(driver, finalcaption);
						UIHelper.sendKeysToAnElementByXpath(driver, finalcaption,
								caption);
					}

					if (!credit.isEmpty()) {
						UIHelper.highlightElement(driver, finalcredit);
						UIHelper.sendKeysToAnElementByXpath(driver, finalcredit, credit);
					}
					
					if (UIHelper.checkForAnElementbyXpath(driver, finalimgrefxpath)
							&& !imgalttxt.isEmpty()) {
						UIHelper.highlightElement(driver, finalimgrefxpath);
						UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
								driver, finalimgrefxpath));
						UIHelper.sendKeysToAnElementByXpath(driver, finalimgrefxpath,
								imgalttxt);

					}
					
					if (!imgalttxt.isEmpty()) {
						UIHelper.highlightElement(driver, finalimgalttxt);
						UIHelper.sendKeysToAnElementByXpath(driver, finalimgalttxt,
								imgalttxt);
					}
					
					if (UIHelper.checkForAnElementbyXpath(driver, finalimguploadbtn)
							&& !imgRef.isEmpty()) {

						UIHelper.highlightElement(driver, finalimguploadbtn);
						UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
								driver, finalimguploadbtn));

						WebElement fileInput = driver.findElement(By
								.xpath(finalimgpreview));

						fileInput.sendKeys(imgRef);
						// Thread.sleep(2000);
						UIHelper.waitFor(driver);

						if (UIHelper.checkForAnElementbyXpath(driver,
								finalimguploadxpath)) {
							UIHelper.highlightElement(driver, finalimguploadxpath);
							report.updateTestLog(
									"Upload File",
									"File uploaded successfully. <br><b> File : <b>"
											+ UIHelper.findAnElementbyXpath(driver,
													finalimguploadxpath).getText(),
									Status.PASS);

						} else {
							report.updateTestLog("Upload File",
									"File uploaded failed.", Status.FAIL);
						}

						report.updateTestLog("Enter smart link Data",
								"smart link data entered successfully.", Status.PASS);
					} 

				}catch(Exception e){
					report.updateTestLog("Enter smart link Data",
							"smart link data entered Unsuccessfully.", Status.FAIL);
				}
				
			}
	
	public void editsmartlink(String name, String URL) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, edittitle);
			UIHelper.findAnElementbyXpath(driver, edittitle).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, edittitle, name);
			UIHelper.findAnElementbyXpath(driver, editurl).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, editurl, URL);

			UIHelper.waitForPageToLoad(driver);

			WebElement saveBtn = UIHelper
					.findAnElementbyXpath(driver, editsave);
			UIHelper.highlightElement(driver, saveBtn);

			report.updateTestLog("Edit smart link resource",
					"Smart link edited successfully"
							+ "<br /><b>Smart link Name :</b> " + name
							+ "<br /><b>Smart link URL:</b> " + URL,
					Status.PASS);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", saveBtn);
			
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, editsave);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Edit AV Smart Link",
					"Smart link edit failed", Status.FAIL);

		}
	}

	public void clearfunction(String name) {
		try {

			String finalimguploadxpath = uploadedxpath.replace("CRAFT", name);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, clearbtn);
			UIHelper.click(driver, clearbtn);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					finalimguploadxpath);

			if (!UIHelper.checkForAnElementbyXpath(driver, finalimguploadxpath)) {
				report.updateTestLog("Clear Functionality",
						"clear functionality successfully", Status.PASS);
			} else {
				report.updateTestLog("Clear Functionality",
						"clear functionality failed", Status.FAIL);

			}
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Clear Functionality",
					"clear functionality failed", Status.FAIL);

		}
	}

	public void errormsg(String file) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, errormsg);
			if (UIHelper.checkForAnElementbyXpath(driver, errormsg)) {

				UIHelper.highlightElement(driver, errormsg);
				if (UIHelper.getTextFromWebElement(driver, errormsg).equals("Unsupported format.")) {
					report.updateTestLog("Verify Error Msg for unsupported format of file selection ",
							"Error Msg for unsupported format of file selection for the " + file + " "
									+ UIHelper.getTextFromWebElement(driver, errormsg),
							Status.PASS);
				} else {
					report.updateTestLog("Verify Error Msg for unsupported format of file selection ",
							"Incorrect Error Msg for unsupported format of file selection for the " + file + " "
									+ UIHelper.getTextFromWebElement(driver, errormsg),
							Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Error Msg ", "Error msg not seen due unsupported format for file " + file,
						Status.FAIL);

			}
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Verify Error Msg ", "Error msg not seen due unsupported format for file " + file,
					Status.FAIL);

		}
	}

	public void error(String error, String type) {
		try {

			String finalsubmit = submitbtn.replace("CRAFT", type);
			UIHelper.highlightElement(driver, finalsubmit);
			UIHelper.click(driver, finalsubmit);

			String finalerror = errorxpath.replace("CRAFT", error).replace("REPLACE", type);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalerror);

			String msg = UIHelper.findAnElementbyXpath(driver, finalerror).getText();
			if (UIHelper.checkForAnElementbyXpath(driver, finalerror) && msg.contains(error)) {
				UIHelper.highlightElement(driver, finalerror);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalerror));
				report.updateTestLog("Verify Error Msg ", "Error msg : " + msg, Status.PASS);
			} else {
				report.updateTestLog("Verify Error Msg ", "Error msg not seen as expected", Status.FAIL);

			}
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Verify Error Msg ", "Error msg not seen as expected.", Status.FAIL);

		}
	}

	public void subcancelbtnerror(String name, String btn) {
		try {
			String cancelbtn = cancelsubxpath.replace("CRAFT", name).replace(
					"REPLACE", btn);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelbtn);

			if (UIHelper.checkForAnElementbyXpath(driver, cancelbtn)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, cancelbtn));
				UIHelper.highlightElement(driver, cancelbtn);
				UIHelper.click(driver, cancelbtn);
				report.updateTestLog(" Verify creation of Smart link", name
						+ " Smart Link created sucessfully. ", Status.PASS);
			} else {
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link creation failed", Status.FAIL);
			}
			// UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, cancelbtn);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog(" Verify creation of Smart link",
					"Smart Link creation failed", Status.FAIL);
		}

	}

	public void submitbutton(String name, String title) {

		try {

			WebElement submit = UIHelper.findAnElementbyXpath(driver,
					"//form[@id='" + name + "']//input[@value='Submit']");
			UIHelper.highlightElement(driver, submit);
			UIHelper.clickanElement(submit);
			

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, "//form[@id='"
					+ name + "']//input[@value='Submit']");
			UIHelper.waitForVisibilityOfEleByXpath(driver,"//*[@class='filename']//a[contains(text(),'"
							+ title + "')]");
			UIHelper.waitFor(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, "//*[@class='filename']//a[contains(text(),'"
							+ title + "')]"))) {
				report.updateTestLog("Smartlink is created for " + name,
						"Smartlink is created for " + name
								+ " in <br><b> title : <b>" + title,
						Status.PASS);
			} else {
				report.updateTestLog("Smartlink is not created for " + name,
						"Smartlink is not created for " + name
								+ " in <br><b> title : <b>" + title,
						Status.FAIL);

			}

			UIHelper.waitForPageToLoad(driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Smartlink is not created for " + name,
					"Smartlink is not created for " + name
							+ " in <br><b> title : <b>" + title, Status.FAIL);

		}

	}

	public void EditSmartlinkFilePathValidation(String smartlinktype,
			String title, String sitename, String filename) {
		try {

			// UIHelper.pageRefresh(driver);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Edit Smartlink:", smartlinktype
					+ " smartlink is expanded", Status.DONE);
			String finalimguploadxpath = uploadedxpath.replace("CRAFT",
					smartlinktype);
			String localpath = UIHelper.getTextFromWebElement(driver,
					finalimguploadxpath);
			String Lsitename = sitename.toLowerCase();
			String ExpectedVal = "/Sites/" + Lsitename + "/documentLibrary/"
					+ title + "/" + filename;
			String ExpectedVal1 = "/Sites/" + Lsitename + "/documentLibrary/"
					+ filename;

			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, finalimguploadxpath));

			if (localpath.equalsIgnoreCase(ExpectedVal)) {
				report.updateTestLog("UI path validation:",
						"UI path is generated <br><b> correctly : <b> "
								+ ExpectedVal, Status.PASS);
				report.updateTestLog("Edit Smartlink:", smartlinktype
						+ " smartlink is expanded", Status.DONE);

			} else if (localpath.equalsIgnoreCase(ExpectedVal1)) {
				report.updateTestLog("UI path validation:",
						"UI path is generated <br><b> correctly : <b> "
								+ localpath, Status.PASS);
				report.updateTestLog("Edit Smartlink:", smartlinktype
						+ " smartlink is expanded", Status.DONE);

			}/*
			 * else{ report.updateTestLog("UI Path validation:",
			 * "UI path is not generated <br><b> correctly : <b> "+localpath,
			 * Status.FAIL); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Edit Smartlink:", smartlinktype
					+ " smartlink is not expanded", Status.FAIL);

		}

	}

	public void SmartlinkFilePathvalidation(String smartlinktype,
			String filename, String uploadtype, String sitename, String title) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalimguploadxpath = uploadedxpath.replace("CRAFT",
					smartlinktype);
			String localpath = UIHelper.getTextFromWebElement(driver,
					finalimguploadxpath);
			String Localpathforselect = selectedxpath.replace("CRAFT",
					smartlinktype);

			String Localpathforselectvalue = UIHelper.getTextFromWebElement(
					driver, Localpathforselect);

			if (uploadtype.equalsIgnoreCase("upload")) {

				String Expectedval = "LocalFile: " + filename;
				System.out.println(Expectedval);
				System.out.println(localpath);
				if (localpath.equalsIgnoreCase(Expectedval)) {
					report.updateTestLog("UI path:",
							"UI path is generated <br><b> correctly : <b> "
									+ Expectedval, Status.PASS);
				} else {
					report.updateTestLog("UI Path:",
							"UI path is not generated <br><b> correctly : <b> "
									+ Expectedval, Status.FAIL);
				}
			} else if (uploadtype.equalsIgnoreCase("select")) {

				String Lsitename = sitename.toLowerCase();
				String ExpectedVal = "/Sites/" + Lsitename
						+ "/documentLibrary/" + title + "/" + filename;
				String ExpectedVal1 = "/Sites/" + Lsitename
						+ "/documentLibrary/" + filename;

				UIHelper.highlightElement(driver, Localpathforselectvalue);
				if (Localpathforselectvalue.equalsIgnoreCase(ExpectedVal)) {
					report.updateTestLog("UI path validation:",
							"UI path is generated <br><b> correctly : <b> "
									+ Localpathforselectvalue, Status.PASS);

				} else if (Localpathforselectvalue
						.equalsIgnoreCase(ExpectedVal1)) {
					report.updateTestLog("UI path validation:",
							"UI path is generated <br><b> correctly : <b> "
									+ Localpathforselectvalue, Status.PASS);
				}/*
				 * else{ report.updateTestLog("UI Path validation:",
				 * "UI path is not generated <br><b> correctly : <b> "
				 * +Localpathforselectvalue, Status.FAIL); }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cancelButtonOnEditSmartlink(String smartlinktype, String title) {

		try {

			WebElement cancel = UIHelper.findAnElementbyXpath(driver,
					"//form[@id='" + smartlinktype + "']//*[@value='Cancel']");
			UIHelper.highlightElement(driver, cancel);
			UIHelper.clickanElement(cancel);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitforthisElement(driver, UIHelper.findAnElementbyXpath(
					driver, "//*[@class='filename']//a[contains(text(),'"
							+ title + "')]"));
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, "//*[@class='filename']//a[contains(text(),'"
							+ title + "')]"))) {
				report.updateTestLog("cancel button",
						"cancel button is clicked for " + smartlinktype
								+ " in <br><b> title : <b>" + title,
						Status.DONE);
			} else {
				report.updateTestLog("cancel button",
						"cancel button is clicked for " + smartlinktype
								+ " in <br><b> title : <b>" + title,
						Status.DONE);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectimagefromSelect(String smartlinktype,
			String targetsitename, String filename) {

		try {
			UIHelper.waitForPageToLoad(driver);

			String finalimguploadxpath = selectxpath.replace("CRAFT",
					smartlinktype);
			// System.out.println(finalimguploadxpath);
			String targetsite = targetsitexpath
					.replace("CRAFT", targetsitename);
			String dynamicfilename = dynamicSelectImagexpath.replace("CRAFT",
					filename);
			
			String newfilename= filenameofselect.replace("CRAFT", filename);
					
			WebElement ele2 = UIHelper.findAnElementbyXpath(driver,
					finalimguploadxpath);
			UIHelper.javascriptClick(driver, ele2);
			
			String mainWindowHandle = driver.getWindowHandle();

			for (String childWindowHandle : driver.getWindowHandles()) {
				if (!childWindowHandle.equals(mainWindowHandle)) {
					driver.switchTo().window(childWindowHandle);
					UIHelper.waitForPageToLoad(driver);
					Boolean flag = UIHelper.isWebElementDisplayed(UIHelper
							.findAnElementbyXpath(driver, recentsitexpath));

					if (flag) {

						UIHelper.highlightElement(driver, recentsitexpath);
						UIHelper.click(driver, recentsitexpath);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								targetsite);

						UIHelper.highlightElement(driver, targetsite);
						UIHelper.waitForPageToLoad(driver);
						//WebElement ele = UIHelper.findAnElementbyXpath(driver,
							//	targetsite);
						//UIHelper.javascriptClick(driver, ele);
						UIHelper.click(driver, targetsite);
						UIHelper.waitForPageToLoad(driver);
						report.updateTestLog("File Picker from select button",
								"Clicked on Select button and clicked on File"
										+ filename, Status.DONE);

						//UIHelper.waitFor(driver);
						//UIHelper.waitFor(driver);
						
						try{
							UIHelper.waitForVisibilityOfEleByXpath(driver,
									documentlib);
							boolean flag1 = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, documentlib));
							if(flag1){
							UIHelper.click(driver, documentlib);
							UIHelper.waitForVisibilityOfEleByXpath(driver,
									newfilename);
							UIHelper.highlightElement(driver, newfilename);
							WebElement ele1 = UIHelper.findAnElementbyXpath(driver,
									newfilename);
							UIHelper.javascriptClick(driver, ele1);
							UIHelper.click(driver, newfilename);
							}
						}catch(Exception e){
							UIHelper.waitForVisibilityOfEleByXpath(driver,
									dynamicfilename);
							UIHelper.highlightElement(driver, dynamicfilename);
							WebElement ele1 = UIHelper.findAnElementbyXpath(driver,
									dynamicfilename);
							UIHelper.javascriptClick(driver, ele1);
						}
						
						//UIHelper.waitForPageToLoad(driver);
					//	UIHelper.click(driver, dynamicfilename);
						//UIHelper.waitForLong(driver);

					} else {
						report.updateTestLog("File Picker from select button",
								"Clicked on Select button is failed",
								Status.DONE);
					}

					// driver.close();
				}
			}
			//UIHelper.waitForPageToLoad(driver);
			driver.switchTo().window(mainWindowHandle);
			UIHelper.waitForPageToLoad(driver);
			String titleofpage = driver.getTitle();
			System.out.println(titleofpage);
			if (titleofpage.equalsIgnoreCase("AV Smart Link")) {
				report.updateTestLog("Check file is select:",
						"File has been selected", Status.PASS);

			} else {
				report.updateTestLog("Check file is select:",
						"File has not been selected", Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// Select file from shared files & my files
		public void selectimagefromSelectSharedMyFiles(String smartlinktype, String sharedMyFiles, String filename) {

			try {
				UIHelper.waitForPageToLoad(driver);

				String finalimguploadxpath = selectxpath.replace("CRAFT", smartlinktype);
				// System.out.println(finalimguploadxpath);
				String targetsite = myFilesSharedFilesxpath.replace("CRAFT", sharedMyFiles);
				String dynamicfilename = dynamicSelectImagexpath.replace("CRAFT", filename);

				String newfilename = filenameofselect.replace("CRAFT", filename);

				WebElement ele2 = UIHelper.findAnElementbyXpath(driver, finalimguploadxpath);
				UIHelper.javascriptClick(driver, ele2);

				String mainWindowHandle = driver.getWindowHandle();

				for (String childWindowHandle : driver.getWindowHandles()) {
					if (!childWindowHandle.equals(mainWindowHandle)) {
						driver.switchTo().window(childWindowHandle);
						UIHelper.waitForPageToLoad(driver);
						Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, targetsite));

						if (flag) {

							UIHelper.highlightElement(driver, targetsite);
							UIHelper.click(driver, targetsite);
							UIHelper.waitForPageToLoad(driver);
							UIHelper.waitForVisibilityOfEleByXpath(driver, targetsite);

							UIHelper.highlightElement(driver, targetsite);
							UIHelper.waitForPageToLoad(driver);
							// WebElement ele =
							// UIHelper.findAnElementbyXpath(driver,
							// targetsite);
							// UIHelper.javascriptClick(driver, ele);
							UIHelper.click(driver, targetsite);
							UIHelper.waitForPageToLoad(driver);
							report.updateTestLog("File Picker from select button",
									"Clicked on Select button and clicked on File" + filename, Status.DONE);

							// UIHelper.waitFor(driver);
							// UIHelper.waitFor(driver);

							try {
								UIHelper.waitForVisibilityOfEleByXpath(driver, targetsite);
								boolean flag1 = UIHelper
										.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, targetsite));
								if (flag1) {
									UIHelper.click(driver, targetsite);
									UIHelper.waitForVisibilityOfEleByXpath(driver, newfilename);
									UIHelper.highlightElement(driver, newfilename);
									WebElement ele1 = UIHelper.findAnElementbyXpath(driver, newfilename);
									UIHelper.javascriptClick(driver, ele1);
									UIHelper.click(driver, newfilename);
								}
							} catch (Exception e) {
								UIHelper.waitForVisibilityOfEleByXpath(driver, dynamicfilename);
								UIHelper.highlightElement(driver, dynamicfilename);
								WebElement ele1 = UIHelper.findAnElementbyXpath(driver, dynamicfilename);
								UIHelper.javascriptClick(driver, ele1);
							}

							// UIHelper.waitForPageToLoad(driver);
							// UIHelper.click(driver, dynamicfilename);
							// UIHelper.waitForLong(driver);

						} else {
							report.updateTestLog("File Picker from select button", "Clicked on Select button is failed",
									Status.DONE);
						}

						// driver.close();
					}
				}
				// UIHelper.waitForPageToLoad(driver);
				driver.switchTo().window(mainWindowHandle);
				UIHelper.waitForPageToLoad(driver);
				String titleofpage = driver.getTitle();
				System.out.println(titleofpage);
				if (titleofpage.equalsIgnoreCase("AV Smart Link")) {
					report.updateTestLog("Check file is select:", "File has been selected", Status.PASS);

				} else {
					report.updateTestLog("Check file is select:", "File has not been selected", Status.FAIL);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	public void VersionCheckdocument(String ExpectedVersion, String title) {		
		String versionxpathfinal = versionxpath.replace("CRAFT", title);		
		UIHelper.mouseOveranElement(driver,
				UIHelper.findAnElementbyXpath(driver, versionxpathfinal));
		String Actualversion = UIHelper.findAnElementbyXpath(driver,
				versionxpathfinal).getText();

		if (Actualversion.contentEquals(ExpectedVersion)) {
			report.updateTestLog(title + " smartlink version:", title
					+ " smartlink version is <b> " + Actualversion, Status.PASS);
		} else {
			report.updateTestLog(title + " smartlink version:", title
					+ " Failed to update smartlink version : <b> "
					+ Actualversion, Status.FAIL);
		}

	}

	public void EnteringImageref(String name, String imgRef) {

		String finalimgrefxpath = imgrefxpath.replace("CRAFT", name);

		if (UIHelper.checkForAnElementbyXpath(driver, finalimgrefxpath)) {

			UIHelper.highlightElement(driver, finalimgrefxpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					finalimgrefxpath));
			UIHelper.findAnElementbyXpath(driver, finalimgrefxpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, finalimgrefxpath,
					imgRef);

		}

	}

	public void editsmarttypedatainduvidual(String name, String field,
			String value) {
		try {

			String finaltitle = smarttitle.replace("CRAFT", name);
			String finalextURLLink = smartexttitle.replace("CRAFT", name);
			String finalcaption = captionxpath.replace("CRAFT", name);
			String finalcredit = credittxtxpath.replace("CRAFT", name);
			String finalimgrefxpath = imgrefxpath.replace("CRAFT", name);
			String finalimgpreview = smartuploadimg.replace("CRAFT", name);
			String finalimguploadbtn = smartuploadbtn.replace("CRAFT", name);
			String finalimgalttxt = imgAltxpath.replace("CRAFT", name);

			if (field.contentEquals("title")) {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finaltitle);
				UIHelper.highlightElement(driver, finaltitle);
				driver.findElement(By.xpath(finaltitle)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, finaltitle, value);
				report.updateTestLog(
						"Updation of the fields in edit smartlink",
						"Updated<b>" + field + " field with value as" + value,
						Status.DONE);

			} else if (field.contentEquals("extURLLink")) {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalextURLLink);
				UIHelper.highlightElement(driver, finalextURLLink);
				driver.findElement(By.xpath(finalextURLLink)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, finalextURLLink,
						value);
				report.updateTestLog(
						"Updation of the fields in edit smartlink",
						"Updated<b> " + field + " field with value as " + value,
						Status.DONE);

			} else if (field.contentEquals("caption")) {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalcaption);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalcaption));
				UIHelper.highlightElement(driver, finalcaption);
				driver.findElement(By.xpath(finalcaption)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, finalcaption, value);
				report.updateTestLog(
						"Updation of the fields in edit smartlink",
						"Updated<b> " + field + " field with value as " + value,
						Status.DONE);

			} else if (field.contentEquals("credit")) {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalcredit);
				UIHelper.highlightElement(driver, finalcredit);
				driver.findElement(By.xpath(finalcredit)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, finalcredit, value);
				report.updateTestLog(
						"Updation of the fields in edit smartlink",
						"Updated<b> " + field + " field with value as " + value,
						Status.DONE);

			} else if (field.contentEquals("imgrefpath")) {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalimgrefxpath);
				UIHelper.highlightElement(driver, finalimgrefxpath);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalimgrefxpath));
				driver.findElement(By.xpath(finalimgrefxpath)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, finalimgrefxpath,
						value);
				report.updateTestLog(
						"Updation of the fields in edit smartlink",
						"Updated<b> " + field + " field with value as " + value,
						Status.DONE);

			} else if (field.contentEquals("imgpreview")) {
				UIHelper.highlightElement(driver, finalimguploadbtn);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalimguploadbtn));
				WebElement fileInput = driver.findElement(By
						.xpath(finalimgpreview));
				fileInput.sendKeys(value);
				report.updateTestLog(
						"Updation of the fields in edit smartlink",
						"Updated<b> " + field + " field with value as " + value,
						Status.DONE);

			} else if (field.contentEquals("imgtext")) {
				UIHelper.highlightElement(driver, finalimgalttxt);
				driver.findElement(By.xpath(finalimgalttxt)).clear();
				UIHelper.sendKeysToAnElementByXpath(driver, finalimgalttxt,
						value);
				report.updateTestLog(
						"Updation of the fields in edit smartlink",
						"Updated<b> " + field + " field with value as " + value,
						Status.DONE);

			}

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			report.updateTestLog("Updating smart link Data",
					"Updating smart link data failed.", Status.FAIL);

		}

	}

	public void ClearbuttonforAllSmartlink(String name, String title) {

		try {
			String finaltitle = cleanbuttonxpath.replace("CRAFT", name);
			String clearance = fileclearedverifyxpath.replace("CRAFT", name);
			WebElement clear = UIHelper
					.findAnElementbyXpath(driver, finaltitle);
			UIHelper.highlightElement(driver, clear);
			UIHelper.clickanElement(clear);
			String result = UIHelper.getTextFromWebElement(driver, clearance);
			if (result.isEmpty()) {
				report.updateTestLog("clear the link",
						"Cleared the link from Smartlink type " + name,
						Status.PASS);
			} else {
				report.updateTestLog("clear the link",
						"Cleared the link from Smartlink type " + name,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void negsubmit(String name) {

		try {
			String finalsubmit = submitbtn.replace("CRAFT", name);
			UIHelper.highlightElement(driver, finalsubmit);
			UIHelper.click(driver, finalsubmit);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, finalsubmit);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void errormsgSelectAndUpload(String file, String Typeofupload,
			String type) {
		try {
			String upladxpath = uploaderrormsg.replace("CRAFT", type);
			String Slectxpath = selecterrormsg.replace("CRAFT", type);
			String xpath = "";
			if (Typeofupload.contentEquals("Select")) {
				xpath = Slectxpath;
			} else if (Typeofupload.contentEquals("Upload")) {
				xpath = upladxpath;
			}
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, xpath);
			if (UIHelper.checkForAnElementbyXpath(driver, xpath)) {
				UIHelper.highlightElement(driver, xpath);
				report.updateTestLog("Verify Error Msg ",
						"Error msg due unsupported format for file " + file,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Error Msg ",
						"Error msg not seen due unsupported format for file "
								+ file, Status.FAIL);

			}
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Verify Error Msg ",
					"Error msg not seen due unsupported format for file "
							+ file, Status.FAIL);

		}

	}

	public boolean checkelementisdisplayed(String element) {

		Boolean flag = false;
		try {

			UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, element));
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}
	
	public void selectimagefromSelectreportory(String smartlinktype,
			String targetsitename, String filename) {

		try {
			UIHelper.waitForPageToLoad(driver);

			String finalimguploadxpath = selectxpath.replace("CRAFT",
					smartlinktype);
			// System.out.println(finalimguploadxpath);
			String targetsite = targetsitexpath
					.replace("CRAFT", targetsitename);
			String dynamicfilename = dynamicSelectImagexpath.replace("CRAFT",
					filename);
		
			String newfilename= filenameofselect.replace("CRAFT", filename);
					
			WebElement ele2 = UIHelper.findAnElementbyXpath(driver,
					finalimguploadxpath);
			UIHelper.javascriptClick(driver, ele2);
			
			String mainWindowHandle = driver.getWindowHandle();

			for (String childWindowHandle : driver.getWindowHandles()) {
				if (!childWindowHandle.equals(mainWindowHandle)) {
					driver.switchTo().window(childWindowHandle);
					UIHelper.waitForPageToLoad(driver);
					Boolean flag = UIHelper.isWebElementDisplayed(UIHelper
							.findAnElementbyXpath(driver, respostorysitexpath));

					if (flag) {

						UIHelper.highlightElement(driver, respostorysitexpath);
						UIHelper.click(driver, respostorysitexpath);
						UIHelper.waitForPageToLoad(driver);
						String sites = "//*[text()='Sites']//parent::span//parent::div/span";
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								sites);

						UIHelper.highlightElement(driver, sites);
						UIHelper.waitForPageToLoad(driver);
						//WebElement ele = UIHelper.findAnElementbyXpath(driver,
							//	targetsite);
						//UIHelper.javascriptClick(driver, ele);
						UIHelper.click(driver, sites);
						UIHelper.waitForPageToLoad(driver);
						
						System.out.println(targetsitename.toLowerCase());
						String lowersite=targetsitename.toLowerCase();
						String sitesname = "//*[text()='"+lowersite+"']//parent::span//parent::div/span[1]";
						System.out.println(sitesname);
						UIHelper.waitForVisibilityOfEleByXpath(driver, sitesname);
						UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, sitesname));
						UIHelper.highlightElement(driver, sitesname);
						UIHelper.click(driver, sitesname);
						report.updateTestLog("File Picker from select button",
								"Clicked on Select button and clicked on File"
										+ filename, Status.DONE);

						
						
						try{
							
							String documentlibaray = "//*[text()='documentLibrary']//parent::span//parent::div/span[1]";
							
							
							UIHelper.waitForVisibilityOfEleByXpath(driver,
									documentlibaray);
							boolean flag1 = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, documentlibaray));
							if(flag1){
							UIHelper.click(driver, documentlibaray);
							UIHelper.waitForVisibilityOfEleByXpath(driver,
									newfilename);
							UIHelper.highlightElement(driver, newfilename);
							WebElement ele1 = UIHelper.findAnElementbyXpath(driver,
									newfilename);
							//UIHelper.javascriptClick(driver, ele1);
							UIHelper.click(driver, newfilename);
							}
						}catch(Exception e){
							//*[text()='Document Library']//parent::span//parent::div/span[1]
							
							String documentlibaray = "//*[text()='Document Library']//parent::span//parent::div/span[1]";
							
							
							UIHelper.waitForVisibilityOfEleByXpath(driver,
									documentlibaray);
							boolean flag1 = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, documentlibaray));
							if(flag1){
							UIHelper.click(driver, documentlibaray);
							UIHelper.waitForVisibilityOfEleByXpath(driver,
									newfilename);
							UIHelper.highlightElement(driver, newfilename);
							WebElement ele1 = UIHelper.findAnElementbyXpath(driver,
									newfilename);
							//UIHelper.javascriptClick(driver, ele1);
							UIHelper.click(driver, newfilename);
							}
						/*	UIHelper.waitForVisibilityOfEleByXpath(driver,
									dynamicfilename);
							UIHelper.highlightElement(driver, dynamicfilename);
							WebElement ele1 = UIHelper.findAnElementbyXpath(driver,
									dynamicfilename);
							UIHelper.javascriptClick(driver, ele1);*/
						}
						
						//UIHelper.waitForPageToLoad(driver);
					//	UIHelper.click(driver, dynamicfilename);
						//UIHelper.waitForLong(driver);

					} else {
						report.updateTestLog("File Picker from select button",
								"Clicked on Select button is failed",
								Status.DONE);
					}

					// driver.close();
				}
			}
			//UIHelper.waitForPageToLoad(driver);
			driver.switchTo().window(mainWindowHandle);
			UIHelper.waitForPageToLoad(driver);
			String titleofpage = driver.getTitle();
			System.out.println(titleofpage);
			if (titleofpage.equalsIgnoreCase("AV Smart Link")) {
				report.updateTestLog("Check file is select:",
						"File has been selected", Status.PASS);

			} else {
				report.updateTestLog("Check file is select:",
						"File has not been selected", Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
}
	public String optimizedRadio = ".//form[@id='thirdPartyInteractiveLink']//td[@class='optimizedClass']/input[@value='CRAFT']";
	public String noBtn = ".//form[@id='thirdPartyInteractiveLink']//td[@class='optimizedClass']/input[@value='No']";
	public String yesBtn = ".//form[@id='thirdPartyInteractiveLink']//td[@class='optimizedClass']/input[@value='Yes']";
	public String optimized = ".//form[@id='thirdPartyInteractiveLink']//td[@class='optimizedClass']";

	public boolean optimizedSelection() {
		boolean flag = false;
		UIHelper.waitForVisibilityOfEleByXpath(driver, optimized);

		UIHelper.waitForVisibilityOfEleByXpath(driver, yesBtn);
		UIHelper.waitForVisibilityOfEleByXpath(driver, noBtn);
		WebElement radioBtnYes = driver.findElement(By.xpath(yesBtn));
		UIHelper.highlightElement(driver, radioBtnYes);
		WebElement radioBtnNo = driver.findElement(By.xpath(noBtn));
		UIHelper.highlightElement(driver, radioBtnNo);
		if (UIHelper.isSelected(radioBtnYes)) {
			report.updateTestLog("Verify Optimized for use on Mobile?-Yes Radio button is selected",
					"Optimized for use on Mobile?-Yes Radio button is selected", Status.PASS);
			flag = true;
		} else if (UIHelper.isSelected(radioBtnNo)) {
			report.updateTestLog("Verify Optimized for use on Mobile?-No Radio button is selected",
					"Optimized for use on Mobile?-No Radio button is selected", Status.FAIL);
		}
		return flag;

	}

	// Toggle the optimized radio button
	public boolean toggleOptimizedSelection(String YesNo) {
		boolean flag = false;
		String toggle = optimizedRadio.replace("CRAFT", YesNo);
		UIHelper.waitForVisibilityOfEleByXpath(driver, optimized);
		UIHelper.waitForVisibilityOfEleByXpath(driver, toggle);
		WebElement radioBtnYesNo = driver.findElement(By.xpath(toggle));
		UIHelper.highlightElement(driver, radioBtnYesNo);
		UIHelper.click(driver, toggle);
		report.updateTestLog("Verify Optimized for use on Mobile?- " + YesNo + " Radio button is clicked",
				"Optimized for use on Mobile?- " + YesNo + " Radio button is clicked", Status.PASS);
		if (UIHelper.isSelected(radioBtnYesNo)) {
			report.updateTestLog("Verify Optimized for use on Mobile?- " + YesNo + " Radio button is selected",
					"Optimized for use on Mobile?- " + YesNo + " Radio button is selected", Status.PASS);
			flag = true;

		} else {
			report.updateTestLog("Verify Optimized for use on Mobile?- " + YesNo + " Radio button is selected",
					"Optimized for use on Mobile?- " + YesNo + " Radio button is not selected", Status.FAIL);
		}
		return flag;

	}

	public String threePILabel=".//form[@id='thirdPartyInteractiveLink']//td[text()='3PI Vendor']";
	public String threePIDropdown = ".//form[@id='thirdPartyInteractiveLink']//select[@id='3PIVendorDDthirdPartyInteractiveLink']";
	

	// Get the 3PI dropdown list
	public void threePIVendorDropdown() {
		ArrayList<String> getAllOptions = new ArrayList<String>();
		UIHelper.waitForVisibilityOfEleByXpath(driver, threePILabel);
		UIHelper.highlightElement(driver, threePILabel);
		UIHelper.waitForVisibilityOfEleByXpath(driver, threePIDropdown);
		WebElement threePI = driver.findElement(By.xpath(threePIDropdown));
		//UIHelper.click(driver, threePIDropdown);
		UIHelper.getAllAvailableOptions(threePI, getAllOptions);
		if(getAllOptions.get(0).equals("None") && getAllOptions.get(1).equals("None"))
		{
			getAllOptions.remove(0);
		}
		for (String options : getAllOptions) {
			report.updateTestLog("Verify 3PI vendor dropdown list", "3PI vendor dropdown lists are" + " " + options,
					Status.PASS);
		}
		Set<String> checkDups = new HashSet<>();

		for (String name : getAllOptions) {
			if (checkDups.add(name) == false) {
				report.updateTestLog("Verify 3PI vendor dropdown list is duplicated",
						"3PI vendor dropdown list gets duplicated is" + " " + name, Status.FAIL);
			}
			else
			{
				report.updateTestLog("Verify 3PI vendor dropdown list is duplicated",
						"3PI vendor dropdown list is not duplicated" + " " + name, Status.PASS);
			}
		}
	}
}
