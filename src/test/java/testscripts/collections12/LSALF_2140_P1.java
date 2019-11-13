package testscripts.collections12;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_2140_P1 extends TestCase {

     private FunctionalLibrary functionalLibrary;

     @Test
     public void COLLECTIONS_01_02() {
           testParameters.setCurrentTestDescription("CMT Standards Correlation Reports");

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
        
           // Log in Pearson Schools project
           functionalLibrary.loginAsValidUser(signOnPage);


           String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
           String filepathfile = dataTable.getData("MyFiles", "uploadpath");
           String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
           String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
           String objectName = dataTable.getData("MyFiles", "FileName");
           String moreOptions = dataTable.getData("MyFiles", "MoreSettingsOption");
           String cmtforsinglegrade = dataTable.getData("MyFiles", "MoreSettingsOption2");
           
           Date date = new Date();
           String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
           File downloadedFile = null;
           ArrayList<String> csvFileRowDataList = null;

           String downloadFilePath = properties.getProperty("DefaultDownloadPath");
           
           // Create site

           homePageObj.navigateToSitesTab();
           String siteNameValue = dataTable.getData("Sites", "SiteName");
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
           sitesPage.enterIntoDocumentLibrary();

           // go to Course plan
           myFiles.openCreatedFolder(folderNames[0]);
           myFiles.openCreatedFolder(folderNames[1]);
           collectionPg.clickOnEditCollectionButton();


           //Click on edit properties//all properties//enter the cmt id
           String allProperties = ".//a[contains(text(),'All Properties...')]";
           collectionPg.clickOnMouseOverMenu(objectName,"Edit Properties");
           UIHelper.waitFor(driver);
           UIHelper.click(driver, allProperties);      
           UIHelper.waitFor(driver);
     driver.findElement(By.name("prop_cpnals_cmtStandards")).sendKeys(cmtforsinglegrade);
           UIHelper.click(driver, "//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default-form-submit-button\"]");
           UIHelper.waitFor(driver);
           // Click on Generate Standards Report for course object
           collectionPg.clickOnMoreSetting(objectName);
       collectionPg.commonMethodForClickOnMoreSettingsOption(objectName,moreOptions);
           
           // Navigate to Document library
           sitesPage.enterIntoDocumentLibrary();

           for (String path : filePath) {
                sitesPage.documentdetails(path);
           }

           // Navigate to generated csv file path and check whether
           sitesPage.documentdetails(currentDate);

           // CSVfile presence and filename
           String fileName1 = objectName + "-" + currentDate;
           /*String filenameCSV = mediaTransPage.RetreiveFilename(fileName1);
           String filename2 = collectionPg.CSVFilename(fileName1);
           */collectionPg.clickOnMoreSetting(fileName1);
           collectionPg.commonMethodForClickOnMoreSettingsOption(fileName1, "Download");

           new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, fileName1);
           downloadedFile = new File(downloadFilePath + "/" + fileName1);
           /*if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
                report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
                csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filenameCSV);
                String downloadedCSVFileANmeWithPath =downloadFilePath + "/" + filename2;
                try {
                     
                     CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "EM", 6, 12);
                     
                } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                }*/
                
           }

     @Override
     public void tearDown() {
           // TODO Auto-generated method stub

     }
}

