package testscripts.collections;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_030 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_060() {
		testParameters.setCurrentTestDescription("TC-V_2.3.7.6-01 - Import test data set <br>"+
	"TC-5200-01 - Export Course to generate Index Code <br>" +
				"TC-5306-01 - Confirm Content Category property population via CSV import <br> " +
	"TC-5306-03 - Confirm Content Category property export<br>" +
				"TC-5306-04 - Confirm Content Category property update<br>"+
	"TC-5306-05 - Confirm Content Category property delete <br>");

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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoSearchPage alfrescoSearchPage = new AlfrescoSearchPage(scriptHelper);
		AlfrescoMyTasksPageTest alfrescoTaskPage = new AlfrescoMyTasksPageTest(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");

		String collectionsobj = dataTable.getData("MyFiles", "CreateFileDetails");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String multiselect = dataTable.getData("MyFiles", "RelationshipName");
		String generateCSVoption = dataTable.getData("MyFiles", "MoreSettingsOption2");
		String objectName = dataTable.getData("MyFiles", "Subfolders1");
		String[] csvpath = dataTable.getData("MyFiles", "CreateFolder").split("/");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption3");
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String aspectprop = dataTable.getData("MyFiles", "Sort Options");
		
		
		
		ArrayList<String> textfeatures = new ArrayList<String>();
		textfeatures = myFiles.getFileNames(multiselect);

		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);

		// Create site
		
		/*  homePageObj.navigateToSitesTab();
		   sitesPage.createSite(siteNameValue,"Yes");*/
		 

		String site = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(site);

		
	 // Go to collection UI 
		sitesPage.enterIntoDocumentLibrary();
		 //go to Course plan 
		sitesPage.documentdetails("Data Imports");
		 sitesPage.documentdetails("Course Plan");
		 
		  // upload course plan
		 
		  collectionPg.uploadFileInCollectionSite(filePath, fileName);
		  UIHelper.waitForLong(driver);
		   UIHelper.waitForLong(driver);
		  UIHelper.pageRefresh(driver);
		   UIHelper.waitForLong(driver);
		  UIHelper.pageRefresh(driver); 
		  driver.navigate().refresh();
	
		 sitesPage.enterIntoDocumentLibraryWithoutReport();
		  
		  //go to course object 1 
	
		  sitesPage.documentdetails("Data Imports");
		  sitesPage.documentdetails("Completed");
		  sitesPage.documentdetails(currentDate.substring(0, 4));
		  sitesPage.documentdetails(currentDate.substring(5, 7));
		  sitesPage.documentdetails(currentDate.substring(8, 10));
		  sitesPage.clickOnMoreSetting(fileName); 
		  sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName,
		  moreSettingsOption);
		 

		
		 sitesPage.enterIntoDocumentLibraryWithoutReport();
		  
		 sitesPage.documentdetails(folderNames[0]);
		 sitesPage.documentdetails(folderNames[1]);
		 collectionPg.clickOnEditCollectionButton();
		 
		 collectionPg.openCollectionObject("CCP-UAT_2.3.7.6_Test_Course_01");
		 collectionPg.openCollectionObject("Unit 1_ The First Unit");
		 collectionPg.openCollectionObject("Learning Model A");
		 collectionPg.openCollectionObject("Lesson 1_ The first Lesson");
		 collectionPg.openCollectionObject("Teacher Support 1");
		 
		 collectionPg.clickOnBrowseActionsInCollectionUI("Printable Teacher Guide for Lesson 1", "View Details");
		 
		 String ActualAspectType1 = appSearchPg.getMetadata(fileName,
					"Content Category:");
		 String ActualLexile1 = appSearchPg.getMetadata(fileName,
					"Lexile:");
		 String ActualTextFeature1 = appSearchPg.getMetadata(fileName,
					"Text Features:");
			
			if(ActualAspectType1.equals(aspectprop)) {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are successfully displayed in Property Section" + "<br/><b>Content Category:</b>"
								+ ActualAspectType1,
						Status.PASS);
			}else {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are failed to display in Property Section" + "<br/><b>Content Category:</b>"
								+ ActualAspectType1,
						Status.FAIL);
			}
			
			if(ActualLexile1.equals("BR1200")) {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are successfully displayed in Property Section" + "<br/><b>Lexile:</b>"
								+ ActualLexile1,
						Status.PASS);
			}else {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are failed to display in Property Section" + "<br/><b>Lexile:</b>"
								+ ActualLexile1,
						Status.FAIL);
			}
			
			if(ActualTextFeature1.equals("Schedule,Sidebar,Table,Time Line,Title")) {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are successfully displayed in Property Section" + "<br/><b>Text Features:</b>"
								+ ActualTextFeature1,
						Status.PASS);
			}else {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are failed to display in Property Section" + "<br/><b>Text Features:</b>"
								+ ActualTextFeature1,
						Status.FAIL);
			}
		
			docDetailsPageObj.commonMethodForPerformDocAction("Edit Properties");
			
			collectionPg.enterCollectionObjectA2LData("Content Category:", "Interactive Activities");
			
			collectionPg.enterCollectionObjectA2LData("Lexile:", "NC120");
		
			UIHelper.selectMultipleFromDropdownList(driver,UIHelper.findAnElementbyXpath(driver, ".//select[contains(@id,'textFeatures-entry')]"),
					textfeatures, UIHelper.findAnElementbyXpath(driver, ".//select[contains(@id,'textFeatures-entry')]"));
			
			UIHelper.waitFor(driver);
		//	collectionPg.verifyThumbnailGridThumbnailValuesInAllPropPage(listOfObjectsString);
			collectionPg.clickOnSaveBtn();
			
			 sitesPage.enterIntoDocumentLibraryWithoutReport();
			  
			 sitesPage.documentdetails(folderNames[0]);
			 sitesPage.documentdetails(folderNames[1]);
			 collectionPg.clickOnEditCollectionButton();
		
			  // Click on Generate Realize Csv for course object
			  collectionPg.clickOnMoreSetting(objectName);
			  collectionPg.commonMethodForClickOnMoreSettingsOption(objectName,
			  generateCSVoption); 
			  collectionPg.clickonrealizebox();
			  
			  UIHelper.waitForPageToLoad(driver);
			  UIHelper.waitFor(driver);
		
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibraryWithoutReport();

		for (String path : csvpath) {
			sitesPage.documentdetails(path);
		}

		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(objectName);
		sitesPage.documentdetails(currentDate);

		String csvName = sitesPage.documentname(objectName);
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, csvName);
		
		String uploadPath = downloadFilePath + csvName;
		
		collectionPg.clickOnBrowseActionsInCollectionUI(csvName, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, csvName);
		downloadedFile = new File(downloadFilePath + "/" + csvName);
		
	

		if (downloadedFile.exists() && !csvName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + csvName + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + csvName);
			
			
			try {
				if(CSVUtil.verifyDataInCell(uploadPath,"Interactive Activities",6,95))
				{
				report.updateTestLog("Verify Content category value in download CSV ",
						"Value has been update. <br><b> Content Category: Interactive Activities <b> " , Status.DONE);
				}else {
					report.updateTestLog("Verify Content category value in download CSV ",
							"Value has not been updated" , Status.FAIL);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			String splitedRow[] = null;
			String splitedRow1[] = null;
			
			
			splitedRow = csvFileRowDataList.get(6).split(",");

			if (splitedRow[15].replace("\"", "").equals("CCP-UAT_2.3.7.6_Test_Course_01:Unit 1: The First Unit:Learning Model A:Lesson 1: The first Lesson:Teacher Support 1")) 
			{
				report.updateTestLog("Verify Index code value in download CSV ", "Index code: " + splitedRow[15].replace("\"", ""), Status.DONE);
			} else {
				report.updateTestLog("Verify Index code value in download CSV" , "Not updated> <br><b> Index Code <b> :" + splitedRow[15].replace("\"", ""), Status.FAIL);
			}


			
			
			if (splitedRow[75].replace("\"", "").equals("Artifact|Schedule|Sidebar|Table")) 
			{
				report.updateTestLog("Verify Text Feature value in download CSV ", "Text Feature: " + splitedRow[75].replace("\"", ""), Status.DONE);
			} else {
				report.updateTestLog("Verify Text Feature value in download CSV" , "Not updated> <br><b> Text Feature <b> :" + splitedRow[75].replace("\"", ""), Status.FAIL);
			}
			
			if (splitedRow[69].replace("\"", "").equals("N120")) 
			{
				report.updateTestLog("Verify Lexile value in download CSV ", "Lexile: " + splitedRow[69].replace("\"", ""), Status.DONE);
			} else {
				report.updateTestLog("Verify Lexile value in download CSV" , "Not updated <br><b> Lexile <b> :" + splitedRow[69].replace("\"", ""), Status.FAIL);
			}

			splitedRow1 = csvFileRowDataList.get(7).split(",");

			if (splitedRow1[15].replace("\"", "").equals("CCP-UAT_2.3.7.6_Test_Course_01")) {
				report.updateTestLog("Verify Index code value in download CSV ", "Index code: " + splitedRow[15].replace("\"", ""), Status.DONE);
			} else {
				report.updateTestLog("Verify Index code value in download CSV" , "Not updated> <br><b> Index Code <b> :" + splitedRow[15].replace("\"", ""), Status.FAIL);
			}
	
		}
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		try {
			CSVUtil.updateCSV(uploadPath, "Home Practice", 6, 95);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			CSVUtil.updateCSV(uploadPath, "800L", 6, 69);
			
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			CSVUtil.updateCSV(uploadPath, "Artifact|Call Out", 6, 75);
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}

		 sitesPage.enterIntoDocumentLibraryWithoutReport();
		 
		 //go to Course plan 
		sitesPage.documentdetails("Data Imports");
		sitesPage.documentdetails("Course Plan");
			 
		// upload course plan
			 
		collectionPg.commonMethodForUploadMultipleFiles(downloadFilePath,"/" + csvName);
		UIHelper.waitForLong(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		driver.navigate().refresh();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();

		sitesPage.documentdetails(folderNames[0]);
		sitesPage.documentdetails(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		collectionPg.openCollectionObject("CCP-UAT_2.3.7.6_Test_Course_01");
		collectionPg.openCollectionObject("Unit 1_ The First Unit");
		collectionPg.openCollectionObject("Learning Model A");
		collectionPg.openCollectionObject("Lesson 1_ The first Lesson");
		collectionPg.openCollectionObject("Teacher Support 1");

		collectionPg.clickOnBrowseActionsInCollectionUI("Printable Teacher Guide for Lesson 1", "View Details");

		String ActualAspectType2 = appSearchPg.getMetadata(fileName, "Content Category:");
		String ActualLexile2 = appSearchPg.getMetadata(fileName,
				"Lexile:");
		 String ActualTextFeature2 = appSearchPg.getMetadata(fileName,
					"Text Features:");
		
		if(ActualAspectType2.equals("Home Practice")) {
			report.updateTestLog("Verify Properties in document details Page after CSV upload",
					"Properties are successfully displayed in Property Section" + "<br/><b>Content Category:</b>"
							+ ActualAspectType2,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Properties in document details Page after CSV upload",
					"Properties are failed to display in Property Section" + "<br/><b>Content Category:</b>"
							+ ActualAspectType2,
					Status.FAIL);
		}
		
		if(ActualLexile2.equals("800L")) {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are successfully displayed in Property Section" + "<br/><b>Lexile:</b>"
							+ ActualLexile2,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are failed to display in Property Section" + "<br/><b>Lexile:</b>"
							+ ActualLexile2,
					Status.FAIL);
		}
		
		
		
		if(ActualTextFeature2.equals("Artifact,Call Out")) {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are successfully displayed in Property Section" + "<br/><b>Text Features:</b>"
							+ ActualTextFeature2,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are failed to display in Property Section" + "<br/><b>Text Features:</b>"
							+ ActualTextFeature2,
					Status.FAIL);
		}
		
		
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		try {
			CSVUtil.updateCSV(uploadPath, "DELETE", 6, 95);
			CSVUtil.updateCSV(uploadPath, "DELETE", 6, 75);
		} catch (IOException e) {
			// TODO Auto-generated catch block 21
			e.printStackTrace();
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		 
		 //go to Course plan 
		sitesPage.documentdetails("Data Imports");
		sitesPage.documentdetails("Course Plan");
			 
		// upload course plan
			 
		collectionPg.commonMethodForUploadMultipleFiles(downloadFilePath,"/" + csvName);
		UIHelper.waitForLong(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForLong(driver);
		UIHelper.pageRefresh(driver);
		driver.navigate().refresh();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();

		sitesPage.documentdetails(folderNames[0]);
		sitesPage.documentdetails(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		collectionPg.openCollectionObject("CCP-UAT_2.3.7.6_Test_Course_01");
		collectionPg.openCollectionObject("Unit 1_ The First Unit");
		collectionPg.openCollectionObject("Learning Model A");
		collectionPg.openCollectionObject("Lesson 1_ The first Lesson");
		collectionPg.openCollectionObject("Teacher Support 1");

		collectionPg.clickOnBrowseActionsInCollectionUI("Printable Teacher Guide for Lesson 1", "View Details");

		String ActualAspectType3 = appSearchPg.getMetadata(fileName, "Content Category:");
		 String ActualTextFeature3 = appSearchPg.getMetadata(fileName,
					"Text Features:");
		
		if(ActualAspectType3.contains("None")) {
			report.updateTestLog("Verify Properties in document details Page after CSV upload 'DELETE' for Content Category",
					"Properties are successfully displayed in Property Section as expected" + "<br/><b>Content Category:</b>"
							+ ActualAspectType3,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Properties in document details Page after CSV upload",
					"Properties are failed to display in Property Section" + "<br/><b>Content Category:</b>"
							+ ActualAspectType3,
					Status.FAIL);
		}
		
		if(ActualTextFeature3.contains("None")) {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are successfully displayed in Property Section" + "<br/><b>Text Features:</b>"
							+ ActualTextFeature3,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are failed to display in Property Section" + "<br/><b>Text Features:</b>"
							+ ActualTextFeature3,
					Status.FAIL);
		}
	} 


	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

}
