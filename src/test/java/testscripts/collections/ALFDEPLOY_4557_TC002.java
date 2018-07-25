package testscripts.collections;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_4557_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_4557_TC002() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY_4557_TC001: Validate Import new Course CSV: ALFEDPLOY-4557 UAT Test Course <br>"
				+ "ALFDEPLOY_4557_TC002: Prepare Generate Realize Csv for course object <br> " 
				+ "ALFDEPLOY_4557_TC003: Validate that non required properties with Delete command not produce an error <br> "
				+ "ALFDEPLOY_4557_TC004: Validate that non-required properties with Delete command have no values <br> "
				+ "ALFDEPLOY_4557_TC005: Validate that required properties with Delete command produce an error, and retain values <br> "
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
			AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
			AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			String errorfile = dataTable.getData("MyFiles", "Subfolders1");
			String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
			File downloadedFile = null;
			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,errorfile);
			ArrayList<String> csvFileRowDataList = null;
			Date date = new Date();
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String[] uploadName = dataTable.getData("MyFiles", "uploadpath").split("/");
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String fileNamewithNonMandatory = dataTable.getData("MyFiles", "Subfolders2");
			String processingindicatorxpath = "//*[@title='Processing import spreadsheet']";	
			String allProperties = ".//a[contains(text(),'All Properties...')]";
			String sqobject = "//*[@class='filename']//span";
			String downloadFilePath = properties.getProperty("DefaultDownloadPath");
			boolean flag = false;
			// Log in Pearson Schools project
			functionalLibrary.loginAsValidUser(signOnPage);						
					
			//Create site
			UIHelper.waitForLong(driver);
		
			homePageObj.navigateToSitesTab();	
			UIHelper.waitFor(driver);
			sitesPage.siteFinder("qa4557");			
		
			// Go to collection UI
			sitesPage.enterIntoDocumentLibrary();
			
			// go to Course plan
			
			myFiles.openCreatedFolder(folderNames[0]);
			UIHelper.waitForPageToLoad(driver);
			myFiles.openCreatedFolder(folderNames[1]);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		
			
			// upload course plan
			collectionPg.uploadFileInCollectionSite(filePath, fileName);				
			UIHelper.waitForLong(driver);						
			UIHelper.pageRefresh(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);	
						
			//go to course object 1
			
			sitesPage.enterIntoDocumentLibrary();
			UIHelper.waitFor(driver);					
			myFiles.openCreatedFolder(folderNames[0]);
			UIHelper.waitFor(driver);
			myFiles.openCreatedFolder(folderNames[2]);
			UIHelper.waitFor(driver);
			myFiles.openCreatedFolder(currentDate.substring(0, 4));
			myFiles.openCreatedFolder(currentDate.substring(5, 7));
			myFiles.openCreatedFolder(currentDate.substring(8, 10));
			report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);					
			UIHelper.waitFor(driver);
			flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName,"View Error Report");
			if(flag){
				report.updateTestLog("View Error Report :", "View Error Report is present "
						+ "for "+"<b> "+fileName, Status.FAIL);		
			}else{
				report.updateTestLog("View Error Report:", "View Error Report is not presented "
						+ "for "+"<b> "+fileName, Status.PASS);			
			}		
			
			
			//case 2: Generate Realize Csv for course object
			
			//Apply version state filter
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder("Programs");
			UIHelper.waitFor(driver);
			myFiles.openCreatedFolder("Program");
			UIHelper.waitFor(driver);
			collectionPg.clickOnEditCollectionButton();	
			UIHelper.waitFor(driver);
			
			// Click on Generate Realize Csv for course object
					collectionPg.clickOnBrowseActionsInCollectionUI("ALFDEPLOY-4557 - UAT Test Course", "Generate Realize CSVs");
					UIHelper.waitFor(driver);
					collectionPg.clickonrealizebox();
					UIHelper.waitFor(driver);
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
					String fileName1 = "ALFDEPLOY-4557 - UAT Test Course" + "-" + currentDate;	
					//String filenameCSV= mediaTransPage.RetreiveFilename(fileName1);
					
					String filename2= collectionPg.CSVFilename(fileName1);
					
					collectionPg.clickOnMoreSetting(filename2);
					collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"Download");
			
					new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
					downloadedFile = new File(downloadFilePath + "/" + filename2);
					if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
						report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
					}	
					
}

					
								

@Override
public void tearDown() {
	// TODO Auto-generated method stub

}
}