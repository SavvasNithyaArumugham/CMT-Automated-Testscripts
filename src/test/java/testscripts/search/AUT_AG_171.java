package testscripts.search;

import java.io.File;

import org.apache.commons.io.FileUtils;
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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_171 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_037()
	{
		testParameters.setCurrentTestDescription("1.Verify relevant search results are returned from repository when keyword having Alphanumeric and Special characters are searched using ''Search all content'' option . <br>+"
				+ "2.Verify relevant search results are returned from the current site when keyword having Alphanumeric and Special characters are searched using 'Search in site My Site option "
				);
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileNme = dataTable.getData("MyFiles", "FileName");
		String searchQuery = dataTable.getData("Search", "Query");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		functionalLibrary.loginAsValidUser(signOnPage);
		
		UIHelper.waitFor(driver);
		sitesPage.siteFinder(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		//myFiles.createFile(fileDetails);
		
	
		if (sitesPage.Checkdocument(fileNme)) {

		} else {

			myFiles.createFile(fileDetails);
		}
		 report.updateTestLog("Check whether Alpba0123456 ~!@#$%^&()_+}{[]=-`.txt plain text file is created" , "Status: "  +"Alpba0123456 ~!@#$%^&()_+}{[]=-`.txt file is created", Status.PASS);
	  
		homePageObj.IntelliSensesearch(searchQuery);
		homePageObj.searchsitecontent(searchQuery, siteassertValue);

		UIHelper.pageRefresh(driver);
		homePageObj.IntelliSensesearch(searchQuery);
		homePageObj.searchallcontent(searchQuery, siteassertValue);
		
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}