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
public class AUT_AG_167 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription
		("ALFDEPLOY-3162_Verify there is a search result when user Search a folder which is having underscore in the folder name and special characters in the starting of the folder name ,through basic search for private site where user is member.<br>"+
		"ALFDEPLOY-3162_Verify there is a search result when user Search a files and folder having same name which is having underscore as name through basic search for private site where user is member..<br>");
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
	//	String fileback = dataTable.getData("MyFiles", "RelationshipName");
	//	String foldback = dataTable.getData("MyFiles", "Sort Options");
		String folder= dataTable.getData("MyFiles", "Version");
	//	String filefront = dataTable.getData("MyFiles", "MoreSettingsOption");
	//	String foldfront = dataTable.getData("MyFiles", "BrowseActionName");
		
		String search = dataTable.getData("Search", "FullText");
		//String searchFolder = dataTable.getData("Search", "Query");
	//	String searchFile = dataTable.getData("Search", "FileName");
	
		
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
		
		
	}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}