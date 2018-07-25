package testscripts.amts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class AUT_AG_008 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_085() {
		testParameters
				.setCurrentTestDescription("Verify that macro code is replaced with subasset code in transformed file when filename based transformation is done for folder containing image file with filename(filename_macrocode_subassetcode_) <br>"+
		"ALFDEPLOY-4427_Verify the target output filename does not cut off period present in source file when filebased image transformation is applied for folder containing image files with period");
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

		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable.getData("Media_Transform", "subAsstCode");
		String imageType = dataTable.getData("Media_Transform", "OutPutFormat");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String ParentFolderName = dataTable.getData("MyFiles", "Version");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		
		String watchFolderName = dataTable.getData("MyFiles",
				"CreateFileDetails");
		String fileNmeBased = dataTable.getData("Media_Transform",
				"BasedOnFile");
		String watchFldr = dataTable.getData("Media_Transform", "WatchFolder");
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		
		
		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.commonMethodForEnterImageProfDetails(profName,
				profDesc, macCode, subAsstCode,
				null, null);
				
		if (mediaTransPage.clickOnAddImageProfBtn()) {

		
			mediaTransPage.selectImgTransType(imageType);
			mediaTransPage.clickAddBtnImgProf();
			mediaTransPage.clickSaveBtnImgProf();

		} else {

		}
		

		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		//docLibPge.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(ParentFolderName);
		//sitesPage.enterIntoDocumentLibrary();
		
		myFiles.uploadFile(filePath, fileName);
		
		String reName = fileName.replace(".", "._"+macCode+"_"+subAsstCode+"_.");
		myFiles.rename(fileName, reName);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		sitesPage.enterIntoDocumentLibrary();
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.clickOnMoreSetting(ParentFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(ParentFolderName, docActionVal);
		
		mediaTransPage.selectFileNmeBaseRadio(fileNmeBased);
		mediaTransPage.watchFldrRadio(watchFldr);
		mediaTransPage.applyTransformationToTargetFolder(profName,
				ParentFolderName, watchFolderName, "", sourceSiteName);
		
	//	mediaTransPage.applyTransformationByFileBased(reName, preTxt);
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(watchFolderName);
		
		String finalFileName = fileName.replace(".gif", "._"+subAsstCode+"_"+subAsstCode+"."+imageType);
		if(mediaTransPage.isNavigatedToDocumentLibrary()){
			if(docLibPg.isFileIsAvailable(finalFileName)){
				report.updateTestLog("Verify the Transformed file with sub assert code",
						"File verified successfully"
								+ "<br><b> Transformed File Name : </b>"
								+ finalFileName, Status.PASS);
			}else{
				report.updateTestLog("Verify the Transformed file with sub assert code",
						"File not verified"
								+ "<br><b> Transformed File Name : </b>"
								+ finalFileName, Status.FAIL);
			}
		}
	
	}

	@Override
	public void tearDown() {

	}

}
