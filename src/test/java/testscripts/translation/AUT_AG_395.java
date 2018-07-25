package testscripts.translation;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_395 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void translation_3465_12()
	{
		
		
		testParameters.setCurrentTestDescription("ALFDEPLOY-3465-Verify options to share, stop sharing, help descriptions"
				+ "in edit sharing properties window, notification dropdown ,Messages inside already shared folder"
				+ "(all details as mentioned in ShareBox tab of translation sheet) are translated to German");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoShareboxPageTest shareboxPageTest = new AlfrescoShareboxPageTest(scriptHelper);
		
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteName);
		
		String action = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String openfolderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String fieldsName = dataTable.getData("Sharebox", "EditShareboxFieldsData");
		
		String fieldLabelHelpText = dataTable.getData("Sharebox", "EditNotifications");
		String notificationOption = dataTable.getData("Sharebox", "EditNotify_Sharer");
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(openfolderName);
		
		String shareFolderExternallyText = dataTable.getData("MyFiles", "BrowseActionName");
		String excudeFromShareboxText = dataTable.getData("MyFiles", "RelationshipName");
		
		
		docLibPge.verifyShareFolderExternallyText(shareFolderExternallyText);
		docLibPge.verifyExcludeFromShareboxText(excudeFromShareboxText);
		
		docLibPge.commonMethodForClickOnMoreSettingsOption(openfolderName, action);
		shareboxPageTest.verifyFieldLabelTextInShareboxPopup(fieldsName);
		
		shareboxPageTest.verifyHelpTextInShareboxPopup(fieldLabelHelpText);
		shareboxPageTest.verifyNotificationOptions(notificationOption);
	
			
	}
	
	
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
