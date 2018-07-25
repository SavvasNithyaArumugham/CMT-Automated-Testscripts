package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_416 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void docLibShareboxLabelCheck() {
		testParameters.setCurrentTestDescription(
				"[1]ALFDEPLOY-5370_Verify Sharebox Folders shared externally label is displayed in Sharebox filter when no folders are shared in document library");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest =new AlfrescoDocumentLibPageTest(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileNameInsideFolder = dataTable.getData("MyFiles", "FileName").split(",")[0];
		String fileNameInDocLib = dataTable.getData("MyFiles", "FileName").split(",")[1];
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String filterName= dataTable.getData("MyFiles", "Sort Options");
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileNameInDocLib);
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		myFiles.uploadFileInMyFilesPage(filePath, fileNameInsideFolder);
		sitesPage.enterIntoDocumentLibrary();
	    docLibPg.filterDocuments(filterName);
		docLibPgTest.verifyDocumentFilterLabel();
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}