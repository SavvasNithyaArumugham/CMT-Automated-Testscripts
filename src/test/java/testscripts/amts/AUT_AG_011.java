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

public class AUT_AG_011 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_088() {
		testParameters
				.setCurrentTestDescription("Verify user is able to created video profile with sub asset code containing multiple dashes at the end and verify its transformed file by applying the profile<br>"+
		"ALFDEPLOY-4427_Verify the target output filename does not cut off period present in source file when video transformation is applied for file containining period <br>"+
						"ALFDEPLOY-4427_Verify the target output filename does not cut off period present in source file when video transformation is applied for folder containing video files with period <br>"+
		"ALFDEPLOY-4109_Verify the target video file is updated with new version and content when target video file resides in the target location upon applying video transformation for a file");
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
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String imageType = dataTable.getData("Media_Transform",
				"FileFormatType");
	/*	String filePathForUploadFile = dataTable.getData("Media_Transform",
				"filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform",
				"fileName");*/
		String rename = dataTable.getData("MyFiles", "RelationshipName");
		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String version1 = dataTable.getData("MyFiles", "BrowseActionName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
	/*	String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");*/
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.pageRefresh(driver);
		mediaTransPage.clickOnCreateVideoProfBtn();
		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc,
				macCode, subAsstCode);
				
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Video profile details",
					"Video Profile details displayed successfully", Status.PASS);	
			
			mediaTransPage.clickSaveBtn();

		} else {
			report.updateTestLog("Verify Video Profile creation",
					"Failed to create Video profile", Status.FAIL);
		}

		homePageObj.navigateToSitesTab();
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.uploadFile(filePath, fileNme);
		
		
		
		myFiles.rename(fileNme, rename);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(rename);
		docLibPg.commonMethodForClickOnMoreSettingsOption(rename,
				docActionVal);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName,
				preTxt);
		sitesPage.enterIntoDocumentLibrary();
		//myFiles.openCreatedFolder(folderName);   
		String finalFileName = rename.replace(".3gp","_"+subAsstCode+"-"+preTxt+"."+imageType);
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
	
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(rename);
		docLibPg.commonMethodForClickOnMoreSettingsOption(rename,
				docActionVal);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName,
				preTxt);
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(finalFileName);

		mediaTransPage.checkVersionOfTransformedFile(version1);
		
		
	}

	@Override
	public void tearDown() {

	}

}
