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

public class ALFDEPLOY_3468_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_3468_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_3468_TC001 Re-import any UAT Test Course in a new Collections Site." +
	    "<br>ALFDEPLOY_3468_TC002_Delete new Course, confirm all children also deleted)"	    	);		
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
		String subfolders1 = dataTable.getData("MyFiles", "Subfolders1");
		String subfolders2 = dataTable.getData("MyFiles", "Subfolders2");
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		String processingindicatorxpath = "//*[@title='Processing import spreadsheet']";	
		functionalLibrary.loginAsValidUser(signOnPage);		
		//UIHelper.waitForLong(driver);
		homePageObj.navigateToSitesTab();
		//Modified as part of NALS
		//sitesPage.siteFinder("qa3468");	
		String siteNameValue =  dataTable.getData("Sites", "SiteName");	
		sitesPage.createSite(siteNameValue, "Yes");
		UIHelper.waitFor(driver);
	   

	  //go to Course plan
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Data Imports");
		UIHelper.waitFor(driver);	
		myFiles.openCreatedFolder("Course Plan");
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);	
		
     // upload course plan		
		
		collectionPg.uploadFileInCollectionSite(filePath,fileName);
		UIHelper.waitForLong(driver);						
		UIHelper.pageRefresh(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);	
		
	
		// delete course 
		
		// Go to collection UI
	    sitesPage.enterIntoDocumentLibrary();	
	    myFiles.openCreatedFolder("Courses");	
		myFiles.deleteCreatedFolder("CCP Test Course 6 - ALFDEPLOY-4554");
						
		// check whether the related content objects are deleted.
		 sitesPage.enterIntoDocumentLibrary();
		 collectionPg.verifyEmptyFolder("CCP Test Course 6 - ALFDEPLOY-4554");
		
		
		 
				
				
	}	

	@Override
	public void tearDown() {

	}
}


