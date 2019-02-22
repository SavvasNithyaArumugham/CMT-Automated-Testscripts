package testscripts.collections9;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1767_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("");
		
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
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String levelOne = dataTable.getData("MyFiles", "MoreSettingsOption3");
		String levelTwo = dataTable.getData("MyFiles", "RelationshipName");
		String numFormat  = dataTable.getData("MyFiles", "BrowseActionName");
		String startValue = dataTable.getData("MyFiles", "Sort Options");
		String restartAtLevel = dataTable.getData("MyFiles", "AccessToken");
		String productType  = dataTable.getData("MyFiles", "PopUpMsg");
		String cmtStandards = dataTable.getData("MyFiles", "StatusReportValue");
				
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		String saveButton = "//*[contains(@id,'default-form-submit-button')]";
		String skillsPath = ".//*[contains(@name,'skillsPath')]";
		String disciplizeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_discipline')]";
		String addButtonXpath =".//*[contains(@id,'default_prop_cpnals_levelAutomationCollection-add-level-automation\')]/span";
		String addProductButtonXpath = ".//*[contains(@id,'prop_cpnals_productConfigurationCollection-add-product-configuration')]/span";
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		// upload course plan
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		
		UIHelper.waitFor(driver);
	// Click on Generate Realize Csv for course object
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		
		collectionPg.clickOnMouseOverMenu("CCP Test Course 4 - ALFDEPLOY-3440","Edit Properties");
		
		UIHelper.click(driver, allProperties);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.sendKeysToAnElementByXpath(driver,skillsPath,"test");
		UIHelper.selectbyVisibleText(driver,disciplizeDropdownXpathInCreateObject,"Math");
		
		String levelLable1Xpath =".//*[contains(@id,'levelAutomationCollection-label-1')]";
		UIHelper.sendKeysToAnElementByXpath(driver,levelLable1Xpath,"test");
		
		UIHelper.click(driver,addButtonXpath);
		UIHelper.waitFor(driver);
		boolean isLevel1Available = collectionPg.isObjectFieldAvailable("Level 1 Label:");
		boolean isLevel2Available = collectionPg.isObjectFieldAvailable("Level 2 Label:");
		boolean isNumberingFormatAvailable = collectionPg.isObjectFieldAvailable("Numbering Format:");
		boolean isStartValueAvailable = collectionPg.isObjectFieldAvailable("Start Value:");
		boolean isRestartatLevelAvailable = collectionPg.isObjectFieldAvailable("Restart at Level:");
		
		
		if((isLevel1Available = true) && (isLevel2Available = true) && (isNumberingFormatAvailable = true) && (isStartValueAvailable = true)
				&& (isRestartatLevelAvailable = true))
		{
			report.updateTestLog("Verify Level Automation fields",	"Verify Level Automation fields available", Status.PASS);
		}else
		{
			report.updateTestLog("Verify Level Automation fields", "Verify Level Automation fields not available", Status.FAIL);
		}
			
		UIHelper.click(driver,addButtonXpath);
		UIHelper.waitFor(driver);
		boolean isLevel3Available = collectionPg.isObjectFieldAvailable("Level 3 Label:");
		boolean isNumberingFormattAvailable = collectionPg.isObjectFieldAvailable("Numbering Format:");
		boolean isStartValueeAvailable = collectionPg.isObjectFieldAvailable("Start Value:");
		boolean isRestartatLevellAvailable = collectionPg.isObjectFieldAvailable("Restart at Level:");
		
			
			boolean isProductTypeAvailable = collectionPg.isObjectFieldAvailable("Product Type:");
		
			if(isProductTypeAvailable = true) 
			{
				report.updateTestLog("Verify Product Configuration fields",	"Verify Product Configuration fields available", Status.PASS);
			}else
			{
				report.updateTestLog("Verify Product Configuration fields", "Verify Product Configuration fields not available", Status.FAIL);
			}
			
			UIHelper.click(driver,addProductButtonXpath);
			UIHelper.waitFor(driver);
			boolean isProductType1Available = collectionPg.isObjectFieldAvailable("Product Type:");
			
			boolean isCMTStandardsAvailable = collectionPg.isObjectFieldAvailable("CMT Standards:");
			
			if(isCMTStandardsAvailable = true) 
			{
				report.updateTestLog("Verify Program Standards fields",	"Verify Program Standards fields available", Status.PASS);
			}else
			{
				report.updateTestLog("Verify Program Standards fields", "Verify Program Standards fields not available", Status.FAIL);
			}
					
			collectionPg.enterDataForCreateCourseObjectNewFields(levelOne,levelTwo,numFormat,startValue,restartAtLevel,productType,cmtStandards);
			
			UIHelper.click(driver, saveButton);
	}
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
