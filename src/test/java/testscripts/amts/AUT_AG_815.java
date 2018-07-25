package testscripts.amts;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

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

public class AUT_AG_815 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_030() {
		testParameters.setCurrentTestDescription(
				"To Verify whether the user is able to select a Transformation profile from the list of available and apply to a folder.");
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

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folderName1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String profName = "";
		String fileNmeBased = dataTable.getData("Media_Transform", "BasedOnFile");
		String watchFldr = dataTable.getData("Media_Transform", "WatchFolder");

		
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateVideoProfBtn();
		mediaTransPage.enterVideoProfDetailsWithUniqueName();

		try {
			profName = new FileUtil().readDataFromFile(mediaTransPage.testOutputFilePathVdoProf);
			mediaTransPage.clickOnAddImageProfBtn();
		//	if (mediaTransPage.clickOnAddVideoProfBtn()) {
				if (mediaTransPage.clickSaveBtn()){
				report.updateTestLog("Verify video profile created",
						"Video profile created successfully." + "<br><b>Video Profile Name : </br>" + profName,
						Status.PASS);
			} else {
				report.updateTestLog("Verify vidoe profile created", "Failed to create Video profile", Status.FAIL);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* mediaTransPage.clickAddBtn(); */
		// mediaTransPage.clickSaveBtn();
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(folderName);
		myFiles.uploadFile(filePath, fileNme);

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		/*
		 * sitesPage.clickOnMoreSetting(fileNme);
		 * docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme,
		 * docActionVal);
		 */

		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, docActionVal);
		mediaTransPage.selectFileNmeBaseRadio(fileNmeBased);
		mediaTransPage.watchFldrRadio(watchFldr);

		mediaTransPage.applyTransformationToTargetFolder(profName, folderName, folderName, preTxt, sourceSiteName);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, docActionVal);
		mediaTransPage.selectFileNmeBaseRadio(fileNmeBased);
		mediaTransPage.watchFldrRadio(watchFldr);
		mediaTransPage.applyTransformationToTargetFolder(profName, folderName, folderName1, preTxt, sourceSiteName);
		
		
		/*mediaTransPage.selectTargetFolderAndApplyTransformationProfile(profName, sourceSiteName, folderName,
				folderName1, preTxt);*/

	
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName1);

		
		 // String transFileNme = fileNme.replaceFirst(".3G2",".mp4"); 
		  //transFileNme = transFileNme.replace(".3G2", ""); 
		 // String transFileNme = fileNme+"_"+subAsstCode+"-"+preTxt+".mp4";
		 

		if (mediaTransPage.isTransferredFileIsAvailable(preTxt)) {
			report.updateTestLog("Verify video profile applied Successfully to the File " + fileNme,
					"Video profile applied successfully.", Status.PASS);

		}

		else {
			report.updateTestLog("Verify video profile applied Successfully to the File " + fileNme,
					"Video profile NOT applied successfully." + "<br><b>File  Name : </br>", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {

	}

}
