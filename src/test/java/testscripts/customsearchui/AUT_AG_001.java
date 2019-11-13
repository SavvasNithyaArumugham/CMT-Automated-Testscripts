package testscripts.customsearchui;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_001 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_001()
	{
		testParameters.setCurrentTestDescription("Verify that custom search can be performed by Course");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);
		
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String courseName = dataTable.getData("MyFiles", "Version");
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		// goto site > document lib
				homePageObj.navigateToSitesTab();
				sitesPage.createSite(siteNameValue, "No");
				sitesPage.openSiteFromRecentSites(siteNameValue);
				sitesPage.enterIntoDocumentLibrary();

				// go to Course plan and upload course
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");
				String filePath = dataTable.getData("MyFiles", "FilePath");
				String fileName = dataTable.getData("MyFiles", "FileName");
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				// verify import process progress
				collectionPg.verifyImportProcessProgress(fileName);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				homePageObj.navigateToCustomSearch();
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.customSearchWithCourse(siteNameValue,courseName);	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}