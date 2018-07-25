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

public class ALFDEPLOY_3818_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_3818_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_3818_TC001 Create test directory and asset upload file." +
	    "<br>ALFDEPLOY_3818_TC002_Create test directory and upload thumbnail file)"+
	    "<br>ALFDEPLOY_3818_TC003_Create test directory and upload Grid Thumbnail file)" +
	    "<br>ALFDEPLOY_3818_TC004_Populate Asset(s) To Link property)"+
	    "<br>ALFDEPLOY_3818_TC005_Populate Thumbnail To Link property)" +
		 "<br>ALFDEPLOY_3818_TC006_Populate Grid Thumbnail To Link property)"+
		    "<br>ALFDEPLOY_3818_TC007_Execute Asset Linking)" +
		    "<br>ALFDEPLOY_3818_TC008_Confirm asset linking)"+
		    "<br>ALFDEPLOY_3818_TC009_Confirm Thumbnail linking)" +
		    "<br>ALFDEPLOY_3818_TC0010_Confirm Grid Thumbnail linking)"+
		    "<br>ALFDEPLOY_3818_TC0011_Populate cross-site auto-link values )"+
		    "<br>ALFDEPLOY_3818_TC0012_Confirm cross-site linking)"	);
		
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
		sitesPage.siteFinder("qa3818");	
		UIHelper.waitFor(driver);	
		
		// Go to collection UI
	    sitesPage.enterIntoDocumentLibrary();	
	    myFiles.openCreatedFolder("Courses");	
		myFiles.deleteCreatedFolder("ALFDEPLOY-4557 - UAT Test Course");
		 sitesPage.enterIntoDocumentLibrary();
	    myFiles.deleteCreatedFolder("Project Files");	   
	    myFiles.commonMethodForCreateFolder("Project Files", "Project Files", "Project Files");
		//UIHelper.waitForPageToLoad(driver);	
	    UIHelper.waitForPageToLoad(driver);
	 

	  //go to Course plan
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Data Imports");
		UIHelper.waitFor(driver);	
		myFiles.openCreatedFolder("Course Plan");
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);	
		
     // upload course plan		
		
		collectionPg.uploadFileInCollectionSite(filePath,"ALFDEPLOY-4557 - UAT Test Course - ALFDEPLOY-4557 - UAT Test Cours.csv");
		UIHelper.waitForLong(driver);						
		UIHelper.pageRefresh(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);	
		
		
		// case 3818:01,2,3 : create folders and upload files 
				// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();	
				UIHelper.waitFor(driver);
				
				
				myFiles.openCreatedFolder("Project Files");	
				UIHelper.waitFor(driver);
				myFiles.commonMethodForCreateFolder("Digital PDFs", "Title of PDF", "PDF Description");
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder("Digital PDFs");
				myFiles.uploadFile(filePath, fileName);		
				
				sitesPage.enterIntoDocumentLibrary();	
				UIHelper.waitFor(driver);			
				myFiles.openCreatedFolder("Project Files");	
				UIHelper.waitFor(driver);
				myFiles.commonMethodForCreateFolder("Thumbs", "Title of Thumbs", "Thumbs Description");
				myFiles.openCreatedFolder("Thumbs");
				myFiles.uploadFile(filePath, subfolders1);	
				
				
				sitesPage.enterIntoDocumentLibrary();	
				UIHelper.waitFor(driver);			
				myFiles.openCreatedFolder("Project Files");	
				UIHelper.waitFor(driver);
				myFiles.commonMethodForCreateFolder("Grid Thumbs", "Title of Grid Thumbs", "Grid Thumbs Description");
				myFiles.openCreatedFolder("Grid Thumbs");
				myFiles.uploadFile(filePath, subfolders2);	
				
				//3818: 4,5,6
		
				sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder("Courses");
				UIHelper.waitFor(driver);				
				myFiles.openCreatedFolder("ALFDEPLOY-4557 - UAT Test Course");
				UIHelper.waitFor(driver);
				collectionPg.clickOnEditCollectionButton();	
				UIHelper.waitFor(driver);
				
				// Edit the content object properties
				collectionPg.clickOnMouseOverMenu("Test to blank values 01","Edit Properties");
				UIHelper.click(driver, allProperties); 	
				UIHelper.waitFor(driver);
				
				//enter the values 
				collectionPg.VerifyPropertyValueValidData("Asset(s) to link:", fileName);
				// Edit the content object properties
				collectionPg.clickOnMouseOverMenu("Test to blank values 01","Edit Properties");
				UIHelper.click(driver, allProperties); 	
				UIHelper.waitFor(driver);
				collectionPg.VerifyPropertyValueValidData("Thumbnail to link:", subfolders1);
				// Edit the content object properties
				collectionPg.clickOnMouseOverMenu("Test to blank values 01","Edit Properties");
				UIHelper.click(driver, allProperties); 	
				UIHelper.waitFor(driver);
				collectionPg.VerifyPropertyValueValidData("Grid Thumbnail to link:", subfolders2);
				
				//Case: 7 : Verify Auto-Link Assets
				
				collectionPg.clickDetailsAndValue("Auto-link assets");
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);	
		
				// case 8,9,10  Check whether the references added
				
				collectionPg.clickOnMouseOverMenu("Test to blank values 01","View Details");
				UIHelper.waitFor(driver);
				collectionPg.verifyOutgoingReferenceInCollObjVieDetailsPg(fileName);
				collectionPg.verifyOutgoingReferenceInCollObjTitle("/Project Files/Digital PDFs/RG16_Test_TG.pdf");
				UIHelper.waitFor(driver);
				collectionPg.verifyOutgoingReferenceInCollObjVieDetailsPg(subfolders1);
				collectionPg.verifyOutgoingReferenceInCollObjTitle("/Project Files/Grid Thumbs/rg16_icon_se_gk@2x.jpg");
				UIHelper.waitFor(driver);
				collectionPg.verifyOutgoingReferenceInCollObjVieDetailsPg(subfolders2);
				collectionPg.verifyOutgoingReferenceInCollObjTitle("/Project Files/Grid Thumbs/rg16_icon_se_gk_grid@2x.jpg");
				UIHelper.waitFor(driver);
				
				//case 11 and 12
				//Create site
				homePageObj.navigateToSitesTab();
				UIHelper.waitFor(driver);
				String siteNameValue =  dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "Yes");
				UIHelper.waitFor(driver);
				
	         // Go to collection UI
			    sitesPage.enterIntoDocumentLibrary();
				//UIHelper.waitForPageToLoad(driver);	
			    UIHelper.waitForPageToLoad(driver);
			    myFiles.openCreatedFolder("Courses");	
				myFiles.deleteCreatedFolder("CCP Test Course 6 - ALFDEPLOY-4554");
				UIHelper.waitFor(driver);	   
		
	
			  //go to Course plan
			    sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder("Data Imports");
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder("Course Plan");
	
		     // upload course plan				
				collectionPg.uploadFileInCollectionSite(filePath, "CCP Test Course 6 - ALFDEPLOY-4554 - CCP Test Course 6 - ALFDEPLOY-4554.csv");
				UIHelper.waitForLong(driver);						
				UIHelper.pageRefresh(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);	
			
			   // Populate cross-site auto-link values and confirm the cross site linking
			    	 
			    
			    sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder("Courses");
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder("CCP Test Course 6 - ALFDEPLOY-4554");
				UIHelper.waitFor(driver);
				collectionPg.clickOnEditCollectionButton();	
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
				// Edit the content object properties
				String floridaxpath ="//h3[@class='filename']//span[contains(text(),'Florida State Unit 2')]"; 	
				WebElement floridaWebElement = UIHelper.findAnElementbyXpath(driver, floridaxpath);						
				UIHelper.mouseOverandclickanElement(driver, floridaWebElement);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				collectionPg.clickOnMouseOverMenu("Florida State Content 1","Edit Properties");
				UIHelper.click(driver, allProperties); 	
				UIHelper.waitFor(driver);
				
				//enter the values 
				collectionPg.VerifyPropertyValueValidData("Asset(s) to link:", "qaphasenew:Project Files/Digital PDFs>RG16_Test_TG.pdf");
				UIHelper.waitFor(driver);
				// Edit the content object properties
				collectionPg.clickOnMouseOverMenu("Florida State Content 1","Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties); 	
				UIHelper.waitFor(driver);
				collectionPg.VerifyPropertyValueValidData("Thumbnail to link:", "qaphasenew:Project Files/Digital PDFs>rg16_icon_se_gk@2x.jpg");
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				// Edit the content object properties
				collectionPg.clickOnMouseOverMenu("Florida State Content 1","Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties); 	
				UIHelper.waitFor(driver);
				collectionPg.VerifyPropertyValueValidData("Grid Thumbnail to link:","qaphasenew:Project Files/Digital PDFs>rg16_icon_se_gk_grid@2x.jpg");
				UIHelper.waitFor(driver);
				//Case: 7 : Verify Auto-Link Assets
				
				collectionPg.clickDetailsAndValue("Auto-link assets");
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);	
				
				// case 8,9,10  Check whether the references added
				
				collectionPg.clickOnMouseOverMenu("Florida State Content 1","View Details");
				UIHelper.waitFor(driver);
				collectionPg.verifyOutgoingReferenceInCollObjVieDetailsPg(fileName);
				UIHelper.waitFor(driver);
				collectionPg.verifyOutgoingReferenceInCollObjTitle("/Project Files/Digital PDFs/RG16_Test_TG.pdf");
				UIHelper.waitFor(driver);
				collectionPg.verifyOutgoingReferenceInCollObjVieDetailsPg(subfolders1);
				collectionPg.verifyOutgoingReferenceInCollObjTitle("/Project Files/Grid Thumbs/rg16_icon_se_gk@2x.jpg");
				UIHelper.waitFor(driver);
				collectionPg.verifyOutgoingReferenceInCollObjVieDetailsPg(subfolders2);
				collectionPg.verifyOutgoingReferenceInCollObjTitle("/Project Files/Grid Thumbs/rg16_icon_se_gk_grid@2x.jpg");
				UIHelper.waitFor(driver);
				
				
				 	
				
				
	}	

	@Override
	public void tearDown() {

	}
}


