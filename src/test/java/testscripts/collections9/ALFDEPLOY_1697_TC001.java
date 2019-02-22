package testscripts.collections9;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1697_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_036() {
		testParameters
				.setCurrentTestDescription("1) Confirm \"Duplicate All\" and \"Duplicate All to\" is not dispalyed for the course object via more action."+
		"Confirm \"Duplicate All\" and \"Duplicate All to\" is not dispalyed for the course object via details menu.");
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
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
				homePageObj.navigateToSitesTab();
				String siteNameValue = dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "Yes");
				String siteName = sitesPage.getCreatedSiteName();
				
	
				
		//Create content object in collections UI
				sitesPage.openSiteFromRecentSites(siteName);
				sitesPage.enterIntoDocumentLibrary();				
				String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				String createObjectData = dataTable.getData("MyFiles","CollectionObjectBasicData");
				collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
				
				sitesPage.enterIntoDocumentLibrary();	
				
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				
				collectionPg.clickOnEditCollectionButton();
				collectionPg.clickOnMoreSetting("AutoCourse");
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				//Read available options 
				ArrayList<String> availableOptions = new ArrayList<String>();
				availableOptions = collectionPg.readMoreOptions("AutoCourse");
				UIHelper.waitFor(driver);
				
				if(availableOptions.contains("Duplicate all")){
					
					report.updateTestLog("Check for Duplicate All option " , " Duplicate All option available: " + availableOptions , Status.FAIL);
					
				}
				else{
					report.updateTestLog("Check for Duplicate All option " , "Duplicate All option not available " +availableOptions , Status.PASS);

				}
				

				if(availableOptions.contains("Duplicate all to")){
					
					report.updateTestLog("Check for Duplicate All to option " , " Duplicate All to option available: " + availableOptions , Status.FAIL);
					
				}
				else{
					report.updateTestLog("Check for Duplicate All to option " , "Duplicate All to option not available " +availableOptions , Status.PASS);

				}
					
			// Create objects Other than course (
				
				sitesPage.enterIntoDocumentLibrary();	
				
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				
				collectionPg.clickOnEditCollectionButton();
				collectionPg.clickOnMoreSetting("AutoSeqObj");
				UIHelper.waitFor(driver);
				
						
				//Read available options 
				ArrayList<String> availableOptions1 = new ArrayList<String>();
				availableOptions1 = collectionPg.readMoreOptions("AutoSeqObj");
				UIHelper.waitFor(driver);
				
				if(availableOptions1.contains("Duplicate all")){
					
					report.updateTestLog("Check for Duplicate All option " , " Duplicate All option available: " + availableOptions1 , Status.PASS);
					
				}
				else{
					report.updateTestLog("Check for Duplicate All option " , "Duplicate All option not available " +availableOptions1 , Status.FAIL);

				}
				

				if(availableOptions1.contains("Duplicate all to...")){
					
					report.updateTestLog("Check for Duplicate All to option " , " Duplicate All to option available: " + availableOptions1 , Status.PASS);
					
				}
				else{
					report.updateTestLog("Check for Duplicate All to option " , "Duplicate All to option not available " +availableOptions1 , Status.FAIL);

				}
			
			
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
