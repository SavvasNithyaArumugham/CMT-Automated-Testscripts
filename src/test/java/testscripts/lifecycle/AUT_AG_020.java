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
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_020 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_020()
	{
		testParameters.setCurrentTestDescription("Verify user is able to revert to previous as minor version of a file that is followed along with lifecycle (Print UK Schools) attached to it");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		docLibPg.clickOnFollowOption(fileName);
		
		docLibPgTest.verifyUnfollowLinkForFile(fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		
		sitePgTestObj.verifyAttachLifecycleForFileOrFolder(fileName);
	
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
	
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
	
		String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
		docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
	
		myFiles.openUploadedOrCreatedFile(fileName,"");
	
		docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
	
		String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
		docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
	
		docDetailsPg.backToFolderOrDocumentPage("");
	
		sitesPage.clickOnMoreSetting(fileName);
	
		String changedLifecycleStateActions = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
	
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		if(changedLifecycleStateActions.contains(","))
		{
			String splittedActions[] = changedLifecycleStateActions.split(",");
			for(String actionName:splittedActions)
			{
				docLibPageTest.verifyBrowseAction(actionName, fileName);
			}
		}
		else
		{
			docLibPageTest.verifyBrowseAction(changedLifecycleStateActions, fileName);
		}
		
		String lifecycleAction = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, lifecycleAction);
		
		String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Sites", "LifecyclePromoteStateDropdownValue");
		docDetailsPg.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue);
		
		myFiles.openUploadedOrCreatedFile(fileName,"");
		
		docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
		
		String changedLifecyclePropValuesForPromote = dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForPromote");
		docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForPromote,"LifeCycle");
		
		docDetailsPg.backToFolderOrDocumentPage("");
		
		sitesPage.clickOnMoreSetting(fileName);
		
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption);
	
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption);
		
		String fileNameUploadNewVersion = dataTable.getData("Document_Details", "FileName");
		String filePathUploadNewVersion = dataTable.getData("Document_Details", "FilePath");
		String versionDetails = dataTable.getData("Document_Details",
				"Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		
		docLibPg.uploadNewVersionFileInDocLibPage(fileNameUploadNewVersion,
				filePathUploadNewVersion, versionDetails, comments);
		
		myFiles.openUploadedOrCreatedFile(fileName, "");
		
		docDetailsPageTest.verifyVersionHistoryHeader();
		
		String expectedOldVersionNoForFile = dataTable.getData("MyFiles", "Version");
		docDetailsPageTest.verifyOlderVersionForFile(expectedOldVersionNoForFile.replace("'", ""));
		
		docDetailsPg.performReverVersionForFile();
	
		String expectedRevertedVersionNoForFile = dataTable.getData("Document_Details", "RevertedFileVersionNo");
		docDetailsPageTest.verifyRevertedVersionNoForFile(expectedRevertedVersionNoForFile);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}