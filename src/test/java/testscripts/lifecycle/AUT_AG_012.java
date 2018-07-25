package testscripts.lifecycle;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_012 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_012()
	{
		testParameters.setCurrentTestDescription("Verify that Options like Promote and Demote are not available on the selected Items Dropdown on attaching lifecycle state to any folder and files");
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
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(scriptHelper);
		
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
	
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
	
		if(selectedItemMenuOptVal.contains(","))
		{
			String splittedOptions[] = selectedItemMenuOptVal.split(",");
			if(splittedOptions!=null && splittedOptions.length==3)
			{
				sitePageTest.verifySelectedItemsMenuOption(splittedOptions[0]);
				
				docLibPg.commonMethodForClickOnSelectedItemsMenuOption(splittedOptions[0]);
				
				String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
				AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
				docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
				
				sitesPage.clickOnSelectedItems();
				sitePageTest.verifySelectedItemsMenuOptionForNegativeCase(splittedOptions[1]);
				
				sitesPage.clickOnSelectedItems();
				sitePageTest.verifySelectedItemsMenuOptionForNegativeCase(splittedOptions[2]);
			}
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}