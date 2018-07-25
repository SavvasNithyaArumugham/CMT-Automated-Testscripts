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
public class AUT_AG_026P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_026() {
		testParameters
				.setCurrentTestDescription("Verify whether internal user is able to share folder to multiple external users."
		+"<br>2. ALFDEPLOY-3527_Verify the ShareBox New file available in shared folder activity email is triggered when the shared folder is linked with any files inside."
		+"<br>3.ALFDEPLOY-3527_Verify the ShareBox New file available in shared folder activity email is triggered when the shared folder is linked with any files inside. "
						+ " <br>-Part1 : Share a folder externally");
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
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String linklt = dataTable.getData("MyFiles", "CreateFileDetails");
		

		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFiles.createFolder(folderDetails);	
		myFilesTestObj.verifyCreatedFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);


			sitesPage.clickOnMoreSetting(folderNamesList.get(0));
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderNamesList.get(0),
					moreSettingsOpt);

			docLibPg.clickOnOkBtnInPopup();
			shareboxPg.commonMethodToEnterSharingProperties(properties,
					message, notification, notifyDetails);
			shareboxPg.clickOnSaveBtnInSharingPopup();

		
			sitesPage.clickOnMoreSetting(fileName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,
					"Link To..");
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		sitesPage.clickOnMoreSetting(folderNamesList.get(1));
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderNamesList.get(1),
				"Link To..");
		
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		sitesPage.clickOnMoreSetting(folderNamesList.get(0));
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderNamesList.get(0),
				"Link To..");
		
		docDetailsPageObj.samesiteFolderLink(sourceSiteName, folderNamesList.get(2));
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}