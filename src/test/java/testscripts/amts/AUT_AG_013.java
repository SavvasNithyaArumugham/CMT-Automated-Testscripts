package testscripts.amts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_013 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_004() {
		testParameters
				.setCurrentTestDescription("Verify brightness slider widget displays 00 value properly in Transformation profile wizard of image profile.<br>"
+"Verify sharpness slider widget displays 00 value properly in Transformation profile wizard of image profile.<br>"
+"Verify blur slider widget displays 00 value properly in Transformation profile wizard of image profile.<br>"
+"Verify the presence of blur slider setting option is in multiples of 10 in Transformation Profile Wizard while creating image profile"
+"ALFDEPLOY-4277_Verify the presence of brightness accordion in image transformation profile section"
+"ALFDEPLOY-4277_Verify the UI of brightness accordion in image transformation profile having values-100 to +100"
+"ALFDEPLOY-4252_Verify the presence of contract accordion in image transformation profile section"
+"ALFDEPLOY-4252_Verify the UI of contract accordion in image transformation profile having values-100 to +100"
					
						
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
		
		
	try {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		ArrayList<Integer> sliderdata = new ArrayList<Integer>();
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String filePathForUploadFile = dataTable.getData("Media_Transform",
				"filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform",
				"fileName");

		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.commonMethodForEnterImageProfDetails(profName, profDesc,
				macCode, subAsstCode, filePathForUploadFile, fileNameForUpload);
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Image profile details",
					"Image Profile details displayed successfully", Status.PASS);
			
		mediaTransPage.clickprofiletypeopen("Flip");
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, mediaTransPage.fliphorizontal);
			if(UIHelper.checkForAnElementbyXpath(driver, mediaTransPage.fliphorizontal)){
				UIHelper.highlightElement(driver, mediaTransPage.fliphorizontal);
				report.updateTestLog("Verify flip canvas horizontally option in flip option widget",
						"flip canvas horizontally option in flip option widget", Status.PASS);
			}else{
				report.updateTestLog("Verify flip canvas horizontally option in flip option widget",
						"flip canvas horizontally option in flip option widget is not displayed", Status.FAIL);
			}
		
			mediaTransPage.clickprofiletypeopen("Brightness");
			sliderdata = mediaTransPage.getsliderdata("Brightness");
		
			
			if (sliderdata.contains(0)){
				
				report.updateTestLog("Verify brightness slider widget displays 0 value",
						"Brightness slider widget displays 0 value is displayed properly", Status.PASS);
				
			}else{
				report.updateTestLog("Verify brightness slider widget displays 0 value",
						"Brightness slider widget doesnt display 0 value properly", Status.FAIL);
			}
			

			if (sliderdata.contains(-100)&&sliderdata.contains(100)&&sliderdata.contains(-80)&&sliderdata.contains(80)&&sliderdata.contains(-60)&&sliderdata.contains(60)&&sliderdata.contains(-40)&&sliderdata.contains(40)&&sliderdata.contains(-20)&&sliderdata.contains(20)){
				
				report.updateTestLog("Verify brightness slider widget displays -100 to +100 value",
						"Brightness slider widget displays-100 to +100  value is displayed properly", Status.PASS);
				
			}else{
				report.updateTestLog("Verify brightness slider widget displays -100 to +100  value",
						"Brightness slider widget doesnt display -100 to +100  value properly", Status.FAIL);
			}
			
			sliderdata.clear();
			
			mediaTransPage.clickprofiletypeopen("Contrast");
			sliderdata = mediaTransPage.getsliderdata("Contrast");
		
			
			if (sliderdata.contains(0)){
				
				report.updateTestLog("Verify Contrast slider widget displays 0 value",
						"Contrast slider widget displays 0 value is displayed properly", Status.PASS);
				
			}else{
				report.updateTestLog("Verify Contrast slider widget displays 0 value",
						"Contrast slider widget doesnt display 0 value properly", Status.FAIL);
			}
			

			if (sliderdata.contains(-100)&&sliderdata.contains(100)&&sliderdata.contains(-80)&&sliderdata.contains(80)&&sliderdata.contains(-60)&&sliderdata.contains(60)&&sliderdata.contains(-40)&&sliderdata.contains(40)&&sliderdata.contains(-20)&&sliderdata.contains(20)){
				
				report.updateTestLog("Verify Contrast slider widget displays -100 to  +100 value",
						"Contrast slider widget displays-100 to +100  value is displayed properly", Status.PASS);
				
			}else{
				report.updateTestLog("Verify Contrast slider widget displays -100 to +100  value",
						"Contrast slider widget doesnt display -100 to +100  value properly", Status.FAIL);
			}
			
			sliderdata.clear();
			
			
			mediaTransPage.clickprofiletypeopen("Sharpness");
			sliderdata = mediaTransPage.getsliderdata("Sharpness");

			if (sliderdata.contains(0)){
				
				report.updateTestLog("Verify Sharpness slider widget displays 0 value",
						"Sharpness slider widget displays 0 value is displayed properly", Status.PASS);
				
			}else{
				report.updateTestLog("Verify Sharpness slider widget displays 0 value",
						"Sharpness slider widget doesnt display 0 value properly", Status.FAIL);
			}
			
			sliderdata.clear();
			
		

			
			mediaTransPage.clickprofiletypeopen("Blur");
			sliderdata = mediaTransPage.getsliderdata("Blur");

			if (sliderdata.contains(0)){
				
				report.updateTestLog("Verify Blur slider widget displays 0 value",
						"Blur slider widget displays 0 value is displayed properly", Status.PASS);
				
			}else{
				report.updateTestLog("Verify Blur slider widget displays 0 value",
						"Blur slider widget doesnt display 0 value properly", Status.FAIL);
			}
			
		
			Boolean flag = false;
			for(Integer i : sliderdata){
				

		if(i!=0 ){
				if(i % 10==0 ){
					flag = true;
				}else{
					 flag = false;
					 break;
				}
		}
			}
			
			if(flag){
				report.updateTestLog("Verify blur slider setting option is in multiples of 10",
						"blur slider setting option is in multiples of 10. Slider Value :" +sliderdata, Status.PASS);
			}else{
				report.updateTestLog("Verify blur slider setting option is in multiples of 10",
						"blur slider setting option is not in multiples of 10 Slider Value :" +sliderdata, Status.FAIL);
			}
			
			sliderdata.clear();
			

		} else {
			report.updateTestLog("Verify Image profile details",
					"Image Profile creation failed", Status.FAIL);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		report.updateTestLog("Verify Image profile details",
				"Image Profile creation failed", Status.FAIL);
	}
	
	}

	@Override
	public void tearDown() {

	}

}