package testscripts.links;

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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC11 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LINKS_008() {
		testParameters
				.setCurrentTestDescription("Verify user can perfom move operation on linked files");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		// Delete file in target site, if present
		UIHelper.waitFor(driver);
		sitesPage.siteFinder(targetSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		//myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		
		//homePageObj.navigateToSitesTab();
		sitesPage.siteFinder(sourceSiteName);
		//sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		
		
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		docLibPg.deleteAllFilesAndFolders();
		//myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		myFiles.deleteUploadedFile(fileName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		// Perform Link to Operation
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, propertyName);
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();

		folderNamesList = myFiles.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			myFiles.openFolder(folderName);
		}

		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyUploadedFile(fileName, "");
		myFiles.openUploadedOrCreatedFile(fileName,"");
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
		
		// Perform Move Operation
		docDetailsPageObj.clickMoveToDocAction();
		docDetailsPageObj.selectFileInMovePopUp();
		sitesPage.siteFinder(targetSiteName);
		//homePageObj.navigateToSitesTab();
		//sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		for (String folderName : folderNamesList) {
			myFiles.openFolder(folderName);
		}
		myFilesTestObj.verifyIsFileAvilabile(fileName,"");

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}