package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_216 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_008(){
		testParameters.setCurrentTestDescription("Verify that username of the Peroot accounts");
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
			
			AlfrescoAdminToolsPageTest adminToolsPageTest = new AlfrescoAdminToolsPageTest(scriptHelper);
			AlfrescoAdminToolsPage appAdminToolsPg = new AlfrescoAdminToolsPage(scriptHelper);

			String userName = dataTable.getData("Admin", "Adduser");
			appAdminToolsPg.searchUser(userName);
			adminToolsPageTest.VerifyUserNameInCaps(userName);
			
			
		}else{
			report.updateTestLog("Admin Tools Option not available",
					"User dont have Admin rights", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {
		
	}
}
