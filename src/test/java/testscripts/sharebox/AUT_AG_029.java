package testscripts.sharebox;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_029 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_029() {
		testParameters
				.setCurrentTestDescription("Verify whether the internal user is able to share a 15th level Subfolder after the root folder has been stopped.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOpt = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		String stopSharingOpt = dataTable
				.getData("MyFiles", "BrowseActionName");

		int i = 0;

		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();

		}

		ArrayList<String> allFolderNamesList = new ArrayList<String>();

		StringTokenizer token = new StringTokenizer(folderDetails, ";");
		while (token.hasMoreElements()) {
			String folderdetails = token.nextElement().toString();
			myFiles.createFolder(folderdetails);
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = myFiles.getFolderNames(folderdetails);
			for (String folderName : folderNamesList) {
				myFiles.openCreatedFolder(folderName);
				allFolderNamesList.add(folderName);
			}
		}

		int middelElement = allFolderNamesList.size() / 2 - 1;
		sitesPage.enterIntoDocumentLibrary();

		sitesPage.clickOnMoreSetting(allFolderNamesList.get(0));
		docLibPg.commonMethodForClickOnMoreSettingsOption(
				allFolderNamesList.get(0), moreSettingsOpt);
		docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(properties, message,
				notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();

		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(0));
		docLibPg.commonMethodForClickOnMoreSettingsOption(
				allFolderNamesList.get(0), stopSharingOpt);
		docLibPg.clickOnOkBtnInPopup();
		StringTokenizer subFolders = new StringTokenizer(folderDetails, ";");
		while (subFolders.hasMoreElements()) {
			String folderdetails = subFolders.nextElement().toString();
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = myFiles.getFolderNames(folderdetails);
			for (String folderName : folderNamesList) {
				if (i == 0) {
					if (!folderName.equalsIgnoreCase(allFolderNamesList
							.get(middelElement))) {
						myFiles.openCreatedFolder(folderName);
					} else {
						sitesPage.clickOnMoreSetting(folderName);
						docLibPg.commonMethodForClickOnMoreSettingsOption(
								folderName, moreSettingsOpt);
						docLibPg.clickOnOkBtnInPopup();
						shareboxPg.commonMethodToEnterSharingProperties(
								properties, message, notification,
								notifyDetails);
						shareboxPg.clickOnSaveBtnInSharingPopup();

						i++;
					}
				} else {
					break;
				}

			}

		}

		sitesPage.enterIntoDocumentLibrary();
		if (shareboxPg.checkShareIconAvailable(allFolderNamesList.get(0))) {
			report.updateTestLog("Check Sharing icon available",
					"Sharing Icon displayed", Status.FAIL);
		} else {
			report.updateTestLog("Check Sharing icon available",
					"Sharing Icon is not displayed", Status.PASS);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}