package testscripts.collections5;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;













import au.com.bytecode.opencsv.CSVReader;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

public class AUT_AG_022 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_045() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4049_Verify there is a modal Dialog box on clicking"
						+ " Generate realize CSV for Course object<br>"					
						+ "ALFDEPLOY-4051_Verify there is a Column or Header in CSV for State Code<br>"					
						+ "ALFDEPLOY-4051_Verify there is a Column or Header in CSV for Version District<br>"
						+ "ALFDEPLOY-4051_Verify there is a Column or Header in CSV for Version statement<br>"
						+ "ALFDEPLOY-4051_Verify there is a Column/Header in CSV for Version Country<br>"
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
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
				
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
		String filepathfile = dataTable.getData("MyFiles", "uploadpath");
		String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectName = dataTable.getData("MyFiles", "FileName");
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
		
		
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");				
		//sitesPage.siteFinder("Autorefercollectionsite18821217130033");
		
		sitesPage.createSite(siteNameValue, "Yes");

				// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();

				// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

				// upload course plan
				collectionPg.uploadFileInCollectionSite(filepathfile, filepathfilename);

				// verify import process progress
				collectionPg.verifyImportProcessProgress(filepathfilename);				
				sitesPage.enterIntoDocumentLibrary();
				
				
				// go to Course plan
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
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
					csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filenameCSV);
					
					
					for (String csvRow : csvFileRowDataList) {
						String splitedRow[] = null;
						splitedRow = csvRow.split(",");

						String[] filterselection1 = dataTable.getData("MyFiles", "HeaderName").split(",");
						for (String listOfObjectsString : filterselection1) {
							//System.out.println(listOfObjectsString);
						if (listOfObjectsString.contains(splitedRow[92].replace("\"", ""))
								|| listOfObjectsString.contains(splitedRow[93].replace("\"", ""))|| listOfObjectsString.contains(splitedRow[94].replace("\"", "")) || listOfObjectsString.contains(splitedRow[91].replace("\"", ""))) {
							report.updateTestLog(listOfObjectsString +" header is appearing in the csv file " , "Status: " + listOfObjectsString+"appeared in the csv file", Status.PASS);
						}else{
							//System.out.println("it is not appearing");
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