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
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_011P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_010()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to Add Group to group");
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
		String groupName = dataTable.getData("Admin", "GroupName");
		if(appAdminToolsPg.searchGpAndDelete(groupName)){
			
		} else{
			appAdminToolsPg.createGroup();
		}
		appAdminToolsPg.BrowseGroup(groupName);
		appAdminToolsPg.addSubGpToGroup();
				
		AlfrescoAdminToolsPageTest appAdminToolsPgTest = new AlfrescoAdminToolsPageTest (scriptHelper);
		appAdminToolsPgTest.VerifyAddSubGroup();
		
		String SubgroupName = dataTable.getData("Admin", "SubGroup");
		appAdminToolsPg.searchGroup(SubgroupName);
		appAdminToolsPg.deleteGroup(SubgroupName);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}