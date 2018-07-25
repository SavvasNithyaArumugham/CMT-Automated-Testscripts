package com.pearson.automation.alfresco.pages;

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
public class AlfrescoSiteMembersPage extends ReusableLibrary {

	private String siteMembersLinkXpath = ".//*[@id='HEADER_SITE_MEMBERS_text']/a";
	private String siteMembersContainerXpath = ".//*[@id='bd']//*[@class='site-members']";
	private String siteMembersNameXpath = ".//*[@id='bd']//table[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td[2]//h3/a";
	private String siteMembersRoleXpath = ".//*[@id='bd']//table[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td[3]";
	private String tempSiteMembersRoleXpathByUser = ".//*[@id='bd']//table[contains(@id,'yuievtautoid')]//*[@class='yui-dt-data']/tr/td[2]//h3/a[contains(.,'USER_NAME')]//ancestor::td[1]//following-sibling::td[2][contains(.,'ROLE_NAME')]";
	private String siteMembersInvitePeopleBtnXpath = ".//*[contains(@id,'default-invite-people-link')]";

	private String searchUserContainerXpath = ".//*[@class='finder-wrapper']";
	private String selectUserroleContainerXpath = ".//*[@class='invitationlist']";
	private String addedUsersContainerXpath = ".//*[@class='added-users-list']";
	
	private String messageXpathForEmptySearch = ".//*[@id='message']//span[contains(.,'Enter at least 1 character')]";

	private String pendingInviteTabXpath = ".//*[contains(@id,'default-pending-invites-link')]";

	private boolean isDisplayedRequiredUserWithRole;

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoSiteMembersPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Navigate To Site Members page
	public void navigateToSiteMembersPage() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, siteMembersLinkXpath)) {
				UIHelper.click(driver, siteMembersLinkXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);

				report.updateTestLog("Navigate To 'Site Members' Page",
						"Navigated to 'Site Members' Page successfully"
								+ "<br /><b> Page Title : </b>"
								+ "Site Members", Status.DONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check user is member of site
	public boolean isDisplayedRequiredUserInSiteMembersList(String userName,
			String roleName) {
		try {
			navigateToSiteMembersPage();

			String SiteMembersRoleXpathByUser = tempSiteMembersRoleXpathByUser
					.replace("USER_NAME", userName).replace("ROLE_NAME",
							roleName);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					siteMembersContainerXpath);

			WebElement siteMembersContainerEle = UIHelper.findAnElementbyXpath(
					driver, siteMembersContainerXpath);
			UIHelper.highlightElement(driver, siteMembersContainerEle);
			UIHelper.mouseOveranElement(driver, siteMembersContainerEle);

			if (UIHelper.checkForAnElementbyXpath(driver,
					SiteMembersRoleXpathByUser)) {
				isDisplayedRequiredUserWithRole = true;
				report.updateTestLog("Verify user in Site member",
						"User:"+userName+" is a site member with role:"+roleName
								, Status.PASS);
				
			} else {
				isDisplayedRequiredUserWithRole = false;
				
				report.updateTestLog("Verify user in Site member",
						"User:"+userName+" is a not site member with role:"+roleName
								, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify user in Site member",
					"User:"+userName+" is a not site member with role:"+roleName
							, Status.FAIL);
		}

		return isDisplayedRequiredUserWithRole;
	}

	// Check Three panels 1)Select Users 2)Set User Role 3)Add Users to Site in
	// 'Add User' screen
	public boolean checkAvailablePanelsInAddUserScreen() {
		boolean isDisplayedAddUserPanels = false;
		try {
			if (UIHelper.checkForAnElementbyXpath(driver,
					searchUserContainerXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							selectUserroleContainerXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							addedUsersContainerXpath)) {
				isDisplayedAddUserPanels = true;
			} else {
				isDisplayedAddUserPanels = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedAddUserPanels;
	}

	// Check message for empty search
	public boolean checkMsgForEmptySearch() {
		boolean isDisplayedMessage = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					messageXpathForEmptySearch);
			if (UIHelper.checkForAnElementbyXpath(driver,
					messageXpathForEmptySearch)) {
				isDisplayedMessage = true;
			} else {
				isDisplayedMessage = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedMessage;
	}

	// Check 'Pending Invites' Tab
	public boolean checkPendingInvitesTab() {
		boolean isDisplayedPendingInvitesTab = false;
		try {
			if (UIHelper
					.checkForAnElementbyXpath(driver, pendingInviteTabXpath)) {
				isDisplayedPendingInvitesTab = true;
			} else {
				isDisplayedPendingInvitesTab = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedPendingInvitesTab;
	}
}