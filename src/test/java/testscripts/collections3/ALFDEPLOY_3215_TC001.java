package testscripts.collections3;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
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
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3215_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_033() {
		testParameters
				.setCurrentTestDescription("1) can create child references from content or composite objects to files, generate a Realize csv and see that there is a CMIS URL generated that points to the file object");
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
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");



		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
				homePageObj.navigateToSitesTab();
				String siteNameValue = dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "Yes");
				String siteName = sitesPage.getCreatedSiteName();
				
			
		// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
		//enter into default course object
				String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
				collectionPg.openCollectionObject(collectionObjectName);
				
		// create collections objects
				String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
				collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
				
		// upload  files 
				String filePath = dataTable.getData("MyFiles", "FilePath");
				String fileName = dataTable.getData("MyFiles", "FileName");
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				
		// Add the uploaded File as child reference to content 				
				collectionPg.clickOnMoreSetting("AlfrescoAutoDemoForTest.docx");
				UIHelper.waitFor(driver);
				collectionPg.commonMethodForClickOnMoreSettingsOption("AlfrescoAutoDemoForTest.docx", "Parent references");
				UIHelper.waitFor(driver);
				collectionPg.addReferenceInInCollectionUi("Parent", "Content Objects/a/AutoContentObj");		
			
				
		// click generate realize CSV on course 
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();
				
				String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionName);
				//Modified as part of NALS Starts
				collectionPg.clickonrealizebox();
				//Modified as part of NALS Ends
				
		//Download generated realize CSV
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder("Data Exports");
				myFiles.openCreatedFolder("Realize Export");
				myFiles.openCreatedFolder("Course");
				myFiles.openCreatedFolder(myFiles.getUploadedFileOrFolderTitle().get(0));
				
				String downloadFilePath = properties.getProperty("DefaultDownloadPath");
				ArrayList<String> generatedFileList = new ArrayList<String>();
				generatedFileList = myFiles.getUploadedFileOrFolderTitle();
				String downloadedCourseFileName = "";
				File downloadedFile = null;
				ArrayList<String> csvFileRowDataList = null;
				boolean downloadFlag = false;

		//read the downloaded CSV and Validate the content object has CIMS url
				for (String file : generatedFileList) {

					if (file.contains("Course")) {
						downloadedCourseFileName = file;
						new FileUtil().deleteFileWithContainsTxtInDownloadPath(downloadFilePath, "Course");
						sitesPage.commonMethodForPerformBrowseOption(file, "Download");
						new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, file);
						downloadedFile = new File(downloadFilePath + "/" + file);
						if (downloadedFile.exists() && !file.equalsIgnoreCase("File Not Found")) {

							report.updateTestLog("Verify download file", "File: " + file + " downloaded sucessfully",
									Status.PASS);
							downloadFlag = true;
							csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + file);

						} else {

							report.updateTestLog("Verify download file", "File: " + file + " failed to download", Status.FAIL);
						}
					} else {

						System.out.println(file + "Not expected File");
					}
				}
				

				if (downloadFlag) {
					for (String csvRow : csvFileRowDataList) {

						String splitedRow[] = null;
						splitedRow = csvRow.replace("\"", "").replace("", " ").split(",");
						
						
						if (splitedRow[20].replace(" ", "").contains("AutoContentObjTestTitle") && splitedRow[46].replace(" ", "").contains("cmis")) {
							report.updateTestLog("Collection Object: " + splitedRow[20].replace(" ", ""), "has URL : " + splitedRow[46].replace(" ",""),
									Status.PASS);
						} else if (splitedRow[20].replace(" ", "").contains("AutoContentObjTestTitle") && !splitedRow[46].replace(" ", "").contains("cmis")) {
							report.updateTestLog("Collection Object: " + splitedRow[20].replace(" ", ""), "has URL : " + splitedRow[46].replace(" ",""),
									Status.FAIL);
						}
					}
					
				}
			

				



	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
