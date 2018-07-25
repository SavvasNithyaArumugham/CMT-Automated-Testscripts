package testscripts.sharebox;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
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
public class AUT_AG_039P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters
				.setCurrentTestDescription("[1]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the Folder is shared to external users.<br>"+
		"[2]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the shared Folder is updated by deleting any folder OR file from shared folder via Alfresco user<br>"+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the shared Folder is updated with multiple files and folders by alfresco user.<br>"+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the shared Folder is updated with new file by alfresco user."+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the shared Folder is updated with new folder by alfresco user.<br>"+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the shared Folder is updated with new version of the already existed file.<br>"+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the shared subFolder is updated with folder OR files via Alfresco user.<br>"+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the subFolder under shared folder is shared to external users.<br>");
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
		String file2 = dataTable.getData("MyFiles", "CreateFileDetails");
		String file3 = dataTable.getData("MyFiles", "RelationshipName");
		
		String subfolderDetails = dataTable.getData("MyFiles", "BrowseActionName");
		String subfolder = dataTable.getData("MyFiles", "Sort Options");
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String properties2 = dataTable.getData("Sharebox", "EditShareboxFieldsData");
		String message2 = dataTable.getData("Sharebox", "EditMessage");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName = dataTable.getData("MyFiles", "Version");
		String dashletNme = dataTable.getData("Home", "DashletName");
		String dashlet = dataTable.getData("Home", "DashBoardName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
	
		String activityDid = dataTable.getData("MyActivities", "ActivityDid");
		String activityName = dataTable.getData("MyActivities", "TitleName");
		
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		
		functionalLibrary.loginAsValidUser(signOnPage);	
		
		homePage.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
       	sitesPage.performInviteUserToSite(site);
        siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);
		

		
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashlet))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}

		sitesPage.enterIntoDocumentLibrary();

		myFiles.createFolder(folderDetails);

		
		sitesPage.clickOnMoreSetting(folderName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);

		docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.documentdetails(folderName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFiles.createFolder(subfolderDetails);
		
		sitesPage.clickOnMoreSetting(subfolder);

		docLibPg.commonMethodForClickOnMoreSettingsOption(subfolder, moreSettingsOptionName);

		docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(properties2,
				message2, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		
		//docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, file2);
		
		sitesPage.documentdetails(file2);
		
		docDetailsPageObj.commonMethodForPerformDocAction("Upload New Version");
	
		docLibPg.uploadNewVersionFileInDocLibPage(file2,
				filePath, versionDetails, comments);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.documentdetails(folderName);
		sitesPage.documentdetails(subfolder);
		
		myFiles.uploadFileInMyFilesPage(filePath, file3);
		
		
		
		siteDashboard.navigateToSiteDashboard();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		
	try {	
		if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet(activityDid, activityName,
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ activityDid +"<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+  activityDid + "<br/>",
					Status.FAIL);
		}
		UIHelper.waitFor(driver);
		
		if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet("ALF01 shared folder AutoTest1", activityName,
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ "ALF01 shared folder AutoTest1 "+  "<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ "ALF01 shared folder AutoTest1 "+  "<br/>",
					Status.FAIL);
		}
		UIHelper.waitFor(driver);
		
		
		if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet("ALF01 added document", fileName,
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ "ALF01 added document "+ fileName + "<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ "ALF01 added document "+ fileName+ "<br/>",
					Status.FAIL);
		}
		UIHelper.waitFor(driver);
		
		
		if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet("ALF01 added folder", folderName,
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ "ALF01 added folder "+ folderName+ "<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ "ALF01 added folder "+ folderName +"<br/>",
					Status.FAIL);
		}
		
		
		if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet("ALF01 added folder", subfolder,
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ "ALF01 added folder "+ subfolder+"<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ "ALF01 added folder "+ subfolder+ "<br/>",
					Status.FAIL);
		}
		
		
		
		
		if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet("ALF01 updated document", file2,
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+"ALF01 updated document "+ file2 +"<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+"ALF01 updated document "+ file2 + "<br/>",
					Status.FAIL);
		}
		
		
		if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet("ALF01 added document", file3,
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+"ALF01 added document "+ file3+ "<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ "ALF01 added document "+ file3+ "<br/>",
					Status.FAIL);
		}
		
		
	} catch (Exception e) {
		report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
				"Verify that a record is displayed in the 'My Activity' dashlet Failed", Status.FAIL);
	}

		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}