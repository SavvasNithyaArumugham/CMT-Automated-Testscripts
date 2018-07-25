package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_202 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_095() {
		testParameters.setCurrentTestDescription(
				"1. Verify that user should not be able to view follow option below the folder name in My Files"
						+ "<br>2. Verify that user should not be able to view Follow option for folders in Shared Files"
						+ "<br>3. Verify that user should not be able to view Follow option for folders in Repository");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		String folderName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String link = dataTable.getData("MyFiles", "MoreSettingsOption");

		// 1. Verify that user should not be able to view follow option below
		// the folder name in My Files
		homePageObj.navigateToMyFilesTab();

		if (sitesPage.Checkdocument(folderName)) {

		} else {
			myFiles.createFolder(folderDetails);
		}
		docLibPg.verifyFolderOptions(folderName, link);

		// 2. Verify that user should not be able to view Follow option for
		// folders in Shared Files
		homePageObj.navigateToSharedFilesTab();

		if (sitesPage.Checkdocument(folderName)) {

		} else {
			myFiles.createFolder(folderDetails);

		}
		docLibPg.verifyFolderOptions(folderName, link);

		// 3. Verify that user should not be able to view Follow option for
		// folders in Repository
		homePageObj.navigateToRepositoryPage();

		if (sitesPage.Checkdocument(folderName)) {

		} else {
			myFiles.createFolder(folderDetails);

		}
		docLibPg.verifyFolderOptions(folderName, link);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}