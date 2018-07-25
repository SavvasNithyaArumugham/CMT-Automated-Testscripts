package testscripts.proofhq;

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
 * 
 * @author Cognizant
 */
public class AUT_AG_039 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void proofhq_23() {
		testParameters.setCurrentTestDescription(
				"Verifying the alfresco 'Attach lifecycle' feature for the proof created file");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		myFiles.uploadFile(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName, "");
		
		String moreSettingsOptions = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String moreSettingsOption1="",moreSettingsOption2="";
		if(moreSettingsOptions.contains(","))
		{
			String splittedMoreSettingOptions[] = moreSettingsOptions.split(",");
			if(splittedMoreSettingOptions!=null && splittedMoreSettingOptions.length>1)
			{
				moreSettingsOption1=splittedMoreSettingOptions[0];
				moreSettingsOption2=splittedMoreSettingOptions[1];
			}
			else
			{
				moreSettingsOption1="Create Proof in ProofHQ";
				moreSettingsOption2="Attach Lifecycle";
			}
		}
		else
		{
			moreSettingsOption1="Create Proof in ProofHQ";
			moreSettingsOption2="Attach Lifecycle";
		}
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption1);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption1);
		
		String recepients=  dataTable.getData("MyFiles", "Recepients");
		String policy=  dataTable.getData("MyFiles", "ProofHQPolicy");
		sitesPage.addProofHQ(recepients, policy, fileName);

		sitesPage.clickOnMoreSetting(fileName);

		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		sitePgTestObj.verifyAttachLifecycleForFileOrFolder(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption2);

		String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);

		String changedLifecycleStateActions = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");

		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);

		sitesPage.clickOnMoreSetting(fileName);

		if (changedLifecycleStateActions.contains(",")) {
			String splittedActions[] = changedLifecycleStateActions.split(",");
			for (String actionName : splittedActions) {
				docLibPageTest.verifyBrowseAction(actionName, fileName);
			}
		} else {
			docLibPageTest.verifyBrowseAction(changedLifecycleStateActions, fileName);
		}

		String lifecycleActionForDemote = dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, lifecycleActionForDemote);

		String expectedMessageVal = dataTable.getData("Sites", "ExpectedConfirmationMessage");
		docLibPgTest.verifyConfirmationDailogMessage(expectedMessageVal);

		myFiles.openUploadedOrCreatedFile(fileName, "");

		docDetailsPageTest.verifyUploadedFileIsOpened(fileName, "");

		String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
		docDetailsPageTest.verifyAttributesInPropertySec(propertyValues, "LifeCycle");

		docDetailsPageObj.backToFolderOrDocumentPage("");

		sitesPage.clickOnMoreSetting(fileName);
		docLibPageTest.verifyAttachLifecycleStateForLifecycleAttachFiles(fileName, moreSettingsOption2);

		String lifecycleAction = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, lifecycleAction);

		String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Sites",
				"LifecyclePromoteStateDropdownValue");
		docDetailsPg.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue);

		myFiles.openUploadedOrCreatedFile(fileName, "");

		docDetailsPageTest.verifyUploadedFileIsOpened(fileName, "");

		String changedLifecyclePropValuesForPromote = dataTable.getData("Document_Details",
				"ChangedLifecyclePropValuesForPromote");
		docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForPromote, "LifeCycle");

		docDetailsPg.backToFolderOrDocumentPage("");

		sitesPage.clickOnMoreSetting(fileName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, lifecycleActionForDemote);

		String demoteStateDropdownValue = dataTable.getData("Sites", "LifecycleDemoteStateDropdownValue");
		docDetailsPg.changeLifeCycleSate(demoteStateDropdownValue);

		myFiles.openUploadedOrCreatedFile(fileName, "");

		docDetailsPageTest.verifyUploadedFileIsOpened(fileName, "");

		String changedLifecyclePropValuesForDemote = dataTable.getData("Document_Details",
				"ChangedLifecyclePropValuesForDemote");
		docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForDemote, "LifeCycle");
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}