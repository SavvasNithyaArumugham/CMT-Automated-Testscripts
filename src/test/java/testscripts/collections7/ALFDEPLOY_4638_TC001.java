package testscripts.collections7;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_4638_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_4638_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_4638_TC001 Validate import testdata course." +
	    "<br>ALFDEPLOY_4638_TC002_Verification of Export Course with two Version State filters applied)");

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
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage DocDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage DocLibPg = new AlfrescoDocumentLibPage(scriptHelper);		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] uploadName = dataTable.getData("MyFiles", "uploadpath").split("/");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		functionalLibrary.loginAsValidUser(signOnPage);
		
		//Create site
		UIHelper.waitForLong(driver);
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");				
		sitesPage.siteFinder(siteNameValue);
		
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		
		//upload file in collectionsite
		
		
		myFiles.openCreatedFolder("Data Imports");
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Course Plan");
		
		//upload file in course plan		
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		
		//Apply version state filter
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Programs");
		myFiles.openCreatedFolder("Program");
		collectionPg.clickOnEditCollectionButton();	
		UIHelper.waitFor(driver);
		
		
		// Click on Generate Realize Csv for course object
		
				collectionPg.clickOnBrowseActionsInCollectionUI("CCP Test Course 7 - ALFDEPLOY-4438", "Generate Realize CSVs");
				UIHelper.waitFor(driver);
				collectionPg.clickonrealizebox();
				UIHelper.waitFor(driver);
				
				// Navigate to Document library
				sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder(uploadName[0]);
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder(uploadName[1]);
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder(uploadName[2]);
				UIHelper.waitFor(driver);
				
				// Navigate to generated csv file path and check whether
				sitesPage.documentdetails(currentDate);
				
				// CSVfile presence and filename
				String fileName1 = "CCP Test Course 7 - ALFDEPLOY-4438" + "-" + currentDate;	
				//String filenameCSV= mediaTransPage.RetreiveFilename(fileName1);
				
				String filename2= collectionPg.CSVFilename(fileName1);
				
				collectionPg.clickOnMoreSetting(filename2);
				collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"Download");
		
				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				downloadedFile = new File(downloadFilePath + "/" + filename2);
				if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);			
			
					csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filename2);
					
					String[] filterselection1 = dataTable.getData("MyFiles", "HeaderName").split(";");		
					int filtercount =0;
					int count1=0;																		
						for (String fileValues : filterselection1) 
						{
							
							String splittedFileValues[] = fileValues.split(",");			
							
							for (String values:splittedFileValues){
								
								if (values != null) 
								{
										System.out.println("SplittedFilevalue: "+values);								
										for (String csvRow : csvFileRowDataList) 
										{				
											String splitedRow[] = null;
											splitedRow = csvRow.split(",",-1);
											System.out.println("splitedRow[92]=="+splitedRow[92]);
											if(values.equals(splitedRow[92].replace("\"", ""))){												
												count1++;
												break;
											}else{
												
											}
										}
										if(count1>0){
											report.updateTestLog("Verify User Can able to export the Csv only for the selected Version State Values  ", "User can able to export the CSV only for selected Version State Values  <br>Options: "+values, Status.PASS);									
										}else{
											report.updateTestLog("Verify User Can able to export the Csv only for the selected Version State Values  ", "User is not able to export the CSV only for selected Version State Values  <br>Options: "+values, Status.PASS);									
										}
										filtercount++;
							}
							}
						}
					
						}else {
							report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
						}
								
							}					
				
						
				
				
							
				

	@Override
	public void tearDown() {

	}
}

