package testscripts.collections;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author ASHOK
 */
public class ALFDEPLOY_2755_TC001 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_032() {
		testParameters
				.setCurrentTestDescription("Following an import of a course CSV with metadata errors, can go to Document Library > Data Imports > Completed and see an indicator on the csv");

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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);

		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		String createObjectData = dataTable.getData("MyFiles",
				"CollectionObjectBasicData");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		ArrayList<String> CR_CourseImportTestsObjectList = new ArrayList<>();

		// // login
		functionalLibrary.loginAsValidUser(signOnPage);

		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		// go to Course plan
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		// upload course plan
		collectionPg.uploadFileInCollectionSite(filePath, fileName);

		// verify import process progress
		collectionPg.verifyImportProcessProgress(fileName);

		// verify uploaded object in the collection view
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.openCollectionObject(collectionObjectName);
		CR_CourseImportTestsObjectList = collectionPg
				.getFoldersFromRightPanInCollectionUi();

		// verify external ID
		for (String object : CR_CourseImportTestsObjectList) {
			String externalID = collectionPg
					.verifyObjectDetailsInViewdatailsPage(object, "External ID");
			if (externalID.contains("CRTEST")) {
				report.updateTestLog("External ID for given object  ",
						externalID, Status.PASS);

			} else {
				report.updateTestLog("External ID for given object  ",
						externalID, Status.FAIL);

			}

		}

		// verify Descriptor ID
		collectionPg
				.openCollectionObject(CR_CourseImportTestsObjectList.get(0));
		collectionPg.openCollectionObject(collectionPg
				.getFoldersFromRightPanInCollectionUi().get(0));
		String DescriptorID = collectionPg
				.verifyObjectDetailsInViewdatailsPage(collectionPg
						.getFoldersFromRightPanInCollectionUi().get(0),
						"Legacy DCTM Descriptor ID");
		String DescriptorIDFromCsv = "71e45f38-69a0-42ae-86c3-c033feb7b8a0";
		if (DescriptorID.trim().contains(DescriptorIDFromCsv.trim())) {
			report.updateTestLog("Descriptor ID for given object  ",
					DescriptorID, Status.PASS);

		} else {
			report.updateTestLog("Descriptor ID for given object  ",
					DescriptorID, Status.FAIL);

		}

		// verify course object in share ui
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Courses");
		collectionPg.getFoldersFromRightPanInShareUi();

		// verify external ID for copied object
		String externalIdBeforeEdit = collectionPg
				.verifyObjectDetailsInViewdatailsPageInShareUi("CR Course Import Test",	"External ID").replace("CRTEST_L", "");
		
		sitesPage.clickOnMoreSetting("CR Course Import Test");
		docLibPg.commonMethodForClickOnMoreSettingsOption("CR Course Import Test", "Copy to...");
		docLibPg.performCopyToOperation(siteName, "Courses");
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		String externalIdAfterEdit = collectionPg
				.verifyObjectDetailsInViewdatailsPageInShareUi(
						"Copy of CR Course Import Test", "External ID")
				.replace("CRTEST_L", "");
		if (Integer.parseInt(externalIdAfterEdit) == Integer
				.parseInt(externalIdBeforeEdit)) {

			report.updateTestLog("Verify External ID of Copied Object",
					"External ID of Copied Object "
							+ "<br /><b> Copied Ext. Id : </b> " + "L"
							+ externalIdBeforeEdit
							+ "<br /><b> Orginial Ext. Id : </b> " + "L"
							+ externalIdAfterEdit + " is same as expected",
					Status.PASS);

		} else {

			report.updateTestLog("Verify External ID of Copied Object",
					"External ID of Copied Object "
							+ "<br /><b> Copied Ext. Id : </b> " + "L"
							+ externalIdBeforeEdit
							+ "<br /><b> Orginial Ext. Id : </b> " + "L"
							+ externalIdAfterEdit + " is not same", Status.FAIL);

		}
		

		
//		verify tumbnail asset
/*		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Assets");
		myFiles.openCreatedFolder("Thumbnails");
		collectionPg.getFoldersFromRightPanInShareUi();*/
		
//		verify tumbnail reference 
	/*	sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.openCollectionObject(collectionObjectName);
		collectionPg.openCollectionObject(collectionPg.getFoldersFromRightPanInCollectionUi().get(0));
		collectionPg.openCollectionObject(collectionPg.getFoldersFromRightPanInCollectionUi().get(0));
		collectionPg.clickOnMoreSetting(collectionPg.getFoldersFromRightPanInCollectionUi().get(1));
		String objName = collectionPg.getFoldersFromRightPanInCollectionUi().get(1);
		collectionPg.commonMethodForClickOnMoreSettingsOption(objName,
				"View Details");
		collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(objName, "outgoing", "Thumbnail");*/

		
//		verify error indicator
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Data Imports");
		sitesPage.documentdetails("Completed");
		sitesPage.documentdetails(collectionPg
				.getFoldersFromRightPanInShareUi().get(0));
		sitesPage.documentdetails(collectionPg
				.getFoldersFromRightPanInShareUi().get(0));
		sitesPage.documentdetails(collectionPg
				.getFoldersFromRightPanInShareUi().get(0));
		collectionPg.verifyErrorIndicator(collectionPg
				.getFoldersFromRightPanInShareUi().get(0));
		
		/*while(!collectionPg.getFoldersFromRightPanInShareUi().get(0).equalsIgnoreCase( "CR Course Import Test.csv")){
			
			myFiles.openCreatedFolder(collectionPg
					.getFoldersFromRightPanInShareUi().get(0));
			collectionPg.verifyErrorIndicator(collectionPg
					.getFoldersFromRightPanInShareUi().get(0));
			
		}
*/
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}