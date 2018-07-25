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
 * 
 * @author Cognizant
 */
public class AUT_AG_011 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LIFECYCLE_011() {
		testParameters
				.setCurrentTestDescription("1. Verify that user is able to see 'Attach Lifecyle' Option for any folder from Document Library Actions Menu"
						+ "<br>2. Verify that the user is able to 'Attach lifecycle State' for any folder from  Document Library Actions Menu"
						+ "<br>3. Verify that user is able to see Options like 'PromoteLifecycle ,Demote Lifecycle and Detach Lifecycle' of any folders after Attaching Lifecycle State from Document Library"
						+ "<br>4. Verify that user is able to  Promote lifecycle State for any folders from the Document Library Actions Menu"
						+ "<br>5. Verify that user is able to demote lifecycle State of any folder from Document Library Actions Menu"
						+ "<br>6. Verify that user is able is not able to demote lifecycle state when folder is in first lifecycle stage"
						+ "<br>7. Verify that user is able to detach lifecycle State at any stage that folder belongs to"
						+ "<br>8. Verify that user is able is not able to promote lifecycle state when file is in last lifecycle stage from Document Library Actions Menu");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);

		homePageObj.navigateToSitesTab();

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		myFiles.createFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			sitesPage.clickOnMoreSetting(folderName);

			sitePgTestObj.verifyAttachLifecycleForFileOrFolder(folderName);

			String docActionVal = dataTable.getData("Sites",
					"SelectedItemsMenuOption");

			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
					docActionVal);

			String attachLifeCycleDropdownVal = dataTable.getData("Sites",
					"LifeCycleDropdownValue");
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(
					scriptHelper);
			docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
			
			String browseOption = dataTable.getData("MyFiles", "BrowseActionName");
			sitesPage.commonMethodForPerformBrowseOption(folderName, browseOption);
		
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
			
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
			
			String lifecycleActionForDemote = dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, lifecycleActionForDemote);
			
			String expectedMessageValForDemote = dataTable.getData("Sites", "ExpectedConfirmationMessage");
			docLibPgTest.verifyConfirmationDailogMessage(expectedMessageValForDemote);
			
			String lifecycleAction = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, lifecycleAction);
			
			String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Sites", "LifecyclePromoteStateDropdownValue");
			docDetailsPg.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue);
			
			sitesPage.commonMethodForPerformBrowseOption(folderName, browseOption);
			
			String changedLifecyclePropValuesForPromote = dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForPromote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForPromote,"LifeCycle");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			sitesPage.clickOnMoreSetting(folderName);
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, lifecycleActionForDemote);
			
			String demoteStateDropdownValue = dataTable.getData("Sites", "LifecycleDemoteStateDropdownValue");
			docDetailsPg.changeLifeCycleSate(demoteStateDropdownValue);
			
			sitesPage.commonMethodForPerformBrowseOption(folderName, browseOption);
			
			String changedLifecyclePropValuesForDemote = dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForDemote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForDemote,"LifeCycle");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			sitesPage.clickOnMoreSetting(folderName);
			
			String lifecycleActionForDetach = dataTable.getData("Document_Details", "LifecycleActionNameForDetach");
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, lifecycleActionForDetach);
			
			String expectedMessageValForDetach = dataTable.getData("Document_Details", "ExpectedConfirmationMessage");
			docLibPgTest.verifyConfirmationDailogMessage(expectedMessageValForDetach);
			
			sitesPage.clickOnMoreSetting(folderName);
			sitePgTestObj.verifyAttachLifecycleForFileOrFolder(folderName);
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
					docActionVal);
			docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
			String promotStateDropdownValues = dataTable.getData("Document_Details", "LifecyclePromoteStateDropdownValue");
			
			if(promotStateDropdownValues.contains(","))
			{
				String splittedPromoteDropdownVals[] = promotStateDropdownValues.split(",");
				for(String promoteDropVal:splittedPromoteDropdownVals)
				{
					sitesPage.clickOnMoreSetting(folderName);
					docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
							lifecycleAction);
					docDetailsPg.changeLifeCycleSate(promoteDropVal);
				}
			}
			else
			{
				sitesPage.clickOnMoreSetting(folderName);
				docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
						lifecycleAction);
				docDetailsPg.changeLifeCycleSate(promotStateDropdownValues);
			}
			
			sitesPage.clickOnMoreSetting(folderName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
					lifecycleAction);
			
			String expectedMessageValForPromote = dataTable.getData("Sites", "FilePropertyName");
			docLibPgTest.verifyConfirmationDailogMessage(expectedMessageValForPromote);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}