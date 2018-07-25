package testscripts.search;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_165 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription
		("ALFDEPLOY-3453_Verify the is a search result when user Search a folder which is having search keyword starting with alphabet through basic search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a file which is having search keyword starting with alphabet through basic search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a folder and file which is having search keyword starting with alphabet through basic search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a folder which is having search keyword ends with alphabet followed by numeric through basic search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a file which is having search keyword ends with alphabet followed by numeric through basic search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a folder and file which is having search keyword ends with alphabet followed by numeric through basic search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a folder which is having search keyword with alpha numeric character through basic search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a file which is having search keyword with alpha numeric character through basic search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a folder which is having search keyword starting with alphabet through advance search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a file which is having search keyword starting with alphabet through advance search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a folder which is having search keyword ends with alphabet followed by numeric through advance search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a file which is having search keyword ends with alphabet followed by numeric through advance search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a folder which is having search keyword with alpha numeric character through advance search.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a file which is having search keyword with alpha numeric character through advance search.<br>"+
		".<br> - Part1: Login with and Open Site and create a file");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);


		String siteName = dataTable.getData("Sites", "SiteName");

		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
	
		String searchQuery = dataTable.getData("Search", "FullText");
		String searchfolder = dataTable.getData("Search", "Query");
		String searchFF = dataTable.getData("Search", "FileName");
		String searchFFolder = dataTable.getData("Search", "Title");
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
		

		myFiles.createFile(fileDetails);

		myFiles.createFolder(folderDetails);
			
		if (searchQuery.contains(",")) {
			String splittedFileDetailas[] = searchQuery.split(",");
			for (String fileValues : splittedFileDetailas) {
				
				homePageObj.alfrescoSearch(fileValues);
				searchTestObj.commonMethodForVerifySearchResults(fileValues);
				
			}
		}
		
		if (searchfolder.contains(",")) {
			String splittedFileDetailas[] = searchfolder.split(",");
			for (String fileValues : splittedFileDetailas) {
				
				homePageObj.alfrescoSearch(fileValues);
				searchTestObj.commonMethodForVerifySearchResults(fileValues);
				
			}
		}
		
		homePageObj.alfrescoSearch(searchFF);
		searchTestObj.commonMethodForVerifySearchResults(searchFF);
		searchTestObj.commonMethodForVerifySearchResults(searchFFolder);
		
		if (searchQuery.contains(",")) {
			String splittedFileDetailas[] = searchQuery.split(",");
			for (String fileValues : splittedFileDetailas) {
				
				homePageObj.navigateToAdvSearch();
				searchObj.inputFileNameAdvSearchparam(fileValues);
				searchObj.clickSearch();
				
				searchTestObj.commonMethodForVerifySearchResults(fileValues);
				
			}
		}
		
		
		
		if (searchfolder.contains(",")) {
			String splittedFileDetailas[] = searchfolder.split(",");
			for (String fileValues : splittedFileDetailas) {
				
				homePageObj.navigateToAdvSearch();
				
				searchObj.selectLookForFieldOption("Folders");
				searchObj.inputFileNameAdvSearchparam(fileValues);
				searchObj.clickSearch();
				
				searchTestObj.commonMethodForVerifySearchResults(fileValues);
				
			}
		}
		
	}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}