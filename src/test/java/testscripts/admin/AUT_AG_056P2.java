package testscripts.admin;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteFinderPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteFinderPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_056P2 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_052(){
		testParameters.setCurrentTestDescription("Using share admin tools,Verify Administrator is able to delete a site from a single admin screen,if he is not a site manager and site is Private - Delete a Private Site");
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
		AlfrescoSiteFinderPage siteFinderPage = new AlfrescoSiteFinderPage(scriptHelper);
		String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
		String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(homePage.isAdminToolsAvailable()){
			homePage.navigateToAdminTab();
			String adminToolsOption = dataTable.getData("Admin", "AdminToolsOptions");
			AlfrescoAdminToolsPage adminToolsPage = new AlfrescoAdminToolsPage(scriptHelper);
			adminToolsPage.navigateToAdminToolsOptionsLink(adminToolsOption);
			
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			siteFinderPage.moveNextPageInSiteManager();
			UIHelper.waitFor(driver);
			siteFinderPage.moveBackPageInSiteManager();
			UIHelper.waitFor(driver);
			
			siteFinderPage.deleteSiteInSiteManager(siteName);
			
			UIHelper.waitFor(driver);	
			
			AlfrescoSiteFinderPageTest siteFinderPageTest = new AlfrescoSiteFinderPageTest(scriptHelper);
			siteFinderPageTest.verifyDeletedSite(siteName);
			
		}else{
			report.updateTestLog("Admin Tools Option not available",
					"User dont have Admin rights", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
