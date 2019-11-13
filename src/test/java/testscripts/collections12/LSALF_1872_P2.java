package testscripts.collections12;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class LSALF_1872_P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_01_02() {
		testParameters.setCurrentTestDescription("");

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
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);


		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);


		String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
		String filepathfile = dataTable.getData("MyFiles", "uploadpath");
		String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectName = dataTable.getData("MyFiles", "FileName");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		
		// Create site

		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String preRequisitesiteNameValue = dataTable.getData("Sites", "TargetSiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		
		String folderUpButtonXpath =".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-folderUp-button')]";		
		String childRefOkXpath = ".//*[@type='button' and contains(.,'OK')]";

		
		String siteXPath = ".//*[@class='yui-g']//tbody[@class='yui-dt-data']//h3//a[text()='"+preRequisitesiteNameValue+"']";
		String siteDocLibraryXpath =".//*[@class='yui-g']//tbody[@class='yui-dt-data']//h3//a[text()='documentLibrary']";
		String contentObjXpath=".//*[@class='yui-g']//tbody[@class='yui-dt-data']//h3//a[text()='Content Objects']";
		String contentObjInnerXpath=".//*[@class='yui-g']//tbody[@class='yui-dt-data']//h3//a[text()='c']";
		String contentObjInnerObjXpath=".//*[@class='yui-g']//tbody[@class='yui-dt-data']//h3//a[text()='c']";
		String addIcon= ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[@title='Add']";

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
		
		collectionPg.openCollectionObject("Course Object 1");
		collectionPg.clickOnMoreSetting("Content Object 1");
		UIHelper.waitFor(driver);
		collectionPg.commonMethodForClickOnMoreSettingsOption("Content Object 1","Child references");
		UIHelper.waitFor(driver); 
				
		//Click on upFolder
		UIHelper.waitForVisibilityOfEleByXpath(driver, folderUpButtonXpath);
		UIHelper.click(driver, folderUpButtonXpath);
		UIHelper.waitFor(driver);
		//Click on upFolder
		UIHelper.waitForVisibilityOfEleByXpath(driver, folderUpButtonXpath);
		UIHelper.click(driver, folderUpButtonXpath);
		UIHelper.waitForLong(driver);
		
		//Click on Sites
		UIHelper.waitFor(driver);
		System.out.println(collectionPg.addCrossSiteReferenceAsChild(preRequisitesiteNameValue));
		UIHelper.waitFor(driver);	
		UIHelper.click(driver, siteXPath);
		UIHelper.waitForLong(driver);
		UIHelper.click(driver, siteDocLibraryXpath);
		UIHelper.waitFor(driver);
		UIHelper.click(driver, contentObjXpath);
		UIHelper.waitFor(driver);
		UIHelper.click(driver, contentObjInnerXpath);
		UIHelper.waitFor(driver);
		UIHelper.click(driver, contentObjInnerObjXpath);
		UIHelper.waitFor(driver);
		UIHelper.click(driver, addIcon);
		UIHelper.waitFor(driver);
		// Click on "OK"
		UIHelper.click(driver, childRefOkXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.clickOnMoreSetting(folderNames[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], "Generate Realize CSVs");
		collectionPg.clickonprintcsvrealizebox();
		UIHelper.waitFor(driver);
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();

		for (String path : filePath) {
			sitesPage.documentdetails(path);
		}

		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(currentDate);

		// CSVfile presence and filename
		String fileName1 = objectName + "-" + currentDate;
		String filenameCSV = mediaTransPage.RetreiveFilename(fileName1);
		String filename2 = collectionPg.CSVFilename(fileName1);
		collectionPg.clickOnMoreSetting(filename2);
		collectionPg.commonMethodForClickOnMoreSettingsOption(filename2, "Download");
		
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);
		if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filenameCSV);
			String downloadedCSVFileANmeWithPath =downloadFilePath + "/" + filename2;
			try {
				
				CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, "Keywords", 3, 46);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sitesPage.enterIntoDocumentLibrary();

			myFiles.openCreatedFolder("Data Imports");
			myFiles.openCreatedFolder("Course Plan");

			myFiles.uploadFile(downloadFilePath + "/", filename2);

			UIHelper.waitFor(driver);
			
			homePageObj.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(preRequisitesiteNameValue);
			
			sitesPage.enterIntoDocumentLibrary();

			myFiles.openCreatedFolder(folderNames[0]);
			myFiles.openCreatedFolder(folderNames[1]);
			collectionPg.clickOnEditCollectionButton();

			collectionPg.openCollectionObject("Batch 4 Test Course");
			collectionPg.openCollectionObject("Sequence Object_2641");
			collectionPg.openCollectionObject("Container Object_2641");
			
			collectionPg.clickOnMoreSetting("Content Object_2641_e");
			collectionPg.commonMethodForClickOnMoreSettingsOption("Content Object_2641_e",
					"View Details");
			UIHelper.waitFor(driver);
			
			collectionPg.verifyObjectsInViewDetails("//*[@id=\"template_x002e_folder-metadata_x002e_folder-details_x0023_default-formContainer-form-fields\"]/div[1]/div[9]/div/span[2]","Keywords");
			report.updateTestLog("Verify download file", "File: " + filename2 + "successfull", Status.PASS);
		}
			else {
			report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
