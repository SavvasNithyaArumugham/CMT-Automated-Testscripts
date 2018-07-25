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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC308 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_035()
	{
		testParameters.setCurrentTestDescription("Verify the user is navigated to Secondary  file  location on clicking 'secondary' files Present under 'Location' Field");
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
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		myFiles.deleteCreatedFolder(folderDetails);
		
		myFiles.createFolder(folderDetails);
		
		ArrayList<String> createdFileNames = myFilesTestObj.getCreatedFileNames(fileDetails);
		for(String fileName:createdFileNames)
		{
			myFiles.deleteUploadedFile(fileName);
			myFiles.createFile(fileDetails);
			docDetailsPageTest.verifyCreatedFile(fileName, "");
			docDetailsPageObj.backToFolderOrDocumentPage("");

			//Operation to Link a File
			sitesPage.clickOnMoreSetting(fileName);
			sitesPage.clickOnMoreLinkOptions(fileName, propertyName);
			docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
			docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		}
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		for (String folderName : folderNamesList) {
			myFiles.openFolder(folderName);
			for(String fileName:createdFileNames)
			{
			myFiles.openUploadedOrCreatedFile(fileName, "");
			docDetailsPageObj.navigateToSecondaryFileLocation();
			System.out.println(fileName);
			myFilesTestObj.verifyIsFileAvilabile(fileName,"");
			}
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}