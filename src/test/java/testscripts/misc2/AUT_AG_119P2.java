package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_119P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_015()
	{
		testParameters.setCurrentTestDescription("To verify that a record Is displayed in the 'My Activity' dashlet, when a user leaves the site - <br> Part 2: User leaves the site");
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

		/*String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
		String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/		
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		//Added as part of NALS Starts 
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToSitesTab();
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteName);
		//Added as part of NALS Ends
		AlfrescoSitesDashboardPage siteDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		siteDashboardPage.toLeaveASite(siteName);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
