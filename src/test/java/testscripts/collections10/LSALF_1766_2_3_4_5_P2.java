package testscripts.collections10;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1766_2_3_4_5_P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_03() {
		testParameters.setCurrentTestDescription(
				"Confirm on clicking Edit properties for other than course object such as Container/Sequence/Learning Bundle/Content Object/Composite Object form collection UI via More options >Edit properties , the new dynamic course properties such as Level automation,Product Configuration and Program standards are not getting displayed.");

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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);

		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");

		
		String CourseXpath = "//*[@class='filename']//*[contains(text(),'AutoCourse')]";
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		homePageObj.navigateToSitesTab();

		// From the site Type dropdown select 'Collection Site'.
		//sitesPage.createSite(siteNameValue, "No");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		
				

		// Navigate to document library and click on a program>Program Object
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitFor(driver);
		
		String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);
		

		ArrayList<String> listOfObjects = new ArrayList<String>();
		listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
		for (String listOfObjectsString : listOfObjects) {
			
			if(listOfObjectsString.contains("AutoCourse")){
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
						"Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties);
				UIHelper.waitFor(driver);
				//Level Automation
				if (UIHelper.getTextFromWebElement(driver,"//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default-form-fields\"]/div[6]/div/div[1]").equals("Level Automation")) {
					report.updateTestLog("Level Automation","Level Automation Property is present",Status.PASS);
				}else {
					report.updateTestLog("Level Automation","Level Automation Property is not present",Status.FAIL);
				}
				UIHelper.waitFor(driver);
				//Product Configuration
				if (UIHelper.getTextFromWebElement(driver,"//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default-form-fields\"]/div[7]/div/div[1]").equals("Product Configuration")) {
					report.updateTestLog("Product Configuration","Product Configuration Property is present",Status.PASS);
				}else {
					report.updateTestLog("Product Configuration","Product Configuration Property is not present",Status.FAIL);
				}
				UIHelper.waitFor(driver);
				//Program Standards
				if (UIHelper.getTextFromWebElement(driver,"//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default-form-fields\"]/div[8]/div/div[1]").equals("Program Standards")) {
					report.updateTestLog("Program Standards","Program Standards Property is present",Status.PASS);
				}else {
					report.updateTestLog("Program Standards","Program Standards Property is not present",Status.FAIL);
				}
				//Click the Cancel button
				UIHelper.click(driver, "//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default-form-cancel-button\"]");
			}	
			
			if(listOfObjectsString.contains("AutoSeqObj") || listOfObjectsString.contains("AutoContentObj") || listOfObjectsString.contains("AutoLearningBundle") || listOfObjectsString.contains("AutoCompositeObj")){
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
						"Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties);
				UIHelper.waitFor(driver);
				collectionPgTest.verifyDynamicProperties();
			}
	}
}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
