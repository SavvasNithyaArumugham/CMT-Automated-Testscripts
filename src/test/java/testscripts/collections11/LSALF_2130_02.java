package testscripts.collections11;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
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

public class LSALF_2130_02 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_03() {
		testParameters.setCurrentTestDescription(
				
				"Confirm Seq Object,Container,Content Object the release version property is edited via edit/all properties and confirmed the same via the view details. and click the printplan csv and check the presence of release version column");

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
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
	
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");		
		// From the site Type dropdown select 'Collection Site'.
		sitesPage.openSiteFromRecentSites(siteNameValue);		
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split(",");
		
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		sitesPage.enterIntoDocumentLibrary();

		// go to Course plan
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		String objectName = dataTable.getData("MyFiles", "FileName");
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		// Click on Generate Realize Csv for course object
		collectionPg.clickOnBrowseActionsInCollectionUI(objectName, browseActionName);
		collectionPg.clickonprintcsvrealizebox();
		
		UIHelper.waitFor(driver);
		// Navigate to Document library
				sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder(filePath[0]);
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder(filePath[1]);
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder(filePath[2]);
				UIHelper.waitFor(driver);
				

				// Navigate to generated csv file path and check whether
				sitesPage.documentdetails(currentDate);

				// CSVfile presence and filename
				String fileName1 = objectName + "-" + currentDate;
				String filenameCSV = mediaTransPage.RetreiveFilename(fileName1);
				String filename2 = collectionPg.CSVFilename(fileName1);
				collectionPg.clickOnMoreSetting(filename2);
				collectionPg.commonMethodForClickOnMoreSettingsOption(filename2, "Download");

				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				downloadedFile = new File(downloadFilePath + "/" + filename2);
				
		if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filenameCSV);

			for (String csvRow : csvFileRowDataList) {
				String splitedRow[] = null;
				splitedRow = csvRow.split(",");

				String filterselection1 = dataTable.getData("MyFiles", "HeaderName");
				
					for(int i =0;i<splitedRow.length;i++) {
					if (filterselection1.equals(splitedRow[i])) {
						report.updateTestLog(filterselection1 + " header is appearing in the csv file ",
								"Status: " + filterselection1 + "appeared in the csv file", Status.PASS);
						break;
					} else {
						/*report.updateTestLog(filterselection1 + " header is not appearing in the csv file ",
								"Status: " + filterselection1 + " not appeared in the csv file", Status.FAIL);*/
					}		
				}
				
				break;
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
