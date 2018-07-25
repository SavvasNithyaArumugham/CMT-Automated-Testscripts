package testscripts.upgrade;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class AUT_AG_461 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC461()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to perform Filtered  Search based on creation time.");
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
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
	
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		if(sitesPage.Checkdocument(fileName)){
			appSearchPg.deletedocument(fileName);
			//sitesPage.documentdetails(fileName);
			
		}else {
			
		}
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		myFiles.createFile(fileDetails);
		
		homePageObj.navigateToAdvSearch();

	
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		
		String filter = dataTable.getData("Search", "Actions");
		String value = dataTable.getData("Search", "AssetType");
		String type = dataTable.getData("Search", "Title");
		appSearchPg.selectfilters(filter,value,type);
		
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		String strDate = sdf.format(date);
		appSearchPgTest.verifySearchFilter(strDate);
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}