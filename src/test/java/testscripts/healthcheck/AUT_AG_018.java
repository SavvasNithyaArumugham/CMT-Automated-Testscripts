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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_018 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC288()
	{
		testParameters.setCurrentTestDescription("Verify  User is able to delete particular linked file when he select 'this file' from the warning message <br>"
				+"Verfiy user can delete linked files through delete 'This file' option");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		ArrayList<String> folderNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		String propertyName2 = dataTable.getData("Sites", "FilePropertyName2");

		
		functionalLibrary.loginAsValidUser(signOnPage);		
				
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
				
		docLibPg.deleteAllFilesAndFolders();

		
		myFiles.createFolder(folderDetails);		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		
		//Operation to Link a File
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.commonMethodForClickOnMoreOptionLink(fileName, propertyName);
		
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for (String folderName : folderNamesList) {
			sitesPage.documentdetails(folderName);
		
		}
		
		myFilesTestObj.verifyUploadedFile(fileName,"");
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.commonMethodForClickOnMoreOptionLink(fileName, propertyName2);
		sitesPage.clickOnDeleteThisFile();
		myFilesTestObj.verifyDeletedFiles(fileName);
		sitesPage.enterIntoDocumentLibrary();
		myFilesTestObj.verifyUploadedFile(fileName,"");
		
		//Operation to Link a File
				sitesPage.clickOnMoreSetting(fileName);
				sitesPage.commonMethodForClickOnMoreOptionLink(fileName, propertyName);
				
				docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
				docDetailsPageObj.clickLinkBtnInLinkPopUp();
				
				folderNamesList = myFiles.getFolderNames(folderDetails);
				
				for (String folderName : folderNamesList) {
					myFiles.openFolder(folderName);
				}
				
				myFilesTestObj.verifyUploadedFile(fileName,"");
				
				sitesPage.clickOnMoreSetting(fileName);
				sitesPage.clickOnMoreLinkOptions(fileName, propertyName2);
				sitesPage.clickOnDeleteThisFile();
				myFilesTestObj.verifyDeletedFiles(fileName);	
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}