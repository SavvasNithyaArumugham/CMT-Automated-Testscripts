package testscripts.collections11;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_2123_3SeqCSV extends TestCase {

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void COLLECTIONS_05() {
		testParameters
				.setCurrentTestDescription("Confirm Library property export with multi select values separated with pipe symbol");
		
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
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
		
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");					
		sitesPage.openSiteFromRecentSites( siteNameValue);

				sitesPage.enterIntoDocumentLibrary();
				
				
				String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
				String filepathfile = dataTable.getData("MyFiles", "uploadpath");
				String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
				String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				String objectName = dataTable.getData("MyFiles", "FileName");
				String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
				Date date = new Date();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				File downloadedFile = null;		
				String downloadFilePath = properties.getProperty("DefaultDownloadPath");
			
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
				String filename2= collectionPg.CSVFilename(fileName1);
				collectionPg.clickOnMoreSetting(filename2);
				collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"Download");
				
				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				downloadedFile = new File(downloadFilePath + "/" + filename2);
				if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
														
					/***************************Confirm Library properties are exporting properly*********************/
					String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
					try {
						if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"Library",0,98))
							{
							report.updateTestLog("Confirm Library property is exporting properly ",
									"Column CU (Library)", Status.PASS);
							}else {
								report.updateTestLog("Confirm Library property is not exporting properly ",
										"Column CU (Library)", Status.FAIL);
							}
						
						if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"Core Texts|Hook and Inspire Texts",3,98))
						{
						report.updateTestLog("Confirm Library property value is exporting properly ",
								"Column CU (Library):"+CSVUtil.readDataInCell(downloadedCSVFileANmeWithPath,3,98), Status.PASS);
						}else {
							report.updateTestLog("Confirm Library property value is not exporting properly ",
									"Column CU (Library):"+CSVUtil.readDataInCell(downloadedCSVFileANmeWithPath,3,98), Status.FAIL);
						}
					} catch (IOException e) {
					
						e.printStackTrace();
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
