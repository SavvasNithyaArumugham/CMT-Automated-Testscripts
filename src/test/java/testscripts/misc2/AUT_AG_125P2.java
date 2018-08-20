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

public class AUT_AG_125P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_021()
	{
		testParameters.setCurrentTestDescription("To verify recent activities displayed at Top in the 'My Activity' dashlet- Part 2");
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
		
		String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
		/*String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	*/	
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		//Modified as part of NALS Starts 
				AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
				homePage.navigateToSitesTab();
				String siteName = dataTable.getData("Sites", "SiteName");
				sitesPage.openSiteFromRecentSites(siteName);
				//sitesPage.siteFinder(siteName);
				//Modified as part of NALS Ends
		AlfrescoSitesDashboardPage siteDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		siteDashboardPage.toLeaveASite(siteName);	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
