package testscripts.sharebox;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
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
public class AUT_AG_001 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_001() {
		testParameters.setCurrentTestDescription(
				"1. Verify user is able to see the 'Share folder externally' option under actions in the document library page"
						+ "<br>2. Verify the confirmation window is displayed after clicking the Share Folder externally option"
						+ "<br>3. Verify user is able to view the Share box folder window and the fields in the sharebox folder window"
						+ "<br>4. Verify user is able to enter values into the fields in the Share box folder window<br>"
						+ "ALFDEPLOY-3444_Verify the tooltip text displayed on clicking tooltip icon for (Shared With Email addresses of invited external users field) in Share folder externally popup on performing Share folder externally action.");
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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String tootltipMsgValidate = dataTable.getData("Sharebox", "EditShareboxFieldsData");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		myFiles.createFolder(folderDetails);

		String folderName = dataTable.getData("MyFiles", "FileName");
		String tooltipMsg = "";
		sitesPage.clickOnMoreSetting(folderName);

		// 1. Verify user is able to see the 'Share folder externally' option
		// under actions in the document library page
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderName, moreSettingsOptionName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);

		// 2. Verify the confirmation window is displayed after clicking the
		// Share Folder externally option
		// docLibPg.verifyConfirmationWindow();
		// docLibPg.clickOnOkBtnInPopup();

		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);

		UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxPg.sharingPopupXpath);

		// 3. Verify user is able to view the Share box folder window and the
		// fields in the sharebox folder window
		if (UIHelper.checkForAnElementbyXpath(driver, shareboxPg.sharingPopupXpath)
				&& UIHelper.checkForAnElementbyXpath(driver, shareboxPg.messageTextXpath)
				&& UIHelper.checkForAnElementbyXpath(driver, shareboxPg.selectNotificationXpath)
				&& UIHelper.checkForAnElementbyXpath(driver, shareboxPg.saveBtnXpath)
				&& UIHelper.checkForAnElementbyXpath(driver, shareboxPg.cancelBtnXpath)
				&& UIHelper.checkForAnElementbyXpath(driver, shareboxPg.sharingPropFieldValuesXpath)
				&& UIHelper.checkForAnElementbyXpath(driver, shareboxPg.sharedMesageFieldXpath)) {
			UIHelper.highlightElement(driver, shareboxPg.sharingPopupXpath);
			UIHelper.highlightElement(driver, shareboxPg.messageTextXpath);
			UIHelper.highlightElement(driver, shareboxPg.selectNotificationXpath);
			UIHelper.highlightElement(driver, shareboxPg.saveBtnXpath);
			UIHelper.highlightElement(driver, shareboxPg.cancelBtnXpath);
			UIHelper.highlightElement(driver, shareboxPg.sharingPropFieldValuesXpath);
			UIHelper.highlightElement(driver, shareboxPg.sharedMesageFieldXpath);

			report.updateTestLog("Verify Share box folder window and the fields",
					"Share box folder window and the fields are displayed", Status.PASS);
		} else {
			report.updateTestLog("Verify Share box folder window and the fields",
					"Share box folder window and the fields are not displayed", Status.FAIL);
		}

		// 4. Verify user is able to enter values into the fields in the Share
		// box folder window
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");

		shareboxPg.commonMethodToEnterSharingProperties(properties, message, notification, notifyDetails);

		// Check the presence of Shared with tooltip(question mark symbol) &
		// click on it to display the message
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
					"Shared with tooltip(question mark symbol) is failed to display in sharebox window", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}