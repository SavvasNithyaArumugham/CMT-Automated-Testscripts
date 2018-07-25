package testscripts.collections;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_025 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_005() {
		testParameters.setCurrentTestDescription(
				
				"<br>ALFDEPLOY-4049_Verify User can be able to select the version state and"
				+ " the CSV should contains collection object which satisfying that condition."
				+"<br>ALFDEPLOY-4049_Verify User can be able to select Single version state"
				+ "<br>ALFDEPLOY-4049_Verify Filter can be applied to Sequence object"
				+"<br>ALFDEPLOY-4049_Verify Filter can be applied to Container object"
				+ "<br>ALFDEPLOY-4049_Verify Filter can be applied to Learning Bundles"
				+"<br>ALFDEPLOY-4049_Verify Filter can be applied to Composite Objects "
				+ "<br>ALFDEPLOY-4049_Verify Filter can be applied to Content Objects"
				+"<br>ALFDEPLOY-4049_Verify Filter cannot be applied to Course object"
				+ "<br>ALFDEPLOY-4049_Verify filter cannot be applied to program objects."
						

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
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");

		
		
		String objectName = dataTable.getData("MyFiles", "FileName");
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		// Select Sites > Create Site from top black menu.\
		UIHelper.waitForLong(driver);
		
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
		UIHelper.waitFor(driver);
		UIHelper.click(driver, "//*[@class='filename']//*[contains(text(),'Course')]");
		// Verify custom icons by creating new collections objects

		collectionPg.createBasicCollectionObjectFromCreateMenuforcsv(createObjectData);

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
					System.out.println(collectionObjectName);
					
				}
			}
		}

		driver.navigate().back();
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		UIHelper.pageRefresh(driver);
		// Click on Generate Realize Csv for course object
		collectionPg.clickOnBrowseActionsInCollectionUI(objectName, browseActionName);
		collectionPg.clickonrealizebox();
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();

		for (String path : filePath) {
			sitesPage.documentdetails(path);
		}
		
		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(currentDate);
		
		// CSVfile presence and filename
		String fileName1 = objectName + "-" + currentDate;	
		String filenameCSV= mediaTransPage.RetreiveFilename(fileName1);
		
		String filename2= collectionPg.CSVFilename(fileName1);
		
		collectionPg.clickOnMoreSetting(filename2);
		collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"Download");
		
		
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);
		if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filename2);
			
			List<String> Versionstate= collectionPg.CSVValues(createObjectData,"VersionState:",2);
			
				
			
			for (String csvRow : csvFileRowDataList) {
				String splitedRow[] = null;
				splitedRow = csvRow.split(",",-1);

				collectionPg.FilterSignleversionstate(Versionstate, splitedRow);
			}	
			
									
		} else {
			report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
		}
		
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
