package testscripts.misc1;

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
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_049 extends TestCase{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_040()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to Edit offline the file user wants and the file should be Locked (When user perform Edit offline)");
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
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		docLibPg.deleteAllFilesAndFolders();
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);	
		sitesPage.clickOnMoreOptionLink(fileName);
		
		if(docLibPg.isOfflineLockMsgDisplayed()){
			report.updateTestLog(
					"Verify the user is able to lock a file",
					"File Locked successfully."
							+ "<br><b> Locked File Name </b> :"
							+ fileName, Status.PASS);
		}else{
			report.updateTestLog(
					"Verify the user is able to lock a file",
					"File not Locked."
							+ "<br><b> File Name </b> :"
							+ fileName, Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
