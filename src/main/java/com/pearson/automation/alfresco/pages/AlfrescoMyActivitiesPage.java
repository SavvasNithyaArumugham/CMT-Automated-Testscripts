package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.javascript.host.arrays.Uint16Array;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * 
 * @author 412766
 * 
 */
public class AlfrescoMyActivitiesPage extends ReusableLibrary {

	private String discussionAddNewTopicButtonXpath = ".//*[@id='template_x002e_toolbar_x002e_discussions-topiclist_x0023_default-create-button-button']";
	private String discussionTitleTextBoxXpath = ".//*[@id='template_x002e_createtopic_x002e_discussions-createtopic_x0023_default-title']";
	private String discussionTagTextBoxXpath = ".//*[@id='template_x002e_createtopic_x002e_discussions-createtopic_x0023_default-tag-input-field']";
	private String discussionSaveButtonXpath = ".//*[@id='template_x002e_createtopic_x002e_discussions-createtopic_x0023_default-submit-button']";
	private String discussionAlertXpath = ".//*[@id='message']/div";
	private String discussionCreatedNameXpath = ".//*[@id='template_x002e_topic_x002e_discussions-topicview_x0023_default-topicview']/div[3]/div[1]/a";

	private String discussionsListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='topiclist yui-dt']//table//*[@class='yui-dt-data']//tr//td";
	private String deleteDiscussionLinkXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@class='topiclist yui-dt']//table//*[@class='yui-dt-data']//tr//td//div//div//div[3]/a";
	private String deleteDiscusionButtonXpath = ".//*[@class='button-group']//*[@class='first-child']//*[contains(text(),'Delete')]";

	private String wikipageAddNewTopicButtonXpath = ".//*[@id='template_x002e_toolbar_x002e_wiki-page_x0023_default-create-button-button']";
	private String wikipageTitleTextBoxXpath = ".//*[@id='template_x002e_createform_x002e_wiki-create_x0023_default-title']";
	private String wikipageTagTextBoxXpath = ".//*[@id='template_x002e_createform_x002e_wiki-create_x0023_default-tag-input-field']";
	private String wikipageSaveButtonXpath = ".//*[@id='template_x002e_createform_x002e_wiki-create_x0023_default-save-button-button']";
	private String wikipageCreatedNameXpath = ".//*[@id='template_x002e_wikipage_x002e_wiki-page_x0023_default-viewButtons']";

	private String wikiPageListButtonXpath = ".//*[@id='template_x002e_toolbar_x002e_wiki-page_x0023_default-body']/div[1]/div";
	private String wikiListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='alfresco-wiki']//*[@id='yui-main']//*[contains(@id,'_wiki_x0023_default-pagelist')]/div";
	private String deleteWikiLinkXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='alfresco-wiki']//*[@id='yui-main']//*[contains(@id,'_wiki_x0023_default-pagelist')]//div//div[1]//div[3]//a";
	private String deleteWikiButtonXpath = ".//*[@class='button-group']//*[@class='first-child']//*[contains(text(),'Delete')]";

	private String editWikiLinkXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='alfresco-wiki']//*[@id='yui-main']//*[contains(@id,'_wiki_x0023_default-pagelist')]//div//div[1]//div[1]//a";
	private String editWikiRenameButtonXpath = ".//*[@id='template_x002e_toolbar_x002e_wiki-page_x0023_default-rename-button-button']";
	private String editWikiRenameTextBoxXpath = ".//*[@id='template_x002e_toolbar_x002e_wiki-page_x0023_default-renameTo']";
	private String editWikiSaveButtonXpath = ".//*[@id='template_x002e_toolbar_x002e_wiki-page_x0023_default-rename-save-button-button']";

	private String blogAddNewTopicButtonXpath = ".//*[@id='template_x002e_toolbar_x002e_blog-postlist_x0023_default-create-button-button']";
	private String blogTitleTextBoxXpath = ".//*[@id='template_x002e_postedit_x002e_blog-postedit_x0023_default-title']";
	private String blogTagTextBoxXpath = ".//*[@id='template_x002e_postedit_x002e_blog-postedit_x0023_default-tag-input-field']";
	private String blogPublishButtonXpath = ".//*[@id='template_x002e_postedit_x002e_blog-postedit_x0023_default-publish-button-button']";
	private String blogCreatedNameXpath = ".//*[@id='template_x002e_postview_x002e_blog-postview_x0023_default-postview']/div[2]/div[1]/a";

	private String blogListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='yui-main']//table//*[@class='yui-dt-data']//tr//td";
	private String deleteBlogLinkXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='yui-main']//*[@class='yui-dt-data']//tr//td//div//div[1]//div[1]//div[2]//a";
	private String deleteBlogButtonXpath = ".//*[@class='button-group']//*[@class='first-child']//*[contains(text(),'Delete')]";

	private String calendarAddNewEventButtonXpath = ".//*[@id='template_x002e_toolbar_x002e_calendar_x0023_default-addEvent-button-button']";
	private String calendarTitleTextBoxXpath = ".//*[@id='eventEditPanel-title']";
	private String calendarTagTextBoxXpath = ".//*[@id='eventEditPanel-tag-input-field']";
	private String calendarOkButtonXpath = ".//*[@id='eventEditPanel-ok-button']";
	private String calenderCreatedNameXpath = ".//*[contains(text(),'CRAFT')]";

	private String calendarEventNameXapth = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='alfresco-calendar']//*[@id='yui-main']//*[contains(@class,'fc-event-inner')]//*[contains(text(),'CRAFT')]";
	private String calendarDeleteButtonXpath = ".//*[@id='template_x002e_view_x002e_calendar_x0023_defaultContainer-delete-button-button']";
	private String calendarConfirmDeleteButtonXpath = ".//*[@class='button-group']//*[@class='first-child']//*[contains(text(),'Delete')]";

	private String calendarEditButtonXpath = ".//*[@id='template_x002e_view_x002e_calendar_x0023_defaultContainer-edit-button-button']";

	private String calEventNameFilePath = "src/test/resources/AppTestData/TestOutput/CalendarEvent.txt";
	private String editPageXPath = ".//*[@class='tabLabel' and contains(.,'Edit')]";

	private String editSaveBtnXapth = ".//*[@class='buttons']//button[contains(.,'Save')]";
	private String viewMenuXapth = "//span[text()='View']";
	private String sourceCodeOptionXapth = ".//span[text()='Source code']";
	private String sourceCodeLayoutXapth = ".//textarea[contains(@class,'mce-abs-layout-item')]";
	private String buttonOKXpath = ".//button[text()='Ok']";
	private String latestVersionBtnXpath = ".//*[contains(@id,'default-selectVersion-button-button')]";
	private String latestVersionListXpath = ".//*[@class='version-quick-change']//*[@class='yuimenuitemlabel']";
	private String discussionNameXpath = ".//*[@class='nodeContent']//a[contains(.,'CRAFT')]";
	private String linkDelXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='yui-main']//table//*[@class='yui-dt-data']//tr//td//*[@class='delete-link']//span";
	private String newLinkXpath = ".//*[@class='createLink']//button";
	private String linkTitleXpath = ".//*[@class='page-form-body']//form//label[contains(.,'Title')]//ancestor::*[@class='yui-gd']//input";
	private String linkURLXpath = ".//*[@class='page-form-body']//form//label[contains(.,'URL')]//ancestor::*[@class='yui-gd']//input";
	private String linkDescXpath = ".//*[@class='page-form-body']//form//label[contains(.,'Description')]//ancestor::*[@class='yui-gd']//textarea";
	private String linkTagXpath = ".//*[@class='page-form-body']//form//label[contains(.,'Tags')]//ancestor::*[@class='yui-gd']//input";
	private String linkSaveBtnXpath = ".//*[@name='page']//following-sibling::div//button[contains(.,'Save')]";
	private String linkListNameXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='yui-main']//table//*[@class='yui-dt-data']//tr//td//*[@class='link-title' and contains(.,'CRAFT')]";
	private String savedLinkXpath = ".//*[@class='nodeTitle']//a";
	private String linkNameListXpath = ".//*[@class='sticky-wrapper']//*[@id='bd']//*[@id='yui-main']//table//*[@class='yui-dt-data']//tr//td//*[@class='link-title']";

	private String wikiEditButtonXpath = ".//*[@class='pageTitle']//a[contains(.,'CRAFT')]//ancestor::div[@class='wikipage']//*[@class='editPage']//a";
	private String wikiDetailsButtonXpath = ".//*[@class='tabLabel' and contains(.,'Details')]";
	private String pageTitleXpath = ".//*[@class='pageTitle' and contains(.,'CRAFT')]";
	private String wikiPageListXpath = ".//*[@class='forwardLink' and contains(.,'Wiki Page List')]";
	private String MainPgXpath = ".//*[@class='forwardLink' and contains(.,'Main Page')]";
	private String pageTitleTextXpath = ".//*[@class='pageTitle' and contains(.,'CRAFT')]//a";

	public AlfrescoMyActivitiesPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	/**
	 * @author 412766
	 */
	public void startNewDiscussion() {
		try {

			String title = dataTable.getData("MyActivities", "TitleName");

			createMyActivityTask(discussionAddNewTopicButtonXpath,
					discussionTitleTextBoxXpath, discussionTagTextBoxXpath,
					discussionSaveButtonXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					discussionCreatedNameXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, discussionCreatedNameXpath))) {
				UIHelper.highlightElement(driver, discussionCreatedNameXpath);
				report.updateTestLog("Start New Discussion",
						"New Discussion started successfully"
								+ "<br /><b> Discussion Name : </b>" + title,
						Status.PASS);
			} else {
				report.updateTestLog("Start New Discussion",
						"New Discussion not started"
								+ "<br /><b> Discussion Name : </b>" + title,
						Status.FAIL);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Start New Discussion",
					"Start New Discussion Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void createWikiPage() {
		try {

			createMyActivityTask(wikipageAddNewTopicButtonXpath,
					wikipageTitleTextBoxXpath, wikipageTagTextBoxXpath,
					wikipageSaveButtonXpath);
			String title = new FileUtil()
					.readDataFromFile(calEventNameFilePath);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					wikipageCreatedNameXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, wikipageCreatedNameXpath))) {
				UIHelper.highlightElement(driver, wikipageCreatedNameXpath);
				report.updateTestLog("Create New Wiki Page",
						"New Wiki Page created successfully"
								+ "<br /><b> Wiki Page Name : </b>" + title,
						Status.PASS);
			} else {
				report.updateTestLog("Create New Wiki Page",
						"New Wiki Page not created"
								+ "<br /><b> Wiki Page Name : </b>" + title,
						Status.FAIL);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Create New Wiki Page",
					"Create New Wiki Page Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void createBlog() {
		try {

			String title = dataTable.getData("MyActivities", "TitleName");

			createMyActivityTask(blogAddNewTopicButtonXpath,
					blogTitleTextBoxXpath, blogTagTextBoxXpath,
					blogPublishButtonXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, blogCreatedNameXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, blogCreatedNameXpath))) {
				UIHelper.highlightElement(driver, blogCreatedNameXpath);
				report.updateTestLog("Create New Blog",
						"New Blog created successfully"
								+ "<br /><b> Blog Name : </b>" + title,
						Status.PASS);
			} else {
				report.updateTestLog("Create New Blog", "New Blog not created"
						+ "<br /><b> Blog Name : </b>" + title, Status.FAIL);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Create New Blog", "Create New Blog Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void createCalendarEvent() {
		try {

			String title = dataTable.getData("MyActivities", "TitleName");

			createMyActivityTask(calendarAddNewEventButtonXpath,
					calendarTitleTextBoxXpath, calendarTagTextBoxXpath,
					calendarOkButtonXpath);

			try {
				title = new FileUtil().readDataFromFile(calEventNameFilePath);

			} catch (Exception e) {
			}

			calenderCreatedNameXpath = calenderCreatedNameXpath.replace(
					"CRAFT", title);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					calenderCreatedNameXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, calenderCreatedNameXpath))) {
				UIHelper.highlightElement(driver, calenderCreatedNameXpath);
				report.updateTestLog("Create New Calendar Event",
						"New Calendar Event created successfully"
								+ "<br /><b> Event Name : </b>" + title,
						Status.PASS);
			} else {
				report.updateTestLog("Create New Calendar Event",
						"New Calendar Event not created"
								+ "<br /><b> Event Name : </b>" + title,
						Status.FAIL);
			}

			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

		} catch (Exception e) {
			report.updateTestLog("Create New Calendar Event",
					"Create New Calendar Event Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param addNewXpath
	 * @param titleXpath
	 * @param tagXpath
	 * @param savebutton
	 */
	private void createMyActivityTask(String addNewXpath, String titleXpath,
			String tagXpath, String savebutton) {

		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, addNewXpath);
		UIHelper.highlightElement(driver, addNewXpath);
		UIHelper.click(driver, addNewXpath);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);

		String title = dataTable.getData("MyActivities", "TitleName");
		String tag = dataTable.getData("MyActivities", "Tag");
		String menuToNavigate = dataTable.getData("Sites",
				"CustomizeSiteMenuToNavigate");

		if (menuToNavigate.equalsIgnoreCase("Wiki")
				|| menuToNavigate.equalsIgnoreCase("Calendar")) {
			String currentDateValue = DateUtil.getCurrentDateAndTime();
			title = title
					+ currentDateValue.replace(" ", "").replace("/", "_")
							.replace(":", "_");
			new FileUtil().writeTextToFile(title, calEventNameFilePath);
		}

		UIHelper.waitForVisibilityOfEleByXpath(driver, titleXpath);
		UIHelper.highlightElement(driver, titleXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
		UIHelper.waitForVisibilityOfEleByXpath(driver, tagXpath);
		UIHelper.highlightElement(driver, tagXpath);
		UIHelper.sendKeysToAnElementByXpath(driver, tagXpath, tag);

		UIHelper.waitForVisibilityOfEleByXpath(driver, savebutton);
		UIHelper.highlightElement(driver, savebutton);
		UIHelper.click(driver, savebutton);

		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
				discussionAlertXpath);
		UIHelper.waitForPageToLoad(driver);

		UIHelper.waitFor(driver);
	}

	/**
	 * @author 412766
	 */
	public void deleteDiscussion() {
		try {
			UIHelper.waitFor(driver);

			List<WebElement> deleteDiscussionList = UIHelper
					.findListOfElementsbyXpath(discussionsListXpath, driver);
			List<WebElement> deleteLinkList = UIHelper
					.findListOfElementsbyXpath(deleteDiscussionLinkXpath,
							driver);
			System.out.println("DISCUSSION VALUE LIST SIZE: "
					+ deleteDiscussionList.size());
			System.out.println("DISCUSSION DELETE LIST SIZE: "
					+ deleteLinkList.size());
			String title = dataTable.getData("MyActivities", "TitleName");
			int index = 0;
			for (WebElement webElement : deleteDiscussionList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					String myActivityText = webElement.getText();
					if (myActivityText.contains(title)) {
						UIHelper.highlightElement(driver, webElement);
						System.out.println("DISCUSSION VALUE : "
								+ myActivityText);
						UIHelper.highlightElement(driver,
								deleteLinkList.get(index));
						UIHelper.clickanElement(deleteLinkList.get(index));
						UIHelper.waitFor(driver);
						UIHelper.highlightElement(driver,
								deleteDiscusionButtonXpath);
						UIHelper.click(driver, deleteDiscusionButtonXpath);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
								discussionAlertXpath);
						UIHelper.waitFor(driver);

						report.updateTestLog("Delete Discussion",
								"Discussion deleted successfully"
										+ "<br /><b> Discussion Name : </b>"
										+ title, Status.PASS);
						break;
					}
				}
				index++;
			}

		} catch (Exception e) {
			report.updateTestLog("Delete Discussion",
					"Delete Discussion Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void deleteBlogPost() {
		try {
			String title = dataTable.getData("MyActivities", "TitleName");
			boolean statusFlag = deleteMyActivityTask(blogListXpath,
					deleteBlogLinkXpath, deleteBlogButtonXpath, title);
			if (statusFlag) {
				report.updateTestLog("Delete Blog Post",
						"Blog Post deleted successfully"
								+ "<br /><b> Blog Name : </b>" + title,
						Status.PASS);
			}

		} catch (Exception e) {
			report.updateTestLog("Delete Blog Post", "Delete Blog Post Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void deleteWikiPage() {
		try {
			navigateToWikiPageList();

			String title = dataTable.getData("MyActivities", "TitleName");
			String titleActual = new FileUtil()
					.readDataFromFile(calEventNameFilePath);
			boolean statusFlag = deleteMyActivityTask(wikiListXpath,
					deleteWikiLinkXpath, deleteWikiButtonXpath, title);
			if (statusFlag) {
				report.updateTestLog("Delete Wiki Page",
						"Wiki Page deleted successfully"
								+ "<br /><b> Wiki Page Name : </b>"
								+ titleActual, Status.PASS);
			} else {
				report.updateTestLog("Delete Wiki Page",
						"Wiki Page not deleted"
								+ "<br /><b> Wiki Page Name : </b>"
								+ titleActual, Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Delete Wiki Page", "Delete Wiki Page Failed",
					Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void navigateToWikiPageList() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					wikiPageListButtonXpath);
			UIHelper.highlightElement(driver, wikiPageListButtonXpath);
			UIHelper.click(driver, wikiPageListButtonXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Navigate to Wiki Page list",
					"Nagivated successfully", Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Navigate to Wiki Page list",
					"Navigate to Wiki Page list Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param activityListXapth
	 */
	private boolean deleteMyActivityTask(String activityListXapth,
			String activityDeleteListXpath, String confirmDeleteXpath,
			String title) {

		boolean flag = false;
		try {
			UIHelper.waitFor(driver);

			List<WebElement> deleteDiscussionList = UIHelper
					.findListOfElementsbyXpath(activityListXapth, driver);
			List<WebElement> deleteLinkList = UIHelper
					.findListOfElementsbyXpath(activityDeleteListXpath, driver);
			System.out.println("ACTIVITY VALUE LIST SIZE: "
					+ deleteDiscussionList.size());
			System.out.println("ACTIVITY DELETE LIST SIZE: "
					+ deleteLinkList.size());
			int index = 0;
			for (WebElement webElement : deleteDiscussionList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					String myActivityText = webElement.getText();
					System.out.println("ACTIVITY VALUE : " + myActivityText);
					System.out.println("TITLE VALUE : " + title);
					if (myActivityText.contains(title.trim())) {
						UIHelper.highlightElement(driver, webElement);
						System.out.println("INISDE ACTIVITY VALUE : "
								+ myActivityText);
						UIHelper.highlightElement(driver,
								deleteLinkList.get(index));
						UIHelper.clickanElement(deleteLinkList.get(index));
						UIHelper.waitFor(driver);
						UIHelper.highlightElement(driver, confirmDeleteXpath);
						UIHelper.click(driver, confirmDeleteXpath);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
								discussionAlertXpath);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitFor(driver);
						flag = true;
						break;
					}
				}
				index++;
			}
		} catch (Exception e) {

		}

		return flag;
	}

	/**
	 * @author 412766
	 */
	public void deleteCalendarEvent() {
		try {
			String title = new FileUtil()
					.readDataFromFile(calEventNameFilePath);
			String finalEventXapth = calendarEventNameXapth.replace("CRAFT",
					title);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalEventXapth);
			UIHelper.highlightElement(driver, finalEventXapth);
			UIHelper.click(driver, finalEventXapth);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					calendarDeleteButtonXpath);
			UIHelper.highlightElement(driver, calendarDeleteButtonXpath);
			UIHelper.click(driver, calendarDeleteButtonXpath);

			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					calendarConfirmDeleteButtonXpath);
			UIHelper.highlightElement(driver, calendarConfirmDeleteButtonXpath);
			UIHelper.click(driver, calendarConfirmDeleteButtonXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					discussionAlertXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Delete Calendar Event",
					"Calendar Event deleted successfully"
							+ "<br /><b> Calendar Event Name : </b>" + title,
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Delete Calendar Event",
					"Delete Calendar Event Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void editCalendarEvent() {
		try {
			String title = new FileUtil()
					.readDataFromFile(calEventNameFilePath);
			String finalEventXapth = calendarEventNameXapth.replace("CRAFT",
					title);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalEventXapth);
			UIHelper.highlightElement(driver, finalEventXapth);
			UIHelper.click(driver, finalEventXapth);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					calendarEditButtonXpath);
			UIHelper.highlightElement(driver, calendarEditButtonXpath);
			UIHelper.click(driver, calendarEditButtonXpath);

			UIHelper.waitFor(driver);

			String tag = dataTable.getData("MyActivities", "Tag");
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					calendarTagTextBoxXpath);
			UIHelper.highlightElement(driver, calendarTagTextBoxXpath);
			UIHelper.sendKeysToAnElementByXpath(driver,
					calendarTagTextBoxXpath, tag+"Edit");

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					calendarOkButtonXpath);
			UIHelper.highlightElement(driver, calendarOkButtonXpath);
			UIHelper.click(driver, calendarOkButtonXpath);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Edit Calendar Event",
					"Calendar Event edited successfully"
							+ "<br /><b> Calendar Event Name : </b>" + title+"Edit",
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Edit Calendar Event",
					"Edit Calendar Event Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void renameWikiPage() {
		try {
			navigateToWikiPageList();

			String title = dataTable.getData("MyActivities", "TitleName");
			String titleActual = new FileUtil()
					.readDataFromFile(calEventNameFilePath);

			List<WebElement> deleteDiscussionList = UIHelper
					.findListOfElementsbyXpath(wikiListXpath, driver);
			List<WebElement> deleteLinkList = UIHelper
					.findListOfElementsbyXpath(editWikiLinkXpath, driver);
			System.out.println("ACTIVITY VALUE LIST SIZE: "
					+ deleteDiscussionList.size());
			System.out.println("ACTIVITY DELETE LIST SIZE: "
					+ deleteLinkList.size());
			int index = 0;

			for (WebElement webElement : deleteDiscussionList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					UIHelper.scrollToAnElement(webElement);
					String myActivityText = webElement.getText();
					System.out.println("ACTIVITY VALUE : " + myActivityText);
					System.out.println("TITLE VALUE : " + title);
					if (myActivityText.contains(title.trim())) {
						UIHelper.highlightElement(driver, webElement);
						System.out.println("INISDE ACTIVITY VALUE : "
								+ myActivityText);
						UIHelper.highlightElement(driver,
								deleteLinkList.get(index));
						UIHelper.clickanElement(deleteLinkList.get(index));
						UIHelper.waitFor(driver);

						UIHelper.waitForPageToLoad(driver);

						UIHelper.waitForVisibilityOfEleByXpath(driver,
								editWikiRenameButtonXpath);
						UIHelper.highlightElement(driver,
								editWikiRenameButtonXpath);
						UIHelper.click(driver, editWikiRenameButtonXpath);

						UIHelper.waitFor(driver);

						UIHelper.waitForVisibilityOfEleByXpath(driver,
								editWikiRenameTextBoxXpath);
						UIHelper.highlightElement(driver,
								editWikiRenameTextBoxXpath);
						UIHelper.sendKeysToAnElementByXpath(driver,
								editWikiRenameTextBoxXpath, titleActual + " 1");

						UIHelper.waitForVisibilityOfEleByXpath(driver,
								editWikiSaveButtonXpath);
						UIHelper.highlightElement(driver,
								editWikiSaveButtonXpath);
						UIHelper.click(driver, editWikiSaveButtonXpath);

						UIHelper.waitFor(driver);
						UIHelper.waitForPageToLoad(driver);

						UIHelper.waitForVisibilityOfEleByXpath(driver,
								wikipageCreatedNameXpath);
						if (UIHelper.isWebElementDisplayed(UIHelper
								.findAnElementbyXpath(driver,
										wikipageCreatedNameXpath))) {
							UIHelper.highlightElement(driver,
									wikipageCreatedNameXpath);
							report.updateTestLog(
									"Rename Wiki Page",
									"Wiki Page renamed successfully"
											+ "<br /><b> Renamed Wiki Page Name : </b>"
											+ titleActual + "_1", Status.PASS);
						} else {
							report.updateTestLog(
									"Rename Wiki Page",
									"Wiki Page not renamed"
											+ "<br /><b> Renamed Wiki Page Name : </b>"
											+ titleActual + "_1", Status.FAIL);
						}

						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);

						break;
					}
				}
			}

		} catch (Exception e) {
			report.updateTestLog("Rename Wiki Page", "Rename Wiki Page Failed",
					Status.FAIL);
		}
	}

	// Verify claendar event
	public void verifyCalaendarEvent() {

		try {

			String title = dataTable.getData("MyActivities", "TitleName");

			try {
				title = new FileUtil().readDataFromFile(calEventNameFilePath);

			} catch (Exception e) {
			}

			calenderCreatedNameXpath = calenderCreatedNameXpath.replace(
					"CRAFT", title);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					calenderCreatedNameXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, calenderCreatedNameXpath))) {
				UIHelper.highlightElement(driver, calenderCreatedNameXpath);
				report.updateTestLog("Verify New Calendar Event",
						"Calendar event is displayed", Status.PASS);

			} else {
				report.updateTestLog("Verify New Calendar Event",
						"Calendar event is not displayed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify New Calendar Event",
					"Create New Calendar Event Failed", Status.FAIL);

		}

	}

	// Verify wiki page
	public void verifyWikiPage() {
		try {
			String title = new FileUtil()
					.readDataFromFile(calEventNameFilePath);
			String finalTitlexpath = pageTitleXpath.replace("CRAFT", title)
					.replace("_", " ");
			UIHelper.waitForVisibilityOfEleByXpath(driver, wikiPageListXpath);
			UIHelper.click(driver, wikiPageListXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, MainPgXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, finalTitlexpath))) {
				UIHelper.highlightElement(driver, finalTitlexpath);
				report.updateTestLog("Verify New Wiki Page",
						"New Wiki Page displayed successfully"
								+ "<br /><b> Wiki Page Name : </b>" + title,
						Status.PASS);
			} else {
				report.updateTestLog("Verify New Wiki Page",
						"New Wiki Page is not displayed"
								+ "<br /><b> Wiki Page Name : </b>" + title,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify New Wiki Page",
					"Failed to display wiki page", Status.FAIL);
		}
	}

	// Edit wiki page
	public void editWikiPage(String content) {
		try {

			String title = new FileUtil()
					.readDataFromFile(calEventNameFilePath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, wikiPageListXpath);
			UIHelper.click(driver, wikiPageListXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, MainPgXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalTitlexpath = pageTitleTextXpath.replace("CRAFT", title)
					.replace("_", " ");
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalTitlexpath);
			UIHelper.highlightElement(driver, finalTitlexpath);
			UIHelper.click(driver, finalTitlexpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editPageXPath);
			UIHelper.click(driver, editPageXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			editHtmlContent(content);
			UIHelper.waitForVisibilityOfEleByXpath(driver, editSaveBtnXapth);
			UIHelper.highlightElement(driver, editSaveBtnXapth);
			UIHelper.click(driver, editSaveBtnXapth);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					discussionAlertXpath);
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Edit wiki page",
					"Wiki Page Edited successfully"
							+ "<br /><b> Wiki Page Name : </b>" + title,
					Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
			String title;
			try {
				title = new FileUtil().readDataFromFile(calEventNameFilePath);
				String finalWikiEditButtonXpath = wikiEditButtonXpath.replace(
						"CRAFT", title).replace("_", " ");
				report.updateTestLog("Edit wiki page",
						"Failed to edit Wiki Page " + finalWikiEditButtonXpath,
						Status.FAIL);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	// Edit Html content
	public void editHtmlContent(String content) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, viewMenuXapth);
			UIHelper.highlightElement(driver, viewMenuXapth);
			UIHelper.click(driver, viewMenuXapth);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, sourceCodeOptionXapth);
			UIHelper.click(driver, sourceCodeOptionXapth);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					sourceCodeLayoutXapth);
			UIHelper.highlightElement(driver, sourceCodeLayoutXapth);
			UIHelper.sendKeysToAnElementByXpath(driver, sourceCodeLayoutXapth,
					content);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, buttonOKXpath);
			UIHelper.click(driver, buttonOKXpath);

			report.updateTestLog("Edit Html comtent",
					"HTML content edited successfully", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Edit Html comtent",
					"Failed to edit HTML content", Status.FAIL);
		}
	}

	// Check latest version of wiki page
	public void checkLatestVersion(String version) {
		try {
			ArrayList<String> versionListFrmDetailsPg = new ArrayList<String>();
			String[] versionList = null;
			ArrayList<String> finalVersionList = new ArrayList<String>();
			
			String title = new FileUtil()
					.readDataFromFile(calEventNameFilePath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, wikiPageListXpath);
			UIHelper.click(driver, wikiPageListXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, MainPgXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalTitlexpath = pageTitleTextXpath.replace("CRAFT", title)
					.replace("_", " ");
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalTitlexpath);
			UIHelper.highlightElement(driver, finalTitlexpath);
			UIHelper.click(driver, finalTitlexpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					wikiDetailsButtonXpath);
			UIHelper.click(driver, wikiDetailsButtonXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					latestVersionBtnXpath);
			UIHelper.click(driver, latestVersionBtnXpath);
			List<WebElement> LatestVersions = UIHelper
					.findListOfElementsbyXpath(latestVersionListXpath, driver);	
		
			for (WebElement LatestVersion : LatestVersions) {
				versionListFrmDetailsPg.add(LatestVersion.getText());
			}
			
			if (version.contains(",")) {
				versionList = version.split(",");
			}

			for(String versions:versionList){
				finalVersionList.add(versions);
			}

			if (UIHelper.compareTwoSimilarLists(versionListFrmDetailsPg,
					finalVersionList)) {
				report.updateTestLog("Check versions of wiki page",
						"Wiki page versions are displayed successfully",
						Status.PASS);

			} else {
				report.updateTestLog("Check versions of wiki page",
						"Wiki page versions are not displayed", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check versions of wiki page",
					"Failed to display versions", Status.FAIL);
		}

	}

	// Verify discussion
	public void verifyDiscussion() {
		try {

			String title = dataTable.getData("MyActivities", "TitleName");
			String finaldiscussionNameXpath = discussionNameXpath.replace(
					"CRAFT", title);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finaldiscussionNameXpath);
			if (UIHelper.findAnElementbyXpath(driver, finaldiscussionNameXpath)
					.isDisplayed()) {
				UIHelper.highlightElement(driver, finaldiscussionNameXpath);
				report.updateTestLog("Verify discussion",
						"Discussion displayed succussfully"
								+ "<br /><b> Discussion Name : </b>" + title,
						Status.PASS);
			} else {
				report.updateTestLog("Verify discussion",
						"Discussion is not displayed"
								+ "<br /><b> Discussion Name : </b>" + title,
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify discussion",
					"Failed to display Discussion", Status.FAIL);
		}
	}

	// Verify blog
	public void verifyBlog() {
		try {

			String title = dataTable.getData("MyActivities", "TitleName");
			String finaldiscussionNameXpath = discussionNameXpath.replace(
					"CRAFT", title);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finaldiscussionNameXpath);
			if (UIHelper.findAnElementbyXpath(driver, finaldiscussionNameXpath)
					.isDisplayed()) {
				UIHelper.highlightElement(driver, finaldiscussionNameXpath);
				report.updateTestLog("Verify Blog",
						"Blog displayed successfully"
								+ "<br /><b> Blog Name : </b>" + title,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Blog", "Blog is not displayed"
						+ "<br /><b> Blog Name : </b>" + title, Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Blog", "Faile dot display blog",
					Status.FAIL);
		}
	}

	// Delete Link
	public void deleteLink() {
		try {
			String title = dataTable.getData("MyActivities", "TitleName");
			boolean statusFlag = deleteMyActivityTask(linkNameListXpath,
					linkDelXpath, deleteBlogButtonXpath, title);
			if (statusFlag) {
				report.updateTestLog("Delete Link", "Link deleted successfully"
						+ "<br /><b> Link Name : </b>" + title, Status.DONE);
			} else {
				report.updateTestLog("Delete Link", "Link not deleted"
						+ "<br /><b> Link Name : </b>" + title, Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Delete Link", "Delete Link Failed",
					Status.FAIL);
		}
	}

	// Create link in site dashboard
	public void createLink() {
		try {
			UIHelper.highlightElement(driver, newLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, newLinkXpath);
			UIHelper.highlightElement(driver, newLinkXpath);
			WebElement ele = UIHelper
					.findAnElementbyXpath(driver, newLinkXpath);
			UIHelper.highlightElement(driver, ele);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			String title = dataTable.getData("MyActivities", "TitleName");
			String url = dataTable.getData("MyActivities", "ActivityDid");
			String description = dataTable.getData("MyActivities",
					"MyActivityDropDownValue");
			String tag = dataTable.getData("MyActivities", "Tag");
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, linkTitleXpath);
			UIHelper.sendKeysToAnElementByXpath(driver, linkTitleXpath, title);
			UIHelper.sendKeysToAnElementByXpath(driver, linkURLXpath, url);
			UIHelper.sendKeysToAnElementByXpath(driver, linkDescXpath,
					description);
			UIHelper.sendKeysToAnElementByXpath(driver, linkTagXpath, tag);
			UIHelper.highlightElement(driver, linkSaveBtnXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, linkSaveBtnXpath);
			UIHelper.highlightElement(driver, linkSaveBtnXpath);
			ele = UIHelper.findAnElementbyXpath(driver, linkSaveBtnXpath);
			UIHelper.highlightElement(driver, ele);
			executor.executeScript("arguments[0].click();", ele);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, savedLinkXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, savedLinkXpath))) {
				UIHelper.highlightElement(driver, savedLinkXpath);
				report.updateTestLog("Create New Link",
						"New Link created successfully"
								+ "<br /><b> Link Name : </b>" + title,
						Status.DONE);
			} else {
				report.updateTestLog("Create New Link", "New Link not created"
						+ "<br /><b> Link Name : </b>" + title, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Create New Link", "Create New Link Failed",
					Status.FAIL);
		}
	}

	// Verify Link
	public void verifyLink() {
		try {

			String title = dataTable.getData("MyActivities", "TitleName");
			String finalListNameXpath = linkListNameXpath.replace("CRAFT",
					title);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalListNameXpath);
			if (UIHelper.findAnElementbyXpath(driver, finalListNameXpath)
					.isDisplayed()) {
				UIHelper.highlightElement(driver, finalListNameXpath);
				report.updateTestLog("Verify Link",
						"Link displayed successfully"
								+ "<br /><b> Link Name : </b>" + title,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Link", "Link is not displayed"
						+ "<br /><b> Link Name : </b>" + title, Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Link", "Failed to display Link",
					Status.FAIL);
		}
	}

	// check Link
	public boolean checkCreatedLink() {
		boolean flag = false;
		try {

			String title = dataTable.getData("MyActivities", "TitleName");
			String finalListNameXpath = linkListNameXpath.replace("CRAFT",
					title);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalListNameXpath);
			if (UIHelper.findAnElementbyXpath(driver, finalListNameXpath)
					.isDisplayed()) {
				flag = true;

			} else {
				flag = false;
			}

		} catch (Exception e) {

		}
		return flag;
	}

}
