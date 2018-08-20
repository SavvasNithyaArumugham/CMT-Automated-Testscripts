package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**
 * This class use to do the Pearson Email verification part.
 * 
 * @author 412766
 * 
 */
public class GmailVerificationPage extends ReusableLibrary {

	private String gmailSubjectXpath = ".//*[contains(text(), 'Mail Subject')]";
	private String mailIconXpath = ".//*[@id='gbq1']/div/a/span";
	private String searchinputXpath = ".//*[@id='gbqfq']";
	private String searchBtnXpath = ".//*[@id='gbqfb']";

	private String searchMailListXpath = ".//span[contains(.,'You have been assigned a task')]";
	private String searchMailXpath = ".//tr[1]//span[contains(.,'You have been assigned') and contains(.,'CRAFT')]";
	private String searchRecentActivityXpath = ".//table//tr//td//span[contains(.,'Alfresco Share: Recent Activities')]";
	private String searchResultMailXpath = "//table//tr/td[6]//span[text()='Simple Task Completed']";
	private String mailContentXpath = "//table//p[3]";
	private String recentMailUserXpath = "//table//a[1]";
	private String recentMailActionXpath = "//table//a[2]";
	private String recentMailDocXpath = "//table//a[3]";
	private String mailStatusXpath = "//table//p[2][contains(.,'Simple Task Completed')]";
	private String searchResultMailNewXpath = "//table//tr/td[6]//span//b[contains(text(),'Simple Task Completed')]";
	private String srcResultMailXpath = ".//*[@class='BltHke nH oy8Mbf' and @role='main']//table//tr";
	private String searchOptionsXpath = ".//*[@class='BltHke nH oy8Mbf' and @role='main']//table//tr[contains(.,'search options')]";

	private String latestmailXpath = ".//*[contains(@role,'main')]//table//tr[1]";
	public String fileURLXpath = "//table//a[contains(.,'site')]";
	private String RecEnvURLXpath = "//table//a[contains(.,'notifications')]";
	private String commonMailXpath = "//table//tr/td[6]//span//b[contains(text(),'CRAFT') and contains(text(),'REPLACE')]";
	private String commonMailCheckXpath = "//div[@role='main']//table//tr/td[6]//div[@role='link']//b[contains(text(),'CRAFT')]";
	// table//tr/td[6]//span//b[contains(text(),'CRAFT')]

	private String editlinkXpath = "//table//a[contains(.,'task-edit')]";
	private String filenameXpath = "//table/tbody/tr[1]/td/b[contains(text(),'CRAFT')]";
	private String priorityXpath = "//table/tbody/tr/td/div/p[5]";
	private String tasknameXpath = "//table/tbody/tr/td/div/p[4]";
	private String commentsXpath = ".//*[@style='border:2px solid black']//td[contains(.,'CRAFT')]";
	private String taskCommentsXpath = ".//*[@style='border:2px solid black']//td[contains(.,'CRAFT')]";

	/*** SiteMember ***/
	private String siteMemberXpath = ".//*[@class='dashlet colleagues']//div//h3/a[contains(text(),'CRAFT')]";
	private String siteDeclineButtonXpath = ".//*[@id='template_x002e_reject-invite_x002e_reject-invite_x0023_default-decline-button']";
	private String searchSiteInviteXpath = ".//*[@class='nH']/*[contains(@class,'BltHke') and not(contains(@style,'display: none;'))]//table//tr//td[6]//span[contains(.,'CRAFT') and contains(.,'Alfresco Share')]";
	private String actionInviteXpath = "//table//a[contains(.,'CRAFT')]";
	private String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";

	/*** SiteMail Format ***/
	private String saluationXpath = "//table/tbody/tr/td/div/p[1]";
	private String bodyXpath = "//table/tbody/tr/td/div/p[3]";
	private String linkXpath = "//table/tbody/tr/td/div/p[6]";
	private String thankXpath = "//table/tbody/tr/td/div/p[7]";
	private String mediaHubTaskCommentsXpath = ".//*[@style='width:100%']//td[contains(.,'CRAFT')]";
	private String timestampXpath = ".//table/tbody/tr/td[2]/div[@class='gK']/span[@class='g3']";
	private String tempTimeStampXpath = "//span[@class='g3']";

