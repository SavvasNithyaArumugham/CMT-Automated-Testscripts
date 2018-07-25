package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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

public class ALFDEPLOY_2236_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_007() {
		testParameters.setCurrentTestDescription(
				"1. Following the creation of a program object can see that the External ID metadata is populated with a value starting with an 'L' followed by one or more digits"
						+ "<br> 2. External ID metadata should be  populated for program object");

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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);

		homePageObj.navigateToSitesTab();

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectFileName = dataTable.getData("MyFiles", "FileName");
		String propName = dataTable.getData("Document_Details", "DocProperties");
		//String CollectionObjectAdditionalData = dataTable.getData("MyFiles", "CollectionObjectAdditionalData");

		String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		String collectionObjectData = dataTable.getData("MyFiles", "CollectionObjectAdditionalData");

		String[] collectionObjectDataArray = collectionObjectData.split(",");

		// Navigate to Document Library page of the site.

		sitesPage.createSite(sourceSiteName, "Yes");
		// sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		// Navigate inside 'Programs' folder
		// Hover over the program and from the right side menu select 'View
		// Details' option.

		myFiles.openCreatedFolder(folderNames[0]);

		sitesPage.clickOnViewDetails(objectFileName);
		UIHelper.waitFor(driver);

		// Verify the program Object External Id
		String externalIdBeforeEdit = collectionPg.getFolderPropValue(propName);
		if (externalIdBeforeEdit.contains("L")) {
			report.updateTestLog("Verify 'External ID' metadata",
					"Metadata displayed successfully" + "<br><b> External ID before Edit : </b>" + externalIdBeforeEdit,
					Status.PASS);
		} else {
			report.updateTestLog("Verify 'External ID' metadata", "Metadata not displayed", Status.FAIL);
		}

		// Click on 'Edit Properties' option from 'Folder Actions' menu.

		UIHelper.waitFor(driver);
		docDetailsPageObj.performEditPropertiesDocAction();

		// Edit value of 'External ID' value in Edit Properties
		String editedValue = "";
		if (externalIdBeforeEdit.length() < 10) {
			editedValue = externalIdBeforeEdit.concat("1");
		} else {
			editedValue = externalIdBeforeEdit.substring(0, externalIdBeforeEdit.length() - 4);
		}

		if (collectionPg.editValueInEditProperties(propName, editedValue)) {
			report.updateTestLog("Edit 'External ID' value in Edit Properties",
					"Data Edited successfully" + "<br><b> Value Edited : </b>" + editedValue, Status.PASS);
		} else {
			report.updateTestLog("Edit 'External ID' value in Edit Properties", "Data not Edited", Status.FAIL);
		}

		UIHelper.waitFor(driver);

		/*collectionPg.setProg_Titlevalue(CollectionObjectAdditionalData);

		collectionPg.clickSaveInEditProp();*/
		
		collectionPg.enterBasicDataForCompositeObject(collectionObjectDataArray[0], 
				collectionObjectDataArray[1], collectionObjectDataArray[2], collectionObjectDataArray[3]
						,collectionObjectDataArray[4],collectionObjectDataArray[5],collectionObjectDataArray[6]
								,collectionObjectDataArray[7]);
		collectionPg.clickOnSaveBtnForSubmitCreateObjectData();

		// Edited value of 'External ID' value in Edit Properties

		String externalIdAfterEdit = collectionPg.getFolderPropValue(propName);
		
		if (editedValue.equalsIgnoreCase(externalIdAfterEdit)) {
			report.updateTestLog("Verify 'External ID' metadata after edit",
					"Metadata Edited successfully" + "<br><b> External ID After Edit : </b>" + externalIdAfterEdit,
					Status.PASS);
		} else {
			report.updateTestLog("Verify 'External ID' metadata after edit", "Metadata not Edited", Status.FAIL);
		}

		UIHelper.waitFor(driver);

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
