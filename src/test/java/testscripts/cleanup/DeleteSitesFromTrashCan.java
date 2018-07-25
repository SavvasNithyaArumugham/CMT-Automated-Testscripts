package testscripts.cleanup;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMaintenanceActivitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class DeleteSitesFromTrashCan extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CLEANUP_002(){
		testParameters.setCurrentTestDescription("To delete Sites from SiteManger and Trash");
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
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMaintenanceActivitesPage maintenance = new AlfrescoMaintenanceActivitesPage(scriptHelper);
		AlfrescoMySitesPage mySites=new AlfrescoMySitesPage(scriptHelper);
		
			
			homePage.navigateToSitesTab();
			homePage.navigateToMySitesPage();
			mySites.navigateToTrashCan();
			maintenance.deleteSitesFromTrash();
					
	}

	@Override
	public void tearDown() {
		
	}

}
