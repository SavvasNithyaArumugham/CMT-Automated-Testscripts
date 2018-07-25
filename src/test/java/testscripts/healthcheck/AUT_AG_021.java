package testscripts.healthcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.server.handler.CaptureScreenshot;
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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_021 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_026() {
		testParameters
				.setCurrentTestDescription("To Verify whether the user is able to select a Transformation profile from the list of available and apply to a one or more video file.");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("Sites", "FileName");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String fileNmeBased = dataTable.getData("Media_Transform",
				"BasedOnFile");
		String watchFldr = dataTable.getData("Media_Transform", "WatchFolder");

		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);

		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateVideoProfBtn();
		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc,
				macCode, subAsstCode);

		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {
			report.updateTestLog("Verify video profile created",
					"Video profile created successfully."
							+ "<br>Video Profile Name : </br><b>" + profName,
					Status.PASS);
			mediaTransPage.clickSaveBtn();
			
			if(mediaTransPage.checkMediaProfAvail(profName)){
				report.updateTestLog("Verify video profile in 'List of Mediatransform Profiles'",
						"Video profile:"+profName+" displayed successfully", Status.PASS);
			}
			else{
				report.updateTestLog("Verify video profile in 'List of Mediatransform Profiles'",
						"Failed to display video Profile", Status.FAIL);
			}

		} else {
			report.updateTestLog("Verify vidoe profile created",
					"Failed to create Video profile", Status.FAIL);
		}

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();		
		myFiles.uploadFile(filePath, fileNme);

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);

		sitesPage.clickOnMoreSetting(fileNme);

		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, docActionVal);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName,
				preTxt);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.enterIntoDocumentLibrary();		
		String[] file = fileNme.split(Pattern.quote("."));

		String fileName = file[0] + "_" + subAsstCode + "-" + preTxt;

		if (mediaTransPage.isTransferredFileIsAvailable(fileName)) {
			report.updateTestLog(
					"Verify video profile applied Successfully to the File "
							+ fileNme, "Video profile applied successfully.",
					Status.PASS);

		}

		else {
			report.updateTestLog(
					"Verify video profile applied Successfully to the File "
							+ fileNme,
					"Video profile NOT applied successfully."
							+ "<br><b>File  Name : </br>", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {

	}

}
