package testscripts.mediaguide;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaGuidePage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC006P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"1.Verify Title and Reference ID fields are non editable in Streaming Audio tab of Media Guide interface.<br>"
						+ "2.Verify Title and Reference ID fields are non editable in Streaming Video tab of Media Guide interface.<br>"
						+ "3.Verify Convert to Streaming Media button appears even on selecting the single untranscoded Audio file for transcoding.<br>"
						+ "4.Verify Convert to Streaming Media button appears even on selecting the single untranscoded Video file for transcoding.<br>"
						+ "5.Verify Convert to Streaming Media button is displayed in the Media Guide Interafce if the user is part of Media Developers Group.");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMediaGuidePage mediaGuidePg = new AlfrescoMediaGuidePage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String convertAction[] = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String validTitle[] = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb").split(",");
		String transcodedAction[] = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		String verifyConfirmMessage = dataTable.getData("MyFiles", "PopUpMsg");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);

		for (String mediaFiles2 : convertAction) {
			mediaGuidePg.VerifyTabsInMediaInterface();
			for (int i1 = 0; i1 < validTitle.length; i1++) {
				if (mediaFiles2.equals("audio")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles2);
					mediaGuidePg.trancodingMediaFiles(mediaFiles2, validTitle[i1], verifyConfirmMessage);
					mediaGuidePg.checkWhetherTitleFieldNotEditable(transcodedAction[0]);
					mediaGuidePg.checkWhetherReferenceFieldNotEditable(transcodedAction[0]);
				} else if (mediaFiles2.equals("video")) {
					mediaGuidePg.clickOnFileSelectCheckBox(mediaFiles2);
					mediaGuidePg.trancodingMediaFiles(mediaFiles2, validTitle[1], verifyConfirmMessage);
					mediaGuidePg.checkWhetherTitleFieldNotEditable(transcodedAction[1]);
					mediaGuidePg.checkWhetherReferenceFieldNotEditable(transcodedAction[1]);
				}
				break;

			}
		}

	}

	@Override
	public void tearDown() {

	}
}
