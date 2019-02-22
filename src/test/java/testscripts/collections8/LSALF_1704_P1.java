package testscripts.collections8;

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

public class LSALF_1704_P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_01_02() {
		testParameters
				.setCurrentTestDescription("Bulk Skills Alignment via CSV Import: "
						+ "Confirm skills are aligned on importing a new course without error via Data import with valid skills populated in course CSV that exists in Skills and Standard site.");
		
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
		

		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
						
		String filepathfile = dataTable.getData("MyFiles", "uploadpath");
		String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectName = dataTable.getData("MyFiles", "FileName");
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		Date date = new Date();
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
	
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");					
		sitesPage.createSite(siteNameValue, "No");		
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
				
				//Select the CMT Skills
				UIHelper.waitFor(driver);
				collectionPg.clickOnMouseOverMenu("LSALF-1704_PRSNSOW8-1_Test Course","Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties); 					
				UIHelper.waitFor(driver);
				UIHelper.sendKeysToAnElementByXpath(driver, "//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_skillsPath\"]","ECE");
				WebElement select1 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_cmtSkillsDiscipline-entry\"]/option[3]"));
		        WebElement select2 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_cmtSkillsDiscipline-entry\"]/option[4]"));
		        WebElement select3 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_cmtSkillsDiscipline-entry\"]/option[1]"));
		        WebElement select4 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_cmtSkillsDiscipline-entry\"]/option[2]"));
		        Actions action = new Actions(driver);
		        action.keyDown(Keys.CONTROL).click(select1).click(select2).click(select3).click(select4).build().perform();				
				collectionPg.clickOnSaveBtn();
				UIHelper.waitFor(driver);
				// Click on Generate Realize Csv for course object
				
				collectionPg.clickOnBrowseActionsInCollectionUI(objectName, browseActionName);
				UIHelper.waitFor(driver);
				collectionPg.clickonrealizebox();
				UIHelper.waitFor(driver);
				
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
