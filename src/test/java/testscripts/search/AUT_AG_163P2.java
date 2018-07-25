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
public class AUT_AG_163P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription
		("ALFDEPLOY-3453_Verify the is a search result when user Search a folder which is having search keyword starting with string through basic search in Private site when user isnot a member of the site.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a file which is having search keyword starting with string through basic search in Private site when user isnot a member of the site.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a folder which is having search keyword starting with string through Advanced searce in Private site when user isnot a member of the site.<br>"+
		"ALFDEPLOY-3453_Verify the is a search result when user Search a file which is having search keyword starting with string through Advanced searce in Private site when user isnot a member of the site.<br>"+
		"ALFDEPLOY-3979_Verfiy download as zip should not work when user try to download the content where the user don’t have access to the private site.<br>");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		
		String file = dataTable.getData("MyFiles", "FileName");
		String folder= dataTable.getData("MyFiles", "Version");

		functionalLibrary.loginAsValidUser(signOnPage);
		searchObj.performSearch();
		
		searchTestObj.verifyNoSearchResults();
		
		homePageObj.navigateToAdvSearch();
		searchObj.inputFileNameAdvSearchparam(file);
		searchObj.clickSearch();
		
		searchTestObj.verifyNoSearchResults();
		
		homePageObj.navigateToAdvSearch();
		
		searchObj.selectLookForFieldOption("Folders");
		searchObj.inputFileNameAdvSearchparam(folder);
		searchObj.clickSearch();
		
		searchTestObj.verifyNoSearchResults();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}