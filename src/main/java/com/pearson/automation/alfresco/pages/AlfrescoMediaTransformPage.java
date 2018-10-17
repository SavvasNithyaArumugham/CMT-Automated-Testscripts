package com.pearson.automation.alfresco.pages;

import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * FlightFinderPage class
 * 
 * @author Cognizant
 */

public class AlfrescoMediaTransformPage extends ReusableLibrary {

	// Media transform - image profile
	public String commonPageTitle = ".//*[@id='HEADER_TITLE']/span";
	public String mediaTrnsXpath = ".//*[@id='HEADER_MEDIA_TRANSFORM_text']/a";
	private String mediaTrnsHeaderXpath = ".//*[@id='HEADER_TITLE']";
	private String mediaPageLoadingXpath = ".//*[@id='popup1']//*[@class='content' and contains(.,'Loading')]";
	private String profileNameXpath = ".//*[contains(@id,'default-form')]//input[contains(@id,'profilename')]";
	private String profileDescXpath = ".//*[@name='profiledescription']";
	private String macroCodeXpath = ".//*[contains(@id,'default-form')]//input[contains(@id,'macrocode')]";
	private String subAssetCodeXpath = ".//*[contains(@id,'default-form')]//input[contains(@id,'subassetcode')]";
	private String addTrnsRulesXpath = ".//*[contains(@id,'submit-button')]";
	private String createImageBtnXpath = ".//*[@type='button' and contains(.,'CREATE IMAGE PROFILE')]";
	private String createaudioBtnXpath = ".//*[@type='button' and contains(.,'CREATE AUDIO PROFILE')]";
	public String addImageProfBtnXpath = ".//*[@class='buttons']//*[contains(text(),'Add Transformation Rules')]";
	private String msgImgXpath = ".//*[@id='message']/div/span";
	private String imgProfLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'File Format')]";
	private String imgTypeinputXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'File Format')]//ancestor::div[3]//*[contains(@class,'dijitDownArrowButton dijitArrowButtonContainer')]//input";
	private String resizeLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Resize')]";
	private String cancelImageProfBtnXpath = ".//*[@class='buttons']//button[contains(text(),'Cancel')]";
	private String cancelConfirmXpath = ".//*[@class='button-group']/span[1]//button";
	private String percentChkBoxXpath = ".//*[@id='resizeByPercentage']//*[@class='control-row']//div//div//input";
	private String resizeChkBoxXpath = ".//*[@id='resizeBySize']//*[@class='control-row']//div//div//input";
	private String percentValXpath = ".//*[@id='percentResize']//*[@class='control-row']//*[@name='percentResize']";
	private String heightValXpath = ".//*[@id='height']//*[@class='control-row']//*[@name='height']";
	private String widthValueXpath = ".//*[@id='width']//*[@class='control-row']//*[@name='width']";
	private String resizeAddBtnXpath = ".//span[contains(.,'Resize')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String resizeRemoveBtnXpath = ".//span[contains(.,'Resize')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String rotateLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Rotate')]";
	private String enterAngleFieldXpath = ".//*[@class='control-row']//*[@name='rotate']";
	private String rotateAddBtnXpath = ".//span[contains(.,'Rotate')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String rotateRemoveBtnXpath = ".//span[contains(.,'Rotate')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String cropLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Crop')]";
	private String cropInputXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Crop')]//ancestor::div[3]//*[contains(@class,'dijitDownArrowButton dijitArrowButtonContainer')]//input";
	private String cropTypeXpath = ".//*[contains(@id,'gravity_CONTROL_menu') and not(contains(@style,'none'))]//tbody//tr//td[contains(.,'CRAFT')]";
	private String cropHeightXpath = ".//*[@id='cropheight']//*[@class='control-row']//*[@name='height']";
	private String cropWidthXpath = ".//*[@id='cropwidth']//*[@class='control-row']//*[@name='width']";
	private String cropAddBtnXpath = ".//span[contains(.,'Crop')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String cropRemoveBtnXpath = ".//span[contains(.,'Crop')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String colorLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Color')]";
	private String tempXpathForColorOption = ".//*[@id='colorOptions_CONTROL']//*[contains(@id,'uniqName')]/*[text()='CRAFT']/ancestor::div[1]//input";
	private String colorAddBtnXpath = ".//span[contains(.,'Color')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String colorRemoveBtnXpath = ".//span[contains(.,'Color')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String colorModeLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'ColorMode')]";
	private String tempXpathForColorModeOption = ".//*[@id='colorMode_CONTROL']//*[contains(@id,'uniqName')]/*[text()='CRAFT']/ancestor::div[1]//input";
	private String colorModeAddBtnXpath = ".//span[contains(.,'ColorMode')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String colorModeRemoveBtnXpath = ".//span[contains(.,'ColorMode')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String creditLinelinkXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'CreditLine')]";
	private String tempXpathForCreditLineOption = ".//*[@id='creditLine_CONTROL']//*[contains(@id,'uniqName')]/*[text()='CRAFT']/ancestor::div[1]//input";
	private String creditLineAddBtnXpath = ".//span[contains(.,'CreditLine')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String creditLineRemoveBtnXpath = ".//span[contains(.,'CreditLine')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String grayscaleLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Grayscale')]";
	private String grayscaleCheckboxOption = ".//*[@id='grayScale']//*[@class='control-row']//input[contains(@name,'grayScale')]";
	private String grayscaleAddBtnXpath = ".//span[contains(.,'Grayscale')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String grayscaleRemoveBtnXpath = ".//span[contains(.,'Grayscale')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String waterMarkLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'WaterMark')]";
	private String tempXpathWaterMarkCheckboxOption = ".//*[@id='watermark_CONTROL']//*[contains(@id,'uniqName')]/*[text()='CRAFT']/ancestor::div[1]//input";
	private String waterMarkAddBtnXpath = ".//span[contains(.,'WaterMark')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String waterMarkRemoveBtnXpath = ".//span[contains(.,'WaterMark')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String flipLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Flip')]";
	private String tempXpathForflipOption = ".//*[@id='flipOption_CONTROL']//*[contains(@id,'uniqName')]/*[text()='CRAFT']/ancestor::div[1]//input";
	private String flipContainerAddBtnXpath = ".//span[contains(.,'Flip')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String flipRemoveBtnXpath = ".//span[contains(.,'Flip')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String compressionLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Compression')]";
	private String compressionCheckboxOption = ".//*[@id='compression']//*[@class='control-row']//input[contains(@name,'compression')]";
	private String compressionAddBtnXpath = ".//span[contains(.,'Compression')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String compressionRemoveBtnXpath = ".//span[contains(.,'Compression')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";

	private String resolutionLabelXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Resolution')]";
	private String enterResolutionFieldXpath = ".//*[@class='control-row']//*[@name='resolution']";
	private String resolutionAddBtnXpath = ".//span[contains(.,'Resolution')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String resolutionRemoveBtnXpath = ".//span[contains(.,'Resolution')]//parent::div//parent::div//following-sibling::div//span[contains(.,'REMOVE') and contains(@class,'ButtonText')]";
	// Media transform - video profile
	private String createVideoBtnXpath = ".//*[@type='button' and contains(.,'CREATE VIDEO PROFILE')]";
	public String vdoProNameXpath = ".//*[@name='profileName']";
	public String vdoProDescXpath = ".//*[@name='profiledesc']";
	public String vdoMacroCodeXpath = ".//*[@name='macrocode']";
	public String vdoSubAsstXpath = ".//*[@name='subassetcode']";
	public String vdoSaveBtnXpath = ".//*[contains(@class,'ButtonText') and contains(.,'Save')]";
	private String msgXpath = ".//*[@id='NOTIFICATION_PROMPT']/div[2]/div[1]";
	private String formsXpath = ".//*[contains(@id,'alfresco_renderers_Property')]/span/span[2]";
	private String formXpath = ".//*[@id='MY_HORIZONTAL_WIDGET_LAYOUT']";
	private String okBtnXpathInSaveNotification = ".//*[contains(@id,'uniqName')]//*[text()='OK']";
	public String testOutputFilePathVdoProf = "src/test/resources/AppTestData/TestOutput/MediaTrans.txt";
	public String testOutputFilePathMsg = "src/test/resources/AppTestData/TestOutput/Message.txt";
	public String valListXpath = ".//*[contains(@class,'alfresco-lists-views-layouts-Row alfresco-lists-views-')]//td[1]//*[@class='value']";
	private String cancelProfBtnXpath = ".//*[contains(@id,'alfresco_forms_Form')]/div/span[2]/span";
	private String cancelProfConfrmXpath = ".//*[@role='dialog']/div[2]/div[2]/span[1]/span";
	// Transformation rules page UI
	private String videoSettingsLabelXpath = ".//*[text()='Video settings ']";
	// Text box
	private String widthXpath = ".//*[text()='Width']";
	private String widthValXpath = ".//*[text()='Width']//ancestor::div[2]/div[2]//div[2]//input";
			//".//*[text()='Width']//ancestor::div[2]/div[2]//span[1]";
	private String hightXpath = ".//*[text()='Height']";
	private String hightValXpath = ".//*[text()='Height']//ancestor::div[2]/div[2]//div[2]//input";
			//".//*[text()='Height']//ancestor::div[2]/div[2]//span[1]";
	private String videoBitRateXpath = ".//*[text()='Video Bit Rate:(KBPS)']";
	private String videoBitRateValXpath = ".//*[text()='Video Bit Rate:(KBPS)']//ancestor::div[2]/div[2]//div[2]//input";
			//".//*[text()='Video Bit Rate:(KBPS)']//ancestor::div[2]/div[2]//span[1]";
	// buttons
	private String addXpath = ".//*[text()='ADD']";
	private String saveXpath = ".//*[text()='Save']";
	private String cancelXpath = ".//*[text()='Cancel']";
	private String summaryXpath = ".//*[text()='Selected Options:']";
	private String okXpath = ".//*[text()='Ok']";
	private String deleteConfirmOkBtnXpath = ".//*[contains(@class,'bd') and contains(.,'The Profile is deleted successfully')]/following-sibling::*//*[text()='Ok']";
	private String oKXpath = ".//*[@class='dijitDialogPaneContent']//*[contains(@class,'alfresco-buttons-AlfButton') and contains(.,'OK')]";
	private String saveImgProfXpath = ".//*[text()='SAVE']";
	private String tempXpathFoeFieldValue = ".//*[contains(@id,'alfresco_documentlibrary_views_layouts_Row')]/td[contains(.,'CRAFT')]//following-sibling::td//span[contains(@class,'value')]";
	private String tempXpathForLabel = ".//*[@class='title-row']//label[contains(.,'CRAFT')]";
	private String tempXpathForDefaultVal = ".//*[@class='title-row']//label[contains(.,'CRAFT')]//ancestor::div[2]//table//tr//td//div//span";
	// Select profile
	private String selectValOptionXpath = ".//*[@id='ProfileListTable']//tr//td[text()='CRAFT']//following-sibling::td//*[@class='profileAction']";
	private String editOptionXpath = ".//*[@class='ProfileActionsList' and not(contains(@style,'none'))]//li[contains(.,'Edit Profile')]";
	private String profileValueXpath = ".//*[text()='profileName']//ancestor::td//following-sibling::td//*[@class='value']";
	private String profileDescValueXpath = ".//*[text()='profileDescription']//ancestor::td//following-sibling::td//*[@class='value']";
	private String macroValueXpath = ".//*[text()='macroCode']//ancestor::td//following-sibling::td//*[@class='value']";
	private String subAsstValueXpath = ".//*[text()='subAssetCode']//ancestor::td//following-sibling::td//*[@class='value']";
	// to enter video profile details
	private String selectoptionsXpath = ".//*[text()='CRAFT']//ancestor::div[2]//table//tr//td[2]/input";
	private String selectValXpath = ".//table[contains(@id,'alfresco_forms_controls_Select') and not(contains(@style,'none'))]/tbody/tr/td[text()='CRAFT']";
	private String selectWidthValXpath = ".//*[contains(@name,'targetVideoWidth')]";
	private String selectHeightValXpath = ".//*[contains(@name,'targetVideoHeight')]";
	private String selectVdoBitRateValXpath = ".//*[contains(@name,'targetVideoBitrate')]";
	private String selectMaxBitRateValXpath = ".//*[contains(@name,'targetMaxBitrate')]";
	private String audioselectValXpath = ".//table[contains(@id,'alfresco_forms_controls_Select')]/tbody/tr/td[text()='CRAFT']";
	
	
	// to enter audio profile details
	
	private String audioformatXpath = ".//label[contains(text(),'Audio format')]//following::table//span[text()='Select Output Format']";
	private String audiorateXpath = ".//input[contains(@name,'targetAudioSample')]";
	private String audiochannelXpath = ".//input[contains(@name,'targetChannel')]";
	private String audiobitrateXpath = ".//*[contains(@name,'targetAudioBitrate')]";
	
		
	// AMTS-xpath
	private String prfSlctXPath = ".//*[@id='profileName' and text()='CRAFT']//ancestor::tr[@role='row']//input";
	private String prfSelectXpathForRendProf = ".//*[@id='ProfileListTable']//tbody//tr[1]//input";
	private String nxtBtnXPath = ".//*[@class='buttons']//button[contains(.,'Next')]";
	private String pvtNmeTxtXPath = ".//input[@name='fileNameExt']";
	private String finBtnXPath = ".//span[text()='Finish']";
	private String okBtnPrfConXPath = ".//div[@class='footer']//span[text()='Ok']";
	private String searchResultsListXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']//h3//a";
	private String filNmeYesXPath = ".//input[@name='filename'][@value='YES']";
	private String filNmeNoXPath = ".//input[@name='filename'][@value='NO']";
	private String watFldrYesXPath = ".//*[@id='watchfolder' and @value='true']";
	private String watFldrNoXPath = ".//*[@id='watchfolder' and @value='false']";
	private String addBtnDestXPath = ".//span[text()='Add']";
	private String uploadImageBtnXpath = "//button[text()='Upload Image']";
	private String selctFilesToUploadBtnXpath = "//*[contains(@id,'default-file-selection-button-overlay')]/span//*[contains(.,'Select files to upload')]";
	private String uploadInputField = ".//*[contains(@id,'default-file-selection-button-overlay')]/span/input";
	private String uploadImageInCreateProfileXpath = "//span[contains(text(),'CRAFT')]";
	private String formatTypeXpath = ".//*[contains(@id,'alfresco_forms_controls_Select') and not(contains(@style,'none'))]//tbody/tr/td[contains(.,'CRAFT')]";
	private String greyScaleCheckBoxXpath = "//input[@name='grayScale']//parent::div";
	private String grayScaleProfileSummaryXpath = "//*[contains(.,'Profile Summary')]//following-sibling::div//td//*[contains(.,'GrayScale')]";
	private String profilesummaryXpath  = "//*[contains(.,'Profile Summary')]//following-sibling::div//td//*[contains(.,'CRAFT')]";
	private String saveNotificationMessage = "//div[contains(.,'Save Notification')]//following-sibling::div//*[@class='dialog-body']";
	private String grayScalelinkXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Grayscale')]";
	private String fileFormatAddBtnXpath = ".//span[contains(.,'File Format')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String grayScaleAddBtnXpath = ".//span[contains(.,'Grayscale')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String transformationProfileNameXpath = ".//*[@id='ProfileListTable']/tbody/tr//td[contains(.,'CRAFT')]";
	private String applyProfileNotificationBodyXpath = "//*[@id='NOTIFICATION_PROMPT']//div[@class='dialog-body']";
	private String flipFormatXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Flip')]";
	private String flipVerticallyXpath = "//div[contains(text(),'Flip canvas Vertically')]//preceding-sibling::div/div";
	private String previewBtnXpath = "//span[contains(text(),'PREVIEW')]";
	private String preparingPreviewXpath = ".//*[@class='previewer Image']/*[contains(.,'Preparing previewer')]";
	private String flipAddBtnXpath = ".//span[contains(.,'Flip')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";

	private String docLibBarXapth = ".//div[contains(@id,'_documentlibrary_x0023_default-navBar')]";

	private String dataLoadingXpathMTP = ".//*[@id='ProfileListTable']//tr[contains(.,'Loading')]";
	private String previewedImageXpath = ".//*[@class='previewer Image']/img";

	private String firstProfXpath = ".//*[@id='ProfileListTable']/tbody/tr[1]/td[1]/input";
	private String notificationXpath = ".//*[@id='NOTIFICATION_PROMPT']//div//div[contains(.,'Select Either')]";
	private String notificationFileTypeXpath = ".//*[contains(@class,'dijitDialogPaneContent')]//*[@class='dialog-body']";

	// convert to compress
	private String compressXpath = ".//*[contains(@id,'alfresco_forms_Form')]//form//div//span[contains(.,'Compression')]";
	private String compressAddBtnXpath = ".//span[contains(.,'Compression')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	private String compressCheckBoxXpath = "//input[@name='compression']//parent::div";
	private String compressProfileSummaryXpath = "//*[contains(.,'Profile Summary')]//following-sibling::div//td//*[contains(.,'Compress')]";

	private String cancelBtnXpath = ".//*[@type='button' and contains(.,'Cancel')]";
	private String repositoryPgXpath = ".//*[@id='HEADER_TITLE']/*[contains(.,'Repository Browser')]";
	private String rotateTextBoxXpath = ".//*[@name='rotate']";

	// color mode
	private String selectColorModeXpath = ".//*[@class='radio-button-widget']//input[@value='CRAFT']//ancestor::div[1]";
	private String tempXpathForCreateProfileBtn = ".//*[@type='button' and contains(.,'CRAFT')]";
	private String addBtnXpath = ".//span[text()='Add']";
	private String recentSitesLinkXpath = ".//span[text()='Recent Sites']";
	private String tempSiteLinkXpath = ".//span[text()='CRAFT']";
	private String docLibLinkXpath = ".//*[contains(@class,'TreeNodeContainer')]//span[contains(@class,'dijitTreeLabel')]";
	private String rootNodeXpath = ".//*[contains(@class,'TreeExpandoClosed')]";
	private String okBtnXapth = ".//span[text()='OK']";
	private String fileVerionXpath = ".//*[@class='document-version']";
	private String applyTrasfromloadingXpath = ".//*[@id='ProfileListTable']//td[contains(.,'Loading')]";
	private String tempXpathForProfileSummaryFieldVal = ".//*[@id='SummaryLabel']//table/tbody/tr/td[contains(.,'CRAFT')]//following-sibling::td[2]";
	private String inLineEditSaveXpath = ".//button[contains(text(),'Save')]";
	private String inLineEditTextAreaXapth = ".//textarea[contains(@id,'_default_prop_cm_content')]";
	private String renditionPromptXapth = ".//*[contains(@id,'prompt') and contains(@style,'visible')]";
	private String renditionOkBtnXpath = ".//button[text()='OK']";
	private String resizeMsgXapth = ".//div[text()='Please select either By Percentage or By Absolute size']";
	private String percentResizeMsgXpath = ".//*[@id='percentResize']/div[1]/span[2]";
	private String hightResizeMsgXpath = ".//*[@id='height']/div[1]/span[2]";
	private String widthResizeMsgXpath = ".//*[@id='width']/div[1]/span[2]";
	private String shareURLXapth = ".//*[@class='link-info']//input";
	private String grayScaleRemoveBtnXpath = ".//span[contains(.,'Grayscale')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	// delete profile from media transform page
	private String deleteOptionXPath = ".//*[@class='ProfileActionsList' and not(contains(@style,'none'))]//li[contains(.,'Delete')]";
	private String deleteXPath = ".//*[text()='Delete']";
	private String commonPageTitleXpath = ".//*[@id='HEADER_TITLE']";
	private String pageBodyXpath = ".//*[@id='bd']";
	private String pagesXpath = ".//*[contains(@id,'ProfileListTable_paginate')]//span//a[contains(@class,'paginate_button')]";
	private String profileNamesXpath = ".//*[@id='ProfileListTable']/tbody/tr//td[1]";
	private String searchnamesXpath = ".//*[@id='ProfileListTable']/tbody/tr//td[CRAFT]";
	private String profileNamesInSelectWindowXpath = ".//*[@id='ProfileListTable']/tbody/tr//*[@id='profileName']";
	private String nextPageXpath = ".//*[@id='ProfileListTable_next']";
	private String searchBoxXpath = ".//*[@id='ProfileListTable_filter']//label[contains(.,'Search')]/input";
	private String transformIconXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']//h3//a[contains(.,'CRAFT')]//ancestor::tr//*[@class='status']//img";

	private String searchFieldXpath = ".//*[@id='ProfileListTable_filter']//input";

	private String errorNotificXpathWhenDestAndSourceSame = ".//*[@id='NOTIFICATION_PROMPT']//*[text()='Ensure Source and Destination are not same.']/following-sibling::*//*[text()='OK']";
	private String errorMsgXpathWhenTargetFolderAddedViaBrowseAndFieldVal = ".//*[@id='NOTIFICATION_PROMPT']//*[@class='dijitDialogPaneContent']/*[contains(.,'Browse Target Folder') and contains(.,'Target folder path')]/following-sibling::*//*[text()='OK']";
	private String tempXpathForRemoveAddedTargetFolder = ".//table//tr//td[contains(.,'CRAFT')]//following-sibling::td//img";
	private String relativeFilePathXpathFortargetFolder = ".//*[contains(@class,'dijitInputContainer')]/input[contains(@name,'destRelativePath')]";

	private String tempXpathForMediaWatchFolderInDocLib = ".//*[@class='yui-dt-data']/tr/td//h3//a[text()='CRAFT']//ancestor::td/preceding-sibling::td//*[contains(@title,'This Folder Attached With Mediawatchfolder')]";

	private String tempprofileadd = ".//div[@role='tablist']//span[@title and text()='CRAFT']";
	private String tempwaitforpanel = ".//div[@role='tablist']//span[@title and text()='CRAFT']//following::div[@role='tabpanel' and contains(@style,'block')]";
	
	private String tempgetvalue = ".//div[@role='tablist']//span[@title and text()='CRAFT']//following::div[@role='tabpanel' and contains(@style,'block')]//div[@class='dijitRuleLabel dijitRuleLabelH']";
	
	public String fliphorizontal = ".//div[@class='radio-button-label' and text()='Flip canvas Horizontally']";
	
	// Audio Profile Name:
	public String audioProfNames = "//form[@data-dojo-attach-point='containerNode']//label[@class='label']";
	public String audioProfNamesXpath = "//input[@name='profileName']";
	public String audioProfDescrXpath = "//*[@name='profiledesc']";
	public String macroXpath = "//*[@name='macrocode']";
	public String subassetcodeXpath = "//*[@name='subassetcode']";
	public String profilecreatedSucessfullyXpath = "//*[contains(text(),'Profile created successfully with name')]";
	public String OkbuttonXpath  = "//*[contains(text(),'OK')]";
	public String subassetcodemustnotempty = "//div[contains(text(),'Sub Asset Code must not be empty.')]";
	public String duplicateerrormessageXpath  = "//*[contains(text(),'Duplicate Profile with same name or sub asset code exists')]";
	public String transformationprofileXpath = "//*[@title='Transformation Profile Summary']";
	
	public String audiosettingvaluesXpath = "(//div[@class='dijitPopup dijitMenuPopup']/table[@role='listbox'])[last()-CRAFT]//tr/td[2]";
	public String audioprofiledetails = ".//*[@id='ProfileListTable']//tr//td[text()='CRAFT']//following-sibling::td";
    public String audioprofilenamedetails =	".//*[@id='ProfileListTable']//tr//td[text()='CRAFT']";
	public String transformationprofilesummaryXpath = "//*[text()='CRAFT']//following::td[1]/span/span/span[2]";
    private String mediasummarytable = ".//*[@id='ProfileListTable']//tbody//following::tr[@role='row']/td[CRAFT]";
    private String mediasummarryvaluetable = ".//*[@id='ProfileListTable']//thead//th/b[text()='CRAFT']";
    private String sliderbarxpath = ".//*[@id='CRAFT']//*[@class='dijitSliderBar dijitSliderBarH dijitSliderProgressBar dijitSliderProgressBarH']";
    private String sliderbarclickxpath  = "//*[@id='CRAFT']/tbody/tr[2]/td[3]/div/div[1]/div/div";
    private String addbuttonxpathofitems = ".//span[contains(.,'CRAFT')]//parent::div//parent::div//following-sibling::div//span[contains(.,'ADD') and contains(@class,'ButtonText')]";
	public String documentsfilesxpath = "//*[@class='filename']/span[2]/a";
	
	
    
    public AlfrescoMediaTransformPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Media transform option check
	public boolean isMediaTransAvailable() {
		boolean flag = false;
		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, mediaTrnsXpath))) {
				UIHelper.highlightElement(driver, mediaTrnsXpath);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Navigate to Media transform page
	public void navigateToMediaTransPage() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTrnsXpath);
			UIHelper.highlightElement(driver, mediaTrnsXpath);
			UIHelper.click(driver, mediaTrnsXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTrnsHeaderXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);

			report.updateTestLog("Navigate to Media Transform Page", "Navigated to Media transform page successfully.",
					Status.DONE);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Navigate to Media Transform Page", "Failed to Navigate Media transform page.",
					Status.FAIL);
		}
	}

	// Create image profile
	public void createImageProfile() {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, mediaPageLoadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createImageBtnXpath);
			UIHelper.highlightElement(driver, createImageBtnXpath);
			UIHelper.click(driver, createImageBtnXpath);

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println("" + sdf.format(cal.getTime()));

			String profName = dataTable.getData("Media_Transform", "ProfName");
			String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
			String macCode = dataTable.getData("Media_Transform", "macCode");
			String subAsstCode = dataTable.getData("Media_Transform", "subAsstCode");

			profName = profName + sdf.format(cal.getTime());
			profName = profName.replaceAll(":", "");
			profDesc = profDesc + sdf.format(cal.getTime());
			profDesc = profDesc.replaceAll(":", "");
			macCode = macCode + sdf.format(cal.getTime());
			macCode = macCode.replaceAll(":", "");
			subAsstCode = subAsstCode + sdf.format(cal.getTime());
			subAsstCode = subAsstCode.replaceAll(":", "");

			UIHelper.waitForVisibilityOfEleByXpath(driver, profileNameXpath);
			UIHelper.findAnElementbyXpath(driver, profileNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, profileNameXpath, profName);
			UIHelper.findAnElementbyXpath(driver, profileDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, profileDescXpath, profDesc);
			UIHelper.findAnElementbyXpath(driver, macroCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, macroCodeXpath, macCode);
			UIHelper.findAnElementbyXpath(driver, subAssetCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, subAssetCodeXpath, subAsstCode);

			UIHelper.click(driver, addTrnsRulesXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgXpath);

			report.updateTestLog("Create Image profile",
					"Image profile created successfully." + "<br><b>Image Profile Name : </br>" + profName,
					Status.DONE);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Create Image profile", "Failed to Create Image profile.", Status.FAIL);
		}
	}

	// to search the profile using search box

	public void searchMediaProfile(String ProfileName) {

		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		UIHelper.waitForVisibilityOfEleByXpath(driver, searchBoxXpath);
		UIHelper.highlightElement(driver, searchBoxXpath);
		UIHelper.click(driver, searchBoxXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, searchBoxXpath, ProfileName);
		UIHelper.waitFor(driver);

	}

	// click on create video profiole button
	public void clickOnCreateVideoProfBtn() {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, mediaPageLoadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createVideoBtnXpath);
			UIHelper.highlightElement(driver, createVideoBtnXpath);
			UIHelper.click(driver, createVideoBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addImageProfBtnXpath);
			//UIHelper.click(driver, addImageProfBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			
			report.updateTestLog("Click on create video profile button",
					"Create Video profile button clicked successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on create video profile button",
					"Failed to click on Create video profile button", Status.FAIL);
		}
	}

	// Check create profile buttons on the top of created profile list
	public boolean checkCreateProfileButton(String buttonLabelName) {
		boolean isDisplayedCreateProfileButton = false;
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, mediaPageLoadingXpath);
			String finalXpathForCreateProfileBtn = tempXpathForCreateProfileBtn.replace("CRAFT", buttonLabelName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForCreateProfileBtn);
			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForCreateProfileBtn)) {
				UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, finalXpathForCreateProfileBtn));
				isDisplayedCreateProfileButton = true;
			} else {
				isDisplayedCreateProfileButton = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedCreateProfileButton;
	}

	// Create video profile with unique name
	public void enterVideoProfDetailsWithUniqueName() {
		try {

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println("" + sdf.format(cal.getTime()));

			String profName = dataTable.getData("Media_Transform", "ProfName");
			String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
			String macCode = dataTable.getData("Media_Transform", "macCode");
			String subAsstCode = dataTable.getData("Media_Transform", "subAsstCode");

			profName = profName + sdf.format(cal.getTime());
			profName = profName.replaceAll(":", "");
			profDesc = profDesc + sdf.format(cal.getTime());
			profDesc = profDesc.replaceAll(":", "");
			macCode = macCode + sdf.format(cal.getTime());
			macCode = macCode.replaceAll(":", "");
			subAsstCode = subAsstCode + sdf.format(cal.getTime());
			subAsstCode = subAsstCode.replaceAll(":", "");
			new FileUtil().writeTextToFile(profName, testOutputFilePathVdoProf);

			UIHelper.waitForVisibilityOfEleByXpath(driver, vdoProNameXpath);
			UIHelper.findAnElementbyXpath(driver, vdoProNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProNameXpath, profName);
			UIHelper.findAnElementbyXpath(driver, vdoProDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProDescXpath, profDesc);
			UIHelper.findAnElementbyXpath(driver, vdoMacroCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoMacroCodeXpath, macCode);
			UIHelper.findAnElementbyXpath(driver, vdoSubAsstXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoSubAsstXpath, subAsstCode);

			report.updateTestLog("Enter video profile details",
					"Video profile details entered successfully." + "<br><b>Video Profile Name : </br>" + profName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter video profile details", "Failed to Enter video profile Details.", Status.FAIL);
		}
	}

	// Common Method for Enter Video Profile details
	public void commonMethodForEnterVideoProfDetails(String profName, String profDesc, String macCode,
			String subAsstCode) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, vdoProNameXpath);
			UIHelper.findAnElementbyXpath(driver, vdoProNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProNameXpath, profName);
			UIHelper.findAnElementbyXpath(driver, vdoProDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProDescXpath, profDesc);
			UIHelper.findAnElementbyXpath(driver, vdoMacroCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoMacroCodeXpath, macCode);
			UIHelper.findAnElementbyXpath(driver, vdoSubAsstXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoSubAsstXpath, subAsstCode);

			report.updateTestLog("Enter video profile details",
					"Video profile details entered successfully." + "<br><b>Video Profile Name : </br>" + profName,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter video profile details", "Failed to Enter video profile Details.", Status.FAIL);
		}
	}

	// Click on Add video profile button
	public boolean clickOnAddVideoProfBtn() {
		boolean flag;
		try {
			/*UIHelper.waitForVisibilityOfEleByXpath(driver, vdoSaveBtnXpath);
			UIHelper.highlightElement(driver, vdoSaveBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, vdoSaveBtnXpath);*/
			UIHelper.waitForVisibilityOfEleByXpath(driver, addImageProfBtnXpath);
			UIHelper.highlightElement(driver, addImageProfBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, addImageProfBtnXpath);
			
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathInSaveNotification);
			} catch (Exception e) {
			}
			UIHelper.click(driver, okBtnXpathInSaveNotification);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			/*
			 * UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgXpath);
			 * UIHelper.waitForPageToLoad(driver); try {
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, formXpath); }
			 * catch (Exception e) { } UIHelper.highlightElement(driver,
			 * formXpath); UIHelper.waitFor(driver);
			 * UIHelper.waitForPageToLoad(driver);
			 */
			report.updateTestLog("Click on Add video profile button", "Add Video profile button clicked successfully",
					Status.DONE);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Add video profile button", "Failed to click Add Video profile button",
					Status.FAIL);
			flag = false;
		}
		return flag;

	}

	// Enter video profile details without unique name
	public void enterVideoProfDetails(String profName) {
		try {

			String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
			String macCode = dataTable.getData("Media_Transform", "macCode");
			String subAsstCode = dataTable.getData("Media_Transform", "subAsstCode");

			new FileUtil().writeTextToFile(profName, testOutputFilePathVdoProf);

			UIHelper.waitForVisibilityOfEleByXpath(driver, vdoProNameXpath);
			UIHelper.findAnElementbyXpath(driver, vdoProNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProNameXpath, profName);
			UIHelper.findAnElementbyXpath(driver, vdoProDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProDescXpath, profDesc);
			UIHelper.findAnElementbyXpath(driver, vdoMacroCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoMacroCodeXpath, macCode);
			UIHelper.findAnElementbyXpath(driver, vdoSubAsstXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoSubAsstXpath, subAsstCode);

			report.updateTestLog("Enter video profile details",
					"Video profile details entered successfully." + "<br><b>Video Profile Name : </br>" + profName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter video profile details",
					"Failed to enter video profile details." + "<br><b>Video Profile Name : </br>" + profName,
					Status.FAIL);
		}

	}

	// check for Duplicate message
	public boolean checkDuplicateMessageOnAddDplicateVideoProf() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, addImageProfBtnXpath);
			UIHelper.highlightElement(driver, addImageProfBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, addImageProfBtnXpath);
			report.updateTestLog("Click on Add video profile button", "Add Video profile button clicked successfully",
					Status.DONE);

			UIHelper.waitForVisibilityOfEleByXpath(driver, msgXpath);
			if (UIHelper.getTextFromWebElement(driver, msgXpath)
					.equalsIgnoreCase("Duplicate Profile with same name or sub asset code exists")) {
				report.updateTestLog("Display the warning message", "Display the warning message"+UIHelper.getTextFromWebElement(driver, msgXpath),
						Status.DONE);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Add video profile button", "Failed to click Add Video profile button",
					Status.FAIL);
			flag = false;
		}
		return flag;

	}

	// Click on cancel button while Adding an video profile
	public boolean clickOnCancelProfBtn() {
		boolean flag;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelProfBtnXpath);
			UIHelper.highlightElement(driver, cancelProfBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, cancelProfBtnXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelProfConfrmXpath);
			UIHelper.highlightElement(driver, cancelProfConfrmXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, cancelProfConfrmXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, createVideoBtnXpath);
			UIHelper.highlightElement(driver, createVideoBtnXpath);

			report.updateTestLog("Click on Cancel video profile button",
					"Cancel Video profile button clicked successfully", Status.DONE);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Cancel video profile button", "Failed to click Cancel Video profile button",
					Status.FAIL);
			flag = false;
		}
		return flag;

	}

	// Check Transformation rules page UI
	public boolean checkUiInTransRulesPage(String testData) {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, videoSettingsLabelXpath);
			UIHelper.highlightElement(driver, videoSettingsLabelXpath);
			String splittedFields[] = testData.split(",");
			String width = dataTable.getData("Media_Transform", "width");
			String height = dataTable.getData("Media_Transform", "height");
			String attribute = dataTable.getData("Media_Transform", "verifyVdoSettings");
			String videoBitRate = dataTable.getData("Media_Transform", "Video_Bit");

			if (splittedFields != null) {
				for (String value : splittedFields) {
					String splitFiledValue[] = value.split(":");

					if (splitFiledValue != null) {
						String finalFieldLabelXpath = tempXpathForLabel.replace("CRAFT", splitFiledValue[0]);
						System.out.println(splitFiledValue[0]);
						String finalFieldValueXpath = tempXpathForDefaultVal.replace("CRAFT", splitFiledValue[0]);
						if (UIHelper.checkForAnElementbyXpath(driver, finalFieldLabelXpath)) {
							UIHelper.highlightElement(driver, finalFieldLabelXpath);

							String actualFieldValue = UIHelper.getTextFromWebElement(driver, finalFieldValueXpath);
							if (actualFieldValue.contains(splitFiledValue[1])
									|| actualFieldValue.equalsIgnoreCase(splitFiledValue[1])) {

								flag = true;
								UIHelper.highlightElement(driver, finalFieldValueXpath);

							} else {
								flag = false;
							}

						} else {
							flag = false;
						}
					}
				}
			}
			if (UIHelper.checkForAnElementbyXpath(driver, widthXpath)
					&& UIHelper.checkForAnElementbyXpath(driver, hightXpath)
					&& UIHelper.checkForAnElementbyXpath(driver, videoBitRateXpath)
					&& /*
						 * UIHelper.checkForAnElementbyXpath(driver, addXpath)
						 * &&
						 */UIHelper.checkForAnElementbyXpath(driver, saveXpath)
					&& UIHelper.checkForAnElementbyXpath(driver, cancelXpath)) {
				UIHelper.highlightElement(driver, widthXpath);
				UIHelper.highlightElement(driver, hightXpath);
				UIHelper.highlightElement(driver, videoBitRateXpath);
				/* UIHelper.highlightElement(driver, addXpath); */
				UIHelper.highlightElement(driver, saveXpath);
				UIHelper.highlightElement(driver, cancelXpath);

				if (UIHelper.getAttributeFromWebElement(driver, widthValXpath,attribute).equals(width)
						&& UIHelper.getAttributeFromWebElement(driver, hightValXpath,attribute).equals(height)
						&& UIHelper.getAttributeFromWebElement(driver, videoBitRateValXpath,attribute).equals(videoBitRate)) {
					UIHelper.highlightElement(driver, widthValXpath);
					UIHelper.highlightElement(driver, hightValXpath);
					UIHelper.highlightElement(driver, videoBitRateValXpath);
					flag = true;
				} else {
					flag = false;
				}
			} else {
				flag = false;
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check for UI in Transformation Rules page", "Failed to display all UI elements",
					Status.FAIL);
			flag = false;
		}
		return flag;

	}

	// click on Add button
	public boolean clickAddBtn() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, addXpath);
			UIHelper.click(driver, addXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, summaryXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, summaryXpath));
			UIHelper.highlightElement(driver, summaryXpath);

			report.updateTestLog("Click on [ADD] button", "User clicked the [ADD] button", Status.DONE);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on [ADD] button", "Failed to click [ADD] button", Status.FAIL);
			flag = false;
		}
		return flag;
	}

	// click on Add button
	public boolean clickSaveBtn() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, saveXpath);
			UIHelper.click(driver, saveXpath);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathInSaveNotification);
			} catch (Exception e) {
			}
			UIHelper.click(driver, okBtnXpathInSaveNotification);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
			UIHelper.waitFor(driver);
			flag = true;
			report.updateTestLog("Click on [SAVE] button", "User clicked the [SAVE] button", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on [SAVE] button", "Failed to click [SAVE] button", Status.FAIL);
			flag = false;
		}
		return flag;
	}

	// Create video profile with unique name and sub asset code
	public void enterVideoProfDetailsWithUniqueNameAndSubAsst(String profName, String subAsstCode) {
		try {

			String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
			String macCode = dataTable.getData("Media_Transform", "macCode");

			UIHelper.waitForVisibilityOfEleByXpath(driver, vdoProNameXpath);
			UIHelper.findAnElementbyXpath(driver, vdoProNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProNameXpath, profName);
			UIHelper.findAnElementbyXpath(driver, vdoProDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProDescXpath, profDesc);
			UIHelper.findAnElementbyXpath(driver, vdoMacroCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoMacroCodeXpath, macCode);
			UIHelper.findAnElementbyXpath(driver, vdoSubAsstXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoSubAsstXpath, subAsstCode);

			report.updateTestLog("Enter video profile details",
					"Video profile details entered successfully." + "<br><b>Video Profile Name : </br>" + profName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter video profile details", "Failed to Enter video profile Details.", Status.FAIL);
		}
	}

	// edit profile from media transform page
	public void editProfileFrmMediaTransPg(String profName) {
		try {

			// navigateToProfilePage(profName);
			isProfileDisplatedInNavigatedPages(profName);

			String finalSelectValOptionXpath = selectValOptionXpath.replace("CRAFT", profName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectValOptionXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalSelectValOptionXpath));
			UIHelper.highlightElement(driver, finalSelectValOptionXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectValOptionXpath);
			UIHelper.click(driver, finalSelectValOptionXpath);
			UIHelper.click(driver, editOptionXpath);
			UIHelper.waitForPageToLoad(driver);
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, formsXpath);
			 * UIHelper.highlightElement(driver, formsXpath);
			 */
			report.updateTestLog("Navigate to edit media Profile",
					"Edit profile page displayed successfully." + "<br><b>Profile Name : </br>" + profName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Navigate to edit media Profile",
					"Failed to navigate to Create profile page." + "<br><b>Profile Name : </br>" + profName,
					Status.FAIL);
		}

	}

	// verify video profile details
	public boolean verifyVideoProfDetails(String profName, String subAsstCode) {
		try {

			String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
			String macCode = dataTable.getData("Media_Transform", "macCode");
			UIHelper.waitForVisibilityOfEleByXpath(driver, profileValueXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, profileValueXpath));
			if (UIHelper.getTextFromWebElement(driver, profileValueXpath).equals(profName)
					&& UIHelper.getTextFromWebElement(driver, profileDescValueXpath).equals(profDesc)
					&& UIHelper.getTextFromWebElement(driver, macroValueXpath).equals(macCode)
					&& UIHelper.getTextFromWebElement(driver, subAsstValueXpath).equals(subAsstCode)) {

				UIHelper.highlightElement(driver, profileValueXpath);
				UIHelper.highlightElement(driver, profileDescValueXpath);
				UIHelper.highlightElement(driver, macroValueXpath);
				UIHelper.highlightElement(driver, subAsstValueXpath);

				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Image/Video Profile details",
					"Failed to display the correct details." + "<br><b>Profile Name : </br>" + profName, Status.FAIL);
			return false;
		}

	}

	// click on create Image profile button
	public void clickOnCreateImageProfBtn() {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, mediaPageLoadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createImageBtnXpath);
			UIHelper.highlightElement(driver, createImageBtnXpath);
			UIHelper.click(driver, createImageBtnXpath);
			report.updateTestLog("Click on create Image profile button",
					"Create Image profile button clicked successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on create Image profile button",
					"Failed to click on Create Image profile button", Status.FAIL);
		}
	}

	// Create Image profile with unique name and sub asset code
	public void enterImageProfDetailsWithUniqueNameAndSubAsst(String profName, String subAsstCode) {
		try {

			String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
			String macCode = dataTable.getData("Media_Transform", "macCode");

			UIHelper.waitForVisibilityOfEleByXpath(driver, profileNameXpath);
			UIHelper.findAnElementbyXpath(driver, profileNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, profileNameXpath, profName);
			UIHelper.findAnElementbyXpath(driver, profileDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, profileDescXpath, profDesc);
			UIHelper.findAnElementbyXpath(driver, macroCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, macroCodeXpath, macCode);
			UIHelper.findAnElementbyXpath(driver, subAssetCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, subAssetCodeXpath, subAsstCode);

			report.updateTestLog("Enter Image profile details",
					"Image profile details entered successfully." + "<br><b>Image Profile Name : </br>" + profName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter Image profile details", "Failed to Enter Image profile Details.", Status.FAIL);
		}
	}

	// Common method for create image profile
	public void commonMethodForEnterImageProfDetails(String profName, String profDesc, String macCode,
			String subAsstCode, String filePath, String fileName) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, profileNameXpath);
			UIHelper.findAnElementbyXpath(driver, profileNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, profileNameXpath, profName);
			UIHelper.findAnElementbyXpath(driver, profileDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, profileDescXpath, profDesc);
			UIHelper.findAnElementbyXpath(driver, macroCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, macroCodeXpath, macCode);
			UIHelper.findAnElementbyXpath(driver, subAssetCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, subAssetCodeXpath, subAsstCode);

			if (filePath != null && fileName != null && !filePath.isEmpty() && !fileName.isEmpty()) {
				String finalFilePath;
				if (filePath.contains("Automation/Alfresco")) {
					finalFilePath = filePath;
				} else {
					finalFilePath = System.getProperty("user.dir") + filePath;
				}

				if (UIHelper.checkForAnElementbyXpath(driver, uploadImageBtnXpath)) {
					String finalXpathForUploadedFile = uploadImageInCreateProfileXpath.replace("CRAFT", fileName);

					UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, uploadImageBtnXpath));

					UIHelper.click(driver, uploadImageBtnXpath);

					UIHelper.waitForVisibilityOfEleByXpath(driver, selctFilesToUploadBtnXpath);
					WebElement uploadInputFieldEle = driver.findElement(By.xpath(selctFilesToUploadBtnXpath));
					UIHelper.highlightElement(driver, uploadInputFieldEle);

					WebElement fileInput = driver.findElement(By.xpath(uploadInputField));
					fileInput.sendKeys(finalFilePath + fileName);

					UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForUploadedFile);
					UIHelper.waitFor(driver);
				}
			}

			report.updateTestLog("Enter Image profile details",
					"Image profile details entered successfully." + "<br><b>Image Profile Name : </br>" + profName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter Image profile details", "Failed to Enter Image profile Details.", Status.FAIL);
		}
	}

	// Click on Add Image profile button
	public boolean clickOnAddImageProfBtn() {
		boolean flag;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, addImageProfBtnXpath);
			UIHelper.highlightElement(driver, addImageProfBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, addImageProfBtnXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgImgXpath);
			UIHelper.waitForPageToLoad(driver);
			// UIHelper.waitForVisibilityOfEleByXpath(driver, formsImgXpath);
			// UIHelper.highlightElement(driver, formsImgXpath);

			report.updateTestLog("Click on Add Image/Video profile button",
					"Add Image/Video profile button clicked successfully", Status.DONE);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on Add Image/video profile button",
					"Failed to click Add Image/video profile button", Status.FAIL);
			flag = false;
		}
		return flag;

	}

	// Click on Add Image profile button
	public boolean clickOnAddTransformationRulesBtn() {
		boolean flag;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, addImageProfBtnXpath);
			UIHelper.highlightElement(driver, addImageProfBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, addImageProfBtnXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgImgXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTrnsHeaderXpath);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, mediaTrnsHeaderXpath));
			UIHelper.waitFor(driver);

			report.updateTestLog("Click on 'Add Transformation Rules' button",
					"'Add Transformation Rules' button clicked successfully", Status.DONE);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on 'Add Transformation Rules' button",
					"Failed to click 'Add Transformation Rules' button", Status.FAIL);
			flag = false;
		}
		return flag;

	}

	// Select transform type
	public void selectImgTransType(String type) {
		try {
			String finalValXpath = selectValXpath.replace("CRAFT", type);
			UIHelper.waitForVisibilityOfEleByXpath(driver, imgProfLabelXpath);
			UIHelper.click(driver, imgProfLabelXpath);
			UIHelper.click(driver, imgTypeinputXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalValXpath);
			UIHelper.click(driver, finalValXpath);
			report.updateTestLog("Select transform type", "Transform type selected successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select transform type", "Failed to select transform type", Status.FAIL);

		}
	}
	
	// Select Videotransform type
	public void selectVidTransType(String type) {
		try {
			String finalValXpath = selectValXpath.replace("CRAFT", type);
			UIHelper.waitForVisibilityOfEleByXpath(driver, imgProfLabelXpath);
			UIHelper.click(driver, imgProfLabelXpath);
			UIHelper.click(driver, imgTypeinputXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalValXpath);
			UIHelper.click(driver, finalValXpath);
			report.updateTestLog("Select transform type", "Transform type selected successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select transform type", "Failed to select transform type", Status.FAIL);

		}
	}


	// click on Add button-Img prof
	public boolean clickAddBtnImgProf() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, addXpath);
			UIHelper.click(driver, addXpath);
			UIHelper.waitFor(driver);
			flag = true;
			report.updateTestLog("Click on [ADD] button", "[ADD] button clicked successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on [ADD] button", "Failed to click [ADD] button", Status.FAIL);
			flag = false;
		}
		return flag;
	}

	// click on Save button
	public boolean clickSaveBtnImgProf() {
		boolean flag = false;
		try {
			//UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, saveImgProfXpath));
			UIHelper.waitForVisibilityOfEleByXpath(driver, saveImgProfXpath);
			UIHelper.click(driver, saveImgProfXpath);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXpathInSaveNotification);
			} catch (Exception e) {
			}
			UIHelper.click(driver, okBtnXpathInSaveNotification);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, createVideoBtnXpath);
			} catch (Exception e) {
			}
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);

			flag = true;
			report.updateTestLog("Click on [SAVE] button", "[SAVE] button clicked successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on [SAVE] button", "Failed to click [SAVE] button", Status.FAIL);
			flag = false;
		}
		return flag;
	}

	// verify video Settings details
	public boolean verifyVideoSettingsDetails(String testData) {
		boolean isDisplayedFieldVal = false;
		try {

			String splittedFields[] = testData.split(",");

			if (splittedFields != null) {
				for (String value : splittedFields) {
					String splitFiledValue[] = value.split(":");

					if (splitFiledValue != null) {
						String finalFieldValueXpath = tempXpathFoeFieldValue.replace("CRAFT", splitFiledValue[0]);
						UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalFieldValueXpath));
						UIHelper.highlightElement(driver, finalFieldValueXpath);
						String actualFieldValue = UIHelper.getTextFromWebElement(driver, finalFieldValueXpath);

						if (actualFieldValue.contains(splitFiledValue[1])
								|| actualFieldValue.equalsIgnoreCase(splitFiledValue[1])) {
							isDisplayedFieldVal = true;
						} else {
							isDisplayedFieldVal = false;
						}
					}

				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify media Profile", "Failed to display the correct details.", Status.FAIL);
			return false;
		}
		return isDisplayedFieldVal;

	}

	// Enter video Settings details
	public void enterVideoSettingsDetails(String videoSettingsData) {
		try {

			String width = dataTable.getData("Media_Transform", "width");
			String height = dataTable.getData("Media_Transform", "height");
			String videoBitRate = dataTable.getData("Media_Transform", "Video_Bit");
			String maxBitRate = dataTable.getData("Media_Transform", "Max_BitRate");
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectWidthValXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, selectWidthValXpath));
			UIHelper.findAnElementbyXpath(driver, selectWidthValXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, selectWidthValXpath, width);
			UIHelper.findAnElementbyXpath(driver, selectHeightValXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, selectHeightValXpath, height);
			UIHelper.findAnElementbyXpath(driver, selectVdoBitRateValXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, selectVdoBitRateValXpath, videoBitRate);

			String splittedFields[] = videoSettingsData.split(",");

			if (splittedFields != null) {
				for (String value : splittedFields) {
					String splitFiledValue[] = value.split(":");

					if (splitFiledValue != null) {
						String finalFieldValueXpath = selectoptionsXpath.replace("CRAFT", splitFiledValue[0]);
						String finalSelectValXpath = selectValXpath.replace("CRAFT", splitFiledValue[1]);
						UIHelper.highlightElement(driver, finalFieldValueXpath);
						UIHelper.click(driver, finalFieldValueXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectValXpath);
						// UIHelper.waitFor(driver);
						UIHelper.click(driver, finalSelectValXpath);

						if (splitFiledValue[1].equalsIgnoreCase("VBR")) {
							UIHelper.waitForVisibilityOfEleByXpath(driver, selectMaxBitRateValXpath);
							if (UIHelper.checkForAnElementbyXpath(driver, selectMaxBitRateValXpath)) {
								UIHelper.findAnElementbyXpath(driver, selectMaxBitRateValXpath).clear();
								UIHelper.sendKeysToAnElementByXpath(driver, selectMaxBitRateValXpath, maxBitRate);
							}
						}
					}

				}

				report.updateTestLog("Edit media Profile settings", "Media profile details updated successfully.",
						Status.PASS);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			/*
			 * report.updateTestLog("Edit media Profile settings",
			 * "Failed to edit the settings details.", Status.FAIL);
			 */

		}

	}

	/**
	 * @author 412766
	 */
	public void applyTransformationByFileBased(String fileName, String prefTxt) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, filNmeYesXPath);
			UIHelper.highlightElement(driver, filNmeYesXPath);
			UIHelper.click(driver, filNmeYesXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
			UIHelper.click(driver, nxtBtnXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pvtNmeTxtXPath);
			driver.findElement(By.xpath(pvtNmeTxtXPath)).sendKeys(prefTxt);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
			UIHelper.click(driver, finBtnXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnPrfConXPath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, okBtnPrfConXPath))) {
				report.updateTestLog("Apply File based Transformation",
						"Transformation applied succesfully" + "<br><b> File Name : </b>" + fileName, Status.PASS);
			} else {
				report.updateTestLog("Apply File based Transformation",
						"Transformation not applied" + "<br><b> File Name : </b>" + fileName, Status.FAIL);
			}
			UIHelper.click(driver, okBtnPrfConXPath);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply File based Transformation", "Apply File based Transformation Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void applyTransformationToTargetFolder(String prfNme, String folderName, String targetFolder, String prefTxt,
			String siteName) {
		boolean isProfileDisplayed = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			
			  UIHelper.waitForVisibilityOfEleByXpath(driver, watFldrYesXPath);
			  UIHelper.highlightElement(driver, watFldrYesXPath);
			 UIHelper.click(driver, watFldrYesXPath);
			 UIHelper.waitForPageToLoad(driver);
			 UIHelper.waitFor(driver);
			searchProfile(prfNme);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);

			if (isProfileDisplayed == false) {
				List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);

				WebElement element = noOfPagesEle.get(noOfPagesEle.size() - 1);
				String noOfPages = element.getText();
				int lastEle = Integer.parseInt(noOfPages);
				System.out.println(lastEle);
				for (int index = 0; index < lastEle; index++) {
					UIHelper.highlightElement(driver, nextPageXpath);
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);

					isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);
					if (isProfileDisplayed) {
						break;
					}
				}
			}

			String prfSlctXPathFinal = prfSlctXPath.replace("CRAFT", prfNme);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prfSlctXPathFinal);

			UIHelper.highlightElement(driver, prfSlctXPathFinal);
			UIHelper.click(driver, prfSlctXPathFinal);

			UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
			UIHelper.click(driver, nxtBtnXPath);
			UIHelper.waitForPageToLoad(driver);

			selectTargetFolderInTransformation(siteName, targetFolder);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pvtNmeTxtXPath);
			driver.findElement(By.xpath(pvtNmeTxtXPath)).sendKeys(prefTxt);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
			UIHelper.click(driver, finBtnXPath);

			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgImgXpath);
			UIHelper.waitFor(driver);

			String finalXpathForRemoveAddedTargetFolder = tempXpathForRemoveAddedTargetFolder.replace("CRAFT",
					targetFolder);

			if (UIHelper.checkForAnElementbyXpath(driver, errorNotificXpathWhenDestAndSourceSame)) {
				report.updateTestLog(
						"Choose the destination folder same as that of source folder for the transformed files using the Add button present in the screen.",
						"User able to see notification as 'Ensure Source and Destination are not same.'", Status.PASS);

				UIHelper.click(driver, errorNotificXpathWhenDestAndSourceSame);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, finalXpathForRemoveAddedTargetFolder);
				UIHelper.waitFor(driver);
			} else {
				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnPrfConXPath);
					
					if (UIHelper.checkForAnElementbyXpath(driver, okBtnPrfConXPath)) {
						report.updateTestLog("Apply Transformation for Target Folder",
								"Transformation applied succesfully" + "<br><b>Transformation Applied File/Folder Name : </b>"
										+ folderName + "<br><b>Target Folder Name : </b>" + targetFolder
										+ "<br><b>Transformation Profile Applied : </b>" + prfNme,
								Status.PASS);
						UIHelper.click(driver, okBtnPrfConXPath);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitFor(driver);
					} else {
						report.updateTestLog("Apply Transformation for Target Folder",
								"Transformation not applied" + "<br><b>Transformation Applied file/Folder Name : </b>" + folderName
										+ "<br><b>Target Folder Name : </b>" + targetFolder
										+ "<br><b>Transformation Profile Applied : </b>" + prfNme,
								Status.FAIL);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Transformation for Target Folder",
					"Apply Transformation for Target Folder Failed", Status.FAIL);
		}
	}

	// Add target folder via relative path and browse selection window
	public void addTargetFolderViaRelativeAndTargSelectionWindow(String prfNme, String folderName, String targetFolder,
			String prefTxt, String siteName) {
		boolean isProfileDisplayed = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			searchProfile(prfNme);
			isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);

			if (isProfileDisplayed == false) {
				List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);

				WebElement element = noOfPagesEle.get(noOfPagesEle.size() - 1);
				String noOfPages = element.getText();
				int lastEle = Integer.parseInt(noOfPages);
				for (int index = 0; index < lastEle; index++) {
					UIHelper.highlightElement(driver, nextPageXpath);
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);

					isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);
					if (isProfileDisplayed) {
						break;
					}
				}
			}

			String prfSlctXPathFinal = prfSlctXPath.replace("CRAFT", prfNme);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prfSlctXPathFinal);

			UIHelper.highlightElement(driver, prfSlctXPathFinal);
			UIHelper.click(driver, prfSlctXPathFinal);

			UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
			UIHelper.click(driver, nxtBtnXPath);
			UIHelper.waitForPageToLoad(driver);

			selectTargetFolderInTransformation(siteName, targetFolder);

			if (UIHelper.checkForAnElementbyXpath(driver, relativeFilePathXpathFortargetFolder)) {
				UIHelper.highlightElement(driver, relativeFilePathXpathFortargetFolder);

				UIHelper.sendKeysToAnElementByXpath(driver, relativeFilePathXpathFortargetFolder, targetFolder);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, pvtNmeTxtXPath);
			driver.findElement(By.xpath(pvtNmeTxtXPath)).sendKeys(prefTxt);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
			UIHelper.click(driver, finBtnXPath);

			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgImgXpath);
			UIHelper.waitFor(driver);

			removeAddedTargetFolderInAppliProfilePage(targetFolder);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
			UIHelper.click(driver, finBtnXPath);

			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgImgXpath);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, okBtnPrfConXPath)) {
				report.updateTestLog("Apply Transformation for Target Folder",
						"Transformation applied succesfully" + "<br><b>Transformation Applied Folder Name : </b>"
								+ folderName + "<br><b>Target Folder Name : </b>" + targetFolder
								+ "<br><b>Transformation Profile Applied : </b>" + prfNme,
						Status.PASS);
				UIHelper.click(driver, okBtnPrfConXPath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForLong(driver);
			} else {
				report.updateTestLog("Apply Transformation for Target Folder",
						"Transformation not applied" + "<br><b>Transformation Applied Folder Name : </b>" + folderName
								+ "<br><b>Target Folder Name : </b>" + targetFolder
								+ "<br><b>Transformation Profile Applied : </b>" + prfNme,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Transformation for Target Folder",
					"Apply Transformation for Target Folder Failed", Status.FAIL);
		}
	}

	// Remove added target folder in Apply profile page
	public void removeAddedTargetFolderInAppliProfilePage(String targetFolder) {
		try {
			String finalXpathForRemoveAddedTargetFolder = tempXpathForRemoveAddedTargetFolder.replace("CRAFT",
					targetFolder);

			if (UIHelper.checkForAnElementbyXpath(driver, errorNotificXpathWhenDestAndSourceSame)) {
				report.updateTestLog(
						"Choose the destination folder same as that of source folder for the transformed files using the Add button present in the screen.",
						"User able to see notification as 'Ensure Source and Destination are not same.'", Status.PASS);

				UIHelper.click(driver, errorNotificXpathWhenDestAndSourceSame);
				UIHelper.waitFor(driver);
			} else if (UIHelper.checkForAnElementbyXpath(driver,
					errorMsgXpathWhenTargetFolderAddedViaBrowseAndFieldVal)) {
				report.updateTestLog(
						"Verify if user selects same target folder through 'Select Browse' and input field: Target folder path",
						"User able to see notification as 'Select Either Browse Target Folder Or Target folder path'",
						Status.PASS);

				UIHelper.click(driver, errorMsgXpathWhenTargetFolderAddedViaBrowseAndFieldVal);
				UIHelper.waitFor(driver);
			}

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForRemoveAddedTargetFolder)) {
				UIHelper.click(driver, finalXpathForRemoveAddedTargetFolder);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectTargetFolderAndApplyTransformationProfile(String prfNme, String siteName, String destFolder,
			String targetFolder, String prefTxt) {
		try {
			UIHelper.waitFor(driver);
			selectTargetFolderInTransformation(siteName, targetFolder);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pvtNmeTxtXPath);
			driver.findElement(By.xpath(pvtNmeTxtXPath)).sendKeys(prefTxt);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
			UIHelper.click(driver, finBtnXPath);

			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, msgImgXpath);
			UIHelper.waitFor(driver);

			String finalXpathForRemoveAddedTargetFolder = tempXpathForRemoveAddedTargetFolder.replace("CRAFT",
					targetFolder);

			if (UIHelper.checkForAnElementbyXpath(driver, errorNotificXpathWhenDestAndSourceSame)) {
				report.updateTestLog(
						"Choose the destination folder same as that of source folder for the transformed files using the Add button present in the screen.",
						"User able to see notification as 'Ensure Source and Destination are not same.'", Status.PASS);

				UIHelper.click(driver, errorNotificXpathWhenDestAndSourceSame);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, finalXpathForRemoveAddedTargetFolder);
				UIHelper.waitFor(driver);
			} else {
				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnPrfConXPath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (UIHelper.checkForAnElementbyXpath(driver, okBtnPrfConXPath)) {
				report.updateTestLog("Apply Transformation for Target Folder",
						"Transformation applied succesfully" + "<br><b>Transformation Applied Folder Name : </b>"
								+ destFolder + "<br><b>Target Folder Name : </b>" + targetFolder
								+ "<br><b>Transformation Profile Applied : </b>" + prfNme,
						Status.PASS);
				UIHelper.click(driver, okBtnPrfConXPath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForLong(driver);
			} else {
				report.updateTestLog("Apply Transformation for Target Folder",
						"Transformation not applied" + "<br><b>Transformation Applied Folder Name : </b>" + destFolder
								+ "<br><b>Target Folder Name : </b>" + targetFolder
								+ "<br><b>Transformation Profile Applied : </b>" + prfNme,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply Transformation for Target Folder",
					"Apply Transformation for Target Folder Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param siteName
	 * @param targetFolder
	 */
	public void selectTargetFolderInTransformation(String siteName, String targetFolder) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addBtnXpath);
			UIHelper.highlightElement(driver, addBtnXpath);
			UIHelper.click(driver, addBtnXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, recentSitesLinkXpath);
			UIHelper.highlightElement(driver, recentSitesLinkXpath);
			UIHelper.click(driver, recentSitesLinkXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			String siteXpath = tempSiteLinkXpath.replace("CRAFT", siteName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteXpath);
			UIHelper.highlightElement(driver, siteXpath);
			UIHelper.click(driver, siteXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, docLibLinkXpath);
			UIHelper.highlightElement(driver, docLibLinkXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, rootNodeXpath);
			UIHelper.highlightElement(driver, rootNodeXpath);
			UIHelper.click(driver, rootNodeXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			String targetFolderXpath = tempSiteLinkXpath.replace("CRAFT", targetFolder);
			UIHelper.waitForVisibilityOfEleByXpath(driver, targetFolderXpath);
			UIHelper.highlightElement(driver, targetFolderXpath);
			UIHelper.click(driver, targetFolderXpath);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnXapth);
			UIHelper.highlightElement(driver, okBtnXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, okBtnXapth))) {
				report.updateTestLog("To select Target folder",
						"Folder selected succesfully" + "<br><b>Target Folder Name : </b>" + targetFolder, Status.PASS);
			} else {
				report.updateTestLog("To select Target folder",
						"Folder not selected" + "<br><b>Target Folder Name : </b>" + targetFolder, Status.FAIL);
			}
			UIHelper.click(driver, okBtnXapth);

			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("To select Target folder", "To select Target folder Failed", Status.FAIL);
		}
	}

	// To select and apply transform rule
	// Navigate to Media transform page
	public void selectProfileFrmListAndApplyTransformation(String prfNme, String prefTxt) {
		boolean isProfileDisplayed = false;
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, applyTrasfromloadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);
			UIHelper.waitForPageToLoad(driver);

			searchProfile(prfNme);
			isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);

			if (isProfileDisplayed == false) {
				List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);

				WebElement element = noOfPagesEle.get(noOfPagesEle.size() - 1);
				String noOfPages = element.getText();
				int lastEle = Integer.parseInt(noOfPages);
				for (int index = 0; index < lastEle; index++) {
					UIHelper.highlightElement(driver, nextPageXpath);
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);

					isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);
					if (isProfileDisplayed) {
						break;
					}
				}
			}

			String prfSlctXPathFinal = prfSlctXPath.replace("CRAFT", prfNme);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prfSlctXPathFinal);
			UIHelper.highlightElement(driver, prfSlctXPathFinal);
			UIHelper.click(driver, prfSlctXPathFinal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
			UIHelper.click(driver, nxtBtnXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pvtNmeTxtXPath);

			driver.findElement(By.xpath(pvtNmeTxtXPath)).sendKeys(prefTxt);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
			UIHelper.click(driver, finBtnXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnPrfConXPath);
			report.updateTestLog("Select profile and Apply transformation:" + prfNme,
					"Profile Selected and transformation applied successfully.", Status.PASS);

			UIHelper.click(driver, okBtnPrfConXPath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForLong(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select profile Apply transformation" + prfNme, "Failed to select profile.",
					Status.FAIL);
		}
	}

	// Apply rendition profile
	public void applyRenditionProfile(String profileName) {
		boolean isProfileDisplayed = false;
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, applyTrasfromloadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);
			UIHelper.waitForPageToLoad(driver);

			searchProfile(profileName);
			isProfileDisplayed = isProfileDisplayedInSelectWindow(profileName);

			if (isProfileDisplayed == false) {
				List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);

				WebElement element = noOfPagesEle.get(noOfPagesEle.size() - 1);
				String noOfPages = element.getText();
				int lastEle = Integer.parseInt(noOfPages);
				for (int index = 0; index < lastEle; index++) {
					UIHelper.highlightElement(driver, nextPageXpath);
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);

					isProfileDisplayed = isProfileDisplayedInSelectWindow(profileName);
					if (isProfileDisplayed) {
						break;
					}
				}
			}

			String prfSlctXPathFinal = prfSlctXPath.replace("CRAFT", profileName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prfSlctXPathFinal);
			UIHelper.highlightElement(driver, prfSlctXPathFinal);
			UIHelper.click(driver, prfSlctXPathFinal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
			UIHelper.click(driver, nxtBtnXPath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, renditionPromptXapth);
			if (UIHelper.checkForAnElementbyXpath(driver, renditionOkBtnXpath)) {
				UIHelper.highlightElement(driver, renditionOkBtnXpath);
				report.updateTestLog("Apply the Rendition profile",
						"Profile applied successfully" + "<br><b>Profile Name : </b>" + profileName, Status.PASS);
				UIHelper.click(driver, renditionOkBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
			} else {
				report.updateTestLog("Apply the Rendition profile",
						"Profile not applied" + "<br><b>Profile Name : </b>" + profileName, Status.FAIL);
			}

			UIHelper.waitForPageToLoad(driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply the Rendition profile", "Apply the Rendition profile Failed", Status.FAIL);
		}
	}

	// Apply rendition profile
	public void selectAndApplyRenditionProfile(String profileName) {
		boolean isProfileDisplayed = false;
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, applyTrasfromloadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);
			UIHelper.waitForPageToLoad(driver);

			searchProfile(profileName);
			isProfileDisplayed = isRendProfileDisplayedInSelectWindow(profileName);

			if (isProfileDisplayed == false) {
				List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);

				WebElement element = noOfPagesEle.get(noOfPagesEle.size() - 1);
				String noOfPages = element.getText();
				int lastEle = Integer.parseInt(noOfPages);
				for (int index = 0; index < lastEle; index++) {
					UIHelper.highlightElement(driver, nextPageXpath);
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);

					isProfileDisplayed = isRendProfileDisplayedInSelectWindow(profileName);
					if (isProfileDisplayed) {
						break;
					}
				}
			}

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prfSelectXpathForRendProf);
			UIHelper.highlightElement(driver, prfSelectXpathForRendProf);
			UIHelper.click(driver, prfSelectXpathForRendProf);
			UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
			UIHelper.click(driver, nxtBtnXPath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, renditionPromptXapth);
			if (UIHelper.checkForAnElementbyXpath(driver, renditionOkBtnXpath)) {
				UIHelper.highlightElement(driver, renditionOkBtnXpath);
				report.updateTestLog("Apply the Rendition profile",
						"Profile applied successfully" + "<br><b>Profile Name : </b>" + profileName, Status.PASS);
				UIHelper.click(driver, renditionOkBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
			} else {
				report.updateTestLog("Apply the Rendition profile",
						"Profile not applied" + "<br><b>Profile Name : </b>" + profileName, Status.FAIL);
			}

			UIHelper.waitForPageToLoad(driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Apply the Rendition profile", "Apply the Rendition profile Failed", Status.FAIL);
		}
	}

	// To verify transferered file

	public boolean isTransferredFileIsAvailable(String fileName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (ele.getText().contains(fileName)) {
					UIHelper.highlightElement(driver, ele);
					flag = true;
					break;

				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify tranferred  file is available", "Check file is available Failed", Status.FAIL);
		}
		return flag;
	}

	// To select file based radio

	public void selectFileNmeBaseRadio(String fleNmeBas) {

		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		if (fleNmeBas.equalsIgnoreCase("Yes")) {
			// UIHelper.waitForVisibilityOfEleByXpath(driver, filNmeYesXPath);
			// driver.findElement(By.xpath(filNmeYesXPath)).sendKeys(Keys.ENTER);
			UIHelper.click(driver, filNmeYesXPath);
			report.updateTestLog("Select Based on file Name option as per the input",
					" Based on file Name option selected successfully", Status.PASS);

		} else {

			UIHelper.waitForVisibilityOfEleByXpath(driver, filNmeNoXPath);
			// driver.findElement(By.xpath(filNmeNoXPath)).sendKeys(Keys.ENTER);
			UIHelper.click(driver, filNmeNoXPath);
			report.updateTestLog("Select Based on file Name option as per the input",
					" Based on file Name option NOT selected successfully", Status.PASS);
		}
	}

	// Toselect Watch folder
	public void watchFldrRadio(String wtchFldr) {

		if (wtchFldr.equalsIgnoreCase("Yes")) {
			UIHelper.waitForVisibilityOfEleByXpath(driver, watFldrYesXPath);
			// driver.findElement(By.xpath(watFldrYesXPath)).sendKeys(Keys.ENTER);
			UIHelper.click(driver, watFldrYesXPath);
			report.updateTestLog("Select watch folder option as per the input",
					"Watch Folder option selected successfully", Status.PASS);

		} else {

			UIHelper.waitForVisibilityOfEleByXpath(driver, watFldrNoXPath);
			// driver.findElement(By.xpath(watFldrNoXPath)).sendKeys(Keys.ENTER);
			UIHelper.click(driver, watFldrNoXPath);
			report.updateTestLog("Select watch folder option as per the input",
					"Watch Folder option selected successfully", Status.PASS);
		}

	}

	// delete profile from media transform page
	public void deleteProfileFrmMediaTransPg(String profName) {
		try {

			// navigateToProfilePage(profName);

			isProfileDisplatedInNavigatedPages(profName);

			String finalSelectValOptionXpath = selectValOptionXpath.replace("CRAFT", profName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalSelectValOptionXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalSelectValOptionXpath));
				UIHelper.highlightElement(driver, finalSelectValOptionXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalSelectValOptionXpath);
				UIHelper.click(driver, finalSelectValOptionXpath);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, deleteOptionXPath);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, deleteXPath);
				} catch (Exception e) {
				}

				UIHelper.click(driver, deleteXPath);
				UIHelper.waitFor(driver);

				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, okXpath);
				} catch (Exception e) {
				}

				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, okXpath);
				UIHelper.click(driver, okXpath);
				/*if (UIHelper.checkForAnElementbyXpath(driver, deleteConfirmOkBtnXpath)) {
					report.updateTestLog("Delete existing media Profile",
							"Media profile deleted successfully." + "<br><b>Profile Name : </br>" + profName,
							Status.PASS);
					UIHelper.click(driver, deleteConfirmOkBtnXpath);
				} else {
					UIHelper.click(driver, okXpath);

					try {
						UIHelper.waitForVisibilityOfEleByXpath(driver, deleteConfirmOkBtnXpath);
					} catch (Exception e) {
					}

					if (UIHelper.checkForAnElementbyXpath(driver, deleteConfirmOkBtnXpath)) {
						report.updateTestLog("Delete existing media Profile",
								"Media profile deleted successfully." + "<br><b>Profile Name : </br>" + profName,
								Status.PASS);
						UIHelper.click(driver, deleteConfirmOkBtnXpath);
					} else {
						report.updateTestLog("Delete existing media Profile",
								"Media profile failed to delete" + "<br><b>Profile Name : </br>" + profName,
								Status.FAIL);
					}
				}*/
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, okXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Check Profile in Media Transformation Profile Page
	public boolean checkProfileNameInMeadiTransProfilePage(String profName) {
		boolean isDisplayedProfileInMTP = false;
		try {
			driver.navigate().refresh();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
			UIHelper.waitFor(driver);
			navigateToProfilePage(profName);
			String finalSelectValOptionXpath = selectValOptionXpath.replace("CRAFT", profName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalSelectValOptionXpath)) {
				isDisplayedProfileInMTP = true;
			} else {
				isDisplayedProfileInMTP = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedProfileInMTP;
	}

	// Navigate to Media transform page
	public void selectProfileFrmListAndApplyTransformationWithDestFldr(String prfNme, String prefTxt) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			String prfSlctXPathFinal = prfSlctXPath.replace("CRAFT", prfNme);
			System.out.println("" + prfSlctXPathFinal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prfSlctXPathFinal);
			UIHelper.highlightElement(driver, prfSlctXPathFinal);
			UIHelper.click(driver, prfSlctXPathFinal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
			UIHelper.click(driver, nxtBtnXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, addBtnDestXPath);
			UIHelper.click(driver, addBtnDestXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pvtNmeTxtXPath);
			driver.findElement(By.xpath(pvtNmeTxtXPath)).sendKeys(prefTxt);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
			UIHelper.click(driver, finBtnXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnPrfConXPath);
			report.updateTestLog("Select a prfile by profile Name and Apply transformation" + prfNme,
					"Profile Selected and transformation applied successfully.", Status.PASS);
			UIHelper.click(driver, okBtnPrfConXPath);

			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select a prfile by profile Name and Apply transformation" + prfNme,
					"Profile NOT Selected and transformation applied successfully.", Status.FAIL);
		}
	}

	public void uploadImageInCreateProfile(String filePath, String fileName) {
		try {
			String tempfileName[] = fileName.split("-");
			String finalfileName = tempfileName[0];
			System.out.println(finalfileName);
			String finalXpathForUploadedFile = uploadImageInCreateProfileXpath.replace("CRAFT", finalfileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadImageBtnXpath);
			UIHelper.click(driver, uploadImageBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, selctFilesToUploadBtnXpath);
			WebElement uploadInputFieldEle = driver.findElement(By.xpath(selctFilesToUploadBtnXpath));
			UIHelper.highlightElement(driver, uploadInputFieldEle);
			String finalFilePath = System.getProperty("user.dir") + filePath;
			WebElement fileInput = driver.findElement(By.xpath(uploadInputField));
			fileInput.sendKeys(finalFilePath + fileName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForUploadedFile);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Upload Image", "Image Uploaded sucessfully in Create Profile:" + fileName,
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Upload Image", "Image Failed to upload in Create Profile:" + fileName, Status.FAIL);
		}

	}

	// Select transform type
	public void selectImgFileFormatType(String type) {
		try {
			String finalValXpath = formatTypeXpath.replace("CRAFT", type);
			UIHelper.waitForVisibilityOfEleByXpath(driver, imgProfLabelXpath);
			UIHelper.click(driver, imgProfLabelXpath);
			UIHelper.click(driver, imgTypeinputXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalValXpath);
			UIHelper.click(driver, finalValXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, fileFormatAddBtnXpath);
			UIHelper.click(driver, fileFormatAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Select Output Format", "Output Transform type selected successfully:" + type,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Output Format", "Failed to select transform type", Status.FAIL);

		}
	}

	// To Get Notification Message after clicking Save
	public void getSaveNotificationMessage() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, saveImgProfXpath);
			UIHelper.click(driver, saveImgProfXpath);

			report.updateTestLog("Click on [SAVE] button", "[SAVE] button clicked successfully", Status.DONE);

			UIHelper.waitForVisibilityOfEleByXpath(driver, oKXpath);
			String message = UIHelper.getTextFromWebElement(driver, saveNotificationMessage);

			System.out.println(message);
			if (message.equalsIgnoreCase("Profile updated successfully")) {
				report.updateTestLog("Save Notification message", "Message displayed as per expected:" + message,
						Status.PASS);
			} else {
				report.updateTestLog("Save Notification message", "Message is not displayed as per expected:" + message,
						Status.FAIL);
				UIHelper.waitFor(driver);
			}
			UIHelper.click(driver, oKXpath);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Save Notification message", "Message is not displayed as per expected:", Status.FAIL);
		}

	}

	// Select transform type
	public void convertToGrayScale() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, grayScalelinkXpath);
			UIHelper.click(driver, grayScalelinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, greyScaleCheckBoxXpath);
			if (UIHelper.findAnElementbyXpath(driver, greyScaleCheckBoxXpath).isSelected()) {
				//
			} else {
				UIHelper.click(driver, greyScaleCheckBoxXpath);
			}
			UIHelper.waitForVisibilityOfEleByXpath(driver, grayScaleAddBtnXpath);
			UIHelper.click(driver, grayScaleAddBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, grayScaleProfileSummaryXpath);
			report.updateTestLog("Select and Add GrayScale", "Grayscale selected and Added successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Grayscale", "Grayscale selection failed", Status.FAIL);

		}
	}

	// Click on Cancel Image profile button
	public boolean clickOnCancelImageProfBtn() {
		boolean flag;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelImageProfBtnXpath);
			UIHelper.highlightElement(driver, cancelImageProfBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, cancelImageProfBtnXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelConfirmXpath);
			UIHelper.highlightElement(driver, cancelConfirmXpath);
			UIHelper.click(driver, cancelConfirmXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createImageBtnXpath);

			report.updateTestLog("Click on cancel Image profile button",
					"Cancel Image profile button clicked successfully", Status.PASS);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on cancel Image profile button", "Failed to click Cancel Image profile button",
					Status.FAIL);
			flag = false;
		}
		return flag;

	}

	// check profile available
	public boolean checkMediaProfAvail(String profName) {
		boolean flag = false;
		try {

			 navigateToProfilePage(profName);

			flag = isProfileDisplatedInNavigatedPages(profName);

			/*
			 * UIHelper.waitForPageToLoad(driver); String
			 * finalSelectValOptionXpath = selectValOptionXpath.replace("CRAFT",
			 * profName);
			 * 
			 * try { UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * finalSelectValOptionXpath); } catch (Exception e) { }
			 * 
			 * if (UIHelper.checkForAnElementbyXpath(driver,
			 * finalSelectValOptionXpath)) { flag = true; } else { flag = false;
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();

		}
		return flag;

	}

	/**
	 * @author 412766
	 */
	public void checkForMsgInResizePane() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, resizeLabelXpath);
			UIHelper.click(driver, resizeLabelXpath);

			UIHelper.waitFor(driver);
			UIHelper.click(driver, percentChkBoxXpath);
			UIHelper.click(driver, resizeAddBtnXpath);
			UIHelper.waitFor(driver);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, resizeMsgXapth))) {
				UIHelper.highlightElement(driver, resizeMsgXapth);
				report.updateTestLog("Check Message in Resize pane", "Message displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Check Message in Resize pane", "Message not displayed", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check Message in Resize pane", "Check Message in Resize pane Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param value
	 */
	public void enterAlphabetsInPercentage(String value) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, resizeLabelXpath);
			UIHelper.click(driver, resizeLabelXpath);

			UIHelper.waitFor(driver);
			UIHelper.click(driver, percentChkBoxXpath);
			UIHelper.click(driver, resizeChkBoxXpath);
			UIHelper.highlightElement(driver, percentValXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, percentValXpath, value);

			UIHelper.waitFor(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, percentResizeMsgXpath))) {
				UIHelper.highlightElement(driver, percentResizeMsgXpath);
				report.updateTestLog("Check Message display above the Percentage textbox",
						"Message displayed successfully", Status.PASS);
				if(UIHelper.findAnElementbyXpath(driver, percentResizeMsgXpath).getText().equals("Enter the numbers or numbers with two decimal values"))			
				{
					report.updateTestLog("Validate Message displayed above the Percentage textbox",
							"Message validated successfully", Status.PASS);
				}
				else {
					report.updateTestLog("Validate Message display above the Percentage textbox", "Message is not validated",
							Status.FAIL);
				}
			} else {
				report.updateTestLog("Check Message display above the Percentage textbox", "Message not displayed",
						Status.FAIL);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check Message display above the Percentage textbox",
					"Check Message display above the Percentage textbox Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param value
	 */
	public void enterAlphabetsInAbsoluteSize(String value) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, resizeLabelXpath);
			UIHelper.click(driver, resizeLabelXpath);

			UIHelper.waitFor(driver);

			UIHelper.highlightElement(driver, heightValXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, heightValXpath, value);
			UIHelper.highlightElement(driver, widthValueXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, widthValueXpath, value);

			UIHelper.waitFor(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, hightResizeMsgXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, widthResizeMsgXpath))) {
				UIHelper.highlightElement(driver, hightResizeMsgXpath);
				UIHelper.highlightElement(driver, widthResizeMsgXpath);
				report.updateTestLog("Check Message display above the Hight and Width textbox",
						"Message displayed successfully", Status.PASS);
				if(UIHelper.findAnElementbyXpath(driver, hightResizeMsgXpath).getText().equals("Enter the numbers or numbers with two decimal values")
						&&
						(UIHelper.findAnElementbyXpath(driver, widthResizeMsgXpath).getText().equals("Enter the numbers or numbers with two decimal values")))
				{
					report.updateTestLog("Validate Message displayed above the Hight and Width textbox",
							"Message validated successfully", Status.PASS);
				}
				else {
					report.updateTestLog("Validate Message display above the Hight and Width textbox", "Message is not validated",
							Status.FAIL);
				}
			}
			 else {
				report.updateTestLog("Check Message display above the Hight and Width textbox", "Message not displayed",
						Status.FAIL);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check Message display above the Hight and Width textbox",
					"Check Message display above the Hight and Width textbox Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isPreviewBtnDisabled() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, previewBtnXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, previewBtnXpath));
			if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, previewBtnXpath))) {
				flag = true;
				System.out.println("FLAG : " + flag);
			} else {
				flag = false;
				System.out.println("FLAG : " + flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Add resize value
	public void addImgResizeByPercent(String percent) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, resizeLabelXpath);
			UIHelper.click(driver, resizeLabelXpath);

			UIHelper.waitFor(driver);
			UIHelper.click(driver, percentChkBoxXpath);
			UIHelper.click(driver, resizeChkBoxXpath);
			UIHelper.highlightElement(driver, percentValXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, percentValXpath, percent);
			UIHelper.waitForVisibilityOfEleByXpath(driver, resizeAddBtnXpath);
			UIHelper.click(driver, resizeAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Resize percentage details", "Percentage value added successfully:" + percent,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Add Resize percentage details", "Failed to Add resize value", Status.FAIL);

		}
	}

	// Add resize value
	public void addImgResizeBySize(String height, String width) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, resizeLabelXpath);
			UIHelper.click(driver, resizeLabelXpath);

			UIHelper.waitFor(driver);

			UIHelper.highlightElement(driver, heightValXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, heightValXpath, height);
			UIHelper.highlightElement(driver, widthValueXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, widthValueXpath, height);

			UIHelper.waitForVisibilityOfEleByXpath(driver, resizeAddBtnXpath);
			UIHelper.click(driver, resizeAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Resize details", "Percentage value added successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Add Resize details", "Failed to Add resize value", Status.FAIL);

		}
	}

	// Get Profile Summary Details
	public String getTextFromProfileSummary(String profileFieldName) {
		String profileSummaryVal = "";
		try {
			String finalXpathForProfileSummaryFieldVal = tempXpathForProfileSummaryFieldVal.replace("CRAFT",
					profileFieldName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpathForProfileSummaryFieldVal);

			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, finalXpathForProfileSummaryFieldVal));
			profileSummaryVal = UIHelper.getTextFromWebElement(driver, finalXpathForProfileSummaryFieldVal);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return profileSummaryVal;
	}

	// Get Profile Summary Details
	public ArrayList<String> getListFromProfileSummary(String profileFieldName) {
		ArrayList<String> profileSummaryVal = new ArrayList<String>();
		try {
			String finalXpathForProfileSummaryFieldVal = tempXpathForProfileSummaryFieldVal.replace("CRAFT",
					profileFieldName);

			UIHelper.waitFor(driver);

			List<WebElement> profileSummaryListEle = UIHelper
					.findListOfElementsbyXpath(finalXpathForProfileSummaryFieldVal, driver);

			for (WebElement ele : profileSummaryListEle) {
				UIHelper.highlightElement(driver, ele);
				profileSummaryVal.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return profileSummaryVal;
	}

	// Check Profile Summary Field Value
	public boolean checkProfileSummaryFieldVal(String profileFieldName) {
		boolean isDisplayedProfileSummaryFieldValue = false;
		try {
			String finalXpathForProfileSummaryFieldVal = tempXpathForProfileSummaryFieldVal.replace("CRAFT",
					profileFieldName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForProfileSummaryFieldVal)) {
				isDisplayedProfileSummaryFieldValue = true;
			} else {
				isDisplayedProfileSummaryFieldValue = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedProfileSummaryFieldValue;
	}

	// Click on Remove Btn in Resize Container
	public void clickOnRemoveBtnInResizeContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, resizeRemoveBtnXpath);
			UIHelper.click(driver, resizeRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add rotate value
	public void addRotateAngleForImg(String rotateAngleVal) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, rotateLabelXpath);
			UIHelper.click(driver, rotateLabelXpath);

			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, enterAngleFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, enterAngleFieldXpath, rotateAngleVal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, rotateAddBtnXpath);
			UIHelper.click(driver, rotateAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Rotate details", "Rotate Angle value added successfully:" + rotateAngleVal,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Remove Btn in Rotate Container
	public void clickOnRemoveBtnInRotateContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, rotateRemoveBtnXpath);
			UIHelper.click(driver, rotateRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add crop details
	public void addImgCropDetails(String type, String height, String width) {
		try {

			String finalValXpath = cropTypeXpath.replace("CRAFT", type);
			UIHelper.waitForVisibilityOfEleByXpath(driver, cropLabelXpath);
			UIHelper.click(driver, cropLabelXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, cropInputXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalValXpath);
			UIHelper.click(driver, finalValXpath);

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * aspectRatioChkBoxXpath); UIHelper.click(driver,
			 * aspectRatioChkBoxXpath);
			 */
			UIHelper.highlightElement(driver, cropHeightXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, cropHeightXpath, height);
			UIHelper.highlightElement(driver, cropWidthXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, cropWidthXpath, width);

			UIHelper.waitForVisibilityOfEleByXpath(driver, cropAddBtnXpath);
			UIHelper.click(driver, cropAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add crop details", "Crop details added successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Add crop details", "Failed to Add crop details", Status.FAIL);

		}
	}

	// Click on Remove Button in Crop Container
	public void clickOnRemoveBtnInCropContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cropRemoveBtnXpath);
			UIHelper.click(driver, cropRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Color value
	public void addColorForImg(String colorOption) {
		try {

			String finalXpathForColorOption = tempXpathForColorOption.replace("CRAFT", colorOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, colorLabelXpath);
			UIHelper.click(driver, colorLabelXpath);

			UIHelper.waitFor(driver);

			UIHelper.click(driver, finalXpathForColorOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, colorAddBtnXpath);
			UIHelper.click(driver, colorAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Color details", "Color Otion added successfully:" + colorOption, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Remove Btn in Color Container
	public void clickOnRemoveBtnInColorContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, colorRemoveBtnXpath);
			UIHelper.click(driver, colorRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Color Mode value
	public void addColorModeForImg(String colorModeOption) {
		try {

			String finalXpathForColorModeOption = tempXpathForColorModeOption.replace("CRAFT", colorModeOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, colorModeLabelXpath);
			UIHelper.click(driver, colorModeLabelXpath);

			UIHelper.waitFor(driver);

			UIHelper.click(driver, finalXpathForColorModeOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, colorModeAddBtnXpath);
			UIHelper.click(driver, colorModeAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Color Mode details", "Color Mode added successfully:" + colorModeOption,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Remove Btn in Grayscale Container
	public void clickOnRemoveBtnInColorModeContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, colorModeRemoveBtnXpath);
			UIHelper.click(driver, colorModeRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Grayscale value
	public void addGrayscaleForImg() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, grayscaleLabelXpath);
			UIHelper.click(driver, grayscaleLabelXpath);

			UIHelper.waitFor(driver);

			UIHelper.click(driver, grayscaleCheckboxOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, grayscaleAddBtnXpath);
			UIHelper.click(driver, grayscaleAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Grayscale details", "Convert to Grayscale added successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Remove Btn in Grayscale Container
	public void clickOnRemoveBtnInGrayscaleContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, grayscaleRemoveBtnXpath);
			UIHelper.click(driver, grayscaleRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Watermark value
	public void addWaterMarkForImg(String waterMarkOption) {
		try {
			String finalXpathWaterMarkCheckboxOption = tempXpathWaterMarkCheckboxOption.replace("CRAFT",
					waterMarkOption);
			UIHelper.waitForVisibilityOfEleByXpath(driver, waterMarkLabelXpath);
			UIHelper.click(driver, waterMarkLabelXpath);

			UIHelper.waitFor(driver);

			UIHelper.click(driver, finalXpathWaterMarkCheckboxOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, waterMarkAddBtnXpath);
			UIHelper.click(driver, waterMarkAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add WaterMark details", "WaterMark option added successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Watermark value
	public void addWaterMarkForImg() {
		try {
			String finalXpathWaterMarkCheckboxOption = tempXpathWaterMarkCheckboxOption.replace("CRAFT",
					"Default");
			UIHelper.waitForVisibilityOfEleByXpath(driver, waterMarkLabelXpath);
			UIHelper.click(driver, waterMarkLabelXpath);

			UIHelper.waitFor(driver);

			UIHelper.click(driver, finalXpathWaterMarkCheckboxOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, waterMarkAddBtnXpath);
			UIHelper.click(driver, waterMarkAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add WaterMark details", "WaterMark option added successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Remove Btn in Watermark Container
	public void clickOnRemoveBtnInWaterMarkContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, waterMarkRemoveBtnXpath);
			UIHelper.click(driver, waterMarkRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//
	// Add Flip value
	public void addFlipForImg(String flipOption) {
		try {

			String finalXpathForFlipOption = tempXpathForflipOption.replace("CRAFT", flipOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, flipLabelXpath);
			UIHelper.click(driver, flipLabelXpath);

			UIHelper.waitFor(driver);

			UIHelper.click(driver, finalXpathForFlipOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, flipContainerAddBtnXpath);
			UIHelper.click(driver, flipContainerAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Flip details", "Flip option added successfully:" + flipOption, Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Remove Btn in Flip Container
	public void clickOnRemoveBtnInFlipContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, flipRemoveBtnXpath);
			UIHelper.click(driver, flipRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Compression value
	public void addCompressionForImg() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, compressionLabelXpath);
			UIHelper.click(driver, compressionLabelXpath);

			UIHelper.waitFor(driver);

			UIHelper.click(driver, compressionCheckboxOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, compressionAddBtnXpath);
			UIHelper.click(driver, compressionAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Compression details", "Compression option added successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Remove Btn in Watermark Container
	public void clickOnRemoveBtnInCompressionContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, compressionRemoveBtnXpath);
			UIHelper.click(driver, compressionRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Resolution value
	public void addResoultionForImg(String resolutionVal) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, resolutionLabelXpath);
			UIHelper.click(driver, resolutionLabelXpath);

			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, enterResolutionFieldXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, enterResolutionFieldXpath, resolutionVal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, resolutionAddBtnXpath);
			UIHelper.click(driver, resolutionAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add Resolution details", "Resolution value added successfully:" + resolutionVal,
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Remove Btn in Resolution Container
	public void clickOnRemoveBtnInResoultionContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, resolutionRemoveBtnXpath);
			UIHelper.click(driver, resolutionRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyProfileName(String prfNme) {
		try {

			isProfileDisplatedInNavigatedPages(prfNme);

			String prfSlctXPathFinal = transformationProfileNameXpath.replace("CRAFT", prfNme);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prfSlctXPathFinal);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, prfSlctXPathFinal));
			UIHelper.highlightElement(driver, prfSlctXPathFinal);
			report.updateTestLog("Verify ProfileName" + prfNme, "Profile displayed successfully.", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify ProfileName" + prfNme, "Profile is not displayed", Status.FAIL);
		}
	}

	// To Select a Profile and Apply Profile without entering any mandatory
	// fields
	public void selectProfileAndApplyProfileWithoutPrefText(String prfNme, String prefTxt) {
		boolean isProfileDisplayed = false;
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, applyTrasfromloadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver, commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);
			UIHelper.waitForPageToLoad(driver);
			searchProfile(prfNme);
			isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);

			if (isProfileDisplayed == false) {
				List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);

				WebElement element = noOfPagesEle.get(noOfPagesEle.size() - 1);
				String noOfPages = element.getText();
				int lastEle = Integer.parseInt(noOfPages);
				System.out.println(lastEle);
				for (int index = 0; index < lastEle; index++) {
					UIHelper.highlightElement(driver, nextPageXpath);
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);

					isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);
					if (isProfileDisplayed) {
						break;
					}
				}
			}
			String prfSlctXPathFinal = prfSlctXPath.replace("CRAFT", prfNme);
			System.out.println("" + prfSlctXPathFinal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prfSlctXPathFinal);
			UIHelper.highlightElement(driver, prfSlctXPathFinal);
			UIHelper.click(driver, prfSlctXPathFinal);
			UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
			UIHelper.click(driver, nxtBtnXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pvtNmeTxtXPath);
			UIHelper.click(driver, finBtnXPath);
			String notificationMessage = UIHelper.getTextFromWebElement(driver, applyProfileNotificationBodyXpath);
			String expectedMessage = "Select Either 'Browse Folder' or 'Preference text'";
			if (notificationMessage.equalsIgnoreCase(expectedMessage)) {
				report.updateTestLog("Apply Profile",
						"Apply profile without preference text and Destination location<br>"
								+ "<b>Expected Message:</b>" + expectedMessage + "<b>Actual Message:</b>"
								+ notificationMessage,
						Status.PASS);
			} else {
				report.updateTestLog("Apply Profile",
						"Apply profile without preference text and Destination location<br>"
								+ "<b>Expected Message:</b>" + expectedMessage + "<b>Actual Message:</b>"
								+ notificationMessage,
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To verify transformed file

	public boolean isTransformedFileIsAvailable(String fileNamepreText) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			System.out.println("" + searchResultsListEle.size());
			for (WebElement ele : searchResultsListEle) {
				if (ele.getText().contains(fileNamepreText)) {
					String actualfilename = ele.getText();
					report.updateTestLog("Verify Transformed File ", "Transformed File is available: " + actualfilename,
							Status.PASS);

					flag = true;
					break;

				} else {
					report.updateTestLog("Verify Transformed File ", "Transformed File is not available", Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify transformed  file is available", "File failed to Transform", Status.FAIL);
		}
		return flag;
	}

	// To Flip Canvas Vertically
	public void flipCanvasVertically() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, flipFormatXpath);
			UIHelper.click(driver, flipFormatXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, flipVerticallyXpath);
			UIHelper.click(driver, flipVerticallyXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, flipAddBtnXpath);
			report.updateTestLog("Select Flip Vertically", "Selected Flip Vertically sucessfully", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Select Flip Vertically", "Selected Flip Vertically failed", Status.FAIL);
		}
	}

	// Add CreditLine value
	public void addCreditLineForImg(String creditLineOption) {
		try {
			String finalXpathForCreditLineOption = tempXpathForCreditLineOption.replace("CRAFT", creditLineOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, creditLinelinkXpath);
			UIHelper.click(driver, creditLinelinkXpath);

			UIHelper.waitFor(driver);

			UIHelper.click(driver, finalXpathForCreditLineOption);

			UIHelper.waitForVisibilityOfEleByXpath(driver, creditLineAddBtnXpath);
			UIHelper.click(driver, creditLineAddBtnXpath);
			UIHelper.waitFor(driver);

			report.updateTestLog("Add CreditLine details", "CreditLine details added successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Remove Btn in CreditLine Container
	public void clickOnRemoveBtnInCreditLineContainer() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, creditLineRemoveBtnXpath);
			UIHelper.click(driver, creditLineRemoveBtnXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Preview Button
	public void clickOnPreviewButton() {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, previewBtnXpath);
			UIHelper.click(driver, previewBtnXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, preparingPreviewXpath);
			report.updateTestLog("Click on Preview ", "Preview button clicked sucessfully", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Click on Preview", "Failed to click preview button", Status.FAIL);
		}

	}

	// Check Image Preview
	public boolean checkImagePerview() {
		boolean isDisplayedImagePreview = false;
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, preparingPreviewXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, previewedImageXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, previewedImageXpath)) {
				isDisplayedImagePreview = true;
			} else {
				isDisplayedImagePreview = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedImagePreview;
	}

	// Check Preview Image
	public void verifyPreviewImageDisplayed() {

		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, preparingPreviewXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, previewedImageXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, previewedImageXpath)) {
				report.updateTestLog("Verify Previewer Image ", "Image previewed sucessfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Previewer Image ", "Image preview Failed", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Preview Image ", "Image preview Failed", Status.FAIL);
		}

	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isNavigatedToDocumentLibrary() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.findAnElementbyXpath(driver, docLibBarXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, docLibBarXapth))) {
				UIHelper.highlightElement(driver, docLibBarXapth);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// notification message when preference text and target folder empty
	public void verifyNotifcationWithoutPrefTextAndFolder(String Message) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
			UIHelper.waitForVisibilityOfEleByXpath(driver, firstProfXpath);
			UIHelper.click(driver, firstProfXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, nxtBtnXPath));
			UIHelper.click(driver, nxtBtnXPath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
			UIHelper.click(driver, finBtnXPath);
			UIHelper.waitFor(driver);
			if (UIHelper.getTextFromWebElement(driver, notificationXpath).contains(Message)) {

				UIHelper.highlightElement(driver, notificationXpath);
				report.updateTestLog("Verify notification message", "Notification message displayed successfully.",
						Status.PASS);
			} else {
				report.updateTestLog("Verify notification message", "Notification message is not displayed.",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify notification message", "Failed to display Notification message.", Status.FAIL);
		}
	}

	public void verifyNotifcationWithoutImgTransformType(String Message) {
		try {
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver, saveImgProfXpath);
			 */
			UIHelper.click(driver, saveImgProfXpath);
			UIHelper.waitFor(driver);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, okXpath);
			} catch (Exception e) {
			}

			if (UIHelper.getTextFromWebElement(driver, notificationFileTypeXpath).contains(Message)) {

				UIHelper.highlightElement(driver, notificationFileTypeXpath);
				report.updateTestLog("Verify notification message", "Notification message displayed successfully.",
						Status.PASS);
			} else {
				report.updateTestLog("Verify notification message", "Notification message is not displayed.",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify notification message", "Failed to display Notification message.", Status.FAIL);
		}
	}

	// Select transform type
	public void convertTocompress() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, compressXpath);
			UIHelper.click(driver, compressXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, compressCheckBoxXpath);
			UIHelper.click(driver, compressCheckBoxXpath);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// grayScaleAddBtnXpath);
			UIHelper.click(driver, compressAddBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, compressProfileSummaryXpath);
			report.updateTestLog("Select and Add Compress", "Compression selected and Added successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Compress", "Compression selection failed", Status.FAIL);

		}
	}

	// click on cancel button
	public void clickOnCancelBtn() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, cancelBtnXpath);
			UIHelper.highlightElement(driver, cancelBtnXpath);
			UIHelper.click(driver, cancelBtnXpath);
			report.updateTestLog("Click on cancel button in media transform page", "Cancel button clicked successfully",
					Status.DONE);
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitle);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, commonPageTitle)) {
				UIHelper.highlightElement(driver, commonPageTitle);
				report.updateTestLog("Verify user dashboard page", "User dashboard page successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify user dashboard page", "User dashboard page successfully", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// Select color mode
	public void selectColorMode(String colorMode) {
		try {
			String finalValXpath = selectColorModeXpath.replace("CRAFT", colorMode);
			UIHelper.waitForVisibilityOfEleByXpath(driver, colorModeLabelXpath);
			UIHelper.click(driver, colorModeLabelXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalValXpath);
			UIHelper.click(driver, finalValXpath);
			UIHelper.click(driver, colorModeAddBtnXpath);
			report.updateTestLog("Add Color Mode", "Color Mode Added successfully <br><b>Color Mode: </b>" + colorMode,
					Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Add Color Mode", "Failed to Added Color Mode", Status.FAIL);

		}
	}

	// Select rotate option
	public void addRotateValue(String rotateVal) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, rotateLabelXpath);
			UIHelper.click(driver, rotateLabelXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, rotateTextBoxXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, rotateTextBoxXpath, rotateVal);
			UIHelper.click(driver, rotateAddBtnXpath);
			report.updateTestLog("Add Rotation", "Rotation Added successfully <br><b>Rotate value: </b>" + rotateVal,
					Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Add Rotation", "Failed to Add Rotation", Status.FAIL);

		}
	}

	// Common method video profile
	public void commonMethodToEnterVideoProfDetails(String profName, String subAsstCode, String profDesc,
			String macCode) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, vdoProNameXpath);
			UIHelper.findAnElementbyXpath(driver, vdoProNameXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProNameXpath, profName);
			UIHelper.findAnElementbyXpath(driver, vdoProDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoProDescXpath, profDesc);
			UIHelper.findAnElementbyXpath(driver, vdoMacroCodeXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoMacroCodeXpath, macCode);
			UIHelper.findAnElementbyXpath(driver, vdoSubAsstXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, vdoSubAsstXpath, subAsstCode);

			report.updateTestLog("Enter video profile details",
					"Video profile details entered successfully." + "<br><b>Video Profile Name : </br>" + profName,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Enter video profile details", "Failed to Enter video profile Details.", Status.FAIL);
		}
	}

	// To verify transferered file version
	public void checkVersionOfTransformedFile(String version) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, fileVerionXpath);
			UIHelper.highlightElement(driver, fileVerionXpath);
			if (UIHelper.getTextFromWebElement(driver, fileVerionXpath).equals(version)) {

				report.updateTestLog("Verify tranferred  file Version",
						"File displayed with correct version details<br><b>Version :</b>" + version, Status.PASS);
			} else {
				report.updateTestLog("Verify tranferred  file Version",
						"File is not displayed with correct version details<br><b>Version :</b>" + version,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify tranferred  file Version",
					"File is not displayed with correct version details", Status.FAIL);
		}

	}

	// To select and apply transform rule based on file name
	public void selectProfileBasedOnFileName(String prefTxt) {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, applyTrasfromloadingXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
			UIHelper.click(driver, nxtBtnXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pvtNmeTxtXPath);
			driver.findElement(By.xpath(pvtNmeTxtXPath)).sendKeys(prefTxt);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
			UIHelper.click(driver, finBtnXPath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnPrfConXPath);
			report.updateTestLog("Select profile Apply transformation",
					"Profile Selected and transformation applied successfully.", Status.PASS);
			UIHelper.click(driver, okBtnPrfConXPath);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select profile Apply transformation", "Failed to select profile.", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void saveInLineEdit() {
		try {
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, inLineEditSaveXpath))) {
				UIHelper.highlightElement(driver, inLineEditSaveXpath);
				report.updateTestLog("Save InLine Edit value", "Saved sucessfully", Status.PASS);
				UIHelper.click(driver, inLineEditSaveXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
			} else {
				report.updateTestLog("Save InLine Edit value", "Not Saved", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Save InLine Edit value", "Save InLine Edit value Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void changeRenditionValue() {
		try {
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			UIHelper.highlightElement(driver, inLineEditTextAreaXapth);
			String value = sitesPage.getStringFromClipboard(inLineEditTextAreaXapth);
			String reNamedValue = value.replace("\"rendition\":false", "\"rendition\":true");

			UIHelper.sendKeysToAnElementByXpath(driver, inLineEditTextAreaXapth, reNamedValue);

			report.updateTestLog("Change Rendition Value to 'True'", "Value Changed", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 317085
	 * @return
	 * @comment Method is used remove gray scale in Profile
	 */
	public void removeToGrayScale() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, grayScalelinkXpath);
			UIHelper.click(driver, grayScalelinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, greyScaleCheckBoxXpath);
			UIHelper.click(driver, greyScaleCheckBoxXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, grayScaleAddBtnXpath);
			UIHelper.click(driver, grayScaleRemoveBtnXpath);
			// UIHelper.waitForVisibilityOfEleByXpath(driver,
			// grayScaleProfileSummaryXpath);
			report.updateTestLog("Select and remove GrayScale", "Grayscale selected and Removed successfully",
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Select Grayscale", "Grayscale removal failed", Status.FAIL);

		}
	}

	/**
	 * @author 317085
	 * @return String
	 * @comment Method is used copy Share URL from doc details page
	 */
	public String copyShareURL() {
		String value = null;
		try {
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareURLXapth);
			UIHelper.highlightElement(driver, shareURLXapth);
			value = sitesPage.getStringFromClipboard(shareURLXapth);
			// String reNamedValue = value.replace("\"rendition\":false",
			// "\"rendition\":true");

			// UIHelper.sendKeysToAnElementByXpath(driver,
			// inLineEditTextAreaXapth, reNamedValue);

			report.updateTestLog("Share URL", "Share URL copied. <br><b> Share URL :<\b>" + value, Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Share URL", "Share URL copy failed", Status.FAIL);
		}

		return value;
	}

	// navigate to the page where profile is available
	public void navigateToProfilePage(String profileName) {
		try {
			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * commonPageTitleXpath); searchProfile(profileName); WebElement
			 * pageHeaderEle = UIHelper.findAnElementbyXpath(driver,
			 * commonPageTitleXpath); UIHelper.highlightElement(driver,
			 * pageHeaderEle); UIHelper.waitForPageToLoad(driver);
			 */

			isProfileDisplatedInNavigatedPages(profileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// check file or profile
	public boolean isProfileDisplayed(String profileName) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper.findListOfElementsbyXpath(profileNamesXpath,
					driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				System.out.println(ele.getText());
				if ((ele.getText().trim()).contains(profileName)
						|| (ele.getText().trim()).equalsIgnoreCase(profileName)) {
					flag = true;
					UIHelper.waitFor(driver);
					// UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Navigate and check profile
	public boolean isProfileDisplatedInNavigatedPages(String profileName) {
		boolean isProfileDisplayed = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, dataLoadingXpathMTP);
			UIHelper.waitFor(driver);

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, searchFieldXpath);
			} catch (Exception e) {
			}

			if (UIHelper.checkForAnElementbyXpath(driver, searchFieldXpath)) {
				UIHelper.highlightElement(driver, searchFieldXpath);
				UIHelper.sendKeysToAnElementByXpath(driver, searchFieldXpath, profileName);
				UIHelper.waitFor(driver);
			}

			isProfileDisplayed = isProfileDisplayed(profileName);

			if (isProfileDisplayed == false) {
				List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);

				WebElement element = noOfPagesEle.get(noOfPagesEle.size() - 1);
				String noOfPages = element.getText();
				int lastEle = Integer.parseInt(noOfPages);
				for (int index = 0; index < lastEle; index++) {
					UIHelper.highlightElement(driver, nextPageXpath);
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);

					isProfileDisplayed = isProfileDisplayed(profileName);
					if (isProfileDisplayed) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isProfileDisplayed;
	}

	// check file or profile
	public boolean isProfileDisplayedInSelectWindow(String profileName) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(profileNamesInSelectWindowXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(profileName)) {
					UIHelper.waitFor(driver);
					UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					flag = true;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// check file or profile
	public boolean isRendProfileDisplayedInSelectWindow(String profileName) {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(profileNamesInSelectWindowXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(profileName) || ele.getText().contains(profileName)) {
					UIHelper.waitFor(driver);
					UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					flag = true;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Search profile
	public void searchProfile(String profileName) {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, searchFieldXpath)) {
				UIHelper.highlightElement(driver, searchFieldXpath);
				UIHelper.sendKeysToAnElementByXpath(driver, searchFieldXpath, profileName);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To verify transform icon
	public boolean isTransformIconAvailable(String folderName) {
		boolean flag = false;
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);
			String finalTransformIconXpath = transformIconXpath.replace("CRAFT", folderName);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (ele.getText().contains(folderName)) {
					UIHelper.highlightElement(driver, ele);
					if (UIHelper.findAnElementbyXpath(driver, finalTransformIconXpath).getAttribute("src")
							.contains("pearson-mediawatchfolder-16.png")) {
						UIHelper.highlightElement(driver, finalTransformIconXpath);
						flag = true;
					}

					break;

				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify tranferred  file is available", "Check file is available Failed", Status.FAIL);
		}
		return flag;
	}

	// check Add button is available
	public boolean isAddBtnAvailableInTransfromPg() {
		boolean flag = false;
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, saveXpath);
			if (UIHelper.findAnElementbyXpath(driver, addXpath).isDisplayed()) {
				UIHelper.highlightElement(driver, addXpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * @author 391543
	 */
	// check select check box available for the profile
	public void selectTransformationProfileOptionAvailable(String prfNme) {
		boolean isProfileDisplayed = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, watFldrYesXPath);
			UIHelper.highlightElement(driver, watFldrYesXPath);
			UIHelper.click(driver, watFldrYesXPath);

			searchProfile(prfNme);
			isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);

			if (isProfileDisplayed == false) {
				List<WebElement> noOfPagesEle = UIHelper.findListOfElementsbyXpath(pagesXpath, driver);

				WebElement element = noOfPagesEle.get(noOfPagesEle.size() - 1);
				String noOfPages = element.getText();
				int lastEle = Integer.parseInt(noOfPages);
				for (int index = 0; index < lastEle; index++) {
					UIHelper.highlightElement(driver, nextPageXpath);
					UIHelper.click(driver, nextPageXpath);
					UIHelper.waitFor(driver);

					isProfileDisplayed = isProfileDisplayedInSelectWindow(prfNme);
					if (isProfileDisplayed) {
						break;
					}
				}
			}

			String prfSlctXPathFinal = prfSlctXPath.replace("CRAFT", prfNme);
			UIHelper.waitForVisibilityOfEleByXpath(driver, prfSlctXPathFinal);
			if (UIHelper.findAnElementbyXpath(driver, prfSlctXPathFinal).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, prfSlctXPathFinal).isEnabled()) {

				UIHelper.highlightElement(driver, prfSlctXPathFinal);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Select option available for Image/Video Profile",
						"Select Option available for the profile <br><b>Profile Name :</b>" + prfNme, Status.PASS);

			} else {
				report.updateTestLog("Verify Select option available for Image/Video Profile",
						"Select Option is not available for the profile <br><b>Profile Name :</b>" + prfNme,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Select option available for Image/Video Profile",
					"Select Option is not available for the profile", Status.FAIL);
		}

	}

	// Check Preview button
	public boolean checkPreviewBtn() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, previewBtnXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, previewBtnXpath));
			if (UIHelper.checkForAnElementbyXpath(driver, previewBtnXpath)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Check Media watch folders in Doc lib page
	public boolean checkMediaWatchFolderInDocLibPg(String folderName) {
		boolean isDisplayedMediaWatchFolder = false;
		try {
			String finalXpathForMediaWatchFolderInDocLib = tempXpathForMediaWatchFolderInDocLib.replace("CRAFT",
					folderName);

			if (UIHelper.checkForAnElementbyXpath(driver, finalXpathForMediaWatchFolderInDocLib)) {
				isDisplayedMediaWatchFolder = true;
			} else {
				isDisplayedMediaWatchFolder = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedMediaWatchFolder;
	}
	
	
	// to click profile add option transformation wizard
	public void clickprofiletypeopen(String type) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String finalprofileadd = tempprofileadd.replace("CRAFT", type);
			String finalwaitforpanel = tempwaitforpanel.replace("CRAFT", type);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalprofileadd);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalprofileadd));
			UIHelper.click(driver, finalprofileadd);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalwaitforpanel);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// get slider value and conpare to input
	public  ArrayList<Integer> getsliderdata(String type) {
		
		ArrayList<Integer> sliderdata = new ArrayList<Integer>();
		
		try {
			String finalgetvalue = tempgetvalue.replace("CRAFT", type);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalgetvalue);
			//UIHelper.findandAddElementsToaList(driver, finalgetvalue, sliderdata);
			
			List<WebElement> slider= driver.findElements(By
					.xpath(finalgetvalue));

			for (WebElement listData : slider) {
				
				sliderdata.add(Integer.parseInt(listData.getText()));
			}
			
		/*	System.out.println(sliderdata);
			
			for(String value : sliderdata){
				//numberList.add(Integer.parseInt(value));
				
				int i = Integer.parseInt(value);
				if(i!=0 && i % 10==0 ){
					System.out.println("true");
				}else{
					System.out.println("false");
				}
				
			}
*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sliderdata;

	}
	
	
	public String RetreiveFilename(String fileName) {
		boolean flag = false;
		String name = null;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageBodyXpath);
			List<WebElement> searchResultsListEle = driver.findElements(By.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (ele.getText().contains(fileName)) {
					UIHelper.highlightElement(driver, ele);
					System.out.println(ele.getText());
					 name = ele.getText();
					flag = true;
					break;

				}
			}
		} catch (Exception e) {
			report.updateTestLog("Verify tranferred  file is available", "Check file is available Failed", Status.FAIL);
		}
		return name;
	}
	
	// click on create Image profile button
	public void clickOnCreateaudioProfBtn() {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, mediaPageLoadingXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createaudioBtnXpath);
			UIHelper.highlightElement(driver, createaudioBtnXpath);
			UIHelper.click(driver, createaudioBtnXpath);
			report.updateTestLog("Click on create audio profile button",
					"Create audio profile button clicked successfully", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click on create audio profile button",
					"Failed to click on Create Image profile button", Status.FAIL);
		}
	}
	
	
	
	// click on create audio profile button
	public void enteraudioSettingsDetails(String audiodetails) {
		try {

			String splittedFields[] = audiodetails.split(",");

			if (splittedFields != null) {
				for (String value : splittedFields) {
					String splitFiledValue[] = value.split(":");

					if (splitFiledValue != null) {
						String finalFieldValueXpath = selectoptionsXpath
								.replace("CRAFT", splitFiledValue[0]);
						String finalSelectValXpath = audioselectValXpath.replace(
								"CRAFT", splitFiledValue[1]);
						UIHelper.highlightElement(driver, finalFieldValueXpath);
						UIHelper.click(driver, finalFieldValueXpath);
						//System.out.println(finalSelectValXpath);
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								finalSelectValXpath);
						// UIHelper.waitFor(driver);
						
						UIHelper.click(driver, finalSelectValXpath);
					}

				}

				report.updateTestLog("Edit media Profile settings",
						"Media profile details updated successfully.",
						Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	public void enterAudioProfileDetails(String PrfName, String PrfDesc, String Macro, String Subasset )
	{
		try{
			
		UIHelper.highlightElement(driver, audioProfNamesXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, audioProfNamesXpath, PrfName);
		UIHelper.highlightElement(driver, audioProfDescrXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, audioProfDescrXpath, PrfDesc);
		UIHelper.highlightElement(driver, macroXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, macroXpath, Macro);
		UIHelper.highlightElement(driver, subassetcodeXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, subassetcodeXpath, Subasset);
		report.updateTestLog("Edit media Profile settings",
				"Audio Media profile <b>"+PrfName + " "+PrfDesc+" "+Macro+" "+Subasset+"details updated succssfully",
				Status.PASS);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public void SubmitAddTransformationrules(String AddTransformationXXXSubAssetXXXTransformationDisabledXXXDuplicate){
		
		UIHelper.highlightElement(driver, addImageProfBtnXpath);
		UIHelper.click(driver, addImageProfBtnXpath);
		
		
		if(AddTransformationXXXSubAssetXXXTransformationDisabledXXXDuplicate.contains("AddTransformation")){
			UIHelper.waitForVisibilityOfEleByXpath(driver, transformationprofileXpath);
			Boolean flag=UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, transformationprofileXpath));
				
				if(flag){
					//UIHelper.highlightElement(driver, OkbuttonXpath);
					//UIHelper.click(driver,OkbuttonXpath);
					report.updateTestLog("Add Transformation",
							"Audio Transformation is successfully created.",
							Status.PASS);
				}else{
					report.updateTestLog("Add Transformation",
							"Audio Transformation is successfully created.",
							Status.FAIL);
				}
		
		} else if(AddTransformationXXXSubAssetXXXTransformationDisabledXXXDuplicate.contains("SubAsset")){
		
			boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, subassetcodemustnotempty));
			if(flag){
				UIHelper.click(driver, OkbuttonXpath);
				report.updateTestLog("Sub Asset Message",
						"Sub Asset code must not empty Message is appeared",
						Status.PASS);
			}else{
				report.updateTestLog("Sub Asset Message",
						"Sub Asset code must not empty Message is not appeared",
						Status.FAIL);
			}
			
		}else if(AddTransformationXXXSubAssetXXXTransformationDisabledXXXDuplicate.contains("TransformationDisabled")){
			
			boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, audioProfNamesXpath));	
			if(flag){
				
				report.updateTestLog("Add Transformation Button Status",
						"Add Transformation button is disabled since mandatory fields are not entered",
						Status.PASS);
			}else{
				report.updateTestLog("Add Transformation Button Status",
						"Add Transformation button is not disabled even though mandatory fields are not entered",
						Status.FAIL);
			}
		
		}else if(AddTransformationXXXSubAssetXXXTransformationDisabledXXXDuplicate.contains("Duplicate")){
			UIHelper.waitForVisibilityOfEleByXpath(driver, duplicateerrormessageXpath);
			Boolean flag=UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, duplicateerrormessageXpath));
			if(flag){
				UIHelper.click(driver, OkbuttonXpath);
				report.updateTestLog("Duplicate Message",
						"Duplicate Profile with same name or sub asset code exists Message is appeared",
						Status.PASS);
			}else{
				report.updateTestLog("Duplicate Message",
						"Duplicate Profile with same name or sub asset code exists Message is not appeared",
						Status.FAIL);
			}
		}
		
	
	}
	
	
	public void getvaluesofaudioprofiletransformdetails(String audiodetails){
		
		try{
			
			int num=0;
			String audiofields = null;
			String[] audioprofile = audiodetails.split(";");
			
			if (audioprofile != null) {
				for (String value : audioprofile) {
					String splitFiledValue[] = value.split(":");

					if (splitFiledValue != null) {
						
						String[] splitFieldvaluesList= splitFiledValue[1].split(",");
						String finalFieldValueXpath = selectoptionsXpath
								.replace("CRAFT", splitFiledValue[0]);
						//System.out.println(finalFieldValueXpath);
						UIHelper.highlightElement(driver, finalFieldValueXpath);
					
						
						for(String fieldvalue:splitFieldvaluesList){
							UIHelper.click(driver, finalFieldValueXpath);
							//System.out.println(fieldvalue);
							String actual = audioselectValXpath.replace("CRAFT", fieldvalue);
							//System.out.println(actual);
							String actualvalue = UIHelper.getTextFromWebElement(driver, actual);
						//System.out.println(actualvalue);
								if(fieldvalue.equalsIgnoreCase(actualvalue)){
								//	System.out.println("actual "+actualvalue);
								num++;
								}
							
							if(num==1){
								report.updateTestLog("Option of Audio Settings",
										"The option "+actualvalue+" Audio settings/Choose transform details for "+splitFiledValue[0]+"is in transformation profile screen",
										Status.PASS);
							}else{
								report.updateTestLog("Option of Audio Settings",
										"The option "+actualvalue+" Audio settings/Choose transform details for "+splitFiledValue[0]+"is not appeared in transformation profile screen",
										Status.FAIL);
							}
							num=0;
							UIHelper.click(driver, finalFieldValueXpath);
						}
					}

				}

				report.updateTestLog("Edit media Profile settings",
						"Media profile details updated successfully.",
						Status.PASS);
			}
			
		}catch(Exception e){
			
		}
	}
		
	public void getprofilesummarydetailsFrmMediaTransPg(String profName, String profiledetails) {
		
		try {
			
			isProfileDisplatedInNavigatedPages(profName);
			
			String finalSelectValOptionXpath = audioprofilenamedetails.replace("CRAFT", profName);
			
			//System.out.println(finalSelectValOptionXpath);
			int num=0;
			String dateutc;
			if (UIHelper.checkForAnElementbyXpath(driver, finalSelectValOptionXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalSelectValOptionXpath));
				String profilename= UIHelper.getTextFromWebElement(driver, finalSelectValOptionXpath);
				if(profilename.equalsIgnoreCase(profilename)){
					report.updateTestLog("ProfileDetails:",
							"<b>"+profilename +" value is appeared",Status.PASS);
				//	System.out.println("pass");
				}else{
					report.updateTestLog("ProfileDetails:",
							"<b>"+profilename +" value is not appeared",Status.FAIL);
				//System.out.println("fail");
				}
				UIHelper.waitFor(driver);
				String audio=audioprofiledetails.replace("CRAFT", profName);
				List<WebElement> li = driver.findElements(By.xpath(audio));
				
				for(WebElement lil:li){
					String actual = lil.getText();
					System.out.println(actual);
					if(actual.contains("UTC")){
						dateutc=actual.substring(0, 9);
						dateutc=dateutc.concat(actual.substring(19, 28));
						System.out.println(dateutc);
						actual=dateutc;
						System.out.println(actual);
					}
					String[] details= profiledetails.split(",");
					
					for(int i=0;i<=4;i++){
						if(actual.contains(details[i])){
						num++;
						}
					}
					if(num==1){
						report.updateTestLog("ProfileDetails:",
								"<b>"+actual +" value is appeared",Status.PASS);
					//	System.out.println("pass");
					}else{
						report.updateTestLog("ProfileDetails:",
								"<b>"+actual +" value is not appeared",Status.FAIL);
						//System.out.println("fail");
					}
					num=0;
					
					
				}
			}
	
		}catch(Exception e){
			
		}
	}
	
	
	public String gettransformationprofilesummarydetails_Audio(String profilefield, String profilevalue){
		
		String audioprofilevalue = null;
		
		try{
			
		String audioprofilename = transformationprofilesummaryXpath.replace("CRAFT", profilefield);
		audioprofilevalue = UIHelper.getTextFromWebElement(driver, audioprofilename);
		
		if(audioprofilevalue.equalsIgnoreCase(profilevalue)){
			report.updateTestLog("ProfileDetails:",
					"<b>"+audioprofilevalue +" value is appeared for "+profilefield,Status.PASS);
		}else{
			report.updateTestLog("ProfileDetails:",
					"<b>"+audioprofilevalue +" value is not appeared for "+profilefield,Status.FAIL);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return audioprofilevalue;
		
	}
	
	
	public String CheckSortOrderofMediaProfile(String columnname){
		
		String profile = null; 
		
		if(columnname.equalsIgnoreCase("Profile Name")){
			profile="1";
		}else if(columnname.equalsIgnoreCase("Profile Description")){
			profile="2";
		}else if(columnname.equalsIgnoreCase("Profile Creation Date")){
			profile="3";
		}else if(columnname.equalsIgnoreCase("Profile Type")){
			profile="4";
		}else if(columnname.equalsIgnoreCase("Profile Author")){
			profile="5";
		}else if(columnname.equalsIgnoreCase("Profile Actions")){
			profile="6";
		}
		String sortbutton = mediasummarryvaluetable.replace("CRAFT", columnname);
		String column= mediasummarytable.replace("CRAFT", profile);
		
		UIHelper.click(driver, sortbutton);
		List<WebElement> li= driver.findElements(By.xpath(column));
		
		return OkbuttonXpath;
		
		
		//
		//
	
		//Profile Name	Profile Description	Profile Creation Date	Profile Type	Profile Author	Profile Actions
		
	}
	
	private String firstRowValXpathInAsyncStatusDashlet = ".//*[@id='ProfileListTable']/tbody/tr[1]";
	private String asyncDetailsPageTableHeaderNamesXpath = ".//*[@id='ProfileListTable']/thead/tr/th";
	
	public boolean performSortOperationFormediaTransformation(String sortBy) {
		boolean isPerformedSortOperation = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, firstRowValXpathInAsyncStatusDashlet);
			UIHelper.waitFor(driver);
			String getFirstrowValue = UIHelper.getTextFromWebElement(driver, firstRowValXpathInAsyncStatusDashlet);
			List<WebElement> asyncDashletHeaderNamesListEle = UIHelper
					.findListOfElementsbyXpath(asyncDetailsPageTableHeaderNamesXpath, driver);

			for (WebElement ele : asyncDashletHeaderNamesListEle) {
				if (ele.getText().trim().equalsIgnoreCase(sortBy)) {
					UIHelper.highlightElement(driver, ele);
					ele.click();
					UIHelper.waitFor(driver);
					ele.click();
					UIHelper.waitForVisibilityOfEleByXpath(driver, firstRowValXpathInAsyncStatusDashlet);
					String getUpdatedFirstrowValue = UIHelper.getTextFromWebElement(driver,
							firstRowValXpathInAsyncStatusDashlet);
					if (!getUpdatedFirstrowValue.equalsIgnoreCase(getFirstrowValue)
							|| getUpdatedFirstrowValue.equalsIgnoreCase(getFirstrowValue)) {
						isPerformedSortOperation = true;
					} else {
						isPerformedSortOperation = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPerformedSortOperation;
	}
	
	public void CheckSortMediatransformation(String columnname){
		
		try{
			Boolean flag = performSortOperationFormediaTransformation(columnname);
			if(flag){
				report.updateTestLog("Media Transformation Sort:",
						"<b> User able to sort based on"+columnname,Status.PASS);
			}else{
				report.updateTestLog("Media Transformation Sort:",
						"<b> User not able to sort based on"+columnname,Status.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void CreationofAudioProfile(String profilename, String profiledesc, String macro, 
			String subcode,String audiodetails, String username){
		
		DateUtil date =  new DateUtil();
		navigateToMediaTransPage();
		deleteProfileFrmMediaTransPg(profilename);
		clickOnCreateaudioProfBtn();
		enterAudioProfileDetails(profilename, profiledesc, macro, subcode);
		SubmitAddTransformationrules("AddTransformation");
		enteraudioSettingsDetails(audiodetails);
		UIHelper.click(driver, vdoSaveBtnXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, OkbuttonXpath);
		UIHelper.click(driver, OkbuttonXpath);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		String dateutc1;
		String utcdate1 = date.getUTCformatDateandTime();
		dateutc1=utcdate1.substring(0, 9);
		dateutc1=dateutc1.concat(utcdate1.substring(19, 28));
		//System.out.println(dateutc1);
		getprofilesummarydetailsFrmMediaTransPg(profilename,profiledesc+","+dateutc1+",AUDIO,"+username+",Profile Action");
		
	}
	
	// check file or profile
		public boolean isSearchWorking(String profileName, String column) {
			boolean flag = false;
			String search = "";
			try {
				if(column.equalsIgnoreCase("Profile Name")){
					 search = searchnamesXpath.replace("CRAFT", "1");	
				}else if(column.equalsIgnoreCase("Profile Description")){
					search = searchnamesXpath.replace("CRAFT", "2");	
				}else if(column.equalsIgnoreCase("Profile Creation Date")){
					search = searchnamesXpath.replace("CRAFT", "3");	
				}else if(column.equalsIgnoreCase("Profile Type")){
					search = searchnamesXpath.replace("CRAFT", "4");	
				}else if(column.equalsIgnoreCase("Profile Author")){
					search = searchnamesXpath.replace("CRAFT", "5");	
				}else if(column.equalsIgnoreCase("Profile Actions")){
					search = searchnamesXpath.replace("CRAFT", "6");	
				}
				UIHelper.waitForPageToLoad(driver);
				List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper.findListOfElementsbyXpath(search,
						driver);
				for (WebElement ele : uploadedFileOrFolderTitleEleList) {
					System.out.println(ele.getText());
					if ((ele.getText().trim()).contains(profileName)
							|| (ele.getText().trim()).equalsIgnoreCase(profileName)) {
						flag = true;
						UIHelper.waitFor(driver);
						// UIHelper.scrollToAnElement(ele);
						UIHelper.highlightElement(driver, ele);
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
		
		
		public void sliderbarcheck(String profiletitle, String num){
			String style="";
			try{
			String slide=sliderbarxpath.replace("CRAFT",profiletitle );
			String click= sliderbarclickxpath.replace("CRAFT", profiletitle);
			UIHelper.click(driver, click);
			
			if(num.startsWith("-")){
				String number =num.replace("-", "");
				number=num.trim();
				int count=Integer.parseInt(number);
				for(int i=0;i<=count;i++){
				driver.findElement(By.xpath(click)).sendKeys(Keys.ARROW_LEFT);
				}
			}else{
			int count=Integer.parseInt(num);
			for(int i=1;i<=count;i++){
			driver.findElement(By.xpath(click)).sendKeys(Keys.ARROW_RIGHT);
			}
			}
			
		
			style= driver.findElement(By.xpath(click)).getAttribute("aria-valuenow");
				report.updateTestLog("Slider Bar of "+profiletitle,
						"<b> slider bar is moved to width of "+style,Status.PASS);
			
			}catch(Exception e){
				report.updateTestLog("Slider Bar of "+profiletitle,
						"<b> slider bar is not moved to width of "+style,Status.FAIL);
				e.printStackTrace();
			}
			
		}
		
		public void addbuttoninImage(String profiletitle){
			try{
			String add= addbuttonxpathofitems.replace("CRAFT", profiletitle);	
			String profile=profilesummaryXpath.replace("CRAFT", profiletitle);
			System.out.println(add);
			UIHelper.waitForVisibilityOfEleByXpath(driver, add);
			UIHelper.click(driver, add);
			UIHelper.waitForVisibilityOfEleByXpath(driver, profile);
			String pro=UIHelper.getTextFromWebElement(driver, profile);
			report.updateTestLog("Select and Add "+pro, pro+"selected and Added successfully", Status.DONE);
		
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//Added as part of NALS AUT_AG_073 
		public void applyTransformationByFileBasedWithoutpreTxt(String fileName, String prefTxt,String message) {
			try {
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, filNmeYesXPath);
				UIHelper.highlightElement(driver, filNmeYesXPath);
				UIHelper.click(driver, filNmeYesXPath);

				UIHelper.waitForVisibilityOfEleByXpath(driver, nxtBtnXPath);
				UIHelper.click(driver, nxtBtnXPath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, pvtNmeTxtXPath);
				driver.findElement(By.xpath(pvtNmeTxtXPath)).sendKeys(prefTxt);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finBtnXPath);
				UIHelper.click(driver, finBtnXPath);
				//UIHelper.waitForVisibilityOfEleByXpath(driver, okBtnPrfConXPath);
				/*if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, okBtnPrfConXPath))) {
					report.updateTestLog("Apply File based Transformation",
							"Transformation applied succesfully" + "<br><b> File Name : </b>" + fileName, Status.PASS);
				} else {
					report.updateTestLog("Apply File based Transformation",
							"Transformation not applied" + "<br><b> File Name : </b>" + fileName, Status.FAIL);
				}*/
				
				UIHelper.waitForPageToLoad(driver);
				if (UIHelper.getTextFromWebElement(driver, notificationXpath).contains(message)) {

					UIHelper.highlightElement(driver, notificationXpath);
					report.updateTestLog("Verify notification message", "Notification message displayed successfully.",
							Status.PASS);
				} else {
					report.updateTestLog("Verify notification message", "Notification message is not displayed.",
							Status.FAIL);
				}
				UIHelper.click(driver, okBtnPrfConXPath);
				
			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog("Verify notification message", "Verify notification message Failed",
						Status.FAIL);
			}
		}
		

}

