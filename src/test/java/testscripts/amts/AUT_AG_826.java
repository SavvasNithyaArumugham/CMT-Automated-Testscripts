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

public class AUT_AG_826 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_821()
	{
		testParameters.setCurrentTestDescription(
		"[1]ALFDEPLOY-4758_Verify the relationship between source and target is displayed in relationship section of target audio file preview on applying file"
		+ "based transformation to audio file when file same as target already exists or uploaded in target location <br>"
				
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
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		
		
	
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites","SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String ParentFolderName = dataTable.getData("MyFiles", "Version");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String imageType = dataTable.getData("Media_Transform", "OutPutFormat");
		String options = dataTable.getData("MyFiles", "MoreSettingsOption");
		String ProfiledetailsMp3[] = dataTable.getData("Media_Transform", "verifyVdoSettings").split(";");
		
		String subAsstCode = dataTable.getData("Media_Transform", "subAsstCode");
		
		
		String finalFileName = fileName.replace(".wav", ".._"+ProfiledetailsMp3[3]+"__"+ProfiledetailsMp3[3]+"-"+preTxt+"."+imageType);
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
		
		sitesPage.documentdetails(ParentFolderName);
	//	myFiles.uploadFile(filePath, finalFileName);	
		myFiles.uploadFile(filePath, fileName);
		
		
		String reName = fileName.replace(".", ".._"+ProfiledetailsMp3[3]+"_.");
		myFiles.rename(fileName, reName);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		
		sitesPage.clickOnMoreSetting(reName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(reName, docActionVal);
		
		mediaTransPage.applyTransformationByFileBased(reName, preTxt);
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitFor(driver);
		
	//	UIHelper.waitForVisibilityOfEleByXpath(driver, finalFileName);
		if(mediaTransPage.isNavigatedToDocumentLibrary()){
			sitesPage.documentdetails(ParentFolderName);
			
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
	
		
		sitesPage.documentdetails(finalFileName);
		docDetailsPageTest.verifyAddedRelationshipData(reName);
		
		

	}

	@Override
	public void tearDown() {
		
	}

}
