package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;



import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;

import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;

import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2197_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_002() {
		testParameters
				.setCurrentTestDescription("Can see the relationship viewer in the document/folder details page of every content type globally");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		
		// Log in Pearson Schools project		
		functionalLibrary.loginAsValidUser(signOnPage);

		// Select Sites > Create Site from top black menu.
		homePageObj.navigateToSitesTab();

		// From the site Type dropdown select 'Collection Site'.
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		// "Navigate to document library
		sitesPage.enterIntoDocumentLibrary();
		
		
		// Navigate to every document/folder details page and See that the relationship viewer is available
		collectionPg.verifyRelationshipViewerOnAllObjectsInShareUI();
		
	
		
	

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
