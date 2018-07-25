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

public class AVT_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_001() {
		testParameters.setCurrentTestDescription("1	Verify user is able to view Streaming media option under Create tab"
				+ "2	Verify create Streaming media screen is cancelled once user click on the cancel button"
				+ "3	Verify the fields in Create Streaming media screen"
				+ "4	Verify the fields in the Related files tab section"
				+ "5	Verify .mov, .m4v, .mp4, are acceptable file extensions for video file and the files should be uploaded successfully without any warning message"
				+ "6	Verify user is getting warning message while uploading unsupported formats video files"
				+ "7	Verify that the Related files tab is enabled for video");
		

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		
//		Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);
		
// 		From the site Type dropdown select 'Collaboration Site '.
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		
//		Navigate to document library
		sitesPage.enterIntoDocumentLibrary();
		
//		click on streaming media
		alfrescoAVTPage.clickOnStreamingMedia();
		
//		verify upload files tab's fields 
		alfrescoAVTPage.verifyUploadFilesTabFields();
		  
//		verify Realted Files tab's fields
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		
//		verify cancel streaming media navigates back to where its started 
		alfrescoAVTPage.clickOnCancle();		
		
//		upload video files and verify supported format for MOV file 
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MOV.mov");
		alfrescoAVTPage.clickOnCancle();
		
//		upload video files and verify supported format for MP4 file 
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MP4.mp4");
		alfrescoAVTPage.clickOnCancle();
		
		
//		upload video files and verify supported format for M4V file 
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "M4V.m4v");
		alfrescoAVTPage.clickOnCancle();
		
//		upload video files and verify supported format for unsupported  file 
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTabNegative(filePath, "WMV.wmv");
		alfrescoAVTPage.clickOnCancle();

		}

	@Override
	public void tearDown() {

	}
}
