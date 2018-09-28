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

public class ALFDEPLOY_4416_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_4416_TC001() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY_4416_TC001: Validate Import new Course CSV with Parent Descriptor on Root Node <br>"
				+ "ALFDEPLOY_4416_TC002: Confirm complete Course import <br> ");
				
				
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
		try{
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
					//Modified as part of NALS
				//	sitesPage.siteFinder("qa4416");			
					String siteNameValue =  dataTable.getData("Sites", "SiteName");	
					sitesPage.createSite(siteNameValue, "Yes");
					UIHelper.waitFor(driver);
					
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
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(currentDate.substring(0, 4));
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(currentDate.substring(5, 7));
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(currentDate.substring(8, 10));
					UIHelper.waitFor(driver);
					report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);
					
					flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName,"View Error Report");
					if(flag){
						report.updateTestLog("View Error Report :", "View Error Report is present "
								+ "for "+"<b> "+fileName, Status.FAIL);		
					}else{
						report.updateTestLog("View Error Report:", "View Error Report is not presented "
								+ "for "+"<b> "+fileName, Status.PASS);			
					}			
						
					//case 2: Confirm complete Course import
					sitesPage.enterIntoDocumentLibrary();
					myFiles.openCreatedFolder("Courses");
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder("ALFDEPLOY-4416 UAT Test Course");
					UIHelper.waitFor(driver);
					collectionPg.clickOnEditCollectionButton();	
					UIHelper.waitFor(driver);
					collectionPg.VerifyCollectionObjectList("ALFDEPLOY-4416 UAT Test Course,Test Sequence Object-1,Test Container,Test Learning Bundle,Test Content Object");				
				
					
									
					
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		

		@Override
		public void tearDown() {
			// TODO Auto-generated method stub

		}
	}



