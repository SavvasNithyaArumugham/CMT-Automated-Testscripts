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
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_002 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_001()
	{
		testParameters.setCurrentTestDescription("1) Verify the 'Batch Publish' option is available in selected items drop down when selecting folders or file or Folders with files in documents library page."
										   +"</br>2) Verify the user is able to publish any object (Folder or file) of a publishable site by selecting any folders and files using 'Batch Publish' option."
										   +"</br>3) Verify only the selected folders and files listed in the 'Batch Publish' pop up.");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage doclib=new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoEPSPage epsPage=new AlfrescoEPSPage(scriptHelper);
		
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		
		sitesPage.siteFinder(sourceSiteName);		
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		
		ArrayList<String> folderNames=new ArrayList<String>(); 
		folderNames=myFiles.getFolderNames(folderDetails);
		for(String folderName:folderNames)
		{
			myFiles.openCreatedFolder(folderName);	
		}
		
		myFiles.uploadFile(filePath, fileName);
		
		myFiles.methodToSelectMultipleFiles(fileName);
		sitesPage.clickOnSelectedItems();
		
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
		
		doclib.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		AlfrescoEPSPageTest epsPageTest = new AlfrescoEPSPageTest(scriptHelper);
		epsPageTest.verifyFilesListedOnBatchPublish(fileName);
		
		epsPage.clickBatchPublishButton();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		if (fileName.contains(",")) {
			String splittedFileNames[] = fileName.split(",");
			for (String fileNameVal : splittedFileNames) {
				docLibPgTest.verifyFilePublished(fileNameVal);
			}
		} else {
			docLibPgTest.verifyFilePublished(fileName);
		}
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}