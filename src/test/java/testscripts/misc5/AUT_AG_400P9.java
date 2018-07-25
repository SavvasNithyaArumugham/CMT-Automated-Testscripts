package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_400P9 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTC030P2() {
		testParameters.setCurrentTestDescription(
				"Verify Manager can delete the other users folder or content which has comment");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

		String folder = dataTable.getData("MyFiles", "Version");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");

		functionalLibrary.loginAsValidUser(signOnPage);

		String site = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(site);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		myFiles.deleteCreatedFolder(folder);
		myFilesTestObj.verifyDeletedFolder(folder);

		docLibPge.navigateToDocumentLibrary();
		myFiles.createFolder(folderDetails);
		myFiles.openFolder(folder1);
		myFiles.uploadFile(filePath, fileName);
		myFiles.openAFile(fileName);
		docLibPge.editOffline(fileName);

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}