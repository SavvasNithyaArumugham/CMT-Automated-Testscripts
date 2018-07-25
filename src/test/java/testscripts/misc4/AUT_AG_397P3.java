package testscripts.misc4;

import java.io.File;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
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
public class AUT_AG_397P3 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-2922_Verify Download option is visible for Contributor role at site in document file preview page.<br>"+
				"ALFDEPLOY-2922_Verify Download button is not visible to user who has Contributor role at Site and has assigned Reviewer role at Asset by the site manager<br>"+
				"ALFDEPLOY-2922_Verify for avi file , Download button is visible to user who is Manager/Coordinator/Collaborator/Contributor/Consumer at Site .<br>"+
				"ALFDEPLOY-2922_Verify for avi file, Download button is not visible when manager changes users( Coordinator/Collaborator/Contributor/Consumer )role to Reviewer<br>");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
	
		String fileName = dataTable.getData("MyFiles", "FileName");
		String file2 = dataTable.getData("MyFiles", "CreateFileDetails");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String downloadopt = dataTable.getData("MyFiles", "BrowseActionName");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		File downloadedFile = null;
		functionalLibrary.loginAsValidUser(signOnPage);
		
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		sitesPage.documentdetails(fileName);
		
		docDetailsTest.verifyElementPresent(docDetailsTest.downloadbutton,downloadopt);
		
		UIHelper.click(driver, docDetailsTest.downloadbutton);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, fileName);
		sitesPage.commonMethodForPerformBrowseOption(fileName, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, fileName);
		downloadedFile = new File(downloadFilePath + "/" + fileName);
		if (downloadedFile.exists() && !fileName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + fileName + " downloaded successfully", Status.PASS);
		}else {
			report.updateTestLog("Verify download file", "File: " + fileName + " downloaded unsuccessfully", Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(file2);
		
		if(!docDetailsTest.verifyElementPresentreturn(docDetailsTest.downloadbutton,downloadopt)) {
			report.updateTestLog("Verify Download Button is available",
					"Download Button Element is not displayed in the page", Status.PASS);
		}else {
			report.updateTestLog("Verify Download Button is available",
					"Download Button Element is displayed in the page whihc is not expected", Status.FAIL);
		}
		
		
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}