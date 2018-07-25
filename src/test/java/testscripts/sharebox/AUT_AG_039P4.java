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
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
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
public class AUT_AG_039P4 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters
				.setCurrentTestDescription("[1][1]ALFDEPLOY-5277_Verify the Activity Feed is not displayed in My activities dashlet from Site dashboard when the shared Folder is updated with some sharebox field values.<br>"+
		"[2]ALFDEPLOY-5277_Verify the Activity Feed is not displayed in My activities dashlet from Site dashboard when the shared folder is unshared via Stop sharing externally option.<br>"+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is displayed in My activities dashlet from Site dashboard when the shared folder is updated with New file OR folder from left Sharebox filter window.<br>"+
		"[1]ALFDEPLOY-5277_Verify the Activity Feed is not displayed in My activities dashlet from Site dashboard when the shared subFolder is updated with files via External Share Box users.<br>");
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
	AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);

	AlfrescoTasksPage tasksPage = new AlfrescoTasksPage(scriptHelper);
	
	String filter = dataTable.getData("MyFiles", "BrowseActionName");
	//String file2 = dataTable.getData("MyFiles", "CreateFileDetails");
	String file3 = dataTable.getData("MyFiles", "Sort Options");
	String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
	String message = dataTable.getData("Sharebox", "Message");
	String date = new DateUtil().getCurrentDate();
	message = message+"_"+date;
	String notification = dataTable.getData("Sharebox", "Notifications");
	String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");	
	String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
	String moreSettingsOptionName1 = dataTable.getData("MyFiles", "RelationshipName");
	String folderName = dataTable.getData("MyFiles", "Version");
	String folder2 = dataTable.getData("MyFiles", "CreateFileDetails");

	String filePath = dataTable.getData("MyFiles", "FilePath");
	String fileName = dataTable.getData("MyFiles", "FileName");

	
	String activityName = dataTable.getData("MyActivities", "TitleName");
		
		functionalLibrary.loginAsValidUser(signOnPage);	
		
		String site=sitesPage.getCreatedSiteName();

		sitesPage.siteFinder(site);

		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		
		sitesPage.clickOnMoreSetting(folder2);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folder2, moreSettingsOptionName);

		//docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		
		
		shareboxPg.clickOneditsaveBtnInSharingPopup();
		
		
		sitesPage.clickOnMoreSetting(folder2);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folder2, moreSettingsOptionName1);
		
		docLibPg.clickOnOkBtnInPopup();
		
		docLibPg.deleteAllFilesAndFolders();
		
		
		/*tasksPage.filter(filter);
		
		sitesPage.documentdetails(folderName);
		
		myFiles.uploadFileInMyFilesPage(filePath, file3);
		*/
		
		siteDashboard.navigateToSiteDashboard();
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		
	try {	
		if (!homePage.isUserActivityDisplayedInMyActivityDashlet("External User added Document in AutoTest", activityName,
				" ")) {
			report.updateTestLog("Verify that a record is not displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet as expected" + "<br/><b> Activity Did : </b>"
							+ "External User added Document in Folder in Share UI <br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record  displayed in the 'My Activity' dashlet which is not expected" + "<br/><b> Activity Did : </b>"
							+ "External User added Document in Folder in Share UI <br/>",
					Status.FAIL);
		}
		UIHelper.waitFor(driver);
		
		
		if (!homePage.isUserActivityDisplayedInMyActivityDashlet("External User added Document in AutoTest1", fileName,
				" ")) {
			report.updateTestLog("Verify that a record is not displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet as expected" + "<br/><b> Activity Did : </b>"
							+ "External User added Document in SubFolder in Share UI <br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record  displayed in the 'My Activity' dashlet which is not expected" + "<br/><b> Activity Did : </b>"
							+ "External User added Document in SubFolder in Share UI <br/>",
					Status.FAIL);
		}
		UIHelper.waitFor(driver);
		
		
		if (!homePage.isUserActivityDisplayedInMyActivityDashlet("ALF01 edited Shared folder Share details", folder2,
				" ")) {
			report.updateTestLog("Verify that a record is not displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet as expected" + "<br/><b> Activity Did : </b>"
							+ "ALF01 edited Shared folder Share details <br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record  displayed in the 'My Activity' dashlet which is not expected" + "<br/><b> Activity Did : </b>"
							+ "ALF01 edited Shared folder Share details <br/>",
					Status.FAIL);
		}
		
		
		if (!homePage.isUserActivityDisplayedInMyActivityDashlet("ALF01 stopped sharing Shared folder", folder2,
				" ")) {
			report.updateTestLog("Verify that a record is not displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet as expected" + "<br/><b> Activity Did : </b>"
							+ "ALF01 stopped sharing Shared folder <br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record  displayed in the 'My Activity' dashlet which is not expected" + "<br/><b> Activity Did : </b>"
							+ "ALF01 stopped sharing Shared folder <br/>",
					Status.FAIL);
		}
		
		if (homePage.isUserActivityDisplayedInMyActivityDashlet("ALF01 deleted ", "documents",
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ "ALF01 deleted document "+ folder2 +"<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ "ALF01 deleted document "+ folder2 + "<br/>",
					Status.FAIL);
		}
		
	/*	if (new AlfrescoHomePage(scriptHelper).isUserActivityDisplayedInMyActivityDashlet("ALF01 added document", file3,
				" ")) {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record displayed in the 'My Activity' dashlet Successfully" + "<br/><b> Activity Did : </b>"
							+ "ALF01 added document "+ file3 + "<br/>",
					Status.PASS);
		} else {
			report.updateTestLog("Verify that a record is displayed in the 'My Activity' dashlet",
					"Record not displayed in the 'My Activity' dashlet" + "<br/><b> Activity Did : </b>"
							+ "ALF01 added document "+ file3+ "<br/>",
					Status.FAIL);
		}
		UIHelper.waitFor(driver);*/
		
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