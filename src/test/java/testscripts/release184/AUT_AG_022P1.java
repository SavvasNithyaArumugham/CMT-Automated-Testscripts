package testscripts.release184;


import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_022P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void release184_01()
	{
		testParameters.setCurrentTestDescription("To check if the edit properties page is set to User Dashboard page when user clicks on Use My Dashboard option under user menu<br> Part1: Set Edit Properties as home page");
		
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
        AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		
		//Login
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		String dashboardname = dataTable.getData("Home", "DashBoardName");
		
		//Validate Actual Home Page
		homePage.validatepagetitle(dashboardname);
		
		// Navigate to site and upload file
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.uploadFile(filePath, fileName);

		myFilesTestObj.verifyUploadedFile(fileName, "");
		
		myFiles.openUploadedOrCreatedFile(fileName, "");
		
		
		//Click Edit Properties in Doc details Page And Setting as Home Page
		docDetailsPageObj.performEditPropertiesDocAction();
		
		String Option = dataTable.getData("Home", "TasksFilterDropdownValue");
		homePage.UserMenucommonMethod(Option);//site names's as option
		}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}