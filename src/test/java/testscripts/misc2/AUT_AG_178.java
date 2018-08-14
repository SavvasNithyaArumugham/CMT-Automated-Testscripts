package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_178 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_073() {
		testParameters
				.setCurrentTestDescription("Verify that when User add a file to my favorite from Shared files then it will show under My Favorite option ");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibPagetest = new AlfrescoDocumentLibPageTest(
				scriptHelper);
		AlfrescoSearchPage searchPage = new AlfrescoSearchPage(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		String link = dataTable.getData("MyFiles", "MoreSettingsOption");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder = dataTable.getData("MyFiles", "BrowseActionName");

		homePageObj.navigateToSharedFilesTab();
		UIHelper.waitForPageToLoad(driver);
		if (sitesPage.Checkdocument(folder)) {
			sitesPage.documentdetails(folder);
		} else {
			myFiles.createFolder(folderDetails);
			sitesPage.documentdetails(folder);
		}
		UIHelper.waitForPageToLoad(driver);
		if (sitesPage.Checkdocument(fileName)) {
			searchPage.deletedocument(fileName);
		}
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		homePageObj.navigateToSharedFilesTab();
		myFiles.openCreatedFolder(folder);
		docLibPgObj.clickOnDocOptions(fileName, link);
		UIHelper.waitFor(driver);
		docLibPagetest.verifyFavoptionselected();
		UIHelper.waitFor(driver);
		taskPage.filterWFtasks();
		docLibPgObj.checkFileIsAvailable(fileName);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}