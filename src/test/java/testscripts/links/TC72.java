package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC72 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_066()
	{
		testParameters.setCurrentTestDescription("Verify  User is able to delete all linked files when he select 'All links' from the warning message");
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
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		//homePageObj.navigateToSitesTab();
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		UIHelper.waitFor(driver);
		sitesPage.siteFinder(sourceSiteName);
	//	sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		String propertyName2 = dataTable.getData("Sites", "FilePropertyName2");
		docLibPg.deleteAllFilesAndFolders();
		//myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		myFiles.deleteUploadedFile(fileName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		//Operation to Link a File
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, propertyName);
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for (String folderName : folderNamesList) {
			myFiles.openFolder(folderName);
		}
		
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyUploadedFile(fileName,"");
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, propertyName2);
		
		sitesPage.clickOnDeleteAllLinks();
		
		myFilesTestObj.verifyDeletedFiles(fileName);
		sitesPage.enterIntoDocumentLibrary();
		myFilesTestObj.verifyDeletedFiles(fileName);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}