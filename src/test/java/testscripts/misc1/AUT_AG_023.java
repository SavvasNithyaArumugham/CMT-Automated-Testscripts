package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author Naresh Kumar Salla
 */
public class AUT_AG_023 extends TestCase {
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_023()
	{
		testParameters.setCurrentTestDescription("1. Verify that version of the asset not get incremented, while performing Edit property when file is present in document library using 'Browse Action'"
				+ "<br.2. Verify that version of the asset not get incremented, while performing Edit property when file is present in document library using 'Document Action'");
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		UIHelper.waitFor(driver);
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPage.clickAllProperties();
		docDetailsPage.enterDataAndSaveIntoEditProperties();
		
		docLibPage.checkVersionAvailable(fileName, "Verify the file version");
		
		docLibPage.openAFile(fileName);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyEditPropertyVersion(fileName);
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		docLibPage.openAFile(fileName);
		docDetailsPage.performEditPropertiesDocAction();
		docDetailsPage.enterDataAndSaveIntoEditProperties();
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.checkVersionAvailable(fileName, "Verify the file version");
		
		docLibPage.openAFile(fileName);
		docDetailsPageTest.verifyEditPropertyVersion(fileName);
		
	}

	@Override
	public void tearDown() {
		
	}

}