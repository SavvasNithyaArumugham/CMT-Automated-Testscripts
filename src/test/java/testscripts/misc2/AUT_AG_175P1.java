package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_175P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_070()
	{
		testParameters.setCurrentTestDescription("Verify that the text 'To review content please go to' and link has been removed from email notification when user performs 'all aspect template download' operation from search results<br> - Part1: Perform 'all aspect template download' operation from search results");
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
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		myFiles.createFile(fileDetails);
		
		docDetailsPageObj.backToFolderOrDocumentPage("");
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		searchObj.performSearch();
		
		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
		searchTestObj.verifyUploadedFileInSearchResults();
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		searchObj.commonMethodForPerformSelectedItemsOperation(fileName, selectedItemMenuOptVal);
		
		String comments = dataTable.getData("Search", "CommentsForBulkDownload");
		searchObj.performAllAspectsTemplateDownload(comments+" via normal search");
		
		new FileUtil().clearFile("src/test/resources/AppTestData/TestOutput/ConfMessages.txt");
		
		String expectedMessageVal = dataTable.getData("Sites", "ExpectedConfirmationMessage");
		searchTestObj.verifyConfirmationDailogMessage(expectedMessageVal);
		
		sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		searchObj.performAdvancedSearch();
		searchTestObj.verifyUploadedFileInSearchResults();
		
		searchObj.commonMethodForPerformSelectedItemsOperation(fileName, selectedItemMenuOptVal);
		
		searchObj.performAllAspectsTemplateDownload(comments+" via advanced search");
		
		searchTestObj.verifyConfirmationDailogMessage(expectedMessageVal);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}