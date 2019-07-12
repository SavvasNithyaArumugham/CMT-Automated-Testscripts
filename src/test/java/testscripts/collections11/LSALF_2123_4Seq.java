package testscripts.collections11;

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

public class LSALF_2123_4Seq extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_04() {
		testParameters.setCurrentTestDescription(
				"Confirm Library property for Sequence Object dropdown allows to remove multi selected values");

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
	
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);

		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String CourseXpath = "//*[@class='filename']//*[contains(text(),'Course')]";
		String allProperties = ".//a[contains(text(),'All Properties...')]";
	
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
		collectionPg.clickOnMouseOverMenu("AutoSeqObj","Edit Properties");
		UIHelper.waitFor(driver);
		UIHelper.click(driver, allProperties); 					
		UIHelper.waitFor(driver);
		
		Select libraryValues = new Select(driver.findElement(By.id("template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_library-entry")));
		libraryValues.deselectAll();
		collectionPg.clickOnSaveBtn();
				 
		collectionPg.clickOnMoreSetting("AutoSeqObj");
		UIHelper.waitFor(driver);
		collectionPg.commonMethodForClickOnMoreSettingsOption("AutoSeqObj","View Details");
		UIHelper.waitFor(driver);
		collectionPg.VerifyPropertyValueINviewDetails("Library:", "(None)");	
		UIHelper.waitFor(driver);
	}
	

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
