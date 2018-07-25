package testscripts.lifecycle;

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
public class AUT_AG_007 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_007()
	{
		testParameters.setCurrentTestDescription("Verify that if user attaches lifecycle state for any folder does not reflect on sub folder");
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
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
	
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderNme = dataTable.getData("Sites", "FileName");
		String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		myFiles.deleteCreatedFolder(folderDetails);
        myFiles.createFolder(folderDetails);
        AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
        
        myFiles.openCreatedFolder(folderNme); 
        
		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		sitesPage.enterIntoDocumentLibrary();	
		sitesPage.clickOnMoreSetting(folderNme);
		
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		sitePgTestObj.verifyAttachLifecycleForFileOrFolder(folderNme);
		String moreSettingsOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderNme, moreSettingsOpt);
		
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
		String browseOption = dataTable.getData("MyFiles", "BrowseActionName");
		sitesPage.commonMethodForPerformBrowseOption(folderNme, browseOption);
	
		String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
		
		docDetailsPg.backToFolderOrDocumentPage("");
		
		sitesPage.clickOnMoreSetting(folderNme);
		
		String changedLifecycleStateActions = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
	
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		if(changedLifecycleStateActions.contains(","))
		{
			String splittedActions[] = changedLifecycleStateActions.split(",");
			for(String actionName:splittedActions)
			{
				docLibPageTest.verifyBrowseAction(actionName, folderNme);
			}
		}
		else
		{
			docLibPageTest.verifyBrowseAction(changedLifecycleStateActions, folderNme);
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		myFiles.openCreatedFolder(folderNme);		
		sitesPage.clickOnMoreSetting(folderNme);
		sitePgTestObj.verifyAttachLifecycleForSubFileOrFolder(folderNme);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}