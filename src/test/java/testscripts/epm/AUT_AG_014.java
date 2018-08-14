package testscripts.epm;


import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_014 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_014()
	{
		testParameters.setCurrentTestDescription("Verify the user is able to perform search operation for multiple times for a single folder by selecting a folder once.");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
	
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
//		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");

	sitesPage.siteFinder(sourceSiteName);
		
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		
		
		String dashletNme = dataTable.getData("Home", "DashletName");
		String dashlet = dataTable.getData("Home", "DashBoardName");
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashlet))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
		
		siteDashboard.navigateToSiteDashboard();
		
		for (String folderName : folderNamesList) {
			System.out.println(folderName);
			siteDashboard.selectFoldersInEPMDashlet(folderName);
			String searchContent=dataTable.getData("EPM", "ListTitlesWithFieldValue");
			String property=dataTable.getData("EPM", "SearchContentType");
			siteDashboard.enterSearchCriTeria(searchContent, property);
			siteDashboard.clickOnFindTab();
			siteDashboard.enterSearchCriTeria(searchContent, property);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}