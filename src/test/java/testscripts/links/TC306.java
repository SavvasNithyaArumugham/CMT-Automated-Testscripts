package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC306 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_033()
	{
		testParameters.setCurrentTestDescription("Verify the user is able to see Location field with Original and Secondary Attribute on the Perview Page of both Original file and linked file");
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
		ArrayList<String> originalFileLocationsList = new ArrayList<String>();
		ArrayList<String> linkedFileLocationsList = new ArrayList<String>();
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);



		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		
		myFiles.deleteCreatedFolder(folderDetails);
		
		myFiles.createFolder(folderDetails);
		
		myFiles.deleteUploadedFile(fileName);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		//Operation to Link a File
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, propertyName);
				
	
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		myFiles.openUploadedOrCreatedFile(fileName, "");
		
		originalFileLocationsList=docDetailsPageObj.getLocations(fileName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for (String folderName : folderNamesList) {
			myFiles.openFolder(folderName);
			myFiles.openUploadedOrCreatedFile(fileName, "");
			linkedFileLocationsList=docDetailsPageObj.getLocations(fileName);
		}
		docDetailsPageTest.verifyOriginalLocation(originalFileLocationsList.get(0));
		docDetailsPageTest.verifySecondaryLocation(linkedFileLocationsList.get(1));
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}