package testscripts.proofhq;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_040 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void proofhq_24() {
		testParameters
				.setCurrentTestDescription("To Verify whether system allows the user to Convert the image Jpg file differenet formats like png, tiff, gif, jpeg");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
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
		String filePathForUploadFile = dataTable.getData("Media_Transform",
				"filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform",
				"fileName");

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String preTxt = dataTable.getData("Media_Transform", "Preference");

		String moreSettingsOptions = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String moreSettingsOption1="",moreSettingsOption2="";
		if(moreSettingsOptions.contains(","))
		{
			String splittedMoreSettingOptions[] = moreSettingsOptions.split(",");
			if(splittedMoreSettingOptions!=null && splittedMoreSettingOptions.length>1)
			{
				moreSettingsOption1=splittedMoreSettingOptions[0];
				moreSettingsOption2=splittedMoreSettingOptions[1];
			}
			else
			{
				moreSettingsOption1="Create Proof in ProofHQ";
				moreSettingsOption2="Apply Transformation Profile";
			}
		}
		else
		{
			moreSettingsOption1="Create Proof in ProofHQ";
			moreSettingsOption2="Apply Transformation Profile";
		}
		
		if (imageType.contains(",")) {
			String variousTypes[] = imageType.split(",");
			for (String eachType : variousTypes) {				

				mediaTransPage.navigateToMediaTransPage();
				//mediaTransPage.navigateToProfilePage(profName);
				mediaTransPage.deleteProfileFrmMediaTransPg(profName);
				UIHelper.waitFor(driver);
				mediaTransPage.clickOnCreateImageProfBtn();
				mediaTransPage.commonMethodForEnterImageProfDetails(profName,
						profDesc, macCode, subAsstCode, filePathForUploadFile,
						fileNameForUpload);
				if (mediaTransPage.clickOnAddImageProfBtn()) {

					report.updateTestLog("Verify Image profile details",
							"Image Profile details displayed successfully",
							Status.PASS);
					mediaTransPage.selectImgFileFormatType(eachType);
					mediaTransPage.clickSaveBtnImgProf();

				} else {
					report.updateTestLog("Verify Image Profile creation",
							"Failed to create Image profile", Status.FAIL);
				}

				homePageObj.navigateToSitesTab();
				sitesPage.openSiteFromRecentSites(sourceSiteName);
				sitesPage.enterIntoDocumentLibrary();
				docLibPge.deleteAllFilesAndFolders();	
				
				myFiles.uploadFile(filePath, fileNme);
				myFilesTestObj.verifyUploadedFile(fileNme, "");
				
				sitesPage.clickOnMoreSetting(fileNme);
				sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileNme, moreSettingsOption1);
				docLibPge.commonMethodForClickOnMoreSettingsOption(fileNme, moreSettingsOption1);
				
				String recepients=  dataTable.getData("MyFiles", "Recepients");
				String policy=  dataTable.getData("MyFiles", "ProofHQPolicy");
				sitesPage.addProofHQ(recepients, policy, fileNme);
				
				sitesPage.clickOnMoreSetting(fileNme);
				docLibPge.commonMethodForClickOnMoreSettingsOption(fileNme,
						moreSettingsOption2);
				mediaTransPage.selectProfileFrmListAndApplyTransformation(
						profName, preTxt);
				sitesPage.enterIntoDocumentLibrary();	
				String[] file = fileNme.split(Pattern.quote("."));
				if(eachType.equalsIgnoreCase("jpeg")){
					eachType = "jpg";
				}
				String fileName = file[0] + "_" + subAsstCode + "-" + preTxt
						+ "." + eachType;

				if (mediaTransPage.isTransferredFileIsAvailable(fileName)) {
					report.updateTestLog(
							"Verify Image profile applied Successfully to the File "
									+ fileName,
							"Image profile applied successfully.", Status.PASS);

				} else {
					report.updateTestLog(
							"Verify Image profile applied Successfully to the File "
									+ fileName, "Image profile is not applied."
									+ "<br><b>File  Name : </br>", Status.FAIL);
				}

			}
		}

	}

	@Override
	public void tearDown() {

	}

}
