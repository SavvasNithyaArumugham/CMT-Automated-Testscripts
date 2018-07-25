package testscripts.amts;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMediaTransformPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_101 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_056() {
		testParameters
				.setCurrentTestDescription("Verify that 'Request placed in queue' message is displayed after applying the transformation for a profile - Video Profile");
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
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		AlfrescoMediaTransformPageTest mediaTransPageTest = new AlfrescoMediaTransformPageTest(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String moreSettingsOpt = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String imageType = dataTable.getData("Media_Transform", "OutPutFormat");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		
		String videoSettingsData = dataTable.getData("Media_Transform", "VdoSettings");
		
		mediaTransPage.navigateToMediaTransPage();

		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.pageRefresh(driver);
		mediaTransPage.clickOnCreateVideoProfBtn();

		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc, macCode, subAsstCode);

		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {

			mediaTransPage.enterVideoSettingsDetails(videoSettingsData);
			
			/*mediaTransPage.clickAddBtn();*/
			mediaTransPage.clickSaveBtn();
			
			mediaTransPageTest.verifyMediaTransformationProfileFile(profName);

		} else {
			report.updateTestLog("Verify Video Profile creation",
					"Failed to create Video profile", Status.FAIL);
		}

		homePage.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		myFiles.uploadFile(filePath, fileName);

		myFilesTestObj.verifyUploadedFile(fileName, "");
		
		String fileNameVal = "";
		String fileTypeVal = "";
		if (fileName.contains(".")) {
			String splitVal[] = fileName.split(Pattern.quote("."));
			fileNameVal = splitVal[0];
			fileTypeVal = "." + splitVal[1];
		}
		
		myFiles.rename(fileName, fileNameVal+"_"+subAsstCode+fileTypeVal);
		
	/*	sitesPage.clickOnEditProperties(fileName);	
		docLibPg.documentRename(fileNameVal+"_"+subAsstCode+fileTypeVal);
		
		myFilesTestObj.verifyRenamedFile(fileNameVal+"_"+subAsstCode+fileTypeVal);*/
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();

		sitesPage.clickOnMoreSetting(fileNameVal+"_"+subAsstCode+fileTypeVal);

		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNameVal+"_"+subAsstCode+fileTypeVal,
				moreSettingsOpt);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName,
				preTxt);
		
		docLibPageTest.VerifyDocLibAccess();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		
		String finalFileName = fileNameVal+"_"+subAsstCode+"_"+subAsstCode+"-"+preTxt+"."+"mp4";
		
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
		
		//myFilesTestObj.verifyTransformedFiles(fileNameVal+"_"+subAsstCode+"-"+preTxt+".mp4");
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}