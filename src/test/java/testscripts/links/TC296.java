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

public class TC296 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_032()
	{
		testParameters.setCurrentTestDescription("Verify that the user is able to perform edit offline operation on linked files while hoving mouse on file.");
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
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		String propertyName2 = dataTable.getData("Sites", "FilePropertyName2");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderName = dataTable.getData("MyFiles", "Version");
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		
	/*	
		ArrayList<String> createdFileNames = myFilesTestObj.getCreatedFileNames(fileDetails);
		for(String fileName:createdFileNames)
		{*/
		/*	myFiles.deleteUploadedFile(fileName);
			myFiles.deleteUploadedFile(fileName);*/
		String filePath = dataTable.getData("MyFiles", "FilePath");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		/*	docDetailsPageTest.verifyCreatedFile(fileName, "");
			docDetailsPageObj.backToFolderOrDocumentPage("");*/
			sitesPage.clickOnMoreSetting(fileName);
			sitesPage.clickOnMoreLinkOptions(fileName, propertyName);
		
			docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
			docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
	/*	}*/
		
			sitesPage.documentdetails(folderName);
	
		
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, propertyName2);
	
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.verifyLockedBanner(fileName);					
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}