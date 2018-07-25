package testscripts.search;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
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
public class AUT_AG_075 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_074()
	{
		testParameters.setCurrentTestDescription("Verify the date format should be universal (17 Jun 20 instead 16/17/15 or 17/6/15.)"
				+ "for 'Modified Date' field of the Advance Search");
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
		homePageObj.navigateToAdvSearch();

		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		String searchDate = dataTable.getData("Search", "Actions");
		appSearchPg.inputModifiedStartDate(searchDate);
		appSearchPg.clickSearch();
		
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		appSearchPgTest.verifyAdvSearchResults();
		
		homePageObj.navigateToAdvSearch();
		String searchDate1 = dataTable.getData("Search", "Query");
		appSearchPg.inputModifiedStartDate(searchDate1);
		appSearchPg.clickSearch();
		//appSearchPgTest.verifyAdvSearchResults();
		
		homePageObj.navigateToAdvSearch();
		String searchDate2 = dataTable.getData("Search", "FileName");
		appSearchPg.inputModifiedStartDate(searchDate2);
		appSearchPg.clickSearch();
	//	appSearchPgTest.verifyAdvSearchResults();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}