package testscripts.misc2;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_150 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_046()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to download a file locked file as read-only");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		UIHelper.waitFor(driver);
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		
		String moreSettOptVal = dataTable.getData("MyFiles", "MoreSettingsOption");
	
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		String finalFileName;
		if (fileName.contains(".")) {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0];
			finalFileName = part1 + "." + splitVal[1];
		} else {
			finalFileName = fileName;
		}
		
		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,
				finalFileName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettOptVal);
		
		new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, finalFileName);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.isFileLocked(fileName);
		
		//sitesPage.documentdetails(fileName);
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPage.deleteLockedFileInDownloadedPath(false);
		
		String browseOption = dataTable.getData("MyFiles", "BrowseActionName");
		sitesPage.commonMethodForPerformBrowseOption(fileName, browseOption);
		
		//docDetailsPage.downloadLockedFileInDocumentDetailsPage();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}