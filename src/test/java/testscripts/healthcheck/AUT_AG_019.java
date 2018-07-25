package testscripts.healthcheck;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * To test the user is able to Add/delete Relationship from the document details page.
 * 
 * @author Cognizant
 */
public class AUT_AG_019 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTC4() {
		testParameters
				.setCurrentTestDescription("Verify that the user is able to Add/Delete Realtionship from the Preview page.");
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
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);

		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		String relationshipName = dataTable.getData("Admin","Bi-Directional_Label");
		ArrayList<String> filesNamesList = new ArrayList<String>();
		filesNamesList = myFiles.getFileNames(fileName);
	
//		System.out.println(relationshipName);
//		System.out.println(propertyName);

		
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		
//		1).add Relationship 	

		//delete all uploaded files 
		docLibPg.deleteAllFilesAndFolders();		

//		upload required files
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.documentdetails(filesNamesList.get(0));
		
		//docDetailsPageTest.verifyDocAction(docActionName);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		docDetailsPageObj.commonMethodForPerformDocAction(propertyName);
		
		
/*//		click on add relationship
		sitesPage.clickOnMoreSetting(filesNamesList.get(0));
		docLibPg.commonMethodForClickOnMoreSettingsOption(filesNamesList.get(0), propertyName);		
		*/
//		add relationship 
		sitesPage.addRelationship(relationshipName,filesNamesList.get(1) );

//		open file
		myFiles.openUploadedOrCreatedFile(filesNamesList.get(0), "");
		
//		verify file
		docDetailsPageTest.verifyUploadedFileIsOpened(filesNamesList.get(0), "");

//		verify relationship
		docDetailsPageTest.verifyAddedRelationshipData(filesNamesList.get(1));
		
//		2).delete Relationship 
		docDetailsPage.deleteRelationshipData( filesNamesList.get(1));
		
	

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}