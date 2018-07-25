package testscripts.misc5;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
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
public class AUT_AG_373 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC5_02()
	{
		
		testParameters.setCurrentTestDescription("1.Able to preview the Abobe illustrator file from document library after Edit offline was applied to file."
				+"<br> 2. Able to preview the Abobe illustrator file from document library after applied share folder externally."
				+"<br> 3. Able to preview the Abobe illustrator file from the document library in the destination folder after the file gets linked."
				+"<br>3.Able to preview the Abobe illustrator file from the document library in the Copy to destination folder.");
		
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String folderName1 = dataTable.getData("MyFiles", "TagName");
		String folderDetails = dataTable.getData("MyFiles", "Version");
		String folderDetails1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String propertyName = dataTable.getData("Sites", "Role");
		String edit = dataTable.getData("Sites", "InviteUserName");

		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPge.deleteAllFilesAndFolders();
		
		
		String fileName = dataTable.getData("MyFiles", "FileName");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(folderName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		sitesPage.clickOnMoreSetting(fileName);
	
		docLibPge.commonMethodForClickOnMoreSettingsOption(fileName, edit);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, "");
		
	
	
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(folderName);
		
		//docLibPge.deleteAllFilesAndFolders();
		
		sitesPage.enterIntoDocumentLibrary();
		
	/*	sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
				moreSettingsOptionName);

		docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();*/
		
		
	//	sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, ""); 
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		docLibPge.deleteAllFilesAndFolders();
		sitesPage.enterIntoDocumentLibrary();
		//docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails1);
	
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, propertyName);
		
		
		docDetailsPageObj.selectFolderInLinkPopUp(siteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, ""); 
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPge.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails1);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageObj.clickMoveToDocAction();
		docDetailsPageObj.selectFileInMovePopUp();
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(folderName1);
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, "");
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPge.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails1);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageObj.clickCopyToDocAction();
		docDetailsPageObj.selectFolderToCopyInCopyPopUp(folderName1);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(folderName1);
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, "");
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}