package testscripts.search;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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
public class AUT_AG_013 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_012()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to perform Aspect search "
				+ "for a document through Advanced search");
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
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
	
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		
		if(sitesPage.Checkdocument(fileName)){
			
		}else {
			
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			
			myFiles.createFile(fileDetails);
			docDetailsPage.performManageAspectsDocAction();
			docDetailsPage.addAspectsAndApllyChangesToAFile();
			docDetailsPage.performEditPropertiesDocAction();
			docDetailsPage.enterDataAndSaveIntoEditProperties();
			
		}
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToAdvSearch();
		
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.inputAspectAdvSearch();
		appSearchPg.clickSearch();
		
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		appSearchPgTest.verifyAspectSearchResults();
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}