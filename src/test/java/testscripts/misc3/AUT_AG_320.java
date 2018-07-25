package testscripts.misc3;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_320 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription("Verify a French translation for Global Media type Lifecycle:");
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
		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);

			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoSearchPage searchPageobj= new AlfrescoSearchPage(scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoSitesPageTest sitesPgTest = new AlfrescoSitesPageTest(scriptHelper);
			AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoMyFilesPageTest myFilesTest = new AlfrescoMyFilesPageTest(scriptHelper);
			AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);

			String sourceSiteName = dataTable.getData("Sites", "SiteName");
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String metaLifeCycle=dataTable.getData("Document_Details","Title");
			String metaLifeCycleState=dataTable.getData("Document_Details","Comments");

			String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
			
			String actualAttachLifeCycMessage;
			String expectedAttachLifeCycMessage = dataTable.getData("MyFiles", "PopUpMsg");
			String actualPromoteLifeMessage=null;
			String expectedPromoteLifeMessage = dataTable.getData("MyFiles", "AccessToken");
			String actualDemoteLifeCycMessage = null;
			String expectedDemotLifeMessage = dataTable.getData("MyFiles", "Sort Options");
			String SuccDetachMessage = dataTable.getData("MyFiles", "StatusReportValue");

			
			
			

			homePageObj.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();

			myFiles.uploadFile(filePath, fileName);
			sitesPage.clickOnMoreSetting(fileName);
			sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOptionName);

			sitesPage.commonMethodForPerformBrowseOption(fileName, moreSettingsOptionName);

			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			actualAttachLifeCycMessage=docDetailsPage.changeLifeCycleSateWithMessage(attachLifeCycleDropdownVal);
			docLibPageTest.verifyDialogConfirmationMessage(expectedAttachLifeCycMessage, actualAttachLifeCycMessage);
			docLibPageTest.verifyLifecycleTags(propertyValues, "LifeCycle", fileName);

			sitesPage.clickOnMoreSetting(fileName);
			String lifecycleActionForPromote = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
			sitesPage.commonMethodForPerformBrowseOption(fileName, lifecycleActionForPromote);
			String promtLifecycleStateItems = dataTable.getData("Document_Details",
					"LifecyclePromoteStateDropdownValue");
			docLibPageTest.getPromoteLifeCycleDropDown(promtLifecycleStateItems, "French");

			UIHelper.pageRefresh(driver);

			String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Document_Details",
					"LifecyclePromoteStateDropdownValue");
			if (promotOrDemoteOrDetachStateDropdownValue.contains(",")) {
				String splittedPromoteDropdownVals[] = promotOrDemoteOrDetachStateDropdownValue.split(",");
				for (String promoteDropVal : splittedPromoteDropdownVals) {
					if (promoteDropVal.equals("Deuxième révision")) {
						break;
					} else {
						sitesPage.clickOnMoreSetting(fileName);
						sitesPage.commonMethodForPerformBrowseOption(fileName, lifecycleActionForPromote);
						actualPromoteLifeMessage=docDetailsPage.changeLifeCycleSateWithMessage(promoteDropVal);
					}
				}
				docLibPageTest.verifyDialogConfirmationMessage(expectedPromoteLifeMessage, actualPromoteLifeMessage);
			} else {
				sitesPage.commonMethodForPerformBrowseOption(fileName, lifecycleActionForPromote);
				docDetailsPage.changeLifeCycleSateWithMessage(promotOrDemoteOrDetachStateDropdownValue);
			}

			myFiles.openUploadedOrCreatedFile(fileName, "");
			String changedpromoteLifecyclePropValuesForPromote = dataTable.getData("Document_Details",
					"ChangedLifecyclePropValuesForPromote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedpromoteLifecyclePropValuesForPromote,
					"PromoteLifeCycle");
			String expLifeCycleMetaData;
			String expLifeCycleStateMetaData;
			expLifeCycleMetaData=searchPageobj.getMetadata(metaLifeCycle);
			expLifeCycleStateMetaData=searchPageobj.getMetadata(metaLifeCycleState);
			if( (!expLifeCycleMetaData.isEmpty()) && (!expLifeCycleStateMetaData.isEmpty()) )
			{
				report.updateTestLog("Verify LifeCycleName and State in Doc Details are displayed in French", "LifeCycleName and State in Doc Details are successfully displayed in French", Status.PASS);
			}
			else
			{
				report.updateTestLog("Verify LifeCycleName and State in Doc Details are displayed in French", "LifeCycleName and State in Doc Details are failed to displayed in French" ,Status.FAIL);
			}

			docDetailsPage.backToFolderOrDocumentPage("");

			sitesPage.clickOnMoreSetting(fileName);

			String lifecycleActionForDemote = dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
			sitesPage.commonMethodForPerformBrowseOption(fileName, lifecycleActionForDemote);

			String demoteStateDropdownValue = dataTable.getData("Sites", "LifecycleDemoteStateDropdownValue");
			actualDemoteLifeCycMessage=docDetailsPage.changeLifeCycleSateWithMessage(demoteStateDropdownValue);
			docLibPageTest.verifyDialogConfirmationMessage(expectedDemotLifeMessage, actualDemoteLifeCycMessage);

			myFiles.openUploadedOrCreatedFile(fileName, "");

			docDetailsPageTest.verifyUploadedFileIsOpened(fileName, "");

			String changedLifecyclePropValuesForDemote = dataTable.getData("Document_Details",
					"ChangedLifecyclePropValuesForDemote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForDemote, "DemoteLifeCycle");
			
			
			docDetailsPage.backToFolderOrDocumentPage("");

			sitesPage.clickOnEditProperties(fileName);
			docDetailsPage.clickAllProperties();

			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			String fieldValue = dataTable.getData("Document_Details", "DocActionName");
			docDetailsPageTest.verifyFieldsInEditProperties(docProperties);
			docDetailsPageTest.verifyFieldValuesInEditPropPage(fieldValue);

			sitesPage.enterIntoDocumentLibrary();

			String lifecycleActionForDetach = dataTable.getData("Document_Details", "LifecycleActionNameForDetach");
			sitesPage.clickOnMoreSetting(fileName);
			sitesPage.commonMethodForPerformBrowseOption(fileName, lifecycleActionForDetach);
			docLibPageTest.verifyConfirmationDailogMessage(SuccDetachMessage);
			myFiles.openUploadedOrCreatedFile(fileName, "");
			// String changedLifecyclePropValuesForDemote =
			// dataTable.getData("Document_Details",
			// "ChangedLifecyclePropValuesForDemote");
			docDetailsPageTest.verifyAttributesInPropertySecForNegativeCase(changedLifecyclePropValuesForDemote,
					"DetachLifecycle");

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}