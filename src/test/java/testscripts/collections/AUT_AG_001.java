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

public class AUT_AG_001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_040() {
		testParameters
				.setCurrentTestDescription(
		 "TC_ALFDEPLOY-3309_To verify user able to add thumbnail if Site URL name is given in thumbnail field(SiteURL:File name)"
		+"<br>TC_ALFDEPLOY-3309_To verify user able to add thumbnail if Site URL name is given in thumbnail field for content object(SiteURL:File name)"
		+"<br>TC_ALFDEPLOY-3309_To verify user able to add thumbnail if Site URL name is given in thumbnail field for Sequence object(SiteURL:File name)"
		+"<br>TC_ALFDEPLOY-3309_To verify user able to add thumbnail if Site URL name is given in thumbnail field for Composite(SiteURL:File name)"
		+"<br>TC_ALFDEPLOY-3309_To verify user able to add thumbnail if Site URL name is given in thumbnail field for learning bundles(SiteURL:File name)"
		+"<br>TC_ALFDEPLOY-3309_To verify user able to add mentioned thumbnail if user have file with same name in different sites(SiteURL:File name)"
		+"<br> Similar testcases of Grid Thumbnail"
		+"<br> ALFDEPLOY-3981_Verify User can be able to duplicate all the objects"
		+"<br> ALFDEPLOY-3981_Verify User can be able to duplicate particular collection object."
		+"<br> ALFDEPLOY-3981_Verify grid thumbnail link remains same for Duplicated Content object."
		+"<br> ALFDEPLOY-3981_Verify grid thumbnail link remains same for Duplicated Learning Bundle."
		+"<br> ALFDEPLOY-3981_Verify grid thumbnail link remains same for Duplicated Sequence Object."
		+"<br> ALFDEPLOY-3981_Verify grid thumbnail link remains same for Duplicated composite object."
		+"<br> ALFDEPLOY-3981_Verify thumbnail link remains same for Duplicated Content object."
		+"<br> ALFDEPLOY-3981_Verify thumbnail link remains same for Duplicated Learning Bundle."
		+"<br> ALFDEPLOY-3981_Verify thumbnail link remains same for Duplicated Sequence Object."
		+"<br> ALFDEPLOY-3981_Verify thumbnail link remains same for Duplicated composite object."
		+"<br> ALFDEPLOY-3981_Verify Asset(s) to link is remains same for Duplicated Content object."
		+"<br> ALFDEPLOY-3981_Verify there should not be a thumnail link for Duplicated Container object"
		+"<br> ALFDEPLOY-3981_Verify there should not be a thumnail link for Duplicated Asset object"
		+"<br> ALFDEPLOY-3981_Verify there should not be a thumnail link for Duplicated Course object"
		+"<br> ALFDEPLOY-3981_Verify there should not be a grid thumnail link for Duplicated Container object"
		+"<br> ALFDEPLOY-3981_Verify there should not be a grid thumnail link for Duplicated Asset object"
		+"<br> ALFDEPLOY-3981_Verify there should not be a grid thumnail link for Duplicated Course object"
		+"<br> ALFDEPLOY-3981_Verify there should be asset to be linked for duplicate Composite object"
		+"<br> ALFDEPLOY-3981_Verify there should be asset to be linked for duplicate Content Object"
		+"<br> ALFDEPLOY-3981_Verify there should not be asset to be linked for duplicate learning bundle"
		+"<br> ALFDEPLOY-3981_Verify there should not be asset to be linked for duplicate COurse"
		+"<br> ALFDEPLOY-3981_Verify there should not be asset to be linked for duplicate Sequence object"
		+"<br> ALFDEPLOY-3981_Verify there should not be asset to be linked for duplicate Container object"
		+"<br> ALFDEPLOY-3981_Verify there should not be asset to be linked for duplicate asset object");
		
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

		sitesPage.createSite(siteNameValue, "Yes");

		// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
				collectionPg.duplicteProg("Duplicate object");
				
		//enter into default course object
				String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
				
				
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName,
						"Duplicate all");
				
				collectionPg.openCollectionObject(collectionObjectName);
				
		// create collections objects
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
		collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
						
		//verify  Grid Thumbnail to link:	,Thumbnail to link: for content, composite, learning bundel, sequence 
				ArrayList<String> listOfObjects = new ArrayList<String>();
				listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
