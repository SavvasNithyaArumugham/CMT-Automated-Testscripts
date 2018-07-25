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
public class AUT_AG_023 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_023() {
		testParameters
				.setCurrentTestDescription("Verify user is able to copy/move an existing ShareBox folder to another ShareBox folder");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOpt = dataTable.getData("MyFiles",
				"MoreSettingsOption");		
		String copyFolderName = dataTable.getData("MyFiles", "Version");
		String targetFolderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOpt1 = dataTable.getData("MyFiles",
				"BrowseActionName");
		String copiedFolderDetails = dataTable.getData("MyFiles",
				"RelationshipName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		myFiles.createFolder(folderDetails);	
		myFilesTestObj.verifyCreatedFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			sitesPage.clickOnMoreSetting(folderName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
					moreSettingsOpt);

			docLibPg.clickOnOkBtnInPopup();
			shareboxPg.commonMethodToEnterSharingProperties(properties,
					message, notification, notifyDetails);
			shareboxPg.clickOnSaveBtnInSharingPopup();

		}
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);		
		sitesPage.clickOnMoreSetting(copyFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(copyFolderName,
				moreSettingsOpt1);
		
		docDetailsPageObj.selectFolderToCopyInCopyPopUp(targetFolderName);
		
		homePage.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.openFolder(targetFolderName);
		myFilesTestObj.verifyCopiedFolder(copiedFolderDetails);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}