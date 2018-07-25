package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_007 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC227()
	{
		testParameters.setCurrentTestDescription("Verify the display of program component sites under document library, created under a program site");
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
		AlfrescoDocumentLibPageTest docLibPgtest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
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
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPgtest.verifyLibraryFoldersForSiteInDocLibPage(createdProgCompSite1, createdProgCompSite2);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}