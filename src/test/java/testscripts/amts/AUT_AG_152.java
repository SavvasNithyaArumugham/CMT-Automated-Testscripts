package testscripts.amts;

import java.util.regex.Pattern;

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

public class AUT_AG_152 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_080() {
		testParameters
				.setCurrentTestDescription("Verify whether user is able to create image profile with watermark as None");
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
		AlfrescoMediaTransformPageTest mediaTransPageTest = new AlfrescoMediaTransformPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String imageType = dataTable.getData("Media_Transform",
				"FileFormatType");
		String waterMarkOpt = dataTable.getData("Media_Transform",
				"EffectOption");
		String filePathForUploadFile = dataTable.getData("Media_Transform",
				"filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform",
				"fileName");

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");		
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String fileNmeBased = dataTable.getData("Media_Transform",
				"BasedOnFile");
		String watchFldr = dataTable.getData("Media_Transform", "WatchFolder");

		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.commonMethodForEnterImageProfDetails(profName, profDesc,
				macCode, subAsstCode, filePathForUploadFile, fileNameForUpload);
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Image profile details",
					"Image Profile details displayed successfully", Status.PASS);
			mediaTransPage.selectImgFileFormatType(imageType);
			mediaTransPage.addWaterMarkForImg(waterMarkOpt);
			mediaTransPageTest.verifyPreviewBtn();
			mediaTransPage.clickOnPreviewButton();
			mediaTransPage.verifyPreviewImageDisplayed();
			mediaTransPage.clickSaveBtnImgProf();
		} else {
			report.updateTestLog("Verify Image Profile creation",
					"Failed to create Image profile", Status.FAIL);
		}

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
		myFiles.uploadFile(filePath, fileNme);
		
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPge.commonMethodForClickOnMoreSettingsOption(fileNme,
				docActionVal);
		mediaTransPage.selectFileNmeBaseRadio(fileNmeBased);
		mediaTransPage.watchFldrRadio(watchFldr);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName,preTxt);
		sitesPage.enterIntoDocumentLibrary();
		String[] file = fileNme.split(Pattern.quote("."));
		if (imageType.equalsIgnoreCase("jpeg")) {
			imageType = "jpg";
		}
		String transformedFileName = file[0] + "_" + subAsstCode + "-" + preTxt + "."
				+ imageType;

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
				transformedFileName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		if (mediaTransPage.isTransferredFileIsAvailable(transformedFileName)) {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ transformedFileName, "Image profile applied successfully.",
					Status.PASS);

		} else {
			report.updateTestLog(
					"Verify Image profile applied Successfully to the File "
							+ transformedFileName, "Image profile is not applied."
							+ "<br><b>File  Name : </br>", Status.FAIL);
		}
		
		myFiles.openUploadedOrCreatedFile(transformedFileName, "");

	}

	@Override
	public void tearDown() {

	}

}
