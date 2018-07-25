package testscripts.collections;
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

public class ALFDEPLOY_4645_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_4645_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_4645_TC001 Validate Thumbnails directory - Upload All files." +
	    "<br>LFDEPLOY_4645_TC002_Verification of uploaded file in picker window)");
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
		AlfrescoDocumentDetailsPage DocDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage DocLibPg = new AlfrescoDocumentLibPage(scriptHelper);	
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		functionalLibrary.loginAsValidUser(signOnPage);
		
		//Create site
		UIHelper.waitForLong(driver);
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");				
		sitesPage.siteFinder(siteNameValue);
		
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		//upload file in collectionsite
		//DocLibPg.deleteAllFilesAndFolders();	
		collectionPg.uploadFileInCollectionSite(filePath, fileName);		
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		myFiles.openCreatedFolder(folderNames[2]);
		myFiles.openCreatedFolder(fileName);
		DocDetailsPg.performUnzipDocAction("DocumentsFolder");			
				
		//Thumbnail file validation			
					
		//homePageObj.navigateToSitesTab();		
		//sitesPage.siteFinder("QA_CollectionSite");
		
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		
		//go to course object 1		
		
		myFiles.openCreatedFolder(folderNames[3]);					
		myFiles.openCreatedFolder(folderNames[4]);
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();		
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		
		//click on edit properties	and All properties			
		
		collectionPg.clickOnMouseOverMenu(folderNames[5],"Edit Properties");
		UIHelper.waitFor(driver);
		UIHelper.click(driver, allProperties);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);		
		collectionPg.VerifyThumbnailListinAllproperties(100);
		UIHelper.waitFor(driver);
	}
		

	@Override
	public void tearDown() {

	}
}


