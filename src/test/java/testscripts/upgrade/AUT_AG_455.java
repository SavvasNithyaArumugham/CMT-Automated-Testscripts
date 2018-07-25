package testscripts.upgrade;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
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
public class AUT_AG_455 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC454()
	{
		testParameters.setCurrentTestDescription("Verify that the after selecting search in location.Results are displayed accordingly.");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");
		
		
		String siteassertValue = dataTable.getData("Sites", "SiteName");
	
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		if(sitesPage.Checkdocument(fileName)){
			//sitesPage.documentdetails(fileName);
			
		}else {
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			myFiles.createFile(fileDetails);
		}
		
		/*homePageObj.navigateToRepositoryPage();		
		
		if(sitesPage.Checkdocument(folder)){
			sitesPage.documentdetails(folder);
				
			}else {
				myFiles.createFolder(folderDetails);
				sitesPage.documentdetails(folder);		
			}	
		
		if(sitesPage.Checkdocument(fileName)){
			//sitesPage.documentdetails(fileName);
				
			}else {
				String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
				myFiles.createFile(fileDetails);
			//	myFiles.uploadFileInMyFilesPage(filePath, fileName);
			//	sitesPage.documentdetails(fileName);		
			}
			*/
		homePageObj.navigateToAdvSearch();
		
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		//String BeforeResult = appSearchPgTest.verifysearchcount();
	/*	appSearchPg.searchResultScope(appSearchPg.selectRepolocXpath,"Respository");
		String RAfterResult = appSearchPgTest.verifysearchcount();
		appSearchPgTest.verifyRepositorysearch(RAfterResult);*/
		
		appSearchPg.searchResultScope(appSearchPg.selectSiteslocXpath,"All Sites");
		String SAfterResult = appSearchPgTest.verifysearchcount();
		
		appSearchPgTest.verifyAllSitesearch(SAfterResult);
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}