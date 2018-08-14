package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author ASHOK
 */
public class ALFDEPLOY_2765_TC001 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_030() {
		testParameters
				.setCurrentTestDescription("Can log in as a user who is not in the designated Collections user group, and cannot see Course Planner Relationships in the details view of an object, regardless of Site Type.");

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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");


//		// login
		functionalLibrary.loginAsValidUser(signOnPage);
		
//		// goto site > document lib
	
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
//		// goto site > document lib >Programs > Program object
		sitesPage.documentdetails("Programs");
		sitesPage.documentdetails("Program");
		
		try {
			boolean isDisplayedEditCollectionOption = collectionPg.checkEditCollectionOptionInCollectionPage();

			if (!isDisplayedEditCollectionOption) {
				report.updateTestLog("Verify 'Edit Collection' Option for non Collections user group",
						"'Edit Collection' option should not be displayed in Collections UI screen", Status.PASS);
			} else {
				report.updateTestLog("Verify 'Edit Collection' Option for non Collections user group",
						"'Edit Collection' option should not be displayed in Collections UI screen", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}