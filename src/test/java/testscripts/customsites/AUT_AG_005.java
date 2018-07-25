package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_005 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC218()
	{
		testParameters.setCurrentTestDescription("Verify the User is able to set the Properties of Program Dashlet for the Program site through Repository Folder");
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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoRepositoryPage repositoryPg = new AlfrescoRepositoryPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		
		String siteDetails = dataTable.getData("Sites", "CreateMultipleSites");
		
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails);
		
		repositoryPg.navigateToRepositoryPage();
		
		String repositoryFolderName = dataTable.getData("Repository", "RepositoryFolderName");
		myFiles.openFolder(repositoryFolderName);
		
		String createdSiteNameWithtimeStamp = sitesDashboardPageTest.getTheCreatedSiteName();
		String finalCreatedSiteNameWithtimeStamp = createdSiteNameWithtimeStamp.replace("_", "").toLowerCase();
		String browseOption = dataTable.getData("Sites", "BrowseActionName");
		sitesPage.checkSiteFolderUnderRepositoryPage(finalCreatedSiteNameWithtimeStamp);
		sitesPage.clickOnBrowseAction(finalCreatedSiteNameWithtimeStamp, browseOption);
		
		String fieldDetails = dataTable.getData("Sites", "SiteDashletFieldDetails1");
		repositoryPg.enterDataInEditPropForProgramSite(fieldDetails);
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(createdSiteNameWithtimeStamp.trim());
		
		sitesDashboardPage.customizeSiteDashboard();
		
		sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		
		String fieldNames = dataTable.getData("Sites", "SiteDashletFieldNames");
		sitesDashboardPageTest.verifyProgramSiteFields(fieldNames);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}