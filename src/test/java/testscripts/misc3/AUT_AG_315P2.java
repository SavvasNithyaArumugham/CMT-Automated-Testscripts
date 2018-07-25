package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_315P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters
				.setCurrentTestDescription("Internal User upload folder,add file and shared externally, for verifying folder path not displaying in 1hr Aggregated mail");
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
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder2Details = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderName= dataTable.getData("MyFiles", "Version");
		String title= dataTable.getData("MyFiles", "BrowseActionName");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String renameFolder =dataTable.getData("MyFiles", "AccessToken");
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		myFiles.openFolder(folderName);
		myFiles.createFolder(folderDetails);
		myFiles.openFolder(title);
		myFiles.uploadFile(filePath, fileName);
		myFiles.createFolder(folder2Details);
		String folderRenamed= dataTable.getData("MyFiles", "RelationshipName");
		sitesPage.clickOnMoreSetting(folderRenamed);
		sitesPage.commonMethodForPerformBrowseOption(folderRenamed, moreSettingsOptionName);

		docDetailsPg.clickAllProperties();
        docDetailsPg.editInEditPropertiesInputBox("Name", renameFolder);

		docDetailsPg.clickSaveInEditProperties();

		if(sitesPage.documentAvailable(renameFolder)){
			report.updateTestLog("Verify Folder Renamed",
					"Folder renamed successfully"
							+ "<br><b> Renamed folder Name : </b></br>"+ renameFolder, Status.PASS);
		}else{
			report.updateTestLog("Verify Folder Renamed",
					"folder failed to renamed", Status.FAIL);
		}
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}