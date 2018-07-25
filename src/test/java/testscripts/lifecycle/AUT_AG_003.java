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
public class AUT_AG_003 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_003()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is able to see 'Attach Lifecyle' Option by hovering mouse on any file"
				+ "<br>2. Verify that the user is able to 'Attach lifecycle State' for hovering mouse on the file"
				+ "<br>3. Verify that user is not able to attach lifecycle state for already lifecycle attached files"
				+ "<br>4. Verify that user is able to see Options like 'PromoteLifecycle, Demote Lifecycle and Detach Lifecycle' of any file after Attaching Lifecycle State from Browse Actions"
				+ "<br>5. Verify that user is able to Promote lifecycle State for any file from Browse Actions Menu"
				+ "<br>6. Verify that user is able to demote lifecycle State of any file from Browse Actions"
				+ "<br>7. Verify that user is not able to demote lifecycle state when file is in first lifecycle stage");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		ArrayList<String> createdFileNames = myFilesTestObj
				.getCreatedFileNames(fileDetails);

		for (String fileName : createdFileNames) {
			myFiles.deleteUploadedFile(fileName);

			myFiles.createFile(fileDetails);
			
			docDetailsPageTest.verifyCreatedFile(fileName, "");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
		
			sitesPage.clickOnMoreSetting(fileName);
		
			AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
			sitePgTestObj.verifyAttachLifecycleForFileOrFolder(fileName);
		
			String docActionVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		
			AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);

			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
			String changedLifecycleStateActions = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
			sitesPage.clickOnMoreSetting(fileName);
		
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
			
			String lifecycleActionForDemote = dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, lifecycleActionForDemote);
			
			String expectedMessageVal = dataTable.getData("Sites", "ExpectedConfirmationMessage");
			docLibPgTest.verifyConfirmationDailogMessage(expectedMessageVal);
			
			myFiles.openUploadedOrCreatedFile(fileName,"");
			
			docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
		
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			sitesPage.clickOnMoreSetting(fileName);
			
			docLibPageTest.verifyAttachLifecycleStateForLifecycleAttachFiles(fileName, docActionVal);
			
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
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, lifecycleActionForDemote);
			
			String demoteStateDropdownValue = dataTable.getData("Sites", "LifecycleDemoteStateDropdownValue");
			docDetailsPg.changeLifeCycleSate(demoteStateDropdownValue);
			
			myFiles.openUploadedOrCreatedFile(fileName,"");
			
			docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
			
			String changedLifecyclePropValuesForDemote = dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForDemote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForDemote,"LifeCycle");
			
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}