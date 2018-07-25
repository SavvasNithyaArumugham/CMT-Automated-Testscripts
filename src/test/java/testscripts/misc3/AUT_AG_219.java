package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMaintenanceActivitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_219 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3()
	{
		testParameters.setCurrentTestDescription("User should be notified with error message, each time login into alfresco,if any particular page set as homepage is unavailable");
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
		String actualHome = dataTable.getData("Home", "Status");
		homePage.UserMenucommonMethod(actualHome);
		homePage.navigateToHomePage();
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sitename1 = dataTable.getData("Sites", "SiteName");
		String sitename2 = dataTable.getData("Sites", "Role");
	
		String dashboardname = dataTable.getData("Home", "DashBoardName");
		homePage.validatepagetitle(dashboardname);
		
		
		homePage.navigateToMyFilesTab();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//Upload
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		
		String Option = dataTable.getData("Home", "TasksFilterDropdownValue");
		homePage.UserMenucommonMethod(actualHome);
		
		homePage.navigateToHomePage();
		
		homePage.validatepagetitle("Document Details");
		
		AlfrescoMaintenanceActivitesPage maintenance = new AlfrescoMaintenanceActivitesPage(scriptHelper);
		homePage.navigateToMyFilesTab();
		try{
			docLibPg.deleteAllFilesAndFolders();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		homePage.navigateToHomePage();
		
	//	sitesPage.siteFinder(sitename2);
		
		//String Option1 = dataTable.getData("Home", "DashletName");
	//	homePage.UserMenucommonMethod(Option);
		
	//	homePage.navigateToHomePage();
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			if(UIHelper.checkForAnElementbyXpath(driver, maintenance.errorpage)){
				UIHelper.highlightElement(driver, maintenance.errorpage);
				report.updateTestLog("Verify Error page", 
						"Expected Error Page displayed successfully.",
						Status.PASS);
			}else{
				report.updateTestLog("Verify Error page", 
						"Expected Error Page not displayed .",
						Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Verify Error page", 
					"Expected Error Page not displayed .",
					Status.FAIL);
		}

		homePage.UserMenucommonMethod(actualHome);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}