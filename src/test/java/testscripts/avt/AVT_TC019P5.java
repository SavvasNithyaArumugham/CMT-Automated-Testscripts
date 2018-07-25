package testscripts.avt;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import java.util.ArrayList;
import java.util.StringTokenizer;

@Test
public class AVT_TC019P5 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	public void AVT_16() {
		testParameters.setCurrentTestDescription(
				"Verify whether user is able to upload video file and 6th level sub folder via streaming media for non admin user.");

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

		try {

			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);

			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			String foldername = dataTable.getData("MyFiles", "MoreSettingsOption");
			// Login Page
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			String siteName = sitesPage.getCreatedSiteName();
			sitesPage.siteFinder(siteName);
			sitesPage.enterIntoDocumentLibrary();

			ArrayList<String> allFolderNamesList = new ArrayList<String>();

			StringTokenizer token = new StringTokenizer(folderDetails, ";");
			while (token.hasMoreElements()) {
				String folderdetails = token.nextElement().toString();

				ArrayList<String> folderNamesList = new ArrayList<String>();
				folderNamesList = myFiles.getFolderNames(folderdetails);
				for (String folderName : folderNamesList) {
					myFiles.openCreatedFolder(folderName);
					allFolderNamesList.add(folderName);
				}
			}

			alfrescoAVTPage.clickOnStreamingMedia();
			alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, fileName);
			String movTitle = alfrescoAVTPage.inputTitle(foldername);
			alfrescoAVTPage.inputReferenceID(movTitle);
			alfrescoAVTPage.verifyRelatedFilesTabFields();
			alfrescoAVTPage.uploadCaptionsFile(filePath, "stat13t_sl_10_03_en.srt", "Spanish");
			alfrescoAVTPage.uploadAndVerifyValidThumbnail(filePath, "JPG.jpg");
			alfrescoAVTPage.uploadAndVerifyValidChapteringXML(filePath, "ChapteringXML.xml");
			alfrescoAVTPage.clickOnSaveAndUpload();
			alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
			alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
			alfrescoAVTPage.waitForInvisibilityOfTranscodingInProgressAndReturnUploadFilesTab();
			alfrescoAVTPage.verifyPreviewTab();
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}

	}

	@Override
	public void tearDown() {

	}
}
