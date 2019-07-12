package testscripts.collections10;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1766_2_3_4_5_P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_1766_1() {
		testParameters.setCurrentTestDescription(
			"Confirm on clicking Edit properties for other than course object such as Container/Sequence/Learning Bundle/Content Object/Composite Object form collection UI via More options >Edit properties , the new dynamic course properties such as Level automation,Product Configuration and Program standards are not getting displayed."
				);
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
		
				// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		// Select Sites > Create Site from top black menu.
		homePageObj.navigateToSitesTab();

		// From the site Type dropdown select 'Collection Site'.
		sitesPage.createSite(siteNameValue, "No");
		
		

		// Navigate to document library and click on a program>Program Object
		sitesPage.enterIntoDocumentLibrary();
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
		String objectFileName = dataTable.getData("MyFiles", "FileName");
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();

		// Verify custom icons by creating new collections objects
		collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);

		// Get the Collections object
		ArrayList<String> collectionObjNamesAndTypeList = collectionPgTest
				.getCreateCollectionObjectNamesWithObjectType(createObjectData);
		// verify all collections object are created
		UIHelper.waitFor(driver);
		collectionPg.moveToFirstInCollectionPage();

		if (collectionPg.isCreatedObjectAvailable(objectFileName)) {
			report.updateTestLog("Verify the object created",
					"Object created successfully" + "<br><b> Object File Name : </b>" + objectFileName, Status.PASS);
		} else {
			report.updateTestLog("Verify the object created",
					"Object not created" + "<br><b> Object File Name : </b>" + objectFileName, Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
