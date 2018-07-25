package testscripts.healthcheck;

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
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_006P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_007P1()
	{
		testParameters.setCurrentTestDescription("1. Verify user is able to download the shared folder as a zip file by clicking on 'Download as Zip' button"
				+ "<br>2. Verify user is able to download the shared folder by clicking on 'Download ZIP' button"
				+ "<br>3. Verify user is able to download the individual files inside the shared folder by clicking on 'Download' button displayed below each file - Part1: Share a folder using sharebox option and download the shred folder using 'Download as Zip' in document library");
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
		
		String shareProperties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

        myFiles.createFolder(folderDetails);
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		
		for(String folderName:folderNamesList)
		{
			myFiles.openCreatedFolder(folderName);
			
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			sitesPage.enterIntoDocumentLibrary();
			
			sitesPage.clickOnMoreSetting(folderName);
	
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
					moreSettingsOptionName);
			
			docLibPg.clickOnOkBtnInPopup();
			
			shareboxPg.commonMethodToEnterSharingProperties(shareProperties,
					message, notification, notifyDetails);
			shareboxPg.clickOnSaveBtnInSharingPopup();
			
		/*	//Verify user is able to download the shared folder as a zip file by clicking on 'Download as Zip' button
			docLibPg.selectAllFilesAndFolders();
			sitesPage.clickOnSelectedItems();
			
			new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath, folderName+".zip");
			
			String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
			
			new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, folderName+".zip");*/
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}