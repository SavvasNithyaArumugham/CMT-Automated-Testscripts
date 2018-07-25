package testscripts.amts;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_821 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_821()
	{
		testParameters.setCurrentTestDescription(
							
		"1_ALFDEPLOY-2811_Verify the successful audio transformation of mp3 to mp3,aac"+
		"2_ALFDEPLOY-2811_Verify the successful audio transformation of m4v to mp3,aac"+
		"3_ALFDEPLOY-2811_Verify the successful audio transformation of webm to mp3,aac"+
		"4_ALFDEPLOY-2811_Verify the successful audio transformation of wav to mp3,aac"+
		"5_ALFDEPLOY-2811_Verify the successful audio transformation of ogg to mp3,aac"+
		"6_ALFDEPLOY-2811_Verify the successful audio transformation of aac to mp3,aac"+
		"ALFDEPLOY-4109_Verify the target audio file is updated with new version "
		+ "and content when target audio file resides in the"
		+ " target location upon applying audio transformation "	
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
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(	scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage =  new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);

		try{
		
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
	
		// Creation of the MP3 Audio Profile
		UIHelper.waitFor(driver);
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
		
		// Creation of the AAC Audio Profile
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(Profiledetailsaac[0]);
		mediaTransPage.clickOnCreateaudioProfBtn();
		mediaTransPage.enterAudioProfileDetails(Profiledetailsaac[0], Profiledetailsaac[1],Profiledetailsaac[2],Profiledetailsaac[3]);
		mediaTransPage.SubmitAddTransformationrules(options);
		mediaTransPage.enteraudioSettingsDetails(Profiledetailsaac[4]);
		UIHelper.click(driver, mediaTransPage.vdoSaveBtnXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTransPage.OkbuttonXpath);
		UIHelper.click(driver, mediaTransPage.OkbuttonXpath);
		
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesDashboardPage.navigateToSiteDashboard();
		
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashletName))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(ParentFolderName);
		
		for(String file:fileNme){			
		myFiles.uploadFile(filePath, file);	
		
		for(String prof:Profiledetails){
			
				String[] profile=prof.split(",");
			
			String[] fileNamePart = file.split(Pattern.quote("."));
			sitesPage.clickOnEditProperties(file);		
			file = fileNamePart[0]+"_"+profile[1]+"_"+"."+fileNamePart[1];
			docLibPg.documentRename(file);
			System.out.println("rename:"+file);
			sitesPage.clickOnMoreSetting(file);
			docLibPg.commonMethodForClickOnMoreSettingsOption(file,	docActionVal);
			mediaTransPage.watchFldrRadio(watchFldr);
			mediaTransPage.selectProfileFrmListAndApplyTransformation(profile[0],preTxt);
			myFiles.openCreatedFolder(ParentFolderName);
			String[] file1 = file.split(Pattern.quote("."));
			String fileName = file1[0] + "_-" + preTxt + "."+ profile[2];
			String fileNamewithsubcode = file1[0] + "_" + profile[1] + "-" + preTxt + "."+ profile[2];
			System.out.println("file with subcode:"+fileNamewithsubcode);
			System.out.println("file:"+fileName);
			if (mediaTransPage.isTransferredFileIsAvailable(fileName)||mediaTransPage.isTransferredFileIsAvailable(fileNamewithsubcode)) {
				report.updateTestLog(
						"Verify Image profile applied Successfully to the File "
								+ fileNamewithsubcode, "Image profile applied successfully.",Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Image profile applied Successfully to the File "
								+ fileNamewithsubcode, "Image profile is not applied."
								+ "<br><b>File  Name : </br>", Status.FAIL);
			}

			sitesPage.clickOnMoreSetting(file);
			docLibPg.commonMethodForClickOnMoreSettingsOption(file,
					docActionVal);
			mediaTransPage.selectProfileFrmListAndApplyTransformation(profile[0],
					preTxt);
			
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(ParentFolderName);
			UIHelper.waitFor(driver);
			
			
			Boolean flag = sitesPage.Checkdocument(fileName);
			Boolean flag1 = sitesPage.Checkdocument(fileNamewithsubcode);	
			System.out.println(flag+" "+flag1);
			if(flag==true||flag1==true){
				
				if(flag==true){
					myFiles.openAFile(fileName);
				}else{
					myFiles.openAFile(fileNamewithsubcode);
				}
				
			}
			
			String version1 = dataTable.getData("MyFiles", "BrowseActionName");
			System.out.println(version1);
			
			mediaTransPage.checkVersionOfTransformedFile(version1);
			UIHelper.waitFor(driver);
			
			sitesDashboardPage.navigateToSiteDashboard();
			String expectedHeaderNames = dataTable.getData("Site_Dashboard", "AsyncDashletHeaderNames");
			sitesDashboardPageTest.verifyHeaderNamesInAsyncDashlet(expectedHeaderNames);
			
			String expectedQueuedNamesForEPSJob = dataTable.getData("Site_Dashboard", "QueuedStatusForEPSJob");
			sitesDashboardPageTest.verifyPublishedFileQueuStatusInAsyncDashlet(expectedQueuedNamesForEPSJob,
					ParentFolderName+"/"+file);
						
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(ParentFolderName);
			
			}
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void tearDown() {
		
	}

}
