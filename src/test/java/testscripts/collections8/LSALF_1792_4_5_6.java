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

public class LSALF_1792_4_5_6 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_04_05_06() {
		testParameters.setCurrentTestDescription(
				
				"<br>Confirm \"CMT Skills Discipline\" property dropdown allows to remove and save multi select values"
				+"<br>Confirm exporting a course with \"CMT Skills Discipline\" values applied."
				+"<br>Confirm \"CMT Skills Discipline\" property value is displayed for the already existed course object.");

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
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		// Navigate to document library and click on a programs>Program Object
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitFor(driver);
		//UIHelper.click(driver, CourseXpath);
		
					UIHelper.waitFor(driver);
					collectionPg.clickOnMouseOverMenu("AutoCourse","Edit Properties");
					UIHelper.waitFor(driver);
					UIHelper.click(driver, allProperties); 					
					UIHelper.waitFor(driver);

					WebElement select1 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_cmtSkillsDiscipline-entry\"]/option[3]"));
			        WebElement select2 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_cmtSkillsDiscipline-entry\"]/option[4]"));
			        Actions action = new Actions(driver);
			        action.keyDown(Keys.CONTROL).click(select1).click(select2).build().perform();				
					collectionPg.clickOnSaveBtn();
					
					}
	

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
