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

public class ALFDEPLOY_4556_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_4556_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_4556_TC001 Verify - Lock the Thumbnail File." +
	    "<br>LFDEPLOY_4556_TC002_Verify - Thumbnail to link field with the locked file)"+
	    "<br>LFDEPLOY_4556_TC003_Verify - Auto Link for locked file)" + "<br>LFDEPLOY_4556_TC004_Verify - view error report for Locked Node Error)");

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
		String errorfile = dataTable.getData("MyFiles", "Subfolders1");
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,errorfile);			
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		//Create site
		UIHelper.waitForLong(driver);
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");				
		sitesPage.siteFinder(siteNameValue);
		
		//upload lockednode file
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Assets");					
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Thumbnails");
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("CCP QA Thumbnail Test set - ALFDEPLOY-4655");
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("CCP QA Thumbnail Test set - ALFDEPLOY-4655");
	//	collectionPg.uploadFileInCollectionSite(filePath, fileName);				
		
		// Go to collection UI
		//sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		//Case 1 - Lock a file
		//myFiles.openCreatedFolder("Assets");					
		//UIHelper.waitFor(driver);
		//myFiles.openCreatedFolder("Thumbnails");
		//UIHelper.waitFor(driver);
		
		collectionPg.clickOnMoreSetting("lockednode.png");
		collectionPg.commonMethodForClickOnMoreSettingsOption("lockednode.png", "Edit Offline");
		
		//validate the offline editing message
		
		collectionPg.lockFileValidation();
		UIHelper.waitFor(driver);
		
		//Case 2 - Link thumbnail file 
		
		//go to course object 1		
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Courses");	
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("CCP Test Course 2 - ALFDEPLOY-3191");
		UIHelper.waitFor(driver);
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();				
		UIHelper.waitForPageToLoad(driver);
		
		//click on edit properties
		UIHelper.waitFor(driver);
		collectionPg.clickOnMouseOverMenu("Leveled Reader Test - Guided Reading","Edit Properties");
		UIHelper.waitFor(driver);
		// linking both Thumbnail and grid Thumbnail
		collectionPg.linkThumbnailandGridThumbnail("lockednode.png");
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitFor(driver);
		
		// case 2 : Verify Auto Link Assets
				collectionPg.clickDetailsAndValue("Auto-link assets");
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.pageRefresh(driver);			
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);				
				
				
				//Case 3 : Verify error report to check results 
				UIHelper.pageRefresh(driver);
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
			
				homePageObj.openNewTab(fileDownloadPath + "\\" + errorfile);	
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				if(UIHelper.chkForThisStringinUI(driver, "A locked node were found for lockednode.png. Link was NOT created."))
				{
					report.updateTestLog("Verify Auto Link Error Report","Auto-link error report contains the specified locked node error", Status.PASS);			
				}else		{
					report.updateTestLog("Verify Auto Link Error Report","Auto-link error report is not contain the specified locked node error", Status.FAIL);
				}
						
				
	}
		

	@Override
	public void tearDown() {

	}
}


