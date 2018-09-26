package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_262P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_046()
	{
		testParameters.setCurrentTestDescription("Test user B can still preview the file that is locked by user A - Part 1");
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
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		//if(!docLibPage.checkFileIsAvailable(fileName)){
			myFiles.deleteUploadedFile(fileName);
			
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
			myFilesTestObj.verifyUploadedFile(fileName,"");
		//}
		docLibPage.openAFile(fileName);
		
		if(!(docLibPage.isFileLocked())){
			docLibPage.lockFile(fileName);
		}
	}

	@Override
	public void tearDown() {
		
	}
}
