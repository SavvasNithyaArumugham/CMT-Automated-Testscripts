package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_396 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-3415_Verify user is able to access any type of file inside a folder that contains percent symbol in search results page<br>"+
				"ALFDEPLOY-3415_Verify user is able to access any type of file inside a folder that contains percent symbol in shared files page<br>"+
				"ALFDEPLOY-3415_Verify user is able to access any type of file inside a sub folder under folder that contains percent symbol in document library<br>"+
				"ALFDEPLOY-3415_Verify user able to access the file which contains percent symbol inside a folder that contains percent symbol in document library<br>"+
				"ALFDEPLOY-3415_Verify user is able to access any type of file inside a folder that contains percent symbol in document library<br>"+
				"ALFDEPLOY-3415_Verify user is able to access any type of file inside a folder that contains percent symbol in My files page");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		
		String siteName = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderDetailsSearch = dataTable.getData("MyFiles", "RelationshipName");
		String folderSearch = dataTable.getData("MyFiles", "AccessToken");	
		String subfolderDetails = dataTable.getData("MyFiles", "MoreSettingsOption");
		String subfolder = dataTable.getData("MyFiles", "Sort Options");
		String search = dataTable.getData("MyFiles", "PopUpMsg");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		
		

		
		
		functionalLibrary.loginAsValidUser(signOnPage);
	
		homePageObj.navigateToMyFilesTab();
		
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetailsSearch);
		sitesPage.documentdetails(folderSearch);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		if(fileName.contains(","))
		{
			String splittedFileNames[] = fileName.split(",");
			
			if(splittedFileNames!=null)
			{
				for(String fileNameVal:splittedFileNames)
				{
					sitesPage.documentdetails(fileNameVal);

					docDetailsPageTest.verifyUploadedFileIsPreviewd(fileNameVal, "");
					
					driver.navigate().back();
					UIHelper.waitForPageToLoad(driver);
		
				}
			}
		}
		
	
		homePageObj.alfrescoSearch(folderSearch);
		appSearchPgTest.commonMethodForVerifySearchResults(folderSearch);

		appSearchPgTest.clickodresultfolder(search);
		
		if(fileName.contains(","))
		{
			String splittedFileNames[] = fileName.split(",");
			
			if(splittedFileNames!=null)
			{
				for(String fileNameVal:splittedFileNames)
				{
					sitesPage.documentdetails(fileNameVal);

					docDetailsPageTest.verifyUploadedFileIsPreviewd(fileNameVal, "");
					
					driver.navigate().back();
					UIHelper.waitForPageToLoad(driver);
		
				}
			}
		}
		
		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(folder);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		if(fileName.contains(","))
		{
			String splittedFileNames[] = fileName.split(",");
			
			if(splittedFileNames!=null)
			{
				for(String fileNameVal:splittedFileNames)
				{
					sitesPage.documentdetails(fileNameVal);

					docDetailsPageTest.verifyUploadedFileIsPreviewd(fileNameVal, "");
					
				//	docDetailsPageTest.verifyPearsonLogoInDocDetailsPg(fileNameVal);
					
					driver.navigate().back();
					UIHelper.waitForPageToLoad(driver);
				}
			}
		}
		
		myFiles.createFolder(subfolderDetails);
		sitesPage.documentdetails(subfolder);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		if(fileName.contains(","))
		{
			String splittedFileNames[] = fileName.split(",");
			
			if(splittedFileNames!=null)
			{
				for(String fileNameVal:splittedFileNames)
				{
					sitesPage.documentdetails(fileNameVal);

					docDetailsPageTest.verifyUploadedFileIsPreviewd(fileNameVal, "");
					
				//	docDetailsPageTest.verifyPearsonLogoInDocDetailsPg(fileNameVal);
					
					driver.navigate().back();
					UIHelper.waitForPageToLoad(driver);
				}
			}
		}
		
		myFiles.createFile(fileDetails);
	//	sitesPage.documentdetails(folderSearch);
		
		docDetailsPageTest.verifyUploadedFileIsPreviewd(folderSearch, "");
		
		homePageObj.navigateToSharedFilesTab();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
			
		if(sitesPage.documentAvailable(folder)){
			sitesPage.documentdetails(folder);
			docLibPg.deleteAllFilesAndFolders();
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}else{
			myFiles.createFolder(folderDetails);
			sitesPage.documentdetails(folder);
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
		}
		
		if(fileName.contains(","))
		{
			String splittedFileNames[] = fileName.split(",");
			
			if(splittedFileNames!=null)
			{
				for(String fileNameVal:splittedFileNames)
				{
					sitesPage.documentdetails(fileNameVal);

					docDetailsPageTest.verifyUploadedFileIsPreviewd(fileNameVal, "");
					
					driver.navigate().back();
					UIHelper.waitForPageToLoad(driver);
				}
			}
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}