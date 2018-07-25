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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2212_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_015() {
		testParameters
				.setCurrentTestDescription("Indicate External URL indicator when URL field is populated");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(
				scriptHelper);
		// AlfrescoMyFilesPageTest myFilesTestObj = new
		// AlfrescoMyFilesPageTest(scriptHelper);
		// AlfrescoDocumentDetailsPage docDetailsPageObj = new
		// AlfrescoDocumentDetailsPage(scriptHelper);

		homePageObj.navigateToSitesTab();

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		// String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String objectFileName = dataTable.getData("MyFiles", "FileName");
		String fieldDataForQuickEdit = dataTable.getData("MyFiles",
				"FieldDataForQuickEdit");
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		String collectionObjectData = dataTable.getData("MyFiles",
				"CollectionObjectAdditionalData");
		String[] collectionObjectDataArray = collectionObjectData.split(",");

		sitesPage.createSite(sourceSiteName, "Yes");
		// sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();

		// ArrayList<String> folderNamesList =
		// myFiles.getFolderNames(folderDetails);

		// for(String folderName:folderNamesList)
		// {
		/*
		 * if(!myFiles.isFileOrFolderDisplayed(folderName)){
		 * myFiles.createFolder(folderDetails);
		 * myFilesTestObj.verifyCreatedFolder(folderDetails);
		 * sitesPage.clickOnMoreSetting(folderName);
		 * sitesPage.clickOnMoreOptionLink(folderName);
		 * docDetailsPageObj.addAspectsAndApllyChangesToAFile(); }
		 * 
		 * myFiles.openCreatedFolder(folderName);
		 * collectionPgTest.verifyEditCollectionOption();
		 * collectionPg.clickOnEditCollectionButton();
		 */

		if (collectionPg.isCreatedObjectAvailable(objectFileName)) {
			collectionPg.clickOnBrowseActionsInCollectionUI(objectFileName,
					"View Details");
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

		/*
		 * if(collectionPg.isCreatedObjectAvailable(objectFileName)){
		 * collectionPg.clickOnBrowseActionsInCollectionUI(objectFileName,
		 * "View Details"); collectionPg.deleteCollectionObject(objectFileName);
		 * sitesPage.enterIntoDocumentLibrary();
		 * 
		 * myFiles.openCreatedFolder(folderName);
		 * collectionPgTest.verifyEditCollectionOption();
		 * collectionPg.clickOnEditCollectionButton(); }
		 * 
		 * collectionPg.clickOnCreateButton();
		 * collectionPg.clickOnCreateMenuItem(collectionObjectName);
		 * UIHelper.waitFor(driver);
		 */

		collectionPg.enterBasicDataForContentObject(
				collectionObjectDataArray[0], collectionObjectDataArray[1],
				collectionObjectDataArray[2], collectionObjectDataArray[3],
				collectionObjectDataArray[4], collectionObjectDataArray[5],
				collectionObjectDataArray[6], collectionObjectDataArray[7]);
		collectionPg.clickOnSaveBtnForSubmitCreateObjectData();
		UIHelper.waitForPageToLoad(driver);

		UIHelper.waitFor(driver);
		collectionPg.moveToFirstInCollectionPage();

		if (collectionPg.isCreatedObjectAvailable(objectFileName)) {
			report.updateTestLog("Verify the object created",
					"Object created successfully"
							+ "<br><b> Object File Name : </b>"
							+ objectFileName, Status.PASS);
		} else {
			report.updateTestLog("Verify the object created",
					"Object not created" + "<br><b> Object File Name : </b>"
							+ objectFileName, Status.FAIL);
		}

		collectionPg.clickOnBrowseActionsInCollectionUI(objectFileName,
				"Edit Properties");
		// collectionPg.clickOnMouseOverMenu(objectFileName, "Edit Properties");

		collectionPg.enterCollectionObjectData(fieldDataForQuickEdit);
		collectionPg.clickOnSaveBtnForSubmitCreateObjectData();
		UIHelper.pageRefresh(driver);

		if (collectionPg.isURLImageDisplayed(objectFileName)) {
			report.updateTestLog("Verify the External URL indicator",
					"Indicator displayed successfully"
							+ "<br><b> Object File Name : </b>"
							+ objectFileName, Status.PASS);
		} else {
			report.updateTestLog("Verify the External URL indicator",
					"Indicator not displayed"
							+ "<br><b> Object File Name : </b>"
							+ objectFileName, Status.FAIL);
		}

		UIHelper.waitFor(driver);
		// }
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
