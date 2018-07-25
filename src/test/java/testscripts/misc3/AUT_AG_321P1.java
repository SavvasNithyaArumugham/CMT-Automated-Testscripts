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
public class AUT_AG_321P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription("1.Verify a French translation for Global Print type Lifecycle:"
				+"<br>2.Verify a French translation for lifecycle(Document Details) options for folders via document preview page once a lifecycle is applied for folders."
				+ "Verify French translation for lifecycle(Folder Actions) options for folders via More once a lifecycle is applied for folders"
				+ "Verify French translation for lifecycle fields in Edit Properties_All properties screen once a lifecycle is applied for folders OR files");
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
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			String folderName = dataTable.getData("MyFiles", "Version");
			String metaLifeCycle=dataTable.getData("Document_Details","Title");
			String metaLifeCycleState=dataTable.getData("Document_Details","Comments");

			String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");

			String actualAttachLifeCycMessage;
			String expectedAttachLifeCycMessage = dataTable.getData("MyFiles", "PopUpMsg");
			String actualPromoteLifeMessage;
			String expectedPromoteLifeMessage = dataTable.getData("MyFiles", "AccessToken");
			String actualDemoteLifeCycMessage = null;
			String expectedDemotLifeMessage = dataTable.getData("MyFiles", "Sort Options");
			String SuccDetachMessage = dataTable.getData("MyFiles", "StatusReportValue");

			String expNotPromtStateMessage = dataTable.getData("MyFiles", "RelationshipName");
			String expNotDemoteStateMessage = dataTable.getData("MyFiles", "BrowseActionName");
			
			
			String lableText = dataTable.getData("Sites", "CreateSiteLabelNames");
			String promtLabelText[] = dataTable.getData("MyFiles", "CreateFileFieldName").split(",");
			String demtLabelText[] = dataTable.getData("MyFiles", "CreateChildFolder").split(",");
			String buttonText = dataTable.getData("MyFiles", "TagName");
			String promtButtonText = dataTable.getData("MyFiles", "EditTagName");
			String demoteButtonText = dataTable.getData("MyFiles", "ContentCategoryItem");

			ArrayList<String> lifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> actuallifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> promtlifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> actualpromtlifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> demotelifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> actualdemotelifeCycleTextItems = new ArrayList<String>();

			homePageObj.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();

			myFiles.createFolder(folderDetails);

			sitesPage.clickOnMoreSetting(folderName);
			sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(folderName, moreSettingsOptionName);

			sitesPage.commonMethodForPerformBrowseOption(folderName, moreSettingsOptionName);
			//attach lifecycle
			lifeCycleTextItems = myFilesTest.getUploadedFileDetails(lableText);
			actuallifeCycleTextItems = sitesPage.getLifeCycleLabelText();
			if (UIHelper.compareTwoSimilarLists(actuallifeCycleTextItems, lifeCycleTextItems)) {
				report.updateTestLog("Verify Attach Lifecycle Label Names are present in French Language for folders",
						"Attach Lifecycle Label Names are displayed in French for folders.<b>" + actuallifeCycleTextItems + "", Status.PASS);

			} else {
				report.updateTestLog("Verify Attach Lifecycle Label Names are present in French Language for folders",
						"Attach Lifecycle Label Names are not displayed in French for folders.<b>" + actuallifeCycleTextItems + "", Status.FAIL);
			}
			ArrayList<String> buttonLifecycle = new ArrayList<String>();
			ArrayList<String> actuallifeCycleButtonItems = new ArrayList<String>();
			actuallifeCycleButtonItems = sitesPage.getButtonNameInLifeCycle();
			buttonLifecycle = myFilesTest.getUploadedFileDetails(buttonText);
			if (UIHelper.compareTwoDiffSizeOfLists(actuallifeCycleButtonItems, buttonLifecycle)) {
				report.updateTestLog("Verify Attach Lifecycle Button Names are present in French Language for folders",
						"Attach Lifecycle Button Names are displayed in French for folders.<b>" + actuallifeCycleButtonItems + "", Status.PASS);
			} else {
				report.updateTestLog("Verify Attach Lifecycle Button Names are present in French Language for folders",
						"Attach Lifecycle Button Names are not displayed in French for folders.<b>" + actuallifeCycleButtonItems + "", Status.FAIL);
			}
			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			actualAttachLifeCycMessage = docDetailsPage.changeLifeCycleSateWithMessage(attachLifeCycleDropdownVal);
			docLibPageTest.verifyDialogConfirmationMessage(expectedAttachLifeCycMessage, actualAttachLifeCycMessage);
			docLibPageTest.verifyLifecycleTags(propertyValues, "LifeCycle", folderName);
			
			
			sitesPage.clickOnViewDetails(folderName);
			String changedLifecycleStateDocActions = dataTable.getData("Document_Details",
					"ChangedLifecycleStateActionNames");

			if (changedLifecycleStateDocActions.contains(",")) {
				String splittedDocActions[] = changedLifecycleStateDocActions.split(",");
				for (String docActionName : splittedDocActions) {
					docDetailsPageTest.verifyDocAction(docActionName);
				}
			}
			
			docDetailsPage.backToFolderOrDocumentPage("");
			sitesPage.clickOnMoreSetting(folderName);
			String changedLifecycleStateDocActions1[] = dataTable
					.getData("Document_Details", "ChangedLifecycleStateActionNames").split(",");
			for (String lifeCycleAction : changedLifecycleStateDocActions1) {
				sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(folderName, lifeCycleAction);
				UIHelper.waitFor(driver);
			}
			
			//promote Lifecycle
			String lifecycleActionForPromote = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
			sitesPage.commonMethodForPerformBrowseOption(folderName, lifecycleActionForPromote);
			
			for (String prmtLabels : promtLabelText) {
				promtlifeCycleTextItems.add(prmtLabels);
			}
			actualpromtlifeCycleTextItems = sitesPage.getPromteLifeCycleLabelText();
			if (UIHelper.compareTwoSimilarLists(actualpromtlifeCycleTextItems, promtlifeCycleTextItems)) {
				report.updateTestLog("Verify Promote cycle Label Names are present in French Language for folders",
						"Promote cycle Label Names are displayed in French for folders.<b>" + actualpromtlifeCycleTextItems + "", Status.PASS);

			} else {
				report.updateTestLog("Verify Promote cycle Label Names are present in French Language for folders",
						"Promote cycle Label Names are not displayed in French for folders.<b>" + actualpromtlifeCycleTextItems + "",
						Status.FAIL);
			}
			ArrayList<String> buttonpromtLifecycle = new ArrayList<String>();
			ArrayList<String> actualpromtlifeCycleButtonItems = new ArrayList<String>();
			actualpromtlifeCycleButtonItems = sitesPage.getPromteButtonNameInLifeCycle();
			buttonpromtLifecycle = myFilesTest.getUploadedFileDetails(promtButtonText);
			if (UIHelper.compareTwoDiffSizeOfLists(actualpromtlifeCycleButtonItems, buttonpromtLifecycle)) {
				report.updateTestLog("Verify Promote cycle Button Names are present in French Language for folders",
						"Promote cycle Button Names are displayed in French for folders.<b>" + actualpromtlifeCycleButtonItems + "", Status.PASS);
			} else {
				report.updateTestLog("Verify Promote cycle Button Names are present in French Language for folders",
						"Promote cycle Button Names are not displayed in French for folders.<b>" + actualpromtlifeCycleButtonItems + "",
						Status.FAIL);
			}
			

			//sitesPage.clickOnMoreSetting(folderName);
			
			String promtLifecycleStateItems = dataTable.getData("Document_Details",
					"LifecyclePromoteStateDropdownValue");
			docLibPageTest.getPromoteLifeCycleDropDown(promtLifecycleStateItems, "French");

			UIHelper.pageRefresh(driver);

			String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Sites",
					"LifecyclePromoteStateDropdownValue");
			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.commonMethodForPerformBrowseOption(folderName, lifecycleActionForPromote);
			actualPromoteLifeMessage = docDetailsPage
					.changeLifeCycleSateWithMessage(promotOrDemoteOrDetachStateDropdownValue);
			docLibPageTest.verifyDialogConfirmationMessage(expectedPromoteLifeMessage, actualPromoteLifeMessage);

			sitesPage.clickOnViewDetails(folderName);

			String changedpromoteLifecyclePropValuesForPromote = dataTable.getData("Document_Details",
					"ChangedLifecyclePropValuesForPromote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedpromoteLifecyclePropValuesForPromote,
					"PromoteLifeCycle");

			docDetailsPage.backToFolderOrDocumentPage("");

		//issue persists
			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.commonMethodForPerformBrowseOption(folderName, lifecycleActionForPromote);
			docLibPageTest.verifyConfirmationDailogMessage(expNotPromtStateMessage);

			sitesPage.clickOnEditProperties(folderName);
			docDetailsPage.clickAllProperties();

			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			String fieldValue = dataTable.getData("Document_Details", "DocActionName");
			docDetailsPageTest.verifyFieldsInEditProperties(docProperties);
			docDetailsPageTest.verifyFieldValuesInEditPropPage(fieldValue);

			sitesPage.enterIntoDocumentLibrary();
			
			//Demote Lifecycle
			String lifecycleActionForDemote = dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
			String demoteStateDropdownValue = dataTable.getData("Sites", "LifecycleDemoteStateDropdownValue");
			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.commonMethodForPerformBrowseOption(folderName, lifecycleActionForDemote);

			for (String demoteLabels : demtLabelText) {
				demotelifeCycleTextItems.add(demoteLabels);
			}
			actualdemotelifeCycleTextItems = sitesPage.getDemteLifeCycleLabelText();
			if (UIHelper.compareTwoSimilarLists(actualdemotelifeCycleTextItems, demotelifeCycleTextItems)) {
				report.updateTestLog("Verify Demote cycle Label Names are present in French Language for folders",
						"Demote cycle Label Names are displayed in French for folders.<b>" + actualdemotelifeCycleTextItems + "", Status.PASS);

			} else {
				report.updateTestLog("Verify Demote cycle Label Names are present in French Language for folders",
						"Demote cycle Label Names are not displayed in French for folders.<b>" + actualdemotelifeCycleTextItems + "",
						Status.FAIL);
			}
			ArrayList<String> buttonDemoteLifecycle = new ArrayList<String>();
			ArrayList<String> actualDemotelifeCycleButtonItems = new ArrayList<String>();
			actualDemotelifeCycleButtonItems = sitesPage.getDemoteButtonNameInLifeCycle();
			buttonDemoteLifecycle = myFilesTest.getUploadedFileDetails(demoteButtonText);
			if (UIHelper.compareTwoDiffSizeOfLists(actualDemotelifeCycleButtonItems, buttonDemoteLifecycle)) {
				report.updateTestLog("Verify Demote cycle Button Names are present in French Language for folders",
						"Demote cycle Button Names are displayed in French for folders.<b>" + actualDemotelifeCycleButtonItems + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Demote cycle Button Names are present in French Language for folders",
						"Demote cycle Button Names are not displayed in French for folders.<b>" + actualDemotelifeCycleButtonItems + "",
						Status.FAIL);
			}

			UIHelper.pageRefresh(driver);

			if (demoteStateDropdownValue.contains(",")) {
				String splittedDemoteDropdownVals[] = demoteStateDropdownValue.split(",");
				for (String demoteDropVal : splittedDemoteDropdownVals) {

					sitesPage.clickOnMoreSetting(folderName);
					sitesPage.commonMethodForPerformBrowseOption(folderName, lifecycleActionForDemote);
					actualDemoteLifeCycMessage = docDetailsPage.changeLifeCycleSateWithMessage(demoteDropVal);

				}
				docLibPageTest.verifyDialogConfirmationMessage(expectedDemotLifeMessage, actualDemoteLifeCycMessage);
			} else {
				sitesPage.commonMethodForPerformBrowseOption(folderName, lifecycleActionForDemote);
				docDetailsPage.changeLifeCycleSateWithMessage(demoteStateDropdownValue);
			}

			sitesPage.clickOnViewDetails(folderName);
			String changedLifecyclePropValuesForDemote = dataTable.getData("Document_Details",
					"ChangedLifecyclePropValuesForDemote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForDemote, "DemoteLifeCycle");
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

			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.commonMethodForPerformBrowseOption(folderName, lifecycleActionForDemote);
			docLibPageTest.verifyConfirmationDailogMessage(expNotDemoteStateMessage);

			String lifecycleActionForDetach = dataTable.getData("Document_Details", "LifecycleActionNameForDetach");
			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.commonMethodForPerformBrowseOption(folderName, lifecycleActionForDetach);
			docLibPageTest.verifyConfirmationDailogMessage(SuccDetachMessage);
			sitesPage.clickOnViewDetails(folderName);
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