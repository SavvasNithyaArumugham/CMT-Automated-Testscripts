package testscripts.admin;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteFinderPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_037 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_035(){
		testParameters.setCurrentTestDescription("Verify user with role admin can delete sites even if user is not having manager permissions - Part 2");
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
		if(homePage.isAdminToolsAvailable()){
			homePage.navigateToAdminTab();
			String adminToolsOption = dataTable.getData("Admin", "AdminToolsOptions");
			AlfrescoAdminToolsPage adminToolsPage = new AlfrescoAdminToolsPage(scriptHelper);
			adminToolsPage.navigateToAdminToolsOptionsLink(adminToolsOption);
			
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			AlfrescoSiteFinderPageTest siteFinderPageTest = new AlfrescoSiteFinderPageTest(scriptHelper);
			siteFinderPageTest.verifySiteManagerPageDisplayed(adminToolsOption);
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
