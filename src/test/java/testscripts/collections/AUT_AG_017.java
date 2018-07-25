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

public class AUT_AG_017 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_056() {
		testParameters
		.setCurrentTestDescription("ALFDEPLOY-4050_Verify Filter command is displayed above the collection tree for Content object type on editing collection."
				+ "ALFDEPLOY-4050_Verify Filter command is displayed above the collection tree for Learning Bundle object type on editing collection. "
				+ "ALFDEPLOY-4050_Verify Filter command is displayed above the collection tree for Course object type on editing collection."
				+"ALFDEPLOY-4050_Verify Filter command is displayed above the collection tree for Sequence object type on editing collection.");
		
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
				
				
		
		        /* Site finder
		        String siteassertValue = dataTable.getData("Sites", "SiteName");
		        sitesPage.siteFinder(siteassertValue);
		        */
          
				 // Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				
	        	//go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

				// upload course plan
				String filePath = dataTable.getData("MyFiles", "FilePath");
				String fileName = dataTable.getData("MyFiles", "FileName");
				collectionPg.uploadFileInCollectionSite(filePath, fileName);

				// verify import process progress
				collectionPg.verifyImportProcessProgress(fileName);
				
							
					UIHelper.waitForPageToLoad(driver);	
				    UIHelper.waitForPageToLoad(driver);	
				    sitesPage.enterIntoDocumentLibrary();
				   // myFiles.openCreatedFolder(folderNames[0]);
				   //myFiles.openCreatedFolder(folderNames[1]);
				   //collectionPg.clickOnEditCollectionButton();
			
				  

				// verify Content object in the collection view
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder("Content Objects");
				myFiles.openCreatedFolder("c");
				myFiles.openCreatedFolder("Content Object 1");
	
		        collectionPg.clickOnEditCollectionButton();
				
				
				// verify Filter
				String Filtermsg1 = "//button[contains(@id,'filter-results-button')]";
				UIHelper.waitForVisibilityOfEleByXpath(driver, Filtermsg1);
			
				WebElement w1=	driver.findElement(By.xpath(Filtermsg1));
		     	UIHelper.highlightElement(driver,w1);
				Boolean flag1=	UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, Filtermsg1));
				if (flag1){
					report.updateTestLog(" Check weather Filter is appearing in "+"Content Objects" +"object content", "Filter is appearing in "+"Content Objects" +"object content",Status.PASS);
				} else{
					report.updateTestLog("Check weather Filter is appearing in "+"Content Objects" +"object content", "Filter is not appeared in "+"Content Objects" +"object content",Status.FAIL);
				}
				
				 // verify Learning Bundles object in the collection view
			     sitesPage.enterIntoDocumentLibrary();
				 myFiles.openCreatedFolder("Learning Bundles");
				 myFiles.openCreatedFolder("l");
				 myFiles.openCreatedFolder("Learning Bundle 1");
				 collectionPg.clickOnEditCollectionButton();

				 // verify Filter
				 String Filtermsg2 = "//button[contains(@id,'filter-results-button')]";
				 UIHelper.waitForVisibilityOfEleByXpath(driver, Filtermsg2);
				
				 WebElement w2 =	driver.findElement(By.xpath(Filtermsg2));
			     UIHelper.highlightElement(driver,w2);
				 Boolean flag2=	UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, Filtermsg2));
				 if (flag2){
					report.updateTestLog(" Check weather Filter is appearing in "+"Learning Bundles" +"object content", "Filter is appearing in "+"Learning Bundles" +"object content",Status.PASS);
				 }else{
					report.updateTestLog("Check weather Filter is appearing in "+"Learning Bundles"+"object content", "Filter is not appeared in "+"Learning Bundles" +"object content",Status.FAIL);
				 }
	
				  // verify Sequence Objects object in the collection view
				  sitesPage.enterIntoDocumentLibrary();
						myFiles.openCreatedFolder("Sequence Objects");
						myFiles.openCreatedFolder("s");
						myFiles.openCreatedFolder("Sequence Object 1");
						collectionPg.clickOnEditCollectionButton();

						// verify Filter
						String Filtermsg3 = "//button[contains(@id,'filter-results-button')]";
						UIHelper.waitForVisibilityOfEleByXpath(driver, Filtermsg3);
					
						WebElement w3 =	driver.findElement(By.xpath(Filtermsg3));
				     	UIHelper.highlightElement(driver,w3);
						Boolean flag3=	UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, Filtermsg3));
							if (flag3){
								report.updateTestLog(" Check weather Filter is appearing in "+"Sequence Objects" +"object content", "Filter is appearing in "+"Sequence Objects" +"object content",Status.PASS);
							}else{
								report.updateTestLog("Check weather Filter is appearing in "+"Sequence Objects"+"object content", "Filter is not appeared in "+"Sequence Objects" +"object content",Status.FAIL);
							}
						
						// verify Courses object in the collection view
						sitesPage.enterIntoDocumentLibrary();
						myFiles.openCreatedFolder("Courses");
						myFiles.openCreatedFolder("Course Object 1");
						collectionPg.clickOnEditCollectionButton();
						
						// verify Filter
						String Filtermsg4 = "//button[contains(@id,'filter-results-button')]";
						UIHelper.waitForVisibilityOfEleByXpath(driver, Filtermsg4);
						
						WebElement w4 =	driver.findElement(By.xpath(Filtermsg4));
					    UIHelper.highlightElement(driver,w4);
						Boolean flag4=	UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, Filtermsg4));
								if (flag4){
									report.updateTestLog(" Check weather Filter is appearing in "+"Courses" +"object content", "Filter is appearing in "+"Courses" +"object content",Status.PASS);
								}else{
									report.updateTestLog("Check weather Filter is appearing in "+"Courses"+"object content", "Filter is not appeared in "+"Courses" +"object content",Status.FAIL);
								}
}

				@Override
				public void tearDown() {
				}
}
				
					
				
			

