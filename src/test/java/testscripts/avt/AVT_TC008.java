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

public class AVT_TC008 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_008() {
		testParameters.setCurrentTestDescription( "24	Verify Reference id is autopopulated if user does not provide any value for the Reference id field" + ""
				+ "34	Verify title is displayed as read only field after the successful creation of the streaming media folder post transcoding" + "" + "");

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

		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// From the site Type dropdown select 'Collaboration Site '.
		homePageObj.navigateToSitesTab();
		String siteName = sitesPage.getCreatedSiteName();
		// String siteName = "AutoAVTRandomSite50517074206";
		sitesPage.openSiteFromRecentSites(siteName);

		
		
//		Verify Reference id is autopopulated if user does not provide any value for the Reference id field		sitesPage.enterIntoDocumentLibrary();
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MOV.mov");
		String movTitle=alfrescoAVTPage.inputTitle("NRIDMOV");
//		alfrescoAVTPage.inputReferenceIDwithoutRandomNumber(movTitle); commented populate reference ID method here 
		alfrescoAVTPage.inputDescription(movTitle);
		alfrescoAVTPage.inpitKeyword(movTitle);
		alfrescoAVTPage.inputPlayListID(movTitle);
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "PNG.png");
		alfrescoAVTPage.uploadAndVerifyValidChapteringXML(filePath, "ChapteringXML.xml");
		alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt",	"English");
		//alfrescoAVTPage.addCaptionsFile(filePath, "stat13t_sl_10_03_es.srt", "Spanish");
		//alfrescoAVTPage.addCaptionsFile(filePath, "VTT.vtt", "English");
		//alfrescoAVTPage.addCaptionsFile(filePath, "VTTS.vtt", "Spanish");
		alfrescoAVTPage.clickOnSaveAndUpload();
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();

		UIHelper.waitFor(driver);UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(movTitle);
		ArrayList<String> listOfFilesForMOV = new ArrayList<String>();
		listOfFilesForMOV=alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();

		
		alfrescoAVTPage.verifyAutopulatedReferenceID(movTitle);
		alfrescoAVTPage.validateReadonlyFieldsInUploadFiles();

	

	
		


	}

	@Override
	public void tearDown() {

	}
}
