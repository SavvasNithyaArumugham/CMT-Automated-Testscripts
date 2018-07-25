package testscripts.translation;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_396 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void translation_3465_15()
	{
		
		
		testParameters.setCurrentTestDescription("Verify german translation of sharebox exclusion feature for a folder");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgeTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoShareboxPageTest shareboxPageTest = new AlfrescoShareboxPageTest(scriptHelper);
		
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteName);
		
		
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String openfolderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String fieldsName = dataTable.getData("Sharebox", "EditShareboxFieldsData");
		String browseOption = dataTable.getData("MyFiles", "Sort Options");
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(openfolderName);
		
		String excudeFromShareboxText = dataTable.getData("MyFiles", "RelationshipName");
		String removeShareboxExclusionText = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");

		String cancelButtonText = dataTable.getData("MyFiles", "AccessToken");
		String popupHeaderText = dataTable.getData("MyFiles", "PopUpMsg");
		String popupText = dataTable.getData("MyFiles", "StatusReportValue");
		
		String removeExclusionPopupText = dataTable.getData("MyFiles", "PopupText");
		
		String processMessage = dataTable.getData("Sites", "InviteUserName");
		String dynamicMessage = dataTable.getData("Sites", "Role");
		String message = dataTable.getData("Sites", "SiteToSelect");
		
		String removeExclusionProcessMessage = dataTable.getData("Sites", "ProcessMessage");
		String removeExclusionDynamicMessage = dataTable.getData("Sites", "DynamicMessage");
		
		docLibPge.verifyExcludeFromShareboxText(excudeFromShareboxText);
//		docLibPge.clickOnExcludeFromSharebox();
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.commonMethodForPerformBrowseOption(openfolderName, browseOption);
		docLibPge.verifyExcludeFromShareboxTextInFolderDetailsPage(excudeFromShareboxText);
		docLibPge.clickOnExcludeFromShareboxInFolderDetailsPage(excudeFromShareboxText);
		docLibPge.verifyExcludeShareboxPopupContents(cancelButtonText, popupHeaderText, popupText);
		docLibPge.clickOnCancelButtonInPopup(cancelButtonText);
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(openfolderName);
		docLibPge.clickOnExcludeFromSharebox(excudeFromShareboxText);
		docLibPge.clickOnOkButtonInPopup();
		
		docLibPgeTest.verifyLoadingMessage(processMessage);
		docLibPgeTest.verifyLoadingMessage(dynamicMessage);
		docLibPgeTest.verifyFolderMessage(message);
		
		sitesPage.clickOnMoreSetting(openfolderName);
		docLibPge.verifyExcludeFromShareboxTextNotPresent(excudeFromShareboxText);
		docLibPge.verifyRemoveShareboxExclusionText(removeShareboxExclusionText);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.commonMethodForPerformBrowseOption(openfolderName, browseOption);
		docLibPge.verifyRemoveShareboxExclusionTextInFolderDetailsPage(removeShareboxExclusionText);
		docLibPge.clickOnRemoveShareboxExclusionInFolderDetailsPage(removeShareboxExclusionText);
		
		docLibPge.verifyRemoveShareboxExclusionPopupContents(cancelButtonText, removeShareboxExclusionText, removeExclusionPopupText);
		docLibPge.clickOnCancelButtonInPopup(cancelButtonText);
		
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(openfolderName);
		docLibPge.clickOnRemoveShareboxExclusion(removeShareboxExclusionText);
		docLibPge.clickOnOkButtonInPopup();
		
//		docLibPgeTest.verifyLoadingMessage(removeExclusionProcessMessage);
		docLibPgeTest.verifyLoadingMessage(removeExclusionDynamicMessage);
		
	
			
	}
	
	
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
