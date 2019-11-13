package testscripts.nalsepsbatchpublish;


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

public class AUT_BP_ZAllCases extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_001()
	{
		testParameters.setCurrentTestDescription("1) Verify the 'Batch Publish' option is available in selected items drop down when selecting files in documents library page."
										   +"</br>2) Verify the user is able to publish any object (Folder or file) of a publishable site by selecting any folders and files using 'Batch Publish' option."
										   +"</br>3) Verify only the selected files listed in the 'Batch Publish' pop up.");
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
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);		
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String files = dataTable.getData("MyFiles", "Version");
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		
		//RevisedFileAndFolder Starts
		
		//Step1: Revise the folder by adding a file
			String splittedFileNames[] = files.split(",");		
			myFiles.openCreatedFolder(splittedFileNames[1]);
			myFiles.uploadFile(filePath, fileName);
			
		sitesPage.enterIntoDocumentLibrary();
		
		// upload new version
		sitesPage.clickOnMoreSetting(splittedFileNames[0]);
		doclib.commonMethodForClickOnMoreSettingsOption(splittedFileNames[0], "Upload New Version");
		doclib.uploadNewVersionInDocLibPage(splittedFileNames[0], filePath,"Minor","minor change");
		doclib.uploadNewVersionButton();
		UIHelper.waitFor(driver);
		//RevisedFileAndFolder Ends
		
		//Unpublished File and Folder Starts
		
		myFiles.createFolder(folderDetails);
		ArrayList<String> folderNames=new ArrayList<String>(); 
		folderNames=myFiles.getFolderNames(folderDetails);
		for(String folderName:folderNames)
		{
			myFiles.openCreatedFolder(folderName);	
		}
		
		myFiles.uploadFile(filePath, fileName);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.uploadFile(filePath, fileName);
		
		//Unpublished File and Folder Ends
		
		//Select the items for batch publish
		myFiles.commonMethodForSelectingFiles(splittedFileNames[0]);
		myFiles.commonMethodForSelectingFiles(splittedFileNames[1]);
		myFiles.commonMethodForSelectingFiles(splittedFileNames[2]);
		myFiles.commonMethodForSelectingFiles(splittedFileNames[3]);
		myFiles.commonMethodForSelectingFiles(splittedFileNames[4]);
		myFiles.commonMethodForSelectingFiles(splittedFileNames[5]);
		
		sitesPage.clickOnSelectedItems();
		UIHelper.waitFor(driver);		
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptVal);		
		doclib.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);		
		AlfrescoEPSPageTest epsPageTest = new AlfrescoEPSPageTest(scriptHelper);		
		epsPage.clickBatchPublishButton();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		if (files.contains(",")) {
			String splittedFileNames1[] = files.split(",");
			for (String fileNameVal1 : splittedFileNames1) {
				docLibPgTest.verifyFilePublished(fileNameVal1);
				UIHelper.pageRefresh(driver);
			}
		} else {
			docLibPgTest.verifyFilePublished(files);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		if (files.contains(",")) {
			String splittedFileNames1[] = files.split(",");
			for (String fileNameVal : splittedFileNames1) {
				UIHelper.waitFor(driver);
				epsPage.checkEPSID(fileNameVal);
				UIHelper.waitFor(driver);
			}
		} else {
			UIHelper.waitFor(driver);
			epsPage.checkEPSID(files);
			UIHelper.waitFor(driver);
			
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}