package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC17 extends TestCase{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void TestTC41(){
		testParameters.setCurrentTestDescription("Verify the admin/Non admin users is able to add association relationship between files");
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
		ArrayList<String> filesNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		if(homePage.isAdminToolsAvailable()){
			homePage.navigateToAdminTab();	
			String adminToolsOption = dataTable.getData("Admin", "AdminToolsOptions");
			AlfrescoAdminToolsPage adminToolsPage = new AlfrescoAdminToolsPage(scriptHelper);
			adminToolsPage.navigateToAdminToolsOptionsLink(adminToolsOption);	
			adminToolsPage.navigateToNewRelationship();
			
			adminToolsPage.createBiDirectionalRelationship();
			
			AlfrescoAdminToolsPageTest adminToolsPageTest = new AlfrescoAdminToolsPageTest(scriptHelper);			
			adminToolsPageTest.verifyBiDirectionalAssociationCreated();
			

			/*String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");

			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			homePageObj.navigateToSitesTab();

			String sourceSiteName = dataTable.getData("Sites", "SiteName");

			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
					scriptHelper);
		
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();

			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			myFiles.deleteUploadedFile(fileName);
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			filesNamesList = myFiles.getFileNames(fileName);

			String propertyName = dataTable.getData("Sites", "FilePropertyName");
			sitesPage.clickOnMoreLinkOptions(filesNamesList.get(0), propertyName);

			String relationshipName = dataTable.getData("Admin",
					"Bi-Directional_Label");
			sitesPage.addRelationship(relationshipName, filesNamesList.get(1));

			myFiles.openUploadedOrCreatedFile(filesNamesList.get(0), "");
			docDetailsPageTest.verifyUploadedFileIsOpened(filesNamesList.get(0), "");

			docDetailsPageTest.verifyAddedRelationshipData(filesNamesList.get(1));*/
												
			
		}else{
			report.updateTestLog("Admin Tools Option not available",
					"User dont have Admin rights", Status.FAIL);
		}		
	}

	@Override
	public void tearDown() {
		
	}
}
