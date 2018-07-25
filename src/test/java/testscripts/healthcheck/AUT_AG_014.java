package testscripts.healthcheck;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
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
public class AUT_AG_014 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_001() {
		testParameters.setCurrentTestDescription("MOVE TO / COPY TO <br>"+ "1. Verify that user is able to move folders to a different folders in same site"
				+"2. Verify that user is able to copy to folders to a different folders in same site");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder = dataTable.getData("MyFiles", "Version");

		String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		// Verify that user is able to move files to a different site
		sitesPage.siteFinder(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		
		myFiles.uploadFile(filePath, fileName);
		sitesPage.documentdetails(fileName);
		
		docDetailsPageObj.clickMoveToDocAction();
		docDetailsPageObj.selectFileInMovePopUp();
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folder);
		
		myFilesTestObj.verifyMovedFileInTargetSite(fileName, "");
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		
		myFiles.uploadFile(filePath, fileName);
		sitesPage.documentdetails(fileName);
		
		docDetailsPageObj.clickCopyToDocAction();
		docDetailsPageObj.selectFolderToCopyInCopyPopUp(folder);
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folder);
		
		myFilesTestObj.verifyCopieddFileInTargetSite(fileName, "");
				
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}