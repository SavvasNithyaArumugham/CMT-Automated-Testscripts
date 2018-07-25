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
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_4655_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_4655_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_4655_TC001 Validate Upload test thumbnail files and apply filename to autolink fields." +
	    "<br>LFDEPLOY_4655_TC002_Verification of Run autolink)"+
	    "<br>LFDEPLOY_4655_TC003_Verify Content Object references for Thumbnail and Grid Thumbnail)");

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
		
		functionalLibrary.loginAsValidUser(signOnPage);		
		//Create site
		UIHelper.waitForLong(driver);
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");				
		sitesPage.siteFinder(siteNameValue);
		
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();	
		
		
		//upload file in collectionsite
			
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		myFiles.openCreatedFolder(folderNames[2]);
		myFiles.openCreatedFolder(fileName);
		DocDetailsPg.performUnzipDocAction("DocumentsFolder");	
		
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		
		//go to course object 1		
		
		myFiles.openCreatedFolder(folderNames[3]);					
		myFiles.openCreatedFolder(folderNames[4]);
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();				
		UIHelper.waitForPageToLoad(driver);
		
		//click on edit properties
		collectionPg.clickOnMouseOverMenu(folderNames[5],"Edit Properties");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		// linking both Thumbnail and grid Thumbnail
		collectionPg.linkThumbnailandGridThumbnail("hsm18_activity@2x.png");
		UIHelper.waitFor(driver);
		
		// case 2 : Verify Auto Link Assets
		collectionPg.clickDetailsAndValue("Auto-link assets");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);		
		
		
		//Case 3 : Verify error report to check results 
		collectionPg.clickDetailsAndValue("View Error Report");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath,errorfile);		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//case 4 : Check whether the references added
		
		collectionPg.clickOnMouseOverMenu(folderNames[5],"View Details");
		collectionPg.verifyOutgoingReferenceInCollObjVieDetailsPg("hsm18_activity@2x.png");	
	
		homePageObj.openNewTab(fileDownloadPath + "\\" + errorfile);		
		if(!UIHelper.chkForThisStringinUI(driver, "Duplicate results"))
		{
			report.updateTestLog("Verify Auto Link Error Report","Duplicate results are not found in Error report", Status.PASS);			
		}else		{
			report.updateTestLog("Verify Auto Link Error Report","Duplicate results are found in Error report", Status.FAIL);
		}
		
		
			
	}	

	@Override
	public void tearDown() {

	}
}


