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
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC10 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_007()
	{
		testParameters.setCurrentTestDescription("1). Verify that user is able to view the original breadcrumbs upon previewing original file."
				+ "2). Verify that user is able to view the linked breadcrumbs upon previewing Linked file.");
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
		ArrayList<String> originalFileLocationsList = new ArrayList<String>();
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");


		functionalLibrary.loginAsValidUser(signOnPage);		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();		
		
		docLibPage.deleteAllFilesAndFolders();

		myFiles.createFolder(folderDetails);		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		//Operation to Link a File
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.commonMethodForClickOnMoreOptionLink(fileName, propertyName);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		myFiles.openUploadedOrCreatedFile(fileName, "");
	
		originalFileLocationsList=docDetailsPageObj.getLocations(fileName);
		docDetailsPageTest.verifyOriginalLocation(originalFileLocationsList.get(0));
		
//		Verify that user is able to view the linked breadcrumbs upon previewing Linked file.
		
		ArrayList<String> secondaryFileLocationsList = new ArrayList<String>();
		ArrayList<String> folderNamesList = new ArrayList<String>();	
		String folderDetails1 = dataTable.getData("MyFiles", "CreateFolder");
		String propertyName1 = dataTable.getData("Sites", "FilePropertyName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		ArrayList<String> createdFileNames = myFilesTestObj.getCreatedFileNames(fileDetails);

		
		sitesPage.enterIntoDocumentLibrary();
		
//		myFiles.deleteCreatedFolder(folderDetails1);
//		
//		myFiles.createFolder(folderDetails1);
		
		
		
		for(String fileName1:createdFileNames)
		{
			myFiles.deleteUploadedFile(fileName1);
			myFiles.createFile(fileDetails);
			docDetailsPageTest.verifyCreatedFile(fileName1, "");
			docDetailsPageObj.backToFolderOrDocumentPage("");

			//Operation to Link a File
			sitesPage.clickOnMoreSetting(fileName1);
			sitesPage.clickOnMoreLinkOptions(fileName1, propertyName1);
			docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
			docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		}
		
		folderNamesList = myFiles.getFolderNames(folderDetails1);
		for (String folderName : folderNamesList) {
			myFiles.openFolder(folderName);
			for(String fileName1:createdFileNames)
			{
			myFiles.openUploadedOrCreatedFile(fileName1, "");
			secondaryFileLocationsList=docDetailsPageObj.getLocations(fileName1);
			}
		}
	
		docDetailsPageTest.verifySecondaryLocation(secondaryFileLocationsList.get(1));
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}