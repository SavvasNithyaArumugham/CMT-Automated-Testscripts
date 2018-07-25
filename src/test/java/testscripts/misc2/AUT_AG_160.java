package testscripts.misc2;


import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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

public class AUT_AG_160 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_056()
	{
		testParameters.setCurrentTestDescription("To verify that user is able to download combination of Files and Folders in ZIP format");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		

		sitesPage.siteFinder(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.deleteUploadedFile(fileName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		ArrayList<String> folderNamesList = new ArrayList<String>();
		folderNamesList = myFiles.getFolderNames(folderDetails);

		myFiles.commonMethodForSelectingFile(fileName);
		myFiles.commonMethodForSelectingFile(folderNamesList.get(0));
		
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		String archivefileName="Archive";
		
		docLibPg.downloadAsZipInDocumentLibrary(archivefileName);
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyDownloadedFile(true, archivefileName);
	}

	@Override
	public void tearDown() {
		
	}

}
