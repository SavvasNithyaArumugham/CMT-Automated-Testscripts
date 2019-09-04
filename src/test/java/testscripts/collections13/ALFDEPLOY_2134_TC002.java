package testscripts.collections13;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2134_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Verify the addition of Release Version Export Filter is available on the Generate Realise to CSV popup(Including and after...)");
		
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		
	//  login
				functionalLibrary.loginAsValidUser(signOnPage);
				
				// goto site > document lib
				homePageObj.navigateToSitesTab();
				String siteNameValue = dataTable.getData("Sites", "SiteName");		
				sitesPage.createSite(siteNameValue, "Yes");
				sitesPage.enterIntoDocumentLibrary();
						
				// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");
				// upload course plan
				String filePath = dataTable.getData("Sites", "InviteUserName");
				String fileName = dataTable.getData("Sites", "Role");
				
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				sitesPage.enterIntoDocumentLibrary();
				String folderNames[] = dataTable.getData("Sites", "CreateSiteLabelNames").split(",");
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitFor(driver);
				collectionPg.openCollectionObject(folderNames[2]);
				
			String allProperties = ".//a[contains(text(),'All Properties...')]";
			String releaseVersionXpath = ".//*[contains(@id,'default_prop_cpnals_releaseVersion')]";
			String saveButton = "//*[contains(@id,'default-form-submit-button')]";
			String objectNames[] = dataTable.getData("Sites", "CreateSiteTypeValues").split(",");	
			String moreSettingOptions[] = dataTable.getData("Sites", "TargetFolder").split(",");	
			
			//Sequence Object  release version update
			collectionPg.clickOnMouseOverMenu(objectNames[0], moreSettingOptions[1]);
			UIHelper.click(driver, allProperties);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.sendKeysToAnElementByXpath(driver,releaseVersionXpath,"10.8");
			UIHelper.click(driver, saveButton);		
				
			//Learning Bundle object release version update
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(folderNames[0]);
			myFiles.openCreatedFolder(folderNames[1]);
			collectionPg.clickOnEditCollectionButton();
			collectionPg.openCollectionObject(folderNames[2]);
			collectionPg.clickOnMouseOverMenu(objectNames[1], moreSettingOptions[1]);
			UIHelper.click(driver, allProperties);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.sendKeysToAnElementByXpath(driver,releaseVersionXpath,"10.9");
			UIHelper.click(driver, saveButton);
					
			//Content object release version update
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(folderNames[0]);
			myFiles.openCreatedFolder(folderNames[1]);
			collectionPg.clickOnEditCollectionButton();
			collectionPg.openCollectionObject(folderNames[2]);
			collectionPg.clickOnMouseOverMenu(objectNames[2], moreSettingOptions[1]);
			UIHelper.click(driver, allProperties);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.sendKeysToAnElementByXpath(driver,releaseVersionXpath,"10.11");
			UIHelper.click(driver, saveButton);

			// Generate Realize CSVs
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(folderNames[0]);
			myFiles.openCreatedFolder(folderNames[1]);
			collectionPg.clickOnEditCollectionButton();
			
			collectionPg.clickOnMoreSetting(folderNames[2]);
			collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], moreSettingOptions[0]);
			
			String releseVersionCheckBox = ".//*[contains(@id,'generate-manifest-release-version-checkbox')]";
			UIHelper.click(driver,releseVersionCheckBox);
			UIHelper.waitFor(driver);
					
			String versionTextBox = "//*[@id='template_x002e_detailed-list-view_x002e_assembly-view_x0023_default-generate-manifest-release-version']";
			UIHelper.sendKeysToAnElementByXpath(driver,versionTextBox, "10.9");
			UIHelper.waitFor(driver);
				
			String versionDropDown = "//*[@id='template_x002e_detailed-list-view_x002e_assembly-view_x0023_default-generate-manifest-release-version-select']";
			UIHelper.click(driver,versionDropDown);
			UIHelper.waitFor(driver);
				
			UIHelper.selectbyVisibleText(driver,versionDropDown,"Including and after...");
			UIHelper.waitFor(driver);
				
			String okButtonXpath = "//*[@id='template_x002e_detailed-list-view_x002e_assembly-view_x0023_default-generate-manifest-ok-button-button']";
			UIHelper.click(driver,okButtonXpath);
			
			sitesPage.enterIntoDocumentLibrary();
			
			myFiles.openCreatedFolder("Data Exports");
			myFiles.openCreatedFolder("Realize Export");
			myFiles.openCreatedFolder("Batch 4 Test Course");
						
			// Navigate to generated csv file path and check whether
			Date date = new Date();
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
					sitesPage.documentdetails(currentDate);

					// CSVfile presence and filename
					String objectName = dataTable.getData("Sites", "FileName");
					String fileName1 =objectName + "-" + currentDate;
					String filename2 = mediaTransPage.RetreiveFilename(fileName1);
					UIHelper.waitFor(driver);
					collectionPg.commonMethodForClickOnMoreSettingsOption(filename2, "Download");
					File downloadedFile = null;
					String downloadFilePath = properties.getProperty("DefaultDownloadPath");
					
					new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
					downloadedFile = new File(downloadFilePath + "/" + filename2);
								
					UIHelper.waitFor(driver);
				
		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
	try {
		if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"10.9",5,28))
			{
report.updateTestLog("Release version Export Filter check","Pass", Status.PASS);
			}
		else
		{
report.updateTestLog("Release version Export Filter check",	"Fail", Status.FAIL);
		}
		
	}catch (IOException e) {
		report.updateTestLog("Confirm that export values are as expected",
				"Export CSV values are not as expected", Status.FAIL);	
			e.printStackTrace();
}	
	
	}
	
		
	
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}