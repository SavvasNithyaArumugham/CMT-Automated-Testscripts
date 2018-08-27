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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2242_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_030() {
		testParameters.setCurrentTestDescription(
				"1. Within the Share UI, can upload one or more files via 'Upload' button or via drag and drop within a 'Collections' Site Type (OOTB Alfresco)"
						+ "<br> 2. Within the Collections UI, can select an object in the left pane and click 'Upload' button to select one or more files (OOTB Collections)");
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

		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);

		// Select Sites > Create Site from top black menu
		// From the site Type dropdown select 'Collection Site'

		homePageObj.navigateToSitesTab();

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		sitesPage.enterIntoDocumentLibrary();

		String[] folderNames = dataTable.getData("MyFiles", "CreateFolder").split(",");

		// Use Drag and Drop or "Upload" button to upload an asset in each
		// bucket type

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		collectionPg.uploadFileInCollectionSite(filePath, fileName);

		// Check the bucketing rules
		// Navigate to the folder detail page of the structural object to which
		// a file has been uploaded

		sitesPage.enterIntoDocumentLibrary();

		String assetTypes = dataTable.getData("MyFiles", "AssetTypes");

		collectionPg.clickOnFolderInLeftsideTreeView("Asset");

		if (assetTypes.contains(",")) {
			String splittedAssetTypes[] = assetTypes.split(",");
			if (splittedAssetTypes != null) {
				for (String assetType : splittedAssetTypes) {

					collectionPgTest.verifyBucketingRulesForAssetType(assetType);
				}
			}
		} else {
			collectionPgTest.verifyBucketingRulesForAssetType(assetTypes);
		}

		sitesPage.enterIntoDocumentLibrary();

		// Check that the "Child Reference" association to the the uploaded file
		// is present in the relationship viewer
		myFiles.openCreatedFolder(folderNames[0]);
		//Modified as part of NALS Starts
		sitesPage.clickOnViewDetails(folderNames[1]);
		
		collectionPg.verifyListOfReferenveValue("Course", "outgoing","Child Reference","AlfrescoMP3File.mp3");
		collectionPg.verifyListOfReferenveValue("Course", "outgoing","Child Reference","AlfrescoMP4File.mp4");
		collectionPg.verifyListOfReferenveValue("Course", "outgoing","Child Reference","AlfrescoJPEGFile.jpg");
		
		
		collectionPgTest.verifyChildReferencesForUploadedFiles("Assets", assetTypes);
		//Modified as part of NALS Ends
	

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
