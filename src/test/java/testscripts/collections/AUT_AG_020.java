package testscripts.collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.util.ArrayList;
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

public class AUT_AG_020 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_059() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4050_Verify processing indicator is displayed when user modifies the criteria of the applied filter"
						+ "ALFDEPLOY-4050_Verify user is able to remove an applied filter on resetting the Collections sequence tree");
		
		
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
				
			
		
		       // Site finder
		       // String siteassertValue = dataTable.getData("Sites", "SiteName");
		       // sitesPage.siteFinder(siteassertValue);
		        
		        
          
                // Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				
	        	//go to Course plan
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
			  
				   // Navigate to Program    
				    sitesPage.enterIntoDocumentLibrary();
				    myFiles.openCreatedFolder(folderNames[0]);
					myFiles.openCreatedFolder(folderNames[1]);
					collectionPg.clickOnEditCollectionButton();
					
				  //Navigate to created Course Obj
				    collectionPg.openCollectionObject("Course Object 1");
					
			   // Object creation for Asset, Container , Composite obj
					String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
					collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
								
				    	UIHelper.waitForPageToLoad(driver);	
					    UIHelper.waitForPageToLoad(driver);
					   
					  //Check for Filter  
					    String Filtermsg = "//button[contains(@id,'filter-results-button')]";
						UIHelper.waitForVisibilityOfEleByXpath(driver, Filtermsg);
								
						WebElement w=driver.findElement(By.xpath(Filtermsg));
						UIHelper.highlightElement(driver,w);
						Boolean flag=	UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, Filtermsg));
							if (flag){
								report.updateTestLog(" Check weather Filter is appearing in "+"Course", "Filter is appearing in "+"Course",Status.PASS);
							}else{
								report.updateTestLog("Check weather Filter is appearing in "+"Course", "Filter is not appeared in "+"Course",Status.FAIL);
							}
							UIHelper.waitForPageToLoad(driver);	
						    UIHelper.waitForPageToLoad(driver);
						    
					        //choosing option 
							driver.findElement(By.xpath(Filtermsg)).click();
							collectionPg.SelectionOfFilter("productType","FilterCollection_ProductType");
							collectionPg.SelectionOfFilter("versionCountry","FilterCollection_VersionCountry ");
							collectionPg.SelectionOfFilter("versionState","FilterCollection_VersionState");
							driver.findElement(By.xpath("//button[contains(@id,'filterResults-ok-button')]")).click();
							String Filter1 = "//button[contains(@id,'filterResults-ok-button')]";
							
							report.updateTestLog("Check whether filter is applied" , "Status: "  +"Filter is applied", Status.PASS);
							
							UIHelper.waitForPageToLoad(driver);	
						    UIHelper.waitForPageToLoad(driver);
		
						   //modifying filter
							driver.findElement(By.xpath(Filtermsg)).click();
							collectionPg.ModifyFilter("productType","FilterCollection_ProductType_update");
							collectionPg.ModifyFilter("versionCountry","FilterCollection_VersionCountry _update");
							collectionPg.ModifyFilter("versionState","FilterCollection_VersionState_update");
							driver.findElement(By.xpath("//button[contains(@id,'filterResults-ok-button')]")).click();
							UIHelper.waitForLong(driver);
							
                           /* driver.findElement(By.xpath("//input[contains(@id,'prop_cpnals_productType-checkbox')]')]")).click();
					
							driver.findElement(By.xpath("//input[contains(@id,'prop_cpnals_versionCountry-checkbox')]")).click();
							
							driver.findElement(By.xpath("//input[contains(@id,'prop_cpnals_versionState-checkbox')]")).click();
							
							*/
							
							report.updateTestLog("Check whether filter is updated" , "Status: "  +"Filter is updated", Status.PASS);
							
							//Remove Filter
							driver.findElement(By.xpath("//button[contains(@id,'remove-filter-button-button')]")).click();
							report.updateTestLog("Check whether Filter is removed" , "Status: "  +"Filter is removed", Status.PASS);
	                }
						    
		  
				
			

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	}

