package testscripts.admin;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with valid user credentials
 * @author Moshin
 */
public class AUT_AG_030P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_028()
	{
		testParameters.setCurrentTestDescription("Verify that user who is added in user_Admin is able to edit new user accounts from Admin tool page.- Edit user details");
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
		String user= dataTable.getData("UserName", "Username");
		homePageObj.navigateToAdminTab();	
		AlfrescoAdminToolsPage appAdminToolsPg = new AlfrescoAdminToolsPage(scriptHelper);	
		AlfrescoAdminToolsPageTest appAdminToolsPgTest = new AlfrescoAdminToolsPageTest (scriptHelper);	
		appAdminToolsPg.adminOptionsMgmt();
		appAdminToolsPg.createUserAsUserAdmin();
		appAdminToolsPgTest.verifyCreatedUser(user);
		appAdminToolsPg.searchUserAsUserAdmin(user);
		appAdminToolsPg.editUserAsUserAdmin();
		appAdminToolsPgTest.verifyEditedUser();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}