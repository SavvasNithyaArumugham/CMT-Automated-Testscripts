package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_292P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_067()
	{
		testParameters.setCurrentTestDescription("Verify that site coordinator role name is visible as 'Site Coordinator' to all below locations: - 1. Manage Permission window.2. Add any user and set user Role as 'Site Coordinator' - Part 2:Manage permissions");
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
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docLibPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");

		String siteName=sitesPage.getCreatedSiteName();				
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.documentlib();
					
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			myFiles.createFile(fileDetails);		
			docLibPg.selectManagePermissions();		        
	        sitesPage.clickOnAddUserGroupButton();	        
	        sitesPage.clickOnUserRoleBtn();	        
	        sitesPage.writeUserRoleDetails();	        
	        sitesPage.clickSaveButtonInManagePermission();
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}