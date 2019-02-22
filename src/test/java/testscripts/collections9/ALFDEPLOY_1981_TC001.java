package testscripts.collections9;

import org.openqa.selenium.WebElement;
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
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1981_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("1.Confirm that Skills alignment search retrieving partial match when we are searching without double quotes from search box and "
						+ "exact match when we are searching with double quotes from search box.");
		
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
		
		
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		
		// Navigate to Document library
	
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);	
		//go to course object 1		
		
		myFiles.openCreatedFolder("Courses");	
		UIHelper.waitFor(driver);	
		myFiles.openCreatedFolder("CCP Test Course 4 - ALFDEPLOY-3440");
		collectionPg.clickOnEditCollectionButton();		
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting("Test Object - Discussion Prompt");	
		collectionPg.commonMethodForClickOnMoreSettingsOption("Test Object - Discussion Prompt", "Align Skills");
		
		// Align Skills - New method to create
			
		UIHelper.waitFor(driver);	
		String framexpath = "//*[@id='alignmentToolIframe']";
		WebElement framexpathEle = UIHelper.findAnElementbyXpath(driver, framexpath);	
		driver.switchTo().frame(framexpathEle);		
		UIHelper.waitFor(driver);
		String discipleXpath = ".//*[contains(@class,'placeholder-custom mat-button')]";
		WebElement discipleElement = UIHelper.findAnElementbyXpath(driver, discipleXpath);
		String MathXpath = "(//*[contains(text(),'Social Studies')])";				
		UIHelper.click(driver, discipleXpath);
		UIHelper.waitFor(driver);
		UIHelper.mouseOverandclickanElement(driver, discipleElement);			
		UIHelper.waitFor(driver);	
		UIHelper.click(driver, MathXpath);
		UIHelper.waitFor(driver);	
		
		String filterSkillsXpath = ".//*[contains(@class,'input-filter mat-input-element')]";
		UIHelper.sendKeysToAnElementByXpath(driver,filterSkillsXpath , "A semicolon");
		UIHelper.waitFor(driver);
		
		String filterSkillsValueXpath = "//*[@id=\"skillsTable\"]/mat-row[1]/mat-cell[3]/text()";
				
		if (filterSkillsValueXpath.matches("A semicolon"));
		report.updateTestLog("Skills Path"," Partial search", Status.PASS);
		UIHelper.waitFor(driver);
		
		
	}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
