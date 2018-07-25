package testscripts.collections;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
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

public class ALFDEPLOY_4591_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_001() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4591 Validate Language property through new course CSV Import "
						+"<br>ALFDEPLOY-4591 Confirm Language property population"
						+"<br>ALFDEPLOY-4591 Modify Language property"
						+"<br>ALFDEPLOY-4591 Confirm Language property export");
		
		
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
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);	
		AlfrescoSearchPage alfrescoSearchPage = new AlfrescoSearchPage(scriptHelper);
		AlfrescoMyTasksPageTest alfrescoTaskPage= new AlfrescoMyTasksPageTest(scriptHelper);
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String collectionsobj = dataTable.getData("MyFiles", "CreateFileDetails");
		String siteNameValue =  dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String errorfile = dataTable.getData("MyFiles", "Subfolders1");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");
		String Metadata = dataTable.getData("MyFiles", "FieldDataForAllProperties");
		File downloadedFile = null;
		Date date = new Date();
	    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
       	           
		boolean flag = false;
		       // Log in Pearson Schools project
			    functionalLibrary.loginAsValidUser(signOnPage);
			    UIHelper.waitForLong(driver);
	        	//Create site
				homePageObj.navigateToSitesTab();
				UIHelper.waitFor(driver);

		/*		String siteName = sitesPage.getCreatedSiteName();				
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
				UIHelper.pageRefresh(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
			    driver.navigate().refresh();
			   
			    sitesPage.enterIntoDocumentLibrary();
		
	
			    //go to course object 1
		             	           
	            myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Completed");
				myFiles.openCreatedFolder(currentDate.substring(0, 4));
				myFiles.openCreatedFolder(currentDate.substring(5, 7));
				myFiles.openCreatedFolder(currentDate.substring(8, 10));
				report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);
				
				/***************************Validate Language property through new course CSV Import *******************************/
	          				
				flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName,Option[0]);
				if(flag){
					report.updateTestLog("View Error Report :", "View Error Report is present "
							+ "for "+"<b> "+fileName, Status.FAIL);		
				}else{
					report.updateTestLog("View Error Report:", "View Error Report is not presented "
							+ "for "+"<b> "+fileName, Status.PASS);			
				}
				
				/*********************************Confirm Language property population****************************/
				/*********************************Modify Language property****************************************/
				
				// Go to Courses and navigate to Content Type
				sitesPage.enterIntoDocumentLibrary();
				sitesPage.documentdetailsColl(folderNames[0]);
				sitesPage.documentdetailsColl(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[2]);
				UIHelper.waitForPageToLoad(driver);
				// Test Sequence Object
				collectionPg.verifyMetadataValuesInEditAllProperties(folderNames[3],Metadata,"Yes");		

				/**********************************Confirm Language property export"*****************************/
				sitesPage.enterIntoDocumentLibrary();
				sitesPage.documentdetailsColl(folderNames[0]);
				sitesPage.documentdetailsColl(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnMoreSetting(folderNames[2]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], Option[0]);
				collectionPg.clickonrealizebox();

				sitesPage.enterIntoDocumentLibrary();

				for (String path : filePath1) {
					sitesPage.documentdetails(path);
				}
			
				// Navigate to generated csv file path and check whether
				sitesPage.documentdetails(currentDate);

				// CSVfile presence and filename
				String fileName1 = folderNames[2] + "-" + currentDate;
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
		
				String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
				try {
					if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"English",2,7))
						{
						report.updateTestLog("Confirm that the Language value (Column H) for 'Spanish Language Test Content Object 1' is English",
								"The Language value (Column H) for 'Spanish Language Test Content Object 1' is English", Status.PASS);
						}
					else
					{
						report.updateTestLog("Confirm that the Language value (Column H) for 'Spanish Language Test Content Object 1' is English",
								"The Language value (Column H) for 'Spanish Language Test Content Object 1' is not English", Status.FAIL);
					}
					
				}catch (IOException e) {
					report.updateTestLog("Confirm that the Language value (Column H) for 'Spanish Language Test Content Object 1' is English",
							"The Language value (Column H) for 'Spanish Language Test Content Object 1' is not English", Status.FAIL);		
						e.printStackTrace();
			}	
		}	  

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	}

