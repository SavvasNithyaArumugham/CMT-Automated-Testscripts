package testscripts.search;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_128 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_127()
	{
		testParameters.setCurrentTestDescription("Verify the user is not able to Perform Advance Search when he gave full name(Nitin Khare) in Modifier Field");
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
		
	/*	AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");*/
		/*sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();*/
		
		/*if(sitesPage.Checkdocument(fileName)){
			
		}else {
			
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			
			myFiles.createFile(fileDetails);
			
		}*/
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToAdvSearch();

		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.searchWithUserName();
		appSearchPg.clickSearch();
		
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		appSearchPgTest.verifyNoSearchResults();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}