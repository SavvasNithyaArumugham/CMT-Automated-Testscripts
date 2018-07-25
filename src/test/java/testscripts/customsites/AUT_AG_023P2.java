package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMaintenanceActivitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * @author 516188
 */
public class AUT_AG_023P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_008()
	{
		testParameters.setCurrentTestDescription("To verify that Site Manager is able to approve the request to Join the moderate site<br> Part2: Another User request to Join Site");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName=sitesPage.getCreatedSiteName();
		//sitesPage.siteFinder(siteName);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
	
	
		homePage.navigateToSitesTab();
		homePage.navigateToSiteFinder();
		sitesPage.sitefinderoption("Join",siteName);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}