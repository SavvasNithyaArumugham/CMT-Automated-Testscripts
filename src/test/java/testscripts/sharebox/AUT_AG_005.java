package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_005 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_005() {
		testParameters.setCurrentTestDescription(
				"1. Verify the edited details in the Shared Folder Window are not saved, if the user edits the shared details through the 'Edit External Sharing' option and clicks on 'Cancel' button"
						+ "<br>2. Verify user is able to edit the sharing details in the sharebox folder window"
						+ "<br>3.ALFDEPLOY-3499_Verify user is getting an validation error message on clicking Save button on removing the email address via Delete button.<br>"
						+ "ALFDEPLOY-3444_Verify the tooltip text displayed on clicking tooltip icon for (Shared With Email addresses of invited external users) field in Edit sharing properties popup on performing Edit external sharing action via Folder details page<br>"
						+ "ALFDEPLOY-3444_Verify the tooltip text displayed on clicking tooltip icon for (Shared With Email addresses of invited external users) field in Edit sharing properties popup on performing Edit external sharing action.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoShareboxPageTest shareboxPgTest = new AlfrescoShareboxPageTest(scriptHelper);

		homePage.navigateToSitesTab();

		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");

		String moreSettingsOptionNames[] = null;
		if (moreSettingsOptionName.contains(";")) {
			moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}

		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");

		String editProperties = dataTable.getData("Sharebox", "EditShareboxFieldsData");
		String editMessage = dataTable.getData("Sharebox", "EditMessage");
		String editNotification = dataTable.getData("Sharebox", "EditNotifications");
		String editNotifyDetails = dataTable.getData("Sharebox", "EditNotify_Sharer");

		String tootltipMsgValidate = dataTable.getData("Sharebox", "ErrorMessageValue");
		String tooltipMsg = "";

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		myFiles.createFolder(folderDetails);

		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyCreatedFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			sitesPage.clickOnMoreSetting(folderName);

			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionNames[0]);

			// docLibPg.clickOnOkBtnInPopup();

			shareboxPg.commonMethodToEnterSharingProperties(properties, message, notification, notifyDetails);
			shareboxPg.clickOnSaveBtnInSharingPopup();

			// 1. Verify the edited details in the Shared Folder Window are not
			// saved, if the user edits the shared details through the 'Edit
			// External Sharing' option and clicks on 'Cancel' button
			sitesPage.enterIntoDocumentLibrary();
			// Tool tip validation from Edit sharing from view details
			sitesPage.clickOnViewDetails(folderName);
			docDetailsPg.commonMethodForPerformDocAction(moreSettingsOptionNames[1]);

			// Check the presence of Shared with tooltip(question mark symbol) &
			// click on it to display the message
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxPg.sharingPopupXpath);

			shareboxPg.deletelist();

			shareboxPg.promptconfirm("OK");

			// shareboxPg.clickOnSaveBtnInSharingPopup();

			shareboxPg.getTextFromErrorMessagePopup();

			shareboxPg.commonMethodToEnterSharingProperties(editProperties, editMessage, editNotification,
					editNotifyDetails);

			if (UIHelper.checkForAnElementbyXpath(driver, shareboxPg.sharedWithQueTooltipXpath)) {
				UIHelper.highlightElement(driver, shareboxPg.sharedWithQueTooltipXpath);
				report.updateTestLog("Verify Shared with tooltip(question mark symbol) is displayed in sharebox window",
						"Shared with tooltip(question mark symbol) is displayed in sharebox window", Status.PASS);
				try {
					UIHelper.click(driver, shareboxPg.sharedWithQueTooltipXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxPg.sharedWithQueTooltipMsgXpath);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (UIHelper.checkForAnElementbyXpath(driver, shareboxPg.sharedWithQueTooltipMsgXpath)) {
					tooltipMsg = UIHelper.getTextFromWebElement(driver, shareboxPg.sharedWithQueTooltipMsgXpath);
					UIHelper.highlightElement(driver, shareboxPg.sharedWithQueTooltipMsgXpath);
					report.updateTestLog(
							"Verify Shared with tooltip(question mark symbol)message is displayed upon clicking the tooltip",
							"Shared with tooltip(question mark symbol)message " + "\" " + tooltipMsg + "\" "
									+ " is displayed upon clicking the tooltip",
							Status.PASS);
					if (tooltipMsg.equals(tootltipMsgValidate)) {
						report.updateTestLog(
								"Verify Shared with tooltip(question mark symbol)message matches with expected",
								"Shared with tooltip(question mark symbol)message matches with expected", Status.PASS);
					} else {
						report.updateTestLog(
								"Verify Shared with tooltip(question mark symbol)message matches with expected",
								"Shared with tooltip(question mark symbol)message does not match with expected",
								Status.FAIL);
					}
				}

			} else {
				report.updateTestLog("Verify Shared with tooltip(question mark symbol) is displayed in sharebox window",
						"Shared with tooltip(question mark symbol) is failed to display in sharebox window",
						Status.FAIL);
			}
			// Tool tip validation from Edit sharing from more option
			shareboxPg.clickOnCancelBtnInSharingPopup();
			sitesPage.enterIntoDocumentLibrary();
			sitesPage.clickOnMoreSetting(folderName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionNames[1]);
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxPg.sharingPopupXpath);
			if (UIHelper.checkForAnElementbyXpath(driver, shareboxPg.sharedWithQueTooltipXpath)) {
				UIHelper.highlightElement(driver, shareboxPg.sharedWithQueTooltipXpath);
				report.updateTestLog("Verify Shared with tooltip(question mark symbol) is displayed in sharebox window",
						"Shared with tooltip(question mark symbol) is displayed in sharebox window", Status.PASS);
				try {
					UIHelper.click(driver, shareboxPg.sharedWithQueTooltipXpath);
					UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxPg.sharedWithQueTooltipMsgXpath);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (UIHelper.checkForAnElementbyXpath(driver, shareboxPg.sharedWithQueTooltipMsgXpath)) {
					tooltipMsg = UIHelper.getTextFromWebElement(driver, shareboxPg.sharedWithQueTooltipMsgXpath);
					UIHelper.highlightElement(driver, shareboxPg.sharedWithQueTooltipMsgXpath);
					report.updateTestLog(
							"Verify Shared with tooltip(question mark symbol)message is displayed upon clicking the tooltip",
							"Shared with tooltip(question mark symbol)message " + "\" " + tooltipMsg + "\" "
									+ " is displayed upon clicking the tooltip",
							Status.PASS);
					if (tooltipMsg.equals(tootltipMsgValidate)) {
						report.updateTestLog(
								"Verify Shared with tooltip(question mark symbol)message matches with expected",
								"Shared with tooltip(question mark symbol)message matches with expected", Status.PASS);
					} else {
						report.updateTestLog(
								"Verify Shared with tooltip(question mark symbol)message matches with expected",
								"Shared with tooltip(question mark symbol)message does not match with expected",
								Status.FAIL);
					}
				}

			} else {
				report.updateTestLog("Verify Shared with tooltip(question mark symbol) is displayed in sharebox window",
						"Shared with tooltip(question mark symbol) is failed to display in sharebox window",
						Status.FAIL);
			}
			shareboxPg.clickOnCancelBtnInSharingPopup();

			// sitesPage.clickOnMoreSetting(folderName);

			// docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
			// moreSettingsOptionNames[1]);

			/*
			 ***************************************************
			 * 
			 * shareboxPgTest.checkEditedSharingPropFieldsWhenClickOnCancel(
			 * editProperties, editMessage);
			 * 
			 * // 2. Verify user is able to edit the sharing details in the //
			 * sharebox folder window sitesPage.enterIntoDocumentLibrary();
			 * 
			 * sitesPage.clickOnMoreSetting(folderName);
			 * 
			 * docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
			 * moreSettingsOptionNames[1]);
			 * 
			 * shareboxPg.commonMethodToEnterSharingProperties(editProperties,
			 * editMessage, editNotification, editNotifyDetails);
			 * 
			 * shareboxPg.clickOneditsaveBtnInSharingPopup();
			 */
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}