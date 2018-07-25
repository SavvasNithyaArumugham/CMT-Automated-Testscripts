package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteFinderPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_031P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_015()
	{
		testParameters.setCurrentTestDescription("Verify user with role site contributor has no permissions to delete sites of which they are part of - Part 2");
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
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSiteFinderPageTest siteFinderPageTest = new AlfrescoSiteFinderPageTest(scriptHelper);
		//taskPage.acceptInvitationTask();
		
		homePage.navigateToTasksTab();
		taskPage.navigateToMyTasksMenu();
		String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
		String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		taskPage.openCreatedOrAssignedTask(siteName) ;
		taskPage.acceptSiteInvitation(siteName);
		
		homePage.navigateToHomePage();		
		UIHelper.waitFor(driver);
		homePageTest.verifyDeleteOptionInMySiteDashlet(siteName);
		
		UIHelper.waitFor(driver);
		sitesPage.siteFinderOption(siteName);
		UIHelper.waitFor(driver);
		siteFinderPageTest.verifyDeleteOptionInSiteFinder(siteName);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
