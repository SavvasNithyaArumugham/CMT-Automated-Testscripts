package testscripts.misc1;

import java.io.File;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_087 extends TestCase{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_077()
	{
		testParameters.setCurrentTestDescription("Verify on clicking Edit Offline option in more button allows the user to download the file to local.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}	
	
	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String downloadedFilePath = Settings.getInstance().getProperty(
				"DefaultDownloadPath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		docLibPg.deleteAllFilesAndFolders();
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);		
		sitesPage.clickOnMoreOptionLink(fileName);
		
		File downloadedFile = new File(downloadedFilePath + "/" + fileName);
		long start_time = System.currentTimeMillis();
		long wait_time = 200000;
		long end_time = start_time + wait_time;
		while (System.currentTimeMillis() < end_time) {
			if (downloadedFile.exists()) {
				report.updateTestLog("Verify the downloaded file",
						"File downloaded sucessfully",
						Status.PASS);
				break;
			}
		}
		if (!downloadedFile.exists()) {
			report.updateTestLog("Verify the downloaded file",
					"File not downloaded", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
