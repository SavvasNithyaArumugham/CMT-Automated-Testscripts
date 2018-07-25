package testscripts.misc4;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_375 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_023()
	{
		testParameters.setCurrentTestDescription("To perform Advance search for "
				+ "1. Lifecycle Name  for a file"
				+ "2. Lifecycle Name for a folder"
				+ "3. Lifecycle state for a file"
				+ "4. Lifecycle state for Folder"
				+ "5. Lifecycle Name and State for a file"
				+ "6. Lifecycle Name and State for a folder"
				+ "7. File with Lifecycle state value contains 'Colon'"
				+ "8. Folder with Lifecycle state value contains 'Colon'");
		
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String aspect = dataTable.getData("Search", "Aspect");
		String aspectProp1 = dataTable.getData("Search", "AspectProp");
		String aspectvalue1 = dataTable.getData("Search", "Query");
		String aspectProp2 = dataTable.getData("Search", "AspectProp1");
		String aspectvalue2 = dataTable.getData("Search", "FullText");
		String aspectvalue3 = dataTable.getData("Search", "Result");

		ArrayList<String> createdFileNames = myFilesTestObj
				.getCreatedFileNames(fileDetails);

		for (String fileName : createdFileNames) {
			myFiles.deleteUploadedFile(fileName);

			myFiles.createFile(fileDetails);
			
			docDetailsPageTest.verifyCreatedFile(fileName, "");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
		
			sitesPage.clickOnMoreSetting(fileName);
		
			sitePgTestObj.verifyAttachLifecycleForFileOrFolder(fileName);
		
			String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
			AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);

			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
			myFiles.openUploadedOrCreatedFile(fileName,"");
		
			docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
		
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			homePageObj.navigateToAdvSearch();
			appSearchPgTest.verifyLookForFieldValueInAdvancedSearchPg();
			appSearchPg.clickShowMore();
			
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp1, aspectvalue1);
			appSearchPg.clickSearch();
			appSearchPg.sortByModifiedDate();
			appSearchPgTest.commonMethodForVerifySearchResults(fileName);
			
			homePageObj.navigateToAdvSearch();
			appSearchPgTest.verifyLookForFieldValueInAdvancedSearchPg();
			appSearchPg.clickShowMore();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp2, aspectvalue2);
			appSearchPg.clickSearch();
			appSearchPg.sortByModifiedDate();
			appSearchPgTest.commonMethodForVerifySearchResults(fileName);
			
			homePageObj.navigateToAdvSearch();
			appSearchPg.clickShowMore();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp1, aspectvalue1);
			appSearchPg.clickAddAspectBtn();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp2, aspectvalue2);
			appSearchPg.clickSearch();
			appSearchPg.sortByModifiedDate();
			appSearchPgTest.commonMethodForVerifySearchResults(fileName);
			
			homePage.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();
			
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
			
			homePageObj.navigateToAdvSearch();
			appSearchPg.clickShowMore();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp1, aspectvalue1);
			appSearchPg.clickAddAspectBtn();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp2, aspectvalue3);
			appSearchPg.clickSearch();
			appSearchPg.sortByModifiedDate();
			appSearchPgTest.commonMethodForVerifySearchResults(fileName);
		}
		
		homePage.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		myFiles.createFolder(folderDetails);

		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
		
			sitesPage.clickOnMoreSetting(folderName);
		
			sitePgTestObj.verifyAttachLifecycleForFileOrFolder(folderName);
		
			String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
			AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);

			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, docActionVal);
		
			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
			
			homePageObj.navigateToAdvSearch();
			appSearchPg.selectLookForFieldOption("Folders");
			appSearchPg.clickShowMore();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp1, aspectvalue1);
			appSearchPg.clickSearch();
			appSearchPg.sortByModifiedDate();
			appSearchPgTest.commonMethodForVerifyFolderInSearchResults(folderName);
			
			homePageObj.navigateToAdvSearch();
			appSearchPg.selectLookForFieldOption("Folders");
			appSearchPg.clickShowMore();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp2, aspectvalue2);
			appSearchPg.clickSearch();
			appSearchPg.sortByModifiedDate();
			appSearchPgTest.commonMethodForVerifyFolderInSearchResults(folderName);
			
			homePageObj.navigateToAdvSearch();
			appSearchPg.selectLookForFieldOption("Folders");
			appSearchPg.clickShowMore();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp1, aspectvalue1);
			appSearchPg.clickAddAspectBtn();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp2, aspectvalue2);
			appSearchPg.clickSearch();
			appSearchPg.sortByModifiedDate();
			appSearchPgTest.commonMethodForVerifyFolderInSearchResults(folderName);
			
			homePage.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();
			
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
			
			myFiles.openUploadedOrCreatedFile(folderName,"");
			
			docDetailsPageTest.verifyUploadedFileIsOpened(folderName,"");
			
			String changedLifecyclePropValuesForPromote = dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForPromote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForPromote,"LifeCycle");
			
			homePageObj.navigateToAdvSearch();
			appSearchPg.selectLookForFieldOption("Folders");
			appSearchPg.clickShowMore();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp1, aspectvalue1);
			appSearchPg.clickAddAspectBtn();
			appSearchPg.inputAspectAdvSearch(aspect, aspectProp2, aspectvalue3);
			appSearchPg.clickSearch();
			appSearchPg.sortByModifiedDate();
			appSearchPgTest.commonMethodForVerifySearchResults(folderName);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}