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
public class AUT_AG_323 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription("1.Verify a French translation for Shared files content by performing attach, promote, demote and detach");
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
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoSitesPageTest sitesPgTest = new AlfrescoSitesPageTest(scriptHelper);
			
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoMyFilesPageTest myFilesTest = new AlfrescoMyFilesPageTest(scriptHelper);
			AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
			AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);

			//String sourceSiteName = dataTable.getData("Sites", "SiteName");
			String lifeCycleDropdownName = dataTable.getData("Sites", "SelectedItemsMenuOption");
			String lableText = dataTable.getData("Sites", "CreateSiteLabelNames");
			String promtLabelText[] = dataTable.getData("MyFiles", "CreateFileFieldName").split(",");
			String demtLabelText[] = dataTable.getData("MyFiles", "CreateChildFolder").split(",");
			String buttonText = dataTable.getData("MyFiles", "RelationshipName");
			String promtButtonText = dataTable.getData("MyFiles", "BrowseActionName");
			String demoteButtonText = dataTable.getData("MyFiles", "Sort Options");
			String myFilesText=dataTable.getData("MyFiles","Version");

			ArrayList<String> lifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> actuallifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> promtlifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> actualpromtlifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> demotelifeCycleTextItems = new ArrayList<String>();
			ArrayList<String> actualdemotelifeCycleTextItems = new ArrayList<String>();

			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		
			
			String metaLifeCycle=dataTable.getData("Document_Details","Title");
			String metaLifeCycleState=dataTable.getData("Document_Details","Comments");

			String title = dataTable.getData("MyFiles", "CreateFileDetails");

			String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");

			homePageObj.navigateToSharedFilesTab();
		
			if(sitesPage.Checkdocument(title)){
				appSearchPg.deletedocument(title);
				}	
			
			myFiles.createFolder(folderDetails);
			
            sitesPage.clickOnMoreSetting(title);
			sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(title, moreSettingsOptionName);

			
			sitesPage.commonMethodForPerformBrowseOption(title, moreSettingsOptionName);
			lifeCycleTextItems = myFilesTest.getUploadedFileDetails(lableText);
			actuallifeCycleTextItems = sitesPage.getLifeCycleLabelText();
			if (UIHelper.compareTwoSimilarLists(actuallifeCycleTextItems, lifeCycleTextItems)) {
				report.updateTestLog("Verify Attach LifeCycle Label Names are present in French Language for files",
						"Label Names are displayed in French for files.<b>" + actuallifeCycleTextItems + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify Attach LifeCycle Label Names are present in French Language for files",
						"Label Names are not displayed in French for files.<b>" + actuallifeCycleTextItems + "",
						Status.FAIL);
			}
			ArrayList<String> buttonLifecycle = new ArrayList<String>();
			ArrayList<String> actuallifeCycleButtonItems = new ArrayList<String>();
			actuallifeCycleButtonItems = sitesPage.getButtonNameInLifeCycle();
			buttonLifecycle = myFilesTest.getUploadedFileDetails(buttonText);
			if (UIHelper.compareTwoDiffSizeOfLists(actuallifeCycleButtonItems, buttonLifecycle)) {
				report.updateTestLog("Verify Attach LifeCycle Button Names are present in French Language for files",
						"Button Names are displayed in French for files.<b>" + actuallifeCycleButtonItems + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Attach LifeCycle Button Names are present in French Language for files",
						"Button Names are not displayed in French for files.<b>" + actuallifeCycleButtonItems + "",
						Status.FAIL);
			}
			docLibPageTest.getLifeCycleDropDown(lifeCycleDropdownName, "French");

			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			docDetailsPage.changeLifeCycleSate(attachLifeCycleDropdownVal);
			docLibPageTest.verifyLifecycleTags(propertyValues, "LifeCycle", title);
		sitesPage.clickOnViewDetails(title);

			String changedLifecycleStateDocActions = dataTable.getData("Document_Details",
					"ChangedLifecycleStateActionNames");

			if (changedLifecycleStateDocActions.contains(",")) {
				String splittedDocActions[] = changedLifecycleStateDocActions.split(",");
				for (String docActionName : splittedDocActions) {
					docDetailsPageTest.verifyDocAction(docActionName);
				}
			}
			String propertyValues1 = dataTable.getData("Document_Details", "AspectName");

			docDetailsPageTest.verifyAttributesInPropertySec(propertyValues1, "AttachLifeCycle");
			docDetailsPage.backToFolderOrDocumentPage(myFilesText);
			sitesPage.clickOnMoreSetting(title);
			String changedLifecycleStateDocActions1[] = dataTable
					.getData("Document_Details", "ChangedLifecycleStateActionNames").split(",");
			for (String lifeCycleAction : changedLifecycleStateDocActions1) {
				sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(title, lifeCycleAction);
				UIHelper.waitFor(driver);
			}

			String lifecycleActionForPromote = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
			sitesPage.commonMethodForPerformBrowseOption(title, lifecycleActionForPromote);

			for (String prmtLabels : promtLabelText) {
				promtlifeCycleTextItems.add(prmtLabels);
			}
			actualpromtlifeCycleTextItems = sitesPage.getPromteLifeCycleLabelText();
			if (UIHelper.compareTwoSimilarLists(actualpromtlifeCycleTextItems, promtlifeCycleTextItems)) {
				report.updateTestLog("Verify Promote Cycle Label Names are present in French Language for files",
						"Label Names are displayed in French for files.<b>" + actualpromtlifeCycleTextItems + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify Promote Cycle Label Names are present in French Language for files",
						"Label Names are not displayed in French for files.<b>" + actualpromtlifeCycleTextItems + "",
						Status.FAIL);
			}
			ArrayList<String> buttonpromtLifecycle = new ArrayList<String>();
			ArrayList<String> actualpromtlifeCycleButtonItems = new ArrayList<String>();
			actualpromtlifeCycleButtonItems = sitesPage.getPromteButtonNameInLifeCycle();
			buttonpromtLifecycle = myFilesTest.getUploadedFileDetails(promtButtonText);
			if (UIHelper.compareTwoDiffSizeOfLists(actualpromtlifeCycleButtonItems, buttonpromtLifecycle)) {
				report.updateTestLog("Verify Promote Cycle Button Names are present in French Language for files",
						"Button Names are displayed in French for files.<b>" + actualpromtlifeCycleButtonItems + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Promote Cycle Button Names are present in French Language for files",
						"Button Names are not displayed in French for files.<b>" + actualpromtlifeCycleButtonItems + "",
						Status.FAIL);
			}
			
			String promoteLifeCycleDropdownVal = dataTable.getData("Sites", "LifecyclePromoteStateDropdownValue");
			docDetailsPage.changeLifeCycleSate(promoteLifeCycleDropdownVal);
			sitesPage.clickOnViewDetails(title);
			String changedpromoteLifecyclePropValuesForPromote = dataTable.getData("Document_Details",
					"ChangedLifecyclePropValuesForPromote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedpromoteLifecyclePropValuesForPromote,
					"PromoteLifeCycle");
			String expLifeCycleMetaData;
			String expLifeCycleStateMetaData;
			expLifeCycleMetaData=appSearchPg.getMetadata(metaLifeCycle);
			expLifeCycleStateMetaData=appSearchPg.getMetadata(metaLifeCycleState);
			if( (!expLifeCycleMetaData.isEmpty()) && (!expLifeCycleStateMetaData.isEmpty()) )
			{
				report.updateTestLog("Verify LifeCycleName and State in Doc Details are displayed in French", "LifeCycleName and State in Doc Details are successfully displayed in French", Status.PASS);
			}
			else
			{
				report.updateTestLog("Verify LifeCycleName and State in Doc Details are displayed in French", "LifeCycleName and State in Doc Details are failed to displayed in French" ,Status.FAIL);
			}
			docDetailsPage.backToFolderOrDocumentPage(myFilesText);

			sitesPage.clickOnMoreSetting(title);

			String lifecycleActionForDemote = dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
			sitesPage.commonMethodForPerformBrowseOption(title, lifecycleActionForDemote);

			String demoteStateDropdownValue = dataTable.getData("Sites", "LifecycleDemoteStateDropdownValue");
			docDetailsPage.changeLifeCycleSate(demoteStateDropdownValue);

			sitesPage.clickOnViewDetails(title);

			

			String changedLifecyclePropValuesForDemote = dataTable.getData("Document_Details",
					"ChangedLifecyclePropValuesForDemote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForDemote, "DemoteLifeCycle");
			docDetailsPage.backToFolderOrDocumentPage(myFilesText);

			sitesPage.clickOnEditProperties(title);
			docDetailsPage.clickAllProperties();

			String docProperties = dataTable.getData("Document_Details", "DocProperties");
			String fieldValue = dataTable.getData("Document_Details", "DocActionName");
			docDetailsPageTest.verifyFieldsInEditProperties(docProperties);
			docDetailsPageTest.verifyFieldValuesInEditPropPage(fieldValue);

			homePageObj.navigateToSharedFilesTab();	

			String lifecycleAction = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
			String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Document_Details",
					"LifecyclePromoteStateDropdownValue");
			if (promotOrDemoteOrDetachStateDropdownValue.contains(",")) {
				String splittedPromoteDropdownVals[] = promotOrDemoteOrDetachStateDropdownValue.split(",");
				for (String promoteDropVal : splittedPromoteDropdownVals) {
					sitesPage.clickOnMoreSetting(title);
					sitesPage.commonMethodForPerformBrowseOption(title, lifecycleAction);
					docDetailsPage.changeLifeCycleSate(promoteDropVal);
				}
			} else {
				sitesPage.commonMethodForPerformBrowseOption(title, lifecycleAction);
				docDetailsPage.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue);
			}
			sitesPage.clickOnMoreSetting(title);
			
			sitesPage.commonMethodForPerformBrowseOption(title, lifecycleActionForDemote);

			for (String demoteLabels : demtLabelText) {
				demotelifeCycleTextItems.add(demoteLabels);
			}
			actualdemotelifeCycleTextItems = sitesPage.getDemteLifeCycleLabelText();
			if (UIHelper.compareTwoSimilarLists(actualdemotelifeCycleTextItems, demotelifeCycleTextItems)) {
				report.updateTestLog("Verify Demote cycle Label Names are present in French Language for files",
						"Label Names are displayed in French for files.<b>" + actualdemotelifeCycleTextItems + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify Demote cycle Label Names are present in French Language for files",
						"Label Names are not displayed in French for files.<b>" + actualdemotelifeCycleTextItems + "",
						Status.FAIL);
			}
			ArrayList<String> buttonDemoteLifecycle = new ArrayList<String>();
			ArrayList<String> actualDemotelifeCycleButtonItems = new ArrayList<String>();
			actualDemotelifeCycleButtonItems = sitesPage.getDemoteButtonNameInLifeCycle();
			buttonDemoteLifecycle = myFilesTest.getUploadedFileDetails(demoteButtonText);
			if (UIHelper.compareTwoDiffSizeOfLists(actualDemotelifeCycleButtonItems, buttonDemoteLifecycle)) {
				report.updateTestLog("Verify Demote cycle Button Names are present in French Language for files",
						"Button Names are displayed in French for files.<b>" + actualDemotelifeCycleButtonItems + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Demote cycle Button Names are present in French Language for files",
						"Button Names are not displayed in French for files.<b>" + actualDemotelifeCycleButtonItems
								+ "",
						Status.FAIL);
			}

			String demotLifecycleStateItems = dataTable.getData("Sites", "ExpectedConfirmationMessage");
			docLibPageTest.getDemoteLifeCycleDropDown(demotLifecycleStateItems, "French");

			UIHelper.pageRefresh(driver);

			String lifecycleActionForDetach = dataTable.getData("Document_Details", "LifecycleActionNameForDetach");
			sitesPage.clickOnMoreSetting(title);
			sitesPage.commonMethodForPerformBrowseOption(title, lifecycleActionForDetach);
			
			sitesPage.clickOnViewDetails(title);
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