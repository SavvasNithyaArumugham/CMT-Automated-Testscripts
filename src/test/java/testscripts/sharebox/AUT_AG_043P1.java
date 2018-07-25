package testscripts.sharebox;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_043P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY - 4768 test data set up and shareing for Test data to share box.<br>");
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoRepositoryPage repoPg = new AlfrescoRepositoryPage(scriptHelper);
	
		String file1 = dataTable.getData("MyFiles", "AccessToken");
		String file3 = dataTable.getData("MyFiles", "StatusReportValue");
		
		String subfolderDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String subfolder = dataTable.getData("MyFiles", "Sort Options");
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String propertiesshare = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String uploadnewVersion = dataTable.getData("MyFiles", "RelationshipName");
		String uploadfile1 = dataTable.getData("MyFiles", "BrowseActionName");
	//	String uploadfile2 = dataTable.getData("MyFiles", "BrowseActionName");
		String folderName = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		String reNamefile = "ShareRenamedFile";
		String reNamefolder= "ShareRenamedFolderc";
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		
		functionalLibrary.loginAsValidUser(signOnPage);	
		
		homePage.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		
	/*	String site = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(site);*/
 
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createFolder(folderDetails);

		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);

		docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(propertiesshare,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFiles.createFolder(subfolderDetails);

		
		sitesPage.documentdetails(file1);
		
		docDetailsPageObj.commonMethodForPerformDocAction(uploadnewVersion);
	
		docLibPg.uploadNewVersionFileInDocLibPage(uploadfile1,
				filePath, versionDetails, comments);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.documentdetails(folderName);
		

		
		repoPg.commonMethodToSelectFileInRepository(file3);
		sitesPage.clickOnSelectedItems();
		
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption("Edit Offline");
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, file3);
		
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, file3);
	
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		repoPg.commonMethodToSelectFileInRepository(file3);
		sitesPage.clickOnSelectedItems();
		
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(uploadnewVersion);
		
		docLibPg.uploadNewVersionFileforeditOfflinefile(file3,
				filePath, versionDetails, comments);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		
		myFiles.rename(subfolder, reNamefolder);
	
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		
		myFiles.rename(uploadfile1, reNamefile);
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}