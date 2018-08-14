package testscripts.links;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC265 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC265()
	{
		testParameters.setCurrentTestDescription("Verify user is able to find 'Link to 'functionality for file/Folder in Document Library.");
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
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		
		myFiles.deleteUploadedFile(fileName);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, propertyName);		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}