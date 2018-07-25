package testscripts.misc2;


import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_156 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_052()
	{
		testParameters.setCurrentTestDescription("To verify that user is able to download multiple files in ZIP format");
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
		
		AlfrescoSitesPage sitesPage =new AlfrescoSitesPage(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
			
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileNames = dataTable.getData("MyFiles", "FileName");
		myFiles.deleteUploadedFile(fileNames);
		myFiles.uploadFileInMyFilesPage(filePath, fileNames);
		
		ArrayList<String> filesList=new ArrayList<String>();
		filesList=myFiles.getFileNames(fileNames);
		
		myFiles.commonMethodForSelectingFile(filesList.get(0));
		myFiles.commonMethodForSelectingFile(filesList.get(1));
		
		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		docLibPage.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		String archivefileName="Archive";
		
			docLibPage.downloadAsZipInDocumentLibrary(archivefileName);
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyDownloadedFile(true, archivefileName);
	}

	@Override
	public void tearDown() {
		
	}

}
