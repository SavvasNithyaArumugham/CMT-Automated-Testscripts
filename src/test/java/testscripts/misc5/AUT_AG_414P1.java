package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
public class AUT_AG_414P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void linkedShareFolderUploadFileMailCheckInSharebox() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3527_Verify the ShareBox New file available in shared folder activity email is "
				+ "triggered when the shared folder is linked with any folders inside." + 
				"");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileNameInsideFolder = dataTable.getData("MyFiles", "FileName").split(",")[0];
		String fileNameInDocLib = dataTable.getData("MyFiles", "FileName").split(",")[1];
		
		myFiles.uploadFileInMyFilesPage(filePath, fileNameInDocLib);
		myFiles.createFolder(folderDetails);
		
		String[] folderNames= dataTable.getData("MyFiles", "Version").split(",");
		String[] moreSettingsOptionNames= dataTable.getData("MyFiles", "MoreSettingsOption").split(",");
		
		for(String folderName:folderNames) {
		myFiles.openCreatedFolder(folderName);
		myFiles.uploadFileInMyFilesPage(filePath, fileNameInsideFolder);
		sitesPage.enterIntoDocumentLibrary();
		}
		
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		
		sitesPage.clickOnMoreSetting(folderNames[0]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderNames[0], moreSettingsOptionNames[0]);
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		if (shareboxPg.checkShareIconAvailable(folderNames[0])) {
			report.updateTestLog("Check Sharing icon available",
					"Sharing Icon displayed", Status.PASS);
		} else {
			report.updateTestLog("Check Sharing icon available",
					"Sharing Icon is not displayed", Status.FAIL);
		}

		
		
		sitesPage.clickOnMoreSetting(folderNames[1]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderNames[1], moreSettingsOptionNames[1]);
		docDetailsPageObj.selectAFolderInLinkPopUp(siteName,folderNames[0]);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		UIHelper.waitFor(driver);
		
		sitesPage.clickOnMoreSetting(fileNameInDocLib);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNameInDocLib, moreSettingsOptionNames[1]);
		docDetailsPageObj.selectAFolderInLinkPopUp(siteName,folderNames[0]);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		

				
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}