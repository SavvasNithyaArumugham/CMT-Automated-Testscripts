package testscripts.collections9;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class ALFDEPLOY_1497_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("1.Confirm outgoing child references is created for the course and content object with "
						+ "files available in project files folder.");
		
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String fileName2 = dataTable.getData("MyFiles", "Subfolders1");
				
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		// go to Course plan and upload course
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");
		collectionPg.uploadFileInCollectionSite(filePath, fileName);

		// verify import process progress
		collectionPg.verifyImportProcessProgress(fileName);

		// Add files in Project Files folder
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder("Project Files");
				collectionPg.uploadFileInCollectionSite(filePath,fileName2);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		
		collectionPg.clickOnMoreSetting(folderNames[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], Option[0]);
		//Add Child reference from Project Files
		collectionPg.addProjectFilesChildReference("Project Files");
		UIHelper.waitFor(driver);
		//Check if child reference is added correctly
		collectionPg.clickOnMoreSetting(folderNames[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], Option[1]);
		UIHelper.waitFor(driver);
			
		String childRefFileAvailableCheck = "//div[@id='template_x002e_comments_x002e_folder-details_x0023_relationships-list-outgoingRelations']//div[@class='divCell']";
		if (UIHelper.checkForAnElementbyXpath(driver,childRefFileAvailableCheck))
	{
		UIHelper.highlightElement(driver, childRefFileAvailableCheck);
		report.updateTestLog("Confirm outgoing child references is created for the content object with files available in project files folder",
				"New outgoing child references is created", Status.PASS);	
	}
	else
	{
		report.updateTestLog("Confirm outgoing child references is created for the content object with files available in project files folder ",
				"New outgoing child references is not created ", Status.FAIL);	
	}
	
		// Check for Course
		
					sitesPage.enterIntoDocumentLibrary();
					myFiles.openCreatedFolder(folderNames[3]);
					myFiles.openCreatedFolder(folderNames[4]);
					collectionPg.clickOnEditCollectionButton();
					collectionPg.clickOnMoreSetting(folderNames[1]);
					collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[1], Option[0]);
					
					collectionPg.addProjectFilesChildReference("Project Files");
					UIHelper.waitFor(driver);
					collectionPg.clickOnMoreSetting(folderNames[2]);
					collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[1], Option[1]);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					
					String childRefFileAvailableCheck1 = "//div[@id='template_x002e_comments_x002e_folder-details_x0023_relationships-list-outgoingRelations']//div[@class='divCell']";
					if (UIHelper.checkForAnElementbyXpath(driver,childRefFileAvailableCheck1))
				{
					UIHelper.highlightElement(driver, childRefFileAvailableCheck1);
					report.updateTestLog("Confirm outgoing child references is created for the course with files available in project files folder",
							"New outgoing child references is created", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm outgoing child references is created for the course with files available in project files folder ",
							"New outgoing child references is not created ", Status.FAIL);	
				}
				
	}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
