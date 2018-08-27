package testscripts.sanity.uswip;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3816_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_3816_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_3816_TC001 Execute Duplicate Object and confirm skill alignment is retained in duplicate." +
	    "<br>ALFDEPLOY_3816_TC002_Execute Duplicate All and confirm skill alignment is retained in duplicate)" +
	    "<br>ALFDEPLOY_3816_TC003_Execute Duplicate All To... and confirm skill alignment is retained in duplicate)");
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
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");		
		String processingindicatorxpath = "//*[@title='Processing import spreadsheet']";
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		functionalLibrary.loginAsValidUser(signOnPage);
		
		//Create site	
		UIHelper.waitFor(driver);
		homePageObj.navigateToSitesTab();				
		//sitesPage.siteFinder("alignsite02");
		sitesPage.createSite(sourceSiteName, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		
		UIHelper.waitFor(driver);
		homePageObj.navigateToSitesTab();					
		//sitesPage.siteFinder("alignsite01");
		//sitesPage.createSite(sourceSiteName, "Yes");
		//String siteName1 = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName+"1");
		
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		
		//delete existing
		myFiles.openCreatedFolder("Courses");	
		myFiles.deleteCreatedFolder("CCP Test Course 6 - ALFDEPLOY-4554");
		UIHelper.waitFor(driver);	
		sitesPage.enterIntoDocumentLibrary();
		
		//upload file in collectionsite	
				
		myFiles.openCreatedFolder("Data Imports");
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Course Plan");
		UIHelper.waitFor(driver);	
				
		// upload course plan
		collectionPg.uploadFileInCollectionSite(filePath, fileName);				
		UIHelper.waitForLong(driver);						
		UIHelper.pageRefresh(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);	
	
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);	
		//go to course object 1		
		
		myFiles.openCreatedFolder("Courses");	
		UIHelper.waitFor(driver);	
		myFiles.openCreatedFolder("CCP Test Course 6 - ALFDEPLOY-4554");
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();		
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
	
		//click on Align Skills to add skills
		
		collectionPg.clickOnMoreSetting("Blank State Unit 1");	
		collectionPg.commonMethodForClickOnMoreSettingsOption("Blank State Unit 1", "Align Skills");
		UIHelper.waitFor(driver);					
		collectionPg.addAlignmentinObjects();
			
		// case 1 & 2: open duplicate object and verify the list under Incoming  / Has Alignment
		driver.navigate().back();
		collectionPg.clickOnEditCollectionButton();		
		UIHelper.waitFor(driver);
		
		collectionPg.clickOnMoreSetting("Blank State Unit 1");	
		UIHelper.waitFor(driver);	
		collectionPg.commonMethodForClickOnMoreSettingsOption("Blank State Unit 1", "Duplicate object");
		UIHelper.waitFor(driver);	
		
		driver.navigate().back();
		collectionPg.clickOnEditCollectionButton();		
		UIHelper.waitFor(driver);
		
		collectionPg.clickOnMoreSetting("Blank State Unit 1");
		UIHelper.waitFor(driver);	
		collectionPg.commonMethodForClickOnMoreSettingsOption("Blank State Unit 1", "Duplicate all");
		UIHelper.waitFor(driver);
		
		driver.navigate().back();
		collectionPg.clickOnEditCollectionButton();		
		UIHelper.waitFor(driver);
		
		collectionPg.clickOnMoreSetting("Blank State Unit 1");
		UIHelper.waitFor(driver);	
		collectionPg.commonMethodForClickOnMoreSettingsOption("Blank State Unit 1", "Duplicate all to...");
		UIHelper.waitFor(driver);	
		collectionPg.mapSiteForDuplicateAllTo(siteName);
		UIHelper.waitFor(driver);
		
		collectionPg.clickOnMouseOverMenu("Blank State Unit 1-1","View Details");
		UIHelper.waitFor(driver);
		collectionPg.verifyIncomingHasAlignmentViewDetailsPg("GMLP.49G0101");
		UIHelper.waitFor(driver);	
		collectionPg.verifyIncomingHasAlignmentViewDetailsPg("GMLP.49M0101");			
			
		//Create site
				
				homePageObj.navigateToSitesTab();					
				sitesPage.siteFinder(siteName);
				
				// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				
				//go to course object 1					
				
				myFiles.openCreatedFolder("Sequence Objects");
				myFiles.openCreatedFolder("b");					
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting("Blank State Unit 1");
				String viewDetailXpath= "//div[@id='onActionFolderViewDetails']";
				UIHelper.click(driver, viewDetailXpath);
				UIHelper.waitFor(driver);
				
				//	//case 2: verify skillcode in targeted site & verify the list under Incoming  / Has Alignment	
				collectionPg.verifyIncomingHasAlignmentViewDetailsPg("GMLP.49G0101");
				UIHelper.waitFor(driver);	
				collectionPg.verifyIncomingHasAlignmentViewDetailsPg("GMLP.49M0101");
				UIHelper.waitFor(driver);
			
	}
		

	@Override
	public void tearDown() {

	}
}


