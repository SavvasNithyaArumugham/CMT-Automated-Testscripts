package testscripts.misc3;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_250 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to download  locked file as read-only");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.Chrome);
		
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
		AlfrescoHomePage homePage= new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTest = new AlfrescoMyFilesPageTest(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		UIHelper.waitFor(driver);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		String downloadedFilePath = properties.getProperty(
				"DefaultDownloadPath");
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	myFiles.uploadFile(filePath, fileName);
	
	myFilesTest.verifyUploadedFile(fileName, "");
		

		
	/*	if(!sitesPage.checkFileIsLocked(fileName))
		{
			sitesPage.documentdetails(fileName);
			
			new FileUtil().deleteIfFileExistsInDownloadPath(downloadedFilePath,
					fileName);
			docDetailsPageObj.performEditOfflineDocAction();
			
			new FileUtil().waitUptoFileDownloadComplete(downloadedFilePath, fileName);
		}
		else
		{
			sitesPage.documentdetails(fileName);
		}*/
		
		//sitesPage.isFileLocked(fileName);
		//sitesPage.documentdetails(fileName);
		
		String moreSettingOption= dataTable.getData("MyFiles", "MoreSettingsOption");
	   String[] moreSettingOptionsList=moreSettingOption.split(",");
		
	   sitesPage.clickOnMoreSetting(fileName);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingOptionsList[0]);
		
	    UIHelper.waitFor(driver);
	
	    myFiles.downloadFileAsZip(fileName);
		//docLibPg.downloadAsZipInDocumentLibrary(fileName);
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		//docDetailsPage.deleteLockedFileInDownloadedPath(false);
		String finalFileName;
		if (fileName.contains(".")) {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0] + " (Working Copy)";
			finalFileName = part1 + "." + splitVal[1];
		} else {
			finalFileName = fileName;
		}
		
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadedFilePath,
				finalFileName);
		
		AlfrescoDocumentDetailsPageTest docDetailsTestPage = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsTestPage.verifyLockedDownloadedFile(false);
		
		/*docDetailsPage.downloadLockedFileInDocumentDetailsPage();
		
		AlfrescoDocumentDetailsPageTest docDetailsTestPage = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsTestPage.verifyLockedDownloadedFile(false);
*/
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}