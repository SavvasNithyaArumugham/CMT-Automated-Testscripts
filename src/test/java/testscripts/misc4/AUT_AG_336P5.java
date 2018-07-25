package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_336P5 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc4_035()
	{
		testParameters.setCurrentTestDescription("Verify user is not getting activity feeds for any action performed for the site after disabling the feeds - Verify Activity Feed for Site Member");
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
		String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");		
		//Enable activity feed and validate
				homePage.navigateToSitesTab();
				homePage.navigateToMySitesPage();
				
				sitesPage.enableOrDisableFeedForSite(siteName);
				
				homePage.navigateToSitesTab();
				sitesPage.openSiteFromRecentSites(siteName);
				sitesPage.enterIntoDocumentLibrary();

				myFiles.uploadFileInMyFilesPage(filePath, fileName);
				
				sitesPage.enterIntoSiteDashboard();
				UIHelper.waitForLong(driver);
				homePageTest.verifyActivityFeedForSiteMember(siteName);
				
				 

	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
