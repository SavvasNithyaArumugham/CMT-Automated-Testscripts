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
public class AUT_AG_309P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription(
				"Internal User created subfolder and versioning file, for verifying folder path not displaying in 24hr Aggregated mail");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderName = dataTable.getData("MyFiles", "Version");

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		myFiles.openFolder(folderName);
		myFiles.createFolder(folderDetails);
		String createFileName= dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");

		sitesPage.clickOnMoreSetting(createFileName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(createFileName, moreSettingsOptionName);

		docLibPg.uploadNewVersionInDocLibPage(fileName, filePath, "Minor", "TestShare");
		docLibPg.uploadNewVersionButton();
		sitesPage.documentdetails(fileName);

		if (docDetailsPage.getCurrentVersion().equals("1.1")) {
			report.updateTestLog(
					"Verify the file is updated with next minor version", "file " + fileName
							+ " is updated with next minor version " + docDetailsPage.getCurrentVersion(),
					Status.PASS);
		} else {
			report.updateTestLog(
					"Verify the file is updated with next minor version", "file " + fileName
							+ " is not updated with next minor version" + docDetailsPage.getCurrentVersion(),
					Status.FAIL);
		}


	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}