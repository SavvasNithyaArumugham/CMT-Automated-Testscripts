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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_201 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_094() {
		testParameters.setCurrentTestDescription(
				"1. Verify that user should be able to view Follow option for files in My File"
						+ "<br>2. Verify that user should be able to view Follow option for files in Shared Files"
						+ "<br>3. Verify that user should be able to view Follow option for files in Repository");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");

		//Verify that user should be able to view Follow option for files in My File
		homePageObj.navigateToMyFilesTab();

		if (!sitesPage.Checkdocument(fileName)) {
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);

		String link = dataTable.getData("MyFiles", "MoreSettingsOption");
		docLibPg.verifyDocOptions(fileName, link);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder = dataTable.getData("MyFiles", "CreateChildFolder");

		//Verify that user should be able to view Follow option for files in Shared Files
		homePageObj.navigateToSharedFilesTab();

		if (sitesPage.Checkdocument(folder)) {
			myFiles.openFolder(folder);

		} else {
			myFiles.createFolder(folderDetails);
			myFiles.openFolder(folder);
		}

		if (!sitesPage.Checkdocument(fileName)) {
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		docLibPg.verifyDocOptions(fileName, link);

		//Verify that user should be able to view Follow option for files in Repository
		/*homePageObj.navigateToRepositoryPage();	
		
		if (sitesPage.Checkdocument(folder)) {
			myFiles.openFolder(folder);

		} else {
			myFiles.createFolder(folderDetails);
			myFiles.openFolder(folder);
		}

		if (!sitesPage.Checkdocument(fileName)) {
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		docLibPg.verifyDocOptions(fileName, link);*/
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}