package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoHomePageTest extends ReusableLibrary {

	private AlfrescoHomePage appHomePg = new AlfrescoHomePage(scriptHelper);
	private AlfrescoSitesPage appSitePg = new AlfrescoSitesPage(scriptHelper);

	private Boolean isJobTypeDownload;
	private Boolean isJobSorted;
	public String isMessageDisplayedXpath = ".//table/tbody[contains(@class,'data')]//td[contains(@class,'message')]/div";
	public String isMessagelongXpath = ".//td[contains(@class,'messageLong')]/div";

	private String creatSiteXPath = ".//a[contains(@id,'createSite')]";
	private String creatSiteInSiteTabXPath = ".//*[@id='HEADER_SITES_MENU_CREATE_SITE_text']";
	private String dashLinkTextXPath = ".//*[@id='HEADER_TITLE']";

	private String usrNmePopupXPath = ".//*[@id='HEADER_USER_MENU_POPUP_text']";
	private String usrDropDwnLstTextXPath = ".//*[@id='HEADER_USER_MENU_POPUP_dropdown']";
	private String myProfileLinkTextXPath = ".//*[@id='HEADER_USER_MENU_PROFILE_text']/a";
	private String myProfPgeTextXPath = ".//*[@id='Share']";
	private boolean isDisplayedMyTask;

	private String calEventNameFilePath = "src/test/resources/AppTestData/TestOutput/CalendarEvent.txt";

	private String splittedActivities[];
	private ArrayList<String> expectedActivitiesList = new ArrayList<String>();
	private ArrayList<String> actualActivitiesList = new ArrayList<String>();

	private String viewInBrowserXPath = "xhtml:html/xhtml:body/xhtml:pre";
	private String pageTitleXpath = ".//*[@id='HEADER_TITLE']";

	private String siteUserMenuXpath = ".//*[@id='HEADER_USER_MENU_POPUP']";
	private String siteUserMenuHelpXpath = ".//*[@id='HEADER_USER_MENU_HELP_text']";
	private String siteMenuTitleXpath = "//div[@id='HEADER_SITES_MENU_dropdown']//tr[contains(@id,'HEADER_SITES_MENU')]";
	private String siteMenuLinkTextXpath = "//div[@id='HEADER_SITES_MENU_dropdown']//td[2]";
	private String taskMenuTitleXpath = "//div[@id='HEADER_TASKS_GROUP']//tr[contains(@class,'MenuItem')]";
	private String taskMenuLinktxtXpath = "//div[@id='HEADER_TASKS_GROUP']//td[2]";
	private String userMenuTitleXpath="//div[@id='HEADER_USER_MENU']//tr[contains(@class,'dijitMenuItem')]";
	private String userMenuTitleLinktxtXpath="//div[@id='HEADER_USER_MENU']//td[2]";
	ArrayList<String> siteTitle = new ArrayList<String>();
	ArrayList<String> siteLink = new ArrayList<String>();
	ArrayList<String> taskTitle = new ArrayList<String>();
	ArrayList<String> taskLink = new ArrayList<String>();
	ArrayList<String> userMenuTitle = new ArrayList<String>();
	ArrayList<String> userMenuLink = new ArrayList<String>();

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoHomePageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void verifyLoginValidUser() {
		String assertValue = dataTable.getData("General_Data", "AssertValue");

		if (appHomePg.getHomeTabValue().equalsIgnoreCase(assertValue)) {
			report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.PASS);
		} else {
			/*
			 * frameworkParameters.setStopExecution(true); throw new
			 * FrameworkException("Verify Login",
			 * "Login failed for valid user");
			 */
			report.updateTestLog("Verify Login", "Login failed for valid user", Status.FAIL);
		}
	}

	public void verifyCmpltdTaskLinkInTaskDashlet() {
		boolean assertValue = appHomePg.ClickCmpltdTaskinMyTaskDashlet();

		if (assertValue) {
			report.updateTestLog(
					"Click Completed Tasks Link in My Task Dashlet in Dashboard and Verify Completed Tasks are displayed",
					"Completed Tasks are displayed Successfully", Status.PASS);
		} else {
			// frameworkParameters.setStopExecution(true);
			report.updateTestLog(
					"Click Completed Tasks Link in My Task Dashlet in Dashboard and Verify Completed Tasks are displayed",
					"Completed Tasks are NOT displayed Successfully", Status.FAIL);

			/*
			 * throw new FrameworkException(
			 * "Click Completed Tasks Link in My Task Dashlet in Dashboard and Verify Completed Tasks are displayed"
			 * , "Completed Tasks are NOT displayed Successfully");
			 */
		}
	}

	public void verifydrpDwnMyTaskDashlet() {
		boolean assertValue = appHomePg.selectValInMyTaskDashletDrpDwn();
		String drpDownVal = dataTable.getData("General_Data", "AssertValue");

		if (assertValue) {
			report.updateTestLog(
					"Select dropdown value " + drpDownVal
							+ " from My Task Dashlet and Verify results are displayed as per the selection",
					"Filter results are displayed Successfully", Status.PASS);
		} else {
			// frameworkParameters.setStopExecution(true);
			report.updateTestLog(
					"Select dropdown value " + drpDownVal
							+ " from My Task Dashlet and Verify results are displayed as per the selection",
					"Filter results are displayed Successfully", Status.FAIL);

			/*
			 * throw new FrameworkException( "Select dropdown value " +
			 * drpDownVal +
			 * " from My Task Dashlet and Verify results are displayed as per the selection"
			 * , "Filter results are displayed Successfully");
			 */
		}
	}

	public void verifyTasksDueDateInMyTaskDashlet() {
		boolean assertValue = appHomePg.isDisplayedDueDateForAllTasksInMyTasksDashlet();

		if (assertValue) {
			report.updateTestLog("Verify Tasks Due Date in My tasks Dashlet",
					"Tasks due date displayed Successfully in My Tasks Dashlet. " + "<br /><b>Number of open Tasks: "
							+ appHomePg.tasksDueDateCount,
					Status.PASS);

		} else {
			report.updateTestLog("Verify Tasks Due Date in My tasks Dashlet",
					"Tasks due date fialed to display in My Tasks Dashlet"
							+ "<br /><b>Number of open Tasks: No Due Date Found",
					Status.FAIL);
		}
	}

	// VerifyTask Due Date in 'My Tasks' Dashlet
	public void verifyTaskDueDateInMyTaskDashlet(String taskName) {
		boolean assertValue = appHomePg.isDisplayedDueDateForTaskInMyTasksDashlet(taskName);

		if (assertValue) {
			report.updateTestLog("Verify Task Due Date in My tasks Dashlet",
					"Task:" + taskName + " due date displayed Successfully in My Tasks Dashlet.", Status.PASS);

		} else {
			report.updateTestLog("Verify Task Due Date in My tasks Dashlet",
					"Task:" + taskName + " due date fialed to display in My Tasks Dashlet", Status.FAIL);
		}
	}

	public void verifyBulkDownloadJobsFromDashlet() {
		try {
			UIHelper.waitFor(driver);
			appHomePg.getBulkjobValues();
			String bulkJobType = dataTable.getData("General_Data", "BulkJobType");
			for (String jobType : appHomePg.bulkTypeList) {
				if (jobType.contains(bulkJobType)) {
					isJobTypeDownload = true;
				} else {
					isJobTypeDownload = false;
					break;
				}
			}
			if (isJobTypeDownload) {
				report.updateTestLog("Verify Bulk Download Job ",
						"All bulk dowload job displayed in dashlet" + "<br /><b> Job Type : </b>"
								+ appHomePg.bulkTypeList.get(0) + "<br /><b> No of Jobs retrieved : </b>"
								+ appHomePg.bulkIDList.size(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Bulk Download Job ", "All bulk dowload job displayed failed in dashlet"
						+ "<br /><b> No of Jobs retrieved : </b>" + appHomePg.bulkIDList.size(), Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Bulk Download Job ", "All bulk dowload job displayed failed in dashlet",
					Status.FAIL);
		}
	}

	public void verifyBulkUploadJobsFromDashlet() {
		try {
			UIHelper.waitFor(driver);
			appHomePg.getBulkjobValues();
			String bulkJobType = dataTable.getData("General_Data", "BulkJobType");
			for (String jobType : appHomePg.bulkTypeList) {
				if (jobType.contains(bulkJobType)) {
					isJobTypeDownload = true;
				} else {
					isJobTypeDownload = false;
					break;
				}
			}
			if (isJobTypeDownload) {
				report.updateTestLog("Verify Bulk Upload Job ",
						"All bulk Upload job displayed in dashlet" + "<br /><b> Job Type : </b>"
								+ appHomePg.bulkTypeList.get(0) + "<br /><b> No of Jobs retrieved : </b>"
								+ appHomePg.bulkIDList.size(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Bulk Upload Job ", "All bulk Upload job displayed failed in dashlet"
						+ "<br /><b> No of Jobs retrieved : </b>" + appHomePg.bulkIDList.size(), Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Bulk Upload Job ", "All bulk Upload job displayed failed in dashlet",
					Status.FAIL);
		}
	}

	public void verifyBulkFailedJobsFromDashlet() {
		try {
			UIHelper.waitFor(driver);
			appHomePg.getBulkjobValues();
			String bulkJobType = dataTable.getData("General_Data", "BulkJobType");
			for (String jobType : appHomePg.bulkStatusList) {
				if (jobType.contains(bulkJobType)) {
					isJobTypeDownload = true;
				} else {
					isJobTypeDownload = false;
					break;
				}
			}
			if (isJobTypeDownload) {
				report.updateTestLog("Verify Bulk Failed Job ",
						"All bulk Upload job displayed in dashlet" + "<br /><b> Job Status : </b>"
								+ appHomePg.bulkStatusList.get(0) + "<br /><b> No of Jobs retrieved : </b>"
								+ appHomePg.bulkIDList.size(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Bulk Failed Job ", "All bulk Upload job displayed failed in dashlet"
						+ "<br /><b> No of Jobs retrieved : </b>" + appHomePg.bulkIDList.size(), Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Bulk Failed Job ", "All bulk Upload job displayed failed in dashlet",
					Status.FAIL);
		}
	}

	public void verifyBulkJobsFromDashlet() {
		try {
			UIHelper.waitFor(driver);
			appHomePg.getBulkjobValues();
			if (appHomePg.bulkIDList.size() > 0) {
				isJobTypeDownload = true;
			} else {
				isJobTypeDownload = false;
			}

			if (isJobTypeDownload) {
				report.updateTestLog("Verify Bulk Job Duration Filter ", "All bulk job displayed in dashlet"
						+ "<br /><b> No of Jobs retrieved : </b>" + appHomePg.bulkIDList.size(), Status.PASS);
			} else {
				report.updateTestLog("Verify Bulk Upload Job ", "All bulk job displayed failed in dashlet"
						+ "<br /><b> No of Jobs retrieved : </b>" + appHomePg.bulkIDList.size(), Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Bulk Upload Job ", "All bulk job displayed failed in dashlet", Status.FAIL);
		}
	}

	public void verifyAddDashletInCustomDashBrd() {
		boolean assertValue = appHomePg.addDashletInCustomDashBoard();
		String dashletNmetoAddTest = dataTable.getData("Home", "DashletName");
		String colNoofAddDashletTest = dataTable.getData("Home", "ColumnNoofAddDashlet");

		if (assertValue) {
			report.updateTestLog(
					"Add the Dashlet -  " + dashletNmetoAddTest + " to Column No " + colNoofAddDashletTest
							+ " using Add Dashlet Functionality in Custom Dashboard.Verify the dashlet has been added successfully",
					"Dashlet has been added successfully", Status.PASS);
		} else {
			// frameworkParameters.setStopExecution(true);
			report.updateTestLog(
					"Add the Dashlet -  " + dashletNmetoAddTest + " to Column No " + colNoofAddDashletTest
							+ " using Add Dashlet Functionality in Custom Dashboard..Verify the dashlet has been added successfully",
					"Dashlet has NOT been added successfully", Status.FAIL);

			/*
			 * throw new FrameworkException( "Add the Dashlet -  " +
			 * dashletNmetoAddTest + " to Column No " + colNoofAddDashletTest +
			 * "using Add Dashlet Functionality in Custom Dashboard..Verify the dashlet has been added successfully"
			 * , "Dashlet has NOT been added successfully");
			 */
		}
	}

	public void verifyRemoveDashletInCustomDashBrd() {
		boolean assertValue = appHomePg.removeDashletInCustomDashBoard();
		String dashletNmetoremoveTest = dataTable.getData("Home", "DashletName");
		String colNoofremvDashletTest = dataTable.getData("Home", "ColumnNoofremvDashlet");

		if (assertValue) {
			report.updateTestLog(
					"Remove the Dashlet -  " + dashletNmetoremoveTest + " from Column No " + colNoofremvDashletTest
							+ "using  Custom Dashboard Functionality.Verify the dashlet has been removed successfully",
					"Dashlet has been removed successfully", Status.PASS);
		} else {
			// frameworkParameters.setStopExecution(true);
			report.updateTestLog(
					"Remove the Dashlet -  " + dashletNmetoremoveTest + " from Column No " + colNoofremvDashletTest
							+ "using Custom Dashboard Functionality.Verify the dashlet has been removed successfully",
					"Dashlet has NOT been removed successfully", Status.FAIL);

			/*
			 * throw new FrameworkException( "Remove the Dashlet -  " +
			 * dashletNmetoremoveTest + " from Column No " +
			 * colNoofremvDashletTest +
			 * "using Custom Dashboard Functionality.Verify the dashlet has been removed successfully"
			 * , "Dashlet has NOT been removed successfully");
			 */
		}
	}

	public void verifyBulkFailedJobsErrorsFromDashlet() {
		try {
			UIHelper.waitFor(driver);
			ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tab.get(1));
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, isMessageDisplayedXpath);

			if (UIHelper.findAnElementbyXpath(driver, isMessageDisplayedXpath).getText() != null) {
				report.updateTestLog("Verify Bulk Failed Job ",
						"Bulk failed job displays Error Msg." + "<br /><b> Error Msg : </b>"
								+ UIHelper.findAnElementbyXpath(driver, isMessageDisplayedXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Bulk Failed Job ",
						"Bulk failed job displays Error Msg failed. Error Msg not found", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Bulk Failed Job ",
					"Bulk failed job displays Error Msg failed. Error Msg not found", Status.FAIL);
		}
	}

	public void verifyHelpURL() {
		try {
			UIHelper.waitFor(driver);

			ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tab.get(1));
			UIHelper.waitForPageToLoad(driver);
			String currentURL = driver.getCurrentUrl();
			System.out.println(currentURL);
			String ExpectedURL = dataTable.getData("Parametrized_Checkpoints", "Help URL");
			System.out.println(ExpectedURL);
			if (currentURL.contains(ExpectedURL)) {
				report.updateTestLog("Verify URL ", "URL = " + currentURL, Status.PASS);
			} else {
				report.updateTestLog("Verify URL ", "Verify URL failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Help URL ", "Verify URL failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyOverDueTaskNotification() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String finalTaskName = taskName + "_" + currentDate;
			UIHelper.waitFor(driver);
			if (new AlfrescoHomePage(scriptHelper).checkOverDueTaskNotification()) {
				report.updateTestLog("Verify the Over due Task Notification",
						"Notification verified successfully" + "<br /><b>Task Name : </b>" + finalTaskName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Over due Task Notification",
						"Notification not displyed" + "<br /><b>Task Name : </b>" + finalTaskName, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the Over due Task Notification",
					"Verify the Over due Task Notification Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyTaskNotification() {
		try {
			String taskName = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String finalTaskName = taskName + "_" + currentDate;

			if (new AlfrescoHomePage(scriptHelper).checkTaskNotification()) {
				report.updateTestLog("Verify the Task Notification In MyTask Dashlet",
						"Notification verified successfully" + "<br /><b>Task Name : </b>" + finalTaskName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Task Notification In MyTask Dashlet",
						"Notification not displyed" + "<br /><b>Task Name : </b>" + finalTaskName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify the Task Notification In MyTask Dashlet",
					"Verify the Task Notification In MyTask Dashlet Failed", Status.FAIL);
		}
	}

	public void verifyBulkJobsSortedFromDashlet() {
		try {
			UIHelper.waitFor(driver);
			appHomePg.getBulkjobValues();
			if (UIHelper.isSortedList(appHomePg.bulkStartDateList)) {
				isJobSorted = true;
			} else {
				isJobSorted = false;
			}
			if (isJobSorted) {
				report.updateTestLog("Verify Bulk Job Sorted", "All bulk job displayed in dashlet are sorted",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Bulk Job Sorted", "All bulk job displayed in dashlet are not sorted",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Bulk Job Sorted", "All bulk job displayed in dashlet are not sorted",
					Status.FAIL);
		}
	}

	// Verify Home Page Create Site Link is available in My site
	// Dashlet
	public void verifyCreatSiteLinkDisplyed() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, creatSiteXPath);
			if (UIHelper.findAnElementbyXpath(driver, creatSiteXPath).isDisplayed()) {
				report.updateTestLog(
						"Verify that user belongs to Group site_admin group able to see Create Site Option on the My Sites Dashlet",
						"Create Site Link displayed successfully", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify that user belongs to Group site_admin group able to see Create Site Option on the My Sites Dashlet",
						"Create Site Link NOT displayed successfully", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify that user belongs to Group site_admin group able to see Create Site Option on the My Sites Dashlet",
					e.getMessage(), Status.FAIL);
		}
	}

	// Verify Home Page Create Site Link is available in SiteTab
	public void verifyCreatSiteLinkDisplyedSiteTab() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, creatSiteInSiteTabXPath);
			if (UIHelper.findAnElementbyXpath(driver, creatSiteInSiteTabXPath).isDisplayed()) {
				report.updateTestLog(
						"Verify that user belongs to Group site_admin group able to see Create Site Option  from Site Menu Bar",
						"Create Site Link displayed successfully", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify that user belongs to Group site_admin group able to see Create Site Option  from Site Menu Bar",
						"Create Site Link NOT displayed successfully", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify that user belongs to Group site_admin group able to see Create Site Option  from Site Menu Bar",
					e.getMessage(), Status.FAIL);
		}
	}

	// Verify Home Page Create Site Link is NOT available in My
	// site Dashlet for Non admin user

	public void verifyCreatSiteLinkNotDisplyed() {
		try {

			if ((UIHelper.findAnElementbyXpath(driver, creatSiteXPath).isDisplayed())) {
				report.updateTestLog(
						"Verify that user does not belongs to Group site_admin group NOT able to see Create Site Option on the My Sites Dashlet",
						"Create Site Link displayed successfully", Status.FAIL);

			}

		} catch (Exception e) {

			report.updateTestLog(
					"Verify that user does not belongs to Group site_admin group NOT  able to see Create Site Option on the My Sites Dashlet",
					"Create Site Link NOT displayed successfully", Status.PASS);

		}
	}

	// Verify Home Page Create Site Link is NOT available in Site
	// Tab for Non admin user

	public void verifyCreatSiteLinkNotDisplyedInSite() {
		try {

			if (UIHelper.checkForAnElementbyXpath(driver, creatSiteInSiteTabXPath)) {
				report.updateTestLog(
						"Verify that user does not belongs to Group site_admin group NOT able to see Create Site Option on the My Sites Dashlet",
						"Create Site Link displayed successfully", Status.FAIL);

			} else {
				report.updateTestLog(
						"Verify that user does not belongs to Group site_admin group NOT  able to see Create Site Option on the My Sites Dashlet",
						"Create Site Link NOT displayed successfully", Status.PASS);
			}

		} catch (Exception e) {

		}
	}

	// Verify Home Page do not Display Welcome Dash board Screen
	public void verifyWelcomeDashBrdNotDisp() {
		try {
			String dashBrdName = dataTable.getData("Home", "DashBoardName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, dashLinkTextXPath);
			if (UIHelper.findAnElementbyXpath(driver, dashLinkTextXPath).getText().equalsIgnoreCase(dashBrdName)) {
				report.updateTestLog("Verify that no Welcome Dashboard is visible to the User after login",
						"Welcome Dashboard NOT displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify that no Welcome Dashboard is visible to the User after login",
						"Welcome Dashboard displayed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify that no Welcome Dashboard is visible to the User after login", e.getMessage(),
					Status.FAIL);
		}
	}

	// Verify Change Password does not appears under User name Drop
	// down menu

	public void verifyChngPwdNotDisp() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, usrNmePopupXPath);
			UIHelper.findAnElementbyXpath(driver, usrNmePopupXPath).click();

			if (!UIHelper.findAnElementbyXpath(driver, usrDropDwnLstTextXPath).getText().contains("Change Password")) {

				report.updateTestLog("Verify that no Change Password Option displayed under User Name Drop down Menu",
						"Change Password NOT displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify that no Change Password Option displayed under User Name Drop down Menu",
						"Change Password  displayed successfully", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify that no Change Password Option displayed under User Name Drop down Menu",
					e.getMessage(), Status.FAIL);
		}
	}

	// Verify Change Password does not appears under My Profile
	// User name Drop down menu

	public void verifyChngPwdNotDispInMyProfile() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, usrNmePopupXPath);
			UIHelper.findAnElementbyXpath(driver, usrNmePopupXPath).click();
			UIHelper.waitForVisibilityOfEleByXpath(driver, myProfileLinkTextXPath);
			UIHelper.findAnElementbyXpath(driver, myProfileLinkTextXPath).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, myProfPgeTextXPath);

			if (!UIHelper.findAnElementbyXpath(driver, myProfPgeTextXPath).getText().contains("Change Password")) {

				report.updateTestLog(
						"Verify that no Change Password Option displayed in My Profile Page under User Name Drop down Menu",
						"Change Password NOT displayed successfully", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify that no Change Password Option displayed in My Profile Page under User Name Drop down Menu",
						"Change Password  displayed successfully", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog(
					"Verify that no Change Password Option displayed in My Profile Page under User Name Drop down Menu",
					e.getMessage(), Status.FAIL);
		}
	}

	public void verifyAllOpenTasks() {

		try {
			String expectedTaskName = dataTable.getData("Workflow", "Message");

			if (appHomePg.isDisplayedTasksInHomePage()) {
				report.updateTestLog("Verify user is able to view all open tasks",
						"User viewed all open tasks successfully. " + "<br /><b>Number of open Tasks: "
								+ appHomePg.openTasksSize + "<br/> <b>Open Task Name:</b>" + expectedTaskName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify user is able to view all open tasks", "User failed to view all open tasks"
						+ "<br /><b>Number of open Tasks: " + appHomePg.openTasksSize, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify open Tasks in Home Page Dashlet
	public void verifyOpenTasksInHomePageDashlet(String expectedTaskName) {

		try {
			// String expectedTaskName = dataTable.getData("Workflow",
			// "Message");
			boolean isDispTaskInMyTasksDashlet = appHomePg.checkTaskInMyTasksDashlet(expectedTaskName);
			int tasksCount = appHomePg.getCountOfTasksFromMyTasksDashlet();
			/*
			 * isDisplayedMyTask = appHomePg
			 * .isDisplayedMyTaskInHomePage(expectedTaskName);
			 */
			if (appHomePg.isDisplayedTasksInHomePage() && isDispTaskInMyTasksDashlet) {
				report.updateTestLog("Verify user is able to view all open tasks",
						"User viewed all open tasks successfully. " + "<br /><b>Number of open Tasks: " + tasksCount
								+ "<br/> <b>Open Task Name:</b>" + expectedTaskName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify user is able to view all open tasks",
						"User failed to view all open tasks" + "<br /><b>Number of open Tasks: " + tasksCount,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Admin tools tab is present for Site_Admin user
	public void verifyAdminTabPresence() {

		boolean blnFlagAdm = false;

		blnFlagAdm = appHomePg.retAdminTabPresence();

		if (!blnFlagAdm) {
			report.updateTestLog("Verify the user (who belongs to site admin group) is able  to see admin tools menu",
					"Admin Tools tab not available to Site_admin user group. Hence the user is not able to do any Admin Related activities.",
					Status.PASS);
		} else {
			report.updateTestLog("Verify the user (who belongs to site admin group) is able  to see admin tools menu",
					"Admin Tools tab is available to Site_admin user group.", Status.FAIL);
		}

	}

	// Verify Invited Site details in My Tasks Daslet in Home Page
	public void verifyInvitedSiteInMyTaskDashlet(String siteName) {
		boolean assertValue = appHomePg.isDisplayedTaskInMyTasksDashlet(siteName);

		if (assertValue) {
			report.updateTestLog("Verify Invited Saite in My tasks Dashlet", "Invited Site:" + siteName
					+ " displayed Successfully in My Tasks Dashlet. " + "<br /><b>Invited Site Name: " + siteName,
					Status.PASS);

		} else {
			report.updateTestLog(
					"Verify Invited Saite in My tasks Dashlet", "Invited Site:" + siteName
							+ " failed to display in My Tasks Dashlet. " + "<br /><b>Invited Site Name: " + siteName,
					Status.FAIL);
		}
	}

	// Verify uploaded file property values in Document Details Page
	public void verifyFirstThreeMyActivitiesDetails(String activities) {
		try {
			actualActivitiesList = appHomePg.getFirstThreeActivities();

			if (activities.contains(";")) {
				splittedActivities = activities.split(";");

			} else if (activities.contains(",")) {
				splittedActivities = activities.split(",");
			} else {
				expectedActivitiesList.add(activities);
			}

			if (splittedActivities != null & splittedActivities.length > 0) {
				for (String expActivityValue : splittedActivities) {
					expectedActivitiesList.add(expActivityValue);
				}
			}

			if (UIHelper.compareTwoDiffSizeOfLists(expectedActivitiesList, actualActivitiesList)) {
				report.updateTestLog("Verify performed activities in 'My Activities' Dashlet in Home Page",
						"Performed activities:" + activities + " are succefully displayed in 'My Activities' Dashlet"
								+ "<br/><b>Performed Activities on Document:</b>" + expectedActivitiesList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify performed activities in 'My Activities' Dashlet in Home Page",
						"Performed activities:" + activities + " are failed to display in 'My Activities' Dashlet"
								+ "<br/><b>Performed Activities on Document:</b>" + expectedActivitiesList,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyUserActivityDisplayedInMyActivityDashlet(String siteName) {
		try {
			String activityDid = dataTable.getData("MyActivities", "ActivityDid");
			String activityName = dataTable.getData("MyActivities", "TitleName");

			String menuToNavigate = dataTable.getData("Sites", "CustomizeSiteMenuToNavigate");
			if (menuToNavigate.equalsIgnoreCase("Calendar")) {
				try {
					activityName = new FileUtil().readDataFromFile(calEventNameFilePath);
				} catch (Exception e) {
				}
			}

			// String siteName = dataTable.getData("Sites", "SiteName");

			if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet(activityDid, activityName,
					siteName)) {
				report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
						"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
								+ activityDid + "<br/><b> Site Name: </b>" + siteName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
						"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
								+ activityDid + "<br/><b> Site Name: </b>" + siteName,
						Status.FAIL);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Verify that a record is displayed in the 'My Activity' dashlet Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyUserActivityDisplayedInMyActivityDashlet(String activityName, String activityDid, String siteName,
			String menuToNavigate, String activityType) {
		try {

			if (menuToNavigate.equalsIgnoreCase("Calendar")) {
				try {
					activityName = new FileUtil().readDataFromFile(calEventNameFilePath);
				} catch (Exception e) {
				}
			}

			if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet(activityDid, activityName,
					siteName, activityType)) {
				report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
						"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
								+ activityDid + "<br/><b> Site Name: </b>" + siteName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
						"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
								+ activityDid + "<br/><b> Site Name: </b>" + siteName,
						Status.FAIL);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Verify that a record is displayed in the 'My Activity' dashlet Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyContentEditingDisplayedInContentEditingDashlet(String timeInfo) {
		try {
			String activityDid = dataTable.getData("MyActivities", "ActivityDid");
			String fileNames = dataTable.getData("MyFiles", "FileName");
			String siteName = dataTable.getData("Sites", "SiteName");

			StringTokenizer token = new StringTokenizer(fileNames, ",");
			while (token.hasMoreElements()) {
				String fileName = token.nextElement().toString();
				if (appHomePg.isContentEditingDisplayedInContentEditingDashlet(activityDid, fileName, siteName,
						timeInfo)) {
					report.updateTestLog("Verify Edit Record is displayed in the 'Content I'm Editing' dashlet",
							"Edited Record displayed in the 'Content I'm Editing' dashlet Successfully"
									+ "<br/><b> Activity Did : </b>" + activityDid + "<br/><b> Site Name: </b>"
									+ siteName + "<br/><b> File Name: </b>" + fileName,
							Status.PASS);
				} else {
					report.updateTestLog("Verify Edit Record is displayed in the 'Content I'm Editing' dashlet",
							"Edited Record not displayed in the 'Content I'm Editing' dashlet"
									+ "<br/><b> Activity Did : </b>" + activityDid + "<br/><b> Site Name: </b>"
									+ siteName + "<br/><b> File Name: </b>" + fileName,
							Status.FAIL);
				}
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Edit Record is displayed in the 'Content I'm Editing' dashlet",
					"Verify Edit Record is displayed in the 'Content I'm Editing' dashlet Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyUserActivityDisplayedInContentEditingDashlet(String activityName) {
		try {
			String activityDid = dataTable.getData("MyActivities", "ActivityDid");

			if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInContentEditingDashlet(activityDid)) {
				report.updateTestLog("Verify that User Activity not displayed in the 'Content I'm Editing' dashlet",
						"User Activity displayed in the 'Content I'm Editing' dashlet" + "<br/><b> Activity Did : </b>"
								+ activityName,
						Status.FAIL);
			} else {
				report.updateTestLog("Verify that User Activity not displayed in the 'Content I'm Editing' dashlet",
						"User Activity not displayed in the 'Content I'm Editing' dashlet"
								+ "<br/><b> Activity Did : </b>" + activityName,
						Status.PASS);
			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify that User Activity not displayed in the 'Content I'm Editing' dashlet",
					"Verify that User Activity not displayed in the 'Content I'm Editing' dashlet Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyJunkDataInContentEditingDashlet(String timeInfo) {
		try {
			String activityDid = dataTable.getData("MyActivities", "ActivityDid");
			String fileNames = dataTable.getData("MyFiles", "FileName");
			String siteName = dataTable.getData("Sites", "SiteName");

			StringTokenizer token = new StringTokenizer(fileNames, ",");
			while (token.hasMoreElements()) {
				String fileName = token.nextElement().toString();
				if (new AlfrescoHomePage(scriptHelper).isContentEditingDisplayedInContentEditingDashlet(activityDid,
						fileName, siteName, timeInfo)) {
					report.updateTestLog("Verify Junk Information displayed in the 'Content I'm Editing' dashlet",
							"Junk Information not displayed in the 'Content I'm Editing' dashlet"
									+ "<br/><b> Activity Did : </b>" + activityDid + "<br/><b> Site Name: </b>"
									+ siteName + "<br/><b> File Name: </b>" + fileName,
							Status.PASS);
				} else {
					report.updateTestLog("Verify Junk Information displayed in the 'Content I'm Editing' dashlet",
							"Junk Information displayed in the 'Content I'm Editing' dashlet"
									+ "<br/><b> Activity Did : </b>" + activityDid + "<br/><b> Site Name: </b>"
									+ siteName + "<br/><b> File Name: </b>" + fileName,
							Status.FAIL);
				}
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Junk Information displayed in the 'Content I'm Editing' dashlet",
					"Verify Junk Information displayed in the 'Content I'm Editing' dashlet Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyFormatOfContentEditingDashlet(String timeInfo) {
		try {
			String activityDid = dataTable.getData("MyActivities", "ActivityDid");
			String fileNames = dataTable.getData("MyFiles", "FileName");
			String siteName = dataTable.getData("Sites", "SiteName");

			StringTokenizer token = new StringTokenizer(fileNames, ",");
			while (token.hasMoreElements()) {
				String fileName = token.nextElement().toString();
				if (new AlfrescoHomePage(scriptHelper).isContentEditingDisplayedInContentEditingDashlet(activityDid,
						fileName, siteName, timeInfo)) {
					report.updateTestLog("Verify the Format of 'Content I'm Editing' dashlet data",
							"Format verified successully" + "<br/><b> Format Verified : </b>"
									+ "<br/><b> File Name: </b>" + fileName + "<br/><b> Activity Did : </b>"
									+ activityDid + "<br/><b> Time : </b>" + timeInfo + "<br/><b> Site Name: </b>"
									+ siteName,
							Status.PASS);
				} else {
					report.updateTestLog("Verify the Format of 'Content I'm Editing' dashlet data",
							"Format not verified" + "<br/><b> Format Verified : </b>" + "<br/><b> File Name: </b>"
									+ fileName + "<br/><b> Activity Did : </b>" + activityDid + "<br/><b> Time : </b>"
									+ timeInfo + "<br/><b> Site Name: </b>" + siteName,
							Status.FAIL);
				}
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify the Format of 'Content I'm Editing' dashlet data",
					"Verify the Format of 'Content I'm Editing' dashlet data Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param siteName
	 */
	public void verifyActivityFeedAfterDisabled(String siteName) {
		try {
			String activityDid = dataTable.getData("MyActivities", "ActivityDid");
			String activityName = dataTable.getData("MyActivities", "TitleName");
			if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet(activityDid, activityName,
					siteName)) {
				report.updateTestLog("Verify the Activity Feed is not displayed in the dashboard",
						"Activity Feed displayed in the dashboard Successfully" + "<br/><b> Activity Did : </b>"
								+ activityDid + "<br/><b> Site Name: </b>" + siteName,
						Status.FAIL);
			} else {
				report.updateTestLog("Verify the Activity Feed is not displayed in the dashboard",
						"Activity Feed not displayed in the dashboard" + "<br/><b> Activity Did : </b>" + activityDid
								+ "<br/><b> Site Name: </b>" + siteName,
						Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify the Activity Feed is not displayed in the dashboard",
					"Verify the Activity Feed is not displayed in the dashboard Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param siteName
	 */
	public void verifyActivityFeedForSiteMember(String siteName) {
		try {
			String activityDid = dataTable.getData("MyActivities", "ActivityDid");
			String activityName = dataTable.getData("MyActivities", "TitleName");
			if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet(activityDid, activityName,
					siteName)) {
				report.updateTestLog("Verify the Activity Feed is displayed in the dashboard",
						"Activity Feed displayed in the dashboard Successfully" + "<br/><b> Activity Did : </b>"
								+ activityDid + "<br/><b> Site Name: </b>" + siteName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Activity Feed is displayed in the dashboard",
						"Activity Feed not displayed in the dashboard" + "<br/><b> Activity Did : </b>" + activityDid
								+ "<br/><b> Site Name: </b>" + siteName,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify the Activity Feed is not displayed in the dashboard",
					"Verify the Activity Feed is not displayed in the dashboard Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 */
	public void verifyDeleteOptionInMySiteDashlet(String siteName) {
		try {

			if (new AlfrescoHomePage(scriptHelper).isDeleteOptionInMySiteDashlet(siteName)) {
				report.updateTestLog("Verify 'Delete' site option in sites dashlet",
						"Delete option not found" + "<br /><b>Site Name : </b>" + siteName, Status.PASS);
			} else {
				report.updateTestLog("Verify 'Delete' site option in sites dashlet",
						"Either Delete option is available / Site Not Found in Sites dashlet"
								+ "<br /><b>Site Name : </b>" + siteName,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify 'Delete' site option in sites dashlet",
					"Verify 'Delete' site option in sites dashlet Failed", Status.FAIL);
		}
	}

	// Verify Blue tints in Dashlet Titles
	public void verifyBlueTintOnUserDashboard() {
		try {
			boolean assertValue = appHomePg.checkBlueTints();

			if (assertValue) {
				report.updateTestLog("Verify blue tints in the dashlet titles on User dashboard",
						"<b>Blue tints</b>" + " displayed successfully in the dashlet titles on User dashboard",
						Status.PASS);
			} else {
				report.updateTestLog("Verify blue tints in the dashlet titles on User dashboard",
						"<b>Blue tints</b>" + " are failed to display in the dashlet titles on User dashboard",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify content view in browser
	public void verifyViewContent() {
		try {

			if (UIHelper.checkForAnElementbyXpath(driver, viewInBrowserXPath)) {
				UIHelper.highlightElement(driver, viewInBrowserXPath);
				report.updateTestLog("Verify view in browser", " Content viewed in browser successfuly", Status.PASS);
			} else {
				report.updateTestLog("Verify view in browser", " Content viewed in browser successfuly", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Switch tab to recent tab
	public void verifyNoofTabs() {
		try {

			UIHelper.waitFor(driver);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			report.updateTestLog("Verify Opened tabs",
					" No New tab was opened on element click" + "<br><b> No of opened tabs : </b> " + tabs.size(),
					Status.PASS);

		} catch (Exception e) {

			report.updateTestLog("Verify Opened tabs", "New tab was opened on element click", Status.FAIL);

		}

	}

	// Switch tab to recent tab
	public void verifyNotifPage() {
		try {

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pageTitleXpath);
			UIHelper.highlightElement(driver, pageTitleXpath);
			report.updateTestLog("Verify Notification Page", " Navigate to Notification page successfully"
					+ "<br><b> Page Title :</b> " + UIHelper.findAnElementbyXpath(driver, pageTitleXpath).getText(),
					Status.PASS);

		} catch (Exception e) {

			report.updateTestLog("Verify Notification Page", " Navigate to Notification page failed"
					+ "<br><b> Page Title :</b> " + UIHelper.findAnElementbyXpath(driver, pageTitleXpath).getText(),
					Status.FAIL);

		}

	}

	// Click on user Menu and Click Help
	public void verifyHelpOption() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.click(driver, siteUserMenuXpath);
			UIHelper.highlightElement(driver, siteUserMenuHelpXpath);
			/* UIHelper.click(driver, siteUserMenuHelpXpath); */
			UIHelper.waitForPageToLoad(driver);
			report.updateTestLog("Verify Help option", "Help option availble", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Verify Help option", "Failed. Help option is not availble", Status.FAIL);
		}
	}

	// Verify open Tasks in Home Page Dashlet
	public void verifyTaskandAttachment(String expectedTaskName) {

		try {
			// String expectedTaskName = dataTable.getData("Workflow",
			// "Message");
			isDisplayedMyTask = appHomePg.isDisplayedMyTaskInHomePage(expectedTaskName);
			String finalattachmentXpath = appHomePg.attachmentXpath.replace("CRAFT", expectedTaskName);

			if (appHomePg.isDisplayedTasksInHomePage() && isDisplayedMyTask) {
				UIHelper.highlightElement(driver, finalattachmentXpath);

				report.updateTestLog("Verify assigned task from Dashlet",
						"Task and attachment verified successfully from dashlet. " + "<br/> <b>Open Task Name:</b>"
								+ expectedTaskName + "<br>"
								+ UIHelper.findAnElementbyXpath(driver, finalattachmentXpath).getText(),
						Status.PASS);
			} else {
				report.updateTestLog("Verify assigned task from Dashlet",
						"Task and attachment verification from dashlet failed. Task not found ", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify assigned task from Dashlet",
					"Task and attachment verification from dashlet failed. Task not found ", Status.FAIL);
		}
	}

	// Check list is empty saved searches
	public boolean isDashletempty(String listXpath) {
		boolean flag = false;
		try {

			List<WebElement> listOfValues = driver.findElements(By.xpath(listXpath));

			if (listOfValues.isEmpty()) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Check description of the saved search
	public AlfrescoHomePage checkDescription(String listXpath, String Desc) {
		try {
			List<WebElement> listOfValues = driver.findElements(By.xpath(listXpath));

			for (WebElement values : listOfValues) {
				UIHelper.scrollToAnElement(values);
				if (values.getText().contains(Desc)) {
					UIHelper.highlightElement(driver, values);
					/* String val[]=values.getText().split(" "); */
					report.updateTestLog("Verify description of Saved search",
							"Description displayed successfully" + "<br><b> Description : </b>" + Desc, Status.PASS);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify description of Saved search", "Failed. Description is not available",
					Status.FAIL);
		}

		return new AlfrescoHomePage(scriptHelper);
	}

	// Check value available in dashlet
	public AlfrescoHomePage checkValAvil(String listXpath, String value) {
		try {

			List<WebElement> listOfValues = driver.findElements(By.xpath(listXpath));

			for (WebElement values : listOfValues) {
				UIHelper.scrollToAnElement(values);
				if (values.getText().equalsIgnoreCase(value)) {
					UIHelper.highlightElement(driver, values);

					report.updateTestLog("Check given value available",
							"Given value is available" + "<br><b> Given value : </b>" + values.getText(), Status.PASS);
				}

				UIHelper.waitFor(driver);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check given value available", "Failed. Given value is not available", Status.FAIL);
		}

		return new AlfrescoHomePage(scriptHelper);
	}

	// Verify filtered Sites in 'My Sites' Dashlet
	public void verifyFilteredFavoriteSitesInMySitesDashlet() {

		try {
			if (appHomePg.isDisplayedFavoriteSitesInHomePage()) {
				report.updateTestLog("Verify user is able to view all filtered sites",
						"User viewed all filtered sites successfully" + "<br /><b>Number of Favorite Sites: "
								+ appHomePg.favoriteSitesCount,
						Status.PASS);
			} else {
				report.updateTestLog("Verify user is able to view all filtered sites",
						"User failed to view all filtered sites" + "<br /><b>Number of Favorite Sites: "
								+ appHomePg.favoriteSitesCount,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify filtered 'All' Sites in 'My Sites' Dashlet
	public void verifyFilteredAllSitesInMySitesDashlet() {

		try {
			if (appHomePg.isDisplayedAllSitesInHomePage()) {
				report.updateTestLog("Verify user is able to view all sites by Filter: 'All'",
						"User viewed filtered all sites successfully" + "<br /><b>Number of Sites: "
								+ appHomePg.allSitesCount,
						Status.PASS);
			} else {
				report.updateTestLog("Verify user is able to view all sites by Filter: 'All'",
						"User failed to view filtered all sites" + "<br /><b>Number of Sites: "
								+ appHomePg.allSitesCount,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify filtered Sites in 'My Sites' Dashlet
	public void verifyFilteredRecentSitesInMySitesDashlet() {

		try {
			ArrayList<String> expectedSitesList = appSitePg.getSitesFromRecentSites();
			ArrayList<String> actualSitesList = appHomePg.getSitesFromMySitesDashletInHomePage();

			if (UIHelper.compareTwoDiffSizeOfLists(expectedSitesList, actualSitesList)) {
				report.updateTestLog("Verify user is able to view all filtered sites by Filter: 'Recent'",
						"User viewed filtered recent sites successfully" + "<br /><b>Expected Recent Sites: "
								+ expectedSitesList + "<br /><b>Actual Recent Sites: " + actualSitesList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify user is able to view all filtered sites by Filter: 'Recent'",
						"User failed to view filtered recent sites" + "<br /><b>Expected Recent Sites: "
								+ expectedSitesList + "<br /><b>Actual Recent Sites: " + actualSitesList,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify filtered Sites in 'My Sites' Dashlet
	public void verifySiteInMySitesDashlet(String expectedSiteName) {

		try {

			if (appHomePg.checkSiteInMySitesDashlet(expectedSiteName)) {
				report.updateTestLog("Verify site in 'My Sites' Dashlet",
						"Site: " + expectedSiteName + " is displayed successfully in 'My Sites' Dashlet", Status.PASS);
			} else {
				report.updateTestLog("Verify site in 'My Sites' Dashlet",
						"Site: " + expectedSiteName + " is failed to display in 'My Sites' Dashlet", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify filtered Sites in 'My Sites' Dashlet
	public void verifySiteInMySitesDashletForNegativeCase(String expectedSiteName) {

		try {

			if (!appHomePg.checkSiteInMySitesDashlet(expectedSiteName)) {
				report.updateTestLog("Verify site in 'My Sites' Dashlet",
						"Site: " + expectedSiteName + " is not displayed in 'My Sites' Dashlet", Status.PASS);
			} else {
				report.updateTestLog("Verify site in 'My Sites' Dashlet",
						"Site: " + expectedSiteName + " is displayed in 'My Sites' Dashlet", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Favorite Site when user take cusor to the star filled with yellow
	// in 'My Sites' Dashlet
	public void verifyFavSiteWhenPlaceCursorOnStar(String expectedSiteName) {
		try {
			if (appHomePg.checkFavoriteSite(expectedSiteName)) {
				report.updateTestLog(
						"Verify Favorite Site in 'My Sites' dashlet when user take cusor to the star filled with yellow",
						"User is seen message as 'Remove site from favorites' and 'x' icon on the favorite site: "
								+ expectedSiteName,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Favorite Site in 'My Sites' dashlet when user take cusor to the star filled with yellow",
						"User failed to see the message as 'Remove site from favorites' and 'x' icon on the favorite site: "
								+ expectedSiteName,
						Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify filtered Documents in 'My Documents' Dashlet
	public void verifyFilteredDocumentsInMySitesDashlet(String documentName, String filterOption) {

		try {
			if (appHomePg.isDisplayedFavoriteDocumentInHomePage(documentName)) {
				report.updateTestLog("Verify filtered Documents", "User viewed filtered document: " + documentName
						+ " successfully using filter option: " + filterOption, Status.PASS);
			} else {
				report.updateTestLog("Verify filtered Documents", "User failed to view filtered document: "
						+ documentName + " using filter option: " + filterOption, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to verify Task is assigned to the user
	public void verifyTaskAssginedToUser() {

		try {
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String taskName = message + "_" + currentDate;
			boolean isDispTaskInMyTasksDashlet = appHomePg.checkTaskInMyTasksDashlet(taskName);
			if (isDispTaskInMyTasksDashlet) {
				report.updateTestLog("Verify the User is able to recieve the assigned Task in My Dashlet",
						"Tasks has been received successfully in the My Tasks Dashlet", Status.PASS);
			} else {
				report.updateTestLog("Verify the User is able to recieve the assigned Task in My Dashlet",
						"Tasks failed to display in the My Tasks Dashlet", Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyTooltipTextForSiteMenu(String user) {
		try {
			List<WebElement> siteElts = UIHelper.findListOfElementsbyXpath(siteMenuTitleXpath, driver);
			for (WebElement siteEle : siteElts) {
				UIHelper.highlightElement(driver, siteEle);
				UIHelper.mouseOveranElement(driver, siteEle);
				siteTitle.add(siteEle.getAttribute("title").trim());
				UIHelper.waitFor(driver);
			}
			List<WebElement> siteTextElts = UIHelper.findListOfElementsbyXpath(siteMenuLinkTextXpath, driver);
			for (WebElement siteTextItems : siteTextElts) {
				UIHelper.highlightElement(driver, siteTextItems);
				UIHelper.mouseOveranElement(driver, siteTextItems);
				siteLink.add(siteTextItems.getText().trim());
				UIHelper.waitFor(driver);

			}
			if (UIHelper.compareTwoSimilarLists(siteTitle, siteLink)) {
				report.updateTestLog("Verify tooltip information is displayed correctly instead of" + "<br>"
						+ "javascript label on hovering all the sub menus present under Sites for " + user + " Users",
						"Tooltip is displayed successfully for all submenus under Sites Menu for " + user + " Users"
								+ "<br><b>:" + siteTitle + "</b>",
						Status.PASS);

			} else {
				report.updateTestLog("Verify tooltip information is displayed correctly instead of " + "<br>"
						+ "javascript label on hovering all the sub menus present under Sites for " + user + " Users",
						"Tooltip is failed to display for all submenus under Sites Menu for " + user + " Users"
								+ "<br><b>:" + siteTitle + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify tooltip information is displayed correctly instead of" + "<br>"
					+ "javascript label on hovering all the sub menus present under Sites for " + user + " Users",
					"Tooltip is failed to display for all submenus under Sites Menu for " + user + " Users" + "<br><b>:"
							+ siteTitle + "</b>",
					Status.FAIL);
		}

	}

	public void verifyTooltipTextForTasksMenu(String user) {
		try {

			List<WebElement> taskElts = UIHelper.findListOfElementsbyXpath(taskMenuTitleXpath, driver);
			for (WebElement taskEle : taskElts) {
				UIHelper.highlightElement(driver, taskEle);
				UIHelper.mouseOveranElement(driver, taskEle);
				taskTitle.add(taskEle.getAttribute("title").trim());
				UIHelper.waitFor(driver);
			}
			List<WebElement> taskTextElts = UIHelper.findListOfElementsbyXpath(taskMenuLinktxtXpath, driver);
			for (WebElement taskTextItems : taskTextElts) {
				UIHelper.highlightElement(driver, taskTextItems);
				UIHelper.mouseOveranElement(driver, taskTextItems);
				taskLink.add(taskTextItems.getText().trim());
				UIHelper.waitFor(driver);

			}
			if (UIHelper.compareTwoSimilarLists(taskTitle, taskLink)) {
				report.updateTestLog("Verify tooltip information is displayed correctly instead of" + "<br>"
						+ "javascript label on hovering all the sub menus present under Tasks for " + user + " Users",
						"Tooltip is displayed successfully for all submenus under Tasks Menu for " + user + " Users"
								+ "<br><b>:" + taskTitle + "</b>",
						Status.PASS);

			} else {
				report.updateTestLog("Verify tooltip information is displayed correctly instead of" + "<br>"
						+ "javascript label on hovering all the sub menus present under Tasks for " + user + " Users",
						"Tooltip is failed to display for all submenus under Tasks Menu for " + user + " Users"
								+ "<br><b>:" + taskTitle + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify tooltip information is displayed correctly instead of" + "<br>"
					+ "javascript label on hovering all the sub menus present under Tasks for " + user + " Users",
					"Tooltip is failed to display for all submenus under Tasks Menu for " + user + " Users" + "<br><b>:"
							+ taskTitle + "</b>",
					Status.FAIL);
		}

	}

	public void verifyTooltipTextForUserMenu(String user) {
		try {

			List<WebElement> userElts = UIHelper.findListOfElementsbyXpath(userMenuTitleXpath, driver);
			for (WebElement userEle : userElts) {
				UIHelper.highlightElement(driver, userEle);
				UIHelper.mouseOveranElement(driver, userEle);
				userMenuTitle.add(userEle.getAttribute("title").trim());
				UIHelper.waitFor(driver);
			}
			List<WebElement> userTextElts = UIHelper.findListOfElementsbyXpath(userMenuTitleLinktxtXpath, driver);
			for (WebElement userTextItems : userTextElts) {
				UIHelper.highlightElement(driver, userTextItems);
				UIHelper.mouseOveranElement(driver, userTextItems);
				userMenuLink.add(userTextItems.getText().trim());
				UIHelper.waitFor(driver);

			}
			if (UIHelper.compareTwoSimilarLists(userMenuTitle, userMenuLink)) {
				report.updateTestLog("Verify tooltip information is displayed correctly instead of" + "<br>"
						+ "javascript label on hovering all the sub menus present under UserMenu for " + user + " Users",
						"Tooltip is displayed successfully for all submenus under UserMenu  for " + user + " Users"
								+ "<br><b>:" + userMenuTitle + "</b>",
						Status.PASS);

			} else {
				report.updateTestLog("Verify tooltip information is displayed correctly instead of" + "<br>"
						+ "javascript label on hovering all the sub menus present under UserMenu for " + user + " Users",
						"Tooltip is failed to display for all submenus under UserMenu for " + user + " Users"
								+ "<br><b>:" + userMenuTitle + "</b>",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify tooltip information is displayed correctly instead of" + "<br>"
					+ "javascript label on hovering all the sub menus present under UserMenu for " + user + " Users",
					"Tooltip is failed to display for all submenus under UserMenu for " + user + " Users" + "<br><b>:"
							+ userMenuTitle + "</b>",
					Status.FAIL);
		}

	}
}
