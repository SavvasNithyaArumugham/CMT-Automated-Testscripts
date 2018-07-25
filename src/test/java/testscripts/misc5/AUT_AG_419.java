package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
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
public class AUT_AG_419 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void selectMenuEnableInDocLib() {
		testParameters.setCurrentTestDescription(
			 "[1]ALFDEPLOY-4979_Verify selected items menu is enabled when normal files & folders,shared folders and excluded files & folders are selected via select dropdown");
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
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest= new AlfrescoDocumentLibPageTest(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames= dataTable.getData("MyFiles", "Version").split(",");	
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName1 = dataTable.getData("MyFiles", "FileName").split(",")[0];
		String fileName2= dataTable.getData("MyFiles", "FileName").split(",")[1];
		String[] moreSettingsOptionNames= dataTable.getData("MyFiles", "MoreSettingsOption").split(",");
		
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String[] expectedMsg=dataTable.getData("Search", "Query").split(",");
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName1);
		myFiles.createFolder(folderDetails);
		for(String folderName:folderNames) {
			myFiles.openCreatedFolder(folderName);
			myFiles.uploadFileInMyFilesPage(filePath, fileName2);
			sitesPage.enterIntoDocumentLibrary();
			}
		
		sitesPage.clickOnMoreSetting(folderNames[0]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderNames[0], moreSettingsOptionNames[0]);
		shareboxPg.commonMethodToEnterSharingProperties(properties,message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		if (shareboxPg.checkShareIconAvailable(folderNames[0])) {
			report.updateTestLog("Check Sharing icon available", "Sharing Icon displayed", Status.PASS);
		} else {
			report.updateTestLog("Check Sharing icon available", "Sharing Icon is not displayed", Status.FAIL);
		}
		
		UIHelper.waitFor(driver);
		sitesPage.enterIntoDocumentLibrary(); 
		sitesPage.clickOnMoreSetting(folderNames[1]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderNames[1], moreSettingsOptionNames[1]);
		docLibPg.clickOnOkBtnInPopup();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		docLibPgTest.verifyBannerMessageInDocLib(folderNames[1],expectedMsg[0]);
		
		docLibPg.scrollToFileorFolder(fileName1);
		sitesPage.clickOnMoreSetting(fileName1);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName1, moreSettingsOptionNames[1]);
		docLibPg.clickOnOkBtnInPopup();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		docLibPgTest.verifyBannerMessageInDocLib(fileName1,expectedMsg[1]);
		
		
		docLibPg.selectAllFilesAndFolders();
		if(docLibPg.isSelectItemsMenuEnabled()){
			report.updateTestLog("Check Select Items menu enabled", "Select Items menu enabled", Status.PASS);
		}else{
			report.updateTestLog("Check Select Items menu enabled", "Select Items menu disabled", Status.FAIL);
		}
	
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}