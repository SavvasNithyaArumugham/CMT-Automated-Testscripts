package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;
import com.pearson.framework.Util;

public class AlfrescoMediaGuidePage extends ReusableLibrary {

	// Media Guide - image profile
	String dateFormat = "HHmmssddMM";
	private String video = ".//*[@class='nav-item']//*[text()='Video']";
	private String streamingVideo = ".//*[@class='nav-item']//*[text()='Streaming Video']";
	private String audio = ".//*[@class='nav-item']//*[text()='Audio']";
	private String streamingAudio = ".//*[@class='nav-item']//*[text()='Streaming Audio']";
	private String all = ".//*[@class='nav-item']//*[text()='All']";
	private String activetab = ".//*[@class='nav-link active']";
	private String convert = ".//*[@class='pull-right btn btn-primary']";
	private String selectallchkbox = "//*[@class='checkbox']//input[@type='checkbox']";
	private String selectchkbox = "//*[@class='mediaTile']//input[@type='checkbox']";
	private String fileNameLabel = "//*[@class='mediaTile']//div[@class='form-group'][1]";
	private String title = "//*[@class='mediaTile']//descendant::input[2]";
	private String referenceID = "//*[@class='mediaTile']//descendant::input[3]";
	private String savefield = "//div[@class='pull-right']//button[@class='btn btn-xs btn-default']";
	private String alertAccept = ".//*[contains(@class,'first-child') and contains(.,'Close')]";
	private String shareicon = "//*[contains(@class,'yui-dt-data')]//*[contains(@class,'yui-dt-col-status')]//div[@class='status']/img[@title='Folder shared externally']//ancestor::tr//*[contains(@class,'yui-dt-col-fileName')]//h3//span//a";
	private String convertToStreamMedia = "//*[@class='pull-right btn btn-primary']";
	private String toggleButton = "//*[@class='dropdown-toggle btn btn-default']";
	private String errorPromptBox = "//*[contains(@id,'prompt_h')]";
	private String errorValidationMessage = "//*[contains(@class,'yui-module yui-overlay yui-panel')]//*[contains(@class,'bd')]";
	private String errorCloseButton = "//*[contains(@class,'ft')]//descendant::span[contains(@class,'first-child')]/button[text()='Close']";
	private String selectListView = "//*[contains(@class,'dropdown-menu')]/li/a[contains(text(),'List')]";
	private String selectGridView = "//*[contains(@class,'dropdown-menu')]/li/a[contains(text(),'Grid')]";
	private String streamTableXpath = "//table[@class='table table-bordered']/tbody/tr[1]";
	private String listDataTableReferenceIDXpath = "//table[@class='table table-bordered']/tbody/tr[1]/td[@tabindex='3']";
	private String listDataTableTitleXpath = "//table[@class='table table-bordered']/tbody/tr[1]/td[@tabindex='4']";
	private String listStreamDataTableReferenceIDXpath = "//table[@class='table table-bordered']/tbody/tr[1]/td[@tabindex='4']";
	private String listStreamDataTableTitleXpath = "//table[@class='table table-bordered']/tbody/tr[1]/td[@tabindex='5']";
	private String gridViewMenu = "//div[@class='mediaGrid']/descendant::div[@class='form-group'][2]//input[@type='text']";
	private String listViewMenu = "//div[@class='tab-container-wrapper']/descendant::div[@class='col-xs-12']/button[contains(text(),'Select Columns')]";
	private String docActionXpath = ".//*[contains(@class,'document-actions document-details-panel') or contains(@class,'folder-actions folder-details-panel')]";
	private String docAudioPreview = "//*[contains(@class,'previewer Audio')]";
	private String docVideoPreview = "//*[contains(@class,'previewer Video')]";
	private String thumbnailXpath = "//div[@class='mediaTile']//descendant::div//img[@class='img-responsive img-thumbnail']";
	private String portraitIconXpath = "//div[@class='mediaTile']//descendant::div//span[@class='glyphicon glyphicon-picture']";
	private String listViewTableColumnXpath = "//div[@class='react-bs-table-container table-fixed']//table[@class='table table-hover table-bordered']/thead/tr/th[@class='sort-column']";
	private String relatedFileXpath = "//div[@class='common-tab-container clear-space']/ul/li[2]/a";
	private String thumbnailButtonXpath = "//div[@class='form-field fleft ']//descendant::label[@for='thumbnailImage']";
	private String captionButonXpath = "//div[@class='form-field fleft ']//descendant::label[@for='captionFile0']";
	private String xmlButtonXpath = "//div[@class='form-field fleft ']//descendant::label[@for='chapteringFile']";
	private String thumbnailuploadXpath = "//div[@class='uploadRelatedFile ']//div[@class='form-left2']//div[@class='form-field fleft ']/div[1]/input[@id='thumbnailImage']";
	private String captionuploadXpath = "//div[@class='form-field fleft ']//descendant::input[@id='captionFile0']";
	private String xmluploadXpath = "//div[@class='form-field fleft ']//descendant::input[@id='chapteringFile']";
	private String successImgtranscodedXpath = "//table[@class='table table-bordered']/tbody/tr/td[3]/span[@title='Success']";
	private String inProgressImgtranscodedXpath = "//table[@class='table table-bordered']/tbody/tr/td[3]/span[@title='In Progress']";
	private String noDataforInprogressXpath = "//div[@id='av-media-guide']//*[contains(@class,'table-fixed')]//td[contains(text(),'There is no data to display')]";

	WebElement element1;
	boolean IsSelectAll;
	boolean IsFileselected;
	private ArrayList<String> listViewColumnName = new ArrayList<String>();
	private ArrayList<String> unTranscodedFiles = new ArrayList<String>();
	private ArrayList<String> getTitleValues = new ArrayList<String>();

	private String saveUploadRelatedFileButton = "//div[@class='fright form-action-top-space']//div[@class='fright']//input[@type='submit']";

	private String progressBarStatus = "//div[@class='progress-bar']";
	private String successMsgRelativeFiles = "//*[contains(@class,'yui-module yui-overlay yui-panel')]//div[@class='bd'][contains(text(),'Transcoding in progress')]";
	private String successMsgOKbutton = "//div[@class='ft']//span[@class='button-group']//*[contains(@class,'first-child')]/button[contains(text(),'OK')]";
	private String fileNumberXpath = "//*[@id='av-media-guide']/descendant::div[@class='row'][last()]//div[@class='form-inline']";
	private String leftPanelButtonxpath = "//div[@id='av-media-guide']//div[@class='pull-left col-xs-1']//button[@class='pull-right btn btn-default']";
	private String statusFilterSection = "//div[@id='av-media-guide']//div[@class='filter-container']//section[3]//child::section//ul//li";
	private String inProgressStatusFilter = "//div[@id='av-media-guide']//div[@class='filter-container']//section[3]//child::section//ul//li[2]//input[@value='In Progress']";
	private String successStatusFilter = "//div[@id='av-media-guide']//div[@class='filter-container']//section[3]//child::section//ul//li[1]//input[@value='Success']";
	ArrayList<String> statusFilterItems = new ArrayList<String>();

	public AlfrescoMediaGuidePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	// Verifying Tab in Media Guide Interface
	public void VerifyTabsInMediaInterface() {
		try
		{
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		String test1, value;
		String xpath = null;

		driver.switchTo().window(tab.get(1));
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		Boolean flag1 = checktab(video, streamingVideo, audio, streamingAudio, all);

		if (flag1) {
			report.updateTestLog(
					"Check whether Video, Streaming Video, Audio, Streaming Audio & All Tabs are displayed",
					" " + "All 5 Tabs are displayed", Status.PASS);
		} else {
			report.updateTestLog(
					"Check whether Video, Streaming Video, Audio, Streaming Audio & All Tabs are displayed",
					" " + "All 5 Tabs are not displayed", Status.FAIL);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			report.updateTestLog(
					"Check whether Video, Streaming Video, Audio, Streaming Audio & All Tabs are displayed",
					" " + "All 5 Tabs are not displayed", Status.FAIL);
		}
	}

	// Verify Default Tab is in Video
	public void verifyDefaultTab() {
		try {
			if (UIHelper.findAnElementbyXpath(driver, activetab).getText().equals("Video")) {
				report.updateTestLog(
						"Check Default Tab of MediaGuide displays Video Tab withall untrancoded Video files",
						"Video Tab displayed as Default Tab in MediaGuideInterface", Status.PASS);
			} else {
				report.updateTestLog(
						"Check Default Tab of MediaGuide displays Video Tab withall untrancoded Video files",
						"Video Tab failed to displayed as Default Tab in MediaGuideInterface", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Navigate to particular Tab
	public void navigateToParticularTab(String convertAction) {
		try {
			if (convertAction.equals("audio")) {
				if (UIHelper.checkForAnElementbyXpath(driver, audio)) {
					UIHelper.highlightElement(driver, audio);
					UIHelper.click(driver, audio);

				}
			}
			if (convertAction.equals("video")) {
				if (UIHelper.checkForAnElementbyXpath(driver, video)) {
					UIHelper.highlightElement(driver, video);
					UIHelper.click(driver, video);
				}

			}
			if (convertAction.equals("Streaming Audio")) {
				if (UIHelper.checkForAnElementbyXpath(driver, streamingAudio)) {
					UIHelper.highlightElement(driver, streamingAudio);
					UIHelper.click(driver, streamingAudio);
				}

			}
			if (convertAction.equals("Streaming Video")) {
				if (UIHelper.checkForAnElementbyXpath(driver, streamingVideo)) {
					UIHelper.highlightElement(driver, streamingVideo);
					UIHelper.click(driver, streamingVideo);
				}
			}
			if (convertAction.equals("All")) {
				if (UIHelper.checkForAnElementbyXpath(driver, all)) {
					UIHelper.highlightElement(driver, all);
					UIHelper.click(driver, all);
				}
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
			}

			report.updateTestLog("Verify " + convertAction + "Tab is Navigated",
					" " + convertAction + " Tab is Navigated ", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify " + convertAction + " Files is selected using checkbox in " + convertAction + " File ",
					" " + convertAction + " File is not selected", Status.FAIL);
		}

	}

	// Method to click on single fileselect
	public void clickOnFileSelectCheckBox(String convertAction) {
		try {
			if (convertAction.equals("audio")) {
				if (UIHelper.checkForAnElementbyXpath(driver, audio)) {
					UIHelper.highlightElement(driver, audio);
					UIHelper.click(driver, audio);
				}
			}
			if (convertAction.equals("video")) {
				if (UIHelper.checkForAnElementbyXpath(driver, video)) {
					UIHelper.highlightElement(driver, video);
					UIHelper.click(driver, video);
				}

			}

			UIHelper.waitFor(driver);
			if (!UIHelper.isSelected(UIHelper.findAnElementbyXpath(driver, selectchkbox))) {
				UIHelper.click(driver, selectchkbox);
			}

			IsFileselected = UIHelper.isSelected(UIHelper.findAnElementbyXpath(driver, selectchkbox));
			if (IsFileselected) {
				report.updateTestLog(
						"Verify " + convertAction + " File is selected using checkbox in " + convertAction + " Files ",
						" " + convertAction + " File is selected", Status.PASS);
			} else {

				report.updateTestLog(
						"Verify " + convertAction + " File is selected using checkbox in " + convertAction + " File ",
						" " + convertAction + " File is not selected", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify " + convertAction + " Files is selected using checkbox in " + convertAction + " File ",
					" " + convertAction + " File is not selected", Status.FAIL);
		}
	}

	// Method to select all Files
	public void clickOnSelectAllCheckbox(String convertAction) {
		UIHelper.uncheckanElement(UIHelper.findAnElementbyXpath(driver, selectallchkbox));
		if (!UIHelper.isSelected(UIHelper.findAnElementbyXpath(driver, selectallchkbox))) {
			UIHelper.click(driver, selectallchkbox);
		}

		// UIHelper.click(driver, selectallchkbox); // Deselect all check box
		// UIHelper.click(driver, selectallchkbox); // Select all check box
		try {
			List<WebElement> checkbox = UIHelper.findListOfElementsbyXpath(selectchkbox, driver);
			IsSelectAll = UIHelper.verifycheckboxselected(checkbox);

			if (IsSelectAll = true) {
				report.updateTestLog("Verify all  " + convertAction + " Files are Selected using Select All checkbox",
						" " + "All  " + convertAction + " Files selected", Status.PASS);
			} else {
				report.updateTestLog("Verify all  " + convertAction + "  Files are Selected using Select All checkbox",
						" " + "All  " + convertAction + " Files not selected", Status.FAIL);
			}
		}

		catch (Exception e) {
			report.updateTestLog("Verify all  " + convertAction + "  Files are Selected using Select All checkbox",
					" " + "All  " + convertAction + " Files not selected", Status.FAIL);
			e.printStackTrace();
		}
	}

	// verify toggle button is displayed
	public void verifyToggleButton() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, toggleButton)) {
				UIHelper.highlightElement(driver, toggleButton);
				UIHelper.click(driver, toggleButton);
				report.updateTestLog("Verify ToggleButton is displayed", " " + "ToggleButton should be displayed",
						Status.DONE);
			} else {
				report.updateTestLog("Verify ToggleButton is displayed", " " + "ToggleButton should not be displayed",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify ToggleButton is displayed", " " + "ToggleButton should not be displayed",
					Status.FAIL);
			e.printStackTrace();
		}

	}

	// verifyErrorValidationInTitle
	public void verifyErrorValidationInTitle(String convertAction, String invalidInput, String errMessage) {
		try {

			if (convertAction.equals("audio") || convertAction.equals("video")) {
				UIHelper.waitFor(driver);
				UIHelper.sendKeysToAnElementByXpath(driver, title, invalidInput);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, convertToStreamMedia);
				UIHelper.click(driver, convertToStreamMedia);
				UIHelper.waitForVisibilityOfEleByXpath(driver, errorPromptBox);
				if (UIHelper.getTextFromWebElement(driver, errorValidationMessage).equals(errMessage)) {
					report.updateTestLog("Error Message should be displayed for unsupported characters in Title field",
							" " + "Error message for Title Field in " + convertAction + "Tab is displayed",
							Status.PASS);
				} else {
					report.updateTestLog("Error Message should be displayed for unsupported characters in Title field",
							" " + "Error message for Title Field in " + convertAction + "Tab is not displayed",
							Status.FAIL);
				}
				UIHelper.click(driver, errorCloseButton);
			}
		}

		catch (Exception e) {
			report.updateTestLog("Error Message should be displayed for unsupported characters in Title field",
					" " + "Error message for Title Field in " + convertAction + "Tab is not displayed", Status.FAIL);
			e.printStackTrace();
		}

	}

	// verifyErrorValidationInReferenceID
	public void verifyErrorValidationInReferenceID(String convertAction, String invalidInput, String errMessage) {
		try {
			if (convertAction.equals("audio") || convertAction.equals("video")) {
				UIHelper.waitFor(driver);
				UIHelper.sendKeysToAnElementByXpath(driver, referenceID, invalidInput);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, convertToStreamMedia);
				UIHelper.click(driver, convertToStreamMedia);
				UIHelper.waitForVisibilityOfEleByXpath(driver, errorPromptBox);
				if (UIHelper.getTextFromWebElement(driver, errorValidationMessage).equals(errMessage)) {
					report.updateTestLog(
							"Error Message should be displayed for unsupported characters in ReferenceID field",
							" " + "Error message for ReferenceID Field in " + convertAction + "Tab is displayed",
							Status.PASS);
				} else {
					report.updateTestLog(
							"Error Message should be displayed for unsupported characters in ReferenceID field",
							" " + "Error message for ReferenceID Field in " + convertAction + "Tab is not displayed",
							Status.FAIL);
				}
				UIHelper.click(driver, errorCloseButton);
				UIHelper.waitFor(driver);
			}
		}

		catch (Exception e) {
			report.updateTestLog("Error Message should be displayed for unsupported characters in ReferenceID field",
					" " + "Error message for ReferenceID Field in " + convertAction + "Tab is not displayed",
					Status.FAIL);
			e.printStackTrace();
		}

	}

	// verifyBlankErrorValidationInTitle
	public void verifyBlankErrorValidationInTitle(String convertAction, String errMessage) {
		try {

			if (convertAction.equals("audio") || convertAction.equals("video")) {
				UIHelper.waitFor(driver);
				UIHelper.clearInputField(driver, title);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, convertToStreamMedia);
				UIHelper.click(driver, convertToStreamMedia);
				UIHelper.waitForVisibilityOfEleByXpath(driver, errorPromptBox);
				if (UIHelper.getTextFromWebElement(driver, errorValidationMessage).equals(errMessage)) {
					report.updateTestLog("Error Message should be displayed for Blank Input  in Title field",
							" " + "Error message for Title Field in " + convertAction + "Tab is displayed",
							Status.PASS);
				} else {
					report.updateTestLog("Error Message should be displayed for Blank Input in Title field",
							" " + "Error message for Title Field in " + convertAction + "Tab is not displayed",
							Status.FAIL);
				}
				UIHelper.click(driver, errorCloseButton);
				UIHelper.waitFor(driver);
			}
		}

		catch (Exception e) {
			report.updateTestLog("Error Message should be displayed for Blank Input in Title field",
					" " + "Error message for Title Field in " + convertAction + "Tab is not displayed", Status.FAIL);
			e.printStackTrace();
		}

	}

