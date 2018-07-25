package testscripts.admin;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_025P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_023()
	{
		testParameters.setCurrentTestDescription("Verify that user who is in Group_Admin will able to create new sub group to group - Add a non admin user to Group_Admin");
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
		homePageObj.navigateToAdminTab();
		
		AlfrescoAdminToolsPage appAdminToolsPg = new AlfrescoAdminToolsPage(scriptHelper);
		AlfrescoAdminToolsPageTest appAdminToolsPgTest = new AlfrescoAdminToolsPageTest (scriptHelper);
		String groupName = dataTable.getData("Admin", "GroupName");
		String newGroupName = dataTable.getData("Admin", "EditGroupName");
		
		if(appAdminToolsPg.searchGpAndDelete(groupName)){
			
		} else{
			appAdminToolsPg.createGroup();
		}
		appAdminToolsPg.BrowseGroup(groupName);
		appAdminToolsPg.addUserToExistingGroupwithoutfIden();	
		UIHelper.waitFor(driver);
		appAdminToolsPgTest.VerifyNewUser();
		UIHelper.waitFor(driver);
		if(appAdminToolsPg.searchGpAndDelete(newGroupName)){
			appAdminToolsPg.deleteGroup(newGroupName);
		} 
		
		String subGroup = dataTable.getData("Admin", "SubGroup");
		if(appAdminToolsPg.searchGpAndDelete(subGroup)){
			appAdminToolsPg.deleteGroup(subGroup);
		} 
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}