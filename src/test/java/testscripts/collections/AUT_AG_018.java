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

public class AUT_AG_018 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_057() {
		testParameters
				.setCurrentTestDescription( "ALFDEPLOY-4050_06_Verify Filter command is displayed above the collection tree for Container object type on editing collection."
				);
		
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
				
		/*Create site
				String siteassertValue = dataTable.getData("Sites", "SiteName");
				sitesPage.siteFinder(siteassertValue);
				*/
			
				//Create site
				homePageObj.navigateToSitesTab();
				String siteNameValue =  dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "Yes");
				//sitesPage.siteFinder(siteNameValue);
		
				
		// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
	     // enter into default course object
				//   String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
				//   collectionPg.openCollectionObject(collectionObjectName);
				   
					
	     // create collections objects
	 			String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
				collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, "//*[@class='filename']//*[contains(text(),'AutoContainer')]"));
							
		 // Object creation for Container
				ArrayList<String> listOfObjects = new ArrayList<String>();
				listOfObjects=myFiles.getFileNames(collectionsobj);
				listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();		
				 
				System.out.println(listOfObjects);

				for (String listOfObjectsString : listOfObjects) {
						
				if(listOfObjectsString.contains("AutoContainer"))  
								
				  {
				    UIHelper.waitForPageToLoad(driver);	
					UIHelper.waitForPageToLoad(driver);
						
					collectionPg.openCollectionObject("AutoContainer");
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForPageToLoad(driver);	
								
								
					String Filtermsg = "//button[contains(@id,'filter-results-button')]";
					UIHelper.waitForVisibilityOfEleByXpath(driver, Filtermsg);
							
					WebElement w=	driver.findElement(By.xpath(Filtermsg));
					UIHelper.highlightElement(driver,w);
					Boolean flag=	UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, Filtermsg));
					if (flag){
					report.updateTestLog(" Check weather Filter is appearing in "+listOfObjectsString +"object content", "Filter is appearing in "+listOfObjectsString +"object content",Status.PASS);
					}else{
					report.updateTestLog("Check weather Filter is appearing in "+listOfObjectsString +"object content", "Filter is not appeared in "+listOfObjectsString +"object content",Status.FAIL);
					}
									
					String programs = "//*[contains(@id,'assembly-view-node-workspace')]//a[contains(text(),'Program')]";					
					UIHelper.click(driver, programs);
					driver.findElement(By.xpath("//span[contains(@class,'dynatree-expander')]")).click();
								
					UIHelper.waitForPageToLoad(driver);	
				    UIHelper.waitForPageToLoad(driver);	
				  }
				}
			}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
