package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2237_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void COLLECTIONS_015() {
		testParameters
				.setCurrentTestDescription("Can see the following custom Pearson Schools content types as selectable items under the 'Create…' menu within Collections UI (note that another story,  if implemented, will make these contextual based on heirarchy rules): Program, Course, …");
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
	public void executeTest() 	{
		
		//Login into Alfresco Application
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		
		//Navigate to site tab to create new site
		homePageObj.navigateToSitesTab();
		
		//Navigate to collections site
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		//	sitesPage.createSite(siteNameValue, "Yes");
		String siteName=sitesPage.getCreatedSiteName();
		
		sitesPage.openSiteFromRecentSites(siteName);
		
		
		//Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		sitesPage.documentdetails(folderNames[0]);
		sitesPage.documentdetails(folderNames[1]);
		String expectedCreateMenuItems = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		
		
		//Navigate to Programs->Program object and click "Edit Collection" button
		collectionPg.clickOnEditCollectionButton();
		
		//Navigate to collection UI and click Create button to Verify whether collection object are getting appeared or not. 
		collectionPg.clickOnCreateButton();
		
		collectionPgTest.verifyCreateMenuItems(expectedCreateMenuItems);
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
