package testscripts.collections2;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

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

public class ALFDEPLOY_2297_TC003 extends TestCase {

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void COLLECTIONS_021() {
		testParameters
				.setCurrentTestDescription(" Do not see the action to 'Generate Realize CSVs' in the right pane of the Collection UI as a hover over on other content types (only Courses). ");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		
		// Login into Application
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		
		//Navigate to site tab 
		homePageObj.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		homePageObj.navigateToSitesTab();
		//Create new collection site from Create site menu
		sitesPage.createSite(sourceSiteName, "Yes");
			
		//Enter into Document library
		sitesPage.enterIntoDocumentLibrary();
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		String collectionObjectData = dataTable.getData("MyFiles", "CollectionObjectAdditionalData");
		String objectFileName = dataTable.getData("MyFiles", "FileName");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String[] objectFileNames = objectFileName.split(",");
		System.out.println(objectFileNames[1]);
		String[] objectTypes = collectionObjectName.split(",");
		String[] collectionObjectDataArray = collectionObjectData.split(","); 
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		
		for (String objName : objectFileNames) {
			if(collectionPg.isCreatedObjectAvailable(objName)){
				collectionPg.clickOnBrowseActionsInCollectionUI(objName, "View Details");
				collectionPg.deleteCollectionObject(objName);
				sitesPage.enterIntoDocumentLibrary();
				
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				
				collectionPgTest.verifyEditCollectionOption();
				collectionPg.clickOnEditCollectionButton();
			}
			UIHelper.waitFor(driver);
		}
		
		collectionPg.clickOnCreateButton();
		collectionPg.clickOnCreateMenuItem(objectTypes[0]);
		UIHelper.waitFor(driver);
		
		collectionPg.enterBasicDataForCompositeObject(objectFileNames[0], 
				collectionObjectDataArray[1], collectionObjectDataArray[2], collectionObjectDataArray[3], 
				collectionObjectDataArray[4], collectionObjectDataArray[5], collectionObjectDataArray[6]
						,collectionObjectDataArray[7]);
		collectionPg.clickOnSaveBtnForSubmitCreateObjectData();
		
		UIHelper.waitFor(driver);
		collectionPg.moveToFirstInCollectionPage();
		
		collectionPg.clickOnCreateButton();
		collectionPg.clickOnCreateMenuItem(objectTypes[1]);
		UIHelper.waitFor(driver);
		
		collectionPg.enterBasicDataForContentObject(objectFileNames[1], 
				collectionObjectDataArray[1], collectionObjectDataArray[2], collectionObjectDataArray[3], 
				collectionObjectDataArray[4], collectionObjectDataArray[5], collectionObjectDataArray[6]
						,collectionObjectDataArray[7],"","","","","","");
		collectionPg.clickOnSaveBtnForSubmitCreateObjectData();
		
		UIHelper.waitFor(driver);
		collectionPg.moveToFirstInCollectionPage();
		
		collectionPg.clickOnMoreSetting(objectFileNames[0]);
		if(collectionPg.checkMoreSettingsOption(objectFileNames[0], moreSettingsOptionName)){
			report.updateTestLog(
					"Verify More Settings Option for "+ objectTypes[0] + " in Collection UI",
					"Option available successfully"
					+"<br><b> Verified Option : </b>" + moreSettingsOptionName, Status.FAIL);
		}else{
			report.updateTestLog(
					"Verify More Settings Option for "+ objectTypes[0] + " in Collection UI",
					"Option not available"
					+"<br><b> Verified Option : </b>" + moreSettingsOptionName, Status.PASS);
		}
		
		UIHelper.waitFor(driver);
		
		collectionPg.clickOnMoreSetting(objectFileNames[1]);
		if(collectionPg.checkMoreSettingsOption(objectFileNames[1], moreSettingsOptionName)){
			report.updateTestLog(
					"Verify More Settings Option for "+ objectTypes[1] + " in Collection UI",
					"Option available successfully"
					+"<br><b> Verified Option : </b>" + moreSettingsOptionName, Status.FAIL);
		}else{
			report.updateTestLog(
					"Verify More Settings Option for "+ objectTypes[1] + " in Collection UI",
					"Option not available"
					+"<br><b> Verified Option : </b>" + moreSettingsOptionName, Status.PASS);
		}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
