package testscripts.mediaguide;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaGuidePage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MG_TC005P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_015() {
		testParameters.setCurrentTestDescription(
				"Verify the user is switched to the Streaming audio tab of the Media Guide Interface "
				+ "on selecting untranscoded audio files and on click of Convert Streaming Media button.<br>");
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

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		String title = dataTable.getData("MyFiles", "Version");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String convertAction = dataTable.getData("MyFiles", "BrowseActionName");
		String validInputTitle= dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String tabDetails=dataTable.getData("MyFiles","SelectDropdownValue");
		String verifyConfirmMessage = dataTable.getData("MyFiles", "PopUpMsg"); 
		

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		
		myFiles.openFolder(title);
		for (String uploadFile : fileName) {
			myFiles.uploadFile(filePath, uploadFile);
		}
		docLibPg.navigateToDocumentLibrary();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, editSL);
		mediaGuidePg.VerifyTabsInMediaInterface();
		mediaGuidePg.navigateToParticularTab(convertAction);
	 	mediaGuidePg.clickOnSelectAllCheckbox(convertAction);
	    mediaGuidePg.enterValidTitleForUntranscodedMultipleFiles(convertAction,validInputTitle);
		mediaGuidePg.clickConvertToStreamMedia();
		mediaGuidePg.verifyValidationConversionMessage(verifyConfirmMessage);
		mediaGuidePg.verifyUserNavigatedToParticularTab(tabDetails);
	}
		
	

	@Override
	public void tearDown() {

	}
}
