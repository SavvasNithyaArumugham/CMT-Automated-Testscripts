package testscripts.collections8;

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

public class LSALF_1709_4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_04() {
		testParameters.setCurrentTestDescription(
				"Confirm Genres property dropdown allows to remove multi selected values");

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
		String CourseXpath = "//*[@class='filename']//*[contains(text(),'Course')]";
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		// Navigate to document library and click on a program>Program Object
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitFor(driver);
		UIHelper.click(driver, CourseXpath);
		
		UIHelper.waitFor(driver);
		collectionPg.clickOnMouseOverMenu("AutoContentObj","Edit Properties");
		UIHelper.waitFor(driver);
		UIHelper.click(driver, allProperties); 					
		UIHelper.waitFor(driver);

		WebElement select1 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_genres-entry\"]/option[8]"));
		WebElement select2 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_genres-entry\"]/option[9]"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(select1).click(select2).build().perform();				
		collectionPg.clickOnSaveBtn();
				 
		collectionPg.clickOnMoreSetting("AutoContentObj");
		UIHelper.waitFor(driver);
		collectionPg.commonMethodForClickOnMoreSettingsOption("AutoContentObj","View Details");
		UIHelper.waitFor(driver);
		collectionPg.VerifyPropertyValueINviewDetails("Genres:", "None");	
		UIHelper.waitFor(driver);
	}
	

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
