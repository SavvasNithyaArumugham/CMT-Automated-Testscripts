package testscripts.misc1;

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
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_033P4 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC033P4()
	{
		testParameters.setCurrentTestDescription("Verify if sitemanager is able to become owner of the folder created by contributor by clicking 'Become owner' in View details to have full ownership rights of the folder<br> - Part4: Login with user and check edit/delete folder");
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
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		String siteName = dataTable.getData("Sites", "SiteName");

		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String veiwDetailsOptionFileOrFolder = dataTable.getData("MyFiles", "BrowseActionName");
		String actionNameForFileOrFolder = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String propertyValues = dataTable.getData("Document_Details", "DocProperties");
		
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			sitePageTest.verifyActionNameForFileOrFolder(veiwDetailsOptionFileOrFolder, folderName);
			
			boolean isDisplayedActionNameAction = sitesPage.checkActionNameForFolderOrFileInDocLibPage(actionNameForFileOrFolder, folderName);
			
			if(isDisplayedActionNameAction)
			{
				report.updateTestLog("Verify the '" + actionNameForFileOrFolder
						+ "' Option for any files/folder",
						"User able to view the '" + actionNameForFileOrFolder
								+ "' Option for any files/folder:<b>"
								+ folderName, Status.FAIL);
				
				sitesPage.clickOnEditProperties(folderName);
				docDetailsPage.clickAllProperties();
				
				docDetailsPage.enterDataAndSaveIntoEditProperties();
				
				sitesPage.clickOnViewDetails(folderName);
				AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(
						scriptHelper);
				docDetailsPageTestObj.verifyPropertiesInDocDetailPage(propertyValues);
				
				sitesPage.enterIntoDocumentLibraryWithoutReport();
			}
			else
			{
				report.updateTestLog("Verify the '" + actionNameForFileOrFolder
						+ "' Option for any files/folder",
						"User unable to view the '" + actionNameForFileOrFolder
								+ "' Option for any files/folder:<b>"
								+ folderName, Status.PASS);
			}
			
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			//docLibPg.selectDocumentLibItems("None");
			
			docLibPg.selectAllFilesAndFolders();
			
			sitesPage.clickOnSelectedItems();
			
			String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
			
			if(sitePageTest.checkSelectedItemsDropdownOption(selectedItemMenuOptVal))
			{
				docLibPg.selectDocumentLibItems("None");
				myFiles.commonMethodFordeleteFolder(folderName);
				myFilesTestObj.verifyFolderDeletionForNegativeCase(folderName);
				
			}
			else
			{
				report.updateTestLog(
						"Verify the "
								+ selectedItemMenuOptVal
								+ "Option for folder:"+folderName+" from Selected Items dropdown",
						"User is not able to see the 'Selected Items' Dropdown Option:"
								+ selectedItemMenuOptVal+" and unable to delete Folder",
						Status.PASS);
			}
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}