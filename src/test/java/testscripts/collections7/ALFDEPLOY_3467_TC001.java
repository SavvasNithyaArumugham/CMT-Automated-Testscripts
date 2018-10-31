package testscripts.collections7;
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
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3467_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_3467_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_3467_TC001 upload thumbnail file." +
	    "<br>ALFDEPLOY_3467_TC002_Populate Thumbnail To Link property)"+
	    "<br>ALFDEPLOY_3467_TC003_Populate Grid Thumbnail To Link property)" +
	    "<br>ALFDEPLOY_3467_TC004_Execute Auto Linking for Thumbnails)"+
	    "<br>ALFDEPLOY_3467_TC005_Confirm Auto Linking for Thumbnails)");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application","Invoke the application under test @ "+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage DocDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);			
		String errorfile = dataTable.getData("MyFiles", "Subfolders1");
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,errorfile);		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String processingindicatorxpath = "//*[@title='Processing import spreadsheet']";	
		functionalLibrary.loginAsValidUser(signOnPage);		
		UIHelper.waitForLong(driver);
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");				
		//sitesPage.siteFinder("qaphase1");	
		sitesPage.createSite(siteNameValue, "Yes");
		UIHelper.waitFor(driver);
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();	
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Project Files");	
		UIHelper.waitFor(driver);
		myFiles.deleteCreatedFolder(fileName);			
		UIHelper.waitFor(driver);
		sitesPage.enterIntoDocumentLibrary();	
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Courses");	
		myFiles.deleteCreatedFolder("ALFDEPLOY-4557 - UAT Test Course");
		
		//upload file in collectionsite
		sitesPage.enterIntoDocumentLibrary();	
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Project Files");	
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		UIHelper.waitFor(driver);			
		
		// go to Course plan
		sitesPage.enterIntoDocumentLibrary();	
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Data Imports");
		UIHelper.waitForPageToLoad(driver);
		myFiles.openCreatedFolder("Course Plan");
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
	
		
		// upload course plan
		collectionPg.uploadFileInCollectionSite(filePath, "ALFDEPLOY-4557 - UAT Test Course - ALFDEPLOY-4557 - UAT Test Cours.csv");				
		UIHelper.waitForLong(driver);						
		UIHelper.pageRefresh(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);		
		
		
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		
		//go to course object 1		
		
		myFiles.openCreatedFolder("Courses");		
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("ALFDEPLOY-4557 - UAT Test Course");
		UIHelper.waitFor(driver);
		collectionPgTest.verifyEditCollectionOption();
		UIHelper.waitFor(driver);
		collectionPg.clickOnEditCollectionButton();				
		UIHelper.waitForPageToLoad(driver);
		
		//click on edit properties
		collectionPg.clickOnMouseOverMenu("Test to blank values 01","Edit Properties");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		// linking both Thumbnail and grid Thumbnail
		collectionPg.linkThumbnailandGridThumbnail(fileName);
		UIHelper.waitFor(driver);
		
		// case 2 : Verify Auto Link Assets
		UIHelper.waitFor(driver);
		collectionPg.clickDetailsAndValue("Auto-link assets");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);			
		
		
		//case 3 : Check whether the references added
		
		collectionPg.clickOnMouseOverMenu("Test to blank values 01","View Details");
		collectionPg.verifyOutgoingReferenceInCollObjVieDetailsPg(fileName);
		
		
	}	

	@Override
	public void tearDown() {

	}
}


