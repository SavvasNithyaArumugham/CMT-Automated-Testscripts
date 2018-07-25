package testscripts.translation;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_398P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void translation_3465_10()
	{
		
		
		testParameters.setCurrentTestDescription("ALFDEPLOY-3465-Verify fields and basic messages and warnings in "
				+ "External ShareBox are translated to German - Part 1");
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
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteName);
		String fileName=dataTable.getData("MyFiles", "FileName");
		String filePath=dataTable.getData("MyFiles", "FilePath");
		String action = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String openfolderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String language = dataTable.getData("Sharebox", "Language");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderName);
		myFiles.openFolder(openfolderName);
		myFiles.uploadFile(filePath, fileName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(openfolderName);
		docLibPge.commonMethodForClickOnMoreSettingsOption(openfolderName, action);
		shareboxPg.commonMethodToEnterSharingProperties(properties, message, language, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
			
	}
	
	
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
