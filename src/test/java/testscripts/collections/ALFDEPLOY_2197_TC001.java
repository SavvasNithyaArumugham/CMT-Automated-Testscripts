package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2197_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_001() {
		testParameters.setCurrentTestDescription(
				"1.Can select Site > Create Site... from top black menu to open the Create New Site dialog (OOTB)"
						+ "<br> 2. Can select 'Collections' from the Site Type dropdown on the Create New Site dialog"
						+ "<br> 3. Can navigate to the Document Library of the new Collection Site, and can see that the following eight plain folders were created"
						+ "<br> 4. When creating a 'collections site', can navigate to the document library and see a new 'Project Files' top level folder"
						+ "<br> 5. Can click on a 'program' in the document library and see the 'edit collection' action"
						+ "<br> 6. When I create a new Program, I can see that it was automatically bucketed (moved) to the Document Library / Programs folder"
						+ "<br> 7. Can see the relationship viewer in the document/folder details page of every content type globally"
						+ "<br> 8. Can see that the new object has an Incoming \"Contained by\" association (primary P/C) to a bucket folder per the bucketing rules (separate story), effectively moving it.");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		// Select Sites > Create Site from top black menu.
		homePageObj.navigateToSitesTab();

		// From the site Type dropdown select 'Collection Site'.
		sitesPage.createSite(sourceSiteName, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		// Navigate to document library
		sitesPage.enterIntoDocumentLibrary();

		// Verify Pre defined folders
		collectionPg.getFoldersFromLeftTreeInShareUi();
		myFilesTestObj.verifyPredefinedFolder(folderDetails);

		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// click on a program>Program Object and then Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();

		// "Navigate to document library
		sitesPage.enterIntoDocumentLibrary();

		// Navigate to every document/folder details page and See that the
		// relationship viewer is available
		//collectionPg.verifyRelationshipViewerOnAllObjectsInShareUI();
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			sitesPage.clickOnViewDetails(folderName);

			if (collectionPg.isRelationshipViewerDisplayed()) {
				report.updateTestLog("Verify Relationship viewer", "Relationship viewer displayed successfully",
						Status.PASS);
				
				sitesPage.enterIntoDocumentLibrary();
			} else {
				report.updateTestLog("Verify Relationship viewer", "Relationship viewer not displayed", Status.FAIL);
			}
		}

	}

	@Override
	public void tearDown() {

	}
}
