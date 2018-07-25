package testscripts.lifecycle;

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
public class AUT_AG_009 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_009()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is able to see 'Attach Lifecycle' Option for Folder and files from Selected Items dropdown"
				+ "<br>2. Verify that the user is able to 'Attach lifecycle State' for folders and files  from the selected Items dropdown"
				+ "<br>3. Verify that user is able to see detach option from Selected Items Menu when he selects only attached lifecycle state files and folders"
				+ "<br>4. Verify that user is not  able to see detach option from Selected Items Menu if he select any non-attached lifecycle state files or folders"
				+ "<br>5. Verify that user is not able to see attach lifecycle option from Selected Items Menu when he selects any attached lifecycle state files and folders"
				+ "<br>6. Verify that user is able to see 'Attach Lifecyle' Option for any folder by hovering mouse on any folder/from selected Items dropdown/Folder Details Page");
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
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(
				scriptHelper);
		
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
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		myFiles.createFile(fileDetails);
		
		docDetailsPageObj.backToFolderOrDocumentPage("");
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
        myFiles.createFolder(folderDetails);
        
        docLibPgObj.selectAllFilesAndFolders();

		sitesPage.clickOnSelectedItems();

		String lifecycleOptNameForDetach = dataTable.getData("Document_Details", "LifecycleActionNameForDetach");
		sitePgTestObj.verifySelectedItemsMenuOptionForNegativeCase(lifecycleOptNameForDetach);
        
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
	
		sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
		
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
		docLibPgObj.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		sitePageTest.verifySelectedItemsMenuOption(lifecycleOptNameForDetach);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		String browseOption = dataTable.getData("MyFiles", "BrowseActionName");
		
		for(String folderName:folderNamesList)
		{
			sitesPage.commonMethodForPerformBrowseOption(folderName, browseOption);
		
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			docLibPgObj.selectAllFilesAndFolders();

			sitesPage.clickOnSelectedItems();
			
			sitePgTestObj.verifySelectedItemsMenuOption(lifecycleOptNameForDetach);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		myFiles.createFolder(folderDetails);
        
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			myFiles.selectMultipleFilesOrFolders(folderName);
			
			sitesPage.clickOnSelectedItems();
			
			sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
			
			sitesPage.clickOnMoreSetting(folderName);
			
			sitePgTestObj.verifyAttachLifecycleForFileOrFolder(folderName);
			
			sitesPage.enterIntoDocumentLibrary();
			
			sitesPage.clickOnFileORFolderPropertiesOption(folderName, browseOption);
			
			docDetailsPageTest.verifyDocAction(selectedItemMenuOptVal);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}