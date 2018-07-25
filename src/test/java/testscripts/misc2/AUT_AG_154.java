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
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_154 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_050()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to Perform 'Download as zip' for source folder containing primary linked files");
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
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		folderNamesList = myFiles.getFolderNames(folderDetails);
		myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		
		myFiles.openCreatedFolder(folderNamesList.get(0));
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		String selectedItem=dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		myFiles.methodToSelectMultipleFiles(fileName);
		
		sitesPage.clickOnSelectedItems();
		sitesPage.selectItemFromSelectedItemsMenuOption(selectedItem);
		
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		sitesPage.enterIntoDocumentLibrary();
	
		myFiles.openCreatedFolder(folderNamesList.get(0));
		myFilesTestObj.verifyMultipleFilesAvailable(fileName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String zipFileName=folderNamesList.get(0)+".zip";
		FileUtil file=new FileUtil();
		String downloadPath = properties.getProperty("DefaultDownloadPath");
		
		file.deleteIfFileExistsInDownloadPath(downloadPath, zipFileName);
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		if(folderNamesList.size() > 0){
			docLibPage.downloadAsZipInDocumentLib(folderNamesList.get(0));
		}
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyDownloadedFile(true, folderNamesList.get(0));
		docDetailsPageTest.verifyFileinDownloadedZipFile(downloadPath, zipFileName, fileName);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}