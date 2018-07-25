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
public class AUT_AG_168 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription
		("ALFDEPLOY-3548_Verify relevant search results are returned when filename containing portuguese character without extension is searched via basic or advanced search.<br>"+
		"ALFDEPLOY-3548_Verify relevant search results are returned when filename containing portuguese character with extension is searched via basic or advanced search.<br>"+
		"ALFDEPLOY-3548_Verify relevant search results are returned when Folder Name containing portuguese character is searched via basic or advanced search<br>");
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
		String folder= dataTable.getData("MyFiles", "Version");
		String search = dataTable.getData("Search", "FullText");

	
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.createFile(fileDetails);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		myFiles.createFolder(folderDetails);
						
		homePageObj.alfrescoSearch(folder);
		searchTestObj.commonMethodForVerifySearchResults(file);
		searchTestObj.commonMethodForVerifySearchResults(folder);
		
		homePageObj.alfrescoSearch(file);
		searchTestObj.commonMethodForVerifySearchResults(file);
						
		homePageObj.navigateToAdvSearch();
		searchObj.inputFileNameAdvSearchparam(file);
		searchObj.clickSearch();
		searchTestObj.commonMethodForVerifySearchResults(file);
	
		homePageObj.navigateToAdvSearch();
				
		searchObj.selectLookForFieldOption("Folders");
		searchObj.inputFileNameAdvSearchparam(folder);
		searchObj.clickSearch();
				
		searchTestObj.commonMethodForVerifySearchResults(folder);
	}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}