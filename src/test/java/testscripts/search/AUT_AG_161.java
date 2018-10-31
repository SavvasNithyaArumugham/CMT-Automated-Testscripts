package testscripts.search;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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
public class AUT_AG_161 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_037()
	{
		testParameters.setCurrentTestDescription("1. Verify relevant search results are returned from repository when keyword having alphabets are searched using ''Search all content'' option. <br>"
				+"2. Verify relevant search results are returned from repository when keyword having numbers are searched using ''Search all content'' option .<br>"+
				"ALFDEPLOY-3967_Verify relevant search results are returned from repository when keyword starting with string are searched using Search all content option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from repository when keyword containing underscore without extension are searched using Search all content option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from the current site when keyword having underscore are searched using Search in site My Site option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from the current site when keyword with extension are searched using Search in site My Site option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from the current site when keyword containing underscore without extension are searched using Search in site My Site option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from repository when keyword having underscore are searched using Search all content option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from repository when keyword starting with underscore are searched using Search all content option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from repository when keyword with extension are searched using Search all content option.<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from repository when keyword without extension are searched using Search all content option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned when file or foldername containing 255 characters is searched using Search all content option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from the current site when keyword starting with underscore are searched using Search in site My Site option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from the current site when keyword ending with underscore are searched using Search in site My Site option .<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from the current site when keyword starting with string are searched using Search in site My Site option.<br>" + 
				"ALFDEPLOY-3967_Verify relevant search results are returned from the current site when keyword ending with string are searched using Search in site My Site option .<br>" );
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String siteValue = dataTable.getData("Sites", "SiteToSelect");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String searchQuery = dataTable.getData("Search", "Query");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		functionalLibrary.loginAsValidUser(signOnPage);
		
		
		sitesPage.siteFinder(siteValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFiles.createFile(fileDetails);
		//Modified as part of NALS
		/*sitesPage.siteFinder(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFiles.createFile(fileDetails);*/
		
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		if (searchQuery.contains(",")) {
			String splittedFileDetailas[] = searchQuery.split(",");
			for (String fileValues : splittedFileDetailas) {
		
		
		homePageObj.IntelliSensesearch(fileValues);
		homePageObj.searchallcontent(fileValues, siteassertValue);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);

		
			}
			//Modified as part of NALS
			/*for (String fileValues : splittedFileDetailas) {
				
				
				homePageObj.IntelliSensesearch(fileValues);
				homePageObj.searchsitecontent(fileValues, siteassertValue);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);

				
					}*/
			
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}