	public String shareBoxactivityxpath = ".//div[contains(@style,'background-color:rgb(0,122,255);')]";

	public String shareBoxmaildatexpath = ".//span[contains(@alt,'CRAFT')]";

	
/*** Gmail Format ***/
	
	private String gmailSubjectHeaderXpath = "//div[@class='ha']/h2";
	private String gmailAccessExpireXpath = "//div[@style='margin-top:20px']";
	private String bestRegardsXpath = "//div[@style='margin-top:20px']/following-sibling::p";
	private String gmailContentXpath = "//div[@class='ii gt']//p[1]";
	
	public GmailVerificationPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

	}

	/**
	 * Method to open the Pearson Gmail link.
	 * 
	 * @author 412766
	 */
	public void openGmail() {
		driver = new ChromeDriver();
		driver.get(Settings.getInstance().getProperty("GmailUrl"));
		UIHelper.waitForPageToLoad(driver);
		driver.manage().window().maximize();
	}

	public void openGmailNew() {

		driver.get(Settings.getInstance().getProperty("GmailUrl"));
		UIHelper.waitForPageToLoad(driver);
		driver.manage().window().maximize();
	}

	/**
	 * Method to enter the credentials.
	 * 
	 * @author 412766
	 */
	public void enterCredentials() {
		login();
	}

	/**
	 * Method to verify the mail received or not based on the mail subject
	 * 
	 * @param mailSubject
	 *            - Mail subject to verify
	 * @author 412766
	 */
	public void checkEmailSubject(String mailSubject) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, mailIconXpath);
			UIHelper.highlightElement(driver, mailIconXpath);
			gmailSubjectXpath = gmailSubjectXpath.replaceAll("Mail Subject", mailSubject);

			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(gmailSubjectXpath, driver);
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify Pearson mail", "Mail received successfully", Status.DONE);
			} else {
				report.updateTestLog("Verify Pearson mail", "Mail not received successfully", Status.FAIL);
			}
			UIHelper.waitFor(driver);
			driver.quit();
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", e.getMessage(), Status.FAIL);
		}
	}

	private void login() {
		String userName = dataTable.getData("Gmail", "Username");
		String password = Settings.getInstance().getProperty("GMailpassword");
		performLogin(userName, password);
	}

	private void performLogin(String userName, String password) {
		try {
			/*
			 * driver.findElement(By.id("UsernameTextBox")).sendKeys(userName);
			 * driver.findElement(By.id("PasswordTextBox")).sendKeys(password);
			 * driver.findElement(By.id("SubmitButton")).click();
			 */

			driver.findElement(By.id("user-name-txt")).sendKeys(userName);
			driver.findElement(By.id("pwd-txt")).sendKeys(password);
			driver.findElement(By.id("signin-button")).click();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Login into Pearson mail",
					"Enter login credentials: " + "<br /><b>Username : </b>" + userName, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Login into Pearson mail", "Login to Gmail failed", Status.FAIL);
		}
	}

	public void searchWFStatusmessage(String Msg) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			// String Msg = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchResultMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify WF task Accepted",
						"WF task Accepted successfully" + "<br /><b> Task Msg : </b>" + Msg, Status.PASS);
			} else {
				report.updateTestLog("Verify WF task Accepted", "WF task Accepted failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "Mail not recieved", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	public void searchWFStatusmessageNew(String Msg) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			// String Msg = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchResultMailNewXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify WF task Accepted",
						"WF task Accepted successfully" + "<br /><b> Task Msg : </b>" + Msg, Status.PASS);
			} else {
				report.updateTestLog("Verify WF task Accepted", "WF task Accepted failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "Mail not recieved", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	// Method to locate the Mail with WF task msg
	public void searchmailwithWFmessage(String Msg) {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			// String Msg = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String site = dataTable.getData("Sites", "SiteName");
			String finalsearchMailXpath = searchMailXpath.replace("CRAFT", Msg);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(finalsearchMailXpath, driver);
			UIHelper.waitFor(driver);

			if (subjectList.size() > 0) {

				System.out.println(subjectList.get(1));
				subjectList.get(1).click();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail", "Mail received successfully", Status.DONE);
				if (UIHelper.findAnElementbyXpath(driver, mailContentXpath).getText().contains(Msg)) {
					report.updateTestLog("Verify Mail received for WF",
							"Mail received successfully" + "<br /><b> Task Msg : </b>"
									+ UIHelper.findAnElementbyXpath(driver, mailContentXpath).getText(),
							Status.PASS);
				} else {
					report.updateTestLog("Verify Mail received for WF", "Mail not received", Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Pearson mail", "Mail not received ", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "Mail not received ", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	public void searchRecentActivityMail() {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String Msg = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchRecentActivityXpath, driver);
			System.out.println(subjectList.size()+ ""+ subjectList.get(0));
			UIHelper.waitFor(driver);
			if (subjectList != null && subjectList.size() > 0) {
				subjectList.get(0).click();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson Recent Activity mail", "Mail received successfully", Status.DONE);
				List<WebElement> userList = UIHelper.findListOfElementsbyXpath(recentMailUserXpath, driver);
				List<WebElement> actionList = UIHelper.findListOfElementsbyXpath(recentMailActionXpath, driver);
				List<WebElement> docList = UIHelper.findListOfElementsbyXpath(recentMailDocXpath, driver);
				System.out.println("*********************************");
				System.out.println(userList.size() + " " + userList.get(0).getText());
				System.out.println(actionList.size() + " " + actionList.get(0));
				System.out.println(docList.size() + " " + docList.get(0));
				System.out.println("*********************************");
				UIHelper.waitFor(driver);
				if (userList != null && actionList != null && docList != null) {

					report.updateTestLog("Verify Pearson Recent Activity mail", "Mail received successfully",
							Status.PASS);

					for (int i = 0; i < userList.size(); i++) {
						if (userList.get(i).getText().contains("Kunal Mehrotra")) {
							UIHelper.scrollToAnElement(userList.get(i));
							UIHelper.highlightElement(driver, userList.get(i));
							UIHelper.clickanElement(docList.get(i));

							report.updateTestLog("Verify user should be navigated Site from the Email ",
									"Naviagation from mail was successfully. User name : " + userList.get(i).getText()
											+ " Site Name  : " + docList.get(i).getText(),
									Status.PASS);

							break;

						}
					}
				} else {
					report.updateTestLog("Verify Pearson Recent Activity mail", "Mail not received successfully",
							Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Pearson Recent Activity mail", "Mail not received successfully",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson Recent Activity mail", e.getMessage(), Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	public void searchFileURLLink(String Msg) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			// String Msg = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String site = dataTable.getData("Sites", "SiteName");
			String finalsearchMailXpath = searchMailXpath.replace("CRAFT", Msg);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(finalsearchMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				subjectList.get(1).click();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail", "Mail received successfully", Status.DONE);
				if (UIHelper.findAnElementbyXpath(driver, fileURLXpath).isDisplayed()) {
					UIHelper.highlightElement(driver, fileURLXpath);
					UIHelper.findAnElementbyXpath(driver, fileURLXpath).click();
					report.updateTestLog("Verify File URL in Pearson mail",
							"Mail received successfully" + "<br /><b>File URL : </b>"
									+ UIHelper.findAnElementbyXpath(driver, fileURLXpath).getText(),
							Status.PASS);
				} else {
					report.updateTestLog("Verify File URL in Pearson mail", "Mail not received successfully",
							Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Pearson mail", "Mail not received successfully", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", e.getMessage(), Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	public void searchTaskActionNotifyeMail() {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String message = dataTable.getData("Workflow", "Message");
			String currentDate = new DateUtil().getCurrentDate();
			String Msg = message + "_" + currentDate;

			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchMailListXpath, driver);
			UIHelper.waitFor(driver);
			System.out.println("" + subjectList.size());
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify notification email sent for Task ",
						"email received successfully" + "<br /><b> Task Msg : </b>" + Msg, Status.PASS);
			} else {
				report.updateTestLog("Verify notification email sent for Task ",
						"email NOT received " + "<br /><b> Task Msg : </b>" + Msg, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "Mail not recieved", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	// Method to locate the Mail with site name
	public void searchmailwithsiteNameAction() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String siteName = new FileUtil().readDataFromFile(testOutputFilePath);
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, siteName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalsiteresultXpath = searchSiteInviteXpath.replace("CRAFT", siteName);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(finalsiteresultXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				subjectList.get(0).click();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson mail", "Mail received successfully", Status.PASS);
				String actionName = dataTable.getData("Sites", "FileName");
				String finalactionInviteXpath = actionInviteXpath.replace("CRAFT", actionName);

				if (UIHelper.checkForAnElementbyXpath(driver, finalactionInviteXpath)) {
					UIHelper.highlightElement(driver, finalactionInviteXpath);
					UIHelper.click(driver, finalactionInviteXpath);
					UIHelper.waitFor(driver);
					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);

					report.updateTestLog("Verify Leave from Site",
							"User removed successfully from site" + "<br /><b> Site Neme : </b>" + siteName,
							Status.PASS);

					if (UIHelper.checkForAnElementbyXpath(driver, ".//*[@id='UsernameTextBox']")) {
						String userName = dataTable.getData("General_Data", "Username");
						String password = dataTable.getData("General_Data", "Password");

						driver.findElement(By.id("UsernameTextBox")).sendKeys(userName);
						driver.findElement(By.id("PasswordTextBox")).sendKeys(password);
						driver.findElement(By.id("SubmitButton")).click();
						UIHelper.waitFor(driver);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, ".//*[@id='loader']");
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitFor(driver);

						report.updateTestLog("Login", "Enter login credentials: " + "Username = " + userName,
								Status.DONE);
					}

				} else {
					report.updateTestLog("Verify Leave from Site", "User failed to leave from site", Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Pearson mail", "Mail not received ", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "Mail not received ", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	// Method to locate the Mail with site name
	public void searchmailwithsiteName() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String siteName = new FileUtil().readDataFromFile(testOutputFilePath);
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, siteName);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			String finalsiteresultXpath = searchSiteInviteXpath.replace("CRAFT", siteName);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(finalsiteresultXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {

				report.updateTestLog("Verify Mail received for Site Invite",
						"Site invite mail received successfully" + "<br /><b> Site Neme : </b>" + siteName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Mail received for Site Invite ", "Mail not received", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "Mail not received ", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	public void verifySiteMember() {
		try {
			String siteMember = dataTable.getData("Sites", "InviteUserName");
			String finalsiteresultXpath = siteMemberXpath.replace("CRAFT", siteMember);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalsiteresultXpath);
			if (UIHelper.findAnElementbyXpath(driver, finalsiteresultXpath).getText().equalsIgnoreCase(siteMember)) {
				UIHelper.highlightElement(driver, finalsiteresultXpath);
				report.updateTestLog("Verify Accept Site Invitation ",
						"Accepted Site Invitation successfully" + "<br/><b>Expected Site Member : </b>" + siteMember
								+ "<br/><b>Actual Source Code Value:</b>"
								+ UIHelper.findAnElementbyXpath(driver, finalsiteresultXpath).getText(),
						Status.PASS);

			} else {

				report.updateTestLog("Verify Accept Site Invitation ",
						"Accepted Site Invitation failed" + "<br/><b>Expected Site Member : </b>" + siteMember
								+ "<br/><b>Actual Source Code Value:</b>"
								+ UIHelper.findAnElementbyXpath(driver, finalsiteresultXpath).getText(),
						Status.FAIL);

			}

		} catch (Exception e) {
			report.updateTestLog("Verify Accept Site Invitation ", "Accepted Site Invitation failed", Status.FAIL);
		}

	}

	public void verifyRejectSiteInvite() {
		try {
			String siteMember = dataTable.getData("Sites", "InviteUserName");
			String siteName = new FileUtil().readDataFromFile(testOutputFilePath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteDeclineButtonXpath);
			UIHelper.click(driver, siteDeclineButtonXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Verify Reject Site Invitation ",
					"Rejected Site Invitation successfully" + "<br/><b>Expected Site Member : </b>" + siteMember
							+ "<br/><b>Rejected Site Name ::</b>" + siteName,
					Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Verify Accept Site Invitation ", "Rejected Site Invitation failed", Status.FAIL);
		}

	}

	public void performLoginAsDiffUser(String userName, String password) {
		try {
			driver.findElement(By.name("UsernameTextBox")).sendKeys(userName);
			driver.findElement(By.name("PasswordTextBox")).sendKeys(password);
			driver.findElement(By.id("SubmitButton")).click();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Login into Pearson mail as different user ",
					"Enter login credentials: " + "<br /><b>Username : </b>" + userName, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Login into Pearson mail", "Login to Gmail failed", Status.FAIL);
		}
	}

	// recent activity mail open
	public void recentActivityMailURL() {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			String Msg = dataTable.getData("Workflow", "Message");
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(searchRecentActivityXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList != null && subjectList.size() > 0) {
				subjectList.get(0).click();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Pearson Recent Activity mail", "Mail received successfully", Status.DONE);

			} else {
				report.updateTestLog("Verify Pearson Recent Activity mail", "Mail not received successfully",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson Recent Activity mail", e.getMessage(), Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	// recent activity mail to verify URL
	public void verifyRecActURL() {
		try {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, RecEnvURLXpath);
			} catch (Exception e) {
			}
			if (UIHelper.checkForAnElementbyXpath(driver, RecEnvURLXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, RecEnvURLXpath));
				UIHelper.highlightElement(driver, RecEnvURLXpath);
				UIHelper.click(driver, RecEnvURLXpath);
				UIHelper.waitForPageToLoad(driver);
				report.updateTestLog("Verify Notification Link",
						"Notification URL available" + "<br><b>Notification Link : </b>"
								+ UIHelper.findAnElementbyXpath(driver, RecEnvURLXpath).getText(),
						Status.PASS);

			} else {
				report.updateTestLog("Verify Pearson Recent Activity mail", "Mail not received successfully",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson Recent Activity mail", e.getMessage(), Status.FAIL);
		}
	}

	// Commonmethod for checking mail by subject
	public void commonMethodforMail(String Subject, String Msg) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String site = dataTable.getData("Sites", "SiteName");
			String finalcommonMailXpath = commonMailXpath.replace("CRAFT", Subject).replace("CRAFT", site);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(finalcommonMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify WF task mail", "WF task mail recieved successfully"
						+ "<br /><b> Suject : </b>" + Subject + "<br /><b> Task Msg : </b>" + Msg, Status.PASS);
			} else {
				report.updateTestLog("Verify WF task mail ", "WF task mail not recieved", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify WF task mail ", "WF task mail not recieved", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	// Commonmethod for checking mail by subject
	public void commonMethodforMailchecking(String Subject, String Msg) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			// String site = dataTable.getData("Sites", "SiteName");
			String finalcommonMailXpath = commonMailCheckXpath.replace("CRAFT", Subject);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(finalcommonMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				System.out.println(subjectList.size());
				subjectList.get(0).click();
				UIHelper.waitForPageToLoad(driver);
				report.updateTestLog("Verify WF task mail", "WF task mail recieved successfully"
						+ "<br /><b> Suject : </b>" + Subject + "<br /><b> Task Msg : </b>" + Msg, Status.PASS);
			} else {
				report.updateTestLog("Verify WF task mail ", "WF task mail not recieved", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify WF task mail ", "WF task mail not recieved", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	// Common method for checking mail by subject
	public void commonMethodforNoMail(String Subject, String Msg) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String site = dataTable.getData("Sites", "SiteName");
			String finalcommonMailXpath = commonMailXpath.replace("CRAFT", Subject).replace("CRAFT", site);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(finalcommonMailXpath, driver);
			UIHelper.waitFor(driver);
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify WF task", "WF task mail recieved" + "<br /><b> Task Msg : </b>" + Msg,
						Status.FAIL);
			} else {
				report.updateTestLog("Verify WF task mail not received",
						"WF task mail not recieved" + "<br /><b> Task Msg : </b>" + Msg, Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify WF task mail ", "WF task mail not recieved", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	// method for checking mail format
	public void checkMailFormat(String filename, String Msg) {
		try {
			String finalfilenameXpath = filenameXpath.replace("CRAFT", filename);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalfilenameXpath);
			UIHelper.highlightElement(driver, finalfilenameXpath);
			UIHelper.highlightElement(driver, editlinkXpath);
			UIHelper.highlightElement(driver, priorityXpath);
			UIHelper.highlightElement(driver, fileURLXpath);
			UIHelper.highlightElement(driver, tasknameXpath);

			report.updateTestLog("Verify Mail Format", "Mail Format verified" + "<br /><b> Task Msg : </b>" + Msg,
					Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Verify Mail Format", "Mail Format failed", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	public void searchWFmessage(String Msg) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, Msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, srcResultMailXpath);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(srcResultMailXpath, driver);

			/*
			 * List<WebElement> subjectList
			 * =driver.findElements(By.cssSelector("div.xT>div.y6>span>b"));
			 */
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify Pearson mail ",
						"Maile recieved successfully" + "<br /><b> Mail Subject : </b>" + Msg, Status.PASS);
			} else {
				report.updateTestLog("Verify Pearson mail", "Mail not recieved", Status.FAIL);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, latestmailXpath);
			UIHelper.highlightElement(driver, latestmailXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, latestmailXpath);
			UIHelper.waitFor(driver);
			report.updateTestLog("Open recieved mail", "Mail opened successfully", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Open recieved mail", "FAILED. Mail not opened", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	public void searchAndOpenMail(String msg) {
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(srcResultMailXpath, driver);

			/*
			 * List<WebElement> subjectList
			 * =driver.findElements(By.cssSelector("div.xT>div.y6>span>b"));
			 */
			if (subjectList.size() > 0) {
				report.updateTestLog("Verify Pearson mail",
						"Mail received successfully" + "<br /><b> Mail Msg : </b>" + msg, Status.PASS);
			} else {
				report.updateTestLog("Verify Pearson mail", "Mail not recieved", Status.FAIL);
			}

			UIHelper.waitForVisibilityOfEleByXpath(driver, latestmailXpath);
			UIHelper.highlightElement(driver, latestmailXpath);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, latestmailXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("Verify Pearson mail", "FAILED. Mail not recieved", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	public void verifyCommentsinMail(String Msg) {
		try {
			String finalCommentsXpath = commentsXpath.replace("CRAFT", Msg);
			String finalTaskCommentsXpath = taskCommentsXpath.replace("CRAFT", Msg);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, finalCommentsXpath)
					|| UIHelper.checkForAnElementbyXpath(driver, finalTaskCommentsXpath)) {
				UIHelper.highlightElement(driver, finalCommentsXpath);
				UIHelper.highlightElement(driver, finalTaskCommentsXpath);
				report.updateTestLog("Verify comments in mail", "Comments matched with the entered comments",
						Status.PASS);
			} else {
				report.updateTestLog("Verify comments in mail", "Comments is not matched with the entered comments",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify comments in mail", "Mail not recieved", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	// method for checking mail format
	public void checkMailFormatforValues() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, saluationXpath);

			UIHelper.highlightElement(driver, saluationXpath);
			UIHelper.highlightElement(driver, bodyXpath);
			UIHelper.highlightElement(driver, linkXpath);
			UIHelper.highlightElement(driver, thankXpath);

			report.updateTestLog("Verify Mail Format", "Mail Format verified", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Verify Mail Format", "Mail Format failed", Status.FAIL);
		} finally {
			// driver.quit();
		}
	}

	public boolean checkMailCount(String msg) {
		boolean flag = false;
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(srcResultMailXpath, driver);

			System.out.println(subjectList.size());
			if (subjectList.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
		} finally {
			// driver.quit();
		}
		return flag;
	}

	public boolean checkSearchOptions() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, searchOptionsXpath)) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {

			flag = false;
		}
		return flag;
	}

	public void verifyCommentsInMediaHubMail(String comments) {
		try {

			String finalTaskCommentsXpath = mediaHubTaskCommentsXpath.replace("CRAFT", comments);

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, finalTaskCommentsXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalTaskCommentsXpath));
				UIHelper.highlightElement(driver, finalTaskCommentsXpath);
				report.updateTestLog("Verify comments in mail", "Comments matched with the entered comments",
						Status.PASS);
			} else {
				report.updateTestLog("Verify comments in mail",
						"Comments is not matched with the entered comments<br><b>Comments :</b>" + comments,
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify comments in mail", "Mail not recieved", Status.FAIL);
		}
	}

	public boolean checkMailCount(String msg, int count) {
		boolean flag = false;
		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, searchinputXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, searchinputXpath, msg);
			UIHelper.click(driver, searchBtnXpath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			List<WebElement> subjectList = UIHelper.findListOfElementsbyXpath(srcResultMailXpath, driver);
			int mailCount = subjectList.size();
			System.out.println(subjectList.size());
			if (mailCount > count) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
		} finally {
			// driver.quit();
		}
		return flag;
	}

	public void verifyTimestampinMail(String time) {
		try {
			UIHelper.waitForPageToLoad(driver);
			String tempExpDate[] = time.split(",");
			String expDate = tempExpDate[1].trim().substring(0, 3) + " " + tempExpDate[1].trim().replaceAll("\\D+", "");
			UIHelper.waitForVisibilityOfEleByXpath(driver, timestampXpath);
			String timestamp = UIHelper.getAttributeFromWebElement(driver, timestampXpath, "title");
			if (timestamp.contains(expDate)) {
				report.updateTestLog("Verify timestamp match in mail", "Time stamp of the mail received present",
						Status.PASS);
			} else {
				report.updateTestLog("Verify timestamp match in mail", "Time stamp of the mail is not received present",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.getMessage();
			report.updateTestLog("Verify timestamp match in mail", "Time stamp of the mail received not present",
					Status.FAIL);
		}

	}

	public boolean verifyTimeStampMatchInMail(String date) {
		boolean flag = false;
		String tempExpDate[] = date.split(",");
		String expDate = tempExpDate[1].trim().substring(0, 3) + " " + tempExpDate[1].trim().replaceAll("\\D+", "");
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, tempTimeStampXpath);
			String timestamp = UIHelper.getAttributeFromWebElement(driver, tempTimeStampXpath, "title");
			if (timestamp.contains(expDate)) {
				flag = true;
			}
		} catch (Exception e) {
			e.getMessage();
			report.updateTestLog("Verify timestamp match in mail", "Time stamp of the mail recieved not present",
					Status.FAIL);
		}

		return flag;

	}
	
public void verifyGmailsubject(String message){
		
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, gmailSubjectHeaderXpath);
			String subject = UIHelper.getTextFromWebElement(driver, gmailSubjectHeaderXpath);
			
			if(subject.contains(message)){
				report.updateTestLog("Verify subject in mail", "<b> Subject: </b>" +message+ "matched",
						Status.PASS);
			}else{
				report.updateTestLog("Verify subject in mail", "<b> Subject: </b>" +message+ "not matched",
						Status.FAIL);
			}
			
		}catch(Exception e){
			report.updateTestLog("Verify subject in mail", "<b> Subject: </b>" +message+ "not matched",
					Status.FAIL);
		}
	}
	
	public void verifyGmailAccessExpire(String message){
		
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, gmailAccessExpireXpath);
			String subject = UIHelper.getTextFromWebElement(driver, gmailAccessExpireXpath);
			
			if(subject.contains(message)){
				report.updateTestLog("Verify link access expire in mail", "<b> Link Access expire: </b>" +message+ "present",
						Status.PASS);
			}else{
				report.updateTestLog("Verify link access expire in mail", "<b> Link Access expire: </b>" +message+ "not present",
						Status.FAIL);
			}
			
		}catch(Exception e){
			report.updateTestLog("Verify link access expire in mail", "<b> Link Access expire: </b>" +message+ "not present",
					Status.FAIL);
		}
	}
	
	public void verifyBestRegards(String message){
		
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, bestRegardsXpath);
			String subject = UIHelper.getTextFromWebElement(driver, bestRegardsXpath);
			
			if(subject.contains(message)){
				report.updateTestLog("Verify best regards in mail", "<b> Best regards: </b>" +message+ "present",
						Status.PASS);
			}else{
				report.updateTestLog("Verify best regards in mail", "<b> Best regards: </b>" +message+ "not present",
						Status.FAIL);
			}
			
		}catch(Exception e){
			report.updateTestLog("Verify link access expire in mail", "<b> Best regards: </b>" +message+ "not present",
					Status.FAIL);
		}
	}
	
	
	
public void verifyGmailContent(String message){
		
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, gmailContentXpath);
			String subject = UIHelper.getTextFromWebElement(driver, gmailContentXpath);
			
			if(subject.contains(message)){
				report.updateTestLog("Verify content in mail", "<b> Content: </b>" +message+ "matched",
						Status.PASS);
			}else{
				report.updateTestLog("Verify content in mail", "<b> Content: </b>" +message+ "not matched",
						Status.FAIL);
				System.out.println();
			}
			
		}catch(Exception e){
			report.updateTestLog("Verify content in mail", "<b> Content: </b>" +message+ "not matched",
					Status.FAIL);
		}
	}

}
