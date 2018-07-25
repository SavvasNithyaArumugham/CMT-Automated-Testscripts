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

public class ALFDEPLOY_2236_TC005 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_011() {
		testParameters.setCurrentTestDescription("Following the creation of a learning bundle can see that the External ID metadata is populated with a value starting with an 'L' followed by one or more digits");
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
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectFileName = dataTable.getData("MyFiles", "FileName");
		String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		String collectionObjectData = dataTable.getData("MyFiles", "CollectionObjectAdditionalData");
		String propName = dataTable.getData("Document_Details", "DocProperties");
		String[] collectionObjectDataArray = collectionObjectData.split(","); 
		
		sitesPage.createSite(sourceSiteName, "Yes");
		//sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		
		if(collectionPg.isCreatedObjectAvailable(objectFileName)){
			collectionPg.clickOnBrowseActionsInCollectionUI(objectFileName, "View Details");
			collectionPg.deleteCollectionObject(objectFileName);
			sitesPage.enterIntoDocumentLibrary();
			
			myFiles.openCreatedFolder(folderNames[0]);
			myFiles.openCreatedFolder(folderNames[1]);
			
			collectionPgTest.verifyEditCollectionOption();
			collectionPg.clickOnEditCollectionButton();
		}
			
		collectionPg.clickOnCreateButton();
		collectionPg.clickOnCreateMenuItem(collectionObjectName);
		UIHelper.waitFor(driver);
		
		collectionPg.enterBasicDataForLearningBundle(collectionObjectDataArray[0], 
				collectionObjectDataArray[1], collectionObjectDataArray[2], collectionObjectDataArray[3]);
		collectionPg.clickOnSaveBtnForSubmitCreateObjectData();
		UIHelper.waitForPageToLoad(driver);
		
		UIHelper.waitFor(driver);
		collectionPg.moveToFirstInCollectionPage();
		
		if(collectionPg.isCreatedObjectAvailable(objectFileName)){
			report.updateTestLog(
					"Verify the object created",
					"Object created successfully"
					+"<br><b> Object File Name : </b>" + objectFileName, Status.PASS);
		}else{
			report.updateTestLog(
					"Verify the object created",
					"Object not created"
					+"<br><b> Object File Name : </b>" + objectFileName, Status.FAIL);
		}
		
		collectionPg.clickOnBrowseActionsInCollectionUI(objectFileName, "View Details");
		UIHelper.waitFor(driver);
		
		String externalIdBeforeEdit = collectionPg.getFolderPropValue(propName);
		if(externalIdBeforeEdit.contains("L")){
			report.updateTestLog(
					"Verify 'External ID' metadata",
					"Metadata displayed successfully"
					+"<br><b> External ID before Edit : </b>" + externalIdBeforeEdit, Status.PASS);
		}else{
			report.updateTestLog(
					"Verify 'External ID' metadata",
					"Metadata not displayed", Status.FAIL);
		}
		
		UIHelper.waitFor(driver);
		docDetailsPageObj.performEditPropertiesDocAction();
		
		String editedValue = "";
		if(externalIdBeforeEdit.length() < 10){
			editedValue = externalIdBeforeEdit.concat("1");
		}else{
			editedValue = externalIdBeforeEdit.substring(0,externalIdBeforeEdit.length()-4);
		}
		
		if(collectionPg.editValueInEditProperties(propName, editedValue)){
			report.updateTestLog(
					"Edit 'External ID' value in Edit Properties",
					"Data Edited successfully"
					+"<br><b> Value Edited : </b>" + editedValue, Status.PASS);
		}else{
			report.updateTestLog(
					"Edit 'External ID' value in Edit Properties",
					"Data not Edited", Status.FAIL);
		}
		
		UIHelper.waitFor(driver);
		collectionPg.clickSaveInEditProp();
		
		String externalIdAfterEdit = collectionPg.getFolderPropValue(propName);
		if(editedValue.equalsIgnoreCase(externalIdAfterEdit)){
			report.updateTestLog(
					"Verify 'External ID' metadata after edit",
					"Metadata Edited successfully"
					+"<br><b> External ID After Edit : </b>" + externalIdAfterEdit, Status.PASS);
		}else{
			report.updateTestLog(
					"Verify 'External ID' metadata after edit",
					"Metadata not Edited", Status.FAIL);
		}
		
		UIHelper.waitFor(driver);
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
