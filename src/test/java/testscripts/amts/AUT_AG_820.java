package testscripts.amts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_820 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_032() {
		testParameters
				.setCurrentTestDescription(
			
			"ALFDEPLOY-2811_Verify the fields of Create Audio Profile"
			+"ALFDEPLOY-2811_Verify the error message when mandatory fields are left blank in Create audio profile screen"
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile without macro code"
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile without macro code and sub asset code"
			+"<b>ALFDEPLOY-2811_Verify the error message upon creating the duplicate audio profile"						
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile with numeric characters and its transformation profile summary"						
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile with alphabetic  characters and its transformation profile summary"						
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile with alphanumeric characters and its transformation profile summary"						
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile with 255 characters"						
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile with sub asset code field filled with only dashes"						
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile with sub asset code field filled with dashes in the middle"						
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile with sub asset code field filled with dashes at the end"
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile with sub asset code field filled with dashes at the start"
			+"<b>ALFDEPLOY-2811_Verify the creation of audio profile with the existing video or image profile"
			
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
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		DateUtil date =  new DateUtil();
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(	scriptHelper);
		String username = dataTable.getData("General_Data", "Username");
		String[] audioNames = dataTable.getData("MyFiles", "CreateFolder").split(",");
		
			
				mediaTransPage.navigateToMediaTransPage();
				mediaTransPage.deleteProfileFrmMediaTransPg("AutoProfile");
				UIHelper.waitFor(driver);
				mediaTransPage.clickOnCreateaudioProfBtn();		
				List<WebElement> audio= driver.findElements(By.xpath(mediaTransPage.audioProfNames));	
				int i=0;
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the fields of Create Audio Profile",
						Status.DONE);
				for(WebElement audiosingle:audio){
					String AudioNames= audiosingle.getText();
				
					if(AudioNames.trim().equalsIgnoreCase(audioNames[i].trim())){
						report.updateTestLog("Audio Profile", "Create Audio Profile, Field <br>"+AudioNames+" is appeared",
								Status.PASS);
					}else{
						report.updateTestLog("Audio Profile", "Create Audio Profile, Field <br>"+AudioNames+" is not appeared",
								Status.FAIL);
					}
					i++;
				}
				
						report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the error message when mandatory fields are left blank in Create audio profile screen",
						Status.DONE);
				mediaTransPage.enterAudioProfileDetails("", "", "", "");
				mediaTransPage.SubmitAddTransformationrules("TransformationDisabled");
				mediaTransPage.enterAudioProfileDetails("", "", "12ABC", "123SUB");
				mediaTransPage.SubmitAddTransformationrules("TransformationDisabled");
				
				
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the error message when sub asset code is left blank in Create audio profile screen",
						Status.DONE);
				mediaTransPage.enterAudioProfileDetails("AutoProfile", "ProfileDescription", "12ABC", "");
				mediaTransPage.SubmitAddTransformationrules("SubAsset");
				
				
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile without macro code",
						Status.DONE);
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile without macro code and sub asset code",
						Status.DONE);
				mediaTransPage.enterAudioProfileDetails("AutoProfile", "ProfileDescription", "", "");
				mediaTransPage.SubmitAddTransformationrules("AddTransformation");
				mediaTransPage.getvaluesofaudioprofiletransformdetails("Audio format:Mp3,AAC;Audio sample rate:32000 Hz,44100 Hz,48000 Hz;Channels:Mono,Stereo;Audio Bitrate(KBPS):Default,16,20,24,28,32,40,48,56,64,80,96,112,128,160,192,224,256,288,320,352,384,416,448;");
				mediaTransPage.enteraudioSettingsDetails("Audio format:Mp3,Channels:Mono,Audio sample rate:32000 Hz,Audio Bitrate(KBPS):24");
				UIHelper.click(driver, mediaTransPage.vdoSaveBtnXpath);
				UIHelper.click(driver, mediaTransPage.OkbuttonXpath);
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify user can sort the profiles based on Profile "
						+ "name,Profile description,Profile creation date,Profile type,Profile Author,Profile Actions"
						+ " in ascending and descending order",
						Status.DONE);	
				
				mediaTransPage.navigateToMediaTransPage();
				mediaTransPage.CheckSortMediatransformation("Profile Description");
				mediaTransPage.CheckSortMediatransformation("Profile Creation Date");
				mediaTransPage.CheckSortMediatransformation("Profile Type");
				mediaTransPage.CheckSortMediatransformation("Profile Author");
				mediaTransPage.CheckSortMediatransformation("Profile Actions");
			
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the error message upon creating the duplicate audio profile",
						Status.DONE);
				mediaTransPage.navigateToMediaTransPage();	
				mediaTransPage.clickOnCreateaudioProfBtn();	
				mediaTransPage.enterAudioProfileDetails("AutoProfile", "ProfileDescription", "", "12SUB");
				mediaTransPage.SubmitAddTransformationrules("Duplicate");
				
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile with numeric characters and its transformation profile summary",
						Status.DONE);
				mediaTransPage.CreationofAudioProfile("9962945983","9962945983", "9962945", "9962945",
						"Audio format:Mp3,Channels:Stereo,Audio sample rate:32000 Hz,Audio Bitrate(KBPS):224",username);
				mediaTransPage.deleteProfileFrmMediaTransPg("9962945983");
				
				
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile with alphabetic  characters and its transformation profile summary",
						Status.DONE);
				mediaTransPage.CreationofAudioProfile("AutoAudio", "AutoAudio", "Audo", "Audo","Audio format:AAC,Cha"
						+ "nnels:Mono,Audio sample rate:32000 Hz,Audio Bitrate(KBPS):32",username);
				mediaTransPage.deleteProfileFrmMediaTransPg("AutoAudio");
				
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile with alphanumeric characters and its transformation profile summary",
						Status.DONE);
				mediaTransPage.CreationofAudioProfile("Auto9962945983","Auto9962945983", "Auto9962945", "Auto945",
						"Audio format:AAC",username);
				mediaTransPage.deleteProfileFrmMediaTransPg("Auto9962945983");
				
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile with 255 characters",
						Status.DONE);
				String chara255 = "dasdasdasdasdasdasdasdasdasdasdasdasdsadsadkjkjkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljadkasjdkasdjaskljdkaskldjaskljdkasdasdasdasdasdasdasdkjkjkljfkafjieowfurfhasjdfhaduoehfweufhasjdfhwefuasjdfajklfsdlfsdjasdasd";
				mediaTransPage.CreationofAudioProfile(chara255,"as", "0A","2S",
						"Audio format:AAC,Channels:Mono,Audio sample rate:32000 Hz,Audio Bitrate(KBPS):32",username);
				mediaTransPage.deleteProfileFrmMediaTransPg(chara255);

						
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile with sub asset code field filled with only dashes",
						Status.DONE);	
				
				mediaTransPage.CreationofAudioProfile("Audiosubcode","Audiosubcode", "123S", "----",
						"Audio format:Mp3,Channels:Mono,Audio sample rate:32000 Hz,Audio Bitrate(KBPS):24",username);
				mediaTransPage.deleteProfileFrmMediaTransPg("Audiosubcode");				
			
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile with sub asset code field filled with dashes in the middle",
						Status.DONE);				
				
				mediaTransPage.CreationofAudioProfile("Audiosubcode","Audiosubcode", "123S", "sub---sdjfhkj",
						"Audio format:AAC,Channels:Stereo,Audio sample rate:32000 Hz,Audio Bitrate(KBPS):112",username);
				mediaTransPage.deleteProfileFrmMediaTransPg("Audiosubcode");				
							
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile with sub asset code field filled with dashes at the end",
						Status.DONE);	
				
				mediaTransPage.CreationofAudioProfile("Audiosubcode","Audiosubcode", "123S", "sdfsdjfhkj--",
						"Audio format:Mp3,Channels:Stereo,Audio sample rate:48000 Hz,Audio Bitrate(KBPS):128",username);
							
												
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile with sub asset code field filled with dashes at the start",
						Status.DONE);	
				mediaTransPage.navigateToMediaTransPage();
				mediaTransPage.deleteProfileFrmMediaTransPg("Audiosubcode");
				mediaTransPage.clickOnCreateaudioProfBtn();
				mediaTransPage.enterAudioProfileDetails("Audiosubcode", "Audiosubcode", "123S", "--sdfsdjfhkj");
				mediaTransPage.SubmitAddTransformationrules("AddTransformation");
				mediaTransPage.enteraudioSettingsDetails("Audio format:Mp3,Channels:Stereo,Audio sample rate:32000 Hz,Audio Bitrate(KBPS):384");
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTransPage.vdoSaveBtnXpath);
				UIHelper.click(driver, mediaTransPage.vdoSaveBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTransPage.OkbuttonXpath);
				UIHelper.click(driver, mediaTransPage.OkbuttonXpath);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
				String dateutc7;
				String utcdate7 = date.getUTCformatDateandTime();
				dateutc7=utcdate7.substring(0, 9);
				dateutc7=dateutc7.concat(utcdate7.substring(19, 28));
				//System.out.println(dateutc7);
				mediaTransPage.getprofilesummarydetailsFrmMediaTransPg("Audiosubcode","Audiosubcode,"+dateutc7+",AUDIO,"+username+",Profile Action");
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify user can search the existing audio profile via search text box in Media transform page",
						Status.DONE);
				mediaTransPage.navigateToMediaTransPage();
				mediaTransPage.searchProfile("Audiosubcode");
				Boolean flag = mediaTransPage.isSearchWorking("Audiosubcode","Profile Description");
				mediaTransPage.searchProfile("AUDIO");
				System.out.println(flag);
				Boolean flag1= mediaTransPage.isSearchWorking("AUDIO","Profile Type");
				mediaTransPage.searchProfile(username);
				Boolean flag2= mediaTransPage.isSearchWorking(username,"Profile Author");
				mediaTransPage.searchProfile("Profile Action");
				Boolean flag3= mediaTransPage.isSearchWorking("Profile Action","Profile Actions");
				//String utcdate7 = "Wed Feb 28 05:54:54 UTC 2018";
				mediaTransPage.searchProfile(utcdate7.substring(0, 9));
				Boolean flag4= mediaTransPage.isSearchWorking(utcdate7.substring(0, 9),"Profile Creation Date");
			//	System.out.println(utcdate7.substring(0, 9));//15
				//System.out.println(flag+"+"+flag1+"+"+flag2+"+"+flag4+"+"+flag3);
				if(flag && flag1 && flag2 && flag4){
					report.updateTestLog("Testcase name", "<b> user can search the existing audio profile via search text box in Media transform page",
							Status.PASS);
				}else{
					report.updateTestLog("Testcase name", "<b> user can't search the existing audio profile via search text box in Media transform page",
							Status.FAIL);
				}
		
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify user can edit the existing audio profile",
						Status.DONE);	
				
				mediaTransPage.editProfileFrmMediaTransPg("Audiosubcode");
				mediaTransPage.enteraudioSettingsDetails("Audio format:AAC,Channels:Mono,Audio sample rate:48000 Hz,Audio Bitrate(KBPS):128");
				UIHelper.click(driver, mediaTransPage.vdoSaveBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTransPage.OkbuttonXpath);
				UIHelper.click(driver, mediaTransPage.OkbuttonXpath);
				mediaTransPage.editProfileFrmMediaTransPg("Audiosubcode");
				UIHelper.waitFor(driver);
				mediaTransPage.gettransformationprofilesummarydetails_Audio("targetAudioSample","48000");
				mediaTransPage.gettransformationprofilesummarydetails_Audio("targetChannel","1");
				mediaTransPage.gettransformationprofilesummarydetails_Audio("targetAudioBitrate","128");
				mediaTransPage.gettransformationprofilesummarydetails_Audio("targetAudioFormat","audio/aac");
				mediaTransPage.navigateToMediaTransPage();
				mediaTransPage.deleteProfileFrmMediaTransPg("Audiosubcode");
				
				
				
				report.updateTestLog("Testcase name", "<b>ALFDEPLOY-2811_Verify the creation of audio profile with the existing video or image profile",
						Status.DONE);				
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
				
				mediaTransPage.navigateToMediaTransPage();
				mediaTransPage.deleteProfileFrmMediaTransPg(profName);
				UIHelper.waitFor(driver);
				mediaTransPage.clickOnCreateImageProfBtn();
				mediaTransPage.commonMethodForEnterImageProfDetails(profName, profDesc,
						macCode, subAsstCode, filePathForUploadFile, fileNameForUpload);
				if (mediaTransPage.clickOnAddImageProfBtn()) {

					report.updateTestLog("Verify Image profile details",
							"Image Profile details displayed successfully", Status.PASS);
					UIHelper.waitFor(driver);
					mediaTransPage.selectImgFileFormatType(imageType);
					mediaTransPage.clickSaveBtnImgProf();

				} else {
					report.updateTestLog("Verify Image Profile creation",
							"Failed to create Image profile", Status.FAIL);
				}
					
				mediaTransPage.navigateToMediaTransPage();
				mediaTransPage.clickOnCreateaudioProfBtn();
				mediaTransPage.enterAudioProfileDetails(profName, profDesc, macCode, subAsstCode);
				mediaTransPage.SubmitAddTransformationrules("AddTransformation");
				mediaTransPage.enteraudioSettingsDetails("Audio format:AAC,Channels:Stereo,Audio sample rate:32000 Hz,Audio Bitrate(KBPS):384");
				UIHelper.click(driver, mediaTransPage.vdoSaveBtnXpath);
				UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTransPage.OkbuttonXpath);
				UIHelper.click(driver, mediaTransPage.OkbuttonXpath);
				mediaTransPage.deleteProfileFrmMediaTransPg(profName);
				
				
	}

	@Override
	public void tearDown() {

	}

}