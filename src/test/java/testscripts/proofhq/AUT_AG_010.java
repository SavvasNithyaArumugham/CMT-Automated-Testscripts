package testscripts.proofhq;


import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoProofHQPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
 * @author Cognizant
 */
public class AUT_AG_010 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void proofhq_09()
	{
		testParameters.setCurrentTestDescription("Verifying the External sharebox feature for proof related folders");
		
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
		

	/*	AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMySitesPage mySites=new AlfrescoMySitesPage(scriptHelper);
		String actualHome = dataTable.getData("Home", "Status");
		String submenu = dataTable.getData("Home", "DashletName");
		String email = dataTable.getData("Home", "ColumnNoofAddDashlet");
		String token = dataTable.getData("Home", "ColumnNoofremvDashlet");*/
		
		 //homePageObj.UserMenucommonMethod(actualHome);
		
		//mySites.navigatetoconfigMenu(submenu);
		
	//	AlfrescoProofHQPage proofHQObj = new AlfrescoProofHQPage(scriptHelper);
		
	//	proofHQObj.configProofHQ(email, token);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);	
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		UIHelper.waitFor(driver);
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(folderName);
		myFiles.uploadFile(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName, "");
		
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption);
		
		String recepients=  dataTable.getData("MyFiles", "Recepients");
		String policy=  dataTable.getData("MyFiles", "ProofHQPolicy");
		sitesPage.addProofHQ(recepients, policy, fileName);
		
		sitesPage.enterIntoDocumentLibrary();
		//sitesPage.documentdetails(folderName);
		String moreSettingsOptionName = dataTable.getData("MyFiles", "BrowseActionName");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
				moreSettingsOptionName);

		docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();

	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}