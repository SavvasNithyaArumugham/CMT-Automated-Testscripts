package testscripts.avt;

import org.testng.annotations.Test;

import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
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

public class AVT_TC014P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_02() {
		testParameters
		.setCurrentTestDescription("Verify user(Manager/Coordinator/Collaborator/Contributor) is able to view Streaming media option under create when user is member of Media Developers group <br>"
				+ "Verify user(Manager/Coordinator/Collaborator/Contributor) is able to view Streaming media option for JSON file created by self when user is member of Media Developers group <br>"
				+ "Verify user(Manager/Coordinator/Collaborator/Contributor) is not able to view Streaming media option under create when user is not member of Media Developers group<br>"
				+ "Verify user(Manager/Coordinator/Collaborator) is able to view Streaming media option for JSON file created by others when user is not member of Media Developers group <br>"
				+ "Part 2 : User with Collaborator role able to click Streaming Media for json file");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String title = dataTable.getData("Media_Transform", "ProfDesc");
		String referenceid = dataTable.getData("Media_Transform", "macCode");
		
		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// From the site Type dropdown select 'Collaboration Site '.

		 String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);

		// Navigate to document library
		sitesPage.enterIntoDocumentLibrary();

		// upload video files and verify supported format for MOV file
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, fileName);
	
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, alfrescoAVTPage.disabledrelatedXpath);
			if(UIHelper.checkForAnElementbyXpath(driver, alfrescoAVTPage.disabledrelatedXpath)){
				UIHelper.highlightElement(driver, alfrescoAVTPage.disabledrelatedXpath);
				report.updateTestLog("Verify related tab is disabled ",
						"Related file tab is disabled for audio", Status.PASS);
			}else{
				report.updateTestLog("Verify related tab is disabled ",
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
	/*	alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
		alfrescoAVTPage.verifyPreviewTab();*/
		
	}

	@Override
	public void tearDown() {

	}
}
