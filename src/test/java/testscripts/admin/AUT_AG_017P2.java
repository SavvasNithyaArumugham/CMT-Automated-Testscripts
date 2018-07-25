package testscripts.admin;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * Test for login with valid user credentials
 * @author Naresh
 */
public class AUT_AG_017P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_015()
	{
		testParameters.setCurrentTestDescription("Verify that Users in Group_Adminare not able to create sites.- Create a new group");
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
		AlfrescoSitesPage sitesPageObj = new AlfrescoSitesPage(scriptHelper);
		AlfrescoAdminToolsPage appAdminToolsPg = new AlfrescoAdminToolsPage(scriptHelper);	
		String groupName = dataTable.getData("Admin", "GroupName");
		if(sitesPageObj.isDisplayedCreateSite()){
			report.updateTestLog(
					"Check Create Site option available",
					"Create site option displayed",
					Status.FAIL);
		}
		else{
			report.updateTestLog(
					"Check Create Site option available",
					"Create site option is not displayed",
					Status.PASS);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}