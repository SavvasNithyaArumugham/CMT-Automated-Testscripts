package testscripts.search;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_158 extends TestCase {
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_157()
	{
		testParameters.setCurrentTestDescription("Verify that user is not able to view the Browser view of the content "
				+ "When clicked on 'Edit Offline'from the document action when file is opend through search result.");
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
		
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		
		if(sitesPage.Checkdocument(fileName)){
			
			appSearchPg.deletedocument(fileName);
			appSearchPg.deletedocument(fileName); 
			
		}else {
			
		}
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		
		myFiles.createFile(fileDetails);
		
		//sitesPage.editOffline();
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToAdvSearch();
	
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPage.deleteFileInDownloadedPath(false);
		appSearchPg.performActionBtn();
		
		AlfrescoHomePageTest homePageObjtest = new AlfrescoHomePageTest(scriptHelper);
		homePageObjtest.verifyNoofTabs();
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}