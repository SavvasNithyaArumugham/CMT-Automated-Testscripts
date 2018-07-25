package testscripts.sharebox;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
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
public class AUT_AG_040P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters
				.setCurrentTestDescription("[1]ALFDEPLOY-4582_Verify user is able to perform Edit external sharing action via Folder details page for the shared folder.<br>"+
		"[1]ALFDEPLOY-4582_Verify user is able to perform stop external sharing action via Folder details page for the shared folder.<br>"+
		"[1]ALFDEPLOY-4582_Verify user is able to share the folder to external user via Folder details page.<br>"+
		"[1]ALFDEPLOY-4582_Verify user is able to share the subfolder to external user via Folder details page when the root folder is already shared."+
		"[1]ALFDEPLOY-4582_Verify user is able to view the updated details once the details are updated via Edit external sharing option form view details page.<br>");
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
		

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		
		
		
		String subfolderDetails = dataTable.getData("MyFiles", "BrowseActionName");
		String subfolder = dataTable.getData("MyFiles", "Sort Options");
		
		String subfolderDetails1 = dataTable.getData("MyFiles", "StatusReportValue");
		String subfolder1 = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String properties2 = dataTable.getData("Sharebox", "EditShareboxFieldsData");
		String message2 = dataTable.getData("Sharebox", "EditMessage");
		String properties3 = dataTable.getData("Sharebox", "ErrorMessageValue");
		String message3 = dataTable.getData("Sharebox", "UploadedFilesInShareboxFolder");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileName1 = dataTable.getData("Document_Details", "FileName");
		String fileName2 = dataTable.getData("Document_Details", "Title");
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptionName1 = dataTable.getData("MyFiles", "AccessToken");
		String moreSettingsOptionName2 = dataTable.getData("MyFiles", "PopUpMsg");
		String folderName = dataTable.getData("MyFiles", "Version");
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		
		
		functionalLibrary.loginAsValidUser(signOnPage);	
		
		sitesPage.siteFinder(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		
		sitesPage.documentdetails(folderName);
		
		myFiles.createFolder(subfolderDetails);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.commonMethodForPerformBrowseOption(folderName, "View Details");
		
		docDetailsPageObj.commonMethodForPerformDocAction(moreSettingsOptionName);
		
		docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		
		sitesPage.commonMethodForPerformBrowseOption(subfolder, "View Details");
		
		docDetailsPageObj.commonMethodForPerformDocAction(moreSettingsOptionName);
		
		//docLibPg.clickOnOkBtnInPopup();
		UIHelper.waitFor(driver);
		System.out.println(properties2);
		shareboxPg.commonMethodToEnterSharingProperties(properties2,
				message2, notification, notifyDetails);
	
		shareboxPg.clickOnSaveBtnInSharingPopup();
		
		docDetailsPageObj.commonMethodForPerformDocAction(moreSettingsOptionName1);
		UIHelper.waitFor(driver);
		shareboxPg.commonMethodToEnterSharingProperties(properties3,
				message3, notification, notifyDetails);
		
		shareboxPg.clickOneditsaveBtnInSharingPopup();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFiles.rename(fileName, "TC79.txt");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		if(shareboxPg.isfoldershared(subfolder)) {
			
			report.updateTestLog("Verify Share Icon is displayed",
					"Share icon is displayed for the folder as expected. <b><br>  File/Folder Name: <b>"+subfolder, Status.PASS);
			
		}else {
			report.updateTestLog("Verify Share Icon is displayed",
					"Share icon is not displayed for the folder. <b><br>  File/Folder Name: <b>"+subfolder, Status.FAIL);
			
		}
		
		sitesPage.commonMethodForPerformBrowseOption(subfolder, "View Details");
	
		docDetailsPageObj.commonMethodForPerformDocAction(moreSettingsOptionName2);
		docLibPg.clickOnOkBtnInPopup();
		

		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		
		if(!shareboxPg.isfoldershared(subfolder)) {
			
			report.updateTestLog("Verify Stop share externally",
					"Stop share externally successful. <b><br>  File/Folder Name: <b>"+subfolder, Status.PASS);
			
		}else {
			report.updateTestLog("Verify Stop share externally",
					"Stop share externally was not successful. <b><br>  File/Folder Name: <b>"+subfolder, Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		
	if(!shareboxPg.isfoldershared(subfolder)) {
			
			report.updateTestLog("Verify Stop share externally",
					"Stop share externally successful. <b><br>  File/Folder Name: <b>"+subfolder, Status.PASS);
			
		}else {
			report.updateTestLog("Verify Stop share externally",
					"Stop share externally was not successful. <b><br>  File/Folder Name: <b>"+subfolder, Status.FAIL);
		}
	
	
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		
		sitesPage.documentdetails("TC79.txt");
		
	//	sitesPage.commonMethodForPerformBrowseOption(subfolder, "View Details");
	
		docDetailsPageObj.commonMethodForPerformDocAction("Upload New Version");
		
		docLibPg.uploadNewVersionFileInDocLibPage(fileName1,
				filePath, versionDetails, comments);
		
		mediaTransPage.checkVersionOfTransformedFile("1.1");
		
		docDetailsPageObj.commonMethodForPerformDocAction("Upload New Version");
		
		docLibPg.uploadNewVersionFileInDocLibPage(fileName2,
				filePath, versionDetails, comments);
		
	//	docLibPg.uploadNewVersionButton();
		
		mediaTransPage.checkVersionOfTransformedFile("1.2");
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		
		sitesPage.commonMethodForPerformBrowseOption(subfolder, "View Details");
		
		docDetailsPageObj.commonMethodForPerformDocAction("Edit Properties");

		docDetailsPageObj.editInEditPropertiesInputBox("Name","AutoTest5");
		
		docDetailsPageObj.clickSaveInEditProperties();

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}