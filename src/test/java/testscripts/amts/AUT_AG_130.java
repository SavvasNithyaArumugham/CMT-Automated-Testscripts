package testscripts.amts;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaTransformPageTest;
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
public class AUT_AG_130 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_068()
	{
		testParameters.setCurrentTestDescription("Verify the version of the file is not incremented even after applying transformations for a transformed with a target folder <br>"+
	"ALFDEPLOY-4109_Verify target image files are not overriden when the target image files reside in target location upon applying image transformation to folder containing image files <br>" +
				"ALFDEPLOY-4109_Verify target video files are not overriden when the target video files reside in target location upon applying video transformation to folder containing video files <br>"+
	"ALFDEPLOY-4109_Verify target image files are not overriden when the target image files reside in target location upon applying filebased image transformation to folder containing image files having multiple keyword <br> " +
				"ALFDEPLOY-4109_Verify target video files are not overriden when the target video files reside in target location upon applying filebased video transformation to folder containing video files having multiple keyword ");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String VprofName = dataTable.getData("Media_Transform", "Video_Bit");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String VsubAsstCode = dataTable
				.getData("Media_Transform", "height");
		String imageType = dataTable.getData("Media_Transform", "OutPutFormat");		
		String filePathForUploadFile = dataTable.getData("Media_Transform", "filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform", "fileName");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String VprofDesc = dataTable.getData("Media_Transform", "Max_BitRate");
		String macCode = dataTable.getData("Media_Transform", "macCode");	
		String VmacCode = dataTable.getData("Media_Transform", "width");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "RelationshipName");
		String targetfolder = dataTable.getData("MyFiles", "CreateFileDetails");
		String siteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String VfileName = dataTable.getData("MyFiles", "Sort Options");
		String prefTxt = dataTable.getData("Media_Transform", "Preference");		
		String file = dataTable.getData("MyFiles", "BrowseActionName");		
		String transFileName= file+"_"+subAsstCode+"-"+prefTxt+"."+imageType;
	//	String transFileName= file+"_"+subAsstCode+"-"+prefTxt+"."+imageType;
		String version = dataTable.getData("MyFiles", "Version");
		String moreSettingsOptName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		
		String VimageType = dataTable.getData("Media_Transform",
				"OutPutFormatForEditProfile");
		
		String finalFileName = VfileName.replace(".3gp","_"+VsubAsstCode+"-"+preTxt+"."+VimageType);
		System.out.println(finalFileName);
		
		
	mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(VprofName);
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateVideoProfBtn();
		mediaTransPage.commonMethodForEnterVideoProfDetails(VprofName, VprofDesc,
				VmacCode, VsubAsstCode);
				
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Video profile details",
					"Video Profile details displayed successfully", Status.PASS);	
			
			mediaTransPage.clickSaveBtn();

		} else {
			report.updateTestLog("Verify Video Profile creation",
					"Failed to create Video profile", Status.FAIL);
		}
		
		//mediaTransPage.navigateToMediaTransPage();
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
		

		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		
		
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();


		docLibPg.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		myFiles.uploadFile(filePath, fileName);
		myFiles.uploadFile(filePath, VfileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptName);
		mediaTransPage.applyTransformationToTargetFolder(profName, folderName, targetfolder, preTxt, sourceSiteName);	
		/*sitesPage.enterIntoDocumentLibraryWithoutReport();	
		UIHelper.pageRefresh(driver);*/
		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptName);
		mediaTransPage.applyTransformationToTargetFolder(profName, folderName, targetfolder, preTxt, sourceSiteName);
		
		
		sitesPage.clickOnMoreSetting(VfileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(VfileName, moreSettingsOptName);
		mediaTransPage.applyTransformationToTargetFolder(VprofName, folderName, targetfolder, preTxt, sourceSiteName);	
	/*	sitesPage.enterIntoDocumentLibraryWithoutReport();	
		UIHelper.pageRefresh(driver);*/
		
		sitesPage.clickOnMoreSetting(VfileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(VfileName, moreSettingsOptName);
		mediaTransPage.applyTransformationToTargetFolder(VprofName, folderName, targetfolder, preTxt, sourceSiteName);
		
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.documentdetails(targetfolder);
		sitesPage.documentdetails(finalFileName);
		mediaTransPage.checkVersionOfTransformedFile(version);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.documentdetails(targetfolder);
		sitesPage.documentdetails(transFileName);
		mediaTransPage.checkVersionOfTransformedFile(version);
		
/*		docDetailsPageObj.commonMethodForPerformDocAction(moreSettingsOptName);
		sitesPage.clickOnMoreSetting(transFileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(transFileName, moreSettingsOptName);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName, prefTxt);		
		sitesPage.enterIntoDocumentLibraryWithoutReport();		
		sitesPage.documentdetails(targetfolder);	
		sitesPage.documentdetails(transFileName);
		mediaTransPage.checkVersionOfTransformedFile(version);		*/
		
	}

	@Override
	public void tearDown() {
		
	}

}
