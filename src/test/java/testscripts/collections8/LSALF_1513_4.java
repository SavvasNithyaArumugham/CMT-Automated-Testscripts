package testscripts.collections8;

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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1513_4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_033() {
		testParameters
		.setCurrentTestDescription("	generate a Realize csv and see that there is a Rubrics URL/URL generated that points to the file object");
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
		Date date = new Date();
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
	
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");					
		sitesPage.openSiteFromRecentSites(siteNameValue);

				// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();

				// Click on Generate Realize Csv for course object
				
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				collectionPg.openCollectionObject(folderNames[5]);
				
				String courseObjectName = dataTable.getData("MyFiles", "MoreSettingsOption2");
				String urlValue = "https://usppewip.pearsoncms.com/alfresco/api/-default-/public/cmis/versions/1.1/browser/root?objectId=workspace://SpacesStore/";			
				String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				File downloadedFile = null;
				UIHelper.waitFor(driver);
				//collectionPg.clickOnBrowseActionsInCollectionUI(courseObjectName, browseActionName);
				UIHelper.click(driver,"//*[@id=\"assembly-view-secondary-toolbar-metadata-button-button\"]");
				
				collectionPg.clickonDetailsRealizeBox();
				// Navigate to Document library
				
				sitesPage.enterIntoDocumentLibrary();

				for (String path : filePath) {
					sitesPage.documentdetails(path);
				}
				
				// Navigate to generated csv file path and check whether
				sitesPage.documentdetails(currentDate);
				
				// CSVfile presence and filename
				String fileName1 = courseObjectName + "-" + currentDate;	
				String filenameCSV= mediaTransPage.RetreiveFilename(fileName1);
				String filename2= collectionPg.CSVFilename(fileName1);
				collectionPg.clickOnMoreSetting(filename2);
				collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"Download");
				
				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				downloadedFile = new File(downloadFilePath + "/" + filename2);
				
				if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
					//***************************Confirm URl/RubricsURLare exporting properly*********************//
					String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
					try {
						if(CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath,urlValue,2,46))
							{
							report.updateTestLog("Confirm url is exporting properly ",
									"Column AU (URL) value"+urlValue, Status.PASS);
							}else {

								report.updateTestLog("Confirm url is not exporting properly ",
										"Column AU (URL) value"+urlValue, Status.FAIL);
							}
						
						if(CSVUtil.verifyDataInCellContains(downloadedCSVFileANmeWithPath,urlValue,2,47))
						{
						report.updateTestLog("Confirm rubricsurl is exporting properly ",
								"Column AV (RubricsURL) value"+urlValue, Status.PASS);
						}else {

							report.updateTestLog("Confirm rubricsurl is not exporting properly ",
									"Column AV (RubricsURL) value"+urlValue, Status.FAIL);
						}
					} catch (IOException e) {
					
						e.printStackTrace();
					}
						
				}


	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
