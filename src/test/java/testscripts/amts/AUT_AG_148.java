package testscripts.amts;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaTransformPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_148 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_076() {
		testParameters
				.setCurrentTestDescription("Verify the user is able to apply rendition profile to folder containing PNG image and multiple subfolders");
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
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		AlfrescoMediaTransformPageTest mediaTransPageTest = new AlfrescoMediaTransformPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoAdminToolsPage adminPg = new AlfrescoAdminToolsPage(
				scriptHelper);
		
		String profName = dataTable.getData("Media_Transform", "ProfName");

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String deepFolderDetails = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String parentFolderName = dataTable.getData("MyFiles", "Version");
		
		String docActionVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");		
		String fileNmeBased = dataTable.getData("Media_Transform",
				"BasedOnFile");
		String watchFldr = dataTable.getData("Media_Transform", "WatchFolder");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(parentFolderName);
		
		myFiles.uploadFile(filePath, fileNme);
		
		String folderName = "";
		if (deepFolderDetails.contains(";")) {
			String splittedFolderDetailas[] = deepFolderDetails.split(";");
			for (String folderValues : splittedFolderDetailas) {
				String splittedFolderValues[] = folderValues.split(",");

				folderName = splittedFolderValues[0].replace("FolderName:", "");
				String folderTitle = splittedFolderValues[1].replace("Title:", "");
				String folderDescription = splittedFolderValues[2].replace("Description:", "");

				for(int index=1;index<=3;index++)
				{
					myFiles.commonMethodForCreateFolder(folderName+index, folderTitle+index, folderDescription+index);
				}
			}
		} else {
			String splittedFolderValues[] = deepFolderDetails.split(",");

			folderName = splittedFolderValues[0].replace("FolderName:", "");
			String folderTitle = splittedFolderValues[1].replace("Title:", "");
			String folderDescription = splittedFolderValues[2].replace("Description:", "");

			for(int index=1;index<=3;index++)
			{
				myFiles.commonMethodForCreateFolder(folderName+index, folderTitle+index, folderDescription+index);
			}
		}
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(parentFolderName);
		docLibPge.commonMethodForClickOnMoreSettingsOption(parentFolderName,
				docActionVal);
		mediaTransPage.selectFileNmeBaseRadio(fileNmeBased);
		mediaTransPage.watchFldrRadio(watchFldr);
		mediaTransPage.selectAndApplyRenditionProfile(profName);
		sitesPage.enterIntoDocumentLibrary();

		sitesDashboardPage.navigateToSiteDashboard();
		
		String dashletName = dataTable.getData("Home", "DashletName");
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashletName))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		String expectedHeaderNames = dataTable.getData("Site_Dashboard", "AsyncDashletHeaderNames");
		sitesDashboardPageTest.verifyHeaderNamesInAsyncDashlet(expectedHeaderNames);
		
		String expectedQueuedNamesForEPSJob = dataTable.getData("Site_Dashboard", "QueuedStatusForEPSJob");
		sitesDashboardPageTest.verifyPublishedFileQueuStatusInAsyncDashlet(expectedQueuedNamesForEPSJob,
				fileNme);
		sitesPage.enterIntoDocumentLibrary();
		
		mediaTransPageTest.verifyMediaWatchFolders(parentFolderName);
		
		myFiles.openCreatedFolder(parentFolderName);

		for(int index=1;index<=3;index++)
		{
			mediaTransPageTest.verifyMediaWatchFolders(folderName+index);
		}
		
		myFiles.openCreatedFolder(folderName+"1");
		myFiles.commonMethodForCreateFolder(folderName+"11", "TestTitle11", "TestDesc11");
		
		mediaTransPageTest.verifyMediaWatchFolders(folderName+"11");
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(parentFolderName);
		
		myFiles.openCreatedFolder(folderName+"2");
		myFiles.uploadFile(filePath, fileNme);
		
		sitesDashboardPage.navigateToSiteDashboard();
		sitesDashboardPageTest.verifyPublishedFileQueuStatusInAsyncDashlet(expectedQueuedNamesForEPSJob,
				fileNme);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(parentFolderName);
		myFiles.openUploadedOrCreatedFile(fileNme, "");
		
		String nodeRef = myFiles.getMarkLogicUrl();
		
		homePage.navigateToAdminTab();
		adminPg.navigateToNodeBrowserTab();
		adminPg.searchNodeBrowserResults(nodeRef);
		adminPg.navigateToNodeBrowserDetailsPg(fileNme);
		
		String childName = dataTable.getData("Admin", "ChildName");
		adminPg.clickOnChildNameLinkUnderChildren(childName);
		
		String propertyName = dataTable.getData("Admin", "PropertyName");
		adminPg.clickOnPropertyNameValueLink(propertyName);
	}

	@Override
	public void tearDown() {

	}

}
