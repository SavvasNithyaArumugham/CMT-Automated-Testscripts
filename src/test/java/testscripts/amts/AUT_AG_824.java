package testscripts.amts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class AUT_AG_824 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_824()
	{
		testParameters.setCurrentTestDescription(
							
		"1_ALFDEPLOY-2811_Verify the successful audio transformation of mp3 to mp3,aac"+
		"2_ALFDEPLOY-2811_Verify the successful audio transformation of m4v to mp3,aac"+
		"3_ALFDEPLOY-2811_Verify the successful audio transformation of webm to mp3,aac"+
		"4_ALFDEPLOY-2811_Verify the successful audio transformation of wav to mp3,aac"+
		"5_ALFDEPLOY-2811_Verify the successful audio transformation of ogg to mp3,aac"+
		"6_ALFDEPLOY-2811_Verify the successful audio transformation of aac to mp3,aac"
	
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
		try{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(	scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage =  new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);

		
		
		String fileNme[] = dataTable.getData("MyFiles", "FileName").split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites","SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails[] = dataTable.getData("MyFiles", "CreateFolder").split(";");
		String ParentFolderName = dataTable.getData("MyFiles", "Version");
		String TargetFolderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String watchFldr = dataTable.getData("Media_Transform", "WatchFolder");
		String dashletName = dataTable.getData("Home", "DashletName");
		String options = dataTable.getData("MyFiles", "MoreSettingsOption");
		String ProfiledetailsMp3[] = dataTable.getData("Media_Transform", "verifyVdoSettings").split(";");
		String Profiledetailsaac[] = dataTable.getData("Media_Transform", "VdoSettings").split(";");
		String Profiledetails[] = dataTable.getData("Media_Transform", "Max_BitRate").split(";");
		String mp3files[] = dataTable.getData("MyFiles", "RelationshipName").split(",");
		String aacfiles[] = dataTable.getData("MyFiles", "Sort Options").split(",");
		// Creation of the MP3 Audio Profile
		functionalLibrary.loginAsValidUser(signOnPage);
		
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
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails[0]);
		myFiles.createFolder(folderDetails[1]);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(ParentFolderName);
		
		for(String file:fileNme){			
		myFiles.uploadFile(filePath, file);	
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(ParentFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(ParentFolderName,	docActionVal);
		
		mediaTransPage.applyTransformationToTargetFolder(ProfiledetailsMp3[0], ParentFolderName, TargetFolderName, preTxt, sourceSiteName);	
		myFiles.openCreatedFolder(TargetFolderName);
	
		ArrayList<String> actual= new ArrayList<String>();
		ArrayList<String> expected= new ArrayList<String>();
		
		List<WebElement> li;
		
		for(int i=0;i<=5;i++) {
		//do{
			li = driver.findElements(By.xpath(mediaTransPage.documentsfilesxpath));
			UIHelper.waitFor(driver);
			UIHelper.pageRefresh(driver);
			System.out.println(li.size()+"inside");
		}
		
		//while(li.size()!=6);
		
		UIHelper.waitFor(driver);
		List<WebElement> lii = driver.findElements(By.xpath(mediaTransPage.documentsfilesxpath));
		System.out.println(lii.size());
		String a=lii.get(2).getText();
		System.out.println(a);
		for(int i=0;i<=lii.size()-1;i++){
		String lil= lii.get(i).getText();
		System.out.println(lil);
		
		actual.add(lil);
		
		
		}
		
		for(String value:mp3files){
			expected.add(value);
		}
		
		Boolean flag = UIHelper.compareTwoSimilarLists(actual, expected);	
		System.out.println(flag);
		
		if(flag){
			report.updateTestLog(" Audio Transformation:", "Audio transformation is successully converted to "+ Arrays.toString(mp3files), Status.PASS);
		}else{
			report.updateTestLog(" Audio Transformation:", "Audio transformation is failed converted to "+ Arrays.toString(mp3files), Status.FAIL);
		}
			
		//aac
		docLibPge.deleteAllFilesAndFolders();
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(ParentFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(ParentFolderName,	docActionVal);
		
		mediaTransPage.applyTransformationToTargetFolder(Profiledetailsaac[0], ParentFolderName, TargetFolderName, preTxt, sourceSiteName);	
		myFiles.openCreatedFolder(TargetFolderName);
	
		ArrayList<String> actualaac= new ArrayList<String>();
		ArrayList<String> expectedaac= new ArrayList<String>();
		
		List<WebElement> liaac;
		for(int i=0;i<=5;i++) {
			liaac = driver.findElements(By.xpath(mediaTransPage.documentsfilesxpath));
			UIHelper.waitFor(driver);
			UIHelper.pageRefresh(driver);
			System.out.println(liaac.size()+"inside");
		}
		
		UIHelper.waitFor(driver);
		List<WebElement> liiaac = driver.findElements(By.xpath(mediaTransPage.documentsfilesxpath));
		System.out.println(liiaac.size());
		String a1=liiaac.get(2).getText();
		System.out.println(a1);
		for(int i=0;i<=liiaac.size()-1;i++){
		String lil= liiaac.get(i).getText();
		System.out.println(lil);
		
		actualaac.add(lil);
		
		
		}
		
		for(String value:aacfiles){
			expectedaac.add(value);
			System.out.println(value);
			
		}
		
		Boolean flagaac = UIHelper.compareTwoSimilarLists(actualaac, expectedaac);	
		System.out.println(flagaac);
	
		
		if(flagaac)
		{
			report.updateTestLog(" Audio Transformation:", "Audio transformation is successully converted to "+ Arrays.toString(aacfiles), Status.PASS);
		}else{
			report.updateTestLog(" Audio Transformation:", "Audio transformation is failed converted to "+ Arrays.toString(aacfiles), Status.FAIL);
		}
		
		docLibPge.deleteAllFilesAndFolders();
		
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog(" AUT_AG_824 Test case Status", "AUT_AG_824  Testcase is failed", Status.FAIL);							
			
		}

	}

	@Override
	public void tearDown() {
		
	}

}
