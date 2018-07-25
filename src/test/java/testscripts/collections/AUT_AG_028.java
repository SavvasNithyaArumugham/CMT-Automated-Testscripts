package testscripts.collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
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

public class AUT_AG_028 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_060() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3676_Verify the CSV export encoding was changed from UTF-8 to UTF-8 with BOM.");
		
		
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
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);	
		AlfrescoSearchPage alfrescoSearchPage = new AlfrescoSearchPage(scriptHelper);
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		
		String collectionsobj = dataTable.getData("MyFiles", "CreateFileDetails");
		
		
		       // Log in Pearson Schools project
		      functionalLibrary.loginAsValidUser(signOnPage);
		
	        	//Create site
				homePageObj.navigateToSitesTab();
				String siteNameValue =  dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "Yes");
				homePageObj.navigateToSitesTab();
				String siteName = sitesPage.getCreatedSiteName();				
				sitesPage.openSiteFromRecentSites(siteName);
				siteName=siteName.toLowerCase();
				
				
		
		        //Site finder
               // String siteassertValue = dataTable.getData("Sites", "SiteName");
               // sitesPage.siteFinder(siteassertValue);
        
		        
          
                // Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				
	        	/*go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

				// upload course plan
				String filePath = dataTable.getData("MyFiles", "FilePath");
				String fileName = dataTable.getData("MyFiles", "FileName");
				collectionPg.uploadFileInCollectionSite(filePath, fileName);

				   //verify import process progress
				   collectionPg.verifyImportProcessProgress(fileName);
					
					UIHelper.waitForPageToLoad(driver);	
				    UIHelper.waitForPageToLoad(driver);
				*/  
				   // Navigate to Program    
				    sitesPage.enterIntoDocumentLibrary();
				    myFiles.openCreatedFolder(folderNames[0]);
					myFiles.openCreatedFolder(folderNames[1]);
					collectionPg.clickOnEditCollectionButton();
					
				  // Navigate to created Course Obj
					WebElement e1= driver.findElement(By.xpath("//*[@class='filename']/span[contains(text(),'Course')]"));
				  UIHelper.mouseOveranElement(driver,e1);
				  WebElement e2=driver.findElement(By.xpath("//*[@class='show-more']/span[contains(text(),'More...')]"));
				  UIHelper.highlightElement(driver,e2);
				  collectionPg.clickOnMoreSetting("Course");
				  collectionPg.commonMethodForClickOnMoreSettingsOption("Course","Generate Realize CSVs"); 
				  report.updateTestLog("Mousehover on 'Course' object"+"click on' More'option", "User clicked the 'More' option", Status.DONE);
					
				     //click on Realize Course CSV filtering
				      collectionPg.clickonrealizebox();
				      
				      //Data Export
				      sitesPage.enterIntoDocumentLibrary();
				      myFiles.openCreatedFolder("Data Exports");
				      UIHelper.waitForPageToLoad(driver);	
					  UIHelper.waitForPageToLoad(driver);
					  
					  //Realize Export
					  myFiles.openCreatedFolder("Realize Export");
					  UIHelper.waitForPageToLoad(driver);	
					  myFiles.openCreatedFolder("Course Object 1");
					  UIHelper.waitForPageToLoad(driver);
					
					
					/*  collectionPg.clickOnBrowseActionsInCollectionUI(objectName, browseActionName);
						collectionPg.clickonrealizebox();
						// Navigate to Document library
						sitesPage.enterIntoDocumentLibrary();

						for (String path : filePath) {
							sitesPage.documentdetails(path);
						}
						
						// Navigate to generated csv file path and check whether

						Date date = new Date();
						String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
						sitesPage.documentdetails(currentDate);

						
						
						// CSVfile presence and filename
						String fileName1 = objectName + "-" + currentDate;	
						String filenameCSV= mediaTransPage.RetreiveFilename(fileName1);
						System.out.println(filenameCSV);
					   String filename2= collectionPg.CSVFilename(fileName1);
						System.out.println(filename2);
						collectionPg.clickOnMoreSetting(filename2);
						collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"Download");
					  
					*/
				
	                }
						    
		  
				
			

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	}

