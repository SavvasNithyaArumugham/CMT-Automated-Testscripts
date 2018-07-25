package com.pearson.automation.alfresco.pages;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * Functional Components class
 * @author Cognizant
 */
public class AlfrescoUserNamePage extends ReusableLibrary
{
	private String userProfileXpath = ".//*[@id='HEADER_USER_MENU_PROFILE_text']/a";
	
	private String notificationsLinkXpath = "//a[contains(text(),'Notifications')]";
	private String emailCheckBoxXpath = ".//*[@id='user-notifications-email']";
	private String okButtonXpath = ".//button[contains(text(),'OK')]";
	private String notificationAlertMessageXpath = ".//*[@id='message']/div";
	
	/**
	 * Constructor to initialize the component library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public AlfrescoUserNamePage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
	}
	
	public String getUserProfileOptionName()
	{
		UIHelper.waitForVisibilityOfEleByXpath(driver, userProfileXpath);
		return UIHelper.getTextFromWebElement(driver, userProfileXpath);
	}
	
	public void receiveEmailNotifications(String status) {

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					notificationsLinkXpath);
			UIHelper.highlightElement(driver, notificationsLinkXpath);
			UIHelper.click(driver, notificationsLinkXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, emailCheckBoxXpath);
			
			if(status.equalsIgnoreCase("Enable"))
			{
				if (!UIHelper.findAnElementbyXpath(driver, emailCheckBoxXpath).isSelected()) {
				UIHelper.findAnElementbyXpath(driver, emailCheckBoxXpath).click();
				UIHelper.click(driver, okButtonXpath);
				report.updateTestLog("Email Notification",
						"Email Notification will be received<br/><b>Expected Results:</b>"+status+
						"</br><b>Actual Result:</b>"+status, Status.PASS);
				
			}
			}
			else if (status.equalsIgnoreCase("Disable"))
			{
				if (UIHelper.findAnElementbyXpath(driver, emailCheckBoxXpath).isSelected()) {
					UIHelper.findAnElementbyXpath(driver, emailCheckBoxXpath).click();
					UIHelper.click(driver, okButtonXpath);
					report.updateTestLog("Email Notification",
							"Email Notification will not be received<br/><b>Expected Results:</b>"+status+
							"</br><b>Actual Result:</b>"+status, Status.PASS);
				}
			}
			

		} catch (Exception e) {
			report.updateTestLog("Email Notification", e.getMessage(),
					Status.FAIL);

		}

	}
}