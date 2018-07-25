package testscripts.collections;

import org.openqa.selenium.By;
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

public class AUT_AG_015 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_045() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4050_Verify on clicking Filter command in Edit Collection"
						+ " user is able to select any combination of values across all three properties Product "
						+ "Type, Version Country and Version State<br>"
						
						+ "ALFDEPLOY-4050_Verify on applying the filter in edit collection, user is able to view"
						+ " the processing indicator when the filtering is in progress.<br>"
						
						+ "ALFDEPLOY-4050_Verify on applying the filter in edit collection, user is able to view "
						+ "summary of the applied criteria displayed above the Collections tree<br>"
						 
					    + "ALFDEPLOY-4050_Verify on applying the filter in edit collection, user is able to view "
					    + "only objects whose Product Type values Version Country and or Version State values match"
					    + " those specified in the Filter criteria<br>"
					    
						+ "ALFDEPLOY-4050_Verify filtering remains applied when expand, collapse, and drag-and-drop "
						+ "re-sequence is done in the filtered tree view <br>"
						
						+ "ALFDEPLOY-4050_Verify filtering remains applied when edit the properties of Collections Objects <br>"
						+ "ALFDEPLOY-4050_Verify filtering remains applied when create or add Collections Objects "
						+ "to the Collections sequence <br>" 
						
						+ "ALFDEPLOY-4050_Verify filtering remains applied when Collections Objects are removed"
						+ " from the Collections sequence <br>"
					
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
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
		
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");				
		//sitesPage.siteFinder("AutoReferCollectionSite02160318160634");
		sitesPage.createSite(siteNameValue, "Yes");
		
		// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				
				// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

				// upload course plan
				collectionPg.uploadFileInCollectionSite(filePath, fileName);

				// verify import process progress
				collectionPg.verifyImportProcessProgress(fileName);				
				sitesPage.enterIntoDocumentLibrary();
				
				//go to course object 1
				
				myFiles.openCreatedFolder("Courses");
				myFiles.openCreatedFolder("Course Object 1");
				collectionPg.clickOnEditCollectionButton();
				
			// validation of filter and cancel and filter collection summary reports
				
				collectionPg.ClickonFilter("filter");
				collectionPg.ClickonFilter("cancel");
				collectionPg.ClickonFilter("filter");
				collectionPg.disableoffiltervalue("productType");
				collectionPg.SelectionOfFilter("productType","FilterCollection_ProductType");
				collectionPg.disableoffiltervalue("versionCountry");
				collectionPg.SelectionOfFilter("versionCountry","FilterCollection_VersionCountry");
				collectionPg.disableoffiltervalue("versionState");
				collectionPg.SelectionOfFilter("versionState","FilterCollection_VersionState");
				collectionPg.ClickonFilter("filterbutton");
				collectionPg.SummaryreportsofFilter();
				collectionPg.ClickonFilter("clickhere");
				UIHelper.waitForPageToLoad(driver);
				String filterselection = dataTable.getData("Sites", "CustomizeSiteMenuToNavigate");
				collectionPg.Summaryfiltervalidation(filterselection);
				collectionPg.ClickonFilter("OKbutton");	
				
			// validation of expand and collapse and drag and drop
				
				UIHelper.waitForPageToLoad(driver);
				collectionPg.expandAndCollapse();
				UIHelper.waitForPageToLoad(driver);				
				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);
				collectionPg.filtervalidation();				
				UIHelper.waitForPageToLoad(driver);
				collectionPg.draganddrop();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);				
				collectionPg.filtervalidation();
				UIHelper.waitForPageToLoad(driver);
				
				// create collections objects
				
				String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
				collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
				
				UIHelper.waitForPageToLoad(driver);	
				collectionPg.filtervalidation();			
				UIHelper.waitForPageToLoad(driver);
				
				// validation of filter is working even we do edit properties
				
				collectionPg.Editpropertiesfolder();
				collectionPg.filtervalidation();
				
				// validation of filter is working even we do delete the content object
				
				collectionPg.deletethecreatedfolder();
				UIHelper.waitForPageToLoad(driver);	
				collectionPg.ClickonFilter("clickhere");
				UIHelper.waitForPageToLoad(driver);
				collectionPg.Summaryfiltervalidation(filterselection);
				collectionPg.ClickonFilter("OKbutton");				
				
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
