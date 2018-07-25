package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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

public class TC7 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_004()
	{
		testParameters.setCurrentTestDescription("1). Verify all the properties of the Original file is reflected for the Linked File."
				+ "2). Verify when user edits the document that was linked then it should update the original source document/folder as well.");
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
	
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		ArrayList<String> folderNamesList = new ArrayList<String>();
		ArrayList<String> sourceFilePropertiesList = new ArrayList<String>();
		ArrayList<String> targetFilePropertiesList = new ArrayList<String>();
		
		
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
	
		myFiles.deleteCreatedFolder(folderDetails);
		
		myFiles.createFolder(folderDetails);
		ArrayList<String> createdFileNames = myFilesTestObj.getCreatedFileNames(fileDetails);
		
		for(String fileName1:createdFileNames)
		{
			myFiles.deleteUploadedFile(fileName1);
			myFiles.createFile(fileDetails);
			docDetailsPageTest.verifyCreatedFile(fileName1, "");
			docDetailsPageObj.backToFolderOrDocumentPage("");
			sitesPage.clickOnMoreSetting(fileName1);
			sitesPage.clickOnMoreLinkOptions(fileName1, propertyName);
		
			docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
			docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
			myFiles.openUploadedOrCreatedFile(fileName1, "");
		
			sourceFilePropertiesList=docDetailsPageObj.getPropertiesOfFile(fileName1);
			docDetailsPageObj.backToFolderOrDocumentPage("");
		}
			
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
			for(String fileName1:createdFileNames)
			{
			myFiles.openUploadedOrCreatedFile(fileName1, "");
			targetFilePropertiesList=docDetailsPageObj.getPropertiesOfFile(fileName1);
		
			}
		}
		System.out.println("target"+targetFilePropertiesList);
		System.out.println("target"+sourceFilePropertiesList);
		docDetailsPageObj.comparePropertiesOfTwoFiles(sourceFilePropertiesList,targetFilePropertiesList);
		

	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}