System.out.println(listOfObjects);
				

				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.equalsIgnoreCase("AutoContentObj") ||listOfObjectsString.equalsIgnoreCase("AutoCompositeObj") ||
							listOfObjectsString.equalsIgnoreCase("AutoSeqObj") ||listOfObjectsString.equalsIgnoreCase("AutoLearningBundle") ){
						
						
						
						
						
	
						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
								"Edit Properties");
						
						if(listOfObjectsString.equalsIgnoreCase("AutoContentObj") 
								|| listOfObjectsString.equalsIgnoreCase("AutoCompositeObj")){
							
							collectionPg.enterCollectionObjectA2LData("Asset(s) to link:", siteName+ ":AlfGrid.jpg");
						}
						
						UIHelper.waitFor(driver);
						
						collectionPg.enterCollectionObjectA2LData("Grid Thumbnail to link:",siteName+ ":AlfGrid.jpg");
						collectionPg.enterCollectionObjectA2LData("Thumbnail to link:", siteName+":AlfTum.jpg");
						
						UIHelper.waitFor(driver);
					//	collectionPg.verifyThumbnailGridThumbnailValuesInAllPropPage(listOfObjectsString);
						collectionPg.clickOnSaveBtn();
					
					}
					
					
					collectionPg.clickOnMoreSetting(listOfObjectsString);
					collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
							"Duplicate object");
				}
				
				
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
		//Click  auto link assets on parent course				
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();	
			
			/*	
				if (collectionPg.isCreatedObjectAvailable("Programs"+"-1")) {
					report.updateTestLog("Verify the Duplicate all object created",
							"Duplicate all Object created successfully" + "<br><b> Object File Name : </b>" + "Programs"+"-1", Status.PASS);
				} else {
					report.updateTestLog("Verify the object created",
							"Object not created" + "<br><b> Object File Name : </b>" + "Programs"+"-1", Status.FAIL);
				}
				
				*/
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, moreSettingsOptionName);
				
				if (collectionPg.isCreatedObjectAvailable(collectionObjectName+"-1")) {
					report.updateTestLog("Verify the Duplicate all object created",
							"Duplicate all Object created successfully" + "<br><b> Object File Name : </b>" + collectionObjectName+"-1", Status.PASS);
				} else {
					report.updateTestLog("Verify the object created",
							"Object not created" + "<br><b> Object File Name : </b>" + collectionObjectName+"-1", Status.FAIL);
				}
				
				
				//enter into default course object
				collectionPg.openCollectionObject(collectionObjectName);
				
				listOfObjects.clear();
				System.out.println(listOfObjects);
				listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
				System.out.println(listOfObjects);
				//Verify respective  grid thumbnail, thumbnail references were added
				for (String listOfObjectsString : listOfObjects) {
					if(listOfObjectsString.equalsIgnoreCase("AutoContentObj") ||listOfObjectsString.equalsIgnoreCase("AutoCompositeObj") ||
							listOfObjectsString.equalsIgnoreCase("AutoSeqObj") ||listOfObjectsString.equalsIgnoreCase("AutoLearningBundle")){
						
						if(listOfObjectsString.equalsIgnoreCase("AutoContentObj") 
								|| listOfObjectsString.equalsIgnoreCase("AutoCompositeObj")){
							
							collectionPg.verifyListOfReferenveValue(listOfObjectsString,"outgoing","Child References","AlfGrid.jpg");
						}
						
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Grid Thumbnail","AlfGrid.jpg");
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Thumbnail","AlfTum.jpg");
						continue;
					}
					
					if(listOfObjectsString.equalsIgnoreCase("AutoContentObj-1") ||listOfObjectsString.equalsIgnoreCase("AutoCompositeObj-1") ||
							listOfObjectsString.equalsIgnoreCase("AutoSeqObj-1") ||listOfObjectsString.equalsIgnoreCase("AutoLearningBundle-1")){
						
						report.updateTestLog("Verify the Duplicate object created for " +listOfObjectsString,
								"Duplicate Object created successfully" + "<br><b> Duplicate Object File Name : </b>" + listOfObjectsString, Status.PASS);
						if(listOfObjectsString.equalsIgnoreCase("AutoContentObj-1") 
								|| listOfObjectsString.equalsIgnoreCase("AutoCompositeObj-1")){
							
							collectionPg.verifyListOfReferenveValue(listOfObjectsString,"outgoing","Child References","AlfGrid.jpg");
						}
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Grid Thumbnail","AlfGrid.jpg");
						collectionPg.verifyListOfReferenveValue(listOfObjectsString, "outgoing","Thumbnail","AlfTum.jpg");
						continue;
					}
					
					
					
					if(listOfObjectsString.equalsIgnoreCase("AutoCourse-1") ||listOfObjectsString.equalsIgnoreCase("AutoContainer-1") ||
							listOfObjectsString.equalsIgnoreCase("AutoAsset-1")){
						report.updateTestLog("Verify the Duplicate object created for " +listOfObjectsString,
								"Duplicate Object created successfully" + "<br><b> Duplicate Object File Name : </b>" + listOfObjectsString, Status.PASS);
						
						
						UIHelper.waitFor(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, collectionPg.rightFoldersListFromCollectionUiXpath);

						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString, "View Details");
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);

						
						if(UIHelper.checkForAnElementbyXpath(driver, collectionPg.paneltwisteropen)){
							
						}else{
							
							UIHelper.click(driver, collectionPg.paneltwister);
							UIHelper.waitFor(driver);
							
						}
						
						if(!UIHelper.checkForAnElementbyXpath(driver, ".//*[text()='Thumbnail']") && 
								!UIHelper.checkForAnElementbyXpath(driver, ".//*[text()='Grid Thumbnail']")){
							report.updateTestLog("Verify there should not be a thumnail link for Duplicated" +listOfObjectsString,
									"No thumnail/Grid Thumbnail link for Duplicated object" + "<br><b> Duplicate Object File Name : </b>" + listOfObjectsString, Status.PASS);
							}else{
								report.updateTestLog("Verify there should not be a thumnail link for Duplicated" +listOfObjectsString,
										"thumnail/Grid Thumbnail link for Duplicated object which is not expected" + "<br><b> Duplicate Object File Name : </b>" + listOfObjectsString, Status.FAIL);
								
							}
						
						driver.navigate().back();
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);
						UIHelper.waitForVisibilityOfEleByXpath(driver, collectionPg.rightFoldersListFromCollectionUiXpath);
						
					}
				}
				
			
			}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
