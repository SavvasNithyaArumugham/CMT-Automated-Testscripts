package testscripts.collections3;

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
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3203_TC004 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_038() {
		testParameters
				.setCurrentTestDescription("1) can see that newly referenced assets are ordered above any previously existing child references. no other ordering is implied"
		+"can update the value of the 'Thumbnail to link' and 'Grid Thumbnail to link' properties and re-run the 'associated content' action to see the thumbnail association is updated to point to a new match, where there is a match");
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
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");



		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
				homePageObj.navigateToSitesTab();
				String siteNameValue = dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "Yes");
				String siteName = sitesPage.getCreatedSiteName();
				siteName=siteName.toLowerCase();
				sitesPage.openSiteFromRecentSites(siteName);			
				sitesPage.enterIntoDocumentLibrary();
				
				
		// upload  files 
				myFiles.openCreatedFolder("Assets");
				myFiles.openCreatedFolder("Thumbnails");
				String filePath = dataTable.getData("MyFiles", "FilePath");
				String fileName = dataTable.getData("MyFiles", "FileName");
				collectionPg.uploadFileInCollectionSite(filePath, fileName);

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
				
				ArrayList<String> listOfObjects = new ArrayList<String>();
				listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
				
				
		//Enter values in Asset(s) to link,Grid Thumbnail to link:	,Thumbnail to link: for content and composite for first file
				for (String listOfObjectsString : listOfObjects) {
				if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ){
					UIHelper.waitFor(driver);
					collectionPg.clickOnMoreSetting(listOfObjectsString);
					collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
							"Edit Properties");
					UIHelper.waitFor(driver);
					collectionPg.enterCollectionObjectA2LData("Asset(s) to link:", siteName+":1.jpg");
					collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":1.jpg");
					collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":1.jpg");
					collectionPg.clickOnSaveBtn();
				}	
			}
				
		//Click  auto link assets on parent course				
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();				
				String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionName);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				
		//enter the course again
				collectionPg.openCollectionObject(collectionObjectName);
				
		//Verify respective child, grid thumbnail, thumbnail references were added
				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.contains("AutoContentObj") || listOfObjectsString.contains("AutoCompositeObj") ){
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Child References","1.jpg");
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Thumbnail","1.jpg");
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Grid Thumbnail","1.jpg");

					}
				}

	
			
		//Enter values in Asset(s) to link,Grid Thumbnail to link:	,Thumbnail to link: for content and composite for second file
				for (String listOfObjectsString : listOfObjects) {
				if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ){
					UIHelper.waitFor(driver);
					collectionPg.clickOnMoreSetting(listOfObjectsString);
					collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
							"Edit Properties");
					UIHelper.waitFor(driver);
					collectionPg.enterCollectionObjectA2LData("Asset(s) to link:", siteName+":2.jpg");
					collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":2.jpg");
					collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":2.jpg");
					collectionPg.clickOnSaveBtn();
				}	
			}
				
		//Click  auto link assets on parent course	
				UIHelper.waitFor(driver);
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();				
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionName);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);


		//enter the course again
				collectionPg.openCollectionObject(collectionObjectName);
				
		//Verify respective child, grid thumbnail, thumbnail references were added
				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.contains("AutoContentObj") || listOfObjectsString.contains("AutoCompositeObj") ){
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Child References","2.jpg");
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Thumbnail","2.jpg");
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Grid Thumbnail","2.jpg");

					}
				}


	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
