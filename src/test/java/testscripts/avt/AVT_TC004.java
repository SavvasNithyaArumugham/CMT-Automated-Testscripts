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

public class AVT_TC004 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_004() {
		testParameters
				.setCurrentTestDescription("11	Verify that the Thumbnail field supports .jpg, .jpeg, .gif, .png, .bmp extensions without any alert and user is getting warning message for unsupported file types"
						+ "<br>12	Verify that the supported file is uploaded for Chaptering XML field without any alert and user is getting warning message for unsupported file types"
						+ "<br>16	Verify user is able to cancel the metadata details entered for upload files section by clicking on Cancel button"
						+ "<br>17	Verify user is able to cancel the details entered for Related files section by clicking on Cancel button"
						+ "<br>15	Verify user is able to upload the file when the selected file content is corrupted.");

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

		// Thumbnail Validation | corrupted video upload
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		String givenName=alfrescoAVTPage.inputTitle("RelatedFilesValidation");
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "CORR.mp4");// corrupted file upload
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "JPG.jpg");
		alfrescoAVTPage.clickOnRemoveThumbnail();
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "GIF.gif");
		alfrescoAVTPage.clickOnRemoveThumbnail();
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "PNG.png");
		alfrescoAVTPage.clickOnRemoveThumbnail();
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "BMP.bmp");
		alfrescoAVTPage.clickOnRemoveThumbnail();
		alfrescoAVTPage.uploadAndVerifyValidThumbnailNegative(filePath, "TIF.tif");
		alfrescoAVTPage.clickOnRemoveThumbnail();

		// chaptring XML validation
		alfrescoAVTPage.uploadAndVerifyValidChapteringXMLNegative(filePath, "BMP.bmp");
		alfrescoAVTPage.clickOnRemoveChapter();
		alfrescoAVTPage.uploadAndVerifyValidChapteringXML(filePath, "ChapteringXML.xml");
		alfrescoAVTPage.clickOnRemoveChapter();
		
//		validate cancel the upload file and related files metadata
		alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "JPG.jpg");
		alfrescoAVTPage.uploadAndVerifyValidChapteringXML(filePath, "ChapteringXML.xml");
		alfrescoAVTPage.clickOnCancle();	
		ArrayList<String> FilesAndFoldersListFromDocumentLibrary= alfrescoAVTPage.getFilesAndFoldersListFromDocumentLibrary();
		alfrescoAVTPage.verifyStreamingMediaFolderNameAndTitleNegative(givenName,FilesAndFoldersListFromDocumentLibrary);

	}

	@Override
	public void tearDown() {

	}
}
