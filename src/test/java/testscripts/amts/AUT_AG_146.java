package testscripts.amts;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_146 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_074() {
		testParameters
				.setCurrentTestDescription("Validate the selection of target folder via relative path for video file transformation");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		
		String profName = dataTable.getData("Media_Transform", "ProfName");

		String fileNme1 = dataTable.getData("MyFiles", "FileName");
		String filePath1 = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
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
		
		myFiles.uploadFile(filePath1, fileNme1);
		
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
		sitesDashboardPageTest.verifyPublishedFileQueuStatusInAsyncDashletForNegativeCase(expectedQueuedNamesForEPSJob,
				fileNme1);
		
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(parentFolderName);
		
		String fileNme2 = dataTable.getData("Document_Details", "FileName");
		String filePath2 = dataTable.getData("Document_Details", "FilePath");
		
		myFiles.uploadFile(filePath2, fileNme2);
		
		sitesDashboardPage.navigateToSiteDashboard();
		sitesDashboardPageTest.verifyPublishedFileQueuStatusInAsyncDashlet(expectedQueuedNamesForEPSJob,
				fileNme2);
		
	}

	@Override
	public void tearDown() {

	}

}
