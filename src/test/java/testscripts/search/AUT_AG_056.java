package testscripts.search;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_056 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_055()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to move the document to other location within search results page.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);
		
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
		homePageObj.navigateToMyFilesTab();
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		  AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
	        AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
	        homePage.navigateToMyFilesTab();
	       docLibPage.deleteAllFilesAndFolders();
		//myFiles.deleteUploadedFile(fileName);
		
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		
		sitesPage.siteFinder(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			
			myFiles.createFile(fileDetails);
			//sitesPage.documentlib();
		
		homePageObj.navigateToAdvSearch();

		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		appSearchPg.performActionBtn();
		appSearchPg.moveorCopyinActionMenu();
		
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		appSearchPgTest.verifyMoveorCopyinSearchResults();
	
		homePageObj.navigateToMyFilesTab();
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.Checkdocument(fileName);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}