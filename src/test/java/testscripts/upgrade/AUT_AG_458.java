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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_458 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC458()
	{
		testParameters.setCurrentTestDescription("Verify that the  number of search results populated after filter type matches with the number of results displayed.");
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
		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);
			
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			homePageObj.navigateToAdvSearch();

			AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
			AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
			appSearchPg.inputFileNameAdvSearch();
			appSearchPg.clickSearch();
			
			String filter = dataTable.getData("Search", "Actions");
			String value = dataTable.getData("Search", "AssetType");
			String type = dataTable.getData("Search", "Title");
			appSearchPg.selectfilters(filter,value,type);
			String Actualcount = appSearchPgTest.verifysearchcount();
			
			/*appSearchPgTest.verifyFileTypeFilter();*/
			
			String finalselectFilterValXpath = appSearchPg.selectFilterValXpath.replace("FILTER",
					filter).replace("CRAFT",value);
			
			if(UIHelper.findAnElementbyXpath(driver, finalselectFilterValXpath).getText().equals(Actualcount)){
				report.updateTestLog("Verify search count based on filter",
						 "Count verified before and after search result." 
								 +"<br><b>Before Filter : </b> "+Actualcount
				+"<br><b>After Filter : </b> "+ UIHelper.findAnElementbyXpath(driver, finalselectFilterValXpath).getText()
				, Status.PASS);
			} else
			{
				report.updateTestLog("Verify search count based on filter",
						 "Count before and after search result are not same." 
								 +"<br><b>Before Filter : </b> "+Actualcount
				+"<br><b>After Filter : </b> "+ UIHelper.findAnElementbyXpath(driver, finalselectFilterValXpath).getText()
				, Status.FAIL);
			}
		} catch (Exception e) {
			
			report.updateTestLog("Verify search count based on filter",
					 "Verification failed.Element not found. " 
					, Status.FAIL);
		}
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}