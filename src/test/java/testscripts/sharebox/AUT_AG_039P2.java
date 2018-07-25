package testscripts.sharebox;

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
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_039P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters
				.setCurrentTestDescription("[1]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the shared folder is updated with New file OR folder by any other user who is having access to the shared folder via Alfresco.<br>"+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is not displayed in My activities dashlet from Site dashboard when the files OR folders is linked to Shared folder.<br>"+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is not displayed in My activities dashlet from Site dashboard when the linked files OR folders are unlinked from Shared folder..<br>");
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
		 AlfrescoRepositoryPage repositoryPage = new AlfrescoRepositoryPage(
					scriptHelper);
/*		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");*/
	/*	String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;*/
	/*	String dashletNme = dataTable.getData("Home", "DashletName");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");*/
		String folderName = dataTable.getData("MyFiles", "Version");
		
		String dashlet = dataTable.getData("Home", "DashBoardName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String file2 = dataTable.getData("MyFiles", "CreateFileDetails");
	
/*		String activityDid = dataTable.getData("MyActivities", "ActivityDid");
		String activityName = dataTable.getData("MyActivities", "TitleName");
		
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");*/
		
		functionalLibrary.loginAsValidUser(signOnPage);	
		
		String site=sitesPage.getCreatedSiteName();
       	
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashlet))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		sitesPage.siteFinder(site);

		sitesPage.enterIntoDocumentLibrary();
		myFiles.uploadFileInMyFilesPage(filePath, file2);
		sitesPage.documentdetails(folderName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		
		 sitesPage.enterIntoDocumentLibrary();
		 
		 sitesPage.clickOnMoreSetting(file2);

		docLibPg.commonMethodForClickOnMoreSettingsOption(file2, "Link To..");
			
			
		docDetailsPageObj.selectFolderInLinkPopUp(site);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		/* sitesPage.enterIntoDocumentLibraryWithoutReport();

		 repositoryPage.commonMethodToSelectFileInRepository(file2);
		
		 sitesPage.clickOnSelectedItems();
		 String selectedItemMenuOptVal = dataTable.getData("Sites",
					"SelectedItemsMenuOption");
			
		 docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		 docLibPg.unlinkSingleLocation();*/
		 sitesPage.enterIntoDocumentLibraryWithoutReport();
		siteDashboard.navigateToSiteDashboard();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		
	try {	
		
		
		if (homePage.isUserActivityDisplayedInMyActivityDashlet("ALF02 added document", fileName,
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ /*activityDid +*/ "<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ /*activityDid +*/ "<br/>",
					Status.FAIL);
		}
		UIHelper.waitFor(driver);
		
		if (!homePage.isUserActivityDisplayedInMyActivityDashlet("ALF02 linked document", file2,
				" ")) {
			report.updateTestLog("Verify that a record is not displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ /*activityDid +*/ "<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ /*activityDid +*/ "<br/>",
					Status.FAIL);
		}
		UIHelper.waitFor(driver);
		
		if (!homePage.isUserActivityDisplayedInMyActivityDashlet("ALF02 Unlinked document", file2,
				" ")) {
			report.updateTestLog("Verify that a record is not displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ /*activityDid +*/ "<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ /*activityDid +*/ "<br/>",
					Status.FAIL);
		}
		UIHelper.waitFor(driver);
			
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