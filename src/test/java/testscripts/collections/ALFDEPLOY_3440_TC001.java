package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3440_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_001() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3440_01_Validate new Discussion Prompt property through new course CSV Import"
						+"<br>ALFDEPLOY-3440_02_Confirm Discussion Prompt is present in Collections"
						+"<br>ALFDEPLOY-3440_03_Modify Discussion Prompt value"
						+"<br>ALFDEPLOY-3440_04_Export course"
						+"<br>ALFDEPLOY-3440_05_Confirm Discussion Prompt is exporting correctly"
						+"<br>ALFDEPLOY-3440_06_Modify Discussion Prompt in CSV and update");
		
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
			String siteNameValue =  dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String Metadata[] = dataTable.getData("MyFiles", "FieldDataForAllProperties").split(";");
		String[] fieldData = dataTable.getData("MyFiles", "FieldDataForQuickEdit").split(";");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");
		String smartlink = dataTable.getData("MyFiles", "BrowseActionName");
		File downloadedFile = null;
		
		boolean flag = false;
		       // Log in Pearson Schools project
			    functionalLibrary.loginAsValidUser(signOnPage);
			    UIHelper.waitForLong(driver);
	        	//Create site
				homePageObj.navigateToSitesTab();
				UIHelper.waitFor(driver);
/*
				String siteName = sitesPage.getCreatedSiteName();				
				sitesPage.openSiteFromRecentSites(siteName);*/
				
				 
				sitesPage.createSite(siteNameValue, "Yes");
				UIHelper.waitFor(driver);
			
	         // Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
		
		  //go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");
	
		     // upload course plan
				
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
			    driver.navigate().refresh();
			   
			    sitesPage.enterIntoDocumentLibrary();
		
			    //go to course object 1
		          
			    Date date = new Date();
			    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
	           	           
	            myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Completed");
				myFiles.openCreatedFolder(currentDate.substring(0, 4));
				myFiles.openCreatedFolder(currentDate.substring(5, 7));
				myFiles.openCreatedFolder(currentDate.substring(8, 10));
				report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);
	           
			    /*********************************Validate new Discussion Prompt property through new course CSV Import******************************/
	          				
				flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName,Option[0]);
				if(flag){
					report.updateTestLog("View Error Report :", "View Error Report is present "
							+ "for "+"<b> "+fileName, Status.FAIL);		
				}else{
					report.updateTestLog("View Error Report:", "View Error Report is not presented "
							+ "for "+"<b> "+fileName, Status.PASS);			
				}			
				
				/*************************** Confirm Discussion Prompt is present in Collections**********************/

				// Go to Courses and navigate to Content Type
				sitesPage.enterIntoDocumentLibrary();
				sitesPage.documentdetailsColl(folderNames[0]);
				sitesPage.documentdetailsColl(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);
				
				/****************************Modify Discussion Prompt value**************************************/
				collectionPg.confirmPropertyinImportAndSave(folderNames[2], Metadata[0], fieldData[0]);
				
				/**************************************Export course*****************************************/
				
				 sitesPage.enterIntoDocumentLibrary();
				 sitesPage.documentdetailsColl("Programs");
				 sitesPage.documentdetailsColl("Program");
				 collectionPg.clickOnEditCollectionButton();
				 UIHelper.waitForPageToLoad(driver);
				
				
				// Click on Generate Realize Csv for course object
				collectionPg.clickOnMoreSetting(folderNames[1]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[1], Option[1]);
				collectionPg.clickonrealizebox();
				// Navigate to Document library
				sitesPage.enterIntoDocumentLibrary();

				for (String path : filePath1) {
					sitesPage.documentdetails(path);
				}
			
				// Navigate to generated csv file path and check whether
				sitesPage.documentdetails(currentDate);

				// CSVfile presence and filename
				String fileName1 = folderNames[1] + "-" + currentDate;
				String filename2 = collectionPg.CSVFilename(fileName1);

				System.out.println("filename1 : " + fileName1); 
				
				new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,
						filename2);

				sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");

				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				downloadedFile = new File(downloadFilePath + "/" + filename2);
				if (downloadedFile.exists()
						&& !filename2.equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: " + filename2
							+ " downloaded sucessfully", Status.PASS);
				}
			
				
				/*********************************Confirm Discussion Prompt is exporting correctly********************************/
				
				String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
				try {
					if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"Discuss changed value",2,81))
						{
						report.updateTestLog("Confirm Discussion Prompt is exporting correctly ",
								"Discussion Prompt column displays 'Discuss changed value' as its value'", Status.PASS);
						}
					else
					{
						report.updateTestLog("Confirm Discussion Prompt is exporting correctly ",
								"Discussion Prompt exported incorrectly ", Status.FAIL);
					}
				
				/************************************Modify Discussion Prompt in CSV and update***********************************/			
					
					CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, smartlink ,2 ,81 );
					report.updateTestLog("Verify Discussion prompt value is updated", "File: " + filename2
							+ " updated sucessfully", Status.PASS);
					
					// Import the updated CSV File
					
					// Go to collection UI
					sitesPage.enterIntoDocumentLibrary();

					//go to Course plan
					myFiles.openCreatedFolder("Data Imports");
					myFiles.openCreatedFolder("Course Plan");
							
					// upload course plan
					
					collectionPg.uploadFileInCollectionSite("/DownloadFiles/", filename2);
					UIHelper.waitForLong(driver);
					UIHelper.pageRefresh(driver);
					UIHelper.waitForLong(driver);
					UIHelper.pageRefresh(driver);
				    driver.navigate().refresh();
				    
				    
				 // Go to Courses and navigate to Content Type
					sitesPage.enterIntoDocumentLibrary();
					sitesPage.documentdetailsColl(folderNames[0]);
					sitesPage.documentdetailsColl(folderNames[1]);
					collectionPg.clickOnEditCollectionButton();
					UIHelper.waitForPageToLoad(driver);
					
					UIHelper.waitForLong(driver);
					collectionPg.clickOnMouseOverMenu("Test Object - Discussion Prompt", "View Details");
					UIHelper.waitForPageToLoad(driver);
					collectionPg.verifyMetadataValues("Discussion Prompt",smartlink);
					
					
				}catch (IOException e) {
					report.updateTestLog("Confirm Discussion Prompt is exporting correctly ",
							"Discussion Prompt exported incorrectly ", Status.FAIL);
					e.printStackTrace();
				}
				
				
		}	  

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	}