	// verifyReferenceIDisAutoPopulated
	public void verifyReferenceIDisAutoPopulated(String convertAction, String validInput, String errMessage,
			String fileName) {
		try {
			if (convertAction.equals("audio") || convertAction.equals("video")) {
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.sendKeysToAnElementByXpath(driver, title, validInput);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, savefield);
				UIHelper.click(driver, savefield);
				UIHelper.waitForVisibilityOfEleByXpath(driver, title);
				try {
					UIHelper.waitFor(driver);
					WebElement element1 = UIHelper.findAnElementbyXpath(driver, convert);
					UIHelper.scrollToAnElement(element1);

					if (UIHelper.checkForAnElementbyXpath(driver, convert)) {

						report.updateTestLog("Check whether Convert to streaming media button is displayed",
								" " + "Convert to streaming media button is displayed ", Status.PASS);
						UIHelper.waitForVisibilityOfEleByXpath(driver, convert);
						UIHelper.click(driver, convert);
						UIHelper.waitFor(driver);
						UIHelper.click(driver, alertAccept);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForLong(driver);
						UIHelper.pageRefresh(driver);
						if (convertAction.equals("audio")) {
							UIHelper.click(driver, streamingAudio);
						} else {
							UIHelper.click(driver, streamingVideo);

						}
						UIHelper.waitFor(driver);
						UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, activetab));
						UIHelper.waitForVisibilityOfEleByXpath(driver, fileNameLabel);
						clickListViewInToggle();
						UIHelper.waitFor(driver);

						WebElement tableElement = UIHelper.findAnElementbyXpath(driver, streamTableXpath);

						List<WebElement> cellItems = tableElement.findElements(By.tagName("td"));

						if (cellItems.get(1).getText().equals(fileName)) {

							if (cellItems.get(3).getText().equals(validInput)) {

								report.updateTestLog(
										"Verify Reference id is auto populated with Title in Streaming  "
												+ convertAction + " tab after transcoding",
										" " + "Reference id in " + convertAction + "Tab is auto populated",
										Status.PASS);

							} else {
								report.updateTestLog(
										"Verify Reference id is auto populated with Title in Streaming  "
												+ convertAction + " tab after transcoding",
										" " + "Reference id in " + convertAction + "Tab is Not auto populated",
										Status.FAIL);

							}

						}
					}
					clickGridViewInToggle();
				}

