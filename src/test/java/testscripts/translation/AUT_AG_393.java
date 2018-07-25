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
public class AUT_AG_393 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void translation_3465_11()
	{
		
		
		testParameters.setCurrentTestDescription("ALFDEPLOY-3465-Verify all fields_messages_Help description_in "
				+ "Edit Sharing Properties window are translated to German");
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
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(openfolderName);
		docLibPge.commonMethodForClickOnMoreSettingsOption(openfolderName, action);
		shareboxPageTest.verifyFieldLabelTextInShareboxPopup(fieldsName);
	
			
	}
	
	
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
