package testscripts.amts;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_822 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_821()
	{
		testParameters.setCurrentTestDescription(
		
		"ALFDEPLOY-4109_Verify target audio files are not overriden when the "
		+ "target audio files reside in target location upon applying audio transformation"
		+ " to folder containing audio files"+
		
		"ALFDEPLOY-4109_Verify target audio files are not overriden when the target audio "
		+ "files reside in target location upon applying filebased audio transformation to"
		+ " folder containing audio files having multiple keyword"
		
		+"ALFDEPLOY-4109_Verify the target audio file is updated with new version "
		+ "and content when target audio file resides in the"
		+ " target location upon applying audio transformation "	
		+
		"[1]ALFDEPLOY-4758_Verify the relationship between source and target is displayed in relationship section of target image file"
		+ " preview on applying file based transformation to image file when file same as target already exists or uploaded in target location<br>"
				);
		
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(	scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		
		
		String fileNme[] = dataTable.getData("MyFiles", "FileName").split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites","SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String ParentFolderName = dataTable.getData("MyFiles", "Version");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String watchFldr = dataTable.getData("Media_Transform", "WatchFolder");
		String dashletName = dataTable.getData("Home", "DashletName");
		String options = dataTable.getData("MyFiles", "MoreSettingsOption");
		String ProfiledetailsMp3[] = dataTable.getData("Media_Transform", "verifyVdoSettings").split(";");
		String Profiledetailsaac[] = dataTable.getData("Media_Transform", "VdoSettings").split(";");
		String Profiledetails[] = dataTable.getData("Media_Transform", "Max_BitRate").split(";");
		String version = dataTable.getData("MyFiles", "BrowseActionName");
		UIHelper.waitFor(driver);
		// Creation of the MP3 Audio Profile
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(ProfiledetailsMp3[0]);
		mediaTransPage.clickOnCreateaudioProfBtn();
		mediaTransPage.enterAudioProfileDetails(ProfiledetailsMp3[0], ProfiledetailsMp3[1], ProfiledetailsMp3[2], ProfiledetailsMp3[3]);
		mediaTransPage.SubmitAddTransformationrules(options);
		System.out.println(ProfiledetailsMp3[4]);
		mediaTransPage.enteraudioSettingsDetails(ProfiledetailsMp3[4]);
		UIHelper.click(driver, mediaTransPage.vdoSaveBtnXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTransPage.OkbuttonXpath);
		UIHelper.click(driver, mediaTransPage.OkbuttonXpath);
		UIHelper.waitFor(driver);
		
			
		sitesPage.navigateToSitesTab();
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		
		for(String file:fileNme){			
		myFiles.uploadFile(filePath, file);	
		sitesPage.clickOnMoreSetting(file);
		docLibPg.commonMethodForClickOnMoreSettingsOption(file,	docActionVal);
		mediaTransPage.applyTransformationToTargetFolder(ProfiledetailsMp3[0], file, ParentFolderName, preTxt, sourceSiteName);	
			
		sitesPage.clickOnMoreSetting(file);
		docLibPg.commonMethodForClickOnMoreSettingsOption(file,	docActionVal);
		mediaTransPage.applyTransformationToTargetFolder(ProfiledetailsMp3[0], file, ParentFolderName, preTxt, sourceSiteName);	
		
		
		String finalFileName="AlfrescoWAVFile_01CA-out.mp3";
		System.out.println(finalFileName);
		myFiles.openCreatedFolder(ParentFolderName);
		sitesPage.documentdetails(finalFileName);
		mediaTransPage.checkVersionOfTransformedFile(version);
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		docDetailsPageObj.commonMethodForPerformDocAction("Apply Transformation Profile");
		mediaTransPage.selectProfileFrmListAndApplyTransformation(ProfiledetailsMp3[0], preTxt);		
		sitesPage.enterIntoDocumentLibraryWithoutReport();		
		sitesPage.documentdetails(ParentFolderName);	
		sitesPage.documentdetails(finalFileName);
		mediaTransPage.checkVersionOfTransformedFile(version);
		
		
		}
		

	}

	@Override
	public void tearDown() {
		
	}

}
