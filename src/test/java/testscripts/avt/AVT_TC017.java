package testscripts.avt;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC017 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_16() {
		testParameters
				.setCurrentTestDescription(
						
						"ALFDEPLOY-3225_Verify user is able to copy and paste the CITE URL in new browser or tab for MP4 Video."
						+"ALFDEPLOY-3225_Verify user is able to copy and paste the CITE URL in new browser or tab for MOV Video."
						+"ALFDEPLOY-3225_Verify user is able to copy and paste the CITE URL in new browser or tab for M4V Video."
						+"ALFDEPLOY-3225_Verify user is able to copy and paste the CITE URL in new browser or tab for MP3 Audio."	
						
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

		try{
		//AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		AlfrescoHomePage homePageObj= new AlfrescoHomePage(scriptHelper);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String urlpart = dataTable.getData("MyFiles", "CreateFileDetails");
		String foldername = dataTable.getData("MyFiles", "MoreSettingsOption");
		String mp4 = dataTable.getData("MyFiles", "RelationshipName");
		String mp3 = dataTable.getData("MyFiles", "BrowseActionName");
		
		// Login Page
		functionalLibrary.loginAsValidUser(signOnPage);
		UIHelper.waitFor(driver);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		//myFiles.openCreatedFolder("MP44388");
		docLibPg.deleteAllFilesAndFolders();
		
		for(String name:fileName){
			alfrescoAVTPage.clickOnStreamingMedia();
			alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, name);
			String movTitle=alfrescoAVTPage.inputTitle(foldername);
			alfrescoAVTPage.inputReferenceID(movTitle);
			alfrescoAVTPage.clickOnSaveAndUpload();
			alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
			alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
			alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
			alfrescoAVTPage.verifyPreviewTab();	
			String extension[]=foldername.split("\\.");
			System.out.println(extension[1]);
			String url=alfrescoAVTPage.FormatofURL("CITE URL",urlpart,extension[1],mp4,mp3);
			homePageObj.openNewTab(url);
			sitesPage.enterIntoDocumentLibrary();
			docLibPg.deleteAllFilesAndFolders();
		}
		
			
		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		
	}

	@Override
	public void tearDown() {

	}
}
