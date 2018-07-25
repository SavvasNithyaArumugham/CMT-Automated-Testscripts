package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DateUtil;
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
public class AUT_AG_038P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_037() {
		testParameters.setCurrentTestDescription(
				"1. Verify Edit Sharing properties screen is update with new Email notification field as Language"
						+ "<br>2. Verify email template for notification mail, mail triggerred while uploading via SharBox, mail triggerred while uploading via Alfresco and aggregated notification mail"
						+ "<br>Part1: Share folder externally and Upload file into shared folder");
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
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String language = dataTable.getData("Sharebox", "Language");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");

		myFiles.createFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		String folderName = folderNamesList.get(0);
		
		sitesPage.clickOnMoreSetting(folderName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);

		docLibPg.clickOnOkBtnInPopup();

		// Verify Edit Sharing properties screen is update with new Email
		// notification field as Language
		if (UIHelper.checkForAnElementbyXpath(driver, shareboxPg.languageUsedInNotifcLabelXpath)) {
			UIHelper.highlightElement(driver, shareboxPg.languageUsedInNotifcLabelXpath);

			report.updateTestLog(
					"Verify the 'Sharebox Folder' popup screen for the new field 'Language used in notifications' under Email Notifications",
					"User able to see the new field 'Language used in notifications' under Email Notifications section in 'Sharebox Folder' popup",
					Status.PASS);
		} else {

			report.updateTestLog(
					"Verify the 'Sharebox Folder' popup screen for the new field 'Language used in notifications' under Email Notifications",
					"User not able to see the new field 'Language used in notifications' under Email Notifications section in 'Sharebox Folder' popup",
					Status.FAIL);
		}

		String languageOptions = dataTable.getData("Sharebox", "DropdownOptions");

		if (shareboxPg.checkLanguageDropdownOptions(languageOptions)) {
			report.updateTestLog("Verify the 'Language used in notifications' options in 'Sharebox Folder' popup",
					"User able to see the 'Language used in notifications' options:" + languageOptions, Status.PASS);
		} else {
			report.updateTestLog("Verify the 'Language used in notifications' options in 'Sharebox Folder' popup",
					"User not able to see the 'Language used in notifications' options:" + languageOptions,
					Status.FAIL);
		}

		shareboxPg.commonMethodToEnterSharingProperties(properties, message, language, notification, notifyDetails);

		shareboxPg.clickOnSaveBtnInSharingPopup();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		myFiles.openCreatedFolder(folderName);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		myFiles.uploadFile(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName,"");
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}