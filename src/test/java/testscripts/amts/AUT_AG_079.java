package testscripts.amts;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_079 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_039()
	{
		testParameters.setCurrentTestDescription("Verify the user is able to navigate to the document library after applying the created profile with a target folder");
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
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String targetfolder = dataTable.getData("MyFiles", "CreateFileDetails");
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		/*if (mediaTransPage.checkMediaProfAvail(profName)){
			mediaTransPage.deleteProfileFrmMediaTransPg(profName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		}*/
		
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();

		mediaTransPage.commonMethodForEnterImageProfDetails(profName,
				profDesc, macCode, subAsstCode,
				null, null);
				
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			mediaTransPage.selectImgTransType(imageType);
			mediaTransPage.clickAddBtnImgProf();
			mediaTransPage.clickSaveBtnImgProf();
		}
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		//docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		myFiles.uploadFile(filePath, fileName);
		sitesPage.enterIntoDocumentLibrary();
		
		/*String reName = fileName.replace(".", "_"+subAsstCode+"_.");
		myFiles.rename(fileName, reName);
		UIHelper.waitFor(driver);*/
		UIHelper.waitFor(driver);
		
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, docActionVal);
		
		mediaTransPage.applyTransformationToTargetFolder(profName, folderName, targetfolder, preTxt, sourceSiteName);
		UIHelper.waitFor(driver);
		/*UIHelper.pageRefresh(driver);
		UIHelper.waitFor(driver);*/
		//String finalFileName = reName.replace(".gif", "_"+subAsstCode+"-"+preTxt+"."+imageType);
		if(mediaTransPage.isNavigatedToDocumentLibrary()){
			myFiles.openCreatedFolder(targetfolder);
			String fileToCheck = fileName.replace(".gif", "_"+subAsstCode+"-"+preTxt+"."+imageType);
			if(docLibPg.isFileIsAvailable(fileToCheck)){
				report.updateTestLog("Verify the File Transformed in Target Folder",
						"File verified successfully"
								+ "<br><b> Transformed File Name : </b>"
								+ fileToCheck, Status.PASS);
			}else{
				report.updateTestLog("Verify the File Transformed in Target Folder",
						"File not verified"
								+ "<br><b> Transformed File Name : </b>"
								+ fileToCheck, Status.FAIL);
			}
		}
	}

	@Override
	public void tearDown() {
		
	}

}