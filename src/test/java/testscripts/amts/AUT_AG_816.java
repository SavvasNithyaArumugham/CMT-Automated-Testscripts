package testscripts.amts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_816 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_29() {
		testParameters.setCurrentTestDescription(
				"To Verify whether the user is able to select a Transformation profile from the list of available and set rules for folders, based on filename, to automatically apply the profile transforms.");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);

		homePageObj.navigateToSitesTab();

		String siteName = dataTable.getData("Sites", "SiteName");
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folderName1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String moreSettingsOptName = dataTable.getData("MyFiles", "MoreSettingsOption");

		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		myFiles.uploadFile(filePath, fileName);

		String profName = dataTable.getData("Media_Transform", "ProfName");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String subAsstCode = dataTable.getData("Media_Transform", "subAsstCode");
		String Data = dataTable.getData("Media_Transform", "VdoSettings");
		String fleNmeBas = dataTable.getData("Media_Transform", "BasedOnFile");
		String wtchFldr = dataTable.getData("Media_Transform", "WatchFolder");
		String prefTxt = dataTable.getData("Media_Transform", "Preference");

		/*
		 * mediaTransPage.navigateToMediaTransPage();
		 * mediaTransPage.navigateToProfilePage(profName);
		 * mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		 * mediaTransPage.clickOnCreateVideoProfBtn();
		 * mediaTransPage.commonMethodToEnterVideoProfDetails(profName,
		 * subAsstCode, profDesc, macCode);
		 * mediaTransPage.clickOnAddImageProfBtn();
		 * UIHelper.waitForPageToLoad(driver);
		 * mediaTransPage.enterVideoSettingsDetails(Data);
		 * mediaTransPage.clickAddBtn(); mediaTransPage.clickSaveBtn();
		 * UIHelper.waitForPageToLoad(driver);
		 */
		sitesPage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
		UIHelper.waitForPageToLoad(driver);
		sitesPage.clickOnEditProperties(fileName);
		String[] fileNamePart = fileName.split(Pattern.quote("."));
		fileName = fileNamePart[0] + "_" + subAsstCode + "." + fileNamePart[1];
		docLibPg.documentRename(fileName);
		// docLibPg.navigateToDocumentLibrary();
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptName);
		mediaTransPage.selectFileNmeBaseRadio(fleNmeBas);
		mediaTransPage.watchFldrRadio(wtchFldr);
		mediaTransPage.applyTransformationToTargetFolder(profName, folderName, folderName1, prefTxt, siteName);
		// mediaTransPage.selectProfileFrmListAndApplyTransformation(profName,
		// prefTxt);
		UIHelper.waitFor(driver);
		sitesPage.openSiteFromRecentSites(siteName);
		docLibPg.navigateToDocumentLibrary();
		myFiles.openCreatedFolder(folderName1);
		if (mediaTransPage.isTransferredFileIsAvailable(fileName)) {
			report.updateTestLog("Verify tranferred  file is available", "File is available", Status.PASS);
		} else {
			report.updateTestLog("Verify tranferred  file is available", "File is not available", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {

	}

}