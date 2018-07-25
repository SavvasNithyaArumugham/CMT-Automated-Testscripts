package testscripts.misc5;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
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
public class AUT_AG_400P5 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_037() {
		testParameters.setCurrentTestDescription(
				"1.Verify Manager or Coordinator can delete others folder containing someone elses content"
						+ "<br>2.Verify Manager or Coordinator can delete others folder containing own content");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		String title = dataTable.getData("MyFiles", "Version");
		String siteName = dataTable.getData("Sites", "SiteName");
		//login as manager
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		// Verify Manager or Coordinator can delete others folder containing
		// someone elses content
		myFiles.deleteCreatedFolder(title);
		myFilesTestObj.verifyDeletedFolder(title);
		docLibPg.navigateToDocumentLibrary();
		// Verify Manager or Coordinator can delete others folder containing own
		// content
		myFiles.openFolder(folderDetails);
		myFiles.uploadFile(filePath, fileName);
		docLibPg.navigateToDocumentLibrary();
		myFiles.deleteCreatedFolder(folderDetails);
		myFilesTestObj.verifyDeletedFolder(folderDetails);

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}