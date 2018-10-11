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

public class ALFDEPLOY_3206_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_039() {
		testParameters
				.setCurrentTestDescription(
		 "1).when viewing a sequence object in a collection can click on the 'auto-link assets' action and see a notification that the content linking has started"+
				"2). when viewing a container in a collection can click on the 'auto-link assets' action and see a notification that the content linking has started"
				 +"3).when viewing a learning bundle in a collection can click on the 'auto-link assets' action and see a notification that the content linking has started");
		
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
		String AssetToLink = dataTable.getData("MyFiles", "MoreSettingsOption");
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
				homePageObj.navigateToSitesTab();
				String siteNameValue = dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "Yes");
				String siteName = sitesPage.getCreatedSiteName();				
				sitesPage.openSiteFromRecentSites(siteName);
				siteName=siteName.toLowerCase();
				sitesPage.enterIntoDocumentLibrary();
				
				
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
				

		// create collections objects
				String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
				collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
				
		// create collections objects inside seq, container,learning bundel and enter asset to link, thumbnail to link and grid thumbnail to link values
				ArrayList<String> listOfObjectsInParentCourse = new ArrayList<String>();
				listOfObjectsInParentCourse=collectionPg.getFoldersFromRightPanInCollectionUi();
				for (String listOfObjectsString : listOfObjectsInParentCourse) {
					
					if(listOfObjectsString.contains("AutoSeqObj") || listOfObjectsString.contains("AutoContainer") || listOfObjectsString.contains("AutoLearningBundle")){
						collectionPg.openCollectionObject(listOfObjectsString);
						// create collections objects in side sequencve, container, learning bundel
						collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
						ArrayList<String> listOfObjectsInSequenceObj = new ArrayList<String>();
						listOfObjectsInSequenceObj=collectionPg.getFoldersFromRightPanInCollectionUi();
						for (String listOfObjectsInSequenceObjstr : listOfObjectsInSequenceObj) {
							
							//Enter values in Asset(s) to link,Grid Thumbnail to link:	,Thumbnail to link: for content and composite in side sequencve, container, learning bundel
										if(listOfObjectsInSequenceObjstr.contains("AutoContentObj") ||listOfObjectsInSequenceObjstr.contains("AutoCompositeObj") ){
											UIHelper.waitFor(driver);
											collectionPg.clickOnMoreSetting(listOfObjectsInSequenceObjstr);
											collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsInSequenceObjstr,
													"Edit Properties");
											UIHelper.waitFor(driver);
											collectionPg.enterCollectionObjectA2LData("Asset(s) to link:", siteName+":AlfrescoXMLFile.xml, "+siteName+":AlfrescoJSONFile.json");
											collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":AlfGrid.jpg");
											collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":AlfTum.jpg");
											collectionPg.clickOnSaveBtn();
										}	
										
							//Enter values in Grid Thumbnail to link:	,Thumbnail to link: for Sequence and learning bundel in side sequencve, container, learning bundel
										if(listOfObjectsInSequenceObjstr.contains("AutoSeqObj") ||listOfObjectsInSequenceObjstr.contains("AutoLearningBundle") ){
											UIHelper.waitFor(driver);
											collectionPg.clickOnMoreSetting(listOfObjectsInSequenceObjstr);
											collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsInSequenceObjstr,
													"Edit Properties");
											UIHelper.waitFor(driver);
											collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":AlfGrid.jpg");
											collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":AlfTum.jpg");
											collectionPg.clickOnSaveBtn();
										}	
									}
						
						
						driver.navigate().back();
						UIHelper.waitForPageToLoad(driver);
						collectionPg.clickOnEditCollectionButton();	
						collectionPg.openCollectionObject(collectionObjectName);
					}
				
				}
				
				UIHelper.waitFor(driver);
				for (String listOfObjectsString : listOfObjectsInParentCourse) {
					if(listOfObjectsString.contains("AutoSeqObj") || listOfObjectsString.contains("AutoContainer") || listOfObjectsString.contains("AutoLearningBundle")){
						
						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString, AssetToLink);
					}
					
				}
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
				for (String listOfObjectsString : listOfObjectsInParentCourse) {
					if(listOfObjectsString.contains("AutoSeqObj") || listOfObjectsString.contains("AutoContainer") || listOfObjectsString.contains("AutoLearningBundle")){
						collectionPg.openCollectionObject(listOfObjectsString);
						ArrayList<String> listOfObjectsInSequenceObj = new ArrayList<String>();
						listOfObjectsInSequenceObj=collectionPg.getFoldersFromRightPanInCollectionUi();
						for (String listOfObjectsInSequenceObjstr : listOfObjectsInSequenceObj) {
							if(listOfObjectsInSequenceObjstr.contains("AutoContentObj") || listOfObjectsInSequenceObjstr.contains("AutoCompositeObj") ){
								collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
										listOfObjectsInSequenceObjstr, "outgoing", "Child References");
								collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
										listOfObjectsInSequenceObjstr, "outgoing", "Thumbnail");
								collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
										listOfObjectsInSequenceObjstr, "outgoing", "Grid Thumbnail");
							}
							
							if(listOfObjectsInSequenceObjstr.contains("AutoSeqObj") || listOfObjectsInSequenceObjstr.contains("AutoLearningBundle") ){
							
								collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
										listOfObjectsInSequenceObjstr, "outgoing", "Thumbnail");
								collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
										listOfObjectsInSequenceObjstr, "outgoing", "Grid Thumbnail");
							}
							
						}
						
						driver.navigate().back();
						UIHelper.waitForPageToLoad(driver);
						collectionPg.clickOnEditCollectionButton();	
						collectionPg.openCollectionObject(collectionObjectName);
						
					}
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
//				listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
//				for (String listOfObjectsString : listOfObjects) {
//					
//		//Enter values in Asset(s) to link,Grid Thumbnail to link:	,Thumbnail to link: for content and composite 
//					if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ){
//						UIHelper.waitFor(driver);
//						collectionPg.clickOnMoreSetting(listOfObjectsString);
//						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
//								"Edit Properties");
//						UIHelper.waitFor(driver);
//						collectionPg.enterCollectionObjectA2LData("Asset(s) to link:", siteName+":AlfrescoXMLFile.xml, "+siteName+":AlfrescoJSONFile.json");
//						collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":AlfGrid.jpg");
//						collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":AlfTum.jpg");
//						collectionPg.clickOnSaveBtn();
//					}	
//					
//		//Enter values in Grid Thumbnail to link:	,Thumbnail to link: for Sequence and learning bundel 
//					if(listOfObjectsString.contains("AutoSeqObj") ||listOfObjectsString.contains("AutoLearningBundle") ){
//						UIHelper.waitFor(driver);
//						collectionPg.clickOnMoreSetting(listOfObjectsString);
//						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
//								"Edit Properties");
//						UIHelper.waitFor(driver);
//						collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":AlfGrid.jpg");
//						collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":AlfTum.jpg");
//						collectionPg.clickOnSaveBtn();
//					}	
//				}
//				
//		//Click  auto link assets on parent course				
//				driver.navigate().back();
//				UIHelper.waitForPageToLoad(driver);
//				collectionPg.clickOnEditCollectionButton();				
//				String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
//				collectionPg.clickOnMoreSetting(collectionObjectName);
//				collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionName);
//				
//		//Verify respective child, grid thumbnail, thumbnail references were added
//				for (String listOfObjectsString : listOfObjects) {
//					if(listOfObjectsString.contains("AutoContentObj") || listOfObjectsString.contains("AutoCompositeObj") ){
//						collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
//								listOfObjectsString, "outgoing", "Child References");
//						collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
//								listOfObjectsString, "outgoing", "Thumbnail");
//						collectionPg.verifyRelationshipViewerOnObjectsInCollectionUi(
//								listOfObjectsString, "outgoing", "Grid Thumbnail");
//					}
//				}
//				
//		/*Enter invalid/wrong/mismatch values in Asset(s) to link,Grid Thumbnail to link:	,Thumbnail to link: for content and composite 
//		and verify edit property fields holds the miss match values still */				
//				for (String listOfObjectsString : listOfObjects) {
//					if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ){
//						UIHelper.waitFor(driver);
//						collectionPg.clickOnMoreSetting(listOfObjectsString);
//						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
//								"Edit Properties");
//						UIHelper.waitFor(driver);
//						collectionPg.enterCollectionObjectA2LData("Asset(s) to link:", siteName+":MissMatchFile.xml");
//						collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":MissMatchFile.jpg");
//						collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":MissMatchFile.jpg");
//						collectionPg.clickOnSaveBtn();
//					}	
//				}
//				UIHelper.waitFor(driver);
//				UIHelper.waitFor(driver);
//				
//		//Click  auto link assets on parent course				
//				driver.navigate().back();
//				UIHelper.waitForPageToLoad(driver);
//				collectionPg.clickOnEditCollectionButton();				
//				collectionPg.clickOnMoreSetting(collectionObjectName);
//				collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionName);
//		
//		//enter into default course object
//				collectionPg.openCollectionObject(collectionObjectName);
//				
//		//Verify edit properties field values for asset,thumbnail,grid thumbnail to link on content and composite object
//				String collectionObjectData = dataTable.getData("MyFiles", "FieldDataForQuickEdit");
//				for (String listOfObjectsString : listOfObjects) {
//					if(listOfObjectsString.contains("AutoContentObj") ||listOfObjectsString.contains("AutoCompositeObj") ){
//						UIHelper.waitFor(driver);
//						collectionPg.clickOnMoreSetting(listOfObjectsString);
//						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
//								"Edit Properties");
//						UIHelper.waitFor(driver);
//						collectionPgTest.verifyFieldValuesInEditPropPage(collectionObjectData);
//						collectionPg.clickOnCancelBtnInEditPropPage();
//
//					}
//				}
		

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
