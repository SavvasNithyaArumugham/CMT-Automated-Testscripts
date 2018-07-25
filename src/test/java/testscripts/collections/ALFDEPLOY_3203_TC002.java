package testscripts.collections;

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

public class ALFDEPLOY_3203_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_034() {
		testParameters
				.setCurrentTestDescription(
		 "1).when the automated linking is complete (email notifications are handled in a separate story) can navigate through the course plan and see child references from content object/composite objects to assets or files"
		+"2).where there is no match can go to the edit properties page of the content or composite object and see that all unmatched filenames remain in the 'Asset(s) to link' property"
		+"3).when in the edit properties page of a content object/composite object/learning bundle/sequence object can specify the site location of a thumbnail in the format: [site name:filename]"
		+"4).when in the edit properties page of a content or composite object can enter the 'Asset(s) to link' property in the format: [site name 1:filename, site name 2:filename, filename] to specify the site location of the asset");
		
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
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
				homePageObj.navigateToSitesTab();
				String siteName = sitesPage.getCreatedSiteName();				
				sitesPage.openSiteFromRecentSites(siteName);
				siteName=siteName.toLowerCase();
				
				
		// upload Thumbnails  files 
				sitesPage.enterIntoDocumentLibrary();
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
				
		
				ArrayList<String> listOfObjects = new ArrayList<String>();
				listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
				for (String listOfObjectsString : listOfObjects) {
					
		//Enter values in Asset(s) to link,Grid Thumbnail to link:	,Thumbnail to link: for content and composite 
					if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ){
						UIHelper.waitFor(driver);
						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
								"Edit Properties");
						UIHelper.waitFor(driver);
						collectionPg.enterCollectionObjectA2LData("Asset(s) to link:", siteName+":AlfrescoXMLFile.xml, "+siteName+":AlfrescoJSONFile.json");
						collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":AlfGrid.jpg");
						collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":AlfTum.jpg");
						collectionPg.clickOnSaveBtn();
					}	
					
		//Enter values in Grid Thumbnail to link:	,Thumbnail to link: for Sequence and learning bundel 
					if(listOfObjectsString.contains("AutoSeqObj") ||listOfObjectsString.contains("AutoLearningBundle") ){
						UIHelper.waitFor(driver);
						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
								"Edit Properties");
						UIHelper.waitFor(driver);
						collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":AlfGrid.jpg");
						collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":AlfTum.jpg");
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
				
//		enter into default course object
				collectionPg.openCollectionObject(collectionObjectName);

		//Verify respective child, grid thumbnail, thumbnail references were added
				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.contains("AutoContentObj") || listOfObjectsString.contains("AutoCompositeObj") ){
						collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
								listOfObjectsString, "outgoing", "Child References");
						collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
								listOfObjectsString, "outgoing", "Thumbnail");
						collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
								listOfObjectsString, "outgoing", "Grid Thumbnail");
					}
				}
				
		/*Enter invalid/wrong/mismatch values in Asset(s) to link,Grid Thumbnail to link:	,Thumbnail to link: for content and composite 
		and verify edit property fields holds the miss match values still */				
				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ){
						UIHelper.waitFor(driver);
						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
								"Edit Properties");
						UIHelper.waitFor(driver);
						collectionPg.enterCollectionObjectA2LData("Asset(s) to link:", siteName+":MissMatchFile.xml");
						collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":MissMatchFile.jpg");
						collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":MissMatchFile.jpg");
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
				
		//Verify edit properties field values for asset,thumbnail,grid thumbnail to link on content and composite object
				String collectionObjectData = dataTable.getData("MyFiles", "FieldDataForQuickEdit");
				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ){
						UIHelper.waitFor(driver);
						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
								"Edit Properties");
						UIHelper.waitFor(driver);
						collectionPgTest.verifyFieldValuesInEditPropPage(collectionObjectData);
						collectionPg.clickOnCancelBtnInEditPropPage();

					}
				}
		

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
