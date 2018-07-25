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
public class AUT_AG_039P4 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC039P4()
	{
		testParameters.setCurrentTestDescription("Verify if sitemanager is able to become owner of the file created by contributor by clicking 'Become Owner' in View details to have full ownership rights of the file<br> - Part4: Login with user and check edit/delete folder");
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
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String actionNameForFileOrFolder = dataTable.getData("MyFiles", "MoreSettingsOption");
		String propertyValues = dataTable.getData("Document_Details",
				"DocProperties");
		
		sitesPage.clickOnMoreSetting(fileName);
		
		sitePageTest.verifyMoreOptionsForFileOrFolder(fileName);
			
		boolean isDisplayedActionNameAction = sitesPage.checkActionNameForFolderOrFileInDocLibPage(actionNameForFileOrFolder, fileName);
		
		if(isDisplayedActionNameAction)
		{
			report.updateTestLog("Verify the '" + actionNameForFileOrFolder
					+ "' Option for any files/folder",
					"User able to view the '" + actionNameForFileOrFolder
							+ "' Option for any files/folder:<b>"
							+ fileName, Status.FAIL);
			
			sitesPage.clickOnEditProperties(fileName);
			docDetailsPage.clickAllProperties();
			
			docDetailsPage.enterDataAndSaveIntoEditProperties();
			
			//sitesPage.clickOnViewDetails(fileName);
			myFiles.openAFile(fileName);
			AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(
					scriptHelper);
			docDetailsPageTestObj.verifyPropertiesInDocDetailPage(propertyValues);
		}
		else
		{
			report.updateTestLog("Verify the '" + actionNameForFileOrFolder
					+ "' Option for any files/folder",
					"User unable to view the '" + actionNameForFileOrFolder
							+ "' Option for any files/folder:<b>"
							+ fileName, Status.PASS);
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		docLibPg.selectDocumentLibItems("All");
		
		sitesPage.clickOnSelectedItems();
		
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		if(sitePageTest.checkSelectedItemsDropdownOption(selectedItemMenuOptVal))
		{
			docLibPg.selectDocumentLibItems("None");
			myFiles.deleteUploadedFile(fileName);
			myFilesTestObj.verifyDeletedFileNegativeCase(fileName);
			
		}
		else
		{
			report.updateTestLog(
					"Verify the "
							+ selectedItemMenuOptVal
							+ "Option for file:"+fileName+" from Selected Items dropdown",
					"User is not able to see the 'Selected Items' Dropdown Option:"
							+ selectedItemMenuOptVal+" and unable to delete file",
					Status.PASS);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}