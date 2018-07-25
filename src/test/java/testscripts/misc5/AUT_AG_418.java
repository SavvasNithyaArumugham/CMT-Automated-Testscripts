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
public class AUT_AG_418 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void selectMenuEnableInDocLib() {
		testParameters.setCurrentTestDescription(
			 "[1]ALFDEPLOY-4979_Verify selected items menu is enabled when only shared folders are selected via select drodpown"
			 +"[1]ALFDEPLOY-4979_Verify user is able to perform the operations of selected items menu when only shared folders are selected via select dropdown"
			 +"[1]ALFDEPLOY-4979_Verify selected items menu is enabled when shared folders and normal folders are selected via select dropdown"
			 +"[1]ALFDEPLOY-4979_Verify user is able to perform the operations of selected items menu when shared folders and normal folders are selected via select dropdown"
			 +"[1]ALFDEPLOY-4979_Verify selected items menu is disabled when shared folders and normal folders are deselected via select dropdown");
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
		
		String[] folderDetails = dataTable.getData("MyFiles", "CreateFolder").split(";");
		String[] folderNames= dataTable.getData("MyFiles", "Version").split(",");	
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName1 = dataTable.getData("MyFiles", "FileName").split(",")[0];
		String fileName2= dataTable.getData("MyFiles", "FileName").split(",")[1];
		String moreSettingsOptionNames= dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		
		// selected items menu is enabled when only shared folders are selected via select drodpown
		myFiles.createFolder(folderDetails[0]);
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.uploadFileInMyFilesPage(filePath, fileName1);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(folderNames[0]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderNames[0], moreSettingsOptionNames);
		shareboxPg.commonMethodToEnterSharingProperties(properties,message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		if (shareboxPg.checkShareIconAvailable(folderNames[0])) {
			report.updateTestLog("Check Sharing icon available", "Sharing Icon displayed", Status.PASS);
		} else {
			report.updateTestLog("Check Sharing icon available", "Sharing Icon is not displayed", Status.FAIL);
		}
		UIHelper.waitFor(driver);
		sitesPage.enterIntoDocumentLibrary(); 
		docLibPg.selectAllFilesAndFolders();
		if(docLibPg.isSelectItemsMenuEnabled()){
			report.updateTestLog("Check Select Items menu enabled", "Select Items menu enabled", Status.PASS);
		}else{
			report.updateTestLog("Check Select Items menu enabled", "Select Items menu disabled", Status.FAIL);
		}
		
		// perform the operations of selected items menu when only shared folders are selected via select dropdown
		docLibPg.performDeleteFromSelectedItemsMenu();
		
		//selected items menu is enabled when shared folders and normal folders are selected via select dropdown
		myFiles.createFolder(folderDetails[0]);
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.uploadFileInMyFilesPage(filePath, fileName1);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(folderNames[0]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderNames[0], moreSettingsOptionNames);
		shareboxPg.commonMethodToEnterSharingProperties(properties,message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		if (shareboxPg.checkShareIconAvailable(folderNames[0])) {
			report.updateTestLog("Check Sharing icon available", "Sharing Icon displayed", Status.PASS);
		} else {
			report.updateTestLog("Check Sharing icon available", "Sharing Icon is not displayed", Status.FAIL);
		}
		
		myFiles.createFolder(folderDetails[1]);
		myFiles.openCreatedFolder(folderNames[1]);
		myFiles.uploadFileInMyFilesPage(filePath, fileName2);
		
		sitesPage.enterIntoDocumentLibrary();		
		UIHelper.waitFor(driver);
		docLibPg.selectAllFilesAndFolders();
		if(docLibPg.isSelectItemsMenuEnabled()){
			report.updateTestLog("Check Select Items menu enabled", "Select Items menu enabled", Status.PASS);
		}else{
			report.updateTestLog("Check Select Items menu enabled", "Select Items menu disabled", Status.FAIL);
		}
		
		//Verify selected items menu is disabled when shared folders and normal folders are deselected via select dropdown
		UIHelper.waitFor(driver);
		docLibPg.deselectAllFilesAndFolders();
		if(!docLibPg.isSelectItemsMenuEnabled()){
			report.updateTestLog("Check Select Items menu disabled", "Select Items menu disabled", Status.PASS);
		}else{
			report.updateTestLog("Check Select Items menu disabled", "Select Items menu enabled", Status.FAIL);
		}
		
		// user is able to perform the operations of selected items menu when shared folders and normal folders are selected via select dropdown
		docLibPg.selectAllFilesAndFolders();
		docLibPg.performDeleteFromSelectedItemsMenu();
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}