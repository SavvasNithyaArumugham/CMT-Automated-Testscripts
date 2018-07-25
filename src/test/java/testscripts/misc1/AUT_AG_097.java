package testscripts.misc1;

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
public class AUT_AG_097 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_087()
	{
		testParameters.setCurrentTestDescription("To check user is able to navigate to user dashboard page using User dashboard option under user menu if  a site document library page is set as default homepage");
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
		
		//homePage.navigateToHomePage();
		
		String Option = dataTable.getData("Home", "TasksFilterDropdownValue");
		homePage.UserMenucommonMethod(Option);
		
		homePage.navigateToHomePage();
		
		homePage.validatepagetitle(sitename);
		
		sitesPage.siteFinder(sitename);
		
		String Option1 = dataTable.getData("Home", "DashletName");
		homePage.UserMenucommonMethod(Option1);
		
		homePage.validatepagetitle(dashboardname);
		
		homePage.UserMenucommonMethod(actualHome);
				
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}