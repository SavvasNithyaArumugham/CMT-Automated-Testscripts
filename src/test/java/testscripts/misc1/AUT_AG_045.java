package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_045 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_036() {
		testParameters.setCurrentTestDescription("Version increment (major or minor) during check-in");
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
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		if (sitesPage.Checkdocument(fileName)) {

			appSearchPg.deletedocument(fileName);
		} else {

		}

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyUploadedFile(fileName, "");

		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.openAFile(fileName);
		docLibPage.editOffline(fileName);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		// docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyUploadedNewVersionFile();
	}

	@Override
	public void tearDown() {

	}

}
