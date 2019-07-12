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

public class LSALF_1770_1To10Tcs_P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_03() {
		testParameters.setCurrentTestDescription(
				"Verify the below tooltip text is displayed for \"Maintain Orientation with Fillers\" field by placing the mouse cursor on the label");

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
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		homePageObj.navigateToSitesTab();

		// From the site Type dropdown select 'Collection Site'.
		//sitesPage.createSite(siteNameValue, "No");
		//String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		// Navigate to document library and click on a program>Program Object
				sitesPage.enterIntoDocumentLibrary();
				String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				
				String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");

				
				String CourseXpath = "//*[@class='filename']//*[contains(text(),'AutoCourse')]";
				String allProperties = ".//a[contains(text(),'All Properties...')]";
				
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				// Click on "Edit collection"
				collectionPgTest.verifyEditCollectionOption();
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitFor(driver);
				
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
						//Level Automation ToolTip
						String levelAutomationXPath="//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_levelAutomationCollection-label-2\"]";
						UIHelper.waitFor(driver);
						WebElement levelAutomation = driver.findElement(By.xpath(levelAutomationXPath));
						
						UIHelper.highlightElement(driver, levelAutomationXPath);
						UIHelper.mouseOveranElement(driver, levelAutomation);
						
						if (levelAutomation.getAttribute("title").equals("Use this field to declare what the Level will be called (e.g. Unit, Lesson, etc.)")) {
							report.updateTestLog("Level Automation","Level Automation ToolTip is correct",Status.PASS);
						}else {
							report.updateTestLog("Level Automation","Level Automation ToolTip is not correct",Status.FAIL);
						}
						
									
						UIHelper.waitFor(driver);
						//Level Automation Numbering Format
						String levelAutomationnumberingXPath="//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_levelAutomationCollection-numbering-format-2\"]";
						UIHelper.waitFor(driver);
						WebElement levelAutomationnumbering = driver.findElement(By.xpath(levelAutomationnumberingXPath));
						
						UIHelper.highlightElement(driver, levelAutomationnumberingXPath);
						UIHelper.mouseOveranElement(driver, levelAutomationnumbering);
						
						if (levelAutomationnumbering.getAttribute("title").equals("Use this field to declare how to display the level's incrementing value (e.g. Unit 1, Module A, or Section I, etc.)")) {
							report.updateTestLog("Level Automation Numbering","Level Automation Numbering ToolTip is correct",Status.PASS);
						}else {
							report.updateTestLog("Level Automation Numbering","Level Automation Numbering ToolTip is not correct",Status.FAIL);
						}
						UIHelper.waitFor(driver);
						//Start value
						String startValueXPath="//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_levelAutomationCollection-start-value-2\"]";
						UIHelper.waitFor(driver);
						WebElement startValue = driver.findElement(By.xpath(startValueXPath));
						
						UIHelper.highlightElement(driver, startValueXPath);
						UIHelper.mouseOveranElement(driver, startValue);
						
						if (startValue.getAttribute("title").equals("Use this field to declare where to start counting from for this level (e.g. value of 1 means starting with Unit 1, or Module A, or Section I, etc.)")) {
							report.updateTestLog("StartValue","StartValue Tooltip is present",Status.PASS);
						}else {
							report.updateTestLog("StartValue","StartValue is not present",Status.FAIL);
						}
						
						//Maintain Orientation Fillers
						String maintainorientationFillersXPath="//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_productConfigurationCollection-maintain-orientation-1\"]";
						UIHelper.waitFor(driver);
						WebElement maintainorientationFiller = driver.findElement(By.xpath(maintainorientationFillersXPath));
						
						UIHelper.highlightElement(driver, maintainorientationFillersXPath);
						UIHelper.mouseOveranElement(driver, maintainorientationFiller);
						
						if (maintainorientationFiller.getAttribute("title").equals("Check this box if your product should retain the page's recto-verso orientation and insert filler pages to resolve collisions. Uncheck to force continuous pagination, which may place pages in orientations they were not designed for.")) {
							report.updateTestLog("Maintain Orientation Fillers","Maintain Orientation Fillers ToolTip is present",Status.PASS);
						}else {
							report.updateTestLog("Maintain Orientation Fillers","Maintain Orientation Fillers ToolTip is not present",Status.FAIL);
						}
						//Restart Level
						String restartLevelXPath="//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_levelAutomationCollection-restart-level-2\"]";
						UIHelper.waitFor(driver);
						WebElement restartLevel = driver.findElement(By.xpath(restartLevelXPath));
						
						UIHelper.highlightElement(driver, restartLevelXPath);
						UIHelper.mouseOveranElement(driver, restartLevel);
						
						if (restartLevel.getAttribute("title").equals("Use this field to declare if/when to restart counting for this level (e.g. restart counting Level 3 \"Lessons\" at each Level 2 \"Unit\")")) {
							report.updateTestLog("Restart Level","Restart Level ToolTip is present",Status.PASS);
						}else {
							report.updateTestLog("Restart Level","Restart Level ToolTip is not present",Status.FAIL);
						}
						
						//CMT Standards
						
						String cmtStdXPath="//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_cmtStandards\"]";
						UIHelper.waitFor(driver);
						WebElement cmtStd = driver.findElement(By.xpath(cmtStdXPath));
						
						UIHelper.highlightElement(driver, cmtStdXPath);
						UIHelper.mouseOveranElement(driver, cmtStd);
						
						if (cmtStd.getAttribute("title").equals("For Print products, paste CMT URL(s) for desired Standards to use in export manifest. Separate multiple URL's with pipe characters.")) {
							report.updateTestLog("Product Configuration","Product Configuration Property is present",Status.PASS);
						}else {
							report.updateTestLog("Product Configuration","Product Configuration Property is not present",Status.FAIL);
						}
						
						//Click the Cancel button
						UIHelper.click(driver, "//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default-form-cancel-button\"]");
					}
				}
		}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
