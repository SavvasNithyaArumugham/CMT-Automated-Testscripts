package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_289P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3()
	{
		testParameters.setCurrentTestDescription("Verify that Site Coordinator is able to perform Manage Permissions. - Accept Site as Coordinator and Remove 'Inherit Permissions'");
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
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		homePage.navigateToTasksTab();
		taskPage.navigateToMyTasksMenu();
		taskPage.filterWFtasks();
		taskPage.waitForTaskWithoutDueDateHeader();
		boolean flag = taskPage.checkCreatedOrAssignedTask(siteNameValue);
		if(flag){
			taskPage.openSiteTask(siteNameValue);
			taskPage.acceptSiteInvitation(siteNameValue);
		}
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreOptionLink(fileName);
				
		docDetailsPage.removeInheritPermissions();
		
		if(!docLibPage.isFileIsAvailable(fileName)){
			report.updateTestLog(
					"Verify the file is displayed after removed 'Inherit Permissions'",
					"File not displayed'"
							+ "</br><b>Verified File Name:</b> " + fileName, Status.PASS);
		}else{
			report.updateTestLog(
					"Verify the file is displayed after removed 'Inherit Permissions'",
					"File displayed'"
							+ "</br><b>Verified File Name:</b> " + fileName, Status.FAIL);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}