package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_218 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_009()
	{
		testParameters.setCurrentTestDescription("To check if the user is able to view the page which is set as homepage after clearing cache and logging into Alfresco");
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
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		String actualHome = dataTable.getData("Home", "Status");
		homePage.UserMenucommonMethod(actualHome);
		homePage.navigateToHomePage();
		
		String sitename = dataTable.getData("Sites", "SiteName");
		String dashboardname = dataTable.getData("Home", "DashBoardName");
		homePage.validatepagetitle(dashboardname);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		sitesPage.siteFinder(sitename);
		
		String Option = dataTable.getData("Home", "TasksFilterDropdownValue");
		homePage.UserMenucommonMethod(Option);

		homePage.navigateToHomePage();
		homePage.validatepagetitle(sitename);
		
		homePage.UserMenucommonMethod(actualHome);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}