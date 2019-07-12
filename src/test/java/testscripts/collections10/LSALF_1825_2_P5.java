package testscripts.collections10;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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

public class LSALF_1825_2_P5 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_1770() {
		testParameters.setCurrentTestDescription(
			"Confirm that all of the below JSON-exportable properties are available in JSON on successful JSON export for a course, sequence  and learning bundle object:"
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
		String createObjectDataDC = dataTable.getData("MyFiles", "CollectionObjectAdditionalData");
		String createObjectDataC = dataTable.getData("MyFiles", "FieldDataForAllProperties");
		String createObjectDataCO = dataTable.getData("MyFiles", "FieldDataForQuickEdit");		
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		String dynamiccontentTypeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_dynamicContentType')]";
		String folioprefixXpath = ".//*[contains(@id,'prop_cpnals_folioPrefix')]";
		String foliostyleXpath = ".//*[contains(@id,'prop_cpnals_folioStyle')]";
		String foliostartXpath = ".//*[contains(@id,'prop_cpnals_folioStart')]";
		String tocIncludeFromXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeFrom')]";
		String tocIncludeToXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeTo')]";
		String folioprefix="";
		String foliostyle="1";
		String foliostart="";
				// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		// Select Sites > Create Site from top black menu.
		homePageObj.navigateToSitesTab();

		// From the site Type dropdown select 'Collection Site'.
		//sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		// Navigate to document library and click on a program>Program Object
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();	
		collectionPg.openCollectionObject("AutoCourse");
		UIHelper.waitFor(driver);
		//collectionPg.openCollectionObject("AutoDCObj");
		collectionPg.clickOnMoreSetting("AutoDCObj");
		collectionPg.commonMethodForClickOnMoreSettingsOption("AutoDCObj",
				"Edit Properties");
		UIHelper.waitFor(driver);
		UIHelper.click(driver, allProperties);
		UIHelper.waitFor(driver);
			Select selectBox = new Select(driver.findElement(By.xpath(dynamiccontentTypeDropdownXpathInCreateObject)));
			selectBox.selectByIndex(1);

		if (!folioprefix.isEmpty()) {
			UIHelper.sendKeysToAnElementByXpath(driver, folioprefixXpath,
					folioprefix);
		}
		
		if (!foliostyle.isEmpty()) {
			Select selectBox1 = new Select(driver.findElement(By.xpath(foliostyleXpath)));
			selectBox1.selectByIndex(1);	
		}
		
		if (!foliostart.isEmpty()) {
			UIHelper.sendKeysToAnElementByXpath(driver, foliostartXpath,
					foliostart);
		}

		//if (!tocIncludeFrom.isEmpty()) {
			Select selectBox2 = new Select(driver.findElement(By.xpath(tocIncludeFromXpath)));
			selectBox2.selectByIndex(0);
		//} 
		
		//if (!tocIncludeTo.isEmpty()) {
			Select selectBox3 = new Select(driver.findElement(By.xpath(tocIncludeToXpath)));
			selectBox3.selectByIndex(0);
		//}				
		collectionPg.clickOnSaveBtn();
		UIHelper.waitFor(driver);
		collectionPg.openCollectionObject("AutoDCObj");
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting("AutoContainer");
		collectionPg.commonMethodForClickOnMoreSettingsOption("AutoContainer",
				"Edit Properties");
		UIHelper.waitFor(driver);
		UIHelper.click(driver, allProperties);
		UIHelper.waitFor(driver);
		
		if (!foliostart.isEmpty()) {
			UIHelper.sendKeysToAnElementByXpath(driver, foliostartXpath,
					foliostart);
		}
		collectionPg.clickOnSaveBtn();
		UIHelper.waitFor(driver);
		
		collectionPg.openCollectionObject("AutoContainer");
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting("AutoContentObj");
		collectionPg.commonMethodForClickOnMoreSettingsOption("AutoContentObj",
				"Edit Properties");
		UIHelper.waitFor(driver);
		UIHelper.click(driver, allProperties);
		UIHelper.waitFor(driver);
		
		if (!folioprefix.isEmpty()) {
			UIHelper.sendKeysToAnElementByXpath(driver, folioprefixXpath,
					folioprefix);
		}
		
		if (!foliostyle.isEmpty()) {
			Select selectBox4 = new Select(driver.findElement(By.xpath(foliostyleXpath)));
			selectBox4.selectByIndex(1);	
		}
		
		if (!foliostart.isEmpty()) {
			UIHelper.sendKeysToAnElementByXpath(driver, foliostartXpath,
					foliostart);
		}
		collectionPg.clickOnSaveBtn();
		UIHelper.waitFor(driver);
		
		driver.findElement(By.linkText("Program")).click();
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting("AutoCourse");
		UIHelper.waitFor(driver);
		collectionPg.commonMethodForClickOnMoreSettingsOption("AutoCourse","Generate Bookbuild");
		}


	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
