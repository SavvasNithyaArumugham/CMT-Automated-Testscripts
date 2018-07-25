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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_015 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_015()
	{
		testParameters.setCurrentTestDescription("Verify that the user is able to 'Attach and changes lifecycle State' for any folder named with special characters");
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
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		
		homePage.navigateToSitesTab();
		
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
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
        myFiles.createFolder(folderDetails);

        ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
        
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnMoreSetting(folderName);
		
			sitePgTestObj.verifyAttachLifecycleForFileOrFolder(folderName);
		
			String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, docActionVal);
		
			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
			String browseOption = dataTable.getData("MyFiles", "BrowseActionName");
			sitesPage.commonMethodForPerformBrowseOption(folderName, browseOption);
		
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,folderName+" LifeCycle");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			sitesPage.clickOnMoreSetting(folderName);
			
			String changedLifecycleStateActions = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			if(changedLifecycleStateActions.contains(","))
			{
				String splittedActions[] = changedLifecycleStateActions.split(",");
				for(String actionName:splittedActions)
				{
					docLibPageTest.verifyBrowseAction(actionName, folderName);
				}
			}
			else
			{
				docLibPageTest.verifyBrowseAction(changedLifecycleStateActions, folderName);
			}
			
			String lifecycleAction = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, lifecycleAction);
			
			String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Sites", "LifecyclePromoteStateDropdownValue");
			docDetailsPg.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue);
			
			sitesPage.commonMethodForPerformBrowseOption(folderName, browseOption);
			
			String changedLifecyclePropValuesForPromote = dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForPromote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForPromote,folderName+" LifeCycle");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}