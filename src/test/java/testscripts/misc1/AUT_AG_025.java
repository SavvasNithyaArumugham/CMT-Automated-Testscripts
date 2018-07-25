package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author Naresh Kumar Salla
 */
public class AUT_AG_025 extends TestCase {
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_025()
	{
		testParameters.setCurrentTestDescription("1. Verify that version of the asset not get incremented, while performing Edit property when file is present in Shared Files using 'Document Action'"
				+ "<br>2. Verify that version of the asset not get incremented, while performing Edit property when file is present in Shared Files using 'Browse Action'");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);		
		
		homePage.navigateToSharedFilesTab();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		String folderAutoTestDetails = "FolderName:1AutoTest,Title:Test,Description:TestDemo";
		
		if(sitesPage.Checkdocument("1AutoTest")){
			sitesPage.openFolder("1AutoTest");
			UIHelper.waitFor(driver);
		}else {
			myFiles.createFolder(folderAutoTestDetails);
			UIHelper.waitFor(driver);
			sitesPage.openFolder("1AutoTest");
			UIHelper.waitFor(driver);
		}
		
		String folderTestDetails = "FolderName:111Test,Title:Test,Description:TestDemo";
		
		if(sitesPage.Checkdocument("111Test")){
			sitesPage.openFolder("111Test");
			UIHelper.waitFor(driver);
		}else {
			myFiles.createFolder(folderTestDetails);
			UIHelper.waitFor(driver);
			sitesPage.openFolder("111Test");
			UIHelper.waitFor(driver);
		}
		
		if(docLibPage.isFileIsAvailable(fileName)){
			docLibPage.deleteFileInDocumentLib();
		}
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFiles.openAFileInSharedFiles(fileName);
		docDetailsPage.performEditPropertiesDocAction();
		docDetailsPage.enterDataAndSaveIntoEditProperties();
		
		homePage.navigateToSharedFilesTab();
		sitesPage.openFolder("1AutoTest");
		UIHelper.waitFor(driver);
		sitesPage.openFolder("111Test");
		docLibPage.checkVersionAvailable(fileName, "Verify the file version");
		
		myFiles.openAFileInSharedFiles(fileName);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyEditPropertyVersion(fileName);
		
		homePage.navigateToSharedFilesTab();
		
		sitesPage.openFolder("1AutoTest");
		UIHelper.waitFor(driver);
		sitesPage.openFolder("111Test");
		
		if(docLibPage.isFileIsAvailable(fileName)){
			docLibPage.deleteFileInDocumentLib();
		}
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPage.clickAllProperties();
		docDetailsPage.enterDataAndSaveIntoEditProperties();
		
		homePage.navigateToSharedFilesTab();
		sitesPage.openFolder("1AutoTest");
		UIHelper.waitFor(driver);
		sitesPage.openFolder("111Test");
		docLibPage.checkVersionAvailable(fileName, "Verify the file version");
		
		myFiles.openAFileInSharedFiles(fileName);
		docDetailsPageTest.verifyEditPropertyVersion(fileName);
	}

	@Override
	public void tearDown() {
		
	}
}
