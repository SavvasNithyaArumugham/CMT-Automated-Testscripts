package testscripts.customsites;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_009 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_009()
	{
		testParameters.setCurrentTestDescription("1. Verify the display of program site and it's component sites in 'Component Navigator' dashlet on program site dashboard"
				+"<br>2. Verify that the user is able to navigate to a program or it's component sites by clicking on the link from 'Component Navigator' dashlet"
				+"<br>3. Verify the users of the Program Site is able to access all contents inside folder named with the component's sitename in its Document Library"
				+"<br>4. Verify the entire Document Library of the Component Site appears (named with the component's sitename) is visible as a subfolder within the Program's Document Library");
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
		AlfrescoSitesPageTest sitesPageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoSitesDashboardPage siteDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest siteDashboardPgTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToSitesTab();
		
		String siteDetails1 = dataTable.getData("Sites", "CreateMultipleSites");
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails1);
		
		String createdProgSite=sitesPageTest.getCreatedSiteName();
        
		String siteDetails2 = dataTable.getData("Sites", "CreateMultipleSites2");
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails2);
		
		String createdProgCompSite1=sitesPageTest.getCreatedSiteName();
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(createdProgSite);
		
		String siteDetails3 = dataTable.getData("Sites", "CreateMultipleSites3");
		
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails3);
		
		String createdProgCompSite2=sitesPageTest.getCreatedSiteName();
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(createdProgSite);
		
		siteDashboardPgTest.verifySiteInComponentNavigator(createdProgSite, createdProgCompSite1, createdProgCompSite2);
		
		siteDashboardPg.clickOnSiteInComponentNavigatorDashlet(createdProgCompSite1);
		siteDashboardPgTest.verifyUserNavigatedToProgOrComponentSite(createdProgCompSite1);
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(createdProgCompSite1);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		
		ArrayList<String> createdFileNames = myFilesTestObj.getCreatedFileNames(fileDetails);
		
		for(String fileName:createdFileNames)
		{
			myFiles.createFile(fileDetails);
			
			docDetailsPageTest.verifyCreatedFile(fileName, "");
			
			sitesPage.openSiteFromRecentSites(createdProgSite);
			
			sitesPage.enterIntoDocumentLibrary();
			
			myFiles.openFolder(createdProgCompSite1);
			
			myFilesTestObj.validateFilesUndersideComponentSiteInDocLib(fileName);
		}
		
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(createdProgSite);
		sitesPage.enterIntoDocumentLibrary();
		
		myFilesTestObj.verifyProgramComponentSiteFolderUnderProgramSiteDocLibPage(createdProgCompSite1);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}