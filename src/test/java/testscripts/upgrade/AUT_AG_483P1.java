package testscripts.upgrade;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_483P1 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_483P1(){
		testParameters.setCurrentTestDescription("Using share admin tools,Verify Administrator is able to delete a site from a single admin screen,if he is not a site manager and site is Private - Create Site");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		//if(!(sitesPage.siteFinderOption(siteName))){
			AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
			homePage.navigateToSitesTab();
			sitesPage.createSite(siteName, "Yes");
		//}
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
