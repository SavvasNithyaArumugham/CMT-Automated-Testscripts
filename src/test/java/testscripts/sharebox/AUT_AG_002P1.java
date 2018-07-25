package testscripts.sharebox;

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
 * @author Cognizant
 */
public class AUT_AG_002P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_002() {
		testParameters
				.setCurrentTestDescription("1. Verify that an email is triggered to the external user, once the internal user share the folder externally"
						+ "<br>2. Verify the notification email contains the link to the shared folder"
						+ "<br>3. Verify user is able to access the shared folder using the link provided in the notification email"
						+ "<br>4. Verify user is able to access the shared folder and view the shared folder content using the link provided in the notification email"
						+ "<br>5. Verify the user receives the notification email immediately, if the Notifications is set as 'immediately' while sharing the Folder"
						+ "<br>6. Verify the Sharing is not removed , if the user clicks on 'Cancel' button after selecting the 'Stop Sharing Externally' option"
						+ "<br>Part1: Share folder externally");
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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
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

		String moreSettingsOptionName1 = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptionName2 = dataTable.getData("MyFiles", "CreateFileDetails");

		myFiles.createFolder(folderDetails);

		String folderName = dataTable.getData("MyFiles", "FileName");
		sitesPage.clickOnMoreSetting(folderName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName1);

		docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		
		sitesPage.clickOnMoreSetting(folderName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName2);
		docLibPg.clickOnOkcancelBtnInPopup();
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}