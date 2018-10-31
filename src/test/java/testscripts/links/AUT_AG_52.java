package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocPreviewPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Sharanya
 */
public class AUT_AG_52 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LINKS_045() {
		testParameters
				.setCurrentTestDescription(
						
						"ALFDEPLOY-2337_Verify user should not see the narrow thumbnail next to relationship"
						+ " file path in asset perview page for related assets in same site"
						+"ALFDEPLOY-2337_Verify user should not see the narrow thumbnail next to relationship "
						+ "file path in asset perview page for related assets in different site"
						+"ALFDEPLOY-2337_Verify user should not see the narrow thumbnail next to relationship "
						+ "file path in asset perview page for related assets in myfiles"
						+"ALFDEPLOY-2337_Verify user should not see the narrow thumbnail next to relationship "
						+ "file path in asset perview page for related assets in sharedfiles"
						
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
		
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocPreviewPage DocPreviewPage =  new AlfrescoDocPreviewPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		// variable Creation
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String siteName = dataTable.getData("Sites", "SiteName");
		String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		String Username = dataTable.getData("General_Data", "Username");
		
		//Image Upload in the Target Site:
		functionalLibrary.loginAsValidUser(signOnPage);
		UIHelper.waitFor(driver);
		//Modified as part of NALS
		//sitesPage.navigateToSitesTab();
		//sitesPage.siteFinder(targetSiteName);
		sitesPage.openSiteFromRecentSites(targetSiteName);
		
		sitesPage.enterIntoDocumentLibrary();	
		docLibPg.deleteAllFilesAndFolders();		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
		
		UIHelper.waitForPageToLoad(driver);
		
		
		// Upload Image in My files:
		UIHelper.click(driver, docLibPg.myfilesxpath);	
		Boolean flag = docLibPg.isFileIsAvailable(fileName[0]);
			if(flag){
			}else{
			collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
			}
		
		// Upload Image in Shared Files:
		UIHelper.click(driver, docLibPg.sharedfilesXpath);
		Boolean flag1 = docLibPg.isFileIsAvailable(fileName[0]);
			if(flag1){
			}else{
			collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
			}
			
		//navigated back to source site
		sitesPage.navigateToSitesTab();
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();	
		docLibPg.deleteAllFilesAndFolders();
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);
		
		
		// Same Site
		report.updateTestLog("Testcase verification","<b>Same Site relationship Image validation",Status.DONE);
		sitesPage.clickOnMoreSetting(fileName[1]);
		sitesPage.clickOnMoreOptionLink(fileName[1]);
		
		sitesPage.addRelationship(relationshipName, fileName[0]);
		myFiles.openAFile(fileName[1]);
		docDetailsPageTest.verifyAddedRelationshipData(fileName[0]);
		sitesPage.isrelationshipimagedisplayed(fileName[0]);
		Boolean flag2 = DocPreviewPage.isDeleteRelatioshipAvailableInPreview(fileName[0]);		
		if(flag2){
			docDetailsPage.deleteRelationshipData(fileName[0]);
			report.updateTestLog("Verify Delete Relationship data for a file",
						"Delete relationship '" + fileName[0] + "' is presented"
								,Status.PASS);
		}else{
			
								report.updateTestLog("Verify Delete Relationship data for a file",
						"Delete relationship '" + fileName[0] + "' is not presented"
								,Status.FAIL);
		}
				
		// Different Site
		report.updateTestLog("Testcase verification","<b>Different Site relationship Image validation",Status.DONE);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(fileName[1]);
		sitesPage.clickOnMoreOptionLink(fileName[1]);
		sitesPage.addRelationshipInDifSiteinsidefoler(relationshipName,fileName[0],targetSiteName);
		myFiles.openAFile(fileName[1]);
		docDetailsPageTest.verifyAddedRelationshipData(fileName[0]);
		sitesPage.isrelationshipimagedisplayed(fileName[0]);
		Boolean flag4 = DocPreviewPage.isDeleteRelatioshipAvailableInPreview(fileName[0]);	
		if(flag4){
			docDetailsPage.deleteRelationshipData(fileName[0]);
			report.updateTestLog("Verify Delete Relationship data for a file",
					"Delete relationship '" + fileName[0] + "' is presented"
							,Status.PASS);
		}else{
			report.updateTestLog("Verify Delete Relationship data for a file",
						"Delete relationship '" + fileName[0] + "' is not presented"
								,Status.FAIL);
		}
		
	
		//MyFiles:
		report.updateTestLog("Testcase verification","<b>My files relationship Image validation",Status.DONE);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(fileName[1]);
		sitesPage.clickOnMoreOptionLink(fileName[1]);
		sitesPage.addRelationshipInMyfiles(relationshipName,fileName[0],targetSiteName,Username);
		myFiles.openAFile(fileName[1]);
		docDetailsPageTest.verifyAddedRelationshipData(fileName[0]);
		sitesPage.isrelationshipimagedisplayed(fileName[0]);
		Boolean flag5 = DocPreviewPage.isDeleteRelatioshipAvailableInPreview(fileName[0]);	
		if(flag5){
			docDetailsPage.deleteRelationshipData(fileName[0]);
			report.updateTestLog("Verify Delete Relationship data for a file",
					"Delete relationship '" + fileName[0] + "' is presented"
							,Status.PASS);
		}else{
			report.updateTestLog("Verify Delete Relationship data for a file",
						"Delete relationship '" + fileName[0] + "' is not presented"
								,Status.FAIL);
		}
		
		// Shared Files
		report.updateTestLog("Testcase verification","<b>SharedFiles relationship Image validation",Status.DONE);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(fileName[1]);
		sitesPage.clickOnMoreOptionLink(fileName[1]);
		sitesPage.addRelationshipInSharedfiles(relationshipName,fileName[0],targetSiteName);
		myFiles.openAFile(fileName[1]);
		docDetailsPageTest.verifyAddedRelationshipData(fileName[0]);
		sitesPage.isrelationshipimagedisplayed(fileName[0]);
		Boolean flag3 = DocPreviewPage.isDeleteRelatioshipAvailableInPreview(fileName[0]);	
		if(flag3){
			docDetailsPage.deleteRelationshipData(fileName[0]);
			report.updateTestLog("Verify Delete Relationship data for a file",
					"Delete relationship '" + fileName[0] + "' is presented"
							,Status.PASS);
		}else{
			report.updateTestLog("Verify Delete Relationship data for a file",
						"Delete relationship '" + fileName[0] + "' is not presented"
								,Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}