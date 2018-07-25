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
public class AUT_AG_005 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_082()
	{
		testParameters.setCurrentTestDescription("Verify that macro code is replaced with subasset code in transformed file when filename based transformation is done for source image file(filename_subassetcode_macrocode_)");
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
		myFiles.uploadFile(filePath, fileName);
		
		String reName = fileName.replace(".", "._"+subAsstCode+"_"+macCode+"_.");
		myFiles.rename(fileName, reName);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.clickOnMoreSetting(reName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(reName, docActionVal);
		
		mediaTransPage.applyTransformationByFileBased(reName, preTxt);
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitFor(driver);
		String finalFileName = fileName.replace(".gif", "._"+subAsstCode+"_"+subAsstCode+"_-"+preTxt+"."+imageType);
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
