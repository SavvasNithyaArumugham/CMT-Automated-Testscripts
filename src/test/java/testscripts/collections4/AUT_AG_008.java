package testscripts.collections4;

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

public class AUT_AG_008 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_047() {
		testParameters
				.setCurrentTestDescription("TC_ALFDEPLOY-3309_To verify user able to add thumbnail if Asset name"
						+ " with special character is given in thumbnail field(SiteURL:File name)"
		+"<br> Similar testcases of Grid Thumbnail");
		
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
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String[] fileNames = dataTable.getData("MyFiles", "FileName").split(",");
		
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
				String siteassertValue = dataTable.getData("Sites", "SiteName");
				sitesPage.siteFinder(siteassertValue);
				String siteName=siteassertValue.toLowerCase();
				
		// upload Thumbnails  files 
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder("Assets");
		myFiles.openCreatedFolder("Thumbnails");	
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		docLibPg.deleteAllFilesAndFolders();
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		
	
		//Create site
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");
		//sitesPage.siteFinder(siteNameValue);
				//
		sitesPage.createSite(siteNameValue, "Yes");
		/*homePageObj.navigateToSitesTab();
		String siteName = sitesPage.getCreatedSiteName();				
		sitesPage.openSiteFromRecentSites(siteName);
		siteName=siteName.toLowerCase();*/
				
		// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
		//enter into default course object
				String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
				collectionPg.openCollectionObject(collectionObjectName);
				
		// create collections objects
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
		collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
						
		//verify  Grid Thumbnail to link:	,Thumbnail to link: for content, composite, learning bundel, sequence 
				ArrayList<String> listOfObjects = new ArrayList<String>();
				listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
//				String collectionObjectData = dataTable.getData("MyFiles", "FieldDataForAllProperties");
				

				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ||
							listOfObjectsString.contains("AutoSeqObj") ||listOfObjectsString.contains("AutoLearningBundle")){
						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
								"Edit Properties");
						UIHelper.waitFor(driver);
					//	collectionPg.enterCollectionObjectA2LData("Asset(s) to link:", siteName+":AlfrescoXMLFile.xml, "+siteName+":AlfrescoJSONFile.json");
						collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+":"+fileNames[0]);
						collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":"+fileNames[1]);
						
						UIHelper.waitFor(driver);
					//	collectionPg.verifyThumbnailGridThumbnailValuesInAllPropPage(listOfObjectsString);
						collectionPg.clickOnSaveBtn();
					}	
				}
				
				
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
		//Click  auto link assets on parent course				
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();				
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionName);
				
				//enter into default course object
				collectionPg.openCollectionObject(collectionObjectName);
				
				//Verify respective  grid thumbnail, thumbnail references were added
				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ||
							listOfObjectsString.contains("AutoSeqObj") ||listOfObjectsString.contains("AutoLearningBundle")){
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Grid Thumbnail",fileNames[0]);
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Thumbnail",fileNames[1]);

					}
				}
			}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}