package testscripts.collections8;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1513_3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_033() {
		testParameters
				.setCurrentTestDescription("can create child references from content object to files");
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
				
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String uploadNormalFileName = dataTable.getData("MyFiles", "Subfolders1");
		String filepathfile = dataTable.getData("MyFiles", "uploadpath");
		String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String jsonFileName = dataTable.getData("MyFiles", "FileName");
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
	
		homePageObj.navigateToSitesTab();
		
		String siteNameValue =  dataTable.getData("Sites", "SiteName");					
		sitesPage.createSite(siteNameValue, "No");
		
				// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();

				// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

				// upload course plan
				collectionPg.uploadFileInCollectionSite(filepathfile, filepathfilename);

				// verify import process progress
				collectionPg.verifyImportProcessProgress(filepathfilename);				
				
				
				// Navigate to document library and click on a Assets/Rubrics
				String flag = "true";
				try {		
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[2]);
				myFiles.openCreatedFolder(folderNames[3]);
				}catch(Exception e) {
					flag= "false";
					report.updateTestLog("Problem in entering into Rubric Folder", "Issue in entering into Rubric Folder using <br/><b>Site Name:</b> " + siteNameValue,
							Status.FAIL);
				}
				
				if(flag.equalsIgnoreCase("true"))
				report.updateTestLog("Enter into Rubric Folder", "Enter into Rubric Folder using <br/><b>Site Name:</b> " + siteNameValue,
						Status.PASS);
				
				// upload json file
				if(flag.equalsIgnoreCase("true")) {
				collectionPg.uploadFileInCollectionSite(filepathfile, jsonFileName);
				report.updateTestLog("Upload .json file", "successfull upload of file into Rubric Folder using <br/><b>Site Name:</b> " + siteNameValue,
						Status.PASS);
				}else
					report.updateTestLog("Upload .json file", "Issue in uploading file into Rubric Folder using <br/><b>Site Name:</b> " + siteNameValue,
							Status.FAIL);
				
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				sitesPage.enterIntoDocumentLibrary();
				
				// upload normal file
				myFiles.openCreatedFolder(folderNames[2]);
				if(flag.equalsIgnoreCase("true")) {
				collectionPg.uploadFileInCollectionSite(filepathfile, uploadNormalFileName);
				report.updateTestLog("Upload normal file", "successfull upload of file into Assets Folder using <br/><b>Site Name:</b> " + siteNameValue,
						Status.PASS);
				}else
					report.updateTestLog("Upload normal file", "Issue in uploading file into Assets Folder using <br/><b>Site Name:</b> " + siteNameValue,
							Status.FAIL);
				
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				sitesPage.enterIntoDocumentLibrary();
				
				
				// go to Course plan
				myFiles.openCreatedFolder(folderNames[4]);
				myFiles.openCreatedFolder(folderNames[5]);
				collectionPg.clickOnEditCollectionButton();
				try {
				// Add the uploaded Normal File as child reference to content 				
				collectionPg.clickOnMoreSetting("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching");
				UIHelper.waitFor(driver);
				collectionPg.commonMethodForClickOnMoreSettingsOption("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching", "Child references");
				UIHelper.waitFor(driver);
				collectionPg.addReferenceInInCollectionUi("Child","Assets/Image/i/Image1.jpg");
				
				report.updateTestLog("Add Child Reference", "successfull uploaded Normal File as child reference to content using <br/><b>Site Name:</b> " + siteNameValue,
						Status.PASS);
				UIHelper.waitFor(driver);
				// Add the uploaded json File as child reference to content 				
				collectionPg.clickOnMoreSetting("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching");
				UIHelper.waitFor(driver);
				collectionPg.commonMethodForClickOnMoreSettingsOption("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching", "Child references");
				UIHelper.waitFor(driver);
				collectionPg.addReferenceInInCollectionUi("Child","Assets/Rubrics/About with 1 sheet.json" );
				report.updateTestLog("Add Child Reference", "successfull uploaded json File as child reference to content using <br/><b>Site Name:</b> " + siteNameValue,
						Status.PASS);
				}catch(Exception e) {
					report.updateTestLog("Add Child Reference", "Issue in uploaded Normal/json File as child reference to content  using <br/><b>Site Name:</b> " + siteNameValue,
							Status.FAIL);
				}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
