package testscripts.misc5;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_372 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC5_01()
	{
		testParameters.setCurrentTestDescription("1.Able to preview the Abobe illustrator file from document library after upload new version action."
				+"<br> 2. Able to preview the Abobe illustrator file from document library."
				+"<br> 2. Able to preview the Abobe illustrator file from My files."
				+"<br>3. Able to preview the Abobe illustrator file from shared files.");
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
		functionalLibrary.loginAsValidUser(signOnPage);
		

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPge.deleteAllFilesAndFolders();
		
		String fileName = dataTable.getData("MyFiles", "FileName");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, "");
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(fileName);
		String action = dataTable.getData("Document_Details", "Title");
		docLibPge.commonMethodForClickOnMoreSettingsOption(fileName, action);
		
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		docLibPge.uploadNewVersionFileInDocLibPage(fileName,
				filePath, versionDetails, comments);
		
		homePageObj.navigateToMyFilesTab();
		
		docLibPge.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, "");
		
		homePageObj.navigateToSharedFilesTab();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		
		if(sitesPage.Checkdocument(folderName)){
			sitesPage.documentdetails(folderName);
			docLibPge.deleteAllFilesAndFolders();
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}else{
			myFiles.createFolder(folderDetails);
			sitesPage.documentdetails(folderName);
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
		}
		
		//myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, "");
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}