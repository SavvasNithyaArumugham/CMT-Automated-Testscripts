package testscripts.collections1;

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

public class ALFDEPLOY_2210_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_005() {
		testParameters.setCurrentTestDescription(
				"1.when in the collection UI can see custom icons in the create menu for every content type object (Program, Course, Sequence Object, Container, Learning Bundle, Content Object, Composite Object )"
						+ "<br> 2. When in the document library can see custom icons in the list view for every container type object (Program, Course, Sequence Object, Container, Learning Bundle, Content Object and Composite Object)"
						+ "<br> 3. When in the collection UI can see custom icons in the list view for every container type object (Program, Course, Sequence Object, Container, Learning Bundle, Content Object and Composite Object) "
						+ "<br> 4. When in the collection UI can see custom icons in the left tree navigation for every container type object (Program, Course, Sequence Object, Container, Learning Bundle, Content Object and Composite Object)"
						+ "<br> 5. In collection view can click on create and see a dropdown with 'composite object'"
						+ "<br> 6. Can click on the 'composite object' to see a create form pop up with all the metadata/associations as defined in data dictionary"
						+ "<br> 7. Can define the metadata/associations and click save to see a new composite object created"
						+ "<br> 8. In collection view can click on create and see a dropdown with 'content object'"
						+ "<br> 9. Can click on the 'content object' to see a create form pop up with all the metadata/associations as defined in data dictionary"
						+ "<br> 10. Can define the metadata/associations and click save to see a new Content object created"
						+ "<br> 11. Indicate External URL indicator when URL field is populated"
						+ "<br> 12. In collection view can click on create and see a dropdown with 'Container'"
						+ "<br> 13. Can click on the 'Container' to see a create form pop up with all the metadata/associations as defined in data dictionary"
						+ "<br> 14. Can define the metadata/associations and click save to see a new Container object created"
						+ "<br> 15. In collection view can click on create and see a dropdown with 'sequence object'"
						+ "<br> 16. Can click on the 'sequence object' to see a create form pop up with all the metadata/associations as defined in data dictionary"
						+ "<br> 17. Can define the metadata/associations and click save to see a new sequence object object created"
						+ "<br> 18. In collection view can click on create and see a dropdown with 'Course'"
						+ "<br> 19. Can click on the 'Course' to see a create form pop up with all the metadata/associations as defined in data dictionary"
						+ "<br> 20. Can define the metadata/associations and click save to see a new Course object object created"
						+ "<br> 21. Can select a new object from the 'Create...' menu within Collections"
						

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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String expectedCreateMenuItems = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");

		String collectionObjectData = dataTable.getData("MyFiles", "CollectionObjectAdditionalData");
		String[] collectionObjectDataArray = collectionObjectData.split(",");
		String objectFileName = dataTable.getData("MyFiles", "FileName");
		String fieldDataForQuickEdit = dataTable.getData("MyFiles", "FieldDataForQuickEdit");

		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		// Select Sites > Create Site from top black menu.
		homePageObj.navigateToSitesTab();

		// From the site Type dropdown select 'Collection Site'.
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		// Navigate to document library and click on a program>Program Object
		sitesPage.enterIntoDocumentLibrary();
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

		// Verify the Image icon for created objects

		for (String collectionObjectNameAndType : collectionObjNamesAndTypeList) {

			if (collectionObjectNameAndType.contains("-")) {
				String splittedCollectionObjectNameAndType[] = collectionObjectNameAndType.split("-");

				if (splittedCollectionObjectNameAndType != null && splittedCollectionObjectNameAndType.length > 1) {
					String collectionObjectType = splittedCollectionObjectNameAndType[0];
					String collectionObjectName = splittedCollectionObjectNameAndType[1];
					collectionPg.moveToFirstInCollectionPage();
					UIHelper.waitFor(driver);
					collectionPgTest.verifyImageIconForCreatedObjects(collectionObjectName, collectionObjectType);

				}
			}
		}

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

		// Enter URL Data for Content object to verify External URL Image
		collectionPg.clickOnBrowseActionsInCollectionUI(objectFileName, "Edit Properties");

		collectionPg.enterCollectionObjectData(fieldDataForQuickEdit);
		collectionPg.clickOnSaveBtnForSubmitCreateObjectData();
		UIHelper.pageRefresh(driver);

		if (collectionPg.isURLImageDisplayed(objectFileName)) {
			report.updateTestLog("Verify the External URL indicator",
					"Indicator displayed successfully" + "<br><b> Object File Name : </b>" + objectFileName,
					Status.PASS);
		} else {
			report.updateTestLog("Verify the External URL indicator",
					"Indicator not displayed" + "<br><b> Object File Name : </b>" + objectFileName, Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
