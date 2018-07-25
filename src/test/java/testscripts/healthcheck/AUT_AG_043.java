package testscripts.healthcheck;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
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

public class AUT_AG_043 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_02() {
		testParameters
				.setCurrentTestDescription("8	Verify the warning message is displayed if user does not provide the value for Title field and verify if user is not able to upload without providing the title."
						+ "9	Verify if user provides value with spaces or other unsupported characters for the Reference ID field then the user is returned to the Upload File section and an alert should be displayed as Reference ID cannot contain <problem character>."
						+ "10	Verify if the Reference ID already exists within the database, the user is returned to the Upload File section and an alert appears: This Reference ID already exists. Please use a unique Reference ID.");

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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String title = dataTable.getData("Media_Transform", "ProfDesc");
		String referenceid = dataTable.getData("Media_Transform", "macCode");
		
		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// From the site Type dropdown select 'Collaboration Site '.
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		 //String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(sourceSiteName);

		// Navigate to document library
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();

		// upload video files and verify supported format for MOV file
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, fileName);
	
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, alfrescoAVTPage.relatedFilesTabXpath);
			System.out.println(UIHelper.findAnElementbyXpath(driver, alfrescoAVTPage.relatedFilesTabXpath)
					.getAttribute("disabled").isEmpty());
			if(UIHelper.findAnElementbyXpath(driver, alfrescoAVTPage.relatedFilesTabXpath)
					.getAttribute("disabled").contains("")){
				UIHelper.highlightElement(driver, alfrescoAVTPage.relatedFilesTabXpath);
				report.updateTestLog("Verify ratated tab is disabled ",
						"Related file tab is disabled for audio", Status.PASS);
			}else{
				report.updateTestLog("Verify rleated tab is disabled ",
						"Related file tab is not disabled for audio", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Verify related tab is disabled ",
					"Related file tab is not disabled for audio", Status.FAIL);
		}
		
		//alfrescoAVTPage.clickOnSaveAndUpload();
		
//		input Title
		String autotitle = alfrescoAVTPage.inputTitle(title);
		
//		input invalid ReferenceId
		String autoref = alfrescoAVTPage.inputReferenceID(referenceid);
		alfrescoAVTPage.clickOnSaveAndUpload();
		

		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(autotitle);
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
		alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyPreviewTab();
	

	}

	@Override
	public void tearDown() {

	}
}
