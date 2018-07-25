package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_013P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_013()
	{
		testParameters.setCurrentTestDescription("Verfiy non members of the site is not able to see 'add relationship' for between sites - Verify Add Relationship Option in Dashlet");
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
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(
				scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String dashletNmetoAddTest = dataTable.getData("Home", "DashletName");
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		if(!sitesDashboardPg.isSiteRelationshipDashletAdded()){	
			report.updateTestLog("Verify 'add relationship' option in  '"+dashletNmetoAddTest+"' dashlet by non site user", "'Add Relationship' option not available"
					+ "<br/><b>Dashlet Name : </b>"
					+ dashletNmetoAddTest,
					Status.PASS);
		}else{
			report.updateTestLog("Verify 'add relationship' option in  '"+dashletNmetoAddTest+"' dashlet by non site user", "Able to see 'Add Relationship' option"
					+ "<br/><b>Dashlet Name : </b>"
					+ dashletNmetoAddTest,
					Status.FAIL);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}