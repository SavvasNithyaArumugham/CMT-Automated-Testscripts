package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_074 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_064()
	{
		testParameters.setCurrentTestDescription("Verify that user get the correct message when perform edit offline to the Followed asset.");
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
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");

		String file = dataTable.getData("Sites", "FileName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		if(sitesPage.Checkdocument(file)){
			appSearchPg.deletedocument(file);
			appSearchPg.deletedocument(file); 
		}else {
			
		}
		
		myFiles.uploadFileInMyFilesPage(filePath, file);
		
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(scriptHelper);
		String link = dataTable.getData("MyFiles", "MoreSettingsOption");
		docLibPgObj.clickOnDocOptions(file, link);	
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPage.deleteFileInDownloadedPath(false);
		sitesPage.documentdetails(file);
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		docDetailsPageObj.commonMethodForPerformDocAction(docActionVal);
		
		AlfrescoDocumentDetailsPageTest docDetailsTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsTest.verifyEditoffMessage();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}