				catch (Exception e) {
					report.updateTestLog(
							"Verify Reference id is auto populated with Title in Streaming  " + convertAction
									+ " tab after transcoding",
							" " + "Reference id in " + convertAction + "Tab is Not auto populated", Status.FAIL);
					e.printStackTrace();
				}
			}
		}

		catch (Exception e) {
			report.updateTestLog(
					"Verify Reference id is auto populated with Title in Streaming  " + convertAction
							+ " tab after transcoding",
					" " + "Reference id in " + convertAction + "Tab is Not auto populated", Status.FAIL);
			e.printStackTrace();
		}

	}

	// Method to click On List View
	public void clickListViewInToggle() {
		verifyToggleButton();
		UIHelper.click(driver, selectListView);

	}

	// Method to click On Grid View
	public void clickGridViewInToggle() {

		verifyToggleButton();
		UIHelper.click(driver, selectGridView);
		UIHelper.waitFor(driver);

	}

	// verifyListView is selected
	public void verifyListView() {
		try {
			clickListViewInToggle();

			if (UIHelper.checkForAnElementbyXpath(driver, listViewMenu)) {
				report.updateTestLog("Verify user is able to select listview using View toggle.",
						"Listview should be selected and the files are displayed in list mode.", Status.PASS);
			} else
				report.updateTestLog("Verify user is able to select listview using View toggle.",
						"Listview should be selected and the files are not displayed in list mode.", Status.FAIL);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify user is able to select listview using View toggle.",
					"Listview should be selected and the files are not displayed in list mode.", Status.FAIL);
		}
	}

	// verifyGridView is selected
	public void verifyGridView() {
		try {
			clickGridViewInToggle();
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, gridViewMenu)) {
				report.updateTestLog("Verify user is able to select grid view  using View toggle.",
						"Gridview  should be selected and the files are displayed in Grid mode.", Status.PASS);
			} else
				report.updateTestLog("Verify user is able to select grid view  using View toggle.",
						"Gridview  should be selected and the files are not displayed in Grid mode.", Status.FAIL);
		} catch (Exception e) {
			report.updateTestLog("Verify user is able to select grid view  using View toggle.",
					"Gridview  should be selected and the files are not displayed in Grid mode.", Status.FAIL);
			e.printStackTrace();
		}
	}

	// method to doubleclickonThumbnail of mediainterface image
	public void doubleClickThumbnail() {

		UIHelper.waitForVisibilityOfEleByXpath(driver, thumbnailXpath);
		try {
			UIHelper.mouseOverandElementdoubleClick(driver, UIHelper.findAnElementbyXpath(driver, thumbnailXpath));
			UIHelper.switchtab(2, driver);
			report.updateTestLog("Verify user is able to do doubleClick in thumbnail.",
					"DoubleClick Event is Done and preview page is navigated.", Status.DONE);

		} catch (Exception e) {
			report.updateTestLog("Verify user is able to do doubleClick in thumbnail.",
					"DoubleClick Event is not Done and preview page is not navigated.", Status.FAIL);

		}

	}
	// method to clickonPortraiticon

	public void clickOnPortraitIcon() {
		UIHelper.waitForVisibilityOfEleByXpath(driver, portraitIconXpath);
		try {
			UIHelper.click(driver, portraitIconXpath);
			UIHelper.switchtab(2, driver);
			report.updateTestLog("Verify user is able to do Click in Portrait.",
					"Single Click Event is Done and preview page is navigated.", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Verify user is able to do Click in Portrait.",
					"Single Click Event is Done and preview page is not navigated.", Status.FAIL);
		}
	}

	// verifyPreviewPage is Navigated
	public void verifyPreviewPageNavigated(String convertAction) {

		try {
			if (convertAction.equals("audio") || convertAction.equals("video")) {
				if ((UIHelper.checkForAnElementbyXpath(driver, docActionXpath)
						&& UIHelper.checkForAnElementbyXpath(driver, docAudioPreview))
						|| (UIHelper.checkForAnElementbyXpath(driver, docActionXpath)
								&& UIHelper.checkForAnElementbyXpath(driver, docVideoPreview)))

				{

					report.updateTestLog(
							"Verify user is able to navigate to Preview Page of " + convertAction
									+ " by doubleClicking thumbnail or clicking portrait icon",
							" " + convertAction + "Preview Page is Navigated", Status.PASS);
				} else {
					report.updateTestLog(
							"Verify user is able to navigate to Preview Page of " + convertAction
									+ " by doubleClicking thumbnail or clicking portrait icon",
							" " + convertAction + "Preview Page is not Navigated", Status.FAIL);
				}
			}
		} catch (Exception e)

		{
			report.updateTestLog(
					"Verify user is able to navigate to Preview Page of " + convertAction
							+ " by doubleClicking thumbnail or clicking portrait icon",
					" " + convertAction + "Preview Page is not Navigated", Status.FAIL);
			e.printStackTrace();
		}

	}

	// method for input title
	public void enterValidTitleForUntranscodedFiles(String convertAction, String validInput) {
		int rno = (int) (Math.random() * 5000 + 1);
		try {
			if (convertAction.equals("audio") || convertAction.equals("video")) {
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.sendKeysToAnElementByXpath(driver, title, validInput + rno);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, savefield);
				UIHelper.click(driver, savefield);
				UIHelper.waitForVisibilityOfEleByXpath(driver, title);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Check whether Vaild Title is Entered",
						" " + "Valid Title is Successfully Entered into " + convertAction + "tab", Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check whether Vaild Title is Entered",
					" " + "Valid Title is not Successfully Entered into " + convertAction + "tab", Status.FAIL);
		}

	}

	// method to click on Streaming Media Button
	public void clickConvertToStreamMedia() {

		try {
			if (UIHelper.checkForAnElementbyXpath(driver, convert)) {

				WebElement element1 = UIHelper.findAnElementbyXpath(driver, convert);
				UIHelper.scrollToAnElement(element1);
				UIHelper.highlightElement(driver, convert);
				UIHelper.click(driver, convert);
				UIHelper.waitFor(driver);
				report.updateTestLog("Check whether Convert to streaming media button is displayed",
						" " + "Convert to streaming media button is displayed ", Status.DONE);
			}
		} catch (Exception e) {

			report.updateTestLog("Check whether Convert to streaming media button is displayed",
					" " + "Convert to streaming media button is not displayed ", Status.FAIL);
		}
	}

	// verifyValidationConversionMessage after clicking streaming button
	public void verifyValidationConversionMessage(String validMsg) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, errorValidationMessage);
			if (UIHelper.findAnElementbyXpath(driver, errorValidationMessage).getText().equals(validMsg)) {
				UIHelper.waitFor(driver);
				UIHelper.click(driver, alertAccept);
				report.updateTestLog("Check whether Success Message is displaying after Conversion",
						" " + "Success Message is displayed after Conversion ", Status.PASS);
			} else {
				report.updateTestLog("Check whether Success Message is displayed after Conversion",
						" " + "Success Message is not displayed after Conversion ", Status.FAIL);
			}
		} catch (Exception e) {

			report.updateTestLog("Check whether Success Message is displayed after Conversion",
					" " + "Success Message is not displayed after Conversion ", Status.FAIL);
		}

	}

	// method to wait,UntillTrancodeComplete
	public void waitUntillTrancodeComplete(String convertAction) {
		int count = 0;
		try {

			do {
				navigateToParticularTab(convertAction);
				verifyListView();
				if (UIHelper.checkForAnElementbyXpath(driver, inProgressImgtranscodedXpath)) {
					UIHelper.highlightElement(driver, inProgressImgtranscodedXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForLong(driver);
					UIHelper.pageRefresh(driver);
					count++;
				} else
					break;
			} while (count != 10);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// method to wait,UntillInprogress Icon appears
	public void waitUntillInProgressIcon(String convertAction) {
		int count = 0;
		try {

			do {
				navigateToParticularTab(convertAction);
				verifyListView();
				clickStatusFilterLeftPanel();
				clickInProgressFilterCheckbox();
				if (UIHelper.checkForAnElementbyXpath(driver, noDataforInprogressXpath)) {
					UIHelper.highlightElement(driver, noDataforInprogressXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForLong(driver);
					UIHelper.pageRefresh(driver);
					count++;
				} else
					break;
			} while (count != 10);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// verifyUserNavigatedToParticularTab
	public void verifyUserNavigatedToParticularTab(String tabName) {
		try {
			if (UIHelper.findAnElementbyXpath(driver, activetab).getText().equals(tabName)) {
				report.updateTestLog(
						"Verify the user is switched to the  " + tabName
								+ " tab on selecting untranscoded files on click of Convert Streaming Media button",
						"User switched to the  " + tabName + " tab of the Media Guide Interface ", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the user is switched to the  " + tabName
								+ " tab on selecting untranscoded files on click of Convert Streaming Media button",
						"User not switched to the  " + tabName + " tab of the Media Guide Interface ", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify the user is switched to the " + tabName
							+ " tab on selecting untranscoded files on click of Convert Streaming Media button",
					"User not switched to the  " + tabName + " tab of the Media Guide Interface ", Status.FAIL);
		}
	}

	// method to enter title for multifiles
	public void enterValidTitleForUntranscodedMultipleFiles(String convertAction, String value) {
		int rno = (int) (Math.random() * 5000 + 1);

		try {
			List<WebElement> validInput = UIHelper.findListOfElementsbyXpath(title, driver);

			for (WebElement titleInput : validInput) {
				UIHelper.sendKeysToAnWebElement(driver, titleInput, value + rno);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, savefield);
				UIHelper.click(driver, savefield);
				UIHelper.waitForVisibilityOfEleByXpath(driver, title);
				UIHelper.waitFor(driver);
				rno++;
			}
			report.updateTestLog("Check whether Vaild Title is Entered in All Files",
					" " + "Valid Title is Successfully Entered into " + convertAction + " tab" + "in All Files",
					Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Check whether Vaild Title is Entered in All Files",
					" " + "Valid Title is not Successfully Entered into " + convertAction + " tab" + "in All Files",
					Status.FAIL);
		}
	}

	// method to checkWhetherTitleFieldEditable

	public void checkWhetherTitleFieldEditable(String convertAction) {
		try {
			if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, title))) {
				report.updateTestLog("Check whether Title Field is Editable in " + convertAction + " Tab",
						" " + "Title Field should be Editable in " + convertAction + " Tab", Status.PASS);
			} else {
				report.updateTestLog("Check whether Title Field is Editable in " + convertAction + " Tab",
						" " + "Title Field should not be Editable in " + convertAction + " Tab", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Check whether Title Field is Editable in " + convertAction + " Tab",
					" " + "Title Field should not be Editable in " + convertAction + " Tab", Status.FAIL);
			e.printStackTrace();
		}
	}

	// method to checkWhetherTitleFieldNotEditable
	public void checkWhetherTitleFieldNotEditable(String convertAction) {
		try {
			if (!UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, title))) {
				report.updateTestLog("Check whether Title Field is not Editable in " + convertAction + " Tab",
						" " + "Title Field should not be Editable in " + convertAction + " Tab", Status.PASS);
			} else {
				report.updateTestLog("Check whether Title Field is not Editable in " + convertAction + " Tab",
						" " + "Title Field should  be Editable in " + convertAction + " Tab", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Check whether Title Field is not Editable in " + convertAction + " Tab",
					" " + "Title Field should  be Editable in " + convertAction + " Tab", Status.FAIL);
		}
	}
	// method to checkWhetherReferenceField is Editable

	public void checkWhetherReferenceFieldEditable(String convertAction) {
		try {
			if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, referenceID))) {
				report.updateTestLog("Check whether ReferenceID Field is Editable in " + convertAction + " Tab",
						" " + "ReferenceID Field should be Editable in " + convertAction + " Tab", Status.PASS);
			} else {
				report.updateTestLog("Check whether ReferenceID Field is Editable in " + convertAction + " Tab",
						" " + "ReferenceID Field should not be Editable in " + convertAction + " Tab", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Check whether ReferenceID Field is Editable in " + convertAction + " Tab",
					" " + "ReferenceID Field should not be Editable in " + convertAction + " Tab", Status.FAIL);
			e.printStackTrace();
		}
	}

	// method to checkWhetherReferenceField is not Editable
	public void checkWhetherReferenceFieldNotEditable(String convertAction) {
		try {
			if (!UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, referenceID))) {
				report.updateTestLog("Check whether ReferenceID Field is not Editable in " + convertAction + " Tab",
						" " + "ReferenceID Field should not be Editable in " + convertAction + " Tab", Status.PASS);
			} else {
				report.updateTestLog("Check whether ReferenceID Field is not Editable in " + convertAction + " Tab",
						" " + "ReferenceID Field should be Editable in " + convertAction + " Tab", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Check whether ReferenceID Field is not Editable in " + convertAction + " Tab",
					" " + "ReferenceID Field should be Editable in " + convertAction + " Tab", Status.FAIL);
		}
	}

	// method to verify TickCrossMarkInFields in title, reference ID
	public void verifyTickCrossMarkInFields(String convertAction, String fieldName) {
		try {
			if (fieldName.equals("TitleField"))

			{
				UIHelper.click(driver, title);
			} else if (fieldName.equals("ReferenceIDField")) {
				UIHelper.click(driver, referenceID);
			}
			List<WebElement> fieldButtons = UIHelper.findListOfElementsbyXpath(savefield, driver);
			if (UIHelper.chkForThisElementList(fieldButtons)) {
				report.updateTestLog(
						"Check whether Tick,Cross Mark in " + fieldName + " is Editable in " + convertAction + " Tab",
						" " + "Tick,Cross Mark in " + fieldName + " should be Editable in " + convertAction + " Tab",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Check whether Tick,Cross Mark in " + fieldName + " is not Editable in " + convertAction
								+ " Tab",
						" " + "Tick,Cross Mark in " + fieldName + " should not be Editable in " + convertAction
								+ " Tab",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Check whether Tick,Cross Mark in " + fieldName + " is not Editable in " + convertAction + " Tab",
					" " + "Tick,Cross Mark in " + fieldName + " should not be Editable in " + convertAction + " Tab",
					Status.FAIL);
			e.printStackTrace();
		}
	}

	// method to enterValidReferenceID
	public void enterValidReferenceID(String convertAction, String validInput) {
		int random = (int) (Math.random() * 4000 + 1);

		try {
			if (convertAction.equals("audio") || convertAction.equals("video")) {

				UIHelper.waitFor(driver);
				UIHelper.sendKeysToAnElementByXpath(driver, referenceID, validInput + random);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, savefield);
				UIHelper.click(driver, savefield);
				UIHelper.waitForVisibilityOfEleByXpath(driver, referenceID);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Valid Reference ID is entered into " + convertAction + "tab",
						" " + "Valid ReferenceID is Entered Successfully ", Status.DONE);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Valid Reference ID is entered into  " + convertAction + "tab",
					" " + "Valid ReferenceID is not Entered Successfully ", Status.FAIL);
		}

	}

	// method to trancodingMediaFiles by giving all inputs
	public void trancodingMediaFiles(String convertAction, String validInput, String confMsg) {
		try {
			String stampTime = Util.getCurrentFormattedTime(dateFormat);
			if (convertAction.equals("audio") || convertAction.equals("video")) {
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.sendKeysToAnElementByXpath(driver, title, validInput + stampTime);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, savefield);
				UIHelper.click(driver, savefield);
				UIHelper.waitForVisibilityOfEleByXpath(driver, title);
				try {
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					WebElement element1 = UIHelper.findAnElementbyXpath(driver, convert);
					UIHelper.scrollToAnElement(element1);

					if (UIHelper.checkForAnElementbyXpath(driver, convert)) {

						report.updateTestLog("Check whether Convert to streaming media button is displayed",
								" " + "Convert to streaming media button is displayed ", Status.PASS);
						UIHelper.waitForVisibilityOfEleByXpath(driver, convert);
						UIHelper.click(driver, convert);
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);

						verifyValidationConversionMessage(confMsg);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForLong(driver);
						UIHelper.pageRefresh(driver);
						if (convertAction.equals("audio")) {
							UIHelper.click(driver, streamingAudio);
						} else {
							UIHelper.click(driver, streamingVideo);
						}
					}
				} catch (Exception e) {
					report.updateTestLog("Check whether Convert to streaming media button is displayed",
							" " + "Convert to streaming media button is not displayed ", Status.FAIL);
				}
			}
		} catch (Exception e) {
			report.updateTestLog("Check whether Convert to streaming media button is displayed",
					" " + "Convert to streaming media button is not displayed ", Status.FAIL);
		}
	}

	// method to verifyListViewColumnsInUntrancodedMedia files
	public ArrayList<String> verifyListViewColumnsInUntrancodedMedia() {
		try {
			List<WebElement> listViewTable = UIHelper.findListOfElementsbyXpath(listViewTableColumnXpath, driver);
			for (WebElement ele : listViewTable) {
				listViewColumnName.add(ele.getText().trim());
			}
			report.updateTestLog("verify Columns Name Added in List",
					"ColumnsName are displayed in List View" + "<br/><b>Column Name List are:</b>" + listViewColumnName,
					Status.PASS);

		} catch (Exception ex) {
			report.updateTestLog("verify Columns Name Added in List",
					"ColumnsName are not displayed in List" + "<br/><b>Column Name List are:</b>" + listViewColumnName,
					Status.FAIL);
		}
		return listViewColumnName;

	}

	// method to verifyEditActionForUnTranscoded filesListView
	public void verifyEditActionForUnTranscodedListView(String convertAction, String field, String value) {
		try {
			if (field.equals("Title")) {
				String oldText = UIHelper.getTextFromWebElement(driver, listDataTableTitleXpath);

				UIHelper.mouseOverandElementdoubleClickSendKeys(driver,
						UIHelper.findAnElementbyXpath(driver, listDataTableTitleXpath), value + "edited");
				UIHelper.waitFor(driver);
				String newText = UIHelper.getTextFromWebElement(driver, listDataTableTitleXpath);

				if (!oldText.equals(newText)) {
					report.updateTestLog("Check whether Title Field is Editable in " + convertAction + " Tab",
							" " + "Title Field should be Editable in " + convertAction + " Tab", Status.PASS);
				} else {
					report.updateTestLog("Check whether Title Field is Editable in " + convertAction + " Tab",
							" " + "Title Field should not be Editable in " + convertAction + " Tab", Status.FAIL);
				}
			} else if (field.equals("ReferenceID")) {

				String oldText1 = UIHelper.getTextFromWebElement(driver, listDataTableReferenceIDXpath);
				UIHelper.mouseOverandElementdoubleClickSendKeys(driver,
						UIHelper.findAnElementbyXpath(driver, listDataTableReferenceIDXpath), value + "edited");
				UIHelper.waitFor(driver);
				String newText1 = UIHelper.getTextFromWebElement(driver, listDataTableReferenceIDXpath);
				if (!oldText1.equals(newText1))

				{
					report.updateTestLog("Check whether Reference Field is Editable in " + convertAction + " Tab",
							" " + "Reference Field should be Editable in " + convertAction + " Tab", Status.PASS);
				} else {
					report.updateTestLog("Check whether Reference Field is Editable in " + convertAction + " Tab",
							" " + "Reference Field should not be Editable in " + convertAction + " Tab", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check whether Reference & Title Field is Editable in " + convertAction + " Tab",
					" " + "Reference & Title Field should not be Editable in " + convertAction + " Tab", Status.FAIL);
		}

	}

	// method to verifyEditActionForTranscoded files ListView
	public void verifyEditActionForTranscodedListView(String convertAction, String field, String value) {
		try {
			if (field.equals("Title")) {
				String oldText = UIHelper.getTextFromWebElement(driver, listStreamDataTableTitleXpath);

				UIHelper.mouseOverandElementdoubleClickSendKeys(driver,
						UIHelper.findAnElementbyXpath(driver, listStreamDataTableTitleXpath), value + "edited");
				UIHelper.waitFor(driver);
				String newText = UIHelper.getTextFromWebElement(driver, listStreamDataTableTitleXpath);

				if (oldText.equals(newText)) {
					report.updateTestLog("Check whether Title Field is not Editable in " + convertAction + " Tab",
							" " + "Title Field should not be Editable in " + convertAction + " Tab", Status.PASS);
				} else {
					report.updateTestLog("Check whether Title Field is not Editable in " + convertAction + " Tab",
							" " + "Title Field is Editable in " + convertAction + " Tab", Status.FAIL);
				}
			} else if (field.equals("ReferenceID")) {

				String oldText1 = UIHelper.getTextFromWebElement(driver, listStreamDataTableReferenceIDXpath);
				UIHelper.mouseOverandElementdoubleClickSendKeys(driver,
						UIHelper.findAnElementbyXpath(driver, listStreamDataTableReferenceIDXpath), value + "edited");
				UIHelper.waitFor(driver);
				String newText1 = UIHelper.getTextFromWebElement(driver, listStreamDataTableReferenceIDXpath);
				if (oldText1.equals(newText1))

				{
					report.updateTestLog("Check whether Reference Field is not Editable in " + convertAction + " Tab",
							" " + "Reference Field should not be Editable in " + convertAction + " Tab", Status.PASS);
				} else {
					report.updateTestLog("Check whether Reference Field is not Editable in " + convertAction + " Tab",
							" " + "Reference Field is Editable in " + convertAction + " Tab", Status.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check whether Reference & Title Field is not Editable in " + convertAction + " Tab",
					" " + "Reference & Title Field are Editable in " + convertAction + " Tab", Status.FAIL);
		}

	}

	// method to verify ExtraFileAddedAfterTrancoding
	public void verifyExtraFileAddedAfterTrancoding(String convertAction, String fileNameValidate) {
		try {
			if (UIHelper.findAnElementbyXpath(driver, fileNameLabel).getText().equals(fileNameValidate)) {
				report.updateTestLog(
						"Verify a user can able to add extra " + convertAction + "files in " + convertAction
								+ " tab after transcoding",
						" " + "User can able to add extra " + convertAction + "files in " + convertAction
								+ " tab after transcoding ",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify a user can able to add extra " + convertAction + "files in " + convertAction
								+ " tab after transcoding",
						" " + "User can't able to add extra " + convertAction + "files in " + convertAction
								+ " tab after transcoding ",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify a user can able to add extra " + convertAction + "files in " + convertAction
							+ " tab after transcoding",
					" " + "User can't able to add extra " + convertAction + "files in " + convertAction
							+ " tab after transcoding ",
					Status.FAIL);
			e.printStackTrace();
		}
	}

	// Verify related files in audio tab is disabled.
	public void verifyRelatedFilesInStreamAudio() {

		try {
			String cssDisabledcolor = UIHelper.findAnElementbyXpath(driver, relatedFileXpath).getCssValue("color");
			String hex = Color.fromString(cssDisabledcolor).asHex();

			if (hex.equals("#a7a7a7")) {

				report.updateTestLog(
						"Verify a user can able to add related Files in Edit Streaming media Page of Audio File",
						" " + "User can't able to add related Files in Edit Streaming media Page of Audio File",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify a user can able to add related Files in Edit Streaming media Page of Audio File",
						" " + "User can able to add related Files in Edit Streaming media Page of Audio File",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify a user can able to add related Files in Edit Streaming media Page of Audio File",
					" " + "User can able to add related Files in Edit Streaming media Page of Audio File", Status.FAIL);
		}

	}

	// method to uploadRelatedFilesInStreamVideo
	public void uploadRelatedFilesInStreamVideo(String tobeupload[], String filePath, String fileName[]) {
		try {
			String finalFilePath = System.getProperty("user.dir");
			if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(driver, relatedFileXpath))) {
				UIHelper.highlightElement(driver, relatedFileXpath);
				UIHelper.click(driver, relatedFileXpath);
				UIHelper.waitFor(driver);
				for (String relupload : tobeupload) {
					if (relupload.equals("thumbnail")) {
						UIHelper.highlightElement(driver, thumbnailButtonXpath);
						UIHelper.findAnElementbyXpath(driver, thumbnailuploadXpath)
								.sendKeys(finalFilePath + filePath + fileName[0]);
						report.updateTestLog(
								"Verify a user can able to add Thumbnail Files in Edit Streaming media Page of Video File",
								" " + "User can able to add Thumbnail Files in Edit Streaming media Page of Video File",
								Status.PASS);

					} else if (relupload.equals("caption")) {
						UIHelper.highlightElement(driver, captionButonXpath);
						UIHelper.findAnElementbyXpath(driver, captionuploadXpath)
								.sendKeys(finalFilePath + filePath + fileName[1]);
						report.updateTestLog(
								"Verify a user can able to add caption Files in Edit Streaming media Page of Video File",
								" " + "User can able to add caption Files in Edit Streaming media Page of Video File",
								Status.PASS);
					} else if (relupload.equals("xml")) {
						UIHelper.highlightElement(driver, xmlButtonXpath);
						UIHelper.findAnElementbyXpath(driver, xmluploadXpath)
								.sendKeys(finalFilePath + filePath + fileName[2]);
						report.updateTestLog(
								"Verify a user can able to add xml Files in Edit Streaming media Page of Video File",
								" " + "User can able to add xml Files in Edit Streaming media Page of Video File",
								Status.PASS);
					}

				}
				UIHelper.findAnElementbyXpath(driver, saveUploadRelatedFileButton).click();
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, progressBarStatus);
				UIHelper.waitForVisibilityOfElementLocated(driver, successMsgRelativeFiles);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, successMsgOKbutton);
				UIHelper.waitForPageToLoad(driver);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify a user can able to add related Files in Edit Streaming media Page of Video File",
					" " + "User can't able to add related Files(Thumbnail,Caption,Xml) in Edit Streaming media Page of Video File",
					Status.FAIL);
		}
	}

	public String getTranscodedFiles() {
		return UIHelper.findAnElementbyXpath(driver, fileNameLabel).getText().trim();
	}

	public ArrayList<String> getListOfUnTrancodedFiles() {
		try {
			List<WebElement> transfiles = UIHelper.findListOfElementsbyXpath(fileNameLabel, driver);
			for (WebElement tranf : transfiles) {
				unTranscodedFiles.add(tranf.getText().trim());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unTranscodedFiles;

	}

	// method to get number of files displayed on bottom of media interface.
	public int getNumberOfFilesDisplayed() {

		int filesCount = 0;
		try {
			WebElement fileCount = UIHelper.findAnElementbyXpath(driver, fileNumberXpath);
			UIHelper.scrollToAnElement(fileCount);
			UIHelper.highlightElement(driver, fileCount);
			String fileCountSplit[] = UIHelper.findAnElementbyXpath(driver, fileNumberXpath).getText().split("\\.");

			if (fileCountSplit[0].contains("files found")) {
				report.updateTestLog("Verify Files Found is displayed in Media Guide Interface",
						" " + "Files Found is successfully displayed in Media Guide Interface", Status.PASS);
			} else {
				report.updateTestLog("Verify Files Found is displayed in Media Guide Interface",
						" " + "Files Found failed to displayed in Media Guide Interface", Status.FAIL);
			}
			filesCount = Integer.parseInt(fileCountSplit[0].replaceAll("\\D", ""));

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Files Found is displayed in Media Guide Interface",
					" " + "Files Found failed to displayed in Media Guide Interface", Status.FAIL);
		}
		return filesCount;
	}

	public String getReferenceIDFromMediaInterface() {
		System.out.println("RefID is " + UIHelper.findAnElementbyXpath(driver, referenceID).getAttribute("value"));
		return UIHelper.findAnElementbyXpath(driver, referenceID).getAttribute("value");

	}

	public String getTitleIDFromMediaInterface() {
		System.out.println("RefID is " + UIHelper.findAnElementbyXpath(driver, title).getAttribute("value"));
		return UIHelper.findAnElementbyXpath(driver, title).getAttribute("value");

	}

	// method to verify AlertForExistingReferenceID
	public void verifyAlertForExistingReferenceID(String convertAction, String existID, String validationMsg) {
		try {
			if (convertAction.equals("audio") || convertAction.equals("video")) {

				UIHelper.waitFor(driver);
				UIHelper.sendKeysToAnElementByXpath(driver, referenceID, existID);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, savefield);
				UIHelper.click(driver, savefield);
				UIHelper.waitForVisibilityOfEleByXpath(driver, errorPromptBox);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				if (UIHelper.findAnElementbyXpath(driver, errorValidationMessage).getText().contains(validationMsg)) {
					report.updateTestLog(
							"Verify an alert dialog box is displayed if the Reference ID already exists within ThePlatform in "
									+ convertAction + " " + "Tab",
							"Alert dialog box is successfully displayed in " + convertAction
									+ " Tab if the Reference ID already exists",
							Status.PASS);
				} else {
					report.updateTestLog(
							"Verify an alert dialog box is displayed if the Reference ID already exists within ThePlatform in "
									+ convertAction + " " + "Tab",
							"Alert dialog box is failed to displayed in " + convertAction
									+ " Tab if the Reference ID already exists",
							Status.FAIL);
				}
				UIHelper.click(driver, alertAccept);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify an alert dialog box is displayed if the Reference ID already exists within ThePlatform in "
							+ convertAction + " " + "Tab",
					"Alert dialog box is failed to displayed in " + convertAction
							+ " Tab if the Reference ID already exists",
					Status.FAIL);
			e.printStackTrace();
		}

	}

	public ArrayList<String> getTitleNameForSort() {
		try {
			List<WebElement> validInput = UIHelper.findListOfElementsbyXpath(title, driver);

			for (WebElement titleInput : validInput) {
				getTitleValues.add(titleInput.getAttribute("value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getTitleValues;
	}

	public void clickStatusFilterLeftPanel() {
		try {
			UIHelper.highlightElement(driver, leftPanelButtonxpath);
			UIHelper.click(driver, leftPanelButtonxpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getStatusFilterInLeftPanel() {
		try {
			clickStatusFilterLeftPanel();
			UIHelper.highlightElement(driver, leftPanelButtonxpath);
			UIHelper.click(driver, leftPanelButtonxpath);
			UIHelper.findandAddElementsToaList(driver, statusFilterSection, statusFilterItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusFilterItems;

	}

	public void clickInProgressFilterCheckbox() {
		try {
			// UIHelper.uncheckanElement(UIHelper.findAnElementbyXpath(driver,
			// inProgressStatusFilter));

			if (!UIHelper.isSelected(UIHelper.findAnElementbyXpath(driver, inProgressStatusFilter)))
				UIHelper.click(driver, inProgressStatusFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSuccessFilterCheckbox() {
		try {
			// UIHelper.uncheckanElement(UIHelper.findAnElementbyXpath(driver,
			// successStatusFilter));
			if (!UIHelper.isSelected(UIHelper.findAnElementbyXpath(driver, successStatusFilter)))
				UIHelper.click(driver, successStatusFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// verify folder is shared externally
	public void checkShareFolder(String folderName) {
		try {

			if (UIHelper.findAnElementbyXpath(driver, shareicon).getText().equals(folderName)) {
				report.updateTestLog("Check whether Folder <b>" + folderName + "</b>  is Externally Shared",
						" " + "Folder :  <b>" + folderName + "</b> is Shared Externally", Status.PASS);
			} else {
				report.updateTestLog("Check whether Folder <b>" + folderName + "</b>  is Externally Shared",
						" " + "Folder :  <b>" + folderName + "</b> is not Shared Externally", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Check whether Folder <b>" + folderName + "</b>  is Externally Shared",
					" " + "Folder :  <b>" + folderName + "</b> is not Shared Externally", Status.FAIL);
			e.printStackTrace();
		}

	}

	// verify tab are present in media guide interface.

	public boolean checktab(String video, String streamingVideo, String audio, String streamingAudio, String all) {
		if (!UIHelper.checkForAnElementbyXpath(driver, video)) {
			return false;

		}

		if (!UIHelper.checkForAnElementbyXpath(driver, streamingVideo)) {

			return false;
		}

		if (!UIHelper.checkForAnElementbyXpath(driver, audio)) {

			return false;

		}

		if (!UIHelper.checkForAnElementbyXpath(driver, streamingAudio)) {

			return false;
		}

		if (!UIHelper.checkForAnElementbyXpath(driver, all)) {
			return false;
		}

		return true;

	}

}
