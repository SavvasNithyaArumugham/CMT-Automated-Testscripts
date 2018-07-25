package testscripts.cleanup;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMaintenanceActivitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteFinderPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteFinderPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class MyFiles2 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CLEANUP_004(){
		testParameters.setCurrentTestDescription("To delete files from My files");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMaintenanceActivitesPage maintenance = new AlfrescoMaintenanceActivitesPage(scriptHelper);
		homePage.navigateToMyFilesTab();
		docLibPage.selectAllFilesAndFolders();
		docLibPage.deleteAllFilesAndFolders();
		docLibPage.selectAllFilesAndFolders();
		docLibPage.deleteAllFilesAndFolders();
	}

	@Override
	public void tearDown() {
		
	}
	/*
	private void doDeleteSite(String siteName){
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToAdminTab();
		String adminToolsOption = dataTable.getData("Admin", "AdminToolsOptions");
		AlfrescoAdminToolsPage adminToolsPage = new AlfrescoAdminToolsPage(scriptHelper);
		adminToolsPage.navigateToAdminToolsOptionsLink(adminToolsOption);
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);		
		
		AlfrescoSiteFinderPage siteFinderPage = new AlfrescoSiteFinderPage(scriptHelper);
		siteFinderPage.deleteSiteInSiteManager(siteName);
	}
	
	private String generateUniqueSiteName(String siteName){
		
		String uniqueSiteName = "";
		
		String currentDateValue = DateUtil.getCurrentDateAndTime();
		uniqueSiteName = siteName+ currentDateValue.replace(" ", "")
						.replace("/", "_").replace(":", "_");
		return uniqueSiteName;
	}*/

}
