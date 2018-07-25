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
import com.pearson.automation.alfresco.tests.AlfrescoMediaTransformPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Moshin Shariff
 *
 */
public class AUT_AG_095 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_051()
	{
		testParameters.setCurrentTestDescription("Verify that the user is able to see the queued status of the transformation process");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String imageType = dataTable.getData("Media_Transform", "OutPutFormat");		
		String filePathForUploadFile = dataTable.getData("Media_Transform", "filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform", "fileName");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");	
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();

		mediaTransPage.commonMethodForEnterImageProfDetails(profName,
				profDesc, macCode, subAsstCode,
				filePathForUploadFile, fileNameForUpload);
		
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Image profile details",
					"Image Profile details displayed successfully", Status.PASS);
			mediaTransPage.selectImgTransType(imageType);
			mediaTransPage.clickAddBtnImgProf();
			mediaTransPage.clickSaveBtnImgProf();

		} else {
			report.updateTestLog("Verify Image Profile creation",
					"Failed to create Image profile", Status.FAIL);
		}
		
		homePageObj.navigateToSitesTab();

		String siteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String prefTxt = dataTable.getData("Media_Transform", "Preference");		
		String file = dataTable.getData("MyFiles", "CreateFolder");
		String dashletName = dataTable.getData("Home", "DashletName");
		
		String transFileName= file+"_"+subAsstCode+"-"+prefTxt+"."+imageType;
		String moreSettingsOptName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		myFiles.uploadFile(filePath, fileName);		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptName);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName, prefTxt);		
		docLibPg.navigateToDocumentLibrary();	
		UIHelper.pageRefresh(driver);
		if(mediaTransPage.isTransferredFileIsAvailable(transFileName)){
			report.updateTestLog("Verify tranferred  file is available",
					"File is available", Status.PASS);
		}
		
		sitesPage.openSiteFromRecentSites(siteName);
		sitesDashboardPage.navigateToSiteDashboard();
		
		
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashletName))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		String expectedHeaderNames = dataTable.getData("Site_Dashboard", "AsyncDashletHeaderNames");
		sitesDashboardPageTest.verifyHeaderNamesInAsyncDashlet(expectedHeaderNames);
		
		String expectedQueuedNamesForEPSJob = dataTable.getData("Site_Dashboard", "QueuedStatusForEPSJob");
		sitesDashboardPageTest.verifyPublishedFileQueuStatusInAsyncDashlet(expectedQueuedNamesForEPSJob,
				fileName);
		
		sitesDashboardPageTest.verifyNumberOfEntriesInAsyncDashlet();
		
		sitesDashboardPage.clickOnMoreInfoLinkUnderAsyncDashlet();
		
		sitesDashboardPageTest.verifyPageNavigationForAsyncStatusDashlet();
		
	}

	@Override
	public void tearDown() {
		
	}

}
