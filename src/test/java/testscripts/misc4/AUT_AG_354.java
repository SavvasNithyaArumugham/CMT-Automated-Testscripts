package testscripts.misc4;

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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Cognizant
 */
public class AUT_AG_354 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc4_049()
	{
		testParameters.setCurrentTestDescription("Add a new or change-version existing briefing document");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		ArrayList<String> folderNamesList = new ArrayList<String>();
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		//myFiles.deleteCreatedFolder(folderDetails);

		//myFiles.createFolder(folderDetails);
        AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		//myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		//for(String folderName:folderNamesList)
		//{
			//myFiles.openCreatedFolder(folderName);
			
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			myFiles.uploadFile(filePath, fileName);
			
			myFilesTestObj.verifyUploadedFile(fileName,"");
			
			myFiles.openUploadedOrCreatedFile(fileName,"");
			
			docDetailsPage.performManageAspectsDocAction();
			docDetailsPage.addAspectsAndApllyChangesToAFile();
			//docDetailsPage.performEditPropertiesDocAction();
			
			AlfrescoAdminToolsPage appAdminToolsPg = new AlfrescoAdminToolsPage(scriptHelper);
			appAdminToolsPg.addNewCatMgmt();
			appAdminToolsPg.saveTag();
			
			sitesPage.documentlib();
			AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
			String rootCategory = dataTable.getData("Admin", "EditCategoryName");
			String category = dataTable.getData("Admin", "CategoryName");
			docLibPage.filterCategory(rootCategory);
			docLibPage.filterCategory(category);
			
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			docLibPageTest.VerifyDocCategory();
			
			sitesPage.enterIntoDocumentLibrary();
		//	myFiles.openCreatedFolder(folderName);
			myFiles.openUploadedOrCreatedFile(fileName,"");
			
		//	AlfrescoDocumentDetailsPage appDocDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		//	String uploadedFilesName = appDocDetailsPg.getDocumentHeaderName();
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPg.uploadNewVersion();
			
			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
			
			docDetailsPageTest.verifyUploadedVersion(fileName);
			
			sitesPage.documentlib();
	
			docLibPage.filterCategory(rootCategory);
			docLibPage.filterCategory(category);
			docLibPageTest.VerifyDocCategory();
			
		//}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}