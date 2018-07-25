package testscripts.sharebox;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
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
public class AUT_AG_041P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters.setCurrentTestDescription(
				"[1]ALFDEPLOY-5278_Document library filter in sidebar to show all ShareBox folders in a site.<br>"
						+ "[1]ALFDEPLOY-5278_Verify files OR sub folders present in sharebox filter is accessible<br>"
						+ "[1]ALFDEPLOY-5278_Verify the changes made via Edit Sharing in shared folder present in document library is reflected in the Sharebox filter and vice versa<br>"
						+ "[1]ALFDEPLOY-5278_Verify the display of 1 shared folder in the Sharebox filter present in the side bar of document library when only one folder is shared<br>"
						+ "[1]ALFDEPLOY-5278_Verify the display of message-No content items_in the Sharebox filter present in the side bar of document library when none of the folders are shared<br>"
						+ "[1]ALFDEPLOY-5278_Verify the expired shared folder is listed in the Sharebox filter present in the side bar of document library<br>"
						+ "[1]ALFDEPLOY-5278_Verify the list of all shared folders in the Sharebox filter present in the side bar of document library when it is in Detailed View<br>"
						+ "[1]ALFDEPLOY-5278_Verify the list of all shared folders in the Sharebox filter present in the side bar of document library when it is in Simple view<br>"
						+ "[1]ALFDEPLOY-5278_Verify the list of all shared folders in the Sharebox filter present in the side bar of document library when multiple folders are shared<br>"
						+ "[1]ALFDEPLOY-5278_Verify the numbers of shared folders in document library matches with number of shared folders in Sharebox filter present in document library<br>"
						+ "[1]ALFDEPLOY-5278_Verify the presence of newly created shared folder in the list of share box filter<br>"
						+ "[1]ALFDEPLOY-5278_Verify the shared folder which is stopped sharing is removed from the list of sharebox filter<br>"
						+ "[1]ALFDEPLOY-5278_Verify the subfolder which is shared in document library is listed in the list of sharebox filter<br>"
						+ "[1]ALFDEPLOY-5278_Verify user is able to perform share box activities(Edit sharing,stop sharing) for shared folders in share box filter<br>"
						+ "[1]ALFDEPLOY-5278_Verify the shared folder deleted in document library is removed from the list of share box filter<br>"
						+ "[1]ALFDEPLOY-5278_Verify the other document filters and library are accessible from share box filter<br>"
						+ "[1]ALFDEPLOY-5278_Verify copied folder get listed in share box filter upon copying shared folder from share box filter to document library<br>"
						+ "[1]ALFDEPLOY-5278_Verify moved folder gets listed in share box filter upon moving shared sub folder from share box filter to document library<br>"
						+ "[1]ALFDEPLOY-5278_Verify the linked shared folder is listed in Sharebox filter present in the side bar of document library<br>"
						+ "[1]ALFDEPLOY-5278_Verify selected items is accessible from share box filter<br>"
						+ "[1]ALFDEPLOY-4582_Verify the options edit external sharing and stop external sharing for the file which is not available inside the shared folder via Document details page.<br>"
						+ "[1]ALFDEPLOY-4582_Verify the dynamic option (Share folder externally) is displayed once the folder is stopped via Folder details page.");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		/*
		 * AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new
		 * AlfrescoSitesDashboardPageTest( scriptHelper);
		 * AlfrescoSitesDashboardPage sitesDashboardPage = new
		 * AlfrescoSitesDashboardPage(scriptHelper); AlfrescoHomePage homePage =
		 * new AlfrescoHomePage(scriptHelper); AlfrescoSitesDashboardPage
		 * siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
		 * AlfrescoDocumentDetailsPage docDetailsPageObj = new
		 * AlfrescoDocumentDetailsPage(scriptHelper);
		 * AlfrescoSiteMembersPageTest siteMemPgTest = new
		 * AlfrescoSiteMembersPageTest( scriptHelper);
		 * AlfrescoMediaTransformPage mediaTransPage = new
		 * AlfrescoMediaTransformPage( scriptHelper);
		 */
		AlfrescoTasksPage tasksPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		String filter = dataTable.getData("MyFiles", "BrowseActionName");
		String favfilter = dataTable.getData("Sites", "CreateSiteLabelNames");

		String folder1 = dataTable.getData("MyFiles", "Sort Options");

		/*
		 * String subfolderDetails1 = dataTable.getData("MyFiles",
		 * "StatusReportValue"); String subfolder1 =
		 * dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		 */
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String exprieddate = dataTable.getData("Sharebox", "Language");

		/*
		 * String properties2 = dataTable.getData("Sharebox",
		 * "EditShareboxFieldsData"); String message2 =
		 * dataTable.getData("Sharebox", "EditMessage");
		 */
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String editproperties = dataTable.getData("Sharebox", "ErrorMessageValue");
		String editmessage = dataTable.getData("Sharebox", "UploadedFilesInShareboxFolder");
		String properties2 = dataTable.getData("Sharebox", "EditNotifications");
		String message2 = dataTable.getData("Sharebox", "EditNotify_Sharer");

		String properties1 = dataTable.getData("Sharebox", "EditShareboxFieldsData");
		String message1 = dataTable.getData("Sharebox", "EditMessage");
		String propertiessub = dataTable.getData("Document_Details", "FilePath");
		String messagesub = dataTable.getData("Document_Details", "FileName");
		/*
		 * String fileName1 = dataTable.getData("Document_Details", "FileName");
		 * String fileName2 = dataTable.getData("Document_Details", "Title")
		 */;

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderDetails1 = dataTable.getData("MyFiles", "StatusReportValue");
		String subfolder = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptionName2 = dataTable.getData("MyFiles", "PopUpMsg");
		String moreSettingsOptionName1 = dataTable.getData("MyFiles", "AccessToken");
		String link = dataTable.getData("Sites", "FileName");

		String linkToOption = dataTable.getData("Document_Details", "Title");

		String folderName = dataTable.getData("MyFiles", "Version");
		String nonSharedfolder = dataTable.getData("MyFiles", "CreateFileDetails");
		/*
		 * String versionDetails = dataTable.getData("Document_Details",
		 * "Version"); String comments = dataTable.getData("Document_Details",
		 * "Comments");
		 */

		String finalsharetitle = shareboxPg.foldershareedittitle.replace("CRAFT", subfolder);
		String copiedFolder = dataTable.getData("Sites", "TargetFolder");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		myFiles.createFolder(folderDetails);

		sitesPage.documentdetails(folderName);

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		tasksPage.filter(filter);

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxPg.nocontentmsg);

			String actualmsg = UIHelper.findAnElementbyXpath(driver, shareboxPg.nocontentmsg).getText();

			if (actualmsg.contains("No content items")) {

				report.updateTestLog(
						"Verify the display of message-No content items_in the Sharebox filter present in the side bar of document library when none of the folders are shared ",
						"The display of message-No content items_in the Sharebox filter present in the side bar of document library when none of the folders are shared <b> <br> "
								+ actualmsg,
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify the display of message-No content items_in the Sharebox filter present in the side bar of document library when none of the folders are shared",
						"No content items_in the Sharebox filter is failed to display <br> " + actualmsg, Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		sitesPage.clickOnMoreSetting(folderName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);
		shareboxPg.commonMethodToEnterSharingProperties(properties, message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();

		sitesPage.enterIntoDocumentLibraryWithoutReport();


		tasksPage.filter(filter);
          UIHelper.waitForPageToLoad(driver);
		if (sitesPage.documentAvailable(folderName) && shareboxPg.isfoldershared(folderName)
				&& !sitesPage.documentAvailable(nonSharedfolder)) {

			report.updateTestLog("Verify ShareBox Filter",
					"Share folder are alone displayed with Sharebox Filter. <b><br>  File/Folder Name: <b>"
							+ folderName,
					Status.PASS);

		} else {
			report.updateTestLog("Verify ShareBox Filter",
					"Shared and un Shared folder are displayed with Sharebox Filter whihc is not expected <b><br>  Un Shared Folder Name: <b>"
							+ nonSharedfolder,
					Status.FAIL);

		}

		sitesPage.documentdetails(folderName);

		if (sitesPage.documentAvailable(fileName)) {

			report.updateTestLog("Verify subfolder/file accessible with ShareBox Filter",
					"subfolder/file accessible with ShareBox Filter as expected. <b><br>  File/Folder Name: <b>"
							+ fileName,
					Status.PASS);

		} else {
			report.updateTestLog("Verify subfolder/file accessible with ShareBox Filter",
					"subfolder/file not accessible with ShareBox Filter which is not expected. <b><br>  File/Folder Name: <b>"
							+ fileName,
					Status.FAIL);

		}
		sitesPage.documentdetails(fileName);
		if (!sitesPage.checkActionNameForFolderOrFileInDocLibPage(moreSettingsOptionName1, fileName)
				&& !sitesPage.checkActionNameForFolderOrFileInDocLibPage(moreSettingsOptionName2, fileName)) {
			report.updateTestLog(
					"Verify the options edit external sharing and stop external sharing for the file which is not available inside the shared folder via Document details page.",
					"the options edit external sharing and stop external sharing for the file which is not available inside the shared folder via Document details page. <b><br>  File/Folder Name: <b>"
							+ fileName,
					Status.PASS);

		} else {
			report.updateTestLog(
					"Verify ALFDEPLOY-4582_Verify the options edit external sharing and stop external sharing for the file which is not available inside the shared folder via Document details page.",
					"the options edit external sharing and stop external sharing for the file which is available inside the shared folder via Document details page.<b><br>  File/Folder Name: <b>"
							+ fileName,
					Status.FAIL);

		}
		// Activity feed check
		// sitesPage.enterIntoSiteDashboard();
		// homePageTest.verifyActivityFeedForSiteMember(sourceSiteName);

		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.documentdetails(folderName);
		myFiles.createFolder(folderDetails1);

		// sitesPage.enterIntoDocumentLibraryWithoutReport();
		// sitesPage.documentdetails(folderName);;
		sitesPage.clickOnMoreSetting(subfolder);

		docLibPg.commonMethodForClickOnMoreSettingsOption(subfolder, moreSettingsOptionName);

		shareboxPg.commonMethodToEnterSharingProperties(propertiessub, messagesub, notification, notifyDetails);

		shareboxPg.clickOnSaveBtnInSharingPopup();

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		tasksPage.filter(filter);

		docLibPg.selectDocLibViewType("Simple View");

		if (sitesPage.documentAvailable(folderName) && sitesPage.documentAvailable(subfolder)
				&& !sitesPage.documentAvailable(nonSharedfolder)) {

			report.updateTestLog(
					"Verify ShareBox Filter displayed shared Subfolder and multiple shared folders in Detailed View",
					"ShareBox Filter displayed shared Subfolder and multiple shared folders as expected. <b><br> Subfolder Name: <b>"
							+ subfolder,
					Status.PASS);

		} else {
			report.updateTestLog("Verify ShareBox Filter displayed shared Subfolder and multiple shared folders",
					"ShareBox Filter is not displaying shared Subfolder and multiple shared folders which is not as expected. <b><br> Subfolder Name: <b>"
							+ subfolder,
					Status.PASS);
		}

		docLibPg.selectDocLibViewType("Detailed View");

		if (sitesPage.documentAvailable(folderName) && sitesPage.documentAvailable(subfolder)
				&& !sitesPage.documentAvailable(nonSharedfolder)) {

			report.updateTestLog(
					"Verify ShareBox Filter displayed shared Subfolder and multiple shared folders in Simple View",
					"ShareBox Filter displayed shared Subfolder and multiple shared folders as expected. <b><br> Subfolder Name: <b>"
							+ subfolder,
					Status.PASS);

		} else {
			report.updateTestLog("Verify ShareBox Filter displayed shared Subfolder and multiple shared folders",
					"ShareBox Filter is not displaying shared Subfolder and multiple shared folders which is not as expected. <b><br> Subfolder Name: <b>"
							+ subfolder,
					Status.PASS);
		}

		// To verify the copied shared folder
		tasksPage.filter(filter);

		if (sitesPage.documentAvailable(folderName) && shareboxPg.isfoldershared(folderName)) {

			sitesPage.clickOnMoreSetting(folderName);

			sitesPage.clickOnCopyToLink(folderName);
			docLibPg.copyFileIntoAnotherSite(folderName, sourceSiteName);
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			if (sitesPage.documentAvailable(copiedFolder) && !shareboxPg.isfoldershared(copiedFolder)) {
				report.updateTestLog("Verify Shared folder is copied into document library",
						"Shared folder is copied successfully into document library and share icon is not present <b><br>  File/Folder Name: <b>"
								+ copiedFolder,
						Status.PASS);

			} else {
				report.updateTestLog("Verify Shared folder is copied into document library",
						"Shared folder is failed to copy into document library <b><br> Folder Name: <b>" + copiedFolder,
						Status.FAIL);

			}
			tasksPage.filter(filter);
			if (!sitesPage.documentAvailable(copiedFolder)) {
				report.updateTestLog(
						"Verify copied folder  get listed in share box filter upon copying shared folder from share box filter to document library",
						"Copied folder is not listed in share box filter<b><br>  File/Folder Name: <b>" + copiedFolder,
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify copied folder  get listed in share box filter upon copying shared folder from share box filter to document library",
						"Copied folder gets listed in share box filter <b><br> Folder Name: <b>" + copiedFolder,
						Status.FAIL);

			}

		}
		// UIHelper.waitFor(driver);

		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.openFolder(copiedFolder);
		// UIHelper.waitFor(driver);
		docLibPg.deleteAllFilesAndFolders();

		// To link the shared folder to any other folder
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		if (sitesPage.documentAvailable(folderName) && shareboxPg.isfoldershared(folderName)) {

			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName, linkToOption);

			docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
			docDetailsPageObj.clickLinkBtnInLinkPopUp();
			docLibPg.selectDocLibViewType("Simple View");
			if (docLibPg.verifyLinkIcon(folderName)) {
				report.updateTestLog("Verify Shared folder is linked and shows link icon",
						"Shared folder is linked successfully and link icon is displayed<b><br>  File/Folder Name: <b>"
								+ folderName,
						Status.PASS);

			} else {
				report.updateTestLog("Verify Shared folder is linked and shows link icon",
						"Shared folder is failed to link<b><br> Folder Name: <b>" + folderName, Status.FAIL);

			}
		}
		docLibPg.selectDocLibViewType("Detailed View");
		sitesPage.openFolder(copiedFolder);
		if (sitesPage.documentAvailable(folderName) && shareboxPg.isfoldershared(folderName)
				&& docLibPg.verifyLinkIcon(folderName)) {
			report.updateTestLog("Verify Shared folder is linked and shows link icon",
					"Shared folder is linked successfully into destination folder and link icon is displayed<b><br>  File/Folder Name: <b>"
							+ folderName,
					Status.PASS);

		} else {
			report.updateTestLog("Verify Shared folder is linked and shows link icon",
					"Shared folder is failed to link to destination folder <b><br> Folder Name: <b>" + folderName,
					Status.FAIL);

		}

		tasksPage.filter(filter);
		if (sitesPage.documentAvailable(folderName) && shareboxPg.isfoldershared(folderName)
				&& docLibPg.verifyLinkIcon(folderName)) {
			report.updateTestLog(
					"Verify the linked shared folder is listed in Sharebox filter present in the side bar of document library",
					"Linked folder gets listed in share box filter<b><br>  File/Folder Name: <b>" + folderName,
					Status.PASS);

		} else {
			report.updateTestLog(
					"Verify the linked shared folder is listed in Sharebox filter present in the side bar of document library",
					"Linked folder failed to get listed in share box filter <b><br> Folder Name: <b>" + folderName,
					Status.FAIL);

		}
		// To verify the moved shared sub folder
		tasksPage.filter(filter);

		if (sitesPage.documentAvailable(subfolder) && shareboxPg.isfoldershared(subfolder)) {
			sitesPage.clickOnMoreSetting(subfolder);
			sitesPage.clickOnMoveToLink(subfolder);
			docLibPg.performMoveToOperation(sourceSiteName, copiedFolder);
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			sitesPage.openFolder(copiedFolder);
			UIHelper.waitFor(driver);
			if (sitesPage.documentAvailable(subfolder) && shareboxPg.isfoldershared(subfolder)) {
				report.updateTestLog("Verify Shared subfolder is moved into document library",
						"Shared subfolder is moved successfully into document library and share icon is present <b><br>  File/Folder Name: <b>"
								+ subfolder,
						Status.PASS);

			} else {
				report.updateTestLog("Verify Shared subfolder is moved into document library",
						"Shared subfolder is failed to move into document library <b><br> Folder Name: <b>" + subfolder,
						Status.FAIL);

			}
			tasksPage.filter(filter);
			if (sitesPage.documentAvailable(subfolder) && shareboxPg.isfoldershared(subfolder)) {
				report.updateTestLog(
						"Verify moved folder gets listed in share box filter upon moving shared sub folder from share box filter to document library",
						"Moved folder gets listed in share box filter<b><br>  File/Folder Name: <b>" + subfolder,
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify moved folder gets listed in share box filter upon moving shared sub folder from share box filter to document library",
						"Moved folder failed to get listed in share box filter <b><br> Folder Name: <b>" + subfolder,
						Status.FAIL);

			}

		}

		sitesPage.clickOnMoreSetting(subfolder);
		docLibPg.commonMethodForClickOnMoreSettingsOption(subfolder, moreSettingsOptionName1);

		shareboxPg.commonMethodToEnterSharingProperties(editproperties, editmessage, notification, notifyDetails);

		shareboxPg.clickOneditsaveBtnInSharingPopup();

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		tasksPage.filter(filter);

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalsharetitle);

			String actualmsg = UIHelper.findAnElementbyXpath(driver, finalsharetitle).getText();

			if (sitesPage.documentAvailable(subfolder) && actualmsg.contains("EditAutoTest")) {

				UIHelper.highlightElement(driver, finalsharetitle);

				report.updateTestLog("Verify edit details in Sharebox are refleted in ShareBox filter",
						"edit details in Sharebox are refleted in ShareBox filter as expected <b> Share Title <b> <br> "
								+ actualmsg,
						Status.PASS);

			} else {
				report.updateTestLog("Verify edit details in Sharebox are refleted in ShareBox filter",
						"edit details in Sharebox are not refleted in ShareBox filter which is not as expected <b> Share Title <b> <br> "
								+ actualmsg,
						Status.FAIL);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sitesPage.clickOnMoreSetting(subfolder);
		docLibPg.commonMethodForClickOnMoreSettingsOption(subfolder, moreSettingsOptionName2);

		docLibPg.clickOnOkBtnInPopup();

		sitesPage.enterIntoDocumentLibraryWithoutReport();
		UIHelper.waitForPageToLoad(driver);
		sitesPage.openFolder(copiedFolder);
		sitesPage.clickOnViewDetails(subfolder);
		UIHelper.waitForPageToLoad(driver);
		docDetailsPageTest.verifyDocAction(moreSettingsOptionName);

	
			report.updateTestLog(
					"Verify the dynamic option (Share folder externally) is displayed once the folder is stopped via Folder details page.",
					"The dynamic option (Share folder externally) is displayed once the folder is stopped via Folder details page. <b><br> Sharing stoped folder Name: <b>"
							+ subfolder,
					Status.PASS);
	

		sitesPage.enterIntoDocumentLibraryWithoutReport();
		tasksPage.filter(filter);

		if (sitesPage.documentAvailable(folderName) && !sitesPage.documentAvailable(subfolder)) {

			report.updateTestLog("Verify ShareBox Filter stop displaying folder after Stop Sharing",
					"ShareBox Filter stop displaying folder after Stop Sharing as expected. <b><br> Sharing stoped folder Name: <b>"
							+ subfolder,
					Status.PASS);

		} else {
			report.updateTestLog("Verify ShareBox Filter stop displaying folder after Stop Sharing",
					"ShareBox Filter  displaying folder after Stop Sharing which is not  expected. <b><br> Sharing stoped folder Name: <b>"
							+ subfolder,
					Status.FAIL);

		}

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		docLibPg.clickOnDocOptions(folder1, link);

		sitesPage.clickOnMoreSetting(folder1);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folder1, moreSettingsOptionName);

		shareboxPg.commonMethodToEnterSharingProperties(properties2, message2, notification, notifyDetails);

		shareboxPg.shareDate(exprieddate, "");
		shareboxPg.clickOnSaveBtnInSharingPopup();

		// sitesPage.enterIntoDocumentLibraryWithoutReport();

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		tasksPage.filter(filter);

		if (sitesPage.documentAvailable(folderName) && shareboxPg.isfoldershared(folderName)
				&& sitesPage.documentAvailable(folder1)) {

			report.updateTestLog("Verify ShareBox Filter displays share expired folder",
					"ShareBox Filter displays share expired folder. <b><br>  share expired Folder Name: <b>" + folder1,
					Status.PASS);

		} else {
			report.updateTestLog("Verify ShareBox Filter displays share expired folder",
					"ShareBox Filter does not display share expired folder. <b><br>  share expired Folder Name: <b>"
							+ folder1,
					Status.FAIL);

		}

		tasksPage.filter(favfilter);

		if (!sitesPage.documentAvailable(folderName) && sitesPage.documentAvailable(folder1)) {

			report.updateTestLog("Verify other document filters are accessible ShareBox Filter ",
					" Other document filters are accessible ShareBox Filter .", Status.PASS);

		} else {
			report.updateTestLog("Verify other document filters are accessible ShareBox Filter ",
					" Other document filters are not accessible ShareBox Filter .", Status.FAIL);
		}

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		sitesPage.clickOnMoreSetting(folder1);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folder1, "Delete Folder");

		docLibPg.deletefolderwithMoreactions();

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		tasksPage.filter(filter);

		if (sitesPage.documentAvailable(folderName) && !sitesPage.documentAvailable(folder1)) {

			report.updateTestLog("Verify Deleted shared folder are not displayed in ShareBox Filter ",
					"Deleted shared folder are not displayed in ShareBox Filter .<b><br>  Deteled Folder Name: <b>"
							+ folder1,
					Status.PASS);

		} else {
			report.updateTestLog("Verify Deleted shared folder are not displayed in ShareBox Filter ",
					"Deleted shared folder are  displayed in ShareBox Filter .<b><br>  Deteled Folder Name: <b>"
							+ folder1,
					Status.FAIL);

		}

		// Verify selected items is accessible from share box filter
		tasksPage.filter(filter);
		AlfrescoRepositoryPage repoPg = new AlfrescoRepositoryPage(scriptHelper);
		if (sitesPage.documentAvailable(folderName) && shareboxPg.isfoldershared(folderName)) {

			repoPg.commonMethodToSelectFileInRepository(folderName);
			sitesPage.clickOnSelectedItems();
			report.updateTestLog("Verify selected items is accessible from share box filter",
					"Selected items is accessible from share box filter<b><br>" + sitesPage.getSelectedItemMenuOption(),
					Status.PASS);
		}

		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}