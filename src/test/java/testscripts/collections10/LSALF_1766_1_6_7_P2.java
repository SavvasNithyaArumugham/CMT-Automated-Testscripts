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

public class LSALF_1766_1_6_7_P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_03() {
		testParameters.setCurrentTestDescription(
				"From ShareUI Confirm that the new dynamic course properties such as Level automation,Product Configuration and Program standards are not getting displayed on clicking Edit properties for other than course object form Share UI Via Document library>Container/Sequence/Learning Bundle/Content Object/Composite Object>Edit properties");

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
		//Courses/AutoCourse
				myFiles.openCreatedFolder(folderNames[0]);
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(folderNames[1]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[1],
						"View Details");
				UIHelper.waitFor(driver);
				//Click Edit Properties
				UIHelper.click(driver, "//*[@id=\"template_x002e_folder-actions_x002e_folder-details_x0023_default-actionSet\"]/div[1]/a/span");
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
				
				sitesPage.enterIntoDocumentLibrary();
				ArrayList<String> listOfObjects = new ArrayList<String>();
				listOfObjects=collectionPg.getFoldersFromLeftTreeInShareUi();
				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.contains("Composite Objects")||listOfObjectsString.equals("Content Objects")||listOfObjectsString.contains("Learning bundles")||listOfObjectsString.contains("Sequence Objects")){
						String object="";
						if(listOfObjectsString.equals("Composite Objects")) {
							object="AutoCompositeObj";
						}else if(listOfObjectsString.equals("Content Objects")) {
							object="AutoContentObj";
						}else if(listOfObjectsString.equals("Learning bundles")) {
							object="AutoLearningBundle";
						}else if(listOfObjectsString.equals("Sequence Objects")) {
							object="AutoSeqObj";
						}
						myFiles.openCreatedFolder(listOfObjectsString);
						UIHelper.waitFor(driver);
						myFiles.openCreatedFolder("a");
					//if(listOfObjectsString.contains("AutoSeqObj") || listOfObjectsString.contains("AutoContentObj") || listOfObjectsString.contains("AutoLearningBundle") || listOfObjectsString.contains("AutoCompositeObj")){
						UIHelper.waitFor(driver);
						collectionPg.clickOnMoreSetting(object);
						collectionPg.commonMethodForClickOnMoreSettingsOption(object,
								"View Details");
						UIHelper.waitFor(driver);
						//Click Edit Properties
						UIHelper.click(driver, "//*[@id=\"template_x002e_folder-actions_x002e_folder-details_x0023_default-actionSet\"]/div[1]/a/span");
						UIHelper.waitFor(driver);
						collectionPgTest.verifyDynamicProperties();
						sitesPage.enterIntoDocumentLibrary();
					}
				}
			
	
}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
