package testscripts.mediaguide;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaGuidePage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMediaGuidePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC007P7 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify Reference ID value updated in Audio tab of Media Guide Interface is reflected in Asset GUID label of Properties panel in Document preview screen."
						+ "<br>2.Verify Reference ID value updated in Video tab of Media Guide Interface is reflected in Asset GUID label of Properties panel in Document preview screen."
						+ "<br>3.Verify Title value updated in Audio tabs of Media Guide Interface is reflected in Title label of Properties panel in Document preview screen."
						+ "<br>4.Verify Title value updated in Video tabs of Media Guide Interface is reflected in the Title label of Properties panel in Document preview screen.");
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

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMediaGuidePage mediaGuidePg = new AlfrescoMediaGuidePage(scriptHelper);
		AlfrescoMediaGuidePageTest mediaGuidePgTest = new AlfrescoMediaGuidePageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailPg = new AlfrescoDocumentDetailsPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
		String convertAction[] = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String validInputTitle[] = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb").split(",");
		String validReferenceID[] = dataTable.getData("MyFiles", "StatusReportValue").split(",");
		String aspectValue = dataTable.getData("MyFiles", "SelectDropdownValue");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(title);
		for (String uploadFile : fileName) {
			myFiles.uploadFile(filePath, uploadFile);
		}
		docLibPg.navigateToDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);
		mediaGuidePg.VerifyTabsInMediaInterface();

		for (String mediaFiles1 : convertAction) {
			for (int i1 = 0; i1 < validInputTitle.length; i1++) {
				if (mediaFiles1.equals("audio")) {

					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
					mediaGuidePg.enterValidTitleForUntranscodedFiles(mediaFiles1, validInputTitle[i1]);
					mediaGuidePg.enterValidReferenceID(mediaFiles1, validReferenceID[i1]);
					String actTitle = mediaGuidePg.getTitleIDFromMediaInterface();
					String actRef = mediaGuidePg.getReferenceIDFromMediaInterface();
					mediaGuidePg.doubleClickThumbnail();
					mediaGuidePgTest.verifyContentAssetApplied(aspectValue, mediaFiles1);
					docDetailPg.clickCancelInManageAspects();
					mediaGuidePgTest.verifyAssetGUID(actRef, mediaFiles1);
					mediaGuidePgTest.verifySourceID(actTitle, mediaFiles1);

				} else if (mediaFiles1.equals("video")) {

					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles1);
					mediaGuidePg.enterValidTitleForUntranscodedFiles(mediaFiles1, validInputTitle[1]);
					mediaGuidePg.enterValidReferenceID(mediaFiles1, validReferenceID[1]);
					String actTitle1 = mediaGuidePg.getTitleIDFromMediaInterface();
					String actRef1 = mediaGuidePg.getReferenceIDFromMediaInterface();
					mediaGuidePg.doubleClickThumbnail();
					mediaGuidePgTest.verifyContentAssetApplied(aspectValue, mediaFiles1);
					docDetailPg.clickCancelInManageAspects();
					mediaGuidePgTest.verifyAssetGUID(actRef1, mediaFiles1);
					mediaGuidePgTest.verifySourceID(actTitle1, mediaFiles1);
				}
				UIHelper.closeCurrentWindow(driver);
				UIHelper.switchtab(1, driver);
				break;
			}
		}
	}

	@Override
	public void tearDown() {

	}
}
