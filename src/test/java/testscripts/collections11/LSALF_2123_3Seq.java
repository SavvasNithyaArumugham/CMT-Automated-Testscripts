package testscripts.collections11;


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
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_2123_3Seq extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_03() {
		testParameters.setCurrentTestDescription(
				"Confirm Library property for Sequence Object dropdown allows to add multi select values");
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

		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		// From the site Type dropdown select 'Collection Site'.
		sitesPage.createSite(siteNameValue, "No");		

		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
		String CourseXpath = "//*[@class='filename']//*[contains(text(),'Course')]";
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		File downloadedFile = null;		
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
		String objectName = dataTable.getData("MyFiles", "FileName");
		// Navigate to document library and click on a program>Program Object
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitFor(driver);
		UIHelper.click(driver, CourseXpath);
		// Verify custom icons by creating new collections objects

		collectionPg.createBasicCollectionObjectFromCreateMenuforcsv(createObjectData);

		String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);
		

		ArrayList<String> listOfObjects = new ArrayList<String>();
		listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
		for (String listOfObjectsString : listOfObjects) {
			
			if(listOfObjectsString.contains("AutoSeqObj")){
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
						"Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties);
				UIHelper.waitFor(driver);
				WebElement select1 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_library-entry\"]/option[2]"));
		        WebElement select2 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_library-entry\"]/option[3]"));
		        Actions action = new Actions(driver);
		        action.keyDown(Keys.CONTROL).click(select1).click(select2).build().perform();				
				collectionPg.clickOnSaveBtn();
				UIHelper.waitFor(driver);
			}			
	}
}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
