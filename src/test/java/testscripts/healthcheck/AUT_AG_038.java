package testscripts.healthcheck;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_038 extends TestCase{

private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC114()
	{
		testParameters.setCurrentTestDescription("Test that user is able to preview the content of a MS Word Doc via the CMS UI when it is stored within the CMS");
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
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		sitesPage.siteFinder(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileNameList = dataTable.getData("MyFiles", "FileName");
        String[] splittedFileName=fileNameList.split(",");
        String wordFile=splittedFileName[0];
        String powerPointFile=splittedFileName[1];
        String excelFile=splittedFileName[2];
        
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		//For Word File
	
	/*	if(!docLibPage.isFileIsAvailable(wordFile)){
			myFiles.deleteUploadedFile(wordFile);*/
			
			myFiles.uploadFileInMyFilesPage(filePath, wordFile);
			
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
			myFilesTestObj.verifyUploadedFile(wordFile,"");
	//	}
		sitesPage.documentdetails(wordFile);
		
		
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		docLibPageTest.verifyDocumentPreviewed(wordFile);
		UIHelper.waitFor(driver);
		
		/*
		//For PowerPoint File
		sitesPage.enterIntoDocumentLibrary();
		
		if(!docLibPage.isFileIsAvailable(powerPointFile)){
			myFiles.deleteUploadedFile(powerPointFile);
			
			myFiles.uploadFileInMyFilesPage(filePath, powerPointFile);
			
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
			myFilesTestObj.verifyUploadedFile(powerPointFile,"");
		}	
		sitesPage.documentdetails(powerPointFile);
		docLibPageTest.verifyDocumentPreviewed(powerPointFile);
		UIHelper.waitFor(driver);
		
        
		//For Excel File
		sitesPage.enterIntoDocumentLibrary();
		
		if(!docLibPage.isFileIsAvailable(excelFile)){
			myFiles.deleteUploadedFile(excelFile);
			
			myFiles.uploadFileInMyFilesPage(filePath, excelFile);
			
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
			myFilesTestObj.verifyUploadedFile(excelFile,"");
		}	
		sitesPage.documentdetails(excelFile);
		docLibPageTest.verifyDocumentPreviewed(excelFile);*/

		}

	@Override
	public void tearDown() {
		
	}

}
