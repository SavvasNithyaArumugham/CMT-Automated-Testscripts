package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_006 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void sharebox_006()
	{
		testParameters.setCurrentTestDescription("Verify user is not able to save the sharing details in the sharebox folder window without entering the mandatory fields");
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

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoShareboxPageTest shareboxPgTest = new AlfrescoShareboxPageTest(scriptHelper);
		
		homePage.navigateToSitesTab();
		
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
		
		String moreSettingsOptionName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		
		String expectedFieldErroVal = dataTable.getData("Sharebox", "ErrorMessageValue");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

        myFiles.createFolder(folderDetails);
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnMoreSetting(folderName);
	
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
					moreSettingsOptionName);
			
			docLibPg.clickOnOkBtnInPopup();
			
			shareboxPg.commonMethodToEnterSharingProperties(properties,
					message, notification, notifyDetails);
			
			
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxPg.disableokbutton);
				String getMessage = UIHelper.findAnElementbyXpath(driver, shareboxPg.disableokbutton).getAttribute("disabled");
				
				if(getMessage.equalsIgnoreCase("true")){
					
					report.updateTestLog(" Verify sharebox folder window without entering the mandatory fields",
							"sharebox folder window without entering the mandatory fields is verified successfully", Status.PASS);
				}else{
					report.updateTestLog(" Verify sharebox folder window without entering the mandatory fields",
							"sharebox folder window without entering the mandatory fields is verified failed", Status.FAIL);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				report.updateTestLog(" Verify sharebox folder window without entering the mandatory fields",
						"sharebox folder window without entering the mandatory fields is verified failed", Status.FAIL);
			}
		/*	shareboxPg.clickOnSaveBtnInSharingPopup();
			
			shareboxPgTest.checkFieldErrorMsgInSharingPropWindow(
					expectedFieldErroVal);*/
			
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}