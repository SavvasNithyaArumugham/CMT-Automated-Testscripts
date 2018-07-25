package testscripts.eps;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_015 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_006()
	{
		testParameters.setCurrentTestDescription("Verify the user is not able to publish any object (Folder or file) of a non publishable site by selecting subset of any folder using 'Batch Publish' option.");
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
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		sitesPage.openSiteFromRecentSites(sourceSiteName); 		
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		AlfrescoDocumentLibPage doclib=new AlfrescoDocumentLibPage(scriptHelper);
		
		ArrayList<String> folderNamesList=new ArrayList<String>(); 
		folderNamesList = myFiles.getFolderNames(folderDetails);
	
		 if(sitesPage.CheckFolderOrFile(folderNamesList.get(0))){
			}
	        else
	        {
	        	myFiles.createFolder(folderDetails);
	        }
		
		for(String folderName:folderNamesList)
		{
			myFiles.openCreatedFolder(folderName);	
		}

		
		if(docLibPge.isFileIsAvailable(fileName))
		{
			docLibPge.deleteFileInDocumentLibrary();
			//myFiles.deleteUploadedFile(fileNme);
		}
		
		myFiles.uploadFile(filePath, fileName);
		

		myFiles.methodToSelectMultipleFiles(fileName);
		sitesPage.clickOnSelectedItems();
		
		doclib.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		AlfrescoEPSPage epsPage=new AlfrescoEPSPage(scriptHelper);
		epsPage.clickBatchPublishButton();
		
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		docLibPgTest.verifyFilePublishedInNonPubSite(fileName);
		
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}