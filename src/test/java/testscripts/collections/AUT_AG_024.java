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

public class AUT_AG_024 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_005() {
		testParameters.setCurrentTestDescription(
				
				"<br>ALFDEPLOY 4051_Verify the values for Version Country,State code,Version disttrict and Version statement"
				+ " should get populate in correct place for Content object"
				+"<br>ALFDEPLOY 4051_Verify the values for Version Country,State code,Version disttrict and Version statement"
				+ " should get populate in correct place for learning bundle object"
				+"<br>ALFDEPLOY 4051_Verify the values for Version Country,State code,Version disttrict and Version statement"
				+ " should get populate in correct place for Course object"
				+"<br>ALFDEPLOY 4051_Verify the values for Version Country,State code,Version disttrict and Version statement "
				+ "should get populate in correct place for Sequence object"
				+"<br>ALFDEPLOY 4051_Verify the values for Version Country,State code,Version disttrict and Version statement "
				+ "should get populate in correct place for Container object"
				+"<br>ALFDEPLOY 4051_Verify the values for Version Country,State code,Version disttrict and Version statement "
				+ "should get populate in correct place for asset object"
				+"<br>ALFDEPLOY 4049_Verify all the collection object are available in the eresulted CSV when the Version state is blank."
				+"<br>ALFDEPLOY 4049_Verify there is no prefix when the filter is blank."
				+"<br>ALFDEPLOY 4049_Verify the filter is working as Boolean 'OR'"
				+"<br>ALFDEPLOY 4049_Verify the CSV  contains collection object whose version state is null even there is some condition applied ."
						

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
		String CourseXpath = "//*[@class='filename']//*[contains(text(),'Course')]";
		
		
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

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
		UIHelper.click(driver, CourseXpath);
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
		
		//String filename2 = "Course-2017-12-03-1144.csv";
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);
		if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filename2);
			
			List<String> Versionstate= collectionPg.CSVValues(createObjectData,"VersionState:",2);			
			List<String> Versioncountry= collectionPg.CSVValues(createObjectData,"VersionCountry:",5);				
			List<String> Versionstatement= collectionPg.CSVValues(createObjectData,"VersionStatement:",4);				
			List<String> Versiondistrict= collectionPg.CSVValues(createObjectData,"VersionDistrict:",3);
			
			for (String csvRow : csvFileRowDataList) {
				String splitedRow[] = null;
				splitedRow = csvRow.split(",",-1);
				collectionPg.valuecomparation(splitedRow,Versioncountry,91,"VersionCountry");				
			}	
			
			for (String csvRow : csvFileRowDataList) {
				String splitedRow[] = null;
				splitedRow = csvRow.split(",",-1);
				collectionPg.valuecomparation(splitedRow,Versionstate,92,"VersionState");				
			}	
			for (String csvRow : csvFileRowDataList) {
				String splitedRow[] = null;
				splitedRow = csvRow.split(",",-1);
				collectionPg.valuecomparation(splitedRow,Versiondistrict,93,"VersionDistrict");				
			}	
			for (String csvRow : csvFileRowDataList) {
				String splitedRow[] = null;
				splitedRow = csvRow.split(",",-1);
				collectionPg.valuecomparation(splitedRow,Versionstatement,94,"VersionStatement");				
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
