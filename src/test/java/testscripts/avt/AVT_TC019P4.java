package testscripts.avt;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import java.util.ArrayList;
import java.util.StringTokenizer;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC019P4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_16() {
		testParameters.setCurrentTestDescription(
				"Verify whether user is able to upload audio file and 6th level sub folder via streaming media for non admin user.");

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
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
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
			docLibPg.deleteAllFilesAndFolders();

			ArrayList<String> allFolderNamesList = new ArrayList<String>();

			StringTokenizer token = new StringTokenizer(folderDetails, ";");
			while (token.hasMoreElements()) {
				String folderdetails = token.nextElement().toString();
				myFiles.createFolder(folderdetails);
				ArrayList<String> folderNamesList = new ArrayList<String>();
				folderNamesList = myFiles.getFolderNames(folderdetails);
				for (String folderName : folderNamesList) {
					myFiles.openCreatedFolder(folderName);
					allFolderNamesList.add(folderName);
				}
			}

			alfrescoAVTPage.clickOnStreamingMedia();
			alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, fileName);

			try {
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, alfrescoAVTPage.disabledrelatedXpath);
				if (UIHelper.checkForAnElementbyXpath(driver, alfrescoAVTPage.disabledrelatedXpath)) {
					UIHelper.highlightElement(driver, alfrescoAVTPage.disabledrelatedXpath);
					report.updateTestLog("Verify related tab is disabled ", "Related file tab is disabled for audio",
							Status.PASS);
				} else {
					report.updateTestLog("Verify related tab is disabled ",
							"Related file tab is not disabled for audio", Status.FAIL);
				}
			} catch (Exception e) {

				e.printStackTrace();
				report.updateTestLog("Verify related tab is disabled ", "Related file tab is not disabled for audio",
						Status.FAIL);
			}
			String movTitle = alfrescoAVTPage.inputTitle(foldername);
			alfrescoAVTPage.inputReferenceID(movTitle);
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
