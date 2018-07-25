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
public class AUT_AG_166 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription
		("ALFDEPLOY-3162_Verify there is a search result when user Search a files which is having underscore in the file name through basic search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user Search a folder which is having underscore at the end of folder name through basic search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user Search a file which is having underscore at the end of name through basic search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user Search a files and folder having same name which is having underscore as name through basic search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user Search a folder whose name has special character and underscore through basic search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user search a folder from keyword starts with underscore through basic search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user search a file from keyword with special character through basic search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user Search a folder which is having underscore in the folder name through Advance search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user Search a folder which is having file name only with underscore through Advance search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user search a file from keyword end with underscore through Advance search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user search a file from keyword with special character through Advance search.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user Search a folder which is having underscore in the folder name through basic search for moderate site where user is member.<br>"+
		"ALFDEPLOY-3548_Verify relevant search results are returned when filename containing underscore without extension is searched via basic or advanced search<br>"+
		"ALFDEPLOY-3548_Verify relevant search results are returned when filename containing underscore with extension is searched via basic or advanced search<br>"+
		"ALFDEPLOY-3548_Verify relevant search results are returned when Folder Name containing underscore is searched via basic or advanced search<br>"+
		"ALFDEPLOY-3548_Verify relevant search results are returned when filename containing special characters without extension is searched via basic or advanced search<br>"+
		"ALFDEPLOY-3548_Verify relevant search results are returned when filename containing special characters with extension is searched via basic or advanced search<br>"+
		"ALFDEPLOY-3548_Verify relevant search results are returned when Folder Name containing special characters is searched via basic or advanced search<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user Search a folder which is having underscore in the folder name through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user Search a folder which is having undesrscore at the end of folder name through basic search. <br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user Search a folder which is having name with numeric and underscore through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user Search a files and folder having same name which is having undesrscore as name through Advance search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a file from keyword starts with underscore through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a folder from keyword starts with underscore through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a folder from keyword with underscore through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a folder from keyword ends with underscore through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a folder from keyword special characte through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a file from keyword with combination of underscore and special character through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a folder from keyword with combination of underscore and special character through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a file from keyword with combination of underscore and special character through Advance search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a folder from keyword with combination of underscore and special character through Advance search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a file from keyword with underscore through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user search a file from keyword end with underscore through basic search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user Search a file which is having undesrscore in the folder name through Advance search.<br>" + 
		"ALFDEPLOY-3162_Verify there is a search result when user Search a folder which is having undesrscore at the end of folder name through Advance search.<br>"+
		"ALFDEPLOY-3548_Verify relevant search results are returned when filename containing alphanumeric without extension is searched via basic or advanced search.<br>" + 
		"ALFDEPLOY-3548_Verify relevant search results are returned when filename containing alphanumeric with extension is searched via basic or advanced search.<br>" + 
		"ALFDEPLOY-3548_Verify relevant search results are returned when Folder Name containing alphanumeric is searched via basic or advanced search.<br>" + 
		"ALFDEPLOY-3548_Verify search results are returned when filename without extension is searched via basic search.<br>" + 
		"ALFDEPLOY-3548_Verify search results are returned when filename without extension is searched via advanced search.<br>" + 
		"ALFDEPLOY-3548_Verify search results are returned when filename with extension is searched via basic search.<br>" + 
		"ALFDEPLOY-3548_Verify search results are returned when filename with extension is searched via advanced search.<br>" + 
		"ALFDEPLOY-3548_Verify search results are returned when folder is searched via basic search.<br>");
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
		String file = dataTable.getData("MyFiles", "FileName");
		String fileback = dataTable.getData("MyFiles", "RelationshipName");
		String foldback = dataTable.getData("MyFiles", "Sort Options");
		String folder= dataTable.getData("MyFiles", "Version");
		String filefront = dataTable.getData("MyFiles", "MoreSettingsOption");
		String foldfront = dataTable.getData("MyFiles", "BrowseActionName");
		
		String search = dataTable.getData("Search", "FullText");
		String searchFolder = dataTable.getData("Search", "Query");
		String searchFile = dataTable.getData("Search", "FileName");
	
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.createFile(fileDetails);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		myFiles.createFolder(folderDetails);
						
		homePageObj.alfrescoSearch(search);
		searchTestObj.commonMethodForVerifySearchResults(file);
		searchTestObj.commonMethodForVerifySearchResults(folder);
						
		homePageObj.navigateToAdvSearch();
		searchObj.inputFileNameAdvSearchparam(search);
		searchObj.clickSearch();
		searchTestObj.commonMethodForVerifySearchResults(file);
	
		homePageObj.navigateToAdvSearch();
				
		searchObj.selectLookForFieldOption("Folders");
		searchObj.inputFileNameAdvSearchparam(search);
		searchObj.clickSearch();
				
		searchTestObj.commonMethodForVerifySearchResults(folder);
		
		// Underscore in back of file
		
		homePageObj.alfrescoSearch(fileback);
		searchTestObj.commonMethodForVerifySearchResults(fileback);
	//	searchTestObj.commonMethodForVerifySearchResults(search1);
						
		homePageObj.navigateToAdvSearch();
		searchObj.inputFileNameAdvSearchparam(fileback);
		searchObj.clickSearch();
		searchTestObj.commonMethodForVerifySearchResults(fileback);
		
		homePageObj.alfrescoSearch(foldback);
		searchTestObj.commonMethodForVerifySearchResults(foldback);
	
		homePageObj.navigateToAdvSearch();
				
		searchObj.selectLookForFieldOption("Folders");
		searchObj.inputFileNameAdvSearchparam(foldback);
		searchObj.clickSearch();
				
		searchTestObj.commonMethodForVerifySearchResults(foldback);
		
		homePageObj.alfrescoSearch(searchFile);
		searchTestObj.commonMethodForVerifySearchResults(filefront);
	
		homePageObj.navigateToAdvSearch();
				
	//	searchObj.selectLookForFieldOption("Folders");
		searchObj.inputFileNameAdvSearchparam(searchFile);
		searchObj.clickSearch();
				
		searchTestObj.commonMethodForVerifySearchResults(filefront);
		
		homePageObj.alfrescoSearch(searchFolder);
		searchTestObj.commonMethodForVerifySearchResults(foldfront);
	
		homePageObj.navigateToAdvSearch();
				
		searchObj.selectLookForFieldOption("Folders");
		searchObj.inputFileNameAdvSearchparam(searchFolder);
		searchObj.clickSearch();
				
		searchTestObj.commonMethodForVerifySearchResults(foldfront);
	}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}