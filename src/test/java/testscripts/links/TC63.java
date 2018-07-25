package testscripts.links;

import java.util.ArrayList;
import java.util.Properties;

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


public class TC63 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_063()
	{
		testParameters.setCurrentTestDescription("Verify that link Icon is not visible for linked files and folder when it copied to other location");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		String selectedItem=dataTable.getData("Sites", "SelectedItemsMenuOption");
		String selectedItem2=dataTable.getData("Sites", "FilePropertyName");
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		myFiles.createFolder(folderDetails);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFiles.methodToSelectMultipleFiles(fileName);
		
		sitesPage.clickOnSelectedItems();
		sitesPage.selectItemFromSelectedItemsMenuOption(selectedItem);
		
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		sitesPage.enterIntoDocumentLibrary();
	
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFiles.openFolder(folderNamesList.get(0));
		myFilesTestObj.verifyMultipleFilesAvailable(fileName);
		
		myFiles.methodToSelectMultipleFiles(fileName);
		sitesPage.clickOnSelectedItems();
		sitesPage.selectItemFromSelectedItemsMenuOption(selectedItem2);
		System.out.println(folderNamesList.get(1));
		docDetailsPageObj.selectFolderToCopyInCopyPopUp(folderNamesList.get(1));
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderNamesList.get(1));
		myFilesTestObj.verifyMultipleFilesAvailable(fileName);
		myFilesTestObj.verifyLinkImageDisplayed(fileName);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}