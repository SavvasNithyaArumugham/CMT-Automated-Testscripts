package testscripts.search;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_162P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-3979_Verify reviewer cannot perform Download as Zip via selected items from search results page when multiple records are selected");
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
		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");

		
		functionalLibrary.loginAsValidUser(signOnPage);
		searchObj.performSearch();
		searchTestObj.verifyUploadedFileInSearchResults();
		
		
		try {
			String finalChkBoxXpathForSearchResultsItem = searchObj.tempChkBoxXpathForSearchResultsItem
					.replace("CRAFT", fileName);
			
			if(!UIHelper.checkForAnElementbyXpath(driver, finalChkBoxXpathForSearchResultsItem)){
				
				report.updateTestLog(
						"Verify reviewer cannot perform Download as Zip via selected items in Search Results",
						"Reviewer cannot perform Download as Zip via selected items in Search Results",
						Status.PASS);
				
			}else{
				report.updateTestLog(
						"Verify reviewer cannot perform Download as Zip via selected items in Search Results",
						"Reviewer can perform Download as Zip via selected items in Search Results",
						Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog(
					"Verify reviewer cannot perform Download as Zip via selected items in Search Results",
					"Reviewer can perform Download as Zip via selected items in Search Results",
					Status.FAIL);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}