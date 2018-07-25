package testscripts.sharebox;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_034P3 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void sharebox_033()
	{
		testParameters.setCurrentTestDescription("1. Verify whether the Unzip option is displayed in alfresco for the zip file which is uploaded via sharebox by External User"
				+ "<br>2. Verify whether the user can share the folder externally which is unzipped form the Zip file - Part3: Verify Unzip option is displayed for uploaded file in Alfresco");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
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

		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			myFiles.openCreatedFolder(folderName);
			
			String fileName = dataTable.getData("MyFiles", "FileName");
			String folder = dataTable.getData("MyFiles", "Version");
			
			String splitVal[] = fileName.split(Pattern.quote("."));
			String finalFileName = splitVal[0];
			
			myFilesTestObj.verifyUploadedFile(fileName,"");
			
			myFiles.openUploadedOrCreatedFile(fileName,"");
			
			docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
			
			String extractTo = dataTable.getData("Document_Details", "ExtractTo");
			
			docDetailsPageObj.performUnzipDocAction(extractTo);
			sitesPage.enterIntoDocumentLibrary();
			sitesPage.documentdetails(folder);
		//	docDetailsPageObj.backToFolderOrDocumentPage("");
			
			myFilesTestObj.verifyUnzippedFolder(finalFileName);
			
			sitesPage.clickOnMoreSetting(finalFileName);
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(finalFileName,
					moreSettingsOptionName);
			
			docLibPg.clickOnOkBtnInPopup();
			
			shareboxPg.commonMethodToEnterSharingProperties(shareProperties,
					message, notification, notifyDetails);
			shareboxPg.clickOnSaveBtnInSharingPopup();
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}