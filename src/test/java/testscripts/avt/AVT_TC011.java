package testscripts.avt;

import org.testng.annotations.Test;

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

public class AVT_TC011 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_011() {
		testParameters
				.setCurrentTestDescription("44	Verify if the user navigates away from the page during upload, it will crash the upload"
						+ ""
						+ "");

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
		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
//		String sourceSiteName = dataTable.getData("Sites", "SiteName");


		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

// 		From the site Type dropdown select 'Collaboration Site '.
		homePageObj.navigateToSitesTab();
//		sitesPage.createSite(sourceSiteName, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		
//		create streaming media
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MOV.mov");
		String movTitle=alfrescoAVTPage.inputTitle("MOV");
		alfrescoAVTPage.inputReferenceIDwithoutRandomNumber(movTitle);
		alfrescoAVTPage.inputDescription(movTitle);
		alfrescoAVTPage.inpitKeyword(movTitle);
		alfrescoAVTPage.inputPlayListID(movTitle);
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "PNG.png");
		alfrescoAVTPage.uploadAndVerifyValidChapteringXML(filePath, "ChapteringXML.xml");
		alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt",	"English");
		alfrescoAVTPage.addCaptionsFile(filePath, "stat13t_sl_10_03_es.srt", "Spanish");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTT.vtt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "VTTS.vtt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		driver.navigate().back();//crashing the streaming while its creating 
		driver.navigate().refresh();
		
		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		
//		verify streaming content	

		ArrayList<String> streamingContent = new ArrayList<String>();
		streamingContent=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		if (streamingContent.contains(movTitle)) {
			report.updateTestLog(
					"crashing the streaming created the streaming folder ",
					"unexcepted", Status.FAIL);

		}
		else {
			report.updateTestLog(
					"crashing the streaming did not created the streaming folder ",
					"excepted", Status.PASS);
			
		}
	}

	@Override
	public void tearDown() {

	}
}
