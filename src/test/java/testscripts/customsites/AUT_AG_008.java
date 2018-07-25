package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_008 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_008()
	{
		testParameters.setCurrentTestDescription("1. Verify user is able to see folder named with Program Site present under "
				+"'Program Library'  on left side of the  Component Site"
				+"<br>2. Verify user is able to see folder named with component Site  present under 'Library' on left side of the Program Site"
				+"<br>3. Verify user is navigated to Componet Document Library on clicking folder named with component Site present under 'Library' on left side of the Program Site"
				+"<br>4. Verify user is navigated to Program's Document Library on clicking folder named with  component Site present under "
				+ "'Program Library' on left side of the Program Site ");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);

		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String siteValue = dataTable.getData("Sites", "SiteToSelect");
		String siteDetails = dataTable.getData("Sites", "CreateMultipleSites");
		
		sitesPage.createMultipleSitesWithoutTimeStamp(siteDetails);
		
		//1st TC validation
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPageTest.verifyProgramLibFolder(siteValue);
		
		//2nd TC validation
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPageTest.verifyProgramFolder(siteassertValue);
		
		//3rd TC validation
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.clickProgramCompFolder(siteValue);
		docLibPageTest.verifyProgramCompDocLib(siteValue);
		
		//4th TC validation
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.clickProgramFolder(siteValue);
		docLibPageTest.verifyProgramDocLib(siteValue);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}