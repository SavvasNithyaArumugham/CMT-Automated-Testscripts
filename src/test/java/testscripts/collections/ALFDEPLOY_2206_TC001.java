package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2206_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_008() {
		testParameters
				.setCurrentTestDescription("As a user, I want to create a new 'Collection' Site from a template, so that bucket folders are consistent and configured.");
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

		
		
		// Log in Pearson Schools project

				AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
				AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
				AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
				AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

				String sourceSiteName = dataTable.getData("Sites", "SiteName");
				String folderDetails = dataTable.getData("MyFiles", "CreateFolder");


				// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);

				// Select Sites > Create Site from top black menu.
				homePageObj.navigateToSitesTab();

				// From the site Type dropdown select 'Collection Site'.
				sitesPage.openSiteFromRecentSites(sourceSiteName);

				// Navigate to document library and click on a program>Program Object
				sitesPage.enterIntoDocumentLibrary();
				collectionPg.getFoldersFromLeftTreeInShareUi();
				myFilesTestObj.verifyPredefinedFolder(folderDetails);

//				- Programs 
//				- Courses 
//				- Sequence Objects 
//				- Container 
//				- Learning Bundles 
//				- Content Objects 
//				- Composite Objects 
//				- Assets 
//				- Data Exports 
//				- Project files"
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
