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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_016 extends TestCase{

private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_016()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is able select and view previous versions in an ordered list"
				+ "<br>2. Verify that user is able to view date and user who checked-in for each version"
				+ "<br>3. Validate any previous revision to select as latest version"
				+ "<br>4. Validate that all earlier versions will be retained"
				+ "<br>5. Verify that the latest version listed (top of list) of assets associate with task"
				+ "<br>6. Verify that user is able to revert any previous version of the file");
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
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		//myFiles.deleteUploadedFile(fileName);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyUploadedFile(fileName,"");
		
		docLibPage.openAFile(fileName);
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		
		AlfrescoMyTasksPageTest taskPageTest = new AlfrescoMyTasksPageTest(scriptHelper);
		//Verify that the latest version listed (top of list) of assets associate with task
		taskPageTest.verifyUploadedFileWithLastVersion(fileName);
		
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		
		AlfrescoDocumentDetailsPageTest docDetailsTestPage = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		
		//Verify that user is able select and view previous versions in an ordered list
		docDetailsTestPage.verifyPreviousVersionInOrderedList();
		
		//Verify that user is able to view date and user who checked-in for each version
		docDetailsTestPage.verifyCheckedInPersonAndDate();
		
		//Validate that all earlier versions will be retained
		docDetailsTestPage.verifyPreviousVersionsRetained();
		
		//3. Validate any previous revision to select as latest version
		//6. Verify that user is able to revert any previous version of the file
		docDetailsPage.revertAnyOlderVersionToCurrentVersion();
		docDetailsTestPage.verifyPreviousVersionIntoLatestVersion();
	}

	@Override
	public void tearDown() {
		
	}
}
