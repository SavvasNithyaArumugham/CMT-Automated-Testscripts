package com.pearson.automation.alfresco.tests;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoSiteMembersPageTest extends ReusableLibrary {

	private AlfrescoSiteMembersPage siteMembPg = new AlfrescoSiteMembersPage(
			scriptHelper);

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoSiteMembersPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Verify Site Members
	public void verifySiteMemebrs(String siteName, String invitedUserName,
			String role) {
		try {
			StringTokenizer userNameToken = new StringTokenizer(
					invitedUserName, ",");
			StringTokenizer roleToken = new StringTokenizer(role, ",");

			while (userNameToken.hasMoreElements()) {
				String userName = userNameToken.nextElement().toString();
				String userRole = "";
				if (role.contains(",")) {
					userRole = roleToken.nextElement().toString();
				} else {
					userRole = role;
				}
				boolean isDisplayedSiteMember = siteMembPg
						.isDisplayedRequiredUserInSiteMembersList(userName,
								userRole);

				if (isDisplayedSiteMember) {
					report.updateTestLog("Check Site Memeber", userName
							+ " joined successfully to a Site:" + siteName,
							Status.PASS);
				} else {
					report.updateTestLog("Check Site Memeber", userName
							+ " failed to join a Site:" + siteName, Status.FAIL);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Three panels 1)Select Users 2)Set User Role 3)Add Users to Site in
	// 'Add User' screen
	public void verifyAvailablePanelsInAddeUserScreen() {
		try {
			boolean isDisplayedAddUserPanels = siteMembPg
					.checkAvailablePanelsInAddUserScreen();

			if (isDisplayedAddUserPanels) {
				report.updateTestLog(
						"Verify Panels in 'Add User' to site page",
						"Three panels 1)Select Users 2)Set User Role 3)Add Users to Site are displayed successfully",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Panels in 'Add User' to site page",
						"Failed to display three panels 1)Select Users 2)Set User Role 3)Add Users",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check message for empty search
	public void verifyMsgForEmptySearch() {
		try {
			boolean isDisplayedMessage = siteMembPg.checkMsgForEmptySearch();

			if (isDisplayedMessage) {
				report.updateTestLog(
						"Verify validation message for empty search in 'Add Users' page",
						"User able to see the validation message as 'Enter at least 1 character(s)'",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify validation message for empty search in 'Add Users' page",
						"User not able to see the validation message as 'Enter at least 1 character(s)'",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check message for empty search
	public void verifyPendingInvitesTab() {
		try {
			boolean isDisplayedPendingInvitesTab = siteMembPg
					.checkPendingInvitesTab();

			if (isDisplayedPendingInvitesTab) {
				report.updateTestLog(
						"Verify 'Pending Invites' tab",
						"User able to see the 'Pending Invites' tab in 'Site Members' page",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify 'Pending Invites' tab",
						"User not able to see the 'Pending Invites' tab in 'Site Members' page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
